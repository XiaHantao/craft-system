<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="制造商" prop="manufacturer">
        <el-input
          v-model="queryParams.manufacturer"
          placeholder="请输入制造商"
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
          v-hasPermi="['marketanalysis:standardtwo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['marketanalysis:standardtwo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['marketanalysis:standardtwo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['marketanalysis:standardtwo:export']"
        >导出</el-button>
      </el-col>
       <el-col :span="1.5">
        <el-button type="info" plain icon="Upload" @click="handleImport">导入</el-button>
      </el-col>
      <input ref="importRef" type="file" hidden accept=".xlsx, .xls" @change="handleFileChange" />
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="standardtwoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <el-table-column label="制造商" align="center" prop="manufacturer" />
      <el-table-column label="聚氨酯轮胎" align="center" prop="polyurethaneTires" />
      <el-table-column label="基本型5米起升高度" align="center" prop="fiveMeterLiftingHeight" />
      <el-table-column label="其它型及其它起升高度门架" align="center" prop="liftingHeightGantry" />
      <el-table-column label="标准系列货叉" align="center" prop="standardSeriesFork" />
      <el-table-column label="非标系列货叉" align="center" prop="nonStandardSeriesFork" />
      <el-table-column label="标准车身颜色（蓝灰色+合力红）" align="center" prop="standardBodyColor" />
      <el-table-column label="其它车身颜色（客户定制）" align="center" prop="customizedBodyColor" />
      <el-table-column label="属具未到位限速" align="center" prop="accessoriesAndSpeedLimit" />
      <el-table-column label="转弯限速" align="center" prop="turningSpeedLimit" />
      <el-table-column label="灭火器" align="center" prop="fireExtinguisher" />
      <el-table-column label="倒车蜂鸣器" align="center" prop="reverseBuzzer" />
      <el-table-column label="倒车语音喇叭" align="center" prop="reverseVoiceHorn" />
      <el-table-column label="两侧后视镜" align="center" prop="twoSideRearviewMirrors" />
      <el-table-column label="逃生包" align="center" prop="escapeBag" />
      <el-table-column label="后轮制动" align="center" prop="rearWheelBraking" />
      <el-table-column label="前轮制动" align="center" prop="frontWheelBrake" />
      <el-table-column label="导向轮" align="center" prop="guideWheel" />
      <el-table-column label="监控（货叉、倒车影像）" align="center" prop="monitor" />
      <el-table-column label="磁导航" align="center" prop="magneticNavigation" />
      <el-table-column label="站驾软包座椅" align="center" prop="stationDrivingSoftPackSeat" />
      <el-table-column label="座驾式座椅" align="center" prop="carStyleSeats" />
      <el-table-column label="电风扇" align="center" prop="electricFan" />
      <el-table-column label="前挡风玻璃" align="center" prop="windscreen" />
      <el-table-column label="后挡风玻璃" align="center" prop="rearWindshield" />
      <el-table-column label="全车LED灯" align="center" prop="fullCarLedLights" />
      <el-table-column label="阅读灯" align="center" prop="readingLamp" />
      <el-table-column label="配装LED闪光警示灯（装配式）" align="center" prop="ledFlashingWarningLight" />
      <el-table-column label="公制螺纹" align="center" prop="metric" />
      <el-table-column label="国标电源接插件" align="center" prop="nationalStandardPowerConnector" />
      <el-table-column label="中文标识" align="center" prop="chineseIdentification" />
      <el-table-column label="英文标识" align="center" prop="englishLogo" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['marketanalysis:standardtwo:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['marketanalysis:standardtwo:remove']">删除</el-button>
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

    <!-- 添加或修改II类车/III类车标准配置对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="standardtwoRef" :model="form" :rules="rules" label-width="140px">
        <el-form-item label="制造商" prop="manufacturer">
          <el-input v-model="form.manufacturer" placeholder="请输入制造商" />
        </el-form-item>
        <el-form-item label="聚氨酯轮胎" prop="polyurethaneTires">
          <el-select v-model="form.polyurethaneTires" placeholder="请选择聚氨酯轮胎" >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>    
        </el-form-item>
        <el-form-item label="基本型5米起升高度" prop="fiveMeterLiftingHeight">
          <el-select v-model="form.fiveMeterLiftingHeight" placeholder="请选择基本型5米起升高度">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="其它型起升高度门架" prop="liftingHeightGantry">
          <el-select v-model="form.liftingHeightGantry" placeholder="请选择其它型及其它起升高度门架">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>       
        </el-form-item>
        <el-form-item label="标准系列货叉" prop="standardSeriesFork">
          <el-select v-model="form.standardSeriesFork" placeholder="请选择标准系列货叉">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="非标系列货叉" prop="nonStandardSeriesFork">
          <el-select v-model="form.nonStandardSeriesFork" placeholder="请选择非标系列货叉">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="标准车身颜色" prop="standardBodyColor">
          <el-select v-model="form.standardBodyColor" placeholder="请选择标准车身颜色">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="其它车身颜色" prop="customizedBodyColor">
          <el-select v-model="form.customizedBodyColor" placeholder="请选择其它车身颜色">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="属具未到位限速" prop="accessoriesAndSpeedLimit">
          <el-select v-model="form.accessoriesAndSpeedLimit" placeholder="请选择属具未到位限速">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="转弯限速" prop="turningSpeedLimit">
          <el-select v-model="form.turningSpeedLimit" placeholder="请选择转弯限速">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="灭火器" prop="fireExtinguisher">
          <el-select v-model="form.fireExtinguisher" placeholder="请选择灭火器">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="倒车蜂鸣器" prop="reverseBuzzer">
          <el-select v-model="form.reverseBuzzer" placeholder="请选择倒车蜂鸣器">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="倒车语音喇叭" prop="reverseVoiceHorn">
          <el-select v-model="form.reverseVoiceHorn" placeholder="请选择倒车语音喇叭">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="两侧后视镜" prop="twoSideRearviewMirrors">
          <el-select v-model="form.twoSideRearviewMirrors" placeholder="请选择两侧后视镜">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="逃生包" prop="escapeBag">
          <el-select v-model="form.escapeBag" placeholder="请选择逃生包">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="后轮制动" prop="rearWheelBraking">
          <el-select v-model="form.rearWheelBraking" placeholder="请选择后轮制动">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="前轮制动" prop="frontWheelBrake">
          <el-select v-model="form.frontWheelBrake" placeholder="请选择前轮制动">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="导向轮" prop="guideWheel">
          <el-select v-model="form.guideWheel" placeholder="请选择导向轮">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="监控（货叉、倒车影像）" prop="monitor">
          <el-select v-model="form.monitor" placeholder="请选择监控（货叉、倒车影像）">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="磁导航" prop="magneticNavigation">
          <el-select v-model="form.magneticNavigation" placeholder="请选择磁导航">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="站驾软包座椅" prop="stationDrivingSoftPackSeat">
          <el-select v-model="form.stationDrivingSoftPackSeat" placeholder="请选择站驾软包座椅">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="座驾式座椅" prop="carStyleSeats">
          <el-select v-model="form.carStyleSeats" placeholder="请选择座驾式座椅">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="电风扇" prop="electricFan">
          <el-select v-model="form.electricFan" placeholder="请选择电风扇">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="前挡风玻璃" prop="windscreen">
          <el-select v-model="form.windscreen" placeholder="请选择前挡风玻璃">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="后挡风玻璃" prop="rearWindshield">
          <el-select v-model="form.rearWindshield" placeholder="请选择后挡风玻璃">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="全车LED灯" prop="fullCarLedLights">
          <el-select v-model="form.fullCarLedLights" placeholder="请选择全车LED灯">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="阅读灯" prop="readingLamp">
          <el-select v-model="form.readingLamp" placeholder="请选择阅读灯">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="LED闪光警示灯" prop="ledFlashingWarningLight">
          <el-select v-model="form.ledFlashingWarningLight" placeholder="请选择LED闪光警示灯">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="公制螺纹" prop="metric">
          <el-select v-model="form.metric" placeholder="请选择公制螺纹">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="国标电源接插件" prop="nationalStandardPowerConnector">
          <el-select v-model="form.nationalStandardPowerConnector" placeholder="请选择国标电源接插件">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="中文标识" prop="chineseIdentification">
          <el-select v-model="form.chineseIdentification" placeholder="请选择中文标识">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
        </el-form-item>
        <el-form-item label="英文标识" prop="englishLogo">
          <el-select v-model="form.englishLogo" placeholder="请选择英文标识">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>   
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

<script setup name="Standardtwo">
import { listStandardtwo, getStandardtwo, delStandardtwo, addStandardtwo, updateStandardtwo,importstandard_two,checkDataExists } from "@/api/marketanalysis/standard/standardtwo";
const importRef = ref(null);
const updateSupport = ref(false);

const handleImport = () => importRef.value.click();

const handleFileChange = async (e) => {
  const files = e.target.files;
  if (!files.length) return;

  try {
    loading.value = true;
    const res = await checkDataExists();
    const dataExists = res.data;
    if (dataExists) {
      proxy.$modal.confirm('检测到已有数据，是否覆盖？').then(() => {
        updateSupport.value = true;
        uploadFile(files[0]);
      }).catch(() => {
        updateSupport.value = false;
        uploadFile(files[0]);
      });
    } else {
      updateSupport.value = false;
      uploadFile(files[0]);
    }
  } finally {
    loading.value = false;
  }
};

const uploadFile = async (file) => {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('updateSupport', updateSupport.value);

  try {
    await importstandard_two(formData);
    proxy.$modal.msgSuccess("导入成功");
    getList();
  } catch (e) {
    proxy.$modal.msgError("导入失败");
  } finally {
    importRef.value.value = '';
  }
};
const { proxy } = getCurrentInstance();
// 添加选项数据
const options = ref([
  { value: '标配', label: '标配' },
  { value: '选配', label: '选配' },
  { value: '无', label: '无' }
]);

const standardtwoList = ref([]);
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
    manufacturer: null,
  },
  rules: {
    manufacturer: [
      { required: true, message: "制造商不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询II类车/III类车标准配置列表 */
function getList() {
  loading.value = true;
  listStandardtwo(queryParams.value).then(response => {
    standardtwoList.value = response.rows;
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
    manufacturer: null,
    polyurethaneTires: null,
    fiveMeterLiftingHeight: null,
    liftingHeightGantry: null,
    standardSeriesFork: null,
    nonStandardSeriesFork: null,
    standardBodyColor: null,
    customizedBodyColor: null,
    accessoriesAndSpeedLimit: null,
    turningSpeedLimit: null,
    fireExtinguisher: null,
    reverseBuzzer: null,
    reverseVoiceHorn: null,
    twoSideRearviewMirrors: null,
    escapeBag: null,
    rearWheelBraking: null,
    frontWheelBrake: null,
    guideWheel: null,
    monitor: null,
    magneticNavigation: null,
    stationDrivingSoftPackSeat: null,
    carStyleSeats: null,
    electricFan: null,
    windscreen: null,
    rearWindshield: null,
    fullCarLedLights: null,
    readingLamp: null,
    ledFlashingWarningLight: null,
    metric: null,
    nationalStandardPowerConnector: null,
    chineseIdentification: null,
    englishLogo: null
  };
  proxy.resetForm("standardtwoRef");
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
  title.value = "添加II类车/III类车标准配置";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getStandardtwo(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改II类车/III类车标准配置";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["standardtwoRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateStandardtwo(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addStandardtwo(form.value).then(response => {
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
  proxy.$modal.confirm('是否确认删除II类车/III类车标准配置编号为"' + _ids + '"的数据项？').then(function() {
    return delStandardtwo(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('marketanalysis/standardtwo/export', {
    ...queryParams.value
  }, `II类车/III类车标准配置表.xlsx`)
}

getList();
</script>
