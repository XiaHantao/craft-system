<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" >
      <el-form-item label="车型" prop="vehicleModel">
        <el-input
            v-model="queryParams.vehicleModel"
            placeholder="请输入车型"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传时间" prop="uploadTime">
        <el-date-picker clearable
                        v-model="queryParams.uploadTime"
                        type="date"
                        value-format="YYYY-MM-DD"
                        placeholder="请选择上传时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="上传人" prop="uploadPerson">
        <el-input
            v-model="queryParams.uploadPerson"
            placeholder="请输入上传人"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-tabs v-model="activeName">
      <el-tab-pane label="专用规程设计" name="first">
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
                type="primary"
                plain
                icon="Plus"
                @click="handleAdd"
                v-hasPermi="['process:processSpecificationDesign:add']"
            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
                type="success"
                plain
                icon="Edit"
                :disabled="single"
                @click="handleUpdate"
                v-hasPermi="['process:processSpecificationDesign:edit']"
            >修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
                type="danger"
                plain
                icon="Delete"
                :disabled="multiple"
                @click="handleDelete"
                v-hasPermi="['process:processSpecificationDesign:remove']"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
                type="warning"
                plain
                icon="Download"
                @click="handleExport"
                v-hasPermi="['process:processSpecificationDesign:export']"
            >导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="processSpecificationDesignList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="车型" align="center" prop="vehicleModel" />
          <el-table-column label="工艺文件" align="center" prop="filePath">
            <template v-slot:default="scope">
              <el-button v-if="scope.row.filePath" icon="Download" @click="downloadFile(scope.row.filePath)"></el-button>
            </template>
          </el-table-column>
          <el-table-column label="关键工艺控制工序" align="center" prop="keyProcessControlProcedures" />
          <el-table-column label="上传人" align="center" prop="uploadPerson" />
          <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
            <template #default="scope">
              <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template #default="scope">
              <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['process:processSpecificationDesign:edit']">修改</el-button>
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:processSpecificationDesign:remove']">删除</el-button>
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
      </el-tab-pane>

      <el-tab-pane label="通用规程设计" name="second">
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
                type="primary"
                plain
                icon="Plus"
                @click="handlelAddGeneral"
                v-hasPermi="['process:processSpecificationDesign:add']"
            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
                type="success"
                plain
                icon="Edit"
                :disabled="single"
                @click="handleUpdateGeneral"
                v-hasPermi="['process:processSpecificationDesign:edit']"
            >修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
                type="danger"
                plain
                icon="Delete"
                :disabled="multiple"
                @click="handleDelete"
                v-hasPermi="['process:processSpecificationDesign:remove']"
            >删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
                type="warning"
                plain
                icon="Download"
                @click="handleExport"
                v-hasPermi="['process:processSpecificationDesign:export']"
            >导出</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="generalList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="车型" align="center" prop="vehicleModel" />
          <el-table-column label="工艺类型" align="center" prop="processType" />
          <el-table-column label="工艺文件" align="center" prop="filePath">
            <template v-slot:default="scope">
              <el-button v-if="scope.row.filePath" icon="Download" @click="downloadFile(scope.row.filePath)"></el-button>
            </template>
          </el-table-column>
          <el-table-column label="上传人" align="center" prop="uploadPerson" />
          <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
            <template #default="scope">
              <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template #default="scope">
              <el-button link type="primary" icon="Edit" @click="handleUpdateGeneral(scope.row)" v-hasPermi="['process:processSpecificationDesign:edit']">修改</el-button>
              <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['process:processSpecificationDesign:remove']">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
            v-show="generalTotal>0"
            :total="generalTotal"
            v-model:page="queryParams.pageNum"
            v-model:limit="queryParams.pageSize"
            @pagination="getList"
        />
      </el-tab-pane>
    </el-tabs>


    <!-- 添加或修改专用规程设计对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="processSpecificationDesignRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车型" prop="vehicleModel">
          <el-select
              v-model="form.vehicleModel"
              placeholder="请选择车型"
              clearable
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
        <el-form-item label="工艺文件" prop="filePath">
          <file-upload v-model="form.filePath"/>
        </el-form-item>
        <el-form-item label="关键工艺控制工序" prop="keyProcessControlProcedures">
          <el-input v-model="form.keyProcessControlProcedures" placeholder="请输入关键工艺控制工序" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 添加或修改通用规程设计对话框 -->
    <el-dialog :title="title" v-model="openGeneral" width="500px" append-to-body>
      <el-form ref="processSpecificationDesignRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车型" prop="vehicleModel">
          <el-select
              v-model="form.vehicleModel"
              placeholder="请选择车型"
              clearable
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
        <el-form-item label="工艺类型" prop="processType">
          <el-select
              v-model="processTypeArray"
              placeholder="请选择工艺类型"
              clearable
              multiple
              @keyup.enter="handleQuery"
          >
            <el-option
                label="通用工艺"
                value="通用工艺"
            ></el-option>
            <el-option
                label="标准工艺"
                value="标准工艺"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="工艺文件" prop="filePath">
          <file-upload v-model="form.filePath"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitGeneralForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="ProcessSpecificationDesign">
