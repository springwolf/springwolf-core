import{$ as Y,$a as fe,$b as Ii,A as wr,Aa as oh,Ab as Hc,B as Sr,Ba as sh,Bb as Kt,C as cs,Ca as lh,Cb as Ni,D as jc,Da as ch,Db as J,E as ds,Ea as lt,Eb as At,F as Fn,Fa as ba,Fb as S,G as ms,Ga as dh,Gb as Ge,H as vt,Ha as Cr,Hb as ae,I as Gt,Ia as mh,Ib as Dr,J as Ie,Ja as yt,Jb as hh,K as ti,Ka as h,Kb as Mr,L as Fe,La as ai,Lb as Nr,M as te,Ma as xt,Mb as Ir,N as V,Na as pt,Nb as vs,O as I,Oa as Er,Ob as _s,P as B,Pa as Oe,Pb as va,Q as p,Qa as fs,Qb as si,R as ma,Ra as xn,Rb as Uc,S as Qu,Sa as gs,Sb as Qe,T as Ju,Ta as T,Tb as qc,U as Ln,Ua as $,Ub as Gc,V as ps,Va as K,Vb as _a,W as dt,Wa as Vc,Wb as ys,X as at,Xa as Ve,Xb as xs,Y as rt,Ya as Wt,Yb as Ar,Z as pa,Za as $c,Zb as fh,_ as ce,_a as ri,_b as cn,a as N,aa as Ei,ab as E,ac as gh,b as Ee,ba as Te,bb as D,bc as bh,ca as W,cb as Ke,cc as ht,d as L,da as Di,db as Ye,dc as vh,e as Iy,ea as ne,eb as Xe,ec as Tr,f as bt,fa as kr,fb as R,fc as _h,g as Nt,ga as eh,gb as y,gc as yh,h as z,ha as ni,hb as v,hc as et,i as Jn,ia as ua,ib as P,ic as xh,j as qu,ja as ha,jb as Ze,jc as Ae,k as Ue,ka as th,kb as Je,kc as Or,l as Gu,la as Mi,lb as ln,lc as wh,m as De,ma as ii,mb as zc,mc as ws,n as Wu,na as us,nb as ut,o as Ku,oa as _t,ob as oi,p as Yu,pa as st,pb as ye,q as Xu,qa as ee,qb as bs,r as It,ra as X,rb as M,s as We,sa as Bn,sb as de,t as Bc,ta as nh,tb as j,u as ls,ua as hs,ub as ot,v as yr,va as fa,vb as qe,w as ei,wa as ga,wb as H,x as Pn,xa as ih,xb as U,y as xr,ya as ah,yb as ph,z as Zu,za as rh,zb as uh}from"./chunk-A6WCRS56.js";var po=L(Ce=>{"use strict";Object.defineProperty(Ce,"__esModule",{value:!0});Ce.regexpCode=Ce.getEsmExportName=Ce.getProperty=Ce.safeStringify=Ce.stringify=Ce.strConcat=Ce.addCodeArg=Ce.str=Ce._=Ce.nil=Ce._Code=Ce.Name=Ce.IDENTIFIER=Ce._CodeOrName=void 0;var co=class{};Ce._CodeOrName=co;Ce.IDENTIFIER=/^[a-z$_][a-z$_0-9]*$/i;var Gi=class extends co{constructor(t){if(super(),!Ce.IDENTIFIER.test(t))throw new Error("CodeGen: name must be a valid identifier");this.str=t}toString(){return this.str}emptyStr(){return!1}get names(){return{[this.str]:1}}};Ce.Name=Gi;var en=class extends co{constructor(t){super(),this._items=typeof t=="string"?[t]:t}toString(){return this.str}emptyStr(){if(this._items.length>1)return!1;let t=this._items[0];return t===""||t==='""'}get str(){var t;return(t=this._str)!==null&&t!==void 0?t:this._str=this._items.reduce((e,i)=>`${e}${i}`,"")}get names(){var t;return(t=this._names)!==null&&t!==void 0?t:this._names=this._items.reduce((e,i)=>(i instanceof Gi&&(e[i.str]=(e[i.str]||0)+1),e),{})}};Ce._Code=en;Ce.nil=new en("");function Lg(n,...t){let e=[n[0]],i=0;for(;i<t.length;)im(e,t[i]),e.push(n[++i]);return new en(e)}Ce._=Lg;var nm=new en("+");function Bg(n,...t){let e=[mo(n[0])],i=0;for(;i<t.length;)e.push(nm),im(e,t[i]),e.push(nm,mo(n[++i]));return Kw(e),new en(e)}Ce.str=Bg;function im(n,t){t instanceof en?n.push(...t._items):t instanceof Gi?n.push(t):n.push(Zw(t))}Ce.addCodeArg=im;function Kw(n){let t=1;for(;t<n.length-1;){if(n[t]===nm){let e=Yw(n[t-1],n[t+1]);if(e!==void 0){n.splice(t-1,3,e);continue}n[t++]="+"}t++}}function Yw(n,t){if(t==='""')return n;if(n==='""')return t;if(typeof n=="string")return t instanceof Gi||n[n.length-1]!=='"'?void 0:typeof t!="string"?`${n.slice(0,-1)}${t}"`:t[0]==='"'?n.slice(0,-1)+t.slice(1):void 0;if(typeof t=="string"&&t[0]==='"'&&!(n instanceof Gi))return`"${n}${t.slice(1)}`}function Xw(n,t){return t.emptyStr()?n:n.emptyStr()?t:Bg`${n}${t}`}Ce.strConcat=Xw;function Zw(n){return typeof n=="number"||typeof n=="boolean"||n===null?n:mo(Array.isArray(n)?n.join(","):n)}function Qw(n){return new en(mo(n))}Ce.stringify=Qw;function mo(n){return JSON.stringify(n).replace(/\u2028/g,"\\u2028").replace(/\u2029/g,"\\u2029")}Ce.safeStringify=mo;function Jw(n){return typeof n=="string"&&Ce.IDENTIFIER.test(n)?new en(`.${n}`):Lg`[${n}]`}Ce.getProperty=Jw;function eS(n){if(typeof n=="string"&&Ce.IDENTIFIER.test(n))return new en(`${n}`);throw new Error(`CodeGen: invalid export name: ${n}, use explicit $id name mapping`)}Ce.getEsmExportName=eS;function tS(n){return new en(n.toString())}Ce.regexpCode=tS});var om=L(Vt=>{"use strict";Object.defineProperty(Vt,"__esModule",{value:!0});Vt.ValueScope=Vt.ValueScopeName=Vt.Scope=Vt.varKinds=Vt.UsedValueState=void 0;var jt=po(),am=class extends Error{constructor(t){super(`CodeGen: "code" for ${t} not defined`),this.value=t.value}},Cl;(function(n){n[n.Started=0]="Started",n[n.Completed=1]="Completed"})(Cl||(Vt.UsedValueState=Cl={}));Vt.varKinds={const:new jt.Name("const"),let:new jt.Name("let"),var:new jt.Name("var")};var El=class{constructor({prefixes:t,parent:e}={}){this._names={},this._prefixes=t,this._parent=e}toName(t){return t instanceof jt.Name?t:this.name(t)}name(t){return new jt.Name(this._newName(t))}_newName(t){let e=this._names[t]||this._nameGroup(t);return`${t}${e.index++}`}_nameGroup(t){var e,i;if(!((i=(e=this._parent)===null||e===void 0?void 0:e._prefixes)===null||i===void 0)&&i.has(t)||this._prefixes&&!this._prefixes.has(t))throw new Error(`CodeGen: prefix "${t}" is not allowed in this scope`);return this._names[t]={prefix:t,index:0}}};Vt.Scope=El;var Dl=class extends jt.Name{constructor(t,e){super(e),this.prefix=t}setValue(t,{property:e,itemIndex:i}){this.value=t,this.scopePath=(0,jt._)`.${new jt.Name(e)}[${i}]`}};Vt.ValueScopeName=Dl;var nS=(0,jt._)`\n`,rm=class extends El{constructor(t){super(t),this._values={},this._scope=t.scope,this.opts=Ee(N({},t),{_n:t.lines?nS:jt.nil})}get(){return this._scope}name(t){return new Dl(t,this._newName(t))}value(t,e){var i;if(e.ref===void 0)throw new Error("CodeGen: ref must be passed in value");let a=this.toName(t),{prefix:r}=a,o=(i=e.key)!==null&&i!==void 0?i:e.ref,s=this._values[r];if(s){let d=s.get(o);if(d)return d}else s=this._values[r]=new Map;s.set(o,a);let l=this._scope[r]||(this._scope[r]=[]),c=l.length;return l[c]=e.ref,a.setValue(e,{property:r,itemIndex:c}),a}getValue(t,e){let i=this._values[t];if(i)return i.get(e)}scopeRefs(t,e=this._values){return this._reduceValues(e,i=>{if(i.scopePath===void 0)throw new Error(`CodeGen: name "${i}" has no value`);return(0,jt._)`${t}${i.scopePath}`})}scopeCode(t=this._values,e,i){return this._reduceValues(t,a=>{if(a.value===void 0)throw new Error(`CodeGen: name "${a}" has no value`);return a.value.code},e,i)}_reduceValues(t,e,i={},a){let r=jt.nil;for(let o in t){let s=t[o];if(!s)continue;let l=i[o]=i[o]||new Map;s.forEach(c=>{if(l.has(c))return;l.set(c,Cl.Started);let d=e(c);if(d){let m=this.opts.es5?Vt.varKinds.var:Vt.varKinds.const;r=(0,jt._)`${r}${m} ${c} = ${d};${this.opts._n}`}else if(d=a?.(c))r=(0,jt._)`${r}${d}${this.opts._n}`;else throw new am(c);l.set(c,Cl.Completed)})}return r}};Vt.ValueScope=rm});var ue=L(pe=>{"use strict";Object.defineProperty(pe,"__esModule",{value:!0});pe.or=pe.and=pe.not=pe.CodeGen=pe.operators=pe.varKinds=pe.ValueScopeName=pe.ValueScope=pe.Scope=pe.Name=pe.regexpCode=pe.stringify=pe.getProperty=pe.nil=pe.strConcat=pe.str=pe._=void 0;var ve=po(),hn=om(),gi=po();Object.defineProperty(pe,"_",{enumerable:!0,get:function(){return gi._}});Object.defineProperty(pe,"str",{enumerable:!0,get:function(){return gi.str}});Object.defineProperty(pe,"strConcat",{enumerable:!0,get:function(){return gi.strConcat}});Object.defineProperty(pe,"nil",{enumerable:!0,get:function(){return gi.nil}});Object.defineProperty(pe,"getProperty",{enumerable:!0,get:function(){return gi.getProperty}});Object.defineProperty(pe,"stringify",{enumerable:!0,get:function(){return gi.stringify}});Object.defineProperty(pe,"regexpCode",{enumerable:!0,get:function(){return gi.regexpCode}});Object.defineProperty(pe,"Name",{enumerable:!0,get:function(){return gi.Name}});var Al=om();Object.defineProperty(pe,"Scope",{enumerable:!0,get:function(){return Al.Scope}});Object.defineProperty(pe,"ValueScope",{enumerable:!0,get:function(){return Al.ValueScope}});Object.defineProperty(pe,"ValueScopeName",{enumerable:!0,get:function(){return Al.ValueScopeName}});Object.defineProperty(pe,"varKinds",{enumerable:!0,get:function(){return Al.varKinds}});pe.operators={GT:new ve._Code(">"),GTE:new ve._Code(">="),LT:new ve._Code("<"),LTE:new ve._Code("<="),EQ:new ve._Code("==="),NEQ:new ve._Code("!=="),NOT:new ve._Code("!"),OR:new ve._Code("||"),AND:new ve._Code("&&"),ADD:new ve._Code("+")};var Wn=class{optimizeNodes(){return this}optimizeNames(t,e){return this}},sm=class extends Wn{constructor(t,e,i){super(),this.varKind=t,this.name=e,this.rhs=i}render({es5:t,_n:e}){let i=t?hn.varKinds.var:this.varKind,a=this.rhs===void 0?"":` = ${this.rhs}`;return`${i} ${this.name}${a};`+e}optimizeNames(t,e){if(t[this.name.str])return this.rhs&&(this.rhs=ja(this.rhs,t,e)),this}get names(){return this.rhs instanceof ve._CodeOrName?this.rhs.names:{}}},Ml=class extends Wn{constructor(t,e,i){super(),this.lhs=t,this.rhs=e,this.sideEffects=i}render({_n:t}){return`${this.lhs} = ${this.rhs};`+t}optimizeNames(t,e){if(!(this.lhs instanceof ve.Name&&!t[this.lhs.str]&&!this.sideEffects))return this.rhs=ja(this.rhs,t,e),this}get names(){let t=this.lhs instanceof ve.Name?{}:N({},this.lhs.names);return Il(t,this.rhs)}},lm=class extends Ml{constructor(t,e,i,a){super(t,i,a),this.op=e}render({_n:t}){return`${this.lhs} ${this.op}= ${this.rhs};`+t}},cm=class extends Wn{constructor(t){super(),this.label=t,this.names={}}render({_n:t}){return`${this.label}:`+t}},dm=class extends Wn{constructor(t){super(),this.label=t,this.names={}}render({_n:t}){return`break${this.label?` ${this.label}`:""};`+t}},mm=class extends Wn{constructor(t){super(),this.error=t}render({_n:t}){return`throw ${this.error};`+t}get names(){return this.error.names}},pm=class extends Wn{constructor(t){super(),this.code=t}render({_n:t}){return`${this.code};`+t}optimizeNodes(){return`${this.code}`?this:void 0}optimizeNames(t,e){return this.code=ja(this.code,t,e),this}get names(){return this.code instanceof ve._CodeOrName?this.code.names:{}}},uo=class extends Wn{constructor(t=[]){super(),this.nodes=t}render(t){return this.nodes.reduce((e,i)=>e+i.render(t),"")}optimizeNodes(){let{nodes:t}=this,e=t.length;for(;e--;){let i=t[e].optimizeNodes();Array.isArray(i)?t.splice(e,1,...i):i?t[e]=i:t.splice(e,1)}return t.length>0?this:void 0}optimizeNames(t,e){let{nodes:i}=this,a=i.length;for(;a--;){let r=i[a];r.optimizeNames(t,e)||(iS(t,r.names),i.splice(a,1))}return i.length>0?this:void 0}get names(){return this.nodes.reduce((t,e)=>Yi(t,e.names),{})}},Kn=class extends uo{render(t){return"{"+t._n+super.render(t)+"}"+t._n}},um=class extends uo{},Ba=class extends Kn{};Ba.kind="else";var Wi=class n extends Kn{constructor(t,e){super(e),this.condition=t}render(t){let e=`if(${this.condition})`+super.render(t);return this.else&&(e+="else "+this.else.render(t)),e}optimizeNodes(){super.optimizeNodes();let t=this.condition;if(t===!0)return this.nodes;let e=this.else;if(e){let i=e.optimizeNodes();e=this.else=Array.isArray(i)?new Ba(i):i}if(e)return t===!1?e instanceof n?e:e.nodes:this.nodes.length?this:new n(jg(t),e instanceof n?[e]:e.nodes);if(!(t===!1||!this.nodes.length))return this}optimizeNames(t,e){var i;if(this.else=(i=this.else)===null||i===void 0?void 0:i.optimizeNames(t,e),!!(super.optimizeNames(t,e)||this.else))return this.condition=ja(this.condition,t,e),this}get names(){let t=super.names;return Il(t,this.condition),this.else&&Yi(t,this.else.names),t}};Wi.kind="if";var Ki=class extends Kn{};Ki.kind="for";var hm=class extends Ki{constructor(t){super(),this.iteration=t}render(t){return`for(${this.iteration})`+super.render(t)}optimizeNames(t,e){if(super.optimizeNames(t,e))return this.iteration=ja(this.iteration,t,e),this}get names(){return Yi(super.names,this.iteration.names)}},fm=class extends Ki{constructor(t,e,i,a){super(),this.varKind=t,this.name=e,this.from=i,this.to=a}render(t){let e=t.es5?hn.varKinds.var:this.varKind,{name:i,from:a,to:r}=this;return`for(${e} ${i}=${a}; ${i}<${r}; ${i}++)`+super.render(t)}get names(){let t=Il(super.names,this.from);return Il(t,this.to)}},Nl=class extends Ki{constructor(t,e,i,a){super(),this.loop=t,this.varKind=e,this.name=i,this.iterable=a}render(t){return`for(${this.varKind} ${this.name} ${this.loop} ${this.iterable})`+super.render(t)}optimizeNames(t,e){if(super.optimizeNames(t,e))return this.iterable=ja(this.iterable,t,e),this}get names(){return Yi(super.names,this.iterable.names)}},ho=class extends Kn{constructor(t,e,i){super(),this.name=t,this.args=e,this.async=i}render(t){return`${this.async?"async ":""}function ${this.name}(${this.args})`+super.render(t)}};ho.kind="func";var fo=class extends uo{render(t){return"return "+super.render(t)}};fo.kind="return";var gm=class extends Kn{render(t){let e="try"+super.render(t);return this.catch&&(e+=this.catch.render(t)),this.finally&&(e+=this.finally.render(t)),e}optimizeNodes(){var t,e;return super.optimizeNodes(),(t=this.catch)===null||t===void 0||t.optimizeNodes(),(e=this.finally)===null||e===void 0||e.optimizeNodes(),this}optimizeNames(t,e){var i,a;return super.optimizeNames(t,e),(i=this.catch)===null||i===void 0||i.optimizeNames(t,e),(a=this.finally)===null||a===void 0||a.optimizeNames(t,e),this}get names(){let t=super.names;return this.catch&&Yi(t,this.catch.names),this.finally&&Yi(t,this.finally.names),t}},go=class extends Kn{constructor(t){super(),this.error=t}render(t){return`catch(${this.error})`+super.render(t)}};go.kind="catch";var bo=class extends Kn{render(t){return"finally"+super.render(t)}};bo.kind="finally";var bm=class{constructor(t,e={}){this._values={},this._blockStarts=[],this._constants={},this.opts=Ee(N({},e),{_n:e.lines?`
`:""}),this._extScope=t,this._scope=new hn.Scope({parent:t}),this._nodes=[new um]}toString(){return this._root.render(this.opts)}name(t){return this._scope.name(t)}scopeName(t){return this._extScope.name(t)}scopeValue(t,e){let i=this._extScope.value(t,e);return(this._values[i.prefix]||(this._values[i.prefix]=new Set)).add(i),i}getScopeValue(t,e){return this._extScope.getValue(t,e)}scopeRefs(t){return this._extScope.scopeRefs(t,this._values)}scopeCode(){return this._extScope.scopeCode(this._values)}_def(t,e,i,a){let r=this._scope.toName(e);return i!==void 0&&a&&(this._constants[r.str]=i),this._leafNode(new sm(t,r,i)),r}const(t,e,i){return this._def(hn.varKinds.const,t,e,i)}let(t,e,i){return this._def(hn.varKinds.let,t,e,i)}var(t,e,i){return this._def(hn.varKinds.var,t,e,i)}assign(t,e,i){return this._leafNode(new Ml(t,e,i))}add(t,e){return this._leafNode(new lm(t,pe.operators.ADD,e))}code(t){return typeof t=="function"?t():t!==ve.nil&&this._leafNode(new pm(t)),this}object(...t){let e=["{"];for(let[i,a]of t)e.length>1&&e.push(","),e.push(i),(i!==a||this.opts.es5)&&(e.push(":"),(0,ve.addCodeArg)(e,a));return e.push("}"),new ve._Code(e)}if(t,e,i){if(this._blockNode(new Wi(t)),e&&i)this.code(e).else().code(i).endIf();else if(e)this.code(e).endIf();else if(i)throw new Error('CodeGen: "else" body without "then" body');return this}elseIf(t){return this._elseNode(new Wi(t))}else(){return this._elseNode(new Ba)}endIf(){return this._endBlockNode(Wi,Ba)}_for(t,e){return this._blockNode(t),e&&this.code(e).endFor(),this}for(t,e){return this._for(new hm(t),e)}forRange(t,e,i,a,r=this.opts.es5?hn.varKinds.var:hn.varKinds.let){let o=this._scope.toName(t);return this._for(new fm(r,o,e,i),()=>a(o))}forOf(t,e,i,a=hn.varKinds.const){let r=this._scope.toName(t);if(this.opts.es5){let o=e instanceof ve.Name?e:this.var("_arr",e);return this.forRange("_i",0,(0,ve._)`${o}.length`,s=>{this.var(r,(0,ve._)`${o}[${s}]`),i(r)})}return this._for(new Nl("of",a,r,e),()=>i(r))}forIn(t,e,i,a=this.opts.es5?hn.varKinds.var:hn.varKinds.const){if(this.opts.ownProperties)return this.forOf(t,(0,ve._)`Object.keys(${e})`,i);let r=this._scope.toName(t);return this._for(new Nl("in",a,r,e),()=>i(r))}endFor(){return this._endBlockNode(Ki)}label(t){return this._leafNode(new cm(t))}break(t){return this._leafNode(new dm(t))}return(t){let e=new fo;if(this._blockNode(e),this.code(t),e.nodes.length!==1)throw new Error('CodeGen: "return" should have one node');return this._endBlockNode(fo)}try(t,e,i){if(!e&&!i)throw new Error('CodeGen: "try" without "catch" and "finally"');let a=new gm;if(this._blockNode(a),this.code(t),e){let r=this.name("e");this._currNode=a.catch=new go(r),e(r)}return i&&(this._currNode=a.finally=new bo,this.code(i)),this._endBlockNode(go,bo)}throw(t){return this._leafNode(new mm(t))}block(t,e){return this._blockStarts.push(this._nodes.length),t&&this.code(t).endBlock(e),this}endBlock(t){let e=this._blockStarts.pop();if(e===void 0)throw new Error("CodeGen: not in self-balancing block");let i=this._nodes.length-e;if(i<0||t!==void 0&&i!==t)throw new Error(`CodeGen: wrong number of nodes: ${i} vs ${t} expected`);return this._nodes.length=e,this}func(t,e=ve.nil,i,a){return this._blockNode(new ho(t,e,i)),a&&this.code(a).endFunc(),this}endFunc(){return this._endBlockNode(ho)}optimize(t=1){for(;t-- >0;)this._root.optimizeNodes(),this._root.optimizeNames(this._root.names,this._constants)}_leafNode(t){return this._currNode.nodes.push(t),this}_blockNode(t){this._currNode.nodes.push(t),this._nodes.push(t)}_endBlockNode(t,e){let i=this._currNode;if(i instanceof t||e&&i instanceof e)return this._nodes.pop(),this;throw new Error(`CodeGen: not in block "${e?`${t.kind}/${e.kind}`:t.kind}"`)}_elseNode(t){let e=this._currNode;if(!(e instanceof Wi))throw new Error('CodeGen: "else" without "if"');return this._currNode=e.else=t,this}get _root(){return this._nodes[0]}get _currNode(){let t=this._nodes;return t[t.length-1]}set _currNode(t){let e=this._nodes;e[e.length-1]=t}};pe.CodeGen=bm;function Yi(n,t){for(let e in t)n[e]=(n[e]||0)+(t[e]||0);return n}function Il(n,t){return t instanceof ve._CodeOrName?Yi(n,t.names):n}function ja(n,t,e){if(n instanceof ve.Name)return i(n);if(!a(n))return n;return new ve._Code(n._items.reduce((r,o)=>(o instanceof ve.Name&&(o=i(o)),o instanceof ve._Code?r.push(...o._items):r.push(o),r),[]));function i(r){let o=e[r.str];return o===void 0||t[r.str]!==1?r:(delete t[r.str],o)}function a(r){return r instanceof ve._Code&&r._items.some(o=>o instanceof ve.Name&&t[o.str]===1&&e[o.str]!==void 0)}}function iS(n,t){for(let e in t)n[e]=(n[e]||0)-(t[e]||0)}function jg(n){return typeof n=="boolean"||typeof n=="number"||n===null?!n:(0,ve._)`!${vm(n)}`}pe.not=jg;var aS=Vg(pe.operators.AND);function rS(...n){return n.reduce(aS)}pe.and=rS;var oS=Vg(pe.operators.OR);function sS(...n){return n.reduce(oS)}pe.or=sS;function Vg(n){return(t,e)=>t===ve.nil?e:e===ve.nil?t:(0,ve._)`${vm(t)} ${n} ${vm(e)}`}function vm(n){return n instanceof ve.Name?n:(0,ve._)`(${n})`}});var Se=L(he=>{"use strict";Object.defineProperty(he,"__esModule",{value:!0});he.checkStrictMode=he.getErrorPath=he.Type=he.useFunc=he.setEvaluated=he.evaluatedPropsToName=he.mergeEvaluated=he.eachItem=he.unescapeJsonPointer=he.escapeJsonPointer=he.escapeFragment=he.unescapeFragment=he.schemaRefOrVal=he.schemaHasRulesButRef=he.schemaHasRules=he.checkUnknownRules=he.alwaysValidSchema=he.toHash=void 0;var Le=ue(),lS=po();function cS(n){let t={};for(let e of n)t[e]=!0;return t}he.toHash=cS;function dS(n,t){return typeof t=="boolean"?t:Object.keys(t).length===0?!0:(Hg(n,t),!Ug(t,n.self.RULES.all))}he.alwaysValidSchema=dS;function Hg(n,t=n.schema){let{opts:e,self:i}=n;if(!e.strictSchema||typeof t=="boolean")return;let a=i.RULES.keywords;for(let r in t)a[r]||Wg(n,`unknown keyword: "${r}"`)}he.checkUnknownRules=Hg;function Ug(n,t){if(typeof n=="boolean")return!n;for(let e in n)if(t[e])return!0;return!1}he.schemaHasRules=Ug;function mS(n,t){if(typeof n=="boolean")return!n;for(let e in n)if(e!=="$ref"&&t.all[e])return!0;return!1}he.schemaHasRulesButRef=mS;function pS({topSchemaRef:n,schemaPath:t},e,i,a){if(!a){if(typeof e=="number"||typeof e=="boolean")return e;if(typeof e=="string")return(0,Le._)`${e}`}return(0,Le._)`${n}${t}${(0,Le.getProperty)(i)}`}he.schemaRefOrVal=pS;function uS(n){return qg(decodeURIComponent(n))}he.unescapeFragment=uS;function hS(n){return encodeURIComponent(ym(n))}he.escapeFragment=hS;function ym(n){return typeof n=="number"?`${n}`:n.replace(/~/g,"~0").replace(/\//g,"~1")}he.escapeJsonPointer=ym;function qg(n){return n.replace(/~1/g,"/").replace(/~0/g,"~")}he.unescapeJsonPointer=qg;function fS(n,t){if(Array.isArray(n))for(let e of n)t(e);else t(n)}he.eachItem=fS;function $g({mergeNames:n,mergeToName:t,mergeValues:e,resultToName:i}){return(a,r,o,s)=>{let l=o===void 0?r:o instanceof Le.Name?(r instanceof Le.Name?n(a,r,o):t(a,r,o),o):r instanceof Le.Name?(t(a,o,r),r):e(r,o);return s===Le.Name&&!(l instanceof Le.Name)?i(a,l):l}}he.mergeEvaluated={props:$g({mergeNames:(n,t,e)=>n.if((0,Le._)`${e} !== true && ${t} !== undefined`,()=>{n.if((0,Le._)`${t} === true`,()=>n.assign(e,!0),()=>n.assign(e,(0,Le._)`${e} || {}`).code((0,Le._)`Object.assign(${e}, ${t})`))}),mergeToName:(n,t,e)=>n.if((0,Le._)`${e} !== true`,()=>{t===!0?n.assign(e,!0):(n.assign(e,(0,Le._)`${e} || {}`),xm(n,e,t))}),mergeValues:(n,t)=>n===!0?!0:N(N({},n),t),resultToName:Gg}),items:$g({mergeNames:(n,t,e)=>n.if((0,Le._)`${e} !== true && ${t} !== undefined`,()=>n.assign(e,(0,Le._)`${t} === true ? true : ${e} > ${t} ? ${e} : ${t}`)),mergeToName:(n,t,e)=>n.if((0,Le._)`${e} !== true`,()=>n.assign(e,t===!0?!0:(0,Le._)`${e} > ${t} ? ${e} : ${t}`)),mergeValues:(n,t)=>n===!0?!0:Math.max(n,t),resultToName:(n,t)=>n.var("items",t)})};function Gg(n,t){if(t===!0)return n.var("props",!0);let e=n.var("props",(0,Le._)`{}`);return t!==void 0&&xm(n,e,t),e}he.evaluatedPropsToName=Gg;function xm(n,t,e){Object.keys(e).forEach(i=>n.assign((0,Le._)`${t}${(0,Le.getProperty)(i)}`,!0))}he.setEvaluated=xm;var zg={};function gS(n,t){return n.scopeValue("func",{ref:t,code:zg[t.code]||(zg[t.code]=new lS._Code(t.code))})}he.useFunc=gS;var _m;(function(n){n[n.Num=0]="Num",n[n.Str=1]="Str"})(_m||(he.Type=_m={}));function bS(n,t,e){if(n instanceof Le.Name){let i=t===_m.Num;return e?i?(0,Le._)`"[" + ${n} + "]"`:(0,Le._)`"['" + ${n} + "']"`:i?(0,Le._)`"/" + ${n}`:(0,Le._)`"/" + ${n}.replace(/~/g, "~0").replace(/\\//g, "~1")`}return e?(0,Le.getProperty)(n).toString():"/"+ym(n)}he.getErrorPath=bS;function Wg(n,t,e=n.opts.strictSchema){if(e){if(t=`strict mode: ${t}`,e===!0)throw new Error(t);n.self.logger.warn(t)}}he.checkStrictMode=Wg});var Yn=L(wm=>{"use strict";Object.defineProperty(wm,"__esModule",{value:!0});var Ct=ue(),vS={data:new Ct.Name("data"),valCxt:new Ct.Name("valCxt"),instancePath:new Ct.Name("instancePath"),parentData:new Ct.Name("parentData"),parentDataProperty:new Ct.Name("parentDataProperty"),rootData:new Ct.Name("rootData"),dynamicAnchors:new Ct.Name("dynamicAnchors"),vErrors:new Ct.Name("vErrors"),errors:new Ct.Name("errors"),this:new Ct.Name("this"),self:new Ct.Name("self"),scope:new Ct.Name("scope"),json:new Ct.Name("json"),jsonPos:new Ct.Name("jsonPos"),jsonLen:new Ct.Name("jsonLen"),jsonPart:new Ct.Name("jsonPart")};wm.default=vS});var vo=L(Et=>{"use strict";Object.defineProperty(Et,"__esModule",{value:!0});Et.extendErrors=Et.resetErrorsCount=Et.reportExtraError=Et.reportError=Et.keyword$DataError=Et.keywordError=void 0;var ke=ue(),Tl=Se(),Pt=Yn();Et.keywordError={message:({keyword:n})=>(0,ke.str)`must pass "${n}" keyword validation`};Et.keyword$DataError={message:({keyword:n,schemaType:t})=>t?(0,ke.str)`"${n}" keyword must be ${t} ($data)`:(0,ke.str)`"${n}" keyword is invalid ($data)`};function _S(n,t=Et.keywordError,e,i){let{it:a}=n,{gen:r,compositeRule:o,allErrors:s}=a,l=Xg(n,t,e);i??(o||s)?Kg(r,l):Yg(a,(0,ke._)`[${l}]`)}Et.reportError=_S;function yS(n,t=Et.keywordError,e){let{it:i}=n,{gen:a,compositeRule:r,allErrors:o}=i,s=Xg(n,t,e);Kg(a,s),r||o||Yg(i,Pt.default.vErrors)}Et.reportExtraError=yS;function xS(n,t){n.assign(Pt.default.errors,t),n.if((0,ke._)`${Pt.default.vErrors} !== null`,()=>n.if(t,()=>n.assign((0,ke._)`${Pt.default.vErrors}.length`,t),()=>n.assign(Pt.default.vErrors,null)))}Et.resetErrorsCount=xS;function wS({gen:n,keyword:t,schemaValue:e,data:i,errsCount:a,it:r}){if(a===void 0)throw new Error("ajv implementation error");let o=n.name("err");n.forRange("i",a,Pt.default.errors,s=>{n.const(o,(0,ke._)`${Pt.default.vErrors}[${s}]`),n.if((0,ke._)`${o}.instancePath === undefined`,()=>n.assign((0,ke._)`${o}.instancePath`,(0,ke.strConcat)(Pt.default.instancePath,r.errorPath))),n.assign((0,ke._)`${o}.schemaPath`,(0,ke.str)`${r.errSchemaPath}/${t}`),r.opts.verbose&&(n.assign((0,ke._)`${o}.schema`,e),n.assign((0,ke._)`${o}.data`,i))})}Et.extendErrors=wS;function Kg(n,t){let e=n.const("err",t);n.if((0,ke._)`${Pt.default.vErrors} === null`,()=>n.assign(Pt.default.vErrors,(0,ke._)`[${e}]`),(0,ke._)`${Pt.default.vErrors}.push(${e})`),n.code((0,ke._)`${Pt.default.errors}++`)}function Yg(n,t){let{gen:e,validateName:i,schemaEnv:a}=n;a.$async?e.throw((0,ke._)`new ${n.ValidationError}(${t})`):(e.assign((0,ke._)`${i}.errors`,t),e.return(!1))}var Xi={keyword:new ke.Name("keyword"),schemaPath:new ke.Name("schemaPath"),params:new ke.Name("params"),propertyName:new ke.Name("propertyName"),message:new ke.Name("message"),schema:new ke.Name("schema"),parentSchema:new ke.Name("parentSchema")};function Xg(n,t,e){let{createErrors:i}=n.it;return i===!1?(0,ke._)`{}`:SS(n,t,e)}function SS(n,t,e={}){let{gen:i,it:a}=n,r=[kS(a,e),CS(n,e)];return ES(n,t,r),i.object(...r)}function kS({errorPath:n},{instancePath:t}){let e=t?(0,ke.str)`${n}${(0,Tl.getErrorPath)(t,Tl.Type.Str)}`:n;return[Pt.default.instancePath,(0,ke.strConcat)(Pt.default.instancePath,e)]}function CS({keyword:n,it:{errSchemaPath:t}},{schemaPath:e,parentSchema:i}){let a=i?t:(0,ke.str)`${t}/${n}`;return e&&(a=(0,ke.str)`${a}${(0,Tl.getErrorPath)(e,Tl.Type.Str)}`),[Xi.schemaPath,a]}function ES(n,{params:t,message:e},i){let{keyword:a,data:r,schemaValue:o,it:s}=n,{opts:l,propertyName:c,topSchemaRef:d,schemaPath:m}=s;i.push([Xi.keyword,a],[Xi.params,typeof t=="function"?t(n):t||(0,ke._)`{}`]),l.messages&&i.push([Xi.message,typeof e=="function"?e(n):e]),l.verbose&&i.push([Xi.schema,o],[Xi.parentSchema,(0,ke._)`${d}${m}`],[Pt.default.data,r]),c&&i.push([Xi.propertyName,c])}});var Qg=L(Va=>{"use strict";Object.defineProperty(Va,"__esModule",{value:!0});Va.boolOrEmptySchema=Va.topBoolOrEmptySchema=void 0;var DS=vo(),MS=ue(),NS=Yn(),IS={message:"boolean schema is false"};function AS(n){let{gen:t,schema:e,validateName:i}=n;e===!1?Zg(n,!1):typeof e=="object"&&e.$async===!0?t.return(NS.default.data):(t.assign((0,MS._)`${i}.errors`,null),t.return(!0))}Va.topBoolOrEmptySchema=AS;function TS(n,t){let{gen:e,schema:i}=n;i===!1?(e.var(t,!1),Zg(n)):e.var(t,!0)}Va.boolOrEmptySchema=TS;function Zg(n,t){let{gen:e,data:i}=n,a={gen:e,keyword:"false schema",data:i,schema:!1,schemaCode:!1,schemaValue:!1,params:{},it:n};(0,DS.reportError)(a,IS,void 0,t)}});var Sm=L($a=>{"use strict";Object.defineProperty($a,"__esModule",{value:!0});$a.getRules=$a.isJSONType=void 0;var OS=["string","number","integer","boolean","null","object","array"],RS=new Set(OS);function PS(n){return typeof n=="string"&&RS.has(n)}$a.isJSONType=PS;function FS(){let n={number:{type:"number",rules:[]},string:{type:"string",rules:[]},array:{type:"array",rules:[]},object:{type:"object",rules:[]}};return{types:Ee(N({},n),{integer:!0,boolean:!0,null:!0}),rules:[{rules:[]},n.number,n.string,n.array,n.object],post:{rules:[]},all:{},keywords:{}}}$a.getRules=FS});var km=L(bi=>{"use strict";Object.defineProperty(bi,"__esModule",{value:!0});bi.shouldUseRule=bi.shouldUseGroup=bi.schemaHasRulesForType=void 0;function LS({schema:n,self:t},e){let i=t.RULES.types[e];return i&&i!==!0&&Jg(n,i)}bi.schemaHasRulesForType=LS;function Jg(n,t){return t.rules.some(e=>eb(n,e))}bi.shouldUseGroup=Jg;function eb(n,t){var e;return n[t.keyword]!==void 0||((e=t.definition.implements)===null||e===void 0?void 0:e.some(i=>n[i]!==void 0))}bi.shouldUseRule=eb});var _o=L(Dt=>{"use strict";Object.defineProperty(Dt,"__esModule",{value:!0});Dt.reportTypeError=Dt.checkDataTypes=Dt.checkDataType=Dt.coerceAndCheckDataType=Dt.getJSONTypes=Dt.getSchemaTypes=Dt.DataType=void 0;var BS=Sm(),jS=km(),VS=vo(),oe=ue(),tb=Se(),za;(function(n){n[n.Correct=0]="Correct",n[n.Wrong=1]="Wrong"})(za||(Dt.DataType=za={}));function $S(n){let t=nb(n.type);if(t.includes("null")){if(n.nullable===!1)throw new Error("type: null contradicts nullable: false")}else{if(!t.length&&n.nullable!==void 0)throw new Error('"nullable" cannot be used without "type"');n.nullable===!0&&t.push("null")}return t}Dt.getSchemaTypes=$S;function nb(n){let t=Array.isArray(n)?n:n?[n]:[];if(t.every(BS.isJSONType))return t;throw new Error("type must be JSONType or JSONType[]: "+t.join(","))}Dt.getJSONTypes=nb;function zS(n,t){let{gen:e,data:i,opts:a}=n,r=HS(t,a.coerceTypes),o=t.length>0&&!(r.length===0&&t.length===1&&(0,jS.schemaHasRulesForType)(n,t[0]));if(o){let s=Em(t,i,a.strictNumbers,za.Wrong);e.if(s,()=>{r.length?US(n,t,r):Dm(n)})}return o}Dt.coerceAndCheckDataType=zS;var ib=new Set(["string","number","integer","boolean","null"]);function HS(n,t){return t?n.filter(e=>ib.has(e)||t==="array"&&e==="array"):[]}function US(n,t,e){let{gen:i,data:a,opts:r}=n,o=i.let("dataType",(0,oe._)`typeof ${a}`),s=i.let("coerced",(0,oe._)`undefined`);r.coerceTypes==="array"&&i.if((0,oe._)`${o} == 'object' && Array.isArray(${a}) && ${a}.length == 1`,()=>i.assign(a,(0,oe._)`${a}[0]`).assign(o,(0,oe._)`typeof ${a}`).if(Em(t,a,r.strictNumbers),()=>i.assign(s,a))),i.if((0,oe._)`${s} !== undefined`);for(let c of e)(ib.has(c)||c==="array"&&r.coerceTypes==="array")&&l(c);i.else(),Dm(n),i.endIf(),i.if((0,oe._)`${s} !== undefined`,()=>{i.assign(a,s),qS(n,s)});function l(c){switch(c){case"string":i.elseIf((0,oe._)`${o} == "number" || ${o} == "boolean"`).assign(s,(0,oe._)`"" + ${a}`).elseIf((0,oe._)`${a} === null`).assign(s,(0,oe._)`""`);return;case"number":i.elseIf((0,oe._)`${o} == "boolean" || ${a} === null
              || (${o} == "string" && ${a} && ${a} == +${a})`).assign(s,(0,oe._)`+${a}`);return;case"integer":i.elseIf((0,oe._)`${o} === "boolean" || ${a} === null
              || (${o} === "string" && ${a} && ${a} == +${a} && !(${a} % 1))`).assign(s,(0,oe._)`+${a}`);return;case"boolean":i.elseIf((0,oe._)`${a} === "false" || ${a} === 0 || ${a} === null`).assign(s,!1).elseIf((0,oe._)`${a} === "true" || ${a} === 1`).assign(s,!0);return;case"null":i.elseIf((0,oe._)`${a} === "" || ${a} === 0 || ${a} === false`),i.assign(s,null);return;case"array":i.elseIf((0,oe._)`${o} === "string" || ${o} === "number"
              || ${o} === "boolean" || ${a} === null`).assign(s,(0,oe._)`[${a}]`)}}}function qS({gen:n,parentData:t,parentDataProperty:e},i){n.if((0,oe._)`${t} !== undefined`,()=>n.assign((0,oe._)`${t}[${e}]`,i))}function Cm(n,t,e,i=za.Correct){let a=i===za.Correct?oe.operators.EQ:oe.operators.NEQ,r;switch(n){case"null":return(0,oe._)`${t} ${a} null`;case"array":r=(0,oe._)`Array.isArray(${t})`;break;case"object":r=(0,oe._)`${t} && typeof ${t} == "object" && !Array.isArray(${t})`;break;case"integer":r=o((0,oe._)`!(${t} % 1) && !isNaN(${t})`);break;case"number":r=o();break;default:return(0,oe._)`typeof ${t} ${a} ${n}`}return i===za.Correct?r:(0,oe.not)(r);function o(s=oe.nil){return(0,oe.and)((0,oe._)`typeof ${t} == "number"`,s,e?(0,oe._)`isFinite(${t})`:oe.nil)}}Dt.checkDataType=Cm;function Em(n,t,e,i){if(n.length===1)return Cm(n[0],t,e,i);let a,r=(0,tb.toHash)(n);if(r.array&&r.object){let o=(0,oe._)`typeof ${t} != "object"`;a=r.null?o:(0,oe._)`!${t} || ${o}`,delete r.null,delete r.array,delete r.object}else a=oe.nil;r.number&&delete r.integer;for(let o in r)a=(0,oe.and)(a,Cm(o,t,e,i));return a}Dt.checkDataTypes=Em;var GS={message:({schema:n})=>`must be ${n}`,params:({schema:n,schemaValue:t})=>typeof n=="string"?(0,oe._)`{type: ${n}}`:(0,oe._)`{type: ${t}}`};function Dm(n){let t=WS(n);(0,VS.reportError)(t,GS)}Dt.reportTypeError=Dm;function WS(n){let{gen:t,data:e,schema:i}=n,a=(0,tb.schemaRefOrVal)(n,i,"type");return{gen:t,keyword:"type",data:e,schema:i.type,schemaCode:a,schemaValue:a,parentSchema:i,params:{},it:n}}});var rb=L(Ol=>{"use strict";Object.defineProperty(Ol,"__esModule",{value:!0});Ol.assignDefaults=void 0;var Ha=ue(),KS=Se();function YS(n,t){let{properties:e,items:i}=n.schema;if(t==="object"&&e)for(let a in e)ab(n,a,e[a].default);else t==="array"&&Array.isArray(i)&&i.forEach((a,r)=>ab(n,r,a.default))}Ol.assignDefaults=YS;function ab(n,t,e){let{gen:i,compositeRule:a,data:r,opts:o}=n;if(e===void 0)return;let s=(0,Ha._)`${r}${(0,Ha.getProperty)(t)}`;if(a){(0,KS.checkStrictMode)(n,`default is ignored for: ${s}`);return}let l=(0,Ha._)`${s} === undefined`;o.useDefaults==="empty"&&(l=(0,Ha._)`${l} || ${s} === null || ${s} === ""`),i.if(l,(0,Ha._)`${s} = ${(0,Ha.stringify)(e)}`)}});var tn=L(Pe=>{"use strict";Object.defineProperty(Pe,"__esModule",{value:!0});Pe.validateUnion=Pe.validateArray=Pe.usePattern=Pe.callValidateCode=Pe.schemaProperties=Pe.allSchemaProperties=Pe.noPropertyInData=Pe.propertyInData=Pe.isOwnProperty=Pe.hasPropFunc=Pe.reportMissingProp=Pe.checkMissingProp=Pe.checkReportMissingProp=void 0;var $e=ue(),Mm=Se(),vi=Yn(),XS=Se();function ZS(n,t){let{gen:e,data:i,it:a}=n;e.if(Im(e,i,t,a.opts.ownProperties),()=>{n.setParams({missingProperty:(0,$e._)`${t}`},!0),n.error()})}Pe.checkReportMissingProp=ZS;function QS({gen:n,data:t,it:{opts:e}},i,a){return(0,$e.or)(...i.map(r=>(0,$e.and)(Im(n,t,r,e.ownProperties),(0,$e._)`${a} = ${r}`)))}Pe.checkMissingProp=QS;function JS(n,t){n.setParams({missingProperty:t},!0),n.error()}Pe.reportMissingProp=JS;function ob(n){return n.scopeValue("func",{ref:Object.prototype.hasOwnProperty,code:(0,$e._)`Object.prototype.hasOwnProperty`})}Pe.hasPropFunc=ob;function Nm(n,t,e){return(0,$e._)`${ob(n)}.call(${t}, ${e})`}Pe.isOwnProperty=Nm;function ek(n,t,e,i){let a=(0,$e._)`${t}${(0,$e.getProperty)(e)} !== undefined`;return i?(0,$e._)`${a} && ${Nm(n,t,e)}`:a}Pe.propertyInData=ek;function Im(n,t,e,i){let a=(0,$e._)`${t}${(0,$e.getProperty)(e)} === undefined`;return i?(0,$e.or)(a,(0,$e.not)(Nm(n,t,e))):a}Pe.noPropertyInData=Im;function sb(n){return n?Object.keys(n).filter(t=>t!=="__proto__"):[]}Pe.allSchemaProperties=sb;function tk(n,t){return sb(t).filter(e=>!(0,Mm.alwaysValidSchema)(n,t[e]))}Pe.schemaProperties=tk;function nk({schemaCode:n,data:t,it:{gen:e,topSchemaRef:i,schemaPath:a,errorPath:r},it:o},s,l,c){let d=c?(0,$e._)`${n}, ${t}, ${i}${a}`:t,m=[[vi.default.instancePath,(0,$e.strConcat)(vi.default.instancePath,r)],[vi.default.parentData,o.parentData],[vi.default.parentDataProperty,o.parentDataProperty],[vi.default.rootData,vi.default.rootData]];o.opts.dynamicRef&&m.push([vi.default.dynamicAnchors,vi.default.dynamicAnchors]);let u=(0,$e._)`${d}, ${e.object(...m)}`;return l!==$e.nil?(0,$e._)`${s}.call(${l}, ${u})`:(0,$e._)`${s}(${u})`}Pe.callValidateCode=nk;var ik=(0,$e._)`new RegExp`;function ak({gen:n,it:{opts:t}},e){let i=t.unicodeRegExp?"u":"",{regExp:a}=t.code,r=a(e,i);return n.scopeValue("pattern",{key:r.toString(),ref:r,code:(0,$e._)`${a.code==="new RegExp"?ik:(0,XS.useFunc)(n,a)}(${e}, ${i})`})}Pe.usePattern=ak;function rk(n){let{gen:t,data:e,keyword:i,it:a}=n,r=t.name("valid");if(a.allErrors){let s=t.let("valid",!0);return o(()=>t.assign(s,!1)),s}return t.var(r,!0),o(()=>t.break()),r;function o(s){let l=t.const("len",(0,$e._)`${e}.length`);t.forRange("i",0,l,c=>{n.subschema({keyword:i,dataProp:c,dataPropType:Mm.Type.Num},r),t.if((0,$e.not)(r),s)})}}Pe.validateArray=rk;function ok(n){let{gen:t,schema:e,keyword:i,it:a}=n;if(!Array.isArray(e))throw new Error("ajv implementation error");if(e.some(l=>(0,Mm.alwaysValidSchema)(a,l))&&!a.opts.unevaluated)return;let o=t.let("valid",!1),s=t.name("_valid");t.block(()=>e.forEach((l,c)=>{let d=n.subschema({keyword:i,schemaProp:c,compositeRule:!0},s);t.assign(o,(0,$e._)`${o} || ${s}`),n.mergeValidEvaluated(d,s)||t.if((0,$e.not)(o))})),n.result(o,()=>n.reset(),()=>n.error(!0))}Pe.validateUnion=ok});var db=L(An=>{"use strict";Object.defineProperty(An,"__esModule",{value:!0});An.validateKeywordUsage=An.validSchemaType=An.funcKeywordCode=An.macroKeywordCode=void 0;var Ft=ue(),Zi=Yn(),sk=tn(),lk=vo();function ck(n,t){let{gen:e,keyword:i,schema:a,parentSchema:r,it:o}=n,s=t.macro.call(o.self,a,r,o),l=cb(e,i,s);o.opts.validateSchema!==!1&&o.self.validateSchema(s,!0);let c=e.name("valid");n.subschema({schema:s,schemaPath:Ft.nil,errSchemaPath:`${o.errSchemaPath}/${i}`,topSchemaRef:l,compositeRule:!0},c),n.pass(c,()=>n.error(!0))}An.macroKeywordCode=ck;function dk(n,t){var e;let{gen:i,keyword:a,schema:r,parentSchema:o,$data:s,it:l}=n;pk(l,t);let c=!s&&t.compile?t.compile.call(l.self,r,o,l):t.validate,d=cb(i,a,c),m=i.let("valid");n.block$data(m,u),n.ok((e=t.valid)!==null&&e!==void 0?e:m);function u(){if(t.errors===!1)b(),t.modifying&&lb(n),_(()=>n.error());else{let x=t.async?f():g();t.modifying&&lb(n),_(()=>mk(n,x))}}function f(){let x=i.let("ruleErrs",null);return i.try(()=>b((0,Ft._)`await `),w=>i.assign(m,!1).if((0,Ft._)`${w} instanceof ${l.ValidationError}`,()=>i.assign(x,(0,Ft._)`${w}.errors`),()=>i.throw(w))),x}function g(){let x=(0,Ft._)`${d}.errors`;return i.assign(x,null),b(Ft.nil),x}function b(x=t.async?(0,Ft._)`await `:Ft.nil){let w=l.opts.passContext?Zi.default.this:Zi.default.self,C=!("compile"in t&&!s||t.schema===!1);i.assign(m,(0,Ft._)`${x}${(0,sk.callValidateCode)(n,d,w,C)}`,t.modifying)}function _(x){var w;i.if((0,Ft.not)((w=t.valid)!==null&&w!==void 0?w:m),x)}}An.funcKeywordCode=dk;function lb(n){let{gen:t,data:e,it:i}=n;t.if(i.parentData,()=>t.assign(e,(0,Ft._)`${i.parentData}[${i.parentDataProperty}]`))}function mk(n,t){let{gen:e}=n;e.if((0,Ft._)`Array.isArray(${t})`,()=>{e.assign(Zi.default.vErrors,(0,Ft._)`${Zi.default.vErrors} === null ? ${t} : ${Zi.default.vErrors}.concat(${t})`).assign(Zi.default.errors,(0,Ft._)`${Zi.default.vErrors}.length`),(0,lk.extendErrors)(n)},()=>n.error())}function pk({schemaEnv:n},t){if(t.async&&!n.$async)throw new Error("async keyword in sync schema")}function cb(n,t,e){if(e===void 0)throw new Error(`keyword "${t}" failed to compile`);return n.scopeValue("keyword",typeof e=="function"?{ref:e}:{ref:e,code:(0,Ft.stringify)(e)})}function uk(n,t,e=!1){return!t.length||t.some(i=>i==="array"?Array.isArray(n):i==="object"?n&&typeof n=="object"&&!Array.isArray(n):typeof n==i||e&&typeof n>"u")}An.validSchemaType=uk;function hk({schema:n,opts:t,self:e,errSchemaPath:i},a,r){if(Array.isArray(a.keyword)?!a.keyword.includes(r):a.keyword!==r)throw new Error("ajv implementation error");let o=a.dependencies;if(o?.some(s=>!Object.prototype.hasOwnProperty.call(n,s)))throw new Error(`parent schema must have dependencies of ${r}: ${o.join(",")}`);if(a.validateSchema&&!a.validateSchema(n[r])){let l=`keyword "${r}" value is invalid at path "${i}": `+e.errorsText(a.validateSchema.errors);if(t.validateSchema==="log")e.logger.error(l);else throw new Error(l)}}An.validateKeywordUsage=hk});var pb=L(_i=>{"use strict";Object.defineProperty(_i,"__esModule",{value:!0});_i.extendSubschemaMode=_i.extendSubschemaData=_i.getSubschema=void 0;var Tn=ue(),mb=Se();function fk(n,{keyword:t,schemaProp:e,schema:i,schemaPath:a,errSchemaPath:r,topSchemaRef:o}){if(t!==void 0&&i!==void 0)throw new Error('both "keyword" and "schema" passed, only one allowed');if(t!==void 0){let s=n.schema[t];return e===void 0?{schema:s,schemaPath:(0,Tn._)`${n.schemaPath}${(0,Tn.getProperty)(t)}`,errSchemaPath:`${n.errSchemaPath}/${t}`}:{schema:s[e],schemaPath:(0,Tn._)`${n.schemaPath}${(0,Tn.getProperty)(t)}${(0,Tn.getProperty)(e)}`,errSchemaPath:`${n.errSchemaPath}/${t}/${(0,mb.escapeFragment)(e)}`}}if(i!==void 0){if(a===void 0||r===void 0||o===void 0)throw new Error('"schemaPath", "errSchemaPath" and "topSchemaRef" are required with "schema"');return{schema:i,schemaPath:a,topSchemaRef:o,errSchemaPath:r}}throw new Error('either "keyword" or "schema" must be passed')}_i.getSubschema=fk;function gk(n,t,{dataProp:e,dataPropType:i,data:a,dataTypes:r,propertyName:o}){if(a!==void 0&&e!==void 0)throw new Error('both "data" and "dataProp" passed, only one allowed');let{gen:s}=t;if(e!==void 0){let{errorPath:c,dataPathArr:d,opts:m}=t,u=s.let("data",(0,Tn._)`${t.data}${(0,Tn.getProperty)(e)}`,!0);l(u),n.errorPath=(0,Tn.str)`${c}${(0,mb.getErrorPath)(e,i,m.jsPropertySyntax)}`,n.parentDataProperty=(0,Tn._)`${e}`,n.dataPathArr=[...d,n.parentDataProperty]}if(a!==void 0){let c=a instanceof Tn.Name?a:s.let("data",a,!0);l(c),o!==void 0&&(n.propertyName=o)}r&&(n.dataTypes=r);function l(c){n.data=c,n.dataLevel=t.dataLevel+1,n.dataTypes=[],t.definedProperties=new Set,n.parentData=t.data,n.dataNames=[...t.dataNames,c]}}_i.extendSubschemaData=gk;function bk(n,{jtdDiscriminator:t,jtdMetadata:e,compositeRule:i,createErrors:a,allErrors:r}){i!==void 0&&(n.compositeRule=i),a!==void 0&&(n.createErrors=a),r!==void 0&&(n.allErrors=r),n.jtdDiscriminator=t,n.jtdMetadata=e}_i.extendSubschemaMode=bk});var Am=L((cj,ub)=>{"use strict";ub.exports=function n(t,e){if(t===e)return!0;if(t&&e&&typeof t=="object"&&typeof e=="object"){if(t.constructor!==e.constructor)return!1;var i,a,r;if(Array.isArray(t)){if(i=t.length,i!=e.length)return!1;for(a=i;a--!==0;)if(!n(t[a],e[a]))return!1;return!0}if(t.constructor===RegExp)return t.source===e.source&&t.flags===e.flags;if(t.valueOf!==Object.prototype.valueOf)return t.valueOf()===e.valueOf();if(t.toString!==Object.prototype.toString)return t.toString()===e.toString();if(r=Object.keys(t),i=r.length,i!==Object.keys(e).length)return!1;for(a=i;a--!==0;)if(!Object.prototype.hasOwnProperty.call(e,r[a]))return!1;for(a=i;a--!==0;){var o=r[a];if(!n(t[o],e[o]))return!1}return!0}return t!==t&&e!==e}});var fb=L((dj,hb)=>{"use strict";var yi=hb.exports=function(n,t,e){typeof t=="function"&&(e=t,t={}),e=t.cb||e;var i=typeof e=="function"?e:e.pre||function(){},a=e.post||function(){};Rl(t,i,a,n,"",n)};yi.keywords={additionalItems:!0,items:!0,contains:!0,additionalProperties:!0,propertyNames:!0,not:!0,if:!0,then:!0,else:!0};yi.arrayKeywords={items:!0,allOf:!0,anyOf:!0,oneOf:!0};yi.propsKeywords={$defs:!0,definitions:!0,properties:!0,patternProperties:!0,dependencies:!0};yi.skipKeywords={default:!0,enum:!0,const:!0,required:!0,maximum:!0,minimum:!0,exclusiveMaximum:!0,exclusiveMinimum:!0,multipleOf:!0,maxLength:!0,minLength:!0,pattern:!0,format:!0,maxItems:!0,minItems:!0,uniqueItems:!0,maxProperties:!0,minProperties:!0};function Rl(n,t,e,i,a,r,o,s,l,c){if(i&&typeof i=="object"&&!Array.isArray(i)){t(i,a,r,o,s,l,c);for(var d in i){var m=i[d];if(Array.isArray(m)){if(d in yi.arrayKeywords)for(var u=0;u<m.length;u++)Rl(n,t,e,m[u],a+"/"+d+"/"+u,r,a,d,i,u)}else if(d in yi.propsKeywords){if(m&&typeof m=="object")for(var f in m)Rl(n,t,e,m[f],a+"/"+d+"/"+vk(f),r,a,d,i,f)}else(d in yi.keywords||n.allKeys&&!(d in yi.skipKeywords))&&Rl(n,t,e,m,a+"/"+d,r,a,d,i)}e(i,a,r,o,s,l,c)}}function vk(n){return n.replace(/~/g,"~0").replace(/\//g,"~1")}});var yo=L($t=>{"use strict";Object.defineProperty($t,"__esModule",{value:!0});$t.getSchemaRefs=$t.resolveUrl=$t.normalizeId=$t._getFullPath=$t.getFullPath=$t.inlineRef=void 0;var _k=Se(),yk=Am(),xk=fb(),wk=new Set(["type","format","pattern","maxLength","minLength","maxProperties","minProperties","maxItems","minItems","maximum","minimum","uniqueItems","multipleOf","required","enum","const"]);function Sk(n,t=!0){return typeof n=="boolean"?!0:t===!0?!Tm(n):t?gb(n)<=t:!1}$t.inlineRef=Sk;var kk=new Set(["$ref","$recursiveRef","$recursiveAnchor","$dynamicRef","$dynamicAnchor"]);function Tm(n){for(let t in n){if(kk.has(t))return!0;let e=n[t];if(Array.isArray(e)&&e.some(Tm)||typeof e=="object"&&Tm(e))return!0}return!1}function gb(n){let t=0;for(let e in n){if(e==="$ref")return 1/0;if(t++,!wk.has(e)&&(typeof n[e]=="object"&&(0,_k.eachItem)(n[e],i=>t+=gb(i)),t===1/0))return 1/0}return t}function bb(n,t="",e){e!==!1&&(t=Ua(t));let i=n.parse(t);return vb(n,i)}$t.getFullPath=bb;function vb(n,t){return n.serialize(t).split("#")[0]+"#"}$t._getFullPath=vb;var Ck=/#\/?$/;function Ua(n){return n?n.replace(Ck,""):""}$t.normalizeId=Ua;function Ek(n,t,e){return e=Ua(e),n.resolve(t,e)}$t.resolveUrl=Ek;var Dk=/^[a-z_][-a-z0-9._]*$/i;function Mk(n,t){if(typeof n=="boolean")return{};let{schemaId:e,uriResolver:i}=this.opts,a=Ua(n[e]||t),r={"":a},o=bb(i,a,!1),s={},l=new Set;return xk(n,{allKeys:!0},(m,u,f,g)=>{if(g===void 0)return;let b=o+u,_=r[g];typeof m[e]=="string"&&(_=x.call(this,m[e])),w.call(this,m.$anchor),w.call(this,m.$dynamicAnchor),r[u]=_;function x(C){let F=this.opts.uriResolver.resolve;if(C=Ua(_?F(_,C):C),l.has(C))throw d(C);l.add(C);let A=this.refs[C];return typeof A=="string"&&(A=this.refs[A]),typeof A=="object"?c(m,A.schema,C):C!==Ua(b)&&(C[0]==="#"?(c(m,s[C],C),s[C]=m):this.refs[C]=b),C}function w(C){if(typeof C=="string"){if(!Dk.test(C))throw new Error(`invalid anchor "${C}"`);x.call(this,`#${C}`)}}}),s;function c(m,u,f){if(u!==void 0&&!yk(m,u))throw d(f)}function d(m){return new Error(`reference "${m}" resolves to more than one schema`)}}$t.getSchemaRefs=Mk});var So=L(xi=>{"use strict";Object.defineProperty(xi,"__esModule",{value:!0});xi.getData=xi.KeywordCxt=xi.validateFunctionCode=void 0;var Sb=Qg(),_b=_o(),Rm=km(),Pl=_o(),Nk=rb(),wo=db(),Om=pb(),G=ue(),ie=Yn(),Ik=yo(),Xn=Se(),xo=vo();function Ak(n){if(Eb(n)&&(Db(n),Cb(n))){Rk(n);return}kb(n,()=>(0,Sb.topBoolOrEmptySchema)(n))}xi.validateFunctionCode=Ak;function kb({gen:n,validateName:t,schema:e,schemaEnv:i,opts:a},r){a.code.es5?n.func(t,(0,G._)`${ie.default.data}, ${ie.default.valCxt}`,i.$async,()=>{n.code((0,G._)`"use strict"; ${yb(e,a)}`),Ok(n,a),n.code(r)}):n.func(t,(0,G._)`${ie.default.data}, ${Tk(a)}`,i.$async,()=>n.code(yb(e,a)).code(r))}function Tk(n){return(0,G._)`{${ie.default.instancePath}="", ${ie.default.parentData}, ${ie.default.parentDataProperty}, ${ie.default.rootData}=${ie.default.data}${n.dynamicRef?(0,G._)`, ${ie.default.dynamicAnchors}={}`:G.nil}}={}`}function Ok(n,t){n.if(ie.default.valCxt,()=>{n.var(ie.default.instancePath,(0,G._)`${ie.default.valCxt}.${ie.default.instancePath}`),n.var(ie.default.parentData,(0,G._)`${ie.default.valCxt}.${ie.default.parentData}`),n.var(ie.default.parentDataProperty,(0,G._)`${ie.default.valCxt}.${ie.default.parentDataProperty}`),n.var(ie.default.rootData,(0,G._)`${ie.default.valCxt}.${ie.default.rootData}`),t.dynamicRef&&n.var(ie.default.dynamicAnchors,(0,G._)`${ie.default.valCxt}.${ie.default.dynamicAnchors}`)},()=>{n.var(ie.default.instancePath,(0,G._)`""`),n.var(ie.default.parentData,(0,G._)`undefined`),n.var(ie.default.parentDataProperty,(0,G._)`undefined`),n.var(ie.default.rootData,ie.default.data),t.dynamicRef&&n.var(ie.default.dynamicAnchors,(0,G._)`{}`)})}function Rk(n){let{schema:t,opts:e,gen:i}=n;kb(n,()=>{e.$comment&&t.$comment&&Nb(n),jk(n),i.let(ie.default.vErrors,null),i.let(ie.default.errors,0),e.unevaluated&&Pk(n),Mb(n),zk(n)})}function Pk(n){let{gen:t,validateName:e}=n;n.evaluated=t.const("evaluated",(0,G._)`${e}.evaluated`),t.if((0,G._)`${n.evaluated}.dynamicProps`,()=>t.assign((0,G._)`${n.evaluated}.props`,(0,G._)`undefined`)),t.if((0,G._)`${n.evaluated}.dynamicItems`,()=>t.assign((0,G._)`${n.evaluated}.items`,(0,G._)`undefined`))}function yb(n,t){let e=typeof n=="object"&&n[t.schemaId];return e&&(t.code.source||t.code.process)?(0,G._)`/*# sourceURL=${e} */`:G.nil}function Fk(n,t){if(Eb(n)&&(Db(n),Cb(n))){Lk(n,t);return}(0,Sb.boolOrEmptySchema)(n,t)}function Cb({schema:n,self:t}){if(typeof n=="boolean")return!n;for(let e in n)if(t.RULES.all[e])return!0;return!1}function Eb(n){return typeof n.schema!="boolean"}function Lk(n,t){let{schema:e,gen:i,opts:a}=n;a.$comment&&e.$comment&&Nb(n),Vk(n),$k(n);let r=i.const("_errs",ie.default.errors);Mb(n,r),i.var(t,(0,G._)`${r} === ${ie.default.errors}`)}function Db(n){(0,Xn.checkUnknownRules)(n),Bk(n)}function Mb(n,t){if(n.opts.jtd)return xb(n,[],!1,t);let e=(0,_b.getSchemaTypes)(n.schema),i=(0,_b.coerceAndCheckDataType)(n,e);xb(n,e,!i,t)}function Bk(n){let{schema:t,errSchemaPath:e,opts:i,self:a}=n;t.$ref&&i.ignoreKeywordsWithRef&&(0,Xn.schemaHasRulesButRef)(t,a.RULES)&&a.logger.warn(`$ref: keywords ignored in schema at path "${e}"`)}function jk(n){let{schema:t,opts:e}=n;t.default!==void 0&&e.useDefaults&&e.strictSchema&&(0,Xn.checkStrictMode)(n,"default is ignored in the schema root")}function Vk(n){let t=n.schema[n.opts.schemaId];t&&(n.baseId=(0,Ik.resolveUrl)(n.opts.uriResolver,n.baseId,t))}function $k(n){if(n.schema.$async&&!n.schemaEnv.$async)throw new Error("async schema in sync schema")}function Nb({gen:n,schemaEnv:t,schema:e,errSchemaPath:i,opts:a}){let r=e.$comment;if(a.$comment===!0)n.code((0,G._)`${ie.default.self}.logger.log(${r})`);else if(typeof a.$comment=="function"){let o=(0,G.str)`${i}/$comment`,s=n.scopeValue("root",{ref:t.root});n.code((0,G._)`${ie.default.self}.opts.$comment(${r}, ${o}, ${s}.schema)`)}}function zk(n){let{gen:t,schemaEnv:e,validateName:i,ValidationError:a,opts:r}=n;e.$async?t.if((0,G._)`${ie.default.errors} === 0`,()=>t.return(ie.default.data),()=>t.throw((0,G._)`new ${a}(${ie.default.vErrors})`)):(t.assign((0,G._)`${i}.errors`,ie.default.vErrors),r.unevaluated&&Hk(n),t.return((0,G._)`${ie.default.errors} === 0`))}function Hk({gen:n,evaluated:t,props:e,items:i}){e instanceof G.Name&&n.assign((0,G._)`${t}.props`,e),i instanceof G.Name&&n.assign((0,G._)`${t}.items`,i)}function xb(n,t,e,i){let{gen:a,schema:r,data:o,allErrors:s,opts:l,self:c}=n,{RULES:d}=c;if(r.$ref&&(l.ignoreKeywordsWithRef||!(0,Xn.schemaHasRulesButRef)(r,d))){a.block(()=>Ab(n,"$ref",d.all.$ref.definition));return}l.jtd||Uk(n,t),a.block(()=>{for(let u of d.rules)m(u);m(d.post)});function m(u){(0,Rm.shouldUseGroup)(r,u)&&(u.type?(a.if((0,Pl.checkDataType)(u.type,o,l.strictNumbers)),wb(n,u),t.length===1&&t[0]===u.type&&e&&(a.else(),(0,Pl.reportTypeError)(n)),a.endIf()):wb(n,u),s||a.if((0,G._)`${ie.default.errors} === ${i||0}`))}}function wb(n,t){let{gen:e,schema:i,opts:{useDefaults:a}}=n;a&&(0,Nk.assignDefaults)(n,t.type),e.block(()=>{for(let r of t.rules)(0,Rm.shouldUseRule)(i,r)&&Ab(n,r.keyword,r.definition,t.type)})}function Uk(n,t){n.schemaEnv.meta||!n.opts.strictTypes||(qk(n,t),n.opts.allowUnionTypes||Gk(n,t),Wk(n,n.dataTypes))}function qk(n,t){if(t.length){if(!n.dataTypes.length){n.dataTypes=t;return}t.forEach(e=>{Ib(n.dataTypes,e)||Pm(n,`type "${e}" not allowed by context "${n.dataTypes.join(",")}"`)}),Yk(n,t)}}function Gk(n,t){t.length>1&&!(t.length===2&&t.includes("null"))&&Pm(n,"use allowUnionTypes to allow union type keyword")}function Wk(n,t){let e=n.self.RULES.all;for(let i in e){let a=e[i];if(typeof a=="object"&&(0,Rm.shouldUseRule)(n.schema,a)){let{type:r}=a.definition;r.length&&!r.some(o=>Kk(t,o))&&Pm(n,`missing type "${r.join(",")}" for keyword "${i}"`)}}}function Kk(n,t){return n.includes(t)||t==="number"&&n.includes("integer")}function Ib(n,t){return n.includes(t)||t==="integer"&&n.includes("number")}function Yk(n,t){let e=[];for(let i of n.dataTypes)Ib(t,i)?e.push(i):t.includes("integer")&&i==="number"&&e.push("integer");n.dataTypes=e}function Pm(n,t){let e=n.schemaEnv.baseId+n.errSchemaPath;t+=` at "${e}" (strictTypes)`,(0,Xn.checkStrictMode)(n,t,n.opts.strictTypes)}var Fl=class{constructor(t,e,i){if((0,wo.validateKeywordUsage)(t,e,i),this.gen=t.gen,this.allErrors=t.allErrors,this.keyword=i,this.data=t.data,this.schema=t.schema[i],this.$data=e.$data&&t.opts.$data&&this.schema&&this.schema.$data,this.schemaValue=(0,Xn.schemaRefOrVal)(t,this.schema,i,this.$data),this.schemaType=e.schemaType,this.parentSchema=t.schema,this.params={},this.it=t,this.def=e,this.$data)this.schemaCode=t.gen.const("vSchema",Tb(this.$data,t));else if(this.schemaCode=this.schemaValue,!(0,wo.validSchemaType)(this.schema,e.schemaType,e.allowUndefined))throw new Error(`${i} value must be ${JSON.stringify(e.schemaType)}`);("code"in e?e.trackErrors:e.errors!==!1)&&(this.errsCount=t.gen.const("_errs",ie.default.errors))}result(t,e,i){this.failResult((0,G.not)(t),e,i)}failResult(t,e,i){this.gen.if(t),i?i():this.error(),e?(this.gen.else(),e(),this.allErrors&&this.gen.endIf()):this.allErrors?this.gen.endIf():this.gen.else()}pass(t,e){this.failResult((0,G.not)(t),void 0,e)}fail(t){if(t===void 0){this.error(),this.allErrors||this.gen.if(!1);return}this.gen.if(t),this.error(),this.allErrors?this.gen.endIf():this.gen.else()}fail$data(t){if(!this.$data)return this.fail(t);let{schemaCode:e}=this;this.fail((0,G._)`${e} !== undefined && (${(0,G.or)(this.invalid$data(),t)})`)}error(t,e,i){if(e){this.setParams(e),this._error(t,i),this.setParams({});return}this._error(t,i)}_error(t,e){(t?xo.reportExtraError:xo.reportError)(this,this.def.error,e)}$dataError(){(0,xo.reportError)(this,this.def.$dataError||xo.keyword$DataError)}reset(){if(this.errsCount===void 0)throw new Error('add "trackErrors" to keyword definition');(0,xo.resetErrorsCount)(this.gen,this.errsCount)}ok(t){this.allErrors||this.gen.if(t)}setParams(t,e){e?Object.assign(this.params,t):this.params=t}block$data(t,e,i=G.nil){this.gen.block(()=>{this.check$data(t,i),e()})}check$data(t=G.nil,e=G.nil){if(!this.$data)return;let{gen:i,schemaCode:a,schemaType:r,def:o}=this;i.if((0,G.or)((0,G._)`${a} === undefined`,e)),t!==G.nil&&i.assign(t,!0),(r.length||o.validateSchema)&&(i.elseIf(this.invalid$data()),this.$dataError(),t!==G.nil&&i.assign(t,!1)),i.else()}invalid$data(){let{gen:t,schemaCode:e,schemaType:i,def:a,it:r}=this;return(0,G.or)(o(),s());function o(){if(i.length){if(!(e instanceof G.Name))throw new Error("ajv implementation error");let l=Array.isArray(i)?i:[i];return(0,G._)`${(0,Pl.checkDataTypes)(l,e,r.opts.strictNumbers,Pl.DataType.Wrong)}`}return G.nil}function s(){if(a.validateSchema){let l=t.scopeValue("validate$data",{ref:a.validateSchema});return(0,G._)`!${l}(${e})`}return G.nil}}subschema(t,e){let i=(0,Om.getSubschema)(this.it,t);(0,Om.extendSubschemaData)(i,this.it,t),(0,Om.extendSubschemaMode)(i,t);let a=Ee(N(N({},this.it),i),{items:void 0,props:void 0});return Fk(a,e),a}mergeEvaluated(t,e){let{it:i,gen:a}=this;i.opts.unevaluated&&(i.props!==!0&&t.props!==void 0&&(i.props=Xn.mergeEvaluated.props(a,t.props,i.props,e)),i.items!==!0&&t.items!==void 0&&(i.items=Xn.mergeEvaluated.items(a,t.items,i.items,e)))}mergeValidEvaluated(t,e){let{it:i,gen:a}=this;if(i.opts.unevaluated&&(i.props!==!0||i.items!==!0))return a.if(e,()=>this.mergeEvaluated(t,G.Name)),!0}};xi.KeywordCxt=Fl;function Ab(n,t,e,i){let a=new Fl(n,e,t);"code"in e?e.code(a,i):a.$data&&e.validate?(0,wo.funcKeywordCode)(a,e):"macro"in e?(0,wo.macroKeywordCode)(a,e):(e.compile||e.validate)&&(0,wo.funcKeywordCode)(a,e)}var Xk=/^\/(?:[^~]|~0|~1)*$/,Zk=/^([0-9]+)(#|\/(?:[^~]|~0|~1)*)?$/;function Tb(n,{dataLevel:t,dataNames:e,dataPathArr:i}){let a,r;if(n==="")return ie.default.rootData;if(n[0]==="/"){if(!Xk.test(n))throw new Error(`Invalid JSON-pointer: ${n}`);a=n,r=ie.default.rootData}else{let c=Zk.exec(n);if(!c)throw new Error(`Invalid JSON-pointer: ${n}`);let d=+c[1];if(a=c[2],a==="#"){if(d>=t)throw new Error(l("property/index",d));return i[t-d]}if(d>t)throw new Error(l("data",d));if(r=e[t-d],!a)return r}let o=r,s=a.split("/");for(let c of s)c&&(r=(0,G._)`${r}${(0,G.getProperty)((0,Xn.unescapeJsonPointer)(c))}`,o=(0,G._)`${o} && ${r}`);return o;function l(c,d){return`Cannot access ${c} ${d} levels up, current level is ${t}`}}xi.getData=Tb});var Ll=L(Lm=>{"use strict";Object.defineProperty(Lm,"__esModule",{value:!0});var Fm=class extends Error{constructor(t){super("validation failed"),this.errors=t,this.ajv=this.validation=!0}};Lm.default=Fm});var ko=L(Vm=>{"use strict";Object.defineProperty(Vm,"__esModule",{value:!0});var Bm=yo(),jm=class extends Error{constructor(t,e,i,a){super(a||`can't resolve reference ${i} from id ${e}`),this.missingRef=(0,Bm.resolveUrl)(t,e,i),this.missingSchema=(0,Bm.normalizeId)((0,Bm.getFullPath)(t,this.missingRef))}};Vm.default=jm});var jl=L(nn=>{"use strict";Object.defineProperty(nn,"__esModule",{value:!0});nn.resolveSchema=nn.getCompilingSchema=nn.resolveRef=nn.compileSchema=nn.SchemaEnv=void 0;var fn=ue(),Qk=Ll(),Qi=Yn(),gn=yo(),Ob=Se(),Jk=So(),qa=class{constructor(t){var e;this.refs={},this.dynamicAnchors={};let i;typeof t.schema=="object"&&(i=t.schema),this.schema=t.schema,this.schemaId=t.schemaId,this.root=t.root||this,this.baseId=(e=t.baseId)!==null&&e!==void 0?e:(0,gn.normalizeId)(i?.[t.schemaId||"$id"]),this.schemaPath=t.schemaPath,this.localRefs=t.localRefs,this.meta=t.meta,this.$async=i?.$async,this.refs={}}};nn.SchemaEnv=qa;function zm(n){let t=Rb.call(this,n);if(t)return t;let e=(0,gn.getFullPath)(this.opts.uriResolver,n.root.baseId),{es5:i,lines:a}=this.opts.code,{ownProperties:r}=this.opts,o=new fn.CodeGen(this.scope,{es5:i,lines:a,ownProperties:r}),s;n.$async&&(s=o.scopeValue("Error",{ref:Qk.default,code:(0,fn._)`require("ajv/dist/runtime/validation_error").default`}));let l=o.scopeName("validate");n.validateName=l;let c={gen:o,allErrors:this.opts.allErrors,data:Qi.default.data,parentData:Qi.default.parentData,parentDataProperty:Qi.default.parentDataProperty,dataNames:[Qi.default.data],dataPathArr:[fn.nil],dataLevel:0,dataTypes:[],definedProperties:new Set,topSchemaRef:o.scopeValue("schema",this.opts.code.source===!0?{ref:n.schema,code:(0,fn.stringify)(n.schema)}:{ref:n.schema}),validateName:l,ValidationError:s,schema:n.schema,schemaEnv:n,rootId:e,baseId:n.baseId||e,schemaPath:fn.nil,errSchemaPath:n.schemaPath||(this.opts.jtd?"":"#"),errorPath:(0,fn._)`""`,opts:this.opts,self:this},d;try{this._compilations.add(n),(0,Jk.validateFunctionCode)(c),o.optimize(this.opts.code.optimize);let m=o.toString();d=`${o.scopeRefs(Qi.default.scope)}return ${m}`,this.opts.code.process&&(d=this.opts.code.process(d,n));let f=new Function(`${Qi.default.self}`,`${Qi.default.scope}`,d)(this,this.scope.get());if(this.scope.value(l,{ref:f}),f.errors=null,f.schema=n.schema,f.schemaEnv=n,n.$async&&(f.$async=!0),this.opts.code.source===!0&&(f.source={validateName:l,validateCode:m,scopeValues:o._values}),this.opts.unevaluated){let{props:g,items:b}=c;f.evaluated={props:g instanceof fn.Name?void 0:g,items:b instanceof fn.Name?void 0:b,dynamicProps:g instanceof fn.Name,dynamicItems:b instanceof fn.Name},f.source&&(f.source.evaluated=(0,fn.stringify)(f.evaluated))}return n.validate=f,n}catch(m){throw delete n.validate,delete n.validateName,d&&this.logger.error("Error compiling schema, function code:",d),m}finally{this._compilations.delete(n)}}nn.compileSchema=zm;function eC(n,t,e){var i;e=(0,gn.resolveUrl)(this.opts.uriResolver,t,e);let a=n.refs[e];if(a)return a;let r=iC.call(this,n,e);if(r===void 0){let o=(i=n.localRefs)===null||i===void 0?void 0:i[e],{schemaId:s}=this.opts;o&&(r=new qa({schema:o,schemaId:s,root:n,baseId:t}))}if(r!==void 0)return n.refs[e]=tC.call(this,r)}nn.resolveRef=eC;function tC(n){return(0,gn.inlineRef)(n.schema,this.opts.inlineRefs)?n.schema:n.validate?n:zm.call(this,n)}function Rb(n){for(let t of this._compilations)if(nC(t,n))return t}nn.getCompilingSchema=Rb;function nC(n,t){return n.schema===t.schema&&n.root===t.root&&n.baseId===t.baseId}function iC(n,t){let e;for(;typeof(e=this.refs[t])=="string";)t=e;return e||this.schemas[t]||Bl.call(this,n,t)}function Bl(n,t){let e=this.opts.uriResolver.parse(t),i=(0,gn._getFullPath)(this.opts.uriResolver,e),a=(0,gn.getFullPath)(this.opts.uriResolver,n.baseId,void 0);if(Object.keys(n.schema).length>0&&i===a)return $m.call(this,e,n);let r=(0,gn.normalizeId)(i),o=this.refs[r]||this.schemas[r];if(typeof o=="string"){let s=Bl.call(this,n,o);return typeof s?.schema!="object"?void 0:$m.call(this,e,s)}if(typeof o?.schema=="object"){if(o.validate||zm.call(this,o),r===(0,gn.normalizeId)(t)){let{schema:s}=o,{schemaId:l}=this.opts,c=s[l];return c&&(a=(0,gn.resolveUrl)(this.opts.uriResolver,a,c)),new qa({schema:s,schemaId:l,root:n,baseId:a})}return $m.call(this,e,o)}}nn.resolveSchema=Bl;var aC=new Set(["properties","patternProperties","enum","dependencies","definitions"]);function $m(n,{baseId:t,schema:e,root:i}){var a;if(((a=n.fragment)===null||a===void 0?void 0:a[0])!=="/")return;for(let s of n.fragment.slice(1).split("/")){if(typeof e=="boolean")return;let l=e[(0,Ob.unescapeFragment)(s)];if(l===void 0)return;e=l;let c=typeof e=="object"&&e[this.opts.schemaId];!aC.has(s)&&c&&(t=(0,gn.resolveUrl)(this.opts.uriResolver,t,c))}let r;if(typeof e!="boolean"&&e.$ref&&!(0,Ob.schemaHasRulesButRef)(e,this.RULES)){let s=(0,gn.resolveUrl)(this.opts.uriResolver,t,e.$ref);r=Bl.call(this,i,s)}let{schemaId:o}=this.opts;if(r=r||new qa({schema:e,schemaId:o,root:i,baseId:t}),r.schema!==r.root.schema)return r}});var Pb=L((bj,rC)=>{rC.exports={$id:"https://raw.githubusercontent.com/ajv-validator/ajv/master/lib/refs/data.json#",description:"Meta-schema for $data reference (JSON AnySchema extension proposal)",type:"object",required:["$data"],properties:{$data:{type:"string",anyOf:[{format:"relative-json-pointer"},{format:"json-pointer"}]}},additionalProperties:!1}});var Km=L((vj,zb)=>{"use strict";var oC=RegExp.prototype.test.bind(/^[\da-f]{8}-[\da-f]{4}-[\da-f]{4}-[\da-f]{4}-[\da-f]{12}$/iu),Um=RegExp.prototype.test.bind(/^(?:(?:25[0-5]|2[0-4]\d|1\d{2}|[1-9]\d|\d)\.){3}(?:25[0-5]|2[0-4]\d|1\d{2}|[1-9]\d|\d)$/u),sC=RegExp.prototype.test.bind(/^\d*$/u),Ji=RegExp.prototype.test.bind(/^[\da-f]{2}$/iu),qm=RegExp.prototype.test.bind(/^[\da-z\-._~]$/iu),Lb=RegExp.prototype.test.bind(/^[A-Za-z0-9\-._~!$&'()*+,;=:@/]$/u),Gm=RegExp.prototype.test.bind(/^[A-Za-z0-9\-._~!$&'()*+,;=:@/?]$/u),lC=RegExp.prototype.test.bind(/^[A-Za-z0-9\-._~!$&'()*+,;=:]$/u),zt=new Array(256);{let n="0123456789ABCDEF";for(let t=0;t<256;t++)zt[t]="%"+n[t>>4]+n[t&15]}function Mt(n){return n<2048?zt[192|n>>6]+zt[128|n&63]:n<65536?zt[224|n>>12]+zt[128|n>>6&63]+zt[128|n&63]:zt[240|n>>18]+zt[128|n>>12&63]+zt[128|n>>6&63]+zt[128|n&63]}function cC(n){let t="",e=0,i=0;for(i=0;i<n.length;i++)if(e=n[i].charCodeAt(0),e!==48){if(!(e>=48&&e<=57||e>=65&&e<=70||e>=97&&e<=102))return"";t+=n[i];break}for(i+=1;i<n.length;i++){if(e=n[i].charCodeAt(0),!(e>=48&&e<=57||e>=65&&e<=70||e>=97&&e<=102))return"";t+=n[i]}return t}var dC=RegExp.prototype.test.bind(/^[\dA-Fa-f]{1,4}$/),mC=RegExp.prototype.test.bind(/^[vV][\dA-Fa-f]+\.[A-Za-z\d\-._~!$&'()*+,;=:]+$/),pC=RegExp.prototype.test.bind(/^[A-Za-z\d\-._~]$/),uC=RegExp.prototype.test.bind(/[^!"$&'()*+,\-.;=_`a-z{}~]/u);function hC(n){if(n.length===0)return!1;for(let t=0;t<n.length;t++)if(!pC(n[t])){if(n[t]==="%"&&t+2<n.length&&Ji(n.slice(t+1,t+3))){t+=2;continue}return!1}return!0}function Fb(n){let t=-1,e=0,i=-1,a=0;for(let s=0;s<n.length;s++)n[s]==="0"?(i===-1&&(i=s),a++,a>e&&(e=a,t=i)):(i=-1,a=0);if(e<2)return n.join(":");let r=n.slice(0,t).join(":"),o=n.slice(t+e).join(":");return r+"::"+o}function fC(n){let t=n.indexOf("::");if(t!==-1&&n.indexOf("::",t+1)!==-1)return;let e=t===-1?n.split(":"):n.slice(0,t).split(":"),i=t===-1?[]:n.slice(t+2).split(":");t!==-1&&(e.length===1&&e[0]===""&&(e.length=0),i.length===1&&i[0]===""&&(i.length=0));let a=e.concat(i),r=0;for(let s=0;s<a.length;s++){let l=a[s];if(l==="")return;if(l.indexOf(".")!==-1){if(s!==a.length-1||t!==-1&&i.length===0||!Um(l))return;r+=2;continue}if(!dC(l))return;a[s]=parseInt(l,16).toString(16),r++}if(t===-1)return r!==8?void 0:Fb(a);if(r>=8)return;let o=a.slice(0,e.length);for(let s=r;s<8;s++)o.push("0");for(let s=e.length;s<a.length;s++)o.push(a[s]);return Fb(o)}function Hm(n){let t=n[0]==="["&&n[n.length-1]==="]";if((n[0]==="["||n[n.length-1]==="]")&&!t)return{host:n,isIPV6:!1,error:!0};let i=t?n.slice(1,-1):n;if(t&&mC(i))return i=i.toLowerCase(),{host:`[${i}]`,escapedHost:i,isIPV6:!1,isIPVFuture:!0};if(gC(i,":")<2)return{host:n,isIPV6:!1,error:t};let a="",r=i.indexOf("%");if(r!==-1){let s=i.slice(r,r+3).toLowerCase()==="%25"?3:1;if(a=i.slice(r+s),!hC(a))return{host:n,isIPV6:!1,error:!0};i=i.slice(0,r)}let o=fC(i);return o===void 0?{host:n,isIPV6:!1,error:!0}:{host:o+(a?"%"+a:""),escapedHost:o+(a?"%25"+a:""),isIPV6:!0}}function gC(n,t){let e=0;for(let i=0;i<n.length;i++)n[i]===t&&e++;return e}function bC(n){let t=n,e=[],i=-1,a=0;for(;a=t.length;){if(a===1){if(t===".")break;if(t==="/"){e.push("/");break}else{e.push(t);break}}else if(a===2){if(t[0]==="."){if(t[1]===".")break;if(t[1]==="/"){t=t.slice(2);continue}}else if(t[0]==="/"&&(t[1]==="."||t[1]==="/")){e.push("/");break}}else if(a===3&&t==="/.."){e.length!==0&&e.pop(),e.push("/");break}if(t[0]==="."){if(t[1]==="."){if(t[2]==="/"){t=t.slice(3);continue}}else if(t[1]==="/"){t=t.slice(2);continue}}else if(t[0]==="/"&&t[1]==="."){if(t[2]==="/"){t=t.slice(2);continue}else if(t[2]==="."&&t[3]==="/"){t=t.slice(3),e.length!==0&&e.pop();continue}}if((i=t.indexOf("/",1))===-1){e.push(t);break}else e.push(t.slice(0,i)),t=t.slice(i)}return e.join("")}var vC={"@":"%40","/":"%2F","?":"%3F","#":"%23",":":"%3A"},_C=/[@/?#:]/g,yC=/[@/?#]/g;function Bb(n,t){let e=t?yC:_C;return e.lastIndex=0,n.replace(e,i=>vC[i])}function jb(n,t=!1){if(n.indexOf("%")===-1)return n;let e="";for(let i=0;i<n.length;i++){if(n[i]==="%"&&i+2<n.length){let a=n.slice(i+1,i+3);if(Ji(a)){let r=a.toUpperCase(),o=String.fromCharCode(parseInt(r,16));t&&qm(o)?e+=o:e+="%"+r,i+=2;continue}}e+=n[i]}return e}function xC(n){let t="";for(let e=0;e<n.length;e++){let i=n[e];if(i==="%"&&e+2<n.length){let a=n.slice(e+1,e+3);if(Ji(a)){let r=a.toUpperCase(),o=String.fromCharCode(parseInt(r,16));o!=="."&&qm(o)?t+=o:t+="%"+r,e+=2;continue}}if(Lb(i))t+=i;else{let a=n.charCodeAt(e);if(a<128)t+=$b(a)?i:zt[a];else if(a<55296||a>57343)t+=Mt(a);else if(a<=56319&&e+1<n.length){let r=n.charCodeAt(e+1);r>=56320&&r<=57343?(t+=Mt(65536+(a-55296<<10)+(r-56320)),e++):t+=Mt(65533)}else t+=Mt(65533)}}return t}function wC(n,t=!1){let e="",i=t&&n[0]!=="/";for(let a=0;a<n.length;a++){let r=n[a];if(r==="%"&&a+2<n.length){let o=n.slice(a+1,a+3);if(Ji(o)){e+="%"+o.toUpperCase(),a+=2;continue}}if(r==="/"&&(i=!1),Lb(r)&&(r!==":"||!i))e+=r;else{let o=n.charCodeAt(a);if(o<128)e+=zt[o];else if(o<55296||o>57343)e+=Mt(o);else if(o<=56319&&a+1<n.length){let s=n.charCodeAt(a+1);s>=56320&&s<=57343?(e+=Mt(65536+(o-55296<<10)+(s-56320)),a++):e+=Mt(65533)}else e+=Mt(65533)}}return e}function Wm(n,t){let e="";for(let i=0;i<n.length;i++){let a=n[i];if(a==="%"&&i+2<n.length){let r=n.slice(i+1,i+3);if(Ji(r)){e+="%"+r.toUpperCase(),i+=2;continue}}if(t(a))e+=a;else{let r=n.charCodeAt(i);if(r<128)e+=zt[r];else if(r<55296||r>57343)e+=Mt(r);else if(r<=56319&&i+1<n.length){let o=n.charCodeAt(i+1);o>=56320&&o<=57343?(e+=Mt(65536+(r-55296<<10)+(o-56320)),i++):e+=Mt(65533)}else e+=Mt(65533)}}return e}function Vb(n){return Wm(n,lC)}function SC(n){return Wm(n,Gm)}function kC(n){return Wm(n,Gm)}function $b(n){return n>=48&&n<=57||n>=65&&n<=90||n>=97&&n<=122||n===42||n===43||n===45||n===46||n===47||n===64||n===95}function CC(n){let t="";for(let e=0;e<n.length;e++){let i=n[e];if(i==="%"&&e+2<n.length){let a=n.slice(e+1,e+3);if(Ji(a)){let r=a.toUpperCase(),o=String.fromCharCode(parseInt(r,16));qm(o)?t+=o:t+="%"+r,e+=2;continue}}if(Gm(i))t+=i;else{let a=n.charCodeAt(e);if(a<128)t+=$b(a)?i:zt[a];else if(a<55296||a>57343)t+=Mt(a);else if(a<=56319&&e+1<n.length){let r=n.charCodeAt(e+1);r>=56320&&r<=57343?(t+=Mt(65536+(a-55296<<10)+(r-56320)),e++):t+=Mt(65533)}else t+=Mt(65533)}}return t}function EC(n){let t="";for(let e=0;e<n.length;e++){if(n[e]==="%"&&e+2<n.length){let i=n.slice(e+1,e+3);if(Ji(i)){t+="%"+i.toUpperCase(),e+=2;continue}}t+=escape(n[e])}return t}function DC(n){let t=[];if(n.userinfo!==void 0&&(t.push(Vb(n.userinfo)),t.push("@")),n.host!==void 0){let e=n.host;if(!Um(e)){let i=Hm(e);i.isIPV6!==!0&&i.isIPVFuture!==!0&&(e=jb(e,!0),i=Hm(e)),i.isIPV6===!0||i.isIPVFuture===!0?e=`[${i.escapedHost}]`:e=Bb(e,!1)}t.push(e)}if(typeof n.port=="number"||typeof n.port=="string"){let e=String(n.port);if(!sC(e))throw new TypeError("URI port is malformed.");t.push(":"),t.push(e)}return t.length?t.join(""):void 0}zb.exports={nonSimpleDomain:uC,recomposeAuthority:DC,reescapeHostDelimiters:Bb,normalizePercentEncoding:jb,normalizePathEncoding:xC,serializePathEncoding:wC,normalizeQueryFragmentEncoding:CC,encodeUserinfo:Vb,encodeQuery:SC,encodeFragment:kC,escapePreservingEscapes:EC,removeDotSegments:bC,isIPv4:Um,isUUID:oC,normalizeIPv6:Hm,stringArrayToHexStripped:cC}});var Wb=L((_j,Gb)=>{"use strict";var{isUUID:MC}=Km(),NC=/^([\da-z][\d\-a-z]{0,31}):((?:[\w!$'()*+,\-./:;=@]|%[\da-f]{2})+)$/iu,IC=["http","https","ws","wss","urn","urn:uuid"];function AC(n){return IC.indexOf(n)!==-1}function Ym(n){return n.secure===!0?!0:n.secure===!1?!1:n.scheme?n.scheme.length===3&&(n.scheme[0]==="w"||n.scheme[0]==="W")&&(n.scheme[1]==="s"||n.scheme[1]==="S")&&(n.scheme[2]==="s"||n.scheme[2]==="S"):!1}function Hb(n){return n.host||(n.error=n.error||"HTTP URIs must have a host."),n}function Ub(n){let t=String(n.scheme).toLowerCase()==="https";return(n.port===(t?443:80)||n.port==="")&&(n.port=void 0),n.path||(n.path="/"),n}function TC(n){return n.secure=Ym(n),n.resourceName=(n.path||"/")+(n.query?"?"+n.query:""),n.path=void 0,n.query=void 0,n}function OC(n){if((n.port===(Ym(n)?443:80)||n.port==="")&&(n.port=void 0),typeof n.secure=="boolean"&&(n.scheme=n.secure?"wss":"ws",n.secure=void 0),n.resourceName){let t=n.resourceName.indexOf("?"),e=t===-1?n.resourceName:n.resourceName.slice(0,t);n.path=e&&e!=="/"?e:void 0,n.query=t===-1?void 0:n.resourceName.slice(t+1),n.resourceName=void 0}return n.fragment=void 0,n}function RC(n,t){if(!n.path)return n.error="URN can not be parsed",n;let e=n.path.match(NC);if(e&&e[0]===n.path){let i=t.scheme||n.scheme||"urn";n.nid=e[1].toLowerCase(),n.nss=e[2];let a=`${i}:${t.nid||n.nid}`,r=Xm(a);n.path=void 0,r&&(n=r.parse(n,t))}else n.error=n.error||"URN can not be parsed.";return n}function PC(n,t){if(n.nid===void 0)throw new Error("URN without nid cannot be serialized");let e=t.scheme||n.scheme||"urn",i=n.nid.toLowerCase(),a=`${e}:${t.nid||i}`,r=Xm(a);r&&(n=r.serialize(n,t));let o=n,s=n.nss;return o.path=`${i||t.nid}:${s}`,t.skipEscape=!0,o}function FC(n,t){let e=n;return e.uuid=e.nss,e.nss=void 0,!t.tolerant&&(!e.uuid||!MC(e.uuid))&&(e.error=e.error||"UUID is not valid."),e}function LC(n){let t=n;return t.nss=(n.uuid||"").toLowerCase(),t}var qb={scheme:"http",domainHost:!0,parse:Hb,serialize:Ub},BC={scheme:"https",domainHost:qb.domainHost,parse:Hb,serialize:Ub},Vl={scheme:"ws",domainHost:!0,parse:TC,serialize:OC},jC={scheme:"wss",domainHost:Vl.domainHost,parse:Vl.parse,serialize:Vl.serialize},VC={scheme:"urn",parse:RC,serialize:PC,skipNormalize:!0},$C={scheme:"urn:uuid",parse:FC,serialize:LC,skipNormalize:!0},$l={http:qb,https:BC,ws:Vl,wss:jC,urn:VC,"urn:uuid":$C};Object.setPrototypeOf($l,null);function Xm(n){return n&&($l[n]||$l[n.toLowerCase()])||void 0}Gb.exports={wsIsSecure:Ym,SCHEMES:$l,isValidSchemeName:AC,getSchemeHandler:Xm}});var ov=L((yj,Ul)=>{"use strict";var{normalizeIPv6:Qb,removeDotSegments:Eo,recomposeAuthority:zC,normalizePercentEncoding:Zm,normalizePathEncoding:HC,serializePathEncoding:Kb,normalizeQueryFragmentEncoding:Yb,encodeQuery:UC,encodeFragment:qC,reescapeHostDelimiters:GC,isIPv4:Jb,nonSimpleDomain:WC}=Km(),{SCHEMES:ev,getSchemeHandler:Qm}=Wb(),tv=/^[A-Za-z][A-Za-z0-9+.-]*$/u,nv="URI scheme is malformed.";function Xb(n){let t=unescape(String(n));if(!tv.test(t))throw new TypeError(nv);return t}function KC(n,t){return typeof n=="string"?n=nE(n,t):typeof n=="object"&&(n=Hl(ea(n,t),t)),n}function YC(n,t,e){let i=e?Object.assign({scheme:"null"},e):{scheme:"null"},{parsed:a,malformedAuthorityOrPort:r,malformedPercentEncoding:o,malformedSchemeSpecific:s,malformedHost:l,malformedScheme:c}=zl(n,i),{parsed:d,malformedAuthorityOrPort:m,malformedPercentEncoding:u,malformedSchemeSpecific:f,malformedHost:g,malformedScheme:b}=zl(t,i);if(r||m||o||u||s||f||l||g||c||b)throw new Error(a.error||d.error||"URI is malformed.");let _=iv(a,d,i,!0),x=Qm(e&&e.scheme||_.scheme),w=_.host,C=w!==void 0&&w!==""&&(Jb(w)||Qb(w).isIPV6);av(_,e||{},x,C);let F=w&&w.indexOf("%")!==-1&&!new RegExp("\\P{ASCII}","u").test(w);if(_.error&&!F)throw new Error(_.error);return i.skipEscape=!0,ea(_,i)}function iv(n,t,e,i){let a={};return i||(n=Hl(ea(n,e),e),t=Hl(ea(t,e),e)),e=e||{},!e.tolerant&&t.scheme?(a.scheme=t.scheme,a.userinfo=t.userinfo,a.host=t.host,a.port=t.port,a.path=Eo(t.path||""),a.query=t.query):(t.userinfo!==void 0||t.host!==void 0||t.port!==void 0?(a.userinfo=t.userinfo,a.host=t.host,a.port=t.port,a.path=Eo(t.path||""),a.query=t.query):(t.path?(t.path[0]==="/"?a.path=Eo(t.path):((n.userinfo!==void 0||n.host!==void 0||n.port!==void 0)&&!n.path?a.path="/"+t.path:n.path?a.path=n.path.slice(0,n.path.lastIndexOf("/")+1)+t.path:a.path=t.path,a.path=Eo(a.path)),a.query=t.query):(a.path=n.path,t.query!==void 0?a.query=t.query:a.query=n.query),a.userinfo=n.userinfo,a.host=n.host,a.port=n.port),a.scheme=n.scheme),a.fragment=t.fragment,a}function XC(n,t,e){let i=Zb(n,e),a=Zb(t,e);return i!==void 0&&a!==void 0&&i===a}function ea(n,t){let e={host:n.host,scheme:n.scheme,userinfo:n.userinfo,port:n.port,path:n.path,query:n.query,nid:n.nid,nss:n.nss,uuid:n.uuid,fragment:n.fragment,reference:n.reference,resourceName:n.resourceName,secure:n.secure,error:""},i=Object.assign({},t),a=[];e.scheme&&(e.scheme=Xb(e.scheme));let r=Qm(i.scheme||e.scheme);r&&r.serialize&&r.serialize(e,i);let o=e.userinfo!==void 0||e.host!==void 0||e.port!==void 0,s=!i.skipEscape&&e.scheme===void 0&&!o;e.path!==void 0&&(i.skipEscape?e.path=Zm(e.path):e.path=Kb(e.path,s)),i.reference!=="suffix"&&e.scheme&&(e.scheme=Xb(e.scheme),a.push(e.scheme,":"));let l=zC(e);if(l!==void 0&&(i.reference!=="suffix"&&a.push("//"),a.push(l),e.path&&e.path[0]!=="/"&&a.push("/")),e.path!==void 0){let c=e.path;!i.absolutePath&&(!r||!r.absolutePath)&&(c=Eo(c)),s&&(c=Kb(c,!0)),l===void 0&&c[0]==="/"&&c[1]==="/"&&(c="/%2F"+c.slice(2)),a.push(c)}return e.query!==void 0&&a.push("?",UC(e.query)),e.fragment!==void 0&&a.push("#",qC(e.fragment)),a.join("")}var ZC=/^(?:([^#/:?]+):)?(?:\/\/((?:([^#/?@]*)@)?(\[[^#/?\]]+\]|[^#/:?]*)(?::(\d*))?))?([^#?]*)(?:\?([^#]*))?(?:#((?:.|[\n\r])*))?/u,QC=/^(?:[^#/:?]+:)?\/\/([^/?#]*)/,JC=/^(?:[^#/:?]+:)?([/\\\t\n\r]*)/;function eE(n,t){if(t[2]!==void 0&&n.path&&n.path[0]!=="/")return'URI path must start with "/" when authority is present.';if(typeof n.port=="number"&&(n.port<0||n.port>65535))return"URI port is malformed."}function Co(n){if(n===void 0)return!1;let t=n.indexOf("%");for(;t!==-1;){if(t+2>=n.length||!/^[\da-f]{2}$/iu.test(n.slice(t+1,t+3)))return!0;t=n.indexOf("%",t+3)}return!1}function Jm(n){return n[0]==="["&&n[n.length-1]==="]"}function tE(n){let t=n[4];return Co(n[3])||t!==void 0&&!Jm(t)&&Co(t)||Co(n[6])||Co(n[7])||Co(n[8])}function av(n,t,e,i){if(!t.unicodeSupport&&(!e||!e.unicodeSupport)&&n.host&&!Jm(n.host)&&(t.domainHost||e&&e.domainHost)&&i===!1&&WC(n.host))try{n.host=new URL("http://"+n.host).hostname}catch(a){return n.error=n.error||"Host's domain name can not be converted to ASCII: "+a,!0}return!1}function zl(n,t){let e=Object.assign({},t),i={scheme:void 0,userinfo:void 0,host:"",port:void 0,path:"",query:void 0,fragment:void 0},a=!1,r=!1,o=!1,s=!1,l=!1,c=!1,d=!1;e.reference==="suffix"&&(e.scheme?n=e.scheme+":"+n:n="//"+n);let m=n.match(QC);m!==null&&m[1].indexOf("\\")!==-1&&(i.error="URI authority must not contain a literal backslash.",a=!0);let u=n.match(JC);if(u!==null){let g=u[1],b=g.replace(/[\t\n\r]/g,"");b.length>=2&&(b.slice(0,2)!=="//"?(i.error=i.error||"URI authority must not contain a literal backslash.",a=!0):g.length!==b.length&&(i.error=i.error||"URI authority introducer must not contain whitespace.",a=!0))}let f=n.match(ZC);if(f){if(i.scheme=f[1],i.userinfo=f[3],i.host=f[4],i.port=parseInt(f[5],10),i.path=f[6]||"",i.query=f[7],i.fragment=f[8],i.scheme!==void 0){let _=unescape(i.scheme);tv.test(_)?i.scheme=_.toLowerCase():(i.error=i.error||nv,c=!0)}r=tE(f),r&&(i.error=i.error||"URI contains malformed percent-encoding."),isNaN(i.port)&&(i.port=f[5]);let g=eE(i,f);if(g!==void 0&&(i.error=i.error||g,a=!0),i.host)if(Jb(i.host)===!1){let x=Jm(i.host),w=i.host.indexOf("[")!==-1||i.host.indexOf("]")!==-1,C=Qb(i.host);d=C.isIPV6||C.isIPVFuture===!0,l=w&&(!x||C.error===!0),i.host=d?C.host:C.host.toLowerCase(),l&&(i.error=i.error||"URI host is malformed.",a=!0)}else d=!0;i.scheme===void 0&&i.userinfo===void 0&&i.host===void 0&&i.port===void 0&&i.query===void 0&&!i.path?i.reference="same-document":i.scheme===void 0?i.reference="relative":i.fragment===void 0?i.reference="absolute":i.reference="uri",e.reference&&e.reference!=="suffix"&&e.reference!==i.reference&&(i.error=i.error||"URI is not a "+e.reference+" reference.");let b=Qm(e.scheme||i.scheme);if(l||(s=av(i,e,b,d)),n.indexOf("%")!==-1&&i.host!==void 0&&!l){let _=d?i.host:Zm(i.host,!0);d||(_=Zm(_.toLowerCase())),i.host=GC(_,d)}(!b||b&&!b.skipNormalize)&&(i.path&&(i.path=HC(i.path)),i.query&&(i.query=Yb(i.query)),i.fragment&&(i.fragment=Yb(i.fragment))),b&&b.parse&&(b.parse(i,e),b===ev.urn&&i.nid===void 0&&(o=!0))}else i.error=i.error||"URI can not be parsed.";return{parsed:i,malformedAuthorityOrPort:a,malformedPercentEncoding:r,malformedSchemeSpecific:o,malformedHost:s,malformedScheme:c}}function Hl(n,t){return zl(n,t).parsed}function nE(n,t){return rv(n,t).normalized}function rv(n,t){let{parsed:e,malformedAuthorityOrPort:i,malformedPercentEncoding:a,malformedSchemeSpecific:r,malformedHost:o,malformedScheme:s}=zl(n,t);return{normalized:i||a||r||o||s?n:ea(e,t),malformedAuthorityOrPort:i,malformedPercentEncoding:a,malformedSchemeSpecific:r,malformedHost:o,malformedScheme:s}}function Zb(n,t){if(typeof n!="string"&&typeof n!="object")return;let e;try{e=typeof n=="string"?n:ea(n,t)}catch{return}let{normalized:i,malformedAuthorityOrPort:a,malformedPercentEncoding:r,malformedSchemeSpecific:o,malformedHost:s,malformedScheme:l}=rv(e,t);return a||r||o||s||l?void 0:i}var ep={SCHEMES:ev,normalize:KC,resolve:YC,resolveComponent:iv,equal:XC,serialize:ea,parse:Hl};Ul.exports=ep;Ul.exports.default=ep;Ul.exports.fastUri=ep});var lv=L(tp=>{"use strict";Object.defineProperty(tp,"__esModule",{value:!0});var sv=ov();sv.code='require("ajv/dist/runtime/uri").default';tp.default=sv});var gv=L(ft=>{"use strict";Object.defineProperty(ft,"__esModule",{value:!0});ft.CodeGen=ft.Name=ft.nil=ft.stringify=ft.str=ft._=ft.KeywordCxt=void 0;var iE=So();Object.defineProperty(ft,"KeywordCxt",{enumerable:!0,get:function(){return iE.KeywordCxt}});var Ga=ue();Object.defineProperty(ft,"_",{enumerable:!0,get:function(){return Ga._}});Object.defineProperty(ft,"str",{enumerable:!0,get:function(){return Ga.str}});Object.defineProperty(ft,"stringify",{enumerable:!0,get:function(){return Ga.stringify}});Object.defineProperty(ft,"nil",{enumerable:!0,get:function(){return Ga.nil}});Object.defineProperty(ft,"Name",{enumerable:!0,get:function(){return Ga.Name}});Object.defineProperty(ft,"CodeGen",{enumerable:!0,get:function(){return Ga.CodeGen}});var aE=Ll(),uv=ko(),rE=Sm(),Do=jl(),oE=ue(),Mo=yo(),ql=_o(),ip=Se(),cv=Pb(),sE=lv(),hv=(n,t)=>new RegExp(n,t);hv.code="new RegExp";var lE=["removeAdditional","useDefaults","coerceTypes"],cE=new Set(["validate","serialize","parse","wrapper","root","schema","keyword","pattern","formats","validate$data","func","obj","Error"]),dE={errorDataPath:"",format:"`validateFormats: false` can be used instead.",nullable:'"nullable" keyword is supported by default.',jsonPointers:"Deprecated jsPropertySyntax can be used instead.",extendRefs:"Deprecated ignoreKeywordsWithRef can be used instead.",missingRefs:"Pass empty schema with $id that should be ignored to ajv.addSchema.",processCode:"Use option `code: {process: (code, schemaEnv: object) => string}`",sourceCode:"Use option `code: {source: true}`",strictDefaults:"It is default now, see option `strict`.",strictKeywords:"It is default now, see option `strict`.",uniqueItems:'"uniqueItems" keyword is always validated.',unknownFormats:"Disable strict mode or pass `true` to `ajv.addFormat` (or `formats` option).",cache:"Map is used as cache, schema object as key.",serialize:"Map is used as cache, schema object as key.",ajvErrors:"It is default now."},mE={ignoreKeywordsWithRef:"",jsPropertySyntax:"",unicode:'"minLength"/"maxLength" account for unicode characters by default.'},dv=200;function pE(n){var t,e,i,a,r,o,s,l,c,d,m,u,f,g,b,_,x,w,C,F,A,Q,Re,He,Be;let q=n.strict,re=(t=n.code)===null||t===void 0?void 0:t.optimize,k=re===!0||re===void 0?1:re||0,le=(i=(e=n.code)===null||e===void 0?void 0:e.regExp)!==null&&i!==void 0?i:hv,je=(a=n.uriResolver)!==null&&a!==void 0?a:sE.default;return{strictSchema:(o=(r=n.strictSchema)!==null&&r!==void 0?r:q)!==null&&o!==void 0?o:!0,strictNumbers:(l=(s=n.strictNumbers)!==null&&s!==void 0?s:q)!==null&&l!==void 0?l:!0,strictTypes:(d=(c=n.strictTypes)!==null&&c!==void 0?c:q)!==null&&d!==void 0?d:"log",strictTuples:(u=(m=n.strictTuples)!==null&&m!==void 0?m:q)!==null&&u!==void 0?u:"log",strictRequired:(g=(f=n.strictRequired)!==null&&f!==void 0?f:q)!==null&&g!==void 0?g:!1,code:n.code?Ee(N({},n.code),{optimize:k,regExp:le}):{optimize:k,regExp:le},loopRequired:(b=n.loopRequired)!==null&&b!==void 0?b:dv,loopEnum:(_=n.loopEnum)!==null&&_!==void 0?_:dv,meta:(x=n.meta)!==null&&x!==void 0?x:!0,messages:(w=n.messages)!==null&&w!==void 0?w:!0,inlineRefs:(C=n.inlineRefs)!==null&&C!==void 0?C:!0,schemaId:(F=n.schemaId)!==null&&F!==void 0?F:"$id",addUsedSchema:(A=n.addUsedSchema)!==null&&A!==void 0?A:!0,validateSchema:(Q=n.validateSchema)!==null&&Q!==void 0?Q:!0,validateFormats:(Re=n.validateFormats)!==null&&Re!==void 0?Re:!0,unicodeRegExp:(He=n.unicodeRegExp)!==null&&He!==void 0?He:!0,int32range:(Be=n.int32range)!==null&&Be!==void 0?Be:!0,uriResolver:je}}var No=class{constructor(t={}){this.schemas={},this.refs={},this.formats=Object.create(null),this._compilations=new Set,this._loading={},this._cache=new Map,t=this.opts=N(N({},t),pE(t));let{es5:e,lines:i}=this.opts.code;this.scope=new oE.ValueScope({scope:{},prefixes:cE,es5:e,lines:i}),this.logger=vE(t.logger);let a=t.validateFormats;t.validateFormats=!1,this.RULES=(0,rE.getRules)(),mv.call(this,dE,t,"NOT SUPPORTED"),mv.call(this,mE,t,"DEPRECATED","warn"),this._metaOpts=gE.call(this),t.formats&&hE.call(this),this._addVocabularies(),this._addDefaultMetaSchema(),t.keywords&&fE.call(this,t.keywords),typeof t.meta=="object"&&this.addMetaSchema(t.meta),uE.call(this),t.validateFormats=a}_addVocabularies(){this.addKeyword("$async")}_addDefaultMetaSchema(){let{$data:t,meta:e,schemaId:i}=this.opts,a=cv;i==="id"&&(a=N({},cv),a.id=a.$id,delete a.$id),e&&t&&this.addMetaSchema(a,a[i],!1)}defaultMeta(){let{meta:t,schemaId:e}=this.opts;return this.opts.defaultMeta=typeof t=="object"?t[e]||t:void 0}validate(t,e){let i;if(typeof t=="string"){if(i=this.getSchema(t),!i)throw new Error(`no schema with key or ref "${t}"`)}else i=this.compile(t);let a=i(e);return"$async"in i||(this.errors=i.errors),a}compile(t,e){let i=this._addSchema(t,e);return i.validate||this._compileSchemaEnv(i)}compileAsync(t,e){if(typeof this.opts.loadSchema!="function")throw new Error("options.loadSchema should be a function");let{loadSchema:i}=this.opts;return a.call(this,t,e);async function a(d,m){await r.call(this,d.$schema);let u=this._addSchema(d,m);return u.validate||o.call(this,u)}async function r(d){d&&!this.getSchema(d)&&await a.call(this,{$ref:d},!0)}async function o(d){try{return this._compileSchemaEnv(d)}catch(m){if(!(m instanceof uv.default))throw m;return s.call(this,m),await l.call(this,m.missingSchema),o.call(this,d)}}function s({missingSchema:d,missingRef:m}){if(this.refs[d])throw new Error(`AnySchema ${d} is loaded but ${m} cannot be resolved`)}async function l(d){let m=await c.call(this,d);this.refs[d]||await r.call(this,m.$schema),this.refs[d]||this.addSchema(m,d,e)}async function c(d){let m=this._loading[d];if(m)return m;try{return await(this._loading[d]=i(d))}finally{delete this._loading[d]}}}addSchema(t,e,i,a=this.opts.validateSchema){if(Array.isArray(t)){for(let o of t)this.addSchema(o,void 0,i,a);return this}let r;if(typeof t=="object"){let{schemaId:o}=this.opts;if(r=t[o],r!==void 0&&typeof r!="string")throw new Error(`schema ${o} must be string`)}return e=(0,Mo.normalizeId)(e||r),this._checkUnique(e),this.schemas[e]=this._addSchema(t,i,e,a,!0),this}addMetaSchema(t,e,i=this.opts.validateSchema){return this.addSchema(t,e,!0,i),this}validateSchema(t,e){if(typeof t=="boolean")return!0;let i;if(i=t.$schema,i!==void 0&&typeof i!="string")throw new Error("$schema must be a string");if(i=i||this.opts.defaultMeta||this.defaultMeta(),!i)return this.logger.warn("meta-schema not available"),this.errors=null,!0;let a=this.validate(i,t);if(!a&&e){let r="schema is invalid: "+this.errorsText();if(this.opts.validateSchema==="log")this.logger.error(r);else throw new Error(r)}return a}getSchema(t){let e;for(;typeof(e=pv.call(this,t))=="string";)t=e;if(e===void 0){let{schemaId:i}=this.opts,a=new Do.SchemaEnv({schema:{},schemaId:i});if(e=Do.resolveSchema.call(this,a,t),!e)return;this.refs[t]=e}return e.validate||this._compileSchemaEnv(e)}removeSchema(t){if(t instanceof RegExp)return this._removeAllSchemas(this.schemas,t),this._removeAllSchemas(this.refs,t),this;switch(typeof t){case"undefined":return this._removeAllSchemas(this.schemas),this._removeAllSchemas(this.refs),this._cache.clear(),this;case"string":{let e=pv.call(this,t);return typeof e=="object"&&this._cache.delete(e.schema),delete this.schemas[t],delete this.refs[t],this}case"object":{let e=t;this._cache.delete(e);let i=t[this.opts.schemaId];return i&&(i=(0,Mo.normalizeId)(i),delete this.schemas[i],delete this.refs[i]),this}default:throw new Error("ajv.removeSchema: invalid parameter")}}addVocabulary(t){for(let e of t)this.addKeyword(e);return this}addKeyword(t,e){let i;if(typeof t=="string")i=t,typeof e=="object"&&(this.logger.warn("these parameters are deprecated, see docs for addKeyword"),e.keyword=i);else if(typeof t=="object"&&e===void 0){if(e=t,i=e.keyword,Array.isArray(i)&&!i.length)throw new Error("addKeywords: keyword must be string or non-empty array")}else throw new Error("invalid addKeywords parameters");if(yE.call(this,i,e),!e)return(0,ip.eachItem)(i,r=>np.call(this,r)),this;wE.call(this,e);let a=Ee(N({},e),{type:(0,ql.getJSONTypes)(e.type),schemaType:(0,ql.getJSONTypes)(e.schemaType)});return(0,ip.eachItem)(i,a.type.length===0?r=>np.call(this,r,a):r=>a.type.forEach(o=>np.call(this,r,a,o))),this}getKeyword(t){let e=this.RULES.all[t];return typeof e=="object"?e.definition:!!e}removeKeyword(t){let{RULES:e}=this;delete e.keywords[t],delete e.all[t];for(let i of e.rules){let a=i.rules.findIndex(r=>r.keyword===t);a>=0&&i.rules.splice(a,1)}return this}addFormat(t,e){return typeof e=="string"&&(e=new RegExp(e)),this.formats[t]=e,this}errorsText(t=this.errors,{separator:e=", ",dataVar:i="data"}={}){return!t||t.length===0?"No errors":t.map(a=>`${i}${a.instancePath} ${a.message}`).reduce((a,r)=>a+e+r)}$dataMetaSchema(t,e){let i=this.RULES.all;t=JSON.parse(JSON.stringify(t));for(let a of e){let r=a.split("/").slice(1),o=t;for(let s of r)o=o[s];for(let s in i){let l=i[s];if(typeof l!="object")continue;let{$data:c}=l.definition,d=o[s];c&&d&&(o[s]=fv(d))}}return t}_removeAllSchemas(t,e){for(let i in t){let a=t[i];(!e||e.test(i))&&(typeof a=="string"?delete t[i]:a&&!a.meta&&(this._cache.delete(a.schema),delete t[i]))}}_addSchema(t,e,i,a=this.opts.validateSchema,r=this.opts.addUsedSchema){let o,{schemaId:s}=this.opts;if(typeof t=="object")o=t[s];else{if(this.opts.jtd)throw new Error("schema must be object");if(typeof t!="boolean")throw new Error("schema must be object or boolean")}let l=this._cache.get(t);if(l!==void 0)return l;i=(0,Mo.normalizeId)(o||i);let c=Mo.getSchemaRefs.call(this,t,i);return l=new Do.SchemaEnv({schema:t,schemaId:s,meta:e,baseId:i,localRefs:c}),this._cache.set(l.schema,l),r&&!i.startsWith("#")&&(i&&this._checkUnique(i),this.refs[i]=l),a&&this.validateSchema(t,!0),l}_checkUnique(t){if(this.schemas[t]||this.refs[t])throw new Error(`schema with key or id "${t}" already exists`)}_compileSchemaEnv(t){if(t.meta?this._compileMetaSchema(t):Do.compileSchema.call(this,t),!t.validate)throw new Error("ajv implementation error");return t.validate}_compileMetaSchema(t){let e=this.opts;this.opts=this._metaOpts;try{Do.compileSchema.call(this,t)}finally{this.opts=e}}};No.ValidationError=aE.default;No.MissingRefError=uv.default;ft.default=No;function mv(n,t,e,i="error"){for(let a in n){let r=a;r in t&&this.logger[i](`${e}: option ${a}. ${n[r]}`)}}function pv(n){return n=(0,Mo.normalizeId)(n),this.schemas[n]||this.refs[n]}function uE(){let n=this.opts.schemas;if(n)if(Array.isArray(n))this.addSchema(n);else for(let t in n)this.addSchema(n[t],t)}function hE(){for(let n in this.opts.formats){let t=this.opts.formats[n];t&&this.addFormat(n,t)}}function fE(n){if(Array.isArray(n)){this.addVocabulary(n);return}this.logger.warn("keywords option as map is deprecated, pass array");for(let t in n){let e=n[t];e.keyword||(e.keyword=t),this.addKeyword(e)}}function gE(){let n=N({},this.opts);for(let t of lE)delete n[t];return n}var bE={log(){},warn(){},error(){}};function vE(n){if(n===!1)return bE;if(n===void 0)return console;if(n.log&&n.warn&&n.error)return n;throw new Error("logger must implement log, warn and error methods")}var _E=/^[a-z_$][a-z0-9_$:-]*$/i;function yE(n,t){let{RULES:e}=this;if((0,ip.eachItem)(n,i=>{if(e.keywords[i])throw new Error(`Keyword ${i} is already defined`);if(!_E.test(i))throw new Error(`Keyword ${i} has invalid name`)}),!!t&&t.$data&&!("code"in t||"validate"in t))throw new Error('$data keyword must have "code" or "validate" function')}function np(n,t,e){var i;let a=t?.post;if(e&&a)throw new Error('keyword with "post" flag cannot have "type"');let{RULES:r}=this,o=a?r.post:r.rules.find(({type:l})=>l===e);if(o||(o={type:e,rules:[]},r.rules.push(o)),r.keywords[n]=!0,!t)return;let s={keyword:n,definition:Ee(N({},t),{type:(0,ql.getJSONTypes)(t.type),schemaType:(0,ql.getJSONTypes)(t.schemaType)})};t.before?xE.call(this,o,s,t.before):o.rules.push(s),r.all[n]=s,(i=t.implements)===null||i===void 0||i.forEach(l=>this.addKeyword(l))}function xE(n,t,e){let i=n.rules.findIndex(a=>a.keyword===e);i>=0?n.rules.splice(i,0,t):(n.rules.push(t),this.logger.warn(`rule ${e} is not defined`))}function wE(n){let{metaSchema:t}=n;t!==void 0&&(n.$data&&this.opts.$data&&(t=fv(t)),n.validateSchema=this.compile(t,!0))}var SE={$ref:"https://raw.githubusercontent.com/ajv-validator/ajv/master/lib/refs/data.json#"};function fv(n){return{anyOf:[n,SE]}}});var bv=L(ap=>{"use strict";Object.defineProperty(ap,"__esModule",{value:!0});var kE={keyword:"id",code(){throw new Error('NOT SUPPORTED: keyword "id", use "$id" for schema ID')}};ap.default=kE});var xv=L(ta=>{"use strict";Object.defineProperty(ta,"__esModule",{value:!0});ta.callRef=ta.getValidate=void 0;var CE=ko(),vv=tn(),Ht=ue(),Wa=Yn(),_v=jl(),Gl=Se(),EE={keyword:"$ref",schemaType:"string",code(n){let{gen:t,schema:e,it:i}=n,{baseId:a,schemaEnv:r,validateName:o,opts:s,self:l}=i,{root:c}=r;if((e==="#"||e==="#/")&&a===c.baseId)return m();let d=_v.resolveRef.call(l,c,a,e);if(d===void 0)throw new CE.default(i.opts.uriResolver,a,e);if(d instanceof _v.SchemaEnv)return u(d);return f(d);function m(){if(r===c)return Wl(n,o,r,r.$async);let g=t.scopeValue("root",{ref:c});return Wl(n,(0,Ht._)`${g}.validate`,c,c.$async)}function u(g){let b=yv(n,g);Wl(n,b,g,g.$async)}function f(g){let b=t.scopeValue("schema",s.code.source===!0?{ref:g,code:(0,Ht.stringify)(g)}:{ref:g}),_=t.name("valid"),x=n.subschema({schema:g,dataTypes:[],schemaPath:Ht.nil,topSchemaRef:b,errSchemaPath:e},_);n.mergeEvaluated(x),n.ok(_)}}};function yv(n,t){let{gen:e}=n;return t.validate?e.scopeValue("validate",{ref:t.validate}):(0,Ht._)`${e.scopeValue("wrapper",{ref:t})}.validate`}ta.getValidate=yv;function Wl(n,t,e,i){let{gen:a,it:r}=n,{allErrors:o,schemaEnv:s,opts:l}=r,c=l.passContext?Wa.default.this:Ht.nil;i?d():m();function d(){if(!s.$async)throw new Error("async schema referenced by sync schema");let g=a.let("valid");a.try(()=>{a.code((0,Ht._)`await ${(0,vv.callValidateCode)(n,t,c)}`),f(t),o||a.assign(g,!0)},b=>{a.if((0,Ht._)`!(${b} instanceof ${r.ValidationError})`,()=>a.throw(b)),u(b),o||a.assign(g,!1)}),n.ok(g)}function m(){n.result((0,vv.callValidateCode)(n,t,c),()=>f(t),()=>u(t))}function u(g){let b=(0,Ht._)`${g}.errors`;a.assign(Wa.default.vErrors,(0,Ht._)`${Wa.default.vErrors} === null ? ${b} : ${Wa.default.vErrors}.concat(${b})`),a.assign(Wa.default.errors,(0,Ht._)`${Wa.default.vErrors}.length`)}function f(g){var b;if(!r.opts.unevaluated)return;let _=(b=e?.validate)===null||b===void 0?void 0:b.evaluated;if(r.props!==!0)if(_&&!_.dynamicProps)_.props!==void 0&&(r.props=Gl.mergeEvaluated.props(a,_.props,r.props));else{let x=a.var("props",(0,Ht._)`${g}.evaluated.props`);r.props=Gl.mergeEvaluated.props(a,x,r.props,Ht.Name)}if(r.items!==!0)if(_&&!_.dynamicItems)_.items!==void 0&&(r.items=Gl.mergeEvaluated.items(a,_.items,r.items));else{let x=a.var("items",(0,Ht._)`${g}.evaluated.items`);r.items=Gl.mergeEvaluated.items(a,x,r.items,Ht.Name)}}}ta.callRef=Wl;ta.default=EE});var wv=L(rp=>{"use strict";Object.defineProperty(rp,"__esModule",{value:!0});var DE=bv(),ME=xv(),NE=["$schema","$id","$defs","$vocabulary",{keyword:"$comment"},"definitions",DE.default,ME.default];rp.default=NE});var Sv=L(op=>{"use strict";Object.defineProperty(op,"__esModule",{value:!0});var Kl=ue(),wi=Kl.operators,Yl={maximum:{okStr:"<=",ok:wi.LTE,fail:wi.GT},minimum:{okStr:">=",ok:wi.GTE,fail:wi.LT},exclusiveMaximum:{okStr:"<",ok:wi.LT,fail:wi.GTE},exclusiveMinimum:{okStr:">",ok:wi.GT,fail:wi.LTE}},IE={message:({keyword:n,schemaCode:t})=>(0,Kl.str)`must be ${Yl[n].okStr} ${t}`,params:({keyword:n,schemaCode:t})=>(0,Kl._)`{comparison: ${Yl[n].okStr}, limit: ${t}}`},AE={keyword:Object.keys(Yl),type:"number",schemaType:"number",$data:!0,error:IE,code(n){let{keyword:t,data:e,schemaCode:i}=n;n.fail$data((0,Kl._)`${e} ${Yl[t].fail} ${i} || isNaN(${e})`)}};op.default=AE});var kv=L(sp=>{"use strict";Object.defineProperty(sp,"__esModule",{value:!0});var Io=ue(),TE={message:({schemaCode:n})=>(0,Io.str)`must be multiple of ${n}`,params:({schemaCode:n})=>(0,Io._)`{multipleOf: ${n}}`},OE={keyword:"multipleOf",type:"number",schemaType:"number",$data:!0,error:TE,code(n){let{gen:t,data:e,schemaCode:i,it:a}=n,r=a.opts.multipleOfPrecision,o=t.let("res"),s=r?(0,Io._)`Math.abs(Math.round(${o}) - ${o}) > 1e-${r}`:(0,Io._)`${o} !== parseInt(${o})`;n.fail$data((0,Io._)`(${i} === 0 || (${o} = ${e}/${i}, ${s}))`)}};sp.default=OE});var Ev=L(lp=>{"use strict";Object.defineProperty(lp,"__esModule",{value:!0});function Cv(n){let t=n.length,e=0,i=0,a;for(;i<t;)e++,a=n.charCodeAt(i++),a>=55296&&a<=56319&&i<t&&(a=n.charCodeAt(i),(a&64512)===56320&&i++);return e}lp.default=Cv;Cv.code='require("ajv/dist/runtime/ucs2length").default'});var Dv=L(cp=>{"use strict";Object.defineProperty(cp,"__esModule",{value:!0});var na=ue(),RE=Se(),PE=Ev(),FE={message({keyword:n,schemaCode:t}){let e=n==="maxLength"?"more":"fewer";return(0,na.str)`must NOT have ${e} than ${t} characters`},params:({schemaCode:n})=>(0,na._)`{limit: ${n}}`},LE={keyword:["maxLength","minLength"],type:"string",schemaType:"number",$data:!0,error:FE,code(n){let{keyword:t,data:e,schemaCode:i,it:a}=n,r=t==="maxLength"?na.operators.GT:na.operators.LT,o=a.opts.unicode===!1?(0,na._)`${e}.length`:(0,na._)`${(0,RE.useFunc)(n.gen,PE.default)}(${e})`;n.fail$data((0,na._)`${o} ${r} ${i}`)}};cp.default=LE});var Mv=L(dp=>{"use strict";Object.defineProperty(dp,"__esModule",{value:!0});var BE=tn(),jE=Se(),Ka=ue(),VE={message:({schemaCode:n})=>(0,Ka.str)`must match pattern "${n}"`,params:({schemaCode:n})=>(0,Ka._)`{pattern: ${n}}`},$E={keyword:"pattern",type:"string",schemaType:"string",$data:!0,error:VE,code(n){let{gen:t,data:e,$data:i,schema:a,schemaCode:r,it:o}=n,s=o.opts.unicodeRegExp?"u":"";if(i){let{regExp:l}=o.opts.code,c=l.code==="new RegExp"?(0,Ka._)`new RegExp`:(0,jE.useFunc)(t,l),d=t.let("valid");t.try(()=>t.assign(d,(0,Ka._)`${c}(${r}, ${s}).test(${e})`),()=>t.assign(d,!1)),n.fail$data((0,Ka._)`!${d}`)}else{let l=(0,BE.usePattern)(n,a);n.fail$data((0,Ka._)`!${l}.test(${e})`)}}};dp.default=$E});var Nv=L(mp=>{"use strict";Object.defineProperty(mp,"__esModule",{value:!0});var Ao=ue(),zE={message({keyword:n,schemaCode:t}){let e=n==="maxProperties"?"more":"fewer";return(0,Ao.str)`must NOT have ${e} than ${t} properties`},params:({schemaCode:n})=>(0,Ao._)`{limit: ${n}}`},HE={keyword:["maxProperties","minProperties"],type:"object",schemaType:"number",$data:!0,error:zE,code(n){let{keyword:t,data:e,schemaCode:i}=n,a=t==="maxProperties"?Ao.operators.GT:Ao.operators.LT;n.fail$data((0,Ao._)`Object.keys(${e}).length ${a} ${i}`)}};mp.default=HE});var Iv=L(pp=>{"use strict";Object.defineProperty(pp,"__esModule",{value:!0});var To=tn(),Oo=ue(),UE=Se(),qE={message:({params:{missingProperty:n}})=>(0,Oo.str)`must have required property '${n}'`,params:({params:{missingProperty:n}})=>(0,Oo._)`{missingProperty: ${n}}`},GE={keyword:"required",type:"object",schemaType:"array",$data:!0,error:qE,code(n){let{gen:t,schema:e,schemaCode:i,data:a,$data:r,it:o}=n,{opts:s}=o;if(!r&&e.length===0)return;let l=e.length>=s.loopRequired;if(o.allErrors?c():d(),s.strictRequired){let f=n.parentSchema.properties,{definedProperties:g}=n.it;for(let b of e)if(f?.[b]===void 0&&!g.has(b)){let _=o.schemaEnv.baseId+o.errSchemaPath,x=`required property "${b}" is not defined at "${_}" (strictRequired)`;(0,UE.checkStrictMode)(o,x,o.opts.strictRequired)}}function c(){if(l||r)n.block$data(Oo.nil,m);else for(let f of e)(0,To.checkReportMissingProp)(n,f)}function d(){let f=t.let("missing");if(l||r){let g=t.let("valid",!0);n.block$data(g,()=>u(f,g)),n.ok(g)}else t.if((0,To.checkMissingProp)(n,e,f)),(0,To.reportMissingProp)(n,f),t.else()}function m(){t.forOf("prop",i,f=>{n.setParams({missingProperty:f}),t.if((0,To.noPropertyInData)(t,a,f,s.ownProperties),()=>n.error())})}function u(f,g){n.setParams({missingProperty:f}),t.forOf(f,i,()=>{t.assign(g,(0,To.propertyInData)(t,a,f,s.ownProperties)),t.if((0,Oo.not)(g),()=>{n.error(),t.break()})},Oo.nil)}}};pp.default=GE});var Av=L(up=>{"use strict";Object.defineProperty(up,"__esModule",{value:!0});var Ro=ue(),WE={message({keyword:n,schemaCode:t}){let e=n==="maxItems"?"more":"fewer";return(0,Ro.str)`must NOT have ${e} than ${t} items`},params:({schemaCode:n})=>(0,Ro._)`{limit: ${n}}`},KE={keyword:["maxItems","minItems"],type:"array",schemaType:"number",$data:!0,error:WE,code(n){let{keyword:t,data:e,schemaCode:i}=n,a=t==="maxItems"?Ro.operators.GT:Ro.operators.LT;n.fail$data((0,Ro._)`${e}.length ${a} ${i}`)}};up.default=KE});var Xl=L(hp=>{"use strict";Object.defineProperty(hp,"__esModule",{value:!0});var Tv=Am();Tv.code='require("ajv/dist/runtime/equal").default';hp.default=Tv});var Ov=L(gp=>{"use strict";Object.defineProperty(gp,"__esModule",{value:!0});var fp=_o(),gt=ue(),YE=Se(),XE=Xl(),ZE={message:({params:{i:n,j:t}})=>(0,gt.str)`must NOT have duplicate items (items ## ${t} and ${n} are identical)`,params:({params:{i:n,j:t}})=>(0,gt._)`{i: ${n}, j: ${t}}`},QE={keyword:"uniqueItems",type:"array",schemaType:"boolean",$data:!0,error:ZE,code(n){let{gen:t,data:e,$data:i,schema:a,parentSchema:r,schemaCode:o,it:s}=n;if(!i&&!a)return;let l=t.let("valid"),c=r.items?(0,fp.getSchemaTypes)(r.items):[];n.block$data(l,d,(0,gt._)`${o} === false`),n.ok(l);function d(){let g=t.let("i",(0,gt._)`${e}.length`),b=t.let("j");n.setParams({i:g,j:b}),t.assign(l,!0),t.if((0,gt._)`${g} > 1`,()=>(m()?u:f)(g,b))}function m(){return c.length>0&&!c.some(g=>g==="object"||g==="array")}function u(g,b){let _=t.name("item"),x=(0,fp.checkDataTypes)(c,_,s.opts.strictNumbers,fp.DataType.Wrong),w=t.const("indices",(0,gt._)`{}`);t.for((0,gt._)`;${g}--;`,()=>{t.let(_,(0,gt._)`${e}[${g}]`),t.if(x,(0,gt._)`continue`),c.length>1&&t.if((0,gt._)`typeof ${_} == "string"`,(0,gt._)`${_} += "_"`),t.if((0,gt._)`typeof ${w}[${_}] == "number"`,()=>{t.assign(b,(0,gt._)`${w}[${_}]`),n.error(),t.assign(l,!1).break()}).code((0,gt._)`${w}[${_}] = ${g}`)})}function f(g,b){let _=(0,YE.useFunc)(t,XE.default),x=t.name("outer");t.label(x).for((0,gt._)`;${g}--;`,()=>t.for((0,gt._)`${b} = ${g}; ${b}--;`,()=>t.if((0,gt._)`${_}(${e}[${g}], ${e}[${b}])`,()=>{n.error(),t.assign(l,!1).break(x)})))}}};gp.default=QE});var Rv=L(vp=>{"use strict";Object.defineProperty(vp,"__esModule",{value:!0});var bp=ue(),JE=Se(),eD=Xl(),tD={message:"must be equal to constant",params:({schemaCode:n})=>(0,bp._)`{allowedValue: ${n}}`},nD={keyword:"const",$data:!0,error:tD,code(n){let{gen:t,data:e,$data:i,schemaCode:a,schema:r}=n;i||r&&typeof r=="object"?n.fail$data((0,bp._)`!${(0,JE.useFunc)(t,eD.default)}(${e}, ${a})`):n.fail((0,bp._)`${r} !== ${e}`)}};vp.default=nD});var Pv=L(_p=>{"use strict";Object.defineProperty(_p,"__esModule",{value:!0});var Po=ue(),iD=Se(),aD=Xl(),rD={message:"must be equal to one of the allowed values",params:({schemaCode:n})=>(0,Po._)`{allowedValues: ${n}}`},oD={keyword:"enum",schemaType:"array",$data:!0,error:rD,code(n){let{gen:t,data:e,$data:i,schema:a,schemaCode:r,it:o}=n;if(!i&&a.length===0)throw new Error("enum must have non-empty array");let s=a.length>=o.opts.loopEnum,l,c=()=>l??(l=(0,iD.useFunc)(t,aD.default)),d;if(s||i)d=t.let("valid"),n.block$data(d,m);else{if(!Array.isArray(a))throw new Error("ajv implementation error");let f=t.const("vSchema",r);d=(0,Po.or)(...a.map((g,b)=>u(f,b)))}n.pass(d);function m(){t.assign(d,!1),t.forOf("v",r,f=>t.if((0,Po._)`${c()}(${e}, ${f})`,()=>t.assign(d,!0).break()))}function u(f,g){let b=a[g];return typeof b=="object"&&b!==null?(0,Po._)`${c()}(${e}, ${f}[${g}])`:(0,Po._)`${e} === ${b}`}}};_p.default=oD});var Fv=L(yp=>{"use strict";Object.defineProperty(yp,"__esModule",{value:!0});var sD=Sv(),lD=kv(),cD=Dv(),dD=Mv(),mD=Nv(),pD=Iv(),uD=Av(),hD=Ov(),fD=Rv(),gD=Pv(),bD=[sD.default,lD.default,cD.default,dD.default,mD.default,pD.default,uD.default,hD.default,{keyword:"type",schemaType:["string","array"]},{keyword:"nullable",schemaType:"boolean"},fD.default,gD.default];yp.default=bD});var wp=L(Fo=>{"use strict";Object.defineProperty(Fo,"__esModule",{value:!0});Fo.validateAdditionalItems=void 0;var ia=ue(),xp=Se(),vD={message:({params:{len:n}})=>(0,ia.str)`must NOT have more than ${n} items`,params:({params:{len:n}})=>(0,ia._)`{limit: ${n}}`},_D={keyword:"additionalItems",type:"array",schemaType:["boolean","object"],before:"uniqueItems",error:vD,code(n){let{parentSchema:t,it:e}=n,{items:i}=t;if(!Array.isArray(i)){(0,xp.checkStrictMode)(e,'"additionalItems" is ignored when "items" is not an array of schemas');return}Lv(n,i)}};function Lv(n,t){let{gen:e,schema:i,data:a,keyword:r,it:o}=n;o.items=!0;let s=e.const("len",(0,ia._)`${a}.length`);if(i===!1)n.setParams({len:t.length}),n.pass((0,ia._)`${s} <= ${t.length}`);else if(typeof i=="object"&&!(0,xp.alwaysValidSchema)(o,i)){let c=e.var("valid",(0,ia._)`${s} <= ${t.length}`);e.if((0,ia.not)(c),()=>l(c)),n.ok(c)}function l(c){e.forRange("i",t.length,s,d=>{n.subschema({keyword:r,dataProp:d,dataPropType:xp.Type.Num},c),o.allErrors||e.if((0,ia.not)(c),()=>e.break())})}}Fo.validateAdditionalItems=Lv;Fo.default=_D});var Sp=L(Lo=>{"use strict";Object.defineProperty(Lo,"__esModule",{value:!0});Lo.validateTuple=void 0;var Bv=ue(),Zl=Se(),yD=tn(),xD={keyword:"items",type:"array",schemaType:["object","array","boolean"],before:"uniqueItems",code(n){let{schema:t,it:e}=n;if(Array.isArray(t))return jv(n,"additionalItems",t);e.items=!0,!(0,Zl.alwaysValidSchema)(e,t)&&n.ok((0,yD.validateArray)(n))}};function jv(n,t,e=n.schema){let{gen:i,parentSchema:a,data:r,keyword:o,it:s}=n;d(a),s.opts.unevaluated&&e.length&&s.items!==!0&&(s.items=Zl.mergeEvaluated.items(i,e.length,s.items));let l=i.name("valid"),c=i.const("len",(0,Bv._)`${r}.length`);e.forEach((m,u)=>{(0,Zl.alwaysValidSchema)(s,m)||(i.if((0,Bv._)`${c} > ${u}`,()=>n.subschema({keyword:o,schemaProp:u,dataProp:u},l)),n.ok(l))});function d(m){let{opts:u,errSchemaPath:f}=s,g=e.length,b=g===m.minItems&&(g===m.maxItems||m[t]===!1);if(u.strictTuples&&!b){let _=`"${o}" is ${g}-tuple, but minItems or maxItems/${t} are not specified or different at path "${f}"`;(0,Zl.checkStrictMode)(s,_,u.strictTuples)}}}Lo.validateTuple=jv;Lo.default=xD});var Vv=L(kp=>{"use strict";Object.defineProperty(kp,"__esModule",{value:!0});var wD=Sp(),SD={keyword:"prefixItems",type:"array",schemaType:["array"],before:"uniqueItems",code:n=>(0,wD.validateTuple)(n,"items")};kp.default=SD});var zv=L(Cp=>{"use strict";Object.defineProperty(Cp,"__esModule",{value:!0});var $v=ue(),kD=Se(),CD=tn(),ED=wp(),DD={message:({params:{len:n}})=>(0,$v.str)`must NOT have more than ${n} items`,params:({params:{len:n}})=>(0,$v._)`{limit: ${n}}`},MD={keyword:"items",type:"array",schemaType:["object","boolean"],before:"uniqueItems",error:DD,code(n){let{schema:t,parentSchema:e,it:i}=n,{prefixItems:a}=e;i.items=!0,!(0,kD.alwaysValidSchema)(i,t)&&(a?(0,ED.validateAdditionalItems)(n,a):n.ok((0,CD.validateArray)(n)))}};Cp.default=MD});var Hv=L(Ep=>{"use strict";Object.defineProperty(Ep,"__esModule",{value:!0});var an=ue(),Ql=Se(),ND={message:({params:{min:n,max:t}})=>t===void 0?(0,an.str)`must contain at least ${n} valid item(s)`:(0,an.str)`must contain at least ${n} and no more than ${t} valid item(s)`,params:({params:{min:n,max:t}})=>t===void 0?(0,an._)`{minContains: ${n}}`:(0,an._)`{minContains: ${n}, maxContains: ${t}}`},ID={keyword:"contains",type:"array",schemaType:["object","boolean"],before:"uniqueItems",trackErrors:!0,error:ND,code(n){let{gen:t,schema:e,parentSchema:i,data:a,it:r}=n,o,s,{minContains:l,maxContains:c}=i;r.opts.next?(o=l===void 0?1:l,s=c):o=1;let d=t.const("len",(0,an._)`${a}.length`);if(n.setParams({min:o,max:s}),s===void 0&&o===0){(0,Ql.checkStrictMode)(r,'"minContains" == 0 without "maxContains": "contains" keyword ignored');return}if(s!==void 0&&o>s){(0,Ql.checkStrictMode)(r,'"minContains" > "maxContains" is always invalid'),n.fail();return}if((0,Ql.alwaysValidSchema)(r,e)){let b=(0,an._)`${d} >= ${o}`;s!==void 0&&(b=(0,an._)`${b} && ${d} <= ${s}`),n.pass(b);return}r.items=!0;let m=t.name("valid");s===void 0&&o===1?f(m,()=>t.if(m,()=>t.break())):o===0?(t.let(m,!0),s!==void 0&&t.if((0,an._)`${a}.length > 0`,u)):(t.let(m,!1),u()),n.result(m,()=>n.reset());function u(){let b=t.name("_valid"),_=t.let("count",0);f(b,()=>t.if(b,()=>g(_)))}function f(b,_){t.forRange("i",0,d,x=>{n.subschema({keyword:"contains",dataProp:x,dataPropType:Ql.Type.Num,compositeRule:!0},b),_()})}function g(b){t.code((0,an._)`${b}++`),s===void 0?t.if((0,an._)`${b} >= ${o}`,()=>t.assign(m,!0).break()):(t.if((0,an._)`${b} > ${s}`,()=>t.assign(m,!1).break()),o===1?t.assign(m,!0):t.if((0,an._)`${b} >= ${o}`,()=>t.assign(m,!0)))}}};Ep.default=ID});var Gv=L(On=>{"use strict";Object.defineProperty(On,"__esModule",{value:!0});On.validateSchemaDeps=On.validatePropertyDeps=On.error=void 0;var Dp=ue(),AD=Se(),Bo=tn();On.error={message:({params:{property:n,depsCount:t,deps:e}})=>{let i=t===1?"property":"properties";return(0,Dp.str)`must have ${i} ${e} when property ${n} is present`},params:({params:{property:n,depsCount:t,deps:e,missingProperty:i}})=>(0,Dp._)`{property: ${n},
    missingProperty: ${i},
    depsCount: ${t},
    deps: ${e}}`};var TD={keyword:"dependencies",type:"object",schemaType:"object",error:On.error,code(n){let[t,e]=OD(n);Uv(n,t),qv(n,e)}};function OD({schema:n}){let t={},e={};for(let i in n){if(i==="__proto__")continue;let a=Array.isArray(n[i])?t:e;a[i]=n[i]}return[t,e]}function Uv(n,t=n.schema){let{gen:e,data:i,it:a}=n;if(Object.keys(t).length===0)return;let r=e.let("missing");for(let o in t){let s=t[o];if(s.length===0)continue;let l=(0,Bo.propertyInData)(e,i,o,a.opts.ownProperties);n.setParams({property:o,depsCount:s.length,deps:s.join(", ")}),a.allErrors?e.if(l,()=>{for(let c of s)(0,Bo.checkReportMissingProp)(n,c)}):(e.if((0,Dp._)`${l} && (${(0,Bo.checkMissingProp)(n,s,r)})`),(0,Bo.reportMissingProp)(n,r),e.else())}}On.validatePropertyDeps=Uv;function qv(n,t=n.schema){let{gen:e,data:i,keyword:a,it:r}=n,o=e.name("valid");for(let s in t)(0,AD.alwaysValidSchema)(r,t[s])||(e.if((0,Bo.propertyInData)(e,i,s,r.opts.ownProperties),()=>{let l=n.subschema({keyword:a,schemaProp:s},o);n.mergeValidEvaluated(l,o)},()=>e.var(o,!0)),n.ok(o))}On.validateSchemaDeps=qv;On.default=TD});var Kv=L(Mp=>{"use strict";Object.defineProperty(Mp,"__esModule",{value:!0});var Wv=ue(),RD=Se(),PD={message:"property name must be valid",params:({params:n})=>(0,Wv._)`{propertyName: ${n.propertyName}}`},FD={keyword:"propertyNames",type:"object",schemaType:["object","boolean"],error:PD,code(n){let{gen:t,schema:e,data:i,it:a}=n;if((0,RD.alwaysValidSchema)(a,e))return;let r=t.name("valid");t.forIn("key",i,o=>{n.setParams({propertyName:o}),n.subschema({keyword:"propertyNames",data:o,dataTypes:["string"],propertyName:o,compositeRule:!0},r),t.if((0,Wv.not)(r),()=>{n.error(!0),a.allErrors||t.break()})}),n.ok(r)}};Mp.default=FD});var Ip=L(Np=>{"use strict";Object.defineProperty(Np,"__esModule",{value:!0});var Jl=tn(),bn=ue(),LD=Yn(),ec=Se(),BD={message:"must NOT have additional properties",params:({params:n})=>(0,bn._)`{additionalProperty: ${n.additionalProperty}}`},jD={keyword:"additionalProperties",type:["object"],schemaType:["boolean","object"],allowUndefined:!0,trackErrors:!0,error:BD,code(n){let{gen:t,schema:e,parentSchema:i,data:a,errsCount:r,it:o}=n;if(!r)throw new Error("ajv implementation error");let{allErrors:s,opts:l}=o;if(o.props=!0,l.removeAdditional!=="all"&&(0,ec.alwaysValidSchema)(o,e))return;let c=(0,Jl.allSchemaProperties)(i.properties),d=(0,Jl.allSchemaProperties)(i.patternProperties);m(),n.ok((0,bn._)`${r} === ${LD.default.errors}`);function m(){t.forIn("key",a,_=>{!c.length&&!d.length?g(_):t.if(u(_),()=>g(_))})}function u(_){let x;if(c.length>8){let w=(0,ec.schemaRefOrVal)(o,i.properties,"properties");x=(0,Jl.isOwnProperty)(t,w,_)}else c.length?x=(0,bn.or)(...c.map(w=>(0,bn._)`${_} === ${w}`)):x=bn.nil;return d.length&&(x=(0,bn.or)(x,...d.map(w=>(0,bn._)`${(0,Jl.usePattern)(n,w)}.test(${_})`))),(0,bn.not)(x)}function f(_){t.code((0,bn._)`delete ${a}[${_}]`)}function g(_){if(l.removeAdditional==="all"||l.removeAdditional&&e===!1){f(_);return}if(e===!1){n.setParams({additionalProperty:_}),n.error(),s||t.break();return}if(typeof e=="object"&&!(0,ec.alwaysValidSchema)(o,e)){let x=t.name("valid");l.removeAdditional==="failing"?(b(_,x,!1),t.if((0,bn.not)(x),()=>{n.reset(),f(_)})):(b(_,x),s||t.if((0,bn.not)(x),()=>t.break()))}}function b(_,x,w){let C={keyword:"additionalProperties",dataProp:_,dataPropType:ec.Type.Str};w===!1&&Object.assign(C,{compositeRule:!0,createErrors:!1,allErrors:!1}),n.subschema(C,x)}}};Np.default=jD});var Zv=L(Tp=>{"use strict";Object.defineProperty(Tp,"__esModule",{value:!0});var VD=So(),Yv=tn(),Ap=Se(),Xv=Ip(),$D={keyword:"properties",type:"object",schemaType:"object",code(n){let{gen:t,schema:e,parentSchema:i,data:a,it:r}=n;r.opts.removeAdditional==="all"&&i.additionalProperties===void 0&&Xv.default.code(new VD.KeywordCxt(r,Xv.default,"additionalProperties"));let o=(0,Yv.allSchemaProperties)(e);for(let m of o)r.definedProperties.add(m);r.opts.unevaluated&&o.length&&r.props!==!0&&(r.props=Ap.mergeEvaluated.props(t,(0,Ap.toHash)(o),r.props));let s=o.filter(m=>!(0,Ap.alwaysValidSchema)(r,e[m]));if(s.length===0)return;let l=t.name("valid");for(let m of s)c(m)?d(m):(t.if((0,Yv.propertyInData)(t,a,m,r.opts.ownProperties)),d(m),r.allErrors||t.else().var(l,!0),t.endIf()),n.it.definedProperties.add(m),n.ok(l);function c(m){return r.opts.useDefaults&&!r.compositeRule&&e[m].default!==void 0}function d(m){n.subschema({keyword:"properties",schemaProp:m,dataProp:m},l)}}};Tp.default=$D});var t_=L(Op=>{"use strict";Object.defineProperty(Op,"__esModule",{value:!0});var Qv=tn(),tc=ue(),Jv=Se(),e_=Se(),zD={keyword:"patternProperties",type:"object",schemaType:"object",code(n){let{gen:t,schema:e,data:i,parentSchema:a,it:r}=n,{opts:o}=r,s=(0,Qv.allSchemaProperties)(e),l=s.filter(b=>(0,Jv.alwaysValidSchema)(r,e[b]));if(s.length===0||l.length===s.length&&(!r.opts.unevaluated||r.props===!0))return;let c=o.strictSchema&&!o.allowMatchingProperties&&a.properties,d=t.name("valid");r.props!==!0&&!(r.props instanceof tc.Name)&&(r.props=(0,e_.evaluatedPropsToName)(t,r.props));let{props:m}=r;u();function u(){for(let b of s)c&&f(b),r.allErrors?g(b):(t.var(d,!0),g(b),t.if(d))}function f(b){for(let _ in c)new RegExp(b).test(_)&&(0,Jv.checkStrictMode)(r,`property ${_} matches pattern ${b} (use allowMatchingProperties)`)}function g(b){t.forIn("key",i,_=>{t.if((0,tc._)`${(0,Qv.usePattern)(n,b)}.test(${_})`,()=>{let x=l.includes(b);x||n.subschema({keyword:"patternProperties",schemaProp:b,dataProp:_,dataPropType:e_.Type.Str},d),r.opts.unevaluated&&m!==!0?t.assign((0,tc._)`${m}[${_}]`,!0):!x&&!r.allErrors&&t.if((0,tc.not)(d),()=>t.break())})})}}};Op.default=zD});var n_=L(Rp=>{"use strict";Object.defineProperty(Rp,"__esModule",{value:!0});var HD=Se(),UD={keyword:"not",schemaType:["object","boolean"],trackErrors:!0,code(n){let{gen:t,schema:e,it:i}=n;if((0,HD.alwaysValidSchema)(i,e)){n.fail();return}let a=t.name("valid");n.subschema({keyword:"not",compositeRule:!0,createErrors:!1,allErrors:!1},a),n.failResult(a,()=>n.reset(),()=>n.error())},error:{message:"must NOT be valid"}};Rp.default=UD});var i_=L(Pp=>{"use strict";Object.defineProperty(Pp,"__esModule",{value:!0});var qD=tn(),GD={keyword:"anyOf",schemaType:"array",trackErrors:!0,code:qD.validateUnion,error:{message:"must match a schema in anyOf"}};Pp.default=GD});var a_=L(Fp=>{"use strict";Object.defineProperty(Fp,"__esModule",{value:!0});var nc=ue(),WD=Se(),KD={message:"must match exactly one schema in oneOf",params:({params:n})=>(0,nc._)`{passingSchemas: ${n.passing}}`},YD={keyword:"oneOf",schemaType:"array",trackErrors:!0,error:KD,code(n){let{gen:t,schema:e,parentSchema:i,it:a}=n;if(!Array.isArray(e))throw new Error("ajv implementation error");if(a.opts.discriminator&&i.discriminator)return;let r=e,o=t.let("valid",!1),s=t.let("passing",null),l=t.name("_valid");n.setParams({passing:s}),t.block(c),n.result(o,()=>n.reset(),()=>n.error(!0));function c(){r.forEach((d,m)=>{let u;(0,WD.alwaysValidSchema)(a,d)?t.var(l,!0):u=n.subschema({keyword:"oneOf",schemaProp:m,compositeRule:!0},l),m>0&&t.if((0,nc._)`${l} && ${o}`).assign(o,!1).assign(s,(0,nc._)`[${s}, ${m}]`).else(),t.if(l,()=>{t.assign(o,!0),t.assign(s,m),u&&n.mergeEvaluated(u,nc.Name)})})}}};Fp.default=YD});var r_=L(Lp=>{"use strict";Object.defineProperty(Lp,"__esModule",{value:!0});var XD=Se(),ZD={keyword:"allOf",schemaType:"array",code(n){let{gen:t,schema:e,it:i}=n;if(!Array.isArray(e))throw new Error("ajv implementation error");let a=t.name("valid");e.forEach((r,o)=>{if((0,XD.alwaysValidSchema)(i,r))return;let s=n.subschema({keyword:"allOf",schemaProp:o},a);n.ok(a),n.mergeEvaluated(s)})}};Lp.default=ZD});var l_=L(Bp=>{"use strict";Object.defineProperty(Bp,"__esModule",{value:!0});var ic=ue(),s_=Se(),QD={message:({params:n})=>(0,ic.str)`must match "${n.ifClause}" schema`,params:({params:n})=>(0,ic._)`{failingKeyword: ${n.ifClause}}`},JD={keyword:"if",schemaType:["object","boolean"],trackErrors:!0,error:QD,code(n){let{gen:t,parentSchema:e,it:i}=n;e.then===void 0&&e.else===void 0&&(0,s_.checkStrictMode)(i,'"if" without "then" and "else" is ignored');let a=o_(i,"then"),r=o_(i,"else");if(!a&&!r)return;let o=t.let("valid",!0),s=t.name("_valid");if(l(),n.reset(),a&&r){let d=t.let("ifClause");n.setParams({ifClause:d}),t.if(s,c("then",d),c("else",d))}else a?t.if(s,c("then")):t.if((0,ic.not)(s),c("else"));n.pass(o,()=>n.error(!0));function l(){let d=n.subschema({keyword:"if",compositeRule:!0,createErrors:!1,allErrors:!1},s);n.mergeEvaluated(d)}function c(d,m){return()=>{let u=n.subschema({keyword:d},s);t.assign(o,s),n.mergeValidEvaluated(u,o),m?t.assign(m,(0,ic._)`${d}`):n.setParams({ifClause:d})}}}};function o_(n,t){let e=n.schema[t];return e!==void 0&&!(0,s_.alwaysValidSchema)(n,e)}Bp.default=JD});var c_=L(jp=>{"use strict";Object.defineProperty(jp,"__esModule",{value:!0});var e1=Se(),t1={keyword:["then","else"],schemaType:["object","boolean"],code({keyword:n,parentSchema:t,it:e}){t.if===void 0&&(0,e1.checkStrictMode)(e,`"${n}" without "if" is ignored`)}};jp.default=t1});var d_=L(Vp=>{"use strict";Object.defineProperty(Vp,"__esModule",{value:!0});var n1=wp(),i1=Vv(),a1=Sp(),r1=zv(),o1=Hv(),s1=Gv(),l1=Kv(),c1=Ip(),d1=Zv(),m1=t_(),p1=n_(),u1=i_(),h1=a_(),f1=r_(),g1=l_(),b1=c_();function v1(n=!1){let t=[p1.default,u1.default,h1.default,f1.default,g1.default,b1.default,l1.default,c1.default,s1.default,d1.default,m1.default];return n?t.push(i1.default,r1.default):t.push(n1.default,a1.default),t.push(o1.default),t}Vp.default=v1});var m_=L($p=>{"use strict";Object.defineProperty($p,"__esModule",{value:!0});var it=ue(),_1={message:({schemaCode:n})=>(0,it.str)`must match format "${n}"`,params:({schemaCode:n})=>(0,it._)`{format: ${n}}`},y1={keyword:"format",type:["number","string"],schemaType:"string",$data:!0,error:_1,code(n,t){let{gen:e,data:i,$data:a,schema:r,schemaCode:o,it:s}=n,{opts:l,errSchemaPath:c,schemaEnv:d,self:m}=s;if(!l.validateFormats)return;a?u():f();function u(){let g=e.scopeValue("formats",{ref:m.formats,code:l.code.formats}),b=e.const("fDef",(0,it._)`${g}[${o}]`),_=e.let("fType"),x=e.let("format");e.if((0,it._)`typeof ${b} == "object" && !(${b} instanceof RegExp)`,()=>e.assign(_,(0,it._)`${b}.type || "string"`).assign(x,(0,it._)`${b}.validate`),()=>e.assign(_,(0,it._)`"string"`).assign(x,b)),n.fail$data((0,it.or)(w(),C()));function w(){return l.strictSchema===!1?it.nil:(0,it._)`${o} && !${x}`}function C(){let F=d.$async?(0,it._)`(${b}.async ? await ${x}(${i}) : ${x}(${i}))`:(0,it._)`${x}(${i})`,A=(0,it._)`(typeof ${x} == "function" ? ${F} : ${x}.test(${i}))`;return(0,it._)`${x} && ${x} !== true && ${_} === ${t} && !${A}`}}function f(){let g=m.formats[r];if(!g){w();return}if(g===!0)return;let[b,_,x]=C(g);b===t&&n.pass(F());function w(){if(l.strictSchema===!1){m.logger.warn(A());return}throw new Error(A());function A(){return`unknown format "${r}" ignored in schema at path "${c}"`}}function C(A){let Q=A instanceof RegExp?(0,it.regexpCode)(A):l.code.formats?(0,it._)`${l.code.formats}${(0,it.getProperty)(r)}`:void 0,Re=e.scopeValue("formats",{key:r,ref:A,code:Q});return typeof A=="object"&&!(A instanceof RegExp)?[A.type||"string",A.validate,(0,it._)`${Re}.validate`]:["string",A,Re]}function F(){if(typeof g=="object"&&!(g instanceof RegExp)&&g.async){if(!d.$async)throw new Error("async format in sync schema");return(0,it._)`await ${x}(${i})`}return typeof _=="function"?(0,it._)`${x}(${i})`:(0,it._)`${x}.test(${i})`}}}};$p.default=y1});var p_=L(zp=>{"use strict";Object.defineProperty(zp,"__esModule",{value:!0});var x1=m_(),w1=[x1.default];zp.default=w1});var u_=L(Ya=>{"use strict";Object.defineProperty(Ya,"__esModule",{value:!0});Ya.contentVocabulary=Ya.metadataVocabulary=void 0;Ya.metadataVocabulary=["title","description","default","deprecated","readOnly","writeOnly","examples"];Ya.contentVocabulary=["contentMediaType","contentEncoding","contentSchema"]});var f_=L(Hp=>{"use strict";Object.defineProperty(Hp,"__esModule",{value:!0});var S1=wv(),k1=Fv(),C1=d_(),E1=p_(),h_=u_(),D1=[S1.default,k1.default,(0,C1.default)(),E1.default,h_.metadataVocabulary,h_.contentVocabulary];Hp.default=D1});var b_=L(ac=>{"use strict";Object.defineProperty(ac,"__esModule",{value:!0});ac.DiscrError=void 0;var g_;(function(n){n.Tag="tag",n.Mapping="mapping"})(g_||(ac.DiscrError=g_={}))});var __=L(qp=>{"use strict";Object.defineProperty(qp,"__esModule",{value:!0});var Xa=ue(),Up=b_(),v_=jl(),M1=ko(),N1=Se(),I1={message:({params:{discrError:n,tagName:t}})=>n===Up.DiscrError.Tag?`tag "${t}" must be string`:`value of tag "${t}" must be in oneOf`,params:({params:{discrError:n,tag:t,tagName:e}})=>(0,Xa._)`{error: ${n}, tag: ${e}, tagValue: ${t}}`},A1={keyword:"discriminator",type:"object",schemaType:"object",error:I1,code(n){let{gen:t,data:e,schema:i,parentSchema:a,it:r}=n,{oneOf:o}=a;if(!r.opts.discriminator)throw new Error("discriminator: requires discriminator option");let s=i.propertyName;if(typeof s!="string")throw new Error("discriminator: requires propertyName");if(i.mapping)throw new Error("discriminator: mapping is not supported");if(!o)throw new Error("discriminator: requires oneOf keyword");let l=t.let("valid",!1),c=t.const("tag",(0,Xa._)`${e}${(0,Xa.getProperty)(s)}`);t.if((0,Xa._)`typeof ${c} == "string"`,()=>d(),()=>n.error(!1,{discrError:Up.DiscrError.Tag,tag:c,tagName:s})),n.ok(l);function d(){let f=u();t.if(!1);for(let g in f)t.elseIf((0,Xa._)`${c} === ${g}`),t.assign(l,m(f[g]));t.else(),n.error(!1,{discrError:Up.DiscrError.Mapping,tag:c,tagName:s}),t.endIf()}function m(f){let g=t.name("valid"),b=n.subschema({keyword:"oneOf",schemaProp:f},g);return n.mergeEvaluated(b,Xa.Name),g}function u(){var f;let g={},b=x(a),_=!0;for(let F=0;F<o.length;F++){let A=o[F];if(A?.$ref&&!(0,N1.schemaHasRulesButRef)(A,r.self.RULES)){let Re=A.$ref;if(A=v_.resolveRef.call(r.self,r.schemaEnv.root,r.baseId,Re),A instanceof v_.SchemaEnv&&(A=A.schema),A===void 0)throw new M1.default(r.opts.uriResolver,r.baseId,Re)}let Q=(f=A?.properties)===null||f===void 0?void 0:f[s];if(typeof Q!="object")throw new Error(`discriminator: oneOf subschemas (or referenced schemas) must have "properties/${s}"`);_=_&&(b||x(A)),w(Q,F)}if(!_)throw new Error(`discriminator: "${s}" must be required`);return g;function x({required:F}){return Array.isArray(F)&&F.includes(s)}function w(F,A){if(F.const)C(F.const,A);else if(F.enum)for(let Q of F.enum)C(Q,A);else throw new Error(`discriminator: "properties/${s}" must have "const" or "enum"`)}function C(F,A){if(typeof F!="string"||F in g)throw new Error(`discriminator: "${s}" values must be unique strings`);g[F]=A}}}};qp.default=A1});var y_=L((cV,T1)=>{T1.exports={$schema:"http://json-schema.org/draft-07/schema#",$id:"http://json-schema.org/draft-07/schema#",title:"Core schema meta-schema",definitions:{schemaArray:{type:"array",minItems:1,items:{$ref:"#"}},nonNegativeInteger:{type:"integer",minimum:0},nonNegativeIntegerDefault0:{allOf:[{$ref:"#/definitions/nonNegativeInteger"},{default:0}]},simpleTypes:{enum:["array","boolean","integer","null","number","object","string"]},stringArray:{type:"array",items:{type:"string"},uniqueItems:!0,default:[]}},type:["object","boolean"],properties:{$id:{type:"string",format:"uri-reference"},$schema:{type:"string",format:"uri"},$ref:{type:"string",format:"uri-reference"},$comment:{type:"string"},title:{type:"string"},description:{type:"string"},default:!0,readOnly:{type:"boolean",default:!1},examples:{type:"array",items:!0},multipleOf:{type:"number",exclusiveMinimum:0},maximum:{type:"number"},exclusiveMaximum:{type:"number"},minimum:{type:"number"},exclusiveMinimum:{type:"number"},maxLength:{$ref:"#/definitions/nonNegativeInteger"},minLength:{$ref:"#/definitions/nonNegativeIntegerDefault0"},pattern:{type:"string",format:"regex"},additionalItems:{$ref:"#"},items:{anyOf:[{$ref:"#"},{$ref:"#/definitions/schemaArray"}],default:!0},maxItems:{$ref:"#/definitions/nonNegativeInteger"},minItems:{$ref:"#/definitions/nonNegativeIntegerDefault0"},uniqueItems:{type:"boolean",default:!1},contains:{$ref:"#"},maxProperties:{$ref:"#/definitions/nonNegativeInteger"},minProperties:{$ref:"#/definitions/nonNegativeIntegerDefault0"},required:{$ref:"#/definitions/stringArray"},additionalProperties:{$ref:"#"},definitions:{type:"object",additionalProperties:{$ref:"#"},default:{}},properties:{type:"object",additionalProperties:{$ref:"#"},default:{}},patternProperties:{type:"object",additionalProperties:{$ref:"#"},propertyNames:{format:"regex"},default:{}},dependencies:{type:"object",additionalProperties:{anyOf:[{$ref:"#"},{$ref:"#/definitions/stringArray"}]}},propertyNames:{$ref:"#"},const:!0,enum:{type:"array",items:!0,minItems:1,uniqueItems:!0},type:{anyOf:[{$ref:"#/definitions/simpleTypes"},{type:"array",items:{$ref:"#/definitions/simpleTypes"},minItems:1,uniqueItems:!0}]},format:{type:"string"},contentMediaType:{type:"string"},contentEncoding:{type:"string"},if:{$ref:"#"},then:{$ref:"#"},else:{$ref:"#"},allOf:{$ref:"#/definitions/schemaArray"},anyOf:{$ref:"#/definitions/schemaArray"},oneOf:{$ref:"#/definitions/schemaArray"},not:{$ref:"#"}},default:!0}});var w_=L((ze,Gp)=>{"use strict";Object.defineProperty(ze,"__esModule",{value:!0});ze.MissingRefError=ze.ValidationError=ze.CodeGen=ze.Name=ze.nil=ze.stringify=ze.str=ze._=ze.KeywordCxt=ze.Ajv=void 0;var O1=gv(),R1=f_(),P1=__(),x_=y_(),F1=["/properties"],rc="http://json-schema.org/draft-07/schema",Za=class extends O1.default{_addVocabularies(){super._addVocabularies(),R1.default.forEach(t=>this.addVocabulary(t)),this.opts.discriminator&&this.addKeyword(P1.default)}_addDefaultMetaSchema(){if(super._addDefaultMetaSchema(),!this.opts.meta)return;let t=this.opts.$data?this.$dataMetaSchema(x_,F1):x_;this.addMetaSchema(t,rc,!1),this.refs["http://json-schema.org/schema"]=rc}defaultMeta(){return this.opts.defaultMeta=super.defaultMeta()||(this.getSchema(rc)?rc:void 0)}};ze.Ajv=Za;Gp.exports=ze=Za;Gp.exports.Ajv=Za;Object.defineProperty(ze,"__esModule",{value:!0});ze.default=Za;var L1=So();Object.defineProperty(ze,"KeywordCxt",{enumerable:!0,get:function(){return L1.KeywordCxt}});var Qa=ue();Object.defineProperty(ze,"_",{enumerable:!0,get:function(){return Qa._}});Object.defineProperty(ze,"str",{enumerable:!0,get:function(){return Qa.str}});Object.defineProperty(ze,"stringify",{enumerable:!0,get:function(){return Qa.stringify}});Object.defineProperty(ze,"nil",{enumerable:!0,get:function(){return Qa.nil}});Object.defineProperty(ze,"Name",{enumerable:!0,get:function(){return Qa.Name}});Object.defineProperty(ze,"CodeGen",{enumerable:!0,get:function(){return Qa.CodeGen}});var B1=Ll();Object.defineProperty(ze,"ValidationError",{enumerable:!0,get:function(){return B1.default}});var j1=ko();Object.defineProperty(ze,"MissingRefError",{enumerable:!0,get:function(){return j1.default}})});var Sh=null;function jn(){return Sh}function Wc(n){Sh??=n}var Rr=class{},ya=(()=>{class n{historyGo(e){throw new Error("")}static \u0275fac=function(i){return new(i||n)};static \u0275prov=te({token:n,factory:()=>p(kh),providedIn:"platform"})}return n})();var kh=(()=>{class n extends ya{_location;_history;_doc=p(Y);constructor(){super(),this._location=window.location,this._history=window.history}getBaseHrefFromDOM(){return jn().getBaseHref(this._doc)}onPopState(e){let i=jn().getGlobalEventTarget(this._doc,"window");return i.addEventListener("popstate",e,!1),()=>i.removeEventListener("popstate",e)}onHashChange(e){let i=jn().getGlobalEventTarget(this._doc,"window");return i.addEventListener("hashchange",e,!1),()=>i.removeEventListener("hashchange",e)}get href(){return this._location.href}get protocol(){return this._location.protocol}get hostname(){return this._location.hostname}get port(){return this._location.port}get pathname(){return this._location.pathname}get search(){return this._location.search}get hash(){return this._location.hash}set pathname(e){this._location.pathname=e}pushState(e,i,a){this._history.pushState(e,i,a)}replaceState(e,i,a){this._history.replaceState(e,i,a)}forward(){this._history.forward()}back(){this._history.back()}historyGo(e=0){this._history.go(e)}getState(){return this._history.state}static \u0275fac=function(i){return new(i||n)};static \u0275prov=te({token:n,factory:()=>new n,providedIn:"platform"})}return n})();function Dh(n,t){return n?t?n.endsWith("/")?t.startsWith("/")?n+t.slice(1):n+t:t.startsWith("/")?n+t:`${n}/${t}`:n:t}function Ch(n){let t=n.search(/#|\?|$/);return n[t-1]==="/"?n.slice(0,t-1)+n.slice(t):n}function li(n){return n&&n[0]!=="?"?`?${n}`:n}var Ss=(()=>{class n{historyGo(e){throw new Error("")}static \u0275fac=function(i){return new(i||n)};static \u0275prov=te({token:n,factory:()=>p(Ty),providedIn:"root"})}return n})(),Ay=new I(""),Ty=(()=>{class n extends Ss{_platformLocation;_baseHref;_removeListenerFns=[];constructor(e,i){super(),this._platformLocation=e,this._baseHref=i??this._platformLocation.getBaseHrefFromDOM()??p(Y).location?.origin??""}ngOnDestroy(){for(;this._removeListenerFns.length;)this._removeListenerFns.pop()()}onPopState(e){this._removeListenerFns.push(this._platformLocation.onPopState(e),this._platformLocation.onHashChange(e))}getBaseHref(){return this._baseHref}prepareExternalUrl(e){return Dh(this._baseHref,e)}path(e=!1){let i=this._platformLocation.pathname+li(this._platformLocation.search),a=this._platformLocation.hash;return a&&e?`${i}${a}`:i}pushState(e,i,a,r){let o=this.prepareExternalUrl(a+li(r));this._platformLocation.pushState(e,i,o)}replaceState(e,i,a,r){let o=this.prepareExternalUrl(a+li(r));this._platformLocation.replaceState(e,i,o)}forward(){this._platformLocation.forward()}back(){this._platformLocation.back()}getState(){return this._platformLocation.getState()}historyGo(e=0){this._platformLocation.historyGo?.(e)}static \u0275fac=function(i){return new(i||n)(B(ya),B(Ay,8))};static \u0275prov=te({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var xa=(()=>{class n{_subject=new z;_basePath;_locationStrategy;_urlChangeListeners=[];_urlChangeSubscription=null;constructor(e){this._locationStrategy=e;let i=this._locationStrategy.getBaseHref();this._basePath=Py(Ch(Eh(i))),this._locationStrategy.onPopState(a=>{let r={url:this.path(!0),pop:!0,state:a.state,type:a.type};a.hasUAVisualTransition&&(r.hasUAVisualTransition=!0),this._subject.next(r)})}ngOnDestroy(){this._urlChangeSubscription?.unsubscribe(),this._urlChangeListeners=[]}path(e=!1){return this.normalize(this._locationStrategy.path(e))}getState(){return this._locationStrategy.getState()}isCurrentPathEqualTo(e,i=""){return this.path()==this.normalize(e+li(i))}normalize(e){return n.stripTrailingSlash(Ry(this._basePath,Eh(e)))}prepareExternalUrl(e){return e&&e[0]!=="/"&&(e="/"+e),this._locationStrategy.prepareExternalUrl(e)}go(e,i="",a=null){this._locationStrategy.pushState(a,"",e,i),this._notifyUrlChangeListeners(this.prepareExternalUrl(e+li(i)),a)}replaceState(e,i="",a=null){this._locationStrategy.replaceState(a,"",e,i),this._notifyUrlChangeListeners(this.prepareExternalUrl(e+li(i)),a)}forward(){this._locationStrategy.forward()}back(){this._locationStrategy.back()}historyGo(e=0){this._locationStrategy.historyGo?.(e)}onUrlChange(e){return this._urlChangeListeners.push(e),this._urlChangeSubscription??=this.subscribe(i=>{this._notifyUrlChangeListeners(i.url,i.state)}),()=>{let i=this._urlChangeListeners.indexOf(e);this._urlChangeListeners.splice(i,1),this._urlChangeListeners.length===0&&(this._urlChangeSubscription?.unsubscribe(),this._urlChangeSubscription=null)}}_notifyUrlChangeListeners(e="",i){this._urlChangeListeners.forEach(a=>a(e,i))}subscribe(e,i,a){return this._subject.subscribe({next:e,error:i??void 0,complete:a??void 0})}static normalizeQueryParams=li;static joinWithSlash=Dh;static stripTrailingSlash=Ch;static \u0275fac=function(i){return new(i||n)(B(Ss))};static \u0275prov=te({token:n,factory:()=>Oy(),providedIn:"root"})}return n})();function Oy(){return new xa(B(Ss))}function Ry(n,t){if(!n||!t.startsWith(n))return t;let e=t.substring(n.length);return e===""||["/",";","?","#"].includes(e[0])?e:t}function Eh(n){return n.replace(/\/index\.html$/,"")}function Py(n){if(new RegExp("^(https?:)?//").test(n)){let[,e]=n.split(/\/\/[^\/]+/);return e}return n}var Kc=/\s+/,Mh=[],Pr=(()=>{class n{_ngEl;_renderer;initialClasses=Mh;rawClass;stateMap=new Map;constructor(e,i){this._ngEl=e,this._renderer=i}set klass(e){this.initialClasses=e!=null?e.trim().split(Kc):Mh}set ngClass(e){this.rawClass=typeof e=="string"?e.trim().split(Kc):e}ngDoCheck(){for(let i of this.initialClasses)this._updateState(i,!0);let e=this.rawClass;if(Array.isArray(e)||e instanceof Set)for(let i of e)this._updateState(i,!0);else if(e!=null)for(let i of Object.keys(e))this._updateState(i,!!e[i]);this._applyStateDiff()}_updateState(e,i){let a=this.stateMap.get(e);a!==void 0?(a.enabled!==i&&(a.changed=!0,a.enabled=i),a.touched=!0):this.stateMap.set(e,{enabled:i,changed:!0,touched:!0})}_applyStateDiff(){for(let e of this.stateMap){let i=e[0],a=e[1];a.changed?(this._toggleClass(i,a.enabled),a.changed=!1):a.touched||(a.enabled&&this._toggleClass(i,!1),this.stateMap.delete(i)),a.touched=!1}}_toggleClass(e,i){e=e.trim(),e.length>0&&e.split(Kc).forEach(a=>{i?this._renderer.addClass(this._ngEl.nativeElement,a):this._renderer.removeClass(this._ngEl.nativeElement,a)})}static \u0275fac=function(i){return new(i||n)(Oe(X),Oe(pt))};static \u0275dir=K({type:n,selectors:[["","ngClass",""]],inputs:{klass:[0,"class","klass"],ngClass:"ngClass"}})}return n})();var Fr=(()=>{class n{_viewContainerRef;_viewRef=null;ngTemplateOutletContext=null;ngTemplateOutlet=null;ngTemplateOutletInjector=null;injector=p(ce);constructor(e){this._viewContainerRef=e}ngOnChanges(e){if(this._shouldRecreateView(e)){let i=this._viewContainerRef;if(this._viewRef&&i.remove(i.indexOf(this._viewRef)),!this.ngTemplateOutlet){this._viewRef=null;return}let a=this._createContextForwardProxy();this._viewRef=i.createEmbeddedView(this.ngTemplateOutlet,a,{injector:this._getInjector()})}}_getInjector(){return this.ngTemplateOutletInjector==="outlet"?this.injector:this.ngTemplateOutletInjector??void 0}_shouldRecreateView(e){return!!e.ngTemplateOutlet||!!e.ngTemplateOutletInjector}_createContextForwardProxy(){return new Proxy({},{set:(e,i,a)=>this.ngTemplateOutletContext?Reflect.set(this.ngTemplateOutletContext,i,a):!1,get:(e,i,a)=>{if(this.ngTemplateOutletContext)return Reflect.get(this.ngTemplateOutletContext,i,a)}})}static \u0275fac=function(i){return new(i||n)(Oe(xn))};static \u0275dir=K({type:n,selectors:[["","ngTemplateOutlet",""]],inputs:{ngTemplateOutletContext:"ngTemplateOutletContext",ngTemplateOutlet:"ngTemplateOutlet",ngTemplateOutletInjector:"ngTemplateOutletInjector"},features:[_t]})}return n})();function By(n,t){return{key:n,value:t}}var Lr=(()=>{class n{differs;constructor(e){this.differs=e}differ;keyValues=[];compareFn=Nh;transform(e,i=Nh){if(!e||!(e instanceof Map)&&typeof e!="object")return null;this.differ??=this.differs.find(e).create();let a=this.differ.diff(e),r=i!==this.compareFn;return a&&(this.keyValues=[],a.forEachItem(o=>{this.keyValues.push(By(o.key,o.currentValue))})),(a||r)&&(i&&this.keyValues.sort(i),this.compareFn=i),this.keyValues}static \u0275fac=function(i){return new(i||n)(Oe(yh,16))};static \u0275pipe=Vc({name:"keyvalue",type:n,pure:!1})}return n})();function Nh(n,t){let e=n.key,i=t.key;if(e===i)return 0;if(e==null)return 1;if(i==null)return-1;if(typeof e=="string"&&typeof i=="string")return e<i?-1:1;if(typeof e=="number"&&typeof i=="number")return e-i;if(typeof e=="boolean"&&typeof i=="boolean")return e<i?-1:1;let a=String(e),r=String(i);return a==r?0:a<r?-1:1}var wn=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({})}return n})();function Br(n,t){t=encodeURIComponent(t);for(let e of n.split(";")){let i=e.indexOf("="),[a,r]=i==-1?[e,""]:[e.slice(0,i),e.slice(i+1)];if(a.trim()!==t)continue;let o=r;try{o=decodeURIComponent(r)}catch{}return o.length>1&&o[0]==='"'&&o[o.length-1]==='"'&&(o=o.slice(1,-1)),o}return null}var jy=(()=>{class n{build(){return new XMLHttpRequest}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),Ai=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275prov=te({token:n,factory:function(i){let a=null;return i?a=new(i||n):a=B(jy),a},providedIn:"root"})}return n})();var Xc="browser";function ci(n){return n===Xc}var Vr=class{_doc;constructor(t){this._doc=t}manager},ks=(()=>{class n extends Vr{constructor(e){super(e)}supports(e){return!0}addEventListener(e,i,a,r){return e.addEventListener(i,a,r),()=>this.removeEventListener(e,i,a,r)}removeEventListener(e,i,a,r){return e.removeEventListener(i,a,r)}static \u0275fac=function(i){return new(i||n)(B(Y))};static \u0275prov=te({token:n,factory:n.\u0275fac})}return n})(),Ds=new I(""),ed=(()=>{class n{_zone;_plugins;_eventNameToPlugin=new Map;constructor(e,i){this._zone=i,e.forEach(o=>{o.manager=this});let a=e.filter(o=>!(o instanceof ks));this._plugins=a.slice().reverse();let r=e.find(o=>o instanceof ks);r&&this._plugins.push(r)}addEventListener(e,i,a,r){return this._findPluginFor(i).addEventListener(e,i,a,r)}getZone(){return this._zone}_findPluginFor(e){let i=this._eventNameToPlugin.get(e);if(i)return i;if(i=this._plugins.find(r=>r.supports(e)),!i)throw new Fe(-5101,!1);return this._eventNameToPlugin.set(e,i),i}static \u0275fac=function(i){return new(i||n)(B(Ds),B(W))};static \u0275prov=te({token:n,factory:n.\u0275fac})}return n})(),Zc="ng-app-id";function Ih(n){for(let t of n)t.remove()}function Ah(n,t){let e=t.createElement("style");return e.textContent=n,e}function $y(n,t,e,i){let a=n.head?.querySelectorAll(`style[${Zc}="${t}"],link[${Zc}="${t}"]`);if(!a||a.length===0)return!1;for(let r of a)r.removeAttribute(Zc),r instanceof HTMLLinkElement?i.set(r.href.slice(r.href.lastIndexOf("/")+1),{usage:0,elements:[r]}):r.textContent&&e.set(r.textContent,{usage:0,elements:[r]});return!0}function Jc(n,t){let e=t.createElement("link");return e.setAttribute("rel","stylesheet"),e.setAttribute("href",n),e}var td=(()=>{class n{doc;appId;nonce;inline=new Map;external=new Map;hosts=new Set;constructor(e,i,a,r={}){this.doc=e,this.appId=i,this.nonce=a,$y(e,i,this.inline,this.external)&&this.hosts.add(e.head)}addStyles(e,i){for(let a of e)this.addUsage(a,this.inline,Ah);i?.forEach(a=>this.addUsage(a,this.external,Jc))}removeStyles(e,i){for(let a of e)this.removeUsage(a,this.inline);i?.forEach(a=>this.removeUsage(a,this.external))}addUsage(e,i,a){let r=i.get(e);r?r.usage++:i.set(e,{usage:1,elements:[...this.hosts].map(o=>this.addElement(o,a(e,this.doc)))})}removeUsage(e,i){let a=i.get(e);a&&(a.usage--,a.usage<=0&&(Ih(a.elements),i.delete(e)))}ngOnDestroy(){for(let[,{elements:e}]of[...this.inline,...this.external])Ih(e);this.hosts.clear()}addHost(e){if(!this.hosts.has(e)){this.hosts.add(e);for(let[i,{elements:a}]of this.inline)a.push(this.addElement(e,Ah(i,this.doc)));for(let[i,{elements:a}]of this.external)a.push(this.addElement(e,Jc(i,this.doc)))}}removeHost(e){this.hosts.delete(e);for(let i of[...this.inline.values(),...this.external.values()]){let a=[];for(let r of i.elements)r.parentNode===e?r.remove():a.push(r);i.elements=a}}addElement(e,i){return this.nonce&&i.setAttribute("nonce",this.nonce),e.appendChild(i)}static \u0275fac=function(i){return new(i||n)(B(Y),B(kr),B(ha,8),B(ni))};static \u0275prov=te({token:n,factory:n.\u0275fac})}return n})(),Qc={svg:"http://www.w3.org/2000/svg",xhtml:"http://www.w3.org/1999/xhtml",xlink:"http://www.w3.org/1999/xlink",xml:"http://www.w3.org/XML/1998/namespace",xmlns:"http://www.w3.org/2000/xmlns/",math:"http://www.w3.org/1998/Math/MathML"},nd=/%COMP%/g;var Oh="%COMP%",zy=`_nghost-${Oh}`,Hy=`_ngcontent-${Oh}`,Uy=!0,qy=new I("",{factory:()=>Uy}),Gy=new I("");function Wy(n){return Hy.replace(nd,n)}function Ky(n){return zy.replace(nd,n)}function Rh(n,t){return t.map(e=>e.replace(nd,n))}var Hr=(()=>{class n{eventManager;sharedStylesHost;appId;removeStylesOnCompDestroy;doc;ngZone;nonce;tracingService;rendererByCompId=new Map;defaultRenderer;cssVarNamespace;constructor(e,i,a,r,o,s,l=null,c=null,d=null){this.eventManager=e,this.sharedStylesHost=i,this.appId=a,this.removeStylesOnCompDestroy=r,this.doc=o,this.ngZone=s,this.nonce=l,this.tracingService=c,this.cssVarNamespace=d??"",this.defaultRenderer=new $r(e,o,s,this.tracingService,this.cssVarNamespace)}createRenderer(e,i){if(!e||!i)return this.defaultRenderer;let a=this.getOrCreateRenderer(e,i);return a instanceof Es?a.applyToHost(e):a instanceof zr&&a.applyStyles(),a}getOrCreateRenderer(e,i){let a=this.rendererByCompId,r=a.get(i.id);if(!r){let o=this.doc,s=this.ngZone,l=this.eventManager,c=this.sharedStylesHost,d=this.removeStylesOnCompDestroy,m=this.tracingService;switch(i.encapsulation){case hs.Emulated:r=new Es(l,c,i,this.appId,d,o,s,m,this.cssVarNamespace);break;case hs.ShadowDom:return new Cs(l,e,i,o,s,this.nonce,m,this.cssVarNamespace,c);case hs.ExperimentalIsolatedShadowDom:return new Cs(l,e,i,o,s,this.nonce,m,this.cssVarNamespace);default:r=new zr(l,c,i,d,o,s,m,this.cssVarNamespace);break}a.set(i.id,r)}return r}ngOnDestroy(){this.rendererByCompId.clear()}componentReplaced(e){this.rendererByCompId.delete(e)}static \u0275fac=function(i){return new(i||n)(B(ed),B(Er),B(kr),B(qy),B(Y),B(W),B(ha),B(Cr,8),B(Gy,8))};static \u0275prov=te({token:n,factory:n.\u0275fac})}return n})(),$r=class{eventManager;doc;ngZone;tracingService;cssVarNamespace;data=Object.create(null);throwOnSyntheticProps=!0;constructor(t,e,i,a,r=""){this.eventManager=t,this.doc=e,this.ngZone=i,this.tracingService=a,this.cssVarNamespace=r}destroy(){}destroyNode=null;createElement(t,e){return e?this.doc.createElementNS(Qc[e]||e,t):this.doc.createElement(t)}createComment(t){return this.doc.createComment(t)}createText(t){return this.doc.createTextNode(t)}appendChild(t,e){(Th(t)?t.content:t).appendChild(e)}insertBefore(t,e,i){if(t){let a=Th(t)?t.content:t;if(i!=null&&i.parentNode!==a)throw new Fe(-5106,!1);a.insertBefore(e,i)}}removeChild(t,e){e.remove()}selectRootElement(t,e){let i=typeof t=="string"?this.doc.querySelector(t):t;if(!i)throw new Fe(-5104,!1);return e||(i.textContent=""),i}parentNode(t){return t.parentNode}nextSibling(t){return t.nextSibling}setAttribute(t,e,i,a){if(a){e=a+":"+e;let r=Qc[a];r?t.setAttributeNS(r,e,i):t.setAttribute(e,i)}else t.setAttribute(e,i)}removeAttribute(t,e,i){if(i){let a=Qc[i];a?t.removeAttributeNS(a,e):t.removeAttribute(`${i}:${e}`)}else t.removeAttribute(e)}addClass(t,e){t.classList.add(e)}removeClass(t,e){t.classList.remove(e)}setStyle(t,e,i,a){let r=e.startsWith("--");r&&(e=e.replace("%NS%",this.cssVarNamespace)),r||a&(ba.DashCase|ba.Important)?t.style.setProperty(e,i,a&ba.Important?"important":""):t.style[e]=i}removeStyle(t,e,i){let a=e.startsWith("--");a&&(e=e.replace("%NS%",this.cssVarNamespace)),a||i&ba.DashCase?t.style.removeProperty(e):t.style[e]=""}setProperty(t,e,i){t!=null&&(t[e]=i)}setValue(t,e){t.nodeValue=e}listen(t,e,i,a){if(typeof t=="string"&&(t=jn().getGlobalEventTarget(this.doc,t),!t))throw new Fe(-5102,!1);let r=this.decoratePreventDefault(i);return this.tracingService?.wrapEventListener&&(r=this.tracingService.wrapEventListener(t,e,r)),this.eventManager.addEventListener(t,e,r,a)}decoratePreventDefault(t){return e=>{if(e==="__ngUnwrap__")return t;t(e)===!1&&e.preventDefault()}}};function Th(n){return n.tagName==="TEMPLATE"&&n.content!==void 0}var Cs=class extends $r{hostEl;sharedStylesHost;shadowRoot;constructor(t,e,i,a,r,o,s,l,c){super(t,a,r,s,l),this.hostEl=e,this.sharedStylesHost=c,this.shadowRoot=e.attachShadow({mode:"open"}),this.sharedStylesHost&&this.sharedStylesHost.addHost(this.shadowRoot);let d=i.styles;d=Rh(i.id,d).map(u=>u.replace(/%NS%/g,l));for(let u of d){let f=document.createElement("style");o&&f.setAttribute("nonce",o),f.textContent=u,this.shadowRoot.appendChild(f)}let m=i.getExternalStyles?.();if(m)for(let u of m){let f=Jc(u,a);o&&f.setAttribute("nonce",o),this.shadowRoot.appendChild(f)}}nodeOrShadowRoot(t){return t===this.hostEl?this.shadowRoot:t}appendChild(t,e){return super.appendChild(this.nodeOrShadowRoot(t),e)}insertBefore(t,e,i){return super.insertBefore(this.nodeOrShadowRoot(t),e,i)}removeChild(t,e){return super.removeChild(null,e)}parentNode(t){return this.nodeOrShadowRoot(super.parentNode(this.nodeOrShadowRoot(t)))}destroy(){this.sharedStylesHost&&this.sharedStylesHost.removeHost(this.shadowRoot)}},zr=class extends $r{sharedStylesHost;removeStylesOnCompDestroy;styles;styleUrls;constructor(t,e,i,a,r,o,s,l,c){super(t,r,o,s,l),this.sharedStylesHost=e,this.removeStylesOnCompDestroy=a;let d=i.styles,m=c?Rh(c,d):d;this.styles=m.map(u=>u.replace(/%NS%/g,l)),this.styleUrls=i.getExternalStyles?.(c)}applyStyles(){this.sharedStylesHost.addStyles(this.styles,this.styleUrls)}destroy(){this.removeStylesOnCompDestroy&&dh.size===0&&this.sharedStylesHost.removeStyles(this.styles,this.styleUrls)}},Es=class extends zr{contentAttr;hostAttr;constructor(t,e,i,a,r,o,s,l,c){let d=a+"-"+i.id;super(t,e,i,r,o,s,l,c,d),this.contentAttr=Wy(d),this.hostAttr=Ky(d)}applyToHost(t){this.applyStyles(),this.setAttribute(t,this.hostAttr,"")}createElement(t,e){let i=super.createElement(t,e);return super.setAttribute(i,this.contentAttr,""),i}};var Ms=class n extends Rr{supportsDOMEvents=!0;static makeCurrent(){Wc(new n)}onAndCancel(t,e,i,a){return t.addEventListener(e,i,a),()=>{t.removeEventListener(e,i,a)}}dispatchEvent(t,e){t.dispatchEvent(e)}remove(t){t.remove()}createElement(t,e){return e=e||this.getDefaultDocument(),e.createElement(t)}createHtmlDocument(){return document.implementation.createHTMLDocument("fakeTitle")}getDefaultDocument(){return document}isElementNode(t){return t.nodeType===Node.ELEMENT_NODE}isShadowRoot(t){return t instanceof DocumentFragment}getGlobalEventTarget(t,e){return e==="window"?window:e==="document"?t:e==="body"?t.body:null}getBaseHref(t){let e=Xy();return e==null?null:Zy(e)}resetBaseElement(){Ur=null}getUserAgent(){return window.navigator.userAgent}getCookie(t){return Br(document.cookie,t)}},Ur=null;function Xy(){return Ur=Ur||document.head.querySelector("base"),Ur?Ur.getAttribute("href"):null}function Zy(n){return new URL(n,document.baseURI).pathname}var Ph=["alt","control","meta","shift"],Qy={"\b":"Backspace","	":"Tab","\x7F":"Delete","\x1B":"Escape",Del:"Delete",Esc:"Escape",Left:"ArrowLeft",Right:"ArrowRight",Up:"ArrowUp",Down:"ArrowDown",Menu:"ContextMenu",Scroll:"ScrollLock",Win:"OS"},Jy={alt:n=>n.altKey,control:n=>n.ctrlKey,meta:n=>n.metaKey,shift:n=>n.shiftKey},Fh=(()=>{class n extends Vr{constructor(e){super(e)}supports(e){return n.parseEventName(e)!=null}addEventListener(e,i,a,r){let o=n.parseEventName(i),s=n.eventCallback(o.fullKey,a,this.manager.getZone());return this.manager.getZone().runOutsideAngular(()=>jn().onAndCancel(e,o.domEventName,s,r))}static parseEventName(e){let i=e.toLowerCase().split("."),a=i.shift();if(i.length===0||!(a==="keydown"||a==="keyup"))return null;let r=n._normalizeKey(i.pop()),o="",s=i.indexOf("code");if(s>-1&&(i.splice(s,1),o="code."),Ph.forEach(c=>{let d=i.indexOf(c);d>-1&&(i.splice(d,1),o+=c+".")}),o+=r,i.length!=0||r.length===0)return null;let l={};return l.domEventName=a,l.fullKey=o,l}static matchEventFullKeyCode(e,i){let a=Qy[e.key]||e.key,r="";return i.indexOf("code.")>-1&&(a=e.code,r="code."),a==null||!a?!1:(a=a.toLowerCase(),a===" "?a="space":a==="."&&(a="dot"),Ph.forEach(o=>{if(o!==a){let s=Jy[o];s(e)&&(r+=o+".")}}),r+=a,r===i)}static eventCallback(e,i,a){return r=>{n.matchEventFullKeyCode(r,e)&&a.runGuarded(()=>i(r))}}static _normalizeKey(e){return e==="esc"?"escape":e}static \u0275fac=function(i){return new(i||n)(B(Y))};static \u0275prov=te({token:n,factory:n.\u0275fac})}return n})();async function id(n,t,e){let i=N({rootComponent:n},e0(t,e));return xh(i)}function e0(n,t){return{platformRef:t?.platformRef,appProviders:[...r0,...n?.providers??[]],platformProviders:a0}}function t0(){Ms.makeCurrent()}function n0(){return new Di}function i0(){return nh(document),document}var a0=[{provide:ni,useValue:Xc},{provide:eh,useValue:t0,multi:!0},{provide:Y,useFactory:i0}];var r0=[{provide:Ju,useValue:"root"},{provide:Di,useFactory:n0},{provide:Ds,useClass:ks,multi:!0},{provide:Ds,useClass:Fh,multi:!0},Hr,{provide:Er,useClass:td},{provide:td,useExisting:Er},ed,{provide:xt,useExisting:Hr},[]];var Yt=class n{headers;normalizedNames=new Map;lazyInit;lazyUpdate=null;constructor(t){t?typeof t=="string"?this.lazyInit=()=>{this.headers=new Map,t.split(`
`).forEach(e=>{let i=e.indexOf(":");if(i>0){let a=e.slice(0,i),r=e.slice(i+1).trim();this.addHeaderEntry(a,r)}})}:typeof Headers<"u"&&t instanceof Headers?(this.headers=new Map,t.forEach((e,i)=>{this.addHeaderEntry(i,e)})):this.lazyInit=()=>{this.headers=new Map,Object.entries(t).forEach(([e,i])=>{this.setHeaderEntries(e,i)})}:this.headers=new Map}has(t){return this.init(),this.headers.has(t.toLowerCase())}get(t){this.init();let e=this.headers.get(t.toLowerCase());return e&&e.length>0?e[0]:null}keys(){return this.init(),Array.from(this.normalizedNames.values())}getAll(t){return this.init(),this.headers.get(t.toLowerCase())||null}append(t,e){return this.clone({name:t,value:e,op:"a"})}set(t,e){return this.clone({name:t,value:e,op:"s"})}delete(t,e){return this.clone({name:t,value:e,op:"d"})}maybeSetNormalizedName(t,e){this.normalizedNames.has(e)||this.normalizedNames.set(e,t)}init(){this.lazyInit&&(this.lazyInit instanceof n?this.copyFrom(this.lazyInit):this.lazyInit(),this.lazyInit=null,this.lazyUpdate&&(this.lazyUpdate.forEach(t=>this.applyUpdate(t)),this.lazyUpdate=null))}copyFrom(t){t.init();for(let[e,i]of t.headers.entries())this.headers.set(e,i),this.normalizedNames.set(e,t.normalizedNames.get(e))}clone(t){let e=new n;return e.lazyInit=this.lazyInit&&this.lazyInit instanceof n?this.lazyInit:this,e.lazyUpdate=(this.lazyUpdate||[]).concat([t]),e}applyUpdate(t){let e=t.name.toLowerCase();switch(t.op){case"a":case"s":let i=t.value;if(typeof i=="string"&&(i=[i]),i.length===0)return;this.maybeSetNormalizedName(t.name,e);let a=t.op==="a"?(this.headers.get(e)||[]).slice():[];a.push(...i),this.headers.set(e,a);break;case"d":let r=t.value;if(r===void 0)this.headers.delete(e),this.normalizedNames.delete(e);else{let o=Array.isArray(r)?r:[r],s=this.headers.get(e);if(!s)return;s=s.filter(l=>o.indexOf(l)===-1),s.length===0?(this.headers.delete(e),this.normalizedNames.delete(e)):this.headers.set(e,s)}break}}addHeaderEntry(t,e){let i=t.toLowerCase();this.maybeSetNormalizedName(t,i),this.headers.has(i)?this.headers.get(i).push(e):this.headers.set(i,[e])}setHeaderEntries(t,e){let i=(Array.isArray(e)?e:[e]).map(r=>r.toString()),a=t.toLowerCase();this.headers.set(a,i),this.maybeSetNormalizedName(t,a)}forEach(t){this.init(),Array.from(this.normalizedNames.keys()).forEach(e=>t(this.normalizedNames.get(e),this.headers.get(e)))}};var Is=class{map=new Map;set(t,e){return this.map.set(t,e),this}get(t){return this.map.has(t)||this.map.set(t,t.defaultValue()),this.map.get(t)}delete(t){return this.map.delete(t),this}has(t){return this.map.has(t)}keys(){return this.map.keys()}},As=class{encodeKey(t){return Lh(t)}encodeValue(t){return Lh(t)}decodeKey(t){return decodeURIComponent(t)}decodeValue(t){return decodeURIComponent(t)}};function o0(n,t){let e=new Map;return n.length>0&&n.replace(/^\?/,"").split("&").forEach(a=>{let r=a.indexOf("="),[o,s]=r==-1?[t.decodeKey(a),""]:[t.decodeKey(a.slice(0,r)),t.decodeValue(a.slice(r+1))],l=e.get(o)||[];l.push(s),e.set(o,l)}),e}var s0=/%(\d[a-f0-9])/gi,l0={40:"@","3A":":",24:"$","2C":",","3B":";","3D":"=","3F":"?","2F":"/"};function Lh(n){return encodeURIComponent(n).replace(s0,(t,e)=>l0[e]??t)}function Ns(n){return`${n}`}var qt=class n{map;encoder;updates=null;cloneFrom=null;constructor(t={}){if(this.encoder=t.encoder||new As,t.fromString){if(t.fromObject)throw new Fe(2805,!1);this.map=o0(t.fromString,this.encoder)}else t.fromObject?(this.map=new Map,Object.keys(t.fromObject).forEach(e=>{let i=t.fromObject[e],a=Array.isArray(i)?i.map(Ns):[Ns(i)];this.map.set(e,a)})):this.map=null}has(t){return this.init(),this.map.has(t)}get(t){this.init();let e=this.map.get(t);return e?e[0]:null}getAll(t){return this.init(),this.map.get(t)||null}keys(){return this.init(),Array.from(this.map.keys())}append(t,e){return this.clone({param:t,value:e,op:"a"})}appendAll(t){let e=[];return Object.keys(t).forEach(i=>{let a=t[i];Array.isArray(a)?a.forEach(r=>{e.push({param:i,value:r,op:"a"})}):e.push({param:i,value:a,op:"a"})}),this.clone(e)}set(t,e){return this.clone({param:t,value:e,op:"s"})}delete(t,e){return this.clone({param:t,value:e,op:"d"})}toString(){return this.init(),this.keys().map(t=>{let e=this.encoder.encodeKey(t);return this.map.get(t).map(i=>e+"="+this.encoder.encodeValue(i)).join("&")}).filter(t=>t!=="").join("&")}clone(t){let e=new n({encoder:this.encoder});return e.cloneFrom=this.cloneFrom||this,e.updates=(this.updates||[]).concat(t),e}init(){if(this.map===null&&(this.map=new Map),this.cloneFrom!==null){this.cloneFrom.init();for(let[t,e]of this.cloneFrom.map.entries())this.map.set(t,e);this.updates.forEach(t=>{switch(t.op){case"a":case"s":let e=t.op==="a"?(this.map.get(t.param)||[]).slice():[];e.push(Ns(t.value)),this.map.set(t.param,e);break;case"d":if(t.value!==void 0){let i=(this.map.get(t.param)||[]).slice(),a=i.indexOf(Ns(t.value));a!==-1&&i.splice(a,1),i.length>0?this.map.set(t.param,i):this.map.delete(t.param)}else{this.map.delete(t.param);break}}}),this.cloneFrom=this.updates=null}}};function c0(n){switch(n){case"DELETE":case"GET":case"HEAD":case"OPTIONS":case"JSONP":return!1;default:return!0}}function Bh(n){return typeof ArrayBuffer<"u"&&n instanceof ArrayBuffer}function jh(n){return typeof Blob<"u"&&n instanceof Blob}function Vh(n){return typeof FormData<"u"&&n instanceof FormData}function d0(n){return typeof URLSearchParams<"u"&&n instanceof URLSearchParams}var qr="Content-Type",Ts="Accept",Uh="text/plain",qh="application/json",Gh=`${qh}, ${Uh}, */*`,wa=class n{url;body=null;headers;context;reportProgress=!1;reportUploadProgress=!1;reportDownloadProgress=!1;withCredentials=!1;credentials;keepalive=!1;cache;priority;mode;redirect;referrer;integrity;referrerPolicy;responseType="json";method;params;urlWithParams;transferCache;timeout;constructor(t,e,i,a){this.url=e,this.method=t.toUpperCase();let r;if(c0(this.method)||a?(this.body=i!==void 0?i:null,r=a):r=i,r){if(this.reportProgress=!!r.reportProgress,this.reportUploadProgress=!!r.reportUploadProgress,this.reportDownloadProgress=!!r.reportDownloadProgress,this.withCredentials=!!r.withCredentials,this.keepalive=!!r.keepalive,r.responseType&&(this.responseType=r.responseType),r.headers&&(this.headers=r.headers),r.context&&(this.context=r.context),r.params&&(this.params=r.params),r.priority&&(this.priority=r.priority),r.cache&&(this.cache=r.cache),r.credentials&&(this.credentials=r.credentials),typeof r.timeout=="number"){if(r.timeout<1||!Number.isInteger(r.timeout))throw new Fe(2822,"");this.timeout=r.timeout}r.mode&&(this.mode=r.mode),r.redirect&&(this.redirect=r.redirect),r.integrity&&(this.integrity=r.integrity),r.referrer!==void 0&&(this.referrer=r.referrer),r.referrerPolicy&&(this.referrerPolicy=r.referrerPolicy),this.transferCache=r.transferCache}if(this.headers??=new Yt,this.context??=new Is,!this.params)this.params=new qt,this.urlWithParams=e;else{let o=this.params.toString();if(o.length===0)this.urlWithParams=e;else{let s=e,l="",c=e.indexOf("#");c!==-1&&(l=e.substring(c),s=e.substring(0,c));let d=s.indexOf("?"),m=d===-1?"?":d<s.length-1?"&":"";this.urlWithParams=s+m+o+l}}}serializeBody(){return this.body===null?null:typeof this.body=="string"||Bh(this.body)||jh(this.body)||Vh(this.body)||d0(this.body)?this.body:this.body instanceof qt?this.body.toString():typeof this.body=="object"||typeof this.body=="boolean"||Array.isArray(this.body)?JSON.stringify(this.body):this.body.toString()}detectContentTypeHeader(){return this.body===null||Vh(this.body)?null:jh(this.body)?this.body.type||null:Bh(this.body)?null:typeof this.body=="string"?Uh:this.body instanceof qt?"application/x-www-form-urlencoded;charset=UTF-8":typeof this.body=="object"||typeof this.body=="number"||typeof this.body=="boolean"?qh:null}clone(t={}){let e=t.method||this.method,i=t.url||this.url,a=t.responseType||this.responseType,r=t.keepalive??this.keepalive,o=t.priority||this.priority,s=t.cache||this.cache,l=t.mode||this.mode,c=t.redirect||this.redirect,d=t.credentials||this.credentials,m=t.referrer??this.referrer,u=t.integrity||this.integrity,f=t.referrerPolicy||this.referrerPolicy,g=t.transferCache??this.transferCache,b=t.timeout??this.timeout,_=t.body!==void 0?t.body:this.body,x=t.withCredentials??this.withCredentials,w=t.reportProgress??this.reportProgress,C=t.reportUploadProgress??this.reportUploadProgress,F=t.reportDownloadProgress??this.reportDownloadProgress,A=t.headers||this.headers,Q=t.params||this.params,Re=t.context??this.context;return t.setHeaders!==void 0&&(A=Object.keys(t.setHeaders).reduce((He,Be)=>He.set(Be,t.setHeaders[Be]),A)),t.setParams&&(Q=Object.keys(t.setParams).reduce((He,Be)=>He.set(Be,t.setParams[Be]),Q)),new n(e,i,_,{params:Q,headers:A,context:Re,reportProgress:w,reportUploadProgress:C,reportDownloadProgress:F,responseType:a,withCredentials:x,transferCache:g,keepalive:r,cache:s,priority:o,timeout:b,mode:l,redirect:c,credentials:d,referrer:m,integrity:u,referrerPolicy:f})}},kn=(function(n){return n[n.Sent=0]="Sent",n[n.UploadProgress=1]="UploadProgress",n[n.ResponseHeader=2]="ResponseHeader",n[n.DownloadProgress=3]="DownloadProgress",n[n.Response=4]="Response",n[n.User=5]="User",n})(kn||{}),Sa=class{headers;status;statusText;url;ok;type;redirected;responseType;constructor(t,e=200,i="OK"){this.headers=t.headers||new Yt,this.status=t.status!==void 0?t.status:e,this.statusText=t.statusText||i,this.url=t.url||null,this.redirected=t.redirected,this.responseType=t.responseType,this.ok=this.status>=200&&this.status<300}},Gr=class n extends Sa{constructor(t={}){super(t)}type=kn.ResponseHeader;clone(t={}){return new n({headers:t.headers||this.headers,status:t.status!==void 0?t.status:this.status,statusText:t.statusText||this.statusText,url:t.url||this.url||void 0})}},di=class n extends Sa{body;constructor(t={}){super(t),this.body=t.body!==void 0?t.body:null}type=kn.Response;clone(t={}){return new n({body:t.body!==void 0?t.body:this.body,headers:t.headers||this.headers,status:t.status!==void 0?t.status:this.status,statusText:t.statusText||this.statusText,url:t.url||this.url||void 0,redirected:t.redirected??this.redirected,responseType:t.responseType??this.responseType})}},Sn=class extends Sa{name="HttpErrorResponse";message;error;ok=!1;constructor(t){super(t,0,"Unknown Error"),this.status>=200&&this.status<300?this.message=`Http failure during parsing for ${t.url||"(unknown url)"}`:this.message=`Http failure response for ${t.url||"(unknown url)"}: ${t.status} ${t.statusText}`,this.error=t.error||null}},Wh=200,m0=204;var p0=/^\)\]\}',?\n/,IT=1024*1024,Kh=new I("",{factory:()=>null}),Os=(()=>{class n{fetchImpl=p(rd,{optional:!0})?.fetch??((...e)=>globalThis.fetch(...e));ngZone=p(W);destroyRef=p(Ei);maxResponseSize=p(Kh);handle(e){return new Nt(i=>{let a=new AbortController,r=!1,o={next:l=>{l.type===kn.Response&&(r=!0),i.next(l)},error:l=>{r=!0,i.error(l)},complete:()=>{r=!0,i.complete()}};this.doRequest(e,a.signal,o).then(od,l=>o.error(new Sn({error:l})));let s;return e.timeout&&(s=this.ngZone.runOutsideAngular(()=>setTimeout(()=>{a.signal.aborted||a.abort(new DOMException("signal timed out","TimeoutError"))},e.timeout))),()=>{s!==void 0&&clearTimeout(s),!r&&!a.signal.aborted&&a.abort()}})}async doRequest(e,i,a){let r=this.createRequestInit(e),o;try{let _=this.ngZone.runOutsideAngular(()=>this.fetchImpl(e.urlWithParams,N({signal:i},r)));u0(_),a.next({type:kn.Sent}),o=await _}catch(_){a.error(new Sn({error:_,status:_.status??0,statusText:_.statusText,url:e.urlWithParams,headers:_.headers}));return}let s=new Yt(o.headers),l=o.statusText,c=o.url||e.urlWithParams,d=o.status,m=null,u=e.reportProgress||e.reportDownloadProgress;if(u&&a.next(new Gr({headers:s,status:d,statusText:l,url:c})),o.body){let _=o.headers.get(qr)??"",x=o.headers.get("content-length"),w=x!==null?Number(x):NaN;this.maxResponseSize!==null&&Number.isFinite(w)&&w>this.maxResponseSize&&(await o.body.cancel(),$h(this.maxResponseSize));let C=[],F=o.body.getReader(),A=0,Q,Re,He=typeof Zone<"u"&&Zone.current,Be=!1;if(await this.ngZone.runOutsideAngular(async()=>{for(;;){if(this.destroyRef.destroyed){await F.cancel(),Be=!0;break}let{done:re,value:k}=await F.read();if(re)break;if(C.push(k),A+=k.length,this.maxResponseSize!==null&&A>this.maxResponseSize&&(await F.cancel(),$h(this.maxResponseSize)),u){Re=e.responseType==="text"?(Re??"")+(Q??=zh(_)).decode(k,{stream:!0}):void 0;let le=()=>a.next({type:kn.DownloadProgress,total:Number.isFinite(w)?w:void 0,loaded:A,partialText:Re});He?He.run(le):le()}}}),Be){a.complete();return}let q=this.concatChunks(C,A);try{m=this.parseBody(e,q,_,d)}catch(re){a.error(new Sn({error:re,headers:new Yt(o.headers),status:o.status,statusText:o.statusText,url:o.url||e.urlWithParams}));return}}d===0&&(d=m?Wh:0);let f=d>=200&&d<300,g=o.redirected,b=o.type;f?(a.next(new di({body:m,headers:s,status:d,statusText:l,url:c,redirected:g,responseType:b})),a.complete()):a.error(new Sn({error:m,headers:s,status:d,statusText:l,url:c,redirected:g,responseType:b}))}parseBody(e,i,a,r){switch(e.responseType){case"json":let o=new TextDecoder().decode(i).replace(p0,"");if(o==="")return null;try{return JSON.parse(o)}catch(s){if(r<200||r>=300)return o;throw s}case"text":return zh(a).decode(i);case"blob":return new Blob([i],{type:a});case"arraybuffer":return i.buffer}}createRequestInit(e){if(e.reportUploadProgress)throw new Fe(2824,!1);let i={},a;if(a=e.credentials,e.withCredentials&&(a="include"),e.headers.forEach((r,o)=>i[r]=o.join(",")),e.headers.has(Ts)||(i[Ts]=Gh),!e.headers.has(qr)){let r=e.detectContentTypeHeader();r!==null&&(i[qr]=r)}return{body:e.serializeBody(),method:e.method,headers:i,credentials:a,keepalive:e.keepalive,cache:e.cache,priority:e.priority,mode:e.mode,redirect:e.redirect,referrer:e.referrer,integrity:e.integrity,referrerPolicy:e.referrerPolicy}}concatChunks(e,i){let a=new Uint8Array(i),r=0;for(let o of e)a.set(o,r),r+=o.length;return a}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),rd=class{};function od(){}function u0(n){n.then(od,od)}function $h(n){throw new Fe(-2825,!1)}var h0=/charset=\s*["']?([^;"'\s]+)["']?/i;function zh(n){let t=n.match(h0);if(t!==null)try{return new TextDecoder(t[1])}catch{}return new TextDecoder}var f0=new I("",{factory:()=>!0}),g0="XSRF-TOKEN",b0=new I("",{factory:()=>g0}),v0="X-XSRF-TOKEN",_0=new I("",{factory:()=>v0}),y0=(()=>{class n{cookieName=p(b0);doc=p(Y);lastCookieString="";lastToken=null;parseCount=0;getToken(){let e=this.doc.cookie||"";return e!==this.lastCookieString&&(this.parseCount++,this.lastToken=Br(e,this.cookieName),this.lastCookieString=e),this.lastToken}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),Yh=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275prov=te({token:n,factory:function(i){let a=null;return i?a=new(i||n):a=B(y0),a},providedIn:"root"})}return n})();function Xh(n,t){if(!p(f0)||n.method==="GET"||n.method==="HEAD")return t(n);try{let a=p(ya).href,{origin:r}=new URL(a),{origin:o}=new URL(n.url,r);if(r!==o)return t(n)}catch{return t(n)}let e=p(Yh).getToken(),i=p(_0);return e!=null&&!n.headers.has(i)&&(n=n.clone({headers:n.headers.set(i,e)})),t(n)}function Zh(n,t){return t(n)}function x0(n,t){return(e,i)=>t.intercept(e,{handle:a=>n(a,i)})}function w0(n,t,e){return(i,a)=>ps(e,()=>t(i,r=>n(r,a)))}var Qh=new I(""),sd=new I("",{factory:()=>[Xh]}),Jh=new I(""),ld=new I("",{factory:()=>!0});function S0(){let n=null;return(t,e)=>{n===null&&(n=(p(Qh,{optional:!0})??[]).reduceRight(x0,Zh));let i=p(us);if(p(ld)){let r=i.add();return n(t,e).pipe(Sr(r))}else return n(t,e)}}var Wr=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275prov=te({token:n,factory:function(i){let a=null;return i?a=new(i||n):a=B(Os),a},providedIn:"root"})}return n})();var Rs=(()=>{class n{backend;injector;chain=null;pendingTasks=p(us);contributeToStability=p(ld);constructor(e,i){this.backend=e,this.injector=i}handle(e){if(this.chain===null){let a=this.injector.get(Ps,null,{skipSelf:!0}),r=a!==null&&this.backend===a,o=this.injector.get(Jh,[],r?{self:!0}:void 0),s=Array.from(new Set([...this.injector.get(sd),...o]));this.chain=s.reduceRight((l,c)=>w0(l,c,this.injector),Zh)}let i=this.chain;if(this.contributeToStability){let a=this.pendingTasks.add();return Ii(()=>i(e,r=>this.backend.handle(r))).pipe(Sr(a))}else return Ii(()=>i(e,a=>this.backend.handle(a)))}static \u0275fac=function(i){return new(i||n)(B(Wr),B(Ln))};static \u0275prov=te({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),Ps=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275prov=te({token:n,factory:function(i){let a=null;return i?a=new(i||n):a=B(Rs),a},providedIn:"root"})}return n})();function ad(n,t){return N({body:t},n)}var Xt=(()=>{class n{handler;constructor(e){this.handler=e}request(e,i,a={}){let r;if(e instanceof wa)r=e;else{let l;a.headers instanceof Yt?l=a.headers:l=new Yt(a.headers);let c;a.params&&(a.params instanceof qt?c=a.params:c=new qt({fromObject:a.params})),r=new wa(e,i,a.body!==void 0?a.body:null,{headers:l,context:a.context,params:c,reportProgress:a.reportProgress,reportUploadProgress:a.reportUploadProgress,reportDownloadProgress:a.reportDownloadProgress,responseType:a.responseType||"json",withCredentials:a.withCredentials,transferCache:a.transferCache,keepalive:a.keepalive,priority:a.priority,cache:a.cache,mode:a.mode,redirect:a.redirect,credentials:a.credentials,referrer:a.referrer,referrerPolicy:a.referrerPolicy,integrity:a.integrity,timeout:a.timeout})}let o=Ue(r).pipe(yr(l=>this.handler.handle(l)));if(e instanceof wa||a.observe==="events")return o;let s=o.pipe(We(l=>l instanceof di));switch(a.observe||"body"){case"body":switch(r.responseType){case"arraybuffer":return s.pipe(De(l=>{if(l.body!==null&&!(l.body instanceof ArrayBuffer))throw new Fe(2806,!1);return l.body}));case"blob":return s.pipe(De(l=>{if(l.body!==null&&!(l.body instanceof Blob))throw new Fe(2807,!1);return l.body}));case"text":return s.pipe(De(l=>{if(l.body!==null&&typeof l.body!="string")throw new Fe(2808,!1);return l.body}));default:return s.pipe(De(l=>l.body))}case"response":return s;default:throw new Fe(2809,!1)}}delete(e,i={}){return this.request("DELETE",e,i)}get(e,i={}){return this.request("GET",e,i)}head(e,i={}){return this.request("HEAD",e,i)}jsonp(e,i){return this.request("JSONP",e,{params:new qt().append(i,"JSONP_CALLBACK"),observe:"body",responseType:"json"})}options(e,i={}){return this.request("OPTIONS",e,i)}patch(e,i,a={}){return this.request("PATCH",e,ad(a,i))}post(e,i,a={}){return this.request("POST",e,ad(a,i))}put(e,i,a={}){return this.request("PUT",e,ad(a,i))}static \u0275fac=function(i){return new(i||n)(B(Ps))};static \u0275prov=te({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var k0=/^\)\]\}',?\n/;var cd=(()=>{class n{xhrFactory;tracingService=p(Cr,{optional:!0});constructor(e){this.xhrFactory=e}maybePropagateTrace(e){return this.tracingService?.propagate?this.tracingService.propagate(e):e}handle(e){if(e.method==="JSONP")throw new Fe(-2800,!1);let i=this.xhrFactory;return Ue(null).pipe(Gt(()=>new Nt(r=>{let o=i.build();if(o.open(e.method,e.urlWithParams),e.withCredentials&&(o.withCredentials=!0),e.headers.forEach((w,C)=>o.setRequestHeader(w,C.join(","))),e.headers.has(Ts)||o.setRequestHeader(Ts,Gh),!e.headers.has(qr)){let w=e.detectContentTypeHeader();w!==null&&o.setRequestHeader(qr,w)}if(e.timeout&&(o.timeout=e.timeout),e.responseType){let w=e.responseType.toLowerCase();o.responseType=w!=="json"?w:"text"}let s=e.serializeBody(),l=null,c=()=>{if(l!==null)return l;let w=o.statusText||"OK",C=new Yt(o.getAllResponseHeaders()),F=o.responseURL||e.url;return l=new Gr({headers:C,status:o.status,statusText:w,url:F}),l},d=this.maybePropagateTrace(()=>{let{headers:w,status:C,statusText:F,url:A}=c(),Q=null;C!==m0&&(Q=typeof o.response>"u"?o.responseText:o.response),C===0&&(C=Q?Wh:0);let Re=C>=200&&C<300;if(e.responseType==="json"&&typeof Q=="string"){let He=Q;Q=Q.replace(k0,"");try{Q=Q!==""?JSON.parse(Q):null}catch(Be){Q=He,Re&&(Re=!1,Q={error:Be,text:Q})}}Re?(r.next(new di({body:Q,headers:w,status:C,statusText:F,url:A||void 0})),r.complete()):r.error(new Sn({error:Q,headers:w,status:C,statusText:F,url:A||void 0}))}),m=this.maybePropagateTrace(w=>{let{url:C}=c(),F=new Sn({error:w,status:o.status||0,statusText:o.statusText||"Unknown Error",url:C||void 0});r.error(F)}),u=m;e.timeout&&(u=this.maybePropagateTrace(w=>{let{url:C}=c(),F=new Sn({error:new DOMException("Request timed out","TimeoutError"),status:o.status||0,statusText:o.statusText||"Request timeout",url:C||void 0});r.error(F)}));let f=!1,g=this.maybePropagateTrace(w=>{f||(r.next(c()),f=!0);let C={type:kn.DownloadProgress,loaded:w.loaded};w.lengthComputable&&(C.total=w.total),e.responseType==="text"&&o.responseText&&(C.partialText=o.responseText),r.next(C)}),b=this.maybePropagateTrace(w=>{let C={type:kn.UploadProgress,loaded:w.loaded};w.lengthComputable&&(C.total=w.total),r.next(C)});o.addEventListener("load",d),o.addEventListener("error",m),o.addEventListener("timeout",u),o.addEventListener("abort",m);let _=e.reportProgress||e.reportUploadProgress,x=e.reportProgress||e.reportDownloadProgress;return x&&o.addEventListener("progress",g),_&&s!==null&&o.upload&&o.upload.addEventListener("progress",b),o.send(s),r.next({type:kn.Sent}),()=>{o.removeEventListener("error",m),o.removeEventListener("abort",m),o.removeEventListener("load",d),o.removeEventListener("timeout",u),x&&o.removeEventListener("progress",g),_&&s!==null&&o.upload&&o.upload.removeEventListener("progress",b),o.readyState!==o.DONE&&o.abort()}})))}static \u0275fac=function(i){return new(i||n)(B(Ai))};static \u0275prov=te({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),dd=(function(n){return n[n.Interceptors=0]="Interceptors",n[n.LegacyInterceptors=1]="LegacyInterceptors",n[n.CustomXsrfConfiguration=2]="CustomXsrfConfiguration",n[n.NoXsrfProtection=3]="NoXsrfProtection",n[n.JsonpSupport=4]="JsonpSupport",n[n.RequestsMadeViaParent=5]="RequestsMadeViaParent",n[n.Fetch=6]="Fetch",n[n.Xhr=7]="Xhr",n})(dd||{});function C0(n,t){return{\u0275kind:n,\u0275providers:t}}function md(...n){let t=[Xt,Os,Rs,{provide:Ps,useExisting:Rs},{provide:Wr,useFactory:()=>p(Os)},{provide:sd,useValue:Xh,multi:!0}];for(let e of n)t.push(...e.\u0275providers);return ma(t)}var Hh=new I("");function pd(){return C0(dd.LegacyInterceptors,[{provide:Hh,useFactory:S0},{provide:sd,useExisting:Hh,multi:!0}])}var Vn=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275prov=te({token:n,factory:function(i){let a=null;return i?a=new(i||n):a=B(D0),a},providedIn:"root"})}return n})(),D0=(()=>{class n extends Vn{_doc=p(Y);sanitize(e,i){if(i==null)return null;switch(e){case dt.NONE:return i;case dt.HTML:return ga(i,"HTML")?fa(i):ch(this._doc,String(i)).toString();case dt.STYLE:return ga(i,"Style")?fa(i):i;case dt.SCRIPT:if(ga(i,"Script"))return fa(i);throw new Fe(5200,!1);case dt.URL:return ga(i,"URL")?fa(i):lh(String(i));case dt.RESOURCE_URL:if(ga(i,"ResourceURL"))return fa(i);throw new Fe(-5201,!1);default:throw new Fe(5202,!1)}}bypassSecurityTrustHtml(e){return ih(e)}bypassSecurityTrustStyle(e){return ah(e)}bypassSecurityTrustScript(e){return rh(e)}bypassSecurityTrustUrl(e){return oh(e)}bypassSecurityTrustResourceUrl(e){return sh(e)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var Ls={production:!0};var M0="@",N0=(()=>{class n{doc;delegate;zone;animationType;moduleImpl;_rendererFactoryPromise=null;scheduler=null;injector=p(ce);loadingSchedulerFn=p(I0,{optional:!0});_engine;constructor(e,i,a,r,o){this.doc=e,this.delegate=i,this.zone=a,this.animationType=r,this.moduleImpl=o}ngOnDestroy(){this._engine?.flush()}loadImpl(){let e=()=>this.moduleImpl??import("./chunk-UD7W42PG.js").then(a=>a),i;return this.loadingSchedulerFn?i=this.loadingSchedulerFn(e):i=e(),i.catch(a=>{throw new Fe(5300,!1)}).then(({\u0275createEngine:a,\u0275AnimationRendererFactory:r})=>{this._engine=a(this.animationType,this.doc);let o=new r(this.delegate,this._engine,this.zone);return this.delegate=o,o})}createRenderer(e,i){let a=this.delegate.createRenderer(e,i);if(a.\u0275type===0)return a;typeof a.throwOnSyntheticProps=="boolean"&&(a.throwOnSyntheticProps=!1);let r=new ud(a);return i?.data?.animation&&!this._rendererFactoryPromise&&(this._rendererFactoryPromise=this.loadImpl()),this._rendererFactoryPromise?.then(o=>{let s=o.createRenderer(e,i);r.use(s),this.scheduler??=this.injector.get(th,null,{optional:!0}),this.scheduler?.notify(10)}).catch(o=>{r.use(a)}),r}begin(){this.delegate.begin?.()}end(){this.delegate.end?.()}whenRenderingDone(){return this.delegate.whenRenderingDone?.()??Promise.resolve()}componentReplaced(e){this._engine?.flush(),this.delegate.componentReplaced?.(e)}static \u0275fac=function(i){fs()};static \u0275prov=te({token:n,factory:n.\u0275fac})}return n})(),ud=class{delegate;replay=[];\u0275type=1;constructor(t){this.delegate=t}use(t){if(this.delegate=t,this.replay!==null){for(let e of this.replay)e(t);this.replay=null}}get data(){return this.delegate.data}destroy(){this.replay=null,this.delegate.destroy()}createElement(t,e){return this.delegate.createElement(t,e)}createComment(t){return this.delegate.createComment(t)}createText(t){return this.delegate.createText(t)}get destroyNode(){return this.delegate.destroyNode}appendChild(t,e){this.delegate.appendChild(t,e)}insertBefore(t,e,i,a){this.delegate.insertBefore(t,e,i,a)}removeChild(t,e,i,a){this.delegate.removeChild(t,e,i,a)}selectRootElement(t,e){return this.delegate.selectRootElement(t,e)}parentNode(t){return this.delegate.parentNode(t)}nextSibling(t){return this.delegate.nextSibling(t)}setAttribute(t,e,i,a){this.delegate.setAttribute(t,e,i,a)}removeAttribute(t,e,i){this.delegate.removeAttribute(t,e,i)}addClass(t,e){this.delegate.addClass(t,e)}removeClass(t,e){this.delegate.removeClass(t,e)}setStyle(t,e,i,a){this.delegate.setStyle(t,e,i,a)}removeStyle(t,e,i){this.delegate.removeStyle(t,e,i)}setProperty(t,e,i){this.shouldReplay(e)&&this.replay.push(a=>a.setProperty(t,e,i)),this.delegate.setProperty(t,e,i)}setValue(t,e){this.delegate.setValue(t,e)}listen(t,e,i,a){return this.shouldReplay(e)&&this.replay.push(r=>r.listen(t,e,i,a)),this.delegate.listen(t,e,i,a)}shouldReplay(t){return this.replay!==null&&t.startsWith(M0)}},I0=new I("");function tf(n="animations"){return mh("NgAsyncAnimations"),ma([{provide:xt,useFactory:()=>new N0(p(Y),p(Hr),p(W),n)},{provide:ua,useValue:n==="noop"?"NoopAnimations":"BrowserAnimations"}])}function A0(n,t){return new Nt(e=>{let i=!1,a=!1,r=n.subscribe(o=>{a=!0,setTimeout(()=>{e.next(o),i&&e.complete()},t)},o=>setTimeout(()=>e.error(o),t),()=>{i=!0,a||e.complete()});return()=>r.unsubscribe()})}var ge={CONTINUE:100,SWITCHING_PROTOCOLS:101,OK:200,CREATED:201,ACCEPTED:202,NON_AUTHORITATIVE_INFORMATION:203,NO_CONTENT:204,RESET_CONTENT:205,PARTIAL_CONTENT:206,MULTIPLE_CHOICES:300,MOVED_PERMANTENTLY:301,FOUND:302,SEE_OTHER:303,NOT_MODIFIED:304,USE_PROXY:305,TEMPORARY_REDIRECT:307,BAD_REQUEST:400,UNAUTHORIZED:401,PAYMENT_REQUIRED:402,FORBIDDEN:403,NOT_FOUND:404,METHOD_NOT_ALLOWED:405,NOT_ACCEPTABLE:406,PROXY_AUTHENTICATION_REQUIRED:407,REQUEST_TIMEOUT:408,CONFLICT:409,GONE:410,LENGTH_REQUIRED:411,PRECONDITION_FAILED:412,PAYLOAD_TO_LARGE:413,URI_TOO_LONG:414,UNSUPPORTED_MEDIA_TYPE:415,RANGE_NOT_SATISFIABLE:416,EXPECTATION_FAILED:417,IM_A_TEAPOT:418,UPGRADE_REQUIRED:426,INTERNAL_SERVER_ERROR:500,NOT_IMPLEMENTED:501,BAD_GATEWAY:502,SERVICE_UNAVAILABLE:503,GATEWAY_TIMEOUT:504,HTTP_VERSION_NOT_SUPPORTED:505,PROCESSING:102,MULTI_STATUS:207,IM_USED:226,PERMANENT_REDIRECT:308,UNPROCESSABLE_ENTRY:422,LOCKED:423,FAILED_DEPENDENCY:424,PRECONDITION_REQUIRED:428,TOO_MANY_REQUESTS:429,REQUEST_HEADER_FIELDS_TOO_LARGE:431,UNAVAILABLE_FOR_LEGAL_REASONS:451,VARIANT_ALSO_NEGOTIATES:506,INSUFFICIENT_STORAGE:507,NETWORK_AUTHENTICATION_REQUIRED:511},T0={100:{code:100,text:"Continue",description:'"The initial part of a request has been received and has not yet been rejected by the server."',spec_title:"RFC7231#6.2.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.2.1"},101:{code:101,text:"Switching Protocols",description:`"The server understands and is willing to comply with the client's request, via the Upgrade header field, for a change in the application protocol being used on this connection."`,spec_title:"RFC7231#6.2.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.2.2"},200:{code:200,text:"OK",description:'"The request has succeeded."',spec_title:"RFC7231#6.3.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.1"},201:{code:201,text:"Created",description:'"The request has been fulfilled and has resulted in one or more new resources being created."',spec_title:"RFC7231#6.3.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.2"},202:{code:202,text:"Accepted",description:'"The request has been accepted for processing, but the processing has not been completed."',spec_title:"RFC7231#6.3.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.3"},203:{code:203,text:"Non-Authoritative Information",description:`"The request was successful but the enclosed payload has been modified from that of the origin server's 200 (OK) response by a transforming proxy."`,spec_title:"RFC7231#6.3.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.4"},204:{code:204,text:"No Content",description:'"The server has successfully fulfilled the request and that there is no additional content to send in the response payload body."',spec_title:"RFC7231#6.3.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.5"},205:{code:205,text:"Reset Content",description:'"The server has fulfilled the request and desires that the user agent reset the "document view", which caused the request to be sent, to its original state as received from the origin server."',spec_title:"RFC7231#6.3.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.6"},206:{code:206,text:"Partial Content",description:`"The server is successfully fulfilling a range request for the target resource by transferring one or more parts of the selected representation that correspond to the satisfiable ranges found in the requests's Range header field."`,spec_title:"RFC7233#4.1",spec_href:"https://tools.ietf.org/html/rfc7233#section-4.1"},300:{code:300,text:"Multiple Choices",description:'"The target resource has more than one representation, each with its own more specific identifier, and information about the alternatives is being provided so that the user (or user agent) can select a preferred representation by redirecting its request to one or more of those identifiers."',spec_title:"RFC7231#6.4.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.1"},301:{code:301,text:"Moved Permanently",description:'"The target resource has been assigned a new permanent URI and any future references to this resource ought to use one of the enclosed URIs."',spec_title:"RFC7231#6.4.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.2"},302:{code:302,text:"Found",description:'"The target resource resides temporarily under a different URI."',spec_title:"RFC7231#6.4.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.3"},303:{code:303,text:"See Other",description:'"The server is redirecting the user agent to a different resource, as indicated by a URI in the Location header field, that is intended to provide an indirect response to the original request."',spec_title:"RFC7231#6.4.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.4"},304:{code:304,text:"Not Modified",description:'"A conditional GET request has been received and would have resulted in a 200 (OK) response if it were not for the fact that the condition has evaluated to false."',spec_title:"RFC7232#4.1",spec_href:"https://tools.ietf.org/html/rfc7232#section-4.1"},305:{code:305,text:"Use Proxy",description:"*deprecated*",spec_title:"RFC7231#6.4.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.5"},307:{code:307,text:"Temporary Redirect",description:'"The target resource resides temporarily under a different URI and the user agent MUST NOT change the request method if it performs an automatic redirection to that URI."',spec_title:"RFC7231#6.4.7",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.7"},400:{code:400,text:"Bad Request",description:'"The server cannot or will not process the request because the received syntax is invalid, nonsensical, or exceeds some limitation on what the server is willing to process."',spec_title:"RFC7231#6.5.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.1"},401:{code:401,text:"Unauthorized",description:'"The request has not been applied because it lacks valid authentication credentials for the target resource."',spec_title:"RFC7235#6.3.1",spec_href:"https://tools.ietf.org/html/rfc7235#section-3.1"},402:{code:402,text:"Payment Required",description:"*reserved*",spec_title:"RFC7231#6.5.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.2"},403:{code:403,text:"Forbidden",description:'"The server understood the request but refuses to authorize it."',spec_title:"RFC7231#6.5.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.3"},404:{code:404,text:"Not Found",description:'"The origin server did not find a current representation for the target resource or is not willing to disclose that one exists."',spec_title:"RFC7231#6.5.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.4"},405:{code:405,text:"Method Not Allowed",description:'"The method specified in the request-line is known by the origin server but not supported by the target resource."',spec_title:"RFC7231#6.5.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.5"},406:{code:406,text:"Not Acceptable",description:'"The target resource does not have a current representation that would be acceptable to the user agent, according to the proactive negotiation header fields received in the request, and the server is unwilling to supply a default representation."',spec_title:"RFC7231#6.5.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.6"},407:{code:407,text:"Proxy Authentication Required",description:'"The client needs to authenticate itself in order to use a proxy."',spec_title:"RFC7231#6.3.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.2"},408:{code:408,text:"Request Timeout",description:'"The server did not receive a complete request message within the time that it was prepared to wait."',spec_title:"RFC7231#6.5.7",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.7"},409:{code:409,text:"Conflict",description:'"The request could not be completed due to a conflict with the current state of the resource."',spec_title:"RFC7231#6.5.8",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.8"},410:{code:410,text:"Gone",description:'"Access to the target resource is no longer available at the origin server and that this condition is likely to be permanent."',spec_title:"RFC7231#6.5.9",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.9"},411:{code:411,text:"Length Required",description:'"The server refuses to accept the request without a defined Content-Length."',spec_title:"RFC7231#6.5.10",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.10"},412:{code:412,text:"Precondition Failed",description:'"One or more preconditions given in the request header fields evaluated to false when tested on the server."',spec_title:"RFC7232#4.2",spec_href:"https://tools.ietf.org/html/rfc7232#section-4.2"},413:{code:413,text:"Payload Too Large",description:'"The server is refusing to process a request because the request payload is larger than the server is willing or able to process."',spec_title:"RFC7231#6.5.11",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.11"},414:{code:414,text:"URI Too Long",description:'"The server is refusing to service the request because the request-target is longer than the server is willing to interpret."',spec_title:"RFC7231#6.5.12",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.12"},415:{code:415,text:"Unsupported Media Type",description:'"The origin server is refusing to service the request because the payload is in a format not supported by the target resource for this method."',spec_title:"RFC7231#6.5.13",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.13"},416:{code:416,text:"Range Not Satisfiable",description:`"None of the ranges in the request's Range header field overlap the current extent of the selected resource or that the set of ranges requested has been rejected due to invalid ranges or an excessive request of small or overlapping ranges."`,spec_title:"RFC7233#4.4",spec_href:"https://tools.ietf.org/html/rfc7233#section-4.4"},417:{code:417,text:"Expectation Failed",description:`"The expectation given in the request's Expect header field could not be met by at least one of the inbound servers."`,spec_title:"RFC7231#6.5.14",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.14"},418:{code:418,text:"I'm a teapot",description:'"1988 April Fools Joke. Returned by tea pots requested to brew coffee."',spec_title:"RFC 2324",spec_href:"https://tools.ietf.org/html/rfc2324"},426:{code:426,text:"Upgrade Required",description:'"The server refuses to perform the request using the current protocol but might be willing to do so after the client upgrades to a different protocol."',spec_title:"RFC7231#6.5.15",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.15"},500:{code:500,text:"Internal Server Error",description:'"The server encountered an unexpected condition that prevented it from fulfilling the request."',spec_title:"RFC7231#6.6.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.1"},501:{code:501,text:"Not Implemented",description:'"The server does not support the functionality required to fulfill the request."',spec_title:"RFC7231#6.6.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.2"},502:{code:502,text:"Bad Gateway",description:'"The server, while acting as a gateway or proxy, received an invalid response from an inbound server it accessed while attempting to fulfill the request."',spec_title:"RFC7231#6.6.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.3"},503:{code:503,text:"Service Unavailable",description:'"The server is currently unable to handle the request due to a temporary overload or scheduled maintenance, which will likely be alleviated after some delay."',spec_title:"RFC7231#6.6.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.4"},504:{code:504,text:"Gateway Time-out",description:'"The server, while acting as a gateway or proxy, did not receive a timely response from an upstream server it needed to access in order to complete the request."',spec_title:"RFC7231#6.6.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.5"},505:{code:505,text:"HTTP Version Not Supported",description:'"The server does not support, or refuses to support, the protocol version that was used in the request message."',spec_title:"RFC7231#6.6.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.6"},102:{code:102,text:"Processing",description:'"An interim response to inform the client that the server has accepted the complete request, but has not yet completed it."',spec_title:"RFC5218#10.1",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.1"},207:{code:207,text:"Multi-Status",description:'"Status for multiple independent operations."',spec_title:"RFC5218#10.2",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.2"},226:{code:226,text:"IM Used",description:'"The server has fulfilled a GET request for the resource, and the response is a representation of the result of one or more instance-manipulations applied to the current instance."',spec_title:"RFC3229#10.4.1",spec_href:"https://tools.ietf.org/html/rfc3229#section-10.4.1"},308:{code:308,text:"Permanent Redirect",description:'"The target resource has been assigned a new permanent URI and any future references to this resource SHOULD use one of the returned URIs. [...] This status code is similar to 301 Moved Permanently (Section 7.3.2 of rfc7231), except that it does not allow rewriting the request method from POST to GET."',spec_title:"RFC7238",spec_href:"https://tools.ietf.org/html/rfc7238"},422:{code:422,text:"Unprocessable Entity",description:'"The server understands the content type of the request entity (hence a 415(Unsupported Media Type) status code is inappropriate), and the syntax of the request entity is correct (thus a 400 (Bad Request) status code is inappropriate) but was unable to process the contained instructions."',spec_title:"RFC5218#10.3",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.3"},423:{code:423,text:"Locked",description:'"The source or destination resource of a method is locked."',spec_title:"RFC5218#10.4",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.4"},424:{code:424,text:"Failed Dependency",description:'"The method could not be performed on the resource because the requested action depended on another action and that action failed."',spec_title:"RFC5218#10.5",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.5"},428:{code:428,text:"Precondition Required",description:'"The origin server requires the request to be conditional."',spec_title:"RFC6585#3",spec_href:"https://tools.ietf.org/html/rfc6585#section-3"},429:{code:429,text:"Too Many Requests",description:'"The user has sent too many requests in a given amount of time ("rate limiting")."',spec_title:"RFC6585#4",spec_href:"https://tools.ietf.org/html/rfc6585#section-4"},431:{code:431,text:"Request Header Fields Too Large",description:'"The server is unwilling to process the request because its header fields are too large."',spec_title:"RFC6585#5",spec_href:"https://tools.ietf.org/html/rfc6585#section-5"},451:{code:451,text:"Unavailable For Legal Reasons",description:'"The server is denying access to the resource in response to a legal demand."',spec_title:"draft-ietf-httpbis-legally-restricted-status",spec_href:"https://tools.ietf.org/html/draft-ietf-httpbis-legally-restricted-status"},506:{code:506,text:"Variant Also Negotiates",description:'"The server has an internal configuration error: the chosen variant resource is configured to engage in transparent content negotiation itself, and is therefore not a proper end point in the negotiation process."',spec_title:"RFC2295#8.1",spec_href:"https://tools.ietf.org/html/rfc2295#section-8.1"},507:{code:507,text:"Insufficient Storage",description:'The method could not be performed on the resource because the server is unable to store the representation needed to successfully complete the request."',spec_title:"RFC5218#10.6",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.6"},511:{code:511,text:"Network Authentication Required",description:'"The client needs to authenticate to gain network access."',spec_title:"RFC6585#6",spec_href:"https://tools.ietf.org/html/rfc6585#section-6"}};function O0(n){return T0[n+""].text||"Unknown Status"}function R0(n){return n>=200&&n<300}var Kr=class{},hd=class{apiBase;caseSensitiveSearch;dataEncapsulation;delay;delete404;host;passThruUnknownUrl;post204;post409;put204;put404;rootPath},Bs=(()=>{class n{constructor(e={}){Object.assign(this,{caseSensitiveSearch:!1,dataEncapsulation:!1,delay:500,delete404:!1,passThruUnknownUrl:!1,post204:!0,post409:!1,put204:!0,put404:!1,apiBase:void 0,host:void 0,rootPath:void 0},e)}static \u0275fac=function(i){return new(i||n)(B(hd))};static \u0275prov=te({token:n,factory:n.\u0275fac})}return n})();function P0(n){let e=/^(?:(?![^:@]+:[^:@\/]*@)([^:\/?#.]+):)?(?:\/\/)?((?:(([^:@]*)(?::([^:@]*))?)?@)?([^:\/?#]*)(?::(\d*))?)(((\/(?:[^?#](?![^?#\/]*\.[^?#\/.]+(?:[?#]|$)))*\/?)?([^?#\/]*))(?:\?([^#]*))?(?:#(.*))?)/.exec(n),i={source:"",protocol:"",authority:"",userInfo:"",user:"",password:"",host:"",port:"",relative:"",path:"",directory:"",file:"",query:"",anchor:""},a=Object.keys(i),r=a.length;for(;r--;)i[a[r]]=e&&e[r]||"";return i}function F0(n){return n.replace(/\/$/,"")}var fd=class{inMemDbService;config=new Bs;db={};dbReadySubject;passThruBackend;requestInfoUtils=this.getRequestInfoUtils();constructor(t,e={}){this.inMemDbService=t;let i=this.getLocation("/");this.config.host=i.host,this.config.rootPath=i.path,Object.assign(this.config,e)}get dbReady(){return this.dbReadySubject||(this.dbReadySubject=new Jn(!1),this.resetDb()),this.dbReadySubject.asObservable().pipe(cs(t=>t))}handleRequest(t){return this.dbReady.pipe(yr(()=>this.handleRequest_(t)))}handleRequest_(t){let e=t.urlWithParams?t.urlWithParams:t.url,i=this.bind("parseRequestUrl"),a=i&&i(e,this.requestInfoUtils)||this.parseRequestUrl(e),r=a.collectionName,o=this.db[r],s={req:t,apiBase:a.apiBase,collection:o,collectionName:r,headers:this.createHeaders({"Content-Type":"application/json"}),id:this.parseId(o,r,a.id),method:this.getRequestMethod(t),query:a.query,resourceUrl:a.resourceUrl,url:e,utils:this.requestInfoUtils},l;if(/commands\/?$/i.test(s.apiBase))return this.commands(s);let c=this.bind(s.method);if(c){let d=c(s);if(d)return d}return this.db[r]?this.createResponse$(()=>this.collectionHandler(s)):this.config.passThruUnknownUrl?this.getPassThruBackend().handle(t):(l=this.createErrorResponseOptions(e,ge.NOT_FOUND,`Collection '${r}' not found`),this.createResponse$(()=>l))}addDelay(t){let e=this.config.delay;return e===0?t:A0(t,e||500)}applyQuery(t,e){let i=[],a=this.config.caseSensitiveSearch?void 0:"i";e.forEach((o,s)=>{o.forEach(l=>i.push({name:s,rx:new RegExp(decodeURI(l),a)}))});let r=i.length;return r?t.filter(o=>{let s=!0,l=r;for(;s&&l;){l-=1;let c=i[l];s=c.rx.test(o[c.name])}return s}):t}bind(t){let e=this.inMemDbService[t];return e?e.bind(this.inMemDbService):void 0}bodify(t){return this.config.dataEncapsulation?{data:t}:t}clone(t){return JSON.parse(JSON.stringify(t))}collectionHandler(t){let e;switch(t.method){case"get":e=this.get(t);break;case"post":e=this.post(t);break;case"put":e=this.put(t);break;case"delete":e=this.delete(t);break;default:e=this.createErrorResponseOptions(t.url,ge.METHOD_NOT_ALLOWED,"Method not allowed");break}let i=this.bind("responseInterceptor");return i?i(e,t):e}commands(t){let e=t.collectionName.toLowerCase(),i=t.method,a={url:t.url};switch(e){case"resetdb":return a.status=ge.NO_CONTENT,this.resetDb(t).pipe(yr(()=>this.createResponse$(()=>a,!1)));case"config":if(i==="get")a.status=ge.OK,a.body=this.clone(this.config);else{let r=this.getJsonBody(t.req);Object.assign(this.config,r),this.passThruBackend=void 0,a.status=ge.NO_CONTENT}break;default:a=this.createErrorResponseOptions(t.url,ge.INTERNAL_SERVER_ERROR,`Unknown command "${e}"`)}return this.createResponse$(()=>a,!1)}createErrorResponseOptions(t,e,i){return{body:{error:`${i}`},url:t,headers:this.createHeaders({"Content-Type":"application/json"}),status:e}}createResponse$(t,e=!0){let i=this.createResponseOptions$(t),a=this.createResponse$fromResponseOptions$(i);return e?this.addDelay(a):a}createResponseOptions$(t){return new Nt(e=>{let i;try{i=t()}catch(r){let o=r.message||r;i=this.createErrorResponseOptions("",ge.INTERNAL_SERVER_ERROR,`${o}`)}let a=i.status;try{i.statusText=a!=null?O0(a):void 0}catch{}return a!=null&&R0(a)?(e.next(i),e.complete()):e.error(i),()=>{}})}delete({collection:t,collectionName:e,headers:i,id:a,url:r}){if(a==null)return this.createErrorResponseOptions(r,ge.NOT_FOUND,`Missing "${e}" id`);let o=this.removeById(t,a);return{headers:i,status:o||!this.config.delete404?ge.NO_CONTENT:ge.NOT_FOUND}}findById(t,e){return t.find(i=>i.id===e)}genId(t,e){let i=this.bind("genId");if(i){let a=i(t,e);if(a!=null)return a}return this.genIdDefault(t,e)}genIdDefault(t,e){if(!this.isCollectionIdNumeric(t,e))throw new Error(`Collection '${e}' id type is non-numeric or unknown. Can only generate numeric ids.`);let i=0;return t.reduce((a,r)=>{i=Math.max(i,typeof r.id=="number"?r.id:i)},void 0),i+1}get({collection:t,collectionName:e,headers:i,id:a,query:r,url:o}){let s=t;return a!=null&&a!==""?s=this.findById(t,a):r&&(s=this.applyQuery(t,r)),s?{body:this.bodify(this.clone(s)),headers:i,status:ge.OK}:this.createErrorResponseOptions(o,ge.NOT_FOUND,`'${e}' with id='${a}' not found`)}getLocation(t){if(!t.startsWith("http")){let e=typeof document>"u"?void 0:document,i=e?e.location.protocol+"//"+e.location.host:"http://fake";t=t.startsWith("/")?i+t:i+"/"+t}return P0(t)}getPassThruBackend(){return this.passThruBackend?this.passThruBackend:this.passThruBackend=this.createPassThruBackend()}getRequestInfoUtils(){return{createResponse$:this.createResponse$.bind(this),findById:this.findById.bind(this),isCollectionIdNumeric:this.isCollectionIdNumeric.bind(this),getConfig:()=>this.config,getDb:()=>this.db,getJsonBody:this.getJsonBody.bind(this),getLocation:this.getLocation.bind(this),getPassThruBackend:this.getPassThruBackend.bind(this),parseRequestUrl:this.parseRequestUrl.bind(this)}}indexOf(t,e){return t.findIndex(i=>i.id===e)}parseId(t,e,i){if(!this.isCollectionIdNumeric(t,e))return i;let a=parseFloat(i);return isNaN(a)?i:a}isCollectionIdNumeric(t,e){return!!(t&&t[0])&&typeof t[0].id=="number"}parseRequestUrl(t){try{let e=this.getLocation(t),i=(this.config.rootPath||"").length,a="";e.host!==this.config.host&&(i=1,a=e.protocol+"//"+e.host+"/");let o=e.path.substring(i).split("/"),s=0,l;this.config.apiBase==null?l=o[s++]:(l=F0(this.config.apiBase.trim()),l?s=l.split("/").length:s=0),l+="/";let c=o[s++];c=c&&c.split(".")[0];let d=o[s++],m=this.createQueryMap(e.query),u=a+l+c+"/";return{apiBase:l,collectionName:c,id:d,query:m,resourceUrl:u}}catch(e){let i=`unable to parse url '${t}'; original error: ${e.message}`;throw new Error(i)}}post({collection:t,collectionName:e,headers:i,id:a,req:r,resourceUrl:o,url:s}){let l=this.clone(this.getJsonBody(r));if(l.id==null)try{l.id=a||this.genId(t,e)}catch(m){let u=m.message||"";return/id type is non-numeric/.test(u)?this.createErrorResponseOptions(s,ge.UNPROCESSABLE_ENTRY,u):this.createErrorResponseOptions(s,ge.INTERNAL_SERVER_ERROR,`Failed to generate new id for '${e}'`)}if(a&&a!==l.id)return this.createErrorResponseOptions(s,ge.BAD_REQUEST,"Request id does not match item.id");a=l.id;let c=this.indexOf(t,a),d=this.bodify(l);return c===-1?(t.push(l),i.set("Location",o+"/"+a),{headers:i,body:d,status:ge.CREATED}):this.config.post409?this.createErrorResponseOptions(s,ge.CONFLICT,`'${e}' item with id='${a} exists and may not be updated with POST; use PUT instead.`):(t[c]=l,this.config.post204?{headers:i,status:ge.NO_CONTENT}:{headers:i,body:d,status:ge.OK})}put({collection:t,collectionName:e,headers:i,id:a,req:r,url:o}){let s=this.clone(this.getJsonBody(r));if(s.id==null)return this.createErrorResponseOptions(o,ge.NOT_FOUND,`Missing '${e}' id`);if(a&&a!==s.id)return this.createErrorResponseOptions(o,ge.BAD_REQUEST,`Request for '${e}' id does not match item.id`);a=s.id;let l=this.indexOf(t,a),c=this.bodify(s);return l>-1?(t[l]=s,this.config.put204?{headers:i,status:ge.NO_CONTENT}:{headers:i,body:c,status:ge.OK}):this.config.put404?this.createErrorResponseOptions(o,ge.NOT_FOUND,`'${e}' item with id='${a} not found and may not be created with PUT; use POST instead.`):(t.push(s),{headers:i,body:c,status:ge.CREATED})}removeById(t,e){let i=this.indexOf(t,e);return i>-1?(t.splice(i,1),!0):!1}resetDb(t){this.dbReadySubject&&this.dbReadySubject.next(!1);let e=this.inMemDbService.createDb(t);return(e instanceof Nt?e:typeof e.then=="function"?qu(e):Ue(e)).pipe(cs()).subscribe(a=>{this.db=a,this.dbReadySubject&&this.dbReadySubject.next(!0)}),this.dbReady}},L0=(()=>{class n extends fd{xhrFactory;injector=p(ce);constructor(e,i,a){super(e,i),this.xhrFactory=a}handle(e){try{return this.handleRequest(e)}catch(i){let a=i.message||i,r=this.createErrorResponseOptions(e.url,ge.INTERNAL_SERVER_ERROR,`${a}`);return this.createResponse$(()=>r)}}getJsonBody(e){return e.body}getRequestMethod(e){return(e.method||"get").toLowerCase()}createHeaders(e){return new Yt(e)}createQueryMap(e){let i=new Map;if(e){let a=new qt({fromString:e});a.keys().forEach(r=>i.set(r,a.getAll(r)||[]))}return i}createResponse$fromResponseOptions$(e){return e.pipe(De(i=>new di(i)))}createPassThruBackend(){try{return ps(this.injector,()=>new cd(this.xhrFactory))}catch(e){throw e.message="Cannot create passThru404 backend; "+(e.message||""),e}}static \u0275fac=function(i){return new(i||n)(B(Kr),B(Bs,8),B(Ai))};static \u0275prov=te({token:n,factory:n.\u0275fac})}return n})();function B0(){return new L0(p(Kr),p(Bs),p(Ai))}var nf=(()=>{class n{static forRoot(e,i){return{ngModule:n,providers:[{provide:Kr,useClass:e},{provide:Bs,useValue:i},{provide:Wr,useFactory:B0}]}}static forFeature(e,i){return n.forRoot(e,i)}static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({})}return n})();var af={asyncapi:"3.1.0",info:{title:"Springwolf example project - AMQP",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-amqp-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com","x-phone":"+49 123 456789"},license:{name:"Apache License 2.0","x-desc":"some description"},"x-api-audience":"company-internal","x-generator":"springwolf"},defaultContentType:"application/json",servers:{"amqp-server":{host:"amqp:5672",protocol:"amqp"}},channels:{"CRUD-topic-exchange-1":{address:"CRUD-topic-exchange-1",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"CRUD-topic-exchange-1",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"CRUD-topic-exchange-2":{address:"CRUD-topic-exchange-2",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"CRUD-topic-exchange-2",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"another-queue",durable:!0,exclusive:!1,autoDelete:!0,vhost:"/"},bindingVersion:"0.3.0"}}},"example-bindings-queue":{address:"example-bindings-queue",bindings:{amqp:{is:"queue",queue:{name:"example-bindings-queue",durable:!0,exclusive:!1,autoDelete:!0,vhost:"/"},bindingVersion:"0.3.0"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"example-queue",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"example-topic-exchange":{address:"example-topic-exchange",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},bindings:{}},"example-topic-exchange_example-topic-routing-key":{address:"example-topic-exchange_example-topic-routing-key",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"example-topic-exchange",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"multi-payload-queue":{address:"multi-payload-queue",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"multi-payload-queue",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-create":{address:"queue-create",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}},bindings:{amqp:{is:"queue",queue:{name:"queue-create",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-delete":{address:"queue-delete",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}},bindings:{amqp:{is:"queue",queue:{name:"queue-delete",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-read":{address:"queue-read",bindings:{amqp:{is:"queue",queue:{name:"queue-read",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-update":{address:"queue-update",bindings:{amqp:{is:"queue",queue:{name:"queue-update",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},SpringRabbitListenerDefaultHeaders:{title:"SpringRabbitListenerDefaultHeaders",type:"object",properties:{},examples:[{}]},"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},description:"Generic payload model",examples:[{genericValue:{someEnum:"FOO2",someLong:5,someString:"some string value"}}],required:["genericValue"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{type:"integer",description:"Generic Payload field",format:"int64"}},description:"Generic payload model",examples:[{genericValue:0}],required:["genericValue"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{type:"string",description:"Generic Payload field"}},description:"Generic payload model",examples:[{genericValue:"string"}],required:["genericValue"]}},messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto",title:"GenericPayloadDtoExamplePayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long",title:"GenericPayloadDtoLong",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String",title:"GenericPayloadDtoString",bindings:{amqp:{bindingVersion:"0.3.0"}}}}},operations:{"CRUD-topic-exchange-1_receive_bindingsUpdate":{action:"receive",channel:{$ref:"#/channels/CRUD-topic-exchange-1"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/CRUD-topic-exchange-1/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"CRUD-topic-exchange-2_receive_bindingsRead":{action:"receive",channel:{$ref:"#/channels/CRUD-topic-exchange-2"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/CRUD-topic-exchange-2/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"example-topic-exchange_example-topic-routing-key_receive_bindingsExample":{action:"receive",channel:{$ref:"#/channels/example-topic-exchange_example-topic-routing-key"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-topic-exchange_example-topic-routing-key/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"example-topic-exchange_send_sendMessage":{action:"send",channel:{$ref:"#/channels/example-topic-exchange"},title:"example-topic-exchange_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{amqp:{expiration:0,cc:[],priority:0,deliveryMode:1,mandatory:!1,bcc:[],timestamp:!1,ack:!1,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-topic-exchange/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}]},"multi-payload-queue_receive_bindingsBeanExample":{action:"receive",channel:{$ref:"#/channels/multi-payload-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/multi-payload-queue/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"},{$ref:"#/channels/multi-payload-queue/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"queue-create_receive_queuesToDeclareCreate":{action:"receive",channel:{$ref:"#/channels/queue-create"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/queue-create/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}]},"queue-delete_receive_queuesToDeclareDelete":{action:"receive",channel:{$ref:"#/channels/queue-delete"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/queue-delete/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}]}}};var rf={asyncapi:"3.1.0",info:{title:"Springwolf example project - Cloud Stream",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-cloud-stream-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"another-topic":{address:"another-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"biconsumer-topic":{address:"biconsumer-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"bifunction-output-topic":{address:"bifunction-output-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"bifunction-topic":{address:"bifunction-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"consumer-class-topic":{address:"consumer-class-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"consumer-topic":{address:"consumer-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"example-topic":{address:"example-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"google-pubsub-topic":{address:"google-pubsub-topic",messages:{GooglePubSubPayloadDto:{$ref:"#/components/messages/GooglePubSubPayloadDto"}},bindings:{googlepubsub:{messageStoragePolicy:{},schemaSettings:{encoding:"BINARY",name:"project/test"},bindingVersion:"0.2.0"}}}},components:{schemas:{AnotherPayloadDto:{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},ExamplePayloadDto:{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]},GooglePubSubPayloadDto:{title:"GooglePubSubPayloadDto",type:"object",properties:{someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Google pubsub payload model",examples:[{someLong:5,someString:"some string value"}],required:["someString"]},HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]}},messages:{AnotherPayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/AnotherPayloadDto"}},name:"AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{kafka:{}}},ExamplePayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/ExamplePayloadDto"}},name:"ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{kafka:{}}},GooglePubSubPayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/GooglePubSubPayloadDto"}},name:"GooglePubSubPayloadDto",title:"GooglePubSubPayloadDto",bindings:{googlepubsub:{schema:{name:"project/test"},bindingVersion:"0.2.0"}}}}},operations:{"another-topic_send_process":{action:"send",channel:{$ref:"#/channels/another-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/another-topic/messages/AnotherPayloadDto"}]},"biconsumer-topic_receive_biConsumerMethod":{action:"receive",channel:{$ref:"#/channels/biconsumer-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/biconsumer-topic/messages/AnotherPayloadDto"}]},"bifunction-output-topic_send_biProcess":{action:"send",channel:{$ref:"#/channels/bifunction-output-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/bifunction-output-topic/messages/AnotherPayloadDto"}]},"bifunction-topic_receive_biProcess":{action:"receive",channel:{$ref:"#/channels/bifunction-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/bifunction-topic/messages/ExamplePayloadDto"}]},"consumer-class-topic_receive_ConsumerClass":{action:"receive",channel:{$ref:"#/channels/consumer-class-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/consumer-class-topic/messages/ExamplePayloadDto"}]},"consumer-topic_receive_consumerMethod":{action:"receive",channel:{$ref:"#/channels/consumer-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/consumer-topic/messages/AnotherPayloadDto"}]},"example-topic_receive_process":{action:"receive",channel:{$ref:"#/channels/example-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/example-topic/messages/ExamplePayloadDto"}]},"google-pubsub-topic_receive_googlePubSubConsumerMethod":{action:"receive",channel:{$ref:"#/channels/google-pubsub-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/google-pubsub-topic/messages/GooglePubSubPayloadDto"}]}}};var of={asyncapi:"3.1.0",info:{title:"Springwolf example project - Kafka",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-kafka-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"another-topic":{address:"another-topic",messages:{"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"avro-topic":{address:"avro-topic",messages:{"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"example-topic":{address:"example-topic",messages:{"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"integer-topic":{address:"integer-topic",messages:{"java.lang.Integer":{$ref:"#/components/messages/java.lang.Integer"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"multi-payload-topic":{address:"multi-payload-topic",messages:{"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"},"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},"javax.money.MonetaryAmount":{$ref:"#/components/messages/javax.money.MonetaryAmount"}},bindings:{kafka:{topic:"multi-payload-topic",partitions:3,replicas:1,topicConfiguration:{"cleanup.policy":["compact","delete"],"retention.ms":864e5,"retention.bytes":-1,"delete.retention.ms":864e5,"max.message.bytes":1048588},bindingVersion:"0.5.0"}}},"no-payload-used-topic":{address:"no-payload-used-topic",messages:{PayloadNotUsed:{$ref:"#/components/messages/PayloadNotUsed"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"nullable-topic":{address:"nullable-topic",messages:{"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"protobuf-topic":{address:"protobuf-topic",messages:{"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"string-topic":{address:"string-topic",messages:{"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"},"java.lang.String":{$ref:"#/components/messages/java.lang.String"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"topic-defined-via-asyncPublisher-annotation":{address:"topic-defined-via-asyncPublisher-annotation",messages:{"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}},servers:[{$ref:"#/servers/kafka-server"}],bindings:{}},"vehicle-topic":{address:"vehicle-topic",messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"xml-topic":{address:"xml-topic",messages:{"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"yaml-topic":{address:"yaml-topic",messages:{"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"There can be headers, but they are not explicitly documented.",title:"HeadersNotDocumented",type:"object"}},HeadersNotUsed:{title:"HeadersNotUsed",type:"object",properties:{},description:"No headers are present.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"No headers are present.",title:"HeadersNotUsed",type:"object"}},PayloadNotUsed:{title:"PayloadNotUsed",type:"object",properties:{},description:"No payload specified",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"No payload specified",title:"PayloadNotUsed",type:"object"}},SpringDefaultHeaderAndCloudEvent:{title:"SpringDefaultHeaderAndCloudEvent",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"},ce_id:{title:"ce_id",type:"string",description:"CloudEvent Id Header",examples:["2c60089e-6f39-459d-8ced-2d6df7e4c03a"],const:"2c60089e-6f39-459d-8ced-2d6df7e4c03a"},ce_source:{title:"ce_source",type:"string",description:"CloudEvent Source Header",examples:["http://localhost"],const:"http://localhost"},ce_specversion:{title:"ce_specversion",type:"string",description:"CloudEvent Spec Version Header",examples:["1.0"],const:"1.0"},ce_subject:{title:"ce_subject",type:"string",description:"CloudEvent Subject Header",examples:["Springwolf example project - Kafka"],const:"Springwolf example project - Kafka"},ce_time:{title:"ce_time",type:"string",description:"CloudEvent Time Header",format:"date-time",examples:["2023-10-28T20:01:23+00:00"],const:"2023-10-28T20:01:23+00:00"},ce_type:{title:"ce_type",type:"string",description:"CloudEvent Payload Type Header",examples:["NestedPayloadDto.v1"],const:"NestedPayloadDto.v1"},"content-type":{title:"content-type",type:"string",description:"CloudEvent Content-Type Header",examples:["application/json"],const:"application/json"}},description:"Spring __TypeId__ and CloudEvent Headers",examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto",ce_id:"2c60089e-6f39-459d-8ced-2d6df7e4c03a",ce_source:"http://localhost",ce_specversion:"1.0",ce_subject:"Springwolf example project - Kafka",ce_time:"2023-10-28T20:01:23+00:00",ce_type:"NestedPayloadDto.v1","content-type":"application/json"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Spring __TypeId__ and CloudEvent Headers",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"},ce_id:{const:"2c60089e-6f39-459d-8ced-2d6df7e4c03a",description:"CloudEvent Id Header",title:"ce_id",type:"string"},ce_source:{const:"http://localhost",description:"CloudEvent Source Header",title:"ce_source",type:"string"},ce_specversion:{const:"1.0",description:"CloudEvent Spec Version Header",title:"ce_specversion",type:"string"},ce_subject:{const:"Springwolf example project - Kafka",description:"CloudEvent Subject Header",title:"ce_subject",type:"string"},ce_time:{const:"2023-10-28T20:01:23+00:00",description:"CloudEvent Time Header",format:"date-time",title:"ce_time",type:"string"},ce_type:{const:"NestedPayloadDto.v1",description:"CloudEvent Payload Type Header",title:"ce_type",type:"string"},"content-type":{const:"application/json",description:"CloudEvent Content-Type Header",title:"content-type",type:"string"}},title:"SpringDefaultHeaderAndCloudEvent",type:"object"}},"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto":{title:"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"],const:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto",type:"object"}},"SpringKafkaDefaultHeaders-AnotherPayloadDto":{title:"SpringKafkaDefaultHeaders-AnotherPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-AnotherTopic":{title:"SpringKafkaDefaultHeaders-AnotherTopic",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Type ID"},my_uuid_field:{title:"my_uuid_field",type:"string",description:"Event identifier",format:"uuid",enum:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"],examples:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"]}},examples:[{__TypeId__:"string",my_uuid_field:"00000000-0000-0000-0000-000000000000"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Type ID",title:"__TypeId__",type:"string"},my_uuid_field:{description:"Event identifier",enum:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"],format:"uuid",title:"my_uuid_field",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherTopic",type:"object"}},"SpringKafkaDefaultHeaders-ExamplePayloadDto-1408272656":{title:"SpringKafkaDefaultHeaders-ExamplePayloadDto-1408272656",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},kafka_offset:{type:"integer",format:"int32",examples:[0]},kafka_receivedMessageKey:{type:"string",examples:['"string"']},kafka_recordMetadata:{title:"ConsumerRecordMetadata",type:"object",examples:[{}]}},examples:[{ConsumerRecordMetadata:{},__TypeId__:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto",kafka_offset:0,kafka_receivedMessageKey:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"},kafka_offset:{format:"int32",type:"integer"},kafka_receivedMessageKey:{type:"string"},kafka_recordMetadata:{title:"ConsumerRecordMetadata",type:"object"}},title:"SpringKafkaDefaultHeaders-ExamplePayloadDto-1408272656",type:"object"}},"SpringKafkaDefaultHeaders-Message":{title:"SpringKafkaDefaultHeaders-Message",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"],const:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-Message",type:"object"}},"SpringKafkaDefaultHeaders-MonetaryAmount":{title:"SpringKafkaDefaultHeaders-MonetaryAmount",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["javax.money.MonetaryAmount"],const:"javax.money.MonetaryAmount"}},examples:[{__TypeId__:"javax.money.MonetaryAmount"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"javax.money.MonetaryAmount",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-MonetaryAmount",type:"object"}},"SpringKafkaDefaultHeaders-PayloadNotUsed":{title:"SpringKafkaDefaultHeaders-PayloadNotUsed",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["PayloadNotUsed"],const:"PayloadNotUsed"}},examples:[{__TypeId__:"PayloadNotUsed"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"PayloadNotUsed",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-PayloadNotUsed",type:"object"}},"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto":{title:"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-VehicleBase":{title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],const:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object"}},"SpringKafkaDefaultHeaders-XmlPayloadDto":{title:"SpringKafkaDefaultHeaders-XmlPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-XmlPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-YamlPayloadDto":{title:"SpringKafkaDefaultHeaders-YamlPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-YamlPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-integer":{title:"SpringKafkaDefaultHeaders-integer",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["java.lang.Integer"],const:"java.lang.Integer"}},examples:[{__TypeId__:"java.lang.Integer"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"java.lang.Integer",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-integer",type:"object"}},"SpringKafkaDefaultHeaders-string":{title:"SpringKafkaDefaultHeaders-string",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["java.lang.String"],const:"java.lang.String"}},examples:[{__TypeId__:"java.lang.String"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"java.lang.String",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-string",type:"object"}},"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{type:"string",description:"Payload description using @Schema annotation and @AsyncApiPayload within envelope class",maxLength:100,examples:['"string"'],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Payload description using @Schema annotation and @AsyncApiPayload within envelope class",maxLength:100,type:"string"}},"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{title:"AnotherPayloadAvroDto",type:"object",properties:{examplePayloadAvroDto:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.avro.ExamplePayloadAvroDto"},someEnum:{title:"ExampleEnum",type:"string",enum:["FOO1","FOO2","FOO3"]}},examples:[{examplePayloadAvroDto:{someLong:0,someString:"string"},someEnum:"FOO1"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{examplePayloadAvroDto:{properties:{someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"ExamplePayloadAvroDto",type:"object"},someEnum:{enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"}},title:"AnotherPayloadAvroDto",type:"object"}},"io.github.springwolf.examples.kafka.dto.avro.ExamplePayloadAvroDto":{title:"ExamplePayloadAvroDto",type:"object",properties:{someLong:{type:"integer",format:"int64"},someString:{type:"string"}},examples:[{someLong:0,someString:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"ExamplePayloadAvroDto",type:"object"}},"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{title:"Message",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",enum:["FOO1","FOO2","FOO3","UNRECOGNIZED"]},someLong:{type:"integer",format:"int64"},someString:{type:"string"}},examples:[{someEnum:"FOO1",someLong:0,someString:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someEnum:{enum:["FOO1","FOO2","FOO3","UNRECOGNIZED"],title:"ExampleEnum",type:"string"},someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"Message",type:"object"}},"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Another payload model",properties:{example:{description:`Example payload model demonstrating markdown text styling:
**bold**, *cursive* and <u>underlined</u>
`,properties:{someEnum:{description:"Some enum field",enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{description:"Some long field",format:"int64",minimum:0,type:"integer"},someString:{description:`###  Some string field with Markdown

- **bold**
- *cursive*
- images: <img src="./assets/springwolf-logo.png" alt="Springwolf" height="50"/>
- and code blocks (json, http, java)
  \`\`\`json
  {
    "key1":"value1",
    "key2":"value2"
  }
  \`\`\`
`,type:"string"}},required:["someEnum","someString"],title:"ExamplePayloadDto",type:"object"},foo:{description:"Foo field",maxLength:100,type:"string"}},required:["example"],title:"AnotherPayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:`###  Some string field with Markdown

- **bold**
- *cursive*
- images: <img src="./assets/springwolf-logo.png" alt="Springwolf" height="50"/>
- and code blocks (json, http, java)
  \`\`\`json
  {
    "key1":"value1",
    "key2":"value2"
  }
  \`\`\`
`,examples:["some string value"]}},description:`Example payload model demonstrating markdown text styling:
**bold**, *cursive* and <u>underlined</u>
`,examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:`Example payload model demonstrating markdown text styling:
**bold**, *cursive* and <u>underlined</u>
`,properties:{someEnum:{description:"Some enum field",enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{description:"Some long field",format:"int64",minimum:0,type:"integer"},someString:{description:`###  Some string field with Markdown

- **bold**
- *cursive*
- images: <img src="./assets/springwolf-logo.png" alt="Springwolf" height="50"/>
- and code blocks (json, http, java)
  \`\`\`json
  {
    "key1":"value1",
    "key2":"value2"
  }
  \`\`\`
`,type:"string"}},required:["someEnum","someString"],title:"ExamplePayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto":{title:"NestedPayloadDto",type:"object",properties:{examplePayloads:{title:"List",type:"array",items:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}},someStrings:{title:"Set",type:"array",items:{type:"string",description:"Some string field",examples:["some string value"]},uniqueItems:!0}},description:"Payload model with nested complex types",examples:[{examplePayloads:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],someStrings:["some string value"]}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Payload model with nested complex types",properties:{examplePayloads:{items:{description:`Example payload model demonstrating markdown text styling:
**bold**, *cursive* and <u>underlined</u>
`,properties:{someEnum:{description:"Some enum field",enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{description:"Some long field",format:"int64",minimum:0,type:"integer"},someString:{description:`###  Some string field with Markdown

- **bold**
- *cursive*
- images: <img src="./assets/springwolf-logo.png" alt="Springwolf" height="50"/>
- and code blocks (json, http, java)
  \`\`\`json
  {
    "key1":"value1",
    "key2":"value2"
  }
  \`\`\`
`,type:"string"}},required:["someEnum","someString"],title:"ExamplePayloadDto",type:"object"},title:"List",type:"array"},someStrings:{items:{description:"Some string field",type:"string"},title:"Set",type:"array",uniqueItems:!0}},title:"NestedPayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto":{title:"RequiredAndNullablePayloadDto",type:"object",properties:{enumField:{title:"ComplexEnum",type:["string","null"],description:"Follows OpenAPI 3.1 spec",enum:["COMPLEX1","COMPLEX2",null]},notRequiredField:{type:"string",description:"This field can be skipped, but value cannot be null"},requiredAndNullableField:{type:["string","null"],description:"This field can be skipped, or value can be null or present"},requiredButNullableField:{type:["string","null"],description:"This field must be present, but value can be null"},requiredField:{type:"string",description:"This field must be present, and value cannot be null"}},description:"Demonstrate required and nullable. Note, @Schema is only descriptive without nullability check",examples:[{enumField:"COMPLEX1",notRequiredField:"string",requiredAndNullableField:"string",requiredButNullableField:"string",requiredField:"string"}],required:["enumField","requiredButNullableField","requiredField"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Demonstrate required and nullable. Note, @Schema is only descriptive without nullability check",properties:{enumField:{description:"Follows OpenAPI 3.1 spec",enum:["COMPLEX1","COMPLEX2",null],title:"ComplexEnum",type:["string","null"]},notRequiredField:{description:"This field can be skipped, but value cannot be null",type:"string"},requiredAndNullableField:{description:"This field can be skipped, or value can be null or present",type:["string","null"]},requiredButNullableField:{description:"This field must be present, but value can be null",type:["string","null"]},requiredField:{description:"This field must be present, and value cannot be null",type:"string"}},required:["enumField","requiredButNullableField","requiredField"],title:"RequiredAndNullablePayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto":{title:"XmlPayloadDto",type:"string",properties:{someAttribute:{type:"string"},someEnum:{title:"ExampleEnum",type:"string",enum:["FOO1","FOO2","FOO3"]},someLong:{type:"integer",format:"int64"},someString:{type:"string"}},examples:['<io.github.springwolf.examples.kafka.dtos.XmlPayloadDto someAttribute="string"><someEnum>FOO1</someEnum><someLong>0</someLong><someString>string</someString></io.github.springwolf.examples.kafka.dtos.XmlPayloadDto>'],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someAttribute:{},someEnum:{enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"XmlPayloadDto",type:"string"}},"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto":{title:"YamlPayloadDto",type:"string",properties:{someEnum:{title:"ExampleEnum",type:"string",enum:["FOO1","FOO2","FOO3"]},someLong:{type:"integer",format:"int64"},someString:{type:"string"}},examples:[`someEnum: FOO1
someLong: 0
someString: string
`],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someEnum:{enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"YamlPayloadDto",type:"string"}},"io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower":{title:"EnginePower",type:"object",properties:{hp:{type:"integer",format:"int32"},torque:{type:"integer",format:"int32"}},examples:[{hp:0,torque:0}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{hp:{format:"int32",type:"integer"},torque:{}},title:"EnginePower",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{discriminator:"vehicleType",title:"VehicleBase",type:"object",properties:{enginePower:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower"},powerSource:{type:"string"},topSpeed:{type:"integer",format:"int32"},vehicleType:{type:"string"}},description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],oneOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto"},{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{allOf:[{},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto":{title:"VehicleElectricPayloadDto",type:"object",description:"Electric vehicle implementation of VehicleBase",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{batteryCapacity:{type:"integer",format:"int32"},chargeTime:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{},{allOf:[{},{properties:{fuelCapacity:{format:"int32",type:"integer"}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{batteryCapacity:{},chargeTime:{}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto":{title:"VehicleGasolinePayloadDto",type:"object",description:"Gasoline vehicle implementation of VehicleBase",examples:[{enginePower:{hp:0,torque:0},fuelCapacity:0,powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{fuelCapacity:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}},"java.lang.Integer":{type:"integer",format:"int32",examples:[0],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",format:"int32",type:"integer"}},"java.lang.String":{type:"string",examples:['"string"'],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",type:"string"}},"javax.money.MonetaryAmount":{type:"object",properties:{amount:{type:"number",exclusiveMinimum:.01,examples:[99.99]},currency:{type:"string",examples:["USD"]}},examples:[{amount:99.99,currency:"USD"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{amount:{exclusiveMinimum:.01,type:"number"},currency:{type:"string"}},type:"object"}}},messages:{PayloadNotUsed:{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-PayloadNotUsed"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{title:"PayloadNotUsed",type:"object",properties:{},description:"No payload specified"}},name:"PayloadNotUsed",title:"PayloadNotUsed",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{headers:{$ref:"#/components/schemas/HeadersNotUsed"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"}},name:"StringPayload",title:"StringEnvelope",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}},contentType:"application/avro",name:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto",title:"AnotherPayloadAvroDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-Message"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}},name:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message",title:"Message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-AnotherTopic"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-ExamplePayloadDto-1408272656"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto":{headers:{$ref:"#/components/schemas/SpringDefaultHeaderAndCloudEvent"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto",title:"NestedPayloadDto",bindings:{kafka:{key:{type:"string",description:"Kafka Producer Message Key",examples:["example-key"]},bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto",title:"RequiredAndNullablePayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}},contentType:"text/xml",name:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto",title:"XmlPayloadDto",description:"Showcases a xml based message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}},contentType:"application/yaml",name:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto",title:"YamlPayloadDto",description:"Showcases a yaml based message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-VehicleBase"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},name:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",title:"VehicleBase",bindings:{kafka:{bindingVersion:"0.5.0"}}},"java.lang.Integer":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-integer"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{type:"integer",format:"int32",examples:[0]}},name:"java.lang.Integer",title:"integer",bindings:{kafka:{bindingVersion:"0.5.0"}}},"java.lang.String":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-string"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{type:"string",examples:['"string"']}},name:"java.lang.String",title:"string",bindings:{kafka:{bindingVersion:"0.5.0"}}},"javax.money.MonetaryAmount":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-MonetaryAmount"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/javax.money.MonetaryAmount"}},name:"javax.money.MonetaryAmount",title:"MonetaryAmount",bindings:{kafka:{key:{type:"string",description:"Kafka Consumer Message Key",examples:["example-key"]},bindingVersion:"0.5.0"}}}}},operations:{"another-topic_receive_receiveAnotherPayloadBatched":{action:"receive",channel:{$ref:"#/channels/another-topic"},bindings:{kafka:{groupId:{type:"string",const:"example-group-id"},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}]},"another-topic_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-topic"},title:"another-topic_send",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}]},"avro-topic_receive_receiveExampleAvroPayload":{action:"receive",channel:{$ref:"#/channels/avro-topic"},title:"avro-topic_receive",description:"Requires a running kafka-schema-registry. See docker-compose.yml to start it",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/avro-topic/messages/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}]},"example-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/example-topic/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}]},"integer-topic_receive_receiveIntegerPayload":{action:"receive",channel:{$ref:"#/channels/integer-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/integer-topic/messages/java.lang.Integer"}]},"multi-payload-topic_receive_ExampleClassLevelKafkaListener":{action:"receive",channel:{$ref:"#/channels/multi-payload-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/multi-payload-topic/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},{$ref:"#/channels/multi-payload-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"},{$ref:"#/channels/multi-payload-topic/messages/javax.money.MonetaryAmount"}]},"multi-payload-topic_receive_receiveMonetaryAmount":{action:"receive",channel:{$ref:"#/channels/multi-payload-topic"},title:"multi-payload-topic_receive",description:"Override description in the AsyncListener annotation with servers at kafka:29092",bindings:{kafka:{groupId:{type:"string",const:"foo-groupId"},clientId:{type:"string",const:"foo-clientId"},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/multi-payload-topic/messages/javax.money.MonetaryAmount"}]},"no-payload-used-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/no-payload-used-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/no-payload-used-topic/messages/PayloadNotUsed"}]},"nullable-topic_receive_receiveNullablePayload":{action:"receive",channel:{$ref:"#/channels/nullable-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/nullable-topic/messages/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}]},"protobuf-topic_receive_receiveExampleProtobufPayload":{action:"receive",channel:{$ref:"#/channels/protobuf-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/protobuf-topic/messages/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}]},"string-topic_receive_receiveStringPayload":{action:"receive",channel:{$ref:"#/channels/string-topic"},title:"string-topic_receive",description:"Final classes (like String) can be documented using an envelope class and the @AsyncApiPayload annotation.",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/string-topic/messages/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"},{$ref:"#/channels/string-topic/messages/java.lang.String"}]},"topic-defined-via-asyncPublisher-annotation_send_sendMessage":{action:"send",channel:{$ref:"#/channels/topic-defined-via-asyncPublisher-annotation"},title:"topic-defined-via-asyncPublisher-annotation_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{kafka:{clientId:{type:"string",const:"foo-clientId"},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/topic-defined-via-asyncPublisher-annotation/messages/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}]},"vehicle-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/vehicle-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/vehicle-topic/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}]},"xml-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/xml-topic"},title:"xml-topic_receive",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/xml-topic/messages/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}]},"yaml-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/yaml-topic"},title:"yaml-topic_receive",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/yaml-topic/messages/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}]}}};var sf={asyncapi:"3.1.0",info:{title:"Springwolf example project - Kafka",version:"1.0.0",description:"This group only contains endpoints that are related to vehicles.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-apitype":"internal","x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"vehicle-topic":{address:"vehicle-topic",messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},bindings:{kafka:{bindingVersion:"0.5.0"}}}},components:{schemas:{"SpringKafkaDefaultHeaders-VehicleBase":{title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],const:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower":{title:"EnginePower",type:"object",properties:{hp:{type:"integer",format:"int32"},torque:{type:"integer",format:"int32"}},examples:[{hp:0,torque:0}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{hp:{format:"int32",type:"integer"},torque:{}},title:"EnginePower",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{discriminator:"vehicleType",title:"VehicleBase",type:"object",properties:{enginePower:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower"},powerSource:{type:"string"},topSpeed:{type:"integer",format:"int32"},vehicleType:{type:"string"}},description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],oneOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto"},{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{allOf:[{},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto":{title:"VehicleElectricPayloadDto",type:"object",description:"Electric vehicle implementation of VehicleBase",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{batteryCapacity:{type:"integer",format:"int32"},chargeTime:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{},{allOf:[{},{properties:{fuelCapacity:{format:"int32",type:"integer"}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{batteryCapacity:{},chargeTime:{}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto":{title:"VehicleGasolinePayloadDto",type:"object",description:"Gasoline vehicle implementation of VehicleBase",examples:[{enginePower:{hp:0,torque:0},fuelCapacity:0,powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{fuelCapacity:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}}},messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-VehicleBase"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},name:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",title:"VehicleBase",bindings:{kafka:{bindingVersion:"0.5.0"}}}}},operations:{"vehicle-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/vehicle-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/vehicle-topic/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}]}}};var lf={initialConfig:{showBindings:!0,showHeaders:!0},groups:[{name:"Only Vehicles"}]};var cf={asyncapi:"3.1.0",info:{title:"Springwolf example project - JMS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-jms-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"jms-server":{host:"tcp://activemq:61616",protocol:"jms"}},channels:{"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}},bindings:{jms:{bindingVersion:"0.0.1"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}},bindings:{jms:{bindingVersion:"0.0.1"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{jms:{bindingVersion:"0.0.1"}}},"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{jms:{bindingVersion:"0.0.1"}}}}},operations:{"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{jms:{}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}]},"another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-queue"},title:"another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{jms:{"internal-field":"customValue",nested:{key:"nestedValue"}}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{jms:{}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}]}}};var df={asyncapi:"3.1.0",info:{title:"Springwolf example project - SNS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-sns-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"sns-server":{host:"http://localhost:4566",protocol:"sns"}},channels:{"another-topic":{address:"another-topic",messages:{"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}},bindings:{}},"example-topic":{address:"example-topic",messages:{"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}},bindings:{}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"There can be headers, but they are not explicitly documented.",title:"HeadersNotDocumented",type:"object"}},"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Another payload model",properties:{example:{description:"Example payload model",properties:{someEnum:{description:"Some enum field",enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{description:"Some long field",format:"int64",minimum:0,type:"integer"},someString:{description:"Some string field",type:"string"}},required:["someEnum","someString"],title:"ExamplePayloadDto",type:"object"},foo:{description:"Foo field",maxLength:100,type:"string"}},required:["example"],title:"AnotherPayloadDto",type:"object"}},"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Example payload model",properties:{someEnum:{description:"Some enum field",enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{description:"Some long field",format:"int64",minimum:0,type:"integer"},someString:{description:"Some string field",type:"string"}},required:["someEnum","someString"],title:"ExamplePayloadDto",type:"object"}}},messages:{"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{sns:{}}},"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{sns:{}}}}},operations:{"another-topic_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-topic"},title:"another-topic_receive",description:"Auto-generated description",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}]},"another-topic_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-topic"},title:"another-topic_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}]},"example-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-topic"},title:"example-topic_receive",description:"Auto-generated description",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/example-topic/messages/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}]}}};var mf={asyncapi:"3.1.0",info:{title:"Springwolf example project - SQS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-sqs-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"sqs-server":{host:"http://localhost:4566",protocol:"sqs"}},channels:{"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}},bindings:{sqs:{queue:{name:"another-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600},bindingVersion:"0.2.0"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}},bindings:{sqs:{queue:{name:"example-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600},bindingVersion:"0.2.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{sqs:{}}},"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{sqs:{}}}}},operations:{"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{sqs:{queues:[{name:"another-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}]},"another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-queue"},title:"another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{sqs:{queues:[{name:"queue-name",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{sqs:{queues:[{name:"example-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}]}}};var pf={asyncapi:"3.1.0",info:{title:"Springwolf example project - STOMP",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-stomp-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{stomp:{host:"localhost:8080/myendpoint",protocol:"stomp"}},channels:{"_app_queue_another-queue":{address:"/app/queue/another-queue",messages:{"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}},bindings:{}},"_app_queue_example-queue":{address:"/app/queue/example-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_queue_sendto-queue":{address:"/app/queue/sendto-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_queue_sendtouser-queue":{address:"/app/queue/sendtouser-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_topic_sendto-response-queue":{address:"/app/topic/sendto-response-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_user_queue_sendtouser-response-queue":{address:"/user/queue/sendtouser-response-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},SpringStompDefaultHeaders:{title:"SpringStompDefaultHeaders",type:"object",properties:{},examples:[{}]},"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{stomp:{}}},"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringStompDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{stomp:{}}}}},operations:{"_app_queue_another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/_app_queue_another-queue"},title:"_app_queue_another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_another-queue/messages/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}]},"_app_queue_example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/_app_queue_example-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_example-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]},"_app_queue_sendto-queue_receive_receiveExamplePayloadSendTo":{action:"receive",channel:{$ref:"#/channels/_app_queue_sendto-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_sendto-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}],reply:{channel:{$ref:"#/channels/_app_topic_sendto-response-queue"},messages:[{$ref:"#/channels/_app_topic_sendto-response-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]}},"_app_queue_sendtouser-queue_receive_receiveExamplePayloadSendToUser":{action:"receive",channel:{$ref:"#/channels/_app_queue_sendtouser-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_sendtouser-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}],reply:{channel:{$ref:"#/channels/_user_queue_sendtouser-response-queue"},messages:[{$ref:"#/channels/_user_queue_sendtouser-response-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]}}}};var js={amqp:{value:af},"cloud-stream":{value:rf},jms:{value:cf},kafka:{value:of,groups:{"Only Vehicles":sf},uiConfig:lf},sns:{value:df},sqs:{value:mf},stomp:{value:pf}};var Vs=class{mockData=this.selectMockData();createDb(){return{}}get(t){if(t.req.url.endsWith("/docs"))return t.utils.createResponse$(()=>({status:ge.OK,body:this.mockData.value}));if(t.req.url.indexOf("/docs/")!==-1){let e=t.req.url.split("/docs/")[1];return t.utils.createResponse$(()=>this.mockData.groups===void 0||this.mockData.groups[e]===void 0?{status:ge.NOT_FOUND,body:void 0}:{status:ge.OK,body:this.mockData.groups[e]})}else{if(t.req.url.endsWith("/publish"))return t.utils.createResponse$(()=>({status:ge.OK,body:{}}));if(t.req.url.endsWith("/ui-config"))return this.mockData.uiConfig?t.utils.createResponse$(()=>({status:ge.OK,body:this.mockData.uiConfig})):{status:ge.NOT_FOUND,body:void 0}}return t.utils.getPassThruBackend().handle(t.req)}post(t){return t.req.url.endsWith("/publish")?t.utils.createResponse$(()=>({status:ge.OK})):t.utils.getPassThruBackend().handle(t.req)}selectMockData(){let t=window.location.hostname,e=Object.keys(js).filter(i=>t.includes(i));return 0<e.length?js[e[0]]:js.kafka}};function uf(n,t){let i=!t?.manualCleanup?t?.injector?.get(Ei)??p(Ei):null,a=K0(t?.equal),r;t?.requireSync?r=ne({kind:0},{equal:a}):r=ne({kind:1,value:t?.initialValue},{equal:a});let o,s=n.subscribe({next:l=>r.set({kind:1,value:l}),error:l=>{r.set({kind:2,error:l}),o?.()},complete:()=>{o?.()}});if(t?.requireSync&&r().kind===0)throw new Fe(601,!1);return o=i?.onDestroy(s.unsubscribe.bind(s)),cn(()=>{let l=r();switch(l.kind){case 1:return l.value;case 2:throw l.error;case 0:throw new Fe(601,!1)}},{equal:t?.equal})}function K0(n=Object.is){return(t,e)=>t.kind===1&&e.kind===1&&n(t.value,e.value)}function _d(){return{async:!1,breaks:!1,extensions:null,gfm:!0,hooks:null,pedantic:!1,renderer:null,silent:!1,tokenizer:null,walkTokens:null}}var Ri=_d();function xf(n){Ri=n}var Ti={exec:()=>null};function ka(n){let t=[];return e=>{let i=Math.max(0,Math.min(3,e-1)),a=t[i];return a||(a=n(i),t[i]=a),a}}function we(n,t=""){let e=typeof n=="string"?n:n.source,i={replace:(a,r)=>{let o=typeof r=="string"?r:r.source;return o=o.replace(wt.caret,"$1"),e=e.replace(a,o),i},getRegex:()=>new RegExp(e,t)};return i}var Y0=((n="")=>{try{return!!new RegExp("(?<=1)(?<!1)"+n)}catch{return!1}})(),wt={codeRemoveIndent:/^(?: {1,4}| {0,3}\t)/gm,outputLinkReplace:/\\([\[\]])/g,indentCodeCompensation:/^(\s+)(?:```)/,beginningSpace:/^\s+/,endingHash:/#$/,startingSpaceChar:/^ /,endingSpaceChar:/ $/,nonSpaceChar:/[^ ]/,newLineCharGlobal:/\n/g,tabCharGlobal:/\t/g,multipleSpaceGlobal:/\s+/g,blankLine:/^[ \t]*$/,doubleBlankLine:/\n[ \t]*\n[ \t]*$/,blockquoteStart:/^ {0,3}>/,blockquoteSetextReplace:/\n {0,3}((?:=+|-+) *)(?=\n|$)/g,blockquoteSetextReplace2:/^ {0,3}>[ \t]?/gm,listReplaceNesting:/^ {1,4}(?=( {4})*[^ ])/g,listIsTask:/^\[[ xX]\] +\S/,listReplaceTask:/^\[[ xX]\] +/,listTaskCheckbox:/\[[ xX]\]/,anyLine:/\n.*\n/,hrefBrackets:/^<(.*)>$/,tableDelimiter:/[:|]/,tableAlignChars:/^\||\| *$/g,tableRowBlankLine:/\n[ \t]*$/,tableAlignRight:/^ *-+: *$/,tableAlignCenter:/^ *:-+: *$/,tableAlignLeft:/^ *:-+ *$/,startATag:/^<a /i,endATag:/^<\/a>/i,startPreScriptTag:/^<(pre|code|kbd|script)(\s|>)/i,endPreScriptTag:/^<\/(pre|code|kbd|script)(\s|>)/i,startAngleBracket:/^</,endAngleBracket:/>$/,pedanticHrefTitle:/^([^'"]*[^\s])\s+(['"])(.*)\2/,unicodeAlphaNumeric:/[\p{L}\p{N}]/u,escapeTest:/[&<>"']/,escapeReplace:/[&<>"']/g,escapeTestNoEncode:/[<>"']|&(?!(#\d{1,7}|#[Xx][a-fA-F0-9]{1,6}|\w+);)/,escapeReplaceNoEncode:/[<>"']|&(?!(#\d{1,7}|#[Xx][a-fA-F0-9]{1,6}|\w+);)/g,caret:/(^|[^\[])\^/g,percentDecode:/%25/g,findPipe:/\|/g,splitPipe:/ \|/,slashPipe:/\\\|/g,carriageReturn:/\r\n|\r/g,spaceLine:/^ +$/gm,notSpaceStart:/^\S*/,endingNewline:/\n$/,listItemRegex:n=>new RegExp(`^( {0,3}${n})((?:[	 ][^\\n]*)?(?:\\n|$))`),nextBulletRegex:ka(n=>new RegExp(`^ {0,${n}}(?:[*+-]|\\d{1,9}[.)])((?:[ 	][^\\n]*)?(?:\\n|$))`)),hrRegex:ka(n=>new RegExp(`^ {0,${n}}((?:- *){3,}|(?:_ *){3,}|(?:\\* *){3,})(?:\\n+|$)`)),fencesBeginRegex:ka(n=>new RegExp(`^ {0,${n}}(?:\`\`\`|~~~)`)),headingBeginRegex:ka(n=>new RegExp(`^ {0,${n}}#`)),htmlBeginRegex:ka(n=>new RegExp(`^ {0,${n}}<(?:[a-z].*>|!--)`,"i")),blockquoteBeginRegex:ka(n=>new RegExp(`^ {0,${n}}>`))},X0=/^(?:[ \t]*(?:\n|$))+/,Z0=/^((?: {4}| {0,3}\t)[^\n]+(?:\n(?:[ \t]*(?:\n|$))*)?)+/,Q0=/^ {0,3}(`{3,}(?=[^`\n]*(?:\n|$))|~{3,})([^\n]*)(?:\n|$)(?:|([\s\S]*?)(?:\n|$))(?: {0,3}\1[~`]* *(?=\n|$)|$)/,Zr=/^ {0,3}((?:-[\t ]*){3,}|(?:_[ \t]*){3,}|(?:\*[ \t]*){3,})(?:\n+|$)/,J0=/^ {0,3}(#{1,6})(?=\s|$)(.*)(?:\n+|$)/,yd=/ {0,3}(?:[*+-]|\d{1,9}[.)])/,wf=/^(?!bull |blockCode|fences|blockquote|heading|html|table)((?:.|\n(?!\s*?\n|bull |blockCode|fences|blockquote|heading|html|table))+?)\n {0,3}(=+|-+) *(?:\n+|$)/,Sf=we(wf).replace(/bull/g,yd).replace(/blockCode/g,/(?: {4}| {0,3}\t)/).replace(/fences/g,/ {0,3}(?:`{3,}|~{3,})/).replace(/blockquote/g,/ {0,3}>/).replace(/heading/g,/ {0,3}#{1,6}/).replace(/html/g,/ {0,3}<[^\n>]+>\n/).replace(/\|table/g,"").getRegex(),ex=we(wf).replace(/bull/g,yd).replace(/blockCode/g,/(?: {4}| {0,3}\t)/).replace(/fences/g,/ {0,3}(?:`{3,}|~{3,})/).replace(/blockquote/g,/ {0,3}>/).replace(/heading/g,/ {0,3}#{1,6}/).replace(/html/g,/ {0,3}<[^\n>]+>\n/).replace(/table/g,/ {0,3}\|?(?:[:\- ]*\|)+[\:\- ]*\n/).getRegex(),xd=/^([^\n]+(?:\n(?!hr|heading|lheading|blockquote|fences|list|html|table| +\n)[^\n]+)*)/,tx=/^[^\n]+/,wd=/(?!\s*\])(?:\\[\s\S]|[^\[\]\\])+/,nx=we(/^ {0,3}\[(label)\]: *(?:\n[ \t]*)?([^<\s][^\s]*|<.*?>)(?:(?: +(?:\n[ \t]*)?| *\n[ \t]*)(title))? *(?:\n+|$)/).replace("label",wd).replace("title",/(?:"(?:\\"?|[^"\\])*"|'[^'\n]*(?:\n[^'\n]+)*\n?'|\([^()]*\))/).getRegex(),ix=we(/^(bull)([ \t][^\n]*?)?(?:\n|$)/).replace(/bull/g,yd).getRegex(),Us="address|article|aside|base|basefont|blockquote|body|caption|center|col|colgroup|dd|details|dialog|dir|div|dl|dt|fieldset|figcaption|figure|footer|form|frame|frameset|h[1-6]|head|header|hr|html|iframe|legend|li|link|main|menu|menuitem|meta|nav|noframes|ol|optgroup|option|p|param|search|section|summary|table|tbody|td|tfoot|th|thead|title|tr|track|ul",Sd=/<!--(?:-?>|[\s\S]*?(?:-->|$))/,ax=we("^ {0,3}(?:<(script|pre|style|textarea)[\\s>][\\s\\S]*?(?:</\\1>[^\\n]*\\n+|$)|comment[^\\n]*(\\n+|$)|<\\?[\\s\\S]*?(?:\\?>\\n*|$)|<![A-Z][\\s\\S]*?(?:>\\n*|$)|<!\\[CDATA\\[[\\s\\S]*?(?:\\]\\]>\\n*|$)|</?(tag)(?: +|\\n|/?>)[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$)|<(?!script|pre|style|textarea)([a-z][\\w-]*)(?:attribute)*? */?>(?=[ \\t]*(?:\\n|$))[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$)|</(?!script|pre|style|textarea)[a-z][\\w-]*\\s*>(?=[ \\t]*(?:\\n|$))[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$))","i").replace("comment",Sd).replace("tag",Us).replace("attribute",/ +[a-zA-Z:_][\w.:-]*(?: *= *"[^"\n]*"| *= *'[^'\n]*'| *= *[^\s"'=<>`]+)?/).getRegex(),kf=we(xd).replace("hr",Zr).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("|lheading","").replace("|table","").replace("blockquote"," {0,3}>").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)])[ \\t]+[^ \\t\\n]").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",Us).getRegex(),rx=we(/^( {0,3}> ?(paragraph|[^\n]*)(?:\n|$))+/).replace("paragraph",kf).getRegex(),kd={blockquote:rx,code:Z0,def:nx,fences:Q0,heading:J0,hr:Zr,html:ax,lheading:Sf,list:ix,newline:X0,paragraph:kf,table:Ti,text:tx},hf=we("^ *([^\\n ].*)\\n {0,3}((?:\\| *)?:?-+:? *(?:\\| *:?-+:? *)*(?:\\| *)?)(?:\\n((?:(?! *\\n|hr|heading|blockquote|code|fences|list|html).*(?:\\n|$))*)\\n*|$)").replace("hr",Zr).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("blockquote"," {0,3}>").replace("code","(?: {4}| {0,3}	)[^\\n]").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)])[ \\t]").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",Us).getRegex(),ox=Ee(N({},kd),{lheading:ex,table:hf,paragraph:we(xd).replace("hr",Zr).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("|lheading","").replace("table",hf).replace("blockquote"," {0,3}>").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)])[ \\t]+[^ \\t\\n]").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",Us).getRegex()}),sx=Ee(N({},kd),{html:we(`^ *(?:comment *(?:\\n|\\s*$)|<(tag)[\\s\\S]+?</\\1> *(?:\\n{2,}|\\s*$)|<tag(?:"[^"]*"|'[^']*'|\\s[^'"/>\\s]*)*?/?> *(?:\\n{2,}|\\s*$))`).replace("comment",Sd).replace(/tag/g,"(?!(?:a|em|strong|small|s|cite|q|dfn|abbr|data|time|code|var|samp|kbd|sub|sup|i|b|u|mark|ruby|rt|rp|bdi|bdo|span|br|wbr|ins|del|img)\\b)\\w+(?!:|[^\\w\\s@]*@)\\b").getRegex(),def:/^ *\[([^\]]+)\]: *<?([^\s>]+)>?(?: +(["(][^\n]+[")]))? *(?:\n+|$)/,heading:/^(#{1,6})(.*)(?:\n+|$)/,fences:Ti,lheading:/^(.+?)\n {0,3}(=+|-+) *(?:\n+|$)/,paragraph:we(xd).replace("hr",Zr).replace("heading",` *#{1,6} *[^
]`).replace("lheading",Sf).replace("|table","").replace("blockquote"," {0,3}>").replace("|fences","").replace("|list","").replace("|html","").replace("|tag","").getRegex()}),lx=/^\\([!"#$%&'()*+,\-./:;<=>?@\[\]\\^_`{|}~])/,cx=/^(`+)([^`]|[^`][\s\S]*?[^`])\1(?!`)/,Cf=/^( {2,}|\\)\n(?!\s*$)/,dx=/^(`+|[^`])(?:(?= {2,}\n)|[\s\S]*?(?:(?=[\\<!\[`*_]|\b_|$)|[^ ](?= {2,}\n)))/,Ca=/[\p{P}\p{S}]/u,qs=/[\s\p{P}\p{S}]/u,Cd=/[^\s\p{P}\p{S}]/u,mx=we(/^((?![*_])punctSpace)/,"u").replace(/punctSpace/g,qs).getRegex(),Ef=/(?!~)[\p{P}\p{S}]/u,px=/(?!~)[\s\p{P}\p{S}]/u,ux=/(?:[^\s\p{P}\p{S}]|~)/u,hx=we(/link|precode-code|html/,"g").replace("link",/\[(?:[^\[\]`]|(?<a>`+)[^`]+\k<a>(?!`))*?\]\((?:\\[\s\S]|[^\\\(\)]|\((?:\\[\s\S]|[^\\\(\)])*\))*\)/).replace("precode-",Y0?"(?<!`)()":"(^^|[^`])").replace("code",/(?<b>`+)[^`]+\k<b>(?!`)/).replace("html",/<(?! )[^<>]*?>/).getRegex(),Df=/^(?:\*+(?:((?!\*)punct)|([^\s*]))?)|^_+(?:((?!_)punct)|([^\s_]))?/,fx=we(Df,"u").replace(/punct/g,Ca).getRegex(),gx=we(Df,"u").replace(/punct/g,Ef).getRegex(),Mf="^[^_*]*?__[^_*]*?\\*[^_*]*?(?=__)|[^*]+(?=[^*])|(?!\\*)punct(\\*+)(?=[\\s]|$)|notPunctSpace(\\*+)(?!\\*)(?=punctSpace|$)|(?!\\*)punctSpace(\\*+)(?=notPunctSpace)|[\\s](\\*+)(?!\\*)(?=punct)|(?!\\*)punct(\\*+)(?!\\*)(?=punct)|notPunctSpace(\\*+)(?=notPunctSpace)",bx=we(Mf,"gu").replace(/notPunctSpace/g,Cd).replace(/punctSpace/g,qs).replace(/punct/g,Ca).getRegex(),vx=we(Mf,"gu").replace(/notPunctSpace/g,ux).replace(/punctSpace/g,px).replace(/punct/g,Ef).getRegex(),_x=we("^[^_*]*?\\*\\*[^_*]*?_[^_*]*?(?=\\*\\*)|[^_]+(?=[^_])|(?!_)punct(_+)(?=[\\s]|$)|notPunctSpace(_+)(?!_)(?=punctSpace|$)|(?!_)punctSpace(_+)(?=notPunctSpace)|[\\s](_+)(?!_)(?=punct)|(?!_)punct(_+)(?!_)(?=punct)","gu").replace(/notPunctSpace/g,Cd).replace(/punctSpace/g,qs).replace(/punct/g,Ca).getRegex(),yx=we(/^~~?(?:((?!~)punct)|[^\s~])/,"u").replace(/punct/g,Ca).getRegex(),xx="^[^~]+(?=[^~])|(?!~)punct(~~?)(?=[\\s]|$)|notPunctSpace(~~?)(?!~)(?=punctSpace|$)|(?!~)punctSpace(~~?)(?=notPunctSpace)|[\\s](~~?)(?!~)(?=punct)|(?!~)punct(~~?)(?!~)(?=punct)|notPunctSpace(~~?)(?=notPunctSpace)",wx=we(xx,"gu").replace(/notPunctSpace/g,Cd).replace(/punctSpace/g,qs).replace(/punct/g,Ca).getRegex(),Sx=we(/\\(punct)/,"gu").replace(/punct/g,Ca).getRegex(),kx=we(/^<(scheme:[^\s\x00-\x1f<>]*|email)>/).replace("scheme",/[a-zA-Z][a-zA-Z0-9+.-]{1,31}/).replace("email",/[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+(@)[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+(?![-_])/).getRegex(),Cx=we(Sd).replace("(?:-->|$)","-->").getRegex(),Ex=we("^comment|^</[a-zA-Z][\\w:-]*\\s*>|^<[a-zA-Z][\\w-]*(?:attribute)*?\\s*/?>|^<\\?[\\s\\S]*?\\?>|^<![a-zA-Z]+\\s[\\s\\S]*?>|^<!\\[CDATA\\[[\\s\\S]*?\\]\\]>").replace("comment",Cx).replace("attribute",/\s+[a-zA-Z:_][\w.:-]*(?:\s*=\s*"[^"]*"|\s*=\s*'[^']*'|\s*=\s*[^\s"'=<>`]+)?/).getRegex(),zs=/(?:\[(?:\\[\s\S]|[^\[\]\\])*\]|\\[\s\S]|`+(?!`)[^`]*?`+(?!`)|``+(?=\])|[^\[\]\\`])*?/,Dx=we(/^!?\[(label)\]\(\s*(href)(?:(?:[ \t]+(?:\n[ \t]*)?|\n[ \t]*)(title))?\s*\)/).replace("label",zs).replace("href",/<(?:\\.|[^\n<>\\])+>|[^ \t\n\x00-\x1f]*/).replace("title",/"(?:\\"?|[^"\\])*"|'(?:\\'?|[^'\\])*'|\((?:\\\)?|[^)\\])*\)/).getRegex(),Nf=we(/^!?\[(label)\]\[(ref)\]/).replace("label",zs).replace("ref",wd).getRegex(),If=we(/^!?\[(ref)\](?:\[\])?/).replace("ref",wd).getRegex(),Mx=we("reflink|nolink(?!\\()","g").replace("reflink",Nf).replace("nolink",If).getRegex(),ff=/[hH][tT][tT][pP][sS]?|[fF][tT][pP]/,Ed={_backpedal:Ti,anyPunctuation:Sx,autolink:kx,blockSkip:hx,br:Cf,code:cx,del:Ti,delLDelim:Ti,delRDelim:Ti,emStrongLDelim:fx,emStrongRDelimAst:bx,emStrongRDelimUnd:_x,escape:lx,link:Dx,nolink:If,punctuation:mx,reflink:Nf,reflinkSearch:Mx,tag:Ex,text:dx,url:Ti},Nx=Ee(N({},Ed),{link:we(/^!?\[(label)\]\((.*?)\)/).replace("label",zs).getRegex(),reflink:we(/^!?\[(label)\]\s*\[([^\]]*)\]/).replace("label",zs).getRegex()}),gd=Ee(N({},Ed),{emStrongRDelimAst:vx,emStrongLDelim:gx,delLDelim:yx,delRDelim:wx,url:we(/^((?:protocol):\/\/|www\.)(?:[a-zA-Z0-9\-]+\.?)+[^\s<]*|^email/).replace("protocol",ff).replace("email",/[A-Za-z0-9._+-]+(@)[a-zA-Z0-9-_]+(?:\.[a-zA-Z0-9-_]*[a-zA-Z0-9])+(?![-_])/).getRegex(),_backpedal:/(?:[^?!.,:;*_'"~()&]+|\([^)]*\)|&(?![a-zA-Z0-9]+;$)|[?!.,:;*_'"~)]+(?!$))+/,del:/^(~~?)(?=[^\s~])((?:\\[\s\S]|[^\\])*?(?:\\[\s\S]|[^\s~\\]))\1(?=[^~]|$)/,text:we(/^([`~]+|[^`~])(?:(?= {2,}\n)|(?=[a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-]+@)|[\s\S]*?(?:(?=[\\<!\[`*~_]|\b_|protocol:\/\/|www\.|$)|[^ ](?= {2,}\n)|[^a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-](?=[a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-]+@)))/).replace("protocol",ff).getRegex()}),Ix=Ee(N({},gd),{br:we(Cf).replace("{2,}","*").getRegex(),text:we(gd.text).replace("\\b_","\\b_| {2,}\\n").replace(/\{2,\}/g,"*").getRegex()}),$s={normal:kd,gfm:ox,pedantic:sx},Yr={normal:Ed,gfm:gd,breaks:Ix,pedantic:Nx},Ax={"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"},gf=n=>Ax[n];function Cn(n,t){if(t){if(wt.escapeTest.test(n))return n.replace(wt.escapeReplace,gf)}else if(wt.escapeTestNoEncode.test(n))return n.replace(wt.escapeReplaceNoEncode,gf);return n}function bf(n){try{n=encodeURI(n).replace(wt.percentDecode,"%")}catch{return null}return n}function vf(n,t){let e=n.replace(wt.findPipe,(r,o,s)=>{let l=!1,c=o;for(;--c>=0&&s[c]==="\\";)l=!l;return l?"|":" |"}),i=e.split(wt.splitPipe),a=0;if(i[0].trim()||i.shift(),i.length>0&&!i.at(-1)?.trim()&&i.pop(),t)if(i.length>t)i.splice(t);else for(;i.length<t;)i.push("");for(;a<i.length;a++)i[a]=i[a].trim().replace(wt.slashPipe,"|");return i}function mi(n,t,e){let i=n.length;if(i===0)return"";let a=0;for(;a<i;){let r=n.charAt(i-a-1);if(r===t&&!e)a++;else if(r!==t&&e)a++;else break}return n.slice(0,i-a)}function _f(n){let t=n.split(`
`),e=t.length-1;for(;e>=0&&wt.blankLine.test(t[e]);)e--;return t.length-e<=2?n:t.slice(0,e+1).join(`
`)}function Tx(n,t){if(n.indexOf(t[1])===-1)return-1;let e=0;for(let i=0;i<n.length;i++)if(n[i]==="\\")i++;else if(n[i]===t[0])e++;else if(n[i]===t[1]&&(e--,e<0))return i;return e>0?-2:-1}function Ox(n,t=0){let e=t,i="";for(let a of n)if(a==="	"){let r=4-e%4;i+=" ".repeat(r),e+=r}else i+=a,e++;return i}function yf(n,t,e,i,a){let r=t.href,o=t.title||null,s=n[1].replace(a.other.outputLinkReplace,"$1");i.state.inLink=!0;let l={type:n[0].charAt(0)==="!"?"image":"link",raw:e,href:r,title:o,text:s,tokens:i.inlineTokens(s)};return i.state.inLink=!1,l}function Rx(n,t,e){let i=n.match(e.other.indentCodeCompensation);if(i===null)return t;let a=i[1];return t.split(`
`).map(r=>{let o=r.match(e.other.beginningSpace);if(o===null)return r;let[s]=o;return s.length>=a.length?r.slice(a.length):r}).join(`
`)}var Hs=class{options;rules;lexer;constructor(n){this.options=n||Ri}space(n){let t=this.rules.block.newline.exec(n);if(t&&t[0].length>0)return{type:"space",raw:t[0]}}code(n){let t=this.rules.block.code.exec(n);if(t){let e=this.options.pedantic?t[0]:_f(t[0]),i=e.replace(this.rules.other.codeRemoveIndent,"");return{type:"code",raw:e,codeBlockStyle:"indented",text:i}}}fences(n){let t=this.rules.block.fences.exec(n);if(t){let e=t[0],i=Rx(e,t[3]||"",this.rules);return{type:"code",raw:e,lang:t[2]?t[2].trim().replace(this.rules.inline.anyPunctuation,"$1"):t[2],text:i}}}heading(n){let t=this.rules.block.heading.exec(n);if(t){let e=t[2].trim();if(this.rules.other.endingHash.test(e)){let i=mi(e,"#");(this.options.pedantic||!i||this.rules.other.endingSpaceChar.test(i))&&(e=i.trim())}return{type:"heading",raw:mi(t[0],`
`),depth:t[1].length,text:e,tokens:this.lexer.inline(e)}}}hr(n){let t=this.rules.block.hr.exec(n);if(t)return{type:"hr",raw:mi(t[0],`
`)}}blockquote(n){let t=this.rules.block.blockquote.exec(n);if(t){let e=mi(t[0],`
`).split(`
`),i="",a="",r=[];for(;e.length>0;){let o=!1,s=[],l;for(l=0;l<e.length;l++)if(this.rules.other.blockquoteStart.test(e[l]))s.push(e[l]),o=!0;else if(!o)s.push(e[l]);else break;e=e.slice(l);let c=s.join(`
`),d=c.replace(this.rules.other.blockquoteSetextReplace,`
    $1`).replace(this.rules.other.blockquoteSetextReplace2,"");i=i?`${i}
${c}`:c,a=a?`${a}
${d}`:d;let m=this.lexer.state.top;if(this.lexer.state.top=!0,this.lexer.blockTokens(d,r,!0),this.lexer.state.top=m,e.length===0)break;let u=r.at(-1);if(u?.type==="code")break;if(u?.type==="blockquote"){let f=u,g=f.raw+`
`+e.join(`
`),b=this.blockquote(g);r[r.length-1]=b,i=i.substring(0,i.length-f.raw.length)+b.raw,a=a.substring(0,a.length-f.text.length)+b.text;break}else if(u?.type==="list"){let f=u,g=f.raw+`
`+e.join(`
`),b=this.list(g);r[r.length-1]=b,i=i.substring(0,i.length-u.raw.length)+b.raw,a=a.substring(0,a.length-f.raw.length)+b.raw,e=g.substring(r.at(-1).raw.length).split(`
`);continue}}return{type:"blockquote",raw:i,tokens:r,text:a}}}list(n){let t=this.rules.block.list.exec(n);if(t){let e=t[1].trim(),i=e.length>1,a={type:"list",raw:"",ordered:i,start:i?+e.slice(0,-1):"",loose:!1,items:[]};e=i?`\\d{1,9}\\${e.slice(-1)}`:`\\${e}`,this.options.pedantic&&(e=i?e:"[*+-]");let r=this.rules.other.listItemRegex(e),o=!1;for(;n;){let l=!1,c="",d="";if(!(t=r.exec(n))||this.rules.block.hr.test(n))break;c=t[0],n=n.substring(c.length);let m=Ox(t[2].split(`
`,1)[0],t[1].length),u=n.split(`
`,1)[0],f=!m.trim(),g=0;if(this.options.pedantic?(g=2,d=m.trimStart()):f?g=t[1].length+1:(g=m.search(this.rules.other.nonSpaceChar),g=g>4?1:g,d=m.slice(g),g+=t[1].length),f&&this.rules.other.blankLine.test(u)&&(c+=u+`
`,n=n.substring(u.length+1),l=!0),!l){let b=this.rules.other.nextBulletRegex(g),_=this.rules.other.hrRegex(g),x=this.rules.other.fencesBeginRegex(g),w=this.rules.other.headingBeginRegex(g),C=this.rules.other.htmlBeginRegex(g),F=this.rules.other.blockquoteBeginRegex(g);for(;n;){let A=n.split(`
`,1)[0],Q;if(u=A,this.options.pedantic?(u=u.replace(this.rules.other.listReplaceNesting,"  "),Q=u):Q=u.replace(this.rules.other.tabCharGlobal,"    "),x.test(u)||w.test(u)||C.test(u)||F.test(u)||b.test(u)||_.test(u))break;if(Q.search(this.rules.other.nonSpaceChar)>=g||!u.trim())d+=`
`+Q.slice(g);else{if(f||m.replace(this.rules.other.tabCharGlobal,"    ").search(this.rules.other.nonSpaceChar)>=4||x.test(m)||w.test(m)||_.test(m))break;d+=`
`+u}f=!u.trim(),c+=A+`
`,n=n.substring(A.length+1),m=Q.slice(g)}}a.loose||(o?a.loose=!0:this.rules.other.doubleBlankLine.test(c)&&(o=!0)),a.items.push({type:"list_item",raw:c,task:!!this.options.gfm&&this.rules.other.listIsTask.test(d),loose:!1,text:d,tokens:[]}),a.raw+=c}let s=a.items.at(-1);if(s)s.raw=s.raw.trimEnd(),s.text=s.text.trimEnd();else return;a.raw=a.raw.trimEnd();for(let l of a.items){this.lexer.state.top=!1,l.tokens=this.lexer.blockTokens(l.text,[]);let c=l.tokens[0];if(l.task&&(c?.type==="text"||c?.type==="paragraph")){l.text=l.text.replace(this.rules.other.listReplaceTask,""),c.raw=c.raw.replace(this.rules.other.listReplaceTask,""),c.text=c.text.replace(this.rules.other.listReplaceTask,"");for(let m=this.lexer.inlineQueue.length-1;m>=0;m--)if(this.rules.other.listIsTask.test(this.lexer.inlineQueue[m].src)){this.lexer.inlineQueue[m].src=this.lexer.inlineQueue[m].src.replace(this.rules.other.listReplaceTask,"");break}let d=this.rules.other.listTaskCheckbox.exec(l.raw);if(d){let m={type:"checkbox",raw:d[0]+" ",checked:d[0]!=="[ ]"};l.checked=m.checked,a.loose?l.tokens[0]&&["paragraph","text"].includes(l.tokens[0].type)&&"tokens"in l.tokens[0]&&l.tokens[0].tokens?(l.tokens[0].raw=m.raw+l.tokens[0].raw,l.tokens[0].text=m.raw+l.tokens[0].text,l.tokens[0].tokens.unshift(m)):l.tokens.unshift({type:"paragraph",raw:m.raw,text:m.raw,tokens:[m]}):l.tokens.unshift(m)}}else l.task&&(l.task=!1);if(!a.loose){let d=l.tokens.filter(u=>u.type==="space"),m=d.length>0&&d.some(u=>this.rules.other.anyLine.test(u.raw));a.loose=m}}if(a.loose)for(let l of a.items){l.loose=!0;for(let c of l.tokens)c.type==="text"&&(c.type="paragraph")}return a}}html(n){let t=this.rules.block.html.exec(n);if(t){let e=_f(t[0]);return{type:"html",block:!0,raw:e,pre:t[1]==="pre"||t[1]==="script"||t[1]==="style",text:e}}}def(n){let t=this.rules.block.def.exec(n);if(t){let e=t[1].toLowerCase().replace(this.rules.other.multipleSpaceGlobal," "),i=t[2]?t[2].replace(this.rules.other.hrefBrackets,"$1").replace(this.rules.inline.anyPunctuation,"$1"):"",a=t[3]?t[3].substring(1,t[3].length-1).replace(this.rules.inline.anyPunctuation,"$1"):t[3];return{type:"def",tag:e,raw:mi(t[0],`
`),href:i,title:a}}}table(n){let t=this.rules.block.table.exec(n);if(!t||!this.rules.other.tableDelimiter.test(t[2]))return;let e=vf(t[1]),i=t[2].replace(this.rules.other.tableAlignChars,"").split("|"),a=t[3]?.trim()?t[3].replace(this.rules.other.tableRowBlankLine,"").split(`
`):[],r={type:"table",raw:mi(t[0],`
`),header:[],align:[],rows:[]};if(e.length===i.length){for(let o of i)this.rules.other.tableAlignRight.test(o)?r.align.push("right"):this.rules.other.tableAlignCenter.test(o)?r.align.push("center"):this.rules.other.tableAlignLeft.test(o)?r.align.push("left"):r.align.push(null);for(let o=0;o<e.length;o++)r.header.push({text:e[o],tokens:this.lexer.inline(e[o]),header:!0,align:r.align[o]});for(let o of a)r.rows.push(vf(o,r.header.length).map((s,l)=>({text:s,tokens:this.lexer.inline(s),header:!1,align:r.align[l]})));return r}}lheading(n){let t=this.rules.block.lheading.exec(n);if(t){let e=t[1].trim();return{type:"heading",raw:mi(t[0],`
`),depth:t[2].charAt(0)==="="?1:2,text:e,tokens:this.lexer.inline(e)}}}paragraph(n){let t=this.rules.block.paragraph.exec(n);if(t){let e=t[1].charAt(t[1].length-1)===`
`?t[1].slice(0,-1):t[1];return{type:"paragraph",raw:t[0],text:e,tokens:this.lexer.inline(e)}}}text(n){let t=this.rules.block.text.exec(n);if(t)return{type:"text",raw:t[0],text:t[0],tokens:this.lexer.inline(t[0])}}escape(n){let t=this.rules.inline.escape.exec(n);if(t)return{type:"escape",raw:t[0],text:t[1]}}tag(n){let t=this.rules.inline.tag.exec(n);if(t)return!this.lexer.state.inLink&&this.rules.other.startATag.test(t[0])?this.lexer.state.inLink=!0:this.lexer.state.inLink&&this.rules.other.endATag.test(t[0])&&(this.lexer.state.inLink=!1),!this.lexer.state.inRawBlock&&this.rules.other.startPreScriptTag.test(t[0])?this.lexer.state.inRawBlock=!0:this.lexer.state.inRawBlock&&this.rules.other.endPreScriptTag.test(t[0])&&(this.lexer.state.inRawBlock=!1),{type:"html",raw:t[0],inLink:this.lexer.state.inLink,inRawBlock:this.lexer.state.inRawBlock,block:!1,text:t[0]}}link(n){let t=this.rules.inline.link.exec(n);if(t){let e=t[2].trim();if(!this.options.pedantic&&this.rules.other.startAngleBracket.test(e)){if(!this.rules.other.endAngleBracket.test(e))return;let r=mi(e.slice(0,-1),"\\");if((e.length-r.length)%2===0)return}else{let r=Tx(t[2],"()");if(r===-2)return;if(r>-1){let o=(t[0].indexOf("!")===0?5:4)+t[1].length+r;t[2]=t[2].substring(0,r),t[0]=t[0].substring(0,o).trim(),t[3]=""}}let i=t[2],a="";if(this.options.pedantic){let r=this.rules.other.pedanticHrefTitle.exec(i);r&&(i=r[1],a=r[3])}else a=t[3]?t[3].slice(1,-1):"";return i=i.trim(),this.rules.other.startAngleBracket.test(i)&&(this.options.pedantic&&!this.rules.other.endAngleBracket.test(e)?i=i.slice(1):i=i.slice(1,-1)),yf(t,{href:i&&i.replace(this.rules.inline.anyPunctuation,"$1"),title:a&&a.replace(this.rules.inline.anyPunctuation,"$1")},t[0],this.lexer,this.rules)}}reflink(n,t){let e;if((e=this.rules.inline.reflink.exec(n))||(e=this.rules.inline.nolink.exec(n))){let i=(e[2]||e[1]).replace(this.rules.other.multipleSpaceGlobal," "),a=t[i.toLowerCase()];if(!a){let r=e[0].charAt(0);return{type:"text",raw:r,text:r}}return yf(e,a,e[0],this.lexer,this.rules)}}emStrong(n,t,e=""){let i=this.rules.inline.emStrongLDelim.exec(n);if(!(!i||!i[1]&&!i[2]&&!i[3]&&!i[4]||i[4]&&e.match(this.rules.other.unicodeAlphaNumeric))&&(!(i[1]||i[3])||!e||this.rules.inline.punctuation.exec(e))){let a=[...i[0]].length-1,r,o,s=a,l=0,c=i[0][0]==="*"?this.rules.inline.emStrongRDelimAst:this.rules.inline.emStrongRDelimUnd;for(c.lastIndex=0,t=t.slice(-1*n.length+a);(i=c.exec(t))!==null;){if(r=i[1]||i[2]||i[3]||i[4]||i[5]||i[6],!r)continue;if(o=[...r].length,i[3]||i[4]){s+=o;continue}else if((i[5]||i[6])&&a%3&&!((a+o)%3)){l+=o;continue}if(s-=o,s>0)continue;o=Math.min(o,o+s+l);let d=[...i[0]][0].length,m=n.slice(0,a+i.index+d+o);if(Math.min(a,o)%2){let f=m.slice(1,-1);return{type:"em",raw:m,text:f,tokens:this.lexer.inlineTokens(f)}}let u=m.slice(2,-2);return{type:"strong",raw:m,text:u,tokens:this.lexer.inlineTokens(u)}}}}codespan(n){let t=this.rules.inline.code.exec(n);if(t){let e=t[2].replace(this.rules.other.newLineCharGlobal," "),i=this.rules.other.nonSpaceChar.test(e),a=this.rules.other.startingSpaceChar.test(e)&&this.rules.other.endingSpaceChar.test(e);return i&&a&&(e=e.substring(1,e.length-1)),{type:"codespan",raw:t[0],text:e}}}br(n){let t=this.rules.inline.br.exec(n);if(t)return{type:"br",raw:t[0]}}del(n,t,e=""){let i=this.rules.inline.delLDelim.exec(n);if(i&&(!i[1]||!e||this.rules.inline.punctuation.exec(e))){let a=[...i[0]].length-1,r,o,s=a,l=this.rules.inline.delRDelim;for(l.lastIndex=0,t=t.slice(-1*n.length+a);(i=l.exec(t))!==null;){if(r=i[1]||i[2]||i[3]||i[4]||i[5]||i[6],!r||(o=[...r].length,o!==a))continue;if(i[3]||i[4]){s+=o;continue}if(s-=o,s>0)continue;o=Math.min(o,o+s);let c=[...i[0]][0].length,d=n.slice(0,a+i.index+c+o),m=d.slice(a,-a);return{type:"del",raw:d,text:m,tokens:this.lexer.inlineTokens(m)}}}}autolink(n){let t=this.rules.inline.autolink.exec(n);if(t){let e,i;return t[2]==="@"?(e=t[1],i="mailto:"+e):(e=t[1],i=e),{type:"link",raw:t[0],text:e,href:i,tokens:[{type:"text",raw:e,text:e}]}}}url(n){let t;if(t=this.rules.inline.url.exec(n)){let e,i;if(t[2]==="@")e=t[0],i="mailto:"+e;else{let a;do a=t[0],t[0]=this.rules.inline._backpedal.exec(t[0])?.[0]??"";while(a!==t[0]);e=t[0],t[1]==="www."?i="http://"+t[0]:i=t[0]}return{type:"link",raw:t[0],text:e,href:i,tokens:[{type:"text",raw:e,text:e}]}}}inlineText(n){let t=this.rules.inline.text.exec(n);if(t){let e=this.lexer.state.inRawBlock;return{type:"text",raw:t[0],text:t[0],escaped:e}}}},dn=class bd{tokens;options;state;inlineQueue;tokenizer;constructor(t){this.tokens=[],this.tokens.links=Object.create(null),this.options=t||Ri,this.options.tokenizer=this.options.tokenizer||new Hs,this.tokenizer=this.options.tokenizer,this.tokenizer.options=this.options,this.tokenizer.lexer=this,this.inlineQueue=[],this.state={inLink:!1,inRawBlock:!1,top:!0};let e={other:wt,block:$s.normal,inline:Yr.normal};this.options.pedantic?(e.block=$s.pedantic,e.inline=Yr.pedantic):this.options.gfm&&(e.block=$s.gfm,this.options.breaks?e.inline=Yr.breaks:e.inline=Yr.gfm),this.tokenizer.rules=e}static get rules(){return{block:$s,inline:Yr}}static lex(t,e){return new bd(e).lex(t)}static lexInline(t,e){return new bd(e).inlineTokens(t)}lex(t){t=t.replace(wt.carriageReturn,`
`),this.blockTokens(t,this.tokens);for(let e=0;e<this.inlineQueue.length;e++){let i=this.inlineQueue[e];this.inlineTokens(i.src,i.tokens)}return this.inlineQueue=[],this.tokens}blockTokens(t,e=[],i=!1){this.tokenizer.lexer=this,this.options.pedantic&&(t=t.replace(wt.tabCharGlobal,"    ").replace(wt.spaceLine,""));let a=1/0;for(;t;){if(t.length<a)a=t.length;else{this.infiniteLoopError(t.charCodeAt(0));break}let r;if(this.options.extensions?.block?.some(s=>(r=s.call({lexer:this},t,e))?(t=t.substring(r.raw.length),e.push(r),!0):!1))continue;if(r=this.tokenizer.space(t)){t=t.substring(r.raw.length);let s=e.at(-1);r.raw.length===1&&s!==void 0?s.raw+=`
`:e.push(r);continue}if(r=this.tokenizer.code(t)){t=t.substring(r.raw.length);let s=e.at(-1);s?.type==="paragraph"||s?.type==="text"?(s.raw+=(s.raw.endsWith(`
`)?"":`
`)+r.raw,s.text+=`
`+r.text,this.inlineQueue.at(-1).src=s.text):e.push(r);continue}if(r=this.tokenizer.fences(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.heading(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.hr(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.blockquote(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.list(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.html(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.def(t)){t=t.substring(r.raw.length);let s=e.at(-1);s?.type==="paragraph"||s?.type==="text"?(s.raw+=(s.raw.endsWith(`
`)?"":`
`)+r.raw,s.text+=`
`+r.raw,this.inlineQueue.at(-1).src=s.text):this.tokens.links[r.tag]||(this.tokens.links[r.tag]={href:r.href,title:r.title},e.push(r));continue}if(r=this.tokenizer.table(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.lheading(t)){t=t.substring(r.raw.length),e.push(r);continue}let o=t;if(this.options.extensions?.startBlock){let s=1/0,l=t.slice(1),c;this.options.extensions.startBlock.forEach(d=>{c=d.call({lexer:this},l),typeof c=="number"&&c>=0&&(s=Math.min(s,c))}),s<1/0&&s>=0&&(o=t.substring(0,s+1))}if(this.state.top&&(r=this.tokenizer.paragraph(o))){let s=e.at(-1);i&&s?.type==="paragraph"?(s.raw+=(s.raw.endsWith(`
`)?"":`
`)+r.raw,s.text+=`
`+r.text,this.inlineQueue.pop(),this.inlineQueue.at(-1).src=s.text):e.push(r),i=o.length!==t.length,t=t.substring(r.raw.length);continue}if(r=this.tokenizer.text(t)){t=t.substring(r.raw.length);let s=e.at(-1);s?.type==="text"?(s.raw+=(s.raw.endsWith(`
`)?"":`
`)+r.raw,s.text+=`
`+r.text,this.inlineQueue.pop(),this.inlineQueue.at(-1).src=s.text):e.push(r);continue}if(t){this.infiniteLoopError(t.charCodeAt(0));break}}return this.state.top=!0,e}inline(t,e=[]){return this.inlineQueue.push({src:t,tokens:e}),e}inlineTokens(t,e=[]){this.tokenizer.lexer=this;let i=t,a=null;if(this.tokens.links){let c=Object.keys(this.tokens.links);if(c.length>0)for(;(a=this.tokenizer.rules.inline.reflinkSearch.exec(i))!==null;)c.includes(a[0].slice(a[0].lastIndexOf("[")+1,-1))&&(i=i.slice(0,a.index)+"["+"a".repeat(a[0].length-2)+"]"+i.slice(this.tokenizer.rules.inline.reflinkSearch.lastIndex))}for(;(a=this.tokenizer.rules.inline.anyPunctuation.exec(i))!==null;)i=i.slice(0,a.index)+"++"+i.slice(this.tokenizer.rules.inline.anyPunctuation.lastIndex);let r;for(;(a=this.tokenizer.rules.inline.blockSkip.exec(i))!==null;)r=a[2]?a[2].length:0,i=i.slice(0,a.index+r)+"["+"a".repeat(a[0].length-r-2)+"]"+i.slice(this.tokenizer.rules.inline.blockSkip.lastIndex);i=this.options.hooks?.emStrongMask?.call({lexer:this},i)??i;let o=!1,s="",l=1/0;for(;t;){if(t.length<l)l=t.length;else{this.infiniteLoopError(t.charCodeAt(0));break}o||(s=""),o=!1;let c;if(this.options.extensions?.inline?.some(m=>(c=m.call({lexer:this},t,e))?(t=t.substring(c.raw.length),e.push(c),!0):!1))continue;if(c=this.tokenizer.escape(t)){t=t.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.tag(t)){t=t.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.link(t)){t=t.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.reflink(t,this.tokens.links)){t=t.substring(c.raw.length);let m=e.at(-1);c.type==="text"&&m?.type==="text"?(m.raw+=c.raw,m.text+=c.text):e.push(c);continue}if(c=this.tokenizer.emStrong(t,i,s)){t=t.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.codespan(t)){t=t.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.br(t)){t=t.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.del(t,i,s)){t=t.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.autolink(t)){t=t.substring(c.raw.length),e.push(c);continue}if(!this.state.inLink&&(c=this.tokenizer.url(t))){t=t.substring(c.raw.length),e.push(c);continue}let d=t;if(this.options.extensions?.startInline){let m=1/0,u=t.slice(1),f;this.options.extensions.startInline.forEach(g=>{f=g.call({lexer:this},u),typeof f=="number"&&f>=0&&(m=Math.min(m,f))}),m<1/0&&m>=0&&(d=t.substring(0,m+1))}if(c=this.tokenizer.inlineText(d)){t=t.substring(c.raw.length),c.raw.slice(-1)!=="_"&&(s=c.raw.slice(-1)),o=!0;let m=e.at(-1);m?.type==="text"?(m.raw+=c.raw,m.text+=c.text):e.push(c);continue}if(t){this.infiniteLoopError(t.charCodeAt(0));break}}return e}infiniteLoopError(t){let e="Infinite loop on byte: "+t;if(this.options.silent)console.error(e);else throw new Error(e)}},pi=class{options;parser;constructor(n){this.options=n||Ri}space(n){return""}code({text:n,lang:t,escaped:e}){let i=(t||"").match(wt.notSpaceStart)?.[0],a=n.replace(wt.endingNewline,"")+`
`;return i?'<pre><code class="language-'+Cn(i)+'">'+(e?a:Cn(a,!0))+`</code></pre>
`:"<pre><code>"+(e?a:Cn(a,!0))+`</code></pre>
`}blockquote({tokens:n}){return`<blockquote>
${this.parser.parse(n)}</blockquote>
`}html({text:n}){return n}def(n){return""}heading({tokens:n,depth:t}){return`<h${t}>${this.parser.parseInline(n)}</h${t}>
`}hr(n){return`<hr>
`}list(n){let t=n.ordered,e=n.start,i="";for(let o=0;o<n.items.length;o++){let s=n.items[o];i+=this.listitem(s)}let a=t?"ol":"ul",r=t&&e!==1?' start="'+e+'"':"";return"<"+a+r+`>
`+i+"</"+a+`>
`}listitem(n){return`<li>${this.parser.parse(n.tokens)}</li>
`}checkbox({checked:n}){return"<input "+(n?'checked="" ':"")+'disabled="" type="checkbox"> '}paragraph({tokens:n}){return`<p>${this.parser.parseInline(n)}</p>
`}table(n){let t="",e="";for(let a=0;a<n.header.length;a++)e+=this.tablecell(n.header[a]);t+=this.tablerow({text:e});let i="";for(let a=0;a<n.rows.length;a++){let r=n.rows[a];e="";for(let o=0;o<r.length;o++)e+=this.tablecell(r[o]);i+=this.tablerow({text:e})}return i&&(i=`<tbody>${i}</tbody>`),`<table>
<thead>
`+t+`</thead>
`+i+`</table>
`}tablerow({text:n}){return`<tr>
${n}</tr>
`}tablecell(n){let t=this.parser.parseInline(n.tokens),e=n.header?"th":"td";return(n.align?`<${e} align="${n.align}">`:`<${e}>`)+t+`</${e}>
`}strong({tokens:n}){return`<strong>${this.parser.parseInline(n)}</strong>`}em({tokens:n}){return`<em>${this.parser.parseInline(n)}</em>`}codespan({text:n}){return`<code>${Cn(n,!0)}</code>`}br(n){return"<br>"}del({tokens:n}){return`<del>${this.parser.parseInline(n)}</del>`}link({href:n,title:t,tokens:e}){let i=this.parser.parseInline(e),a=bf(n);if(a===null)return i;n=a;let r='<a href="'+n+'"';return t&&(r+=' title="'+Cn(t)+'"'),r+=">"+i+"</a>",r}image({href:n,title:t,text:e,tokens:i}){i&&(e=this.parser.parseInline(i,this.parser.textRenderer));let a=bf(n);if(a===null)return Cn(e);n=a;let r=`<img src="${n}" alt="${Cn(e)}"`;return t&&(r+=` title="${Cn(t)}"`),r+=">",r}text(n){return"tokens"in n&&n.tokens?this.parser.parseInline(n.tokens):"escaped"in n&&n.escaped?n.text:Cn(n.text)}},Dd=class{strong({text:n}){return n}em({text:n}){return n}codespan({text:n}){return n}del({text:n}){return n}html({text:n}){return n}text({text:n}){return n}link({text:n}){return""+n}image({text:n}){return""+n}br(){return""}checkbox({raw:n}){return n}},mn=class vd{options;renderer;textRenderer;constructor(t){this.options=t||Ri,this.options.renderer=this.options.renderer||new pi,this.renderer=this.options.renderer,this.renderer.options=this.options,this.renderer.parser=this,this.textRenderer=new Dd}static parse(t,e){return new vd(e).parse(t)}static parseInline(t,e){return new vd(e).parseInline(t)}parse(t){this.renderer.parser=this;let e="";for(let i=0;i<t.length;i++){let a=t[i];if(this.options.extensions?.renderers?.[a.type]){let o=a,s=this.options.extensions.renderers[o.type].call({parser:this},o);if(s!==!1||!["space","hr","heading","code","table","blockquote","list","html","def","paragraph","text"].includes(o.type)){e+=s||"";continue}}let r=a;switch(r.type){case"space":{e+=this.renderer.space(r);break}case"hr":{e+=this.renderer.hr(r);break}case"heading":{e+=this.renderer.heading(r);break}case"code":{e+=this.renderer.code(r);break}case"table":{e+=this.renderer.table(r);break}case"blockquote":{e+=this.renderer.blockquote(r);break}case"list":{e+=this.renderer.list(r);break}case"checkbox":{e+=this.renderer.checkbox(r);break}case"html":{e+=this.renderer.html(r);break}case"def":{e+=this.renderer.def(r);break}case"paragraph":{e+=this.renderer.paragraph(r);break}case"text":{e+=this.renderer.text(r);break}default:{let o='Token with "'+r.type+'" type was not found.';if(this.options.silent)return console.error(o),"";throw new Error(o)}}}return e}parseInline(t,e=this.renderer){this.renderer.parser=this;let i="";for(let a=0;a<t.length;a++){let r=t[a];if(this.options.extensions?.renderers?.[r.type]){let s=this.options.extensions.renderers[r.type].call({parser:this},r);if(s!==!1||!["escape","html","link","image","strong","em","codespan","br","del","text"].includes(r.type)){i+=s||"";continue}}let o=r;switch(o.type){case"escape":{i+=e.text(o);break}case"html":{i+=e.html(o);break}case"link":{i+=e.link(o);break}case"image":{i+=e.image(o);break}case"checkbox":{i+=e.checkbox(o);break}case"strong":{i+=e.strong(o);break}case"em":{i+=e.em(o);break}case"codespan":{i+=e.codespan(o);break}case"br":{i+=e.br(o);break}case"del":{i+=e.del(o);break}case"text":{i+=e.text(o);break}default:{let s='Token with "'+o.type+'" type was not found.';if(this.options.silent)return console.error(s),"";throw new Error(s)}}}return i}},Xr=class{options;block;constructor(n){this.options=n||Ri}static passThroughHooks=new Set(["preprocess","postprocess","processAllTokens","emStrongMask"]);static passThroughHooksRespectAsync=new Set(["preprocess","postprocess","processAllTokens"]);preprocess(n){return n}postprocess(n){return n}processAllTokens(n){return n}emStrongMask(n){return n}provideLexer(n=this.block){return n?dn.lex:dn.lexInline}provideParser(n=this.block){return n?mn.parse:mn.parseInline}},Px=class{defaults=_d();options=this.setOptions;parse=this.parseMarkdown(!0);parseInline=this.parseMarkdown(!1);Parser=mn;Renderer=pi;TextRenderer=Dd;Lexer=dn;Tokenizer=Hs;Hooks=Xr;constructor(...n){this.use(...n)}walkTokens(n,t){let e=[];for(let i of n)switch(e=e.concat(t.call(this,i)),i.type){case"table":{let a=i;for(let r of a.header)e=e.concat(this.walkTokens(r.tokens,t));for(let r of a.rows)for(let o of r)e=e.concat(this.walkTokens(o.tokens,t));break}case"list":{let a=i;e=e.concat(this.walkTokens(a.items,t));break}default:{let a=i;this.defaults.extensions?.childTokens?.[a.type]?this.defaults.extensions.childTokens[a.type].forEach(r=>{let o=a[r].flat(1/0);e=e.concat(this.walkTokens(o,t))}):a.tokens&&(e=e.concat(this.walkTokens(a.tokens,t)))}}return e}use(...n){let t=this.defaults.extensions||{renderers:{},childTokens:{}};return n.forEach(e=>{let i=N({},e);if(i.async=this.defaults.async||i.async||!1,e.extensions&&(e.extensions.forEach(a=>{if(!a.name)throw new Error("extension name required");if("renderer"in a){let r=t.renderers[a.name];r?t.renderers[a.name]=function(...o){let s=a.renderer.apply(this,o);return s===!1&&(s=r.apply(this,o)),s}:t.renderers[a.name]=a.renderer}if("tokenizer"in a){if(!a.level||a.level!=="block"&&a.level!=="inline")throw new Error("extension level must be 'block' or 'inline'");let r=t[a.level];r?r.unshift(a.tokenizer):t[a.level]=[a.tokenizer],a.start&&(a.level==="block"?t.startBlock?t.startBlock.push(a.start):t.startBlock=[a.start]:a.level==="inline"&&(t.startInline?t.startInline.push(a.start):t.startInline=[a.start]))}"childTokens"in a&&a.childTokens&&(t.childTokens[a.name]=a.childTokens)}),i.extensions=t),e.renderer){let a=this.defaults.renderer||new pi(this.defaults);for(let r in e.renderer){if(!(r in a))throw new Error(`renderer '${r}' does not exist`);if(["options","parser"].includes(r))continue;let o=r,s=e.renderer[o],l=a[o];a[o]=(...c)=>{let d=s.apply(a,c);return d===!1&&(d=l.apply(a,c)),d||""}}i.renderer=a}if(e.tokenizer){let a=this.defaults.tokenizer||new Hs(this.defaults);for(let r in e.tokenizer){if(!(r in a))throw new Error(`tokenizer '${r}' does not exist`);if(["options","rules","lexer"].includes(r))continue;let o=r,s=e.tokenizer[o],l=a[o];a[o]=(...c)=>{let d=s.apply(a,c);return d===!1&&(d=l.apply(a,c)),d}}i.tokenizer=a}if(e.hooks){let a=this.defaults.hooks||new Xr;for(let r in e.hooks){if(!(r in a))throw new Error(`hook '${r}' does not exist`);if(["options","block"].includes(r))continue;let o=r,s=e.hooks[o],l=a[o];Xr.passThroughHooks.has(r)?a[o]=c=>{if(this.defaults.async&&Xr.passThroughHooksRespectAsync.has(r))return(async()=>{let m=await s.call(a,c);return l.call(a,m)})();let d=s.call(a,c);return l.call(a,d)}:a[o]=(...c)=>{if(this.defaults.async)return(async()=>{let m=await s.apply(a,c);return m===!1&&(m=await l.apply(a,c)),m})();let d=s.apply(a,c);return d===!1&&(d=l.apply(a,c)),d}}i.hooks=a}if(e.walkTokens){let a=this.defaults.walkTokens,r=e.walkTokens;i.walkTokens=function(o){let s=[];return s.push(r.call(this,o)),a&&(s=s.concat(a.call(this,o))),s}}this.defaults=N(N({},this.defaults),i)}),this}setOptions(n){return this.defaults=N(N({},this.defaults),n),this}lexer(n,t){return dn.lex(n,t??this.defaults)}parser(n,t){return mn.parse(n,t??this.defaults)}parseMarkdown(n){return(t,e)=>{let i=N({},e),a=N(N({},this.defaults),i),r=this.onError(!!a.silent,!!a.async);if(this.defaults.async===!0&&i.async===!1)return r(new Error("marked(): The async option was set to true by an extension. Remove async: false from the parse options object to return a Promise."));if(typeof t>"u"||t===null)return r(new Error("marked(): input parameter is undefined or null"));if(typeof t!="string")return r(new Error("marked(): input parameter is of type "+Object.prototype.toString.call(t)+", string expected"));if(a.hooks&&(a.hooks.options=a,a.hooks.block=n),a.async)return(async()=>{let o=a.hooks?await a.hooks.preprocess(t):t,s=await(a.hooks?await a.hooks.provideLexer(n):n?dn.lex:dn.lexInline)(o,a),l=a.hooks?await a.hooks.processAllTokens(s):s;a.walkTokens&&await Promise.all(this.walkTokens(l,a.walkTokens));let c=await(a.hooks?await a.hooks.provideParser(n):n?mn.parse:mn.parseInline)(l,a);return a.hooks?await a.hooks.postprocess(c):c})().catch(r);try{a.hooks&&(t=a.hooks.preprocess(t));let o=(a.hooks?a.hooks.provideLexer(n):n?dn.lex:dn.lexInline)(t,a);a.hooks&&(o=a.hooks.processAllTokens(o)),a.walkTokens&&this.walkTokens(o,a.walkTokens);let s=(a.hooks?a.hooks.provideParser(n):n?mn.parse:mn.parseInline)(o,a);return a.hooks&&(s=a.hooks.postprocess(s)),s}catch(o){return r(o)}}}onError(n,t){return e=>{if(e.message+=`
Please report this to https://github.com/markedjs/marked.`,n){let i="<p>An error occurred:</p><pre>"+Cn(e.message+"",!0)+"</pre>";return t?Promise.resolve(i):i}if(t)return Promise.reject(e);throw e}}},Oi=new Px;function xe(n,t){return Oi.parse(n,t)}xe.options=xe.setOptions=function(n){return Oi.setOptions(n),xe.defaults=Oi.defaults,xf(xe.defaults),xe};xe.getDefaults=_d;xe.defaults=Ri;xe.use=function(...n){return Oi.use(...n),xe.defaults=Oi.defaults,xf(xe.defaults),xe};xe.walkTokens=function(n,t){return Oi.walkTokens(n,t)};xe.parseInline=Oi.parseInline;xe.Parser=mn;xe.parser=mn.parse;xe.Renderer=pi;xe.TextRenderer=Dd;xe.Lexer=dn;xe.lexer=dn.lex;xe.Tokenizer=Hs;xe.Hooks=Xr;xe.parse=xe;var fR=xe.options,gR=xe.setOptions,bR=xe.use,vR=xe.walkTokens,_R=xe.parseInline;var yR=mn.parse,xR=dn.lex;var Fx="Copy",Lx="Copied",Bx=(()=>{class n{constructor(){this._buttonClick$=new z,this.copied=uf(this._buttonClick$.pipe(Gt(()=>It(Ue(!0),Xu(3e3).pipe(xr(!1)))),wr(),Fn(1))),this.copiedText=cn(()=>this.copied()?Lx:Fx)}onCopyToClipboardClick(){this._buttonClick$.next()}static{this.\u0275fac=function(i){return new(i||n)}}static{this.\u0275cmp=T({type:n,selectors:[["markdown-clipboard"]],decls:2,vars:3,consts:[[1,"markdown-clipboard-button",3,"click"]],template:function(i,a){i&1&&(Ze(0,"button",0),bs("click",function(){return a.onCopyToClipboardClick()}),S(1),Je()),i&2&&(J("copied",a.copied()),h(),Ge(a.copiedText()))},encapsulation:2})}}return n})(),jx=new I("CLIPBOARD_OPTIONS"),Vx=new I("KATEX_OPTIONS");var $x=new I("MARKED_EXTENSIONS"),zx=new I("MARKED_OPTIONS"),Hx=new I("MERMAID_OPTIONS"),Ux=new I("SANITIZE");function qx(n){return typeof n=="function"}var Gx="[ngx-markdown] When using the `emoji` attribute you *have to* include Emoji-Toolkit files to `angular.json` or use imports. See README for more information";var Wx="[ngx-markdown] When using the `katex` attribute you *have to* include the `marked-katex-extension` package and its dependencies. See README for more information",Kx="[ngx-markdown] When using the `mermaid` attribute you *have to* include Mermaid files to `angular.json` or use imports. See README for more information",Yx="[ngx-markdown] When using the `clipboard` attribute you *have to* include Clipboard files to `angular.json` or use imports. See README for more information",Xx="[ngx-markdown] When using the `clipboard` attribute you *have to* provide the `viewContainerRef` parameter to `MarkdownService.render()` function",Zx="[ngx-markdown] When using the `src` attribute you *have to* pass the `HttpClient` as a parameter of the `forRoot` method. See README for more information";var Af=(()=>{class n{get options(){return this._options}set options(e){this._options=N(N({},this.DEFAULT_MARKED_OPTIONS),e)}get renderer(){return this.options.renderer}set renderer(e){this.options.renderer=e}constructor(){this.clipboardOptions=p(jx,{optional:!0}),this.extensions=p($x,{optional:!0}),this.http=p(Xt,{optional:!0}),this.mermaidOptions=p(Hx,{optional:!0}),this.katexOptions=p(Vx,{optional:!0}),this.platform=p(ni),this.sanitize=p(Ux,{optional:!0}),this.sanitizer=p(Vn),this.katexGate={enabled:!1},this.markedKatex=null,this.DEFAULT_MARKED_OPTIONS={renderer:new pi},this.DEFAULT_MERMAID_OPTIONS={startOnLoad:!1},this.DEFAULT_CLIPBOARD_OPTIONS={buttonComponent:void 0},this.DEFAULT_PARSE_OPTIONS={decodeHtml:!1,inline:!1,emoji:!1,katex:!1,katexOptions:void 0,mermaid:!1,markedOptions:void 0,disableSanitizer:!1},this.DEFAULT_RENDER_OPTIONS={clipboard:!1,clipboardOptions:void 0,mermaid:!1,mermaidOptions:void 0},this.DEFAULT_SECURITY_CONTEXT=dt.HTML,this._options=null,this._reload$=new z,this.reload$=this._reload$.asObservable(),this.options=p(zx,{optional:!0})}async parse(e,i=this.DEFAULT_PARSE_OPTIONS){let{decodeHtml:a,inline:r,emoji:o,katex:s,katexOptions:l,mermaid:c,disableSanitizer:d}=i;this.katexGate={enabled:!!i.katex};let m=N(N({},this.options),i.markedOptions),u=m.renderer||this.renderer||new pi;this.extensions&&(this.renderer=this.extendsRendererForExtensions(u)),s&&(this.renderer=await this.extendsRendererForKatex(u,l)),c&&(this.renderer=this.extendsRendererForMermaid(u));let f=this.trimIndentation(e),g=a?this.decodeHtml(f):f,b=o?this.parseEmoji(g):g,_=this.parseMarked(b,m,r);return d?_:this.sanitizeHtml(_)}render(e,i=this.DEFAULT_RENDER_OPTIONS,a){let{clipboard:r,clipboardOptions:o,mermaid:s,mermaidOptions:l}=i;s&&this.renderMermaid(e,N(N(N({},this.DEFAULT_MERMAID_OPTIONS),this.mermaidOptions),l)),r&&this.renderClipboard(e,a,N(N(N({},this.DEFAULT_CLIPBOARD_OPTIONS),this.clipboardOptions),o)),this.highlight(e)}reload(){this._reload$.next()}getSource(e){if(!this.http)throw new Error(Zx);return this.http.get(e,{responseType:"text"}).pipe(De(i=>this.handleExtension(e,i)))}highlight(e){if(!ci(this.platform)||typeof Prism>"u"||typeof Prism.highlightAllUnder>"u")return;e||(e=document);let i=e.querySelectorAll('pre code:not([class*="language-"])');Array.prototype.forEach.call(i,a=>a.classList.add("language-none")),Prism.highlightAllUnder(e)}decodeHtml(e){if(!ci(this.platform))return e;let i=document.createElement("textarea");return i.innerHTML=e,i.value}extendsRendererForExtensions(e){let i=e;return i.\u0275NgxMarkdownRendererExtendedForExtensions===!0||(this.extensions&&this.extensions.length>0&&xe.use(...this.extensions),i.\u0275NgxMarkdownRendererExtendedForExtensions=!0),e}async extendsRendererForKatex(e,i){let a=this.katexGate,r=e;if(r.\u0275NgxMarkdownRendererExtendedForKatex===!0)return e;if(this.markedKatex??=await import("marked-katex-extension").then(d=>d.default).catch(()=>null),!this.markedKatex)throw new Error(Wx);let o=N(N({},this.katexOptions),i),s=this.markedKatex(o),l=s.extensions?.map(d=>{let u=d.tokenizer;return Ee(N({},d),{tokenizer(f){if(!(!a.enabled||!u))return u.call(this,f,[])}})}),c=Ee(N({},s),{extensions:l});return xe.use(c),r.\u0275NgxMarkdownRendererExtendedForKatex=!0,e}extendsRendererForMermaid(e){let i=e;if(i.\u0275NgxMarkdownRendererExtendedForMermaid===!0)return e;let a=e.code;return e.code=r=>r.lang==="mermaid"?`<div class="mermaid">${r.text}</div>`:a(r),i.\u0275NgxMarkdownRendererExtendedForMermaid=!0,e}handleExtension(e,i){let a=e.lastIndexOf("://"),r=a>-1?e.substring(a+4):e,o=r.lastIndexOf("/"),s=o>-1?r.substring(o+1).split("?")[0]:"",l=s.lastIndexOf("."),c=l>-1?s.substring(l+1):"";return c&&c!=="md"?"```"+c+`
`+i+"\n```":i}parseMarked(e,i,a=!1){if(i.renderer){let r=N({},i.renderer);delete r.\u0275NgxMarkdownRendererExtendedForExtensions,delete r.\u0275NgxMarkdownRendererExtendedForKatex,delete r.\u0275NgxMarkdownRendererExtendedForMermaid,delete i.renderer,xe.use({renderer:r})}return a?xe.parseInline(e,i):xe.parse(e,i)}parseEmoji(e){if(!ci(this.platform))return e;if(typeof joypixels>"u"||typeof joypixels.shortnameToUnicode>"u")throw new Error(Gx);return joypixels.shortnameToUnicode(e)}renderClipboard(e,i,a){if(!ci(this.platform))return;if(typeof ClipboardJS>"u")throw new Error(Yx);if(!i)throw new Error(Xx);let{buttonComponent:r,buttonTemplate:o}=a,s=e.querySelectorAll("pre");for(let l=0;l<s.length;l++){let c=s.item(l),d=document.createElement("div");d.style.position="relative",c.parentNode.insertBefore(d,c),d.appendChild(c);let m=document.createElement("div");m.classList.add("markdown-clipboard-toolbar"),m.style.position="absolute",m.style.top=".5em",m.style.right=".5em",m.style.zIndex="1",d.insertAdjacentElement("beforeend",m),d.onmouseenter=()=>m.classList.add("hover"),d.onmouseleave=()=>m.classList.remove("hover");let u;if(r){let g=i.createComponent(r);u=g.hostView,g.changeDetectorRef.markForCheck()}else if(o)u=i.createEmbeddedView(o);else{let g=i.createComponent(Bx);u=g.hostView,g.changeDetectorRef.markForCheck()}let f;u.rootNodes.forEach(g=>{m.appendChild(g),f=new ClipboardJS(g,{text:()=>c.innerText})}),u.onDestroy(()=>f.destroy())}}renderMermaid(e,i=this.DEFAULT_MERMAID_OPTIONS){if(!ci(this.platform))return;if(typeof mermaid>"u"||typeof mermaid.initialize>"u")throw new Error(Kx);let a=e.querySelectorAll(".mermaid");a.length!==0&&(mermaid.initialize(i),mermaid.run({nodes:a}))}trimIndentation(e){if(!e)return"";let i;return e.split(`
`).map(a=>{let r=i;return a.length>0&&(r=isNaN(r)?a.search(/\S|$/):Math.min(a.search(/\S|$/),r)),isNaN(i)&&(i=r),r?a.substring(r):a}).join(`
`)}async sanitizeHtml(e){return qx(this.sanitize)?this.sanitize(await e):this.sanitize!==dt.NONE?this.sanitizer.sanitize(this.sanitize??this.DEFAULT_SECURITY_CONTEXT,e)??"":e}static{this.\u0275fac=function(i){return new(i||n)}}static{this.\u0275prov=te({token:n,factory:n.\u0275fac})}}return n})(),Md=(function(n){return n.CommandLine="command-line",n.LineHighlight="line-highlight",n.LineNumbers="line-numbers",n})(Md||{}),En=(()=>{class n{constructor(){this.element=p(X),this.markdownService=p(Af),this.viewContainerRef=p(xn),this.error=new Te,this.load=new Te,this.ready=new Te,this._clipboard=!1,this._commandLine=!1,this._disableSanitizer=!1,this._emoji=!1,this._inline=!1,this._katex=!1,this._lineHighlight=!1,this._lineNumbers=!1,this._mermaid=!1,this.destroyed$=new z}get disableSanitizer(){return this._disableSanitizer}set disableSanitizer(e){this._disableSanitizer=this.coerceBooleanProperty(e)}get inline(){return this._inline}set inline(e){this._inline=this.coerceBooleanProperty(e)}get clipboard(){return this._clipboard}set clipboard(e){this._clipboard=this.coerceBooleanProperty(e)}get emoji(){return this._emoji}set emoji(e){this._emoji=this.coerceBooleanProperty(e)}get katex(){return this._katex}set katex(e){this._katex=this.coerceBooleanProperty(e)}get mermaid(){return this._mermaid}set mermaid(e){this._mermaid=this.coerceBooleanProperty(e)}get lineHighlight(){return this._lineHighlight}set lineHighlight(e){this._lineHighlight=this.coerceBooleanProperty(e)}get lineNumbers(){return this._lineNumbers}set lineNumbers(e){this._lineNumbers=this.coerceBooleanProperty(e)}get commandLine(){return this._commandLine}set commandLine(e){this._commandLine=this.coerceBooleanProperty(e)}ngOnChanges(){this.loadContent()}loadContent(){if(this.data!=null){this.handleData();return}if(this.src!=null){this.handleSrc();return}}ngAfterViewInit(){!this.data&&!this.src&&this.handleTransclusion(),this.markdownService.reload$.pipe(Ie(this.destroyed$)).subscribe(()=>this.loadContent())}ngOnDestroy(){this.destroyed$.next(),this.destroyed$.complete()}async render(e,i=!1){let a={decodeHtml:i,inline:this.inline,emoji:this.emoji,katex:this.katex,katexOptions:this.katexOptions,mermaid:this.mermaid,disableSanitizer:this.disableSanitizer},r={clipboard:this.clipboard,clipboardOptions:this.getClipboardOptions(),mermaid:this.mermaid,mermaidOptions:this.mermaidOptions},o=await this.markdownService.parse(e,a);this.element.nativeElement.innerHTML=o,this.handlePlugins(),this.markdownService.render(this.element.nativeElement,r,this.viewContainerRef),this.ready.emit()}coerceBooleanProperty(e){return e!=null&&`${String(e)}`!="false"}getClipboardOptions(){if(this.clipboardButtonComponent||this.clipboardButtonTemplate)return{buttonComponent:this.clipboardButtonComponent,buttonTemplate:this.clipboardButtonTemplate}}handleData(){this.render(this.data)}handleSrc(){this.markdownService.getSource(this.src).subscribe({next:e=>{this.render(e).then(()=>{this.load.emit(e)})},error:e=>this.error.emit(e)})}handleTransclusion(){this.render(this.element.nativeElement.innerHTML,!0)}handlePlugins(){this.commandLine&&(this.setPluginClass(this.element.nativeElement,Md.CommandLine),this.setPluginOptions(this.element.nativeElement,{dataFilterOutput:this.filterOutput,dataHost:this.host,dataPrompt:this.prompt,dataOutput:this.output,dataUser:this.user})),this.lineHighlight&&this.setPluginOptions(this.element.nativeElement,{dataLine:this.line,dataLineOffset:this.lineOffset}),this.lineNumbers&&(this.setPluginClass(this.element.nativeElement,Md.LineNumbers),this.setPluginOptions(this.element.nativeElement,{dataStart:this.start}))}setPluginClass(e,i){let a=e.querySelectorAll("pre");for(let r=0;r<a.length;r++){let o=i instanceof Array?i:[i];a.item(r).classList.add(...o)}}setPluginOptions(e,i){let a=e.querySelectorAll("pre");for(let r=0;r<a.length;r++)Object.keys(i).forEach(o=>{let s=i[o];if(s){let l=this.toLispCase(o);a.item(r).setAttribute(l,s.toString())}})}toLispCase(e){let i=e.match(/([A-Z])/g);if(!i)return e;let a=e.toString();for(let r=0,o=i.length;r<o;r++)a=a.replace(new RegExp(i[r]),"-"+i[r].toLowerCase());return a.slice(0,1)==="-"&&(a=a.slice(1)),a}static{this.\u0275fac=function(i){return new(i||n)}}static{this.\u0275cmp=(function(){return T({type:n,selectors:[["markdown"],["","markdown",""]],inputs:{data:"data",src:"src",disableSanitizer:"disableSanitizer",inline:"inline",clipboard:"clipboard",clipboardButtonComponent:"clipboardButtonComponent",clipboardButtonTemplate:"clipboardButtonTemplate",emoji:"emoji",katex:"katex",katexOptions:"katexOptions",mermaid:"mermaid",mermaidOptions:"mermaidOptions",lineHighlight:"lineHighlight",line:"line",lineOffset:"lineOffset",lineNumbers:"lineNumbers",start:"start",commandLine:"commandLine",filterOutput:"filterOutput",host:"host",prompt:"prompt",output:"output",user:"user"},outputs:{error:"error",load:"load",ready:"ready"},features:[_t],ngContentSelectors:["*"],decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},encapsulation:2})})()}}return n})();function Nd(n){return[Af,n?.loader??[],n?.clipboardOptions??[],n?.katexOptions??[],n?.markedOptions??[],n?.mermaidOptions??[],n?.markedExtensions??[],n?.sanitize??[]]}var Dn=(()=>{class n{static forRoot(e){return{ngModule:n,providers:[Nd(e)]}}static forChild(){return{ngModule:n}}static{this.\u0275fac=function(i){return new(i||n)}}static{this.\u0275mod=$({type:n})}static{this.\u0275inj=V({})}}return n})();var Ws=new WeakMap,ct=(()=>{class n{_appRef;_injector=p(ce);_environmentInjector=p(Ln);load(e){let i=this._appRef=this._appRef||this._injector.get(ri),a=Ws.get(i);a||(a={loaders:new Set,refs:[]},Ws.set(i,a),i.onDestroy(()=>{Ws.get(i)?.refs.forEach(r=>r.destroy()),Ws.delete(i)})),a.loaders.has(e)||(a.loaders.add(e),a.refs.push(ws(e,{environmentInjector:this._environmentInjector})))}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var Ea=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275cmp=T({type:n,selectors:[["ng-component"]],exportAs:["cdkVisuallyHidden"],decls:0,vars:0,template:function(i,a){},styles:[`.cdk-visually-hidden {
  border: 0;
  clip: rect(0 0 0 0);
  height: 1px;
  margin: -1px;
  overflow: hidden;
  padding: 0;
  position: absolute;
  width: 1px;
  white-space: nowrap;
  outline: 0;
  -webkit-appearance: none;
  -moz-appearance: none;
  left: 0;
}
[dir=rtl] .cdk-visually-hidden {
  left: auto;
  right: 0;
}
`],encapsulation:2})}return n})(),Ks;function Qx(){if(Ks===void 0&&(Ks=null,typeof window<"u")){let n=window;if(n.trustedTypes!==void 0)try{Ks=n.trustedTypes.createPolicy("angular#components",{createHTML:t=>t})}catch(t){console.error(t)}}return Ks}function Pi(n){return Qx()?.createHTML(n)||n}function Tf(n,t,e){let i=e.sanitize(dt.HTML,t);n.innerHTML=Pi(i||"")}function Of(n){return Error(`Unable to find icon with the name "${n}"`)}function Jx(){return Error("Could not find HttpClient for use with Angular Material icons. Please add provideHttpClient() to your providers.")}function Rf(n){return Error(`The URL provided to MatIconRegistry was not trusted as a resource URL via Angular's DomSanitizer. Attempted URL was "${n}".`)}function Pf(n){return Error(`The literal provided to MatIconRegistry was not trusted as safe HTML by Angular's DomSanitizer. Attempted literal was "${n}".`)}var $n=class{url;svgText;options;svgElement=null;constructor(t,e,i){this.url=t,this.svgText=e,this.options=i}},Xs=(()=>{class n{_httpClient;_sanitizer;_errorHandler;_document;_svgIconConfigs=new Map;_iconSetConfigs=new Map;_cachedIconsByUrl=new Map;_inProgressUrlFetches=new Map;_fontCssClassesByAlias=new Map;_resolvers=[];_defaultFontSetClass;constructor(e,i,a,r){this._httpClient=e,this._sanitizer=i,this._errorHandler=r,this._document=a}addSvgIcon(e,i,a){return this.addSvgIconInNamespace("",e,i,a)}addSvgIconLiteral(e,i,a){return this.addSvgIconLiteralInNamespace("",e,i,a)}addSvgIconInNamespace(e,i,a,r){return this._addSvgIconConfig(e,i,new $n(a,null,r))}addSvgIconResolver(e){return this._resolvers.push(e),this}addSvgIconLiteralInNamespace(e,i,a,r){let o=this._sanitizer.sanitize(dt.HTML,a);if(!o)throw Pf(a);let s=Pi(o);return this._addSvgIconConfig(e,i,new $n("",s,r))}addSvgIconSet(e,i){return this.addSvgIconSetInNamespace("",e,i)}addSvgIconSetLiteral(e,i){return this.addSvgIconSetLiteralInNamespace("",e,i)}addSvgIconSetInNamespace(e,i,a){return this._addSvgIconSetConfig(e,new $n(i,null,a))}addSvgIconSetLiteralInNamespace(e,i,a){let r=this._sanitizer.sanitize(dt.HTML,i);if(!r)throw Pf(i);let o=Pi(r);return this._addSvgIconSetConfig(e,new $n("",o,a))}registerFontClassAlias(e,i=e){return this._fontCssClassesByAlias.set(e,i),this}classNameForFontAlias(e){return this._fontCssClassesByAlias.get(e)||e}setDefaultFontSetClass(...e){return this._defaultFontSetClass=e,this}getDefaultFontSetClass(){return this._defaultFontSetClass??=tw(this._document),this._defaultFontSetClass}getSvgIconFromUrl(e){let i=this._sanitizer.sanitize(dt.RESOURCE_URL,e);if(!i)throw Rf(e);let a=this._cachedIconsByUrl.get(i);return a?Ue(Ys(a)):this._loadSvgIconFromConfig(new $n(e,null)).pipe(ti(r=>this._cachedIconsByUrl.set(i,r)),De(r=>Ys(r)))}getNamedSvgIcon(e,i=""){let a=Ff(i,e),r=this._svgIconConfigs.get(a);if(r)return this._getSvgFromConfig(r);if(r=this._getIconConfigFromResolvers(i,e),r)return this._svgIconConfigs.set(a,r),this._getSvgFromConfig(r);let o=this._iconSetConfigs.get(i);return o?this._getSvgFromIconSetConfigs(e,o):Gu(Of(a))}ngOnDestroy(){this._resolvers=[],this._svgIconConfigs.clear(),this._iconSetConfigs.clear(),this._cachedIconsByUrl.clear()}_getSvgFromConfig(e){return e.svgText?Ue(Ys(this._svgElementFromConfig(e))):this._loadSvgIconFromConfig(e).pipe(De(i=>Ys(i)))}_getSvgFromIconSetConfigs(e,i){let a=this._extractIconWithNameFromAnySet(e,i);if(a)return Ue(a);let r=i.filter(o=>!o.svgText).map(o=>this._loadSvgIconSetFromConfig(o).pipe(ls(s=>{let c=`Loading icon set URL: ${this._sanitizer.sanitize(dt.RESOURCE_URL,o.url)} failed: ${s.message}`;return this._errorHandler.handleError(new Error(c)),Ue(null)})));return Yu(r).pipe(De(()=>{let o=this._extractIconWithNameFromAnySet(e,i);if(!o)throw Of(e);return o}))}_extractIconWithNameFromAnySet(e,i){for(let a=i.length-1;a>=0;a--){let r=i[a];if(r.svgText&&r.svgText.toString().indexOf(e)>-1){let o=this._svgElementFromConfig(r),s=this._extractSvgIconFromSet(o,e,r.options);if(s)return s}}return null}_loadSvgIconFromConfig(e){return this._fetchIcon(e).pipe(ti(i=>e.svgText=i),De(()=>this._svgElementFromConfig(e)))}_loadSvgIconSetFromConfig(e){return e.svgText?Ue(null):this._fetchIcon(e).pipe(ti(i=>e.svgText=i))}_extractSvgIconFromSet(e,i,a){let r=e.querySelector(`[id="${i}"]`);if(!r)return null;let o=r.cloneNode(!0);if(o.removeAttribute("id"),o.nodeName.toLowerCase()==="svg")return this._setSvgAttributes(o,a);if(o.nodeName.toLowerCase()==="symbol")return this._setSvgAttributes(this._toSvgElement(o),a);let s=this._svgElementFromString(Pi("<svg></svg>"));return s.appendChild(o),this._setSvgAttributes(s,a)}_svgElementFromString(e){let i=this._document.createElement("DIV");i.innerHTML=e;let a=i.querySelector("svg");if(!a)throw Error("<svg> tag not found");return a}_toSvgElement(e){let i=this._svgElementFromString(Pi("<svg></svg>")),a=e.attributes;for(let r=0;r<a.length;r++){let{name:o,value:s}=a[r];o!=="id"&&i.setAttribute(o,s)}for(let r=0;r<e.childNodes.length;r++)e.childNodes[r].nodeType===this._document.ELEMENT_NODE&&i.appendChild(e.childNodes[r].cloneNode(!0));return i}_setSvgAttributes(e,i){return e.setAttribute("fit",""),e.setAttribute("height","100%"),e.setAttribute("width","100%"),e.setAttribute("preserveAspectRatio","xMidYMid meet"),e.setAttribute("focusable","false"),i&&i.viewBox&&e.setAttribute("viewBox",i.viewBox),e}_fetchIcon(e){let{url:i,options:a}=e,r=a?.withCredentials??!1;if(!this._httpClient)throw Jx();if(i==null)throw Error(`Cannot fetch icon from URL "${i}".`);let o=this._sanitizer.sanitize(dt.RESOURCE_URL,i);if(!o)throw Rf(i);let s=this._inProgressUrlFetches.get(o);if(s)return s;let l=this._httpClient.get(o,{responseType:"text",withCredentials:r}).pipe(De(c=>Pi(c)),Sr(()=>this._inProgressUrlFetches.delete(o)),ds());return this._inProgressUrlFetches.set(o,l),l}_addSvgIconConfig(e,i,a){return this._svgIconConfigs.set(Ff(e,i),a),this}_addSvgIconSetConfig(e,i){let a=this._iconSetConfigs.get(e);return a?a.push(i):this._iconSetConfigs.set(e,[i]),this}_svgElementFromConfig(e){if(!e.svgElement){let i=this._svgElementFromString(e.svgText);this._setSvgAttributes(i,e.options),e.svgElement=i}return e.svgElement}_getIconConfigFromResolvers(e,i){for(let a=0;a<this._resolvers.length;a++){let r=this._resolvers[a](i,e);if(r)return ew(r)?new $n(r.url,null,r.options):new $n(r,null)}}static \u0275fac=function(i){return new(i||n)(B(Xt,8),B(Vn),B(Y,8),B(Di))};static \u0275prov=te({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();function Ys(n){return n.cloneNode(!0)}function Ff(n,t){return n+":"+t}function ew(n){return!!(n.url&&n.options)}function tw(n){let t=null,e=!1;return n.fonts&&typeof n.fonts.forEach=="function"&&n.fonts.forEach(i=>{let a=i.family.replace(/['"]/g,"").trim().toLowerCase();(a==="material icons"||a.startsWith("material icons "))&&(e=!0),a.startsWith("material symbols rounded")?t="rounded":a.startsWith("material symbols sharp")?t="sharp":a.startsWith("material symbols")&&(t="outlined")}),[t&&!e?`material-symbols-${t}`:"material-icons","mat-ligature-font"]}var nw=new I("cdk-dir-doc",{providedIn:"root",factory:()=>p(Y)}),iw=/^(ar|ckb|dv|he|iw|fa|nqo|ps|sd|ug|ur|yi|.*[-_](Adlm|Arab|Hebr|Nkoo|Rohg|Thaa))(?!.*[-_](Latn|Cyrl)($|-|_))($|-|_)/i;function Lf(n){let t=n?.toLowerCase()||"";return t==="auto"&&typeof navigator<"u"&&navigator?.language?iw.test(navigator.language)?"rtl":"ltr":t==="rtl"?"rtl":"ltr"}var Tt=(()=>{class n{get value(){return this.valueSignal()}valueSignal=ne("ltr");change=new Te;constructor(){let e=p(nw,{optional:!0});if(e){let i=e.body?e.body.dir:null,a=e.documentElement?e.documentElement.dir:null;this.valueSignal.set(Lf(i||a||"ltr"))}}ngOnDestroy(){this.change.complete()}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var me=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({})}return n})();var aw=new I("MAT_ICON_DEFAULT_OPTIONS"),rw=new I("mat-icon-location",{providedIn:"root",factory:()=>{let n=p(Y),t=n?n.location:null;return{getPathname:()=>t?t.pathname+t.search:""}}}),Bf=["clip-path","color-profile","src","cursor","fill","filter","marker","marker-start","marker-mid","marker-end","mask","stroke"],ow=Bf.map(n=>`[${n}]`).join(", "),sw=/^url\(['"]?#(.*?)['"]?\)$/,Lt=(()=>{class n{_elementRef=p(X);_iconRegistry=p(Xs);_location=p(rw);_errorHandler=p(Di);_defaultColor;get color(){return this._color||this._defaultColor}set color(e){this._color=e}_color;inline=!1;get svgIcon(){return this._svgIcon}set svgIcon(e){e!==this._svgIcon&&(e?this._updateSvgIcon(e):this._svgIcon&&this._clearSvgElement(),this._svgIcon=e)}_svgIcon;get fontSet(){return this._fontSet}set fontSet(e){let i=this._cleanupFontValue(e);i!==this._fontSet&&(this._fontSet=i,this._updateFontIconClasses())}_fontSet;get fontIcon(){return this._fontIcon}set fontIcon(e){let i=this._cleanupFontValue(e);i!==this._fontIcon&&(this._fontIcon=i,this._updateFontIconClasses())}_fontIcon;_previousFontSetClass=[];_previousFontIconClass;_svgName=null;_svgNamespace=null;_previousPath;_elementsWithExternalReferences;_currentIconFetch=bt.EMPTY;constructor(){let e=p(new gh("aria-hidden"),{optional:!0}),i=p(aw,{optional:!0});i&&(i.color&&(this.color=this._defaultColor=i.color),i.fontSet&&(this.fontSet=i.fontSet)),e||this._elementRef.nativeElement.setAttribute("aria-hidden","true")}_splitIconName(e){if(!e)return["",""];let i=e.split(":");switch(i.length){case 1:return["",i[0]];case 2:return i;default:throw Error(`Invalid icon name: "${e}"`)}}ngOnInit(){this._updateFontIconClasses()}ngAfterViewChecked(){let e=this._elementsWithExternalReferences;if(e&&e.size){let i=this._location.getPathname();i!==this._previousPath&&(this._previousPath=i,this._prependPathToReferences(i))}}ngOnDestroy(){this._currentIconFetch.unsubscribe(),this._elementsWithExternalReferences&&this._elementsWithExternalReferences.clear()}_usingFontIcon(){return!this.svgIcon}_setSvgElement(e){this._clearSvgElement();let i=this._location.getPathname();this._previousPath=i,this._cacheChildrenWithExternalReferences(e),this._prependPathToReferences(i),this._elementRef.nativeElement.appendChild(e)}_clearSvgElement(){let e=this._elementRef.nativeElement,i=e.childNodes.length;for(this._elementsWithExternalReferences&&this._elementsWithExternalReferences.clear();i--;){let a=e.childNodes[i];(a.nodeType!==1||a.nodeName.toLowerCase()==="svg")&&a.remove()}}_updateFontIconClasses(){if(!this._usingFontIcon())return;let e=this._elementRef.nativeElement,i=(this.fontSet?this._iconRegistry.classNameForFontAlias(this.fontSet).split(/ +/):this._iconRegistry.getDefaultFontSetClass()).filter(a=>a.length>0);this._previousFontSetClass.forEach(a=>e.classList.remove(a)),i.forEach(a=>e.classList.add(a)),this._previousFontSetClass=i,this.fontIcon!==this._previousFontIconClass&&!i.includes("mat-ligature-font")&&(this._previousFontIconClass&&e.classList.remove(this._previousFontIconClass),this.fontIcon&&e.classList.add(this.fontIcon),this._previousFontIconClass=this.fontIcon)}_cleanupFontValue(e){return typeof e=="string"?e.trim().split(" ")[0]:e}_prependPathToReferences(e){let i=this._elementsWithExternalReferences;i&&i.forEach((a,r)=>{a.forEach(o=>{r.setAttribute(o.name,`url('${e}#${o.value}')`)})})}_cacheChildrenWithExternalReferences(e){let i=e.querySelectorAll(ow),a=this._elementsWithExternalReferences=this._elementsWithExternalReferences||new Map;for(let r=0;r<i.length;r++)Bf.forEach(o=>{let s=i[r],l=s.getAttribute(o),c=l?l.match(sw):null;if(c){let d=a.get(s);d||(d=[],a.set(s,d)),d.push({name:o,value:c[1]})}})}_updateSvgIcon(e){if(this._svgNamespace=null,this._svgName=null,this._currentIconFetch.unsubscribe(),e){let[i,a]=this._splitIconName(e);i&&(this._svgNamespace=i),a&&(this._svgName=a),this._currentIconFetch=this._iconRegistry.getNamedSvgIcon(a,i).pipe(Pn(1)).subscribe(r=>this._setSvgElement(r),r=>{let o=`Error retrieving icon ${i}:${a}! ${r.message}`;this._errorHandler.handleError(new Error(o))})}}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){return T({type:n,selectors:[["mat-icon"]],hostAttrs:["role","img",1,"mat-icon","notranslate"],hostVars:10,hostBindings:function(a,r){a&2&&(fe("data-mat-icon-type",r._usingFontIcon()?"font":"svg")("data-mat-icon-name",r._svgName||r.fontIcon)("data-mat-icon-namespace",r._svgNamespace||r.fontSet)("fontIcon",r._usingFontIcon()?r.fontIcon:null),At(r.color?"mat-"+r.color:""),J("mat-icon-inline",r.inline)("mat-icon-no-color",r.color!=="primary"&&r.color!=="accent"&&r.color!=="warn"))},inputs:{color:"color",inline:[2,"inline","inline",Ae],svgIcon:"svgIcon",fontSet:"fontSet",fontIcon:"fontIcon"},exportAs:["matIcon"],ngContentSelectors:["*"],decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},styles:[`mat-icon, mat-icon.mat-primary, mat-icon.mat-accent, mat-icon.mat-warn {
  color: var(--%NS%mat-icon-color, inherit);
}

.mat-icon {
  -webkit-user-select: none;
  user-select: none;
  background-repeat: no-repeat;
  display: inline-block;
  fill: currentColor;
  height: 24px;
  width: 24px;
  overflow: hidden;
}
.mat-icon.mat-icon-inline {
  font-size: inherit;
  height: inherit;
  line-height: inherit;
  width: inherit;
}
.mat-icon.mat-ligature-font[fontIcon]::before {
  content: attr(fontIcon);
}

[dir=rtl] .mat-icon-rtl-mirror {
  transform: scale(-1, 1);
}

.mat-form-field:not(.mat-form-field-appearance-legacy) .mat-form-field-prefix .mat-icon,
.mat-form-field:not(.mat-form-field-appearance-legacy) .mat-form-field-suffix .mat-icon {
  display: block;
}
.mat-form-field:not(.mat-form-field-appearance-legacy) .mat-form-field-prefix .mat-icon-button .mat-icon,
.mat-form-field:not(.mat-form-field-appearance-legacy) .mat-form-field-suffix .mat-icon-button .mat-icon {
  margin: auto;
}
`],encapsulation:2})})()}return n})(),Ot=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[me]})}return n})();var Da=class{},Zs=class n{constructor(t,e){this.iconRegistry=t;this.sanitizer=e}iconRegistry;sanitizer;load(){}static \u0275fac=function(e){return new(e||n)(B(Xs),B(Vn))};static \u0275prov=te({token:n,factory:n.\u0275fac})};var Mn=class n{static contextPath=n.getContextPath();static getContextPath(){return document.location.pathname.split("/asyncapi-ui.html")[0]}static uiConfig=n.contextPath+"/ui-config";static docs=n.contextPath+"/docs";static getDocsForGroupEndpoint(t){return n.docs+`/${t}`}static getPublishEndpoint(t){return n.contextPath+`/plugin/${t}/publish`}};var Me=class{static DEFAULT_SHOW_BINDINGS=!0;static DEFAULT_SHOW_HEADERS=!0;static DEFAULT_GROUP="default"},Qs=class n extends Me{constructor(e){super();this.http=e;this.uiConfig=this.http.get(Mn.uiConfig).pipe(ls(()=>Ue(this.fallbackConfig)),Fn()),this.uiConfig.subscribe(i=>{this.toggleIsShowBindings(i.initialConfig.showBindings),this.toggleIsShowHeaders(i.initialConfig.showHeaders)})}http;_getGroup=new Jn(Me.DEFAULT_GROUP);isGroup$=this._getGroup.asObservable();fallbackConfig={initialConfig:{showBindings:Me.DEFAULT_SHOW_BINDINGS,showHeaders:Me.DEFAULT_SHOW_HEADERS},groups:[]};_isShowBindings=new Jn(Me.DEFAULT_SHOW_BINDINGS);isShowBindings$=this._isShowBindings.asObservable();_isShowHeaders=new Jn(Me.DEFAULT_SHOW_HEADERS);isShowHeaders$=this._isShowHeaders.asObservable();uiConfig;toggleIsShowBindings(e){this._isShowBindings.next(e)}toggleIsShowHeaders(e){this._isShowHeaders.next(e)}changeGroup(e){this._getGroup.next(e)}static \u0275fac=function(i){return new(i||n)(B(Xt))};static \u0275prov=te({token:n,factory:n.\u0275fac})};var Qr="server-";var Id="channel-";var Zt=class{value;rawValue;lineCount;constructor(t){this.rawValue=t,typeof t=="object"?Object.keys(t).length>0?this.value=JSON.stringify(t,null,2):this.value="":this.value=""+t,this.lineCount=this.value.split(`
`).length}};var el=class extends Error{};function Ad(n,t){try{return t()}catch(e){throw e instanceof Error?new el(n+" ("+e.message+")"):new el(n+" ("+e+")")}}function jf(n,t=()=>{}){try{return n()}catch(e){t!==void 0&&t(e);return}}function Li(n){return n.buttons===0||n.detail===0}function Bi(n){let t=n.touches&&n.touches[0]||n.changedTouches&&n.changedTouches[0];return!!t&&t.identifier===-1&&(t.radiusX==null||t.radiusX===1)&&(t.radiusY==null||t.radiusY===1)}var Td;function Vf(){if(Td==null){let n=typeof document<"u"?document.head:null;Td=!!(n&&(n.createShadowRoot||n.attachShadow))}return Td}function Od(n){if(Vf()){let t=n.getRootNode?n.getRootNode():null;if(typeof ShadowRoot<"u"&&ShadowRoot&&t instanceof ShadowRoot)return t}return null}function Bt(n){if(n.composedPath)try{return n.composedPath()[0]}catch{}return n.target}var Rd;try{Rd=typeof Intl<"u"&&Intl.v8BreakIterator}catch{Rd=!1}var Ne=(()=>{class n{_platformId=p(ni);isBrowser=this._platformId?ci(this._platformId):typeof document=="object"&&!!document;EDGE=this.isBrowser&&/(edge)/i.test(navigator.userAgent);TRIDENT=this.isBrowser&&/(msie|trident)/i.test(navigator.userAgent);BLINK=this.isBrowser&&!!(window.chrome||Rd)&&typeof CSS<"u"&&!this.EDGE&&!this.TRIDENT;WEBKIT=this.isBrowser&&/AppleWebKit/i.test(navigator.userAgent)&&!this.BLINK&&!this.EDGE&&!this.TRIDENT;IOS=this.isBrowser&&/iPad|iPhone|iPod/.test(navigator.userAgent)&&!("MSStream"in window);FIREFOX=this.isBrowser&&/(firefox|minefield)/i.test(navigator.userAgent);ANDROID=this.isBrowser&&/android/i.test(navigator.userAgent)&&!this.TRIDENT;SAFARI=this.isBrowser&&/safari/i.test(navigator.userAgent)&&this.WEBKIT;static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var Jr;function $f(){if(Jr==null&&typeof window<"u")try{window.addEventListener("test",null,Object.defineProperty({},"passive",{get:()=>Jr=!0}))}finally{Jr=Jr||!1}return Jr}function Ma(n){return $f()?n:!!n.capture}function zn(n,t=0){return zf(n)?Number(n):arguments.length===2?t:0}function zf(n){return!isNaN(parseFloat(n))&&!isNaN(Number(n))}function Qt(n){return n instanceof X?n.nativeElement:n}var Hf=new I("cdk-input-modality-detector-options"),Uf={ignoreKeys:[18,17,224,91,16]},qf=650,Pd={passive:!0,capture:!0},Gf=(()=>{class n{_platform=p(Ne);_listenerCleanups;modalityDetected;modalityChanged;get mostRecentModality(){return this._modality.value}_mostRecentTarget=null;_modality=new Jn(null);_options;_lastTouchMs=0;_onKeydown=e=>{this._options?.ignoreKeys?.some(i=>i===e.keyCode)||(this._modality.next("keyboard"),this._mostRecentTarget=Bt(e))};_onMousedown=e=>{Date.now()-this._lastTouchMs<qf||(this._modality.next(Li(e)?"keyboard":"mouse"),this._mostRecentTarget=Bt(e))};_onTouchstart=e=>{if(Bi(e)){this._modality.next("keyboard");return}this._lastTouchMs=Date.now(),this._modality.next("touch"),this._mostRecentTarget=Bt(e)};constructor(){let e=p(W),i=p(Y),a=p(Hf,{optional:!0});if(this._options=N(N({},Uf),a),this.modalityDetected=this._modality.pipe(ms(1)),this.modalityChanged=this.modalityDetected.pipe(wr()),this._platform.isBrowser){let r=p(xt).createRenderer(null,null);this._listenerCleanups=e.runOutsideAngular(()=>[r.listen(i,"keydown",this._onKeydown,Pd),r.listen(i,"mousedown",this._onMousedown,Pd),r.listen(i,"touchstart",this._onTouchstart,Pd)])}}ngOnDestroy(){this._modality.complete(),this._listenerCleanups?.forEach(e=>e())}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),eo=(function(n){return n[n.IMMEDIATE=0]="IMMEDIATE",n[n.EVENTUAL=1]="EVENTUAL",n})(eo||{}),Wf=new I("cdk-focus-monitor-default-options"),tl=Ma({passive:!0,capture:!0}),Nn=(()=>{class n{_ngZone=p(W);_platform=p(Ne);_inputModalityDetector=p(Gf);_origin=null;_lastFocusOrigin=null;_windowFocused=!1;_windowFocusTimeoutId;_originTimeoutId;_originFromTouchInteraction=!1;_elementInfo=new Map;_monitoredElementCount=0;_rootNodeFocusListenerCount=new Map;_detectionMode;_windowFocusListener=()=>{this._windowFocused=!0,this._windowFocusTimeoutId=setTimeout(()=>this._windowFocused=!1)};_document=p(Y);_stopInputModalityDetector=new z;constructor(){let e=p(Wf,{optional:!0});this._detectionMode=e?.detectionMode||eo.IMMEDIATE}_rootNodeFocusAndBlurListener=e=>{let i=Bt(e);for(let a=i;a;a=a.parentElement)e.type==="focus"?this._onFocus(e,a):this._onBlur(e,a)};monitor(e,i=!1){let a=Qt(e);if(!this._platform.isBrowser||a.nodeType!==1)return Ue();let r=Od(a)||this._document,o=this._elementInfo.get(a);if(o)return i&&(o.checkChildren=!0),o.subject;let s={checkChildren:i,subject:new z,rootNode:r};return this._elementInfo.set(a,s),this._registerGlobalListeners(s),s.subject}stopMonitoring(e){let i=Qt(e),a=this._elementInfo.get(i);a&&(a.subject.complete(),this._setClasses(i),this._elementInfo.delete(i),this._removeGlobalListeners(a))}focusVia(e,i,a){let r=Qt(e),o=this._document.activeElement;r===o?this._getClosestElementsInfo(r).forEach(([s,l])=>this._originChanged(s,i,l)):(this._setOrigin(i),typeof r.focus=="function"&&r.focus(a))}ngOnDestroy(){this._elementInfo.forEach((e,i)=>this.stopMonitoring(i))}_getWindow(){return this._document.defaultView||window}_getFocusOrigin(e){return this._origin?this._originFromTouchInteraction?this._shouldBeAttributedToTouch(e)?"touch":"program":this._origin:this._windowFocused&&this._lastFocusOrigin?this._lastFocusOrigin:e&&this._isLastInteractionFromInputLabel(e)?"mouse":"program"}_shouldBeAttributedToTouch(e){return this._detectionMode===eo.EVENTUAL||!!e?.contains(this._inputModalityDetector._mostRecentTarget)}_setClasses(e,i){e.classList.toggle("cdk-focused",!!i),e.classList.toggle("cdk-touch-focused",i==="touch"),e.classList.toggle("cdk-keyboard-focused",i==="keyboard"),e.classList.toggle("cdk-mouse-focused",i==="mouse"),e.classList.toggle("cdk-program-focused",i==="program")}_setOrigin(e,i=!1){this._ngZone.runOutsideAngular(()=>{if(this._origin=e,this._originFromTouchInteraction=e==="touch"&&i,this._detectionMode===eo.IMMEDIATE){clearTimeout(this._originTimeoutId);let a=this._originFromTouchInteraction?qf:1;this._originTimeoutId=setTimeout(()=>this._origin=null,a)}})}_onFocus(e,i){let a=this._elementInfo.get(i),r=Bt(e);!a||!a.checkChildren&&i!==r||this._originChanged(i,this._getFocusOrigin(r),a)}_onBlur(e,i){let a=this._elementInfo.get(i);!a||a.checkChildren&&e.relatedTarget instanceof Node&&i.contains(e.relatedTarget)||(this._setClasses(i),this._emitOrigin(a,null))}_emitOrigin(e,i){e.subject.observers.length&&this._ngZone.run(()=>e.subject.next(i))}_registerGlobalListeners(e){if(!this._platform.isBrowser)return;let i=e.rootNode,a=this._rootNodeFocusListenerCount.get(i)||0;a||this._ngZone.runOutsideAngular(()=>{i.addEventListener("focus",this._rootNodeFocusAndBlurListener,tl),i.addEventListener("blur",this._rootNodeFocusAndBlurListener,tl)}),this._rootNodeFocusListenerCount.set(i,a+1),++this._monitoredElementCount===1&&(this._ngZone.runOutsideAngular(()=>{this._getWindow().addEventListener("focus",this._windowFocusListener)}),this._inputModalityDetector.modalityDetected.pipe(Ie(this._stopInputModalityDetector)).subscribe(r=>{this._setOrigin(r,!0)}))}_removeGlobalListeners(e){let i=e.rootNode;if(this._rootNodeFocusListenerCount.has(i)){let a=this._rootNodeFocusListenerCount.get(i);a>1?this._rootNodeFocusListenerCount.set(i,a-1):(i.removeEventListener("focus",this._rootNodeFocusAndBlurListener,tl),i.removeEventListener("blur",this._rootNodeFocusAndBlurListener,tl),this._rootNodeFocusListenerCount.delete(i))}--this._monitoredElementCount||(this._getWindow().removeEventListener("focus",this._windowFocusListener),this._stopInputModalityDetector.next(),clearTimeout(this._windowFocusTimeoutId),clearTimeout(this._originTimeoutId))}_originChanged(e,i,a){this._setClasses(e,i),this._emitOrigin(a,i),this._lastFocusOrigin=i}_getClosestElementsInfo(e){let i=[];return this._elementInfo.forEach((a,r)=>{(r===e||a.checkChildren&&r.contains(e))&&i.push([r,a])}),i}_isLastInteractionFromInputLabel(e){let{_mostRecentTarget:i,mostRecentModality:a}=this._inputModalityDetector;if(a!=="mouse"||!i||i===e||e.nodeName!=="INPUT"&&e.nodeName!=="TEXTAREA"||e.disabled)return!1;let r=e.labels;if(r){for(let o=0;o<r.length;o++)if(r[o].contains(i))return!0}return!1}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();function Na(n){return Array.isArray(n)?n:[n]}var Kf=new Set,ji,nl=(()=>{class n{_platform=p(Ne);_nonce=p(ha,{optional:!0});_matchMedia;constructor(){this._matchMedia=this._platform.isBrowser&&window.matchMedia?window.matchMedia.bind(window):dw}matchMedia(e){return(this._platform.WEBKIT||this._platform.BLINK)&&cw(e,this._nonce),this._matchMedia(e)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();function cw(n,t){if(!Kf.has(n))try{ji||(ji=document.createElement("style"),t&&ji.setAttribute("nonce",t),ji.setAttribute("type","text/css"),document.head.appendChild(ji)),ji.sheet&&(ji.sheet.insertRule(`@media ${n.replace(/[{}]/g,"")} {body{ }}`,0),Kf.add(n))}catch(e){console.error(e)}}function dw(n){return{matches:n==="all"||n==="",media:n,addListener:()=>{},removeListener:()=>{}}}var Fd=(()=>{class n{_mediaMatcher=p(nl);_zone=p(W);_queries=new Map;_destroySubject=new z;ngOnDestroy(){this._destroySubject.next(),this._destroySubject.complete()}isMatched(e){return Yf(Na(e)).some(a=>this._registerQuery(a).mql.matches)}observe(e){let a=Yf(Na(e)).map(o=>this._registerQuery(o).observable),r=Wu(a);return r=Ku(r.pipe(Pn(1)),r.pipe(ms(1),ei(0))),r.pipe(De(o=>{let s={matches:!1,breakpoints:{}};return o.forEach(({matches:l,query:c})=>{s.matches=s.matches||l,s.breakpoints[c]=l}),s}))}_registerQuery(e){if(this._queries.has(e))return this._queries.get(e);let i=this._mediaMatcher.matchMedia(e),r={observable:new Nt(o=>{let s=l=>this._zone.run(()=>o.next(l));return i.addListener(s),()=>{i.removeListener(s)}}).pipe(vt(i),De(({matches:o})=>({query:e,matches:o})),Ie(this._destroySubject)),mql:i};return this._queries.set(e,r),r}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();function Yf(n){return n.map(t=>t.split(",")).reduce((t,e)=>t.concat(e)).map(t=>t.trim())}function mw(n){if(n.type==="characterData"&&n.target instanceof Comment)return!0;if(n.type==="childList"){for(let t=0;t<n.addedNodes.length;t++)if(!(n.addedNodes[t]instanceof Comment))return!1;for(let t=0;t<n.removedNodes.length;t++)if(!(n.removedNodes[t]instanceof Comment))return!1;return!0}return!1}var Xf=(()=>{class n{create(e){return typeof MutationObserver>"u"?null:new MutationObserver(e)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),pw=(()=>{class n{_mutationObserverFactory=p(Xf);_observedElements=new Map;_ngZone=p(W);ngOnDestroy(){this._observedElements.forEach((e,i)=>this._cleanupObserver(i))}observe(e){let i=Qt(e);return new Nt(a=>{let o=this._observeElement(i).pipe(De(s=>s.filter(l=>!mw(l))),We(s=>!!s.length)).subscribe(s=>{this._ngZone.run(()=>{a.next(s)})});return()=>{o.unsubscribe(),this._unobserveElement(i)}})}_observeElement(e){return this._ngZone.runOutsideAngular(()=>{if(this._observedElements.has(e))this._observedElements.get(e).count++;else{let i=new z,a=this._mutationObserverFactory.create(r=>i.next(r));a&&a.observe(e,{characterData:!0,childList:!0,subtree:!0}),this._observedElements.set(e,{observer:a,stream:i,count:1})}return this._observedElements.get(e).stream})}_unobserveElement(e){this._observedElements.has(e)&&(this._observedElements.get(e).count--,this._observedElements.get(e).count||this._cleanupObserver(e))}_cleanupObserver(e){if(this._observedElements.has(e)){let{observer:i,stream:a}=this._observedElements.get(e);i&&i.disconnect(),a.complete(),this._observedElements.delete(e)}}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),Zf=(()=>{class n{_contentObserver=p(pw);_elementRef=p(X);event=new Te;get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._disabled?this._unsubscribe():this._subscribe()}_disabled=!1;get debounce(){return this._debounce}set debounce(e){this._debounce=zn(e),this._subscribe()}_debounce;_currentSubscription=null;ngAfterContentInit(){!this._currentSubscription&&!this.disabled&&this._subscribe()}ngOnDestroy(){this._unsubscribe()}_subscribe(){this._unsubscribe();let e=this._contentObserver.observe(this._elementRef);this._currentSubscription=(this.debounce?e.pipe(ei(this.debounce)):e).subscribe(this.event)}_unsubscribe(){this._currentSubscription?.unsubscribe()}static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","cdkObserveContent",""]],inputs:{disabled:[2,"cdkObserveContentDisabled","disabled",Ae],debounce:"debounce"},outputs:{event:"cdkObserveContent"},exportAs:["cdkObserveContent"]})}return n})(),il=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({providers:[Xf]})}return n})();var rl=(()=>{class n{_platform=p(Ne);isDisabled(e){return e.hasAttribute("disabled")}isVisible(e){return hw(e)&&getComputedStyle(e).visibility==="visible"}isTabbable(e){if(!this._platform.isBrowser)return!1;let i=uw(ww(e));if(i&&(Qf(i)===-1||!this.isVisible(i)))return!1;let a=e.nodeName.toLowerCase(),r=Qf(e);return e.hasAttribute("contenteditable")?r!==-1:a==="iframe"||a==="object"||this._platform.WEBKIT&&this._platform.IOS&&!yw(e)?!1:a==="audio"?e.hasAttribute("controls")?r!==-1:!1:a==="video"?r===-1?!1:r!==null?!0:this._platform.FIREFOX||e.hasAttribute("controls"):e.tabIndex>=0}isFocusable(e,i){return xw(e)&&!this.isDisabled(e)&&(i?.ignoreVisibility||this.isVisible(e))}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();function uw(n){try{return n.frameElement}catch{return null}}function hw(n){return!!(n.offsetWidth||n.offsetHeight||typeof n.getClientRects=="function"&&n.getClientRects().length)}function fw(n){let t=n.nodeName.toLowerCase();return t==="input"||t==="select"||t==="button"||t==="textarea"}function gw(n){return vw(n)&&n.type=="hidden"}function bw(n){return _w(n)&&n.hasAttribute("href")}function vw(n){return n.nodeName.toLowerCase()=="input"}function _w(n){return n.nodeName.toLowerCase()=="a"}function Jf(n){if(!n.hasAttribute("tabindex")||n.tabIndex===void 0)return!1;let t=n.getAttribute("tabindex");return!!(t&&!isNaN(parseInt(t,10)))}function Qf(n){if(!Jf(n))return null;let t=parseInt(n.getAttribute("tabindex")||"",10);return isNaN(t)?-1:t}function yw(n){let t=n.nodeName.toLowerCase(),e=t==="input"&&n.type;return e==="text"||e==="password"||t==="select"||t==="textarea"}function xw(n){return gw(n)?!1:fw(n)||bw(n)||n.hasAttribute("contenteditable")||Jf(n)}function ww(n){return n.ownerDocument&&n.ownerDocument.defaultView||window}var al=class{_element;_checker;_ngZone;_document;_injector;_startAnchor=null;_endAnchor=null;_hasAttached=!1;startAnchorListener=()=>{!this.focusLastTabbableElement()&&this._checker.isFocusable(this._element)&&this._element.focus()};endAnchorListener=()=>{!this.focusFirstTabbableElement()&&this._checker.isFocusable(this._element)&&this._element.focus()};get enabled(){return this._enabled}set enabled(t){this._enabled=t,this._startAnchor&&this._endAnchor&&(this._toggleAnchorTabIndex(t,this._startAnchor),this._toggleAnchorTabIndex(t,this._endAnchor))}_enabled=!0;constructor(t,e,i,a,r=!1,o){this._element=t,this._checker=e,this._ngZone=i,this._document=a,this._injector=o,r||this.attachAnchors()}destroy(){let t=this._startAnchor,e=this._endAnchor;t&&(t.removeEventListener("focus",this.startAnchorListener),t.remove()),e&&(e.removeEventListener("focus",this.endAnchorListener),e.remove()),this._startAnchor=this._endAnchor=null,this._hasAttached=!1}attachAnchors(){return this._hasAttached?!0:(this._ngZone.runOutsideAngular(()=>{this._startAnchor||(this._startAnchor=this._createAnchor(),this._startAnchor.addEventListener("focus",this.startAnchorListener)),this._endAnchor||(this._endAnchor=this._createAnchor(),this._endAnchor.addEventListener("focus",this.endAnchorListener))}),this._element.parentNode&&(this._element.parentNode.insertBefore(this._startAnchor,this._element),this._element.parentNode.insertBefore(this._endAnchor,this._element.nextSibling),this._hasAttached=!0),this._hasAttached)}focusInitialElementWhenReady(t){return new Promise(e=>{this._executeOnStable(()=>e(this.focusInitialElement(t)))})}focusFirstTabbableElementWhenReady(t){return new Promise(e=>{this._executeOnStable(()=>e(this.focusFirstTabbableElement(t)))})}focusLastTabbableElementWhenReady(t){return new Promise(e=>{this._executeOnStable(()=>e(this.focusLastTabbableElement(t)))})}_getRegionBoundary(t){let e=this._element.querySelectorAll(`[cdk-focus-region-${t}], [cdkFocusRegion${t}], [cdk-focus-${t}]`);return t=="start"?e.length?e[0]:this._getFirstTabbableElement(this._element):e.length?e[e.length-1]:this._getLastTabbableElement(this._element)}focusInitialElement(t){let e=this._element.querySelector("[cdk-focus-initial], [cdkFocusInitial]");if(e){if(!this._checker.isFocusable(e)){let i=this._getFirstTabbableElement(e);return i?.focus(t),!!i}return e.focus(t),!0}return this.focusFirstTabbableElement(t)}focusFirstTabbableElement(t){let e=this._getRegionBoundary("start");return e&&e.focus(t),!!e}focusLastTabbableElement(t){let e=this._getRegionBoundary("end");return e&&e.focus(t),!!e}hasAttached(){return this._hasAttached}_getFirstTabbableElement(t){if(this._checker.isFocusable(t)&&this._checker.isTabbable(t))return t;let e=t.children;for(let i=0;i<e.length;i++){let a=e[i].nodeType===this._document.ELEMENT_NODE?this._getFirstTabbableElement(e[i]):null;if(a)return a}return null}_getLastTabbableElement(t){if(this._checker.isFocusable(t)&&this._checker.isTabbable(t))return t;let e=t.children;for(let i=e.length-1;i>=0;i--){let a=e[i].nodeType===this._document.ELEMENT_NODE?this._getLastTabbableElement(e[i]):null;if(a)return a}return null}_createAnchor(){let t=this._document.createElement("div");return this._toggleAnchorTabIndex(this._enabled,t),t.classList.add("cdk-visually-hidden"),t.classList.add("cdk-focus-trap-anchor"),t.setAttribute("aria-hidden","true"),t}_toggleAnchorTabIndex(t,e){t?e.setAttribute("tabindex","0"):e.removeAttribute("tabindex")}toggleAnchors(t){this._startAnchor&&this._endAnchor&&(this._toggleAnchorTabIndex(t,this._startAnchor),this._toggleAnchorTabIndex(t,this._endAnchor))}_executeOnStable(t){yt(t,{injector:this._injector})}},Ld=(()=>{class n{_checker=p(rl);_ngZone=p(W);_document=p(Y);_injector=p(ce);constructor(){p(ct).load(Ea)}create(e,i=!1){return new al(e,this._checker,this._ngZone,this._document,i,this._injector)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var eg=new I("liveAnnouncerElement",{providedIn:"root",factory:()=>null}),tg=new I("LIVE_ANNOUNCER_DEFAULT_OPTIONS"),Sw=0,Bd=(()=>{class n{_ngZone=p(W);_defaultOptions=p(tg,{optional:!0});_liveElement;_document=p(Y);_sanitizer=p(Vn);_previousTimeout;_currentPromise;_currentResolve;constructor(){let e=p(eg,{optional:!0});this._liveElement=e||this._createLiveElement()}announce(e,...i){let a=this._defaultOptions,r,o;return i.length===1&&typeof i[0]=="number"?o=i[0]:[r,o]=i,this.clear(),clearTimeout(this._previousTimeout),r||(r=a&&a.politeness?a.politeness:"polite"),o==null&&a&&(o=a.duration),this._liveElement.setAttribute("aria-live",r),this._liveElement.id&&this._exposeAnnouncerToModals(this._liveElement.id),this._ngZone.runOutsideAngular(()=>(this._currentPromise||(this._currentPromise=new Promise(s=>this._currentResolve=s)),clearTimeout(this._previousTimeout),this._previousTimeout=setTimeout(()=>{!e||typeof e=="string"?this._liveElement.textContent=e:Tf(this._liveElement,e,this._sanitizer),typeof o=="number"&&(this._previousTimeout=setTimeout(()=>this.clear(),o)),this._currentResolve?.(),this._currentPromise=this._currentResolve=void 0},100),this._currentPromise))}clear(){this._liveElement&&(this._liveElement.textContent="")}ngOnDestroy(){clearTimeout(this._previousTimeout),this._liveElement?.remove(),this._liveElement=null,this._currentResolve?.(),this._currentPromise=this._currentResolve=void 0}_createLiveElement(){let e="cdk-live-announcer-element",i=this._document.getElementsByClassName(e),a=this._document.createElement("div");for(let r=0;r<i.length;r++)i[r].remove();return a.classList.add(e),a.classList.add("cdk-visually-hidden"),a.setAttribute("aria-atomic","true"),a.setAttribute("aria-live","polite"),a.id=`cdk-live-announcer-${Sw++}`,this._document.body.appendChild(a),a}_exposeAnnouncerToModals(e){let i=this._document.querySelectorAll('body > .cdk-overlay-container [aria-modal="true"]');for(let a=0;a<i.length;a++){let r=i[a],o=r.getAttribute("aria-owns");o?o.indexOf(e)===-1&&r.setAttribute("aria-owns",o+" "+e):r.setAttribute("aria-owns",e)}}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var kw=200,ol=class{_letterKeyStream=new z;_items=[];_selectedItemIndex=-1;_pressedLetters=[];_skipPredicateFn;_selectedItem=new z;selectedItem=this._selectedItem;constructor(t,e){let i=typeof e?.debounceInterval=="number"?e.debounceInterval:kw;e?.skipPredicate&&(this._skipPredicateFn=e.skipPredicate),this.setItems(t),this._setupKeyHandler(i)}destroy(){this._pressedLetters=[],this._letterKeyStream.complete(),this._selectedItem.complete()}setCurrentSelectedItemIndex(t){this._selectedItemIndex=t}setItems(t){this._items=t}handleKey(t){let e=t.keyCode;t.key&&t.key.length===1?this._letterKeyStream.next(t.key.toLocaleUpperCase()):(e>=65&&e<=90||e>=48&&e<=57)&&this._letterKeyStream.next(String.fromCharCode(e))}isTyping(){return this._pressedLetters.length>0}reset(){this._pressedLetters=[]}_setupKeyHandler(t){this._letterKeyStream.pipe(ti(e=>this._pressedLetters.push(e)),ei(t),We(()=>this._pressedLetters.length>0),De(()=>this._pressedLetters.join("").toLocaleUpperCase())).subscribe(e=>{for(let i=1;i<this._items.length+1;i++){let a=(this._selectedItemIndex+i)%this._items.length,r=this._items[a];if(!this._skipPredicateFn?.(r)&&r.getLabel?.().toLocaleUpperCase().trim().indexOf(e)===0){this._selectedItem.next(r);break}}this._pressedLetters=[]})}};function ui(n,...t){return t.length?t.some(e=>n[e]):n.altKey||n.shiftKey||n.ctrlKey||n.metaKey}var sl=class{_items;_activeItemIndex=ne(-1);_activeItem=ne(null);_wrap=!1;_typeaheadSubscription=bt.EMPTY;_itemChangesSubscription;_vertical=!0;_horizontal=null;_allowedModifierKeys=[];_homeAndEnd=!1;_pageUpAndDown={enabled:!1,delta:10};_effectRef;_typeahead;_skipPredicateFn=t=>t.disabled;constructor(t,e){this._items=t,t instanceof Bn?this._itemChangesSubscription=t.changes.subscribe(i=>this._itemsChanged(i.toArray())):ii(t)&&(this._effectRef=Mi(()=>this._itemsChanged(t()),{injector:e}))}tabOut=new z;change=new z;skipPredicate(t){return this._skipPredicateFn=t,this}withWrap(t=!0){return this._wrap=t,this}withVerticalOrientation(t=!0){return this._vertical=t,this}withHorizontalOrientation(t){return this._horizontal=t,this}withAllowedModifierKeys(t){return this._allowedModifierKeys=t,this}withTypeAhead(t=200){this._typeaheadSubscription.unsubscribe();let e=this._getItemsArray();return this._typeahead=new ol(e,{debounceInterval:typeof t=="number"?t:void 0,skipPredicate:i=>this._skipPredicateFn(i)}),this._typeaheadSubscription=this._typeahead.selectedItem.subscribe(i=>{this.setActiveItem(i)}),this}cancelTypeahead(){return this._typeahead?.reset(),this}withHomeAndEnd(t=!0){return this._homeAndEnd=t,this}withPageUpDown(t=!0,e=10){return this._pageUpAndDown={enabled:t,delta:e},this}setActiveItem(t){let e=this._activeItem();this.updateActiveItem(t),this._activeItem()!==e&&this.change.next(this._activeItemIndex())}onKeydown(t){let e=t.keyCode,a=["altKey","ctrlKey","metaKey","shiftKey"].every(r=>!t[r]||this._allowedModifierKeys.indexOf(r)>-1);switch(e){case 9:this.tabOut.next();return;case 40:if(this._vertical&&a){this.setNextItemActive();break}else return;case 38:if(this._vertical&&a){this.setPreviousItemActive();break}else return;case 39:if(this._horizontal&&a){this._horizontal==="rtl"?this.setPreviousItemActive():this.setNextItemActive();break}else return;case 37:if(this._horizontal&&a){this._horizontal==="rtl"?this.setNextItemActive():this.setPreviousItemActive();break}else return;case 36:if(this._homeAndEnd&&a){this.setFirstItemActive();break}else return;case 35:if(this._homeAndEnd&&a){this.setLastItemActive();break}else return;case 33:if(this._pageUpAndDown.enabled&&a){let r=this._activeItemIndex()-this._pageUpAndDown.delta;this._setActiveItemByIndex(r>0?r:0,1);break}else return;case 34:if(this._pageUpAndDown.enabled&&a){let r=this._activeItemIndex()+this._pageUpAndDown.delta,o=this._getItemsArray().length;this._setActiveItemByIndex(r<o?r:o-1,-1);break}else return;default:(a||ui(t,"shiftKey"))&&this._typeahead?.handleKey(t);return}this._typeahead?.reset(),t.preventDefault()}get activeItemIndex(){return this._activeItemIndex()}get activeItem(){return this._activeItem()}isTyping(){return!!this._typeahead&&this._typeahead.isTyping()}setFirstItemActive(){this._setActiveItemByIndex(0,1)}setLastItemActive(){this._setActiveItemByIndex(this._getItemsArray().length-1,-1)}setNextItemActive(){this._activeItemIndex()<0?this.setFirstItemActive():this._setActiveItemByDelta(1)}setPreviousItemActive(){this._activeItemIndex()<0&&this._wrap?this.setLastItemActive():this._setActiveItemByDelta(-1)}updateActiveItem(t){let e=this._getItemsArray(),i=typeof t=="number"?t:e.indexOf(t),a=e[i];this._activeItem.set(a??null),this._activeItemIndex.set(i),this._typeahead?.setCurrentSelectedItemIndex(i)}destroy(){this._typeaheadSubscription.unsubscribe(),this._itemChangesSubscription?.unsubscribe(),this._effectRef?.destroy(),this._typeahead?.destroy(),this.tabOut.complete(),this.change.complete()}_setActiveItemByDelta(t){this._wrap?this._setActiveInWrapMode(t):this._setActiveInDefaultMode(t)}_setActiveInWrapMode(t){let e=this._getItemsArray();for(let i=1;i<=e.length;i++){let a=(this._activeItemIndex()+t*i+e.length)%e.length,r=e[a];if(!this._skipPredicateFn(r)){this.setActiveItem(a);return}}}_setActiveInDefaultMode(t){this._setActiveItemByIndex(this._activeItemIndex()+t,t)}_setActiveItemByIndex(t,e){let i=this._getItemsArray();if(i[t]){for(;this._skipPredicateFn(i[t]);)if(t+=e,!i[t])return;this.setActiveItem(t)}}_getItemsArray(){return ii(this._items)?this._items():this._items instanceof Bn?this._items.toArray():this._items}_itemsChanged(t){this._typeahead?.setItems(t);let e=this._activeItem();if(e){let i=t.indexOf(e);i>-1&&i!==this._activeItemIndex()&&(this._activeItemIndex.set(i),this._typeahead?.setCurrentSelectedItemIndex(i))}}};var Vi=class extends sl{_origin="program";setFocusOrigin(t){return this._origin=t,this}setActiveItem(t){super.setActiveItem(t),this.activeItem&&this.activeItem.focus(this._origin)}};var ag=new Map,St=class n{_appId=p(kr);static _infix=`a${Math.floor(Math.random()*1e5).toString()}`;getId(t,e=!1){this._appId!=="ng"&&(t+=this._appId);let i=ag.get(t);return i===void 0?i=0:i++,ag.set(t,i),`${t}${e?n._infix+"-":""}${i}`}static \u0275fac=function(e){return new(e||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})};var rg={XSmall:"(max-width: 599.98px)",Small:"(min-width: 600px) and (max-width: 959.98px)",Medium:"(min-width: 960px) and (max-width: 1279.98px)",Large:"(min-width: 1280px) and (max-width: 1919.98px)",XLarge:"(min-width: 1920px)",Handset:"(max-width: 599.98px) and (orientation: portrait), (max-width: 959.98px) and (orientation: landscape)",Tablet:"(min-width: 600px) and (max-width: 839.98px) and (orientation: portrait), (min-width: 960px) and (max-width: 1279.98px) and (orientation: landscape)",Web:"(min-width: 840px) and (orientation: portrait), (min-width: 1280px) and (orientation: landscape)",HandsetPortrait:"(max-width: 599.98px) and (orientation: portrait)",TabletPortrait:"(min-width: 600px) and (max-width: 839.98px) and (orientation: portrait)",WebPortrait:"(min-width: 840px) and (orientation: portrait)",HandsetLandscape:"(max-width: 959.98px) and (orientation: landscape)",TabletLandscape:"(min-width: 960px) and (max-width: 1279.98px) and (orientation: landscape)",WebLandscape:"(min-width: 1280px) and (orientation: landscape)"};function $d(){return typeof __karma__<"u"&&!!__karma__||typeof jasmine<"u"&&!!jasmine||typeof jest<"u"&&!!jest||typeof Mocha<"u"&&!!Mocha}function nt(n){return n==null?"":typeof n=="string"?n:`${n}px`}var pn=(function(n){return n[n.NORMAL=0]="NORMAL",n[n.NEGATED=1]="NEGATED",n[n.INVERTED=2]="INVERTED",n})(pn||{}),ll,$i;function cl(){if($i==null){if(typeof document!="object"||!document||typeof Element!="function"||!Element)return $i=!1,$i;if(document.documentElement?.style&&"scrollBehavior"in document.documentElement.style)$i=!0;else{let n=Element.prototype.scrollTo;n?$i=!/\{\s*\[native code\]\s*\}/.test(n.toString()):$i=!1}}return $i}function Ia(){if(typeof document!="object"||!document)return pn.NORMAL;if(ll==null){let n=document.createElement("div"),t=n.style;n.dir="rtl",t.width="1px",t.overflow="auto",t.visibility="hidden",t.pointerEvents="none",t.position="absolute";let e=document.createElement("div"),i=e.style;i.width="2px",i.height="1px",n.appendChild(e),document.body.appendChild(n),ll=pn.NORMAL,n.scrollLeft===0&&(n.scrollLeft=1,ll=n.scrollLeft===0?pn.NEGATED:pn.INVERTED),n.remove()}return ll}var Cw=20,to=(()=>{class n{_ngZone=p(W);_platform=p(Ne);_renderer=p(xt).createRenderer(null,null);_cleanupGlobalListener;_scrolled=new z;_scrolledCount=0;scrollContainers=new Map;register(e){this.scrollContainers.has(e)||this.scrollContainers.set(e,e.elementScrolled().subscribe(()=>this._scrolled.next(e)))}deregister(e){let i=this.scrollContainers.get(e);i&&(i.unsubscribe(),this.scrollContainers.delete(e))}scrolled(e=Cw){return this._platform.isBrowser?new Nt(i=>{this._cleanupGlobalListener||(this._cleanupGlobalListener=this._ngZone.runOutsideAngular(()=>this._renderer.listen("document","scroll",()=>this._scrolled.next())));let a=e>0?this._scrolled.pipe(Bc(e)).subscribe(i):this._scrolled.subscribe(i);return this._scrolledCount++,()=>{a.unsubscribe(),this._scrolledCount--,this._scrolledCount||(this._cleanupGlobalListener?.(),this._cleanupGlobalListener=void 0)}}):Ue()}ngOnDestroy(){this._cleanupGlobalListener?.(),this._cleanupGlobalListener=void 0,this.scrollContainers.forEach((e,i)=>this.deregister(i)),this._scrolled.complete()}ancestorScrolled(e,i){let a=this.getAncestorScrollContainers(e);return this.scrolled(i).pipe(We(r=>!r||a.indexOf(r)>-1))}getAncestorScrollContainers(e){let i=[];return this.scrollContainers.forEach((a,r)=>{this._targetContainsElement(r,e)&&i.push(r)}),i}_targetContainsElement(e,i){let a=Qt(i),r=e.getElementRef().nativeElement;do if(a==r)return!0;while(a=a.parentElement);return!1}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),Aa=(()=>{class n{elementRef=p(X);scrollDispatcher=p(to);ngZone=p(W);dir=p(Tt,{optional:!0});_scrollElement=this.elementRef.nativeElement;_destroyed=new z;_renderer=p(pt);_cleanupScroll;_elementScrolled=new z;ngOnInit(){this._cleanupScroll=this.ngZone.runOutsideAngular(()=>this._renderer.listen(this._scrollElement,"scroll",e=>this._elementScrolled.next(e))),this.scrollDispatcher.register(this)}ngOnDestroy(){this._cleanupScroll?.(),this._elementScrolled.complete(),this.scrollDispatcher.deregister(this),this._destroyed.next(),this._destroyed.complete()}elementScrolled(){return this._elementScrolled}getElementRef(){return this.elementRef}scrollTo(e){let i=this.elementRef.nativeElement,a=this.dir&&this.dir.value=="rtl";e.left==null&&(e.left=a?e.end:e.start),e.right==null&&(e.right=a?e.start:e.end),e.bottom!=null&&(e.top=i.scrollHeight-i.clientHeight-e.bottom),a&&Ia()!=pn.NORMAL?(e.left!=null&&(e.right=i.scrollWidth-i.clientWidth-e.left),Ia()==pn.INVERTED?e.left=e.right:Ia()==pn.NEGATED&&(e.left=e.right?-e.right:e.right)):e.right!=null&&(e.left=i.scrollWidth-i.clientWidth-e.right),this._applyScrollToOptions(e)}_applyScrollToOptions(e){let i=this.elementRef.nativeElement;cl()?i.scrollTo(e):(e.top!=null&&(i.scrollTop=e.top),e.left!=null&&(i.scrollLeft=e.left))}measureScrollOffset(e){let i="left",a="right",r=this.elementRef.nativeElement;if(e=="top")return r.scrollTop;if(e=="bottom")return r.scrollHeight-r.clientHeight-r.scrollTop;let o=this.dir&&this.dir.value=="rtl";return e=="start"?e=o?a:i:e=="end"&&(e=o?i:a),o&&Ia()==pn.INVERTED?e==i?r.scrollWidth-r.clientWidth-r.scrollLeft:r.scrollLeft:o&&Ia()==pn.NEGATED?e==i?r.scrollLeft+r.scrollWidth-r.clientWidth:-r.scrollLeft:e==i?r.scrollLeft:r.scrollWidth-r.clientWidth-r.scrollLeft}static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","cdk-scrollable",""],["","cdkScrollable",""]]})}return n})(),Ew=20,Un=(()=>{class n{_platform=p(Ne);_listeners;_viewportSize=null;_change=new z;_document=p(Y);constructor(){let e=p(W),i=p(xt).createRenderer(null,null);e.runOutsideAngular(()=>{if(this._platform.isBrowser){let a=r=>this._change.next(r);this._listeners=[i.listen("window","resize",a),i.listen("window","orientationchange",a)]}this.change().subscribe(()=>this._viewportSize=null)})}ngOnDestroy(){this._listeners?.forEach(e=>e()),this._change.complete()}getViewportSize(){this._viewportSize||this._updateViewportSize();let e={width:this._viewportSize.width,height:this._viewportSize.height};return this._platform.isBrowser||(this._viewportSize=null),e}getViewportRect(){let e=this.getViewportScrollPosition(),{width:i,height:a}=this.getViewportSize();return{top:e.top,left:e.left,bottom:e.top+a,right:e.left+i,height:a,width:i}}getViewportScrollPosition(){if(!this._platform.isBrowser)return{top:0,left:0};let e=this._document,i=this._getWindow(),a=e.documentElement,r=a.getBoundingClientRect(),o=-r.top||e.body?.scrollTop||i.scrollY||a.scrollTop||0,s=-r.left||e.body?.scrollLeft||i.scrollX||a.scrollLeft||0;return{top:o,left:s}}change(e=Ew){return e>0?this._change.pipe(Bc(e)):this._change}_getWindow(){return this._document.defaultView||window}_updateViewportSize(){let e=this._getWindow();this._viewportSize=this._platform.isBrowser?{width:e.innerWidth,height:e.innerHeight}:{width:0,height:0}}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var Hn=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({})}return n})(),zd=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[me,Hn,me,Hn]})}return n})();var no=class{_attachedHost=null;attach(t){return this._attachedHost=t,t.attach(this)}detach(){let t=this._attachedHost;t!=null&&(this._attachedHost=null,t.detach())}get isAttached(){return this._attachedHost!=null}setAttachedHost(t){this._attachedHost=t}},Ta=class extends no{component;viewContainerRef;injector;projectableNodes;bindings;directives;constructor(t,e,i,a,r,o){super(),this.component=t,this.viewContainerRef=e,this.injector=i,this.projectableNodes=a,this.bindings=r||null,this.directives=o||null}},qn=class extends no{templateRef;viewContainerRef;context;injector;constructor(t,e,i,a){super(),this.templateRef=t,this.viewContainerRef=e,this.context=i,this.injector=a}get origin(){return this.templateRef.elementRef}attach(t,e=this.context){return this.context=e,super.attach(t)}detach(){return this.context=void 0,super.detach()}},Hd=class extends no{element;constructor(t){super(),this.element=t instanceof X?t.nativeElement:t}},Oa=class{_attachedPortal=null;_disposeFn=null;_isDisposed=!1;hasAttached(){return!!this._attachedPortal}attach(t){if(t instanceof Ta)return this._attachedPortal=t,this.attachComponentPortal(t);if(t instanceof qn)return this._attachedPortal=t,this.attachTemplatePortal(t);if(this.attachDomPortal&&t instanceof Hd)return this._attachedPortal=t,this.attachDomPortal(t)}attachDomPortal=null;detach(){this._attachedPortal&&(this._attachedPortal.setAttachedHost(null),this._attachedPortal=null),this._invokeDisposeFn()}dispose(){this.hasAttached()&&this.detach(),this._invokeDisposeFn(),this._isDisposed=!0}setDisposeFn(t){this._disposeFn=t}_invokeDisposeFn(){this._disposeFn&&(this._disposeFn(),this._disposeFn=null)}},io=class extends Oa{outletElement;_appRef;_defaultInjector;constructor(t,e,i){super(),this.outletElement=t,this._appRef=e,this._defaultInjector=i}attachComponentPortal(t){let e;if(t.viewContainerRef){let i=t.injector||t.viewContainerRef.injector,a=i.get(gs,null,{optional:!0})||void 0;e=t.viewContainerRef.createComponent(t.component,{index:t.viewContainerRef.length,injector:i,ngModuleRef:a,projectableNodes:t.projectableNodes||void 0,bindings:t.bindings||void 0,directives:t.directives||void 0}),this.setDisposeFn(()=>e.destroy())}else{let i=this._appRef,a=t.injector||this._defaultInjector||ce.NULL,r=a.get(Ln,i.injector);e=ws(t.component,{elementInjector:a,environmentInjector:r,projectableNodes:t.projectableNodes||void 0,bindings:t.bindings||void 0,directives:t.directives||void 0}),i.attachView(e.hostView),this.setDisposeFn(()=>{i.viewCount>0&&i.detachView(e.hostView),e.destroy()})}return this.outletElement.appendChild(this._getComponentRootNode(e)),this._attachedPortal=t,e}attachTemplatePortal(t){let e=t.viewContainerRef,i=e.createEmbeddedView(t.templateRef,t.context,{injector:t.injector});return i.rootNodes.forEach(a=>this.outletElement.appendChild(a)),i.detectChanges(),this.setDisposeFn(()=>{let a=e.indexOf(i);a!==-1&&e.remove(a)}),this._attachedPortal=t,i}attachDomPortal=t=>{let e=t.element;e.parentNode;let i=this.outletElement.ownerDocument.createComment("dom-portal");e.parentNode.insertBefore(i,e),this.outletElement.appendChild(e),this._attachedPortal=t,super.setDisposeFn(()=>{i.parentNode&&i.parentNode.replaceChild(e,i)})};dispose(){super.dispose(),this.outletElement.remove()}_getComponentRootNode(t){return t.hostView.rootNodes[0]}};var ml=(()=>{class n extends Oa{_moduleRef=p(gs,{optional:!0});_document=p(Y);_viewContainerRef=p(xn);_isInitialized=!1;_attachedRef=null;get portal(){return this._attachedPortal}set portal(e){this.hasAttached()&&!e&&!this._isInitialized||(this.hasAttached()&&super.detach(),e&&super.attach(e),this._attachedPortal=e||null)}attached=new Te;get attachedRef(){return this._attachedRef}ngOnInit(){this._isInitialized=!0}ngOnDestroy(){super.dispose(),this._attachedRef=this._attachedPortal=null}attachComponentPortal(e){e.setAttachedHost(this);let i=e.viewContainerRef!=null?e.viewContainerRef:this._viewContainerRef,a=i.createComponent(e.component,{index:i.length,injector:e.injector||i.injector,projectableNodes:e.projectableNodes||void 0,ngModuleRef:this._moduleRef||void 0,bindings:e.bindings||void 0,directives:e.directives||void 0});return i!==this._viewContainerRef&&this._getRootNode().appendChild(a.hostView.rootNodes[0]),super.setDisposeFn(()=>a.destroy()),this._attachedPortal=e,this._attachedRef=a,this.attached.emit(a),a}attachTemplatePortal(e){e.setAttachedHost(this);let i=this._viewContainerRef.createEmbeddedView(e.templateRef,e.context,{injector:e.injector});return super.setDisposeFn(()=>this._viewContainerRef.clear()),this._attachedPortal=e,this._attachedRef=i,this.attached.emit(i),i}attachDomPortal=e=>{let i=e.element;i.parentNode;let a=this._document.createComment("dom-portal");e.setAttachedHost(this),i.parentNode.insertBefore(a,i),this._getRootNode().appendChild(i),this._attachedPortal=e,super.setDisposeFn(()=>{a.parentNode&&a.parentNode.replaceChild(i,a)})};_getRootNode(){let e=this._viewContainerRef.element.nativeElement;return e.nodeType===e.ELEMENT_NODE?e:e.parentNode}static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(n)))(a||n)}})();static \u0275dir=K({type:n,selectors:[["","cdkPortalOutlet",""]],inputs:{portal:[0,"cdkPortalOutlet","portal"]},outputs:{attached:"attached"},exportAs:["cdkPortalOutlet"],features:[Ve]})}return n})(),Ra=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({})}return n})();var og=cl();function ug(n){return new pl(n.get(Un),n.get(Y))}var pl=class{_viewportRuler;_previousHTMLStyles={top:"",left:""};_previousScrollPosition;_isEnabled=!1;_document;constructor(t,e){this._viewportRuler=t,this._document=e}attach(){}enable(){if(this._canBeEnabled()){let t=this._document.documentElement;this._previousScrollPosition=this._viewportRuler.getViewportScrollPosition(),this._previousHTMLStyles.left=t.style.left||"",this._previousHTMLStyles.top=t.style.top||"",t.style.left=nt(-this._previousScrollPosition.left),t.style.top=nt(-this._previousScrollPosition.top),t.classList.add("cdk-global-scrollblock"),this._isEnabled=!0}}disable(){if(this._isEnabled){let t=this._document.documentElement,e=this._document.body,i=t.style,a=e.style,r=i.scrollBehavior||"",o=a.scrollBehavior||"";this._isEnabled=!1,i.left=this._previousHTMLStyles.left,i.top=this._previousHTMLStyles.top,t.classList.remove("cdk-global-scrollblock"),og&&(i.scrollBehavior=a.scrollBehavior="auto"),window.scroll(this._previousScrollPosition.left,this._previousScrollPosition.top),og&&(i.scrollBehavior=r,a.scrollBehavior=o)}}_canBeEnabled(){if(this._document.documentElement.classList.contains("cdk-global-scrollblock")||this._isEnabled)return!1;let e=this._document.documentElement,i=this._viewportRuler.getViewportSize();return e.scrollHeight>i.height||e.scrollWidth>i.width}};function hg(n,t){return new ul(n.get(to),n.get(W),n.get(Un),t)}var ul=class{_scrollDispatcher;_ngZone;_viewportRuler;_config;_scrollSubscription=null;_overlayRef;_initialScrollPosition;constructor(t,e,i,a){this._scrollDispatcher=t,this._ngZone=e,this._viewportRuler=i,this._config=a}attach(t){this._overlayRef,this._overlayRef=t}enable(){if(this._scrollSubscription)return;let t=this._scrollDispatcher.scrolled(0).pipe(We(e=>!e||!this._overlayRef.overlayElement.contains(e.getElementRef().nativeElement)));this._config&&this._config.threshold&&this._config.threshold>1?(this._initialScrollPosition=this._viewportRuler.getViewportScrollPosition().top,this._scrollSubscription=t.subscribe(()=>{let e=this._viewportRuler.getViewportScrollPosition().top;Math.abs(e-this._initialScrollPosition)>this._config.threshold?this._detach():this._overlayRef.updatePosition()})):this._scrollSubscription=t.subscribe(this._detach)}disable(){this._scrollSubscription&&(this._scrollSubscription.unsubscribe(),this._scrollSubscription=null)}detach(){this.disable(),this._overlayRef=null}_detach=()=>{this.disable(),this._overlayRef.hasAttached()&&this._ngZone.run(()=>this._overlayRef.detach())}};var ao=class{enable(){}disable(){}attach(){}};function qd(n,t){return t.some(e=>{let i=n.bottom<e.top,a=n.top>e.bottom,r=n.right<e.left,o=n.left>e.right;return i||a||r||o})}function sg(n,t){return t.some(e=>{let i=n.top<e.top,a=n.bottom>e.bottom,r=n.left<e.left,o=n.right>e.right;return i||a||r||o})}function ro(n,t){return new hl(n.get(to),n.get(Un),n.get(W),t)}var hl=class{_scrollDispatcher;_viewportRuler;_ngZone;_config;_scrollSubscription=null;_overlayRef;constructor(t,e,i,a){this._scrollDispatcher=t,this._viewportRuler=e,this._ngZone=i,this._config=a}attach(t){this._overlayRef,this._overlayRef=t}enable(){if(!this._scrollSubscription){let t=this._config?this._config.scrollThrottle:0;this._scrollSubscription=this._scrollDispatcher.scrolled(t).subscribe(()=>{if(this._overlayRef.updatePosition(),this._config&&this._config.autoClose){let e=this._overlayRef.overlayElement.getBoundingClientRect(),{width:i,height:a}=this._viewportRuler.getViewportSize();qd(e,[{width:i,height:a,bottom:a,right:i,top:0,left:0}])&&(this.disable(),this._ngZone.run(()=>this._overlayRef.detach()))}})}}disable(){this._scrollSubscription&&(this._scrollSubscription.unsubscribe(),this._scrollSubscription=null)}detach(){this.disable(),this._overlayRef=null}},fg=(()=>{class n{_injector=p(ce);noop=()=>new ao;close=e=>hg(this._injector,e);block=()=>ug(this._injector);reposition=e=>ro(this._injector,e);static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),hi=class{positionStrategy;scrollStrategy=new ao;panelClass="";hasBackdrop=!1;backdropClass="cdk-overlay-dark-backdrop";disableAnimations;width;height;minWidth;minHeight;maxWidth;maxHeight;direction;disposeOnNavigation=!1;usePopover;eventPredicate;constructor(t){if(t){let e=Object.keys(t);for(let i of e)t[i]!==void 0&&(this[i]=t[i])}}};var fl=class{connectionPair;scrollableViewProperties;constructor(t,e){this.connectionPair=t,this.scrollableViewProperties=e}};var gg=(()=>{class n{_attachedOverlays=[];_document=p(Y);_isAttached=!1;ngOnDestroy(){this.detach()}add(e){this.remove(e),this._attachedOverlays.push(e)}remove(e){let i=this._attachedOverlays.indexOf(e);i>-1&&this._attachedOverlays.splice(i,1),this._attachedOverlays.length===0&&this.detach()}canReceiveEvent(e,i,a){return a.observers.length<1?!1:e.eventPredicate?e.eventPredicate(i):!0}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),bg=(()=>{class n extends gg{_ngZone=p(W);_renderer=p(xt).createRenderer(null,null);_cleanupKeydown;add(e){super.add(e),this._isAttached||(this._ngZone.runOutsideAngular(()=>{this._cleanupKeydown=this._renderer.listen("body","keydown",this._keydownListener)}),this._isAttached=!0)}detach(){this._isAttached&&(this._cleanupKeydown?.(),this._isAttached=!1)}_keydownListener=e=>{let i=this._attachedOverlays;for(let a=i.length-1;a>-1;a--){let r=i[a];if(this.canReceiveEvent(r,e,r._keydownEvents)){this._ngZone.run(()=>r._keydownEvents.next(e));break}}};static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),vg=(()=>{class n extends gg{_platform=p(Ne);_ngZone=p(W);_renderer=p(xt).createRenderer(null,null);_cursorOriginalValue;_cursorStyleIsSet=!1;_pointerDownEventTarget=null;_cleanups;add(e){if(super.add(e),!this._isAttached){let i=this._document.body,a={capture:!0},r=this._renderer;this._cleanups=this._ngZone.runOutsideAngular(()=>[r.listen(i,"pointerdown",this._pointerDownListener,a),r.listen(i,"click",this._clickListener,a),r.listen(i,"auxclick",this._clickListener,a),r.listen(i,"contextmenu",this._clickListener,a)]),this._platform.IOS&&!this._cursorStyleIsSet&&(this._cursorOriginalValue=i.style.cursor,i.style.cursor="pointer",this._cursorStyleIsSet=!0),this._isAttached=!0}}detach(){this._isAttached&&(this._cleanups?.forEach(e=>e()),this._cleanups=void 0,this._platform.IOS&&this._cursorStyleIsSet&&(this._document.body.style.cursor=this._cursorOriginalValue,this._cursorStyleIsSet=!1),this._isAttached=!1)}_pointerDownListener=e=>{this._pointerDownEventTarget=Bt(e)};_clickListener=e=>{let i=Bt(e),a=e.type==="click"&&this._pointerDownEventTarget?this._pointerDownEventTarget:i;this._pointerDownEventTarget=null;let r=this._attachedOverlays.slice();for(let o=r.length-1;o>-1;o--){let s=r[o],l=s._outsidePointerEvents;if(!(!s.hasAttached()||!this.canReceiveEvent(s,e,l))){if(lg(s.overlayElement,i)||lg(s.overlayElement,a))break;this._ngZone?this._ngZone.run(()=>l.next(e)):l.next(e)}}};static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();function lg(n,t){let e=typeof ShadowRoot<"u"&&ShadowRoot,i=t;for(;i;){if(i===n)return!0;i=e&&i instanceof ShadowRoot?i.host:i.parentNode}return!1}var _g=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275cmp=T({type:n,selectors:[["ng-component"]],hostAttrs:["cdk-overlay-style-loader",""],decls:0,vars:0,template:function(i,a){},styles:[`.cdk-overlay-container, .cdk-global-overlay-wrapper {
  pointer-events: none;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
}

.cdk-overlay-container {
  position: fixed;
}
@layer cdk-overlay {
  .cdk-overlay-container {
    z-index: 1000;
  }
}
.cdk-overlay-container:empty {
  display: none;
}

.cdk-global-overlay-wrapper {
  display: flex;
  position: absolute;
}
@layer cdk-overlay {
  .cdk-global-overlay-wrapper {
    z-index: 1000;
  }
}

.cdk-overlay-pane {
  position: absolute;
  pointer-events: auto;
  box-sizing: border-box;
  display: flex;
  max-width: 100%;
  max-height: 100%;
}
@layer cdk-overlay {
  .cdk-overlay-pane {
    z-index: 1000;
  }
}

.cdk-overlay-backdrop {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  pointer-events: auto;
  -webkit-tap-highlight-color: transparent;
  opacity: 0;
  touch-action: manipulation;
}
@layer cdk-overlay {
  .cdk-overlay-backdrop {
    z-index: 1000;
    transition: opacity 400ms cubic-bezier(0.25, 0.8, 0.25, 1);
  }
}
@media (prefers-reduced-motion) {
  .cdk-overlay-backdrop {
    transition-duration: 1ms;
  }
}

.cdk-overlay-backdrop-showing {
  opacity: 1;
}
@media (forced-colors: active) {
  .cdk-overlay-backdrop-showing {
    opacity: 0.6;
  }
}

@layer cdk-overlay {
  .cdk-overlay-dark-backdrop {
    background: rgba(0, 0, 0, 0.32);
  }
}

.cdk-overlay-transparent-backdrop {
  transition: visibility 1ms linear, opacity 1ms linear;
  visibility: hidden;
  opacity: 1;
}
.cdk-overlay-transparent-backdrop.cdk-overlay-backdrop-showing, .cdk-high-contrast-active .cdk-overlay-transparent-backdrop {
  opacity: 0;
  visibility: visible;
}

.cdk-overlay-backdrop-noop-animation {
  transition: none;
}

.cdk-overlay-connected-position-bounding-box {
  position: absolute;
  display: flex;
  flex-direction: column;
  min-width: 1px;
  min-height: 1px;
}
@layer cdk-overlay {
  .cdk-overlay-connected-position-bounding-box {
    z-index: 1000;
  }
}

.cdk-global-scrollblock {
  position: fixed;
  width: 100%;
  overflow-y: scroll;
}

.cdk-overlay-popover {
  background: none;
  border: none;
  padding: 0;
  outline: 0;
  overflow: visible;
  position: fixed;
  pointer-events: none;
  white-space: normal;
  color: inherit;
  text-decoration: none;
  width: 100%;
  height: 100%;
  inset: auto;
  top: 0;
  left: 0;
}
.cdk-overlay-popover::backdrop {
  display: none;
}
.cdk-overlay-popover .cdk-overlay-backdrop {
  position: fixed;
  z-index: auto;
}
`],encapsulation:2})}return n})(),yg=(()=>{class n{_platform=p(Ne);_containerElement;_document=p(Y);_styleLoader=p(ct);ngOnDestroy(){this._containerElement?.remove()}getContainerElement(){return this._loadStyles(),this._containerElement||this._createContainer(),this._containerElement}_createContainer(){let e="cdk-overlay-container";if(this._platform.isBrowser||$d()){let a=this._document.querySelectorAll(`.${e}[platform="server"], .${e}[platform="test"]`);for(let r=0;r<a.length;r++)a[r].remove()}let i=this._document.createElement("div");i.classList.add(e),$d()?i.setAttribute("platform","test"):this._platform.isBrowser||i.setAttribute("platform","server"),this._document.body.appendChild(i),this._containerElement=i}_loadStyles(){this._styleLoader.load(_g)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),Gd=class{_renderer;_ngZone;element;_cleanupClick;_cleanupTransitionEnd;_fallbackTimeout;constructor(t,e,i,a){this._renderer=e,this._ngZone=i,this.element=t.createElement("div"),this.element.classList.add("cdk-overlay-backdrop"),this._cleanupClick=e.listen(this.element,"click",a)}detach(){this._ngZone.runOutsideAngular(()=>{let t=this.element;clearTimeout(this._fallbackTimeout),this._cleanupTransitionEnd?.(),this._cleanupTransitionEnd=this._renderer.listen(t,"transitionend",this.dispose),this._fallbackTimeout=setTimeout(this.dispose,500),t.style.pointerEvents="none",t.classList.remove("cdk-overlay-backdrop-showing")})}dispose=()=>{clearTimeout(this._fallbackTimeout),this._cleanupClick?.(),this._cleanupTransitionEnd?.(),this._cleanupClick=this._cleanupTransitionEnd=this._fallbackTimeout=void 0,this.element.remove()}};function Wd(n){return n&&n.nodeType===1}var Ud=new Set,gl=class{_portalOutlet;_host;_pane;_config;_ngZone;_keyboardDispatcher;_document;_location;_outsideClickDispatcher;_animationsDisabled;_injector;_renderer;_backdropClick=new z;_attachments=new z;_detachments=new z;_positionStrategy;_scrollStrategy;_locationChanges=bt.EMPTY;_backdropRef=null;_detachContentMutationObserver;_detachContentAfterRenderRef;_disposed=!1;_previousHostParent;_keydownEvents=new z;_outsidePointerEvents=new z;_afterNextRenderRef;constructor(t,e,i,a,r,o,s,l,c,d=!1,m,u){this._portalOutlet=t,this._host=e,this._pane=i,this._config=a,this._ngZone=r,this._keyboardDispatcher=o,this._document=s,this._location=l,this._outsideClickDispatcher=c,this._animationsDisabled=d,this._injector=m,this._renderer=u,a.scrollStrategy&&(this._scrollStrategy=a.scrollStrategy,this._scrollStrategy.attach(this)),this._positionStrategy=a.positionStrategy}get overlayElement(){return this._pane}get backdropElement(){return this._backdropRef?.element||null}get hostElement(){return this._host}get eventPredicate(){return this._config?.eventPredicate||null}attach(t){if(this._disposed)return null;this._attachHost();let e=this._portalOutlet.attach(t);return this._positionStrategy?.attach(this),this._updateStackingOrder(),this._updateElementSize(),this._updateElementDirection(),Ud.add(this),this._scrollStrategy&&this._scrollStrategy.enable(),this._afterNextRenderRef?.destroy(),this._afterNextRenderRef=yt(()=>{this.hasAttached()&&this.updatePosition()},{injector:this._injector}),this._togglePointerEvents(!0),this._config.hasBackdrop&&this._attachBackdrop(),this._config.panelClass&&this._toggleClasses(this._pane,this._config.panelClass,!0),this._attachments.next(),this._completeDetachContent(),this._keyboardDispatcher.add(this),this._config.disposeOnNavigation&&(this._locationChanges=this._location.subscribe(()=>this.dispose())),this._outsideClickDispatcher.add(this),typeof e?.onDestroy=="function"&&e.onDestroy(()=>{this.hasAttached()&&this._ngZone.runOutsideAngular(()=>Promise.resolve().then(()=>this.detach()))}),e}detach(){if(!this.hasAttached())return;this.detachBackdrop(),this._togglePointerEvents(!1),this._positionStrategy&&this._positionStrategy.detach&&this._positionStrategy.detach(),this._scrollStrategy&&this._scrollStrategy.disable();let t=this._portalOutlet.detach();return this._detachments.next(),this._completeDetachContent(),this._keyboardDispatcher.remove(this),this._detachContentWhenEmpty(),this._locationChanges.unsubscribe(),this._outsideClickDispatcher.remove(this),Ud.delete(this),t}dispose(){if(this._disposed)return;let t=this.hasAttached();this._positionStrategy&&this._positionStrategy.dispose(),this._disposeScrollStrategy(),this._backdropRef?.dispose(),this._locationChanges.unsubscribe(),this._keyboardDispatcher.remove(this),this._portalOutlet.dispose(),this._attachments.complete(),this._backdropClick.complete(),this._keydownEvents.complete(),this._outsidePointerEvents.complete(),this._outsideClickDispatcher.remove(this),this._host?.remove(),this._afterNextRenderRef?.destroy(),this._previousHostParent=this._pane=this._host=this._backdropRef=null,t&&this._detachments.next(),this._detachments.complete(),this._completeDetachContent(),this._disposed=!0,Ud.delete(this)}hasAttached(){return this._portalOutlet.hasAttached()}backdropClick(){return this._backdropClick}attachments(){return this._attachments}detachments(){return this._detachments}keydownEvents(){return this._keydownEvents}outsidePointerEvents(){return this._outsidePointerEvents}getConfig(){return this._config}updatePosition(){this._positionStrategy&&this._positionStrategy.apply()}updatePositionStrategy(t){t!==this._positionStrategy&&(this._positionStrategy&&this._positionStrategy.dispose(),this._positionStrategy=t,this.hasAttached()&&(t.attach(this),this.updatePosition()))}updateSize(t){this._config=N(N({},this._config),t),this._updateElementSize()}setDirection(t){this._config=Ee(N({},this._config),{direction:t}),this._updateElementDirection()}addPanelClass(t){this._pane&&this._toggleClasses(this._pane,t,!0)}removePanelClass(t){this._pane&&this._toggleClasses(this._pane,t,!1)}getDirection(){let t=this._config.direction;return t?typeof t=="string"?t:t.value:"ltr"}updateScrollStrategy(t){t!==this._scrollStrategy&&(this._disposeScrollStrategy(),this._scrollStrategy=t,this.hasAttached()&&(t.attach(this),t.enable()))}_updateElementDirection(){this._host.setAttribute("dir",this.getDirection())}_updateElementSize(){if(!this._pane)return;let t=this._pane.style;t.width=nt(this._config.width),t.height=nt(this._config.height),t.minWidth=nt(this._config.minWidth),t.minHeight=nt(this._config.minHeight),t.maxWidth=nt(this._config.maxWidth),t.maxHeight=nt(this._config.maxHeight)}_togglePointerEvents(t){this._pane.style.pointerEvents=t?"":"none"}_attachHost(){if(!this._host.parentElement){let t=this._config.usePopover?this._positionStrategy?.getPopoverInsertionPoint?.():null;Wd(t)?t.after(this._host):t?.type==="parent"?t.element.appendChild(this._host):this._previousHostParent?.appendChild(this._host)}if(this._config.usePopover)try{this._host.showPopover()}catch{}}_attachBackdrop(){let t="cdk-overlay-backdrop-showing";this._backdropRef?.dispose(),this._backdropRef=new Gd(this._document,this._renderer,this._ngZone,e=>{this._backdropClick.next(e)}),this._animationsDisabled&&this._backdropRef.element.classList.add("cdk-overlay-backdrop-noop-animation"),this._config.backdropClass&&this._toggleClasses(this._backdropRef.element,this._config.backdropClass,!0),this._config.usePopover?this._host.prepend(this._backdropRef.element):this._host.parentElement.insertBefore(this._backdropRef.element,this._host),!this._animationsDisabled&&typeof requestAnimationFrame<"u"?this._ngZone.runOutsideAngular(()=>{requestAnimationFrame(()=>this._backdropRef?.element.classList.add(t))}):this._backdropRef.element.classList.add(t)}_updateStackingOrder(){!this._config.usePopover&&this._host.nextSibling&&this._host.parentNode.appendChild(this._host)}detachBackdrop(){this._animationsDisabled?(this._backdropRef?.dispose(),this._backdropRef=null):this._backdropRef?.detach()}_toggleClasses(t,e,i){let a=Na(e||[]).filter(r=>!!r);a.length&&(i?t.classList.add(...a):t.classList.remove(...a))}_detachContentWhenEmpty(){let t=!1;try{this._detachContentAfterRenderRef=yt(()=>{t=!0,this._detachContent()},{injector:this._injector})}catch(e){if(t)throw e;this._detachContent()}globalThis.MutationObserver&&this._pane&&(this._detachContentMutationObserver||=new globalThis.MutationObserver(()=>{this._detachContent()}),this._detachContentMutationObserver.observe(this._pane,{childList:!0}))}_detachContent(){(!this._pane||!this._host||this._pane.children.length===0)&&(this._pane&&this._config.panelClass&&this._toggleClasses(this._pane,this._config.panelClass,!1),this._host&&this._host.parentElement&&(this._previousHostParent=this._host.parentElement,this._host.remove()),this._completeDetachContent())}_completeDetachContent(){this._detachContentAfterRenderRef?.destroy(),this._detachContentAfterRenderRef=void 0,this._detachContentMutationObserver?.disconnect()}_disposeScrollStrategy(){let t=this._scrollStrategy;t?.disable(),t?.detach?.()}},cg="cdk-overlay-connected-position-bounding-box",Dw=/([A-Za-z%]+)$/;function _l(n,t){return new bl(t,n.get(Un),n.get(Y),n.get(Ne),n.get(yg))}var bl=class{_viewportRuler;_document;_platform;_overlayContainer;_overlayRef;_isInitialRender=!1;_lastBoundingBoxSize={width:0,height:0};_isPushed=!1;_canPush=!0;_growAfterOpen=!1;_hasFlexibleDimensions=!0;_positionLocked=!1;_originRect;_overlayRect;_viewportRect;_containerRect;_viewportMargin=0;_scrollables=[];_preferredPositions=[];_origin;_pane;_isDisposed=!1;_boundingBox=null;_lastPosition=null;_lastScrollVisibility=null;_positionChanges=new z;_resizeSubscription=bt.EMPTY;_offsetX=0;_offsetY=0;_transformOriginSelector;_appliedPanelClasses=[];_previousPushAmount=null;_popoverLocation="global";positionChanges=this._positionChanges;get positions(){return this._preferredPositions}constructor(t,e,i,a,r){this._viewportRuler=e,this._document=i,this._platform=a,this._overlayContainer=r,this.setOrigin(t)}attach(t){this._overlayRef&&this._overlayRef,this._validatePositions(),t.hostElement.classList.add(cg),this._overlayRef=t,this._boundingBox=t.hostElement,this._pane=t.overlayElement,this._isDisposed=!1,this._isInitialRender=!0,this._lastPosition=null,this._resizeSubscription.unsubscribe(),this._resizeSubscription=this._viewportRuler.change().subscribe(()=>{this._isInitialRender=!0,this.apply()})}apply(){if(this._isDisposed||!this._platform.isBrowser)return;if(!this._isInitialRender&&this._positionLocked&&this._lastPosition){this.reapplyLastPosition();return}this._clearPanelClasses(),this._resetOverlayElementStyles(),this._resetBoundingBoxStyles(),this._viewportRect=this._getNarrowedViewportRect(),this._originRect=this._getOriginRect(),this._overlayRect=this._pane.getBoundingClientRect(),this._containerRect=this._getContainerRect();let t=this._originRect,e=this._overlayRect,i=this._viewportRect,a=this._containerRect,r=[],o;for(let s of this._preferredPositions){let l=this._getOriginPoint(t,a,s),c=this._getOverlayPoint(l,e,s),d=this._getOverlayFit(c,e,i,s);if(d.isCompletelyWithinViewport){this._isPushed=!1,this._applyPosition(s,l);return}if(this._canFitWithFlexibleDimensions(d,c,i)){r.push({position:s,origin:l,overlayRect:e,boundingBoxRect:this._calculateBoundingBoxRect(l,s)});continue}(!o||o.overlayFit.visibleArea<d.visibleArea)&&(o={overlayFit:d,overlayPoint:c,originPoint:l,position:s,overlayRect:e})}if(r.length){let s=null,l=-1;for(let c of r){let d=c.boundingBoxRect.width*c.boundingBoxRect.height*(c.position.weight||1);d>l&&(l=d,s=c)}this._isPushed=!1,this._applyPosition(s.position,s.origin);return}if(this._canPush){this._isPushed=!0,this._applyPosition(o.position,o.originPoint);return}this._applyPosition(o.position,o.originPoint)}detach(){this._clearPanelClasses(),this._lastPosition=null,this._previousPushAmount=null,this._resizeSubscription.unsubscribe()}dispose(){this._isDisposed||(this._boundingBox&&zi(this._boundingBox.style,{top:"",left:"",right:"",bottom:"",height:"",width:"",alignItems:"",justifyContent:""}),this._pane&&this._resetOverlayElementStyles(),this._overlayRef&&this._overlayRef.hostElement.classList.remove(cg),this.detach(),this._positionChanges.complete(),this._overlayRef=this._boundingBox=null,this._isDisposed=!0)}reapplyLastPosition(){if(this._isDisposed||!this._platform.isBrowser)return;let t=this._lastPosition;t?(this._originRect=this._getOriginRect(),this._overlayRect=this._pane.getBoundingClientRect(),this._viewportRect=this._getNarrowedViewportRect(),this._containerRect=this._getContainerRect(),this._applyPosition(t,this._getOriginPoint(this._originRect,this._containerRect,t))):this.apply()}withScrollableContainers(t){return this._scrollables=t,this}withPositions(t){return this._preferredPositions=t,t.indexOf(this._lastPosition)===-1&&(this._lastPosition=null),this._validatePositions(),this}withViewportMargin(t){return this._viewportMargin=t,this}withFlexibleDimensions(t=!0){return this._hasFlexibleDimensions=t,this}withGrowAfterOpen(t=!0){return this._growAfterOpen=t,this}withPush(t=!0){return this._canPush=t,this}withLockedPosition(t=!0){return this._positionLocked=t,this}setOrigin(t){return this._origin=t,this}withDefaultOffsetX(t){return this._offsetX=t,this}withDefaultOffsetY(t){return this._offsetY=t,this}withTransformOriginOn(t){return this._transformOriginSelector=t,this}withPopoverLocation(t){return this._popoverLocation=t,this}getPopoverInsertionPoint(){return this._popoverLocation==="global"?null:this._popoverLocation!=="inline"?this._popoverLocation:this._origin instanceof X?this._origin.nativeElement:Wd(this._origin)?this._origin:null}_getOriginPoint(t,e,i){let a;if(i.originX=="center")a=t.left+t.width/2;else{let o=this._isRtl()?t.right:t.left,s=this._isRtl()?t.left:t.right;a=i.originX=="start"?o:s}e.left<0&&(a-=e.left);let r;return i.originY=="center"?r=t.top+t.height/2:r=i.originY=="top"?t.top:t.bottom,e.top<0&&(r-=e.top),{x:a,y:r}}_getOverlayPoint(t,e,i){let a;i.overlayX=="center"?a=-e.width/2:i.overlayX==="start"?a=this._isRtl()?-e.width:0:a=this._isRtl()?0:-e.width;let r;return i.overlayY=="center"?r=-e.height/2:r=i.overlayY=="top"?0:-e.height,{x:t.x+a,y:t.y+r}}_getOverlayFit(t,e,i,a){let r=mg(e),{x:o,y:s}=t,l=this._getOffset(a,"x"),c=this._getOffset(a,"y");l&&(o+=l),c&&(s+=c);let d=0-o,m=o+r.width-i.width,u=0-s,f=s+r.height-i.height,g=this._subtractOverflows(r.width,d,m),b=this._subtractOverflows(r.height,u,f),_=g*b;return{visibleArea:_,isCompletelyWithinViewport:r.width*r.height===_,fitsInViewportVertically:b===r.height,fitsInViewportHorizontally:g==r.width}}_canFitWithFlexibleDimensions(t,e,i){if(this._hasFlexibleDimensions){let a=i.bottom-e.y,r=i.right-e.x,o=dg(this._overlayRef.getConfig().minHeight),s=dg(this._overlayRef.getConfig().minWidth),l=t.fitsInViewportVertically||o!=null&&o<=a,c=t.fitsInViewportHorizontally||s!=null&&s<=r;return l&&c}return!1}_pushOverlayOnScreen(t,e,i){if(this._previousPushAmount&&this._positionLocked)return{x:t.x+this._previousPushAmount.x,y:t.y+this._previousPushAmount.y};let a=mg(e),r=this._viewportRect,o=Math.max(t.x+a.width-r.width,0),s=Math.max(t.y+a.height-r.height,0),l=Math.max(r.top-i.top-t.y,0),c=Math.max(r.left-i.left-t.x,0),d=0,m=0;return a.width<=r.width?d=c||-o:d=t.x<this._getViewportMarginStart()?r.left-i.left-t.x:0,a.height<=r.height?m=l||-s:m=t.y<this._getViewportMarginTop()?r.top-i.top-t.y:0,this._previousPushAmount={x:d,y:m},{x:t.x+d,y:t.y+m}}_applyPosition(t,e){if(this._setTransformOrigin(t),this._setOverlayElementStyles(e,t),this._setBoundingBoxStyles(e,t),t.panelClass&&this._addPanelClasses(t.panelClass),this._positionChanges.observers.length){let i=this._getScrollVisibility();if(t!==this._lastPosition||!this._lastScrollVisibility||!Mw(this._lastScrollVisibility,i)){let a=new fl(t,i);this._positionChanges.next(a)}this._lastScrollVisibility=i}this._lastPosition=t,this._isInitialRender=!1}_setTransformOrigin(t){if(!this._transformOriginSelector)return;let e=this._boundingBox.querySelectorAll(this._transformOriginSelector),i,a=t.overlayY;t.overlayX==="center"?i="center":this._isRtl()?i=t.overlayX==="start"?"right":"left":i=t.overlayX==="start"?"left":"right";for(let r=0;r<e.length;r++)e[r].style.transformOrigin=`${i} ${a}`}_calculateBoundingBoxRect(t,e){let i=this._viewportRect,a=this._isRtl(),r,o,s;if(e.overlayY==="top")o=t.y,r=i.height-o+this._getViewportMarginBottom();else if(e.overlayY==="bottom")s=i.height-t.y+this._getViewportMarginTop()+this._getViewportMarginBottom(),r=i.height-s+this._getViewportMarginTop();else{let f=Math.min(i.bottom-t.y+i.top,t.y),g=this._lastBoundingBoxSize.height;r=f*2,o=t.y-f,r>g&&!this._isInitialRender&&!this._growAfterOpen&&(o=t.y-g/2)}let l=e.overlayX==="start"&&!a||e.overlayX==="end"&&a,c=e.overlayX==="end"&&!a||e.overlayX==="start"&&a,d,m,u;if(c)u=i.width-t.x+this._getViewportMarginStart()+this._getViewportMarginEnd(),d=t.x-this._getViewportMarginStart();else if(l)m=t.x,d=i.right-t.x-this._getViewportMarginEnd();else{let f=Math.min(i.right-t.x+i.left,t.x),g=this._lastBoundingBoxSize.width;d=f*2,m=t.x-f,d>g&&!this._isInitialRender&&!this._growAfterOpen&&(m=t.x-g/2)}return{top:o,left:m,bottom:s,right:u,width:d,height:r}}_setBoundingBoxStyles(t,e){let i=this._calculateBoundingBoxRect(t,e);!this._isInitialRender&&!this._growAfterOpen&&(i.height=Math.min(i.height,this._lastBoundingBoxSize.height),i.width=Math.min(i.width,this._lastBoundingBoxSize.width));let a={};if(this._hasExactPosition())a.top=a.left="0",a.bottom=a.right="auto",a.maxHeight=a.maxWidth="",a.width=a.height="100%";else{let r=this._overlayRef.getConfig().maxHeight,o=this._overlayRef.getConfig().maxWidth;a.width=nt(i.width),a.height=nt(i.height),a.top=nt(i.top)||"auto",a.bottom=nt(i.bottom)||"auto",a.left=nt(i.left)||"auto",a.right=nt(i.right)||"auto",e.overlayX==="center"?a.alignItems="center":a.alignItems=e.overlayX==="end"?"flex-end":"flex-start",e.overlayY==="center"?a.justifyContent="center":a.justifyContent=e.overlayY==="bottom"?"flex-end":"flex-start",r&&(a.maxHeight=nt(r)),o&&(a.maxWidth=nt(o))}this._lastBoundingBoxSize=i,zi(this._boundingBox.style,a)}_resetBoundingBoxStyles(){zi(this._boundingBox.style,{top:"0",left:"0",right:"0",bottom:"0",height:"",width:"",alignItems:"",justifyContent:""})}_resetOverlayElementStyles(){zi(this._pane.style,{top:"",left:"",bottom:"",right:"",position:"",transform:""})}_setOverlayElementStyles(t,e){let i={},a=this._hasExactPosition(),r=this._hasFlexibleDimensions,o=this._overlayRef.getConfig();if(a){let d=this._viewportRuler.getViewportScrollPosition();zi(i,this._getExactOverlayY(e,t,d)),zi(i,this._getExactOverlayX(e,t,d))}else i.position="static";let s="",l=this._getOffset(e,"x"),c=this._getOffset(e,"y");l&&(s+=`translateX(${l}px) `),c&&(s+=`translateY(${c}px)`),i.transform=s.trim(),o.maxHeight&&(a?i.maxHeight=nt(o.maxHeight):r&&(i.maxHeight="")),o.maxWidth&&(a?i.maxWidth=nt(o.maxWidth):r&&(i.maxWidth="")),zi(this._pane.style,i)}_getExactOverlayY(t,e,i){let a={top:"",bottom:""},r=this._getOverlayPoint(e,this._overlayRect,t);if(this._isPushed&&(r=this._pushOverlayOnScreen(r,this._overlayRect,i)),t.overlayY==="bottom"){let o=this._document.documentElement.clientHeight;a.bottom=`${o-(r.y+this._overlayRect.height)}px`}else a.top=nt(r.y);return a}_getExactOverlayX(t,e,i){let a={left:"",right:""},r=this._getOverlayPoint(e,this._overlayRect,t);this._isPushed&&(r=this._pushOverlayOnScreen(r,this._overlayRect,i));let o;if(this._isRtl()?o=t.overlayX==="end"?"left":"right":o=t.overlayX==="end"?"right":"left",o==="right"){let s=this._document.documentElement.clientWidth;a.right=`${s-(r.x+this._overlayRect.width)}px`}else a.left=nt(r.x);return a}_getScrollVisibility(){let t=this._getOriginRect(),e=this._pane.getBoundingClientRect(),i=this._scrollables.map(a=>a.getElementRef().nativeElement.getBoundingClientRect());return{isOriginClipped:sg(t,i),isOriginOutsideView:qd(t,i),isOverlayClipped:sg(e,i),isOverlayOutsideView:qd(e,i)}}_subtractOverflows(t,...e){return e.reduce((i,a)=>i-Math.max(a,0),t)}_getNarrowedViewportRect(){let t=this._document.documentElement.clientWidth,e=this._document.documentElement.clientHeight,i=this._viewportRuler.getViewportScrollPosition();return{top:i.top+this._getViewportMarginTop(),left:i.left+this._getViewportMarginStart(),right:i.left+t-this._getViewportMarginEnd(),bottom:i.top+e-this._getViewportMarginBottom(),width:t-this._getViewportMarginStart()-this._getViewportMarginEnd(),height:e-this._getViewportMarginTop()-this._getViewportMarginBottom()}}_isRtl(){return this._overlayRef.getDirection()==="rtl"}_hasExactPosition(){return!this._hasFlexibleDimensions||this._isPushed}_getOffset(t,e){return e==="x"?t.offsetX==null?this._offsetX:t.offsetX:t.offsetY==null?this._offsetY:t.offsetY}_validatePositions(){}_addPanelClasses(t){this._pane&&Na(t).forEach(e=>{e!==""&&this._appliedPanelClasses.indexOf(e)===-1&&(this._appliedPanelClasses.push(e),this._pane.classList.add(e))})}_clearPanelClasses(){this._pane&&(this._appliedPanelClasses.forEach(t=>{this._pane.classList.remove(t)}),this._appliedPanelClasses=[])}_getViewportMarginStart(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.start??0}_getViewportMarginEnd(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.end??0}_getViewportMarginTop(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.top??0}_getViewportMarginBottom(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.bottom??0}_getOriginRect(){let t=this._origin;if(t instanceof X)return t.nativeElement.getBoundingClientRect();if(t instanceof Element)return t.getBoundingClientRect();let e=t.width||0,i=t.height||0;return{top:t.y,bottom:t.y+i,left:t.x,right:t.x+e,height:i,width:e}}_getContainerRect(){let t=this._overlayRef.getConfig().usePopover&&this._popoverLocation!=="global",e=this._overlayContainer.getContainerElement();t&&(e.style.display="block");let i=e.getBoundingClientRect();return t&&(e.style.display=""),i}};function zi(n,t){for(let e in t)Object.hasOwn(t,e)&&(n[e]=t[e]);return n}function dg(n){if(typeof n!="number"&&n!=null){let[t,e]=n.split(Dw);return!e||e==="px"?parseFloat(t):null}return n||null}function mg(n){return{top:Math.floor(n.top),right:Math.floor(n.right),bottom:Math.floor(n.bottom),left:Math.floor(n.left),width:Math.floor(n.width),height:Math.floor(n.height)}}function Mw(n,t){return n===t?!0:n.isOriginClipped===t.isOriginClipped&&n.isOriginOutsideView===t.isOriginOutsideView&&n.isOverlayClipped===t.isOverlayClipped&&n.isOverlayOutsideView===t.isOverlayOutsideView}var pg="cdk-global-overlay-wrapper";function yl(n){return new vl}var vl=class{_overlayRef;_cssPosition="static";_topOffset="";_bottomOffset="";_alignItems="";_xPosition="";_xOffset="";_width="";_height="";_isDisposed=!1;attach(t){let e=t.getConfig();this._overlayRef=t,this._width&&!e.width&&t.updateSize({width:this._width}),this._height&&!e.height&&t.updateSize({height:this._height}),t.hostElement.classList.add(pg),this._isDisposed=!1}top(t=""){return this._bottomOffset="",this._topOffset=t,this._alignItems="flex-start",this}left(t=""){return this._xOffset=t,this._xPosition="left",this}bottom(t=""){return this._topOffset="",this._bottomOffset=t,this._alignItems="flex-end",this}right(t=""){return this._xOffset=t,this._xPosition="right",this}start(t=""){return this._xOffset=t,this._xPosition="start",this}end(t=""){return this._xOffset=t,this._xPosition="end",this}width(t=""){return this._overlayRef?this._overlayRef.updateSize({width:t}):this._width=t,this}height(t=""){return this._overlayRef?this._overlayRef.updateSize({height:t}):this._height=t,this}centerHorizontally(t=""){return this.left(t),this._xPosition="center",this}centerVertically(t=""){return this.top(t),this._alignItems="center",this}apply(){if(!this._overlayRef||!this._overlayRef.hasAttached())return;let t=this._overlayRef.overlayElement.style,e=this._overlayRef.hostElement.style,i=this._overlayRef.getConfig(),{width:a,height:r,maxWidth:o,maxHeight:s}=i,l=(a==="100%"||a==="100vw")&&(!o||o==="100%"||o==="100vw"),c=(r==="100%"||r==="100vh")&&(!s||s==="100%"||s==="100vh"),d=this._xPosition,m=this._xOffset,u=this._overlayRef.getConfig().direction==="rtl",f="",g="",b="";l?b="flex-start":d==="center"?(b="center",u?g=m:f=m):u?d==="left"||d==="end"?(b="flex-end",f=m):(d==="right"||d==="start")&&(b="flex-start",g=m):d==="left"||d==="start"?(b="flex-start",f=m):(d==="right"||d==="end")&&(b="flex-end",g=m),t.position=this._cssPosition,t.marginLeft=l?"0":f,t.marginTop=c?"0":this._topOffset,t.marginBottom=this._bottomOffset,t.marginRight=l?"0":g,e.justifyContent=b,e.alignItems=c?"flex-start":this._alignItems}dispose(){if(this._isDisposed||!this._overlayRef)return;let t=this._overlayRef.overlayElement.style,e=this._overlayRef.hostElement,i=e.style;e.classList.remove(pg),i.justifyContent=i.alignItems=t.marginTop=t.marginBottom=t.marginLeft=t.marginRight=t.position="",this._overlayRef=null,this._isDisposed=!0}},xg=(()=>{class n{_injector=p(ce);global(){return yl()}flexibleConnectedTo(e){return _l(this._injector,e)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),Kd=new I("OVERLAY_DEFAULT_CONFIG");function Pa(n,t){n.get(ct).load(_g);let e=n.get(yg),i=n.get(Y),a=n.get(St),r=n.get(ri),o=n.get(Tt),s=n.get(pt,null,{optional:!0})||n.get(xt).createRenderer(null,null),l=new hi(t),c=n.get(Kd,null,{optional:!0})?.usePopover??!0;l.direction=l.direction||o.value,!i.body||!("showPopover"in i.body)?l.usePopover=!1:l.usePopover=t?.usePopover??c;let d=i.createElement("div"),m=i.createElement("div");d.id=a.getId("cdk-overlay-"),d.classList.add("cdk-overlay-pane"),m.appendChild(d),l.usePopover&&(m.setAttribute("popover","manual"),m.classList.add("cdk-overlay-popover"));let u=l.usePopover?l.positionStrategy?.getPopoverInsertionPoint?.():null;return Wd(u)?u.after(m):u?.type==="parent"?u.element.appendChild(m):e.getContainerElement().appendChild(m),new gl(new io(d,r,n),m,d,l,n.get(W),n.get(bg),i,n.get(xa),n.get(vg),t?.disableAnimations??n.get(ua,null,{optional:!0})==="NoopAnimations",n.get(Ln),s)}var wg=(()=>{class n{scrollStrategies=p(fg);_positionBuilder=p(xg);_injector=p(ce);create(e){return Pa(this._injector,e)}position(){return this._positionBuilder}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var Hi=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({providers:[wg],imports:[me,Ra,zd,zd]})}return n})();var Tw=new I("MATERIAL_ANIMATIONS"),Sg=null;function Ow(){return p(Tw,{optional:!0})?.animationsDisabled||p(ua,{optional:!0})==="NoopAnimations"?"di-disabled":(Sg??=p(nl).matchMedia("(prefers-reduced-motion)").matches,Sg?"reduced-motion":"enabled")}function tt(){return Ow()!=="enabled"}function Rt(n){return n!=null&&`${n}`!="false"}var Jt=(function(n){return n[n.FADING_IN=0]="FADING_IN",n[n.VISIBLE=1]="VISIBLE",n[n.FADING_OUT=2]="FADING_OUT",n[n.HIDDEN=3]="HIDDEN",n})(Jt||{}),Yd=class{_renderer;element;config;_animationForciblyDisabledThroughCss;state=Jt.HIDDEN;constructor(t,e,i,a=!1){this._renderer=t,this.element=e,this.config=i,this._animationForciblyDisabledThroughCss=a}fadeOut(){this._renderer.fadeOutRipple(this)}},kg=Ma({passive:!0,capture:!0}),Xd=class{_events=new Map;addHandler(t,e,i,a){let r=this._events.get(e);if(r){let o=r.get(i);o?o.add(a):r.set(i,new Set([a]))}else this._events.set(e,new Map([[i,new Set([a])]])),t.runOutsideAngular(()=>{document.addEventListener(e,this._delegateEventHandler,kg)})}removeHandler(t,e,i){let a=this._events.get(t);if(!a)return;let r=a.get(e);r&&(r.delete(i),r.size===0&&a.delete(e),a.size===0&&(this._events.delete(t),document.removeEventListener(t,this._delegateEventHandler,kg)))}_delegateEventHandler=t=>{let e=Bt(t);e&&this._events.get(t.type)?.forEach((i,a)=>{(a===e||a.contains(e))&&i.forEach(r=>r.handleEvent(t))})}},oo={enterDuration:225,exitDuration:150},Rw=800,Cg=Ma({passive:!0,capture:!0}),Eg=["mousedown","touchstart"],Dg=["mouseup","mouseleave","touchend","touchcancel"],Pw=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275cmp=T({type:n,selectors:[["ng-component"]],hostAttrs:["mat-ripple-style-loader",""],decls:0,vars:0,template:function(i,a){},styles:[`.mat-ripple {
  overflow: hidden;
  position: relative;
}
.mat-ripple:not(:empty) {
  transform: translateZ(0);
}

.mat-ripple.mat-ripple-unbounded {
  overflow: visible;
}

.mat-ripple-element {
  position: absolute;
  border-radius: 50%;
  pointer-events: none;
  transition: opacity, transform 0ms cubic-bezier(0, 0, 0.2, 1);
  transform: scale3d(0, 0, 0);
  background-color: var(--%NS%mat-ripple-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 10%, transparent));
}
@media (forced-colors: active) {
  .mat-ripple-element {
    display: none;
  }
}
.cdk-drag-preview .mat-ripple-element, .cdk-drag-placeholder .mat-ripple-element {
  display: none;
}
`],encapsulation:2})}return n})(),Ui=class n{_target;_ngZone;_platform;_containerElement;_triggerElement=null;_isPointerDown=!1;_activeRipples=new Map;_mostRecentTransientRipple=null;_lastTouchStartEvent;_pointerUpEventsRegistered=!1;_containerRect=null;static _eventManager=new Xd;constructor(t,e,i,a,r){this._target=t,this._ngZone=e,this._platform=a,a.isBrowser&&(this._containerElement=Qt(i)),r&&r.get(ct).load(Pw)}fadeInRipple(t,e,i={}){let a=this._containerRect=this._containerRect||this._containerElement.getBoundingClientRect(),r=N(N({},oo),i.animation);i.centered&&(t=a.left+a.width/2,e=a.top+a.height/2);let o=i.radius||Fw(t,e,a),s=t-a.left,l=e-a.top,c=r.enterDuration,d=document.createElement("div");d.classList.add("mat-ripple-element"),d.style.left=`${s-o}px`,d.style.top=`${l-o}px`,d.style.height=`${o*2}px`,d.style.width=`${o*2}px`,i.color!=null&&(d.style.backgroundColor=i.color),d.style.transitionDuration=`${c}ms`,this._containerElement.appendChild(d);let m=window.getComputedStyle(d),u=m.transitionProperty,f=m.transitionDuration,g=u==="none"||f==="0s"||f==="0s, 0s"||a.width===0&&a.height===0,b=new Yd(this,d,i,g);d.style.transform="scale3d(1, 1, 1)",b.state=Jt.FADING_IN,i.persistent||(this._mostRecentTransientRipple=b);let _=null;return!g&&(c||r.exitDuration)&&this._ngZone.runOutsideAngular(()=>{let x=()=>{_&&(_.fallbackTimer=null),clearTimeout(C),this._finishRippleTransition(b)},w=()=>this._destroyRipple(b),C=setTimeout(w,c+100);d.addEventListener("transitionend",x),d.addEventListener("transitioncancel",w),_={onTransitionEnd:x,onTransitionCancel:w,fallbackTimer:C}}),this._activeRipples.set(b,_),(g||!c)&&this._finishRippleTransition(b),b}fadeOutRipple(t){if(t.state===Jt.FADING_OUT||t.state===Jt.HIDDEN)return;let e=t.element,i=N(N({},oo),t.config.animation);e.style.transitionDuration=`${i.exitDuration}ms`,e.style.opacity="0",t.state=Jt.FADING_OUT,(t._animationForciblyDisabledThroughCss||!i.exitDuration)&&this._finishRippleTransition(t)}fadeOutAll(){this._getActiveRipples().forEach(t=>t.fadeOut())}fadeOutAllNonPersistent(){this._getActiveRipples().forEach(t=>{t.config.persistent||t.fadeOut()})}setupTriggerEvents(t){let e=Qt(t);!this._platform.isBrowser||!e||e===this._triggerElement||(this._removeTriggerEvents(),this._triggerElement=e,Eg.forEach(i=>{n._eventManager.addHandler(this._ngZone,i,e,this)}))}handleEvent(t){t.type==="mousedown"?this._onMousedown(t):t.type==="touchstart"?this._onTouchStart(t):this._onPointerUp(),this._pointerUpEventsRegistered||(this._ngZone.runOutsideAngular(()=>{Dg.forEach(e=>{this._triggerElement.addEventListener(e,this,Cg)})}),this._pointerUpEventsRegistered=!0)}_finishRippleTransition(t){t.state===Jt.FADING_IN?this._startFadeOutTransition(t):t.state===Jt.FADING_OUT&&this._destroyRipple(t)}_startFadeOutTransition(t){let e=t===this._mostRecentTransientRipple,{persistent:i}=t.config;t.state=Jt.VISIBLE,!i&&(!e||!this._isPointerDown)&&t.fadeOut()}_destroyRipple(t){let e=this._activeRipples.get(t)??null;this._activeRipples.delete(t),this._activeRipples.size||(this._containerRect=null),t===this._mostRecentTransientRipple&&(this._mostRecentTransientRipple=null),t.state=Jt.HIDDEN,e!==null&&(t.element.removeEventListener("transitionend",e.onTransitionEnd),t.element.removeEventListener("transitioncancel",e.onTransitionCancel),e.fallbackTimer!==null&&clearTimeout(e.fallbackTimer)),t.element.remove()}_onMousedown(t){let e=Li(t),i=this._lastTouchStartEvent&&Date.now()<this._lastTouchStartEvent+Rw;!this._target.rippleDisabled&&!e&&!i&&(this._isPointerDown=!0,this.fadeInRipple(t.clientX,t.clientY,this._target.rippleConfig))}_onTouchStart(t){if(!this._target.rippleDisabled&&!Bi(t)){this._lastTouchStartEvent=Date.now(),this._isPointerDown=!0;let e=t.changedTouches;if(e)for(let i=0;i<e.length;i++)this.fadeInRipple(e[i].clientX,e[i].clientY,this._target.rippleConfig)}}_onPointerUp(){this._isPointerDown&&(this._isPointerDown=!1,this._getActiveRipples().forEach(t=>{let e=t.state===Jt.VISIBLE||t.config.terminateOnPointerUp&&t.state===Jt.FADING_IN;!t.config.persistent&&e&&t.fadeOut()}))}_getActiveRipples(){return Array.from(this._activeRipples.keys())}_removeTriggerEvents(){let t=this._triggerElement;t&&(Eg.forEach(e=>n._eventManager.removeHandler(e,t,this)),this._pointerUpEventsRegistered&&(Dg.forEach(e=>t.removeEventListener(e,this,Cg)),this._pointerUpEventsRegistered=!1))}};function Fw(n,t,e){let i=Math.max(Math.abs(n-e.left),Math.abs(n-e.right)),a=Math.max(Math.abs(t-e.top),Math.abs(t-e.bottom));return Math.sqrt(i*i+a*a)}var qi=new I("mat-ripple-global-options"),xl=(()=>{class n{_elementRef=p(X);_animationsDisabled=tt();color;unbounded=!1;centered=!1;radius=0;animation;get disabled(){return this._disabled}set disabled(e){e&&this.fadeOutAllNonPersistent(),this._disabled=e,this._setupTriggerEventsIfEnabled()}_disabled=!1;get trigger(){return this._trigger||this._elementRef.nativeElement}set trigger(e){this._trigger=e,this._setupTriggerEventsIfEnabled()}_trigger;_rippleRenderer;_globalOptions;_isInitialized=!1;constructor(){let e=p(W),i=p(Ne),a=p(qi,{optional:!0}),r=p(ce);this._globalOptions=a||{},this._rippleRenderer=new Ui(this,e,this._elementRef,i,r)}ngOnInit(){this._isInitialized=!0,this._setupTriggerEventsIfEnabled()}ngOnDestroy(){this._rippleRenderer._removeTriggerEvents()}fadeOutAll(){this._rippleRenderer.fadeOutAll()}fadeOutAllNonPersistent(){this._rippleRenderer.fadeOutAllNonPersistent()}get rippleConfig(){return{centered:this.centered,radius:this.radius,color:this.color,animation:N(N(N({},this._globalOptions.animation),this._animationsDisabled?{enterDuration:0,exitDuration:0}:{}),this.animation),terminateOnPointerUp:this._globalOptions.terminateOnPointerUp}}get rippleDisabled(){return this.disabled||!!this._globalOptions.disabled}_setupTriggerEventsIfEnabled(){!this.disabled&&this._isInitialized&&this._rippleRenderer.setupTriggerEvents(this.trigger)}launch(e,i=0,a){return typeof e=="number"?this._rippleRenderer.fadeInRipple(e,i,N(N({},this.rippleConfig),a)):this._rippleRenderer.fadeInRipple(0,0,N(N({},this.rippleConfig),e))}static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","mat-ripple",""],["","matRipple",""]],hostAttrs:[1,"mat-ripple"],hostVars:2,hostBindings:function(i,a){i&2&&J("mat-ripple-unbounded",a.unbounded)},inputs:{color:[0,"matRippleColor","color"],unbounded:[0,"matRippleUnbounded","unbounded"],centered:[0,"matRippleCentered","centered"],radius:[0,"matRippleRadius","radius"],animation:[0,"matRippleAnimation","animation"],disabled:[0,"matRippleDisabled","disabled"],trigger:[0,"matRippleTrigger","trigger"]},exportAs:["matRipple"]})}return n})();var Lw={capture:!0},Bw=["focus","mousedown","mouseenter","touchstart"],Zd="mat-ripple-loader-uninitialized",Qd="mat-ripple-loader-class-name",Mg="mat-ripple-loader-centered",wl="mat-ripple-loader-disabled",Sl=(()=>{class n{_document=p(Y);_animationsDisabled=tt();_globalRippleOptions=p(qi,{optional:!0});_platform=p(Ne);_ngZone=p(W);_injector=p(ce);_eventCleanups;_hosts=new Map;constructor(){let e=p(xt).createRenderer(null,null);this._eventCleanups=this._ngZone.runOutsideAngular(()=>Bw.map(i=>e.listen(this._document,i,this._onInteraction,Lw)))}ngOnDestroy(){let e=this._hosts.keys();for(let i of e)this.destroyRipple(i);this._eventCleanups.forEach(i=>i())}configureRipple(e,i){e.setAttribute(Zd,this._globalRippleOptions?.namespace??""),(i.className||!e.hasAttribute(Qd))&&e.setAttribute(Qd,i.className||""),i.centered&&e.setAttribute(Mg,""),i.disabled&&e.setAttribute(wl,"")}setDisabled(e,i){let a=this._hosts.get(e);a?(a.target.rippleDisabled=i,!i&&!a.hasSetUpEvents&&(a.hasSetUpEvents=!0,a.renderer.setupTriggerEvents(e))):i?e.setAttribute(wl,""):e.removeAttribute(wl)}_onInteraction=e=>{let i=Bt(e);if(i instanceof HTMLElement){let a=i.closest(`[${Zd}="${this._globalRippleOptions?.namespace??""}"]`);a&&this._createRipple(a)}};_createRipple(e){if(!this._document||this._hosts.has(e))return;e.querySelector(".mat-ripple")?.remove();let i=this._document.createElement("span");i.classList.add("mat-ripple",e.getAttribute(Qd)),e.append(i);let a=this._globalRippleOptions,r=this._animationsDisabled?0:a?.animation?.enterDuration??oo.enterDuration,o=this._animationsDisabled?0:a?.animation?.exitDuration??oo.exitDuration,s={rippleDisabled:this._animationsDisabled||a?.disabled||e.hasAttribute(wl),rippleConfig:{centered:e.hasAttribute(Mg),terminateOnPointerUp:a?.terminateOnPointerUp,animation:{enterDuration:r,exitDuration:o}}},l=new Ui(s,this._ngZone,i,this._platform,this._injector),c=!s.rippleDisabled;c&&l.setupTriggerEvents(e),this._hosts.set(e,{target:s,renderer:l,hasSetUpEvents:c}),e.removeAttribute(Zd)}destroyRipple(e){let i=this._hosts.get(e);i&&(i.renderer._removeTriggerEvents(),this._hosts.delete(e))}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var un=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275cmp=T({type:n,selectors:[["structural-styles"]],decls:0,vars:0,template:function(i,a){},styles:[`.mat-focus-indicator {
  position: relative;
}
.mat-focus-indicator::before {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  box-sizing: border-box;
  pointer-events: none;
  display: var(--%NS%mat-focus-indicator-display, none);
  border-width: var(--%NS%mat-focus-indicator-border-width, 3px);
  border-style: var(--%NS%mat-focus-indicator-border-style, solid);
  border-color: var(--%NS%mat-focus-indicator-border-color, transparent);
  border-radius: var(--%NS%mat-focus-indicator-border-radius, 4px);
}
.mat-focus-indicator:focus-visible::before {
  content: "";
}

@media (forced-colors: active) {
  html {
    --%NS%mat-focus-indicator-display: block;
    --%NS%mat-focus-indicator-fallback-border-style: none;
  }
}
`],encapsulation:2})}return n})();var jw=new I("MAT_BUTTON_CONFIG");function Ng(n){return n==null?void 0:Or(n)}var Ig=(()=>{class n{_elementRef=p(X);_ngZone=p(W);_animationsDisabled=tt();_config=p(jw,{optional:!0});_focusMonitor=p(Nn);_cleanupClick;_renderer=p(pt);_rippleLoader=p(Sl);_isAnchor;_isFab=!1;color;get disableRipple(){return this._disableRipple}set disableRipple(e){this._disableRipple=e,this._updateRippleDisabled()}_disableRipple=!1;get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._updateRippleDisabled()}_disabled=!1;ariaDisabled;disabledInteractive;tabIndex;set _tabindex(e){this.tabIndex=e}showProgress=ht(!1,{transform:Ae});constructor(){p(ct).load(un);let e=this._elementRef.nativeElement;this._isAnchor=e.tagName==="A",this.disabledInteractive=this._config?.disabledInteractive??!1,this.color=this._config?.color??null,this._rippleLoader?.configureRipple(e,{className:"mat-mdc-button-ripple"})}ngAfterViewInit(){this._focusMonitor.monitor(this._elementRef,!0),this._isAnchor&&this._setupAsAnchor()}ngOnDestroy(){this._cleanupClick?.(),this._focusMonitor.stopMonitoring(this._elementRef),this._rippleLoader?.destroyRipple(this._elementRef.nativeElement)}focus(e="program",i){e?this._focusMonitor.focusVia(this._elementRef.nativeElement,e,i):this._elementRef.nativeElement.focus(i)}_getAriaDisabled(){return this.ariaDisabled!=null?this.ariaDisabled:this._isAnchor?this.disabled||null:this.disabled&&this.disabledInteractive?!0:null}_getDisabledAttribute(){return this.disabledInteractive||!this.disabled?null:!0}_updateRippleDisabled(){this._rippleLoader?.setDisabled(this._elementRef.nativeElement,this.disableRipple||this.disabled)}_getTabIndex(){return this._isAnchor?this.disabled&&!this.disabledInteractive?-1:this.tabIndex:this.tabIndex}_setupAsAnchor(){this._cleanupClick=this._ngZone.runOutsideAngular(()=>this._renderer.listen(this._elementRef.nativeElement,"click",e=>{this.disabled&&(e.preventDefault(),e.stopImmediatePropagation())}))}static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,hostAttrs:[1,"mat-mdc-button-base"],hostVars:15,hostBindings:function(i,a){i&2&&(fe("disabled",a._getDisabledAttribute())("aria-disabled",a._getAriaDisabled())("tabindex",a._getTabIndex()),At(a.color?"mat-"+a.color:""),J("mat-mdc-button-progress-indicator-shown",a.showProgress())("mat-mdc-button-disabled",a.disabled)("mat-mdc-button-disabled-interactive",a.disabledInteractive)("mat-unthemed",!a.color)("_mat-animation-noopable",a._animationsDisabled))},inputs:{color:"color",disableRipple:[2,"disableRipple","disableRipple",Ae],disabled:[2,"disabled","disabled",Ae],ariaDisabled:[2,"aria-disabled","ariaDisabled",Ae],disabledInteractive:[2,"disabledInteractive","disabledInteractive",Ae],tabIndex:[2,"tabIndex","tabIndex",Ng],_tabindex:[2,"tabindex","_tabindex",Ng],showProgress:[1,"showProgress"]}})}return n})();var In=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[me]})}return n})();var Ag=new Map([["text",["mat-mdc-button"]],["filled",["mdc-button--unelevated","mat-mdc-unelevated-button"]],["elevated",["mdc-button--raised","mat-mdc-raised-button"]],["outlined",["mdc-button--outlined","mat-mdc-outlined-button"]],["tonal",["mat-tonal-button"]]]),Fa=(()=>{class n extends Ig{get appearance(){return this._appearance}set appearance(e){this.setAppearance(e||this._config?.defaultAppearance||"text")}_appearance=null;constructor(){super();let e=Vw(this._elementRef.nativeElement);e&&this.setAppearance(e)}setAppearance(e){if(e===this._appearance)return;let i=this._elementRef.nativeElement.classList,a=this._appearance?Ag.get(this._appearance):null,r=Ag.get(e);a&&i.remove(...a),i.add(...r),this._appearance=e}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=[[["",8,"material-icons",3,"iconPositionEnd",""],["mat-icon",3,"iconPositionEnd",""],["","matButtonIcon","",3,"iconPositionEnd",""],["",8,"material-symbols-outlined",3,"iconPositionEnd",""],["",8,"material-symbols-rounded",3,"iconPositionEnd",""],["",8,"material-symbols-sharp",3,"iconPositionEnd",""]],"*",[["","iconPositionEnd","",8,"material-icons"],["mat-icon","iconPositionEnd",""],["","matButtonIcon","","iconPositionEnd",""],["","iconPositionEnd","",8,"material-symbols-outlined"],["","iconPositionEnd","",8,"material-symbols-rounded"],["","iconPositionEnd","",8,"material-symbols-sharp"]],[["","progressIndicator",""]]],i=[".material-icons:not([iconPositionEnd]), mat-icon:not([iconPositionEnd]), [matButtonIcon]:not([iconPositionEnd]), .material-symbols-outlined:not([iconPositionEnd]), .material-symbols-rounded:not([iconPositionEnd]), .material-symbols-sharp:not([iconPositionEnd])","*",".material-icons[iconPositionEnd], mat-icon[iconPositionEnd], [matButtonIcon][iconPositionEnd], .material-symbols-outlined[iconPositionEnd], .material-symbols-rounded[iconPositionEnd], .material-symbols-sharp[iconPositionEnd]","[progressIndicator]"];function a(r,o){r&1&&(Ze(0,"div",2),j(1,3),Je())}return T({type:n,selectors:[["button","matButton",""],["a","matButton",""],["button","mat-button",""],["button","mat-raised-button",""],["button","mat-flat-button",""],["button","mat-stroked-button",""],["a","mat-button",""],["a","mat-raised-button",""],["a","mat-flat-button",""],["a","mat-stroked-button",""]],hostAttrs:[1,"mdc-button"],inputs:{appearance:[0,"matButton","appearance"]},exportAs:["matButton","matAnchor"],features:[Ve],ngContentSelectors:i,decls:8,vars:5,consts:[[1,"mat-mdc-button-persistent-ripple"],[1,"mdc-button__label"],[1,"mat-mdc-button-progress-indicator-container"],[1,"mat-focus-indicator"],[1,"mat-mdc-button-touch-target"]],template:function(o,s){o&1&&(de(e),ln(0,"span",0),j(1),Ze(2,"span",1),j(3,1),Je(),j(4,2),E(5,a,2,0,"div",2),ln(6,"span",3)(7,"span",4)),o&2&&(J("mdc-button__ripple",!s._isFab)("mdc-fab__ripple",s._isFab),h(5),D(s.showProgress()?5:-1))},styles:[`.mat-mdc-button-base {
  text-decoration: none;
}
.mat-mdc-button-base .mat-icon {
  min-height: fit-content;
  flex-shrink: 0;
}
@media (hover: none) {
  .mat-mdc-button-base:hover > span.mat-mdc-button-persistent-ripple::before {
    opacity: 0;
  }
}

.mdc-button {
  -webkit-user-select: none;
  user-select: none;
  position: relative;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  box-sizing: border-box;
  min-width: 64px;
  border: none;
  outline: none;
  line-height: inherit;
  -webkit-appearance: none;
  overflow: visible;
  vertical-align: middle;
  background: transparent;
  padding: 0 8px;
}
.mdc-button::-moz-focus-inner {
  padding: 0;
  border: 0;
}
.mdc-button:active {
  outline: none;
}
.mdc-button:hover {
  cursor: pointer;
}
.mdc-button:disabled {
  cursor: default;
  pointer-events: none;
}
.mdc-button[hidden] {
  display: none;
}
.mdc-button .mdc-button__label {
  position: relative;
}

.mat-mdc-button {
  padding: 0 var(--%NS%mat-button-text-horizontal-padding, 12px);
  height: var(--%NS%mat-button-text-container-height, 40px);
  font-family: var(--%NS%mat-button-text-label-text-font, var(--%NS%mat-sys-label-large-font));
  font-size: var(--%NS%mat-button-text-label-text-size, var(--%NS%mat-sys-label-large-size));
  letter-spacing: var(--%NS%mat-button-text-label-text-tracking, var(--%NS%mat-sys-label-large-tracking));
  text-transform: var(--%NS%mat-button-text-label-text-transform);
  font-weight: var(--%NS%mat-button-text-label-text-weight, var(--%NS%mat-sys-label-large-weight));
}
.mat-mdc-button, .mat-mdc-button .mdc-button__ripple {
  border-radius: var(--%NS%mat-button-text-container-shape, var(--%NS%mat-sys-corner-full));
}
.mat-mdc-button:not(:disabled) {
  color: var(--%NS%mat-button-text-label-text-color, var(--%NS%mat-sys-primary));
}
.mat-mdc-button[disabled], .mat-mdc-button.mat-mdc-button-disabled {
  cursor: default;
  pointer-events: none;
  color: var(--%NS%mat-button-text-disabled-label-text-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mat-mdc-button.mat-mdc-button-disabled-interactive {
  pointer-events: auto;
}
.mat-mdc-button:has(.material-icons, .material-symbols-outlined, .material-symbols-rounded,
.material-symbols-sharp, mat-icon, [matButtonIcon]) {
  padding: 0 var(--%NS%mat-button-text-with-icon-horizontal-padding, 16px);
}
.mat-mdc-button > .mat-icon {
  margin-right: var(--%NS%mat-button-text-icon-spacing, 8px);
  margin-left: var(--%NS%mat-button-text-icon-offset, -4px);
}
[dir=rtl] .mat-mdc-button > .mat-icon {
  margin-right: var(--%NS%mat-button-text-icon-offset, -4px);
  margin-left: var(--%NS%mat-button-text-icon-spacing, 8px);
}
.mat-mdc-button .mdc-button__label + .mat-icon {
  margin-right: var(--%NS%mat-button-text-icon-offset, -4px);
  margin-left: var(--%NS%mat-button-text-icon-spacing, 8px);
}
[dir=rtl] .mat-mdc-button .mdc-button__label + .mat-icon {
  margin-right: var(--%NS%mat-button-text-icon-spacing, 8px);
  margin-left: var(--%NS%mat-button-text-icon-offset, -4px);
}
.mat-mdc-button .mat-ripple-element {
  background-color: var(--%NS%mat-button-text-ripple-color, color-mix(in srgb, var(--%NS%mat-sys-primary) calc(var(--%NS%mat-sys-pressed-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-button .mat-mdc-button-persistent-ripple::before {
  background-color: var(--%NS%mat-button-text-state-layer-color, var(--%NS%mat-sys-primary));
}
.mat-mdc-button.mat-mdc-button-disabled .mat-mdc-button-persistent-ripple::before {
  background-color: var(--%NS%mat-button-text-disabled-state-layer-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-mdc-button:hover > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-text-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity));
}
.mat-mdc-button.cdk-program-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-button.cdk-keyboard-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-button.mat-mdc-button-disabled-interactive:focus > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-text-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity));
}
.mat-mdc-button:active > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-text-pressed-state-layer-opacity, var(--%NS%mat-sys-pressed-state-layer-opacity));
}
.mat-mdc-button .mat-mdc-button-touch-target {
  position: absolute;
  top: 50%;
  height: var(--%NS%mat-button-text-touch-target-size, 48px);
  display: var(--%NS%mat-button-text-touch-target-display, block);
  left: 0;
  right: 0;
  transform: translateY(-50%);
}

.mat-mdc-unelevated-button {
  transition: box-shadow 280ms cubic-bezier(0.4, 0, 0.2, 1);
  height: var(--%NS%mat-button-filled-container-height, 40px);
  font-family: var(--%NS%mat-button-filled-label-text-font, var(--%NS%mat-sys-label-large-font));
  font-size: var(--%NS%mat-button-filled-label-text-size, var(--%NS%mat-sys-label-large-size));
  letter-spacing: var(--%NS%mat-button-filled-label-text-tracking, var(--%NS%mat-sys-label-large-tracking));
  text-transform: var(--%NS%mat-button-filled-label-text-transform);
  font-weight: var(--%NS%mat-button-filled-label-text-weight, var(--%NS%mat-sys-label-large-weight));
  padding: 0 var(--%NS%mat-button-filled-horizontal-padding, 24px);
}
.mat-mdc-unelevated-button > .mat-icon {
  margin-right: var(--%NS%mat-button-filled-icon-spacing, 8px);
  margin-left: var(--%NS%mat-button-filled-icon-offset, -8px);
}
[dir=rtl] .mat-mdc-unelevated-button > .mat-icon {
  margin-right: var(--%NS%mat-button-filled-icon-offset, -8px);
  margin-left: var(--%NS%mat-button-filled-icon-spacing, 8px);
}
.mat-mdc-unelevated-button .mdc-button__label + .mat-icon {
  margin-right: var(--%NS%mat-button-filled-icon-offset, -8px);
  margin-left: var(--%NS%mat-button-filled-icon-spacing, 8px);
}
[dir=rtl] .mat-mdc-unelevated-button .mdc-button__label + .mat-icon {
  margin-right: var(--%NS%mat-button-filled-icon-spacing, 8px);
  margin-left: var(--%NS%mat-button-filled-icon-offset, -8px);
}
.mat-mdc-unelevated-button .mat-ripple-element {
  background-color: var(--%NS%mat-button-filled-ripple-color, color-mix(in srgb, var(--%NS%mat-sys-on-primary) calc(var(--%NS%mat-sys-pressed-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-unelevated-button .mat-mdc-button-persistent-ripple::before {
  background-color: var(--%NS%mat-button-filled-state-layer-color, var(--%NS%mat-sys-on-primary));
}
.mat-mdc-unelevated-button.mat-mdc-button-disabled .mat-mdc-button-persistent-ripple::before {
  background-color: var(--%NS%mat-button-filled-disabled-state-layer-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-mdc-unelevated-button:hover > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-filled-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity));
}
.mat-mdc-unelevated-button.cdk-program-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-unelevated-button.cdk-keyboard-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-unelevated-button.mat-mdc-button-disabled-interactive:focus > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-filled-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity));
}
.mat-mdc-unelevated-button:active > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-filled-pressed-state-layer-opacity, var(--%NS%mat-sys-pressed-state-layer-opacity));
}
.mat-mdc-unelevated-button .mat-mdc-button-touch-target {
  position: absolute;
  top: 50%;
  height: var(--%NS%mat-button-filled-touch-target-size, 48px);
  display: var(--%NS%mat-button-filled-touch-target-display, block);
  left: 0;
  right: 0;
  transform: translateY(-50%);
}
.mat-mdc-unelevated-button:not(:disabled) {
  color: var(--%NS%mat-button-filled-label-text-color, var(--%NS%mat-sys-on-primary));
  background-color: var(--%NS%mat-button-filled-container-color, var(--%NS%mat-sys-primary));
}
.mat-mdc-unelevated-button, .mat-mdc-unelevated-button .mdc-button__ripple {
  border-radius: var(--%NS%mat-button-filled-container-shape, var(--%NS%mat-sys-corner-full));
}
.mat-mdc-unelevated-button .mat-mdc-button-progress-indicator-container {
  --%NS%mat-progress-spinner-active-indicator-color: var(--%NS%mat-button-filled-progress-active-indicator-color, var(--%NS%mat-sys-on-primary));
}
.mat-mdc-unelevated-button[disabled], .mat-mdc-unelevated-button.mat-mdc-button-disabled {
  cursor: default;
  pointer-events: none;
  color: var(--%NS%mat-button-filled-disabled-label-text-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
  background-color: var(--%NS%mat-button-filled-disabled-container-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 12%, transparent));
}
.mat-mdc-unelevated-button.mat-mdc-button-disabled-interactive {
  pointer-events: auto;
}

.mat-mdc-raised-button {
  transition: box-shadow 280ms cubic-bezier(0.4, 0, 0.2, 1);
  box-shadow: var(--%NS%mat-button-protected-container-elevation-shadow, var(--%NS%mat-sys-level1));
  height: var(--%NS%mat-button-protected-container-height, 40px);
  font-family: var(--%NS%mat-button-protected-label-text-font, var(--%NS%mat-sys-label-large-font));
  font-size: var(--%NS%mat-button-protected-label-text-size, var(--%NS%mat-sys-label-large-size));
  letter-spacing: var(--%NS%mat-button-protected-label-text-tracking, var(--%NS%mat-sys-label-large-tracking));
  text-transform: var(--%NS%mat-button-protected-label-text-transform);
  font-weight: var(--%NS%mat-button-protected-label-text-weight, var(--%NS%mat-sys-label-large-weight));
  padding: 0 var(--%NS%mat-button-protected-horizontal-padding, 24px);
}
.mat-mdc-raised-button > .mat-icon {
  margin-right: var(--%NS%mat-button-protected-icon-spacing, 8px);
  margin-left: var(--%NS%mat-button-protected-icon-offset, -8px);
}
[dir=rtl] .mat-mdc-raised-button > .mat-icon {
  margin-right: var(--%NS%mat-button-protected-icon-offset, -8px);
  margin-left: var(--%NS%mat-button-protected-icon-spacing, 8px);
}
.mat-mdc-raised-button .mdc-button__label + .mat-icon {
  margin-right: var(--%NS%mat-button-protected-icon-offset, -8px);
  margin-left: var(--%NS%mat-button-protected-icon-spacing, 8px);
}
[dir=rtl] .mat-mdc-raised-button .mdc-button__label + .mat-icon {
  margin-right: var(--%NS%mat-button-protected-icon-spacing, 8px);
  margin-left: var(--%NS%mat-button-protected-icon-offset, -8px);
}
.mat-mdc-raised-button .mat-ripple-element {
  background-color: var(--%NS%mat-button-protected-ripple-color, color-mix(in srgb, var(--%NS%mat-sys-primary) calc(var(--%NS%mat-sys-pressed-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-raised-button .mat-mdc-button-persistent-ripple::before {
  background-color: var(--%NS%mat-button-protected-state-layer-color, var(--%NS%mat-sys-primary));
}
.mat-mdc-raised-button.mat-mdc-button-disabled .mat-mdc-button-persistent-ripple::before {
  background-color: var(--%NS%mat-button-protected-disabled-state-layer-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-mdc-raised-button:hover > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-protected-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity));
}
.mat-mdc-raised-button.cdk-program-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-raised-button.cdk-keyboard-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-raised-button.mat-mdc-button-disabled-interactive:focus > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-protected-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity));
}
.mat-mdc-raised-button:active > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-protected-pressed-state-layer-opacity, var(--%NS%mat-sys-pressed-state-layer-opacity));
}
.mat-mdc-raised-button .mat-mdc-button-touch-target {
  position: absolute;
  top: 50%;
  height: var(--%NS%mat-button-protected-touch-target-size, 48px);
  display: var(--%NS%mat-button-protected-touch-target-display, block);
  left: 0;
  right: 0;
  transform: translateY(-50%);
}
.mat-mdc-raised-button:not(:disabled) {
  color: var(--%NS%mat-button-protected-label-text-color, var(--%NS%mat-sys-primary));
  background-color: var(--%NS%mat-button-protected-container-color, var(--%NS%mat-sys-surface));
}
.mat-mdc-raised-button, .mat-mdc-raised-button .mdc-button__ripple {
  border-radius: var(--%NS%mat-button-protected-container-shape, var(--%NS%mat-sys-corner-full));
}
@media (hover: hover) {
  .mat-mdc-raised-button:hover {
    box-shadow: var(--%NS%mat-button-protected-hover-container-elevation-shadow, var(--%NS%mat-sys-level2));
  }
}
.mat-mdc-raised-button:focus {
  box-shadow: var(--%NS%mat-button-protected-focus-container-elevation-shadow, var(--%NS%mat-sys-level1));
}
.mat-mdc-raised-button:active, .mat-mdc-raised-button:focus:active {
  box-shadow: var(--%NS%mat-button-protected-pressed-container-elevation-shadow, var(--%NS%mat-sys-level1));
}
.mat-mdc-raised-button[disabled], .mat-mdc-raised-button.mat-mdc-button-disabled {
  cursor: default;
  pointer-events: none;
  color: var(--%NS%mat-button-protected-disabled-label-text-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
  background-color: var(--%NS%mat-button-protected-disabled-container-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 12%, transparent));
}
.mat-mdc-raised-button[disabled].mat-mdc-button-disabled, .mat-mdc-raised-button.mat-mdc-button-disabled.mat-mdc-button-disabled {
  box-shadow: var(--%NS%mat-button-protected-disabled-container-elevation-shadow, var(--%NS%mat-sys-level0));
}
.mat-mdc-raised-button.mat-mdc-button-disabled-interactive {
  pointer-events: auto;
}

.mat-mdc-outlined-button {
  border-style: solid;
  transition: border 280ms cubic-bezier(0.4, 0, 0.2, 1);
  height: var(--%NS%mat-button-outlined-container-height, 40px);
  font-family: var(--%NS%mat-button-outlined-label-text-font, var(--%NS%mat-sys-label-large-font));
  font-size: var(--%NS%mat-button-outlined-label-text-size, var(--%NS%mat-sys-label-large-size));
  letter-spacing: var(--%NS%mat-button-outlined-label-text-tracking, var(--%NS%mat-sys-label-large-tracking));
  text-transform: var(--%NS%mat-button-outlined-label-text-transform);
  font-weight: var(--%NS%mat-button-outlined-label-text-weight, var(--%NS%mat-sys-label-large-weight));
  border-radius: var(--%NS%mat-button-outlined-container-shape, var(--%NS%mat-sys-corner-full));
  border-width: var(--%NS%mat-button-outlined-outline-width, 1px);
  padding: 0 var(--%NS%mat-button-outlined-horizontal-padding, 24px);
}
.mat-mdc-outlined-button > .mat-icon {
  margin-right: var(--%NS%mat-button-outlined-icon-spacing, 8px);
  margin-left: var(--%NS%mat-button-outlined-icon-offset, -8px);
}
[dir=rtl] .mat-mdc-outlined-button > .mat-icon {
  margin-right: var(--%NS%mat-button-outlined-icon-offset, -8px);
  margin-left: var(--%NS%mat-button-outlined-icon-spacing, 8px);
}
.mat-mdc-outlined-button .mdc-button__label + .mat-icon {
  margin-right: var(--%NS%mat-button-outlined-icon-offset, -8px);
  margin-left: var(--%NS%mat-button-outlined-icon-spacing, 8px);
}
[dir=rtl] .mat-mdc-outlined-button .mdc-button__label + .mat-icon {
  margin-right: var(--%NS%mat-button-outlined-icon-spacing, 8px);
  margin-left: var(--%NS%mat-button-outlined-icon-offset, -8px);
}
.mat-mdc-outlined-button .mat-ripple-element {
  background-color: var(--%NS%mat-button-outlined-ripple-color, color-mix(in srgb, var(--%NS%mat-sys-primary) calc(var(--%NS%mat-sys-pressed-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-outlined-button .mat-mdc-button-persistent-ripple::before {
  background-color: var(--%NS%mat-button-outlined-state-layer-color, var(--%NS%mat-sys-primary));
}
.mat-mdc-outlined-button.mat-mdc-button-disabled .mat-mdc-button-persistent-ripple::before {
  background-color: var(--%NS%mat-button-outlined-disabled-state-layer-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-mdc-outlined-button:hover > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-outlined-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity));
}
.mat-mdc-outlined-button.cdk-program-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-outlined-button.cdk-keyboard-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-outlined-button.mat-mdc-button-disabled-interactive:focus > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-outlined-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity));
}
.mat-mdc-outlined-button:active > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-outlined-pressed-state-layer-opacity, var(--%NS%mat-sys-pressed-state-layer-opacity));
}
.mat-mdc-outlined-button .mat-mdc-button-touch-target {
  position: absolute;
  top: 50%;
  height: var(--%NS%mat-button-outlined-touch-target-size, 48px);
  display: var(--%NS%mat-button-outlined-touch-target-display, block);
  left: 0;
  right: 0;
  transform: translateY(-50%);
}
.mat-mdc-outlined-button:not(:disabled) {
  color: var(--%NS%mat-button-outlined-label-text-color, var(--%NS%mat-sys-primary));
  border-color: var(--%NS%mat-button-outlined-outline-color, var(--%NS%mat-sys-outline));
}
.mat-mdc-outlined-button[disabled], .mat-mdc-outlined-button.mat-mdc-button-disabled {
  cursor: default;
  pointer-events: none;
  color: var(--%NS%mat-button-outlined-disabled-label-text-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
  border-color: var(--%NS%mat-button-outlined-disabled-outline-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 12%, transparent));
}
.mat-mdc-outlined-button.mat-mdc-button-disabled-interactive {
  pointer-events: auto;
}

.mat-tonal-button {
  transition: box-shadow 280ms cubic-bezier(0.4, 0, 0.2, 1);
  height: var(--%NS%mat-button-tonal-container-height, 40px);
  font-family: var(--%NS%mat-button-tonal-label-text-font, var(--%NS%mat-sys-label-large-font));
  font-size: var(--%NS%mat-button-tonal-label-text-size, var(--%NS%mat-sys-label-large-size));
  letter-spacing: var(--%NS%mat-button-tonal-label-text-tracking, var(--%NS%mat-sys-label-large-tracking));
  text-transform: var(--%NS%mat-button-tonal-label-text-transform);
  font-weight: var(--%NS%mat-button-tonal-label-text-weight, var(--%NS%mat-sys-label-large-weight));
  padding: 0 var(--%NS%mat-button-tonal-horizontal-padding, 24px);
}
.mat-tonal-button:not(:disabled) {
  color: var(--%NS%mat-button-tonal-label-text-color, var(--%NS%mat-sys-on-secondary-container));
  background-color: var(--%NS%mat-button-tonal-container-color, var(--%NS%mat-sys-secondary-container));
}
.mat-tonal-button, .mat-tonal-button .mdc-button__ripple {
  border-radius: var(--%NS%mat-button-tonal-container-shape, var(--%NS%mat-sys-corner-full));
}
.mat-tonal-button[disabled], .mat-tonal-button.mat-mdc-button-disabled {
  cursor: default;
  pointer-events: none;
  color: var(--%NS%mat-button-tonal-disabled-label-text-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
  background-color: var(--%NS%mat-button-tonal-disabled-container-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 12%, transparent));
}
.mat-tonal-button.mat-mdc-button-disabled-interactive {
  pointer-events: auto;
}
.mat-tonal-button > .mat-icon {
  margin-right: var(--%NS%mat-button-tonal-icon-spacing, 8px);
  margin-left: var(--%NS%mat-button-tonal-icon-offset, -8px);
}
[dir=rtl] .mat-tonal-button > .mat-icon {
  margin-right: var(--%NS%mat-button-tonal-icon-offset, -8px);
  margin-left: var(--%NS%mat-button-tonal-icon-spacing, 8px);
}
.mat-tonal-button .mdc-button__label + .mat-icon {
  margin-right: var(--%NS%mat-button-tonal-icon-offset, -8px);
  margin-left: var(--%NS%mat-button-tonal-icon-spacing, 8px);
}
[dir=rtl] .mat-tonal-button .mdc-button__label + .mat-icon {
  margin-right: var(--%NS%mat-button-tonal-icon-spacing, 8px);
  margin-left: var(--%NS%mat-button-tonal-icon-offset, -8px);
}
.mat-tonal-button .mat-ripple-element {
  background-color: var(--%NS%mat-button-tonal-ripple-color, color-mix(in srgb, var(--%NS%mat-sys-on-secondary-container) calc(var(--%NS%mat-sys-pressed-state-layer-opacity) * 100%), transparent));
}
.mat-tonal-button .mat-mdc-button-persistent-ripple::before {
  background-color: var(--%NS%mat-button-tonal-state-layer-color, var(--%NS%mat-sys-on-secondary-container));
}
.mat-tonal-button.mat-mdc-button-disabled .mat-mdc-button-persistent-ripple::before {
  background-color: var(--%NS%mat-button-tonal-disabled-state-layer-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-tonal-button:hover > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-tonal-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity));
}
.mat-tonal-button.cdk-program-focused > .mat-mdc-button-persistent-ripple::before, .mat-tonal-button.cdk-keyboard-focused > .mat-mdc-button-persistent-ripple::before, .mat-tonal-button.mat-mdc-button-disabled-interactive:focus > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-tonal-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity));
}
.mat-tonal-button:active > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--%NS%mat-button-tonal-pressed-state-layer-opacity, var(--%NS%mat-sys-pressed-state-layer-opacity));
}
.mat-tonal-button .mat-mdc-button-touch-target {
  position: absolute;
  top: 50%;
  height: var(--%NS%mat-button-tonal-touch-target-size, 48px);
  display: var(--%NS%mat-button-tonal-touch-target-display, block);
  left: 0;
  right: 0;
  transform: translateY(-50%);
}

.mat-mdc-button,
.mat-mdc-unelevated-button,
.mat-mdc-raised-button,
.mat-mdc-outlined-button,
.mat-tonal-button {
  -webkit-tap-highlight-color: transparent;
}
.mat-mdc-button .mat-mdc-button-ripple,
.mat-mdc-button .mat-mdc-button-persistent-ripple,
.mat-mdc-button .mat-mdc-button-persistent-ripple::before,
.mat-mdc-unelevated-button .mat-mdc-button-ripple,
.mat-mdc-unelevated-button .mat-mdc-button-persistent-ripple,
.mat-mdc-unelevated-button .mat-mdc-button-persistent-ripple::before,
.mat-mdc-raised-button .mat-mdc-button-ripple,
.mat-mdc-raised-button .mat-mdc-button-persistent-ripple,
.mat-mdc-raised-button .mat-mdc-button-persistent-ripple::before,
.mat-mdc-outlined-button .mat-mdc-button-ripple,
.mat-mdc-outlined-button .mat-mdc-button-persistent-ripple,
.mat-mdc-outlined-button .mat-mdc-button-persistent-ripple::before,
.mat-tonal-button .mat-mdc-button-ripple,
.mat-tonal-button .mat-mdc-button-persistent-ripple,
.mat-tonal-button .mat-mdc-button-persistent-ripple::before {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  pointer-events: none;
  border-radius: inherit;
}
.mat-mdc-button .mat-mdc-button-ripple,
.mat-mdc-unelevated-button .mat-mdc-button-ripple,
.mat-mdc-raised-button .mat-mdc-button-ripple,
.mat-mdc-outlined-button .mat-mdc-button-ripple,
.mat-tonal-button .mat-mdc-button-ripple {
  overflow: hidden;
}
.mat-mdc-button .mat-mdc-button-persistent-ripple::before,
.mat-mdc-unelevated-button .mat-mdc-button-persistent-ripple::before,
.mat-mdc-raised-button .mat-mdc-button-persistent-ripple::before,
.mat-mdc-outlined-button .mat-mdc-button-persistent-ripple::before,
.mat-tonal-button .mat-mdc-button-persistent-ripple::before {
  content: "";
  opacity: 0;
}
.mat-mdc-button .mdc-button__label,
.mat-mdc-button .mat-icon,
.mat-mdc-unelevated-button .mdc-button__label,
.mat-mdc-unelevated-button .mat-icon,
.mat-mdc-raised-button .mdc-button__label,
.mat-mdc-raised-button .mat-icon,
.mat-mdc-outlined-button .mdc-button__label,
.mat-mdc-outlined-button .mat-icon,
.mat-tonal-button .mdc-button__label,
.mat-tonal-button .mat-icon {
  z-index: 1;
  position: relative;
}
.mat-mdc-button .mat-focus-indicator,
.mat-mdc-unelevated-button .mat-focus-indicator,
.mat-mdc-raised-button .mat-focus-indicator,
.mat-mdc-outlined-button .mat-focus-indicator,
.mat-tonal-button .mat-focus-indicator {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  border-radius: inherit;
}
.mat-mdc-button:focus-visible > .mat-focus-indicator::before,
.mat-mdc-unelevated-button:focus-visible > .mat-focus-indicator::before,
.mat-mdc-raised-button:focus-visible > .mat-focus-indicator::before,
.mat-mdc-outlined-button:focus-visible > .mat-focus-indicator::before,
.mat-tonal-button:focus-visible > .mat-focus-indicator::before {
  content: "";
  border-radius: inherit;
}
.mat-mdc-button._mat-animation-noopable,
.mat-mdc-unelevated-button._mat-animation-noopable,
.mat-mdc-raised-button._mat-animation-noopable,
.mat-mdc-outlined-button._mat-animation-noopable,
.mat-tonal-button._mat-animation-noopable {
  transition: none !important;
  animation: none !important;
}
.mat-mdc-button > .mat-icon,
.mat-mdc-unelevated-button > .mat-icon,
.mat-mdc-raised-button > .mat-icon,
.mat-mdc-outlined-button > .mat-icon,
.mat-tonal-button > .mat-icon {
  display: inline-block;
  position: relative;
  vertical-align: top;
  font-size: 1.125rem;
  height: 1.125rem;
  width: 1.125rem;
}

.mat-mdc-outlined-button .mat-mdc-button-ripple,
.mat-mdc-outlined-button .mdc-button__ripple {
  top: -1px;
  left: -1px;
  bottom: -1px;
  right: -1px;
}

.mat-mdc-unelevated-button .mat-focus-indicator::before,
.mat-tonal-button .mat-focus-indicator::before,
.mat-mdc-raised-button .mat-focus-indicator::before {
  margin: calc(calc(var(--%NS%mat-focus-indicator-border-width, 3px) + 2px) * -1);
}

.mat-mdc-outlined-button .mat-focus-indicator::before {
  margin: calc(calc(var(--%NS%mat-focus-indicator-border-width, 3px) + 3px) * -1);
}

.mat-mdc-button-progress-indicator-container {
  position: absolute;
  inset-inline-start: 0;
  inset-block-start: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
}

.mat-mdc-button-progress-indicator-shown mat-icon,
.mat-mdc-button-progress-indicator-shown [matButtonIcon],
.mat-mdc-button-progress-indicator-shown .mdc-button__label {
  visibility: hidden;
}
`,`@media (forced-colors: active) {
  .mat-mdc-button:not(.mdc-button--outlined),
  .mat-mdc-unelevated-button:not(.mdc-button--outlined),
  .mat-mdc-raised-button:not(.mdc-button--outlined),
  .mat-mdc-outlined-button:not(.mdc-button--outlined),
  .mat-mdc-button-base.mat-tonal-button,
  .mat-mdc-icon-button.mat-mdc-icon-button,
  .mat-mdc-outlined-button .mdc-button__ripple {
    outline: solid 1px;
  }
}
`],encapsulation:2})})()}return n})();function Vw(n){return n.hasAttribute("mat-raised-button")?"elevated":n.hasAttribute("mat-stroked-button")?"outlined":n.hasAttribute("mat-flat-button")?"filled":n.hasAttribute("mat-button")?"text":null}var Gn=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[In,me]})}return n})();var $w=Math.pow(2,31)-1,so=class{_overlayRef;instance;containerInstance;_afterDismissed=new z;_afterOpened=new z;_onAction=new z;_durationTimeoutId;_dismissedByAction=!1;constructor(t,e){this._overlayRef=e,this.containerInstance=t,t._onExit.subscribe(()=>this._finishDismiss())}dismiss(){this._afterDismissed.closed||this.containerInstance.exit(),clearTimeout(this._durationTimeoutId)}dismissWithAction(){this._onAction.closed||(this._dismissedByAction=!0,this._onAction.next(),this._onAction.complete(),this.dismiss()),clearTimeout(this._durationTimeoutId)}closeWithAction(){this.dismissWithAction()}_dismissAfter(t){this._durationTimeoutId=setTimeout(()=>this.dismiss(),Math.min(t,$w))}_open(){this._afterOpened.closed||(this._afterOpened.next(),this._afterOpened.complete())}_finishDismiss(){this._overlayRef.dispose(),this._onAction.closed||this._onAction.complete(),this._afterDismissed.next({dismissedByAction:this._dismissedByAction}),this._afterDismissed.complete(),this._dismissedByAction=!1}afterDismissed(){return this._afterDismissed}afterOpened(){return this.containerInstance._onEnter}onAction(){return this._onAction}},Og=new I("MatSnackBarData"),La=class{politeness="polite";announcementMessage="";viewContainerRef;duration=0;panelClass;direction;data=null;horizontalPosition="center";verticalPosition="bottom"},zw=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","matSnackBarLabel",""]],hostAttrs:[1,"mat-mdc-snack-bar-label","mdc-snackbar__label"]})}return n})(),Hw=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","matSnackBarActions",""]],hostAttrs:[1,"mat-mdc-snack-bar-actions","mdc-snackbar__actions"]})}return n})(),Uw=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","matSnackBarAction",""]],hostAttrs:[1,"mat-mdc-snack-bar-action","mdc-snackbar__action"]})}return n})(),Rg=(()=>{class n{snackBarRef=p(so);data=p(Og);action(){this.snackBarRef.dismissWithAction()}get hasAction(){return!!this.data.action}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){function e(i,a){if(i&1){let r=ut();y(0,"div",1)(1,"button",2),ye("click",function(){at(r);let s=M();return rt(s.action())}),S(2),v()()}if(i&2){let r=M();h(2),ae(" ",r.data.action," ")}}return T({type:n,selectors:[["simple-snack-bar"]],hostAttrs:[1,"mat-mdc-simple-snack-bar"],exportAs:["matSnackBar"],decls:3,vars:2,consts:[["matSnackBarLabel",""],["matSnackBarActions",""],["matButton","","matSnackBarAction","",3,"click"]],template:function(a,r){a&1&&(y(0,"div",0),S(1),v(),E(2,e,3,1,"div",1)),a&2&&(h(),ae(" ",r.data.message,`
`),h(),D(r.hasAction?2:-1))},dependencies:[Fa,zw,Hw,Uw],styles:[`.mat-mdc-simple-snack-bar {
  display: flex;
}
.mat-mdc-simple-snack-bar .mat-mdc-snack-bar-label {
  max-height: 50vh;
  overflow: auto;
}
`],encapsulation:2})})()}return n})(),Jd="_mat-snack-bar-enter",em="_mat-snack-bar-exit",qw=(()=>{class n extends Oa{_ngZone=p(W);_elementRef=p(X);_changeDetectorRef=p(et);_platform=p(Ne);_animationsDisabled=tt();snackBarConfig=p(La);_document=p(Y);_trackedModals=new Set;_enterFallback;_exitFallback;_injector=p(ce);_announceDelay=150;_announceTimeoutId;_destroyed=!1;_portalOutlet;_onAnnounce=new z;_onExit=new z;_onEnter=new z;_animationState="void";_live;_label;_role;_liveElementId=p(St).getId("mat-snack-bar-container-live-");constructor(){super();let e=this.snackBarConfig;e.politeness==="assertive"&&!e.announcementMessage?this._live="assertive":e.politeness==="off"?this._live="off":this._live="polite",this._platform.FIREFOX&&(this._live==="polite"&&(this._role="status"),this._live==="assertive"&&(this._role="alert"))}attachComponentPortal(e){this._assertNotAttached();let i=this._portalOutlet.attachComponentPortal(e);return this._afterPortalAttached(),i}attachTemplatePortal(e){this._assertNotAttached();let i=this._portalOutlet.attachTemplatePortal(e);return this._afterPortalAttached(),i}attachDomPortal=e=>{this._assertNotAttached();let i=this._portalOutlet.attachDomPortal(e);return this._afterPortalAttached(),i};onAnimationEnd(e){e===em?this._completeExit():e===Jd&&(clearTimeout(this._enterFallback),this._ngZone.run(()=>{this._onEnter.next(),this._onEnter.complete()}))}enter(){this._destroyed||(this._animationState="visible",this._changeDetectorRef.markForCheck(),this._changeDetectorRef.detectChanges(),this._screenReaderAnnounce(),this._animationsDisabled?yt(()=>{this._ngZone.run(()=>queueMicrotask(()=>this.onAnimationEnd(Jd)))},{injector:this._injector}):(clearTimeout(this._enterFallback),this._enterFallback=setTimeout(()=>{this._elementRef.nativeElement.classList.add("mat-snack-bar-fallback-visible"),this.onAnimationEnd(Jd)},200)))}exit(){return this._destroyed?Ue(void 0):(this._ngZone.run(()=>{this._animationState="hidden",this._changeDetectorRef.markForCheck(),this._elementRef.nativeElement.setAttribute("mat-exit",""),clearTimeout(this._announceTimeoutId),this._animationsDisabled?yt(()=>{this._ngZone.run(()=>queueMicrotask(()=>this.onAnimationEnd(em)))},{injector:this._injector}):(clearTimeout(this._exitFallback),this._exitFallback=setTimeout(()=>this.onAnimationEnd(em),200))}),this._onExit)}ngOnDestroy(){this._destroyed=!0,this._clearFromModals(),this._completeExit()}_completeExit(){clearTimeout(this._exitFallback),queueMicrotask(()=>{this._onExit.next(),this._onExit.complete()})}_afterPortalAttached(){let e=this._elementRef.nativeElement,i=this.snackBarConfig.panelClass;i&&(Array.isArray(i)?i.forEach(o=>e.classList.add(o)):e.classList.add(i)),this._exposeToModals();let a=this._label.nativeElement,r="mdc-snackbar__label";a.classList.toggle(r,!a.querySelector(`.${r}`))}_exposeToModals(){let e=this._liveElementId,i=this._document.querySelectorAll('body > .cdk-overlay-container [aria-modal="true"]');for(let a=0;a<i.length;a++){let r=i[a],o=r.getAttribute("aria-owns");this._trackedModals.add(r),o?o.indexOf(e)===-1&&r.setAttribute("aria-owns",o+" "+e):r.setAttribute("aria-owns",e)}}_clearFromModals(){this._trackedModals.forEach(e=>{let i=e.getAttribute("aria-owns");if(i){let a=i.replace(this._liveElementId,"").trim();a.length>0?e.setAttribute("aria-owns",a):e.removeAttribute("aria-owns")}}),this._trackedModals.clear()}_assertNotAttached(){this._portalOutlet.hasAttached()}_screenReaderAnnounce(){this._announceTimeoutId||this._ngZone.runOutsideAngular(()=>{this._announceTimeoutId=setTimeout(()=>{if(this._destroyed)return;let e=this._elementRef.nativeElement,i=e.querySelector("[aria-hidden]"),a=e.querySelector("[aria-live]");if(i&&a){let r=null;this._platform.isBrowser&&document.activeElement instanceof HTMLElement&&i.contains(document.activeElement)&&(r=document.activeElement),i.removeAttribute("aria-hidden"),a.appendChild(i),r?.focus(),this._onAnnounce.next(),this._onAnnounce.complete()}},this._announceDelay)})}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=["label"];function i(a,r){}return T({type:n,selectors:[["mat-snack-bar-container"]],viewQuery:function(r,o){if(r&1&&qe(ml,7)(e,7),r&2){let s;H(s=U())&&(o._portalOutlet=s.first),H(s=U())&&(o._label=s.first)}},hostAttrs:[1,"mdc-snackbar","mat-mdc-snack-bar-container"],hostVars:6,hostBindings:function(r,o){r&1&&ye("animationend",function(l){return o.onAnimationEnd(l.animationName)})("animationcancel",function(l){return o.onAnimationEnd(l.animationName)}),r&2&&J("mat-snack-bar-container-enter",o._animationState==="visible")("mat-snack-bar-container-exit",o._animationState==="hidden")("mat-snack-bar-container-animations-enabled",!o._animationsDisabled)},features:[Ve],decls:6,vars:3,consts:[["label",""],[1,"mdc-snackbar__surface","mat-mdc-snackbar-surface"],[1,"mat-mdc-snack-bar-label"],["aria-hidden","true"],["cdkPortalOutlet",""]],template:function(r,o){r&1&&(y(0,"div",1)(1,"div",2,0)(3,"div",3),Wt(4,i,0,0,"ng-template",4),v(),P(5,"div"),v()()),r&2&&(h(5),fe("aria-live",o._live)("role",o._role)("id",o._liveElementId))},dependencies:[ml],styles:[`@keyframes _mat-snack-bar-enter {
  from {
    transform: scale(0.8);
    opacity: 0;
  }
  to {
    transform: scale(1);
    opacity: 1;
  }
}
@keyframes _mat-snack-bar-exit {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
.mat-mdc-snack-bar-container {
  display: flex;
  align-items: center;
  justify-content: center;
  box-sizing: border-box;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  margin: 8px;
}
.mat-mdc-snack-bar-handset .mat-mdc-snack-bar-container {
  width: 100vw;
}

.mat-snack-bar-container-animations-enabled {
  opacity: 0;
}
.mat-snack-bar-container-animations-enabled.mat-snack-bar-fallback-visible {
  opacity: 1;
}
.mat-snack-bar-container-animations-enabled.mat-snack-bar-container-enter {
  animation: _mat-snack-bar-enter 150ms cubic-bezier(0, 0, 0.2, 1) forwards;
}
.mat-snack-bar-container-animations-enabled.mat-snack-bar-container-exit {
  animation: _mat-snack-bar-exit 75ms cubic-bezier(0.4, 0, 1, 1) forwards;
}

.mat-mdc-snackbar-surface {
  box-shadow: 0px 3px 5px -1px rgba(0, 0, 0, 0.2), 0px 6px 10px 0px rgba(0, 0, 0, 0.14), 0px 1px 18px 0px rgba(0, 0, 0, 0.12);
  display: flex;
  align-items: center;
  justify-content: flex-start;
  box-sizing: border-box;
  padding-left: 0;
  padding-right: 8px;
}
[dir=rtl] .mat-mdc-snackbar-surface {
  padding-right: 0;
  padding-left: 8px;
}
.mat-mdc-snack-bar-container .mat-mdc-snackbar-surface {
  min-width: 344px;
  max-width: 672px;
}
.mat-mdc-snack-bar-handset .mat-mdc-snackbar-surface {
  width: 100%;
  min-width: 0;
}
@media (forced-colors: active) {
  .mat-mdc-snackbar-surface {
    outline: solid 1px;
  }
}
.mat-mdc-snack-bar-container .mat-mdc-snackbar-surface {
  color: var(--%NS%mat-snack-bar-supporting-text-color, var(--%NS%mat-sys-inverse-on-surface));
  border-radius: var(--%NS%mat-snack-bar-container-shape, var(--%NS%mat-sys-corner-extra-small));
  background-color: var(--%NS%mat-snack-bar-container-color, var(--%NS%mat-sys-inverse-surface));
}

.mdc-snackbar__label {
  width: 100%;
  flex-grow: 1;
  box-sizing: border-box;
  margin: 0;
  padding: 14px 8px 14px 16px;
}
[dir=rtl] .mdc-snackbar__label {
  padding-left: 8px;
  padding-right: 16px;
}
.mat-mdc-snack-bar-container .mdc-snackbar__label {
  font-family: var(--%NS%mat-snack-bar-supporting-text-font, var(--%NS%mat-sys-body-medium-font));
  font-size: var(--%NS%mat-snack-bar-supporting-text-size, var(--%NS%mat-sys-body-medium-size));
  font-weight: var(--%NS%mat-snack-bar-supporting-text-weight, var(--%NS%mat-sys-body-medium-weight));
  line-height: var(--%NS%mat-snack-bar-supporting-text-line-height, var(--%NS%mat-sys-body-medium-line-height));
}

.mat-mdc-snack-bar-actions {
  display: flex;
  flex-shrink: 0;
  align-items: center;
  box-sizing: border-box;
}

.mat-mdc-snack-bar-handset,
.mat-mdc-snack-bar-container,
.mat-mdc-snack-bar-label {
  flex: 1 1 auto;
}

.mat-mdc-snack-bar-container .mat-mdc-button.mat-mdc-snack-bar-action:not(:disabled).mat-unthemed {
  color: var(--%NS%mat-snack-bar-button-color, var(--%NS%mat-sys-inverse-primary));
}
.mat-mdc-snack-bar-container .mat-mdc-button.mat-mdc-snack-bar-action:not(:disabled) {
  --%NS%mat-button-text-state-layer-color: currentColor;
  --%NS%mat-button-text-ripple-color: currentColor;
}
.mat-mdc-snack-bar-container .mat-mdc-button.mat-mdc-snack-bar-action:not(:disabled) .mat-ripple-element {
  opacity: 0.1;
}
`],encapsulation:2,changeDetection:1})})()}return n})(),Gw=new I("mat-snack-bar-default-options",{providedIn:"root",factory:()=>new La}),lo=(()=>{class n{_live=p(Bd);_injector=p(ce);_breakpointObserver=p(Fd);_parentSnackBar=p(n,{optional:!0,skipSelf:!0});_defaultConfig=p(Gw);_animationsDisabled=tt();_snackBarRefAtThisLevel=null;simpleSnackBarComponent=Rg;snackBarContainerComponent=qw;handsetCssClass="mat-mdc-snack-bar-handset";get _openedSnackBarRef(){let e=this._parentSnackBar;return e?e._openedSnackBarRef:this._snackBarRefAtThisLevel}set _openedSnackBarRef(e){this._parentSnackBar?this._parentSnackBar._openedSnackBarRef=e:this._snackBarRefAtThisLevel=e}openFromComponent(e,i){return this._attach(e,i)}openFromTemplate(e,i){return this._attach(e,i)}open(e,i="",a){let r=N(N({},this._defaultConfig),a);return r.data={message:e,action:i},r.announcementMessage===e&&(r.announcementMessage=void 0),this.openFromComponent(this.simpleSnackBarComponent,r)}dismiss(){this._openedSnackBarRef&&this._openedSnackBarRef.dismiss()}ngOnDestroy(){this._snackBarRefAtThisLevel&&this._snackBarRefAtThisLevel.dismiss()}_attachSnackBarContainer(e,i){let a=i&&i.viewContainerRef&&i.viewContainerRef.injector,r=ce.create({parent:a||this._injector,providers:[{provide:La,useValue:i}]}),o=new Ta(this.snackBarContainerComponent,i.viewContainerRef,r),s=e.attach(o);return s.instance.snackBarConfig=i,s.instance}_attach(e,i){let a=N(N(N({},new La),this._defaultConfig),i),r=this._createOverlay(a),o=this._attachSnackBarContainer(r,a),s=new so(o,r);if(e instanceof ai){let l=new qn(e,null,{$implicit:a.data,snackBarRef:s});s.instance=o.attachTemplatePortal(l)}else{let l=this._createInjector(a,s),c=new Ta(e,void 0,l),d=o.attachComponentPortal(c);s.instance=d.instance}return this._breakpointObserver.observe(rg.HandsetPortrait).pipe(Ie(r.detachments())).subscribe(l=>{r.overlayElement.classList.toggle(this.handsetCssClass,l.matches)}),a.announcementMessage&&o._onAnnounce.subscribe(()=>{this._live.announce(a.announcementMessage,a.politeness)}),this._animateSnackBar(s,a),this._openedSnackBarRef=s,this._openedSnackBarRef}_animateSnackBar(e,i){e.afterDismissed().subscribe(()=>{this._openedSnackBarRef==e&&(this._openedSnackBarRef=null),i.announcementMessage&&this._live.clear()}),i.duration&&i.duration>0&&e.afterOpened().subscribe(()=>e._dismissAfter(i.duration)),this._openedSnackBarRef?(this._openedSnackBarRef.afterDismissed().subscribe(()=>{e.containerInstance.enter()}),this._openedSnackBarRef.dismiss()):e.containerInstance.enter()}_createOverlay(e){let i=new hi;i.direction=e.direction;let a=yl(this._injector),r=e.direction==="rtl",o=e.horizontalPosition==="left"||e.horizontalPosition==="start"&&!r||e.horizontalPosition==="end"&&r,s=!o&&e.horizontalPosition!=="center";return o?a.left("0"):s?a.right("0"):a.centerHorizontally(),e.verticalPosition==="top"?a.top("0"):a.bottom("0"),i.positionStrategy=a,i.disableAnimations=this._animationsDisabled,Pa(this._injector,i)}_createInjector(e,i){let a=e&&e.viewContainerRef&&e.viewContainerRef.injector;return ce.create({parent:a||this._injector,providers:[{provide:so,useValue:i},{provide:Og,useValue:e.data}]})}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var tm=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({providers:[lo],imports:[Hi,Ra,Gn,Rg,me]})}return n})();var fi=class{},kl=class n{constructor(t){this.snackBar=t}snackBar;showError(t){this.snackBar.open(t,"Close",{verticalPosition:"top"})}showWarning(t){this.snackBar.open(t,"Close",{duration:3e3})}static \u0275fac=function(e){return new(e||n)(B(lo))};static \u0275prov=te({token:n,factory:n.\u0275fac})};var kt=class n{constructor(t){this.notificationService=t}notificationService;static BASE_URL=window.location.pathname+window.location.search+"#";toAsyncApi(t){try{let e=this.mapChannels(t.channels,t.operations,t.components.messages,t.components.schemas,t.servers,t.defaultContentType),i={info:this.mapInfo(t),servers:this.mapServers(t.servers),channels:e,channelOperations:e.flatMap(a=>a.operations),components:{schemas:this.mapSchemas(t.components.schemas)},defaultContentType:t.defaultContentType};return this.postProcess(i),i}catch(e){this.notificationService.showError("Error parsing AsyncAPI: "+e?.message);return}}mapInfo(t){return{title:t.info.title,version:t.info.version,description:t.info.description,contact:{url:t.info.contact?.url,email:t.info.contact?.email&&{name:t.info.contact.email,href:"mailto:"+t.info.contact.email}||void 0},license:{name:t.info.license?.name,url:t.info.license?.url},asyncApiJson:t}}mapServers(t){let e=new Map;if(t)for(let i in t){let a=t[i];e.set(i,{host:a.host,protocol:a.protocol,anchorIdentifier:Qr+i,anchorUrl:n.BASE_URL+Qr+i})}return e}mapChannels(t,e,i,a,r,o){let s={};for(let l in t)this.parsingErrorBoundary("channel "+l,()=>{let c=t[l],d="channel-"+this.toSafeAnchorIdentifier(l);s[l]={name:c.address,anchorIdentifier:d,anchorUrl:n.BASE_URL+d,operations:[],bindings:c.bindings||{}}});for(let l in e)this.parsingErrorBoundary("operation "+l,()=>{let c=e[l],d=this.resolveRefId(c.channel.$ref),m=t[d].address;this.verifyBindings(c.bindings,"operation "+l),this.mapServerAsyncApiMessages(m,t[d],i,a,c.messages,o).forEach(f=>{let g=this.parsingErrorBoundary("channel with name "+m,()=>this.mapChannelOperation(m,t[d],t,c,f,r));g!=null&&s[d].operations.push(g)})});return Object.values(s).forEach(l=>{l.operations=l.operations.sort((c,d)=>c.operation.protocol===d.operation.protocol?c.operation.operationType===d.operation.operationType?c.name===d.name?c.operation.message.name.localeCompare(d.operation.message.name):c.name.localeCompare(d.name):c.operation.operationType.localeCompare(d.operation.operationType):c.operation.protocol!=null&&d.operation.protocol!=null?c.operation.protocol.localeCompare(d.operation.protocol):0)}),Object.values(s).sort((l,c)=>l.name.localeCompare(c.name))}mapChannelOperation(t,e,i,a,r,o){let l=(e?.servers?.map(m=>this.resolveRefId(m.$ref))||o&&Object.keys(o)||[]).map(m=>({name:m,anchorIdentifier:Qr+m,anchorUrl:n.BASE_URL+Qr+m})),c=this.mapOperation(t,i,a.action,l,r,a.bindings,a.description,a.reply),d=Id+[c.protocol,this.toSafeAnchorIdentifier(t),c.operationType,c.message.title].join("-");return{name:t,anchorIdentifier:d,anchorUrl:n.BASE_URL+d,description:e.description,operation:c,bindings:e.bindings||{}}}mapServerAsyncApiMessages(t,e,i,a,r,o){return r.map(s=>this.parsingErrorBoundary("message of channel "+t,()=>{let l=this.resolveRefId(s.$ref),c=this.resolveTitleFromName(l),d=e.messages[l],m=this.resolveRefId(d.$ref),u=i[m];return this.verifyBindings(u.bindings,"message "+u.name),{name:u.name||c,title:u.title||c,description:u.description,contentType:u.contentType||o,payload:this.mapPayload(u.name||c,u.payload.schema,a),headers:this.mapHeaders(u.headers),bindings:this.mapServerAsyncApiMessageBindings(u.bindings),rawBindings:u.bindings||{}}})).filter(s=>s!==void 0)}mapHeaders(t){if(t===void 0)return;let e=this.resolveRefId(t.$ref);return{ts_type:"ref",name:e,title:e,anchorUrl:n.BASE_URL+e}}mapPayload(t,e,i){if("$ref"in e){let a=this.resolveRefId(e.$ref);return{ts_type:"ref",name:a,title:this.resolveTitleFromName(a),anchorUrl:n.BASE_URL+a}}return this.mapSchemaObj(t,e,i)}mapServerAsyncApiMessageBindings(t){let e=new Map;return t!==void 0&&Object.keys(t).forEach(i=>{e.set(i,this.mapServerAsyncApiMessageBinding(t[i]))}),e}mapServerAsyncApiMessageBinding(t){let e={};return Object.keys(t).forEach(i=>{let a=t[i];typeof a=="object"?e[i]=this.mapServerAsyncApiMessageBinding(a):e[i]=a}),e}mapOperation(t,e,i,a,r,o,s,l){return{protocol:this.getProtocol(o)||"unsupported-protocol",bindings:o||{},servers:a,operationType:i=="send"?"send":"receive",channelName:t,description:s,message:r,reply:this.mapOperationReply(l,e)}}mapOperationReply(t,e){if(!t)return;let i=this.resolveRefId(t.channel.$ref),a=e[i].address,r=this.resolveRefId(t.messages[0].$ref);return{channelAnchorUrl:n.BASE_URL+Id+i,channelName:a,messageAnchorUrl:n.BASE_URL+r,messageName:r}}getProtocol(t){if(t!==void 0)return Object.keys(t)[0]}mapSchemas(t){let e=new Map;return Object.entries(t).forEach(([i,a])=>{let r=this.parsingErrorBoundary("schema with name "+i,()=>this.mapSchema(i,a,t));r!=null&&e.set(i,r)}),new Map([...e.entries()].sort((i,a)=>i[1].title.localeCompare(a[1].title)))}mapSchema(t,e,i){return"$ref"in e?this.mapSchemaRef(t,e):this.mapSchemaObj(t,e,i)}mapSchemaObj(t,e,i){let a={};this.addPropertiesToSchema(e,a,i),e.allOf!==void 0&&e.allOf.forEach(s=>{this.addPropertiesToSchema(s,a,i)}),e.anyOf!==void 0&&e.anyOf.length>0&&this.addPropertiesToSchema(e.anyOf[0],a,i),e.oneOf!==void 0&&e.oneOf.length>0&&this.addPropertiesToSchema(e.oneOf[0],a,i);let r=e.items!==void 0?this.mapSchema(t+"[]",e.items,i):void 0,o=e.examples!==void 0&&0<e.examples.length?new Zt(e.examples[0]):void 0;return{ts_type:"object",name:t,title:this.resolveTitleFromName(t)||"undefined-title",usedBy:[],anchorIdentifier:this.toSafeAnchorIdentifier(t),anchorUrl:n.BASE_URL+t,description:e.description,deprecated:e.deprecated,enum:e.enum,example:o,type:e.type,format:e.format,properties:a,required:e.required,items:r,minItems:e.minItems,maxItems:e.maxItems,uniqueItems:e.uniqueItems,minLength:e.minLength,maxLength:e.maxLength,pattern:e.pattern,minimum:e.exclusiveMinimum?e.exclusiveMinimum:e.minimum,maximum:e.exclusiveMaximum?e.exclusiveMinimum:e.maximum,exclusiveMinimum:e.minimum==e.exclusiveMinimum,exclusiveMaximum:e.maximum==e.exclusiveMaximum,multipleOf:e.multipleOf}}addPropertiesToSchema(t,e,i){let a=this.resolveSchema(t,i);"properties"in a&&a.properties!==void 0&&Object.entries(a.properties).forEach(([r,o])=>{e[r]=this.mapSchema(r,o,i)})}resolveSchema(t,e){let i=t;for(;"$ref"in i;){let a=this.resolveRefId(i.$ref),r=e[a];if(r!==void 0)i=r;else throw new Error("Schema "+a+" not found")}return i}mapSchemaRef(t,e){let i=this.resolveRefId(e.$ref);return{ts_type:"object",name:t,title:this.resolveTitleFromName(t),usedBy:[],anchorIdentifier:this.toSafeAnchorIdentifier(t),anchorUrl:n.BASE_URL+t,refAnchorUrl:n.BASE_URL+i,refName:i,refTitle:this.resolveTitleFromName(i)}}resolveRefId(t){return t.split("/").pop()}resolveTitleFromName(t){return t.split(".").pop()}verifyBindings(t,e){(t==null||Object.keys(t).length==0)&&this.notificationService.showWarning("No binding defined for "+e)}postProcess(t){t.components.schemas.forEach(e=>{t.channels.forEach(i=>{i.operations.forEach(a=>{a.operation.message.payload.name===e.name&&e.usedBy.push({name:a.name,anchorUrl:a.anchorUrl,type:"channel"}),a.operation.message.headers?.name===e.name&&e.usedBy.push({name:a.name,anchorUrl:a.anchorUrl,type:"channel"})})}),t.components.schemas.forEach(i=>{Object.values(i?.properties||{}).forEach(a=>{a.refName===e.name&&e.usedBy.push({name:i.title,anchorUrl:i.anchorUrl,type:"schema"})}),i.items?.refName===e.name&&e.usedBy.push({name:i.title,anchorUrl:i.anchorUrl,type:"schema"})})})}toSafeAnchorIdentifier(t){return t.replaceAll(/\$/g,"\xA7")}parsingErrorBoundary(t,e){return jf(e,i=>{this.notificationService.showError("Error parsing AsyncAPI "+t+": "+i.message)})}static \u0275fac=function(e){return new(e||n)(B(fi))};static \u0275prov=te({token:n,factory:n.\u0275fac})};var S_=Iy(w_());var oc={$schema:"http://json-schema.org/draft-07/schema#",$ref:"#/definitions/ServerAsyncApi",definitions:{ServerAsyncApi:{type:"object",properties:{asyncapi:{type:"string"},info:{$ref:"#/definitions/ServerAsyncApiInfo"},defaultContentType:{type:"string"},servers:{$ref:"#/definitions/ServerServers"},channels:{$ref:"#/definitions/ServerChannels"},operations:{$ref:"#/definitions/ServerOperations"},components:{$ref:"#/definitions/ServerComponents"}},required:["asyncapi","info","defaultContentType","channels","operations","components"],additionalProperties:!1},ServerAsyncApiInfo:{type:"object",properties:{title:{type:"string"},version:{type:"string"},description:{type:"string"},contact:{type:"object",properties:{name:{type:"string"},url:{type:"string"},email:{type:"string"}},additionalProperties:{}},license:{type:"object",properties:{name:{type:"string"},url:{type:"string"}},additionalProperties:{}},termsOfService:{type:"string"}},required:["title"],additionalProperties:{}},ServerServers:{type:"object",additionalProperties:{type:"object",properties:{host:{type:"string"},protocol:{type:"string"},description:{type:"string"}},required:["host","protocol"],additionalProperties:!1}},ServerChannels:{type:"object",additionalProperties:{$ref:"#/definitions/ServerChannel"}},ServerChannel:{type:"object",properties:{address:{type:"string"},description:{type:"string"},messages:{type:"object",additionalProperties:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},servers:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},bindings:{$ref:"#/definitions/ServerBindings"}},required:["address"],additionalProperties:!1},ServerBindings:{type:"object",additionalProperties:{$ref:"#/definitions/ServerBinding"}},ServerBinding:{type:"object",additionalProperties:{anyOf:[{$ref:"#/definitions/ServerBinding"},{}]}},ServerOperations:{type:"object",additionalProperties:{$ref:"#/definitions/ServerOperation"}},ServerOperation:{type:"object",properties:{action:{type:"string"},title:{type:"string"},description:{type:"string"},channel:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},messages:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},reply:{$ref:"#/definitions/ServerOperationReply"},bindings:{$ref:"#/definitions/ServerBindings"}},required:["action","channel","messages"],additionalProperties:!1},ServerOperationReply:{type:"object",properties:{channel:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},messages:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}}},required:["channel","messages"],additionalProperties:!1},ServerComponents:{type:"object",properties:{schemas:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiSchema"}},messages:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiMessage"}}},required:["schemas","messages"],additionalProperties:!1},ServerAsyncApiSchema:{type:"object",properties:{title:{type:"string"},description:{type:"string"},deprecated:{type:"boolean"},enum:{type:"array",items:{type:["string","null"]}},examples:{type:"array",items:{}},type:{anyOf:[{type:"string"},{type:"array",items:{type:"string"}}]},format:{type:"string"},not:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"},allOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},anyOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},oneOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},properties:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},required:{type:"array",items:{type:"string"}},items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"},minItems:{type:"number"},maxItems:{type:"number"},uniqueItems:{type:"boolean"},minLength:{type:"number"},maxLength:{type:"number"},pattern:{type:"string"},minimum:{type:"number"},maximum:{type:"number"},exclusiveMinimum:{type:"number"},exclusiveMaximum:{type:"number"},multipleOf:{type:"number"}},additionalProperties:!1},ServerAsyncApiSchemaOrRef:{anyOf:[{$ref:"#/definitions/ServerAsyncApiSchema"},{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}]},ServerAsyncApiMessage:{type:"object",properties:{name:{type:"string"},title:{type:"string"},description:{type:"string"},contentType:{type:"string"},payload:{type:"object",properties:{schemaFormat:{type:"string"},schema:{anyOf:[{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},{$ref:"#/definitions/ServerAsyncApiSchema"}]}},required:["schemaFormat","schema"],additionalProperties:!1},headers:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},bindings:{$ref:"#/definitions/ServerBindings"}},required:["payload"],additionalProperties:!1}}};var Ja=class n{constructor(t){this.notificationService=t}notificationService;ajv=new S_.default({allErrors:!0});_logToConsole=!0;validate(t){let e=JSON.parse(JSON.stringify(t));this.ajv.opts.removeAdditional=!1;let i=this.ajv.compile(oc);i(e)||this._logToConsole&&console.info("Validation error while parsing AsyncAPI file in Springwolf format (strict mode)",i.errors),this.ajv.removeSchema(oc),this.ajv.opts.removeAdditional=!0;let r=this.ajv.compile(oc);r(e)||(this.notificationService.showError("Validation error while parsing AsyncAPI file in Springwolf format (lenient mode), see console logs for details."),this._logToConsole&&console.warn("Validation error while parsing AsyncAPI file in Springwolf format (lenient mode)",r.errors))}static \u0275fac=function(e){return new(e||n)(B(fi))};static \u0275prov=te({token:n,factory:n.\u0275fac})};var mt=class n{constructor(t,e,i,a){this.http=t;this.asyncApiMapperService=e;this.uiService=i;this.asyncApiValidatorService=a;this.docs=this.uiService.isGroup$.pipe(Gt(r=>{let o=r==Me.DEFAULT_GROUP?Mn.docs:Mn.getDocsForGroupEndpoint(r);return this.http.get(o)}),ti(r=>this.asyncApiValidatorService.validate(r)),De(r=>this.asyncApiMapperService.toAsyncApi(r)),We(r=>r!==void 0),Fn())}http;asyncApiMapperService;uiService;asyncApiValidatorService;docs;getAsyncApi(){return this.docs}static \u0275fac=function(e){return new(e||n)(B(Xt),B(kt),B(Me),B(Ja))};static \u0275prov=te({token:n,factory:n.\u0275fac})};var er=class n{constructor(t){this.http=t}http;publishable={};canPublish(t){return this.publishable[t]===void 0&&(this.publishable[t]=this.http.get(Mn.getPublishEndpoint(t),{observe:"response"}).pipe(De(e=>e.status===200),ds())),this.publishable[t]}publish(t,e,i,a,r,o){let s=Mn.getPublishEndpoint(t),l=new qt().set("topic",e),c={payload:i,type:a,headers:r,bindings:o};return console.log(`Publishing to ${s} with messageBinding ${JSON.stringify(o)} and headers ${JSON.stringify(r)}: ${JSON.stringify(c)}`),this.http.post(s,c,{params:l})}static \u0275fac=function(e){return new(e||n)(B(Xt))};static \u0275prov=te({token:n,factory:n.\u0275fac})};var k_={providers:[fh(),tf(),md(pd()),Ls.production?[]:Qu(nf.forRoot(Vs,{delay:100})),Nd(),{provide:Da,useClass:Zs},mt,kt,Ja,{provide:fi,useClass:kl},er,{provide:Me,useClass:Qs}]};var Yp=new I("MAT_MENU_PANEL"),jo=(()=>{class n{_isAnchor;_elementRef=p(X);_document=p(Y);_focusMonitor=p(Nn);_parentMenu=p(Yp,{optional:!0});_changeDetectorRef=p(et);role="menuitem";disabled=!1;disabledInteractive=!1;disableRipple=!1;_hovered=new z;_focused=new z;_highlighted=!1;_triggersSubmenu=!1;constructor(){p(ct).load(un),this._parentMenu?.addItem?.(this),this._isAnchor=this._elementRef.nativeElement.tagName==="A"}focus(e,i){this._focusMonitor&&e?this._focusMonitor.focusVia(this._getHostElement(),e,i):this._getHostElement().focus(i),this._focused.next(this)}ngAfterViewInit(){this._focusMonitor&&this._focusMonitor.monitor(this._elementRef,!1)}ngOnDestroy(){this._focusMonitor&&this._focusMonitor.stopMonitoring(this._elementRef),this._parentMenu&&this._parentMenu.removeItem&&this._parentMenu.removeItem(this),this._hovered.complete(),this._focused.complete()}_getTabIndex(){return this.disabled&&!this.disabledInteractive?"-1":"0"}_getAriaDisabled(){return this._isAnchor?this.disabled||null:this.disabled&&this.disabledInteractive?!0:null}_getDisabled(){return this.disabledInteractive||!this.disabled?null:!0}_getHostElement(){return this._elementRef.nativeElement}_checkDisabled(e){this.disabled&&(e.preventDefault(),e.stopPropagation())}_handleMouseEnter(){this._hovered.next(this)}getLabel(){let e=this._elementRef.nativeElement.cloneNode(!0),i=e.querySelectorAll("mat-icon, .material-icons, .material-symbols-outlined, .material-symbols-rounded, .material-symbols-sharp");for(let a=0;a<i.length;a++)i[a].remove();return e.textContent?.trim()||""}_setHighlighted(e){this._highlighted=e,this._changeDetectorRef.markForCheck()}_setTriggersSubmenu(e){this._triggersSubmenu=e,this._changeDetectorRef.markForCheck()}_hasFocus(){return this._document&&this._document.activeElement===this._getHostElement()}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=[[["mat-icon"],["","matMenuItemIcon",""]],"*"],i=["mat-icon, [matMenuItemIcon]","*"];function a(r,o){r&1&&(pa(),y(0,"svg",2),P(1,"polygon",3),v())}return T({type:n,selectors:[["","mat-menu-item",""]],hostAttrs:[1,"mat-mdc-menu-item","mat-focus-indicator"],hostVars:12,hostBindings:function(o,s){o&1&&ye("click",function(c){return s._checkDisabled(c)})("mouseenter",function(){return s._handleMouseEnter()}),o&2&&(fe("role",s.role)("tabindex",s._getTabIndex())("aria-disabled",s._getAriaDisabled())("disabled",s._getDisabled()),J("mat-mdc-menu-item-highlighted",s._highlighted)("mat-mdc-menu-item-submenu-trigger",s._triggersSubmenu)("mat-mdc-menu-item-disabled",s.disabled)("mat-mdc-menu-item-disabled-interactive",s.disabledInteractive))},inputs:{role:"role",disabled:[2,"disabled","disabled",Ae],disabledInteractive:[2,"disabledInteractive","disabledInteractive",Ae],disableRipple:[2,"disableRipple","disableRipple",Ae]},exportAs:["matMenuItem"],ngContentSelectors:i,decls:5,vars:3,consts:[[1,"mat-mdc-menu-item-text"],["matRipple","",1,"mat-mdc-menu-ripple",3,"matRippleDisabled","matRippleTrigger"],["viewBox","0 0 5 10","focusable","false","aria-hidden","true",1,"mat-mdc-menu-submenu-icon"],["points","0,0 5,5 0,10"]],template:function(o,s){o&1&&(de(e),j(0),y(1,"span",0),j(2,1),v(),P(3,"div",1),E(4,a,2,0,":svg:svg",2)),o&2&&(h(3),R("matRippleDisabled",s.disableRipple||s.disabled)("matRippleTrigger",s._getHostElement()),h(),D(s._triggersSubmenu?4:-1))},dependencies:[xl],encapsulation:2})})()}return n})();var H1=new I("MatMenuContent");var U1=new I("mat-menu-default-options",{providedIn:"root",factory:()=>({overlapTrigger:!1,xPosition:"after",yPosition:"below",backdropClass:"cdk-overlay-transparent-backdrop"})}),Wp="_mat-menu-enter",sc="_mat-menu-exit",nr=(()=>{class n{_elementRef=p(X);_changeDetectorRef=p(et);_injector=p(ce);_keyManager;_xPosition;_yPosition;_firstItemFocusRef;_exitFallbackTimeout;_animationsDisabled=tt();_allItems;_directDescendantItems=new Bn;_classList={};_panelAnimationState="void";_animationDone=new z;_isAnimating=ne(!1);parentMenu;direction;overlayPanelClass;backdropClass;ariaLabel;ariaLabelledby;ariaDescribedby;get xPosition(){return this._xPosition}set xPosition(e){this._xPosition=e,this.setPositionClasses()}get yPosition(){return this._yPosition}set yPosition(e){this._yPosition=e,this.setPositionClasses()}templateRef;items;lazyContent;overlapTrigger=!1;hasBackdrop;get panelClass(){return this._previousPanelClass}set panelClass(e){let i=this._previousPanelClass,a=N({},this._classList);i&&i.length&&i.split(" ").forEach(r=>{a[r]=!1}),this._previousPanelClass=e,e&&e.length&&(e.split(" ").forEach(r=>{a[r]=!0}),this._elementRef.nativeElement.className=""),this._classList=a}_previousPanelClass="";get classList(){return this.panelClass}set classList(e){this.panelClass=e}closed=new Te;close=this.closed;panelId=p(St).getId("mat-menu-panel-");constructor(){let e=p(U1);this.overlayPanelClass=e.overlayPanelClass||"",this._xPosition=e.xPosition,this._yPosition=e.yPosition,this.backdropClass=e.backdropClass,this.overlapTrigger=e.overlapTrigger,this.hasBackdrop=e.hasBackdrop}ngOnInit(){this.setPositionClasses()}ngAfterContentInit(){this._updateDirectDescendants(),this._keyManager=new Vi(this._directDescendantItems).withWrap().withTypeAhead().withHomeAndEnd().skipPredicate(e=>e.disabled&&!e.disabledInteractive),this._keyManager.tabOut.subscribe(()=>this.closed.emit("tab")),this._directDescendantItems.changes.pipe(vt(this._directDescendantItems),Gt(e=>It(...e.map(i=>i._focused)))).subscribe(e=>this._keyManager.updateActiveItem(e)),this._directDescendantItems.changes.subscribe(e=>{let i=this._keyManager;if(this._panelAnimationState==="enter"&&i.activeItem?._hasFocus()){let a=e.toArray(),r=Math.max(0,Math.min(a.length-1,i.activeItemIndex||0));a[r]&&!a[r].disabled?i.setActiveItem(r):i.setNextItemActive()}})}ngOnDestroy(){this._keyManager?.destroy(),this._directDescendantItems.destroy(),this.closed.complete(),this._firstItemFocusRef?.destroy(),clearTimeout(this._exitFallbackTimeout)}_hovered(){return this._directDescendantItems.changes.pipe(vt(this._directDescendantItems),Gt(i=>It(...i.map(a=>a._hovered))))}addItem(e){}removeItem(e){}_handleKeydown(e){let i=e.keyCode,a=this._keyManager;switch(i){case 27:ui(e)||(e.preventDefault(),this.closed.emit("keydown"));break;case 37:this.parentMenu&&this.direction==="ltr"&&this.closed.emit("keydown");break;case 39:this.parentMenu&&this.direction==="rtl"&&this.closed.emit("keydown");break;default:(i===38||i===40)&&a.setFocusOrigin("keyboard"),a.onKeydown(e);return}}focusFirstItem(e="program"){this._firstItemFocusRef?.destroy(),this._firstItemFocusRef=yt(()=>{let i=this._resolvePanel();if(!i||!i.contains(document.activeElement)){let a=this._keyManager;a.setFocusOrigin(e).setFirstItemActive(),!a.activeItem&&i&&i.focus()}},{injector:this._injector})}resetActiveItem(){this._keyManager.setActiveItem(-1)}setElevation(e){}setPositionClasses(e=this.xPosition,i=this.yPosition){this._classList=Ee(N({},this._classList),{"mat-menu-before":e==="before","mat-menu-after":e==="after","mat-menu-above":i==="above","mat-menu-below":i==="below"}),this._changeDetectorRef.markForCheck()}_onAnimationDone(e){let i=e===sc;(i||e===Wp)&&(i&&(clearTimeout(this._exitFallbackTimeout),this._exitFallbackTimeout=void 0),this._animationDone.next(i?"void":"enter"),this._isAnimating.set(!1))}_onAnimationStart(e){(e===Wp||e===sc)&&this._isAnimating.set(!0)}_setIsOpen(e){if(this._panelAnimationState=e?"enter":"void",e){if(this._keyManager.activeItemIndex===0){let i=this._resolvePanel();i&&(i.scrollTop=0)}}else this._animationsDisabled||(this._exitFallbackTimeout=setTimeout(()=>this._onAnimationDone(sc),200));this._animationsDisabled&&setTimeout(()=>{this._onAnimationDone(e?Wp:sc)}),this._changeDetectorRef.markForCheck()}_updateDirectDescendants(){this._allItems.changes.pipe(vt(this._allItems)).subscribe(e=>{this._directDescendantItems.reset(e.filter(i=>i._parentMenu===this)),this._directDescendantItems.notifyOnChanges()})}_resolvePanel(){let e=null;return this._directDescendantItems.length&&(e=this._directDescendantItems.first._getHostElement().closest('[role="menu"]')),e}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=["*"];function i(a,r){if(a&1){let o=ut();Ze(0,"div",0),bs("click",function(){at(o);let l=M();return rt(l.closed.emit("click"))})("animationstart",function(l){at(o);let c=M();return rt(c._onAnimationStart(l.animationName))})("animationend",function(l){at(o);let c=M();return rt(c._onAnimationDone(l.animationName))})("animationcancel",function(l){at(o);let c=M();return rt(c._onAnimationDone(l.animationName))}),Ze(1,"div",1),j(2),Je()()}if(a&2){let o=M();At(o._classList),J("mat-menu-panel-animations-disabled",o._animationsDisabled)("mat-menu-panel-exit-animation",o._panelAnimationState==="void")("mat-menu-panel-animating",o._isAnimating()),oi("id",o.panelId),fe("aria-label",o.ariaLabel||null)("aria-labelledby",o.ariaLabelledby||null)("aria-describedby",o.ariaDescribedby||null)}}return T({type:n,selectors:[["mat-menu"]],contentQueries:function(r,o,s){if(r&1&&ot(s,H1,5)(s,jo,5)(s,jo,4),r&2){let l;H(l=U())&&(o.lazyContent=l.first),H(l=U())&&(o._allItems=l),H(l=U())&&(o.items=l)}},viewQuery:function(r,o){if(r&1&&qe(ai,5),r&2){let s;H(s=U())&&(o.templateRef=s.first)}},hostVars:3,hostBindings:function(r,o){r&2&&fe("aria-label",null)("aria-labelledby",null)("aria-describedby",null)},inputs:{backdropClass:"backdropClass",ariaLabel:[0,"aria-label","ariaLabel"],ariaLabelledby:[0,"aria-labelledby","ariaLabelledby"],ariaDescribedby:[0,"aria-describedby","ariaDescribedby"],xPosition:"xPosition",yPosition:"yPosition",overlapTrigger:[2,"overlapTrigger","overlapTrigger",Ae],hasBackdrop:[2,"hasBackdrop","hasBackdrop",a=>a==null?null:Ae(a)],panelClass:[0,"class","panelClass"],classList:"classList"},outputs:{closed:"closed",close:"close"},exportAs:["matMenu"],features:[Qe([{provide:Yp,useExisting:n}])],ngContentSelectors:e,decls:1,vars:0,consts:[["tabindex","-1","role","menu",1,"mat-mdc-menu-panel",3,"click","animationstart","animationend","animationcancel","id"],[1,"mat-mdc-menu-content"]],template:function(r,o){r&1&&(de(),$c(0,i,3,12,"ng-template"))},styles:[`mat-menu {
  display: none;
}

.mat-mdc-menu-content {
  margin: 0;
  padding: 8px 0;
  outline: 0;
}
.mat-mdc-menu-content,
.mat-mdc-menu-content .mat-mdc-menu-item .mat-mdc-menu-item-text {
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  flex: 1;
  white-space: normal;
  font-family: var(--%NS%mat-menu-item-label-text-font, var(--%NS%mat-sys-label-large-font));
  line-height: var(--%NS%mat-menu-item-label-text-line-height, var(--%NS%mat-sys-label-large-line-height));
  font-size: var(--%NS%mat-menu-item-label-text-size, var(--%NS%mat-sys-label-large-size));
  letter-spacing: var(--%NS%mat-menu-item-label-text-tracking, var(--%NS%mat-sys-label-large-tracking));
  font-weight: var(--%NS%mat-menu-item-label-text-weight, var(--%NS%mat-sys-label-large-weight));
}

@keyframes _mat-menu-enter {
  from {
    opacity: 0;
    transform: scale(0.8);
  }
  to {
    opacity: 1;
    transform: none;
  }
}
@keyframes _mat-menu-exit {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}
.mat-mdc-menu-panel {
  min-width: 112px;
  max-width: 280px;
  overflow: auto;
  box-sizing: border-box;
  outline: 0;
  animation: _mat-menu-enter 120ms cubic-bezier(0, 0, 0.2, 1);
  border-radius: var(--%NS%mat-menu-container-shape, var(--%NS%mat-sys-corner-extra-small));
  background-color: var(--%NS%mat-menu-container-color, var(--%NS%mat-sys-surface-container));
  box-shadow: var(--%NS%mat-menu-container-elevation-shadow, 0px 3px 1px -2px rgba(0, 0, 0, 0.2), 0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12));
  will-change: transform, opacity;
}
.mat-mdc-menu-panel.mat-menu-panel-exit-animation {
  animation: _mat-menu-exit 100ms 25ms linear forwards;
}
.mat-mdc-menu-panel.mat-menu-panel-animations-disabled {
  animation: none;
}
.mat-mdc-menu-panel.mat-menu-panel-animating {
  pointer-events: none;
}
.mat-mdc-menu-panel.mat-menu-panel-animating:has(.mat-mdc-menu-content:empty) {
  display: none;
}
@media (forced-colors: active) {
  .mat-mdc-menu-panel {
    outline: solid 1px;
  }
}
.mat-mdc-menu-panel .mat-divider {
  border-top-color: var(--%NS%mat-menu-divider-color, var(--%NS%mat-sys-surface-variant));
  margin-bottom: var(--%NS%mat-menu-divider-bottom-spacing, 8px);
  margin-top: var(--%NS%mat-menu-divider-top-spacing, 8px);
}

.mat-mdc-menu-item {
  display: flex;
  position: relative;
  align-items: center;
  justify-content: flex-start;
  overflow: hidden;
  padding: 0;
  cursor: pointer;
  width: 100%;
  text-align: left;
  box-sizing: border-box;
  color: inherit;
  font-size: inherit;
  background: none;
  text-decoration: none;
  margin: 0;
  min-height: 48px;
  padding-left: var(--%NS%mat-menu-item-leading-spacing, 12px);
  padding-right: var(--%NS%mat-menu-item-trailing-spacing, 12px);
  -webkit-user-select: none;
  user-select: none;
  cursor: pointer;
  outline: none;
  border: none;
  -webkit-tap-highlight-color: transparent;
}
.mat-mdc-menu-item::-moz-focus-inner {
  border: 0;
}
[dir=rtl] .mat-mdc-menu-item {
  padding-left: var(--%NS%mat-menu-item-trailing-spacing, 12px);
  padding-right: var(--%NS%mat-menu-item-leading-spacing, 12px);
}
.mat-mdc-menu-item:has(.material-icons, .material-symbols-outlined, .material-symbols-rounded, .material-symbols-sharp, mat-icon, [matButtonIcon]) {
  padding-left: var(--%NS%mat-menu-item-with-icon-leading-spacing, 12px);
  padding-right: var(--%NS%mat-menu-item-with-icon-trailing-spacing, 12px);
}
[dir=rtl] .mat-mdc-menu-item:has(.material-icons, .material-symbols-outlined, .material-symbols-rounded, .material-symbols-sharp, mat-icon, [matButtonIcon]) {
  padding-left: var(--%NS%mat-menu-item-with-icon-trailing-spacing, 12px);
  padding-right: var(--%NS%mat-menu-item-with-icon-leading-spacing, 12px);
}
.mat-mdc-menu-item, .mat-mdc-menu-item:visited, .mat-mdc-menu-item:link {
  color: var(--%NS%mat-menu-item-label-text-color, var(--%NS%mat-sys-on-surface));
}
.mat-mdc-menu-item .mat-icon-no-color,
.mat-mdc-menu-item .mat-mdc-menu-submenu-icon {
  color: var(--%NS%mat-menu-item-icon-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-mdc-menu-item[disabled], .mat-mdc-menu-item.mat-mdc-menu-item-disabled {
  cursor: default;
  opacity: 0.38;
}
.mat-mdc-menu-item[disabled]::after, .mat-mdc-menu-item.mat-mdc-menu-item-disabled::after {
  display: block;
  position: absolute;
  content: "";
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
}
.mat-mdc-menu-item:focus {
  outline: 0;
}
.mat-mdc-menu-item .mat-icon {
  flex-shrink: 0;
  margin-right: var(--%NS%mat-menu-item-spacing, 12px);
  height: var(--%NS%mat-menu-item-icon-size, 24px);
  width: var(--%NS%mat-menu-item-icon-size, 24px);
}
[dir=rtl] .mat-mdc-menu-item {
  text-align: right;
}
[dir=rtl] .mat-mdc-menu-item .mat-icon {
  margin-right: 0;
  margin-left: var(--%NS%mat-menu-item-spacing, 12px);
}
.mat-mdc-menu-item:not([disabled]):hover {
  background-color: var(--%NS%mat-menu-item-hover-state-layer-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) calc(var(--%NS%mat-sys-hover-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-menu-item:not([disabled]).cdk-program-focused, .mat-mdc-menu-item:not([disabled]).cdk-keyboard-focused, .mat-mdc-menu-item:not([disabled]).mat-mdc-menu-item-highlighted {
  background-color: var(--%NS%mat-menu-item-focus-state-layer-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) calc(var(--%NS%mat-sys-focus-state-layer-opacity) * 100%), transparent));
}
@media (forced-colors: active) {
  .mat-mdc-menu-item {
    margin-top: 1px;
  }
}

.mat-mdc-menu-submenu-icon {
  width: var(--%NS%mat-menu-item-icon-size, 24px);
  height: 10px;
  fill: currentColor;
  padding-left: var(--%NS%mat-menu-item-spacing, 12px);
}
[dir=rtl] .mat-mdc-menu-submenu-icon {
  padding-right: var(--%NS%mat-menu-item-spacing, 12px);
  padding-left: 0;
}
[dir=rtl] .mat-mdc-menu-submenu-icon polygon {
  transform: scaleX(-1);
  transform-origin: center;
}
@media (forced-colors: active) {
  .mat-mdc-menu-submenu-icon {
    fill: CanvasText;
  }
}

.mat-mdc-menu-item .mat-mdc-menu-ripple {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  pointer-events: none;
}
`],encapsulation:2})})()}return n})(),q1=new I("mat-menu-scroll-strategy",{providedIn:"root",factory:()=>{let n=p(ce);return()=>ro(n)}});var tr=new WeakMap,G1=(()=>{class n{_canHaveBackdrop;_element=p(X);_viewContainerRef=p(xn);_menuItemInstance=p(jo,{optional:!0,self:!0});_dir=p(Tt,{optional:!0});_focusMonitor=p(Nn);_ngZone=p(W);_injector=p(ce);_scrollStrategy=p(q1);_changeDetectorRef=p(et);_animationsDisabled=tt();_portal;_overlayRef=null;_menuOpen=!1;_closingActionsSubscription=bt.EMPTY;_menuCloseSubscription=bt.EMPTY;_pendingRemoval;_parentMaterialMenu;_parentInnerPadding;_openedBy=void 0;get _menu(){return this._menuInternal}set _menu(e){e!==this._menuInternal&&(this._menuInternal=e,this._menuCloseSubscription.unsubscribe(),e?(this._parentMaterialMenu,this._menuCloseSubscription=e.close.subscribe(i=>{this._destroyMenu(i),(i==="click"||i==="tab")&&this._parentMaterialMenu&&this._parentMaterialMenu.closed.emit(i)})):this._destroyMenu(),this._menuItemInstance?._setTriggersSubmenu(this._triggersSubmenu()))}_menuInternal=null;constructor(e){this._canHaveBackdrop=e;let i=p(Yp,{optional:!0});this._parentMaterialMenu=i instanceof nr?i:void 0}ngOnDestroy(){this._menu&&this._ownsMenu(this._menu)&&tr.delete(this._menu),this._pendingRemoval?.unsubscribe(),this._menuCloseSubscription.unsubscribe(),this._closingActionsSubscription.unsubscribe(),this._overlayRef&&(this._overlayRef.dispose(),this._overlayRef=null)}get menuOpen(){return this._menuOpen}get dir(){return this._dir&&this._dir.value==="rtl"?"rtl":"ltr"}_triggersSubmenu(){return!!(this._menuItemInstance&&this._parentMaterialMenu&&this._menu)}_closeMenu(){this._menu?.close.emit()}_openMenu(e){if(this._triggerIsAriaDisabled())return;let i=this._menu;if(this._menuOpen||!i)return;this._pendingRemoval?.unsubscribe();let a=tr.get(i);tr.set(i,this),a&&a!==this&&a._closeMenu();let r=this._createOverlay(i),o=r.getConfig(),s=o.positionStrategy;this._setPosition(i,s),this._canHaveBackdrop?o.hasBackdrop=i.hasBackdrop==null?!this._triggersSubmenu():i.hasBackdrop:o.hasBackdrop=i.hasBackdrop??!1,r.hasAttached()||(r.attach(this._getPortal(i)),i.lazyContent?.attach(this.menuData)),this._closingActionsSubscription=this._menuClosingActions().subscribe(()=>this._closeMenu()),i.parentMenu=this._triggersSubmenu()?this._parentMaterialMenu:void 0,i.direction=this.dir,e&&i.focusFirstItem(this._openedBy||"program"),this._setIsMenuOpen(!0),i instanceof nr&&(i._setIsOpen(!0),i._directDescendantItems.changes.pipe(Ie(i.close)).subscribe(()=>{s.withLockedPosition(!1).reapplyLastPosition(),s.withLockedPosition(!0)}))}focus(e,i){this._focusMonitor&&e?this._focusMonitor.focusVia(this._element,e,i):this._element.nativeElement.focus(i)}_destroyMenu(e){let i=this._overlayRef,a=this._menu;!i||!this.menuOpen||(this._closingActionsSubscription.unsubscribe(),this._pendingRemoval?.unsubscribe(),a instanceof nr&&this._ownsMenu(a)?(this._pendingRemoval=a._animationDone.pipe(Pn(1)).subscribe(()=>{i.detach(),tr.has(a)||a.lazyContent?.detach()}),a._setIsOpen(!1)):(i.detach(),a?.lazyContent?.detach()),a&&this._ownsMenu(a)&&tr.delete(a),this.restoreFocus&&(e==="keydown"||!this._openedBy||!this._triggersSubmenu())&&this.focus(this._openedBy),this._openedBy=void 0,this._setIsMenuOpen(!1))}_setIsMenuOpen(e){e!==this._menuOpen&&(this._menuOpen=e,this._menuOpen?this.menuOpened.emit():this.menuClosed.emit(),this._triggersSubmenu()&&this._menuItemInstance._setHighlighted(e),this._changeDetectorRef.markForCheck())}_createOverlay(e){if(!this._overlayRef){let i=this._getOverlayConfig(e);this._subscribeToPositions(e,i.positionStrategy),this._overlayRef=Pa(this._injector,i),this._overlayRef.keydownEvents().subscribe(a=>{this._menu instanceof nr&&this._menu._handleKeydown(a)})}return this._overlayRef}_getOverlayConfig(e){return new hi({positionStrategy:_l(this._injector,this._getOverlayOrigin()).withLockedPosition().withGrowAfterOpen().withTransformOriginOn(".mat-menu-panel, .mat-mdc-menu-panel"),backdropClass:e.backdropClass||"cdk-overlay-transparent-backdrop",panelClass:e.overlayPanelClass,scrollStrategy:this._scrollStrategy(),direction:this._dir||"ltr",disableAnimations:this._animationsDisabled})}_subscribeToPositions(e,i){e.setPositionClasses&&i.positionChanges.subscribe(a=>{this._ngZone.run(()=>{let r=a.connectionPair.overlayX==="start"?"after":"before",o=a.connectionPair.overlayY==="top"?"below":"above";e.setPositionClasses(r,o)})})}_setPosition(e,i){let[a,r]=e.xPosition==="before"?["end","start"]:["start","end"],[o,s]=e.yPosition==="above"?["bottom","top"]:["top","bottom"],[l,c]=[o,s],[d,m]=[a,r],u=0;if(this._triggersSubmenu()){if(m=a=e.xPosition==="before"?"start":"end",r=d=a==="end"?"start":"end",this._parentMaterialMenu){if(this._parentInnerPadding==null){let f=this._parentMaterialMenu.items.first;this._parentInnerPadding=f?f._getHostElement().offsetTop:0}u=o==="bottom"?this._parentInnerPadding:-this._parentInnerPadding}}else e.overlapTrigger||(l=o==="top"?"bottom":"top",c=s==="top"?"bottom":"top");i.withPositions([{originX:a,originY:l,overlayX:d,overlayY:o,offsetY:u},{originX:r,originY:l,overlayX:m,overlayY:o,offsetY:u},{originX:a,originY:c,overlayX:d,overlayY:s,offsetY:-u},{originX:r,originY:c,overlayX:m,overlayY:s,offsetY:-u}])}_menuClosingActions(){let e=this._getOutsideClickStream(this._overlayRef),i=this._overlayRef.detachments(),a=this._parentMaterialMenu?this._parentMaterialMenu.closed:Ue(),r=this._parentMaterialMenu?this._parentMaterialMenu._hovered().pipe(We(o=>this._menuOpen&&o!==this._menuItemInstance)):Ue();return It(e,a,r,i)}_getPortal(e){return(!this._portal||this._portal.templateRef!==e.templateRef)&&(this._portal=new qn(e.templateRef,this._viewContainerRef)),this._portal}_ownsMenu(e){return tr.get(e)===this}_triggerIsAriaDisabled(){return Ae(this._element.nativeElement.getAttribute("aria-disabled"))}static \u0275fac=function(i){fs()};static \u0275dir=K({type:n})}return n})(),C_=(()=>{class n extends G1{_cleanupTouchstart;_hoverSubscription=bt.EMPTY;get _deprecatedMatMenuTriggerFor(){return this.menu}set _deprecatedMatMenuTriggerFor(e){this.menu=e}get menu(){return this._menu}set menu(e){this._menu=e}menuData;restoreFocus=!0;menuOpened=new Te;onMenuOpen=this.menuOpened;menuClosed=new Te;onMenuClose=this.menuClosed;constructor(){super(!0);let e=p(pt);this._cleanupTouchstart=e.listen(this._element.nativeElement,"touchstart",i=>{Bi(i)||(this._openedBy="touch")},{passive:!0})}triggersSubmenu(){return super._triggersSubmenu()}toggleMenu(){return this.menuOpen?this.closeMenu():this.openMenu()}openMenu(){this._openMenu(!0)}closeMenu(){this._closeMenu()}updatePosition(){this._overlayRef?.updatePosition()}ngAfterContentInit(){this._handleHover()}ngOnDestroy(){super.ngOnDestroy(),this._cleanupTouchstart(),this._hoverSubscription.unsubscribe()}_getOverlayOrigin(){return this._element}_getOutsideClickStream(e){return e.backdropClick()}_handleMousedown(e){Li(e)||(this._openedBy=e.button===0?"mouse":void 0,this.triggersSubmenu()&&e.preventDefault())}_handleKeydown(e){let i=e.keyCode;(i===13||i===32)&&(this._openedBy="keyboard"),this.triggersSubmenu()&&(i===39&&this.dir==="ltr"||i===37&&this.dir==="rtl")&&(this._openedBy="keyboard",this.openMenu())}_handleClick(e){this.triggersSubmenu()?(e.stopPropagation(),this.openMenu()):this.toggleMenu()}_handleHover(){this.triggersSubmenu()&&this._parentMaterialMenu&&(this._hoverSubscription=this._parentMaterialMenu._hovered().subscribe(e=>{e===this._menuItemInstance&&!e.disabled&&this._parentMaterialMenu?._panelAnimationState!=="void"&&(this._openedBy="mouse",this._openMenu(!1))}))}static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","mat-menu-trigger-for",""],["","matMenuTriggerFor",""]],hostAttrs:[1,"mat-mdc-menu-trigger"],hostVars:3,hostBindings:function(i,a){i&1&&ye("click",function(o){return a._handleClick(o)})("mousedown",function(o){return a._handleMousedown(o)})("keydown",function(o){return a._handleKeydown(o)}),i&2&&fe("aria-haspopup",a.menu?"menu":null)("aria-expanded",a.menuOpen)("aria-controls",a.menuOpen?a.menu?.panelId:null)},inputs:{_deprecatedMatMenuTriggerFor:[0,"mat-menu-trigger-for","_deprecatedMatMenuTriggerFor"],menu:[0,"matMenuTriggerFor","menu"],menuData:[0,"matMenuTriggerData","menuData"],restoreFocus:[0,"matMenuTriggerRestoreFocus","restoreFocus"]},outputs:{menuOpened:"menuOpened",onMenuOpen:"onMenuOpen",menuClosed:"menuClosed",onMenuClose:"onMenuClose"},exportAs:["matMenuTrigger"],features:[Ve]})}return n})();var Vo=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[In,Hi,me,Hn]})}return n})();var K1=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["mat-toolbar-row"]],hostAttrs:[1,"mat-toolbar-row"],exportAs:["matToolbarRow"]})}return n})(),E_=(()=>{class n{_elementRef=p(X);_platform=p(Ne);_document=p(Y);color;_toolbarRows;ngAfterViewInit(){this._platform.isBrowser&&(this._checkToolbarMixedModes(),this._toolbarRows.changes.subscribe(()=>this._checkToolbarMixedModes()))}_checkToolbarMixedModes(){this._toolbarRows.length}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=["*",[["mat-toolbar-row"]]];return T({type:n,selectors:[["mat-toolbar"]],contentQueries:function(r,o,s){if(r&1&&ot(s,K1,5),r&2){let l;H(l=U())&&(o._toolbarRows=l)}},hostAttrs:[1,"mat-toolbar"],hostVars:6,hostBindings:function(r,o){r&2&&(At(o.color?"mat-"+o.color:""),J("mat-toolbar-multiple-rows",o._toolbarRows.length>0)("mat-toolbar-single-row",o._toolbarRows.length===0))},inputs:{color:"color"},exportAs:["matToolbar"],ngContentSelectors:["*","mat-toolbar-row"],decls:2,vars:0,template:function(r,o){r&1&&(de(e),j(0),j(1,1))},styles:[`.mat-toolbar {
  background: var(--%NS%mat-toolbar-container-background-color, var(--%NS%mat-sys-surface));
  color: var(--%NS%mat-toolbar-container-text-color, var(--%NS%mat-sys-on-surface));
}
.mat-toolbar, .mat-toolbar h1, .mat-toolbar h2, .mat-toolbar h3, .mat-toolbar h4, .mat-toolbar h5, .mat-toolbar h6 {
  font-family: var(--%NS%mat-toolbar-title-text-font, var(--%NS%mat-sys-title-large-font));
  font-size: var(--%NS%mat-toolbar-title-text-size, var(--%NS%mat-sys-title-large-size));
  line-height: var(--%NS%mat-toolbar-title-text-line-height, var(--%NS%mat-sys-title-large-line-height));
  font-weight: var(--%NS%mat-toolbar-title-text-weight, var(--%NS%mat-sys-title-large-weight));
  letter-spacing: var(--%NS%mat-toolbar-title-text-tracking, var(--%NS%mat-sys-title-large-tracking));
  margin: 0;
}
@media (forced-colors: active) {
  .mat-toolbar {
    outline: solid 1px;
  }
}
.mat-toolbar .mat-form-field-underline,
.mat-toolbar .mat-form-field-ripple,
.mat-toolbar .mat-focused .mat-form-field-ripple {
  background-color: currentColor;
}
.mat-toolbar .mat-form-field-label,
.mat-toolbar .mat-focused .mat-form-field-label,
.mat-toolbar .mat-select-value,
.mat-toolbar .mat-select-arrow,
.mat-toolbar .mat-form-field.mat-focused .mat-select-arrow {
  color: inherit;
}
.mat-toolbar .mat-input-element {
  caret-color: currentColor;
}
.mat-toolbar .mat-mdc-button-base.mat-mdc-button-base.mat-unthemed {
  --%NS%mat-button-text-label-text-color: var(--%NS%mat-toolbar-container-text-color, var(--%NS%mat-sys-on-surface));
  --%NS%mat-button-outlined-label-text-color: var(--%NS%mat-toolbar-container-text-color, var(--%NS%mat-sys-on-surface));
}

.mat-toolbar-row, .mat-toolbar-single-row {
  display: flex;
  box-sizing: border-box;
  padding: 0 16px;
  width: 100%;
  flex-direction: row;
  align-items: center;
  white-space: nowrap;
  height: var(--%NS%mat-toolbar-standard-height, 64px);
}
@media (max-width: 599px) {
  .mat-toolbar-row, .mat-toolbar-single-row {
    height: var(--%NS%mat-toolbar-mobile-height, 56px);
  }
}

.mat-toolbar-multiple-rows {
  display: flex;
  box-sizing: border-box;
  flex-direction: column;
  width: 100%;
  min-height: var(--%NS%mat-toolbar-standard-height, 64px);
}
@media (max-width: 599px) {
  .mat-toolbar-multiple-rows {
    min-height: var(--%NS%mat-toolbar-mobile-height, 56px);
  }
}
`],encapsulation:2})})()}return n})();var $o=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[me]})}return n})();var X1=new I("MAT_CARD_CONFIG"),ar=(()=>{class n{appearance;constructor(){let e=p(X1,{optional:!0});this.appearance=e?.appearance||"raised"}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){return T({type:n,selectors:[["mat-card"]],hostAttrs:[1,"mat-mdc-card","mdc-card"],hostVars:8,hostBindings:function(a,r){a&2&&J("mat-mdc-card-outlined",r.appearance==="outlined")("mdc-card--outlined",r.appearance==="outlined")("mat-mdc-card-filled",r.appearance==="filled")("mdc-card--filled",r.appearance==="filled")},inputs:{appearance:"appearance"},exportAs:["matCard"],ngContentSelectors:["*"],decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},styles:[`.mat-mdc-card {
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  position: relative;
  border-style: solid;
  border-width: 0;
  background-color: var(--%NS%mat-card-elevated-container-color, var(--%NS%mat-sys-surface-container-low));
  border-color: var(--%NS%mat-card-elevated-container-color, var(--%NS%mat-sys-surface-container-low));
  border-radius: var(--%NS%mat-card-elevated-container-shape, var(--%NS%mat-sys-corner-medium));
  box-shadow: var(--%NS%mat-card-elevated-container-elevation, var(--%NS%mat-sys-level1));
}
.mat-mdc-card::after {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  border: solid 1px transparent;
  content: "";
  display: block;
  pointer-events: none;
  box-sizing: border-box;
  border-radius: var(--%NS%mat-card-elevated-container-shape, var(--%NS%mat-sys-corner-medium));
}

.mat-mdc-card-outlined {
  background-color: var(--%NS%mat-card-outlined-container-color, var(--%NS%mat-sys-surface));
  border-radius: var(--%NS%mat-card-outlined-container-shape, var(--%NS%mat-sys-corner-medium));
  border-width: var(--%NS%mat-card-outlined-outline-width, 1px);
  border-color: var(--%NS%mat-card-outlined-outline-color, var(--%NS%mat-sys-outline-variant));
  box-shadow: var(--%NS%mat-card-outlined-container-elevation, var(--%NS%mat-sys-level0));
}
.mat-mdc-card-outlined::after {
  border: none;
}

.mat-mdc-card-filled {
  background-color: var(--%NS%mat-card-filled-container-color, var(--%NS%mat-sys-surface-container-highest));
  border-radius: var(--%NS%mat-card-filled-container-shape, var(--%NS%mat-sys-corner-medium));
  box-shadow: var(--%NS%mat-card-filled-container-elevation, var(--%NS%mat-sys-level0));
}

.mdc-card__media {
  position: relative;
  box-sizing: border-box;
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
}
.mdc-card__media::before {
  display: block;
  content: "";
}
.mdc-card__media:first-child {
  border-top-left-radius: inherit;
  border-top-right-radius: inherit;
}
.mdc-card__media:last-child {
  border-bottom-left-radius: inherit;
  border-bottom-right-radius: inherit;
}

.mat-mdc-card-actions {
  display: flex;
  flex-direction: row;
  align-items: center;
  box-sizing: border-box;
  min-height: 52px;
  padding: 8px;
}

.mat-mdc-card-title {
  font-family: var(--%NS%mat-card-title-text-font, var(--%NS%mat-sys-title-large-font));
  line-height: var(--%NS%mat-card-title-text-line-height, var(--%NS%mat-sys-title-large-line-height));
  font-size: var(--%NS%mat-card-title-text-size, var(--%NS%mat-sys-title-large-size));
  letter-spacing: var(--%NS%mat-card-title-text-tracking, var(--%NS%mat-sys-title-large-tracking));
  font-weight: var(--%NS%mat-card-title-text-weight, var(--%NS%mat-sys-title-large-weight));
}

.mat-mdc-card-subtitle {
  color: var(--%NS%mat-card-subtitle-text-color, var(--%NS%mat-sys-on-surface));
  font-family: var(--%NS%mat-card-subtitle-text-font, var(--%NS%mat-sys-title-medium-font));
  line-height: var(--%NS%mat-card-subtitle-text-line-height, var(--%NS%mat-sys-title-medium-line-height));
  font-size: var(--%NS%mat-card-subtitle-text-size, var(--%NS%mat-sys-title-medium-size));
  letter-spacing: var(--%NS%mat-card-subtitle-text-tracking, var(--%NS%mat-sys-title-medium-tracking));
  font-weight: var(--%NS%mat-card-subtitle-text-weight, var(--%NS%mat-sys-title-medium-weight));
}

.mat-mdc-card-title,
.mat-mdc-card-subtitle {
  display: block;
  margin: 0;
}
.mat-mdc-card-avatar ~ .mat-mdc-card-header-text .mat-mdc-card-title,
.mat-mdc-card-avatar ~ .mat-mdc-card-header-text .mat-mdc-card-subtitle {
  padding: 16px 16px 0;
}

.mat-mdc-card-header {
  display: flex;
  padding: 16px 16px 0;
}

.mat-mdc-card-content {
  display: block;
  padding: 0 16px;
}
.mat-mdc-card-content:first-child {
  padding-top: 16px;
}
.mat-mdc-card-content:last-child {
  padding-bottom: 16px;
}

.mat-mdc-card-title-group {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.mat-mdc-card-avatar {
  height: 40px;
  width: 40px;
  border-radius: 50%;
  flex-shrink: 0;
  margin-bottom: 16px;
  object-fit: cover;
}
.mat-mdc-card-avatar ~ .mat-mdc-card-header-text .mat-mdc-card-subtitle,
.mat-mdc-card-avatar ~ .mat-mdc-card-header-text .mat-mdc-card-title {
  line-height: normal;
}

.mat-mdc-card-sm-image {
  width: 80px;
  height: 80px;
}

.mat-mdc-card-md-image {
  width: 112px;
  height: 112px;
}

.mat-mdc-card-lg-image {
  width: 152px;
  height: 152px;
}

.mat-mdc-card-xl-image {
  width: 240px;
  height: 240px;
}

.mat-mdc-card-subtitle ~ .mat-mdc-card-title,
.mat-mdc-card-title ~ .mat-mdc-card-subtitle,
.mat-mdc-card-header .mat-mdc-card-header-text .mat-mdc-card-title,
.mat-mdc-card-header .mat-mdc-card-header-text .mat-mdc-card-subtitle,
.mat-mdc-card-title-group .mat-mdc-card-title,
.mat-mdc-card-title-group .mat-mdc-card-subtitle {
  padding-top: 0;
}

.mat-mdc-card-content > :last-child:not(.mat-mdc-card-footer) {
  margin-bottom: 0;
}

.mat-mdc-card-actions-align-end {
  justify-content: flex-end;
}
`],encapsulation:2})})()}return n})(),rr=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["mat-card-title"],["","mat-card-title",""],["","matCardTitle",""]],hostAttrs:[1,"mat-mdc-card-title"]})}return n})();var or=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["mat-card-content"]],hostAttrs:[1,"mat-mdc-card-content"]})}return n})();var sr=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=[[["","mat-card-avatar",""],["","matCardAvatar",""]],[["mat-card-title"],["mat-card-subtitle"],["","mat-card-title",""],["","mat-card-subtitle",""],["","matCardTitle",""],["","matCardSubtitle",""]],"*"];return T({type:n,selectors:[["mat-card-header"]],hostAttrs:[1,"mat-mdc-card-header"],ngContentSelectors:["[mat-card-avatar], [matCardAvatar]",`mat-card-title, mat-card-subtitle,
      [mat-card-title], [mat-card-subtitle],
      [matCardTitle], [matCardSubtitle]`,"*"],decls:4,vars:0,consts:[[1,"mat-mdc-card-header-text"]],template:function(r,o){r&1&&(de(e),j(0),Ze(1,"div",0),j(2,1),Je(),j(3,2))},encapsulation:2})})()}return n})();var Zn=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[me]})}return n})();var D_=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({})}return n})();var Zp=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[D_,Ra,me]})}return n})();var Qp=class{_box;_destroyed=new z;_resizeSubject=new z;_resizeObserver;_elementObservables=new Map;constructor(t){this._box=t,typeof ResizeObserver<"u"&&(this._resizeObserver=new ResizeObserver(e=>this._resizeSubject.next(e)))}observe(t){return this._elementObservables.has(t)||this._elementObservables.set(t,new Nt(e=>{let i=this._resizeSubject.subscribe(e);return this._resizeObserver?.observe(t,{box:this._box}),()=>{this._resizeObserver?.unobserve(t),i.unsubscribe(),this._elementObservables.delete(t)}}).pipe(We(e=>e.some(i=>i.target===t)),Fn({bufferSize:1,refCount:!0}),Ie(this._destroyed))),this._elementObservables.get(t)}destroy(){this._destroyed.next(),this._destroyed.complete(),this._resizeSubject.complete(),this._elementObservables.clear()}},M_=(()=>{class n{_cleanupErrorListener;_observers=new Map;_ngZone=p(W);constructor(){typeof ResizeObserver<"u"}ngOnDestroy(){for(let[,e]of this._observers)e.destroy();this._observers.clear(),this._cleanupErrorListener?.()}observe(e,i){let a=i?.box||"content-box";return this._observers.has(a)||this._observers.set(a,new Qp(a)),this._observers.get(a).observe(e)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var Jp=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[me]})}return n})();var zo=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[me]})}return n})();var eu=class{_document;_textarea;constructor(t,e){this._document=e;let i=this._textarea=this._document.createElement("textarea"),a=i.style;a.position="fixed",a.top=a.opacity="0",a.left="-999em",i.setAttribute("aria-hidden","true"),i.value=t,i.readOnly=!0,(this._document.fullscreenElement||this._document.body).appendChild(i)}copy(){let t=this._textarea,e=!1;try{if(t){let i=this._document.activeElement;t.select(),t.setSelectionRange(0,t.value.length),e=this._document.execCommand("copy"),i&&i.focus()}}catch{}return e}destroy(){let t=this._textarea;t&&(t.remove(),this._textarea=void 0)}},Z1=(()=>{class n{_document=p(Y);copy(e){let i=this.beginCopy(e),a=i.copy();return i.destroy(),a}beginCopy(e){return new eu(e,this._document)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})(),Q1=new I("CDK_COPY_TO_CLIPBOARD_CONFIG"),N_=(()=>{class n{_clipboard=p(Z1);_ngZone=p(W);text="";attempts=1;copied=new Te;_pending=new Set;_destroyed=!1;_currentTimeout;constructor(){let e=p(Q1,{optional:!0});e&&e.attempts!=null&&(this.attempts=e.attempts)}copy(e=this.attempts){if(e=Math.min(e,50),e>1){let i=e,a=this._clipboard.beginCopy(this.text);this._pending.add(a);let r=()=>{let o=a.copy();!o&&--i&&!this._destroyed?this._currentTimeout=this._ngZone.runOutsideAngular(()=>setTimeout(r,1)):(this._currentTimeout=null,this._pending.delete(a),a.destroy(),this.copied.emit(o))};r()}else this.copied.emit(this._clipboard.copy(this.text))}ngOnDestroy(){this._currentTimeout&&clearTimeout(this._currentTimeout),this._pending.forEach(e=>e.destroy()),this._pending.clear(),this._destroyed=!0}static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","cdkCopyToClipboard",""]],hostBindings:function(i,a){i&1&&ye("click",function(){return a.copy()})},inputs:{text:[0,"cdkCopyToClipboard","text"],attempts:[0,"cdkCopyToClipboardAttempts","attempts"]},outputs:{copied:"cdkCopyToClipboardCopied"}})}return n})(),Ho=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({})}return n})();var eM=new I("",{factory:()=>I_}),I_="always";var tM=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({})}return n})();var A_=(()=>{class n{static withConfig(e){return{ngModule:n,providers:[{provide:eM,useValue:e.callSetDisabledState??I_}]}}static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[tM]})}return n})();var O_=Symbol("FIELD_TREE");var R_=Symbol("IS_ASYNC_VALIDATION_RESOURCE"),T_=class{reducer;create;brand;[R_];constructor(t,e){this.reducer=t,this.create=e}};function Uo(n){return typeof n=="function"&&n[O_]===!0}var tu=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["mat-label"]]})}return n})(),nM=new I("MatError");var nu=(()=>{class n{align="start";id=p(St).getId("mat-mdc-hint-");static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["mat-hint"]],hostAttrs:[1,"mat-mdc-form-field-hint","mat-mdc-form-field-bottom-align"],hostVars:4,hostBindings:function(i,a){i&2&&(oi("id",a.id),fe("align",null),J("mat-mdc-form-field-hint-end",a.align==="end"))},inputs:{align:"align",id:"id"}})}return n})(),iM=new I("MatPrefix");var aM=new I("MatSuffix");var $_=new I("FloatingLabelParent"),P_=(()=>{class n{_elementRef=p(X);get floating(){return this._floating}set floating(e){this._floating=e,this.monitorResize&&this._handleResize()}_floating=!1;get monitorResize(){return this._monitorResize}set monitorResize(e){this._monitorResize=e,this._monitorResize?this._subscribeToResize():this._resizeSubscription.unsubscribe()}_monitorResize=!1;_resizeObserver=p(M_);_ngZone=p(W);_parent=p($_);_resizeSubscription=new bt;ngOnDestroy(){this._resizeSubscription.unsubscribe()}getWidth(){return rM(this._elementRef.nativeElement)}get element(){return this._elementRef.nativeElement}_handleResize(){setTimeout(()=>this._parent._handleLabelResized())}_subscribeToResize(){this._resizeSubscription.unsubscribe(),this._ngZone.runOutsideAngular(()=>{this._resizeSubscription=this._resizeObserver.observe(this._elementRef.nativeElement,{box:"border-box"}).subscribe(()=>this._handleResize())})}static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["label","matFormFieldFloatingLabel",""]],hostAttrs:[1,"mdc-floating-label","mat-mdc-floating-label"],hostVars:2,hostBindings:function(i,a){i&2&&J("mdc-floating-label--float-above",a.floating)},inputs:{floating:"floating",monitorResize:"monitorResize"}})}return n})();function rM(n){let t=n;if(t.offsetParent!==null)return t.scrollWidth;let e=t.cloneNode(!0);e.style.setProperty("position","absolute"),e.style.setProperty("transform","translate(-9999px, -9999px)"),document.documentElement.appendChild(e);let i=e.scrollWidth;return e.remove(),i}var F_="mdc-line-ripple--active",cc="mdc-line-ripple--deactivating",L_=(()=>{class n{_elementRef=p(X);_cleanupTransitionEnd;constructor(){let e=p(W),i=p(pt);e.runOutsideAngular(()=>{this._cleanupTransitionEnd=i.listen(this._elementRef.nativeElement,"transitionend",this._handleTransitionEnd)})}activate(){let e=this._elementRef.nativeElement.classList;e.remove(cc),e.add(F_)}deactivate(){this._elementRef.nativeElement.classList.add(cc)}_handleTransitionEnd=e=>{let i=this._elementRef.nativeElement.classList,a=i.contains(cc);e.propertyName==="opacity"&&a&&i.remove(F_,cc)};ngOnDestroy(){this._cleanupTransitionEnd()}static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["div","matFormFieldLineRipple",""]],hostAttrs:[1,"mdc-line-ripple"]})}return n})(),B_=(()=>{class n{_elementRef=p(X);_ngZone=p(W);open=!1;_notch;ngAfterViewInit(){let e=this._elementRef.nativeElement,i=e.querySelector(".mdc-floating-label");i?(e.classList.add("mdc-notched-outline--upgraded"),typeof requestAnimationFrame=="function"&&(i.style.transitionDuration="0s",this._ngZone.runOutsideAngular(()=>{requestAnimationFrame(()=>i.style.transitionDuration="")}))):e.classList.add("mdc-notched-outline--no-label")}_setNotchWidth(e){let i=this._notch.nativeElement;!this.open||!e?i.style.width="":i.style.width=`calc(${e}px * var(--mat-mdc-form-field-floating-label-scale, 0.75) + 9px)`}_setMaxWidth(e){this._notch.nativeElement.style.setProperty("--mat-form-field-notch-max-width",`calc(100% - ${e}px)`)}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=["notch"];return T({type:n,selectors:[["div","matFormFieldNotchedOutline",""]],viewQuery:function(r,o){if(r&1&&qe(e,5),r&2){let s;H(s=U())&&(o._notch=s.first)}},hostAttrs:[1,"mdc-notched-outline"],hostVars:2,hostBindings:function(r,o){r&2&&J("mdc-notched-outline--notched",o.open)},inputs:{open:[0,"matFormFieldNotchedOutlineOpen","open"]},ngContentSelectors:["*"],decls:5,vars:0,consts:[["notch",""],[1,"mat-mdc-notch-piece","mdc-notched-outline__leading"],[1,"mat-mdc-notch-piece","mdc-notched-outline__notch"],[1,"mat-mdc-notch-piece","mdc-notched-outline__trailing"]],template:function(r,o){r&1&&(de(),ln(0,"div",1),Ze(1,"div",2,0),j(3),Je(),ln(4,"div",3))},encapsulation:2})})()}return n})(),oM=(()=>{class n{id;ngField=null;ngControl=null;focused=!1;empty=!1;shouldLabelFloat=!1;required=!1;disabled=!1;errorState=!1;controlType;autofilled;userAriaDescribedBy;disableAutomaticLabeling;describedByIds;stateChanges=null;value;static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n})}return n})();var sM=new I("MatFormField"),lM=new I("MAT_FORM_FIELD_DEFAULT_OPTIONS"),j_="fill",cM="auto",V_="fixed",dM="translateY(-50%)",z_=(()=>{class n{_elementRef=p(X);_changeDetectorRef=p(et);_platform=p(Ne);_idGenerator=p(St);_ngZone=p(W);_defaults=p(lM,{optional:!0});_currentDirection;_unwrapMaybeSignal(e){return ii(e)?e():e}_textField;_iconPrefixContainer;_textPrefixContainer;_iconSuffixContainer;_textSuffixContainer;_floatingLabel;_notchedOutline;_lineRipple;_iconPrefixContainerSignal=Tr("iconPrefixContainer");_textPrefixContainerSignal=Tr("textPrefixContainer");_iconSuffixContainerSignal=Tr("iconSuffixContainer");_textSuffixContainerSignal=Tr("textSuffixContainer");_prefixSuffixContainers=cn(()=>[this._iconPrefixContainerSignal(),this._textPrefixContainerSignal(),this._iconSuffixContainerSignal(),this._textSuffixContainerSignal()].map(e=>e?.nativeElement).filter(e=>e!==void 0));_formFieldControl;_prefixChildren;_suffixChildren;_errorChildren;_hintChildren;_labelChild=_h(tu);get hideRequiredMarker(){return this._hideRequiredMarker}set hideRequiredMarker(e){this._hideRequiredMarker=Rt(e)}_hideRequiredMarker=!1;color="primary";get floatLabel(){return this._floatLabel||this._defaults?.floatLabel||cM}set floatLabel(e){e!==this._floatLabel&&(this._floatLabel=e,this._changeDetectorRef.markForCheck())}_floatLabel;get appearance(){return this._appearanceSignal()}set appearance(e){let i=e||this._defaults?.appearance||j_;this._appearanceSignal.set(i)}_appearanceSignal=ne(j_);get subscriptSizing(){return this._subscriptSizing||this._defaults?.subscriptSizing||V_}set subscriptSizing(e){this._subscriptSizing=e||this._defaults?.subscriptSizing||V_}_subscriptSizing=null;get hintLabel(){return this._hintLabel}set hintLabel(e){this._hintLabel=e,this._processHints()}_hintLabel="";_hasIconPrefix=!1;_hasTextPrefix=!1;_hasIconSuffix=!1;_hasTextSuffix=!1;_labelId=this._idGenerator.getId("mat-mdc-form-field-label-");_hintLabelId=this._idGenerator.getId("mat-mdc-hint-");_describedByIds;get _control(){return this._explicitFormFieldControl||this._formFieldControl}set _control(e){this._explicitFormFieldControl=e}_destroyed=new z;_isFocused=null;_explicitFormFieldControl;_previousControl=null;_previousControlValidatorFn=null;_stateChanges;_valueChanges;_describedByChanges;_outlineLabelOffsetResizeObserver=null;_animationsDisabled=tt();constructor(){let e=this._defaults,i=p(Tt);e&&(e.appearance&&(this.appearance=e.appearance),this._hideRequiredMarker=!!e?.hideRequiredMarker,e.color&&(this.color=e.color)),Mi(()=>this._currentDirection=i.valueSignal()),this._syncOutlineLabelOffset()}ngAfterViewInit(){this._updateFocusState(),this._animationsDisabled||this._ngZone.runOutsideAngular(()=>{setTimeout(()=>{this._elementRef.nativeElement.classList.add("mat-form-field-animations-enabled")},300)}),this._changeDetectorRef.detectChanges()}ngAfterContentInit(){this._assertFormFieldControl(),this._initializeSubscript(),this._initializePrefixAndSuffix()}ngAfterContentChecked(){this._assertFormFieldControl(),this._control!==this._previousControl&&(this._initializeControl(this._previousControl),this._control.ngControl&&this._control.ngControl.control&&(this._previousControlValidatorFn=Uo(this._control.ngField)?null:this._control.ngControl.control.validator),this._previousControl=this._control,this._changeDetectorRef.markForCheck()),this._control.ngControl&&this._control.ngControl.control&&!Uo(this._control.ngField)&&this._control.ngControl.control.validator!==this._previousControlValidatorFn&&this._changeDetectorRef.markForCheck()}ngOnDestroy(){this._outlineLabelOffsetResizeObserver?.disconnect(),this._stateChanges?.unsubscribe(),this._valueChanges?.unsubscribe(),this._describedByChanges?.unsubscribe(),this._destroyed.next(),this._destroyed.complete()}getLabelId=cn(()=>this._hasFloatingLabel()?this._labelId:null);getConnectedOverlayOrigin(){return this._textField||this._elementRef}_animateAndLockLabel(){this._hasFloatingLabel()&&(this.floatLabel="always")}_initializeControl(e){let i=this._control,a="mat-mdc-form-field-type-";e&&this._elementRef.nativeElement.classList.remove(a+e.controlType),i.controlType&&this._elementRef.nativeElement.classList.add(a+i.controlType),this._stateChanges?.unsubscribe(),this._stateChanges=i.stateChanges?.subscribe(()=>{this._updateFocusState(),this._changeDetectorRef.markForCheck()}),this._describedByChanges?.unsubscribe(),this._describedByChanges=i.stateChanges?.pipe(vt([void 0,void 0]),De(()=>[this._unwrapMaybeSignal(i.errorState),i.userAriaDescribedBy]),jc(),We(([[r,o],[s,l]])=>r!==s||o!==l)).subscribe(()=>this._syncDescribedByIds()),this._valueChanges?.unsubscribe(),i.ngControl&&i.ngControl.valueChanges&&!Uo(i.ngField)&&(this._valueChanges=i.ngControl.valueChanges.pipe(Ie(this._destroyed)).subscribe(()=>this._changeDetectorRef.markForCheck()))}_checkPrefixAndSuffixTypes(){this._hasIconPrefix=!!this._prefixChildren.find(e=>!e._isText),this._hasTextPrefix=!!this._prefixChildren.find(e=>e._isText),this._hasIconSuffix=!!this._suffixChildren.find(e=>!e._isText),this._hasTextSuffix=!!this._suffixChildren.find(e=>e._isText)}_initializePrefixAndSuffix(){this._checkPrefixAndSuffixTypes(),It(this._prefixChildren.changes,this._suffixChildren.changes).subscribe(()=>{this._checkPrefixAndSuffixTypes(),this._changeDetectorRef.markForCheck()})}_initializeSubscript(){this._hintChildren.changes.subscribe(()=>{this._processHints(),this._changeDetectorRef.markForCheck()}),this._errorChildren.changes.subscribe(()=>{this._syncDescribedByIds(),this._changeDetectorRef.markForCheck()}),this._validateHints(),this._syncDescribedByIds()}_assertFormFieldControl(){this._control}_updateFocusState(){let e=this._unwrapMaybeSignal(this._control.focused);e&&!this._isFocused?(this._isFocused=!0,this._lineRipple?.activate()):!e&&(this._isFocused||this._isFocused===null)&&(this._isFocused=!1,this._lineRipple?.deactivate()),this._elementRef.nativeElement.classList.toggle("mat-focused",e),this._textField?.nativeElement.classList.toggle("mdc-text-field--focused",e)}_syncOutlineLabelOffset(){wh({earlyRead:()=>{if(this._appearanceSignal()!=="outline")return this._outlineLabelOffsetResizeObserver?.disconnect(),null;if(globalThis.ResizeObserver){this._outlineLabelOffsetResizeObserver||=new globalThis.ResizeObserver(()=>{this._writeOutlinedLabelStyles(this._getOutlinedLabelOffset())});for(let e of this._prefixSuffixContainers())this._outlineLabelOffsetResizeObserver.observe(e,{box:"border-box"})}return this._getOutlinedLabelOffset()},write:e=>this._writeOutlinedLabelStyles(e())})}_shouldAlwaysFloat(){return this.floatLabel==="always"}_hasOutline(){return this.appearance==="outline"}_forceDisplayInfixLabel(){return!this._platform.isBrowser&&this._prefixChildren.length&&!this._shouldLabelFloat()}_hasFloatingLabel=cn(()=>!!this._labelChild());_shouldLabelFloat(){return this._hasFloatingLabel()?this._shouldAlwaysFloat()||this._unwrapMaybeSignal(this._control.shouldLabelFloat):!1}_shouldForward(e){let i=this._control?.ngField||this._control?.ngControl;if(!i)return!1;if(Uo(i)){let a=i();return e==="valid"?a.valid():e==="dirty"?a.dirty():e==="touched"?a.touched():e==="pending"?a.pending():e==="untouched"?!a.touched():e==="pristine"?!a.dirty():e==="invalid"?!a.valid():!1}else{let a=i;return e==="valid"?a.valid:e==="dirty"?a.dirty:e==="touched"?a.touched:e==="pending"?a.pending:e==="untouched"?a.untouched:e==="pristine"?a.pristine:e==="invalid"?a.invalid:!1}}_getSubscriptMessageType(){return this._errorChildren&&this._errorChildren.length>0&&this._unwrapMaybeSignal(this._control.errorState)?"error":"hint"}_handleLabelResized(){this._refreshOutlineNotchWidth()}_refreshOutlineNotchWidth(){!this._hasOutline()||!this._floatingLabel||!this._shouldLabelFloat()?this._notchedOutline?._setNotchWidth(0):this._notchedOutline?._setNotchWidth(this._floatingLabel.getWidth())}_processHints(){this._validateHints(),this._syncDescribedByIds()}_validateHints(){this._hintChildren}_syncDescribedByIds(){if(this._control){let e=[];if(this._control.userAriaDescribedBy&&typeof this._control.userAriaDescribedBy=="string"&&e.push(...this._control.userAriaDescribedBy.split(" ")),this._getSubscriptMessageType()==="hint"){let r=this._hintChildren?this._hintChildren.find(s=>s.align==="start"):null,o=this._hintChildren?this._hintChildren.find(s=>s.align==="end"):null;r?e.push(r.id):this._hintLabel&&e.push(this._hintLabelId),o&&e.push(o.id)}else this._errorChildren&&e.push(...this._errorChildren.map(r=>r.id));let i=this._control.describedByIds,a;if(i){let r=this._describedByIds||e;a=e.concat(i.filter(o=>o&&!r.includes(o)))}else a=e;this._control.setDescribedByIds(a),this._describedByIds=e}}_getOutlinedLabelOffset(){if(!this._hasOutline()||!this._floatingLabel)return null;if(!this._iconPrefixContainer&&!this._textPrefixContainer)return["",null];if(!this._isAttachedToDom())return null;let e=this._iconPrefixContainer?.nativeElement,i=this._textPrefixContainer?.nativeElement,a=this._iconSuffixContainer?.nativeElement,r=this._textSuffixContainer?.nativeElement,o=e?.getBoundingClientRect().width??0,s=i?.getBoundingClientRect().width??0,l=a?.getBoundingClientRect().width??0,c=r?.getBoundingClientRect().width??0,d=this._currentDirection==="rtl"?"-1":"1",m=`${o+s}px`,f=`calc(${d} * (${m} + var(--mat-mdc-form-field-label-offset-x, 0px)))`,g=`var(--mat-mdc-form-field-label-transform, ${dM} translateX(${f}))`,b=o+s+l+c;return[g,b]}_writeOutlinedLabelStyles(e){if(e!==null){let[i,a]=e;this._floatingLabel&&(this._floatingLabel.element.style.transform=i),a!==null&&this._notchedOutline?._setMaxWidth(a)}}_isAttachedToDom(){let e=this._elementRef.nativeElement;if(e.getRootNode){let i=e.getRootNode();return i&&i!==e}return document.documentElement.contains(e)}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=["iconPrefixContainer"],i=["textPrefixContainer"],a=["iconSuffixContainer"],r=["textSuffixContainer"],o=["textField"],s=["*",[["mat-label"]],[["","matPrefix",""],["","matIconPrefix",""]],[["","matTextPrefix",""]],[["","matTextSuffix",""]],[["","matSuffix",""],["","matIconSuffix",""]],[["mat-error"],["","matError",""]],[["mat-hint",3,"align","end"]],[["mat-hint","align","end"]]],l=["*","mat-label","[matPrefix], [matIconPrefix]","[matTextPrefix]","[matTextSuffix]","[matSuffix], [matIconSuffix]","mat-error, [matError]","mat-hint:not([align='end'])","mat-hint[align='end']"];function c(q,re){q&1&&P(0,"span",21)}function d(q,re){if(q&1&&(y(0,"label",20),j(1,1),E(2,c,1,0,"span",21),v()),q&2){let k=M(2);R("floating",k._shouldLabelFloat())("monitorResize",k._hasOutline())("id",k._labelId),fe("for",k._control.disableAutomaticLabeling?null:k._control.id),h(2),D(!k.hideRequiredMarker&&k._unwrapMaybeSignal(k._control.required)?2:-1)}}function m(q,re){if(q&1&&E(0,d,3,5,"label",20),q&2){let k=M();D(k._hasFloatingLabel()?0:-1)}}function u(q,re){q&1&&P(0,"div",7)}function f(q,re){}function g(q,re){if(q&1&&Wt(0,f,0,0,"ng-template",13),q&2){M(2);let k=Kt(1);R("ngTemplateOutlet",k)}}function b(q,re){if(q&1&&(y(0,"div",9),E(1,g,1,1,null,13),v()),q&2){let k=M();R("matFormFieldNotchedOutlineOpen",k._shouldLabelFloat()),h(),D(k._forceDisplayInfixLabel()?-1:1)}}function _(q,re){q&1&&(y(0,"div",10,2),j(2,2),v())}function x(q,re){q&1&&(y(0,"div",11,3),j(2,3),v())}function w(q,re){}function C(q,re){if(q&1&&Wt(0,w,0,0,"ng-template",13),q&2){M();let k=Kt(1);R("ngTemplateOutlet",k)}}function F(q,re){q&1&&(y(0,"div",14,4),j(2,4),v())}function A(q,re){q&1&&(y(0,"div",15,5),j(2,5),v())}function Q(q,re){q&1&&P(0,"div",16)}function Re(q,re){q&1&&(y(0,"div",18),j(1,6),v())}function He(q,re){if(q&1&&(y(0,"mat-hint",22),S(1),v()),q&2){let k=M(2);R("id",k._hintLabelId),h(),Ge(k.hintLabel)}}function Be(q,re){if(q&1&&(y(0,"div",19),E(1,He,2,2,"mat-hint",22),j(2,7),P(3,"div",23),j(4,8),v()),q&2){let k=M();h(),D(k.hintLabel?1:-1)}}return T({type:n,selectors:[["mat-form-field"]],contentQueries:function(re,k,le){if(re&1&&(ph(le,k._labelChild,tu,5),ot(le,oM,5)(le,iM,5)(le,aM,5)(le,nM,5)(le,nu,5)),re&2){Hc();let je;H(je=U())&&(k._formFieldControl=je.first),H(je=U())&&(k._prefixChildren=je),H(je=U())&&(k._suffixChildren=je),H(je=U())&&(k._errorChildren=je),H(je=U())&&(k._hintChildren=je)}},viewQuery:function(re,k){if(re&1&&(uh(k._iconPrefixContainerSignal,e,5)(k._textPrefixContainerSignal,i,5)(k._iconSuffixContainerSignal,a,5)(k._textSuffixContainerSignal,r,5),qe(o,5)(e,5)(i,5)(a,5)(r,5)(P_,5)(B_,5)(L_,5)),re&2){Hc(4);let le;H(le=U())&&(k._textField=le.first),H(le=U())&&(k._iconPrefixContainer=le.first),H(le=U())&&(k._textPrefixContainer=le.first),H(le=U())&&(k._iconSuffixContainer=le.first),H(le=U())&&(k._textSuffixContainer=le.first),H(le=U())&&(k._floatingLabel=le.first),H(le=U())&&(k._notchedOutline=le.first),H(le=U())&&(k._lineRipple=le.first)}},hostAttrs:[1,"mat-mdc-form-field"],hostVars:38,hostBindings:function(re,k){re&2&&J("mat-mdc-form-field-label-always-float",k._shouldAlwaysFloat())("mat-mdc-form-field-has-icon-prefix",k._hasIconPrefix)("mat-mdc-form-field-has-icon-suffix",k._hasIconSuffix)("mat-form-field-invalid",k._unwrapMaybeSignal(k._control.errorState))("mat-form-field-disabled",k._unwrapMaybeSignal(k._control.disabled))("mat-form-field-autofilled",k._unwrapMaybeSignal(k._control.autofilled))("mat-form-field-appearance-fill",k.appearance=="fill")("mat-form-field-appearance-outline",k.appearance=="outline")("mat-form-field-hide-placeholder",k._hasFloatingLabel()&&!k._shouldLabelFloat())("mat-primary",k.color!=="accent"&&k.color!=="warn")("mat-accent",k.color==="accent")("mat-warn",k.color==="warn")("ng-untouched",k._shouldForward("untouched"))("ng-touched",k._shouldForward("touched"))("ng-pristine",k._shouldForward("pristine"))("ng-dirty",k._shouldForward("dirty"))("ng-valid",k._shouldForward("valid"))("ng-invalid",k._shouldForward("invalid"))("ng-pending",k._shouldForward("pending"))},inputs:{hideRequiredMarker:"hideRequiredMarker",color:"color",floatLabel:"floatLabel",appearance:"appearance",subscriptSizing:"subscriptSizing",hintLabel:"hintLabel"},exportAs:["matFormField"],features:[Qe([{provide:sM,useExisting:n},{provide:$_,useExisting:n}])],ngContentSelectors:l,decls:18,vars:21,consts:[["labelTemplate",""],["textField",""],["iconPrefixContainer",""],["textPrefixContainer",""],["textSuffixContainer",""],["iconSuffixContainer",""],[1,"mat-mdc-text-field-wrapper","mdc-text-field",3,"click"],[1,"mat-mdc-form-field-focus-overlay"],[1,"mat-mdc-form-field-flex"],["matFormFieldNotchedOutline","",3,"matFormFieldNotchedOutlineOpen"],[1,"mat-mdc-form-field-icon-prefix"],[1,"mat-mdc-form-field-text-prefix"],[1,"mat-mdc-form-field-infix"],[3,"ngTemplateOutlet"],[1,"mat-mdc-form-field-text-suffix"],[1,"mat-mdc-form-field-icon-suffix"],["matFormFieldLineRipple",""],["aria-atomic","true","aria-live","polite",1,"mat-mdc-form-field-subscript-wrapper","mat-mdc-form-field-bottom-align"],[1,"mat-mdc-form-field-error-wrapper"],[1,"mat-mdc-form-field-hint-wrapper"],["matFormFieldFloatingLabel","",3,"floating","monitorResize","id"],["aria-hidden","true",1,"mat-mdc-form-field-required-marker","mdc-floating-label--required"],[3,"id"],[1,"mat-mdc-form-field-hint-spacer"]],template:function(re,k){if(re&1&&(de(s),Wt(0,m,1,1,"ng-template",null,0,Ar),y(2,"div",6,1),ye("click",function(je){return k._control.onContainerClick(je)}),E(4,u,1,0,"div",7),y(5,"div",8),E(6,b,2,2,"div",9),E(7,_,3,0,"div",10),E(8,x,3,0,"div",11),y(9,"div",12),E(10,C,1,1,null,13),j(11),v(),E(12,F,3,0,"div",14),E(13,A,3,0,"div",15),v(),E(14,Q,1,0,"div",16),v(),y(15,"div",17),E(16,Re,2,0,"div",18)(17,Be,5,1,"div",19),v()),re&2){let le,je=k._unwrapMaybeSignal(k._control.disabled);h(2),J("mdc-text-field--filled",!k._hasOutline())("mdc-text-field--outlined",k._hasOutline())("mdc-text-field--no-label",!k._hasFloatingLabel())("mdc-text-field--disabled",je)("mdc-text-field--invalid",k._unwrapMaybeSignal(k._control.errorState)),h(2),D(!k._hasOutline()&&!je?4:-1),h(2),D(k._hasOutline()?6:-1),h(),D(k._hasIconPrefix?7:-1),h(),D(k._hasTextPrefix?8:-1),h(2),D(!k._hasOutline()||k._forceDisplayInfixLabel()?10:-1),h(2),D(k._hasTextSuffix?12:-1),h(),D(k._hasIconSuffix?13:-1),h(),D(k._hasOutline()?-1:14),h(),J("mat-mdc-form-field-subscript-dynamic-size",k.subscriptSizing==="dynamic");let se=k._getSubscriptMessageType();h(),D((le=se)==="error"?16:le==="hint"?17:-1)}},dependencies:[P_,B_,Fr,L_,nu],styles:[`.mdc-text-field {
  display: inline-flex;
  align-items: baseline;
  padding: 0 16px;
  position: relative;
  box-sizing: border-box;
  overflow: hidden;
  will-change: opacity, transform, color;
  border-top-left-radius: 4px;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.mdc-text-field__input {
  width: 100%;
  min-width: 0;
  border: none;
  border-radius: 0;
  background: none;
  padding: 0;
  -moz-appearance: none;
  -webkit-appearance: none;
  height: 28px;
}
.mdc-text-field__input::-webkit-calendar-picker-indicator, .mdc-text-field__input::-webkit-search-cancel-button {
  display: none;
}
.mdc-text-field__input::-ms-clear {
  display: none;
}
.mdc-text-field__input:focus {
  outline: none;
}
.mdc-text-field__input:invalid {
  box-shadow: none;
}
.mdc-text-field__input::placeholder {
  opacity: 0;
}
.mdc-text-field__input::-moz-placeholder {
  opacity: 0;
}
.mdc-text-field__input::-webkit-input-placeholder {
  opacity: 0;
}
.mdc-text-field__input:-ms-input-placeholder {
  opacity: 0;
}
.mdc-text-field--no-label .mdc-text-field__input::placeholder, .mdc-text-field--focused .mdc-text-field__input::placeholder {
  opacity: 1;
}
.mdc-text-field--no-label .mdc-text-field__input::-moz-placeholder, .mdc-text-field--focused .mdc-text-field__input::-moz-placeholder {
  opacity: 1;
}
.mdc-text-field--no-label .mdc-text-field__input::-webkit-input-placeholder, .mdc-text-field--focused .mdc-text-field__input::-webkit-input-placeholder {
  opacity: 1;
}
.mdc-text-field--no-label .mdc-text-field__input:-ms-input-placeholder, .mdc-text-field--focused .mdc-text-field__input:-ms-input-placeholder {
  opacity: 1;
}
.mdc-text-field--%NS%disabled:not(.mdc-text-field--no-label) .mdc-text-field__input.mat-mdc-input-disabled-interactive::placeholder {
  opacity: 0;
}
.mdc-text-field--%NS%disabled:not(.mdc-text-field--no-label) .mdc-text-field__input.mat-mdc-input-disabled-interactive::-moz-placeholder {
  opacity: 0;
}
.mdc-text-field--%NS%disabled:not(.mdc-text-field--no-label) .mdc-text-field__input.mat-mdc-input-disabled-interactive::-webkit-input-placeholder {
  opacity: 0;
}
.mdc-text-field--%NS%disabled:not(.mdc-text-field--no-label) .mdc-text-field__input.mat-mdc-input-disabled-interactive:-ms-input-placeholder {
  opacity: 0;
}
.mdc-text-field--outlined .mdc-text-field__input, .mdc-text-field--filled.mdc-text-field--no-label .mdc-text-field__input {
  height: 100%;
}
.mdc-text-field--outlined .mdc-text-field__input {
  display: flex;
  border: none !important;
  background-color: transparent;
}
.mdc-text-field--disabled .mdc-text-field__input {
  pointer-events: auto;
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled) .mdc-text-field__input {
  color: var(--%NS%mat-form-field-filled-input-text-color, var(--%NS%mat-sys-on-surface));
  caret-color: var(--%NS%mat-form-field-filled-caret-color, var(--%NS%mat-sys-primary));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled) .mdc-text-field__input::placeholder {
  color: var(--%NS%mat-form-field-filled-input-text-placeholder-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled) .mdc-text-field__input::-moz-placeholder {
  color: var(--%NS%mat-form-field-filled-input-text-placeholder-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled) .mdc-text-field__input::-webkit-input-placeholder {
  color: var(--%NS%mat-form-field-filled-input-text-placeholder-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled) .mdc-text-field__input:-ms-input-placeholder {
  color: var(--%NS%mat-form-field-filled-input-text-placeholder-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled) .mdc-text-field__input {
  color: var(--%NS%mat-form-field-outlined-input-text-color, var(--%NS%mat-sys-on-surface));
  caret-color: var(--%NS%mat-form-field-outlined-caret-color, var(--%NS%mat-sys-primary));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled) .mdc-text-field__input::placeholder {
  color: var(--%NS%mat-form-field-outlined-input-text-placeholder-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled) .mdc-text-field__input::-moz-placeholder {
  color: var(--%NS%mat-form-field-outlined-input-text-placeholder-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled) .mdc-text-field__input::-webkit-input-placeholder {
  color: var(--%NS%mat-form-field-outlined-input-text-placeholder-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled) .mdc-text-field__input:-ms-input-placeholder {
  color: var(--%NS%mat-form-field-outlined-input-text-placeholder-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--filled.mdc-text-field--%NS%invalid:not(.mdc-text-field--disabled) .mdc-text-field__input {
  caret-color: var(--%NS%mat-form-field-filled-error-caret-color, var(--%NS%mat-sys-error));
}
.mdc-text-field--outlined.mdc-text-field--%NS%invalid:not(.mdc-text-field--disabled) .mdc-text-field__input {
  caret-color: var(--%NS%mat-form-field-outlined-error-caret-color, var(--%NS%mat-sys-error));
}
.mdc-text-field--filled.mdc-text-field--disabled .mdc-text-field__input {
  color: var(--%NS%mat-form-field-filled-disabled-input-text-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mdc-text-field--outlined.mdc-text-field--disabled .mdc-text-field__input {
  color: var(--%NS%mat-form-field-outlined-disabled-input-text-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
@media (forced-colors: active) {
  .mdc-text-field--disabled .mdc-text-field__input {
    background-color: Window;
  }
}

.mdc-text-field--filled {
  height: 56px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
  border-top-left-radius: var(--%NS%mat-form-field-filled-container-shape, var(--%NS%mat-sys-corner-extra-small));
  border-top-right-radius: var(--%NS%mat-form-field-filled-container-shape, var(--%NS%mat-sys-corner-extra-small));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled) {
  background-color: var(--%NS%mat-form-field-filled-container-color, var(--%NS%mat-sys-surface-variant));
}
.mdc-text-field--filled.mdc-text-field--disabled {
  background-color: var(--%NS%mat-form-field-filled-disabled-container-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 4%, transparent));
}

.mdc-text-field--outlined {
  height: 56px;
  overflow: visible;
  padding-right: max(16px, var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small)));
  padding-left: max(16px, var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small)) + 4px);
}
[dir=rtl] .mdc-text-field--outlined {
  padding-right: max(16px, var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small)) + 4px);
  padding-left: max(16px, var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small)));
}

.mdc-floating-label {
  position: absolute;
  left: 0;
  transform-origin: left top;
  line-height: 1.15rem;
  text-align: left;
  text-overflow: ellipsis;
  white-space: nowrap;
  cursor: text;
  overflow: hidden;
  will-change: transform;
}
[dir=rtl] .mdc-floating-label {
  right: 0;
  left: auto;
  transform-origin: right top;
  text-align: right;
}
.mdc-text-field .mdc-floating-label {
  top: 50%;
  transform: translateY(-50%);
  pointer-events: none;
}
.mdc-notched-outline .mdc-floating-label {
  display: inline-block;
  position: relative;
  max-width: 100%;
}
.mdc-text-field--outlined .mdc-floating-label {
  left: 4px;
  right: auto;
}
[dir=rtl] .mdc-text-field--outlined .mdc-floating-label {
  left: auto;
  right: 4px;
}
.mdc-text-field--filled .mdc-floating-label {
  left: 16px;
  right: auto;
}
[dir=rtl] .mdc-text-field--filled .mdc-floating-label {
  left: auto;
  right: 16px;
}
.mdc-text-field--disabled .mdc-floating-label {
  cursor: default;
}
@media (forced-colors: active) {
  .mdc-text-field--disabled .mdc-floating-label {
    z-index: 1;
  }
}
.mdc-text-field--filled.mdc-text-field--no-label .mdc-floating-label {
  display: none;
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled) .mdc-floating-label {
  color: var(--%NS%mat-form-field-filled-label-text-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled).mdc-text-field--focused .mdc-floating-label {
  color: var(--%NS%mat-form-field-filled-focus-label-text-color, var(--%NS%mat-sys-primary));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled):not(.mdc-text-field--focused):hover .mdc-floating-label {
  color: var(--%NS%mat-form-field-filled-hover-label-text-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--filled.mdc-text-field--disabled .mdc-floating-label {
  color: var(--%NS%mat-form-field-filled-disabled-label-text-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled).mdc-text-field--invalid .mdc-floating-label {
  color: var(--%NS%mat-form-field-filled-error-label-text-color, var(--%NS%mat-sys-error));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled).mdc-text-field--invalid.mdc-text-field--focused .mdc-floating-label {
  color: var(--%NS%mat-form-field-filled-error-focus-label-text-color, var(--%NS%mat-sys-error));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled).mdc-text-field--%NS%invalid:not(.mdc-text-field--disabled):hover .mdc-floating-label {
  color: var(--%NS%mat-form-field-filled-error-hover-label-text-color, var(--%NS%mat-sys-on-error-container));
}
.mdc-text-field--filled .mdc-floating-label {
  font-family: var(--%NS%mat-form-field-filled-label-text-font, var(--%NS%mat-sys-body-large-font));
  font-size: var(--%NS%mat-form-field-filled-label-text-size, var(--%NS%mat-sys-body-large-size));
  font-weight: var(--%NS%mat-form-field-filled-label-text-weight, var(--%NS%mat-sys-body-large-weight));
  letter-spacing: var(--%NS%mat-form-field-filled-label-text-tracking, var(--%NS%mat-sys-body-large-tracking));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled) .mdc-floating-label {
  color: var(--%NS%mat-form-field-outlined-label-text-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled).mdc-text-field--focused .mdc-floating-label {
  color: var(--%NS%mat-form-field-outlined-focus-label-text-color, var(--%NS%mat-sys-primary));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled):not(.mdc-text-field--focused):hover .mdc-floating-label {
  color: var(--%NS%mat-form-field-outlined-hover-label-text-color, var(--%NS%mat-sys-on-surface));
}
.mdc-text-field--outlined.mdc-text-field--disabled .mdc-floating-label {
  color: var(--%NS%mat-form-field-outlined-disabled-label-text-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled).mdc-text-field--invalid .mdc-floating-label {
  color: var(--%NS%mat-form-field-outlined-error-label-text-color, var(--%NS%mat-sys-error));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled).mdc-text-field--invalid.mdc-text-field--focused .mdc-floating-label {
  color: var(--%NS%mat-form-field-outlined-error-focus-label-text-color, var(--%NS%mat-sys-error));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled).mdc-text-field--%NS%invalid:not(.mdc-text-field--disabled):hover .mdc-floating-label {
  color: var(--%NS%mat-form-field-outlined-error-hover-label-text-color, var(--%NS%mat-sys-on-error-container));
}
.mdc-text-field--outlined .mdc-floating-label {
  font-family: var(--%NS%mat-form-field-outlined-label-text-font, var(--%NS%mat-sys-body-large-font));
  font-size: var(--%NS%mat-form-field-outlined-label-text-size, var(--%NS%mat-sys-body-large-size));
  font-weight: var(--%NS%mat-form-field-outlined-label-text-weight, var(--%NS%mat-sys-body-large-weight));
  letter-spacing: var(--%NS%mat-form-field-outlined-label-text-tracking, var(--%NS%mat-sys-body-large-tracking));
}

.mdc-floating-label--float-above {
  cursor: auto;
  transform: translateY(-106%) scale(0.75);
}
.mdc-text-field--filled .mdc-floating-label--float-above {
  transform: translateY(-106%) scale(0.75);
}
.mdc-text-field--outlined .mdc-floating-label--float-above {
  transform: translateY(-37.25px) scale(1);
  font-size: 0.75rem;
}
.mdc-notched-outline .mdc-floating-label--float-above {
  text-overflow: clip;
}
.mdc-notched-outline--upgraded .mdc-floating-label--float-above {
  max-width: 133.3333333333%;
}
.mdc-text-field--outlined.mdc-notched-outline--upgraded .mdc-floating-label--float-above, .mdc-text-field--outlined .mdc-notched-outline--upgraded .mdc-floating-label--float-above {
  transform: translateY(-34.75px) scale(0.75);
}
.mdc-text-field--outlined.mdc-notched-outline--upgraded .mdc-floating-label--float-above, .mdc-text-field--outlined .mdc-notched-outline--upgraded .mdc-floating-label--float-above {
  font-size: 1rem;
}

.mdc-floating-label--%NS%required:not(.mdc-floating-label--hide-required-marker)::after {
  margin-left: 1px;
  margin-right: 0;
  content: "*";
}
[dir=rtl] .mdc-floating-label--%NS%required:not(.mdc-floating-label--hide-required-marker)::after {
  margin-left: 0;
  margin-right: 1px;
}

.mdc-notched-outline {
  display: flex;
  position: absolute;
  top: 0;
  right: 0;
  left: 0;
  box-sizing: border-box;
  width: 100%;
  max-width: 100%;
  height: 100%;
  text-align: left;
  pointer-events: none;
}
[dir=rtl] .mdc-notched-outline {
  text-align: right;
}
.mdc-text-field--outlined .mdc-notched-outline {
  z-index: 1;
}

.mat-mdc-notch-piece {
  box-sizing: border-box;
  height: 100%;
  pointer-events: none;
  border: none;
  border-top: 1px solid;
  border-bottom: 1px solid;
}
.mdc-text-field--focused .mat-mdc-notch-piece {
  border-width: 2px;
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled) .mat-mdc-notch-piece {
  border-color: var(--%NS%mat-form-field-outlined-outline-color, var(--%NS%mat-sys-outline));
  border-width: var(--%NS%mat-form-field-outlined-outline-width, 1px);
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled):not(.mdc-text-field--focused):hover .mat-mdc-notch-piece {
  border-color: var(--%NS%mat-form-field-outlined-hover-outline-color, var(--%NS%mat-sys-on-surface));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled).mdc-text-field--focused .mat-mdc-notch-piece {
  border-color: var(--%NS%mat-form-field-outlined-focus-outline-color, var(--%NS%mat-sys-primary));
}
.mdc-text-field--outlined.mdc-text-field--disabled .mat-mdc-notch-piece {
  border-color: var(--%NS%mat-form-field-outlined-disabled-outline-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 12%, transparent));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled).mdc-text-field--invalid .mat-mdc-notch-piece {
  border-color: var(--%NS%mat-form-field-outlined-error-outline-color, var(--%NS%mat-sys-error));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled).mdc-text-field--%NS%invalid:not(.mdc-text-field--focused):hover .mdc-notched-outline .mat-mdc-notch-piece {
  border-color: var(--%NS%mat-form-field-outlined-error-hover-outline-color, var(--%NS%mat-sys-on-error-container));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled).mdc-text-field--invalid.mdc-text-field--focused .mat-mdc-notch-piece {
  border-color: var(--%NS%mat-form-field-outlined-error-focus-outline-color, var(--%NS%mat-sys-error));
}
.mdc-text-field--%NS%outlined:not(.mdc-text-field--disabled).mdc-text-field--focused .mdc-notched-outline .mat-mdc-notch-piece {
  border-width: var(--%NS%mat-form-field-outlined-focus-outline-width, 2px);
}

.mdc-notched-outline__leading {
  border-left: 1px solid;
  border-right: none;
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  border-top-left-radius: var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small));
  border-bottom-left-radius: var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small));
}
.mdc-text-field--outlined .mdc-notched-outline .mdc-notched-outline__leading {
  width: max(12px, var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small)));
}
[dir=rtl] .mdc-notched-outline__leading {
  border-left: none;
  border-right: 1px solid;
  border-bottom-left-radius: 0;
  border-top-left-radius: 0;
  border-top-right-radius: var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small));
  border-bottom-right-radius: var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small));
}

.mdc-notched-outline__trailing {
  flex-grow: 1;
  border-left: none;
  border-right: 1px solid;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  border-top-right-radius: var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small));
  border-bottom-right-radius: var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small));
}
[dir=rtl] .mdc-notched-outline__trailing {
  border-left: 1px solid;
  border-right: none;
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  border-top-left-radius: var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small));
  border-bottom-left-radius: var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small));
}

.mdc-notched-outline__notch {
  flex: 0 0 auto;
  width: auto;
}
.mdc-text-field--outlined .mdc-notched-outline .mdc-notched-outline__notch {
  max-width: min(var(--%NS%mat-form-field-notch-max-width, 100%), calc(100% - max(12px, var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small))) * 2));
}
.mdc-text-field--outlined .mdc-notched-outline--notched .mdc-notched-outline__notch {
  max-width: min(100%, calc(100% - max(12px, var(--%NS%mat-form-field-outlined-container-shape, var(--%NS%mat-sys-corner-extra-small))) * 2));
}
.mdc-text-field--outlined .mdc-notched-outline--notched .mdc-notched-outline__notch {
  padding-top: 1px;
}
.mdc-text-field--focused.mdc-text-field--outlined .mdc-notched-outline--notched .mdc-notched-outline__notch {
  padding-top: 2px;
}
.mdc-notched-outline--notched .mdc-notched-outline__notch {
  padding-left: 0;
  padding-right: 8px;
  border-top: none;
}
[dir=rtl] .mdc-notched-outline--notched .mdc-notched-outline__notch {
  padding-left: 8px;
  padding-right: 0;
}
.mdc-notched-outline--no-label .mdc-notched-outline__notch {
  display: none;
}

.mdc-line-ripple::before, .mdc-line-ripple::after {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  border-bottom-style: solid;
  content: "";
}
.mdc-line-ripple::before {
  z-index: 1;
  border-bottom-width: var(--%NS%mat-form-field-filled-active-indicator-height, 1px);
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled) .mdc-line-ripple::before {
  border-bottom-color: var(--%NS%mat-form-field-filled-active-indicator-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled):not(.mdc-text-field--focused):hover .mdc-line-ripple::before {
  border-bottom-color: var(--%NS%mat-form-field-filled-hover-active-indicator-color, var(--%NS%mat-sys-on-surface));
}
.mdc-text-field--filled.mdc-text-field--disabled .mdc-line-ripple::before {
  border-bottom-color: var(--%NS%mat-form-field-filled-disabled-active-indicator-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled).mdc-text-field--invalid .mdc-line-ripple::before {
  border-bottom-color: var(--%NS%mat-form-field-filled-error-active-indicator-color, var(--%NS%mat-sys-error));
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled).mdc-text-field--%NS%invalid:not(.mdc-text-field--focused):hover .mdc-line-ripple::before {
  border-bottom-color: var(--%NS%mat-form-field-filled-error-hover-active-indicator-color, var(--%NS%mat-sys-on-error-container));
}
.mdc-line-ripple::after {
  transform: scaleX(0);
  opacity: 0;
  z-index: 2;
}
.mdc-text-field--filled .mdc-line-ripple::after {
  border-bottom-width: var(--%NS%mat-form-field-filled-focus-active-indicator-height, 2px);
}
.mdc-text-field--%NS%filled:not(.mdc-text-field--disabled) .mdc-line-ripple::after {
  border-bottom-color: var(--%NS%mat-form-field-filled-focus-active-indicator-color, var(--%NS%mat-sys-primary));
}
.mdc-text-field--filled.mdc-text-field--%NS%invalid:not(.mdc-text-field--disabled) .mdc-line-ripple::after {
  border-bottom-color: var(--%NS%mat-form-field-filled-error-focus-active-indicator-color, var(--%NS%mat-sys-error));
}

.mdc-line-ripple--%NS%active::after {
  transform: scaleX(1);
  opacity: 1;
}

.mdc-line-ripple--%NS%deactivating::after {
  opacity: 0;
}

.mdc-text-field--disabled {
  pointer-events: none;
}

.mat-mdc-form-field-textarea-control {
  vertical-align: middle;
  resize: vertical;
  box-sizing: border-box;
  height: auto;
  margin: 0;
  padding: 0;
  border: none;
  overflow: auto;
}

.mat-mdc-form-field-input-control.mat-mdc-form-field-input-control {
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  font: inherit;
  letter-spacing: inherit;
  text-decoration: inherit;
  text-transform: inherit;
  border: none;
}

.mat-mdc-form-field .mat-mdc-floating-label.mdc-floating-label {
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  line-height: normal;
  pointer-events: all;
  will-change: auto;
}

.mat-mdc-form-field:not(.mat-form-field-disabled) .mat-mdc-floating-label.mdc-floating-label {
  cursor: inherit;
}

.mdc-text-field--%NS%no-label:not(.mdc-text-field--textarea) .mat-mdc-form-field-input-control.mdc-text-field__input,
.mat-mdc-text-field-wrapper .mat-mdc-form-field-input-control {
  height: auto;
}

.mat-mdc-text-field-wrapper .mat-mdc-form-field-input-control.mdc-text-field__input[type=color] {
  height: 23px;
}

.mat-mdc-text-field-wrapper {
  height: auto;
  flex: auto;
  will-change: auto;
}

.mat-mdc-form-field-has-icon-prefix .mat-mdc-text-field-wrapper {
  padding-left: 0;
  --%NS%mat-mdc-form-field-label-offset-x: -16px;
}

.mat-mdc-form-field-has-icon-suffix .mat-mdc-text-field-wrapper {
  padding-right: 0;
}

[dir=rtl] .mat-mdc-text-field-wrapper {
  padding-left: 16px;
  padding-right: 16px;
}
[dir=rtl] .mat-mdc-form-field-has-icon-suffix .mat-mdc-text-field-wrapper {
  padding-left: 0;
}
[dir=rtl] .mat-mdc-form-field-has-icon-prefix .mat-mdc-text-field-wrapper {
  padding-right: 0;
}

.mat-form-field-disabled .mdc-text-field__input::placeholder {
  color: var(--%NS%mat-form-field-disabled-input-text-placeholder-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mat-form-field-disabled .mdc-text-field__input::-moz-placeholder {
  color: var(--%NS%mat-form-field-disabled-input-text-placeholder-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mat-form-field-disabled .mdc-text-field__input::-webkit-input-placeholder {
  color: var(--%NS%mat-form-field-disabled-input-text-placeholder-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mat-form-field-disabled .mdc-text-field__input:-ms-input-placeholder {
  color: var(--%NS%mat-form-field-disabled-input-text-placeholder-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}

.mat-mdc-form-field-label-always-float .mdc-text-field__input::placeholder {
  transition-delay: 40ms;
  transition-duration: 110ms;
  opacity: 1;
}

.mat-mdc-text-field-wrapper .mat-mdc-form-field-infix .mat-mdc-floating-label {
  left: auto;
  right: auto;
}

.mat-mdc-text-field-wrapper.mdc-text-field--outlined .mdc-text-field__input {
  display: inline-block;
}

.mat-mdc-form-field .mat-mdc-text-field-wrapper.mdc-text-field .mdc-notched-outline__notch {
  padding-top: 0;
}

.mat-mdc-form-field.mat-mdc-form-field.mat-mdc-form-field.mat-mdc-form-field.mat-mdc-form-field.mat-mdc-form-field .mdc-notched-outline__notch {
  border-left: 1px solid transparent;
}

[dir=rtl] .mat-mdc-form-field.mat-mdc-form-field.mat-mdc-form-field.mat-mdc-form-field.mat-mdc-form-field.mat-mdc-form-field .mdc-notched-outline__notch {
  border-left: none;
  border-right: 1px solid transparent;
}

.mat-mdc-form-field-infix {
  min-height: var(--%NS%mat-form-field-container-height, 56px);
  padding-top: var(--%NS%mat-form-field-filled-with-label-container-padding-top, 24px);
  padding-bottom: var(--%NS%mat-form-field-filled-with-label-container-padding-bottom, 8px);
}
.mdc-text-field--outlined .mat-mdc-form-field-infix, .mdc-text-field--no-label .mat-mdc-form-field-infix {
  padding-top: var(--%NS%mat-form-field-container-vertical-padding, 16px);
  padding-bottom: var(--%NS%mat-form-field-container-vertical-padding, 16px);
}

.mat-mdc-text-field-wrapper .mat-mdc-form-field-flex .mat-mdc-floating-label {
  top: calc(var(--%NS%mat-form-field-container-height, 56px) / 2);
}

.mdc-text-field--filled .mat-mdc-floating-label {
  display: var(--%NS%mat-form-field-filled-label-display, block);
}

.mat-mdc-text-field-wrapper.mdc-text-field--outlined .mdc-notched-outline--upgraded .mdc-floating-label--float-above {
  --%NS%mat-mdc-form-field-label-transform: translateY(calc(calc(6.75px + var(--%NS%mat-form-field-container-height, 56px) / 2) * -1))
    scale(var(--%NS%mat-mdc-form-field-floating-label-scale, 0.75));
  transform: var(--%NS%mat-mdc-form-field-label-transform);
}

@keyframes _mat-form-field-subscript-animation {
  from {
    opacity: 0;
    transform: translateY(-5px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
.mat-mdc-form-field-subscript-wrapper {
  box-sizing: border-box;
  width: 100%;
  position: relative;
}

.mat-mdc-form-field-hint-wrapper,
.mat-mdc-form-field-error-wrapper {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  padding: 0 16px;
  opacity: 1;
  transform: translateY(0);
  animation: _mat-form-field-subscript-animation 0ms cubic-bezier(0.55, 0, 0.55, 0.2);
}

.mat-mdc-form-field-subscript-dynamic-size .mat-mdc-form-field-hint-wrapper,
.mat-mdc-form-field-subscript-dynamic-size .mat-mdc-form-field-error-wrapper {
  position: static;
}

.mat-mdc-form-field-bottom-align::before {
  content: "";
  display: inline-block;
  height: 16px;
}

.mat-mdc-form-field-bottom-align.mat-mdc-form-field-subscript-dynamic-size::before {
  content: unset;
}

.mat-mdc-form-field-hint-end {
  order: 1;
}

.mat-mdc-form-field-hint-wrapper {
  display: flex;
}

.mat-mdc-form-field-hint-spacer {
  flex: 1 0 1em;
}

.mat-mdc-form-field-error {
  display: block;
  color: var(--%NS%mat-form-field-error-text-color, var(--%NS%mat-sys-error));
}

.mat-mdc-form-field-subscript-wrapper,
.mat-mdc-form-field-bottom-align::before {
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  font-family: var(--%NS%mat-form-field-subscript-text-font, var(--%NS%mat-sys-body-small-font));
  line-height: var(--%NS%mat-form-field-subscript-text-line-height, var(--%NS%mat-sys-body-small-line-height));
  font-size: var(--%NS%mat-form-field-subscript-text-size, var(--%NS%mat-sys-body-small-size));
  letter-spacing: var(--%NS%mat-form-field-subscript-text-tracking, var(--%NS%mat-sys-body-small-tracking));
  font-weight: var(--%NS%mat-form-field-subscript-text-weight, var(--%NS%mat-sys-body-small-weight));
}

.mat-mdc-form-field-focus-overlay {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  opacity: 0;
  pointer-events: none;
  background-color: var(--%NS%mat-form-field-state-layer-color, var(--%NS%mat-sys-on-surface));
}
.mat-mdc-text-field-wrapper:hover .mat-mdc-form-field-focus-overlay {
  opacity: var(--%NS%mat-form-field-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity));
}
.mat-mdc-form-field.mat-focused .mat-mdc-form-field-focus-overlay {
  opacity: var(--%NS%mat-form-field-focus-state-layer-opacity, 0);
}

select.mat-mdc-form-field-input-control {
  -moz-appearance: none;
  -webkit-appearance: none;
  background-color: transparent;
  display: inline-flex;
  box-sizing: border-box;
}
select.mat-mdc-form-field-input-control:not(:disabled) {
  cursor: pointer;
}
select.mat-mdc-form-field-input-control:not(.mat-mdc-native-select-inline) option {
  color: var(--%NS%mat-form-field-select-option-text-color, var(--%NS%mat-sys-neutral10));
}
select.mat-mdc-form-field-input-control:not(.mat-mdc-native-select-inline) option:disabled {
  color: var(--%NS%mat-form-field-select-disabled-option-text-color, color-mix(in srgb, var(--%NS%mat-sys-neutral10) 38%, transparent));
}

.mat-mdc-form-field-type-mat-native-select .mat-mdc-form-field-infix::after {
  content: "";
  width: 0;
  height: 0;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-top: 5px solid;
  position: absolute;
  right: 0;
  top: 50%;
  margin-top: -2.5px;
  pointer-events: none;
  color: var(--%NS%mat-form-field-enabled-select-arrow-color, var(--%NS%mat-sys-on-surface-variant));
}
[dir=rtl] .mat-mdc-form-field-type-mat-native-select .mat-mdc-form-field-infix::after {
  right: auto;
  left: 0;
}
.mat-mdc-form-field-type-mat-native-select.mat-focused .mat-mdc-form-field-infix::after {
  color: var(--%NS%mat-form-field-focus-select-arrow-color, var(--%NS%mat-sys-primary));
}
.mat-mdc-form-field-type-mat-native-select.mat-form-field-disabled .mat-mdc-form-field-infix::after {
  color: var(--%NS%mat-form-field-disabled-select-arrow-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mat-mdc-form-field-type-mat-native-select .mat-mdc-form-field-input-control {
  padding-right: 15px;
}
[dir=rtl] .mat-mdc-form-field-type-mat-native-select .mat-mdc-form-field-input-control {
  padding-right: 0;
  padding-left: 15px;
}

@media (forced-colors: active) {
  .mat-form-field-appearance-fill .mat-mdc-text-field-wrapper {
    outline: solid 1px;
  }
}
@media (forced-colors: active) {
  .mat-form-field-appearance-fill.mat-form-field-disabled .mat-mdc-text-field-wrapper {
    outline-color: GrayText;
  }
}

@media (forced-colors: active) {
  .mat-form-field-appearance-fill.mat-focused .mat-mdc-text-field-wrapper {
    outline: dashed 3px;
  }
}

@media (forced-colors: active) {
  .mat-mdc-form-field.mat-focused .mdc-notched-outline {
    border: dashed 3px;
  }
}

.mat-mdc-form-field-input-control[type=date], .mat-mdc-form-field-input-control[type=datetime], .mat-mdc-form-field-input-control[type=datetime-local], .mat-mdc-form-field-input-control[type=month], .mat-mdc-form-field-input-control[type=week], .mat-mdc-form-field-input-control[type=time] {
  line-height: 1;
}
.mat-mdc-form-field-input-control::-webkit-datetime-edit {
  line-height: 1;
  padding: 0;
  margin-bottom: -2px;
}

.mat-mdc-form-field {
  --%NS%mat-mdc-form-field-floating-label-scale: 0.75;
  display: inline-flex;
  flex-direction: column;
  min-width: 0;
  text-align: left;
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  font-family: var(--%NS%mat-form-field-container-text-font, var(--%NS%mat-sys-body-large-font));
  line-height: var(--%NS%mat-form-field-container-text-line-height, var(--%NS%mat-sys-body-large-line-height));
  font-size: var(--%NS%mat-form-field-container-text-size, var(--%NS%mat-sys-body-large-size));
  letter-spacing: var(--%NS%mat-form-field-container-text-tracking, var(--%NS%mat-sys-body-large-tracking));
  font-weight: var(--%NS%mat-form-field-container-text-weight, var(--%NS%mat-sys-body-large-weight));
}
.mat-mdc-form-field .mdc-text-field--outlined .mdc-floating-label--float-above {
  font-size: calc(var(--%NS%mat-form-field-outlined-label-text-populated-size) * var(--%NS%mat-mdc-form-field-floating-label-scale));
}
.mat-mdc-form-field .mdc-text-field--outlined .mdc-notched-outline--upgraded .mdc-floating-label--float-above {
  font-size: var(--%NS%mat-form-field-outlined-label-text-populated-size);
}
[dir=rtl] .mat-mdc-form-field {
  text-align: right;
}

.mat-mdc-form-field-flex {
  display: inline-flex;
  align-items: baseline;
  box-sizing: border-box;
  width: 100%;
}

.mat-mdc-text-field-wrapper {
  width: 100%;
  z-index: 0;
}

.mat-mdc-form-field-icon-prefix,
.mat-mdc-form-field-icon-suffix {
  align-self: center;
  line-height: 0;
  pointer-events: auto;
  position: relative;
  z-index: 1;
}
.mat-mdc-form-field-icon-prefix > .mat-icon,
.mat-mdc-form-field-icon-suffix > .mat-icon {
  padding: 0 12px;
  box-sizing: content-box;
}

.mat-mdc-form-field-icon-prefix {
  color: var(--%NS%mat-form-field-leading-icon-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-form-field-disabled .mat-mdc-form-field-icon-prefix {
  color: var(--%NS%mat-form-field-disabled-leading-icon-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}

.mat-mdc-form-field-icon-suffix {
  color: var(--%NS%mat-form-field-trailing-icon-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-form-field-disabled .mat-mdc-form-field-icon-suffix {
  color: var(--%NS%mat-form-field-disabled-trailing-icon-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mat-form-field-invalid .mat-mdc-form-field-icon-suffix {
  color: var(--%NS%mat-form-field-error-trailing-icon-color, var(--%NS%mat-sys-error));
}
.mat-form-field-invalid:not(.mat-focused):not(.mat-form-field-disabled) .mat-mdc-text-field-wrapper:hover .mat-mdc-form-field-icon-suffix {
  color: var(--%NS%mat-form-field-error-hover-trailing-icon-color, var(--%NS%mat-sys-on-error-container));
}
.mat-form-field-invalid.mat-focused .mat-mdc-text-field-wrapper .mat-mdc-form-field-icon-suffix {
  color: var(--%NS%mat-form-field-error-focus-trailing-icon-color, var(--%NS%mat-sys-error));
}

.mat-mdc-form-field-icon-prefix,
[dir=rtl] .mat-mdc-form-field-icon-suffix {
  padding: 0 4px 0 0;
}

.mat-mdc-form-field-icon-suffix,
[dir=rtl] .mat-mdc-form-field-icon-prefix {
  padding: 0 0 0 4px;
}

.mat-mdc-form-field-subscript-wrapper .mat-icon,
.mat-mdc-form-field label .mat-icon {
  width: 1em;
  height: 1em;
  font-size: inherit;
}

.mat-mdc-form-field-infix {
  flex: auto;
  min-width: 0;
  width: 180px;
  position: relative;
  box-sizing: border-box;
}
.mat-mdc-form-field-infix:has(textarea[cols]) {
  width: auto;
}

.mat-mdc-form-field .mdc-notched-outline__notch {
  margin-left: -1px;
  -webkit-clip-path: inset(-9em -999em -9em 1px);
  clip-path: inset(-9em -999em -9em 1px);
}
[dir=rtl] .mat-mdc-form-field .mdc-notched-outline__notch {
  margin-left: 0;
  margin-right: -1px;
  -webkit-clip-path: inset(-9em 1px -9em -999em);
  clip-path: inset(-9em 1px -9em -999em);
}

.mat-mdc-form-field.mat-form-field-animations-enabled .mdc-floating-label {
  transition: transform 150ms cubic-bezier(0.4, 0, 0.2, 1), color 150ms cubic-bezier(0.4, 0, 0.2, 1);
}
.mat-mdc-form-field.mat-form-field-animations-enabled .mdc-text-field__input {
  transition: opacity 150ms cubic-bezier(0.4, 0, 0.2, 1);
}
.mat-mdc-form-field.mat-form-field-animations-enabled .mdc-text-field__input::placeholder {
  transition: opacity 67ms cubic-bezier(0.4, 0, 0.2, 1);
}
.mat-mdc-form-field.mat-form-field-animations-enabled .mdc-text-field__input::-moz-placeholder {
  transition: opacity 67ms cubic-bezier(0.4, 0, 0.2, 1);
}
.mat-mdc-form-field.mat-form-field-animations-enabled .mdc-text-field__input::-webkit-input-placeholder {
  transition: opacity 67ms cubic-bezier(0.4, 0, 0.2, 1);
}
.mat-mdc-form-field.mat-form-field-animations-enabled .mdc-text-field__input:-ms-input-placeholder {
  transition: opacity 67ms cubic-bezier(0.4, 0, 0.2, 1);
}
.mat-mdc-form-field.mat-form-field-animations-enabled.mdc-text-field--no-label .mdc-text-field__input::placeholder, .mat-mdc-form-field.mat-form-field-animations-enabled.mdc-text-field--focused .mdc-text-field__input::placeholder {
  transition-delay: 40ms;
  transition-duration: 110ms;
}
.mat-mdc-form-field.mat-form-field-animations-enabled.mdc-text-field--no-label .mdc-text-field__input::-moz-placeholder, .mat-mdc-form-field.mat-form-field-animations-enabled.mdc-text-field--focused .mdc-text-field__input::-moz-placeholder {
  transition-delay: 40ms;
  transition-duration: 110ms;
}
.mat-mdc-form-field.mat-form-field-animations-enabled.mdc-text-field--no-label .mdc-text-field__input::-webkit-input-placeholder, .mat-mdc-form-field.mat-form-field-animations-enabled.mdc-text-field--focused .mdc-text-field__input::-webkit-input-placeholder {
  transition-delay: 40ms;
  transition-duration: 110ms;
}
.mat-mdc-form-field.mat-form-field-animations-enabled.mdc-text-field--no-label .mdc-text-field__input:-ms-input-placeholder, .mat-mdc-form-field.mat-form-field-animations-enabled.mdc-text-field--focused .mdc-text-field__input:-ms-input-placeholder {
  transition-delay: 40ms;
  transition-duration: 110ms;
}
.mat-mdc-form-field.mat-form-field-animations-enabled .mdc-text-field--%NS%filled:not(.mdc-ripple-upgraded):focus .mdc-text-field__ripple::before {
  transition-duration: 75ms;
}
.mat-mdc-form-field.mat-form-field-animations-enabled .mdc-line-ripple::after {
  transition: transform 180ms cubic-bezier(0.4, 0, 0.2, 1), opacity 180ms cubic-bezier(0.4, 0, 0.2, 1);
}
.mat-mdc-form-field.mat-form-field-animations-enabled .mat-mdc-form-field-hint-wrapper,
.mat-mdc-form-field.mat-form-field-animations-enabled .mat-mdc-form-field-error-wrapper {
  animation-duration: 300ms;
}

.mdc-notched-outline .mdc-floating-label {
  max-width: calc(100% + 1px);
}

.mdc-notched-outline--upgraded .mdc-floating-label--float-above {
  max-width: calc(133.3333333333% + 1px);
}
`],encapsulation:2})})()}return n})();var qo=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[il,z_,me]})}return n})();var H_=(()=>{class n{_animationsDisabled=tt();state="unchecked";disabled=!1;appearance="full";static \u0275fac=function(i){return new(i||n)};static \u0275cmp=T({type:n,selectors:[["mat-pseudo-checkbox"]],hostAttrs:[1,"mat-pseudo-checkbox"],hostVars:12,hostBindings:function(i,a){i&2&&J("mat-pseudo-checkbox-indeterminate",a.state==="indeterminate")("mat-pseudo-checkbox-checked",a.state==="checked")("mat-pseudo-checkbox-disabled",a.disabled)("mat-pseudo-checkbox-minimal",a.appearance==="minimal")("mat-pseudo-checkbox-full",a.appearance==="full")("_mat-animation-noopable",a._animationsDisabled)},inputs:{state:"state",disabled:"disabled",appearance:"appearance"},decls:0,vars:0,template:function(i,a){},styles:[`.mat-pseudo-checkbox {
  border-radius: 2px;
  cursor: pointer;
  display: inline-block;
  vertical-align: middle;
  box-sizing: border-box;
  position: relative;
  flex-shrink: 0;
  transition: border-color 90ms cubic-bezier(0, 0, 0.2, 0.1), background-color 90ms cubic-bezier(0, 0, 0.2, 0.1);
}
.mat-pseudo-checkbox::after {
  position: absolute;
  opacity: 0;
  content: "";
  border-bottom: 2px solid currentColor;
  transition: opacity 90ms cubic-bezier(0, 0, 0.2, 0.1);
}
.mat-pseudo-checkbox._mat-animation-noopable {
  transition: none !important;
  animation: none !important;
}
.mat-pseudo-checkbox._mat-animation-noopable::after {
  transition: none;
}

.mat-pseudo-checkbox-disabled {
  cursor: default;
}

.mat-pseudo-checkbox-indeterminate::after {
  left: 1px;
  opacity: 1;
  border-radius: 2px;
}

.mat-pseudo-checkbox-checked::after {
  left: 1px;
  border-left: 2px solid currentColor;
  transform: rotate(-45deg);
  opacity: 1;
  box-sizing: content-box;
}

.mat-pseudo-checkbox-minimal.mat-pseudo-checkbox-checked::after, .mat-pseudo-checkbox-minimal.mat-pseudo-checkbox-indeterminate::after {
  color: var(--%NS%mat-pseudo-checkbox-minimal-selected-checkmark-color, var(--%NS%mat-sys-primary));
}
.mat-pseudo-checkbox-minimal.mat-pseudo-checkbox-checked.mat-pseudo-checkbox-disabled::after, .mat-pseudo-checkbox-minimal.mat-pseudo-checkbox-indeterminate.mat-pseudo-checkbox-disabled::after {
  color: var(--%NS%mat-pseudo-checkbox-minimal-disabled-selected-checkmark-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}

.mat-pseudo-checkbox-full {
  border-color: var(--%NS%mat-pseudo-checkbox-full-unselected-icon-color, var(--%NS%mat-sys-on-surface-variant));
  border-width: 2px;
  border-style: solid;
}
.mat-pseudo-checkbox-full.mat-pseudo-checkbox-disabled {
  border-color: var(--%NS%mat-pseudo-checkbox-full-disabled-unselected-icon-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mat-pseudo-checkbox-full.mat-pseudo-checkbox-checked, .mat-pseudo-checkbox-full.mat-pseudo-checkbox-indeterminate {
  background-color: var(--%NS%mat-pseudo-checkbox-full-selected-icon-color, var(--%NS%mat-sys-primary));
  border-color: transparent;
}
.mat-pseudo-checkbox-full.mat-pseudo-checkbox-checked::after, .mat-pseudo-checkbox-full.mat-pseudo-checkbox-indeterminate::after {
  color: var(--%NS%mat-pseudo-checkbox-full-selected-checkmark-color, var(--%NS%mat-sys-on-primary));
}
.mat-pseudo-checkbox-full.mat-pseudo-checkbox-checked.mat-pseudo-checkbox-disabled, .mat-pseudo-checkbox-full.mat-pseudo-checkbox-indeterminate.mat-pseudo-checkbox-disabled {
  background-color: var(--%NS%mat-pseudo-checkbox-full-disabled-selected-icon-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}
.mat-pseudo-checkbox-full.mat-pseudo-checkbox-checked.mat-pseudo-checkbox-disabled::after, .mat-pseudo-checkbox-full.mat-pseudo-checkbox-indeterminate.mat-pseudo-checkbox-disabled::after {
  color: var(--%NS%mat-pseudo-checkbox-full-disabled-selected-checkmark-color, var(--%NS%mat-sys-surface));
}

.mat-pseudo-checkbox {
  width: 18px;
  height: 18px;
}

.mat-pseudo-checkbox-minimal.mat-pseudo-checkbox-checked::after {
  width: 14px;
  height: 6px;
  transform-origin: center;
  top: -4.2426406871px;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
}
.mat-pseudo-checkbox-minimal.mat-pseudo-checkbox-indeterminate::after {
  top: 8px;
  width: 16px;
}

.mat-pseudo-checkbox-full.mat-pseudo-checkbox-checked::after {
  width: 10px;
  height: 4px;
  transform-origin: center;
  top: -2.8284271247px;
  left: 0;
  bottom: 0;
  right: 0;
  margin: auto;
}
.mat-pseudo-checkbox-full.mat-pseudo-checkbox-indeterminate::after {
  top: 6px;
  width: 12px;
}
`],encapsulation:2})}return n})();var mM=new I("MAT_OPTION_PARENT_COMPONENT"),pM=new I("MatOptgroup");var iu=class{source;isUserInput;constructor(t,e=!1){this.source=t,this.isUserInput=e}},U_=(()=>{class n{_element=p(X);_changeDetectorRef=p(et);_parent=p(mM,{optional:!0});group=p(pM,{optional:!0});_signalDisableRipple=!1;_selected=!1;_active=!1;_mostRecentViewValue="";get multiple(){return this._parent&&this._parent.multiple}get selected(){return this._selected}value;id=p(St).getId("mat-option-");get disabled(){return this.group&&this.group.disabled||this._disabled()}set disabled(e){this._disabled.set(e)}_disabled=ne(!1);get disableRipple(){return this._signalDisableRipple?this._parent.disableRipple():!!this._parent?.disableRipple}get hideSingleSelectionIndicator(){return!!(this._parent&&this._parent.hideSingleSelectionIndicator)}onSelectionChange=new Te;_text;_stateChanges=new z;constructor(){let e=p(ct);e.load(un),e.load(Ea),this._signalDisableRipple=!!this._parent&&ii(this._parent.disableRipple)}get active(){return this._active}get viewValue(){return(this._text?.nativeElement.textContent||"").trim()}select(e=!0){this._selected||(this._selected=!0,this._changeDetectorRef.markForCheck(),e&&this._emitSelectionChangeEvent())}deselect(e=!0){this._selected&&(this._selected=!1,this._changeDetectorRef.markForCheck(),e&&this._emitSelectionChangeEvent())}focus(e,i){let a=this._getHostElement();typeof a.focus=="function"&&a.focus(i)}setActiveStyles(){this._active||(this._active=!0,this._changeDetectorRef.markForCheck())}setInactiveStyles(){this._active&&(this._active=!1,this._changeDetectorRef.markForCheck())}getLabel(){return this.viewValue}_handleKeydown(e){(e.keyCode===13||e.keyCode===32)&&!ui(e)&&(this._selectViaInteraction(),e.preventDefault())}_selectViaInteraction(){this.disabled||(this._selected=this.multiple?!this._selected:!0,this._changeDetectorRef.markForCheck(),this._emitSelectionChangeEvent(!0))}_getTabIndex(){return this.disabled?"-1":"0"}_getHostElement(){return this._element.nativeElement}ngAfterViewChecked(){if(this._selected){let e=this.viewValue;e!==this._mostRecentViewValue&&(this._mostRecentViewValue&&this._stateChanges.next(),this._mostRecentViewValue=e)}}ngOnDestroy(){this._stateChanges.complete()}_emitSelectionChangeEvent(e=!1){this.onSelectionChange.emit(new iu(this,e))}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=["text"],i=[[["mat-icon"]],"*"],a=["mat-icon","*"];function r(l,c){if(l&1&&P(0,"mat-pseudo-checkbox",1),l&2){let d=M();R("disabled",d.disabled)("state",d.selected?"checked":"unchecked")}}function o(l,c){if(l&1&&P(0,"mat-pseudo-checkbox",3),l&2){let d=M();R("disabled",d.disabled)}}function s(l,c){if(l&1&&(y(0,"span",4),S(1),v()),l&2){let d=M();h(),ae("(",d.group.label,")")}}return T({type:n,selectors:[["mat-option"]],viewQuery:function(c,d){if(c&1&&qe(e,7),c&2){let m;H(m=U())&&(d._text=m.first)}},hostAttrs:["role","option",1,"mat-mdc-option","mdc-list-item"],hostVars:11,hostBindings:function(c,d){c&1&&ye("click",function(){return d._selectViaInteraction()})("keydown",function(u){return d._handleKeydown(u)}),c&2&&(oi("id",d.id),fe("aria-selected",d.selected)("aria-disabled",d.disabled.toString()),J("mdc-list-item--selected",d.selected)("mat-mdc-option-multiple",d.multiple)("mat-mdc-option-active",d.active)("mdc-list-item--disabled",d.disabled))},inputs:{value:"value",id:"id",disabled:[2,"disabled","disabled",Ae]},outputs:{onSelectionChange:"onSelectionChange"},exportAs:["matOption"],ngContentSelectors:a,decls:8,vars:5,consts:[["text",""],["aria-hidden","true",1,"mat-mdc-option-pseudo-checkbox",3,"disabled","state"],[1,"mdc-list-item__primary-text"],["state","checked","aria-hidden","true","appearance","minimal",1,"mat-mdc-option-pseudo-checkbox",3,"disabled"],[1,"cdk-visually-hidden"],["aria-hidden","true","mat-ripple","",1,"mat-mdc-option-ripple","mat-focus-indicator",3,"matRippleTrigger","matRippleDisabled"]],template:function(c,d){c&1&&(de(i),E(0,r,1,2,"mat-pseudo-checkbox",1),j(1),y(2,"span",2,0),j(4,1),v(),E(5,o,1,1,"mat-pseudo-checkbox",3),E(6,s,2,1,"span",4),P(7,"div",5)),c&2&&(D(d.multiple?0:-1),h(5),D(!d.multiple&&d.selected&&!d.hideSingleSelectionIndicator?5:-1),h(),D(d.group&&d.group._inert?6:-1),h(),R("matRippleTrigger",d._getHostElement())("matRippleDisabled",d.disabled||d.disableRipple))},dependencies:[H_,xl],styles:[`.mat-mdc-option {
  -webkit-user-select: none;
  user-select: none;
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  display: flex;
  position: relative;
  align-items: center;
  justify-content: flex-start;
  overflow: hidden;
  min-height: 48px;
  padding: 0 16px;
  cursor: pointer;
  -webkit-tap-highlight-color: transparent;
  color: var(--%NS%mat-option-label-text-color, var(--%NS%mat-sys-on-surface));
  font-family: var(--%NS%mat-option-label-text-font, var(--%NS%mat-sys-label-large-font));
  line-height: var(--%NS%mat-option-label-text-line-height, var(--%NS%mat-sys-label-large-line-height));
  font-size: var(--%NS%mat-option-label-text-size, var(--%NS%mat-sys-body-large-size));
  letter-spacing: var(--%NS%mat-option-label-text-tracking, var(--%NS%mat-sys-label-large-tracking));
  font-weight: var(--%NS%mat-option-label-text-weight, var(--%NS%mat-sys-body-large-weight));
}
.mat-mdc-option:hover:not(.mdc-list-item--disabled) {
  background-color: var(--%NS%mat-option-hover-state-layer-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) calc(var(--%NS%mat-sys-hover-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-option:focus.mdc-list-item, .mat-mdc-option.mat-mdc-option-active.mdc-list-item {
  background-color: var(--%NS%mat-option-focus-state-layer-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) calc(var(--%NS%mat-sys-focus-state-layer-opacity) * 100%), transparent));
  outline: 0;
}
.mat-mdc-option.mdc-list-item--%NS%selected:not(.mdc-list-item--disabled):not(.mat-mdc-option-active, .mat-mdc-option-multiple, :focus, :hover) {
  background-color: var(--%NS%mat-option-selected-state-layer-color, var(--%NS%mat-sys-secondary-container));
}
.mat-mdc-option.mdc-list-item--%NS%selected:not(.mdc-list-item--disabled):not(.mat-mdc-option-active, .mat-mdc-option-multiple, :focus, :hover) .mdc-list-item__primary-text {
  color: var(--%NS%mat-option-selected-state-label-text-color, var(--%NS%mat-sys-on-secondary-container));
}
.mat-mdc-option .mat-pseudo-checkbox {
  --%NS%mat-pseudo-checkbox-minimal-selected-checkmark-color: var(--%NS%mat-option-selected-state-label-text-color, var(--%NS%mat-sys-on-secondary-container));
}
.mat-mdc-option.mdc-list-item {
  align-items: center;
  background: transparent;
}
.mat-mdc-option.mdc-list-item--disabled {
  cursor: default;
  pointer-events: none;
}
.mat-mdc-option.mdc-list-item--disabled .mat-mdc-option-pseudo-checkbox, .mat-mdc-option.mdc-list-item--disabled .mdc-list-item__primary-text, .mat-mdc-option.mdc-list-item--disabled > mat-icon {
  opacity: 0.38;
}
.mat-mdc-optgroup .mat-mdc-option:not(.mat-mdc-option-multiple) {
  padding-left: 32px;
}
[dir=rtl] .mat-mdc-optgroup .mat-mdc-option:not(.mat-mdc-option-multiple) {
  padding-left: 16px;
  padding-right: 32px;
}
.mat-mdc-option .mat-icon,
.mat-mdc-option .mat-pseudo-checkbox-full {
  margin-right: 16px;
  flex-shrink: 0;
}
[dir=rtl] .mat-mdc-option .mat-icon,
[dir=rtl] .mat-mdc-option .mat-pseudo-checkbox-full {
  margin-right: 0;
  margin-left: 16px;
}
.mat-mdc-option .mat-pseudo-checkbox-minimal {
  margin-left: 16px;
  flex-shrink: 0;
}
[dir=rtl] .mat-mdc-option .mat-pseudo-checkbox-minimal {
  margin-right: 16px;
  margin-left: 0;
}
.mat-mdc-option .mat-mdc-option-ripple {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  pointer-events: none;
}
.mat-mdc-option .mdc-list-item__primary-text {
  white-space: normal;
  font-size: inherit;
  font-weight: inherit;
  letter-spacing: inherit;
  line-height: inherit;
  font-family: inherit;
  text-decoration: inherit;
  text-transform: inherit;
  margin-right: auto;
}
[dir=rtl] .mat-mdc-option .mdc-list-item__primary-text {
  margin-right: 0;
  margin-left: auto;
}
@media (forced-colors: active) {
  .mat-mdc-option.mdc-list-item--%NS%selected:not(:has(.mat-mdc-option-pseudo-checkbox))::after {
    content: "";
    position: absolute;
    top: 50%;
    right: 16px;
    transform: translateY(-50%);
    width: 10px;
    height: 0;
    border-bottom: solid 10px;
    border-radius: 10px;
  }
  [dir=rtl] .mat-mdc-option.mdc-list-item--%NS%selected:not(:has(.mat-mdc-option-pseudo-checkbox))::after {
    right: auto;
    left: 16px;
  }
}

.mat-mdc-option-multiple {
  --%NS%mat-list-list-item-selected-container-color: var(--%NS%mat-list-list-item-container-color, transparent);
}

.mat-mdc-option-active .mat-focus-indicator::before {
  content: "";
}
`],encapsulation:2})})()}return n})();var q_=(()=>{class n{isErrorState(e,i){return!!(e&&e.invalid&&(e.touched||i&&i.submitted))}isSignalErrorState(e){if(!e)return!1;let i=e().invalid(),a=e().touched();return i&&a}static \u0275fac=function(i){return new(i||n)};static \u0275prov=ee({token:n,factory:n.\u0275fac})}return n})();var dc=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[me]})}return n})();var au=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[In,dc,U_,me]})}return n})();var Go=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[Hi,au,me,Hn,qo,au]})}return n})();var uM=new I("mat-chips-default-options",{providedIn:"root",factory:()=>({separatorKeyCodes:[13]})}),ru=new I("MatChipAvatar"),G_=new I("MatChipTrailingIcon"),W_=new I("MatChipEdit"),K_=new I("MatChipRemove"),Y_=new I("MatChip"),X_=(()=>{class n{_elementRef=p(X);_parentChip=p(Y_);_isPrimary=!0;_isLeading=!1;get disabled(){return this._disabled||this._parentChip?.disabled||!1}set disabled(e){this._disabled=e}_disabled=!1;tabIndex=-1;_allowFocusWhenDisabled=!1;_getDisabledAttribute(){return this.disabled&&!this._allowFocusWhenDisabled?"":null}constructor(){p(ct).load(un),this._elementRef.nativeElement.nodeName==="BUTTON"&&this._elementRef.nativeElement.setAttribute("type","button")}focus(){this._elementRef.nativeElement.focus()}static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","matChipContent",""]],hostAttrs:[1,"mat-mdc-chip-action","mdc-evolution-chip__action","mdc-evolution-chip__action--presentational"],hostVars:8,hostBindings:function(i,a){i&2&&(fe("disabled",a._getDisabledAttribute())("aria-disabled",a.disabled),J("mdc-evolution-chip__action--primary",a._isPrimary)("mdc-evolution-chip__action--secondary",!a._isPrimary)("mdc-evolution-chip__action--trailing",!a._isPrimary&&!a._isLeading))},inputs:{disabled:[2,"disabled","disabled",Ae],tabIndex:[2,"tabIndex","tabIndex",e=>e==null?-1:Or(e)],_allowFocusWhenDisabled:"_allowFocusWhenDisabled"}})}return n})(),hM=(()=>{class n extends X_{_getTabindex(){return this.disabled&&!this._allowFocusWhenDisabled?null:this.tabIndex.toString()}_handleClick(e){!this.disabled&&this._isPrimary&&(e.preventDefault(),this._parentChip._handlePrimaryActionInteraction())}_handleKeydown(e){(e.keyCode===13||e.keyCode===32)&&!this.disabled&&this._isPrimary&&!this._parentChip._isEditing&&(e.preventDefault(),this._parentChip._handlePrimaryActionInteraction())}static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(n)))(a||n)}})();static \u0275dir=K({type:n,selectors:[["","matChipAction",""]],hostVars:3,hostBindings:function(i,a){i&1&&ye("click",function(o){return a._handleClick(o)})("keydown",function(o){return a._handleKeydown(o)}),i&2&&(fe("tabindex",a._getTabindex()),J("mdc-evolution-chip__action--presentational",!1))},features:[Ve]})}return n})(),Si=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["mat-chip-avatar"],["","matChipAvatar",""]],hostAttrs:["role","img",1,"mat-mdc-chip-avatar","mdc-evolution-chip__icon","mdc-evolution-chip__icon--primary"],features:[Qe([{provide:ru,useExisting:n}])]})}return n})();var Qn=(()=>{class n{_changeDetectorRef=p(et);_elementRef=p(X);_tagName=p(bh);_ngZone=p(W);_focusMonitor=p(Nn);_globalRippleOptions=p(qi,{optional:!0});_document=p(Y);_onFocus=new z;_onBlur=new z;_isBasicChip=!1;role=null;_hasFocusInternal=!1;_pendingFocus=!1;_actionChanges;_animationsDisabled=tt();_allLeadingIcons;_allTrailingIcons;_allEditIcons;_allRemoveIcons;_hasFocus(){return this._hasFocusInternal}id=p(St).getId("mat-mdc-chip-");ariaLabel=null;ariaDescription=null;_chipListDisabled=!1;_hadFocusOnRemove=!1;_textElement;get value(){return this._value!==void 0?this._value:this._textElement.textContent.trim()}set value(e){this._value=e}_value;color;removable=!0;highlighted=!1;disableRipple=!1;get disabled(){return this._disabled||this._chipListDisabled}set disabled(e){this._disabled=e}_disabled=!1;removed=new Te;destroyed=new Te;basicChipAttrName="mat-basic-chip";leadingIcon;editIcon;trailingIcon;removeIcon;primaryAction;_rippleLoader=p(Sl);_injector=p(ce);constructor(){let e=p(ct);e.load(un),e.load(Ea),this._monitorFocus(),this._rippleLoader?.configureRipple(this._elementRef.nativeElement,{className:"mat-mdc-chip-ripple",disabled:this._isRippleDisabled()})}ngOnInit(){this._isBasicChip=this._elementRef.nativeElement.hasAttribute(this.basicChipAttrName)||this._tagName.toLowerCase()===this.basicChipAttrName}ngAfterViewInit(){this._textElement=this._elementRef.nativeElement.querySelector(".mat-mdc-chip-action-label"),this._pendingFocus&&(this._pendingFocus=!1,this.focus())}ngAfterContentInit(){this._actionChanges=It(this._allLeadingIcons.changes,this._allTrailingIcons.changes,this._allEditIcons.changes,this._allRemoveIcons.changes).subscribe(()=>this._changeDetectorRef.markForCheck())}ngDoCheck(){this._rippleLoader.setDisabled(this._elementRef.nativeElement,this._isRippleDisabled())}ngOnDestroy(){this.destroyed.emit({chip:this}),this.destroyed.complete(),this._focusMonitor.stopMonitoring(this._elementRef),this._rippleLoader?.destroyRipple(this._elementRef.nativeElement),this._actionChanges?.unsubscribe()}remove(){this.removable&&(this._hadFocusOnRemove=this._hasFocus(),this.removed.emit({chip:this}))}_isRippleDisabled(){return this.disabled||this.disableRipple||this._animationsDisabled||this._isBasicChip||!this._hasInteractiveActions()||!!this._globalRippleOptions?.disabled}_hasTrailingIcon(){return!!(this.trailingIcon||this.removeIcon)}_handleKeydown(e){(e.keyCode===8&&!e.repeat||e.keyCode===46)&&(e.preventDefault(),this.remove())}focus(){this.disabled||(this.primaryAction?this.primaryAction.focus():this._pendingFocus=!0)}_getSourceAction(e){return this._getActions().find(i=>{let a=i._elementRef.nativeElement;return a===e||a.contains(e)})}_getActions(){let e=[];return this.editIcon&&e.push(this.editIcon),this.primaryAction&&e.push(this.primaryAction),this.removeIcon&&e.push(this.removeIcon),e}_handlePrimaryActionInteraction(){}_hasInteractiveActions(){return this._getActions().length>0}_edit(e){}_monitorFocus(){this._focusMonitor.monitor(this._elementRef,!0).subscribe(e=>{let i=e!==null;i!==this._hasFocusInternal&&(this._hasFocusInternal=i,i?this._onFocus.next({chip:this}):(this._changeDetectorRef.markForCheck(),setTimeout(()=>this._ngZone.run(()=>this._onBlur.next({chip:this})))))})}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=["*",[["mat-chip-avatar"],["","matChipAvatar",""]],[["mat-chip-trailing-icon"],["","matChipRemove",""],["","matChipTrailingIcon",""]]],i=["*","mat-chip-avatar, [matChipAvatar]","mat-chip-trailing-icon,[matChipRemove],[matChipTrailingIcon]"];function a(o,s){o&1&&(y(0,"span",3),j(1,1),v())}function r(o,s){o&1&&(y(0,"span",6),j(1,2),v())}return T({type:n,selectors:[["mat-basic-chip"],["","mat-basic-chip",""],["mat-chip"],["","mat-chip",""]],contentQueries:function(s,l,c){if(s&1&&ot(c,ru,5)(c,W_,5)(c,G_,5)(c,K_,5)(c,ru,5)(c,G_,5)(c,W_,5)(c,K_,5),s&2){let d;H(d=U())&&(l.leadingIcon=d.first),H(d=U())&&(l.editIcon=d.first),H(d=U())&&(l.trailingIcon=d.first),H(d=U())&&(l.removeIcon=d.first),H(d=U())&&(l._allLeadingIcons=d),H(d=U())&&(l._allTrailingIcons=d),H(d=U())&&(l._allEditIcons=d),H(d=U())&&(l._allRemoveIcons=d)}},viewQuery:function(s,l){if(s&1&&qe(hM,5),s&2){let c;H(c=U())&&(l.primaryAction=c.first)}},hostAttrs:[1,"mat-mdc-chip"],hostVars:31,hostBindings:function(s,l){s&1&&ye("keydown",function(d){return l._handleKeydown(d)}),s&2&&(oi("id",l.id),fe("role",l.role)("aria-label",l.ariaLabel),At("mat-"+(l.color||"primary")),J("mdc-evolution-chip",!l._isBasicChip)("mdc-evolution-chip--disabled",l.disabled)("mdc-evolution-chip--with-trailing-action",l._hasTrailingIcon())("mdc-evolution-chip--with-primary-graphic",l.leadingIcon)("mdc-evolution-chip--with-primary-icon",l.leadingIcon)("mdc-evolution-chip--with-avatar",l.leadingIcon)("mat-mdc-chip-with-avatar",l.leadingIcon)("mat-mdc-chip-highlighted",l.highlighted)("mat-mdc-chip-disabled",l.disabled)("mat-mdc-basic-chip",l._isBasicChip)("mat-mdc-standard-chip",!l._isBasicChip)("mat-mdc-chip-with-trailing-icon",l._hasTrailingIcon())("_mat-animation-noopable",l._animationsDisabled))},inputs:{role:"role",id:"id",ariaLabel:[0,"aria-label","ariaLabel"],ariaDescription:[0,"aria-description","ariaDescription"],value:"value",color:"color",removable:[2,"removable","removable",Ae],highlighted:[2,"highlighted","highlighted",Ae],disableRipple:[2,"disableRipple","disableRipple",Ae],disabled:[2,"disabled","disabled",Ae]},outputs:{removed:"removed",destroyed:"destroyed"},exportAs:["matChip"],features:[Qe([{provide:Y_,useExisting:n}])],ngContentSelectors:i,decls:8,vars:2,consts:[[1,"mat-mdc-chip-focus-overlay"],[1,"mdc-evolution-chip__cell","mdc-evolution-chip__cell--primary"],["matChipContent",""],[1,"mdc-evolution-chip__graphic","mat-mdc-chip-graphic"],[1,"mdc-evolution-chip__text-label","mat-mdc-chip-action-label"],[1,"mat-mdc-chip-primary-focus-indicator","mat-focus-indicator"],[1,"mdc-evolution-chip__cell","mdc-evolution-chip__cell--trailing"]],template:function(s,l){s&1&&(de(e),P(0,"span",0),y(1,"span",1)(2,"span",2),E(3,a,2,0,"span",3),y(4,"span",4),j(5),P(6,"span",5),v()()(),E(7,r,2,0,"span",6)),s&2&&(h(3),D(l.leadingIcon?3:-1),h(4),D(l._hasTrailingIcon()?7:-1))},dependencies:[X_],styles:[`.mdc-evolution-chip,
.mdc-evolution-chip__cell,
.mdc-evolution-chip__action {
  display: inline-flex;
  align-items: center;
}

.mdc-evolution-chip {
  position: relative;
  max-width: 100%;
}

.mdc-evolution-chip__cell,
.mdc-evolution-chip__action {
  height: 100%;
}

.mdc-evolution-chip__cell--primary {
  flex-basis: 100%;
  overflow-x: hidden;
}

.mdc-evolution-chip__cell--trailing {
  flex: 1 0 auto;
}

.mdc-evolution-chip__action {
  align-items: center;
  background: none;
  border: none;
  box-sizing: content-box;
  cursor: pointer;
  display: inline-flex;
  justify-content: center;
  outline: none;
  padding: 0;
  text-decoration: none;
  color: inherit;
}

.mdc-evolution-chip__action--presentational {
  cursor: auto;
}

.mdc-evolution-chip--disabled,
.mdc-evolution-chip__action:disabled {
  pointer-events: none;
}
@media (forced-colors: active) {
  .mdc-evolution-chip--disabled,
  .mdc-evolution-chip__action:disabled {
    forced-color-adjust: none;
  }
}

.mdc-evolution-chip__action--primary {
  font: inherit;
  letter-spacing: inherit;
  white-space: inherit;
  overflow-x: hidden;
}
.mat-mdc-standard-chip .mdc-evolution-chip__action--%NS%primary::before {
  border-width: var(--%NS%mat-chip-outline-width, 1px);
  border-radius: var(--%NS%mat-chip-container-shape-radius, 8px);
  box-sizing: border-box;
  content: "";
  height: 100%;
  left: 0;
  position: absolute;
  pointer-events: none;
  top: 0;
  width: 100%;
  z-index: 1;
  border-style: solid;
}
.mat-mdc-standard-chip .mdc-evolution-chip__action--primary {
  padding-left: 12px;
  padding-right: 12px;
}
.mat-mdc-standard-chip.mdc-evolution-chip--with-primary-graphic .mdc-evolution-chip__action--primary {
  padding-left: 0;
  padding-right: 12px;
}
[dir=rtl] .mat-mdc-standard-chip.mdc-evolution-chip--with-primary-graphic .mdc-evolution-chip__action--primary {
  padding-left: 12px;
  padding-right: 0;
}
.mat-mdc-standard-chip:not(.mdc-evolution-chip--disabled) .mdc-evolution-chip__action--%NS%primary::before {
  border-color: var(--%NS%mat-chip-outline-color, var(--%NS%mat-sys-outline));
}
.mdc-evolution-chip__action--%NS%primary:not(.mdc-evolution-chip__action--presentational):not(.mdc-ripple-upgraded):focus::before {
  border-color: var(--%NS%mat-chip-focus-outline-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled .mdc-evolution-chip__action--%NS%primary::before {
  border-color: var(--%NS%mat-chip-disabled-outline-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 12%, transparent));
}
.mat-mdc-standard-chip.mdc-evolution-chip--selected .mdc-evolution-chip__action--%NS%primary::before {
  border-width: var(--%NS%mat-chip-flat-selected-outline-width, 0);
}
.mat-mdc-basic-chip .mdc-evolution-chip__action--primary {
  font: inherit;
}
.mat-mdc-standard-chip.mdc-evolution-chip--with-leading-action .mdc-evolution-chip__action--primary {
  padding-left: 0;
  padding-right: 12px;
}
[dir=rtl] .mat-mdc-standard-chip.mdc-evolution-chip--with-leading-action .mdc-evolution-chip__action--primary {
  padding-left: 12px;
  padding-right: 0;
}
.mat-mdc-standard-chip.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--primary {
  padding-left: 12px;
  padding-right: 0;
}
[dir=rtl] .mat-mdc-standard-chip.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--primary {
  padding-left: 0;
  padding-right: 12px;
}
.mat-mdc-standard-chip.mdc-evolution-chip--with-leading-action.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--primary {
  padding-left: 0;
  padding-right: 0;
}
.mat-mdc-standard-chip.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--primary {
  padding-left: 0;
  padding-right: 0;
}
[dir=rtl] .mat-mdc-standard-chip.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--primary {
  padding-left: 0;
  padding-right: 0;
}
.mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic .mdc-evolution-chip__action--primary {
  padding-left: 0;
  padding-right: 12px;
}
[dir=rtl] .mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic .mdc-evolution-chip__action--primary {
  padding-left: 12px;
  padding-right: 0;
}
.mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--primary {
  padding-left: 0;
  padding-right: 0;
}
[dir=rtl] .mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--primary {
  padding-left: 0;
  padding-right: 0;
}

.mdc-evolution-chip__action--secondary {
  position: relative;
  overflow: visible;
}
.mat-mdc-standard-chip:not(.mdc-evolution-chip--disabled) .mdc-evolution-chip__action--secondary {
  color: var(--%NS%mat-chip-with-trailing-icon-trailing-icon-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled .mdc-evolution-chip__action--secondary {
  color: var(--%NS%mat-chip-with-trailing-icon-disabled-trailing-icon-color, var(--%NS%mat-sys-on-surface));
}
.mat-mdc-standard-chip.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--secondary, .mat-mdc-standard-chip.mdc-evolution-chip--with-leading-action .mdc-evolution-chip__action--secondary {
  padding-left: 8px;
  padding-right: 8px;
}
.mat-mdc-standard-chip.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--secondary, .mat-mdc-standard-chip.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-leading-action .mdc-evolution-chip__action--secondary {
  padding-left: 8px;
  padding-right: 8px;
}
.mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--secondary, .mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-leading-action .mdc-evolution-chip__action--secondary {
  padding-left: 8px;
  padding-right: 8px;
}
[dir=rtl] .mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--secondary, [dir=rtl] .mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-leading-action .mdc-evolution-chip__action--secondary {
  padding-left: 8px;
  padding-right: 8px;
}

.mdc-evolution-chip__text-label {
  -webkit-user-select: none;
  user-select: none;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}
.mat-mdc-standard-chip .mdc-evolution-chip__text-label {
  font-family: var(--%NS%mat-chip-label-text-font, var(--%NS%mat-sys-label-large-font));
  line-height: var(--%NS%mat-chip-label-text-line-height, var(--%NS%mat-sys-label-large-line-height));
  font-size: var(--%NS%mat-chip-label-text-size, var(--%NS%mat-sys-label-large-size));
  font-weight: var(--%NS%mat-chip-label-text-weight, var(--%NS%mat-sys-label-large-weight));
  letter-spacing: var(--%NS%mat-chip-label-text-tracking, var(--%NS%mat-sys-label-large-tracking));
}
.mat-mdc-standard-chip:not(.mdc-evolution-chip--disabled) .mdc-evolution-chip__text-label {
  color: var(--%NS%mat-chip-label-text-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-mdc-standard-chip.mdc-evolution-chip--%NS%selected:not(.mdc-evolution-chip--disabled) .mdc-evolution-chip__text-label {
  color: var(--%NS%mat-chip-selected-label-text-color, var(--%NS%mat-sys-on-secondary-container));
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled .mdc-evolution-chip__text-label, .mat-mdc-standard-chip.mdc-evolution-chip--selected.mdc-evolution-chip--disabled .mdc-evolution-chip__text-label {
  color: var(--%NS%mat-chip-disabled-label-text-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 38%, transparent));
}

.mdc-evolution-chip__graphic {
  align-items: center;
  display: inline-flex;
  justify-content: center;
  overflow: hidden;
  pointer-events: none;
  position: relative;
  flex: 1 0 auto;
}
.mat-mdc-standard-chip .mdc-evolution-chip__graphic {
  width: var(--%NS%mat-chip-with-avatar-avatar-size, 24px);
  height: var(--%NS%mat-chip-with-avatar-avatar-size, 24px);
  font-size: var(--%NS%mat-chip-with-avatar-avatar-size, 24px);
}
.mdc-evolution-chip--selecting .mdc-evolution-chip__graphic {
  transition: width 150ms 0ms cubic-bezier(0.4, 0, 0.2, 1);
}
.mdc-evolution-chip--%NS%selectable:not(.mdc-evolution-chip--selected):not(.mdc-evolution-chip--with-primary-icon) .mdc-evolution-chip__graphic {
  width: 0;
}
.mat-mdc-standard-chip.mdc-evolution-chip--with-primary-graphic .mdc-evolution-chip__graphic {
  padding-left: 6px;
  padding-right: 6px;
}
.mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic .mdc-evolution-chip__graphic {
  padding-left: 4px;
  padding-right: 8px;
}
[dir=rtl] .mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic .mdc-evolution-chip__graphic {
  padding-left: 8px;
  padding-right: 4px;
}
.mat-mdc-standard-chip.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__graphic {
  padding-left: 6px;
  padding-right: 6px;
}
.mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__graphic {
  padding-left: 4px;
  padding-right: 8px;
}
[dir=rtl] .mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__graphic {
  padding-left: 8px;
  padding-right: 4px;
}
.mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-leading-action .mdc-evolution-chip__graphic {
  padding-left: 0;
}

.mdc-evolution-chip__checkmark {
  position: absolute;
  opacity: 0;
  top: 50%;
  left: 50%;
  height: 20px;
  width: 20px;
}
.mat-mdc-standard-chip:not(.mdc-evolution-chip--disabled) .mdc-evolution-chip__checkmark {
  color: var(--%NS%mat-chip-with-icon-selected-icon-color, var(--%NS%mat-sys-on-secondary-container));
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled .mdc-evolution-chip__checkmark {
  color: var(--%NS%mat-chip-with-icon-disabled-icon-color, var(--%NS%mat-sys-on-surface));
}
.mdc-evolution-chip--selecting .mdc-evolution-chip__checkmark {
  transition: transform 150ms 0ms cubic-bezier(0.4, 0, 0.2, 1);
  transform: translate(-75%, -50%);
}
.mdc-evolution-chip--selected .mdc-evolution-chip__checkmark {
  transform: translate(-50%, -50%);
  opacity: 1;
}

.mdc-evolution-chip__checkmark-svg {
  display: block;
}

.mdc-evolution-chip__checkmark-path {
  stroke-width: 2px;
  stroke-dasharray: 29.7833385;
  stroke-dashoffset: 29.7833385;
  stroke: currentColor;
}
.mdc-evolution-chip--selecting .mdc-evolution-chip__checkmark-path {
  transition: stroke-dashoffset 150ms 45ms cubic-bezier(0.4, 0, 0.2, 1);
}
.mdc-evolution-chip--selected .mdc-evolution-chip__checkmark-path {
  stroke-dashoffset: 0;
}
@media (forced-colors: active) {
  .mdc-evolution-chip__checkmark-path {
    stroke: CanvasText !important;
  }
}

.mat-mdc-standard-chip .mdc-evolution-chip__icon--trailing {
  height: 18px;
  width: 18px;
  font-size: 18px;
}
.mdc-evolution-chip--disabled .mdc-evolution-chip__icon--trailing.mat-mdc-chip-remove {
  opacity: calc(var(--%NS%mat-chip-trailing-action-opacity, 1) * var(--%NS%mat-chip-with-trailing-icon-disabled-trailing-icon-opacity, 0.38));
}
.mdc-evolution-chip--disabled .mdc-evolution-chip__icon--trailing.mat-mdc-chip-remove:focus {
  opacity: calc(var(--%NS%mat-chip-trailing-action-focus-opacity, 1) * var(--%NS%mat-chip-with-trailing-icon-disabled-trailing-icon-opacity, 0.38));
}

.mat-mdc-standard-chip {
  border-radius: var(--%NS%mat-chip-container-shape-radius, 8px);
  height: var(--%NS%mat-chip-container-height, 32px);
}
.mat-mdc-standard-chip:not(.mdc-evolution-chip--disabled) {
  background-color: var(--%NS%mat-chip-elevated-container-color, transparent);
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled {
  background-color: var(--%NS%mat-chip-elevated-disabled-container-color);
}
.mat-mdc-standard-chip.mdc-evolution-chip--%NS%selected:not(.mdc-evolution-chip--disabled) {
  background-color: var(--%NS%mat-chip-elevated-selected-container-color, var(--%NS%mat-sys-secondary-container));
}
.mat-mdc-standard-chip.mdc-evolution-chip--selected.mdc-evolution-chip--disabled {
  background-color: var(--%NS%mat-chip-flat-disabled-selected-container-color, color-mix(in srgb, var(--%NS%mat-sys-on-surface) 12%, transparent));
}
@media (forced-colors: active) {
  .mat-mdc-standard-chip {
    outline: solid 1px;
  }
}

.mat-mdc-standard-chip .mdc-evolution-chip__icon--primary {
  border-radius: var(--%NS%mat-chip-with-avatar-avatar-shape-radius, 24px);
  width: var(--%NS%mat-chip-with-icon-icon-size, 18px);
  height: var(--%NS%mat-chip-with-icon-icon-size, 18px);
  font-size: var(--%NS%mat-chip-with-icon-icon-size, 18px);
}
.mdc-evolution-chip--selected .mdc-evolution-chip__icon--primary {
  opacity: 0;
}
.mat-mdc-standard-chip:not(.mdc-evolution-chip--disabled) .mdc-evolution-chip__icon--primary {
  color: var(--%NS%mat-chip-with-icon-icon-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled .mdc-evolution-chip__icon--primary {
  color: var(--%NS%mat-chip-with-icon-disabled-icon-color, var(--%NS%mat-sys-on-surface));
}

.mat-mdc-chip-highlighted {
  --%NS%mat-chip-with-icon-icon-color: var(--%NS%mat-chip-with-icon-selected-icon-color, var(--%NS%mat-sys-on-secondary-container));
  --%NS%mat-chip-elevated-container-color: var(--%NS%mat-chip-elevated-selected-container-color, var(--%NS%mat-sys-secondary-container));
  --%NS%mat-chip-label-text-color: var(--%NS%mat-chip-selected-label-text-color, var(--%NS%mat-sys-on-secondary-container));
  --%NS%mat-chip-outline-width: var(--%NS%mat-chip-flat-selected-outline-width, 0);
}

.mat-mdc-chip-focus-overlay {
  background: var(--%NS%mat-chip-focus-state-layer-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-mdc-chip-selected .mat-mdc-chip-focus-overlay, .mat-mdc-chip-highlighted .mat-mdc-chip-focus-overlay {
  background: var(--%NS%mat-chip-selected-focus-state-layer-color, var(--%NS%mat-sys-on-secondary-container));
}
.mat-mdc-chip:hover .mat-mdc-chip-focus-overlay {
  background: var(--%NS%mat-chip-hover-state-layer-color, var(--%NS%mat-sys-on-surface-variant));
  opacity: var(--%NS%mat-chip-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity));
}
.mat-mdc-chip-focus-overlay .mat-mdc-chip-selected:hover, .mat-mdc-chip-highlighted:hover .mat-mdc-chip-focus-overlay {
  background: var(--%NS%mat-chip-selected-hover-state-layer-color, var(--%NS%mat-sys-on-secondary-container));
  opacity: var(--%NS%mat-chip-selected-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity));
}
.mat-mdc-chip.cdk-focused .mat-mdc-chip-focus-overlay {
  background: var(--%NS%mat-chip-focus-state-layer-color, var(--%NS%mat-sys-on-surface-variant));
  opacity: var(--%NS%mat-chip-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity));
}
.mat-mdc-chip-selected.cdk-focused .mat-mdc-chip-focus-overlay, .mat-mdc-chip-highlighted.cdk-focused .mat-mdc-chip-focus-overlay {
  background: var(--%NS%mat-chip-selected-focus-state-layer-color, var(--%NS%mat-sys-on-secondary-container));
  opacity: var(--%NS%mat-chip-selected-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity));
}

.mdc-evolution-chip--%NS%disabled:not(.mdc-evolution-chip--selected) .mat-mdc-chip-avatar {
  opacity: var(--%NS%mat-chip-with-avatar-disabled-avatar-opacity, 0.38);
}

.mdc-evolution-chip--disabled .mdc-evolution-chip__icon--trailing {
  opacity: var(--%NS%mat-chip-with-trailing-icon-disabled-trailing-icon-opacity, 0.38);
}

.mdc-evolution-chip--disabled.mdc-evolution-chip--selected .mdc-evolution-chip__checkmark {
  opacity: var(--%NS%mat-chip-with-icon-disabled-icon-opacity, 0.38);
}

.mat-mdc-standard-chip.mdc-evolution-chip--disabled {
  opacity: var(--%NS%mat-chip-disabled-container-opacity, 1);
}
.mat-mdc-standard-chip.mdc-evolution-chip--selected .mdc-evolution-chip__icon--trailing, .mat-mdc-standard-chip.mat-mdc-chip-highlighted .mdc-evolution-chip__icon--trailing {
  color: var(--%NS%mat-chip-selected-trailing-icon-color, var(--%NS%mat-sys-on-secondary-container));
}
.mat-mdc-standard-chip.mdc-evolution-chip--selected.mdc-evolution-chip--disabled .mdc-evolution-chip__icon--trailing, .mat-mdc-standard-chip.mat-mdc-chip-highlighted.mdc-evolution-chip--disabled .mdc-evolution-chip__icon--trailing {
  color: var(--%NS%mat-chip-selected-disabled-trailing-icon-color, var(--%NS%mat-sys-on-surface));
}

.mat-mdc-chip-edit, .mat-mdc-chip-remove {
  opacity: var(--%NS%mat-chip-trailing-action-opacity, 1);
}
.mat-mdc-chip-edit:focus, .mat-mdc-chip-remove:focus {
  opacity: var(--%NS%mat-chip-trailing-action-focus-opacity, 1);
}
.mat-mdc-chip-edit::after, .mat-mdc-chip-remove::after {
  background-color: var(--%NS%mat-chip-trailing-action-state-layer-color, var(--%NS%mat-sys-on-surface-variant));
}
.mat-mdc-chip-edit:hover::after, .mat-mdc-chip-remove:hover::after {
  opacity: calc(var(--%NS%mat-chip-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity)) + var(--%NS%mat-chip-trailing-action-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity)));
}
.mat-mdc-chip-edit:focus::after, .mat-mdc-chip-remove:focus::after {
  opacity: calc(var(--%NS%mat-chip-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity)) + var(--%NS%mat-chip-trailing-action-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity)));
}

.mat-mdc-chip-selected .mat-mdc-chip-remove::after,
.mat-mdc-chip-highlighted .mat-mdc-chip-remove::after {
  background-color: var(--%NS%mat-chip-selected-trailing-action-state-layer-color, var(--%NS%mat-sys-on-secondary-container));
}

.mat-mdc-chip.cdk-focused .mat-mdc-chip-edit:focus::after, .mat-mdc-chip.cdk-focused .mat-mdc-chip-remove:focus::after {
  opacity: calc(var(--%NS%mat-chip-selected-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity)) + var(--%NS%mat-chip-trailing-action-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity)));
}
.mat-mdc-chip.cdk-focused .mat-mdc-chip-edit:hover::after, .mat-mdc-chip.cdk-focused .mat-mdc-chip-remove:hover::after {
  opacity: calc(var(--%NS%mat-chip-selected-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity)) + var(--%NS%mat-chip-trailing-action-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity)));
}

.mat-mdc-standard-chip {
  -webkit-tap-highlight-color: transparent;
}
.mat-mdc-standard-chip .mat-mdc-chip-graphic,
.mat-mdc-standard-chip .mat-mdc-chip-trailing-icon {
  box-sizing: content-box;
}
.mat-mdc-standard-chip._mat-animation-noopable,
.mat-mdc-standard-chip._mat-animation-noopable .mdc-evolution-chip__graphic,
.mat-mdc-standard-chip._mat-animation-noopable .mdc-evolution-chip__checkmark,
.mat-mdc-standard-chip._mat-animation-noopable .mdc-evolution-chip__checkmark-path {
  transition-duration: 1ms;
  animation-duration: 1ms;
}

.mat-mdc-chip-focus-overlay {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  pointer-events: none;
  opacity: 0;
  border-radius: inherit;
  transition: opacity 150ms linear;
}
._mat-animation-noopable .mat-mdc-chip-focus-overlay {
  transition: none;
}
.mat-mdc-basic-chip .mat-mdc-chip-focus-overlay {
  display: none;
}

.mat-mdc-chip .mat-ripple.mat-mdc-chip-ripple {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  pointer-events: none;
  border-radius: inherit;
}

.mat-mdc-chip-avatar {
  text-align: center;
  line-height: 1;
  color: var(--%NS%mat-chip-with-icon-icon-color, currentColor);
}

.mat-mdc-chip {
  position: relative;
  z-index: 0;
}

.mat-mdc-chip-action-label {
  text-align: left;
  z-index: 1;
}
[dir=rtl] .mat-mdc-chip-action-label {
  text-align: right;
}
.mat-mdc-chip.mdc-evolution-chip--with-trailing-action .mat-mdc-chip-action-label {
  position: relative;
}
.mat-mdc-chip-action-label .mat-mdc-chip-primary-focus-indicator {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  pointer-events: none;
}
.mat-mdc-chip-action-label .mat-focus-indicator::before {
  margin: calc(calc(var(--%NS%mat-focus-indicator-border-width, 3px) + 2px) * -1);
}

.mat-mdc-chip-edit::before, .mat-mdc-chip-remove::before {
  margin: calc(var(--%NS%mat-focus-indicator-border-width, 3px) * -1);
  left: 8px;
  right: 8px;
}
.mat-mdc-chip-edit::after, .mat-mdc-chip-remove::after {
  content: "";
  display: block;
  opacity: 0;
  position: absolute;
  top: -3px;
  bottom: -3px;
  left: 5px;
  right: 5px;
  border-radius: 50%;
  box-sizing: border-box;
  padding: 12px;
  margin: -12px;
  background-clip: content-box;
}
.mat-mdc-chip-edit .mat-icon, .mat-mdc-chip-remove .mat-icon {
  width: 18px;
  height: 18px;
  font-size: 18px;
  box-sizing: content-box;
}

.mat-chip-edit-input {
  cursor: text;
  display: inline-block;
  color: inherit;
  outline: 0;
}

@media (forced-colors: active) {
  .mat-mdc-chip-selected:not(.mat-mdc-chip-multiple) {
    outline-width: 3px;
  }
}

.mat-mdc-chip-action:focus-visible .mat-focus-indicator::before {
  content: "";
}

.mdc-evolution-chip__icon, .mat-mdc-chip-edit .mat-icon, .mat-mdc-chip-remove .mat-icon {
  min-height: fit-content;
}

img.mdc-evolution-chip__icon {
  min-height: 0;
}
`],encapsulation:2})})()}return n})();var lr=(()=>{class n{_elementRef=p(X);_changeDetectorRef=p(et);_dir=p(Tt,{optional:!0});_lastDestroyedFocusedChipIndex=null;_keyManager;_destroyed=new z;_defaultRole="presentation";get chipFocusChanges(){return this._getChipStream(e=>e._onFocus)}get chipDestroyedChanges(){return this._getChipStream(e=>e.destroyed)}get chipRemovedChanges(){return this._getChipStream(e=>e.removed)}get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._syncChipsState()}_disabled=!1;get empty(){return!this._chips||this._chips.length===0}get role(){return this._explicitRole?this._explicitRole:this.empty?null:this._defaultRole}tabIndex=0;set role(e){this._explicitRole=e}_explicitRole=null;get focused(){return this._hasFocusedChip()}_chips;_chipActions=new Bn;ngAfterViewInit(){this._setUpFocusManagement(),this._trackChipSetChanges(),this._trackDestroyedFocusedChip()}ngOnDestroy(){this._keyManager?.destroy(),this._chipActions.destroy(),this._destroyed.next(),this._destroyed.complete()}_hasFocusedChip(){return this._chips&&this._chips.some(e=>e._hasFocus())}_syncChipsState(){this._chips?.forEach(e=>{e._chipListDisabled=this._disabled,e._changeDetectorRef.markForCheck()})}focus(){}_handleKeydown(e){this._originatesFromChip(e)&&this._keyManager.onKeydown(e)}_isValidIndex(e){return e>=0&&e<this._chips.length}_allowFocusEscape(){let e=this._elementRef.nativeElement.tabIndex;e!==-1&&(this._elementRef.nativeElement.tabIndex=-1,setTimeout(()=>this._elementRef.nativeElement.tabIndex=e))}_getChipStream(e){return this._chips.changes.pipe(vt(null),Gt(()=>It(...this._chips.map(e))))}_originatesFromChip(e){let i=e.target;for(;i&&i!==this._elementRef.nativeElement;){if(i.classList.contains("mat-mdc-chip"))return!0;i=i.parentElement}return!1}_setUpFocusManagement(){this._chips.changes.pipe(vt(this._chips)).subscribe(e=>{let i=[];e.forEach(a=>a._getActions().forEach(r=>i.push(r))),this._chipActions.reset(i),this._chipActions.notifyOnChanges()}),this._keyManager=new Vi(this._chipActions).withVerticalOrientation().withHorizontalOrientation(this._dir?this._dir.value:"ltr").withHomeAndEnd().skipPredicate(e=>this._skipPredicate(e)),this.chipFocusChanges.pipe(Ie(this._destroyed)).subscribe(({chip:e})=>{let i=e._getSourceAction(document.activeElement);i&&this._keyManager.updateActiveItem(i)}),this._dir?.change.pipe(Ie(this._destroyed)).subscribe(e=>this._keyManager.withHorizontalOrientation(e))}_skipPredicate(e){return e.disabled}_trackChipSetChanges(){this._chips.changes.pipe(vt(null),Ie(this._destroyed)).subscribe(()=>{this.disabled&&Promise.resolve().then(()=>this._syncChipsState()),this._redirectDestroyedChipFocus()})}_trackDestroyedFocusedChip(){this.chipDestroyedChanges.pipe(Ie(this._destroyed)).subscribe(e=>{let a=this._chips.toArray().indexOf(e.chip),r=e.chip._hasFocus(),o=e.chip._hadFocusOnRemove&&this._keyManager.activeItem&&e.chip._getActions().includes(this._keyManager.activeItem),s=r||o;this._isValidIndex(a)&&s&&(this._lastDestroyedFocusedChipIndex=a)})}_redirectDestroyedChipFocus(){if(this._lastDestroyedFocusedChipIndex!=null){if(this._chips.length){let e=Math.min(this._lastDestroyedFocusedChipIndex,this._chips.length-1),i=this._chips.toArray()[e];i.disabled?this._chips.length===1?this.focus():this._keyManager.setPreviousItemActive():i.focus()}else this.focus();this._lastDestroyedFocusedChipIndex=null}}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){return T({type:n,selectors:[["mat-chip-set"]],contentQueries:function(a,r,o){if(a&1&&ot(o,Qn,5),a&2){let s;H(s=U())&&(r._chips=s)}},hostAttrs:[1,"mat-mdc-chip-set","mdc-evolution-chip-set"],hostVars:1,hostBindings:function(a,r){a&1&&ye("keydown",function(s){return r._handleKeydown(s)}),a&2&&fe("role",r.role)},inputs:{disabled:[2,"disabled","disabled",Ae],role:"role",tabIndex:[2,"tabIndex","tabIndex",i=>i==null?0:Or(i)]},ngContentSelectors:["*"],decls:2,vars:0,consts:[["role","presentation",1,"mdc-evolution-chip-set__chips"]],template:function(a,r){a&1&&(de(),Ze(0,"div",0),j(1),Je())},styles:[`.mat-mdc-chip-set {
  display: flex;
}
.mat-mdc-chip-set:focus {
  outline: none;
}
.mat-mdc-chip-set .mdc-evolution-chip-set__chips {
  min-width: 100%;
  margin-left: -8px;
  margin-right: 0;
}
.mat-mdc-chip-set .mdc-evolution-chip {
  margin: 4px 0 4px 8px;
}
[dir=rtl] .mat-mdc-chip-set .mdc-evolution-chip-set__chips {
  margin-left: 0;
  margin-right: -8px;
}
[dir=rtl] .mat-mdc-chip-set .mdc-evolution-chip {
  margin-left: 0;
  margin-right: 8px;
}

.mdc-evolution-chip-set__chips {
  display: flex;
  flex-flow: wrap;
  min-width: 0;
}

.mat-mdc-chip-set-stacked {
  flex-direction: column;
  align-items: flex-start;
}
.mat-mdc-chip-set-stacked .mat-mdc-chip {
  width: 100%;
}
.mat-mdc-chip-set-stacked .mdc-evolution-chip__graphic {
  flex-grow: 0;
}
.mat-mdc-chip-set-stacked .mdc-evolution-chip__action--primary {
  flex-basis: 100%;
  justify-content: start;
}

input.mat-mdc-chip-input {
  flex: 1 0 150px;
  margin-left: 8px;
}
[dir=rtl] input.mat-mdc-chip-input {
  margin-left: 0;
  margin-right: 8px;
}
.mat-mdc-form-field:not(.mat-form-field-hide-placeholder) input.mat-mdc-chip-input::placeholder {
  opacity: 1;
}
.mat-mdc-form-field:not(.mat-form-field-hide-placeholder) input.mat-mdc-chip-input::-moz-placeholder {
  opacity: 1;
}
.mat-mdc-form-field:not(.mat-form-field-hide-placeholder) input.mat-mdc-chip-input::-webkit-input-placeholder {
  opacity: 1;
}
.mat-mdc-form-field:not(.mat-form-field-hide-placeholder) input.mat-mdc-chip-input:-ms-input-placeholder {
  opacity: 1;
}
.mat-mdc-chip-set + input.mat-mdc-chip-input {
  margin-left: 0;
  margin-right: 0;
}
`],encapsulation:2})})()}return n})();var vn=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({providers:[q_,{provide:uM,useValue:{separatorKeyCodes:[13]}}],imports:[In,me]})}return n})();var fM=new I("ListOption"),su=(()=>{class n{_elementRef=p(X);static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","matListItemTitle",""]],hostAttrs:[1,"mat-mdc-list-item-title","mdc-list-item__primary-text"]})}return n})(),lu=(()=>{class n{_elementRef=p(X);static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","matListItemLine",""]],hostAttrs:[1,"mat-mdc-list-item-line","mdc-list-item__secondary-text"]})}return n})(),gM=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,selectors:[["","matListItemMeta",""]],hostAttrs:[1,"mat-mdc-list-item-meta","mdc-list-item__end"]})}return n})(),Z_=(()=>{class n{_listOption=p(fM,{optional:!0});_isAlignedAtStart(){return!this._listOption||this._listOption?._getTogglePosition()==="after"}static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,hostVars:4,hostBindings:function(i,a){i&2&&J("mdc-list-item__start",a._isAlignedAtStart())("mdc-list-item__end",!a._isAlignedAtStart())}})}return n})(),bM=(()=>{class n extends Z_{static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(n)))(a||n)}})();static \u0275dir=K({type:n,selectors:[["","matListItemAvatar",""]],hostAttrs:[1,"mat-mdc-list-item-avatar"],features:[Ve]})}return n})(),cu=(()=>{class n extends Z_{static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(n)))(a||n)}})();static \u0275dir=K({type:n,selectors:[["","matListItemIcon",""]],hostAttrs:[1,"mat-mdc-list-item-icon"],features:[Ve]})}return n})(),vM=new I("MAT_LIST_CONFIG"),ou=(()=>{class n{_isNonInteractive=!0;get disableRipple(){return this._disableRipple}set disableRipple(e){this._disableRipple=Rt(e)}_disableRipple=!1;get disabled(){return this._disabled()}set disabled(e){this._disabled.set(Rt(e))}_disabled=ne(!1);_defaultOptions=p(vM,{optional:!0});static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,hostVars:1,hostBindings:function(i,a){i&2&&fe("aria-disabled",a.disabled)},inputs:{disableRipple:"disableRipple",disabled:"disabled"}})}return n})(),_M=(()=>{class n{_elementRef=p(X);_ngZone=p(W);_listBase=p(ou,{optional:!0});_platform=p(Ne);_hostElement;_isButtonElement;_noopAnimations=tt();_avatars;_icons;set lines(e){this._explicitLines=zn(e,null),this._updateItemLines(!1)}_explicitLines=null;get disableRipple(){return this.disabled||this._disableRipple||this._noopAnimations||!!this._listBase?.disableRipple}set disableRipple(e){this._disableRipple=Rt(e)}_disableRipple=!1;get disabled(){return this._disabled()||!!this._listBase?.disabled}set disabled(e){this._disabled.set(Rt(e))}_disabled=ne(!1);_subscriptions=new bt;_rippleRenderer=null;_hasUnscopedTextContent=!1;rippleConfig;get rippleDisabled(){return this.disableRipple||!!this.rippleConfig.disabled}constructor(){p(ct).load(un);let e=p(qi,{optional:!0});this.rippleConfig=e||{},this._hostElement=this._elementRef.nativeElement,this._isButtonElement=this._hostElement.nodeName.toLowerCase()==="button",this._listBase&&!this._listBase._isNonInteractive&&this._initInteractiveListItem(),this._isButtonElement&&!this._hostElement.hasAttribute("type")&&this._hostElement.setAttribute("type","button")}ngAfterViewInit(){this._monitorProjectedLinesAndTitle(),this._updateItemLines(!0)}ngOnDestroy(){this._subscriptions.unsubscribe(),this._rippleRenderer!==null&&this._rippleRenderer._removeTriggerEvents()}_hasIconOrAvatar(){return!!(this._avatars.length||this._icons.length)}_initInteractiveListItem(){this._hostElement.classList.add("mat-mdc-list-item-interactive"),this._rippleRenderer=new Ui(this,this._ngZone,this._hostElement,this._platform,p(ce)),this._rippleRenderer.setupTriggerEvents(this._hostElement)}_monitorProjectedLinesAndTitle(){this._ngZone.runOutsideAngular(()=>{this._subscriptions.add(It(this._lines.changes,this._titles.changes).subscribe(()=>this._updateItemLines(!1)))})}_updateItemLines(e){if(!this._lines||!this._titles||!this._unscopedContent)return;e&&this._checkDomForUnscopedTextContent();let i=this._explicitLines??this._inferLinesFromContent(),a=this._unscopedContent.nativeElement;if(this._hostElement.classList.toggle("mat-mdc-list-item-single-line",i<=1),this._hostElement.classList.toggle("mdc-list-item--with-one-line",i<=1),this._hostElement.classList.toggle("mdc-list-item--with-two-lines",i===2),this._hostElement.classList.toggle("mdc-list-item--with-three-lines",i===3),this._hasUnscopedTextContent){let r=this._titles.length===0&&i===1;a.classList.toggle("mdc-list-item__primary-text",r),a.classList.toggle("mdc-list-item__secondary-text",!r)}else a.classList.remove("mdc-list-item__primary-text"),a.classList.remove("mdc-list-item__secondary-text")}_inferLinesFromContent(){let e=this._titles.length+this._lines.length;return this._hasUnscopedTextContent&&(e+=1),e}_checkDomForUnscopedTextContent(){this._hasUnscopedTextContent=Array.from(this._unscopedContent.nativeElement.childNodes).filter(e=>e.nodeType!==e.COMMENT_NODE).some(e=>!!(e.textContent&&e.textContent.trim()))}static \u0275fac=function(i){return new(i||n)};static \u0275dir=K({type:n,contentQueries:function(i,a,r){if(i&1&&ot(r,bM,4)(r,cu,4),i&2){let o;H(o=U())&&(a._avatars=o),H(o=U())&&(a._icons=o)}},hostVars:4,hostBindings:function(i,a){i&2&&(fe("aria-disabled",a.disabled)("disabled",a._isButtonElement&&a.disabled||null),J("mdc-list-item--disabled",a.disabled))},inputs:{lines:"lines",disableRipple:"disableRipple",disabled:"disabled"}})}return n})();var Q_=(()=>{class n extends ou{static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(n)))(a||n)}})();static \u0275cmp=(function(){let e=["*"];return T({type:n,selectors:[["mat-list"]],hostAttrs:[1,"mat-mdc-list","mat-mdc-list-base","mdc-list"],exportAs:["matList"],features:[Qe([{provide:ou,useExisting:n}]),Ve],ngContentSelectors:e,decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},styles:[`.mdc-list {
  margin: 0;
  padding: 8px 0;
  list-style-type: none;
}
.mdc-list:focus {
  outline: none;
}

.mdc-list-item {
  display: flex;
  position: relative;
  justify-content: flex-start;
  overflow: hidden;
  padding: 0;
  align-items: stretch;
  cursor: pointer;
  padding-left: 16px;
  padding-right: 16px;
  background-color: var(--%NS%mat-list-list-item-container-color, transparent);
  border-radius: var(--%NS%mat-list-list-item-container-shape, var(--%NS%mat-sys-corner-none));
}
.mdc-list-item.mdc-list-item--selected {
  background-color: var(--%NS%mat-list-list-item-selected-container-color);
}
.mdc-list-item:focus {
  outline: 0;
}
.mdc-list-item.mdc-list-item--disabled {
  cursor: auto;
}
.mdc-list-item.mdc-list-item--with-one-line {
  height: var(--%NS%mat-list-list-item-one-line-container-height, 48px);
}
.mdc-list-item.mdc-list-item--with-one-line .mdc-list-item__start {
  align-self: center;
  margin-top: 0;
}
.mdc-list-item.mdc-list-item--with-one-line .mdc-list-item__end {
  align-self: center;
  margin-top: 0;
}
.mdc-list-item.mdc-list-item--with-two-lines {
  height: var(--%NS%mat-list-list-item-two-line-container-height, 64px);
}
.mdc-list-item.mdc-list-item--with-two-lines .mdc-list-item__start {
  align-self: flex-start;
  margin-top: 16px;
}
.mdc-list-item.mdc-list-item--with-two-lines .mdc-list-item__end {
  align-self: center;
  margin-top: 0;
}
.mdc-list-item.mdc-list-item--with-three-lines {
  height: var(--%NS%mat-list-list-item-three-line-container-height, 88px);
}
.mdc-list-item.mdc-list-item--with-three-lines .mdc-list-item__start {
  align-self: flex-start;
  margin-top: 16px;
}
.mdc-list-item.mdc-list-item--with-three-lines .mdc-list-item__end {
  align-self: flex-start;
  margin-top: 16px;
}
.mdc-list-item.mdc-list-item--%NS%selected::before, .mdc-list-item.mdc-list-item--%NS%selected:focus::before, .mdc-list-item:not(.mdc-list-item--selected):focus::before {
  position: absolute;
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  content: "";
  pointer-events: none;
}

a.mdc-list-item {
  color: inherit;
  text-decoration: none;
}

.mdc-list-item__start {
  fill: currentColor;
  flex-shrink: 0;
  pointer-events: none;
}
.mdc-list-item--with-leading-icon .mdc-list-item__start {
  color: var(--%NS%mat-list-list-item-leading-icon-color, var(--%NS%mat-sys-on-surface-variant));
  width: var(--%NS%mat-list-list-item-leading-icon-size, 24px);
  height: var(--%NS%mat-list-list-item-leading-icon-size, 24px);
  margin-left: 16px;
  margin-right: 32px;
}
[dir=rtl] .mdc-list-item--with-leading-icon .mdc-list-item__start {
  margin-left: 32px;
  margin-right: 16px;
}
.mdc-list-item--%NS%with-leading-icon:hover .mdc-list-item__start {
  color: var(--%NS%mat-list-list-item-hover-leading-icon-color);
}
.mdc-list-item--with-leading-avatar .mdc-list-item__start {
  width: var(--%NS%mat-list-list-item-leading-avatar-size, 40px);
  height: var(--%NS%mat-list-list-item-leading-avatar-size, 40px);
  margin-left: 16px;
  margin-right: 16px;
  border-radius: 50%;
}
.mdc-list-item--with-leading-avatar .mdc-list-item__start, [dir=rtl] .mdc-list-item--with-leading-avatar .mdc-list-item__start {
  margin-left: 16px;
  margin-right: 16px;
  border-radius: 50%;
}

.mdc-list-item__end {
  flex-shrink: 0;
  pointer-events: none;
}
.mdc-list-item--with-trailing-meta .mdc-list-item__end {
  font-family: var(--%NS%mat-list-list-item-trailing-supporting-text-font, var(--%NS%mat-sys-label-small-font));
  line-height: var(--%NS%mat-list-list-item-trailing-supporting-text-line-height, var(--%NS%mat-sys-label-small-line-height));
  font-size: var(--%NS%mat-list-list-item-trailing-supporting-text-size, var(--%NS%mat-sys-label-small-size));
  font-weight: var(--%NS%mat-list-list-item-trailing-supporting-text-weight, var(--%NS%mat-sys-label-small-weight));
  letter-spacing: var(--%NS%mat-list-list-item-trailing-supporting-text-tracking, var(--%NS%mat-sys-label-small-tracking));
}
.mdc-list-item--with-trailing-icon .mdc-list-item__end {
  color: var(--%NS%mat-list-list-item-trailing-icon-color, var(--%NS%mat-sys-on-surface-variant));
  width: var(--%NS%mat-list-list-item-trailing-icon-size, 24px);
  height: var(--%NS%mat-list-list-item-trailing-icon-size, 24px);
}
.mdc-list-item--%NS%with-trailing-icon:hover .mdc-list-item__end {
  color: var(--%NS%mat-list-list-item-hover-trailing-icon-color);
}
.mdc-list-item.mdc-list-item--with-trailing-meta .mdc-list-item__end {
  color: var(--%NS%mat-list-list-item-trailing-supporting-text-color, var(--%NS%mat-sys-on-surface-variant));
}
.mdc-list-item--selected.mdc-list-item--with-trailing-icon .mdc-list-item__end {
  color: var(--%NS%mat-list-list-item-selected-trailing-icon-color, var(--%NS%mat-sys-primary));
}

.mdc-list-item__content {
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  align-self: center;
  flex: 1;
  pointer-events: none;
}
.mdc-list-item--with-two-lines .mdc-list-item__content, .mdc-list-item--with-three-lines .mdc-list-item__content {
  align-self: stretch;
}

.mdc-list-item__primary-text {
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  color: var(--%NS%mat-list-list-item-label-text-color, var(--%NS%mat-sys-on-surface));
  font-family: var(--%NS%mat-list-list-item-label-text-font, var(--%NS%mat-sys-body-large-font));
  line-height: var(--%NS%mat-list-list-item-label-text-line-height, var(--%NS%mat-sys-body-large-line-height));
  font-size: var(--%NS%mat-list-list-item-label-text-size, var(--%NS%mat-sys-body-large-size));
  font-weight: var(--%NS%mat-list-list-item-label-text-weight, var(--%NS%mat-sys-body-large-weight));
  letter-spacing: var(--%NS%mat-list-list-item-label-text-tracking, var(--%NS%mat-sys-body-large-tracking));
}
.mdc-list-item:hover .mdc-list-item__primary-text {
  color: var(--%NS%mat-list-list-item-hover-label-text-color, var(--%NS%mat-sys-on-surface));
}
.mdc-list-item:focus .mdc-list-item__primary-text {
  color: var(--%NS%mat-list-list-item-focus-label-text-color, var(--%NS%mat-sys-on-surface));
}
.mdc-list-item--with-two-lines .mdc-list-item__primary-text, .mdc-list-item--with-three-lines .mdc-list-item__primary-text {
  display: block;
  margin-top: 0;
  line-height: normal;
  margin-bottom: -20px;
}
.mdc-list-item--with-two-lines .mdc-list-item__primary-text::before, .mdc-list-item--with-three-lines .mdc-list-item__primary-text::before {
  display: inline-block;
  width: 0;
  height: 28px;
  content: "";
  vertical-align: 0;
}
.mdc-list-item--with-two-lines .mdc-list-item__primary-text::after, .mdc-list-item--with-three-lines .mdc-list-item__primary-text::after {
  display: inline-block;
  width: 0;
  height: 20px;
  content: "";
  vertical-align: -20px;
}

.mdc-list-item__secondary-text {
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  display: block;
  margin-top: 0;
  color: var(--%NS%mat-list-list-item-supporting-text-color, var(--%NS%mat-sys-on-surface-variant));
  font-family: var(--%NS%mat-list-list-item-supporting-text-font, var(--%NS%mat-sys-body-medium-font));
  line-height: var(--%NS%mat-list-list-item-supporting-text-line-height, var(--%NS%mat-sys-body-medium-line-height));
  font-size: var(--%NS%mat-list-list-item-supporting-text-size, var(--%NS%mat-sys-body-medium-size));
  font-weight: var(--%NS%mat-list-list-item-supporting-text-weight, var(--%NS%mat-sys-body-medium-weight));
  letter-spacing: var(--%NS%mat-list-list-item-supporting-text-tracking, var(--%NS%mat-sys-body-medium-tracking));
}
.mdc-list-item__secondary-text::before {
  display: inline-block;
  width: 0;
  height: 20px;
  content: "";
  vertical-align: 0;
}
.mdc-list-item--with-three-lines .mdc-list-item__secondary-text {
  white-space: normal;
  line-height: 20px;
}
.mdc-list-item--with-overline .mdc-list-item__secondary-text {
  white-space: nowrap;
  line-height: auto;
}

.mdc-list-item--with-leading-radio.mdc-list-item,
.mdc-list-item--with-leading-checkbox.mdc-list-item,
.mdc-list-item--with-leading-icon.mdc-list-item,
.mdc-list-item--with-leading-avatar.mdc-list-item {
  padding-left: 0;
  padding-right: 16px;
}
[dir=rtl] .mdc-list-item--with-leading-radio.mdc-list-item,
[dir=rtl] .mdc-list-item--with-leading-checkbox.mdc-list-item,
[dir=rtl] .mdc-list-item--with-leading-icon.mdc-list-item,
[dir=rtl] .mdc-list-item--with-leading-avatar.mdc-list-item {
  padding-left: 16px;
  padding-right: 0;
}
.mdc-list-item--with-leading-radio.mdc-list-item--with-two-lines .mdc-list-item__primary-text,
.mdc-list-item--with-leading-checkbox.mdc-list-item--with-two-lines .mdc-list-item__primary-text,
.mdc-list-item--with-leading-icon.mdc-list-item--with-two-lines .mdc-list-item__primary-text,
.mdc-list-item--with-leading-avatar.mdc-list-item--with-two-lines .mdc-list-item__primary-text {
  display: block;
  margin-top: 0;
  line-height: normal;
  margin-bottom: -20px;
}
.mdc-list-item--with-leading-radio.mdc-list-item--with-two-lines .mdc-list-item__primary-text::before,
.mdc-list-item--with-leading-checkbox.mdc-list-item--with-two-lines .mdc-list-item__primary-text::before,
.mdc-list-item--with-leading-icon.mdc-list-item--with-two-lines .mdc-list-item__primary-text::before,
.mdc-list-item--with-leading-avatar.mdc-list-item--with-two-lines .mdc-list-item__primary-text::before {
  display: inline-block;
  width: 0;
  height: 32px;
  content: "";
  vertical-align: 0;
}
.mdc-list-item--with-leading-radio.mdc-list-item--with-two-lines .mdc-list-item__primary-text::after,
.mdc-list-item--with-leading-checkbox.mdc-list-item--with-two-lines .mdc-list-item__primary-text::after,
.mdc-list-item--with-leading-icon.mdc-list-item--with-two-lines .mdc-list-item__primary-text::after,
.mdc-list-item--with-leading-avatar.mdc-list-item--with-two-lines .mdc-list-item__primary-text::after {
  display: inline-block;
  width: 0;
  height: 20px;
  content: "";
  vertical-align: -20px;
}
.mdc-list-item--with-leading-radio.mdc-list-item--with-two-lines.mdc-list-item--with-trailing-meta .mdc-list-item__end,
.mdc-list-item--with-leading-checkbox.mdc-list-item--with-two-lines.mdc-list-item--with-trailing-meta .mdc-list-item__end,
.mdc-list-item--with-leading-icon.mdc-list-item--with-two-lines.mdc-list-item--with-trailing-meta .mdc-list-item__end,
.mdc-list-item--with-leading-avatar.mdc-list-item--with-two-lines.mdc-list-item--with-trailing-meta .mdc-list-item__end {
  display: block;
  margin-top: 0;
  line-height: normal;
}
.mdc-list-item--with-leading-radio.mdc-list-item--with-two-lines.mdc-list-item--with-trailing-meta .mdc-list-item__end::before,
.mdc-list-item--with-leading-checkbox.mdc-list-item--with-two-lines.mdc-list-item--with-trailing-meta .mdc-list-item__end::before,
.mdc-list-item--with-leading-icon.mdc-list-item--with-two-lines.mdc-list-item--with-trailing-meta .mdc-list-item__end::before,
.mdc-list-item--with-leading-avatar.mdc-list-item--with-two-lines.mdc-list-item--with-trailing-meta .mdc-list-item__end::before {
  display: inline-block;
  width: 0;
  height: 32px;
  content: "";
  vertical-align: 0;
}

.mdc-list-item--with-trailing-icon.mdc-list-item, [dir=rtl] .mdc-list-item--with-trailing-icon.mdc-list-item {
  padding-left: 0;
  padding-right: 0;
}
.mdc-list-item--with-trailing-icon .mdc-list-item__end {
  margin-left: 16px;
  margin-right: 16px;
}

.mdc-list-item--with-trailing-meta.mdc-list-item {
  padding-left: 16px;
  padding-right: 0;
}
[dir=rtl] .mdc-list-item--with-trailing-meta.mdc-list-item {
  padding-left: 0;
  padding-right: 16px;
}
.mdc-list-item--with-trailing-meta .mdc-list-item__end {
  -webkit-user-select: none;
  user-select: none;
  margin-left: 28px;
  margin-right: 16px;
}
[dir=rtl] .mdc-list-item--with-trailing-meta .mdc-list-item__end {
  margin-left: 16px;
  margin-right: 28px;
}
.mdc-list-item--with-trailing-meta.mdc-list-item--with-three-lines .mdc-list-item__end, .mdc-list-item--with-trailing-meta.mdc-list-item--with-two-lines .mdc-list-item__end {
  display: block;
  line-height: normal;
  align-self: flex-start;
  margin-top: 0;
}
.mdc-list-item--with-trailing-meta.mdc-list-item--with-three-lines .mdc-list-item__end::before, .mdc-list-item--with-trailing-meta.mdc-list-item--with-two-lines .mdc-list-item__end::before {
  display: inline-block;
  width: 0;
  height: 28px;
  content: "";
  vertical-align: 0;
}

.mdc-list-item--with-leading-radio .mdc-list-item__start,
.mdc-list-item--with-leading-checkbox .mdc-list-item__start {
  margin-left: 8px;
  margin-right: 24px;
}
[dir=rtl] .mdc-list-item--with-leading-radio .mdc-list-item__start,
[dir=rtl] .mdc-list-item--with-leading-checkbox .mdc-list-item__start {
  margin-left: 24px;
  margin-right: 8px;
}
.mdc-list-item--with-leading-radio.mdc-list-item--with-two-lines .mdc-list-item__start,
.mdc-list-item--with-leading-checkbox.mdc-list-item--with-two-lines .mdc-list-item__start {
  align-self: flex-start;
  margin-top: 8px;
}

.mdc-list-item--with-trailing-radio.mdc-list-item,
.mdc-list-item--with-trailing-checkbox.mdc-list-item {
  padding-left: 16px;
  padding-right: 0;
}
[dir=rtl] .mdc-list-item--with-trailing-radio.mdc-list-item,
[dir=rtl] .mdc-list-item--with-trailing-checkbox.mdc-list-item {
  padding-left: 0;
  padding-right: 16px;
}
.mdc-list-item--with-trailing-radio.mdc-list-item--with-leading-icon, .mdc-list-item--with-trailing-radio.mdc-list-item--with-leading-avatar,
.mdc-list-item--with-trailing-checkbox.mdc-list-item--with-leading-icon,
.mdc-list-item--with-trailing-checkbox.mdc-list-item--with-leading-avatar {
  padding-left: 0;
}
[dir=rtl] .mdc-list-item--with-trailing-radio.mdc-list-item--with-leading-icon, [dir=rtl] .mdc-list-item--with-trailing-radio.mdc-list-item--with-leading-avatar,
[dir=rtl] .mdc-list-item--with-trailing-checkbox.mdc-list-item--with-leading-icon,
[dir=rtl] .mdc-list-item--with-trailing-checkbox.mdc-list-item--with-leading-avatar {
  padding-right: 0;
}
.mdc-list-item--with-trailing-radio .mdc-list-item__end,
.mdc-list-item--with-trailing-checkbox .mdc-list-item__end {
  margin-left: 24px;
  margin-right: 8px;
}
[dir=rtl] .mdc-list-item--with-trailing-radio .mdc-list-item__end,
[dir=rtl] .mdc-list-item--with-trailing-checkbox .mdc-list-item__end {
  margin-left: 8px;
  margin-right: 24px;
}
.mdc-list-item--with-trailing-radio.mdc-list-item--with-three-lines .mdc-list-item__end,
.mdc-list-item--with-trailing-checkbox.mdc-list-item--with-three-lines .mdc-list-item__end {
  align-self: flex-start;
  margin-top: 8px;
}

.mdc-list-group__subheader {
  margin: 0.75rem 16px;
}

.mdc-list-item--disabled .mdc-list-item__start,
.mdc-list-item--disabled .mdc-list-item__content,
.mdc-list-item--disabled .mdc-list-item__end {
  opacity: 1;
}
.mdc-list-item--disabled .mdc-list-item__primary-text,
.mdc-list-item--disabled .mdc-list-item__secondary-text {
  opacity: var(--%NS%mat-list-list-item-disabled-label-text-opacity, 0.3);
}
.mdc-list-item--disabled.mdc-list-item--with-leading-icon .mdc-list-item__start {
  color: var(--%NS%mat-list-list-item-disabled-leading-icon-color, var(--%NS%mat-sys-on-surface));
  opacity: var(--%NS%mat-list-list-item-disabled-leading-icon-opacity, 0.38);
}
.mdc-list-item--disabled.mdc-list-item--with-trailing-icon .mdc-list-item__end {
  color: var(--%NS%mat-list-list-item-disabled-trailing-icon-color, var(--%NS%mat-sys-on-surface));
  opacity: var(--%NS%mat-list-list-item-disabled-trailing-icon-opacity, 0.38);
}

.mat-mdc-list-item.mat-mdc-list-item-both-leading-and-trailing, [dir=rtl] .mat-mdc-list-item.mat-mdc-list-item-both-leading-and-trailing {
  padding-left: 0;
  padding-right: 0;
}

.mdc-list-item.mdc-list-item--disabled .mdc-list-item__primary-text {
  color: var(--%NS%mat-list-list-item-disabled-label-text-color, var(--%NS%mat-sys-on-surface));
}

.mdc-list-item:hover::before {
  background-color: var(--%NS%mat-list-list-item-hover-state-layer-color, var(--%NS%mat-sys-on-surface));
  opacity: var(--%NS%mat-list-list-item-hover-state-layer-opacity, var(--%NS%mat-sys-hover-state-layer-opacity));
}

.mdc-list-item.mdc-list-item--%NS%disabled::before {
  background-color: var(--%NS%mat-list-list-item-disabled-state-layer-color, var(--%NS%mat-sys-on-surface));
  opacity: var(--%NS%mat-list-list-item-disabled-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity));
}

.mdc-list-item:focus::before {
  background-color: var(--%NS%mat-list-list-item-focus-state-layer-color, var(--%NS%mat-sys-on-surface));
  opacity: var(--%NS%mat-list-list-item-focus-state-layer-opacity, var(--%NS%mat-sys-focus-state-layer-opacity));
}

.mdc-list-item--disabled .mdc-radio,
.mdc-list-item--disabled .mdc-checkbox {
  opacity: var(--%NS%mat-list-list-item-disabled-label-text-opacity, 0.3);
}

.mdc-list-item--with-leading-avatar .mat-mdc-list-item-avatar {
  border-radius: var(--%NS%mat-list-list-item-leading-avatar-shape, var(--%NS%mat-sys-corner-full));
  background-color: var(--%NS%mat-list-list-item-leading-avatar-color, var(--%NS%mat-sys-primary-container));
}

.mat-mdc-list-item-icon {
  font-size: var(--%NS%mat-list-list-item-leading-icon-size, 24px);
}

@media (forced-colors: active) {
  a.mdc-list-item--%NS%activated::after {
    content: "";
    position: absolute;
    top: 50%;
    right: 16px;
    transform: translateY(-50%);
    width: 10px;
    height: 0;
    border-bottom: solid 10px;
    border-radius: 10px;
  }
  a.mdc-list-item--activated [dir=rtl]::after {
    right: auto;
    left: 16px;
  }
}

.mat-mdc-list-base {
  display: block;
}
.mat-mdc-list-base .mdc-list-item__start,
.mat-mdc-list-base .mdc-list-item__end,
.mat-mdc-list-base .mdc-list-item__content {
  pointer-events: auto;
}

.mat-mdc-list-item,
.mat-mdc-list-option {
  width: 100%;
  box-sizing: border-box;
  -webkit-tap-highlight-color: transparent;
}
.mat-mdc-list-item:not(.mat-mdc-list-item-interactive),
.mat-mdc-list-option:not(.mat-mdc-list-item-interactive) {
  cursor: default;
}
.mat-mdc-list-item .mat-divider-inset,
.mat-mdc-list-option .mat-divider-inset {
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
}
.mat-mdc-list-item .mat-mdc-list-item-avatar ~ .mat-divider-inset,
.mat-mdc-list-option .mat-mdc-list-item-avatar ~ .mat-divider-inset {
  margin-left: 72px;
}
[dir=rtl] .mat-mdc-list-item .mat-mdc-list-item-avatar ~ .mat-divider-inset,
[dir=rtl] .mat-mdc-list-option .mat-mdc-list-item-avatar ~ .mat-divider-inset {
  margin-right: 72px;
}

.mat-mdc-list-item-interactive::before {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  content: "";
  opacity: 0;
  pointer-events: none;
  border-radius: inherit;
}

.mat-mdc-list-item > .mat-focus-indicator {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  pointer-events: none;
}
.mat-mdc-list-item:focus-visible > .mat-focus-indicator::before {
  content: "";
}

.mat-mdc-list-item.mdc-list-item--with-three-lines .mat-mdc-list-item-line.mdc-list-item__secondary-text {
  white-space: nowrap;
  line-height: normal;
}
.mat-mdc-list-item.mdc-list-item--with-three-lines .mat-mdc-list-item-unscoped-content.mdc-list-item__secondary-text {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
}

mat-action-list button {
  background: none;
  color: inherit;
  border: none;
  font: inherit;
  outline: inherit;
  -webkit-tap-highlight-color: transparent;
  text-align: start;
}
mat-action-list button::-moz-focus-inner {
  border: 0;
}

.mdc-list-item--with-leading-icon .mdc-list-item__start {
  margin-inline-start: var(--%NS%mat-list-list-item-leading-icon-start-space, 16px);
  margin-inline-end: var(--%NS%mat-list-list-item-leading-icon-end-space, 16px);
}

.mat-mdc-nav-list .mat-mdc-list-item {
  border-radius: var(--%NS%mat-list-active-indicator-shape, var(--%NS%mat-sys-corner-full));
  --%NS%mat-focus-indicator-border-radius: var(--%NS%mat-list-active-indicator-shape, var(--%NS%mat-sys-corner-full));
}
.mat-mdc-nav-list .mat-mdc-list-item.mdc-list-item--activated {
  background-color: var(--%NS%mat-list-active-indicator-color, var(--%NS%mat-sys-secondary-container));
}
`],encapsulation:2})})()}return n})(),J_=(()=>{class n extends _M{_lines;_titles;_meta;_unscopedContent;_itemText;get activated(){return this._activated}set activated(e){this._activated=Rt(e)}_activated=!1;_getAriaCurrent(){return this._hostElement.nodeName==="A"&&this._activated?"page":null}_hasBothLeadingAndTrailing(){return this._meta.length!==0&&(this._avatars.length!==0||this._icons.length!==0)}static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(n)))(a||n)}})();static \u0275cmp=(function(){let e=["unscopedContent"],i=["text"],a=[[["","matListItemAvatar",""],["","matListItemIcon",""]],[["","matListItemTitle",""]],[["","matListItemLine",""]],"*",[["","matListItemMeta",""]],[["mat-divider"]]];return T({type:n,selectors:[["mat-list-item"],["a","mat-list-item",""],["button","mat-list-item",""]],contentQueries:function(s,l,c){if(s&1&&ot(c,lu,5)(c,su,5)(c,gM,5),s&2){let d;H(d=U())&&(l._lines=d),H(d=U())&&(l._titles=d),H(d=U())&&(l._meta=d)}},viewQuery:function(s,l){if(s&1&&qe(e,5)(i,5),s&2){let c;H(c=U())&&(l._unscopedContent=c.first),H(c=U())&&(l._itemText=c.first)}},hostAttrs:[1,"mat-mdc-list-item","mdc-list-item"],hostVars:13,hostBindings:function(s,l){s&2&&(fe("aria-current",l._getAriaCurrent()),J("mdc-list-item--activated",l.activated)("mdc-list-item--with-leading-avatar",l._avatars.length!==0)("mdc-list-item--with-leading-icon",l._icons.length!==0)("mdc-list-item--with-trailing-meta",l._meta.length!==0)("mat-mdc-list-item-both-leading-and-trailing",l._hasBothLeadingAndTrailing())("_mat-animation-noopable",l._noopAnimations))},inputs:{activated:"activated"},exportAs:["matListItem"],features:[Ve],ngContentSelectors:["[matListItemAvatar],[matListItemIcon]","[matListItemTitle]","[matListItemLine]","*","[matListItemMeta]","mat-divider"],decls:10,vars:0,consts:[["unscopedContent",""],[1,"mdc-list-item__content"],[1,"mat-mdc-list-item-unscoped-content",3,"cdkObserveContent"],[1,"mat-focus-indicator"]],template:function(s,l){s&1&&(de(a),j(0),y(1,"span",1),j(2,1),j(3,2),y(4,"span",2,0),ye("cdkObserveContent",function(){return l._updateItemLines(!0)}),j(6,3),v()(),j(7,4),j(8,5),P(9,"div",3))},dependencies:[Zf],encapsulation:2})})()}return n})();var Wo=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[il,In,dc,me,zo]})}return n})();var xM=new I("MAT_DRAWER_DEFAULT_AUTOSIZE",{providedIn:"root",factory:()=>!1}),pu=new I("MAT_DRAWER_CONTAINER"),Ko=(()=>{class n extends Aa{_platform=p(Ne);_changeDetectorRef=p(et);_element=p(X);_ngZone=p(W);_isInert=!1;_container=p(mu);ngAfterContentInit(){this._container._contentMarginChanges.subscribe(()=>this._changeDetectorRef.markForCheck())}_drawerToggled(e){e.opened?this._ngZone.runOutsideAngular(()=>{e._animationEnd.pipe(Zu(50),Pn(1)).subscribe(()=>this._updateInert())}):this._updateInert()}_drawerModeChanged(){this._updateInert()}_updateInert(){let e=this._container._isShowingBackdrop();if(e!==this._isInert){let i=this._element.nativeElement;this._isInert=e,e?i.setAttribute("inert","true"):i.removeAttribute("inert")}}_shouldBeHidden(){if(this._platform.isBrowser)return!1;let{start:e,end:i}=this._container;return e!=null&&e.mode!=="over"&&e.opened||i!=null&&i.mode!=="over"&&i.opened}static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(n)))(a||n)}})();static \u0275cmp=(function(){let e=["*"];return T({type:n,selectors:[["mat-drawer-content"]],hostAttrs:[1,"mat-drawer-content"],hostVars:6,hostBindings:function(a,r){a&2&&(Ni("margin-left",r._container._contentMargins.left,"px")("margin-right",r._container._contentMargins.right,"px"),J("mat-drawer-content-hidden",r._shouldBeHidden()))},features:[Qe([{provide:Aa,useExisting:n}]),Ve],ngContentSelectors:e,decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},encapsulation:2})})()}return n})(),du=(()=>{class n{_elementRef=p(X);_focusTrapFactory=p(Ld);_focusMonitor=p(Nn);_platform=p(Ne);_ngZone=p(W);_renderer=p(pt);_interactivityChecker=p(rl);_doc=p(Y);_isAnimating=!1;_container=p(pu,{optional:!0});_focusTrap=null;_elementFocusedBeforeDrawerWasOpened=null;_eventCleanups;_isAttached=!1;_anchor=null;get position(){return this._position}set position(e){e=e==="end"?"end":"start",e!==this._position&&(this._isAttached&&this._updatePositionInParent(e),this._position=e,this.onPositionChanged.emit())}_position="start";get mode(){return this._mode}set mode(e){this._mode=e,this._updateFocusTrapState(),this._modeChanged.next(),this._getContent()?._drawerModeChanged()}_mode="over";get disableClose(){return this._disableClose}set disableClose(e){this._disableClose=Rt(e)}_disableClose=!1;get autoFocus(){let e=this._autoFocus;return e??(this.mode==="side"?"dialog":"first-tabbable")}set autoFocus(e){(e==="true"||e==="false"||e==null)&&(e=Rt(e)),this._autoFocus=e}_autoFocus;get opened(){return this._opened()}set opened(e){this.toggle(Rt(e))}_opened=ne(!1);_openedVia=null;_animationStarted=new z;_animationEnd=new z;openedChange=new Te(!0);_openedStream=this.openedChange.pipe(We(e=>e),De(()=>{}));openedStart=this._animationStarted.pipe(We(()=>this.opened),xr(void 0));_closedStream=this.openedChange.pipe(We(e=>!e),De(()=>{}));closedStart=this._animationStarted.pipe(We(()=>!this.opened),xr(void 0));_destroyed=new z;onPositionChanged=new Te;_content;_modeChanged=new z;_injector=p(ce);_changeDetectorRef=p(et);constructor(){this.openedChange.pipe(Ie(this._destroyed)).subscribe(e=>{e?(this._elementFocusedBeforeDrawerWasOpened=this._doc.activeElement,this._takeFocus()):this._isFocusWithinDrawer()&&this._restoreFocus(this._openedVia||"program")}),this._eventCleanups=this._ngZone.runOutsideAngular(()=>{let e=this._renderer,i=this._elementRef.nativeElement;return[e.listen(i,"keydown",a=>{a.keyCode===27&&!this.disableClose&&!ui(a)&&this._ngZone.run(()=>{this.close(),a.stopPropagation(),a.preventDefault()})}),e.listen(i,"transitionend",this._handleTransitionEvent),e.listen(i,"transitioncancel",this._handleTransitionEvent)]}),this._animationEnd.subscribe(()=>{this.openedChange.emit(this.opened)})}_focusByCssSelector(e,i){let a=this._elementRef.nativeElement.querySelector(e);a&&(this._interactivityChecker.isFocusable(a)||(a.tabIndex=-1,this._ngZone.runOutsideAngular(()=>{let r=()=>{o(),s(),a.removeAttribute("tabindex")},o=this._renderer.listen(a,"blur",r),s=this._renderer.listen(a,"mousedown",r)})),a.focus(i))}_takeFocus(){if(!this._focusTrap)return;let e=this._elementRef.nativeElement;switch(this.autoFocus){case!1:case"dialog":return;case!0:case"first-tabbable":yt(()=>{let i=this._isAnimating?{preventScroll:!0}:void 0;!this._focusTrap.focusInitialElement(i)&&typeof e.focus=="function"&&e.focus(i)},{injector:this._injector});break;case"first-heading":this._focusByCssSelector('h1, h2, h3, h4, h5, h6, [role="heading"]');break;default:this._focusByCssSelector(this.autoFocus);break}}_restoreFocus(e){this.autoFocus!=="dialog"&&(this._elementFocusedBeforeDrawerWasOpened?this._focusMonitor.focusVia(this._elementFocusedBeforeDrawerWasOpened,e):this._elementRef.nativeElement.blur(),this._elementFocusedBeforeDrawerWasOpened=null)}_isFocusWithinDrawer(){let e=this._doc.activeElement;return!!e&&this._elementRef.nativeElement.contains(e)}ngAfterViewInit(){this._isAttached=!0,this._position==="end"&&this._updatePositionInParent("end"),this._platform.isBrowser&&(this._focusTrap=this._focusTrapFactory.create(this._elementRef.nativeElement),this._updateFocusTrapState())}ngOnDestroy(){this._eventCleanups.forEach(e=>e()),this._focusTrap?.destroy(),this._anchor?.remove(),this._anchor=null,this._animationStarted.complete(),this._animationEnd.complete(),this._modeChanged.complete(),this._destroyed.next(),this._destroyed.complete()}open(e){return this.toggle(!0,e)}close(){return this.toggle(!1)}_closeViaBackdropClick(){return this._setOpen(!1,!0,"mouse")}toggle(e=!this.opened,i){e&&i&&(this._openedVia=i);let a=this._setOpen(e,!e&&this._isFocusWithinDrawer(),this._openedVia||"program");return e||(this._openedVia=null),a}_setOpen(e,i,a){return e===this.opened?Promise.resolve(e?"open":"close"):(this._opened.set(e),this._getContent()?._drawerToggled(this),this._container?._transitionsEnabled?this._isAnimating?(this._setIsAnimating(!1),this._simulateAnimation()):(this._setIsAnimating(!0),setTimeout(()=>this._animationStarted.next())):this._simulateAnimation(),this._elementRef.nativeElement.classList.toggle("mat-drawer-opened",e),!e&&i&&this._restoreFocus(a),this._changeDetectorRef.markForCheck(),this._updateFocusTrapState(),new Promise(r=>{this.openedChange.pipe(Pn(1)).subscribe(o=>r(o?"open":"close"))}))}_getContent(){return this._container?._content||this._container?._userContent}_setIsAnimating(e){e!==this._isAnimating&&(this._isAnimating=e,this._elementRef.nativeElement.classList.toggle("mat-drawer-animating",e))}_simulateAnimation(){setTimeout(()=>{this._animationStarted.next(),this._animationEnd.next()})}_getWidth(){return this._elementRef.nativeElement.offsetWidth||0}_updateFocusTrapState(){this._focusTrap&&(this._focusTrap.enabled=this.opened&&!!this._container?._isShowingBackdrop())}_updatePositionInParent(e){if(!this._platform.isBrowser)return;let i=this._elementRef.nativeElement,a=i.parentNode;e==="end"?(this._anchor||(this._anchor=this._doc.createComment("mat-drawer-anchor"),a.insertBefore(this._anchor,i)),a.appendChild(i)):this._anchor&&this._anchor.parentNode.insertBefore(i,this._anchor)}_handleTransitionEvent=e=>{let i=this._elementRef.nativeElement;e.target===i&&this._ngZone.run(()=>{e.type==="transitionend"&&this._setIsAnimating(!1),this._animationEnd.next(e)})};static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=["content"];return T({type:n,selectors:[["mat-drawer"]],viewQuery:function(r,o){if(r&1&&qe(e,5),r&2){let s;H(s=U())&&(o._content=s.first)}},hostAttrs:[1,"mat-drawer"],hostVars:12,hostBindings:function(r,o){r&2&&(fe("align",null)("tabIndex",o.mode!=="side"?"-1":null),Ni("visibility",!o._container&&!o.opened?"hidden":null),J("mat-drawer-end",o.position==="end")("mat-drawer-over",o.mode==="over")("mat-drawer-push",o.mode==="push")("mat-drawer-side",o.mode==="side"))},inputs:{position:"position",mode:"mode",disableClose:"disableClose",autoFocus:"autoFocus",opened:"opened"},outputs:{openedChange:"openedChange",_openedStream:"opened",openedStart:"openedStart",_closedStream:"closed",closedStart:"closedStart",onPositionChanged:"positionChanged"},exportAs:["matDrawer"],ngContentSelectors:["*"],decls:3,vars:0,consts:[["content",""],["cdkScrollable","",1,"mat-drawer-inner-container"]],template:function(r,o){r&1&&(de(),y(0,"div",1,0),j(2),v())},dependencies:[Aa],encapsulation:2})})()}return n})(),mu=(()=>{class n{_dir=p(Tt,{optional:!0});_element=p(X);_ngZone=p(W);_changeDetectorRef=p(et);_animationDisabled=tt();_transitionsEnabled=!1;_allDrawers;_drawers=new Bn;_content;_userContent;get start(){return this._start}get end(){return this._end}get autosize(){return this._autosize}set autosize(e){this._autosize=Rt(e)}_autosize=p(xM);get hasBackdrop(){return this._drawerHasBackdrop(this._start)||this._drawerHasBackdrop(this._end)}set hasBackdrop(e){this._backdropOverride=e==null?null:Rt(e)}_backdropOverride=null;backdropClick=new Te;_start=null;_end=null;_left=null;_right=null;_destroyed=new z;_doCheckSubject=new z;_contentMargins={left:null,right:null};_contentMarginChanges=new z;get scrollable(){return this._userContent||this._content}_injector=p(ce);constructor(){let e=p(Ne),i=p(Un);this._dir?.change.pipe(Ie(this._destroyed)).subscribe(()=>{this._validateDrawers(),this.updateContentMargins()}),i.change().pipe(Ie(this._destroyed)).subscribe(()=>this.updateContentMargins()),!this._animationDisabled&&e.isBrowser&&this._ngZone.runOutsideAngular(()=>{setTimeout(()=>{this._element.nativeElement.classList.add("mat-drawer-transition"),this._transitionsEnabled=!0},200)})}ngAfterContentInit(){this._allDrawers.changes.pipe(vt(this._allDrawers),Ie(this._destroyed)).subscribe(e=>{this._drawers.reset(e.filter(i=>!i._container||i._container===this)),this._drawers.notifyOnChanges()}),this._drawers.changes.pipe(vt(null)).subscribe(()=>{this._validateDrawers(),this._drawers.forEach(e=>{this._watchDrawerToggle(e),this._watchDrawerPosition(e),this._watchDrawerMode(e)}),(!this._drawers.length||this._isDrawerOpen(this._start)||this._isDrawerOpen(this._end))&&this.updateContentMargins(),this._changeDetectorRef.markForCheck()}),this._ngZone.runOutsideAngular(()=>{this._doCheckSubject.pipe(ei(10),Ie(this._destroyed)).subscribe(()=>this.updateContentMargins())})}ngOnDestroy(){this._contentMarginChanges.complete(),this._doCheckSubject.complete(),this._drawers.destroy(),this._destroyed.next(),this._destroyed.complete()}open(){this._drawers.forEach(e=>e.open())}close(){this._drawers.forEach(e=>e.close())}updateContentMargins(){let e=0,i=0;if(this._left&&this._left.opened){if(this._left.mode=="side")e+=this._left._getWidth();else if(this._left.mode=="push"){let a=this._left._getWidth();e+=a,i-=a}}if(this._right&&this._right.opened){if(this._right.mode=="side")i+=this._right._getWidth();else if(this._right.mode=="push"){let a=this._right._getWidth();i+=a,e-=a}}e=e||null,i=i||null,(e!==this._contentMargins.left||i!==this._contentMargins.right)&&(this._contentMargins={left:e,right:i},this._ngZone.run(()=>this._contentMarginChanges.next(this._contentMargins)))}ngDoCheck(){this._autosize&&this._isPushed()&&this._ngZone.runOutsideAngular(()=>this._doCheckSubject.next())}_watchDrawerToggle(e){e._animationStarted.pipe(Ie(this._drawers.changes)).subscribe(()=>{this.updateContentMargins(),this._changeDetectorRef.markForCheck()}),e.mode!=="side"&&e.openedChange.pipe(Ie(this._drawers.changes)).subscribe(()=>this._setContainerClass(e.opened))}_watchDrawerPosition(e){e.onPositionChanged.pipe(Ie(this._drawers.changes)).subscribe(()=>{yt({read:()=>this._validateDrawers()},{injector:this._injector})})}_watchDrawerMode(e){e._modeChanged.pipe(Ie(It(this._drawers.changes,this._destroyed))).subscribe(()=>{this.updateContentMargins(),this._changeDetectorRef.markForCheck()})}_setContainerClass(e){let i=this._element.nativeElement.classList,a="mat-drawer-container-has-open";e?i.add(a):i.remove(a)}_validateDrawers(){this._start=this._end=null,this._drawers.forEach(e=>{e.position=="end"?(this._end!=null,this._end=e):(this._start!=null,this._start=e)}),this._right=this._left=null,this._dir&&this._dir.value==="rtl"?(this._left=this._end,this._right=this._start):(this._left=this._start,this._right=this._end)}_isPushed(){return this._isDrawerOpen(this._start)&&this._start.mode!="over"||this._isDrawerOpen(this._end)&&this._end.mode!="over"}_onBackdropClicked(){this.backdropClick.emit(),this._closeModalDrawersViaBackdrop()}_closeModalDrawersViaBackdrop(){[this._start,this._end].filter(e=>e&&!e.disableClose&&this._drawerHasBackdrop(e)).forEach(e=>e._closeViaBackdropClick())}_isShowingBackdrop(){return this._isDrawerOpen(this._start)&&this._drawerHasBackdrop(this._start)||this._isDrawerOpen(this._end)&&this._drawerHasBackdrop(this._end)}_isDrawerOpen(e){return e!=null&&e.opened}_drawerHasBackdrop(e){return this._backdropOverride==null?!!e&&e.mode!=="side":this._backdropOverride}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=(function(){let e=[[["mat-drawer"],["mat-sidenav"]],[["mat-drawer-content"],["mat-sidenav-content"]],"*"],i=["mat-drawer, mat-sidenav","mat-drawer-content, mat-sidenav-content","*"];function a(o,s){if(o&1){let l=ut();y(0,"div",1),ye("click",function(){at(l);let d=M();return rt(d._onBackdropClicked())}),v()}if(o&2){let l=M();J("mat-drawer-shown",l._isShowingBackdrop())}}function r(o,s){o&1&&(y(0,"mat-drawer-content"),j(1,2),v())}return T({type:n,selectors:[["mat-drawer-container"]],contentQueries:function(s,l,c){if(s&1&&ot(c,Ko,5)(c,du,5),s&2){let d;H(d=U())&&(l._content=d.first),H(d=U())&&(l._allDrawers=d)}},viewQuery:function(s,l){if(s&1&&qe(Ko,5),s&2){let c;H(c=U())&&(l._userContent=c.first)}},hostAttrs:[1,"mat-drawer-container"],hostVars:2,hostBindings:function(s,l){s&2&&J("mat-drawer-container-explicit-backdrop",l._backdropOverride)},inputs:{autosize:"autosize",hasBackdrop:"hasBackdrop"},outputs:{backdropClick:"backdropClick"},exportAs:["matDrawerContainer"],features:[Qe([{provide:pu,useExisting:n}])],ngContentSelectors:i,decls:4,vars:2,consts:[[1,"mat-drawer-backdrop",3,"mat-drawer-shown"],[1,"mat-drawer-backdrop",3,"click"]],template:function(s,l){s&1&&(de(e),E(0,a,1,2,"div",0),j(1),j(2,1),E(3,r,2,0,"mat-drawer-content")),s&2&&(D(l.hasBackdrop?0:-1),h(3),D(l._content?-1:3))},dependencies:[Ko],styles:[`.mat-drawer-container {
  position: relative;
  z-index: 1;
  color: var(--%NS%mat-sidenav-content-text-color, var(--%NS%mat-sys-on-background));
  background-color: var(--%NS%mat-sidenav-content-background-color, var(--%NS%mat-sys-background));
  box-sizing: border-box;
  display: block;
  overflow: hidden;
}
.mat-drawer-container[fullscreen] {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
}
.mat-drawer-container[fullscreen].mat-drawer-container-has-open {
  overflow: hidden;
}
.mat-drawer-container.mat-drawer-container-explicit-backdrop .mat-drawer-side {
  z-index: 3;
}
.mat-drawer-container.ng-animate-disabled .mat-drawer-backdrop,
.mat-drawer-container.ng-animate-disabled .mat-drawer-content, .ng-animate-disabled .mat-drawer-container .mat-drawer-backdrop,
.ng-animate-disabled .mat-drawer-container .mat-drawer-content {
  transition: none;
}

.mat-drawer-backdrop {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  display: block;
  z-index: 3;
  visibility: hidden;
}
.mat-drawer-backdrop.mat-drawer-shown {
  visibility: visible;
  background-color: var(--%NS%mat-sidenav-scrim-color, color-mix(in srgb, var(--%NS%mat-sys-neutral-variant20) 40%, transparent));
}
.mat-drawer-transition .mat-drawer-backdrop {
  transition-duration: 400ms;
  transition-timing-function: cubic-bezier(0.25, 0.8, 0.25, 1);
  transition-property: background-color, visibility;
}
@media (forced-colors: active) {
  .mat-drawer-backdrop {
    opacity: 0.5;
  }
}

.mat-drawer-content {
  position: relative;
  z-index: 1;
  display: block;
  height: 100%;
  overflow: auto;
}
.mat-drawer-content.mat-drawer-content-hidden {
  opacity: 0;
}
.mat-drawer-transition .mat-drawer-content {
  transition-duration: 400ms;
  transition-timing-function: cubic-bezier(0.25, 0.8, 0.25, 1);
  transition-property: transform, margin-left, margin-right;
}

.mat-drawer {
  position: relative;
  z-index: 4;
  color: var(--%NS%mat-sidenav-container-text-color, var(--%NS%mat-sys-on-surface-variant));
  box-shadow: var(--%NS%mat-sidenav-container-elevation-shadow, none);
  background-color: var(--%NS%mat-sidenav-container-background-color, var(--%NS%mat-sys-surface));
  border-top-right-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-bottom-right-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  width: var(--%NS%mat-sidenav-container-width, 360px);
  display: block;
  position: absolute;
  top: 0;
  bottom: 0;
  z-index: 3;
  outline: 0;
  box-sizing: border-box;
  overflow-y: auto;
  transform: translate3d(-100%, 0, 0);
}
@media (forced-colors: active) {
  .mat-drawer, [dir=rtl] .mat-drawer.mat-drawer-end {
    border-right: solid 1px currentColor;
  }
}
@media (forced-colors: active) {
  [dir=rtl] .mat-drawer, .mat-drawer.mat-drawer-end {
    border-left: solid 1px currentColor;
    border-right: none;
  }
}
.mat-drawer.mat-drawer-side {
  z-index: 2;
}
.mat-drawer.mat-drawer-end {
  right: 0;
  transform: translate3d(100%, 0, 0);
  border-top-left-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-bottom-left-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}
[dir=rtl] .mat-drawer {
  border-top-left-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-bottom-left-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  transform: translate3d(100%, 0, 0);
}
[dir=rtl] .mat-drawer.mat-drawer-end {
  border-top-right-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-bottom-right-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  left: 0;
  right: auto;
  transform: translate3d(-100%, 0, 0);
}
.mat-drawer-transition .mat-drawer {
  transition: transform 400ms cubic-bezier(0.25, 0.8, 0.25, 1);
}
.mat-drawer:not(.mat-drawer-opened):not(.mat-drawer-animating) {
  visibility: hidden;
  box-shadow: none;
}
.mat-drawer:not(.mat-drawer-opened):not(.mat-drawer-animating) .mat-drawer-inner-container {
  display: none;
}
.mat-drawer.mat-drawer-opened.mat-drawer-opened {
  transform: none;
}

.mat-drawer-side {
  box-shadow: none;
  border-right-color: var(--%NS%mat-sidenav-container-divider-color, transparent);
  border-right-width: 1px;
  border-right-style: solid;
}
.mat-drawer-side.mat-drawer-end {
  border-left-color: var(--%NS%mat-sidenav-container-divider-color, transparent);
  border-left-width: 1px;
  border-left-style: solid;
  border-right: none;
}
[dir=rtl] .mat-drawer-side {
  border-left-color: var(--%NS%mat-sidenav-container-divider-color, transparent);
  border-left-width: 1px;
  border-left-style: solid;
  border-right: none;
}
[dir=rtl] .mat-drawer-side.mat-drawer-end {
  border-right-color: var(--%NS%mat-sidenav-container-divider-color, transparent);
  border-right-width: 1px;
  border-right-style: solid;
  border-left: none;
}

.mat-drawer-inner-container {
  width: 100%;
  height: 100%;
  overflow: auto;
}

.mat-sidenav-fixed {
  position: fixed;
}
`],encapsulation:2})})()}return n})(),pc=(()=>{class n extends Ko{static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(n)))(a||n)}})();static \u0275cmp=(function(){let e=["*"];return T({type:n,selectors:[["mat-sidenav-content"]],hostAttrs:[1,"mat-drawer-content","mat-sidenav-content"],features:[Qe([{provide:Aa,useExisting:n},{provide:Ko,useExisting:n}]),Ve],ngContentSelectors:e,decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},encapsulation:2})})()}return n})(),uu=(()=>{class n extends du{get fixedInViewport(){return this._fixedInViewport}set fixedInViewport(e){this._fixedInViewport=Rt(e)}_fixedInViewport=!1;get fixedTopGap(){return this._fixedTopGap}set fixedTopGap(e){this._fixedTopGap=zn(e)}_fixedTopGap=0;get fixedBottomGap(){return this._fixedBottomGap}set fixedBottomGap(e){this._fixedBottomGap=zn(e)}_fixedBottomGap=0;static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(n)))(a||n)}})();static \u0275cmp=(function(){let e=["*"];return T({type:n,selectors:[["mat-sidenav"]],hostAttrs:[1,"mat-drawer","mat-sidenav"],hostVars:16,hostBindings:function(a,r){a&2&&(fe("tabIndex",r.mode!=="side"?"-1":null)("align",null),Ni("top",r.fixedInViewport?r.fixedTopGap:null,"px")("bottom",r.fixedInViewport?r.fixedBottomGap:null,"px"),J("mat-drawer-end",r.position==="end")("mat-drawer-over",r.mode==="over")("mat-drawer-push",r.mode==="push")("mat-drawer-side",r.mode==="side")("mat-sidenav-fixed",r.fixedInViewport))},inputs:{fixedInViewport:"fixedInViewport",fixedTopGap:"fixedTopGap",fixedBottomGap:"fixedBottomGap"},exportAs:["matSidenav"],features:[Qe([{provide:du,useExisting:n}]),Ve],ngContentSelectors:e,decls:3,vars:0,consts:[["content",""],["cdkScrollable","",1,"mat-drawer-inner-container"]],template:function(a,r){a&1&&(de(),y(0,"div",1,0),j(2),v())},dependencies:[Aa],encapsulation:2})})()}return n})(),ey=(()=>{class n extends mu{_allDrawers=void 0;_content=void 0;static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(n)))(a||n)}})();static \u0275cmp=(function(){let e=[[["mat-drawer"],["mat-sidenav"]],[["mat-drawer-content"],["mat-sidenav-content"]],"*"],i=["mat-drawer, mat-sidenav","mat-drawer-content, mat-sidenav-content","*"];function a(o,s){if(o&1){let l=ut();y(0,"div",1),ye("click",function(){at(l);let d=M();return rt(d._onBackdropClicked())}),v()}if(o&2){let l=M();J("mat-drawer-shown",l._isShowingBackdrop())}}function r(o,s){o&1&&(y(0,"mat-sidenav-content"),j(1,2),v())}return T({type:n,selectors:[["mat-sidenav-container"]],contentQueries:function(s,l,c){if(s&1&&ot(c,pc,5)(c,uu,5),s&2){let d;H(d=U())&&(l._content=d.first),H(d=U())&&(l._allDrawers=d)}},hostAttrs:[1,"mat-drawer-container","mat-sidenav-container"],hostVars:2,hostBindings:function(s,l){s&2&&J("mat-drawer-container-explicit-backdrop",l._backdropOverride)},exportAs:["matSidenavContainer"],features:[Qe([{provide:pu,useExisting:n},{provide:mu,useExisting:n}]),Ve],ngContentSelectors:i,decls:4,vars:2,consts:[[1,"mat-drawer-backdrop",3,"mat-drawer-shown"],[1,"mat-drawer-backdrop",3,"click"]],template:function(s,l){s&1&&(de(e),E(0,a,1,2,"div",0),j(1),j(2,1),E(3,r,2,0,"mat-sidenav-content")),s&2&&(D(l.hasBackdrop?0:-1),h(3),D(l._content?-1:3))},dependencies:[pc],styles:[`.mat-drawer-container {
  position: relative;
  z-index: 1;
  color: var(--%NS%mat-sidenav-content-text-color, var(--%NS%mat-sys-on-background));
  background-color: var(--%NS%mat-sidenav-content-background-color, var(--%NS%mat-sys-background));
  box-sizing: border-box;
  display: block;
  overflow: hidden;
}
.mat-drawer-container[fullscreen] {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
}
.mat-drawer-container[fullscreen].mat-drawer-container-has-open {
  overflow: hidden;
}
.mat-drawer-container.mat-drawer-container-explicit-backdrop .mat-drawer-side {
  z-index: 3;
}
.mat-drawer-container.ng-animate-disabled .mat-drawer-backdrop,
.mat-drawer-container.ng-animate-disabled .mat-drawer-content, .ng-animate-disabled .mat-drawer-container .mat-drawer-backdrop,
.ng-animate-disabled .mat-drawer-container .mat-drawer-content {
  transition: none;
}

.mat-drawer-backdrop {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  display: block;
  z-index: 3;
  visibility: hidden;
}
.mat-drawer-backdrop.mat-drawer-shown {
  visibility: visible;
  background-color: var(--%NS%mat-sidenav-scrim-color, color-mix(in srgb, var(--%NS%mat-sys-neutral-variant20) 40%, transparent));
}
.mat-drawer-transition .mat-drawer-backdrop {
  transition-duration: 400ms;
  transition-timing-function: cubic-bezier(0.25, 0.8, 0.25, 1);
  transition-property: background-color, visibility;
}
@media (forced-colors: active) {
  .mat-drawer-backdrop {
    opacity: 0.5;
  }
}

.mat-drawer-content {
  position: relative;
  z-index: 1;
  display: block;
  height: 100%;
  overflow: auto;
}
.mat-drawer-content.mat-drawer-content-hidden {
  opacity: 0;
}
.mat-drawer-transition .mat-drawer-content {
  transition-duration: 400ms;
  transition-timing-function: cubic-bezier(0.25, 0.8, 0.25, 1);
  transition-property: transform, margin-left, margin-right;
}

.mat-drawer {
  position: relative;
  z-index: 4;
  color: var(--%NS%mat-sidenav-container-text-color, var(--%NS%mat-sys-on-surface-variant));
  box-shadow: var(--%NS%mat-sidenav-container-elevation-shadow, none);
  background-color: var(--%NS%mat-sidenav-container-background-color, var(--%NS%mat-sys-surface));
  border-top-right-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-bottom-right-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  width: var(--%NS%mat-sidenav-container-width, 360px);
  display: block;
  position: absolute;
  top: 0;
  bottom: 0;
  z-index: 3;
  outline: 0;
  box-sizing: border-box;
  overflow-y: auto;
  transform: translate3d(-100%, 0, 0);
}
@media (forced-colors: active) {
  .mat-drawer, [dir=rtl] .mat-drawer.mat-drawer-end {
    border-right: solid 1px currentColor;
  }
}
@media (forced-colors: active) {
  [dir=rtl] .mat-drawer, .mat-drawer.mat-drawer-end {
    border-left: solid 1px currentColor;
    border-right: none;
  }
}
.mat-drawer.mat-drawer-side {
  z-index: 2;
}
.mat-drawer.mat-drawer-end {
  right: 0;
  transform: translate3d(100%, 0, 0);
  border-top-left-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-bottom-left-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}
[dir=rtl] .mat-drawer {
  border-top-left-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-bottom-left-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  transform: translate3d(100%, 0, 0);
}
[dir=rtl] .mat-drawer.mat-drawer-end {
  border-top-right-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-bottom-right-radius: var(--%NS%mat-sidenav-container-shape, var(--%NS%mat-sys-corner-large));
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  left: 0;
  right: auto;
  transform: translate3d(-100%, 0, 0);
}
.mat-drawer-transition .mat-drawer {
  transition: transform 400ms cubic-bezier(0.25, 0.8, 0.25, 1);
}
.mat-drawer:not(.mat-drawer-opened):not(.mat-drawer-animating) {
  visibility: hidden;
  box-shadow: none;
}
.mat-drawer:not(.mat-drawer-opened):not(.mat-drawer-animating) .mat-drawer-inner-container {
  display: none;
}
.mat-drawer.mat-drawer-opened.mat-drawer-opened {
  transform: none;
}

.mat-drawer-side {
  box-shadow: none;
  border-right-color: var(--%NS%mat-sidenav-container-divider-color, transparent);
  border-right-width: 1px;
  border-right-style: solid;
}
.mat-drawer-side.mat-drawer-end {
  border-left-color: var(--%NS%mat-sidenav-container-divider-color, transparent);
  border-left-width: 1px;
  border-left-style: solid;
  border-right: none;
}
[dir=rtl] .mat-drawer-side {
  border-left-color: var(--%NS%mat-sidenav-container-divider-color, transparent);
  border-left-width: 1px;
  border-left-style: solid;
  border-right: none;
}
[dir=rtl] .mat-drawer-side.mat-drawer-end {
  border-right-color: var(--%NS%mat-sidenav-container-divider-color, transparent);
  border-right-width: 1px;
  border-right-style: solid;
  border-left: none;
}

.mat-drawer-inner-container {
  width: 100%;
  height: 100%;
  overflow: auto;
}

.mat-sidenav-fixed {
  position: fixed;
}
`],encapsulation:2})})()}return n})(),Yo=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[Hn,me,Hn]})}return n})();var SM=[Vo,Gn,Ot,$o,Zn,Zp,Jp,zo,Wo,Ho,tm,qo,Go,vn,Yo],uc=class n{static \u0275fac=function(e){return new(e||n)};static \u0275mod=$({type:n});static \u0275inj=V({imports:[SM,Vo,Gn,Ot,$o,Zn,Zp,Jp,zo,Wo,Ho,tm,qo,Go,vn,Yo]})};var hc=class n{data=ht.required({transform:t=>"```json\n"+JSON.stringify(t,null,2)+"\n```"});static \u0275fac=function(e){return new(e||n)};static \u0275cmp=T({type:n,selectors:[["app-json"]],inputs:{data:[1,"data"]},decls:1,vars:1,consts:[[3,"data"]],template:function(e,i){e&1&&P(0,"markdown",0),e&2&&R("data",i.data())},dependencies:[Dn,En],encapsulation:2})};var fc={},cr=Symbol(),ra=Symbol(),ny=n=>typeof n=="string"?_e[n]:n,_e={plain:fc,plaintext:fc,text:fc,txt:fc},Qo=(n,t)=>(t[ra]||hu)(n,t),hu=(n,t)=>{for(var e=[n],i,a=[],r=0;i=ny(t[cr]);)delete t[cr],Object.assign(t,i);for(ay(n,t,e,0);a[r++]=e[0],e=e[1];);return a},fu=(n,t,e)=>n.replace(/&/g,"&amp;").replace(t,e),ty="</span>",gc="",Xo="",gu=n=>{for(var t="",e,i=0;e=n[i++];)t+=iy(e);return t},iy=n=>{if(n instanceof Zo){var{type:t,alias:e,content:i}=n,a=gc,r=Xo,o=`<span class="token ${t+(e?" "+e:"")+(t=="keyword"&&typeof i=="string"?" keyword-"+i.replace(/"|\s/g,""):"")}">`;Xo+=ty,gc+=o;var s=iy(i);return gc=a,Xo=r,o+s+ty}return typeof n!="string"?gu(n):(n=fu(n,/</g,"&lt;"),Xo&&n.includes(`
`)?n.replace(/\n/g,Xo+`
`+gc):n)};var ay=(n,t,e,i,a)=>{for(var r in t)if(t[r])for(var o=0,s=t[r],l,c=Array.isArray(s)?s:[s];l=c[o];o++){if(a&&a[0]==r&&a[1]==o)return;for(var d=l.pattern||l,m=ny(l.inside),u=l.lookbehind,f=d.global,g=l.alias,b=e,_=i;b&&(!a||_<a[2]);_+=b[0].length,b=b[1]){var x=b[0],w=0,C;if(!(x instanceof Zo)){if(d.lastIndex=f?_:0,C=d.exec(f?n:x),!C&&f)break;if(C&&C[0]){var F=u&&C[1]?C[1].length:0,A=C.index+F,Q=C[0].slice(F),Re=A+Q.length,He,s;if(f){for(;s=_+b[0].length,A>=s;b=b[1],_=s);if(b[0]instanceof Zo)continue;for(He=b,s=_;(s+=He[0].length)<Re;He=He[1],w++);x=n.slice(_,s),A-=_,Re-=_}for(var Be=x.slice(Re),q=_+x.length,re=new Zo(r,m?Qo(Q,m):Q,Q,g),k=b,le=0,je;k=k[1],le++<w;);Be&&(!k||k[0]instanceof Zo?k=[Be,k]:k[0]=Be+k[0]),_+=A,b[0]=A?x.slice(0,A):re,A?b=b[1]=[re,k]:b[1]=k,w&&(ay(n,t,b,_,je=[r,o,q]),q=je[2]),a&&q>a[2]&&(a[2]=q)}}}}};function Zo(n,t,e,i){this.type=n,this.content=t,this.alias=i,this.length=e.length}var Jo=(n,t,...e)=>{let i,a=[],r,o="",s,l=!1,c=!0,d=[],m,u=0,f=CM(),g=f.firstChild,b=g.children,_=b[0],x=_.firstChild,w={language:"text",value:o},C=new Set(e),F={},A=se=>{Object.assign(w,se);let be=o!=(o=se.value??o),Ut=i!=(i=w.language);m=!!w.readOnly,f.style.tabSize=w.tabSize||2,x.inputMode=m?"none":"",x.setAttribute("aria-readonly",m),He(),Re(),be&&(l||x.remove(),x.value=o,x.selectionEnd=0,l||_.prepend(x)),(be||Ut)&&Q()},Q=()=>{d=Qo(o=x.value,_e[i]||{}),k("tokenize",d,i,o);let se=gu(d).split(`
`),be=0,Ut=u,Ci=u=se.length;for(;se[be]==a[be]&&be<Ci;)++be;for(;Ci&&se[--Ci]==a[--Ut];);if(be==Ci&&be==Ut)b[be+1].innerHTML=se[be]+`
`;else{let Uu=Ut<be?Ut:be-1,_r=Uu,Lc="";for(;_r<Ci;)Lc+=`<div class=pce-line aria-hidden=true>${se[++_r]}
</div>`;for(_r=Ci<be?Ci:be-1;_r<Ut;_r++)b[be+1].remove();Lc&&b[Uu+1].insertAdjacentHTML("afterend",Lc),f.style.setProperty("--number-width",(0|Math.log10(u))+1+".001ch")}k("update",o),le(!0),c&&setTimeout(setTimeout,0,()=>c=!0),a=se,c=!1},Re=se=>{(se||C).forEach(be=>{typeof be=="object"?(be.update(je,w),se&&C.add(be)):(be(je,w),se||C.delete(be))})},He=([se,be]=Be())=>{f.className=`prism-code-editor language-${i}${w.lineNumbers==!1?"":" show-line-numbers"} pce-${w.wordWrap?"":"no"}wrap${w.rtl?" pce-rtl":""} pce-${se<be?"has":"no"}-selection${l?" pce-focus":""}${m?" pce-readonly":""}${w.class?" "+w.class:""}`},Be=()=>[x.selectionStart,x.selectionEnd,x.selectionDirection],q={Escape(){x.blur()}},re={},k=(se,...be)=>{F[se]?.forEach(Ut=>Ut.apply(je,be)),w["on"+se[0].toUpperCase()+se.slice(1)]?.(...be,je)},le=se=>{if(se||c){let be=Be(),Ut=b[s=bc(o,0,be[be[2]<"f"?0:1])];Ut!=r&&(r?.classList.remove("active-line"),Ut.classList.add("active-line"),r=Ut),He(be),k("selectionChange",be,o)}},je={container:f,wrapper:g,lines:b,textarea:x,get activeLine(){return s},get value(){return o},options:w,get focused(){return l},get tokens(){return d},inputCommandMap:re,keyCommandMap:q,extensions:{},setOptions:A,update:Q,getSelection:Be,addExtensions(...se){Re(se)},on:(se,be)=>((F[se]||=new Set).add(be),()=>F[se].delete(be)),remove(){f.remove()}};return rn(x,"keydown",se=>{q[se.key]?.(se,Be(),o)&&oa(se)}),rn(x,"beforeinput",se=>{(m||se.inputType=="insertText"&&re[se.data]?.(se,Be(),o))&&oa(se)}),rn(x,"input",Q),rn(x,"blur",()=>{vu=null,l=!1,He()}),rn(x,"focus",()=>{vu=le,l=!0,He()}),rn(x,"selectionchange",se=>{le(!se.isTrusted),oa(se)}),kM(n)?.append(f),t&&A(t),je};var on="u">typeof window?document:null,bu=on?.createElement("div"),_u=(n,t)=>(bu&&(bu.innerHTML=n,t=bu.firstChild),()=>t.cloneNode(!0)),rn=(n,t,e,i)=>n.addEventListener(t,e,i),kM=n=>typeof n=="string"?on.querySelector(n):n,bc=(n,t=0,e=1/0)=>{let i=1;for(;(t=n.indexOf(`
`,t)+1)&&t<=e;i++);return i},vc={},CM=_u("<div><div class=pce-wrapper><div class=pce-overlays><textarea class=pce-textarea spellcheck=false autocapitalize=off autocomplete=off>"),oa=n=>{n.preventDefault(),n.stopImmediatePropagation()};var vu;on&&rn(on,"selectionchange",()=>vu?.());globalThis.Prism={highlightAllUnder:n=>{n.querySelectorAll("pre code").forEach(t=>{let e=Array.from(t.classList).find(a=>a.startsWith("language"))||"",i=t.textContent||"";t.textContent="",Jo(t.parentElement,{value:i.trimEnd(),language:e.replace("language-",""),lineNumbers:!1,wordWrap:!0,readOnly:!0})})}};function EM(n,t){if(n&1&&(Ze(0,"span"),S(1),Je()),n&2){let e=M();h(),Dr(" ",e.lowerBoundInclusive()?">=":">"," ",e.lowerBound()," ")}}function DM(n,t){if(n&1&&(Ze(0,"span"),S(1),Je()),n&2){let e=M();h(),Dr(" ",e.upperBoundInclusive()?"<=":"<"," ",e.upperBound()," ")}}function MM(n,t){if(n&1&&(Ze(0,"span"),S(1),Je()),n&2){let e=M();h(),hh("",e.lowerBoundInclusive()?"[":"("," ",e.lowerBound()," .. ",e.upperBound()," ",e.upperBoundInclusive()?"]":")"," ")}}var _c=class n{lowerBound=ht();upperBound=ht();lowerBoundInclusive=ht(!0,{transform:t=>t===!0||t=="true"});upperBoundInclusive=ht(!0,{transform:t=>t===!0||t=="true"});static \u0275fac=function(e){return new(e||n)};static \u0275cmp=T({type:n,selectors:[["app-schema-range"]],inputs:{lowerBound:[1,"lowerBound"],upperBound:[1,"upperBound"],lowerBoundInclusive:[1,"lowerBoundInclusive"],upperBoundInclusive:[1,"upperBoundInclusive"]},decls:4,vars:3,template:function(e,i){e&1&&(Ze(0,"span"),E(1,EM,2,2,"span"),E(2,DM,2,2,"span"),E(3,MM,2,4,"span"),Je()),e&2&&(h(),D(i.lowerBound()!=null&&i.upperBound()==null?1:-1),h(),D(i.lowerBound()==null&&i.upperBound()!=null?2:-1),h(),D(i.lowerBound()!=null&&i.upperBound()!=null?3:-1))},encapsulation:2})};var ry=()=>({}),oy=n=>({value:n});function NM(n,t){if(n&1&&zc(0,2),n&2){let e=M(),i=Kt(4);R("ngTemplateOutlet",i)("ngTemplateOutletContext",Gc(2,oy,e.schema()))}}function IM(n,t){n&1&&(y(0,"span",7),S(1,"*"),v())}function AM(n,t){n&1&&(y(0,"span",8),S(1,"\xA0(deprecated)"),v())}function TM(n,t){if(n&1&&(y(0,"mat-list-item",3)(1,"mat-icon",4),S(2,"arrow_right"),v(),y(3,"span",5)(4,"b",6),S(5),v(),E(6,IM,2,0,"span",7),E(7,AM,2,0,"span",8),v(),zc(8,9),v()),n&2){let e=t.$implicit,i=M(2),a=Kt(4);h(5),Ge(e.key),h(),D(i.schema().required?.includes(e.key)?6:-1),h(),D(e.value.deprecated?7:-1),h(),R("ngTemplateOutlet",a)("ngTemplateOutletContext",Gc(5,oy,e.value))}}function OM(n,t){if(n&1&&(y(0,"mat-list"),Ye(1,TM,9,7,"mat-list-item",3,Ke),ys(3,"keyvalue"),v()),n&2){let e=M();h(),Xe(xs(3,0,e.schema().properties||qc(2,ry)))}}function RM(n,t){if(n&1&&(y(0,"span",10),S(1),v()),n&2){let e=M(2).value;h(),ae(" ",e.type.join(", ")," ")}}function PM(n,t){if(n&1&&S(0),n&2){let e=M(2).value;ae(" ",e.type," ")}}function FM(n,t){if(n&1&&(y(0,"span",16),S(1),v()),n&2){let e=M(2).value;h(),ae("(",e.format,")")}}function LM(n,t){if(n&1&&(y(0,"span",10),E(1,RM,2,1,"span",10)(2,PM,1,1),E(3,FM,2,1,"span",16),v()),n&2){let e=M().value,i=M();h(),D(i.Array.isArray(e.type)?1:2),h(2),D(e.format?3:-1)}}function BM(n,t){if(n&1&&(y(0,"span",11)(1,"mat-chip-set")(2,"a",17)(3,"mat-chip"),P(4,"mat-icon",18),S(5),v()()()()),n&2){let e=M().value;h(2),R("href",e.refAnchorUrl,lt),h(3),ae(" ",e.refTitle," ")}}function jM(n,t){if(n&1&&(y(0,"span",10),S(1," array "),y(2,"mat-chip-set")(3,"a",17)(4,"mat-chip"),P(5,"mat-icon",18),S(6),v()()()()),n&2){let e=M().value;h(3),R("href",e.items.refAnchorUrl,lt),h(3),ae(" ",e.items.refTitle," ")}}function VM(n,t){if(n&1&&(y(0,"span",10),S(1),v()),n&2){let e=M().value;h(),ae(" ",e.items.type,"[] ")}}function $M(n,t){if(n&1&&(y(0,"div",12),P(1,"markdown",19),v()),n&2){let e=M().value;h(),R("data",e.description)}}function zM(n,t){if(n&1&&(y(0,"div",6),S(1),v()),n&2){let e=t.$implicit;h(),ae(" ",e," ")}}function HM(n,t){if(n&1&&(y(0,"div",13)(1,"span",20)(2,"i"),S(3,"Example:"),v()(),S(4," \xA0 "),y(5,"span",21),Ye(6,zM,2,1,"div",6,Ke),v()()),n&2){let e=M().value;h(6),Xe(e.example.value.split(`
`))}}function UM(n,t){if(n&1&&(y(0,"span",22),S(1),v()),n&2){let e=t.$implicit;h(),Ge(e||"null")}}function qM(n,t){if(n&1&&(y(0,"div",13)(1,"span",20)(2,"i"),S(3,"Allowed values:"),v()(),S(4," \xA0 "),Ye(5,UM,2,1,"span",22,Ke),v()),n&2){let e=M().value;h(5),Xe(e.enum)}}function GM(n,t){if(n&1&&(y(0,"span",15),P(1,"app-schema-range",23),S(2," items "),v()),n&2){let e=M().value;h(),R("lowerBound",e.minItems)("upperBound",e.maxItems)}}function WM(n,t){if(n&1&&(y(0,"span",15),S(1),v()),n&2){let e=M().value;h(),ae(" Unique items: ",e.uniqueItems?"yes":"no"," ")}}function KM(n,t){if(n&1&&(y(0,"span",15),S(1," pattern: "),y(2,"span",6),S(3),v()()),n&2){let e=M().value;h(3),ae(" ",e.pattern," ")}}function YM(n,t){if(n&1&&(y(0,"span",15),P(1,"app-schema-range",23),S(2," length "),v()),n&2){let e=M().value;h(),R("lowerBound",e.minLength)("upperBound",e.maxLength)}}function XM(n,t){if(n&1&&(y(0,"span",15),P(1,"app-schema-range",24),S(2," value range "),v()),n&2){let e=M().value;h(),R("lowerBound",e.minimum)("upperBound",e.maximum)("lowerBoundInclusive",!e.exclusiveMinimum)("upperBoundInclusive",!e.exclusiveMaximum)}}function ZM(n,t){if(n&1&&(y(0,"span",15),S(1),v()),n&2){let e=M().value;h(),ae(" Multiple of ",e.multipleOf," ")}}function QM(n,t){if(n&1&&(E(0,LM,4,2,"span",10),E(1,BM,6,2,"span",11),E(2,jM,7,2,"span",10),E(3,VM,2,1,"span",10),E(4,$M,2,1,"div",12),E(5,HM,8,0,"div",13),E(6,qM,7,0,"div",13),y(7,"span",14),E(8,GM,3,2,"span",15),E(9,WM,2,1,"span",15),E(10,KM,4,1,"span",15),E(11,YM,3,2,"span",15),E(12,XM,3,4,"span",15),E(13,ZM,2,1,"span",15),v()),n&2){let e=t.value;D(e?.type!="array"?0:-1),h(),D(e.refTitle?1:-1),h(),D(e?.type=="array"&&e?.items?.refTitle?2:-1),h(),D(e?.type=="array"&&!e?.items?.refTitle?3:-1),h(),D(e.description?.length>0?4:-1),h(),D(e.example?5:-1),h(),D(e.enum&&e.enum.length>0?6:-1),h(2),D(e.minItems!=null||e.maxItems!=null?8:-1),h(),D(e.uniqueItems!=null?9:-1),h(),D(e.pattern?10:-1),h(),D(e.minLength!=null||e.maxLength!=null?11:-1),h(),D(e.minimum!=null||e.maximum!=null?12:-1),h(),D(e.multipleOf!=null?13:-1)}}var dr=class n{schema=ht.required();Array=Array;Object=Object;static \u0275fac=function(e){return new(e||n)};static \u0275cmp=T({type:n,selectors:[["app-schema"]],inputs:{schema:[1,"schema"]},decls:5,vars:3,consts:[["valueContent",""],[1,"schema"],[3,"ngTemplateOutlet","ngTemplateOutletContext"],["lines","99"],["matListItemIcon",""],["matListItemTitle","",1,"key"],[1,"text-console"],[1,"required"],[1,"deprecated"],["matListItemLine","",3,"ngTemplateOutlet","ngTemplateOutletContext"],[1,"type","text-console"],[1,"type"],[1,"description"],[1,"flex"],[1,"flex","flex-wrap"],[1,"attribute"],[1,"format"],[3,"href"],["matChipAvatar","","fontIcon","schema"],[3,"data"],[1,"property-title"],[1,"value-box"],[1,"text-console","value-box"],["lowerBoundInclusive","true","upperBoundInclusive","true",3,"lowerBound","upperBound"],[3,"lowerBound","upperBound","lowerBoundInclusive","upperBoundInclusive"]],template:function(e,i){e&1&&(y(0,"div",1),E(1,NM,1,4,"ng-container",2),E(2,OM,4,3,"mat-list"),v(),Wt(3,QM,14,13,"ng-template",null,0,Ar)),e&2&&(h(),D(i.schema().type!=="object"?1:-1),h(),D(i.Object.keys(i.schema().properties||qc(2,ry)).length>0?2:-1))},dependencies:[Go,Wo,Q_,J_,cu,lu,su,vn,Qn,Si,lr,Ot,Lt,wn,Fr,Dn,En,_c,Lr],styles:[".required[_ngcontent-%COMP%], .deprecated[_ngcontent-%COMP%]{color:red}.description[_ngcontent-%COMP%]{overflow:auto}.property-title[_ngcontent-%COMP%]{min-width:fit-content}.attribute[_ngcontent-%COMP%]{background-color:#805ad5;color:#fff;margin:0 .2em .2em 0;padding:.1em .2em;border-radius:4px}.value-box[_ngcontent-%COMP%]{margin:1px 6px 1px 0;background-color:#eee;border:1px dashed grey;color:#696969;border-radius:4px;padding:0 2px;overflow-wrap:anywhere;white-space:normal;width:fit-content}"]})};var sn=class n{constructor(t){this.el=t}el;static \u0275fac=function(e){return new(e||n)(Oe(X))};static \u0275dir=K({type:n,selectors:[["","appNavigationTarget",""]]})};function JM(n,t){if(n&1&&(y(0,"div",6)(1,"span"),S(2,"Description"),v(),P(3,"markdown",7),v()),n&2){let e=M().$implicit;h(3),R("data",e.description)}}function eN(n,t){if(n&1&&(y(0,"a",9)(1,"mat-chip")(2,"mat-icon",10),S(3),v(),S(4),v()()),n&2){let e=t.$implicit;R("href",e.anchorUrl,lt),h(3),Ge(e.type=="schema"?"schema":"swap_vert"),h(),ae(" ",e.name," ")}}function tN(n,t){if(n&1&&(y(0,"div",3)(1,"span"),S(2,"Used by"),v(),y(3,"mat-chip-set"),Ye(4,eN,5,3,"a",9,Ke),v()()),n&2){let e=M().$implicit;h(4),Xe(e.usedBy)}}function nN(n,t){n&1&&P(0,"br")}function iN(n,t){if(n&1&&(y(0,"article",0)(1,"mat-card")(2,"mat-card-header",1)(3,"mat-card-title"),S(4),v()(),y(5,"mat-card-content")(6,"div",2)(7,"div",3)(8,"span"),S(9,"Name"),v(),y(10,"span",4),S(11),v()(),y(12,"div",3)(13,"span"),S(14,"Type"),v(),y(15,"span")(16,"div",5),S(17),v()()(),E(18,JM,4,1,"div",6),E(19,tN,6,0,"div",3),v(),y(20,"h6"),S(21,"Example"),v(),y(22,"div"),P(23,"app-json",7),v(),y(24,"h6"),S(25,"Properties"),v(),P(26,"app-schema",8),v()(),E(27,nN,1,0,"br"),v()),n&2){let e=t.$implicit,i=t.$index,a=t.$count;R("id",e.anchorIdentifier),h(4),ae(" ",e.title," "),h(7),Ge(e.name),h(6),Ge(e.type),h(),D(e.description?18:-1),h(),D(e.usedBy.length>0?19:-1),h(4),R("data",e.example?.rawValue),h(3),R("schema",e),h(),D(i!==a-1?27:-1)}}var yc=class n{constructor(t){this.asyncApiService=t}asyncApiService;schemas=ne([]);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(t=>this.schemas.set([...t.components.schemas.values()]))}static \u0275fac=function(e){return new(e||n)(Oe(mt))};static \u0275cmp=T({type:n,selectors:[["app-schemas"]],decls:4,vars:0,consts:[["appNavigationTarget","",3,"id"],[1,"flex","space-between","align-items-baseline"],[1,"table","margin-vertical-1em"],[1,"table-row"],[1,"text-console"],[1,"type-badge"],[1,"table-row","description"],[3,"data"],[3,"schema"],[3,"href"],["matChipAvatar",""]],template:function(e,i){e&1&&(y(0,"h2"),S(1,"Schemas"),v(),Ye(2,iN,28,9,"article",0,Ke)),e&2&&(h(2),Xe(i.schemas()))},dependencies:[En,Ot,Lt,Zn,ar,or,sr,rr,vn,Qn,Si,lr,hc,dr,sn],styles:[".table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:first-child{vertical-align:middle}.table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:last-child{word-break:break-word}.type-badge[_ngcontent-%COMP%]{display:inline;background-color:#e0e0e0;border-radius:4px;padding:4px;font-weight:400;font-size:small}"]})};var es=new Zt("init"),sa=new Zt("");var sy={asyncApiJson:{},contact:{},license:{},title:"",version:""};var ts={ts_type:"object",title:"",name:"",anchorUrl:"",anchorIdentifier:"",usedBy:[]};function aN(n,t){if(n&1){let e=ut();y(0,"a",3),ye("click",function(){at(e);let a=M();return rt(a.download())}),S(1,"AsyncAPI JSON"),v()}}function rN(n,t){if(n&1&&(y(0,"a",5),S(1),v()),n&2){M(2);let e=va(6);R("href",e.url,lt),h(),ae(" ",e.name," ")}}function oN(n,t){if(n&1&&S(0),n&2){M(2);let e=va(6);ae(" ",e.name," ")}}function sN(n,t){if(n&1&&(y(0,"mat-chip"),P(1,"mat-icon",4),E(2,rN,2,2,"a",5)(3,oN,1,1),v()),n&2){M();let e=va(6);h(2),D(e?.url?2:3)}}function lN(n,t){if(n&1&&(y(0,"mat-chip"),P(1,"mat-icon",6),y(2,"a",5),S(3),v()()),n&2){M();let e=va(8);h(2),R("href",e.url,lt),h(),ae(" ",e.url," ")}}function cN(n,t){if(n&1&&(y(0,"mat-chip"),P(1,"mat-icon",7),y(2,"a",5),S(3),v()()),n&2){M();let e=va(10);h(2),R("href",e.href,lt),h(),ae(" ",e.name," ")}}function dN(n,t){if(n&1&&(y(0,"p"),P(1,"markdown",8),v()),n&2){let e=M();h(),R("data",e.info().description)}}var xc=class n{constructor(t){this.asyncApiService=t}asyncApiService;asyncApiData=void 0;info=ne(sy);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(t=>{this.asyncApiData=t,this.info.set(t.info)})}download(){if(this.asyncApiData===void 0)return!1;let t=JSON.stringify(this.asyncApiData.info.asyncApiJson,null,2),e=new TextEncoder().encode(t),i=new Blob([e],{type:"application/json"}),a=window.URL.createObjectURL(i);return window.open(a),!1}static \u0275fac=function(e){return new(e||n)(Oe(mt))};static \u0275cmp=T({type:n,selectors:[["app-info"]],decls:13,vars:10,consts:[[1,"info-chips"],["matChipAvatar","","fontIcon","download"],["href","javascript:void(0);"],["href","javascript:void(0);",3,"click"],["matChipAvatar","","fontIcon","attribution"],["target","_blank",3,"href"],["matChipAvatar","","fontIcon","link"],["matChipAvatar","","fontIcon","email"],[3,"data"]],template:function(e,i){if(e&1&&(y(0,"h1"),S(1),v(),y(2,"p",0)(3,"mat-chip"),P(4,"mat-icon",1),E(5,aN,2,0,"a",2),v(),vs(6),E(7,sN,4,1,"mat-chip"),vs(8),E(9,lN,4,2,"mat-chip"),vs(10),E(11,cN,4,2,"mat-chip"),v(),E(12,dN,2,1,"p")),e&2){h(),Dr(" ",i.info().title," ",i.info().version?"v"+i.info().version:"",`
`),h(4),D(i.info().asyncApiJson?5:-1),h();let a=_s(i.info().license);h(),D(a?.name?7:-1),h();let r=_s(i.info().contact);h(),D(r.url?9:-1),h();let o=_s(i.info().contact.email);h(),D(o?11:-1),h(),D(i.info().description?12:-1)}},dependencies:[vn,Qn,Si,Ot,Lt,Dn,En],styles:[".info-chips[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]{margin-inline-end:8px}"]})};function mN(n,t){if(n&1&&(y(0,"article",1)(1,"mat-card",2)(2,"mat-card-header")(3,"mat-card-title"),S(4),v(),y(5,"span",3)(6,"span",4),S(7),v()()(),y(8,"mat-card-content")(9,"table")(10,"tbody")(11,"tr")(12,"td"),S(13,"Host"),v(),y(14,"td",5),S(15),v()()()()()()()),n&2){let e=t.$implicit;R("id",e.value.anchorIdentifier),h(4),ae(" ",e.key," "),h(3),ae(" ",e.value.protocol," "),h(8),ae(" ",e.value.host," ")}}var wc=class n{constructor(t){this.asyncApiService=t}asyncApiService;servers=ne(new Map);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(t=>this.servers.set(t.servers))}static \u0275fac=function(e){return new(e||n)(Oe(mt))};static \u0275cmp=T({type:n,selectors:[["app-servers"]],decls:6,vars:2,consts:[[1,"row"],["appNavigationTarget","",1,"width-6-of-12","width-12-of-12-s",3,"id"],["appearance","outlined"],[1,"flex","gap-16","padding-horizontal-1em","height-fix-content"],[1,"badge","protocol-badge"],[1,"text-console"]],template:function(e,i){e&1&&(y(0,"h2"),S(1,"Servers"),v(),y(2,"div",0),Ye(3,mN,16,4,"article",1,Ke),ys(5,"keyvalue"),v()),e&2&&(h(3),Xe(xs(5,0,i.servers())))},dependencies:[wn,Zn,ar,or,sr,rr,sn,Lr],styles:[".badge[_ngcontent-%COMP%]{border-radius:4px;padding:.3em;font-size:smaller;text-transform:uppercase}.protocol-badge[_ngcontent-%COMP%]{background-color:#347aeb;color:#fff}"]})};var Sc=(n,t)=>{if(t.has(n))return t.get(n);var e=n,i=pN.call(n).slice(8,-1);if(i=="Object"){t.set(n,e={});for(var a in n)e[a]=Sc(n[a],t);n[cr]&&(e[cr]=Sc(n[cr],t)),n[ra]&&(e[ra]=n[ra])}else if(i=="Array"){t.set(n,e=[]);for(var r=0,o=n.length;r<o;r++)e[r]=Sc(n[r],t)}return e},kc=n=>Sc(n,new Map);var Cc=(n,t,e)=>{var i={};for(var a in n)i[a]=n[a],delete n[a];for(var a in i)a==t&&Object.assign(n,e),e.hasOwnProperty(a)||(n[a]=i[a])},ly=(n,t)=>n.alias=n.alias?n.alias+" "+t:t,pN={}.toString;var cy=(n,t,e)=>t.indexOf(n[0])+1||e&&t.indexOf(n[e])+1;var dy=(n=!0,t="()[]{}")=>{let e,i,a=[],r=d=>{d.extensions.matchBrackets=r,d.on("tokenize",l),n&&d.tokens[0]?d.update():l(d.tokens)},o=r.brackets=[],s=r.pairs=[],l=d=>{if(s.length=o.length=i=e=0,c(d,0),n)for(let m=0,u;u=o[m];)ly(u[0],`bracket-${m++in s?"level-"+u[3]%12:"error"}`)},c=(d,m)=>{let u,f=0;for(;u=d[f++];){let g=u.length;if(typeof u!="string"){let b=u.content;if(Array.isArray(b))c(b,m);else if((u.alias||u.type)=="punctuation"){let _=cy(b,t,g-1),x=_%2;if(_){if(o[e]=[u,m,m+g,i,b,!!x],x)a[i++]=[e,_+1];else for(let w=i,C;C=a[--w];)if(_==C[1]){s[s[e]=C[0]]=e,o[e][3]=i=w;break}e++}}}m+=g}};return r};var mr=(n,t)=>t?n.lastIndexOf(`
`,t-1)+1:0,ns=(n,t)=>(t=n.indexOf(`
`,t))+1?t:n.length,my=(n,t,e,i)=>(rn(n,t,e,i),()=>n.removeEventListener(t,e,i)),ki=(n,t,e,i)=>my(n.textarea,t,e,i),py=(n,t)=>parseFloat(getComputedStyle(n)[t]);var w5=new Set("xml,rss,atom,jsx,tsx,xquery,xeora,xeoracube,actionscript".split(","));var la,pr=n=>n.replace(/[$+?|.^*()[\]{}\\]/g,"\\$&"),uy=(n,t)=>n.slice(mr(n,t),t),ur=(n,t,e=t)=>[n.slice(t=mr(n,t),e=ns(n,e)).split(`
`),t,e],wu=(n,t,e=0,i=e,a=n.getSelection()[0])=>{let r=n.value,o=n.lines[bc(r,0,a)],s=on.createTreeWalker(o,5),l=s.lastChild(),c=ns(r,a)+1-a-l.length;for(;-c<=i&&(l=s.previousNode());)if(!l.lastChild&&(c-=l.length||0,c<=e)){for(;l!=o;l=l.parentNode)if(l.matches?.(t))return l}},Su=(n,t)=>wu(n,"[class*=language-]",0,0,t)?.className.match(/language-(\S*)/)[1]||n.options.language,Rn=(n,t,e,i,a,r)=>{if(n.options.readOnly)return;la=n.getSelection(),i??=e;let o=n.textarea,s=n.value,l=xu&&!s[i??la[1]]&&/\n$/.test(t)&&/^$|\n$/.test(s),c;n.focused||o.focus(),e!=null&&o.setSelectionRange(e,i),a!=null&&(c=n.on("update",()=>{o.setSelectionRange(a,r??a,la[2]),c()})),yu||o.dispatchEvent(new InputEvent("beforeinput",{data:t})),xu||yu?(l&&(o.selectionEnd--,t=t.slice(0,-1)),yu&&(t+=`
`),on.execCommand(t?"insertHTML":"delete",!1,fu(t,/</g,"&lt;")),l&&o.selectionStart++):on.execCommand(t?"insertText":"delete",!1,t),la=0},ku=(n,t,e=t,i)=>{let a=n.textarea,r=my(a,"focus",o=>{let s=o.relatedTarget;s?s.focus():a.blur()});a.setSelectionRange(t,e,i),r(),a.dispatchEvent(new Event("selectionchange"))},hy=on?navigator.userAgent:"",yn=on?/Mac|iPhone|iP[ao]d/.test(navigator.platform):!1,xu=/Chrome\//.test(hy),yu=!xu&&/AppleWebKit\//.test(hy),Cu=n=>n.altKey+n.ctrlKey*2+n.metaKey*4+n.shiftKey*8,fy=(n,t)=>n.lines[0].append(t);var gy=()=>n=>{let t,e=[],i=()=>{let r=n.extensions.matchBrackets,[o,s]=n.getSelection();if(r){let l=r.brackets,c=r.pairs,d,m;if(n.focused&&o==s){for(let u=0,f;f=l[++u];)if(!f[5]&&f[2]>=s&&l[c[u]]?.[1]<=s){d=l[c[u]],m=f;break}}m!=t&&(a(),m?(e=[d,m].map(u=>wu(n,".punctuation",0,-1,u[1])),e[0]!=e[1]&&d[2]==m[1]&&(e[0].textContent+=e[1].textContent,e[1].textContent="",e[1]=e[0]),a(!0)):e=[]),t=m}},a=r=>e.forEach(o=>o.classList.toggle("active-bracket",!!r));ki(n,"focus",i),ki(n,"blur",i),n.on("selectionChange",i)};var Ec=yn?4:2;var is=!1;var ca=n=>n.search(/\S|$/),Iu=({options:{insertSpaces:n=!0,tabSize:t}})=>[n?" ":"	",n?t||2:1],da=n=>!n.options.readOnly&&!n.extensions.cursor?.scrollIntoView(),by=(n,t,e,i,a,r,o)=>{let s=e.join(`
`);if(s==t.join(`
`))return;let l=t.length-1,c=e[l],d=t[l],m=d.length-c.length,u=e[0].length-t[0].length,f=i+ca((u<0?e:t)[0]),g=a-d.length+ca(m>0?c:d),b=i-a+s.length+m,_=f>r?r:Math.max(f,r+u),x=o+i-a+s.length;Rn(n,s,i,a,_,o<g?x+m:Math.max(g+b,x))},as=(n,t)=>{let[e,i]=n.getSelection(),[a,r,o]=ur(n.value,e,i),[s,l]=Iu(n);return by(n,a,a.map(t?c=>c.slice(ca(c)?l-ca(c)%l:0):c=>c&&s.repeat(l-ca(c)%l)+c),r,o,e,i),da(n)},vy=(n,t)=>{let[e,i]=Iu(n);return Rn(n,e.repeat(i-(t-mr(n.value,t))%i)),da(n)},Mc=(n,t)=>{let e=n.getSelection(),i=n.value;t&&(e[0]=e[1]=ns(i,e[1]));let[a,r]=Iu(n),[o,s]=e,l=vc[Su(n,o)]?.autoIndent,c=Math.floor(ca(uy(i,o))/r)*r,d=l?.[0]?.(e,i,n)?r:0,m=l?.[1]?.(e,i,n),u=`
`+a.repeat(c+d)+(m?`
`+a.repeat(c):"");if(u[1]||i[s])return Rn(n,u,o,s,o+c+d+1),da(n)},Eu=(n,t)=>{let[e,i]=n.getSelection(),a=n.value,r=t?mr(a,e)-1:e,o=t?i:a.indexOf(`
`,i)+1;if(r>-1&&o>0){let[s,l,c]=ur(a,r,o),d=s[t?"shift":"pop"](),m=(d.length+1)*(t?-1:1);s[t?"push":"unshift"](d),Rn(n,s.join(`
`),l,c,e+m,i+m)}return da(n)},Du=(n,t)=>{let[e,i]=n.getSelection(),a=n.value,[r,o,s]=ur(a,e,i),l=r.join(`
`),c=t?0:l.length+1;return Rn(n,l+`
`+l,o,s,e+c,i+c),da(n)},Mu=(n,t)=>(n.container.scrollBy(0,py(n.container,"lineHeight")*(t?-1:1)),!0),_y=n=>{let[t,e,i]=n.getSelection(),a=n.value,[r,o,s]=ur(a,t,e),l=i>"f"?e-s+r.pop().length:t-o,c=ns(a,s+1)-s-1;return Rn(n,"",o-!!o,s+!o,o+Math.min(l,c)),da(n)},Nu=(n,t)=>{let[e,i]=n.getSelection(),a=n.value,r=t?e:mr(a,e),o=vc[Su(n,r)]||{},{line:s,block:l}=o.getComments?.(n,r,a)||o.comments||{},[c,d,m]=ur(a,e,i),u=c.length-1;if(t){if(l){let[f,g]=l,b=a.slice(e,i),_=a.slice(0,e).search(pr(f)+" ?$");_+1&&RegExp("^ ?"+pr(g)).test(a.slice(i))?Rn(n,b,_,i+(a[i]==" ")+g.length,_,_+i-e):Rn(n,`${f} ${b} ${g}`,e,i,e+f.length+1,i+f.length+1)}}else if(s){let f=pr(s),g=RegExp(`^\\s*(${f} ?|$)`),b=RegExp(f+" ?"),_=!/\S/.test(a.slice(d,m));by(n,c,c.map(!_&&c.every(x=>g.test(x))?x=>x.replace(b,""):x=>_||/\S/.test(x)?x.replace(/(?!\s)/,s+" "):x),d,m,e,i)}else if(l){let[f,g]=l,b=c[0],_=ca(b),x=b.startsWith(f,_)&&c[u].endsWith(g);c[0]=b.replace(x?RegExp(pr(f)+" ?"):/(?!\s)/,x?"":f+" ");let w=c[0].length-b.length;c[u]=x?c[u].replace(RegExp(` ?${pr(g)}$`),""):c[u]+" "+g;let C=c.join(`
`),F=_+d,A=F>e?e:Math.max(e+w,F),Q=F>i-(e!=i)?i:Math.min(Math.max(F,i+w),d+C.length);Rn(n,C,d,m,A,Math.max(A,Q))}return l||s&&!t?da(n):!1},uN={Tab(n){if(!is){let[t,e]=n.getSelection();return t==e?vy(n,t):as(n)}},"8+Tab":n=>!is&&as(n,!0),"1+ArrowDown":n=>Eu(n),"1+ArrowUp":n=>Eu(n,!0),"9+ArrowDown":n=>Du(n),"9+ArrowUp":n=>Du(n,!0),Enter:n=>Mc(n),"8+Enter":n=>Mc(n),"Mod+Enter":n=>Mc(n,!0),"Mod+]":n=>as(n),"Mod+[":n=>as(n,!0),"8+Mod+k":_y,"Mod+/":n=>Nu(n),"9+a":n=>Nu(n,!0),[yn?"10+m":"2+m"]:()=>(is=!is,!0),[`2+${yn?"Page":"Arrow"}Down`]:n=>Mu(n),[`2+${yn?"Page":"Arrow"}Up`]:n=>Mu(n,!0)};var hN=[["2+ ","Trigger suggestion"],["mod+i","Trigger suggestion"],...yn?[["1+Escape","Trigger suggestion"]]:[],["2+ ","Toggle suggestion documentation"],["mod+i","Toggle suggestion documentation"],["Tab","Insert suggestion"],["Enter","Insert suggestion"],["Escape","Close completion widget"],["Escape","Clear tab stops"],["Tab","Select next tab stop"],["8+Tab","Select previous tab stop"],["ArrowUp","Select previous suggestion"],["ArrowDown","Select next suggestion"],["PageUp","Select first visible suggestion"],["PageDown","Select last visible suggestion"]],Dc=yn?5:1,fN=[["mod+f","Start search"],[yn?"5+f":"2+h","Start replacing"],["mod+g","Find next match"],["mod+8+g","Find previous match"],["f3","Find next match"],["8+f3","Find previous match"],["Enter","Select next match"],["8+Enter","Select previous match"],["Escape","Close search widget"],["Enter","Replace match"],[`${yn?4:3}+Enter`,"Replace all matches"],[Dc+"+r","Toggle regex search"],[Dc+"+p","Toggle case preservation"],[Dc+"+w","Toggle whole word search"],[Dc+"+l","Toggle find in selection"]];var Au=(n=999)=>{let t=0,e,i,a=!1,r,o,s,l,c,d,m=[],u=_=>{_>=n&&(_--,m.shift()),m.splice(t=_,n,[e.value,d(),d()])},f=_=>{m[_]&&(c.value=m[_][0],c.setSelectionRange(...m[_][_<t?2:1]),e.update(),e.extensions.cursor?.scrollIntoView(),t=_,i=!1)},g=(_,x)=>{_.extensions.history=g,e=_,d=_.getSelection,c||u(0),c=_.textarea,_.on("selectionChange",()=>{i=a,a=!1}),ki(_,"beforeinput",w=>{let C=w.data,F=w.inputType,A=w.timeStamp;/history/.test(F)?(f(t+(F[7]=="U"?-1:1)),oa(w)):(l=i&&(r==F||A-s<99&&F.slice(-4)=="Drop")&&!la&&(C!=" "||o==C))||(m[t][2]=la||d()),a=!0,o=C,s=A,r=F}),ki(_,"input",()=>u(t+!l)),ki(_,"keydown",w=>{if(!x.readOnly){let C=Cu(w),F=w.keyCode,A=C==Ec&&F==90,Q=C==Ec+8&&F==90||!yn&&C==Ec&&F==89;A?(f(t-1),oa(w)):Q&&(f(t+1),oa(w))}}),_.addExtensions({update(){_.value!=c.value&&b()}})},b=g.clear=()=>{u(0),i=!1};return g.has=_=>t+_ in m,g.go=_=>f(t+_),g};var gN=_u('<div style=display:flex;align-items:flex-start;justify-content:flex-end><button type=button dir=ltr style=display:none class=pce-copy><svg width=1.2em aria-hidden=true viewBox="0 0 16 16" overflow=visible stroke-linecap=round fill=none stroke=currentColor><rect x=4 y=4 width=11 height=11 rx=1 /><path d="m12 2a1 1 0 00-1-1H2A1 1 0 001 2v9a1 1 0 001 1">'),yy=(n="Copy",t="Copied!")=>e=>{let i=gN(),a=i.firstChild,r=o=>a.setAttribute("aria-label",o);rn(a,"click",()=>{r(t),navigator.clipboard?.writeText(e.extensions.codeFold?.fullCode??e.value)||(e.textarea.select(),on.execCommand("copy"),ku(e,0))}),rn(a,"pointerenter",()=>r(n)),r(n),fy(e,i)};var hr=/\/\/.*|\/\*[^]*?(?:\*\/|$)/g;var Nc=/[()[\]{}.,:;]/,fr=/\b(?:false|true)\b/,Tu={punctuation:/\./};_e.webmanifest=_e.json={property:/"(?:\\.|[^\\\n"])*"(?=\s*:)/g,string:/"(?:\\.|[^\\\n"])*"/g,comment:hr,number:/-?\b\d+(?:\.\d+)?(?:e[+-]?\d+)?\b/i,operator:/:/,punctuation:/[[\]{},]/,boolean:fr,null:{pattern:/\bnull\b/,alias:"keyword"}};var rs=(n,t)=>n.replace(/<(\d+)>/g,(e,i)=>`(?:${t[+i]})`),gr=(n,t,e)=>RegExp(rs(n,t),e);var Ou=/[*&][^\s[\]{},]+/,Ru=/!(?:<[\w%#;/?:@&=$,.!~*'()[\]+-]+>|(?:[a-zA-Z\d-]*!)?[\w%#;/?:@&=$.~*'()+-]+)?/,Pu=`(?:${Ru.source}(?:[ 	]+${Ou.source})?|${Ou.source}(?:[ 	]+${Ru.source})?)`,bN=rs("(?:[^\\s\0-\\x08\\x0e-\\x1f!\"#%&'*,:>?@[\\]{}`|\\x7f-\\x84\\x86-\\x9f\\ud800-\\udfff\\ufffe\\uffff-]|[?:-]<0>)(?:[ 	]*(?:(?![#:])<0>|:<0>))*",["[^\\s\0-\\x08\\x0e-\\x1f,[\\]{}\\x7f-\\x84\\x86-\\x9f\\ud800-\\udfff\\ufffe\\uffff]"]),xy=`"(?:\\\\.|[^\\\\
"])*"|'(?:\\\\.|[^\\\\
'])*'`,os=(n,t)=>gr(`([:,[{-]\\s*(?:\\s<0>[ 	]+)?)<1>(?=[ 	]*(?:$|,|\\]|\\}|(?:
\\s*)?#))`,[Pu,n],t);_e.yml=_e.yaml={scalar:{pattern:gr(`([:-]\\s*(?:\\s<0>[ 	]+)?[|>])[ 	]*(?:(
[ 	]+)\\S.*(?:\\2.+)*)`,[Pu]),lookbehind:!0,alias:"string"},comment:/#.*/,key:{pattern:gr(`((?:^|[:,[{
?-])[ 	]*(?:<0>[ 	]+)?)<1>(?=\\s*:\\s)`,[Pu,"(?:"+bN+"|"+xy+")"],"g"),lookbehind:!0,alias:"atrule"},directive:{pattern:/(^[ 	]*)%.+/m,lookbehind:!0,alias:"important"},datetime:{pattern:os("\\d{4}-\\d\\d?-\\d\\d?(?:[tT]|[ 	]+)\\d\\d?:\\d\\d:\\d\\d(?:\\.\\d*)?(?:[ 	]*(?:Z|[+-]\\d\\d?(?::\\d\\d)?))?|\\d{4}-\\d\\d-\\d\\d|\\d\\d?:\\d\\d(?::\\d\\d(?:\\.\\d*)?)?","m"),lookbehind:!0,alias:"number"},boolean:{pattern:os("false|true","im"),lookbehind:!0,alias:"important"},null:{pattern:os("null|~","im"),lookbehind:!0,alias:"important"},string:{pattern:os(xy,"mg"),lookbehind:!0},number:{pattern:os("[+-]?(?:0x[a-f\\d]+|0o[0-7]+|(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:e[+-]?\\d+)?|\\.inf|\\.nan)","im"),lookbehind:!0},tag:Ru,important:Ou,punctuation:/---|[:[\]{},|>?-]|\.{3}/};var Ic=(n,t)=>({pattern:RegExp("(^(?:"+n+"):[ 	]*)\\S[^]*","i"),lookbehind:!0,alias:t&&"language-"+t,inside:t}),wy=_e.http={"request-line":{pattern:/^(?:CONNECT|DELETE|GET|HEAD|OPTIONS|PATCH|POST|PRI|PUT|SEARCH|TRACE)\s(?:https?:\/)?\/\S*\sHTTP\/[\d.]+/m,inside:{method:{pattern:/^\w+/,alias:"property"},"request-target":{pattern:/^(\s)[h/]\S*/,lookbehind:!0,alias:"url",inside:"uri"},"http-version":{pattern:/(?!^)\S+/,alias:"property"}}},"response-status":{pattern:/^HTTP\/[\d.]+ \d+ .+/m,inside:{"http-version":{pattern:/^\S+/,alias:"property"},"status-code":{pattern:/^( )\d+(?= )/,lookbehind:!0,alias:"number"},"reason-phrase":{pattern:/(?!^).+/,alias:"string"}}}};["application/javascript","application/json","application/xml","text/xml","text/html","text/css","text/plain"].forEach(n=>{var t=n.split("/")[1],e=n[10]&&!t[4]?"(?:"+n+"|\\w+/(?:[\\w.-]+\\+)+"+t+"(?![\\w.+-]))":n;wy[n.replace("/","-")]={pattern:RegExp("(content-type:\\s*"+e+`(?:;.*)?(?:
[\\w-].*)*
)[^\\w 	-][^]*`,"i"),lookbehind:!0,alias:"language-"+t,inside:t=="json"?_e.json||"js":t}});wy.header={pattern:/^[\w-]+:.+(?:\n[ 	].+)*/m,inside:{"header-value":[Ic("Content-Security-Policy","csp"),Ic("Public-Key-Pins(?:-Report-Only)?","hpkp"),Ic("Strict-Transport-Security","hsts"),Ic("[^:]+")],"header-name":{pattern:/^[^:]+/,alias:"keyword"},punctuation:/^:/}};var Fu=/\b(?:abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|exports|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|interface|long|module|native|new|non-sealed|null|opens?|package|permits|private|protected|provides|public|record(?!\s*[()[\]{}%~.,:;?%&|^=<>/*+-])|requires|return|sealed|short|static|strictfp|super|switch|synchronized|this|throws?|to|transient|transitive|try|uses|var|void|volatile|while|with|yield)\b/,ss="(?:[a-z]\\w*\\s*\\.\\s*)*(?:[A-Z]\\w*\\s*\\.\\s*)*",Lu={pattern:/^[a-z]\w*(?:\s*\.\s*[a-z]\w*)*(?:\s*\.)?/,inside:Tu},Bu={namespace:Lu,punctuation:/\./},Sy={pattern:RegExp(`(^|[^\\w.])${ss}[A-Z](?:[\\d_A-Z]*[a-z]\\w*)?\\b`),lookbehind:!0,inside:Bu};_e.java={"doc-comment":{pattern:/\/\*\*(?!\/)[^]*?(?:\*\/|$)/g,alias:"comment",inside:"javadoc"},comment:hr,"triple-quoted-string":{pattern:/"""[ 	]*\n(?:\\.|[^\\])*?"""/g,alias:"string"},char:/'(?:\\.|[^\\\n']){1,6}'/g,string:{pattern:/(^|[^\\])"(?:\\.|[^\\\n"])*"/g,lookbehind:!0},annotation:{pattern:/(^|[^.])@\w+(?:\s*\.\s*\w+)*/,lookbehind:!0,alias:"punctuation"},generics:{pattern:/<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&))*>)*>)*>)*>/,inside:{"class-name":Sy,keyword:Fu,punctuation:/[().,:<>]/,operator:/[?&|]/}},import:[{pattern:RegExp(`(\\bimport\\s+)${ss}(?:[A-Z]\\w*|\\*)(?=\\s*;)`),lookbehind:!0,inside:{namespace:Lu,punctuation:/\./,operator:/\*/,"class-name":/\w+/}},{pattern:RegExp(`(\\bimport\\s+static\\s+)${ss}(?:\\w+|\\*)(?=\\s*;)`),lookbehind:!0,alias:"static",inside:{namespace:Lu,static:/\b\w+$/,punctuation:/\./,operator:/\*/,"class-name":/\w+/}}],namespace:{pattern:RegExp(`(\\b(?:exports|import(?:\\s+static)?|module|opens?|package|provides|requires|to|transitive|uses|with)\\s+)(?!${Fu.source})[a-z]\\w*(?:\\.[a-z]\\w*)*\\.?`),lookbehind:!0,inside:Tu},"class-name":[Sy,{pattern:RegExp(`(^|[^\\w.])${ss}[A-Z]\\w*(?=\\s+\\w+\\s*[;,=()]|\\s*(?:\\[[\\s,]*\\]\\s*)?::\\s*new\\b)`),lookbehind:!0,inside:Bu},{pattern:RegExp(`(\\b(?:class|enum|extends|implements|instanceof|interface|new|record|throws)\\s+)${ss}[A-Z]\\w*\\b`),lookbehind:!0,inside:Bu}],keyword:Fu,boolean:fr,function:{pattern:/\b\w+(?=\()|(::\s*)[a-z_]\w*/,lookbehind:!0},number:/\b0b[01][01_]*l?\b|\b0x(?:\.[a-f\d_p+-]+|[a-f\d_]+(?:\.[a-f\d_p+-]+)?)\b|(?:\b\d[\d_]*(?:\.[\d_]*)?|\B\.\d[\d_]*)(?:e[+-]?\d[\d_]*)?[dfl]?/i,constant:/\b[A-Z][A-Z_\d]+\b/,operator:{pattern:/(^|[^.])(?:<<=?|>>>?=?|->|--|\+\+|&&|\|\||::|[?:~]|[%&|^!=<>/*+-]=?)/m,lookbehind:!0},punctuation:Nc};var ju={"interpolation-punctuation":{pattern:/^.\{?|\}$/g,alias:"punctuation"},expression:{pattern:/[^]+/}};ju.expression.inside=_e.kts=_e.kt=_e.kotlin={"string-literal":[{pattern:/"""(?:[^$]|\$(?:(?!\{)|\{[^{}]*\}))*?"""/,alias:"multiline",inside:{interpolation:{pattern:/\$(?:[a-z_]\w*|\{[^{}]*\})/i,inside:ju},string:/[^]+/}},{pattern:/"(?:\\.|[^\\\n"$]|\$(?:(?!\{)|\{[^{}]*\}))*"/,alias:"singleline",inside:{interpolation:{pattern:/((?:^|[^\\])(?:\\\\)*)\$(?:[a-z_]\w*|\{[^{}]*\})/i,lookbehind:!0,inside:ju},string:/[^]+/}}],char:/'(?:[^\\\n']|\\(?:.|u[a-fA-F\d]{0,4}))'/g,comment:hr,annotation:{pattern:/\B@(?:\w+:)?(?:[A-Z]\w*|\[[^\]]+\])/,alias:"builtin"},keyword:{pattern:/(^|[^.])\b(?:abstract|actual|annotation|as|break|by|catch|class|companion|const|constructor|continue|crossinline|data|do|dynamic|else|enum|expect|external|final|finally|for|fun|get|if|import|in|infix|init|inline|inner|interface|internal|is|lateinit|noinline|null|object|open|operator|out|override|package|private|protected|public|reified|return|sealed|set|super|suspend|tailrec|this|throw|to|try|typealias|val|var|vararg|when|where|while)\b/,lookbehind:!0},boolean:fr,label:{pattern:/\b\w+@|@\w+/,alias:"symbol"},function:{pattern:/(?:`[^\n`]+`|\b\w+)(?=\s*\()|(\.)(?:`[^\n`]+`|\w+)(?=\s*\{)/g,lookbehind:!0},number:/\b(?:0[xX][a-fA-F\d]+(?:_[a-fA-F\d]+)*|0[bB][01]+(?:_[01]+)*|\d+(?:_\d+)*(?:\.\d+(?:_\d+)*)?(?:[eE][+-]?\d+(?:_\d+)*)?[fFL]?)\b/,operator:/--|\+\+|&&|\|\||->|[!=]==|!!|[%!=<>/*+-]=?|[?:]:?|\.\.|\b(?:and|inv|shl|u?shr|x?or)\b/,punctuation:Nc};var Vu=[{pattern:/&[a-z\d]{1,8};/i,alias:"named-entity"},/&#x?[a-f\d]{1,8};/i],ky=/<!--(?:(?!<!--)[^])*?-->/g,Cy={pattern:/<\/?(?!\d)[^\s/=>$<%]+(?:\s(?:\s*[^\s/=>]+(?:\s*=\s*(?!\s)(?:"[^"]*"|'[^']*'|[^\s"'=>]+(?=[\s>]))?|(?=[\s/>])))+)?\s*\/?>/g,inside:{punctuation:/^<\/?|\/?>$/,tag:{pattern:/^\S+/,inside:{namespace:/^[^:]+:/}},"attr-value":[{pattern:/(=\s*)(?:"[^"]*"|'[^']*'|[^\s"'>]+)/g,lookbehind:!0,inside:{punctuation:/^["']|["']$/g,entity:Vu}}],"attr-equals":/=/,"attr-name":{pattern:/\S+/,inside:{namespace:/^[^:]+:/}}}};_e.rss=_e.atom=_e.ssml=_e.xml={comment:ky,prolog:/<\?[^]+?\?>/g,doctype:{pattern:/<!DOCTYPE(?:[^>"'[\]]|"[^"]*"|'[^']*')+(?:\[(?:[^<"'\]]|"[^"]*"|'[^']*'|<(?!!--)|<!--(?:[^-]|-(?!->))*-->)*\]\s*)?>/gi,inside:{"internal-subset":{pattern:/(\[)[^]+(?=\]\s*>$)/,lookbehind:!0,inside:"xml"},string:/"[^"]*"|'[^']*'/,punctuation:/^<!|[>[\]]/,"doctype-tag":/^DOCTYPE/i,name:/\S+/}},cdata:/<!\[CDATA\[[^]*?\]\]>/gi,tag:Cy,entity:Vu,"markup-bracket":{pattern:/[()[\]{}]/,alias:"punctuation"}};var $u=(n,t)=>(n["language-"+t]={pattern:/[^]+/,inside:t},n),Ey=(n,t)=>({pattern:RegExp(`(<${n}[^>]*>)(?!</${n}>)(?:<!\\[CDATA\\[(?:[^\\]]|\\](?!\\]>))*\\]\\]>|(?!<!\\[CDATA\\[)[^])+?(?=</${n}>)`,"gi"),lookbehind:!0,inside:$u({"included-cdata":{pattern:/<!\[CDATA\[[^]*?\]\]>/i,inside:$u({cdata:/^<!\[CDATA\[|\]\]>$/i},t)}},t)}),Dy=(n,t,e=n)=>({pattern:RegExp(`([\\s"']${n}\\s*=\\s*)(?:"[^"]*"|'[^']*'|[^\\s>]+)`,"gi"),lookbehind:!0,alias:e,inside:$u({punctuation:/^["']|["']$/g},t)}),My=_e.svg=_e.mathml=_e.html=_e.markup=kc(_e.xml);My.tag.inside["attr-value"].unshift(Dy("style","css"),Dy("on[a-z]+","javascript","script"));Cc(My,"cdata",{style:Ey("style","css"),script:Ey("script","javascript")});var vN=[`(?:\\\\.|[^\\\\
]|
(?!
))`],Ac=n=>gr(`((?:^|[^\\\\])(?:\\\\\\\\)*)(?:${n})`,vN,"g"),zu=/(?:\\.|``(?:[^\n`]|`(?!`))+``|`[^\n`]+`|[^\\\n|`])+/,Ny=rs(`\\|?<0>(?:\\|<0>)+\\|?(?:
|(?![\\s\\S]))`,[zu.source]),_N=`\\|?[ 	]*:?-{3,}:?[ 	]*(?:\\|[ 	]*:?-{3,}:?[ 	]*)+\\|?
`,br=_e.md=_e.markdown=kc(_e.html);Cc(br,"prolog",{"front-matter-block":{pattern:/(^(?:\s*\n)?)---(?!.)[^]*?\n---(?!.)/g,lookbehind:!0,inside:{punctuation:/^---|---$/,"front-matter":{pattern:/\S(?:[^]*\S)?/,alias:"language-yaml",inside:"yaml"}}},blockquote:{pattern:/^>(?:[ 	]*>)*/m,alias:"punctuation"},table:{pattern:RegExp("^"+Ny+_N+"(?:"+Ny+")*","m"),inside:{"table-header-row":{pattern:/^.+/,inside:{"table-header":{pattern:zu,alias:"important",inside:br},punctuation:/\|/}},"table-data-rows":{pattern:/(.+\n)[^]+/,lookbehind:!0,inside:{"table-data":{pattern:zu,inside:br},punctuation:/\|/}},"table-line":{pattern:/.+/,inside:{punctuation:/\S+/}}}},"code-snippet":{pattern:/(^|[^\\`])(`+)[^\n`](?:|.*?[^\n`])\2(?!`)/g,lookbehind:!0,alias:"code keyword"},code:[{pattern:/(^[ 	]*\n)(?:    |	).+(?:\n(?:    |	).+)*/gm,lookbehind:!0,alias:"keyword"},{pattern:/^(```+)[^`][^]*?^\1`*$/gm,inside:{punctuation:/^`+|`+$/,"code-language":/^.+/,"code-block":/(?!^)[^]+(?=\n)/,[ra](n,t){var e=hu(n,t),i;return e[5]&&(i=(/[a-z][\w-]*/i.exec(e[1].content.replace(/\b#/g,"sharp").replace(/\b\+\+/g,"pp"))||[""])[0].toLowerCase(),e[3].alias="language-"+i,(t=_e[i])&&(e[3].content=Qo(e[3].content,t))),e}}}],title:[{pattern:/\S.*\n(?:==+|--+)(?=[ 	]*$)/gm,alias:"important",inside:{punctuation:/=+$|-+$/}},{pattern:/(^\s*)#.+/gm,lookbehind:!0,alias:"important",inside:{punctuation:/^#+|#+$/}}],hr:{pattern:/(^\s*)([*-])(?:[ 	]*\2){2,}(?=\s*$)/m,lookbehind:!0,alias:"punctuation"},list:{pattern:/(^\s*)(?:[*+-]|\d+\.)(?=[ 	].)/gm,lookbehind:!0,alias:"punctuation"},"url-reference":{pattern:/!?\[[^\]]+\]:[ 	]+(?:\S+|<(?:\\.|[^\\>])+>)(?:[ 	]+(?:"(?:\\.|[^\\"])*"|'(?:\\.|[^\\'])*'|\((?:\\.|[^\\)])*\)))?/g,inside:{variable:{pattern:/^(!?\[)[^\]]+/,lookbehind:!0},string:/(?:"(?:\\.|[^\\"])*"|'(?:\\.|[^\\'])*'|\((?:\\.|[^\\)])*\))$/,punctuation:/^[[\]!:]|<|>/},alias:"url"},bold:{pattern:Ac("\\b__(?:(?!_)<0>|_(?:(?!_)<0>)+_)+__\\b|\\*\\*(?:(?!\\*)<0>|\\*(?:(?!\\*)<0>)+\\*)+\\*\\*"),lookbehind:!0,inside:{content:{pattern:/(^..)[^]+(?=..)/,lookbehind:!0,inside:{}},punctuation:/../}},italic:{pattern:Ac("\\b_(?:(?!_)<0>|__(?:(?!_)<0>)+__)+_\\b|\\*(?:(?!\\*)<0>|\\*\\*(?:(?!\\*)<0>)+\\*\\*)+\\*"),lookbehind:!0,inside:{content:{pattern:/(?!^)[^]+(?=.)/,inside:{}},punctuation:/./}},strike:{pattern:Ac("(~~?)(?:(?!~)<0>)+\\2"),lookbehind:!0,inside:{punctuation:/^~~?|~~?$/,content:{pattern:/[^]+/,inside:{}}}},url:{pattern:Ac('!?\\[(?:(?!\\])<0>)+\\](?:\\([^\\s)]+(?:[ 	]+"(?:\\\\.|[^\\\\"])*")?\\)|[ 	]?\\[(?:(?!\\])<0>)+\\])'),lookbehind:!0,inside:{operator:/^!/,content:{pattern:/(^\[)[^\]]+(?=\])/,lookbehind:!0,inside:{}},variable:{pattern:/(^\][ 	]?\[)[^\]]+(?=\]$)/,lookbehind:!0},url:{pattern:/(^\]\()[^\s)]+/,lookbehind:!0},string:{pattern:/(^[ 	]+)"(?:\\.|[^\\"])*"(?=\)$)/,lookbehind:!0},"markup-bracket":br["markup-bracket"]}}});["url","bold","italic","strike"].forEach(n=>{["url","bold","italic","strike","code-snippet","markup-bracket"].forEach(t=>{n!=t&&(br[n].inside.content.inside[t]=br[t])})});var yN=["editorContainer"],vr=class n{code=vh("");language=ht("markdown");readonly=ht(!1,{transform:t=>t=="true"});editor=void 0;editorContainer;ngAfterViewInit(){this.editorContainer.nativeElement&&(this.editor=this.initEditor())}ngOnChanges(t){t.code.previousValue!==t.code.currentValue&&this.editor?.setOptions({value:t.code.currentValue})}initEditor(){let t=Jo(this.editorContainer.nativeElement,{value:this.code(),language:this.language(),lineNumbers:!1,wordWrap:!0,readOnly:this.readonly(),onUpdate:e=>{this.code.set(e)}});return t.addExtensions(yy(),dy(!0),gy(),Au()),this.code.subscribe(()=>{this.editor?.update()}),t}static \u0275fac=function(e){return new(e||n)};static \u0275cmp=T({type:n,selectors:[["app-prism-editor"]],viewQuery:function(e,i){if(e&1&&qe(yN,5),e&2){let a;H(a=U())&&(i.editorContainer=a.first)}},inputs:{code:[1,"code"],language:[1,"language"],readonly:[1,"readonly"]},outputs:{code:"codeChange"},features:[_t],decls:2,vars:0,consts:[["editorContainer",""]],template:function(e,i){e&1&&ln(0,"div",null,0)},styles:["[_nghost-%COMP%] > div[_ngcontent-%COMP%]{margin-bottom:.5em}"]})};function xN(n,t){if(n&1&&(y(0,"div",3)(1,"span"),S(2,"Operation description"),v(),P(3,"markdown",17),v()),n&2){let e=M();h(3),R("data",e.operation().description)}}function wN(n,t){if(n&1&&(y(0,"div",3)(1,"span"),S(2,"Message description"),v(),P(3,"markdown",17),v()),n&2){let e=M();h(3),R("data",e.operation().message.description)}}function SN(n,t){if(n&1&&(y(0,"div",3)(1,"span"),S(2,"Content-Type"),v(),y(3,"span",18),S(4),v()()),n&2){let e=M();h(4),Ge(e.operation().message.contentType)}}function kN(n,t){if(n&1&&(y(0,"div",3)(1,"span"),S(2,"Reply to"),v(),y(3,"span")(4,"mat-chip-set")(5,"a",4)(6,"mat-chip"),P(7,"mat-icon",19),S(8),v()()(),S(9," with "),y(10,"mat-chip-set")(11,"a",4)(12,"mat-chip"),P(13,"mat-icon",6),S(14),v()()()()()),n&2){let e=M();h(5),R("href",si(e.operation().reply.channelAnchorUrl),lt),h(3),ae(" ",e.operation().reply.channelName," "),h(3),R("href",si(e.operation().reply.messageAnchorUrl),lt),h(3),ae(" ",e.operation().reply.messageName," ")}}function CN(n,t){if(n&1&&(y(0,"a",4)(1,"mat-chip"),P(2,"mat-icon",20),S(3),v()()),n&2){let e=t.$implicit;R("href",si(e.anchorUrl),lt),h(3),ae(" ",e.name," ")}}function EN(n,t){if(n&1){let e=ut();y(0,"app-prism-editor",22),Ir("codeChange",function(a){at(e);let r=M(3);return Nr(r.operationBindingExampleString,a)||(r.operationBindingExampleString=a),rt(a)}),v()}if(n&2){let e=M(3);Mr("code",e.operationBindingExampleString)}}function DN(n,t){if(n&1&&(y(0,"div"),E(1,EN,1,1,"app-prism-editor",21),v()),n&2){let e=M(2);h(),D(e.operationBindingExampleString?1:-1)}}function MN(n,t){if(n&1&&(y(0,"div",0)(1,"div",5)(2,"h6"),S(3,"Operation Binding"),v()(),y(4,"div",5),E(5,DN,2,1,"div"),v()()),n&2){let e=M();h(5),D(e.operation().protocol?5:-1)}}function NN(n,t){if(n&1){let e=ut();y(0,"app-prism-editor",22),Ir("codeChange",function(a){at(e);let r=M(2);return Nr(r.messageBindingExampleString,a)||(r.messageBindingExampleString=a),rt(a)}),v()}if(n&2){let e=M(2);Mr("code",e.messageBindingExampleString)}}function IN(n,t){n&1&&(y(0,"span")(1,"i"),S(2,"none"),v()())}function AN(n,t){if(n&1&&(y(0,"div",0)(1,"div",5)(2,"h6"),S(3,"Message Binding"),v()(),y(4,"div",5)(5,"div"),E(6,NN,1,1,"app-prism-editor",21),E(7,IN,3,0,"span"),v()()()),n&2){let e=M();h(6),D(e.messageBindingExampleString?6:-1),h(),D(e.messageBindingExampleString?-1:7)}}function TN(n,t){if(n&1&&(y(0,"a",4)(1,"mat-chip"),P(2,"mat-icon",6),S(3),v()()),n&2){let e=M(2);R("href",e.headers().anchorUrl,lt),h(3),ae(" ",e.headers().title," ")}}function ON(n,t){if(n&1&&P(0,"app-schema",7),n&2){let e=M(2);R("schema",e.headers())}}function RN(n,t){if(n&1){let e=ut();y(0,"div",0)(1,"div",5)(2,"h6"),S(3,"Headers"),v(),y(4,"mat-chip-set"),E(5,TN,4,2,"a",4),v(),E(6,ON,1,1,"app-schema",7),v(),y(7,"div",5)(8,"app-prism-editor",23),Ir("codeChange",function(a){at(e);let r=M();return Nr(r.headersExample.value,a)||(r.headersExample.value=a),rt(a)}),v()()()}if(n&2){let e=M();h(5),D(e.headers().anchorUrl!==e.initSchema.anchorUrl?5:-1),h(),D(e.headers()?6:-1),h(2),Mr("code",e.headersExample.value)}}function PN(n,t){if(n&1&&P(0,"app-schema",7),n&2){let e=M();R("schema",e.defaultSchema())}}var Tc=class n{constructor(t,e,i,a){this.asyncApiService=t;this.publisherService=e;this.uiService=i;this.snackBar=a}asyncApiService;publisherService;uiService;snackBar;channelName=ht.required();operation=ht.required();initSchema=ts;defaultSchema=ne(ts);defaultExample=ne(es);originalDefaultExample=ne(es);exampleLanguage=cn(()=>{let t=this.operation().message.contentType;return t.includes("avro")?"json":t.split("/").pop()||"json"});headers=ne(ts);headersExample=es;originalHeadersExample=ne(es);operationBindingExampleString;messageBindingExampleString;isShowBindings=ne(Me.DEFAULT_SHOW_BINDINGS);isShowHeaders=ne(Me.DEFAULT_SHOW_HEADERS);canPublish=ne(!1);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(t=>{let e=t.components.schemas,i=this.operation().message.payload;if(i.ts_type==="ref"){let r=i.name.slice(i.name.lastIndexOf("/")+1),o=e.get(r);this.defaultSchema.set(o),this.defaultExample.set(o.example||sa),this.originalDefaultExample.set(o.example||sa)}else this.defaultSchema.set(i),this.defaultExample.set(i.example||sa),this.originalDefaultExample.set(i.example||sa);let a=this.operation().message.headers;a?this.headers.set(e.get(a?.name)):this.headers.set(ts),this.headersExample=this.headers().example||sa,this.originalHeadersExample.set(this.headers().example||sa),this.operationBindingExampleString=new Zt(this.operation().bindings[this.operation().protocol])?.value,this.messageBindingExampleString=this.createBindingExample(this.operation().message.bindings.get(this.operation().protocol))?.value,this.reset(),this.publisherService.canPublish(this.operation().protocol).subscribe(r=>{this.canPublish.set(r)})}),this.uiService.isShowBindings$.subscribe(t=>this.isShowBindings.set(t)),this.uiService.isShowHeaders$.subscribe(t=>this.isShowHeaders.set(t))}createBindingExample(t){if(t==null)return;let e={};return Object.keys(t).forEach(i=>{i!=="bindingVersion"&&(e[i]=this.getExampleValue(t[i]))}),new Zt(e)}getExampleValue(t){if(typeof t=="string")return t;if("examples"in t&&typeof t.examples=="object")return t.examples[0]}reset(){this.defaultExample.set(new Zt(this.originalDefaultExample().rawValue)),this.headersExample=new Zt(this.originalHeadersExample().rawValue)}publish(){let t=this.defaultExample().value,e=this.operation().message.payload.name,i=this.headersExample.value,a=this.messageBindingExampleString;try{let r=i===""?{}:Ad("Unable to convert headers to JSON object (or is empty)",()=>JSON.parse(i||"")),o=a===""?{}:Ad("Unable to convert bindings to JSON object (or is empty)",()=>JSON.parse(a||""));this.publisherService.publish(this.operation().protocol||"not-supported-protocol",this.channelName(),t,e,r,o).subscribe(s=>this.handlePublishSuccess(),s=>this.handlePublishError(s))}catch(r){this.snackBar.open("Unable to create publishing payload: "+r?.message,"ERROR",{duration:3e3})}}handlePublishSuccess(){return this.snackBar.open("Example payload sent to: "+this.channelName(),"PUBLISHED",{duration:3e3})}handlePublishError(t){let e="Publish failed";return t?.status===ge.NOT_FOUND&&(e+=": no publisher was provided for "+this.operation().protocol),this.snackBar.open(e,"ERROR",{duration:4e3})}static \u0275fac=function(e){return new(e||n)(Oe(mt),Oe(er),Oe(Me),Oe(lo))};static \u0275cmp=T({type:n,selectors:[["app-channel-operation"]],inputs:{channelName:[1,"channelName"],operation:[1,"operation"]},decls:45,vars:15,consts:[[1,"row"],[1,"width-12-of-12","width-12-of-12-s"],[1,"table","margin-vertical-1em"],[1,"table-row"],[3,"href"],[1,"width-6-of-12","width-12-of-12-s"],["matChipAvatar","","fontIcon","schema"],[3,"schema"],[3,"codeChange","code","language"],[1,"flex","space-between"],[1,"flex","gap-8"],["mat-raised-button","",3,"cdkCopyToClipboard"],["fontIcon","content_copy"],["mat-raised-button","",3,"click"],["fontIcon","restart_alt"],["mat-raised-button","",3,"click","disabled"],["fontIcon","send"],[3,"data"],[1,"text-console"],["matChipAvatar","","fontIcon","swap_vert"],["matChipAvatar","","fontIcon","dns"],["language","json","readonly","true",3,"code"],["language","json","readonly","true",3,"codeChange","code"],["language","json",3,"codeChange","code"]],template:function(e,i){e&1&&(y(0,"div",0)(1,"div",1)(2,"div",2)(3,"div",3)(4,"span"),S(5,"Channel"),v(),y(6,"span"),S(7),v()(),E(8,xN,4,1,"div",3),E(9,wN,4,1,"div",3),E(10,SN,5,1,"div",3),E(11,kN,15,6,"div",3),y(12,"div",3)(13,"span"),S(14,"Servers"),v(),y(15,"span")(16,"mat-chip-set"),Ye(17,CN,4,3,"a",4,Ke),v()()()()()(),E(19,MN,6,1,"div",0),E(20,AN,8,2,"div",0),E(21,RN,9,3,"div",0),y(22,"div",0)(23,"div",5)(24,"h6"),S(25,"Payload"),v(),y(26,"mat-chip-set")(27,"a",4)(28,"mat-chip"),P(29,"mat-icon",6),S(30),v()()(),E(31,PN,1,1,"app-schema",7),v(),y(32,"div",5)(33,"app-prism-editor",8),Ir("codeChange",function(r){return Nr(i.defaultExample().value,r)||(i.defaultExample().value=r),r}),v(),y(34,"div",9)(35,"div",10)(36,"button",11),P(37,"mat-icon",12),S(38," Copy "),v(),y(39,"button",13),ye("click",function(){return i.reset()}),P(40,"mat-icon",14),S(41," Reset "),v()(),y(42,"button",15),ye("click",function(){return i.publish()}),P(43,"mat-icon",16),S(44," Publish "),v()()()()),e&2&&(h(7),Ge(i.operation().channelName),h(),D(i.operation().description?8:-1),h(),D(i.operation().message.description?9:-1),h(),D(i.operation().message.contentType?10:-1),h(),D(i.operation().reply?11:-1),h(6),Xe(i.operation().servers),h(2),D(i.isShowBindings()?19:-1),h(),D(i.isShowBindings()?20:-1),h(),D(i.isShowHeaders()?21:-1),h(6),R("href",i.operation().message.payload.anchorUrl,lt),h(3),ae(" ",i.operation().message.payload.title," "),h(),D(i.defaultSchema()?31:-1),h(2),Mr("code",i.defaultExample().value),R("language",i.exampleLanguage()),h(3),R("cdkCopyToClipboard",i.defaultExample().value),h(6),R("disabled",!i.canPublish))},dependencies:[Dn,En,vn,Qn,Si,lr,Ot,Lt,vr,dr,Ho,N_,Gn,Fa],styles:[".table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:first-child{vertical-align:middle}[_nghost-%COMP%]     .mdc-evolution-chip-set__chips{max-width:100%}[_nghost-%COMP%]     .mat-mdc-standard-chip .mdc-evolution-chip__cell--primary, [_nghost-%COMP%]     .mat-mdc-standard-chip .mdc-evolution-chip__action--primary, [_nghost-%COMP%]     .mat-mdc-standard-chip .mat-mdc-chip-action-label{overflow:hidden}"]})};var FN=(n,t)=>({"send-badge":n,"receive-badge":t});function LN(n,t){if(n&1&&(y(0,"div"),P(1,"app-prism-editor",4),v()),n&2){let e=M(2).$implicit,i=M();h(),R("code",si(i.JSON.stringify(e.bindings,null,2)))}}function BN(n,t){if(n&1&&(y(0,"div",2)(1,"div",3)(2,"h6"),S(3,"Channel Binding"),v()(),y(4,"div",3),E(5,LN,2,2,"div"),v()()),n&2){let e=M().$implicit;h(5),D(e.bindings?5:-1)}}function jN(n,t){if(n&1&&(y(0,"span",7),S(1),v()),n&2){let e=M().$implicit;h(),ae(" ",e.operation.protocol," ")}}function VN(n,t){n&1&&P(0,"br")}function $N(n,t){if(n&1&&(y(0,"mat-card",5)(1,"mat-card-header")(2,"mat-card-title"),S(3),v(),y(4,"span",6),E(5,jN,2,1,"span",7),y(6,"span",8),S(7),v()()(),y(8,"mat-card-content"),P(9,"app-channel-operation",9),v()(),E(10,VN,1,0,"br")),n&2){let e=t.$implicit,i=t.$index,a=t.$count,r=M().$implicit;R("id",e.anchorIdentifier),h(2),fe("data-testid",r.anchorIdentifier),h(),ae(" ",e.operation.message.title," "),h(2),D(e.operation.protocol?5:-1),h(),R("ngClass",_a(9,FN,e.operation.operationType==="send",e.operation.operationType==="receive")),h(),ae(" ",e.operation.operationType," "),h(2),R("channelName",r.name)("operation",e.operation),h(),D(i!==a-1?10:-1)}}function zN(n,t){n&1&&P(0,"br")}function HN(n,t){if(n&1&&(y(0,"article",0)(1,"h3"),P(2,"mat-icon",1),S(3),v(),E(4,BN,6,1,"div",2),Ye(5,$N,11,12,null,null,Ke),E(7,zN,1,0,"br"),v()),n&2){let e=t.$implicit,i=t.$index,a=t.$count,r=M();R("id",e.anchorIdentifier),h(3),ae(" ",e.name),h(),D(r.isShowBindings()?4:-1),h(),Xe(e.operations),h(2),D(i!==a-1?7:-1)}}var Oc=class n{constructor(t,e){this.asyncApiService=t;this.uiService=e}asyncApiService;uiService;channels=ne([]);isShowBindings=ne(Me.DEFAULT_SHOW_BINDINGS);JSON=JSON;ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(t=>{this.channels.set(t.channels)}),this.uiService.isShowBindings$.subscribe(t=>this.isShowBindings.set(t))}static \u0275fac=function(e){return new(e||n)(Oe(mt),Oe(Me))};static \u0275cmp=T({type:n,selectors:[["app-channels"]],decls:4,vars:0,consts:[["appNavigationTarget","",3,"id"],["fontIcon","swap_vert"],[1,"row"],[1,"width-6-of-12","width-12-of-12-s"],["language","json","readonly","true",3,"code"],["appearance","outlined","appNavigationTarget","",3,"id"],[1,"flex","gap-16","padding-horizontal-1em","height-fix-content"],[1,"badge","protocol-badge"],[1,"badge",3,"ngClass"],[3,"channelName","operation"]],template:function(e,i){e&1&&(y(0,"h2"),S(1,"Channels"),v(),Ye(2,HN,8,4,"article",0,Ke)),e&2&&(h(2),Xe(i.channels()))},dependencies:[vr,wn,Pr,Zn,ar,or,sr,rr,Tc,sn,Lt],styles:[".badge[_ngcontent-%COMP%]{border-radius:4px;padding:.3em;font-size:smaller;text-transform:uppercase}.protocol-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-protocol);background-color:var(--%NS%springwolf-badge-color-background-protocol)}.send-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-send);background-color:var(--%NS%springwolf-badge-color-background-send)}.receive-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-receive);background-color:var(--%NS%springwolf-badge-color-background-receive)}"]})};var UN=["scrollableElement"],qN=["*"],Hu=(n,t)=>({selected:n,expanded:t});function GN(n,t){if(n&1&&P(0,"mat-icon",5),n&2){let e=M().$implicit;R("fontIcon",si(e.icon))}}function WN(n,t){if(n&1&&(y(0,"span"),S(1),v()),n&2){let e=t.$implicit;At(Uc("badge ",e.type,"-badge")),h(),Ge(e.value)}}function KN(n,t){if(n&1&&(y(0,"span"),S(1),v()),n&2){let e=t.$implicit;At(Uc("badge ",e.type,"-badge")),h(),Ge(e.value)}}function YN(n,t){if(n&1&&(y(0,"li",2)(1,"span")(2,"a",6),S(3),v(),Ye(4,KN,2,4,"span",7,Ke),v()()),n&2){let e=t.$implicit;R("ngClass",_a(3,Hu,e.selected,e.expanded)),h(2),R("href",e.href,lt),h(),ae(" ",e.name.join("\u200B")," "),h(),Xe(e.tags)}}function XN(n,t){if(n&1&&(y(0,"li",2)(1,"span")(2,"a",6),S(3),v(),Ye(4,WN,2,4,"span",7,Ke),v(),y(6,"ul"),Ye(7,YN,6,6,"li",2,Ke),v()()),n&2){let e=t.$implicit;R("ngClass",_a(3,Hu,e.selected,e.expanded)),h(2),R("href",e.href,lt),h(),ae(" ",e.name.join("\u200B")," "),h(),Xe(e.tags),h(3),Xe(e.children)}}function ZN(n,t){if(n&1&&(y(0,"ul",2)(1,"li")(2,"span"),E(3,GN,1,2,"mat-icon",5),y(4,"b")(5,"a",6),S(6),v()()(),y(7,"ul"),Ye(8,XN,9,6,"li",2,Ke),v()()()),n&2){let e=t.$implicit;R("ngClass",_a(4,Hu,e.selected,e.expanded)),h(3),D(e.icon?3:-1),h(2),R("href",e.href,lt),h(),ae(" ",e.name.join("\u200B")," "),h(2),Xe(e.children)}}var Rc=class n{constructor(t,e){this.asyncApiService=t;this.location=e}asyncApiService;location;scrollableElement;navigationTargets;navigation=ne([]);ngOnInit(){this.location.subscribe(this.scrollToUrlLocation),this.asyncApiService.getAsyncApi().subscribe(t=>{let e=[];e.push({name:["Info"],icon:"info",href:kt.BASE_URL+"info"});let i=Array.from(t.servers.keys()).map(o=>({name:this.splitForWordBreaking(o),href:kt.BASE_URL+t.servers.get(o).anchorIdentifier,tags:[{type:"protocol",value:t.servers.get(o).protocol}]}));e.push({name:["Servers"],icon:"dns",href:kt.BASE_URL+"servers",children:i});let a={name:["Channels & Operations"],icon:"swap_vert",href:kt.BASE_URL+"channels",children:[]};t.channels.forEach(o=>{let s=o.operations.map(d=>({name:this.splitForWordBreaking(d.operation.message.title),href:kt.BASE_URL+d.anchorIdentifier,tags:[{type:"operation-"+d.operation.operationType,value:d.operation.operationType}]})),l=s.flatMap(d=>d.tags).flatMap(d=>d),c={name:this.splitForWordBreaking(o.name),href:kt.BASE_URL+o.anchorIdentifier,tags:this.filterAndSort(l,"value"),children:s};a.children.push(c)}),e.push(a);let r={name:["Schemas"],icon:"schema",href:kt.BASE_URL+"schemas",children:[]};t.components.schemas.forEach(o=>{r.children.push({name:this.splitForWordBreaking(o.title),href:kt.BASE_URL+""+o.anchorIdentifier})}),e.push(r),this.navigation.set(e),this.scrollToUrlLocation()})}splitForWordBreaking=t=>t.split(/(?<=[.,_/\-])/);filterAndSort(t,e){let i=new Set;return t.filter(r=>{let o=JSON.stringify(r);return i.has(o)?!1:i.add(o)}).sort((r,o)=>r[e]<o[e]?-1:r[e]>o[e]?1:0)}ngAfterViewInit(){this.scrollableElement.nativeElement.addEventListener("scroll",this.updateNavigationSelection)}updateNavigationSelection=()=>{let t="",e=this.scrollableElement.nativeElement.scrollTop;document.querySelectorAll("[appNavigationTarget]").forEach(i=>{let a=i,r=a.offsetTop,o=a.offsetHeight;e>=r&&e<r+o&&(t=kt.BASE_URL+""+a.getAttribute("id"))}),this.navigation().forEach(i=>{let a=!1;i.children?.forEach(r=>{let o=!1;r.children?.forEach(s=>{s.selected=t==s.href,s.expanded=s.selected,o=o||s.selected}),r.selected=t==r.href||o,a=a||r.selected,r.children?.forEach(s=>{s.expanded=r.selected})}),i.selected=t==i.href||a,i.children?.forEach(r=>{r.expanded=i.selected}),i.expanded=!0}),this.navigation.set([...this.navigation()])};scrollToUrlLocation=()=>{setTimeout(()=>{document.getElementById(window.location.hash.substring(1))?.scrollIntoView(),this.updateNavigationSelection()},10)};static \u0275fac=function(e){return new(e||n)(Oe(mt),Oe(xa))};static \u0275cmp=T({type:n,selectors:[["app-sidenav"]],contentQueries:function(e,i,a){if(e&1&&ot(a,sn,5),e&2){let r;H(r=U())&&(i.navigationTargets=r)}},viewQuery:function(e,i){if(e&1&&qe(UN,5),e&2){let a;H(a=U())&&(i.scrollableElement=a.first)}},ngContentSelectors:qN,decls:8,vars:0,consts:[["scrollableElement",""],["mode","side","opened","",1,"sidenav","width-s-hide"],[1,"entry",3,"ngClass"],[1,"width-s-margin-reset"],[2,"overflow-y","auto","height","100%"],[3,"fontIcon"],[3,"href"],[3,"class"]],template:function(e,i){e&1&&(de(),y(0,"mat-sidenav-container")(1,"mat-sidenav",1),Ye(2,ZN,10,7,"ul",2,Ke),v(),y(4,"mat-sidenav-content",3)(5,"div",4,0),j(7),v()()()),e&2&&(h(2),Xe(i.navigation()))},dependencies:[Yo,uu,ey,pc,Ot,Lt,wn,Pr],styles:["a[_ngcontent-%COMP%]{color:#373737;text-decoration:none}.sidenav[_ngcontent-%COMP%]   ul[_ngcontent-%COMP%]{list-style-type:none;padding-inline-start:.5em}.sidenav[_ngcontent-%COMP%]   .entry.selected[_ngcontent-%COMP%] > span[_ngcontent-%COMP%] > a[_ngcontent-%COMP%]{font-weight:700;color:var(--%NS%mat-app-text-color)}.sidenav[_ngcontent-%COMP%]   .entry[_ngcontent-%COMP%]:not(.expanded){display:none}.sidenav[_ngcontent-%COMP%]   .entry[_ngcontent-%COMP%] > span[_ngcontent-%COMP%]{display:flex;justify-content:space-between}.sidenav[_ngcontent-%COMP%]   .badge[_ngcontent-%COMP%]{text-transform:uppercase;font-size:smaller;border-radius:.3em;padding:0 .2em;margin:.2em .3em auto}.sidenav[_ngcontent-%COMP%]   .badge.operation-send-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-send);background-color:var(--%NS%springwolf-badge-color-background-send)}.sidenav[_ngcontent-%COMP%]   .badge.operation-receive-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-receive);background-color:var(--%NS%springwolf-badge-color-background-receive)}.sidenav[_ngcontent-%COMP%]   .badge.protocol-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-protocol);background-color:var(--%NS%springwolf-badge-color-background-protocol)}"]})};function QN(n,t){if(n&1&&(y(0,"button",9),S(1," Group "),v()),n&2){M();let e=Kt(16);R("matMenuTriggerFor",e)}}function JN(n,t){if(n&1){let e=ut();y(0,"button",15),ye("click",function(){let a=at(e).$implicit,r=M();return rt(r.changeGroup(a.value))}),y(1,"mat-icon"),S(2),v(),S(3),v()}if(n&2){let e=t.$implicit,i=M();h(2),Ge(i.isGroup()==e.value?"radio_button_checked":"radio_button_unchecked"),h(),ae(" ",e.viewValue," ")}}var Pc=class n{constructor(t,e,i){this.uiService=t;this.asyncApiService=e;this.assetService=i}uiService;asyncApiService;assetService;groups=ne([]);isGroup=ne(Me.DEFAULT_GROUP);isShowBindings=ne(Me.DEFAULT_SHOW_BINDINGS);isShowHeaders=ne(Me.DEFAULT_SHOW_HEADERS);title=ne("");ngOnInit(){this.assetService.load(),this.uiService.isShowBindings$.subscribe(t=>this.isShowBindings.set(t)),this.uiService.isShowHeaders$.subscribe(t=>this.isShowHeaders.set(t)),this.uiService.isGroup$.subscribe(t=>this.isGroup.set(t)),this.uiService.uiConfig.subscribe(t=>{if(t.groups.length>0){let e=t.groups.map(i=>({value:i.name,viewValue:i.name}));this.groups.set([{value:Me.DEFAULT_GROUP,viewValue:Me.DEFAULT_GROUP},...e])}}),this.asyncApiService.getAsyncApi().subscribe(t=>{this.title.set(t.info.title)})}toggleIsShowBindings(){this.uiService.toggleIsShowBindings(!this.isShowBindings())}toggleIsShowHeaders(){this.uiService.toggleIsShowHeaders(!this.isShowHeaders())}changeGroup(t){this.uiService.changeGroup(t)}static \u0275fac=function(e){return new(e||n)(Oe(Me),Oe(mt),Oe(Da))};static \u0275cmp=T({type:n,selectors:[["app-header"]],decls:30,vars:5,consts:[["settings","matMenu"],["group","matMenu"],[1,"row","space-between"],["href","https://www.springwolf.dev","target","_blank",1,"flex","flex-column","items-center"],["src","assets/springwolf-logo.png","alt","Logo","height","1024","width","1024",1,"logo"],[1,"width-s-hide"],[1,"flex","flex-column","items-center"],["mat-button","","data-testid","settings",3,"matMenuTriggerFor"],[2,"color","black"],["mat-menu-item","","data-testid","settings-group-menu",3,"matMenuTriggerFor"],["mat-menu-item",""],["mat-menu-item","","data-testid","settings-bindings",3,"click"],["mat-menu-item","","data-testid","settings-headers",3,"click"],["href","https://github.com/springwolf/springwolf-core","target","_blank"],["src","assets/github.png","alt","Github","height","1024","width","1024",1,"github"],["mat-menu-item","",3,"click"]],template:function(e,i){if(e&1&&(y(0,"mat-toolbar",2)(1,"span")(2,"a",3),P(3,"img",4),S(4," Springwolf "),v()(),y(5,"h1",5),S(6),v(),y(7,"div",6)(8,"button",7)(9,"mat-icon",8),S(10,"settings"),v(),S(11," Settings "),v(),y(12,"mat-menu",null,0),E(14,QN,2,1,"button",9),y(15,"mat-menu",null,1),Ye(17,JN,4,2,"button",10,Ke),v(),y(19,"button",11),ye("click",function(){return i.toggleIsShowBindings()}),y(20,"mat-icon"),S(21),v(),S(22," Show bindings "),v(),y(23,"button",12),ye("click",function(){return i.toggleIsShowHeaders()}),y(24,"mat-icon"),S(25),v(),S(26," Show headers "),v()(),S(27," \xA0 "),y(28,"a",13),P(29,"img",14),v()()()),e&2){let a=Kt(13);h(6),Ge(i.title()),h(2),R("matMenuTriggerFor",a),h(6),D(0<i.groups().length?14:-1),h(3),Xe(i.groups()),h(4),Ge(i.isShowBindings()?"check_box":"check_box_outline_blank"),h(4),Ge(i.isShowHeaders()?"check_box":"check_box_outline_blank")}},dependencies:[$o,E_,Gn,Fa,Vo,nr,jo,C_,Ot,Lt],styles:[".logo[_ngcontent-%COMP%]{height:3em;width:3em;display:block}.github[_ngcontent-%COMP%]{height:2em;width:2em;display:block;filter:invert(100%)}[_nghost-%COMP%]     label, a[_ngcontent-%COMP%]{color:var(--%NS%mat-toolbar-container-text-color)}a[_ngcontent-%COMP%]{text-decoration:none}a[_ngcontent-%COMP%]:hover{color:#d3d3d3}[_nghost-%COMP%]     mat-icon{height:3em;width:3em;transform:scale(.75);filter:invert(100%)}"]})};var Fc=class n{static \u0275fac=function(e){return new(e||n)};static \u0275cmp=T({type:n,selectors:[["app-root"]],decls:11,vars:0,consts:[[1,"mat-typography"],["appNavigationTarget","","id","info"],["appNavigationTarget","","id","servers"],["appNavigationTarget","","id","channels"],["appNavigationTarget","","id","schemas"]],template:function(e,i){e&1&&(P(0,"app-header"),y(1,"main",0)(2,"app-sidenav")(3,"article",1),P(4,"app-info"),v(),y(5,"article",2),P(6,"app-servers"),v(),y(7,"article",3),P(8,"app-channels"),v(),y(9,"article",4),P(10,"app-schemas"),v()()())},dependencies:[uc,A_,Dn,Pc,Rc,xc,wc,Oc,yc,sn],styles:[".app-header[_ngcontent-%COMP%]{position:fixed;z-index:100;width:100%}main[_ngcontent-%COMP%]{margin:0;height:calc(100% - 64px);padding:0}article[_ngcontent-%COMP%]{margin:2em 1em}"]})};Ls.production&&void 0;id(Fc,k_).catch(n=>console.error(n));
