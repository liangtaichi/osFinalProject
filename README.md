# **计算机操作系统课程设计**

## 一、**课程设计目的**

操作系统是计算机系统配置的基本软件之一。它在整个计算机系统软件中占有中心地位。其作用是对计算机系统进行统一的调度和管理，提供各种强有力的系统服务，为用户创造既灵活又方便的使用环境。本课程是计算机及应用专业的一门专业主干课和必修课。
   通过课程设计,使学生掌握操作系统的基本概念、设计原理及实施技术,具有分析操作系统和设计、实现、开发实际操作系统的能力。

## **二、课程设计内容和要求**

用高级语言编写程序，模拟实现一个简单功能的操作系统。

l 提交一批作业（>=10），按先来先服选择一部分作业（最多5个）进入内存

l 为每个作业创建一个进程，并分配内存（用户内存：0—1024K，采用可变连续分配方式）

l 进程调度功能（时间片轮转）

l 随机阻塞进程，并在一段时间后唤醒进程（选做）

l 显示相关信息：后备作业队列、内存分配情况、进程信息、完成作业情况

l 这些功能要有机地连接起来

## **三、软、硬件环境**

软件环境：IntelliJ IDEA 2020.1.1 x64（为了练习自己的java链表操作，本次用java语言实现）

硬件环境： CPU：Intel [i5-8300H@2.30GHz](mailto:i5-8300H@2.30GHz)

## **四、设计步骤**

1. **基本模块：**

  本次课程设计根据要求可分为以下几个模块：

提交作业需要一个自动生成作业的模块、先来先服务算法模块、内存分配算法模块。

作业运行需要时间片轮转法模块、阻塞模块。

而为了更好的了解到运行时发生了什么，需要一个输出模块，显示当前状态。

以图片的角度总结，可得出下图：

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image006.gif)

2. **模块的分别实现：**

   * 数据结构的设计：

   A．PCB的数据结构：

   * 本次使用的是先来先服务算法，所以不需要优先级这一数据，需要的是进程名称、所需运行时间、抵达时间、运行时间、所需内存等几个数据即可。同时建立修改或者输出类中信息的接口（格式都是get/set+需要改动信息）。


![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image008.gif)

   B.  内存分配模块数据结构实现：

   * 本次课程设计，内存分区节点有分区大小、分区开始地址、占有该分区的进程名称、分区是否空闲等信息。

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image010.gif)

   同时，用linkedlist将多个zone链接起来，形成链表。

   C．时间片轮转法的数据结构实现：

   * 时间片轮转法用多个队列Queue来实现，一个存放所有进程，一个存放抵达进程、一个存放执行完毕的进程。

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image012.gif)

\2. 重要方法的实现：

A．最佳适应算法：给定进程需要内存大小与进程名称，总是将进程所需内存空间分配到能满足要求但是最小的空间。

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image014.gif)

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image016.gif)

B．时间片轮转法的实现：

 设置三条队列，每隔一定时刻查看是否有新的进程到达，如果到达，并且内存充足，将到达的进程从原来的队列中取出，加入运行队列，同时，如果这个时间片进程运行结束，将进程放到运行结束队列。同时，用一个while循环限制ReadyQueue的大小小于5，就实现了题目中的“按先来先服选择一部分作业（最多5个）进入内存”的要求。（也就是说“只允许五个作业进入内存”这一任务并不是在内存分配模块实现的）

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image018.gif)

 

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image020.gif)

C．先来先服务算法实现：

实质上就是一个利用PriorityQueue里面Comparator的自动排序功能，基于到达时间对所有进程进行排序。其中，如果不是用while循环用！queue.isEmpty条件实现，而采用for循环，循环条件为i<queue.size，很容易有数据没有加入队列，导致程序出错。

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image022.gif)

**3.** **结果：**

特别说明：

A．内存分配显示中，“空闲状态显示 ”，true为空闲，false为忙碌，Free空间虽然显示False，但是实质上是空闲空间的一种。

B．本次截图时所有进程运行完毕需要100多秒，中间每个时间片（即每隔3秒）都会显示整个内存分配空间的情况，截图过多，所以贴图时会省略一部分。

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image024.gif)

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image026.gif)

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image028.gif)

v ![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image030.gif)

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image032.gif)

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image034.gif)

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image036.gif)

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image038.gif)

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image040.gif)

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image042.gif)

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image044.gif)

![img](file:///C:/Users/liang/AppData/Local/Temp/msohtmlclip1/01/clip_image046.gif)