if("undefined"==typeof Promise||Promise.prototype.finally||(Promise.prototype.finally=function(e){const n=this.constructor;return this.then((t=>n.resolve(e()).then((()=>t))),(t=>n.resolve(e()).then((()=>{throw t}))))}),"undefined"!=typeof uni&&uni&&uni.requireGlobal){const e=uni.requireGlobal();ArrayBuffer=e.ArrayBuffer,Int8Array=e.Int8Array,Uint8Array=e.Uint8Array,Uint8ClampedArray=e.Uint8ClampedArray,Int16Array=e.Int16Array,Uint16Array=e.Uint16Array,Int32Array=e.Int32Array,Uint32Array=e.Uint32Array,Float32Array=e.Float32Array,Float64Array=e.Float64Array,BigInt64Array=e.BigInt64Array,BigUint64Array=e.BigUint64Array}uni.restoreGlobal&&uni.restoreGlobal(Vue,weex,plus,setTimeout,clearTimeout,setInterval,clearInterval),function(e){"use strict";__definePage("pages/index/index",e.defineComponent({__name:"index",setup(n){const t=e.ref("Hello");return(n,r)=>(e.openBlock(),e.createElementBlock("view",{class:"content"},[e.createElementVNode("image",{class:"logo",src:"/static/logo.png"}),e.createElementVNode("view",{class:"text-area"},[e.createElementVNode("text",{class:"title"},e.toDisplayString(t.value),1)])]))}}));function n(e,n,...t){uni.__log__?uni.__log__(e,n,...t):console[e].apply(console,[...t,n])}const t=n=>(t,r=e.getCurrentInstance())=>{!e.isInSSRComponentSetup&&e.injectHook(n,t,r)},r=t("onShow"),o=t("onHide"),a=t("onLaunch"),i=e.defineComponent({__name:"App",setup:e=>(a((()=>{n("log","at App.vue:4","App Launch")})),r((()=>{n("log","at App.vue:7","App Show")})),o((()=>{n("log","at App.vue:10","App Hide")})),()=>{})});const{app:l,Vuex:p,Pinia:u}={app:e.createVueApp(i)};uni.Vuex=p,uni.Pinia=u,l.provide("__globalStyles",__uniConfig.styles),l._component.mpType="app",l._component.render=()=>{},l.mount("#app")}(Vue);
