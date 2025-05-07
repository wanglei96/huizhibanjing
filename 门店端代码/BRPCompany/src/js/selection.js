/*节点说明
TableName：表名
ValueFieldName：值字段名
TextFieldName：值字段名
ShowCategory：是否显示分类树
OrderBy：排序
Condition：条件
CategoryValueFieldName：分类值字段名
-->
<SelectionConfig TableName="ViewSysUser" ValueFieldName="ID" TextFieldName="TrueName" NoFieldName="TrueName" ShowCategory="true" OrderBy="EmployeeNo" CategoryValueFieldName="SysOrganizationID" Condition="LoginName&lt;&gt;'admin'">
  <!--Category节点说明
  TableName：表名
  ValueFieldName：值字段名
  TextFieldName：值字段名
  ParentValueFieldName：父节点值字段名
  RootNodeText：根节点文本
  RootValue：根节点值
  OrderBy：排序
  Condition：条件
  -->
  <Category TableName="SysOrganization" ValueFieldName="ID" ParentValueFieldName="ParentID"  TextFieldName="Name" RootNodeText="组织机构" OrderBy="DisplayOrder" RootValue="0"/>
  <QueryFields>
    <!--QueryField节点说明
    Value：值
    Text：文本
    -->
    <QueryField Value="LoginName" Text="登录账号" />
    <QueryField Value="TrueName" Text="姓名" />
    <QueryField Value="EmployeeNo" Text="员工编号" />
    <QueryField Value="SysOrganizationName" Text="部门名称" />
  </QueryFields>
  <Columns>
    <!--Column节点说明
    DataField：数据字段
    DataFormatString：数据格式化字符串
    HeaderText：表头文本
    Width：宽度
    -->
    <Column DataField="EmployeeNo" HeaderText="员工编号" Width="100"  />
    <Column DataField="TrueName" HeaderText="姓名"/>
    <Column DataField="LoginName" HeaderText="登录账号"/>
    <Column DataField="SysOrganizationName" HeaderText="部门名称"/>
  </Columns>
  <ViewPage Url="../System/SysUserDetail.aspx?Mode=View" Width="800" Height="500" Permission="SysUserManagementView">
    <UrlParameters>
      <!--UrlParameter节点说明
      Name：参数名称
      Value：参数值
      DataSourceType：数据源类型，值可能为Table：数据表；Request：页面请求参数
      -->
      <UrlParameter Name="ID" Value="ID" DataSourceType="Table"></UrlParameter>
    </UrlParameters>
  </ViewPage>
</SelectionConfig>
 */
var config = {}

var payOrder = { //人员
	tableName: "view_pay_order", //表名
	valueFieldName: "id", //值字段名
	textFieldName: "pay_order_no", //文本字段名
	showCategory: false, //是否显示分类树
	orderBy: "create_time desc", //排序
	condition: "", //条件
	queryFields: [{
		fieldName: "member_name", //值
		text: "会员姓名", //文本
		operation: "like"
	},
	{
		fieldName: "book_name", //值
		text: "书名", //文本
		operation: "like"
	}],
	columns: [{
			dataField: "pay_order_no", //数据字段
			dataFormatString: null, //数据格式化字符串
			headerText: "账单编号", //表头文本
			width: null, //宽度
		},
		{
			dataField: "member_name", //数据字段
			dataFormatString: null, //数据格式化字符串
			headerText: "会员姓名", //表头文本
			width: null, //宽度
		}, {
			dataField: "member_card_no", //数据字段
			dataFormatString: null, //数据格式化字符串
			headerText: "会员卡号", //表头文本
			//width : "70%", //宽度
		}, {
			dataField: "book_name", //数据字段
			dataFormatString: null, //数据格式化字符串
			headerText: "书名", //表头文本
			//width : "70%", //宽度
		},
		{
			dataField: "book_sn", //数据字段
			dataFormatString: null, //数据格式化字符串
			headerText: "书号", //表头文本
			//width : "70%", //宽度
		}
	],
	viewPage: {
		url: null, //地址
		width: "800", //宽度 
		height: "500", //高度
		permission: "SysUserManagementView", //权限
		urlParameters: [{
			name: "500", //参数名称
			value: "500", //参数值
			dataSourceType: "500", //数据源类型，值可能为Table：数据表；Request：页面请求参数
		}]
	}
};


export default {
	payOrder
};