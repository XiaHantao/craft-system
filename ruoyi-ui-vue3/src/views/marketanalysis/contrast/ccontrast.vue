<template>
  <div class="app-container">
    <div class="compare-container">
      <!-- 第一行：三个对比框 -->
      <div class="compare-row">
        <div class="selector-item" v-for="n in 3" :key="n">
          <!-- 车型选择器 -->
          <el-select
            v-model="selectedVehicleTypes[n-1]"
            placeholder="请选择车型（相同车类）"
            @change="handleVehicleTypeChange(n)"
            style="margin-bottom: 10px"
            clearable
            filterable
          >
            <el-option
              v-for="item in vehicleTypeOptions[n-1]"
              :key="item"
              :label="item"
              :value="item"
            />
          </el-select>

          <!-- 制造商选择器 -->
          <el-select
            v-model="selectedManufacturers[n-1]"
            filterable
            remote
            :remote-method="query => searchManufacturers(n, query)"
            :loading="loadingManufacturer"
            :disabled="!selectedVehicleTypes[n-1]"
            :placeholder="selectedVehicleTypes[n-1] ? '请选择制造商' : '请先选择车型'"
            style="width: 100%"
            clearable
          >
            <el-option
              v-for="item in manufacturerOptions[n-1]"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </div>
      </div>
      
      <!-- 第二行：两个对比框 + 按钮 -->
      <div class="compare-row">
        <!-- 第四和第五个选择器 -->
        <div class="selector-item selector-item-shrink" v-for="n in 2" :key="n+3">
          <el-select
            v-model="selectedVehicleTypes[n+2]"
            placeholder="请选择车型（相同车类）"
            @change="handleVehicleTypeChange(n+3)"
            style="margin-bottom: 10px"
            clearable
            filterable
          >
            <el-option
              v-for="item in vehicleTypeOptions[n+2]"
              :key="item"
              :label="item"
              :value="item"
            />
          </el-select>

          <el-select
            v-model="selectedManufacturers[n+2]"
            filterable
            remote
            :remote-method="query => searchManufacturers(n+3, query)"
            :loading="loadingManufacturer"
            :disabled="!selectedVehicleTypes[n+2]"
            :placeholder="selectedVehicleTypes[n+2] ? '请选择制造商' : '请先选择车型'"
            style="width: 100%"
            clearable
          >
            <el-option
              v-for="item in manufacturerOptions[n+2]"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </div>
        
        <!-- 对比按钮 -->
        <div class="compare-button-container">
          <el-button 
            type="primary" 
            @click="handleCompare"
            :disabled="!canCompare"
            size="large"
            class="compact-button"
          >
            确定对比
          </el-button>
        </div>
      </div>
    </div>

    <!-- 对比表格 -->
    <el-table
      v-loading="tableLoading"
      :data="comparisonData"
      style="width: 100%; margin-top: 20px"
      v-if="showTable"
      border
    >
      <el-table-column prop="paramName" label="参数名称" width="200" fixed="left" />
      
      <!-- 动态生成五列 -->
      <el-table-column 
        v-for="(_, index) in 5" 
        :key="index"
        :label="'制造商' + String.fromCharCode(65 + index)"
      >
        <template #default="{ row }">
          <span :class="{ 'diff-cell': !row.allSame }">
            {{ row.values[index] || '-' }}
          </span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { listStandardone } from '@/api/marketanalysis/standard/standardone'
import { listStandardtwo } from '@/api/marketanalysis/standard/standardtwo'
import { listStandardfive } from '@/api/marketanalysis/standard/standardfive'

