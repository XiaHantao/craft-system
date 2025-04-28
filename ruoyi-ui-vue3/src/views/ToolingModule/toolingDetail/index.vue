<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="工具编号" prop="toolNumber">
        <el-input
          v-model="queryParams.toolNumber"
          placeholder="请输入工具编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="工具名称" prop="toolName">
        <el-input
          v-model="queryParams.toolName"
          placeholder="请输入工具名称"
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
          v-hasPermi="['ToolingModule:toolingDetail:add']"
        >新增</el-button>
      </el-col>
      <el-upload
          class="upload-demo"
          action=""
          :http-request="handleUpload"
          :show-file-list="true"
          :limit="1"
          accept=".xls,.xlsx"
      >
        <el-button type="primary">上传 Excel 文件</el-button>
      </el-upload>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ToolingModule:toolingDetail:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ToolingModule:toolingDetail:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['ToolingModule:toolingDetail:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="toolingDetailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="id" />-->
<!--      <el-table-column label="序号" align="center" prop="serialNumber" />-->
      <el-table-column label="序号" align="center">
        <template #default="{ $index }">
          <span>{{ ($index + 1) + (queryParams.pageNum - 1) * queryParams.pageSize }}</span> <!-- 根据当前页计算序号 -->
        </template>
      </el-table-column>
      <el-table-column label="工具编号" align="center" prop="toolNumber" />
      <el-table-column label="工具名称" align="center" prop="toolName" />
      <el-table-column label="合计数量" align="center" prop="totalQuantity" />
      <el-table-column label="材质" align="center" prop="textureOfMaterial" />
      <el-table-column label="下料尺寸" align="center" prop="cuttingSize" />
      <el-table-column label="总重量" align="center" prop="totalWeight" />
      <el-table-column label="原材料物料号" align="center" prop="rawMaterialNumber" />
      <el-table-column label="下料" align="center" prop="materialCutting" />
      <el-table-column label="金工" align="center" prop="metalworking" />
      <el-table-column label="组焊" align="center" prop="assemblyWelding" />
      <el-table-column label="涂装" align="center" prop="painting" />
      <el-table-column label="装配" align="center" prop="assembling" />
      <el-table-column label="备注" align="center" prop="remarks" />
<!--      <el-table-column label="车型id" align="center" prop="modelId" />-->
<!--      <el-table-column label="是否为共用件" align="center" prop="sharedComponents" />-->
      <el-table-column label="工装图纸" align="center" prop="toolingDrawings">
        <template #default="{ row }">
            <span v-if="row.toolingDrawings">
              <!-- 如果有文件地址，显示预览按钮 -->
              <el-button type="text" @click="previewFile(row.toolingDrawings)">预览</el-button>
            </span>
          <span v-else>
            <!-- 如果没有文件地址，显示“无图纸” -->
            无图纸
          </span>
        </template>
      </el-table-column><!--      <el-table-column label="验证文件" align="center" prop="verifyFile" />-->
<!--      <el-table-column label="采购清单" align="center" prop="procurementList" />-->
<!--      <el-table-column label="验证结论" align="center" prop="verificationConclusion" />-->
      <el-table-column label="更换时间" align="center" prop="changeTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.changeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否为关键部件" align="center" prop="keyComponents" />
<!--      <el-table-column label="维修记录" align="center" prop="maintenanceRecord" />-->
<!--      <el-table-column label="模具所属" align="center" prop="moldOwnership" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['ToolingModule:toolingDetail:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['ToolingModule:toolingDetail:remove']">删除</el-button>
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

    <!-- 添加或修改工装详细对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="toolingDetailRef" :model="form" :rules="rules" label-width="110px">
<!--        <el-form-item label="序号" prop="serialNumber">-->
<!--          <el-input v-model="form.serialNumber" placeholder="请输入序号" />-->
<!--        </el-form-item>-->
        <el-form-item label="工具编号" prop="toolNumber">
          <el-input v-model="form.toolNumber" placeholder="请输入工具编号" />
        </el-form-item>
        <el-form-item label="工具名称" prop="toolName">
          <el-input v-model="form.toolName" placeholder="请输入工具名称" />
        </el-form-item>
        <el-form-item label="合计数量" prop="totalQuantity">
          <el-input v-model="form.totalQuantity" placeholder="请输入合计数量" />
        </el-form-item>
        <el-form-item label="材质" prop="textureOfMaterial">
          <el-input v-model="form.textureOfMaterial" placeholder="请输入材质" />
        </el-form-item>
        <el-form-item label="下料尺寸" prop="cuttingSize">
          <el-input v-model="form.cuttingSize" placeholder="请输入下料尺寸" />
        </el-form-item>
        <el-form-item label="总重量" prop="totalWeight">
          <el-input v-model="form.totalWeight" placeholder="请输入总重量" />
        </el-form-item>
        <el-form-item label="原材料物料号" prop="rawMaterialNumber">
          <el-input v-model="form.rawMaterialNumber" placeholder="请输入原材料物料号" />
        </el-form-item>
        <el-form-item label="下料" prop="materialCutting">
          <el-input v-model="form.materialCutting" placeholder="请输入下料" />
        </el-form-item>
        <el-form-item label="金工" prop="metalworking">
          <el-input v-model="form.metalworking" placeholder="请输入金工" />
        </el-form-item>
        <el-form-item label="组焊" prop="assemblyWelding">
          <el-input v-model="form.assemblyWelding" placeholder="请输入组焊" />
        </el-form-item>
        <el-form-item label="涂装" prop="painting">
          <el-input v-model="form.painting" placeholder="请输入涂装" />
        </el-form-item>
        <el-form-item label="装配" prop="assembling">
          <el-input v-model="form.assembling" placeholder="请输入装配" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注" />
        </el-form-item>
<!--        <el-form-item label="车型" prop="modelId">-->
<!--          <el-input v-model="form.modelId" placeholder="请输入车型" />-->
<!--        </el-form-item>-->
        <el-form-item label="是否为共用件" prop="sharedComponents">
          <el-radio-group v-model="form.sharedComponents">
            <el-radio label="1">是</el-radio>
            <el-radio label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="工装图纸" prop="toolingDrawings">
          <file-upload v-model="form.toolingDrawings"/>
        </el-form-item>
<!--        <el-form-item label="验证文件" prop="verifyFile">-->
<!--          <file-upload v-model="form.verifyFile"/>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="采购清单" prop="procurementList">-->
<!--          <el-input v-model="form.procurementList" placeholder="请输入采购清单" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="验证结论" prop="verificationConclusion">-->
<!--          <el-input v-model="form.verificationConclusion" placeholder="请输入验证结论" />-->
<!--        </el-form-item>-->
        <el-form-item label="更换时间" prop="changeTime">
          <el-date-picker clearable
            v-model="form.changeTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择更换时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否为关键部件" prop="keyComponents">
          <el-radio-group v-model="form.keyComponents">
            <el-radio label="1">是</el-radio>
            <el-radio label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
<!--        <el-form-item label="是否关键部件" prop="keyComponents">-->
<!--          <el-input v-model="form.keyComponents" placeholder="请输入是否为关键部件" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="维修记录" prop="maintenanceRecord">-->
<!--          <el-input v-model="form.maintenanceRecord" placeholder="请输入维修记录" />-->
<!--        </el-form-item>-->
        <el-form-item label="模具所属" prop="moldOwnership">
          <el-input v-model="form.moldOwnership" placeholder="请输入模具所属" />
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

<script setup name="ToolingDetail">
import {
  listToolingDetail,
  getToolingDetail,
  delToolingDetail,
  addToolingDetail,
  updateToolingDetail, uploadFile,
} from "@/api/ToolingModule/toolingDetail";


const { proxy } = getCurrentInstance();

const toolingDetailList = ref([]);
const open = ref(false);
const showDialog = ref(false);
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
    serialNumber: null,
    toolNumber: null,
    toolName: null,
    totalQuantity: null,
    textureOfMaterial: null,
    cuttingSize: null,
    totalWeight: null,
    rawMaterialNumber: null,
    materialCutting: null,
    metalworking: null,
    assemblyWelding: null,
    painting: null,
    assembling: null,
    remarks: null,
    modelId: null,
    sharedComponents: null,
    toolingDrawings: null,
    verifyFile: null,
    procurementList: null,
    verificationConclusion: null,
    changeTime: null,
    keyComponents: null,
    maintenanceRecord: null,
    moldOwnership: null
  },
  rules: {
    toolNumber: [
      { required: true, message: "编号不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);


//预览文件
function previewFile(fileUrl) {
  const baseUrl = import.meta.env.VITE_APP_BASE_API; // 或者你可以直接赋值为基础文件路径
  const fullUrl = `${baseUrl}${fileUrl}`; // 拼接文件的完整 URL 地址
  console.log('处理中');
  window.open(fullUrl, '_blank');
}


/** 查询工装详细列表 */
function getList() {
  loading.value = true;
  listToolingDetail(queryParams.value).then(response => {
    toolingDetailList.value = response.rows;
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
    serialNumber: null,
    toolNumber: null,
    toolName: null,
    totalQuantity: null,
    textureOfMaterial: null,
    cuttingSize: null,
    totalWeight: null,
    rawMaterialNumber: null,
    materialCutting: null,
    metalworking: null,
    assemblyWelding: null,
    painting: null,
    assembling: null,
    remarks: null,
    modelId: null,
    sharedComponents: null,
    toolingDrawings: null,
    verifyFile: null,
    procurementList: null,
    verificationConclusion: null,
    changeTime: null,
    keyComponents: null,
    maintenanceRecord: null,
    moldOwnership: null
  };
  proxy.resetForm("toolingDetailRef");
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
  title.value = "添加工装详细";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getToolingDetail(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改工装详细";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["toolingDetailRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateToolingDetail(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addToolingDetail(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除工装详细编号为"' + _ids + '"的数据项？').then(function() {
    return delToolingDetail(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('ToolingModule/toolingDetail/export', {
    ...queryParams.value
  }, `toolingDetail_${new Date().getTime()}.xlsx`)
}

const handleUpload = async (file) => {
  try {
    const response = await uploadFile(file.file);
    // console.log("Response:", response); // 查看响应数据
    // console.log("Response:", response.code); // 查看响应数据
    if (response.code === 200) {
      proxy.$modal.msgSuccess(response.data.msg || "上传成功");
    } else {
      proxy.$modal.msgError(response.data.msg || "文件导入失败");
    }
  } catch (error) {
  }
};



getList();
</script>
