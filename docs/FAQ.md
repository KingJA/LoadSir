FAQ
---

If you have better solutions about the questions below, welcome to push pull requests to me.

### #1 How to use with ButterKnife?

* ###### in Activity

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

* ###### in Fragment

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

### #2 How to keep the toolbar in Fragment?
```java
@Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    //fetch contentView View，the View except toolbar or titleView.
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

If you have a better idea, please let me know. I'm very interested to improve LoadSir in any way. Thanks for your help.


### #3 `setDefaultCallback(...)` vs `addCallback(...)`
What's the purpose of a `DefaultCallback`?

With `setDefaultCallback(...)` you set the **first** visible "view" for LoadSir.
With `addCallback(...)` you could set other "views" for different purposes.
Normally you have the use case to show a loading view/ProgressBar, therefore you should use `setDefaultCallback("your LoadingCallback")`.





