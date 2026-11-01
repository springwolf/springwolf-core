import{$ as Y,$a as Gc,$b as vh,A as Cr,Aa as lh,Ab as fh,B as Er,Ba as ch,Bb as gh,C as zc,Ca as dh,Cb as Kc,D as Hc,Da as mh,Db as Kt,E as ds,Ea as ph,Eb as $n,F as Ln,Fa as uh,Fb as ee,G as ms,Ga as lt,Gb as At,H as _t,Ha as _a,Hb as S,I as Gt,Ia as hh,Ib as Ge,J as Ae,Ja as Mr,Jb as ae,K as ai,Ka as hs,Kb as Ir,L as Pe,La as ht,Lb as bh,M as te,Ma as h,Mb as Ar,N as V,Na as li,Nb as Tr,O as I,Oa as yt,Ob as Or,P as B,Pa as ct,Pb as vs,Q as p,Qa as Nr,Qb as _s,R as ua,Ra as Oe,Rb as ya,S as th,Sa as fs,Sb as mi,T as nh,Ta as wn,Tb as Yc,U as Bn,Ua as gs,Ub as Je,V as ps,Va as T,Vb as Xc,W as mt,Wa as $,Wb as Zc,X as at,Xa as q,Xb as xa,Y as rt,Ya as qc,Yb as ys,Z as ha,Za as Ve,Zb as xs,_ as re,_a as Wt,_b as Rr,a as N,aa as jn,ab as ci,ac as cn,b as De,ba as Te,bb as fe,bc as dn,ca as K,cb as E,cc as _h,d as L,da as Ai,db as D,dc as yh,e as zy,ea as ne,eb as Ye,ec as gt,f as Mt,fa as Dr,fb as Xe,fc as xh,g as Nt,ga as ih,gb as Ze,gc as Pr,h as z,ha as ri,hb as P,hc as wh,i as ni,ia as fa,ib as y,ic as Sh,j as Ku,ja as ga,jb as v,jc as We,k as Ue,ka as ah,kb as R,kc as kh,l as Yu,la as Uc,lb as Qe,lc as ve,m as Me,ma as rh,mb as et,mc as pi,n as Xu,na as Ti,nb as ln,nc as Ch,o as Zu,oa as oi,ob as Wc,oc as ws,p as Qu,pa as ba,pb as ft,q as Ju,qa as pt,qb as di,r as It,ra as st,rb as xe,s as Ke,sa as J,sb as bs,t as $c,ta as X,tb as M,u as cs,ua as Vn,ub as de,v as Sr,va as oh,vb as j,w as ii,wa as us,wb as ot,x as Fn,xa as si,xb as qe,y as kr,ya as va,yb as H,z as eh,za as sh,zb as U}from"./chunk-D77BYUQG.js";var uo=L(Ee=>{"use strict";Object.defineProperty(Ee,"__esModule",{value:!0});Ee.regexpCode=Ee.getEsmExportName=Ee.getProperty=Ee.safeStringify=Ee.stringify=Ee.strConcat=Ee.addCodeArg=Ee.str=Ee._=Ee.nil=Ee._Code=Ee.Name=Ee.IDENTIFIER=Ee._CodeOrName=void 0;var mo=class{};Ee._CodeOrName=mo;Ee.IDENTIFIER=/^[a-z$_][a-z$_0-9]*$/i;var Ki=class extends mo{constructor(n){if(super(),!Ee.IDENTIFIER.test(n))throw new Error("CodeGen: name must be a valid identifier");this.str=n}toString(){return this.str}emptyStr(){return!1}get names(){return{[this.str]:1}}};Ee.Name=Ki;var en=class extends mo{constructor(n){super(),this._items=typeof n=="string"?[n]:n}toString(){return this.str}emptyStr(){if(this._items.length>1)return!1;let n=this._items[0];return n===""||n==='""'}get str(){var n;return(n=this._str)!==null&&n!==void 0?n:this._str=this._items.reduce((e,i)=>`${e}${i}`,"")}get names(){var n;return(n=this._names)!==null&&n!==void 0?n:this._names=this._items.reduce((e,i)=>(i instanceof Ki&&(e[i.str]=(e[i.str]||0)+1),e),{})}};Ee._Code=en;Ee.nil=new en("");function Gg(t,...n){let e=[t[0]],i=0;for(;i<n.length;)sm(e,n[i]),e.push(t[++i]);return new en(e)}Ee._=Gg;var om=new en("+");function Wg(t,...n){let e=[po(t[0])],i=0;for(;i<n.length;)e.push(om),sm(e,n[i]),e.push(om,po(t[++i]));return uS(e),new en(e)}Ee.str=Wg;function sm(t,n){n instanceof en?t.push(...n._items):n instanceof Ki?t.push(n):t.push(gS(n))}Ee.addCodeArg=sm;function uS(t){let n=1;for(;n<t.length-1;){if(t[n]===om){let e=hS(t[n-1],t[n+1]);if(e!==void 0){t.splice(n-1,3,e);continue}t[n++]="+"}n++}}function hS(t,n){if(n==='""')return t;if(t==='""')return n;if(typeof t=="string")return n instanceof Ki||t[t.length-1]!=='"'?void 0:typeof n!="string"?`${t.slice(0,-1)}${n}"`:n[0]==='"'?t.slice(0,-1)+n.slice(1):void 0;if(typeof n=="string"&&n[0]==='"'&&!(t instanceof Ki))return`"${t}${n.slice(1)}`}function fS(t,n){return n.emptyStr()?t:t.emptyStr()?n:Wg`${t}${n}`}Ee.strConcat=fS;function gS(t){return typeof t=="number"||typeof t=="boolean"||t===null?t:po(Array.isArray(t)?t.join(","):t)}function bS(t){return new en(po(t))}Ee.stringify=bS;function po(t){return JSON.stringify(t).replace(/\u2028/g,"\\u2028").replace(/\u2029/g,"\\u2029")}Ee.safeStringify=po;function vS(t){return typeof t=="string"&&Ee.IDENTIFIER.test(t)?new en(`.${t}`):Gg`[${t}]`}Ee.getProperty=vS;function _S(t){if(typeof t=="string"&&Ee.IDENTIFIER.test(t))return new en(`${t}`);throw new Error(`CodeGen: invalid export name: ${t}, use explicit $id name mapping`)}Ee.getEsmExportName=_S;function yS(t){return new en(t.toString())}Ee.regexpCode=yS});var dm=L(Vt=>{"use strict";Object.defineProperty(Vt,"__esModule",{value:!0});Vt.ValueScope=Vt.ValueScopeName=Vt.Scope=Vt.varKinds=Vt.UsedValueState=void 0;var jt=uo(),lm=class extends Error{constructor(n){super(`CodeGen: "code" for ${n} not defined`),this.value=n.value}},El;(function(t){t[t.Started=0]="Started",t[t.Completed=1]="Completed"})(El||(Vt.UsedValueState=El={}));Vt.varKinds={const:new jt.Name("const"),let:new jt.Name("let"),var:new jt.Name("var")};var Dl=class{constructor({prefixes:n,parent:e}={}){this._names={},this._prefixes=n,this._parent=e}toName(n){return n instanceof jt.Name?n:this.name(n)}name(n){return new jt.Name(this._newName(n))}_newName(n){let e=this._names[n]||this._nameGroup(n);return`${n}${e.index++}`}_nameGroup(n){var e,i;if(!((i=(e=this._parent)===null||e===void 0?void 0:e._prefixes)===null||i===void 0)&&i.has(n)||this._prefixes&&!this._prefixes.has(n))throw new Error(`CodeGen: prefix "${n}" is not allowed in this scope`);return this._names[n]={prefix:n,index:0}}};Vt.Scope=Dl;var Ml=class extends jt.Name{constructor(n,e){super(e),this.prefix=n}setValue(n,{property:e,itemIndex:i}){this.value=n,this.scopePath=(0,jt._)`.${new jt.Name(e)}[${i}]`}};Vt.ValueScopeName=Ml;var xS=(0,jt._)`\n`,cm=class extends Dl{constructor(n){super(n),this._values={},this._scope=n.scope,this.opts=De(N({},n),{_n:n.lines?xS:jt.nil})}get(){return this._scope}name(n){return new Ml(n,this._newName(n))}value(n,e){var i;if(e.ref===void 0)throw new Error("CodeGen: ref must be passed in value");let a=this.toName(n),{prefix:r}=a,o=(i=e.key)!==null&&i!==void 0?i:e.ref,s=this._values[r];if(s){let d=s.get(o);if(d)return d}else s=this._values[r]=new Map;s.set(o,a);let l=this._scope[r]||(this._scope[r]=[]),c=l.length;return l[c]=e.ref,a.setValue(e,{property:r,itemIndex:c}),a}getValue(n,e){let i=this._values[n];if(i)return i.get(e)}scopeRefs(n,e=this._values){return this._reduceValues(e,i=>{if(i.scopePath===void 0)throw new Error(`CodeGen: name "${i}" has no value`);return(0,jt._)`${n}${i.scopePath}`})}scopeCode(n=this._values,e,i){return this._reduceValues(n,a=>{if(a.value===void 0)throw new Error(`CodeGen: name "${a}" has no value`);return a.value.code},e,i)}_reduceValues(n,e,i={},a){let r=jt.nil;for(let o in n){let s=n[o];if(!s)continue;let l=i[o]=i[o]||new Map;s.forEach(c=>{if(l.has(c))return;l.set(c,El.Started);let d=e(c);if(d){let m=this.opts.es5?Vt.varKinds.var:Vt.varKinds.const;r=(0,jt._)`${r}${m} ${c} = ${d};${this.opts._n}`}else if(d=a?.(c))r=(0,jt._)`${r}${d}${this.opts._n}`;else throw new lm(c);l.set(c,El.Completed)})}return r}};Vt.ValueScope=cm});var ue=L(pe=>{"use strict";Object.defineProperty(pe,"__esModule",{value:!0});pe.or=pe.and=pe.not=pe.CodeGen=pe.operators=pe.varKinds=pe.ValueScopeName=pe.ValueScope=pe.Scope=pe.Name=pe.regexpCode=pe.stringify=pe.getProperty=pe.nil=pe.strConcat=pe.str=pe._=void 0;var _e=uo(),fn=dm(),xi=uo();Object.defineProperty(pe,"_",{enumerable:!0,get:function(){return xi._}});Object.defineProperty(pe,"str",{enumerable:!0,get:function(){return xi.str}});Object.defineProperty(pe,"strConcat",{enumerable:!0,get:function(){return xi.strConcat}});Object.defineProperty(pe,"nil",{enumerable:!0,get:function(){return xi.nil}});Object.defineProperty(pe,"getProperty",{enumerable:!0,get:function(){return xi.getProperty}});Object.defineProperty(pe,"stringify",{enumerable:!0,get:function(){return xi.stringify}});Object.defineProperty(pe,"regexpCode",{enumerable:!0,get:function(){return xi.regexpCode}});Object.defineProperty(pe,"Name",{enumerable:!0,get:function(){return xi.Name}});var Tl=dm();Object.defineProperty(pe,"Scope",{enumerable:!0,get:function(){return Tl.Scope}});Object.defineProperty(pe,"ValueScope",{enumerable:!0,get:function(){return Tl.ValueScope}});Object.defineProperty(pe,"ValueScopeName",{enumerable:!0,get:function(){return Tl.ValueScopeName}});Object.defineProperty(pe,"varKinds",{enumerable:!0,get:function(){return Tl.varKinds}});pe.operators={GT:new _e._Code(">"),GTE:new _e._Code(">="),LT:new _e._Code("<"),LTE:new _e._Code("<="),EQ:new _e._Code("==="),NEQ:new _e._Code("!=="),NOT:new _e._Code("!"),OR:new _e._Code("||"),AND:new _e._Code("&&"),ADD:new _e._Code("+")};var Xn=class{optimizeNodes(){return this}optimizeNames(n,e){return this}},mm=class extends Xn{constructor(n,e,i){super(),this.varKind=n,this.name=e,this.rhs=i}render({es5:n,_n:e}){let i=n?fn.varKinds.var:this.varKind,a=this.rhs===void 0?"":` = ${this.rhs}`;return`${i} ${this.name}${a};`+e}optimizeNames(n,e){if(n[this.name.str])return this.rhs&&(this.rhs=za(this.rhs,n,e)),this}get names(){return this.rhs instanceof _e._CodeOrName?this.rhs.names:{}}},Nl=class extends Xn{constructor(n,e,i){super(),this.lhs=n,this.rhs=e,this.sideEffects=i}render({_n:n}){return`${this.lhs} = ${this.rhs};`+n}optimizeNames(n,e){if(!(this.lhs instanceof _e.Name&&!n[this.lhs.str]&&!this.sideEffects))return this.rhs=za(this.rhs,n,e),this}get names(){let n=this.lhs instanceof _e.Name?{}:N({},this.lhs.names);return Al(n,this.rhs)}},pm=class extends Nl{constructor(n,e,i,a){super(n,i,a),this.op=e}render({_n:n}){return`${this.lhs} ${this.op}= ${this.rhs};`+n}},um=class extends Xn{constructor(n){super(),this.label=n,this.names={}}render({_n:n}){return`${this.label}:`+n}},hm=class extends Xn{constructor(n){super(),this.label=n,this.names={}}render({_n:n}){return`break${this.label?` ${this.label}`:""};`+n}},fm=class extends Xn{constructor(n){super(),this.error=n}render({_n:n}){return`throw ${this.error};`+n}get names(){return this.error.names}},gm=class extends Xn{constructor(n){super(),this.code=n}render({_n:n}){return`${this.code};`+n}optimizeNodes(){return`${this.code}`?this:void 0}optimizeNames(n,e){return this.code=za(this.code,n,e),this}get names(){return this.code instanceof _e._CodeOrName?this.code.names:{}}},ho=class extends Xn{constructor(n=[]){super(),this.nodes=n}render(n){return this.nodes.reduce((e,i)=>e+i.render(n),"")}optimizeNodes(){let{nodes:n}=this,e=n.length;for(;e--;){let i=n[e].optimizeNodes();Array.isArray(i)?n.splice(e,1,...i):i?n[e]=i:n.splice(e,1)}return n.length>0?this:void 0}optimizeNames(n,e){let{nodes:i}=this,a=i.length;for(;a--;){let r=i[a];r.optimizeNames(n,e)||(wS(n,r.names),i.splice(a,1))}return i.length>0?this:void 0}get names(){return this.nodes.reduce((n,e)=>Zi(n,e.names),{})}},Zn=class extends ho{render(n){return"{"+n._n+super.render(n)+"}"+n._n}},bm=class extends ho{},$a=class extends Zn{};$a.kind="else";var Yi=class t extends Zn{constructor(n,e){super(e),this.condition=n}render(n){let e=`if(${this.condition})`+super.render(n);return this.else&&(e+="else "+this.else.render(n)),e}optimizeNodes(){super.optimizeNodes();let n=this.condition;if(n===!0)return this.nodes;let e=this.else;if(e){let i=e.optimizeNodes();e=this.else=Array.isArray(i)?new $a(i):i}if(e)return n===!1?e instanceof t?e:e.nodes:this.nodes.length?this:new t(Kg(n),e instanceof t?[e]:e.nodes);if(!(n===!1||!this.nodes.length))return this}optimizeNames(n,e){var i;if(this.else=(i=this.else)===null||i===void 0?void 0:i.optimizeNames(n,e),!!(super.optimizeNames(n,e)||this.else))return this.condition=za(this.condition,n,e),this}get names(){let n=super.names;return Al(n,this.condition),this.else&&Zi(n,this.else.names),n}};Yi.kind="if";var Xi=class extends Zn{};Xi.kind="for";var vm=class extends Xi{constructor(n){super(),this.iteration=n}render(n){return`for(${this.iteration})`+super.render(n)}optimizeNames(n,e){if(super.optimizeNames(n,e))return this.iteration=za(this.iteration,n,e),this}get names(){return Zi(super.names,this.iteration.names)}},_m=class extends Xi{constructor(n,e,i,a){super(),this.varKind=n,this.name=e,this.from=i,this.to=a}render(n){let e=n.es5?fn.varKinds.var:this.varKind,{name:i,from:a,to:r}=this;return`for(${e} ${i}=${a}; ${i}<${r}; ${i}++)`+super.render(n)}get names(){let n=Al(super.names,this.from);return Al(n,this.to)}},Il=class extends Xi{constructor(n,e,i,a){super(),this.loop=n,this.varKind=e,this.name=i,this.iterable=a}render(n){return`for(${this.varKind} ${this.name} ${this.loop} ${this.iterable})`+super.render(n)}optimizeNames(n,e){if(super.optimizeNames(n,e))return this.iterable=za(this.iterable,n,e),this}get names(){return Zi(super.names,this.iterable.names)}},fo=class extends Zn{constructor(n,e,i){super(),this.name=n,this.args=e,this.async=i}render(n){return`${this.async?"async ":""}function ${this.name}(${this.args})`+super.render(n)}};fo.kind="func";var go=class extends ho{render(n){return"return "+super.render(n)}};go.kind="return";var ym=class extends Zn{render(n){let e="try"+super.render(n);return this.catch&&(e+=this.catch.render(n)),this.finally&&(e+=this.finally.render(n)),e}optimizeNodes(){var n,e;return super.optimizeNodes(),(n=this.catch)===null||n===void 0||n.optimizeNodes(),(e=this.finally)===null||e===void 0||e.optimizeNodes(),this}optimizeNames(n,e){var i,a;return super.optimizeNames(n,e),(i=this.catch)===null||i===void 0||i.optimizeNames(n,e),(a=this.finally)===null||a===void 0||a.optimizeNames(n,e),this}get names(){let n=super.names;return this.catch&&Zi(n,this.catch.names),this.finally&&Zi(n,this.finally.names),n}},bo=class extends Zn{constructor(n){super(),this.error=n}render(n){return`catch(${this.error})`+super.render(n)}};bo.kind="catch";var vo=class extends Zn{render(n){return"finally"+super.render(n)}};vo.kind="finally";var xm=class{constructor(n,e={}){this._values={},this._blockStarts=[],this._constants={},this.opts=De(N({},e),{_n:e.lines?`
`:""}),this._extScope=n,this._scope=new fn.Scope({parent:n}),this._nodes=[new bm]}toString(){return this._root.render(this.opts)}name(n){return this._scope.name(n)}scopeName(n){return this._extScope.name(n)}scopeValue(n,e){let i=this._extScope.value(n,e);return(this._values[i.prefix]||(this._values[i.prefix]=new Set)).add(i),i}getScopeValue(n,e){return this._extScope.getValue(n,e)}scopeRefs(n){return this._extScope.scopeRefs(n,this._values)}scopeCode(){return this._extScope.scopeCode(this._values)}_def(n,e,i,a){let r=this._scope.toName(e);return i!==void 0&&a&&(this._constants[r.str]=i),this._leafNode(new mm(n,r,i)),r}const(n,e,i){return this._def(fn.varKinds.const,n,e,i)}let(n,e,i){return this._def(fn.varKinds.let,n,e,i)}var(n,e,i){return this._def(fn.varKinds.var,n,e,i)}assign(n,e,i){return this._leafNode(new Nl(n,e,i))}add(n,e){return this._leafNode(new pm(n,pe.operators.ADD,e))}code(n){return typeof n=="function"?n():n!==_e.nil&&this._leafNode(new gm(n)),this}object(...n){let e=["{"];for(let[i,a]of n)e.length>1&&e.push(","),e.push(i),(i!==a||this.opts.es5)&&(e.push(":"),(0,_e.addCodeArg)(e,a));return e.push("}"),new _e._Code(e)}if(n,e,i){if(this._blockNode(new Yi(n)),e&&i)this.code(e).else().code(i).endIf();else if(e)this.code(e).endIf();else if(i)throw new Error('CodeGen: "else" body without "then" body');return this}elseIf(n){return this._elseNode(new Yi(n))}else(){return this._elseNode(new $a)}endIf(){return this._endBlockNode(Yi,$a)}_for(n,e){return this._blockNode(n),e&&this.code(e).endFor(),this}for(n,e){return this._for(new vm(n),e)}forRange(n,e,i,a,r=this.opts.es5?fn.varKinds.var:fn.varKinds.let){let o=this._scope.toName(n);return this._for(new _m(r,o,e,i),()=>a(o))}forOf(n,e,i,a=fn.varKinds.const){let r=this._scope.toName(n);if(this.opts.es5){let o=e instanceof _e.Name?e:this.var("_arr",e);return this.forRange("_i",0,(0,_e._)`${o}.length`,s=>{this.var(r,(0,_e._)`${o}[${s}]`),i(r)})}return this._for(new Il("of",a,r,e),()=>i(r))}forIn(n,e,i,a=this.opts.es5?fn.varKinds.var:fn.varKinds.const){if(this.opts.ownProperties)return this.forOf(n,(0,_e._)`Object.keys(${e})`,i);let r=this._scope.toName(n);return this._for(new Il("in",a,r,e),()=>i(r))}endFor(){return this._endBlockNode(Xi)}label(n){return this._leafNode(new um(n))}break(n){return this._leafNode(new hm(n))}return(n){let e=new go;if(this._blockNode(e),this.code(n),e.nodes.length!==1)throw new Error('CodeGen: "return" should have one node');return this._endBlockNode(go)}try(n,e,i){if(!e&&!i)throw new Error('CodeGen: "try" without "catch" and "finally"');let a=new ym;if(this._blockNode(a),this.code(n),e){let r=this.name("e");this._currNode=a.catch=new bo(r),e(r)}return i&&(this._currNode=a.finally=new vo,this.code(i)),this._endBlockNode(bo,vo)}throw(n){return this._leafNode(new fm(n))}block(n,e){return this._blockStarts.push(this._nodes.length),n&&this.code(n).endBlock(e),this}endBlock(n){let e=this._blockStarts.pop();if(e===void 0)throw new Error("CodeGen: not in self-balancing block");let i=this._nodes.length-e;if(i<0||n!==void 0&&i!==n)throw new Error(`CodeGen: wrong number of nodes: ${i} vs ${n} expected`);return this._nodes.length=e,this}func(n,e=_e.nil,i,a){return this._blockNode(new fo(n,e,i)),a&&this.code(a).endFunc(),this}endFunc(){return this._endBlockNode(fo)}optimize(n=1){for(;n-- >0;)this._root.optimizeNodes(),this._root.optimizeNames(this._root.names,this._constants)}_leafNode(n){return this._currNode.nodes.push(n),this}_blockNode(n){this._currNode.nodes.push(n),this._nodes.push(n)}_endBlockNode(n,e){let i=this._currNode;if(i instanceof n||e&&i instanceof e)return this._nodes.pop(),this;throw new Error(`CodeGen: not in block "${e?`${n.kind}/${e.kind}`:n.kind}"`)}_elseNode(n){let e=this._currNode;if(!(e instanceof Yi))throw new Error('CodeGen: "else" without "if"');return this._currNode=e.else=n,this}get _root(){return this._nodes[0]}get _currNode(){let n=this._nodes;return n[n.length-1]}set _currNode(n){let e=this._nodes;e[e.length-1]=n}};pe.CodeGen=xm;function Zi(t,n){for(let e in n)t[e]=(t[e]||0)+(n[e]||0);return t}function Al(t,n){return n instanceof _e._CodeOrName?Zi(t,n.names):t}function za(t,n,e){if(t instanceof _e.Name)return i(t);if(!a(t))return t;return new _e._Code(t._items.reduce((r,o)=>(o instanceof _e.Name&&(o=i(o)),o instanceof _e._Code?r.push(...o._items):r.push(o),r),[]));function i(r){let o=e[r.str];return o===void 0||n[r.str]!==1?r:(delete n[r.str],o)}function a(r){return r instanceof _e._Code&&r._items.some(o=>o instanceof _e.Name&&n[o.str]===1&&e[o.str]!==void 0)}}function wS(t,n){for(let e in n)t[e]=(t[e]||0)-(n[e]||0)}function Kg(t){return typeof t=="boolean"||typeof t=="number"||t===null?!t:(0,_e._)`!${wm(t)}`}pe.not=Kg;var SS=Yg(pe.operators.AND);function kS(...t){return t.reduce(SS)}pe.and=kS;var CS=Yg(pe.operators.OR);function ES(...t){return t.reduce(CS)}pe.or=ES;function Yg(t){return(n,e)=>n===_e.nil?e:e===_e.nil?n:(0,_e._)`${wm(n)} ${t} ${wm(e)}`}function wm(t){return t instanceof _e.Name?t:(0,_e._)`(${t})`}});var ke=L(he=>{"use strict";Object.defineProperty(he,"__esModule",{value:!0});he.checkStrictMode=he.getErrorPath=he.Type=he.useFunc=he.setEvaluated=he.evaluatedPropsToName=he.mergeEvaluated=he.eachItem=he.unescapeJsonPointer=he.escapeJsonPointer=he.escapeFragment=he.unescapeFragment=he.schemaRefOrVal=he.schemaHasRulesButRef=he.schemaHasRules=he.checkUnknownRules=he.alwaysValidSchema=he.toHash=void 0;var Le=ue(),DS=uo();function MS(t){let n={};for(let e of t)n[e]=!0;return n}he.toHash=MS;function NS(t,n){return typeof n=="boolean"?n:Object.keys(n).length===0?!0:(Qg(t,n),!Jg(n,t.self.RULES.all))}he.alwaysValidSchema=NS;function Qg(t,n=t.schema){let{opts:e,self:i}=t;if(!e.strictSchema||typeof n=="boolean")return;let a=i.RULES.keywords;for(let r in n)a[r]||nb(t,`unknown keyword: "${r}"`)}he.checkUnknownRules=Qg;function Jg(t,n){if(typeof t=="boolean")return!t;for(let e in t)if(n[e])return!0;return!1}he.schemaHasRules=Jg;function IS(t,n){if(typeof t=="boolean")return!t;for(let e in t)if(e!=="$ref"&&n.all[e])return!0;return!1}he.schemaHasRulesButRef=IS;function AS({topSchemaRef:t,schemaPath:n},e,i,a){if(!a){if(typeof e=="number"||typeof e=="boolean")return e;if(typeof e=="string")return(0,Le._)`${e}`}return(0,Le._)`${t}${n}${(0,Le.getProperty)(i)}`}he.schemaRefOrVal=AS;function TS(t){return eb(decodeURIComponent(t))}he.unescapeFragment=TS;function OS(t){return encodeURIComponent(km(t))}he.escapeFragment=OS;function km(t){return typeof t=="number"?`${t}`:t.replace(/~/g,"~0").replace(/\//g,"~1")}he.escapeJsonPointer=km;function eb(t){return t.replace(/~1/g,"/").replace(/~0/g,"~")}he.unescapeJsonPointer=eb;function RS(t,n){if(Array.isArray(t))for(let e of t)n(e);else n(t)}he.eachItem=RS;function Xg({mergeNames:t,mergeToName:n,mergeValues:e,resultToName:i}){return(a,r,o,s)=>{let l=o===void 0?r:o instanceof Le.Name?(r instanceof Le.Name?t(a,r,o):n(a,r,o),o):r instanceof Le.Name?(n(a,o,r),r):e(r,o);return s===Le.Name&&!(l instanceof Le.Name)?i(a,l):l}}he.mergeEvaluated={props:Xg({mergeNames:(t,n,e)=>t.if((0,Le._)`${e} !== true && ${n} !== undefined`,()=>{t.if((0,Le._)`${n} === true`,()=>t.assign(e,!0),()=>t.assign(e,(0,Le._)`${e} || {}`).code((0,Le._)`Object.assign(${e}, ${n})`))}),mergeToName:(t,n,e)=>t.if((0,Le._)`${e} !== true`,()=>{n===!0?t.assign(e,!0):(t.assign(e,(0,Le._)`${e} || {}`),Cm(t,e,n))}),mergeValues:(t,n)=>t===!0?!0:N(N({},t),n),resultToName:tb}),items:Xg({mergeNames:(t,n,e)=>t.if((0,Le._)`${e} !== true && ${n} !== undefined`,()=>t.assign(e,(0,Le._)`${n} === true ? true : ${e} > ${n} ? ${e} : ${n}`)),mergeToName:(t,n,e)=>t.if((0,Le._)`${e} !== true`,()=>t.assign(e,n===!0?!0:(0,Le._)`${e} > ${n} ? ${e} : ${n}`)),mergeValues:(t,n)=>t===!0?!0:Math.max(t,n),resultToName:(t,n)=>t.var("items",n)})};function tb(t,n){if(n===!0)return t.var("props",!0);let e=t.var("props",(0,Le._)`{}`);return n!==void 0&&Cm(t,e,n),e}he.evaluatedPropsToName=tb;function Cm(t,n,e){Object.keys(e).forEach(i=>t.assign((0,Le._)`${n}${(0,Le.getProperty)(i)}`,!0))}he.setEvaluated=Cm;var Zg={};function PS(t,n){return t.scopeValue("func",{ref:n,code:Zg[n.code]||(Zg[n.code]=new DS._Code(n.code))})}he.useFunc=PS;var Sm;(function(t){t[t.Num=0]="Num",t[t.Str=1]="Str"})(Sm||(he.Type=Sm={}));function FS(t,n,e){if(t instanceof Le.Name){let i=n===Sm.Num;return e?i?(0,Le._)`"[" + ${t} + "]"`:(0,Le._)`"['" + ${t} + "']"`:i?(0,Le._)`"/" + ${t}`:(0,Le._)`"/" + ${t}.replace(/~/g, "~0").replace(/\\//g, "~1")`}return e?(0,Le.getProperty)(t).toString():"/"+km(t)}he.getErrorPath=FS;function nb(t,n,e=t.opts.strictSchema){if(e){if(n=`strict mode: ${n}`,e===!0)throw new Error(n);t.self.logger.warn(n)}}he.checkStrictMode=nb});var Qn=L(Em=>{"use strict";Object.defineProperty(Em,"__esModule",{value:!0});var kt=ue(),LS={data:new kt.Name("data"),valCxt:new kt.Name("valCxt"),instancePath:new kt.Name("instancePath"),parentData:new kt.Name("parentData"),parentDataProperty:new kt.Name("parentDataProperty"),rootData:new kt.Name("rootData"),dynamicAnchors:new kt.Name("dynamicAnchors"),vErrors:new kt.Name("vErrors"),errors:new kt.Name("errors"),this:new kt.Name("this"),self:new kt.Name("self"),scope:new kt.Name("scope"),json:new kt.Name("json"),jsonPos:new kt.Name("jsonPos"),jsonLen:new kt.Name("jsonLen"),jsonPart:new kt.Name("jsonPart")};Em.default=LS});var _o=L(Ct=>{"use strict";Object.defineProperty(Ct,"__esModule",{value:!0});Ct.extendErrors=Ct.resetErrorsCount=Ct.reportExtraError=Ct.reportError=Ct.keyword$DataError=Ct.keywordError=void 0;var Ce=ue(),Ol=ke(),Pt=Qn();Ct.keywordError={message:({keyword:t})=>(0,Ce.str)`must pass "${t}" keyword validation`};Ct.keyword$DataError={message:({keyword:t,schemaType:n})=>n?(0,Ce.str)`"${t}" keyword must be ${n} ($data)`:(0,Ce.str)`"${t}" keyword is invalid ($data)`};function BS(t,n=Ct.keywordError,e,i){let{it:a}=t,{gen:r,compositeRule:o,allErrors:s}=a,l=rb(t,n,e);i??(o||s)?ib(r,l):ab(a,(0,Ce._)`[${l}]`)}Ct.reportError=BS;function jS(t,n=Ct.keywordError,e){let{it:i}=t,{gen:a,compositeRule:r,allErrors:o}=i,s=rb(t,n,e);ib(a,s),r||o||ab(i,Pt.default.vErrors)}Ct.reportExtraError=jS;function VS(t,n){t.assign(Pt.default.errors,n),t.if((0,Ce._)`${Pt.default.vErrors} !== null`,()=>t.if(n,()=>t.assign((0,Ce._)`${Pt.default.vErrors}.length`,n),()=>t.assign(Pt.default.vErrors,null)))}Ct.resetErrorsCount=VS;function $S({gen:t,keyword:n,schemaValue:e,data:i,errsCount:a,it:r}){if(a===void 0)throw new Error("ajv implementation error");let o=t.name("err");t.forRange("i",a,Pt.default.errors,s=>{t.const(o,(0,Ce._)`${Pt.default.vErrors}[${s}]`),t.if((0,Ce._)`${o}.instancePath === undefined`,()=>t.assign((0,Ce._)`${o}.instancePath`,(0,Ce.strConcat)(Pt.default.instancePath,r.errorPath))),t.assign((0,Ce._)`${o}.schemaPath`,(0,Ce.str)`${r.errSchemaPath}/${n}`),r.opts.verbose&&(t.assign((0,Ce._)`${o}.schema`,e),t.assign((0,Ce._)`${o}.data`,i))})}Ct.extendErrors=$S;function ib(t,n){let e=t.const("err",n);t.if((0,Ce._)`${Pt.default.vErrors} === null`,()=>t.assign(Pt.default.vErrors,(0,Ce._)`[${e}]`),(0,Ce._)`${Pt.default.vErrors}.push(${e})`),t.code((0,Ce._)`${Pt.default.errors}++`)}function ab(t,n){let{gen:e,validateName:i,schemaEnv:a}=t;a.$async?e.throw((0,Ce._)`new ${t.ValidationError}(${n})`):(e.assign((0,Ce._)`${i}.errors`,n),e.return(!1))}var Qi={keyword:new Ce.Name("keyword"),schemaPath:new Ce.Name("schemaPath"),params:new Ce.Name("params"),propertyName:new Ce.Name("propertyName"),message:new Ce.Name("message"),schema:new Ce.Name("schema"),parentSchema:new Ce.Name("parentSchema")};function rb(t,n,e){let{createErrors:i}=t.it;return i===!1?(0,Ce._)`{}`:zS(t,n,e)}function zS(t,n,e={}){let{gen:i,it:a}=t,r=[HS(a,e),US(t,e)];return qS(t,n,r),i.object(...r)}function HS({errorPath:t},{instancePath:n}){let e=n?(0,Ce.str)`${t}${(0,Ol.getErrorPath)(n,Ol.Type.Str)}`:t;return[Pt.default.instancePath,(0,Ce.strConcat)(Pt.default.instancePath,e)]}function US({keyword:t,it:{errSchemaPath:n}},{schemaPath:e,parentSchema:i}){let a=i?n:(0,Ce.str)`${n}/${t}`;return e&&(a=(0,Ce.str)`${a}${(0,Ol.getErrorPath)(e,Ol.Type.Str)}`),[Qi.schemaPath,a]}function qS(t,{params:n,message:e},i){let{keyword:a,data:r,schemaValue:o,it:s}=t,{opts:l,propertyName:c,topSchemaRef:d,schemaPath:m}=s;i.push([Qi.keyword,a],[Qi.params,typeof n=="function"?n(t):n||(0,Ce._)`{}`]),l.messages&&i.push([Qi.message,typeof e=="function"?e(t):e]),l.verbose&&i.push([Qi.schema,o],[Qi.parentSchema,(0,Ce._)`${d}${m}`],[Pt.default.data,r]),c&&i.push([Qi.propertyName,c])}});var sb=L(Ha=>{"use strict";Object.defineProperty(Ha,"__esModule",{value:!0});Ha.boolOrEmptySchema=Ha.topBoolOrEmptySchema=void 0;var GS=_o(),WS=ue(),KS=Qn(),YS={message:"boolean schema is false"};function XS(t){let{gen:n,schema:e,validateName:i}=t;e===!1?ob(t,!1):typeof e=="object"&&e.$async===!0?n.return(KS.default.data):(n.assign((0,WS._)`${i}.errors`,null),n.return(!0))}Ha.topBoolOrEmptySchema=XS;function ZS(t,n){let{gen:e,schema:i}=t;i===!1?(e.var(n,!1),ob(t)):e.var(n,!0)}Ha.boolOrEmptySchema=ZS;function ob(t,n){let{gen:e,data:i}=t,a={gen:e,keyword:"false schema",data:i,schema:!1,schemaCode:!1,schemaValue:!1,params:{},it:t};(0,GS.reportError)(a,YS,void 0,n)}});var Dm=L(Ua=>{"use strict";Object.defineProperty(Ua,"__esModule",{value:!0});Ua.getRules=Ua.isJSONType=void 0;var QS=["string","number","integer","boolean","null","object","array"],JS=new Set(QS);function ek(t){return typeof t=="string"&&JS.has(t)}Ua.isJSONType=ek;function tk(){let t={number:{type:"number",rules:[]},string:{type:"string",rules:[]},array:{type:"array",rules:[]},object:{type:"object",rules:[]}};return{types:De(N({},t),{integer:!0,boolean:!0,null:!0}),rules:[{rules:[]},t.number,t.string,t.array,t.object],post:{rules:[]},all:{},keywords:{}}}Ua.getRules=tk});var Mm=L(wi=>{"use strict";Object.defineProperty(wi,"__esModule",{value:!0});wi.shouldUseRule=wi.shouldUseGroup=wi.schemaHasRulesForType=void 0;function nk({schema:t,self:n},e){let i=n.RULES.types[e];return i&&i!==!0&&lb(t,i)}wi.schemaHasRulesForType=nk;function lb(t,n){return n.rules.some(e=>cb(t,e))}wi.shouldUseGroup=lb;function cb(t,n){var e;return t[n.keyword]!==void 0||((e=n.definition.implements)===null||e===void 0?void 0:e.some(i=>t[i]!==void 0))}wi.shouldUseRule=cb});var yo=L(Et=>{"use strict";Object.defineProperty(Et,"__esModule",{value:!0});Et.reportTypeError=Et.checkDataTypes=Et.checkDataType=Et.coerceAndCheckDataType=Et.getJSONTypes=Et.getSchemaTypes=Et.DataType=void 0;var ik=Dm(),ak=Mm(),rk=_o(),se=ue(),db=ke(),qa;(function(t){t[t.Correct=0]="Correct",t[t.Wrong=1]="Wrong"})(qa||(Et.DataType=qa={}));function ok(t){let n=mb(t.type);if(n.includes("null")){if(t.nullable===!1)throw new Error("type: null contradicts nullable: false")}else{if(!n.length&&t.nullable!==void 0)throw new Error('"nullable" cannot be used without "type"');t.nullable===!0&&n.push("null")}return n}Et.getSchemaTypes=ok;function mb(t){let n=Array.isArray(t)?t:t?[t]:[];if(n.every(ik.isJSONType))return n;throw new Error("type must be JSONType or JSONType[]: "+n.join(","))}Et.getJSONTypes=mb;function sk(t,n){let{gen:e,data:i,opts:a}=t,r=lk(n,a.coerceTypes),o=n.length>0&&!(r.length===0&&n.length===1&&(0,ak.schemaHasRulesForType)(t,n[0]));if(o){let s=Im(n,i,a.strictNumbers,qa.Wrong);e.if(s,()=>{r.length?ck(t,n,r):Am(t)})}return o}Et.coerceAndCheckDataType=sk;var pb=new Set(["string","number","integer","boolean","null"]);function lk(t,n){return n?t.filter(e=>pb.has(e)||n==="array"&&e==="array"):[]}function ck(t,n,e){let{gen:i,data:a,opts:r}=t,o=i.let("dataType",(0,se._)`typeof ${a}`),s=i.let("coerced",(0,se._)`undefined`);r.coerceTypes==="array"&&i.if((0,se._)`${o} == 'object' && Array.isArray(${a}) && ${a}.length == 1`,()=>i.assign(a,(0,se._)`${a}[0]`).assign(o,(0,se._)`typeof ${a}`).if(Im(n,a,r.strictNumbers),()=>i.assign(s,a))),i.if((0,se._)`${s} !== undefined`);for(let c of e)(pb.has(c)||c==="array"&&r.coerceTypes==="array")&&l(c);i.else(),Am(t),i.endIf(),i.if((0,se._)`${s} !== undefined`,()=>{i.assign(a,s),dk(t,s)});function l(c){switch(c){case"string":i.elseIf((0,se._)`${o} == "number" || ${o} == "boolean"`).assign(s,(0,se._)`"" + ${a}`).elseIf((0,se._)`${a} === null`).assign(s,(0,se._)`""`);return;case"number":i.elseIf((0,se._)`${o} == "boolean" || ${a} === null
              || (${o} == "string" && ${a} && ${a} == +${a})`).assign(s,(0,se._)`+${a}`);return;case"integer":i.elseIf((0,se._)`${o} === "boolean" || ${a} === null
              || (${o} === "string" && ${a} && ${a} == +${a} && !(${a} % 1))`).assign(s,(0,se._)`+${a}`);return;case"boolean":i.elseIf((0,se._)`${a} === "false" || ${a} === 0 || ${a} === null`).assign(s,!1).elseIf((0,se._)`${a} === "true" || ${a} === 1`).assign(s,!0);return;case"null":i.elseIf((0,se._)`${a} === "" || ${a} === 0 || ${a} === false`),i.assign(s,null);return;case"array":i.elseIf((0,se._)`${o} === "string" || ${o} === "number"
              || ${o} === "boolean" || ${a} === null`).assign(s,(0,se._)`[${a}]`)}}}function dk({gen:t,parentData:n,parentDataProperty:e},i){t.if((0,se._)`${n} !== undefined`,()=>t.assign((0,se._)`${n}[${e}]`,i))}function Nm(t,n,e,i=qa.Correct){let a=i===qa.Correct?se.operators.EQ:se.operators.NEQ,r;switch(t){case"null":return(0,se._)`${n} ${a} null`;case"array":r=(0,se._)`Array.isArray(${n})`;break;case"object":r=(0,se._)`${n} && typeof ${n} == "object" && !Array.isArray(${n})`;break;case"integer":r=o((0,se._)`!(${n} % 1) && !isNaN(${n})`);break;case"number":r=o();break;default:return(0,se._)`typeof ${n} ${a} ${t}`}return i===qa.Correct?r:(0,se.not)(r);function o(s=se.nil){return(0,se.and)((0,se._)`typeof ${n} == "number"`,s,e?(0,se._)`isFinite(${n})`:se.nil)}}Et.checkDataType=Nm;function Im(t,n,e,i){if(t.length===1)return Nm(t[0],n,e,i);let a,r=(0,db.toHash)(t);if(r.array&&r.object){let o=(0,se._)`typeof ${n} != "object"`;a=r.null?o:(0,se._)`!${n} || ${o}`,delete r.null,delete r.array,delete r.object}else a=se.nil;r.number&&delete r.integer;for(let o in r)a=(0,se.and)(a,Nm(o,n,e,i));return a}Et.checkDataTypes=Im;var mk={message:({schema:t})=>`must be ${t}`,params:({schema:t,schemaValue:n})=>typeof t=="string"?(0,se._)`{type: ${t}}`:(0,se._)`{type: ${n}}`};function Am(t){let n=pk(t);(0,rk.reportError)(n,mk)}Et.reportTypeError=Am;function pk(t){let{gen:n,data:e,schema:i}=t,a=(0,db.schemaRefOrVal)(t,i,"type");return{gen:n,keyword:"type",data:e,schema:i.type,schemaCode:a,schemaValue:a,parentSchema:i,params:{},it:t}}});var hb=L(Rl=>{"use strict";Object.defineProperty(Rl,"__esModule",{value:!0});Rl.assignDefaults=void 0;var Ga=ue(),uk=ke();function hk(t,n){let{properties:e,items:i}=t.schema;if(n==="object"&&e)for(let a in e)ub(t,a,e[a].default);else n==="array"&&Array.isArray(i)&&i.forEach((a,r)=>ub(t,r,a.default))}Rl.assignDefaults=hk;function ub(t,n,e){let{gen:i,compositeRule:a,data:r,opts:o}=t;if(e===void 0)return;let s=(0,Ga._)`${r}${(0,Ga.getProperty)(n)}`;if(a){(0,uk.checkStrictMode)(t,`default is ignored for: ${s}`);return}let l=(0,Ga._)`${s} === undefined`;o.useDefaults==="empty"&&(l=(0,Ga._)`${l} || ${s} === null || ${s} === ""`),i.if(l,(0,Ga._)`${s} = ${(0,Ga.stringify)(e)}`)}});var tn=L(Fe=>{"use strict";Object.defineProperty(Fe,"__esModule",{value:!0});Fe.validateUnion=Fe.validateArray=Fe.usePattern=Fe.callValidateCode=Fe.schemaProperties=Fe.allSchemaProperties=Fe.noPropertyInData=Fe.propertyInData=Fe.isOwnProperty=Fe.hasPropFunc=Fe.reportMissingProp=Fe.checkMissingProp=Fe.checkReportMissingProp=void 0;var $e=ue(),Tm=ke(),Si=Qn(),fk=ke();function gk(t,n){let{gen:e,data:i,it:a}=t;e.if(Rm(e,i,n,a.opts.ownProperties),()=>{t.setParams({missingProperty:(0,$e._)`${n}`},!0),t.error()})}Fe.checkReportMissingProp=gk;function bk({gen:t,data:n,it:{opts:e}},i,a){return(0,$e.or)(...i.map(r=>(0,$e.and)(Rm(t,n,r,e.ownProperties),(0,$e._)`${a} = ${r}`)))}Fe.checkMissingProp=bk;function vk(t,n){t.setParams({missingProperty:n},!0),t.error()}Fe.reportMissingProp=vk;function fb(t){return t.scopeValue("func",{ref:Object.prototype.hasOwnProperty,code:(0,$e._)`Object.prototype.hasOwnProperty`})}Fe.hasPropFunc=fb;function Om(t,n,e){return(0,$e._)`${fb(t)}.call(${n}, ${e})`}Fe.isOwnProperty=Om;function _k(t,n,e,i){let a=(0,$e._)`${n}${(0,$e.getProperty)(e)} !== undefined`;return i?(0,$e._)`${a} && ${Om(t,n,e)}`:a}Fe.propertyInData=_k;function Rm(t,n,e,i){let a=(0,$e._)`${n}${(0,$e.getProperty)(e)} === undefined`;return i?(0,$e.or)(a,(0,$e.not)(Om(t,n,e))):a}Fe.noPropertyInData=Rm;function gb(t){return t?Object.keys(t).filter(n=>n!=="__proto__"):[]}Fe.allSchemaProperties=gb;function yk(t,n){return gb(n).filter(e=>!(0,Tm.alwaysValidSchema)(t,n[e]))}Fe.schemaProperties=yk;function xk({schemaCode:t,data:n,it:{gen:e,topSchemaRef:i,schemaPath:a,errorPath:r},it:o},s,l,c){let d=c?(0,$e._)`${t}, ${n}, ${i}${a}`:n,m=[[Si.default.instancePath,(0,$e.strConcat)(Si.default.instancePath,r)],[Si.default.parentData,o.parentData],[Si.default.parentDataProperty,o.parentDataProperty],[Si.default.rootData,Si.default.rootData]];o.opts.dynamicRef&&m.push([Si.default.dynamicAnchors,Si.default.dynamicAnchors]);let u=(0,$e._)`${d}, ${e.object(...m)}`;return l!==$e.nil?(0,$e._)`${s}.call(${l}, ${u})`:(0,$e._)`${s}(${u})`}Fe.callValidateCode=xk;var wk=(0,$e._)`new RegExp`;function Sk({gen:t,it:{opts:n}},e){let i=n.unicodeRegExp?"u":"",{regExp:a}=n.code,r=a(e,i);return t.scopeValue("pattern",{key:r.toString(),ref:r,code:(0,$e._)`${a.code==="new RegExp"?wk:(0,fk.useFunc)(t,a)}(${e}, ${i})`})}Fe.usePattern=Sk;function kk(t){let{gen:n,data:e,keyword:i,it:a}=t,r=n.name("valid");if(a.allErrors){let s=n.let("valid",!0);return o(()=>n.assign(s,!1)),s}return n.var(r,!0),o(()=>n.break()),r;function o(s){let l=n.const("len",(0,$e._)`${e}.length`);n.forRange("i",0,l,c=>{t.subschema({keyword:i,dataProp:c,dataPropType:Tm.Type.Num},r),n.if((0,$e.not)(r),s)})}}Fe.validateArray=kk;function Ck(t){let{gen:n,schema:e,keyword:i,it:a}=t;if(!Array.isArray(e))throw new Error("ajv implementation error");if(e.some(l=>(0,Tm.alwaysValidSchema)(a,l))&&!a.opts.unevaluated)return;let o=n.let("valid",!1),s=n.name("_valid");n.block(()=>e.forEach((l,c)=>{let d=t.subschema({keyword:i,schemaProp:c,compositeRule:!0},s);n.assign(o,(0,$e._)`${o} || ${s}`),t.mergeValidEvaluated(d,s)||n.if((0,$e.not)(o))})),t.result(o,()=>t.reset(),()=>t.error(!0))}Fe.validateUnion=Ck});var _b=L(Tn=>{"use strict";Object.defineProperty(Tn,"__esModule",{value:!0});Tn.validateKeywordUsage=Tn.validSchemaType=Tn.funcKeywordCode=Tn.macroKeywordCode=void 0;var Ft=ue(),Ji=Qn(),Ek=tn(),Dk=_o();function Mk(t,n){let{gen:e,keyword:i,schema:a,parentSchema:r,it:o}=t,s=n.macro.call(o.self,a,r,o),l=vb(e,i,s);o.opts.validateSchema!==!1&&o.self.validateSchema(s,!0);let c=e.name("valid");t.subschema({schema:s,schemaPath:Ft.nil,errSchemaPath:`${o.errSchemaPath}/${i}`,topSchemaRef:l,compositeRule:!0},c),t.pass(c,()=>t.error(!0))}Tn.macroKeywordCode=Mk;function Nk(t,n){var e;let{gen:i,keyword:a,schema:r,parentSchema:o,$data:s,it:l}=t;Ak(l,n);let c=!s&&n.compile?n.compile.call(l.self,r,o,l):n.validate,d=vb(i,a,c),m=i.let("valid");t.block$data(m,u),t.ok((e=n.valid)!==null&&e!==void 0?e:m);function u(){if(n.errors===!1)b(),n.modifying&&bb(t),_(()=>t.error());else{let x=n.async?f():g();n.modifying&&bb(t),_(()=>Ik(t,x))}}function f(){let x=i.let("ruleErrs",null);return i.try(()=>b((0,Ft._)`await `),w=>i.assign(m,!1).if((0,Ft._)`${w} instanceof ${l.ValidationError}`,()=>i.assign(x,(0,Ft._)`${w}.errors`),()=>i.throw(w))),x}function g(){let x=(0,Ft._)`${d}.errors`;return i.assign(x,null),b(Ft.nil),x}function b(x=n.async?(0,Ft._)`await `:Ft.nil){let w=l.opts.passContext?Ji.default.this:Ji.default.self,C=!("compile"in n&&!s||n.schema===!1);i.assign(m,(0,Ft._)`${x}${(0,Ek.callValidateCode)(t,d,w,C)}`,n.modifying)}function _(x){var w;i.if((0,Ft.not)((w=n.valid)!==null&&w!==void 0?w:m),x)}}Tn.funcKeywordCode=Nk;function bb(t){let{gen:n,data:e,it:i}=t;n.if(i.parentData,()=>n.assign(e,(0,Ft._)`${i.parentData}[${i.parentDataProperty}]`))}function Ik(t,n){let{gen:e}=t;e.if((0,Ft._)`Array.isArray(${n})`,()=>{e.assign(Ji.default.vErrors,(0,Ft._)`${Ji.default.vErrors} === null ? ${n} : ${Ji.default.vErrors}.concat(${n})`).assign(Ji.default.errors,(0,Ft._)`${Ji.default.vErrors}.length`),(0,Dk.extendErrors)(t)},()=>t.error())}function Ak({schemaEnv:t},n){if(n.async&&!t.$async)throw new Error("async keyword in sync schema")}function vb(t,n,e){if(e===void 0)throw new Error(`keyword "${n}" failed to compile`);return t.scopeValue("keyword",typeof e=="function"?{ref:e}:{ref:e,code:(0,Ft.stringify)(e)})}function Tk(t,n,e=!1){return!n.length||n.some(i=>i==="array"?Array.isArray(t):i==="object"?t&&typeof t=="object"&&!Array.isArray(t):typeof t==i||e&&typeof t>"u")}Tn.validSchemaType=Tk;function Ok({schema:t,opts:n,self:e,errSchemaPath:i},a,r){if(Array.isArray(a.keyword)?!a.keyword.includes(r):a.keyword!==r)throw new Error("ajv implementation error");let o=a.dependencies;if(o?.some(s=>!Object.prototype.hasOwnProperty.call(t,s)))throw new Error(`parent schema must have dependencies of ${r}: ${o.join(",")}`);if(a.validateSchema&&!a.validateSchema(t[r])){let l=`keyword "${r}" value is invalid at path "${i}": `+e.errorsText(a.validateSchema.errors);if(n.validateSchema==="log")e.logger.error(l);else throw new Error(l)}}Tn.validateKeywordUsage=Ok});var xb=L(ki=>{"use strict";Object.defineProperty(ki,"__esModule",{value:!0});ki.extendSubschemaMode=ki.extendSubschemaData=ki.getSubschema=void 0;var On=ue(),yb=ke();function Rk(t,{keyword:n,schemaProp:e,schema:i,schemaPath:a,errSchemaPath:r,topSchemaRef:o}){if(n!==void 0&&i!==void 0)throw new Error('both "keyword" and "schema" passed, only one allowed');if(n!==void 0){let s=t.schema[n];return e===void 0?{schema:s,schemaPath:(0,On._)`${t.schemaPath}${(0,On.getProperty)(n)}`,errSchemaPath:`${t.errSchemaPath}/${n}`}:{schema:s[e],schemaPath:(0,On._)`${t.schemaPath}${(0,On.getProperty)(n)}${(0,On.getProperty)(e)}`,errSchemaPath:`${t.errSchemaPath}/${n}/${(0,yb.escapeFragment)(e)}`}}if(i!==void 0){if(a===void 0||r===void 0||o===void 0)throw new Error('"schemaPath", "errSchemaPath" and "topSchemaRef" are required with "schema"');return{schema:i,schemaPath:a,topSchemaRef:o,errSchemaPath:r}}throw new Error('either "keyword" or "schema" must be passed')}ki.getSubschema=Rk;function Pk(t,n,{dataProp:e,dataPropType:i,data:a,dataTypes:r,propertyName:o}){if(a!==void 0&&e!==void 0)throw new Error('both "data" and "dataProp" passed, only one allowed');let{gen:s}=n;if(e!==void 0){let{errorPath:c,dataPathArr:d,opts:m}=n,u=s.let("data",(0,On._)`${n.data}${(0,On.getProperty)(e)}`,!0);l(u),t.errorPath=(0,On.str)`${c}${(0,yb.getErrorPath)(e,i,m.jsPropertySyntax)}`,t.parentDataProperty=(0,On._)`${e}`,t.dataPathArr=[...d,t.parentDataProperty]}if(a!==void 0){let c=a instanceof On.Name?a:s.let("data",a,!0);l(c),o!==void 0&&(t.propertyName=o)}r&&(t.dataTypes=r);function l(c){t.data=c,t.dataLevel=n.dataLevel+1,t.dataTypes=[],n.definedProperties=new Set,t.parentData=n.data,t.dataNames=[...n.dataNames,c]}}ki.extendSubschemaData=Pk;function Fk(t,{jtdDiscriminator:n,jtdMetadata:e,compositeRule:i,createErrors:a,allErrors:r}){i!==void 0&&(t.compositeRule=i),a!==void 0&&(t.createErrors=a),r!==void 0&&(t.allErrors=r),t.jtdDiscriminator=n,t.jtdMetadata=e}ki.extendSubschemaMode=Fk});var Pm=L((Lj,wb)=>{"use strict";wb.exports=function t(n,e){if(n===e)return!0;if(n&&e&&typeof n=="object"&&typeof e=="object"){if(n.constructor!==e.constructor)return!1;var i,a,r;if(Array.isArray(n)){if(i=n.length,i!=e.length)return!1;for(a=i;a--!==0;)if(!t(n[a],e[a]))return!1;return!0}if(n.constructor===RegExp)return n.source===e.source&&n.flags===e.flags;if(n.valueOf!==Object.prototype.valueOf)return n.valueOf()===e.valueOf();if(n.toString!==Object.prototype.toString)return n.toString()===e.toString();if(r=Object.keys(n),i=r.length,i!==Object.keys(e).length)return!1;for(a=i;a--!==0;)if(!Object.prototype.hasOwnProperty.call(e,r[a]))return!1;for(a=i;a--!==0;){var o=r[a];if(!t(n[o],e[o]))return!1}return!0}return n!==n&&e!==e}});var kb=L((Bj,Sb)=>{"use strict";var Ci=Sb.exports=function(t,n,e){typeof n=="function"&&(e=n,n={}),e=n.cb||e;var i=typeof e=="function"?e:e.pre||function(){},a=e.post||function(){};Pl(n,i,a,t,"",t)};Ci.keywords={additionalItems:!0,items:!0,contains:!0,additionalProperties:!0,propertyNames:!0,not:!0,if:!0,then:!0,else:!0};Ci.arrayKeywords={items:!0,allOf:!0,anyOf:!0,oneOf:!0};Ci.propsKeywords={$defs:!0,definitions:!0,properties:!0,patternProperties:!0,dependencies:!0};Ci.skipKeywords={default:!0,enum:!0,const:!0,required:!0,maximum:!0,minimum:!0,exclusiveMaximum:!0,exclusiveMinimum:!0,multipleOf:!0,maxLength:!0,minLength:!0,pattern:!0,format:!0,maxItems:!0,minItems:!0,uniqueItems:!0,maxProperties:!0,minProperties:!0};function Pl(t,n,e,i,a,r,o,s,l,c){if(i&&typeof i=="object"&&!Array.isArray(i)){n(i,a,r,o,s,l,c);for(var d in i){var m=i[d];if(Array.isArray(m)){if(d in Ci.arrayKeywords)for(var u=0;u<m.length;u++)Pl(t,n,e,m[u],a+"/"+d+"/"+u,r,a,d,i,u)}else if(d in Ci.propsKeywords){if(m&&typeof m=="object")for(var f in m)Pl(t,n,e,m[f],a+"/"+d+"/"+Lk(f),r,a,d,i,f)}else(d in Ci.keywords||t.allKeys&&!(d in Ci.skipKeywords))&&Pl(t,n,e,m,a+"/"+d,r,a,d,i)}e(i,a,r,o,s,l,c)}}function Lk(t){return t.replace(/~/g,"~0").replace(/\//g,"~1")}});var xo=L($t=>{"use strict";Object.defineProperty($t,"__esModule",{value:!0});$t.getSchemaRefs=$t.resolveUrl=$t.normalizeId=$t._getFullPath=$t.getFullPath=$t.inlineRef=void 0;var Bk=ke(),jk=Pm(),Vk=kb(),$k=new Set(["type","format","pattern","maxLength","minLength","maxProperties","minProperties","maxItems","minItems","maximum","minimum","uniqueItems","multipleOf","required","enum","const"]);function zk(t,n=!0){return typeof t=="boolean"?!0:n===!0?!Fm(t):n?Cb(t)<=n:!1}$t.inlineRef=zk;var Hk=new Set(["$ref","$recursiveRef","$recursiveAnchor","$dynamicRef","$dynamicAnchor"]);function Fm(t){for(let n in t){if(Hk.has(n))return!0;let e=t[n];if(Array.isArray(e)&&e.some(Fm)||typeof e=="object"&&Fm(e))return!0}return!1}function Cb(t){let n=0;for(let e in t){if(e==="$ref")return 1/0;if(n++,!$k.has(e)&&(typeof t[e]=="object"&&(0,Bk.eachItem)(t[e],i=>n+=Cb(i)),n===1/0))return 1/0}return n}function Eb(t,n="",e){e!==!1&&(n=Wa(n));let i=t.parse(n);return Db(t,i)}$t.getFullPath=Eb;function Db(t,n){return t.serialize(n).split("#")[0]+"#"}$t._getFullPath=Db;var Uk=/#\/?$/;function Wa(t){return t?t.replace(Uk,""):""}$t.normalizeId=Wa;function qk(t,n,e){return e=Wa(e),t.resolve(n,e)}$t.resolveUrl=qk;var Gk=/^[a-z_][-a-z0-9._]*$/i;function Wk(t,n){if(typeof t=="boolean")return{};let{schemaId:e,uriResolver:i}=this.opts,a=Wa(t[e]||n),r={"":a},o=Eb(i,a,!1),s={},l=new Set;return Vk(t,{allKeys:!0},(m,u,f,g)=>{if(g===void 0)return;let b=o+u,_=r[g];typeof m[e]=="string"&&(_=x.call(this,m[e])),w.call(this,m.$anchor),w.call(this,m.$dynamicAnchor),r[u]=_;function x(C){let F=this.opts.uriResolver.resolve;if(C=Wa(_?F(_,C):C),l.has(C))throw d(C);l.add(C);let A=this.refs[C];return typeof A=="string"&&(A=this.refs[A]),typeof A=="object"?c(m,A.schema,C):C!==Wa(b)&&(C[0]==="#"?(c(m,s[C],C),s[C]=m):this.refs[C]=b),C}function w(C){if(typeof C=="string"){if(!Gk.test(C))throw new Error(`invalid anchor "${C}"`);x.call(this,`#${C}`)}}}),s;function c(m,u,f){if(u!==void 0&&!jk(m,u))throw d(f)}function d(m){return new Error(`reference "${m}" resolves to more than one schema`)}}$t.getSchemaRefs=Wk});var ko=L(Ei=>{"use strict";Object.defineProperty(Ei,"__esModule",{value:!0});Ei.getData=Ei.KeywordCxt=Ei.validateFunctionCode=void 0;var Tb=sb(),Mb=yo(),Bm=Mm(),Fl=yo(),Kk=hb(),So=_b(),Lm=xb(),W=ue(),ie=Qn(),Yk=xo(),Jn=ke(),wo=_o();function Xk(t){if(Pb(t)&&(Fb(t),Rb(t))){Jk(t);return}Ob(t,()=>(0,Tb.topBoolOrEmptySchema)(t))}Ei.validateFunctionCode=Xk;function Ob({gen:t,validateName:n,schema:e,schemaEnv:i,opts:a},r){a.code.es5?t.func(n,(0,W._)`${ie.default.data}, ${ie.default.valCxt}`,i.$async,()=>{t.code((0,W._)`"use strict"; ${Nb(e,a)}`),Qk(t,a),t.code(r)}):t.func(n,(0,W._)`${ie.default.data}, ${Zk(a)}`,i.$async,()=>t.code(Nb(e,a)).code(r))}function Zk(t){return(0,W._)`{${ie.default.instancePath}="", ${ie.default.parentData}, ${ie.default.parentDataProperty}, ${ie.default.rootData}=${ie.default.data}${t.dynamicRef?(0,W._)`, ${ie.default.dynamicAnchors}={}`:W.nil}}={}`}function Qk(t,n){t.if(ie.default.valCxt,()=>{t.var(ie.default.instancePath,(0,W._)`${ie.default.valCxt}.${ie.default.instancePath}`),t.var(ie.default.parentData,(0,W._)`${ie.default.valCxt}.${ie.default.parentData}`),t.var(ie.default.parentDataProperty,(0,W._)`${ie.default.valCxt}.${ie.default.parentDataProperty}`),t.var(ie.default.rootData,(0,W._)`${ie.default.valCxt}.${ie.default.rootData}`),n.dynamicRef&&t.var(ie.default.dynamicAnchors,(0,W._)`${ie.default.valCxt}.${ie.default.dynamicAnchors}`)},()=>{t.var(ie.default.instancePath,(0,W._)`""`),t.var(ie.default.parentData,(0,W._)`undefined`),t.var(ie.default.parentDataProperty,(0,W._)`undefined`),t.var(ie.default.rootData,ie.default.data),n.dynamicRef&&t.var(ie.default.dynamicAnchors,(0,W._)`{}`)})}function Jk(t){let{schema:n,opts:e,gen:i}=t;Ob(t,()=>{e.$comment&&n.$comment&&Bb(t),aC(t),i.let(ie.default.vErrors,null),i.let(ie.default.errors,0),e.unevaluated&&eC(t),Lb(t),sC(t)})}function eC(t){let{gen:n,validateName:e}=t;t.evaluated=n.const("evaluated",(0,W._)`${e}.evaluated`),n.if((0,W._)`${t.evaluated}.dynamicProps`,()=>n.assign((0,W._)`${t.evaluated}.props`,(0,W._)`undefined`)),n.if((0,W._)`${t.evaluated}.dynamicItems`,()=>n.assign((0,W._)`${t.evaluated}.items`,(0,W._)`undefined`))}function Nb(t,n){let e=typeof t=="object"&&t[n.schemaId];return e&&(n.code.source||n.code.process)?(0,W._)`/*# sourceURL=${e} */`:W.nil}function tC(t,n){if(Pb(t)&&(Fb(t),Rb(t))){nC(t,n);return}(0,Tb.boolOrEmptySchema)(t,n)}function Rb({schema:t,self:n}){if(typeof t=="boolean")return!t;for(let e in t)if(n.RULES.all[e])return!0;return!1}function Pb(t){return typeof t.schema!="boolean"}function nC(t,n){let{schema:e,gen:i,opts:a}=t;a.$comment&&e.$comment&&Bb(t),rC(t),oC(t);let r=i.const("_errs",ie.default.errors);Lb(t,r),i.var(n,(0,W._)`${r} === ${ie.default.errors}`)}function Fb(t){(0,Jn.checkUnknownRules)(t),iC(t)}function Lb(t,n){if(t.opts.jtd)return Ib(t,[],!1,n);let e=(0,Mb.getSchemaTypes)(t.schema),i=(0,Mb.coerceAndCheckDataType)(t,e);Ib(t,e,!i,n)}function iC(t){let{schema:n,errSchemaPath:e,opts:i,self:a}=t;n.$ref&&i.ignoreKeywordsWithRef&&(0,Jn.schemaHasRulesButRef)(n,a.RULES)&&a.logger.warn(`$ref: keywords ignored in schema at path "${e}"`)}function aC(t){let{schema:n,opts:e}=t;n.default!==void 0&&e.useDefaults&&e.strictSchema&&(0,Jn.checkStrictMode)(t,"default is ignored in the schema root")}function rC(t){let n=t.schema[t.opts.schemaId];n&&(t.baseId=(0,Yk.resolveUrl)(t.opts.uriResolver,t.baseId,n))}function oC(t){if(t.schema.$async&&!t.schemaEnv.$async)throw new Error("async schema in sync schema")}function Bb({gen:t,schemaEnv:n,schema:e,errSchemaPath:i,opts:a}){let r=e.$comment;if(a.$comment===!0)t.code((0,W._)`${ie.default.self}.logger.log(${r})`);else if(typeof a.$comment=="function"){let o=(0,W.str)`${i}/$comment`,s=t.scopeValue("root",{ref:n.root});t.code((0,W._)`${ie.default.self}.opts.$comment(${r}, ${o}, ${s}.schema)`)}}function sC(t){let{gen:n,schemaEnv:e,validateName:i,ValidationError:a,opts:r}=t;e.$async?n.if((0,W._)`${ie.default.errors} === 0`,()=>n.return(ie.default.data),()=>n.throw((0,W._)`new ${a}(${ie.default.vErrors})`)):(n.assign((0,W._)`${i}.errors`,ie.default.vErrors),r.unevaluated&&lC(t),n.return((0,W._)`${ie.default.errors} === 0`))}function lC({gen:t,evaluated:n,props:e,items:i}){e instanceof W.Name&&t.assign((0,W._)`${n}.props`,e),i instanceof W.Name&&t.assign((0,W._)`${n}.items`,i)}function Ib(t,n,e,i){let{gen:a,schema:r,data:o,allErrors:s,opts:l,self:c}=t,{RULES:d}=c;if(r.$ref&&(l.ignoreKeywordsWithRef||!(0,Jn.schemaHasRulesButRef)(r,d))){a.block(()=>Vb(t,"$ref",d.all.$ref.definition));return}l.jtd||cC(t,n),a.block(()=>{for(let u of d.rules)m(u);m(d.post)});function m(u){(0,Bm.shouldUseGroup)(r,u)&&(u.type?(a.if((0,Fl.checkDataType)(u.type,o,l.strictNumbers)),Ab(t,u),n.length===1&&n[0]===u.type&&e&&(a.else(),(0,Fl.reportTypeError)(t)),a.endIf()):Ab(t,u),s||a.if((0,W._)`${ie.default.errors} === ${i||0}`))}}function Ab(t,n){let{gen:e,schema:i,opts:{useDefaults:a}}=t;a&&(0,Kk.assignDefaults)(t,n.type),e.block(()=>{for(let r of n.rules)(0,Bm.shouldUseRule)(i,r)&&Vb(t,r.keyword,r.definition,n.type)})}function cC(t,n){t.schemaEnv.meta||!t.opts.strictTypes||(dC(t,n),t.opts.allowUnionTypes||mC(t,n),pC(t,t.dataTypes))}function dC(t,n){if(n.length){if(!t.dataTypes.length){t.dataTypes=n;return}n.forEach(e=>{jb(t.dataTypes,e)||jm(t,`type "${e}" not allowed by context "${t.dataTypes.join(",")}"`)}),hC(t,n)}}function mC(t,n){n.length>1&&!(n.length===2&&n.includes("null"))&&jm(t,"use allowUnionTypes to allow union type keyword")}function pC(t,n){let e=t.self.RULES.all;for(let i in e){let a=e[i];if(typeof a=="object"&&(0,Bm.shouldUseRule)(t.schema,a)){let{type:r}=a.definition;r.length&&!r.some(o=>uC(n,o))&&jm(t,`missing type "${r.join(",")}" for keyword "${i}"`)}}}function uC(t,n){return t.includes(n)||n==="number"&&t.includes("integer")}function jb(t,n){return t.includes(n)||n==="integer"&&t.includes("number")}function hC(t,n){let e=[];for(let i of t.dataTypes)jb(n,i)?e.push(i):n.includes("integer")&&i==="number"&&e.push("integer");t.dataTypes=e}function jm(t,n){let e=t.schemaEnv.baseId+t.errSchemaPath;n+=` at "${e}" (strictTypes)`,(0,Jn.checkStrictMode)(t,n,t.opts.strictTypes)}var Ll=class{constructor(n,e,i){if((0,So.validateKeywordUsage)(n,e,i),this.gen=n.gen,this.allErrors=n.allErrors,this.keyword=i,this.data=n.data,this.schema=n.schema[i],this.$data=e.$data&&n.opts.$data&&this.schema&&this.schema.$data,this.schemaValue=(0,Jn.schemaRefOrVal)(n,this.schema,i,this.$data),this.schemaType=e.schemaType,this.parentSchema=n.schema,this.params={},this.it=n,this.def=e,this.$data)this.schemaCode=n.gen.const("vSchema",$b(this.$data,n));else if(this.schemaCode=this.schemaValue,!(0,So.validSchemaType)(this.schema,e.schemaType,e.allowUndefined))throw new Error(`${i} value must be ${JSON.stringify(e.schemaType)}`);("code"in e?e.trackErrors:e.errors!==!1)&&(this.errsCount=n.gen.const("_errs",ie.default.errors))}result(n,e,i){this.failResult((0,W.not)(n),e,i)}failResult(n,e,i){this.gen.if(n),i?i():this.error(),e?(this.gen.else(),e(),this.allErrors&&this.gen.endIf()):this.allErrors?this.gen.endIf():this.gen.else()}pass(n,e){this.failResult((0,W.not)(n),void 0,e)}fail(n){if(n===void 0){this.error(),this.allErrors||this.gen.if(!1);return}this.gen.if(n),this.error(),this.allErrors?this.gen.endIf():this.gen.else()}fail$data(n){if(!this.$data)return this.fail(n);let{schemaCode:e}=this;this.fail((0,W._)`${e} !== undefined && (${(0,W.or)(this.invalid$data(),n)})`)}error(n,e,i){if(e){this.setParams(e),this._error(n,i),this.setParams({});return}this._error(n,i)}_error(n,e){(n?wo.reportExtraError:wo.reportError)(this,this.def.error,e)}$dataError(){(0,wo.reportError)(this,this.def.$dataError||wo.keyword$DataError)}reset(){if(this.errsCount===void 0)throw new Error('add "trackErrors" to keyword definition');(0,wo.resetErrorsCount)(this.gen,this.errsCount)}ok(n){this.allErrors||this.gen.if(n)}setParams(n,e){e?Object.assign(this.params,n):this.params=n}block$data(n,e,i=W.nil){this.gen.block(()=>{this.check$data(n,i),e()})}check$data(n=W.nil,e=W.nil){if(!this.$data)return;let{gen:i,schemaCode:a,schemaType:r,def:o}=this;i.if((0,W.or)((0,W._)`${a} === undefined`,e)),n!==W.nil&&i.assign(n,!0),(r.length||o.validateSchema)&&(i.elseIf(this.invalid$data()),this.$dataError(),n!==W.nil&&i.assign(n,!1)),i.else()}invalid$data(){let{gen:n,schemaCode:e,schemaType:i,def:a,it:r}=this;return(0,W.or)(o(),s());function o(){if(i.length){if(!(e instanceof W.Name))throw new Error("ajv implementation error");let l=Array.isArray(i)?i:[i];return(0,W._)`${(0,Fl.checkDataTypes)(l,e,r.opts.strictNumbers,Fl.DataType.Wrong)}`}return W.nil}function s(){if(a.validateSchema){let l=n.scopeValue("validate$data",{ref:a.validateSchema});return(0,W._)`!${l}(${e})`}return W.nil}}subschema(n,e){let i=(0,Lm.getSubschema)(this.it,n);(0,Lm.extendSubschemaData)(i,this.it,n),(0,Lm.extendSubschemaMode)(i,n);let a=De(N(N({},this.it),i),{items:void 0,props:void 0});return tC(a,e),a}mergeEvaluated(n,e){let{it:i,gen:a}=this;i.opts.unevaluated&&(i.props!==!0&&n.props!==void 0&&(i.props=Jn.mergeEvaluated.props(a,n.props,i.props,e)),i.items!==!0&&n.items!==void 0&&(i.items=Jn.mergeEvaluated.items(a,n.items,i.items,e)))}mergeValidEvaluated(n,e){let{it:i,gen:a}=this;if(i.opts.unevaluated&&(i.props!==!0||i.items!==!0))return a.if(e,()=>this.mergeEvaluated(n,W.Name)),!0}};Ei.KeywordCxt=Ll;function Vb(t,n,e,i){let a=new Ll(t,e,n);"code"in e?e.code(a,i):a.$data&&e.validate?(0,So.funcKeywordCode)(a,e):"macro"in e?(0,So.macroKeywordCode)(a,e):(e.compile||e.validate)&&(0,So.funcKeywordCode)(a,e)}var fC=/^\/(?:[^~]|~0|~1)*$/,gC=/^([0-9]+)(#|\/(?:[^~]|~0|~1)*)?$/;function $b(t,{dataLevel:n,dataNames:e,dataPathArr:i}){let a,r;if(t==="")return ie.default.rootData;if(t[0]==="/"){if(!fC.test(t))throw new Error(`Invalid JSON-pointer: ${t}`);a=t,r=ie.default.rootData}else{let c=gC.exec(t);if(!c)throw new Error(`Invalid JSON-pointer: ${t}`);let d=+c[1];if(a=c[2],a==="#"){if(d>=n)throw new Error(l("property/index",d));return i[n-d]}if(d>n)throw new Error(l("data",d));if(r=e[n-d],!a)return r}let o=r,s=a.split("/");for(let c of s)c&&(r=(0,W._)`${r}${(0,W.getProperty)((0,Jn.unescapeJsonPointer)(c))}`,o=(0,W._)`${o} && ${r}`);return o;function l(c,d){return`Cannot access ${c} ${d} levels up, current level is ${n}`}}Ei.getData=$b});var Bl=L($m=>{"use strict";Object.defineProperty($m,"__esModule",{value:!0});var Vm=class extends Error{constructor(n){super("validation failed"),this.errors=n,this.ajv=this.validation=!0}};$m.default=Vm});var Co=L(Um=>{"use strict";Object.defineProperty(Um,"__esModule",{value:!0});var zm=xo(),Hm=class extends Error{constructor(n,e,i,a){super(a||`can't resolve reference ${i} from id ${e}`),this.missingRef=(0,zm.resolveUrl)(n,e,i),this.missingSchema=(0,zm.normalizeId)((0,zm.getFullPath)(n,this.missingRef))}};Um.default=Hm});var Vl=L(nn=>{"use strict";Object.defineProperty(nn,"__esModule",{value:!0});nn.resolveSchema=nn.getCompilingSchema=nn.resolveRef=nn.compileSchema=nn.SchemaEnv=void 0;var gn=ue(),bC=Bl(),ea=Qn(),bn=xo(),zb=ke(),vC=ko(),Ka=class{constructor(n){var e;this.refs={},this.dynamicAnchors={};let i;typeof n.schema=="object"&&(i=n.schema),this.schema=n.schema,this.schemaId=n.schemaId,this.root=n.root||this,this.baseId=(e=n.baseId)!==null&&e!==void 0?e:(0,bn.normalizeId)(i?.[n.schemaId||"$id"]),this.schemaPath=n.schemaPath,this.localRefs=n.localRefs,this.meta=n.meta,this.$async=i?.$async,this.refs={}}};nn.SchemaEnv=Ka;function Gm(t){let n=Hb.call(this,t);if(n)return n;let e=(0,bn.getFullPath)(this.opts.uriResolver,t.root.baseId),{es5:i,lines:a}=this.opts.code,{ownProperties:r}=this.opts,o=new gn.CodeGen(this.scope,{es5:i,lines:a,ownProperties:r}),s;t.$async&&(s=o.scopeValue("Error",{ref:bC.default,code:(0,gn._)`require("ajv/dist/runtime/validation_error").default`}));let l=o.scopeName("validate");t.validateName=l;let c={gen:o,allErrors:this.opts.allErrors,data:ea.default.data,parentData:ea.default.parentData,parentDataProperty:ea.default.parentDataProperty,dataNames:[ea.default.data],dataPathArr:[gn.nil],dataLevel:0,dataTypes:[],definedProperties:new Set,topSchemaRef:o.scopeValue("schema",this.opts.code.source===!0?{ref:t.schema,code:(0,gn.stringify)(t.schema)}:{ref:t.schema}),validateName:l,ValidationError:s,schema:t.schema,schemaEnv:t,rootId:e,baseId:t.baseId||e,schemaPath:gn.nil,errSchemaPath:t.schemaPath||(this.opts.jtd?"":"#"),errorPath:(0,gn._)`""`,opts:this.opts,self:this},d;try{this._compilations.add(t),(0,vC.validateFunctionCode)(c),o.optimize(this.opts.code.optimize);let m=o.toString();d=`${o.scopeRefs(ea.default.scope)}return ${m}`,this.opts.code.process&&(d=this.opts.code.process(d,t));let f=new Function(`${ea.default.self}`,`${ea.default.scope}`,d)(this,this.scope.get());if(this.scope.value(l,{ref:f}),f.errors=null,f.schema=t.schema,f.schemaEnv=t,t.$async&&(f.$async=!0),this.opts.code.source===!0&&(f.source={validateName:l,validateCode:m,scopeValues:o._values}),this.opts.unevaluated){let{props:g,items:b}=c;f.evaluated={props:g instanceof gn.Name?void 0:g,items:b instanceof gn.Name?void 0:b,dynamicProps:g instanceof gn.Name,dynamicItems:b instanceof gn.Name},f.source&&(f.source.evaluated=(0,gn.stringify)(f.evaluated))}return t.validate=f,t}catch(m){throw delete t.validate,delete t.validateName,d&&this.logger.error("Error compiling schema, function code:",d),m}finally{this._compilations.delete(t)}}nn.compileSchema=Gm;function _C(t,n,e){var i;e=(0,bn.resolveUrl)(this.opts.uriResolver,n,e);let a=t.refs[e];if(a)return a;let r=wC.call(this,t,e);if(r===void 0){let o=(i=t.localRefs)===null||i===void 0?void 0:i[e],{schemaId:s}=this.opts;o&&(r=new Ka({schema:o,schemaId:s,root:t,baseId:n}))}if(r!==void 0)return t.refs[e]=yC.call(this,r)}nn.resolveRef=_C;function yC(t){return(0,bn.inlineRef)(t.schema,this.opts.inlineRefs)?t.schema:t.validate?t:Gm.call(this,t)}function Hb(t){for(let n of this._compilations)if(xC(n,t))return n}nn.getCompilingSchema=Hb;function xC(t,n){return t.schema===n.schema&&t.root===n.root&&t.baseId===n.baseId}function wC(t,n){let e;for(;typeof(e=this.refs[n])=="string";)n=e;return e||this.schemas[n]||jl.call(this,t,n)}function jl(t,n){let e=this.opts.uriResolver.parse(n),i=(0,bn._getFullPath)(this.opts.uriResolver,e),a=(0,bn.getFullPath)(this.opts.uriResolver,t.baseId,void 0);if(Object.keys(t.schema).length>0&&i===a)return qm.call(this,e,t);let r=(0,bn.normalizeId)(i),o=this.refs[r]||this.schemas[r];if(typeof o=="string"){let s=jl.call(this,t,o);return typeof s?.schema!="object"?void 0:qm.call(this,e,s)}if(typeof o?.schema=="object"){if(o.validate||Gm.call(this,o),r===(0,bn.normalizeId)(n)){let{schema:s}=o,{schemaId:l}=this.opts,c=s[l];return c&&(a=(0,bn.resolveUrl)(this.opts.uriResolver,a,c)),new Ka({schema:s,schemaId:l,root:t,baseId:a})}return qm.call(this,e,o)}}nn.resolveSchema=jl;var SC=new Set(["properties","patternProperties","enum","dependencies","definitions"]);function qm(t,{baseId:n,schema:e,root:i}){var a;if(((a=t.fragment)===null||a===void 0?void 0:a[0])!=="/")return;for(let s of t.fragment.slice(1).split("/")){if(typeof e=="boolean")return;let l=e[(0,zb.unescapeFragment)(s)];if(l===void 0)return;e=l;let c=typeof e=="object"&&e[this.opts.schemaId];!SC.has(s)&&c&&(n=(0,bn.resolveUrl)(this.opts.uriResolver,n,c))}let r;if(typeof e!="boolean"&&e.$ref&&!(0,zb.schemaHasRulesButRef)(e,this.RULES)){let s=(0,bn.resolveUrl)(this.opts.uriResolver,n,e.$ref);r=jl.call(this,i,s)}let{schemaId:o}=this.opts;if(r=r||new Ka({schema:e,schemaId:o,root:i,baseId:n}),r.schema!==r.root.schema)return r}});var Ub=L((qj,kC)=>{kC.exports={$id:"https://raw.githubusercontent.com/ajv-validator/ajv/master/lib/refs/data.json#",description:"Meta-schema for $data reference (JSON AnySchema extension proposal)",type:"object",required:["$data"],properties:{$data:{type:"string",anyOf:[{format:"relative-json-pointer"},{format:"json-pointer"}]}},additionalProperties:!1}});var Qm=L((Gj,Qb)=>{"use strict";var CC=RegExp.prototype.test.bind(/^[\da-f]{8}-[\da-f]{4}-[\da-f]{4}-[\da-f]{4}-[\da-f]{12}$/iu),Km=RegExp.prototype.test.bind(/^(?:(?:25[0-5]|2[0-4]\d|1\d{2}|[1-9]\d|\d)\.){3}(?:25[0-5]|2[0-4]\d|1\d{2}|[1-9]\d|\d)$/u),EC=RegExp.prototype.test.bind(/^\d*$/u),ta=RegExp.prototype.test.bind(/^[\da-f]{2}$/iu),Ym=RegExp.prototype.test.bind(/^[\da-z\-._~]$/iu),Gb=RegExp.prototype.test.bind(/^[A-Za-z0-9\-._~!$&'()*+,;=:@/]$/u),Xm=RegExp.prototype.test.bind(/^[A-Za-z0-9\-._~!$&'()*+,;=:@/?]$/u),DC=RegExp.prototype.test.bind(/^[A-Za-z0-9\-._~!$&'()*+,;=:]$/u),zt=new Array(256);{let t="0123456789ABCDEF";for(let n=0;n<256;n++)zt[n]="%"+t[n>>4]+t[n&15]}function Dt(t){return t<2048?zt[192|t>>6]+zt[128|t&63]:t<65536?zt[224|t>>12]+zt[128|t>>6&63]+zt[128|t&63]:zt[240|t>>18]+zt[128|t>>12&63]+zt[128|t>>6&63]+zt[128|t&63]}function MC(t){let n="",e=0,i=0;for(i=0;i<t.length;i++)if(e=t[i].charCodeAt(0),e!==48){if(!(e>=48&&e<=57||e>=65&&e<=70||e>=97&&e<=102))return"";n+=t[i];break}for(i+=1;i<t.length;i++){if(e=t[i].charCodeAt(0),!(e>=48&&e<=57||e>=65&&e<=70||e>=97&&e<=102))return"";n+=t[i]}return n}var NC=RegExp.prototype.test.bind(/^[\dA-Fa-f]{1,4}$/),IC=RegExp.prototype.test.bind(/^[vV][\dA-Fa-f]+\.[A-Za-z\d\-._~!$&'()*+,;=:]+$/),AC=RegExp.prototype.test.bind(/^[A-Za-z\d\-._~]$/),TC=RegExp.prototype.test.bind(/[^!"$&'()*+,\-.;=_`a-z{}~]/u);function OC(t){if(t.length===0)return!1;for(let n=0;n<t.length;n++)if(!AC(t[n])){if(t[n]==="%"&&n+2<t.length&&ta(t.slice(n+1,n+3))){n+=2;continue}return!1}return!0}function qb(t){let n=-1,e=0,i=-1,a=0;for(let s=0;s<t.length;s++)t[s]==="0"?(i===-1&&(i=s),a++,a>e&&(e=a,n=i)):(i=-1,a=0);if(e<2)return t.join(":");let r=t.slice(0,n).join(":"),o=t.slice(n+e).join(":");return r+"::"+o}function RC(t){let n=t.indexOf("::");if(n!==-1&&t.indexOf("::",n+1)!==-1)return;let e=n===-1?t.split(":"):t.slice(0,n).split(":"),i=n===-1?[]:t.slice(n+2).split(":");n!==-1&&(e.length===1&&e[0]===""&&(e.length=0),i.length===1&&i[0]===""&&(i.length=0));let a=e.concat(i),r=0;for(let s=0;s<a.length;s++){let l=a[s];if(l==="")return;if(l.indexOf(".")!==-1){if(s!==a.length-1||n!==-1&&i.length===0||!Km(l))return;r+=2;continue}if(!NC(l))return;a[s]=parseInt(l,16).toString(16),r++}if(n===-1)return r!==8?void 0:qb(a);if(r>=8)return;let o=a.slice(0,e.length);for(let s=r;s<8;s++)o.push("0");for(let s=e.length;s<a.length;s++)o.push(a[s]);return qb(o)}function Wm(t){let n=t[0]==="["&&t[t.length-1]==="]";if((t[0]==="["||t[t.length-1]==="]")&&!n)return{host:t,isIPV6:!1,error:!0};let i=n?t.slice(1,-1):t;if(n&&IC(i))return i=i.toLowerCase(),{host:`[${i}]`,escapedHost:i,isIPV6:!1,isIPVFuture:!0};if(PC(i,":")<2)return{host:t,isIPV6:!1,error:n};let a="",r=i.indexOf("%");if(r!==-1){let s=i.slice(r,r+3).toLowerCase()==="%25"?3:1;if(a=i.slice(r+s),!OC(a))return{host:t,isIPV6:!1,error:!0};i=i.slice(0,r)}let o=RC(i);return o===void 0?{host:t,isIPV6:!1,error:!0}:{host:o+(a?"%"+a:""),escapedHost:o+(a?"%25"+a:""),isIPV6:!0}}function PC(t,n){let e=0;for(let i=0;i<t.length;i++)t[i]===n&&e++;return e}function Wb(t){let n=t,e=[],i=-1,a=0;for(;a=n.length;){if(a===1){if(n===".")break;if(n==="/"){e.push("/");break}else{e.push(n);break}}else if(a===2){if(n[0]==="."){if(n[1]===".")break;if(n[1]==="/"){n=n.slice(2);continue}}else if(n[0]==="/"&&(n[1]==="."||n[1]==="/")){e.push("/");break}}else if(a===3&&n==="/.."){e.length!==0&&e.pop(),e.push("/");break}if(n[0]==="."){if(n[1]==="."){if(n[2]==="/"){n=n.slice(3);continue}}else if(n[1]==="/"){n=n.slice(2);continue}}else if(n[0]==="/"&&n[1]==="."){if(n[2]==="/"){n=n.slice(2);continue}else if(n[2]==="."&&n[3]==="/"){n=n.slice(3),e.length!==0&&e.pop();continue}}if((i=n.indexOf("/",1))===-1){e.push(n);break}else e.push(n.slice(0,i)),n=n.slice(i)}return e.join("")}function FC(t){if(t===""||t[0]==="/")return Wb(t);let n=t.split("/"),e=n.length-1,i=[],a=!1;for(let r=0;r<=e;r++){let o=n[r];o==="."||o===".."?(o===".."&&(i.length!==0&&i[i.length-1]!==".."?i.pop():i.push("..")),a=r===e):i.push(o)}return a&&i[i.length-1]!==".."&&i.push(""),(i.length===0||i[0]===""||i[0].includes(":"))&&i.unshift("."),i.join("/")}var LC={"@":"%40","/":"%2F","?":"%3F","#":"%23",":":"%3A"},BC=/[@/?#:]/g,jC=/[@/?#]/g;function Kb(t,n){let e=n?jC:BC;return e.lastIndex=0,t.replace(e,i=>LC[i])}function Yb(t,n=!1){if(t.indexOf("%")===-1)return t;let e="";for(let i=0;i<t.length;i++){if(t[i]==="%"&&i+2<t.length){let a=t.slice(i+1,i+3);if(ta(a)){let r=a.toUpperCase(),o=String.fromCharCode(parseInt(r,16));n&&Ym(o)?e+=o:e+="%"+r,i+=2;continue}}e+=t[i]}return e}function VC(t){let n="";for(let e=0;e<t.length;e++){let i=t[e];if(i==="%"&&e+2<t.length){let a=t.slice(e+1,e+3);if(ta(a)){let r=a.toUpperCase(),o=String.fromCharCode(parseInt(r,16));o!=="."&&Ym(o)?n+=o:n+="%"+r,e+=2;continue}}if(Gb(i))n+=i;else{let a=t.charCodeAt(e);if(a<128)n+=Zb(a)?i:zt[a];else if(a<55296||a>57343)n+=Dt(a);else if(a<=56319&&e+1<t.length){let r=t.charCodeAt(e+1);r>=56320&&r<=57343?(n+=Dt(65536+(a-55296<<10)+(r-56320)),e++):n+=Dt(65533)}else n+=Dt(65533)}}return n}function $C(t,n=!1){let e="",i=n&&t[0]!=="/";for(let a=0;a<t.length;a++){let r=t[a];if(r==="%"&&a+2<t.length){let o=t.slice(a+1,a+3);if(ta(o)){e+="%"+o.toUpperCase(),a+=2;continue}}if(r==="/"&&(i=!1),Gb(r)&&(r!==":"||!i))e+=r;else{let o=t.charCodeAt(a);if(o<128)e+=zt[o];else if(o<55296||o>57343)e+=Dt(o);else if(o<=56319&&a+1<t.length){let s=t.charCodeAt(a+1);s>=56320&&s<=57343?(e+=Dt(65536+(o-55296<<10)+(s-56320)),a++):e+=Dt(65533)}else e+=Dt(65533)}}return e}function Zm(t,n){let e="";for(let i=0;i<t.length;i++){let a=t[i];if(a==="%"&&i+2<t.length){let r=t.slice(i+1,i+3);if(ta(r)){e+="%"+r.toUpperCase(),i+=2;continue}}if(n(a))e+=a;else{let r=t.charCodeAt(i);if(r<128)e+=zt[r];else if(r<55296||r>57343)e+=Dt(r);else if(r<=56319&&i+1<t.length){let o=t.charCodeAt(i+1);o>=56320&&o<=57343?(e+=Dt(65536+(r-55296<<10)+(o-56320)),i++):e+=Dt(65533)}else e+=Dt(65533)}}return e}function Xb(t){return Zm(t,DC)}function zC(t){return Zm(t,Xm)}function HC(t){return Zm(t,Xm)}function Zb(t){return t>=48&&t<=57||t>=65&&t<=90||t>=97&&t<=122||t===42||t===43||t===45||t===46||t===47||t===64||t===95}function UC(t){let n="";for(let e=0;e<t.length;e++){let i=t[e];if(i==="%"&&e+2<t.length){let a=t.slice(e+1,e+3);if(ta(a)){let r=a.toUpperCase(),o=String.fromCharCode(parseInt(r,16));Ym(o)?n+=o:n+="%"+r,e+=2;continue}}if(Xm(i))n+=i;else{let a=t.charCodeAt(e);if(a<128)n+=Zb(a)?i:zt[a];else if(a<55296||a>57343)n+=Dt(a);else if(a<=56319&&e+1<t.length){let r=t.charCodeAt(e+1);r>=56320&&r<=57343?(n+=Dt(65536+(a-55296<<10)+(r-56320)),e++):n+=Dt(65533)}else n+=Dt(65533)}}return n}function qC(t){let n="";for(let e=0;e<t.length;e++){if(t[e]==="%"&&e+2<t.length){let i=t.slice(e+1,e+3);if(ta(i)){n+="%"+i.toUpperCase(),e+=2;continue}}n+=escape(t[e])}return n}function GC(t){let n=[];if(t.userinfo!==void 0&&(n.push(Xb(t.userinfo)),n.push("@")),t.host!==void 0){let e=t.host;if(!Km(e)){let i=Wm(e);i.isIPV6!==!0&&i.isIPVFuture!==!0&&(e=Yb(e,!0),i=Wm(e)),i.isIPV6===!0||i.isIPVFuture===!0?e=`[${i.escapedHost}]`:e=Kb(e,!1)}n.push(e)}if(typeof t.port=="number"||typeof t.port=="string"){let e=String(t.port);if(!EC(e))throw new TypeError("URI port is malformed.");n.push(":"),n.push(e)}return n.length?n.join(""):void 0}Qb.exports={nonSimpleDomain:TC,recomposeAuthority:GC,reescapeHostDelimiters:Kb,normalizePercentEncoding:Yb,normalizePathEncoding:VC,serializePathEncoding:$C,normalizeQueryFragmentEncoding:UC,encodeUserinfo:Xb,encodeQuery:zC,encodeFragment:HC,escapePreservingEscapes:qC,removeDotSegments:Wb,removeDotSegmentsRelative:FC,isIPv4:Km,isUUID:CC,normalizeIPv6:Wm,stringArrayToHexStripped:MC}});var iv=L((Wj,nv)=>{"use strict";var{isUUID:WC}=Qm(),KC=/^([\da-z][\d\-a-z]{0,31}):((?:[\w!$'()*+,\-./:;=@]|%[\da-f]{2})+)$/iu,YC=["http","https","ws","wss","urn","urn:uuid"];function XC(t){return YC.indexOf(t)!==-1}function Jm(t){return t.secure===!0?!0:t.secure===!1?!1:t.scheme?t.scheme.length===3&&(t.scheme[0]==="w"||t.scheme[0]==="W")&&(t.scheme[1]==="s"||t.scheme[1]==="S")&&(t.scheme[2]==="s"||t.scheme[2]==="S"):!1}function Jb(t){return t.host||(t.error=t.error||"HTTP URIs must have a host."),t}function ev(t){let n=String(t.scheme).toLowerCase()==="https";return(t.port===(n?443:80)||t.port==="")&&(t.port=void 0),t.path||(t.path="/"),t}function ZC(t){return t.secure=Jm(t),t.resourceName=(t.path||"/")+(t.query?"?"+t.query:""),t.path=void 0,t.query=void 0,t}function QC(t){if((t.port===(Jm(t)?443:80)||t.port==="")&&(t.port=void 0),typeof t.secure=="boolean"&&(t.scheme=t.secure?"wss":"ws",t.secure=void 0),t.resourceName){let n=t.resourceName.indexOf("?"),e=n===-1?t.resourceName:t.resourceName.slice(0,n);t.path=e&&e!=="/"?e:void 0,t.query=n===-1?void 0:t.resourceName.slice(n+1),t.resourceName=void 0}return t.fragment=void 0,t}function JC(t,n){if(!t.path)return t.error="URN can not be parsed",t;let e=t.path.match(KC);if(e&&e[0]===t.path){let i=n.scheme||t.scheme||"urn";t.nid=e[1].toLowerCase(),t.nss=e[2];let a=`${i}:${n.nid||t.nid}`,r=ep(a);t.path=void 0,r&&(t=r.parse(t,n))}else t.error=t.error||"URN can not be parsed.";return t}function eE(t,n){if(t.nid===void 0)throw new Error("URN without nid cannot be serialized");let e=n.scheme||t.scheme||"urn",i=t.nid.toLowerCase(),a=`${e}:${n.nid||i}`,r=ep(a);r&&(t=r.serialize(t,n));let o=t,s=t.nss;return o.path=`${i||n.nid}:${s}`,n.skipEscape=!0,o}function tE(t,n){let e=t;return e.uuid=e.nss,e.nss=void 0,!n.tolerant&&(!e.uuid||!WC(e.uuid))&&(e.error=e.error||"UUID is not valid."),e}function nE(t){let n=t;return n.nss=(t.uuid||"").toLowerCase(),n}var tv={scheme:"http",domainHost:!0,parse:Jb,serialize:ev},iE={scheme:"https",domainHost:tv.domainHost,parse:Jb,serialize:ev},$l={scheme:"ws",domainHost:!0,parse:ZC,serialize:QC},aE={scheme:"wss",domainHost:$l.domainHost,parse:$l.parse,serialize:$l.serialize},rE={scheme:"urn",parse:JC,serialize:eE,skipNormalize:!0},oE={scheme:"urn:uuid",parse:tE,serialize:nE,skipNormalize:!0},zl={http:tv,https:iE,ws:$l,wss:aE,urn:rE,"urn:uuid":oE};Object.setPrototypeOf(zl,null);function ep(t){return t&&(zl[t]||zl[t.toLowerCase()])||void 0}nv.exports={wsIsSecure:Jm,SCHEMES:zl,isValidSchemeName:XC,getSchemeHandler:ep}});var vv=L((Kj,Wl)=>{"use strict";var{normalizeIPv6:lv,removeDotSegments:Do,removeDotSegmentsRelative:cv,recomposeAuthority:sE,normalizePercentEncoding:tp,normalizePathEncoding:lE,serializePathEncoding:av,normalizeQueryFragmentEncoding:rv,encodeQuery:cE,encodeFragment:dE,reescapeHostDelimiters:mE,isIPv4:dv,nonSimpleDomain:pE}=Qm(),{SCHEMES:mv,getSchemeHandler:np}=iv(),pv=/^[A-Za-z][A-Za-z0-9+.-]*$/u,uv="URI scheme is malformed.",Hl="URI host is malformed.",uE=/[\0-\x20"#/:<>?@[\\\]^`{|}\x7F]/u;function ov(t){let n=String(t);if(!pv.test(n))throw new TypeError(uv);return n}function hE(t,n){return typeof t=="string"?t=SE(t,n):typeof t=="object"&&(t=ql(na(t,n),n)),t}function fE(t,n,e){let i=e?Object.assign({scheme:"null"},e):{scheme:"null"},{parsed:a,malformedAuthorityOrPort:r,malformedPercentEncoding:o,malformedSchemeSpecific:s,malformedHost:l,malformedScheme:c}=Ul(t,i),{parsed:d,malformedAuthorityOrPort:m,malformedPercentEncoding:u,malformedSchemeSpecific:f,malformedHost:g,malformedScheme:b}=Ul(n,i);if(r||m||o||u||s||f||l||g||c||b)throw new Error(a.error||d.error||"URI is malformed.");let _=hv(a,d,i,!0),x=np(e&&e.scheme||_.scheme),w=_.host,C=w!==void 0&&w!==""&&(dv(w)||lv(w).isIPV6);if(gv(_,e||{},x,C))throw new Error(Hl);fv(_,e||{},x,C);let F=w&&w.indexOf("%")!==-1&&!new RegExp("\\P{ASCII}","u").test(w);if(_.error&&!F)throw new Error(_.error);return i.skipEscape=!0,na(_,i)}function hv(t,n,e,i){let a={};if(i||(t=ql(na(t,e),e),n=ql(na(n,e),e)),e=e||{},!e.tolerant&&n.scheme)a.scheme=n.scheme,a.userinfo=n.userinfo,a.host=n.host,a.port=n.port,a.path=Do(n.path||""),a.query=n.query;else{if(n.userinfo!==void 0||n.host!==void 0||n.port!==void 0)a.userinfo=n.userinfo,a.host=n.host,a.port=n.port,a.path=Do(n.path||""),a.query=n.query;else{if(!n.path)a.path=t.path,n.query!==void 0?a.query=n.query:a.query=t.query;else{if(n.path[0]==="/")a.path=Do(n.path);else{let r=t.userinfo!==void 0||t.host!==void 0||t.port!==void 0;r&&!t.path?a.path="/"+n.path:t.path?a.path=t.path.slice(0,t.path.lastIndexOf("/")+1)+n.path:a.path=n.path,a.path=t.scheme===void 0&&!r&&a.path[0]!=="/"?cv(a.path):Do(a.path)}a.query=n.query}a.userinfo=t.userinfo,a.host=t.host,a.port=t.port}a.scheme=t.scheme}return a.fragment=n.fragment,a}function gE(t,n,e){let i=sv(t,e),a=sv(n,e);return i!==void 0&&a!==void 0&&i===a}function na(t,n){let e={host:t.host,scheme:t.scheme,userinfo:t.userinfo,port:t.port,path:t.path,query:t.query,nid:t.nid,nss:t.nss,uuid:t.uuid,fragment:t.fragment,reference:t.reference,resourceName:t.resourceName,secure:t.secure,error:""},i=Object.assign({},n),a=[];e.scheme&&(e.scheme=ov(e.scheme));let r=np(i.scheme||e.scheme);r&&r.serialize&&r.serialize(e,i);let o=e.userinfo!==void 0||e.host!==void 0||e.port!==void 0,s=(e.scheme===void 0||i.reference==="suffix")&&!o,l=!i.skipEscape&&s;e.path!==void 0&&(i.skipEscape?e.path=tp(e.path):e.path=av(e.path,l)),i.reference!=="suffix"&&e.scheme&&(e.scheme=ov(e.scheme),a.push(e.scheme,":"));let c=sE(e);if(c!==void 0&&(i.reference!=="suffix"&&a.push("//"),a.push(c),e.path&&e.path[0]!=="/"&&a.push("/")),e.path!==void 0){let d=e.path;!i.absolutePath&&(!r||!r.absolutePath)&&(d=s&&d[0]!=="/"?cv(d):Do(d)),s&&(d=av(d,!0)),c===void 0&&d[0]==="/"&&d[1]==="/"&&(d="/%2F"+d.slice(2)),a.push(d)}return e.query!==void 0&&a.push("?",cE(e.query)),e.fragment!==void 0&&a.push("#",dE(e.fragment)),a.join("")}var bE=/^(?:([^#/:?]+):)?(?:\/\/((?:([^#/?@]*)@)?(\[[^#/?\]]+\]|[^#/:?]*)(?::(\d*))?))?([^#?]*)(?:\?([^#]*))?(?:#((?:.|[\n\r])*))?/u,vE=/^(?:[^#/:?]+:)?\/\/([^/?#]*)/,_E=/^(?:[^#/:?]+:)?([/\\\t\n\r]*)/;function yE(t,n){if(n[2]!==void 0&&t.path&&t.path[0]!=="/")return'URI path must start with "/" when authority is present.';if(typeof t.port=="number"&&(t.port<0||t.port>65535))return"URI port is malformed."}function Eo(t){if(t===void 0)return!1;let n=t.indexOf("%");for(;n!==-1;){if(n+2>=t.length||!/^[\da-f]{2}$/iu.test(t.slice(n+1,n+3)))return!0;n=t.indexOf("%",n+3)}return!1}function Gl(t){return t[0]==="["&&t[t.length-1]==="]"}function xE(t){let n=t[4];return Eo(t[3])||n!==void 0&&!Gl(n)&&Eo(n)||Eo(t[6])||Eo(t[7])||Eo(t[8])}function fv(t,n,e,i){if(gv(t,n,e,i))return t.error=t.error||Hl,!0;if(!n.unicodeSupport&&(!e||!e.unicodeSupport)&&t.host&&!Gl(t.host)&&(n.domainHost||e&&e.domainHost)&&i===!1&&pE(t.host)){let a;try{a=new URL("http://"+t.host)}catch(r){return t.error=t.error||"Host's domain name can not be converted to ASCII: "+r,!0}if(!wE(a))return t.error=t.error||Hl,!0;t.host=a.hostname}return!1}function gv(t,n,e,i){return!!(t.host&&i===!1&&!Gl(t.host)&&(n.domainHost||e&&e.domainHost)&&uE.test(t.host))}function wE(t){return t.username===""&&t.password===""&&t.port===""&&t.pathname==="/"&&t.search===""&&t.hash===""&&t.hostname!==""}function Ul(t,n){let e=Object.assign({},n),i={scheme:void 0,userinfo:void 0,host:"",port:void 0,path:"",query:void 0,fragment:void 0},a=!1,r=!1,o=!1,s=!1,l=!1,c=!1,d=!1;e.reference==="suffix"&&(e.scheme?t=e.scheme+":"+t:t="//"+t);let m=t.match(vE);m!==null&&m[1].indexOf("\\")!==-1&&(i.error="URI authority must not contain a literal backslash.",a=!0);let u=t.match(_E);if(u!==null){let g=u[1],b=g.replace(/[\t\n\r]/g,"");b.length>=2&&(b.slice(0,2)!=="//"?(i.error=i.error||"URI authority must not contain a literal backslash.",a=!0):g.length!==b.length&&(i.error=i.error||"URI authority introducer must not contain whitespace.",a=!0))}let f=t.match(bE);if(f){i.scheme=f[1],i.userinfo=f[3],i.host=f[4],i.port=parseInt(f[5],10),i.path=f[6]||"",i.query=f[7],i.fragment=f[8],i.scheme!==void 0&&(pv.test(i.scheme)?i.scheme=i.scheme.toLowerCase():(i.error=i.error||uv,c=!0)),r=xE(f),r&&(i.error=i.error||"URI contains malformed percent-encoding."),isNaN(i.port)&&(i.port=f[5]);let g=yE(i,f);if(g!==void 0&&(i.error=i.error||g,a=!0),i.host)if(dv(i.host)===!1){let x=Gl(i.host),w=i.host.indexOf("[")!==-1||i.host.indexOf("]")!==-1,C=lv(i.host);d=C.isIPV6||C.isIPVFuture===!0,l=w&&(!x||C.error===!0),i.host=d?C.host:C.host.toLowerCase(),l&&(i.error=i.error||Hl,a=!0)}else d=!0;i.scheme===void 0&&i.userinfo===void 0&&i.host===void 0&&i.port===void 0&&i.query===void 0&&!i.path?i.reference="same-document":i.scheme===void 0?i.reference="relative":i.fragment===void 0?i.reference="absolute":i.reference="uri",e.reference&&e.reference!=="suffix"&&e.reference!==i.reference&&(i.error=i.error||"URI is not a "+e.reference+" reference.");let b=np(e.scheme||i.scheme);if(l||(s=fv(i,e,b,d)),t.indexOf("%")!==-1&&i.host!==void 0&&!l){let _=d?i.host:tp(i.host,!0);d||(_=tp(_.toLowerCase())),i.host=d||e.scheme!=="null"?mE(_,d):_}(!b||b&&!b.skipNormalize)&&(i.path&&(i.path=lE(i.path)),i.query&&(i.query=rv(i.query)),i.fragment&&(i.fragment=rv(i.fragment))),b&&b.parse&&(b.parse(i,e),b===mv.urn&&i.nid===void 0&&(o=!0))}else i.error=i.error||"URI can not be parsed.";return{parsed:i,malformedAuthorityOrPort:a,malformedPercentEncoding:r,malformedSchemeSpecific:o,malformedHost:s,malformedScheme:c}}function ql(t,n){return Ul(t,n).parsed}function SE(t,n){return bv(t,n).normalized}function bv(t,n){let{parsed:e,malformedAuthorityOrPort:i,malformedPercentEncoding:a,malformedSchemeSpecific:r,malformedHost:o,malformedScheme:s}=Ul(t,n);return{normalized:i||a||r||o||s?t:na(e,n),malformedAuthorityOrPort:i,malformedPercentEncoding:a,malformedSchemeSpecific:r,malformedHost:o,malformedScheme:s}}function sv(t,n){if(typeof t!="string"&&typeof t!="object")return;let e;try{e=typeof t=="string"?t:na(t,n)}catch{return}let{normalized:i,malformedAuthorityOrPort:a,malformedPercentEncoding:r,malformedSchemeSpecific:o,malformedHost:s,malformedScheme:l}=bv(e,n);return a||r||o||s||l?void 0:i}var ip={SCHEMES:mv,normalize:hE,resolve:fE,resolveComponent:hv,equal:gE,serialize:na,parse:ql};Wl.exports=ip;Wl.exports.default=ip;Wl.exports.fastUri=ip});var yv=L(ap=>{"use strict";Object.defineProperty(ap,"__esModule",{value:!0});var _v=vv();_v.code='require("ajv/dist/runtime/uri").default';ap.default=_v});var Mv=L(bt=>{"use strict";Object.defineProperty(bt,"__esModule",{value:!0});bt.CodeGen=bt.Name=bt.nil=bt.stringify=bt.str=bt._=bt.KeywordCxt=void 0;var kE=ko();Object.defineProperty(bt,"KeywordCxt",{enumerable:!0,get:function(){return kE.KeywordCxt}});var Ya=ue();Object.defineProperty(bt,"_",{enumerable:!0,get:function(){return Ya._}});Object.defineProperty(bt,"str",{enumerable:!0,get:function(){return Ya.str}});Object.defineProperty(bt,"stringify",{enumerable:!0,get:function(){return Ya.stringify}});Object.defineProperty(bt,"nil",{enumerable:!0,get:function(){return Ya.nil}});Object.defineProperty(bt,"Name",{enumerable:!0,get:function(){return Ya.Name}});Object.defineProperty(bt,"CodeGen",{enumerable:!0,get:function(){return Ya.CodeGen}});var CE=Bl(),Cv=Co(),EE=Dm(),Mo=Vl(),DE=ue(),No=xo(),Kl=yo(),op=ke(),xv=Ub(),ME=yv(),Ev=(t,n)=>new RegExp(t,n);Ev.code="new RegExp";var NE=["removeAdditional","useDefaults","coerceTypes"],IE=new Set(["validate","serialize","parse","wrapper","root","schema","keyword","pattern","formats","validate$data","func","obj","Error"]),AE={errorDataPath:"",format:"`validateFormats: false` can be used instead.",nullable:'"nullable" keyword is supported by default.',jsonPointers:"Deprecated jsPropertySyntax can be used instead.",extendRefs:"Deprecated ignoreKeywordsWithRef can be used instead.",missingRefs:"Pass empty schema with $id that should be ignored to ajv.addSchema.",processCode:"Use option `code: {process: (code, schemaEnv: object) => string}`",sourceCode:"Use option `code: {source: true}`",strictDefaults:"It is default now, see option `strict`.",strictKeywords:"It is default now, see option `strict`.",uniqueItems:'"uniqueItems" keyword is always validated.',unknownFormats:"Disable strict mode or pass `true` to `ajv.addFormat` (or `formats` option).",cache:"Map is used as cache, schema object as key.",serialize:"Map is used as cache, schema object as key.",ajvErrors:"It is default now."},TE={ignoreKeywordsWithRef:"",jsPropertySyntax:"",unicode:'"minLength"/"maxLength" account for unicode characters by default.'},wv=200;function OE(t){var n,e,i,a,r,o,s,l,c,d,m,u,f,g,b,_,x,w,C,F,A,Q,Re,He,Be;let G=t.strict,oe=(n=t.code)===null||n===void 0?void 0:n.optimize,k=oe===!0||oe===void 0?1:oe||0,ce=(i=(e=t.code)===null||e===void 0?void 0:e.regExp)!==null&&i!==void 0?i:Ev,je=(a=t.uriResolver)!==null&&a!==void 0?a:ME.default;return{strictSchema:(o=(r=t.strictSchema)!==null&&r!==void 0?r:G)!==null&&o!==void 0?o:!0,strictNumbers:(l=(s=t.strictNumbers)!==null&&s!==void 0?s:G)!==null&&l!==void 0?l:!0,strictTypes:(d=(c=t.strictTypes)!==null&&c!==void 0?c:G)!==null&&d!==void 0?d:"log",strictTuples:(u=(m=t.strictTuples)!==null&&m!==void 0?m:G)!==null&&u!==void 0?u:"log",strictRequired:(g=(f=t.strictRequired)!==null&&f!==void 0?f:G)!==null&&g!==void 0?g:!1,code:t.code?De(N({},t.code),{optimize:k,regExp:ce}):{optimize:k,regExp:ce},loopRequired:(b=t.loopRequired)!==null&&b!==void 0?b:wv,loopEnum:(_=t.loopEnum)!==null&&_!==void 0?_:wv,meta:(x=t.meta)!==null&&x!==void 0?x:!0,messages:(w=t.messages)!==null&&w!==void 0?w:!0,inlineRefs:(C=t.inlineRefs)!==null&&C!==void 0?C:!0,schemaId:(F=t.schemaId)!==null&&F!==void 0?F:"$id",addUsedSchema:(A=t.addUsedSchema)!==null&&A!==void 0?A:!0,validateSchema:(Q=t.validateSchema)!==null&&Q!==void 0?Q:!0,validateFormats:(Re=t.validateFormats)!==null&&Re!==void 0?Re:!0,unicodeRegExp:(He=t.unicodeRegExp)!==null&&He!==void 0?He:!0,int32range:(Be=t.int32range)!==null&&Be!==void 0?Be:!0,uriResolver:je}}var Io=class{constructor(n={}){this.schemas={},this.refs={},this.formats=Object.create(null),this._compilations=new Set,this._loading={},this._cache=new Map,n=this.opts=N(N({},n),OE(n));let{es5:e,lines:i}=this.opts.code;this.scope=new DE.ValueScope({scope:{},prefixes:IE,es5:e,lines:i}),this.logger=jE(n.logger);let a=n.validateFormats;n.validateFormats=!1,this.RULES=(0,EE.getRules)(),Sv.call(this,AE,n,"NOT SUPPORTED"),Sv.call(this,TE,n,"DEPRECATED","warn"),this._metaOpts=LE.call(this),n.formats&&PE.call(this),this._addVocabularies(),this._addDefaultMetaSchema(),n.keywords&&FE.call(this,n.keywords),typeof n.meta=="object"&&this.addMetaSchema(n.meta),RE.call(this),n.validateFormats=a}_addVocabularies(){this.addKeyword("$async")}_addDefaultMetaSchema(){let{$data:n,meta:e,schemaId:i}=this.opts,a=xv;i==="id"&&(a=N({},xv),a.id=a.$id,delete a.$id),e&&n&&this.addMetaSchema(a,a[i],!1)}defaultMeta(){let{meta:n,schemaId:e}=this.opts;return this.opts.defaultMeta=typeof n=="object"?n[e]||n:void 0}validate(n,e){let i;if(typeof n=="string"){if(i=this.getSchema(n),!i)throw new Error(`no schema with key or ref "${n}"`)}else i=this.compile(n);let a=i(e);return"$async"in i||(this.errors=i.errors),a}compile(n,e){let i=this._addSchema(n,e);return i.validate||this._compileSchemaEnv(i)}compileAsync(n,e){if(typeof this.opts.loadSchema!="function")throw new Error("options.loadSchema should be a function");let{loadSchema:i}=this.opts;return a.call(this,n,e);async function a(d,m){await r.call(this,d.$schema);let u=this._addSchema(d,m);return u.validate||o.call(this,u)}async function r(d){d&&!this.getSchema(d)&&await a.call(this,{$ref:d},!0)}async function o(d){try{return this._compileSchemaEnv(d)}catch(m){if(!(m instanceof Cv.default))throw m;return s.call(this,m),await l.call(this,m.missingSchema),o.call(this,d)}}function s({missingSchema:d,missingRef:m}){if(this.refs[d])throw new Error(`AnySchema ${d} is loaded but ${m} cannot be resolved`)}async function l(d){let m=await c.call(this,d);this.refs[d]||await r.call(this,m.$schema),this.refs[d]||this.addSchema(m,d,e)}async function c(d){let m=this._loading[d];if(m)return m;try{return await(this._loading[d]=i(d))}finally{delete this._loading[d]}}}addSchema(n,e,i,a=this.opts.validateSchema){if(Array.isArray(n)){for(let o of n)this.addSchema(o,void 0,i,a);return this}let r;if(typeof n=="object"){let{schemaId:o}=this.opts;if(r=n[o],r!==void 0&&typeof r!="string")throw new Error(`schema ${o} must be string`)}return e=(0,No.normalizeId)(e||r),this._checkUnique(e),this.schemas[e]=this._addSchema(n,i,e,a,!0),this}addMetaSchema(n,e,i=this.opts.validateSchema){return this.addSchema(n,e,!0,i),this}validateSchema(n,e){if(typeof n=="boolean")return!0;let i;if(i=n.$schema,i!==void 0&&typeof i!="string")throw new Error("$schema must be a string");if(i=i||this.opts.defaultMeta||this.defaultMeta(),!i)return this.logger.warn("meta-schema not available"),this.errors=null,!0;let a=this.validate(i,n);if(!a&&e){let r="schema is invalid: "+this.errorsText();if(this.opts.validateSchema==="log")this.logger.error(r);else throw new Error(r)}return a}getSchema(n){let e;for(;typeof(e=kv.call(this,n))=="string";)n=e;if(e===void 0){let{schemaId:i}=this.opts,a=new Mo.SchemaEnv({schema:{},schemaId:i});if(e=Mo.resolveSchema.call(this,a,n),!e)return;this.refs[n]=e}return e.validate||this._compileSchemaEnv(e)}removeSchema(n){if(n instanceof RegExp)return this._removeAllSchemas(this.schemas,n),this._removeAllSchemas(this.refs,n),this;switch(typeof n){case"undefined":return this._removeAllSchemas(this.schemas),this._removeAllSchemas(this.refs),this._cache.clear(),this;case"string":{let e=kv.call(this,n);return typeof e=="object"&&this._cache.delete(e.schema),delete this.schemas[n],delete this.refs[n],this}case"object":{let e=n;this._cache.delete(e);let i=n[this.opts.schemaId];return i&&(i=(0,No.normalizeId)(i),delete this.schemas[i],delete this.refs[i]),this}default:throw new Error("ajv.removeSchema: invalid parameter")}}addVocabulary(n){for(let e of n)this.addKeyword(e);return this}addKeyword(n,e){let i;if(typeof n=="string")i=n,typeof e=="object"&&(this.logger.warn("these parameters are deprecated, see docs for addKeyword"),e.keyword=i);else if(typeof n=="object"&&e===void 0){if(e=n,i=e.keyword,Array.isArray(i)&&!i.length)throw new Error("addKeywords: keyword must be string or non-empty array")}else throw new Error("invalid addKeywords parameters");if($E.call(this,i,e),!e)return(0,op.eachItem)(i,r=>rp.call(this,r)),this;HE.call(this,e);let a=De(N({},e),{type:(0,Kl.getJSONTypes)(e.type),schemaType:(0,Kl.getJSONTypes)(e.schemaType)});return(0,op.eachItem)(i,a.type.length===0?r=>rp.call(this,r,a):r=>a.type.forEach(o=>rp.call(this,r,a,o))),this}getKeyword(n){let e=this.RULES.all[n];return typeof e=="object"?e.definition:!!e}removeKeyword(n){let{RULES:e}=this;delete e.keywords[n],delete e.all[n];for(let i of e.rules){let a=i.rules.findIndex(r=>r.keyword===n);a>=0&&i.rules.splice(a,1)}return this}addFormat(n,e){return typeof e=="string"&&(e=new RegExp(e)),this.formats[n]=e,this}errorsText(n=this.errors,{separator:e=", ",dataVar:i="data"}={}){return!n||n.length===0?"No errors":n.map(a=>`${i}${a.instancePath} ${a.message}`).reduce((a,r)=>a+e+r)}$dataMetaSchema(n,e){let i=this.RULES.all;n=JSON.parse(JSON.stringify(n));for(let a of e){let r=a.split("/").slice(1),o=n;for(let s of r)o=o[s];for(let s in i){let l=i[s];if(typeof l!="object")continue;let{$data:c}=l.definition,d=o[s];c&&d&&(o[s]=Dv(d))}}return n}_removeAllSchemas(n,e){for(let i in n){let a=n[i];(!e||e.test(i))&&(typeof a=="string"?delete n[i]:a&&!a.meta&&(this._cache.delete(a.schema),delete n[i]))}}_addSchema(n,e,i,a=this.opts.validateSchema,r=this.opts.addUsedSchema){let o,{schemaId:s}=this.opts;if(typeof n=="object")o=n[s];else{if(this.opts.jtd)throw new Error("schema must be object");if(typeof n!="boolean")throw new Error("schema must be object or boolean")}let l=this._cache.get(n);if(l!==void 0)return l;i=(0,No.normalizeId)(o||i);let c=No.getSchemaRefs.call(this,n,i);return l=new Mo.SchemaEnv({schema:n,schemaId:s,meta:e,baseId:i,localRefs:c}),this._cache.set(l.schema,l),r&&!i.startsWith("#")&&(i&&this._checkUnique(i),this.refs[i]=l),a&&this.validateSchema(n,!0),l}_checkUnique(n){if(this.schemas[n]||this.refs[n])throw new Error(`schema with key or id "${n}" already exists`)}_compileSchemaEnv(n){if(n.meta?this._compileMetaSchema(n):Mo.compileSchema.call(this,n),!n.validate)throw new Error("ajv implementation error");return n.validate}_compileMetaSchema(n){let e=this.opts;this.opts=this._metaOpts;try{Mo.compileSchema.call(this,n)}finally{this.opts=e}}};Io.ValidationError=CE.default;Io.MissingRefError=Cv.default;bt.default=Io;function Sv(t,n,e,i="error"){for(let a in t){let r=a;r in n&&this.logger[i](`${e}: option ${a}. ${t[r]}`)}}function kv(t){return t=(0,No.normalizeId)(t),this.schemas[t]||this.refs[t]}function RE(){let t=this.opts.schemas;if(t)if(Array.isArray(t))this.addSchema(t);else for(let n in t)this.addSchema(t[n],n)}function PE(){for(let t in this.opts.formats){let n=this.opts.formats[t];n&&this.addFormat(t,n)}}function FE(t){if(Array.isArray(t)){this.addVocabulary(t);return}this.logger.warn("keywords option as map is deprecated, pass array");for(let n in t){let e=t[n];e.keyword||(e.keyword=n),this.addKeyword(e)}}function LE(){let t=N({},this.opts);for(let n of NE)delete t[n];return t}var BE={log(){},warn(){},error(){}};function jE(t){if(t===!1)return BE;if(t===void 0)return console;if(t.log&&t.warn&&t.error)return t;throw new Error("logger must implement log, warn and error methods")}var VE=/^[a-z_$][a-z0-9_$:-]*$/i;function $E(t,n){let{RULES:e}=this;if((0,op.eachItem)(t,i=>{if(e.keywords[i])throw new Error(`Keyword ${i} is already defined`);if(!VE.test(i))throw new Error(`Keyword ${i} has invalid name`)}),!!n&&n.$data&&!("code"in n||"validate"in n))throw new Error('$data keyword must have "code" or "validate" function')}function rp(t,n,e){var i;let a=n?.post;if(e&&a)throw new Error('keyword with "post" flag cannot have "type"');let{RULES:r}=this,o=a?r.post:r.rules.find(({type:l})=>l===e);if(o||(o={type:e,rules:[]},r.rules.push(o)),r.keywords[t]=!0,!n)return;let s={keyword:t,definition:De(N({},n),{type:(0,Kl.getJSONTypes)(n.type),schemaType:(0,Kl.getJSONTypes)(n.schemaType)})};n.before?zE.call(this,o,s,n.before):o.rules.push(s),r.all[t]=s,(i=n.implements)===null||i===void 0||i.forEach(l=>this.addKeyword(l))}function zE(t,n,e){let i=t.rules.findIndex(a=>a.keyword===e);i>=0?t.rules.splice(i,0,n):(t.rules.push(n),this.logger.warn(`rule ${e} is not defined`))}function HE(t){let{metaSchema:n}=t;n!==void 0&&(t.$data&&this.opts.$data&&(n=Dv(n)),t.validateSchema=this.compile(n,!0))}var UE={$ref:"https://raw.githubusercontent.com/ajv-validator/ajv/master/lib/refs/data.json#"};function Dv(t){return{anyOf:[t,UE]}}});var Nv=L(sp=>{"use strict";Object.defineProperty(sp,"__esModule",{value:!0});var qE={keyword:"id",code(){throw new Error('NOT SUPPORTED: keyword "id", use "$id" for schema ID')}};sp.default=qE});var Ov=L(ia=>{"use strict";Object.defineProperty(ia,"__esModule",{value:!0});ia.callRef=ia.getValidate=void 0;var GE=Co(),Iv=tn(),Ht=ue(),Xa=Qn(),Av=Vl(),Yl=ke(),WE={keyword:"$ref",schemaType:"string",code(t){let{gen:n,schema:e,it:i}=t,{baseId:a,schemaEnv:r,validateName:o,opts:s,self:l}=i,{root:c}=r;if((e==="#"||e==="#/")&&a===c.baseId)return m();let d=Av.resolveRef.call(l,c,a,e);if(d===void 0)throw new GE.default(i.opts.uriResolver,a,e);if(d instanceof Av.SchemaEnv)return u(d);return f(d);function m(){if(r===c)return Xl(t,o,r,r.$async);let g=n.scopeValue("root",{ref:c});return Xl(t,(0,Ht._)`${g}.validate`,c,c.$async)}function u(g){let b=Tv(t,g);Xl(t,b,g,g.$async)}function f(g){let b=n.scopeValue("schema",s.code.source===!0?{ref:g,code:(0,Ht.stringify)(g)}:{ref:g}),_=n.name("valid"),x=t.subschema({schema:g,dataTypes:[],schemaPath:Ht.nil,topSchemaRef:b,errSchemaPath:e},_);t.mergeEvaluated(x),t.ok(_)}}};function Tv(t,n){let{gen:e}=t;return n.validate?e.scopeValue("validate",{ref:n.validate}):(0,Ht._)`${e.scopeValue("wrapper",{ref:n})}.validate`}ia.getValidate=Tv;function Xl(t,n,e,i){let{gen:a,it:r}=t,{allErrors:o,schemaEnv:s,opts:l}=r,c=l.passContext?Xa.default.this:Ht.nil;i?d():m();function d(){if(!s.$async)throw new Error("async schema referenced by sync schema");let g=a.let("valid");a.try(()=>{a.code((0,Ht._)`await ${(0,Iv.callValidateCode)(t,n,c)}`),f(n),o||a.assign(g,!0)},b=>{a.if((0,Ht._)`!(${b} instanceof ${r.ValidationError})`,()=>a.throw(b)),u(b),o||a.assign(g,!1)}),t.ok(g)}function m(){t.result((0,Iv.callValidateCode)(t,n,c),()=>f(n),()=>u(n))}function u(g){let b=(0,Ht._)`${g}.errors`;a.assign(Xa.default.vErrors,(0,Ht._)`${Xa.default.vErrors} === null ? ${b} : ${Xa.default.vErrors}.concat(${b})`),a.assign(Xa.default.errors,(0,Ht._)`${Xa.default.vErrors}.length`)}function f(g){var b;if(!r.opts.unevaluated)return;let _=(b=e?.validate)===null||b===void 0?void 0:b.evaluated;if(r.props!==!0)if(_&&!_.dynamicProps)_.props!==void 0&&(r.props=Yl.mergeEvaluated.props(a,_.props,r.props));else{let x=a.var("props",(0,Ht._)`${g}.evaluated.props`);r.props=Yl.mergeEvaluated.props(a,x,r.props,Ht.Name)}if(r.items!==!0)if(_&&!_.dynamicItems)_.items!==void 0&&(r.items=Yl.mergeEvaluated.items(a,_.items,r.items));else{let x=a.var("items",(0,Ht._)`${g}.evaluated.items`);r.items=Yl.mergeEvaluated.items(a,x,r.items,Ht.Name)}}}ia.callRef=Xl;ia.default=WE});var Rv=L(lp=>{"use strict";Object.defineProperty(lp,"__esModule",{value:!0});var KE=Nv(),YE=Ov(),XE=["$schema","$id","$defs","$vocabulary",{keyword:"$comment"},"definitions",KE.default,YE.default];lp.default=XE});var Pv=L(cp=>{"use strict";Object.defineProperty(cp,"__esModule",{value:!0});var Zl=ue(),Di=Zl.operators,Ql={maximum:{okStr:"<=",ok:Di.LTE,fail:Di.GT},minimum:{okStr:">=",ok:Di.GTE,fail:Di.LT},exclusiveMaximum:{okStr:"<",ok:Di.LT,fail:Di.GTE},exclusiveMinimum:{okStr:">",ok:Di.GT,fail:Di.LTE}},ZE={message:({keyword:t,schemaCode:n})=>(0,Zl.str)`must be ${Ql[t].okStr} ${n}`,params:({keyword:t,schemaCode:n})=>(0,Zl._)`{comparison: ${Ql[t].okStr}, limit: ${n}}`},QE={keyword:Object.keys(Ql),type:"number",schemaType:"number",$data:!0,error:ZE,code(t){let{keyword:n,data:e,schemaCode:i}=t;t.fail$data((0,Zl._)`${e} ${Ql[n].fail} ${i} || isNaN(${e})`)}};cp.default=QE});var Fv=L(dp=>{"use strict";Object.defineProperty(dp,"__esModule",{value:!0});var Ao=ue(),JE={message:({schemaCode:t})=>(0,Ao.str)`must be multiple of ${t}`,params:({schemaCode:t})=>(0,Ao._)`{multipleOf: ${t}}`},eD={keyword:"multipleOf",type:"number",schemaType:"number",$data:!0,error:JE,code(t){let{gen:n,data:e,schemaCode:i,it:a}=t,r=a.opts.multipleOfPrecision,o=n.let("res"),s=r?(0,Ao._)`Math.abs(Math.round(${o}) - ${o}) > 1e-${r}`:(0,Ao._)`${o} !== parseInt(${o})`;t.fail$data((0,Ao._)`(${i} === 0 || (${o} = ${e}/${i}, ${s}))`)}};dp.default=eD});var Bv=L(mp=>{"use strict";Object.defineProperty(mp,"__esModule",{value:!0});function Lv(t){let n=t.length,e=0,i=0,a;for(;i<n;)e++,a=t.charCodeAt(i++),a>=55296&&a<=56319&&i<n&&(a=t.charCodeAt(i),(a&64512)===56320&&i++);return e}mp.default=Lv;Lv.code='require("ajv/dist/runtime/ucs2length").default'});var jv=L(pp=>{"use strict";Object.defineProperty(pp,"__esModule",{value:!0});var aa=ue(),tD=ke(),nD=Bv(),iD={message({keyword:t,schemaCode:n}){let e=t==="maxLength"?"more":"fewer";return(0,aa.str)`must NOT have ${e} than ${n} characters`},params:({schemaCode:t})=>(0,aa._)`{limit: ${t}}`},aD={keyword:["maxLength","minLength"],type:"string",schemaType:"number",$data:!0,error:iD,code(t){let{keyword:n,data:e,schemaCode:i,it:a}=t,r=n==="maxLength"?aa.operators.GT:aa.operators.LT,o=a.opts.unicode===!1?(0,aa._)`${e}.length`:(0,aa._)`${(0,tD.useFunc)(t.gen,nD.default)}(${e})`;t.fail$data((0,aa._)`${o} ${r} ${i}`)}};pp.default=aD});var Vv=L(up=>{"use strict";Object.defineProperty(up,"__esModule",{value:!0});var rD=tn(),oD=ke(),Za=ue(),sD={message:({schemaCode:t})=>(0,Za.str)`must match pattern "${t}"`,params:({schemaCode:t})=>(0,Za._)`{pattern: ${t}}`},lD={keyword:"pattern",type:"string",schemaType:"string",$data:!0,error:sD,code(t){let{gen:n,data:e,$data:i,schema:a,schemaCode:r,it:o}=t,s=o.opts.unicodeRegExp?"u":"";if(i){let{regExp:l}=o.opts.code,c=l.code==="new RegExp"?(0,Za._)`new RegExp`:(0,oD.useFunc)(n,l),d=n.let("valid");n.try(()=>n.assign(d,(0,Za._)`${c}(${r}, ${s}).test(${e})`),()=>n.assign(d,!1)),t.fail$data((0,Za._)`!${d}`)}else{let l=(0,rD.usePattern)(t,a);t.fail$data((0,Za._)`!${l}.test(${e})`)}}};up.default=lD});var $v=L(hp=>{"use strict";Object.defineProperty(hp,"__esModule",{value:!0});var To=ue(),cD={message({keyword:t,schemaCode:n}){let e=t==="maxProperties"?"more":"fewer";return(0,To.str)`must NOT have ${e} than ${n} properties`},params:({schemaCode:t})=>(0,To._)`{limit: ${t}}`},dD={keyword:["maxProperties","minProperties"],type:"object",schemaType:"number",$data:!0,error:cD,code(t){let{keyword:n,data:e,schemaCode:i}=t,a=n==="maxProperties"?To.operators.GT:To.operators.LT;t.fail$data((0,To._)`Object.keys(${e}).length ${a} ${i}`)}};hp.default=dD});var zv=L(fp=>{"use strict";Object.defineProperty(fp,"__esModule",{value:!0});var Oo=tn(),Ro=ue(),mD=ke(),pD={message:({params:{missingProperty:t}})=>(0,Ro.str)`must have required property '${t}'`,params:({params:{missingProperty:t}})=>(0,Ro._)`{missingProperty: ${t}}`},uD={keyword:"required",type:"object",schemaType:"array",$data:!0,error:pD,code(t){let{gen:n,schema:e,schemaCode:i,data:a,$data:r,it:o}=t,{opts:s}=o;if(!r&&e.length===0)return;let l=e.length>=s.loopRequired;if(o.allErrors?c():d(),s.strictRequired){let f=t.parentSchema.properties,{definedProperties:g}=t.it;for(let b of e)if(f?.[b]===void 0&&!g.has(b)){let _=o.schemaEnv.baseId+o.errSchemaPath,x=`required property "${b}" is not defined at "${_}" (strictRequired)`;(0,mD.checkStrictMode)(o,x,o.opts.strictRequired)}}function c(){if(l||r)t.block$data(Ro.nil,m);else for(let f of e)(0,Oo.checkReportMissingProp)(t,f)}function d(){let f=n.let("missing");if(l||r){let g=n.let("valid",!0);t.block$data(g,()=>u(f,g)),t.ok(g)}else n.if((0,Oo.checkMissingProp)(t,e,f)),(0,Oo.reportMissingProp)(t,f),n.else()}function m(){n.forOf("prop",i,f=>{t.setParams({missingProperty:f}),n.if((0,Oo.noPropertyInData)(n,a,f,s.ownProperties),()=>t.error())})}function u(f,g){t.setParams({missingProperty:f}),n.forOf(f,i,()=>{n.assign(g,(0,Oo.propertyInData)(n,a,f,s.ownProperties)),n.if((0,Ro.not)(g),()=>{t.error(),n.break()})},Ro.nil)}}};fp.default=uD});var Hv=L(gp=>{"use strict";Object.defineProperty(gp,"__esModule",{value:!0});var Po=ue(),hD={message({keyword:t,schemaCode:n}){let e=t==="maxItems"?"more":"fewer";return(0,Po.str)`must NOT have ${e} than ${n} items`},params:({schemaCode:t})=>(0,Po._)`{limit: ${t}}`},fD={keyword:["maxItems","minItems"],type:"array",schemaType:"number",$data:!0,error:hD,code(t){let{keyword:n,data:e,schemaCode:i}=t,a=n==="maxItems"?Po.operators.GT:Po.operators.LT;t.fail$data((0,Po._)`${e}.length ${a} ${i}`)}};gp.default=fD});var Jl=L(bp=>{"use strict";Object.defineProperty(bp,"__esModule",{value:!0});var Uv=Pm();Uv.code='require("ajv/dist/runtime/equal").default';bp.default=Uv});var qv=L(_p=>{"use strict";Object.defineProperty(_p,"__esModule",{value:!0});var vp=yo(),vt=ue(),gD=ke(),bD=Jl(),vD={message:({params:{i:t,j:n}})=>(0,vt.str)`must NOT have duplicate items (items ## ${n} and ${t} are identical)`,params:({params:{i:t,j:n}})=>(0,vt._)`{i: ${t}, j: ${n}}`},_D={keyword:"uniqueItems",type:"array",schemaType:"boolean",$data:!0,error:vD,code(t){let{gen:n,data:e,$data:i,schema:a,parentSchema:r,schemaCode:o,it:s}=t;if(!i&&!a)return;let l=n.let("valid"),c=r.items?(0,vp.getSchemaTypes)(r.items):[];t.block$data(l,d,(0,vt._)`${o} === false`),t.ok(l);function d(){let g=n.let("i",(0,vt._)`${e}.length`),b=n.let("j");t.setParams({i:g,j:b}),n.assign(l,!0),n.if((0,vt._)`${g} > 1`,()=>(m()?u:f)(g,b))}function m(){return c.length>0&&!c.some(g=>g==="object"||g==="array")}function u(g,b){let _=n.name("item"),x=(0,vp.checkDataTypes)(c,_,s.opts.strictNumbers,vp.DataType.Wrong),w=n.const("indices",(0,vt._)`{}`);n.for((0,vt._)`;${g}--;`,()=>{n.let(_,(0,vt._)`${e}[${g}]`),n.if(x,(0,vt._)`continue`),c.length>1&&n.if((0,vt._)`typeof ${_} == "string"`,(0,vt._)`${_} += "_"`),n.if((0,vt._)`typeof ${w}[${_}] == "number"`,()=>{n.assign(b,(0,vt._)`${w}[${_}]`),t.error(),n.assign(l,!1).break()}).code((0,vt._)`${w}[${_}] = ${g}`)})}function f(g,b){let _=(0,gD.useFunc)(n,bD.default),x=n.name("outer");n.label(x).for((0,vt._)`;${g}--;`,()=>n.for((0,vt._)`${b} = ${g}; ${b}--;`,()=>n.if((0,vt._)`${_}(${e}[${g}], ${e}[${b}])`,()=>{t.error(),n.assign(l,!1).break(x)})))}}};_p.default=_D});var Gv=L(xp=>{"use strict";Object.defineProperty(xp,"__esModule",{value:!0});var yp=ue(),yD=ke(),xD=Jl(),wD={message:"must be equal to constant",params:({schemaCode:t})=>(0,yp._)`{allowedValue: ${t}}`},SD={keyword:"const",$data:!0,error:wD,code(t){let{gen:n,data:e,$data:i,schemaCode:a,schema:r}=t;i||r&&typeof r=="object"?t.fail$data((0,yp._)`!${(0,yD.useFunc)(n,xD.default)}(${e}, ${a})`):t.fail((0,yp._)`${r} !== ${e}`)}};xp.default=SD});var Wv=L(wp=>{"use strict";Object.defineProperty(wp,"__esModule",{value:!0});var Fo=ue(),kD=ke(),CD=Jl(),ED={message:"must be equal to one of the allowed values",params:({schemaCode:t})=>(0,Fo._)`{allowedValues: ${t}}`},DD={keyword:"enum",schemaType:"array",$data:!0,error:ED,code(t){let{gen:n,data:e,$data:i,schema:a,schemaCode:r,it:o}=t;if(!i&&a.length===0)throw new Error("enum must have non-empty array");let s=a.length>=o.opts.loopEnum,l,c=()=>l??(l=(0,kD.useFunc)(n,CD.default)),d;if(s||i)d=n.let("valid"),t.block$data(d,m);else{if(!Array.isArray(a))throw new Error("ajv implementation error");let f=n.const("vSchema",r);d=(0,Fo.or)(...a.map((g,b)=>u(f,b)))}t.pass(d);function m(){n.assign(d,!1),n.forOf("v",r,f=>n.if((0,Fo._)`${c()}(${e}, ${f})`,()=>n.assign(d,!0).break()))}function u(f,g){let b=a[g];return typeof b=="object"&&b!==null?(0,Fo._)`${c()}(${e}, ${f}[${g}])`:(0,Fo._)`${e} === ${b}`}}};wp.default=DD});var Kv=L(Sp=>{"use strict";Object.defineProperty(Sp,"__esModule",{value:!0});var MD=Pv(),ND=Fv(),ID=jv(),AD=Vv(),TD=$v(),OD=zv(),RD=Hv(),PD=qv(),FD=Gv(),LD=Wv(),BD=[MD.default,ND.default,ID.default,AD.default,TD.default,OD.default,RD.default,PD.default,{keyword:"type",schemaType:["string","array"]},{keyword:"nullable",schemaType:"boolean"},FD.default,LD.default];Sp.default=BD});var Cp=L(Lo=>{"use strict";Object.defineProperty(Lo,"__esModule",{value:!0});Lo.validateAdditionalItems=void 0;var ra=ue(),kp=ke(),jD={message:({params:{len:t}})=>(0,ra.str)`must NOT have more than ${t} items`,params:({params:{len:t}})=>(0,ra._)`{limit: ${t}}`},VD={keyword:"additionalItems",type:"array",schemaType:["boolean","object"],before:"uniqueItems",error:jD,code(t){let{parentSchema:n,it:e}=t,{items:i}=n;if(!Array.isArray(i)){(0,kp.checkStrictMode)(e,'"additionalItems" is ignored when "items" is not an array of schemas');return}Yv(t,i)}};function Yv(t,n){let{gen:e,schema:i,data:a,keyword:r,it:o}=t;o.items=!0;let s=e.const("len",(0,ra._)`${a}.length`);if(i===!1)t.setParams({len:n.length}),t.pass((0,ra._)`${s} <= ${n.length}`);else if(typeof i=="object"&&!(0,kp.alwaysValidSchema)(o,i)){let c=e.var("valid",(0,ra._)`${s} <= ${n.length}`);e.if((0,ra.not)(c),()=>l(c)),t.ok(c)}function l(c){e.forRange("i",n.length,s,d=>{t.subschema({keyword:r,dataProp:d,dataPropType:kp.Type.Num},c),o.allErrors||e.if((0,ra.not)(c),()=>e.break())})}}Lo.validateAdditionalItems=Yv;Lo.default=VD});var Ep=L(Bo=>{"use strict";Object.defineProperty(Bo,"__esModule",{value:!0});Bo.validateTuple=void 0;var Xv=ue(),ec=ke(),$D=tn(),zD={keyword:"items",type:"array",schemaType:["object","array","boolean"],before:"uniqueItems",code(t){let{schema:n,it:e}=t;if(Array.isArray(n))return Zv(t,"additionalItems",n);e.items=!0,!(0,ec.alwaysValidSchema)(e,n)&&t.ok((0,$D.validateArray)(t))}};function Zv(t,n,e=t.schema){let{gen:i,parentSchema:a,data:r,keyword:o,it:s}=t;d(a),s.opts.unevaluated&&e.length&&s.items!==!0&&(s.items=ec.mergeEvaluated.items(i,e.length,s.items));let l=i.name("valid"),c=i.const("len",(0,Xv._)`${r}.length`);e.forEach((m,u)=>{(0,ec.alwaysValidSchema)(s,m)||(i.if((0,Xv._)`${c} > ${u}`,()=>t.subschema({keyword:o,schemaProp:u,dataProp:u},l)),t.ok(l))});function d(m){let{opts:u,errSchemaPath:f}=s,g=e.length,b=g===m.minItems&&(g===m.maxItems||m[n]===!1);if(u.strictTuples&&!b){let _=`"${o}" is ${g}-tuple, but minItems or maxItems/${n} are not specified or different at path "${f}"`;(0,ec.checkStrictMode)(s,_,u.strictTuples)}}}Bo.validateTuple=Zv;Bo.default=zD});var Qv=L(Dp=>{"use strict";Object.defineProperty(Dp,"__esModule",{value:!0});var HD=Ep(),UD={keyword:"prefixItems",type:"array",schemaType:["array"],before:"uniqueItems",code:t=>(0,HD.validateTuple)(t,"items")};Dp.default=UD});var e_=L(Mp=>{"use strict";Object.defineProperty(Mp,"__esModule",{value:!0});var Jv=ue(),qD=ke(),GD=tn(),WD=Cp(),KD={message:({params:{len:t}})=>(0,Jv.str)`must NOT have more than ${t} items`,params:({params:{len:t}})=>(0,Jv._)`{limit: ${t}}`},YD={keyword:"items",type:"array",schemaType:["object","boolean"],before:"uniqueItems",error:KD,code(t){let{schema:n,parentSchema:e,it:i}=t,{prefixItems:a}=e;i.items=!0,!(0,qD.alwaysValidSchema)(i,n)&&(a?(0,WD.validateAdditionalItems)(t,a):t.ok((0,GD.validateArray)(t)))}};Mp.default=YD});var t_=L(Np=>{"use strict";Object.defineProperty(Np,"__esModule",{value:!0});var an=ue(),tc=ke(),XD={message:({params:{min:t,max:n}})=>n===void 0?(0,an.str)`must contain at least ${t} valid item(s)`:(0,an.str)`must contain at least ${t} and no more than ${n} valid item(s)`,params:({params:{min:t,max:n}})=>n===void 0?(0,an._)`{minContains: ${t}}`:(0,an._)`{minContains: ${t}, maxContains: ${n}}`},ZD={keyword:"contains",type:"array",schemaType:["object","boolean"],before:"uniqueItems",trackErrors:!0,error:XD,code(t){let{gen:n,schema:e,parentSchema:i,data:a,it:r}=t,o,s,{minContains:l,maxContains:c}=i;r.opts.next?(o=l===void 0?1:l,s=c):o=1;let d=n.const("len",(0,an._)`${a}.length`);if(t.setParams({min:o,max:s}),s===void 0&&o===0){(0,tc.checkStrictMode)(r,'"minContains" == 0 without "maxContains": "contains" keyword ignored');return}if(s!==void 0&&o>s){(0,tc.checkStrictMode)(r,'"minContains" > "maxContains" is always invalid'),t.fail();return}if((0,tc.alwaysValidSchema)(r,e)){let b=(0,an._)`${d} >= ${o}`;s!==void 0&&(b=(0,an._)`${b} && ${d} <= ${s}`),t.pass(b);return}r.items=!0;let m=n.name("valid");s===void 0&&o===1?f(m,()=>n.if(m,()=>n.break())):o===0?(n.let(m,!0),s!==void 0&&n.if((0,an._)`${a}.length > 0`,u)):(n.let(m,!1),u()),t.result(m,()=>t.reset());function u(){let b=n.name("_valid"),_=n.let("count",0);f(b,()=>n.if(b,()=>g(_)))}function f(b,_){n.forRange("i",0,d,x=>{t.subschema({keyword:"contains",dataProp:x,dataPropType:tc.Type.Num,compositeRule:!0},b),_()})}function g(b){n.code((0,an._)`${b}++`),s===void 0?n.if((0,an._)`${b} >= ${o}`,()=>n.assign(m,!0).break()):(n.if((0,an._)`${b} > ${s}`,()=>n.assign(m,!1).break()),o===1?n.assign(m,!0):n.if((0,an._)`${b} >= ${o}`,()=>n.assign(m,!0)))}}};Np.default=ZD});var a_=L(Rn=>{"use strict";Object.defineProperty(Rn,"__esModule",{value:!0});Rn.validateSchemaDeps=Rn.validatePropertyDeps=Rn.error=void 0;var Ip=ue(),QD=ke(),jo=tn();Rn.error={message:({params:{property:t,depsCount:n,deps:e}})=>{let i=n===1?"property":"properties";return(0,Ip.str)`must have ${i} ${e} when property ${t} is present`},params:({params:{property:t,depsCount:n,deps:e,missingProperty:i}})=>(0,Ip._)`{property: ${t},
    missingProperty: ${i},
    depsCount: ${n},
    deps: ${e}}`};var JD={keyword:"dependencies",type:"object",schemaType:"object",error:Rn.error,code(t){let[n,e]=e1(t);n_(t,n),i_(t,e)}};function e1({schema:t}){let n={},e={};for(let i in t){if(i==="__proto__")continue;let a=Array.isArray(t[i])?n:e;a[i]=t[i]}return[n,e]}function n_(t,n=t.schema){let{gen:e,data:i,it:a}=t;if(Object.keys(n).length===0)return;let r=e.let("missing");for(let o in n){let s=n[o];if(s.length===0)continue;let l=(0,jo.propertyInData)(e,i,o,a.opts.ownProperties);t.setParams({property:o,depsCount:s.length,deps:s.join(", ")}),a.allErrors?e.if(l,()=>{for(let c of s)(0,jo.checkReportMissingProp)(t,c)}):(e.if((0,Ip._)`${l} && (${(0,jo.checkMissingProp)(t,s,r)})`),(0,jo.reportMissingProp)(t,r),e.else())}}Rn.validatePropertyDeps=n_;function i_(t,n=t.schema){let{gen:e,data:i,keyword:a,it:r}=t,o=e.name("valid");for(let s in n)(0,QD.alwaysValidSchema)(r,n[s])||(e.if((0,jo.propertyInData)(e,i,s,r.opts.ownProperties),()=>{let l=t.subschema({keyword:a,schemaProp:s},o);t.mergeValidEvaluated(l,o)},()=>e.var(o,!0)),t.ok(o))}Rn.validateSchemaDeps=i_;Rn.default=JD});var o_=L(Ap=>{"use strict";Object.defineProperty(Ap,"__esModule",{value:!0});var r_=ue(),t1=ke(),n1={message:"property name must be valid",params:({params:t})=>(0,r_._)`{propertyName: ${t.propertyName}}`},i1={keyword:"propertyNames",type:"object",schemaType:["object","boolean"],error:n1,code(t){let{gen:n,schema:e,data:i,it:a}=t;if((0,t1.alwaysValidSchema)(a,e))return;let r=n.name("valid");n.forIn("key",i,o=>{t.setParams({propertyName:o}),t.subschema({keyword:"propertyNames",data:o,dataTypes:["string"],propertyName:o,compositeRule:!0},r),n.if((0,r_.not)(r),()=>{t.error(!0),a.allErrors||n.break()})}),t.ok(r)}};Ap.default=i1});var Op=L(Tp=>{"use strict";Object.defineProperty(Tp,"__esModule",{value:!0});var nc=tn(),vn=ue(),a1=Qn(),ic=ke(),r1={message:"must NOT have additional properties",params:({params:t})=>(0,vn._)`{additionalProperty: ${t.additionalProperty}}`},o1={keyword:"additionalProperties",type:["object"],schemaType:["boolean","object"],allowUndefined:!0,trackErrors:!0,error:r1,code(t){let{gen:n,schema:e,parentSchema:i,data:a,errsCount:r,it:o}=t;if(!r)throw new Error("ajv implementation error");let{allErrors:s,opts:l}=o;if(o.props=!0,l.removeAdditional!=="all"&&(0,ic.alwaysValidSchema)(o,e))return;let c=(0,nc.allSchemaProperties)(i.properties),d=(0,nc.allSchemaProperties)(i.patternProperties);m(),t.ok((0,vn._)`${r} === ${a1.default.errors}`);function m(){n.forIn("key",a,_=>{!c.length&&!d.length?g(_):n.if(u(_),()=>g(_))})}function u(_){let x;if(c.length>8){let w=(0,ic.schemaRefOrVal)(o,i.properties,"properties");x=(0,nc.isOwnProperty)(n,w,_)}else c.length?x=(0,vn.or)(...c.map(w=>(0,vn._)`${_} === ${w}`)):x=vn.nil;return d.length&&(x=(0,vn.or)(x,...d.map(w=>(0,vn._)`${(0,nc.usePattern)(t,w)}.test(${_})`))),(0,vn.not)(x)}function f(_){n.code((0,vn._)`delete ${a}[${_}]`)}function g(_){if(l.removeAdditional==="all"||l.removeAdditional&&e===!1){f(_);return}if(e===!1){t.setParams({additionalProperty:_}),t.error(),s||n.break();return}if(typeof e=="object"&&!(0,ic.alwaysValidSchema)(o,e)){let x=n.name("valid");l.removeAdditional==="failing"?(b(_,x,!1),n.if((0,vn.not)(x),()=>{t.reset(),f(_)})):(b(_,x),s||n.if((0,vn.not)(x),()=>n.break()))}}function b(_,x,w){let C={keyword:"additionalProperties",dataProp:_,dataPropType:ic.Type.Str};w===!1&&Object.assign(C,{compositeRule:!0,createErrors:!1,allErrors:!1}),t.subschema(C,x)}}};Tp.default=o1});var c_=L(Pp=>{"use strict";Object.defineProperty(Pp,"__esModule",{value:!0});var s1=ko(),s_=tn(),Rp=ke(),l_=Op(),l1={keyword:"properties",type:"object",schemaType:"object",code(t){let{gen:n,schema:e,parentSchema:i,data:a,it:r}=t;r.opts.removeAdditional==="all"&&i.additionalProperties===void 0&&l_.default.code(new s1.KeywordCxt(r,l_.default,"additionalProperties"));let o=(0,s_.allSchemaProperties)(e);for(let m of o)r.definedProperties.add(m);r.opts.unevaluated&&o.length&&r.props!==!0&&(r.props=Rp.mergeEvaluated.props(n,(0,Rp.toHash)(o),r.props));let s=o.filter(m=>!(0,Rp.alwaysValidSchema)(r,e[m]));if(s.length===0)return;let l=n.name("valid");for(let m of s)c(m)?d(m):(n.if((0,s_.propertyInData)(n,a,m,r.opts.ownProperties)),d(m),r.allErrors||n.else().var(l,!0),n.endIf()),t.it.definedProperties.add(m),t.ok(l);function c(m){return r.opts.useDefaults&&!r.compositeRule&&e[m].default!==void 0}function d(m){t.subschema({keyword:"properties",schemaProp:m,dataProp:m},l)}}};Pp.default=l1});var u_=L(Fp=>{"use strict";Object.defineProperty(Fp,"__esModule",{value:!0});var d_=tn(),ac=ue(),m_=ke(),p_=ke(),c1={keyword:"patternProperties",type:"object",schemaType:"object",code(t){let{gen:n,schema:e,data:i,parentSchema:a,it:r}=t,{opts:o}=r,s=(0,d_.allSchemaProperties)(e),l=s.filter(b=>(0,m_.alwaysValidSchema)(r,e[b]));if(s.length===0||l.length===s.length&&(!r.opts.unevaluated||r.props===!0))return;let c=o.strictSchema&&!o.allowMatchingProperties&&a.properties,d=n.name("valid");r.props!==!0&&!(r.props instanceof ac.Name)&&(r.props=(0,p_.evaluatedPropsToName)(n,r.props));let{props:m}=r;u();function u(){for(let b of s)c&&f(b),r.allErrors?g(b):(n.var(d,!0),g(b),n.if(d))}function f(b){for(let _ in c)new RegExp(b).test(_)&&(0,m_.checkStrictMode)(r,`property ${_} matches pattern ${b} (use allowMatchingProperties)`)}function g(b){n.forIn("key",i,_=>{n.if((0,ac._)`${(0,d_.usePattern)(t,b)}.test(${_})`,()=>{let x=l.includes(b);x||t.subschema({keyword:"patternProperties",schemaProp:b,dataProp:_,dataPropType:p_.Type.Str},d),r.opts.unevaluated&&m!==!0?n.assign((0,ac._)`${m}[${_}]`,!0):!x&&!r.allErrors&&n.if((0,ac.not)(d),()=>n.break())})})}}};Fp.default=c1});var h_=L(Lp=>{"use strict";Object.defineProperty(Lp,"__esModule",{value:!0});var d1=ke(),m1={keyword:"not",schemaType:["object","boolean"],trackErrors:!0,code(t){let{gen:n,schema:e,it:i}=t;if((0,d1.alwaysValidSchema)(i,e)){t.fail();return}let a=n.name("valid");t.subschema({keyword:"not",compositeRule:!0,createErrors:!1,allErrors:!1},a),t.failResult(a,()=>t.reset(),()=>t.error())},error:{message:"must NOT be valid"}};Lp.default=m1});var f_=L(Bp=>{"use strict";Object.defineProperty(Bp,"__esModule",{value:!0});var p1=tn(),u1={keyword:"anyOf",schemaType:"array",trackErrors:!0,code:p1.validateUnion,error:{message:"must match a schema in anyOf"}};Bp.default=u1});var g_=L(jp=>{"use strict";Object.defineProperty(jp,"__esModule",{value:!0});var rc=ue(),h1=ke(),f1={message:"must match exactly one schema in oneOf",params:({params:t})=>(0,rc._)`{passingSchemas: ${t.passing}}`},g1={keyword:"oneOf",schemaType:"array",trackErrors:!0,error:f1,code(t){let{gen:n,schema:e,parentSchema:i,it:a}=t;if(!Array.isArray(e))throw new Error("ajv implementation error");if(a.opts.discriminator&&i.discriminator)return;let r=e,o=n.let("valid",!1),s=n.let("passing",null),l=n.name("_valid");t.setParams({passing:s}),n.block(c),t.result(o,()=>t.reset(),()=>t.error(!0));function c(){r.forEach((d,m)=>{let u;(0,h1.alwaysValidSchema)(a,d)?n.var(l,!0):u=t.subschema({keyword:"oneOf",schemaProp:m,compositeRule:!0},l),m>0&&n.if((0,rc._)`${l} && ${o}`).assign(o,!1).assign(s,(0,rc._)`[${s}, ${m}]`).else(),n.if(l,()=>{n.assign(o,!0),n.assign(s,m),u&&t.mergeEvaluated(u,rc.Name)})})}}};jp.default=g1});var b_=L(Vp=>{"use strict";Object.defineProperty(Vp,"__esModule",{value:!0});var b1=ke(),v1={keyword:"allOf",schemaType:"array",code(t){let{gen:n,schema:e,it:i}=t;if(!Array.isArray(e))throw new Error("ajv implementation error");let a=n.name("valid");e.forEach((r,o)=>{if((0,b1.alwaysValidSchema)(i,r))return;let s=t.subschema({keyword:"allOf",schemaProp:o},a);t.ok(a),t.mergeEvaluated(s)})}};Vp.default=v1});var y_=L($p=>{"use strict";Object.defineProperty($p,"__esModule",{value:!0});var oc=ue(),__=ke(),_1={message:({params:t})=>(0,oc.str)`must match "${t.ifClause}" schema`,params:({params:t})=>(0,oc._)`{failingKeyword: ${t.ifClause}}`},y1={keyword:"if",schemaType:["object","boolean"],trackErrors:!0,error:_1,code(t){let{gen:n,parentSchema:e,it:i}=t;e.then===void 0&&e.else===void 0&&(0,__.checkStrictMode)(i,'"if" without "then" and "else" is ignored');let a=v_(i,"then"),r=v_(i,"else");if(!a&&!r)return;let o=n.let("valid",!0),s=n.name("_valid");if(l(),t.reset(),a&&r){let d=n.let("ifClause");t.setParams({ifClause:d}),n.if(s,c("then",d),c("else",d))}else a?n.if(s,c("then")):n.if((0,oc.not)(s),c("else"));t.pass(o,()=>t.error(!0));function l(){let d=t.subschema({keyword:"if",compositeRule:!0,createErrors:!1,allErrors:!1},s);t.mergeEvaluated(d)}function c(d,m){return()=>{let u=t.subschema({keyword:d},s);n.assign(o,s),t.mergeValidEvaluated(u,o),m?n.assign(m,(0,oc._)`${d}`):t.setParams({ifClause:d})}}}};function v_(t,n){let e=t.schema[n];return e!==void 0&&!(0,__.alwaysValidSchema)(t,e)}$p.default=y1});var x_=L(zp=>{"use strict";Object.defineProperty(zp,"__esModule",{value:!0});var x1=ke(),w1={keyword:["then","else"],schemaType:["object","boolean"],code({keyword:t,parentSchema:n,it:e}){n.if===void 0&&(0,x1.checkStrictMode)(e,`"${t}" without "if" is ignored`)}};zp.default=w1});var w_=L(Hp=>{"use strict";Object.defineProperty(Hp,"__esModule",{value:!0});var S1=Cp(),k1=Qv(),C1=Ep(),E1=e_(),D1=t_(),M1=a_(),N1=o_(),I1=Op(),A1=c_(),T1=u_(),O1=h_(),R1=f_(),P1=g_(),F1=b_(),L1=y_(),B1=x_();function j1(t=!1){let n=[O1.default,R1.default,P1.default,F1.default,L1.default,B1.default,N1.default,I1.default,M1.default,A1.default,T1.default];return t?n.push(k1.default,E1.default):n.push(S1.default,C1.default),n.push(D1.default),n}Hp.default=j1});var S_=L(Up=>{"use strict";Object.defineProperty(Up,"__esModule",{value:!0});var it=ue(),V1={message:({schemaCode:t})=>(0,it.str)`must match format "${t}"`,params:({schemaCode:t})=>(0,it._)`{format: ${t}}`},$1={keyword:"format",type:["number","string"],schemaType:"string",$data:!0,error:V1,code(t,n){let{gen:e,data:i,$data:a,schema:r,schemaCode:o,it:s}=t,{opts:l,errSchemaPath:c,schemaEnv:d,self:m}=s;if(!l.validateFormats)return;a?u():f();function u(){let g=e.scopeValue("formats",{ref:m.formats,code:l.code.formats}),b=e.const("fDef",(0,it._)`${g}[${o}]`),_=e.let("fType"),x=e.let("format");e.if((0,it._)`typeof ${b} == "object" && !(${b} instanceof RegExp)`,()=>e.assign(_,(0,it._)`${b}.type || "string"`).assign(x,(0,it._)`${b}.validate`),()=>e.assign(_,(0,it._)`"string"`).assign(x,b)),t.fail$data((0,it.or)(w(),C()));function w(){return l.strictSchema===!1?it.nil:(0,it._)`${o} && !${x}`}function C(){let F=d.$async?(0,it._)`(${b}.async ? await ${x}(${i}) : ${x}(${i}))`:(0,it._)`${x}(${i})`,A=(0,it._)`(typeof ${x} == "function" ? ${F} : ${x}.test(${i}))`;return(0,it._)`${x} && ${x} !== true && ${_} === ${n} && !${A}`}}function f(){let g=m.formats[r];if(!g){w();return}if(g===!0)return;let[b,_,x]=C(g);b===n&&t.pass(F());function w(){if(l.strictSchema===!1){m.logger.warn(A());return}throw new Error(A());function A(){return`unknown format "${r}" ignored in schema at path "${c}"`}}function C(A){let Q=A instanceof RegExp?(0,it.regexpCode)(A):l.code.formats?(0,it._)`${l.code.formats}${(0,it.getProperty)(r)}`:void 0,Re=e.scopeValue("formats",{key:r,ref:A,code:Q});return typeof A=="object"&&!(A instanceof RegExp)?[A.type||"string",A.validate,(0,it._)`${Re}.validate`]:["string",A,Re]}function F(){if(typeof g=="object"&&!(g instanceof RegExp)&&g.async){if(!d.$async)throw new Error("async format in sync schema");return(0,it._)`await ${x}(${i})`}return typeof _=="function"?(0,it._)`${x}(${i})`:(0,it._)`${x}.test(${i})`}}}};Up.default=$1});var k_=L(qp=>{"use strict";Object.defineProperty(qp,"__esModule",{value:!0});var z1=S_(),H1=[z1.default];qp.default=H1});var C_=L(Qa=>{"use strict";Object.defineProperty(Qa,"__esModule",{value:!0});Qa.contentVocabulary=Qa.metadataVocabulary=void 0;Qa.metadataVocabulary=["title","description","default","deprecated","readOnly","writeOnly","examples"];Qa.contentVocabulary=["contentMediaType","contentEncoding","contentSchema"]});var D_=L(Gp=>{"use strict";Object.defineProperty(Gp,"__esModule",{value:!0});var U1=Rv(),q1=Kv(),G1=w_(),W1=k_(),E_=C_(),K1=[U1.default,q1.default,(0,G1.default)(),W1.default,E_.metadataVocabulary,E_.contentVocabulary];Gp.default=K1});var N_=L(sc=>{"use strict";Object.defineProperty(sc,"__esModule",{value:!0});sc.DiscrError=void 0;var M_;(function(t){t.Tag="tag",t.Mapping="mapping"})(M_||(sc.DiscrError=M_={}))});var A_=L(Kp=>{"use strict";Object.defineProperty(Kp,"__esModule",{value:!0});var Ja=ue(),Wp=N_(),I_=Vl(),Y1=Co(),X1=ke(),Z1={message:({params:{discrError:t,tagName:n}})=>t===Wp.DiscrError.Tag?`tag "${n}" must be string`:`value of tag "${n}" must be in oneOf`,params:({params:{discrError:t,tag:n,tagName:e}})=>(0,Ja._)`{error: ${t}, tag: ${e}, tagValue: ${n}}`},Q1={keyword:"discriminator",type:"object",schemaType:"object",error:Z1,code(t){let{gen:n,data:e,schema:i,parentSchema:a,it:r}=t,{oneOf:o}=a;if(!r.opts.discriminator)throw new Error("discriminator: requires discriminator option");let s=i.propertyName;if(typeof s!="string")throw new Error("discriminator: requires propertyName");if(i.mapping)throw new Error("discriminator: mapping is not supported");if(!o)throw new Error("discriminator: requires oneOf keyword");let l=n.let("valid",!1),c=n.const("tag",(0,Ja._)`${e}${(0,Ja.getProperty)(s)}`);n.if((0,Ja._)`typeof ${c} == "string"`,()=>d(),()=>t.error(!1,{discrError:Wp.DiscrError.Tag,tag:c,tagName:s})),t.ok(l);function d(){let f=u();n.if(!1);for(let g in f)n.elseIf((0,Ja._)`${c} === ${g}`),n.assign(l,m(f[g]));n.else(),t.error(!1,{discrError:Wp.DiscrError.Mapping,tag:c,tagName:s}),n.endIf()}function m(f){let g=n.name("valid"),b=t.subschema({keyword:"oneOf",schemaProp:f},g);return t.mergeEvaluated(b,Ja.Name),g}function u(){var f;let g={},b=x(a),_=!0;for(let F=0;F<o.length;F++){let A=o[F];if(A?.$ref&&!(0,X1.schemaHasRulesButRef)(A,r.self.RULES)){let Re=A.$ref;if(A=I_.resolveRef.call(r.self,r.schemaEnv.root,r.baseId,Re),A instanceof I_.SchemaEnv&&(A=A.schema),A===void 0)throw new Y1.default(r.opts.uriResolver,r.baseId,Re)}let Q=(f=A?.properties)===null||f===void 0?void 0:f[s];if(typeof Q!="object")throw new Error(`discriminator: oneOf subschemas (or referenced schemas) must have "properties/${s}"`);_=_&&(b||x(A)),w(Q,F)}if(!_)throw new Error(`discriminator: "${s}" must be required`);return g;function x({required:F}){return Array.isArray(F)&&F.includes(s)}function w(F,A){if(F.const)C(F.const,A);else if(F.enum)for(let Q of F.enum)C(Q,A);else throw new Error(`discriminator: "properties/${s}" must have "const" or "enum"`)}function C(F,A){if(typeof F!="string"||F in g)throw new Error(`discriminator: "${s}" values must be unique strings`);g[F]=A}}}};Kp.default=Q1});var T_=L((LV,J1)=>{J1.exports={$schema:"http://json-schema.org/draft-07/schema#",$id:"http://json-schema.org/draft-07/schema#",title:"Core schema meta-schema",definitions:{schemaArray:{type:"array",minItems:1,items:{$ref:"#"}},nonNegativeInteger:{type:"integer",minimum:0},nonNegativeIntegerDefault0:{allOf:[{$ref:"#/definitions/nonNegativeInteger"},{default:0}]},simpleTypes:{enum:["array","boolean","integer","null","number","object","string"]},stringArray:{type:"array",items:{type:"string"},uniqueItems:!0,default:[]}},type:["object","boolean"],properties:{$id:{type:"string",format:"uri-reference"},$schema:{type:"string",format:"uri"},$ref:{type:"string",format:"uri-reference"},$comment:{type:"string"},title:{type:"string"},description:{type:"string"},default:!0,readOnly:{type:"boolean",default:!1},examples:{type:"array",items:!0},multipleOf:{type:"number",exclusiveMinimum:0},maximum:{type:"number"},exclusiveMaximum:{type:"number"},minimum:{type:"number"},exclusiveMinimum:{type:"number"},maxLength:{$ref:"#/definitions/nonNegativeInteger"},minLength:{$ref:"#/definitions/nonNegativeIntegerDefault0"},pattern:{type:"string",format:"regex"},additionalItems:{$ref:"#"},items:{anyOf:[{$ref:"#"},{$ref:"#/definitions/schemaArray"}],default:!0},maxItems:{$ref:"#/definitions/nonNegativeInteger"},minItems:{$ref:"#/definitions/nonNegativeIntegerDefault0"},uniqueItems:{type:"boolean",default:!1},contains:{$ref:"#"},maxProperties:{$ref:"#/definitions/nonNegativeInteger"},minProperties:{$ref:"#/definitions/nonNegativeIntegerDefault0"},required:{$ref:"#/definitions/stringArray"},additionalProperties:{$ref:"#"},definitions:{type:"object",additionalProperties:{$ref:"#"},default:{}},properties:{type:"object",additionalProperties:{$ref:"#"},default:{}},patternProperties:{type:"object",additionalProperties:{$ref:"#"},propertyNames:{format:"regex"},default:{}},dependencies:{type:"object",additionalProperties:{anyOf:[{$ref:"#"},{$ref:"#/definitions/stringArray"}]}},propertyNames:{$ref:"#"},const:!0,enum:{type:"array",items:!0,minItems:1,uniqueItems:!0},type:{anyOf:[{$ref:"#/definitions/simpleTypes"},{type:"array",items:{$ref:"#/definitions/simpleTypes"},minItems:1,uniqueItems:!0}]},format:{type:"string"},contentMediaType:{type:"string"},contentEncoding:{type:"string"},if:{$ref:"#"},then:{$ref:"#"},else:{$ref:"#"},allOf:{$ref:"#/definitions/schemaArray"},anyOf:{$ref:"#/definitions/schemaArray"},oneOf:{$ref:"#/definitions/schemaArray"},not:{$ref:"#"}},default:!0}});var R_=L((ze,Yp)=>{"use strict";Object.defineProperty(ze,"__esModule",{value:!0});ze.MissingRefError=ze.ValidationError=ze.CodeGen=ze.Name=ze.nil=ze.stringify=ze.str=ze._=ze.KeywordCxt=ze.Ajv=void 0;var eM=Mv(),tM=D_(),nM=A_(),O_=T_(),iM=["/properties"],lc="http://json-schema.org/draft-07/schema",er=class extends eM.default{_addVocabularies(){super._addVocabularies(),tM.default.forEach(n=>this.addVocabulary(n)),this.opts.discriminator&&this.addKeyword(nM.default)}_addDefaultMetaSchema(){if(super._addDefaultMetaSchema(),!this.opts.meta)return;let n=this.opts.$data?this.$dataMetaSchema(O_,iM):O_;this.addMetaSchema(n,lc,!1),this.refs["http://json-schema.org/schema"]=lc}defaultMeta(){return this.opts.defaultMeta=super.defaultMeta()||(this.getSchema(lc)?lc:void 0)}};ze.Ajv=er;Yp.exports=ze=er;Yp.exports.Ajv=er;Object.defineProperty(ze,"__esModule",{value:!0});ze.default=er;var aM=ko();Object.defineProperty(ze,"KeywordCxt",{enumerable:!0,get:function(){return aM.KeywordCxt}});var tr=ue();Object.defineProperty(ze,"_",{enumerable:!0,get:function(){return tr._}});Object.defineProperty(ze,"str",{enumerable:!0,get:function(){return tr.str}});Object.defineProperty(ze,"stringify",{enumerable:!0,get:function(){return tr.stringify}});Object.defineProperty(ze,"nil",{enumerable:!0,get:function(){return tr.nil}});Object.defineProperty(ze,"Name",{enumerable:!0,get:function(){return tr.Name}});Object.defineProperty(ze,"CodeGen",{enumerable:!0,get:function(){return tr.CodeGen}});var rM=Bl();Object.defineProperty(ze,"ValidationError",{enumerable:!0,get:function(){return rM.default}});var oM=Co();Object.defineProperty(ze,"MissingRefError",{enumerable:!0,get:function(){return oM.default}})});var Eh=null;function zn(){return Eh}function Qc(t){Eh??=t}var Fr=class{},wa=(()=>{class t{historyGo(e){throw new Error("")}static \u0275fac=function(i){return new(i||t)};static \u0275prov=te({token:t,factory:()=>p(Dh),providedIn:"platform"})}return t})();var Dh=(()=>{class t extends wa{_location;_history;_doc=p(Y);constructor(){super(),this._location=window.location,this._history=window.history}getBaseHrefFromDOM(){return zn().getBaseHref(this._doc)}onPopState(e){let i=zn().getGlobalEventTarget(this._doc,"window");return i.addEventListener("popstate",e,!1),()=>i.removeEventListener("popstate",e)}onHashChange(e){let i=zn().getGlobalEventTarget(this._doc,"window");return i.addEventListener("hashchange",e,!1),()=>i.removeEventListener("hashchange",e)}get href(){return this._location.href}get protocol(){return this._location.protocol}get hostname(){return this._location.hostname}get port(){return this._location.port}get pathname(){return this._location.pathname}get search(){return this._location.search}get hash(){return this._location.hash}set pathname(e){this._location.pathname=e}pushState(e,i,a){this._history.pushState(e,i,a)}replaceState(e,i,a){this._history.replaceState(e,i,a)}forward(){this._history.forward()}back(){this._history.back()}historyGo(e=0){this._history.go(e)}getState(){return this._history.state}static \u0275fac=function(i){return new(i||t)};static \u0275prov=te({token:t,factory:()=>new t,providedIn:"platform"})}return t})();function Ih(t,n){return t?n?t.endsWith("/")?n.startsWith("/")?t+n.slice(1):t+n:n.startsWith("/")?t+n:`${t}/${n}`:t:n}function Mh(t){let n=t.search(/#|\?|$/);return t[n-1]==="/"?t.slice(0,n-1)+t.slice(n):t}function ui(t){return t&&t[0]!=="?"?`?${t}`:t}var Ss=(()=>{class t{historyGo(e){throw new Error("")}static \u0275fac=function(i){return new(i||t)};static \u0275prov=te({token:t,factory:()=>p(Uy),providedIn:"root"})}return t})(),Hy=new I(""),Uy=(()=>{class t extends Ss{_platformLocation;_baseHref;_removeListenerFns=[];constructor(e,i){super(),this._platformLocation=e,this._baseHref=i??this._platformLocation.getBaseHrefFromDOM()??p(Y).location?.origin??""}ngOnDestroy(){for(;this._removeListenerFns.length;)this._removeListenerFns.pop()()}onPopState(e){this._removeListenerFns.push(this._platformLocation.onPopState(e),this._platformLocation.onHashChange(e))}getBaseHref(){return this._baseHref}prepareExternalUrl(e){return Ih(this._baseHref,e)}path(e=!1){let i=this._platformLocation.pathname+ui(this._platformLocation.search),a=this._platformLocation.hash;return a&&e?`${i}${a}`:i}pushState(e,i,a,r){let o=this.prepareExternalUrl(a+ui(r));this._platformLocation.pushState(e,i,o)}replaceState(e,i,a,r){let o=this.prepareExternalUrl(a+ui(r));this._platformLocation.replaceState(e,i,o)}forward(){this._platformLocation.forward()}back(){this._platformLocation.back()}getState(){return this._platformLocation.getState()}historyGo(e=0){this._platformLocation.historyGo?.(e)}static \u0275fac=function(i){return new(i||t)(B(wa),B(Hy,8))};static \u0275prov=te({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var Sa=(()=>{class t{_subject=new z;_basePath;_locationStrategy;_urlChangeListeners=[];_urlChangeSubscription=null;constructor(e){this._locationStrategy=e;let i=this._locationStrategy.getBaseHref();this._basePath=Wy(Mh(Nh(i))),this._locationStrategy.onPopState(a=>{let r={url:this.path(!0),pop:!0,state:a.state,type:a.type};a.hasUAVisualTransition&&(r.hasUAVisualTransition=!0),this._subject.next(r)})}ngOnDestroy(){this._urlChangeSubscription?.unsubscribe(),this._urlChangeListeners=[]}path(e=!1){return this.normalize(this._locationStrategy.path(e))}getState(){return this._locationStrategy.getState()}isCurrentPathEqualTo(e,i=""){return this.path()==this.normalize(e+ui(i))}normalize(e){return t.stripTrailingSlash(Gy(this._basePath,Nh(e)))}prepareExternalUrl(e){return e&&e[0]!=="/"&&(e="/"+e),this._locationStrategy.prepareExternalUrl(e)}go(e,i="",a=null){this._locationStrategy.pushState(a,"",e,i),this._notifyUrlChangeListeners(this.prepareExternalUrl(e+ui(i)),a)}replaceState(e,i="",a=null){this._locationStrategy.replaceState(a,"",e,i),this._notifyUrlChangeListeners(this.prepareExternalUrl(e+ui(i)),a)}forward(){this._locationStrategy.forward()}back(){this._locationStrategy.back()}historyGo(e=0){this._locationStrategy.historyGo?.(e)}onUrlChange(e){return this._urlChangeListeners.push(e),this._urlChangeSubscription??=this.subscribe(i=>{this._notifyUrlChangeListeners(i.url,i.state)}),()=>{let i=this._urlChangeListeners.indexOf(e);this._urlChangeListeners.splice(i,1),this._urlChangeListeners.length===0&&(this._urlChangeSubscription?.unsubscribe(),this._urlChangeSubscription=null)}}_notifyUrlChangeListeners(e="",i){this._urlChangeListeners.forEach(a=>a(e,i))}subscribe(e,i,a){return this._subject.subscribe({next:e,error:i??void 0,complete:a??void 0})}static normalizeQueryParams=ui;static joinWithSlash=Ih;static stripTrailingSlash=Mh;static \u0275fac=function(i){return new(i||t)(B(Ss))};static \u0275prov=te({token:t,factory:()=>qy(),providedIn:"root"})}return t})();function qy(){return new Sa(B(Ss))}function Gy(t,n){if(!t||!n.startsWith(t))return n;let e=n.substring(t.length);return e===""||["/",";","?","#"].includes(e[0])?e:n}function Nh(t){return t.replace(/\/index\.html$/,"")}function Wy(t){if(new RegExp("^(https?:)?//").test(t)){let[,e]=t.split(/\/\/[^\/]+/);return e}return t}var Jc=/\s+/,Ah=[],Lr=(()=>{class t{_ngEl;_renderer;initialClasses=Ah;rawClass;stateMap=new Map;constructor(e,i){this._ngEl=e,this._renderer=i}set klass(e){this.initialClasses=e!=null?e.trim().split(Jc):Ah}set ngClass(e){this.rawClass=typeof e=="string"?e.trim().split(Jc):e}ngDoCheck(){for(let i of this.initialClasses)this._updateState(i,!0);let e=this.rawClass;if(Array.isArray(e)||e instanceof Set)for(let i of e)this._updateState(i,!0);else if(e!=null)for(let i of Object.keys(e))this._updateState(i,!!e[i]);this._applyStateDiff()}_updateState(e,i){let a=this.stateMap.get(e);a!==void 0?(a.enabled!==i&&(a.changed=!0,a.enabled=i),a.touched=!0):this.stateMap.set(e,{enabled:i,changed:!0,touched:!0})}_applyStateDiff(){for(let e of this.stateMap){let i=e[0],a=e[1];a.changed?(this._toggleClass(i,a.enabled),a.changed=!1):a.touched||(a.enabled&&this._toggleClass(i,!1),this.stateMap.delete(i)),a.touched=!1}}_toggleClass(e,i){e=e.trim(),e.length>0&&e.split(Jc).forEach(a=>{i?this._renderer.addClass(this._ngEl.nativeElement,a):this._renderer.removeClass(this._ngEl.nativeElement,a)})}static \u0275fac=function(i){return new(i||t)(Oe(X),Oe(ct))};static \u0275dir=q({type:t,selectors:[["","ngClass",""]],inputs:{klass:[0,"class","klass"],ngClass:"ngClass"}})}return t})();var Br=(()=>{class t{_viewContainerRef;_viewRef=null;ngTemplateOutletContext=null;ngTemplateOutlet=null;ngTemplateOutletInjector=null;injector=p(re);constructor(e){this._viewContainerRef=e}ngOnChanges(e){if(this._shouldRecreateView(e)){let i=this._viewContainerRef;if(this._viewRef&&i.remove(i.indexOf(this._viewRef)),!this.ngTemplateOutlet){this._viewRef=null;return}let a=this._createContextForwardProxy();this._viewRef=i.createEmbeddedView(this.ngTemplateOutlet,a,{injector:this._getInjector()})}}_getInjector(){return this.ngTemplateOutletInjector==="outlet"?this.injector:this.ngTemplateOutletInjector??void 0}_shouldRecreateView(e){return!!e.ngTemplateOutlet||!!e.ngTemplateOutletInjector}_createContextForwardProxy(){return new Proxy({},{set:(e,i,a)=>this.ngTemplateOutletContext?Reflect.set(this.ngTemplateOutletContext,i,a):!1,get:(e,i,a)=>{if(this.ngTemplateOutletContext)return Reflect.get(this.ngTemplateOutletContext,i,a)}})}static \u0275fac=function(i){return new(i||t)(Oe(wn))};static \u0275dir=q({type:t,selectors:[["","ngTemplateOutlet",""]],inputs:{ngTemplateOutletContext:"ngTemplateOutletContext",ngTemplateOutlet:"ngTemplateOutlet",ngTemplateOutletInjector:"ngTemplateOutletInjector"},features:[pt]})}return t})();function Xy(t,n){return{key:t,value:n}}var jr=(()=>{class t{differs;constructor(e){this.differs=e}differ;keyValues=[];compareFn=Th;transform(e,i=Th){if(!e||!(e instanceof Map)&&typeof e!="object")return null;this.differ??=this.differs.find(e).create();let a=this.differ.diff(e),r=i!==this.compareFn;return a&&(this.keyValues=[],a.forEachItem(o=>{this.keyValues.push(Xy(o.key,o.currentValue))})),(a||r)&&(i&&this.keyValues.sort(i),this.compareFn=i),this.keyValues}static \u0275fac=function(i){return new(i||t)(Oe(Sh,16))};static \u0275pipe=qc({name:"keyvalue",type:t,pure:!1})}return t})();function Th(t,n){let e=t.key,i=n.key;if(e===i)return 0;if(e==null)return 1;if(i==null)return-1;if(typeof e=="string"&&typeof i=="string")return e<i?-1:1;if(typeof e=="number"&&typeof i=="number")return e-i;if(typeof e=="boolean"&&typeof i=="boolean")return e<i?-1:1;let a=String(e),r=String(i);return a==r?0:a<r?-1:1}var Sn=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({})}return t})();function Vr(t,n){n=encodeURIComponent(n);for(let e of t.split(";")){let i=e.indexOf("="),[a,r]=i==-1?[e,""]:[e.slice(0,i),e.slice(i+1)];if(a.trim()!==n)continue;let o=r;try{o=decodeURIComponent(r)}catch{}return o.length>1&&o[0]==='"'&&o[o.length-1]==='"'&&(o=o.slice(1,-1)),o}return null}var Zy=(()=>{class t{build(){return new XMLHttpRequest}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),Oi=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275prov=te({token:t,factory:function(i){let a=null;return i?a=new(i||t):a=B(Zy),a},providedIn:"root"})}return t})();var ed="browser";function hi(t){return t===ed}function Oh(t){return t.replace(/\\/g,"\\\\").replace(/[\n\r\f\0]/g,"").replace(/"/g,'\\"')}var Fh=t=>t.src,Jy=new I("",{factory:()=>Fh});var Rh=/^((\s*\d+w\s*(,|$)){1,})$/;var e0=[1,2],t0=640;var n0=1920,i0=1080;var Lh=(()=>{class t{imageLoader=p(Jy);config=a0(p(Uc));renderer=p(ct);imgElement=p(X).nativeElement;injector=p(re);destroyRef=p(jn);lcpObserver;_renderedSrc=null;ngSrc;ngSrcset;sizes;width;height;decoding;loading;priority=!1;loaderParams;disableOptimizedSrcset=!1;fill=!1;placeholder;placeholderConfig;src;srcset;constructor(){this.destroyRef.onDestroy(()=>{this.renderer.removeAttribute(this.imgElement,"loading")})}ngOnInit(){hs("NgOptimizedImage"),this.placeholder&&this.removePlaceholderOnLoad(this.imgElement),this.setHostAttributes()}setHostAttributes(){this.fill?this.sizes||="100vw":(this.setHostAttribute("width",this.width.toString()),this.setHostAttribute("height",this.height.toString())),this.setHostAttribute("loading",this.getLoadingBehavior()),this.setHostAttribute("fetchpriority",this.getFetchPriority()),this.setHostAttribute("decoding",this.getDecoding()),this.setHostAttribute("ng-img","true");let e=this.updateSrcAndSrcset();this.sizes?this.getLoadingBehavior()==="lazy"?this.setHostAttribute("sizes","auto, "+this.sizes):this.setHostAttribute("sizes",this.sizes):this.ngSrcset&&Rh.test(this.ngSrcset)&&this.getLoadingBehavior()==="lazy"&&this.setHostAttribute("sizes","auto, 100vw")}ngOnChanges(e){if(e.ngSrc&&!e.ngSrc.isFirstChange()){let i=this._renderedSrc;this.updateSrcAndSrcset(!0)}}getAspectRatio(){return this.width&&this.height&&this.height!==0?this.width/this.height:null}callImageLoader(e){let i=e;this.loaderParams&&(i.loaderParams=this.loaderParams);let a=this.getAspectRatio();return a!==null&&i.width&&(i.height=Math.round(i.width/a)),this.imageLoader(i)}getLoadingBehavior(){return!this.priority&&this.loading!==void 0?this.loading:this.priority?"eager":"lazy"}getFetchPriority(){return this.priority?"high":"auto"}getDecoding(){return this.priority?"sync":this.decoding??"auto"}getRewrittenSrc(){if(!this._renderedSrc){let e={src:this.ngSrc};this._renderedSrc=this.callImageLoader(e)}return this._renderedSrc}getRewrittenSrcset(){let e=Rh.test(this.ngSrcset);return this.ngSrcset.split(",").filter(a=>a!=="").map(a=>{a=a.trim();let r=e?parseFloat(a):parseFloat(a)*this.width;return`${this.callImageLoader({src:this.ngSrc,width:r})} ${a}`}).join(", ")}getAutomaticSrcset(){return this.sizes?this.getResponsiveSrcset():this.getFixedSrcset()}getResponsiveSrcset(){let{breakpoints:e}=this.config,i=e;return this.sizes?.trim()==="100vw"&&(i=e.filter(r=>r>=t0)),i.map(r=>`${this.callImageLoader({src:this.ngSrc,width:r})} ${r}w`).join(", ")}updateSrcAndSrcset(e=!1){e&&(this._renderedSrc=null);let i=this.getRewrittenSrc();this.setHostAttribute("src",i);let a;return this.ngSrcset?a=this.getRewrittenSrcset():this.shouldGenerateAutomaticSrcset()&&(a=this.getAutomaticSrcset()),a?this.setHostAttribute("srcset",a):this.disableOptimizedSrcset&&this.srcset&&this.setHostAttribute("srcset",this.srcset),a}getFixedSrcset(){return e0.map(i=>`${this.callImageLoader({src:this.ngSrc,width:this.width*i})} ${i}x`).join(", ")}shouldGenerateAutomaticSrcset(){let e=!1;return this.sizes||(e=this.width>n0||this.height>i0),!this.disableOptimizedSrcset&&!this.srcset&&this.imageLoader!==Fh&&!e}generatePlaceholder(e){let{placeholderResolution:i}=this.config;return e===!0?`url("${Oh(this.callImageLoader({src:this.ngSrc,width:i,isPlaceholder:!0}))}")`:typeof e=="string"?`url("${Oh(e)}")`:null}shouldBlurPlaceholder(e){return!e||!Object.hasOwn(e,"blur")?!0:!!e.blur}removePlaceholderOnLoad(e){let i=()=>{let o=this.injector.get(We);a(),r(),this.placeholder=!1,o.markForCheck()},a=this.renderer.listen(e,"load",i),r=this.renderer.listen(e,"error",i);this.destroyRef.onDestroy(()=>{a(),r()}),r0(e,i)}setHostAttribute(e,i){this.renderer.setAttribute(this.imgElement,e,i)}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["img","ngSrc",""]],hostVars:18,hostBindings:function(i,a){i&2&&$n("position",a.fill?"absolute":null)("width",a.fill?"100%":null)("height",a.fill?"100%":null)("inset",a.fill?"0":null)("background-size",a.placeholder?"cover":null)("background-position",a.placeholder?"50% 50%":null)("background-repeat",a.placeholder?"no-repeat":null)("background-image",a.placeholder?a.generatePlaceholder(a.placeholder):null)("filter",a.placeholder&&a.shouldBlurPlaceholder(a.placeholderConfig)?"blur(15px)":null)},inputs:{ngSrc:[2,"ngSrc","ngSrc",o0],ngSrcset:"ngSrcset",sizes:"sizes",width:[2,"width","width",pi],height:[2,"height","height",pi],decoding:"decoding",loading:"loading",priority:[2,"priority","priority",ve],loaderParams:"loaderParams",disableOptimizedSrcset:[2,"disableOptimizedSrcset","disableOptimizedSrcset",ve],fill:[2,"fill","fill",ve],placeholder:[2,"placeholder","placeholder",s0],placeholderConfig:"placeholderConfig",src:"src",srcset:"srcset"},features:[pt]})}return t})();function a0(t){let n={};return t.breakpoints&&(n.breakpoints=t.breakpoints.sort((e,i)=>e-i)),Object.assign({},ah,t,n)}function r0(t,n){t.complete&&t.naturalWidth&&n()}function o0(t){return typeof t=="string"?t:si(t)}function s0(t){return typeof t=="string"&&t!=="true"&&t!=="false"&&t!==""?t:ve(t)}var zr=class{_doc;constructor(n){this._doc=n}manager},Cs=(()=>{class t extends zr{constructor(e){super(e)}supports(e){return!0}addEventListener(e,i,a,r){return e.addEventListener(i,a,r),()=>this.removeEventListener(e,i,a,r)}removeEventListener(e,i,a,r){return e.removeEventListener(i,a,r)}static \u0275fac=function(i){return new(i||t)(B(Y))};static \u0275prov=te({token:t,factory:t.\u0275fac})}return t})(),Ms=new I(""),ad=(()=>{class t{_zone;_plugins;_eventNameToPlugin=new Map;constructor(e,i){this._zone=i,e.forEach(o=>{o.manager=this});let a=e.filter(o=>!(o instanceof Cs));this._plugins=a.slice().reverse();let r=e.find(o=>o instanceof Cs);r&&this._plugins.push(r)}addEventListener(e,i,a,r){return this._findPluginFor(i).addEventListener(e,i,a,r)}getZone(){return this._zone}_findPluginFor(e){let i=this._eventNameToPlugin.get(e);if(i)return i;if(i=this._plugins.find(r=>r.supports(e)),!i)throw new Pe(-5101,!1);return this._eventNameToPlugin.set(e,i),i}static \u0275fac=function(i){return new(i||t)(B(Ms),B(K))};static \u0275prov=te({token:t,factory:t.\u0275fac})}return t})(),td="ng-app-id";function Bh(t){for(let n of t)n.remove()}function jh(t,n){let e=n.createElement("style");return e.textContent=t,e}function l0(t,n,e,i){let a=t.head?.querySelectorAll(`style[${td}="${n}"],link[${td}="${n}"]`);if(!a||a.length===0)return!1;for(let r of a)r.removeAttribute(td),r instanceof HTMLLinkElement?i.set(r.href.slice(r.href.lastIndexOf("/")+1),{usage:0,elements:[r]}):r.textContent&&e.set(r.textContent,{usage:0,elements:[r]});return!0}function id(t,n){let e=n.createElement("link");return e.setAttribute("rel","stylesheet"),e.setAttribute("href",t),e}var rd=(()=>{class t{doc;appId;nonce;inline=new Map;external=new Map;hosts=new Set;constructor(e,i,a,r={}){this.doc=e,this.appId=i,this.nonce=a,l0(e,i,this.inline,this.external)&&this.hosts.add(e.head)}addStyles(e,i){for(let a of e)this.addUsage(a,this.inline,jh);i?.forEach(a=>this.addUsage(a,this.external,id))}removeStyles(e,i){for(let a of e)this.removeUsage(a,this.inline);i?.forEach(a=>this.removeUsage(a,this.external))}addUsage(e,i,a){let r=i.get(e);r?r.usage++:i.set(e,{usage:1,elements:[...this.hosts].map(o=>this.addElement(o,a(e,this.doc)))})}removeUsage(e,i){let a=i.get(e);a&&(a.usage--,a.usage<=0&&(Bh(a.elements),i.delete(e)))}ngOnDestroy(){for(let[,{elements:e}]of[...this.inline,...this.external])Bh(e);this.hosts.clear()}addHost(e){if(!this.hosts.has(e)){this.hosts.add(e);for(let[i,{elements:a}]of this.inline)a.push(this.addElement(e,jh(i,this.doc)));for(let[i,{elements:a}]of this.external)a.push(this.addElement(e,id(i,this.doc)))}}removeHost(e){this.hosts.delete(e);for(let i of[...this.inline.values(),...this.external.values()]){let a=[];for(let r of i.elements)r.parentNode===e?r.remove():a.push(r);i.elements=a}}addElement(e,i){return this.nonce&&i.setAttribute("nonce",this.nonce),e.appendChild(i)}static \u0275fac=function(i){return new(i||t)(B(Y),B(Dr),B(ga,8),B(ri))};static \u0275prov=te({token:t,factory:t.\u0275fac})}return t})(),nd={svg:"http://www.w3.org/2000/svg",xhtml:"http://www.w3.org/1999/xhtml",xlink:"http://www.w3.org/1999/xlink",xml:"http://www.w3.org/XML/1998/namespace",xmlns:"http://www.w3.org/2000/xmlns/",math:"http://www.w3.org/1998/Math/MathML"},od=/%COMP%/g;var $h="%COMP%",c0=`_nghost-${$h}`,d0=`_ngcontent-${$h}`,m0=!0,p0=new I("",{factory:()=>m0}),u0=new I("");function h0(t){return d0.replace(od,t)}function f0(t){return c0.replace(od,t)}function zh(t,n){return n.map(e=>e.replace(od,t))}var Ur=(()=>{class t{eventManager;sharedStylesHost;appId;removeStylesOnCompDestroy;doc;ngZone;nonce;tracingService;rendererByCompId=new Map;defaultRenderer;cssVarNamespace;constructor(e,i,a,r,o,s,l=null,c=null,d=null){this.eventManager=e,this.sharedStylesHost=i,this.appId=a,this.removeStylesOnCompDestroy=r,this.doc=o,this.ngZone=s,this.nonce=l,this.tracingService=c,this.cssVarNamespace=d??"",this.defaultRenderer=new ka(e,o,s,this.tracingService,this.cssVarNamespace)}createRenderer(e,i){if(!e||!i)return this.defaultRenderer;let a=this.getOrCreateRenderer(e,i);return a instanceof Ds?a.applyToHost(e):a instanceof Hr&&a.applyStyles(),a}getOrCreateRenderer(e,i){let a=this.rendererByCompId,r=a.get(i.id);if(!r){let o=this.doc,s=this.ngZone,l=this.eventManager,c=this.sharedStylesHost,d=this.removeStylesOnCompDestroy,m=this.tracingService;switch(i.encapsulation){case us.Emulated:r=new Ds(l,c,i,this.appId,d,o,s,m,this.cssVarNamespace);break;case us.ShadowDom:return new Es(l,e,i,o,s,this.nonce,m,this.cssVarNamespace,c);case us.ExperimentalIsolatedShadowDom:return new Es(l,e,i,o,s,this.nonce,m,this.cssVarNamespace);default:r=new Hr(l,c,i,d,o,s,m,this.cssVarNamespace);break}a.set(i.id,r)}return r}ngOnDestroy(){this.rendererByCompId.clear()}componentReplaced(e){this.rendererByCompId.delete(e)}static \u0275fac=function(i){return new(i||t)(B(ad),B(Nr),B(Dr),B(p0),B(Y),B(K),B(ga),B(Mr,8),B(u0,8))};static \u0275prov=te({token:t,factory:t.\u0275fac})}return t})(),ka=class{eventManager;doc;ngZone;tracingService;cssVarNamespace;data=Object.create(null);throwOnSyntheticProps=!0;constructor(n,e,i,a,r=""){this.eventManager=n,this.doc=e,this.ngZone=i,this.tracingService=a,this.cssVarNamespace=r}destroy(){}destroyNode=null;createElement(n,e){return e?this.doc.createElementNS(nd[e]||e,n):this.doc.createElement(n)}createComment(n){return this.doc.createComment(n)}createText(n){return this.doc.createTextNode(n)}appendChild(n,e){(Vh(n)?n.content:n).appendChild(e)}insertBefore(n,e,i){if(n){let a=Vh(n)?n.content:n;if(i!=null&&i.parentNode!==a)throw new Pe(-5106,!1);a.insertBefore(e,i)}}removeChild(n,e){e.remove()}selectRootElement(n,e){let i=typeof n=="string"?this.doc.querySelector(n):n;if(!i)throw new Pe(-5104,!1);return e||(i.textContent=""),i}parentNode(n){return n.parentNode}nextSibling(n){return n.nextSibling}setAttribute(n,e,i,a){if(a){e=a+":"+e;let r=nd[a];r?n.setAttributeNS(r,e,i):n.setAttribute(e,i)}else n.setAttribute(e,i)}removeAttribute(n,e,i){if(i){let a=nd[i];a?n.removeAttributeNS(a,e):n.removeAttribute(`${i}:${e}`)}else n.removeAttribute(e)}addClass(n,e){n.classList.add(e)}removeClass(n,e){n.classList.remove(e)}setStyle(n,e,i,a){let r=e.startsWith("--");r&&(e=e.replace("%NS%",this.cssVarNamespace)),r||a&(_a.DashCase|_a.Important)?n.style.setProperty(e,i,a&_a.Important?"important":""):n.style[e]=i}removeStyle(n,e,i){let a=e.startsWith("--");a&&(e=e.replace("%NS%",this.cssVarNamespace)),a||i&_a.DashCase?n.style.removeProperty(e):n.style[e]=""}setProperty(n,e,i){n!=null&&(n[e]=i)}setValue(n,e){n.nodeValue=e}listen(n,e,i,a){if(typeof n=="string"&&(n=zn().getGlobalEventTarget(this.doc,n),!n))throw new Pe(-5102,!1);let r=this.decoratePreventDefault(i);return this.tracingService?.wrapEventListener&&(r=this.tracingService.wrapEventListener(n,e,r)),this.eventManager.addEventListener(n,e,r,a)}decoratePreventDefault(n){return e=>{if(e==="__ngUnwrap__")return n;n(e)===!1&&e.preventDefault()}}};function Hh(t){t instanceof ka&&(t.throwOnSyntheticProps=!1)}function Vh(t){return t.tagName==="TEMPLATE"&&t.content!==void 0}var Es=class extends ka{hostEl;sharedStylesHost;shadowRoot;constructor(n,e,i,a,r,o,s,l,c){super(n,a,r,s,l),this.hostEl=e,this.sharedStylesHost=c,this.shadowRoot=e.attachShadow({mode:"open"}),this.sharedStylesHost&&this.sharedStylesHost.addHost(this.shadowRoot);let d=i.styles;d=zh(i.id,d).map(u=>u.replace(/%NS%/g,l));for(let u of d){let f=document.createElement("style");o&&f.setAttribute("nonce",o),f.textContent=u,this.shadowRoot.appendChild(f)}let m=i.getExternalStyles?.();if(m)for(let u of m){let f=id(u,a);o&&f.setAttribute("nonce",o),this.shadowRoot.appendChild(f)}}nodeOrShadowRoot(n){return n===this.hostEl?this.shadowRoot:n}appendChild(n,e){return super.appendChild(this.nodeOrShadowRoot(n),e)}insertBefore(n,e,i){return super.insertBefore(this.nodeOrShadowRoot(n),e,i)}removeChild(n,e){return super.removeChild(null,e)}parentNode(n){return this.nodeOrShadowRoot(super.parentNode(this.nodeOrShadowRoot(n)))}destroy(){this.sharedStylesHost&&this.sharedStylesHost.removeHost(this.shadowRoot)}},Hr=class extends ka{sharedStylesHost;removeStylesOnCompDestroy;styles;styleUrls;constructor(n,e,i,a,r,o,s,l,c){super(n,r,o,s,l),this.sharedStylesHost=e,this.removeStylesOnCompDestroy=a;let d=i.styles,m=c?zh(c,d):d;this.styles=m.map(u=>u.replace(/%NS%/g,l)),this.styleUrls=i.getExternalStyles?.(c)}applyStyles(){this.sharedStylesHost.addStyles(this.styles,this.styleUrls)}destroy(){this.removeStylesOnCompDestroy&&hh.size===0&&this.sharedStylesHost.removeStyles(this.styles,this.styleUrls)}},Ds=class extends Hr{contentAttr;hostAttr;constructor(n,e,i,a,r,o,s,l,c){let d=a+"-"+i.id;super(n,e,i,r,o,s,l,c,d),this.contentAttr=h0(d),this.hostAttr=f0(d)}applyToHost(n){this.applyStyles(),this.setAttribute(n,this.hostAttr,"")}createElement(n,e){let i=super.createElement(n,e);return super.setAttribute(i,this.contentAttr,""),i}};var Ns=class t extends Fr{supportsDOMEvents=!0;static makeCurrent(){Qc(new t)}onAndCancel(n,e,i,a){return n.addEventListener(e,i,a),()=>{n.removeEventListener(e,i,a)}}dispatchEvent(n,e){n.dispatchEvent(e)}remove(n){n.remove()}createElement(n,e){return e=e||this.getDefaultDocument(),e.createElement(n)}createHtmlDocument(){return document.implementation.createHTMLDocument("fakeTitle")}getDefaultDocument(){return document}isElementNode(n){return n.nodeType===Node.ELEMENT_NODE}isShadowRoot(n){return n instanceof DocumentFragment}getGlobalEventTarget(n,e){return e==="window"?window:e==="document"?n:e==="body"?n.body:null}getBaseHref(n){let e=b0();return e==null?null:v0(e)}resetBaseElement(){qr=null}getUserAgent(){return window.navigator.userAgent}getCookie(n){return Vr(document.cookie,n)}},qr=null;function b0(){return qr=qr||document.head.querySelector("base"),qr?qr.getAttribute("href"):null}function v0(t){return new URL(t,document.baseURI).pathname}var Uh=["alt","control","meta","shift"],_0={"\b":"Backspace","	":"Tab","\x7F":"Delete","\x1B":"Escape",Del:"Delete",Esc:"Escape",Left:"ArrowLeft",Right:"ArrowRight",Up:"ArrowUp",Down:"ArrowDown",Menu:"ContextMenu",Scroll:"ScrollLock",Win:"OS"},y0={alt:t=>t.altKey,control:t=>t.ctrlKey,meta:t=>t.metaKey,shift:t=>t.shiftKey},qh=(()=>{class t extends zr{constructor(e){super(e)}supports(e){return t.parseEventName(e)!=null}addEventListener(e,i,a,r){let o=t.parseEventName(i),s=t.eventCallback(o.fullKey,a,this.manager.getZone());return this.manager.getZone().runOutsideAngular(()=>zn().onAndCancel(e,o.domEventName,s,r))}static parseEventName(e){let i=e.toLowerCase().split("."),a=i.shift();if(i.length===0||!(a==="keydown"||a==="keyup"))return null;let r=t._normalizeKey(i.pop()),o="",s=i.indexOf("code");if(s>-1&&(i.splice(s,1),o="code."),Uh.forEach(c=>{let d=i.indexOf(c);d>-1&&(i.splice(d,1),o+=c+".")}),o+=r,i.length!=0||r.length===0)return null;let l={};return l.domEventName=a,l.fullKey=o,l}static matchEventFullKeyCode(e,i){let a=_0[e.key]||e.key,r="";return i.indexOf("code.")>-1&&(a=e.code,r="code."),a==null||!a?!1:(a=a.toLowerCase(),a===" "?a="space":a==="."&&(a="dot"),Uh.forEach(o=>{if(o!==a){let s=y0[o];s(e)&&(r+=o+".")}}),r+=a,r===i)}static eventCallback(e,i,a){return r=>{t.matchEventFullKeyCode(r,e)&&a.runGuarded(()=>i(r))}}static _normalizeKey(e){return e==="esc"?"escape":e}static \u0275fac=function(i){return new(i||t)(B(Y))};static \u0275prov=te({token:t,factory:t.\u0275fac})}return t})();async function sd(t,n,e){let i=N({rootComponent:t},x0(n,e));return kh(i)}function x0(t,n){return{platformRef:n?.platformRef,appProviders:[...E0,...t?.providers??[]],platformProviders:C0}}function w0(){Ns.makeCurrent()}function S0(){return new Ai}function k0(){return oh(document),document}var C0=[{provide:ri,useValue:ed},{provide:ih,useValue:w0,multi:!0},{provide:Y,useFactory:k0}];var E0=[{provide:nh,useValue:"root"},{provide:Ai,useFactory:S0},{provide:Ms,useClass:Cs,multi:!0},{provide:Ms,useClass:qh,multi:!0},Ur,{provide:Nr,useClass:rd},{provide:rd,useExisting:Nr},ad,{provide:yt,useExisting:Ur},[]];var Yt=class t{headers;normalizedNames=new Map;lazyInit;lazyUpdate=null;constructor(n){n?typeof n=="string"?this.lazyInit=()=>{this.headers=new Map,n.split(`
`).forEach(e=>{let i=e.indexOf(":");if(i>0){let a=e.slice(0,i),r=e.slice(i+1).trim();this.addHeaderEntry(a,r)}})}:typeof Headers<"u"&&n instanceof Headers?(this.headers=new Map,n.forEach((e,i)=>{this.addHeaderEntry(i,e)})):this.lazyInit=()=>{this.headers=new Map,Object.entries(n).forEach(([e,i])=>{this.setHeaderEntries(e,i)})}:this.headers=new Map}has(n){return this.init(),this.headers.has(n.toLowerCase())}get(n){this.init();let e=this.headers.get(n.toLowerCase());return e&&e.length>0?e[0]:null}keys(){return this.init(),Array.from(this.normalizedNames.values())}getAll(n){return this.init(),this.headers.get(n.toLowerCase())||null}append(n,e){return this.clone({name:n,value:e,op:"a"})}set(n,e){return this.clone({name:n,value:e,op:"s"})}delete(n,e){return this.clone({name:n,value:e,op:"d"})}maybeSetNormalizedName(n,e){this.normalizedNames.has(e)||this.normalizedNames.set(e,n)}init(){this.lazyInit&&(this.lazyInit instanceof t?this.copyFrom(this.lazyInit):this.lazyInit(),this.lazyInit=null,this.lazyUpdate&&(this.lazyUpdate.forEach(n=>this.applyUpdate(n)),this.lazyUpdate=null))}copyFrom(n){n.init();for(let[e,i]of n.headers.entries())this.headers.set(e,i),this.normalizedNames.set(e,n.normalizedNames.get(e))}clone(n){let e=new t;return e.lazyInit=this.lazyInit&&this.lazyInit instanceof t?this.lazyInit:this,e.lazyUpdate=(this.lazyUpdate||[]).concat([n]),e}applyUpdate(n){let e=n.name.toLowerCase();switch(n.op){case"a":case"s":let i=n.value;if(typeof i=="string"&&(i=[i]),i.length===0)return;this.maybeSetNormalizedName(n.name,e);let a=n.op==="a"?(this.headers.get(e)||[]).slice():[];a.push(...i),this.headers.set(e,a);break;case"d":let r=n.value;if(r===void 0)this.headers.delete(e),this.normalizedNames.delete(e);else{let o=Array.isArray(r)?r:[r],s=this.headers.get(e);if(!s)return;s=s.filter(l=>o.indexOf(l)===-1),s.length===0?(this.headers.delete(e),this.normalizedNames.delete(e)):this.headers.set(e,s)}break}}addHeaderEntry(n,e){let i=n.toLowerCase();this.maybeSetNormalizedName(n,i),this.headers.has(i)?this.headers.get(i).push(e):this.headers.set(i,[e])}setHeaderEntries(n,e){let i=(Array.isArray(e)?e:[e]).map(r=>r.toString()),a=n.toLowerCase();this.headers.set(a,i),this.maybeSetNormalizedName(n,a)}forEach(n){this.init(),Array.from(this.normalizedNames.keys()).forEach(e=>n(this.normalizedNames.get(e),this.headers.get(e)))}};var As=class{map=new Map;set(n,e){return this.map.set(n,e),this}get(n){return this.map.has(n)||this.map.set(n,n.defaultValue()),this.map.get(n)}delete(n){return this.map.delete(n),this}has(n){return this.map.has(n)}keys(){return this.map.keys()}},Ts=class{encodeKey(n){return Gh(n)}encodeValue(n){return Gh(n)}decodeKey(n){return decodeURIComponent(n)}decodeValue(n){return decodeURIComponent(n)}};function D0(t,n){let e=new Map;return t.length>0&&t.replace(/^\?/,"").split("&").forEach(a=>{let r=a.indexOf("="),[o,s]=r==-1?[n.decodeKey(a),""]:[n.decodeKey(a.slice(0,r)),n.decodeValue(a.slice(r+1))],l=e.get(o)||[];l.push(s),e.set(o,l)}),e}var M0=/[&+#]/g;function Gh(t){return encodeURI(t).replace(M0,encodeURIComponent)}function Is(t){return`${t}`}var qt=class t{map;encoder;updates=null;cloneFrom=null;constructor(n={}){if(this.encoder=n.encoder||new Ts,n.fromString){if(n.fromObject)throw new Pe(2805,!1);this.map=D0(n.fromString,this.encoder)}else n.fromObject?(this.map=new Map,Object.keys(n.fromObject).forEach(e=>{let i=n.fromObject[e],a=Array.isArray(i)?i.map(Is):[Is(i)];this.map.set(e,a)})):this.map=null}has(n){return this.init(),this.map.has(n)}get(n){this.init();let e=this.map.get(n);return e?e[0]:null}getAll(n){return this.init(),this.map.get(n)||null}keys(){return this.init(),Array.from(this.map.keys())}append(n,e){return this.clone({param:n,value:e,op:"a"})}appendAll(n){let e=[];return Object.keys(n).forEach(i=>{let a=n[i];Array.isArray(a)?a.forEach(r=>{e.push({param:i,value:r,op:"a"})}):e.push({param:i,value:a,op:"a"})}),this.clone(e)}set(n,e){return this.clone({param:n,value:e,op:"s"})}delete(n,e){return this.clone({param:n,value:e,op:"d"})}toString(){return this.init(),this.keys().map(n=>{let e=this.encoder.encodeKey(n);return this.map.get(n).map(i=>e+"="+this.encoder.encodeValue(i)).join("&")}).filter(n=>n!=="").join("&")}clone(n){let e=new t({encoder:this.encoder});return e.cloneFrom=this.cloneFrom||this,e.updates=(this.updates||[]).concat(n),e}init(){if(this.map===null&&(this.map=new Map),this.cloneFrom!==null){this.cloneFrom.init();for(let[n,e]of this.cloneFrom.map.entries())this.map.set(n,e);this.updates.forEach(n=>{switch(n.op){case"a":case"s":let e=n.op==="a"?(this.map.get(n.param)||[]).slice():[];e.push(Is(n.value)),this.map.set(n.param,e);break;case"d":if(n.value!==void 0){let i=(this.map.get(n.param)||[]).slice(),a=i.indexOf(Is(n.value));a!==-1&&i.splice(a,1),i.length>0?this.map.set(n.param,i):this.map.delete(n.param)}else{this.map.delete(n.param);break}}}),this.cloneFrom=this.updates=null}}};function N0(t){switch(t){case"DELETE":case"GET":case"HEAD":case"OPTIONS":case"JSONP":return!1;default:return!0}}function Wh(t){return typeof ArrayBuffer<"u"&&t instanceof ArrayBuffer}function Kh(t){return typeof Blob<"u"&&t instanceof Blob}function Yh(t){return typeof FormData<"u"&&t instanceof FormData}function I0(t){return typeof URLSearchParams<"u"&&t instanceof URLSearchParams}var Gr="Content-Type",Os="Accept",Jh="text/plain",ef="application/json",tf=`${ef}, ${Jh}, */*`,Ca=class t{url;body=null;headers;context;reportProgress=!1;reportUploadProgress=!1;reportDownloadProgress=!1;withCredentials=!1;credentials;keepalive=!1;cache;priority;mode;redirect;referrer;integrity;referrerPolicy;responseType="json";method;params;urlWithParams;transferCache;timeout;constructor(n,e,i,a){this.url=e,this.method=n.toUpperCase();let r;if(N0(this.method)||a?(this.body=i!==void 0?i:null,r=a):r=i,r){if(this.reportProgress=!!r.reportProgress,this.reportUploadProgress=!!r.reportUploadProgress,this.reportDownloadProgress=!!r.reportDownloadProgress,this.withCredentials=!!r.withCredentials,this.keepalive=!!r.keepalive,r.responseType&&(this.responseType=r.responseType),r.headers&&(this.headers=r.headers),r.context&&(this.context=r.context),r.params&&(this.params=r.params),r.priority&&(this.priority=r.priority),r.cache&&(this.cache=r.cache),r.credentials&&(this.credentials=r.credentials),typeof r.timeout=="number"){if(r.timeout<1||!Number.isInteger(r.timeout))throw new Pe(2822,"");this.timeout=r.timeout}r.mode&&(this.mode=r.mode),r.redirect&&(this.redirect=r.redirect),r.integrity&&(this.integrity=r.integrity),r.referrer!==void 0&&(this.referrer=r.referrer),r.referrerPolicy&&(this.referrerPolicy=r.referrerPolicy),this.transferCache=r.transferCache}if(this.headers??=new Yt,this.context??=new As,!this.params)this.params=new qt,this.urlWithParams=e;else{let o=this.params.toString();if(o.length===0)this.urlWithParams=e;else{let s=e,l="",c=e.indexOf("#");c!==-1&&(l=e.substring(c),s=e.substring(0,c));let d=s.indexOf("?"),m=d===-1?"?":d<s.length-1?"&":"";this.urlWithParams=s+m+o+l}}}serializeBody(){return this.body===null?null:typeof this.body=="string"||Wh(this.body)||Kh(this.body)||Yh(this.body)||I0(this.body)?this.body:this.body instanceof qt?this.body.toString():typeof this.body=="object"||typeof this.body=="boolean"||Array.isArray(this.body)?JSON.stringify(this.body):this.body.toString()}detectContentTypeHeader(){return this.body===null||Yh(this.body)?null:Kh(this.body)?this.body.type||null:Wh(this.body)?null:typeof this.body=="string"?Jh:this.body instanceof qt?"application/x-www-form-urlencoded;charset=UTF-8":typeof this.body=="object"||typeof this.body=="number"||typeof this.body=="boolean"?ef:null}clone(n={}){let e=n.method||this.method,i=n.url||this.url,a=n.responseType||this.responseType,r=n.keepalive??this.keepalive,o=n.priority||this.priority,s=n.cache||this.cache,l=n.mode||this.mode,c=n.redirect||this.redirect,d=n.credentials||this.credentials,m=n.referrer??this.referrer,u=n.integrity||this.integrity,f=n.referrerPolicy||this.referrerPolicy,g=n.transferCache??this.transferCache,b=n.timeout??this.timeout,_=n.body!==void 0?n.body:this.body,x=n.withCredentials??this.withCredentials,w=n.reportProgress??this.reportProgress,C=n.reportUploadProgress??this.reportUploadProgress,F=n.reportDownloadProgress??this.reportDownloadProgress,A=n.headers||this.headers,Q=n.params||this.params,Re=n.context??this.context;return n.setHeaders!==void 0&&(A=Object.keys(n.setHeaders).reduce((He,Be)=>He.set(Be,n.setHeaders[Be]),A)),n.setParams&&(Q=Object.keys(n.setParams).reduce((He,Be)=>He.set(Be,n.setParams[Be]),Q)),new t(e,i,_,{params:Q,headers:A,context:Re,reportProgress:w,reportUploadProgress:C,reportDownloadProgress:F,responseType:a,withCredentials:x,transferCache:g,keepalive:r,cache:s,priority:o,timeout:b,mode:l,redirect:c,credentials:d,referrer:m,integrity:u,referrerPolicy:f})}},Cn=(function(t){return t[t.Sent=0]="Sent",t[t.UploadProgress=1]="UploadProgress",t[t.ResponseHeader=2]="ResponseHeader",t[t.DownloadProgress=3]="DownloadProgress",t[t.Response=4]="Response",t[t.User=5]="User",t})(Cn||{}),Ea=class{headers;status;statusText;url;ok;type;redirected;responseType;constructor(n,e=200,i="OK"){this.headers=n.headers||new Yt,this.status=n.status!==void 0?n.status:e,this.statusText=n.statusText||i,this.url=n.url||null,this.redirected=n.redirected,this.responseType=n.responseType,this.ok=this.status>=200&&this.status<300}},Wr=class t extends Ea{constructor(n={}){super(n)}type=Cn.ResponseHeader;clone(n={}){return new t({headers:n.headers||this.headers,status:n.status!==void 0?n.status:this.status,statusText:n.statusText||this.statusText,url:n.url||this.url||void 0})}},fi=class t extends Ea{body;constructor(n={}){super(n),this.body=n.body!==void 0?n.body:null}type=Cn.Response;clone(n={}){return new t({body:n.body!==void 0?n.body:this.body,headers:n.headers||this.headers,status:n.status!==void 0?n.status:this.status,statusText:n.statusText||this.statusText,url:n.url||this.url||void 0,redirected:n.redirected??this.redirected,responseType:n.responseType??this.responseType})}},kn=class extends Ea{name="HttpErrorResponse";message;error;ok=!1;constructor(n){super(n,0,"Unknown Error"),this.status>=200&&this.status<300?this.message=`Http failure during parsing for ${n.url||"(unknown url)"}`:this.message=`Http failure response for ${n.url||"(unknown url)"}: ${n.status} ${n.statusText}`,this.error=n.error||null}},nf=200,A0=204;var T0=/^\)\]\}',?\n/,aO=1024*1024,af=new I("",{factory:()=>null}),Rs=(()=>{class t{fetchImpl=p(cd,{optional:!0})?.fetch??((...e)=>globalThis.fetch(...e));ngZone=p(K);destroyRef=p(jn);maxResponseSize=p(af);handle(e){return new Nt(i=>{let a=new AbortController,r=!1,o={next:l=>{l.type===Cn.Response&&(r=!0),i.next(l)},error:l=>{r=!0,i.error(l)},complete:()=>{r=!0,i.complete()}};this.doRequest(e,a.signal,o).then(dd,l=>o.error(new kn({error:l})));let s;return e.timeout&&(s=this.ngZone.runOutsideAngular(()=>setTimeout(()=>{a.signal.aborted||a.abort(new DOMException("signal timed out","TimeoutError"))},e.timeout))),()=>{s!==void 0&&clearTimeout(s),!r&&!a.signal.aborted&&a.abort()}})}async doRequest(e,i,a){let r=this.createRequestInit(e),o;try{let _=this.ngZone.runOutsideAngular(()=>this.fetchImpl(e.urlWithParams,N({signal:i},r)));O0(_),a.next({type:Cn.Sent}),o=await _}catch(_){a.error(new kn({error:_,status:_.status??0,statusText:_.statusText,url:e.urlWithParams,headers:_.headers}));return}let s=new Yt(o.headers),l=o.statusText,c=o.url||e.urlWithParams,d=o.status,m=null,u=e.reportProgress||e.reportDownloadProgress;if(u&&a.next(new Wr({headers:s,status:d,statusText:l,url:c})),o.body){let _=o.headers.get(Gr)??"",x=o.headers.get("content-length"),w=x!==null?Number(x):NaN;this.maxResponseSize!==null&&Number.isFinite(w)&&w>this.maxResponseSize&&(await o.body.cancel(),Xh(this.maxResponseSize));let C=[],F=o.body.getReader(),A=0,Q,Re,He=typeof Zone<"u"&&Zone.current,Be=!1;if(await this.ngZone.runOutsideAngular(async()=>{for(;;){if(this.destroyRef.destroyed){await F.cancel(),Be=!0;break}let{done:oe,value:k}=await F.read();if(oe)break;if(C.push(k),A+=k.length,this.maxResponseSize!==null&&A>this.maxResponseSize&&(await F.cancel(),Xh(this.maxResponseSize)),u){Re=e.responseType==="text"?(Re??"")+(Q??=Zh(_)).decode(k,{stream:!0}):void 0;let ce=()=>a.next({type:Cn.DownloadProgress,total:Number.isFinite(w)?w:void 0,loaded:A,partialText:Re});He?He.run(ce):ce()}}}),Be){a.complete();return}let G=this.concatChunks(C,A);try{m=this.parseBody(e,G,_,d)}catch(oe){a.error(new kn({error:oe,headers:new Yt(o.headers),status:o.status,statusText:o.statusText,url:o.url||e.urlWithParams}));return}}d===0&&(d=m?nf:0);let f=d>=200&&d<300,g=o.redirected,b=o.type;f?(a.next(new fi({body:m,headers:s,status:d,statusText:l,url:c,redirected:g,responseType:b})),a.complete()):a.error(new kn({error:m,headers:s,status:d,statusText:l,url:c,redirected:g,responseType:b}))}parseBody(e,i,a,r){switch(e.responseType){case"json":let o=new TextDecoder().decode(i).replace(T0,"");if(o==="")return null;try{return JSON.parse(o)}catch(s){if(r<200||r>=300)return o;throw s}case"text":return Zh(a).decode(i);case"blob":return new Blob([i],{type:a});case"arraybuffer":return i.buffer}}createRequestInit(e){if(e.reportUploadProgress)throw new Pe(2824,!1);let i={},a;if(a=e.credentials,e.withCredentials&&(a="include"),e.headers.forEach((r,o)=>i[r]=o.join(",")),e.headers.has(Os)||(i[Os]=tf),!e.headers.has(Gr)){let r=e.detectContentTypeHeader();r!==null&&(i[Gr]=r)}return{body:e.serializeBody(),method:e.method,headers:i,credentials:a,keepalive:e.keepalive,cache:e.cache,priority:e.priority,mode:e.mode,redirect:e.redirect,referrer:e.referrer,integrity:e.integrity,referrerPolicy:e.referrerPolicy}}concatChunks(e,i){let a=new Uint8Array(i),r=0;for(let o of e)a.set(o,r),r+=o.length;return a}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),cd=class{};function dd(){}function O0(t){t.then(dd,dd)}function Xh(t){throw new Pe(-2825,!1)}var R0=/charset=\s*["']?([^;"'\s]+)["']?/i;function Zh(t){let n=t.match(R0);if(n!==null)try{return new TextDecoder(n[1])}catch{}return new TextDecoder}var P0=new I("",{factory:()=>!0}),F0="XSRF-TOKEN",L0=new I("",{factory:()=>F0}),B0="X-XSRF-TOKEN",j0=new I("",{factory:()=>B0}),V0=(()=>{class t{cookieName=p(L0);doc=p(Y);lastCookieString="";lastToken=null;parseCount=0;getToken(){let e=this.doc.cookie||"";return e!==this.lastCookieString&&(this.parseCount++,this.lastToken=Vr(e,this.cookieName),this.lastCookieString=e),this.lastToken}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),rf=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275prov=te({token:t,factory:function(i){let a=null;return i?a=new(i||t):a=B(V0),a},providedIn:"root"})}return t})();function of(t,n){if(!p(P0)||t.method==="GET"||t.method==="HEAD")return n(t);try{let a=p(wa).href,{origin:r}=new URL(a),{origin:o}=new URL(t.url,r);if(r!==o)return n(t)}catch{return n(t)}let e=p(rf).getToken(),i=p(j0);return e!=null&&!t.headers.has(i)&&(t=t.clone({headers:t.headers.set(i,e)})),n(t)}function sf(t,n){return n(t)}function $0(t,n){return(e,i)=>n.intercept(e,{handle:a=>t(a,i)})}function z0(t,n,e){return(i,a)=>ps(e,()=>n(i,r=>t(r,a)))}var lf=new I(""),md=new I("",{factory:()=>[of]}),cf=new I(""),pd=new I("",{factory:()=>!0});function H0(){let t=null;return(n,e)=>{t===null&&(t=(p(lf,{optional:!0})??[]).reduceRight($0,sf));let i=p(ba);if(p(pd)){let r=i.add();return t(n,e).pipe(Er(r))}else return t(n,e)}}var Kr=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275prov=te({token:t,factory:function(i){let a=null;return i?a=new(i||t):a=B(Rs),a},providedIn:"root"})}return t})();var Ps=(()=>{class t{backend;injector;chain=null;pendingTasks=p(ba);contributeToStability=p(pd);constructor(e,i){this.backend=e,this.injector=i}handle(e){if(this.chain===null){let a=this.injector.get(Fs,null,{skipSelf:!0}),r=a!==null&&this.backend===a,o=this.injector.get(cf,[],r?{self:!0}:void 0),s=Array.from(new Set([...this.injector.get(md),...o]));this.chain=s.reduceRight((l,c)=>z0(l,c,this.injector),sf)}let i=this.chain;if(this.contributeToStability){let a=this.pendingTasks.add();return dn(()=>i(e,r=>this.backend.handle(r))).pipe(Er(a))}else return dn(()=>i(e,a=>this.backend.handle(a)))}static \u0275fac=function(i){return new(i||t)(B(Kr),B(Bn))};static \u0275prov=te({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),Fs=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275prov=te({token:t,factory:function(i){let a=null;return i?a=new(i||t):a=B(Ps),a},providedIn:"root"})}return t})();function ld(t,n){return N({body:n},t)}var Xt=(()=>{class t{handler;constructor(e){this.handler=e}request(e,i,a={}){let r;if(e instanceof Ca)r=e;else{let l;a.headers instanceof Yt?l=a.headers:l=new Yt(a.headers);let c;a.params&&(a.params instanceof qt?c=a.params:c=new qt({fromObject:a.params})),r=new Ca(e,i,a.body!==void 0?a.body:null,{headers:l,context:a.context,params:c,reportProgress:a.reportProgress,reportUploadProgress:a.reportUploadProgress,reportDownloadProgress:a.reportDownloadProgress,responseType:a.responseType||"json",withCredentials:a.withCredentials,transferCache:a.transferCache,keepalive:a.keepalive,priority:a.priority,cache:a.cache,mode:a.mode,redirect:a.redirect,credentials:a.credentials,referrer:a.referrer,referrerPolicy:a.referrerPolicy,integrity:a.integrity,timeout:a.timeout})}let o=Ue(r).pipe(Sr(l=>this.handler.handle(l)));if(e instanceof Ca||a.observe==="events")return o;let s=o.pipe(Ke(l=>l instanceof fi));switch(a.observe||"body"){case"body":switch(r.responseType){case"arraybuffer":return s.pipe(Me(l=>{if(l.body!==null&&!(l.body instanceof ArrayBuffer))throw new Pe(2806,!1);return l.body}));case"blob":return s.pipe(Me(l=>{if(l.body!==null&&!(l.body instanceof Blob))throw new Pe(2807,!1);return l.body}));case"text":return s.pipe(Me(l=>{if(l.body!==null&&typeof l.body!="string")throw new Pe(2808,!1);return l.body}));default:return s.pipe(Me(l=>l.body))}case"response":return s;default:throw new Pe(2809,!1)}}delete(e,i={}){return this.request("DELETE",e,i)}get(e,i={}){return this.request("GET",e,i)}head(e,i={}){return this.request("HEAD",e,i)}jsonp(e,i){return this.request("JSONP",e,{params:new qt().append(i,"JSONP_CALLBACK"),observe:"body",responseType:"json"})}options(e,i={}){return this.request("OPTIONS",e,i)}patch(e,i,a={}){return this.request("PATCH",e,ld(a,i))}post(e,i,a={}){return this.request("POST",e,ld(a,i))}put(e,i,a={}){return this.request("PUT",e,ld(a,i))}static \u0275fac=function(i){return new(i||t)(B(Fs))};static \u0275prov=te({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var U0=/^\)\]\}',?\n/;var ud=(()=>{class t{xhrFactory;tracingService=p(Mr,{optional:!0});constructor(e){this.xhrFactory=e}maybePropagateTrace(e){return this.tracingService?.propagate?this.tracingService.propagate(e):e}handle(e){if(e.method==="JSONP")throw new Pe(-2800,!1);let i=this.xhrFactory;return Ue(null).pipe(Gt(()=>new Nt(r=>{let o=i.build();if(o.open(e.method,e.urlWithParams),e.withCredentials&&(o.withCredentials=!0),e.headers.forEach((w,C)=>o.setRequestHeader(w,C.join(","))),e.headers.has(Os)||o.setRequestHeader(Os,tf),!e.headers.has(Gr)){let w=e.detectContentTypeHeader();w!==null&&o.setRequestHeader(Gr,w)}if(e.timeout&&(o.timeout=e.timeout),e.responseType){let w=e.responseType.toLowerCase();o.responseType=w!=="json"?w:"text"}let s=e.serializeBody(),l=null,c=()=>{if(l!==null)return l;let w=o.statusText||"OK",C=new Yt(o.getAllResponseHeaders()),F=o.responseURL||e.url;return l=new Wr({headers:C,status:o.status,statusText:w,url:F}),l},d=this.maybePropagateTrace(()=>{let{headers:w,status:C,statusText:F,url:A}=c(),Q=null;C!==A0&&(Q=typeof o.response>"u"?o.responseText:o.response),C===0&&(C=Q?nf:0);let Re=C>=200&&C<300;if(e.responseType==="json"&&typeof Q=="string"){let He=Q;Q=Q.replace(U0,"");try{Q=Q!==""?JSON.parse(Q):null}catch(Be){Q=He,Re&&(Re=!1,Q={error:Be,text:Q})}}Re?(r.next(new fi({body:Q,headers:w,status:C,statusText:F,url:A||void 0})),r.complete()):r.error(new kn({error:Q,headers:w,status:C,statusText:F,url:A||void 0}))}),m=this.maybePropagateTrace(w=>{let{url:C}=c(),F=new kn({error:w,status:o.status||0,statusText:o.statusText||"Unknown Error",url:C||void 0});r.error(F)}),u=m;e.timeout&&(u=this.maybePropagateTrace(w=>{let{url:C}=c(),F=new kn({error:new DOMException("Request timed out","TimeoutError"),status:o.status||0,statusText:o.statusText||"Request timeout",url:C||void 0});r.error(F)}));let f=!1,g=this.maybePropagateTrace(w=>{f||(r.next(c()),f=!0);let C={type:Cn.DownloadProgress,loaded:w.loaded};w.lengthComputable&&(C.total=w.total),e.responseType==="text"&&o.responseText&&(C.partialText=o.responseText),r.next(C)}),b=this.maybePropagateTrace(w=>{let C={type:Cn.UploadProgress,loaded:w.loaded};w.lengthComputable&&(C.total=w.total),r.next(C)});o.addEventListener("load",d),o.addEventListener("error",m),o.addEventListener("timeout",u),o.addEventListener("abort",m);let _=e.reportProgress||e.reportUploadProgress,x=e.reportProgress||e.reportDownloadProgress;return x&&o.addEventListener("progress",g),_&&s!==null&&o.upload&&o.upload.addEventListener("progress",b),o.send(s),r.next({type:Cn.Sent}),()=>{o.removeEventListener("error",m),o.removeEventListener("abort",m),o.removeEventListener("load",d),o.removeEventListener("timeout",u),x&&o.removeEventListener("progress",g),_&&s!==null&&o.upload&&o.upload.removeEventListener("progress",b),o.readyState!==o.DONE&&o.abort()}})))}static \u0275fac=function(i){return new(i||t)(B(Oi))};static \u0275prov=te({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),hd=(function(t){return t[t.Interceptors=0]="Interceptors",t[t.LegacyInterceptors=1]="LegacyInterceptors",t[t.CustomXsrfConfiguration=2]="CustomXsrfConfiguration",t[t.NoXsrfProtection=3]="NoXsrfProtection",t[t.JsonpSupport=4]="JsonpSupport",t[t.RequestsMadeViaParent=5]="RequestsMadeViaParent",t[t.Fetch=6]="Fetch",t[t.Xhr=7]="Xhr",t})(hd||{});function q0(t,n){return{\u0275kind:t,\u0275providers:n}}function fd(...t){let n=[Xt,Rs,Ps,{provide:Fs,useExisting:Ps},{provide:Kr,useFactory:()=>p(Rs)},{provide:md,useValue:of,multi:!0}];for(let e of t)n.push(...e.\u0275providers);return ua(n)}var Qh=new I("");function gd(){return q0(hd.LegacyInterceptors,[{provide:Qh,useFactory:H0},{provide:md,useExisting:Qh,multi:!0}])}var Hn=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275prov=te({token:t,factory:function(i){let a=null;return i?a=new(i||t):a=B(G0),a},providedIn:"root"})}return t})(),G0=(()=>{class t extends Hn{_doc=p(Y);sanitize(e,i){if(i==null)return null;switch(e){case mt.NONE:return i;case mt.HTML:return va(i,"HTML")?si(i):uh(this._doc,String(i)).toString();case mt.STYLE:return va(i,"Style")?si(i):i;case mt.SCRIPT:if(va(i,"Script"))return si(i);throw new Pe(5200,!1);case mt.URL:return va(i,"URL")?si(i):ph(String(i));case mt.RESOURCE_URL:if(va(i,"ResourceURL"))return si(i);throw new Pe(-5201,!1);default:throw new Pe(5202,!1)}}bypassSecurityTrustHtml(e){return sh(e)}bypassSecurityTrustStyle(e){return lh(e)}bypassSecurityTrustScript(e){return ch(e)}bypassSecurityTrustUrl(e){return dh(e)}bypassSecurityTrustResourceUrl(e){return mh(e)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var Bs={production:!0};var W0="@",K0=(()=>{class t{doc;delegate;zone;animationType;moduleImpl;_rendererFactoryPromise=null;scheduler=null;injector=p(re);loadingSchedulerFn=p(Y0,{optional:!0});_engine;constructor(e,i,a,r,o){this.doc=e,this.delegate=i,this.zone=a,this.animationType=r,this.moduleImpl=o}ngOnDestroy(){this._engine?.flush()}loadImpl(){let e=()=>this.moduleImpl??import("./chunk-5TPWYSTA.js").then(a=>a),i;return this.loadingSchedulerFn?i=this.loadingSchedulerFn(e):i=e(),i.catch(a=>{throw new Pe(5300,!1)}).then(({\u0275createEngine:a,\u0275AnimationRendererFactory:r})=>{this._engine=a(this.animationType,this.doc);let o=new r(this.delegate,this._engine,this.zone);return this.delegate=o,o})}createRenderer(e,i){let a=this.delegate.createRenderer(e,i);if(a.\u0275type===0)return a;Hh(a);let r=new bd(a);return i?.data?.animation&&!this._rendererFactoryPromise&&(this._rendererFactoryPromise=this.loadImpl()),this._rendererFactoryPromise?.then(o=>{let s=o.createRenderer(e,i);r.use(s),this.scheduler??=this.injector.get(rh,null,{optional:!0}),this.scheduler?.notify(10)}).catch(o=>{r.use(a)}),r}begin(){this.delegate.begin?.()}end(){this.delegate.end?.()}whenRenderingDone(){return this.delegate.whenRenderingDone?.()??Promise.resolve()}componentReplaced(e){this._engine?.flush(),this.delegate.componentReplaced?.(e)}static \u0275fac=function(i){fs()};static \u0275prov=te({token:t,factory:t.\u0275fac})}return t})(),bd=class{delegate;replay=[];\u0275type=1;constructor(n){this.delegate=n}use(n){if(this.delegate=n,this.replay!==null){for(let e of this.replay)e(n);this.replay=null}}get data(){return this.delegate.data}destroy(){this.replay=null,this.delegate.destroy()}createElement(n,e){return this.delegate.createElement(n,e)}createComment(n){return this.delegate.createComment(n)}createText(n){return this.delegate.createText(n)}get destroyNode(){return this.delegate.destroyNode}appendChild(n,e){this.delegate.appendChild(n,e)}insertBefore(n,e,i,a){this.delegate.insertBefore(n,e,i,a)}removeChild(n,e,i,a){this.delegate.removeChild(n,e,i,a)}selectRootElement(n,e){return this.delegate.selectRootElement(n,e)}parentNode(n){return this.delegate.parentNode(n)}nextSibling(n){return this.delegate.nextSibling(n)}setAttribute(n,e,i,a){this.delegate.setAttribute(n,e,i,a)}removeAttribute(n,e,i){this.delegate.removeAttribute(n,e,i)}addClass(n,e){this.delegate.addClass(n,e)}removeClass(n,e){this.delegate.removeClass(n,e)}setStyle(n,e,i,a){this.delegate.setStyle(n,e,i,a)}removeStyle(n,e,i){this.delegate.removeStyle(n,e,i)}setProperty(n,e,i){this.shouldReplay(e)&&this.replay.push(a=>a.setProperty(n,e,i)),this.delegate.setProperty(n,e,i)}setValue(n,e){this.delegate.setValue(n,e)}listen(n,e,i,a){return this.shouldReplay(e)&&this.replay.push(r=>r.listen(n,e,i,a)),this.delegate.listen(n,e,i,a)}shouldReplay(n){return this.replay!==null&&n.startsWith(W0)}},Y0=new I("");function mf(t="animations"){return hs("NgAsyncAnimations"),ua([{provide:yt,useFactory:()=>new K0(p(Y),p(Ur),p(K),t)},{provide:fa,useValue:t==="noop"?"NoopAnimations":"BrowserAnimations"}])}function X0(t,n){return new Nt(e=>{let i=!1,a=!1,r=t.subscribe(o=>{a=!0,setTimeout(()=>{e.next(o),i&&e.complete()},n)},o=>setTimeout(()=>e.error(o),n),()=>{i=!0,a||e.complete()});return()=>r.unsubscribe()})}var ge={CONTINUE:100,SWITCHING_PROTOCOLS:101,OK:200,CREATED:201,ACCEPTED:202,NON_AUTHORITATIVE_INFORMATION:203,NO_CONTENT:204,RESET_CONTENT:205,PARTIAL_CONTENT:206,MULTIPLE_CHOICES:300,MOVED_PERMANTENTLY:301,FOUND:302,SEE_OTHER:303,NOT_MODIFIED:304,USE_PROXY:305,TEMPORARY_REDIRECT:307,BAD_REQUEST:400,UNAUTHORIZED:401,PAYMENT_REQUIRED:402,FORBIDDEN:403,NOT_FOUND:404,METHOD_NOT_ALLOWED:405,NOT_ACCEPTABLE:406,PROXY_AUTHENTICATION_REQUIRED:407,REQUEST_TIMEOUT:408,CONFLICT:409,GONE:410,LENGTH_REQUIRED:411,PRECONDITION_FAILED:412,PAYLOAD_TO_LARGE:413,URI_TOO_LONG:414,UNSUPPORTED_MEDIA_TYPE:415,RANGE_NOT_SATISFIABLE:416,EXPECTATION_FAILED:417,IM_A_TEAPOT:418,UPGRADE_REQUIRED:426,INTERNAL_SERVER_ERROR:500,NOT_IMPLEMENTED:501,BAD_GATEWAY:502,SERVICE_UNAVAILABLE:503,GATEWAY_TIMEOUT:504,HTTP_VERSION_NOT_SUPPORTED:505,PROCESSING:102,MULTI_STATUS:207,IM_USED:226,PERMANENT_REDIRECT:308,UNPROCESSABLE_ENTRY:422,LOCKED:423,FAILED_DEPENDENCY:424,PRECONDITION_REQUIRED:428,TOO_MANY_REQUESTS:429,REQUEST_HEADER_FIELDS_TOO_LARGE:431,UNAVAILABLE_FOR_LEGAL_REASONS:451,VARIANT_ALSO_NEGOTIATES:506,INSUFFICIENT_STORAGE:507,NETWORK_AUTHENTICATION_REQUIRED:511},Z0={100:{code:100,text:"Continue",description:'"The initial part of a request has been received and has not yet been rejected by the server."',spec_title:"RFC7231#6.2.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.2.1"},101:{code:101,text:"Switching Protocols",description:`"The server understands and is willing to comply with the client's request, via the Upgrade header field, for a change in the application protocol being used on this connection."`,spec_title:"RFC7231#6.2.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.2.2"},200:{code:200,text:"OK",description:'"The request has succeeded."',spec_title:"RFC7231#6.3.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.1"},201:{code:201,text:"Created",description:'"The request has been fulfilled and has resulted in one or more new resources being created."',spec_title:"RFC7231#6.3.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.2"},202:{code:202,text:"Accepted",description:'"The request has been accepted for processing, but the processing has not been completed."',spec_title:"RFC7231#6.3.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.3"},203:{code:203,text:"Non-Authoritative Information",description:`"The request was successful but the enclosed payload has been modified from that of the origin server's 200 (OK) response by a transforming proxy."`,spec_title:"RFC7231#6.3.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.4"},204:{code:204,text:"No Content",description:'"The server has successfully fulfilled the request and that there is no additional content to send in the response payload body."',spec_title:"RFC7231#6.3.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.5"},205:{code:205,text:"Reset Content",description:'"The server has fulfilled the request and desires that the user agent reset the "document view", which caused the request to be sent, to its original state as received from the origin server."',spec_title:"RFC7231#6.3.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.6"},206:{code:206,text:"Partial Content",description:`"The server is successfully fulfilling a range request for the target resource by transferring one or more parts of the selected representation that correspond to the satisfiable ranges found in the requests's Range header field."`,spec_title:"RFC7233#4.1",spec_href:"https://tools.ietf.org/html/rfc7233#section-4.1"},300:{code:300,text:"Multiple Choices",description:'"The target resource has more than one representation, each with its own more specific identifier, and information about the alternatives is being provided so that the user (or user agent) can select a preferred representation by redirecting its request to one or more of those identifiers."',spec_title:"RFC7231#6.4.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.1"},301:{code:301,text:"Moved Permanently",description:'"The target resource has been assigned a new permanent URI and any future references to this resource ought to use one of the enclosed URIs."',spec_title:"RFC7231#6.4.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.2"},302:{code:302,text:"Found",description:'"The target resource resides temporarily under a different URI."',spec_title:"RFC7231#6.4.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.3"},303:{code:303,text:"See Other",description:'"The server is redirecting the user agent to a different resource, as indicated by a URI in the Location header field, that is intended to provide an indirect response to the original request."',spec_title:"RFC7231#6.4.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.4"},304:{code:304,text:"Not Modified",description:'"A conditional GET request has been received and would have resulted in a 200 (OK) response if it were not for the fact that the condition has evaluated to false."',spec_title:"RFC7232#4.1",spec_href:"https://tools.ietf.org/html/rfc7232#section-4.1"},305:{code:305,text:"Use Proxy",description:"*deprecated*",spec_title:"RFC7231#6.4.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.5"},307:{code:307,text:"Temporary Redirect",description:'"The target resource resides temporarily under a different URI and the user agent MUST NOT change the request method if it performs an automatic redirection to that URI."',spec_title:"RFC7231#6.4.7",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.7"},400:{code:400,text:"Bad Request",description:'"The server cannot or will not process the request because the received syntax is invalid, nonsensical, or exceeds some limitation on what the server is willing to process."',spec_title:"RFC7231#6.5.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.1"},401:{code:401,text:"Unauthorized",description:'"The request has not been applied because it lacks valid authentication credentials for the target resource."',spec_title:"RFC7235#6.3.1",spec_href:"https://tools.ietf.org/html/rfc7235#section-3.1"},402:{code:402,text:"Payment Required",description:"*reserved*",spec_title:"RFC7231#6.5.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.2"},403:{code:403,text:"Forbidden",description:'"The server understood the request but refuses to authorize it."',spec_title:"RFC7231#6.5.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.3"},404:{code:404,text:"Not Found",description:'"The origin server did not find a current representation for the target resource or is not willing to disclose that one exists."',spec_title:"RFC7231#6.5.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.4"},405:{code:405,text:"Method Not Allowed",description:'"The method specified in the request-line is known by the origin server but not supported by the target resource."',spec_title:"RFC7231#6.5.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.5"},406:{code:406,text:"Not Acceptable",description:'"The target resource does not have a current representation that would be acceptable to the user agent, according to the proactive negotiation header fields received in the request, and the server is unwilling to supply a default representation."',spec_title:"RFC7231#6.5.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.6"},407:{code:407,text:"Proxy Authentication Required",description:'"The client needs to authenticate itself in order to use a proxy."',spec_title:"RFC7231#6.3.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.2"},408:{code:408,text:"Request Timeout",description:'"The server did not receive a complete request message within the time that it was prepared to wait."',spec_title:"RFC7231#6.5.7",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.7"},409:{code:409,text:"Conflict",description:'"The request could not be completed due to a conflict with the current state of the resource."',spec_title:"RFC7231#6.5.8",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.8"},410:{code:410,text:"Gone",description:'"Access to the target resource is no longer available at the origin server and that this condition is likely to be permanent."',spec_title:"RFC7231#6.5.9",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.9"},411:{code:411,text:"Length Required",description:'"The server refuses to accept the request without a defined Content-Length."',spec_title:"RFC7231#6.5.10",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.10"},412:{code:412,text:"Precondition Failed",description:'"One or more preconditions given in the request header fields evaluated to false when tested on the server."',spec_title:"RFC7232#4.2",spec_href:"https://tools.ietf.org/html/rfc7232#section-4.2"},413:{code:413,text:"Payload Too Large",description:'"The server is refusing to process a request because the request payload is larger than the server is willing or able to process."',spec_title:"RFC7231#6.5.11",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.11"},414:{code:414,text:"URI Too Long",description:'"The server is refusing to service the request because the request-target is longer than the server is willing to interpret."',spec_title:"RFC7231#6.5.12",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.12"},415:{code:415,text:"Unsupported Media Type",description:'"The origin server is refusing to service the request because the payload is in a format not supported by the target resource for this method."',spec_title:"RFC7231#6.5.13",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.13"},416:{code:416,text:"Range Not Satisfiable",description:`"None of the ranges in the request's Range header field overlap the current extent of the selected resource or that the set of ranges requested has been rejected due to invalid ranges or an excessive request of small or overlapping ranges."`,spec_title:"RFC7233#4.4",spec_href:"https://tools.ietf.org/html/rfc7233#section-4.4"},417:{code:417,text:"Expectation Failed",description:`"The expectation given in the request's Expect header field could not be met by at least one of the inbound servers."`,spec_title:"RFC7231#6.5.14",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.14"},418:{code:418,text:"I'm a teapot",description:'"1988 April Fools Joke. Returned by tea pots requested to brew coffee."',spec_title:"RFC 2324",spec_href:"https://tools.ietf.org/html/rfc2324"},426:{code:426,text:"Upgrade Required",description:'"The server refuses to perform the request using the current protocol but might be willing to do so after the client upgrades to a different protocol."',spec_title:"RFC7231#6.5.15",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.15"},500:{code:500,text:"Internal Server Error",description:'"The server encountered an unexpected condition that prevented it from fulfilling the request."',spec_title:"RFC7231#6.6.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.1"},501:{code:501,text:"Not Implemented",description:'"The server does not support the functionality required to fulfill the request."',spec_title:"RFC7231#6.6.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.2"},502:{code:502,text:"Bad Gateway",description:'"The server, while acting as a gateway or proxy, received an invalid response from an inbound server it accessed while attempting to fulfill the request."',spec_title:"RFC7231#6.6.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.3"},503:{code:503,text:"Service Unavailable",description:'"The server is currently unable to handle the request due to a temporary overload or scheduled maintenance, which will likely be alleviated after some delay."',spec_title:"RFC7231#6.6.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.4"},504:{code:504,text:"Gateway Time-out",description:'"The server, while acting as a gateway or proxy, did not receive a timely response from an upstream server it needed to access in order to complete the request."',spec_title:"RFC7231#6.6.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.5"},505:{code:505,text:"HTTP Version Not Supported",description:'"The server does not support, or refuses to support, the protocol version that was used in the request message."',spec_title:"RFC7231#6.6.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.6"},102:{code:102,text:"Processing",description:'"An interim response to inform the client that the server has accepted the complete request, but has not yet completed it."',spec_title:"RFC5218#10.1",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.1"},207:{code:207,text:"Multi-Status",description:'"Status for multiple independent operations."',spec_title:"RFC5218#10.2",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.2"},226:{code:226,text:"IM Used",description:'"The server has fulfilled a GET request for the resource, and the response is a representation of the result of one or more instance-manipulations applied to the current instance."',spec_title:"RFC3229#10.4.1",spec_href:"https://tools.ietf.org/html/rfc3229#section-10.4.1"},308:{code:308,text:"Permanent Redirect",description:'"The target resource has been assigned a new permanent URI and any future references to this resource SHOULD use one of the returned URIs. [...] This status code is similar to 301 Moved Permanently (Section 7.3.2 of rfc7231), except that it does not allow rewriting the request method from POST to GET."',spec_title:"RFC7238",spec_href:"https://tools.ietf.org/html/rfc7238"},422:{code:422,text:"Unprocessable Entity",description:'"The server understands the content type of the request entity (hence a 415(Unsupported Media Type) status code is inappropriate), and the syntax of the request entity is correct (thus a 400 (Bad Request) status code is inappropriate) but was unable to process the contained instructions."',spec_title:"RFC5218#10.3",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.3"},423:{code:423,text:"Locked",description:'"The source or destination resource of a method is locked."',spec_title:"RFC5218#10.4",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.4"},424:{code:424,text:"Failed Dependency",description:'"The method could not be performed on the resource because the requested action depended on another action and that action failed."',spec_title:"RFC5218#10.5",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.5"},428:{code:428,text:"Precondition Required",description:'"The origin server requires the request to be conditional."',spec_title:"RFC6585#3",spec_href:"https://tools.ietf.org/html/rfc6585#section-3"},429:{code:429,text:"Too Many Requests",description:'"The user has sent too many requests in a given amount of time ("rate limiting")."',spec_title:"RFC6585#4",spec_href:"https://tools.ietf.org/html/rfc6585#section-4"},431:{code:431,text:"Request Header Fields Too Large",description:'"The server is unwilling to process the request because its header fields are too large."',spec_title:"RFC6585#5",spec_href:"https://tools.ietf.org/html/rfc6585#section-5"},451:{code:451,text:"Unavailable For Legal Reasons",description:'"The server is denying access to the resource in response to a legal demand."',spec_title:"draft-ietf-httpbis-legally-restricted-status",spec_href:"https://tools.ietf.org/html/draft-ietf-httpbis-legally-restricted-status"},506:{code:506,text:"Variant Also Negotiates",description:'"The server has an internal configuration error: the chosen variant resource is configured to engage in transparent content negotiation itself, and is therefore not a proper end point in the negotiation process."',spec_title:"RFC2295#8.1",spec_href:"https://tools.ietf.org/html/rfc2295#section-8.1"},507:{code:507,text:"Insufficient Storage",description:'The method could not be performed on the resource because the server is unable to store the representation needed to successfully complete the request."',spec_title:"RFC5218#10.6",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.6"},511:{code:511,text:"Network Authentication Required",description:'"The client needs to authenticate to gain network access."',spec_title:"RFC6585#6",spec_href:"https://tools.ietf.org/html/rfc6585#section-6"}};function Q0(t){return Z0[t+""].text||"Unknown Status"}function J0(t){return t>=200&&t<300}var Yr=class{},vd=class{apiBase;caseSensitiveSearch;dataEncapsulation;delay;delete404;host;passThruUnknownUrl;post204;post409;put204;put404;rootPath},js=(()=>{class t{constructor(e={}){Object.assign(this,{caseSensitiveSearch:!1,dataEncapsulation:!1,delay:500,delete404:!1,passThruUnknownUrl:!1,post204:!0,post409:!1,put204:!0,put404:!1,apiBase:void 0,host:void 0,rootPath:void 0},e)}static \u0275fac=function(i){return new(i||t)(B(vd))};static \u0275prov=te({token:t,factory:t.\u0275fac})}return t})();function ex(t){let e=/^(?:(?![^:@]+:[^:@\/]*@)([^:\/?#.]+):)?(?:\/\/)?((?:(([^:@]*)(?::([^:@]*))?)?@)?([^:\/?#]*)(?::(\d*))?)(((\/(?:[^?#](?![^?#\/]*\.[^?#\/.]+(?:[?#]|$)))*\/?)?([^?#\/]*))(?:\?([^#]*))?(?:#(.*))?)/.exec(t),i={source:"",protocol:"",authority:"",userInfo:"",user:"",password:"",host:"",port:"",relative:"",path:"",directory:"",file:"",query:"",anchor:""},a=Object.keys(i),r=a.length;for(;r--;)i[a[r]]=e&&e[r]||"";return i}function tx(t){return t.replace(/\/$/,"")}var _d=class{inMemDbService;config=new js;db={};dbReadySubject;passThruBackend;requestInfoUtils=this.getRequestInfoUtils();constructor(n,e={}){this.inMemDbService=n;let i=this.getLocation("/");this.config.host=i.host,this.config.rootPath=i.path,Object.assign(this.config,e)}get dbReady(){return this.dbReadySubject||(this.dbReadySubject=new ni(!1),this.resetDb()),this.dbReadySubject.asObservable().pipe(zc(n=>n))}handleRequest(n){return this.dbReady.pipe(Sr(()=>this.handleRequest_(n)))}handleRequest_(n){let e=n.urlWithParams?n.urlWithParams:n.url,i=this.bind("parseRequestUrl"),a=i&&i(e,this.requestInfoUtils)||this.parseRequestUrl(e),r=a.collectionName,o=this.db[r],s={req:n,apiBase:a.apiBase,collection:o,collectionName:r,headers:this.createHeaders({"Content-Type":"application/json"}),id:this.parseId(o,r,a.id),method:this.getRequestMethod(n),query:a.query,resourceUrl:a.resourceUrl,url:e,utils:this.requestInfoUtils},l;if(/commands\/?$/i.test(s.apiBase))return this.commands(s);let c=this.bind(s.method);if(c){let d=c(s);if(d)return d}return this.db[r]?this.createResponse$(()=>this.collectionHandler(s)):this.config.passThruUnknownUrl?this.getPassThruBackend().handle(n):(l=this.createErrorResponseOptions(e,ge.NOT_FOUND,`Collection '${r}' not found`),this.createResponse$(()=>l))}addDelay(n){let e=this.config.delay;return e===0?n:X0(n,e||500)}applyQuery(n,e){let i=[],a=this.config.caseSensitiveSearch?void 0:"i";e.forEach((o,s)=>{o.forEach(l=>i.push({name:s,rx:new RegExp(decodeURI(l),a)}))});let r=i.length;return r?n.filter(o=>{let s=!0,l=r;for(;s&&l;){l-=1;let c=i[l];s=c.rx.test(o[c.name])}return s}):n}bind(n){let e=this.inMemDbService[n];return e?e.bind(this.inMemDbService):void 0}bodify(n){return this.config.dataEncapsulation?{data:n}:n}clone(n){return JSON.parse(JSON.stringify(n))}collectionHandler(n){let e;switch(n.method){case"get":e=this.get(n);break;case"post":e=this.post(n);break;case"put":e=this.put(n);break;case"delete":e=this.delete(n);break;default:e=this.createErrorResponseOptions(n.url,ge.METHOD_NOT_ALLOWED,"Method not allowed");break}let i=this.bind("responseInterceptor");return i?i(e,n):e}commands(n){let e=n.collectionName.toLowerCase(),i=n.method,a={url:n.url};switch(e){case"resetdb":return a.status=ge.NO_CONTENT,this.resetDb(n).pipe(Sr(()=>this.createResponse$(()=>a,!1)));case"config":if(i==="get")a.status=ge.OK,a.body=this.clone(this.config);else{let r=this.getJsonBody(n.req);Object.assign(this.config,r),this.passThruBackend=void 0,a.status=ge.NO_CONTENT}break;default:a=this.createErrorResponseOptions(n.url,ge.INTERNAL_SERVER_ERROR,`Unknown command "${e}"`)}return this.createResponse$(()=>a,!1)}createErrorResponseOptions(n,e,i){return{body:{error:`${i}`},url:n,headers:this.createHeaders({"Content-Type":"application/json"}),status:e}}createResponse$(n,e=!0){let i=this.createResponseOptions$(n),a=this.createResponse$fromResponseOptions$(i);return e?this.addDelay(a):a}createResponseOptions$(n){return new Nt(e=>{let i;try{i=n()}catch(r){let o=r.message||r;i=this.createErrorResponseOptions("",ge.INTERNAL_SERVER_ERROR,`${o}`)}let a=i.status;try{i.statusText=a!=null?Q0(a):void 0}catch{}return a!=null&&J0(a)?(e.next(i),e.complete()):e.error(i),()=>{}})}delete({collection:n,collectionName:e,headers:i,id:a,url:r}){if(a==null)return this.createErrorResponseOptions(r,ge.NOT_FOUND,`Missing "${e}" id`);let o=this.removeById(n,a);return{headers:i,status:o||!this.config.delete404?ge.NO_CONTENT:ge.NOT_FOUND}}findById(n,e){return n.find(i=>i.id===e)}genId(n,e){let i=this.bind("genId");if(i){let a=i(n,e);if(a!=null)return a}return this.genIdDefault(n,e)}genIdDefault(n,e){if(!this.isCollectionIdNumeric(n,e))throw new Error(`Collection '${e}' id type is non-numeric or unknown. Can only generate numeric ids.`);let i=0;return n.reduce((a,r)=>{i=Math.max(i,typeof r.id=="number"?r.id:i)},void 0),i+1}get({collection:n,collectionName:e,headers:i,id:a,query:r,url:o}){let s=n;return a!=null&&a!==""?s=this.findById(n,a):r&&(s=this.applyQuery(n,r)),s?{body:this.bodify(this.clone(s)),headers:i,status:ge.OK}:this.createErrorResponseOptions(o,ge.NOT_FOUND,`'${e}' with id='${a}' not found`)}getLocation(n){if(!n.startsWith("http")){let e=typeof document>"u"?void 0:document,i=e?e.location.protocol+"//"+e.location.host:"http://fake";n=n.startsWith("/")?i+n:i+"/"+n}return ex(n)}getPassThruBackend(){return this.passThruBackend?this.passThruBackend:this.passThruBackend=this.createPassThruBackend()}getRequestInfoUtils(){return{createResponse$:this.createResponse$.bind(this),findById:this.findById.bind(this),isCollectionIdNumeric:this.isCollectionIdNumeric.bind(this),getConfig:()=>this.config,getDb:()=>this.db,getJsonBody:this.getJsonBody.bind(this),getLocation:this.getLocation.bind(this),getPassThruBackend:this.getPassThruBackend.bind(this),parseRequestUrl:this.parseRequestUrl.bind(this)}}indexOf(n,e){return n.findIndex(i=>i.id===e)}parseId(n,e,i){if(!this.isCollectionIdNumeric(n,e))return i;let a=parseFloat(i);return isNaN(a)?i:a}isCollectionIdNumeric(n,e){return!!(n&&n[0])&&typeof n[0].id=="number"}parseRequestUrl(n){try{let e=this.getLocation(n),i=(this.config.rootPath||"").length,a="";e.host!==this.config.host&&(i=1,a=e.protocol+"//"+e.host+"/");let o=e.path.substring(i).split("/"),s=0,l;this.config.apiBase==null?l=o[s++]:(l=tx(this.config.apiBase.trim()),l?s=l.split("/").length:s=0),l+="/";let c=o[s++];c=c&&c.split(".")[0];let d=o[s++],m=this.createQueryMap(e.query),u=a+l+c+"/";return{apiBase:l,collectionName:c,id:d,query:m,resourceUrl:u}}catch(e){let i=`unable to parse url '${n}'; original error: ${e.message}`;throw new Error(i)}}post({collection:n,collectionName:e,headers:i,id:a,req:r,resourceUrl:o,url:s}){let l=this.clone(this.getJsonBody(r));if(l.id==null)try{l.id=a||this.genId(n,e)}catch(m){let u=m.message||"";return/id type is non-numeric/.test(u)?this.createErrorResponseOptions(s,ge.UNPROCESSABLE_ENTRY,u):this.createErrorResponseOptions(s,ge.INTERNAL_SERVER_ERROR,`Failed to generate new id for '${e}'`)}if(a&&a!==l.id)return this.createErrorResponseOptions(s,ge.BAD_REQUEST,"Request id does not match item.id");a=l.id;let c=this.indexOf(n,a),d=this.bodify(l);return c===-1?(n.push(l),i.set("Location",o+"/"+a),{headers:i,body:d,status:ge.CREATED}):this.config.post409?this.createErrorResponseOptions(s,ge.CONFLICT,`'${e}' item with id='${a} exists and may not be updated with POST; use PUT instead.`):(n[c]=l,this.config.post204?{headers:i,status:ge.NO_CONTENT}:{headers:i,body:d,status:ge.OK})}put({collection:n,collectionName:e,headers:i,id:a,req:r,url:o}){let s=this.clone(this.getJsonBody(r));if(s.id==null)return this.createErrorResponseOptions(o,ge.NOT_FOUND,`Missing '${e}' id`);if(a&&a!==s.id)return this.createErrorResponseOptions(o,ge.BAD_REQUEST,`Request for '${e}' id does not match item.id`);a=s.id;let l=this.indexOf(n,a),c=this.bodify(s);return l>-1?(n[l]=s,this.config.put204?{headers:i,status:ge.NO_CONTENT}:{headers:i,body:c,status:ge.OK}):this.config.put404?this.createErrorResponseOptions(o,ge.NOT_FOUND,`'${e}' item with id='${a} not found and may not be created with PUT; use POST instead.`):(n.push(s),{headers:i,body:c,status:ge.CREATED})}removeById(n,e){let i=this.indexOf(n,e);return i>-1?(n.splice(i,1),!0):!1}resetDb(n){this.dbReadySubject&&this.dbReadySubject.next(!1);let e=this.inMemDbService.createDb(n);return(e instanceof Nt?e:typeof e.then=="function"?Ku(e):Ue(e)).pipe(zc()).subscribe(a=>{this.db=a,this.dbReadySubject&&this.dbReadySubject.next(!0)}),this.dbReady}},nx=(()=>{class t extends _d{xhrFactory;injector=p(re);constructor(e,i,a){super(e,i),this.xhrFactory=a}handle(e){try{return this.handleRequest(e)}catch(i){let a=i.message||i,r=this.createErrorResponseOptions(e.url,ge.INTERNAL_SERVER_ERROR,`${a}`);return this.createResponse$(()=>r)}}getJsonBody(e){return e.body}getRequestMethod(e){return(e.method||"get").toLowerCase()}createHeaders(e){return new Yt(e)}createQueryMap(e){let i=new Map;if(e){let a=new qt({fromString:e});a.keys().forEach(r=>i.set(r,a.getAll(r)||[]))}return i}createResponse$fromResponseOptions$(e){return e.pipe(Me(i=>new fi(i)))}createPassThruBackend(){try{return ps(this.injector,()=>new ud(this.xhrFactory))}catch(e){throw e.message="Cannot create passThru404 backend; "+(e.message||""),e}}static \u0275fac=function(i){return new(i||t)(B(Yr),B(js,8),B(Oi))};static \u0275prov=te({token:t,factory:t.\u0275fac})}return t})();function ix(){return new nx(p(Yr),p(js),p(Oi))}var pf=(()=>{class t{static forRoot(e,i){return{ngModule:t,providers:[{provide:Yr,useClass:e},{provide:js,useValue:i},{provide:Kr,useFactory:ix}]}}static forFeature(e,i){return t.forRoot(e,i)}static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({})}return t})();var uf={asyncapi:"3.1.0",info:{title:"Springwolf example project - AMQP",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-amqp-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com","x-phone":"+49 123 456789"},license:{name:"Apache License 2.0","x-desc":"some description"},"x-api-audience":"company-internal","x-generator":"springwolf"},defaultContentType:"application/json",servers:{"amqp-server":{host:"amqp:5672",protocol:"amqp"}},channels:{"CRUD-topic-exchange-1":{address:"CRUD-topic-exchange-1",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"CRUD-topic-exchange-1",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"CRUD-topic-exchange-2":{address:"CRUD-topic-exchange-2",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"CRUD-topic-exchange-2",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"another-queue",durable:!0,exclusive:!1,autoDelete:!0,vhost:"/"},bindingVersion:"0.3.0"}}},"example-bindings-queue":{address:"example-bindings-queue",bindings:{amqp:{is:"queue",queue:{name:"example-bindings-queue",durable:!0,exclusive:!1,autoDelete:!0,vhost:"/"},bindingVersion:"0.3.0"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"example-queue",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"example-topic-exchange":{address:"example-topic-exchange",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},bindings:{}},"example-topic-exchange_example-topic-routing-key":{address:"example-topic-exchange_example-topic-routing-key",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"example-topic-exchange",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"multi-payload-queue":{address:"multi-payload-queue",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"multi-payload-queue",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-create":{address:"queue-create",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}},bindings:{amqp:{is:"queue",queue:{name:"queue-create",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-delete":{address:"queue-delete",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}},bindings:{amqp:{is:"queue",queue:{name:"queue-delete",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-read":{address:"queue-read",bindings:{amqp:{is:"queue",queue:{name:"queue-read",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-update":{address:"queue-update",bindings:{amqp:{is:"queue",queue:{name:"queue-update",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},SpringRabbitListenerDefaultHeaders:{title:"SpringRabbitListenerDefaultHeaders",type:"object",properties:{},examples:[{}]},"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},description:"Generic payload model",examples:[{genericValue:{someEnum:"FOO2",someLong:5,someString:"some string value"}}],required:["genericValue"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{type:"integer",description:"Generic Payload field",format:"int64"}},description:"Generic payload model",examples:[{genericValue:0}],required:["genericValue"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{type:"string",description:"Generic Payload field"}},description:"Generic payload model",examples:[{genericValue:"string"}],required:["genericValue"]}},messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto",title:"GenericPayloadDtoExamplePayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long",title:"GenericPayloadDtoLong",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String",title:"GenericPayloadDtoString",bindings:{amqp:{bindingVersion:"0.3.0"}}}}},operations:{"CRUD-topic-exchange-1_receive_bindingsUpdate":{action:"receive",channel:{$ref:"#/channels/CRUD-topic-exchange-1"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/CRUD-topic-exchange-1/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"CRUD-topic-exchange-2_receive_bindingsRead":{action:"receive",channel:{$ref:"#/channels/CRUD-topic-exchange-2"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/CRUD-topic-exchange-2/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"example-topic-exchange_example-topic-routing-key_receive_bindingsExample":{action:"receive",channel:{$ref:"#/channels/example-topic-exchange_example-topic-routing-key"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-topic-exchange_example-topic-routing-key/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"example-topic-exchange_send_sendMessage":{action:"send",channel:{$ref:"#/channels/example-topic-exchange"},title:"example-topic-exchange_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{amqp:{expiration:0,cc:[],priority:0,deliveryMode:1,mandatory:!1,bcc:[],timestamp:!1,ack:!1,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-topic-exchange/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}]},"multi-payload-queue_receive_bindingsBeanExample":{action:"receive",channel:{$ref:"#/channels/multi-payload-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/multi-payload-queue/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"},{$ref:"#/channels/multi-payload-queue/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"queue-create_receive_queuesToDeclareCreate":{action:"receive",channel:{$ref:"#/channels/queue-create"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/queue-create/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}]},"queue-delete_receive_queuesToDeclareDelete":{action:"receive",channel:{$ref:"#/channels/queue-delete"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/queue-delete/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}]}}};var hf={asyncapi:"3.1.0",info:{title:"Springwolf example project - Cloud Stream",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-cloud-stream-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"another-topic":{address:"another-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"biconsumer-topic":{address:"biconsumer-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"bifunction-output-topic":{address:"bifunction-output-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"bifunction-topic":{address:"bifunction-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"consumer-class-topic":{address:"consumer-class-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"consumer-topic":{address:"consumer-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"example-topic":{address:"example-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"google-pubsub-topic":{address:"google-pubsub-topic",messages:{GooglePubSubPayloadDto:{$ref:"#/components/messages/GooglePubSubPayloadDto"}},bindings:{googlepubsub:{messageStoragePolicy:{},schemaSettings:{encoding:"BINARY",name:"project/test"},bindingVersion:"0.2.0"}}}},components:{schemas:{AnotherPayloadDto:{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},ExamplePayloadDto:{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]},GooglePubSubPayloadDto:{title:"GooglePubSubPayloadDto",type:"object",properties:{someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Google pubsub payload model",examples:[{someLong:5,someString:"some string value"}],required:["someString"]},HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]}},messages:{AnotherPayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/AnotherPayloadDto"}},name:"AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{kafka:{}}},ExamplePayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/ExamplePayloadDto"}},name:"ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{kafka:{}}},GooglePubSubPayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/GooglePubSubPayloadDto"}},name:"GooglePubSubPayloadDto",title:"GooglePubSubPayloadDto",bindings:{googlepubsub:{schema:{name:"project/test"},bindingVersion:"0.2.0"}}}}},operations:{"another-topic_send_process":{action:"send",channel:{$ref:"#/channels/another-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/another-topic/messages/AnotherPayloadDto"}]},"biconsumer-topic_receive_biConsumerMethod":{action:"receive",channel:{$ref:"#/channels/biconsumer-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/biconsumer-topic/messages/AnotherPayloadDto"}]},"bifunction-output-topic_send_biProcess":{action:"send",channel:{$ref:"#/channels/bifunction-output-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/bifunction-output-topic/messages/AnotherPayloadDto"}]},"bifunction-topic_receive_biProcess":{action:"receive",channel:{$ref:"#/channels/bifunction-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/bifunction-topic/messages/ExamplePayloadDto"}]},"consumer-class-topic_receive_ConsumerClass":{action:"receive",channel:{$ref:"#/channels/consumer-class-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/consumer-class-topic/messages/ExamplePayloadDto"}]},"consumer-topic_receive_consumerMethod":{action:"receive",channel:{$ref:"#/channels/consumer-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/consumer-topic/messages/AnotherPayloadDto"}]},"example-topic_receive_process":{action:"receive",channel:{$ref:"#/channels/example-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/example-topic/messages/ExamplePayloadDto"}]},"google-pubsub-topic_receive_googlePubSubConsumerMethod":{action:"receive",channel:{$ref:"#/channels/google-pubsub-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/google-pubsub-topic/messages/GooglePubSubPayloadDto"}]}}};var ff={asyncapi:"3.1.0",info:{title:"Springwolf example project - Kafka",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-kafka-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"another-topic":{address:"another-topic",messages:{"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"avro-topic":{address:"avro-topic",messages:{"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"bean-ref-topic":{address:"bean-ref-topic",messages:{"java.lang.Object":{$ref:"#/components/messages/java.lang.Object"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"example-topic":{address:"example-topic",messages:{"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"integer-topic":{address:"integer-topic",messages:{"java.lang.Integer":{$ref:"#/components/messages/java.lang.Integer"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"multi-payload-topic":{address:"multi-payload-topic",messages:{"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"},"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},"javax.money.MonetaryAmount":{$ref:"#/components/messages/javax.money.MonetaryAmount"}},bindings:{kafka:{topic:"multi-payload-topic",partitions:3,replicas:1,topicConfiguration:{"cleanup.policy":["compact","delete"],"retention.ms":864e5,"retention.bytes":-1,"delete.retention.ms":864e5,"max.message.bytes":1048588},bindingVersion:"0.5.0"}}},"no-payload-used-topic":{address:"no-payload-used-topic",messages:{PayloadNotUsed:{$ref:"#/components/messages/PayloadNotUsed"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"nullable-topic":{address:"nullable-topic",messages:{"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"protobuf-topic":{address:"protobuf-topic",messages:{"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"string-topic":{address:"string-topic",messages:{"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"},"java.lang.String":{$ref:"#/components/messages/java.lang.String"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"topic-defined-via-asyncPublisher-annotation":{address:"topic-defined-via-asyncPublisher-annotation",messages:{"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}},servers:[{$ref:"#/servers/kafka-server"}],bindings:{}},"vehicle-topic":{address:"vehicle-topic",messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"xml-topic":{address:"xml-topic",messages:{"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"yaml-topic":{address:"yaml-topic",messages:{"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"There can be headers, but they are not explicitly documented.",title:"HeadersNotDocumented",type:"object"}},HeadersNotUsed:{title:"HeadersNotUsed",type:"object",properties:{},description:"No headers are present.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"No headers are present.",title:"HeadersNotUsed",type:"object"}},PayloadNotUsed:{title:"PayloadNotUsed",type:"object",properties:{},description:"No payload specified",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"No payload specified",title:"PayloadNotUsed",type:"object"}},SpringDefaultHeaderAndCloudEvent:{title:"SpringDefaultHeaderAndCloudEvent",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"},ce_id:{title:"ce_id",type:"string",description:"CloudEvent Id Header",examples:["2c60089e-6f39-459d-8ced-2d6df7e4c03a"],const:"2c60089e-6f39-459d-8ced-2d6df7e4c03a"},ce_source:{title:"ce_source",type:"string",description:"CloudEvent Source Header",examples:["http://localhost"],const:"http://localhost"},ce_specversion:{title:"ce_specversion",type:"string",description:"CloudEvent Spec Version Header",examples:["1.0"],const:"1.0"},ce_subject:{title:"ce_subject",type:"string",description:"CloudEvent Subject Header",examples:["Springwolf example project - Kafka"],const:"Springwolf example project - Kafka"},ce_time:{title:"ce_time",type:"string",description:"CloudEvent Time Header",format:"date-time",examples:["2023-10-28T20:01:23+00:00"],const:"2023-10-28T20:01:23+00:00"},ce_type:{title:"ce_type",type:"string",description:"CloudEvent Payload Type Header",examples:["NestedPayloadDto.v1"],const:"NestedPayloadDto.v1"},"content-type":{title:"content-type",type:"string",description:"CloudEvent Content-Type Header",examples:["application/json"],const:"application/json"}},description:"Spring __TypeId__ and CloudEvent Headers",examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto",ce_id:"2c60089e-6f39-459d-8ced-2d6df7e4c03a",ce_source:"http://localhost",ce_specversion:"1.0",ce_subject:"Springwolf example project - Kafka",ce_time:"2023-10-28T20:01:23+00:00",ce_type:"NestedPayloadDto.v1","content-type":"application/json"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Spring __TypeId__ and CloudEvent Headers",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"},ce_id:{const:"2c60089e-6f39-459d-8ced-2d6df7e4c03a",description:"CloudEvent Id Header",title:"ce_id",type:"string"},ce_source:{const:"http://localhost",description:"CloudEvent Source Header",title:"ce_source",type:"string"},ce_specversion:{const:"1.0",description:"CloudEvent Spec Version Header",title:"ce_specversion",type:"string"},ce_subject:{const:"Springwolf example project - Kafka",description:"CloudEvent Subject Header",title:"ce_subject",type:"string"},ce_time:{const:"2023-10-28T20:01:23+00:00",description:"CloudEvent Time Header",format:"date-time",title:"ce_time",type:"string"},ce_type:{const:"NestedPayloadDto.v1",description:"CloudEvent Payload Type Header",title:"ce_type",type:"string"},"content-type":{const:"application/json",description:"CloudEvent Content-Type Header",title:"content-type",type:"string"}},title:"SpringDefaultHeaderAndCloudEvent",type:"object"}},"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto":{title:"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"],const:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto",type:"object"}},"SpringKafkaDefaultHeaders-AnotherPayloadDto":{title:"SpringKafkaDefaultHeaders-AnotherPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-AnotherTopic":{title:"SpringKafkaDefaultHeaders-AnotherTopic",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Type ID"},my_uuid_field:{title:"my_uuid_field",type:"string",description:"Event identifier",format:"uuid",enum:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"],examples:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"]}},examples:[{__TypeId__:"string",my_uuid_field:"00000000-0000-0000-0000-000000000000"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Type ID",title:"__TypeId__",type:"string"},my_uuid_field:{description:"Event identifier",enum:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"],format:"uuid",title:"my_uuid_field",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherTopic",type:"object"}},"SpringKafkaDefaultHeaders-ExamplePayloadDto-1810898840":{title:"SpringKafkaDefaultHeaders-ExamplePayloadDto-1810898840",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},kafka_offset:{type:"integer",format:"int32",examples:[0]},kafka_receivedMessageKey:{type:"string",examples:['"string"']}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto",kafka_offset:0,kafka_receivedMessageKey:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"},kafka_offset:{format:"int32",type:"integer"},kafka_receivedMessageKey:{type:"string"}},title:"SpringKafkaDefaultHeaders-ExamplePayloadDto-1810898840",type:"object"}},"SpringKafkaDefaultHeaders-Message":{title:"SpringKafkaDefaultHeaders-Message",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"],const:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-Message",type:"object"}},"SpringKafkaDefaultHeaders-MonetaryAmount":{title:"SpringKafkaDefaultHeaders-MonetaryAmount",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["javax.money.MonetaryAmount"],const:"javax.money.MonetaryAmount"}},examples:[{__TypeId__:"javax.money.MonetaryAmount"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"javax.money.MonetaryAmount",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-MonetaryAmount",type:"object"}},"SpringKafkaDefaultHeaders-Object":{title:"SpringKafkaDefaultHeaders-Object",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["java.lang.Object"],const:"java.lang.Object"}},examples:[{__TypeId__:"java.lang.Object"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"java.lang.Object",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-Object",type:"object"}},"SpringKafkaDefaultHeaders-PayloadNotUsed":{title:"SpringKafkaDefaultHeaders-PayloadNotUsed",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["PayloadNotUsed"],const:"PayloadNotUsed"}},examples:[{__TypeId__:"PayloadNotUsed"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"PayloadNotUsed",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-PayloadNotUsed",type:"object"}},"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto":{title:"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-VehicleBase":{title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],const:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object"}},"SpringKafkaDefaultHeaders-XmlPayloadDto":{title:"SpringKafkaDefaultHeaders-XmlPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-XmlPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-YamlPayloadDto":{title:"SpringKafkaDefaultHeaders-YamlPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"],const:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-YamlPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-integer":{title:"SpringKafkaDefaultHeaders-integer",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["java.lang.Integer"],const:"java.lang.Integer"}},examples:[{__TypeId__:"java.lang.Integer"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"java.lang.Integer",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-integer",type:"object"}},"SpringKafkaDefaultHeaders-string":{title:"SpringKafkaDefaultHeaders-string",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["java.lang.String"],const:"java.lang.String"}},examples:[{__TypeId__:"java.lang.String"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"java.lang.String",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-string",type:"object"}},"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{type:"string",description:"Payload description using @Schema annotation and @AsyncApiPayload within envelope class",maxLength:100,examples:['"string"'],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Payload description using @Schema annotation and @AsyncApiPayload within envelope class",maxLength:100,type:"string"}},"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{title:"AnotherPayloadAvroDto",type:"object",properties:{examplePayloadAvroDto:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.avro.ExamplePayloadAvroDto"},someEnum:{title:"ExampleEnum",type:"string",enum:["FOO1","FOO2","FOO3"]}},examples:[{examplePayloadAvroDto:{someLong:0,someString:"string"},someEnum:"FOO1"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{examplePayloadAvroDto:{properties:{someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"ExamplePayloadAvroDto",type:"object"},someEnum:{enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"}},title:"AnotherPayloadAvroDto",type:"object"}},"io.github.springwolf.examples.kafka.dto.avro.ExamplePayloadAvroDto":{title:"ExamplePayloadAvroDto",type:"object",properties:{someLong:{type:"integer",format:"int64"},someString:{type:"string"}},examples:[{someLong:0,someString:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"ExamplePayloadAvroDto",type:"object"}},"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{title:"Message",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",enum:["FOO1","FOO2","FOO3","UNRECOGNIZED"]},someLong:{type:"integer",format:"int64"},someString:{type:"string"}},examples:[{someEnum:"FOO1",someLong:0,someString:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someEnum:{enum:["FOO1","FOO2","FOO3","UNRECOGNIZED"],title:"ExampleEnum",type:"string"},someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"Message",type:"object"}},"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Another payload model",properties:{example:{description:`Example payload model demonstrating markdown text styling:
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
`],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someEnum:{enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"YamlPayloadDto",type:"string"}},"io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower":{title:"EnginePower",type:"object",properties:{hp:{type:"integer",format:"int32"},torque:{type:"integer",format:"int32"}},examples:[{hp:0,torque:0}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{hp:{format:"int32",type:"integer"},torque:{}},title:"EnginePower",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{discriminator:"vehicleType",title:"VehicleBase",type:"object",properties:{enginePower:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower"},powerSource:{type:"string"},topSpeed:{type:"integer",format:"int32"},vehicleType:{type:"string"}},description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],oneOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto"},{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{allOf:[{},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto":{title:"VehicleElectricPayloadDto",type:"object",description:"Electric vehicle implementation of VehicleBase",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{batteryCapacity:{type:"integer",format:"int32"},chargeTime:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{},{allOf:[{},{properties:{fuelCapacity:{format:"int32",type:"integer"}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{batteryCapacity:{},chargeTime:{}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto":{title:"VehicleGasolinePayloadDto",type:"object",description:"Gasoline vehicle implementation of VehicleBase",examples:[{enginePower:{hp:0,torque:0},fuelCapacity:0,powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{fuelCapacity:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}},"java.lang.Integer":{type:"integer",format:"int32",examples:[0],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",format:"int32",type:"integer"}},"java.lang.Object":{type:"object",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",type:"object"}},"java.lang.String":{type:"string",examples:['"string"'],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",type:"string"}},"javax.money.MonetaryAmount":{type:"object",properties:{amount:{type:"number",exclusiveMinimum:.01,examples:[99.99]},currency:{type:"string",examples:["USD"]}},examples:[{amount:99.99,currency:"USD"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{amount:{exclusiveMinimum:.01,type:"number"},currency:{type:"string"}},type:"object"}}},messages:{PayloadNotUsed:{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-PayloadNotUsed"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{title:"PayloadNotUsed",type:"object",properties:{},description:"No payload specified"}},name:"PayloadNotUsed",title:"PayloadNotUsed",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{headers:{$ref:"#/components/schemas/HeadersNotUsed"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"}},name:"StringPayload",title:"StringEnvelope",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}},contentType:"application/avro",name:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto",title:"AnotherPayloadAvroDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-Message"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}},name:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message",title:"Message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-AnotherTopic"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-ExamplePayloadDto-1810898840"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto":{headers:{$ref:"#/components/schemas/SpringDefaultHeaderAndCloudEvent"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto",title:"NestedPayloadDto",bindings:{kafka:{key:{type:"string",description:"Kafka Producer Message Key",examples:["example-key"]},bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto",title:"RequiredAndNullablePayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}},contentType:"text/xml",name:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto",title:"XmlPayloadDto",description:"Showcases a xml based message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}},contentType:"application/yaml",name:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto",title:"YamlPayloadDto",description:"Showcases a yaml based message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-VehicleBase"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},name:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",title:"VehicleBase",bindings:{kafka:{bindingVersion:"0.5.0"}}},"java.lang.Integer":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-integer"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{type:"integer",format:"int32",examples:[0]}},name:"java.lang.Integer",title:"integer",bindings:{kafka:{bindingVersion:"0.5.0"}}},"java.lang.Object":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-Object"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{type:"object",examples:[{}]}},name:"java.lang.Object",title:"Object",bindings:{kafka:{bindingVersion:"0.5.0"}}},"java.lang.String":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-string"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{type:"string",examples:['"string"']}},name:"java.lang.String",title:"string",bindings:{kafka:{bindingVersion:"0.5.0"}}},"javax.money.MonetaryAmount":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-MonetaryAmount"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/javax.money.MonetaryAmount"}},name:"javax.money.MonetaryAmount",title:"MonetaryAmount",bindings:{kafka:{key:{type:"string",description:"Kafka Consumer Message Key",examples:["example-key"]},bindingVersion:"0.5.0"}}}}},operations:{"another-topic_receive_receiveAnotherPayloadBatched":{action:"receive",channel:{$ref:"#/channels/another-topic"},bindings:{kafka:{groupId:{type:"string",const:"example-group-id"},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}]},"another-topic_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-topic"},title:"another-topic_send",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}]},"avro-topic_receive_receiveExampleAvroPayload":{action:"receive",channel:{$ref:"#/channels/avro-topic"},title:"avro-topic_receive",description:"Requires a running kafka-schema-registry. See docker-compose.yml to start it",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/avro-topic/messages/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}]},"bean-ref-topic_receive_listen":{action:"receive",channel:{$ref:"#/channels/bean-ref-topic"},bindings:{kafka:{groupId:{type:"string",const:"bean-ref-group"},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/bean-ref-topic/messages/java.lang.Object"}]},"example-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/example-topic/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}]},"integer-topic_receive_receiveIntegerPayload":{action:"receive",channel:{$ref:"#/channels/integer-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/integer-topic/messages/java.lang.Integer"}]},"multi-payload-topic_receive_ExampleClassLevelKafkaListener":{action:"receive",channel:{$ref:"#/channels/multi-payload-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/multi-payload-topic/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},{$ref:"#/channels/multi-payload-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"},{$ref:"#/channels/multi-payload-topic/messages/javax.money.MonetaryAmount"}]},"multi-payload-topic_receive_receiveMonetaryAmount":{action:"receive",channel:{$ref:"#/channels/multi-payload-topic"},title:"multi-payload-topic_receive",description:"Override description in the AsyncListener annotation with servers at kafka:29092",bindings:{kafka:{groupId:{type:"string",const:"foo-groupId"},clientId:{type:"string",const:"foo-clientId"},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/multi-payload-topic/messages/javax.money.MonetaryAmount"}]},"no-payload-used-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/no-payload-used-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/no-payload-used-topic/messages/PayloadNotUsed"}]},"nullable-topic_receive_receiveNullablePayload":{action:"receive",channel:{$ref:"#/channels/nullable-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/nullable-topic/messages/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}]},"protobuf-topic_receive_receiveExampleProtobufPayload":{action:"receive",channel:{$ref:"#/channels/protobuf-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/protobuf-topic/messages/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}]},"string-topic_receive_receiveStringPayload":{action:"receive",channel:{$ref:"#/channels/string-topic"},title:"string-topic_receive",description:"Final classes (like String) can be documented using an envelope class and the @AsyncApiPayload annotation.",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/string-topic/messages/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"},{$ref:"#/channels/string-topic/messages/java.lang.String"}]},"topic-defined-via-asyncPublisher-annotation_send_sendMessage":{action:"send",channel:{$ref:"#/channels/topic-defined-via-asyncPublisher-annotation"},title:"topic-defined-via-asyncPublisher-annotation_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{kafka:{clientId:{type:"string",const:"foo-clientId"},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/topic-defined-via-asyncPublisher-annotation/messages/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}]},"vehicle-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/vehicle-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/vehicle-topic/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}]},"xml-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/xml-topic"},title:"xml-topic_receive",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/xml-topic/messages/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}]},"yaml-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/yaml-topic"},title:"yaml-topic_receive",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/yaml-topic/messages/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}]}}};var gf={asyncapi:"3.1.0",info:{title:"Springwolf example project - Kafka",version:"1.0.0",description:"This group only contains endpoints that are related to vehicles.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-apitype":"internal","x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"vehicle-topic":{address:"vehicle-topic",messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},bindings:{kafka:{bindingVersion:"0.5.0"}}}},components:{schemas:{"SpringKafkaDefaultHeaders-VehicleBase":{title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",examples:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],const:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{const:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",description:"Spring Type Id Header",title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower":{title:"EnginePower",type:"object",properties:{hp:{type:"integer",format:"int32"},torque:{type:"integer",format:"int32"}},examples:[{hp:0,torque:0}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{hp:{format:"int32",type:"integer"},torque:{}},title:"EnginePower",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{discriminator:"vehicleType",title:"VehicleBase",type:"object",properties:{enginePower:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower"},powerSource:{type:"string"},topSpeed:{type:"integer",format:"int32"},vehicleType:{type:"string"}},description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],oneOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto"},{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{allOf:[{},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto":{title:"VehicleElectricPayloadDto",type:"object",description:"Electric vehicle implementation of VehicleBase",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{batteryCapacity:{type:"integer",format:"int32"},chargeTime:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{},{allOf:[{},{properties:{fuelCapacity:{format:"int32",type:"integer"}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{batteryCapacity:{},chargeTime:{}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto":{title:"VehicleGasolinePayloadDto",type:"object",description:"Gasoline vehicle implementation of VehicleBase",examples:[{enginePower:{hp:0,torque:0},fuelCapacity:0,powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{fuelCapacity:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}}},messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-VehicleBase"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},name:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",title:"VehicleBase",bindings:{kafka:{bindingVersion:"0.5.0"}}}}},operations:{"vehicle-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/vehicle-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/vehicle-topic/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}]}}};var bf={initialConfig:{showBindings:!0,showHeaders:!0},groups:[{name:"Only Vehicles"}]};var vf={asyncapi:"3.1.0",info:{title:"Springwolf example project - JMS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-jms-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"jms-server":{host:"tcp://activemq:61616",protocol:"jms"}},channels:{"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}},bindings:{jms:{bindingVersion:"0.0.1"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}},bindings:{jms:{bindingVersion:"0.0.1"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{jms:{bindingVersion:"0.0.1"}}},"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{jms:{bindingVersion:"0.0.1"}}}}},operations:{"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{jms:{}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}]},"another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-queue"},title:"another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{jms:{"internal-field":"customValue",nested:{key:"nestedValue"}}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{jms:{}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}]}}};var _f={asyncapi:"3.1.0",info:{title:"Springwolf example project - SNS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-sns-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"sns-server":{host:"http://localhost:4566",protocol:"sns"}},channels:{"another-topic":{address:"another-topic",messages:{"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}},bindings:{}},"example-topic":{address:"example-topic",messages:{"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}},bindings:{}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"There can be headers, but they are not explicitly documented.",title:"HeadersNotDocumented",type:"object"}},"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Another payload model",properties:{example:{description:"Example payload model",properties:{someEnum:{description:"Some enum field",enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{description:"Some long field",format:"int64",minimum:0,type:"integer"},someString:{description:"Some string field",type:"string"}},required:["someEnum","someString"],title:"ExamplePayloadDto",type:"object"},foo:{description:"Foo field",maxLength:100,type:"string"}},required:["example"],title:"AnotherPayloadDto",type:"object"}},"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Example payload model",properties:{someEnum:{description:"Some enum field",enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{description:"Some long field",format:"int64",minimum:0,type:"integer"},someString:{description:"Some string field",type:"string"}},required:["someEnum","someString"],title:"ExamplePayloadDto",type:"object"}}},messages:{"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{sns:{}}},"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{sns:{}}}}},operations:{"another-topic_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-topic"},title:"another-topic_receive",description:"Auto-generated description",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}]},"another-topic_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-topic"},title:"another-topic_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}]},"example-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-topic"},title:"example-topic_receive",description:"Auto-generated description",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/example-topic/messages/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}]}}};var yf={asyncapi:"3.1.0",info:{title:"Springwolf example project - SQS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-sqs-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"sqs-server":{host:"http://localhost:4566",protocol:"sqs"}},channels:{"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}},bindings:{sqs:{queue:{name:"another-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600},bindingVersion:"0.2.0"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}},bindings:{sqs:{queue:{name:"example-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600},bindingVersion:"0.2.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{sqs:{}}},"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{sqs:{}}}}},operations:{"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{sqs:{queues:[{name:"another-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}]},"another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-queue"},title:"another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{sqs:{queues:[{name:"queue-name",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{sqs:{queues:[{name:"example-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}]}}};var xf={asyncapi:"3.1.0",info:{title:"Springwolf example project - STOMP",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-stomp-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{stomp:{host:"localhost:8080/myendpoint",protocol:"stomp"}},channels:{"_app_queue_another-queue":{address:"/app/queue/another-queue",messages:{"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}},bindings:{}},"_app_queue_example-queue":{address:"/app/queue/example-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_queue_sendto-queue":{address:"/app/queue/sendto-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_queue_sendtouser-queue":{address:"/app/queue/sendtouser-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_topic_sendto-response-queue":{address:"/app/topic/sendto-response-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_user_queue_sendtouser-response-queue":{address:"/user/queue/sendtouser-response-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},SpringStompDefaultHeaders:{title:"SpringStompDefaultHeaders",type:"object",properties:{},examples:[{}]},"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{stomp:{}}},"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringStompDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{stomp:{}}}}},operations:{"_app_queue_another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/_app_queue_another-queue"},title:"_app_queue_another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_another-queue/messages/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}]},"_app_queue_example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/_app_queue_example-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_example-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]},"_app_queue_sendto-queue_receive_receiveExamplePayloadSendTo":{action:"receive",channel:{$ref:"#/channels/_app_queue_sendto-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_sendto-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}],reply:{channel:{$ref:"#/channels/_app_topic_sendto-response-queue"},messages:[{$ref:"#/channels/_app_topic_sendto-response-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]}},"_app_queue_sendtouser-queue_receive_receiveExamplePayloadSendToUser":{action:"receive",channel:{$ref:"#/channels/_app_queue_sendtouser-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_sendtouser-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}],reply:{channel:{$ref:"#/channels/_user_queue_sendtouser-response-queue"},messages:[{$ref:"#/channels/_user_queue_sendtouser-response-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]}}}};var Vs={amqp:{value:uf},"cloud-stream":{value:hf},jms:{value:vf},kafka:{value:ff,groups:{"Only Vehicles":gf},uiConfig:bf},sns:{value:_f},sqs:{value:yf},stomp:{value:xf}};var $s=class{mockData=this.selectMockData();createDb(){return{}}get(n){if(n.req.url.endsWith("/docs"))return n.utils.createResponse$(()=>({status:ge.OK,body:this.mockData.value}));if(n.req.url.indexOf("/docs/")!==-1){let e=n.req.url.split("/docs/")[1];return n.utils.createResponse$(()=>this.mockData.groups===void 0||this.mockData.groups[e]===void 0?{status:ge.NOT_FOUND,body:void 0}:{status:ge.OK,body:this.mockData.groups[e]})}else{if(n.req.url.endsWith("/publish"))return n.utils.createResponse$(()=>({status:ge.OK,body:{}}));if(n.req.url.endsWith("/ui-config"))return this.mockData.uiConfig?n.utils.createResponse$(()=>({status:ge.OK,body:this.mockData.uiConfig})):{status:ge.NOT_FOUND,body:void 0}}return n.utils.getPassThruBackend().handle(n.req)}post(n){return n.req.url.endsWith("/publish")?n.utils.createResponse$(()=>({status:ge.OK})):n.utils.getPassThruBackend().handle(n.req)}selectMockData(){let n=window.location.hostname,e=Object.keys(Vs).filter(i=>n.includes(i));return 0<e.length?Vs[e[0]]:Vs.kafka}};function wf(t,n){let i=!n?.manualCleanup?n?.injector?.get(jn)??p(jn):null,a=ux(n?.equal),r;n?.requireSync?r=ne({kind:0},{equal:a}):r=ne({kind:1,value:n?.initialValue},{equal:a});let o,s=t.subscribe({next:l=>r.set({kind:1,value:l}),error:l=>{r.set({kind:2,error:l}),o?.()},complete:()=>{o?.()}});if(n?.requireSync&&r().kind===0)throw new Pe(601,!1);return o=i?.onDestroy(s.unsubscribe.bind(s)),cn(()=>{let l=r();switch(l.kind){case 1:return l.value;case 2:throw l.error;case 0:throw new Pe(601,!1)}},{equal:n?.equal})}function ux(t=Object.is){return(n,e)=>n.kind===1&&e.kind===1&&t(n.value,e.value)}function Sd(){return{async:!1,breaks:!1,extensions:null,gfm:!0,hooks:null,pedantic:!1,renderer:null,silent:!1,tokenizer:null,walkTokens:null}}var Fi=Sd();function If(t){Fi=t}var Ri={exec:()=>null};function Da(t){let n=[];return e=>{let i=Math.max(0,Math.min(3,e-1)),a=n[i];return a||(a=t(i),n[i]=a),a}}function Se(t,n=""){let e=typeof t=="string"?t:t.source,i={replace:(a,r)=>{let o=typeof r=="string"?r:r.source;return o=o.replace(xt.caret,"$1"),e=e.replace(a,o),i},getRegex:()=>new RegExp(e,n)};return i}var hx=((t="")=>{try{return!!new RegExp("(?<=1)(?<!1)"+t)}catch{return!1}})(),xt={codeRemoveIndent:/^(?: {1,4}| {0,3}\t)/gm,outputLinkReplace:/\\([\[\]])/g,indentCodeCompensation:/^(\s+)(?:```)/,beginningSpace:/^\s+/,endingHash:/#$/,startingSpaceChar:/^ /,endingSpaceChar:/ $/,nonSpaceChar:/[^ ]/,newLineCharGlobal:/\n/g,tabCharGlobal:/\t/g,multipleSpaceGlobal:/\s+/g,blankLine:/^[ \t]*$/,doubleBlankLine:/\n[ \t]*\n[ \t]*$/,blockquoteStart:/^ {0,3}>/,blockquoteSetextReplace:/\n {0,3}((?:=+|-+) *)(?=\n|$)/g,blockquoteSetextReplace2:/^ {0,3}>[ \t]?/gm,listReplaceNesting:/^ {1,4}(?=( {4})*[^ ])/g,listIsTask:/^\[[ xX]\] +\S/,listReplaceTask:/^\[[ xX]\] +/,listTaskCheckbox:/\[[ xX]\]/,anyLine:/\n.*\n/,hrefBrackets:/^<(.*)>$/,tableDelimiter:/[:|]/,tableAlignChars:/^\||\| *$/g,tableRowBlankLine:/\n[ \t]*$/,tableAlignRight:/^ *-+: *$/,tableAlignCenter:/^ *:-+: *$/,tableAlignLeft:/^ *:-+ *$/,startATag:/^<a /i,endATag:/^<\/a>/i,startPreScriptTag:/^<(pre|code|kbd|script)(\s|>)/i,endPreScriptTag:/^<\/(pre|code|kbd|script)(\s|>)/i,startAngleBracket:/^</,endAngleBracket:/>$/,pedanticHrefTitle:/^([^'"]*[^\s])\s+(['"])(.*)\2/,unicodeAlphaNumeric:/[\p{L}\p{N}]/u,escapeTest:/[&<>"']/,escapeReplace:/[&<>"']/g,escapeTestNoEncode:/[<>"']|&(?!(#\d{1,7}|#[Xx][a-fA-F0-9]{1,6}|\w+);)/,escapeReplaceNoEncode:/[<>"']|&(?!(#\d{1,7}|#[Xx][a-fA-F0-9]{1,6}|\w+);)/g,caret:/(^|[^\[])\^/g,percentDecode:/%25/g,findPipe:/\|/g,splitPipe:/ \|/,slashPipe:/\\\|/g,carriageReturn:/\r\n|\r/g,spaceLine:/^ +$/gm,notSpaceStart:/^\S*/,endingNewline:/\n$/,listItemRegex:t=>new RegExp(`^( {0,3}${t})((?:[	 ][^\\n]*)?(?:\\n|$))`),nextBulletRegex:Da(t=>new RegExp(`^ {0,${t}}(?:[*+-]|\\d{1,9}[.)])((?:[ 	][^\\n]*)?(?:\\n|$))`)),hrRegex:Da(t=>new RegExp(`^ {0,${t}}((?:- *){3,}|(?:_ *){3,}|(?:\\* *){3,})(?:\\n+|$)`)),fencesBeginRegex:Da(t=>new RegExp(`^ {0,${t}}(?:\`\`\`|~~~)`)),headingBeginRegex:Da(t=>new RegExp(`^ {0,${t}}#`)),htmlBeginRegex:Da(t=>new RegExp(`^ {0,${t}}<(?:[a-z].*>|!--)`,"i")),blockquoteBeginRegex:Da(t=>new RegExp(`^ {0,${t}}>`))},fx=/^(?:[ \t]*(?:\n|$))+/,gx=/^((?: {4}| {0,3}\t)[^\n]+(?:\n(?:[ \t]*(?:\n|$))*)?)+/,bx=/^ {0,3}(`{3,}(?=[^`\n]*(?:\n|$))|~{3,})([^\n]*)(?:\n|$)(?:|([\s\S]*?)(?:\n|$))(?: {0,3}\1[~`]* *(?=\n|$)|$)/,Qr=/^ {0,3}((?:-[\t ]*){3,}|(?:_[ \t]*){3,}|(?:\*[ \t]*){3,})(?:\n+|$)/,vx=/^ {0,3}(#{1,6})(?=\s|$)(.*)(?:\n+|$)/,kd=/ {0,3}(?:[*+-]|\d{1,9}[.)])/,Af=/^(?!bull |blockCode|fences|blockquote|heading|html|table)((?:.|\n(?!\s*?\n|bull |blockCode|fences|blockquote|heading|html|table))+?)\n {0,3}(=+|-+) *(?:\n+|$)/,Tf=Se(Af).replace(/bull/g,kd).replace(/blockCode/g,/(?: {4}| {0,3}\t)/).replace(/fences/g,/ {0,3}(?:`{3,}|~{3,})/).replace(/blockquote/g,/ {0,3}>/).replace(/heading/g,/ {0,3}#{1,6}/).replace(/html/g,/ {0,3}<[^\n>]+>\n/).replace(/\|table/g,"").getRegex(),_x=Se(Af).replace(/bull/g,kd).replace(/blockCode/g,/(?: {4}| {0,3}\t)/).replace(/fences/g,/ {0,3}(?:`{3,}|~{3,})/).replace(/blockquote/g,/ {0,3}>/).replace(/heading/g,/ {0,3}#{1,6}/).replace(/html/g,/ {0,3}<[^\n>]+>\n/).replace(/table/g,/ {0,3}\|?(?:[:\- ]*\|)+[\:\- ]*\n/).getRegex(),Cd=/^([^\n]+(?:\n(?!hr|heading|lheading|blockquote|fences|list|html|table| +\n)[^\n]+)*)/,yx=/^[^\n]+/,Ed=/(?!\s*\])(?:\\[\s\S]|[^\[\]\\])+/,xx=Se(/^ {0,3}\[(label)\]: *(?:\n[ \t]*)?([^<\s][^\s]*|<.*?>)(?:(?: +(?:\n[ \t]*)?| *\n[ \t]*)(title))? *(?:\n+|$)/).replace("label",Ed).replace("title",/(?:"(?:\\"?|[^"\\])*"|'[^'\n]*(?:\n[^'\n]+)*\n?'|\([^()]*\))/).getRegex(),wx=Se(/^(bull)([ \t][^\n]*?)?(?:\n|$)/).replace(/bull/g,kd).getRegex(),qs="address|article|aside|base|basefont|blockquote|body|caption|center|col|colgroup|dd|details|dialog|dir|div|dl|dt|fieldset|figcaption|figure|footer|form|frame|frameset|h[1-6]|head|header|hr|html|iframe|legend|li|link|main|menu|menuitem|meta|nav|noframes|ol|optgroup|option|p|param|search|section|summary|table|tbody|td|tfoot|th|thead|title|tr|track|ul",Dd=/<!--(?:-?>|[\s\S]*?(?:-->|$))/,Sx=Se("^ {0,3}(?:<(script|pre|style|textarea)[\\s>][\\s\\S]*?(?:</\\1>[^\\n]*\\n+|$)|comment[^\\n]*(\\n+|$)|<\\?[\\s\\S]*?(?:\\?>\\n*|$)|<![A-Z][\\s\\S]*?(?:>\\n*|$)|<!\\[CDATA\\[[\\s\\S]*?(?:\\]\\]>\\n*|$)|</?(tag)(?: +|\\n|/?>)[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$)|<(?!script|pre|style|textarea)([a-z][\\w-]*)(?:attribute)*? */?>(?=[ \\t]*(?:\\n|$))[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$)|</(?!script|pre|style|textarea)[a-z][\\w-]*\\s*>(?=[ \\t]*(?:\\n|$))[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$))","i").replace("comment",Dd).replace("tag",qs).replace("attribute",/ +[a-zA-Z:_][\w.:-]*(?: *= *"[^"\n]*"| *= *'[^'\n]*'| *= *[^\s"'=<>`]+)?/).getRegex(),Of=Se(Cd).replace("hr",Qr).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("|lheading","").replace("|table","").replace("blockquote"," {0,3}>").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)])[ \\t]+[^ \\t\\n]").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",qs).getRegex(),kx=Se(/^( {0,3}> ?(paragraph|[^\n]*)(?:\n|$))+/).replace("paragraph",Of).getRegex(),Md={blockquote:kx,code:gx,def:xx,fences:bx,heading:vx,hr:Qr,html:Sx,lheading:Tf,list:wx,newline:fx,paragraph:Of,table:Ri,text:yx},Sf=Se("^ *([^\\n ].*)\\n {0,3}((?:\\| *)?:?-+:? *(?:\\| *:?-+:? *)*(?:\\| *)?)(?:\\n((?:(?! *\\n|hr|heading|blockquote|code|fences|list|html).*(?:\\n|$))*)\\n*|$)").replace("hr",Qr).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("blockquote"," {0,3}>").replace("code","(?: {4}| {0,3}	)[^\\n]").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)])[ \\t]").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",qs).getRegex(),Cx=De(N({},Md),{lheading:_x,table:Sf,paragraph:Se(Cd).replace("hr",Qr).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("|lheading","").replace("table",Sf).replace("blockquote"," {0,3}>").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)])[ \\t]+[^ \\t\\n]").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",qs).getRegex()}),Ex=De(N({},Md),{html:Se(`^ *(?:comment *(?:\\n|\\s*$)|<(tag)[\\s\\S]+?</\\1> *(?:\\n{2,}|\\s*$)|<tag(?:"[^"]*"|'[^']*'|\\s[^'"/>\\s]*)*?/?> *(?:\\n{2,}|\\s*$))`).replace("comment",Dd).replace(/tag/g,"(?!(?:a|em|strong|small|s|cite|q|dfn|abbr|data|time|code|var|samp|kbd|sub|sup|i|b|u|mark|ruby|rt|rp|bdi|bdo|span|br|wbr|ins|del|img)\\b)\\w+(?!:|[^\\w\\s@]*@)\\b").getRegex(),def:/^ *\[([^\]]+)\]: *<?([^\s>]+)>?(?: +(["(][^\n]+[")]))? *(?:\n+|$)/,heading:/^(#{1,6})(.*)(?:\n+|$)/,fences:Ri,lheading:/^(.+?)\n {0,3}(=+|-+) *(?:\n+|$)/,paragraph:Se(Cd).replace("hr",Qr).replace("heading",` *#{1,6} *[^
]`).replace("lheading",Tf).replace("|table","").replace("blockquote"," {0,3}>").replace("|fences","").replace("|list","").replace("|html","").replace("|tag","").getRegex()}),Dx=/^\\([!"#$%&'()*+,\-./:;<=>?@\[\]\\^_`{|}~])/,Mx=/^(`+)([^`]|[^`][\s\S]*?[^`])\1(?!`)/,Rf=/^( {2,}|\\)\n(?!\s*$)/,Nx=/^(`+|[^`])(?:(?= {2,}\n)|[\s\S]*?(?:(?=[\\<!\[`*_]|\b_|$)|[^ ](?= {2,}\n)))/,Ma=/[\p{P}\p{S}]/u,Gs=/[\s\p{P}\p{S}]/u,Nd=/[^\s\p{P}\p{S}]/u,Ix=Se(/^((?![*_])punctSpace)/,"u").replace(/punctSpace/g,Gs).getRegex(),Pf=/(?!~)[\p{P}\p{S}]/u,Ax=/(?!~)[\s\p{P}\p{S}]/u,Tx=/(?:[^\s\p{P}\p{S}]|~)/u,Ox=Se(/link|precode-code|html/,"g").replace("link",/\[(?:[^\[\]`]|(?<a>`+)[^`]+\k<a>(?!`))*?\]\((?:\\[\s\S]|[^\\\(\)]|\((?:\\[\s\S]|[^\\\(\)])*\))*\)/).replace("precode-",hx?"(?<!`)()":"(^^|[^`])").replace("code",/(?<b>`+)[^`]+\k<b>(?!`)/).replace("html",/<(?! )[^<>]*?>/).getRegex(),Ff=/^(?:\*+(?:((?!\*)punct)|([^\s*]))?)|^_+(?:((?!_)punct)|([^\s_]))?/,Rx=Se(Ff,"u").replace(/punct/g,Ma).getRegex(),Px=Se(Ff,"u").replace(/punct/g,Pf).getRegex(),Lf="^[^_*]*?__[^_*]*?\\*[^_*]*?(?=__)|[^*]+(?=[^*])|(?!\\*)punct(\\*+)(?=[\\s]|$)|notPunctSpace(\\*+)(?!\\*)(?=punctSpace|$)|(?!\\*)punctSpace(\\*+)(?=notPunctSpace)|[\\s](\\*+)(?!\\*)(?=punct)|(?!\\*)punct(\\*+)(?!\\*)(?=punct)|notPunctSpace(\\*+)(?=notPunctSpace)",Fx=Se(Lf,"gu").replace(/notPunctSpace/g,Nd).replace(/punctSpace/g,Gs).replace(/punct/g,Ma).getRegex(),Lx=Se(Lf,"gu").replace(/notPunctSpace/g,Tx).replace(/punctSpace/g,Ax).replace(/punct/g,Pf).getRegex(),Bx=Se("^[^_*]*?\\*\\*[^_*]*?_[^_*]*?(?=\\*\\*)|[^_]+(?=[^_])|(?!_)punct(_+)(?=[\\s]|$)|notPunctSpace(_+)(?!_)(?=punctSpace|$)|(?!_)punctSpace(_+)(?=notPunctSpace)|[\\s](_+)(?!_)(?=punct)|(?!_)punct(_+)(?!_)(?=punct)","gu").replace(/notPunctSpace/g,Nd).replace(/punctSpace/g,Gs).replace(/punct/g,Ma).getRegex(),jx=Se(/^~~?(?:((?!~)punct)|[^\s~])/,"u").replace(/punct/g,Ma).getRegex(),Vx="^[^~]+(?=[^~])|(?!~)punct(~~?)(?=[\\s]|$)|notPunctSpace(~~?)(?!~)(?=punctSpace|$)|(?!~)punctSpace(~~?)(?=notPunctSpace)|[\\s](~~?)(?!~)(?=punct)|(?!~)punct(~~?)(?!~)(?=punct)|notPunctSpace(~~?)(?=notPunctSpace)",$x=Se(Vx,"gu").replace(/notPunctSpace/g,Nd).replace(/punctSpace/g,Gs).replace(/punct/g,Ma).getRegex(),zx=Se(/\\(punct)/,"gu").replace(/punct/g,Ma).getRegex(),Hx=Se(/^<(scheme:[^\s\x00-\x1f<>]*|email)>/).replace("scheme",/[a-zA-Z][a-zA-Z0-9+.-]{1,31}/).replace("email",/[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+(@)[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+(?![-_])/).getRegex(),Ux=Se(Dd).replace("(?:-->|$)","-->").getRegex(),qx=Se("^comment|^</[a-zA-Z][\\w:-]*\\s*>|^<[a-zA-Z][\\w-]*(?:attribute)*?\\s*/?>|^<\\?[\\s\\S]*?\\?>|^<![a-zA-Z]+\\s[\\s\\S]*?>|^<!\\[CDATA\\[[\\s\\S]*?\\]\\]>").replace("comment",Ux).replace("attribute",/\s+[a-zA-Z:_][\w.:-]*(?:\s*=\s*"[^"]*"|\s*=\s*'[^']*'|\s*=\s*[^\s"'=<>`]+)?/).getRegex(),Hs=/(?:\[(?:\\[\s\S]|[^\[\]\\])*\]|\\[\s\S]|`+(?!`)[^`]*?`+(?!`)|``+(?=\])|[^\[\]\\`])*?/,Gx=Se(/^!?\[(label)\]\(\s*(href)(?:(?:[ \t]+(?:\n[ \t]*)?|\n[ \t]*)(title))?\s*\)/).replace("label",Hs).replace("href",/<(?:\\.|[^\n<>\\])+>|[^ \t\n\x00-\x1f]*/).replace("title",/"(?:\\"?|[^"\\])*"|'(?:\\'?|[^'\\])*'|\((?:\\\)?|[^)\\])*\)/).getRegex(),Bf=Se(/^!?\[(label)\]\[(ref)\]/).replace("label",Hs).replace("ref",Ed).getRegex(),jf=Se(/^!?\[(ref)\](?:\[\])?/).replace("ref",Ed).getRegex(),Wx=Se("reflink|nolink(?!\\()","g").replace("reflink",Bf).replace("nolink",jf).getRegex(),kf=/[hH][tT][tT][pP][sS]?|[fF][tT][pP]/,Id={_backpedal:Ri,anyPunctuation:zx,autolink:Hx,blockSkip:Ox,br:Rf,code:Mx,del:Ri,delLDelim:Ri,delRDelim:Ri,emStrongLDelim:Rx,emStrongRDelimAst:Fx,emStrongRDelimUnd:Bx,escape:Dx,link:Gx,nolink:jf,punctuation:Ix,reflink:Bf,reflinkSearch:Wx,tag:qx,text:Nx,url:Ri},Kx=De(N({},Id),{link:Se(/^!?\[(label)\]\((.*?)\)/).replace("label",Hs).getRegex(),reflink:Se(/^!?\[(label)\]\s*\[([^\]]*)\]/).replace("label",Hs).getRegex()}),yd=De(N({},Id),{emStrongRDelimAst:Lx,emStrongLDelim:Px,delLDelim:jx,delRDelim:$x,url:Se(/^((?:protocol):\/\/|www\.)(?:[a-zA-Z0-9\-]+\.?)+[^\s<]*|^email/).replace("protocol",kf).replace("email",/[A-Za-z0-9._+-]+(@)[a-zA-Z0-9-_]+(?:\.[a-zA-Z0-9-_]*[a-zA-Z0-9])+(?![-_])/).getRegex(),_backpedal:/(?:[^?!.,:;*_'"~()&]+|\([^)]*\)|&(?![a-zA-Z0-9]+;$)|[?!.,:;*_'"~)]+(?!$))+/,del:/^(~~?)(?=[^\s~])((?:\\[\s\S]|[^\\])*?(?:\\[\s\S]|[^\s~\\]))\1(?=[^~]|$)/,text:Se(/^([`~]+|[^`~])(?:(?= {2,}\n)|(?=[a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-]+@)|[\s\S]*?(?:(?=[\\<!\[`*~_]|\b_|protocol:\/\/|www\.|$)|[^ ](?= {2,}\n)|[^a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-](?=[a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-]+@)))/).replace("protocol",kf).getRegex()}),Yx=De(N({},yd),{br:Se(Rf).replace("{2,}","*").getRegex(),text:Se(yd.text).replace("\\b_","\\b_| {2,}\\n").replace(/\{2,\}/g,"*").getRegex()}),zs={normal:Md,gfm:Cx,pedantic:Ex},Xr={normal:Id,gfm:yd,breaks:Yx,pedantic:Kx},Xx={"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"},Cf=t=>Xx[t];function En(t,n){if(n){if(xt.escapeTest.test(t))return t.replace(xt.escapeReplace,Cf)}else if(xt.escapeTestNoEncode.test(t))return t.replace(xt.escapeReplaceNoEncode,Cf);return t}function Ef(t){try{t=encodeURI(t).replace(xt.percentDecode,"%")}catch{return null}return t}function Df(t,n){let e=t.replace(xt.findPipe,(r,o,s)=>{let l=!1,c=o;for(;--c>=0&&s[c]==="\\";)l=!l;return l?"|":" |"}),i=e.split(xt.splitPipe),a=0;if(i[0].trim()||i.shift(),i.length>0&&!i.at(-1)?.trim()&&i.pop(),n)if(i.length>n)i.splice(n);else for(;i.length<n;)i.push("");for(;a<i.length;a++)i[a]=i[a].trim().replace(xt.slashPipe,"|");return i}function gi(t,n,e){let i=t.length;if(i===0)return"";let a=0;for(;a<i;){let r=t.charAt(i-a-1);if(r===n&&!e)a++;else if(r!==n&&e)a++;else break}return t.slice(0,i-a)}function Mf(t){let n=t.split(`
`),e=n.length-1;for(;e>=0&&xt.blankLine.test(n[e]);)e--;return n.length-e<=2?t:n.slice(0,e+1).join(`
`)}function Zx(t,n){if(t.indexOf(n[1])===-1)return-1;let e=0;for(let i=0;i<t.length;i++)if(t[i]==="\\")i++;else if(t[i]===n[0])e++;else if(t[i]===n[1]&&(e--,e<0))return i;return e>0?-2:-1}function Qx(t,n=0){let e=n,i="";for(let a of t)if(a==="	"){let r=4-e%4;i+=" ".repeat(r),e+=r}else i+=a,e++;return i}function Nf(t,n,e,i,a){let r=n.href,o=n.title||null,s=t[1].replace(a.other.outputLinkReplace,"$1");i.state.inLink=!0;let l={type:t[0].charAt(0)==="!"?"image":"link",raw:e,href:r,title:o,text:s,tokens:i.inlineTokens(s)};return i.state.inLink=!1,l}function Jx(t,n,e){let i=t.match(e.other.indentCodeCompensation);if(i===null)return n;let a=i[1];return n.split(`
`).map(r=>{let o=r.match(e.other.beginningSpace);if(o===null)return r;let[s]=o;return s.length>=a.length?r.slice(a.length):r}).join(`
`)}var Us=class{options;rules;lexer;constructor(t){this.options=t||Fi}space(t){let n=this.rules.block.newline.exec(t);if(n&&n[0].length>0)return{type:"space",raw:n[0]}}code(t){let n=this.rules.block.code.exec(t);if(n){let e=this.options.pedantic?n[0]:Mf(n[0]),i=e.replace(this.rules.other.codeRemoveIndent,"");return{type:"code",raw:e,codeBlockStyle:"indented",text:i}}}fences(t){let n=this.rules.block.fences.exec(t);if(n){let e=n[0],i=Jx(e,n[3]||"",this.rules);return{type:"code",raw:e,lang:n[2]?n[2].trim().replace(this.rules.inline.anyPunctuation,"$1"):n[2],text:i}}}heading(t){let n=this.rules.block.heading.exec(t);if(n){let e=n[2].trim();if(this.rules.other.endingHash.test(e)){let i=gi(e,"#");(this.options.pedantic||!i||this.rules.other.endingSpaceChar.test(i))&&(e=i.trim())}return{type:"heading",raw:gi(n[0],`
`),depth:n[1].length,text:e,tokens:this.lexer.inline(e)}}}hr(t){let n=this.rules.block.hr.exec(t);if(n)return{type:"hr",raw:gi(n[0],`
`)}}blockquote(t){let n=this.rules.block.blockquote.exec(t);if(n){let e=gi(n[0],`
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
`);continue}}return{type:"blockquote",raw:i,tokens:r,text:a}}}list(t){let n=this.rules.block.list.exec(t);if(n){let e=n[1].trim(),i=e.length>1,a={type:"list",raw:"",ordered:i,start:i?+e.slice(0,-1):"",loose:!1,items:[]};e=i?`\\d{1,9}\\${e.slice(-1)}`:`\\${e}`,this.options.pedantic&&(e=i?e:"[*+-]");let r=this.rules.other.listItemRegex(e),o=!1;for(;t;){let l=!1,c="",d="";if(!(n=r.exec(t))||this.rules.block.hr.test(t))break;c=n[0],t=t.substring(c.length);let m=Qx(n[2].split(`
`,1)[0],n[1].length),u=t.split(`
`,1)[0],f=!m.trim(),g=0;if(this.options.pedantic?(g=2,d=m.trimStart()):f?g=n[1].length+1:(g=m.search(this.rules.other.nonSpaceChar),g=g>4?1:g,d=m.slice(g),g+=n[1].length),f&&this.rules.other.blankLine.test(u)&&(c+=u+`
`,t=t.substring(u.length+1),l=!0),!l){let b=this.rules.other.nextBulletRegex(g),_=this.rules.other.hrRegex(g),x=this.rules.other.fencesBeginRegex(g),w=this.rules.other.headingBeginRegex(g),C=this.rules.other.htmlBeginRegex(g),F=this.rules.other.blockquoteBeginRegex(g);for(;t;){let A=t.split(`
`,1)[0],Q;if(u=A,this.options.pedantic?(u=u.replace(this.rules.other.listReplaceNesting,"  "),Q=u):Q=u.replace(this.rules.other.tabCharGlobal,"    "),x.test(u)||w.test(u)||C.test(u)||F.test(u)||b.test(u)||_.test(u))break;if(Q.search(this.rules.other.nonSpaceChar)>=g||!u.trim())d+=`
`+Q.slice(g);else{if(f||m.replace(this.rules.other.tabCharGlobal,"    ").search(this.rules.other.nonSpaceChar)>=4||x.test(m)||w.test(m)||_.test(m))break;d+=`
`+u}f=!u.trim(),c+=A+`
`,t=t.substring(A.length+1),m=Q.slice(g)}}a.loose||(o?a.loose=!0:this.rules.other.doubleBlankLine.test(c)&&(o=!0)),a.items.push({type:"list_item",raw:c,task:!!this.options.gfm&&this.rules.other.listIsTask.test(d),loose:!1,text:d,tokens:[]}),a.raw+=c}let s=a.items.at(-1);if(s)s.raw=s.raw.trimEnd(),s.text=s.text.trimEnd();else return;a.raw=a.raw.trimEnd();for(let l of a.items){this.lexer.state.top=!1,l.tokens=this.lexer.blockTokens(l.text,[]);let c=l.tokens[0];if(l.task&&(c?.type==="text"||c?.type==="paragraph")){l.text=l.text.replace(this.rules.other.listReplaceTask,""),c.raw=c.raw.replace(this.rules.other.listReplaceTask,""),c.text=c.text.replace(this.rules.other.listReplaceTask,"");for(let m=this.lexer.inlineQueue.length-1;m>=0;m--)if(this.rules.other.listIsTask.test(this.lexer.inlineQueue[m].src)){this.lexer.inlineQueue[m].src=this.lexer.inlineQueue[m].src.replace(this.rules.other.listReplaceTask,"");break}let d=this.rules.other.listTaskCheckbox.exec(l.raw);if(d){let m={type:"checkbox",raw:d[0]+" ",checked:d[0]!=="[ ]"};l.checked=m.checked,a.loose?l.tokens[0]&&["paragraph","text"].includes(l.tokens[0].type)&&"tokens"in l.tokens[0]&&l.tokens[0].tokens?(l.tokens[0].raw=m.raw+l.tokens[0].raw,l.tokens[0].text=m.raw+l.tokens[0].text,l.tokens[0].tokens.unshift(m)):l.tokens.unshift({type:"paragraph",raw:m.raw,text:m.raw,tokens:[m]}):l.tokens.unshift(m)}}else l.task&&(l.task=!1);if(!a.loose){let d=l.tokens.filter(u=>u.type==="space"),m=d.length>0&&d.some(u=>this.rules.other.anyLine.test(u.raw));a.loose=m}}if(a.loose)for(let l of a.items){l.loose=!0;for(let c of l.tokens)c.type==="text"&&(c.type="paragraph")}return a}}html(t){let n=this.rules.block.html.exec(t);if(n){let e=Mf(n[0]);return{type:"html",block:!0,raw:e,pre:n[1]==="pre"||n[1]==="script"||n[1]==="style",text:e}}}def(t){let n=this.rules.block.def.exec(t);if(n){let e=n[1].toLowerCase().replace(this.rules.other.multipleSpaceGlobal," "),i=n[2]?n[2].replace(this.rules.other.hrefBrackets,"$1").replace(this.rules.inline.anyPunctuation,"$1"):"",a=n[3]?n[3].substring(1,n[3].length-1).replace(this.rules.inline.anyPunctuation,"$1"):n[3];return{type:"def",tag:e,raw:gi(n[0],`
`),href:i,title:a}}}table(t){let n=this.rules.block.table.exec(t);if(!n||!this.rules.other.tableDelimiter.test(n[2]))return;let e=Df(n[1]),i=n[2].replace(this.rules.other.tableAlignChars,"").split("|"),a=n[3]?.trim()?n[3].replace(this.rules.other.tableRowBlankLine,"").split(`
`):[],r={type:"table",raw:gi(n[0],`
`),header:[],align:[],rows:[]};if(e.length===i.length){for(let o of i)this.rules.other.tableAlignRight.test(o)?r.align.push("right"):this.rules.other.tableAlignCenter.test(o)?r.align.push("center"):this.rules.other.tableAlignLeft.test(o)?r.align.push("left"):r.align.push(null);for(let o=0;o<e.length;o++)r.header.push({text:e[o],tokens:this.lexer.inline(e[o]),header:!0,align:r.align[o]});for(let o of a)r.rows.push(Df(o,r.header.length).map((s,l)=>({text:s,tokens:this.lexer.inline(s),header:!1,align:r.align[l]})));return r}}lheading(t){let n=this.rules.block.lheading.exec(t);if(n){let e=n[1].trim();return{type:"heading",raw:gi(n[0],`
`),depth:n[2].charAt(0)==="="?1:2,text:e,tokens:this.lexer.inline(e)}}}paragraph(t){let n=this.rules.block.paragraph.exec(t);if(n){let e=n[1].charAt(n[1].length-1)===`
`?n[1].slice(0,-1):n[1];return{type:"paragraph",raw:n[0],text:e,tokens:this.lexer.inline(e)}}}text(t){let n=this.rules.block.text.exec(t);if(n)return{type:"text",raw:n[0],text:n[0],tokens:this.lexer.inline(n[0])}}escape(t){let n=this.rules.inline.escape.exec(t);if(n)return{type:"escape",raw:n[0],text:n[1]}}tag(t){let n=this.rules.inline.tag.exec(t);if(n)return!this.lexer.state.inLink&&this.rules.other.startATag.test(n[0])?this.lexer.state.inLink=!0:this.lexer.state.inLink&&this.rules.other.endATag.test(n[0])&&(this.lexer.state.inLink=!1),!this.lexer.state.inRawBlock&&this.rules.other.startPreScriptTag.test(n[0])?this.lexer.state.inRawBlock=!0:this.lexer.state.inRawBlock&&this.rules.other.endPreScriptTag.test(n[0])&&(this.lexer.state.inRawBlock=!1),{type:"html",raw:n[0],inLink:this.lexer.state.inLink,inRawBlock:this.lexer.state.inRawBlock,block:!1,text:n[0]}}link(t){let n=this.rules.inline.link.exec(t);if(n){let e=n[2].trim();if(!this.options.pedantic&&this.rules.other.startAngleBracket.test(e)){if(!this.rules.other.endAngleBracket.test(e))return;let r=gi(e.slice(0,-1),"\\");if((e.length-r.length)%2===0)return}else{let r=Zx(n[2],"()");if(r===-2)return;if(r>-1){let o=(n[0].indexOf("!")===0?5:4)+n[1].length+r;n[2]=n[2].substring(0,r),n[0]=n[0].substring(0,o).trim(),n[3]=""}}let i=n[2],a="";if(this.options.pedantic){let r=this.rules.other.pedanticHrefTitle.exec(i);r&&(i=r[1],a=r[3])}else a=n[3]?n[3].slice(1,-1):"";return i=i.trim(),this.rules.other.startAngleBracket.test(i)&&(this.options.pedantic&&!this.rules.other.endAngleBracket.test(e)?i=i.slice(1):i=i.slice(1,-1)),Nf(n,{href:i&&i.replace(this.rules.inline.anyPunctuation,"$1"),title:a&&a.replace(this.rules.inline.anyPunctuation,"$1")},n[0],this.lexer,this.rules)}}reflink(t,n){let e;if((e=this.rules.inline.reflink.exec(t))||(e=this.rules.inline.nolink.exec(t))){let i=(e[2]||e[1]).replace(this.rules.other.multipleSpaceGlobal," "),a=n[i.toLowerCase()];if(!a){let r=e[0].charAt(0);return{type:"text",raw:r,text:r}}return Nf(e,a,e[0],this.lexer,this.rules)}}emStrong(t,n,e=""){let i=this.rules.inline.emStrongLDelim.exec(t);if(!(!i||!i[1]&&!i[2]&&!i[3]&&!i[4]||i[4]&&e.match(this.rules.other.unicodeAlphaNumeric))&&(!(i[1]||i[3])||!e||this.rules.inline.punctuation.exec(e))){let a=[...i[0]].length-1,r,o,s=a,l=0,c=i[0][0]==="*"?this.rules.inline.emStrongRDelimAst:this.rules.inline.emStrongRDelimUnd;for(c.lastIndex=0,n=n.slice(-1*t.length+a);(i=c.exec(n))!==null;){if(r=i[1]||i[2]||i[3]||i[4]||i[5]||i[6],!r)continue;if(o=[...r].length,i[3]||i[4]){s+=o;continue}else if((i[5]||i[6])&&a%3&&!((a+o)%3)){l+=o;continue}if(s-=o,s>0)continue;o=Math.min(o,o+s+l);let d=[...i[0]][0].length,m=t.slice(0,a+i.index+d+o);if(Math.min(a,o)%2){let f=m.slice(1,-1);return{type:"em",raw:m,text:f,tokens:this.lexer.inlineTokens(f)}}let u=m.slice(2,-2);return{type:"strong",raw:m,text:u,tokens:this.lexer.inlineTokens(u)}}}}codespan(t){let n=this.rules.inline.code.exec(t);if(n){let e=n[2].replace(this.rules.other.newLineCharGlobal," "),i=this.rules.other.nonSpaceChar.test(e),a=this.rules.other.startingSpaceChar.test(e)&&this.rules.other.endingSpaceChar.test(e);return i&&a&&(e=e.substring(1,e.length-1)),{type:"codespan",raw:n[0],text:e}}}br(t){let n=this.rules.inline.br.exec(t);if(n)return{type:"br",raw:n[0]}}del(t,n,e=""){let i=this.rules.inline.delLDelim.exec(t);if(i&&(!i[1]||!e||this.rules.inline.punctuation.exec(e))){let a=[...i[0]].length-1,r,o,s=a,l=this.rules.inline.delRDelim;for(l.lastIndex=0,n=n.slice(-1*t.length+a);(i=l.exec(n))!==null;){if(r=i[1]||i[2]||i[3]||i[4]||i[5]||i[6],!r||(o=[...r].length,o!==a))continue;if(i[3]||i[4]){s+=o;continue}if(s-=o,s>0)continue;o=Math.min(o,o+s);let c=[...i[0]][0].length,d=t.slice(0,a+i.index+c+o),m=d.slice(a,-a);return{type:"del",raw:d,text:m,tokens:this.lexer.inlineTokens(m)}}}}autolink(t){let n=this.rules.inline.autolink.exec(t);if(n){let e,i;return n[2]==="@"?(e=n[1],i="mailto:"+e):(e=n[1],i=e),{type:"link",raw:n[0],text:e,href:i,tokens:[{type:"text",raw:e,text:e}]}}}url(t){let n;if(n=this.rules.inline.url.exec(t)){let e,i;if(n[2]==="@")e=n[0],i="mailto:"+e;else{let a;do a=n[0],n[0]=this.rules.inline._backpedal.exec(n[0])?.[0]??"";while(a!==n[0]);e=n[0],n[1]==="www."?i="http://"+n[0]:i=n[0]}return{type:"link",raw:n[0],text:e,href:i,tokens:[{type:"text",raw:e,text:e}]}}}inlineText(t){let n=this.rules.inline.text.exec(t);if(n){let e=this.lexer.state.inRawBlock;return{type:"text",raw:n[0],text:n[0],escaped:e}}}},mn=class xd{tokens;options;state;inlineQueue;tokenizer;constructor(n){this.tokens=[],this.tokens.links=Object.create(null),this.options=n||Fi,this.options.tokenizer=this.options.tokenizer||new Us,this.tokenizer=this.options.tokenizer,this.tokenizer.options=this.options,this.tokenizer.lexer=this,this.inlineQueue=[],this.state={inLink:!1,inRawBlock:!1,top:!0};let e={other:xt,block:zs.normal,inline:Xr.normal};this.options.pedantic?(e.block=zs.pedantic,e.inline=Xr.pedantic):this.options.gfm&&(e.block=zs.gfm,this.options.breaks?e.inline=Xr.breaks:e.inline=Xr.gfm),this.tokenizer.rules=e}static get rules(){return{block:zs,inline:Xr}}static lex(n,e){return new xd(e).lex(n)}static lexInline(n,e){return new xd(e).inlineTokens(n)}lex(n){n=n.replace(xt.carriageReturn,`
`),this.blockTokens(n,this.tokens);for(let e=0;e<this.inlineQueue.length;e++){let i=this.inlineQueue[e];this.inlineTokens(i.src,i.tokens)}return this.inlineQueue=[],this.tokens}blockTokens(n,e=[],i=!1){this.tokenizer.lexer=this,this.options.pedantic&&(n=n.replace(xt.tabCharGlobal,"    ").replace(xt.spaceLine,""));let a=1/0;for(;n;){if(n.length<a)a=n.length;else{this.infiniteLoopError(n.charCodeAt(0));break}let r;if(this.options.extensions?.block?.some(s=>(r=s.call({lexer:this},n,e))?(n=n.substring(r.raw.length),e.push(r),!0):!1))continue;if(r=this.tokenizer.space(n)){n=n.substring(r.raw.length);let s=e.at(-1);r.raw.length===1&&s!==void 0?s.raw+=`
`:e.push(r);continue}if(r=this.tokenizer.code(n)){n=n.substring(r.raw.length);let s=e.at(-1);s?.type==="paragraph"||s?.type==="text"?(s.raw+=(s.raw.endsWith(`
`)?"":`
`)+r.raw,s.text+=`
`+r.text,this.inlineQueue.at(-1).src=s.text):e.push(r);continue}if(r=this.tokenizer.fences(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.heading(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.hr(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.blockquote(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.list(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.html(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.def(n)){n=n.substring(r.raw.length);let s=e.at(-1);s?.type==="paragraph"||s?.type==="text"?(s.raw+=(s.raw.endsWith(`
`)?"":`
`)+r.raw,s.text+=`
`+r.raw,this.inlineQueue.at(-1).src=s.text):this.tokens.links[r.tag]||(this.tokens.links[r.tag]={href:r.href,title:r.title},e.push(r));continue}if(r=this.tokenizer.table(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.lheading(n)){n=n.substring(r.raw.length),e.push(r);continue}let o=n;if(this.options.extensions?.startBlock){let s=1/0,l=n.slice(1),c;this.options.extensions.startBlock.forEach(d=>{c=d.call({lexer:this},l),typeof c=="number"&&c>=0&&(s=Math.min(s,c))}),s<1/0&&s>=0&&(o=n.substring(0,s+1))}if(this.state.top&&(r=this.tokenizer.paragraph(o))){let s=e.at(-1);i&&s?.type==="paragraph"?(s.raw+=(s.raw.endsWith(`
`)?"":`
`)+r.raw,s.text+=`
`+r.text,this.inlineQueue.pop(),this.inlineQueue.at(-1).src=s.text):e.push(r),i=o.length!==n.length,n=n.substring(r.raw.length);continue}if(r=this.tokenizer.text(n)){n=n.substring(r.raw.length);let s=e.at(-1);s?.type==="text"?(s.raw+=(s.raw.endsWith(`
`)?"":`
`)+r.raw,s.text+=`
`+r.text,this.inlineQueue.pop(),this.inlineQueue.at(-1).src=s.text):e.push(r);continue}if(n){this.infiniteLoopError(n.charCodeAt(0));break}}return this.state.top=!0,e}inline(n,e=[]){return this.inlineQueue.push({src:n,tokens:e}),e}inlineTokens(n,e=[]){this.tokenizer.lexer=this;let i=n,a=null;if(this.tokens.links){let c=Object.keys(this.tokens.links);if(c.length>0)for(;(a=this.tokenizer.rules.inline.reflinkSearch.exec(i))!==null;)c.includes(a[0].slice(a[0].lastIndexOf("[")+1,-1))&&(i=i.slice(0,a.index)+"["+"a".repeat(a[0].length-2)+"]"+i.slice(this.tokenizer.rules.inline.reflinkSearch.lastIndex))}for(;(a=this.tokenizer.rules.inline.anyPunctuation.exec(i))!==null;)i=i.slice(0,a.index)+"++"+i.slice(this.tokenizer.rules.inline.anyPunctuation.lastIndex);let r;for(;(a=this.tokenizer.rules.inline.blockSkip.exec(i))!==null;)r=a[2]?a[2].length:0,i=i.slice(0,a.index+r)+"["+"a".repeat(a[0].length-r-2)+"]"+i.slice(this.tokenizer.rules.inline.blockSkip.lastIndex);i=this.options.hooks?.emStrongMask?.call({lexer:this},i)??i;let o=!1,s="",l=1/0;for(;n;){if(n.length<l)l=n.length;else{this.infiniteLoopError(n.charCodeAt(0));break}o||(s=""),o=!1;let c;if(this.options.extensions?.inline?.some(m=>(c=m.call({lexer:this},n,e))?(n=n.substring(c.raw.length),e.push(c),!0):!1))continue;if(c=this.tokenizer.escape(n)){n=n.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.tag(n)){n=n.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.link(n)){n=n.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.reflink(n,this.tokens.links)){n=n.substring(c.raw.length);let m=e.at(-1);c.type==="text"&&m?.type==="text"?(m.raw+=c.raw,m.text+=c.text):e.push(c);continue}if(c=this.tokenizer.emStrong(n,i,s)){n=n.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.codespan(n)){n=n.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.br(n)){n=n.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.del(n,i,s)){n=n.substring(c.raw.length),e.push(c);continue}if(c=this.tokenizer.autolink(n)){n=n.substring(c.raw.length),e.push(c);continue}if(!this.state.inLink&&(c=this.tokenizer.url(n))){n=n.substring(c.raw.length),e.push(c);continue}let d=n;if(this.options.extensions?.startInline){let m=1/0,u=n.slice(1),f;this.options.extensions.startInline.forEach(g=>{f=g.call({lexer:this},u),typeof f=="number"&&f>=0&&(m=Math.min(m,f))}),m<1/0&&m>=0&&(d=n.substring(0,m+1))}if(c=this.tokenizer.inlineText(d)){n=n.substring(c.raw.length),c.raw.slice(-1)!=="_"&&(s=c.raw.slice(-1)),o=!0;let m=e.at(-1);m?.type==="text"?(m.raw+=c.raw,m.text+=c.text):e.push(c);continue}if(n){this.infiniteLoopError(n.charCodeAt(0));break}}return e}infiniteLoopError(n){let e="Infinite loop on byte: "+n;if(this.options.silent)console.error(e);else throw new Error(e)}},bi=class{options;parser;constructor(t){this.options=t||Fi}space(t){return""}code({text:t,lang:n,escaped:e}){let i=(n||"").match(xt.notSpaceStart)?.[0],a=t.replace(xt.endingNewline,"")+`
`;return i?'<pre><code class="language-'+En(i)+'">'+(e?a:En(a,!0))+`</code></pre>
`:"<pre><code>"+(e?a:En(a,!0))+`</code></pre>
`}blockquote({tokens:t}){return`<blockquote>
${this.parser.parse(t)}</blockquote>
`}html({text:t}){return t}def(t){return""}heading({tokens:t,depth:n}){return`<h${n}>${this.parser.parseInline(t)}</h${n}>
`}hr(t){return`<hr>
`}list(t){let n=t.ordered,e=t.start,i="";for(let o=0;o<t.items.length;o++){let s=t.items[o];i+=this.listitem(s)}let a=n?"ol":"ul",r=n&&e!==1?' start="'+e+'"':"";return"<"+a+r+`>
`+i+"</"+a+`>
`}listitem(t){return`<li>${this.parser.parse(t.tokens)}</li>
`}checkbox({checked:t}){return"<input "+(t?'checked="" ':"")+'disabled="" type="checkbox"> '}paragraph({tokens:t}){return`<p>${this.parser.parseInline(t)}</p>
`}table(t){let n="",e="";for(let a=0;a<t.header.length;a++)e+=this.tablecell(t.header[a]);n+=this.tablerow({text:e});let i="";for(let a=0;a<t.rows.length;a++){let r=t.rows[a];e="";for(let o=0;o<r.length;o++)e+=this.tablecell(r[o]);i+=this.tablerow({text:e})}return i&&(i=`<tbody>${i}</tbody>`),`<table>
<thead>
`+n+`</thead>
`+i+`</table>
`}tablerow({text:t}){return`<tr>
${t}</tr>
`}tablecell(t){let n=this.parser.parseInline(t.tokens),e=t.header?"th":"td";return(t.align?`<${e} align="${t.align}">`:`<${e}>`)+n+`</${e}>
`}strong({tokens:t}){return`<strong>${this.parser.parseInline(t)}</strong>`}em({tokens:t}){return`<em>${this.parser.parseInline(t)}</em>`}codespan({text:t}){return`<code>${En(t,!0)}</code>`}br(t){return"<br>"}del({tokens:t}){return`<del>${this.parser.parseInline(t)}</del>`}link({href:t,title:n,tokens:e}){let i=this.parser.parseInline(e),a=Ef(t);if(a===null)return i;t=a;let r='<a href="'+t+'"';return n&&(r+=' title="'+En(n)+'"'),r+=">"+i+"</a>",r}image({href:t,title:n,text:e,tokens:i}){i&&(e=this.parser.parseInline(i,this.parser.textRenderer));let a=Ef(t);if(a===null)return En(e);t=a;let r=`<img src="${t}" alt="${En(e)}"`;return n&&(r+=` title="${En(n)}"`),r+=">",r}text(t){return"tokens"in t&&t.tokens?this.parser.parseInline(t.tokens):"escaped"in t&&t.escaped?t.text:En(t.text)}},Ad=class{strong({text:t}){return t}em({text:t}){return t}codespan({text:t}){return t}del({text:t}){return t}html({text:t}){return t}text({text:t}){return t}link({text:t}){return""+t}image({text:t}){return""+t}br(){return""}checkbox({raw:t}){return t}},pn=class wd{options;renderer;textRenderer;constructor(n){this.options=n||Fi,this.options.renderer=this.options.renderer||new bi,this.renderer=this.options.renderer,this.renderer.options=this.options,this.renderer.parser=this,this.textRenderer=new Ad}static parse(n,e){return new wd(e).parse(n)}static parseInline(n,e){return new wd(e).parseInline(n)}parse(n){this.renderer.parser=this;let e="";for(let i=0;i<n.length;i++){let a=n[i];if(this.options.extensions?.renderers?.[a.type]){let o=a,s=this.options.extensions.renderers[o.type].call({parser:this},o);if(s!==!1||!["space","hr","heading","code","table","blockquote","list","html","def","paragraph","text"].includes(o.type)){e+=s||"";continue}}let r=a;switch(r.type){case"space":{e+=this.renderer.space(r);break}case"hr":{e+=this.renderer.hr(r);break}case"heading":{e+=this.renderer.heading(r);break}case"code":{e+=this.renderer.code(r);break}case"table":{e+=this.renderer.table(r);break}case"blockquote":{e+=this.renderer.blockquote(r);break}case"list":{e+=this.renderer.list(r);break}case"checkbox":{e+=this.renderer.checkbox(r);break}case"html":{e+=this.renderer.html(r);break}case"def":{e+=this.renderer.def(r);break}case"paragraph":{e+=this.renderer.paragraph(r);break}case"text":{e+=this.renderer.text(r);break}default:{let o='Token with "'+r.type+'" type was not found.';if(this.options.silent)return console.error(o),"";throw new Error(o)}}}return e}parseInline(n,e=this.renderer){this.renderer.parser=this;let i="";for(let a=0;a<n.length;a++){let r=n[a];if(this.options.extensions?.renderers?.[r.type]){let s=this.options.extensions.renderers[r.type].call({parser:this},r);if(s!==!1||!["escape","html","link","image","strong","em","codespan","br","del","text"].includes(r.type)){i+=s||"";continue}}let o=r;switch(o.type){case"escape":{i+=e.text(o);break}case"html":{i+=e.html(o);break}case"link":{i+=e.link(o);break}case"image":{i+=e.image(o);break}case"checkbox":{i+=e.checkbox(o);break}case"strong":{i+=e.strong(o);break}case"em":{i+=e.em(o);break}case"codespan":{i+=e.codespan(o);break}case"br":{i+=e.br(o);break}case"del":{i+=e.del(o);break}case"text":{i+=e.text(o);break}default:{let s='Token with "'+o.type+'" type was not found.';if(this.options.silent)return console.error(s),"";throw new Error(s)}}}return i}},Zr=class{options;block;constructor(t){this.options=t||Fi}static passThroughHooks=new Set(["preprocess","postprocess","processAllTokens","emStrongMask"]);static passThroughHooksRespectAsync=new Set(["preprocess","postprocess","processAllTokens"]);preprocess(t){return t}postprocess(t){return t}processAllTokens(t){return t}emStrongMask(t){return t}provideLexer(t=this.block){return t?mn.lex:mn.lexInline}provideParser(t=this.block){return t?pn.parse:pn.parseInline}},ew=class{defaults=Sd();options=this.setOptions;parse=this.parseMarkdown(!0);parseInline=this.parseMarkdown(!1);Parser=pn;Renderer=bi;TextRenderer=Ad;Lexer=mn;Tokenizer=Us;Hooks=Zr;constructor(...t){this.use(...t)}walkTokens(t,n){let e=[];for(let i of t)switch(e=e.concat(n.call(this,i)),i.type){case"table":{let a=i;for(let r of a.header)e=e.concat(this.walkTokens(r.tokens,n));for(let r of a.rows)for(let o of r)e=e.concat(this.walkTokens(o.tokens,n));break}case"list":{let a=i;e=e.concat(this.walkTokens(a.items,n));break}default:{let a=i;this.defaults.extensions?.childTokens?.[a.type]?this.defaults.extensions.childTokens[a.type].forEach(r=>{let o=a[r].flat(1/0);e=e.concat(this.walkTokens(o,n))}):a.tokens&&(e=e.concat(this.walkTokens(a.tokens,n)))}}return e}use(...t){let n=this.defaults.extensions||{renderers:{},childTokens:{}};return t.forEach(e=>{let i=N({},e);if(i.async=this.defaults.async||i.async||!1,e.extensions&&(e.extensions.forEach(a=>{if(!a.name)throw new Error("extension name required");if("renderer"in a){let r=n.renderers[a.name];r?n.renderers[a.name]=function(...o){let s=a.renderer.apply(this,o);return s===!1&&(s=r.apply(this,o)),s}:n.renderers[a.name]=a.renderer}if("tokenizer"in a){if(!a.level||a.level!=="block"&&a.level!=="inline")throw new Error("extension level must be 'block' or 'inline'");let r=n[a.level];r?r.unshift(a.tokenizer):n[a.level]=[a.tokenizer],a.start&&(a.level==="block"?n.startBlock?n.startBlock.push(a.start):n.startBlock=[a.start]:a.level==="inline"&&(n.startInline?n.startInline.push(a.start):n.startInline=[a.start]))}"childTokens"in a&&a.childTokens&&(n.childTokens[a.name]=a.childTokens)}),i.extensions=n),e.renderer){let a=this.defaults.renderer||new bi(this.defaults);for(let r in e.renderer){if(!(r in a))throw new Error(`renderer '${r}' does not exist`);if(["options","parser"].includes(r))continue;let o=r,s=e.renderer[o],l=a[o];a[o]=(...c)=>{let d=s.apply(a,c);return d===!1&&(d=l.apply(a,c)),d||""}}i.renderer=a}if(e.tokenizer){let a=this.defaults.tokenizer||new Us(this.defaults);for(let r in e.tokenizer){if(!(r in a))throw new Error(`tokenizer '${r}' does not exist`);if(["options","rules","lexer"].includes(r))continue;let o=r,s=e.tokenizer[o],l=a[o];a[o]=(...c)=>{let d=s.apply(a,c);return d===!1&&(d=l.apply(a,c)),d}}i.tokenizer=a}if(e.hooks){let a=this.defaults.hooks||new Zr;for(let r in e.hooks){if(!(r in a))throw new Error(`hook '${r}' does not exist`);if(["options","block"].includes(r))continue;let o=r,s=e.hooks[o],l=a[o];Zr.passThroughHooks.has(r)?a[o]=c=>{if(this.defaults.async&&Zr.passThroughHooksRespectAsync.has(r))return(async()=>{let m=await s.call(a,c);return l.call(a,m)})();let d=s.call(a,c);return l.call(a,d)}:a[o]=(...c)=>{if(this.defaults.async)return(async()=>{let m=await s.apply(a,c);return m===!1&&(m=await l.apply(a,c)),m})();let d=s.apply(a,c);return d===!1&&(d=l.apply(a,c)),d}}i.hooks=a}if(e.walkTokens){let a=this.defaults.walkTokens,r=e.walkTokens;i.walkTokens=function(o){let s=[];return s.push(r.call(this,o)),a&&(s=s.concat(a.call(this,o))),s}}this.defaults=N(N({},this.defaults),i)}),this}setOptions(t){return this.defaults=N(N({},this.defaults),t),this}lexer(t,n){return mn.lex(t,n??this.defaults)}parser(t,n){return pn.parse(t,n??this.defaults)}parseMarkdown(t){return(n,e)=>{let i=N({},e),a=N(N({},this.defaults),i),r=this.onError(!!a.silent,!!a.async);if(this.defaults.async===!0&&i.async===!1)return r(new Error("marked(): The async option was set to true by an extension. Remove async: false from the parse options object to return a Promise."));if(typeof n>"u"||n===null)return r(new Error("marked(): input parameter is undefined or null"));if(typeof n!="string")return r(new Error("marked(): input parameter is of type "+Object.prototype.toString.call(n)+", string expected"));if(a.hooks&&(a.hooks.options=a,a.hooks.block=t),a.async)return(async()=>{let o=a.hooks?await a.hooks.preprocess(n):n,s=await(a.hooks?await a.hooks.provideLexer(t):t?mn.lex:mn.lexInline)(o,a),l=a.hooks?await a.hooks.processAllTokens(s):s;a.walkTokens&&await Promise.all(this.walkTokens(l,a.walkTokens));let c=await(a.hooks?await a.hooks.provideParser(t):t?pn.parse:pn.parseInline)(l,a);return a.hooks?await a.hooks.postprocess(c):c})().catch(r);try{a.hooks&&(n=a.hooks.preprocess(n));let o=(a.hooks?a.hooks.provideLexer(t):t?mn.lex:mn.lexInline)(n,a);a.hooks&&(o=a.hooks.processAllTokens(o)),a.walkTokens&&this.walkTokens(o,a.walkTokens);let s=(a.hooks?a.hooks.provideParser(t):t?pn.parse:pn.parseInline)(o,a);return a.hooks&&(s=a.hooks.postprocess(s)),s}catch(o){return r(o)}}}onError(t,n){return e=>{if(e.message+=`
Please report this to https://github.com/markedjs/marked.`,t){let i="<p>An error occurred:</p><pre>"+En(e.message+"",!0)+"</pre>";return n?Promise.resolve(i):i}if(n)return Promise.reject(e);throw e}}},Pi=new ew;function we(t,n){return Pi.parse(t,n)}we.options=we.setOptions=function(t){return Pi.setOptions(t),we.defaults=Pi.defaults,If(we.defaults),we};we.getDefaults=Sd;we.defaults=Fi;we.use=function(...t){return Pi.use(...t),we.defaults=Pi.defaults,If(we.defaults),we};we.walkTokens=function(t,n){return Pi.walkTokens(t,n)};we.parseInline=Pi.parseInline;we.Parser=pn;we.parser=pn.parse;we.Renderer=bi;we.TextRenderer=Ad;we.Lexer=mn;we.lexer=mn.lex;we.Tokenizer=Us;we.Hooks=Zr;we.parse=we;var HR=we.options,UR=we.setOptions,qR=we.use,GR=we.walkTokens,WR=we.parseInline;var KR=pn.parse,YR=mn.lex;var tw="Copy",nw="Copied",iw=(()=>{class t{constructor(){this._buttonClick$=new z,this.copied=wf(this._buttonClick$.pipe(Gt(()=>It(Ue(!0),Ju(3e3).pipe(kr(!1)))),Cr(),Ln(1))),this.copiedText=cn(()=>this.copied()?nw:tw)}onCopyToClipboardClick(){this._buttonClick$.next()}static{this.\u0275fac=function(i){return new(i||t)}}static{this.\u0275cmp=T({type:t,selectors:[["markdown-clipboard"]],decls:2,vars:3,consts:[[1,"markdown-clipboard-button",3,"click"]],template:function(i,a){i&1&&(Qe(0,"button",0),bs("click",function(){return a.onCopyToClipboardClick()}),S(1),et()),i&2&&(ee("copied",a.copied()),h(),Ge(a.copiedText()))},encapsulation:2})}}return t})(),aw=new I("CLIPBOARD_OPTIONS"),rw=new I("KATEX_OPTIONS");var ow=new I("MARKED_EXTENSIONS"),sw=new I("MARKED_OPTIONS"),lw=new I("MERMAID_OPTIONS"),cw=new I("SANITIZE");function dw(t){return typeof t=="function"}var mw="[ngx-markdown] When using the `emoji` attribute you *have to* include Emoji-Toolkit files to `angular.json` or use imports. See README for more information";var pw="[ngx-markdown] When using the `katex` attribute you *have to* include the `marked-katex-extension` package and its dependencies. See README for more information",uw="[ngx-markdown] When using the `mermaid` attribute you *have to* include Mermaid files to `angular.json` or use imports. See README for more information",hw="[ngx-markdown] When using the `clipboard` attribute you *have to* include Clipboard files to `angular.json` or use imports. See README for more information",fw="[ngx-markdown] When using the `clipboard` attribute you *have to* provide the `viewContainerRef` parameter to `MarkdownService.render()` function",gw="[ngx-markdown] When using the `src` attribute you *have to* pass the `HttpClient` as a parameter of the `forRoot` method. See README for more information";var Vf=(()=>{class t{get options(){return this._options}set options(e){this._options=N(N({},this.DEFAULT_MARKED_OPTIONS),e)}get renderer(){return this.options.renderer}set renderer(e){this.options.renderer=e}constructor(){this.clipboardOptions=p(aw,{optional:!0}),this.extensions=p(ow,{optional:!0}),this.http=p(Xt,{optional:!0}),this.mermaidOptions=p(lw,{optional:!0}),this.katexOptions=p(rw,{optional:!0}),this.platform=p(ri),this.sanitize=p(cw,{optional:!0}),this.sanitizer=p(Hn),this.katexGate={enabled:!1},this.markedKatex=null,this.DEFAULT_MARKED_OPTIONS={renderer:new bi},this.DEFAULT_MERMAID_OPTIONS={startOnLoad:!1},this.DEFAULT_CLIPBOARD_OPTIONS={buttonComponent:void 0},this.DEFAULT_PARSE_OPTIONS={decodeHtml:!1,inline:!1,emoji:!1,katex:!1,katexOptions:void 0,mermaid:!1,markedOptions:void 0,disableSanitizer:!1},this.DEFAULT_RENDER_OPTIONS={clipboard:!1,clipboardOptions:void 0,mermaid:!1,mermaidOptions:void 0},this.DEFAULT_SECURITY_CONTEXT=mt.HTML,this._options=null,this._reload$=new z,this.reload$=this._reload$.asObservable(),this.options=p(sw,{optional:!0})}async parse(e,i=this.DEFAULT_PARSE_OPTIONS){let{decodeHtml:a,inline:r,emoji:o,katex:s,katexOptions:l,mermaid:c,disableSanitizer:d}=i;this.katexGate={enabled:!!i.katex};let m=N(N({},this.options),i.markedOptions),u=m.renderer||this.renderer||new bi;this.extensions&&(this.renderer=this.extendsRendererForExtensions(u)),s&&(this.renderer=await this.extendsRendererForKatex(u,l)),c&&(this.renderer=this.extendsRendererForMermaid(u));let f=this.trimIndentation(e),g=a?this.decodeHtml(f):f,b=o?this.parseEmoji(g):g,_=this.parseMarked(b,m,r);return d?_:this.sanitizeHtml(_)}render(e,i=this.DEFAULT_RENDER_OPTIONS,a){let{clipboard:r,clipboardOptions:o,mermaid:s,mermaidOptions:l}=i;s&&this.renderMermaid(e,N(N(N({},this.DEFAULT_MERMAID_OPTIONS),this.mermaidOptions),l)),r&&this.renderClipboard(e,a,N(N(N({},this.DEFAULT_CLIPBOARD_OPTIONS),this.clipboardOptions),o)),this.highlight(e)}reload(){this._reload$.next()}getSource(e){if(!this.http)throw new Error(gw);return this.http.get(e,{responseType:"text"}).pipe(Me(i=>this.handleExtension(e,i)))}highlight(e){if(!hi(this.platform)||typeof Prism>"u"||typeof Prism.highlightAllUnder>"u")return;e||(e=document);let i=e.querySelectorAll('pre code:not([class*="language-"])');Array.prototype.forEach.call(i,a=>a.classList.add("language-none")),Prism.highlightAllUnder(e)}decodeHtml(e){if(!hi(this.platform))return e;let i=document.createElement("textarea");return i.innerHTML=e,i.value}extendsRendererForExtensions(e){let i=e;return i.\u0275NgxMarkdownRendererExtendedForExtensions===!0||(this.extensions&&this.extensions.length>0&&we.use(...this.extensions),i.\u0275NgxMarkdownRendererExtendedForExtensions=!0),e}async extendsRendererForKatex(e,i){let a=this.katexGate,r=e;if(r.\u0275NgxMarkdownRendererExtendedForKatex===!0)return e;if(this.markedKatex??=await import("marked-katex-extension").then(d=>d.default).catch(()=>null),!this.markedKatex)throw new Error(pw);let o=N(N({},this.katexOptions),i),s=this.markedKatex(o),l=s.extensions?.map(d=>{let u=d.tokenizer;return De(N({},d),{tokenizer(f){if(!(!a.enabled||!u))return u.call(this,f,[])}})}),c=De(N({},s),{extensions:l});return we.use(c),r.\u0275NgxMarkdownRendererExtendedForKatex=!0,e}extendsRendererForMermaid(e){let i=e;if(i.\u0275NgxMarkdownRendererExtendedForMermaid===!0)return e;let a=e.code;return e.code=r=>r.lang==="mermaid"?`<div class="mermaid">${r.text}</div>`:a(r),i.\u0275NgxMarkdownRendererExtendedForMermaid=!0,e}handleExtension(e,i){let a=e.lastIndexOf("://"),r=a>-1?e.substring(a+4):e,o=r.lastIndexOf("/"),s=o>-1?r.substring(o+1).split("?")[0]:"",l=s.lastIndexOf("."),c=l>-1?s.substring(l+1):"";return c&&c!=="md"?"```"+c+`
`+i+"\n```":i}parseMarked(e,i,a=!1){if(i.renderer){let r=N({},i.renderer);delete r.\u0275NgxMarkdownRendererExtendedForExtensions,delete r.\u0275NgxMarkdownRendererExtendedForKatex,delete r.\u0275NgxMarkdownRendererExtendedForMermaid,delete i.renderer,we.use({renderer:r})}return a?we.parseInline(e,i):we.parse(e,i)}parseEmoji(e){if(!hi(this.platform))return e;if(typeof joypixels>"u"||typeof joypixels.shortnameToUnicode>"u")throw new Error(mw);return joypixels.shortnameToUnicode(e)}renderClipboard(e,i,a){if(!hi(this.platform))return;if(typeof ClipboardJS>"u")throw new Error(hw);if(!i)throw new Error(fw);let{buttonComponent:r,buttonTemplate:o}=a,s=e.querySelectorAll("pre");for(let l=0;l<s.length;l++){let c=s.item(l),d=document.createElement("div");d.style.position="relative",c.parentNode.insertBefore(d,c),d.appendChild(c);let m=document.createElement("div");m.classList.add("markdown-clipboard-toolbar"),m.style.position="absolute",m.style.top=".5em",m.style.right=".5em",m.style.zIndex="1",d.insertAdjacentElement("beforeend",m),d.onmouseenter=()=>m.classList.add("hover"),d.onmouseleave=()=>m.classList.remove("hover");let u;if(r){let g=i.createComponent(r);u=g.hostView,g.changeDetectorRef.markForCheck()}else if(o)u=i.createEmbeddedView(o);else{let g=i.createComponent(iw);u=g.hostView,g.changeDetectorRef.markForCheck()}let f;u.rootNodes.forEach(g=>{m.appendChild(g),f=new ClipboardJS(g,{text:()=>c.innerText})}),u.onDestroy(()=>f.destroy())}}renderMermaid(e,i=this.DEFAULT_MERMAID_OPTIONS){if(!hi(this.platform))return;if(typeof mermaid>"u"||typeof mermaid.initialize>"u")throw new Error(uw);let a=e.querySelectorAll(".mermaid");a.length!==0&&(mermaid.initialize(i),mermaid.run({nodes:a}))}trimIndentation(e){if(!e)return"";let i;return e.split(`
`).map(a=>{let r=i;return a.length>0&&(r=isNaN(r)?a.search(/\S|$/):Math.min(a.search(/\S|$/),r)),isNaN(i)&&(i=r),r?a.substring(r):a}).join(`
`)}async sanitizeHtml(e){return dw(this.sanitize)?this.sanitize(await e):this.sanitize!==mt.NONE?this.sanitizer.sanitize(this.sanitize??this.DEFAULT_SECURITY_CONTEXT,e)??"":e}static{this.\u0275fac=function(i){return new(i||t)}}static{this.\u0275prov=te({token:t,factory:t.\u0275fac})}}return t})(),Td=(function(t){return t.CommandLine="command-line",t.LineHighlight="line-highlight",t.LineNumbers="line-numbers",t})(Td||{}),Dn=(()=>{class t{constructor(){this.element=p(X),this.markdownService=p(Vf),this.viewContainerRef=p(wn),this.pendingTasks=p(ba),this.error=new Te,this.load=new Te,this.ready=new Te,this._clipboard=!1,this._commandLine=!1,this._disableSanitizer=!1,this._emoji=!1,this._inline=!1,this._katex=!1,this._lineHighlight=!1,this._lineNumbers=!1,this._mermaid=!1,this.destroyed$=new z}get disableSanitizer(){return this._disableSanitizer}set disableSanitizer(e){this._disableSanitizer=this.coerceBooleanProperty(e)}get inline(){return this._inline}set inline(e){this._inline=this.coerceBooleanProperty(e)}get clipboard(){return this._clipboard}set clipboard(e){this._clipboard=this.coerceBooleanProperty(e)}get emoji(){return this._emoji}set emoji(e){this._emoji=this.coerceBooleanProperty(e)}get katex(){return this._katex}set katex(e){this._katex=this.coerceBooleanProperty(e)}get mermaid(){return this._mermaid}set mermaid(e){this._mermaid=this.coerceBooleanProperty(e)}get lineHighlight(){return this._lineHighlight}set lineHighlight(e){this._lineHighlight=this.coerceBooleanProperty(e)}get lineNumbers(){return this._lineNumbers}set lineNumbers(e){this._lineNumbers=this.coerceBooleanProperty(e)}get commandLine(){return this._commandLine}set commandLine(e){this._commandLine=this.coerceBooleanProperty(e)}ngOnChanges(){this.loadContent()}loadContent(){if(this.data!=null){this.handleData();return}if(this.src!=null){this.handleSrc();return}}ngAfterViewInit(){!this.data&&!this.src&&this.handleTransclusion(),this.markdownService.reload$.pipe(Ae(this.destroyed$)).subscribe(()=>this.loadContent())}ngOnDestroy(){this.destroyed$.next(),this.destroyed$.complete()}async render(e,i=!1){let a=this.pendingTasks.add();try{await this.renderContent(e,i)}finally{a()}}async renderContent(e,i){let a={decodeHtml:i,inline:this.inline,emoji:this.emoji,katex:this.katex,katexOptions:this.katexOptions,mermaid:this.mermaid,disableSanitizer:this.disableSanitizer},r={clipboard:this.clipboard,clipboardOptions:this.getClipboardOptions(),mermaid:this.mermaid,mermaidOptions:this.mermaidOptions},o=await this.markdownService.parse(e,a);this.element.nativeElement.innerHTML=o,this.handlePlugins(),this.markdownService.render(this.element.nativeElement,r,this.viewContainerRef),this.ready.emit()}coerceBooleanProperty(e){return e!=null&&`${String(e)}`!="false"}getClipboardOptions(){if(this.clipboardButtonComponent||this.clipboardButtonTemplate)return{buttonComponent:this.clipboardButtonComponent,buttonTemplate:this.clipboardButtonTemplate}}handleData(){this.render(this.data)}handleSrc(){this.markdownService.getSource(this.src).subscribe({next:e=>{this.render(e).then(()=>{this.load.emit(e)})},error:e=>this.error.emit(e)})}handleTransclusion(){this.render(this.element.nativeElement.innerHTML,!0)}handlePlugins(){this.commandLine&&(this.setPluginClass(this.element.nativeElement,Td.CommandLine),this.setPluginOptions(this.element.nativeElement,{dataFilterOutput:this.filterOutput,dataHost:this.host,dataPrompt:this.prompt,dataOutput:this.output,dataUser:this.user})),this.lineHighlight&&this.setPluginOptions(this.element.nativeElement,{dataLine:this.line,dataLineOffset:this.lineOffset}),this.lineNumbers&&(this.setPluginClass(this.element.nativeElement,Td.LineNumbers),this.setPluginOptions(this.element.nativeElement,{dataStart:this.start}))}setPluginClass(e,i){let a=e.querySelectorAll("pre");for(let r=0;r<a.length;r++){let o=i instanceof Array?i:[i];a.item(r).classList.add(...o)}}setPluginOptions(e,i){let a=e.querySelectorAll("pre");for(let r=0;r<a.length;r++)Object.keys(i).forEach(o=>{let s=i[o];if(s){let l=this.toLispCase(o);a.item(r).setAttribute(l,s.toString())}})}toLispCase(e){let i=e.match(/([A-Z])/g);if(!i)return e;let a=e.toString();for(let r=0,o=i.length;r<o;r++)a=a.replace(new RegExp(i[r]),"-"+i[r].toLowerCase());return a.slice(0,1)==="-"&&(a=a.slice(1)),a}static{this.\u0275fac=function(i){return new(i||t)}}static{this.\u0275cmp=(function(){return T({type:t,selectors:[["markdown"],["","markdown",""]],inputs:{data:"data",src:"src",disableSanitizer:"disableSanitizer",inline:"inline",clipboard:"clipboard",clipboardButtonComponent:"clipboardButtonComponent",clipboardButtonTemplate:"clipboardButtonTemplate",emoji:"emoji",katex:"katex",katexOptions:"katexOptions",mermaid:"mermaid",mermaidOptions:"mermaidOptions",lineHighlight:"lineHighlight",line:"line",lineOffset:"lineOffset",lineNumbers:"lineNumbers",start:"start",commandLine:"commandLine",filterOutput:"filterOutput",host:"host",prompt:"prompt",output:"output",user:"user"},outputs:{error:"error",load:"load",ready:"ready"},features:[pt],ngContentSelectors:["*"],decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},encapsulation:2})})()}}return t})();function Od(t){return[Vf,t?.loader??[],t?.clipboardOptions??[],t?.katexOptions??[],t?.markedOptions??[],t?.mermaidOptions??[],t?.markedExtensions??[],t?.sanitize??[]]}var Mn=(()=>{class t{static forRoot(e){return{ngModule:t,providers:[Od(e)]}}static forChild(){return{ngModule:t}}static{this.\u0275fac=function(i){return new(i||t)}}static{this.\u0275mod=$({type:t})}static{this.\u0275inj=V({})}}return t})();var Ks=new WeakMap,dt=(()=>{class t{_appRef;_injector=p(re);_environmentInjector=p(Bn);load(e){let i=this._appRef=this._appRef||this._injector.get(ci),a=Ks.get(i);a||(a={loaders:new Set,refs:[]},Ks.set(i,a),i.onDestroy(()=>{Ks.get(i)?.refs.forEach(r=>r.destroy()),Ks.delete(i)})),a.loaders.has(e)||(a.loaders.add(e),a.refs.push(ws(e,{environmentInjector:this._environmentInjector})))}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var Na=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["ng-component"]],exportAs:["cdkVisuallyHidden"],decls:0,vars:0,template:function(i,a){},styles:[`.cdk-visually-hidden {
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
`],encapsulation:2})}return t})(),Ys;function bw(){if(Ys===void 0&&(Ys=null,typeof window<"u")){let t=window;if(t.trustedTypes!==void 0)try{Ys=t.trustedTypes.createPolicy("angular#components",{createHTML:n=>n})}catch(n){console.error(n)}}return Ys}function Li(t){return bw()?.createHTML(t)||t}function $f(t,n,e){let i=e.sanitize(mt.HTML,n);t.innerHTML=Li(i||"")}function zf(t){return Error(`Unable to find icon with the name "${t}"`)}function vw(){return Error("Could not find HttpClient for use with Angular Material icons. Please add provideHttpClient() to your providers.")}function Hf(t){return Error(`The URL provided to MatIconRegistry was not trusted as a resource URL via Angular's DomSanitizer. Attempted URL was "${t}".`)}function Uf(t){return Error(`The literal provided to MatIconRegistry was not trusted as safe HTML by Angular's DomSanitizer. Attempted literal was "${t}".`)}var Un=class{url;svgText;options;svgElement=null;constructor(n,e,i){this.url=n,this.svgText=e,this.options=i}},Zs=(()=>{class t{_httpClient;_sanitizer;_errorHandler;_document;_svgIconConfigs=new Map;_iconSetConfigs=new Map;_cachedIconsByUrl=new Map;_inProgressUrlFetches=new Map;_fontCssClassesByAlias=new Map;_resolvers=[];_defaultFontSetClass;constructor(e,i,a,r){this._httpClient=e,this._sanitizer=i,this._errorHandler=r,this._document=a}addSvgIcon(e,i,a){return this.addSvgIconInNamespace("",e,i,a)}addSvgIconLiteral(e,i,a){return this.addSvgIconLiteralInNamespace("",e,i,a)}addSvgIconInNamespace(e,i,a,r){return this._addSvgIconConfig(e,i,new Un(a,null,r))}addSvgIconResolver(e){return this._resolvers.push(e),this}addSvgIconLiteralInNamespace(e,i,a,r){let o=this._sanitizer.sanitize(mt.HTML,a);if(!o)throw Uf(a);let s=Li(o);return this._addSvgIconConfig(e,i,new Un("",s,r))}addSvgIconSet(e,i){return this.addSvgIconSetInNamespace("",e,i)}addSvgIconSetLiteral(e,i){return this.addSvgIconSetLiteralInNamespace("",e,i)}addSvgIconSetInNamespace(e,i,a){return this._addSvgIconSetConfig(e,new Un(i,null,a))}addSvgIconSetLiteralInNamespace(e,i,a){let r=this._sanitizer.sanitize(mt.HTML,i);if(!r)throw Uf(i);let o=Li(r);return this._addSvgIconSetConfig(e,new Un("",o,a))}registerFontClassAlias(e,i=e){return this._fontCssClassesByAlias.set(e,i),this}classNameForFontAlias(e){return this._fontCssClassesByAlias.get(e)||e}setDefaultFontSetClass(...e){return this._defaultFontSetClass=e,this}getDefaultFontSetClass(){return this._defaultFontSetClass??=yw(this._document),this._defaultFontSetClass}getSvgIconFromUrl(e){let i=this._sanitizer.sanitize(mt.RESOURCE_URL,e);if(!i)throw Hf(e);let a=this._cachedIconsByUrl.get(i);return a?Ue(Xs(a)):this._loadSvgIconFromConfig(new Un(e,null)).pipe(ai(r=>this._cachedIconsByUrl.set(i,r)),Me(r=>Xs(r)))}getNamedSvgIcon(e,i=""){let a=qf(i,e),r=this._svgIconConfigs.get(a);if(r)return this._getSvgFromConfig(r);if(r=this._getIconConfigFromResolvers(i,e),r)return this._svgIconConfigs.set(a,r),this._getSvgFromConfig(r);let o=this._iconSetConfigs.get(i);return o?this._getSvgFromIconSetConfigs(e,o):Yu(zf(a))}ngOnDestroy(){this._resolvers=[],this._svgIconConfigs.clear(),this._iconSetConfigs.clear(),this._cachedIconsByUrl.clear()}_getSvgFromConfig(e){return e.svgText?Ue(Xs(this._svgElementFromConfig(e))):this._loadSvgIconFromConfig(e).pipe(Me(i=>Xs(i)))}_getSvgFromIconSetConfigs(e,i){let a=this._extractIconWithNameFromAnySet(e,i);if(a)return Ue(a);let r=i.filter(o=>!o.svgText).map(o=>this._loadSvgIconSetFromConfig(o).pipe(cs(s=>{let c=`Loading icon set URL: ${this._sanitizer.sanitize(mt.RESOURCE_URL,o.url)} failed: ${s.message}`;return this._errorHandler.handleError(new Error(c)),Ue(null)})));return Qu(r).pipe(Me(()=>{let o=this._extractIconWithNameFromAnySet(e,i);if(!o)throw zf(e);return o}))}_extractIconWithNameFromAnySet(e,i){for(let a=i.length-1;a>=0;a--){let r=i[a];if(r.svgText&&r.svgText.toString().indexOf(e)>-1){let o=this._svgElementFromConfig(r),s=this._extractSvgIconFromSet(o,e,r.options);if(s)return s}}return null}_loadSvgIconFromConfig(e){return this._fetchIcon(e).pipe(ai(i=>e.svgText=i),Me(()=>this._svgElementFromConfig(e)))}_loadSvgIconSetFromConfig(e){return e.svgText?Ue(null):this._fetchIcon(e).pipe(ai(i=>e.svgText=i))}_extractSvgIconFromSet(e,i,a){let r=e.querySelector(`[id="${i}"]`);if(!r)return null;let o=r.cloneNode(!0);if(o.removeAttribute("id"),o.nodeName.toLowerCase()==="svg")return this._setSvgAttributes(o,a);if(o.nodeName.toLowerCase()==="symbol")return this._setSvgAttributes(this._toSvgElement(o),a);let s=this._svgElementFromString(Li("<svg></svg>"));return s.appendChild(o),this._setSvgAttributes(s,a)}_svgElementFromString(e){let i=this._document.createElement("DIV");i.innerHTML=e;let a=i.querySelector("svg");if(!a)throw Error("<svg> tag not found");return a}_toSvgElement(e){let i=this._svgElementFromString(Li("<svg></svg>")),a=e.attributes;for(let r=0;r<a.length;r++){let{name:o,value:s}=a[r],l=o.toLowerCase();l!=="id"&&!l.startsWith("on")&&i.setAttribute(o,s)}for(let r=0;r<e.childNodes.length;r++)e.childNodes[r].nodeType===this._document.ELEMENT_NODE&&i.appendChild(e.childNodes[r].cloneNode(!0));return i}_setSvgAttributes(e,i){return e.setAttribute("fit",""),e.setAttribute("height","100%"),e.setAttribute("width","100%"),e.setAttribute("preserveAspectRatio","xMidYMid meet"),e.setAttribute("focusable","false"),i&&i.viewBox&&e.setAttribute("viewBox",i.viewBox),e}_fetchIcon(e){let{url:i,options:a}=e,r=a?.withCredentials??!1;if(!this._httpClient)throw vw();if(i==null)throw Error(`Cannot fetch icon from URL "${i}".`);let o=this._sanitizer.sanitize(mt.RESOURCE_URL,i);if(!o)throw Hf(i);let s=this._inProgressUrlFetches.get(o);if(s)return s;let l=this._httpClient.get(o,{responseType:"text",withCredentials:r}).pipe(Me(c=>Li(c)),Er(()=>this._inProgressUrlFetches.delete(o)),ds());return this._inProgressUrlFetches.set(o,l),l}_addSvgIconConfig(e,i,a){return this._svgIconConfigs.set(qf(e,i),a),this}_addSvgIconSetConfig(e,i){let a=this._iconSetConfigs.get(e);return a?a.push(i):this._iconSetConfigs.set(e,[i]),this}_svgElementFromConfig(e){if(!e.svgElement){let i=this._svgElementFromString(e.svgText);this._setSvgAttributes(i,e.options),e.svgElement=i}return e.svgElement}_getIconConfigFromResolvers(e,i){for(let a=0;a<this._resolvers.length;a++){let r=this._resolvers[a](i,e);if(r)return _w(r)?new Un(r.url,null,r.options):new Un(r,null)}}static \u0275fac=function(i){return new(i||t)(B(Xt,8),B(Hn),B(Y,8),B(Ai))};static \u0275prov=te({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();function Xs(t){return t.cloneNode(!0)}function qf(t,n){return t+":"+n}function _w(t){return!!(t.url&&t.options)}function yw(t){let n=null,e=!1;return t.fonts&&typeof t.fonts.forEach=="function"&&t.fonts.forEach(i=>{let a=i.family.replace(/['"]/g,"").trim().toLowerCase();(a==="material icons"||a.startsWith("material icons "))&&(e=!0),a.startsWith("material symbols rounded")?n="rounded":a.startsWith("material symbols sharp")?n="sharp":a.startsWith("material symbols")&&(n="outlined")}),[n&&!e?`material-symbols-${n}`:"material-icons","mat-ligature-font"]}var xw=new I("cdk-dir-doc",{providedIn:"root",factory:()=>p(Y)}),ww=/^(ar|ckb|dv|he|iw|fa|nqo|ps|sd|ug|ur|yi|.*[-_](Adlm|Arab|Hebr|Nkoo|Rohg|Thaa))(?!.*[-_](Latn|Cyrl)($|-|_))($|-|_)/i;function Gf(t){let n=t?.toLowerCase()||"";return n==="auto"&&typeof navigator<"u"&&navigator?.language?ww.test(navigator.language)?"rtl":"ltr":n==="rtl"?"rtl":"ltr"}var Tt=(()=>{class t{get value(){return this.valueSignal()}valueSignal=ne("ltr");change=new Te;constructor(){let e=p(xw,{optional:!0});if(e){let i=e.body?e.body.dir:null,a=e.documentElement?e.documentElement.dir:null;this.valueSignal.set(Gf(i||a||"ltr"))}}ngOnDestroy(){this.change.complete()}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var me=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({})}return t})();var Sw=new I("MAT_ICON_DEFAULT_OPTIONS"),kw=new I("mat-icon-location",{providedIn:"root",factory:()=>{let t=p(Y),n=t?t.location:null;return{getPathname:()=>n?n.pathname+n.search:""}}}),Wf=["clip-path","color-profile","src","cursor","fill","filter","marker","marker-start","marker-mid","marker-end","mask","stroke"],Cw=Wf.map(t=>`[${t}]`).join(", "),Ew=/^url\(['"]?#(.*?)['"]?\)$/,Lt=(()=>{class t{_elementRef=p(X);_iconRegistry=p(Zs);_location=p(kw);_errorHandler=p(Ai);_defaultColor;get color(){return this._color||this._defaultColor}set color(e){this._color=e}_color;inline=!1;get svgIcon(){return this._svgIcon}set svgIcon(e){e!==this._svgIcon&&(e?this._updateSvgIcon(e):this._svgIcon&&this._clearSvgElement(),this._svgIcon=e)}_svgIcon;get fontSet(){return this._fontSet}set fontSet(e){let i=this._cleanupFontValue(e);i!==this._fontSet&&(this._fontSet=i,this._updateFontIconClasses())}_fontSet;get fontIcon(){return this._fontIcon}set fontIcon(e){let i=this._cleanupFontValue(e);i!==this._fontIcon&&(this._fontIcon=i,this._updateFontIconClasses())}_fontIcon;_previousFontSetClass=[];_previousFontIconClass;_svgName=null;_svgNamespace=null;_previousPath;_elementsWithExternalReferences;_currentIconFetch=Mt.EMPTY;constructor(){let e=p(new _h("aria-hidden"),{optional:!0}),i=p(Sw,{optional:!0});i&&(i.color&&(this.color=this._defaultColor=i.color),i.fontSet&&(this.fontSet=i.fontSet)),e||this._elementRef.nativeElement.setAttribute("aria-hidden","true")}_splitIconName(e){if(!e)return["",""];let i=e.split(":");switch(i.length){case 1:return["",i[0]];case 2:return i;default:throw Error(`Invalid icon name: "${e}"`)}}ngOnInit(){this._updateFontIconClasses()}ngAfterViewChecked(){let e=this._elementsWithExternalReferences;if(e&&e.size){let i=this._location.getPathname();i!==this._previousPath&&(this._previousPath=i,this._prependPathToReferences(i))}}ngOnDestroy(){this._currentIconFetch.unsubscribe(),this._elementsWithExternalReferences&&this._elementsWithExternalReferences.clear()}_usingFontIcon(){return!this.svgIcon}_setSvgElement(e){this._clearSvgElement();let i=this._location.getPathname();this._previousPath=i,this._cacheChildrenWithExternalReferences(e),this._prependPathToReferences(i),this._elementRef.nativeElement.appendChild(e)}_clearSvgElement(){let e=this._elementRef.nativeElement,i=e.childNodes.length;for(this._elementsWithExternalReferences&&this._elementsWithExternalReferences.clear();i--;){let a=e.childNodes[i];(a.nodeType!==1||a.nodeName.toLowerCase()==="svg")&&a.remove()}}_updateFontIconClasses(){if(!this._usingFontIcon())return;let e=this._elementRef.nativeElement,i=(this.fontSet?this._iconRegistry.classNameForFontAlias(this.fontSet).split(/ +/):this._iconRegistry.getDefaultFontSetClass()).filter(a=>a.length>0);this._previousFontSetClass.forEach(a=>e.classList.remove(a)),i.forEach(a=>e.classList.add(a)),this._previousFontSetClass=i,this.fontIcon!==this._previousFontIconClass&&!i.includes("mat-ligature-font")&&(this._previousFontIconClass&&e.classList.remove(this._previousFontIconClass),this.fontIcon&&e.classList.add(this.fontIcon),this._previousFontIconClass=this.fontIcon)}_cleanupFontValue(e){return typeof e=="string"?e.trim().split(" ")[0]:e}_prependPathToReferences(e){let i=this._elementsWithExternalReferences,a=e.startsWith("//")?`/.${e}`:e;i&&i.forEach((r,o)=>{r.forEach(s=>{o.setAttribute(s.name,`url('${a}#${s.value}')`)})})}_cacheChildrenWithExternalReferences(e){let i=e.querySelectorAll(Cw),a=this._elementsWithExternalReferences=this._elementsWithExternalReferences||new Map;for(let r=0;r<i.length;r++)Wf.forEach(o=>{let s=i[r],l=s.getAttribute(o),c=l?l.match(Ew):null;if(c){let d=a.get(s);d||(d=[],a.set(s,d)),d.push({name:o,value:c[1]})}})}_updateSvgIcon(e){if(this._svgNamespace=null,this._svgName=null,this._currentIconFetch.unsubscribe(),e){let[i,a]=this._splitIconName(e);i&&(this._svgNamespace=i),a&&(this._svgName=a),this._currentIconFetch=this._iconRegistry.getNamedSvgIcon(a,i).pipe(Fn(1)).subscribe(r=>this._setSvgElement(r),r=>{let o=`Error retrieving icon ${i}:${a}! ${r.message}`;this._errorHandler.handleError(new Error(o))})}}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){return T({type:t,selectors:[["mat-icon"]],hostAttrs:["role","img",1,"mat-icon","notranslate"],hostVars:10,hostBindings:function(a,r){a&2&&(fe("data-mat-icon-type",r._usingFontIcon()?"font":"svg")("data-mat-icon-name",r._svgName||r.fontIcon)("data-mat-icon-namespace",r._svgNamespace||r.fontSet)("fontIcon",r._usingFontIcon()?r.fontIcon:null),At(r.color?"mat-"+r.color:""),ee("mat-icon-inline",r.inline)("mat-icon-no-color",r.color!=="primary"&&r.color!=="accent"&&r.color!=="warn"))},inputs:{color:"color",inline:[2,"inline","inline",ve],svgIcon:"svgIcon",fontSet:"fontSet",fontIcon:"fontIcon"},exportAs:["matIcon"],ngContentSelectors:["*"],decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},styles:[`mat-icon, mat-icon.mat-primary, mat-icon.mat-accent, mat-icon.mat-warn {
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
`],encapsulation:2})})()}return t})(),Ot=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[me]})}return t})();var Ia=class{},Qs=class t{constructor(n,e){this.iconRegistry=n;this.sanitizer=e}iconRegistry;sanitizer;load(){}static \u0275fac=function(e){return new(e||t)(B(Zs),B(Hn))};static \u0275prov=te({token:t,factory:t.\u0275fac})};var Nn=class t{static contextPath=t.getContextPath();static getContextPath(){return document.location.pathname.split("/asyncapi-ui.html")[0]}static uiConfig=t.contextPath+"/ui-config";static docs=t.contextPath+"/docs";static getDocsForGroupEndpoint(n){return t.docs+`/${n}`}static getPublishEndpoint(n){return t.contextPath+`/plugin/${n}/publish`}};var Ne=class{static DEFAULT_SHOW_BINDINGS=!0;static DEFAULT_SHOW_HEADERS=!0;static DEFAULT_GROUP="default"},Js=class t extends Ne{constructor(e){super();this.http=e;this.uiConfig=this.http.get(Nn.uiConfig).pipe(cs(()=>Ue(this.fallbackConfig)),Ln()),this.uiConfig.subscribe(i=>{this.toggleIsShowBindings(i.initialConfig.showBindings),this.toggleIsShowHeaders(i.initialConfig.showHeaders)})}http;_getGroup=new ni(Ne.DEFAULT_GROUP);isGroup$=this._getGroup.asObservable();fallbackConfig={initialConfig:{showBindings:Ne.DEFAULT_SHOW_BINDINGS,showHeaders:Ne.DEFAULT_SHOW_HEADERS},groups:[]};_isShowBindings=new ni(Ne.DEFAULT_SHOW_BINDINGS);isShowBindings$=this._isShowBindings.asObservable();_isShowHeaders=new ni(Ne.DEFAULT_SHOW_HEADERS);isShowHeaders$=this._isShowHeaders.asObservable();uiConfig;toggleIsShowBindings(e){this._isShowBindings.next(e)}toggleIsShowHeaders(e){this._isShowHeaders.next(e)}changeGroup(e){this._getGroup.next(e)}static \u0275fac=function(i){return new(i||t)(B(Xt))};static \u0275prov=te({token:t,factory:t.\u0275fac})};var Jr="server-";var Rd="channel-";var Zt=class{value;rawValue;lineCount;constructor(n){this.rawValue=n,typeof n=="object"?Object.keys(n).length>0?this.value=JSON.stringify(n,null,2):this.value="":this.value=""+n,this.lineCount=this.value.split(`
`).length}};var tl=class extends Error{};function Pd(t,n){try{return n()}catch(e){throw e instanceof Error?new tl(t+" ("+e.message+")"):new tl(t+" ("+e+")")}}function Kf(t,n=()=>{}){try{return t()}catch(e){n!==void 0&&n(e);return}}function ji(t){return t.buttons===0||t.detail===0}function Vi(t){let n=t.touches&&t.touches[0]||t.changedTouches&&t.changedTouches[0];return!!n&&n.identifier===-1&&(n.radiusX==null||n.radiusX===1)&&(n.radiusY==null||n.radiusY===1)}var Fd;function Yf(){if(Fd==null){let t=typeof document<"u"?document.head:null;Fd=!!(t&&(t.createShadowRoot||t.attachShadow))}return Fd}function Ld(t){if(Yf()){let n=t.getRootNode?t.getRootNode():null;if(typeof ShadowRoot<"u"&&ShadowRoot&&n instanceof ShadowRoot)return n}return null}function Bt(t){if(t.composedPath)try{return t.composedPath()[0]}catch{}return t.target}var Bd;try{Bd=typeof Intl<"u"&&Intl.v8BreakIterator}catch{Bd=!1}var Ie=(()=>{class t{_platformId=p(ri);isBrowser=this._platformId?hi(this._platformId):typeof document=="object"&&!!document;EDGE=this.isBrowser&&/(edge)/i.test(navigator.userAgent);TRIDENT=this.isBrowser&&/(msie|trident)/i.test(navigator.userAgent);BLINK=this.isBrowser&&!!(window.chrome||Bd)&&typeof CSS<"u"&&!this.EDGE&&!this.TRIDENT;WEBKIT=this.isBrowser&&/AppleWebKit/i.test(navigator.userAgent)&&!this.BLINK&&!this.EDGE&&!this.TRIDENT;IOS=this.isBrowser&&/iPad|iPhone|iPod/.test(navigator.userAgent)&&!("MSStream"in window);FIREFOX=this.isBrowser&&/(firefox|minefield)/i.test(navigator.userAgent);ANDROID=this.isBrowser&&/android/i.test(navigator.userAgent)&&!this.TRIDENT;SAFARI=this.isBrowser&&/safari/i.test(navigator.userAgent)&&this.WEBKIT;static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var eo;function Xf(){if(eo==null&&typeof window<"u")try{window.addEventListener("test",null,Object.defineProperty({},"passive",{get:()=>eo=!0}))}finally{eo=eo||!1}return eo}function Aa(t){return Xf()?t:!!t.capture}function qn(t,n=0){return Zf(t)?Number(t):arguments.length===2?n:0}function Zf(t){return!isNaN(parseFloat(t))&&!isNaN(Number(t))}function Qt(t){return t instanceof X?t.nativeElement:t}var Qf=new I("cdk-input-modality-detector-options"),Jf={ignoreKeys:[18,17,224,91,16]},eg=650,jd={passive:!0,capture:!0},tg=(()=>{class t{_platform=p(Ie);_listenerCleanups;modalityDetected;modalityChanged;get mostRecentModality(){return this._modality.value}_mostRecentTarget=null;_modality=new ni(null);_options;_lastTouchMs=0;_onKeydown=e=>{this._options?.ignoreKeys?.some(i=>i===e.keyCode)||(this._modality.next("keyboard"),this._mostRecentTarget=Bt(e))};_onMousedown=e=>{Date.now()-this._lastTouchMs<eg||(this._modality.next(ji(e)?"keyboard":"mouse"),this._mostRecentTarget=Bt(e))};_onTouchstart=e=>{if(Vi(e)){this._modality.next("keyboard");return}this._lastTouchMs=Date.now(),this._modality.next("touch"),this._mostRecentTarget=Bt(e)};constructor(){let e=p(K),i=p(Y),a=p(Qf,{optional:!0});if(this._options=N(N({},Jf),a),this.modalityDetected=this._modality.pipe(ms(1)),this.modalityChanged=this.modalityDetected.pipe(Cr()),this._platform.isBrowser){let r=p(yt).createRenderer(null,null);this._listenerCleanups=e.runOutsideAngular(()=>[r.listen(i,"keydown",this._onKeydown,jd),r.listen(i,"mousedown",this._onMousedown,jd),r.listen(i,"touchstart",this._onTouchstart,jd)])}}ngOnDestroy(){this._modality.complete(),this._listenerCleanups?.forEach(e=>e())}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),to=(function(t){return t[t.IMMEDIATE=0]="IMMEDIATE",t[t.EVENTUAL=1]="EVENTUAL",t})(to||{}),ng=new I("cdk-focus-monitor-default-options"),nl=Aa({passive:!0,capture:!0}),In=(()=>{class t{_ngZone=p(K);_platform=p(Ie);_inputModalityDetector=p(tg);_origin=null;_lastFocusOrigin=null;_windowFocused=!1;_windowFocusTimeoutId;_originTimeoutId;_originFromTouchInteraction=!1;_elementInfo=new Map;_monitoredElementCount=0;_rootNodeFocusListenerCount=new Map;_detectionMode;_windowFocusListener=()=>{this._windowFocused=!0,this._windowFocusTimeoutId=setTimeout(()=>this._windowFocused=!1)};_document=p(Y);_stopInputModalityDetector=new z;constructor(){let e=p(ng,{optional:!0});this._detectionMode=e?.detectionMode||to.IMMEDIATE}_rootNodeFocusAndBlurListener=e=>{let i=Bt(e);for(let a=i;a;a=a.parentElement)e.type==="focus"?this._onFocus(e,a):this._onBlur(e,a)};monitor(e,i=!1){let a=Qt(e);if(!this._platform.isBrowser||a.nodeType!==1)return Ue();let r=Ld(a)||this._document,o=this._elementInfo.get(a);if(o)return i&&(o.checkChildren=!0),o.subject;let s={checkChildren:i,subject:new z,rootNode:r};return this._elementInfo.set(a,s),this._registerGlobalListeners(s),s.subject}stopMonitoring(e){let i=Qt(e),a=this._elementInfo.get(i);a&&(a.subject.complete(),this._setClasses(i),this._elementInfo.delete(i),this._removeGlobalListeners(a))}focusVia(e,i,a){let r=Qt(e),o=this._document.activeElement;r===o?this._getClosestElementsInfo(r).forEach(([s,l])=>this._originChanged(s,i,l)):(this._setOrigin(i),typeof r.focus=="function"&&r.focus(a))}ngOnDestroy(){this._elementInfo.forEach((e,i)=>this.stopMonitoring(i))}_getWindow(){return this._document.defaultView||window}_getFocusOrigin(e){return this._origin?this._originFromTouchInteraction?this._shouldBeAttributedToTouch(e)?"touch":"program":this._origin:this._windowFocused&&this._lastFocusOrigin?this._lastFocusOrigin:e&&this._isLastInteractionFromInputLabel(e)?"mouse":"program"}_shouldBeAttributedToTouch(e){return this._detectionMode===to.EVENTUAL||!!e?.contains(this._inputModalityDetector._mostRecentTarget)}_setClasses(e,i){e.classList.toggle("cdk-focused",!!i),e.classList.toggle("cdk-touch-focused",i==="touch"),e.classList.toggle("cdk-keyboard-focused",i==="keyboard"),e.classList.toggle("cdk-mouse-focused",i==="mouse"),e.classList.toggle("cdk-program-focused",i==="program")}_setOrigin(e,i=!1){this._ngZone.runOutsideAngular(()=>{if(this._origin=e,this._originFromTouchInteraction=e==="touch"&&i,this._detectionMode===to.IMMEDIATE){clearTimeout(this._originTimeoutId);let a=this._originFromTouchInteraction?eg:1;this._originTimeoutId=setTimeout(()=>this._origin=null,a)}})}_onFocus(e,i){let a=this._elementInfo.get(i),r=Bt(e);!a||!a.checkChildren&&i!==r||this._originChanged(i,this._getFocusOrigin(r),a)}_onBlur(e,i){let a=this._elementInfo.get(i);!a||a.checkChildren&&e.relatedTarget instanceof Node&&i.contains(e.relatedTarget)||(this._setClasses(i),this._emitOrigin(a,null))}_emitOrigin(e,i){e.subject.observers.length&&this._ngZone.run(()=>e.subject.next(i))}_registerGlobalListeners(e){if(!this._platform.isBrowser)return;let i=e.rootNode,a=this._rootNodeFocusListenerCount.get(i)||0;a||this._ngZone.runOutsideAngular(()=>{i.addEventListener("focus",this._rootNodeFocusAndBlurListener,nl),i.addEventListener("blur",this._rootNodeFocusAndBlurListener,nl)}),this._rootNodeFocusListenerCount.set(i,a+1),++this._monitoredElementCount===1&&(this._ngZone.runOutsideAngular(()=>{this._getWindow().addEventListener("focus",this._windowFocusListener)}),this._inputModalityDetector.modalityDetected.pipe(Ae(this._stopInputModalityDetector)).subscribe(r=>{this._setOrigin(r,!0)}))}_removeGlobalListeners(e){let i=e.rootNode;if(this._rootNodeFocusListenerCount.has(i)){let a=this._rootNodeFocusListenerCount.get(i);a>1?this._rootNodeFocusListenerCount.set(i,a-1):(i.removeEventListener("focus",this._rootNodeFocusAndBlurListener,nl),i.removeEventListener("blur",this._rootNodeFocusAndBlurListener,nl),this._rootNodeFocusListenerCount.delete(i))}--this._monitoredElementCount||(this._getWindow().removeEventListener("focus",this._windowFocusListener),this._stopInputModalityDetector.next(),clearTimeout(this._windowFocusTimeoutId),clearTimeout(this._originTimeoutId))}_originChanged(e,i,a){this._setClasses(e,i),this._emitOrigin(a,i),this._lastFocusOrigin=i}_getClosestElementsInfo(e){let i=[];return this._elementInfo.forEach((a,r)=>{(r===e||a.checkChildren&&r.contains(e))&&i.push([r,a])}),i}_isLastInteractionFromInputLabel(e){let{_mostRecentTarget:i,mostRecentModality:a}=this._inputModalityDetector;if(a!=="mouse"||!i||i===e||e.nodeName!=="INPUT"&&e.nodeName!=="TEXTAREA"||e.disabled)return!1;let r=e.labels;if(r){for(let o=0;o<r.length;o++)if(r[o].contains(i))return!0}return!1}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();function Ta(t){return Array.isArray(t)?t:[t]}var ig=new Set,$i,il=(()=>{class t{_platform=p(Ie);_nonce=p(ga,{optional:!0});_matchMedia;constructor(){this._matchMedia=this._platform.isBrowser&&window.matchMedia?window.matchMedia.bind(window):Nw}matchMedia(e){return(this._platform.WEBKIT||this._platform.BLINK)&&Mw(e,this._nonce),this._matchMedia(e)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();function Mw(t,n){if(!ig.has(t))try{$i||($i=document.createElement("style"),n&&$i.setAttribute("nonce",n),$i.setAttribute("type","text/css"),document.head.appendChild($i)),$i.sheet&&($i.sheet.insertRule(`@media ${t.replace(/[{}]/g,"")} {body{ }}`,0),ig.add(t))}catch(e){console.error(e)}}function Nw(t){return{matches:t==="all"||t==="",media:t,addListener:()=>{},removeListener:()=>{}}}var Vd=(()=>{class t{_mediaMatcher=p(il);_zone=p(K);_queries=new Map;_destroySubject=new z;ngOnDestroy(){this._destroySubject.next(),this._destroySubject.complete()}isMatched(e){return ag(Ta(e)).some(a=>this._registerQuery(a).mql.matches)}observe(e){let a=ag(Ta(e)).map(o=>this._registerQuery(o).observable),r=Xu(a);return r=Zu(r.pipe(Fn(1)),r.pipe(ms(1),ii(0))),r.pipe(Me(o=>{let s={matches:!1,breakpoints:{}};return o.forEach(({matches:l,query:c})=>{s.matches=s.matches||l,s.breakpoints[c]=l}),s}))}_registerQuery(e){if(this._queries.has(e))return this._queries.get(e);let i=this._mediaMatcher.matchMedia(e),r={observable:new Nt(o=>{let s=l=>this._zone.run(()=>o.next(l));return i.addListener(s),()=>{i.removeListener(s)}}).pipe(_t(i),Me(({matches:o})=>({query:e,matches:o})),Ae(this._destroySubject)),mql:i};return this._queries.set(e,r),r}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();function ag(t){return t.map(n=>n.split(",")).reduce((n,e)=>n.concat(e)).map(n=>n.trim())}function Iw(t){if(t.type==="characterData"&&t.target instanceof Comment)return!0;if(t.type==="childList"){for(let n=0;n<t.addedNodes.length;n++)if(!(t.addedNodes[n]instanceof Comment))return!1;for(let n=0;n<t.removedNodes.length;n++)if(!(t.removedNodes[n]instanceof Comment))return!1;return!0}return!1}var rg=(()=>{class t{create(e){return typeof MutationObserver>"u"?null:new MutationObserver(e)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),Aw=(()=>{class t{_mutationObserverFactory=p(rg);_observedElements=new Map;_ngZone=p(K);ngOnDestroy(){this._observedElements.forEach((e,i)=>this._cleanupObserver(i))}observe(e){let i=Qt(e);return new Nt(a=>{let o=this._observeElement(i).pipe(Me(s=>s.filter(l=>!Iw(l))),Ke(s=>!!s.length)).subscribe(s=>{this._ngZone.run(()=>{a.next(s)})});return()=>{o.unsubscribe(),this._unobserveElement(i)}})}_observeElement(e){return this._ngZone.runOutsideAngular(()=>{if(this._observedElements.has(e))this._observedElements.get(e).count++;else{let i=new z,a=this._mutationObserverFactory.create(r=>i.next(r));a&&a.observe(e,{characterData:!0,childList:!0,subtree:!0}),this._observedElements.set(e,{observer:a,stream:i,count:1})}return this._observedElements.get(e).stream})}_unobserveElement(e){this._observedElements.has(e)&&(this._observedElements.get(e).count--,this._observedElements.get(e).count||this._cleanupObserver(e))}_cleanupObserver(e){if(this._observedElements.has(e)){let{observer:i,stream:a}=this._observedElements.get(e);i&&i.disconnect(),a.complete(),this._observedElements.delete(e)}}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),og=(()=>{class t{_contentObserver=p(Aw);_elementRef=p(X);event=new Te;get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._disabled?this._unsubscribe():this._subscribe()}_disabled=!1;get debounce(){return this._debounce}set debounce(e){this._debounce=qn(e),this._subscribe()}_debounce;_currentSubscription=null;ngAfterContentInit(){!this._currentSubscription&&!this.disabled&&this._subscribe()}ngOnDestroy(){this._unsubscribe()}_subscribe(){this._unsubscribe();let e=this._contentObserver.observe(this._elementRef);this._currentSubscription=(this.debounce?e.pipe(ii(this.debounce)):e).subscribe(this.event)}_unsubscribe(){this._currentSubscription?.unsubscribe()}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","cdkObserveContent",""]],inputs:{disabled:[2,"cdkObserveContentDisabled","disabled",ve],debounce:"debounce"},outputs:{event:"cdkObserveContent"},exportAs:["cdkObserveContent"]})}return t})(),al=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({providers:[rg]})}return t})();var ol=(()=>{class t{_platform=p(Ie);isDisabled(e){return e.hasAttribute("disabled")}isVisible(e){return Ow(e)&&getComputedStyle(e).visibility==="visible"}isTabbable(e){if(!this._platform.isBrowser)return!1;let i=Tw($w(e));if(i&&(sg(i)===-1||!this.isVisible(i)))return!1;let a=e.nodeName.toLowerCase(),r=sg(e);return e.hasAttribute("contenteditable")?r!==-1:a==="iframe"||a==="object"||this._platform.WEBKIT&&this._platform.IOS&&!jw(e)?!1:a==="audio"?e.hasAttribute("controls")?r!==-1:!1:a==="video"?r===-1?!1:r!==null?!0:this._platform.FIREFOX||e.hasAttribute("controls"):e.tabIndex>=0}isFocusable(e,i){return Vw(e)&&!this.isDisabled(e)&&(i?.ignoreInert||!e.closest?.("[inert]"))&&(i?.ignoreVisibility||this.isVisible(e))}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();function Tw(t){try{return t.frameElement}catch{return null}}function Ow(t){return!!(t.offsetWidth||t.offsetHeight||typeof t.getClientRects=="function"&&t.getClientRects().length)}function Rw(t){let n=t.nodeName.toLowerCase();return n==="input"||n==="select"||n==="button"||n==="textarea"}function Pw(t){return Lw(t)&&t.type=="hidden"}function Fw(t){return Bw(t)&&t.hasAttribute("href")}function Lw(t){return t.nodeName.toLowerCase()=="input"}function Bw(t){return t.nodeName.toLowerCase()=="a"}function lg(t){if(!t.hasAttribute("tabindex")||t.tabIndex===void 0)return!1;let n=t.getAttribute("tabindex");return!!(n&&!isNaN(parseInt(n,10)))}function sg(t){if(!lg(t))return null;let n=parseInt(t.getAttribute("tabindex")||"",10);return isNaN(n)?-1:n}function jw(t){let n=t.nodeName.toLowerCase(),e=n==="input"&&t.type;return e==="text"||e==="password"||n==="select"||n==="textarea"}function Vw(t){return Pw(t)?!1:Rw(t)||Fw(t)||t.hasAttribute("contenteditable")||lg(t)}function $w(t){return t.ownerDocument&&t.ownerDocument.defaultView||window}var rl=class{_element;_checker;_ngZone;_document;_injector;_startAnchor=null;_endAnchor=null;_hasAttached=!1;startAnchorListener=()=>{!this.focusLastTabbableElement()&&this._checker.isFocusable(this._element)&&this._element.focus()};endAnchorListener=()=>{!this.focusFirstTabbableElement()&&this._checker.isFocusable(this._element)&&this._element.focus()};get enabled(){return this._enabled}set enabled(n){this._enabled=n,this._startAnchor&&this._endAnchor&&(this._toggleAnchorTabIndex(n,this._startAnchor),this._toggleAnchorTabIndex(n,this._endAnchor))}_enabled=!0;constructor(n,e,i,a,r=!1,o){this._element=n,this._checker=e,this._ngZone=i,this._document=a,this._injector=o,r||this.attachAnchors()}destroy(){let n=this._startAnchor,e=this._endAnchor;n&&(n.removeEventListener("focus",this.startAnchorListener),n.remove()),e&&(e.removeEventListener("focus",this.endAnchorListener),e.remove()),this._startAnchor=this._endAnchor=null,this._hasAttached=!1}attachAnchors(){return this._hasAttached?!0:(this._ngZone.runOutsideAngular(()=>{this._startAnchor||(this._startAnchor=this._createAnchor(),this._startAnchor.addEventListener("focus",this.startAnchorListener)),this._endAnchor||(this._endAnchor=this._createAnchor(),this._endAnchor.addEventListener("focus",this.endAnchorListener))}),this._element.parentNode&&(this._element.parentNode.insertBefore(this._startAnchor,this._element),this._element.parentNode.insertBefore(this._endAnchor,this._element.nextSibling),this._hasAttached=!0),this._hasAttached)}focusInitialElementWhenReady(n){return new Promise(e=>{this._executeOnStable(()=>e(this.focusInitialElement(n)))})}focusFirstTabbableElementWhenReady(n){return new Promise(e=>{this._executeOnStable(()=>e(this.focusFirstTabbableElement(n)))})}focusLastTabbableElementWhenReady(n){return new Promise(e=>{this._executeOnStable(()=>e(this.focusLastTabbableElement(n)))})}_getRegionBoundary(n){let e=this._element.querySelectorAll(`[cdk-focus-region-${n}], [cdkFocusRegion${n}], [cdk-focus-${n}]`);return n=="start"?e.length?e[0]:this._getFirstTabbableElement(this._element):e.length?e[e.length-1]:this._getLastTabbableElement(this._element)}focusInitialElement(n){let e=this._element.querySelector("[cdk-focus-initial], [cdkFocusInitial]");if(e){if(!this._checker.isFocusable(e)){let i=this._getFirstTabbableElement(e);return i?.focus(n),!!i}return e.focus(n),!0}return this.focusFirstTabbableElement(n)}focusFirstTabbableElement(n){let e=this._getRegionBoundary("start");return e&&e.focus(n),!!e}focusLastTabbableElement(n){let e=this._getRegionBoundary("end");return e&&e.focus(n),!!e}hasAttached(){return this._hasAttached}_getFirstTabbableElement(n){if(this._checker.isFocusable(n)&&this._checker.isTabbable(n))return n;let e=n.children;for(let i=0;i<e.length;i++){let a=e[i].nodeType===this._document.ELEMENT_NODE?this._getFirstTabbableElement(e[i]):null;if(a)return a}return null}_getLastTabbableElement(n){if(this._checker.isFocusable(n)&&this._checker.isTabbable(n))return n;let e=n.children;for(let i=e.length-1;i>=0;i--){let a=e[i].nodeType===this._document.ELEMENT_NODE?this._getLastTabbableElement(e[i]):null;if(a)return a}return null}_createAnchor(){let n=this._document.createElement("div");return this._toggleAnchorTabIndex(this._enabled,n),n.classList.add("cdk-visually-hidden"),n.classList.add("cdk-focus-trap-anchor"),n.setAttribute("aria-hidden","true"),n}_toggleAnchorTabIndex(n,e){n?e.setAttribute("tabindex","0"):e.removeAttribute("tabindex")}toggleAnchors(n){this._startAnchor&&this._endAnchor&&(this._toggleAnchorTabIndex(n,this._startAnchor),this._toggleAnchorTabIndex(n,this._endAnchor))}_executeOnStable(n){ht(n,{injector:this._injector})}},$d=(()=>{class t{_checker=p(ol);_ngZone=p(K);_document=p(Y);_injector=p(re);constructor(){p(dt).load(Na)}create(e,i=!1){return new rl(e,this._checker,this._ngZone,this._document,i,this._injector)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var cg=new I("liveAnnouncerElement",{providedIn:"root",factory:()=>null}),dg=new I("LIVE_ANNOUNCER_DEFAULT_OPTIONS"),zw=0,zd=(()=>{class t{_ngZone=p(K);_defaultOptions=p(dg,{optional:!0});_liveElement;_document=p(Y);_sanitizer=p(Hn);_previousTimeout;_currentPromise;_currentResolve;constructor(){let e=p(cg,{optional:!0});this._liveElement=e||this._createLiveElement()}announce(e,...i){let a=this._defaultOptions,r,o;return i.length===1&&typeof i[0]=="number"?o=i[0]:[r,o]=i,this.clear(),clearTimeout(this._previousTimeout),r||(r=a&&a.politeness?a.politeness:"polite"),o==null&&a&&(o=a.duration),this._liveElement.setAttribute("aria-live",r),this._liveElement.id&&this._exposeAnnouncerToModals(this._liveElement.id),this._ngZone.runOutsideAngular(()=>(this._currentPromise||(this._currentPromise=new Promise(s=>this._currentResolve=s)),clearTimeout(this._previousTimeout),this._previousTimeout=setTimeout(()=>{!e||typeof e=="string"?this._liveElement.textContent=e:$f(this._liveElement,e,this._sanitizer),typeof o=="number"&&(this._previousTimeout=setTimeout(()=>this.clear(),o)),this._currentResolve?.(),this._currentPromise=this._currentResolve=void 0},100),this._currentPromise))}clear(){this._liveElement&&(this._liveElement.textContent="")}ngOnDestroy(){clearTimeout(this._previousTimeout),this._liveElement?.remove(),this._liveElement=null,this._currentResolve?.(),this._currentPromise=this._currentResolve=void 0}_createLiveElement(){let e="cdk-live-announcer-element",i=this._document.getElementsByClassName(e),a=this._document.createElement("div");for(let r=0;r<i.length;r++)i[r].remove();return a.classList.add(e),a.classList.add("cdk-visually-hidden"),a.setAttribute("aria-atomic","true"),a.setAttribute("aria-live","polite"),a.id=`cdk-live-announcer-${zw++}`,this._document.body.appendChild(a),a}_exposeAnnouncerToModals(e){let i=this._document.querySelectorAll('body > .cdk-overlay-container [aria-modal="true"]');for(let a=0;a<i.length;a++){let r=i[a],o=r.getAttribute("aria-owns");o?o.indexOf(e)===-1&&r.setAttribute("aria-owns",o+" "+e):r.setAttribute("aria-owns",e)}}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var Hw=200,sl=class{_letterKeyStream=new z;_items=[];_selectedItemIndex=-1;_pressedLetters=[];_skipPredicateFn;_selectedItem=new z;selectedItem=this._selectedItem;constructor(n,e){let i=typeof e?.debounceInterval=="number"?e.debounceInterval:Hw;e?.skipPredicate&&(this._skipPredicateFn=e.skipPredicate),this.setItems(n),this._setupKeyHandler(i)}destroy(){this._pressedLetters=[],this._letterKeyStream.complete(),this._selectedItem.complete()}setCurrentSelectedItemIndex(n){this._selectedItemIndex=n}setItems(n){this._items=n}handleKey(n){let e=n.keyCode;n.key&&n.key.length===1?this._letterKeyStream.next(n.key.toLocaleUpperCase()):(e>=65&&e<=90||e>=48&&e<=57)&&this._letterKeyStream.next(String.fromCharCode(e))}isTyping(){return this._pressedLetters.length>0}reset(){this._pressedLetters=[]}_setupKeyHandler(n){this._letterKeyStream.pipe(ai(e=>this._pressedLetters.push(e)),ii(n),Ke(()=>this._pressedLetters.length>0),Me(()=>this._pressedLetters.join("").toLocaleUpperCase())).subscribe(e=>{for(let i=1;i<this._items.length+1;i++){let a=(this._selectedItemIndex+i)%this._items.length,r=this._items[a];if(!this._skipPredicateFn?.(r)&&r.getLabel?.().toLocaleUpperCase().trim().indexOf(e)===0){this._selectedItem.next(r);break}}this._pressedLetters=[]})}};function vi(t,...n){return n.length?n.some(e=>t[e]):t.altKey||t.shiftKey||t.ctrlKey||t.metaKey}var ll=class{_items;_activeItemIndex=ne(-1);_activeItem=ne(null);_wrap=!1;_typeaheadSubscription=Mt.EMPTY;_itemChangesSubscription;_vertical=!0;_horizontal=null;_allowedModifierKeys=[];_homeAndEnd=!1;_pageUpAndDown={enabled:!1,delta:10};_effectRef;_typeahead;_skipPredicateFn=n=>n.disabled;constructor(n,e){this._items=n,n instanceof Vn?this._itemChangesSubscription=n.changes.subscribe(i=>this._itemsChanged(i.toArray())):oi(n)&&(this._effectRef=Ti(()=>this._itemsChanged(n()),{injector:e}))}tabOut=new z;change=new z;skipPredicate(n){return this._skipPredicateFn=n,this}withWrap(n=!0){return this._wrap=n,this}withVerticalOrientation(n=!0){return this._vertical=n,this}withHorizontalOrientation(n){return this._horizontal=n,this}withAllowedModifierKeys(n){return this._allowedModifierKeys=n,this}withTypeAhead(n=200){this._typeaheadSubscription.unsubscribe();let e=this._getItemsArray();return this._typeahead=new sl(e,{debounceInterval:typeof n=="number"?n:void 0,skipPredicate:i=>this._skipPredicateFn(i)}),this._typeaheadSubscription=this._typeahead.selectedItem.subscribe(i=>{this.setActiveItem(i)}),this}cancelTypeahead(){return this._typeahead?.reset(),this}withHomeAndEnd(n=!0){return this._homeAndEnd=n,this}withPageUpDown(n=!0,e=10){return this._pageUpAndDown={enabled:n,delta:e},this}setActiveItem(n){let e=this._activeItem();this.updateActiveItem(n),this._activeItem()!==e&&this.change.next(this._activeItemIndex())}onKeydown(n){let e=n.keyCode,a=["altKey","ctrlKey","metaKey","shiftKey"].every(r=>!n[r]||this._allowedModifierKeys.indexOf(r)>-1);switch(e){case 9:this.tabOut.next();return;case 40:if(this._vertical&&a){this.setNextItemActive();break}else return;case 38:if(this._vertical&&a){this.setPreviousItemActive();break}else return;case 39:if(this._horizontal&&a){this._horizontal==="rtl"?this.setPreviousItemActive():this.setNextItemActive();break}else return;case 37:if(this._horizontal&&a){this._horizontal==="rtl"?this.setNextItemActive():this.setPreviousItemActive();break}else return;case 36:if(this._homeAndEnd&&a){this.setFirstItemActive();break}else return;case 35:if(this._homeAndEnd&&a){this.setLastItemActive();break}else return;case 33:if(this._pageUpAndDown.enabled&&a){let r=this._activeItemIndex()-this._pageUpAndDown.delta;this._setActiveItemByIndex(r>0?r:0,1);break}else return;case 34:if(this._pageUpAndDown.enabled&&a){let r=this._activeItemIndex()+this._pageUpAndDown.delta,o=this._getItemsArray().length;this._setActiveItemByIndex(r<o?r:o-1,-1);break}else return;default:(a||vi(n,"shiftKey"))&&this._typeahead?.handleKey(n);return}this._typeahead?.reset(),n.preventDefault()}get activeItemIndex(){return this._activeItemIndex()}get activeItem(){return this._activeItem()}isTyping(){return!!this._typeahead&&this._typeahead.isTyping()}setFirstItemActive(){this._setActiveItemByIndex(0,1)}setLastItemActive(){this._setActiveItemByIndex(this._getItemsArray().length-1,-1)}setNextItemActive(){this._activeItemIndex()<0?this.setFirstItemActive():this._setActiveItemByDelta(1)}setPreviousItemActive(){this._activeItemIndex()<0&&this._wrap?this.setLastItemActive():this._setActiveItemByDelta(-1)}updateActiveItem(n){let e=this._getItemsArray(),i=typeof n=="number"?n:e.indexOf(n),a=e[i];this._activeItem.set(a??null),this._activeItemIndex.set(i),this._typeahead?.setCurrentSelectedItemIndex(i)}destroy(){this._typeaheadSubscription.unsubscribe(),this._itemChangesSubscription?.unsubscribe(),this._effectRef?.destroy(),this._typeahead?.destroy(),this.tabOut.complete(),this.change.complete()}_setActiveItemByDelta(n){this._wrap?this._setActiveInWrapMode(n):this._setActiveInDefaultMode(n)}_setActiveInWrapMode(n){let e=this._getItemsArray();for(let i=1;i<=e.length;i++){let a=(this._activeItemIndex()+n*i+e.length)%e.length,r=e[a];if(!this._skipPredicateFn(r)){this.setActiveItem(a);return}}}_setActiveInDefaultMode(n){this._setActiveItemByIndex(this._activeItemIndex()+n,n)}_setActiveItemByIndex(n,e){let i=this._getItemsArray();if(i[n]){for(;this._skipPredicateFn(i[n]);)if(n+=e,!i[n])return;this.setActiveItem(n)}}_getItemsArray(){return oi(this._items)?this._items():this._items instanceof Vn?this._items.toArray():this._items}_itemsChanged(n){this._typeahead?.setItems(n);let e=this._activeItem();if(e){let i=n.indexOf(e);i>-1&&i!==this._activeItemIndex()&&(this._activeItemIndex.set(i),this._typeahead?.setCurrentSelectedItemIndex(i))}}};var zi=class extends ll{_origin="program";setFocusOrigin(n){return this._origin=n,this}setActiveItem(n){super.setActiveItem(n),this.activeItem&&this.activeItem.focus(this._origin)}};var ug=new Map,wt=class t{_appId=p(Dr);static _infix=`a${Math.floor(Math.random()*1e5).toString()}`;getId(n,e=!1){this._appId!=="ng"&&(n+=this._appId);let i=ug.get(n);return i===void 0?i=0:i++,ug.set(n,i),`${n}${e?t._infix+"-":""}${i}`}static \u0275fac=function(e){return new(e||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})};var hg={XSmall:"(max-width: 599.98px)",Small:"(min-width: 600px) and (max-width: 959.98px)",Medium:"(min-width: 960px) and (max-width: 1279.98px)",Large:"(min-width: 1280px) and (max-width: 1919.98px)",XLarge:"(min-width: 1920px)",Handset:"(max-width: 599.98px) and (orientation: portrait), (max-width: 959.98px) and (orientation: landscape)",Tablet:"(min-width: 600px) and (max-width: 839.98px) and (orientation: portrait), (min-width: 960px) and (max-width: 1279.98px) and (orientation: landscape)",Web:"(min-width: 840px) and (orientation: portrait), (min-width: 1280px) and (orientation: landscape)",HandsetPortrait:"(max-width: 599.98px) and (orientation: portrait)",TabletPortrait:"(min-width: 600px) and (max-width: 839.98px) and (orientation: portrait)",WebPortrait:"(min-width: 840px) and (orientation: portrait)",HandsetLandscape:"(max-width: 959.98px) and (orientation: landscape)",TabletLandscape:"(min-width: 960px) and (max-width: 1279.98px) and (orientation: landscape)",WebLandscape:"(min-width: 1280px) and (orientation: landscape)"};function qd(){return typeof __karma__<"u"&&!!__karma__||typeof jasmine<"u"&&!!jasmine||typeof jest<"u"&&!!jest||typeof Mocha<"u"&&!!Mocha}function nt(t){return t==null?"":typeof t=="string"?t:`${t}px`}var un=(function(t){return t[t.NORMAL=0]="NORMAL",t[t.NEGATED=1]="NEGATED",t[t.INVERTED=2]="INVERTED",t})(un||{}),cl,Hi;function dl(){if(Hi==null){if(typeof document!="object"||!document||typeof Element!="function"||!Element)return Hi=!1,Hi;if(document.documentElement?.style&&"scrollBehavior"in document.documentElement.style)Hi=!0;else{let t=Element.prototype.scrollTo;t?Hi=!/\{\s*\[native code\]\s*\}/.test(t.toString()):Hi=!1}}return Hi}function Oa(){if(typeof document!="object"||!document)return un.NORMAL;if(cl==null){let t=document.createElement("div"),n=t.style;t.dir="rtl",n.width="1px",n.overflow="auto",n.visibility="hidden",n.pointerEvents="none",n.position="absolute";let e=document.createElement("div"),i=e.style;i.width="2px",i.height="1px",t.appendChild(e),document.body.appendChild(t),cl=un.NORMAL,t.scrollLeft===0&&(t.scrollLeft=1,cl=t.scrollLeft===0?un.NEGATED:un.INVERTED),t.remove()}return cl}var Uw=20,no=(()=>{class t{_ngZone=p(K);_platform=p(Ie);_renderer=p(yt).createRenderer(null,null);_cleanupGlobalListener;_scrolled=new z;_scrolledCount=0;scrollContainers=new Map;register(e){this.scrollContainers.has(e)||this.scrollContainers.set(e,e.elementScrolled().subscribe(()=>this._scrolled.next(e)))}deregister(e){let i=this.scrollContainers.get(e);i&&(i.unsubscribe(),this.scrollContainers.delete(e))}scrolled(e=Uw){return this._platform.isBrowser?new Nt(i=>{this._cleanupGlobalListener||(this._cleanupGlobalListener=this._ngZone.runOutsideAngular(()=>this._renderer.listen("document","scroll",()=>this._scrolled.next())));let a=e>0?this._scrolled.pipe($c(e)).subscribe(i):this._scrolled.subscribe(i);return this._scrolledCount++,()=>{a.unsubscribe(),this._scrolledCount--,this._scrolledCount||(this._cleanupGlobalListener?.(),this._cleanupGlobalListener=void 0)}}):Ue()}ngOnDestroy(){this._cleanupGlobalListener?.(),this._cleanupGlobalListener=void 0,this.scrollContainers.forEach((e,i)=>this.deregister(i)),this._scrolled.complete()}ancestorScrolled(e,i){let a=this.getAncestorScrollContainers(e);return this.scrolled(i).pipe(Ke(r=>!r||a.indexOf(r)>-1))}getAncestorScrollContainers(e){let i=[];return this.scrollContainers.forEach((a,r)=>{this._targetContainsElement(r,e)&&i.push(r)}),i}_targetContainsElement(e,i){let a=Qt(i),r=e.getElementRef().nativeElement;do if(a==r)return!0;while(a=a.parentElement);return!1}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),Ra=(()=>{class t{elementRef=p(X);scrollDispatcher=p(no);ngZone=p(K);dir=p(Tt,{optional:!0});_scrollElement=this.elementRef.nativeElement;_destroyed=new z;_renderer=p(ct);_cleanupScroll;_elementScrolled=new z;ngOnInit(){this._cleanupScroll=this.ngZone.runOutsideAngular(()=>this._renderer.listen(this._scrollElement,"scroll",e=>this._elementScrolled.next(e))),this.scrollDispatcher.register(this)}ngOnDestroy(){this._cleanupScroll?.(),this._elementScrolled.complete(),this.scrollDispatcher.deregister(this),this._destroyed.next(),this._destroyed.complete()}elementScrolled(){return this._elementScrolled}getElementRef(){return this.elementRef}scrollTo(e){let i=this.elementRef.nativeElement,a=this.dir&&this.dir.value=="rtl";e.left==null&&(e.left=a?e.end:e.start),e.right==null&&(e.right=a?e.start:e.end),e.bottom!=null&&(e.top=i.scrollHeight-i.clientHeight-e.bottom),a&&Oa()!=un.NORMAL?(e.left!=null&&(e.right=i.scrollWidth-i.clientWidth-e.left),Oa()==un.INVERTED?e.left=e.right:Oa()==un.NEGATED&&(e.left=e.right?-e.right:e.right)):e.right!=null&&(e.left=i.scrollWidth-i.clientWidth-e.right),this._applyScrollToOptions(e)}_applyScrollToOptions(e){let i=this.elementRef.nativeElement;dl()?i.scrollTo(e):(e.top!=null&&(i.scrollTop=e.top),e.left!=null&&(i.scrollLeft=e.left))}measureScrollOffset(e){let i="left",a="right",r=this.elementRef.nativeElement;if(e=="top")return r.scrollTop;if(e=="bottom")return r.scrollHeight-r.clientHeight-r.scrollTop;let o=this.dir&&this.dir.value=="rtl";return e=="start"?e=o?a:i:e=="end"&&(e=o?i:a),o&&Oa()==un.INVERTED?e==i?r.scrollWidth-r.clientWidth-r.scrollLeft:r.scrollLeft:o&&Oa()==un.NEGATED?e==i?r.scrollLeft+r.scrollWidth-r.clientWidth:-r.scrollLeft:e==i?r.scrollLeft:r.scrollWidth-r.clientWidth-r.scrollLeft}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","cdk-scrollable",""],["","cdkScrollable",""]]})}return t})(),qw=20,Wn=(()=>{class t{_platform=p(Ie);_listeners;_viewportSize=null;_change=new z;_document=p(Y);constructor(){let e=p(K),i=p(yt).createRenderer(null,null);e.runOutsideAngular(()=>{if(this._platform.isBrowser){let a=r=>this._change.next(r);this._listeners=[i.listen("window","resize",a),i.listen("window","orientationchange",a)]}this.change().subscribe(()=>this._viewportSize=null)})}ngOnDestroy(){this._listeners?.forEach(e=>e()),this._change.complete()}getViewportSize(){this._viewportSize||this._updateViewportSize();let e={width:this._viewportSize.width,height:this._viewportSize.height};return this._platform.isBrowser||(this._viewportSize=null),e}getViewportRect(){let e=this.getViewportScrollPosition(),{width:i,height:a}=this.getViewportSize();return{top:e.top,left:e.left,bottom:e.top+a,right:e.left+i,height:a,width:i}}getViewportScrollPosition(){if(!this._platform.isBrowser)return{top:0,left:0};let e=this._document,i=this._getWindow(),a=e.documentElement,r=a.getBoundingClientRect(),o=-r.top||e.body?.scrollTop||i.scrollY||a.scrollTop||0,s=-r.left||e.body?.scrollLeft||i.scrollX||a.scrollLeft||0;return{top:o,left:s}}change(e=qw){return e>0?this._change.pipe($c(e)):this._change}_getWindow(){return this._document.defaultView||window}_updateViewportSize(){let e=this._getWindow();this._viewportSize=this._platform.isBrowser?{width:e.innerWidth,height:e.innerHeight}:{width:0,height:0}}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var Gn=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({})}return t})(),Gd=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[me,Gn,me,Gn]})}return t})();var io=class{_attachedHost=null;attach(n){return this._attachedHost=n,n.attach(this)}detach(){let n=this._attachedHost;n!=null&&(this._attachedHost=null,n.detach())}get isAttached(){return this._attachedHost!=null}setAttachedHost(n){this._attachedHost=n}},Pa=class extends io{component;viewContainerRef;injector;projectableNodes;bindings;directives;constructor(n,e,i,a,r,o){super(),this.component=n,this.viewContainerRef=e,this.injector=i,this.projectableNodes=a,this.bindings=r||null,this.directives=o||null}},Kn=class extends io{templateRef;viewContainerRef;context;injector;constructor(n,e,i,a){super(),this.templateRef=n,this.viewContainerRef=e,this.context=i,this.injector=a}get origin(){return this.templateRef.elementRef}attach(n,e=this.context){return this.context=e,super.attach(n)}detach(){return this.context=void 0,super.detach()}},Wd=class extends io{element;constructor(n){super(),this.element=n instanceof X?n.nativeElement:n}},Fa=class{_attachedPortal=null;_disposeFn=null;_isDisposed=!1;hasAttached(){return!!this._attachedPortal}attach(n){if(n instanceof Pa)return this._attachedPortal=n,this.attachComponentPortal(n);if(n instanceof Kn)return this._attachedPortal=n,this.attachTemplatePortal(n);if(this.attachDomPortal&&n instanceof Wd)return this._attachedPortal=n,this.attachDomPortal(n)}attachDomPortal=null;detach(){this._attachedPortal&&(this._attachedPortal.setAttachedHost(null),this._attachedPortal=null),this._invokeDisposeFn()}dispose(){this.hasAttached()&&this.detach(),this._invokeDisposeFn(),this._isDisposed=!0}setDisposeFn(n){this._disposeFn=n}_invokeDisposeFn(){this._disposeFn&&(this._disposeFn(),this._disposeFn=null)}},ao=class extends Fa{outletElement;_appRef;_defaultInjector;constructor(n,e,i){super(),this.outletElement=n,this._appRef=e,this._defaultInjector=i}attachComponentPortal(n){let e;if(n.viewContainerRef){let i=n.injector||n.viewContainerRef.injector,a=i.get(gs,null,{optional:!0})||void 0;e=n.viewContainerRef.createComponent(n.component,{index:n.viewContainerRef.length,injector:i,ngModuleRef:a,projectableNodes:n.projectableNodes||void 0,bindings:n.bindings||void 0,directives:n.directives||void 0}),this.setDisposeFn(()=>e.destroy())}else{let i=this._appRef,a=n.injector||this._defaultInjector||re.NULL,r=a.get(Bn,i.injector);e=ws(n.component,{elementInjector:a,environmentInjector:r,projectableNodes:n.projectableNodes||void 0,bindings:n.bindings||void 0,directives:n.directives||void 0}),i.attachView(e.hostView),this.setDisposeFn(()=>{i.viewCount>0&&i.detachView(e.hostView),e.destroy()})}return this.outletElement.appendChild(this._getComponentRootNode(e)),this._attachedPortal=n,e}attachTemplatePortal(n){let e=n.viewContainerRef,i=e.createEmbeddedView(n.templateRef,n.context,{injector:n.injector});return i.rootNodes.forEach(a=>this.outletElement.appendChild(a)),i.detectChanges(),this.setDisposeFn(()=>{let a=e.indexOf(i);a!==-1&&e.remove(a)}),this._attachedPortal=n,i}attachDomPortal=n=>{let e=n.element;e.parentNode;let i=this.outletElement.ownerDocument.createComment("dom-portal");e.parentNode.insertBefore(i,e),this.outletElement.appendChild(e),this._attachedPortal=n,super.setDisposeFn(()=>{i.parentNode&&i.parentNode.replaceChild(e,i)})};dispose(){super.dispose(),this.outletElement.remove()}_getComponentRootNode(n){return n.hostView.rootNodes[0]}};var pl=(()=>{class t extends Fa{_moduleRef=p(gs,{optional:!0});_document=p(Y);_viewContainerRef=p(wn);_isInitialized=!1;_attachedRef=null;get portal(){return this._attachedPortal}set portal(e){this.hasAttached()&&!e&&!this._isInitialized||(this.hasAttached()&&super.detach(),e&&super.attach(e),this._attachedPortal=e||null)}attached=new Te;get attachedRef(){return this._attachedRef}ngOnInit(){this._isInitialized=!0}ngOnDestroy(){super.dispose(),this._attachedRef=this._attachedPortal=null}attachComponentPortal(e){e.setAttachedHost(this);let i=e.viewContainerRef!=null?e.viewContainerRef:this._viewContainerRef,a=i.createComponent(e.component,{index:i.length,injector:e.injector||i.injector,projectableNodes:e.projectableNodes||void 0,ngModuleRef:this._moduleRef||void 0,bindings:e.bindings||void 0,directives:e.directives||void 0});return i!==this._viewContainerRef&&this._getRootNode().appendChild(a.hostView.rootNodes[0]),super.setDisposeFn(()=>a.destroy()),this._attachedPortal=e,this._attachedRef=a,this.attached.emit(a),a}attachTemplatePortal(e){e.setAttachedHost(this);let i=this._viewContainerRef.createEmbeddedView(e.templateRef,e.context,{injector:e.injector});return super.setDisposeFn(()=>this._viewContainerRef.clear()),this._attachedPortal=e,this._attachedRef=i,this.attached.emit(i),i}attachDomPortal=e=>{let i=e.element;i.parentNode;let a=this._document.createComment("dom-portal");e.setAttachedHost(this),i.parentNode.insertBefore(a,i),this._getRootNode().appendChild(i),this._attachedPortal=e,super.setDisposeFn(()=>{a.parentNode&&a.parentNode.replaceChild(i,a)})};_getRootNode(){let e=this._viewContainerRef.element.nativeElement;return e.nodeType===e.ELEMENT_NODE?e:e.parentNode}static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(t)))(a||t)}})();static \u0275dir=q({type:t,selectors:[["","cdkPortalOutlet",""]],inputs:{portal:[0,"cdkPortalOutlet","portal"]},outputs:{attached:"attached"},exportAs:["cdkPortalOutlet"],features:[Ve]})}return t})(),La=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({})}return t})();var fg=dl();function wg(t){return new ul(t.get(Wn),t.get(Y))}var ul=class{_viewportRuler;_previousHTMLStyles={top:"",left:""};_previousScrollPosition;_isEnabled=!1;_document;constructor(n,e){this._viewportRuler=n,this._document=e}attach(){}enable(){if(this._canBeEnabled()){let n=this._document.documentElement;this._previousScrollPosition=this._viewportRuler.getViewportScrollPosition(),this._previousHTMLStyles.left=n.style.left||"",this._previousHTMLStyles.top=n.style.top||"",n.style.left=nt(-this._previousScrollPosition.left),n.style.top=nt(-this._previousScrollPosition.top),n.classList.add("cdk-global-scrollblock"),this._isEnabled=!0}}disable(){if(this._isEnabled){let n=this._document.documentElement,e=this._document.body,i=n.style,a=e.style,r=i.scrollBehavior||"",o=a.scrollBehavior||"";this._isEnabled=!1,i.left=this._previousHTMLStyles.left,i.top=this._previousHTMLStyles.top,n.classList.remove("cdk-global-scrollblock"),fg&&(i.scrollBehavior=a.scrollBehavior="auto"),window.scroll(this._previousScrollPosition.left,this._previousScrollPosition.top),fg&&(i.scrollBehavior=r,a.scrollBehavior=o)}}_canBeEnabled(){if(this._document.documentElement.classList.contains("cdk-global-scrollblock")||this._isEnabled)return!1;let e=this._document.documentElement,i=this._viewportRuler.getViewportSize();return e.scrollHeight>i.height||e.scrollWidth>i.width}};function Sg(t,n){return new hl(t.get(no),t.get(K),t.get(Wn),n)}var hl=class{_scrollDispatcher;_ngZone;_viewportRuler;_config;_scrollSubscription=null;_overlayRef;_initialScrollPosition;constructor(n,e,i,a){this._scrollDispatcher=n,this._ngZone=e,this._viewportRuler=i,this._config=a}attach(n){this._overlayRef,this._overlayRef=n}enable(){if(this._scrollSubscription)return;let n=this._scrollDispatcher.scrolled(0).pipe(Ke(e=>!e||!this._overlayRef.overlayElement.contains(e.getElementRef().nativeElement)));this._config&&this._config.threshold&&this._config.threshold>1?(this._initialScrollPosition=this._viewportRuler.getViewportScrollPosition().top,this._scrollSubscription=n.subscribe(()=>{let e=this._viewportRuler.getViewportScrollPosition().top;Math.abs(e-this._initialScrollPosition)>this._config.threshold?this._detach():this._overlayRef.updatePosition()})):this._scrollSubscription=n.subscribe(this._detach)}disable(){this._scrollSubscription&&(this._scrollSubscription.unsubscribe(),this._scrollSubscription=null)}detach(){this.disable(),this._overlayRef=null}_detach=()=>{this.disable(),this._overlayRef.hasAttached()&&this._ngZone.run(()=>this._overlayRef.detach())}};var ro=class{enable(){}disable(){}attach(){}};function Yd(t,n){return n.some(e=>{let i=t.bottom<e.top,a=t.top>e.bottom,r=t.right<e.left,o=t.left>e.right;return i||a||r||o})}function gg(t,n){return n.some(e=>{let i=t.top<e.top,a=t.bottom>e.bottom,r=t.left<e.left,o=t.right>e.right;return i||a||r||o})}function oo(t,n){return new fl(t.get(no),t.get(Wn),t.get(K),n)}var fl=class{_scrollDispatcher;_viewportRuler;_ngZone;_config;_scrollSubscription=null;_overlayRef;constructor(n,e,i,a){this._scrollDispatcher=n,this._viewportRuler=e,this._ngZone=i,this._config=a}attach(n){this._overlayRef,this._overlayRef=n}enable(){if(!this._scrollSubscription){let n=this._config?this._config.scrollThrottle:0;this._scrollSubscription=this._scrollDispatcher.scrolled(n).subscribe(()=>{if(this._overlayRef.updatePosition(),this._config&&this._config.autoClose){let e=this._overlayRef.overlayElement.getBoundingClientRect(),{width:i,height:a}=this._viewportRuler.getViewportSize();Yd(e,[{width:i,height:a,bottom:a,right:i,top:0,left:0}])&&(this.disable(),this._ngZone.run(()=>this._overlayRef.detach()))}})}}disable(){this._scrollSubscription&&(this._scrollSubscription.unsubscribe(),this._scrollSubscription=null)}detach(){this.disable(),this._overlayRef=null}},kg=(()=>{class t{_injector=p(re);noop=()=>new ro;close=e=>Sg(this._injector,e);block=()=>wg(this._injector);reposition=e=>oo(this._injector,e);static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),_i=class{positionStrategy;scrollStrategy=new ro;panelClass="";hasBackdrop=!1;backdropClass="cdk-overlay-dark-backdrop";disableAnimations;width;height;minWidth;minHeight;maxWidth;maxHeight;direction;disposeOnNavigation=!1;usePopover;eventPredicate;constructor(n){if(n){let e=Object.keys(n);for(let i of e)n[i]!==void 0&&(this[i]=n[i])}}};var gl=class{connectionPair;scrollableViewProperties;constructor(n,e){this.connectionPair=n,this.scrollableViewProperties=e}};var Cg=(()=>{class t{_attachedOverlays=[];_document=p(Y);_isAttached=!1;ngOnDestroy(){this.detach()}add(e){this.remove(e),this._attachedOverlays.push(e)}remove(e){let i=this._attachedOverlays.indexOf(e);i>-1&&this._attachedOverlays.splice(i,1),this._attachedOverlays.length===0&&this.detach()}canReceiveEvent(e,i,a){return a.observers.length<1?!1:e.eventPredicate?e.eventPredicate(i):!0}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),Eg=(()=>{class t extends Cg{_ngZone=p(K);_renderer=p(yt).createRenderer(null,null);_cleanupKeydown;add(e){super.add(e),this._isAttached||(this._ngZone.runOutsideAngular(()=>{this._cleanupKeydown=this._renderer.listen("body","keydown",this._keydownListener)}),this._isAttached=!0)}detach(){this._isAttached&&(this._cleanupKeydown?.(),this._isAttached=!1)}_keydownListener=e=>{let i=this._attachedOverlays;for(let a=i.length-1;a>-1;a--){let r=i[a];if(this.canReceiveEvent(r,e,r._keydownEvents)){this._ngZone.run(()=>r._keydownEvents.next(e));break}}};static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),Dg=(()=>{class t extends Cg{_platform=p(Ie);_ngZone=p(K);_renderer=p(yt).createRenderer(null,null);_cursorOriginalValue;_cursorStyleIsSet=!1;_pointerDownEventTarget=null;_cleanups;add(e){if(super.add(e),!this._isAttached){let i=this._document.body,a={capture:!0},r=this._renderer;this._cleanups=this._ngZone.runOutsideAngular(()=>[r.listen(i,"pointerdown",this._pointerDownListener,a),r.listen(i,"click",this._clickListener,a),r.listen(i,"auxclick",this._clickListener,a),r.listen(i,"contextmenu",this._clickListener,a)]),this._platform.IOS&&!this._cursorStyleIsSet&&(this._cursorOriginalValue=i.style.cursor,i.style.cursor="pointer",this._cursorStyleIsSet=!0),this._isAttached=!0}}detach(){this._isAttached&&(this._cleanups?.forEach(e=>e()),this._cleanups=void 0,this._platform.IOS&&this._cursorStyleIsSet&&(this._document.body.style.cursor=this._cursorOriginalValue,this._cursorStyleIsSet=!1),this._isAttached=!1)}_pointerDownListener=e=>{this._pointerDownEventTarget=Bt(e)};_clickListener=e=>{let i=Bt(e),a=e.type==="click"&&this._pointerDownEventTarget?this._pointerDownEventTarget:i;this._pointerDownEventTarget=null;let r=this._attachedOverlays.slice();for(let o=r.length-1;o>-1;o--){let s=r[o],l=s._outsidePointerEvents;if(!(!s.hasAttached()||!this.canReceiveEvent(s,e,l))){if(bg(s.overlayElement,i)||bg(s.overlayElement,a))break;this._ngZone?this._ngZone.run(()=>l.next(e)):l.next(e)}}};static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();function bg(t,n){let e=typeof ShadowRoot<"u"&&ShadowRoot,i=n;for(;i;){if(i===t)return!0;i=e&&i instanceof ShadowRoot?i.host:i.parentNode}return!1}var Mg=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["ng-component"]],hostAttrs:["cdk-overlay-style-loader",""],decls:0,vars:0,template:function(i,a){},styles:[`.cdk-overlay-container, .cdk-global-overlay-wrapper {
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
`],encapsulation:2})}return t})(),Ng=(()=>{class t{_platform=p(Ie);_containerElement;_document=p(Y);_styleLoader=p(dt);ngOnDestroy(){this._containerElement?.remove()}getContainerElement(){return this._loadStyles(),this._containerElement||this._createContainer(),this._containerElement}_createContainer(){let e="cdk-overlay-container";if(this._platform.isBrowser||qd()){let a=this._document.querySelectorAll(`.${e}[platform="server"], .${e}[platform="test"]`);for(let r=0;r<a.length;r++)a[r].remove()}let i=this._document.createElement("div");i.classList.add(e),qd()?i.setAttribute("platform","test"):this._platform.isBrowser||i.setAttribute("platform","server"),this._document.body.appendChild(i),this._containerElement=i}_loadStyles(){this._styleLoader.load(Mg)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),Xd=class{_renderer;_ngZone;element;_cleanupClick;_cleanupTransitionEnd;_fallbackTimeout;constructor(n,e,i,a){this._renderer=e,this._ngZone=i,this.element=n.createElement("div"),this.element.classList.add("cdk-overlay-backdrop"),this._cleanupClick=e.listen(this.element,"click",a)}detach(){this._ngZone.runOutsideAngular(()=>{let n=this.element;clearTimeout(this._fallbackTimeout),this._cleanupTransitionEnd?.(),this._cleanupTransitionEnd=this._renderer.listen(n,"transitionend",this.dispose),this._fallbackTimeout=setTimeout(this.dispose,500),n.style.pointerEvents="none",n.classList.remove("cdk-overlay-backdrop-showing")})}dispose=()=>{clearTimeout(this._fallbackTimeout),this._cleanupClick?.(),this._cleanupTransitionEnd?.(),this._cleanupClick=this._cleanupTransitionEnd=this._fallbackTimeout=void 0,this.element.remove()}};function Zd(t){return t&&t.nodeType===1}var Kd=ne([]),bl=class{_portalOutlet;_host;_pane;_config;_ngZone;_keyboardDispatcher;_document;_location;_outsideClickDispatcher;_animationsDisabled;_injector;_renderer;_backdropClick=new z;_attachments=new z;_detachments=new z;_positionStrategy;_scrollStrategy;_locationChanges;_backdropRef=null;_detachContentMutationObserver;_detachContentAfterRenderRef;_disposed=!1;_previousHostParent;_keydownEvents=new z;_outsidePointerEvents=new z;_afterNextRenderRef;constructor(n,e,i,a,r,o,s,l,c,d=!1,m,u){this._portalOutlet=n,this._host=e,this._pane=i,this._config=a,this._ngZone=r,this._keyboardDispatcher=o,this._document=s,this._location=l,this._outsideClickDispatcher=c,this._animationsDisabled=d,this._injector=m,this._renderer=u,a.scrollStrategy&&(this._scrollStrategy=a.scrollStrategy,this._scrollStrategy.attach(this)),this._positionStrategy=a.positionStrategy}get overlayElement(){return this._pane}get backdropElement(){return this._backdropRef?.element||null}get hostElement(){return this._host}get eventPredicate(){return this._config?.eventPredicate||null}attach(n){if(this._disposed)return null;this._attachHost();let e=this._portalOutlet.attach(n);if(this._positionStrategy?.attach(this),this._updateStackingOrder(),this._updateElementSize(),this._updateElementDirection(),dn(()=>{Kd.update(i=>i.includes(this)?i:[...i,this])}),this._scrollStrategy&&this._scrollStrategy.enable(),this._afterNextRenderRef?.destroy(),this._afterNextRenderRef=ht(()=>{this.hasAttached()&&this.updatePosition()},{injector:this._injector}),this._togglePointerEvents(!0),this._config.hasBackdrop&&this._attachBackdrop(),this._config.panelClass&&this._toggleClasses(this._pane,this._config.panelClass,!0),this._attachments.next(),this._completeDetachContent(),this._keyboardDispatcher.add(this),this._config.disposeOnNavigation===!0||this._config.disposeOnNavigation==="pop-state"){let i=this._location.subscribe(()=>this.dispose());this._locationChanges=()=>i.unsubscribe()}else this._config.disposeOnNavigation==="url-change"&&(this._locationChanges=this._location.onUrlChange(()=>this.dispose()));return this._outsideClickDispatcher.add(this),typeof e?.onDestroy=="function"&&e.onDestroy(()=>{this.hasAttached()&&this._ngZone.runOutsideAngular(()=>Promise.resolve().then(()=>this.detach()))}),e}detach(){if(!this.hasAttached())return;this.detachBackdrop(),this._togglePointerEvents(!1),this._positionStrategy&&this._positionStrategy.detach&&this._positionStrategy.detach(),this._scrollStrategy&&this._scrollStrategy.disable();let n=this._portalOutlet.detach();return this._detachments.next(),this._completeDetachContent(),this._keyboardDispatcher.remove(this),this._detachContentWhenEmpty(),this._locationChanges?.(),this._outsideClickDispatcher.remove(this),dn(()=>{Kd.update(e=>e.filter(i=>i!==this))}),n}dispose(){if(this._disposed)return;let n=this.hasAttached();this._positionStrategy&&this._positionStrategy.dispose(),this._disposeScrollStrategy(),this._backdropRef?.dispose(),this._locationChanges?.(),this._keyboardDispatcher.remove(this),this._portalOutlet.dispose(),this._attachments.complete(),this._backdropClick.complete(),this._keydownEvents.complete(),this._outsidePointerEvents.complete(),this._outsideClickDispatcher.remove(this),this._host?.remove(),this._afterNextRenderRef?.destroy(),this._previousHostParent=this._pane=this._host=this._backdropRef=null,n&&this._detachments.next(),this._detachments.complete(),this._completeDetachContent(),this._disposed=!0,dn(()=>{Kd.update(e=>e.filter(i=>i!==this))})}hasAttached(){return this._portalOutlet.hasAttached()}backdropClick(){return this._backdropClick}attachments(){return this._attachments}detachments(){return this._detachments}keydownEvents(){return this._keydownEvents}outsidePointerEvents(){return this._outsidePointerEvents}getConfig(){return this._config}updatePosition(){this._positionStrategy&&this._positionStrategy.apply()}updatePositionStrategy(n){n!==this._positionStrategy&&(this._positionStrategy&&this._positionStrategy.dispose(),this._positionStrategy=n,this.hasAttached()&&(n.attach(this),this.updatePosition()))}updateSize(n){this._config=N(N({},this._config),n),this._updateElementSize()}setDirection(n){this._config=De(N({},this._config),{direction:n}),this._updateElementDirection()}addPanelClass(n){this._pane&&this._toggleClasses(this._pane,n,!0)}removePanelClass(n){this._pane&&this._toggleClasses(this._pane,n,!1)}getDirection(){let n=this._config.direction;return n?typeof n=="string"?n:n.value:"ltr"}updateScrollStrategy(n){n!==this._scrollStrategy&&(this._disposeScrollStrategy(),this._scrollStrategy=n,this.hasAttached()&&(n.attach(this),n.enable()))}_updateElementDirection(){this._host.setAttribute("dir",this.getDirection())}_updateElementSize(){if(!this._pane)return;let n=this._pane.style;n.width=nt(this._config.width),n.height=nt(this._config.height),n.minWidth=nt(this._config.minWidth),n.minHeight=nt(this._config.minHeight),n.maxWidth=nt(this._config.maxWidth),n.maxHeight=nt(this._config.maxHeight)}_togglePointerEvents(n){this._pane.style.pointerEvents=n?"":"none"}_attachHost(){if(!this._host.parentElement){let n=this._config.usePopover?this._positionStrategy?.getPopoverInsertionPoint?.():null;Zd(n)?n.after(this._host):n?.type==="parent"?n.element.appendChild(this._host):this._previousHostParent?.appendChild(this._host)}if(this._config.usePopover)try{this._host.showPopover()}catch{}}_attachBackdrop(){let n="cdk-overlay-backdrop-showing";this._backdropRef?.dispose(),this._backdropRef=new Xd(this._document,this._renderer,this._ngZone,e=>{this._backdropClick.next(e)}),this._animationsDisabled&&this._backdropRef.element.classList.add("cdk-overlay-backdrop-noop-animation"),this._config.backdropClass&&this._toggleClasses(this._backdropRef.element,this._config.backdropClass,!0),this._config.usePopover?this._host.prepend(this._backdropRef.element):this._host.parentElement.insertBefore(this._backdropRef.element,this._host),!this._animationsDisabled&&typeof requestAnimationFrame<"u"?this._ngZone.runOutsideAngular(()=>{requestAnimationFrame(()=>this._backdropRef?.element.classList.add(n))}):this._backdropRef.element.classList.add(n)}_updateStackingOrder(){!this._config.usePopover&&this._host.nextSibling&&this._host.parentNode.appendChild(this._host)}detachBackdrop(){this._animationsDisabled?(this._backdropRef?.dispose(),this._backdropRef=null):this._backdropRef?.detach()}_toggleClasses(n,e,i){let a=Ta(e||[]).filter(r=>!!r);a.length&&(i?n.classList.add(...a):n.classList.remove(...a))}_detachContentWhenEmpty(){let n=!1;try{this._detachContentAfterRenderRef=ht(()=>{n=!0,this._detachContent()},{injector:this._injector})}catch(e){if(n)throw e;this._detachContent()}globalThis.MutationObserver&&this._pane&&(this._detachContentMutationObserver||=new globalThis.MutationObserver(()=>{this._detachContent()}),this._detachContentMutationObserver.observe(this._pane,{childList:!0}))}_detachContent(){(!this._pane||!this._host||this._pane.children.length===0)&&(this._pane&&this._config.panelClass&&this._toggleClasses(this._pane,this._config.panelClass,!1),this._host&&this._host.parentElement&&(this._previousHostParent=this._host.parentElement,this._host.remove()),this._completeDetachContent())}_completeDetachContent(){this._detachContentAfterRenderRef?.destroy(),this._detachContentAfterRenderRef=void 0,this._detachContentMutationObserver?.disconnect()}_disposeScrollStrategy(){let n=this._scrollStrategy;n?.disable(),n?.detach?.()}},vg="cdk-overlay-connected-position-bounding-box",Gw=/([A-Za-z%]+)$/;function yl(t,n){return new vl(n,t.get(Wn),t.get(Y),t.get(Ie),t.get(Ng))}var vl=class{_viewportRuler;_document;_platform;_overlayContainer;_overlayRef;_isInitialRender=!1;_lastBoundingBoxSize={width:0,height:0};_isPushed=!1;_canPush=!0;_growAfterOpen=!1;_hasFlexibleDimensions=!0;_positionLocked=!1;_originRect;_overlayRect;_viewportRect;_containerRect;_viewportMargin=0;_scrollables=[];_preferredPositions=[];_origin;_pane;_isDisposed=!1;_boundingBox=null;_lastPosition=null;_lastScrollVisibility=null;_positionChanges=new z;_resizeSubscription=Mt.EMPTY;_offsetX=0;_offsetY=0;_transformOriginSelector;_appliedPanelClasses=[];_previousPushAmount=null;_popoverLocation="global";positionChanges=this._positionChanges;get positions(){return this._preferredPositions}constructor(n,e,i,a,r){this._viewportRuler=e,this._document=i,this._platform=a,this._overlayContainer=r,this.setOrigin(n)}attach(n){this._overlayRef&&this._overlayRef,this._validatePositions(),n.hostElement.classList.add(vg),this._overlayRef=n,this._boundingBox=n.hostElement,this._pane=n.overlayElement,this._isDisposed=!1,this._isInitialRender=!0,this._lastPosition=null,this._resizeSubscription.unsubscribe(),this._resizeSubscription=this._viewportRuler.change().subscribe(()=>{this._isInitialRender=!0,this.apply()})}apply(){if(this._isDisposed||!this._platform.isBrowser)return;if(!this._isInitialRender&&this._positionLocked&&this._lastPosition){this.reapplyLastPosition();return}this._clearPanelClasses(),this._resetOverlayElementStyles(),this._resetBoundingBoxStyles(),this._viewportRect=this._getNarrowedViewportRect(),this._originRect=this._getOriginRect(),this._overlayRect=this._pane.getBoundingClientRect(),this._containerRect=this._getContainerRect();let n=this._originRect,e=this._overlayRect,i=this._viewportRect,a=this._containerRect,r=[],o;for(let s of this._preferredPositions){let l=this._getOriginPoint(n,a,s),c=this._getOverlayPoint(l,e,s),d=this._getOverlayFit(c,e,i,s);if(d.isCompletelyWithinViewport){this._isPushed=!1,this._applyPosition(s,l);return}if(this._canFitWithFlexibleDimensions(d,c,i)){r.push({position:s,origin:l,overlayRect:e,boundingBoxRect:this._calculateBoundingBoxRect(l,s)});continue}(!o||o.overlayFit.visibleArea<d.visibleArea)&&(o={overlayFit:d,overlayPoint:c,originPoint:l,position:s,overlayRect:e})}if(r.length){let s=null,l=-1;for(let c of r){let d=c.boundingBoxRect.width*c.boundingBoxRect.height*(c.position.weight||1);d>l&&(l=d,s=c)}this._isPushed=!1,this._applyPosition(s.position,s.origin);return}if(this._canPush){this._isPushed=!0,this._applyPosition(o.position,o.originPoint);return}this._applyPosition(o.position,o.originPoint)}detach(){this._clearPanelClasses(),this._lastPosition=null,this._previousPushAmount=null,this._resizeSubscription.unsubscribe()}dispose(){this._isDisposed||(this._boundingBox&&Ui(this._boundingBox.style,{top:"",left:"",right:"",bottom:"",height:"",width:"",alignItems:"",justifyContent:""}),this._pane&&this._resetOverlayElementStyles(),this._overlayRef&&this._overlayRef.hostElement.classList.remove(vg),this.detach(),this._positionChanges.complete(),this._overlayRef=this._boundingBox=null,this._isDisposed=!0)}reapplyLastPosition(){if(this._isDisposed||!this._platform.isBrowser)return;let n=this._lastPosition;n?(this._originRect=this._getOriginRect(),this._overlayRect=this._pane.getBoundingClientRect(),this._viewportRect=this._getNarrowedViewportRect(),this._containerRect=this._getContainerRect(),this._applyPosition(n,this._getOriginPoint(this._originRect,this._containerRect,n))):this.apply()}withScrollableContainers(n){return this._scrollables=n,this}withPositions(n){return this._preferredPositions=n,n.indexOf(this._lastPosition)===-1&&(this._lastPosition=null),this._validatePositions(),this}withViewportMargin(n){return this._viewportMargin=n,this}withFlexibleDimensions(n=!0){return this._hasFlexibleDimensions=n,this}withGrowAfterOpen(n=!0){return this._growAfterOpen=n,this}withPush(n=!0){return this._canPush=n,this}withLockedPosition(n=!0){return this._positionLocked=n,this}setOrigin(n){return this._origin=n,this}withDefaultOffsetX(n){return this._offsetX=n,this}withDefaultOffsetY(n){return this._offsetY=n,this}withTransformOriginOn(n){return this._transformOriginSelector=n,this}withPopoverLocation(n){return this._popoverLocation=n,this}getPopoverInsertionPoint(){return this._popoverLocation==="global"?null:this._popoverLocation!=="inline"?this._popoverLocation:this._origin instanceof X?this._origin.nativeElement:Zd(this._origin)?this._origin:null}_getOriginPoint(n,e,i){let a;if(i.originX=="center")a=n.left+n.width/2;else{let o=this._isRtl()?n.right:n.left,s=this._isRtl()?n.left:n.right;a=i.originX=="start"?o:s}e.left<0&&(a-=e.left);let r;return i.originY=="center"?r=n.top+n.height/2:r=i.originY=="top"?n.top:n.bottom,e.top<0&&(r-=e.top),{x:a,y:r}}_getOverlayPoint(n,e,i){let a;i.overlayX=="center"?a=-e.width/2:i.overlayX==="start"?a=this._isRtl()?-e.width:0:a=this._isRtl()?0:-e.width;let r;return i.overlayY=="center"?r=-e.height/2:r=i.overlayY=="top"?0:-e.height,{x:n.x+a,y:n.y+r}}_getOverlayFit(n,e,i,a){let r=yg(e),{x:o,y:s}=n,l=this._getOffset(a,"x"),c=this._getOffset(a,"y");l&&(o+=l),c&&(s+=c);let d=0-o,m=o+r.width-i.width,u=0-s,f=s+r.height-i.height,g=this._subtractOverflows(r.width,d,m),b=this._subtractOverflows(r.height,u,f),_=g*b;return{visibleArea:_,isCompletelyWithinViewport:r.width*r.height===_,fitsInViewportVertically:b===r.height,fitsInViewportHorizontally:g==r.width}}_canFitWithFlexibleDimensions(n,e,i){if(this._hasFlexibleDimensions){let a=i.bottom-e.y,r=i.right-e.x,o=_g(this._overlayRef.getConfig().minHeight),s=_g(this._overlayRef.getConfig().minWidth),l=n.fitsInViewportVertically||o!=null&&o<=a,c=n.fitsInViewportHorizontally||s!=null&&s<=r;return l&&c}return!1}_pushOverlayOnScreen(n,e,i){if(this._previousPushAmount&&this._positionLocked)return{x:n.x+this._previousPushAmount.x,y:n.y+this._previousPushAmount.y};let a=yg(e),r=this._viewportRect,o=Math.max(n.x+a.width-r.width,0),s=Math.max(n.y+a.height-r.height,0),l=Math.max(r.top-i.top-n.y,0),c=Math.max(r.left-i.left-n.x,0),d=0,m=0;return a.width<=r.width?d=c||-o:d=n.x<this._getViewportMarginStart()?r.left-i.left-n.x:0,a.height<=r.height?m=l||-s:m=n.y<this._getViewportMarginTop()?r.top-i.top-n.y:0,this._previousPushAmount={x:d,y:m},{x:n.x+d,y:n.y+m}}_applyPosition(n,e){if(this._setTransformOrigin(n),this._setOverlayElementStyles(e,n),this._setBoundingBoxStyles(e,n),n.panelClass&&this._addPanelClasses(n.panelClass),this._positionChanges.observers.length){let i=this._getScrollVisibility();if(n!==this._lastPosition||!this._lastScrollVisibility||!Ww(this._lastScrollVisibility,i)){let a=new gl(n,i);this._positionChanges.next(a)}this._lastScrollVisibility=i}this._lastPosition=n,this._isInitialRender=!1}_setTransformOrigin(n){if(!this._transformOriginSelector)return;let e=this._boundingBox.querySelectorAll(this._transformOriginSelector),i,a=n.overlayY;n.overlayX==="center"?i="center":this._isRtl()?i=n.overlayX==="start"?"right":"left":i=n.overlayX==="start"?"left":"right";for(let r=0;r<e.length;r++)e[r].style.transformOrigin=`${i} ${a}`}_calculateBoundingBoxRect(n,e){let i=this._viewportRect,a=this._isRtl(),r,o,s;if(e.overlayY==="top")o=n.y,r=i.height-o+this._getViewportMarginBottom();else if(e.overlayY==="bottom")s=i.height-n.y+this._getViewportMarginTop()+this._getViewportMarginBottom(),r=i.height-s+this._getViewportMarginTop();else{let f=Math.min(i.bottom-n.y+i.top,n.y),g=this._lastBoundingBoxSize.height;r=f*2,o=n.y-f,r>g&&!this._isInitialRender&&!this._growAfterOpen&&(o=n.y-g/2)}let l=e.overlayX==="start"&&!a||e.overlayX==="end"&&a,c=e.overlayX==="end"&&!a||e.overlayX==="start"&&a,d,m,u;if(c)u=i.width-n.x+this._getViewportMarginStart()+this._getViewportMarginEnd(),d=n.x-this._getViewportMarginStart();else if(l)m=n.x,d=i.right-n.x-this._getViewportMarginEnd();else{let f=Math.min(i.right-n.x+i.left,n.x),g=this._lastBoundingBoxSize.width;d=f*2,m=n.x-f,d>g&&!this._isInitialRender&&!this._growAfterOpen&&(m=n.x-g/2)}return{top:o,left:m,bottom:s,right:u,width:d,height:r}}_setBoundingBoxStyles(n,e){let i=this._calculateBoundingBoxRect(n,e);!this._isInitialRender&&!this._growAfterOpen&&(i.height=Math.min(i.height,this._lastBoundingBoxSize.height),i.width=Math.min(i.width,this._lastBoundingBoxSize.width));let a={};if(this._hasExactPosition())a.top=a.left="0",a.bottom=a.right="auto",a.maxHeight=a.maxWidth="",a.width=a.height="100%";else{let r=this._overlayRef.getConfig().maxHeight,o=this._overlayRef.getConfig().maxWidth;a.width=nt(i.width),a.height=nt(i.height),a.top=nt(i.top)||"auto",a.bottom=nt(i.bottom)||"auto",a.left=nt(i.left)||"auto",a.right=nt(i.right)||"auto",e.overlayX==="center"?a.alignItems="center":a.alignItems=e.overlayX==="end"?"flex-end":"flex-start",e.overlayY==="center"?a.justifyContent="center":a.justifyContent=e.overlayY==="bottom"?"flex-end":"flex-start",r&&(a.maxHeight=nt(r)),o&&(a.maxWidth=nt(o))}this._lastBoundingBoxSize=i,Ui(this._boundingBox.style,a)}_resetBoundingBoxStyles(){Ui(this._boundingBox.style,{top:"0",left:"0",right:"0",bottom:"0",height:"",width:"",alignItems:"",justifyContent:""})}_resetOverlayElementStyles(){Ui(this._pane.style,{top:"",left:"",bottom:"",right:"",position:"",transform:""})}_setOverlayElementStyles(n,e){let i={},a=this._hasExactPosition(),r=this._hasFlexibleDimensions,o=this._overlayRef.getConfig();if(a){let d=this._viewportRuler.getViewportScrollPosition();Ui(i,this._getExactOverlayY(e,n,d)),Ui(i,this._getExactOverlayX(e,n,d))}else i.position="static";let s="",l=this._getOffset(e,"x"),c=this._getOffset(e,"y");l&&(s+=`translateX(${l}px) `),c&&(s+=`translateY(${c}px)`),i.transform=s.trim(),o.maxHeight&&(a?i.maxHeight=nt(o.maxHeight):r&&(i.maxHeight="")),o.maxWidth&&(a?i.maxWidth=nt(o.maxWidth):r&&(i.maxWidth="")),Ui(this._pane.style,i)}_getExactOverlayY(n,e,i){let a={top:"",bottom:""},r=this._getOverlayPoint(e,this._overlayRect,n);if(this._isPushed&&(r=this._pushOverlayOnScreen(r,this._overlayRect,i)),n.overlayY==="bottom"){let o=this._document.documentElement.clientHeight;a.bottom=`${o-(r.y+this._overlayRect.height)}px`}else a.top=nt(r.y);return a}_getExactOverlayX(n,e,i){let a={left:"",right:""},r=this._getOverlayPoint(e,this._overlayRect,n);this._isPushed&&(r=this._pushOverlayOnScreen(r,this._overlayRect,i));let o;if(this._isRtl()?o=n.overlayX==="end"?"left":"right":o=n.overlayX==="end"?"right":"left",o==="right"){let s=this._document.documentElement.clientWidth;a.right=`${s-(r.x+this._overlayRect.width)}px`}else a.left=nt(r.x);return a}_getScrollVisibility(){let n=this._getOriginRect(),e=this._pane.getBoundingClientRect(),i=this._scrollables.map(a=>a.getElementRef().nativeElement.getBoundingClientRect());return{isOriginClipped:gg(n,i),isOriginOutsideView:Yd(n,i),isOverlayClipped:gg(e,i),isOverlayOutsideView:Yd(e,i)}}_subtractOverflows(n,...e){return e.reduce((i,a)=>i-Math.max(a,0),n)}_getNarrowedViewportRect(){let n=this._document.documentElement.clientWidth,e=this._document.documentElement.clientHeight,i=this._viewportRuler.getViewportScrollPosition();return{top:i.top+this._getViewportMarginTop(),left:i.left+this._getViewportMarginStart(),right:i.left+n-this._getViewportMarginEnd(),bottom:i.top+e-this._getViewportMarginBottom(),width:n-this._getViewportMarginStart()-this._getViewportMarginEnd(),height:e-this._getViewportMarginTop()-this._getViewportMarginBottom()}}_isRtl(){return this._overlayRef.getDirection()==="rtl"}_hasExactPosition(){return!this._hasFlexibleDimensions||this._isPushed}_getOffset(n,e){return e==="x"?n.offsetX==null?this._offsetX:n.offsetX:n.offsetY==null?this._offsetY:n.offsetY}_validatePositions(){}_addPanelClasses(n){this._pane&&Ta(n).forEach(e=>{e!==""&&this._appliedPanelClasses.indexOf(e)===-1&&(this._appliedPanelClasses.push(e),this._pane.classList.add(e))})}_clearPanelClasses(){this._pane&&(this._appliedPanelClasses.forEach(n=>{this._pane.classList.remove(n)}),this._appliedPanelClasses=[])}_getViewportMarginStart(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.start??0}_getViewportMarginEnd(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.end??0}_getViewportMarginTop(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.top??0}_getViewportMarginBottom(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.bottom??0}_getOriginRect(){let n=this._origin;if(n instanceof X)return n.nativeElement.getBoundingClientRect();if(n instanceof Element)return n.getBoundingClientRect();let e=n.width||0,i=n.height||0;return{top:n.y,bottom:n.y+i,left:n.x,right:n.x+e,height:i,width:e}}_getContainerRect(){let n=this._overlayRef.getConfig().usePopover&&this._popoverLocation!=="global",e=this._overlayContainer.getContainerElement();n&&(e.style.display="block");let i=e.getBoundingClientRect();return n&&(e.style.display=""),i}};function Ui(t,n){for(let e in n)Object.hasOwn(n,e)&&(t[e]=n[e]);return t}function _g(t){if(typeof t!="number"&&t!=null){let[n,e]=t.split(Gw);return!e||e==="px"?parseFloat(n):null}return t||null}function yg(t){return{top:Math.floor(t.top),right:Math.floor(t.right),bottom:Math.floor(t.bottom),left:Math.floor(t.left),width:Math.floor(t.width),height:Math.floor(t.height)}}function Ww(t,n){return t===n?!0:t.isOriginClipped===n.isOriginClipped&&t.isOriginOutsideView===n.isOriginOutsideView&&t.isOverlayClipped===n.isOverlayClipped&&t.isOverlayOutsideView===n.isOverlayOutsideView}var xg="cdk-global-overlay-wrapper";function xl(t){return new _l}var _l=class{_overlayRef;_cssPosition="static";_topOffset="";_bottomOffset="";_alignItems="";_xPosition="";_xOffset="";_width="";_height="";_isDisposed=!1;attach(n){let e=n.getConfig();this._overlayRef=n,this._width&&!e.width&&n.updateSize({width:this._width}),this._height&&!e.height&&n.updateSize({height:this._height}),n.hostElement.classList.add(xg),this._isDisposed=!1}top(n=""){return this._bottomOffset="",this._topOffset=n,this._alignItems="flex-start",this}left(n=""){return this._xOffset=n,this._xPosition="left",this}bottom(n=""){return this._topOffset="",this._bottomOffset=n,this._alignItems="flex-end",this}right(n=""){return this._xOffset=n,this._xPosition="right",this}start(n=""){return this._xOffset=n,this._xPosition="start",this}end(n=""){return this._xOffset=n,this._xPosition="end",this}width(n=""){return this._overlayRef?this._overlayRef.updateSize({width:n}):this._width=n,this}height(n=""){return this._overlayRef?this._overlayRef.updateSize({height:n}):this._height=n,this}centerHorizontally(n=""){return this.left(n),this._xPosition="center",this}centerVertically(n=""){return this.top(n),this._alignItems="center",this}apply(){if(!this._overlayRef||!this._overlayRef.hasAttached())return;let n=this._overlayRef.overlayElement.style,e=this._overlayRef.hostElement.style,i=this._overlayRef.getConfig(),{width:a,height:r,maxWidth:o,maxHeight:s}=i,l=(a==="100%"||a==="100vw")&&(!o||o==="100%"||o==="100vw"),c=(r==="100%"||r==="100vh")&&(!s||s==="100%"||s==="100vh"),d=this._xPosition,m=this._xOffset,u=this._overlayRef.getConfig().direction==="rtl",f="",g="",b="";l?b="flex-start":d==="center"?(b="center",u?g=m:f=m):u?d==="left"||d==="end"?(b="flex-end",f=m):(d==="right"||d==="start")&&(b="flex-start",g=m):d==="left"||d==="start"?(b="flex-start",f=m):(d==="right"||d==="end")&&(b="flex-end",g=m),n.position=this._cssPosition,n.marginLeft=l?"0":f,n.marginTop=c?"0":this._topOffset,n.marginBottom=this._bottomOffset,n.marginRight=l?"0":g,e.justifyContent=b,e.alignItems=c?"flex-start":this._alignItems}dispose(){if(this._isDisposed||!this._overlayRef)return;let n=this._overlayRef.overlayElement.style,e=this._overlayRef.hostElement,i=e.style;e.classList.remove(xg),i.justifyContent=i.alignItems=n.marginTop=n.marginBottom=n.marginLeft=n.marginRight=n.position="",this._overlayRef=null,this._isDisposed=!0}},Ig=(()=>{class t{_injector=p(re);global(){return xl()}flexibleConnectedTo(e){return yl(this._injector,e)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),Qd=new I("OVERLAY_DEFAULT_CONFIG");function Ba(t,n){t.get(dt).load(Mg);let e=t.get(Ng),i=t.get(Y),a=t.get(wt),r=t.get(ci),o=t.get(Tt),s=t.get(ct,null,{optional:!0})||t.get(yt).createRenderer(null,null),l=new _i(n),c=t.get(Qd,null,{optional:!0})?.usePopover??!0;l.direction=l.direction||o.value,!i.body||!("showPopover"in i.body)?l.usePopover=!1:l.usePopover=n?.usePopover??c;let d=i.createElement("div"),m=i.createElement("div");d.id=a.getId("cdk-overlay-"),d.classList.add("cdk-overlay-pane"),m.appendChild(d),l.usePopover&&(m.setAttribute("popover","manual"),m.classList.add("cdk-overlay-popover"));let u=l.usePopover?l.positionStrategy?.getPopoverInsertionPoint?.():null;return Zd(u)?u.after(m):u?.type==="parent"?u.element.appendChild(m):e.getContainerElement().appendChild(m),new bl(new ao(d,r,t),m,d,l,t.get(K),t.get(Eg),i,t.get(Sa),t.get(Dg),n?.disableAnimations??t.get(fa,null,{optional:!0})==="NoopAnimations",t.get(Bn),s)}var Ag=(()=>{class t{scrollStrategies=p(kg);_positionBuilder=p(Ig);_injector=p(re);create(e){return Ba(this._injector,e)}position(){return this._positionBuilder}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var qi=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({providers:[Ag],imports:[me,La,Gd,Gd]})}return t})();var Zw=new I("MATERIAL_ANIMATIONS"),Tg=null;function Qw(){return p(Zw,{optional:!0})?.animationsDisabled||p(fa,{optional:!0})==="NoopAnimations"?"di-disabled":(Tg??=p(il).matchMedia("(prefers-reduced-motion)").matches,Tg?"reduced-motion":"enabled")}function tt(){return Qw()!=="enabled"}function Rt(t){return t!=null&&`${t}`!="false"}var Jt=(function(t){return t[t.FADING_IN=0]="FADING_IN",t[t.VISIBLE=1]="VISIBLE",t[t.FADING_OUT=2]="FADING_OUT",t[t.HIDDEN=3]="HIDDEN",t})(Jt||{}),Jd=class{_renderer;element;config;_animationForciblyDisabledThroughCss;state=Jt.HIDDEN;constructor(n,e,i,a=!1){this._renderer=n,this.element=e,this.config=i,this._animationForciblyDisabledThroughCss=a}fadeOut(){this._renderer.fadeOutRipple(this)}},Og=Aa({passive:!0,capture:!0}),em=class{_events=new Map;addHandler(n,e,i,a){let r=this._events.get(e);if(r){let o=r.get(i);o?o.add(a):r.set(i,new Set([a]))}else this._events.set(e,new Map([[i,new Set([a])]])),n.runOutsideAngular(()=>{document.addEventListener(e,this._delegateEventHandler,Og)})}removeHandler(n,e,i){let a=this._events.get(n);if(!a)return;let r=a.get(e);r&&(r.delete(i),r.size===0&&a.delete(e),a.size===0&&(this._events.delete(n),document.removeEventListener(n,this._delegateEventHandler,Og)))}_delegateEventHandler=n=>{let e=Bt(n);e&&this._events.get(n.type)?.forEach((i,a)=>{(a===e||a.contains(e))&&i.forEach(r=>r.handleEvent(n))})}},so={enterDuration:225,exitDuration:150},Jw=800,Rg=Aa({passive:!0,capture:!0}),Pg=["mousedown","touchstart"],Fg=["mouseup","mouseleave","touchend","touchcancel"],eS=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["ng-component"]],hostAttrs:["mat-ripple-style-loader",""],decls:0,vars:0,template:function(i,a){},styles:[`.mat-ripple {
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
`],encapsulation:2})}return t})(),Gi=class t{_target;_ngZone;_platform;_containerElement;_triggerElement=null;_isPointerDown=!1;_activeRipples=new Map;_mostRecentTransientRipple=null;_lastTouchStartEvent;_pointerUpEventsRegistered=!1;_containerRect=null;static _eventManager=new em;constructor(n,e,i,a,r){this._target=n,this._ngZone=e,this._platform=a,a.isBrowser&&(this._containerElement=Qt(i)),r&&r.get(dt).load(eS)}fadeInRipple(n,e,i={}){let a=this._containerRect=this._containerRect||this._containerElement.getBoundingClientRect(),r=N(N({},so),i.animation);i.centered&&(n=a.left+a.width/2,e=a.top+a.height/2);let o=i.radius||tS(n,e,a),s=n-a.left,l=e-a.top,c=r.enterDuration,d=document.createElement("div");d.classList.add("mat-ripple-element"),d.style.left=`${s-o}px`,d.style.top=`${l-o}px`,d.style.height=`${o*2}px`,d.style.width=`${o*2}px`,i.color!=null&&(d.style.backgroundColor=i.color),d.style.transitionDuration=`${c}ms`,this._containerElement.appendChild(d);let m=window.getComputedStyle(d),u=m.transitionProperty,f=m.transitionDuration,g=u==="none"||f==="0s"||f==="0s, 0s"||a.width===0&&a.height===0,b=new Jd(this,d,i,g);d.style.transform="scale3d(1, 1, 1)",b.state=Jt.FADING_IN,i.persistent||(this._mostRecentTransientRipple=b);let _=null;return!g&&(c||r.exitDuration)&&this._ngZone.runOutsideAngular(()=>{let x=()=>{_&&(_.fallbackTimer=null),clearTimeout(C),this._finishRippleTransition(b)},w=()=>this._destroyRipple(b),C=setTimeout(w,c+100);d.addEventListener("transitionend",x),d.addEventListener("transitioncancel",w),_={onTransitionEnd:x,onTransitionCancel:w,fallbackTimer:C}}),this._activeRipples.set(b,_),(g||!c)&&this._finishRippleTransition(b),b}fadeOutRipple(n){if(n.state===Jt.FADING_OUT||n.state===Jt.HIDDEN)return;let e=n.element,i=N(N({},so),n.config.animation);e.style.transitionDuration=`${i.exitDuration}ms`,e.style.opacity="0",n.state=Jt.FADING_OUT,(n._animationForciblyDisabledThroughCss||!i.exitDuration)&&this._finishRippleTransition(n)}fadeOutAll(){this._getActiveRipples().forEach(n=>n.fadeOut())}fadeOutAllNonPersistent(){this._getActiveRipples().forEach(n=>{n.config.persistent||n.fadeOut()})}setupTriggerEvents(n){let e=Qt(n);!this._platform.isBrowser||!e||e===this._triggerElement||(this._removeTriggerEvents(),this._triggerElement=e,Pg.forEach(i=>{t._eventManager.addHandler(this._ngZone,i,e,this)}))}handleEvent(n){n.type==="mousedown"?this._onMousedown(n):n.type==="touchstart"?this._onTouchStart(n):this._onPointerUp(),this._pointerUpEventsRegistered||(this._ngZone.runOutsideAngular(()=>{Fg.forEach(e=>{this._triggerElement.addEventListener(e,this,Rg)})}),this._pointerUpEventsRegistered=!0)}_finishRippleTransition(n){n.state===Jt.FADING_IN?this._startFadeOutTransition(n):n.state===Jt.FADING_OUT&&this._destroyRipple(n)}_startFadeOutTransition(n){let e=n===this._mostRecentTransientRipple,{persistent:i}=n.config;n.state=Jt.VISIBLE,!i&&(!e||!this._isPointerDown)&&n.fadeOut()}_destroyRipple(n){let e=this._activeRipples.get(n)??null;this._activeRipples.delete(n),this._activeRipples.size||(this._containerRect=null),n===this._mostRecentTransientRipple&&(this._mostRecentTransientRipple=null),n.state=Jt.HIDDEN,e!==null&&(n.element.removeEventListener("transitionend",e.onTransitionEnd),n.element.removeEventListener("transitioncancel",e.onTransitionCancel),e.fallbackTimer!==null&&clearTimeout(e.fallbackTimer)),n.element.remove()}_onMousedown(n){let e=ji(n),i=this._lastTouchStartEvent&&Date.now()<this._lastTouchStartEvent+Jw;!this._target.rippleDisabled&&!e&&!i&&(this._isPointerDown=!0,this.fadeInRipple(n.clientX,n.clientY,this._target.rippleConfig))}_onTouchStart(n){if(!this._target.rippleDisabled&&!Vi(n)){this._lastTouchStartEvent=Date.now(),this._isPointerDown=!0;let e=n.changedTouches;if(e)for(let i=0;i<e.length;i++)this.fadeInRipple(e[i].clientX,e[i].clientY,this._target.rippleConfig)}}_onPointerUp(){this._isPointerDown&&(this._isPointerDown=!1,this._getActiveRipples().forEach(n=>{let e=n.state===Jt.VISIBLE||n.config.terminateOnPointerUp&&n.state===Jt.FADING_IN;!n.config.persistent&&e&&n.fadeOut()}))}_getActiveRipples(){return Array.from(this._activeRipples.keys())}_removeTriggerEvents(){let n=this._triggerElement;n&&(Pg.forEach(e=>t._eventManager.removeHandler(e,n,this)),this._pointerUpEventsRegistered&&(Fg.forEach(e=>n.removeEventListener(e,this,Rg)),this._pointerUpEventsRegistered=!1))}};function tS(t,n,e){let i=Math.max(Math.abs(t-e.left),Math.abs(t-e.right)),a=Math.max(Math.abs(n-e.top),Math.abs(n-e.bottom));return Math.sqrt(i*i+a*a)}var Wi=new I("mat-ripple-global-options"),wl=(()=>{class t{_elementRef=p(X);_animationsDisabled=tt();color;unbounded=!1;centered=!1;radius=0;animation;get disabled(){return this._disabled}set disabled(e){e&&this.fadeOutAllNonPersistent(),this._disabled=e,this._setupTriggerEventsIfEnabled()}_disabled=!1;get trigger(){return this._trigger||this._elementRef.nativeElement}set trigger(e){this._trigger=e,this._setupTriggerEventsIfEnabled()}_trigger;_rippleRenderer;_globalOptions;_isInitialized=!1;constructor(){let e=p(K),i=p(Ie),a=p(Wi,{optional:!0}),r=p(re);this._globalOptions=a||{},this._rippleRenderer=new Gi(this,e,this._elementRef,i,r)}ngOnInit(){this._isInitialized=!0,this._setupTriggerEventsIfEnabled()}ngOnDestroy(){this._rippleRenderer._removeTriggerEvents()}fadeOutAll(){this._rippleRenderer.fadeOutAll()}fadeOutAllNonPersistent(){this._rippleRenderer.fadeOutAllNonPersistent()}get rippleConfig(){return{centered:this.centered,radius:this.radius,color:this.color,animation:N(N(N({},this._globalOptions.animation),this._animationsDisabled?{enterDuration:0,exitDuration:0}:{}),this.animation),terminateOnPointerUp:this._globalOptions.terminateOnPointerUp}}get rippleDisabled(){return this.disabled||!!this._globalOptions.disabled}_setupTriggerEventsIfEnabled(){!this.disabled&&this._isInitialized&&this._rippleRenderer.setupTriggerEvents(this.trigger)}launch(e,i=0,a){return typeof e=="number"?this._rippleRenderer.fadeInRipple(e,i,N(N({},this.rippleConfig),a)):this._rippleRenderer.fadeInRipple(0,0,N(N({},this.rippleConfig),e))}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","mat-ripple",""],["","matRipple",""]],hostAttrs:[1,"mat-ripple"],hostVars:2,hostBindings:function(i,a){i&2&&ee("mat-ripple-unbounded",a.unbounded)},inputs:{color:[0,"matRippleColor","color"],unbounded:[0,"matRippleUnbounded","unbounded"],centered:[0,"matRippleCentered","centered"],radius:[0,"matRippleRadius","radius"],animation:[0,"matRippleAnimation","animation"],disabled:[0,"matRippleDisabled","disabled"],trigger:[0,"matRippleTrigger","trigger"]},exportAs:["matRipple"]})}return t})();var nS={capture:!0},iS=["focus","mousedown","mouseenter","touchstart"],tm="mat-ripple-loader-uninitialized",nm="mat-ripple-loader-class-name",Lg="mat-ripple-loader-centered",Sl="mat-ripple-loader-disabled",kl=(()=>{class t{_document=p(Y);_animationsDisabled=tt();_globalRippleOptions=p(Wi,{optional:!0});_platform=p(Ie);_ngZone=p(K);_injector=p(re);_eventCleanups;_hosts=new Map;constructor(){let e=p(yt).createRenderer(null,null);this._eventCleanups=this._ngZone.runOutsideAngular(()=>iS.map(i=>e.listen(this._document,i,this._onInteraction,nS)))}ngOnDestroy(){let e=this._hosts.keys();for(let i of e)this.destroyRipple(i);this._eventCleanups.forEach(i=>i())}configureRipple(e,i){e.setAttribute(tm,this._globalRippleOptions?.namespace??""),(i.className||!e.hasAttribute(nm))&&e.setAttribute(nm,i.className||""),i.centered&&e.setAttribute(Lg,""),i.disabled&&e.setAttribute(Sl,"")}setDisabled(e,i){let a=this._hosts.get(e);a?(a.target.rippleDisabled=i,!i&&!a.hasSetUpEvents&&(a.hasSetUpEvents=!0,a.renderer.setupTriggerEvents(e))):i?e.setAttribute(Sl,""):e.removeAttribute(Sl)}_onInteraction=e=>{let i=Bt(e);if(i instanceof HTMLElement){let a=i.closest(`[${tm}="${this._globalRippleOptions?.namespace??""}"]`);a&&this._createRipple(a)}};_createRipple(e){if(!this._document||this._hosts.has(e))return;e.querySelector(".mat-ripple")?.remove();let i=this._document.createElement("span");i.classList.add("mat-ripple",e.getAttribute(nm)),e.append(i);let a=this._globalRippleOptions,r=this._animationsDisabled?0:a?.animation?.enterDuration??so.enterDuration,o=this._animationsDisabled?0:a?.animation?.exitDuration??so.exitDuration,s={rippleDisabled:this._animationsDisabled||a?.disabled||e.hasAttribute(Sl),rippleConfig:{centered:e.hasAttribute(Lg),terminateOnPointerUp:a?.terminateOnPointerUp,animation:{enterDuration:r,exitDuration:o}}},l=new Gi(s,this._ngZone,i,this._platform,this._injector),c=!s.rippleDisabled;c&&l.setupTriggerEvents(e),this._hosts.set(e,{target:s,renderer:l,hasSetUpEvents:c}),e.removeAttribute(tm)}destroyRipple(e){let i=this._hosts.get(e);i&&(i.renderer._removeTriggerEvents(),this._hosts.delete(e))}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var hn=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["structural-styles"]],decls:0,vars:0,template:function(i,a){},styles:[`.mat-focus-indicator {
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
`],encapsulation:2})}return t})();var aS=new I("MAT_BUTTON_CONFIG");function Bg(t){return t==null?void 0:pi(t)}var jg=(()=>{class t{_elementRef=p(X);_ngZone=p(K);_animationsDisabled=tt();_config=p(aS,{optional:!0});_focusMonitor=p(In);_cleanupClick;_renderer=p(ct);_rippleLoader=p(kl);_isAnchor;_isFab=!1;color;get disableRipple(){return this._disableRipple}set disableRipple(e){this._disableRipple=e,this._updateRippleDisabled()}_disableRipple=!1;get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._updateRippleDisabled()}_disabled=!1;ariaDisabled;disabledInteractive;tabIndex;set _tabindex(e){this.tabIndex=e}showProgress=gt(!1,{transform:ve});constructor(){p(dt).load(hn);let e=this._elementRef.nativeElement;this._isAnchor=e.tagName==="A",this.disabledInteractive=this._config?.disabledInteractive??!1,this.color=this._config?.color??null,this._rippleLoader?.configureRipple(e,{className:"mat-mdc-button-ripple"})}ngAfterViewInit(){this._focusMonitor.monitor(this._elementRef,!0),this._isAnchor&&this._setupAsAnchor()}ngOnDestroy(){this._cleanupClick?.(),this._focusMonitor.stopMonitoring(this._elementRef),this._rippleLoader?.destroyRipple(this._elementRef.nativeElement)}focus(e="program",i){e?this._focusMonitor.focusVia(this._elementRef.nativeElement,e,i):this._elementRef.nativeElement.focus(i)}_getAriaDisabled(){return this.ariaDisabled!=null?this.ariaDisabled:this._isAnchor?this.disabled||null:this.disabled&&this.disabledInteractive?!0:null}_getDisabledAttribute(){return this.disabledInteractive||!this.disabled?null:!0}_updateRippleDisabled(){this._rippleLoader?.setDisabled(this._elementRef.nativeElement,this.disableRipple||this.disabled)}_getTabIndex(){return this._isAnchor?this.disabled&&!this.disabledInteractive?-1:this.tabIndex:this.tabIndex}_setupAsAnchor(){this._cleanupClick=this._ngZone.runOutsideAngular(()=>this._renderer.listen(this._elementRef.nativeElement,"click",e=>{this.disabled&&(e.preventDefault(),e.stopImmediatePropagation())}))}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,hostAttrs:[1,"mat-mdc-button-base"],hostVars:15,hostBindings:function(i,a){i&2&&(fe("disabled",a._getDisabledAttribute())("aria-disabled",a._getAriaDisabled())("tabindex",a._getTabIndex()),At(a.color?"mat-"+a.color:""),ee("mat-mdc-button-progress-indicator-shown",a.showProgress())("mat-mdc-button-disabled",a.disabled)("mat-mdc-button-disabled-interactive",a.disabledInteractive)("mat-unthemed",!a.color)("_mat-animation-noopable",a._animationsDisabled))},inputs:{color:"color",disableRipple:[2,"disableRipple","disableRipple",ve],disabled:[2,"disabled","disabled",ve],ariaDisabled:[2,"aria-disabled","ariaDisabled",ve],disabledInteractive:[2,"disabledInteractive","disabledInteractive",ve],tabIndex:[2,"tabIndex","tabIndex",Bg],_tabindex:[2,"tabindex","_tabindex",Bg],showProgress:[1,"showProgress"]}})}return t})();var An=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[me]})}return t})();var Vg=new Map([["text",["mat-mdc-button"]],["filled",["mdc-button--unelevated","mat-mdc-unelevated-button"]],["elevated",["mdc-button--raised","mat-mdc-raised-button"]],["outlined",["mdc-button--outlined","mat-mdc-outlined-button"]],["tonal",["mat-tonal-button"]]]),ja=(()=>{class t extends jg{get appearance(){return this._appearance}set appearance(e){this.setAppearance(e||this._config?.defaultAppearance||"text")}_appearance=null;constructor(){super();let e=rS(this._elementRef.nativeElement);e&&this.setAppearance(e)}setAppearance(e){if(e===this._appearance)return;let i=this._elementRef.nativeElement.classList,a=this._appearance?Vg.get(this._appearance):null,r=Vg.get(e);a&&i.remove(...a),i.add(...r),this._appearance=e}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=[[["",8,"material-icons",3,"iconPositionEnd",""],["mat-icon",3,"iconPositionEnd",""],["","matButtonIcon","",3,"iconPositionEnd",""],["",8,"material-symbols-outlined",3,"iconPositionEnd",""],["",8,"material-symbols-rounded",3,"iconPositionEnd",""],["",8,"material-symbols-sharp",3,"iconPositionEnd",""]],"*",[["","iconPositionEnd","",8,"material-icons"],["mat-icon","iconPositionEnd",""],["","matButtonIcon","","iconPositionEnd",""],["","iconPositionEnd","",8,"material-symbols-outlined"],["","iconPositionEnd","",8,"material-symbols-rounded"],["","iconPositionEnd","",8,"material-symbols-sharp"]],[["","progressIndicator",""]]],i=[".material-icons:not([iconPositionEnd]), mat-icon:not([iconPositionEnd]), [matButtonIcon]:not([iconPositionEnd]), .material-symbols-outlined:not([iconPositionEnd]), .material-symbols-rounded:not([iconPositionEnd]), .material-symbols-sharp:not([iconPositionEnd])","*",".material-icons[iconPositionEnd], mat-icon[iconPositionEnd], [matButtonIcon][iconPositionEnd], .material-symbols-outlined[iconPositionEnd], .material-symbols-rounded[iconPositionEnd], .material-symbols-sharp[iconPositionEnd]","[progressIndicator]"];function a(r,o){r&1&&(Qe(0,"div",2),j(1,3),et())}return T({type:t,selectors:[["button","matButton",""],["a","matButton",""],["button","mat-button",""],["button","mat-raised-button",""],["button","mat-flat-button",""],["button","mat-stroked-button",""],["a","mat-button",""],["a","mat-raised-button",""],["a","mat-flat-button",""],["a","mat-stroked-button",""]],hostAttrs:[1,"mdc-button"],inputs:{appearance:[0,"matButton","appearance"]},exportAs:["matButton","matAnchor"],features:[Ve],ngContentSelectors:i,decls:8,vars:5,consts:[[1,"mat-mdc-button-persistent-ripple"],[1,"mdc-button__label"],[1,"mat-mdc-button-progress-indicator-container"],[1,"mat-focus-indicator"],[1,"mat-mdc-button-touch-target"]],template:function(o,s){o&1&&(de(e),ln(0,"span",0),j(1),Qe(2,"span",1),j(3,1),et(),j(4,2),E(5,a,2,0,"div",2),ln(6,"span",3)(7,"span",4)),o&2&&(ee("mdc-button__ripple",!s._isFab)("mdc-fab__ripple",s._isFab),h(5),D(s.showProgress()?5:-1))},styles:[`.mat-mdc-button-base {
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
`],encapsulation:2})})()}return t})();function rS(t){return t.hasAttribute("mat-raised-button")?"elevated":t.hasAttribute("mat-stroked-button")?"outlined":t.hasAttribute("mat-flat-button")?"filled":t.hasAttribute("mat-button")?"text":null}var Yn=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[An,me]})}return t})();var oS=Math.pow(2,31)-1,lo=class{_overlayRef;instance;containerInstance;_afterDismissed=new z;_afterOpened=new z;_onAction=new z;_durationTimeoutId;_dismissedByAction=!1;constructor(n,e){this._overlayRef=e,this.containerInstance=n,n._onExit.subscribe(()=>this._finishDismiss())}dismiss(){this._afterDismissed.closed||this.containerInstance.exit(),clearTimeout(this._durationTimeoutId)}dismissWithAction(){this._onAction.closed||(this._dismissedByAction=!0,this._onAction.next(),this._onAction.complete(),this.dismiss()),clearTimeout(this._durationTimeoutId)}closeWithAction(){this.dismissWithAction()}_dismissAfter(n){this._durationTimeoutId=setTimeout(()=>this.dismiss(),Math.min(n,oS))}_open(){this._afterOpened.closed||(this._afterOpened.next(),this._afterOpened.complete())}_finishDismiss(){this._overlayRef.dispose(),this._onAction.closed||this._onAction.complete(),this._afterDismissed.next({dismissedByAction:this._dismissedByAction}),this._afterDismissed.complete(),this._dismissedByAction=!1}afterDismissed(){return this._afterDismissed}afterOpened(){return this.containerInstance._onEnter}onAction(){return this._onAction}},zg=new I("MatSnackBarData"),Va=class{politeness="polite";announcementMessage="";viewContainerRef;duration=0;panelClass;direction;data=null;horizontalPosition="center";verticalPosition="bottom"},sS=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","matSnackBarLabel",""]],hostAttrs:[1,"mat-mdc-snack-bar-label","mdc-snackbar__label"]})}return t})(),lS=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","matSnackBarActions",""]],hostAttrs:[1,"mat-mdc-snack-bar-actions","mdc-snackbar__actions"]})}return t})(),cS=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","matSnackBarAction",""]],hostAttrs:[1,"mat-mdc-snack-bar-action","mdc-snackbar__action"]})}return t})(),Hg=(()=>{class t{snackBarRef=p(lo);data=p(zg);action(){this.snackBarRef.dismissWithAction()}get hasAction(){return!!this.data.action}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){function e(i,a){if(i&1){let r=ft();y(0,"div",1)(1,"button",2),xe("click",function(){at(r);let s=M();return rt(s.action())}),S(2),v()()}if(i&2){let r=M();h(2),ae(" ",r.data.action," ")}}return T({type:t,selectors:[["simple-snack-bar"]],hostAttrs:[1,"mat-mdc-simple-snack-bar"],exportAs:["matSnackBar"],decls:3,vars:2,consts:[["matSnackBarLabel",""],["matSnackBarActions",""],["matButton","","matSnackBarAction","",3,"click"]],template:function(a,r){a&1&&(y(0,"div",0),S(1),v(),E(2,e,3,1,"div",1)),a&2&&(h(),ae(" ",r.data.message,`
`),h(),D(r.hasAction?2:-1))},dependencies:[ja,sS,lS,cS],styles:[`.mat-mdc-simple-snack-bar {
  display: flex;
}
.mat-mdc-simple-snack-bar .mat-mdc-snack-bar-label {
  max-height: 50vh;
  overflow: auto;
}
`],encapsulation:2})})()}return t})(),im="_mat-snack-bar-enter",am="_mat-snack-bar-exit",dS=(()=>{class t extends Fa{_ngZone=p(K);_elementRef=p(X);_changeDetectorRef=p(We);_platform=p(Ie);_animationsDisabled=tt();snackBarConfig=p(Va);_document=p(Y);_trackedModals=new Set;_enterFallback;_exitFallback;_injector=p(re);_announceDelay=150;_announceTimeoutId;_destroyed=!1;_portalOutlet;_onAnnounce=new z;_onExit=new z;_onEnter=new z;_animationState="void";_live;_label;_role;_liveElementId=p(wt).getId("mat-snack-bar-container-live-");constructor(){super();let e=this.snackBarConfig;e.politeness==="assertive"&&!e.announcementMessage?this._live="assertive":e.politeness==="off"?this._live="off":this._live="polite",this._platform.FIREFOX&&(this._live==="polite"&&(this._role="status"),this._live==="assertive"&&(this._role="alert"))}attachComponentPortal(e){this._assertNotAttached();let i=this._portalOutlet.attachComponentPortal(e);return this._afterPortalAttached(),i}attachTemplatePortal(e){this._assertNotAttached();let i=this._portalOutlet.attachTemplatePortal(e);return this._afterPortalAttached(),i}attachDomPortal=e=>{this._assertNotAttached();let i=this._portalOutlet.attachDomPortal(e);return this._afterPortalAttached(),i};onAnimationEnd(e){e===am?this._completeExit():e===im&&(clearTimeout(this._enterFallback),this._ngZone.run(()=>{this._onEnter.next(),this._onEnter.complete()}))}enter(){this._destroyed||(this._animationState="visible",this._changeDetectorRef.markForCheck(),this._changeDetectorRef.detectChanges(),this._screenReaderAnnounce(),this._animationsDisabled?ht(()=>{this._ngZone.run(()=>queueMicrotask(()=>this.onAnimationEnd(im)))},{injector:this._injector}):(clearTimeout(this._enterFallback),this._enterFallback=setTimeout(()=>{this._elementRef.nativeElement.classList.add("mat-snack-bar-fallback-visible"),this.onAnimationEnd(im)},200)))}exit(){return this._destroyed?Ue(void 0):(this._ngZone.run(()=>{this._animationState="hidden",this._changeDetectorRef.markForCheck(),this._elementRef.nativeElement.setAttribute("mat-exit",""),clearTimeout(this._announceTimeoutId),this._animationsDisabled?ht(()=>{this._ngZone.run(()=>queueMicrotask(()=>this.onAnimationEnd(am)))},{injector:this._injector}):(clearTimeout(this._exitFallback),this._exitFallback=setTimeout(()=>this.onAnimationEnd(am),200))}),this._onExit)}ngOnDestroy(){this._destroyed=!0,this._clearFromModals(),this._completeExit()}_completeExit(){clearTimeout(this._exitFallback),queueMicrotask(()=>{this._onExit.next(),this._onExit.complete()})}_afterPortalAttached(){let e=this._elementRef.nativeElement,i=this.snackBarConfig.panelClass;i&&(Array.isArray(i)?i.forEach(o=>e.classList.add(o)):e.classList.add(i)),this._exposeToModals();let a=this._label.nativeElement,r="mdc-snackbar__label";a.classList.toggle(r,!a.querySelector(`.${r}`))}_exposeToModals(){let e=this._liveElementId,i=this._document.querySelectorAll('body > .cdk-overlay-container [aria-modal="true"]');for(let a=0;a<i.length;a++){let r=i[a],o=r.getAttribute("aria-owns");this._trackedModals.add(r),o?o.indexOf(e)===-1&&r.setAttribute("aria-owns",o+" "+e):r.setAttribute("aria-owns",e)}}_clearFromModals(){this._trackedModals.forEach(e=>{let i=e.getAttribute("aria-owns");if(i){let a=i.replace(this._liveElementId,"").trim();a.length>0?e.setAttribute("aria-owns",a):e.removeAttribute("aria-owns")}}),this._trackedModals.clear()}_assertNotAttached(){this._portalOutlet.hasAttached()}_screenReaderAnnounce(){this._announceTimeoutId||this._ngZone.runOutsideAngular(()=>{this._announceTimeoutId=setTimeout(()=>{if(this._destroyed)return;let e=this._elementRef.nativeElement,i=e.querySelector("[aria-hidden]"),a=e.querySelector("[aria-live]");if(i&&a){let r=null;this._platform.isBrowser&&document.activeElement instanceof HTMLElement&&i.contains(document.activeElement)&&(r=document.activeElement),i.removeAttribute("aria-hidden"),a.appendChild(i),r?.focus(),this._onAnnounce.next(),this._onAnnounce.complete()}},this._announceDelay)})}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=["label"];function i(a,r){}return T({type:t,selectors:[["mat-snack-bar-container"]],viewQuery:function(r,o){if(r&1&&qe(pl,7)(e,7),r&2){let s;H(s=U())&&(o._portalOutlet=s.first),H(s=U())&&(o._label=s.first)}},hostAttrs:[1,"mdc-snackbar","mat-mdc-snack-bar-container"],hostVars:6,hostBindings:function(r,o){r&1&&xe("animationend",function(l){return o.onAnimationEnd(l.animationName)})("animationcancel",function(l){return o.onAnimationEnd(l.animationName)}),r&2&&ee("mat-snack-bar-container-enter",o._animationState==="visible")("mat-snack-bar-container-exit",o._animationState==="hidden")("mat-snack-bar-container-animations-enabled",!o._animationsDisabled)},features:[Ve],decls:6,vars:3,consts:[["label",""],[1,"mdc-snackbar__surface","mat-mdc-snackbar-surface"],[1,"mat-mdc-snack-bar-label"],["aria-hidden","true"],["cdkPortalOutlet",""]],template:function(r,o){r&1&&(y(0,"div",1)(1,"div",2,0)(3,"div",3),Wt(4,i,0,0,"ng-template",4),v(),R(5,"div"),v()()),r&2&&(h(5),fe("aria-live",o._live)("role",o._role)("id",o._liveElementId))},dependencies:[pl],styles:[`@keyframes _mat-snack-bar-enter {
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
`],encapsulation:2,changeDetection:1})})()}return t})(),mS=new I("mat-snack-bar-default-options",{providedIn:"root",factory:()=>new Va}),co=(()=>{class t{_live=p(zd);_injector=p(re);_breakpointObserver=p(Vd);_parentSnackBar=p(t,{optional:!0,skipSelf:!0});_defaultConfig=p(mS);_animationsDisabled=tt();_snackBarRefAtThisLevel=null;simpleSnackBarComponent=Hg;snackBarContainerComponent=dS;handsetCssClass="mat-mdc-snack-bar-handset";get _openedSnackBarRef(){let e=this._parentSnackBar;return e?e._openedSnackBarRef:this._snackBarRefAtThisLevel}set _openedSnackBarRef(e){this._parentSnackBar?this._parentSnackBar._openedSnackBarRef=e:this._snackBarRefAtThisLevel=e}openFromComponent(e,i){return this._attach(e,i)}openFromTemplate(e,i){return this._attach(e,i)}open(e,i="",a){let r=N(N({},this._defaultConfig),a);return r.data={message:e,action:i},r.announcementMessage===e&&(r.announcementMessage=void 0),this.openFromComponent(this.simpleSnackBarComponent,r)}dismiss(){this._openedSnackBarRef&&this._openedSnackBarRef.dismiss()}ngOnDestroy(){this._snackBarRefAtThisLevel&&this._snackBarRefAtThisLevel.dismiss()}_attachSnackBarContainer(e,i){let a=i&&i.viewContainerRef&&i.viewContainerRef.injector,r=re.create({parent:a||this._injector,providers:[{provide:Va,useValue:i}]}),o=new Pa(this.snackBarContainerComponent,i.viewContainerRef,r),s=e.attach(o);return s.instance.snackBarConfig=i,s.instance}_attach(e,i){let a=N(N(N({},new Va),this._defaultConfig),i),r=this._createOverlay(a),o=this._attachSnackBarContainer(r,a),s=new lo(o,r);if(e instanceof li){let l=new Kn(e,null,{$implicit:a.data,snackBarRef:s});s.instance=o.attachTemplatePortal(l)}else{let l=this._createInjector(a,s),c=new Pa(e,void 0,l),d=o.attachComponentPortal(c);s.instance=d.instance}return this._breakpointObserver.observe(hg.HandsetPortrait).pipe(Ae(r.detachments())).subscribe(l=>{r.overlayElement.classList.toggle(this.handsetCssClass,l.matches)}),a.announcementMessage&&o._onAnnounce.subscribe(()=>{this._live.announce(a.announcementMessage,a.politeness)}),this._animateSnackBar(s,a),this._openedSnackBarRef=s,this._openedSnackBarRef}_animateSnackBar(e,i){e.afterDismissed().subscribe(()=>{this._openedSnackBarRef==e&&(this._openedSnackBarRef=null),i.announcementMessage&&this._live.clear()}),i.duration&&i.duration>0&&e.afterOpened().subscribe(()=>e._dismissAfter(i.duration)),this._openedSnackBarRef?(this._openedSnackBarRef.afterDismissed().subscribe(()=>{e.containerInstance.enter()}),this._openedSnackBarRef.dismiss()):e.containerInstance.enter()}_createOverlay(e){let i=new _i;i.direction=e.direction;let a=xl(this._injector),r=e.direction==="rtl",o=e.horizontalPosition==="left"||e.horizontalPosition==="start"&&!r||e.horizontalPosition==="end"&&r,s=!o&&e.horizontalPosition!=="center";return o?a.left("0"):s?a.right("0"):a.centerHorizontally(),e.verticalPosition==="top"?a.top("0"):a.bottom("0"),i.positionStrategy=a,i.disableAnimations=this._animationsDisabled,Ba(this._injector,i)}_createInjector(e,i){let a=e&&e.viewContainerRef&&e.viewContainerRef.injector;return re.create({parent:a||this._injector,providers:[{provide:lo,useValue:i},{provide:zg,useValue:e.data}]})}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var rm=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({providers:[co],imports:[qi,La,Yn,Hg,me]})}return t})();var yi=class{},Cl=class t{constructor(n){this.snackBar=n}snackBar;showError(n){this.snackBar.open(n,"Close",{verticalPosition:"top"})}showWarning(n){this.snackBar.open(n,"Close",{duration:3e3})}static \u0275fac=function(e){return new(e||t)(B(co))};static \u0275prov=te({token:t,factory:t.\u0275fac})};var St=class t{constructor(n){this.notificationService=n}notificationService;static BASE_URL=window.location.pathname+window.location.search+"#";toAsyncApi(n){try{let e=this.mapChannels(n.channels,n.operations,n.components.messages,n.components.schemas,n.servers,n.defaultContentType),i={info:this.mapInfo(n),servers:this.mapServers(n.servers),channels:e,channelOperations:e.flatMap(a=>a.operations),components:{schemas:this.mapSchemas(n.components.schemas)},defaultContentType:n.defaultContentType};return this.postProcess(i),i}catch(e){this.notificationService.showError("Error parsing AsyncAPI: "+e?.message);return}}mapInfo(n){return{title:n.info.title,version:n.info.version,description:n.info.description,contact:{url:n.info.contact?.url,email:n.info.contact?.email&&{name:n.info.contact.email,href:"mailto:"+n.info.contact.email}||void 0},license:{name:n.info.license?.name,url:n.info.license?.url},asyncApiJson:n}}mapServers(n){let e=new Map;if(n)for(let i in n){let a=n[i];e.set(i,{host:a.host,protocol:a.protocol,anchorIdentifier:Jr+i,anchorUrl:t.BASE_URL+Jr+i})}return e}mapChannels(n,e,i,a,r,o){let s={};for(let l in n)this.parsingErrorBoundary("channel "+l,()=>{let c=n[l],d="channel-"+this.toSafeAnchorIdentifier(l);s[l]={name:c.address,anchorIdentifier:d,anchorUrl:t.BASE_URL+d,operations:[],bindings:c.bindings||{}}});for(let l in e)this.parsingErrorBoundary("operation "+l,()=>{let c=e[l],d=this.resolveRefId(c.channel.$ref),m=n[d].address;this.verifyBindings(c.bindings,"operation "+l),this.mapServerAsyncApiMessages(m,n[d],i,a,c.messages,o).forEach(f=>{let g=this.parsingErrorBoundary("channel with name "+m,()=>this.mapChannelOperation(m,n[d],n,c,f,r));g!=null&&s[d].operations.push(g)})});return Object.values(s).forEach(l=>{l.operations=l.operations.sort((c,d)=>c.operation.protocol===d.operation.protocol?c.operation.operationType===d.operation.operationType?c.name===d.name?c.operation.message.name.localeCompare(d.operation.message.name):c.name.localeCompare(d.name):c.operation.operationType.localeCompare(d.operation.operationType):c.operation.protocol!=null&&d.operation.protocol!=null?c.operation.protocol.localeCompare(d.operation.protocol):0)}),Object.values(s).sort((l,c)=>l.name.localeCompare(c.name))}mapChannelOperation(n,e,i,a,r,o){let l=(e?.servers?.map(m=>this.resolveRefId(m.$ref))||o&&Object.keys(o)||[]).map(m=>({name:m,anchorIdentifier:Jr+m,anchorUrl:t.BASE_URL+Jr+m})),c=this.mapOperation(n,i,a.action,l,r,a.bindings,a.description,a.reply),d=Rd+[c.protocol,this.toSafeAnchorIdentifier(n),c.operationType,c.message.title].join("-");return{name:n,anchorIdentifier:d,anchorUrl:t.BASE_URL+d,description:e.description,operation:c,bindings:e.bindings||{}}}mapServerAsyncApiMessages(n,e,i,a,r,o){return r.map(s=>this.parsingErrorBoundary("message of channel "+n,()=>{let l=this.resolveRefId(s.$ref),c=this.resolveTitleFromName(l),d=e.messages[l],m=this.resolveRefId(d.$ref),u=i[m];return this.verifyBindings(u.bindings,"message "+u.name),{name:u.name||c,title:u.title||c,description:u.description,contentType:u.contentType||o,payload:this.mapPayload(u.name||c,u.payload.schema,a),headers:this.mapHeaders(u.headers),bindings:this.mapServerAsyncApiMessageBindings(u.bindings),rawBindings:u.bindings||{}}})).filter(s=>s!==void 0)}mapHeaders(n){if(n===void 0)return;let e=this.resolveRefId(n.$ref);return{ts_type:"ref",name:e,title:e,anchorUrl:t.BASE_URL+e}}mapPayload(n,e,i){if("$ref"in e){let a=this.resolveRefId(e.$ref);return{ts_type:"ref",name:a,title:this.resolveTitleFromName(a),anchorUrl:t.BASE_URL+a}}return this.mapSchemaObj(n,e,i)}mapServerAsyncApiMessageBindings(n){let e=new Map;return n!==void 0&&Object.keys(n).forEach(i=>{e.set(i,this.mapServerAsyncApiMessageBinding(n[i]))}),e}mapServerAsyncApiMessageBinding(n){let e={};return Object.keys(n).forEach(i=>{let a=n[i];typeof a=="object"?e[i]=this.mapServerAsyncApiMessageBinding(a):e[i]=a}),e}mapOperation(n,e,i,a,r,o,s,l){return{protocol:this.getProtocol(o)||"unsupported-protocol",bindings:o||{},servers:a,operationType:i=="send"?"send":"receive",channelName:n,description:s,message:r,reply:this.mapOperationReply(l,e)}}mapOperationReply(n,e){if(!n)return;let i=this.resolveRefId(n.channel.$ref),a=e[i].address,r=this.resolveRefId(n.messages[0].$ref);return{channelAnchorUrl:t.BASE_URL+Rd+i,channelName:a,messageAnchorUrl:t.BASE_URL+r,messageName:r}}getProtocol(n){if(n!==void 0)return Object.keys(n)[0]}mapSchemas(n){let e=new Map;return Object.entries(n).forEach(([i,a])=>{let r=this.parsingErrorBoundary("schema with name "+i,()=>this.mapSchema(i,a,n));r!=null&&e.set(i,r)}),new Map([...e.entries()].sort((i,a)=>i[1].title.localeCompare(a[1].title)))}mapSchema(n,e,i){return"$ref"in e?this.mapSchemaRef(n,e):this.mapSchemaObj(n,e,i)}mapSchemaObj(n,e,i){let a={};this.addPropertiesToSchema(e,a,i),e.allOf!==void 0&&e.allOf.forEach(s=>{this.addPropertiesToSchema(s,a,i)}),e.anyOf!==void 0&&e.anyOf.length>0&&this.addPropertiesToSchema(e.anyOf[0],a,i),e.oneOf!==void 0&&e.oneOf.length>0&&this.addPropertiesToSchema(e.oneOf[0],a,i);let r=e.items!==void 0?this.mapSchema(n+"[]",e.items,i):void 0,o=e.examples!==void 0&&0<e.examples.length?new Zt(e.examples[0]):void 0;return{ts_type:"object",name:n,title:this.resolveTitleFromName(n)||"undefined-title",usedBy:[],anchorIdentifier:this.toSafeAnchorIdentifier(n),anchorUrl:t.BASE_URL+n,description:e.description,deprecated:e.deprecated,enum:e.enum,example:o,type:e.type,format:e.format,properties:a,required:e.required,items:r,minItems:e.minItems,maxItems:e.maxItems,uniqueItems:e.uniqueItems,minLength:e.minLength,maxLength:e.maxLength,pattern:e.pattern,minimum:e.exclusiveMinimum?e.exclusiveMinimum:e.minimum,maximum:e.exclusiveMaximum?e.exclusiveMinimum:e.maximum,exclusiveMinimum:e.minimum==e.exclusiveMinimum,exclusiveMaximum:e.maximum==e.exclusiveMaximum,multipleOf:e.multipleOf}}addPropertiesToSchema(n,e,i){let a=this.resolveSchema(n,i);"properties"in a&&a.properties!==void 0&&Object.entries(a.properties).forEach(([r,o])=>{e[r]=this.mapSchema(r,o,i)})}resolveSchema(n,e){let i=n;for(;"$ref"in i;){let a=this.resolveRefId(i.$ref),r=e[a];if(r!==void 0)i=r;else throw new Error("Schema "+a+" not found")}return i}mapSchemaRef(n,e){let i=this.resolveRefId(e.$ref);return{ts_type:"object",name:n,title:this.resolveTitleFromName(n),usedBy:[],anchorIdentifier:this.toSafeAnchorIdentifier(n),anchorUrl:t.BASE_URL+n,refAnchorUrl:t.BASE_URL+i,refName:i,refTitle:this.resolveTitleFromName(i)}}resolveRefId(n){return n.split("/").pop()}resolveTitleFromName(n){return n.split(".").pop()}verifyBindings(n,e){(n==null||Object.keys(n).length==0)&&this.notificationService.showWarning("No binding defined for "+e)}postProcess(n){n.components.schemas.forEach(e=>{n.channels.forEach(i=>{i.operations.forEach(a=>{a.operation.message.payload.name===e.name&&e.usedBy.push({name:a.name,anchorUrl:a.anchorUrl,type:"channel"}),a.operation.message.headers?.name===e.name&&e.usedBy.push({name:a.name,anchorUrl:a.anchorUrl,type:"channel"})})}),n.components.schemas.forEach(i=>{Object.values(i?.properties||{}).forEach(a=>{a.refName===e.name&&e.usedBy.push({name:i.title,anchorUrl:i.anchorUrl,type:"schema"})}),i.items?.refName===e.name&&e.usedBy.push({name:i.title,anchorUrl:i.anchorUrl,type:"schema"})})})}toSafeAnchorIdentifier(n){return n.replaceAll(/\$/g,"\xA7")}parsingErrorBoundary(n,e){return Kf(e,i=>{this.notificationService.showError("Error parsing AsyncAPI "+n+": "+i.message)})}static \u0275fac=function(e){return new(e||t)(B(yi))};static \u0275prov=te({token:t,factory:t.\u0275fac})};var P_=zy(R_());var cc={$schema:"http://json-schema.org/draft-07/schema#",$ref:"#/definitions/ServerAsyncApi",definitions:{ServerAsyncApi:{type:"object",properties:{asyncapi:{type:"string"},info:{$ref:"#/definitions/ServerAsyncApiInfo"},defaultContentType:{type:"string"},servers:{$ref:"#/definitions/ServerServers"},channels:{$ref:"#/definitions/ServerChannels"},operations:{$ref:"#/definitions/ServerOperations"},components:{$ref:"#/definitions/ServerComponents"}},required:["asyncapi","info","defaultContentType","channels","operations","components"],additionalProperties:!1},ServerAsyncApiInfo:{type:"object",properties:{title:{type:"string"},version:{type:"string"},description:{type:"string"},contact:{type:"object",properties:{name:{type:"string"},url:{type:"string"},email:{type:"string"}},additionalProperties:{}},license:{type:"object",properties:{name:{type:"string"},url:{type:"string"}},additionalProperties:{}},termsOfService:{type:"string"}},required:["title"],additionalProperties:{}},ServerServers:{type:"object",additionalProperties:{type:"object",properties:{host:{type:"string"},protocol:{type:"string"},description:{type:"string"}},required:["host","protocol"],additionalProperties:!1}},ServerChannels:{type:"object",additionalProperties:{$ref:"#/definitions/ServerChannel"}},ServerChannel:{type:"object",properties:{address:{type:"string"},description:{type:"string"},messages:{type:"object",additionalProperties:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},servers:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},bindings:{$ref:"#/definitions/ServerBindings"}},required:["address"],additionalProperties:!1},ServerBindings:{type:"object",additionalProperties:{$ref:"#/definitions/ServerBinding"}},ServerBinding:{type:"object",additionalProperties:{anyOf:[{$ref:"#/definitions/ServerBinding"},{}]}},ServerOperations:{type:"object",additionalProperties:{$ref:"#/definitions/ServerOperation"}},ServerOperation:{type:"object",properties:{action:{type:"string"},title:{type:"string"},description:{type:"string"},channel:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},messages:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},reply:{$ref:"#/definitions/ServerOperationReply"},bindings:{$ref:"#/definitions/ServerBindings"}},required:["action","channel","messages"],additionalProperties:!1},ServerOperationReply:{type:"object",properties:{channel:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},messages:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}}},required:["channel","messages"],additionalProperties:!1},ServerComponents:{type:"object",properties:{schemas:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiSchema"}},messages:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiMessage"}}},required:["schemas","messages"],additionalProperties:!1},ServerAsyncApiSchema:{type:"object",properties:{title:{type:"string"},description:{type:"string"},deprecated:{type:"boolean"},enum:{type:"array",items:{type:["string","null"]}},examples:{type:"array",items:{}},type:{anyOf:[{type:"string"},{type:"array",items:{type:"string"}}]},format:{type:"string"},not:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"},allOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},anyOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},oneOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},properties:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},required:{type:"array",items:{type:"string"}},items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"},minItems:{type:"number"},maxItems:{type:"number"},uniqueItems:{type:"boolean"},minLength:{type:"number"},maxLength:{type:"number"},pattern:{type:"string"},minimum:{type:"number"},maximum:{type:"number"},exclusiveMinimum:{type:"number"},exclusiveMaximum:{type:"number"},multipleOf:{type:"number"}},additionalProperties:!1},ServerAsyncApiSchemaOrRef:{anyOf:[{$ref:"#/definitions/ServerAsyncApiSchema"},{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}]},ServerAsyncApiMessage:{type:"object",properties:{name:{type:"string"},title:{type:"string"},description:{type:"string"},contentType:{type:"string"},payload:{type:"object",properties:{schemaFormat:{type:"string"},schema:{anyOf:[{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},{$ref:"#/definitions/ServerAsyncApiSchema"}]}},required:["schemaFormat","schema"],additionalProperties:!1},headers:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},bindings:{$ref:"#/definitions/ServerBindings"}},required:["payload"],additionalProperties:!1}}};var nr=class t{constructor(n){this.notificationService=n}notificationService;ajv=new P_.default({allErrors:!0});_logToConsole=!0;validate(n){let e=JSON.parse(JSON.stringify(n));this.ajv.opts.removeAdditional=!1;let i=this.ajv.compile(cc);i(e)||this._logToConsole&&console.info("Validation error while parsing AsyncAPI file in Springwolf format (strict mode)",i.errors),this.ajv.removeSchema(cc),this.ajv.opts.removeAdditional=!0;let r=this.ajv.compile(cc);r(e)||(this.notificationService.showError("Validation error while parsing AsyncAPI file in Springwolf format (lenient mode), see console logs for details."),this._logToConsole&&console.warn("Validation error while parsing AsyncAPI file in Springwolf format (lenient mode)",r.errors))}static \u0275fac=function(e){return new(e||t)(B(yi))};static \u0275prov=te({token:t,factory:t.\u0275fac})};var ut=class t{constructor(n,e,i,a){this.http=n;this.asyncApiMapperService=e;this.uiService=i;this.asyncApiValidatorService=a;this.docs=this.uiService.isGroup$.pipe(Gt(r=>{let o=r==Ne.DEFAULT_GROUP?Nn.docs:Nn.getDocsForGroupEndpoint(r);return this.http.get(o)}),ai(r=>this.asyncApiValidatorService.validate(r)),Me(r=>this.asyncApiMapperService.toAsyncApi(r)),Ke(r=>r!==void 0),Ln())}http;asyncApiMapperService;uiService;asyncApiValidatorService;docs;getAsyncApi(){return this.docs}static \u0275fac=function(e){return new(e||t)(B(Xt),B(St),B(Ne),B(nr))};static \u0275prov=te({token:t,factory:t.\u0275fac})};var ir=class t{constructor(n){this.http=n}http;publishable={};canPublish(n){return this.publishable[n]===void 0&&(this.publishable[n]=this.http.get(Nn.getPublishEndpoint(n),{observe:"response"}).pipe(Me(e=>e.status===200),ds())),this.publishable[n]}publish(n,e,i,a,r,o){let s=Nn.getPublishEndpoint(n),l=new qt().set("topic",e),c={payload:i,type:a,headers:r,bindings:o};return console.log(`Publishing to ${s} with messageBinding ${JSON.stringify(o)} and headers ${JSON.stringify(r)}: ${JSON.stringify(c)}`),this.http.post(s,c,{params:l})}static \u0275fac=function(e){return new(e||t)(B(Xt))};static \u0275prov=te({token:t,factory:t.\u0275fac})};var F_={providers:[vh(),mf(),fd(gd()),Bs.production?[]:th(pf.forRoot($s,{delay:100})),Od(),{provide:Ia,useClass:Qs},ut,St,nr,{provide:yi,useClass:Cl},ir,{provide:Ne,useClass:Js}]};var Qp=new I("MAT_MENU_PANEL"),Vo=(()=>{class t{_isAnchor;_elementRef=p(X);_document=p(Y);_focusMonitor=p(In);_parentMenu=p(Qp,{optional:!0});_changeDetectorRef=p(We);role="menuitem";disabled=!1;disabledInteractive=!1;disableRipple=!1;_hovered=new z;_focused=new z;_highlighted=!1;_triggersSubmenu=!1;constructor(){p(dt).load(hn),this._parentMenu?.addItem?.(this),this._isAnchor=this._elementRef.nativeElement.tagName==="A"}focus(e,i){this._focusMonitor&&e?this._focusMonitor.focusVia(this._getHostElement(),e,i):this._getHostElement().focus(i),this._focused.next(this)}ngAfterViewInit(){this._focusMonitor&&this._focusMonitor.monitor(this._elementRef,!1)}ngOnDestroy(){this._focusMonitor&&this._focusMonitor.stopMonitoring(this._elementRef),this._parentMenu&&this._parentMenu.removeItem&&this._parentMenu.removeItem(this),this._hovered.complete(),this._focused.complete()}_getTabIndex(){return this.disabled&&!this.disabledInteractive?"-1":"0"}_getAriaDisabled(){return this._isAnchor?this.disabled||null:this.disabled&&this.disabledInteractive?!0:null}_getDisabled(){return this.disabledInteractive||!this.disabled?null:!0}_getHostElement(){return this._elementRef.nativeElement}_checkDisabled(e){this.disabled&&(e.preventDefault(),e.stopPropagation())}_handleMouseEnter(){this._hovered.next(this)}getLabel(){let e=this._elementRef.nativeElement.cloneNode(!0),i=e.querySelectorAll("mat-icon, .material-icons, .material-symbols-outlined, .material-symbols-rounded, .material-symbols-sharp");for(let a=0;a<i.length;a++)i[a].remove();return e.textContent?.trim()||""}_setHighlighted(e){this._highlighted=e,this._changeDetectorRef.markForCheck()}_setTriggersSubmenu(e){this._triggersSubmenu=e,this._changeDetectorRef.markForCheck()}_hasFocus(){return this._document&&this._document.activeElement===this._getHostElement()}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=[[["mat-icon"],["","matMenuItemIcon",""]],"*"],i=["mat-icon, [matMenuItemIcon]","*"];function a(r,o){r&1&&(ha(),y(0,"svg",2),R(1,"polygon",3),v())}return T({type:t,selectors:[["","mat-menu-item",""]],hostAttrs:[1,"mat-mdc-menu-item","mat-focus-indicator"],hostVars:12,hostBindings:function(o,s){o&1&&xe("click",function(c){return s._checkDisabled(c)})("mouseenter",function(){return s._handleMouseEnter()}),o&2&&(fe("role",s.role)("tabindex",s._getTabIndex())("aria-disabled",s._getAriaDisabled())("disabled",s._getDisabled()),ee("mat-mdc-menu-item-highlighted",s._highlighted)("mat-mdc-menu-item-submenu-trigger",s._triggersSubmenu)("mat-mdc-menu-item-disabled",s.disabled)("mat-mdc-menu-item-disabled-interactive",s.disabledInteractive))},inputs:{role:"role",disabled:[2,"disabled","disabled",ve],disabledInteractive:[2,"disabledInteractive","disabledInteractive",ve],disableRipple:[2,"disableRipple","disableRipple",ve]},exportAs:["matMenuItem"],ngContentSelectors:i,decls:5,vars:3,consts:[[1,"mat-mdc-menu-item-text"],["matRipple","",1,"mat-mdc-menu-ripple",3,"matRippleDisabled","matRippleTrigger"],["viewBox","0 0 5 10","focusable","false","aria-hidden","true",1,"mat-mdc-menu-submenu-icon"],["points","0,0 5,5 0,10"]],template:function(o,s){o&1&&(de(e),j(0),y(1,"span",0),j(2,1),v(),R(3,"div",1),E(4,a,2,0,"svg",2)),o&2&&(h(3),P("matRippleDisabled",s.disableRipple||s.disabled)("matRippleTrigger",s._getHostElement()),h(),D(s._triggersSubmenu?4:-1))},dependencies:[wl],encapsulation:2})})()}return t})();var dM=new I("MatMenuContent");var mM=new I("mat-menu-default-options",{providedIn:"root",factory:()=>({overlapTrigger:!1,xPosition:"after",yPosition:"below",backdropClass:"cdk-overlay-transparent-backdrop"})}),Xp="_mat-menu-enter",dc="_mat-menu-exit",rr=(()=>{class t{_elementRef=p(X);_changeDetectorRef=p(We);_injector=p(re);_keyManager;_xPosition;_yPosition;_firstItemFocusRef;_exitFallbackTimeout;_animationsDisabled=tt();_allItems;_directDescendantItems=new Vn;_classList={};_panelAnimationState="void";_animationDone=new z;_isAnimating=ne(!1);parentMenu;direction;overlayPanelClass;backdropClass;ariaLabel;ariaLabelledby;ariaDescribedby;get xPosition(){return this._xPosition}set xPosition(e){this._xPosition=e,this.setPositionClasses()}get yPosition(){return this._yPosition}set yPosition(e){this._yPosition=e,this.setPositionClasses()}templateRef;items;lazyContent;overlapTrigger=!1;hasBackdrop;get panelClass(){return this._previousPanelClass}set panelClass(e){let i=this._previousPanelClass,a=N({},this._classList);i&&i.length&&i.split(" ").forEach(r=>{a[r]=!1}),this._previousPanelClass=e,e&&e.length&&(e.split(" ").forEach(r=>{a[r]=!0}),this._elementRef.nativeElement.className=""),this._classList=a}_previousPanelClass="";get classList(){return this.panelClass}set classList(e){this.panelClass=e}closed=new Te;close=this.closed;panelId=p(wt).getId("mat-menu-panel-");constructor(){let e=p(mM);this.overlayPanelClass=e.overlayPanelClass||"",this._xPosition=e.xPosition,this._yPosition=e.yPosition,this.backdropClass=e.backdropClass,this.overlapTrigger=e.overlapTrigger,this.hasBackdrop=e.hasBackdrop}ngOnInit(){this.setPositionClasses()}ngAfterContentInit(){this._updateDirectDescendants(),this._keyManager=new zi(this._directDescendantItems).withWrap().withTypeAhead().withHomeAndEnd().skipPredicate(e=>e.disabled&&!e.disabledInteractive),this._keyManager.tabOut.subscribe(()=>this.closed.emit("tab")),this._directDescendantItems.changes.pipe(_t(this._directDescendantItems),Gt(e=>It(...e.map(i=>i._focused)))).subscribe(e=>this._keyManager.updateActiveItem(e)),this._directDescendantItems.changes.subscribe(e=>{let i=this._keyManager;if(this._panelAnimationState==="enter"&&i.activeItem?._hasFocus()){let a=e.toArray(),r=Math.max(0,Math.min(a.length-1,i.activeItemIndex||0));a[r]&&!a[r].disabled?i.setActiveItem(r):i.setNextItemActive()}})}ngOnDestroy(){this._keyManager?.destroy(),this._directDescendantItems.destroy(),this.closed.complete(),this._firstItemFocusRef?.destroy(),clearTimeout(this._exitFallbackTimeout)}_hovered(){return this._directDescendantItems.changes.pipe(_t(this._directDescendantItems),Gt(i=>It(...i.map(a=>a._hovered))))}addItem(e){}removeItem(e){}_handleKeydown(e){let i=e.keyCode,a=this._keyManager;switch(i){case 27:vi(e)||(e.preventDefault(),this.closed.emit("keydown"));break;case 37:this.parentMenu&&this.direction==="ltr"&&this.closed.emit("keydown");break;case 39:this.parentMenu&&this.direction==="rtl"&&this.closed.emit("keydown");break;default:(i===38||i===40)&&a.setFocusOrigin("keyboard"),a.onKeydown(e);return}}focusFirstItem(e="program"){this._firstItemFocusRef?.destroy(),this._firstItemFocusRef=ht(()=>{let i=this._resolvePanel();if(!i||!i.contains(document.activeElement)){let a=this._keyManager;a.setFocusOrigin(e).setFirstItemActive(),!a.activeItem&&i&&i.focus()}},{injector:this._injector})}resetActiveItem(){this._keyManager.setActiveItem(-1)}setElevation(e){}setPositionClasses(e=this.xPosition,i=this.yPosition){this._classList=De(N({},this._classList),{"mat-menu-before":e==="before","mat-menu-after":e==="after","mat-menu-above":i==="above","mat-menu-below":i==="below"}),this._changeDetectorRef.markForCheck()}_onAnimationDone(e){let i=e===dc;(i||e===Xp)&&(i&&(clearTimeout(this._exitFallbackTimeout),this._exitFallbackTimeout=void 0),this._animationDone.next(i?"void":"enter"),this._isAnimating.set(!1))}_onAnimationStart(e){(e===Xp||e===dc)&&this._isAnimating.set(!0)}_setIsOpen(e){if(this._panelAnimationState=e?"enter":"void",e){if(this._keyManager.activeItemIndex===0){let i=this._resolvePanel();i&&(i.scrollTop=0)}}else this._animationsDisabled||(this._exitFallbackTimeout=setTimeout(()=>this._onAnimationDone(dc),200));this._animationsDisabled&&setTimeout(()=>{this._onAnimationDone(e?Xp:dc)}),this._changeDetectorRef.markForCheck()}_updateDirectDescendants(){this._allItems.changes.pipe(_t(this._allItems)).subscribe(e=>{this._directDescendantItems.reset(e.filter(i=>i._parentMenu===this)),this._directDescendantItems.notifyOnChanges()})}_resolvePanel(){let e=null;return this._directDescendantItems.length&&(e=this._directDescendantItems.first._getHostElement().closest('[role="menu"]')),e}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=["*"];function i(a,r){if(a&1){let o=ft();Qe(0,"div",0),bs("click",function(){at(o);let l=M();return rt(l.closed.emit("click"))})("animationstart",function(l){at(o);let c=M();return rt(c._onAnimationStart(l.animationName))})("animationend",function(l){at(o);let c=M();return rt(c._onAnimationDone(l.animationName))})("animationcancel",function(l){at(o);let c=M();return rt(c._onAnimationDone(l.animationName))}),Qe(1,"div",1),j(2),et()()}if(a&2){let o=M();At(o._classList),ee("mat-menu-panel-animations-disabled",o._animationsDisabled)("mat-menu-panel-exit-animation",o._panelAnimationState==="void")("mat-menu-panel-animating",o._isAnimating()),di("id",o.panelId),fe("aria-label",o.ariaLabel||null)("aria-labelledby",o.ariaLabelledby||null)("aria-describedby",o.ariaDescribedby||null)}}return T({type:t,selectors:[["mat-menu"]],contentQueries:function(r,o,s){if(r&1&&ot(s,dM,5)(s,Vo,5)(s,Vo,4),r&2){let l;H(l=U())&&(o.lazyContent=l.first),H(l=U())&&(o._allItems=l),H(l=U())&&(o.items=l)}},viewQuery:function(r,o){if(r&1&&qe(li,5),r&2){let s;H(s=U())&&(o.templateRef=s.first)}},hostVars:3,hostBindings:function(r,o){r&2&&fe("aria-label",null)("aria-labelledby",null)("aria-describedby",null)},inputs:{backdropClass:"backdropClass",ariaLabel:[0,"aria-label","ariaLabel"],ariaLabelledby:[0,"aria-labelledby","ariaLabelledby"],ariaDescribedby:[0,"aria-describedby","ariaDescribedby"],xPosition:"xPosition",yPosition:"yPosition",overlapTrigger:[2,"overlapTrigger","overlapTrigger",ve],hasBackdrop:[2,"hasBackdrop","hasBackdrop",a=>a==null?null:ve(a)],panelClass:[0,"class","panelClass"],classList:"classList"},outputs:{closed:"closed",close:"close"},exportAs:["matMenu"],features:[Je([{provide:Qp,useExisting:t}])],ngContentSelectors:e,decls:1,vars:0,consts:[["tabindex","-1","role","menu",1,"mat-mdc-menu-panel",3,"click","animationstart","animationend","animationcancel","id"],[1,"mat-mdc-menu-content"]],template:function(r,o){r&1&&(de(),Gc(0,i,3,12,"ng-template"))},styles:[`mat-menu {
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
`],encapsulation:2})})()}return t})(),pM=new I("mat-menu-scroll-strategy",{providedIn:"root",factory:()=>{let t=p(re);return()=>oo(t)}});var ar=new WeakMap,uM=(()=>{class t{_canHaveBackdrop;_element=p(X);_viewContainerRef=p(wn);_menuItemInstance=p(Vo,{optional:!0,self:!0});_dir=p(Tt,{optional:!0});_focusMonitor=p(In);_ngZone=p(K);_injector=p(re);_scrollStrategy=p(pM);_changeDetectorRef=p(We);_animationsDisabled=tt();_portal;_overlayRef=null;_menuOpen=!1;_closingActionsSubscription=Mt.EMPTY;_menuCloseSubscription=Mt.EMPTY;_pendingRemoval;_parentMaterialMenu;_parentInnerPadding;_openedBy=void 0;get _menu(){return this._menuInternal}set _menu(e){e!==this._menuInternal&&(this._menuInternal=e,this._menuCloseSubscription.unsubscribe(),e?(this._parentMaterialMenu,this._menuCloseSubscription=e.close.subscribe(i=>{this._destroyMenu(i),(i==="click"||i==="tab")&&this._parentMaterialMenu&&this._parentMaterialMenu.closed.emit(i)})):this._destroyMenu(),this._menuItemInstance?._setTriggersSubmenu(this._triggersSubmenu()))}_menuInternal=null;constructor(e){this._canHaveBackdrop=e;let i=p(Qp,{optional:!0});this._parentMaterialMenu=i instanceof rr?i:void 0}ngOnDestroy(){this._menu&&this._ownsMenu(this._menu)&&ar.delete(this._menu),this._pendingRemoval?.unsubscribe(),this._menuCloseSubscription.unsubscribe(),this._closingActionsSubscription.unsubscribe(),this._overlayRef&&(this._overlayRef.dispose(),this._overlayRef=null)}get menuOpen(){return this._menuOpen}get dir(){return this._dir&&this._dir.value==="rtl"?"rtl":"ltr"}_triggersSubmenu(){return!!(this._menuItemInstance&&this._parentMaterialMenu&&this._menu)}_closeMenu(){this._menu?.close.emit()}_openMenu(e){if(this._triggerIsAriaDisabled())return;let i=this._menu;if(this._menuOpen||!i)return;this._pendingRemoval?.unsubscribe();let a=ar.get(i);ar.set(i,this),a&&a!==this&&a._closeMenu();let r=this._createOverlay(i),o=r.getConfig(),s=o.positionStrategy;this._setPosition(i,s),this._canHaveBackdrop?o.hasBackdrop=i.hasBackdrop==null?!this._triggersSubmenu():i.hasBackdrop:o.hasBackdrop=i.hasBackdrop??!1,r.hasAttached()||(r.attach(this._getPortal(i)),i.lazyContent?.attach(this.menuData)),this._closingActionsSubscription=this._menuClosingActions().subscribe(()=>this._closeMenu()),i.parentMenu=this._triggersSubmenu()?this._parentMaterialMenu:void 0,i.direction=this.dir,e&&i.focusFirstItem(this._openedBy||"program"),this._setIsMenuOpen(!0),i instanceof rr&&(i._setIsOpen(!0),i._directDescendantItems.changes.pipe(Ae(i.close)).subscribe(()=>{s.withLockedPosition(!1).reapplyLastPosition(),s.withLockedPosition(!0)}))}focus(e,i){this._focusMonitor&&e?this._focusMonitor.focusVia(this._element,e,i):this._element.nativeElement.focus(i)}_destroyMenu(e){let i=this._overlayRef,a=this._menu;!i||!this.menuOpen||(this._closingActionsSubscription.unsubscribe(),this._pendingRemoval?.unsubscribe(),a instanceof rr&&this._ownsMenu(a)?(this._pendingRemoval=a._animationDone.pipe(Fn(1)).subscribe(()=>{i.detach(),ar.has(a)||a.lazyContent?.detach()}),a._setIsOpen(!1)):(i.detach(),a?.lazyContent?.detach()),a&&this._ownsMenu(a)&&ar.delete(a),this.restoreFocus&&(e==="keydown"||!this._openedBy||!this._triggersSubmenu())&&this.focus(this._openedBy),this._openedBy=void 0,this._setIsMenuOpen(!1))}_setIsMenuOpen(e){e!==this._menuOpen&&(this._menuOpen=e,this._menuOpen?this.menuOpened.emit():this.menuClosed.emit(),this._triggersSubmenu()&&this._menuItemInstance._setHighlighted(e),this._changeDetectorRef.markForCheck())}_createOverlay(e){if(!this._overlayRef){let i=this._getOverlayConfig(e);this._subscribeToPositions(e,i.positionStrategy),this._overlayRef=Ba(this._injector,i),this._overlayRef.keydownEvents().subscribe(a=>{this._menu instanceof rr&&this._menu._handleKeydown(a)})}return this._overlayRef}_getOverlayConfig(e){return new _i({positionStrategy:yl(this._injector,this._getOverlayOrigin()).withLockedPosition().withGrowAfterOpen().withTransformOriginOn(".mat-menu-panel, .mat-mdc-menu-panel"),backdropClass:e.backdropClass||"cdk-overlay-transparent-backdrop",panelClass:e.overlayPanelClass,scrollStrategy:this._scrollStrategy(),direction:this._dir||"ltr",disableAnimations:this._animationsDisabled})}_subscribeToPositions(e,i){e.setPositionClasses&&i.positionChanges.subscribe(a=>{this._ngZone.run(()=>{let r=a.connectionPair.overlayX==="start"?"after":"before",o=a.connectionPair.overlayY==="top"?"below":"above";e.setPositionClasses(r,o)})})}_setPosition(e,i){let[a,r]=e.xPosition==="before"?["end","start"]:["start","end"],[o,s]=e.yPosition==="above"?["bottom","top"]:["top","bottom"],[l,c]=[o,s],[d,m]=[a,r],u=0;if(this._triggersSubmenu()){if(m=a=e.xPosition==="before"?"start":"end",r=d=a==="end"?"start":"end",this._parentMaterialMenu){if(this._parentInnerPadding==null){let f=this._parentMaterialMenu.items.first;this._parentInnerPadding=f?f._getHostElement().offsetTop:0}u=o==="bottom"?this._parentInnerPadding:-this._parentInnerPadding}}else e.overlapTrigger||(l=o==="top"?"bottom":"top",c=s==="top"?"bottom":"top");i.withPositions([{originX:a,originY:l,overlayX:d,overlayY:o,offsetY:u},{originX:r,originY:l,overlayX:m,overlayY:o,offsetY:u},{originX:a,originY:c,overlayX:d,overlayY:s,offsetY:-u},{originX:r,originY:c,overlayX:m,overlayY:s,offsetY:-u}])}_menuClosingActions(){let e=this._getOutsideClickStream(this._overlayRef),i=this._overlayRef.detachments(),a=this._parentMaterialMenu?this._parentMaterialMenu.closed:Ue(),r=this._parentMaterialMenu?this._parentMaterialMenu._hovered().pipe(Ke(o=>this._menuOpen&&o!==this._menuItemInstance)):Ue();return It(e,a,r,i)}_getPortal(e){return(!this._portal||this._portal.templateRef!==e.templateRef)&&(this._portal=new Kn(e.templateRef,this._viewContainerRef)),this._portal}_ownsMenu(e){return ar.get(e)===this}_triggerIsAriaDisabled(){return ve(this._element.nativeElement.getAttribute("aria-disabled"))}static \u0275fac=function(i){fs()};static \u0275dir=q({type:t})}return t})(),L_=(()=>{class t extends uM{_cleanupTouchstart;_hoverSubscription=Mt.EMPTY;get _deprecatedMatMenuTriggerFor(){return this.menu}set _deprecatedMatMenuTriggerFor(e){this.menu=e}get menu(){return this._menu}set menu(e){this._menu=e}menuData;restoreFocus=!0;menuOpened=new Te;onMenuOpen=this.menuOpened;menuClosed=new Te;onMenuClose=this.menuClosed;constructor(){super(!0);let e=p(ct);this._cleanupTouchstart=e.listen(this._element.nativeElement,"touchstart",i=>{Vi(i)||(this._openedBy="touch")},{passive:!0})}triggersSubmenu(){return super._triggersSubmenu()}toggleMenu(){return this.menuOpen?this.closeMenu():this.openMenu()}openMenu(){this._openMenu(!0)}closeMenu(){this._closeMenu()}updatePosition(){this._overlayRef?.updatePosition()}ngAfterContentInit(){this._handleHover()}ngOnDestroy(){super.ngOnDestroy(),this._cleanupTouchstart(),this._hoverSubscription.unsubscribe()}_getOverlayOrigin(){return this._element}_getOutsideClickStream(e){return e.backdropClick()}_handleMousedown(e){ji(e)||(this._openedBy=e.button===0?"mouse":void 0,this.triggersSubmenu()&&e.preventDefault())}_handleKeydown(e){let i=e.keyCode;(i===13||i===32)&&(this._openedBy="keyboard"),this.triggersSubmenu()&&(i===39&&this.dir==="ltr"||i===37&&this.dir==="rtl")&&(this._openedBy="keyboard",this.openMenu())}_handleClick(e){this.triggersSubmenu()?(e.stopPropagation(),this.openMenu()):this.toggleMenu()}_handleHover(){this.triggersSubmenu()&&this._parentMaterialMenu&&(this._hoverSubscription=this._parentMaterialMenu._hovered().subscribe(e=>{e===this._menuItemInstance&&!e.disabled&&this._parentMaterialMenu?._panelAnimationState!=="void"&&(this._openedBy="mouse",this._openMenu(!1))}))}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","mat-menu-trigger-for",""],["","matMenuTriggerFor",""]],hostAttrs:[1,"mat-mdc-menu-trigger"],hostVars:3,hostBindings:function(i,a){i&1&&xe("click",function(o){return a._handleClick(o)})("mousedown",function(o){return a._handleMousedown(o)})("keydown",function(o){return a._handleKeydown(o)}),i&2&&fe("aria-haspopup",a.menu?"menu":null)("aria-expanded",a.menuOpen)("aria-controls",a.menuOpen?a.menu?.panelId:null)},inputs:{_deprecatedMatMenuTriggerFor:[0,"mat-menu-trigger-for","_deprecatedMatMenuTriggerFor"],menu:[0,"matMenuTriggerFor","menu"],menuData:[0,"matMenuTriggerData","menuData"],restoreFocus:[0,"matMenuTriggerRestoreFocus","restoreFocus"]},outputs:{menuOpened:"menuOpened",onMenuOpen:"onMenuOpen",menuClosed:"menuClosed",onMenuClose:"onMenuClose"},exportAs:["matMenuTrigger"],features:[Ve]})}return t})();var $o=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[An,qi,me,Gn]})}return t})();var fM=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["mat-toolbar-row"]],hostAttrs:[1,"mat-toolbar-row"],exportAs:["matToolbarRow"]})}return t})(),B_=(()=>{class t{_elementRef=p(X);_platform=p(Ie);_document=p(Y);color;_toolbarRows;ngAfterViewInit(){this._platform.isBrowser&&(this._checkToolbarMixedModes(),this._toolbarRows.changes.subscribe(()=>this._checkToolbarMixedModes()))}_checkToolbarMixedModes(){this._toolbarRows.length}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=["*",[["mat-toolbar-row"]]];return T({type:t,selectors:[["mat-toolbar"]],contentQueries:function(r,o,s){if(r&1&&ot(s,fM,5),r&2){let l;H(l=U())&&(o._toolbarRows=l)}},hostAttrs:[1,"mat-toolbar"],hostVars:6,hostBindings:function(r,o){r&2&&(At(o.color?"mat-"+o.color:""),ee("mat-toolbar-multiple-rows",o._toolbarRows.length>0)("mat-toolbar-single-row",o._toolbarRows.length===0))},inputs:{color:"color"},exportAs:["matToolbar"],ngContentSelectors:["*","mat-toolbar-row"],decls:2,vars:0,template:function(r,o){r&1&&(de(e),j(0),j(1,1))},styles:[`.mat-toolbar {
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
`],encapsulation:2})})()}return t})();var zo=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[me]})}return t})();var bM=new I("MAT_CARD_CONFIG"),sr=(()=>{class t{appearance;constructor(){let e=p(bM,{optional:!0});this.appearance=e?.appearance||"raised"}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){return T({type:t,selectors:[["mat-card"]],hostAttrs:[1,"mat-mdc-card","mdc-card"],hostVars:8,hostBindings:function(a,r){a&2&&ee("mat-mdc-card-outlined",r.appearance==="outlined")("mdc-card--outlined",r.appearance==="outlined")("mat-mdc-card-filled",r.appearance==="filled")("mdc-card--filled",r.appearance==="filled")},inputs:{appearance:"appearance"},exportAs:["matCard"],ngContentSelectors:["*"],decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},styles:[`.mat-mdc-card {
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
`],encapsulation:2})})()}return t})(),lr=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["mat-card-title"],["","mat-card-title",""],["","matCardTitle",""]],hostAttrs:[1,"mat-mdc-card-title"]})}return t})();var cr=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["mat-card-content"]],hostAttrs:[1,"mat-mdc-card-content"]})}return t})();var dr=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=[[["","mat-card-avatar",""],["","matCardAvatar",""]],[["mat-card-title"],["mat-card-subtitle"],["","mat-card-title",""],["","mat-card-subtitle",""],["","matCardTitle",""],["","matCardSubtitle",""]],"*"];return T({type:t,selectors:[["mat-card-header"]],hostAttrs:[1,"mat-mdc-card-header"],ngContentSelectors:["[mat-card-avatar], [matCardAvatar]",`mat-card-title, mat-card-subtitle,
      [mat-card-title], [mat-card-subtitle],
      [matCardTitle], [matCardSubtitle]`,"*"],decls:4,vars:0,consts:[[1,"mat-mdc-card-header-text"]],template:function(r,o){r&1&&(de(e),j(0),Qe(1,"div",0),j(2,1),et(),j(3,2))},encapsulation:2})})()}return t})();var ei=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[me]})}return t})();var j_=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({})}return t})();var eu=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[j_,La,me]})}return t})();var tu=class{_box;_destroyed=new z;_resizeSubject=new z;_resizeObserver;_elementObservables=new Map;constructor(n){this._box=n,typeof ResizeObserver<"u"&&(this._resizeObserver=new ResizeObserver(e=>this._resizeSubject.next(e)))}observe(n){return this._elementObservables.has(n)||this._elementObservables.set(n,new Nt(e=>{let i=this._resizeSubject.subscribe(e);return this._resizeObserver?.observe(n,{box:this._box}),()=>{this._resizeObserver?.unobserve(n),i.unsubscribe(),this._elementObservables.delete(n)}}).pipe(Ke(e=>e.some(i=>i.target===n)),Ln({bufferSize:1,refCount:!0}),Ae(this._destroyed))),this._elementObservables.get(n)}destroy(){this._destroyed.next(),this._destroyed.complete(),this._resizeSubject.complete(),this._elementObservables.clear()}},V_=(()=>{class t{_cleanupErrorListener;_observers=new Map;_ngZone=p(K);constructor(){typeof ResizeObserver<"u"}ngOnDestroy(){for(let[,e]of this._observers)e.destroy();this._observers.clear(),this._cleanupErrorListener?.()}observe(e,i){let a=i?.box||"content-box";return this._observers.has(a)||this._observers.set(a,new tu(a)),this._observers.get(a).observe(e)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var nu=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[me]})}return t})();var Ho=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[me]})}return t})();var iu=class{_document;_textarea;constructor(n,e){this._document=e;let i=this._textarea=this._document.createElement("textarea"),a=i.style;a.position="fixed",a.top=a.opacity="0",a.left="-999em",i.setAttribute("aria-hidden","true"),i.value=n,i.readOnly=!0,(this._document.fullscreenElement||this._document.body).appendChild(i)}copy(){let n=this._textarea,e=!1;try{if(n){let i=this._document.activeElement;n.select(),n.setSelectionRange(0,n.value.length),e=this._document.execCommand("copy"),i&&i.focus()}}catch{}return e}destroy(){let n=this._textarea;n&&(n.remove(),this._textarea=void 0)}},vM=(()=>{class t{_document=p(Y);copy(e){let i=this.beginCopy(e),a=i.copy();return i.destroy(),a}beginCopy(e){return new iu(e,this._document)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})(),_M=new I("CDK_COPY_TO_CLIPBOARD_CONFIG"),$_=(()=>{class t{_clipboard=p(vM);_ngZone=p(K);text="";attempts=1;copied=new Te;_pending=new Set;_destroyed=!1;_currentTimeout;constructor(){let e=p(_M,{optional:!0});e&&e.attempts!=null&&(this.attempts=e.attempts)}copy(e=this.attempts){if(e=Math.min(e,50),e>1){let i=e,a=this._clipboard.beginCopy(this.text);this._pending.add(a);let r=()=>{let o=a.copy();!o&&--i&&!this._destroyed?this._currentTimeout=this._ngZone.runOutsideAngular(()=>setTimeout(r,1)):(this._currentTimeout=null,this._pending.delete(a),a.destroy(),this.copied.emit(o))};r()}else this.copied.emit(this._clipboard.copy(this.text))}ngOnDestroy(){this._currentTimeout&&clearTimeout(this._currentTimeout),this._pending.forEach(e=>e.destroy()),this._pending.clear(),this._destroyed=!0}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","cdkCopyToClipboard",""]],hostBindings:function(i,a){i&1&&xe("click",function(){return a.copy()})},inputs:{text:[0,"cdkCopyToClipboard","text"],attempts:[0,"cdkCopyToClipboardAttempts","attempts"]},outputs:{copied:"cdkCopyToClipboardCopied"}})}return t})(),Uo=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({})}return t})();var xM=new I("",{factory:()=>z_}),z_="always";var wM=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({})}return t})();var H_=(()=>{class t{static withConfig(e){return{ngModule:t,providers:[{provide:xM,useValue:e.callSetDisabledState??z_}]}}static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[wM]})}return t})();var q_=Symbol("FIELD_TREE");var G_=Symbol("IS_ASYNC_VALIDATION_RESOURCE"),U_=class{reducer;create;brand;[G_];constructor(n,e){this.reducer=n,this.create=e}};function qo(t){return typeof t=="function"&&t[q_]===!0}var au=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["mat-label"]]})}return t})(),SM=new I("MatError");var ru=(()=>{class t{align="start";id=p(wt).getId("mat-mdc-hint-");static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["mat-hint"]],hostAttrs:[1,"mat-mdc-form-field-hint","mat-mdc-form-field-bottom-align"],hostVars:4,hostBindings:function(i,a){i&2&&(di("id",a.id),fe("align",null),ee("mat-mdc-form-field-hint-end",a.align==="end"))},inputs:{align:"align",id:"id"}})}return t})(),kM=new I("MatPrefix");var CM=new I("MatSuffix");var J_=new I("FloatingLabelParent"),W_=(()=>{class t{_elementRef=p(X);get floating(){return this._floating}set floating(e){this._floating=e,this.monitorResize&&this._handleResize()}_floating=!1;get monitorResize(){return this._monitorResize}set monitorResize(e){this._monitorResize=e,this._monitorResize?this._subscribeToResize():this._resizeSubscription.unsubscribe()}_monitorResize=!1;_resizeObserver=p(V_);_ngZone=p(K);_parent=p(J_);_resizeSubscription=new Mt;ngOnDestroy(){this._resizeSubscription.unsubscribe()}getWidth(){return EM(this._elementRef.nativeElement)}get element(){return this._elementRef.nativeElement}_handleResize(){setTimeout(()=>this._parent._handleLabelResized())}_subscribeToResize(){this._resizeSubscription.unsubscribe(),this._ngZone.runOutsideAngular(()=>{this._resizeSubscription=this._resizeObserver.observe(this._elementRef.nativeElement,{box:"border-box"}).subscribe(()=>this._handleResize())})}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["label","matFormFieldFloatingLabel",""]],hostAttrs:[1,"mdc-floating-label","mat-mdc-floating-label"],hostVars:2,hostBindings:function(i,a){i&2&&ee("mdc-floating-label--float-above",a.floating)},inputs:{floating:"floating",monitorResize:"monitorResize"}})}return t})();function EM(t){let n=t;if(n.offsetParent!==null)return n.scrollWidth;let e=n.cloneNode(!0);e.style.setProperty("position","absolute"),e.style.setProperty("transform","translate(-9999px, -9999px)"),document.documentElement.appendChild(e);let i=e.scrollWidth;return e.remove(),i}var K_="mdc-line-ripple--active",pc="mdc-line-ripple--deactivating",Y_=(()=>{class t{_elementRef=p(X);_cleanupTransitionEnd;constructor(){let e=p(K),i=p(ct);e.runOutsideAngular(()=>{this._cleanupTransitionEnd=i.listen(this._elementRef.nativeElement,"transitionend",this._handleTransitionEnd)})}activate(){let e=this._elementRef.nativeElement.classList;e.remove(pc),e.add(K_)}deactivate(){this._elementRef.nativeElement.classList.add(pc)}_handleTransitionEnd=e=>{let i=this._elementRef.nativeElement.classList,a=i.contains(pc);e.propertyName==="opacity"&&a&&i.remove(K_,pc)};ngOnDestroy(){this._cleanupTransitionEnd()}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["div","matFormFieldLineRipple",""]],hostAttrs:[1,"mdc-line-ripple"]})}return t})(),X_=(()=>{class t{_elementRef=p(X);_ngZone=p(K);open=!1;_notch;ngAfterViewInit(){let e=this._elementRef.nativeElement,i=e.querySelector(".mdc-floating-label");i?(e.classList.add("mdc-notched-outline--upgraded"),typeof requestAnimationFrame=="function"&&(i.style.transitionDuration="0s",this._ngZone.runOutsideAngular(()=>{requestAnimationFrame(()=>i.style.transitionDuration="")}))):e.classList.add("mdc-notched-outline--no-label")}_setNotchWidth(e){let i=this._notch.nativeElement;!this.open||!e?i.style.width="":i.style.width=`calc(${e}px * var(--mat-mdc-form-field-floating-label-scale, 0.75) + 9px)`}_setMaxWidth(e){this._notch.nativeElement.style.setProperty("--mat-form-field-notch-max-width",`calc(100% - ${e}px)`)}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=["notch"];return T({type:t,selectors:[["div","matFormFieldNotchedOutline",""]],viewQuery:function(r,o){if(r&1&&qe(e,5),r&2){let s;H(s=U())&&(o._notch=s.first)}},hostAttrs:[1,"mdc-notched-outline"],hostVars:2,hostBindings:function(r,o){r&2&&ee("mdc-notched-outline--notched",o.open)},inputs:{open:[0,"matFormFieldNotchedOutlineOpen","open"]},ngContentSelectors:["*"],decls:5,vars:0,consts:[["notch",""],[1,"mat-mdc-notch-piece","mdc-notched-outline__leading"],[1,"mat-mdc-notch-piece","mdc-notched-outline__notch"],[1,"mat-mdc-notch-piece","mdc-notched-outline__trailing"]],template:function(r,o){r&1&&(de(),ln(0,"div",1),Qe(1,"div",2,0),j(3),et(),ln(4,"div",3))},encapsulation:2})})()}return t})(),DM=(()=>{class t{id;ngField=null;ngControl=null;focused=!1;empty=!1;shouldLabelFloat=!1;required=!1;disabled=!1;errorState=!1;controlType;autofilled;userAriaDescribedBy;disableAutomaticLabeling;describedByIds;stateChanges=null;value;static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t})}return t})();var MM=new I("MatFormField"),NM=new I("MAT_FORM_FIELD_DEFAULT_OPTIONS"),Z_="fill",IM="auto",Q_="fixed",AM="translateY(-50%)",ey=(()=>{class t{_elementRef=p(X);_changeDetectorRef=p(We);_platform=p(Ie);_idGenerator=p(wt);_ngZone=p(K);_defaults=p(NM,{optional:!0});_currentDirection;_unwrapMaybeSignal(e){return oi(e)?e():e}_textField;_iconPrefixContainer;_textPrefixContainer;_iconSuffixContainer;_textSuffixContainer;_floatingLabel;_notchedOutline;_lineRipple;_iconPrefixContainerSignal=Pr("iconPrefixContainer");_textPrefixContainerSignal=Pr("textPrefixContainer");_iconSuffixContainerSignal=Pr("iconSuffixContainer");_textSuffixContainerSignal=Pr("textSuffixContainer");_prefixSuffixContainers=cn(()=>[this._iconPrefixContainerSignal(),this._textPrefixContainerSignal(),this._iconSuffixContainerSignal(),this._textSuffixContainerSignal()].map(e=>e?.nativeElement).filter(e=>e!==void 0));_formFieldControl;_prefixChildren;_suffixChildren;_errorChildren;_hintChildren;_labelChild=wh(au);get hideRequiredMarker(){return this._hideRequiredMarker}set hideRequiredMarker(e){this._hideRequiredMarker=Rt(e)}_hideRequiredMarker=!1;color="primary";get floatLabel(){return this._floatLabel||this._defaults?.floatLabel||IM}set floatLabel(e){e!==this._floatLabel&&(this._floatLabel=e,this._changeDetectorRef.markForCheck())}_floatLabel;get appearance(){return this._appearanceSignal()}set appearance(e){let i=e||this._defaults?.appearance||Z_;this._appearanceSignal.set(i)}_appearanceSignal=ne(Z_);get subscriptSizing(){return this._subscriptSizing||this._defaults?.subscriptSizing||Q_}set subscriptSizing(e){this._subscriptSizing=e||this._defaults?.subscriptSizing||Q_}_subscriptSizing=null;get hintLabel(){return this._hintLabel}set hintLabel(e){this._hintLabel=e,this._processHints()}_hintLabel="";_hasIconPrefix=!1;_hasTextPrefix=!1;_hasIconSuffix=!1;_hasTextSuffix=!1;_labelId=this._idGenerator.getId("mat-mdc-form-field-label-");_hintLabelId=this._idGenerator.getId("mat-mdc-hint-");_describedByIds;get _control(){return this._explicitFormFieldControl||this._formFieldControl}set _control(e){this._explicitFormFieldControl=e}_destroyed=new z;_isFocused=null;_explicitFormFieldControl;_previousControl=null;_previousControlValidatorFn=null;_stateChanges;_valueChanges;_describedByChanges;_outlineLabelOffsetResizeObserver=null;_animationsDisabled=tt();constructor(){let e=this._defaults,i=p(Tt);e&&(e.appearance&&(this.appearance=e.appearance),this._hideRequiredMarker=!!e?.hideRequiredMarker,e.color&&(this.color=e.color)),Ti(()=>this._currentDirection=i.valueSignal()),this._syncOutlineLabelOffset()}ngAfterViewInit(){this._updateFocusState(),this._animationsDisabled||this._ngZone.runOutsideAngular(()=>{setTimeout(()=>{this._elementRef.nativeElement.classList.add("mat-form-field-animations-enabled")},300)}),this._changeDetectorRef.detectChanges()}ngAfterContentInit(){this._assertFormFieldControl(),this._initializeSubscript(),this._initializePrefixAndSuffix()}ngAfterContentChecked(){this._assertFormFieldControl(),this._control!==this._previousControl&&(this._initializeControl(this._previousControl),this._control.ngControl&&this._control.ngControl.control&&(this._previousControlValidatorFn=qo(this._control.ngField)?null:this._control.ngControl.control.validator),this._previousControl=this._control,this._changeDetectorRef.markForCheck()),this._control.ngControl&&this._control.ngControl.control&&!qo(this._control.ngField)&&this._control.ngControl.control.validator!==this._previousControlValidatorFn&&this._changeDetectorRef.markForCheck()}ngOnDestroy(){this._outlineLabelOffsetResizeObserver?.disconnect(),this._stateChanges?.unsubscribe(),this._valueChanges?.unsubscribe(),this._describedByChanges?.unsubscribe(),this._destroyed.next(),this._destroyed.complete()}getLabelId=cn(()=>this._hasFloatingLabel()?this._labelId:null);getConnectedOverlayOrigin(){return this._textField||this._elementRef}_animateAndLockLabel(){this._hasFloatingLabel()&&(this.floatLabel="always")}_initializeControl(e){let i=this._control,a="mat-mdc-form-field-type-";e&&this._elementRef.nativeElement.classList.remove(a+e.controlType),i.controlType&&this._elementRef.nativeElement.classList.add(a+i.controlType),this._stateChanges?.unsubscribe(),this._stateChanges=i.stateChanges?.subscribe(()=>{this._updateFocusState(),this._changeDetectorRef.markForCheck()}),this._describedByChanges?.unsubscribe(),this._describedByChanges=i.stateChanges?.pipe(_t([void 0,void 0]),Me(()=>[this._unwrapMaybeSignal(i.errorState),i.userAriaDescribedBy]),Hc(),Ke(([[r,o],[s,l]])=>r!==s||o!==l)).subscribe(()=>this._syncDescribedByIds()),this._valueChanges?.unsubscribe(),i.ngControl&&i.ngControl.valueChanges&&!qo(i.ngField)&&(this._valueChanges=i.ngControl.valueChanges.pipe(Ae(this._destroyed)).subscribe(()=>this._changeDetectorRef.markForCheck()))}_checkPrefixAndSuffixTypes(){this._hasIconPrefix=!!this._prefixChildren.find(e=>!e._isText),this._hasTextPrefix=!!this._prefixChildren.find(e=>e._isText),this._hasIconSuffix=!!this._suffixChildren.find(e=>!e._isText),this._hasTextSuffix=!!this._suffixChildren.find(e=>e._isText)}_initializePrefixAndSuffix(){this._checkPrefixAndSuffixTypes(),It(this._prefixChildren.changes,this._suffixChildren.changes).subscribe(()=>{this._checkPrefixAndSuffixTypes(),this._changeDetectorRef.markForCheck()})}_initializeSubscript(){this._hintChildren.changes.subscribe(()=>{this._processHints(),this._changeDetectorRef.markForCheck()}),this._errorChildren.changes.subscribe(()=>{this._syncDescribedByIds(),this._changeDetectorRef.markForCheck()}),this._validateHints(),this._syncDescribedByIds()}_assertFormFieldControl(){this._control}_updateFocusState(){let e=this._unwrapMaybeSignal(this._control.focused);e&&!this._isFocused?(this._isFocused=!0,this._lineRipple?.activate()):!e&&(this._isFocused||this._isFocused===null)&&(this._isFocused=!1,this._lineRipple?.deactivate()),this._elementRef.nativeElement.classList.toggle("mat-focused",e),this._textField?.nativeElement.classList.toggle("mdc-text-field--focused",e)}_syncOutlineLabelOffset(){Ch({earlyRead:()=>{if(this._appearanceSignal()!=="outline")return this._outlineLabelOffsetResizeObserver?.disconnect(),null;if(globalThis.ResizeObserver){this._outlineLabelOffsetResizeObserver||=new globalThis.ResizeObserver(()=>{this._writeOutlinedLabelStyles(this._getOutlinedLabelOffset())});for(let e of this._prefixSuffixContainers())this._outlineLabelOffsetResizeObserver.observe(e,{box:"border-box"})}return this._getOutlinedLabelOffset()},write:e=>this._writeOutlinedLabelStyles(e())})}_shouldAlwaysFloat(){return this.floatLabel==="always"}_hasOutline(){return this.appearance==="outline"}_forceDisplayInfixLabel(){return!this._platform.isBrowser&&this._prefixChildren.length&&!this._shouldLabelFloat()}_hasFloatingLabel=cn(()=>!!this._labelChild());_shouldLabelFloat(){return this._hasFloatingLabel()?this._shouldAlwaysFloat()||this._unwrapMaybeSignal(this._control.shouldLabelFloat):!1}_shouldForward(e){let i=this._control?.ngField||this._control?.ngControl;if(!i)return!1;if(qo(i)){let a=i();return e==="valid"?a.valid():e==="dirty"?a.dirty():e==="touched"?a.touched():e==="pending"?a.pending():e==="untouched"?!a.touched():e==="pristine"?!a.dirty():e==="invalid"?!a.valid():!1}else{let a=i;return e==="valid"?a.valid:e==="dirty"?a.dirty:e==="touched"?a.touched:e==="pending"?a.pending:e==="untouched"?a.untouched:e==="pristine"?a.pristine:e==="invalid"?a.invalid:!1}}_getSubscriptMessageType(){return this._errorChildren&&this._errorChildren.length>0&&this._unwrapMaybeSignal(this._control.errorState)?"error":"hint"}_handleLabelResized(){this._refreshOutlineNotchWidth()}_refreshOutlineNotchWidth(){!this._hasOutline()||!this._floatingLabel||!this._shouldLabelFloat()?this._notchedOutline?._setNotchWidth(0):this._notchedOutline?._setNotchWidth(this._floatingLabel.getWidth())}_processHints(){this._validateHints(),this._syncDescribedByIds()}_validateHints(){this._hintChildren}_syncDescribedByIds(){if(this._control){let e=[];if(this._control.userAriaDescribedBy&&typeof this._control.userAriaDescribedBy=="string"&&e.push(...this._control.userAriaDescribedBy.split(" ")),this._getSubscriptMessageType()==="hint"){let r=this._hintChildren?this._hintChildren.find(s=>s.align==="start"):null,o=this._hintChildren?this._hintChildren.find(s=>s.align==="end"):null;r?e.push(r.id):this._hintLabel&&e.push(this._hintLabelId),o&&e.push(o.id)}else this._errorChildren&&e.push(...this._errorChildren.map(r=>r.id));let i=this._control.describedByIds,a;if(i){let r=this._describedByIds||e;a=e.concat(i.filter(o=>o&&!r.includes(o)))}else a=e;this._control.setDescribedByIds(a),this._describedByIds=e}}_getOutlinedLabelOffset(){if(!this._hasOutline()||!this._floatingLabel)return null;if(!this._iconPrefixContainer&&!this._textPrefixContainer)return["",null];if(!this._isAttachedToDom())return null;let e=this._iconPrefixContainer?.nativeElement,i=this._textPrefixContainer?.nativeElement,a=this._iconSuffixContainer?.nativeElement,r=this._textSuffixContainer?.nativeElement,o=e?.getBoundingClientRect().width??0,s=i?.getBoundingClientRect().width??0,l=a?.getBoundingClientRect().width??0,c=r?.getBoundingClientRect().width??0,d=this._currentDirection==="rtl"?"-1":"1",m=`${o+s}px`,f=`calc(${d} * (${m} + var(--mat-mdc-form-field-label-offset-x, 0px)))`,g=`var(--mat-mdc-form-field-label-transform, ${AM} translateX(${f}))`,b=o+s+l+c;return[g,b]}_writeOutlinedLabelStyles(e){if(e!==null){let[i,a]=e;this._floatingLabel&&(this._floatingLabel.element.style.transform=i),a!==null&&this._notchedOutline?._setMaxWidth(a)}}_isAttachedToDom(){let e=this._elementRef.nativeElement;if(e.getRootNode){let i=e.getRootNode();return i&&i!==e}return document.documentElement.contains(e)}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=["iconPrefixContainer"],i=["textPrefixContainer"],a=["iconSuffixContainer"],r=["textSuffixContainer"],o=["textField"],s=["*",[["mat-label"]],[["","matPrefix",""],["","matIconPrefix",""]],[["","matTextPrefix",""]],[["","matTextSuffix",""]],[["","matSuffix",""],["","matIconSuffix",""]],[["mat-error"],["","matError",""]],[["mat-hint",3,"align","end"]],[["mat-hint","align","end"]]],l=["*","mat-label","[matPrefix], [matIconPrefix]","[matTextPrefix]","[matTextSuffix]","[matSuffix], [matIconSuffix]","mat-error, [matError]","mat-hint:not([align='end'])","mat-hint[align='end']"];function c(G,oe){G&1&&R(0,"span",21)}function d(G,oe){if(G&1&&(y(0,"label",20),j(1,1),E(2,c,1,0,"span",21),v()),G&2){let k=M(2);P("floating",k._shouldLabelFloat())("monitorResize",k._hasOutline())("id",k._labelId),fe("for",k._control.disableAutomaticLabeling?null:k._control.id),h(2),D(!k.hideRequiredMarker&&k._unwrapMaybeSignal(k._control.required)?2:-1)}}function m(G,oe){if(G&1&&E(0,d,3,5,"label",20),G&2){let k=M();D(k._hasFloatingLabel()?0:-1)}}function u(G,oe){G&1&&R(0,"div",7)}function f(G,oe){}function g(G,oe){if(G&1&&Wt(0,f,0,0,"ng-template",13),G&2){M(2);let k=Kt(1);P("ngTemplateOutlet",k)}}function b(G,oe){if(G&1&&(y(0,"div",9),E(1,g,1,1,null,13),v()),G&2){let k=M();P("matFormFieldNotchedOutlineOpen",k._shouldLabelFloat()),h(),D(k._forceDisplayInfixLabel()?-1:1)}}function _(G,oe){G&1&&(y(0,"div",10,2),j(2,2),v())}function x(G,oe){G&1&&(y(0,"div",11,3),j(2,3),v())}function w(G,oe){}function C(G,oe){if(G&1&&Wt(0,w,0,0,"ng-template",13),G&2){M();let k=Kt(1);P("ngTemplateOutlet",k)}}function F(G,oe){G&1&&(y(0,"div",14,4),j(2,4),v())}function A(G,oe){G&1&&(y(0,"div",15,5),j(2,5),v())}function Q(G,oe){G&1&&R(0,"div",16)}function Re(G,oe){G&1&&(y(0,"div",18),j(1,6),v())}function He(G,oe){if(G&1&&(y(0,"mat-hint",22),S(1),v()),G&2){let k=M(2);P("id",k._hintLabelId),h(),Ge(k.hintLabel)}}function Be(G,oe){if(G&1&&(y(0,"div",19),E(1,He,2,2,"mat-hint",22),j(2,7),R(3,"div",23),j(4,8),v()),G&2){let k=M();h(),D(k.hintLabel?1:-1)}}return T({type:t,selectors:[["mat-form-field"]],contentQueries:function(oe,k,ce){if(oe&1&&(fh(ce,k._labelChild,au,5),ot(ce,DM,5)(ce,kM,5)(ce,CM,5)(ce,SM,5)(ce,ru,5)),oe&2){Kc();let je;H(je=U())&&(k._formFieldControl=je.first),H(je=U())&&(k._prefixChildren=je),H(je=U())&&(k._suffixChildren=je),H(je=U())&&(k._errorChildren=je),H(je=U())&&(k._hintChildren=je)}},viewQuery:function(oe,k){if(oe&1&&(gh(k._iconPrefixContainerSignal,e,5)(k._textPrefixContainerSignal,i,5)(k._iconSuffixContainerSignal,a,5)(k._textSuffixContainerSignal,r,5),qe(o,5)(e,5)(i,5)(a,5)(r,5)(W_,5)(X_,5)(Y_,5)),oe&2){Kc(4);let ce;H(ce=U())&&(k._textField=ce.first),H(ce=U())&&(k._iconPrefixContainer=ce.first),H(ce=U())&&(k._textPrefixContainer=ce.first),H(ce=U())&&(k._iconSuffixContainer=ce.first),H(ce=U())&&(k._textSuffixContainer=ce.first),H(ce=U())&&(k._floatingLabel=ce.first),H(ce=U())&&(k._notchedOutline=ce.first),H(ce=U())&&(k._lineRipple=ce.first)}},hostAttrs:[1,"mat-mdc-form-field"],hostVars:38,hostBindings:function(oe,k){oe&2&&ee("mat-mdc-form-field-label-always-float",k._shouldAlwaysFloat())("mat-mdc-form-field-has-icon-prefix",k._hasIconPrefix)("mat-mdc-form-field-has-icon-suffix",k._hasIconSuffix)("mat-form-field-invalid",k._unwrapMaybeSignal(k._control.errorState))("mat-form-field-disabled",k._unwrapMaybeSignal(k._control.disabled))("mat-form-field-autofilled",k._unwrapMaybeSignal(k._control.autofilled))("mat-form-field-appearance-fill",k.appearance=="fill")("mat-form-field-appearance-outline",k.appearance=="outline")("mat-form-field-hide-placeholder",k._hasFloatingLabel()&&!k._shouldLabelFloat())("mat-primary",k.color!=="accent"&&k.color!=="warn")("mat-accent",k.color==="accent")("mat-warn",k.color==="warn")("ng-untouched",k._shouldForward("untouched"))("ng-touched",k._shouldForward("touched"))("ng-pristine",k._shouldForward("pristine"))("ng-dirty",k._shouldForward("dirty"))("ng-valid",k._shouldForward("valid"))("ng-invalid",k._shouldForward("invalid"))("ng-pending",k._shouldForward("pending"))},inputs:{hideRequiredMarker:"hideRequiredMarker",color:"color",floatLabel:"floatLabel",appearance:"appearance",subscriptSizing:"subscriptSizing",hintLabel:"hintLabel"},exportAs:["matFormField"],features:[Je([{provide:MM,useExisting:t},{provide:J_,useExisting:t}])],ngContentSelectors:l,decls:18,vars:21,consts:[["labelTemplate",""],["textField",""],["iconPrefixContainer",""],["textPrefixContainer",""],["textSuffixContainer",""],["iconSuffixContainer",""],[1,"mat-mdc-text-field-wrapper","mdc-text-field",3,"click"],[1,"mat-mdc-form-field-focus-overlay"],[1,"mat-mdc-form-field-flex"],["matFormFieldNotchedOutline","",3,"matFormFieldNotchedOutlineOpen"],[1,"mat-mdc-form-field-icon-prefix"],[1,"mat-mdc-form-field-text-prefix"],[1,"mat-mdc-form-field-infix"],[3,"ngTemplateOutlet"],[1,"mat-mdc-form-field-text-suffix"],[1,"mat-mdc-form-field-icon-suffix"],["matFormFieldLineRipple",""],["aria-atomic","true","aria-live","polite",1,"mat-mdc-form-field-subscript-wrapper","mat-mdc-form-field-bottom-align"],[1,"mat-mdc-form-field-error-wrapper"],[1,"mat-mdc-form-field-hint-wrapper"],["matFormFieldFloatingLabel","",3,"floating","monitorResize","id"],["aria-hidden","true",1,"mat-mdc-form-field-required-marker","mdc-floating-label--required"],[3,"id"],[1,"mat-mdc-form-field-hint-spacer"]],template:function(oe,k){if(oe&1&&(de(s),Wt(0,m,1,1,"ng-template",null,0,Rr),y(2,"div",6,1),xe("click",function(je){return k._control.onContainerClick(je)}),E(4,u,1,0,"div",7),y(5,"div",8),E(6,b,2,2,"div",9),E(7,_,3,0,"div",10),E(8,x,3,0,"div",11),y(9,"div",12),E(10,C,1,1,null,13),j(11),v(),E(12,F,3,0,"div",14),E(13,A,3,0,"div",15),v(),E(14,Q,1,0,"div",16),v(),y(15,"div",17),E(16,Re,2,0,"div",18)(17,Be,5,1,"div",19),v()),oe&2){let ce,je=k._unwrapMaybeSignal(k._control.disabled);h(2),ee("mdc-text-field--filled",!k._hasOutline())("mdc-text-field--outlined",k._hasOutline())("mdc-text-field--no-label",!k._hasFloatingLabel())("mdc-text-field--disabled",je)("mdc-text-field--invalid",k._unwrapMaybeSignal(k._control.errorState)),h(2),D(!k._hasOutline()&&!je?4:-1),h(2),D(k._hasOutline()?6:-1),h(),D(k._hasIconPrefix?7:-1),h(),D(k._hasTextPrefix?8:-1),h(2),D(!k._hasOutline()||k._forceDisplayInfixLabel()?10:-1),h(2),D(k._hasTextSuffix?12:-1),h(),D(k._hasIconSuffix?13:-1),h(),D(k._hasOutline()?-1:14),h(),ee("mat-mdc-form-field-subscript-dynamic-size",k.subscriptSizing==="dynamic");let le=k._getSubscriptMessageType();h(),D((ce=le)==="error"?16:ce==="hint"?17:-1)}},dependencies:[W_,X_,Br,Y_,ru],styles:[`.mdc-text-field {
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
`],encapsulation:2})})()}return t})();var Go=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[al,ey,me]})}return t})();var ty=(()=>{class t{_animationsDisabled=tt();state="unchecked";disabled=!1;appearance="full";static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-pseudo-checkbox"]],hostAttrs:[1,"mat-pseudo-checkbox"],hostVars:12,hostBindings:function(i,a){i&2&&ee("mat-pseudo-checkbox-indeterminate",a.state==="indeterminate")("mat-pseudo-checkbox-checked",a.state==="checked")("mat-pseudo-checkbox-disabled",a.disabled)("mat-pseudo-checkbox-minimal",a.appearance==="minimal")("mat-pseudo-checkbox-full",a.appearance==="full")("_mat-animation-noopable",a._animationsDisabled)},inputs:{state:"state",disabled:"disabled",appearance:"appearance"},decls:0,vars:0,template:function(i,a){},styles:[`.mat-pseudo-checkbox {
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
`],encapsulation:2})}return t})();var TM=new I("MAT_OPTION_PARENT_COMPONENT"),OM=new I("MatOptgroup");var ou=class{source;isUserInput;constructor(n,e=!1){this.source=n,this.isUserInput=e}},ny=(()=>{class t{_element=p(X);_changeDetectorRef=p(We);_parent=p(TM,{optional:!0});group=p(OM,{optional:!0});_signalDisableRipple=!1;_selected=!1;_active=!1;_mostRecentViewValue="";get multiple(){return this._parent&&this._parent.multiple}get selected(){return this._selected}value;id=p(wt).getId("mat-option-");get disabled(){return this.group&&this.group.disabled||this._disabled()}set disabled(e){this._disabled.set(e)}_disabled=ne(!1);get disableRipple(){return this._signalDisableRipple?this._parent.disableRipple():!!this._parent?.disableRipple}get hideSingleSelectionIndicator(){return!!(this._parent&&this._parent.hideSingleSelectionIndicator)}onSelectionChange=new Te;_text;_stateChanges=new z;constructor(){let e=p(dt);e.load(hn),e.load(Na),this._signalDisableRipple=!!this._parent&&oi(this._parent.disableRipple)}get active(){return this._active}get viewValue(){return(this._text?.nativeElement.textContent||"").trim()}select(e=!0){this._selected||(this._selected=!0,this._changeDetectorRef.markForCheck(),e&&this._emitSelectionChangeEvent())}deselect(e=!0){this._selected&&(this._selected=!1,this._changeDetectorRef.markForCheck(),e&&this._emitSelectionChangeEvent())}focus(e,i){let a=this._getHostElement();typeof a.focus=="function"&&a.focus(i)}setActiveStyles(){this._active||(this._active=!0,this._changeDetectorRef.markForCheck())}setInactiveStyles(){this._active&&(this._active=!1,this._changeDetectorRef.markForCheck())}getLabel(){return this.viewValue}_handleKeydown(e){(e.keyCode===13||e.keyCode===32)&&!vi(e)&&(this._selectViaInteraction(),e.preventDefault())}_selectViaInteraction(){this.disabled||(this._selected=this.multiple?!this._selected:!0,this._changeDetectorRef.markForCheck(),this._emitSelectionChangeEvent(!0))}_getTabIndex(){return this.disabled?"-1":"0"}_getHostElement(){return this._element.nativeElement}ngAfterViewChecked(){if(this._selected){let e=this.viewValue;e!==this._mostRecentViewValue&&(this._mostRecentViewValue&&this._stateChanges.next(),this._mostRecentViewValue=e)}}ngOnDestroy(){this._stateChanges.complete()}_emitSelectionChangeEvent(e=!1){this.onSelectionChange.emit(new ou(this,e))}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=["text"],i=[[["mat-icon"]],"*"],a=["mat-icon","*"];function r(l,c){if(l&1&&R(0,"mat-pseudo-checkbox",1),l&2){let d=M();P("disabled",d.disabled)("state",d.selected?"checked":"unchecked")}}function o(l,c){if(l&1&&R(0,"mat-pseudo-checkbox",3),l&2){let d=M();P("disabled",d.disabled)}}function s(l,c){if(l&1&&(y(0,"span",4),S(1),v()),l&2){let d=M();h(),ae("(",d.group.label,")")}}return T({type:t,selectors:[["mat-option"]],viewQuery:function(c,d){if(c&1&&qe(e,7),c&2){let m;H(m=U())&&(d._text=m.first)}},hostAttrs:["role","option",1,"mat-mdc-option","mdc-list-item"],hostVars:11,hostBindings:function(c,d){c&1&&xe("click",function(){return d._selectViaInteraction()})("keydown",function(u){return d._handleKeydown(u)}),c&2&&(di("id",d.id),fe("aria-selected",d.selected)("aria-disabled",d.disabled.toString()),ee("mdc-list-item--selected",d.selected)("mat-mdc-option-multiple",d.multiple)("mat-mdc-option-active",d.active)("mdc-list-item--disabled",d.disabled))},inputs:{value:"value",id:"id",disabled:[2,"disabled","disabled",ve]},outputs:{onSelectionChange:"onSelectionChange"},exportAs:["matOption"],ngContentSelectors:a,decls:8,vars:5,consts:[["text",""],["aria-hidden","true",1,"mat-mdc-option-pseudo-checkbox",3,"disabled","state"],[1,"mdc-list-item__primary-text"],["state","checked","aria-hidden","true","appearance","minimal",1,"mat-mdc-option-pseudo-checkbox",3,"disabled"],[1,"cdk-visually-hidden"],["aria-hidden","true","mat-ripple","",1,"mat-mdc-option-ripple","mat-focus-indicator",3,"matRippleTrigger","matRippleDisabled"]],template:function(c,d){c&1&&(de(i),E(0,r,1,2,"mat-pseudo-checkbox",1),j(1),y(2,"span",2,0),j(4,1),v(),E(5,o,1,1,"mat-pseudo-checkbox",3),E(6,s,2,1,"span",4),R(7,"div",5)),c&2&&(D(d.multiple?0:-1),h(5),D(!d.multiple&&d.selected&&!d.hideSingleSelectionIndicator?5:-1),h(),D(d.group&&d.group._inert?6:-1),h(),P("matRippleTrigger",d._getHostElement())("matRippleDisabled",d.disabled||d.disableRipple))},dependencies:[ty,wl],styles:[`.mat-mdc-option {
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
`],encapsulation:2})})()}return t})();var iy=(()=>{class t{isErrorState(e,i){return!!(e&&e.invalid&&(e.touched||i&&i.submitted))}isSignalErrorState(e){if(!e)return!1;let i=e().invalid(),a=e().touched();return i&&a}static \u0275fac=function(i){return new(i||t)};static \u0275prov=J({token:t,factory:t.\u0275fac})}return t})();var uc=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[me]})}return t})();var su=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[An,uc,ny,me]})}return t})();var Wo=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[qi,su,me,Gn,Go,su]})}return t})();var RM=new I("mat-chips-default-options",{providedIn:"root",factory:()=>({separatorKeyCodes:[13]})}),lu=new I("MatChipAvatar"),ay=new I("MatChipTrailingIcon"),ry=new I("MatChipEdit"),oy=new I("MatChipRemove"),sy=new I("MatChip"),ly=(()=>{class t{_elementRef=p(X);_parentChip=p(sy);_isPrimary=!0;_isLeading=!1;get disabled(){return this._disabled||this._parentChip?.disabled||!1}set disabled(e){this._disabled=e}_disabled=!1;tabIndex=-1;_allowFocusWhenDisabled=!1;_getDisabledAttribute(){return this.disabled&&!this._allowFocusWhenDisabled?"":null}constructor(){p(dt).load(hn),this._elementRef.nativeElement.nodeName==="BUTTON"&&this._elementRef.nativeElement.setAttribute("type","button")}focus(){this._elementRef.nativeElement.focus()}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","matChipContent",""]],hostAttrs:[1,"mat-mdc-chip-action","mdc-evolution-chip__action","mdc-evolution-chip__action--presentational"],hostVars:8,hostBindings:function(i,a){i&2&&(fe("disabled",a._getDisabledAttribute())("aria-disabled",a.disabled),ee("mdc-evolution-chip__action--primary",a._isPrimary)("mdc-evolution-chip__action--secondary",!a._isPrimary)("mdc-evolution-chip__action--trailing",!a._isPrimary&&!a._isLeading))},inputs:{disabled:[2,"disabled","disabled",ve],tabIndex:[2,"tabIndex","tabIndex",e=>e==null?-1:pi(e)],_allowFocusWhenDisabled:"_allowFocusWhenDisabled"}})}return t})(),PM=(()=>{class t extends ly{_getTabindex(){return this.disabled&&!this._allowFocusWhenDisabled?null:this.tabIndex.toString()}_handleClick(e){!this.disabled&&this._isPrimary&&(e.preventDefault(),this._parentChip._handlePrimaryActionInteraction())}_handleKeydown(e){(e.keyCode===13||e.keyCode===32)&&!this.disabled&&this._isPrimary&&!this._parentChip._isEditing&&(e.preventDefault(),this._parentChip._handlePrimaryActionInteraction())}static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(t)))(a||t)}})();static \u0275dir=q({type:t,selectors:[["","matChipAction",""]],hostVars:3,hostBindings:function(i,a){i&1&&xe("click",function(o){return a._handleClick(o)})("keydown",function(o){return a._handleKeydown(o)}),i&2&&(fe("tabindex",a._getTabindex()),ee("mdc-evolution-chip__action--presentational",!1))},features:[Ve]})}return t})(),Mi=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["mat-chip-avatar"],["","matChipAvatar",""]],hostAttrs:["role","img",1,"mat-mdc-chip-avatar","mdc-evolution-chip__icon","mdc-evolution-chip__icon--primary"],features:[Je([{provide:lu,useExisting:t}])]})}return t})();var ti=(()=>{class t{_changeDetectorRef=p(We);_elementRef=p(X);_tagName=p(yh);_ngZone=p(K);_focusMonitor=p(In);_globalRippleOptions=p(Wi,{optional:!0});_document=p(Y);_onFocus=new z;_onBlur=new z;_isBasicChip=!1;role=null;_hasFocusInternal=!1;_pendingFocus=!1;_actionChanges;_animationsDisabled=tt();_allLeadingIcons;_allTrailingIcons;_allEditIcons;_allRemoveIcons;_hasFocus(){return this._hasFocusInternal}id=p(wt).getId("mat-mdc-chip-");ariaLabel=null;ariaDescription=null;_chipListDisabled=!1;_hadFocusOnRemove=!1;_textElement;get value(){return this._value!==void 0?this._value:this._textElement.textContent.trim()}set value(e){this._value=e}_value;color;removable=!0;highlighted=!1;disableRipple=!1;get disabled(){return this._disabled||this._chipListDisabled}set disabled(e){this._disabled=e}_disabled=!1;removed=new Te;destroyed=new Te;basicChipAttrName="mat-basic-chip";leadingIcon;editIcon;trailingIcon;removeIcon;primaryAction;_rippleLoader=p(kl);_injector=p(re);constructor(){let e=p(dt);e.load(hn),e.load(Na),this._monitorFocus(),this._rippleLoader?.configureRipple(this._elementRef.nativeElement,{className:"mat-mdc-chip-ripple",disabled:this._isRippleDisabled()})}ngOnInit(){this._isBasicChip=this._elementRef.nativeElement.hasAttribute(this.basicChipAttrName)||this._tagName.toLowerCase()===this.basicChipAttrName}ngAfterViewInit(){this._textElement=this._elementRef.nativeElement.querySelector(".mat-mdc-chip-action-label"),this._pendingFocus&&(this._pendingFocus=!1,this.focus())}ngAfterContentInit(){this._actionChanges=It(this._allLeadingIcons.changes,this._allTrailingIcons.changes,this._allEditIcons.changes,this._allRemoveIcons.changes).subscribe(()=>this._changeDetectorRef.markForCheck())}ngDoCheck(){this._rippleLoader.setDisabled(this._elementRef.nativeElement,this._isRippleDisabled())}ngOnDestroy(){this.destroyed.emit({chip:this}),this.destroyed.complete(),this._focusMonitor.stopMonitoring(this._elementRef),this._rippleLoader?.destroyRipple(this._elementRef.nativeElement),this._actionChanges?.unsubscribe()}remove(){this.removable&&(this._hadFocusOnRemove=this._hasFocus(),this.removed.emit({chip:this}))}_isRippleDisabled(){return this.disabled||this.disableRipple||this._animationsDisabled||this._isBasicChip||!this._hasInteractiveActions()||!!this._globalRippleOptions?.disabled}_hasTrailingIcon(){return!!(this.trailingIcon||this.removeIcon)}_handleKeydown(e){(e.keyCode===8&&!e.repeat||e.keyCode===46)&&(e.preventDefault(),this.remove())}focus(){this.disabled||(this.primaryAction?this.primaryAction.focus():this._pendingFocus=!0)}_getSourceAction(e){return this._getActions().find(i=>{let a=i._elementRef.nativeElement;return a===e||a.contains(e)})}_getActions(){let e=[];return this.editIcon&&e.push(this.editIcon),this.primaryAction&&e.push(this.primaryAction),this.removeIcon&&e.push(this.removeIcon),e}_handlePrimaryActionInteraction(){}_hasInteractiveActions(){return this._getActions().length>0}_edit(e){}_monitorFocus(){this._focusMonitor.monitor(this._elementRef,!0).subscribe(e=>{let i=e!==null;i!==this._hasFocusInternal&&(this._hasFocusInternal=i,i?this._onFocus.next({chip:this}):(this._changeDetectorRef.markForCheck(),setTimeout(()=>this._ngZone.run(()=>this._onBlur.next({chip:this})))))})}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=["*",[["mat-chip-avatar"],["","matChipAvatar",""]],[["mat-chip-trailing-icon"],["","matChipRemove",""],["","matChipTrailingIcon",""]]],i=["*","mat-chip-avatar, [matChipAvatar]","mat-chip-trailing-icon,[matChipRemove],[matChipTrailingIcon]"];function a(o,s){o&1&&(y(0,"span",3),j(1,1),v())}function r(o,s){o&1&&(y(0,"span",7),j(1,2),v())}return T({type:t,selectors:[["mat-basic-chip"],["","mat-basic-chip",""],["mat-chip"],["","mat-chip",""]],contentQueries:function(s,l,c){if(s&1&&ot(c,lu,5)(c,ry,5)(c,ay,5)(c,oy,5)(c,lu,5)(c,ay,5)(c,ry,5)(c,oy,5),s&2){let d;H(d=U())&&(l.leadingIcon=d.first),H(d=U())&&(l.editIcon=d.first),H(d=U())&&(l.trailingIcon=d.first),H(d=U())&&(l.removeIcon=d.first),H(d=U())&&(l._allLeadingIcons=d),H(d=U())&&(l._allTrailingIcons=d),H(d=U())&&(l._allEditIcons=d),H(d=U())&&(l._allRemoveIcons=d)}},viewQuery:function(s,l){if(s&1&&qe(PM,5),s&2){let c;H(c=U())&&(l.primaryAction=c.first)}},hostAttrs:[1,"mat-mdc-chip"],hostVars:31,hostBindings:function(s,l){s&1&&xe("keydown",function(d){return l._handleKeydown(d)}),s&2&&(di("id",l.id),fe("role",l.role)("aria-label",l.ariaLabel),At("mat-"+(l.color||"primary")),ee("mdc-evolution-chip",!l._isBasicChip)("mdc-evolution-chip--disabled",l.disabled)("mdc-evolution-chip--with-trailing-action",l._hasTrailingIcon())("mdc-evolution-chip--with-primary-graphic",l.leadingIcon)("mdc-evolution-chip--with-primary-icon",l.leadingIcon)("mdc-evolution-chip--with-avatar",l.leadingIcon)("mat-mdc-chip-with-avatar",l.leadingIcon)("mat-mdc-chip-highlighted",l.highlighted)("mat-mdc-chip-disabled",l.disabled)("mat-mdc-basic-chip",l._isBasicChip)("mat-mdc-standard-chip",!l._isBasicChip)("mat-mdc-chip-with-trailing-icon",l._hasTrailingIcon())("_mat-animation-noopable",l._animationsDisabled))},inputs:{role:"role",id:"id",ariaLabel:[0,"aria-label","ariaLabel"],ariaDescription:[0,"aria-description","ariaDescription"],value:"value",color:"color",removable:[2,"removable","removable",ve],highlighted:[2,"highlighted","highlighted",ve],disableRipple:[2,"disableRipple","disableRipple",ve],disabled:[2,"disabled","disabled",ve]},outputs:{removed:"removed",destroyed:"destroyed"},exportAs:["matChip"],features:[Je([{provide:sy,useExisting:t}])],ngContentSelectors:i,decls:9,vars:2,consts:[[1,"mat-mdc-chip-focus-overlay"],[1,"mdc-evolution-chip__cell","mdc-evolution-chip__cell--primary"],["matChipContent",""],[1,"mdc-evolution-chip__graphic","mat-mdc-chip-graphic"],[1,"mdc-evolution-chip__text-label","mat-mdc-chip-action-label"],[1,"mat-mdc-chip-primary-focus-indicator","mat-focus-indicator"],[1,"mat-mdc-chip-touch-target"],[1,"mdc-evolution-chip__cell","mdc-evolution-chip__cell--trailing"]],template:function(s,l){s&1&&(de(e),R(0,"span",0),y(1,"span",1)(2,"span",2),E(3,a,2,0,"span",3),y(4,"span",4),j(5),R(6,"span",5),v(),R(7,"span",6),v()(),E(8,r,2,0,"span",7)),s&2&&(h(3),D(l.leadingIcon?3:-1),h(5),D(l._hasTrailingIcon()?8:-1))},dependencies:[ly],styles:[`.mdc-evolution-chip,
.mdc-evolution-chip__cell,
.mdc-evolution-chip__action {
  display: inline-flex;
  align-items: center;
}

.mdc-evolution-chip {
  position: relative;
  max-width: 100%;
}

.mat-mdc-chip-touch-target {
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  height: 48px;
  transform: translateY(-50%);
  display: var(--%NS%mat-chip-touch-target-display, block);
  pointer-events: auto;
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
`],encapsulation:2})})()}return t})();var mr=(()=>{class t{_elementRef=p(X);_changeDetectorRef=p(We);_dir=p(Tt,{optional:!0});_lastDestroyedFocusedChipIndex=null;_keyManager;_destroyed=new z;_defaultRole="presentation";get chipFocusChanges(){return this._getChipStream(e=>e._onFocus)}get chipDestroyedChanges(){return this._getChipStream(e=>e.destroyed)}get chipRemovedChanges(){return this._getChipStream(e=>e.removed)}get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._syncChipsState()}_disabled=!1;get empty(){return!this._chips||this._chips.length===0}get role(){return this._explicitRole?this._explicitRole:this.empty?null:this._defaultRole}tabIndex=0;set role(e){this._explicitRole=e}_explicitRole=null;get focused(){return this._hasFocusedChip()}_chips;_chipActions=new Vn;ngAfterViewInit(){this._setUpFocusManagement(),this._trackChipSetChanges(),this._trackDestroyedFocusedChip()}ngOnDestroy(){this._keyManager?.destroy(),this._chipActions.destroy(),this._destroyed.next(),this._destroyed.complete()}_hasFocusedChip(){return this._chips&&this._chips.some(e=>e._hasFocus())}_syncChipsState(){this._chips?.forEach(e=>{e._chipListDisabled=this._disabled,e._changeDetectorRef.markForCheck()})}focus(){}_handleKeydown(e){this._originatesFromChip(e)&&this._keyManager.onKeydown(e)}_isValidIndex(e){return e>=0&&e<this._chips.length}_allowFocusEscape(){let e=this._elementRef.nativeElement.tabIndex;e!==-1&&(this._elementRef.nativeElement.tabIndex=-1,setTimeout(()=>this._elementRef.nativeElement.tabIndex=e))}_getChipStream(e){return this._chips.changes.pipe(_t(null),Gt(()=>It(...this._chips.map(e))))}_originatesFromChip(e){let i=e.target;for(;i&&i!==this._elementRef.nativeElement;){if(i.classList.contains("mat-mdc-chip"))return!0;i=i.parentElement}return!1}_setUpFocusManagement(){this._chips.changes.pipe(_t(this._chips)).subscribe(e=>{let i=[];e.forEach(a=>a._getActions().forEach(r=>i.push(r))),this._chipActions.reset(i),this._chipActions.notifyOnChanges()}),this._keyManager=new zi(this._chipActions).withVerticalOrientation().withHorizontalOrientation(this._dir?this._dir.value:"ltr").withHomeAndEnd().skipPredicate(e=>this._skipPredicate(e)),this.chipFocusChanges.pipe(Ae(this._destroyed)).subscribe(({chip:e})=>{let i=e._getSourceAction(document.activeElement);i&&this._keyManager.updateActiveItem(i)}),this._dir?.change.pipe(Ae(this._destroyed)).subscribe(e=>this._keyManager.withHorizontalOrientation(e))}_skipPredicate(e){return e.disabled}_trackChipSetChanges(){this._chips.changes.pipe(_t(null),Ae(this._destroyed)).subscribe(()=>{this.disabled&&Promise.resolve().then(()=>this._syncChipsState()),this._redirectDestroyedChipFocus()})}_trackDestroyedFocusedChip(){this.chipDestroyedChanges.pipe(Ae(this._destroyed)).subscribe(e=>{let a=this._chips.toArray().indexOf(e.chip),r=e.chip._hasFocus(),o=e.chip._hadFocusOnRemove&&this._keyManager.activeItem&&e.chip._getActions().includes(this._keyManager.activeItem),s=r||o;this._isValidIndex(a)&&s&&(this._lastDestroyedFocusedChipIndex=a)})}_redirectDestroyedChipFocus(){if(this._lastDestroyedFocusedChipIndex!=null){if(this._chips.length){let e=Math.min(this._lastDestroyedFocusedChipIndex,this._chips.length-1),i=this._chips.toArray()[e];i.disabled?this._chips.length===1?this.focus():this._keyManager.setPreviousItemActive():i.focus()}else this.focus();this._lastDestroyedFocusedChipIndex=null}}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){return T({type:t,selectors:[["mat-chip-set"]],contentQueries:function(a,r,o){if(a&1&&ot(o,ti,5),a&2){let s;H(s=U())&&(r._chips=s)}},hostAttrs:[1,"mat-mdc-chip-set","mdc-evolution-chip-set"],hostVars:1,hostBindings:function(a,r){a&1&&xe("keydown",function(s){return r._handleKeydown(s)}),a&2&&fe("role",r.role)},inputs:{disabled:[2,"disabled","disabled",ve],role:"role",tabIndex:[2,"tabIndex","tabIndex",i=>i==null?0:pi(i)]},ngContentSelectors:["*"],decls:2,vars:0,consts:[["role","presentation",1,"mdc-evolution-chip-set__chips"]],template:function(a,r){a&1&&(de(),Qe(0,"div",0),j(1),et())},styles:[`.mat-mdc-chip-set {
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
  margin: 8px 0 8px 8px;
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
`],encapsulation:2})})()}return t})();var _n=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({providers:[iy,{provide:RM,useValue:{separatorKeyCodes:[13]}}],imports:[An,me]})}return t})();var FM=new I("ListOption"),du=(()=>{class t{_elementRef=p(X);static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","matListItemTitle",""]],hostAttrs:[1,"mat-mdc-list-item-title","mdc-list-item__primary-text"]})}return t})(),mu=(()=>{class t{_elementRef=p(X);static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","matListItemLine",""]],hostAttrs:[1,"mat-mdc-list-item-line","mdc-list-item__secondary-text"]})}return t})(),LM=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,selectors:[["","matListItemMeta",""]],hostAttrs:[1,"mat-mdc-list-item-meta","mdc-list-item__end"]})}return t})(),cy=(()=>{class t{_listOption=p(FM,{optional:!0});_isAlignedAtStart(){return!this._listOption||this._listOption?._getTogglePosition()==="after"}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,hostVars:4,hostBindings:function(i,a){i&2&&ee("mdc-list-item__start",a._isAlignedAtStart())("mdc-list-item__end",!a._isAlignedAtStart())}})}return t})(),BM=(()=>{class t extends cy{static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(t)))(a||t)}})();static \u0275dir=q({type:t,selectors:[["","matListItemAvatar",""]],hostAttrs:[1,"mat-mdc-list-item-avatar"],features:[Ve]})}return t})(),pu=(()=>{class t extends cy{static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(t)))(a||t)}})();static \u0275dir=q({type:t,selectors:[["","matListItemIcon",""]],hostAttrs:[1,"mat-mdc-list-item-icon"],features:[Ve]})}return t})(),jM=new I("MAT_LIST_CONFIG"),cu=(()=>{class t{_isNonInteractive=!0;get disableRipple(){return this._disableRipple}set disableRipple(e){this._disableRipple=Rt(e)}_disableRipple=!1;get disabled(){return this._disabled()}set disabled(e){this._disabled.set(Rt(e))}_disabled=ne(!1);_defaultOptions=p(jM,{optional:!0});static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,hostVars:1,hostBindings:function(i,a){i&2&&fe("aria-disabled",a.disabled)},inputs:{disableRipple:"disableRipple",disabled:"disabled"}})}return t})(),VM=(()=>{class t{_elementRef=p(X);_ngZone=p(K);_listBase=p(cu,{optional:!0});_platform=p(Ie);_hostElement;_isButtonElement;_noopAnimations=tt();_avatars;_icons;set lines(e){this._explicitLines=qn(e,null),this._updateItemLines(!1)}_explicitLines=null;get disableRipple(){return this.disabled||this._disableRipple||this._noopAnimations||!!this._listBase?.disableRipple}set disableRipple(e){this._disableRipple=Rt(e)}_disableRipple=!1;get disabled(){return this._disabled()||!!this._listBase?.disabled}set disabled(e){this._disabled.set(Rt(e))}_disabled=ne(!1);_subscriptions=new Mt;_rippleRenderer=null;_hasUnscopedTextContent=!1;rippleConfig;get rippleDisabled(){return this.disableRipple||!!this.rippleConfig.disabled}constructor(){p(dt).load(hn);let e=p(Wi,{optional:!0});this.rippleConfig=e||{},this._hostElement=this._elementRef.nativeElement,this._isButtonElement=this._hostElement.nodeName.toLowerCase()==="button",this._listBase&&!this._listBase._isNonInteractive&&this._initInteractiveListItem(),this._isButtonElement&&!this._hostElement.hasAttribute("type")&&this._hostElement.setAttribute("type","button")}ngAfterViewInit(){this._monitorProjectedLinesAndTitle(),this._updateItemLines(!0)}ngOnDestroy(){this._subscriptions.unsubscribe(),this._rippleRenderer!==null&&this._rippleRenderer._removeTriggerEvents()}_hasIconOrAvatar(){return!!(this._avatars.length||this._icons.length)}_initInteractiveListItem(){this._hostElement.classList.add("mat-mdc-list-item-interactive"),this._rippleRenderer=new Gi(this,this._ngZone,this._hostElement,this._platform,p(re)),this._rippleRenderer.setupTriggerEvents(this._hostElement)}_monitorProjectedLinesAndTitle(){this._ngZone.runOutsideAngular(()=>{this._subscriptions.add(It(this._lines.changes,this._titles.changes).subscribe(()=>this._updateItemLines(!1)))})}_updateItemLines(e){if(!this._lines||!this._titles||!this._unscopedContent)return;e&&this._checkDomForUnscopedTextContent();let i=this._explicitLines??this._inferLinesFromContent(),a=this._unscopedContent.nativeElement;if(this._hostElement.classList.toggle("mat-mdc-list-item-single-line",i<=1),this._hostElement.classList.toggle("mdc-list-item--with-one-line",i<=1),this._hostElement.classList.toggle("mdc-list-item--with-two-lines",i===2),this._hostElement.classList.toggle("mdc-list-item--with-three-lines",i===3),this._hasUnscopedTextContent){let r=this._titles.length===0&&i===1;a.classList.toggle("mdc-list-item__primary-text",r),a.classList.toggle("mdc-list-item__secondary-text",!r)}else a.classList.remove("mdc-list-item__primary-text"),a.classList.remove("mdc-list-item__secondary-text")}_inferLinesFromContent(){let e=this._titles.length+this._lines.length;return this._hasUnscopedTextContent&&(e+=1),e}_checkDomForUnscopedTextContent(){this._hasUnscopedTextContent=Array.from(this._unscopedContent.nativeElement.childNodes).filter(e=>e.nodeType!==e.COMMENT_NODE).some(e=>!!(e.textContent&&e.textContent.trim()))}static \u0275fac=function(i){return new(i||t)};static \u0275dir=q({type:t,contentQueries:function(i,a,r){if(i&1&&ot(r,BM,4)(r,pu,4),i&2){let o;H(o=U())&&(a._avatars=o),H(o=U())&&(a._icons=o)}},hostVars:4,hostBindings:function(i,a){i&2&&(fe("aria-disabled",a.disabled)("disabled",a._isButtonElement&&a.disabled||null),ee("mdc-list-item--disabled",a.disabled))},inputs:{lines:"lines",disableRipple:"disableRipple",disabled:"disabled"}})}return t})();var dy=(()=>{class t extends cu{static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(t)))(a||t)}})();static \u0275cmp=(function(){let e=["*"];return T({type:t,selectors:[["mat-list"]],hostAttrs:[1,"mat-mdc-list","mat-mdc-list-base","mdc-list"],exportAs:["matList"],features:[Je([{provide:cu,useExisting:t}]),Ve],ngContentSelectors:e,decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},styles:[`.mdc-list {
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
`],encapsulation:2})})()}return t})(),my=(()=>{class t extends VM{_lines;_titles;_meta;_unscopedContent;_itemText;get activated(){return this._activated}set activated(e){this._activated=Rt(e)}_activated=!1;_getAriaCurrent(){return this._hostElement.nodeName==="A"&&this._activated?"page":null}_hasBothLeadingAndTrailing(){return this._meta.length!==0&&(this._avatars.length!==0||this._icons.length!==0)}static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(t)))(a||t)}})();static \u0275cmp=(function(){let e=["unscopedContent"],i=["text"],a=[[["","matListItemAvatar",""],["","matListItemIcon",""]],[["","matListItemTitle",""]],[["","matListItemLine",""]],"*",[["","matListItemMeta",""]],[["mat-divider"]]];return T({type:t,selectors:[["mat-list-item"],["a","mat-list-item",""],["button","mat-list-item",""]],contentQueries:function(s,l,c){if(s&1&&ot(c,mu,5)(c,du,5)(c,LM,5),s&2){let d;H(d=U())&&(l._lines=d),H(d=U())&&(l._titles=d),H(d=U())&&(l._meta=d)}},viewQuery:function(s,l){if(s&1&&qe(e,5)(i,5),s&2){let c;H(c=U())&&(l._unscopedContent=c.first),H(c=U())&&(l._itemText=c.first)}},hostAttrs:[1,"mat-mdc-list-item","mdc-list-item"],hostVars:13,hostBindings:function(s,l){s&2&&(fe("aria-current",l._getAriaCurrent()),ee("mdc-list-item--activated",l.activated)("mdc-list-item--with-leading-avatar",l._avatars.length!==0)("mdc-list-item--with-leading-icon",l._icons.length!==0)("mdc-list-item--with-trailing-meta",l._meta.length!==0)("mat-mdc-list-item-both-leading-and-trailing",l._hasBothLeadingAndTrailing())("_mat-animation-noopable",l._noopAnimations))},inputs:{activated:"activated"},exportAs:["matListItem"],features:[Ve],ngContentSelectors:["[matListItemAvatar],[matListItemIcon]","[matListItemTitle]","[matListItemLine]","*","[matListItemMeta]","mat-divider"],decls:10,vars:0,consts:[["unscopedContent",""],[1,"mdc-list-item__content"],[1,"mat-mdc-list-item-unscoped-content",3,"cdkObserveContent"],[1,"mat-focus-indicator"]],template:function(s,l){s&1&&(de(a),j(0),y(1,"span",1),j(2,1),j(3,2),y(4,"span",2,0),xe("cdkObserveContent",function(){return l._updateItemLines(!0)}),j(6,3),v()(),j(7,4),j(8,5),R(9,"div",3))},dependencies:[og],encapsulation:2})})()}return t})();var Ko=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[al,An,uc,me,Ho]})}return t})();var zM=new I("MAT_DRAWER_DEFAULT_AUTOSIZE",{providedIn:"root",factory:()=>!1}),fu=new I("MAT_DRAWER_CONTAINER"),Yo=(()=>{class t extends Ra{_platform=p(Ie);_changeDetectorRef=p(We);_element=p(X);_ngZone=p(K);_isInert=!1;_container=p(hu);ngAfterContentInit(){this._container._contentMarginChanges.subscribe(()=>this._changeDetectorRef.markForCheck())}_drawerToggled(e){e.opened?this._ngZone.runOutsideAngular(()=>{e._animationEnd.pipe(eh(50),Fn(1)).subscribe(()=>this._updateInert())}):this._updateInert()}_drawerModeChanged(){this._updateInert()}_updateInert(){let e=this._container._isShowingBackdrop();if(e!==this._isInert){let i=this._element.nativeElement;this._isInert=e,e?i.setAttribute("inert","true"):i.removeAttribute("inert")}}_shouldBeHidden(){if(this._platform.isBrowser)return!1;let{start:e,end:i}=this._container;return e!=null&&e.mode!=="over"&&e.opened||i!=null&&i.mode!=="over"&&i.opened}static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(t)))(a||t)}})();static \u0275cmp=(function(){let e=["*"];return T({type:t,selectors:[["mat-drawer-content"]],hostAttrs:[1,"mat-drawer-content"],hostVars:6,hostBindings:function(a,r){a&2&&($n("margin-left",r._container._contentMargins.left,"px")("margin-right",r._container._contentMargins.right,"px"),ee("mat-drawer-content-hidden",r._shouldBeHidden()))},features:[Je([{provide:Ra,useExisting:t}]),Ve],ngContentSelectors:e,decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},encapsulation:2})})()}return t})(),uu=(()=>{class t{_elementRef=p(X);_focusTrapFactory=p($d);_focusMonitor=p(In);_platform=p(Ie);_ngZone=p(K);_renderer=p(ct);_interactivityChecker=p(ol);_doc=p(Y);_isAnimating=!1;_container=p(fu,{optional:!0});_focusTrap=null;_elementFocusedBeforeDrawerWasOpened=null;_eventCleanups;_isAttached=!1;_anchor=null;get position(){return this._position}set position(e){e=e==="end"?"end":"start",e!==this._position&&(this._isAttached&&this._updatePositionInParent(e),this._position=e,this.onPositionChanged.emit())}_position="start";get mode(){return this._mode}set mode(e){this._mode=e,this._updateFocusTrapState(),this._modeChanged.next(),this._getContent()?._drawerModeChanged()}_mode="over";get disableClose(){return this._disableClose}set disableClose(e){this._disableClose=Rt(e)}_disableClose=!1;get autoFocus(){let e=this._autoFocus;return e??(this.mode==="side"?"dialog":"first-tabbable")}set autoFocus(e){(e==="true"||e==="false"||e==null)&&(e=Rt(e)),this._autoFocus=e}_autoFocus;get opened(){return this._opened()}set opened(e){this.toggle(Rt(e))}_opened=ne(!1);_openedVia=null;_animationStarted=new z;_animationEnd=new z;openedChange=new Te(!0);_openedStream=this.openedChange.pipe(Ke(e=>e),Me(()=>{}));openedStart=this._animationStarted.pipe(Ke(()=>this.opened),kr(void 0));_closedStream=this.openedChange.pipe(Ke(e=>!e),Me(()=>{}));closedStart=this._animationStarted.pipe(Ke(()=>!this.opened),kr(void 0));_destroyed=new z;onPositionChanged=new Te;_content;_modeChanged=new z;_injector=p(re);_changeDetectorRef=p(We);constructor(){this.openedChange.pipe(Ae(this._destroyed)).subscribe(e=>{e?(this._elementFocusedBeforeDrawerWasOpened=this._doc.activeElement,this._takeFocus()):this._isFocusWithinDrawer()&&this._restoreFocus(this._openedVia||"program")}),this._eventCleanups=this._ngZone.runOutsideAngular(()=>{let e=this._renderer,i=this._elementRef.nativeElement;return[e.listen(i,"keydown",a=>{a.keyCode===27&&!this.disableClose&&!vi(a)&&this._ngZone.run(()=>{this.close(),a.stopPropagation(),a.preventDefault()})}),e.listen(i,"transitionend",this._handleTransitionEvent),e.listen(i,"transitioncancel",this._handleTransitionEvent)]}),this._animationEnd.subscribe(()=>{this.openedChange.emit(this.opened)})}_focusByCssSelector(e,i){let a=this._elementRef.nativeElement.querySelector(e);a&&(this._interactivityChecker.isFocusable(a)||(a.tabIndex=-1,this._ngZone.runOutsideAngular(()=>{let r=()=>{o(),s(),a.removeAttribute("tabindex")},o=this._renderer.listen(a,"blur",r),s=this._renderer.listen(a,"mousedown",r)})),a.focus(i))}_takeFocus(){if(!this._focusTrap)return;let e=this._elementRef.nativeElement;switch(this.autoFocus){case!1:case"dialog":return;case!0:case"first-tabbable":ht(()=>{let i=this._isAnimating?{preventScroll:!0}:void 0;!this._focusTrap.focusInitialElement(i)&&typeof e.focus=="function"&&e.focus(i)},{injector:this._injector});break;case"first-heading":this._focusByCssSelector('h1, h2, h3, h4, h5, h6, [role="heading"]');break;default:this._focusByCssSelector(this.autoFocus);break}}_restoreFocus(e){this.autoFocus!=="dialog"&&(this._elementFocusedBeforeDrawerWasOpened?this._focusMonitor.focusVia(this._elementFocusedBeforeDrawerWasOpened,e):this._elementRef.nativeElement.blur(),this._elementFocusedBeforeDrawerWasOpened=null)}_isFocusWithinDrawer(){let e=this._doc.activeElement;return!!e&&this._elementRef.nativeElement.contains(e)}ngAfterViewInit(){this._isAttached=!0,this._position==="end"&&this._updatePositionInParent("end"),this._platform.isBrowser&&(this._focusTrap=this._focusTrapFactory.create(this._elementRef.nativeElement),this._updateFocusTrapState())}ngOnDestroy(){this._eventCleanups.forEach(e=>e()),this._focusTrap?.destroy(),this._anchor?.remove(),this._anchor=null,this._animationStarted.complete(),this._animationEnd.complete(),this._modeChanged.complete(),this._destroyed.next(),this._destroyed.complete()}open(e){return this.toggle(!0,e)}close(){return this.toggle(!1)}_closeViaBackdropClick(){return this._setOpen(!1,!0,"mouse")}toggle(e=!this.opened,i){e&&i&&(this._openedVia=i);let a=this._setOpen(e,!e&&this._isFocusWithinDrawer(),this._openedVia||"program");return e||(this._openedVia=null),a}_setOpen(e,i,a){return e===this.opened?Promise.resolve(e?"open":"close"):(this._opened.set(e),this._getContent()?._drawerToggled(this),this._container?._transitionsEnabled?this._isAnimating?(this._setIsAnimating(!1),this._simulateAnimation()):(this._setIsAnimating(!0),setTimeout(()=>this._animationStarted.next())):this._simulateAnimation(),this._elementRef.nativeElement.classList.toggle("mat-drawer-opened",e),!e&&i&&this._restoreFocus(a),this._changeDetectorRef.markForCheck(),this._updateFocusTrapState(),new Promise(r=>{this.openedChange.pipe(Fn(1)).subscribe(o=>r(o?"open":"close"))}))}_getContent(){return this._container?._content||this._container?._userContent}_setIsAnimating(e){e!==this._isAnimating&&(this._isAnimating=e,this._elementRef.nativeElement.classList.toggle("mat-drawer-animating",e))}_simulateAnimation(){setTimeout(()=>{this._animationStarted.next(),this._animationEnd.next()})}_getWidth(){return this._elementRef.nativeElement.offsetWidth||0}_updateFocusTrapState(){this._focusTrap&&(this._focusTrap.enabled=this.opened&&!!this._container?._isShowingBackdrop())}_updatePositionInParent(e){if(!this._platform.isBrowser)return;let i=this._elementRef.nativeElement,a=i.parentNode;e==="end"?(this._anchor||(this._anchor=this._doc.createComment("mat-drawer-anchor"),a.insertBefore(this._anchor,i)),a.appendChild(i)):this._anchor&&this._anchor.parentNode.insertBefore(i,this._anchor)}_handleTransitionEvent=e=>{let i=this._elementRef.nativeElement;e.target===i&&this._ngZone.run(()=>{e.type==="transitionend"&&this._setIsAnimating(!1),this._animationEnd.next(e)})};static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=["content"];return T({type:t,selectors:[["mat-drawer"]],viewQuery:function(r,o){if(r&1&&qe(e,5),r&2){let s;H(s=U())&&(o._content=s.first)}},hostAttrs:[1,"mat-drawer"],hostVars:12,hostBindings:function(r,o){r&2&&(fe("align",null)("tabIndex",o.mode!=="side"?"-1":null),$n("visibility",!o._container&&!o.opened?"hidden":null),ee("mat-drawer-end",o.position==="end")("mat-drawer-over",o.mode==="over")("mat-drawer-push",o.mode==="push")("mat-drawer-side",o.mode==="side"))},inputs:{position:"position",mode:"mode",disableClose:"disableClose",autoFocus:"autoFocus",opened:"opened"},outputs:{openedChange:"openedChange",_openedStream:"opened",openedStart:"openedStart",_closedStream:"closed",closedStart:"closedStart",onPositionChanged:"positionChanged"},exportAs:["matDrawer"],ngContentSelectors:["*"],decls:3,vars:0,consts:[["content",""],["cdkScrollable","",1,"mat-drawer-inner-container"]],template:function(r,o){r&1&&(de(),y(0,"div",1,0),j(2),v())},dependencies:[Ra],encapsulation:2})})()}return t})(),hu=(()=>{class t{_dir=p(Tt,{optional:!0});_element=p(X);_ngZone=p(K);_changeDetectorRef=p(We);_animationDisabled=tt();_transitionsEnabled=!1;_allDrawers;_drawers=new Vn;_content;_userContent;get start(){return this._start}get end(){return this._end}get autosize(){return this._autosize}set autosize(e){this._autosize=Rt(e)}_autosize=p(zM);get hasBackdrop(){return this._drawerHasBackdrop(this._start)||this._drawerHasBackdrop(this._end)}set hasBackdrop(e){this._backdropOverride=e==null?null:Rt(e)}_backdropOverride=null;backdropClick=new Te;_start=null;_end=null;_left=null;_right=null;_destroyed=new z;_doCheckSubject=new z;_contentMargins={left:null,right:null};_contentMarginChanges=new z;get scrollable(){return this._userContent||this._content}_injector=p(re);constructor(){let e=p(Ie),i=p(Wn);this._dir?.change.pipe(Ae(this._destroyed)).subscribe(()=>{this._validateDrawers(),this.updateContentMargins()}),i.change().pipe(Ae(this._destroyed)).subscribe(()=>this.updateContentMargins()),!this._animationDisabled&&e.isBrowser&&this._ngZone.runOutsideAngular(()=>{setTimeout(()=>{this._element.nativeElement.classList.add("mat-drawer-transition"),this._transitionsEnabled=!0},200)})}ngAfterContentInit(){this._allDrawers.changes.pipe(_t(this._allDrawers),Ae(this._destroyed)).subscribe(e=>{this._drawers.reset(e.filter(i=>!i._container||i._container===this)),this._drawers.notifyOnChanges()}),this._drawers.changes.pipe(_t(null)).subscribe(()=>{this._validateDrawers(),this._drawers.forEach(e=>{this._watchDrawerToggle(e),this._watchDrawerPosition(e),this._watchDrawerMode(e)}),(!this._drawers.length||this._isDrawerOpen(this._start)||this._isDrawerOpen(this._end))&&this.updateContentMargins(),this._changeDetectorRef.markForCheck()}),this._ngZone.runOutsideAngular(()=>{this._doCheckSubject.pipe(ii(10),Ae(this._destroyed)).subscribe(()=>this.updateContentMargins())})}ngOnDestroy(){this._contentMarginChanges.complete(),this._doCheckSubject.complete(),this._drawers.destroy(),this._destroyed.next(),this._destroyed.complete()}open(){this._drawers.forEach(e=>e.open())}close(){this._drawers.forEach(e=>e.close())}updateContentMargins(){let e=0,i=0;if(this._left&&this._left.opened){if(this._left.mode=="side")e+=this._left._getWidth();else if(this._left.mode=="push"){let a=this._left._getWidth();e+=a,i-=a}}if(this._right&&this._right.opened){if(this._right.mode=="side")i+=this._right._getWidth();else if(this._right.mode=="push"){let a=this._right._getWidth();i+=a,e-=a}}e=e||null,i=i||null,(e!==this._contentMargins.left||i!==this._contentMargins.right)&&(this._contentMargins={left:e,right:i},this._ngZone.run(()=>this._contentMarginChanges.next(this._contentMargins)))}ngDoCheck(){this._autosize&&this._isPushed()&&this._ngZone.runOutsideAngular(()=>this._doCheckSubject.next())}_watchDrawerToggle(e){e._animationStarted.pipe(Ae(this._drawers.changes)).subscribe(()=>{this.updateContentMargins(),this._changeDetectorRef.markForCheck()}),e.mode!=="side"&&e.openedChange.pipe(Ae(this._drawers.changes)).subscribe(()=>this._setContainerClass(e.opened))}_watchDrawerPosition(e){e.onPositionChanged.pipe(Ae(this._drawers.changes)).subscribe(()=>{ht({read:()=>this._validateDrawers()},{injector:this._injector})})}_watchDrawerMode(e){e._modeChanged.pipe(Ae(It(this._drawers.changes,this._destroyed))).subscribe(()=>{this.updateContentMargins(),this._changeDetectorRef.markForCheck()})}_setContainerClass(e){let i=this._element.nativeElement.classList,a="mat-drawer-container-has-open";e?i.add(a):i.remove(a)}_validateDrawers(){this._start=this._end=null,this._drawers.forEach(e=>{e.position=="end"?(this._end!=null,this._end=e):(this._start!=null,this._start=e)}),this._right=this._left=null,this._dir&&this._dir.value==="rtl"?(this._left=this._end,this._right=this._start):(this._left=this._start,this._right=this._end)}_isPushed(){return this._isDrawerOpen(this._start)&&this._start.mode!="over"||this._isDrawerOpen(this._end)&&this._end.mode!="over"}_onBackdropClicked(){this.backdropClick.emit(),this._closeModalDrawersViaBackdrop()}_closeModalDrawersViaBackdrop(){[this._start,this._end].filter(e=>e&&!e.disableClose&&this._drawerHasBackdrop(e)).forEach(e=>e._closeViaBackdropClick())}_isShowingBackdrop(){return this._isDrawerOpen(this._start)&&this._drawerHasBackdrop(this._start)||this._isDrawerOpen(this._end)&&this._drawerHasBackdrop(this._end)}_isDrawerOpen(e){return e!=null&&e.opened}_drawerHasBackdrop(e){return this._backdropOverride==null?!!e&&e.mode!=="side":this._backdropOverride}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=(function(){let e=[[["mat-drawer"],["mat-sidenav"]],[["mat-drawer-content"],["mat-sidenav-content"]],"*"],i=["mat-drawer, mat-sidenav","mat-drawer-content, mat-sidenav-content","*"];function a(o,s){if(o&1){let l=ft();y(0,"div",1),xe("click",function(){at(l);let d=M();return rt(d._onBackdropClicked())}),v()}if(o&2){let l=M();ee("mat-drawer-shown",l._isShowingBackdrop())}}function r(o,s){o&1&&(y(0,"mat-drawer-content"),j(1,2),v())}return T({type:t,selectors:[["mat-drawer-container"]],contentQueries:function(s,l,c){if(s&1&&ot(c,Yo,5)(c,uu,5),s&2){let d;H(d=U())&&(l._content=d.first),H(d=U())&&(l._allDrawers=d)}},viewQuery:function(s,l){if(s&1&&qe(Yo,5),s&2){let c;H(c=U())&&(l._userContent=c.first)}},hostAttrs:[1,"mat-drawer-container"],hostVars:2,hostBindings:function(s,l){s&2&&ee("mat-drawer-container-explicit-backdrop",l._backdropOverride)},inputs:{autosize:"autosize",hasBackdrop:"hasBackdrop"},outputs:{backdropClick:"backdropClick"},exportAs:["matDrawerContainer"],features:[Je([{provide:fu,useExisting:t}])],ngContentSelectors:i,decls:4,vars:2,consts:[[1,"mat-drawer-backdrop",3,"mat-drawer-shown"],[1,"mat-drawer-backdrop",3,"click"]],template:function(s,l){s&1&&(de(e),E(0,a,1,2,"div",0),j(1),j(2,1),E(3,r,2,0,"mat-drawer-content")),s&2&&(D(l.hasBackdrop?0:-1),h(3),D(l._content?-1:3))},dependencies:[Yo],styles:[`.mat-drawer-container {
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
`],encapsulation:2})})()}return t})(),fc=(()=>{class t extends Yo{static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(t)))(a||t)}})();static \u0275cmp=(function(){let e=["*"];return T({type:t,selectors:[["mat-sidenav-content"]],hostAttrs:[1,"mat-drawer-content","mat-sidenav-content"],features:[Je([{provide:Ra,useExisting:t},{provide:Yo,useExisting:t}]),Ve],ngContentSelectors:e,decls:1,vars:0,template:function(a,r){a&1&&(de(),j(0))},encapsulation:2})})()}return t})(),gu=(()=>{class t extends uu{get fixedInViewport(){return this._fixedInViewport}set fixedInViewport(e){this._fixedInViewport=Rt(e)}_fixedInViewport=!1;get fixedTopGap(){return this._fixedTopGap}set fixedTopGap(e){this._fixedTopGap=qn(e)}_fixedTopGap=0;get fixedBottomGap(){return this._fixedBottomGap}set fixedBottomGap(e){this._fixedBottomGap=qn(e)}_fixedBottomGap=0;static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(t)))(a||t)}})();static \u0275cmp=(function(){let e=["*"];return T({type:t,selectors:[["mat-sidenav"]],hostAttrs:[1,"mat-drawer","mat-sidenav"],hostVars:16,hostBindings:function(a,r){a&2&&(fe("tabIndex",r.mode!=="side"?"-1":null)("align",null),$n("top",r.fixedInViewport?r.fixedTopGap:null,"px")("bottom",r.fixedInViewport?r.fixedBottomGap:null,"px"),ee("mat-drawer-end",r.position==="end")("mat-drawer-over",r.mode==="over")("mat-drawer-push",r.mode==="push")("mat-drawer-side",r.mode==="side")("mat-sidenav-fixed",r.fixedInViewport))},inputs:{fixedInViewport:"fixedInViewport",fixedTopGap:"fixedTopGap",fixedBottomGap:"fixedBottomGap"},exportAs:["matSidenav"],features:[Je([{provide:uu,useExisting:t}]),Ve],ngContentSelectors:e,decls:3,vars:0,consts:[["content",""],["cdkScrollable","",1,"mat-drawer-inner-container"]],template:function(a,r){a&1&&(de(),y(0,"div",1,0),j(2),v())},dependencies:[Ra],encapsulation:2})})()}return t})(),py=(()=>{class t extends hu{_allDrawers=void 0;_content=void 0;static \u0275fac=(()=>{let e;return function(a){return(e||(e=st(t)))(a||t)}})();static \u0275cmp=(function(){let e=[[["mat-drawer"],["mat-sidenav"]],[["mat-drawer-content"],["mat-sidenav-content"]],"*"],i=["mat-drawer, mat-sidenav","mat-drawer-content, mat-sidenav-content","*"];function a(o,s){if(o&1){let l=ft();y(0,"div",1),xe("click",function(){at(l);let d=M();return rt(d._onBackdropClicked())}),v()}if(o&2){let l=M();ee("mat-drawer-shown",l._isShowingBackdrop())}}function r(o,s){o&1&&(y(0,"mat-sidenav-content"),j(1,2),v())}return T({type:t,selectors:[["mat-sidenav-container"]],contentQueries:function(s,l,c){if(s&1&&ot(c,fc,5)(c,gu,5),s&2){let d;H(d=U())&&(l._content=d.first),H(d=U())&&(l._allDrawers=d)}},hostAttrs:[1,"mat-drawer-container","mat-sidenav-container"],hostVars:2,hostBindings:function(s,l){s&2&&ee("mat-drawer-container-explicit-backdrop",l._backdropOverride)},exportAs:["matSidenavContainer"],features:[Je([{provide:fu,useExisting:t},{provide:hu,useExisting:t}]),Ve],ngContentSelectors:i,decls:4,vars:2,consts:[[1,"mat-drawer-backdrop",3,"mat-drawer-shown"],[1,"mat-drawer-backdrop",3,"click"]],template:function(s,l){s&1&&(de(e),E(0,a,1,2,"div",0),j(1),j(2,1),E(3,r,2,0,"mat-sidenav-content")),s&2&&(D(l.hasBackdrop?0:-1),h(3),D(l._content?-1:3))},dependencies:[fc],styles:[`.mat-drawer-container {
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
`],encapsulation:2})})()}return t})(),Xo=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[Gn,me,Gn]})}return t})();var UM=[$o,Yn,Ot,zo,ei,eu,nu,Ho,Ko,Uo,rm,Go,Wo,_n,Xo],gc=class t{static \u0275fac=function(e){return new(e||t)};static \u0275mod=$({type:t});static \u0275inj=V({imports:[UM,$o,Yn,Ot,zo,ei,eu,nu,Ho,Ko,Uo,rm,Go,Wo,_n,Xo]})};var bc=class t{data=gt.required({transform:n=>"```json\n"+JSON.stringify(n,null,2)+"\n```"});static \u0275fac=function(e){return new(e||t)};static \u0275cmp=T({type:t,selectors:[["app-json"]],inputs:{data:[1,"data"]},decls:1,vars:1,consts:[[3,"data"]],template:function(e,i){e&1&&R(0,"markdown",0),e&2&&P("data",i.data())},dependencies:[Mn,Dn],encapsulation:2})};var vc={},pr=Symbol(),sa=Symbol(),hy=t=>typeof t=="string"?ye[t]:t,ye={plain:vc,plaintext:vc,text:vc,txt:vc},Jo=(t,n)=>(n[sa]||bu)(t,n),bu=(t,n)=>{for(var e=[t],i,a=[],r=0;i=hy(n[pr]);)delete n[pr],Object.assign(n,i);for(gy(t,n,e,0);a[r++]=e[0],e=e[1];);return a},vu=(t,n,e)=>t.replace(/&/g,"&amp;").replace(n,e),uy="</span>",_c="",Zo="",_u=t=>{for(var n="",e,i=0;e=t[i++];)n+=fy(e);return n},fy=t=>{if(t instanceof Qo){var{type:n,alias:e,content:i}=t,a=_c,r=Zo,o=`<span class="token ${n+(e?" "+e:"")+(n=="keyword"&&typeof i=="string"?" keyword-"+i.replace(/"|\s/g,""):"")}">`;Zo+=uy,_c+=o;var s=fy(i);return _c=a,Zo=r,o+s+uy}return typeof t!="string"?_u(t):(t=vu(t,/</g,"&lt;"),Zo&&t.includes(`
`)?t.replace(/\n/g,Zo+`
`+_c):t)};var gy=(t,n,e,i,a)=>{for(var r in n)if(n[r])for(var o=0,s=n[r],l,c=Array.isArray(s)?s:[s];l=c[o];o++){if(a&&a[0]==r&&a[1]==o)return;for(var d=l.pattern||l,m=hy(l.inside),u=l.lookbehind,f=d.global,g=l.alias,b=e,_=i;b&&(!a||_<a[2]);_+=b[0].length,b=b[1]){var x=b[0],w=0,C;if(!(x instanceof Qo)){if(d.lastIndex=f?_:0,C=d.exec(f?t:x),!C&&f)break;if(C&&C[0]){var F=u&&C[1]?C[1].length:0,A=C.index+F,Q=C[0].slice(F),Re=A+Q.length,He,s;if(f){for(;s=_+b[0].length,A>=s;b=b[1],_=s);if(b[0]instanceof Qo)continue;for(He=b,s=_;(s+=He[0].length)<Re;He=He[1],w++);x=t.slice(_,s),A-=_,Re-=_}for(var Be=x.slice(Re),G=_+x.length,oe=new Qo(r,m?Jo(Q,m):Q,Q,g),k=b,ce=0,je;k=k[1],ce++<w;);Be&&(!k||k[0]instanceof Qo?k=[Be,k]:k[0]=Be+k[0]),_+=A,b[0]=A?x.slice(0,A):oe,A?b=b[1]=[oe,k]:b[1]=k,w&&(gy(t,n,b,_,je=[r,o,G]),G=je[2]),a&&G>a[2]&&(a[2]=G)}}}}};function Qo(t,n,e,i){this.type=t,this.content=n,this.alias=i,this.length=e.length}var es=(t,n,...e)=>{let i,a=[],r,o="",s,l=!1,c=!0,d=[],m,u=0,f=GM(),g=f.firstChild,b=g.children,_=b[0],x=_.firstChild,w={language:"text",value:o},C=new Set(e),F={},A=le=>{Object.assign(w,le);let be=o!=(o=le.value??o),Ut=i!=(i=w.language);m=!!w.readOnly,f.style.tabSize=w.tabSize||2,x.inputMode=m?"none":"",x.setAttribute("aria-readonly",m),He(),Re(),be&&(l||x.remove(),x.value=o,x.selectionEnd=0,l||_.prepend(x)),(be||Ut)&&Q()},Q=()=>{d=Jo(o=x.value,ye[i]||{}),k("tokenize",d,i,o);let le=_u(d).split(`
`),be=0,Ut=u,Ii=u=le.length;for(;le[be]==a[be]&&be<Ii;)++be;for(;Ii&&le[--Ii]==a[--Ut];);if(be==Ii&&be==Ut)b[be+1].innerHTML=le[be]+`
`;else{let Wu=Ut<be?Ut:be-1,wr=Wu,Vc="";for(;wr<Ii;)Vc+=`<div class=pce-line aria-hidden=true>${le[++wr]}
</div>`;for(wr=Ii<be?Ii:be-1;wr<Ut;wr++)b[be+1].remove();Vc&&b[Wu+1].insertAdjacentHTML("afterend",Vc),f.style.setProperty("--number-width",(0|Math.log10(u))+1+".001ch")}k("update",o),ce(!0),c&&setTimeout(setTimeout,0,()=>c=!0),a=le,c=!1},Re=le=>{(le||C).forEach(be=>{typeof be=="object"?(be.update(je,w),le&&C.add(be)):(be(je,w),le||C.delete(be))})},He=([le,be]=Be())=>{f.className=`prism-code-editor language-${i}${w.lineNumbers==!1?"":" show-line-numbers"} pce-${w.wordWrap?"":"no"}wrap${w.rtl?" pce-rtl":""} pce-${le<be?"has":"no"}-selection${l?" pce-focus":""}${m?" pce-readonly":""}${w.class?" "+w.class:""}`},Be=()=>[x.selectionStart,x.selectionEnd,x.selectionDirection],G={Escape(){x.blur()}},oe={},k=(le,...be)=>{F[le]?.forEach(Ut=>Ut.apply(je,be)),w["on"+le[0].toUpperCase()+le.slice(1)]?.(...be,je)},ce=le=>{if(le||c){let be=Be(),Ut=b[s=yc(o,0,be[be[2]<"f"?0:1])];Ut!=r&&(r?.classList.remove("active-line"),Ut.classList.add("active-line"),r=Ut),He(be),k("selectionChange",be,o)}},je={container:f,wrapper:g,lines:b,textarea:x,get activeLine(){return s},get value(){return o},options:w,get focused(){return l},get tokens(){return d},inputCommandMap:oe,keyCommandMap:G,extensions:{},setOptions:A,update:Q,getSelection:Be,addExtensions(...le){Re(le)},on:(le,be)=>((F[le]||=new Set).add(be),()=>F[le].delete(be)),remove(){f.remove()}};return rn(x,"keydown",le=>{G[le.key]?.(le,Be(),o)&&la(le)}),rn(x,"beforeinput",le=>{(m||le.inputType=="insertText"&&oe[le.data]?.(le,Be(),o))&&la(le)}),rn(x,"input",Q),rn(x,"blur",()=>{xu=null,l=!1,He()}),rn(x,"focus",()=>{xu=ce,l=!0,He()}),rn(x,"selectionchange",le=>{ce(!le.isTrusted),la(le)}),qM(t)?.append(f),n&&A(n),je};var on="u">typeof window?document:null,yu=on?.createElement("div"),wu=(t,n)=>(yu&&(yu.innerHTML=t,n=yu.firstChild),()=>n.cloneNode(!0)),rn=(t,n,e,i)=>t.addEventListener(n,e,i),qM=t=>typeof t=="string"?on.querySelector(t):t,yc=(t,n=0,e=1/0)=>{let i=1;for(;(n=t.indexOf(`
`,n)+1)&&n<=e;i++);return i},xc={},GM=wu("<div><div class=pce-wrapper><div class=pce-overlays><textarea class=pce-textarea spellcheck=false autocapitalize=off autocomplete=off>"),la=t=>{t.preventDefault(),t.stopImmediatePropagation()};var xu;on&&rn(on,"selectionchange",()=>xu?.());globalThis.Prism={highlightAllUnder:t=>{t.querySelectorAll("pre code").forEach(n=>{let e=Array.from(n.classList).find(a=>a.startsWith("language"))||"",i=n.textContent||"";n.textContent="",es(n.parentElement,{value:i.trimEnd(),language:e.replace("language-",""),lineNumbers:!1,wordWrap:!0,readOnly:!0})})}};function WM(t,n){if(t&1&&(Qe(0,"span"),S(1),et()),t&2){let e=M();h(),Ir(" ",e.lowerBoundInclusive()?">=":">"," ",e.lowerBound()," ")}}function KM(t,n){if(t&1&&(Qe(0,"span"),S(1),et()),t&2){let e=M();h(),Ir(" ",e.upperBoundInclusive()?"<=":"<"," ",e.upperBound()," ")}}function YM(t,n){if(t&1&&(Qe(0,"span"),S(1),et()),t&2){let e=M();h(),bh("",e.lowerBoundInclusive()?"[":"("," ",e.lowerBound()," .. ",e.upperBound()," ",e.upperBoundInclusive()?"]":")"," ")}}var wc=class t{lowerBound=gt();upperBound=gt();lowerBoundInclusive=gt(!0,{transform:n=>n===!0||n=="true"});upperBoundInclusive=gt(!0,{transform:n=>n===!0||n=="true"});static \u0275fac=function(e){return new(e||t)};static \u0275cmp=T({type:t,selectors:[["app-schema-range"]],inputs:{lowerBound:[1,"lowerBound"],upperBound:[1,"upperBound"],lowerBoundInclusive:[1,"lowerBoundInclusive"],upperBoundInclusive:[1,"upperBoundInclusive"]},decls:4,vars:3,template:function(e,i){e&1&&(Qe(0,"span"),E(1,WM,2,2,"span"),E(2,KM,2,2,"span"),E(3,YM,2,4,"span"),et()),e&2&&(h(),D(i.lowerBound()!=null&&i.upperBound()==null?1:-1),h(),D(i.lowerBound()==null&&i.upperBound()!=null?2:-1),h(),D(i.lowerBound()!=null&&i.upperBound()!=null?3:-1))},encapsulation:2})};var by=()=>({}),vy=t=>({value:t});function XM(t,n){if(t&1&&Wc(0,2),t&2){let e=M(),i=Kt(4);P("ngTemplateOutlet",i)("ngTemplateOutletContext",Zc(2,vy,e.schema()))}}function ZM(t,n){t&1&&(y(0,"span",7),S(1,"*"),v())}function QM(t,n){t&1&&(y(0,"span",8),S(1,"\xA0(deprecated)"),v())}function JM(t,n){if(t&1&&(y(0,"mat-list-item",3)(1,"mat-icon",4),S(2,"arrow_right"),v(),y(3,"span",5)(4,"b",6),S(5),v(),E(6,ZM,2,0,"span",7),E(7,QM,2,0,"span",8),v(),Wc(8,9),v()),t&2){let e=n.$implicit,i=M(2),a=Kt(4);h(5),Ge(e.key),h(),D(i.schema().required?.includes(e.key)?6:-1),h(),D(e.value.deprecated?7:-1),h(),P("ngTemplateOutlet",a)("ngTemplateOutletContext",Zc(5,vy,e.value))}}function eN(t,n){if(t&1&&(y(0,"mat-list"),Xe(1,JM,9,7,"mat-list-item",3,Ye),ys(3,"keyvalue"),v()),t&2){let e=M();h(),Ze(xs(3,0,e.schema().properties||Xc(2,by)))}}function tN(t,n){if(t&1&&(y(0,"span",10),S(1),v()),t&2){let e=M(2).value;h(),ae(" ",e.type.join(", ")," ")}}function nN(t,n){if(t&1&&S(0),t&2){let e=M(2).value;ae(" ",e.type," ")}}function iN(t,n){if(t&1&&(y(0,"span",16),S(1),v()),t&2){let e=M(2).value;h(),ae("(",e.format,")")}}function aN(t,n){if(t&1&&(y(0,"span",10),E(1,tN,2,1,"span",10)(2,nN,1,1),E(3,iN,2,1,"span",16),v()),t&2){let e=M().value,i=M();h(),D(i.Array.isArray(e.type)?1:2),h(2),D(e.format?3:-1)}}function rN(t,n){if(t&1&&(y(0,"span",11)(1,"mat-chip-set")(2,"a",17)(3,"mat-chip"),R(4,"mat-icon",18),S(5),v()()()()),t&2){let e=M().value;h(2),P("href",e.refAnchorUrl,lt),h(3),ae(" ",e.refTitle," ")}}function oN(t,n){if(t&1&&(y(0,"span",10),S(1," array "),y(2,"mat-chip-set")(3,"a",17)(4,"mat-chip"),R(5,"mat-icon",18),S(6),v()()()()),t&2){let e=M().value;h(3),P("href",e.items.refAnchorUrl,lt),h(3),ae(" ",e.items.refTitle," ")}}function sN(t,n){if(t&1&&(y(0,"span",10),S(1),v()),t&2){let e=M().value;h(),ae(" ",e.items.type,"[] ")}}function lN(t,n){if(t&1&&(y(0,"div",12),R(1,"markdown",19),v()),t&2){let e=M().value;h(),P("data",e.description)}}function cN(t,n){if(t&1&&(y(0,"div",6),S(1),v()),t&2){let e=n.$implicit;h(),ae(" ",e," ")}}function dN(t,n){if(t&1&&(y(0,"div",13)(1,"span",20)(2,"i"),S(3,"Example:"),v()(),S(4," \xA0 "),y(5,"span",21),Xe(6,cN,2,1,"div",6,Ye),v()()),t&2){let e=M().value;h(6),Ze(e.example.value.split(`
`))}}function mN(t,n){if(t&1&&(y(0,"span",22),S(1),v()),t&2){let e=n.$implicit;h(),Ge(e||"null")}}function pN(t,n){if(t&1&&(y(0,"div",13)(1,"span",20)(2,"i"),S(3,"Allowed values:"),v()(),S(4," \xA0 "),Xe(5,mN,2,1,"span",22,Ye),v()),t&2){let e=M().value;h(5),Ze(e.enum)}}function uN(t,n){if(t&1&&(y(0,"span",15),R(1,"app-schema-range",23),S(2," items "),v()),t&2){let e=M().value;h(),P("lowerBound",e.minItems)("upperBound",e.maxItems)}}function hN(t,n){if(t&1&&(y(0,"span",15),S(1),v()),t&2){let e=M().value;h(),ae(" Unique items: ",e.uniqueItems?"yes":"no"," ")}}function fN(t,n){if(t&1&&(y(0,"span",15),S(1," pattern: "),y(2,"span",6),S(3),v()()),t&2){let e=M().value;h(3),ae(" ",e.pattern," ")}}function gN(t,n){if(t&1&&(y(0,"span",15),R(1,"app-schema-range",23),S(2," length "),v()),t&2){let e=M().value;h(),P("lowerBound",e.minLength)("upperBound",e.maxLength)}}function bN(t,n){if(t&1&&(y(0,"span",15),R(1,"app-schema-range",24),S(2," value range "),v()),t&2){let e=M().value;h(),P("lowerBound",e.minimum)("upperBound",e.maximum)("lowerBoundInclusive",!e.exclusiveMinimum)("upperBoundInclusive",!e.exclusiveMaximum)}}function vN(t,n){if(t&1&&(y(0,"span",15),S(1),v()),t&2){let e=M().value;h(),ae(" Multiple of ",e.multipleOf," ")}}function _N(t,n){if(t&1&&(E(0,aN,4,2,"span",10),E(1,rN,6,2,"span",11),E(2,oN,7,2,"span",10),E(3,sN,2,1,"span",10),E(4,lN,2,1,"div",12),E(5,dN,8,0,"div",13),E(6,pN,7,0,"div",13),y(7,"span",14),E(8,uN,3,2,"span",15),E(9,hN,2,1,"span",15),E(10,fN,4,1,"span",15),E(11,gN,3,2,"span",15),E(12,bN,3,4,"span",15),E(13,vN,2,1,"span",15),v()),t&2){let e=n.value;D(e?.type!="array"?0:-1),h(),D(e.refTitle?1:-1),h(),D(e?.type=="array"&&e?.items?.refTitle?2:-1),h(),D(e?.type=="array"&&!e?.items?.refTitle?3:-1),h(),D(e.description?.length>0?4:-1),h(),D(e.example?5:-1),h(),D(e.enum&&e.enum.length>0?6:-1),h(2),D(e.minItems!=null||e.maxItems!=null?8:-1),h(),D(e.uniqueItems!=null?9:-1),h(),D(e.pattern?10:-1),h(),D(e.minLength!=null||e.maxLength!=null?11:-1),h(),D(e.minimum!=null||e.maximum!=null?12:-1),h(),D(e.multipleOf!=null?13:-1)}}var ur=class t{schema=gt.required();Array=Array;Object=Object;static \u0275fac=function(e){return new(e||t)};static \u0275cmp=T({type:t,selectors:[["app-schema"]],inputs:{schema:[1,"schema"]},decls:5,vars:3,consts:[["valueContent",""],[1,"schema"],[3,"ngTemplateOutlet","ngTemplateOutletContext"],["lines","99"],["matListItemIcon",""],["matListItemTitle","",1,"key"],[1,"text-console"],[1,"required"],[1,"deprecated"],["matListItemLine","",3,"ngTemplateOutlet","ngTemplateOutletContext"],[1,"type","text-console"],[1,"type"],[1,"description"],[1,"flex"],[1,"flex","flex-wrap"],[1,"attribute"],[1,"format"],[3,"href"],["matChipAvatar","","fontIcon","schema"],[3,"data"],[1,"property-title"],[1,"value-box"],[1,"text-console","value-box"],["lowerBoundInclusive","true","upperBoundInclusive","true",3,"lowerBound","upperBound"],[3,"lowerBound","upperBound","lowerBoundInclusive","upperBoundInclusive"]],template:function(e,i){e&1&&(y(0,"div",1),E(1,XM,1,4,"ng-container",2),E(2,eN,4,3,"mat-list"),v(),Wt(3,_N,14,13,"ng-template",null,0,Rr)),e&2&&(h(),D(i.schema().type!=="object"?1:-1),h(),D(i.Object.keys(i.schema().properties||Xc(2,by)).length>0?2:-1))},dependencies:[Wo,Ko,dy,my,pu,mu,du,_n,ti,Mi,mr,Ot,Lt,Sn,Br,Mn,Dn,wc,jr],styles:[".required[_ngcontent-%COMP%], .deprecated[_ngcontent-%COMP%]{color:red}.description[_ngcontent-%COMP%]{overflow:auto}.property-title[_ngcontent-%COMP%]{min-width:fit-content}.attribute[_ngcontent-%COMP%]{background-color:#805ad5;color:#fff;margin:0 .2em .2em 0;padding:.1em .2em;border-radius:4px}.value-box[_ngcontent-%COMP%]{margin:1px 6px 1px 0;background-color:#eee;border:1px dashed grey;color:#696969;border-radius:4px;padding:0 2px;overflow-wrap:anywhere;white-space:normal;width:fit-content}"]})};var sn=class t{constructor(n){this.el=n}el;static \u0275fac=function(e){return new(e||t)(Oe(X))};static \u0275dir=q({type:t,selectors:[["","appNavigationTarget",""]]})};function yN(t,n){if(t&1&&(y(0,"div",6)(1,"span"),S(2,"Description"),v(),R(3,"markdown",7),v()),t&2){let e=M().$implicit;h(3),P("data",e.description)}}function xN(t,n){if(t&1&&(y(0,"a",9)(1,"mat-chip")(2,"mat-icon",10),S(3),v(),S(4),v()()),t&2){let e=n.$implicit;P("href",e.anchorUrl,lt),h(3),Ge(e.type=="schema"?"schema":"swap_vert"),h(),ae(" ",e.name," ")}}function wN(t,n){if(t&1&&(y(0,"div",3)(1,"span"),S(2,"Used by"),v(),y(3,"mat-chip-set"),Xe(4,xN,5,3,"a",9,Ye),v()()),t&2){let e=M().$implicit;h(4),Ze(e.usedBy)}}function SN(t,n){t&1&&R(0,"br")}function kN(t,n){if(t&1&&(y(0,"article",0)(1,"mat-card")(2,"mat-card-header",1)(3,"mat-card-title"),S(4),v()(),y(5,"mat-card-content")(6,"div",2)(7,"div",3)(8,"span"),S(9,"Name"),v(),y(10,"span",4),S(11),v()(),y(12,"div",3)(13,"span"),S(14,"Type"),v(),y(15,"span")(16,"div",5),S(17),v()()(),E(18,yN,4,1,"div",6),E(19,wN,6,0,"div",3),v(),y(20,"h6"),S(21,"Example"),v(),y(22,"div"),R(23,"app-json",7),v(),y(24,"h6"),S(25,"Properties"),v(),R(26,"app-schema",8),v()(),E(27,SN,1,0,"br"),v()),t&2){let e=n.$implicit,i=n.$index,a=n.$count;P("id",e.anchorIdentifier),h(4),ae(" ",e.title," "),h(7),Ge(e.name),h(6),Ge(e.type),h(),D(e.description?18:-1),h(),D(e.usedBy.length>0?19:-1),h(4),P("data",e.example?.rawValue),h(3),P("schema",e),h(),D(i!==a-1?27:-1)}}var Sc=class t{constructor(n){this.asyncApiService=n}asyncApiService;schemas=ne([]);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(n=>this.schemas.set([...n.components.schemas.values()]))}static \u0275fac=function(e){return new(e||t)(Oe(ut))};static \u0275cmp=T({type:t,selectors:[["app-schemas"]],decls:4,vars:0,consts:[["appNavigationTarget","",3,"id"],[1,"flex","space-between","align-items-baseline"],[1,"table","margin-vertical-1em"],[1,"table-row"],[1,"text-console"],[1,"type-badge"],[1,"table-row","description"],[3,"data"],[3,"schema"],[3,"href"],["matChipAvatar",""]],template:function(e,i){e&1&&(y(0,"h2"),S(1,"Schemas"),v(),Xe(2,kN,28,9,"article",0,Ye)),e&2&&(h(2),Ze(i.schemas()))},dependencies:[Dn,Ot,Lt,ei,sr,cr,dr,lr,_n,ti,Mi,mr,bc,ur,sn],styles:[".table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:first-child{vertical-align:middle}.table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:last-child{word-break:break-word}.type-badge[_ngcontent-%COMP%]{display:inline;background-color:#e0e0e0;border-radius:4px;padding:4px;font-weight:400;font-size:small}"]})};var ts=new Zt("init"),ca=new Zt("");var _y={asyncApiJson:{},contact:{},license:{},title:"",version:""};var ns={ts_type:"object",title:"",name:"",anchorUrl:"",anchorIdentifier:"",usedBy:[]};function CN(t,n){if(t&1){let e=ft();y(0,"a",3),xe("click",function(){at(e);let a=M();return rt(a.download())}),S(1,"AsyncAPI JSON"),v()}}function EN(t,n){if(t&1&&(y(0,"a",5),S(1),v()),t&2){M(2);let e=ya(6);P("href",e.url,lt),h(),ae(" ",e.name," ")}}function DN(t,n){if(t&1&&S(0),t&2){M(2);let e=ya(6);ae(" ",e.name," ")}}function MN(t,n){if(t&1&&(y(0,"mat-chip"),R(1,"mat-icon",4),E(2,EN,2,2,"a",5)(3,DN,1,1),v()),t&2){M();let e=ya(6);h(2),D(e?.url?2:3)}}function NN(t,n){if(t&1&&(y(0,"mat-chip"),R(1,"mat-icon",6),y(2,"a",5),S(3),v()()),t&2){M();let e=ya(8);h(2),P("href",e.url,lt),h(),ae(" ",e.url," ")}}function IN(t,n){if(t&1&&(y(0,"mat-chip"),R(1,"mat-icon",7),y(2,"a",5),S(3),v()()),t&2){M();let e=ya(10);h(2),P("href",e.href,lt),h(),ae(" ",e.name," ")}}function AN(t,n){if(t&1&&(y(0,"p"),R(1,"markdown",8),v()),t&2){let e=M();h(),P("data",e.info().description)}}var kc=class t{constructor(n){this.asyncApiService=n}asyncApiService;asyncApiData=void 0;info=ne(_y);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(n=>{this.asyncApiData=n,this.info.set(n.info)})}download(){if(this.asyncApiData===void 0)return!1;let n=JSON.stringify(this.asyncApiData.info.asyncApiJson,null,2),e=new TextEncoder().encode(n),i=new Blob([e],{type:"application/json"}),a=window.URL.createObjectURL(i);return window.open(a),!1}static \u0275fac=function(e){return new(e||t)(Oe(ut))};static \u0275cmp=T({type:t,selectors:[["app-info"]],decls:13,vars:10,consts:[[1,"info-chips"],["matChipAvatar","","fontIcon","download"],["href","javascript:void(0);"],["href","javascript:void(0);",3,"click"],["matChipAvatar","","fontIcon","attribution"],["target","_blank",3,"href"],["matChipAvatar","","fontIcon","link"],["matChipAvatar","","fontIcon","email"],[3,"data"]],template:function(e,i){if(e&1&&(y(0,"h1"),S(1),v(),y(2,"p",0)(3,"mat-chip"),R(4,"mat-icon",1),E(5,CN,2,0,"a",2),v(),vs(6),E(7,MN,4,1,"mat-chip"),vs(8),E(9,NN,4,2,"mat-chip"),vs(10),E(11,IN,4,2,"mat-chip"),v(),E(12,AN,2,1,"p")),e&2){h(),Ir(" ",i.info().title," ",i.info().version?"v"+i.info().version:"",`
`),h(4),D(i.info().asyncApiJson?5:-1),h();let a=_s(i.info().license);h(),D(a?.name?7:-1),h();let r=_s(i.info().contact);h(),D(r.url?9:-1),h();let o=_s(i.info().contact.email);h(),D(o?11:-1),h(),D(i.info().description?12:-1)}},dependencies:[_n,ti,Mi,Ot,Lt,Mn,Dn],styles:[".info-chips[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]{margin-inline-end:8px}"]})};function TN(t,n){if(t&1&&(y(0,"article",1)(1,"mat-card",2)(2,"mat-card-header")(3,"mat-card-title"),S(4),v(),y(5,"span",3)(6,"span",4),S(7),v()()(),y(8,"mat-card-content")(9,"table")(10,"tbody")(11,"tr")(12,"td"),S(13,"Host"),v(),y(14,"td",5),S(15),v()()()()()()()),t&2){let e=n.$implicit;P("id",e.value.anchorIdentifier),h(4),ae(" ",e.key," "),h(3),ae(" ",e.value.protocol," "),h(8),ae(" ",e.value.host," ")}}var Cc=class t{constructor(n){this.asyncApiService=n}asyncApiService;servers=ne(new Map);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(n=>this.servers.set(n.servers))}static \u0275fac=function(e){return new(e||t)(Oe(ut))};static \u0275cmp=T({type:t,selectors:[["app-servers"]],decls:6,vars:2,consts:[[1,"row"],["appNavigationTarget","",1,"width-6-of-12","width-12-of-12-s",3,"id"],["appearance","outlined"],[1,"flex","gap-16","padding-horizontal-1em","height-fix-content"],[1,"badge","protocol-badge"],[1,"text-console"]],template:function(e,i){e&1&&(y(0,"h2"),S(1,"Servers"),v(),y(2,"div",0),Xe(3,TN,16,4,"article",1,Ye),ys(5,"keyvalue"),v()),e&2&&(h(3),Ze(xs(5,0,i.servers())))},dependencies:[Sn,ei,sr,cr,dr,lr,sn,jr],styles:[".badge[_ngcontent-%COMP%]{border-radius:4px;padding:.3em;font-size:smaller;text-transform:uppercase}.protocol-badge[_ngcontent-%COMP%]{background-color:#347aeb;color:#fff}"]})};var Ec=(t,n)=>{if(n.has(t))return n.get(t);var e=t,i=ON.call(t).slice(8,-1);if(i=="Object"){n.set(t,e={});for(var a in t)e[a]=Ec(t[a],n);t[pr]&&(e[pr]=Ec(t[pr],n)),t[sa]&&(e[sa]=t[sa])}else if(i=="Array"){n.set(t,e=[]);for(var r=0,o=t.length;r<o;r++)e[r]=Ec(t[r],n)}return e},Dc=t=>Ec(t,new Map);var Mc=(t,n,e)=>{var i={};for(var a in t)i[a]=t[a],delete t[a];for(var a in i)a==n&&Object.assign(t,e),e.hasOwnProperty(a)||(t[a]=i[a])},yy=(t,n)=>t.alias=t.alias?t.alias+" "+n:n,ON={}.toString;var xy=(t,n,e)=>n.indexOf(t[0])+1||e&&n.indexOf(t[e])+1;var wy=(t=!0,n="()[]{}")=>{let e,i,a=[],r=d=>{d.extensions.matchBrackets=r,d.on("tokenize",l),t&&d.tokens[0]?d.update():l(d.tokens)},o=r.brackets=[],s=r.pairs=[],l=d=>{if(s.length=o.length=i=e=0,c(d,0),t)for(let m=0,u;u=o[m];)yy(u[0],`bracket-${m++in s?"level-"+u[3]%12:"error"}`)},c=(d,m)=>{let u,f=0;for(;u=d[f++];){let g=u.length;if(typeof u!="string"){let b=u.content;if(Array.isArray(b))c(b,m);else if((u.alias||u.type)=="punctuation"){let _=xy(b,n,g-1),x=_%2;if(_){if(o[e]=[u,m,m+g,i,b,!!x],x)a[i++]=[e,_+1];else for(let w=i,C;C=a[--w];)if(_==C[1]){s[s[e]=C[0]]=e,o[e][3]=i=w;break}e++}}}m+=g}};return r};var hr=(t,n)=>n?t.lastIndexOf(`
`,n-1)+1:0,is=(t,n)=>(n=t.indexOf(`
`,n))+1?n:t.length,Sy=(t,n,e,i)=>(rn(t,n,e,i),()=>t.removeEventListener(n,e,i)),Ni=(t,n,e,i)=>Sy(t.textarea,n,e,i),ky=(t,n)=>parseFloat(getComputedStyle(t)[n]);var X5=new Set("xml,rss,atom,jsx,tsx,xquery,xeora,xeoracube,actionscript".split(","));var da,fr=t=>t.replace(/[$+?|.^*()[\]{}\\]/g,"\\$&"),Cy=(t,n)=>t.slice(hr(t,n),n),gr=(t,n,e=n)=>[t.slice(n=hr(t,n),e=is(t,e)).split(`
`),n,e],Cu=(t,n,e=0,i=e,a=t.getSelection()[0])=>{let r=t.value,o=t.lines[yc(r,0,a)],s=on.createTreeWalker(o,5),l=s.lastChild(),c=is(r,a)+1-a-l.length;for(;-c<=i&&(l=s.previousNode());)if(!l.lastChild&&(c-=l.length||0,c<=e)){for(;l!=o;l=l.parentNode)if(l.matches?.(n))return l}},Eu=(t,n)=>Cu(t,"[class*=language-]",0,0,n)?.className.match(/language-(\S*)/)[1]||t.options.language,Pn=(t,n,e,i,a,r)=>{if(t.options.readOnly)return;da=t.getSelection(),i??=e;let o=t.textarea,s=t.value,l=ku&&!s[i??da[1]]&&/\n$/.test(n)&&/^$|\n$/.test(s),c;t.focused||o.focus(),e!=null&&o.setSelectionRange(e,i),a!=null&&(c=t.on("update",()=>{o.setSelectionRange(a,r??a,da[2]),c()})),Su||o.dispatchEvent(new InputEvent("beforeinput",{data:n})),ku||Su?(l&&(o.selectionEnd--,n=n.slice(0,-1)),Su&&(n+=`
`),on.execCommand(n?"insertHTML":"delete",!1,vu(n,/</g,"&lt;")),l&&o.selectionStart++):on.execCommand(n?"insertText":"delete",!1,n),da=0},Du=(t,n,e=n,i)=>{let a=t.textarea,r=Sy(a,"focus",o=>{let s=o.relatedTarget;s?s.focus():a.blur()});a.setSelectionRange(n,e,i),r(),a.dispatchEvent(new Event("selectionchange"))},Ey=on?navigator.userAgent:"",xn=on?/Mac|iPhone|iP[ao]d/.test(navigator.platform):!1,ku=/Chrome\//.test(Ey),Su=!ku&&/AppleWebKit\//.test(Ey),Mu=t=>t.altKey+t.ctrlKey*2+t.metaKey*4+t.shiftKey*8,Dy=(t,n)=>t.lines[0].append(n);var My=()=>t=>{let n,e=[],i=()=>{let r=t.extensions.matchBrackets,[o,s]=t.getSelection();if(r){let l=r.brackets,c=r.pairs,d,m;if(t.focused&&o==s){for(let u=0,f;f=l[++u];)if(!f[5]&&f[2]>=s&&l[c[u]]?.[1]<=s){d=l[c[u]],m=f;break}}m!=n&&(a(),m?(e=[d,m].map(u=>Cu(t,".punctuation",0,-1,u[1])),e[0]!=e[1]&&d[2]==m[1]&&(e[0].textContent+=e[1].textContent,e[1].textContent="",e[1]=e[0]),a(!0)):e=[]),n=m}},a=r=>e.forEach(o=>o.classList.toggle("active-bracket",!!r));Ni(t,"focus",i),Ni(t,"blur",i),t.on("selectionChange",i)};var Nc=xn?4:2;var as=!1;var ma=t=>t.search(/\S|$/),Ou=({options:{insertSpaces:t=!0,tabSize:n}})=>[t?" ":"	",t?n||2:1],pa=t=>!t.options.readOnly&&!t.extensions.cursor?.scrollIntoView(),Ny=(t,n,e,i,a,r,o)=>{let s=e.join(`
`);if(s==n.join(`
`))return;let l=n.length-1,c=e[l],d=n[l],m=d.length-c.length,u=e[0].length-n[0].length,f=i+ma((u<0?e:n)[0]),g=a-d.length+ma(m>0?c:d),b=i-a+s.length+m,_=f>r?r:Math.max(f,r+u),x=o+i-a+s.length;Pn(t,s,i,a,_,o<g?x+m:Math.max(g+b,x))},rs=(t,n)=>{let[e,i]=t.getSelection(),[a,r,o]=gr(t.value,e,i),[s,l]=Ou(t);return Ny(t,a,a.map(n?c=>c.slice(ma(c)?l-ma(c)%l:0):c=>c&&s.repeat(l-ma(c)%l)+c),r,o,e,i),pa(t)},Iy=(t,n)=>{let[e,i]=Ou(t);return Pn(t,e.repeat(i-(n-hr(t.value,n))%i)),pa(t)},Ac=(t,n)=>{let e=t.getSelection(),i=t.value;n&&(e[0]=e[1]=is(i,e[1]));let[a,r]=Ou(t),[o,s]=e,l=xc[Eu(t,o)]?.autoIndent,c=Math.floor(ma(Cy(i,o))/r)*r,d=l?.[0]?.(e,i,t)?r:0,m=l?.[1]?.(e,i,t),u=`
`+a.repeat(c+d)+(m?`
`+a.repeat(c):"");if(u[1]||i[s])return Pn(t,u,o,s,o+c+d+1),pa(t)},Nu=(t,n)=>{let[e,i]=t.getSelection(),a=t.value,r=n?hr(a,e)-1:e,o=n?i:a.indexOf(`
`,i)+1;if(r>-1&&o>0){let[s,l,c]=gr(a,r,o),d=s[n?"shift":"pop"](),m=(d.length+1)*(n?-1:1);s[n?"push":"unshift"](d),Pn(t,s.join(`
`),l,c,e+m,i+m)}return pa(t)},Iu=(t,n)=>{let[e,i]=t.getSelection(),a=t.value,[r,o,s]=gr(a,e,i),l=r.join(`
`),c=n?0:l.length+1;return Pn(t,l+`
`+l,o,s,e+c,i+c),pa(t)},Au=(t,n)=>(t.container.scrollBy(0,ky(t.container,"lineHeight")*(n?-1:1)),!0),Ay=t=>{let[n,e,i]=t.getSelection(),a=t.value,[r,o,s]=gr(a,n,e),l=i>"f"?e-s+r.pop().length:n-o,c=is(a,s+1)-s-1;return Pn(t,"",o-!!o,s+!o,o+Math.min(l,c)),pa(t)},Tu=(t,n)=>{let[e,i]=t.getSelection(),a=t.value,r=n?e:hr(a,e),o=xc[Eu(t,r)]||{},{line:s,block:l}=o.getComments?.(t,r,a)||o.comments||{},[c,d,m]=gr(a,e,i),u=c.length-1;if(n){if(l){let[f,g]=l,b=a.slice(e,i),_=a.slice(0,e).search(fr(f)+" ?$");_+1&&RegExp("^ ?"+fr(g)).test(a.slice(i))?Pn(t,b,_,i+(a[i]==" ")+g.length,_,_+i-e):Pn(t,`${f} ${b} ${g}`,e,i,e+f.length+1,i+f.length+1)}}else if(s){let f=fr(s),g=RegExp(`^\\s*(${f} ?|$)`),b=RegExp(f+" ?"),_=!/\S/.test(a.slice(d,m));Ny(t,c,c.map(!_&&c.every(x=>g.test(x))?x=>x.replace(b,""):x=>_||/\S/.test(x)?x.replace(/(?!\s)/,s+" "):x),d,m,e,i)}else if(l){let[f,g]=l,b=c[0],_=ma(b),x=b.startsWith(f,_)&&c[u].endsWith(g);c[0]=b.replace(x?RegExp(fr(f)+" ?"):/(?!\s)/,x?"":f+" ");let w=c[0].length-b.length;c[u]=x?c[u].replace(RegExp(` ?${fr(g)}$`),""):c[u]+" "+g;let C=c.join(`
`),F=_+d,A=F>e?e:Math.max(e+w,F),Q=F>i-(e!=i)?i:Math.min(Math.max(F,i+w),d+C.length);Pn(t,C,d,m,A,Math.max(A,Q))}return l||s&&!n?pa(t):!1},RN={Tab(t){if(!as){let[n,e]=t.getSelection();return n==e?Iy(t,n):rs(t)}},"8+Tab":t=>!as&&rs(t,!0),"1+ArrowDown":t=>Nu(t),"1+ArrowUp":t=>Nu(t,!0),"9+ArrowDown":t=>Iu(t),"9+ArrowUp":t=>Iu(t,!0),Enter:t=>Ac(t),"8+Enter":t=>Ac(t),"Mod+Enter":t=>Ac(t,!0),"Mod+]":t=>rs(t),"Mod+[":t=>rs(t,!0),"8+Mod+k":Ay,"Mod+/":t=>Tu(t),"9+a":t=>Tu(t,!0),[xn?"10+m":"2+m"]:()=>(as=!as,!0),[`2+${xn?"Page":"Arrow"}Down`]:t=>Au(t),[`2+${xn?"Page":"Arrow"}Up`]:t=>Au(t,!0)};var PN=[["2+ ","Trigger suggestion"],["mod+i","Trigger suggestion"],...xn?[["1+Escape","Trigger suggestion"]]:[],["2+ ","Toggle suggestion documentation"],["mod+i","Toggle suggestion documentation"],["Tab","Insert suggestion"],["Enter","Insert suggestion"],["Escape","Close completion widget"],["Escape","Clear tab stops"],["Tab","Select next tab stop"],["8+Tab","Select previous tab stop"],["ArrowUp","Select previous suggestion"],["ArrowDown","Select next suggestion"],["PageUp","Select first visible suggestion"],["PageDown","Select last visible suggestion"]],Ic=xn?5:1,FN=[["mod+f","Start search"],[xn?"5+f":"2+h","Start replacing"],["mod+g","Find next match"],["mod+8+g","Find previous match"],["f3","Find next match"],["8+f3","Find previous match"],["Enter","Select next match"],["8+Enter","Select previous match"],["Escape","Close search widget"],["Enter","Replace match"],[`${xn?4:3}+Enter`,"Replace all matches"],[Ic+"+r","Toggle regex search"],[Ic+"+p","Toggle case preservation"],[Ic+"+w","Toggle whole word search"],[Ic+"+l","Toggle find in selection"]];var Ru=(t=999)=>{let n=0,e,i,a=!1,r,o,s,l,c,d,m=[],u=_=>{_>=t&&(_--,m.shift()),m.splice(n=_,t,[e.value,d(),d()])},f=_=>{m[_]&&(c.value=m[_][0],c.setSelectionRange(...m[_][_<n?2:1]),e.update(),e.extensions.cursor?.scrollIntoView(),n=_,i=!1)},g=(_,x)=>{_.extensions.history=g,e=_,d=_.getSelection,c||u(0),c=_.textarea,_.on("selectionChange",()=>{i=a,a=!1}),Ni(_,"beforeinput",w=>{let C=w.data,F=w.inputType,A=w.timeStamp;/history/.test(F)?(f(n+(F[7]=="U"?-1:1)),la(w)):(l=i&&(r==F||A-s<99&&F.slice(-4)=="Drop")&&!da&&(C!=" "||o==C))||(m[n][2]=da||d()),a=!0,o=C,s=A,r=F}),Ni(_,"input",()=>u(n+!l)),Ni(_,"keydown",w=>{if(!x.readOnly){let C=Mu(w),F=w.keyCode,A=C==Nc&&F==90,Q=C==Nc+8&&F==90||!xn&&C==Nc&&F==89;A?(f(n-1),la(w)):Q&&(f(n+1),la(w))}}),_.addExtensions({update(){_.value!=c.value&&b()}})},b=g.clear=()=>{u(0),i=!1};return g.has=_=>n+_ in m,g.go=_=>f(n+_),g};var LN=wu('<div style=display:flex;align-items:flex-start;justify-content:flex-end><button type=button dir=ltr style=display:none class=pce-copy><svg width=1.2em aria-hidden=true viewBox="0 0 16 16" overflow=visible stroke-linecap=round fill=none stroke=currentColor><rect x=4 y=4 width=11 height=11 rx=1 /><path d="m12 2a1 1 0 00-1-1H2A1 1 0 001 2v9a1 1 0 001 1">'),Ty=(t="Copy",n="Copied!")=>e=>{let i=LN(),a=i.firstChild,r=o=>a.setAttribute("aria-label",o);rn(a,"click",()=>{r(n),navigator.clipboard?.writeText(e.extensions.codeFold?.fullCode??e.value)||(e.textarea.select(),on.execCommand("copy"),Du(e,0))}),rn(a,"pointerenter",()=>r(t)),r(t),Dy(e,i)};var br=/\/\/.*|\/\*[^]*?(?:\*\/|$)/g;var Tc=/[()[\]{}.,:;]/,vr=/\b(?:false|true)\b/,Pu={punctuation:/\./};ye.webmanifest=ye.json={property:/"(?:\\.|[^\\\n"])*"(?=\s*:)/g,string:/"(?:\\.|[^\\\n"])*"/g,comment:br,number:/-?\b\d+(?:\.\d+)?(?:e[+-]?\d+)?\b/i,operator:/:/,punctuation:/[[\]{},]/,boolean:vr,null:{pattern:/\bnull\b/,alias:"keyword"}};var os=(t,n)=>t.replace(/<(\d+)>/g,(e,i)=>`(?:${n[+i]})`),_r=(t,n,e)=>RegExp(os(t,n),e);var Fu=/[*&][^\s[\]{},]+/,Lu=/!(?:<[\w%#;/?:@&=$,.!~*'()[\]+-]+>|(?:[a-zA-Z\d-]*!)?[\w%#;/?:@&=$.~*'()+-]+)?/,Bu=`(?:${Lu.source}(?:[ 	]+${Fu.source})?|${Fu.source}(?:[ 	]+${Lu.source})?)`,BN=os("(?:[^\\s\0-\\x08\\x0e-\\x1f!\"#%&'*,:>?@[\\]{}`|\\x7f-\\x84\\x86-\\x9f\\ud800-\\udfff\\ufffe\\uffff-]|[?:-]<0>)(?:[ 	]*(?:(?![#:])<0>|:<0>))*",["[^\\s\0-\\x08\\x0e-\\x1f,[\\]{}\\x7f-\\x84\\x86-\\x9f\\ud800-\\udfff\\ufffe\\uffff]"]),Oy=`"(?:\\\\.|[^\\\\
"])*"|'(?:\\\\.|[^\\\\
'])*'`,ss=(t,n)=>_r(`([:,[{-]\\s*(?:\\s<0>[ 	]+)?)<1>(?=[ 	]*(?:$|,|\\]|\\}|(?:
\\s*)?#))`,[Bu,t],n);ye.yml=ye.yaml={scalar:{pattern:_r(`([:-]\\s*(?:\\s<0>[ 	]+)?[|>])[ 	]*(?:(
[ 	]+)\\S.*(?:\\2.+)*)`,[Bu]),lookbehind:!0,alias:"string"},comment:/#.*/,key:{pattern:_r(`((?:^|[:,[{
?-])[ 	]*(?:<0>[ 	]+)?)<1>(?=\\s*:\\s)`,[Bu,"(?:"+BN+"|"+Oy+")"],"g"),lookbehind:!0,alias:"atrule"},directive:{pattern:/(^[ 	]*)%.+/m,lookbehind:!0,alias:"important"},datetime:{pattern:ss("\\d{4}-\\d\\d?-\\d\\d?(?:[tT]|[ 	]+)\\d\\d?:\\d\\d:\\d\\d(?:\\.\\d*)?(?:[ 	]*(?:Z|[+-]\\d\\d?(?::\\d\\d)?))?|\\d{4}-\\d\\d-\\d\\d|\\d\\d?:\\d\\d(?::\\d\\d(?:\\.\\d*)?)?","m"),lookbehind:!0,alias:"number"},boolean:{pattern:ss("false|true","im"),lookbehind:!0,alias:"important"},null:{pattern:ss("null|~","im"),lookbehind:!0,alias:"important"},string:{pattern:ss(Oy,"mg"),lookbehind:!0},number:{pattern:ss("[+-]?(?:0x[a-f\\d]+|0o[0-7]+|(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:e[+-]?\\d+)?|\\.inf|\\.nan)","im"),lookbehind:!0},tag:Lu,important:Fu,punctuation:/---|[:[\]{},|>?-]|\.{3}/};var Oc=(t,n)=>({pattern:RegExp("(^(?:"+t+"):[ 	]*)\\S[^]*","i"),lookbehind:!0,alias:n&&"language-"+n,inside:n}),Ry=ye.http={"request-line":{pattern:/^(?:CONNECT|DELETE|GET|HEAD|OPTIONS|PATCH|POST|PRI|PUT|SEARCH|TRACE)\s(?:https?:\/)?\/\S*\sHTTP\/[\d.]+/m,inside:{method:{pattern:/^\w+/,alias:"property"},"request-target":{pattern:/^(\s)[h/]\S*/,lookbehind:!0,alias:"url",inside:"uri"},"http-version":{pattern:/(?!^)\S+/,alias:"property"}}},"response-status":{pattern:/^HTTP\/[\d.]+ \d+ .+/m,inside:{"http-version":{pattern:/^\S+/,alias:"property"},"status-code":{pattern:/^( )\d+(?= )/,lookbehind:!0,alias:"number"},"reason-phrase":{pattern:/(?!^).+/,alias:"string"}}}};["application/javascript","application/json","application/xml","text/xml","text/html","text/css","text/plain"].forEach(t=>{var n=t.split("/")[1],e=t[10]&&!n[4]?"(?:"+t+"|\\w+/(?:[\\w.-]+\\+)+"+n+"(?![\\w.+-]))":t;Ry[t.replace("/","-")]={pattern:RegExp("(content-type:\\s*"+e+`(?:;.*)?(?:
[\\w-].*)*
)[^\\w 	-][^]*`,"i"),lookbehind:!0,alias:"language-"+n,inside:n=="json"?ye.json||"js":n}});Ry.header={pattern:/^[\w-]+:.+(?:\n[ 	].+)*/m,inside:{"header-value":[Oc("Content-Security-Policy","csp"),Oc("Public-Key-Pins(?:-Report-Only)?","hpkp"),Oc("Strict-Transport-Security","hsts"),Oc("[^:]+")],"header-name":{pattern:/^[^:]+/,alias:"keyword"},punctuation:/^:/}};var ju=/\b(?:abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|exports|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|interface|long|module|native|new|non-sealed|null|opens?|package|permits|private|protected|provides|public|record(?!\s*[()[\]{}%~.,:;?%&|^=<>/*+-])|requires|return|sealed|short|static|strictfp|super|switch|synchronized|this|throws?|to|transient|transitive|try|uses|var|void|volatile|while|with|yield)\b/,ls="(?:[a-z]\\w*\\s*\\.\\s*)*(?:[A-Z]\\w*\\s*\\.\\s*)*",Vu={pattern:/^[a-z]\w*(?:\s*\.\s*[a-z]\w*)*(?:\s*\.)?/,inside:Pu},$u={namespace:Vu,punctuation:/\./},Py={pattern:RegExp(`(^|[^\\w.])${ls}[A-Z](?:[\\d_A-Z]*[a-z]\\w*)?\\b`),lookbehind:!0,inside:$u};ye.java={"doc-comment":{pattern:/\/\*\*(?!\/)[^]*?(?:\*\/|$)/g,alias:"comment",inside:"javadoc"},comment:br,"triple-quoted-string":{pattern:/"""[ 	]*\n(?:\\.|[^\\])*?"""/g,alias:"string"},char:/'(?:\\.|[^\\\n']){1,6}'/g,string:{pattern:/(^|[^\\])"(?:\\.|[^\\\n"])*"/g,lookbehind:!0},annotation:{pattern:/(^|[^.])@\w+(?:\s*\.\s*\w+)*/,lookbehind:!0,alias:"punctuation"},generics:{pattern:/<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&))*>)*>)*>)*>/,inside:{"class-name":Py,keyword:ju,punctuation:/[().,:<>]/,operator:/[?&|]/}},import:[{pattern:RegExp(`(\\bimport\\s+)${ls}(?:[A-Z]\\w*|\\*)(?=\\s*;)`),lookbehind:!0,inside:{namespace:Vu,punctuation:/\./,operator:/\*/,"class-name":/\w+/}},{pattern:RegExp(`(\\bimport\\s+static\\s+)${ls}(?:\\w+|\\*)(?=\\s*;)`),lookbehind:!0,alias:"static",inside:{namespace:Vu,static:/\b\w+$/,punctuation:/\./,operator:/\*/,"class-name":/\w+/}}],namespace:{pattern:RegExp(`(\\b(?:exports|import(?:\\s+static)?|module|opens?|package|provides|requires|to|transitive|uses|with)\\s+)(?!${ju.source})[a-z]\\w*(?:\\.[a-z]\\w*)*\\.?`),lookbehind:!0,inside:Pu},"class-name":[Py,{pattern:RegExp(`(^|[^\\w.])${ls}[A-Z]\\w*(?=\\s+\\w+\\s*[;,=()]|\\s*(?:\\[[\\s,]*\\]\\s*)?::\\s*new\\b)`),lookbehind:!0,inside:$u},{pattern:RegExp(`(\\b(?:class|enum|extends|implements|instanceof|interface|new|record|throws)\\s+)${ls}[A-Z]\\w*\\b`),lookbehind:!0,inside:$u}],keyword:ju,boolean:vr,function:{pattern:/\b\w+(?=\()|(::\s*)[a-z_]\w*/,lookbehind:!0},number:/\b0b[01][01_]*l?\b|\b0x(?:\.[a-f\d_p+-]+|[a-f\d_]+(?:\.[a-f\d_p+-]+)?)\b|(?:\b\d[\d_]*(?:\.[\d_]*)?|\B\.\d[\d_]*)(?:e[+-]?\d[\d_]*)?[dfl]?/i,constant:/\b[A-Z][A-Z_\d]+\b/,operator:{pattern:/(^|[^.])(?:<<=?|>>>?=?|->|--|\+\+|&&|\|\||::|[?:~]|[%&|^!=<>/*+-]=?)/m,lookbehind:!0},punctuation:Tc};var zu={"interpolation-punctuation":{pattern:/^.\{?|\}$/g,alias:"punctuation"},expression:{pattern:/[^]+/}};zu.expression.inside=ye.kts=ye.kt=ye.kotlin={"string-literal":[{pattern:/"""(?:[^$]|\$(?:(?!\{)|\{[^{}]*\}))*?"""/,alias:"multiline",inside:{interpolation:{pattern:/\$(?:[a-z_]\w*|\{[^{}]*\})/i,inside:zu},string:/[^]+/}},{pattern:/"(?:\\.|[^\\\n"$]|\$(?:(?!\{)|\{[^{}]*\}))*"/,alias:"singleline",inside:{interpolation:{pattern:/((?:^|[^\\])(?:\\\\)*)\$(?:[a-z_]\w*|\{[^{}]*\})/i,lookbehind:!0,inside:zu},string:/[^]+/}}],char:/'(?:[^\\\n']|\\(?:.|u[a-fA-F\d]{0,4}))'/g,comment:br,annotation:{pattern:/\B@(?:\w+:)?(?:[A-Z]\w*|\[[^\]]+\])/,alias:"builtin"},keyword:{pattern:/(^|[^.])\b(?:abstract|actual|annotation|as|break|by|catch|class|companion|const|constructor|continue|crossinline|data|do|dynamic|else|enum|expect|external|final|finally|for|fun|get|if|import|in|infix|init|inline|inner|interface|internal|is|lateinit|noinline|null|object|open|operator|out|override|package|private|protected|public|reified|return|sealed|set|super|suspend|tailrec|this|throw|to|try|typealias|val|var|vararg|when|where|while)\b/,lookbehind:!0},boolean:vr,label:{pattern:/\b\w+@|@\w+/,alias:"symbol"},function:{pattern:/(?:`[^\n`]+`|\b\w+)(?=\s*\()|(\.)(?:`[^\n`]+`|\w+)(?=\s*\{)/g,lookbehind:!0},number:/\b(?:0[xX][a-fA-F\d]+(?:_[a-fA-F\d]+)*|0[bB][01]+(?:_[01]+)*|\d+(?:_\d+)*(?:\.\d+(?:_\d+)*)?(?:[eE][+-]?\d+(?:_\d+)*)?[fFL]?)\b/,operator:/--|\+\+|&&|\|\||->|[!=]==|!!|[%!=<>/*+-]=?|[?:]:?|\.\.|\b(?:and|inv|shl|u?shr|x?or)\b/,punctuation:Tc};var Hu=[{pattern:/&[a-z\d]{1,8};/i,alias:"named-entity"},/&#x?[a-f\d]{1,8};/i],Fy=/<!--(?:(?!<!--)[^])*?-->/g,Ly={pattern:/<\/?(?!\d)[^\s/=>$<%]+(?:\s(?:\s*[^\s/=>]+(?:\s*=\s*(?!\s)(?:"[^"]*"|'[^']*'|[^\s"'=>]+(?=[\s>]))?|(?=[\s/>])))+)?\s*\/?>/g,inside:{punctuation:/^<\/?|\/?>$/,tag:{pattern:/^\S+/,inside:{namespace:/^[^:]+:/}},"attr-value":[{pattern:/(=\s*)(?:"[^"]*"|'[^']*'|[^\s"'>]+)/g,lookbehind:!0,inside:{punctuation:/^["']|["']$/g,entity:Hu}}],"attr-equals":/=/,"attr-name":{pattern:/\S+/,inside:{namespace:/^[^:]+:/}}}};ye.rss=ye.atom=ye.ssml=ye.xml={comment:Fy,prolog:/<\?[^]+?\?>/g,doctype:{pattern:/<!DOCTYPE(?:[^>"'[\]]|"[^"]*"|'[^']*')+(?:\[(?:[^<"'\]]|"[^"]*"|'[^']*'|<(?!!--)|<!--(?:[^-]|-(?!->))*-->)*\]\s*)?>/gi,inside:{"internal-subset":{pattern:/(\[)[^]+(?=\]\s*>$)/,lookbehind:!0,inside:"xml"},string:/"[^"]*"|'[^']*'/,punctuation:/^<!|[>[\]]/,"doctype-tag":/^DOCTYPE/i,name:/\S+/}},cdata:/<!\[CDATA\[[^]*?\]\]>/gi,tag:Ly,entity:Hu,"markup-bracket":{pattern:/[()[\]{}]/,alias:"punctuation"}};var Uu=(t,n)=>(t["language-"+n]={pattern:/[^]+/,inside:n},t),By=(t,n)=>({pattern:RegExp(`(<${t}[^>]*>)(?!</${t}>)(?:<!\\[CDATA\\[(?:[^\\]]|\\](?!\\]>))*\\]\\]>|(?!<!\\[CDATA\\[)[^])+?(?=</${t}>)`,"gi"),lookbehind:!0,inside:Uu({"included-cdata":{pattern:/<!\[CDATA\[[^]*?\]\]>/i,inside:Uu({cdata:/^<!\[CDATA\[|\]\]>$/i},n)}},n)}),jy=(t,n,e=t)=>({pattern:RegExp(`([\\s"']${t}\\s*=\\s*)(?:"[^"]*"|'[^']*'|[^\\s>]+)`,"gi"),lookbehind:!0,alias:e,inside:Uu({punctuation:/^["']|["']$/g},n)}),Vy=ye.svg=ye.mathml=ye.html=ye.markup=Dc(ye.xml);Vy.tag.inside["attr-value"].unshift(jy("style","css"),jy("on[a-z]+","javascript","script"));Mc(Vy,"cdata",{style:By("style","css"),script:By("script","javascript")});var jN=[`(?:\\\\.|[^\\\\
]|
(?!
))`],Rc=t=>_r(`((?:^|[^\\\\])(?:\\\\\\\\)*)(?:${t})`,jN,"g"),qu=/(?:\\.|``(?:[^\n`]|`(?!`))+``|`[^\n`]+`|[^\\\n|`])+/,$y=os(`\\|?<0>(?:\\|<0>)+\\|?(?:
|(?![\\s\\S]))`,[qu.source]),VN=`\\|?[ 	]*:?-{3,}:?[ 	]*(?:\\|[ 	]*:?-{3,}:?[ 	]*)+\\|?
`,yr=ye.md=ye.markdown=Dc(ye.html);Mc(yr,"prolog",{"front-matter-block":{pattern:/(^(?:\s*\n)?)---(?!.)[^]*?\n---(?!.)/g,lookbehind:!0,inside:{punctuation:/^---|---$/,"front-matter":{pattern:/\S(?:[^]*\S)?/,alias:"language-yaml",inside:"yaml"}}},blockquote:{pattern:/^>(?:[ 	]*>)*/m,alias:"punctuation"},table:{pattern:RegExp("^"+$y+VN+"(?:"+$y+")*","m"),inside:{"table-header-row":{pattern:/^.+/,inside:{"table-header":{pattern:qu,alias:"important",inside:yr},punctuation:/\|/}},"table-data-rows":{pattern:/(.+\n)[^]+/,lookbehind:!0,inside:{"table-data":{pattern:qu,inside:yr},punctuation:/\|/}},"table-line":{pattern:/.+/,inside:{punctuation:/\S+/}}}},"code-snippet":{pattern:/(^|[^\\`])(`+)[^\n`](?:|.*?[^\n`])\2(?!`)/g,lookbehind:!0,alias:"code keyword"},code:[{pattern:/(^[ 	]*\n)(?:    |	).+(?:\n(?:    |	).+)*/gm,lookbehind:!0,alias:"keyword"},{pattern:/^(```+)[^`][^]*?^\1`*$/gm,inside:{punctuation:/^`+|`+$/,"code-language":/^.+/,"code-block":/(?!^)[^]+(?=\n)/,[sa](t,n){var e=bu(t,n),i;return e[5]&&(i=(/[a-z][\w-]*/i.exec(e[1].content.replace(/\b#/g,"sharp").replace(/\b\+\+/g,"pp"))||[""])[0].toLowerCase(),e[3].alias="language-"+i,(n=ye[i])&&(e[3].content=Jo(e[3].content,n))),e}}}],title:[{pattern:/\S.*\n(?:==+|--+)(?=[ 	]*$)/gm,alias:"important",inside:{punctuation:/=+$|-+$/}},{pattern:/(^\s*)#.+/gm,lookbehind:!0,alias:"important",inside:{punctuation:/^#+|#+$/}}],hr:{pattern:/(^\s*)([*-])(?:[ 	]*\2){2,}(?=\s*$)/m,lookbehind:!0,alias:"punctuation"},list:{pattern:/(^\s*)(?:[*+-]|\d+\.)(?=[ 	].)/gm,lookbehind:!0,alias:"punctuation"},"url-reference":{pattern:/!?\[[^\]]+\]:[ 	]+(?:\S+|<(?:\\.|[^\\>])+>)(?:[ 	]+(?:"(?:\\.|[^\\"])*"|'(?:\\.|[^\\'])*'|\((?:\\.|[^\\)])*\)))?/g,inside:{variable:{pattern:/^(!?\[)[^\]]+/,lookbehind:!0},string:/(?:"(?:\\.|[^\\"])*"|'(?:\\.|[^\\'])*'|\((?:\\.|[^\\)])*\))$/,punctuation:/^[[\]!:]|<|>/},alias:"url"},bold:{pattern:Rc("\\b__(?:(?!_)<0>|_(?:(?!_)<0>)+_)+__\\b|\\*\\*(?:(?!\\*)<0>|\\*(?:(?!\\*)<0>)+\\*)+\\*\\*"),lookbehind:!0,inside:{content:{pattern:/(^..)[^]+(?=..)/,lookbehind:!0,inside:{}},punctuation:/../}},italic:{pattern:Rc("\\b_(?:(?!_)<0>|__(?:(?!_)<0>)+__)+_\\b|\\*(?:(?!\\*)<0>|\\*\\*(?:(?!\\*)<0>)+\\*\\*)+\\*"),lookbehind:!0,inside:{content:{pattern:/(?!^)[^]+(?=.)/,inside:{}},punctuation:/./}},strike:{pattern:Rc("(~~?)(?:(?!~)<0>)+\\2"),lookbehind:!0,inside:{punctuation:/^~~?|~~?$/,content:{pattern:/[^]+/,inside:{}}}},url:{pattern:Rc('!?\\[(?:(?!\\])<0>)+\\](?:\\([^\\s)]+(?:[ 	]+"(?:\\\\.|[^\\\\"])*")?\\)|[ 	]?\\[(?:(?!\\])<0>)+\\])'),lookbehind:!0,inside:{operator:/^!/,content:{pattern:/(^\[)[^\]]+(?=\])/,lookbehind:!0,inside:{}},variable:{pattern:/(^\][ 	]?\[)[^\]]+(?=\]$)/,lookbehind:!0},url:{pattern:/(^\]\()[^\s)]+/,lookbehind:!0},string:{pattern:/(^[ 	]+)"(?:\\.|[^\\"])*"(?=\)$)/,lookbehind:!0},"markup-bracket":yr["markup-bracket"]}}});["url","bold","italic","strike"].forEach(t=>{["url","bold","italic","strike","code-snippet","markup-bracket"].forEach(n=>{t!=n&&(yr[t].inside.content.inside[n]=yr[n])})});var $N=["editorContainer"],xr=class t{code=xh("");language=gt("markdown");readonly=gt(!1,{transform:n=>n=="true"});editor=void 0;editorContainer;ngAfterViewInit(){this.editorContainer.nativeElement&&(this.editor=this.initEditor())}ngOnChanges(n){n.code.previousValue!==n.code.currentValue&&this.editor?.setOptions({value:n.code.currentValue})}initEditor(){let n=es(this.editorContainer.nativeElement,{value:this.code(),language:this.language(),lineNumbers:!1,wordWrap:!0,readOnly:this.readonly(),onUpdate:e=>{this.code.set(e)}});return n.addExtensions(Ty(),wy(!0),My(),Ru()),this.code.subscribe(()=>{this.editor?.update()}),n}static \u0275fac=function(e){return new(e||t)};static \u0275cmp=T({type:t,selectors:[["app-prism-editor"]],viewQuery:function(e,i){if(e&1&&qe($N,5),e&2){let a;H(a=U())&&(i.editorContainer=a.first)}},inputs:{code:[1,"code"],language:[1,"language"],readonly:[1,"readonly"]},outputs:{code:"codeChange"},features:[pt],decls:2,vars:0,consts:[["editorContainer",""]],template:function(e,i){e&1&&ln(0,"div",null,0)},styles:["[_nghost-%COMP%] > div[_ngcontent-%COMP%]{margin-bottom:.5em}"]})};function zN(t,n){if(t&1&&(y(0,"div",3)(1,"span"),S(2,"Operation description"),v(),R(3,"markdown",17),v()),t&2){let e=M();h(3),P("data",e.operation().description)}}function HN(t,n){if(t&1&&(y(0,"div",3)(1,"span"),S(2,"Message description"),v(),R(3,"markdown",17),v()),t&2){let e=M();h(3),P("data",e.operation().message.description)}}function UN(t,n){if(t&1&&(y(0,"div",3)(1,"span"),S(2,"Content-Type"),v(),y(3,"span",18),S(4),v()()),t&2){let e=M();h(4),Ge(e.operation().message.contentType)}}function qN(t,n){if(t&1&&(y(0,"div",3)(1,"span"),S(2,"Reply to"),v(),y(3,"span")(4,"mat-chip-set")(5,"a",4)(6,"mat-chip"),R(7,"mat-icon",19),S(8),v()()(),S(9," with "),y(10,"mat-chip-set")(11,"a",4)(12,"mat-chip"),R(13,"mat-icon",6),S(14),v()()()()()),t&2){let e=M();h(5),P("href",mi(e.operation().reply.channelAnchorUrl),lt),h(3),ae(" ",e.operation().reply.channelName," "),h(3),P("href",mi(e.operation().reply.messageAnchorUrl),lt),h(3),ae(" ",e.operation().reply.messageName," ")}}function GN(t,n){if(t&1&&(y(0,"a",4)(1,"mat-chip"),R(2,"mat-icon",20),S(3),v()()),t&2){let e=n.$implicit;P("href",mi(e.anchorUrl),lt),h(3),ae(" ",e.name," ")}}function WN(t,n){if(t&1){let e=ft();y(0,"app-prism-editor",22),Or("codeChange",function(a){at(e);let r=M(3);return Tr(r.operationBindingExampleString,a)||(r.operationBindingExampleString=a),rt(a)}),v()}if(t&2){let e=M(3);Ar("code",e.operationBindingExampleString)}}function KN(t,n){if(t&1&&(y(0,"div"),E(1,WN,1,1,"app-prism-editor",21),v()),t&2){let e=M(2);h(),D(e.operationBindingExampleString?1:-1)}}function YN(t,n){if(t&1&&(y(0,"div",0)(1,"div",5)(2,"h6"),S(3,"Operation Binding"),v()(),y(4,"div",5),E(5,KN,2,1,"div"),v()()),t&2){let e=M();h(5),D(e.operation().protocol?5:-1)}}function XN(t,n){if(t&1){let e=ft();y(0,"app-prism-editor",22),Or("codeChange",function(a){at(e);let r=M(2);return Tr(r.messageBindingExampleString,a)||(r.messageBindingExampleString=a),rt(a)}),v()}if(t&2){let e=M(2);Ar("code",e.messageBindingExampleString)}}function ZN(t,n){t&1&&(y(0,"span")(1,"i"),S(2,"none"),v()())}function QN(t,n){if(t&1&&(y(0,"div",0)(1,"div",5)(2,"h6"),S(3,"Message Binding"),v()(),y(4,"div",5)(5,"div"),E(6,XN,1,1,"app-prism-editor",21),E(7,ZN,3,0,"span"),v()()()),t&2){let e=M();h(6),D(e.messageBindingExampleString?6:-1),h(),D(e.messageBindingExampleString?-1:7)}}function JN(t,n){if(t&1&&(y(0,"a",4)(1,"mat-chip"),R(2,"mat-icon",6),S(3),v()()),t&2){let e=M(2);P("href",e.headers().anchorUrl,lt),h(3),ae(" ",e.headers().title," ")}}function eI(t,n){if(t&1&&R(0,"app-schema",7),t&2){let e=M(2);P("schema",e.headers())}}function tI(t,n){if(t&1){let e=ft();y(0,"div",0)(1,"div",5)(2,"h6"),S(3,"Headers"),v(),y(4,"mat-chip-set"),E(5,JN,4,2,"a",4),v(),E(6,eI,1,1,"app-schema",7),v(),y(7,"div",5)(8,"app-prism-editor",23),Or("codeChange",function(a){at(e);let r=M();return Tr(r.headersExample.value,a)||(r.headersExample.value=a),rt(a)}),v()()()}if(t&2){let e=M();h(5),D(e.headers().anchorUrl!==e.initSchema.anchorUrl?5:-1),h(),D(e.headers()?6:-1),h(2),Ar("code",e.headersExample.value)}}function nI(t,n){if(t&1&&R(0,"app-schema",7),t&2){let e=M();P("schema",e.defaultSchema())}}var Pc=class t{constructor(n,e,i,a){this.asyncApiService=n;this.publisherService=e;this.uiService=i;this.snackBar=a}asyncApiService;publisherService;uiService;snackBar;channelName=gt.required();operation=gt.required();initSchema=ns;defaultSchema=ne(ns);defaultExample=ne(ts);originalDefaultExample=ne(ts);exampleLanguage=cn(()=>{let n=this.operation().message.contentType;return n.includes("avro")?"json":n.split("/").pop()||"json"});headers=ne(ns);headersExample=ts;originalHeadersExample=ne(ts);operationBindingExampleString;messageBindingExampleString;isShowBindings=ne(Ne.DEFAULT_SHOW_BINDINGS);isShowHeaders=ne(Ne.DEFAULT_SHOW_HEADERS);canPublish=ne(!1);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(n=>{let e=n.components.schemas,i=this.operation().message.payload;if(i.ts_type==="ref"){let r=i.name.slice(i.name.lastIndexOf("/")+1),o=e.get(r);this.defaultSchema.set(o),this.defaultExample.set(o.example||ca),this.originalDefaultExample.set(o.example||ca)}else this.defaultSchema.set(i),this.defaultExample.set(i.example||ca),this.originalDefaultExample.set(i.example||ca);let a=this.operation().message.headers;a?this.headers.set(e.get(a?.name)):this.headers.set(ns),this.headersExample=this.headers().example||ca,this.originalHeadersExample.set(this.headers().example||ca),this.operationBindingExampleString=new Zt(this.operation().bindings[this.operation().protocol])?.value,this.messageBindingExampleString=this.createBindingExample(this.operation().message.bindings.get(this.operation().protocol))?.value,this.reset(),this.publisherService.canPublish(this.operation().protocol).subscribe(r=>{this.canPublish.set(r)})}),this.uiService.isShowBindings$.subscribe(n=>this.isShowBindings.set(n)),this.uiService.isShowHeaders$.subscribe(n=>this.isShowHeaders.set(n))}createBindingExample(n){if(n==null)return;let e={};return Object.keys(n).forEach(i=>{i!=="bindingVersion"&&(e[i]=this.getExampleValue(n[i]))}),new Zt(e)}getExampleValue(n){if(typeof n=="string")return n;if("examples"in n&&typeof n.examples=="object")return n.examples[0]}reset(){this.defaultExample.set(new Zt(this.originalDefaultExample().rawValue)),this.headersExample=new Zt(this.originalHeadersExample().rawValue)}publish(){let n=this.defaultExample().value,e=this.operation().message.payload.name,i=this.headersExample.value,a=this.messageBindingExampleString;try{let r=i===""?{}:Pd("Unable to convert headers to JSON object (or is empty)",()=>JSON.parse(i||"")),o=a===""?{}:Pd("Unable to convert bindings to JSON object (or is empty)",()=>JSON.parse(a||""));this.publisherService.publish(this.operation().protocol||"not-supported-protocol",this.channelName(),n,e,r,o).subscribe(s=>this.handlePublishSuccess(),s=>this.handlePublishError(s))}catch(r){this.snackBar.open("Unable to create publishing payload: "+r?.message,"ERROR",{duration:3e3})}}handlePublishSuccess(){return this.snackBar.open("Example payload sent to: "+this.channelName(),"PUBLISHED",{duration:3e3})}handlePublishError(n){let e="Publish failed";return n?.status===ge.NOT_FOUND&&(e+=": no publisher was provided for "+this.operation().protocol),this.snackBar.open(e,"ERROR",{duration:4e3})}static \u0275fac=function(e){return new(e||t)(Oe(ut),Oe(ir),Oe(Ne),Oe(co))};static \u0275cmp=T({type:t,selectors:[["app-channel-operation"]],inputs:{channelName:[1,"channelName"],operation:[1,"operation"]},decls:45,vars:15,consts:[[1,"row"],[1,"width-12-of-12","width-12-of-12-s"],[1,"table","margin-vertical-1em"],[1,"table-row"],[3,"href"],[1,"width-6-of-12","width-12-of-12-s"],["matChipAvatar","","fontIcon","schema"],[3,"schema"],[3,"codeChange","code","language"],[1,"flex","space-between"],[1,"flex","gap-8"],["mat-raised-button","",3,"cdkCopyToClipboard"],["fontIcon","content_copy"],["mat-raised-button","",3,"click"],["fontIcon","restart_alt"],["mat-raised-button","",3,"click","disabled"],["fontIcon","send"],[3,"data"],[1,"text-console"],["matChipAvatar","","fontIcon","swap_vert"],["matChipAvatar","","fontIcon","dns"],["language","json","readonly","true",3,"code"],["language","json","readonly","true",3,"codeChange","code"],["language","json",3,"codeChange","code"]],template:function(e,i){e&1&&(y(0,"div",0)(1,"div",1)(2,"div",2)(3,"div",3)(4,"span"),S(5,"Channel"),v(),y(6,"span"),S(7),v()(),E(8,zN,4,1,"div",3),E(9,HN,4,1,"div",3),E(10,UN,5,1,"div",3),E(11,qN,15,6,"div",3),y(12,"div",3)(13,"span"),S(14,"Servers"),v(),y(15,"span")(16,"mat-chip-set"),Xe(17,GN,4,3,"a",4,Ye),v()()()()()(),E(19,YN,6,1,"div",0),E(20,QN,8,2,"div",0),E(21,tI,9,3,"div",0),y(22,"div",0)(23,"div",5)(24,"h6"),S(25,"Payload"),v(),y(26,"mat-chip-set")(27,"a",4)(28,"mat-chip"),R(29,"mat-icon",6),S(30),v()()(),E(31,nI,1,1,"app-schema",7),v(),y(32,"div",5)(33,"app-prism-editor",8),Or("codeChange",function(r){return Tr(i.defaultExample().value,r)||(i.defaultExample().value=r),r}),v(),y(34,"div",9)(35,"div",10)(36,"button",11),R(37,"mat-icon",12),S(38," Copy "),v(),y(39,"button",13),xe("click",function(){return i.reset()}),R(40,"mat-icon",14),S(41," Reset "),v()(),y(42,"button",15),xe("click",function(){return i.publish()}),R(43,"mat-icon",16),S(44," Publish "),v()()()()),e&2&&(h(7),Ge(i.operation().channelName),h(),D(i.operation().description?8:-1),h(),D(i.operation().message.description?9:-1),h(),D(i.operation().message.contentType?10:-1),h(),D(i.operation().reply?11:-1),h(6),Ze(i.operation().servers),h(2),D(i.isShowBindings()?19:-1),h(),D(i.isShowBindings()?20:-1),h(),D(i.isShowHeaders()?21:-1),h(6),P("href",i.operation().message.payload.anchorUrl,lt),h(3),ae(" ",i.operation().message.payload.title," "),h(),D(i.defaultSchema()?31:-1),h(2),Ar("code",i.defaultExample().value),P("language",i.exampleLanguage()),h(3),P("cdkCopyToClipboard",i.defaultExample().value),h(6),P("disabled",!i.canPublish))},dependencies:[Mn,Dn,_n,ti,Mi,mr,Ot,Lt,xr,ur,Uo,$_,Yn,ja],styles:[".table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:first-child{vertical-align:middle}[_nghost-%COMP%]     .mdc-evolution-chip-set__chips{max-width:100%}[_nghost-%COMP%]     .mat-mdc-standard-chip .mdc-evolution-chip__cell--primary, [_nghost-%COMP%]     .mat-mdc-standard-chip .mdc-evolution-chip__action--primary, [_nghost-%COMP%]     .mat-mdc-standard-chip .mat-mdc-chip-action-label{overflow:hidden}"]})};var iI=(t,n)=>({"send-badge":t,"receive-badge":n});function aI(t,n){if(t&1&&(y(0,"div"),R(1,"app-prism-editor",4),v()),t&2){let e=M(2).$implicit,i=M();h(),P("code",mi(i.JSON.stringify(e.bindings,null,2)))}}function rI(t,n){if(t&1&&(y(0,"div",2)(1,"div",3)(2,"h6"),S(3,"Channel Binding"),v()(),y(4,"div",3),E(5,aI,2,2,"div"),v()()),t&2){let e=M().$implicit;h(5),D(e.bindings?5:-1)}}function oI(t,n){if(t&1&&(y(0,"span",7),S(1),v()),t&2){let e=M().$implicit;h(),ae(" ",e.operation.protocol," ")}}function sI(t,n){t&1&&R(0,"br")}function lI(t,n){if(t&1&&(y(0,"mat-card",5)(1,"mat-card-header")(2,"mat-card-title"),S(3),v(),y(4,"span",6),E(5,oI,2,1,"span",7),y(6,"span",8),S(7),v()()(),y(8,"mat-card-content"),R(9,"app-channel-operation",9),v()(),E(10,sI,1,0,"br")),t&2){let e=n.$implicit,i=n.$index,a=n.$count,r=M().$implicit;P("id",e.anchorIdentifier),h(2),fe("data-testid",r.anchorIdentifier),h(),ae(" ",e.operation.message.title," "),h(2),D(e.operation.protocol?5:-1),h(),P("ngClass",xa(9,iI,e.operation.operationType==="send",e.operation.operationType==="receive")),h(),ae(" ",e.operation.operationType," "),h(2),P("channelName",r.name)("operation",e.operation),h(),D(i!==a-1?10:-1)}}function cI(t,n){t&1&&R(0,"br")}function dI(t,n){if(t&1&&(y(0,"article",0)(1,"h3"),R(2,"mat-icon",1),S(3),v(),E(4,rI,6,1,"div",2),Xe(5,lI,11,12,null,null,Ye),E(7,cI,1,0,"br"),v()),t&2){let e=n.$implicit,i=n.$index,a=n.$count,r=M();P("id",e.anchorIdentifier),h(3),ae(" ",e.name),h(),D(r.isShowBindings()?4:-1),h(),Ze(e.operations),h(2),D(i!==a-1?7:-1)}}var Fc=class t{constructor(n,e){this.asyncApiService=n;this.uiService=e}asyncApiService;uiService;channels=ne([]);isShowBindings=ne(Ne.DEFAULT_SHOW_BINDINGS);JSON=JSON;ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(n=>{this.channels.set(n.channels)}),this.uiService.isShowBindings$.subscribe(n=>this.isShowBindings.set(n))}static \u0275fac=function(e){return new(e||t)(Oe(ut),Oe(Ne))};static \u0275cmp=T({type:t,selectors:[["app-channels"]],decls:4,vars:0,consts:[["appNavigationTarget","",3,"id"],["fontIcon","swap_vert"],[1,"row"],[1,"width-6-of-12","width-12-of-12-s"],["language","json","readonly","true",3,"code"],["appearance","outlined","appNavigationTarget","",3,"id"],[1,"flex","gap-16","padding-horizontal-1em","height-fix-content"],[1,"badge","protocol-badge"],[1,"badge",3,"ngClass"],[3,"channelName","operation"]],template:function(e,i){e&1&&(y(0,"h2"),S(1,"Channels"),v(),Xe(2,dI,8,4,"article",0,Ye)),e&2&&(h(2),Ze(i.channels()))},dependencies:[xr,Sn,Lr,ei,sr,cr,dr,lr,Pc,sn,Lt],styles:[".badge[_ngcontent-%COMP%]{border-radius:4px;padding:.3em;font-size:smaller;text-transform:uppercase}.protocol-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-protocol);background-color:var(--%NS%springwolf-badge-color-background-protocol)}.send-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-send);background-color:var(--%NS%springwolf-badge-color-background-send)}.receive-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-receive);background-color:var(--%NS%springwolf-badge-color-background-receive)}"]})};var mI=["scrollableElement"],pI=["*"],Gu=(t,n)=>({selected:t,expanded:n});function uI(t,n){if(t&1&&R(0,"mat-icon",5),t&2){let e=M().$implicit;P("fontIcon",mi(e.icon))}}function hI(t,n){if(t&1&&(y(0,"span"),S(1),v()),t&2){let e=n.$implicit;At(Yc("badge ",e.type,"-badge")),h(),Ge(e.value)}}function fI(t,n){if(t&1&&(y(0,"span"),S(1),v()),t&2){let e=n.$implicit;At(Yc("badge ",e.type,"-badge")),h(),Ge(e.value)}}function gI(t,n){if(t&1&&(y(0,"li",2)(1,"span")(2,"a",6),S(3),v(),Xe(4,fI,2,4,"span",7,Ye),v()()),t&2){let e=n.$implicit;P("ngClass",xa(3,Gu,e.selected,e.expanded)),h(2),P("href",e.href,lt),h(),ae(" ",e.name.join("\u200B")," "),h(),Ze(e.tags)}}function bI(t,n){if(t&1&&(y(0,"li",2)(1,"span")(2,"a",6),S(3),v(),Xe(4,hI,2,4,"span",7,Ye),v(),y(6,"ul"),Xe(7,gI,6,6,"li",2,Ye),v()()),t&2){let e=n.$implicit;P("ngClass",xa(3,Gu,e.selected,e.expanded)),h(2),P("href",e.href,lt),h(),ae(" ",e.name.join("\u200B")," "),h(),Ze(e.tags),h(3),Ze(e.children)}}function vI(t,n){if(t&1&&(y(0,"ul",2)(1,"li")(2,"span"),E(3,uI,1,2,"mat-icon",5),y(4,"b")(5,"a",6),S(6),v()()(),y(7,"ul"),Xe(8,bI,9,6,"li",2,Ye),v()()()),t&2){let e=n.$implicit;P("ngClass",xa(4,Gu,e.selected,e.expanded)),h(3),D(e.icon?3:-1),h(2),P("href",e.href,lt),h(),ae(" ",e.name.join("\u200B")," "),h(2),Ze(e.children)}}var Lc=class t{constructor(n,e){this.asyncApiService=n;this.location=e}asyncApiService;location;scrollableElement;navigationTargets;navigation=ne([]);ngOnInit(){this.location.subscribe(this.scrollToUrlLocation),this.asyncApiService.getAsyncApi().subscribe(n=>{let e=[];e.push({name:["Info"],icon:"info",href:St.BASE_URL+"info"});let i=Array.from(n.servers.keys()).map(o=>({name:this.splitForWordBreaking(o),href:St.BASE_URL+n.servers.get(o).anchorIdentifier,tags:[{type:"protocol",value:n.servers.get(o).protocol}]}));e.push({name:["Servers"],icon:"dns",href:St.BASE_URL+"servers",children:i});let a={name:["Channels & Operations"],icon:"swap_vert",href:St.BASE_URL+"channels",children:[]};n.channels.forEach(o=>{let s=o.operations.map(d=>({name:this.splitForWordBreaking(d.operation.message.title),href:St.BASE_URL+d.anchorIdentifier,tags:[{type:"operation-"+d.operation.operationType,value:d.operation.operationType}]})),l=s.flatMap(d=>d.tags).flatMap(d=>d),c={name:this.splitForWordBreaking(o.name),href:St.BASE_URL+o.anchorIdentifier,tags:this.filterAndSort(l,"value"),children:s};a.children.push(c)}),e.push(a);let r={name:["Schemas"],icon:"schema",href:St.BASE_URL+"schemas",children:[]};n.components.schemas.forEach(o=>{r.children.push({name:this.splitForWordBreaking(o.title),href:St.BASE_URL+""+o.anchorIdentifier})}),e.push(r),this.navigation.set(e),this.scrollToUrlLocation()})}splitForWordBreaking=n=>n.split(/(?<=[.,_/\-])/);filterAndSort(n,e){let i=new Set;return n.filter(r=>{let o=JSON.stringify(r);return i.has(o)?!1:i.add(o)}).sort((r,o)=>r[e]<o[e]?-1:r[e]>o[e]?1:0)}ngAfterViewInit(){this.scrollableElement.nativeElement.addEventListener("scroll",this.updateNavigationSelection)}updateNavigationSelection=()=>{let n="",e=this.scrollableElement.nativeElement.scrollTop;document.querySelectorAll("[appNavigationTarget]").forEach(i=>{let a=i,r=a.offsetTop,o=a.offsetHeight;e>=r&&e<r+o&&(n=St.BASE_URL+""+a.getAttribute("id"))}),this.navigation().forEach(i=>{let a=!1;i.children?.forEach(r=>{let o=!1;r.children?.forEach(s=>{s.selected=n==s.href,s.expanded=s.selected,o=o||s.selected}),r.selected=n==r.href||o,a=a||r.selected,r.children?.forEach(s=>{s.expanded=r.selected})}),i.selected=n==i.href||a,i.children?.forEach(r=>{r.expanded=i.selected}),i.expanded=!0}),this.navigation.set([...this.navigation()])};scrollToUrlLocation=()=>{setTimeout(()=>{document.getElementById(window.location.hash.substring(1))?.scrollIntoView(),this.updateNavigationSelection()},10)};static \u0275fac=function(e){return new(e||t)(Oe(ut),Oe(Sa))};static \u0275cmp=T({type:t,selectors:[["app-sidenav"]],contentQueries:function(e,i,a){if(e&1&&ot(a,sn,5),e&2){let r;H(r=U())&&(i.navigationTargets=r)}},viewQuery:function(e,i){if(e&1&&qe(mI,5),e&2){let a;H(a=U())&&(i.scrollableElement=a.first)}},ngContentSelectors:pI,decls:8,vars:0,consts:[["scrollableElement",""],["mode","side","opened","",1,"sidenav","width-s-hide"],[1,"entry",3,"ngClass"],[1,"width-s-margin-reset"],[2,"overflow-y","auto","height","100%"],[3,"fontIcon"],[3,"href"],[3,"class"]],template:function(e,i){e&1&&(de(),y(0,"mat-sidenav-container")(1,"mat-sidenav",1),Xe(2,vI,10,7,"ul",2,Ye),v(),y(4,"mat-sidenav-content",3)(5,"div",4,0),j(7),v()()()),e&2&&(h(2),Ze(i.navigation()))},dependencies:[Xo,gu,py,fc,Ot,Lt,Sn,Lr],styles:["a[_ngcontent-%COMP%]{color:#373737;text-decoration:none}.sidenav[_ngcontent-%COMP%]   ul[_ngcontent-%COMP%]{list-style-type:none;padding-inline-start:.5em}.sidenav[_ngcontent-%COMP%]   .entry.selected[_ngcontent-%COMP%] > span[_ngcontent-%COMP%] > a[_ngcontent-%COMP%]{font-weight:700;color:var(--%NS%mat-app-text-color)}.sidenav[_ngcontent-%COMP%]   .entry[_ngcontent-%COMP%]:not(.expanded){display:none}.sidenav[_ngcontent-%COMP%]   .entry[_ngcontent-%COMP%] > span[_ngcontent-%COMP%]{display:flex;justify-content:space-between}.sidenav[_ngcontent-%COMP%]   .badge[_ngcontent-%COMP%]{text-transform:uppercase;font-size:smaller;border-radius:.3em;padding:0 .2em;margin:.2em .3em auto}.sidenav[_ngcontent-%COMP%]   .badge.operation-send-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-send);background-color:var(--%NS%springwolf-badge-color-background-send)}.sidenav[_ngcontent-%COMP%]   .badge.operation-receive-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-receive);background-color:var(--%NS%springwolf-badge-color-background-receive)}.sidenav[_ngcontent-%COMP%]   .badge.protocol-badge[_ngcontent-%COMP%]{color:var(--%NS%springwolf-badge-color-protocol);background-color:var(--%NS%springwolf-badge-color-background-protocol)}"]})};function _I(t,n){if(t&1&&(y(0,"button",9),S(1," Group "),v()),t&2){M();let e=Kt(16);P("matMenuTriggerFor",e)}}function yI(t,n){if(t&1){let e=ft();y(0,"button",15),xe("click",function(){let a=at(e).$implicit,r=M();return rt(r.changeGroup(a.value))}),y(1,"mat-icon"),S(2),v(),S(3),v()}if(t&2){let e=n.$implicit,i=M();h(2),Ge(i.isGroup()==e.value?"radio_button_checked":"radio_button_unchecked"),h(),ae(" ",e.viewValue," ")}}var Bc=class t{constructor(n,e,i){this.uiService=n;this.asyncApiService=e;this.assetService=i}uiService;asyncApiService;assetService;groups=ne([]);isGroup=ne(Ne.DEFAULT_GROUP);isShowBindings=ne(Ne.DEFAULT_SHOW_BINDINGS);isShowHeaders=ne(Ne.DEFAULT_SHOW_HEADERS);title=ne("");ngOnInit(){this.assetService.load(),this.uiService.isShowBindings$.subscribe(n=>this.isShowBindings.set(n)),this.uiService.isShowHeaders$.subscribe(n=>this.isShowHeaders.set(n)),this.uiService.isGroup$.subscribe(n=>this.isGroup.set(n)),this.uiService.uiConfig.subscribe(n=>{if(n.groups.length>0){let e=n.groups.map(i=>({value:i.name,viewValue:i.name}));this.groups.set([{value:Ne.DEFAULT_GROUP,viewValue:Ne.DEFAULT_GROUP},...e])}}),this.asyncApiService.getAsyncApi().subscribe(n=>{this.title.set(n.info.title)})}toggleIsShowBindings(){this.uiService.toggleIsShowBindings(!this.isShowBindings())}toggleIsShowHeaders(){this.uiService.toggleIsShowHeaders(!this.isShowHeaders())}changeGroup(n){this.uiService.changeGroup(n)}static \u0275fac=function(e){return new(e||t)(Oe(Ne),Oe(ut),Oe(Ia))};static \u0275cmp=T({type:t,selectors:[["app-header"]],decls:30,vars:5,consts:[["settings","matMenu"],["group","matMenu"],[1,"row","space-between"],["href","https://www.springwolf.dev","target","_blank",1,"flex","flex-column","items-center"],["ngSrc","assets/springwolf-logo.png","alt","Logo","height","1024","width","1024",1,"logo"],[1,"width-s-hide"],[1,"flex","flex-column","items-center"],["mat-button","","data-testid","settings",3,"matMenuTriggerFor"],[2,"color","black"],["mat-menu-item","","data-testid","settings-group-menu",3,"matMenuTriggerFor"],["mat-menu-item",""],["mat-menu-item","","data-testid","settings-bindings",3,"click"],["mat-menu-item","","data-testid","settings-headers",3,"click"],["href","https://github.com/springwolf/springwolf-core","target","_blank"],["ngSrc","assets/github.png","alt","Github","height","1024","width","1024",1,"github"],["mat-menu-item","",3,"click"]],template:function(e,i){if(e&1&&(y(0,"mat-toolbar",2)(1,"span")(2,"a",3),R(3,"img",4),S(4," Springwolf "),v()(),y(5,"h1",5),S(6),v(),y(7,"div",6)(8,"button",7)(9,"mat-icon",8),S(10,"settings"),v(),S(11," Settings "),v(),y(12,"mat-menu",null,0),E(14,_I,2,1,"button",9),y(15,"mat-menu",null,1),Xe(17,yI,4,2,"button",10,Ye),v(),y(19,"button",11),xe("click",function(){return i.toggleIsShowBindings()}),y(20,"mat-icon"),S(21),v(),S(22," Show bindings "),v(),y(23,"button",12),xe("click",function(){return i.toggleIsShowHeaders()}),y(24,"mat-icon"),S(25),v(),S(26," Show headers "),v()(),S(27," \xA0 "),y(28,"a",13),R(29,"img",14),v()()()),e&2){let a=Kt(13);h(6),Ge(i.title()),h(2),P("matMenuTriggerFor",a),h(6),D(0<i.groups().length?14:-1),h(3),Ze(i.groups()),h(4),Ge(i.isShowBindings()?"check_box":"check_box_outline_blank"),h(4),Ge(i.isShowHeaders()?"check_box":"check_box_outline_blank")}},dependencies:[zo,B_,Yn,ja,$o,rr,Vo,L_,Ot,Lt,Lh],styles:[".logo[_ngcontent-%COMP%]{height:3em;width:3em;display:block}.github[_ngcontent-%COMP%]{height:2em;width:2em;display:block;filter:invert(100%)}[_nghost-%COMP%]     label, a[_ngcontent-%COMP%]{color:var(--%NS%mat-toolbar-container-text-color)}a[_ngcontent-%COMP%]{text-decoration:none}a[_ngcontent-%COMP%]:hover{color:#d3d3d3}[_nghost-%COMP%]     mat-icon{height:3em;width:3em;transform:scale(.75);filter:invert(100%)}"]})};var jc=class t{static \u0275fac=function(e){return new(e||t)};static \u0275cmp=T({type:t,selectors:[["app-root"]],decls:11,vars:0,consts:[[1,"mat-typography"],["appNavigationTarget","","id","info"],["appNavigationTarget","","id","servers"],["appNavigationTarget","","id","channels"],["appNavigationTarget","","id","schemas"]],template:function(e,i){e&1&&(R(0,"app-header"),y(1,"main",0)(2,"app-sidenav")(3,"article",1),R(4,"app-info"),v(),y(5,"article",2),R(6,"app-servers"),v(),y(7,"article",3),R(8,"app-channels"),v(),y(9,"article",4),R(10,"app-schemas"),v()()())},dependencies:[gc,H_,Mn,Bc,Lc,kc,Cc,Fc,Sc,sn],styles:[".app-header[_ngcontent-%COMP%]{position:fixed;z-index:100;width:100%}main[_ngcontent-%COMP%]{margin:0;height:calc(100% - 64px);padding:0}article[_ngcontent-%COMP%]{margin:2em 1em}"]})};Bs.production&&void 0;sd(jc,F_).catch(t=>console.error(t));
