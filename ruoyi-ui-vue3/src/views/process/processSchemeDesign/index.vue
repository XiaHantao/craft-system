<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车型" prop="vehicleModel">
        <el-input
          v-model="queryParams.vehicleModel"
          placeholder="请输入车型"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评审时间" prop="reviewTime">
        <el-date-picker clearable
          v-model="queryParams.reviewTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择评审时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="评审人" prop="reviewer">
        <el-input
          v-model="queryParams.reviewer"
          placeholder="请输入评审人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['process:processSchemeDesign:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['process:processSchemeDesign:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['process:processSchemeDesign:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['process:processSchemeDesign:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="processSchemeDesignList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="车型" align="center" prop="vehicleModel" />
      <el-table-column label="工艺方案文件" align="center" prop="filePath">
        <template v-slot:default="scope">
          <el-button v-if="scope.row.filePath" icon="Download" @click="downloadFile(scope.row.filePath)"></el-button>
        </template>
      </el-table-column>
      <el-table-column label="评审状态" align="center" prop="reviewStatus">
        <template #default="scope">
          <el-tag v-if="scope.row.reviewStatus" :type="scope.row.reviewStatus === '已评审' ? 'success' : 'danger'" >
            {{ scope.row.reviewStatus }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="评审时间" align="center" prop="reviewTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.reviewTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="评审人" align="center" prop="reviewer" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['process:processSchemeDesign:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:processSchemeDesign:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改工艺方案设计对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="processSchemeDesignRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车型" prop="vehicleModel">
          <el-select
              v-model="form.vehicleModel"
              placeholder="请选择车型"
              clearable
              filterable
              @keyup.enter="handleQuery"
          >
            <el-option
                v-for="model in modelList"
                :key="model.vehicleModel"
                :label="model.vehicleModel"
                :value="model.vehicleModel"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工艺方案文件" prop="filePath">
          <file-upload v-model="form.filePath"/>
        </el-form-item>
        <el-form-item label="评审状态" prop="reviewStatus">
          <el-select
              v-model="form.reviewStatus"
              placeholder="请选择评审状态"
              clearable
              filterable
          >
            <el-option
                label="已评审"
                value="已评审"
            ></el-option>
            <el-option
                label="未评审"
                value="未评审"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="评审时间" prop="reviewTime">
          <el-date-picker clearable
            v-model="form.reviewTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择评审时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="评审人" prop="reviewer">
          <el-input v-model="form.reviewer" placeholder="请输入评审人" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="ProcessSchemeDesign">
import { listProcessSchemeDesign, getProcessSchemeDesign, delProcessSchemeDesign, addProcessSchemeDesign, updateProcessSchemeDesign } from "@/api/process/processSchemeDesign";
import {listModelTable} from "@/api/process/modelTable";

const { proxy } = getCurrentInstance();

const modelList = ref([]); // 车型列表
const processSchemeDesignList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    vehicleModel: null,
    filePath: null,
    reviewStatus: null,
    reviewTime: null,
    reviewer: null
  },
  rules: {
    vehicleModel: [
      { required: true, message: "车型不能为空", trigger: "blur" }
    ],
    filePath: [
      { required: true, message: "工艺方案文件不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询工艺方案设计列表 */
function getList() {
  loading.value = true;
  listProcessSchemeDesign(queryParams.value).then(response => {
    processSchemeDesignList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });
}

/** 查询车型列表 */
function getModelList() {
  listModelTable().then(response => {
    modelList.value = response.rows;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    vehicleModel: null,
    filePath: null,
    reviewStatus: null,
    reviewTime: null,
    reviewer: null
  };
  proxy.resetForm("processSchemeDesignRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加工艺方案设计";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProcessSchemeDesign(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改工艺方案设计";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["processSchemeDesignRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateProcessSchemeDesign(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProcessSchemeDesign(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除工艺方案设计编号为"' + _ids + '"的数据项？').then(function() {
    return delProcessSchemeDesign(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('process/processSchemeDesign/export', {
    ...queryParams.value
  }, `processSchemeDesign_${new Date().getTime()}.xlsx`)
}

/** 文件下载 */
function downloadFile(filePath) {
  proxy.$download.resource(filePath);
}

getModelList();
getList();
</script>
