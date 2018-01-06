# 151220129 计科 吴政亿

## 一、代码框架

| File Name           | File Information                           |
| ------------------- | ------------------------------------------ |
| Main                | 包含了整个程序的main函数，执行操作的模块   |
| loginUI             | 开始界面的UI                               |
| nju                 | 实现的葫芦娃的包                           |
| nju.java.attribute  | 定义了生物/战场等应有的接口                |
| nju.java.Creature   | 定义了生物类Creature(基类)与各个生物(子类) |
| nju.java.field      | 定义了生物作战的战场                       |
| nju.java..formation | 定义了一系列阵型接口                       |
| nju.java.record     | 定义了保存战斗录像回放的类                 |

## 二、类的关系

![CodeStruction](http://img.blog.csdn.net/20180106193107616?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvanVzdGljZTA=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 三、代码详解

### Main

程序的主类

### attribute

| Class name   | Class information                                                       |
| ------------ | ----------------------------------------------------------------------- |
| Action       | 定义了Creature可以执行动作的接口,包含是否可以行动与向某个方向前进       |
| Attack       | 定义了Creature可以的进攻行为，包含攻击敌人与向敌人移动                  |
| Camp         | 定义了一个阵营的枚举变量，包含正方，反方与中立                          |
| Direction    | 定义了前进方向的枚举变量，包含东南西北中                                |
| Fighters     | 定义了Field的战斗者们的接口，包含判断阵营，添加战斗者与战斗结束等方法   |
| ObserveField | 定义了Field的战斗者们观察战场接口，包含寻找地方势力与观察四周生物等方法 |

### Creature

| Class Name | Class Information                                                 |
| ---------- | ----------------------------------------------------------------- |
| Creatrue   | Thing2D的派生类，以下生物的基类，拥有Runnable, Action, Attack接口 |
| Calabash   | 葫芦娃                                                            |
| Grandpa    | 爷爷                                                              |
| Minion     | 小兵                                                              |
| Scorpion   | 蝎子精                                                            |
| Snake      | 蛇精                                                              |

### field

| Class Name | Class Information                                                      |
| ---------- | ---------------------------------------------------------------------- |
| Field      | 定义了一个战场，拥有ObserveField, Fighters, BluntYokeVSCraneWing等接口 |
| Fight      | 定义了一个打架现场，只有双方都存活并且不在打架而且敌对才可以打架       |
| Ground     | 战场下厚重的大地，是世界的基础                                         |
| Thing2D    | 二位生物的抽象类                                                       |
| Tile       | 战场下要有一点草才好看                                                 |

### formation

定义了双方各种阵型的接口，令field implements 不同的formation就可以更换阵型


### record

| Class                  | Information                  |
| ---------------------- | ---------------------------- |
| MyDialog               | 选择是否存储当局录像的对话框 |
| WnetWSreenRecorder     | 接受一个Rectangle录像        |
| WnetWSreenRecordPlayer | 播放一个录像                 |

## 四、使用手册

- 进入界面后，选择开始游戏后弹出一个对话框选择时候保存当前录像。保存的录像将在当前文件下的records中，并且文件夹名是以时间命名的，用来避免重复。
- 游戏中，摁下`space键`开始游戏，摁下`r`键游戏重新开始。
- 主界面选择回放后，请选择你想看的战斗的文件夹，打开的是默认路径，请确保选择正确的战斗文件哦
- 关闭游戏很简单，选择退出游戏或者简单粗暴的右(左)上角的`x`即可。

## 五、代码思路

- 应用范型和接口来为战场field提供一场战役，他的参赛者们分为两类，爷爷为首的goodCreature，蛇精为首的badCreture，通过绕嘴的自限定来保证他的类型是Creature或者是他的派生类。
- Creature是Thing2D的派生类，因为在我的世界里，一切生物都是二维的，包括土地上的草皮。
- Creature实现了行动，进攻等接口，因为我的世界里的生物他们都会打架和移动emmmm。
- 每一个Creature进攻时，需要先看看是否到了自己的回合，然后在field上找离自己最近的敌人来得到自己的进攻目标，再四处张望一下怎么前进比较快，然后再调用自身方法向那个方向移动。
- field中有一个God，他保持中立并且作为战场的裁判，每过一会就看一看打架打完了没有，如果有一方阵营全死了，他就宣布胜利者并且打出Completed。
- 战斗录像是通过录屏操作实现的，每隔一定时间就捕捉战场的信息并且拿小本本记下来，保存成一张png图片仍在records里面。
- 战斗回放就是让用户自己选择记仇的小本本，然后Frame开始每隔一定时间就把照片替换成下一帧。
- 在field里为GetDistance特地编写了一下测试用例，用来测试一下计算每一对欢喜冤家的距离是否准确。
- 战场双方的阵型被我抽象成了一个接口，只需要在attribute里创建你心中的钟意的阵型，然后将field里实现的接口改成你钟意的阵型即可。
- 战斗开始后，每一个战斗者都被我扔进了线程池里面，为了保证同步问题(在每一个生物眼中，每一个敌人的存活状态都的一样啊！)，为其中涉及到field里实现的ObersveField加上了同步锁。
- 应用**接口隔离原则**来使自己的代码有了更多复用和补漏的可能，field与creture实现了强关联，双方互相持有一个引用，通过**组合模式**来保证双方的密切关联，再通过割离UI与实现，使代码更加清晰易懂。

## 六、心得体会

- 多线程带来的同步问题，还是有点难搞的。
- 保存战斗记录如果选择保存对象，那么涉及到各种类的指针引用有点烦，所以选择了直接录屏。
- maven一直无法快乐的打包，最后查了半天发现是自己的pom.xml写的不太好。例如`无法加载huluwa因为找不到主类Main`最后发现是因为pom.xml里面<classmain>填写有误(路径不对)。
- 觉的本学期对面向对象的理解更加深刻了，以前是因为必须用面向对象语法而面向对象，现在开始思索生物有什么属性，生物有什么方法，什么方法是生物本身不具有的等等。