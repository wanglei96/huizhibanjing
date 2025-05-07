<template>
  <main>
    <editor v-model="myValue" :init="init" :disabled="disabled" :id="tinymceId"></editor>
  </main>
</template>
<script setup>
import { reactive, ref, onMounted, watch } from 'vue'
import axios from 'axios'
import tinymce from 'tinymce/tinymce'
// import 'tinymce/skins/content/default/content.css'
import Editor from '@tinymce/tinymce-vue'
import 'tinymce/themes/silver'
import 'tinymce/themes/silver/theme'
import 'tinymce/icons/default'
import 'tinymce/models/dom'

import 'tinymce/icons/default/icons'
import 'tinymce/plugins/table'
import 'tinymce/plugins/lists'
import 'tinymce/plugins/image'
import 'tinymce/plugins/wordcount'
import 'tinymce/plugins/fullscreen'
import 'tinymce/plugins/preview'
import 'tinymce/plugins/searchreplace'
import 'tinymce/plugins/autolink'
import 'tinymce/plugins/advlist'
import 'tinymce/plugins/code'
import 'tinymce/plugins/anchor'
import 'tinymce/plugins/autoresize'
import 'tinymce/plugins/autosave'
import 'tinymce/plugins/charmap'
import 'tinymce/plugins/codesample'
import 'tinymce/plugins/directionality'
import 'tinymce/plugins/importcss'
import 'tinymce/plugins/insertdatetime'
import 'tinymce/plugins/link'
import 'tinymce/plugins/importcss'
import 'tinymce/plugins/media'
import 'tinymce/plugins/nonbreaking'
import 'tinymce/plugins/pagebreak'
import 'tinymce/plugins/quickbars'
import 'tinymce/plugins/save'
import 'tinymce/plugins/visualblocks'
import 'tinymce/plugins/visualchars'

const emits = defineEmits(['getContent'])
const props = defineProps({
  value: {
    type: String,
    default: () => {
      return ''
    }
  },
  baseUrl: {
    type: String,
    default: ''
  },
  disabled: {
    type: Boolean,
    default: false
  },
  plugins: {
    type: [String, Array],
    default: `lists 
    table 
    image 
    wordcount 
    fullscreen 
    preview 
    searchreplace 
    autolink 
    advlist 
    anchor 
    autosave 
    charmap 
    codesample 
    directionality 
    importcss 
    insertdatetime 
    link 
    importcss 
    media 
    nonbreaking 
    pagebreak 
    quickbars 
    save 
    visualblocks 
    visualchars 
    code 
    `
  }, //必填
  toolbar: {
    type: [String, Array],
    default: `code fullscreen codesample bold italic underline alignleft aligncenter alignright alignjustify | 
      undo redo | 
      fontfamily fontsize blocks |
      formatselect | 
      forecolor backcolor | 
      bullist numlist outdent indent | 
      lists link table code | 
      removeformat | 
      image media |
      preview
      `
  } //必填
})
//用于接收外部传递进来的富文本
const myValue = ref(props.value)
const tinymceId = ref('vue-tinymce-' + +new Date() + ((Math.random() * 1000).toFixed(0) + ''))
//定义一个对象 init初始化
const init = reactive({
  selector: '#' + tinymceId.value, //富文本编辑器的id,
  language_url: '/tinymce/langs/zh_CN.js', // 语言包的路径，具体路径看自己的项目，文档后面附上中文js文件
  language: 'zh_CN', //语言
  skin_url: '/tinymce/skins/ui/oxide', // skin路径，具体路径看自己的项目
  height: 500, //编辑器高度
  branding: false, //是否禁用“Powered by TinyMCE”
  menubar: true, //顶部菜单栏显示
  image_dimensions: false, //去除宽高属性
  plugins: props.plugins, //这里的数据是在props里面就定义好了的
  toolbar: props.toolbar, //这里的数据是在props里面就定义好了的
  ffont_family_formats:
    'Arial=arial,helvetica,sans-serif; 宋体=SimSun; 微软雅黑=Microsoft Yahei; Impact=impact,chicago;', //字体
  font_size_formats: '11px 12px 14px 16px 18px 24px 36px 48px 64px 72px', //文字大小
  // fontsize_formats: '8pt 10pt 12pt 14pt 18pt 24pt 36pt', // 第二步
  // paste_convert_word_fake_lists: false, // 插入word文档需要该属性
  paste_webkit_styles: 'all',
  paste_merge_formats: true,
  nonbreaking_force_tab: false,
  paste_auto_cleanup_on_paste: false,
  file_picker_types: 'file',
  promotion: false,
  content_css: '/tinymce/skins/content/default/content.css', //以css文件方式自定义可编辑区域的css样式，css文件需自己创建并引入
  //图片上传
  images_upload_handler: (blobInfo, progress) =>
    new Promise((resolve, reject) => {
      if (blobInfo.blob().size / 1024 / 1024 > 2) {
        reject({ message: '上传失败，图片大小请控制在 2M 以内', remove: true })
        return
      } else {
        const ph = import.meta.env.VITE_BASE_PATH + ':' + import.meta.env.VITE_SERVER_PORT + '/'
        let params = new FormData()
        params.append('file', blobInfo.blob())

        let config = {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }
        axios
          .post('xxxx', params, config)
          .then((res) => {
            if (res.data.code == 200) {
              resolve(ph + res.data.msg) //上传成功，在成功函数里填入图片路径
            } else {
              reject('HTTP Error: 上传失败' + res.data.code)
              return
            }
          })
          .catch(() => {
            reject('上传出错，服务器开小差了呢')
            return
          })
      }
    }),
  // 文件上传
  file_picker_callback: (callback, value, meta) => {
    // Provide file and text for the link dialog
    if (meta.filetype == 'file') {
      callback('mypage.html', { text: 'My text' })
    }

    // Provide image and alt text for the image dialog
    if (meta.filetype == 'image') {
      callback('myimage.jpg', { alt: 'My alt text' })
    }

    // Provide alternative source and posted for the media dialog
    if (meta.filetype == 'media') {
      callback('movie.mp4', { source2: 'alt.ogg', poster: 'image.jpg' })
    }
  }
})
//监听外部传递进来的的数据变化
watch(
  () => props.value,
  () => {
    myValue.value = props.value
    emits('getContent', myValue.value)
  }
)
//监听富文本中的数据变化
watch(
  () => myValue.value,
  () => {
    emits('getContent', myValue.value)
  }
)
//在onMounted中初始化编辑器
onMounted(() => {
  tinymce.init({})
})
</script>
