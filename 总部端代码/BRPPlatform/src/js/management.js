import { computed, reactive, ref } from "vue";
import common from "@/js/common";
import util from "@/js/util";
import { ElMessage, ElMessageBox } from "element-plus";
import config from '@/js/config';

const management = function (pageOptions) {
    if (!pageOptions) {
        throw new Error("pageOptions参数不允许为空");
    }

    //多选主键值列表
    const primaryKeyValueArray = ref([]);
    //多选选中数量
    const selectedColumnCount = computed(() => {
        return primaryKeyValueArray.value.length;
    });
    //详情页组件引用
    const refDetail = pageOptions.refDetail;
    //表名
    const tableName = pageOptions.tableName;
    //数据列表
    const dataList = ref([]);
    //每页大小
    const pageSize = ref(pageOptions.pageSize || 10);
    //当前页
    const pageIndex = ref(pageOptions.pageIndex || 0);
    //总记录数
    const recordCount = ref(0);
    //总页数
    const pageCount = ref(0);
    //分页大小列表
    const pageSizes = reactive(pageOptions.pageSizes || [10, 20, 30, 50]);
    //排序字段
    const orderBy = ref(pageOptions.orderBy || '')
    //查询条件
    const condition = pageOptions.condition || '';
    //分页布局
    const layout = pageOptions.layout || "total, sizes, prev, pager, next, jumper";
    //请求参数
    const parameters = reactive(pageOptions.parameters || {});
    //实体名
    const dataName = pageOptions.dataName || util.toPascal(tableName);
    //查询列表处理类名
    const getListServiceName = pageOptions.getListServiceName || "Get" + dataName + "List";
    //单个删除处理类名
    const deleteServiceName = pageOptions.deleteServiceName || "Delete" + dataName;
    //批量删除处理类名
    const batchDeleteServiceName = pageOptions.batchDeleteServiceName || "BatchDelete" + dataName;
    //列表主键属性名
    const primaryKeyName = pageOptions.primaryKeyName || "id";
    //是否是基础表
    const isBasicTable = pageOptions.isBasicTable;
    //列表类型
    const gridType = pageOptions.gridType || "List";
    //列表书主键名
    const valueFieldName = pageOptions.valueFieldName || 'id';
    //列表树上级属性名
    const parentFieldName = pageOptions.parentFieldName || 'parent_id';
    // TODO 获取请求参数
    const optionGetParameters = pageOptions.getParameters;
    const optionParameters = pageOptions.parameters;
    //列表树上级属性值
    const parentFieldValue = pageOptions.parentFieldValue || 0;
    //上级字段名
    const parentValueFieldName = pageOptions.parentValueFieldName || 0;
    //如果是Cms基础表，则增加站点id条件
    const isCms = pageOptions.gridType || false;
    //处理返回结果
    const processResult = pageOptions.processResult || null;

    //是否有左侧树
    const hasTree = pageOptions.hasTree || false;
    //树表名
    const treeTableName = pageOptions.treeTableName || '';
    //树查询条件
    const treeCondition = pageOptions.treeCondition || '';
    //树查询排序
    const treeOrderBy = pageOptions.treeOrderBy || '';
    //树数据
    const treeData = ref([]);
    //树根节点属性名
    const treeRootName = pageOptions.treeRootName || 'id';
    //树根节点属性值
    const treeRootValue = pageOptions.treeRootValue || 0;
    //树父节点属性名
    const treeParentValueFieldName = pageOptions.treeParentValueFieldName || 'parent_id';
    //左侧树配置,还有其他属性可参考Element Plus官方文档增加配置
    const defaultProps = ref({
        //指定子树为节点对象的某个属性值
        children: 'children',
        //指定节点标签为节点对象的某个属性值
        label: pageOptions.treeTextFieldName || 'name'
    });
    const processDataItem = pageOptions.processDataItem || null;

    /**
     * 加载页面
     */
    function loadPage(options) {
        //绑定控件
        if (options?.bindControls) {
            options.bindControls();
        } else {
            bindControls();
        }
        //绑定数据
        if (options?.bindData) {
            if (gridType === "Tree") {
                options.bindData(treeData);
            } else {
                options.bindData(dataList);
            }
        } else {
            bindData(options);
            if (hasTree) {
                bindTree(options);
            }
        }
    }

    /**
     * 绑定控件
     */
    function bindControls() {
        bindControlsBase();
    }

    function bindControlsBase() {
    }

    /**
     * 绑定数据
     */
    function bindData(options) {
        pageIndex.value = options?.pageIndex || 0;
        loadData(options);
    }

    /**
     * 绑定列表左侧树
     * @param options
     */
    function bindTree() {
        const data = {
            tableName: treeTableName
        };
        if (treeCondition) {
            data.condition = treeCondition;
        }
        if (treeOrderBy) {
            data.orderBy = treeOrderBy;
        }
        data.pageSize = 99999;
        if (config.isCompany) {
            let companyId = common.getLocalStorage("companyId")
            if (data.condition) {
                data.condition += ` and company_id=${companyId}`;
            } else {
                data.condition = `company_id=${companyId}`;
            }
        }
        data.pageSize = 99999;
        common.call("GetTable", data, function (result) {
            treeData.value = common.getTreeData({
                dataList: result.data,
                valueFieldName: treeRootName,
                parentValueFieldName: treeParentValueFieldName,
                parentValue: treeRootValue
            });
        })
    }

    /**
     * 加载数据
     */
    function loadData(options) {
        let data = getParameters(options);
        if (config.isCompany) {
            data.companyId = common.getLocalStorage("companyId")
        }
        common.call(getListServiceName, data, function (result) {
            if (gridType === "List") {
                outputDataGrid(options, result);
            } else if (gridType === "Tree") {
                outputTreeGrid(options, result);
            }
        });
    }

    function outputDataGrid(options, result) {
        let resDataList = [];
        let newDataList = result.data.dataList || result.data.DataList || result.data;

        if (processResult !== null) {
            newDataList = processResult(result);
        }
        for (let i = 0; i < newDataList.length; i++) {
            let dataItem = newDataList[i];
            //处理数据项
            if (options?.processDataItem) {
                dataItem = options.processDataItem(dataItem);
            } else if (processDataItem) {
                processDataItem(dataItem);
            }
            resDataList.push(dataItem);
        }
        //处理数据列表
        if (options?.processDataList) {
            resDataList = options.processDataList(resDataList);
        }
        dataList.value = [...resDataList];
        console.log("dataList:", dataList.value);
        recordCount.value = result.data.recordCount;
        pageCount.value = result.data.pageCount;
    }

    function outputTreeGrid(options, result) {
        dataList.value = common.getTreeData({
            dataList: result.data.dataList,
            valueFieldName,
            parentFieldName,
            parentFieldValue,
            parentValueFieldName
        });
    }

    function getParameters(options) {
        let data = {};
        Object.assign(data, parameters);

        if (options?.getParameters) {
            data = options.getParameters();
        } else if (options?.parameters) {
            data = options.parameters;
        } else if (optionGetParameters) {
            data = optionGetParameters();
        } else if (optionParameters) {
            data = optionParameters;
        }

        //设置时间范围请求参数
        for (let key in data) {
            if (key.indexOf('dpSearch') !== -1) {
                let parameterName = getParameterName(key, 'dpSearch'.length);
                if (data[key]) {
                    data[parameterName + 'Begin'] = data[key][0];
                    data[parameterName + 'End'] = data[key][1];
                }
            }
        }
        data.pageIndex = options?.pageIndex || pageIndex.value;
        data.pageSize = gridType === 'Tree' ? 99999 : (options?.pageSize || pageSize.value);
        data.orderBy = options?.orderBy || orderBy.value;
        //拼接各个方式传进来的condition
        let conditionArr = [];
        if (data.condition) {
            conditionArr.push(data.condition);
        }
        if (options?.condition) {
            conditionArr.push(options.condition);
        }
        if (condition) {
            conditionArr.push(condition);
        }
        if (conditionArr.length > 0) {
            data.condition = conditionArr.join(' and ');
        }
        // data.propertyNameType = "camel";
        if (isBasicTable) {
            data.tableName = tableName;
        }
        return data;
    }

    //多选
    const selectRow = (val) => {
        primaryKeyValueArray.value = [];
        val.forEach((item) => {
            primaryKeyValueArray.value.push(item[primaryKeyName]);
        });
    };

    const sortChange = (sort) => {
        let underProp = underScoreStr(sort.prop);
        let prop = underProp.replace(/_text$/, '');
        let order = sort.order === 'ascending' ? 'asc' : 'desc'
        let neworderBy = prop + ' ' + order;
        orderBy.value = neworderBy;
        loadData()
    }
    //将下划线转成驼峰规则
    function underScoreStr(str) {
        const underStr = str.replace(/([A-Z])/g, (match, p1) => `_${p1.toLowerCase()}`);
        return underStr
    }

    /**
     * 删除
     * @param {Object} primaryKeyValue
     */
    function remove(primaryKeyValue, options) {
        // 询问
        ElMessageBox.confirm("确定要删除这条数据吗？", "温馨提示", {
            confirmButtonText: "确认",
            cancelButtonText: "取消",
            type: "warning"
        })
            .then(() => {
                const data = {};
                data[primaryKeyName] = primaryKeyValue;
                common.call(deleteServiceName.value, data, (result) => {
                    // 刷新数据
                    loadData(options);
                });
            })
            .catch((error) => {
                throw new Error(error.message);
            });
    }

    /**
     * 批量删除
     * @param {Object} page
     * @param {Object} primaryKeyValueArray
     */
    function batchRemove(primaryKeyValueArray, options) {
        // 询问
        ElMessageBox.confirm("确定要删除所选数据吗？", "温馨提示", {
            confirmButtonText: "确认",
            cancelButtonText: "取消",
            type: "warning"
        })
            .then(() => {
                const data = {};
                data.ids = primaryKeyValueArray;
                common.call(batchDeleteServiceName.value, data, (result) => {
                    loadData(options);
                });
            })
            .catch((error) => {
                console.log(error);
            });
    }

    /**
     * 删除
     */
    function deleteRow(primaryKeyValue) {
        // 询问
        ElMessageBox.confirm("确定要删除这条数据吗？", "温馨提示", {
            confirmButtonText: "确认",
            cancelButtonText: "取消",
            type: "warning"
        })
            .then(() => {
                const data = {};
                data[primaryKeyName] = primaryKeyValue;
                if (isBasicTable) {
                    data.tableName = tableName;
                }
                common.call(deleteServiceName, data, (result) => {
                    // 刷新数据
                    loadData();
                });
            })
            .catch((error) => {
                throw new Error(error.message);
            });
    }

    /**
     * 批量删除
     */
    function deleteRows() {
        if (primaryKeyValueArray.value.length === 0) {
            ElMessage({ message: "请先选择要删除的数据", type: "warning" });
        } else {
            // 询问
            ElMessageBox.confirm("确定要删除所选数据吗？", "温馨提示", {
                confirmButtonText: "确认",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    const data = {};
                    if (primaryKeyName == "id") {
                        data.ids = primaryKeyValueArray.value;
                    } else if (primaryKeyName == "code") {
                        data.codes = primaryKeyValueArray.value;
                    } else {
                        data.primaryKeys = primaryKeyValueArray.value;
                    }
                    if (isBasicTable) {
                        data.tableName = tableName;
                    }
                    common.call(batchDeleteServiceName, data, (result) => {
                        loadData();
                    });
                })
                .catch((error) => {
                    throw new Error(error.message);
                });
        }
    }

    //当前页
    const currentPage = computed({
        get() {
            return pageIndex.value + 1;
        },
        set(newValue) {
            pageIndex.value = newValue - 1;
            loadData();
        }
    });
    //页面大小
    const pageSizeComputed = computed({
        get() {
            return pageSize.value;
        },
        set(newValue) {
            pageIndex.value = 0;
            pageSize.value = newValue;
            loadData();
        }
    });

    //查询
    function query(options) {
        loadPage(options);
    }

    //重置搜索表单数
    const resetForm = (form) => {
        form.resetFields();
    };

    /*
    打开明细窗口
    */
    const openDetailDialog = (options) => {
        refDetail.value.init(options);
    };

    /*
     * 上移
     */
    function moveUp(row) {
        move("MoveUp", row);
    }

    /*
     * 下移
     */
    function moveDown(row) {
        move("MoveDown", row);
    }

    /*
     * 移动数据
     */
    function move(moveDirection, row) {
        var data = new Object();
        data.primaryKey = getPrimaryKeyValue(row);
        data.primaryKeyName = primaryKeyName;
        data.tableName = tableName;
        data.moveDirection = moveDirection;
        // 如果是树型基础表，则增加父节点条件
        if (gridType == "Tree") {
            var parentValue = getParentValue(row);
            if (primaryKeyName == "id") {
                if (!parentValue || parentValue == "null") {
                    parentValue = 0;
                }
                data.condition = "ifnull(parent_id,0)=" + parentValue + "";
            } else {
                if (!parentValue || parentValue == "null") {
                    parentValue = "";
                }
                data.condition = "ifnull(parent_code,'')='" + parentValue + "'";
            }
        }

        // 如果是Cms基础表，则增加站点id条件
        if (isCms) {
            data.isCms = true;
            data.cmsSiteId = common.getLocalStorage("cmsSiteId");
            if (data.condition != null && data.condition != "") {
                data.condition += " and ";
            } else {
                data.condition = "";
            }
            var cmsSiteId = common.getLocalStorage("cmsSiteId");
            data.condition += "cms_site_id=" + cmsSiteId;
        }
        common.call("Move", data, function (result) {
            loadData();
        });
    }

    function getParentValue(param) {
        //TODO complete me
    }

    /*
     * 切换启用状态
     */
    function toggleEnabled(row) {
        var data = {};
        data.primaryKey = getPrimaryKeyValue(row);
        data.primaryKeyName = primaryKeyName;
        data.tableName = tableName;
        data.enabled = row.enabled;
        common.call("SetEnabled", data, function (result) {
            loadData();
        });
    }

    /**
     * 取得主键值
     */
    function getPrimaryKeyValue(row) {
        if (gridType == "List") {
            let primaryKeyValue = row[primaryKeyName];
            return primaryKeyValue;
        } else {
            let primaryKeyValue = row[primaryKeyName];
            return primaryKeyValue;
        }
    }

    /**
     * 加载数据
     */
    loadPage(pageOptions);

    return {
        parameters,
        dataList,
        pageSizes,
        pageCount,
        recordCount,
        layout,
        selectRow,
        deleteRow,
        deleteRows,
        query,
        selectedColumnCount,
        loadPage,
        loadData,
        currentPage,
        pageSize,
        pageSizeComputed,
        resetForm,
        openDetailDialog,
        moveUp,
        moveDown,
        toggleEnabled,
        treeData,
        defaultProps,
        primaryKeyValueArray,
        sortChange
    };
};

/*
 * 根据查询条件控件id取得参数名称
 */
function getParameterName(searchControlId, prefixLength) {
    let parameterName = searchControlId.substring(prefixLength);
    parameterName = parameterName.substring(0, 1).toLowerCase()
        + parameterName.substring(1);
    return parameterName;
}

//初始化列表页
export default management