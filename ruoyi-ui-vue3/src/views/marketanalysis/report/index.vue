<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="报告名称" prop="reportTitle">
        <el-input v-model="queryParams.reportTitle" placeholder="请输入报告名称" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="关联车型" prop="vehicleType">
        <el-input v-model="queryParams.vehicleType" placeholder="请输入关联车型" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="报告格式" prop="reportFormat">
        <el-input v-model="queryParams.reportFormat" placeholder="请输入报告格式" clearable @keyup.enter="handleQuery" />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['marketanalysis:report:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate"
          v-hasPermi="['marketanalysis:report:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['marketanalysis:report:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport"
          v-hasPermi="['marketanalysis:report:export']">导出</el-button>
      </el-col> -->
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="编号" align="center" prop="id" /> -->
      <el-table-column label="报告名称" align="center" prop="reportTitle" />
      <el-table-column label="关联车型" align="center" prop="vehicleType" />
      <el-table-column label="报告说明" align="center" prop="reportFormat" />
      <el-table-column label="试验报告" align="center" prop="file" >
      <template v-slot:default="scope">
          <el-button v-if="scope.row.file" icon="Download" @click="downloadFiles(scope.row.file)"></el-button>
        </template>
      </el-table-column> 
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['marketanalysis:report:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['marketanalysis:report:remove']">删除</el-button>
          <!-- <el-button size="mini" type="text" icon="Download"
            @click="$download.resource(scope.row.file, false)">下载报告</el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改试验报告对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="reportRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="报告名称" prop="reportTitle">
          <el-input v-model="form.reportTitle" placeholder="请输入报告名称" />
        </el-form-item>
        <el-form-item label="关联车型" prop="vehicleType">
          <el-input v-model="form.vehicleType" placeholder="请输入关联车型" />
        </el-form-item>
        <el-form-item label="报告说明" prop="reportFormat">
          <el-input v-model="form.reportFormat" placeholder="请输入报告说明" />
        </el-form-item>
        <el-form-item label="试验报告" prop="file">
          <file-upload :limit="1"  v-model="form.file"/>
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

<script setup name="Report">
import { listReport, getReport, delReport, addReport, updateReport } from "@/api/marketanalysis/report/report";

const { proxy } = getCurrentInstance();

const reportList = ref([]);
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
    reportTitle: null,
    vehicleType: null,
    reportFormat: null,
  },
  rules: {
    reportTitle: [
      { required: true, message: "报告名称不能为空", trigger: "blur" }
    ],
    file: [
      { required: true, message: "试验报告文件不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询试验报告列表 */
function getList() {
  loading.value = true;
  listReport(queryParams.value).then(response => {
    reportList.value = response.rows;
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
    reportTitle: null,
    vehicleType: null,
    reportFormat: null,
    createTime: null,
    file: null,
    beiyongTwo: null,
    beiyongThree: null,
    beiyongFour: null,
    beiyongFive: null
  };
  proxy.resetForm("reportRef");
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
  title.value = "添加试验报告";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getReport(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改试验报告";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["reportRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateReport(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addReport(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除试验报告编号为"' + _ids + '"的数据项？').then(function() {
    return delReport(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/report/export', {
    ...queryParams.value
  }, `report_${new Date().getTime()}.xlsx`)
}

/** 多文件下载 */
const formatFileUrl = (url) => {
  const baseUrl = import.meta.env.VITE_APP_BASE_API;
  if (url.startsWith('http')) return url;
  return `${baseUrl}/${url}`;
};

function downloadFiles(urls) {
  // 统一处理输入为数组
  if (typeof urls === 'string') {
    urls = decodeURIComponent(urls).split(',').map(url => url.trim());
  }
  
  // 确保是数组格式
  if (!Array.isArray(urls)) {
    console.error('urls 必须是数组或逗号分隔的字符串');
    return;
  }

  // 遍历下载每个文件
  urls.forEach(url => {
    const formattedUrl = formatFileUrl(url);
    const link = document.createElement('a');
    link.href = formattedUrl;
    link.download = decodeURIComponent(url.split('/').pop());
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  });
}
getList();
</script>
