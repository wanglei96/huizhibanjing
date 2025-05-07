import { reactive, defineEmits } from 'vue'
import common from '@/js/common.js'
import util from '@/js/util.js'
import config from '@/js/config'

const page = function (pageOptions) {
    if (!pageOptions) {
        throw new Error('pageOptions参数不允许为空')
    }
    //详情页开关
    const detailFormVisible = pageOptions.detailFormVisible
    //确定按钮状态
    const isLoading = ref()
    //表单引用
    const refDataEntityForm = pageOptions.refDataEntityForm
    //定义事件发射器
    const emit = pageOptions.emit
    //表名
    const tableName = pageOptions.tableName
    //数据名称
    const dataName = pageOptions.dataName || util.toPascal(pageOptions.tableName)
    //页面模式
    const mode = pageOptions.mode
    //基础标题
    const baseTitle = pageOptions.baseTitle || ''
    const title = ref('')
    //取得实体服务名称
    const getServiceName = pageOptions.getServiceName || 'Get' + dataName
    //更新服务名称
    const updateServiceName = pageOptions.updateServiceName || 'Update' + dataName
    //添加服务名称
    const addServiceName = pageOptions.addServiceName || 'Add' + dataName
    //主键字段名
    const primaryKeyName = pageOptions.primaryKeyName || 'id'
    //是否基础表
    const isBasicTable = pageOptions.isBasicTable || false
    //是否cms表
    const isCms = pageOptions.isCms || false
    //主键值
    let primaryKeyValue = pageOptions.primaryKeyValue
    //是否自动加载
    const autoLoad = pageOptions.autoLoad == null ? true : pageOptions.autoLoad
    //数据实体
    let dataEntity = ref({})
    let getDataEntity = pageOptions.getDataEntity
    //表单校验失败时触发
    const submitFail = pageOptions.submitFail
    //当关闭详情页时触发
    const onDetailHide = pageOptions.onDetailHide
    //绑定选择器配置(一个对象数组)
    const bindSelectOptions = pageOptions.bindSelectOptions
    //选择器信息
    const pickerInfo = reactive({})

    /**
     * 加载页面
     * @param options
     */
    function loadPage(options) {
        //绑定控件
        bindControls(options)
        //绑定数据
        if (mode === 'edit' || mode === 'view') {
            if (pageOptions.bindData) {
                pageOptions.bindData()
            } else {
                bindData(options)
            }
        }
    }

    /**
     * 绑定控件
     * @param options
     */
    function bindControls(options) {
        if (options.bindControls) {
            options.bindControls(options)
        } else {
            bindControlsBase(options)
        }
    }

    /**
     * 绑定控件
     * @param options
     */
    function bindControlsBase(options) {
        var pageMode = options.mode || mode
        if (pageMode === 'add') {
            title.value = '新增' + baseTitle
            if (options.setAddMode) {
                options.setAddMode()
            } else {
                setAddModeBase()
            }
        } else if (pageMode === 'edit') {
            title.value = '编辑' + baseTitle
            if (options.setEditMode) {
                options.setEditMode()
            }
        } else if (pageMode === 'view') {
            title.value = '查看' + baseTitle
            if (options.setViewMode) {
                options.setViewMode()
            }
        }
        if (options.bindControlsCustom) {
            options.bindControlsCustom()
        }

        if (bindSelectOptions) {
            bindSelect()
        }
    }

    /**
     * 绑定选择器数据（下拉框，多选框，标签）
     */
    function bindSelect() {
        bindSelectOptions.forEach((item, index) => {
            let data = {
                tableName: item.tableName,
                pageSize: 99999
            }
            if (item.fields) {
                data.fields = item.fields
            }
            if (item.orderBy) {
                data.orderBy = item.orderBy
            }
            if (item.condition) {
                data.condition = item.condition
            }
            common.call('GetTable', data, (res) => {
                pickerInfo[util.toCameral(item.tableName) + 'List'] = res.data
            })
        })
    }

    /**
     * 设置添加模式
     */
    function setAddModeBase() {
    }

    /**
     * 是否添加模式
     */
    function isAddMode() {
        return mode == 'add'
    }

    /**
     * 是否添加模式
     */
    function isEditMode() {
        return mode == 'edit'
    }

    /**
     * 是否查看模式
     */
    function isViewMode() {
        return mode == 'view'
    }

    /**
     * 绑定数据
     * @param options
     */
    function bindData(options) {
        bindDataBase(options)
    }

    /**
     * 绑定数据
     * @param options
     */
    function bindDataBase(options) {
        let data = {}
        data[options.primaryKeyName || primaryKeyName] = options.primaryKeyValue || primaryKeyValue
        if (isBasicTable) {
            data.tableName = tableName
        }
        common.call(options.getServiceName || getServiceName, data, function (result) {
            if (options.processDataEntity) {
                dataEntity.value = options.processDataEntity(result.data)
                console.log('dataEntity:', dataEntity.value)
                return
            }
            dataEntity.value = result.data
            console.log('dataEntity:', dataEntity.value)
        })
    }

    /**
     * 保存
     * @param options
     * @param success
     */
    function save(options, success) {
        if (options?.mode?.value === 'edit' || mode === 'edit') {
            //修改
            if (options?.updateDataEntity) {
                options.updateDataEntity()
                return
            }

            if (options?.getDataEntity) {
                options.getDataEntity(dataEntity)
            } else if (getDataEntity) {
                getDataEntity(dataEntity)
            }
            let data = filterNullProperties(dataEntity.value)
            if (isBasicTable) {
                data.tableName = tableName
                data[primaryKeyName] = primaryKeyValue
            }
            if (config.isCompany) {
                data.companyId = common.getLocalStorage('companyId')
            }
            common.call(
                options?.updateServiceName || updateServiceName,
                data,
                function (result) {
                    if (options?.submitSuccess) {
                        options.submitSuccess()
                    } else if (success) {
                        success(result)
                    } else {
                        detailFormVisible.value = false
                        emit('reload-data')
                        setTimeout(() => {
                            isLoading.value = false
                        }, 300)
                    }
                },
                function (result) {
                    let errorMessage = result.errorMessage || result.error_message
                    //输出错误信息
                    if (result.messageType === 'warning') {
                        ElMessage({ message: errorMessage, type: 'warning' })
                    } else {
                        ElMessage({ message: errorMessage, type: 'error' })
                    }
                    setTimeout(() => {
                        isLoading.value = false
                    }, 310)
                }
            )
        } else if (options?.mode?.value === 'add' || mode === 'add') {
            //添加
            if (options?.addDataEntity) {
                options.addDataEntity()
                return
            }
            if (options?.getDataEntity) {
                options.getDataEntity(dataEntity)
            } else if (getDataEntity) {
                getDataEntity(dataEntity)
            }
            let data = filterNullProperties(dataEntity.value)
            if (isBasicTable) {
                data.tableName = tableName
            }
            if (config.isCompany) {
                data.companyId = common.getLocalStorage('companyId')
            }
            if (isCms) {
                data.cmsSiteId = common.getCmsSiteId()
            }
            common.call(
                options?.addServiceName || addServiceName,
                data,
                function (result) {
                    if (options?.submitSuccess) {
                        options.submitSuccess()
                    } else if (success) {
                        success(result)
                    } else {
                        detailFormVisible.value = false
                        emit('reload-data')
                        setTimeout(() => {
                            isLoading.value = false
                        }, 300)
                    }
                },
                function (result) {
                    let errorMessage = result.errorMessage || result.error_message
                    //输出错误信息
                    if (result.messageType === 'warning') {
                        ElMessage({ message: errorMessage, type: 'warning' })
                    } else {
                        ElMessage({ message: errorMessage, type: 'error' })
                    }
                    setTimeout(() => {
                        isLoading.value = false
                    }, 310)
                }
            )
        }
    }

    /**
     * 过滤掉对象中值为null的属性并返回一个新的对象
     * @param data
     * @returns {{}}
     */
    function filterNullProperties(data) {
        const filteredData = Object.entries(data).reduce((acc, [key, value]) => {
            // 如果值不为null，则保留这个属性
            if (value !== null) {
                acc[key] = value
            }
            return acc
        }, {})
        return filteredData
    }

    if (autoLoad) {
        loadPage(pageOptions)
    }

    //提交表单
    function submitForm(options) {
        refDataEntityForm.value
            .validate()
            .then(() => {
                isLoading.value = true
                save(options)
            })
            .catch((res) => {
                options?.submitFail ? options.submitFail() : submitFail ? submitFail() : null
                console.log(res)
                ElMessage({ message: '表单格式有误', type: 'warning' })
            })
    }

    //监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
    watch(detailFormVisible, (newValue, oldValue) => {
        if (!newValue) {
            onDetailHide ? onDetailHide() : ''
        }
        //清除表单项的校验结果
        refDataEntityForm?.value?.clearValidate()
        dataEntity.value = {}
    })

    //监听详情页开关，详情页关闭时移除该表单项的校验结果并清空dataEntity
    watch(detailFormVisible, (newValue, oldValue) => {
        if (!newValue) {
            onDetailHide ? onDetailHide() : '';
            //清除单项的校验结果
            refDataEntityForm?.value?.clearValidate();
            dataEntity.value = {};
        }

    })

    return {
        bindControls,
        bindControlsBase,
        bindData,
        bindDataBase,
        save,
        submitForm,
        dataEntity,
        mode,
        isLoading,
        title,
        pickerInfo,
        loadPage,
        isAddMode,
        isEditMode,
        isViewMode
    }
}

//提交表单
//重置表单
const resetForm = (form) => {
    form.resetFields();
};
export default page;