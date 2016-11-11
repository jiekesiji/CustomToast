# CustomToast
Customize your own Toast，it can be used in every thread。 

![](http://i.imgur.com/qVIS3K7.gif)

## how to use ##

  first: Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
	
 second:Add the dependency

	dependencies {
		compile 'com.github.jiekesiji:CustomToast:v1.2.2'
	}

# 代码示例： #
	
	//强制传入的context必须是ApplicationContext，不然程序报异常
       CustomToast.makeToast(mContext, content)
                .setTextColor(Color.WHITE)
                .setTextSize(18)
                .setPadding(32, 24, 32, 24)
                .setTextImage(iconResId)
				// 这两个方法有先后顺序
                .setTextImageSize(80, 80)
                .setTextImageLocation(CustomToast.LEFT)

                .setImagePadding(50)
                .setBackgroundColor(Color.parseColor("#" + bgColor))
                .setBackgroundRadius(10)
                .setGravity(Gravity.BOTTOM, 0, 100);


# 方法说明： #

<div>
    <table border="0">
      <tr>
        <th>方法</th>
        <th>功能</th>
      </tr>
      <tr>
        <td>setTextImageLocation(100,100)  </td>
        <td>设置Toast位置，单位px，相对屏幕中心位置</td>
      </tr>
      <tr>
        <td>setBackgroundColor(Color.parseColor("#88000000"))</td>
        <td>  设置背景，参数1背景颜色，参数2圆角弧度</td>
      </tr>
      <tr>
        <td>setPadding(20,20,20,20)</td>
        <td> 设置内边距</td>
      </tr>
      <tr>
        <td>setTextColor(Color.BLUE)</td>
        <td> 设置字体颜色</td>
      </tr>
      <tr>
        <td>setTextSize(32) </td>
        <td>设置字体大小</td>
      </tr>
      <tr>
        <td>setTextImage(R.mipmap.ic_launcher)</td>
        <td>设置想要添加的图片</td>
      </tr>
		<tr>
        <td>setDuration(Toast.LENGTH_LONG) </td>
        <td> 显示Toast时长，精确到毫秒，能够完全自定义</td>
      </tr>
      <tr>
        <td>showMToast(Toast.LENGTH_LONG) </td>
        <td> 显示Toast</td>
      </tr>
		<tr>
        <td>clearSetting()</td>
        <td>清除之前设置</td>
      </tr>     
    </table>
</div>


# 注意： #
	
1. 传入的上下文必须是ApplicationContext，不然回报异常
2. 建议设置玩text相关属性，再设置image属性
3. 先设置图片大小在设置图片位置，不然会报错。



