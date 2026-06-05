# CloudApp - 云端开发 Android 项目

零本地依赖的安卓开发模板：在 **GitHub Codespaces** 里写代码，**GitHub Actions** 自动打包 APK。

## 这是什么

一个开箱即用的 Android 项目模板（Kotlin + AppCompat），配置好了：

- **`.devcontainer/`** - GitHub Codespaces 配置，浏览器里打开就有完整 Android SDK + Gradle + Kotlin 环境
- **`.github/workflows/android.yml`** - 推送代码自动云端构建 Debug APK，可在 Actions 页面下载
- **完整 Android 项目结构** - 一个能编译运行的 Hello World 应用

## 部署步骤（5 分钟）

### 1. 推送到 GitHub

```bash
cd android-cloud-dev
git init
git add .
git commit -m "Initial commit: Android cloud dev template"
git branch -M main

# 在 GitHub 网页上新建一个空仓库(不要勾选 README/gitignore),复制 URL 后:
git remote add origin https://github.com/<你的用户名>/<仓库名>.git
git push -u origin main
```

### 2. 等待第一次自动构建

推送后打开 GitHub 仓库的 **Actions** 标签页：

- 会看到 "Build Android APK" 正在跑（约 5-8 分钟）
- 跑完后点进去，最下面 **Artifacts** 区域可以下载 `CloudApp-debug-1.apk`
- 把 APK 传到安卓手机安装（需要开启"未知来源"安装权限）

### 3. 启动 Codespaces 在线开发

仓库主页右上角绿色按钮 **Code** → **Codespaces** → **Create codespace on main**

- 等待 2-3 分钟初始化（自动装 Android SDK、Gradle、Kotlin 插件）
- 完成后是浏览器版 VS Code，左侧文件浏览器，底部终端
- 改代码、保存 → 推送 → Actions 自动出新 APK

### 4. 本地构建（可选）

Codespaces 终端里：

```bash
./gradlew assembleDebug
# APK 在 app/build/outputs/apk/debug/app-debug.apk
```

## 项目结构

```
android-cloud-dev/
├── .devcontainer/
│   ├── devcontainer.json   # Codespaces 容器配置
│   └── setup.sh             # 容器启动后的初始化脚本
├── .github/workflows/
│   └── android.yml          # CI 工作流(构建 APK)
├── app/
│   ├── build.gradle.kts     # 模块构建脚本
│   ├── proguard-rules.pro
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/cloudapp/
│       │   └── MainActivity.kt
│       └── res/             # 布局、字符串、主题、图标
├── gradle/wrapper/
│   └── gradle-wrapper.properties  # 版本约束(jar 自动生成)
├── build.gradle.kts         # 根构建脚本
├── settings.gradle.kts
├── gradle.properties
├── .gitignore
└── README.md
```

## 改 App 的快速指南

| 想做的事 | 改哪个文件 |
|---|---|
| 改 App 名 | `app/src/main/res/values/strings.xml` 中 `app_name` |
| 改界面文字 | `app/src/main/res/values/strings.xml` |
| 改界面布局 | `app/src/main/res/layout/activity_main.xml` |
| 改主题颜色 | `app/src/main/res/values/colors.xml` |
| 改业务逻辑 | `app/src/main/java/com/example/cloudapp/MainActivity.kt` |
| 加新页面 | 在 `java/com/example/cloudapp/` 新建 Activity + 在 manifest 注册 |
| 加第三方库 | `app/build.gradle.kts` 的 `dependencies { }` 块 |
| 改包名/应用 ID | `app/build.gradle.kts` 中 `namespace` 和 `applicationId` |

## 常见问题

**Q: Actions 构建失败怎么办？**
A: 点进失败的 run，看红色 ✗ 的步骤日志。最常见原因是 Kotlin/Gradle 语法错误。

**Q: 怎么发布签名版（Release APK）？**
A: 在 GitHub 仓库 Settings → Secrets 里加入 keystore 文件（base64）和密码，然后在 workflow 里增加 release 构建步骤。需要时告诉我可以加配置。

**Q: 怎么在浏览器里跑模拟器？**
A: GitHub Codespaces 没有 GPU/KVM，跑不了 Android 模拟器。建议：
- 改完代码 → 推送 → Actions 出 APK → 手机安装测试
- 或用 Firebase Test Lab 云端真机（每天免费 5 次）

**Q: 免费额度够用吗？**
A: GitHub 个人账号每月：
- Codespaces：120 核小时（2 核机器 = 60 小时）
- Actions：公开仓库无限，私有仓库 2000 分钟（每次构建约 5 分钟，够 400 次）

**Q: 项目跑不起来 / 想用 Compose？**
A: 这个模板用的是传统 XML 视图（更稳定）。想换 Jetpack Compose 告诉我可以重写。
