Changelog
---
**1.3.8**
- [x] 适配Android X
- [x] 支持ConstraintLayout的子View注册(详见Ps[1])
- [x] 修复子view有margin，注册后出现双倍margin问题

Ps:
[1]要注册RelativeLayout或ConstraintLayout的子View，如果该子View被其它子View约束，建议在子View外层再包一层布局，参考
acitivy_view.xm和activity_constraintlayout.xml

**1.3.6**
- [x] modify code in`showWithCallback(boolean successVisible)`, use INVISIBLE instead of GONE thanks @X

**1.3.5**
- [x] add API `public Class<? extends Callback> getCurrentCallback()`X
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
