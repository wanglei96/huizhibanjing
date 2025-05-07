import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      imports: ['vue', 'vue-router'], // 自动导入vue相关函数，如：ref，reactive， toRef等
      resolvers: [
        ElementPlusResolver()
        // 自动引入图标组件
        // IconsResolver({
        //   prefix: 'Icon'
        // })
      ],
      eslintrc: { enabled: true } //eslint 报 no-undef 错误，添加此项会生成.eslintrc-auto-import.json 文件
    }),
    Components({
      resolvers: [
        ElementPlusResolver()
        // 自动注册图标组件
        // IconsResolver({
        //   enabledCollections: ['ep']
        // })
      ]
    }),
    Icons({
      autoInstall: true
    })
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  build: {
    chunkSizeWarningLimit: 1500, // 调整包的大小
    rollupOptions: {
      output: {
        // 最小化拆分包
        manualChunks(id) {
          if (id.includes('node_modules')) {
            return id.toString().split('node_modules/')[1].split('/')[0].toString()
          }
        },
        // 用于从入口点创建的块的打包输出格式[name]表示文件名,[hash]表示该文件内容hash值
        entryFileNames: 'assets/js/[name].[hash].js', // 用于命名代码拆分时创建的共享块的输出命名
        chunkFileNames: 'assets/js/[name].[hash].js', // 用于输出静态资源的命名，[ext]表示文件扩展名
        assetFileNames: 'assets/[ext]/[name].[hash].[ext]'
      }
    }
  },

  // 配置代理服务器
  server: {
    proxy: {
      '/api/Service': {
        target: 'http://localhost:8080/api/Service',
        ws:true,
        changeOrigin:true
      },
      '/front': {
        target: 'http://39.97.218.60/'
      },
      '/boss': {
        target: 'http://39.97.218.60/'
      }
    }
  }
})
