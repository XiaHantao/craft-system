<template>
  <div class="app-container">
    <div class="compare-selectors">
      <div class="selector-item" v-for="n in 2" :key="n">
        <el-select
          v-model="selectedVehicleType[n-1]"
          placeholder="请选择车类（相同车类）"
          @change="handleVehicleTypeChange(n)"
          style="margin-bottom: 10px"
          clearable
        >
          <el-option
            v-for="item in vehicleTypes"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>

        <el-select
          v-model="selectedManufacturers[n-1]"
          filterable
          remote
          :remote-method="query => searchManufacturers(n, query)"
          :loading="loadingManufacturer"
          :disabled="!selectedVehicleType[n-1]"
          :placeholder="selectedVehicleType[n-1] ? '请输入制造商名称' : '请先选择车类'"
          style="width: 100%"
          clearable
          @change="handleManufacturerChange"
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

     <!-- 对比表格 -->
     <el-table
      v-loading="tableLoading"
      :data="comparisonData"
      border
      style="width: 100%; margin-top: 20px"
      v-if="showTable"
    >
      <el-table-column prop="paramName" label="参数名称" width="200" fixed />
      <el-table-column label="制造商A">
        <template #default="{ row }">
          <span :class="{ 'diff-cell': row.manufacturerA !== row.manufacturerB }">
            {{ row.manufacturerA || '-' }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="制造商B">
        <template #default="{ row }">
          <span :class="{ 'diff-cell': row.manufacturerA !== row.manufacturerB }">
            {{ row.manufacturerB || '-' }}
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

// 各车类参数配置
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
  2: [ // 二类车参数
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
      selectedVehicleType: [null, null],
      selectedManufacturers: [null, null],
      manufacturerOptions: [[], []],
      cachedManufacturers: [[], []],
      loadingManufacturer: false,
      tableLoading: false,
      comparisonData: [],
      vehicleTypes: [
        { value: 1, label: '一类车' },
        { value: 2, label: '二类车' },
        { value: 3, label: '五类/七类车' }
      ]
    }
  },
  computed: {
    showTable() {
      return this.selectedManufacturers.every(m => m) && this.comparisonData.length > 0
    }
  },
  watch: {
    selectedManufacturers: {
      deep: true,
      handler(newVal) {
        if (newVal[0] && newVal[1]) {
          this.loadComparisonData()
        }
      }
    }
  },
  methods: {
    handleVehicleTypeChange(index) {
      const vehicleType = this.selectedVehicleType[index - 1]
      if (!vehicleType) {
        this.manufacturerOptions.splice(index - 1, 1, [])
        return
      }
      this.loadManufacturers(index, vehicleType)
    },

    async loadManufacturers(index, vehicleType) {
      this.loadingManufacturer = true
      try {
        let apiMethod
        switch (vehicleType) {
          case 1: apiMethod = listStandardone; break
          case 2: apiMethod = listStandardtwo; break
          case 3: apiMethod = listStandardfive; break
          default: return
        }

        const res = await apiMethod({})
        const manufacturers = res.rows.map(item => item.manufacturer).filter(Boolean)
        const uniqueManufacturers = [...new Set(manufacturers)]
        
        const formatted = uniqueManufacturers.map(m => ({ value: m, label: m }))
        this.cachedManufacturers.splice(index - 1, 1, formatted)
        this.manufacturerOptions.splice(index - 1, 1, formatted)
      } catch (error) {
        console.error('获取制造商失败:', error)
      } finally {
        this.loadingManufacturer = false
      }
    },

    searchManufacturers(index, query) {
      const cached = this.cachedManufacturers[index - 1]
      if (!query) {
        this.manufacturerOptions.splice(index - 1, 1, cached)
        return
      }
      const filtered = cached.filter(item =>
        item.label.toLowerCase().includes(query.toLowerCase())
      )
      this.manufacturerOptions.splice(index - 1, 1, filtered)
    },

    async loadComparisonData() {
      // 检查车类是否相同
      if (this.selectedVehicleType[0] !== this.selectedVehicleType[1]) {
        this.$message.warning('请选择相同车类进行对比')
        this.comparisonData = []
        return
      }

      this.tableLoading = true
      try {
        const [dataA, dataB] = await Promise.all([
          this.fetchManufacturerData(0),
          this.fetchManufacturerData(1)
        ])
        
        this.generateComparisonTable(dataA, dataB)
      } catch (error) {
        console.error('数据对比失败:', error)
      } finally {
        this.tableLoading = false
      }
    },

    async fetchManufacturerData(index) {
      const vehicleType = this.selectedVehicleType[index]
      const manufacturer = this.selectedManufacturers[index]

      let apiMethod
      switch (vehicleType) {
        case 1: apiMethod = listStandardone; break
        case 2: apiMethod = listStandardtwo; break
        case 3: apiMethod = listStandardfive; break
        default: return {}
      }

      const res = await apiMethod({ manufacturer })
      return res.rows[0] || {}
    },

    generateComparisonTable(dataA, dataB) {
      const vehicleType = this.selectedVehicleType[0]
      const params = vehicleParams[vehicleType] || []
      
      this.comparisonData = params.map(param => ({
        paramName: this.formatParamName(param),
        manufacturerA: dataA[param] || '-',
        manufacturerB: dataB[param] || '-'
      }))
    },

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
        accessoriesAndSpeedLimit: '属具未到位限速',     
        escapeBag: '逃生包',                          
        guideWheel: '导向轮',                         
        magneticNavigation: '磁导航',                 

        // 五类/七类车特有
        twinTires: '双胎',
        walkingOpsFunction: '行走OPS',
        parkingSensor: '倒车雷达',
        medianExhaust: '中位排气',
        highExhaust: '高排气',
        twoWheelDrive: '两驱',
        fourWheelDrive: '四驱',
        dentalEmbeddedDifferentialLock: '牙嵌差速锁',
        limitedSlipDifferential: '限滑差速器',
        manualDifferentialLock: '手动差速锁',
        walkingOpsFunction: '行走OPS功能',           
        medianExhaust: '中位排气',                   
        highExhaust: '高排气',                       
      }
      return nameMap[key] || key
    },

    handleManufacturerChange() {
      this.comparisonData = []
    }
  }
}
</script>

<style scoped>
.compare-selectors {
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
}
.selector-item {
  flex: 1;
}
.diff-cell {
  color: #F56C6C;
  font-weight: bold;
}
.el-select {
  width: 400px !important; /* 覆盖原有100%宽度 */
}
</style>