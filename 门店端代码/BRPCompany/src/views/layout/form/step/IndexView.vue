<script setup>
// 分步表单 当前选中值
const stepActive = ref(0)
// form表单响应式数据
const stepForm = reactive({
  fukuan: '6226170415671722',
  shoukuan: '1030009174317046555',
  xingming: '张三',
  jine: '300',
  password: '123456',
  yzm: ''
})
// 下一步
const next = () => {
  if (stepActive.value++ > 3) stepActive.value = 0
}
// 上一步
const prev = () => {
  if (stepActive.value-- >= 0) stepActive.value = 0
}
</script>
<template>
  <div>
    <el-card shadow="never" style="margin-bottom: 15px">
      <h4>分步表单</h4>
      <div style="margin-top: 5px; font-size: 14px; color: #aaa">
        当表单较长时，提高长表单的操作效率显得格外重要，所以将表单信息进行拆分，并分步设计，可以有效降低用户输入压力...
      </div>
    </el-card>
    <el-card shadow="never" class="stepCard">
      <el-steps :active="stepActive" finish-status="success" align-center>
        <el-step title="第一步" description="填写转账信息" />
        <el-step title="第二步" description="确认转账信息" />
        <el-step title="第三步" description="短信验证" />
        <el-step title="第四步" description="转账成功" />
      </el-steps>
      <el-form class="stepForm" :model="stepForm" label-width="120px">
        <!-- 第一步 -->
        <div v-show="stepActive === 0">
          <el-form-item label="付款账户">
            <el-input v-model="stepForm.fukuan" placeholder="fukuanren@eleadmin.cn"></el-input>
          </el-form-item>
          <el-form-item label="收款账户">
            <el-input v-model="stepForm.shoukuan" placeholder="shoukuanren@eleadmin.cn"></el-input>
          </el-form-item>
          <el-form-item label="收款人姓名">
            <el-input v-model="stepForm.xingming" placeholder="xingming"></el-input>
          </el-form-item>
          <el-form-item label="转账金额">
            <el-input v-model="stepForm.jine" placeholder="300" prefix-icon="search"></el-input>
          </el-form-item>
        </div>
        <!-- 第二步 -->
        <div v-show="stepActive === 1">
          <el-alert
            title="请您确认转账信息，确认转账后，资金将直接打入对方账户，无法退回。"
            type="warning"
            show-icon
            style="width: 700px; margin: 0 auto"
          />
          <table cellpadding="0" cellspacing="0" class="mytable">
            <tr>
              <td width="120" align="right" bgcolor="#f7f7f7">付款账户</td>
              <td>{{ stepForm.fukuan }}</td>
            </tr>
            <tr>
              <td width="120" align="right" bgcolor="#f7f7f7">收款账户</td>
              <td>{{ stepForm.shoukuan }}</td>
            </tr>
            <tr>
              <td width="120" align="right" bgcolor="#f7f7f7">付款人姓名</td>
              <td>{{ stepForm.xingming }}</td>
            </tr>
            <tr>
              <td width="120" align="right" bgcolor="#f7f7f7">转账金额</td>
              <td>{{ stepForm.jine }}</td>
            </tr>
            <tr>
              <td width="120" align="right" bgcolor="#f7f7f7">支付密码</td>
              <td>
                <el-input
                  type="password"
                  style="width: 180px"
                  v-model="stepForm.password"
                ></el-input>
              </td>
            </tr>
          </table>
        </div>
        <!-- 第三步 -->
        <div v-show="stepActive === 2">
          <el-alert
            title="短信已经发送至您的银行预留的手机号上，请查收！"
            type="warning"
            show-icon
            style="width: 700px; margin: 0 auto; margin-bottom: 20px"
          />
          <el-form-item label="短信验证码">
            <el-input
              v-model="stepForm.yzm"
              placeholder="请输入短信验证码"
              style="width: 400px"
            ></el-input>
          </el-form-item>
        </div>
        <!-- 第四步 -->
        <div v-show="stepActive === 3">
          <el-result
            icon="success"
            title="操作成功"
            sub-title="预计两个小时内到账，以银行实际到账时间为准！"
          >
            <template #extra>
              <el-button type="primary">返回首页</el-button>
              <el-button type="default">返回列表</el-button>
            </template>
          </el-result>
        </div>
        <el-form-item>
          <el-button style="margin-top: 12px" @click="prev" v-if="stepActive > 0 && stepActive < 3"
            >上一步</el-button
          >
          <el-button type="primary" style="margin-top: 12px" @click="next" v-if="stepActive < 3"
            >下一步</el-button
          >
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.stepCard {
  display: flex;
  justify-content: space-around;
  padding-top: 20px;

  .stepForm {
    margin-top: 50px;
    width: 800px;
    display: flex;
    flex-direction: column;
  }
}

.mytable {
  width: 700px;
  margin: 0 auto;
  border: 1px solid #e8e8e8;
  border-collapse: collapse;
  margin-top: 20px;
  font-size: 14px;
  margin-bottom: 20px;
}

.mytable td {
  height: 18px;
  padding: 10px;
  border: 1px solid #e8e8e8;
}
</style>
