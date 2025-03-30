<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物料号" prop="materialNumber">
        <el-input
            v-model="queryParams.materialNumber"
            placeholder="请输入物料号"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物料名称" prop="materialName">
        <el-input
            v-model="queryParams.materialName"
            placeholder="请输入物料名称"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input
            v-model="queryParams.remarks"
            placeholder="请输入备注"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审查人" prop="reviewer">
        <el-input
            v-model="queryParams.reviewer"
            placeholder="请输入审查人"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审查时间" prop="reviewTime">
        <el-date-picker clearable
                        v-model="queryParams.reviewTime"
                        type="date"
                        value-format="YYYY-MM-DD"
                        placeholder="请选择审查时间">
        </el-date-picker>
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
            v-hasPermi="['process:processReviewTable:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['process:processReviewTable:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['process:processReviewTable:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['process:processReviewTable:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="processReviewTableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="物料号" align="center" prop="materialNumber" />
      <el-table-column label="物料名称" align="center" prop="materialName" />
      <el-table-column label="BOM文件" align="center" prop="materialFilePath">
        <template v-slot:default="scope">
          <el-button v-if="scope.row.materialFilePath" icon="Download" @click="downloadFile(scope.row.materialFilePath)"></el-button>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remarks" />
      <el-table-column label="附带图片" align="center" prop="imagePath" width="100">
        <template #default="scope">
          <image-preview v-if="scope.row.imagePath" :src="scope.row.imagePath" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="审查状态" align="center">
        <template #default="scope">
          <el-tag :type="scope.row.reviewStatus === '已审查' ? 'success' : 'danger'" >
            {{ scope.row.reviewStatus }}
          </el-tag>
        </template>
      </el-table-column>


      <el-table-column label="审查记录文件" align="center" prop="reviewRecordPath">
        <template v-slot:default="scope">
          <el-button v-if="scope.row.reviewRecordPath" icon="Download" @click="downloadFile(scope.row.reviewRecordPath)"></el-button>
        </template>
      </el-table-column>
      <el-table-column label="审查人" align="center" prop="reviewer" />
      <el-table-column label="审查时间" align="center" prop="reviewTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.reviewTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['process:processReviewTable:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:processReviewTable:remove']">删除</el-button>
          <el-button link type="primary" icon="Check" @click="handleReview(scope.row)" v-hasPermi="['process:processReviewTable:review']">审查</el-button>
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

    <!-- 添加或修改工艺性审查对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="processReviewTableRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物料号" prop="materialNumber">
          <el-select
              v-model="form.materialNumber"
              placeholder="请选择物料号"
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
        <el-form-item label="物料名称" prop="materialName">
          <el-input v-model="form.materialName" placeholder="请输入物料名称" />
        </el-form-item>
        <el-form-item label="BOM文件" prop="materialFilePath">
          <file-upload v-model="form.materialFilePath"/>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="附带图片" prop="imagePath">
          <image-upload v-model="form.imagePath"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <el-dialog :title="title" v-model="reviewOpen" width="500px" append-to-body>
      <el-form ref="processReviewTableRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="审查记录文件" prop="reviewRecordPath">
          <file-upload v-model="form.reviewRecordPath"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="reviewSubmitForm">确 定</el-button>
          <el-button @click="reviewCancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="ProcessReviewTable">
import { listProcessReviewTable, getProcessReviewTable, delProcessReviewTable, addProcessReviewTable, updateProcessReviewTable } from "@/api/process/processReviewTable";
import {listModelTable} from "@/api/process/modelTable";
import ImagePreview from "@/components/ImagePreview/index.vue";
import {getUserProfile} from "@/api/system/user";

const { proxy } = getCurrentInstance();

const userInfoList = ref([]);  //用户信息
const processReviewTableList = ref([]);
const modelList = ref([]); // 车型列表
const open = ref(false);
const reviewOpen = ref(false); // 审查对话框
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
    materialNumber: null,
    materialName: null,
    materialFilePath: null,
    remarks: null,
    imagePath: null,
    reviewStatus: null,
    reviewRecordPath: null,
    reviewer: null,
    reviewTime: null
  },
  rules: {
    materialNumber: [
      { required: true, message: "物料号不能为空", trigger: "blur" }
    ],
    materialName: [
      { required: true, message: "物料名称不能为空", trigger: "blur" }
    ],
    materialFilePath: [
      { required: true, message: "BOM文件不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询工艺性审查列表 */
function getList() {
  loading.value = true;
  listProcessReviewTable(queryParams.value).then(response => {
    processReviewTableList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
  });
  getUserProfile().then(response => {
    userInfoList.value = response.data;
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
    materialNumber: null,
    materialName: null,
    remarks: null,
    imagePath: null,
    reviewRecordPath: null,
    reviewer: null,
    reviewTime: null
  };
  proxy.resetForm("processReviewTableRef");
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
  title.value = "添加BOM文件";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProcessReviewTable(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改BOM文件";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["processReviewTableRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        console.log("form.value===>",form.value);
        updateProcessReviewTable(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        form.value.reviewStatus = "未审查";
        console.log("form.value2===>",form.value);
        addProcessReviewTable(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除编号为"' + _ids + '"的数据项？').then(function() {
    return delProcessReviewTable(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('process/processReviewTable/export', {
    ...queryParams.value
  }, `processReviewTable_${new Date().getTime()}.xlsx`)
}

/** 文件下载 */
function downloadFile(filePath) {
  proxy.$download.resource(filePath);
}

/** 工艺审查文件上传 */
function handleReview(row) {
  reset();
  getProcessReviewTable(row.id).then(response => {
    form.value = response.data;
    reviewOpen.value = true;
    title.value = "工艺审查文件上传";
  });
}

/** 工艺审查文件上传提交按钮 */
function reviewSubmitForm() {
  proxy.$refs["processReviewTableRef"].validate(valid => {
    if (valid) {
      if(form.value.reviewRecordPath) {
        form.value.reviewStatus = "已审查";
      } else form.value.reviewStatus = "未审查";
      form.value.reviewer = userInfoList.value.nickName;
      form.value.reviewTime = new Date();
      updateProcessReviewTable(form.value).then(response => {
        proxy.$modal.msgSuccess("上传成功");
        reviewOpen.value = false;
        getList();
      });
    }
  });
}

/** 工艺审查文件上传取消按钮 */
function reviewCancel() {
  reviewOpen.value = false;
}

getModelList();
getList();
</script>

<style scoped>
</style>