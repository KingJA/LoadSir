常见问题
---

如果下列问题中，你有更好的解决方法，欢迎对docs/FAQ-cn.md 发起pull request。

### #1 如何和ButterKnife配合使用?

in Activity

```java

public class BufferKnifeActivity extends AppCompatActivity {
    @BindView(R.id.tv_msg)
    TextView mTv_msg;
    private LoadService loadService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new EmptyCallback())
                .build();
        loadService = loadSir.register(this, new Callback.OnReloadListener() {
           @Override
           public void onReload(View v) {
              //TODO
           }

       });
        loadService.showCallback(EmptyCallback.class);
    }
}

```

in Fragment

```java
@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View rootView = View.inflate(getActivity(), R.layout.fragment_bk, null);
        ButterKnife.bind(this, rootView);

        LoadSir loadSir = new LoadSir.Builder()
                .addCallback(new EmptyCallback())
                .build();
        loadService = loadSir.register(rootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
              //TODO
            }

        });
        return loadService.getLoadLayout();
    }
```

### #2 如何在自定义状态页上添加标题栏以模仿原布局的标题栏
由于标题栏的样式多种多样，为了降低耦合没有对该需求提供扩展，如果有这方面需求的同学可以利用View注册的方式，对标题栏以下
的布局进行注册，这样就保留了标题栏，如果大家有更好的思路请告诉我，或发起pull request。








