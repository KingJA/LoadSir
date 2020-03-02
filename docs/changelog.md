Changelog
---

**1.3.8**
- [x] 支持Android X
- [x] 适配所有宿主View(目前)
- [x] GONE->INVISIBLE
- [x] Fragment 的注册方式，还需要优化，最好统一单Fragment和多Fragment的注册

PS:一个Activity可能会存在多个Fragment，因此Fragment的onCreateView需要返回LoadLayout，这样避免两个Fragment的View同时存在，
因为Fragment的show和hide会切换onCreateView为Visiable和GONE
阅读Fragment，理清各个类的指责，重构代码


**1.3.6**
- [x] modify code in`showWithCallback(boolean successVisible)`, use INVISIBLE instead of GONE thanks @X

**1.3.5**
- [x] add API `public Class<? extends Callback> getCurrentCallback()`
- [x] deprecated API `public LinearLayout getTitleLoadLayout(Context context, ViewGroup rootView, View titleView)`

**1.3.2**
- [x] add API `public boolean getSuccessVisible()` used for displaying callback above successView.
- [x] fix layout fault when register in View.

**1.3.0**
- [x] in default, addView(SuccessfulView). Hide SuccessfulView instead of removeView(SuccessfulView).
- [x] add default Callback `ProgressCallback`, `HintCallback`.

**v1.2.2**
- [x] change minSdkVersion 16 to 14 [issues #8](https://github.com/KingJA/LoadSir/issues/8)
- [x] modify callback dynamically [issues #11](https://github.com/KingJA/LoadSir/issues/11) [issues #7](https://github.com/KingJA/LoadSir/issues/7)
- [x] add TitleBar sample [issues #12](https://github.com/KingJA/LoadSir/issues/12)
- [x] add API in Callback [issues #10](https://github.com/KingJA/LoadSir/issues/10)
    * protected abstract int onCreateView()
    * public void onDetach()
    * public void onAttach(Context context, View view)
- [x] make LoadLayout public

**v1.2.0**
- [x] Improve API naming notations.

**v1.1.1**
- [x] Initial release .