import {
  listProcessSpecificationDesign,
  getProcessSpecificationDesign,
  delProcessSpecificationDesign,
  addProcessSpecificationDesign,
  updateProcessSpecificationDesign,
  listGeneral
} from "@/api/process/processSpecificationDesign";
import {listModelTable} from "@/api/process/modelTable";
import {getUserProfile} from "@/api/system/user";

const { proxy } = getCurrentInstance();

const processTypeArray = ref([]);  // 通用工艺类型数组
const activeName = ref('first'); // tab标签
const userInfoList = ref([]);  // 用户信息列表
const modelList = ref([]); // 车型列表
const processSpecificationDesignList = ref([]);  // 专用规程列表
const generalList = ref([]);  // 通用规程列表
const open = ref(false);  // 专用规程设计对话框
const openGeneral = ref(false); // 通用规程设计对话框
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);  // 专用规程总数
const generalTotal = ref(0); // 通用规程总数
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    vehicleModel: null,
    processType: null,
    filePath: null,
    keyProcessControlProcedures: null,
    uploadTime: null,
    uploadPerson: null,
    tabFlag: null
  },
  rules: {
    vehicleModel: [
      { required: true, message: "车型不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询规程列表 */
function getList() {
  loading.value = true;
  // 查询专用规程列表
  listProcessSpecificationDesign(queryParams.value).then(response => {
    processSpecificationDesignList.value = response.rows;
    total.value = response.total;
  });

  // 查询通用规程列表
  listGeneral(queryParams.value).then(response => {
    generalList.value = response.rows;
    generalTotal.value = response.total;
  });

  getUserProfile().then(response => {
    userInfoList.value = response.data;
  });

  loading.value = false;
}



// 取消按钮
function cancel() {
  open.value = false;
  openGeneral.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    vehicleModel: null,
    processType: null,
    filePath: null,
    keyProcessControlProcedures: null,
    uploadTime: null,
    uploadPerson: null,
    tabFlag: null
  };
  processTypeArray.value = []; // 重置为数组
  proxy.resetForm("processSpecificationDesignRef");
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

/** 新增专用规程按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加专用规程设计";
}

/** 新增通用规程按钮操作 */
function handlelAddGeneral() {
  reset();
  openGeneral.value = true;
  title.value = "添加通用规程设计";
}

/** 修改专用规程按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getProcessSpecificationDesign(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改专用规程设计";
  });
}

/** 修改通用规程按钮操作 */
function handleUpdateGeneral(row) {
  reset();
  const _id = row.id || ids.value
  getProcessSpecificationDesign(_id).then(response => {
    form.value = response.data;
    processTypeArray.value = form.value.processType ? form.value.processType.split(',') : []; // 将逗号分隔的字符串转换
    openGeneral.value = true;
    title.value = "修改通用规程设计";
  });
}

/** 专用工艺提交按钮 */
function submitForm() {
  proxy.$refs["processSpecificationDesignRef"].validate(valid => {
    if (valid) {
      form.value.uploadPerson = userInfoList.value.nickName;
      form.value.tabFlag = 1;
      if(form.value.filePath) {
        form.value.uploadTime = new Date();
      } else form.value.uploadTime = null;
      if (form.value.id != null) {
        updateProcessSpecificationDesign(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addProcessSpecificationDesign(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 通用工艺提交按钮 */
function submitGeneralForm() {
  proxy.$refs["processSpecificationDesignRef"].validate(valid => {
    if (valid) {
      form.value.uploadPerson = userInfoList.value.nickName;
      console.log("processTypeArray==>",processTypeArray);
      form.value.processType = processTypeArray.value.join(',');
      form.value.tabFlag = 2;
      if(form.value.filePath) {
        form.value.uploadTime = new Date();
      } else form.value.uploadTime = null;
      if (form.value.id != null) {
        updateProcessSpecificationDesign(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          openGeneral.value = false;
          getList();
        });
      } else {
        addProcessSpecificationDesign(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          openGeneral.value = false;
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
    return delProcessSpecificationDesign(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('process/processSpecificationDesign/export', {
    ...queryParams.value
  }, `processSpecificationDesign_${new Date().getTime()}.xlsx`)
}

/** 查询车型列表 */
function getModelList() {
  listModelTable().then(response => {
    modelList.value = response.rows;
  });
}


/** 文件下载 */
function downloadFile(filePath) {
  const paths = filePath.split(',').map(path => path.trim());
  paths.forEach(path => {
    proxy.$download.resource(path);
  });
}

getModelList();
getList();
</script>