// 各车类参数配置保持不变
const vehicleParams = {
  1: [ // 一类车参数
    'manufacturer',
    'pneumaticTire',
    'solidTire',
    'singleTire',
    'dualTire',
    'twoPieceValve',
    'threeOrFourPieceValve',
    'threeMeterLiftingHeight',
    'liftingHeightGantry',
    'standardSeriesFork',
    'nonStandardSeriesForks',
    'standardBodyColor',
    'customizedBodyColor',
    'completeOpsFunctionality',
    'turningSpeedLimit',
    'overspeed',
    'reverseArmrestWithHorn',
    'seatBeltSwitch',
    'overloadOfMultiWayValve',
    'fireExtinguisher',
    'reverseBuzzer',
    'semiEnclosedSeat',
    'fullySuspendedSeat',
    'usbInterface',
    'electricFan',
    'turnTheSteeringWheel',
    'synchronousSteering',
    'standardRoofProtectionFrame',
    'nonStandardRoofProtectionFrame',
    'windscreen',
    'frontHeatresistantWindshield',
    'rearWindshield',
    'pcEnduranceBoardCeiling',
    'surfaceMountedCab',
    'singleCoolingAirConditioner',
    'dualPurposeAirConditioner',
    'warmAirBlower',
    'fullCarLedLights',
    'ledRearWorkLight',
    'blueLightBehindTheCar',
    'threeSidedBlueStripLight',
    'threeSidedRedStripLights',
    'ledFlashingWarningLight',
    'ledRotatingWarningLight',
    'ledRotatingBuzzerWarningLight',
    'metric',
    'tiltCylinderSleeve',
    'steeringCylinderSleeve',
    'remaPowerConnector',
    'nationalStandardPowerConnector',
    'domesticBattery',
    'ficsHeliConnectedCarBasicEdition'
  ],
  2: [ // 二类/三类车参数
    'manufacturer',
    'polyurethaneTires',
    'fiveMeterLiftingHeight',
    'liftingHeightGantry',
    'standardSeriesFork',
    'nonStandardSeriesFork',
    'standardBodyColor',
    'customizedBodyColor',
    'accessoriesAndSpeedLimit',
    'turningSpeedLimit',
    'fireExtinguisher',
    'reverseBuzzer',
    'reverseVoiceHorn',
    'twoSideRearviewMirrors',
    'escapeBag',
    'rearWheelBraking',
    'frontWheelBrake',
    'guideWheel',
    'monitor',
    'magneticNavigation',
    'stationDrivingSoftPackSeat',
    'carStyleSeats',
    'electricFan',
    'windscreen',
    'rearWindshield',
    'fullCarLedLights',
    'readingLamp',
    'ledFlashingWarningLight',
    'metric',
    'nationalStandardPowerConnector',
    'chineseIdentification',
    'englishLogo'
  ],
  3: [ // 五类/七类车参数
    'manufacturer',
    'pneumaticTire',
    'solidTire',
    'singleTire',
    'twinTires',
    'twoPieceValve',
    'threeOrFourPieceValve',
    'threeMeterLiftingHeight',
    'liftingHeightGantry',
    'standardSeriesFork',
    'nonStandardSeriesForks',
    'standardBodyColor',
    'customizedBodyColor',
    'completeOpsFunctionality',
    'walkingOpsFunction',
    'overspeed',
    'reverseArmrestWithHorn',
    'seatBeltSwitch',
    'overloadOfMultiWayValve',
    'fireExtinguisher',
    'reverseBuzzer',
    'parkingSensor',
    'semiEnclosedSeat',
    'fullySuspendedSeat',
    'usbInterface',
    'electricFan',
    'turnTheSteeringWheel',
    'synchronousSteering',
    'standardRoofProtectionFrame',
    'nonStandardRoofProtectionFrame',
    'windscreen',
    'frontHeatresistantWindshield',
    'rearWindshield',
    'pcEnduranceBoardCeiling',
    'surfaceMountedCab',
    'singleCoolingAirConditioner',
    'dualPurposeAirConditioner',
    'warmAirBlower',
    'fullCarLedLights',
    'ledRearWorkLight',
    'blueLightBehindTheCar',
    'threeSidedBlueStripLight',
    'threeSidedRedStripLights',
    'ledFlashingWarningLight',
    'ledRotatingWarningLight',
    'ledRotatingBuzzerWarningLight',
    'metric',
    'tiltCylinderSleeve',
    'steeringCylinderSleeve',
    'medianExhaust',
    'highExhaust',
    'twoWheelDrive',
    'fourWheelDrive',
    'dentalEmbeddedDifferentialLock',
    'limitedSlipDifferential',
    'manualDifferentialLock',
    'ficsHeliConnectedCarBasicEdition'
  ]
}

