"use weex:vue";

if (typeof Promise !== 'undefined' && !Promise.prototype.finally) {
  Promise.prototype.finally = function(callback) {
    const promise = this.constructor
    return this.then(
      value => promise.resolve(callback()).then(() => value),
      reason => promise.resolve(callback()).then(() => {
        throw reason
      })
    )
  }
};

if (typeof uni !== 'undefined' && uni && uni.requireGlobal) {
  const global = uni.requireGlobal()
  ArrayBuffer = global.ArrayBuffer
  Int8Array = global.Int8Array
  Uint8Array = global.Uint8Array
  Uint8ClampedArray = global.Uint8ClampedArray
  Int16Array = global.Int16Array
  Uint16Array = global.Uint16Array
  Int32Array = global.Int32Array
  Uint32Array = global.Uint32Array
  Float32Array = global.Float32Array
  Float64Array = global.Float64Array
  BigInt64Array = global.BigInt64Array
  BigUint64Array = global.BigUint64Array
};


(()=>{var u=Object.create;var c=Object.defineProperty;var d=Object.getOwnPropertyDescriptor;var w=Object.getOwnPropertyNames;var f=Object.getPrototypeOf,m=Object.prototype.hasOwnProperty;var v=(t,e)=>()=>(e||t((e={exports:{}}).exports,e),e.exports);var g=(t,e,o,s)=>{if(e&&typeof e=="object"||typeof e=="function")for(let r of w(e))!m.call(t,r)&&r!==o&&c(t,r,{get:()=>e[r],enumerable:!(s=d(e,r))||s.enumerable});return t};var b=(t,e,o)=>(o=t!=null?u(f(t)):{},g(e||!t||!t.__esModule?c(o,"default",{value:t,enumerable:!0}):o,t));var a=v((A,_)=>{_.exports=Vue});var n=b(a());function y(t,e,...o){uni.__log__?uni.__log__(t,e,...o):console[t].apply(console,[...o,e])}var x=(0,n.defineComponent)({__name:"index",setup(t){let e=(0,n.ref)(),o=s=>{y("log","at pages/index/index.nvue:11",s)};return(0,n.onMounted)(()=>{e.value.init(s=>{e.value.load(["https://admin.at.yesaiot.com/app/"])})}),(s,r)=>{let i=(0,n.resolveComponent)("mWebview");return(0,n.openBlock)(),(0,n.createElementBlock)("scroll-view",{scrollY:!0,showScrollbar:!0,enableBackToTop:!0,bubble:"true",style:{flexDirection:"column"}},[(0,n.createVNode)(i,{ref_key:"webview",ref:e,class:"webview",onBridge:o},null,512)])}}}),h={webview:{"":{width:"750rpx",flex:1}}},k=(t,e)=>{let o=t.__vccOpts||t;for(let[s,r]of e)o[s]=r;return o},l=k(x,[["styles",[h]]]);var p=plus.webview.currentWebview();if(p){let t=parseInt(p.id),e="pages/index/index",o={};try{o=JSON.parse(p.__query__)}catch(r){}l.mpType="page";let s=Vue.createPageApp(l,{$store:getApp({allowDefault:!0}).$store,__pageId:t,__pagePath:e,__pageQuery:o});s.provide("__globalStyles",Vue.useCssStyles([...__uniConfig.styles,...l.styles||[]])),s.mount("#root")}})();
