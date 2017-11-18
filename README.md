- 查找activity类上的注解的时候，要递归遍历到所有的父类，放入集合，第一个元素就是最子类的注解，对于setContentView，使用第一个就行
- 查找类里的view的注解，也要递归遍历所有父类中的所有View
- 获取注解的注解             
`Event event = annotation.annotationType().getAnnotation(Event.class);`


# 可以在EventType中添加新的事件
```java
public enum EventType {
    @BaseEvent(setListenerMethodName = "setOnClickListener", listener = View.OnClickListener.class, callBackMethodName = "onClick")
    Click,
    @BaseEvent(setListenerMethodName = "setOnLongClickListener", listener = View.OnLongClickListener.class, callBackMethodName = "onLongClick")
    LongClick,
    @BaseEvent(setListenerMethodName = "addTextChangedListener", listener = TextWatcher.class, callBackMethodName = "onTextChanged")
    EDITTEXT_TEXT_CHANGE
}
```
# 事件注入
> 动态代理：一句话概括就是，接口的方法被调用的时候，都可以被拦截到，然后就可以为所欲为了


对事件的注入其实就是：
- 正常情况下，为View设置一个事件的接口（OnClickListener），当用户触发一个事件的时候，接口方法就会被调用，通常情况下，我们只能在方法内知道这个方法被调用
- 我们想要在知道接口方法何时被调用了，所以就拦截这个方法，当他被调用的时候。
    - 给View设置这个接口对象的时候，不要设置直接设置，而是给他设置这个接口的代理对象
    - 这样当用户触发这个接口方法的时候，代理处理者会知道，然后就可以为所欲为了
    - 例如，让handler持有activity的引用就可以通知activity了
- 所以，其实这里跟activity是没有什么关系的
