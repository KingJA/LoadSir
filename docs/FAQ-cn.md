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

### #2 如果保留原布局的标题栏(toolbar,或者titileView)?
在Activity，在Fragment中，只要注册toolbar,或者titileView以下的布局View即可，这样LoadSir就会保留标题栏。
在Fragment，请看参考代码:
```java
@Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    LinearLayout contentView = (LinearLayout) rootView.findViewById(R.id.ll_content);
    LoadSir loadSir = new LoadSir.Builder()
            .addCallback(new EmptyCallback())
            .addCallback(new LoadingCallback())
            .setDefaultCallback(LoadingCallback.class)
            .build();
    loadService = loadSir.register(contentView, new Callback.OnReloadListener() {
        @Override
        public void onReload(View v) {
            loadService.showSuccess();
        }
    });
    PostUtil.postCallbackDelayed(loadService, EmptyCallback.class, 1200);
}
```








