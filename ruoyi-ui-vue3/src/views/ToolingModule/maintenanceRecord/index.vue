<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="创建者" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建者"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择创建时间">
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
          v-hasPermi="['ToolingModule:maintenanceRecord:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ToolingModule:maintenanceRecord:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ToolingModule:maintenanceRecord:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['ToolingModule:maintenanceRecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="maintenanceRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="id" />-->
      <el-table-column label="维修内容" align="center" prop="maintenanceRecord" />
<!--      <el-table-column label="维修文件" align="center" prop="maintenanceFile" />-->
<!--      <el-table-column label="维修文件" align="center" prop="maintenanceFile">-->
<!--        <template #default="{ row }">-->
<!--            <span v-if="row.maintenanceFile">-->
<!--              &lt;!&ndash; 如果有文件地址，显示预览按钮 &ndash;&gt;-->
<!--              <el-button type="text" @click="previewFile(row.maintenanceFile)">预览</el-button>-->
<!--            </span>-->
<!--          <span v-else>-->
<!--            &lt;!&ndash; 如果没有文件地址，显示“无图纸” &ndash;&gt;-->
<!--            无文件-->
<!--          </span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="维修文件" align="center" prop="maintenanceFile">
        <template v-slot:default="scope">
          <el-button  v-if="scope.row.maintenanceFile" icon="Download" @click="downloadFile(scope.row.maintenanceFile)">
          </el-button>
        </template>
      </el-table-column>
<!--      <el-table-column label="创建者" align="center" prop="createBy" />-->
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['ToolingModule:maintenanceRecord:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['ToolingModule:maintenanceRecord:remove']">删除</el-button>
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

    <!-- 添加或修改维修记录对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="maintenanceRecordRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="维修记录" prop="maintenanceRecord">
          <el-input v-model="form.maintenanceRecord" placeholder="请输入维修记录" />
        </el-form-item>
        <el-form-item label="维修文件" prop="maintenanceFile">
          <file-upload v-model="form.maintenanceFile"/>
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

<script setup name="MaintenanceRecord">
import {
  listMaintenanceRecord,
  getMaintenanceRecord,
  delMaintenanceRecord,
  addMaintenanceRecord,
  updateMaintenanceRecord,
  listMaintenancenewRecord
} from "@/api/ToolingModule/maintenanceRecord";
import {useRoute} from "vue-router";

const { proxy } = getCurrentInstance();

const maintenanceRecordList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const route = useRoute();

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    createBy: null,
    createTime: null
  },
  rules: {
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询维修记录列表 */
function getList() {
  const toolnumber = route.query.Number;
  // console.log('=====' ,toolnumber);
  loading.value = true;
  listMaintenancenewRecord(queryParams.value ,toolnumber).then(response => {
    maintenanceRecordList.value = response.rows;
    total.value = Number(response.total);
    loading.value = false;
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
    maintenanceRecord: null,
    maintenanceFile: null,
    maintenanceTool: null,
    createBy: null,
    createTime: null
  };
  proxy.resetForm("maintenanceRecordRef");
}
/** 文件下载 */
function downloadFile(filePath) {
  const paths = filePath.split(',').map(path => path.trim());
  paths.forEach(path => {
    proxy.$download.resource(path);
  });
}
//预览文件
function previewFile(fileUrl) {
  const baseUrl = import.meta.env.VITE_APP_BASE_API; // 或者你可以直接赋值为基础文件路径
  const fullUrl = `${baseUrl}${fileUrl}`; // 拼接文件的完整 URL 地址
  // console.log('处理中');
  window.open(fullUrl, '_blank');
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
  title.value = "添加维修记录";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getMaintenanceRecord(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改维修记录";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["maintenanceRecordRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateMaintenanceRecord(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        form.value.maintenanceTool = route.query.Number ;
        addMaintenanceRecord(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除维修记录编号为"' + _ids + '"的数据项？').then(function() {
    return delMaintenanceRecord(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('ToolingModule/maintenanceRecord/export', {
    ...queryParams.value
  }, `maintenanceRecord_${new Date().getTime()}.xlsx`)
}

getList();
</script>
