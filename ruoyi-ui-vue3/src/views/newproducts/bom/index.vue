<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="88px">
        <el-form-item label="项目编号" prop="projectCode">
          <el-select v-model="queryParams.projectCode" aria-placeholder="请选择项目编号！" clearable filterable
            @keyup.enter="handleQuery">
            <el-option v-for="model in projectCodeList" :key="model.projectCode" :label="model.projectCode"
              :value="model.projectCode"></el-option>
          </el-select>
        </el-form-item>
      <!-- <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="层" prop="layer">
        <el-input
          v-model="queryParams.layer"
          placeholder="请输入层级"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>      
      <el-form-item label="物料编号" prop="materialCode">
        <el-input v-model="queryParams.materialCode" placeholder="请输入物料编号" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="物料类型" prop="purchaseType">
        <el-select v-model="queryParams.purchaseType" placeholder="请选择采购类型" style="width: 200px" clearable>
          <el-option label="E" value="E"></el-option>
          <el-option label="F" value="F"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="到货情况" prop="arrivalStatus">
        <el-select v-model="queryParams.arrivalStatus" placeholder="请选择到货情况" style="width: 200px" clearable>
          <el-option label="已到货" value="已到货"></el-option>
          <el-option label="未到货" value="未到货"></el-option>
          <el-option label="已完成" value="已完成"></el-option>
          <el-option label="未完成" value="未完成"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="质检情况" prop="inspectionStatus">
        <el-select v-model="queryParams.inspectionStatus" placeholder="请选择质检情况" style="width: 200px" clearable>
          <el-option label="需要质检" value="需要质检"></el-option>
          <el-option label="无需质检" value="无需质检"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="质检结果" prop="inspectionResult">
        <el-select v-model="queryParams.inspectionResult" placeholder="请选择质检结果" style="width: 200px" clearable>
          <el-option label="合格" value="合格"></el-option>
          <el-option label="不合格" value="不合格"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="质检处理结果" prop="inspectionSolve">
        <el-select v-model="queryParams.inspectionSolve" placeholder="请选择质检处理结果" style="width: 250px" clearable>
          <el-option label="已处理" value="已处理"></el-option>
          <el-option label="未处理" value="未处理"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="领用记录" prop="extField2">
        <el-select v-model="queryParams.extField2" placeholder="请选择领用记录" style="width: 250px" clearable>
          <el-option label="已领用" value="已领用"></el-option>
          <el-option label="未领用" value="未领用"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd"
          v-hasPermi="['newproducts:bom:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="Sort" @click="toggleExpandAll">展开/折叠</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="Upload" @click="handleImport"
          v-hasPermi="['newproducts:bom:import']">导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="Export"
          v-hasPermi="['newproducts:bom:export']">导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-if="refreshTable" v-loading="loading" :data="bomList" row-key="id" :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}" height="600px">

      <el-table-column label="项目编号" prop="projectCode" />

      <!-- <el-table-column label="项目名称" align="center" prop="projectName" /> -->
      <el-table-column label="层" align="center" prop="layer" />
      <el-table-column label="物料编号" align="center" prop="materialCode" />
      <el-table-column label="物料描述" align="center" prop="materialDescription" />
      <el-table-column label="数量" align="center" prop="quantity" />
      <!-- <el-table-column label="采购类型" align="center" prop="purchaseType" /> -->
      <el-table-column label="采购类型" align="center" prop="purchaseType">
        <template #default="scope">
          <el-tag v-if="scope.row.purchaseType" :type="scope.row.purchaseType === 'E' ? 'default' : 'default'">
            {{ scope.row.purchaseType }}
          </el-tag>
        </template>
      </el-table-column>
     
      <el-table-column label="到货情况" align="center" prop="arrivalStatus">
        <template #default="scope">
          <el-tag v-if="scope.row.arrivalStatus" :type="scope.row.arrivalStatus === '完成'
            ? 'danger'
            : scope.row.arrivalStatus === '已完成'
              ? 'success'
              : scope.row.arrivalStatus === '已到货'
                ? 'success'
                : 'danger'">
            {{ scope.row.arrivalStatus }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="质检情况" align="center" prop="inspectionStatus">
        <template #default="scope">
          <el-tag v-if="scope.row.inspectionStatus"
            :type="scope.row.inspectionStatus === '无需质检' ? 'success' : 'danger'">
            {{ scope.row.inspectionStatus }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="质检结果" align="center" prop="inspectionResult">
        <template #default="scope">
          <el-tag v-if="scope.row.inspectionResult" :type="scope.row.inspectionResult === '合格' ? 'success' : 'danger'">
            {{ scope.row.inspectionResult }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="质检结果文件" align="center" prop="inspectionFile">
        <template v-slot:default="scope">
          <el-button v-if="scope.row.inspectionFile" icon="Download"
            @click="downloadFiles(scope.row.inspectionFile)"></el-button>
        </template>
      </el-table-column>

      <el-table-column label="质检处理结果" align="center" prop="inspectionSolve">
        <template #default="scope">
          <el-tag v-if="scope.row.inspectionSolve" :type="scope.row.inspectionSolve === '已处理' ? 'success' : 'danger'">
            {{ scope.row.inspectionSolve }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="质检处理备注" align="center" prop="inspectionRemarks" />
      <el-table-column label="领用记录" align="center" prop="extField2" >
        <template #default="scope">
          <el-tag v-if="scope.row.extField2" :type="scope.row.extField2 === '已领用' ? 'success' : 'danger'">
            {{ scope.row.extField2 }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="领用日期" align="center" prop="receivingDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.receivingDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="问题记录" align="center" prop="issueRecord" />
      <!-- <el-table-column label="父级ID" align="center" prop="parentId" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right"  width="200">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['newproducts:bom:edit']">修改</el-button>
          <el-button link type="primary" icon="Plus" @click="handleAdd(scope.row)"
            v-hasPermi="['newproducts:bom:add']">新增</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
            v-hasPermi="['newproducts:bom:remove']">删除</el-button>
          <el-button link type="primary" icon="Edit" @click="handleInspectionStatus(scope.row)"
            v-hasPermi="['newproducts:bom:inspectionstatus']">质检情况</el-button>
          <el-button link type="primary" icon="Edit" @click="handleInspection(scope.row)"
            v-hasPermi="['newproducts:bom:inspection']">质检</el-button>
          <el-button link type="primary" icon="Edit" @click="handleCollection(scope.row)"
            v-hasPermi="['newproducts:bom:collection']">领用</el-button>
          <el-button link type="primary" icon="Edit" @click="handleIssue(scope.row)"
            v-hasPermi="['newproducts:bom:issue']">问题记录</el-button>      
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改新产品BOM对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="bomRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="项目编号" prop="projectCode">
          <el-select v-model="form.projectCode" aria-placeholder="请选择项目编号！" clearable filterable
            @keyup.enter="handleQuery">
            <el-option v-for="model in projectCodeList" :key="model.projectCode" :label="model.projectCode"
              :value="model.projectCode"></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item> -->
        <el-form-item label="上级物料编号" prop="parentId">
          <el-tree-select v-model="form.parentId" :data="bomOptions"
            :props="{ value: 'id', label: 'materialCode', children: 'children' }" value-key="materialCode" placeholder="请选择上级物料编号"
            check-strictly />
        </el-form-item>
        <el-form-item label="层" prop="layer">
          <el-input v-model="form.layer" placeholder="请输入层" />
        </el-form-item>
        <el-form-item label="物料编号" prop="materialCode">
          <el-input v-model="form.materialCode" placeholder="请输入物料编号" />
        </el-form-item>
        <el-form-item label="物料描述" prop="materialDescription">
          <el-input v-model="form.materialDescription" placeholder="请输入物料描述" />
        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="物料类型" prop="purchaseType">
          <el-radio-group v-model="form.purchaseType" placeholder="请选择采购类型" clearable filterable>
            <el-radio label="E" >自制</el-radio>
            <el-radio label="F" >采购</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="到货情况" prop="arrivalStatus">
          <el-select v-model="form.arrivalStatus" placeholder="请选择到货情况" clearable filterable>
            <el-option label="已到货" value="已到货"></el-option>
            <el-option label="未到货" value="未到货"></el-option>
            <el-option label="已完成" value="已完成"></el-option>
            <el-option label="未完成" value="未完成"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="质检情况" prop="inspectionStatus">
            <el-radio-group v-model="form.inspectionStatus">
            <el-radio label="需要质检" />
            <el-radio label="无需质检" />
            </el-radio-group>
        </el-form-item>
<!--         <el-form-item label="质检结果" prop="inspectionResult">
          <el-radio-group v-model="form.inspectionResult" placeholder="请选择质检结果" clearable filterable>
            <el-radio label="合格" value="合格"></el-radio>
            <el-radio label="不合格" value="不合格"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="质检结果文件" prop="inspectionFile">
          <file-upload v-model="form.inspectionFile" />
        </el-form-item>

        <el-form-item label="质检处理结果" prop="inspectionSolve">
          <el-radio-group v-model="form.inspectionSolve" placeholder="请选择质检处理结果" clearable filterable>
            <el-radio label="已处理" value="已处理"></el-radio>
            <el-radio label="未处理" value="未处理"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="质检处理备注" prop="inspectionRemarks">
          <el-input v-model="form.inspectionRemarks" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="领用记录" prop="extField2">
          <el-radio-group v-model="form.extField2" placeholder="请选择质检处理结果" clearable filterable>
            <el-radio label="已领用" value="已领用"></el-radio>
            <el-radio label="未领用" value="未领用"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="领用日期" prop="receivingDate">
          <el-date-picker clearable v-model="form.receivingDate" type="date" value-format="YYYY-MM-DD"
            placeholder="选择领用日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="问题记录" prop="issueRecord">
          <el-input v-model="form.issueRecord" type="textarea" placeholder="请输入内容" />
        </el-form-item> -->
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 导入对话框 -->
    <el-dialog title="导入新产品BOM" v-model="importDialogVisible" width="500px" append-to-body>
      <el-form ref="importFormRef" :model="importForm" :rules="importRules" label-width="100px">
        <el-form-item label="项目编号" prop="projectCode">
          <el-select v-model="importForm.projectCode" placeholder="请选择项目编号" clearable @change="handleProjectSelect">
            <el-option v-for="item in projectCodeList" :key="item.projectCode" :label="item.projectCode"
              :value="item.projectCode" />
          </el-select>
        </el-form-item>

        <!-- <el-form-item label="项目名称" prop="projectName">
        <el-input 
          v-model="importForm.projectName" 
          placeholder="请输入项目名称"
          :disabled="!!importForm.projectCode"
        />
      </el-form-item> -->

        <el-form-item label="Excel文件" prop="file">
          <el-upload ref="uploadRef" action="" :auto-upload="false" :on-change="handleFileChange" :show-file-list="true"
            :file-list="fileList" accept=".xlsx, .xls">
            <el-button type="primary">选择文件</el-button>
          </el-upload>
        </el-form-item>
      </el-form>

      <template #footer>
        <div class="dialog-footer">
          <el-button @click="importDialogVisible = false">取消</el-button>
          <el-button type="primary" :loading="importLoading" @click="submitImport">开始导入</el-button>
        </div>
      </template>
    </el-dialog>

<!-- 导出对话框 -->
    <el-dialog :title="title" v-model="openExport" width="800px" append-to-body>
      <el-form ref="bomRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="项目编号" prop="projectCode">
          <el-select v-model="importForm.projectCode" placeholder="请选择项目编号" clearable @change="handleProjectSelect">
            <el-option v-for="item in projectCodeList" :key="item.projectCode" :label="item.projectCode"
              :value="item.projectCode" />
          </el-select>
        </el-form-item>      
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="exportAll">导出</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

<!-- 质检情况对话框 -->
    <el-dialog :title="title" v-model="openInspectionStatus" width="800px" append-to-body>
      <el-form ref="bomRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="质检情况" prop="inspectionStatus">
            <el-radio-group v-model="form.inspectionStatus">
            <el-radio label="需要质检" />
            <el-radio label="无需质检" />
            </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 质检对话框 -->
    <el-dialog :title="title" v-model="openInspection" width="800px" append-to-body>
      <el-form ref="bomRef" :model="form" :rules="rules" label-width="100px">
        <!-- <el-form-item label="质检情况" prop="inspectionStatus">
            <el-radio-group v-model="form.inspectionStatus">
            <el-radio label="需要质检" />
            <el-radio label="无需质检" />
            </el-radio-group>
        </el-form-item> -->
        <el-form-item label="质检结果" prop="inspectionResult">
          <el-radio-group v-model="form.inspectionResult" placeholder="请选择质检结果" clearable filterable>
            <el-radio label="合格" value="合格"></el-radio>
            <el-radio label="不合格" value="不合格"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="质检结果文件" prop="inspectionFile">
          <file-upload v-model="form.inspectionFile" />
        </el-form-item>
        <el-form-item label="质检处理结果" prop="inspectionSolve">
          <el-radio-group v-model="form.inspectionSolve" placeholder="请选择质检处理结果" clearable filterable>
            <el-radio label="已处理" value="已处理"></el-radio>
            <el-radio label="未处理" value="未处理"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="质检处理备注" prop="inspectionRemarks">
          <el-input v-model="form.inspectionRemarks" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

<!-- 领用对话框 -->
    <el-dialog :title="title" v-model="openCollection" width="800px" append-to-body>
      <el-form ref="bomRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="领用记录" prop="extField2">
          <el-radio-group v-model="form.extField2" placeholder="请选择质检处理结果" clearable filterable>
            <el-radio label="已领用" value="已领用"></el-radio>
            <el-radio label="未领用" value="未领用"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="领用日期" prop="receivingDate">
          <el-date-picker clearable v-model="form.receivingDate" type="date" value-format="YYYY-MM-DD"
            placeholder="选择领用日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

<!-- 问题记录对话框 -->
    <el-dialog :title="title" v-model="openIssue" width="800px" append-to-body>
      <el-form ref="bomRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="问题记录" prop="issueRecord">
          <el-input v-model="form.issueRecord" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 新增文件选择下载对话框 -->
<!--     <el-dialog v-model="downloadSelectVisible" title="选择下载文件" width="500px">
      <el-scrollbar height="300px">
        <el-checkbox-group v-model="selectedDownloadFiles" class="file-select-group">
          <el-checkbox v-for="(file, index) in downloadableFiles" :key="index" :label="file.fullUrl" class="file-item">
            <div class="file-info">
              <el-icon class="file-type-icon">
                <component :is="getFileIcon(file.fullUrl)" />
              </el-icon>
              <span class="file-name">{{ file.displayName }}</span>
            </div>
          </el-checkbox>
        </el-checkbox-group>
      </el-scrollbar>
      <template #footer>
        <el-button @click="downloadSelectVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmDownload">下载选中文件</el-button>
      </template>
    </el-dialog> -->
  </div>
</template>

<script setup name="Bom">
import { getCurrentInstance, ref, reactive, toRefs } from 'vue';
import { listBom, getBom, delBom, addBom, updateBom,importBom, checkProjectDataExists } from "@/api/newproducts/bom";
import { listCreate } from "@/api/newproducts/create";
//导出
import * as XLSX from "xlsx";
import { saveAs } from "file-saver";
// import {Loading} from "element-ui";
// import { mapActions } from 'vuex';

// 导入对话框相关变量
const importDialogVisible = ref(false);
const importLoading = ref(false);
const uploadRef = ref(null);
const fileList = ref([]);

// 导入表单
const importForm = reactive({
  projectCode: '',
  projectName: '',
  file: null
});

// 导入表单验证规则
const importRules = {
  projectCode: [
    { required: true, message: '请选择项目编号', trigger: 'blur' }
  ],
  projectName: [
    { required: true, message: '请输入项目名称', trigger: 'blur' }
  ],
  file: [
    { required: true, message: '请选择Excel文件', trigger: 'change' }
  ]
};

// 处理项目选择
const handleProjectSelect = (value) => {
  const selected = projectCodeList.value.find(item => item.projectCode === value);
  if (selected) {
    importForm.projectName = selected.projectName;
  }
};

// 处理导入按钮点击
const handleImport = () => {
  // 重置表单
  importForm.projectCode = '';
  importForm.projectName = '';
  importForm.file = null;
  fileList.value = [];
  
  // 打开对话框
  importDialogVisible.value = true;
};

// 处理文件选择
const handleFileChange = (file) => {
  importForm.file = file.raw;
};

// 提交导入
const submitImport = () => {
  proxy.$refs.importFormRef.validate(valid => {
    if (valid && importForm.file) {
      importLoading.value = true;
      
      // 创建FormData对象
      const formData = new FormData();
      formData.append('file', importForm.file);
      formData.append('projectCode', importForm.projectCode);
      formData.append('projectName', importForm.projectName);
      
      // 检查项目数据是否存在
      checkProjectDataExists({ projectCode: importForm.projectCode })
        .then(response => {
          if (response.data) {
            proxy.$modal.confirm('该项目已存在数据，导入将覆盖现有数据，是否继续？')
              .then(() => {
                performImport(formData);
              })
              .catch(() => {
                importLoading.value = false;
              });
          } else {
            performImport(formData);
          }
        })
        .catch(error => {
          proxy.$modal.msgError('检查数据失败: ' + error.message);
          importLoading.value = false;
        });
    }
  });
};

// 执行导入操作
const performImport = (formData) => {
  importBom(formData)
    .then(response => {
      proxy.$modal.msgSuccess(response.msg || '导入成功');
      importDialogVisible.value = false;
      getList(); // 刷新列表
    })
    .catch(error => {
      proxy.$modal.msgError('导入失败: ' + (error.message || '请检查文件格式'));
    })
    .finally(() => {
      importLoading.value = false;
    });
};
const { proxy } = getCurrentInstance();
const projectCodeList =ref([]);//项目编号列表
const bomList = ref([]);
const bomOptions = ref([]);
const open = ref(false);
const openExport =ref(false);//导出对话框
const openInspectionStatus = ref(false);//质检情况对话框
const openInspection =ref(false);//质检对话框
const openCollection = ref(false);//领用对话框
const openIssue = ref(false);//问题记录对话框
const loading = ref(true);
const showSearch = ref(true);
const title = ref("");
const isExpandAll = ref(false);
const refreshTable = ref(true);
// 新增下载相关状态
// const downloadSelectVisible = ref(false);
// const downloadableFiles = ref([]);
// const selectedDownloadFiles = ref([]);
const data = reactive({
  form: {},
  queryParams: {
    projectCode: null,
    projectName: null,
    materialCode: null,
  },
  rules: {
    projectCode: [
      { required: true, message: "项目编号不能为空", trigger: "blur" }
    ],
    materialCode: [
      { required: true, message: "物料编号不能为空", trigger: "blur" }
    ],
    layer: [
      { required: true, message: "层不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);
//查询项目编号列表
function getprojectCodeList () {
  listCreate().then(response => {
    projectCodeList.value = response.rows;
  });
}
/** 查询新产品BOM列表 */
function getList() {
  loading.value = true;
  listBom(queryParams.value).then(response => {
    bomList.value = proxy.handleTree(response.data, "id", "parentId");
    loading.value = false;
  });
}

/** 查询新产品BOM下拉树结构 */
function getTreeselect() {
  listBom().then(response => {
    bomOptions.value = [];
    const data = { id: 0, materialCode: '顶级节点', children: [] };
    data.children = proxy.handleTree(response.data, "id", "parentId");
    bomOptions.value.push(data);
  });
}
	
// 取消按钮
function cancel() {
  open.value = false;
  openInspectionStatus.value = false;
  openInspection.value = false;
  openCollection.value = false;
  openIssue.value = false;
  openExport.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    projectCode: null,
    projectName: null,
    layer: null,
    materialCode: null,
    materialDescription: null,
    quantity: null,
    purchaseType: null,
    arrivalStatus: null,
    inspectionStatus: null,
    inspectionResult: null,
    inspectionFile: null,
    inspectionRemarks: null,
    receivingDate: null,
    issueRecord: null,
    parentId: null,
    extField1: null,
    extField2: null,
    extField3: null
  };
  proxy.resetForm("bomRef");
}

/** 多文件下载 */
const formatFileUrl = (url) => {
  const baseUrl = import.meta.env.VITE_APP_BASE_API;
  if (url.startsWith('http')) return url;
  return `${baseUrl}/${url}`;
};
//文件下载
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

/** 搜索按钮操作 */
function handleQuery() {
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

/** 新增按钮操作 */
function handleAdd(row) {
  reset();
  getTreeselect();
  if (row != null && row.id) {
    form.value.parentId = row.id;
  } else {
    form.value.parentId = 0;
  }
  open.value = true;
  title.value = "添加新产品BOM";
}

/** 展开/折叠操作 */
function toggleExpandAll() {
  refreshTable.value = false;
  isExpandAll.value = !isExpandAll.value;
  nextTick(() => {
    refreshTable.value = true;
  });
}

/** 修改按钮操作 */
async function handleUpdate(row) {
  reset();
  await getTreeselect();
  if (row != null) {
    form.value.parentId = row.id;
  }
  getBom(row.id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改新产品BOM";
  });
}

//质检情况按钮操作
function handleInspectionStatus(row){
  reset();
  getBom(row.id).then(response => {
    form.value =response.data;
    openInspectionStatus.value = true;
    title.value = "是否需要质检";
  });
}

//质检按钮操作
function handleInspection(row){
  reset();
  getBom(row.id).then(response => {
    form.value = response.data;
    openInspection.value = true;
    title.value = "质检";
  });
}

//领用按钮操作
function handleCollection(row){
  reset();
  getBom(row.id).then(response => {
    form.value = response.data;
    openCollection.value = true;
    title.value = "领用";
  });
}

//问题记录按钮操作
function handleIssue(row){
  reset();
  getBom(row.id).then(response => {
    form.value = response.data;
    openIssue.value = true;
    title.value = "问题记录";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["bomRef"].validate(valid => {
    if (valid) {
      // 新增采购类型和到货情况的匹配验证
      const purchaseType = form.value.purchaseType;
      console.log("form.value===>",form.value)
      const arrivalStatus = form.value.arrivalStatus;
      
      // 验证逻辑：E类型对应完成状态，F类型对应到货状态
      if (purchaseType === 'E' && 
          (arrivalStatus === '已到货' || arrivalStatus === '未到货')) {
        proxy.$modal.msgError("物料类型为E时，到货情况应为'已完成'或'未完成'");
        return;
      }
      
      if (purchaseType === 'F' && 
          (arrivalStatus === '已完成' || arrivalStatus === '未完成')) {
        proxy.$modal.msgError("物料类型为F时，到货情况应为'已到货'或'未到货'");
        return;
      }

      // 原有提交逻辑保持不变
      if (form.value.id != null) {
        updateBom(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          openInspectionStatus.value = false;
          openInspection.value = false;
          openCollection.value = false;
          openIssue.value = false;
          getList();
        });
      } else {
        addBom(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          openInspectionStatus.value = false;
          openInspection.value = false;
          openCollection.value = false;
          openIssue.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  proxy.$modal.confirm('是否确认删除？').then(function() {
    return delBom(row.id);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}
/** 导出按钮操作（问题记录） */
/* function handleExport() {
  // 添加 issueRecord 参数，设置为 true 表示只导出有问题记录的数据
  proxy.download('newproducts/bom/export', {
    ...queryParams.value,
    issueRecord: true // 新增参数
  }, `问题记录${new Date().getTime()}.xlsx`)
} */

//导出按钮操作
/* function Export(){
  proxy.download('newproducts/bom/exportbom',{
    ...queryParams.value
  }, `新产品bom信息表${new Date().getTime()}.xlsx`)

// 打开对话框
//确定按钮跳转到exportAllAdd

} */
//导出对话框操作
function Export(row) {
    reset();
    openExport.value = true;
    title.value = "导出";
}

function exportAll(){
    const traverseTree = (nodes) => {
        let results = [];
        nodes.forEach(node => {
            if(node.projectCode === importForm.projectCode){
            results.push({
                层: node.layer,
                物料编号: node.materialCode,
                物料描述: node.materialDescription,
                数量: node.quantity,
                采购类型: node.purchaseType,
                到货情况: node.arrivalStatus,
                质检情况: node.inspectionStatus,
                质检结果: node.inspectionResult,
                // 质检结果文件: node.inspectionFile,
                质检结果处理: node.inspectionSolve,
                // 质检处理备注: node.inspectionRemarks,
                领用记录: node.extField2,
                领用日期: node.receivingDate,
                问题记录: node.issueRecord,
            });
            }
            // 递归处理子节点（若依框架默认子节点字段是 `children`）
            if (node.children && node.children.length > 0) {
                results = results.concat(traverseTree(node.children));
            }
        });
        return results;
    };
            // 获取所有节点数据
    const allData = traverseTree(bomList.value);
    try {
        // 直接使用数据生成Excel，不需要Promise.all
        const ws = XLSX.utils.json_to_sheet(allData);
        const wb = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, "BOM列表");

        const wbout = XLSX.write(wb, { bookType: "xlsx", type: "array" });

        const fileName = `新产品项目编号_${importForm.projectCode}_BOM数据_${new Date().toISOString().slice(0, 10)}.xlsx`;
        saveAs(
            new Blob([wbout], { type: "application/octet-stream" }),
            fileName
        );
    } catch (error) {
        console.error("导出失败:", error);
        // 可以在这里添加错误提示，比如使用Element UI的Message.error
        Message.error("导出失败：" + error.message);
    }
    openExport.value = false ;
}





getprojectCodeList();
getList();
</script>