export default {
  name: 'Contrast',
  data() {
    return {
      // 修改为5个元素
      selectedVehicleTypes: Array(5).fill(null),
      selectedVehicleCategories: Array(5).fill(null),
      selectedManufacturers: Array(5).fill(null),
      vehicleTypeOptions: Array(5).fill([]),
      manufacturerOptions: Array(5).fill([]),
      cachedManufacturers: Array(5).fill([]),
      loadingManufacturer: false,
      tableLoading: false,
      comparisonData: [],
      allVehicleTypes: [], // 存储所有车型数据
      allVehicleTypesSet: [] // 存储所有车型名称（去重）
    }
  },
  computed: {
    showTable() {
      return this.comparisonData.length > 0
    },
    // 检查是否可以执行对比
    canCompare() {
      // 统计已选择的制造商数量
      const selectedCount = this.selectedManufacturers.filter(Boolean).length;
      // 至少需要选择2个制造商才能对比
      return selectedCount >= 2;
    }
  },
  created() {
    this.loadAllVehicleTypes()
  },
  methods: {
    // 加载所有车型数据
    async loadAllVehicleTypes() {
      try {
        // 同时获取三类车的车型数据
        const [res1, res2, res3] = await Promise.all([
          listStandardone({}),
          listStandardtwo({}),
          listStandardfive({})
        ]);
        
        // 合并所有车型数据
        this.allVehicleTypes = [
          ...res1.rows.map(item => ({ 
            vehicleType: item.vehicleType, 
            manufacturer: item.manufacturer,
            category: 1,
            data: item
          })),
          ...res2.rows.map(item => ({ 
            vehicleType: item.vehicleType, 
            manufacturer: item.manufacturer,
            category: 2,
            data: item
          })),
          ...res3.rows.map(item => ({ 
            vehicleType: item.vehicleType, 
            manufacturer: item.manufacturer,
            category: 3,
            data: item
          }))
        ];
        
        // 获取所有车型名称（去重）
        this.allVehicleTypesSet = [...new Set(this.allVehicleTypes.map(item => item.vehicleType))].filter(Boolean);
        
        // 初始化所有选择器的车型选项
        this.vehicleTypeOptions = Array(5).fill(this.allVehicleTypesSet);
      } catch (error) {
        console.error('获取车型列表失败:', error);
        this.$message.error('获取车型数据失败，请稍后重试');
      }
    },
    
    // 处理车型选择变化
    handleVehicleTypeChange(index) {
      const idx = index - 1;
      const vehicleType = this.selectedVehicleTypes[idx];
      
      if (!vehicleType) {
        this.selectedVehicleCategories.splice(idx, 1, null);
        this.manufacturerOptions.splice(idx, 1, []);
        this.selectedManufacturers.splice(idx, 1, null); // 清空制造商选择
        // 重置车型选项为所有车型
        this.vehicleTypeOptions.splice(idx, 1, this.allVehicleTypesSet);
        return;
      }
      
      // 查找选中的车型数据
      const vehicleData = this.allVehicleTypes.find(v => v.vehicleType === vehicleType);
      if (!vehicleData) return;
      
      // 保存车类ID
      this.selectedVehicleCategories.splice(idx, 1, vehicleData.category);
      
      // 加载该车型对应的制造商
      this.loadManufacturers(index, vehicleType, vehicleData.category);
      
      // 更新车型选项为同一车类的所有车型
      this.updateVehicleTypeOptions(index, vehicleData.category);
    },

    // 加载制造商
    async loadManufacturers(index, vehicleType, categoryId) {
      this.loadingManufacturer = true;
      try {
        // 从所有车型数据中筛选出符合条件的制造商
        const manufacturers = this.allVehicleTypes
          .filter(item => item.vehicleType === vehicleType && item.category === categoryId)
          .map(item => item.manufacturer)
          .filter(Boolean);
        
        const uniqueManufacturers = [...new Set(manufacturers)];
        const formatted = uniqueManufacturers.map(m => ({ value: m, label: m }));
        
        this.cachedManufacturers.splice(index - 1, 1, formatted);
        this.manufacturerOptions.splice(index - 1, 1, formatted);
      } catch (error) {
        console.error('获取制造商失败:', error);
        this.$message.error('获取制造商数据失败');
      } finally {
        this.loadingManufacturer = false;
      }
    },

    // 更新车型选项
    updateVehicleTypeOptions(index, categoryId) {
      const idx = index - 1;
      // 从所有车型数据中筛选出对应车类的车型
      const vehicleTypes = this.allVehicleTypes
        .filter(item => item.category === categoryId)
        .map(item => item.vehicleType)
        .filter(Boolean);
      
      const uniqueVehicleTypes = [...new Set(vehicleTypes)];
      this.vehicleTypeOptions.splice(idx, 1, uniqueVehicleTypes);
    },

    // 搜索制造商
    searchManufacturers(index, query) {
      const cached = this.cachedManufacturers[index - 1];
      if (!query) {
        this.manufacturerOptions.splice(index - 1, 1, cached);
        return;
      }
      const filtered = cached.filter(item =>
        item.label.toLowerCase().includes(query.toLowerCase())
      );
      this.manufacturerOptions.splice(index - 1, 1, filtered);
    },

    // 处理对比按钮点击
    handleCompare() {
      if (!this.canCompare) {
        this.$message.warning('请至少选择两个制造商进行对比');
        return;
      }
      
      // 检查所有已选车类是否相同
      const selectedCategories = this.selectedVehicleCategories.filter(Boolean);
      if (selectedCategories.length > 0 && !selectedCategories.every(c => c === selectedCategories[0])) {
        this.$message.warning('请选择相同车类下的车型进行对比');
        return;
      }
      
      this.loadComparisonData();
    },

    // 加载对比数据
    async loadComparisonData() {
      this.tableLoading = true;
      try {
        // 获取所有已选制造商的数据
        const manufacturerData = await Promise.all(
          this.selectedManufacturers.map((manufacturer, index) => 
            manufacturer ? this.fetchManufacturerData(index) : Promise.resolve(null)
          )
        );
        
        this.generateComparisonTable(manufacturerData);
      } catch (error) {
        console.error('数据对比失败:', error);
        this.$message.error('数据对比失败，请稍后重试');
      } finally {
        this.tableLoading = false;
      }
    },

    // 获取制造商数据
    async fetchManufacturerData(index) {
      const categoryId = this.selectedVehicleCategories[index];
      const manufacturer = this.selectedManufacturers[index];
      const vehicleType = this.selectedVehicleTypes[index];

      if (!categoryId || !manufacturer || !vehicleType) return null;

      // 直接从已加载的数据中查找
      const vehicleData = this.allVehicleTypes.find(item => 
        item.category === categoryId && 
        item.manufacturer === manufacturer && 
        item.vehicleType === vehicleType
      );
      
      return vehicleData ? vehicleData.data : null;
    },

    // 生成对比表格
    generateComparisonTable(manufacturerData) {
      const categoryId = this.selectedVehicleCategories.find(Boolean);
      if (!categoryId) {
        this.comparisonData = [];
        return;
      }
      
      const params = vehicleParams[categoryId] || [];
      
      this.comparisonData = params.map(param => {
        const values = manufacturerData.map(data => {
          if (!data) return '-';
          // 处理布尔值显示
          const value = data[param];
          if (typeof value === 'boolean') {
            return value ? '是' : '否';
          }
          return value || '-';
        });
        
        // 检查该行所有值是否相同（忽略'-'）
        const nonEmptyValues = values.filter(v => v !== '-');
        const allSame = nonEmptyValues.length <= 1 || 
                       nonEmptyValues.every(v => v === nonEmptyValues[0]);
        
        return {
          paramName: this.formatParamName(param),
          values: values,
          allSame: allSame
        };
      });
    },

    // 格式化参数名称
    formatParamName(key) {
      const nameMap = {
        // 公共字段
        manufacturer: '制造商',
        metric: '公制螺纹',
        fireExtinguisher: '灭火器',
        electricFan: '电风扇',
        
        // 一类车特有
        pneumaticTire: '充气胎',
        solidTire: '实心胎',
        singleTire: '单胎',
        dualTire: '双胎',
        twoPieceValve: '两片阀',
        threeOrFourPieceValve: '三片或四片阀',
        threeMeterLiftingHeight: '3米起升高度',
        liftingHeightGantry: '其它起升高度门架',
        standardSeriesFork: '标准货叉',
        nonStandardSeriesForks: '非标货叉/属具',
        standardBodyColor: '标准车身颜色',
        customizedBodyColor: '其它车身颜色',
        completeOpsFunctionality: '全套OPS功能',
        turningSpeedLimit: '转弯限速',
        overspeed: '超速报警',
        reverseArmrestWithHorn: '倒车扶手喇叭',
        seatBeltSwitch: '座椅安全带开关',
        overloadOfMultiWayValve: '多路阀过载',
        reverseBuzzer: '倒车蜂鸣器',
        semiEnclosedSeat: '半包围座椅',
        fullySuspendedSeat: '全悬浮座椅',
        usbInterface: 'USB接口',
        turnTheSteeringWheel: '方向盘启动转向',
        synchronousSteering: '同步转向',
        standardRoofProtectionFrame: '标准护顶架',
        nonStandardRoofProtectionFrame: '非标护顶架',
        windscreen: '前挡风玻璃',
        frontHeatresistantWindshield: '耐高温挡风玻璃',
        rearWindshield: '后挡风玻璃',
        pcEnduranceBoardCeiling: 'PC耐力板顶棚',
        surfaceMountedCab: '贴片式驾驶室',
        singleCoolingAirConditioner: '单冷空调',
        dualPurposeAirConditioner: '冷暖空调',
        warmAirBlower: '暖风机',
        fullCarLedLights: '全车LED灯',
        ledRearWorkLight: 'LED后工作灯',
        blueLightBehindTheCar: '车后蓝光灯',
        threeSidedBlueStripLight: '三边蓝条灯',
        threeSidedRedStripLights: '三边红条灯',
        ledFlashingWarningLight: 'LED闪光警示灯',
        ledRotatingWarningLight: 'LED旋转警示灯',
        ledRotatingBuzzerWarningLight: '旋转蜂鸣警示灯',
        tiltCylinderSleeve: '倾斜油缸护套',
        steeringCylinderSleeve: '转向油缸护套',
        remaPowerConnector: 'REMA电源接口',
        nationalStandardPowerConnector: '国标电源接口',
        domesticBattery: '国产蓄电池',
        ficsHeliConnectedCarBasicEdition: '合力车联网',
        centralWideangleRearviewMirror: '中央广角后视镜', 
        twoSideRearviewMirrors: '两侧后视镜',           
        parkingSensor: '倒车雷达',                     
        
        // 二类车特有
        polyurethaneTires: '聚氨酯轮胎',
        fiveMeterLiftingHeight: '5米起升高度',
        nonStandardSeriesFork: '非标货叉',
        accessoriesAndSpeedLimit: '属具限速',
        reverseVoiceHorn: '倒车语音喇叭',
        escapeBag: '逃生包',
        rearWheelBraking: '后轮制动',
        frontWheelBrake: '前轮制动',
        guideWheel: '导向轮',
        monitor: '监控',
        magneticNavigation: '磁导航',
        stationDrivingSoftPackSeat: '站驾软包座椅',
        carStyleSeats: '座驾式座椅',
        readingLamp: '阅读灯',
        chineseIdentification: '中文标识',
        englishLogo: '英文标识',
        
        // 五类/七类车特有
        twinTires: '双胎',
        walkingOpsFunction: '行走OPS',
        medianExhaust: '中位排气',
        highExhaust: '高排气',
        twoWheelDrive: '两驱',
        fourWheelDrive: '四驱',
        dentalEmbeddedDifferentialLock: '牙嵌差速锁',
        limitedSlipDifferential: '限滑差速器',
        manualDifferentialLock: '手动差速锁'
      };
      
      return nameMap[key] || key;
    }
  }
}
</script>

