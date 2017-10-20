Changelog
---
**1.3.2**
- [x] add API `public boolean getSuccessVisible()` used for displaying callback above successView.
- [x] fix layout fault when register in View.

**1.3.0**
- [x] in default, addView(SuccessfulView). Hide SuccessfulView instead of removeView(SuccessfulView).
- [x] add default Callback `ProgressCallback`, `HintCallback`.

* **v1.2.2**
- [x] change minSdkVersion 16 to 14 [issues #8](https://github.com/KingJA/LoadSir/issues/8)
- [x] modify callback dynamically [issues #11](https://github.com/KingJA/LoadSir/issues/11) [issues #7](https://github.com/KingJA/LoadSir/issues/7)
- [x] add TitleBar sample [issues #12](https://github.com/KingJA/LoadSir/issues/12)
- [x] add API in Callback [issues #10](https://github.com/KingJA/LoadSir/issues/10)
    * protected abstract int onCreateView()
    * public void onDetach()
    * public void onAttach(Context context, View view)
- [x] make LoadLayout public

* **v1.2.0**
- [x] Improve API naming notations.

* **v1.1.1**
- [x] Initial release .
