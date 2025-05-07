<script setup>
const activeName = ref('first')
const userForm = reactive({
  nickname: '超级管理员',
  sex: '男',
  email: 'admin@eleadmin.cn',
  desc: '这家伙很懒，啥也没留下',
  address: '',
  phone: ''
})
</script>
<template>
  <div>
    <el-row >
      <el-col :span="6">
        <el-card shadow="never" class="baseCard">
          <div style="width: 80px; height: 80px; margin: 0 auto; margin-bottom: 15px">
            <el-avatar
              style="width: 80px; height: 80px"
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            />
          </div>
          <div style="text-align: center; font-size: 20px">超级管理员</div>
          <div style="font-size: 14px; color: #999; margin-top: 5px; text-align: center">
            努力能有收获，人人都能成功
          </div>
          <div class="base_list">
            <div class="base_list_item">
              <el-icon><User /></el-icon>
              <span>资深前端工程师</span>
            </div>
            <div class="base_list_item">
              <el-icon><OfficeBuilding /></el-icon>
              <span>Eleadmin.cn 科技有限公司</span>
            </div>
            <div class="base_list_item">
              <el-icon><View /></el-icon>
              <span>年龄：42岁</span>
            </div>
            <div class="base_list_item">
              <el-icon><Location /></el-icon>
              <span>中国 · 北京</span>
            </div>
            <div class="base_list_item">
              <el-icon><Sunny /></el-icon>
              <span>让生活每天充满阳光</span>
            </div>
          </div>
          <el-divider border-style="dashed" />
          <div>技术标签</div>
          <div class="tags">
            <el-tag class="ml-2" type="info">Vue3</el-tag>
            <el-tag class="ml-2" type="info">Vite</el-tag>
            <el-tag class="ml-2" type="info">Pinia</el-tag>
            <el-tag class="ml-2" type="info">Javascript</el-tag>
            <el-tag class="ml-2" type="info">TypeScript</el-tag>
            <el-tag class="ml-2" type="info">Axios</el-tag>
            <el-tag class="ml-2" type="info">ElementPlus</el-tag>
          </div>
        </el-card>
      </el-col>
      <el-col :span="18">
        <el-card shadow="never">
          <el-tabs v-model="activeName" class="demo-tabs">
            <el-tab-pane label="基本信息" name="first">
              <el-row style="margin-top: 20px">
                <el-col :span="12">
                  <el-form :model="userForm" label-width="120px">
                    <el-form-item label="昵称">
                      <el-input v-model="userForm.nickname"></el-input>
                    </el-form-item>
                    <el-form-item label="性别">
                      <el-select v-model="userForm.sex" placeholder="请选择性别">
                        <el-option label="男" value="男"></el-option>
                        <el-option label="女" value="女"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="电子邮箱">
                      <el-input v-model="userForm.email"></el-input>
                    </el-form-item>
                    <el-form-item label="个人简介">
                      <el-input v-model="userForm.desc" type="textarea" />
                    </el-form-item>
                    <el-form-item label="街道地址">
                      <el-input v-model="userForm.address"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话">
                      <el-input v-model="userForm.phone"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <el-button type="primary">保存资料</el-button>
                    </el-form-item>
                  </el-form>
                </el-col>
              </el-row>
            </el-tab-pane>
            <el-tab-pane label="安全认证" name="second">
              <div class="list_container">
                <div class="list_item">
                  <div class="list_info">
                    <div>密保手机</div>
                    <div>已绑定手机号：13800000000</div>
                  </div>
                  <div class="list_btn">
                    <el-button type="primary" text>去修改</el-button>
                  </div>
                </div>
                <div class="list_item">
                  <div class="list_info">
                    <div>密保邮箱</div>
                    <div>已绑定电子邮箱：admin@eleadmin.cn</div>
                  </div>
                  <div class="list_btn">
                    <el-button type="primary" text>去修改</el-button>
                  </div>
                </div>
                <div class="list_item">
                  <div class="list_info">
                    <div>密保问题</div>
                    <div>您的出生地在哪里？</div>
                  </div>
                  <div class="list_btn">
                    <el-button type="primary" text>去修改</el-button>
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="绑定第三方应用" name="third">
              <div class="list_container">
                <div class="list_item">
                  <div class="list_info2">
                    <div class="list_icon2">
                      <img src="@/assets/images/icons/icon_wx.png" alt="" />
                    </div>
                    <div>
                      <div class="list_info2_title">绑定微信</div>
                      <div class="list_info2_subtitle">您还没有绑定微信账号</div>
                    </div>
                  </div>
                  <div class="list_btn">
                    <el-button type="primary" text>去绑定</el-button>
                  </div>
                </div>
                <div class="list_item">
                  <div class="list_info2">
                    <div class="list_icon2">
                      <img src="@/assets/images/icons/icon_qq.png" alt="" />
                    </div>
                    <div>
                      <div class="list_info2_title">绑定QQ</div>
                      <div class="list_info2_subtitle">您还没有绑定QQ账号</div>
                    </div>
                  </div>
                  <div class="list_btn">
                    <el-button type="primary" text>去绑定</el-button>
                  </div>
                </div>
                <div class="list_item">
                  <div class="list_info2">
                    <div class="list_icon2">
                      <img src="@/assets/images/icons/icon_zfb.png" alt="" />
                    </div>
                    <div>
                      <div class="list_info2_title">绑定支付宝</div>
                      <div class="list_info2_subtitle">您还没有绑定支付宝账号</div>
                    </div>
                  </div>
                  <div class="list_btn">
                    <el-button type="primary" text>去绑定</el-button>
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.baseCard {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.base_list {
  padding: 20px;
  margin-top: 30px;
  .base_list_item {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    margin-bottom: 10px;
    font-size: 14px;
    span {
      padding-left: 8px;
    }
  }
}
.tags {
  margin-top: 20px;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;

  .el-tag {
    width: 100px;
    min-height: 25px;
    height: auto !important;
    white-space: normal;
    margin-left: 10px;
    margin-right: 10px;
    margin-bottom: 10px;
  }
}
.list_container {
  padding: 10px;
  font-size: 14px;

  .list_item {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-bottom: 20px;
    border-bottom: 1px solid #ccc;
    padding-bottom: 10px;

    .list_info div:first-child {
      font-size: 16px;
    }

    .list_info div:last-child {
      margin-top: 5px;
      color: #999;
      font-size: 13px;
    }

    .list_icon {
      width: 45px;
      height: 45px;
    }
  }

  .list_info2 {
    display: flex;
    .list_icon2 {
      margin-right: 10px;
    }
  }
  .list_info2_title {
    font-size: 16px;
  }
  .list_info2_subtitle {
    margin-top: 5px;
    color: #999;
    font-size: 13px;
  }
}
</style>
