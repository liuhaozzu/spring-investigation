# Domain Driven Design
## validate 内聚
1. 何时进行校验
    1. 从数据库中加载已有模型
    2. 模型新创建后
    3. 经过一系列的模型操作后，模型被最终持久化时
    
    其中1、2可以在对象工厂中校验；  
    针对3，引入一个新的模式：工作单元
    