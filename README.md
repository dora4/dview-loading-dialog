dview-loading-dialog
![Release](https://jitpack.io/v/dora4/dview-loading-dialog.svg)
--------------------------------

#### gradle依赖配置

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
