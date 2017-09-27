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

### #2 How to add Toolbar or TitleView in custom Callback?
Because there are so many styles of TitleView, LoadSir didn't support this feature. You can register the View below the
TitleView instead. So that LayoutLoad will not include the TitleView.

If you have a better idea, please let me know. I'm very interested to improve LoadSir in any way. Thanks for your help.









