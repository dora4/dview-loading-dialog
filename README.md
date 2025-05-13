dview-loading-dialog
![Release](https://jitpack.io/v/dora4/dview-loading-dialog.svg)
--------------------------------

#### 卡片
![DORA视图 时涡回转者](https://github.com/user-attachments/assets/d3478212-e3d8-49d9-9c97-4876de6098f7)

#### Gradle依赖配置

```groovy
// 添加以下代码到项目根目录下的build.gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
// 添加以下代码到app模块的build.gradle
dependencies {
    implementation 'com.github.dora4:dview-loading-dialog:1.5'
}
```

#### 使用控件

```kotlin
val dialog = DoraLoadingDialog(this).show("登录中...") {
            messageTextSize(15f)
        }
        Handler().postDelayed({
            dialog.dismissWithAnimation()
        }, 1000)
```

#### 示例代码

https://github.com/dora4/dora_samples
