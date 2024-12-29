dview-loading-dialog
![Release](https://jitpack.io/v/dora4/dview-loading-dialog.svg)
--------------------------------

##### 卡名：Dora视图 Loading Dialog
###### 卡片类型：效果怪兽
###### 属性：光
###### 星级：2
###### 种族：天使族
###### 攻击力/防御力：100/1000
###### 效果：此卡不会因为对方卡的效果而破坏，并可使其无效化。此卡攻击里侧守备表示的怪兽时，若攻击力高于其守备力，则给予对方此卡原攻击力的伤害，并抽一张卡。每当此卡攻击破坏对方怪兽送去墓地时，你可以破坏对方场上一张魔法或陷阱卡。此卡名字可以当作任何名字带有「Dora视图」的怪兽使用。

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
