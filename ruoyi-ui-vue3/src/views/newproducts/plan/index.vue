<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="128px">
      <el-form-item label="技术科确认结果" prop="technicalcheck">
        <el-select
           v-model="queryParams.technicalcheck"
          placeholder="请选择"
          clearable
          @change="handleQuery"
        >
          <el-option label="通过" value="通过" />
          <el-option label="拒绝" value="拒绝" />
          <el-option label="待确认" value="待确认" />
        </el-select>     
      </el-form-item>
      <el-form-item label="质量科确认结果" prop="qulitycheck">
        <el-select
           v-model="queryParams.qualitycheck"
          placeholder="请选择"
          clearable
          @change="handleQuery"
        >
          <el-option label="通过" value="通过" />
          <el-option label="拒绝" value="拒绝" />
          <el-option label="待确认" value="待确认" />
        </el-select>     
      </el-form-item>
      <el-form-item label="安环科确认结果" prop="securitycheck">
        <el-select
           v-model="queryParams.securitycheck"
          placeholder="请选择"
          clearable
          @change="handleQuery"
        >
          <el-option label="通过" value="通过" />
          <el-option label="拒绝" value="拒绝" />
          <el-option label="待确认" value="待确认" />
        </el-select>     
      </el-form-item>
      <el-form-item label="新产品计划名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入新产品计划名称"
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
          v-hasPermi="['newproducts:plan:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['newproducts:plan:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['newproducts:plan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['newproducts:plan:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange" height="500px">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column v-if="false" label="id" align="center" prop="id" />
      <el-table-column label="新产品计划名称" align="center" prop="name" />
      <el-table-column label="生产计划" align="center" prop="planfile" >
        <template v-slot:default="scope">
          <el-button v-if="scope.row.planfile" icon="Download" @click="downloadFiles(scope.row)"></el-button>
        </template> 
      </el-table-column>  
      <el-table-column label="技术科确认" align="center" prop="technicalcheck" >
        <template #default="scope">
          <el-tag :type="scope.row.technicalcheck === '通过' ? 'success' : 'danger'" >
            {{ scope.row.technicalcheck }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="技术科备注" align="center" prop="technicalremark" />
      <el-table-column label="质量科确认" align="center" prop="qualitycheck" >
        <template #default="scope">
          <el-tag :type="scope.row.qualitycheck === '通过' ? 'success' : 'danger'" >
            {{ scope.row.qualitycheck }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="质量科备注" align="center" prop="qualityremark" />
      <el-table-column label="安环科确认" align="center" prop="securitycheck" >
        <template #default="scope">
          <el-tag :type="scope.row.securitycheck === '通过' ? 'success' : 'danger'" >
            {{ scope.row.securitycheck }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="安环科备注" align="center" prop="securityremark" />
<!--       <el-table-column label="" align="center" prop="time" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.time, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="" align="center" prop="testa" />
      <el-table-column label="" align="center" prop="testb" />
      <el-table-column label="" align="center" prop="testc" />
      <el-table-column label="" align="center" prop="testd" />
      <el-table-column label="" align="center" prop="teste" />
      <el-table-column label="" align="center" prop="testf" />
      <el-table-column label="" align="center" prop="testg" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right"  width="200">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['newproducts:plan:edit']" 
          v-if="scope.row.technicalcheck != '通过' || scope.row.qualitycheck != '通过' || scope.row.securitycheck !='通过'">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['newproducts:plan:remove']">删除</el-button>
          <el-button link type="primary" icon="Check" @click="handleCheck(scope.row)" v-hasPermi="['newproducts:submit:edit']">确认</el-button>
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

    <!-- 添加或修改新产品生产计划对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-form ref="planRef" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="新产品计划名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入新产品计划名称" />
        </el-form-item>
        <el-form-item label="生产计划" prop="planfile">
          <file-upload v-model="form.planfile"/>
        </el-form-item>
<!--         <el-form-item label="" prop="time">
          <el-date-picker clearable
            v-model="form.time"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="" prop="testa">
          <el-input v-model="form.testa" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="testb">
          <el-input v-model="form.testb" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="testc">
          <el-input v-model="form.testc" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="testd">
          <file-upload v-model="form.testd"/>
        </el-form-item>
        <el-form-item label="" prop="teste">
          <file-upload v-model="form.teste"/>
        </el-form-item>
        <el-form-item label="" prop="testf">
          <el-input v-model="form.testf" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="" prop="testg">
          <el-input v-model="form.testg" placeholder="请输入" />
        </el-form-item> -->
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 确认对话框 -->
    <el-dialog :title="checkTitle" v-model="openCheckDialog" width="800px" append-to-body>
      <el-form ref="submitRef" :model="form" :rules="rules" label-width="150px">
        
        <div v-if="userdep =='老实人科技' || userdep =='技术科'">        
        <el-form-item label="技术科确认结果" prop="technicalcheck">
          <el-radio-group v-model="form.technicalcheck">
            <el-radio label="通过" />
            <el-radio label="拒绝" />
          </el-radio-group>
        </el-form-item>        
        <el-form-item label="技术科备注" prop="technicalremark">
          <el-input v-model="form.technicalremark" placeholder="请输入备注" />
        </el-form-item>
        </div>
        <div v-if="userdep !=='老实人科技' || userdep =='质量科'"> 
        <el-form-item label="质量科确认结果" prop="qualitycheck">
          <el-radio-group v-model="form.qualitycheck">
            <el-radio label="通过" />
            <el-radio label="拒绝" />
          </el-radio-group>
        </el-form-item>        
        <el-form-item label="质量科备注" prop="qualityremark">
          <el-input v-model="form.qualityremark" placeholder="请输入备注" />
        </el-form-item>
        </div>
       <div v-if="userdep !=='老实人科技' || userdep =='安环科'"> 
        <el-form-item label="安环科确认结果" prop="securitycheck">          
          <el-radio-group v-model="form.securitycheck">
            <el-radio label="通过" />
            <el-radio label="拒绝" />
          </el-radio-group>
        </el-form-item>        
        <el-form-item label="安环科备注" prop="securityremark">
          <el-input v-model="form.securityremark" placeholder="请输入备注" />
        </el-form-item>
        </div>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitCheckForm">确 定</el-button>
          <el-button @click="cancelCheck">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Plan">
import { listPlan, getPlan, delPlan, addPlan, updatePlan,getLatestRecord02 } from "@/api/newproducts/plan";
import { getUserProfile, listUser } from "@/api/system/user";
import {addSysMessageNotification} from "@/api/system/sysMessageNotification";
import {listDept} from "@/api/system/dept";
//import { id } from "element-plus/es/locale";

const { proxy } = getCurrentInstance();

const planList = ref([]);
const open = ref(false);
const openCheckDialog = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const checkTitle = ref("");
const userdep = ref({}); //  初始化 userdep用户部门根据其值显示不同确认框
const deptList = ref([]);  // 部门列表
const userList = ref([]);  // 用户列表
const usernickName = ref(""); // 用户昵称

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
  },
  rules: {
    name: [
      { required: true, message: "新产品计划名称不能为空", trigger: "blur" }
    ],
    technicalcheck: [
        { required: true, message: '请选择技术科确认结果', trigger: 'change' }
      ],
      qualitycheck: [
        { required: true, message: '请选择质量科确认结果', trigger: 'change' }
      ],
      securitycheck: [
        { required: true, message: '请选择安环科确认结果', trigger: 'change' }
      ]    
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询新产品生产计划列表 */
function getList() {
  loading.value = true;
  listPlan(queryParams.value).then(response => {
    planList.value = response.rows;
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
    name: null,
    planfile: null,
    technicalcheck: null,
    technicalremark: null,
    qualitycheck: null,
    qualityremark: null,
    securitycheck: null,
    securityremark: null,
/*     time: null,
    testa: null,
    testb: null,
    testc: null,
    testd: null,
    teste: null,
    testf: null,
    testg: null */
  };
  proxy.resetForm("planRef");
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
  title.value = "添加新产品生产计划";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getPlan(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改新产品生产计划";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["planRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
          form.value.technicalcheck ="待确认" ;
          form.value.qualitycheck ="待确认" ;
          form.value.securitycheck ="待确认" ;
        updatePlan(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
 
      // 修改后待确认通知
      listDept().then(response => {
        deptList.value = response.data;
        // 获取用户列表
        listUser().then(response => {
          userList.value = response.rows;
          // 定义目标部门名称
          const targetDepts = ["技术科", "质量科", "安环科","老实人科技"];
          // 过滤出目标部门的ID
          const targetDeptIds = deptList.value
            .filter(dept => targetDepts.includes(dept.deptName))
            .map(dept => dept.deptId);
          // 过滤出目标部门下的用户
          const targetUsers = userList.value
            .filter(user => targetDeptIds.includes(user.deptId));
          //获取当前用户昵称
          getUserProfile().then(response => {
          usernickName.value = response.data.nickName;
          // 对目标用户执行通知函数
          targetUsers.forEach(users => {
            addSysMessageNotification({
              noticeTitle: "新产品生产计划通知",
              noticeContent: "有一条新产品生产计划需要确认，请及时处理。",
              createdBy: usernickName.value,
              createdTime: new Date(),
              executedBy: users.nickName,
              path: "/newproducts/plan",
              pathId: form.value.id,
              status: 0
            });
          });
          });
        });
      });

        });
      } else {
          form.value.technicalcheck ="待确认" ;
          form.value.qualitycheck ="待确认" ;
          form.value.securitycheck ="待确认" ;
        addPlan(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();

      // 新增后待确认通知
        getLatestRecord02("newproducts_plan").then(response1 => {
        listDept().then(response => {
          deptList.value = response.data;
        // 获取用户列表
        listUser().then(response => {
          userList.value = response.rows;
          // 定义目标部门名称
          const targetDepts = ["技术科", "质量科", "安环科","老实人科技"];
          // 过滤出目标部门的ID
          const targetDeptIds = deptList.value
            .filter(dept => targetDepts.includes(dept.deptName))
            .map(dept => dept.deptId);
          // 过滤出目标部门下的用户
          const targetUsers = userList.value
            .filter(user => targetDeptIds.includes(user.deptId));
          //获取当前用户昵称
          getUserProfile().then(response => {
          usernickName.value = response.data.nickName;
          // 对目标用户执行通知函数
          targetUsers.forEach(users => {
            addSysMessageNotification({
              noticeTitle: "新产品生产计划通知",
              noticeContent: "有一条新产品生产计划需要确认，请及时处理。",
              createdBy: usernickName.value,
              createdTime: new Date(),
              executedBy: users.nickName,
              path: "/newproducts/plan",
              pathId: response1.data.id,
              status: 0
            });
          });
          });
        });
      });
        });
          
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除？').then(function() {
    return delPlan(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('newproducts/plan/export', {
    ...queryParams.value
  }, `plan_${new Date().getTime()}.xlsx`)
}

/** 确认按钮操作 */
function handleCheck(row) {
  reset();
    getUserProfile().then(response => {
    userdep.value = response.data.dept.deptName;  //获得用户部门
    });
    openCheckDialog.value = true;
    checkTitle.value = "确认新产品生产计划提交";
    form.value.id = row.id;
  // });
}
/* 确认提交按钮 */
function submitCheckForm() {
  proxy.$refs["submitRef"].validate(valid => {
    if(valid){    
    if (form.value.id != null) {
      updatePlan(form.value).then(response => {
        console.log(form.value);
        proxy.$modal.msgSuccess("确认完成");
        openCheckDialog.value = false;
        getList();

     // 判断是否满足通知条件
    if (
      form.value.technicalcheck === "通过" &&
      form.value.qualitycheck === "通过" &&
      form.value.securitycheck === "通过"
    ) { 
      // 满足条件后执行逻辑
      listDept().then(response => {
        deptList.value = response.data;
        // 获取用户列表
        listUser().then(response => {
          userList.value = response.rows;
          // 定义目标部门名称
          const targetDepts = ["生产科", "市场科", "供应科","老实人科技"];
          // 过滤出目标部门的ID
          const targetDeptIds = deptList.value
            .filter(dept => targetDepts.includes(dept.deptName))
            .map(dept => dept.deptId);
          // 过滤出目标部门下的用户
          const targetUsers = userList.value
            .filter(user => targetDeptIds.includes(user.deptId));
          //获取当前用户昵称
          getUserProfile().then(response => {
          usernickName.value = response.data.nickName;
          // 对目标用户执行通知函数
          targetUsers.forEach(users => {
            addSysMessageNotification({
              noticeTitle: "新产品生产计划通知",
              noticeContent: "有一条新产品生产计划可以生产，请及时处理。",
              createdBy: usernickName.value,
              createdTime: new Date(),
              executedBy: users.nickName,
              path: "/newproducts/plan",
              pathId: form.value.id,
              status: 0
            });
          });
          });
        });
      });
    }

      });
    } 
    }
  });
  
}
// 取消确认对话框
function cancelCheck() {
  openCheckDialog.value = false;
  reset();
}

/** 多文件下载 */
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
// 监听试制记录按钮路由，实时跳转
watch(
  () => proxy.$route.query,  
  (newQuery) => {
    // 更新查询参数
    queryParams.value.id = newQuery.id;
    getList(); // 重新查询数据
  },
  { immediate: true } // 立即执行一次
);

getList();
</script>
