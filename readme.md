# 1. 学习Git工作流

1. ryan 
- ryan是checkout master之后做自己的事情
- ryan在自己的本地commit了多次

2. lio
- lio是checkout master之后干自己的事情
- lio提交了多次
- lio push 到master

3. ryan push
- ryan先fetch master
- ryan merge master
- ryan 本地分支已经是最新的，而且比master 高一个版本， 但从git log上看到的结构是：按照时间顺序的commit，也就是ryan的commit和lio的commit交错。这样可以清晰的看到时间提交结构。
- ryan push 到一个origin/ryan 分支