<style scoped>
.app-container {
  padding: 20px;
}

.compare-container {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.compare-row {
  display: flex;
  gap: 20px;
  justify-content: space-between;
}

/* 第一行选择器样式 */
.selector-item {
  flex: 1;
  min-width: 240px;
}

/* 第二行选择器缩小20% */
.selector-item-shrink {
  flex: 0.8; /* 缩小20% */
  min-width: 200px;
}

.compare-button-container {
  display: flex;
  align-items: center;
  justify-content: center;
  min-width: 150px;
  padding: 0 10px; /* 添加两侧内边距 */
}

/* 紧凑型按钮样式 */
.compact-button {
  width: 100px; /* 原始150px的三分之二 */
  height: 60px; /* 原始80px的四分之三 */
  font-size: 16px; /* 适当调整字体大小 */
  padding: 10px; /* 内部填充 */
}

.diff-cell {
  color: #F56C6C;
  font-weight: bold;
}

.el-select {
  width: 100% !important;
}

/* 响应式调整 */
@media (max-width: 1200px) {
  .compare-row {
    flex-wrap: wrap;
  }
  
  .selector-item, .selector-item-shrink {
    min-width: calc(50% - 10px);
  }
  
  .compare-button-container {
    min-width: 100%;
    margin-top: 10px;
  }
  
  .compact-button {
    width: 100%;
    height: 50px;
  }
}
</style>