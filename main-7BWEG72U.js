import{$ as Ee,$a as $e,$b as uo,A as io,Aa as tt,Ab as Q,B as Ra,Ba as Ze,Bb as vt,C as oc,Ca as p,Cb as w,D as Pa,Da as tr,Db as Le,E as Mn,Ea as pp,Eb as J,F as Fa,Fa as Ba,Fb as ao,G as ct,Ga as hp,Gb as _p,H as jt,Ha as mt,Hb as so,I as De,Ia as Jn,Ib as lo,J as Xn,Ja as bt,Jb as co,K as Fe,Ka as rt,Kb as za,L as E,La as Se,Lb as Ha,M as $,Ma as $a,Mb as ir,N as M,Na as pn,Nb as ni,O as j,Oa as ja,Ob as dc,P as m,Pa as I,Pb as ze,Q as ro,Qa as V,Qb as mc,R as ep,Ra as G,Rb as uc,S as tp,Sa as ac,Sb as rr,T as An,Ta as Te,Tb as qa,U as np,Ua as Vt,Ub as Ua,V as Ke,Va as sc,Vb as mo,W as Ye,Wa as nr,Wb as bp,X as Ki,Xa as ei,Xb as Jt,Y as te,Ya as le,Yb as vp,Z as W,Za as k,Zb as yp,_ as Yi,_a as D,_b as yt,a as S,aa as K,ab as je,ac as xp,b as ye,ba as _i,bb as Ve,bc as wp,ca as X,cb as R,cc as Ue,d as F,da as ip,db as g,dc as Cp,e as bv,ea as Na,eb as f,ec as kp,f as lt,fa as Qi,fb as P,fc as Me,g as St,ga as dt,gb as qe,gc as po,h as z,ha as et,hb as Qe,hc as Dp,i as Kn,ia as Y,ib as Zt,ic as Ga,j as Ku,ja as In,jb as lc,k as Pe,ka as rp,kb as ot,l as Yu,la as oo,lb as ti,m as xe,ma as op,mb as pe,n as Qu,na as Zn,nb as Va,o as Xu,oa as Xi,ob as C,p as Zu,pa as Zi,pb as ne,q as Ju,qa as La,qb as B,r as _t,ra as Ji,rb as Xe,s as Be,sa as er,sb as Ne,t as rc,ta as ap,tb as H,u as Oa,ua as sp,ub as q,v as eo,va as lp,vb as fp,w as Yn,wa as cp,wb as gp,x as Qn,xa as dp,xb as cc,y as to,ya as mp,yb as zt,z as no,za as up,zb as bi}from"./chunk-6GWAROCZ.js";var Uo=F(_e=>{"use strict";Object.defineProperty(_e,"__esModule",{value:!0});_e.regexpCode=_e.getEsmExportName=_e.getProperty=_e.safeStringify=_e.stringify=_e.strConcat=_e.addCodeArg=_e.str=_e._=_e.nil=_e._Code=_e.Name=_e.IDENTIFIER=_e._CodeOrName=void 0;var Ho=class{};_e._CodeOrName=Ho;_e.IDENTIFIER=/^[a-z$_][a-z$_0-9]*$/i;var Fi=class extends Ho{constructor(t){if(super(),!_e.IDENTIFIER.test(t))throw new Error("CodeGen: name must be a valid identifier");this.str=t}toString(){return this.str}emptyStr(){return!1}get names(){return{[this.str]:1}}};_e.Name=Fi;var Wt=class extends Ho{constructor(t){super(),this._items=typeof t=="string"?[t]:t}toString(){return this.str}emptyStr(){if(this._items.length>1)return!1;let t=this._items[0];return t===""||t==='""'}get str(){var t;return(t=this._str)!==null&&t!==void 0?t:this._str=this._items.reduce((e,i)=>`${e}${i}`,"")}get names(){var t;return(t=this._names)!==null&&t!==void 0?t:this._names=this._items.reduce((e,i)=>(i instanceof Fi&&(e[i.str]=(e[i.str]||0)+1),e),{})}};_e._Code=Wt;_e.nil=new Wt("");function Nf(n,...t){let e=[n[0]],i=0;for(;i<t.length;)wd(e,t[i]),e.push(n[++i]);return new Wt(e)}_e._=Nf;var xd=new Wt("+");function Lf(n,...t){let e=[qo(n[0])],i=0;for(;i<t.length;)e.push(xd),wd(e,t[i]),e.push(xd,qo(n[++i]));return Bx(e),new Wt(e)}_e.str=Lf;function wd(n,t){t instanceof Wt?n.push(...t._items):t instanceof Fi?n.push(t):n.push(Vx(t))}_e.addCodeArg=wd;function Bx(n){let t=1;for(;t<n.length-1;){if(n[t]===xd){let e=$x(n[t-1],n[t+1]);if(e!==void 0){n.splice(t-1,3,e);continue}n[t++]="+"}t++}}function $x(n,t){if(t==='""')return n;if(n==='""')return t;if(typeof n=="string")return t instanceof Fi||n[n.length-1]!=='"'?void 0:typeof t!="string"?`${n.slice(0,-1)}${t}"`:t[0]==='"'?n.slice(0,-1)+t.slice(1):void 0;if(typeof t=="string"&&t[0]==='"'&&!(n instanceof Fi))return`"${n}${t.slice(1)}`}function jx(n,t){return t.emptyStr()?n:n.emptyStr()?t:Lf`${n}${t}`}_e.strConcat=jx;function Vx(n){return typeof n=="number"||typeof n=="boolean"||n===null?n:qo(Array.isArray(n)?n.join(","):n)}function zx(n){return new Wt(qo(n))}_e.stringify=zx;function qo(n){return JSON.stringify(n).replace(/\u2028/g,"\\u2028").replace(/\u2029/g,"\\u2029")}_e.safeStringify=qo;function Hx(n){return typeof n=="string"&&_e.IDENTIFIER.test(n)?new Wt(`.${n}`):Nf`[${n}]`}_e.getProperty=Hx;function qx(n){if(typeof n=="string"&&_e.IDENTIFIER.test(n))return new Wt(`${n}`);throw new Error(`CodeGen: invalid export name: ${n}, use explicit $id name mapping`)}_e.getEsmExportName=qx;function Ux(n){return new Wt(n.toString())}_e.regexpCode=Ux});var Dd=F(Tt=>{"use strict";Object.defineProperty(Tt,"__esModule",{value:!0});Tt.ValueScope=Tt.ValueScopeName=Tt.Scope=Tt.varKinds=Tt.UsedValueState=void 0;var It=Uo(),Cd=class extends Error{constructor(t){super(`CodeGen: "code" for ${t} not defined`),this.value=t.value}},Gs=(function(n){return n[n.Started=0]="Started",n[n.Completed=1]="Completed",n})(Gs||(Tt.UsedValueState=Gs={}));Tt.varKinds={const:new It.Name("const"),let:new It.Name("let"),var:new It.Name("var")};var Ws=class{constructor({prefixes:t,parent:e}={}){this._names={},this._prefixes=t,this._parent=e}toName(t){return t instanceof It.Name?t:this.name(t)}name(t){return new It.Name(this._newName(t))}_newName(t){let e=this._names[t]||this._nameGroup(t);return`${t}${e.index++}`}_nameGroup(t){var e,i;if(!((i=(e=this._parent)===null||e===void 0?void 0:e._prefixes)===null||i===void 0)&&i.has(t)||this._prefixes&&!this._prefixes.has(t))throw new Error(`CodeGen: prefix "${t}" is not allowed in this scope`);return this._names[t]={prefix:t,index:0}}};Tt.Scope=Ws;var Ks=class extends It.Name{constructor(t,e){super(e),this.prefix=t}setValue(t,{property:e,itemIndex:i}){this.value=t,this.scopePath=(0,It._)`.${new It.Name(e)}[${i}]`}};Tt.ValueScopeName=Ks;var Gx=(0,It._)`\n`,kd=class extends Ws{constructor(t){super(t),this._values={},this._scope=t.scope,this.opts=ye(S({},t),{_n:t.lines?Gx:It.nil})}get(){return this._scope}name(t){return new Ks(t,this._newName(t))}value(t,e){var i;if(e.ref===void 0)throw new Error("CodeGen: ref must be passed in value");let r=this.toName(t),{prefix:o}=r,a=(i=e.key)!==null&&i!==void 0?i:e.ref,s=this._values[o];if(s){let c=s.get(a);if(c)return c}else s=this._values[o]=new Map;s.set(a,r);let l=this._scope[o]||(this._scope[o]=[]),d=l.length;return l[d]=e.ref,r.setValue(e,{property:o,itemIndex:d}),r}getValue(t,e){let i=this._values[t];if(i)return i.get(e)}scopeRefs(t,e=this._values){return this._reduceValues(e,i=>{if(i.scopePath===void 0)throw new Error(`CodeGen: name "${i}" has no value`);return(0,It._)`${t}${i.scopePath}`})}scopeCode(t=this._values,e,i){return this._reduceValues(t,r=>{if(r.value===void 0)throw new Error(`CodeGen: name "${r}" has no value`);return r.value.code},e,i)}_reduceValues(t,e,i={},r){let o=It.nil;for(let a in t){let s=t[a];if(!s)continue;let l=i[a]=i[a]||new Map;s.forEach(d=>{if(l.has(d))return;l.set(d,Gs.Started);let c=e(d);if(c){let u=this.opts.es5?Tt.varKinds.var:Tt.varKinds.const;o=(0,It._)`${o}${u} ${d} = ${c};${this.opts._n}`}else if(c=r?.(d))o=(0,It._)`${o}${c}${this.opts._n}`;else throw new Cd(d);l.set(d,Gs.Completed)})}return o}};Tt.ValueScope=kd});var oe=F(re=>{"use strict";Object.defineProperty(re,"__esModule",{value:!0});re.or=re.and=re.not=re.CodeGen=re.operators=re.varKinds=re.ValueScopeName=re.ValueScope=re.Scope=re.Name=re.regexpCode=re.stringify=re.getProperty=re.nil=re.strConcat=re.str=re._=void 0;var me=Uo(),an=Dd(),li=Uo();Object.defineProperty(re,"_",{enumerable:!0,get:function(){return li._}});Object.defineProperty(re,"str",{enumerable:!0,get:function(){return li.str}});Object.defineProperty(re,"strConcat",{enumerable:!0,get:function(){return li.strConcat}});Object.defineProperty(re,"nil",{enumerable:!0,get:function(){return li.nil}});Object.defineProperty(re,"getProperty",{enumerable:!0,get:function(){return li.getProperty}});Object.defineProperty(re,"stringify",{enumerable:!0,get:function(){return li.stringify}});Object.defineProperty(re,"regexpCode",{enumerable:!0,get:function(){return li.regexpCode}});Object.defineProperty(re,"Name",{enumerable:!0,get:function(){return li.Name}});var Js=Dd();Object.defineProperty(re,"Scope",{enumerable:!0,get:function(){return Js.Scope}});Object.defineProperty(re,"ValueScope",{enumerable:!0,get:function(){return Js.ValueScope}});Object.defineProperty(re,"ValueScopeName",{enumerable:!0,get:function(){return Js.ValueScopeName}});Object.defineProperty(re,"varKinds",{enumerable:!0,get:function(){return Js.varKinds}});re.operators={GT:new me._Code(">"),GTE:new me._Code(">="),LT:new me._Code("<"),LTE:new me._Code("<="),EQ:new me._Code("==="),NEQ:new me._Code("!=="),NOT:new me._Code("!"),OR:new me._Code("||"),AND:new me._Code("&&"),ADD:new me._Code("+")};var jn=class{optimizeNodes(){return this}optimizeNames(t,e){return this}},Ed=class extends jn{constructor(t,e,i){super(),this.varKind=t,this.name=e,this.rhs=i}render({es5:t,_n:e}){let i=t?an.varKinds.var:this.varKind,r=this.rhs===void 0?"":` = ${this.rhs}`;return`${i} ${this.name}${r};`+e}optimizeNames(t,e){if(t[this.name.str])return this.rhs&&(this.rhs=wr(this.rhs,t,e)),this}get names(){return this.rhs instanceof me._CodeOrName?this.rhs.names:{}}},Qs=class extends jn{constructor(t,e,i){super(),this.lhs=t,this.rhs=e,this.sideEffects=i}render({_n:t}){return`${this.lhs} = ${this.rhs};`+t}optimizeNames(t,e){if(!(this.lhs instanceof me.Name&&!t[this.lhs.str]&&!this.sideEffects))return this.rhs=wr(this.rhs,t,e),this}get names(){let t=this.lhs instanceof me.Name?{}:S({},this.lhs.names);return Zs(t,this.rhs)}},Sd=class extends Qs{constructor(t,e,i,r){super(t,i,r),this.op=e}render({_n:t}){return`${this.lhs} ${this.op}= ${this.rhs};`+t}},Md=class extends jn{constructor(t){super(),this.label=t,this.names={}}render({_n:t}){return`${this.label}:`+t}},Ad=class extends jn{constructor(t){super(),this.label=t,this.names={}}render({_n:t}){return`break${this.label?` ${this.label}`:""};`+t}},Id=class extends jn{constructor(t){super(),this.error=t}render({_n:t}){return`throw ${this.error};`+t}get names(){return this.error.names}},Td=class extends jn{constructor(t){super(),this.code=t}render({_n:t}){return`${this.code};`+t}optimizeNodes(){return`${this.code}`?this:void 0}optimizeNames(t,e){return this.code=wr(this.code,t,e),this}get names(){return this.code instanceof me._CodeOrName?this.code.names:{}}},Go=class extends jn{constructor(t=[]){super(),this.nodes=t}render(t){return this.nodes.reduce((e,i)=>e+i.render(t),"")}optimizeNodes(){let{nodes:t}=this,e=t.length;for(;e--;){let i=t[e].optimizeNodes();Array.isArray(i)?t.splice(e,1,...i):i?t[e]=i:t.splice(e,1)}return t.length>0?this:void 0}optimizeNames(t,e){let{nodes:i}=this,r=i.length;for(;r--;){let o=i[r];o.optimizeNames(t,e)||(Wx(t,o.names),i.splice(r,1))}return i.length>0?this:void 0}get names(){return this.nodes.reduce((t,e)=>Ni(t,e.names),{})}},Vn=class extends Go{render(t){return"{"+t._n+super.render(t)+"}"+t._n}},Od=class extends Go{},Rd=(()=>{class n extends Vn{}return n.kind="else",n})(),Ys=(()=>{class n extends Vn{constructor(e,i){super(i),this.condition=e}render(e){let i=`if(${this.condition})`+super.render(e);return this.else&&(i+="else "+this.else.render(e)),i}optimizeNodes(){super.optimizeNodes();let e=this.condition;if(e===!0)return this.nodes;let i=this.else;if(i){let r=i.optimizeNodes();i=this.else=Array.isArray(r)?new Rd(r):r}if(i)return e===!1?i instanceof n?i:i.nodes:this.nodes.length?this:new n(zf(e),i instanceof n?[i]:i.nodes);if(!(e===!1||!this.nodes.length))return this}optimizeNames(e,i){var r;if(this.else=(r=this.else)===null||r===void 0?void 0:r.optimizeNames(e,i),!!(super.optimizeNames(e,i)||this.else))return this.condition=wr(this.condition,e,i),this}get names(){let e=super.names;return Zs(e,this.condition),this.else&&Ni(e,this.else.names),e}}return n.kind="if",n})(),el=(()=>{class n extends Vn{}return n.kind="for",n})(),Pd=class extends el{constructor(t){super(),this.iteration=t}render(t){return`for(${this.iteration})`+super.render(t)}optimizeNames(t,e){if(super.optimizeNames(t,e))return this.iteration=wr(this.iteration,t,e),this}get names(){return Ni(super.names,this.iteration.names)}},Fd=class extends el{constructor(t,e,i,r){super(),this.varKind=t,this.name=e,this.from=i,this.to=r}render(t){let e=t.es5?an.varKinds.var:this.varKind,{name:i,from:r,to:o}=this;return`for(${e} ${i}=${r}; ${i}<${o}; ${i}++)`+super.render(t)}get names(){let t=Zs(super.names,this.from);return Zs(t,this.to)}},Xs=class extends el{constructor(t,e,i,r){super(),this.loop=t,this.varKind=e,this.name=i,this.iterable=r}render(t){return`for(${this.varKind} ${this.name} ${this.loop} ${this.iterable})`+super.render(t)}optimizeNames(t,e){if(super.optimizeNames(t,e))return this.iterable=wr(this.iterable,t,e),this}get names(){return Ni(super.names,this.iterable.names)}},Bf=(()=>{class n extends Vn{constructor(e,i,r){super(),this.name=e,this.args=i,this.async=r}render(e){return`${this.async?"async ":""}function ${this.name}(${this.args})`+super.render(e)}}return n.kind="func",n})(),$f=(()=>{class n extends Go{render(e){return"return "+super.render(e)}}return n.kind="return",n})(),Nd=class extends Vn{render(t){let e="try"+super.render(t);return this.catch&&(e+=this.catch.render(t)),this.finally&&(e+=this.finally.render(t)),e}optimizeNodes(){var t,e;return super.optimizeNodes(),(t=this.catch)===null||t===void 0||t.optimizeNodes(),(e=this.finally)===null||e===void 0||e.optimizeNodes(),this}optimizeNames(t,e){var i,r;return super.optimizeNames(t,e),(i=this.catch)===null||i===void 0||i.optimizeNames(t,e),(r=this.finally)===null||r===void 0||r.optimizeNames(t,e),this}get names(){let t=super.names;return this.catch&&Ni(t,this.catch.names),this.finally&&Ni(t,this.finally.names),t}},jf=(()=>{class n extends Vn{constructor(e){super(),this.error=e}render(e){return`catch(${this.error})`+super.render(e)}}return n.kind="catch",n})(),Vf=(()=>{class n extends Vn{render(e){return"finally"+super.render(e)}}return n.kind="finally",n})(),Ld=class{constructor(t,e={}){this._values={},this._blockStarts=[],this._constants={},this.opts=ye(S({},e),{_n:e.lines?`
`:""}),this._extScope=t,this._scope=new an.Scope({parent:t}),this._nodes=[new Od]}toString(){return this._root.render(this.opts)}name(t){return this._scope.name(t)}scopeName(t){return this._extScope.name(t)}scopeValue(t,e){let i=this._extScope.value(t,e);return(this._values[i.prefix]||(this._values[i.prefix]=new Set)).add(i),i}getScopeValue(t,e){return this._extScope.getValue(t,e)}scopeRefs(t){return this._extScope.scopeRefs(t,this._values)}scopeCode(){return this._extScope.scopeCode(this._values)}_def(t,e,i,r){let o=this._scope.toName(e);return i!==void 0&&r&&(this._constants[o.str]=i),this._leafNode(new Ed(t,o,i)),o}const(t,e,i){return this._def(an.varKinds.const,t,e,i)}let(t,e,i){return this._def(an.varKinds.let,t,e,i)}var(t,e,i){return this._def(an.varKinds.var,t,e,i)}assign(t,e,i){return this._leafNode(new Qs(t,e,i))}add(t,e){return this._leafNode(new Sd(t,re.operators.ADD,e))}code(t){return typeof t=="function"?t():t!==me.nil&&this._leafNode(new Td(t)),this}object(...t){let e=["{"];for(let[i,r]of t)e.length>1&&e.push(","),e.push(i),(i!==r||this.opts.es5)&&(e.push(":"),(0,me.addCodeArg)(e,r));return e.push("}"),new me._Code(e)}if(t,e,i){if(this._blockNode(new Ys(t)),e&&i)this.code(e).else().code(i).endIf();else if(e)this.code(e).endIf();else if(i)throw new Error('CodeGen: "else" body without "then" body');return this}elseIf(t){return this._elseNode(new Ys(t))}else(){return this._elseNode(new Rd)}endIf(){return this._endBlockNode(Ys,Rd)}_for(t,e){return this._blockNode(t),e&&this.code(e).endFor(),this}for(t,e){return this._for(new Pd(t),e)}forRange(t,e,i,r,o=this.opts.es5?an.varKinds.var:an.varKinds.let){let a=this._scope.toName(t);return this._for(new Fd(o,a,e,i),()=>r(a))}forOf(t,e,i,r=an.varKinds.const){let o=this._scope.toName(t);if(this.opts.es5){let a=e instanceof me.Name?e:this.var("_arr",e);return this.forRange("_i",0,(0,me._)`${a}.length`,s=>{this.var(o,(0,me._)`${a}[${s}]`),i(o)})}return this._for(new Xs("of",r,o,e),()=>i(o))}forIn(t,e,i,r=this.opts.es5?an.varKinds.var:an.varKinds.const){if(this.opts.ownProperties)return this.forOf(t,(0,me._)`Object.keys(${e})`,i);let o=this._scope.toName(t);return this._for(new Xs("in",r,o,e),()=>i(o))}endFor(){return this._endBlockNode(el)}label(t){return this._leafNode(new Md(t))}break(t){return this._leafNode(new Ad(t))}return(t){let e=new $f;if(this._blockNode(e),this.code(t),e.nodes.length!==1)throw new Error('CodeGen: "return" should have one node');return this._endBlockNode($f)}try(t,e,i){if(!e&&!i)throw new Error('CodeGen: "try" without "catch" and "finally"');let r=new Nd;if(this._blockNode(r),this.code(t),e){let o=this.name("e");this._currNode=r.catch=new jf(o),e(o)}return i&&(this._currNode=r.finally=new Vf,this.code(i)),this._endBlockNode(jf,Vf)}throw(t){return this._leafNode(new Id(t))}block(t,e){return this._blockStarts.push(this._nodes.length),t&&this.code(t).endBlock(e),this}endBlock(t){let e=this._blockStarts.pop();if(e===void 0)throw new Error("CodeGen: not in self-balancing block");let i=this._nodes.length-e;if(i<0||t!==void 0&&i!==t)throw new Error(`CodeGen: wrong number of nodes: ${i} vs ${t} expected`);return this._nodes.length=e,this}func(t,e=me.nil,i,r){return this._blockNode(new Bf(t,e,i)),r&&this.code(r).endFunc(),this}endFunc(){return this._endBlockNode(Bf)}optimize(t=1){for(;t-- >0;)this._root.optimizeNodes(),this._root.optimizeNames(this._root.names,this._constants)}_leafNode(t){return this._currNode.nodes.push(t),this}_blockNode(t){this._currNode.nodes.push(t),this._nodes.push(t)}_endBlockNode(t,e){let i=this._currNode;if(i instanceof t||e&&i instanceof e)return this._nodes.pop(),this;throw new Error(`CodeGen: not in block "${e?`${t.kind}/${e.kind}`:t.kind}"`)}_elseNode(t){let e=this._currNode;if(!(e instanceof Ys))throw new Error('CodeGen: "else" without "if"');return this._currNode=e.else=t,this}get _root(){return this._nodes[0]}get _currNode(){let t=this._nodes;return t[t.length-1]}set _currNode(t){let e=this._nodes;e[e.length-1]=t}};re.CodeGen=Ld;function Ni(n,t){for(let e in t)n[e]=(n[e]||0)+(t[e]||0);return n}function Zs(n,t){return t instanceof me._CodeOrName?Ni(n,t.names):n}function wr(n,t,e){if(n instanceof me.Name)return i(n);if(!r(n))return n;return new me._Code(n._items.reduce((o,a)=>(a instanceof me.Name&&(a=i(a)),a instanceof me._Code?o.push(...a._items):o.push(a),o),[]));function i(o){let a=e[o.str];return a===void 0||t[o.str]!==1?o:(delete t[o.str],a)}function r(o){return o instanceof me._Code&&o._items.some(a=>a instanceof me.Name&&t[a.str]===1&&e[a.str]!==void 0)}}function Wx(n,t){for(let e in t)n[e]=(n[e]||0)-(t[e]||0)}function zf(n){return typeof n=="boolean"||typeof n=="number"||n===null?!n:(0,me._)`!${Bd(n)}`}re.not=zf;var Kx=Hf(re.operators.AND);function Yx(...n){return n.reduce(Kx)}re.and=Yx;var Qx=Hf(re.operators.OR);function Xx(...n){return n.reduce(Qx)}re.or=Xx;function Hf(n){return(t,e)=>t===me.nil?e:e===me.nil?t:(0,me._)`${Bd(t)} ${n} ${Bd(e)}`}function Bd(n){return n instanceof me.Name?n:(0,me._)`(${n})`}});var he=F(ae=>{"use strict";Object.defineProperty(ae,"__esModule",{value:!0});ae.checkStrictMode=ae.getErrorPath=ae.Type=ae.useFunc=ae.setEvaluated=ae.evaluatedPropsToName=ae.mergeEvaluated=ae.eachItem=ae.unescapeJsonPointer=ae.escapeJsonPointer=ae.escapeFragment=ae.unescapeFragment=ae.schemaRefOrVal=ae.schemaHasRulesButRef=ae.schemaHasRules=ae.checkUnknownRules=ae.alwaysValidSchema=ae.toHash=void 0;var Ie=oe(),Zx=Uo();function Jx(n){let t={};for(let e of n)t[e]=!0;return t}ae.toHash=Jx;function ew(n,t){return typeof t=="boolean"?t:Object.keys(t).length===0?!0:(Gf(n,t),!Wf(t,n.self.RULES.all))}ae.alwaysValidSchema=ew;function Gf(n,t=n.schema){let{opts:e,self:i}=n;if(!e.strictSchema||typeof t=="boolean")return;let r=i.RULES.keywords;for(let o in t)r[o]||Qf(n,`unknown keyword: "${o}"`)}ae.checkUnknownRules=Gf;function Wf(n,t){if(typeof n=="boolean")return!n;for(let e in n)if(t[e])return!0;return!1}ae.schemaHasRules=Wf;function tw(n,t){if(typeof n=="boolean")return!n;for(let e in n)if(e!=="$ref"&&t.all[e])return!0;return!1}ae.schemaHasRulesButRef=tw;function nw({topSchemaRef:n,schemaPath:t},e,i,r){if(!r){if(typeof e=="number"||typeof e=="boolean")return e;if(typeof e=="string")return(0,Ie._)`${e}`}return(0,Ie._)`${n}${t}${(0,Ie.getProperty)(i)}`}ae.schemaRefOrVal=nw;function iw(n){return Kf(decodeURIComponent(n))}ae.unescapeFragment=iw;function rw(n){return encodeURIComponent(jd(n))}ae.escapeFragment=rw;function jd(n){return typeof n=="number"?`${n}`:n.replace(/~/g,"~0").replace(/\//g,"~1")}ae.escapeJsonPointer=jd;function Kf(n){return n.replace(/~1/g,"/").replace(/~0/g,"~")}ae.unescapeJsonPointer=Kf;function ow(n,t){if(Array.isArray(n))for(let e of n)t(e);else t(n)}ae.eachItem=ow;function qf({mergeNames:n,mergeToName:t,mergeValues:e,resultToName:i}){return(r,o,a,s)=>{let l=a===void 0?o:a instanceof Ie.Name?(o instanceof Ie.Name?n(r,o,a):t(r,o,a),a):o instanceof Ie.Name?(t(r,a,o),o):e(o,a);return s===Ie.Name&&!(l instanceof Ie.Name)?i(r,l):l}}ae.mergeEvaluated={props:qf({mergeNames:(n,t,e)=>n.if((0,Ie._)`${e} !== true && ${t} !== undefined`,()=>{n.if((0,Ie._)`${t} === true`,()=>n.assign(e,!0),()=>n.assign(e,(0,Ie._)`${e} || {}`).code((0,Ie._)`Object.assign(${e}, ${t})`))}),mergeToName:(n,t,e)=>n.if((0,Ie._)`${e} !== true`,()=>{t===!0?n.assign(e,!0):(n.assign(e,(0,Ie._)`${e} || {}`),Vd(n,e,t))}),mergeValues:(n,t)=>n===!0?!0:S(S({},n),t),resultToName:Yf}),items:qf({mergeNames:(n,t,e)=>n.if((0,Ie._)`${e} !== true && ${t} !== undefined`,()=>n.assign(e,(0,Ie._)`${t} === true ? true : ${e} > ${t} ? ${e} : ${t}`)),mergeToName:(n,t,e)=>n.if((0,Ie._)`${e} !== true`,()=>n.assign(e,t===!0?!0:(0,Ie._)`${e} > ${t} ? ${e} : ${t}`)),mergeValues:(n,t)=>n===!0?!0:Math.max(n,t),resultToName:(n,t)=>n.var("items",t)})};function Yf(n,t){if(t===!0)return n.var("props",!0);let e=n.var("props",(0,Ie._)`{}`);return t!==void 0&&Vd(n,e,t),e}ae.evaluatedPropsToName=Yf;function Vd(n,t,e){Object.keys(e).forEach(i=>n.assign((0,Ie._)`${t}${(0,Ie.getProperty)(i)}`,!0))}ae.setEvaluated=Vd;var Uf={};function aw(n,t){return n.scopeValue("func",{ref:t,code:Uf[t.code]||(Uf[t.code]=new Zx._Code(t.code))})}ae.useFunc=aw;var $d=(function(n){return n[n.Num=0]="Num",n[n.Str=1]="Str",n})($d||(ae.Type=$d={}));function sw(n,t,e){if(n instanceof Ie.Name){let i=t===$d.Num;return e?i?(0,Ie._)`"[" + ${n} + "]"`:(0,Ie._)`"['" + ${n} + "']"`:i?(0,Ie._)`"/" + ${n}`:(0,Ie._)`"/" + ${n}.replace(/~/g, "~0").replace(/\\//g, "~1")`}return e?(0,Ie.getProperty)(n).toString():"/"+jd(n)}ae.getErrorPath=sw;function Qf(n,t,e=n.opts.strictSchema){if(e){if(t=`strict mode: ${t}`,e===!0)throw new Error(t);n.self.logger.warn(t)}}ae.checkStrictMode=Qf});var zn=F(zd=>{"use strict";Object.defineProperty(zd,"__esModule",{value:!0});var ht=oe(),lw={data:new ht.Name("data"),valCxt:new ht.Name("valCxt"),instancePath:new ht.Name("instancePath"),parentData:new ht.Name("parentData"),parentDataProperty:new ht.Name("parentDataProperty"),rootData:new ht.Name("rootData"),dynamicAnchors:new ht.Name("dynamicAnchors"),vErrors:new ht.Name("vErrors"),errors:new ht.Name("errors"),this:new ht.Name("this"),self:new ht.Name("self"),scope:new ht.Name("scope"),json:new ht.Name("json"),jsonPos:new ht.Name("jsonPos"),jsonLen:new ht.Name("jsonLen"),jsonPart:new ht.Name("jsonPart")};zd.default=lw});var Wo=F(ft=>{"use strict";Object.defineProperty(ft,"__esModule",{value:!0});ft.extendErrors=ft.resetErrorsCount=ft.reportExtraError=ft.reportError=ft.keyword$DataError=ft.keywordError=void 0;var fe=oe(),tl=he(),Dt=zn();ft.keywordError={message:({keyword:n})=>(0,fe.str)`must pass "${n}" keyword validation`};ft.keyword$DataError={message:({keyword:n,schemaType:t})=>t?(0,fe.str)`"${n}" keyword must be ${t} ($data)`:(0,fe.str)`"${n}" keyword is invalid ($data)`};function cw(n,t=ft.keywordError,e,i){let{it:r}=n,{gen:o,compositeRule:a,allErrors:s}=r,l=Jf(n,t,e);i??(a||s)?Xf(o,l):Zf(r,(0,fe._)`[${l}]`)}ft.reportError=cw;function dw(n,t=ft.keywordError,e){let{it:i}=n,{gen:r,compositeRule:o,allErrors:a}=i,s=Jf(n,t,e);Xf(r,s),o||a||Zf(i,Dt.default.vErrors)}ft.reportExtraError=dw;function mw(n,t){n.assign(Dt.default.errors,t),n.if((0,fe._)`${Dt.default.vErrors} !== null`,()=>n.if(t,()=>n.assign((0,fe._)`${Dt.default.vErrors}.length`,t),()=>n.assign(Dt.default.vErrors,null)))}ft.resetErrorsCount=mw;function uw({gen:n,keyword:t,schemaValue:e,data:i,errsCount:r,it:o}){if(r===void 0)throw new Error("ajv implementation error");let a=n.name("err");n.forRange("i",r,Dt.default.errors,s=>{n.const(a,(0,fe._)`${Dt.default.vErrors}[${s}]`),n.if((0,fe._)`${a}.instancePath === undefined`,()=>n.assign((0,fe._)`${a}.instancePath`,(0,fe.strConcat)(Dt.default.instancePath,o.errorPath))),n.assign((0,fe._)`${a}.schemaPath`,(0,fe.str)`${o.errSchemaPath}/${t}`),o.opts.verbose&&(n.assign((0,fe._)`${a}.schema`,e),n.assign((0,fe._)`${a}.data`,i))})}ft.extendErrors=uw;function Xf(n,t){let e=n.const("err",t);n.if((0,fe._)`${Dt.default.vErrors} === null`,()=>n.assign(Dt.default.vErrors,(0,fe._)`[${e}]`),(0,fe._)`${Dt.default.vErrors}.push(${e})`),n.code((0,fe._)`${Dt.default.errors}++`)}function Zf(n,t){let{gen:e,validateName:i,schemaEnv:r}=n;r.$async?e.throw((0,fe._)`new ${n.ValidationError}(${t})`):(e.assign((0,fe._)`${i}.errors`,t),e.return(!1))}var Li={keyword:new fe.Name("keyword"),schemaPath:new fe.Name("schemaPath"),params:new fe.Name("params"),propertyName:new fe.Name("propertyName"),message:new fe.Name("message"),schema:new fe.Name("schema"),parentSchema:new fe.Name("parentSchema")};function Jf(n,t,e){let{createErrors:i}=n.it;return i===!1?(0,fe._)`{}`:pw(n,t,e)}function pw(n,t,e={}){let{gen:i,it:r}=n,o=[hw(r,e),fw(n,e)];return gw(n,t,o),i.object(...o)}function hw({errorPath:n},{instancePath:t}){let e=t?(0,fe.str)`${n}${(0,tl.getErrorPath)(t,tl.Type.Str)}`:n;return[Dt.default.instancePath,(0,fe.strConcat)(Dt.default.instancePath,e)]}function fw({keyword:n,it:{errSchemaPath:t}},{schemaPath:e,parentSchema:i}){let r=i?t:(0,fe.str)`${t}/${n}`;return e&&(r=(0,fe.str)`${r}${(0,tl.getErrorPath)(e,tl.Type.Str)}`),[Li.schemaPath,r]}function gw(n,{params:t,message:e},i){let{keyword:r,data:o,schemaValue:a,it:s}=n,{opts:l,propertyName:d,topSchemaRef:c,schemaPath:u}=s;i.push([Li.keyword,r],[Li.params,typeof t=="function"?t(n):t||(0,fe._)`{}`]),l.messages&&i.push([Li.message,typeof e=="function"?e(n):e]),l.verbose&&i.push([Li.schema,a],[Li.parentSchema,(0,fe._)`${c}${u}`],[Dt.default.data,o]),d&&i.push([Li.propertyName,d])}});var tg=F(Cr=>{"use strict";Object.defineProperty(Cr,"__esModule",{value:!0});Cr.boolOrEmptySchema=Cr.topBoolOrEmptySchema=void 0;var _w=Wo(),bw=oe(),vw=zn(),yw={message:"boolean schema is false"};function xw(n){let{gen:t,schema:e,validateName:i}=n;e===!1?eg(n,!1):typeof e=="object"&&e.$async===!0?t.return(vw.default.data):(t.assign((0,bw._)`${i}.errors`,null),t.return(!0))}Cr.topBoolOrEmptySchema=xw;function ww(n,t){let{gen:e,schema:i}=n;i===!1?(e.var(t,!1),eg(n)):e.var(t,!0)}Cr.boolOrEmptySchema=ww;function eg(n,t){let{gen:e,data:i}=n,r={gen:e,keyword:"false schema",data:i,schema:!1,schemaCode:!1,schemaValue:!1,params:{},it:n};(0,_w.reportError)(r,yw,void 0,t)}});var Hd=F(kr=>{"use strict";Object.defineProperty(kr,"__esModule",{value:!0});kr.getRules=kr.isJSONType=void 0;var Cw=["string","number","integer","boolean","null","object","array"],kw=new Set(Cw);function Dw(n){return typeof n=="string"&&kw.has(n)}kr.isJSONType=Dw;function Ew(){let n={number:{type:"number",rules:[]},string:{type:"string",rules:[]},array:{type:"array",rules:[]},object:{type:"object",rules:[]}};return{types:ye(S({},n),{integer:!0,boolean:!0,null:!0}),rules:[{rules:[]},n.number,n.string,n.array,n.object],post:{rules:[]},all:{},keywords:{}}}kr.getRules=Ew});var qd=F(ci=>{"use strict";Object.defineProperty(ci,"__esModule",{value:!0});ci.shouldUseRule=ci.shouldUseGroup=ci.schemaHasRulesForType=void 0;function Sw({schema:n,self:t},e){let i=t.RULES.types[e];return i&&i!==!0&&ng(n,i)}ci.schemaHasRulesForType=Sw;function ng(n,t){return t.rules.some(e=>ig(n,e))}ci.shouldUseGroup=ng;function ig(n,t){var e;return n[t.keyword]!==void 0||((e=t.definition.implements)===null||e===void 0?void 0:e.some(i=>n[i]!==void 0))}ci.shouldUseRule=ig});var Ko=F(gt=>{"use strict";Object.defineProperty(gt,"__esModule",{value:!0});gt.reportTypeError=gt.checkDataTypes=gt.checkDataType=gt.coerceAndCheckDataType=gt.getJSONTypes=gt.getSchemaTypes=gt.DataType=void 0;var Mw=Hd(),Aw=qd(),Iw=Wo(),ee=oe(),rg=he(),Dr=(function(n){return n[n.Correct=0]="Correct",n[n.Wrong=1]="Wrong",n})(Dr||(gt.DataType=Dr={}));function Tw(n){let t=og(n.type);if(t.includes("null")){if(n.nullable===!1)throw new Error("type: null contradicts nullable: false")}else{if(!t.length&&n.nullable!==void 0)throw new Error('"nullable" cannot be used without "type"');n.nullable===!0&&t.push("null")}return t}gt.getSchemaTypes=Tw;function og(n){let t=Array.isArray(n)?n:n?[n]:[];if(t.every(Mw.isJSONType))return t;throw new Error("type must be JSONType or JSONType[]: "+t.join(","))}gt.getJSONTypes=og;function Ow(n,t){let{gen:e,data:i,opts:r}=n,o=Rw(t,r.coerceTypes),a=t.length>0&&!(o.length===0&&t.length===1&&(0,Aw.schemaHasRulesForType)(n,t[0]));if(a){let s=Gd(t,i,r.strictNumbers,Dr.Wrong);e.if(s,()=>{o.length?Pw(n,t,o):Wd(n)})}return a}gt.coerceAndCheckDataType=Ow;var ag=new Set(["string","number","integer","boolean","null"]);function Rw(n,t){return t?n.filter(e=>ag.has(e)||t==="array"&&e==="array"):[]}function Pw(n,t,e){let{gen:i,data:r,opts:o}=n,a=i.let("dataType",(0,ee._)`typeof ${r}`),s=i.let("coerced",(0,ee._)`undefined`);o.coerceTypes==="array"&&i.if((0,ee._)`${a} == 'object' && Array.isArray(${r}) && ${r}.length == 1`,()=>i.assign(r,(0,ee._)`${r}[0]`).assign(a,(0,ee._)`typeof ${r}`).if(Gd(t,r,o.strictNumbers),()=>i.assign(s,r))),i.if((0,ee._)`${s} !== undefined`);for(let d of e)(ag.has(d)||d==="array"&&o.coerceTypes==="array")&&l(d);i.else(),Wd(n),i.endIf(),i.if((0,ee._)`${s} !== undefined`,()=>{i.assign(r,s),Fw(n,s)});function l(d){switch(d){case"string":i.elseIf((0,ee._)`${a} == "number" || ${a} == "boolean"`).assign(s,(0,ee._)`"" + ${r}`).elseIf((0,ee._)`${r} === null`).assign(s,(0,ee._)`""`);return;case"number":i.elseIf((0,ee._)`${a} == "boolean" || ${r} === null
              || (${a} == "string" && ${r} && ${r} == +${r})`).assign(s,(0,ee._)`+${r}`);return;case"integer":i.elseIf((0,ee._)`${a} === "boolean" || ${r} === null
              || (${a} === "string" && ${r} && ${r} == +${r} && !(${r} % 1))`).assign(s,(0,ee._)`+${r}`);return;case"boolean":i.elseIf((0,ee._)`${r} === "false" || ${r} === 0 || ${r} === null`).assign(s,!1).elseIf((0,ee._)`${r} === "true" || ${r} === 1`).assign(s,!0);return;case"null":i.elseIf((0,ee._)`${r} === "" || ${r} === 0 || ${r} === false`),i.assign(s,null);return;case"array":i.elseIf((0,ee._)`${a} === "string" || ${a} === "number"
              || ${a} === "boolean" || ${r} === null`).assign(s,(0,ee._)`[${r}]`)}}}function Fw({gen:n,parentData:t,parentDataProperty:e},i){n.if((0,ee._)`${t} !== undefined`,()=>n.assign((0,ee._)`${t}[${e}]`,i))}function Ud(n,t,e,i=Dr.Correct){let r=i===Dr.Correct?ee.operators.EQ:ee.operators.NEQ,o;switch(n){case"null":return(0,ee._)`${t} ${r} null`;case"array":o=(0,ee._)`Array.isArray(${t})`;break;case"object":o=(0,ee._)`${t} && typeof ${t} == "object" && !Array.isArray(${t})`;break;case"integer":o=a((0,ee._)`!(${t} % 1) && !isNaN(${t})`);break;case"number":o=a();break;default:return(0,ee._)`typeof ${t} ${r} ${n}`}return i===Dr.Correct?o:(0,ee.not)(o);function a(s=ee.nil){return(0,ee.and)((0,ee._)`typeof ${t} == "number"`,s,e?(0,ee._)`isFinite(${t})`:ee.nil)}}gt.checkDataType=Ud;function Gd(n,t,e,i){if(n.length===1)return Ud(n[0],t,e,i);let r,o=(0,rg.toHash)(n);if(o.array&&o.object){let a=(0,ee._)`typeof ${t} != "object"`;r=o.null?a:(0,ee._)`!${t} || ${a}`,delete o.null,delete o.array,delete o.object}else r=ee.nil;o.number&&delete o.integer;for(let a in o)r=(0,ee.and)(r,Ud(a,t,e,i));return r}gt.checkDataTypes=Gd;var Nw={message:({schema:n})=>`must be ${n}`,params:({schema:n,schemaValue:t})=>typeof n=="string"?(0,ee._)`{type: ${n}}`:(0,ee._)`{type: ${t}}`};function Wd(n){let t=Lw(n);(0,Iw.reportError)(t,Nw)}gt.reportTypeError=Wd;function Lw(n){let{gen:t,data:e,schema:i}=n,r=(0,rg.schemaRefOrVal)(n,i,"type");return{gen:t,keyword:"type",data:e,schema:i.type,schemaCode:r,schemaValue:r,parentSchema:i,params:{},it:n}}});var lg=F(nl=>{"use strict";Object.defineProperty(nl,"__esModule",{value:!0});nl.assignDefaults=void 0;var Er=oe(),Bw=he();function $w(n,t){let{properties:e,items:i}=n.schema;if(t==="object"&&e)for(let r in e)sg(n,r,e[r].default);else t==="array"&&Array.isArray(i)&&i.forEach((r,o)=>sg(n,o,r.default))}nl.assignDefaults=$w;function sg(n,t,e){let{gen:i,compositeRule:r,data:o,opts:a}=n;if(e===void 0)return;let s=(0,Er._)`${o}${(0,Er.getProperty)(t)}`;if(r){(0,Bw.checkStrictMode)(n,`default is ignored for: ${s}`);return}let l=(0,Er._)`${s} === undefined`;a.useDefaults==="empty"&&(l=(0,Er._)`${l} || ${s} === null || ${s} === ""`),i.if(l,(0,Er._)`${s} = ${(0,Er.stringify)(e)}`)}});var Kt=F(Ae=>{"use strict";Object.defineProperty(Ae,"__esModule",{value:!0});Ae.validateUnion=Ae.validateArray=Ae.usePattern=Ae.callValidateCode=Ae.schemaProperties=Ae.allSchemaProperties=Ae.noPropertyInData=Ae.propertyInData=Ae.isOwnProperty=Ae.hasPropFunc=Ae.reportMissingProp=Ae.checkMissingProp=Ae.checkReportMissingProp=void 0;var Oe=oe(),Kd=he(),di=zn(),jw=he();function Vw(n,t){let{gen:e,data:i,it:r}=n;e.if(Qd(e,i,t,r.opts.ownProperties),()=>{n.setParams({missingProperty:(0,Oe._)`${t}`},!0),n.error()})}Ae.checkReportMissingProp=Vw;function zw({gen:n,data:t,it:{opts:e}},i,r){return(0,Oe.or)(...i.map(o=>(0,Oe.and)(Qd(n,t,o,e.ownProperties),(0,Oe._)`${r} = ${o}`)))}Ae.checkMissingProp=zw;function Hw(n,t){n.setParams({missingProperty:t},!0),n.error()}Ae.reportMissingProp=Hw;function cg(n){return n.scopeValue("func",{ref:Object.prototype.hasOwnProperty,code:(0,Oe._)`Object.prototype.hasOwnProperty`})}Ae.hasPropFunc=cg;function Yd(n,t,e){return(0,Oe._)`${cg(n)}.call(${t}, ${e})`}Ae.isOwnProperty=Yd;function qw(n,t,e,i){let r=(0,Oe._)`${t}${(0,Oe.getProperty)(e)} !== undefined`;return i?(0,Oe._)`${r} && ${Yd(n,t,e)}`:r}Ae.propertyInData=qw;function Qd(n,t,e,i){let r=(0,Oe._)`${t}${(0,Oe.getProperty)(e)} === undefined`;return i?(0,Oe.or)(r,(0,Oe.not)(Yd(n,t,e))):r}Ae.noPropertyInData=Qd;function dg(n){return n?Object.keys(n).filter(t=>t!=="__proto__"):[]}Ae.allSchemaProperties=dg;function Uw(n,t){return dg(t).filter(e=>!(0,Kd.alwaysValidSchema)(n,t[e]))}Ae.schemaProperties=Uw;function Gw({schemaCode:n,data:t,it:{gen:e,topSchemaRef:i,schemaPath:r,errorPath:o},it:a},s,l,d){let c=d?(0,Oe._)`${n}, ${t}, ${i}${r}`:t,u=[[di.default.instancePath,(0,Oe.strConcat)(di.default.instancePath,o)],[di.default.parentData,a.parentData],[di.default.parentDataProperty,a.parentDataProperty],[di.default.rootData,di.default.rootData]];a.opts.dynamicRef&&u.push([di.default.dynamicAnchors,di.default.dynamicAnchors]);let h=(0,Oe._)`${c}, ${e.object(...u)}`;return l!==Oe.nil?(0,Oe._)`${s}.call(${l}, ${h})`:(0,Oe._)`${s}(${h})`}Ae.callValidateCode=Gw;var Ww=(0,Oe._)`new RegExp`;function Kw({gen:n,it:{opts:t}},e){let i=t.unicodeRegExp?"u":"",{regExp:r}=t.code,o=r(e,i);return n.scopeValue("pattern",{key:o.toString(),ref:o,code:(0,Oe._)`${r.code==="new RegExp"?Ww:(0,jw.useFunc)(n,r)}(${e}, ${i})`})}Ae.usePattern=Kw;function Yw(n){let{gen:t,data:e,keyword:i,it:r}=n,o=t.name("valid");if(r.allErrors){let s=t.let("valid",!0);return a(()=>t.assign(s,!1)),s}return t.var(o,!0),a(()=>t.break()),o;function a(s){let l=t.const("len",(0,Oe._)`${e}.length`);t.forRange("i",0,l,d=>{n.subschema({keyword:i,dataProp:d,dataPropType:Kd.Type.Num},o),t.if((0,Oe.not)(o),s)})}}Ae.validateArray=Yw;function Qw(n){let{gen:t,schema:e,keyword:i,it:r}=n;if(!Array.isArray(e))throw new Error("ajv implementation error");if(e.some(l=>(0,Kd.alwaysValidSchema)(r,l))&&!r.opts.unevaluated)return;let a=t.let("valid",!1),s=t.name("_valid");t.block(()=>e.forEach((l,d)=>{let c=n.subschema({keyword:i,schemaProp:d,compositeRule:!0},s);t.assign(a,(0,Oe._)`${a} || ${s}`),n.mergeValidEvaluated(c,s)||t.if((0,Oe.not)(a))})),n.result(a,()=>n.reset(),()=>n.error(!0))}Ae.validateUnion=Qw});var pg=F(wn=>{"use strict";Object.defineProperty(wn,"__esModule",{value:!0});wn.validateKeywordUsage=wn.validSchemaType=wn.funcKeywordCode=wn.macroKeywordCode=void 0;var Et=oe(),Bi=zn(),Xw=Kt(),Zw=Wo();function Jw(n,t){let{gen:e,keyword:i,schema:r,parentSchema:o,it:a}=n,s=t.macro.call(a.self,r,o,a),l=ug(e,i,s);a.opts.validateSchema!==!1&&a.self.validateSchema(s,!0);let d=e.name("valid");n.subschema({schema:s,schemaPath:Et.nil,errSchemaPath:`${a.errSchemaPath}/${i}`,topSchemaRef:l,compositeRule:!0},d),n.pass(d,()=>n.error(!0))}wn.macroKeywordCode=Jw;function eC(n,t){var e;let{gen:i,keyword:r,schema:o,parentSchema:a,$data:s,it:l}=n;nC(l,t);let d=!s&&t.compile?t.compile.call(l.self,o,a,l):t.validate,c=ug(i,r,d),u=i.let("valid");n.block$data(u,h),n.ok((e=t.valid)!==null&&e!==void 0?e:u);function h(){if(t.errors===!1)b(),t.modifying&&mg(n),v(()=>n.error());else{let x=t.async?y():_();t.modifying&&mg(n),v(()=>tC(n,x))}}function y(){let x=i.let("ruleErrs",null);return i.try(()=>b((0,Et._)`await `),A=>i.assign(u,!1).if((0,Et._)`${A} instanceof ${l.ValidationError}`,()=>i.assign(x,(0,Et._)`${A}.errors`),()=>i.throw(A))),x}function _(){let x=(0,Et._)`${c}.errors`;return i.assign(x,null),b(Et.nil),x}function b(x=t.async?(0,Et._)`await `:Et.nil){let A=l.opts.passContext?Bi.default.this:Bi.default.self,T=!("compile"in t&&!s||t.schema===!1);i.assign(u,(0,Et._)`${x}${(0,Xw.callValidateCode)(n,c,A,T)}`,t.modifying)}function v(x){var A;i.if((0,Et.not)((A=t.valid)!==null&&A!==void 0?A:u),x)}}wn.funcKeywordCode=eC;function mg(n){let{gen:t,data:e,it:i}=n;t.if(i.parentData,()=>t.assign(e,(0,Et._)`${i.parentData}[${i.parentDataProperty}]`))}function tC(n,t){let{gen:e}=n;e.if((0,Et._)`Array.isArray(${t})`,()=>{e.assign(Bi.default.vErrors,(0,Et._)`${Bi.default.vErrors} === null ? ${t} : ${Bi.default.vErrors}.concat(${t})`).assign(Bi.default.errors,(0,Et._)`${Bi.default.vErrors}.length`),(0,Zw.extendErrors)(n)},()=>n.error())}function nC({schemaEnv:n},t){if(t.async&&!n.$async)throw new Error("async keyword in sync schema")}function ug(n,t,e){if(e===void 0)throw new Error(`keyword "${t}" failed to compile`);return n.scopeValue("keyword",typeof e=="function"?{ref:e}:{ref:e,code:(0,Et.stringify)(e)})}function iC(n,t,e=!1){return!t.length||t.some(i=>i==="array"?Array.isArray(n):i==="object"?n&&typeof n=="object"&&!Array.isArray(n):typeof n==i||e&&typeof n>"u")}wn.validSchemaType=iC;function rC({schema:n,opts:t,self:e,errSchemaPath:i},r,o){if(Array.isArray(r.keyword)?!r.keyword.includes(o):r.keyword!==o)throw new Error("ajv implementation error");let a=r.dependencies;if(a?.some(s=>!Object.prototype.hasOwnProperty.call(n,s)))throw new Error(`parent schema must have dependencies of ${o}: ${a.join(",")}`);if(r.validateSchema&&!r.validateSchema(n[o])){let l=`keyword "${o}" value is invalid at path "${i}": `+e.errorsText(r.validateSchema.errors);if(t.validateSchema==="log")e.logger.error(l);else throw new Error(l)}}wn.validateKeywordUsage=rC});var fg=F(mi=>{"use strict";Object.defineProperty(mi,"__esModule",{value:!0});mi.extendSubschemaMode=mi.extendSubschemaData=mi.getSubschema=void 0;var Cn=oe(),hg=he();function oC(n,{keyword:t,schemaProp:e,schema:i,schemaPath:r,errSchemaPath:o,topSchemaRef:a}){if(t!==void 0&&i!==void 0)throw new Error('both "keyword" and "schema" passed, only one allowed');if(t!==void 0){let s=n.schema[t];return e===void 0?{schema:s,schemaPath:(0,Cn._)`${n.schemaPath}${(0,Cn.getProperty)(t)}`,errSchemaPath:`${n.errSchemaPath}/${t}`}:{schema:s[e],schemaPath:(0,Cn._)`${n.schemaPath}${(0,Cn.getProperty)(t)}${(0,Cn.getProperty)(e)}`,errSchemaPath:`${n.errSchemaPath}/${t}/${(0,hg.escapeFragment)(e)}`}}if(i!==void 0){if(r===void 0||o===void 0||a===void 0)throw new Error('"schemaPath", "errSchemaPath" and "topSchemaRef" are required with "schema"');return{schema:i,schemaPath:r,topSchemaRef:a,errSchemaPath:o}}throw new Error('either "keyword" or "schema" must be passed')}mi.getSubschema=oC;function aC(n,t,{dataProp:e,dataPropType:i,data:r,dataTypes:o,propertyName:a}){if(r!==void 0&&e!==void 0)throw new Error('both "data" and "dataProp" passed, only one allowed');let{gen:s}=t;if(e!==void 0){let{errorPath:d,dataPathArr:c,opts:u}=t,h=s.let("data",(0,Cn._)`${t.data}${(0,Cn.getProperty)(e)}`,!0);l(h),n.errorPath=(0,Cn.str)`${d}${(0,hg.getErrorPath)(e,i,u.jsPropertySyntax)}`,n.parentDataProperty=(0,Cn._)`${e}`,n.dataPathArr=[...c,n.parentDataProperty]}if(r!==void 0){let d=r instanceof Cn.Name?r:s.let("data",r,!0);l(d),a!==void 0&&(n.propertyName=a)}o&&(n.dataTypes=o);function l(d){n.data=d,n.dataLevel=t.dataLevel+1,n.dataTypes=[],t.definedProperties=new Set,n.parentData=t.data,n.dataNames=[...t.dataNames,d]}}mi.extendSubschemaData=aC;function sC(n,{jtdDiscriminator:t,jtdMetadata:e,compositeRule:i,createErrors:r,allErrors:o}){i!==void 0&&(n.compositeRule=i),r!==void 0&&(n.createErrors=r),o!==void 0&&(n.allErrors=o),n.jtdDiscriminator=t,n.jtdMetadata=e}mi.extendSubschemaMode=sC});var Xd=F((BB,gg)=>{"use strict";gg.exports=function n(t,e){if(t===e)return!0;if(t&&e&&typeof t=="object"&&typeof e=="object"){if(t.constructor!==e.constructor)return!1;var i,r,o;if(Array.isArray(t)){if(i=t.length,i!=e.length)return!1;for(r=i;r--!==0;)if(!n(t[r],e[r]))return!1;return!0}if(t.constructor===RegExp)return t.source===e.source&&t.flags===e.flags;if(t.valueOf!==Object.prototype.valueOf)return t.valueOf()===e.valueOf();if(t.toString!==Object.prototype.toString)return t.toString()===e.toString();if(o=Object.keys(t),i=o.length,i!==Object.keys(e).length)return!1;for(r=i;r--!==0;)if(!Object.prototype.hasOwnProperty.call(e,o[r]))return!1;for(r=i;r--!==0;){var a=o[r];if(!n(t[a],e[a]))return!1}return!0}return t!==t&&e!==e}});var bg=F(($B,_g)=>{"use strict";var ui=_g.exports=function(n,t,e){typeof t=="function"&&(e=t,t={}),e=t.cb||e;var i=typeof e=="function"?e:e.pre||function(){},r=e.post||function(){};il(t,i,r,n,"",n)};ui.keywords={additionalItems:!0,items:!0,contains:!0,additionalProperties:!0,propertyNames:!0,not:!0,if:!0,then:!0,else:!0};ui.arrayKeywords={items:!0,allOf:!0,anyOf:!0,oneOf:!0};ui.propsKeywords={$defs:!0,definitions:!0,properties:!0,patternProperties:!0,dependencies:!0};ui.skipKeywords={default:!0,enum:!0,const:!0,required:!0,maximum:!0,minimum:!0,exclusiveMaximum:!0,exclusiveMinimum:!0,multipleOf:!0,maxLength:!0,minLength:!0,pattern:!0,format:!0,maxItems:!0,minItems:!0,uniqueItems:!0,maxProperties:!0,minProperties:!0};function il(n,t,e,i,r,o,a,s,l,d){if(i&&typeof i=="object"&&!Array.isArray(i)){t(i,r,o,a,s,l,d);for(var c in i){var u=i[c];if(Array.isArray(u)){if(c in ui.arrayKeywords)for(var h=0;h<u.length;h++)il(n,t,e,u[h],r+"/"+c+"/"+h,o,r,c,i,h)}else if(c in ui.propsKeywords){if(u&&typeof u=="object")for(var y in u)il(n,t,e,u[y],r+"/"+c+"/"+lC(y),o,r,c,i,y)}else(c in ui.keywords||n.allKeys&&!(c in ui.skipKeywords))&&il(n,t,e,u,r+"/"+c,o,r,c,i)}e(i,r,o,a,s,l,d)}}function lC(n){return n.replace(/~/g,"~0").replace(/\//g,"~1")}});var Yo=F(Ot=>{"use strict";Object.defineProperty(Ot,"__esModule",{value:!0});Ot.getSchemaRefs=Ot.resolveUrl=Ot.normalizeId=Ot._getFullPath=Ot.getFullPath=Ot.inlineRef=void 0;var cC=he(),dC=Xd(),mC=bg(),uC=new Set(["type","format","pattern","maxLength","minLength","maxProperties","minProperties","maxItems","minItems","maximum","minimum","uniqueItems","multipleOf","required","enum","const"]);function pC(n,t=!0){return typeof n=="boolean"?!0:t===!0?!Zd(n):t?vg(n)<=t:!1}Ot.inlineRef=pC;var hC=new Set(["$ref","$recursiveRef","$recursiveAnchor","$dynamicRef","$dynamicAnchor"]);function Zd(n){for(let t in n){if(hC.has(t))return!0;let e=n[t];if(Array.isArray(e)&&e.some(Zd)||typeof e=="object"&&Zd(e))return!0}return!1}function vg(n){let t=0;for(let e in n){if(e==="$ref")return 1/0;if(t++,!uC.has(e)&&(typeof n[e]=="object"&&(0,cC.eachItem)(n[e],i=>t+=vg(i)),t===1/0))return 1/0}return t}function yg(n,t="",e){e!==!1&&(t=Sr(t));let i=n.parse(t);return xg(n,i)}Ot.getFullPath=yg;function xg(n,t){return n.serialize(t).split("#")[0]+"#"}Ot._getFullPath=xg;var fC=/#\/?$/;function Sr(n){return n?n.replace(fC,""):""}Ot.normalizeId=Sr;function gC(n,t,e){return e=Sr(e),n.resolve(t,e)}Ot.resolveUrl=gC;var _C=/^[a-z_][-a-z0-9._]*$/i;function bC(n,t){if(typeof n=="boolean")return{};let{schemaId:e,uriResolver:i}=this.opts,r=Sr(n[e]||t),o={"":r},a=yg(i,r,!1),s={},l=new Set;return mC(n,{allKeys:!0},(u,h,y,_)=>{if(_===void 0)return;let b=a+h,v=o[_];typeof u[e]=="string"&&(v=x.call(this,u[e])),A.call(this,u.$anchor),A.call(this,u.$dynamicAnchor),o[h]=v;function x(T){let L=this.opts.uriResolver.resolve;if(T=Sr(v?L(v,T):T),l.has(T))throw c(T);l.add(T);let N=this.refs[T];return typeof N=="string"&&(N=this.refs[N]),typeof N=="object"?d(u,N.schema,T):T!==Sr(b)&&(T[0]==="#"?(d(u,s[T],T),s[T]=u):this.refs[T]=b),T}function A(T){if(typeof T=="string"){if(!_C.test(T))throw new Error(`invalid anchor "${T}"`);x.call(this,`#${T}`)}}}),s;function d(u,h,y){if(h!==void 0&&!dC(u,h))throw c(y)}function c(u){return new Error(`reference "${u}" resolves to more than one schema`)}}Ot.getSchemaRefs=bC});var Zo=F(pi=>{"use strict";Object.defineProperty(pi,"__esModule",{value:!0});pi.getData=pi.KeywordCxt=pi.validateFunctionCode=void 0;var Eg=tg(),wg=Ko(),em=qd(),rl=Ko(),vC=lg(),Xo=pg(),Jd=fg(),U=oe(),Z=zn(),yC=Yo(),Hn=he(),Qo=Wo();function xC(n){if(Ag(n)&&(Ig(n),Mg(n))){kC(n);return}Sg(n,()=>(0,Eg.topBoolOrEmptySchema)(n))}pi.validateFunctionCode=xC;function Sg({gen:n,validateName:t,schema:e,schemaEnv:i,opts:r},o){r.code.es5?n.func(t,(0,U._)`${Z.default.data}, ${Z.default.valCxt}`,i.$async,()=>{n.code((0,U._)`"use strict"; ${Cg(e,r)}`),CC(n,r),n.code(o)}):n.func(t,(0,U._)`${Z.default.data}, ${wC(r)}`,i.$async,()=>n.code(Cg(e,r)).code(o))}function wC(n){return(0,U._)`{${Z.default.instancePath}="", ${Z.default.parentData}, ${Z.default.parentDataProperty}, ${Z.default.rootData}=${Z.default.data}${n.dynamicRef?(0,U._)`, ${Z.default.dynamicAnchors}={}`:U.nil}}={}`}function CC(n,t){n.if(Z.default.valCxt,()=>{n.var(Z.default.instancePath,(0,U._)`${Z.default.valCxt}.${Z.default.instancePath}`),n.var(Z.default.parentData,(0,U._)`${Z.default.valCxt}.${Z.default.parentData}`),n.var(Z.default.parentDataProperty,(0,U._)`${Z.default.valCxt}.${Z.default.parentDataProperty}`),n.var(Z.default.rootData,(0,U._)`${Z.default.valCxt}.${Z.default.rootData}`),t.dynamicRef&&n.var(Z.default.dynamicAnchors,(0,U._)`${Z.default.valCxt}.${Z.default.dynamicAnchors}`)},()=>{n.var(Z.default.instancePath,(0,U._)`""`),n.var(Z.default.parentData,(0,U._)`undefined`),n.var(Z.default.parentDataProperty,(0,U._)`undefined`),n.var(Z.default.rootData,Z.default.data),t.dynamicRef&&n.var(Z.default.dynamicAnchors,(0,U._)`{}`)})}function kC(n){let{schema:t,opts:e,gen:i}=n;Sg(n,()=>{e.$comment&&t.$comment&&Og(n),AC(n),i.let(Z.default.vErrors,null),i.let(Z.default.errors,0),e.unevaluated&&DC(n),Tg(n),OC(n)})}function DC(n){let{gen:t,validateName:e}=n;n.evaluated=t.const("evaluated",(0,U._)`${e}.evaluated`),t.if((0,U._)`${n.evaluated}.dynamicProps`,()=>t.assign((0,U._)`${n.evaluated}.props`,(0,U._)`undefined`)),t.if((0,U._)`${n.evaluated}.dynamicItems`,()=>t.assign((0,U._)`${n.evaluated}.items`,(0,U._)`undefined`))}function Cg(n,t){let e=typeof n=="object"&&n[t.schemaId];return e&&(t.code.source||t.code.process)?(0,U._)`/*# sourceURL=${e} */`:U.nil}function EC(n,t){if(Ag(n)&&(Ig(n),Mg(n))){SC(n,t);return}(0,Eg.boolOrEmptySchema)(n,t)}function Mg({schema:n,self:t}){if(typeof n=="boolean")return!n;for(let e in n)if(t.RULES.all[e])return!0;return!1}function Ag(n){return typeof n.schema!="boolean"}function SC(n,t){let{schema:e,gen:i,opts:r}=n;r.$comment&&e.$comment&&Og(n),IC(n),TC(n);let o=i.const("_errs",Z.default.errors);Tg(n,o),i.var(t,(0,U._)`${o} === ${Z.default.errors}`)}function Ig(n){(0,Hn.checkUnknownRules)(n),MC(n)}function Tg(n,t){if(n.opts.jtd)return kg(n,[],!1,t);let e=(0,wg.getSchemaTypes)(n.schema),i=(0,wg.coerceAndCheckDataType)(n,e);kg(n,e,!i,t)}function MC(n){let{schema:t,errSchemaPath:e,opts:i,self:r}=n;t.$ref&&i.ignoreKeywordsWithRef&&(0,Hn.schemaHasRulesButRef)(t,r.RULES)&&r.logger.warn(`$ref: keywords ignored in schema at path "${e}"`)}function AC(n){let{schema:t,opts:e}=n;t.default!==void 0&&e.useDefaults&&e.strictSchema&&(0,Hn.checkStrictMode)(n,"default is ignored in the schema root")}function IC(n){let t=n.schema[n.opts.schemaId];t&&(n.baseId=(0,yC.resolveUrl)(n.opts.uriResolver,n.baseId,t))}function TC(n){if(n.schema.$async&&!n.schemaEnv.$async)throw new Error("async schema in sync schema")}function Og({gen:n,schemaEnv:t,schema:e,errSchemaPath:i,opts:r}){let o=e.$comment;if(r.$comment===!0)n.code((0,U._)`${Z.default.self}.logger.log(${o})`);else if(typeof r.$comment=="function"){let a=(0,U.str)`${i}/$comment`,s=n.scopeValue("root",{ref:t.root});n.code((0,U._)`${Z.default.self}.opts.$comment(${o}, ${a}, ${s}.schema)`)}}function OC(n){let{gen:t,schemaEnv:e,validateName:i,ValidationError:r,opts:o}=n;e.$async?t.if((0,U._)`${Z.default.errors} === 0`,()=>t.return(Z.default.data),()=>t.throw((0,U._)`new ${r}(${Z.default.vErrors})`)):(t.assign((0,U._)`${i}.errors`,Z.default.vErrors),o.unevaluated&&RC(n),t.return((0,U._)`${Z.default.errors} === 0`))}function RC({gen:n,evaluated:t,props:e,items:i}){e instanceof U.Name&&n.assign((0,U._)`${t}.props`,e),i instanceof U.Name&&n.assign((0,U._)`${t}.items`,i)}function kg(n,t,e,i){let{gen:r,schema:o,data:a,allErrors:s,opts:l,self:d}=n,{RULES:c}=d;if(o.$ref&&(l.ignoreKeywordsWithRef||!(0,Hn.schemaHasRulesButRef)(o,c))){r.block(()=>Pg(n,"$ref",c.all.$ref.definition));return}l.jtd||PC(n,t),r.block(()=>{for(let h of c.rules)u(h);u(c.post)});function u(h){(0,em.shouldUseGroup)(o,h)&&(h.type?(r.if((0,rl.checkDataType)(h.type,a,l.strictNumbers)),Dg(n,h),t.length===1&&t[0]===h.type&&e&&(r.else(),(0,rl.reportTypeError)(n)),r.endIf()):Dg(n,h),s||r.if((0,U._)`${Z.default.errors} === ${i||0}`))}}function Dg(n,t){let{gen:e,schema:i,opts:{useDefaults:r}}=n;r&&(0,vC.assignDefaults)(n,t.type),e.block(()=>{for(let o of t.rules)(0,em.shouldUseRule)(i,o)&&Pg(n,o.keyword,o.definition,t.type)})}function PC(n,t){n.schemaEnv.meta||!n.opts.strictTypes||(FC(n,t),n.opts.allowUnionTypes||NC(n,t),LC(n,n.dataTypes))}function FC(n,t){if(t.length){if(!n.dataTypes.length){n.dataTypes=t;return}t.forEach(e=>{Rg(n.dataTypes,e)||tm(n,`type "${e}" not allowed by context "${n.dataTypes.join(",")}"`)}),$C(n,t)}}function NC(n,t){t.length>1&&!(t.length===2&&t.includes("null"))&&tm(n,"use allowUnionTypes to allow union type keyword")}function LC(n,t){let e=n.self.RULES.all;for(let i in e){let r=e[i];if(typeof r=="object"&&(0,em.shouldUseRule)(n.schema,r)){let{type:o}=r.definition;o.length&&!o.some(a=>BC(t,a))&&tm(n,`missing type "${o.join(",")}" for keyword "${i}"`)}}}function BC(n,t){return n.includes(t)||t==="number"&&n.includes("integer")}function Rg(n,t){return n.includes(t)||t==="integer"&&n.includes("number")}function $C(n,t){let e=[];for(let i of n.dataTypes)Rg(t,i)?e.push(i):t.includes("integer")&&i==="number"&&e.push("integer");n.dataTypes=e}function tm(n,t){let e=n.schemaEnv.baseId+n.errSchemaPath;t+=` at "${e}" (strictTypes)`,(0,Hn.checkStrictMode)(n,t,n.opts.strictTypes)}var ol=class{constructor(t,e,i){if((0,Xo.validateKeywordUsage)(t,e,i),this.gen=t.gen,this.allErrors=t.allErrors,this.keyword=i,this.data=t.data,this.schema=t.schema[i],this.$data=e.$data&&t.opts.$data&&this.schema&&this.schema.$data,this.schemaValue=(0,Hn.schemaRefOrVal)(t,this.schema,i,this.$data),this.schemaType=e.schemaType,this.parentSchema=t.schema,this.params={},this.it=t,this.def=e,this.$data)this.schemaCode=t.gen.const("vSchema",Fg(this.$data,t));else if(this.schemaCode=this.schemaValue,!(0,Xo.validSchemaType)(this.schema,e.schemaType,e.allowUndefined))throw new Error(`${i} value must be ${JSON.stringify(e.schemaType)}`);("code"in e?e.trackErrors:e.errors!==!1)&&(this.errsCount=t.gen.const("_errs",Z.default.errors))}result(t,e,i){this.failResult((0,U.not)(t),e,i)}failResult(t,e,i){this.gen.if(t),i?i():this.error(),e?(this.gen.else(),e(),this.allErrors&&this.gen.endIf()):this.allErrors?this.gen.endIf():this.gen.else()}pass(t,e){this.failResult((0,U.not)(t),void 0,e)}fail(t){if(t===void 0){this.error(),this.allErrors||this.gen.if(!1);return}this.gen.if(t),this.error(),this.allErrors?this.gen.endIf():this.gen.else()}fail$data(t){if(!this.$data)return this.fail(t);let{schemaCode:e}=this;this.fail((0,U._)`${e} !== undefined && (${(0,U.or)(this.invalid$data(),t)})`)}error(t,e,i){if(e){this.setParams(e),this._error(t,i),this.setParams({});return}this._error(t,i)}_error(t,e){(t?Qo.reportExtraError:Qo.reportError)(this,this.def.error,e)}$dataError(){(0,Qo.reportError)(this,this.def.$dataError||Qo.keyword$DataError)}reset(){if(this.errsCount===void 0)throw new Error('add "trackErrors" to keyword definition');(0,Qo.resetErrorsCount)(this.gen,this.errsCount)}ok(t){this.allErrors||this.gen.if(t)}setParams(t,e){e?Object.assign(this.params,t):this.params=t}block$data(t,e,i=U.nil){this.gen.block(()=>{this.check$data(t,i),e()})}check$data(t=U.nil,e=U.nil){if(!this.$data)return;let{gen:i,schemaCode:r,schemaType:o,def:a}=this;i.if((0,U.or)((0,U._)`${r} === undefined`,e)),t!==U.nil&&i.assign(t,!0),(o.length||a.validateSchema)&&(i.elseIf(this.invalid$data()),this.$dataError(),t!==U.nil&&i.assign(t,!1)),i.else()}invalid$data(){let{gen:t,schemaCode:e,schemaType:i,def:r,it:o}=this;return(0,U.or)(a(),s());function a(){if(i.length){if(!(e instanceof U.Name))throw new Error("ajv implementation error");let l=Array.isArray(i)?i:[i];return(0,U._)`${(0,rl.checkDataTypes)(l,e,o.opts.strictNumbers,rl.DataType.Wrong)}`}return U.nil}function s(){if(r.validateSchema){let l=t.scopeValue("validate$data",{ref:r.validateSchema});return(0,U._)`!${l}(${e})`}return U.nil}}subschema(t,e){let i=(0,Jd.getSubschema)(this.it,t);(0,Jd.extendSubschemaData)(i,this.it,t),(0,Jd.extendSubschemaMode)(i,t);let r=ye(S(S({},this.it),i),{items:void 0,props:void 0});return EC(r,e),r}mergeEvaluated(t,e){let{it:i,gen:r}=this;i.opts.unevaluated&&(i.props!==!0&&t.props!==void 0&&(i.props=Hn.mergeEvaluated.props(r,t.props,i.props,e)),i.items!==!0&&t.items!==void 0&&(i.items=Hn.mergeEvaluated.items(r,t.items,i.items,e)))}mergeValidEvaluated(t,e){let{it:i,gen:r}=this;if(i.opts.unevaluated&&(i.props!==!0||i.items!==!0))return r.if(e,()=>this.mergeEvaluated(t,U.Name)),!0}};pi.KeywordCxt=ol;function Pg(n,t,e,i){let r=new ol(n,e,t);"code"in e?e.code(r,i):r.$data&&e.validate?(0,Xo.funcKeywordCode)(r,e):"macro"in e?(0,Xo.macroKeywordCode)(r,e):(e.compile||e.validate)&&(0,Xo.funcKeywordCode)(r,e)}var jC=/^\/(?:[^~]|~0|~1)*$/,VC=/^([0-9]+)(#|\/(?:[^~]|~0|~1)*)?$/;function Fg(n,{dataLevel:t,dataNames:e,dataPathArr:i}){let r,o;if(n==="")return Z.default.rootData;if(n[0]==="/"){if(!jC.test(n))throw new Error(`Invalid JSON-pointer: ${n}`);r=n,o=Z.default.rootData}else{let d=VC.exec(n);if(!d)throw new Error(`Invalid JSON-pointer: ${n}`);let c=+d[1];if(r=d[2],r==="#"){if(c>=t)throw new Error(l("property/index",c));return i[t-c]}if(c>t)throw new Error(l("data",c));if(o=e[t-c],!r)return o}let a=o,s=r.split("/");for(let d of s)d&&(o=(0,U._)`${o}${(0,U.getProperty)((0,Hn.unescapeJsonPointer)(d))}`,a=(0,U._)`${a} && ${o}`);return a;function l(d,c){return`Cannot access ${d} ${c} levels up, current level is ${t}`}}pi.getData=Fg});var al=F(im=>{"use strict";Object.defineProperty(im,"__esModule",{value:!0});var nm=class extends Error{constructor(t){super("validation failed"),this.errors=t,this.ajv=this.validation=!0}};im.default=nm});var Jo=F(am=>{"use strict";Object.defineProperty(am,"__esModule",{value:!0});var rm=Yo(),om=class extends Error{constructor(t,e,i,r){super(r||`can't resolve reference ${i} from id ${e}`),this.missingRef=(0,rm.resolveUrl)(t,e,i),this.missingSchema=(0,rm.normalizeId)((0,rm.getFullPath)(t,this.missingRef))}};am.default=om});var ll=F(Yt=>{"use strict";Object.defineProperty(Yt,"__esModule",{value:!0});Yt.resolveSchema=Yt.getCompilingSchema=Yt.resolveRef=Yt.compileSchema=Yt.SchemaEnv=void 0;var sn=oe(),zC=al(),$i=zn(),ln=Yo(),Ng=he(),HC=Zo(),Mr=class{constructor(t){var e;this.refs={},this.dynamicAnchors={};let i;typeof t.schema=="object"&&(i=t.schema),this.schema=t.schema,this.schemaId=t.schemaId,this.root=t.root||this,this.baseId=(e=t.baseId)!==null&&e!==void 0?e:(0,ln.normalizeId)(i?.[t.schemaId||"$id"]),this.schemaPath=t.schemaPath,this.localRefs=t.localRefs,this.meta=t.meta,this.$async=i?.$async,this.refs={}}};Yt.SchemaEnv=Mr;function lm(n){let t=Lg.call(this,n);if(t)return t;let e=(0,ln.getFullPath)(this.opts.uriResolver,n.root.baseId),{es5:i,lines:r}=this.opts.code,{ownProperties:o}=this.opts,a=new sn.CodeGen(this.scope,{es5:i,lines:r,ownProperties:o}),s;n.$async&&(s=a.scopeValue("Error",{ref:zC.default,code:(0,sn._)`require("ajv/dist/runtime/validation_error").default`}));let l=a.scopeName("validate");n.validateName=l;let d={gen:a,allErrors:this.opts.allErrors,data:$i.default.data,parentData:$i.default.parentData,parentDataProperty:$i.default.parentDataProperty,dataNames:[$i.default.data],dataPathArr:[sn.nil],dataLevel:0,dataTypes:[],definedProperties:new Set,topSchemaRef:a.scopeValue("schema",this.opts.code.source===!0?{ref:n.schema,code:(0,sn.stringify)(n.schema)}:{ref:n.schema}),validateName:l,ValidationError:s,schema:n.schema,schemaEnv:n,rootId:e,baseId:n.baseId||e,schemaPath:sn.nil,errSchemaPath:n.schemaPath||(this.opts.jtd?"":"#"),errorPath:(0,sn._)`""`,opts:this.opts,self:this},c;try{this._compilations.add(n),(0,HC.validateFunctionCode)(d),a.optimize(this.opts.code.optimize);let u=a.toString();c=`${a.scopeRefs($i.default.scope)}return ${u}`,this.opts.code.process&&(c=this.opts.code.process(c,n));let y=new Function(`${$i.default.self}`,`${$i.default.scope}`,c)(this,this.scope.get());if(this.scope.value(l,{ref:y}),y.errors=null,y.schema=n.schema,y.schemaEnv=n,n.$async&&(y.$async=!0),this.opts.code.source===!0&&(y.source={validateName:l,validateCode:u,scopeValues:a._values}),this.opts.unevaluated){let{props:_,items:b}=d;y.evaluated={props:_ instanceof sn.Name?void 0:_,items:b instanceof sn.Name?void 0:b,dynamicProps:_ instanceof sn.Name,dynamicItems:b instanceof sn.Name},y.source&&(y.source.evaluated=(0,sn.stringify)(y.evaluated))}return n.validate=y,n}catch(u){throw delete n.validate,delete n.validateName,c&&this.logger.error("Error compiling schema, function code:",c),u}finally{this._compilations.delete(n)}}Yt.compileSchema=lm;function qC(n,t,e){var i;e=(0,ln.resolveUrl)(this.opts.uriResolver,t,e);let r=n.refs[e];if(r)return r;let o=WC.call(this,n,e);if(o===void 0){let a=(i=n.localRefs)===null||i===void 0?void 0:i[e],{schemaId:s}=this.opts;a&&(o=new Mr({schema:a,schemaId:s,root:n,baseId:t}))}if(o!==void 0)return n.refs[e]=UC.call(this,o)}Yt.resolveRef=qC;function UC(n){return(0,ln.inlineRef)(n.schema,this.opts.inlineRefs)?n.schema:n.validate?n:lm.call(this,n)}function Lg(n){for(let t of this._compilations)if(GC(t,n))return t}Yt.getCompilingSchema=Lg;function GC(n,t){return n.schema===t.schema&&n.root===t.root&&n.baseId===t.baseId}function WC(n,t){let e;for(;typeof(e=this.refs[t])=="string";)t=e;return e||this.schemas[t]||sl.call(this,n,t)}function sl(n,t){let e=this.opts.uriResolver.parse(t),i=(0,ln._getFullPath)(this.opts.uriResolver,e),r=(0,ln.getFullPath)(this.opts.uriResolver,n.baseId,void 0);if(Object.keys(n.schema).length>0&&i===r)return sm.call(this,e,n);let o=(0,ln.normalizeId)(i),a=this.refs[o]||this.schemas[o];if(typeof a=="string"){let s=sl.call(this,n,a);return typeof s?.schema!="object"?void 0:sm.call(this,e,s)}if(typeof a?.schema=="object"){if(a.validate||lm.call(this,a),o===(0,ln.normalizeId)(t)){let{schema:s}=a,{schemaId:l}=this.opts,d=s[l];return d&&(r=(0,ln.resolveUrl)(this.opts.uriResolver,r,d)),new Mr({schema:s,schemaId:l,root:n,baseId:r})}return sm.call(this,e,a)}}Yt.resolveSchema=sl;var KC=new Set(["properties","patternProperties","enum","dependencies","definitions"]);function sm(n,{baseId:t,schema:e,root:i}){var r;if(((r=n.fragment)===null||r===void 0?void 0:r[0])!=="/")return;for(let s of n.fragment.slice(1).split("/")){if(typeof e=="boolean")return;let l=e[(0,Ng.unescapeFragment)(s)];if(l===void 0)return;e=l;let d=typeof e=="object"&&e[this.opts.schemaId];!KC.has(s)&&d&&(t=(0,ln.resolveUrl)(this.opts.uriResolver,t,d))}let o;if(typeof e!="boolean"&&e.$ref&&!(0,Ng.schemaHasRulesButRef)(e,this.RULES)){let s=(0,ln.resolveUrl)(this.opts.uriResolver,t,e.$ref);o=sl.call(this,i,s)}let{schemaId:a}=this.opts;if(o=o||new Mr({schema:e,schemaId:a,root:i,baseId:t}),o.schema!==o.root.schema)return o}});var Bg=F((GB,YC)=>{YC.exports={$id:"https://raw.githubusercontent.com/ajv-validator/ajv/master/lib/refs/data.json#",description:"Meta-schema for $data reference (JSON AnySchema extension proposal)",type:"object",required:["$data"],properties:{$data:{type:"string",anyOf:[{format:"relative-json-pointer"},{format:"json-pointer"}]}},additionalProperties:!1}});var dm=F((WB,zg)=>{"use strict";var QC=RegExp.prototype.test.bind(/^[\da-f]{8}-[\da-f]{4}-[\da-f]{4}-[\da-f]{4}-[\da-f]{12}$/iu),jg=RegExp.prototype.test.bind(/^(?:(?:25[0-5]|2[0-4]\d|1\d{2}|[1-9]\d|\d)\.){3}(?:25[0-5]|2[0-4]\d|1\d{2}|[1-9]\d|\d)$/u);function cm(n){let t="",e=0,i=0;for(i=0;i<n.length;i++)if(e=n[i].charCodeAt(0),e!==48){if(!(e>=48&&e<=57||e>=65&&e<=70||e>=97&&e<=102))return"";t+=n[i];break}for(i+=1;i<n.length;i++){if(e=n[i].charCodeAt(0),!(e>=48&&e<=57||e>=65&&e<=70||e>=97&&e<=102))return"";t+=n[i]}return t}var XC=RegExp.prototype.test.bind(/[^!"$&'()*+,\-.;=_`a-z{}~]/u);function $g(n){return n.length=0,!0}function ZC(n,t,e){if(n.length){let i=cm(n);if(i!=="")t.push(i);else return e.error=!0,!1;n.length=0}return!0}function JC(n){let t=0,e={error:!1,address:"",zone:""},i=[],r=[],o=!1,a=!1,s=ZC;for(let l=0;l<n.length;l++){let d=n[l];if(!(d==="["||d==="]"))if(d===":"){if(o===!0&&(a=!0),!s(r,i,e))break;if(++t>7){e.error=!0;break}l>0&&n[l-1]===":"&&(o=!0),i.push(":");continue}else if(d==="%"){if(!s(r,i,e))break;s=$g}else{r.push(d);continue}}return r.length&&(s===$g?e.zone=r.join(""):a?i.push(r.join("")):i.push(cm(r))),e.address=i.join(""),e}function Vg(n){if(ek(n,":")<2)return{host:n,isIPV6:!1};let t=JC(n);if(t.error)return{host:n,isIPV6:!1};{let e=t.address,i=t.address;return t.zone&&(e+="%"+t.zone,i+="%25"+t.zone),{host:e,isIPV6:!0,escapedHost:i}}}function ek(n,t){let e=0;for(let i=0;i<n.length;i++)n[i]===t&&e++;return e}function tk(n){let t=n,e=[],i=-1,r=0;for(;r=t.length;){if(r===1){if(t===".")break;if(t==="/"){e.push("/");break}else{e.push(t);break}}else if(r===2){if(t[0]==="."){if(t[1]===".")break;if(t[1]==="/"){t=t.slice(2);continue}}else if(t[0]==="/"&&(t[1]==="."||t[1]==="/")){e.push("/");break}}else if(r===3&&t==="/.."){e.length!==0&&e.pop(),e.push("/");break}if(t[0]==="."){if(t[1]==="."){if(t[2]==="/"){t=t.slice(3);continue}}else if(t[1]==="/"){t=t.slice(2);continue}}else if(t[0]==="/"&&t[1]==="."){if(t[2]==="/"){t=t.slice(2);continue}else if(t[2]==="."&&t[3]==="/"){t=t.slice(3),e.length!==0&&e.pop();continue}}if((i=t.indexOf("/",1))===-1){e.push(t);break}else e.push(t.slice(0,i)),t=t.slice(i)}return e.join("")}function nk(n,t){let e=t!==!0?escape:unescape;return n.scheme!==void 0&&(n.scheme=e(n.scheme)),n.userinfo!==void 0&&(n.userinfo=e(n.userinfo)),n.host!==void 0&&(n.host=e(n.host)),n.path!==void 0&&(n.path=e(n.path)),n.query!==void 0&&(n.query=e(n.query)),n.fragment!==void 0&&(n.fragment=e(n.fragment)),n}function ik(n){let t=[];if(n.userinfo!==void 0&&(t.push(n.userinfo),t.push("@")),n.host!==void 0){let e=unescape(n.host);if(!jg(e)){let i=Vg(e);i.isIPV6===!0?e=`[${i.escapedHost}]`:e=n.host}t.push(e)}return(typeof n.port=="number"||typeof n.port=="string")&&(t.push(":"),t.push(String(n.port))),t.length?t.join(""):void 0}zg.exports={nonSimpleDomain:XC,recomposeAuthority:ik,normalizeComponentEncoding:nk,removeDotSegments:tk,isIPv4:jg,isUUID:QC,normalizeIPv6:Vg,stringArrayToHexStripped:cm}});var Wg=F((KB,Gg)=>{"use strict";var{isUUID:rk}=dm(),ok=/([\da-z][\d\-a-z]{0,31}):((?:[\w!$'()*+,\-.:;=@]|%[\da-f]{2})+)/iu,ak=["http","https","ws","wss","urn","urn:uuid"];function sk(n){return ak.indexOf(n)!==-1}function mm(n){return n.secure===!0?!0:n.secure===!1?!1:n.scheme?n.scheme.length===3&&(n.scheme[0]==="w"||n.scheme[0]==="W")&&(n.scheme[1]==="s"||n.scheme[1]==="S")&&(n.scheme[2]==="s"||n.scheme[2]==="S"):!1}function Hg(n){return n.host||(n.error=n.error||"HTTP URIs must have a host."),n}function qg(n){let t=String(n.scheme).toLowerCase()==="https";return(n.port===(t?443:80)||n.port==="")&&(n.port=void 0),n.path||(n.path="/"),n}function lk(n){return n.secure=mm(n),n.resourceName=(n.path||"/")+(n.query?"?"+n.query:""),n.path=void 0,n.query=void 0,n}function ck(n){if((n.port===(mm(n)?443:80)||n.port==="")&&(n.port=void 0),typeof n.secure=="boolean"&&(n.scheme=n.secure?"wss":"ws",n.secure=void 0),n.resourceName){let[t,e]=n.resourceName.split("?");n.path=t&&t!=="/"?t:void 0,n.query=e,n.resourceName=void 0}return n.fragment=void 0,n}function dk(n,t){if(!n.path)return n.error="URN can not be parsed",n;let e=n.path.match(ok);if(e){let i=t.scheme||n.scheme||"urn";n.nid=e[1].toLowerCase(),n.nss=e[2];let r=`${i}:${t.nid||n.nid}`,o=um(r);n.path=void 0,o&&(n=o.parse(n,t))}else n.error=n.error||"URN can not be parsed.";return n}function mk(n,t){if(n.nid===void 0)throw new Error("URN without nid cannot be serialized");let e=t.scheme||n.scheme||"urn",i=n.nid.toLowerCase(),r=`${e}:${t.nid||i}`,o=um(r);o&&(n=o.serialize(n,t));let a=n,s=n.nss;return a.path=`${i||t.nid}:${s}`,t.skipEscape=!0,a}function uk(n,t){let e=n;return e.uuid=e.nss,e.nss=void 0,!t.tolerant&&(!e.uuid||!rk(e.uuid))&&(e.error=e.error||"UUID is not valid."),e}function pk(n){let t=n;return t.nss=(n.uuid||"").toLowerCase(),t}var Ug={scheme:"http",domainHost:!0,parse:Hg,serialize:qg},hk={scheme:"https",domainHost:Ug.domainHost,parse:Hg,serialize:qg},cl={scheme:"ws",domainHost:!0,parse:lk,serialize:ck},fk={scheme:"wss",domainHost:cl.domainHost,parse:cl.parse,serialize:cl.serialize},gk={scheme:"urn",parse:dk,serialize:mk,skipNormalize:!0},_k={scheme:"urn:uuid",parse:uk,serialize:pk,skipNormalize:!0},dl={http:Ug,https:hk,ws:cl,wss:fk,urn:gk,"urn:uuid":_k};Object.setPrototypeOf(dl,null);function um(n){return n&&(dl[n]||dl[n.toLowerCase()])||void 0}Gg.exports={wsIsSecure:mm,SCHEMES:dl,isValidSchemeName:sk,getSchemeHandler:um}});var Qg=F((YB,ul)=>{"use strict";var{normalizeIPv6:bk,removeDotSegments:ea,recomposeAuthority:vk,normalizeComponentEncoding:ml,isIPv4:yk,nonSimpleDomain:xk}=dm(),{SCHEMES:wk,getSchemeHandler:Kg}=Wg();function Ck(n,t){return typeof n=="string"?n=kn(qn(n,t),t):typeof n=="object"&&(n=qn(kn(n,t),t)),n}function kk(n,t,e){let i=e?Object.assign({scheme:"null"},e):{scheme:"null"},r=Yg(qn(n,i),qn(t,i),i,!0);return i.skipEscape=!0,kn(r,i)}function Yg(n,t,e,i){let r={};return i||(n=qn(kn(n,e),e),t=qn(kn(t,e),e)),e=e||{},!e.tolerant&&t.scheme?(r.scheme=t.scheme,r.userinfo=t.userinfo,r.host=t.host,r.port=t.port,r.path=ea(t.path||""),r.query=t.query):(t.userinfo!==void 0||t.host!==void 0||t.port!==void 0?(r.userinfo=t.userinfo,r.host=t.host,r.port=t.port,r.path=ea(t.path||""),r.query=t.query):(t.path?(t.path[0]==="/"?r.path=ea(t.path):((n.userinfo!==void 0||n.host!==void 0||n.port!==void 0)&&!n.path?r.path="/"+t.path:n.path?r.path=n.path.slice(0,n.path.lastIndexOf("/")+1)+t.path:r.path=t.path,r.path=ea(r.path)),r.query=t.query):(r.path=n.path,t.query!==void 0?r.query=t.query:r.query=n.query),r.userinfo=n.userinfo,r.host=n.host,r.port=n.port),r.scheme=n.scheme),r.fragment=t.fragment,r}function Dk(n,t,e){return typeof n=="string"?(n=unescape(n),n=kn(ml(qn(n,e),!0),ye(S({},e),{skipEscape:!0}))):typeof n=="object"&&(n=kn(ml(n,!0),ye(S({},e),{skipEscape:!0}))),typeof t=="string"?(t=unescape(t),t=kn(ml(qn(t,e),!0),ye(S({},e),{skipEscape:!0}))):typeof t=="object"&&(t=kn(ml(t,!0),ye(S({},e),{skipEscape:!0}))),n.toLowerCase()===t.toLowerCase()}function kn(n,t){let e={host:n.host,scheme:n.scheme,userinfo:n.userinfo,port:n.port,path:n.path,query:n.query,nid:n.nid,nss:n.nss,uuid:n.uuid,fragment:n.fragment,reference:n.reference,resourceName:n.resourceName,secure:n.secure,error:""},i=Object.assign({},t),r=[],o=Kg(i.scheme||e.scheme);o&&o.serialize&&o.serialize(e,i),e.path!==void 0&&(i.skipEscape?e.path=unescape(e.path):(e.path=escape(e.path),e.scheme!==void 0&&(e.path=e.path.split("%3A").join(":")))),i.reference!=="suffix"&&e.scheme&&r.push(e.scheme,":");let a=vk(e);if(a!==void 0&&(i.reference!=="suffix"&&r.push("//"),r.push(a),e.path&&e.path[0]!=="/"&&r.push("/")),e.path!==void 0){let s=e.path;!i.absolutePath&&(!o||!o.absolutePath)&&(s=ea(s)),a===void 0&&s[0]==="/"&&s[1]==="/"&&(s="/%2F"+s.slice(2)),r.push(s)}return e.query!==void 0&&r.push("?",e.query),e.fragment!==void 0&&r.push("#",e.fragment),r.join("")}var Ek=/^(?:([^#/:?]+):)?(?:\/\/((?:([^#/?@]*)@)?(\[[^#/?\]]+\]|[^#/:?]*)(?::(\d*))?))?([^#?]*)(?:\?([^#]*))?(?:#((?:.|[\n\r])*))?/u;function qn(n,t){let e=Object.assign({},t),i={scheme:void 0,userinfo:void 0,host:"",port:void 0,path:"",query:void 0,fragment:void 0},r=!1;e.reference==="suffix"&&(e.scheme?n=e.scheme+":"+n:n="//"+n);let o=n.match(Ek);if(o){if(i.scheme=o[1],i.userinfo=o[3],i.host=o[4],i.port=parseInt(o[5],10),i.path=o[6]||"",i.query=o[7],i.fragment=o[8],isNaN(i.port)&&(i.port=o[5]),i.host)if(yk(i.host)===!1){let l=bk(i.host);i.host=l.host.toLowerCase(),r=l.isIPV6}else r=!0;i.scheme===void 0&&i.userinfo===void 0&&i.host===void 0&&i.port===void 0&&i.query===void 0&&!i.path?i.reference="same-document":i.scheme===void 0?i.reference="relative":i.fragment===void 0?i.reference="absolute":i.reference="uri",e.reference&&e.reference!=="suffix"&&e.reference!==i.reference&&(i.error=i.error||"URI is not a "+e.reference+" reference.");let a=Kg(e.scheme||i.scheme);if(!e.unicodeSupport&&(!a||!a.unicodeSupport)&&i.host&&(e.domainHost||a&&a.domainHost)&&r===!1&&xk(i.host))try{i.host=URL.domainToASCII(i.host.toLowerCase())}catch(s){i.error=i.error||"Host's domain name can not be converted to ASCII: "+s}(!a||a&&!a.skipNormalize)&&(n.indexOf("%")!==-1&&(i.scheme!==void 0&&(i.scheme=unescape(i.scheme)),i.host!==void 0&&(i.host=unescape(i.host))),i.path&&(i.path=escape(unescape(i.path))),i.fragment&&(i.fragment=encodeURI(decodeURIComponent(i.fragment)))),a&&a.parse&&a.parse(i,e)}else i.error=i.error||"URI can not be parsed.";return i}var pm={SCHEMES:wk,normalize:Ck,resolve:kk,resolveComponent:Yg,equal:Dk,serialize:kn,parse:qn};ul.exports=pm;ul.exports.default=pm;ul.exports.fastUri=pm});var Zg=F(hm=>{"use strict";Object.defineProperty(hm,"__esModule",{value:!0});var Xg=Qg();Xg.code='require("ajv/dist/runtime/uri").default';hm.default=Xg});var a_=F(at=>{"use strict";Object.defineProperty(at,"__esModule",{value:!0});at.CodeGen=at.Name=at.nil=at.stringify=at.str=at._=at.KeywordCxt=void 0;var Sk=Zo();Object.defineProperty(at,"KeywordCxt",{enumerable:!0,get:function(){return Sk.KeywordCxt}});var Ar=oe();Object.defineProperty(at,"_",{enumerable:!0,get:function(){return Ar._}});Object.defineProperty(at,"str",{enumerable:!0,get:function(){return Ar.str}});Object.defineProperty(at,"stringify",{enumerable:!0,get:function(){return Ar.stringify}});Object.defineProperty(at,"nil",{enumerable:!0,get:function(){return Ar.nil}});Object.defineProperty(at,"Name",{enumerable:!0,get:function(){return Ar.Name}});Object.defineProperty(at,"CodeGen",{enumerable:!0,get:function(){return Ar.CodeGen}});var Mk=al(),i_=Jo(),Ak=Hd(),ta=ll(),Ik=oe(),na=Yo(),pl=Ko(),gm=he(),Jg=Bg(),Tk=Zg(),r_=(n,t)=>new RegExp(n,t);r_.code="new RegExp";var Ok=["removeAdditional","useDefaults","coerceTypes"],Rk=new Set(["validate","serialize","parse","wrapper","root","schema","keyword","pattern","formats","validate$data","func","obj","Error"]),Pk={errorDataPath:"",format:"`validateFormats: false` can be used instead.",nullable:'"nullable" keyword is supported by default.',jsonPointers:"Deprecated jsPropertySyntax can be used instead.",extendRefs:"Deprecated ignoreKeywordsWithRef can be used instead.",missingRefs:"Pass empty schema with $id that should be ignored to ajv.addSchema.",processCode:"Use option `code: {process: (code, schemaEnv: object) => string}`",sourceCode:"Use option `code: {source: true}`",strictDefaults:"It is default now, see option `strict`.",strictKeywords:"It is default now, see option `strict`.",uniqueItems:'"uniqueItems" keyword is always validated.',unknownFormats:"Disable strict mode or pass `true` to `ajv.addFormat` (or `formats` option).",cache:"Map is used as cache, schema object as key.",serialize:"Map is used as cache, schema object as key.",ajvErrors:"It is default now."},Fk={ignoreKeywordsWithRef:"",jsPropertySyntax:"",unicode:'"minLength"/"maxLength" account for unicode characters by default.'},e_=200;function Nk(n){var t,e,i,r,o,a,s,l,d,c,u,h,y,_,b,v,x,A,T,L,N,ve,ke,Pt,Ft;let Nt=n.strict,Sn=(t=n.code)===null||t===void 0?void 0:t.optimize,it=Sn===!0||Sn===void 0?1:Sn||0,Wn=(i=(e=n.code)===null||e===void 0?void 0:e.regExp)!==null&&i!==void 0?i:r_,un=(r=n.uriResolver)!==null&&r!==void 0?r:Tk.default;return{strictSchema:(a=(o=n.strictSchema)!==null&&o!==void 0?o:Nt)!==null&&a!==void 0?a:!0,strictNumbers:(l=(s=n.strictNumbers)!==null&&s!==void 0?s:Nt)!==null&&l!==void 0?l:!0,strictTypes:(c=(d=n.strictTypes)!==null&&d!==void 0?d:Nt)!==null&&c!==void 0?c:"log",strictTuples:(h=(u=n.strictTuples)!==null&&u!==void 0?u:Nt)!==null&&h!==void 0?h:"log",strictRequired:(_=(y=n.strictRequired)!==null&&y!==void 0?y:Nt)!==null&&_!==void 0?_:!1,code:n.code?ye(S({},n.code),{optimize:it,regExp:Wn}):{optimize:it,regExp:Wn},loopRequired:(b=n.loopRequired)!==null&&b!==void 0?b:e_,loopEnum:(v=n.loopEnum)!==null&&v!==void 0?v:e_,meta:(x=n.meta)!==null&&x!==void 0?x:!0,messages:(A=n.messages)!==null&&A!==void 0?A:!0,inlineRefs:(T=n.inlineRefs)!==null&&T!==void 0?T:!0,schemaId:(L=n.schemaId)!==null&&L!==void 0?L:"$id",addUsedSchema:(N=n.addUsedSchema)!==null&&N!==void 0?N:!0,validateSchema:(ve=n.validateSchema)!==null&&ve!==void 0?ve:!0,validateFormats:(ke=n.validateFormats)!==null&&ke!==void 0?ke:!0,unicodeRegExp:(Pt=n.unicodeRegExp)!==null&&Pt!==void 0?Pt:!0,int32range:(Ft=n.int32range)!==null&&Ft!==void 0?Ft:!0,uriResolver:un}}var ia=class{constructor(t={}){this.schemas={},this.refs={},this.formats=Object.create(null),this._compilations=new Set,this._loading={},this._cache=new Map,t=this.opts=S(S({},t),Nk(t));let{es5:e,lines:i}=this.opts.code;this.scope=new Ik.ValueScope({scope:{},prefixes:Rk,es5:e,lines:i}),this.logger=zk(t.logger);let r=t.validateFormats;t.validateFormats=!1,this.RULES=(0,Ak.getRules)(),t_.call(this,Pk,t,"NOT SUPPORTED"),t_.call(this,Fk,t,"DEPRECATED","warn"),this._metaOpts=jk.call(this),t.formats&&Bk.call(this),this._addVocabularies(),this._addDefaultMetaSchema(),t.keywords&&$k.call(this,t.keywords),typeof t.meta=="object"&&this.addMetaSchema(t.meta),Lk.call(this),t.validateFormats=r}_addVocabularies(){this.addKeyword("$async")}_addDefaultMetaSchema(){let{$data:t,meta:e,schemaId:i}=this.opts,r=Jg;i==="id"&&(r=S({},Jg),r.id=r.$id,delete r.$id),e&&t&&this.addMetaSchema(r,r[i],!1)}defaultMeta(){let{meta:t,schemaId:e}=this.opts;return this.opts.defaultMeta=typeof t=="object"?t[e]||t:void 0}validate(t,e){let i;if(typeof t=="string"){if(i=this.getSchema(t),!i)throw new Error(`no schema with key or ref "${t}"`)}else i=this.compile(t);let r=i(e);return"$async"in i||(this.errors=i.errors),r}compile(t,e){let i=this._addSchema(t,e);return i.validate||this._compileSchemaEnv(i)}compileAsync(t,e){if(typeof this.opts.loadSchema!="function")throw new Error("options.loadSchema should be a function");let{loadSchema:i}=this.opts;return r.call(this,t,e);async function r(c,u){await o.call(this,c.$schema);let h=this._addSchema(c,u);return h.validate||a.call(this,h)}async function o(c){c&&!this.getSchema(c)&&await r.call(this,{$ref:c},!0)}async function a(c){try{return this._compileSchemaEnv(c)}catch(u){if(!(u instanceof i_.default))throw u;return s.call(this,u),await l.call(this,u.missingSchema),a.call(this,c)}}function s({missingSchema:c,missingRef:u}){if(this.refs[c])throw new Error(`AnySchema ${c} is loaded but ${u} cannot be resolved`)}async function l(c){let u=await d.call(this,c);this.refs[c]||await o.call(this,u.$schema),this.refs[c]||this.addSchema(u,c,e)}async function d(c){let u=this._loading[c];if(u)return u;try{return await(this._loading[c]=i(c))}finally{delete this._loading[c]}}}addSchema(t,e,i,r=this.opts.validateSchema){if(Array.isArray(t)){for(let a of t)this.addSchema(a,void 0,i,r);return this}let o;if(typeof t=="object"){let{schemaId:a}=this.opts;if(o=t[a],o!==void 0&&typeof o!="string")throw new Error(`schema ${a} must be string`)}return e=(0,na.normalizeId)(e||o),this._checkUnique(e),this.schemas[e]=this._addSchema(t,i,e,r,!0),this}addMetaSchema(t,e,i=this.opts.validateSchema){return this.addSchema(t,e,!0,i),this}validateSchema(t,e){if(typeof t=="boolean")return!0;let i;if(i=t.$schema,i!==void 0&&typeof i!="string")throw new Error("$schema must be a string");if(i=i||this.opts.defaultMeta||this.defaultMeta(),!i)return this.logger.warn("meta-schema not available"),this.errors=null,!0;let r=this.validate(i,t);if(!r&&e){let o="schema is invalid: "+this.errorsText();if(this.opts.validateSchema==="log")this.logger.error(o);else throw new Error(o)}return r}getSchema(t){let e;for(;typeof(e=n_.call(this,t))=="string";)t=e;if(e===void 0){let{schemaId:i}=this.opts,r=new ta.SchemaEnv({schema:{},schemaId:i});if(e=ta.resolveSchema.call(this,r,t),!e)return;this.refs[t]=e}return e.validate||this._compileSchemaEnv(e)}removeSchema(t){if(t instanceof RegExp)return this._removeAllSchemas(this.schemas,t),this._removeAllSchemas(this.refs,t),this;switch(typeof t){case"undefined":return this._removeAllSchemas(this.schemas),this._removeAllSchemas(this.refs),this._cache.clear(),this;case"string":{let e=n_.call(this,t);return typeof e=="object"&&this._cache.delete(e.schema),delete this.schemas[t],delete this.refs[t],this}case"object":{let e=t;this._cache.delete(e);let i=t[this.opts.schemaId];return i&&(i=(0,na.normalizeId)(i),delete this.schemas[i],delete this.refs[i]),this}default:throw new Error("ajv.removeSchema: invalid parameter")}}addVocabulary(t){for(let e of t)this.addKeyword(e);return this}addKeyword(t,e){let i;if(typeof t=="string")i=t,typeof e=="object"&&(this.logger.warn("these parameters are deprecated, see docs for addKeyword"),e.keyword=i);else if(typeof t=="object"&&e===void 0){if(e=t,i=e.keyword,Array.isArray(i)&&!i.length)throw new Error("addKeywords: keyword must be string or non-empty array")}else throw new Error("invalid addKeywords parameters");if(qk.call(this,i,e),!e)return(0,gm.eachItem)(i,o=>fm.call(this,o)),this;Gk.call(this,e);let r=ye(S({},e),{type:(0,pl.getJSONTypes)(e.type),schemaType:(0,pl.getJSONTypes)(e.schemaType)});return(0,gm.eachItem)(i,r.type.length===0?o=>fm.call(this,o,r):o=>r.type.forEach(a=>fm.call(this,o,r,a))),this}getKeyword(t){let e=this.RULES.all[t];return typeof e=="object"?e.definition:!!e}removeKeyword(t){let{RULES:e}=this;delete e.keywords[t],delete e.all[t];for(let i of e.rules){let r=i.rules.findIndex(o=>o.keyword===t);r>=0&&i.rules.splice(r,1)}return this}addFormat(t,e){return typeof e=="string"&&(e=new RegExp(e)),this.formats[t]=e,this}errorsText(t=this.errors,{separator:e=", ",dataVar:i="data"}={}){return!t||t.length===0?"No errors":t.map(r=>`${i}${r.instancePath} ${r.message}`).reduce((r,o)=>r+e+o)}$dataMetaSchema(t,e){let i=this.RULES.all;t=JSON.parse(JSON.stringify(t));for(let r of e){let o=r.split("/").slice(1),a=t;for(let s of o)a=a[s];for(let s in i){let l=i[s];if(typeof l!="object")continue;let{$data:d}=l.definition,c=a[s];d&&c&&(a[s]=o_(c))}}return t}_removeAllSchemas(t,e){for(let i in t){let r=t[i];(!e||e.test(i))&&(typeof r=="string"?delete t[i]:r&&!r.meta&&(this._cache.delete(r.schema),delete t[i]))}}_addSchema(t,e,i,r=this.opts.validateSchema,o=this.opts.addUsedSchema){let a,{schemaId:s}=this.opts;if(typeof t=="object")a=t[s];else{if(this.opts.jtd)throw new Error("schema must be object");if(typeof t!="boolean")throw new Error("schema must be object or boolean")}let l=this._cache.get(t);if(l!==void 0)return l;i=(0,na.normalizeId)(a||i);let d=na.getSchemaRefs.call(this,t,i);return l=new ta.SchemaEnv({schema:t,schemaId:s,meta:e,baseId:i,localRefs:d}),this._cache.set(l.schema,l),o&&!i.startsWith("#")&&(i&&this._checkUnique(i),this.refs[i]=l),r&&this.validateSchema(t,!0),l}_checkUnique(t){if(this.schemas[t]||this.refs[t])throw new Error(`schema with key or id "${t}" already exists`)}_compileSchemaEnv(t){if(t.meta?this._compileMetaSchema(t):ta.compileSchema.call(this,t),!t.validate)throw new Error("ajv implementation error");return t.validate}_compileMetaSchema(t){let e=this.opts;this.opts=this._metaOpts;try{ta.compileSchema.call(this,t)}finally{this.opts=e}}};ia.ValidationError=Mk.default;ia.MissingRefError=i_.default;at.default=ia;function t_(n,t,e,i="error"){for(let r in n){let o=r;o in t&&this.logger[i](`${e}: option ${r}. ${n[o]}`)}}function n_(n){return n=(0,na.normalizeId)(n),this.schemas[n]||this.refs[n]}function Lk(){let n=this.opts.schemas;if(n)if(Array.isArray(n))this.addSchema(n);else for(let t in n)this.addSchema(n[t],t)}function Bk(){for(let n in this.opts.formats){let t=this.opts.formats[n];t&&this.addFormat(n,t)}}function $k(n){if(Array.isArray(n)){this.addVocabulary(n);return}this.logger.warn("keywords option as map is deprecated, pass array");for(let t in n){let e=n[t];e.keyword||(e.keyword=t),this.addKeyword(e)}}function jk(){let n=S({},this.opts);for(let t of Ok)delete n[t];return n}var Vk={log(){},warn(){},error(){}};function zk(n){if(n===!1)return Vk;if(n===void 0)return console;if(n.log&&n.warn&&n.error)return n;throw new Error("logger must implement log, warn and error methods")}var Hk=/^[a-z_$][a-z0-9_$:-]*$/i;function qk(n,t){let{RULES:e}=this;if((0,gm.eachItem)(n,i=>{if(e.keywords[i])throw new Error(`Keyword ${i} is already defined`);if(!Hk.test(i))throw new Error(`Keyword ${i} has invalid name`)}),!!t&&t.$data&&!("code"in t||"validate"in t))throw new Error('$data keyword must have "code" or "validate" function')}function fm(n,t,e){var i;let r=t?.post;if(e&&r)throw new Error('keyword with "post" flag cannot have "type"');let{RULES:o}=this,a=r?o.post:o.rules.find(({type:l})=>l===e);if(a||(a={type:e,rules:[]},o.rules.push(a)),o.keywords[n]=!0,!t)return;let s={keyword:n,definition:ye(S({},t),{type:(0,pl.getJSONTypes)(t.type),schemaType:(0,pl.getJSONTypes)(t.schemaType)})};t.before?Uk.call(this,a,s,t.before):a.rules.push(s),o.all[n]=s,(i=t.implements)===null||i===void 0||i.forEach(l=>this.addKeyword(l))}function Uk(n,t,e){let i=n.rules.findIndex(r=>r.keyword===e);i>=0?n.rules.splice(i,0,t):(n.rules.push(t),this.logger.warn(`rule ${e} is not defined`))}function Gk(n){let{metaSchema:t}=n;t!==void 0&&(n.$data&&this.opts.$data&&(t=o_(t)),n.validateSchema=this.compile(t,!0))}var Wk={$ref:"https://raw.githubusercontent.com/ajv-validator/ajv/master/lib/refs/data.json#"};function o_(n){return{anyOf:[n,Wk]}}});var s_=F(_m=>{"use strict";Object.defineProperty(_m,"__esModule",{value:!0});var Kk={keyword:"id",code(){throw new Error('NOT SUPPORTED: keyword "id", use "$id" for schema ID')}};_m.default=Kk});var m_=F(ji=>{"use strict";Object.defineProperty(ji,"__esModule",{value:!0});ji.callRef=ji.getValidate=void 0;var Yk=Jo(),l_=Kt(),Rt=oe(),Ir=zn(),c_=ll(),hl=he(),Qk={keyword:"$ref",schemaType:"string",code(n){let{gen:t,schema:e,it:i}=n,{baseId:r,schemaEnv:o,validateName:a,opts:s,self:l}=i,{root:d}=o;if((e==="#"||e==="#/")&&r===d.baseId)return u();let c=c_.resolveRef.call(l,d,r,e);if(c===void 0)throw new Yk.default(i.opts.uriResolver,r,e);if(c instanceof c_.SchemaEnv)return h(c);return y(c);function u(){if(o===d)return fl(n,a,o,o.$async);let _=t.scopeValue("root",{ref:d});return fl(n,(0,Rt._)`${_}.validate`,d,d.$async)}function h(_){let b=d_(n,_);fl(n,b,_,_.$async)}function y(_){let b=t.scopeValue("schema",s.code.source===!0?{ref:_,code:(0,Rt.stringify)(_)}:{ref:_}),v=t.name("valid"),x=n.subschema({schema:_,dataTypes:[],schemaPath:Rt.nil,topSchemaRef:b,errSchemaPath:e},v);n.mergeEvaluated(x),n.ok(v)}}};function d_(n,t){let{gen:e}=n;return t.validate?e.scopeValue("validate",{ref:t.validate}):(0,Rt._)`${e.scopeValue("wrapper",{ref:t})}.validate`}ji.getValidate=d_;function fl(n,t,e,i){let{gen:r,it:o}=n,{allErrors:a,schemaEnv:s,opts:l}=o,d=l.passContext?Ir.default.this:Rt.nil;i?c():u();function c(){if(!s.$async)throw new Error("async schema referenced by sync schema");let _=r.let("valid");r.try(()=>{r.code((0,Rt._)`await ${(0,l_.callValidateCode)(n,t,d)}`),y(t),a||r.assign(_,!0)},b=>{r.if((0,Rt._)`!(${b} instanceof ${o.ValidationError})`,()=>r.throw(b)),h(b),a||r.assign(_,!1)}),n.ok(_)}function u(){n.result((0,l_.callValidateCode)(n,t,d),()=>y(t),()=>h(t))}function h(_){let b=(0,Rt._)`${_}.errors`;r.assign(Ir.default.vErrors,(0,Rt._)`${Ir.default.vErrors} === null ? ${b} : ${Ir.default.vErrors}.concat(${b})`),r.assign(Ir.default.errors,(0,Rt._)`${Ir.default.vErrors}.length`)}function y(_){var b;if(!o.opts.unevaluated)return;let v=(b=e?.validate)===null||b===void 0?void 0:b.evaluated;if(o.props!==!0)if(v&&!v.dynamicProps)v.props!==void 0&&(o.props=hl.mergeEvaluated.props(r,v.props,o.props));else{let x=r.var("props",(0,Rt._)`${_}.evaluated.props`);o.props=hl.mergeEvaluated.props(r,x,o.props,Rt.Name)}if(o.items!==!0)if(v&&!v.dynamicItems)v.items!==void 0&&(o.items=hl.mergeEvaluated.items(r,v.items,o.items));else{let x=r.var("items",(0,Rt._)`${_}.evaluated.items`);o.items=hl.mergeEvaluated.items(r,x,o.items,Rt.Name)}}}ji.callRef=fl;ji.default=Qk});var u_=F(bm=>{"use strict";Object.defineProperty(bm,"__esModule",{value:!0});var Xk=s_(),Zk=m_(),Jk=["$schema","$id","$defs","$vocabulary",{keyword:"$comment"},"definitions",Xk.default,Zk.default];bm.default=Jk});var p_=F(vm=>{"use strict";Object.defineProperty(vm,"__esModule",{value:!0});var gl=oe(),hi=gl.operators,_l={maximum:{okStr:"<=",ok:hi.LTE,fail:hi.GT},minimum:{okStr:">=",ok:hi.GTE,fail:hi.LT},exclusiveMaximum:{okStr:"<",ok:hi.LT,fail:hi.GTE},exclusiveMinimum:{okStr:">",ok:hi.GT,fail:hi.LTE}},eD={message:({keyword:n,schemaCode:t})=>(0,gl.str)`must be ${_l[n].okStr} ${t}`,params:({keyword:n,schemaCode:t})=>(0,gl._)`{comparison: ${_l[n].okStr}, limit: ${t}}`},tD={keyword:Object.keys(_l),type:"number",schemaType:"number",$data:!0,error:eD,code(n){let{keyword:t,data:e,schemaCode:i}=n;n.fail$data((0,gl._)`${e} ${_l[t].fail} ${i} || isNaN(${e})`)}};vm.default=tD});var h_=F(ym=>{"use strict";Object.defineProperty(ym,"__esModule",{value:!0});var ra=oe(),nD={message:({schemaCode:n})=>(0,ra.str)`must be multiple of ${n}`,params:({schemaCode:n})=>(0,ra._)`{multipleOf: ${n}}`},iD={keyword:"multipleOf",type:"number",schemaType:"number",$data:!0,error:nD,code(n){let{gen:t,data:e,schemaCode:i,it:r}=n,o=r.opts.multipleOfPrecision,a=t.let("res"),s=o?(0,ra._)`Math.abs(Math.round(${a}) - ${a}) > 1e-${o}`:(0,ra._)`${a} !== parseInt(${a})`;n.fail$data((0,ra._)`(${i} === 0 || (${a} = ${e}/${i}, ${s}))`)}};ym.default=iD});var g_=F(xm=>{"use strict";Object.defineProperty(xm,"__esModule",{value:!0});function f_(n){let t=n.length,e=0,i=0,r;for(;i<t;)e++,r=n.charCodeAt(i++),r>=55296&&r<=56319&&i<t&&(r=n.charCodeAt(i),(r&64512)===56320&&i++);return e}xm.default=f_;f_.code='require("ajv/dist/runtime/ucs2length").default'});var __=F(wm=>{"use strict";Object.defineProperty(wm,"__esModule",{value:!0});var Vi=oe(),rD=he(),oD=g_(),aD={message({keyword:n,schemaCode:t}){let e=n==="maxLength"?"more":"fewer";return(0,Vi.str)`must NOT have ${e} than ${t} characters`},params:({schemaCode:n})=>(0,Vi._)`{limit: ${n}}`},sD={keyword:["maxLength","minLength"],type:"string",schemaType:"number",$data:!0,error:aD,code(n){let{keyword:t,data:e,schemaCode:i,it:r}=n,o=t==="maxLength"?Vi.operators.GT:Vi.operators.LT,a=r.opts.unicode===!1?(0,Vi._)`${e}.length`:(0,Vi._)`${(0,rD.useFunc)(n.gen,oD.default)}(${e})`;n.fail$data((0,Vi._)`${a} ${o} ${i}`)}};wm.default=sD});var b_=F(Cm=>{"use strict";Object.defineProperty(Cm,"__esModule",{value:!0});var lD=Kt(),cD=he(),Tr=oe(),dD={message:({schemaCode:n})=>(0,Tr.str)`must match pattern "${n}"`,params:({schemaCode:n})=>(0,Tr._)`{pattern: ${n}}`},mD={keyword:"pattern",type:"string",schemaType:"string",$data:!0,error:dD,code(n){let{gen:t,data:e,$data:i,schema:r,schemaCode:o,it:a}=n,s=a.opts.unicodeRegExp?"u":"";if(i){let{regExp:l}=a.opts.code,d=l.code==="new RegExp"?(0,Tr._)`new RegExp`:(0,cD.useFunc)(t,l),c=t.let("valid");t.try(()=>t.assign(c,(0,Tr._)`${d}(${o}, ${s}).test(${e})`),()=>t.assign(c,!1)),n.fail$data((0,Tr._)`!${c}`)}else{let l=(0,lD.usePattern)(n,r);n.fail$data((0,Tr._)`!${l}.test(${e})`)}}};Cm.default=mD});var v_=F(km=>{"use strict";Object.defineProperty(km,"__esModule",{value:!0});var oa=oe(),uD={message({keyword:n,schemaCode:t}){let e=n==="maxProperties"?"more":"fewer";return(0,oa.str)`must NOT have ${e} than ${t} properties`},params:({schemaCode:n})=>(0,oa._)`{limit: ${n}}`},pD={keyword:["maxProperties","minProperties"],type:"object",schemaType:"number",$data:!0,error:uD,code(n){let{keyword:t,data:e,schemaCode:i}=n,r=t==="maxProperties"?oa.operators.GT:oa.operators.LT;n.fail$data((0,oa._)`Object.keys(${e}).length ${r} ${i}`)}};km.default=pD});var y_=F(Dm=>{"use strict";Object.defineProperty(Dm,"__esModule",{value:!0});var aa=Kt(),sa=oe(),hD=he(),fD={message:({params:{missingProperty:n}})=>(0,sa.str)`must have required property '${n}'`,params:({params:{missingProperty:n}})=>(0,sa._)`{missingProperty: ${n}}`},gD={keyword:"required",type:"object",schemaType:"array",$data:!0,error:fD,code(n){let{gen:t,schema:e,schemaCode:i,data:r,$data:o,it:a}=n,{opts:s}=a;if(!o&&e.length===0)return;let l=e.length>=s.loopRequired;if(a.allErrors?d():c(),s.strictRequired){let y=n.parentSchema.properties,{definedProperties:_}=n.it;for(let b of e)if(y?.[b]===void 0&&!_.has(b)){let v=a.schemaEnv.baseId+a.errSchemaPath,x=`required property "${b}" is not defined at "${v}" (strictRequired)`;(0,hD.checkStrictMode)(a,x,a.opts.strictRequired)}}function d(){if(l||o)n.block$data(sa.nil,u);else for(let y of e)(0,aa.checkReportMissingProp)(n,y)}function c(){let y=t.let("missing");if(l||o){let _=t.let("valid",!0);n.block$data(_,()=>h(y,_)),n.ok(_)}else t.if((0,aa.checkMissingProp)(n,e,y)),(0,aa.reportMissingProp)(n,y),t.else()}function u(){t.forOf("prop",i,y=>{n.setParams({missingProperty:y}),t.if((0,aa.noPropertyInData)(t,r,y,s.ownProperties),()=>n.error())})}function h(y,_){n.setParams({missingProperty:y}),t.forOf(y,i,()=>{t.assign(_,(0,aa.propertyInData)(t,r,y,s.ownProperties)),t.if((0,sa.not)(_),()=>{n.error(),t.break()})},sa.nil)}}};Dm.default=gD});var x_=F(Em=>{"use strict";Object.defineProperty(Em,"__esModule",{value:!0});var la=oe(),_D={message({keyword:n,schemaCode:t}){let e=n==="maxItems"?"more":"fewer";return(0,la.str)`must NOT have ${e} than ${t} items`},params:({schemaCode:n})=>(0,la._)`{limit: ${n}}`},bD={keyword:["maxItems","minItems"],type:"array",schemaType:"number",$data:!0,error:_D,code(n){let{keyword:t,data:e,schemaCode:i}=n,r=t==="maxItems"?la.operators.GT:la.operators.LT;n.fail$data((0,la._)`${e}.length ${r} ${i}`)}};Em.default=bD});var bl=F(Sm=>{"use strict";Object.defineProperty(Sm,"__esModule",{value:!0});var w_=Xd();w_.code='require("ajv/dist/runtime/equal").default';Sm.default=w_});var C_=F(Am=>{"use strict";Object.defineProperty(Am,"__esModule",{value:!0});var Mm=Ko(),st=oe(),vD=he(),yD=bl(),xD={message:({params:{i:n,j:t}})=>(0,st.str)`must NOT have duplicate items (items ## ${t} and ${n} are identical)`,params:({params:{i:n,j:t}})=>(0,st._)`{i: ${n}, j: ${t}}`},wD={keyword:"uniqueItems",type:"array",schemaType:"boolean",$data:!0,error:xD,code(n){let{gen:t,data:e,$data:i,schema:r,parentSchema:o,schemaCode:a,it:s}=n;if(!i&&!r)return;let l=t.let("valid"),d=o.items?(0,Mm.getSchemaTypes)(o.items):[];n.block$data(l,c,(0,st._)`${a} === false`),n.ok(l);function c(){let _=t.let("i",(0,st._)`${e}.length`),b=t.let("j");n.setParams({i:_,j:b}),t.assign(l,!0),t.if((0,st._)`${_} > 1`,()=>(u()?h:y)(_,b))}function u(){return d.length>0&&!d.some(_=>_==="object"||_==="array")}function h(_,b){let v=t.name("item"),x=(0,Mm.checkDataTypes)(d,v,s.opts.strictNumbers,Mm.DataType.Wrong),A=t.const("indices",(0,st._)`{}`);t.for((0,st._)`;${_}--;`,()=>{t.let(v,(0,st._)`${e}[${_}]`),t.if(x,(0,st._)`continue`),d.length>1&&t.if((0,st._)`typeof ${v} == "string"`,(0,st._)`${v} += "_"`),t.if((0,st._)`typeof ${A}[${v}] == "number"`,()=>{t.assign(b,(0,st._)`${A}[${v}]`),n.error(),t.assign(l,!1).break()}).code((0,st._)`${A}[${v}] = ${_}`)})}function y(_,b){let v=(0,vD.useFunc)(t,yD.default),x=t.name("outer");t.label(x).for((0,st._)`;${_}--;`,()=>t.for((0,st._)`${b} = ${_}; ${b}--;`,()=>t.if((0,st._)`${v}(${e}[${_}], ${e}[${b}])`,()=>{n.error(),t.assign(l,!1).break(x)})))}}};Am.default=wD});var k_=F(Tm=>{"use strict";Object.defineProperty(Tm,"__esModule",{value:!0});var Im=oe(),CD=he(),kD=bl(),DD={message:"must be equal to constant",params:({schemaCode:n})=>(0,Im._)`{allowedValue: ${n}}`},ED={keyword:"const",$data:!0,error:DD,code(n){let{gen:t,data:e,$data:i,schemaCode:r,schema:o}=n;i||o&&typeof o=="object"?n.fail$data((0,Im._)`!${(0,CD.useFunc)(t,kD.default)}(${e}, ${r})`):n.fail((0,Im._)`${o} !== ${e}`)}};Tm.default=ED});var D_=F(Om=>{"use strict";Object.defineProperty(Om,"__esModule",{value:!0});var ca=oe(),SD=he(),MD=bl(),AD={message:"must be equal to one of the allowed values",params:({schemaCode:n})=>(0,ca._)`{allowedValues: ${n}}`},ID={keyword:"enum",schemaType:"array",$data:!0,error:AD,code(n){let{gen:t,data:e,$data:i,schema:r,schemaCode:o,it:a}=n;if(!i&&r.length===0)throw new Error("enum must have non-empty array");let s=r.length>=a.opts.loopEnum,l,d=()=>l??(l=(0,SD.useFunc)(t,MD.default)),c;if(s||i)c=t.let("valid"),n.block$data(c,u);else{if(!Array.isArray(r))throw new Error("ajv implementation error");let y=t.const("vSchema",o);c=(0,ca.or)(...r.map((_,b)=>h(y,b)))}n.pass(c);function u(){t.assign(c,!1),t.forOf("v",o,y=>t.if((0,ca._)`${d()}(${e}, ${y})`,()=>t.assign(c,!0).break()))}function h(y,_){let b=r[_];return typeof b=="object"&&b!==null?(0,ca._)`${d()}(${e}, ${y}[${_}])`:(0,ca._)`${e} === ${b}`}}};Om.default=ID});var E_=F(Rm=>{"use strict";Object.defineProperty(Rm,"__esModule",{value:!0});var TD=p_(),OD=h_(),RD=__(),PD=b_(),FD=v_(),ND=y_(),LD=x_(),BD=C_(),$D=k_(),jD=D_(),VD=[TD.default,OD.default,RD.default,PD.default,FD.default,ND.default,LD.default,BD.default,{keyword:"type",schemaType:["string","array"]},{keyword:"nullable",schemaType:"boolean"},$D.default,jD.default];Rm.default=VD});var Fm=F(da=>{"use strict";Object.defineProperty(da,"__esModule",{value:!0});da.validateAdditionalItems=void 0;var zi=oe(),Pm=he(),zD={message:({params:{len:n}})=>(0,zi.str)`must NOT have more than ${n} items`,params:({params:{len:n}})=>(0,zi._)`{limit: ${n}}`},HD={keyword:"additionalItems",type:"array",schemaType:["boolean","object"],before:"uniqueItems",error:zD,code(n){let{parentSchema:t,it:e}=n,{items:i}=t;if(!Array.isArray(i)){(0,Pm.checkStrictMode)(e,'"additionalItems" is ignored when "items" is not an array of schemas');return}S_(n,i)}};function S_(n,t){let{gen:e,schema:i,data:r,keyword:o,it:a}=n;a.items=!0;let s=e.const("len",(0,zi._)`${r}.length`);if(i===!1)n.setParams({len:t.length}),n.pass((0,zi._)`${s} <= ${t.length}`);else if(typeof i=="object"&&!(0,Pm.alwaysValidSchema)(a,i)){let d=e.var("valid",(0,zi._)`${s} <= ${t.length}`);e.if((0,zi.not)(d),()=>l(d)),n.ok(d)}function l(d){e.forRange("i",t.length,s,c=>{n.subschema({keyword:o,dataProp:c,dataPropType:Pm.Type.Num},d),a.allErrors||e.if((0,zi.not)(d),()=>e.break())})}}da.validateAdditionalItems=S_;da.default=HD});var Nm=F(ma=>{"use strict";Object.defineProperty(ma,"__esModule",{value:!0});ma.validateTuple=void 0;var M_=oe(),vl=he(),qD=Kt(),UD={keyword:"items",type:"array",schemaType:["object","array","boolean"],before:"uniqueItems",code(n){let{schema:t,it:e}=n;if(Array.isArray(t))return A_(n,"additionalItems",t);e.items=!0,!(0,vl.alwaysValidSchema)(e,t)&&n.ok((0,qD.validateArray)(n))}};function A_(n,t,e=n.schema){let{gen:i,parentSchema:r,data:o,keyword:a,it:s}=n;c(r),s.opts.unevaluated&&e.length&&s.items!==!0&&(s.items=vl.mergeEvaluated.items(i,e.length,s.items));let l=i.name("valid"),d=i.const("len",(0,M_._)`${o}.length`);e.forEach((u,h)=>{(0,vl.alwaysValidSchema)(s,u)||(i.if((0,M_._)`${d} > ${h}`,()=>n.subschema({keyword:a,schemaProp:h,dataProp:h},l)),n.ok(l))});function c(u){let{opts:h,errSchemaPath:y}=s,_=e.length,b=_===u.minItems&&(_===u.maxItems||u[t]===!1);if(h.strictTuples&&!b){let v=`"${a}" is ${_}-tuple, but minItems or maxItems/${t} are not specified or different at path "${y}"`;(0,vl.checkStrictMode)(s,v,h.strictTuples)}}}ma.validateTuple=A_;ma.default=UD});var I_=F(Lm=>{"use strict";Object.defineProperty(Lm,"__esModule",{value:!0});var GD=Nm(),WD={keyword:"prefixItems",type:"array",schemaType:["array"],before:"uniqueItems",code:n=>(0,GD.validateTuple)(n,"items")};Lm.default=WD});var O_=F(Bm=>{"use strict";Object.defineProperty(Bm,"__esModule",{value:!0});var T_=oe(),KD=he(),YD=Kt(),QD=Fm(),XD={message:({params:{len:n}})=>(0,T_.str)`must NOT have more than ${n} items`,params:({params:{len:n}})=>(0,T_._)`{limit: ${n}}`},ZD={keyword:"items",type:"array",schemaType:["object","boolean"],before:"uniqueItems",error:XD,code(n){let{schema:t,parentSchema:e,it:i}=n,{prefixItems:r}=e;i.items=!0,!(0,KD.alwaysValidSchema)(i,t)&&(r?(0,QD.validateAdditionalItems)(n,r):n.ok((0,YD.validateArray)(n)))}};Bm.default=ZD});var R_=F($m=>{"use strict";Object.defineProperty($m,"__esModule",{value:!0});var Qt=oe(),yl=he(),JD={message:({params:{min:n,max:t}})=>t===void 0?(0,Qt.str)`must contain at least ${n} valid item(s)`:(0,Qt.str)`must contain at least ${n} and no more than ${t} valid item(s)`,params:({params:{min:n,max:t}})=>t===void 0?(0,Qt._)`{minContains: ${n}}`:(0,Qt._)`{minContains: ${n}, maxContains: ${t}}`},eE={keyword:"contains",type:"array",schemaType:["object","boolean"],before:"uniqueItems",trackErrors:!0,error:JD,code(n){let{gen:t,schema:e,parentSchema:i,data:r,it:o}=n,a,s,{minContains:l,maxContains:d}=i;o.opts.next?(a=l===void 0?1:l,s=d):a=1;let c=t.const("len",(0,Qt._)`${r}.length`);if(n.setParams({min:a,max:s}),s===void 0&&a===0){(0,yl.checkStrictMode)(o,'"minContains" == 0 without "maxContains": "contains" keyword ignored');return}if(s!==void 0&&a>s){(0,yl.checkStrictMode)(o,'"minContains" > "maxContains" is always invalid'),n.fail();return}if((0,yl.alwaysValidSchema)(o,e)){let b=(0,Qt._)`${c} >= ${a}`;s!==void 0&&(b=(0,Qt._)`${b} && ${c} <= ${s}`),n.pass(b);return}o.items=!0;let u=t.name("valid");s===void 0&&a===1?y(u,()=>t.if(u,()=>t.break())):a===0?(t.let(u,!0),s!==void 0&&t.if((0,Qt._)`${r}.length > 0`,h)):(t.let(u,!1),h()),n.result(u,()=>n.reset());function h(){let b=t.name("_valid"),v=t.let("count",0);y(b,()=>t.if(b,()=>_(v)))}function y(b,v){t.forRange("i",0,c,x=>{n.subschema({keyword:"contains",dataProp:x,dataPropType:yl.Type.Num,compositeRule:!0},b),v()})}function _(b){t.code((0,Qt._)`${b}++`),s===void 0?t.if((0,Qt._)`${b} >= ${a}`,()=>t.assign(u,!0).break()):(t.if((0,Qt._)`${b} > ${s}`,()=>t.assign(u,!1).break()),a===1?t.assign(u,!0):t.if((0,Qt._)`${b} >= ${a}`,()=>t.assign(u,!0)))}}};$m.default=eE});var N_=F(Dn=>{"use strict";Object.defineProperty(Dn,"__esModule",{value:!0});Dn.validateSchemaDeps=Dn.validatePropertyDeps=Dn.error=void 0;var jm=oe(),tE=he(),ua=Kt();Dn.error={message:({params:{property:n,depsCount:t,deps:e}})=>{let i=t===1?"property":"properties";return(0,jm.str)`must have ${i} ${e} when property ${n} is present`},params:({params:{property:n,depsCount:t,deps:e,missingProperty:i}})=>(0,jm._)`{property: ${n},
    missingProperty: ${i},
    depsCount: ${t},
    deps: ${e}}`};var nE={keyword:"dependencies",type:"object",schemaType:"object",error:Dn.error,code(n){let[t,e]=iE(n);P_(n,t),F_(n,e)}};function iE({schema:n}){let t={},e={};for(let i in n){if(i==="__proto__")continue;let r=Array.isArray(n[i])?t:e;r[i]=n[i]}return[t,e]}function P_(n,t=n.schema){let{gen:e,data:i,it:r}=n;if(Object.keys(t).length===0)return;let o=e.let("missing");for(let a in t){let s=t[a];if(s.length===0)continue;let l=(0,ua.propertyInData)(e,i,a,r.opts.ownProperties);n.setParams({property:a,depsCount:s.length,deps:s.join(", ")}),r.allErrors?e.if(l,()=>{for(let d of s)(0,ua.checkReportMissingProp)(n,d)}):(e.if((0,jm._)`${l} && (${(0,ua.checkMissingProp)(n,s,o)})`),(0,ua.reportMissingProp)(n,o),e.else())}}Dn.validatePropertyDeps=P_;function F_(n,t=n.schema){let{gen:e,data:i,keyword:r,it:o}=n,a=e.name("valid");for(let s in t)(0,tE.alwaysValidSchema)(o,t[s])||(e.if((0,ua.propertyInData)(e,i,s,o.opts.ownProperties),()=>{let l=n.subschema({keyword:r,schemaProp:s},a);n.mergeValidEvaluated(l,a)},()=>e.var(a,!0)),n.ok(a))}Dn.validateSchemaDeps=F_;Dn.default=nE});var B_=F(Vm=>{"use strict";Object.defineProperty(Vm,"__esModule",{value:!0});var L_=oe(),rE=he(),oE={message:"property name must be valid",params:({params:n})=>(0,L_._)`{propertyName: ${n.propertyName}}`},aE={keyword:"propertyNames",type:"object",schemaType:["object","boolean"],error:oE,code(n){let{gen:t,schema:e,data:i,it:r}=n;if((0,rE.alwaysValidSchema)(r,e))return;let o=t.name("valid");t.forIn("key",i,a=>{n.setParams({propertyName:a}),n.subschema({keyword:"propertyNames",data:a,dataTypes:["string"],propertyName:a,compositeRule:!0},o),t.if((0,L_.not)(o),()=>{n.error(!0),r.allErrors||t.break()})}),n.ok(o)}};Vm.default=aE});var Hm=F(zm=>{"use strict";Object.defineProperty(zm,"__esModule",{value:!0});var xl=Kt(),cn=oe(),sE=zn(),wl=he(),lE={message:"must NOT have additional properties",params:({params:n})=>(0,cn._)`{additionalProperty: ${n.additionalProperty}}`},cE={keyword:"additionalProperties",type:["object"],schemaType:["boolean","object"],allowUndefined:!0,trackErrors:!0,error:lE,code(n){let{gen:t,schema:e,parentSchema:i,data:r,errsCount:o,it:a}=n;if(!o)throw new Error("ajv implementation error");let{allErrors:s,opts:l}=a;if(a.props=!0,l.removeAdditional!=="all"&&(0,wl.alwaysValidSchema)(a,e))return;let d=(0,xl.allSchemaProperties)(i.properties),c=(0,xl.allSchemaProperties)(i.patternProperties);u(),n.ok((0,cn._)`${o} === ${sE.default.errors}`);function u(){t.forIn("key",r,v=>{!d.length&&!c.length?_(v):t.if(h(v),()=>_(v))})}function h(v){let x;if(d.length>8){let A=(0,wl.schemaRefOrVal)(a,i.properties,"properties");x=(0,xl.isOwnProperty)(t,A,v)}else d.length?x=(0,cn.or)(...d.map(A=>(0,cn._)`${v} === ${A}`)):x=cn.nil;return c.length&&(x=(0,cn.or)(x,...c.map(A=>(0,cn._)`${(0,xl.usePattern)(n,A)}.test(${v})`))),(0,cn.not)(x)}function y(v){t.code((0,cn._)`delete ${r}[${v}]`)}function _(v){if(l.removeAdditional==="all"||l.removeAdditional&&e===!1){y(v);return}if(e===!1){n.setParams({additionalProperty:v}),n.error(),s||t.break();return}if(typeof e=="object"&&!(0,wl.alwaysValidSchema)(a,e)){let x=t.name("valid");l.removeAdditional==="failing"?(b(v,x,!1),t.if((0,cn.not)(x),()=>{n.reset(),y(v)})):(b(v,x),s||t.if((0,cn.not)(x),()=>t.break()))}}function b(v,x,A){let T={keyword:"additionalProperties",dataProp:v,dataPropType:wl.Type.Str};A===!1&&Object.assign(T,{compositeRule:!0,createErrors:!1,allErrors:!1}),n.subschema(T,x)}}};zm.default=cE});var V_=F(Um=>{"use strict";Object.defineProperty(Um,"__esModule",{value:!0});var dE=Zo(),$_=Kt(),qm=he(),j_=Hm(),mE={keyword:"properties",type:"object",schemaType:"object",code(n){let{gen:t,schema:e,parentSchema:i,data:r,it:o}=n;o.opts.removeAdditional==="all"&&i.additionalProperties===void 0&&j_.default.code(new dE.KeywordCxt(o,j_.default,"additionalProperties"));let a=(0,$_.allSchemaProperties)(e);for(let u of a)o.definedProperties.add(u);o.opts.unevaluated&&a.length&&o.props!==!0&&(o.props=qm.mergeEvaluated.props(t,(0,qm.toHash)(a),o.props));let s=a.filter(u=>!(0,qm.alwaysValidSchema)(o,e[u]));if(s.length===0)return;let l=t.name("valid");for(let u of s)d(u)?c(u):(t.if((0,$_.propertyInData)(t,r,u,o.opts.ownProperties)),c(u),o.allErrors||t.else().var(l,!0),t.endIf()),n.it.definedProperties.add(u),n.ok(l);function d(u){return o.opts.useDefaults&&!o.compositeRule&&e[u].default!==void 0}function c(u){n.subschema({keyword:"properties",schemaProp:u,dataProp:u},l)}}};Um.default=mE});var U_=F(Gm=>{"use strict";Object.defineProperty(Gm,"__esModule",{value:!0});var z_=Kt(),Cl=oe(),H_=he(),q_=he(),uE={keyword:"patternProperties",type:"object",schemaType:"object",code(n){let{gen:t,schema:e,data:i,parentSchema:r,it:o}=n,{opts:a}=o,s=(0,z_.allSchemaProperties)(e),l=s.filter(b=>(0,H_.alwaysValidSchema)(o,e[b]));if(s.length===0||l.length===s.length&&(!o.opts.unevaluated||o.props===!0))return;let d=a.strictSchema&&!a.allowMatchingProperties&&r.properties,c=t.name("valid");o.props!==!0&&!(o.props instanceof Cl.Name)&&(o.props=(0,q_.evaluatedPropsToName)(t,o.props));let{props:u}=o;h();function h(){for(let b of s)d&&y(b),o.allErrors?_(b):(t.var(c,!0),_(b),t.if(c))}function y(b){for(let v in d)new RegExp(b).test(v)&&(0,H_.checkStrictMode)(o,`property ${v} matches pattern ${b} (use allowMatchingProperties)`)}function _(b){t.forIn("key",i,v=>{t.if((0,Cl._)`${(0,z_.usePattern)(n,b)}.test(${v})`,()=>{let x=l.includes(b);x||n.subschema({keyword:"patternProperties",schemaProp:b,dataProp:v,dataPropType:q_.Type.Str},c),o.opts.unevaluated&&u!==!0?t.assign((0,Cl._)`${u}[${v}]`,!0):!x&&!o.allErrors&&t.if((0,Cl.not)(c),()=>t.break())})})}}};Gm.default=uE});var G_=F(Wm=>{"use strict";Object.defineProperty(Wm,"__esModule",{value:!0});var pE=he(),hE={keyword:"not",schemaType:["object","boolean"],trackErrors:!0,code(n){let{gen:t,schema:e,it:i}=n;if((0,pE.alwaysValidSchema)(i,e)){n.fail();return}let r=t.name("valid");n.subschema({keyword:"not",compositeRule:!0,createErrors:!1,allErrors:!1},r),n.failResult(r,()=>n.reset(),()=>n.error())},error:{message:"must NOT be valid"}};Wm.default=hE});var W_=F(Km=>{"use strict";Object.defineProperty(Km,"__esModule",{value:!0});var fE=Kt(),gE={keyword:"anyOf",schemaType:"array",trackErrors:!0,code:fE.validateUnion,error:{message:"must match a schema in anyOf"}};Km.default=gE});var K_=F(Ym=>{"use strict";Object.defineProperty(Ym,"__esModule",{value:!0});var kl=oe(),_E=he(),bE={message:"must match exactly one schema in oneOf",params:({params:n})=>(0,kl._)`{passingSchemas: ${n.passing}}`},vE={keyword:"oneOf",schemaType:"array",trackErrors:!0,error:bE,code(n){let{gen:t,schema:e,parentSchema:i,it:r}=n;if(!Array.isArray(e))throw new Error("ajv implementation error");if(r.opts.discriminator&&i.discriminator)return;let o=e,a=t.let("valid",!1),s=t.let("passing",null),l=t.name("_valid");n.setParams({passing:s}),t.block(d),n.result(a,()=>n.reset(),()=>n.error(!0));function d(){o.forEach((c,u)=>{let h;(0,_E.alwaysValidSchema)(r,c)?t.var(l,!0):h=n.subschema({keyword:"oneOf",schemaProp:u,compositeRule:!0},l),u>0&&t.if((0,kl._)`${l} && ${a}`).assign(a,!1).assign(s,(0,kl._)`[${s}, ${u}]`).else(),t.if(l,()=>{t.assign(a,!0),t.assign(s,u),h&&n.mergeEvaluated(h,kl.Name)})})}}};Ym.default=vE});var Y_=F(Qm=>{"use strict";Object.defineProperty(Qm,"__esModule",{value:!0});var yE=he(),xE={keyword:"allOf",schemaType:"array",code(n){let{gen:t,schema:e,it:i}=n;if(!Array.isArray(e))throw new Error("ajv implementation error");let r=t.name("valid");e.forEach((o,a)=>{if((0,yE.alwaysValidSchema)(i,o))return;let s=n.subschema({keyword:"allOf",schemaProp:a},r);n.ok(r),n.mergeEvaluated(s)})}};Qm.default=xE});var Z_=F(Xm=>{"use strict";Object.defineProperty(Xm,"__esModule",{value:!0});var Dl=oe(),X_=he(),wE={message:({params:n})=>(0,Dl.str)`must match "${n.ifClause}" schema`,params:({params:n})=>(0,Dl._)`{failingKeyword: ${n.ifClause}}`},CE={keyword:"if",schemaType:["object","boolean"],trackErrors:!0,error:wE,code(n){let{gen:t,parentSchema:e,it:i}=n;e.then===void 0&&e.else===void 0&&(0,X_.checkStrictMode)(i,'"if" without "then" and "else" is ignored');let r=Q_(i,"then"),o=Q_(i,"else");if(!r&&!o)return;let a=t.let("valid",!0),s=t.name("_valid");if(l(),n.reset(),r&&o){let c=t.let("ifClause");n.setParams({ifClause:c}),t.if(s,d("then",c),d("else",c))}else r?t.if(s,d("then")):t.if((0,Dl.not)(s),d("else"));n.pass(a,()=>n.error(!0));function l(){let c=n.subschema({keyword:"if",compositeRule:!0,createErrors:!1,allErrors:!1},s);n.mergeEvaluated(c)}function d(c,u){return()=>{let h=n.subschema({keyword:c},s);t.assign(a,s),n.mergeValidEvaluated(h,a),u?t.assign(u,(0,Dl._)`${c}`):n.setParams({ifClause:c})}}}};function Q_(n,t){let e=n.schema[t];return e!==void 0&&!(0,X_.alwaysValidSchema)(n,e)}Xm.default=CE});var J_=F(Zm=>{"use strict";Object.defineProperty(Zm,"__esModule",{value:!0});var kE=he(),DE={keyword:["then","else"],schemaType:["object","boolean"],code({keyword:n,parentSchema:t,it:e}){t.if===void 0&&(0,kE.checkStrictMode)(e,`"${n}" without "if" is ignored`)}};Zm.default=DE});var eb=F(Jm=>{"use strict";Object.defineProperty(Jm,"__esModule",{value:!0});var EE=Fm(),SE=I_(),ME=Nm(),AE=O_(),IE=R_(),TE=N_(),OE=B_(),RE=Hm(),PE=V_(),FE=U_(),NE=G_(),LE=W_(),BE=K_(),$E=Y_(),jE=Z_(),VE=J_();function zE(n=!1){let t=[NE.default,LE.default,BE.default,$E.default,jE.default,VE.default,OE.default,RE.default,TE.default,PE.default,FE.default];return n?t.push(SE.default,AE.default):t.push(EE.default,ME.default),t.push(IE.default),t}Jm.default=zE});var tb=F(eu=>{"use strict";Object.defineProperty(eu,"__esModule",{value:!0});var We=oe(),HE={message:({schemaCode:n})=>(0,We.str)`must match format "${n}"`,params:({schemaCode:n})=>(0,We._)`{format: ${n}}`},qE={keyword:"format",type:["number","string"],schemaType:"string",$data:!0,error:HE,code(n,t){let{gen:e,data:i,$data:r,schema:o,schemaCode:a,it:s}=n,{opts:l,errSchemaPath:d,schemaEnv:c,self:u}=s;if(!l.validateFormats)return;r?h():y();function h(){let _=e.scopeValue("formats",{ref:u.formats,code:l.code.formats}),b=e.const("fDef",(0,We._)`${_}[${a}]`),v=e.let("fType"),x=e.let("format");e.if((0,We._)`typeof ${b} == "object" && !(${b} instanceof RegExp)`,()=>e.assign(v,(0,We._)`${b}.type || "string"`).assign(x,(0,We._)`${b}.validate`),()=>e.assign(v,(0,We._)`"string"`).assign(x,b)),n.fail$data((0,We.or)(A(),T()));function A(){return l.strictSchema===!1?We.nil:(0,We._)`${a} && !${x}`}function T(){let L=c.$async?(0,We._)`(${b}.async ? await ${x}(${i}) : ${x}(${i}))`:(0,We._)`${x}(${i})`,N=(0,We._)`(typeof ${x} == "function" ? ${L} : ${x}.test(${i}))`;return(0,We._)`${x} && ${x} !== true && ${v} === ${t} && !${N}`}}function y(){let _=u.formats[o];if(!_){A();return}if(_===!0)return;let[b,v,x]=T(_);b===t&&n.pass(L());function A(){if(l.strictSchema===!1){u.logger.warn(N());return}throw new Error(N());function N(){return`unknown format "${o}" ignored in schema at path "${d}"`}}function T(N){let ve=N instanceof RegExp?(0,We.regexpCode)(N):l.code.formats?(0,We._)`${l.code.formats}${(0,We.getProperty)(o)}`:void 0,ke=e.scopeValue("formats",{key:o,ref:N,code:ve});return typeof N=="object"&&!(N instanceof RegExp)?[N.type||"string",N.validate,(0,We._)`${ke}.validate`]:["string",N,ke]}function L(){if(typeof _=="object"&&!(_ instanceof RegExp)&&_.async){if(!c.$async)throw new Error("async format in sync schema");return(0,We._)`await ${x}(${i})`}return typeof v=="function"?(0,We._)`${x}(${i})`:(0,We._)`${x}.test(${i})`}}}};eu.default=qE});var nb=F(tu=>{"use strict";Object.defineProperty(tu,"__esModule",{value:!0});var UE=tb(),GE=[UE.default];tu.default=GE});var ib=F(Or=>{"use strict";Object.defineProperty(Or,"__esModule",{value:!0});Or.contentVocabulary=Or.metadataVocabulary=void 0;Or.metadataVocabulary=["title","description","default","deprecated","readOnly","writeOnly","examples"];Or.contentVocabulary=["contentMediaType","contentEncoding","contentSchema"]});var ob=F(nu=>{"use strict";Object.defineProperty(nu,"__esModule",{value:!0});var WE=u_(),KE=E_(),YE=eb(),QE=nb(),rb=ib(),XE=[WE.default,KE.default,(0,YE.default)(),QE.default,rb.metadataVocabulary,rb.contentVocabulary];nu.default=XE});var sb=F(El=>{"use strict";Object.defineProperty(El,"__esModule",{value:!0});El.DiscrError=void 0;var ab=(function(n){return n.Tag="tag",n.Mapping="mapping",n})(ab||(El.DiscrError=ab={}))});var cb=F(ru=>{"use strict";Object.defineProperty(ru,"__esModule",{value:!0});var Rr=oe(),iu=sb(),lb=ll(),ZE=Jo(),JE=he(),eS={message:({params:{discrError:n,tagName:t}})=>n===iu.DiscrError.Tag?`tag "${t}" must be string`:`value of tag "${t}" must be in oneOf`,params:({params:{discrError:n,tag:t,tagName:e}})=>(0,Rr._)`{error: ${n}, tag: ${e}, tagValue: ${t}}`},tS={keyword:"discriminator",type:"object",schemaType:"object",error:eS,code(n){let{gen:t,data:e,schema:i,parentSchema:r,it:o}=n,{oneOf:a}=r;if(!o.opts.discriminator)throw new Error("discriminator: requires discriminator option");let s=i.propertyName;if(typeof s!="string")throw new Error("discriminator: requires propertyName");if(i.mapping)throw new Error("discriminator: mapping is not supported");if(!a)throw new Error("discriminator: requires oneOf keyword");let l=t.let("valid",!1),d=t.const("tag",(0,Rr._)`${e}${(0,Rr.getProperty)(s)}`);t.if((0,Rr._)`typeof ${d} == "string"`,()=>c(),()=>n.error(!1,{discrError:iu.DiscrError.Tag,tag:d,tagName:s})),n.ok(l);function c(){let y=h();t.if(!1);for(let _ in y)t.elseIf((0,Rr._)`${d} === ${_}`),t.assign(l,u(y[_]));t.else(),n.error(!1,{discrError:iu.DiscrError.Mapping,tag:d,tagName:s}),t.endIf()}function u(y){let _=t.name("valid"),b=n.subschema({keyword:"oneOf",schemaProp:y},_);return n.mergeEvaluated(b,Rr.Name),_}function h(){var y;let _={},b=x(r),v=!0;for(let L=0;L<a.length;L++){let N=a[L];if(N?.$ref&&!(0,JE.schemaHasRulesButRef)(N,o.self.RULES)){let ke=N.$ref;if(N=lb.resolveRef.call(o.self,o.schemaEnv.root,o.baseId,ke),N instanceof lb.SchemaEnv&&(N=N.schema),N===void 0)throw new ZE.default(o.opts.uriResolver,o.baseId,ke)}let ve=(y=N?.properties)===null||y===void 0?void 0:y[s];if(typeof ve!="object")throw new Error(`discriminator: oneOf subschemas (or referenced schemas) must have "properties/${s}"`);v=v&&(b||x(N)),A(ve,L)}if(!v)throw new Error(`discriminator: "${s}" must be required`);return _;function x({required:L}){return Array.isArray(L)&&L.includes(s)}function A(L,N){if(L.const)T(L.const,N);else if(L.enum)for(let ve of L.enum)T(ve,N);else throw new Error(`discriminator: "properties/${s}" must have "const" or "enum"`)}function T(L,N){if(typeof L!="string"||L in _)throw new Error(`discriminator: "${s}" values must be unique strings`);_[L]=N}}}};ru.default=tS});var db=F(($$,nS)=>{nS.exports={$schema:"http://json-schema.org/draft-07/schema#",$id:"http://json-schema.org/draft-07/schema#",title:"Core schema meta-schema",definitions:{schemaArray:{type:"array",minItems:1,items:{$ref:"#"}},nonNegativeInteger:{type:"integer",minimum:0},nonNegativeIntegerDefault0:{allOf:[{$ref:"#/definitions/nonNegativeInteger"},{default:0}]},simpleTypes:{enum:["array","boolean","integer","null","number","object","string"]},stringArray:{type:"array",items:{type:"string"},uniqueItems:!0,default:[]}},type:["object","boolean"],properties:{$id:{type:"string",format:"uri-reference"},$schema:{type:"string",format:"uri"},$ref:{type:"string",format:"uri-reference"},$comment:{type:"string"},title:{type:"string"},description:{type:"string"},default:!0,readOnly:{type:"boolean",default:!1},examples:{type:"array",items:!0},multipleOf:{type:"number",exclusiveMinimum:0},maximum:{type:"number"},exclusiveMaximum:{type:"number"},minimum:{type:"number"},exclusiveMinimum:{type:"number"},maxLength:{$ref:"#/definitions/nonNegativeInteger"},minLength:{$ref:"#/definitions/nonNegativeIntegerDefault0"},pattern:{type:"string",format:"regex"},additionalItems:{$ref:"#"},items:{anyOf:[{$ref:"#"},{$ref:"#/definitions/schemaArray"}],default:!0},maxItems:{$ref:"#/definitions/nonNegativeInteger"},minItems:{$ref:"#/definitions/nonNegativeIntegerDefault0"},uniqueItems:{type:"boolean",default:!1},contains:{$ref:"#"},maxProperties:{$ref:"#/definitions/nonNegativeInteger"},minProperties:{$ref:"#/definitions/nonNegativeIntegerDefault0"},required:{$ref:"#/definitions/stringArray"},additionalProperties:{$ref:"#"},definitions:{type:"object",additionalProperties:{$ref:"#"},default:{}},properties:{type:"object",additionalProperties:{$ref:"#"},default:{}},patternProperties:{type:"object",additionalProperties:{$ref:"#"},propertyNames:{format:"regex"},default:{}},dependencies:{type:"object",additionalProperties:{anyOf:[{$ref:"#"},{$ref:"#/definitions/stringArray"}]}},propertyNames:{$ref:"#"},const:!0,enum:{type:"array",items:!0,minItems:1,uniqueItems:!0},type:{anyOf:[{$ref:"#/definitions/simpleTypes"},{type:"array",items:{$ref:"#/definitions/simpleTypes"},minItems:1,uniqueItems:!0}]},format:{type:"string"},contentMediaType:{type:"string"},contentEncoding:{type:"string"},if:{$ref:"#"},then:{$ref:"#"},else:{$ref:"#"},allOf:{$ref:"#/definitions/schemaArray"},anyOf:{$ref:"#/definitions/schemaArray"},oneOf:{$ref:"#/definitions/schemaArray"},not:{$ref:"#"}},default:!0}});var ub=F((Re,ou)=>{"use strict";Object.defineProperty(Re,"__esModule",{value:!0});Re.MissingRefError=Re.ValidationError=Re.CodeGen=Re.Name=Re.nil=Re.stringify=Re.str=Re._=Re.KeywordCxt=Re.Ajv=void 0;var iS=a_(),rS=ob(),oS=cb(),mb=db(),aS=["/properties"],Sl="http://json-schema.org/draft-07/schema",Pr=class extends iS.default{_addVocabularies(){super._addVocabularies(),rS.default.forEach(t=>this.addVocabulary(t)),this.opts.discriminator&&this.addKeyword(oS.default)}_addDefaultMetaSchema(){if(super._addDefaultMetaSchema(),!this.opts.meta)return;let t=this.opts.$data?this.$dataMetaSchema(mb,aS):mb;this.addMetaSchema(t,Sl,!1),this.refs["http://json-schema.org/schema"]=Sl}defaultMeta(){return this.opts.defaultMeta=super.defaultMeta()||(this.getSchema(Sl)?Sl:void 0)}};Re.Ajv=Pr;ou.exports=Re=Pr;ou.exports.Ajv=Pr;Object.defineProperty(Re,"__esModule",{value:!0});Re.default=Pr;var sS=Zo();Object.defineProperty(Re,"KeywordCxt",{enumerable:!0,get:function(){return sS.KeywordCxt}});var Fr=oe();Object.defineProperty(Re,"_",{enumerable:!0,get:function(){return Fr._}});Object.defineProperty(Re,"str",{enumerable:!0,get:function(){return Fr.str}});Object.defineProperty(Re,"stringify",{enumerable:!0,get:function(){return Fr.stringify}});Object.defineProperty(Re,"nil",{enumerable:!0,get:function(){return Fr.nil}});Object.defineProperty(Re,"Name",{enumerable:!0,get:function(){return Fr.Name}});Object.defineProperty(Re,"CodeGen",{enumerable:!0,get:function(){return Fr.CodeGen}});var lS=al();Object.defineProperty(Re,"ValidationError",{enumerable:!0,get:function(){return lS.default}});var cS=Jo();Object.defineProperty(Re,"MissingRefError",{enumerable:!0,get:function(){return cS.default}})});var Ep=null;function Tn(){return Ep}function pc(n){Ep??=n}var ho=class{},or=(()=>{class n{historyGo(e){throw new Error("")}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:()=>m(Sp),providedIn:"platform"})}return n})();var Sp=(()=>{class n extends or{_location;_history;_doc=m(W);constructor(){super(),this._location=window.location,this._history=window.history}getBaseHrefFromDOM(){return Tn().getBaseHref(this._doc)}onPopState(e){let i=Tn().getGlobalEventTarget(this._doc,"window");return i.addEventListener("popstate",e,!1),()=>i.removeEventListener("popstate",e)}onHashChange(e){let i=Tn().getGlobalEventTarget(this._doc,"window");return i.addEventListener("hashchange",e,!1),()=>i.removeEventListener("hashchange",e)}get href(){return this._location.href}get protocol(){return this._location.protocol}get hostname(){return this._location.hostname}get port(){return this._location.port}get pathname(){return this._location.pathname}get search(){return this._location.search}get hash(){return this._location.hash}set pathname(e){this._location.pathname=e}pushState(e,i,r){this._history.pushState(e,i,r)}replaceState(e,i,r){this._history.replaceState(e,i,r)}forward(){this._history.forward()}back(){this._history.back()}historyGo(e=0){this._history.go(e)}getState(){return this._history.state}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:()=>new n,providedIn:"platform"})}return n})();function Ip(n,t){return n?t?n.endsWith("/")?t.startsWith("/")?n+t.slice(1):n+t:t.startsWith("/")?n+t:`${n}/${t}`:n:t}function Mp(n){let t=n.search(/#|\?|$/);return n[t-1]==="/"?n.slice(0,t-1)+n.slice(t):n}function ii(n){return n&&n[0]!=="?"?`?${n}`:n}var Wa=(()=>{class n{historyGo(e){throw new Error("")}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:()=>m(yv),providedIn:"root"})}return n})(),vv=new M(""),yv=(()=>{class n extends Wa{_platformLocation;_baseHref;_removeListenerFns=[];constructor(e,i){super(),this._platformLocation=e,this._baseHref=i??this._platformLocation.getBaseHrefFromDOM()??m(W).location?.origin??""}ngOnDestroy(){for(;this._removeListenerFns.length;)this._removeListenerFns.pop()()}onPopState(e){this._removeListenerFns.push(this._platformLocation.onPopState(e),this._platformLocation.onHashChange(e))}getBaseHref(){return this._baseHref}prepareExternalUrl(e){return Ip(this._baseHref,e)}path(e=!1){let i=this._platformLocation.pathname+ii(this._platformLocation.search),r=this._platformLocation.hash;return r&&e?`${i}${r}`:i}pushState(e,i,r,o){let a=this.prepareExternalUrl(r+ii(o));this._platformLocation.pushState(e,i,a)}replaceState(e,i,r,o){let a=this.prepareExternalUrl(r+ii(o));this._platformLocation.replaceState(e,i,a)}forward(){this._platformLocation.forward()}back(){this._platformLocation.back()}getState(){return this._platformLocation.getState()}historyGo(e=0){this._platformLocation.historyGo?.(e)}static \u0275fac=function(i){return new(i||n)(j(or),j(vv,8))};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var ar=(()=>{class n{_subject=new z;_basePath;_locationStrategy;_urlChangeListeners=[];_urlChangeSubscription=null;constructor(e){this._locationStrategy=e;let i=this._locationStrategy.getBaseHref();this._basePath=Cv(Mp(Ap(i))),this._locationStrategy.onPopState(r=>{this._subject.next({url:this.path(!0),pop:!0,state:r.state,type:r.type})})}ngOnDestroy(){this._urlChangeSubscription?.unsubscribe(),this._urlChangeListeners=[]}path(e=!1){return this.normalize(this._locationStrategy.path(e))}getState(){return this._locationStrategy.getState()}isCurrentPathEqualTo(e,i=""){return this.path()==this.normalize(e+ii(i))}normalize(e){return n.stripTrailingSlash(wv(this._basePath,Ap(e)))}prepareExternalUrl(e){return e&&e[0]!=="/"&&(e="/"+e),this._locationStrategy.prepareExternalUrl(e)}go(e,i="",r=null){this._locationStrategy.pushState(r,"",e,i),this._notifyUrlChangeListeners(this.prepareExternalUrl(e+ii(i)),r)}replaceState(e,i="",r=null){this._locationStrategy.replaceState(r,"",e,i),this._notifyUrlChangeListeners(this.prepareExternalUrl(e+ii(i)),r)}forward(){this._locationStrategy.forward()}back(){this._locationStrategy.back()}historyGo(e=0){this._locationStrategy.historyGo?.(e)}onUrlChange(e){return this._urlChangeListeners.push(e),this._urlChangeSubscription??=this.subscribe(i=>{this._notifyUrlChangeListeners(i.url,i.state)}),()=>{let i=this._urlChangeListeners.indexOf(e);this._urlChangeListeners.splice(i,1),this._urlChangeListeners.length===0&&(this._urlChangeSubscription?.unsubscribe(),this._urlChangeSubscription=null)}}_notifyUrlChangeListeners(e="",i){this._urlChangeListeners.forEach(r=>r(e,i))}subscribe(e,i,r){return this._subject.subscribe({next:e,error:i??void 0,complete:r??void 0})}static normalizeQueryParams=ii;static joinWithSlash=Ip;static stripTrailingSlash=Mp;static \u0275fac=function(i){return new(i||n)(j(Wa))};static \u0275prov=E({token:n,factory:()=>xv(),providedIn:"root"})}return n})();function xv(){return new ar(j(Wa))}function wv(n,t){if(!n||!t.startsWith(n))return t;let e=t.substring(n.length);return e===""||["/",";","?","#"].includes(e[0])?e:t}function Ap(n){return n.replace(/\/index.html$/,"")}function Cv(n){if(new RegExp("^(https?:)?//").test(n)){let[,e]=n.split(/\/\/[^\/]+/);return e}return n}var hc=/\s+/,Tp=[],fo=(()=>{class n{_ngEl;_renderer;initialClasses=Tp;rawClass;stateMap=new Map;constructor(e,i){this._ngEl=e,this._renderer=i}set klass(e){this.initialClasses=e!=null?e.trim().split(hc):Tp}set ngClass(e){this.rawClass=typeof e=="string"?e.trim().split(hc):e}ngDoCheck(){for(let i of this.initialClasses)this._updateState(i,!0);let e=this.rawClass;if(Array.isArray(e)||e instanceof Set)for(let i of e)this._updateState(i,!0);else if(e!=null)for(let i of Object.keys(e))this._updateState(i,!!e[i]);this._applyStateDiff()}_updateState(e,i){let r=this.stateMap.get(e);r!==void 0?(r.enabled!==i&&(r.changed=!0,r.enabled=i),r.touched=!0):this.stateMap.set(e,{enabled:i,changed:!0,touched:!0})}_applyStateDiff(){for(let e of this.stateMap){let i=e[0],r=e[1];r.changed?(this._toggleClass(i,r.enabled),r.changed=!1):r.touched||(r.enabled&&this._toggleClass(i,!1),this.stateMap.delete(i)),r.touched=!1}}_toggleClass(e,i){e=e.trim(),e.length>0&&e.split(hc).forEach(r=>{i?this._renderer.addClass(this._ngEl.nativeElement,r):this._renderer.removeClass(this._ngEl.nativeElement,r)})}static \u0275fac=function(i){return new(i||n)(Se(Y),Se(rt))};static \u0275dir=G({type:n,selectors:[["","ngClass",""]],inputs:{klass:[0,"class","klass"],ngClass:"ngClass"}})}return n})();var go=(()=>{class n{_viewContainerRef;_viewRef=null;ngTemplateOutletContext=null;ngTemplateOutlet=null;ngTemplateOutletInjector=null;injector=m(te);constructor(e){this._viewContainerRef=e}ngOnChanges(e){if(this._shouldRecreateView(e)){let i=this._viewContainerRef;if(this._viewRef&&i.remove(i.indexOf(this._viewRef)),!this.ngTemplateOutlet){this._viewRef=null;return}let r=this._createContextForwardProxy();this._viewRef=i.createEmbeddedView(this.ngTemplateOutlet,r,{injector:this._getInjector()})}}_getInjector(){return this.ngTemplateOutletInjector==="outlet"?this.injector:this.ngTemplateOutletInjector??void 0}_shouldRecreateView(e){return!!e.ngTemplateOutlet||!!e.ngTemplateOutletInjector}_createContextForwardProxy(){return new Proxy({},{set:(e,i,r)=>this.ngTemplateOutletContext?Reflect.set(this.ngTemplateOutletContext,i,r):!1,get:(e,i,r)=>{if(this.ngTemplateOutletContext)return Reflect.get(this.ngTemplateOutletContext,i,r)}})}static \u0275fac=function(i){return new(i||n)(Se(pn))};static \u0275dir=G({type:n,selectors:[["","ngTemplateOutlet",""]],inputs:{ngTemplateOutletContext:"ngTemplateOutletContext",ngTemplateOutlet:"ngTemplateOutlet",ngTemplateOutletInjector:"ngTemplateOutletInjector"},features:[dt]})}return n})();function Ev(n,t){return{key:n,value:t}}var _o=(()=>{class n{differs;constructor(e){this.differs=e}differ;keyValues=[];compareFn=Op;transform(e,i=Op){if(!e||!(e instanceof Map)&&typeof e!="object")return null;this.differ??=this.differs.find(e).create();let r=this.differ.diff(e),o=i!==this.compareFn;return r&&(this.keyValues=[],r.forEachItem(a=>{this.keyValues.push(Ev(a.key,a.currentValue))})),(r||o)&&(i&&this.keyValues.sort(i),this.compareFn=i),this.keyValues}static \u0275fac=function(i){return new(i||n)(Se(Cp,16))};static \u0275pipe=ac({name:"keyvalue",type:n,pure:!1})}return n})();function Op(n,t){let e=n.key,i=t.key;if(e===i)return 0;if(e==null)return 1;if(i==null)return-1;if(typeof e=="string"&&typeof i=="string")return e<i?-1:1;if(typeof e=="number"&&typeof i=="number")return e-i;if(typeof e=="boolean"&&typeof i=="boolean")return e<i?-1:1;let r=String(e),o=String(i);return r==o?0:r<o?-1:1}var hn=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({})}return n})();function bo(n,t){t=encodeURIComponent(t);for(let e of n.split(";")){let i=e.indexOf("="),[r,o]=i==-1?[e,""]:[e.slice(0,i),e.slice(i+1)];if(r.trim()===t)return decodeURIComponent(o)}return null}var en=class{};var gc="browser";function On(n){return n===gc}var yo=class{_doc;constructor(t){this._doc=t}manager},Ka=(()=>{class n extends yo{constructor(e){super(e)}supports(e){return!0}addEventListener(e,i,r,o){return e.addEventListener(i,r,o),()=>this.removeEventListener(e,i,r,o)}removeEventListener(e,i,r,o){return e.removeEventListener(i,r,o)}static \u0275fac=function(i){return new(i||n)(j(W))};static \u0275prov=E({token:n,factory:n.\u0275fac})}return n})(),Xa=new M(""),yc=(()=>{class n{_zone;_plugins;_eventNameToPlugin=new Map;constructor(e,i){this._zone=i,e.forEach(a=>{a.manager=this});let r=e.filter(a=>!(a instanceof Ka));this._plugins=r.slice().reverse();let o=e.find(a=>a instanceof Ka);o&&this._plugins.push(o)}addEventListener(e,i,r,o){return this._findPluginFor(i).addEventListener(e,i,r,o)}getZone(){return this._zone}_findPluginFor(e){let i=this._eventNameToPlugin.get(e);if(i)return i;if(i=this._plugins.find(o=>o.supports(e)),!i)throw new Fe(5101,!1);return this._eventNameToPlugin.set(e,i),i}static \u0275fac=function(i){return new(i||n)(j(Xa),j(K))};static \u0275prov=E({token:n,factory:n.\u0275fac})}return n})(),_c="ng-app-id";function Pp(n){for(let t of n)t.remove()}function Fp(n,t){let e=t.createElement("style");return e.textContent=n,e}function Mv(n,t,e,i){let r=n.head?.querySelectorAll(`style[${_c}="${t}"],link[${_c}="${t}"]`);if(r)for(let o of r)o.removeAttribute(_c),o instanceof HTMLLinkElement?i.set(o.href.slice(o.href.lastIndexOf("/")+1),{usage:0,elements:[o]}):o.textContent&&e.set(o.textContent,{usage:0,elements:[o]})}function vc(n,t){let e=t.createElement("link");return e.setAttribute("rel","stylesheet"),e.setAttribute("href",n),e}var xc=(()=>{class n{doc;appId;nonce;inline=new Map;external=new Map;hosts=new Set;constructor(e,i,r,o={}){this.doc=e,this.appId=i,this.nonce=r,Mv(e,i,this.inline,this.external),this.hosts.add(e.head)}addStyles(e,i){for(let r of e)this.addUsage(r,this.inline,Fp);i?.forEach(r=>this.addUsage(r,this.external,vc))}removeStyles(e,i){for(let r of e)this.removeUsage(r,this.inline);i?.forEach(r=>this.removeUsage(r,this.external))}addUsage(e,i,r){let o=i.get(e);o?o.usage++:i.set(e,{usage:1,elements:[...this.hosts].map(a=>this.addElement(a,r(e,this.doc)))})}removeUsage(e,i){let r=i.get(e);r&&(r.usage--,r.usage<=0&&(Pp(r.elements),i.delete(e)))}ngOnDestroy(){for(let[,{elements:e}]of[...this.inline,...this.external])Pp(e);this.hosts.clear()}addHost(e){this.hosts.add(e);for(let[i,{elements:r}]of this.inline)r.push(this.addElement(e,Fp(i,this.doc)));for(let[i,{elements:r}]of this.external)r.push(this.addElement(e,vc(i,this.doc)))}removeHost(e){this.hosts.delete(e)}addElement(e,i){return this.nonce&&i.setAttribute("nonce",this.nonce),e.appendChild(i)}static \u0275fac=function(i){return new(i||n)(j(W),j(oo),j(Zi,8),j(Zn))};static \u0275prov=E({token:n,factory:n.\u0275fac})}return n})(),bc={svg:"http://www.w3.org/2000/svg",xhtml:"http://www.w3.org/1999/xhtml",xlink:"http://www.w3.org/1999/xlink",xml:"http://www.w3.org/XML/1998/namespace",xmlns:"http://www.w3.org/2000/xmlns/",math:"http://www.w3.org/1998/Math/MathML"},wc=/%COMP%/g;var Lp="%COMP%",Av=`_nghost-${Lp}`,Iv=`_ngcontent-${Lp}`,Tv=!0,Ov=new M("",{factory:()=>Tv});function Rv(n){return Iv.replace(wc,n)}function Pv(n){return Av.replace(wc,n)}function Bp(n,t){return t.map(e=>e.replace(wc,n))}var Co=(()=>{class n{eventManager;sharedStylesHost;appId;removeStylesOnCompDestroy;doc;ngZone;nonce;tracingService;rendererByCompId=new Map;defaultRenderer;constructor(e,i,r,o,a,s,l=null,d=null){this.eventManager=e,this.sharedStylesHost=i,this.appId=r,this.removeStylesOnCompDestroy=o,this.doc=a,this.ngZone=s,this.nonce=l,this.tracingService=d,this.defaultRenderer=new xo(e,a,s,this.tracingService)}createRenderer(e,i){if(!e||!i)return this.defaultRenderer;let r=this.getOrCreateRenderer(e,i);return r instanceof Qa?r.applyToHost(e):r instanceof wo&&r.applyStyles(),r}getOrCreateRenderer(e,i){let r=this.rendererByCompId,o=r.get(i.id);if(!o){let a=this.doc,s=this.ngZone,l=this.eventManager,d=this.sharedStylesHost,c=this.removeStylesOnCompDestroy,u=this.tracingService;switch(i.encapsulation){case La.Emulated:o=new Qa(l,d,i,this.appId,c,a,s,u);break;case La.ShadowDom:return new Ya(l,e,i,a,s,this.nonce,u,d);case La.ExperimentalIsolatedShadowDom:return new Ya(l,e,i,a,s,this.nonce,u);default:o=new wo(l,d,i,c,a,s,u);break}r.set(i.id,o)}return o}ngOnDestroy(){this.rendererByCompId.clear()}componentReplaced(e){this.rendererByCompId.delete(e)}static \u0275fac=function(i){return new(i||n)(j(yc),j(xc),j(oo),j(Ov),j(W),j(K),j(Zi),j(Ba,8))};static \u0275prov=E({token:n,factory:n.\u0275fac})}return n})(),xo=class{eventManager;doc;ngZone;tracingService;data=Object.create(null);throwOnSyntheticProps=!0;constructor(t,e,i,r){this.eventManager=t,this.doc=e,this.ngZone=i,this.tracingService=r}destroy(){}destroyNode=null;createElement(t,e){return e?this.doc.createElementNS(bc[e]||e,t):this.doc.createElement(t)}createComment(t){return this.doc.createComment(t)}createText(t){return this.doc.createTextNode(t)}appendChild(t,e){(Np(t)?t.content:t).appendChild(e)}insertBefore(t,e,i){t&&(Np(t)?t.content:t).insertBefore(e,i)}removeChild(t,e){e.remove()}selectRootElement(t,e){let i=typeof t=="string"?this.doc.querySelector(t):t;if(!i)throw new Fe(-5104,!1);return e||(i.textContent=""),i}parentNode(t){return t.parentNode}nextSibling(t){return t.nextSibling}setAttribute(t,e,i,r){if(r){e=r+":"+e;let o=bc[r];o?t.setAttributeNS(o,e,i):t.setAttribute(e,i)}else t.setAttribute(e,i)}removeAttribute(t,e,i){if(i){let r=bc[i];r?t.removeAttributeNS(r,e):t.removeAttribute(`${i}:${e}`)}else t.removeAttribute(e)}addClass(t,e){t.classList.add(e)}removeClass(t,e){t.classList.remove(e)}setStyle(t,e,i,r){r&(tr.DashCase|tr.Important)?t.style.setProperty(e,i,r&tr.Important?"important":""):t.style[e]=i}removeStyle(t,e,i){i&tr.DashCase?t.style.removeProperty(e):t.style[e]=""}setProperty(t,e,i){t!=null&&(t[e]=i)}setValue(t,e){t.nodeValue=e}listen(t,e,i,r){if(typeof t=="string"&&(t=Tn().getGlobalEventTarget(this.doc,t),!t))throw new Fe(5102,!1);let o=this.decoratePreventDefault(i);return this.tracingService?.wrapEventListener&&(o=this.tracingService.wrapEventListener(t,e,o)),this.eventManager.addEventListener(t,e,o,r)}decoratePreventDefault(t){return e=>{if(e==="__ngUnwrap__")return t;t(e)===!1&&e.preventDefault()}}};function Np(n){return n.tagName==="TEMPLATE"&&n.content!==void 0}var Ya=class extends xo{hostEl;sharedStylesHost;shadowRoot;constructor(t,e,i,r,o,a,s,l){super(t,r,o,s),this.hostEl=e,this.sharedStylesHost=l,this.shadowRoot=e.attachShadow({mode:"open"}),this.sharedStylesHost&&this.sharedStylesHost.addHost(this.shadowRoot);let d=i.styles;d=Bp(i.id,d);for(let u of d){let h=document.createElement("style");a&&h.setAttribute("nonce",a),h.textContent=u,this.shadowRoot.appendChild(h)}let c=i.getExternalStyles?.();if(c)for(let u of c){let h=vc(u,r);a&&h.setAttribute("nonce",a),this.shadowRoot.appendChild(h)}}nodeOrShadowRoot(t){return t===this.hostEl?this.shadowRoot:t}appendChild(t,e){return super.appendChild(this.nodeOrShadowRoot(t),e)}insertBefore(t,e,i){return super.insertBefore(this.nodeOrShadowRoot(t),e,i)}removeChild(t,e){return super.removeChild(null,e)}parentNode(t){return this.nodeOrShadowRoot(super.parentNode(this.nodeOrShadowRoot(t)))}destroy(){this.sharedStylesHost&&this.sharedStylesHost.removeHost(this.shadowRoot)}},wo=class extends xo{sharedStylesHost;removeStylesOnCompDestroy;styles;styleUrls;constructor(t,e,i,r,o,a,s,l){super(t,o,a,s),this.sharedStylesHost=e,this.removeStylesOnCompDestroy=r;let d=i.styles;this.styles=l?Bp(l,d):d,this.styleUrls=i.getExternalStyles?.(l)}applyStyles(){this.sharedStylesHost.addStyles(this.styles,this.styleUrls)}destroy(){this.removeStylesOnCompDestroy&&pp.size===0&&this.sharedStylesHost.removeStyles(this.styles,this.styleUrls)}},Qa=class extends wo{contentAttr;hostAttr;constructor(t,e,i,r,o,a,s,l){let d=r+"-"+i.id;super(t,e,i,o,a,s,l,d),this.contentAttr=Rv(d),this.hostAttr=Pv(d)}applyToHost(t){this.applyStyles(),this.setAttribute(t,this.hostAttr,"")}createElement(t,e){let i=super.createElement(t,e);return super.setAttribute(i,this.contentAttr,""),i}};var Za=class n extends ho{supportsDOMEvents=!0;static makeCurrent(){pc(new n)}onAndCancel(t,e,i,r){return t.addEventListener(e,i,r),()=>{t.removeEventListener(e,i,r)}}dispatchEvent(t,e){t.dispatchEvent(e)}remove(t){t.remove()}createElement(t,e){return e=e||this.getDefaultDocument(),e.createElement(t)}createHtmlDocument(){return document.implementation.createHTMLDocument("fakeTitle")}getDefaultDocument(){return document}isElementNode(t){return t.nodeType===Node.ELEMENT_NODE}isShadowRoot(t){return t instanceof DocumentFragment}getGlobalEventTarget(t,e){return e==="window"?window:e==="document"?t:e==="body"?t.body:null}getBaseHref(t){let e=Nv();return e==null?null:Lv(e)}resetBaseElement(){ko=null}getUserAgent(){return window.navigator.userAgent}getCookie(t){return bo(document.cookie,t)}},ko=null;function Nv(){return ko=ko||document.head.querySelector("base"),ko?ko.getAttribute("href"):null}function Lv(n){return new URL(n,document.baseURI).pathname}var Bv=(()=>{class n{build(){return new XMLHttpRequest}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac})}return n})(),$p=["alt","control","meta","shift"],$v={"\b":"Backspace","	":"Tab","\x7F":"Delete","\x1B":"Escape",Del:"Delete",Esc:"Escape",Left:"ArrowLeft",Right:"ArrowRight",Up:"ArrowUp",Down:"ArrowDown",Menu:"ContextMenu",Scroll:"ScrollLock",Win:"OS"},jv={alt:n=>n.altKey,control:n=>n.ctrlKey,meta:n=>n.metaKey,shift:n=>n.shiftKey},jp=(()=>{class n extends yo{constructor(e){super(e)}supports(e){return n.parseEventName(e)!=null}addEventListener(e,i,r,o){let a=n.parseEventName(i),s=n.eventCallback(a.fullKey,r,this.manager.getZone());return this.manager.getZone().runOutsideAngular(()=>Tn().onAndCancel(e,a.domEventName,s,o))}static parseEventName(e){let i=e.toLowerCase().split("."),r=i.shift();if(i.length===0||!(r==="keydown"||r==="keyup"))return null;let o=n._normalizeKey(i.pop()),a="",s=i.indexOf("code");if(s>-1&&(i.splice(s,1),a="code."),$p.forEach(d=>{let c=i.indexOf(d);c>-1&&(i.splice(c,1),a+=d+".")}),a+=o,i.length!=0||o.length===0)return null;let l={};return l.domEventName=r,l.fullKey=a,l}static matchEventFullKeyCode(e,i){let r=$v[e.key]||e.key,o="";return i.indexOf("code.")>-1&&(r=e.code,o="code."),r==null||!r?!1:(r=r.toLowerCase(),r===" "?r="space":r==="."&&(r="dot"),$p.forEach(a=>{if(a!==r){let s=jv[a];s(e)&&(o+=a+".")}}),o+=r,o===i)}static eventCallback(e,i,r){return o=>{n.matchEventFullKeyCode(o,e)&&r.runGuarded(()=>i(o))}}static _normalizeKey(e){return e==="esc"?"escape":e}static \u0275fac=function(i){return new(i||n)(j(W))};static \u0275prov=E({token:n,factory:n.\u0275fac})}return n})();async function Cc(n,t,e){let i=S({rootComponent:n},Vv(t,e));return kp(i)}function Vv(n,t){return{platformRef:t?.platformRef,appProviders:[...Gv,...n?.providers??[]],platformProviders:Uv}}function zv(){Za.makeCurrent()}function Hv(){return new _i}function qv(){return rp(document),document}var Uv=[{provide:Zn,useValue:gc},{provide:op,useValue:zv,multi:!0},{provide:W,useFactory:qv}];var Gv=[{provide:tp,useValue:"root"},{provide:_i,useFactory:Hv},{provide:Xa,useClass:Ka,multi:!0},{provide:Xa,useClass:jp,multi:!0},Co,xc,yc,{provide:bt,useExisting:Co},{provide:en,useClass:Bv},[]];var fn=class n{headers;normalizedNames=new Map;lazyInit;lazyUpdate=null;constructor(t){t?typeof t=="string"?this.lazyInit=()=>{this.headers=new Map,t.split(`
`).forEach(e=>{let i=e.indexOf(":");if(i>0){let r=e.slice(0,i),o=e.slice(i+1).trim();this.addHeaderEntry(r,o)}})}:typeof Headers<"u"&&t instanceof Headers?(this.headers=new Map,t.forEach((e,i)=>{this.addHeaderEntry(i,e)})):this.lazyInit=()=>{this.headers=new Map,Object.entries(t).forEach(([e,i])=>{this.setHeaderEntries(e,i)})}:this.headers=new Map}has(t){return this.init(),this.headers.has(t.toLowerCase())}get(t){this.init();let e=this.headers.get(t.toLowerCase());return e&&e.length>0?e[0]:null}keys(){return this.init(),Array.from(this.normalizedNames.values())}getAll(t){return this.init(),this.headers.get(t.toLowerCase())||null}append(t,e){return this.clone({name:t,value:e,op:"a"})}set(t,e){return this.clone({name:t,value:e,op:"s"})}delete(t,e){return this.clone({name:t,value:e,op:"d"})}maybeSetNormalizedName(t,e){this.normalizedNames.has(e)||this.normalizedNames.set(e,t)}init(){this.lazyInit&&(this.lazyInit instanceof n?this.copyFrom(this.lazyInit):this.lazyInit(),this.lazyInit=null,this.lazyUpdate&&(this.lazyUpdate.forEach(t=>this.applyUpdate(t)),this.lazyUpdate=null))}copyFrom(t){t.init(),Array.from(t.headers.keys()).forEach(e=>{this.headers.set(e,t.headers.get(e)),this.normalizedNames.set(e,t.normalizedNames.get(e))})}clone(t){let e=new n;return e.lazyInit=this.lazyInit&&this.lazyInit instanceof n?this.lazyInit:this,e.lazyUpdate=(this.lazyUpdate||[]).concat([t]),e}applyUpdate(t){let e=t.name.toLowerCase();switch(t.op){case"a":case"s":let i=t.value;if(typeof i=="string"&&(i=[i]),i.length===0)return;this.maybeSetNormalizedName(t.name,e);let r=(t.op==="a"?this.headers.get(e):void 0)||[];r.push(...i),this.headers.set(e,r);break;case"d":let o=t.value;if(!o)this.headers.delete(e),this.normalizedNames.delete(e);else{let a=this.headers.get(e);if(!a)return;a=a.filter(s=>o.indexOf(s)===-1),a.length===0?(this.headers.delete(e),this.normalizedNames.delete(e)):this.headers.set(e,a)}break}}addHeaderEntry(t,e){let i=t.toLowerCase();this.maybeSetNormalizedName(t,i),this.headers.has(i)?this.headers.get(i).push(e):this.headers.set(i,[e])}setHeaderEntries(t,e){let i=(Array.isArray(e)?e:[e]).map(o=>o.toString()),r=t.toLowerCase();this.headers.set(r,i),this.maybeSetNormalizedName(t,r)}forEach(t){this.init(),Array.from(this.normalizedNames.keys()).forEach(e=>t(this.normalizedNames.get(e),this.headers.get(e)))}};var es=class{map=new Map;set(t,e){return this.map.set(t,e),this}get(t){return this.map.has(t)||this.map.set(t,t.defaultValue()),this.map.get(t)}delete(t){return this.map.delete(t),this}has(t){return this.map.has(t)}keys(){return this.map.keys()}},ts=class{encodeKey(t){return Vp(t)}encodeValue(t){return Vp(t)}decodeKey(t){return decodeURIComponent(t)}decodeValue(t){return decodeURIComponent(t)}};function Wv(n,t){let e=new Map;return n.length>0&&n.replace(/^\?/,"").split("&").forEach(r=>{let o=r.indexOf("="),[a,s]=o==-1?[t.decodeKey(r),""]:[t.decodeKey(r.slice(0,o)),t.decodeValue(r.slice(o+1))],l=e.get(a)||[];l.push(s),e.set(a,l)}),e}var Kv=/%(\d[a-f0-9])/gi,Yv={40:"@","3A":":",24:"$","2C":",","3B":";","3D":"=","3F":"?","2F":"/"};function Vp(n){return encodeURIComponent(n).replace(Kv,(t,e)=>Yv[e]??t)}function Ja(n){return`${n}`}var Bt=class n{map;encoder;updates=null;cloneFrom=null;constructor(t={}){if(this.encoder=t.encoder||new ts,t.fromString){if(t.fromObject)throw new Fe(2805,!1);this.map=Wv(t.fromString,this.encoder)}else t.fromObject?(this.map=new Map,Object.keys(t.fromObject).forEach(e=>{let i=t.fromObject[e],r=Array.isArray(i)?i.map(Ja):[Ja(i)];this.map.set(e,r)})):this.map=null}has(t){return this.init(),this.map.has(t)}get(t){this.init();let e=this.map.get(t);return e?e[0]:null}getAll(t){return this.init(),this.map.get(t)||null}keys(){return this.init(),Array.from(this.map.keys())}append(t,e){return this.clone({param:t,value:e,op:"a"})}appendAll(t){let e=[];return Object.keys(t).forEach(i=>{let r=t[i];Array.isArray(r)?r.forEach(o=>{e.push({param:i,value:o,op:"a"})}):e.push({param:i,value:r,op:"a"})}),this.clone(e)}set(t,e){return this.clone({param:t,value:e,op:"s"})}delete(t,e){return this.clone({param:t,value:e,op:"d"})}toString(){return this.init(),this.keys().map(t=>{let e=this.encoder.encodeKey(t);return this.map.get(t).map(i=>e+"="+this.encoder.encodeValue(i)).join("&")}).filter(t=>t!=="").join("&")}clone(t){let e=new n({encoder:this.encoder});return e.cloneFrom=this.cloneFrom||this,e.updates=(this.updates||[]).concat(t),e}init(){this.map===null&&(this.map=new Map),this.cloneFrom!==null&&(this.cloneFrom.init(),this.cloneFrom.keys().forEach(t=>this.map.set(t,this.cloneFrom.map.get(t))),this.updates.forEach(t=>{switch(t.op){case"a":case"s":let e=(t.op==="a"?this.map.get(t.param):void 0)||[];e.push(Ja(t.value)),this.map.set(t.param,e);break;case"d":if(t.value!==void 0){let i=this.map.get(t.param)||[],r=i.indexOf(Ja(t.value));r!==-1&&i.splice(r,1),i.length>0?this.map.set(t.param,i):this.map.delete(t.param)}else{this.map.delete(t.param);break}}}),this.cloneFrom=this.updates=null)}};function Qv(n){switch(n){case"DELETE":case"GET":case"HEAD":case"OPTIONS":case"JSONP":return!1;default:return!0}}function zp(n){return typeof ArrayBuffer<"u"&&n instanceof ArrayBuffer}function Hp(n){return typeof Blob<"u"&&n instanceof Blob}function qp(n){return typeof FormData<"u"&&n instanceof FormData}function Xv(n){return typeof URLSearchParams<"u"&&n instanceof URLSearchParams}var Up="Content-Type",Gp="Accept",Kp="text/plain",Yp="application/json",Zv=`${Yp}, ${Kp}, */*`,sr=class n{url;body=null;headers;context;reportProgress=!1;withCredentials=!1;credentials;keepalive=!1;cache;priority;mode;redirect;referrer;integrity;referrerPolicy;responseType="json";method;params;urlWithParams;transferCache;timeout;constructor(t,e,i,r){this.url=e,this.method=t.toUpperCase();let o;if(Qv(this.method)||r?(this.body=i!==void 0?i:null,o=r):o=i,o){if(this.reportProgress=!!o.reportProgress,this.withCredentials=!!o.withCredentials,this.keepalive=!!o.keepalive,o.responseType&&(this.responseType=o.responseType),o.headers&&(this.headers=o.headers),o.context&&(this.context=o.context),o.params&&(this.params=o.params),o.priority&&(this.priority=o.priority),o.cache&&(this.cache=o.cache),o.credentials&&(this.credentials=o.credentials),typeof o.timeout=="number"){if(o.timeout<1||!Number.isInteger(o.timeout))throw new Fe(2822,"");this.timeout=o.timeout}o.mode&&(this.mode=o.mode),o.redirect&&(this.redirect=o.redirect),o.integrity&&(this.integrity=o.integrity),o.referrer&&(this.referrer=o.referrer),o.referrerPolicy&&(this.referrerPolicy=o.referrerPolicy),this.transferCache=o.transferCache}if(this.headers??=new fn,this.context??=new es,!this.params)this.params=new Bt,this.urlWithParams=e;else{let a=this.params.toString();if(a.length===0)this.urlWithParams=e;else{let s=e.indexOf("?"),l=s===-1?"?":s<e.length-1?"&":"";this.urlWithParams=e+l+a}}}serializeBody(){return this.body===null?null:typeof this.body=="string"||zp(this.body)||Hp(this.body)||qp(this.body)||Xv(this.body)?this.body:this.body instanceof Bt?this.body.toString():typeof this.body=="object"||typeof this.body=="boolean"||Array.isArray(this.body)?JSON.stringify(this.body):this.body.toString()}detectContentTypeHeader(){return this.body===null||qp(this.body)?null:Hp(this.body)?this.body.type||null:zp(this.body)?null:typeof this.body=="string"?Kp:this.body instanceof Bt?"application/x-www-form-urlencoded;charset=UTF-8":typeof this.body=="object"||typeof this.body=="number"||typeof this.body=="boolean"?Yp:null}clone(t={}){let e=t.method||this.method,i=t.url||this.url,r=t.responseType||this.responseType,o=t.keepalive??this.keepalive,a=t.priority||this.priority,s=t.cache||this.cache,l=t.mode||this.mode,d=t.redirect||this.redirect,c=t.credentials||this.credentials,u=t.referrer||this.referrer,h=t.integrity||this.integrity,y=t.referrerPolicy||this.referrerPolicy,_=t.transferCache??this.transferCache,b=t.timeout??this.timeout,v=t.body!==void 0?t.body:this.body,x=t.withCredentials??this.withCredentials,A=t.reportProgress??this.reportProgress,T=t.headers||this.headers,L=t.params||this.params,N=t.context??this.context;return t.setHeaders!==void 0&&(T=Object.keys(t.setHeaders).reduce((ve,ke)=>ve.set(ke,t.setHeaders[ke]),T)),t.setParams&&(L=Object.keys(t.setParams).reduce((ve,ke)=>ve.set(ke,t.setParams[ke]),L)),new n(e,i,v,{params:L,headers:T,context:N,reportProgress:A,responseType:r,withCredentials:x,transferCache:_,keepalive:o,cache:s,priority:a,timeout:b,mode:l,redirect:d,credentials:c,referrer:u,integrity:h,referrerPolicy:y})}},vi=(function(n){return n[n.Sent=0]="Sent",n[n.UploadProgress=1]="UploadProgress",n[n.ResponseHeader=2]="ResponseHeader",n[n.DownloadProgress=3]="DownloadProgress",n[n.Response=4]="Response",n[n.User=5]="User",n})(vi||{}),cr=class{headers;status;statusText;url;ok;type;redirected;responseType;constructor(t,e=200,i="OK"){this.headers=t.headers||new fn,this.status=t.status!==void 0?t.status:e,this.statusText=t.statusText||i,this.url=t.url||null,this.redirected=t.redirected,this.responseType=t.responseType,this.ok=this.status>=200&&this.status<300}},ns=class n extends cr{constructor(t={}){super(t)}type=vi.ResponseHeader;clone(t={}){return new n({headers:t.headers||this.headers,status:t.status!==void 0?t.status:this.status,statusText:t.statusText||this.statusText,url:t.url||this.url||void 0})}},yi=class n extends cr{body;constructor(t={}){super(t),this.body=t.body!==void 0?t.body:null}type=vi.Response;clone(t={}){return new n({body:t.body!==void 0?t.body:this.body,headers:t.headers||this.headers,status:t.status!==void 0?t.status:this.status,statusText:t.statusText||this.statusText,url:t.url||this.url||void 0,redirected:t.redirected??this.redirected,responseType:t.responseType??this.responseType})}},lr=class extends cr{name="HttpErrorResponse";message;error;ok=!1;constructor(t){super(t,0,"Unknown Error"),this.status>=200&&this.status<300?this.message=`Http failure during parsing for ${t.url||"(unknown url)"}`:this.message=`Http failure response for ${t.url||"(unknown url)"}: ${t.status} ${t.statusText}`,this.error=t.error||null}},Jv=200,ey=204;var ty=new M("");var ny=/^\)\]\}',?\n/;var Do=(()=>{class n{xhrFactory;tracingService=m(Ba,{optional:!0});constructor(e){this.xhrFactory=e}maybePropagateTrace(e){return this.tracingService?.propagate?this.tracingService.propagate(e):e}handle(e){if(e.method==="JSONP")throw new Fe(-2800,!1);let i=this.xhrFactory;return Pe(null).pipe(jt(()=>new St(o=>{let a=i.build();if(a.open(e.method,e.urlWithParams),e.withCredentials&&(a.withCredentials=!0),e.headers.forEach((v,x)=>a.setRequestHeader(v,x.join(","))),e.headers.has(Gp)||a.setRequestHeader(Gp,Zv),!e.headers.has(Up)){let v=e.detectContentTypeHeader();v!==null&&a.setRequestHeader(Up,v)}if(e.timeout&&(a.timeout=e.timeout),e.responseType){let v=e.responseType.toLowerCase();a.responseType=v!=="json"?v:"text"}let s=e.serializeBody(),l=null,d=()=>{if(l!==null)return l;let v=a.statusText||"OK",x=new fn(a.getAllResponseHeaders()),A=a.responseURL||e.url;return l=new ns({headers:x,status:a.status,statusText:v,url:A}),l},c=this.maybePropagateTrace(()=>{let{headers:v,status:x,statusText:A,url:T}=d(),L=null;x!==ey&&(L=typeof a.response>"u"?a.responseText:a.response),x===0&&(x=L?Jv:0);let N=x>=200&&x<300;if(e.responseType==="json"&&typeof L=="string"){let ve=L;L=L.replace(ny,"");try{L=L!==""?JSON.parse(L):null}catch(ke){L=ve,N&&(N=!1,L={error:ke,text:L})}}N?(o.next(new yi({body:L,headers:v,status:x,statusText:A,url:T||void 0})),o.complete()):o.error(new lr({error:L,headers:v,status:x,statusText:A,url:T||void 0}))}),u=this.maybePropagateTrace(v=>{let{url:x}=d(),A=new lr({error:v,status:a.status||0,statusText:a.statusText||"Unknown Error",url:x||void 0});o.error(A)}),h=u;e.timeout&&(h=this.maybePropagateTrace(v=>{let{url:x}=d(),A=new lr({error:new DOMException("Request timed out","TimeoutError"),status:a.status||0,statusText:a.statusText||"Request timeout",url:x||void 0});o.error(A)}));let y=!1,_=this.maybePropagateTrace(v=>{y||(o.next(d()),y=!0);let x={type:vi.DownloadProgress,loaded:v.loaded};v.lengthComputable&&(x.total=v.total),e.responseType==="text"&&a.responseText&&(x.partialText=a.responseText),o.next(x)}),b=this.maybePropagateTrace(v=>{let x={type:vi.UploadProgress,loaded:v.loaded};v.lengthComputable&&(x.total=v.total),o.next(x)});return a.addEventListener("load",c),a.addEventListener("error",u),a.addEventListener("timeout",h),a.addEventListener("abort",u),e.reportProgress&&(a.addEventListener("progress",_),s!==null&&a.upload&&a.upload.addEventListener("progress",b)),a.send(s),o.next({type:vi.Sent}),()=>{a.removeEventListener("error",u),a.removeEventListener("abort",u),a.removeEventListener("load",c),a.removeEventListener("timeout",h),e.reportProgress&&(a.removeEventListener("progress",_),s!==null&&a.upload&&a.upload.removeEventListener("progress",b)),a.readyState!==a.DONE&&a.abort()}})))}static \u0275fac=function(i){return new(i||n)(j(en))};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();function Qp(n,t){return t(n)}function iy(n,t){return(e,i)=>t.intercept(e,{handle:r=>n(r,i)})}function ry(n,t,e){return(i,r)=>np(e,()=>t(i,o=>n(o,r)))}var Xp=new M(""),Dc=new M("",{factory:()=>[]}),Zp=new M(""),Ec=new M("",{factory:()=>!0});function oy(){let n=null;return(t,e)=>{n===null&&(n=(m(Xp,{optional:!0})??[]).reduceRight(iy,Qp));let i=m(Na);if(m(Ec)){let o=i.add();return n(t,e).pipe(io(o))}else return n(t,e)}}var Eo=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:function(i){let r=null;return i?r=new(i||n):r=j(Do),r},providedIn:"root"})}return n})();var is=(()=>{class n{backend;injector;chain=null;pendingTasks=m(Na);contributeToStability=m(Ec);constructor(e,i){this.backend=e,this.injector=i}handle(e){if(this.chain===null){let i=Array.from(new Set([...this.injector.get(Dc),...this.injector.get(Zp,[])]));this.chain=i.reduceRight((r,o)=>ry(r,o,this.injector),Qp)}if(this.contributeToStability){let i=this.pendingTasks.add();return this.chain(e,r=>this.backend.handle(r)).pipe(io(i))}else return this.chain(e,i=>this.backend.handle(i))}static \u0275fac=function(i){return new(i||n)(j(Eo),j(An))};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),Sc=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:function(i){let r=null;return i?r=new(i||n):r=j(is),r},providedIn:"root"})}return n})();function kc(n,t){return{body:t,headers:n.headers,context:n.context,observe:n.observe,params:n.params,reportProgress:n.reportProgress,responseType:n.responseType,withCredentials:n.withCredentials,credentials:n.credentials,transferCache:n.transferCache,timeout:n.timeout,keepalive:n.keepalive,priority:n.priority,cache:n.cache,mode:n.mode,redirect:n.redirect,integrity:n.integrity,referrer:n.referrer,referrerPolicy:n.referrerPolicy}}var Ht=(()=>{class n{handler;constructor(e){this.handler=e}request(e,i,r={}){let o;if(e instanceof sr)o=e;else{let l;r.headers instanceof fn?l=r.headers:l=new fn(r.headers);let d;r.params&&(r.params instanceof Bt?d=r.params:d=new Bt({fromObject:r.params})),o=new sr(e,i,r.body!==void 0?r.body:null,{headers:l,context:r.context,params:d,reportProgress:r.reportProgress,responseType:r.responseType||"json",withCredentials:r.withCredentials,transferCache:r.transferCache,keepalive:r.keepalive,priority:r.priority,cache:r.cache,mode:r.mode,redirect:r.redirect,credentials:r.credentials,referrer:r.referrer,referrerPolicy:r.referrerPolicy,integrity:r.integrity,timeout:r.timeout})}let a=Pe(o).pipe(eo(l=>this.handler.handle(l)));if(e instanceof sr||r.observe==="events")return a;let s=a.pipe(Be(l=>l instanceof yi));switch(r.observe||"body"){case"body":switch(o.responseType){case"arraybuffer":return s.pipe(xe(l=>{if(l.body!==null&&!(l.body instanceof ArrayBuffer))throw new Fe(2806,!1);return l.body}));case"blob":return s.pipe(xe(l=>{if(l.body!==null&&!(l.body instanceof Blob))throw new Fe(2807,!1);return l.body}));case"text":return s.pipe(xe(l=>{if(l.body!==null&&typeof l.body!="string")throw new Fe(2808,!1);return l.body}));default:return s.pipe(xe(l=>l.body))}case"response":return s;default:throw new Fe(2809,!1)}}delete(e,i={}){return this.request("DELETE",e,i)}get(e,i={}){return this.request("GET",e,i)}head(e,i={}){return this.request("HEAD",e,i)}jsonp(e,i){return this.request("JSONP",e,{params:new Bt().append(i,"JSONP_CALLBACK"),observe:"body",responseType:"json"})}options(e,i={}){return this.request("OPTIONS",e,i)}patch(e,i,r={}){return this.request("PATCH",e,kc(r,i))}post(e,i,r={}){return this.request("POST",e,kc(r,i))}put(e,i,r={}){return this.request("PUT",e,kc(r,i))}static \u0275fac=function(i){return new(i||n)(j(Sc))};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var ay=new M("",{factory:()=>!0}),sy="XSRF-TOKEN",ly=new M("",{factory:()=>sy}),cy="X-XSRF-TOKEN",dy=new M("",{factory:()=>cy}),my=(()=>{class n{cookieName=m(ly);doc=m(W);lastCookieString="";lastToken=null;parseCount=0;getToken(){let e=this.doc.cookie||"";return e!==this.lastCookieString&&(this.parseCount++,this.lastToken=bo(e,this.cookieName),this.lastCookieString=e),this.lastToken}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),Jp=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:function(i){let r=null;return i?r=new(i||n):r=j(my),r},providedIn:"root"})}return n})();function uy(n,t){if(!m(ay)||n.method==="GET"||n.method==="HEAD")return t(n);try{let r=m(or).href,{origin:o}=new URL(r),{origin:a}=new URL(n.url,o);if(o!==a)return t(n)}catch{return t(n)}let e=m(Jp).getToken(),i=m(dy);return e!=null&&!n.headers.has(i)&&(n=n.clone({headers:n.headers.set(i,e)})),t(n)}var Mc=(function(n){return n[n.Interceptors=0]="Interceptors",n[n.LegacyInterceptors=1]="LegacyInterceptors",n[n.CustomXsrfConfiguration=2]="CustomXsrfConfiguration",n[n.NoXsrfProtection=3]="NoXsrfProtection",n[n.JsonpSupport=4]="JsonpSupport",n[n.RequestsMadeViaParent=5]="RequestsMadeViaParent",n[n.Fetch=6]="Fetch",n})(Mc||{});function py(n,t){return{\u0275kind:n,\u0275providers:t}}function Ac(...n){let t=[Ht,is,{provide:Sc,useExisting:is},{provide:Eo,useFactory:()=>m(ty,{optional:!0})??m(Do)},{provide:Dc,useValue:uy,multi:!0}];for(let e of n)t.push(...e.\u0275providers);return ro(t)}var Wp=new M("");function Ic(){return py(Mc.LegacyInterceptors,[{provide:Wp,useFactory:oy},{provide:Dc,useExisting:Wp,multi:!0}])}var Rn=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:function(i){let r=null;return i?r=new(i||n):r=j(fy),r},providedIn:"root"})}return n})(),fy=(()=>{class n extends Rn{_doc;constructor(e){super(),this._doc=e}sanitize(e,i){if(i==null)return null;switch(e){case tt.NONE:return i;case tt.HTML:return er(i,"HTML")?Ji(i):up(this._doc,String(i)).toString();case tt.STYLE:return er(i,"Style")?Ji(i):i;case tt.SCRIPT:if(er(i,"Script"))return Ji(i);throw new Fe(5200,!1);case tt.URL:return er(i,"URL")?Ji(i):mp(String(i));case tt.RESOURCE_URL:if(er(i,"ResourceURL"))return Ji(i);throw new Fe(5201,!1);default:throw new Fe(5202,!1)}}bypassSecurityTrustHtml(e){return ap(e)}bypassSecurityTrustStyle(e){return sp(e)}bypassSecurityTrustScript(e){return lp(e)}bypassSecurityTrustUrl(e){return cp(e)}bypassSecurityTrustResourceUrl(e){return dp(e)}static \u0275fac=function(i){return new(i||n)(j(W))};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var os={production:!0};var gy="@",_y=(()=>{class n{doc;delegate;zone;animationType;moduleImpl;_rendererFactoryPromise=null;scheduler=null;injector=m(te);loadingSchedulerFn=m(by,{optional:!0});_engine;constructor(e,i,r,o,a){this.doc=e,this.delegate=i,this.zone=r,this.animationType=o,this.moduleImpl=a}ngOnDestroy(){this._engine?.flush()}loadImpl(){let e=()=>this.moduleImpl??import("./chunk-AFQFVTKK.js").then(r=>r),i;return this.loadingSchedulerFn?i=this.loadingSchedulerFn(e):i=e(),i.catch(r=>{throw new Fe(5300,!1)}).then(({\u0275createEngine:r,\u0275AnimationRendererFactory:o})=>{this._engine=r(this.animationType,this.doc);let a=new o(this.delegate,this._engine,this.zone);return this.delegate=a,a})}createRenderer(e,i){let r=this.delegate.createRenderer(e,i);if(r.\u0275type===0)return r;typeof r.throwOnSyntheticProps=="boolean"&&(r.throwOnSyntheticProps=!1);let o=new Tc(r);return i?.data?.animation&&!this._rendererFactoryPromise&&(this._rendererFactoryPromise=this.loadImpl()),this._rendererFactoryPromise?.then(a=>{let s=a.createRenderer(e,i);o.use(s),this.scheduler??=this.injector.get(ip,null,{optional:!0}),this.scheduler?.notify(10)}).catch(a=>{o.use(r)}),o}begin(){this.delegate.begin?.()}end(){this.delegate.end?.()}whenRenderingDone(){return this.delegate.whenRenderingDone?.()??Promise.resolve()}componentReplaced(e){this._engine?.flush(),this.delegate.componentReplaced?.(e)}static \u0275fac=function(i){$a()};static \u0275prov=E({token:n,factory:n.\u0275fac})}return n})(),Tc=class{delegate;replay=[];\u0275type=1;constructor(t){this.delegate=t}use(t){if(this.delegate=t,this.replay!==null){for(let e of this.replay)e(t);this.replay=null}}get data(){return this.delegate.data}destroy(){this.replay=null,this.delegate.destroy()}createElement(t,e){return this.delegate.createElement(t,e)}createComment(t){return this.delegate.createComment(t)}createText(t){return this.delegate.createText(t)}get destroyNode(){return this.delegate.destroyNode}appendChild(t,e){this.delegate.appendChild(t,e)}insertBefore(t,e,i,r){this.delegate.insertBefore(t,e,i,r)}removeChild(t,e,i,r){this.delegate.removeChild(t,e,i,r)}selectRootElement(t,e){return this.delegate.selectRootElement(t,e)}parentNode(t){return this.delegate.parentNode(t)}nextSibling(t){return this.delegate.nextSibling(t)}setAttribute(t,e,i,r){this.delegate.setAttribute(t,e,i,r)}removeAttribute(t,e,i){this.delegate.removeAttribute(t,e,i)}addClass(t,e){this.delegate.addClass(t,e)}removeClass(t,e){this.delegate.removeClass(t,e)}setStyle(t,e,i,r){this.delegate.setStyle(t,e,i,r)}removeStyle(t,e,i){this.delegate.removeStyle(t,e,i)}setProperty(t,e,i){this.shouldReplay(e)&&this.replay.push(r=>r.setProperty(t,e,i)),this.delegate.setProperty(t,e,i)}setValue(t,e){this.delegate.setValue(t,e)}listen(t,e,i,r){return this.shouldReplay(e)&&this.replay.push(o=>o.listen(t,e,i,r)),this.delegate.listen(t,e,i,r)}shouldReplay(t){return this.replay!==null&&t.startsWith(gy)}},by=new M("");function th(n="animations"){return hp("NgAsyncAnimations"),ro([{provide:bt,useFactory:()=>new _y(m(W),m(Co),m(K),n)},{provide:Xi,useValue:n==="noop"?"NoopAnimations":"BrowserAnimations"}])}function vy(n,t){return new St(e=>{let i=!1,r=!1,o=n.subscribe(a=>{r=!0,setTimeout(()=>{e.next(a),i&&e.complete()},t)},a=>setTimeout(()=>e.error(a),t),()=>{i=!0,r||e.complete()});return()=>o.unsubscribe()})}var ce={CONTINUE:100,SWITCHING_PROTOCOLS:101,OK:200,CREATED:201,ACCEPTED:202,NON_AUTHORITATIVE_INFORMATION:203,NO_CONTENT:204,RESET_CONTENT:205,PARTIAL_CONTENT:206,MULTIPLE_CHOICES:300,MOVED_PERMANTENTLY:301,FOUND:302,SEE_OTHER:303,NOT_MODIFIED:304,USE_PROXY:305,TEMPORARY_REDIRECT:307,BAD_REQUEST:400,UNAUTHORIZED:401,PAYMENT_REQUIRED:402,FORBIDDEN:403,NOT_FOUND:404,METHOD_NOT_ALLOWED:405,NOT_ACCEPTABLE:406,PROXY_AUTHENTICATION_REQUIRED:407,REQUEST_TIMEOUT:408,CONFLICT:409,GONE:410,LENGTH_REQUIRED:411,PRECONDITION_FAILED:412,PAYLOAD_TO_LARGE:413,URI_TOO_LONG:414,UNSUPPORTED_MEDIA_TYPE:415,RANGE_NOT_SATISFIABLE:416,EXPECTATION_FAILED:417,IM_A_TEAPOT:418,UPGRADE_REQUIRED:426,INTERNAL_SERVER_ERROR:500,NOT_IMPLEMENTED:501,BAD_GATEWAY:502,SERVICE_UNAVAILABLE:503,GATEWAY_TIMEOUT:504,HTTP_VERSION_NOT_SUPPORTED:505,PROCESSING:102,MULTI_STATUS:207,IM_USED:226,PERMANENT_REDIRECT:308,UNPROCESSABLE_ENTRY:422,LOCKED:423,FAILED_DEPENDENCY:424,PRECONDITION_REQUIRED:428,TOO_MANY_REQUESTS:429,REQUEST_HEADER_FIELDS_TOO_LARGE:431,UNAVAILABLE_FOR_LEGAL_REASONS:451,VARIANT_ALSO_NEGOTIATES:506,INSUFFICIENT_STORAGE:507,NETWORK_AUTHENTICATION_REQUIRED:511},yy={100:{code:100,text:"Continue",description:'"The initial part of a request has been received and has not yet been rejected by the server."',spec_title:"RFC7231#6.2.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.2.1"},101:{code:101,text:"Switching Protocols",description:`"The server understands and is willing to comply with the client's request, via the Upgrade header field, for a change in the application protocol being used on this connection."`,spec_title:"RFC7231#6.2.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.2.2"},200:{code:200,text:"OK",description:'"The request has succeeded."',spec_title:"RFC7231#6.3.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.1"},201:{code:201,text:"Created",description:'"The request has been fulfilled and has resulted in one or more new resources being created."',spec_title:"RFC7231#6.3.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.2"},202:{code:202,text:"Accepted",description:'"The request has been accepted for processing, but the processing has not been completed."',spec_title:"RFC7231#6.3.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.3"},203:{code:203,text:"Non-Authoritative Information",description:`"The request was successful but the enclosed payload has been modified from that of the origin server's 200 (OK) response by a transforming proxy."`,spec_title:"RFC7231#6.3.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.4"},204:{code:204,text:"No Content",description:'"The server has successfully fulfilled the request and that there is no additional content to send in the response payload body."',spec_title:"RFC7231#6.3.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.5"},205:{code:205,text:"Reset Content",description:'"The server has fulfilled the request and desires that the user agent reset the "document view", which caused the request to be sent, to its original state as received from the origin server."',spec_title:"RFC7231#6.3.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.6"},206:{code:206,text:"Partial Content",description:`"The server is successfully fulfilling a range request for the target resource by transferring one or more parts of the selected representation that correspond to the satisfiable ranges found in the requests's Range header field."`,spec_title:"RFC7233#4.1",spec_href:"https://tools.ietf.org/html/rfc7233#section-4.1"},300:{code:300,text:"Multiple Choices",description:'"The target resource has more than one representation, each with its own more specific identifier, and information about the alternatives is being provided so that the user (or user agent) can select a preferred representation by redirecting its request to one or more of those identifiers."',spec_title:"RFC7231#6.4.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.1"},301:{code:301,text:"Moved Permanently",description:'"The target resource has been assigned a new permanent URI and any future references to this resource ought to use one of the enclosed URIs."',spec_title:"RFC7231#6.4.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.2"},302:{code:302,text:"Found",description:'"The target resource resides temporarily under a different URI."',spec_title:"RFC7231#6.4.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.3"},303:{code:303,text:"See Other",description:'"The server is redirecting the user agent to a different resource, as indicated by a URI in the Location header field, that is intended to provide an indirect response to the original request."',spec_title:"RFC7231#6.4.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.4"},304:{code:304,text:"Not Modified",description:'"A conditional GET request has been received and would have resulted in a 200 (OK) response if it were not for the fact that the condition has evaluated to false."',spec_title:"RFC7232#4.1",spec_href:"https://tools.ietf.org/html/rfc7232#section-4.1"},305:{code:305,text:"Use Proxy",description:"*deprecated*",spec_title:"RFC7231#6.4.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.5"},307:{code:307,text:"Temporary Redirect",description:'"The target resource resides temporarily under a different URI and the user agent MUST NOT change the request method if it performs an automatic redirection to that URI."',spec_title:"RFC7231#6.4.7",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.7"},400:{code:400,text:"Bad Request",description:'"The server cannot or will not process the request because the received syntax is invalid, nonsensical, or exceeds some limitation on what the server is willing to process."',spec_title:"RFC7231#6.5.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.1"},401:{code:401,text:"Unauthorized",description:'"The request has not been applied because it lacks valid authentication credentials for the target resource."',spec_title:"RFC7235#6.3.1",spec_href:"https://tools.ietf.org/html/rfc7235#section-3.1"},402:{code:402,text:"Payment Required",description:"*reserved*",spec_title:"RFC7231#6.5.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.2"},403:{code:403,text:"Forbidden",description:'"The server understood the request but refuses to authorize it."',spec_title:"RFC7231#6.5.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.3"},404:{code:404,text:"Not Found",description:'"The origin server did not find a current representation for the target resource or is not willing to disclose that one exists."',spec_title:"RFC7231#6.5.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.4"},405:{code:405,text:"Method Not Allowed",description:'"The method specified in the request-line is known by the origin server but not supported by the target resource."',spec_title:"RFC7231#6.5.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.5"},406:{code:406,text:"Not Acceptable",description:'"The target resource does not have a current representation that would be acceptable to the user agent, according to the proactive negotiation header fields received in the request, and the server is unwilling to supply a default representation."',spec_title:"RFC7231#6.5.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.6"},407:{code:407,text:"Proxy Authentication Required",description:'"The client needs to authenticate itself in order to use a proxy."',spec_title:"RFC7231#6.3.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.2"},408:{code:408,text:"Request Timeout",description:'"The server did not receive a complete request message within the time that it was prepared to wait."',spec_title:"RFC7231#6.5.7",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.7"},409:{code:409,text:"Conflict",description:'"The request could not be completed due to a conflict with the current state of the resource."',spec_title:"RFC7231#6.5.8",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.8"},410:{code:410,text:"Gone",description:'"Access to the target resource is no longer available at the origin server and that this condition is likely to be permanent."',spec_title:"RFC7231#6.5.9",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.9"},411:{code:411,text:"Length Required",description:'"The server refuses to accept the request without a defined Content-Length."',spec_title:"RFC7231#6.5.10",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.10"},412:{code:412,text:"Precondition Failed",description:'"One or more preconditions given in the request header fields evaluated to false when tested on the server."',spec_title:"RFC7232#4.2",spec_href:"https://tools.ietf.org/html/rfc7232#section-4.2"},413:{code:413,text:"Payload Too Large",description:'"The server is refusing to process a request because the request payload is larger than the server is willing or able to process."',spec_title:"RFC7231#6.5.11",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.11"},414:{code:414,text:"URI Too Long",description:'"The server is refusing to service the request because the request-target is longer than the server is willing to interpret."',spec_title:"RFC7231#6.5.12",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.12"},415:{code:415,text:"Unsupported Media Type",description:'"The origin server is refusing to service the request because the payload is in a format not supported by the target resource for this method."',spec_title:"RFC7231#6.5.13",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.13"},416:{code:416,text:"Range Not Satisfiable",description:`"None of the ranges in the request's Range header field overlap the current extent of the selected resource or that the set of ranges requested has been rejected due to invalid ranges or an excessive request of small or overlapping ranges."`,spec_title:"RFC7233#4.4",spec_href:"https://tools.ietf.org/html/rfc7233#section-4.4"},417:{code:417,text:"Expectation Failed",description:`"The expectation given in the request's Expect header field could not be met by at least one of the inbound servers."`,spec_title:"RFC7231#6.5.14",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.14"},418:{code:418,text:"I'm a teapot",description:'"1988 April Fools Joke. Returned by tea pots requested to brew coffee."',spec_title:"RFC 2324",spec_href:"https://tools.ietf.org/html/rfc2324"},426:{code:426,text:"Upgrade Required",description:'"The server refuses to perform the request using the current protocol but might be willing to do so after the client upgrades to a different protocol."',spec_title:"RFC7231#6.5.15",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.15"},500:{code:500,text:"Internal Server Error",description:'"The server encountered an unexpected condition that prevented it from fulfilling the request."',spec_title:"RFC7231#6.6.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.1"},501:{code:501,text:"Not Implemented",description:'"The server does not support the functionality required to fulfill the request."',spec_title:"RFC7231#6.6.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.2"},502:{code:502,text:"Bad Gateway",description:'"The server, while acting as a gateway or proxy, received an invalid response from an inbound server it accessed while attempting to fulfill the request."',spec_title:"RFC7231#6.6.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.3"},503:{code:503,text:"Service Unavailable",description:'"The server is currently unable to handle the request due to a temporary overload or scheduled maintenance, which will likely be alleviated after some delay."',spec_title:"RFC7231#6.6.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.4"},504:{code:504,text:"Gateway Time-out",description:'"The server, while acting as a gateway or proxy, did not receive a timely response from an upstream server it needed to access in order to complete the request."',spec_title:"RFC7231#6.6.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.5"},505:{code:505,text:"HTTP Version Not Supported",description:'"The server does not support, or refuses to support, the protocol version that was used in the request message."',spec_title:"RFC7231#6.6.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.6"},102:{code:102,text:"Processing",description:'"An interim response to inform the client that the server has accepted the complete request, but has not yet completed it."',spec_title:"RFC5218#10.1",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.1"},207:{code:207,text:"Multi-Status",description:'"Status for multiple independent operations."',spec_title:"RFC5218#10.2",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.2"},226:{code:226,text:"IM Used",description:'"The server has fulfilled a GET request for the resource, and the response is a representation of the result of one or more instance-manipulations applied to the current instance."',spec_title:"RFC3229#10.4.1",spec_href:"https://tools.ietf.org/html/rfc3229#section-10.4.1"},308:{code:308,text:"Permanent Redirect",description:'"The target resource has been assigned a new permanent URI and any future references to this resource SHOULD use one of the returned URIs. [...] This status code is similar to 301 Moved Permanently (Section 7.3.2 of rfc7231), except that it does not allow rewriting the request method from POST to GET."',spec_title:"RFC7238",spec_href:"https://tools.ietf.org/html/rfc7238"},422:{code:422,text:"Unprocessable Entity",description:'"The server understands the content type of the request entity (hence a 415(Unsupported Media Type) status code is inappropriate), and the syntax of the request entity is correct (thus a 400 (Bad Request) status code is inappropriate) but was unable to process the contained instructions."',spec_title:"RFC5218#10.3",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.3"},423:{code:423,text:"Locked",description:'"The source or destination resource of a method is locked."',spec_title:"RFC5218#10.4",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.4"},424:{code:424,text:"Failed Dependency",description:'"The method could not be performed on the resource because the requested action depended on another action and that action failed."',spec_title:"RFC5218#10.5",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.5"},428:{code:428,text:"Precondition Required",description:'"The origin server requires the request to be conditional."',spec_title:"RFC6585#3",spec_href:"https://tools.ietf.org/html/rfc6585#section-3"},429:{code:429,text:"Too Many Requests",description:'"The user has sent too many requests in a given amount of time ("rate limiting")."',spec_title:"RFC6585#4",spec_href:"https://tools.ietf.org/html/rfc6585#section-4"},431:{code:431,text:"Request Header Fields Too Large",description:'"The server is unwilling to process the request because its header fields are too large."',spec_title:"RFC6585#5",spec_href:"https://tools.ietf.org/html/rfc6585#section-5"},451:{code:451,text:"Unavailable For Legal Reasons",description:'"The server is denying access to the resource in response to a legal demand."',spec_title:"draft-ietf-httpbis-legally-restricted-status",spec_href:"https://tools.ietf.org/html/draft-ietf-httpbis-legally-restricted-status"},506:{code:506,text:"Variant Also Negotiates",description:'"The server has an internal configuration error: the chosen variant resource is configured to engage in transparent content negotiation itself, and is therefore not a proper end point in the negotiation process."',spec_title:"RFC2295#8.1",spec_href:"https://tools.ietf.org/html/rfc2295#section-8.1"},507:{code:507,text:"Insufficient Storage",description:'The method could not be performed on the resource because the server is unable to store the representation needed to successfully complete the request."',spec_title:"RFC5218#10.6",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.6"},511:{code:511,text:"Network Authentication Required",description:'"The client needs to authenticate to gain network access."',spec_title:"RFC6585#6",spec_href:"https://tools.ietf.org/html/rfc6585#section-6"}};function xy(n){return yy[n+""].text||"Unknown Status"}function wy(n){return n>=200&&n<300}var So=class{},Oc=class{apiBase;caseSensitiveSearch;dataEncapsulation;delay;delete404;host;passThruUnknownUrl;post204;post409;put204;put404;rootPath},as=(()=>{class n{constructor(e={}){Object.assign(this,{caseSensitiveSearch:!1,dataEncapsulation:!1,delay:500,delete404:!1,passThruUnknownUrl:!1,post204:!0,post409:!1,put204:!0,put404:!1,apiBase:void 0,host:void 0,rootPath:void 0},e)}static \u0275fac=function(i){return new(i||n)(j(Oc))};static \u0275prov=E({token:n,factory:n.\u0275fac})}return n})();function Cy(n){let e=/^(?:(?![^:@]+:[^:@\/]*@)([^:\/?#.]+):)?(?:\/\/)?((?:(([^:@]*)(?::([^:@]*))?)?@)?([^:\/?#]*)(?::(\d*))?)(((\/(?:[^?#](?![^?#\/]*\.[^?#\/.]+(?:[?#]|$)))*\/?)?([^?#\/]*))(?:\?([^#]*))?(?:#(.*))?)/.exec(n),i={source:"",protocol:"",authority:"",userInfo:"",user:"",password:"",host:"",port:"",relative:"",path:"",directory:"",file:"",query:"",anchor:""},r=Object.keys(i),o=r.length;for(;o--;)i[r[o]]=e&&e[o]||"";return i}function ky(n){return n.replace(/\/$/,"")}var Rc=class{inMemDbService;config=new as;db={};dbReadySubject;passThruBackend;requestInfoUtils=this.getRequestInfoUtils();constructor(t,e={}){this.inMemDbService=t;let i=this.getLocation("/");this.config.host=i.host,this.config.rootPath=i.path,Object.assign(this.config,e)}get dbReady(){return this.dbReadySubject||(this.dbReadySubject=new Kn(!1),this.resetDb()),this.dbReadySubject.asObservable().pipe(Ra(t=>t))}handleRequest(t){return this.dbReady.pipe(eo(()=>this.handleRequest_(t)))}handleRequest_(t){let e=t.urlWithParams?t.urlWithParams:t.url,i=this.bind("parseRequestUrl"),r=i&&i(e,this.requestInfoUtils)||this.parseRequestUrl(e),o=r.collectionName,a=this.db[o],s={req:t,apiBase:r.apiBase,collection:a,collectionName:o,headers:this.createHeaders({"Content-Type":"application/json"}),id:this.parseId(a,o,r.id),method:this.getRequestMethod(t),query:r.query,resourceUrl:r.resourceUrl,url:e,utils:this.requestInfoUtils},l;if(/commands\/?$/i.test(s.apiBase))return this.commands(s);let d=this.bind(s.method);if(d){let c=d(s);if(c)return c}return this.db[o]?this.createResponse$(()=>this.collectionHandler(s)):this.config.passThruUnknownUrl?this.getPassThruBackend().handle(t):(l=this.createErrorResponseOptions(e,ce.NOT_FOUND,`Collection '${o}' not found`),this.createResponse$(()=>l))}addDelay(t){let e=this.config.delay;return e===0?t:vy(t,e||500)}applyQuery(t,e){let i=[],r=this.config.caseSensitiveSearch?void 0:"i";e.forEach((a,s)=>{a.forEach(l=>i.push({name:s,rx:new RegExp(decodeURI(l),r)}))});let o=i.length;return o?t.filter(a=>{let s=!0,l=o;for(;s&&l;){l-=1;let d=i[l];s=d.rx.test(a[d.name])}return s}):t}bind(t){let e=this.inMemDbService[t];return e?e.bind(this.inMemDbService):void 0}bodify(t){return this.config.dataEncapsulation?{data:t}:t}clone(t){return JSON.parse(JSON.stringify(t))}collectionHandler(t){let e;switch(t.method){case"get":e=this.get(t);break;case"post":e=this.post(t);break;case"put":e=this.put(t);break;case"delete":e=this.delete(t);break;default:e=this.createErrorResponseOptions(t.url,ce.METHOD_NOT_ALLOWED,"Method not allowed");break}let i=this.bind("responseInterceptor");return i?i(e,t):e}commands(t){let e=t.collectionName.toLowerCase(),i=t.method,r={url:t.url};switch(e){case"resetdb":return r.status=ce.NO_CONTENT,this.resetDb(t).pipe(eo(()=>this.createResponse$(()=>r,!1)));case"config":if(i==="get")r.status=ce.OK,r.body=this.clone(this.config);else{let o=this.getJsonBody(t.req);Object.assign(this.config,o),this.passThruBackend=void 0,r.status=ce.NO_CONTENT}break;default:r=this.createErrorResponseOptions(t.url,ce.INTERNAL_SERVER_ERROR,`Unknown command "${e}"`)}return this.createResponse$(()=>r,!1)}createErrorResponseOptions(t,e,i){return{body:{error:`${i}`},url:t,headers:this.createHeaders({"Content-Type":"application/json"}),status:e}}createResponse$(t,e=!0){let i=this.createResponseOptions$(t),r=this.createResponse$fromResponseOptions$(i);return e?this.addDelay(r):r}createResponseOptions$(t){return new St(e=>{let i;try{i=t()}catch(o){let a=o.message||o;i=this.createErrorResponseOptions("",ce.INTERNAL_SERVER_ERROR,`${a}`)}let r=i.status;try{i.statusText=r!=null?xy(r):void 0}catch{}return r!=null&&wy(r)?(e.next(i),e.complete()):e.error(i),()=>{}})}delete({collection:t,collectionName:e,headers:i,id:r,url:o}){if(r==null)return this.createErrorResponseOptions(o,ce.NOT_FOUND,`Missing "${e}" id`);let a=this.removeById(t,r);return{headers:i,status:a||!this.config.delete404?ce.NO_CONTENT:ce.NOT_FOUND}}findById(t,e){return t.find(i=>i.id===e)}genId(t,e){let i=this.bind("genId");if(i){let r=i(t,e);if(r!=null)return r}return this.genIdDefault(t,e)}genIdDefault(t,e){if(!this.isCollectionIdNumeric(t,e))throw new Error(`Collection '${e}' id type is non-numeric or unknown. Can only generate numeric ids.`);let i=0;return t.reduce((r,o)=>{i=Math.max(i,typeof o.id=="number"?o.id:i)},void 0),i+1}get({collection:t,collectionName:e,headers:i,id:r,query:o,url:a}){let s=t;return r!=null&&r!==""?s=this.findById(t,r):o&&(s=this.applyQuery(t,o)),s?{body:this.bodify(this.clone(s)),headers:i,status:ce.OK}:this.createErrorResponseOptions(a,ce.NOT_FOUND,`'${e}' with id='${r}' not found`)}getLocation(t){if(!t.startsWith("http")){let e=typeof document>"u"?void 0:document,i=e?e.location.protocol+"//"+e.location.host:"http://fake";t=t.startsWith("/")?i+t:i+"/"+t}return Cy(t)}getPassThruBackend(){return this.passThruBackend?this.passThruBackend:this.passThruBackend=this.createPassThruBackend()}getRequestInfoUtils(){return{createResponse$:this.createResponse$.bind(this),findById:this.findById.bind(this),isCollectionIdNumeric:this.isCollectionIdNumeric.bind(this),getConfig:()=>this.config,getDb:()=>this.db,getJsonBody:this.getJsonBody.bind(this),getLocation:this.getLocation.bind(this),getPassThruBackend:this.getPassThruBackend.bind(this),parseRequestUrl:this.parseRequestUrl.bind(this)}}indexOf(t,e){return t.findIndex(i=>i.id===e)}parseId(t,e,i){if(!this.isCollectionIdNumeric(t,e))return i;let r=parseFloat(i);return isNaN(r)?i:r}isCollectionIdNumeric(t,e){return!!(t&&t[0])&&typeof t[0].id=="number"}parseRequestUrl(t){try{let e=this.getLocation(t),i=(this.config.rootPath||"").length,r="";e.host!==this.config.host&&(i=1,r=e.protocol+"//"+e.host+"/");let a=e.path.substring(i).split("/"),s=0,l;this.config.apiBase==null?l=a[s++]:(l=ky(this.config.apiBase.trim()),l?s=l.split("/").length:s=0),l+="/";let d=a[s++];d=d&&d.split(".")[0];let c=a[s++],u=this.createQueryMap(e.query),h=r+l+d+"/";return{apiBase:l,collectionName:d,id:c,query:u,resourceUrl:h}}catch(e){let i=`unable to parse url '${t}'; original error: ${e.message}`;throw new Error(i)}}post({collection:t,collectionName:e,headers:i,id:r,req:o,resourceUrl:a,url:s}){let l=this.clone(this.getJsonBody(o));if(l.id==null)try{l.id=r||this.genId(t,e)}catch(u){let h=u.message||"";return/id type is non-numeric/.test(h)?this.createErrorResponseOptions(s,ce.UNPROCESSABLE_ENTRY,h):this.createErrorResponseOptions(s,ce.INTERNAL_SERVER_ERROR,`Failed to generate new id for '${e}'`)}if(r&&r!==l.id)return this.createErrorResponseOptions(s,ce.BAD_REQUEST,"Request id does not match item.id");r=l.id;let d=this.indexOf(t,r),c=this.bodify(l);return d===-1?(t.push(l),i.set("Location",a+"/"+r),{headers:i,body:c,status:ce.CREATED}):this.config.post409?this.createErrorResponseOptions(s,ce.CONFLICT,`'${e}' item with id='${r} exists and may not be updated with POST; use PUT instead.`):(t[d]=l,this.config.post204?{headers:i,status:ce.NO_CONTENT}:{headers:i,body:c,status:ce.OK})}put({collection:t,collectionName:e,headers:i,id:r,req:o,url:a}){let s=this.clone(this.getJsonBody(o));if(s.id==null)return this.createErrorResponseOptions(a,ce.NOT_FOUND,`Missing '${e}' id`);if(r&&r!==s.id)return this.createErrorResponseOptions(a,ce.BAD_REQUEST,`Request for '${e}' id does not match item.id`);r=s.id;let l=this.indexOf(t,r),d=this.bodify(s);return l>-1?(t[l]=s,this.config.put204?{headers:i,status:ce.NO_CONTENT}:{headers:i,body:d,status:ce.OK}):this.config.put404?this.createErrorResponseOptions(a,ce.NOT_FOUND,`'${e}' item with id='${r} not found and may not be created with PUT; use POST instead.`):(t.push(s),{headers:i,body:d,status:ce.CREATED})}removeById(t,e){let i=this.indexOf(t,e);return i>-1?(t.splice(i,1),!0):!1}resetDb(t){this.dbReadySubject&&this.dbReadySubject.next(!1);let e=this.inMemDbService.createDb(t);return(e instanceof St?e:typeof e.then=="function"?Ku(e):Pe(e)).pipe(Ra()).subscribe(r=>{this.db=r,this.dbReadySubject&&this.dbReadySubject.next(!0)}),this.dbReady}},Dy=(()=>{class n extends Rc{xhrFactory;constructor(e,i,r){super(e,i),this.xhrFactory=r}handle(e){try{return this.handleRequest(e)}catch(i){let r=i.message||i,o=this.createErrorResponseOptions(e.url,ce.INTERNAL_SERVER_ERROR,`${r}`);return this.createResponse$(()=>o)}}getJsonBody(e){return e.body}getRequestMethod(e){return(e.method||"get").toLowerCase()}createHeaders(e){return new fn(e)}createQueryMap(e){let i=new Map;if(e){let r=new Bt({fromString:e});r.keys().forEach(o=>i.set(o,r.getAll(o)||[]))}return i}createResponse$fromResponseOptions$(e){return e.pipe(xe(i=>new yi(i)))}createPassThruBackend(){try{return new Do(this.xhrFactory)}catch(e){throw e.message="Cannot create passThru404 backend; "+(e.message||""),e}}static \u0275fac=function(i){return new(i||n)(j(So),j(as,8),j(en))};static \u0275prov=E({token:n,factory:n.\u0275fac})}return n})();function Ey(){return new Dy(m(So),m(as),m(en))}var nh=(()=>{class n{static forRoot(e,i){return{ngModule:n,providers:[{provide:So,useClass:e},{provide:as,useValue:i},{provide:Eo,useFactory:Ey}]}}static forFeature(e,i){return n.forRoot(e,i)}static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({})}return n})();var ih={asyncapi:"3.1.0",info:{title:"Springwolf example project - AMQP",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-amqp-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com","x-phone":"+49 123 456789"},license:{name:"Apache License 2.0","x-desc":"some description"},"x-api-audience":"company-internal","x-generator":"springwolf"},defaultContentType:"application/json",servers:{"amqp-server":{host:"amqp:5672",protocol:"amqp"}},channels:{"CRUD-topic-exchange-1":{address:"CRUD-topic-exchange-1",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"CRUD-topic-exchange-1",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"CRUD-topic-exchange-2":{address:"CRUD-topic-exchange-2",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"CRUD-topic-exchange-2",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"another-queue",durable:!0,exclusive:!1,autoDelete:!0,vhost:"/"},bindingVersion:"0.3.0"}}},"example-bindings-queue":{address:"example-bindings-queue",bindings:{amqp:{is:"queue",queue:{name:"example-bindings-queue",durable:!0,exclusive:!1,autoDelete:!0,vhost:"/"},bindingVersion:"0.3.0"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"example-queue",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"example-topic-exchange":{address:"example-topic-exchange",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},bindings:{}},"example-topic-exchange_example-topic-routing-key":{address:"example-topic-exchange_example-topic-routing-key",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"example-topic-exchange",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"multi-payload-queue":{address:"multi-payload-queue",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"multi-payload-queue",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-create":{address:"queue-create",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}},bindings:{amqp:{is:"queue",queue:{name:"queue-create",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-delete":{address:"queue-delete",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}},bindings:{amqp:{is:"queue",queue:{name:"queue-delete",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-read":{address:"queue-read",bindings:{amqp:{is:"queue",queue:{name:"queue-read",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-update":{address:"queue-update",bindings:{amqp:{is:"queue",queue:{name:"queue-update",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},SpringRabbitListenerDefaultHeaders:{title:"SpringRabbitListenerDefaultHeaders",type:"object",properties:{},examples:[{}]},"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},description:"Generic payload model",examples:[{genericValue:{someEnum:"FOO2",someLong:5,someString:"some string value"}}],required:["genericValue"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{type:"integer",description:"Generic Payload field",format:"int64"}},description:"Generic payload model",examples:[{genericValue:0}],required:["genericValue"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{type:"string",description:"Generic Payload field"}},description:"Generic payload model",examples:[{genericValue:"string"}],required:["genericValue"]}},messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto",title:"GenericPayloadDtoExamplePayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long",title:"GenericPayloadDtoLong",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String",title:"GenericPayloadDtoString",bindings:{amqp:{bindingVersion:"0.3.0"}}}}},operations:{"CRUD-topic-exchange-1_receive_bindingsUpdate":{action:"receive",channel:{$ref:"#/channels/CRUD-topic-exchange-1"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/CRUD-topic-exchange-1/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"CRUD-topic-exchange-2_receive_bindingsRead":{action:"receive",channel:{$ref:"#/channels/CRUD-topic-exchange-2"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/CRUD-topic-exchange-2/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"example-topic-exchange_example-topic-routing-key_receive_bindingsExample":{action:"receive",channel:{$ref:"#/channels/example-topic-exchange_example-topic-routing-key"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-topic-exchange_example-topic-routing-key/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"example-topic-exchange_send_sendMessage":{action:"send",channel:{$ref:"#/channels/example-topic-exchange"},title:"example-topic-exchange_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{amqp:{expiration:0,cc:[],priority:0,deliveryMode:1,mandatory:!1,bcc:[],timestamp:!1,ack:!1,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-topic-exchange/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}]},"multi-payload-queue_receive_bindingsBeanExample":{action:"receive",channel:{$ref:"#/channels/multi-payload-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/multi-payload-queue/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"},{$ref:"#/channels/multi-payload-queue/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"queue-create_receive_queuesToDeclareCreate":{action:"receive",channel:{$ref:"#/channels/queue-create"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/queue-create/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}]},"queue-delete_receive_queuesToDeclareDelete":{action:"receive",channel:{$ref:"#/channels/queue-delete"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/queue-delete/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}]}}};var rh={asyncapi:"3.1.0",info:{title:"Springwolf example project - Cloud Stream",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-cloud-stream-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"another-topic":{address:"another-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"biconsumer-topic":{address:"biconsumer-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"bifunction-output-topic":{address:"bifunction-output-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"bifunction-topic":{address:"bifunction-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"consumer-class-topic":{address:"consumer-class-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"consumer-topic":{address:"consumer-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"example-topic":{address:"example-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"google-pubsub-topic":{address:"google-pubsub-topic",messages:{GooglePubSubPayloadDto:{$ref:"#/components/messages/GooglePubSubPayloadDto"}},bindings:{googlepubsub:{messageStoragePolicy:{},schemaSettings:{encoding:"BINARY",name:"project/test"},bindingVersion:"0.2.0"}}}},components:{schemas:{AnotherPayloadDto:{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},ExamplePayloadDto:{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]},GooglePubSubPayloadDto:{title:"GooglePubSubPayloadDto",type:"object",properties:{someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Google pubsub payload model",examples:[{someLong:5,someString:"some string value"}],required:["someString"]},HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]}},messages:{AnotherPayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/AnotherPayloadDto"}},name:"AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{kafka:{}}},ExamplePayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/ExamplePayloadDto"}},name:"ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{kafka:{}}},GooglePubSubPayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/GooglePubSubPayloadDto"}},name:"GooglePubSubPayloadDto",title:"GooglePubSubPayloadDto",bindings:{googlepubsub:{schema:{name:"project/test"},bindingVersion:"0.2.0"}}}}},operations:{"another-topic_send_process":{action:"send",channel:{$ref:"#/channels/another-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/another-topic/messages/AnotherPayloadDto"}]},"biconsumer-topic_receive_biConsumerMethod":{action:"receive",channel:{$ref:"#/channels/biconsumer-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/biconsumer-topic/messages/AnotherPayloadDto"}]},"bifunction-output-topic_send_biProcess":{action:"send",channel:{$ref:"#/channels/bifunction-output-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/bifunction-output-topic/messages/AnotherPayloadDto"}]},"bifunction-topic_receive_biProcess":{action:"receive",channel:{$ref:"#/channels/bifunction-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/bifunction-topic/messages/ExamplePayloadDto"}]},"consumer-class-topic_receive_ConsumerClass":{action:"receive",channel:{$ref:"#/channels/consumer-class-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/consumer-class-topic/messages/ExamplePayloadDto"}]},"consumer-topic_receive_consumerMethod":{action:"receive",channel:{$ref:"#/channels/consumer-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/consumer-topic/messages/AnotherPayloadDto"}]},"example-topic_receive_process":{action:"receive",channel:{$ref:"#/channels/example-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/example-topic/messages/ExamplePayloadDto"}]},"google-pubsub-topic_receive_googlePubSubConsumerMethod":{action:"receive",channel:{$ref:"#/channels/google-pubsub-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/google-pubsub-topic/messages/GooglePubSubPayloadDto"}]}}};var oh={asyncapi:"3.1.0",info:{title:"Springwolf example project - Kafka",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-kafka-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"another-topic":{address:"another-topic",messages:{"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"avro-topic":{address:"avro-topic",messages:{"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"example-topic":{address:"example-topic",messages:{"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"integer-topic":{address:"integer-topic",messages:{"java.lang.Integer":{$ref:"#/components/messages/java.lang.Integer"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"multi-payload-topic":{address:"multi-payload-topic",messages:{"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"},"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},"javax.money.MonetaryAmount":{$ref:"#/components/messages/javax.money.MonetaryAmount"}},bindings:{kafka:{topic:"multi-payload-topic",partitions:3,replicas:1,topicConfiguration:{"cleanup.policy":["compact","delete"],"retention.ms":864e5,"retention.bytes":-1,"delete.retention.ms":864e5,"max.message.bytes":1048588},bindingVersion:"0.5.0"}}},"no-payload-used-topic":{address:"no-payload-used-topic",messages:{PayloadNotUsed:{$ref:"#/components/messages/PayloadNotUsed"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"nullable-topic":{address:"nullable-topic",messages:{"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"protobuf-topic":{address:"protobuf-topic",messages:{"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"string-topic":{address:"string-topic",messages:{"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"},"java.lang.String":{$ref:"#/components/messages/java.lang.String"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"topic-defined-via-asyncPublisher-annotation":{address:"topic-defined-via-asyncPublisher-annotation",messages:{"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}},servers:[{$ref:"#/servers/kafka-server"}],bindings:{}},"vehicle-topic":{address:"vehicle-topic",messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"xml-topic":{address:"xml-topic",messages:{"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"yaml-topic":{address:"yaml-topic",messages:{"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"There can be headers, but they are not explicitly documented.",title:"HeadersNotDocumented",type:"object"}},HeadersNotUsed:{title:"HeadersNotUsed",type:"object",properties:{},description:"No headers are present.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"No headers are present.",title:"HeadersNotUsed",type:"object"}},PayloadNotUsed:{title:"PayloadNotUsed",type:"object",properties:{},description:"No payload specified",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"No payload specified",title:"PayloadNotUsed",type:"object"}},SpringDefaultHeaderAndCloudEvent:{title:"SpringDefaultHeaderAndCloudEvent",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"]},ce_id:{title:"ce_id",type:"string",description:"CloudEvent Id Header",enum:["2c60089e-6f39-459d-8ced-2d6df7e4c03a"],examples:["2c60089e-6f39-459d-8ced-2d6df7e4c03a"]},ce_source:{title:"ce_source",type:"string",description:"CloudEvent Source Header",enum:["http://localhost"],examples:["http://localhost"]},ce_specversion:{title:"ce_specversion",type:"string",description:"CloudEvent Spec Version Header",enum:["1.0"],examples:["1.0"]},ce_subject:{title:"ce_subject",type:"string",description:"CloudEvent Subject Header",enum:["Springwolf example project - Kafka"],examples:["Springwolf example project - Kafka"]},ce_time:{title:"ce_time",type:"string",description:"CloudEvent Time Header",format:"date-time",enum:["2023-10-28T20:01:23+00:00"],examples:["2023-10-28T20:01:23+00:00"]},ce_type:{title:"ce_type",type:"string",description:"CloudEvent Payload Type Header",enum:["NestedPayloadDto.v1"],examples:["NestedPayloadDto.v1"]},"content-type":{title:"content-type",type:"string",description:"CloudEvent Content-Type Header",enum:["application/json"],examples:["application/json"]}},description:"Spring __TypeId__ and CloudEvent Headers",examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto",ce_id:"2c60089e-6f39-459d-8ced-2d6df7e4c03a",ce_source:"http://localhost",ce_specversion:"1.0",ce_subject:"Springwolf example project - Kafka",ce_time:"2023-10-28T20:01:23+00:00",ce_type:"NestedPayloadDto.v1","content-type":"application/json"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Spring __TypeId__ and CloudEvent Headers",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"],title:"__TypeId__",type:"string"},ce_id:{description:"CloudEvent Id Header",enum:["2c60089e-6f39-459d-8ced-2d6df7e4c03a"],title:"ce_id",type:"string"},ce_source:{description:"CloudEvent Source Header",enum:["http://localhost"],title:"ce_source",type:"string"},ce_specversion:{description:"CloudEvent Spec Version Header",enum:["1.0"],title:"ce_specversion",type:"string"},ce_subject:{description:"CloudEvent Subject Header",enum:["Springwolf example project - Kafka"],title:"ce_subject",type:"string"},ce_time:{description:"CloudEvent Time Header",enum:["2023-10-28T20:01:23+00:00"],format:"date-time",title:"ce_time",type:"string"},ce_type:{description:"CloudEvent Payload Type Header",enum:["NestedPayloadDto.v1"],title:"ce_type",type:"string"},"content-type":{description:"CloudEvent Content-Type Header",enum:["application/json"],title:"content-type",type:"string"}},title:"SpringDefaultHeaderAndCloudEvent",type:"object"}},"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto":{title:"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"],examples:["io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto",type:"object"}},"SpringKafkaDefaultHeaders-AnotherPayloadDto":{title:"SpringKafkaDefaultHeaders-AnotherPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-AnotherTopic":{title:"SpringKafkaDefaultHeaders-AnotherTopic",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Type ID"},my_uuid_field:{title:"my_uuid_field",type:"string",description:"Event identifier",format:"uuid",enum:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"],examples:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"]}},examples:[{__TypeId__:"string",my_uuid_field:"00000000-0000-0000-0000-000000000000"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Type ID",title:"__TypeId__",type:"string"},my_uuid_field:{description:"Event identifier",enum:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"],format:"uuid",title:"my_uuid_field",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherTopic",type:"object"}},"SpringKafkaDefaultHeaders-ExamplePayloadDto-546532105":{title:"SpringKafkaDefaultHeaders-ExamplePayloadDto-546532105",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"]},kafka_offset:{type:"integer",format:"int32",examples:[0]},kafka_receivedMessageKey:{type:"string",examples:['"string"']},kafka_recordMetadata:{title:"ConsumerRecordMetadata",type:"object",examples:[{}]}},examples:[{ConsumerRecordMetadata:{},__TypeId__:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto",kafka_offset:0,kafka_receivedMessageKey:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"],title:"__TypeId__",type:"string"},kafka_offset:{format:"int32",type:"integer"},kafka_receivedMessageKey:{type:"string"},kafka_recordMetadata:{title:"ConsumerRecordMetadata",type:"object"}},title:"SpringKafkaDefaultHeaders-ExamplePayloadDto-546532105",type:"object"}},"SpringKafkaDefaultHeaders-Message":{title:"SpringKafkaDefaultHeaders-Message",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"],examples:["io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-Message",type:"object"}},"SpringKafkaDefaultHeaders-MonetaryAmount":{title:"SpringKafkaDefaultHeaders-MonetaryAmount",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["javax.money.MonetaryAmount"],examples:["javax.money.MonetaryAmount"]}},examples:[{__TypeId__:"javax.money.MonetaryAmount"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["javax.money.MonetaryAmount"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-MonetaryAmount",type:"object"}},"SpringKafkaDefaultHeaders-PayloadNotUsed":{title:"SpringKafkaDefaultHeaders-PayloadNotUsed",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["PayloadNotUsed"],examples:["PayloadNotUsed"]}},examples:[{__TypeId__:"PayloadNotUsed"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["PayloadNotUsed"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-PayloadNotUsed",type:"object"}},"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto":{title:"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-VehicleBase":{title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],examples:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object"}},"SpringKafkaDefaultHeaders-XmlPayloadDto":{title:"SpringKafkaDefaultHeaders-XmlPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-XmlPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-YamlPayloadDto":{title:"SpringKafkaDefaultHeaders-YamlPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-YamlPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-integer":{title:"SpringKafkaDefaultHeaders-integer",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["java.lang.Integer"],examples:["java.lang.Integer"]}},examples:[{__TypeId__:"java.lang.Integer"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["java.lang.Integer"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-integer",type:"object"}},"SpringKafkaDefaultHeaders-string":{title:"SpringKafkaDefaultHeaders-string",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["java.lang.String"],examples:["java.lang.String"]}},examples:[{__TypeId__:"java.lang.String"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["java.lang.String"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-string",type:"object"}},"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{type:"string",description:"Payload description using @Schema annotation and @AsyncApiPayload within envelope class",maxLength:100,examples:['"string"'],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Payload description using @Schema annotation and @AsyncApiPayload within envelope class",maxLength:100,type:"string"}},"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{title:"AnotherPayloadAvroDto",type:"object",properties:{examplePayloadAvroDto:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.avro.ExamplePayloadAvroDto"},someEnum:{title:"ExampleEnum",type:"string",enum:["FOO1","FOO2","FOO3"]}},examples:[{examplePayloadAvroDto:{someLong:0,someString:"string"},someEnum:"FOO1"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{examplePayloadAvroDto:{properties:{someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"ExamplePayloadAvroDto",type:"object"},someEnum:{enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"}},title:"AnotherPayloadAvroDto",type:"object"}},"io.github.springwolf.examples.kafka.dto.avro.ExamplePayloadAvroDto":{title:"ExamplePayloadAvroDto",type:"object",properties:{someLong:{type:"integer",format:"int64"},someString:{type:"string"}},examples:[{someLong:0,someString:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"ExamplePayloadAvroDto",type:"object"}},"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{title:"Message",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",enum:["FOO1","FOO2","FOO3","UNRECOGNIZED"]},someLong:{type:"integer",format:"int64"},someString:{type:"string"}},examples:[{someEnum:"FOO1",someLong:0,someString:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someEnum:{enum:["FOO1","FOO2","FOO3","UNRECOGNIZED"],title:"ExampleEnum",type:"string"},someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"Message",type:"object"}},"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Another payload model",properties:{example:{description:`Example payload model demonstrating markdown text styling:
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
`],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someEnum:{enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"YamlPayloadDto",type:"string"}},"io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower":{title:"EnginePower",type:"object",properties:{hp:{type:"integer",format:"int32"},torque:{type:"integer",format:"int32"}},examples:[{hp:0,torque:0}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{hp:{format:"int32",type:"integer"},torque:{}},title:"EnginePower",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{discriminator:"vehicleType",title:"VehicleBase",type:"object",properties:{enginePower:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower"},powerSource:{type:"string"},topSpeed:{type:"integer",format:"int32"},vehicleType:{type:"string"}},description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],oneOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto"},{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{allOf:[{},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto":{title:"VehicleElectricPayloadDto",type:"object",description:"Electric vehicle implementation of VehicleBase",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{batteryCapacity:{type:"integer",format:"int32"},chargeTime:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{},{allOf:[{},{properties:{fuelCapacity:{format:"int32",type:"integer"}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{batteryCapacity:{},chargeTime:{}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto":{title:"VehicleGasolinePayloadDto",type:"object",description:"Gasoline vehicle implementation of VehicleBase",examples:[{enginePower:{hp:0,torque:0},fuelCapacity:0,powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{fuelCapacity:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}},"java.lang.Integer":{type:"integer",format:"int32",examples:[0],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",format:"int32",type:"integer"}},"java.lang.String":{type:"string",examples:['"string"'],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",type:"string"}},"javax.money.MonetaryAmount":{type:"object",properties:{amount:{type:"number",exclusiveMinimum:.01,examples:[99.99]},currency:{type:"string",examples:["USD"]}},examples:[{amount:99.99,currency:"USD"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{amount:{exclusiveMinimum:.01,type:"number"},currency:{type:"string"}},type:"object"}}},messages:{PayloadNotUsed:{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-PayloadNotUsed"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{title:"PayloadNotUsed",type:"object",properties:{},description:"No payload specified"}},name:"PayloadNotUsed",title:"PayloadNotUsed",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{headers:{$ref:"#/components/schemas/HeadersNotUsed"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"}},name:"StringPayload",title:"StringEnvelope",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}},contentType:"application/avro",name:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto",title:"AnotherPayloadAvroDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-Message"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}},name:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message",title:"Message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-AnotherTopic"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-ExamplePayloadDto-546532105"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto":{headers:{$ref:"#/components/schemas/SpringDefaultHeaderAndCloudEvent"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto",title:"NestedPayloadDto",bindings:{kafka:{key:{type:"string",description:"Kafka Producer Message Key",examples:["example-key"]},bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto",title:"RequiredAndNullablePayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}},contentType:"text/xml",name:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto",title:"XmlPayloadDto",description:"Showcases a xml based message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}},contentType:"application/yaml",name:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto",title:"YamlPayloadDto",description:"Showcases a yaml based message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-VehicleBase"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},name:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",title:"VehicleBase",bindings:{kafka:{bindingVersion:"0.5.0"}}},"java.lang.Integer":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-integer"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{type:"integer",format:"int32",examples:[0]}},name:"java.lang.Integer",title:"integer",bindings:{kafka:{bindingVersion:"0.5.0"}}},"java.lang.String":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-string"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{type:"string",examples:['"string"']}},name:"java.lang.String",title:"string",bindings:{kafka:{bindingVersion:"0.5.0"}}},"javax.money.MonetaryAmount":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-MonetaryAmount"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/javax.money.MonetaryAmount"}},name:"javax.money.MonetaryAmount",title:"MonetaryAmount",bindings:{kafka:{key:{type:"string",description:"Kafka Consumer Message Key",examples:["example-key"]},bindingVersion:"0.5.0"}}}}},operations:{"another-topic_receive_receiveAnotherPayloadBatched":{action:"receive",channel:{$ref:"#/channels/another-topic"},bindings:{kafka:{groupId:{type:"string",enum:["example-group-id"]},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}]},"another-topic_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-topic"},title:"another-topic_send",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}]},"avro-topic_receive_receiveExampleAvroPayload":{action:"receive",channel:{$ref:"#/channels/avro-topic"},title:"avro-topic_receive",description:"Requires a running kafka-schema-registry. See docker-compose.yml to start it",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/avro-topic/messages/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}]},"example-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/example-topic/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}]},"integer-topic_receive_receiveIntegerPayload":{action:"receive",channel:{$ref:"#/channels/integer-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/integer-topic/messages/java.lang.Integer"}]},"multi-payload-topic_receive_ExampleClassLevelKafkaListener":{action:"receive",channel:{$ref:"#/channels/multi-payload-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/multi-payload-topic/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},{$ref:"#/channels/multi-payload-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"},{$ref:"#/channels/multi-payload-topic/messages/javax.money.MonetaryAmount"}]},"multi-payload-topic_receive_receiveMonetaryAmount":{action:"receive",channel:{$ref:"#/channels/multi-payload-topic"},title:"multi-payload-topic_receive",description:"Override description in the AsyncListener annotation with servers at kafka:29092",bindings:{kafka:{groupId:{type:"string",enum:["foo-groupId"]},clientId:{type:"string",enum:["foo-clientId"]},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/multi-payload-topic/messages/javax.money.MonetaryAmount"}]},"no-payload-used-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/no-payload-used-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/no-payload-used-topic/messages/PayloadNotUsed"}]},"nullable-topic_receive_receiveNullablePayload":{action:"receive",channel:{$ref:"#/channels/nullable-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/nullable-topic/messages/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}]},"protobuf-topic_receive_receiveExampleProtobufPayload":{action:"receive",channel:{$ref:"#/channels/protobuf-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/protobuf-topic/messages/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}]},"string-topic_receive_receiveStringPayload":{action:"receive",channel:{$ref:"#/channels/string-topic"},title:"string-topic_receive",description:"Final classes (like String) can be documented using an envelope class and the @AsyncApiPayload annotation.",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/string-topic/messages/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"},{$ref:"#/channels/string-topic/messages/java.lang.String"}]},"topic-defined-via-asyncPublisher-annotation_send_sendMessage":{action:"send",channel:{$ref:"#/channels/topic-defined-via-asyncPublisher-annotation"},title:"topic-defined-via-asyncPublisher-annotation_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{kafka:{clientId:{type:"string",enum:["foo-clientId"]},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/topic-defined-via-asyncPublisher-annotation/messages/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}]},"vehicle-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/vehicle-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/vehicle-topic/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}]},"xml-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/xml-topic"},title:"xml-topic_receive",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/xml-topic/messages/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}]},"yaml-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/yaml-topic"},title:"yaml-topic_receive",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/yaml-topic/messages/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}]}}};var ah={asyncapi:"3.1.0",info:{title:"Springwolf example project - Kafka",version:"1.0.0",description:"This group only contains endpoints that are related to vehicles.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-apitype":"internal","x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"vehicle-topic":{address:"vehicle-topic",messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},bindings:{kafka:{bindingVersion:"0.5.0"}}}},components:{schemas:{"SpringKafkaDefaultHeaders-VehicleBase":{title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],examples:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower":{title:"EnginePower",type:"object",properties:{hp:{type:"integer",format:"int32"},torque:{type:"integer",format:"int32"}},examples:[{hp:0,torque:0}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{hp:{format:"int32",type:"integer"},torque:{}},title:"EnginePower",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{discriminator:"vehicleType",title:"VehicleBase",type:"object",properties:{enginePower:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower"},powerSource:{type:"string"},topSpeed:{type:"integer",format:"int32"},vehicleType:{type:"string"}},description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],oneOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto"},{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{allOf:[{},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto":{title:"VehicleElectricPayloadDto",type:"object",description:"Electric vehicle implementation of VehicleBase",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{batteryCapacity:{type:"integer",format:"int32"},chargeTime:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{},{allOf:[{},{properties:{fuelCapacity:{format:"int32",type:"integer"}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{batteryCapacity:{},chargeTime:{}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto":{title:"VehicleGasolinePayloadDto",type:"object",description:"Gasoline vehicle implementation of VehicleBase",examples:[{enginePower:{hp:0,torque:0},fuelCapacity:0,powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{fuelCapacity:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}}},messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-VehicleBase"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},name:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",title:"VehicleBase",bindings:{kafka:{bindingVersion:"0.5.0"}}}}},operations:{"vehicle-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/vehicle-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/vehicle-topic/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}]}}};var sh={initialConfig:{showBindings:!0,showHeaders:!0},groups:[{name:"Only Vehicles"}]};var lh={asyncapi:"3.1.0",info:{title:"Springwolf example project - JMS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-jms-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"jms-server":{host:"tcp://activemq:61616",protocol:"jms"}},channels:{"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}},bindings:{jms:{bindingVersion:"0.0.1"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}},bindings:{jms:{bindingVersion:"0.0.1"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{jms:{bindingVersion:"0.0.1"}}},"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{jms:{bindingVersion:"0.0.1"}}}}},operations:{"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{jms:{}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}]},"another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-queue"},title:"another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{jms:{"internal-field":"customValue",nested:{key:"nestedValue"}}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{jms:{}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}]}}};var ch={asyncapi:"3.1.0",info:{title:"Springwolf example project - SNS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-sns-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"sns-server":{host:"http://localhost:4566",protocol:"sns"}},channels:{"another-topic":{address:"another-topic",messages:{"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}},bindings:{}},"example-topic":{address:"example-topic",messages:{"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}},bindings:{}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"There can be headers, but they are not explicitly documented.",title:"HeadersNotDocumented",type:"object"}},"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Another payload model",properties:{example:{description:"Example payload model",properties:{someEnum:{description:"Some enum field",enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{description:"Some long field",format:"int64",minimum:0,type:"integer"},someString:{description:"Some string field",type:"string"}},required:["someEnum","someString"],title:"ExamplePayloadDto",type:"object"},foo:{description:"Foo field",maxLength:100,type:"string"}},required:["example"],title:"AnotherPayloadDto",type:"object"}},"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Example payload model",properties:{someEnum:{description:"Some enum field",enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{description:"Some long field",format:"int64",minimum:0,type:"integer"},someString:{description:"Some string field",type:"string"}},required:["someEnum","someString"],title:"ExamplePayloadDto",type:"object"}}},messages:{"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{sns:{}}},"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{sns:{}}}}},operations:{"another-topic_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-topic"},title:"another-topic_receive",description:"Auto-generated description",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}]},"another-topic_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-topic"},title:"another-topic_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}]},"example-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-topic"},title:"example-topic_receive",description:"Auto-generated description",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/example-topic/messages/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}]}}};var dh={asyncapi:"3.1.0",info:{title:"Springwolf example project - SQS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-sqs-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"sqs-server":{host:"http://localhost:4566",protocol:"sqs"}},channels:{"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}},bindings:{sqs:{queue:{name:"another-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600},bindingVersion:"0.2.0"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}},bindings:{sqs:{queue:{name:"example-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600},bindingVersion:"0.2.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{sqs:{}}},"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{sqs:{}}}}},operations:{"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{sqs:{queues:[{name:"another-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}]},"another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-queue"},title:"another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{sqs:{queues:[{name:"queue-name",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{sqs:{queues:[{name:"example-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}]}}};var mh={asyncapi:"3.1.0",info:{title:"Springwolf example project - STOMP",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-stomp-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{stomp:{host:"localhost:8080/myendpoint",protocol:"stomp"}},channels:{"_app_queue_another-queue":{address:"/app/queue/another-queue",messages:{"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}},bindings:{}},"_app_queue_example-queue":{address:"/app/queue/example-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_queue_sendto-queue":{address:"/app/queue/sendto-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_queue_sendtouser-queue":{address:"/app/queue/sendtouser-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_topic_sendto-response-queue":{address:"/app/topic/sendto-response-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_user_queue_sendtouser-response-queue":{address:"/user/queue/sendtouser-response-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},SpringStompDefaultHeaders:{title:"SpringStompDefaultHeaders",type:"object",properties:{},examples:[{}]},"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{stomp:{}}},"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringStompDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{stomp:{}}}}},operations:{"_app_queue_another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/_app_queue_another-queue"},title:"_app_queue_another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_another-queue/messages/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}]},"_app_queue_example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/_app_queue_example-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_example-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]},"_app_queue_sendto-queue_receive_receiveExamplePayloadSendTo":{action:"receive",channel:{$ref:"#/channels/_app_queue_sendto-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_sendto-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}],reply:{channel:{$ref:"#/channels/_app_topic_sendto-response-queue"},messages:[{$ref:"#/channels/_app_topic_sendto-response-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]}},"_app_queue_sendtouser-queue_receive_receiveExamplePayloadSendToUser":{action:"receive",channel:{$ref:"#/channels/_app_queue_sendtouser-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_sendtouser-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}],reply:{channel:{$ref:"#/channels/_user_queue_sendtouser-response-queue"},messages:[{$ref:"#/channels/_user_queue_sendtouser-response-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]}}}};var ss={amqp:{value:ih},"cloud-stream":{value:rh},jms:{value:lh},kafka:{value:oh,groups:{"Only Vehicles":ah},uiConfig:sh},sns:{value:ch},sqs:{value:dh},stomp:{value:mh}};var ls=class{mockData=this.selectMockData();createDb(){return{}}get(t){if(t.req.url.endsWith("/docs"))return t.utils.createResponse$(()=>({status:ce.OK,body:this.mockData.value}));if(t.req.url.indexOf("/docs/")!==-1){let e=t.req.url.split("/docs/")[1];return t.utils.createResponse$(()=>this.mockData.groups===void 0||this.mockData.groups[e]===void 0?{status:ce.NOT_FOUND,body:void 0}:{status:ce.OK,body:this.mockData.groups[e]})}else{if(t.req.url.endsWith("/publish"))return t.utils.createResponse$(()=>({status:ce.OK,body:{}}));if(t.req.url.endsWith("/ui-config"))return this.mockData.uiConfig?t.utils.createResponse$(()=>({status:ce.OK,body:this.mockData.uiConfig})):{status:ce.NOT_FOUND,body:void 0}}return t.utils.getPassThruBackend().handle(t.req)}post(t){return t.req.url.endsWith("/publish")?t.utils.createResponse$(()=>({status:ce.OK})):t.utils.getPassThruBackend().handle(t.req)}selectMockData(){let t=window.location.hostname,e=Object.keys(ss).filter(i=>t.includes(i));return 0<e.length?ss[e[0]]:ss.kafka}};function uh(n,t){let i=!t?.manualCleanup?t?.injector?.get(Yi)??m(Yi):null,r=Ny(t?.equal),o;t?.requireSync?o=X({kind:0},{equal:r}):o=X({kind:1,value:t?.initialValue},{equal:r});let a,s=n.subscribe({next:l=>o.set({kind:1,value:l}),error:l=>{o.set({kind:2,error:l}),a?.()},complete:()=>{a?.()}});if(t?.requireSync&&o().kind===0)throw new Fe(601,!1);return a=i?.onDestroy(s.unsubscribe.bind(s)),Jt(()=>{let l=o();switch(l.kind){case 1:return l.value;case 2:throw l.error;case 0:throw new Fe(601,!1)}},{equal:t?.equal})}function Ny(n=Object.is){return(t,e)=>t.kind===1&&e.kind===1&&n(t.value,e.value)}function Lc(){return{async:!1,breaks:!1,extensions:null,gfm:!0,hooks:null,pedantic:!1,renderer:null,silent:!1,tokenizer:null,walkTokens:null}}var wi=Lc();function vh(n){wi=n}var To={exec:()=>null};function be(n,t=""){let e=typeof n=="string"?n:n.source,i={replace:(r,o)=>{let a=typeof o=="string"?o:o.source;return a=a.replace(xt.caret,"$1"),e=e.replace(r,a),i},getRegex:()=>new RegExp(e,t)};return i}var Ly=(()=>{try{return!!new RegExp("(?<=1)(?<!1)")}catch{return!1}})(),xt={codeRemoveIndent:/^(?: {1,4}| {0,3}\t)/gm,outputLinkReplace:/\\([\[\]])/g,indentCodeCompensation:/^(\s+)(?:```)/,beginningSpace:/^\s+/,endingHash:/#$/,startingSpaceChar:/^ /,endingSpaceChar:/ $/,nonSpaceChar:/[^ ]/,newLineCharGlobal:/\n/g,tabCharGlobal:/\t/g,multipleSpaceGlobal:/\s+/g,blankLine:/^[ \t]*$/,doubleBlankLine:/\n[ \t]*\n[ \t]*$/,blockquoteStart:/^ {0,3}>/,blockquoteSetextReplace:/\n {0,3}((?:=+|-+) *)(?=\n|$)/g,blockquoteSetextReplace2:/^ {0,3}>[ \t]?/gm,listReplaceTabs:/^\t+/,listReplaceNesting:/^ {1,4}(?=( {4})*[^ ])/g,listIsTask:/^\[[ xX]\] /,listReplaceTask:/^\[[ xX]\] +/,anyLine:/\n.*\n/,hrefBrackets:/^<(.*)>$/,tableDelimiter:/[:|]/,tableAlignChars:/^\||\| *$/g,tableRowBlankLine:/\n[ \t]*$/,tableAlignRight:/^ *-+: *$/,tableAlignCenter:/^ *:-+: *$/,tableAlignLeft:/^ *:-+ *$/,startATag:/^<a /i,endATag:/^<\/a>/i,startPreScriptTag:/^<(pre|code|kbd|script)(\s|>)/i,endPreScriptTag:/^<\/(pre|code|kbd|script)(\s|>)/i,startAngleBracket:/^</,endAngleBracket:/>$/,pedanticHrefTitle:/^([^'"]*[^\s])\s+(['"])(.*)\2/,unicodeAlphaNumeric:/[\p{L}\p{N}]/u,escapeTest:/[&<>"']/,escapeReplace:/[&<>"']/g,escapeTestNoEncode:/[<>"']|&(?!(#\d{1,7}|#[Xx][a-fA-F0-9]{1,6}|\w+);)/,escapeReplaceNoEncode:/[<>"']|&(?!(#\d{1,7}|#[Xx][a-fA-F0-9]{1,6}|\w+);)/g,unescapeTest:/&(#(?:\d+)|(?:#x[0-9A-Fa-f]+)|(?:\w+));?/ig,caret:/(^|[^\[])\^/g,percentDecode:/%25/g,findPipe:/\|/g,splitPipe:/ \|/,slashPipe:/\\\|/g,carriageReturn:/\r\n|\r/g,spaceLine:/^ +$/gm,notSpaceStart:/^\S*/,endingNewline:/\n$/,listItemRegex:n=>new RegExp(`^( {0,3}${n})((?:[	 ][^\\n]*)?(?:\\n|$))`),nextBulletRegex:n=>new RegExp(`^ {0,${Math.min(3,n-1)}}(?:[*+-]|\\d{1,9}[.)])((?:[ 	][^\\n]*)?(?:\\n|$))`),hrRegex:n=>new RegExp(`^ {0,${Math.min(3,n-1)}}((?:- *){3,}|(?:_ *){3,}|(?:\\* *){3,})(?:\\n+|$)`),fencesBeginRegex:n=>new RegExp(`^ {0,${Math.min(3,n-1)}}(?:\`\`\`|~~~)`),headingBeginRegex:n=>new RegExp(`^ {0,${Math.min(3,n-1)}}#`),htmlBeginRegex:n=>new RegExp(`^ {0,${Math.min(3,n-1)}}<(?:[a-z].*>|!--)`,"i")},By=/^(?:[ \t]*(?:\n|$))+/,$y=/^((?: {4}| {0,3}\t)[^\n]+(?:\n(?:[ \t]*(?:\n|$))*)?)+/,jy=/^ {0,3}(`{3,}(?=[^`\n]*(?:\n|$))|~{3,})([^\n]*)(?:\n|$)(?:|([\s\S]*?)(?:\n|$))(?: {0,3}\1[~`]* *(?=\n|$)|$)/,Oo=/^ {0,3}((?:-[\t ]*){3,}|(?:_[ \t]*){3,}|(?:\*[ \t]*){3,})(?:\n+|$)/,Vy=/^ {0,3}(#{1,6})(?=\s|$)(.*)(?:\n+|$)/,Bc=/(?:[*+-]|\d{1,9}[.)])/,yh=/^(?!bull |blockCode|fences|blockquote|heading|html|table)((?:.|\n(?!\s*?\n|bull |blockCode|fences|blockquote|heading|html|table))+?)\n {0,3}(=+|-+) *(?:\n+|$)/,xh=be(yh).replace(/bull/g,Bc).replace(/blockCode/g,/(?: {4}| {0,3}\t)/).replace(/fences/g,/ {0,3}(?:`{3,}|~{3,})/).replace(/blockquote/g,/ {0,3}>/).replace(/heading/g,/ {0,3}#{1,6}/).replace(/html/g,/ {0,3}<[^\n>]+>\n/).replace(/\|table/g,"").getRegex(),zy=be(yh).replace(/bull/g,Bc).replace(/blockCode/g,/(?: {4}| {0,3}\t)/).replace(/fences/g,/ {0,3}(?:`{3,}|~{3,})/).replace(/blockquote/g,/ {0,3}>/).replace(/heading/g,/ {0,3}#{1,6}/).replace(/html/g,/ {0,3}<[^\n>]+>\n/).replace(/table/g,/ {0,3}\|?(?:[:\- ]*\|)+[\:\- ]*\n/).getRegex(),$c=/^([^\n]+(?:\n(?!hr|heading|lheading|blockquote|fences|list|html|table| +\n)[^\n]+)*)/,Hy=/^[^\n]+/,jc=/(?!\s*\])(?:\\[\s\S]|[^\[\]\\])+/,qy=be(/^ {0,3}\[(label)\]: *(?:\n[ \t]*)?([^<\s][^\s]*|<.*?>)(?:(?: +(?:\n[ \t]*)?| *\n[ \t]*)(title))? *(?:\n+|$)/).replace("label",jc).replace("title",/(?:"(?:\\"?|[^"\\])*"|'[^'\n]*(?:\n[^'\n]+)*\n?'|\([^()]*\))/).getRegex(),Uy=be(/^( {0,3}bull)([ \t][^\n]+?)?(?:\n|$)/).replace(/bull/g,Bc).getRegex(),us="address|article|aside|base|basefont|blockquote|body|caption|center|col|colgroup|dd|details|dialog|dir|div|dl|dt|fieldset|figcaption|figure|footer|form|frame|frameset|h[1-6]|head|header|hr|html|iframe|legend|li|link|main|menu|menuitem|meta|nav|noframes|ol|optgroup|option|p|param|search|section|summary|table|tbody|td|tfoot|th|thead|title|tr|track|ul",Vc=/<!--(?:-?>|[\s\S]*?(?:-->|$))/,Gy=be("^ {0,3}(?:<(script|pre|style|textarea)[\\s>][\\s\\S]*?(?:</\\1>[^\\n]*\\n+|$)|comment[^\\n]*(\\n+|$)|<\\?[\\s\\S]*?(?:\\?>\\n*|$)|<![A-Z][\\s\\S]*?(?:>\\n*|$)|<!\\[CDATA\\[[\\s\\S]*?(?:\\]\\]>\\n*|$)|</?(tag)(?: +|\\n|/?>)[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$)|<(?!script|pre|style|textarea)([a-z][\\w-]*)(?:attribute)*? */?>(?=[ \\t]*(?:\\n|$))[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$)|</(?!script|pre|style|textarea)[a-z][\\w-]*\\s*>(?=[ \\t]*(?:\\n|$))[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$))","i").replace("comment",Vc).replace("tag",us).replace("attribute",/ +[a-zA-Z:_][\w.:-]*(?: *= *"[^"\n]*"| *= *'[^'\n]*'| *= *[^\s"'=<>`]+)?/).getRegex(),wh=be($c).replace("hr",Oo).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("|lheading","").replace("|table","").replace("blockquote"," {0,3}>").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)]) ").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",us).getRegex(),Wy=be(/^( {0,3}> ?(paragraph|[^\n]*)(?:\n|$))+/).replace("paragraph",wh).getRegex(),zc={blockquote:Wy,code:$y,def:qy,fences:jy,heading:Vy,hr:Oo,html:Gy,lheading:xh,list:Uy,newline:By,paragraph:wh,table:To,text:Hy},ph=be("^ *([^\\n ].*)\\n {0,3}((?:\\| *)?:?-+:? *(?:\\| *:?-+:? *)*(?:\\| *)?)(?:\\n((?:(?! *\\n|hr|heading|blockquote|code|fences|list|html).*(?:\\n|$))*)\\n*|$)").replace("hr",Oo).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("blockquote"," {0,3}>").replace("code","(?: {4}| {0,3}	)[^\\n]").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)]) ").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",us).getRegex(),Ky=ye(S({},zc),{lheading:zy,table:ph,paragraph:be($c).replace("hr",Oo).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("|lheading","").replace("table",ph).replace("blockquote"," {0,3}>").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)]) ").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",us).getRegex()}),Yy=ye(S({},zc),{html:be(`^ *(?:comment *(?:\\n|\\s*$)|<(tag)[\\s\\S]+?</\\1> *(?:\\n{2,}|\\s*$)|<tag(?:"[^"]*"|'[^']*'|\\s[^'"/>\\s]*)*?/?> *(?:\\n{2,}|\\s*$))`).replace("comment",Vc).replace(/tag/g,"(?!(?:a|em|strong|small|s|cite|q|dfn|abbr|data|time|code|var|samp|kbd|sub|sup|i|b|u|mark|ruby|rt|rp|bdi|bdo|span|br|wbr|ins|del|img)\\b)\\w+(?!:|[^\\w\\s@]*@)\\b").getRegex(),def:/^ *\[([^\]]+)\]: *<?([^\s>]+)>?(?: +(["(][^\n]+[")]))? *(?:\n+|$)/,heading:/^(#{1,6})(.*)(?:\n+|$)/,fences:To,lheading:/^(.+?)\n {0,3}(=+|-+) *(?:\n+|$)/,paragraph:be($c).replace("hr",Oo).replace("heading",` *#{1,6} *[^
]`).replace("lheading",xh).replace("|table","").replace("blockquote"," {0,3}>").replace("|fences","").replace("|list","").replace("|html","").replace("|tag","").getRegex()}),Qy=/^\\([!"#$%&'()*+,\-./:;<=>?@\[\]\\^_`{|}~])/,Xy=/^(`+)([^`]|[^`][\s\S]*?[^`])\1(?!`)/,Ch=/^( {2,}|\\)\n(?!\s*$)/,Zy=/^(`+|[^`])(?:(?= {2,}\n)|[\s\S]*?(?:(?=[\\<!\[`*_]|\b_|$)|[^ ](?= {2,}\n)))/,ps=/[\p{P}\p{S}]/u,Hc=/[\s\p{P}\p{S}]/u,kh=/[^\s\p{P}\p{S}]/u,Jy=be(/^((?![*_])punctSpace)/,"u").replace(/punctSpace/g,Hc).getRegex(),Dh=/(?!~)[\p{P}\p{S}]/u,e0=/(?!~)[\s\p{P}\p{S}]/u,t0=/(?:[^\s\p{P}\p{S}]|~)/u,n0=be(/link|precode-code|html/,"g").replace("link",/\[(?:[^\[\]`]|(?<a>`+)[^`]+\k<a>(?!`))*?\]\((?:\\[\s\S]|[^\\\(\)]|\((?:\\[\s\S]|[^\\\(\)])*\))*\)/).replace("precode-",Ly?"(?<!`)()":"(^^|[^`])").replace("code",/(?<b>`+)[^`]+\k<b>(?!`)/).replace("html",/<(?! )[^<>]*?>/).getRegex(),Eh=/^(?:\*+(?:((?!\*)punct)|[^\s*]))|^_+(?:((?!_)punct)|([^\s_]))/,i0=be(Eh,"u").replace(/punct/g,ps).getRegex(),r0=be(Eh,"u").replace(/punct/g,Dh).getRegex(),Sh="^[^_*]*?__[^_*]*?\\*[^_*]*?(?=__)|[^*]+(?=[^*])|(?!\\*)punct(\\*+)(?=[\\s]|$)|notPunctSpace(\\*+)(?!\\*)(?=punctSpace|$)|(?!\\*)punctSpace(\\*+)(?=notPunctSpace)|[\\s](\\*+)(?!\\*)(?=punct)|(?!\\*)punct(\\*+)(?!\\*)(?=punct)|notPunctSpace(\\*+)(?=notPunctSpace)",o0=be(Sh,"gu").replace(/notPunctSpace/g,kh).replace(/punctSpace/g,Hc).replace(/punct/g,ps).getRegex(),a0=be(Sh,"gu").replace(/notPunctSpace/g,t0).replace(/punctSpace/g,e0).replace(/punct/g,Dh).getRegex(),s0=be("^[^_*]*?\\*\\*[^_*]*?_[^_*]*?(?=\\*\\*)|[^_]+(?=[^_])|(?!_)punct(_+)(?=[\\s]|$)|notPunctSpace(_+)(?!_)(?=punctSpace|$)|(?!_)punctSpace(_+)(?=notPunctSpace)|[\\s](_+)(?!_)(?=punct)|(?!_)punct(_+)(?!_)(?=punct)","gu").replace(/notPunctSpace/g,kh).replace(/punctSpace/g,Hc).replace(/punct/g,ps).getRegex(),l0=be(/\\(punct)/,"gu").replace(/punct/g,ps).getRegex(),c0=be(/^<(scheme:[^\s\x00-\x1f<>]*|email)>/).replace("scheme",/[a-zA-Z][a-zA-Z0-9+.-]{1,31}/).replace("email",/[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+(@)[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+(?![-_])/).getRegex(),d0=be(Vc).replace("(?:-->|$)","-->").getRegex(),m0=be("^comment|^</[a-zA-Z][\\w:-]*\\s*>|^<[a-zA-Z][\\w-]*(?:attribute)*?\\s*/?>|^<\\?[\\s\\S]*?\\?>|^<![a-zA-Z]+\\s[\\s\\S]*?>|^<!\\[CDATA\\[[\\s\\S]*?\\]\\]>").replace("comment",d0).replace("attribute",/\s+[a-zA-Z:_][\w.:-]*(?:\s*=\s*"[^"]*"|\s*=\s*'[^']*'|\s*=\s*[^\s"'=<>`]+)?/).getRegex(),ds=/(?:\[(?:\\[\s\S]|[^\[\]\\])*\]|\\[\s\S]|`+[^`]*?`+(?!`)|[^\[\]\\`])*?/,u0=be(/^!?\[(label)\]\(\s*(href)(?:(?:[ \t]*(?:\n[ \t]*)?)(title))?\s*\)/).replace("label",ds).replace("href",/<(?:\\.|[^\n<>\\])+>|[^ \t\n\x00-\x1f]*/).replace("title",/"(?:\\"?|[^"\\])*"|'(?:\\'?|[^'\\])*'|\((?:\\\)?|[^)\\])*\)/).getRegex(),Mh=be(/^!?\[(label)\]\[(ref)\]/).replace("label",ds).replace("ref",jc).getRegex(),Ah=be(/^!?\[(ref)\](?:\[\])?/).replace("ref",jc).getRegex(),p0=be("reflink|nolink(?!\\()","g").replace("reflink",Mh).replace("nolink",Ah).getRegex(),hh=/[hH][tT][tT][pP][sS]?|[fF][tT][pP]/,qc={_backpedal:To,anyPunctuation:l0,autolink:c0,blockSkip:n0,br:Ch,code:Xy,del:To,emStrongLDelim:i0,emStrongRDelimAst:o0,emStrongRDelimUnd:s0,escape:Qy,link:u0,nolink:Ah,punctuation:Jy,reflink:Mh,reflinkSearch:p0,tag:m0,text:Zy,url:To},h0=ye(S({},qc),{link:be(/^!?\[(label)\]\((.*?)\)/).replace("label",ds).getRegex(),reflink:be(/^!?\[(label)\]\s*\[([^\]]*)\]/).replace("label",ds).getRegex()}),Pc=ye(S({},qc),{emStrongRDelimAst:a0,emStrongLDelim:r0,url:be(/^((?:protocol):\/\/|www\.)(?:[a-zA-Z0-9\-]+\.?)+[^\s<]*|^email/).replace("protocol",hh).replace("email",/[A-Za-z0-9._+-]+(@)[a-zA-Z0-9-_]+(?:\.[a-zA-Z0-9-_]*[a-zA-Z0-9])+(?![-_])/).getRegex(),_backpedal:/(?:[^?!.,:;*_'"~()&]+|\([^)]*\)|&(?![a-zA-Z0-9]+;$)|[?!.,:;*_'"~)]+(?!$))+/,del:/^(~~?)(?=[^\s~])((?:\\[\s\S]|[^\\])*?(?:\\[\s\S]|[^\s~\\]))\1(?=[^~]|$)/,text:be(/^([`~]+|[^`~])(?:(?= {2,}\n)|(?=[a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-]+@)|[\s\S]*?(?:(?=[\\<!\[`*~_]|\b_|protocol:\/\/|www\.|$)|[^ ](?= {2,}\n)|[^a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-](?=[a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-]+@)))/).replace("protocol",hh).getRegex()}),f0=ye(S({},Pc),{br:be(Ch).replace("{2,}","*").getRegex(),text:be(Pc.text).replace("\\b_","\\b_| {2,}\\n").replace(/\{2,\}/g,"*").getRegex()}),cs={normal:zc,gfm:Ky,pedantic:Yy},Mo={normal:qc,gfm:Pc,breaks:f0,pedantic:h0},g0={"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"},fh=n=>g0[n];function gn(n,t){if(t){if(xt.escapeTest.test(n))return n.replace(xt.escapeReplace,fh)}else if(xt.escapeTestNoEncode.test(n))return n.replace(xt.escapeReplaceNoEncode,fh);return n}function gh(n){try{n=encodeURI(n).replace(xt.percentDecode,"%")}catch{return null}return n}function _h(n,t){let e=n.replace(xt.findPipe,(o,a,s)=>{let l=!1,d=a;for(;--d>=0&&s[d]==="\\";)l=!l;return l?"|":" |"}),i=e.split(xt.splitPipe),r=0;if(i[0].trim()||i.shift(),i.length>0&&!i.at(-1)?.trim()&&i.pop(),t)if(i.length>t)i.splice(t);else for(;i.length<t;)i.push("");for(;r<i.length;r++)i[r]=i[r].trim().replace(xt.slashPipe,"|");return i}function Ao(n,t,e){let i=n.length;if(i===0)return"";let r=0;for(;r<i;){let o=n.charAt(i-r-1);if(o===t&&!e)r++;else if(o!==t&&e)r++;else break}return n.slice(0,i-r)}function _0(n,t){if(n.indexOf(t[1])===-1)return-1;let e=0;for(let i=0;i<n.length;i++)if(n[i]==="\\")i++;else if(n[i]===t[0])e++;else if(n[i]===t[1]&&(e--,e<0))return i;return e>0?-2:-1}function bh(n,t,e,i,r){let o=t.href,a=t.title||null,s=n[1].replace(r.other.outputLinkReplace,"$1");i.state.inLink=!0;let l={type:n[0].charAt(0)==="!"?"image":"link",raw:e,href:o,title:a,text:s,tokens:i.inlineTokens(s)};return i.state.inLink=!1,l}function b0(n,t,e){let i=n.match(e.other.indentCodeCompensation);if(i===null)return t;let r=i[1];return t.split(`
`).map(o=>{let a=o.match(e.other.beginningSpace);if(a===null)return o;let[s]=a;return s.length>=r.length?o.slice(r.length):o}).join(`
`)}var ms=class{options;rules;lexer;constructor(n){this.options=n||wi}space(n){let t=this.rules.block.newline.exec(n);if(t&&t[0].length>0)return{type:"space",raw:t[0]}}code(n){let t=this.rules.block.code.exec(n);if(t){let e=t[0].replace(this.rules.other.codeRemoveIndent,"");return{type:"code",raw:t[0],codeBlockStyle:"indented",text:this.options.pedantic?e:Ao(e,`
`)}}}fences(n){let t=this.rules.block.fences.exec(n);if(t){let e=t[0],i=b0(e,t[3]||"",this.rules);return{type:"code",raw:e,lang:t[2]?t[2].trim().replace(this.rules.inline.anyPunctuation,"$1"):t[2],text:i}}}heading(n){let t=this.rules.block.heading.exec(n);if(t){let e=t[2].trim();if(this.rules.other.endingHash.test(e)){let i=Ao(e,"#");(this.options.pedantic||!i||this.rules.other.endingSpaceChar.test(i))&&(e=i.trim())}return{type:"heading",raw:t[0],depth:t[1].length,text:e,tokens:this.lexer.inline(e)}}}hr(n){let t=this.rules.block.hr.exec(n);if(t)return{type:"hr",raw:Ao(t[0],`
`)}}blockquote(n){let t=this.rules.block.blockquote.exec(n);if(t){let e=Ao(t[0],`
`).split(`
`),i="",r="",o=[];for(;e.length>0;){let a=!1,s=[],l;for(l=0;l<e.length;l++)if(this.rules.other.blockquoteStart.test(e[l]))s.push(e[l]),a=!0;else if(!a)s.push(e[l]);else break;e=e.slice(l);let d=s.join(`
`),c=d.replace(this.rules.other.blockquoteSetextReplace,`
    $1`).replace(this.rules.other.blockquoteSetextReplace2,"");i=i?`${i}
${d}`:d,r=r?`${r}
${c}`:c;let u=this.lexer.state.top;if(this.lexer.state.top=!0,this.lexer.blockTokens(c,o,!0),this.lexer.state.top=u,e.length===0)break;let h=o.at(-1);if(h?.type==="code")break;if(h?.type==="blockquote"){let y=h,_=y.raw+`
`+e.join(`
`),b=this.blockquote(_);o[o.length-1]=b,i=i.substring(0,i.length-y.raw.length)+b.raw,r=r.substring(0,r.length-y.text.length)+b.text;break}else if(h?.type==="list"){let y=h,_=y.raw+`
`+e.join(`
`),b=this.list(_);o[o.length-1]=b,i=i.substring(0,i.length-h.raw.length)+b.raw,r=r.substring(0,r.length-y.raw.length)+b.raw,e=_.substring(o.at(-1).raw.length).split(`
`);continue}}return{type:"blockquote",raw:i,tokens:o,text:r}}}list(n){let t=this.rules.block.list.exec(n);if(t){let e=t[1].trim(),i=e.length>1,r={type:"list",raw:"",ordered:i,start:i?+e.slice(0,-1):"",loose:!1,items:[]};e=i?`\\d{1,9}\\${e.slice(-1)}`:`\\${e}`,this.options.pedantic&&(e=i?e:"[*+-]");let o=this.rules.other.listItemRegex(e),a=!1;for(;n;){let l=!1,d="",c="";if(!(t=o.exec(n))||this.rules.block.hr.test(n))break;d=t[0],n=n.substring(d.length);let u=t[2].split(`
`,1)[0].replace(this.rules.other.listReplaceTabs,x=>" ".repeat(3*x.length)),h=n.split(`
`,1)[0],y=!u.trim(),_=0;if(this.options.pedantic?(_=2,c=u.trimStart()):y?_=t[1].length+1:(_=t[2].search(this.rules.other.nonSpaceChar),_=_>4?1:_,c=u.slice(_),_+=t[1].length),y&&this.rules.other.blankLine.test(h)&&(d+=h+`
`,n=n.substring(h.length+1),l=!0),!l){let x=this.rules.other.nextBulletRegex(_),A=this.rules.other.hrRegex(_),T=this.rules.other.fencesBeginRegex(_),L=this.rules.other.headingBeginRegex(_),N=this.rules.other.htmlBeginRegex(_);for(;n;){let ve=n.split(`
`,1)[0],ke;if(h=ve,this.options.pedantic?(h=h.replace(this.rules.other.listReplaceNesting,"  "),ke=h):ke=h.replace(this.rules.other.tabCharGlobal,"    "),T.test(h)||L.test(h)||N.test(h)||x.test(h)||A.test(h))break;if(ke.search(this.rules.other.nonSpaceChar)>=_||!h.trim())c+=`
`+ke.slice(_);else{if(y||u.replace(this.rules.other.tabCharGlobal,"    ").search(this.rules.other.nonSpaceChar)>=4||T.test(u)||L.test(u)||A.test(u))break;c+=`
`+h}!y&&!h.trim()&&(y=!0),d+=ve+`
`,n=n.substring(ve.length+1),u=ke.slice(_)}}r.loose||(a?r.loose=!0:this.rules.other.doubleBlankLine.test(d)&&(a=!0));let b=null,v;this.options.gfm&&(b=this.rules.other.listIsTask.exec(c),b&&(v=b[0]!=="[ ] ",c=c.replace(this.rules.other.listReplaceTask,""))),r.items.push({type:"list_item",raw:d,task:!!b,checked:v,loose:!1,text:c,tokens:[]}),r.raw+=d}let s=r.items.at(-1);if(s)s.raw=s.raw.trimEnd(),s.text=s.text.trimEnd();else return;r.raw=r.raw.trimEnd();for(let l=0;l<r.items.length;l++)if(this.lexer.state.top=!1,r.items[l].tokens=this.lexer.blockTokens(r.items[l].text,[]),!r.loose){let d=r.items[l].tokens.filter(u=>u.type==="space"),c=d.length>0&&d.some(u=>this.rules.other.anyLine.test(u.raw));r.loose=c}if(r.loose)for(let l=0;l<r.items.length;l++)r.items[l].loose=!0;return r}}html(n){let t=this.rules.block.html.exec(n);if(t)return{type:"html",block:!0,raw:t[0],pre:t[1]==="pre"||t[1]==="script"||t[1]==="style",text:t[0]}}def(n){let t=this.rules.block.def.exec(n);if(t){let e=t[1].toLowerCase().replace(this.rules.other.multipleSpaceGlobal," "),i=t[2]?t[2].replace(this.rules.other.hrefBrackets,"$1").replace(this.rules.inline.anyPunctuation,"$1"):"",r=t[3]?t[3].substring(1,t[3].length-1).replace(this.rules.inline.anyPunctuation,"$1"):t[3];return{type:"def",tag:e,raw:t[0],href:i,title:r}}}table(n){let t=this.rules.block.table.exec(n);if(!t||!this.rules.other.tableDelimiter.test(t[2]))return;let e=_h(t[1]),i=t[2].replace(this.rules.other.tableAlignChars,"").split("|"),r=t[3]?.trim()?t[3].replace(this.rules.other.tableRowBlankLine,"").split(`
`):[],o={type:"table",raw:t[0],header:[],align:[],rows:[]};if(e.length===i.length){for(let a of i)this.rules.other.tableAlignRight.test(a)?o.align.push("right"):this.rules.other.tableAlignCenter.test(a)?o.align.push("center"):this.rules.other.tableAlignLeft.test(a)?o.align.push("left"):o.align.push(null);for(let a=0;a<e.length;a++)o.header.push({text:e[a],tokens:this.lexer.inline(e[a]),header:!0,align:o.align[a]});for(let a of r)o.rows.push(_h(a,o.header.length).map((s,l)=>({text:s,tokens:this.lexer.inline(s),header:!1,align:o.align[l]})));return o}}lheading(n){let t=this.rules.block.lheading.exec(n);if(t)return{type:"heading",raw:t[0],depth:t[2].charAt(0)==="="?1:2,text:t[1],tokens:this.lexer.inline(t[1])}}paragraph(n){let t=this.rules.block.paragraph.exec(n);if(t){let e=t[1].charAt(t[1].length-1)===`
`?t[1].slice(0,-1):t[1];return{type:"paragraph",raw:t[0],text:e,tokens:this.lexer.inline(e)}}}text(n){let t=this.rules.block.text.exec(n);if(t)return{type:"text",raw:t[0],text:t[0],tokens:this.lexer.inline(t[0])}}escape(n){let t=this.rules.inline.escape.exec(n);if(t)return{type:"escape",raw:t[0],text:t[1]}}tag(n){let t=this.rules.inline.tag.exec(n);if(t)return!this.lexer.state.inLink&&this.rules.other.startATag.test(t[0])?this.lexer.state.inLink=!0:this.lexer.state.inLink&&this.rules.other.endATag.test(t[0])&&(this.lexer.state.inLink=!1),!this.lexer.state.inRawBlock&&this.rules.other.startPreScriptTag.test(t[0])?this.lexer.state.inRawBlock=!0:this.lexer.state.inRawBlock&&this.rules.other.endPreScriptTag.test(t[0])&&(this.lexer.state.inRawBlock=!1),{type:"html",raw:t[0],inLink:this.lexer.state.inLink,inRawBlock:this.lexer.state.inRawBlock,block:!1,text:t[0]}}link(n){let t=this.rules.inline.link.exec(n);if(t){let e=t[2].trim();if(!this.options.pedantic&&this.rules.other.startAngleBracket.test(e)){if(!this.rules.other.endAngleBracket.test(e))return;let o=Ao(e.slice(0,-1),"\\");if((e.length-o.length)%2===0)return}else{let o=_0(t[2],"()");if(o===-2)return;if(o>-1){let a=(t[0].indexOf("!")===0?5:4)+t[1].length+o;t[2]=t[2].substring(0,o),t[0]=t[0].substring(0,a).trim(),t[3]=""}}let i=t[2],r="";if(this.options.pedantic){let o=this.rules.other.pedanticHrefTitle.exec(i);o&&(i=o[1],r=o[3])}else r=t[3]?t[3].slice(1,-1):"";return i=i.trim(),this.rules.other.startAngleBracket.test(i)&&(this.options.pedantic&&!this.rules.other.endAngleBracket.test(e)?i=i.slice(1):i=i.slice(1,-1)),bh(t,{href:i&&i.replace(this.rules.inline.anyPunctuation,"$1"),title:r&&r.replace(this.rules.inline.anyPunctuation,"$1")},t[0],this.lexer,this.rules)}}reflink(n,t){let e;if((e=this.rules.inline.reflink.exec(n))||(e=this.rules.inline.nolink.exec(n))){let i=(e[2]||e[1]).replace(this.rules.other.multipleSpaceGlobal," "),r=t[i.toLowerCase()];if(!r){let o=e[0].charAt(0);return{type:"text",raw:o,text:o}}return bh(e,r,e[0],this.lexer,this.rules)}}emStrong(n,t,e=""){let i=this.rules.inline.emStrongLDelim.exec(n);if(!(!i||i[3]&&e.match(this.rules.other.unicodeAlphaNumeric))&&(!(i[1]||i[2])||!e||this.rules.inline.punctuation.exec(e))){let r=[...i[0]].length-1,o,a,s=r,l=0,d=i[0][0]==="*"?this.rules.inline.emStrongRDelimAst:this.rules.inline.emStrongRDelimUnd;for(d.lastIndex=0,t=t.slice(-1*n.length+r);(i=d.exec(t))!=null;){if(o=i[1]||i[2]||i[3]||i[4]||i[5]||i[6],!o)continue;if(a=[...o].length,i[3]||i[4]){s+=a;continue}else if((i[5]||i[6])&&r%3&&!((r+a)%3)){l+=a;continue}if(s-=a,s>0)continue;a=Math.min(a,a+s+l);let c=[...i[0]][0].length,u=n.slice(0,r+i.index+c+a);if(Math.min(r,a)%2){let y=u.slice(1,-1);return{type:"em",raw:u,text:y,tokens:this.lexer.inlineTokens(y)}}let h=u.slice(2,-2);return{type:"strong",raw:u,text:h,tokens:this.lexer.inlineTokens(h)}}}}codespan(n){let t=this.rules.inline.code.exec(n);if(t){let e=t[2].replace(this.rules.other.newLineCharGlobal," "),i=this.rules.other.nonSpaceChar.test(e),r=this.rules.other.startingSpaceChar.test(e)&&this.rules.other.endingSpaceChar.test(e);return i&&r&&(e=e.substring(1,e.length-1)),{type:"codespan",raw:t[0],text:e}}}br(n){let t=this.rules.inline.br.exec(n);if(t)return{type:"br",raw:t[0]}}del(n){let t=this.rules.inline.del.exec(n);if(t)return{type:"del",raw:t[0],text:t[2],tokens:this.lexer.inlineTokens(t[2])}}autolink(n){let t=this.rules.inline.autolink.exec(n);if(t){let e,i;return t[2]==="@"?(e=t[1],i="mailto:"+e):(e=t[1],i=e),{type:"link",raw:t[0],text:e,href:i,tokens:[{type:"text",raw:e,text:e}]}}}url(n){let t;if(t=this.rules.inline.url.exec(n)){let e,i;if(t[2]==="@")e=t[0],i="mailto:"+e;else{let r;do r=t[0],t[0]=this.rules.inline._backpedal.exec(t[0])?.[0]??"";while(r!==t[0]);e=t[0],t[1]==="www."?i="http://"+t[0]:i=t[0]}return{type:"link",raw:t[0],text:e,href:i,tokens:[{type:"text",raw:e,text:e}]}}}inlineText(n){let t=this.rules.inline.text.exec(n);if(t){let e=this.lexer.state.inRawBlock;return{type:"text",raw:t[0],text:t[0],escaped:e}}}},tn=class Fc{tokens;options;state;tokenizer;inlineQueue;constructor(t){this.tokens=[],this.tokens.links=Object.create(null),this.options=t||wi,this.options.tokenizer=this.options.tokenizer||new ms,this.tokenizer=this.options.tokenizer,this.tokenizer.options=this.options,this.tokenizer.lexer=this,this.inlineQueue=[],this.state={inLink:!1,inRawBlock:!1,top:!0};let e={other:xt,block:cs.normal,inline:Mo.normal};this.options.pedantic?(e.block=cs.pedantic,e.inline=Mo.pedantic):this.options.gfm&&(e.block=cs.gfm,this.options.breaks?e.inline=Mo.breaks:e.inline=Mo.gfm),this.tokenizer.rules=e}static get rules(){return{block:cs,inline:Mo}}static lex(t,e){return new Fc(e).lex(t)}static lexInline(t,e){return new Fc(e).inlineTokens(t)}lex(t){t=t.replace(xt.carriageReturn,`
`),this.blockTokens(t,this.tokens);for(let e=0;e<this.inlineQueue.length;e++){let i=this.inlineQueue[e];this.inlineTokens(i.src,i.tokens)}return this.inlineQueue=[],this.tokens}blockTokens(t,e=[],i=!1){for(this.options.pedantic&&(t=t.replace(xt.tabCharGlobal,"    ").replace(xt.spaceLine,""));t;){let r;if(this.options.extensions?.block?.some(a=>(r=a.call({lexer:this},t,e))?(t=t.substring(r.raw.length),e.push(r),!0):!1))continue;if(r=this.tokenizer.space(t)){t=t.substring(r.raw.length);let a=e.at(-1);r.raw.length===1&&a!==void 0?a.raw+=`
`:e.push(r);continue}if(r=this.tokenizer.code(t)){t=t.substring(r.raw.length);let a=e.at(-1);a?.type==="paragraph"||a?.type==="text"?(a.raw+=(a.raw.endsWith(`
`)?"":`
`)+r.raw,a.text+=`
`+r.text,this.inlineQueue.at(-1).src=a.text):e.push(r);continue}if(r=this.tokenizer.fences(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.heading(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.hr(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.blockquote(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.list(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.html(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.def(t)){t=t.substring(r.raw.length);let a=e.at(-1);a?.type==="paragraph"||a?.type==="text"?(a.raw+=(a.raw.endsWith(`
`)?"":`
`)+r.raw,a.text+=`
`+r.raw,this.inlineQueue.at(-1).src=a.text):this.tokens.links[r.tag]||(this.tokens.links[r.tag]={href:r.href,title:r.title},e.push(r));continue}if(r=this.tokenizer.table(t)){t=t.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.lheading(t)){t=t.substring(r.raw.length),e.push(r);continue}let o=t;if(this.options.extensions?.startBlock){let a=1/0,s=t.slice(1),l;this.options.extensions.startBlock.forEach(d=>{l=d.call({lexer:this},s),typeof l=="number"&&l>=0&&(a=Math.min(a,l))}),a<1/0&&a>=0&&(o=t.substring(0,a+1))}if(this.state.top&&(r=this.tokenizer.paragraph(o))){let a=e.at(-1);i&&a?.type==="paragraph"?(a.raw+=(a.raw.endsWith(`
`)?"":`
`)+r.raw,a.text+=`
`+r.text,this.inlineQueue.pop(),this.inlineQueue.at(-1).src=a.text):e.push(r),i=o.length!==t.length,t=t.substring(r.raw.length);continue}if(r=this.tokenizer.text(t)){t=t.substring(r.raw.length);let a=e.at(-1);a?.type==="text"?(a.raw+=(a.raw.endsWith(`
`)?"":`
`)+r.raw,a.text+=`
`+r.text,this.inlineQueue.pop(),this.inlineQueue.at(-1).src=a.text):e.push(r);continue}if(t){let a="Infinite loop on byte: "+t.charCodeAt(0);if(this.options.silent){console.error(a);break}else throw new Error(a)}}return this.state.top=!0,e}inline(t,e=[]){return this.inlineQueue.push({src:t,tokens:e}),e}inlineTokens(t,e=[]){let i=t,r=null;if(this.tokens.links){let l=Object.keys(this.tokens.links);if(l.length>0)for(;(r=this.tokenizer.rules.inline.reflinkSearch.exec(i))!=null;)l.includes(r[0].slice(r[0].lastIndexOf("[")+1,-1))&&(i=i.slice(0,r.index)+"["+"a".repeat(r[0].length-2)+"]"+i.slice(this.tokenizer.rules.inline.reflinkSearch.lastIndex))}for(;(r=this.tokenizer.rules.inline.anyPunctuation.exec(i))!=null;)i=i.slice(0,r.index)+"++"+i.slice(this.tokenizer.rules.inline.anyPunctuation.lastIndex);let o;for(;(r=this.tokenizer.rules.inline.blockSkip.exec(i))!=null;)o=r[2]?r[2].length:0,i=i.slice(0,r.index+o)+"["+"a".repeat(r[0].length-o-2)+"]"+i.slice(this.tokenizer.rules.inline.blockSkip.lastIndex);i=this.options.hooks?.emStrongMask?.call({lexer:this},i)??i;let a=!1,s="";for(;t;){a||(s=""),a=!1;let l;if(this.options.extensions?.inline?.some(c=>(l=c.call({lexer:this},t,e))?(t=t.substring(l.raw.length),e.push(l),!0):!1))continue;if(l=this.tokenizer.escape(t)){t=t.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.tag(t)){t=t.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.link(t)){t=t.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.reflink(t,this.tokens.links)){t=t.substring(l.raw.length);let c=e.at(-1);l.type==="text"&&c?.type==="text"?(c.raw+=l.raw,c.text+=l.text):e.push(l);continue}if(l=this.tokenizer.emStrong(t,i,s)){t=t.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.codespan(t)){t=t.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.br(t)){t=t.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.del(t)){t=t.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.autolink(t)){t=t.substring(l.raw.length),e.push(l);continue}if(!this.state.inLink&&(l=this.tokenizer.url(t))){t=t.substring(l.raw.length),e.push(l);continue}let d=t;if(this.options.extensions?.startInline){let c=1/0,u=t.slice(1),h;this.options.extensions.startInline.forEach(y=>{h=y.call({lexer:this},u),typeof h=="number"&&h>=0&&(c=Math.min(c,h))}),c<1/0&&c>=0&&(d=t.substring(0,c+1))}if(l=this.tokenizer.inlineText(d)){t=t.substring(l.raw.length),l.raw.slice(-1)!=="_"&&(s=l.raw.slice(-1)),a=!0;let c=e.at(-1);c?.type==="text"?(c.raw+=l.raw,c.text+=l.text):e.push(l);continue}if(t){let c="Infinite loop on byte: "+t.charCodeAt(0);if(this.options.silent){console.error(c);break}else throw new Error(c)}}return e}},ri=class{options;parser;constructor(n){this.options=n||wi}space(n){return""}code({text:n,lang:t,escaped:e}){let i=(t||"").match(xt.notSpaceStart)?.[0],r=n.replace(xt.endingNewline,"")+`
`;return i?'<pre><code class="language-'+gn(i)+'">'+(e?r:gn(r,!0))+`</code></pre>
`:"<pre><code>"+(e?r:gn(r,!0))+`</code></pre>
`}blockquote({tokens:n}){return`<blockquote>
${this.parser.parse(n)}</blockquote>
`}html({text:n}){return n}def(n){return""}heading({tokens:n,depth:t}){return`<h${t}>${this.parser.parseInline(n)}</h${t}>
`}hr(n){return`<hr>
`}list(n){let t=n.ordered,e=n.start,i="";for(let a=0;a<n.items.length;a++){let s=n.items[a];i+=this.listitem(s)}let r=t?"ol":"ul",o=t&&e!==1?' start="'+e+'"':"";return"<"+r+o+`>
`+i+"</"+r+`>
`}listitem(n){let t="";if(n.task){let e=this.checkbox({checked:!!n.checked});n.loose?n.tokens[0]?.type==="paragraph"?(n.tokens[0].text=e+" "+n.tokens[0].text,n.tokens[0].tokens&&n.tokens[0].tokens.length>0&&n.tokens[0].tokens[0].type==="text"&&(n.tokens[0].tokens[0].text=e+" "+gn(n.tokens[0].tokens[0].text),n.tokens[0].tokens[0].escaped=!0)):n.tokens.unshift({type:"text",raw:e+" ",text:e+" ",escaped:!0}):t+=e+" "}return t+=this.parser.parse(n.tokens,!!n.loose),`<li>${t}</li>
`}checkbox({checked:n}){return"<input "+(n?'checked="" ':"")+'disabled="" type="checkbox">'}paragraph({tokens:n}){return`<p>${this.parser.parseInline(n)}</p>
`}table(n){let t="",e="";for(let r=0;r<n.header.length;r++)e+=this.tablecell(n.header[r]);t+=this.tablerow({text:e});let i="";for(let r=0;r<n.rows.length;r++){let o=n.rows[r];e="";for(let a=0;a<o.length;a++)e+=this.tablecell(o[a]);i+=this.tablerow({text:e})}return i&&(i=`<tbody>${i}</tbody>`),`<table>
<thead>
`+t+`</thead>
`+i+`</table>
`}tablerow({text:n}){return`<tr>
${n}</tr>
`}tablecell(n){let t=this.parser.parseInline(n.tokens),e=n.header?"th":"td";return(n.align?`<${e} align="${n.align}">`:`<${e}>`)+t+`</${e}>
`}strong({tokens:n}){return`<strong>${this.parser.parseInline(n)}</strong>`}em({tokens:n}){return`<em>${this.parser.parseInline(n)}</em>`}codespan({text:n}){return`<code>${gn(n,!0)}</code>`}br(n){return"<br>"}del({tokens:n}){return`<del>${this.parser.parseInline(n)}</del>`}link({href:n,title:t,tokens:e}){let i=this.parser.parseInline(e),r=gh(n);if(r===null)return i;n=r;let o='<a href="'+n+'"';return t&&(o+=' title="'+gn(t)+'"'),o+=">"+i+"</a>",o}image({href:n,title:t,text:e,tokens:i}){i&&(e=this.parser.parseInline(i,this.parser.textRenderer));let r=gh(n);if(r===null)return gn(e);n=r;let o=`<img src="${n}" alt="${e}"`;return t&&(o+=` title="${gn(t)}"`),o+=">",o}text(n){return"tokens"in n&&n.tokens?this.parser.parseInline(n.tokens):"escaped"in n&&n.escaped?n.text:gn(n.text)}},Uc=class{strong({text:n}){return n}em({text:n}){return n}codespan({text:n}){return n}del({text:n}){return n}html({text:n}){return n}text({text:n}){return n}link({text:n}){return""+n}image({text:n}){return""+n}br(){return""}},nn=class Nc{options;renderer;textRenderer;constructor(t){this.options=t||wi,this.options.renderer=this.options.renderer||new ri,this.renderer=this.options.renderer,this.renderer.options=this.options,this.renderer.parser=this,this.textRenderer=new Uc}static parse(t,e){return new Nc(e).parse(t)}static parseInline(t,e){return new Nc(e).parseInline(t)}parse(t,e=!0){let i="";for(let r=0;r<t.length;r++){let o=t[r];if(this.options.extensions?.renderers?.[o.type]){let s=o,l=this.options.extensions.renderers[s.type].call({parser:this},s);if(l!==!1||!["space","hr","heading","code","table","blockquote","list","html","def","paragraph","text"].includes(s.type)){i+=l||"";continue}}let a=o;switch(a.type){case"space":{i+=this.renderer.space(a);continue}case"hr":{i+=this.renderer.hr(a);continue}case"heading":{i+=this.renderer.heading(a);continue}case"code":{i+=this.renderer.code(a);continue}case"table":{i+=this.renderer.table(a);continue}case"blockquote":{i+=this.renderer.blockquote(a);continue}case"list":{i+=this.renderer.list(a);continue}case"html":{i+=this.renderer.html(a);continue}case"def":{i+=this.renderer.def(a);continue}case"paragraph":{i+=this.renderer.paragraph(a);continue}case"text":{let s=a,l=this.renderer.text(s);for(;r+1<t.length&&t[r+1].type==="text";)s=t[++r],l+=`
`+this.renderer.text(s);e?i+=this.renderer.paragraph({type:"paragraph",raw:l,text:l,tokens:[{type:"text",raw:l,text:l,escaped:!0}]}):i+=l;continue}default:{let s='Token with "'+a.type+'" type was not found.';if(this.options.silent)return console.error(s),"";throw new Error(s)}}}return i}parseInline(t,e=this.renderer){let i="";for(let r=0;r<t.length;r++){let o=t[r];if(this.options.extensions?.renderers?.[o.type]){let s=this.options.extensions.renderers[o.type].call({parser:this},o);if(s!==!1||!["escape","html","link","image","strong","em","codespan","br","del","text"].includes(o.type)){i+=s||"";continue}}let a=o;switch(a.type){case"escape":{i+=e.text(a);break}case"html":{i+=e.html(a);break}case"link":{i+=e.link(a);break}case"image":{i+=e.image(a);break}case"strong":{i+=e.strong(a);break}case"em":{i+=e.em(a);break}case"codespan":{i+=e.codespan(a);break}case"br":{i+=e.br(a);break}case"del":{i+=e.del(a);break}case"text":{i+=e.text(a);break}default:{let s='Token with "'+a.type+'" type was not found.';if(this.options.silent)return console.error(s),"";throw new Error(s)}}}return i}},Io=class{options;block;constructor(n){this.options=n||wi}static passThroughHooks=new Set(["preprocess","postprocess","processAllTokens","emStrongMask"]);static passThroughHooksRespectAsync=new Set(["preprocess","postprocess","processAllTokens"]);preprocess(n){return n}postprocess(n){return n}processAllTokens(n){return n}emStrongMask(n){return n}provideLexer(){return this.block?tn.lex:tn.lexInline}provideParser(){return this.block?nn.parse:nn.parseInline}},v0=class{defaults=Lc();options=this.setOptions;parse=this.parseMarkdown(!0);parseInline=this.parseMarkdown(!1);Parser=nn;Renderer=ri;TextRenderer=Uc;Lexer=tn;Tokenizer=ms;Hooks=Io;constructor(...n){this.use(...n)}walkTokens(n,t){let e=[];for(let i of n)switch(e=e.concat(t.call(this,i)),i.type){case"table":{let r=i;for(let o of r.header)e=e.concat(this.walkTokens(o.tokens,t));for(let o of r.rows)for(let a of o)e=e.concat(this.walkTokens(a.tokens,t));break}case"list":{let r=i;e=e.concat(this.walkTokens(r.items,t));break}default:{let r=i;this.defaults.extensions?.childTokens?.[r.type]?this.defaults.extensions.childTokens[r.type].forEach(o=>{let a=r[o].flat(1/0);e=e.concat(this.walkTokens(a,t))}):r.tokens&&(e=e.concat(this.walkTokens(r.tokens,t)))}}return e}use(...n){let t=this.defaults.extensions||{renderers:{},childTokens:{}};return n.forEach(e=>{let i=S({},e);if(i.async=this.defaults.async||i.async||!1,e.extensions&&(e.extensions.forEach(r=>{if(!r.name)throw new Error("extension name required");if("renderer"in r){let o=t.renderers[r.name];o?t.renderers[r.name]=function(...a){let s=r.renderer.apply(this,a);return s===!1&&(s=o.apply(this,a)),s}:t.renderers[r.name]=r.renderer}if("tokenizer"in r){if(!r.level||r.level!=="block"&&r.level!=="inline")throw new Error("extension level must be 'block' or 'inline'");let o=t[r.level];o?o.unshift(r.tokenizer):t[r.level]=[r.tokenizer],r.start&&(r.level==="block"?t.startBlock?t.startBlock.push(r.start):t.startBlock=[r.start]:r.level==="inline"&&(t.startInline?t.startInline.push(r.start):t.startInline=[r.start]))}"childTokens"in r&&r.childTokens&&(t.childTokens[r.name]=r.childTokens)}),i.extensions=t),e.renderer){let r=this.defaults.renderer||new ri(this.defaults);for(let o in e.renderer){if(!(o in r))throw new Error(`renderer '${o}' does not exist`);if(["options","parser"].includes(o))continue;let a=o,s=e.renderer[a],l=r[a];r[a]=(...d)=>{let c=s.apply(r,d);return c===!1&&(c=l.apply(r,d)),c||""}}i.renderer=r}if(e.tokenizer){let r=this.defaults.tokenizer||new ms(this.defaults);for(let o in e.tokenizer){if(!(o in r))throw new Error(`tokenizer '${o}' does not exist`);if(["options","rules","lexer"].includes(o))continue;let a=o,s=e.tokenizer[a],l=r[a];r[a]=(...d)=>{let c=s.apply(r,d);return c===!1&&(c=l.apply(r,d)),c}}i.tokenizer=r}if(e.hooks){let r=this.defaults.hooks||new Io;for(let o in e.hooks){if(!(o in r))throw new Error(`hook '${o}' does not exist`);if(["options","block"].includes(o))continue;let a=o,s=e.hooks[a],l=r[a];Io.passThroughHooks.has(o)?r[a]=d=>{if(this.defaults.async&&Io.passThroughHooksRespectAsync.has(o))return(async()=>{let u=await s.call(r,d);return l.call(r,u)})();let c=s.call(r,d);return l.call(r,c)}:r[a]=(...d)=>{if(this.defaults.async)return(async()=>{let u=await s.apply(r,d);return u===!1&&(u=await l.apply(r,d)),u})();let c=s.apply(r,d);return c===!1&&(c=l.apply(r,d)),c}}i.hooks=r}if(e.walkTokens){let r=this.defaults.walkTokens,o=e.walkTokens;i.walkTokens=function(a){let s=[];return s.push(o.call(this,a)),r&&(s=s.concat(r.call(this,a))),s}}this.defaults=S(S({},this.defaults),i)}),this}setOptions(n){return this.defaults=S(S({},this.defaults),n),this}lexer(n,t){return tn.lex(n,t??this.defaults)}parser(n,t){return nn.parse(n,t??this.defaults)}parseMarkdown(n){return(t,e)=>{let i=S({},e),r=S(S({},this.defaults),i),o=this.onError(!!r.silent,!!r.async);if(this.defaults.async===!0&&i.async===!1)return o(new Error("marked(): The async option was set to true by an extension. Remove async: false from the parse options object to return a Promise."));if(typeof t>"u"||t===null)return o(new Error("marked(): input parameter is undefined or null"));if(typeof t!="string")return o(new Error("marked(): input parameter is of type "+Object.prototype.toString.call(t)+", string expected"));if(r.hooks&&(r.hooks.options=r,r.hooks.block=n),r.async)return(async()=>{let a=r.hooks?await r.hooks.preprocess(t):t,s=await(r.hooks?await r.hooks.provideLexer():n?tn.lex:tn.lexInline)(a,r),l=r.hooks?await r.hooks.processAllTokens(s):s;r.walkTokens&&await Promise.all(this.walkTokens(l,r.walkTokens));let d=await(r.hooks?await r.hooks.provideParser():n?nn.parse:nn.parseInline)(l,r);return r.hooks?await r.hooks.postprocess(d):d})().catch(o);try{r.hooks&&(t=r.hooks.preprocess(t));let a=(r.hooks?r.hooks.provideLexer():n?tn.lex:tn.lexInline)(t,r);r.hooks&&(a=r.hooks.processAllTokens(a)),r.walkTokens&&this.walkTokens(a,r.walkTokens);let s=(r.hooks?r.hooks.provideParser():n?nn.parse:nn.parseInline)(a,r);return r.hooks&&(s=r.hooks.postprocess(s)),s}catch(a){return o(a)}}}onError(n,t){return e=>{if(e.message+=`
Please report this to https://github.com/markedjs/marked.`,n){let i="<p>An error occurred:</p><pre>"+gn(e.message+"",!0)+"</pre>";return t?Promise.resolve(i):i}if(t)return Promise.reject(e);throw e}}},xi=new v0;function ge(n,t){return xi.parse(n,t)}ge.options=ge.setOptions=function(n){return xi.setOptions(n),ge.defaults=xi.defaults,vh(ge.defaults),ge};ge.getDefaults=Lc;ge.defaults=wi;ge.use=function(...n){return xi.use(...n),ge.defaults=xi.defaults,vh(ge.defaults),ge};ge.walkTokens=function(n,t){return xi.walkTokens(n,t)};ge.parseInline=xi.parseInline;ge.Parser=nn;ge.parser=nn.parse;ge.Renderer=ri;ge.TextRenderer=Uc;ge.Lexer=tn;ge.lexer=tn.lex;ge.Tokenizer=ms;ge.Hooks=Io;ge.parse=ge;var $R=ge.options,jR=ge.setOptions,VR=ge.use,zR=ge.walkTokens,HR=ge.parseInline;var qR=nn.parse,UR=tn.lex;var y0=["*"],x0="Copy",w0="Copied",C0=(()=>{class n{constructor(){this._buttonClick$=new z,this.copied=uh(this._buttonClick$.pipe(jt(()=>_t(Pe(!0),Ju(3e3).pipe(to(!1)))),no(),Mn(1))),this.copiedText=Jt(()=>this.copied()?w0:x0)}onCopyToClipboardClick(){this._buttonClick$.next()}static{this.\u0275fac=function(i){return new(i||n)}}static{this.\u0275cmp=I({type:n,selectors:[["markdown-clipboard"]],decls:2,vars:3,consts:[[1,"markdown-clipboard-button",3,"click"]],template:function(i,r){i&1&&(qe(0,"button",0),Va("click",function(){return r.onCopyToClipboardClick()}),w(1),Qe()),i&2&&(Q("copied",r.copied()),p(),Le(r.copiedText()))},encapsulation:2,changeDetection:0})}}return n})(),k0=new M("CLIPBOARD_OPTIONS");var D0=new M("MARKED_EXTENSIONS"),E0=new M("MARKED_OPTIONS"),S0=new M("MERMAID_OPTIONS"),M0=new M("SANITIZE");function A0(n){return typeof n=="function"}var I0="[ngx-markdown] When using the `emoji` attribute you *have to* include Emoji-Toolkit files to `angular.json` or use imports. See README for more information",T0="[ngx-markdown] When using the `katex` attribute you *have to* include KaTeX files to `angular.json` or use imports. See README for more information",O0="[ngx-markdown] When using the `mermaid` attribute you *have to* include Mermaid files to `angular.json` or use imports. See README for more information",R0="[ngx-markdown] When using the `clipboard` attribute you *have to* include Clipboard files to `angular.json` or use imports. See README for more information",P0="[ngx-markdown] When using the `clipboard` attribute you *have to* provide the `viewContainerRef` parameter to `MarkdownService.render()` function",F0="[ngx-markdown] When using the `src` attribute you *have to* pass the `HttpClient` as a parameter of the `forRoot` method. See README for more information";var Ih=(()=>{class n{get options(){return this._options}set options(e){this._options=S(S({},this.DEFAULT_MARKED_OPTIONS),e)}get renderer(){return this.options.renderer}set renderer(e){this.options.renderer=e}constructor(){this.clipboardOptions=m(k0,{optional:!0}),this.extensions=m(D0,{optional:!0}),this.http=m(Ht,{optional:!0}),this.mermaidOptions=m(S0,{optional:!0}),this.platform=m(Zn),this.sanitize=m(M0,{optional:!0}),this.sanitizer=m(Rn),this.DEFAULT_MARKED_OPTIONS={renderer:new ri},this.DEFAULT_KATEX_OPTIONS={delimiters:[{left:"$$",right:"$$",display:!0},{left:"$",right:"$",display:!1},{left:"\\(",right:"\\)",display:!1},{left:"\\begin{equation}",right:"\\end{equation}",display:!0},{left:"\\begin{align}",right:"\\end{align}",display:!0},{left:"\\begin{alignat}",right:"\\end{alignat}",display:!0},{left:"\\begin{gather}",right:"\\end{gather}",display:!0},{left:"\\begin{CD}",right:"\\end{CD}",display:!0},{left:"\\[",right:"\\]",display:!0}]},this.DEFAULT_MERMAID_OPTIONS={startOnLoad:!1},this.DEFAULT_CLIPBOARD_OPTIONS={buttonComponent:void 0},this.DEFAULT_PARSE_OPTIONS={decodeHtml:!1,inline:!1,emoji:!1,mermaid:!1,markedOptions:void 0,disableSanitizer:!1},this.DEFAULT_RENDER_OPTIONS={clipboard:!1,clipboardOptions:void 0,katex:!1,katexOptions:void 0,mermaid:!1,mermaidOptions:void 0},this.DEFAULT_SECURITY_CONTEXT=tt.HTML,this._options=null,this._reload$=new z,this.reload$=this._reload$.asObservable(),this.options=m(E0,{optional:!0})}parse(e,i=this.DEFAULT_PARSE_OPTIONS){let{decodeHtml:r,inline:o,emoji:a,mermaid:s,disableSanitizer:l}=i,d=S(S({},this.options),i.markedOptions),c=d.renderer||this.renderer||new ri;this.extensions&&(this.renderer=this.extendsRendererForExtensions(c)),s&&(this.renderer=this.extendsRendererForMermaid(c));let u=this.trimIndentation(e),h=r?this.decodeHtml(u):u,y=a?this.parseEmoji(h):h,_=this.parseMarked(y,d,o);return l?_:this.sanitizeHtml(_)}render(e,i=this.DEFAULT_RENDER_OPTIONS,r){let{clipboard:o,clipboardOptions:a,katex:s,katexOptions:l,mermaid:d,mermaidOptions:c}=i;s&&this.renderKatex(e,S(S({},this.DEFAULT_KATEX_OPTIONS),l)),d&&this.renderMermaid(e,S(S(S({},this.DEFAULT_MERMAID_OPTIONS),this.mermaidOptions),c)),o&&this.renderClipboard(e,r,S(S(S({},this.DEFAULT_CLIPBOARD_OPTIONS),this.clipboardOptions),a)),this.highlight(e)}reload(){this._reload$.next()}getSource(e){if(!this.http)throw new Error(F0);return this.http.get(e,{responseType:"text"}).pipe(xe(i=>this.handleExtension(e,i)))}highlight(e){if(!On(this.platform)||typeof Prism>"u"||typeof Prism.highlightAllUnder>"u")return;e||(e=document);let i=e.querySelectorAll('pre code:not([class*="language-"])');Array.prototype.forEach.call(i,r=>r.classList.add("language-none")),Prism.highlightAllUnder(e)}decodeHtml(e){if(!On(this.platform))return e;let i=document.createElement("textarea");return i.innerHTML=e,i.value}extendsRendererForExtensions(e){let i=e;return i.\u0275NgxMarkdownRendererExtendedForExtensions===!0||(this.extensions&&this.extensions.length>0&&ge.use(...this.extensions),i.\u0275NgxMarkdownRendererExtendedForExtensions=!0),e}extendsRendererForMermaid(e){let i=e;if(i.\u0275NgxMarkdownRendererExtendedForMermaid===!0)return e;let r=e.code;return e.code=o=>o.lang==="mermaid"?`<div class="mermaid">${o.text}</div>`:r(o),i.\u0275NgxMarkdownRendererExtendedForMermaid=!0,e}handleExtension(e,i){let r=e.lastIndexOf("://"),o=r>-1?e.substring(r+4):e,a=o.lastIndexOf("/"),s=a>-1?o.substring(a+1).split("?")[0]:"",l=s.lastIndexOf("."),d=l>-1?s.substring(l+1):"";return d&&d!=="md"?"```"+d+`
`+i+"\n```":i}parseMarked(e,i,r=!1){if(i.renderer){let o=S({},i.renderer);delete o.\u0275NgxMarkdownRendererExtendedForExtensions,delete o.\u0275NgxMarkdownRendererExtendedForMermaid,delete i.renderer,ge.use({renderer:o})}return r?ge.parseInline(e,i):ge.parse(e,i)}parseEmoji(e){if(!On(this.platform))return e;if(typeof joypixels>"u"||typeof joypixels.shortnameToUnicode>"u")throw new Error(I0);return joypixels.shortnameToUnicode(e)}renderKatex(e,i){if(On(this.platform)){if(typeof katex>"u"||typeof renderMathInElement>"u")throw new Error(T0);renderMathInElement(e,i)}}renderClipboard(e,i,r){if(!On(this.platform))return;if(typeof ClipboardJS>"u")throw new Error(R0);if(!i)throw new Error(P0);let{buttonComponent:o,buttonTemplate:a}=r,s=e.querySelectorAll("pre");for(let l=0;l<s.length;l++){let d=s.item(l),c=document.createElement("div");c.style.position="relative",d.parentNode.insertBefore(c,d),c.appendChild(d);let u=document.createElement("div");u.classList.add("markdown-clipboard-toolbar"),u.style.position="absolute",u.style.top=".5em",u.style.right=".5em",u.style.zIndex="1",c.insertAdjacentElement("beforeend",u),c.onmouseenter=()=>u.classList.add("hover"),c.onmouseleave=()=>u.classList.remove("hover");let h;if(o){let _=i.createComponent(o);h=_.hostView,_.changeDetectorRef.markForCheck()}else if(a)h=i.createEmbeddedView(a);else{let _=i.createComponent(C0);h=_.hostView,_.changeDetectorRef.markForCheck()}let y;h.rootNodes.forEach(_=>{u.appendChild(_),y=new ClipboardJS(_,{text:()=>d.innerText})}),h.onDestroy(()=>y.destroy())}}renderMermaid(e,i=this.DEFAULT_MERMAID_OPTIONS){if(!On(this.platform))return;if(typeof mermaid>"u"||typeof mermaid.initialize>"u")throw new Error(O0);let r=e.querySelectorAll(".mermaid");r.length!==0&&(mermaid.initialize(i),mermaid.run({nodes:r}))}trimIndentation(e){if(!e)return"";let i;return e.split(`
`).map(r=>{let o=i;return r.length>0&&(o=isNaN(o)?r.search(/\S|$/):Math.min(r.search(/\S|$/),o)),isNaN(i)&&(i=o),o?r.substring(o):r}).join(`
`)}async sanitizeHtml(e){return A0(this.sanitize)?this.sanitize(await e):this.sanitize!==tt.NONE?this.sanitizer.sanitize(this.sanitize??this.DEFAULT_SECURITY_CONTEXT,e)??"":e}static{this.\u0275fac=function(i){return new(i||n)}}static{this.\u0275prov=E({token:n,factory:n.\u0275fac})}}return n})(),Gc=(function(n){return n.CommandLine="command-line",n.LineHighlight="line-highlight",n.LineNumbers="line-numbers",n})(Gc||{}),_n=(()=>{class n{constructor(){this.element=m(Y),this.markdownService=m(Ih),this.viewContainerRef=m(pn),this.error=new Ee,this.load=new Ee,this.ready=new Ee,this._clipboard=!1,this._commandLine=!1,this._disableSanitizer=!1,this._emoji=!1,this._inline=!1,this._katex=!1,this._lineHighlight=!1,this._lineNumbers=!1,this._mermaid=!1,this.destroyed$=new z}get disableSanitizer(){return this._disableSanitizer}set disableSanitizer(e){this._disableSanitizer=this.coerceBooleanProperty(e)}get inline(){return this._inline}set inline(e){this._inline=this.coerceBooleanProperty(e)}get clipboard(){return this._clipboard}set clipboard(e){this._clipboard=this.coerceBooleanProperty(e)}get emoji(){return this._emoji}set emoji(e){this._emoji=this.coerceBooleanProperty(e)}get katex(){return this._katex}set katex(e){this._katex=this.coerceBooleanProperty(e)}get mermaid(){return this._mermaid}set mermaid(e){this._mermaid=this.coerceBooleanProperty(e)}get lineHighlight(){return this._lineHighlight}set lineHighlight(e){this._lineHighlight=this.coerceBooleanProperty(e)}get lineNumbers(){return this._lineNumbers}set lineNumbers(e){this._lineNumbers=this.coerceBooleanProperty(e)}get commandLine(){return this._commandLine}set commandLine(e){this._commandLine=this.coerceBooleanProperty(e)}ngOnChanges(){this.loadContent()}loadContent(){if(this.data!=null){this.handleData();return}if(this.src!=null){this.handleSrc();return}}ngAfterViewInit(){!this.data&&!this.src&&this.handleTransclusion(),this.markdownService.reload$.pipe(De(this.destroyed$)).subscribe(()=>this.loadContent())}ngOnDestroy(){this.destroyed$.next(),this.destroyed$.complete()}async render(e,i=!1){let r={decodeHtml:i,inline:this.inline,emoji:this.emoji,mermaid:this.mermaid,disableSanitizer:this.disableSanitizer},o={clipboard:this.clipboard,clipboardOptions:this.getClipboardOptions(),katex:this.katex,katexOptions:this.katexOptions,mermaid:this.mermaid,mermaidOptions:this.mermaidOptions},a=await this.markdownService.parse(e,r);this.element.nativeElement.innerHTML=a,this.handlePlugins(),this.markdownService.render(this.element.nativeElement,o,this.viewContainerRef),this.ready.emit()}coerceBooleanProperty(e){return e!=null&&`${String(e)}`!="false"}getClipboardOptions(){if(this.clipboardButtonComponent||this.clipboardButtonTemplate)return{buttonComponent:this.clipboardButtonComponent,buttonTemplate:this.clipboardButtonTemplate}}handleData(){this.render(this.data)}handleSrc(){this.markdownService.getSource(this.src).subscribe({next:e=>{this.render(e).then(()=>{this.load.emit(e)})},error:e=>this.error.emit(e)})}handleTransclusion(){this.render(this.element.nativeElement.innerHTML,!0)}handlePlugins(){this.commandLine&&(this.setPluginClass(this.element.nativeElement,Gc.CommandLine),this.setPluginOptions(this.element.nativeElement,{dataFilterOutput:this.filterOutput,dataHost:this.host,dataPrompt:this.prompt,dataOutput:this.output,dataUser:this.user})),this.lineHighlight&&this.setPluginOptions(this.element.nativeElement,{dataLine:this.line,dataLineOffset:this.lineOffset}),this.lineNumbers&&(this.setPluginClass(this.element.nativeElement,Gc.LineNumbers),this.setPluginOptions(this.element.nativeElement,{dataStart:this.start}))}setPluginClass(e,i){let r=e.querySelectorAll("pre");for(let o=0;o<r.length;o++){let a=i instanceof Array?i:[i];r.item(o).classList.add(...a)}}setPluginOptions(e,i){let r=e.querySelectorAll("pre");for(let o=0;o<r.length;o++)Object.keys(i).forEach(a=>{let s=i[a];if(s){let l=this.toLispCase(a);r.item(o).setAttribute(l,s.toString())}})}toLispCase(e){let i=e.match(/([A-Z])/g);if(!i)return e;let r=e.toString();for(let o=0,a=i.length;o<a;o++)r=r.replace(new RegExp(i[o]),"-"+i[o].toLowerCase());return r.slice(0,1)==="-"&&(r=r.slice(1)),r}static{this.\u0275fac=function(i){return new(i||n)}}static{this.\u0275cmp=I({type:n,selectors:[["markdown"],["","markdown",""]],inputs:{data:"data",src:"src",disableSanitizer:"disableSanitizer",inline:"inline",clipboard:"clipboard",clipboardButtonComponent:"clipboardButtonComponent",clipboardButtonTemplate:"clipboardButtonTemplate",emoji:"emoji",katex:"katex",katexOptions:"katexOptions",mermaid:"mermaid",mermaidOptions:"mermaidOptions",lineHighlight:"lineHighlight",line:"line",lineOffset:"lineOffset",lineNumbers:"lineNumbers",start:"start",commandLine:"commandLine",filterOutput:"filterOutput",host:"host",prompt:"prompt",output:"output",user:"user"},outputs:{error:"error",load:"load",ready:"ready"},features:[dt],ngContentSelectors:y0,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},encapsulation:2})}}return n})();function Wc(n){return[Ih,n?.loader??[],n?.clipboardOptions??[],n?.markedOptions??[],n?.mermaidOptions??[],n?.markedExtensions??[],n?.sanitize??[]]}var bn=(()=>{class n{static forRoot(e){return{ngModule:n,providers:[Wc(e)]}}static forChild(){return{ngModule:n}}static{this.\u0275fac=function(i){return new(i||n)}}static{this.\u0275mod=V({type:n})}static{this.\u0275inj=$({})}}return n})();var fs=new WeakMap,Je=(()=>{class n{_appRef;_injector=m(te);_environmentInjector=m(An);load(e){let i=this._appRef=this._appRef||this._injector.get(ei),r=fs.get(i);r||(r={loaders:new Set,refs:[]},fs.set(i,r),i.onDestroy(()=>{fs.get(i)?.refs.forEach(o=>o.destroy()),fs.delete(i)})),r.loaders.has(e)||(r.loaders.add(e),r.refs.push(Ga(e,{environmentInjector:this._environmentInjector})))}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var dr=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["ng-component"]],exportAs:["cdkVisuallyHidden"],decls:0,vars:0,template:function(i,r){},styles:[`.cdk-visually-hidden {
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
`],encapsulation:2,changeDetection:0})}return n})(),gs;function N0(){if(gs===void 0&&(gs=null,typeof window<"u")){let n=window;n.trustedTypes!==void 0&&(gs=n.trustedTypes.createPolicy("angular#components",{createHTML:t=>t}))}return gs}function Ci(n){return N0()?.createHTML(n)||n}function Th(n,t,e){let i=e.sanitize(tt.HTML,t);n.innerHTML=Ci(i||"")}function Oh(n){return Error(`Unable to find icon with the name "${n}"`)}function L0(){return Error("Could not find HttpClient for use with Angular Material icons. Please add provideHttpClient() to your providers.")}function Rh(n){return Error(`The URL provided to MatIconRegistry was not trusted as a resource URL via Angular's DomSanitizer. Attempted URL was "${n}".`)}function Ph(n){return Error(`The literal provided to MatIconRegistry was not trusted as safe HTML by Angular's DomSanitizer. Attempted literal was "${n}".`)}var Pn=class{url;svgText;options;svgElement=null;constructor(t,e,i){this.url=t,this.svgText=e,this.options=i}},bs=(()=>{class n{_httpClient;_sanitizer;_errorHandler;_document;_svgIconConfigs=new Map;_iconSetConfigs=new Map;_cachedIconsByUrl=new Map;_inProgressUrlFetches=new Map;_fontCssClassesByAlias=new Map;_resolvers=[];_defaultFontSetClass=["material-icons","mat-ligature-font"];constructor(e,i,r,o){this._httpClient=e,this._sanitizer=i,this._errorHandler=o,this._document=r}addSvgIcon(e,i,r){return this.addSvgIconInNamespace("",e,i,r)}addSvgIconLiteral(e,i,r){return this.addSvgIconLiteralInNamespace("",e,i,r)}addSvgIconInNamespace(e,i,r,o){return this._addSvgIconConfig(e,i,new Pn(r,null,o))}addSvgIconResolver(e){return this._resolvers.push(e),this}addSvgIconLiteralInNamespace(e,i,r,o){let a=this._sanitizer.sanitize(tt.HTML,r);if(!a)throw Ph(r);let s=Ci(a);return this._addSvgIconConfig(e,i,new Pn("",s,o))}addSvgIconSet(e,i){return this.addSvgIconSetInNamespace("",e,i)}addSvgIconSetLiteral(e,i){return this.addSvgIconSetLiteralInNamespace("",e,i)}addSvgIconSetInNamespace(e,i,r){return this._addSvgIconSetConfig(e,new Pn(i,null,r))}addSvgIconSetLiteralInNamespace(e,i,r){let o=this._sanitizer.sanitize(tt.HTML,i);if(!o)throw Ph(i);let a=Ci(o);return this._addSvgIconSetConfig(e,new Pn("",a,r))}registerFontClassAlias(e,i=e){return this._fontCssClassesByAlias.set(e,i),this}classNameForFontAlias(e){return this._fontCssClassesByAlias.get(e)||e}setDefaultFontSetClass(...e){return this._defaultFontSetClass=e,this}getDefaultFontSetClass(){return this._defaultFontSetClass}getSvgIconFromUrl(e){let i=this._sanitizer.sanitize(tt.RESOURCE_URL,e);if(!i)throw Rh(e);let r=this._cachedIconsByUrl.get(i);return r?Pe(_s(r)):this._loadSvgIconFromConfig(new Pn(e,null)).pipe(Xn(o=>this._cachedIconsByUrl.set(i,o)),xe(o=>_s(o)))}getNamedSvgIcon(e,i=""){let r=Fh(i,e),o=this._svgIconConfigs.get(r);if(o)return this._getSvgFromConfig(o);if(o=this._getIconConfigFromResolvers(i,e),o)return this._svgIconConfigs.set(r,o),this._getSvgFromConfig(o);let a=this._iconSetConfigs.get(i);return a?this._getSvgFromIconSetConfigs(e,a):Yu(Oh(r))}ngOnDestroy(){this._resolvers=[],this._svgIconConfigs.clear(),this._iconSetConfigs.clear(),this._cachedIconsByUrl.clear()}_getSvgFromConfig(e){return e.svgText?Pe(_s(this._svgElementFromConfig(e))):this._loadSvgIconFromConfig(e).pipe(xe(i=>_s(i)))}_getSvgFromIconSetConfigs(e,i){let r=this._extractIconWithNameFromAnySet(e,i);if(r)return Pe(r);let o=i.filter(a=>!a.svgText).map(a=>this._loadSvgIconSetFromConfig(a).pipe(Oa(s=>{let d=`Loading icon set URL: ${this._sanitizer.sanitize(tt.RESOURCE_URL,a.url)} failed: ${s.message}`;return this._errorHandler.handleError(new Error(d)),Pe(null)})));return Zu(o).pipe(xe(()=>{let a=this._extractIconWithNameFromAnySet(e,i);if(!a)throw Oh(e);return a}))}_extractIconWithNameFromAnySet(e,i){for(let r=i.length-1;r>=0;r--){let o=i[r];if(o.svgText&&o.svgText.toString().indexOf(e)>-1){let a=this._svgElementFromConfig(o),s=this._extractSvgIconFromSet(a,e,o.options);if(s)return s}}return null}_loadSvgIconFromConfig(e){return this._fetchIcon(e).pipe(Xn(i=>e.svgText=i),xe(()=>this._svgElementFromConfig(e)))}_loadSvgIconSetFromConfig(e){return e.svgText?Pe(null):this._fetchIcon(e).pipe(Xn(i=>e.svgText=i))}_extractSvgIconFromSet(e,i,r){let o=e.querySelector(`[id="${i}"]`);if(!o)return null;let a=o.cloneNode(!0);if(a.removeAttribute("id"),a.nodeName.toLowerCase()==="svg")return this._setSvgAttributes(a,r);if(a.nodeName.toLowerCase()==="symbol")return this._setSvgAttributes(this._toSvgElement(a),r);let s=this._svgElementFromString(Ci("<svg></svg>"));return s.appendChild(a),this._setSvgAttributes(s,r)}_svgElementFromString(e){let i=this._document.createElement("DIV");i.innerHTML=e;let r=i.querySelector("svg");if(!r)throw Error("<svg> tag not found");return r}_toSvgElement(e){let i=this._svgElementFromString(Ci("<svg></svg>")),r=e.attributes;for(let o=0;o<r.length;o++){let{name:a,value:s}=r[o];a!=="id"&&i.setAttribute(a,s)}for(let o=0;o<e.childNodes.length;o++)e.childNodes[o].nodeType===this._document.ELEMENT_NODE&&i.appendChild(e.childNodes[o].cloneNode(!0));return i}_setSvgAttributes(e,i){return e.setAttribute("fit",""),e.setAttribute("height","100%"),e.setAttribute("width","100%"),e.setAttribute("preserveAspectRatio","xMidYMid meet"),e.setAttribute("focusable","false"),i&&i.viewBox&&e.setAttribute("viewBox",i.viewBox),e}_fetchIcon(e){let{url:i,options:r}=e,o=r?.withCredentials??!1;if(!this._httpClient)throw L0();if(i==null)throw Error(`Cannot fetch icon from URL "${i}".`);let a=this._sanitizer.sanitize(tt.RESOURCE_URL,i);if(!a)throw Rh(i);let s=this._inProgressUrlFetches.get(a);if(s)return s;let l=this._httpClient.get(a,{responseType:"text",withCredentials:o}).pipe(xe(d=>Ci(d)),io(()=>this._inProgressUrlFetches.delete(a)),Pa());return this._inProgressUrlFetches.set(a,l),l}_addSvgIconConfig(e,i,r){return this._svgIconConfigs.set(Fh(e,i),r),this}_addSvgIconSetConfig(e,i){let r=this._iconSetConfigs.get(e);return r?r.push(i):this._iconSetConfigs.set(e,[i]),this}_svgElementFromConfig(e){if(!e.svgElement){let i=this._svgElementFromString(e.svgText);this._setSvgAttributes(i,e.options),e.svgElement=i}return e.svgElement}_getIconConfigFromResolvers(e,i){for(let r=0;r<this._resolvers.length;r++){let o=this._resolvers[r](i,e);if(o)return B0(o)?new Pn(o.url,null,o.options):new Pn(o,null)}}static \u0275fac=function(i){return new(i||n)(j(Ht,8),j(Rn),j(W,8),j(_i))};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();function _s(n){return n.cloneNode(!0)}function Fh(n,t){return n+":"+t}function B0(n){return!!(n.url&&n.options)}var $0=new M("cdk-dir-doc",{providedIn:"root",factory:()=>m(W)}),j0=/^(ar|ckb|dv|he|iw|fa|nqo|ps|sd|ug|ur|yi|.*[-_](Adlm|Arab|Hebr|Nkoo|Rohg|Thaa))(?!.*[-_](Latn|Cyrl)($|-|_))($|-|_)/i;function Nh(n){let t=n?.toLowerCase()||"";return t==="auto"&&typeof navigator<"u"&&navigator?.language?j0.test(navigator.language)?"rtl":"ltr":t==="rtl"?"rtl":"ltr"}var wt=(()=>{class n{get value(){return this.valueSignal()}valueSignal=X("ltr");change=new Ee;constructor(){let e=m($0,{optional:!0});if(e){let i=e.body?e.body.dir:null,r=e.documentElement?e.documentElement.dir:null;this.valueSignal.set(Nh(i||r||"ltr"))}}ngOnDestroy(){this.change.complete()}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var ie=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({})}return n})();var V0=["*"],z0=new M("MAT_ICON_DEFAULT_OPTIONS"),H0=new M("mat-icon-location",{providedIn:"root",factory:()=>{let n=m(W),t=n?n.location:null;return{getPathname:()=>t?t.pathname+t.search:""}}}),Lh=["clip-path","color-profile","src","cursor","fill","filter","marker","marker-start","marker-mid","marker-end","mask","stroke"],q0=Lh.map(n=>`[${n}]`).join(", "),U0=/^url\(['"]?#(.*?)['"]?\)$/,Mt=(()=>{class n{_elementRef=m(Y);_iconRegistry=m(bs);_location=m(H0);_errorHandler=m(_i);_defaultColor;get color(){return this._color||this._defaultColor}set color(e){this._color=e}_color;inline=!1;get svgIcon(){return this._svgIcon}set svgIcon(e){e!==this._svgIcon&&(e?this._updateSvgIcon(e):this._svgIcon&&this._clearSvgElement(),this._svgIcon=e)}_svgIcon;get fontSet(){return this._fontSet}set fontSet(e){let i=this._cleanupFontValue(e);i!==this._fontSet&&(this._fontSet=i,this._updateFontIconClasses())}_fontSet;get fontIcon(){return this._fontIcon}set fontIcon(e){let i=this._cleanupFontValue(e);i!==this._fontIcon&&(this._fontIcon=i,this._updateFontIconClasses())}_fontIcon;_previousFontSetClass=[];_previousFontIconClass;_svgName=null;_svgNamespace=null;_previousPath;_elementsWithExternalReferences;_currentIconFetch=lt.EMPTY;constructor(){let e=m(new vp("aria-hidden"),{optional:!0}),i=m(z0,{optional:!0});i&&(i.color&&(this.color=this._defaultColor=i.color),i.fontSet&&(this.fontSet=i.fontSet)),e||this._elementRef.nativeElement.setAttribute("aria-hidden","true")}_splitIconName(e){if(!e)return["",""];let i=e.split(":");switch(i.length){case 1:return["",i[0]];case 2:return i;default:throw Error(`Invalid icon name: "${e}"`)}}ngOnInit(){this._updateFontIconClasses()}ngAfterViewChecked(){let e=this._elementsWithExternalReferences;if(e&&e.size){let i=this._location.getPathname();i!==this._previousPath&&(this._previousPath=i,this._prependPathToReferences(i))}}ngOnDestroy(){this._currentIconFetch.unsubscribe(),this._elementsWithExternalReferences&&this._elementsWithExternalReferences.clear()}_usingFontIcon(){return!this.svgIcon}_setSvgElement(e){this._clearSvgElement();let i=this._location.getPathname();this._previousPath=i,this._cacheChildrenWithExternalReferences(e),this._prependPathToReferences(i),this._elementRef.nativeElement.appendChild(e)}_clearSvgElement(){let e=this._elementRef.nativeElement,i=e.childNodes.length;for(this._elementsWithExternalReferences&&this._elementsWithExternalReferences.clear();i--;){let r=e.childNodes[i];(r.nodeType!==1||r.nodeName.toLowerCase()==="svg")&&r.remove()}}_updateFontIconClasses(){if(!this._usingFontIcon())return;let e=this._elementRef.nativeElement,i=(this.fontSet?this._iconRegistry.classNameForFontAlias(this.fontSet).split(/ +/):this._iconRegistry.getDefaultFontSetClass()).filter(r=>r.length>0);this._previousFontSetClass.forEach(r=>e.classList.remove(r)),i.forEach(r=>e.classList.add(r)),this._previousFontSetClass=i,this.fontIcon!==this._previousFontIconClass&&!i.includes("mat-ligature-font")&&(this._previousFontIconClass&&e.classList.remove(this._previousFontIconClass),this.fontIcon&&e.classList.add(this.fontIcon),this._previousFontIconClass=this.fontIcon)}_cleanupFontValue(e){return typeof e=="string"?e.trim().split(" ")[0]:e}_prependPathToReferences(e){let i=this._elementsWithExternalReferences;i&&i.forEach((r,o)=>{r.forEach(a=>{o.setAttribute(a.name,`url('${e}#${a.value}')`)})})}_cacheChildrenWithExternalReferences(e){let i=e.querySelectorAll(q0),r=this._elementsWithExternalReferences=this._elementsWithExternalReferences||new Map;for(let o=0;o<i.length;o++)Lh.forEach(a=>{let s=i[o],l=s.getAttribute(a),d=l?l.match(U0):null;if(d){let c=r.get(s);c||(c=[],r.set(s,c)),c.push({name:a,value:d[1]})}})}_updateSvgIcon(e){if(this._svgNamespace=null,this._svgName=null,this._currentIconFetch.unsubscribe(),e){let[i,r]=this._splitIconName(e);i&&(this._svgNamespace=i),r&&(this._svgName=r),this._currentIconFetch=this._iconRegistry.getNamedSvgIcon(r,i).pipe(Qn(1)).subscribe(o=>this._setSvgElement(o),o=>{let a=`Error retrieving icon ${i}:${r}! ${o.message}`;this._errorHandler.handleError(new Error(a))})}}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-icon"]],hostAttrs:["role","img",1,"mat-icon","notranslate"],hostVars:10,hostBindings:function(i,r){i&2&&(le("data-mat-icon-type",r._usingFontIcon()?"font":"svg")("data-mat-icon-name",r._svgName||r.fontIcon)("data-mat-icon-namespace",r._svgNamespace||r.fontSet)("fontIcon",r._usingFontIcon()?r.fontIcon:null),vt(r.color?"mat-"+r.color:""),Q("mat-icon-inline",r.inline)("mat-icon-no-color",r.color!=="primary"&&r.color!=="accent"&&r.color!=="warn"))},inputs:{color:"color",inline:[2,"inline","inline",Me],svgIcon:"svgIcon",fontSet:"fontSet",fontIcon:"fontIcon"},exportAs:["matIcon"],ngContentSelectors:V0,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},styles:[`mat-icon, mat-icon.mat-primary, mat-icon.mat-accent, mat-icon.mat-warn {
  color: var(--mat-icon-color, inherit);
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
`],encapsulation:2,changeDetection:0})}return n})(),Ct=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[ie]})}return n})();var mr=class{},vs=class n{constructor(t,e){this.iconRegistry=t;this.sanitizer=e}load(){}static \u0275fac=function(e){return new(e||n)(j(bs),j(Rn))};static \u0275prov=E({token:n,factory:n.\u0275fac})};var vn=class n{static contextPath=n.getContextPath();static getContextPath(){return document.location.pathname.split("/asyncapi-ui.html")[0]}static uiConfig=n.contextPath+"/ui-config";static docs=n.contextPath+"/docs";static getDocsForGroupEndpoint(t){return n.docs+`/${t}`}static getPublishEndpoint(t){return n.contextPath+`/plugin/${t}/publish`}};var we=class{static DEFAULT_SHOW_BINDINGS=!0;static DEFAULT_SHOW_HEADERS=!0;static DEFAULT_GROUP="default"},ys=class n extends we{constructor(e){super();this.http=e;this.uiConfig=this.http.get(vn.uiConfig).pipe(Oa(()=>Pe(this.fallbackConfig)),Mn()),this.uiConfig.subscribe(i=>{this.toggleIsShowBindings(i.initialConfig.showBindings),this.toggleIsShowHeaders(i.initialConfig.showHeaders)})}_getGroup=new Kn(we.DEFAULT_GROUP);isGroup$=this._getGroup.asObservable();fallbackConfig={initialConfig:{showBindings:we.DEFAULT_SHOW_BINDINGS,showHeaders:we.DEFAULT_SHOW_HEADERS},groups:[]};_isShowBindings=new Kn(we.DEFAULT_SHOW_BINDINGS);isShowBindings$=this._isShowBindings.asObservable();_isShowHeaders=new Kn(we.DEFAULT_SHOW_HEADERS);isShowHeaders$=this._isShowHeaders.asObservable();uiConfig;toggleIsShowBindings(e){this._isShowBindings.next(e)}toggleIsShowHeaders(e){this._isShowHeaders.next(e)}changeGroup(e){this._getGroup.next(e)}static \u0275fac=function(i){return new(i||n)(j(Ht))};static \u0275prov=E({token:n,factory:n.\u0275fac})};var Ro="server-";var Kc="channel-";var qt=class{value;rawValue;lineCount;constructor(t){this.rawValue=t,typeof t=="object"?Object.keys(t).length>0?this.value=JSON.stringify(t,null,2):this.value="":this.value=""+t,this.lineCount=this.value.split(`
`).length}};var ws=class extends Error{};function Yc(n,t){try{return t()}catch(e){throw e instanceof Error?new ws(n+" ("+e.message+")"):new ws(n+" ("+e+")")}}function Bh(n,t=()=>{}){try{return n()}catch(e){t!==void 0&&t(e);return}}function Di(n){return n.buttons===0||n.detail===0}function Ei(n){let t=n.touches&&n.touches[0]||n.changedTouches&&n.changedTouches[0];return!!t&&t.identifier===-1&&(t.radiusX==null||t.radiusX===1)&&(t.radiusY==null||t.radiusY===1)}var Qc;function $h(){if(Qc==null){let n=typeof document<"u"?document.head:null;Qc=!!(n&&(n.createShadowRoot||n.attachShadow))}return Qc}function Xc(n){if($h()){let t=n.getRootNode?n.getRootNode():null;if(typeof ShadowRoot<"u"&&ShadowRoot&&t instanceof ShadowRoot)return t}return null}function At(n){return n.composedPath?n.composedPath()[0]:n.target}var Zc;try{Zc=typeof Intl<"u"&&Intl.v8BreakIterator}catch{Zc=!1}var Ce=(()=>{class n{_platformId=m(Zn);isBrowser=this._platformId?On(this._platformId):typeof document=="object"&&!!document;EDGE=this.isBrowser&&/(edge)/i.test(navigator.userAgent);TRIDENT=this.isBrowser&&/(msie|trident)/i.test(navigator.userAgent);BLINK=this.isBrowser&&!!(window.chrome||Zc)&&typeof CSS<"u"&&!this.EDGE&&!this.TRIDENT;WEBKIT=this.isBrowser&&/AppleWebKit/i.test(navigator.userAgent)&&!this.BLINK&&!this.EDGE&&!this.TRIDENT;IOS=this.isBrowser&&/iPad|iPhone|iPod/.test(navigator.userAgent)&&!("MSStream"in window);FIREFOX=this.isBrowser&&/(firefox|minefield)/i.test(navigator.userAgent);ANDROID=this.isBrowser&&/android/i.test(navigator.userAgent)&&!this.TRIDENT;SAFARI=this.isBrowser&&/safari/i.test(navigator.userAgent)&&this.WEBKIT;constructor(){}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var Po;function jh(){if(Po==null&&typeof window<"u")try{window.addEventListener("test",null,Object.defineProperty({},"passive",{get:()=>Po=!0}))}finally{Po=Po||!1}return Po}function ur(n){return jh()?n:!!n.capture}function Fn(n,t=0){return Vh(n)?Number(n):arguments.length===2?t:0}function Vh(n){return!isNaN(parseFloat(n))&&!isNaN(Number(n))}function Ut(n){return n instanceof Y?n.nativeElement:n}var zh=new M("cdk-input-modality-detector-options"),Hh={ignoreKeys:[18,17,224,91,16]},qh=650,Jc={passive:!0,capture:!0},Uh=(()=>{class n{_platform=m(Ce);_listenerCleanups;modalityDetected;modalityChanged;get mostRecentModality(){return this._modality.value}_mostRecentTarget=null;_modality=new Kn(null);_options;_lastTouchMs=0;_onKeydown=e=>{this._options?.ignoreKeys?.some(i=>i===e.keyCode)||(this._modality.next("keyboard"),this._mostRecentTarget=At(e))};_onMousedown=e=>{Date.now()-this._lastTouchMs<qh||(this._modality.next(Di(e)?"keyboard":"mouse"),this._mostRecentTarget=At(e))};_onTouchstart=e=>{if(Ei(e)){this._modality.next("keyboard");return}this._lastTouchMs=Date.now(),this._modality.next("touch"),this._mostRecentTarget=At(e)};constructor(){let e=m(K),i=m(W),r=m(zh,{optional:!0});if(this._options=S(S({},Hh),r),this.modalityDetected=this._modality.pipe(Fa(1)),this.modalityChanged=this.modalityDetected.pipe(no()),this._platform.isBrowser){let o=m(bt).createRenderer(null,null);this._listenerCleanups=e.runOutsideAngular(()=>[o.listen(i,"keydown",this._onKeydown,Jc),o.listen(i,"mousedown",this._onMousedown,Jc),o.listen(i,"touchstart",this._onTouchstart,Jc)])}}ngOnDestroy(){this._modality.complete(),this._listenerCleanups?.forEach(e=>e())}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),Fo=(function(n){return n[n.IMMEDIATE=0]="IMMEDIATE",n[n.EVENTUAL=1]="EVENTUAL",n})(Fo||{}),Gh=new M("cdk-focus-monitor-default-options"),Cs=ur({passive:!0,capture:!0}),yn=(()=>{class n{_ngZone=m(K);_platform=m(Ce);_inputModalityDetector=m(Uh);_origin=null;_lastFocusOrigin=null;_windowFocused=!1;_windowFocusTimeoutId;_originTimeoutId;_originFromTouchInteraction=!1;_elementInfo=new Map;_monitoredElementCount=0;_rootNodeFocusListenerCount=new Map;_detectionMode;_windowFocusListener=()=>{this._windowFocused=!0,this._windowFocusTimeoutId=setTimeout(()=>this._windowFocused=!1)};_document=m(W);_stopInputModalityDetector=new z;constructor(){let e=m(Gh,{optional:!0});this._detectionMode=e?.detectionMode||Fo.IMMEDIATE}_rootNodeFocusAndBlurListener=e=>{let i=At(e);for(let r=i;r;r=r.parentElement)e.type==="focus"?this._onFocus(e,r):this._onBlur(e,r)};monitor(e,i=!1){let r=Ut(e);if(!this._platform.isBrowser||r.nodeType!==1)return Pe();let o=Xc(r)||this._document,a=this._elementInfo.get(r);if(a)return i&&(a.checkChildren=!0),a.subject;let s={checkChildren:i,subject:new z,rootNode:o};return this._elementInfo.set(r,s),this._registerGlobalListeners(s),s.subject}stopMonitoring(e){let i=Ut(e),r=this._elementInfo.get(i);r&&(r.subject.complete(),this._setClasses(i),this._elementInfo.delete(i),this._removeGlobalListeners(r))}focusVia(e,i,r){let o=Ut(e),a=this._document.activeElement;o===a?this._getClosestElementsInfo(o).forEach(([s,l])=>this._originChanged(s,i,l)):(this._setOrigin(i),typeof o.focus=="function"&&o.focus(r))}ngOnDestroy(){this._elementInfo.forEach((e,i)=>this.stopMonitoring(i))}_getWindow(){return this._document.defaultView||window}_getFocusOrigin(e){return this._origin?this._originFromTouchInteraction?this._shouldBeAttributedToTouch(e)?"touch":"program":this._origin:this._windowFocused&&this._lastFocusOrigin?this._lastFocusOrigin:e&&this._isLastInteractionFromInputLabel(e)?"mouse":"program"}_shouldBeAttributedToTouch(e){return this._detectionMode===Fo.EVENTUAL||!!e?.contains(this._inputModalityDetector._mostRecentTarget)}_setClasses(e,i){e.classList.toggle("cdk-focused",!!i),e.classList.toggle("cdk-touch-focused",i==="touch"),e.classList.toggle("cdk-keyboard-focused",i==="keyboard"),e.classList.toggle("cdk-mouse-focused",i==="mouse"),e.classList.toggle("cdk-program-focused",i==="program")}_setOrigin(e,i=!1){this._ngZone.runOutsideAngular(()=>{if(this._origin=e,this._originFromTouchInteraction=e==="touch"&&i,this._detectionMode===Fo.IMMEDIATE){clearTimeout(this._originTimeoutId);let r=this._originFromTouchInteraction?qh:1;this._originTimeoutId=setTimeout(()=>this._origin=null,r)}})}_onFocus(e,i){let r=this._elementInfo.get(i),o=At(e);!r||!r.checkChildren&&i!==o||this._originChanged(i,this._getFocusOrigin(o),r)}_onBlur(e,i){let r=this._elementInfo.get(i);!r||r.checkChildren&&e.relatedTarget instanceof Node&&i.contains(e.relatedTarget)||(this._setClasses(i),this._emitOrigin(r,null))}_emitOrigin(e,i){e.subject.observers.length&&this._ngZone.run(()=>e.subject.next(i))}_registerGlobalListeners(e){if(!this._platform.isBrowser)return;let i=e.rootNode,r=this._rootNodeFocusListenerCount.get(i)||0;r||this._ngZone.runOutsideAngular(()=>{i.addEventListener("focus",this._rootNodeFocusAndBlurListener,Cs),i.addEventListener("blur",this._rootNodeFocusAndBlurListener,Cs)}),this._rootNodeFocusListenerCount.set(i,r+1),++this._monitoredElementCount===1&&(this._ngZone.runOutsideAngular(()=>{this._getWindow().addEventListener("focus",this._windowFocusListener)}),this._inputModalityDetector.modalityDetected.pipe(De(this._stopInputModalityDetector)).subscribe(o=>{this._setOrigin(o,!0)}))}_removeGlobalListeners(e){let i=e.rootNode;if(this._rootNodeFocusListenerCount.has(i)){let r=this._rootNodeFocusListenerCount.get(i);r>1?this._rootNodeFocusListenerCount.set(i,r-1):(i.removeEventListener("focus",this._rootNodeFocusAndBlurListener,Cs),i.removeEventListener("blur",this._rootNodeFocusAndBlurListener,Cs),this._rootNodeFocusListenerCount.delete(i))}--this._monitoredElementCount||(this._getWindow().removeEventListener("focus",this._windowFocusListener),this._stopInputModalityDetector.next(),clearTimeout(this._windowFocusTimeoutId),clearTimeout(this._originTimeoutId))}_originChanged(e,i,r){this._setClasses(e,i),this._emitOrigin(r,i),this._lastFocusOrigin=i}_getClosestElementsInfo(e){let i=[];return this._elementInfo.forEach((r,o)=>{(o===e||r.checkChildren&&o.contains(e))&&i.push([o,r])}),i}_isLastInteractionFromInputLabel(e){let{_mostRecentTarget:i,mostRecentModality:r}=this._inputModalityDetector;if(r!=="mouse"||!i||i===e||e.nodeName!=="INPUT"&&e.nodeName!=="TEXTAREA"||e.disabled)return!1;let o=e.labels;if(o){for(let a=0;a<o.length;a++)if(o[a].contains(i))return!0}return!1}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();function pr(n){return Array.isArray(n)?n:[n]}var Wh=new Set,Si,ks=(()=>{class n{_platform=m(Ce);_nonce=m(Zi,{optional:!0});_matchMedia;constructor(){this._matchMedia=this._platform.isBrowser&&window.matchMedia?window.matchMedia.bind(window):K0}matchMedia(e){return(this._platform.WEBKIT||this._platform.BLINK)&&W0(e,this._nonce),this._matchMedia(e)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();function W0(n,t){if(!Wh.has(n))try{Si||(Si=document.createElement("style"),t&&Si.setAttribute("nonce",t),Si.setAttribute("type","text/css"),document.head.appendChild(Si)),Si.sheet&&(Si.sheet.insertRule(`@media ${n} {body{ }}`,0),Wh.add(n))}catch(e){console.error(e)}}function K0(n){return{matches:n==="all"||n==="",media:n,addListener:()=>{},removeListener:()=>{}}}var ed=(()=>{class n{_mediaMatcher=m(ks);_zone=m(K);_queries=new Map;_destroySubject=new z;constructor(){}ngOnDestroy(){this._destroySubject.next(),this._destroySubject.complete()}isMatched(e){return Kh(pr(e)).some(r=>this._registerQuery(r).mql.matches)}observe(e){let r=Kh(pr(e)).map(a=>this._registerQuery(a).observable),o=Qu(r);return o=Xu(o.pipe(Qn(1)),o.pipe(Fa(1),Yn(0))),o.pipe(xe(a=>{let s={matches:!1,breakpoints:{}};return a.forEach(({matches:l,query:d})=>{s.matches=s.matches||l,s.breakpoints[d]=l}),s}))}_registerQuery(e){if(this._queries.has(e))return this._queries.get(e);let i=this._mediaMatcher.matchMedia(e),o={observable:new St(a=>{let s=l=>this._zone.run(()=>a.next(l));return i.addListener(s),()=>{i.removeListener(s)}}).pipe(ct(i),xe(({matches:a})=>({query:e,matches:a})),De(this._destroySubject)),mql:i};return this._queries.set(e,o),o}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();function Kh(n){return n.map(t=>t.split(",")).reduce((t,e)=>t.concat(e)).map(t=>t.trim())}function Y0(n){if(n.type==="characterData"&&n.target instanceof Comment)return!0;if(n.type==="childList"){for(let t=0;t<n.addedNodes.length;t++)if(!(n.addedNodes[t]instanceof Comment))return!1;for(let t=0;t<n.removedNodes.length;t++)if(!(n.removedNodes[t]instanceof Comment))return!1;return!0}return!1}var Yh=(()=>{class n{create(e){return typeof MutationObserver>"u"?null:new MutationObserver(e)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),Q0=(()=>{class n{_mutationObserverFactory=m(Yh);_observedElements=new Map;_ngZone=m(K);constructor(){}ngOnDestroy(){this._observedElements.forEach((e,i)=>this._cleanupObserver(i))}observe(e){let i=Ut(e);return new St(r=>{let a=this._observeElement(i).pipe(xe(s=>s.filter(l=>!Y0(l))),Be(s=>!!s.length)).subscribe(s=>{this._ngZone.run(()=>{r.next(s)})});return()=>{a.unsubscribe(),this._unobserveElement(i)}})}_observeElement(e){return this._ngZone.runOutsideAngular(()=>{if(this._observedElements.has(e))this._observedElements.get(e).count++;else{let i=new z,r=this._mutationObserverFactory.create(o=>i.next(o));r&&r.observe(e,{characterData:!0,childList:!0,subtree:!0}),this._observedElements.set(e,{observer:r,stream:i,count:1})}return this._observedElements.get(e).stream})}_unobserveElement(e){this._observedElements.has(e)&&(this._observedElements.get(e).count--,this._observedElements.get(e).count||this._cleanupObserver(e))}_cleanupObserver(e){if(this._observedElements.has(e)){let{observer:i,stream:r}=this._observedElements.get(e);i&&i.disconnect(),r.complete(),this._observedElements.delete(e)}}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),Qh=(()=>{class n{_contentObserver=m(Q0);_elementRef=m(Y);event=new Ee;get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._disabled?this._unsubscribe():this._subscribe()}_disabled=!1;get debounce(){return this._debounce}set debounce(e){this._debounce=Fn(e),this._subscribe()}_debounce;_currentSubscription=null;constructor(){}ngAfterContentInit(){!this._currentSubscription&&!this.disabled&&this._subscribe()}ngOnDestroy(){this._unsubscribe()}_subscribe(){this._unsubscribe();let e=this._contentObserver.observe(this._elementRef);this._currentSubscription=(this.debounce?e.pipe(Yn(this.debounce)):e).subscribe(this.event)}_unsubscribe(){this._currentSubscription?.unsubscribe()}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","cdkObserveContent",""]],inputs:{disabled:[2,"cdkObserveContentDisabled","disabled",Me],debounce:"debounce"},outputs:{event:"cdkObserveContent"},exportAs:["cdkObserveContent"]})}return n})(),Ds=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({providers:[Yh]})}return n})();var Ss=(()=>{class n{_platform=m(Ce);constructor(){}isDisabled(e){return e.hasAttribute("disabled")}isVisible(e){return Z0(e)&&getComputedStyle(e).visibility==="visible"}isTabbable(e){if(!this._platform.isBrowser)return!1;let i=X0(ax(e));if(i&&(Xh(i)===-1||!this.isVisible(i)))return!1;let r=e.nodeName.toLowerCase(),o=Xh(e);return e.hasAttribute("contenteditable")?o!==-1:r==="iframe"||r==="object"||this._platform.WEBKIT&&this._platform.IOS&&!rx(e)?!1:r==="audio"?e.hasAttribute("controls")?o!==-1:!1:r==="video"?o===-1?!1:o!==null?!0:this._platform.FIREFOX||e.hasAttribute("controls"):e.tabIndex>=0}isFocusable(e,i){return ox(e)&&!this.isDisabled(e)&&(i?.ignoreVisibility||this.isVisible(e))}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();function X0(n){try{return n.frameElement}catch{return null}}function Z0(n){return!!(n.offsetWidth||n.offsetHeight||typeof n.getClientRects=="function"&&n.getClientRects().length)}function J0(n){let t=n.nodeName.toLowerCase();return t==="input"||t==="select"||t==="button"||t==="textarea"}function ex(n){return nx(n)&&n.type=="hidden"}function tx(n){return ix(n)&&n.hasAttribute("href")}function nx(n){return n.nodeName.toLowerCase()=="input"}function ix(n){return n.nodeName.toLowerCase()=="a"}function Zh(n){if(!n.hasAttribute("tabindex")||n.tabIndex===void 0)return!1;let t=n.getAttribute("tabindex");return!!(t&&!isNaN(parseInt(t,10)))}function Xh(n){if(!Zh(n))return null;let t=parseInt(n.getAttribute("tabindex")||"",10);return isNaN(t)?-1:t}function rx(n){let t=n.nodeName.toLowerCase(),e=t==="input"&&n.type;return e==="text"||e==="password"||t==="select"||t==="textarea"}function ox(n){return ex(n)?!1:J0(n)||tx(n)||n.hasAttribute("contenteditable")||Zh(n)}function ax(n){return n.ownerDocument&&n.ownerDocument.defaultView||window}var Es=class{_element;_checker;_ngZone;_document;_injector;_startAnchor=null;_endAnchor=null;_hasAttached=!1;startAnchorListener=()=>this.focusLastTabbableElement();endAnchorListener=()=>this.focusFirstTabbableElement();get enabled(){return this._enabled}set enabled(t){this._enabled=t,this._startAnchor&&this._endAnchor&&(this._toggleAnchorTabIndex(t,this._startAnchor),this._toggleAnchorTabIndex(t,this._endAnchor))}_enabled=!0;constructor(t,e,i,r,o=!1,a){this._element=t,this._checker=e,this._ngZone=i,this._document=r,this._injector=a,o||this.attachAnchors()}destroy(){let t=this._startAnchor,e=this._endAnchor;t&&(t.removeEventListener("focus",this.startAnchorListener),t.remove()),e&&(e.removeEventListener("focus",this.endAnchorListener),e.remove()),this._startAnchor=this._endAnchor=null,this._hasAttached=!1}attachAnchors(){return this._hasAttached?!0:(this._ngZone.runOutsideAngular(()=>{this._startAnchor||(this._startAnchor=this._createAnchor(),this._startAnchor.addEventListener("focus",this.startAnchorListener)),this._endAnchor||(this._endAnchor=this._createAnchor(),this._endAnchor.addEventListener("focus",this.endAnchorListener))}),this._element.parentNode&&(this._element.parentNode.insertBefore(this._startAnchor,this._element),this._element.parentNode.insertBefore(this._endAnchor,this._element.nextSibling),this._hasAttached=!0),this._hasAttached)}focusInitialElementWhenReady(t){return new Promise(e=>{this._executeOnStable(()=>e(this.focusInitialElement(t)))})}focusFirstTabbableElementWhenReady(t){return new Promise(e=>{this._executeOnStable(()=>e(this.focusFirstTabbableElement(t)))})}focusLastTabbableElementWhenReady(t){return new Promise(e=>{this._executeOnStable(()=>e(this.focusLastTabbableElement(t)))})}_getRegionBoundary(t){let e=this._element.querySelectorAll(`[cdk-focus-region-${t}], [cdkFocusRegion${t}], [cdk-focus-${t}]`);return t=="start"?e.length?e[0]:this._getFirstTabbableElement(this._element):e.length?e[e.length-1]:this._getLastTabbableElement(this._element)}focusInitialElement(t){let e=this._element.querySelector("[cdk-focus-initial], [cdkFocusInitial]");if(e){if(!this._checker.isFocusable(e)){let i=this._getFirstTabbableElement(e);return i?.focus(t),!!i}return e.focus(t),!0}return this.focusFirstTabbableElement(t)}focusFirstTabbableElement(t){let e=this._getRegionBoundary("start");return e&&e.focus(t),!!e}focusLastTabbableElement(t){let e=this._getRegionBoundary("end");return e&&e.focus(t),!!e}hasAttached(){return this._hasAttached}_getFirstTabbableElement(t){if(this._checker.isFocusable(t)&&this._checker.isTabbable(t))return t;let e=t.children;for(let i=0;i<e.length;i++){let r=e[i].nodeType===this._document.ELEMENT_NODE?this._getFirstTabbableElement(e[i]):null;if(r)return r}return null}_getLastTabbableElement(t){if(this._checker.isFocusable(t)&&this._checker.isTabbable(t))return t;let e=t.children;for(let i=e.length-1;i>=0;i--){let r=e[i].nodeType===this._document.ELEMENT_NODE?this._getLastTabbableElement(e[i]):null;if(r)return r}return null}_createAnchor(){let t=this._document.createElement("div");return this._toggleAnchorTabIndex(this._enabled,t),t.classList.add("cdk-visually-hidden"),t.classList.add("cdk-focus-trap-anchor"),t.setAttribute("aria-hidden","true"),t}_toggleAnchorTabIndex(t,e){t?e.setAttribute("tabindex","0"):e.removeAttribute("tabindex")}toggleAnchors(t){this._startAnchor&&this._endAnchor&&(this._toggleAnchorTabIndex(t,this._startAnchor),this._toggleAnchorTabIndex(t,this._endAnchor))}_executeOnStable(t){this._injector?mt(t,{injector:this._injector}):setTimeout(t)}},td=(()=>{class n{_checker=m(Ss);_ngZone=m(K);_document=m(W);_injector=m(te);constructor(){m(Je).load(dr)}create(e,i=!1){return new Es(e,this._checker,this._ngZone,this._document,i,this._injector)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var Jh=new M("liveAnnouncerElement",{providedIn:"root",factory:()=>null}),ef=new M("LIVE_ANNOUNCER_DEFAULT_OPTIONS"),sx=0,nd=(()=>{class n{_ngZone=m(K);_defaultOptions=m(ef,{optional:!0});_liveElement;_document=m(W);_sanitizer=m(Rn);_previousTimeout;_currentPromise;_currentResolve;constructor(){let e=m(Jh,{optional:!0});this._liveElement=e||this._createLiveElement()}announce(e,...i){let r=this._defaultOptions,o,a;return i.length===1&&typeof i[0]=="number"?a=i[0]:[o,a]=i,this.clear(),clearTimeout(this._previousTimeout),o||(o=r&&r.politeness?r.politeness:"polite"),a==null&&r&&(a=r.duration),this._liveElement.setAttribute("aria-live",o),this._liveElement.id&&this._exposeAnnouncerToModals(this._liveElement.id),this._ngZone.runOutsideAngular(()=>(this._currentPromise||(this._currentPromise=new Promise(s=>this._currentResolve=s)),clearTimeout(this._previousTimeout),this._previousTimeout=setTimeout(()=>{!e||typeof e=="string"?this._liveElement.textContent=e:Th(this._liveElement,e,this._sanitizer),typeof a=="number"&&(this._previousTimeout=setTimeout(()=>this.clear(),a)),this._currentResolve?.(),this._currentPromise=this._currentResolve=void 0},100),this._currentPromise))}clear(){this._liveElement&&(this._liveElement.textContent="")}ngOnDestroy(){clearTimeout(this._previousTimeout),this._liveElement?.remove(),this._liveElement=null,this._currentResolve?.(),this._currentPromise=this._currentResolve=void 0}_createLiveElement(){let e="cdk-live-announcer-element",i=this._document.getElementsByClassName(e),r=this._document.createElement("div");for(let o=0;o<i.length;o++)i[o].remove();return r.classList.add(e),r.classList.add("cdk-visually-hidden"),r.setAttribute("aria-atomic","true"),r.setAttribute("aria-live","polite"),r.id=`cdk-live-announcer-${sx++}`,this._document.body.appendChild(r),r}_exposeAnnouncerToModals(e){let i=this._document.querySelectorAll('body > .cdk-overlay-container [aria-modal="true"]');for(let r=0;r<i.length;r++){let o=i[r],a=o.getAttribute("aria-owns");a?a.indexOf(e)===-1&&o.setAttribute("aria-owns",a+" "+e):o.setAttribute("aria-owns",e)}}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var lx=200,Ms=class{_letterKeyStream=new z;_items=[];_selectedItemIndex=-1;_pressedLetters=[];_skipPredicateFn;_selectedItem=new z;selectedItem=this._selectedItem;constructor(t,e){let i=typeof e?.debounceInterval=="number"?e.debounceInterval:lx;e?.skipPredicate&&(this._skipPredicateFn=e.skipPredicate),this.setItems(t),this._setupKeyHandler(i)}destroy(){this._pressedLetters=[],this._letterKeyStream.complete(),this._selectedItem.complete()}setCurrentSelectedItemIndex(t){this._selectedItemIndex=t}setItems(t){this._items=t}handleKey(t){let e=t.keyCode;t.key&&t.key.length===1?this._letterKeyStream.next(t.key.toLocaleUpperCase()):(e>=65&&e<=90||e>=48&&e<=57)&&this._letterKeyStream.next(String.fromCharCode(e))}isTyping(){return this._pressedLetters.length>0}reset(){this._pressedLetters=[]}_setupKeyHandler(t){this._letterKeyStream.pipe(Xn(e=>this._pressedLetters.push(e)),Yn(t),Be(()=>this._pressedLetters.length>0),xe(()=>this._pressedLetters.join("").toLocaleUpperCase())).subscribe(e=>{for(let i=1;i<this._items.length+1;i++){let r=(this._selectedItemIndex+i)%this._items.length,o=this._items[r];if(!this._skipPredicateFn?.(o)&&o.getLabel?.().toLocaleUpperCase().trim().indexOf(e)===0){this._selectedItem.next(o);break}}this._pressedLetters=[]})}};function oi(n,...t){return t.length?t.some(e=>n[e]):n.altKey||n.shiftKey||n.ctrlKey||n.metaKey}var As=class{_items;_activeItemIndex=X(-1);_activeItem=X(null);_wrap=!1;_typeaheadSubscription=lt.EMPTY;_itemChangesSubscription;_vertical=!0;_horizontal=null;_allowedModifierKeys=[];_homeAndEnd=!1;_pageUpAndDown={enabled:!1,delta:10};_effectRef;_typeahead;_skipPredicateFn=t=>t.disabled;constructor(t,e){this._items=t,t instanceof In?this._itemChangesSubscription=t.changes.subscribe(i=>this._itemsChanged(i.toArray())):nr(t)&&(this._effectRef=Qi(()=>this._itemsChanged(t()),{injector:e}))}tabOut=new z;change=new z;skipPredicate(t){return this._skipPredicateFn=t,this}withWrap(t=!0){return this._wrap=t,this}withVerticalOrientation(t=!0){return this._vertical=t,this}withHorizontalOrientation(t){return this._horizontal=t,this}withAllowedModifierKeys(t){return this._allowedModifierKeys=t,this}withTypeAhead(t=200){this._typeaheadSubscription.unsubscribe();let e=this._getItemsArray();return this._typeahead=new Ms(e,{debounceInterval:typeof t=="number"?t:void 0,skipPredicate:i=>this._skipPredicateFn(i)}),this._typeaheadSubscription=this._typeahead.selectedItem.subscribe(i=>{this.setActiveItem(i)}),this}cancelTypeahead(){return this._typeahead?.reset(),this}withHomeAndEnd(t=!0){return this._homeAndEnd=t,this}withPageUpDown(t=!0,e=10){return this._pageUpAndDown={enabled:t,delta:e},this}setActiveItem(t){let e=this._activeItem();this.updateActiveItem(t),this._activeItem()!==e&&this.change.next(this._activeItemIndex())}onKeydown(t){let e=t.keyCode,r=["altKey","ctrlKey","metaKey","shiftKey"].every(o=>!t[o]||this._allowedModifierKeys.indexOf(o)>-1);switch(e){case 9:this.tabOut.next();return;case 40:if(this._vertical&&r){this.setNextItemActive();break}else return;case 38:if(this._vertical&&r){this.setPreviousItemActive();break}else return;case 39:if(this._horizontal&&r){this._horizontal==="rtl"?this.setPreviousItemActive():this.setNextItemActive();break}else return;case 37:if(this._horizontal&&r){this._horizontal==="rtl"?this.setNextItemActive():this.setPreviousItemActive();break}else return;case 36:if(this._homeAndEnd&&r){this.setFirstItemActive();break}else return;case 35:if(this._homeAndEnd&&r){this.setLastItemActive();break}else return;case 33:if(this._pageUpAndDown.enabled&&r){let o=this._activeItemIndex()-this._pageUpAndDown.delta;this._setActiveItemByIndex(o>0?o:0,1);break}else return;case 34:if(this._pageUpAndDown.enabled&&r){let o=this._activeItemIndex()+this._pageUpAndDown.delta,a=this._getItemsArray().length;this._setActiveItemByIndex(o<a?o:a-1,-1);break}else return;default:(r||oi(t,"shiftKey"))&&this._typeahead?.handleKey(t);return}this._typeahead?.reset(),t.preventDefault()}get activeItemIndex(){return this._activeItemIndex()}get activeItem(){return this._activeItem()}isTyping(){return!!this._typeahead&&this._typeahead.isTyping()}setFirstItemActive(){this._setActiveItemByIndex(0,1)}setLastItemActive(){this._setActiveItemByIndex(this._getItemsArray().length-1,-1)}setNextItemActive(){this._activeItemIndex()<0?this.setFirstItemActive():this._setActiveItemByDelta(1)}setPreviousItemActive(){this._activeItemIndex()<0&&this._wrap?this.setLastItemActive():this._setActiveItemByDelta(-1)}updateActiveItem(t){let e=this._getItemsArray(),i=typeof t=="number"?t:e.indexOf(t),r=e[i];this._activeItem.set(r??null),this._activeItemIndex.set(i),this._typeahead?.setCurrentSelectedItemIndex(i)}destroy(){this._typeaheadSubscription.unsubscribe(),this._itemChangesSubscription?.unsubscribe(),this._effectRef?.destroy(),this._typeahead?.destroy(),this.tabOut.complete(),this.change.complete()}_setActiveItemByDelta(t){this._wrap?this._setActiveInWrapMode(t):this._setActiveInDefaultMode(t)}_setActiveInWrapMode(t){let e=this._getItemsArray();for(let i=1;i<=e.length;i++){let r=(this._activeItemIndex()+t*i+e.length)%e.length,o=e[r];if(!this._skipPredicateFn(o)){this.setActiveItem(r);return}}}_setActiveInDefaultMode(t){this._setActiveItemByIndex(this._activeItemIndex()+t,t)}_setActiveItemByIndex(t,e){let i=this._getItemsArray();if(i[t]){for(;this._skipPredicateFn(i[t]);)if(t+=e,!i[t])return;this.setActiveItem(t)}}_getItemsArray(){return nr(this._items)?this._items():this._items instanceof In?this._items.toArray():this._items}_itemsChanged(t){this._typeahead?.setItems(t);let e=this._activeItem();if(e){let i=t.indexOf(e);i>-1&&i!==this._activeItemIndex()&&(this._activeItemIndex.set(i),this._typeahead?.setCurrentSelectedItemIndex(i))}}};var Mi=class extends As{_origin="program";setFocusOrigin(t){return this._origin=t,this}setActiveItem(t){super.setActiveItem(t),this.activeItem&&this.activeItem.focus(this._origin)}};var od={},ut=class n{_appId=m(oo);static _infix=`a${Math.floor(Math.random()*1e5).toString()}`;getId(t,e=!1){return this._appId!=="ng"&&(t+=this._appId),od.hasOwnProperty(t)||(od[t]=0),`${t}${e?n._infix+"-":""}${od[t]++}`}static \u0275fac=function(e){return new(e||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})};var rf={XSmall:"(max-width: 599.98px)",Small:"(min-width: 600px) and (max-width: 959.98px)",Medium:"(min-width: 960px) and (max-width: 1279.98px)",Large:"(min-width: 1280px) and (max-width: 1919.98px)",XLarge:"(min-width: 1920px)",Handset:"(max-width: 599.98px) and (orientation: portrait), (max-width: 959.98px) and (orientation: landscape)",Tablet:"(min-width: 600px) and (max-width: 839.98px) and (orientation: portrait), (min-width: 960px) and (max-width: 1279.98px) and (orientation: landscape)",Web:"(min-width: 840px) and (orientation: portrait), (min-width: 1280px) and (orientation: landscape)",HandsetPortrait:"(max-width: 599.98px) and (orientation: portrait)",TabletPortrait:"(min-width: 600px) and (max-width: 839.98px) and (orientation: portrait)",WebPortrait:"(min-width: 840px) and (orientation: portrait)",HandsetLandscape:"(max-width: 959.98px) and (orientation: landscape)",TabletLandscape:"(min-width: 960px) and (max-width: 1279.98px) and (orientation: landscape)",WebLandscape:"(min-width: 1280px) and (orientation: landscape)"};function ad(){return typeof __karma__<"u"&&!!__karma__||typeof jasmine<"u"&&!!jasmine||typeof jest<"u"&&!!jest||typeof Mocha<"u"&&!!Mocha}function Ge(n){return n==null?"":typeof n=="string"?n:`${n}px`}var rn=(function(n){return n[n.NORMAL=0]="NORMAL",n[n.NEGATED=1]="NEGATED",n[n.INVERTED=2]="INVERTED",n})(rn||{}),Is,Ai;function Ts(){if(Ai==null){if(typeof document!="object"||!document||typeof Element!="function"||!Element)return Ai=!1,Ai;if(document.documentElement?.style&&"scrollBehavior"in document.documentElement.style)Ai=!0;else{let n=Element.prototype.scrollTo;n?Ai=!/\{\s*\[native code\]\s*\}/.test(n.toString()):Ai=!1}}return Ai}function hr(){if(typeof document!="object"||!document)return rn.NORMAL;if(Is==null){let n=document.createElement("div"),t=n.style;n.dir="rtl",t.width="1px",t.overflow="auto",t.visibility="hidden",t.pointerEvents="none",t.position="absolute";let e=document.createElement("div"),i=e.style;i.width="2px",i.height="1px",n.appendChild(e),document.body.appendChild(n),Is=rn.NORMAL,n.scrollLeft===0&&(n.scrollLeft=1,Is=n.scrollLeft===0?rn.NEGATED:rn.INVERTED),n.remove()}return Is}var cx=20,Ii=(()=>{class n{_ngZone=m(K);_platform=m(Ce);_renderer=m(bt).createRenderer(null,null);_cleanupGlobalListener;constructor(){}_scrolled=new z;_scrolledCount=0;scrollContainers=new Map;register(e){this.scrollContainers.has(e)||this.scrollContainers.set(e,e.elementScrolled().subscribe(()=>this._scrolled.next(e)))}deregister(e){let i=this.scrollContainers.get(e);i&&(i.unsubscribe(),this.scrollContainers.delete(e))}scrolled(e=cx){return this._platform.isBrowser?new St(i=>{this._cleanupGlobalListener||(this._cleanupGlobalListener=this._ngZone.runOutsideAngular(()=>this._renderer.listen("document","scroll",()=>this._scrolled.next())));let r=e>0?this._scrolled.pipe(rc(e)).subscribe(i):this._scrolled.subscribe(i);return this._scrolledCount++,()=>{r.unsubscribe(),this._scrolledCount--,this._scrolledCount||(this._cleanupGlobalListener?.(),this._cleanupGlobalListener=void 0)}}):Pe()}ngOnDestroy(){this._cleanupGlobalListener?.(),this._cleanupGlobalListener=void 0,this.scrollContainers.forEach((e,i)=>this.deregister(i)),this._scrolled.complete()}ancestorScrolled(e,i){let r=this.getAncestorScrollContainers(e);return this.scrolled(i).pipe(Be(o=>!o||r.indexOf(o)>-1))}getAncestorScrollContainers(e){let i=[];return this.scrollContainers.forEach((r,o)=>{this._scrollableContainsElement(o,e)&&i.push(o)}),i}_scrollableContainsElement(e,i){let r=Ut(i),o=e.getElementRef().nativeElement;do if(r==o)return!0;while(r=r.parentElement);return!1}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),fr=(()=>{class n{elementRef=m(Y);scrollDispatcher=m(Ii);ngZone=m(K);dir=m(wt,{optional:!0});_scrollElement=this.elementRef.nativeElement;_destroyed=new z;_renderer=m(rt);_cleanupScroll;_elementScrolled=new z;constructor(){}ngOnInit(){this._cleanupScroll=this.ngZone.runOutsideAngular(()=>this._renderer.listen(this._scrollElement,"scroll",e=>this._elementScrolled.next(e))),this.scrollDispatcher.register(this)}ngOnDestroy(){this._cleanupScroll?.(),this._elementScrolled.complete(),this.scrollDispatcher.deregister(this),this._destroyed.next(),this._destroyed.complete()}elementScrolled(){return this._elementScrolled}getElementRef(){return this.elementRef}scrollTo(e){let i=this.elementRef.nativeElement,r=this.dir&&this.dir.value=="rtl";e.left==null&&(e.left=r?e.end:e.start),e.right==null&&(e.right=r?e.start:e.end),e.bottom!=null&&(e.top=i.scrollHeight-i.clientHeight-e.bottom),r&&hr()!=rn.NORMAL?(e.left!=null&&(e.right=i.scrollWidth-i.clientWidth-e.left),hr()==rn.INVERTED?e.left=e.right:hr()==rn.NEGATED&&(e.left=e.right?-e.right:e.right)):e.right!=null&&(e.left=i.scrollWidth-i.clientWidth-e.right),this._applyScrollToOptions(e)}_applyScrollToOptions(e){let i=this.elementRef.nativeElement;Ts()?i.scrollTo(e):(e.top!=null&&(i.scrollTop=e.top),e.left!=null&&(i.scrollLeft=e.left))}measureScrollOffset(e){let i="left",r="right",o=this.elementRef.nativeElement;if(e=="top")return o.scrollTop;if(e=="bottom")return o.scrollHeight-o.clientHeight-o.scrollTop;let a=this.dir&&this.dir.value=="rtl";return e=="start"?e=a?r:i:e=="end"&&(e=a?i:r),a&&hr()==rn.INVERTED?e==i?o.scrollWidth-o.clientWidth-o.scrollLeft:o.scrollLeft:a&&hr()==rn.NEGATED?e==i?o.scrollLeft+o.scrollWidth-o.clientWidth:-o.scrollLeft:e==i?o.scrollLeft:o.scrollWidth-o.clientWidth-o.scrollLeft}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","cdk-scrollable",""],["","cdkScrollable",""]]})}return n})(),dx=20,Ln=(()=>{class n{_platform=m(Ce);_listeners;_viewportSize=null;_change=new z;_document=m(W);constructor(){let e=m(K),i=m(bt).createRenderer(null,null);e.runOutsideAngular(()=>{if(this._platform.isBrowser){let r=o=>this._change.next(o);this._listeners=[i.listen("window","resize",r),i.listen("window","orientationchange",r)]}this.change().subscribe(()=>this._viewportSize=null)})}ngOnDestroy(){this._listeners?.forEach(e=>e()),this._change.complete()}getViewportSize(){this._viewportSize||this._updateViewportSize();let e={width:this._viewportSize.width,height:this._viewportSize.height};return this._platform.isBrowser||(this._viewportSize=null),e}getViewportRect(){let e=this.getViewportScrollPosition(),{width:i,height:r}=this.getViewportSize();return{top:e.top,left:e.left,bottom:e.top+r,right:e.left+i,height:r,width:i}}getViewportScrollPosition(){if(!this._platform.isBrowser)return{top:0,left:0};let e=this._document,i=this._getWindow(),r=e.documentElement,o=r.getBoundingClientRect(),a=-o.top||e.body?.scrollTop||i.scrollY||r.scrollTop||0,s=-o.left||e.body?.scrollLeft||i.scrollX||r.scrollLeft||0;return{top:a,left:s}}change(e=dx){return e>0?this._change.pipe(rc(e)):this._change}_getWindow(){return this._document.defaultView||window}_updateViewportSize(){let e=this._getWindow();this._viewportSize=this._platform.isBrowser?{width:e.innerWidth,height:e.innerHeight}:{width:0,height:0}}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var Nn=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({})}return n})(),ld=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[ie,Nn,ie,Nn]})}return n})();var No=class{_attachedHost=null;attach(t){return this._attachedHost=t,t.attach(this)}detach(){let t=this._attachedHost;t!=null&&(this._attachedHost=null,t.detach())}get isAttached(){return this._attachedHost!=null}setAttachedHost(t){this._attachedHost=t}},gr=class extends No{component;viewContainerRef;injector;projectableNodes;bindings;constructor(t,e,i,r,o){super(),this.component=t,this.viewContainerRef=e,this.injector=i,this.projectableNodes=r,this.bindings=o||null}},Bn=class extends No{templateRef;viewContainerRef;context;injector;constructor(t,e,i,r){super(),this.templateRef=t,this.viewContainerRef=e,this.context=i,this.injector=r}get origin(){return this.templateRef.elementRef}attach(t,e=this.context){return this.context=e,super.attach(t)}detach(){return this.context=void 0,super.detach()}},cd=class extends No{element;constructor(t){super(),this.element=t instanceof Y?t.nativeElement:t}},_r=class{_attachedPortal=null;_disposeFn=null;_isDisposed=!1;hasAttached(){return!!this._attachedPortal}attach(t){if(t instanceof gr)return this._attachedPortal=t,this.attachComponentPortal(t);if(t instanceof Bn)return this._attachedPortal=t,this.attachTemplatePortal(t);if(this.attachDomPortal&&t instanceof cd)return this._attachedPortal=t,this.attachDomPortal(t)}attachDomPortal=null;detach(){this._attachedPortal&&(this._attachedPortal.setAttachedHost(null),this._attachedPortal=null),this._invokeDisposeFn()}dispose(){this.hasAttached()&&this.detach(),this._invokeDisposeFn(),this._isDisposed=!0}setDisposeFn(t){this._disposeFn=t}_invokeDisposeFn(){this._disposeFn&&(this._disposeFn(),this._disposeFn=null)}},Lo=class extends _r{outletElement;_appRef;_defaultInjector;constructor(t,e,i){super(),this.outletElement=t,this._appRef=e,this._defaultInjector=i}attachComponentPortal(t){let e;if(t.viewContainerRef){let i=t.injector||t.viewContainerRef.injector,r=i.get(ja,null,{optional:!0})||void 0;e=t.viewContainerRef.createComponent(t.component,{index:t.viewContainerRef.length,injector:i,ngModuleRef:r,projectableNodes:t.projectableNodes||void 0,bindings:t.bindings||void 0}),this.setDisposeFn(()=>e.destroy())}else{let i=this._appRef,r=t.injector||this._defaultInjector||te.NULL,o=r.get(An,i.injector);e=Ga(t.component,{elementInjector:r,environmentInjector:o,projectableNodes:t.projectableNodes||void 0,bindings:t.bindings||void 0}),i.attachView(e.hostView),this.setDisposeFn(()=>{i.viewCount>0&&i.detachView(e.hostView),e.destroy()})}return this.outletElement.appendChild(this._getComponentRootNode(e)),this._attachedPortal=t,e}attachTemplatePortal(t){let e=t.viewContainerRef,i=e.createEmbeddedView(t.templateRef,t.context,{injector:t.injector});return i.rootNodes.forEach(r=>this.outletElement.appendChild(r)),i.detectChanges(),this.setDisposeFn(()=>{let r=e.indexOf(i);r!==-1&&e.remove(r)}),this._attachedPortal=t,i}attachDomPortal=t=>{let e=t.element;e.parentNode;let i=this.outletElement.ownerDocument.createComment("dom-portal");e.parentNode.insertBefore(i,e),this.outletElement.appendChild(e),this._attachedPortal=t,super.setDisposeFn(()=>{i.parentNode&&i.parentNode.replaceChild(e,i)})};dispose(){super.dispose(),this.outletElement.remove()}_getComponentRootNode(t){return t.hostView.rootNodes[0]}};var Os=(()=>{class n extends _r{_moduleRef=m(ja,{optional:!0});_document=m(W);_viewContainerRef=m(pn);_isInitialized=!1;_attachedRef=null;constructor(){super()}get portal(){return this._attachedPortal}set portal(e){this.hasAttached()&&!e&&!this._isInitialized||(this.hasAttached()&&super.detach(),e&&super.attach(e),this._attachedPortal=e||null)}attached=new Ee;get attachedRef(){return this._attachedRef}ngOnInit(){this._isInitialized=!0}ngOnDestroy(){super.dispose(),this._attachedRef=this._attachedPortal=null}attachComponentPortal(e){e.setAttachedHost(this);let i=e.viewContainerRef!=null?e.viewContainerRef:this._viewContainerRef,r=i.createComponent(e.component,{index:i.length,injector:e.injector||i.injector,projectableNodes:e.projectableNodes||void 0,ngModuleRef:this._moduleRef||void 0,bindings:e.bindings||void 0});return i!==this._viewContainerRef&&this._getRootNode().appendChild(r.hostView.rootNodes[0]),super.setDisposeFn(()=>r.destroy()),this._attachedPortal=e,this._attachedRef=r,this.attached.emit(r),r}attachTemplatePortal(e){e.setAttachedHost(this);let i=this._viewContainerRef.createEmbeddedView(e.templateRef,e.context,{injector:e.injector});return super.setDisposeFn(()=>this._viewContainerRef.clear()),this._attachedPortal=e,this._attachedRef=i,this.attached.emit(i),i}attachDomPortal=e=>{let i=e.element;i.parentNode;let r=this._document.createComment("dom-portal");e.setAttachedHost(this),i.parentNode.insertBefore(r,i),this._getRootNode().appendChild(i),this._attachedPortal=e,super.setDisposeFn(()=>{r.parentNode&&r.parentNode.replaceChild(i,r)})};_getRootNode(){let e=this._viewContainerRef.element.nativeElement;return e.nodeType===e.ELEMENT_NODE?e:e.parentNode}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","cdkPortalOutlet",""]],inputs:{portal:[0,"cdkPortalOutlet","portal"]},outputs:{attached:"attached"},exportAs:["cdkPortalOutlet"],features:[Te]})}return n})(),br=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({})}return n})();var of=Ts();function uf(n){return new Rs(n.get(Ln),n.get(W))}var Rs=class{_viewportRuler;_previousHTMLStyles={top:"",left:""};_previousScrollPosition;_isEnabled=!1;_document;constructor(t,e){this._viewportRuler=t,this._document=e}attach(){}enable(){if(this._canBeEnabled()){let t=this._document.documentElement;this._previousScrollPosition=this._viewportRuler.getViewportScrollPosition(),this._previousHTMLStyles.left=t.style.left||"",this._previousHTMLStyles.top=t.style.top||"",t.style.left=Ge(-this._previousScrollPosition.left),t.style.top=Ge(-this._previousScrollPosition.top),t.classList.add("cdk-global-scrollblock"),this._isEnabled=!0}}disable(){if(this._isEnabled){let t=this._document.documentElement,e=this._document.body,i=t.style,r=e.style,o=i.scrollBehavior||"",a=r.scrollBehavior||"";this._isEnabled=!1,i.left=this._previousHTMLStyles.left,i.top=this._previousHTMLStyles.top,t.classList.remove("cdk-global-scrollblock"),of&&(i.scrollBehavior=r.scrollBehavior="auto"),window.scroll(this._previousScrollPosition.left,this._previousScrollPosition.top),of&&(i.scrollBehavior=o,r.scrollBehavior=a)}}_canBeEnabled(){if(this._document.documentElement.classList.contains("cdk-global-scrollblock")||this._isEnabled)return!1;let e=this._document.documentElement,i=this._viewportRuler.getViewportSize();return e.scrollHeight>i.height||e.scrollWidth>i.width}};function pf(n,t){return new Ps(n.get(Ii),n.get(K),n.get(Ln),t)}var Ps=class{_scrollDispatcher;_ngZone;_viewportRuler;_config;_scrollSubscription=null;_overlayRef;_initialScrollPosition;constructor(t,e,i,r){this._scrollDispatcher=t,this._ngZone=e,this._viewportRuler=i,this._config=r}attach(t){this._overlayRef,this._overlayRef=t}enable(){if(this._scrollSubscription)return;let t=this._scrollDispatcher.scrolled(0).pipe(Be(e=>!e||!this._overlayRef.overlayElement.contains(e.getElementRef().nativeElement)));this._config&&this._config.threshold&&this._config.threshold>1?(this._initialScrollPosition=this._viewportRuler.getViewportScrollPosition().top,this._scrollSubscription=t.subscribe(()=>{let e=this._viewportRuler.getViewportScrollPosition().top;Math.abs(e-this._initialScrollPosition)>this._config.threshold?this._detach():this._overlayRef.updatePosition()})):this._scrollSubscription=t.subscribe(this._detach)}disable(){this._scrollSubscription&&(this._scrollSubscription.unsubscribe(),this._scrollSubscription=null)}detach(){this.disable(),this._overlayRef=null}_detach=()=>{this.disable(),this._overlayRef.hasAttached()&&this._ngZone.run(()=>this._overlayRef.detach())}};var Bo=class{enable(){}disable(){}attach(){}};function dd(n,t){return t.some(e=>{let i=n.bottom<e.top,r=n.top>e.bottom,o=n.right<e.left,a=n.left>e.right;return i||r||o||a})}function af(n,t){return t.some(e=>{let i=n.top<e.top,r=n.bottom>e.bottom,o=n.left<e.left,a=n.right>e.right;return i||r||o||a})}function $o(n,t){return new Fs(n.get(Ii),n.get(Ln),n.get(K),t)}var Fs=class{_scrollDispatcher;_viewportRuler;_ngZone;_config;_scrollSubscription=null;_overlayRef;constructor(t,e,i,r){this._scrollDispatcher=t,this._viewportRuler=e,this._ngZone=i,this._config=r}attach(t){this._overlayRef,this._overlayRef=t}enable(){if(!this._scrollSubscription){let t=this._config?this._config.scrollThrottle:0;this._scrollSubscription=this._scrollDispatcher.scrolled(t).subscribe(()=>{if(this._overlayRef.updatePosition(),this._config&&this._config.autoClose){let e=this._overlayRef.overlayElement.getBoundingClientRect(),{width:i,height:r}=this._viewportRuler.getViewportSize();dd(e,[{width:i,height:r,bottom:r,right:i,top:0,left:0}])&&(this.disable(),this._ngZone.run(()=>this._overlayRef.detach()))}})}}disable(){this._scrollSubscription&&(this._scrollSubscription.unsubscribe(),this._scrollSubscription=null)}detach(){this.disable(),this._overlayRef=null}},hf=(()=>{class n{_injector=m(te);constructor(){}noop=()=>new Bo;close=e=>pf(this._injector,e);block=()=>uf(this._injector);reposition=e=>$o(this._injector,e);static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),ai=class{positionStrategy;scrollStrategy=new Bo;panelClass="";hasBackdrop=!1;backdropClass="cdk-overlay-dark-backdrop";disableAnimations;width;height;minWidth;minHeight;maxWidth;maxHeight;direction;disposeOnNavigation=!1;usePopover;eventPredicate;constructor(t){if(t){let e=Object.keys(t);for(let i of e)t[i]!==void 0&&(this[i]=t[i])}}};var Ns=class{connectionPair;scrollableViewProperties;constructor(t,e){this.connectionPair=t,this.scrollableViewProperties=e}};var ff=(()=>{class n{_attachedOverlays=[];_document=m(W);_isAttached=!1;constructor(){}ngOnDestroy(){this.detach()}add(e){this.remove(e),this._attachedOverlays.push(e)}remove(e){let i=this._attachedOverlays.indexOf(e);i>-1&&this._attachedOverlays.splice(i,1),this._attachedOverlays.length===0&&this.detach()}canReceiveEvent(e,i,r){return r.observers.length<1?!1:e.eventPredicate?e.eventPredicate(i):!0}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),gf=(()=>{class n extends ff{_ngZone=m(K);_renderer=m(bt).createRenderer(null,null);_cleanupKeydown;add(e){super.add(e),this._isAttached||(this._ngZone.runOutsideAngular(()=>{this._cleanupKeydown=this._renderer.listen("body","keydown",this._keydownListener)}),this._isAttached=!0)}detach(){this._isAttached&&(this._cleanupKeydown?.(),this._isAttached=!1)}_keydownListener=e=>{let i=this._attachedOverlays;for(let r=i.length-1;r>-1;r--){let o=i[r];if(this.canReceiveEvent(o,e,o._keydownEvents)){this._ngZone.run(()=>o._keydownEvents.next(e));break}}};static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(n)))(r||n)}})();static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),_f=(()=>{class n extends ff{_platform=m(Ce);_ngZone=m(K);_renderer=m(bt).createRenderer(null,null);_cursorOriginalValue;_cursorStyleIsSet=!1;_pointerDownEventTarget=null;_cleanups;add(e){if(super.add(e),!this._isAttached){let i=this._document.body,r={capture:!0},o=this._renderer;this._cleanups=this._ngZone.runOutsideAngular(()=>[o.listen(i,"pointerdown",this._pointerDownListener,r),o.listen(i,"click",this._clickListener,r),o.listen(i,"auxclick",this._clickListener,r),o.listen(i,"contextmenu",this._clickListener,r)]),this._platform.IOS&&!this._cursorStyleIsSet&&(this._cursorOriginalValue=i.style.cursor,i.style.cursor="pointer",this._cursorStyleIsSet=!0),this._isAttached=!0}}detach(){this._isAttached&&(this._cleanups?.forEach(e=>e()),this._cleanups=void 0,this._platform.IOS&&this._cursorStyleIsSet&&(this._document.body.style.cursor=this._cursorOriginalValue,this._cursorStyleIsSet=!1),this._isAttached=!1)}_pointerDownListener=e=>{this._pointerDownEventTarget=At(e)};_clickListener=e=>{let i=At(e),r=e.type==="click"&&this._pointerDownEventTarget?this._pointerDownEventTarget:i;this._pointerDownEventTarget=null;let o=this._attachedOverlays.slice();for(let a=o.length-1;a>-1;a--){let s=o[a],l=s._outsidePointerEvents;if(!(!s.hasAttached()||!this.canReceiveEvent(s,e,l))){if(sf(s.overlayElement,i)||sf(s.overlayElement,r))break;this._ngZone?this._ngZone.run(()=>l.next(e)):l.next(e)}}};static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(n)))(r||n)}})();static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();function sf(n,t){let e=typeof ShadowRoot<"u"&&ShadowRoot,i=t;for(;i;){if(i===n)return!0;i=e&&i instanceof ShadowRoot?i.host:i.parentNode}return!1}var bf=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["ng-component"]],hostAttrs:["cdk-overlay-style-loader",""],decls:0,vars:0,template:function(i,r){},styles:[`.cdk-overlay-container, .cdk-global-overlay-wrapper {
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
`],encapsulation:2,changeDetection:0})}return n})(),vf=(()=>{class n{_platform=m(Ce);_containerElement;_document=m(W);_styleLoader=m(Je);constructor(){}ngOnDestroy(){this._containerElement?.remove()}getContainerElement(){return this._loadStyles(),this._containerElement||this._createContainer(),this._containerElement}_createContainer(){let e="cdk-overlay-container";if(this._platform.isBrowser||ad()){let r=this._document.querySelectorAll(`.${e}[platform="server"], .${e}[platform="test"]`);for(let o=0;o<r.length;o++)r[o].remove()}let i=this._document.createElement("div");i.classList.add(e),ad()?i.setAttribute("platform","test"):this._platform.isBrowser||i.setAttribute("platform","server"),this._document.body.appendChild(i),this._containerElement=i}_loadStyles(){this._styleLoader.load(bf)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),md=class{_renderer;_ngZone;element;_cleanupClick;_cleanupTransitionEnd;_fallbackTimeout;constructor(t,e,i,r){this._renderer=e,this._ngZone=i,this.element=t.createElement("div"),this.element.classList.add("cdk-overlay-backdrop"),this._cleanupClick=e.listen(this.element,"click",r)}detach(){this._ngZone.runOutsideAngular(()=>{let t=this.element;clearTimeout(this._fallbackTimeout),this._cleanupTransitionEnd?.(),this._cleanupTransitionEnd=this._renderer.listen(t,"transitionend",this.dispose),this._fallbackTimeout=setTimeout(this.dispose,500),t.style.pointerEvents="none",t.classList.remove("cdk-overlay-backdrop-showing")})}dispose=()=>{clearTimeout(this._fallbackTimeout),this._cleanupClick?.(),this._cleanupTransitionEnd?.(),this._cleanupClick=this._cleanupTransitionEnd=this._fallbackTimeout=void 0,this.element.remove()}};function ud(n){return n&&n.nodeType===1}var Ls=class{_portalOutlet;_host;_pane;_config;_ngZone;_keyboardDispatcher;_document;_location;_outsideClickDispatcher;_animationsDisabled;_injector;_renderer;_backdropClick=new z;_attachments=new z;_detachments=new z;_positionStrategy;_scrollStrategy;_locationChanges=lt.EMPTY;_backdropRef=null;_detachContentMutationObserver;_detachContentAfterRenderRef;_disposed=!1;_previousHostParent;_keydownEvents=new z;_outsidePointerEvents=new z;_afterNextRenderRef;constructor(t,e,i,r,o,a,s,l,d,c=!1,u,h){this._portalOutlet=t,this._host=e,this._pane=i,this._config=r,this._ngZone=o,this._keyboardDispatcher=a,this._document=s,this._location=l,this._outsideClickDispatcher=d,this._animationsDisabled=c,this._injector=u,this._renderer=h,r.scrollStrategy&&(this._scrollStrategy=r.scrollStrategy,this._scrollStrategy.attach(this)),this._positionStrategy=r.positionStrategy}get overlayElement(){return this._pane}get backdropElement(){return this._backdropRef?.element||null}get hostElement(){return this._host}get eventPredicate(){return this._config?.eventPredicate||null}attach(t){if(this._disposed)return null;this._attachHost();let e=this._portalOutlet.attach(t);return this._positionStrategy?.attach(this),this._updateStackingOrder(),this._updateElementSize(),this._updateElementDirection(),this._scrollStrategy&&this._scrollStrategy.enable(),this._afterNextRenderRef?.destroy(),this._afterNextRenderRef=mt(()=>{this.hasAttached()&&this.updatePosition()},{injector:this._injector}),this._togglePointerEvents(!0),this._config.hasBackdrop&&this._attachBackdrop(),this._config.panelClass&&this._toggleClasses(this._pane,this._config.panelClass,!0),this._attachments.next(),this._completeDetachContent(),this._keyboardDispatcher.add(this),this._config.disposeOnNavigation&&(this._locationChanges=this._location.subscribe(()=>this.dispose())),this._outsideClickDispatcher.add(this),typeof e?.onDestroy=="function"&&e.onDestroy(()=>{this.hasAttached()&&this._ngZone.runOutsideAngular(()=>Promise.resolve().then(()=>this.detach()))}),e}detach(){if(!this.hasAttached())return;this.detachBackdrop(),this._togglePointerEvents(!1),this._positionStrategy&&this._positionStrategy.detach&&this._positionStrategy.detach(),this._scrollStrategy&&this._scrollStrategy.disable();let t=this._portalOutlet.detach();return this._detachments.next(),this._completeDetachContent(),this._keyboardDispatcher.remove(this),this._detachContentWhenEmpty(),this._locationChanges.unsubscribe(),this._outsideClickDispatcher.remove(this),t}dispose(){if(this._disposed)return;let t=this.hasAttached();this._positionStrategy&&this._positionStrategy.dispose(),this._disposeScrollStrategy(),this._backdropRef?.dispose(),this._locationChanges.unsubscribe(),this._keyboardDispatcher.remove(this),this._portalOutlet.dispose(),this._attachments.complete(),this._backdropClick.complete(),this._keydownEvents.complete(),this._outsidePointerEvents.complete(),this._outsideClickDispatcher.remove(this),this._host?.remove(),this._afterNextRenderRef?.destroy(),this._previousHostParent=this._pane=this._host=this._backdropRef=null,t&&this._detachments.next(),this._detachments.complete(),this._completeDetachContent(),this._disposed=!0}hasAttached(){return this._portalOutlet.hasAttached()}backdropClick(){return this._backdropClick}attachments(){return this._attachments}detachments(){return this._detachments}keydownEvents(){return this._keydownEvents}outsidePointerEvents(){return this._outsidePointerEvents}getConfig(){return this._config}updatePosition(){this._positionStrategy&&this._positionStrategy.apply()}updatePositionStrategy(t){t!==this._positionStrategy&&(this._positionStrategy&&this._positionStrategy.dispose(),this._positionStrategy=t,this.hasAttached()&&(t.attach(this),this.updatePosition()))}updateSize(t){this._config=S(S({},this._config),t),this._updateElementSize()}setDirection(t){this._config=ye(S({},this._config),{direction:t}),this._updateElementDirection()}addPanelClass(t){this._pane&&this._toggleClasses(this._pane,t,!0)}removePanelClass(t){this._pane&&this._toggleClasses(this._pane,t,!1)}getDirection(){let t=this._config.direction;return t?typeof t=="string"?t:t.value:"ltr"}updateScrollStrategy(t){t!==this._scrollStrategy&&(this._disposeScrollStrategy(),this._scrollStrategy=t,this.hasAttached()&&(t.attach(this),t.enable()))}_updateElementDirection(){this._host.setAttribute("dir",this.getDirection())}_updateElementSize(){if(!this._pane)return;let t=this._pane.style;t.width=Ge(this._config.width),t.height=Ge(this._config.height),t.minWidth=Ge(this._config.minWidth),t.minHeight=Ge(this._config.minHeight),t.maxWidth=Ge(this._config.maxWidth),t.maxHeight=Ge(this._config.maxHeight)}_togglePointerEvents(t){this._pane.style.pointerEvents=t?"":"none"}_attachHost(){if(!this._host.parentElement){let t=this._config.usePopover?this._positionStrategy?.getPopoverInsertionPoint?.():null;ud(t)?t.after(this._host):t?.type==="parent"?t.element.appendChild(this._host):this._previousHostParent?.appendChild(this._host)}if(this._config.usePopover)try{this._host.showPopover()}catch{}}_attachBackdrop(){let t="cdk-overlay-backdrop-showing";this._backdropRef?.dispose(),this._backdropRef=new md(this._document,this._renderer,this._ngZone,e=>{this._backdropClick.next(e)}),this._animationsDisabled&&this._backdropRef.element.classList.add("cdk-overlay-backdrop-noop-animation"),this._config.backdropClass&&this._toggleClasses(this._backdropRef.element,this._config.backdropClass,!0),this._config.usePopover?this._host.prepend(this._backdropRef.element):this._host.parentElement.insertBefore(this._backdropRef.element,this._host),!this._animationsDisabled&&typeof requestAnimationFrame<"u"?this._ngZone.runOutsideAngular(()=>{requestAnimationFrame(()=>this._backdropRef?.element.classList.add(t))}):this._backdropRef.element.classList.add(t)}_updateStackingOrder(){!this._config.usePopover&&this._host.nextSibling&&this._host.parentNode.appendChild(this._host)}detachBackdrop(){this._animationsDisabled?(this._backdropRef?.dispose(),this._backdropRef=null):this._backdropRef?.detach()}_toggleClasses(t,e,i){let r=pr(e||[]).filter(o=>!!o);r.length&&(i?t.classList.add(...r):t.classList.remove(...r))}_detachContentWhenEmpty(){let t=!1;try{this._detachContentAfterRenderRef=mt(()=>{t=!0,this._detachContent()},{injector:this._injector})}catch(e){if(t)throw e;this._detachContent()}globalThis.MutationObserver&&this._pane&&(this._detachContentMutationObserver||=new globalThis.MutationObserver(()=>{this._detachContent()}),this._detachContentMutationObserver.observe(this._pane,{childList:!0}))}_detachContent(){(!this._pane||!this._host||this._pane.children.length===0)&&(this._pane&&this._config.panelClass&&this._toggleClasses(this._pane,this._config.panelClass,!1),this._host&&this._host.parentElement&&(this._previousHostParent=this._host.parentElement,this._host.remove()),this._completeDetachContent())}_completeDetachContent(){this._detachContentAfterRenderRef?.destroy(),this._detachContentAfterRenderRef=void 0,this._detachContentMutationObserver?.disconnect()}_disposeScrollStrategy(){let t=this._scrollStrategy;t?.disable(),t?.detach?.()}},lf="cdk-overlay-connected-position-bounding-box",mx=/([A-Za-z%]+)$/;function js(n,t){return new Bs(t,n.get(Ln),n.get(W),n.get(Ce),n.get(vf))}var Bs=class{_viewportRuler;_document;_platform;_overlayContainer;_overlayRef;_isInitialRender=!1;_lastBoundingBoxSize={width:0,height:0};_isPushed=!1;_canPush=!0;_growAfterOpen=!1;_hasFlexibleDimensions=!0;_positionLocked=!1;_originRect;_overlayRect;_viewportRect;_containerRect;_viewportMargin=0;_scrollables=[];_preferredPositions=[];_origin;_pane;_isDisposed=!1;_boundingBox=null;_lastPosition=null;_lastScrollVisibility=null;_positionChanges=new z;_resizeSubscription=lt.EMPTY;_offsetX=0;_offsetY=0;_transformOriginSelector;_appliedPanelClasses=[];_previousPushAmount=null;_popoverLocation="global";positionChanges=this._positionChanges;get positions(){return this._preferredPositions}constructor(t,e,i,r,o){this._viewportRuler=e,this._document=i,this._platform=r,this._overlayContainer=o,this.setOrigin(t)}attach(t){this._overlayRef&&this._overlayRef,this._validatePositions(),t.hostElement.classList.add(lf),this._overlayRef=t,this._boundingBox=t.hostElement,this._pane=t.overlayElement,this._isDisposed=!1,this._isInitialRender=!0,this._lastPosition=null,this._resizeSubscription.unsubscribe(),this._resizeSubscription=this._viewportRuler.change().subscribe(()=>{this._isInitialRender=!0,this.apply()})}apply(){if(this._isDisposed||!this._platform.isBrowser)return;if(!this._isInitialRender&&this._positionLocked&&this._lastPosition){this.reapplyLastPosition();return}this._clearPanelClasses(),this._resetOverlayElementStyles(),this._resetBoundingBoxStyles(),this._viewportRect=this._getNarrowedViewportRect(),this._originRect=this._getOriginRect(),this._overlayRect=this._pane.getBoundingClientRect(),this._containerRect=this._getContainerRect();let t=this._originRect,e=this._overlayRect,i=this._viewportRect,r=this._containerRect,o=[],a;for(let s of this._preferredPositions){let l=this._getOriginPoint(t,r,s),d=this._getOverlayPoint(l,e,s),c=this._getOverlayFit(d,e,i,s);if(c.isCompletelyWithinViewport){this._isPushed=!1,this._applyPosition(s,l);return}if(this._canFitWithFlexibleDimensions(c,d,i)){o.push({position:s,origin:l,overlayRect:e,boundingBoxRect:this._calculateBoundingBoxRect(l,s)});continue}(!a||a.overlayFit.visibleArea<c.visibleArea)&&(a={overlayFit:c,overlayPoint:d,originPoint:l,position:s,overlayRect:e})}if(o.length){let s=null,l=-1;for(let d of o){let c=d.boundingBoxRect.width*d.boundingBoxRect.height*(d.position.weight||1);c>l&&(l=c,s=d)}this._isPushed=!1,this._applyPosition(s.position,s.origin);return}if(this._canPush){this._isPushed=!0,this._applyPosition(a.position,a.originPoint);return}this._applyPosition(a.position,a.originPoint)}detach(){this._clearPanelClasses(),this._lastPosition=null,this._previousPushAmount=null,this._resizeSubscription.unsubscribe()}dispose(){this._isDisposed||(this._boundingBox&&Ti(this._boundingBox.style,{top:"",left:"",right:"",bottom:"",height:"",width:"",alignItems:"",justifyContent:""}),this._pane&&this._resetOverlayElementStyles(),this._overlayRef&&this._overlayRef.hostElement.classList.remove(lf),this.detach(),this._positionChanges.complete(),this._overlayRef=this._boundingBox=null,this._isDisposed=!0)}reapplyLastPosition(){if(this._isDisposed||!this._platform.isBrowser)return;let t=this._lastPosition;t?(this._originRect=this._getOriginRect(),this._overlayRect=this._pane.getBoundingClientRect(),this._viewportRect=this._getNarrowedViewportRect(),this._containerRect=this._getContainerRect(),this._applyPosition(t,this._getOriginPoint(this._originRect,this._containerRect,t))):this.apply()}withScrollableContainers(t){return this._scrollables=t,this}withPositions(t){return this._preferredPositions=t,t.indexOf(this._lastPosition)===-1&&(this._lastPosition=null),this._validatePositions(),this}withViewportMargin(t){return this._viewportMargin=t,this}withFlexibleDimensions(t=!0){return this._hasFlexibleDimensions=t,this}withGrowAfterOpen(t=!0){return this._growAfterOpen=t,this}withPush(t=!0){return this._canPush=t,this}withLockedPosition(t=!0){return this._positionLocked=t,this}setOrigin(t){return this._origin=t,this}withDefaultOffsetX(t){return this._offsetX=t,this}withDefaultOffsetY(t){return this._offsetY=t,this}withTransformOriginOn(t){return this._transformOriginSelector=t,this}withPopoverLocation(t){return this._popoverLocation=t,this}getPopoverInsertionPoint(){return this._popoverLocation==="global"?null:this._popoverLocation!=="inline"?this._popoverLocation:this._origin instanceof Y?this._origin.nativeElement:ud(this._origin)?this._origin:null}_getOriginPoint(t,e,i){let r;if(i.originX=="center")r=t.left+t.width/2;else{let a=this._isRtl()?t.right:t.left,s=this._isRtl()?t.left:t.right;r=i.originX=="start"?a:s}e.left<0&&(r-=e.left);let o;return i.originY=="center"?o=t.top+t.height/2:o=i.originY=="top"?t.top:t.bottom,e.top<0&&(o-=e.top),{x:r,y:o}}_getOverlayPoint(t,e,i){let r;i.overlayX=="center"?r=-e.width/2:i.overlayX==="start"?r=this._isRtl()?-e.width:0:r=this._isRtl()?0:-e.width;let o;return i.overlayY=="center"?o=-e.height/2:o=i.overlayY=="top"?0:-e.height,{x:t.x+r,y:t.y+o}}_getOverlayFit(t,e,i,r){let o=df(e),{x:a,y:s}=t,l=this._getOffset(r,"x"),d=this._getOffset(r,"y");l&&(a+=l),d&&(s+=d);let c=0-a,u=a+o.width-i.width,h=0-s,y=s+o.height-i.height,_=this._subtractOverflows(o.width,c,u),b=this._subtractOverflows(o.height,h,y),v=_*b;return{visibleArea:v,isCompletelyWithinViewport:o.width*o.height===v,fitsInViewportVertically:b===o.height,fitsInViewportHorizontally:_==o.width}}_canFitWithFlexibleDimensions(t,e,i){if(this._hasFlexibleDimensions){let r=i.bottom-e.y,o=i.right-e.x,a=cf(this._overlayRef.getConfig().minHeight),s=cf(this._overlayRef.getConfig().minWidth),l=t.fitsInViewportVertically||a!=null&&a<=r,d=t.fitsInViewportHorizontally||s!=null&&s<=o;return l&&d}return!1}_pushOverlayOnScreen(t,e,i){if(this._previousPushAmount&&this._positionLocked)return{x:t.x+this._previousPushAmount.x,y:t.y+this._previousPushAmount.y};let r=df(e),o=this._viewportRect,a=Math.max(t.x+r.width-o.width,0),s=Math.max(t.y+r.height-o.height,0),l=Math.max(o.top-i.top-t.y,0),d=Math.max(o.left-i.left-t.x,0),c=0,u=0;return r.width<=o.width?c=d||-a:c=t.x<this._getViewportMarginStart()?o.left-i.left-t.x:0,r.height<=o.height?u=l||-s:u=t.y<this._getViewportMarginTop()?o.top-i.top-t.y:0,this._previousPushAmount={x:c,y:u},{x:t.x+c,y:t.y+u}}_applyPosition(t,e){if(this._setTransformOrigin(t),this._setOverlayElementStyles(e,t),this._setBoundingBoxStyles(e,t),t.panelClass&&this._addPanelClasses(t.panelClass),this._positionChanges.observers.length){let i=this._getScrollVisibility();if(t!==this._lastPosition||!this._lastScrollVisibility||!ux(this._lastScrollVisibility,i)){let r=new Ns(t,i);this._positionChanges.next(r)}this._lastScrollVisibility=i}this._lastPosition=t,this._isInitialRender=!1}_setTransformOrigin(t){if(!this._transformOriginSelector)return;let e=this._boundingBox.querySelectorAll(this._transformOriginSelector),i,r=t.overlayY;t.overlayX==="center"?i="center":this._isRtl()?i=t.overlayX==="start"?"right":"left":i=t.overlayX==="start"?"left":"right";for(let o=0;o<e.length;o++)e[o].style.transformOrigin=`${i} ${r}`}_calculateBoundingBoxRect(t,e){let i=this._viewportRect,r=this._isRtl(),o,a,s;if(e.overlayY==="top")a=t.y,o=i.height-a+this._getViewportMarginBottom();else if(e.overlayY==="bottom")s=i.height-t.y+this._getViewportMarginTop()+this._getViewportMarginBottom(),o=i.height-s+this._getViewportMarginTop();else{let y=Math.min(i.bottom-t.y+i.top,t.y),_=this._lastBoundingBoxSize.height;o=y*2,a=t.y-y,o>_&&!this._isInitialRender&&!this._growAfterOpen&&(a=t.y-_/2)}let l=e.overlayX==="start"&&!r||e.overlayX==="end"&&r,d=e.overlayX==="end"&&!r||e.overlayX==="start"&&r,c,u,h;if(d)h=i.width-t.x+this._getViewportMarginStart()+this._getViewportMarginEnd(),c=t.x-this._getViewportMarginStart();else if(l)u=t.x,c=i.right-t.x-this._getViewportMarginEnd();else{let y=Math.min(i.right-t.x+i.left,t.x),_=this._lastBoundingBoxSize.width;c=y*2,u=t.x-y,c>_&&!this._isInitialRender&&!this._growAfterOpen&&(u=t.x-_/2)}return{top:a,left:u,bottom:s,right:h,width:c,height:o}}_setBoundingBoxStyles(t,e){let i=this._calculateBoundingBoxRect(t,e);!this._isInitialRender&&!this._growAfterOpen&&(i.height=Math.min(i.height,this._lastBoundingBoxSize.height),i.width=Math.min(i.width,this._lastBoundingBoxSize.width));let r={};if(this._hasExactPosition())r.top=r.left="0",r.bottom=r.right="auto",r.maxHeight=r.maxWidth="",r.width=r.height="100%";else{let o=this._overlayRef.getConfig().maxHeight,a=this._overlayRef.getConfig().maxWidth;r.width=Ge(i.width),r.height=Ge(i.height),r.top=Ge(i.top)||"auto",r.bottom=Ge(i.bottom)||"auto",r.left=Ge(i.left)||"auto",r.right=Ge(i.right)||"auto",e.overlayX==="center"?r.alignItems="center":r.alignItems=e.overlayX==="end"?"flex-end":"flex-start",e.overlayY==="center"?r.justifyContent="center":r.justifyContent=e.overlayY==="bottom"?"flex-end":"flex-start",o&&(r.maxHeight=Ge(o)),a&&(r.maxWidth=Ge(a))}this._lastBoundingBoxSize=i,Ti(this._boundingBox.style,r)}_resetBoundingBoxStyles(){Ti(this._boundingBox.style,{top:"0",left:"0",right:"0",bottom:"0",height:"",width:"",alignItems:"",justifyContent:""})}_resetOverlayElementStyles(){Ti(this._pane.style,{top:"",left:"",bottom:"",right:"",position:"",transform:""})}_setOverlayElementStyles(t,e){let i={},r=this._hasExactPosition(),o=this._hasFlexibleDimensions,a=this._overlayRef.getConfig();if(r){let c=this._viewportRuler.getViewportScrollPosition();Ti(i,this._getExactOverlayY(e,t,c)),Ti(i,this._getExactOverlayX(e,t,c))}else i.position="static";let s="",l=this._getOffset(e,"x"),d=this._getOffset(e,"y");l&&(s+=`translateX(${l}px) `),d&&(s+=`translateY(${d}px)`),i.transform=s.trim(),a.maxHeight&&(r?i.maxHeight=Ge(a.maxHeight):o&&(i.maxHeight="")),a.maxWidth&&(r?i.maxWidth=Ge(a.maxWidth):o&&(i.maxWidth="")),Ti(this._pane.style,i)}_getExactOverlayY(t,e,i){let r={top:"",bottom:""},o=this._getOverlayPoint(e,this._overlayRect,t);if(this._isPushed&&(o=this._pushOverlayOnScreen(o,this._overlayRect,i)),t.overlayY==="bottom"){let a=this._document.documentElement.clientHeight;r.bottom=`${a-(o.y+this._overlayRect.height)}px`}else r.top=Ge(o.y);return r}_getExactOverlayX(t,e,i){let r={left:"",right:""},o=this._getOverlayPoint(e,this._overlayRect,t);this._isPushed&&(o=this._pushOverlayOnScreen(o,this._overlayRect,i));let a;if(this._isRtl()?a=t.overlayX==="end"?"left":"right":a=t.overlayX==="end"?"right":"left",a==="right"){let s=this._document.documentElement.clientWidth;r.right=`${s-(o.x+this._overlayRect.width)}px`}else r.left=Ge(o.x);return r}_getScrollVisibility(){let t=this._getOriginRect(),e=this._pane.getBoundingClientRect(),i=this._scrollables.map(r=>r.getElementRef().nativeElement.getBoundingClientRect());return{isOriginClipped:af(t,i),isOriginOutsideView:dd(t,i),isOverlayClipped:af(e,i),isOverlayOutsideView:dd(e,i)}}_subtractOverflows(t,...e){return e.reduce((i,r)=>i-Math.max(r,0),t)}_getNarrowedViewportRect(){let t=this._document.documentElement.clientWidth,e=this._document.documentElement.clientHeight,i=this._viewportRuler.getViewportScrollPosition();return{top:i.top+this._getViewportMarginTop(),left:i.left+this._getViewportMarginStart(),right:i.left+t-this._getViewportMarginEnd(),bottom:i.top+e-this._getViewportMarginBottom(),width:t-this._getViewportMarginStart()-this._getViewportMarginEnd(),height:e-this._getViewportMarginTop()-this._getViewportMarginBottom()}}_isRtl(){return this._overlayRef.getDirection()==="rtl"}_hasExactPosition(){return!this._hasFlexibleDimensions||this._isPushed}_getOffset(t,e){return e==="x"?t.offsetX==null?this._offsetX:t.offsetX:t.offsetY==null?this._offsetY:t.offsetY}_validatePositions(){}_addPanelClasses(t){this._pane&&pr(t).forEach(e=>{e!==""&&this._appliedPanelClasses.indexOf(e)===-1&&(this._appliedPanelClasses.push(e),this._pane.classList.add(e))})}_clearPanelClasses(){this._pane&&(this._appliedPanelClasses.forEach(t=>{this._pane.classList.remove(t)}),this._appliedPanelClasses=[])}_getViewportMarginStart(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.start??0}_getViewportMarginEnd(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.end??0}_getViewportMarginTop(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.top??0}_getViewportMarginBottom(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.bottom??0}_getOriginRect(){let t=this._origin;if(t instanceof Y)return t.nativeElement.getBoundingClientRect();if(t instanceof Element)return t.getBoundingClientRect();let e=t.width||0,i=t.height||0;return{top:t.y,bottom:t.y+i,left:t.x,right:t.x+e,height:i,width:e}}_getContainerRect(){let t=this._overlayRef.getConfig().usePopover&&this._popoverLocation!=="global",e=this._overlayContainer.getContainerElement();t&&(e.style.display="block");let i=e.getBoundingClientRect();return t&&(e.style.display=""),i}};function Ti(n,t){for(let e in t)t.hasOwnProperty(e)&&(n[e]=t[e]);return n}function cf(n){if(typeof n!="number"&&n!=null){let[t,e]=n.split(mx);return!e||e==="px"?parseFloat(t):null}return n||null}function df(n){return{top:Math.floor(n.top),right:Math.floor(n.right),bottom:Math.floor(n.bottom),left:Math.floor(n.left),width:Math.floor(n.width),height:Math.floor(n.height)}}function ux(n,t){return n===t?!0:n.isOriginClipped===t.isOriginClipped&&n.isOriginOutsideView===t.isOriginOutsideView&&n.isOverlayClipped===t.isOverlayClipped&&n.isOverlayOutsideView===t.isOverlayOutsideView}var mf="cdk-global-overlay-wrapper";function Vs(n){return new $s}var $s=class{_overlayRef;_cssPosition="static";_topOffset="";_bottomOffset="";_alignItems="";_xPosition="";_xOffset="";_width="";_height="";_isDisposed=!1;attach(t){let e=t.getConfig();this._overlayRef=t,this._width&&!e.width&&t.updateSize({width:this._width}),this._height&&!e.height&&t.updateSize({height:this._height}),t.hostElement.classList.add(mf),this._isDisposed=!1}top(t=""){return this._bottomOffset="",this._topOffset=t,this._alignItems="flex-start",this}left(t=""){return this._xOffset=t,this._xPosition="left",this}bottom(t=""){return this._topOffset="",this._bottomOffset=t,this._alignItems="flex-end",this}right(t=""){return this._xOffset=t,this._xPosition="right",this}start(t=""){return this._xOffset=t,this._xPosition="start",this}end(t=""){return this._xOffset=t,this._xPosition="end",this}width(t=""){return this._overlayRef?this._overlayRef.updateSize({width:t}):this._width=t,this}height(t=""){return this._overlayRef?this._overlayRef.updateSize({height:t}):this._height=t,this}centerHorizontally(t=""){return this.left(t),this._xPosition="center",this}centerVertically(t=""){return this.top(t),this._alignItems="center",this}apply(){if(!this._overlayRef||!this._overlayRef.hasAttached())return;let t=this._overlayRef.overlayElement.style,e=this._overlayRef.hostElement.style,i=this._overlayRef.getConfig(),{width:r,height:o,maxWidth:a,maxHeight:s}=i,l=(r==="100%"||r==="100vw")&&(!a||a==="100%"||a==="100vw"),d=(o==="100%"||o==="100vh")&&(!s||s==="100%"||s==="100vh"),c=this._xPosition,u=this._xOffset,h=this._overlayRef.getConfig().direction==="rtl",y="",_="",b="";l?b="flex-start":c==="center"?(b="center",h?_=u:y=u):h?c==="left"||c==="end"?(b="flex-end",y=u):(c==="right"||c==="start")&&(b="flex-start",_=u):c==="left"||c==="start"?(b="flex-start",y=u):(c==="right"||c==="end")&&(b="flex-end",_=u),t.position=this._cssPosition,t.marginLeft=l?"0":y,t.marginTop=d?"0":this._topOffset,t.marginBottom=this._bottomOffset,t.marginRight=l?"0":_,e.justifyContent=b,e.alignItems=d?"flex-start":this._alignItems}dispose(){if(this._isDisposed||!this._overlayRef)return;let t=this._overlayRef.overlayElement.style,e=this._overlayRef.hostElement,i=e.style;e.classList.remove(mf),i.justifyContent=i.alignItems=t.marginTop=t.marginBottom=t.marginLeft=t.marginRight=t.position="",this._overlayRef=null,this._isDisposed=!0}},yf=(()=>{class n{_injector=m(te);constructor(){}global(){return Vs()}flexibleConnectedTo(e){return js(this._injector,e)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),pd=new M("OVERLAY_DEFAULT_CONFIG");function vr(n,t){n.get(Je).load(bf);let e=n.get(vf),i=n.get(W),r=n.get(ut),o=n.get(ei),a=n.get(wt),s=n.get(rt,null,{optional:!0})||n.get(bt).createRenderer(null,null),l=new ai(t),d=n.get(pd,null,{optional:!0})?.usePopover??!0;l.direction=l.direction||a.value,"showPopover"in i.body?l.usePopover=t?.usePopover??d:l.usePopover=!1;let c=i.createElement("div"),u=i.createElement("div");c.id=r.getId("cdk-overlay-"),c.classList.add("cdk-overlay-pane"),u.appendChild(c),l.usePopover&&(u.setAttribute("popover","manual"),u.classList.add("cdk-overlay-popover"));let h=l.usePopover?l.positionStrategy?.getPopoverInsertionPoint?.():null;return ud(h)?h.after(u):h?.type==="parent"?h.element.appendChild(u):e.getContainerElement().appendChild(u),new Ls(new Lo(c,o,n),u,c,l,n.get(K),n.get(gf),i,n.get(ar),n.get(_f),t?.disableAnimations??n.get(Xi,null,{optional:!0})==="NoopAnimations",n.get(An),s)}var xf=(()=>{class n{scrollStrategies=m(hf);_positionBuilder=m(yf);_injector=m(te);constructor(){}create(e){return vr(this._injector,e)}position(){return this._positionBuilder}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var Oi=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({providers:[xf],imports:[ie,br,ld,ld]})}return n})();var gx=new M("MATERIAL_ANIMATIONS"),wf=null;function _x(){return m(gx,{optional:!0})?.animationsDisabled||m(Xi,{optional:!0})==="NoopAnimations"?"di-disabled":(wf??=m(ks).matchMedia("(prefers-reduced-motion)").matches,wf?"reduced-motion":"enabled")}function He(){return _x()!=="enabled"}function kt(n){return n!=null&&`${n}`!="false"}var Gt=(function(n){return n[n.FADING_IN=0]="FADING_IN",n[n.VISIBLE=1]="VISIBLE",n[n.FADING_OUT=2]="FADING_OUT",n[n.HIDDEN=3]="HIDDEN",n})(Gt||{}),hd=class{_renderer;element;config;_animationForciblyDisabledThroughCss;state=Gt.HIDDEN;constructor(t,e,i,r=!1){this._renderer=t,this.element=e,this.config=i,this._animationForciblyDisabledThroughCss=r}fadeOut(){this._renderer.fadeOutRipple(this)}},Cf=ur({passive:!0,capture:!0}),fd=class{_events=new Map;addHandler(t,e,i,r){let o=this._events.get(e);if(o){let a=o.get(i);a?a.add(r):o.set(i,new Set([r]))}else this._events.set(e,new Map([[i,new Set([r])]])),t.runOutsideAngular(()=>{document.addEventListener(e,this._delegateEventHandler,Cf)})}removeHandler(t,e,i){let r=this._events.get(t);if(!r)return;let o=r.get(e);o&&(o.delete(i),o.size===0&&r.delete(e),r.size===0&&(this._events.delete(t),document.removeEventListener(t,this._delegateEventHandler,Cf)))}_delegateEventHandler=t=>{let e=At(t);e&&this._events.get(t.type)?.forEach((i,r)=>{(r===e||r.contains(e))&&i.forEach(o=>o.handleEvent(t))})}},jo={enterDuration:225,exitDuration:150},bx=800,kf=ur({passive:!0,capture:!0}),Df=["mousedown","touchstart"],Ef=["mouseup","mouseleave","touchend","touchcancel"],vx=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["ng-component"]],hostAttrs:["mat-ripple-style-loader",""],decls:0,vars:0,template:function(i,r){},styles:[`.mat-ripple {
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
  background-color: var(--mat-ripple-color, color-mix(in srgb, var(--mat-sys-on-surface) 10%, transparent));
}
@media (forced-colors: active) {
  .mat-ripple-element {
    display: none;
  }
}
.cdk-drag-preview .mat-ripple-element, .cdk-drag-placeholder .mat-ripple-element {
  display: none;
}
`],encapsulation:2,changeDetection:0})}return n})(),Ri=class n{_target;_ngZone;_platform;_containerElement;_triggerElement=null;_isPointerDown=!1;_activeRipples=new Map;_mostRecentTransientRipple=null;_lastTouchStartEvent;_pointerUpEventsRegistered=!1;_containerRect=null;static _eventManager=new fd;constructor(t,e,i,r,o){this._target=t,this._ngZone=e,this._platform=r,r.isBrowser&&(this._containerElement=Ut(i)),o&&o.get(Je).load(vx)}fadeInRipple(t,e,i={}){let r=this._containerRect=this._containerRect||this._containerElement.getBoundingClientRect(),o=S(S({},jo),i.animation);i.centered&&(t=r.left+r.width/2,e=r.top+r.height/2);let a=i.radius||yx(t,e,r),s=t-r.left,l=e-r.top,d=o.enterDuration,c=document.createElement("div");c.classList.add("mat-ripple-element"),c.style.left=`${s-a}px`,c.style.top=`${l-a}px`,c.style.height=`${a*2}px`,c.style.width=`${a*2}px`,i.color!=null&&(c.style.backgroundColor=i.color),c.style.transitionDuration=`${d}ms`,this._containerElement.appendChild(c);let u=window.getComputedStyle(c),h=u.transitionProperty,y=u.transitionDuration,_=h==="none"||y==="0s"||y==="0s, 0s"||r.width===0&&r.height===0,b=new hd(this,c,i,_);c.style.transform="scale3d(1, 1, 1)",b.state=Gt.FADING_IN,i.persistent||(this._mostRecentTransientRipple=b);let v=null;return!_&&(d||o.exitDuration)&&this._ngZone.runOutsideAngular(()=>{let x=()=>{v&&(v.fallbackTimer=null),clearTimeout(T),this._finishRippleTransition(b)},A=()=>this._destroyRipple(b),T=setTimeout(A,d+100);c.addEventListener("transitionend",x),c.addEventListener("transitioncancel",A),v={onTransitionEnd:x,onTransitionCancel:A,fallbackTimer:T}}),this._activeRipples.set(b,v),(_||!d)&&this._finishRippleTransition(b),b}fadeOutRipple(t){if(t.state===Gt.FADING_OUT||t.state===Gt.HIDDEN)return;let e=t.element,i=S(S({},jo),t.config.animation);e.style.transitionDuration=`${i.exitDuration}ms`,e.style.opacity="0",t.state=Gt.FADING_OUT,(t._animationForciblyDisabledThroughCss||!i.exitDuration)&&this._finishRippleTransition(t)}fadeOutAll(){this._getActiveRipples().forEach(t=>t.fadeOut())}fadeOutAllNonPersistent(){this._getActiveRipples().forEach(t=>{t.config.persistent||t.fadeOut()})}setupTriggerEvents(t){let e=Ut(t);!this._platform.isBrowser||!e||e===this._triggerElement||(this._removeTriggerEvents(),this._triggerElement=e,Df.forEach(i=>{n._eventManager.addHandler(this._ngZone,i,e,this)}))}handleEvent(t){t.type==="mousedown"?this._onMousedown(t):t.type==="touchstart"?this._onTouchStart(t):this._onPointerUp(),this._pointerUpEventsRegistered||(this._ngZone.runOutsideAngular(()=>{Ef.forEach(e=>{this._triggerElement.addEventListener(e,this,kf)})}),this._pointerUpEventsRegistered=!0)}_finishRippleTransition(t){t.state===Gt.FADING_IN?this._startFadeOutTransition(t):t.state===Gt.FADING_OUT&&this._destroyRipple(t)}_startFadeOutTransition(t){let e=t===this._mostRecentTransientRipple,{persistent:i}=t.config;t.state=Gt.VISIBLE,!i&&(!e||!this._isPointerDown)&&t.fadeOut()}_destroyRipple(t){let e=this._activeRipples.get(t)??null;this._activeRipples.delete(t),this._activeRipples.size||(this._containerRect=null),t===this._mostRecentTransientRipple&&(this._mostRecentTransientRipple=null),t.state=Gt.HIDDEN,e!==null&&(t.element.removeEventListener("transitionend",e.onTransitionEnd),t.element.removeEventListener("transitioncancel",e.onTransitionCancel),e.fallbackTimer!==null&&clearTimeout(e.fallbackTimer)),t.element.remove()}_onMousedown(t){let e=Di(t),i=this._lastTouchStartEvent&&Date.now()<this._lastTouchStartEvent+bx;!this._target.rippleDisabled&&!e&&!i&&(this._isPointerDown=!0,this.fadeInRipple(t.clientX,t.clientY,this._target.rippleConfig))}_onTouchStart(t){if(!this._target.rippleDisabled&&!Ei(t)){this._lastTouchStartEvent=Date.now(),this._isPointerDown=!0;let e=t.changedTouches;if(e)for(let i=0;i<e.length;i++)this.fadeInRipple(e[i].clientX,e[i].clientY,this._target.rippleConfig)}}_onPointerUp(){this._isPointerDown&&(this._isPointerDown=!1,this._getActiveRipples().forEach(t=>{let e=t.state===Gt.VISIBLE||t.config.terminateOnPointerUp&&t.state===Gt.FADING_IN;!t.config.persistent&&e&&t.fadeOut()}))}_getActiveRipples(){return Array.from(this._activeRipples.keys())}_removeTriggerEvents(){let t=this._triggerElement;t&&(Df.forEach(e=>n._eventManager.removeHandler(e,t,this)),this._pointerUpEventsRegistered&&(Ef.forEach(e=>t.removeEventListener(e,this,kf)),this._pointerUpEventsRegistered=!1))}};function yx(n,t,e){let i=Math.max(Math.abs(n-e.left),Math.abs(n-e.right)),r=Math.max(Math.abs(t-e.top),Math.abs(t-e.bottom));return Math.sqrt(i*i+r*r)}var Pi=new M("mat-ripple-global-options"),zs=(()=>{class n{_elementRef=m(Y);_animationsDisabled=He();color;unbounded=!1;centered=!1;radius=0;animation;get disabled(){return this._disabled}set disabled(e){e&&this.fadeOutAllNonPersistent(),this._disabled=e,this._setupTriggerEventsIfEnabled()}_disabled=!1;get trigger(){return this._trigger||this._elementRef.nativeElement}set trigger(e){this._trigger=e,this._setupTriggerEventsIfEnabled()}_trigger;_rippleRenderer;_globalOptions;_isInitialized=!1;constructor(){let e=m(K),i=m(Ce),r=m(Pi,{optional:!0}),o=m(te);this._globalOptions=r||{},this._rippleRenderer=new Ri(this,e,this._elementRef,i,o)}ngOnInit(){this._isInitialized=!0,this._setupTriggerEventsIfEnabled()}ngOnDestroy(){this._rippleRenderer._removeTriggerEvents()}fadeOutAll(){this._rippleRenderer.fadeOutAll()}fadeOutAllNonPersistent(){this._rippleRenderer.fadeOutAllNonPersistent()}get rippleConfig(){return{centered:this.centered,radius:this.radius,color:this.color,animation:S(S(S({},this._globalOptions.animation),this._animationsDisabled?{enterDuration:0,exitDuration:0}:{}),this.animation),terminateOnPointerUp:this._globalOptions.terminateOnPointerUp}}get rippleDisabled(){return this.disabled||!!this._globalOptions.disabled}_setupTriggerEventsIfEnabled(){!this.disabled&&this._isInitialized&&this._rippleRenderer.setupTriggerEvents(this.trigger)}launch(e,i=0,r){return typeof e=="number"?this._rippleRenderer.fadeInRipple(e,i,S(S({},this.rippleConfig),r)):this._rippleRenderer.fadeInRipple(0,0,S(S({},this.rippleConfig),e))}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","mat-ripple",""],["","matRipple",""]],hostAttrs:[1,"mat-ripple"],hostVars:2,hostBindings:function(i,r){i&2&&Q("mat-ripple-unbounded",r.unbounded)},inputs:{color:[0,"matRippleColor","color"],unbounded:[0,"matRippleUnbounded","unbounded"],centered:[0,"matRippleCentered","centered"],radius:[0,"matRippleRadius","radius"],animation:[0,"matRippleAnimation","animation"],disabled:[0,"matRippleDisabled","disabled"],trigger:[0,"matRippleTrigger","trigger"]},exportAs:["matRipple"]})}return n})();var xx={capture:!0},wx=["focus","mousedown","mouseenter","touchstart"],gd="mat-ripple-loader-uninitialized",_d="mat-ripple-loader-class-name",Sf="mat-ripple-loader-centered",Hs="mat-ripple-loader-disabled",qs=(()=>{class n{_document=m(W);_animationsDisabled=He();_globalRippleOptions=m(Pi,{optional:!0});_platform=m(Ce);_ngZone=m(K);_injector=m(te);_eventCleanups;_hosts=new Map;constructor(){let e=m(bt).createRenderer(null,null);this._eventCleanups=this._ngZone.runOutsideAngular(()=>wx.map(i=>e.listen(this._document,i,this._onInteraction,xx)))}ngOnDestroy(){let e=this._hosts.keys();for(let i of e)this.destroyRipple(i);this._eventCleanups.forEach(i=>i())}configureRipple(e,i){e.setAttribute(gd,this._globalRippleOptions?.namespace??""),(i.className||!e.hasAttribute(_d))&&e.setAttribute(_d,i.className||""),i.centered&&e.setAttribute(Sf,""),i.disabled&&e.setAttribute(Hs,"")}setDisabled(e,i){let r=this._hosts.get(e);r?(r.target.rippleDisabled=i,!i&&!r.hasSetUpEvents&&(r.hasSetUpEvents=!0,r.renderer.setupTriggerEvents(e))):i?e.setAttribute(Hs,""):e.removeAttribute(Hs)}_onInteraction=e=>{let i=At(e);if(i instanceof HTMLElement){let r=i.closest(`[${gd}="${this._globalRippleOptions?.namespace??""}"]`);r&&this._createRipple(r)}};_createRipple(e){if(!this._document||this._hosts.has(e))return;e.querySelector(".mat-ripple")?.remove();let i=this._document.createElement("span");i.classList.add("mat-ripple",e.getAttribute(_d)),e.append(i);let r=this._globalRippleOptions,o=this._animationsDisabled?0:r?.animation?.enterDuration??jo.enterDuration,a=this._animationsDisabled?0:r?.animation?.exitDuration??jo.exitDuration,s={rippleDisabled:this._animationsDisabled||r?.disabled||e.hasAttribute(Hs),rippleConfig:{centered:e.hasAttribute(Sf),terminateOnPointerUp:r?.terminateOnPointerUp,animation:{enterDuration:o,exitDuration:a}}},l=new Ri(s,this._ngZone,i,this._platform,this._injector),d=!s.rippleDisabled;d&&l.setupTriggerEvents(e),this._hosts.set(e,{target:s,renderer:l,hasSetUpEvents:d}),e.removeAttribute(gd)}destroyRipple(e){let i=this._hosts.get(e);i&&(i.renderer._removeTriggerEvents(),this._hosts.delete(e))}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var on=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["structural-styles"]],decls:0,vars:0,template:function(i,r){},styles:[`.mat-focus-indicator {
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
  display: var(--mat-focus-indicator-display, none);
  border-width: var(--mat-focus-indicator-border-width, 3px);
  border-style: var(--mat-focus-indicator-border-style, solid);
  border-color: var(--mat-focus-indicator-border-color, transparent);
  border-radius: var(--mat-focus-indicator-border-radius, 4px);
}
.mat-focus-indicator:focus-visible::before {
  content: "";
}

@media (forced-colors: active) {
  html {
    --mat-focus-indicator-display: block;
  }
}
`],encapsulation:2,changeDetection:0})}return n})();var Cx=new M("MAT_BUTTON_CONFIG");function Mf(n){return n==null?void 0:po(n)}var Af=(()=>{class n{_elementRef=m(Y);_ngZone=m(K);_animationsDisabled=He();_config=m(Cx,{optional:!0});_focusMonitor=m(yn);_cleanupClick;_renderer=m(rt);_rippleLoader=m(qs);_isAnchor;_isFab=!1;color;get disableRipple(){return this._disableRipple}set disableRipple(e){this._disableRipple=e,this._updateRippleDisabled()}_disableRipple=!1;get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._updateRippleDisabled()}_disabled=!1;ariaDisabled;disabledInteractive;tabIndex;set _tabindex(e){this.tabIndex=e}constructor(){m(Je).load(on);let e=this._elementRef.nativeElement;this._isAnchor=e.tagName==="A",this.disabledInteractive=this._config?.disabledInteractive??!1,this.color=this._config?.color??null,this._rippleLoader?.configureRipple(e,{className:"mat-mdc-button-ripple"})}ngAfterViewInit(){this._focusMonitor.monitor(this._elementRef,!0),this._isAnchor&&this._setupAsAnchor()}ngOnDestroy(){this._cleanupClick?.(),this._focusMonitor.stopMonitoring(this._elementRef),this._rippleLoader?.destroyRipple(this._elementRef.nativeElement)}focus(e="program",i){e?this._focusMonitor.focusVia(this._elementRef.nativeElement,e,i):this._elementRef.nativeElement.focus(i)}_getAriaDisabled(){return this.ariaDisabled!=null?this.ariaDisabled:this._isAnchor?this.disabled||null:this.disabled&&this.disabledInteractive?!0:null}_getDisabledAttribute(){return this.disabledInteractive||!this.disabled?null:!0}_updateRippleDisabled(){this._rippleLoader?.setDisabled(this._elementRef.nativeElement,this.disableRipple||this.disabled)}_getTabIndex(){return this._isAnchor?this.disabled&&!this.disabledInteractive?-1:this.tabIndex:this.tabIndex}_setupAsAnchor(){this._cleanupClick=this._ngZone.runOutsideAngular(()=>this._renderer.listen(this._elementRef.nativeElement,"click",e=>{this.disabled&&(e.preventDefault(),e.stopImmediatePropagation())}))}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,hostAttrs:[1,"mat-mdc-button-base"],hostVars:13,hostBindings:function(i,r){i&2&&(le("disabled",r._getDisabledAttribute())("aria-disabled",r._getAriaDisabled())("tabindex",r._getTabIndex()),vt(r.color?"mat-"+r.color:""),Q("mat-mdc-button-disabled",r.disabled)("mat-mdc-button-disabled-interactive",r.disabledInteractive)("mat-unthemed",!r.color)("_mat-animation-noopable",r._animationsDisabled))},inputs:{color:"color",disableRipple:[2,"disableRipple","disableRipple",Me],disabled:[2,"disabled","disabled",Me],ariaDisabled:[2,"aria-disabled","ariaDisabled",Me],disabledInteractive:[2,"disabledInteractive","disabledInteractive",Me],tabIndex:[2,"tabIndex","tabIndex",Mf],_tabindex:[2,"tabindex","_tabindex",Mf]}})}return n})();var xn=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[ie]})}return n})();var kx=["matButton",""],Dx=[[["",8,"material-icons",3,"iconPositionEnd",""],["mat-icon",3,"iconPositionEnd",""],["","matButtonIcon","",3,"iconPositionEnd",""]],"*",[["","iconPositionEnd","",8,"material-icons"],["mat-icon","iconPositionEnd",""],["","matButtonIcon","","iconPositionEnd",""]]],Ex=[".material-icons:not([iconPositionEnd]), mat-icon:not([iconPositionEnd]), [matButtonIcon]:not([iconPositionEnd])","*",".material-icons[iconPositionEnd], mat-icon[iconPositionEnd], [matButtonIcon][iconPositionEnd]"];var If=new Map([["text",["mat-mdc-button"]],["filled",["mdc-button--unelevated","mat-mdc-unelevated-button"]],["elevated",["mdc-button--raised","mat-mdc-raised-button"]],["outlined",["mdc-button--outlined","mat-mdc-outlined-button"]],["tonal",["mat-tonal-button"]]]),yr=(()=>{class n extends Af{get appearance(){return this._appearance}set appearance(e){this.setAppearance(e||this._config?.defaultAppearance||"text")}_appearance=null;constructor(){super();let e=Sx(this._elementRef.nativeElement);e&&this.setAppearance(e)}setAppearance(e){if(e===this._appearance)return;let i=this._elementRef.nativeElement.classList,r=this._appearance?If.get(this._appearance):null,o=If.get(e);r&&i.remove(...r),i.add(...o),this._appearance=e}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["button","matButton",""],["a","matButton",""],["button","mat-button",""],["button","mat-raised-button",""],["button","mat-flat-button",""],["button","mat-stroked-button",""],["a","mat-button",""],["a","mat-raised-button",""],["a","mat-flat-button",""],["a","mat-stroked-button",""]],hostAttrs:[1,"mdc-button"],inputs:{appearance:[0,"matButton","appearance"]},exportAs:["matButton","matAnchor"],features:[Te],attrs:kx,ngContentSelectors:Ex,decls:7,vars:4,consts:[[1,"mat-mdc-button-persistent-ripple"],[1,"mdc-button__label"],[1,"mat-focus-indicator"],[1,"mat-mdc-button-touch-target"]],template:function(i,r){i&1&&(ne(Dx),Zt(0,"span",0),B(1),qe(2,"span",1),B(3,1),Qe(),B(4,2),Zt(5,"span",2)(6,"span",3)),i&2&&Q("mdc-button__ripple",!r._isFab)("mdc-fab__ripple",r._isFab)},styles:[`.mat-mdc-button-base {
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
  padding: 0 var(--mat-button-text-horizontal-padding, 12px);
  height: var(--mat-button-text-container-height, 40px);
  font-family: var(--mat-button-text-label-text-font, var(--mat-sys-label-large-font));
  font-size: var(--mat-button-text-label-text-size, var(--mat-sys-label-large-size));
  letter-spacing: var(--mat-button-text-label-text-tracking, var(--mat-sys-label-large-tracking));
  text-transform: var(--mat-button-text-label-text-transform);
  font-weight: var(--mat-button-text-label-text-weight, var(--mat-sys-label-large-weight));
}
.mat-mdc-button, .mat-mdc-button .mdc-button__ripple {
  border-radius: var(--mat-button-text-container-shape, var(--mat-sys-corner-full));
}
.mat-mdc-button:not(:disabled) {
  color: var(--mat-button-text-label-text-color, var(--mat-sys-primary));
}
.mat-mdc-button[disabled], .mat-mdc-button.mat-mdc-button-disabled {
  cursor: default;
  pointer-events: none;
  color: var(--mat-button-text-disabled-label-text-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}
.mat-mdc-button.mat-mdc-button-disabled-interactive {
  pointer-events: auto;
}
.mat-mdc-button:has(.material-icons, mat-icon, [matButtonIcon]) {
  padding: 0 var(--mat-button-text-with-icon-horizontal-padding, 16px);
}
.mat-mdc-button > .mat-icon {
  margin-right: var(--mat-button-text-icon-spacing, 8px);
  margin-left: var(--mat-button-text-icon-offset, -4px);
}
[dir=rtl] .mat-mdc-button > .mat-icon {
  margin-right: var(--mat-button-text-icon-offset, -4px);
  margin-left: var(--mat-button-text-icon-spacing, 8px);
}
.mat-mdc-button .mdc-button__label + .mat-icon {
  margin-right: var(--mat-button-text-icon-offset, -4px);
  margin-left: var(--mat-button-text-icon-spacing, 8px);
}
[dir=rtl] .mat-mdc-button .mdc-button__label + .mat-icon {
  margin-right: var(--mat-button-text-icon-spacing, 8px);
  margin-left: var(--mat-button-text-icon-offset, -4px);
}
.mat-mdc-button .mat-ripple-element {
  background-color: var(--mat-button-text-ripple-color, color-mix(in srgb, var(--mat-sys-primary) calc(var(--mat-sys-pressed-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-button .mat-mdc-button-persistent-ripple::before {
  background-color: var(--mat-button-text-state-layer-color, var(--mat-sys-primary));
}
.mat-mdc-button.mat-mdc-button-disabled .mat-mdc-button-persistent-ripple::before {
  background-color: var(--mat-button-text-disabled-state-layer-color, var(--mat-sys-on-surface-variant));
}
.mat-mdc-button:hover > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-text-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity));
}
.mat-mdc-button.cdk-program-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-button.cdk-keyboard-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-button.mat-mdc-button-disabled-interactive:focus > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-text-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity));
}
.mat-mdc-button:active > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-text-pressed-state-layer-opacity, var(--mat-sys-pressed-state-layer-opacity));
}
.mat-mdc-button .mat-mdc-button-touch-target {
  position: absolute;
  top: 50%;
  height: var(--mat-button-text-touch-target-size, 48px);
  display: var(--mat-button-text-touch-target-display, block);
  left: 0;
  right: 0;
  transform: translateY(-50%);
}

.mat-mdc-unelevated-button {
  transition: box-shadow 280ms cubic-bezier(0.4, 0, 0.2, 1);
  height: var(--mat-button-filled-container-height, 40px);
  font-family: var(--mat-button-filled-label-text-font, var(--mat-sys-label-large-font));
  font-size: var(--mat-button-filled-label-text-size, var(--mat-sys-label-large-size));
  letter-spacing: var(--mat-button-filled-label-text-tracking, var(--mat-sys-label-large-tracking));
  text-transform: var(--mat-button-filled-label-text-transform);
  font-weight: var(--mat-button-filled-label-text-weight, var(--mat-sys-label-large-weight));
  padding: 0 var(--mat-button-filled-horizontal-padding, 24px);
}
.mat-mdc-unelevated-button > .mat-icon {
  margin-right: var(--mat-button-filled-icon-spacing, 8px);
  margin-left: var(--mat-button-filled-icon-offset, -8px);
}
[dir=rtl] .mat-mdc-unelevated-button > .mat-icon {
  margin-right: var(--mat-button-filled-icon-offset, -8px);
  margin-left: var(--mat-button-filled-icon-spacing, 8px);
}
.mat-mdc-unelevated-button .mdc-button__label + .mat-icon {
  margin-right: var(--mat-button-filled-icon-offset, -8px);
  margin-left: var(--mat-button-filled-icon-spacing, 8px);
}
[dir=rtl] .mat-mdc-unelevated-button .mdc-button__label + .mat-icon {
  margin-right: var(--mat-button-filled-icon-spacing, 8px);
  margin-left: var(--mat-button-filled-icon-offset, -8px);
}
.mat-mdc-unelevated-button .mat-ripple-element {
  background-color: var(--mat-button-filled-ripple-color, color-mix(in srgb, var(--mat-sys-on-primary) calc(var(--mat-sys-pressed-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-unelevated-button .mat-mdc-button-persistent-ripple::before {
  background-color: var(--mat-button-filled-state-layer-color, var(--mat-sys-on-primary));
}
.mat-mdc-unelevated-button.mat-mdc-button-disabled .mat-mdc-button-persistent-ripple::before {
  background-color: var(--mat-button-filled-disabled-state-layer-color, var(--mat-sys-on-surface-variant));
}
.mat-mdc-unelevated-button:hover > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-filled-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity));
}
.mat-mdc-unelevated-button.cdk-program-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-unelevated-button.cdk-keyboard-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-unelevated-button.mat-mdc-button-disabled-interactive:focus > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-filled-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity));
}
.mat-mdc-unelevated-button:active > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-filled-pressed-state-layer-opacity, var(--mat-sys-pressed-state-layer-opacity));
}
.mat-mdc-unelevated-button .mat-mdc-button-touch-target {
  position: absolute;
  top: 50%;
  height: var(--mat-button-filled-touch-target-size, 48px);
  display: var(--mat-button-filled-touch-target-display, block);
  left: 0;
  right: 0;
  transform: translateY(-50%);
}
.mat-mdc-unelevated-button:not(:disabled) {
  color: var(--mat-button-filled-label-text-color, var(--mat-sys-on-primary));
  background-color: var(--mat-button-filled-container-color, var(--mat-sys-primary));
}
.mat-mdc-unelevated-button, .mat-mdc-unelevated-button .mdc-button__ripple {
  border-radius: var(--mat-button-filled-container-shape, var(--mat-sys-corner-full));
}
.mat-mdc-unelevated-button[disabled], .mat-mdc-unelevated-button.mat-mdc-button-disabled {
  cursor: default;
  pointer-events: none;
  color: var(--mat-button-filled-disabled-label-text-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
  background-color: var(--mat-button-filled-disabled-container-color, color-mix(in srgb, var(--mat-sys-on-surface) 12%, transparent));
}
.mat-mdc-unelevated-button.mat-mdc-button-disabled-interactive {
  pointer-events: auto;
}

.mat-mdc-raised-button {
  transition: box-shadow 280ms cubic-bezier(0.4, 0, 0.2, 1);
  box-shadow: var(--mat-button-protected-container-elevation-shadow, var(--mat-sys-level1));
  height: var(--mat-button-protected-container-height, 40px);
  font-family: var(--mat-button-protected-label-text-font, var(--mat-sys-label-large-font));
  font-size: var(--mat-button-protected-label-text-size, var(--mat-sys-label-large-size));
  letter-spacing: var(--mat-button-protected-label-text-tracking, var(--mat-sys-label-large-tracking));
  text-transform: var(--mat-button-protected-label-text-transform);
  font-weight: var(--mat-button-protected-label-text-weight, var(--mat-sys-label-large-weight));
  padding: 0 var(--mat-button-protected-horizontal-padding, 24px);
}
.mat-mdc-raised-button > .mat-icon {
  margin-right: var(--mat-button-protected-icon-spacing, 8px);
  margin-left: var(--mat-button-protected-icon-offset, -8px);
}
[dir=rtl] .mat-mdc-raised-button > .mat-icon {
  margin-right: var(--mat-button-protected-icon-offset, -8px);
  margin-left: var(--mat-button-protected-icon-spacing, 8px);
}
.mat-mdc-raised-button .mdc-button__label + .mat-icon {
  margin-right: var(--mat-button-protected-icon-offset, -8px);
  margin-left: var(--mat-button-protected-icon-spacing, 8px);
}
[dir=rtl] .mat-mdc-raised-button .mdc-button__label + .mat-icon {
  margin-right: var(--mat-button-protected-icon-spacing, 8px);
  margin-left: var(--mat-button-protected-icon-offset, -8px);
}
.mat-mdc-raised-button .mat-ripple-element {
  background-color: var(--mat-button-protected-ripple-color, color-mix(in srgb, var(--mat-sys-primary) calc(var(--mat-sys-pressed-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-raised-button .mat-mdc-button-persistent-ripple::before {
  background-color: var(--mat-button-protected-state-layer-color, var(--mat-sys-primary));
}
.mat-mdc-raised-button.mat-mdc-button-disabled .mat-mdc-button-persistent-ripple::before {
  background-color: var(--mat-button-protected-disabled-state-layer-color, var(--mat-sys-on-surface-variant));
}
.mat-mdc-raised-button:hover > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-protected-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity));
}
.mat-mdc-raised-button.cdk-program-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-raised-button.cdk-keyboard-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-raised-button.mat-mdc-button-disabled-interactive:focus > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-protected-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity));
}
.mat-mdc-raised-button:active > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-protected-pressed-state-layer-opacity, var(--mat-sys-pressed-state-layer-opacity));
}
.mat-mdc-raised-button .mat-mdc-button-touch-target {
  position: absolute;
  top: 50%;
  height: var(--mat-button-protected-touch-target-size, 48px);
  display: var(--mat-button-protected-touch-target-display, block);
  left: 0;
  right: 0;
  transform: translateY(-50%);
}
.mat-mdc-raised-button:not(:disabled) {
  color: var(--mat-button-protected-label-text-color, var(--mat-sys-primary));
  background-color: var(--mat-button-protected-container-color, var(--mat-sys-surface));
}
.mat-mdc-raised-button, .mat-mdc-raised-button .mdc-button__ripple {
  border-radius: var(--mat-button-protected-container-shape, var(--mat-sys-corner-full));
}
@media (hover: hover) {
  .mat-mdc-raised-button:hover {
    box-shadow: var(--mat-button-protected-hover-container-elevation-shadow, var(--mat-sys-level2));
  }
}
.mat-mdc-raised-button:focus {
  box-shadow: var(--mat-button-protected-focus-container-elevation-shadow, var(--mat-sys-level1));
}
.mat-mdc-raised-button:active, .mat-mdc-raised-button:focus:active {
  box-shadow: var(--mat-button-protected-pressed-container-elevation-shadow, var(--mat-sys-level1));
}
.mat-mdc-raised-button[disabled], .mat-mdc-raised-button.mat-mdc-button-disabled {
  cursor: default;
  pointer-events: none;
  color: var(--mat-button-protected-disabled-label-text-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
  background-color: var(--mat-button-protected-disabled-container-color, color-mix(in srgb, var(--mat-sys-on-surface) 12%, transparent));
}
.mat-mdc-raised-button[disabled].mat-mdc-button-disabled, .mat-mdc-raised-button.mat-mdc-button-disabled.mat-mdc-button-disabled {
  box-shadow: var(--mat-button-protected-disabled-container-elevation-shadow, var(--mat-sys-level0));
}
.mat-mdc-raised-button.mat-mdc-button-disabled-interactive {
  pointer-events: auto;
}

.mat-mdc-outlined-button {
  border-style: solid;
  transition: border 280ms cubic-bezier(0.4, 0, 0.2, 1);
  height: var(--mat-button-outlined-container-height, 40px);
  font-family: var(--mat-button-outlined-label-text-font, var(--mat-sys-label-large-font));
  font-size: var(--mat-button-outlined-label-text-size, var(--mat-sys-label-large-size));
  letter-spacing: var(--mat-button-outlined-label-text-tracking, var(--mat-sys-label-large-tracking));
  text-transform: var(--mat-button-outlined-label-text-transform);
  font-weight: var(--mat-button-outlined-label-text-weight, var(--mat-sys-label-large-weight));
  border-radius: var(--mat-button-outlined-container-shape, var(--mat-sys-corner-full));
  border-width: var(--mat-button-outlined-outline-width, 1px);
  padding: 0 var(--mat-button-outlined-horizontal-padding, 24px);
}
.mat-mdc-outlined-button > .mat-icon {
  margin-right: var(--mat-button-outlined-icon-spacing, 8px);
  margin-left: var(--mat-button-outlined-icon-offset, -8px);
}
[dir=rtl] .mat-mdc-outlined-button > .mat-icon {
  margin-right: var(--mat-button-outlined-icon-offset, -8px);
  margin-left: var(--mat-button-outlined-icon-spacing, 8px);
}
.mat-mdc-outlined-button .mdc-button__label + .mat-icon {
  margin-right: var(--mat-button-outlined-icon-offset, -8px);
  margin-left: var(--mat-button-outlined-icon-spacing, 8px);
}
[dir=rtl] .mat-mdc-outlined-button .mdc-button__label + .mat-icon {
  margin-right: var(--mat-button-outlined-icon-spacing, 8px);
  margin-left: var(--mat-button-outlined-icon-offset, -8px);
}
.mat-mdc-outlined-button .mat-ripple-element {
  background-color: var(--mat-button-outlined-ripple-color, color-mix(in srgb, var(--mat-sys-primary) calc(var(--mat-sys-pressed-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-outlined-button .mat-mdc-button-persistent-ripple::before {
  background-color: var(--mat-button-outlined-state-layer-color, var(--mat-sys-primary));
}
.mat-mdc-outlined-button.mat-mdc-button-disabled .mat-mdc-button-persistent-ripple::before {
  background-color: var(--mat-button-outlined-disabled-state-layer-color, var(--mat-sys-on-surface-variant));
}
.mat-mdc-outlined-button:hover > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-outlined-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity));
}
.mat-mdc-outlined-button.cdk-program-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-outlined-button.cdk-keyboard-focused > .mat-mdc-button-persistent-ripple::before, .mat-mdc-outlined-button.mat-mdc-button-disabled-interactive:focus > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-outlined-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity));
}
.mat-mdc-outlined-button:active > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-outlined-pressed-state-layer-opacity, var(--mat-sys-pressed-state-layer-opacity));
}
.mat-mdc-outlined-button .mat-mdc-button-touch-target {
  position: absolute;
  top: 50%;
  height: var(--mat-button-outlined-touch-target-size, 48px);
  display: var(--mat-button-outlined-touch-target-display, block);
  left: 0;
  right: 0;
  transform: translateY(-50%);
}
.mat-mdc-outlined-button:not(:disabled) {
  color: var(--mat-button-outlined-label-text-color, var(--mat-sys-primary));
  border-color: var(--mat-button-outlined-outline-color, var(--mat-sys-outline));
}
.mat-mdc-outlined-button[disabled], .mat-mdc-outlined-button.mat-mdc-button-disabled {
  cursor: default;
  pointer-events: none;
  color: var(--mat-button-outlined-disabled-label-text-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
  border-color: var(--mat-button-outlined-disabled-outline-color, color-mix(in srgb, var(--mat-sys-on-surface) 12%, transparent));
}
.mat-mdc-outlined-button.mat-mdc-button-disabled-interactive {
  pointer-events: auto;
}

.mat-tonal-button {
  transition: box-shadow 280ms cubic-bezier(0.4, 0, 0.2, 1);
  height: var(--mat-button-tonal-container-height, 40px);
  font-family: var(--mat-button-tonal-label-text-font, var(--mat-sys-label-large-font));
  font-size: var(--mat-button-tonal-label-text-size, var(--mat-sys-label-large-size));
  letter-spacing: var(--mat-button-tonal-label-text-tracking, var(--mat-sys-label-large-tracking));
  text-transform: var(--mat-button-tonal-label-text-transform);
  font-weight: var(--mat-button-tonal-label-text-weight, var(--mat-sys-label-large-weight));
  padding: 0 var(--mat-button-tonal-horizontal-padding, 24px);
}
.mat-tonal-button:not(:disabled) {
  color: var(--mat-button-tonal-label-text-color, var(--mat-sys-on-secondary-container));
  background-color: var(--mat-button-tonal-container-color, var(--mat-sys-secondary-container));
}
.mat-tonal-button, .mat-tonal-button .mdc-button__ripple {
  border-radius: var(--mat-button-tonal-container-shape, var(--mat-sys-corner-full));
}
.mat-tonal-button[disabled], .mat-tonal-button.mat-mdc-button-disabled {
  cursor: default;
  pointer-events: none;
  color: var(--mat-button-tonal-disabled-label-text-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
  background-color: var(--mat-button-tonal-disabled-container-color, color-mix(in srgb, var(--mat-sys-on-surface) 12%, transparent));
}
.mat-tonal-button.mat-mdc-button-disabled-interactive {
  pointer-events: auto;
}
.mat-tonal-button > .mat-icon {
  margin-right: var(--mat-button-tonal-icon-spacing, 8px);
  margin-left: var(--mat-button-tonal-icon-offset, -8px);
}
[dir=rtl] .mat-tonal-button > .mat-icon {
  margin-right: var(--mat-button-tonal-icon-offset, -8px);
  margin-left: var(--mat-button-tonal-icon-spacing, 8px);
}
.mat-tonal-button .mdc-button__label + .mat-icon {
  margin-right: var(--mat-button-tonal-icon-offset, -8px);
  margin-left: var(--mat-button-tonal-icon-spacing, 8px);
}
[dir=rtl] .mat-tonal-button .mdc-button__label + .mat-icon {
  margin-right: var(--mat-button-tonal-icon-spacing, 8px);
  margin-left: var(--mat-button-tonal-icon-offset, -8px);
}
.mat-tonal-button .mat-ripple-element {
  background-color: var(--mat-button-tonal-ripple-color, color-mix(in srgb, var(--mat-sys-on-secondary-container) calc(var(--mat-sys-pressed-state-layer-opacity) * 100%), transparent));
}
.mat-tonal-button .mat-mdc-button-persistent-ripple::before {
  background-color: var(--mat-button-tonal-state-layer-color, var(--mat-sys-on-secondary-container));
}
.mat-tonal-button.mat-mdc-button-disabled .mat-mdc-button-persistent-ripple::before {
  background-color: var(--mat-button-tonal-disabled-state-layer-color, var(--mat-sys-on-surface-variant));
}
.mat-tonal-button:hover > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-tonal-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity));
}
.mat-tonal-button.cdk-program-focused > .mat-mdc-button-persistent-ripple::before, .mat-tonal-button.cdk-keyboard-focused > .mat-mdc-button-persistent-ripple::before, .mat-tonal-button.mat-mdc-button-disabled-interactive:focus > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-tonal-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity));
}
.mat-tonal-button:active > .mat-mdc-button-persistent-ripple::before {
  opacity: var(--mat-button-tonal-pressed-state-layer-opacity, var(--mat-sys-pressed-state-layer-opacity));
}
.mat-tonal-button .mat-mdc-button-touch-target {
  position: absolute;
  top: 50%;
  height: var(--mat-button-tonal-touch-target-size, 48px);
  display: var(--mat-button-tonal-touch-target-display, block);
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
  margin: calc(calc(var(--mat-focus-indicator-border-width, 3px) + 2px) * -1);
}

.mat-mdc-outlined-button .mat-focus-indicator::before {
  margin: calc(calc(var(--mat-focus-indicator-border-width, 3px) + 3px) * -1);
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
`],encapsulation:2,changeDetection:0})}return n})();function Sx(n){return n.hasAttribute("mat-raised-button")?"elevated":n.hasAttribute("mat-stroked-button")?"outlined":n.hasAttribute("mat-flat-button")?"filled":n.hasAttribute("mat-button")?"text":null}var $n=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[xn,ie]})}return n})();function Mx(n,t){if(n&1){let e=ot();g(0,"div",1)(1,"button",2),pe("click",function(){Ke(e);let r=C();return Ye(r.action())}),w(2),f()()}if(n&2){let e=C();p(2),J(" ",e.data.action," ")}}var Ax=["label"];function Ix(n,t){}var Tx=Math.pow(2,31)-1,Vo=class{_overlayRef;instance;containerInstance;_afterDismissed=new z;_afterOpened=new z;_onAction=new z;_durationTimeoutId;_dismissedByAction=!1;constructor(t,e){this._overlayRef=e,this.containerInstance=t,t._onExit.subscribe(()=>this._finishDismiss())}dismiss(){this._afterDismissed.closed||this.containerInstance.exit(),clearTimeout(this._durationTimeoutId)}dismissWithAction(){this._onAction.closed||(this._dismissedByAction=!0,this._onAction.next(),this._onAction.complete(),this.dismiss()),clearTimeout(this._durationTimeoutId)}closeWithAction(){this.dismissWithAction()}_dismissAfter(t){this._durationTimeoutId=setTimeout(()=>this.dismiss(),Math.min(t,Tx))}_open(){this._afterOpened.closed||(this._afterOpened.next(),this._afterOpened.complete())}_finishDismiss(){this._overlayRef.dispose(),this._onAction.closed||this._onAction.complete(),this._afterDismissed.next({dismissedByAction:this._dismissedByAction}),this._afterDismissed.complete(),this._dismissedByAction=!1}afterDismissed(){return this._afterDismissed}afterOpened(){return this.containerInstance._onEnter}onAction(){return this._onAction}},Of=new M("MatSnackBarData"),xr=class{politeness="polite";announcementMessage="";viewContainerRef;duration=0;panelClass;direction;data=null;horizontalPosition="center";verticalPosition="bottom"},Ox=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","matSnackBarLabel",""]],hostAttrs:[1,"mat-mdc-snack-bar-label","mdc-snackbar__label"]})}return n})(),Rx=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","matSnackBarActions",""]],hostAttrs:[1,"mat-mdc-snack-bar-actions","mdc-snackbar__actions"]})}return n})(),Px=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","matSnackBarAction",""]],hostAttrs:[1,"mat-mdc-snack-bar-action","mdc-snackbar__action"]})}return n})(),Rf=(()=>{class n{snackBarRef=m(Vo);data=m(Of);constructor(){}action(){this.snackBarRef.dismissWithAction()}get hasAction(){return!!this.data.action}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["simple-snack-bar"]],hostAttrs:[1,"mat-mdc-simple-snack-bar"],exportAs:["matSnackBar"],decls:3,vars:2,consts:[["matSnackBarLabel",""],["matSnackBarActions",""],["matButton","","matSnackBarAction","",3,"click"]],template:function(i,r){i&1&&(g(0,"div",0),w(1),f(),k(2,Mx,3,1,"div",1)),i&2&&(p(),J(" ",r.data.message,`
`),p(),D(r.hasAction?2:-1))},dependencies:[yr,Ox,Rx,Px],styles:[`.mat-mdc-simple-snack-bar {
  display: flex;
}
.mat-mdc-simple-snack-bar .mat-mdc-snack-bar-label {
  max-height: 50vh;
  overflow: auto;
}
`],encapsulation:2,changeDetection:0})}return n})(),bd="_mat-snack-bar-enter",vd="_mat-snack-bar-exit",Fx=(()=>{class n extends _r{_ngZone=m(K);_elementRef=m(Y);_changeDetectorRef=m(Ue);_platform=m(Ce);_animationsDisabled=He();snackBarConfig=m(xr);_document=m(W);_trackedModals=new Set;_enterFallback;_exitFallback;_injector=m(te);_announceDelay=150;_announceTimeoutId;_destroyed=!1;_portalOutlet;_onAnnounce=new z;_onExit=new z;_onEnter=new z;_animationState="void";_live;_label;_role;_liveElementId=m(ut).getId("mat-snack-bar-container-live-");constructor(){super();let e=this.snackBarConfig;e.politeness==="assertive"&&!e.announcementMessage?this._live="assertive":e.politeness==="off"?this._live="off":this._live="polite",this._platform.FIREFOX&&(this._live==="polite"&&(this._role="status"),this._live==="assertive"&&(this._role="alert"))}attachComponentPortal(e){this._assertNotAttached();let i=this._portalOutlet.attachComponentPortal(e);return this._afterPortalAttached(),i}attachTemplatePortal(e){this._assertNotAttached();let i=this._portalOutlet.attachTemplatePortal(e);return this._afterPortalAttached(),i}attachDomPortal=e=>{this._assertNotAttached();let i=this._portalOutlet.attachDomPortal(e);return this._afterPortalAttached(),i};onAnimationEnd(e){e===vd?this._completeExit():e===bd&&(clearTimeout(this._enterFallback),this._ngZone.run(()=>{this._onEnter.next(),this._onEnter.complete()}))}enter(){this._destroyed||(this._animationState="visible",this._changeDetectorRef.markForCheck(),this._changeDetectorRef.detectChanges(),this._screenReaderAnnounce(),this._animationsDisabled?mt(()=>{this._ngZone.run(()=>queueMicrotask(()=>this.onAnimationEnd(bd)))},{injector:this._injector}):(clearTimeout(this._enterFallback),this._enterFallback=setTimeout(()=>{this._elementRef.nativeElement.classList.add("mat-snack-bar-fallback-visible"),this.onAnimationEnd(bd)},200)))}exit(){return this._destroyed?Pe(void 0):(this._ngZone.run(()=>{this._animationState="hidden",this._changeDetectorRef.markForCheck(),this._elementRef.nativeElement.setAttribute("mat-exit",""),clearTimeout(this._announceTimeoutId),this._animationsDisabled?mt(()=>{this._ngZone.run(()=>queueMicrotask(()=>this.onAnimationEnd(vd)))},{injector:this._injector}):(clearTimeout(this._exitFallback),this._exitFallback=setTimeout(()=>this.onAnimationEnd(vd),200))}),this._onExit)}ngOnDestroy(){this._destroyed=!0,this._clearFromModals(),this._completeExit()}_completeExit(){clearTimeout(this._exitFallback),queueMicrotask(()=>{this._onExit.next(),this._onExit.complete()})}_afterPortalAttached(){let e=this._elementRef.nativeElement,i=this.snackBarConfig.panelClass;i&&(Array.isArray(i)?i.forEach(a=>e.classList.add(a)):e.classList.add(i)),this._exposeToModals();let r=this._label.nativeElement,o="mdc-snackbar__label";r.classList.toggle(o,!r.querySelector(`.${o}`))}_exposeToModals(){let e=this._liveElementId,i=this._document.querySelectorAll('body > .cdk-overlay-container [aria-modal="true"]');for(let r=0;r<i.length;r++){let o=i[r],a=o.getAttribute("aria-owns");this._trackedModals.add(o),a?a.indexOf(e)===-1&&o.setAttribute("aria-owns",a+" "+e):o.setAttribute("aria-owns",e)}}_clearFromModals(){this._trackedModals.forEach(e=>{let i=e.getAttribute("aria-owns");if(i){let r=i.replace(this._liveElementId,"").trim();r.length>0?e.setAttribute("aria-owns",r):e.removeAttribute("aria-owns")}}),this._trackedModals.clear()}_assertNotAttached(){this._portalOutlet.hasAttached()}_screenReaderAnnounce(){this._announceTimeoutId||this._ngZone.runOutsideAngular(()=>{this._announceTimeoutId=setTimeout(()=>{if(this._destroyed)return;let e=this._elementRef.nativeElement,i=e.querySelector("[aria-hidden]"),r=e.querySelector("[aria-live]");if(i&&r){let o=null;this._platform.isBrowser&&document.activeElement instanceof HTMLElement&&i.contains(document.activeElement)&&(o=document.activeElement),i.removeAttribute("aria-hidden"),r.appendChild(i),o?.focus(),this._onAnnounce.next(),this._onAnnounce.complete()}},this._announceDelay)})}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-snack-bar-container"]],viewQuery:function(i,r){if(i&1&&Ne(Os,7)(Ax,7),i&2){let o;H(o=q())&&(r._portalOutlet=o.first),H(o=q())&&(r._label=o.first)}},hostAttrs:[1,"mdc-snackbar","mat-mdc-snack-bar-container"],hostVars:6,hostBindings:function(i,r){i&1&&pe("animationend",function(a){return r.onAnimationEnd(a.animationName)})("animationcancel",function(a){return r.onAnimationEnd(a.animationName)}),i&2&&Q("mat-snack-bar-container-enter",r._animationState==="visible")("mat-snack-bar-container-exit",r._animationState==="hidden")("mat-snack-bar-container-animations-enabled",!r._animationsDisabled)},features:[Te],decls:6,vars:3,consts:[["label",""],[1,"mdc-snackbar__surface","mat-mdc-snackbar-surface"],[1,"mat-mdc-snack-bar-label"],["aria-hidden","true"],["cdkPortalOutlet",""]],template:function(i,r){i&1&&(g(0,"div",1)(1,"div",2,0)(3,"div",3),Vt(4,Ix,0,0,"ng-template",4),f(),P(5,"div"),f()()),i&2&&(p(5),le("aria-live",r._live)("role",r._role)("id",r._liveElementId))},dependencies:[Os],styles:[`@keyframes _mat-snack-bar-enter {
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
  color: var(--mat-snack-bar-supporting-text-color, var(--mat-sys-inverse-on-surface));
  border-radius: var(--mat-snack-bar-container-shape, var(--mat-sys-corner-extra-small));
  background-color: var(--mat-snack-bar-container-color, var(--mat-sys-inverse-surface));
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
  font-family: var(--mat-snack-bar-supporting-text-font, var(--mat-sys-body-medium-font));
  font-size: var(--mat-snack-bar-supporting-text-size, var(--mat-sys-body-medium-size));
  font-weight: var(--mat-snack-bar-supporting-text-weight, var(--mat-sys-body-medium-weight));
  line-height: var(--mat-snack-bar-supporting-text-line-height, var(--mat-sys-body-medium-line-height));
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
  color: var(--mat-snack-bar-button-color, var(--mat-sys-inverse-primary));
}
.mat-mdc-snack-bar-container .mat-mdc-button.mat-mdc-snack-bar-action:not(:disabled) {
  --mat-button-text-state-layer-color: currentColor;
  --mat-button-text-ripple-color: currentColor;
}
.mat-mdc-snack-bar-container .mat-mdc-button.mat-mdc-snack-bar-action:not(:disabled) .mat-ripple-element {
  opacity: 0.1;
}
`],encapsulation:2})}return n})(),Nx=new M("mat-snack-bar-default-options",{providedIn:"root",factory:()=>new xr}),zo=(()=>{class n{_live=m(nd);_injector=m(te);_breakpointObserver=m(ed);_parentSnackBar=m(n,{optional:!0,skipSelf:!0});_defaultConfig=m(Nx);_animationsDisabled=He();_snackBarRefAtThisLevel=null;simpleSnackBarComponent=Rf;snackBarContainerComponent=Fx;handsetCssClass="mat-mdc-snack-bar-handset";get _openedSnackBarRef(){let e=this._parentSnackBar;return e?e._openedSnackBarRef:this._snackBarRefAtThisLevel}set _openedSnackBarRef(e){this._parentSnackBar?this._parentSnackBar._openedSnackBarRef=e:this._snackBarRefAtThisLevel=e}constructor(){}openFromComponent(e,i){return this._attach(e,i)}openFromTemplate(e,i){return this._attach(e,i)}open(e,i="",r){let o=S(S({},this._defaultConfig),r);return o.data={message:e,action:i},o.announcementMessage===e&&(o.announcementMessage=void 0),this.openFromComponent(this.simpleSnackBarComponent,o)}dismiss(){this._openedSnackBarRef&&this._openedSnackBarRef.dismiss()}ngOnDestroy(){this._snackBarRefAtThisLevel&&this._snackBarRefAtThisLevel.dismiss()}_attachSnackBarContainer(e,i){let r=i&&i.viewContainerRef&&i.viewContainerRef.injector,o=te.create({parent:r||this._injector,providers:[{provide:xr,useValue:i}]}),a=new gr(this.snackBarContainerComponent,i.viewContainerRef,o),s=e.attach(a);return s.instance.snackBarConfig=i,s.instance}_attach(e,i){let r=S(S(S({},new xr),this._defaultConfig),i),o=this._createOverlay(r),a=this._attachSnackBarContainer(o,r),s=new Vo(a,o);if(e instanceof Jn){let l=new Bn(e,null,{$implicit:r.data,snackBarRef:s});s.instance=a.attachTemplatePortal(l)}else{let l=this._createInjector(r,s),d=new gr(e,void 0,l),c=a.attachComponentPortal(d);s.instance=c.instance}return this._breakpointObserver.observe(rf.HandsetPortrait).pipe(De(o.detachments())).subscribe(l=>{o.overlayElement.classList.toggle(this.handsetCssClass,l.matches)}),r.announcementMessage&&a._onAnnounce.subscribe(()=>{this._live.announce(r.announcementMessage,r.politeness)}),this._animateSnackBar(s,r),this._openedSnackBarRef=s,this._openedSnackBarRef}_animateSnackBar(e,i){e.afterDismissed().subscribe(()=>{this._openedSnackBarRef==e&&(this._openedSnackBarRef=null),i.announcementMessage&&this._live.clear()}),i.duration&&i.duration>0&&e.afterOpened().subscribe(()=>e._dismissAfter(i.duration)),this._openedSnackBarRef?(this._openedSnackBarRef.afterDismissed().subscribe(()=>{e.containerInstance.enter()}),this._openedSnackBarRef.dismiss()):e.containerInstance.enter()}_createOverlay(e){let i=new ai;i.direction=e.direction;let r=Vs(this._injector),o=e.direction==="rtl",a=e.horizontalPosition==="left"||e.horizontalPosition==="start"&&!o||e.horizontalPosition==="end"&&o,s=!a&&e.horizontalPosition!=="center";return a?r.left("0"):s?r.right("0"):r.centerHorizontally(),e.verticalPosition==="top"?r.top("0"):r.bottom("0"),i.positionStrategy=r,i.disableAnimations=this._animationsDisabled,vr(this._injector,i)}_createInjector(e,i){let r=e&&e.viewContainerRef&&e.viewContainerRef.injector;return te.create({parent:r||this._injector,providers:[{provide:Vo,useValue:i},{provide:Of,useValue:e.data}]})}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var yd=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({providers:[zo],imports:[Oi,br,$n,Rf,ie]})}return n})();var si=class{},Us=class n{constructor(t){this.snackBar=t}showError(t){this.snackBar.open(t,"Close",{verticalPosition:"top"})}showWarning(t){this.snackBar.open(t,"Close",{duration:3e3})}static \u0275fac=function(e){return new(e||n)(j(zo))};static \u0275prov=E({token:n,factory:n.\u0275fac})};var pt=class n{constructor(t){this.notificationService=t}static BASE_URL=window.location.pathname+window.location.search+"#";toAsyncApi(t){try{let e=this.mapChannels(t.channels,t.operations,t.components.messages,t.components.schemas,t.servers,t.defaultContentType),i={info:this.mapInfo(t),servers:this.mapServers(t.servers),channels:e,channelOperations:e.flatMap(r=>r.operations),components:{schemas:this.mapSchemas(t.components.schemas)},defaultContentType:t.defaultContentType};return this.postProcess(i),i}catch(e){this.notificationService.showError("Error parsing AsyncAPI: "+e?.message);return}}mapInfo(t){return{title:t.info.title,version:t.info.version,description:t.info.description,contact:{url:t.info.contact?.url,email:t.info.contact?.email&&{name:t.info.contact.email,href:"mailto:"+t.info.contact.email}||void 0},license:{name:t.info.license?.name,url:t.info.license?.url},asyncApiJson:t}}mapServers(t){let e=new Map;if(t)for(let i in t){let r=t[i];e.set(i,{host:r.host,protocol:r.protocol,anchorIdentifier:Ro+i,anchorUrl:n.BASE_URL+Ro+i})}return e}mapChannels(t,e,i,r,o,a){let s={};for(let l in t)this.parsingErrorBoundary("channel "+l,()=>{let d=t[l],c="channel-"+this.toSafeAnchorIdentifier(l);s[l]={name:d.address,anchorIdentifier:c,anchorUrl:n.BASE_URL+c,operations:[],bindings:d.bindings||{}}});for(let l in e)this.parsingErrorBoundary("operation "+l,()=>{let d=e[l],c=this.resolveRefId(d.channel.$ref),u=t[c].address;this.verifyBindings(d.bindings,"operation "+l),this.mapServerAsyncApiMessages(u,t[c],i,r,d.messages,a).forEach(y=>{let _=this.parsingErrorBoundary("channel with name "+u,()=>this.mapChannelOperation(u,t[c],t,d,y,o));_!=null&&s[c].operations.push(_)})});return Object.values(s).forEach(l=>{l.operations=l.operations.sort((d,c)=>d.operation.protocol===c.operation.protocol?d.operation.operationType===c.operation.operationType?d.name===c.name?d.operation.message.name.localeCompare(c.operation.message.name):d.name.localeCompare(c.name):d.operation.operationType.localeCompare(c.operation.operationType):d.operation.protocol!=null&&c.operation.protocol!=null?d.operation.protocol.localeCompare(c.operation.protocol):0)}),Object.values(s).sort((l,d)=>l.name.localeCompare(d.name))}mapChannelOperation(t,e,i,r,o,a){let l=(e?.servers?.map(u=>this.resolveRefId(u.$ref))||a&&Object.keys(a)||[]).map(u=>({name:u,anchorIdentifier:Ro+u,anchorUrl:n.BASE_URL+Ro+u})),d=this.mapOperation(t,i,r.action,l,o,r.bindings,r.description,r.reply),c=Kc+[d.protocol,this.toSafeAnchorIdentifier(t),d.operationType,d.message.title].join("-");return{name:t,anchorIdentifier:c,anchorUrl:n.BASE_URL+c,description:e.description,operation:d,bindings:e.bindings||{}}}mapServerAsyncApiMessages(t,e,i,r,o,a){return o.map(s=>this.parsingErrorBoundary("message of channel "+t,()=>{let l=this.resolveRefId(s.$ref),d=this.resolveTitleFromName(l),c=e.messages[l],u=this.resolveRefId(c.$ref),h=i[u];return this.verifyBindings(h.bindings,"message "+h.name),{name:h.name||d,title:h.title||d,description:h.description,contentType:h.contentType||a,payload:this.mapPayload(h.name||d,h.payload.schema,r),headers:this.mapHeaders(h.headers),bindings:this.mapServerAsyncApiMessageBindings(h.bindings),rawBindings:h.bindings||{}}})).filter(s=>s!==void 0)}mapHeaders(t){if(t===void 0)return;let e=this.resolveRefId(t.$ref);return{ts_type:"ref",name:e,title:e,anchorUrl:n.BASE_URL+e}}mapPayload(t,e,i){if("$ref"in e){let r=this.resolveRefId(e.$ref);return{ts_type:"ref",name:r,title:this.resolveTitleFromName(r),anchorUrl:n.BASE_URL+r}}return this.mapSchemaObj(t,e,i)}mapServerAsyncApiMessageBindings(t){let e=new Map;return t!==void 0&&Object.keys(t).forEach(i=>{e.set(i,this.mapServerAsyncApiMessageBinding(t[i]))}),e}mapServerAsyncApiMessageBinding(t){let e={};return Object.keys(t).forEach(i=>{let r=t[i];typeof r=="object"?e[i]=this.mapServerAsyncApiMessageBinding(r):e[i]=r}),e}mapOperation(t,e,i,r,o,a,s,l){return{protocol:this.getProtocol(a)||"unsupported-protocol",bindings:a||{},servers:r,operationType:i=="send"?"send":"receive",channelName:t,description:s,message:o,reply:this.mapOperationReply(l,e)}}mapOperationReply(t,e){if(!t)return;let i=this.resolveRefId(t.channel.$ref),r=e[i].address,o=this.resolveRefId(t.messages[0].$ref);return{channelAnchorUrl:n.BASE_URL+Kc+i,channelName:r,messageAnchorUrl:n.BASE_URL+o,messageName:o}}getProtocol(t){if(t!==void 0)return Object.keys(t)[0]}mapSchemas(t){let e=new Map;return Object.entries(t).forEach(([i,r])=>{let o=this.parsingErrorBoundary("schema with name "+i,()=>this.mapSchema(i,r,t));o!=null&&e.set(i,o)}),new Map([...e.entries()].sort((i,r)=>i[1].title.localeCompare(r[1].title)))}mapSchema(t,e,i){return"$ref"in e?this.mapSchemaRef(t,e):this.mapSchemaObj(t,e,i)}mapSchemaObj(t,e,i){let r={};this.addPropertiesToSchema(e,r,i),e.allOf!==void 0&&e.allOf.forEach(s=>{this.addPropertiesToSchema(s,r,i)}),e.anyOf!==void 0&&e.anyOf.length>0&&this.addPropertiesToSchema(e.anyOf[0],r,i),e.oneOf!==void 0&&e.oneOf.length>0&&this.addPropertiesToSchema(e.oneOf[0],r,i);let o=e.items!==void 0?this.mapSchema(t+"[]",e.items,i):void 0,a=e.examples!==void 0&&0<e.examples.length?new qt(e.examples[0]):void 0;return{ts_type:"object",name:t,title:this.resolveTitleFromName(t)||"undefined-title",usedBy:[],anchorIdentifier:this.toSafeAnchorIdentifier(t),anchorUrl:n.BASE_URL+t,description:e.description,deprecated:e.deprecated,enum:e.enum,example:a,type:e.type,format:e.format,properties:r,required:e.required,items:o,minItems:e.minItems,maxItems:e.maxItems,uniqueItems:e.uniqueItems,minLength:e.minLength,maxLength:e.maxLength,pattern:e.pattern,minimum:e.exclusiveMinimum?e.exclusiveMinimum:e.minimum,maximum:e.exclusiveMaximum?e.exclusiveMinimum:e.maximum,exclusiveMinimum:e.minimum==e.exclusiveMinimum,exclusiveMaximum:e.maximum==e.exclusiveMaximum,multipleOf:e.multipleOf}}addPropertiesToSchema(t,e,i){let r=this.resolveSchema(t,i);"properties"in r&&r.properties!==void 0&&Object.entries(r.properties).forEach(([o,a])=>{e[o]=this.mapSchema(o,a,i)})}resolveSchema(t,e){let i=t;for(;"$ref"in i;){let r=this.resolveRefId(i.$ref),o=e[r];if(o!==void 0)i=o;else throw new Error("Schema "+r+" not found")}return i}mapSchemaRef(t,e){let i=this.resolveRefId(e.$ref);return{ts_type:"object",name:t,title:this.resolveTitleFromName(t),usedBy:[],anchorIdentifier:this.toSafeAnchorIdentifier(t),anchorUrl:n.BASE_URL+t,refAnchorUrl:n.BASE_URL+i,refName:i,refTitle:this.resolveTitleFromName(i)}}resolveRefId(t){return t.split("/").pop()}resolveTitleFromName(t){return t.split(".").pop()}verifyBindings(t,e){(t==null||Object.keys(t).length==0)&&this.notificationService.showWarning("No binding defined for "+e)}postProcess(t){t.components.schemas.forEach(e=>{t.channels.forEach(i=>{i.operations.forEach(r=>{r.operation.message.payload.name===e.name&&e.usedBy.push({name:r.name,anchorUrl:r.anchorUrl,type:"channel"}),r.operation.message.headers?.name===e.name&&e.usedBy.push({name:r.name,anchorUrl:r.anchorUrl,type:"channel"})})}),t.components.schemas.forEach(i=>{Object.values(i?.properties||{}).forEach(r=>{r.refName===e.name&&e.usedBy.push({name:i.title,anchorUrl:i.anchorUrl,type:"schema"})}),i.items?.refName===e.name&&e.usedBy.push({name:i.title,anchorUrl:i.anchorUrl,type:"schema"})})})}toSafeAnchorIdentifier(t){return t.replaceAll(/\$/g,"\xA7")}parsingErrorBoundary(t,e){return Bh(e,i=>{this.notificationService.showError("Error parsing AsyncAPI "+t+": "+i.message)})}static \u0275fac=function(e){return new(e||n)(j(si))};static \u0275prov=E({token:n,factory:n.\u0275fac})};var pb=bv(ub());var Ml={$schema:"http://json-schema.org/draft-07/schema#",$ref:"#/definitions/ServerAsyncApi",definitions:{ServerAsyncApi:{type:"object",properties:{asyncapi:{type:"string"},info:{$ref:"#/definitions/ServerAsyncApiInfo"},defaultContentType:{type:"string"},servers:{$ref:"#/definitions/ServerServers"},channels:{$ref:"#/definitions/ServerChannels"},operations:{$ref:"#/definitions/ServerOperations"},components:{$ref:"#/definitions/ServerComponents"}},required:["asyncapi","info","defaultContentType","channels","operations","components"],additionalProperties:!1},ServerAsyncApiInfo:{type:"object",properties:{title:{type:"string"},version:{type:"string"},description:{type:"string"},contact:{type:"object",properties:{name:{type:"string"},url:{type:"string"},email:{type:"string"}},additionalProperties:{}},license:{type:"object",properties:{name:{type:"string"},url:{type:"string"}},additionalProperties:{}},termsOfService:{type:"string"}},required:["title"],additionalProperties:{}},ServerServers:{type:"object",additionalProperties:{type:"object",properties:{host:{type:"string"},protocol:{type:"string"},description:{type:"string"}},required:["host","protocol"],additionalProperties:!1}},ServerChannels:{type:"object",additionalProperties:{$ref:"#/definitions/ServerChannel"}},ServerChannel:{type:"object",properties:{address:{type:"string"},description:{type:"string"},messages:{type:"object",additionalProperties:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},servers:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},bindings:{$ref:"#/definitions/ServerBindings"}},required:["address"],additionalProperties:!1},ServerBindings:{type:"object",additionalProperties:{$ref:"#/definitions/ServerBinding"}},ServerBinding:{type:"object",additionalProperties:{anyOf:[{$ref:"#/definitions/ServerBinding"},{}]}},ServerOperations:{type:"object",additionalProperties:{$ref:"#/definitions/ServerOperation"}},ServerOperation:{type:"object",properties:{action:{type:"string"},title:{type:"string"},description:{type:"string"},channel:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},messages:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},reply:{$ref:"#/definitions/ServerOperationReply"},bindings:{$ref:"#/definitions/ServerBindings"}},required:["action","channel","messages"],additionalProperties:!1},ServerOperationReply:{type:"object",properties:{channel:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},messages:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}}},required:["channel","messages"],additionalProperties:!1},ServerComponents:{type:"object",properties:{schemas:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiSchema"}},messages:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiMessage"}}},required:["schemas","messages"],additionalProperties:!1},ServerAsyncApiSchema:{type:"object",properties:{title:{type:"string"},description:{type:"string"},deprecated:{type:"boolean"},enum:{type:"array",items:{type:["string","null"]}},examples:{type:"array",items:{}},type:{anyOf:[{type:"string"},{type:"array",items:{type:"string"}}]},format:{type:"string"},not:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"},allOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},anyOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},oneOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},properties:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},required:{type:"array",items:{type:"string"}},items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"},minItems:{type:"number"},maxItems:{type:"number"},uniqueItems:{type:"boolean"},minLength:{type:"number"},maxLength:{type:"number"},pattern:{type:"string"},minimum:{type:"number"},maximum:{type:"number"},exclusiveMinimum:{type:"number"},exclusiveMaximum:{type:"number"},multipleOf:{type:"number"}},additionalProperties:!1},ServerAsyncApiSchemaOrRef:{anyOf:[{$ref:"#/definitions/ServerAsyncApiSchema"},{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}]},ServerAsyncApiMessage:{type:"object",properties:{name:{type:"string"},title:{type:"string"},description:{type:"string"},contentType:{type:"string"},payload:{type:"object",properties:{schemaFormat:{type:"string"},schema:{anyOf:[{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},{$ref:"#/definitions/ServerAsyncApiSchema"}]}},required:["schemaFormat","schema"],additionalProperties:!1},headers:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},bindings:{$ref:"#/definitions/ServerBindings"}},required:["payload"],additionalProperties:!1}}};var Nr=class n{constructor(t){this.notificationService=t}ajv=new pb.default({allErrors:!0});_logToConsole=!0;validate(t){let e=JSON.parse(JSON.stringify(t));this.ajv.opts.removeAdditional=!1;let i=this.ajv.compile(Ml);i(e)||this._logToConsole&&console.info("Validation error while parsing AsyncAPI file in Springwolf format (strict mode)",i.errors),this.ajv.removeSchema(Ml),this.ajv.opts.removeAdditional=!0;let o=this.ajv.compile(Ml);o(e)||(this.notificationService.showError("Validation error while parsing AsyncAPI file in Springwolf format (lenient mode), see console logs for details."),this._logToConsole&&console.warn("Validation error while parsing AsyncAPI file in Springwolf format (lenient mode)",o.errors))}static \u0275fac=function(e){return new(e||n)(j(si))};static \u0275prov=E({token:n,factory:n.\u0275fac})};var nt=class n{constructor(t,e,i,r){this.http=t;this.asyncApiMapperService=e;this.uiService=i;this.asyncApiValidatorService=r;this.docs=this.uiService.isGroup$.pipe(jt(o=>{let a=o==we.DEFAULT_GROUP?vn.docs:vn.getDocsForGroupEndpoint(o);return this.http.get(a)}),Xn(o=>this.asyncApiValidatorService.validate(o)),xe(o=>this.asyncApiMapperService.toAsyncApi(o)),Be(o=>o!==void 0),Mn())}docs;getAsyncApi(){return this.docs}static \u0275fac=function(e){return new(e||n)(j(Ht),j(pt),j(we),j(Nr))};static \u0275prov=E({token:n,factory:n.\u0275fac})};var Lr=class n{constructor(t){this.http=t}publishable={};canPublish(t){return this.publishable[t]===void 0&&(this.publishable[t]=this.http.get(vn.getPublishEndpoint(t),{observe:"response"}).pipe(xe(e=>e.status===200),Pa())),this.publishable[t]}publish(t,e,i,r,o,a){let s=vn.getPublishEndpoint(t),l=new Bt().set("topic",e),d={payload:i,type:r,headers:o,bindings:a};return console.log(`Publishing to ${s} with messageBinding ${JSON.stringify(a)} and headers ${JSON.stringify(o)}: ${JSON.stringify(d)}`),this.http.post(s,d,{params:l})}static \u0275fac=function(e){return new(e||n)(j(Ht))};static \u0275prov=E({token:n,factory:n.\u0275fac})};var hb={providers:[bp(),th(),Ac(Ic()),os.production?[]:ep(nh.forRoot(ls,{delay:100})),Wc(),{provide:mr,useClass:vs},nt,pt,Nr,{provide:si,useClass:Us},Lr,{provide:we,useClass:ys}]};var pS=["mat-menu-item",""],hS=[[["mat-icon"],["","matMenuItemIcon",""]],"*"],fS=["mat-icon, [matMenuItemIcon]","*"];function gS(n,t){n&1&&(Ki(),g(0,"svg",2),P(1,"polygon",3),f())}var _S=["*"];function bS(n,t){if(n&1){let e=ot();qe(0,"div",0),Va("click",function(){Ke(e);let r=C();return Ye(r.closed.emit("click"))})("animationstart",function(r){Ke(e);let o=C();return Ye(o._onAnimationStart(r.animationName))})("animationend",function(r){Ke(e);let o=C();return Ye(o._onAnimationDone(r.animationName))})("animationcancel",function(r){Ke(e);let o=C();return Ye(o._onAnimationDone(r.animationName))}),qe(1,"div",1),B(2),Qe()()}if(n&2){let e=C();vt(e._classList),Q("mat-menu-panel-animations-disabled",e._animationsDisabled)("mat-menu-panel-exit-animation",e._panelAnimationState==="void")("mat-menu-panel-animating",e._isAnimating()),ti("id",e.panelId),le("aria-label",e.ariaLabel||null)("aria-labelledby",e.ariaLabelledby||null)("aria-describedby",e.ariaDescribedby||null)}}var lu=new M("MAT_MENU_PANEL"),pa=(()=>{class n{_elementRef=m(Y);_document=m(W);_focusMonitor=m(yn);_parentMenu=m(lu,{optional:!0});_changeDetectorRef=m(Ue);role="menuitem";disabled=!1;disableRipple=!1;_hovered=new z;_focused=new z;_highlighted=!1;_triggersSubmenu=!1;constructor(){m(Je).load(on),this._parentMenu?.addItem?.(this)}focus(e,i){this._focusMonitor&&e?this._focusMonitor.focusVia(this._getHostElement(),e,i):this._getHostElement().focus(i),this._focused.next(this)}ngAfterViewInit(){this._focusMonitor&&this._focusMonitor.monitor(this._elementRef,!1)}ngOnDestroy(){this._focusMonitor&&this._focusMonitor.stopMonitoring(this._elementRef),this._parentMenu&&this._parentMenu.removeItem&&this._parentMenu.removeItem(this),this._hovered.complete(),this._focused.complete()}_getTabIndex(){return this.disabled?"-1":"0"}_getHostElement(){return this._elementRef.nativeElement}_checkDisabled(e){this.disabled&&(e.preventDefault(),e.stopPropagation())}_handleMouseEnter(){this._hovered.next(this)}getLabel(){let e=this._elementRef.nativeElement.cloneNode(!0),i=e.querySelectorAll("mat-icon, .material-icons");for(let r=0;r<i.length;r++)i[r].remove();return e.textContent?.trim()||""}_setHighlighted(e){this._highlighted=e,this._changeDetectorRef.markForCheck()}_setTriggersSubmenu(e){this._triggersSubmenu=e,this._changeDetectorRef.markForCheck()}_hasFocus(){return this._document&&this._document.activeElement===this._getHostElement()}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["","mat-menu-item",""]],hostAttrs:[1,"mat-mdc-menu-item","mat-focus-indicator"],hostVars:8,hostBindings:function(i,r){i&1&&pe("click",function(a){return r._checkDisabled(a)})("mouseenter",function(){return r._handleMouseEnter()}),i&2&&(le("role",r.role)("tabindex",r._getTabIndex())("aria-disabled",r.disabled)("disabled",r.disabled||null),Q("mat-mdc-menu-item-highlighted",r._highlighted)("mat-mdc-menu-item-submenu-trigger",r._triggersSubmenu))},inputs:{role:"role",disabled:[2,"disabled","disabled",Me],disableRipple:[2,"disableRipple","disableRipple",Me]},exportAs:["matMenuItem"],attrs:pS,ngContentSelectors:fS,decls:5,vars:3,consts:[[1,"mat-mdc-menu-item-text"],["matRipple","",1,"mat-mdc-menu-ripple",3,"matRippleDisabled","matRippleTrigger"],["viewBox","0 0 5 10","focusable","false","aria-hidden","true",1,"mat-mdc-menu-submenu-icon"],["points","0,0 5,5 0,10"]],template:function(i,r){i&1&&(ne(hS),B(0),g(1,"span",0),B(2,1),f(),P(3,"div",1),k(4,gS,2,0,":svg:svg",2)),i&2&&(p(3),R("matRippleDisabled",r.disableRipple||r.disabled)("matRippleTrigger",r._getHostElement()),p(),D(r._triggersSubmenu?4:-1))},dependencies:[zs],encapsulation:2,changeDetection:0})}return n})();var vS=new M("MatMenuContent");var yS=new M("mat-menu-default-options",{providedIn:"root",factory:()=>({overlapTrigger:!1,xPosition:"after",yPosition:"below",backdropClass:"cdk-overlay-transparent-backdrop"})}),au="_mat-menu-enter",Al="_mat-menu-exit",$r=(()=>{class n{_elementRef=m(Y);_changeDetectorRef=m(Ue);_injector=m(te);_keyManager;_xPosition;_yPosition;_firstItemFocusRef;_exitFallbackTimeout;_animationsDisabled=He();_allItems;_directDescendantItems=new In;_classList={};_panelAnimationState="void";_animationDone=new z;_isAnimating=X(!1);parentMenu;direction;overlayPanelClass;backdropClass;ariaLabel;ariaLabelledby;ariaDescribedby;get xPosition(){return this._xPosition}set xPosition(e){this._xPosition=e,this.setPositionClasses()}get yPosition(){return this._yPosition}set yPosition(e){this._yPosition=e,this.setPositionClasses()}templateRef;items;lazyContent;overlapTrigger=!1;hasBackdrop;set panelClass(e){let i=this._previousPanelClass,r=S({},this._classList);i&&i.length&&i.split(" ").forEach(o=>{r[o]=!1}),this._previousPanelClass=e,e&&e.length&&(e.split(" ").forEach(o=>{r[o]=!0}),this._elementRef.nativeElement.className=""),this._classList=r}_previousPanelClass;get classList(){return this.panelClass}set classList(e){this.panelClass=e}closed=new Ee;close=this.closed;panelId=m(ut).getId("mat-menu-panel-");constructor(){let e=m(yS);this.overlayPanelClass=e.overlayPanelClass||"",this._xPosition=e.xPosition,this._yPosition=e.yPosition,this.backdropClass=e.backdropClass,this.overlapTrigger=e.overlapTrigger,this.hasBackdrop=e.hasBackdrop}ngOnInit(){this.setPositionClasses()}ngAfterContentInit(){this._updateDirectDescendants(),this._keyManager=new Mi(this._directDescendantItems).withWrap().withTypeAhead().withHomeAndEnd(),this._keyManager.tabOut.subscribe(()=>this.closed.emit("tab")),this._directDescendantItems.changes.pipe(ct(this._directDescendantItems),jt(e=>_t(...e.map(i=>i._focused)))).subscribe(e=>this._keyManager.updateActiveItem(e)),this._directDescendantItems.changes.subscribe(e=>{let i=this._keyManager;if(this._panelAnimationState==="enter"&&i.activeItem?._hasFocus()){let r=e.toArray(),o=Math.max(0,Math.min(r.length-1,i.activeItemIndex||0));r[o]&&!r[o].disabled?i.setActiveItem(o):i.setNextItemActive()}})}ngOnDestroy(){this._keyManager?.destroy(),this._directDescendantItems.destroy(),this.closed.complete(),this._firstItemFocusRef?.destroy(),clearTimeout(this._exitFallbackTimeout)}_hovered(){return this._directDescendantItems.changes.pipe(ct(this._directDescendantItems),jt(i=>_t(...i.map(r=>r._hovered))))}addItem(e){}removeItem(e){}_handleKeydown(e){let i=e.keyCode,r=this._keyManager;switch(i){case 27:oi(e)||(e.preventDefault(),this.closed.emit("keydown"));break;case 37:this.parentMenu&&this.direction==="ltr"&&this.closed.emit("keydown");break;case 39:this.parentMenu&&this.direction==="rtl"&&this.closed.emit("keydown");break;default:(i===38||i===40)&&r.setFocusOrigin("keyboard"),r.onKeydown(e);return}}focusFirstItem(e="program"){this._firstItemFocusRef?.destroy(),this._firstItemFocusRef=mt(()=>{let i=this._resolvePanel();if(!i||!i.contains(document.activeElement)){let r=this._keyManager;r.setFocusOrigin(e).setFirstItemActive(),!r.activeItem&&i&&i.focus()}},{injector:this._injector})}resetActiveItem(){this._keyManager.setActiveItem(-1)}setElevation(e){}setPositionClasses(e=this.xPosition,i=this.yPosition){this._classList=ye(S({},this._classList),{"mat-menu-before":e==="before","mat-menu-after":e==="after","mat-menu-above":i==="above","mat-menu-below":i==="below"}),this._changeDetectorRef.markForCheck()}_onAnimationDone(e){let i=e===Al;(i||e===au)&&(i&&(clearTimeout(this._exitFallbackTimeout),this._exitFallbackTimeout=void 0),this._animationDone.next(i?"void":"enter"),this._isAnimating.set(!1))}_onAnimationStart(e){(e===au||e===Al)&&this._isAnimating.set(!0)}_setIsOpen(e){if(this._panelAnimationState=e?"enter":"void",e){if(this._keyManager.activeItemIndex===0){let i=this._resolvePanel();i&&(i.scrollTop=0)}}else this._animationsDisabled||(this._exitFallbackTimeout=setTimeout(()=>this._onAnimationDone(Al),200));this._animationsDisabled&&setTimeout(()=>{this._onAnimationDone(e?au:Al)}),this._changeDetectorRef.markForCheck()}_updateDirectDescendants(){this._allItems.changes.pipe(ct(this._allItems)).subscribe(e=>{this._directDescendantItems.reset(e.filter(i=>i._parentMenu===this)),this._directDescendantItems.notifyOnChanges()})}_resolvePanel(){let e=null;return this._directDescendantItems.length&&(e=this._directDescendantItems.first._getHostElement().closest('[role="menu"]')),e}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-menu"]],contentQueries:function(i,r,o){if(i&1&&Xe(o,vS,5)(o,pa,5)(o,pa,4),i&2){let a;H(a=q())&&(r.lazyContent=a.first),H(a=q())&&(r._allItems=a),H(a=q())&&(r.items=a)}},viewQuery:function(i,r){if(i&1&&Ne(Jn,5),i&2){let o;H(o=q())&&(r.templateRef=o.first)}},hostVars:3,hostBindings:function(i,r){i&2&&le("aria-label",null)("aria-labelledby",null)("aria-describedby",null)},inputs:{backdropClass:"backdropClass",ariaLabel:[0,"aria-label","ariaLabel"],ariaLabelledby:[0,"aria-labelledby","ariaLabelledby"],ariaDescribedby:[0,"aria-describedby","ariaDescribedby"],xPosition:"xPosition",yPosition:"yPosition",overlapTrigger:[2,"overlapTrigger","overlapTrigger",Me],hasBackdrop:[2,"hasBackdrop","hasBackdrop",e=>e==null?null:Me(e)],panelClass:[0,"class","panelClass"],classList:"classList"},outputs:{closed:"closed",close:"close"},exportAs:["matMenu"],features:[ze([{provide:lu,useExisting:n}])],ngContentSelectors:_S,decls:1,vars:0,consts:[["tabindex","-1","role","menu",1,"mat-mdc-menu-panel",3,"click","animationstart","animationend","animationcancel","id"],[1,"mat-mdc-menu-content"]],template:function(i,r){i&1&&(ne(),sc(0,bS,3,12,"ng-template"))},styles:[`mat-menu {
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
  font-family: var(--mat-menu-item-label-text-font, var(--mat-sys-label-large-font));
  line-height: var(--mat-menu-item-label-text-line-height, var(--mat-sys-label-large-line-height));
  font-size: var(--mat-menu-item-label-text-size, var(--mat-sys-label-large-size));
  letter-spacing: var(--mat-menu-item-label-text-tracking, var(--mat-sys-label-large-tracking));
  font-weight: var(--mat-menu-item-label-text-weight, var(--mat-sys-label-large-weight));
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
  border-radius: var(--mat-menu-container-shape, var(--mat-sys-corner-extra-small));
  background-color: var(--mat-menu-container-color, var(--mat-sys-surface-container));
  box-shadow: var(--mat-menu-container-elevation-shadow, 0px 3px 1px -2px rgba(0, 0, 0, 0.2), 0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12));
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
  border-top-color: var(--mat-menu-divider-color, var(--mat-sys-surface-variant));
  margin-bottom: var(--mat-menu-divider-bottom-spacing, 8px);
  margin-top: var(--mat-menu-divider-top-spacing, 8px);
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
  padding-left: var(--mat-menu-item-leading-spacing, 12px);
  padding-right: var(--mat-menu-item-trailing-spacing, 12px);
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
  padding-left: var(--mat-menu-item-trailing-spacing, 12px);
  padding-right: var(--mat-menu-item-leading-spacing, 12px);
}
.mat-mdc-menu-item:has(.material-icons, mat-icon, [matButtonIcon]) {
  padding-left: var(--mat-menu-item-with-icon-leading-spacing, 12px);
  padding-right: var(--mat-menu-item-with-icon-trailing-spacing, 12px);
}
[dir=rtl] .mat-mdc-menu-item:has(.material-icons, mat-icon, [matButtonIcon]) {
  padding-left: var(--mat-menu-item-with-icon-trailing-spacing, 12px);
  padding-right: var(--mat-menu-item-with-icon-leading-spacing, 12px);
}
.mat-mdc-menu-item, .mat-mdc-menu-item:visited, .mat-mdc-menu-item:link {
  color: var(--mat-menu-item-label-text-color, var(--mat-sys-on-surface));
}
.mat-mdc-menu-item .mat-icon-no-color,
.mat-mdc-menu-item .mat-mdc-menu-submenu-icon {
  color: var(--mat-menu-item-icon-color, var(--mat-sys-on-surface-variant));
}
.mat-mdc-menu-item[disabled] {
  cursor: default;
  opacity: 0.38;
}
.mat-mdc-menu-item[disabled]::after {
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
  margin-right: var(--mat-menu-item-spacing, 12px);
  height: var(--mat-menu-item-icon-size, 24px);
  width: var(--mat-menu-item-icon-size, 24px);
}
[dir=rtl] .mat-mdc-menu-item {
  text-align: right;
}
[dir=rtl] .mat-mdc-menu-item .mat-icon {
  margin-right: 0;
  margin-left: var(--mat-menu-item-spacing, 12px);
}
.mat-mdc-menu-item:not([disabled]):hover {
  background-color: var(--mat-menu-item-hover-state-layer-color, color-mix(in srgb, var(--mat-sys-on-surface) calc(var(--mat-sys-hover-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-menu-item:not([disabled]).cdk-program-focused, .mat-mdc-menu-item:not([disabled]).cdk-keyboard-focused, .mat-mdc-menu-item:not([disabled]).mat-mdc-menu-item-highlighted {
  background-color: var(--mat-menu-item-focus-state-layer-color, color-mix(in srgb, var(--mat-sys-on-surface) calc(var(--mat-sys-focus-state-layer-opacity) * 100%), transparent));
}
@media (forced-colors: active) {
  .mat-mdc-menu-item {
    margin-top: 1px;
  }
}

.mat-mdc-menu-submenu-icon {
  width: var(--mat-menu-item-icon-size, 24px);
  height: 10px;
  fill: currentColor;
  padding-left: var(--mat-menu-item-spacing, 12px);
}
[dir=rtl] .mat-mdc-menu-submenu-icon {
  padding-right: var(--mat-menu-item-spacing, 12px);
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
`],encapsulation:2,changeDetection:0})}return n})(),xS=new M("mat-menu-scroll-strategy",{providedIn:"root",factory:()=>{let n=m(te);return()=>$o(n)}});var Br=new WeakMap,wS=(()=>{class n{_canHaveBackdrop;_element=m(Y);_viewContainerRef=m(pn);_menuItemInstance=m(pa,{optional:!0,self:!0});_dir=m(wt,{optional:!0});_focusMonitor=m(yn);_ngZone=m(K);_injector=m(te);_scrollStrategy=m(xS);_changeDetectorRef=m(Ue);_animationsDisabled=He();_portal;_overlayRef=null;_menuOpen=!1;_closingActionsSubscription=lt.EMPTY;_menuCloseSubscription=lt.EMPTY;_pendingRemoval;_parentMaterialMenu;_parentInnerPadding;_openedBy=void 0;get _menu(){return this._menuInternal}set _menu(e){e!==this._menuInternal&&(this._menuInternal=e,this._menuCloseSubscription.unsubscribe(),e&&(this._parentMaterialMenu,this._menuCloseSubscription=e.close.subscribe(i=>{this._destroyMenu(i),(i==="click"||i==="tab")&&this._parentMaterialMenu&&this._parentMaterialMenu.closed.emit(i)})),this._menuItemInstance?._setTriggersSubmenu(this._triggersSubmenu()))}_menuInternal=null;constructor(e){this._canHaveBackdrop=e;let i=m(lu,{optional:!0});this._parentMaterialMenu=i instanceof $r?i:void 0}ngOnDestroy(){this._menu&&this._ownsMenu(this._menu)&&Br.delete(this._menu),this._pendingRemoval?.unsubscribe(),this._menuCloseSubscription.unsubscribe(),this._closingActionsSubscription.unsubscribe(),this._overlayRef&&(this._overlayRef.dispose(),this._overlayRef=null)}get menuOpen(){return this._menuOpen}get dir(){return this._dir&&this._dir.value==="rtl"?"rtl":"ltr"}_triggersSubmenu(){return!!(this._menuItemInstance&&this._parentMaterialMenu&&this._menu)}_closeMenu(){this._menu?.close.emit()}_openMenu(e){if(this._triggerIsAriaDisabled())return;let i=this._menu;if(this._menuOpen||!i)return;this._pendingRemoval?.unsubscribe();let r=Br.get(i);Br.set(i,this),r&&r!==this&&r._closeMenu();let o=this._createOverlay(i),a=o.getConfig(),s=a.positionStrategy;this._setPosition(i,s),this._canHaveBackdrop?a.hasBackdrop=i.hasBackdrop==null?!this._triggersSubmenu():i.hasBackdrop:a.hasBackdrop=i.hasBackdrop??!1,o.hasAttached()||(o.attach(this._getPortal(i)),i.lazyContent?.attach(this.menuData)),this._closingActionsSubscription=this._menuClosingActions().subscribe(()=>this._closeMenu()),i.parentMenu=this._triggersSubmenu()?this._parentMaterialMenu:void 0,i.direction=this.dir,e&&i.focusFirstItem(this._openedBy||"program"),this._setIsMenuOpen(!0),i instanceof $r&&(i._setIsOpen(!0),i._directDescendantItems.changes.pipe(De(i.close)).subscribe(()=>{s.withLockedPosition(!1).reapplyLastPosition(),s.withLockedPosition(!0)}))}focus(e,i){this._focusMonitor&&e?this._focusMonitor.focusVia(this._element,e,i):this._element.nativeElement.focus(i)}_destroyMenu(e){let i=this._overlayRef,r=this._menu;!i||!this.menuOpen||(this._closingActionsSubscription.unsubscribe(),this._pendingRemoval?.unsubscribe(),r instanceof $r&&this._ownsMenu(r)?(this._pendingRemoval=r._animationDone.pipe(Qn(1)).subscribe(()=>{i.detach(),Br.has(r)||r.lazyContent?.detach()}),r._setIsOpen(!1)):(i.detach(),r?.lazyContent?.detach()),r&&this._ownsMenu(r)&&Br.delete(r),this.restoreFocus&&(e==="keydown"||!this._openedBy||!this._triggersSubmenu())&&this.focus(this._openedBy),this._openedBy=void 0,this._setIsMenuOpen(!1))}_setIsMenuOpen(e){e!==this._menuOpen&&(this._menuOpen=e,this._menuOpen?this.menuOpened.emit():this.menuClosed.emit(),this._triggersSubmenu()&&this._menuItemInstance._setHighlighted(e),this._changeDetectorRef.markForCheck())}_createOverlay(e){if(!this._overlayRef){let i=this._getOverlayConfig(e);this._subscribeToPositions(e,i.positionStrategy),this._overlayRef=vr(this._injector,i),this._overlayRef.keydownEvents().subscribe(r=>{this._menu instanceof $r&&this._menu._handleKeydown(r)})}return this._overlayRef}_getOverlayConfig(e){return new ai({positionStrategy:js(this._injector,this._getOverlayOrigin()).withLockedPosition().withGrowAfterOpen().withTransformOriginOn(".mat-menu-panel, .mat-mdc-menu-panel"),backdropClass:e.backdropClass||"cdk-overlay-transparent-backdrop",panelClass:e.overlayPanelClass,scrollStrategy:this._scrollStrategy(),direction:this._dir||"ltr",disableAnimations:this._animationsDisabled})}_subscribeToPositions(e,i){e.setPositionClasses&&i.positionChanges.subscribe(r=>{this._ngZone.run(()=>{let o=r.connectionPair.overlayX==="start"?"after":"before",a=r.connectionPair.overlayY==="top"?"below":"above";e.setPositionClasses(o,a)})})}_setPosition(e,i){let[r,o]=e.xPosition==="before"?["end","start"]:["start","end"],[a,s]=e.yPosition==="above"?["bottom","top"]:["top","bottom"],[l,d]=[a,s],[c,u]=[r,o],h=0;if(this._triggersSubmenu()){if(u=r=e.xPosition==="before"?"start":"end",o=c=r==="end"?"start":"end",this._parentMaterialMenu){if(this._parentInnerPadding==null){let y=this._parentMaterialMenu.items.first;this._parentInnerPadding=y?y._getHostElement().offsetTop:0}h=a==="bottom"?this._parentInnerPadding:-this._parentInnerPadding}}else e.overlapTrigger||(l=a==="top"?"bottom":"top",d=s==="top"?"bottom":"top");i.withPositions([{originX:r,originY:l,overlayX:c,overlayY:a,offsetY:h},{originX:o,originY:l,overlayX:u,overlayY:a,offsetY:h},{originX:r,originY:d,overlayX:c,overlayY:s,offsetY:-h},{originX:o,originY:d,overlayX:u,overlayY:s,offsetY:-h}])}_menuClosingActions(){let e=this._getOutsideClickStream(this._overlayRef),i=this._overlayRef.detachments(),r=this._parentMaterialMenu?this._parentMaterialMenu.closed:Pe(),o=this._parentMaterialMenu?this._parentMaterialMenu._hovered().pipe(Be(a=>this._menuOpen&&a!==this._menuItemInstance)):Pe();return _t(e,r,o,i)}_getPortal(e){return(!this._portal||this._portal.templateRef!==e.templateRef)&&(this._portal=new Bn(e.templateRef,this._viewContainerRef)),this._portal}_ownsMenu(e){return Br.get(e)===this}_triggerIsAriaDisabled(){return Me(this._element.nativeElement.getAttribute("aria-disabled"))}static \u0275fac=function(i){$a()};static \u0275dir=G({type:n})}return n})(),fb=(()=>{class n extends wS{_cleanupTouchstart;_hoverSubscription=lt.EMPTY;get _deprecatedMatMenuTriggerFor(){return this.menu}set _deprecatedMatMenuTriggerFor(e){this.menu=e}get menu(){return this._menu}set menu(e){this._menu=e}menuData;restoreFocus=!0;menuOpened=new Ee;onMenuOpen=this.menuOpened;menuClosed=new Ee;onMenuClose=this.menuClosed;constructor(){super(!0);let e=m(rt);this._cleanupTouchstart=e.listen(this._element.nativeElement,"touchstart",i=>{Ei(i)||(this._openedBy="touch")},{passive:!0})}triggersSubmenu(){return super._triggersSubmenu()}toggleMenu(){return this.menuOpen?this.closeMenu():this.openMenu()}openMenu(){this._openMenu(!0)}closeMenu(){this._closeMenu()}updatePosition(){this._overlayRef?.updatePosition()}ngAfterContentInit(){this._handleHover()}ngOnDestroy(){super.ngOnDestroy(),this._cleanupTouchstart(),this._hoverSubscription.unsubscribe()}_getOverlayOrigin(){return this._element}_getOutsideClickStream(e){return e.backdropClick()}_handleMousedown(e){Di(e)||(this._openedBy=e.button===0?"mouse":void 0,this.triggersSubmenu()&&e.preventDefault())}_handleKeydown(e){let i=e.keyCode;(i===13||i===32)&&(this._openedBy="keyboard"),this.triggersSubmenu()&&(i===39&&this.dir==="ltr"||i===37&&this.dir==="rtl")&&(this._openedBy="keyboard",this.openMenu())}_handleClick(e){this.triggersSubmenu()?(e.stopPropagation(),this.openMenu()):this.toggleMenu()}_handleHover(){this.triggersSubmenu()&&this._parentMaterialMenu&&(this._hoverSubscription=this._parentMaterialMenu._hovered().subscribe(e=>{e===this._menuItemInstance&&!e.disabled&&this._parentMaterialMenu?._panelAnimationState!=="void"&&(this._openedBy="mouse",this._openMenu(!1))}))}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","mat-menu-trigger-for",""],["","matMenuTriggerFor",""]],hostAttrs:[1,"mat-mdc-menu-trigger"],hostVars:3,hostBindings:function(i,r){i&1&&pe("click",function(a){return r._handleClick(a)})("mousedown",function(a){return r._handleMousedown(a)})("keydown",function(a){return r._handleKeydown(a)}),i&2&&le("aria-haspopup",r.menu?"menu":null)("aria-expanded",r.menuOpen)("aria-controls",r.menuOpen?r.menu==null?null:r.menu.panelId:null)},inputs:{_deprecatedMatMenuTriggerFor:[0,"mat-menu-trigger-for","_deprecatedMatMenuTriggerFor"],menu:[0,"matMenuTriggerFor","menu"],menuData:[0,"matMenuTriggerData","menuData"],restoreFocus:[0,"matMenuTriggerRestoreFocus","restoreFocus"]},outputs:{menuOpened:"menuOpened",onMenuOpen:"onMenuOpen",menuClosed:"menuClosed",onMenuClose:"onMenuClose"},exportAs:["matMenuTrigger"],features:[Te]})}return n})();var ha=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[xn,Oi,ie,Nn]})}return n})();var kS=["*",[["mat-toolbar-row"]]],DS=["*","mat-toolbar-row"],ES=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["mat-toolbar-row"]],hostAttrs:[1,"mat-toolbar-row"],exportAs:["matToolbarRow"]})}return n})(),gb=(()=>{class n{_elementRef=m(Y);_platform=m(Ce);_document=m(W);color;_toolbarRows;constructor(){}ngAfterViewInit(){this._platform.isBrowser&&(this._checkToolbarMixedModes(),this._toolbarRows.changes.subscribe(()=>this._checkToolbarMixedModes()))}_checkToolbarMixedModes(){this._toolbarRows.length}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-toolbar"]],contentQueries:function(i,r,o){if(i&1&&Xe(o,ES,5),i&2){let a;H(a=q())&&(r._toolbarRows=a)}},hostAttrs:[1,"mat-toolbar"],hostVars:6,hostBindings:function(i,r){i&2&&(vt(r.color?"mat-"+r.color:""),Q("mat-toolbar-multiple-rows",r._toolbarRows.length>0)("mat-toolbar-single-row",r._toolbarRows.length===0))},inputs:{color:"color"},exportAs:["matToolbar"],ngContentSelectors:DS,decls:2,vars:0,template:function(i,r){i&1&&(ne(kS),B(0),B(1,1))},styles:[`.mat-toolbar {
  background: var(--mat-toolbar-container-background-color, var(--mat-sys-surface));
  color: var(--mat-toolbar-container-text-color, var(--mat-sys-on-surface));
}
.mat-toolbar, .mat-toolbar h1, .mat-toolbar h2, .mat-toolbar h3, .mat-toolbar h4, .mat-toolbar h5, .mat-toolbar h6 {
  font-family: var(--mat-toolbar-title-text-font, var(--mat-sys-title-large-font));
  font-size: var(--mat-toolbar-title-text-size, var(--mat-sys-title-large-size));
  line-height: var(--mat-toolbar-title-text-line-height, var(--mat-sys-title-large-line-height));
  font-weight: var(--mat-toolbar-title-text-weight, var(--mat-sys-title-large-weight));
  letter-spacing: var(--mat-toolbar-title-text-tracking, var(--mat-sys-title-large-tracking));
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
  --mat-button-text-label-text-color: var(--mat-toolbar-container-text-color, var(--mat-sys-on-surface));
  --mat-button-outlined-label-text-color: var(--mat-toolbar-container-text-color, var(--mat-sys-on-surface));
}

.mat-toolbar-row, .mat-toolbar-single-row {
  display: flex;
  box-sizing: border-box;
  padding: 0 16px;
  width: 100%;
  flex-direction: row;
  align-items: center;
  white-space: nowrap;
  height: var(--mat-toolbar-standard-height, 64px);
}
@media (max-width: 599px) {
  .mat-toolbar-row, .mat-toolbar-single-row {
    height: var(--mat-toolbar-mobile-height, 56px);
  }
}

.mat-toolbar-multiple-rows {
  display: flex;
  box-sizing: border-box;
  flex-direction: column;
  width: 100%;
  min-height: var(--mat-toolbar-standard-height, 64px);
}
@media (max-width: 599px) {
  .mat-toolbar-multiple-rows {
    min-height: var(--mat-toolbar-mobile-height, 56px);
  }
}
`],encapsulation:2,changeDetection:0})}return n})();var fa=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[ie]})}return n})();var MS=["*"];var AS=[[["","mat-card-avatar",""],["","matCardAvatar",""]],[["mat-card-title"],["mat-card-subtitle"],["","mat-card-title",""],["","mat-card-subtitle",""],["","matCardTitle",""],["","matCardSubtitle",""]],"*"],IS=["[mat-card-avatar], [matCardAvatar]",`mat-card-title, mat-card-subtitle,
      [mat-card-title], [mat-card-subtitle],
      [matCardTitle], [matCardSubtitle]`,"*"],TS=new M("MAT_CARD_CONFIG"),Vr=(()=>{class n{appearance;constructor(){let e=m(TS,{optional:!0});this.appearance=e?.appearance||"raised"}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-card"]],hostAttrs:[1,"mat-mdc-card","mdc-card"],hostVars:8,hostBindings:function(i,r){i&2&&Q("mat-mdc-card-outlined",r.appearance==="outlined")("mdc-card--outlined",r.appearance==="outlined")("mat-mdc-card-filled",r.appearance==="filled")("mdc-card--filled",r.appearance==="filled")},inputs:{appearance:"appearance"},exportAs:["matCard"],ngContentSelectors:MS,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},styles:[`.mat-mdc-card {
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  position: relative;
  border-style: solid;
  border-width: 0;
  background-color: var(--mat-card-elevated-container-color, var(--mat-sys-surface-container-low));
  border-color: var(--mat-card-elevated-container-color, var(--mat-sys-surface-container-low));
  border-radius: var(--mat-card-elevated-container-shape, var(--mat-sys-corner-medium));
  box-shadow: var(--mat-card-elevated-container-elevation, var(--mat-sys-level1));
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
  border-radius: var(--mat-card-elevated-container-shape, var(--mat-sys-corner-medium));
}

.mat-mdc-card-outlined {
  background-color: var(--mat-card-outlined-container-color, var(--mat-sys-surface));
  border-radius: var(--mat-card-outlined-container-shape, var(--mat-sys-corner-medium));
  border-width: var(--mat-card-outlined-outline-width, 1px);
  border-color: var(--mat-card-outlined-outline-color, var(--mat-sys-outline-variant));
  box-shadow: var(--mat-card-outlined-container-elevation, var(--mat-sys-level0));
}
.mat-mdc-card-outlined::after {
  border: none;
}

.mat-mdc-card-filled {
  background-color: var(--mat-card-filled-container-color, var(--mat-sys-surface-container-highest));
  border-radius: var(--mat-card-filled-container-shape, var(--mat-sys-corner-medium));
  box-shadow: var(--mat-card-filled-container-elevation, var(--mat-sys-level0));
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
  font-family: var(--mat-card-title-text-font, var(--mat-sys-title-large-font));
  line-height: var(--mat-card-title-text-line-height, var(--mat-sys-title-large-line-height));
  font-size: var(--mat-card-title-text-size, var(--mat-sys-title-large-size));
  letter-spacing: var(--mat-card-title-text-tracking, var(--mat-sys-title-large-tracking));
  font-weight: var(--mat-card-title-text-weight, var(--mat-sys-title-large-weight));
}

.mat-mdc-card-subtitle {
  color: var(--mat-card-subtitle-text-color, var(--mat-sys-on-surface));
  font-family: var(--mat-card-subtitle-text-font, var(--mat-sys-title-medium-font));
  line-height: var(--mat-card-subtitle-text-line-height, var(--mat-sys-title-medium-line-height));
  font-size: var(--mat-card-subtitle-text-size, var(--mat-sys-title-medium-size));
  letter-spacing: var(--mat-card-subtitle-text-tracking, var(--mat-sys-title-medium-tracking));
  font-weight: var(--mat-card-subtitle-text-weight, var(--mat-sys-title-medium-weight));
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
`],encapsulation:2,changeDetection:0})}return n})(),zr=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["mat-card-title"],["","mat-card-title",""],["","matCardTitle",""]],hostAttrs:[1,"mat-mdc-card-title"]})}return n})();var Hr=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["mat-card-content"]],hostAttrs:[1,"mat-mdc-card-content"]})}return n})();var qr=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-card-header"]],hostAttrs:[1,"mat-mdc-card-header"],ngContentSelectors:IS,decls:4,vars:0,consts:[[1,"mat-mdc-card-header-text"]],template:function(i,r){i&1&&(ne(AS),B(0),qe(1,"div",0),B(2,1),Qe(),B(3,2))},encapsulation:2,changeDetection:0})}return n})();var Un=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[ie]})}return n})();var _b=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({})}return n})();var du=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[_b,br,ie]})}return n})();var mu=class{_box;_destroyed=new z;_resizeSubject=new z;_resizeObserver;_elementObservables=new Map;constructor(t){this._box=t,typeof ResizeObserver<"u"&&(this._resizeObserver=new ResizeObserver(e=>this._resizeSubject.next(e)))}observe(t){return this._elementObservables.has(t)||this._elementObservables.set(t,new St(e=>{let i=this._resizeSubject.subscribe(e);return this._resizeObserver?.observe(t,{box:this._box}),()=>{this._resizeObserver?.unobserve(t),i.unsubscribe(),this._elementObservables.delete(t)}}).pipe(Be(e=>e.some(i=>i.target===t)),Mn({bufferSize:1,refCount:!0}),De(this._destroyed))),this._elementObservables.get(t)}destroy(){this._destroyed.next(),this._destroyed.complete(),this._resizeSubject.complete(),this._elementObservables.clear()}},bb=(()=>{class n{_cleanupErrorListener;_observers=new Map;_ngZone=m(K);constructor(){typeof ResizeObserver<"u"}ngOnDestroy(){for(let[,e]of this._observers)e.destroy();this._observers.clear(),this._cleanupErrorListener?.()}observe(e,i){let r=i?.box||"content-box";return this._observers.has(r)||this._observers.set(r,new mu(r)),this._observers.get(r).observe(e)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var uu=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[ie]})}return n})();var ga=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[ie]})}return n})();var pu=class{_document;_textarea;constructor(t,e){this._document=e;let i=this._textarea=this._document.createElement("textarea"),r=i.style;r.position="fixed",r.top=r.opacity="0",r.left="-999em",i.setAttribute("aria-hidden","true"),i.value=t,i.readOnly=!0,(this._document.fullscreenElement||this._document.body).appendChild(i)}copy(){let t=this._textarea,e=!1;try{if(t){let i=this._document.activeElement;t.select(),t.setSelectionRange(0,t.value.length),e=this._document.execCommand("copy"),i&&i.focus()}}catch{}return e}destroy(){let t=this._textarea;t&&(t.remove(),this._textarea=void 0)}},OS=(()=>{class n{_document=m(W);constructor(){}copy(e){let i=this.beginCopy(e),r=i.copy();return i.destroy(),r}beginCopy(e){return new pu(e,this._document)}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})(),RS=new M("CDK_COPY_TO_CLIPBOARD_CONFIG"),vb=(()=>{class n{_clipboard=m(OS);_ngZone=m(K);text="";attempts=1;copied=new Ee;_pending=new Set;_destroyed=!1;_currentTimeout;constructor(){let e=m(RS,{optional:!0});e&&e.attempts!=null&&(this.attempts=e.attempts)}copy(e=this.attempts){if(e>1){let i=e,r=this._clipboard.beginCopy(this.text);this._pending.add(r);let o=()=>{let a=r.copy();!a&&--i&&!this._destroyed?this._currentTimeout=this._ngZone.runOutsideAngular(()=>setTimeout(o,1)):(this._currentTimeout=null,this._pending.delete(r),r.destroy(),this.copied.emit(a))};o()}else this.copied.emit(this._clipboard.copy(this.text))}ngOnDestroy(){this._currentTimeout&&clearTimeout(this._currentTimeout),this._pending.forEach(e=>e.destroy()),this._pending.clear(),this._destroyed=!0}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","cdkCopyToClipboard",""]],hostBindings:function(i,r){i&1&&pe("click",function(){return r.copy()})},inputs:{text:[0,"cdkCopyToClipboard","text"],attempts:[0,"cdkCopyToClipboardAttempts","attempts"]},outputs:{copied:"cdkCopyToClipboardCopied"}})}return n})(),_a=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({})}return n})();var FS=["notch"],NS=["matFormFieldNotchedOutline",""],LS=["*"],yb=["iconPrefixContainer"],xb=["textPrefixContainer"],wb=["iconSuffixContainer"],Cb=["textSuffixContainer"],BS=["textField"],$S=["*",[["mat-label"]],[["","matPrefix",""],["","matIconPrefix",""]],[["","matTextPrefix",""]],[["","matTextSuffix",""]],[["","matSuffix",""],["","matIconSuffix",""]],[["mat-error"],["","matError",""]],[["mat-hint",3,"align","end"]],[["mat-hint","align","end"]]],jS=["*","mat-label","[matPrefix], [matIconPrefix]","[matTextPrefix]","[matTextSuffix]","[matSuffix], [matIconSuffix]","mat-error, [matError]","mat-hint:not([align='end'])","mat-hint[align='end']"];function VS(n,t){n&1&&P(0,"span",21)}function zS(n,t){if(n&1&&(g(0,"label",20),B(1,1),k(2,VS,1,0,"span",21),f()),n&2){let e=C(2);R("floating",e._shouldLabelFloat())("monitorResize",e._hasOutline())("id",e._labelId),le("for",e._control.disableAutomaticLabeling?null:e._control.id),p(2),D(!e.hideRequiredMarker&&e._control.required?2:-1)}}function HS(n,t){if(n&1&&k(0,zS,3,5,"label",20),n&2){let e=C();D(e._hasFloatingLabel()?0:-1)}}function qS(n,t){n&1&&P(0,"div",7)}function US(n,t){}function GS(n,t){if(n&1&&Vt(0,US,0,0,"ng-template",13),n&2){C(2);let e=zt(1);R("ngTemplateOutlet",e)}}function WS(n,t){if(n&1&&(g(0,"div",9),k(1,GS,1,1,null,13),f()),n&2){let e=C();R("matFormFieldNotchedOutlineOpen",e._shouldLabelFloat()),p(),D(e._forceDisplayInfixLabel()?-1:1)}}function KS(n,t){n&1&&(g(0,"div",10,2),B(2,2),f())}function YS(n,t){n&1&&(g(0,"div",11,3),B(2,3),f())}function QS(n,t){}function XS(n,t){if(n&1&&Vt(0,QS,0,0,"ng-template",13),n&2){C();let e=zt(1);R("ngTemplateOutlet",e)}}function ZS(n,t){n&1&&(g(0,"div",14,4),B(2,4),f())}function JS(n,t){n&1&&(g(0,"div",15,5),B(2,5),f())}function e1(n,t){n&1&&P(0,"div",16)}function t1(n,t){n&1&&(g(0,"div",18),B(1,6),f())}function n1(n,t){if(n&1&&(g(0,"mat-hint",22),w(1),f()),n&2){let e=C(2);R("id",e._hintLabelId),p(),Le(e.hintLabel)}}function i1(n,t){if(n&1&&(g(0,"div",19),k(1,n1,2,2,"mat-hint",22),B(2,7),P(3,"div",23),B(4,8),f()),n&2){let e=C();p(),D(e.hintLabel?1:-1)}}var hu=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["mat-label"]]})}return n})(),r1=new M("MatError");var fu=(()=>{class n{align="start";id=m(ut).getId("mat-mdc-hint-");static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["mat-hint"]],hostAttrs:[1,"mat-mdc-form-field-hint","mat-mdc-form-field-bottom-align"],hostVars:4,hostBindings:function(i,r){i&2&&(ti("id",r.id),le("align",null),Q("mat-mdc-form-field-hint-end",r.align==="end"))},inputs:{align:"align",id:"id"}})}return n})(),o1=new M("MatPrefix");var a1=new M("MatSuffix");var Ib=new M("FloatingLabelParent"),kb=(()=>{class n{_elementRef=m(Y);get floating(){return this._floating}set floating(e){this._floating=e,this.monitorResize&&this._handleResize()}_floating=!1;get monitorResize(){return this._monitorResize}set monitorResize(e){this._monitorResize=e,this._monitorResize?this._subscribeToResize():this._resizeSubscription.unsubscribe()}_monitorResize=!1;_resizeObserver=m(bb);_ngZone=m(K);_parent=m(Ib);_resizeSubscription=new lt;constructor(){}ngOnDestroy(){this._resizeSubscription.unsubscribe()}getWidth(){return s1(this._elementRef.nativeElement)}get element(){return this._elementRef.nativeElement}_handleResize(){setTimeout(()=>this._parent._handleLabelResized())}_subscribeToResize(){this._resizeSubscription.unsubscribe(),this._ngZone.runOutsideAngular(()=>{this._resizeSubscription=this._resizeObserver.observe(this._elementRef.nativeElement,{box:"border-box"}).subscribe(()=>this._handleResize())})}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["label","matFormFieldFloatingLabel",""]],hostAttrs:[1,"mdc-floating-label","mat-mdc-floating-label"],hostVars:2,hostBindings:function(i,r){i&2&&Q("mdc-floating-label--float-above",r.floating)},inputs:{floating:"floating",monitorResize:"monitorResize"}})}return n})();function s1(n){let t=n;if(t.offsetParent!==null)return t.scrollWidth;let e=t.cloneNode(!0);e.style.setProperty("position","absolute"),e.style.setProperty("transform","translate(-9999px, -9999px)"),document.documentElement.appendChild(e);let i=e.scrollWidth;return e.remove(),i}var Db="mdc-line-ripple--active",Tl="mdc-line-ripple--deactivating",Eb=(()=>{class n{_elementRef=m(Y);_cleanupTransitionEnd;constructor(){let e=m(K),i=m(rt);e.runOutsideAngular(()=>{this._cleanupTransitionEnd=i.listen(this._elementRef.nativeElement,"transitionend",this._handleTransitionEnd)})}activate(){let e=this._elementRef.nativeElement.classList;e.remove(Tl),e.add(Db)}deactivate(){this._elementRef.nativeElement.classList.add(Tl)}_handleTransitionEnd=e=>{let i=this._elementRef.nativeElement.classList,r=i.contains(Tl);e.propertyName==="opacity"&&r&&i.remove(Db,Tl)};ngOnDestroy(){this._cleanupTransitionEnd()}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["div","matFormFieldLineRipple",""]],hostAttrs:[1,"mdc-line-ripple"]})}return n})(),Sb=(()=>{class n{_elementRef=m(Y);_ngZone=m(K);open=!1;_notch;ngAfterViewInit(){let e=this._elementRef.nativeElement,i=e.querySelector(".mdc-floating-label");i?(e.classList.add("mdc-notched-outline--upgraded"),typeof requestAnimationFrame=="function"&&(i.style.transitionDuration="0s",this._ngZone.runOutsideAngular(()=>{requestAnimationFrame(()=>i.style.transitionDuration="")}))):e.classList.add("mdc-notched-outline--no-label")}_setNotchWidth(e){let i=this._notch.nativeElement;!this.open||!e?i.style.width="":i.style.width=`calc(${e}px * var(--mat-mdc-form-field-floating-label-scale, 0.75) + 9px)`}_setMaxWidth(e){this._notch.nativeElement.style.setProperty("--mat-form-field-notch-max-width",`calc(100% - ${e}px)`)}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["div","matFormFieldNotchedOutline",""]],viewQuery:function(i,r){if(i&1&&Ne(FS,5),i&2){let o;H(o=q())&&(r._notch=o.first)}},hostAttrs:[1,"mdc-notched-outline"],hostVars:2,hostBindings:function(i,r){i&2&&Q("mdc-notched-outline--notched",r.open)},inputs:{open:[0,"matFormFieldNotchedOutlineOpen","open"]},attrs:NS,ngContentSelectors:LS,decls:5,vars:0,consts:[["notch",""],[1,"mat-mdc-notch-piece","mdc-notched-outline__leading"],[1,"mat-mdc-notch-piece","mdc-notched-outline__notch"],[1,"mat-mdc-notch-piece","mdc-notched-outline__trailing"]],template:function(i,r){i&1&&(ne(),Zt(0,"div",1),qe(1,"div",2,0),B(3),Qe(),Zt(4,"div",3))},encapsulation:2,changeDetection:0})}return n})(),l1=(()=>{class n{value=null;stateChanges;id;placeholder;ngControl=null;focused=!1;empty=!1;shouldLabelFloat=!1;required=!1;disabled=!1;errorState=!1;controlType;autofilled;userAriaDescribedBy;disableAutomaticLabeling;describedByIds;static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n})}return n})();var c1=new M("MatFormField"),d1=new M("MAT_FORM_FIELD_DEFAULT_OPTIONS"),Mb="fill",m1="auto",Ab="fixed",u1="translateY(-50%)",Tb=(()=>{class n{_elementRef=m(Y);_changeDetectorRef=m(Ue);_platform=m(Ce);_idGenerator=m(ut);_ngZone=m(K);_defaults=m(d1,{optional:!0});_currentDirection;_textField;_iconPrefixContainer;_textPrefixContainer;_iconSuffixContainer;_textSuffixContainer;_floatingLabel;_notchedOutline;_lineRipple;_iconPrefixContainerSignal=uo("iconPrefixContainer");_textPrefixContainerSignal=uo("textPrefixContainer");_iconSuffixContainerSignal=uo("iconSuffixContainer");_textSuffixContainerSignal=uo("textSuffixContainer");_prefixSuffixContainers=Jt(()=>[this._iconPrefixContainerSignal(),this._textPrefixContainerSignal(),this._iconSuffixContainerSignal(),this._textSuffixContainerSignal()].map(e=>e?.nativeElement).filter(e=>e!==void 0));_formFieldControl;_prefixChildren;_suffixChildren;_errorChildren;_hintChildren;_labelChild=xp(hu);get hideRequiredMarker(){return this._hideRequiredMarker}set hideRequiredMarker(e){this._hideRequiredMarker=kt(e)}_hideRequiredMarker=!1;color="primary";get floatLabel(){return this._floatLabel||this._defaults?.floatLabel||m1}set floatLabel(e){e!==this._floatLabel&&(this._floatLabel=e,this._changeDetectorRef.markForCheck())}_floatLabel;get appearance(){return this._appearanceSignal()}set appearance(e){let i=e||this._defaults?.appearance||Mb;this._appearanceSignal.set(i)}_appearanceSignal=X(Mb);get subscriptSizing(){return this._subscriptSizing||this._defaults?.subscriptSizing||Ab}set subscriptSizing(e){this._subscriptSizing=e||this._defaults?.subscriptSizing||Ab}_subscriptSizing=null;get hintLabel(){return this._hintLabel}set hintLabel(e){this._hintLabel=e,this._processHints()}_hintLabel="";_hasIconPrefix=!1;_hasTextPrefix=!1;_hasIconSuffix=!1;_hasTextSuffix=!1;_labelId=this._idGenerator.getId("mat-mdc-form-field-label-");_hintLabelId=this._idGenerator.getId("mat-mdc-hint-");_describedByIds;get _control(){return this._explicitFormFieldControl||this._formFieldControl}set _control(e){this._explicitFormFieldControl=e}_destroyed=new z;_isFocused=null;_explicitFormFieldControl;_previousControl=null;_previousControlValidatorFn=null;_stateChanges;_valueChanges;_describedByChanges;_outlineLabelOffsetResizeObserver=null;_animationsDisabled=He();constructor(){let e=this._defaults,i=m(wt);e&&(e.appearance&&(this.appearance=e.appearance),this._hideRequiredMarker=!!e?.hideRequiredMarker,e.color&&(this.color=e.color)),Qi(()=>this._currentDirection=i.valueSignal()),this._syncOutlineLabelOffset()}ngAfterViewInit(){this._updateFocusState(),this._animationsDisabled||this._ngZone.runOutsideAngular(()=>{setTimeout(()=>{this._elementRef.nativeElement.classList.add("mat-form-field-animations-enabled")},300)}),this._changeDetectorRef.detectChanges()}ngAfterContentInit(){this._assertFormFieldControl(),this._initializeSubscript(),this._initializePrefixAndSuffix()}ngAfterContentChecked(){this._assertFormFieldControl(),this._control!==this._previousControl&&(this._initializeControl(this._previousControl),this._control.ngControl&&this._control.ngControl.control&&(this._previousControlValidatorFn=this._control.ngControl.control.validator),this._previousControl=this._control),this._control.ngControl&&this._control.ngControl.control&&this._control.ngControl.control.validator!==this._previousControlValidatorFn&&this._changeDetectorRef.markForCheck()}ngOnDestroy(){this._outlineLabelOffsetResizeObserver?.disconnect(),this._stateChanges?.unsubscribe(),this._valueChanges?.unsubscribe(),this._describedByChanges?.unsubscribe(),this._destroyed.next(),this._destroyed.complete()}getLabelId=Jt(()=>this._hasFloatingLabel()?this._labelId:null);getConnectedOverlayOrigin(){return this._textField||this._elementRef}_animateAndLockLabel(){this._hasFloatingLabel()&&(this.floatLabel="always")}_initializeControl(e){let i=this._control,r="mat-mdc-form-field-type-";e&&this._elementRef.nativeElement.classList.remove(r+e.controlType),i.controlType&&this._elementRef.nativeElement.classList.add(r+i.controlType),this._stateChanges?.unsubscribe(),this._stateChanges=i.stateChanges.subscribe(()=>{this._updateFocusState(),this._changeDetectorRef.markForCheck()}),this._describedByChanges?.unsubscribe(),this._describedByChanges=i.stateChanges.pipe(ct([void 0,void 0]),xe(()=>[i.errorState,i.userAriaDescribedBy]),oc(),Be(([[o,a],[s,l]])=>o!==s||a!==l)).subscribe(()=>this._syncDescribedByIds()),this._valueChanges?.unsubscribe(),i.ngControl&&i.ngControl.valueChanges&&(this._valueChanges=i.ngControl.valueChanges.pipe(De(this._destroyed)).subscribe(()=>this._changeDetectorRef.markForCheck()))}_checkPrefixAndSuffixTypes(){this._hasIconPrefix=!!this._prefixChildren.find(e=>!e._isText),this._hasTextPrefix=!!this._prefixChildren.find(e=>e._isText),this._hasIconSuffix=!!this._suffixChildren.find(e=>!e._isText),this._hasTextSuffix=!!this._suffixChildren.find(e=>e._isText)}_initializePrefixAndSuffix(){this._checkPrefixAndSuffixTypes(),_t(this._prefixChildren.changes,this._suffixChildren.changes).subscribe(()=>{this._checkPrefixAndSuffixTypes(),this._changeDetectorRef.markForCheck()})}_initializeSubscript(){this._hintChildren.changes.subscribe(()=>{this._processHints(),this._changeDetectorRef.markForCheck()}),this._errorChildren.changes.subscribe(()=>{this._syncDescribedByIds(),this._changeDetectorRef.markForCheck()}),this._validateHints(),this._syncDescribedByIds()}_assertFormFieldControl(){this._control}_updateFocusState(){let e=this._control.focused;e&&!this._isFocused?(this._isFocused=!0,this._lineRipple?.activate()):!e&&(this._isFocused||this._isFocused===null)&&(this._isFocused=!1,this._lineRipple?.deactivate()),this._elementRef.nativeElement.classList.toggle("mat-focused",e),this._textField?.nativeElement.classList.toggle("mdc-text-field--focused",e)}_syncOutlineLabelOffset(){Dp({earlyRead:()=>{if(this._appearanceSignal()!=="outline")return this._outlineLabelOffsetResizeObserver?.disconnect(),null;if(globalThis.ResizeObserver){this._outlineLabelOffsetResizeObserver||=new globalThis.ResizeObserver(()=>{this._writeOutlinedLabelStyles(this._getOutlinedLabelOffset())});for(let e of this._prefixSuffixContainers())this._outlineLabelOffsetResizeObserver.observe(e,{box:"border-box"})}return this._getOutlinedLabelOffset()},write:e=>this._writeOutlinedLabelStyles(e())})}_shouldAlwaysFloat(){return this.floatLabel==="always"}_hasOutline(){return this.appearance==="outline"}_forceDisplayInfixLabel(){return!this._platform.isBrowser&&this._prefixChildren.length&&!this._shouldLabelFloat()}_hasFloatingLabel=Jt(()=>!!this._labelChild());_shouldLabelFloat(){return this._hasFloatingLabel()?this._control.shouldLabelFloat||this._shouldAlwaysFloat():!1}_shouldForward(e){let i=this._control?this._control.ngControl:null;return i&&i[e]}_getSubscriptMessageType(){return this._errorChildren&&this._errorChildren.length>0&&this._control.errorState?"error":"hint"}_handleLabelResized(){this._refreshOutlineNotchWidth()}_refreshOutlineNotchWidth(){!this._hasOutline()||!this._floatingLabel||!this._shouldLabelFloat()?this._notchedOutline?._setNotchWidth(0):this._notchedOutline?._setNotchWidth(this._floatingLabel.getWidth())}_processHints(){this._validateHints(),this._syncDescribedByIds()}_validateHints(){this._hintChildren}_syncDescribedByIds(){if(this._control){let e=[];if(this._control.userAriaDescribedBy&&typeof this._control.userAriaDescribedBy=="string"&&e.push(...this._control.userAriaDescribedBy.split(" ")),this._getSubscriptMessageType()==="hint"){let o=this._hintChildren?this._hintChildren.find(s=>s.align==="start"):null,a=this._hintChildren?this._hintChildren.find(s=>s.align==="end"):null;o?e.push(o.id):this._hintLabel&&e.push(this._hintLabelId),a&&e.push(a.id)}else this._errorChildren&&e.push(...this._errorChildren.map(o=>o.id));let i=this._control.describedByIds,r;if(i){let o=this._describedByIds||e;r=e.concat(i.filter(a=>a&&!o.includes(a)))}else r=e;this._control.setDescribedByIds(r),this._describedByIds=e}}_getOutlinedLabelOffset(){if(!this._hasOutline()||!this._floatingLabel)return null;if(!this._iconPrefixContainer&&!this._textPrefixContainer)return["",null];if(!this._isAttachedToDom())return null;let e=this._iconPrefixContainer?.nativeElement,i=this._textPrefixContainer?.nativeElement,r=this._iconSuffixContainer?.nativeElement,o=this._textSuffixContainer?.nativeElement,a=e?.getBoundingClientRect().width??0,s=i?.getBoundingClientRect().width??0,l=r?.getBoundingClientRect().width??0,d=o?.getBoundingClientRect().width??0,c=this._currentDirection==="rtl"?"-1":"1",u=`${a+s}px`,y=`calc(${c} * (${u} + var(--mat-mdc-form-field-label-offset-x, 0px)))`,_=`var(--mat-mdc-form-field-label-transform, ${u1} translateX(${y}))`,b=a+s+l+d;return[_,b]}_writeOutlinedLabelStyles(e){if(e!==null){let[i,r]=e;this._floatingLabel&&(this._floatingLabel.element.style.transform=i),r!==null&&this._notchedOutline?._setMaxWidth(r)}}_isAttachedToDom(){let e=this._elementRef.nativeElement;if(e.getRootNode){let i=e.getRootNode();return i&&i!==e}return document.documentElement.contains(e)}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-form-field"]],contentQueries:function(i,r,o){if(i&1&&(fp(o,r._labelChild,hu,5),Xe(o,l1,5)(o,o1,5)(o,a1,5)(o,r1,5)(o,fu,5)),i&2){cc();let a;H(a=q())&&(r._formFieldControl=a.first),H(a=q())&&(r._prefixChildren=a),H(a=q())&&(r._suffixChildren=a),H(a=q())&&(r._errorChildren=a),H(a=q())&&(r._hintChildren=a)}},viewQuery:function(i,r){if(i&1&&(gp(r._iconPrefixContainerSignal,yb,5)(r._textPrefixContainerSignal,xb,5)(r._iconSuffixContainerSignal,wb,5)(r._textSuffixContainerSignal,Cb,5),Ne(BS,5)(yb,5)(xb,5)(wb,5)(Cb,5)(kb,5)(Sb,5)(Eb,5)),i&2){cc(4);let o;H(o=q())&&(r._textField=o.first),H(o=q())&&(r._iconPrefixContainer=o.first),H(o=q())&&(r._textPrefixContainer=o.first),H(o=q())&&(r._iconSuffixContainer=o.first),H(o=q())&&(r._textSuffixContainer=o.first),H(o=q())&&(r._floatingLabel=o.first),H(o=q())&&(r._notchedOutline=o.first),H(o=q())&&(r._lineRipple=o.first)}},hostAttrs:[1,"mat-mdc-form-field"],hostVars:38,hostBindings:function(i,r){i&2&&Q("mat-mdc-form-field-label-always-float",r._shouldAlwaysFloat())("mat-mdc-form-field-has-icon-prefix",r._hasIconPrefix)("mat-mdc-form-field-has-icon-suffix",r._hasIconSuffix)("mat-form-field-invalid",r._control.errorState)("mat-form-field-disabled",r._control.disabled)("mat-form-field-autofilled",r._control.autofilled)("mat-form-field-appearance-fill",r.appearance=="fill")("mat-form-field-appearance-outline",r.appearance=="outline")("mat-form-field-hide-placeholder",r._hasFloatingLabel()&&!r._shouldLabelFloat())("mat-primary",r.color!=="accent"&&r.color!=="warn")("mat-accent",r.color==="accent")("mat-warn",r.color==="warn")("ng-untouched",r._shouldForward("untouched"))("ng-touched",r._shouldForward("touched"))("ng-pristine",r._shouldForward("pristine"))("ng-dirty",r._shouldForward("dirty"))("ng-valid",r._shouldForward("valid"))("ng-invalid",r._shouldForward("invalid"))("ng-pending",r._shouldForward("pending"))},inputs:{hideRequiredMarker:"hideRequiredMarker",color:"color",floatLabel:"floatLabel",appearance:"appearance",subscriptSizing:"subscriptSizing",hintLabel:"hintLabel"},exportAs:["matFormField"],features:[ze([{provide:c1,useExisting:n},{provide:Ib,useExisting:n}])],ngContentSelectors:jS,decls:18,vars:21,consts:[["labelTemplate",""],["textField",""],["iconPrefixContainer",""],["textPrefixContainer",""],["textSuffixContainer",""],["iconSuffixContainer",""],[1,"mat-mdc-text-field-wrapper","mdc-text-field",3,"click"],[1,"mat-mdc-form-field-focus-overlay"],[1,"mat-mdc-form-field-flex"],["matFormFieldNotchedOutline","",3,"matFormFieldNotchedOutlineOpen"],[1,"mat-mdc-form-field-icon-prefix"],[1,"mat-mdc-form-field-text-prefix"],[1,"mat-mdc-form-field-infix"],[3,"ngTemplateOutlet"],[1,"mat-mdc-form-field-text-suffix"],[1,"mat-mdc-form-field-icon-suffix"],["matFormFieldLineRipple",""],["aria-atomic","true","aria-live","polite",1,"mat-mdc-form-field-subscript-wrapper","mat-mdc-form-field-bottom-align"],[1,"mat-mdc-form-field-error-wrapper"],[1,"mat-mdc-form-field-hint-wrapper"],["matFormFieldFloatingLabel","",3,"floating","monitorResize","id"],["aria-hidden","true",1,"mat-mdc-form-field-required-marker","mdc-floating-label--required"],[3,"id"],[1,"mat-mdc-form-field-hint-spacer"]],template:function(i,r){if(i&1&&(ne($S),Vt(0,HS,1,1,"ng-template",null,0,mo),g(2,"div",6,1),pe("click",function(a){return r._control.onContainerClick(a)}),k(4,qS,1,0,"div",7),g(5,"div",8),k(6,WS,2,2,"div",9),k(7,KS,3,0,"div",10),k(8,YS,3,0,"div",11),g(9,"div",12),k(10,XS,1,1,null,13),B(11),f(),k(12,ZS,3,0,"div",14),k(13,JS,3,0,"div",15),f(),k(14,e1,1,0,"div",16),f(),g(15,"div",17),k(16,t1,2,0,"div",18)(17,i1,5,1,"div",19),f()),i&2){let o;p(2),Q("mdc-text-field--filled",!r._hasOutline())("mdc-text-field--outlined",r._hasOutline())("mdc-text-field--no-label",!r._hasFloatingLabel())("mdc-text-field--disabled",r._control.disabled)("mdc-text-field--invalid",r._control.errorState),p(2),D(!r._hasOutline()&&!r._control.disabled?4:-1),p(2),D(r._hasOutline()?6:-1),p(),D(r._hasIconPrefix?7:-1),p(),D(r._hasTextPrefix?8:-1),p(2),D(!r._hasOutline()||r._forceDisplayInfixLabel()?10:-1),p(2),D(r._hasTextSuffix?12:-1),p(),D(r._hasIconSuffix?13:-1),p(),D(r._hasOutline()?-1:14),p(),Q("mat-mdc-form-field-subscript-dynamic-size",r.subscriptSizing==="dynamic");let a=r._getSubscriptMessageType();p(),D((o=a)==="error"?16:o==="hint"?17:-1)}},dependencies:[kb,Sb,go,Eb,fu],styles:[`.mdc-text-field {
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
.mdc-text-field--disabled:not(.mdc-text-field--no-label) .mdc-text-field__input.mat-mdc-input-disabled-interactive::placeholder {
  opacity: 0;
}
.mdc-text-field--disabled:not(.mdc-text-field--no-label) .mdc-text-field__input.mat-mdc-input-disabled-interactive::-moz-placeholder {
  opacity: 0;
}
.mdc-text-field--disabled:not(.mdc-text-field--no-label) .mdc-text-field__input.mat-mdc-input-disabled-interactive::-webkit-input-placeholder {
  opacity: 0;
}
.mdc-text-field--disabled:not(.mdc-text-field--no-label) .mdc-text-field__input.mat-mdc-input-disabled-interactive:-ms-input-placeholder {
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
.mdc-text-field--filled:not(.mdc-text-field--disabled) .mdc-text-field__input {
  color: var(--mat-form-field-filled-input-text-color, var(--mat-sys-on-surface));
  caret-color: var(--mat-form-field-filled-caret-color, var(--mat-sys-primary));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled) .mdc-text-field__input::placeholder {
  color: var(--mat-form-field-filled-input-text-placeholder-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled) .mdc-text-field__input::-moz-placeholder {
  color: var(--mat-form-field-filled-input-text-placeholder-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled) .mdc-text-field__input::-webkit-input-placeholder {
  color: var(--mat-form-field-filled-input-text-placeholder-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled) .mdc-text-field__input:-ms-input-placeholder {
  color: var(--mat-form-field-filled-input-text-placeholder-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled) .mdc-text-field__input {
  color: var(--mat-form-field-outlined-input-text-color, var(--mat-sys-on-surface));
  caret-color: var(--mat-form-field-outlined-caret-color, var(--mat-sys-primary));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled) .mdc-text-field__input::placeholder {
  color: var(--mat-form-field-outlined-input-text-placeholder-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled) .mdc-text-field__input::-moz-placeholder {
  color: var(--mat-form-field-outlined-input-text-placeholder-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled) .mdc-text-field__input::-webkit-input-placeholder {
  color: var(--mat-form-field-outlined-input-text-placeholder-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled) .mdc-text-field__input:-ms-input-placeholder {
  color: var(--mat-form-field-outlined-input-text-placeholder-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--filled.mdc-text-field--invalid:not(.mdc-text-field--disabled) .mdc-text-field__input {
  caret-color: var(--mat-form-field-filled-error-caret-color, var(--mat-sys-error));
}
.mdc-text-field--outlined.mdc-text-field--invalid:not(.mdc-text-field--disabled) .mdc-text-field__input {
  caret-color: var(--mat-form-field-outlined-error-caret-color, var(--mat-sys-error));
}
.mdc-text-field--filled.mdc-text-field--disabled .mdc-text-field__input {
  color: var(--mat-form-field-filled-disabled-input-text-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}
.mdc-text-field--outlined.mdc-text-field--disabled .mdc-text-field__input {
  color: var(--mat-form-field-outlined-disabled-input-text-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
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
  border-top-left-radius: var(--mat-form-field-filled-container-shape, var(--mat-sys-corner-extra-small));
  border-top-right-radius: var(--mat-form-field-filled-container-shape, var(--mat-sys-corner-extra-small));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled) {
  background-color: var(--mat-form-field-filled-container-color, var(--mat-sys-surface-variant));
}
.mdc-text-field--filled.mdc-text-field--disabled {
  background-color: var(--mat-form-field-filled-disabled-container-color, color-mix(in srgb, var(--mat-sys-on-surface) 4%, transparent));
}

.mdc-text-field--outlined {
  height: 56px;
  overflow: visible;
  padding-right: max(16px, var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small)));
  padding-left: max(16px, var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small)) + 4px);
}
[dir=rtl] .mdc-text-field--outlined {
  padding-right: max(16px, var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small)) + 4px);
  padding-left: max(16px, var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small)));
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
.mdc-text-field--filled:not(.mdc-text-field--disabled) .mdc-floating-label {
  color: var(--mat-form-field-filled-label-text-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled).mdc-text-field--focused .mdc-floating-label {
  color: var(--mat-form-field-filled-focus-label-text-color, var(--mat-sys-primary));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled):not(.mdc-text-field--focused):hover .mdc-floating-label {
  color: var(--mat-form-field-filled-hover-label-text-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--filled.mdc-text-field--disabled .mdc-floating-label {
  color: var(--mat-form-field-filled-disabled-label-text-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled).mdc-text-field--invalid .mdc-floating-label {
  color: var(--mat-form-field-filled-error-label-text-color, var(--mat-sys-error));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled).mdc-text-field--invalid.mdc-text-field--focused .mdc-floating-label {
  color: var(--mat-form-field-filled-error-focus-label-text-color, var(--mat-sys-error));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled).mdc-text-field--invalid:not(.mdc-text-field--disabled):hover .mdc-floating-label {
  color: var(--mat-form-field-filled-error-hover-label-text-color, var(--mat-sys-on-error-container));
}
.mdc-text-field--filled .mdc-floating-label {
  font-family: var(--mat-form-field-filled-label-text-font, var(--mat-sys-body-large-font));
  font-size: var(--mat-form-field-filled-label-text-size, var(--mat-sys-body-large-size));
  font-weight: var(--mat-form-field-filled-label-text-weight, var(--mat-sys-body-large-weight));
  letter-spacing: var(--mat-form-field-filled-label-text-tracking, var(--mat-sys-body-large-tracking));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled) .mdc-floating-label {
  color: var(--mat-form-field-outlined-label-text-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled).mdc-text-field--focused .mdc-floating-label {
  color: var(--mat-form-field-outlined-focus-label-text-color, var(--mat-sys-primary));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled):not(.mdc-text-field--focused):hover .mdc-floating-label {
  color: var(--mat-form-field-outlined-hover-label-text-color, var(--mat-sys-on-surface));
}
.mdc-text-field--outlined.mdc-text-field--disabled .mdc-floating-label {
  color: var(--mat-form-field-outlined-disabled-label-text-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled).mdc-text-field--invalid .mdc-floating-label {
  color: var(--mat-form-field-outlined-error-label-text-color, var(--mat-sys-error));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled).mdc-text-field--invalid.mdc-text-field--focused .mdc-floating-label {
  color: var(--mat-form-field-outlined-error-focus-label-text-color, var(--mat-sys-error));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled).mdc-text-field--invalid:not(.mdc-text-field--disabled):hover .mdc-floating-label {
  color: var(--mat-form-field-outlined-error-hover-label-text-color, var(--mat-sys-on-error-container));
}
.mdc-text-field--outlined .mdc-floating-label {
  font-family: var(--mat-form-field-outlined-label-text-font, var(--mat-sys-body-large-font));
  font-size: var(--mat-form-field-outlined-label-text-size, var(--mat-sys-body-large-size));
  font-weight: var(--mat-form-field-outlined-label-text-weight, var(--mat-sys-body-large-weight));
  letter-spacing: var(--mat-form-field-outlined-label-text-tracking, var(--mat-sys-body-large-tracking));
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

.mdc-floating-label--required:not(.mdc-floating-label--hide-required-marker)::after {
  margin-left: 1px;
  margin-right: 0;
  content: "*";
}
[dir=rtl] .mdc-floating-label--required:not(.mdc-floating-label--hide-required-marker)::after {
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
.mdc-text-field--outlined:not(.mdc-text-field--disabled) .mat-mdc-notch-piece {
  border-color: var(--mat-form-field-outlined-outline-color, var(--mat-sys-outline));
  border-width: var(--mat-form-field-outlined-outline-width, 1px);
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled):not(.mdc-text-field--focused):hover .mat-mdc-notch-piece {
  border-color: var(--mat-form-field-outlined-hover-outline-color, var(--mat-sys-on-surface));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled).mdc-text-field--focused .mat-mdc-notch-piece {
  border-color: var(--mat-form-field-outlined-focus-outline-color, var(--mat-sys-primary));
}
.mdc-text-field--outlined.mdc-text-field--disabled .mat-mdc-notch-piece {
  border-color: var(--mat-form-field-outlined-disabled-outline-color, color-mix(in srgb, var(--mat-sys-on-surface) 12%, transparent));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled).mdc-text-field--invalid .mat-mdc-notch-piece {
  border-color: var(--mat-form-field-outlined-error-outline-color, var(--mat-sys-error));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled).mdc-text-field--invalid:not(.mdc-text-field--focused):hover .mdc-notched-outline .mat-mdc-notch-piece {
  border-color: var(--mat-form-field-outlined-error-hover-outline-color, var(--mat-sys-on-error-container));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled).mdc-text-field--invalid.mdc-text-field--focused .mat-mdc-notch-piece {
  border-color: var(--mat-form-field-outlined-error-focus-outline-color, var(--mat-sys-error));
}
.mdc-text-field--outlined:not(.mdc-text-field--disabled).mdc-text-field--focused .mdc-notched-outline .mat-mdc-notch-piece {
  border-width: var(--mat-form-field-outlined-focus-outline-width, 2px);
}

.mdc-notched-outline__leading {
  border-left: 1px solid;
  border-right: none;
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  border-top-left-radius: var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small));
  border-bottom-left-radius: var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small));
}
.mdc-text-field--outlined .mdc-notched-outline .mdc-notched-outline__leading {
  width: max(12px, var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small)));
}
[dir=rtl] .mdc-notched-outline__leading {
  border-left: none;
  border-right: 1px solid;
  border-bottom-left-radius: 0;
  border-top-left-radius: 0;
  border-top-right-radius: var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small));
  border-bottom-right-radius: var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small));
}

.mdc-notched-outline__trailing {
  flex-grow: 1;
  border-left: none;
  border-right: 1px solid;
  border-top-left-radius: 0;
  border-bottom-left-radius: 0;
  border-top-right-radius: var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small));
  border-bottom-right-radius: var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small));
}
[dir=rtl] .mdc-notched-outline__trailing {
  border-left: 1px solid;
  border-right: none;
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  border-top-left-radius: var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small));
  border-bottom-left-radius: var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small));
}

.mdc-notched-outline__notch {
  flex: 0 0 auto;
  width: auto;
}
.mdc-text-field--outlined .mdc-notched-outline .mdc-notched-outline__notch {
  max-width: min(var(--mat-form-field-notch-max-width, 100%), calc(100% - max(12px, var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small))) * 2));
}
.mdc-text-field--outlined .mdc-notched-outline--notched .mdc-notched-outline__notch {
  max-width: min(100%, calc(100% - max(12px, var(--mat-form-field-outlined-container-shape, var(--mat-sys-corner-extra-small))) * 2));
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
  border-bottom-width: var(--mat-form-field-filled-active-indicator-height, 1px);
}
.mdc-text-field--filled:not(.mdc-text-field--disabled) .mdc-line-ripple::before {
  border-bottom-color: var(--mat-form-field-filled-active-indicator-color, var(--mat-sys-on-surface-variant));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled):not(.mdc-text-field--focused):hover .mdc-line-ripple::before {
  border-bottom-color: var(--mat-form-field-filled-hover-active-indicator-color, var(--mat-sys-on-surface));
}
.mdc-text-field--filled.mdc-text-field--disabled .mdc-line-ripple::before {
  border-bottom-color: var(--mat-form-field-filled-disabled-active-indicator-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled).mdc-text-field--invalid .mdc-line-ripple::before {
  border-bottom-color: var(--mat-form-field-filled-error-active-indicator-color, var(--mat-sys-error));
}
.mdc-text-field--filled:not(.mdc-text-field--disabled).mdc-text-field--invalid:not(.mdc-text-field--focused):hover .mdc-line-ripple::before {
  border-bottom-color: var(--mat-form-field-filled-error-hover-active-indicator-color, var(--mat-sys-on-error-container));
}
.mdc-line-ripple::after {
  transform: scaleX(0);
  opacity: 0;
  z-index: 2;
}
.mdc-text-field--filled .mdc-line-ripple::after {
  border-bottom-width: var(--mat-form-field-filled-focus-active-indicator-height, 2px);
}
.mdc-text-field--filled:not(.mdc-text-field--disabled) .mdc-line-ripple::after {
  border-bottom-color: var(--mat-form-field-filled-focus-active-indicator-color, var(--mat-sys-primary));
}
.mdc-text-field--filled.mdc-text-field--invalid:not(.mdc-text-field--disabled) .mdc-line-ripple::after {
  border-bottom-color: var(--mat-form-field-filled-error-focus-active-indicator-color, var(--mat-sys-error));
}

.mdc-line-ripple--active::after {
  transform: scaleX(1);
  opacity: 1;
}

.mdc-line-ripple--deactivating::after {
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

.mdc-text-field--no-label:not(.mdc-text-field--textarea) .mat-mdc-form-field-input-control.mdc-text-field__input,
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
  --mat-mdc-form-field-label-offset-x: -16px;
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
  color: var(--mat-form-field-disabled-input-text-placeholder-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}
.mat-form-field-disabled .mdc-text-field__input::-moz-placeholder {
  color: var(--mat-form-field-disabled-input-text-placeholder-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}
.mat-form-field-disabled .mdc-text-field__input::-webkit-input-placeholder {
  color: var(--mat-form-field-disabled-input-text-placeholder-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}
.mat-form-field-disabled .mdc-text-field__input:-ms-input-placeholder {
  color: var(--mat-form-field-disabled-input-text-placeholder-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
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
  min-height: var(--mat-form-field-container-height, 56px);
  padding-top: var(--mat-form-field-filled-with-label-container-padding-top, 24px);
  padding-bottom: var(--mat-form-field-filled-with-label-container-padding-bottom, 8px);
}
.mdc-text-field--outlined .mat-mdc-form-field-infix, .mdc-text-field--no-label .mat-mdc-form-field-infix {
  padding-top: var(--mat-form-field-container-vertical-padding, 16px);
  padding-bottom: var(--mat-form-field-container-vertical-padding, 16px);
}

.mat-mdc-text-field-wrapper .mat-mdc-form-field-flex .mat-mdc-floating-label {
  top: calc(var(--mat-form-field-container-height, 56px) / 2);
}

.mdc-text-field--filled .mat-mdc-floating-label {
  display: var(--mat-form-field-filled-label-display, block);
}

.mat-mdc-text-field-wrapper.mdc-text-field--outlined .mdc-notched-outline--upgraded .mdc-floating-label--float-above {
  --mat-mdc-form-field-label-transform: translateY(calc(calc(6.75px + var(--mat-form-field-container-height, 56px) / 2) * -1))
    scale(var(--mat-mdc-form-field-floating-label-scale, 0.75));
  transform: var(--mat-mdc-form-field-label-transform);
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
  color: var(--mat-form-field-error-text-color, var(--mat-sys-error));
}

.mat-mdc-form-field-subscript-wrapper,
.mat-mdc-form-field-bottom-align::before {
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  font-family: var(--mat-form-field-subscript-text-font, var(--mat-sys-body-small-font));
  line-height: var(--mat-form-field-subscript-text-line-height, var(--mat-sys-body-small-line-height));
  font-size: var(--mat-form-field-subscript-text-size, var(--mat-sys-body-small-size));
  letter-spacing: var(--mat-form-field-subscript-text-tracking, var(--mat-sys-body-small-tracking));
  font-weight: var(--mat-form-field-subscript-text-weight, var(--mat-sys-body-small-weight));
}

.mat-mdc-form-field-focus-overlay {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  opacity: 0;
  pointer-events: none;
  background-color: var(--mat-form-field-state-layer-color, var(--mat-sys-on-surface));
}
.mat-mdc-text-field-wrapper:hover .mat-mdc-form-field-focus-overlay {
  opacity: var(--mat-form-field-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity));
}
.mat-mdc-form-field.mat-focused .mat-mdc-form-field-focus-overlay {
  opacity: var(--mat-form-field-focus-state-layer-opacity, 0);
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
  color: var(--mat-form-field-select-option-text-color, var(--mat-sys-neutral10));
}
select.mat-mdc-form-field-input-control:not(.mat-mdc-native-select-inline) option:disabled {
  color: var(--mat-form-field-select-disabled-option-text-color, color-mix(in srgb, var(--mat-sys-neutral10) 38%, transparent));
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
  color: var(--mat-form-field-enabled-select-arrow-color, var(--mat-sys-on-surface-variant));
}
[dir=rtl] .mat-mdc-form-field-type-mat-native-select .mat-mdc-form-field-infix::after {
  right: auto;
  left: 0;
}
.mat-mdc-form-field-type-mat-native-select.mat-focused .mat-mdc-form-field-infix::after {
  color: var(--mat-form-field-focus-select-arrow-color, var(--mat-sys-primary));
}
.mat-mdc-form-field-type-mat-native-select.mat-form-field-disabled .mat-mdc-form-field-infix::after {
  color: var(--mat-form-field-disabled-select-arrow-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
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
  --mat-mdc-form-field-floating-label-scale: 0.75;
  display: inline-flex;
  flex-direction: column;
  min-width: 0;
  text-align: left;
  -moz-osx-font-smoothing: grayscale;
  -webkit-font-smoothing: antialiased;
  font-family: var(--mat-form-field-container-text-font, var(--mat-sys-body-large-font));
  line-height: var(--mat-form-field-container-text-line-height, var(--mat-sys-body-large-line-height));
  font-size: var(--mat-form-field-container-text-size, var(--mat-sys-body-large-size));
  letter-spacing: var(--mat-form-field-container-text-tracking, var(--mat-sys-body-large-tracking));
  font-weight: var(--mat-form-field-container-text-weight, var(--mat-sys-body-large-weight));
}
.mat-mdc-form-field .mdc-text-field--outlined .mdc-floating-label--float-above {
  font-size: calc(var(--mat-form-field-outlined-label-text-populated-size) * var(--mat-mdc-form-field-floating-label-scale));
}
.mat-mdc-form-field .mdc-text-field--outlined .mdc-notched-outline--upgraded .mdc-floating-label--float-above {
  font-size: var(--mat-form-field-outlined-label-text-populated-size);
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
  color: var(--mat-form-field-leading-icon-color, var(--mat-sys-on-surface-variant));
}
.mat-form-field-disabled .mat-mdc-form-field-icon-prefix {
  color: var(--mat-form-field-disabled-leading-icon-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}

.mat-mdc-form-field-icon-suffix {
  color: var(--mat-form-field-trailing-icon-color, var(--mat-sys-on-surface-variant));
}
.mat-form-field-disabled .mat-mdc-form-field-icon-suffix {
  color: var(--mat-form-field-disabled-trailing-icon-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}
.mat-form-field-invalid .mat-mdc-form-field-icon-suffix {
  color: var(--mat-form-field-error-trailing-icon-color, var(--mat-sys-error));
}
.mat-form-field-invalid:not(.mat-focused):not(.mat-form-field-disabled) .mat-mdc-text-field-wrapper:hover .mat-mdc-form-field-icon-suffix {
  color: var(--mat-form-field-error-hover-trailing-icon-color, var(--mat-sys-on-error-container));
}
.mat-form-field-invalid.mat-focused .mat-mdc-text-field-wrapper .mat-mdc-form-field-icon-suffix {
  color: var(--mat-form-field-error-focus-trailing-icon-color, var(--mat-sys-error));
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
.mat-mdc-form-field.mat-form-field-animations-enabled .mdc-text-field--filled:not(.mdc-ripple-upgraded):focus .mdc-text-field__ripple::before {
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
`],encapsulation:2,changeDetection:0})}return n})();var ba=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[Ds,Tb,ie]})}return n})();var p1=new M("",{factory:()=>Ob}),Ob="always";var h1=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({})}return n})();var Rb=(()=>{class n{static withConfig(e){return{ngModule:n,providers:[{provide:p1,useValue:e.callSetDisabledState??Ob}]}}static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[h1]})}return n})();var Pb=(()=>{class n{_animationsDisabled=He();state="unchecked";disabled=!1;appearance="full";constructor(){}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-pseudo-checkbox"]],hostAttrs:[1,"mat-pseudo-checkbox"],hostVars:12,hostBindings:function(i,r){i&2&&Q("mat-pseudo-checkbox-indeterminate",r.state==="indeterminate")("mat-pseudo-checkbox-checked",r.state==="checked")("mat-pseudo-checkbox-disabled",r.disabled)("mat-pseudo-checkbox-minimal",r.appearance==="minimal")("mat-pseudo-checkbox-full",r.appearance==="full")("_mat-animation-noopable",r._animationsDisabled)},inputs:{state:"state",disabled:"disabled",appearance:"appearance"},decls:0,vars:0,template:function(i,r){},styles:[`.mat-pseudo-checkbox {
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
  color: var(--mat-pseudo-checkbox-minimal-selected-checkmark-color, var(--mat-sys-primary));
}
.mat-pseudo-checkbox-minimal.mat-pseudo-checkbox-checked.mat-pseudo-checkbox-disabled::after, .mat-pseudo-checkbox-minimal.mat-pseudo-checkbox-indeterminate.mat-pseudo-checkbox-disabled::after {
  color: var(--mat-pseudo-checkbox-minimal-disabled-selected-checkmark-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}

.mat-pseudo-checkbox-full {
  border-color: var(--mat-pseudo-checkbox-full-unselected-icon-color, var(--mat-sys-on-surface-variant));
  border-width: 2px;
  border-style: solid;
}
.mat-pseudo-checkbox-full.mat-pseudo-checkbox-disabled {
  border-color: var(--mat-pseudo-checkbox-full-disabled-unselected-icon-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}
.mat-pseudo-checkbox-full.mat-pseudo-checkbox-checked, .mat-pseudo-checkbox-full.mat-pseudo-checkbox-indeterminate {
  background-color: var(--mat-pseudo-checkbox-full-selected-icon-color, var(--mat-sys-primary));
  border-color: transparent;
}
.mat-pseudo-checkbox-full.mat-pseudo-checkbox-checked::after, .mat-pseudo-checkbox-full.mat-pseudo-checkbox-indeterminate::after {
  color: var(--mat-pseudo-checkbox-full-selected-checkmark-color, var(--mat-sys-on-primary));
}
.mat-pseudo-checkbox-full.mat-pseudo-checkbox-checked.mat-pseudo-checkbox-disabled, .mat-pseudo-checkbox-full.mat-pseudo-checkbox-indeterminate.mat-pseudo-checkbox-disabled {
  background-color: var(--mat-pseudo-checkbox-full-disabled-selected-icon-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
}
.mat-pseudo-checkbox-full.mat-pseudo-checkbox-checked.mat-pseudo-checkbox-disabled::after, .mat-pseudo-checkbox-full.mat-pseudo-checkbox-indeterminate.mat-pseudo-checkbox-disabled::after {
  color: var(--mat-pseudo-checkbox-full-disabled-selected-checkmark-color, var(--mat-sys-surface));
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
`],encapsulation:2,changeDetection:0})}return n})();var f1=["text"],g1=[[["mat-icon"]],"*"],_1=["mat-icon","*"];function b1(n,t){if(n&1&&P(0,"mat-pseudo-checkbox",1),n&2){let e=C();R("disabled",e.disabled)("state",e.selected?"checked":"unchecked")}}function v1(n,t){if(n&1&&P(0,"mat-pseudo-checkbox",3),n&2){let e=C();R("disabled",e.disabled)}}function y1(n,t){if(n&1&&(g(0,"span",4),w(1),f()),n&2){let e=C();p(),J("(",e.group.label,")")}}var x1=new M("MAT_OPTION_PARENT_COMPONENT"),w1=new M("MatOptgroup");var gu=class{source;isUserInput;constructor(t,e=!1){this.source=t,this.isUserInput=e}},Fb=(()=>{class n{_element=m(Y);_changeDetectorRef=m(Ue);_parent=m(x1,{optional:!0});group=m(w1,{optional:!0});_signalDisableRipple=!1;_selected=!1;_active=!1;_mostRecentViewValue="";get multiple(){return this._parent&&this._parent.multiple}get selected(){return this._selected}value;id=m(ut).getId("mat-option-");get disabled(){return this.group&&this.group.disabled||this._disabled()}set disabled(e){this._disabled.set(e)}_disabled=X(!1);get disableRipple(){return this._signalDisableRipple?this._parent.disableRipple():!!this._parent?.disableRipple}get hideSingleSelectionIndicator(){return!!(this._parent&&this._parent.hideSingleSelectionIndicator)}onSelectionChange=new Ee;_text;_stateChanges=new z;constructor(){let e=m(Je);e.load(on),e.load(dr),this._signalDisableRipple=!!this._parent&&nr(this._parent.disableRipple)}get active(){return this._active}get viewValue(){return(this._text?.nativeElement.textContent||"").trim()}select(e=!0){this._selected||(this._selected=!0,this._changeDetectorRef.markForCheck(),e&&this._emitSelectionChangeEvent())}deselect(e=!0){this._selected&&(this._selected=!1,this._changeDetectorRef.markForCheck(),e&&this._emitSelectionChangeEvent())}focus(e,i){let r=this._getHostElement();typeof r.focus=="function"&&r.focus(i)}setActiveStyles(){this._active||(this._active=!0,this._changeDetectorRef.markForCheck())}setInactiveStyles(){this._active&&(this._active=!1,this._changeDetectorRef.markForCheck())}getLabel(){return this.viewValue}_handleKeydown(e){(e.keyCode===13||e.keyCode===32)&&!oi(e)&&(this._selectViaInteraction(),e.preventDefault())}_selectViaInteraction(){this.disabled||(this._selected=this.multiple?!this._selected:!0,this._changeDetectorRef.markForCheck(),this._emitSelectionChangeEvent(!0))}_getTabIndex(){return this.disabled?"-1":"0"}_getHostElement(){return this._element.nativeElement}ngAfterViewChecked(){if(this._selected){let e=this.viewValue;e!==this._mostRecentViewValue&&(this._mostRecentViewValue&&this._stateChanges.next(),this._mostRecentViewValue=e)}}ngOnDestroy(){this._stateChanges.complete()}_emitSelectionChangeEvent(e=!1){this.onSelectionChange.emit(new gu(this,e))}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-option"]],viewQuery:function(i,r){if(i&1&&Ne(f1,7),i&2){let o;H(o=q())&&(r._text=o.first)}},hostAttrs:["role","option",1,"mat-mdc-option","mdc-list-item"],hostVars:11,hostBindings:function(i,r){i&1&&pe("click",function(){return r._selectViaInteraction()})("keydown",function(a){return r._handleKeydown(a)}),i&2&&(ti("id",r.id),le("aria-selected",r.selected)("aria-disabled",r.disabled.toString()),Q("mdc-list-item--selected",r.selected)("mat-mdc-option-multiple",r.multiple)("mat-mdc-option-active",r.active)("mdc-list-item--disabled",r.disabled))},inputs:{value:"value",id:"id",disabled:[2,"disabled","disabled",Me]},outputs:{onSelectionChange:"onSelectionChange"},exportAs:["matOption"],ngContentSelectors:_1,decls:8,vars:5,consts:[["text",""],["aria-hidden","true",1,"mat-mdc-option-pseudo-checkbox",3,"disabled","state"],[1,"mdc-list-item__primary-text"],["state","checked","aria-hidden","true","appearance","minimal",1,"mat-mdc-option-pseudo-checkbox",3,"disabled"],[1,"cdk-visually-hidden"],["aria-hidden","true","mat-ripple","",1,"mat-mdc-option-ripple","mat-focus-indicator",3,"matRippleTrigger","matRippleDisabled"]],template:function(i,r){i&1&&(ne(g1),k(0,b1,1,2,"mat-pseudo-checkbox",1),B(1),g(2,"span",2,0),B(4,1),f(),k(5,v1,1,1,"mat-pseudo-checkbox",3),k(6,y1,2,1,"span",4),P(7,"div",5)),i&2&&(D(r.multiple?0:-1),p(5),D(!r.multiple&&r.selected&&!r.hideSingleSelectionIndicator?5:-1),p(),D(r.group&&r.group._inert?6:-1),p(),R("matRippleTrigger",r._getHostElement())("matRippleDisabled",r.disabled||r.disableRipple))},dependencies:[Pb,zs],styles:[`.mat-mdc-option {
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
  color: var(--mat-option-label-text-color, var(--mat-sys-on-surface));
  font-family: var(--mat-option-label-text-font, var(--mat-sys-label-large-font));
  line-height: var(--mat-option-label-text-line-height, var(--mat-sys-label-large-line-height));
  font-size: var(--mat-option-label-text-size, var(--mat-sys-body-large-size));
  letter-spacing: var(--mat-option-label-text-tracking, var(--mat-sys-label-large-tracking));
  font-weight: var(--mat-option-label-text-weight, var(--mat-sys-body-large-weight));
}
.mat-mdc-option:hover:not(.mdc-list-item--disabled) {
  background-color: var(--mat-option-hover-state-layer-color, color-mix(in srgb, var(--mat-sys-on-surface) calc(var(--mat-sys-hover-state-layer-opacity) * 100%), transparent));
}
.mat-mdc-option:focus.mdc-list-item, .mat-mdc-option.mat-mdc-option-active.mdc-list-item {
  background-color: var(--mat-option-focus-state-layer-color, color-mix(in srgb, var(--mat-sys-on-surface) calc(var(--mat-sys-focus-state-layer-opacity) * 100%), transparent));
  outline: 0;
}
.mat-mdc-option.mdc-list-item--selected:not(.mdc-list-item--disabled):not(.mat-mdc-option-active, .mat-mdc-option-multiple, :focus, :hover) {
  background-color: var(--mat-option-selected-state-layer-color, var(--mat-sys-secondary-container));
}
.mat-mdc-option.mdc-list-item--selected:not(.mdc-list-item--disabled):not(.mat-mdc-option-active, .mat-mdc-option-multiple, :focus, :hover) .mdc-list-item__primary-text {
  color: var(--mat-option-selected-state-label-text-color, var(--mat-sys-on-secondary-container));
}
.mat-mdc-option .mat-pseudo-checkbox {
  --mat-pseudo-checkbox-minimal-selected-checkmark-color: var(--mat-option-selected-state-label-text-color, var(--mat-sys-on-secondary-container));
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
  .mat-mdc-option.mdc-list-item--selected:not(:has(.mat-mdc-option-pseudo-checkbox))::after {
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
  [dir=rtl] .mat-mdc-option.mdc-list-item--selected:not(:has(.mat-mdc-option-pseudo-checkbox))::after {
    right: auto;
    left: 16px;
  }
}

.mat-mdc-option-multiple {
  --mat-list-list-item-selected-container-color: var(--mat-list-list-item-container-color, transparent);
}

.mat-mdc-option-active .mat-focus-indicator::before {
  content: "";
}
`],encapsulation:2,changeDetection:0})}return n})();var Nb=(()=>{class n{isErrorState(e,i){return!!(e&&e.invalid&&(e.touched||i&&i.submitted))}static \u0275fac=function(i){return new(i||n)};static \u0275prov=E({token:n,factory:n.\u0275fac,providedIn:"root"})}return n})();var Ol=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[ie]})}return n})();var _u=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[xn,Ol,Fb,ie]})}return n})();var va=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[Oi,_u,ie,Nn,ba,_u]})}return n})();var C1=["*",[["mat-chip-avatar"],["","matChipAvatar",""]],[["mat-chip-trailing-icon"],["","matChipRemove",""],["","matChipTrailingIcon",""]]],k1=["*","mat-chip-avatar, [matChipAvatar]","mat-chip-trailing-icon,[matChipRemove],[matChipTrailingIcon]"];function D1(n,t){n&1&&(g(0,"span",3),B(1,1),f())}function E1(n,t){n&1&&(g(0,"span",6),B(1,2),f())}var S1=["*"];var M1=new M("mat-chips-default-options",{providedIn:"root",factory:()=>({separatorKeyCodes:[13]})}),bu=new M("MatChipAvatar"),Lb=new M("MatChipTrailingIcon"),Bb=new M("MatChipEdit"),$b=new M("MatChipRemove"),jb=new M("MatChip"),Vb=(()=>{class n{_elementRef=m(Y);_parentChip=m(jb);_isPrimary=!0;_isLeading=!1;get disabled(){return this._disabled||this._parentChip?.disabled||!1}set disabled(e){this._disabled=e}_disabled=!1;tabIndex=-1;_allowFocusWhenDisabled=!1;_getDisabledAttribute(){return this.disabled&&!this._allowFocusWhenDisabled?"":null}constructor(){m(Je).load(on),this._elementRef.nativeElement.nodeName==="BUTTON"&&this._elementRef.nativeElement.setAttribute("type","button")}focus(){this._elementRef.nativeElement.focus()}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","matChipContent",""]],hostAttrs:[1,"mat-mdc-chip-action","mdc-evolution-chip__action","mdc-evolution-chip__action--presentational"],hostVars:8,hostBindings:function(i,r){i&2&&(le("disabled",r._getDisabledAttribute())("aria-disabled",r.disabled),Q("mdc-evolution-chip__action--primary",r._isPrimary)("mdc-evolution-chip__action--secondary",!r._isPrimary)("mdc-evolution-chip__action--trailing",!r._isPrimary&&!r._isLeading))},inputs:{disabled:[2,"disabled","disabled",Me],tabIndex:[2,"tabIndex","tabIndex",e=>e==null?-1:po(e)],_allowFocusWhenDisabled:"_allowFocusWhenDisabled"}})}return n})(),A1=(()=>{class n extends Vb{_getTabindex(){return this.disabled&&!this._allowFocusWhenDisabled?null:this.tabIndex.toString()}_handleClick(e){!this.disabled&&this._isPrimary&&(e.preventDefault(),this._parentChip._handlePrimaryActionInteraction())}_handleKeydown(e){(e.keyCode===13||e.keyCode===32)&&!this.disabled&&this._isPrimary&&!this._parentChip._isEditing&&(e.preventDefault(),this._parentChip._handlePrimaryActionInteraction())}static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(n)))(r||n)}})();static \u0275dir=G({type:n,selectors:[["","matChipAction",""]],hostVars:3,hostBindings:function(i,r){i&1&&pe("click",function(a){return r._handleClick(a)})("keydown",function(a){return r._handleKeydown(a)}),i&2&&(le("tabindex",r._getTabindex()),Q("mdc-evolution-chip__action--presentational",!1))},features:[Te]})}return n})(),fi=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["mat-chip-avatar"],["","matChipAvatar",""]],hostAttrs:["role","img",1,"mat-mdc-chip-avatar","mdc-evolution-chip__icon","mdc-evolution-chip__icon--primary"],features:[ze([{provide:bu,useExisting:n}])]})}return n})();var Gn=(()=>{class n{_changeDetectorRef=m(Ue);_elementRef=m(Y);_tagName=m(yp);_ngZone=m(K);_focusMonitor=m(yn);_globalRippleOptions=m(Pi,{optional:!0});_document=m(W);_onFocus=new z;_onBlur=new z;_isBasicChip=!1;role=null;_hasFocusInternal=!1;_pendingFocus=!1;_actionChanges;_animationsDisabled=He();_allLeadingIcons;_allTrailingIcons;_allEditIcons;_allRemoveIcons;_hasFocus(){return this._hasFocusInternal}id=m(ut).getId("mat-mdc-chip-");ariaLabel=null;ariaDescription=null;_chipListDisabled=!1;_hadFocusOnRemove=!1;_textElement;get value(){return this._value!==void 0?this._value:this._textElement.textContent.trim()}set value(e){this._value=e}_value;color;removable=!0;highlighted=!1;disableRipple=!1;get disabled(){return this._disabled||this._chipListDisabled}set disabled(e){this._disabled=e}_disabled=!1;removed=new Ee;destroyed=new Ee;basicChipAttrName="mat-basic-chip";leadingIcon;editIcon;trailingIcon;removeIcon;primaryAction;_rippleLoader=m(qs);_injector=m(te);constructor(){let e=m(Je);e.load(on),e.load(dr),this._monitorFocus(),this._rippleLoader?.configureRipple(this._elementRef.nativeElement,{className:"mat-mdc-chip-ripple",disabled:this._isRippleDisabled()})}ngOnInit(){this._isBasicChip=this._elementRef.nativeElement.hasAttribute(this.basicChipAttrName)||this._tagName.toLowerCase()===this.basicChipAttrName}ngAfterViewInit(){this._textElement=this._elementRef.nativeElement.querySelector(".mat-mdc-chip-action-label"),this._pendingFocus&&(this._pendingFocus=!1,this.focus())}ngAfterContentInit(){this._actionChanges=_t(this._allLeadingIcons.changes,this._allTrailingIcons.changes,this._allEditIcons.changes,this._allRemoveIcons.changes).subscribe(()=>this._changeDetectorRef.markForCheck())}ngDoCheck(){this._rippleLoader.setDisabled(this._elementRef.nativeElement,this._isRippleDisabled())}ngOnDestroy(){this._focusMonitor.stopMonitoring(this._elementRef),this._rippleLoader?.destroyRipple(this._elementRef.nativeElement),this._actionChanges?.unsubscribe(),this.destroyed.emit({chip:this}),this.destroyed.complete()}remove(){this.removable&&(this._hadFocusOnRemove=this._hasFocus(),this.removed.emit({chip:this}))}_isRippleDisabled(){return this.disabled||this.disableRipple||this._animationsDisabled||this._isBasicChip||!this._hasInteractiveActions()||!!this._globalRippleOptions?.disabled}_hasTrailingIcon(){return!!(this.trailingIcon||this.removeIcon)}_handleKeydown(e){(e.keyCode===8&&!e.repeat||e.keyCode===46)&&(e.preventDefault(),this.remove())}focus(){this.disabled||(this.primaryAction?this.primaryAction.focus():this._pendingFocus=!0)}_getSourceAction(e){return this._getActions().find(i=>{let r=i._elementRef.nativeElement;return r===e||r.contains(e)})}_getActions(){let e=[];return this.editIcon&&e.push(this.editIcon),this.primaryAction&&e.push(this.primaryAction),this.removeIcon&&e.push(this.removeIcon),e}_handlePrimaryActionInteraction(){}_hasInteractiveActions(){return this._getActions().length>0}_edit(e){}_monitorFocus(){this._focusMonitor.monitor(this._elementRef,!0).subscribe(e=>{let i=e!==null;i!==this._hasFocusInternal&&(this._hasFocusInternal=i,i?this._onFocus.next({chip:this}):(this._changeDetectorRef.markForCheck(),setTimeout(()=>this._ngZone.run(()=>this._onBlur.next({chip:this})))))})}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-basic-chip"],["","mat-basic-chip",""],["mat-chip"],["","mat-chip",""]],contentQueries:function(i,r,o){if(i&1&&Xe(o,bu,5)(o,Bb,5)(o,Lb,5)(o,$b,5)(o,bu,5)(o,Lb,5)(o,Bb,5)(o,$b,5),i&2){let a;H(a=q())&&(r.leadingIcon=a.first),H(a=q())&&(r.editIcon=a.first),H(a=q())&&(r.trailingIcon=a.first),H(a=q())&&(r.removeIcon=a.first),H(a=q())&&(r._allLeadingIcons=a),H(a=q())&&(r._allTrailingIcons=a),H(a=q())&&(r._allEditIcons=a),H(a=q())&&(r._allRemoveIcons=a)}},viewQuery:function(i,r){if(i&1&&Ne(A1,5),i&2){let o;H(o=q())&&(r.primaryAction=o.first)}},hostAttrs:[1,"mat-mdc-chip"],hostVars:31,hostBindings:function(i,r){i&1&&pe("keydown",function(a){return r._handleKeydown(a)}),i&2&&(ti("id",r.id),le("role",r.role)("aria-label",r.ariaLabel),vt("mat-"+(r.color||"primary")),Q("mdc-evolution-chip",!r._isBasicChip)("mdc-evolution-chip--disabled",r.disabled)("mdc-evolution-chip--with-trailing-action",r._hasTrailingIcon())("mdc-evolution-chip--with-primary-graphic",r.leadingIcon)("mdc-evolution-chip--with-primary-icon",r.leadingIcon)("mdc-evolution-chip--with-avatar",r.leadingIcon)("mat-mdc-chip-with-avatar",r.leadingIcon)("mat-mdc-chip-highlighted",r.highlighted)("mat-mdc-chip-disabled",r.disabled)("mat-mdc-basic-chip",r._isBasicChip)("mat-mdc-standard-chip",!r._isBasicChip)("mat-mdc-chip-with-trailing-icon",r._hasTrailingIcon())("_mat-animation-noopable",r._animationsDisabled))},inputs:{role:"role",id:"id",ariaLabel:[0,"aria-label","ariaLabel"],ariaDescription:[0,"aria-description","ariaDescription"],value:"value",color:"color",removable:[2,"removable","removable",Me],highlighted:[2,"highlighted","highlighted",Me],disableRipple:[2,"disableRipple","disableRipple",Me],disabled:[2,"disabled","disabled",Me]},outputs:{removed:"removed",destroyed:"destroyed"},exportAs:["matChip"],features:[ze([{provide:jb,useExisting:n}])],ngContentSelectors:k1,decls:8,vars:2,consts:[[1,"mat-mdc-chip-focus-overlay"],[1,"mdc-evolution-chip__cell","mdc-evolution-chip__cell--primary"],["matChipContent",""],[1,"mdc-evolution-chip__graphic","mat-mdc-chip-graphic"],[1,"mdc-evolution-chip__text-label","mat-mdc-chip-action-label"],[1,"mat-mdc-chip-primary-focus-indicator","mat-focus-indicator"],[1,"mdc-evolution-chip__cell","mdc-evolution-chip__cell--trailing"]],template:function(i,r){i&1&&(ne(C1),P(0,"span",0),g(1,"span",1)(2,"span",2),k(3,D1,2,0,"span",3),g(4,"span",4),B(5),P(6,"span",5),f()()(),k(7,E1,2,0,"span",6)),i&2&&(p(3),D(r.leadingIcon?3:-1),p(4),D(r._hasTrailingIcon()?7:-1))},dependencies:[Vb],styles:[`.mdc-evolution-chip,
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
.mat-mdc-standard-chip .mdc-evolution-chip__action--primary::before {
  border-width: var(--mat-chip-outline-width, 1px);
  border-radius: var(--mat-chip-container-shape-radius, 8px);
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
.mat-mdc-standard-chip:not(.mdc-evolution-chip--disabled) .mdc-evolution-chip__action--primary::before {
  border-color: var(--mat-chip-outline-color, var(--mat-sys-outline));
}
.mdc-evolution-chip__action--primary:not(.mdc-evolution-chip__action--presentational):not(.mdc-ripple-upgraded):focus::before {
  border-color: var(--mat-chip-focus-outline-color, var(--mat-sys-on-surface-variant));
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled .mdc-evolution-chip__action--primary::before {
  border-color: var(--mat-chip-disabled-outline-color, color-mix(in srgb, var(--mat-sys-on-surface) 12%, transparent));
}
.mat-mdc-standard-chip.mdc-evolution-chip--selected .mdc-evolution-chip__action--primary::before {
  border-width: var(--mat-chip-flat-selected-outline-width, 0);
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
  color: var(--mat-chip-with-trailing-icon-trailing-icon-color, var(--mat-sys-on-surface-variant));
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled .mdc-evolution-chip__action--secondary {
  color: var(--mat-chip-with-trailing-icon-disabled-trailing-icon-color, var(--mat-sys-on-surface));
}
.mat-mdc-standard-chip.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--secondary {
  padding-left: 8px;
  padding-right: 8px;
}
.mat-mdc-standard-chip.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--secondary {
  padding-left: 8px;
  padding-right: 8px;
}
.mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--secondary {
  padding-left: 8px;
  padding-right: 8px;
}
[dir=rtl] .mdc-evolution-chip--with-avatar.mdc-evolution-chip--with-primary-graphic.mdc-evolution-chip--with-trailing-action .mdc-evolution-chip__action--secondary {
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
  font-family: var(--mat-chip-label-text-font, var(--mat-sys-label-large-font));
  line-height: var(--mat-chip-label-text-line-height, var(--mat-sys-label-large-line-height));
  font-size: var(--mat-chip-label-text-size, var(--mat-sys-label-large-size));
  font-weight: var(--mat-chip-label-text-weight, var(--mat-sys-label-large-weight));
  letter-spacing: var(--mat-chip-label-text-tracking, var(--mat-sys-label-large-tracking));
}
.mat-mdc-standard-chip:not(.mdc-evolution-chip--disabled) .mdc-evolution-chip__text-label {
  color: var(--mat-chip-label-text-color, var(--mat-sys-on-surface-variant));
}
.mat-mdc-standard-chip.mdc-evolution-chip--selected:not(.mdc-evolution-chip--disabled) .mdc-evolution-chip__text-label {
  color: var(--mat-chip-selected-label-text-color, var(--mat-sys-on-secondary-container));
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled .mdc-evolution-chip__text-label, .mat-mdc-standard-chip.mdc-evolution-chip--selected.mdc-evolution-chip--disabled .mdc-evolution-chip__text-label {
  color: var(--mat-chip-disabled-label-text-color, color-mix(in srgb, var(--mat-sys-on-surface) 38%, transparent));
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
  width: var(--mat-chip-with-avatar-avatar-size, 24px);
  height: var(--mat-chip-with-avatar-avatar-size, 24px);
  font-size: var(--mat-chip-with-avatar-avatar-size, 24px);
}
.mdc-evolution-chip--selecting .mdc-evolution-chip__graphic {
  transition: width 150ms 0ms cubic-bezier(0.4, 0, 0.2, 1);
}
.mdc-evolution-chip--selectable:not(.mdc-evolution-chip--selected):not(.mdc-evolution-chip--with-primary-icon) .mdc-evolution-chip__graphic {
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
  color: var(--mat-chip-with-icon-selected-icon-color, var(--mat-sys-on-secondary-container));
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled .mdc-evolution-chip__checkmark {
  color: var(--mat-chip-with-icon-disabled-icon-color, var(--mat-sys-on-surface));
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
  opacity: calc(var(--mat-chip-trailing-action-opacity, 1) * var(--mat-chip-with-trailing-icon-disabled-trailing-icon-opacity, 0.38));
}
.mdc-evolution-chip--disabled .mdc-evolution-chip__icon--trailing.mat-mdc-chip-remove:focus {
  opacity: calc(var(--mat-chip-trailing-action-focus-opacity, 1) * var(--mat-chip-with-trailing-icon-disabled-trailing-icon-opacity, 0.38));
}

.mat-mdc-standard-chip {
  border-radius: var(--mat-chip-container-shape-radius, 8px);
  height: var(--mat-chip-container-height, 32px);
}
.mat-mdc-standard-chip:not(.mdc-evolution-chip--disabled) {
  background-color: var(--mat-chip-elevated-container-color, transparent);
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled {
  background-color: var(--mat-chip-elevated-disabled-container-color);
}
.mat-mdc-standard-chip.mdc-evolution-chip--selected:not(.mdc-evolution-chip--disabled) {
  background-color: var(--mat-chip-elevated-selected-container-color, var(--mat-sys-secondary-container));
}
.mat-mdc-standard-chip.mdc-evolution-chip--selected.mdc-evolution-chip--disabled {
  background-color: var(--mat-chip-flat-disabled-selected-container-color, color-mix(in srgb, var(--mat-sys-on-surface) 12%, transparent));
}
@media (forced-colors: active) {
  .mat-mdc-standard-chip {
    outline: solid 1px;
  }
}

.mat-mdc-standard-chip .mdc-evolution-chip__icon--primary {
  border-radius: var(--mat-chip-with-avatar-avatar-shape-radius, 24px);
  width: var(--mat-chip-with-icon-icon-size, 18px);
  height: var(--mat-chip-with-icon-icon-size, 18px);
  font-size: var(--mat-chip-with-icon-icon-size, 18px);
}
.mdc-evolution-chip--selected .mdc-evolution-chip__icon--primary {
  opacity: 0;
}
.mat-mdc-standard-chip:not(.mdc-evolution-chip--disabled) .mdc-evolution-chip__icon--primary {
  color: var(--mat-chip-with-icon-icon-color, var(--mat-sys-on-surface-variant));
}
.mat-mdc-standard-chip.mdc-evolution-chip--disabled .mdc-evolution-chip__icon--primary {
  color: var(--mat-chip-with-icon-disabled-icon-color, var(--mat-sys-on-surface));
}

.mat-mdc-chip-highlighted {
  --mat-chip-with-icon-icon-color: var(--mat-chip-with-icon-selected-icon-color, var(--mat-sys-on-secondary-container));
  --mat-chip-elevated-container-color: var(--mat-chip-elevated-selected-container-color, var(--mat-sys-secondary-container));
  --mat-chip-label-text-color: var(--mat-chip-selected-label-text-color, var(--mat-sys-on-secondary-container));
  --mat-chip-outline-width: var(--mat-chip-flat-selected-outline-width, 0);
}

.mat-mdc-chip-focus-overlay {
  background: var(--mat-chip-focus-state-layer-color, var(--mat-sys-on-surface-variant));
}
.mat-mdc-chip-selected .mat-mdc-chip-focus-overlay, .mat-mdc-chip-highlighted .mat-mdc-chip-focus-overlay {
  background: var(--mat-chip-selected-focus-state-layer-color, var(--mat-sys-on-secondary-container));
}
.mat-mdc-chip:hover .mat-mdc-chip-focus-overlay {
  background: var(--mat-chip-hover-state-layer-color, var(--mat-sys-on-surface-variant));
  opacity: var(--mat-chip-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity));
}
.mat-mdc-chip-focus-overlay .mat-mdc-chip-selected:hover, .mat-mdc-chip-highlighted:hover .mat-mdc-chip-focus-overlay {
  background: var(--mat-chip-selected-hover-state-layer-color, var(--mat-sys-on-secondary-container));
  opacity: var(--mat-chip-selected-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity));
}
.mat-mdc-chip.cdk-focused .mat-mdc-chip-focus-overlay {
  background: var(--mat-chip-focus-state-layer-color, var(--mat-sys-on-surface-variant));
  opacity: var(--mat-chip-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity));
}
.mat-mdc-chip-selected.cdk-focused .mat-mdc-chip-focus-overlay, .mat-mdc-chip-highlighted.cdk-focused .mat-mdc-chip-focus-overlay {
  background: var(--mat-chip-selected-focus-state-layer-color, var(--mat-sys-on-secondary-container));
  opacity: var(--mat-chip-selected-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity));
}

.mdc-evolution-chip--disabled:not(.mdc-evolution-chip--selected) .mat-mdc-chip-avatar {
  opacity: var(--mat-chip-with-avatar-disabled-avatar-opacity, 0.38);
}

.mdc-evolution-chip--disabled .mdc-evolution-chip__icon--trailing {
  opacity: var(--mat-chip-with-trailing-icon-disabled-trailing-icon-opacity, 0.38);
}

.mdc-evolution-chip--disabled.mdc-evolution-chip--selected .mdc-evolution-chip__checkmark {
  opacity: var(--mat-chip-with-icon-disabled-icon-opacity, 0.38);
}

.mat-mdc-standard-chip.mdc-evolution-chip--disabled {
  opacity: var(--mat-chip-disabled-container-opacity, 1);
}
.mat-mdc-standard-chip.mdc-evolution-chip--selected .mdc-evolution-chip__icon--trailing, .mat-mdc-standard-chip.mat-mdc-chip-highlighted .mdc-evolution-chip__icon--trailing {
  color: var(--mat-chip-selected-trailing-icon-color, var(--mat-sys-on-secondary-container));
}
.mat-mdc-standard-chip.mdc-evolution-chip--selected.mdc-evolution-chip--disabled .mdc-evolution-chip__icon--trailing, .mat-mdc-standard-chip.mat-mdc-chip-highlighted.mdc-evolution-chip--disabled .mdc-evolution-chip__icon--trailing {
  color: var(--mat-chip-selected-disabled-trailing-icon-color, var(--mat-sys-on-surface));
}

.mat-mdc-chip-edit, .mat-mdc-chip-remove {
  opacity: var(--mat-chip-trailing-action-opacity, 1);
}
.mat-mdc-chip-edit:focus, .mat-mdc-chip-remove:focus {
  opacity: var(--mat-chip-trailing-action-focus-opacity, 1);
}
.mat-mdc-chip-edit::after, .mat-mdc-chip-remove::after {
  background-color: var(--mat-chip-trailing-action-state-layer-color, var(--mat-sys-on-surface-variant));
}
.mat-mdc-chip-edit:hover::after, .mat-mdc-chip-remove:hover::after {
  opacity: calc(var(--mat-chip-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity)) + var(--mat-chip-trailing-action-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity)));
}
.mat-mdc-chip-edit:focus::after, .mat-mdc-chip-remove:focus::after {
  opacity: calc(var(--mat-chip-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity)) + var(--mat-chip-trailing-action-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity)));
}

.mat-mdc-chip-selected .mat-mdc-chip-remove::after,
.mat-mdc-chip-highlighted .mat-mdc-chip-remove::after {
  background-color: var(--mat-chip-selected-trailing-action-state-layer-color, var(--mat-sys-on-secondary-container));
}

.mat-mdc-chip.cdk-focused .mat-mdc-chip-edit:focus::after, .mat-mdc-chip.cdk-focused .mat-mdc-chip-remove:focus::after {
  opacity: calc(var(--mat-chip-selected-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity)) + var(--mat-chip-trailing-action-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity)));
}
.mat-mdc-chip.cdk-focused .mat-mdc-chip-edit:hover::after, .mat-mdc-chip.cdk-focused .mat-mdc-chip-remove:hover::after {
  opacity: calc(var(--mat-chip-selected-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity)) + var(--mat-chip-trailing-action-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity)));
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
  color: var(--mat-chip-with-icon-icon-color, currentColor);
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
  margin: calc(calc(var(--mat-focus-indicator-border-width, 3px) + 2px) * -1);
}

.mat-mdc-chip-edit::before, .mat-mdc-chip-remove::before {
  margin: calc(var(--mat-focus-indicator-border-width, 3px) * -1);
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
`],encapsulation:2,changeDetection:0})}return n})();var Ur=(()=>{class n{_elementRef=m(Y);_changeDetectorRef=m(Ue);_dir=m(wt,{optional:!0});_lastDestroyedFocusedChipIndex=null;_keyManager;_destroyed=new z;_defaultRole="presentation";get chipFocusChanges(){return this._getChipStream(e=>e._onFocus)}get chipDestroyedChanges(){return this._getChipStream(e=>e.destroyed)}get chipRemovedChanges(){return this._getChipStream(e=>e.removed)}get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._syncChipsState()}_disabled=!1;get empty(){return!this._chips||this._chips.length===0}get role(){return this._explicitRole?this._explicitRole:this.empty?null:this._defaultRole}tabIndex=0;set role(e){this._explicitRole=e}_explicitRole=null;get focused(){return this._hasFocusedChip()}_chips;_chipActions=new In;constructor(){}ngAfterViewInit(){this._setUpFocusManagement(),this._trackChipSetChanges(),this._trackDestroyedFocusedChip()}ngOnDestroy(){this._keyManager?.destroy(),this._chipActions.destroy(),this._destroyed.next(),this._destroyed.complete()}_hasFocusedChip(){return this._chips&&this._chips.some(e=>e._hasFocus())}_syncChipsState(){this._chips?.forEach(e=>{e._chipListDisabled=this._disabled,e._changeDetectorRef.markForCheck()})}focus(){}_handleKeydown(e){this._originatesFromChip(e)&&this._keyManager.onKeydown(e)}_isValidIndex(e){return e>=0&&e<this._chips.length}_allowFocusEscape(){let e=this._elementRef.nativeElement.tabIndex;e!==-1&&(this._elementRef.nativeElement.tabIndex=-1,setTimeout(()=>this._elementRef.nativeElement.tabIndex=e))}_getChipStream(e){return this._chips.changes.pipe(ct(null),jt(()=>_t(...this._chips.map(e))))}_originatesFromChip(e){let i=e.target;for(;i&&i!==this._elementRef.nativeElement;){if(i.classList.contains("mat-mdc-chip"))return!0;i=i.parentElement}return!1}_setUpFocusManagement(){this._chips.changes.pipe(ct(this._chips)).subscribe(e=>{let i=[];e.forEach(r=>r._getActions().forEach(o=>i.push(o))),this._chipActions.reset(i),this._chipActions.notifyOnChanges()}),this._keyManager=new Mi(this._chipActions).withVerticalOrientation().withHorizontalOrientation(this._dir?this._dir.value:"ltr").withHomeAndEnd().skipPredicate(e=>this._skipPredicate(e)),this.chipFocusChanges.pipe(De(this._destroyed)).subscribe(({chip:e})=>{let i=e._getSourceAction(document.activeElement);i&&this._keyManager.updateActiveItem(i)}),this._dir?.change.pipe(De(this._destroyed)).subscribe(e=>this._keyManager.withHorizontalOrientation(e))}_skipPredicate(e){return e.disabled}_trackChipSetChanges(){this._chips.changes.pipe(ct(null),De(this._destroyed)).subscribe(()=>{this.disabled&&Promise.resolve().then(()=>this._syncChipsState()),this._redirectDestroyedChipFocus()})}_trackDestroyedFocusedChip(){this.chipDestroyedChanges.pipe(De(this._destroyed)).subscribe(e=>{let r=this._chips.toArray().indexOf(e.chip),o=e.chip._hasFocus(),a=e.chip._hadFocusOnRemove&&this._keyManager.activeItem&&e.chip._getActions().includes(this._keyManager.activeItem),s=o||a;this._isValidIndex(r)&&s&&(this._lastDestroyedFocusedChipIndex=r)})}_redirectDestroyedChipFocus(){if(this._lastDestroyedFocusedChipIndex!=null){if(this._chips.length){let e=Math.min(this._lastDestroyedFocusedChipIndex,this._chips.length-1),i=this._chips.toArray()[e];i.disabled?this._chips.length===1?this.focus():this._keyManager.setPreviousItemActive():i.focus()}else this.focus();this._lastDestroyedFocusedChipIndex=null}}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-chip-set"]],contentQueries:function(i,r,o){if(i&1&&Xe(o,Gn,5),i&2){let a;H(a=q())&&(r._chips=a)}},hostAttrs:[1,"mat-mdc-chip-set","mdc-evolution-chip-set"],hostVars:1,hostBindings:function(i,r){i&1&&pe("keydown",function(a){return r._handleKeydown(a)}),i&2&&le("role",r.role)},inputs:{disabled:[2,"disabled","disabled",Me],role:"role",tabIndex:[2,"tabIndex","tabIndex",e=>e==null?0:po(e)]},ngContentSelectors:S1,decls:2,vars:0,consts:[["role","presentation",1,"mdc-evolution-chip-set__chips"]],template:function(i,r){i&1&&(ne(),qe(0,"div",0),B(1),Qe())},styles:[`.mat-mdc-chip-set {
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
`],encapsulation:2,changeDetection:0})}return n})();var dn=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({providers:[Nb,{provide:M1,useValue:{separatorKeyCodes:[13]}}],imports:[xn,ie]})}return n})();var I1=["*"],T1=`.mdc-list {
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
  background-color: var(--mat-list-list-item-container-color, transparent);
  border-radius: var(--mat-list-list-item-container-shape, var(--mat-sys-corner-none));
}
.mdc-list-item.mdc-list-item--selected {
  background-color: var(--mat-list-list-item-selected-container-color);
}
.mdc-list-item:focus {
  outline: 0;
}
.mdc-list-item.mdc-list-item--disabled {
  cursor: auto;
}
.mdc-list-item.mdc-list-item--with-one-line {
  height: var(--mat-list-list-item-one-line-container-height, 48px);
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
  height: var(--mat-list-list-item-two-line-container-height, 64px);
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
  height: var(--mat-list-list-item-three-line-container-height, 88px);
}
.mdc-list-item.mdc-list-item--with-three-lines .mdc-list-item__start {
  align-self: flex-start;
  margin-top: 16px;
}
.mdc-list-item.mdc-list-item--with-three-lines .mdc-list-item__end {
  align-self: flex-start;
  margin-top: 16px;
}
.mdc-list-item.mdc-list-item--selected::before, .mdc-list-item.mdc-list-item--selected:focus::before, .mdc-list-item:not(.mdc-list-item--selected):focus::before {
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
  color: var(--mat-list-list-item-leading-icon-color, var(--mat-sys-on-surface-variant));
  width: var(--mat-list-list-item-leading-icon-size, 24px);
  height: var(--mat-list-list-item-leading-icon-size, 24px);
  margin-left: 16px;
  margin-right: 32px;
}
[dir=rtl] .mdc-list-item--with-leading-icon .mdc-list-item__start {
  margin-left: 32px;
  margin-right: 16px;
}
.mdc-list-item--with-leading-icon:hover .mdc-list-item__start {
  color: var(--mat-list-list-item-hover-leading-icon-color);
}
.mdc-list-item--with-leading-avatar .mdc-list-item__start {
  width: var(--mat-list-list-item-leading-avatar-size, 40px);
  height: var(--mat-list-list-item-leading-avatar-size, 40px);
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
  font-family: var(--mat-list-list-item-trailing-supporting-text-font, var(--mat-sys-label-small-font));
  line-height: var(--mat-list-list-item-trailing-supporting-text-line-height, var(--mat-sys-label-small-line-height));
  font-size: var(--mat-list-list-item-trailing-supporting-text-size, var(--mat-sys-label-small-size));
  font-weight: var(--mat-list-list-item-trailing-supporting-text-weight, var(--mat-sys-label-small-weight));
  letter-spacing: var(--mat-list-list-item-trailing-supporting-text-tracking, var(--mat-sys-label-small-tracking));
}
.mdc-list-item--with-trailing-icon .mdc-list-item__end {
  color: var(--mat-list-list-item-trailing-icon-color, var(--mat-sys-on-surface-variant));
  width: var(--mat-list-list-item-trailing-icon-size, 24px);
  height: var(--mat-list-list-item-trailing-icon-size, 24px);
}
.mdc-list-item--with-trailing-icon:hover .mdc-list-item__end {
  color: var(--mat-list-list-item-hover-trailing-icon-color);
}
.mdc-list-item.mdc-list-item--with-trailing-meta .mdc-list-item__end {
  color: var(--mat-list-list-item-trailing-supporting-text-color, var(--mat-sys-on-surface-variant));
}
.mdc-list-item--selected.mdc-list-item--with-trailing-icon .mdc-list-item__end {
  color: var(--mat-list-list-item-selected-trailing-icon-color, var(--mat-sys-primary));
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
  color: var(--mat-list-list-item-label-text-color, var(--mat-sys-on-surface));
  font-family: var(--mat-list-list-item-label-text-font, var(--mat-sys-body-large-font));
  line-height: var(--mat-list-list-item-label-text-line-height, var(--mat-sys-body-large-line-height));
  font-size: var(--mat-list-list-item-label-text-size, var(--mat-sys-body-large-size));
  font-weight: var(--mat-list-list-item-label-text-weight, var(--mat-sys-body-large-weight));
  letter-spacing: var(--mat-list-list-item-label-text-tracking, var(--mat-sys-body-large-tracking));
}
.mdc-list-item:hover .mdc-list-item__primary-text {
  color: var(--mat-list-list-item-hover-label-text-color, var(--mat-sys-on-surface));
}
.mdc-list-item:focus .mdc-list-item__primary-text {
  color: var(--mat-list-list-item-focus-label-text-color, var(--mat-sys-on-surface));
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
  color: var(--mat-list-list-item-supporting-text-color, var(--mat-sys-on-surface-variant));
  font-family: var(--mat-list-list-item-supporting-text-font, var(--mat-sys-body-medium-font));
  line-height: var(--mat-list-list-item-supporting-text-line-height, var(--mat-sys-body-medium-line-height));
  font-size: var(--mat-list-list-item-supporting-text-size, var(--mat-sys-body-medium-size));
  font-weight: var(--mat-list-list-item-supporting-text-weight, var(--mat-sys-body-medium-weight));
  letter-spacing: var(--mat-list-list-item-supporting-text-tracking, var(--mat-sys-body-medium-tracking));
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
  opacity: var(--mat-list-list-item-disabled-label-text-opacity, 0.3);
}
.mdc-list-item--disabled.mdc-list-item--with-leading-icon .mdc-list-item__start {
  color: var(--mat-list-list-item-disabled-leading-icon-color, var(--mat-sys-on-surface));
  opacity: var(--mat-list-list-item-disabled-leading-icon-opacity, 0.38);
}
.mdc-list-item--disabled.mdc-list-item--with-trailing-icon .mdc-list-item__end {
  color: var(--mat-list-list-item-disabled-trailing-icon-color, var(--mat-sys-on-surface));
  opacity: var(--mat-list-list-item-disabled-trailing-icon-opacity, 0.38);
}

.mat-mdc-list-item.mat-mdc-list-item-both-leading-and-trailing, [dir=rtl] .mat-mdc-list-item.mat-mdc-list-item-both-leading-and-trailing {
  padding-left: 0;
  padding-right: 0;
}

.mdc-list-item.mdc-list-item--disabled .mdc-list-item__primary-text {
  color: var(--mat-list-list-item-disabled-label-text-color, var(--mat-sys-on-surface));
}

.mdc-list-item:hover::before {
  background-color: var(--mat-list-list-item-hover-state-layer-color, var(--mat-sys-on-surface));
  opacity: var(--mat-list-list-item-hover-state-layer-opacity, var(--mat-sys-hover-state-layer-opacity));
}

.mdc-list-item.mdc-list-item--disabled::before {
  background-color: var(--mat-list-list-item-disabled-state-layer-color, var(--mat-sys-on-surface));
  opacity: var(--mat-list-list-item-disabled-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity));
}

.mdc-list-item:focus::before {
  background-color: var(--mat-list-list-item-focus-state-layer-color, var(--mat-sys-on-surface));
  opacity: var(--mat-list-list-item-focus-state-layer-opacity, var(--mat-sys-focus-state-layer-opacity));
}

.mdc-list-item--disabled .mdc-radio,
.mdc-list-item--disabled .mdc-checkbox {
  opacity: var(--mat-list-list-item-disabled-label-text-opacity, 0.3);
}

.mdc-list-item--with-leading-avatar .mat-mdc-list-item-avatar {
  border-radius: var(--mat-list-list-item-leading-avatar-shape, var(--mat-sys-corner-full));
  background-color: var(--mat-list-list-item-leading-avatar-color, var(--mat-sys-primary-container));
}

.mat-mdc-list-item-icon {
  font-size: var(--mat-list-list-item-leading-icon-size, 24px);
}

@media (forced-colors: active) {
  a.mdc-list-item--activated::after {
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
  margin-inline-start: var(--mat-list-list-item-leading-icon-start-space, 16px);
  margin-inline-end: var(--mat-list-list-item-leading-icon-end-space, 16px);
}

.mat-mdc-nav-list .mat-mdc-list-item {
  border-radius: var(--mat-list-active-indicator-shape, var(--mat-sys-corner-full));
  --mat-focus-indicator-border-radius: var(--mat-list-active-indicator-shape, var(--mat-sys-corner-full));
}
.mat-mdc-nav-list .mat-mdc-list-item.mdc-list-item--activated {
  background-color: var(--mat-list-active-indicator-color, var(--mat-sys-secondary-container));
}
`,O1=["unscopedContent"],R1=["text"],P1=[[["","matListItemAvatar",""],["","matListItemIcon",""]],[["","matListItemTitle",""]],[["","matListItemLine",""]],"*",[["","matListItemMeta",""]],[["mat-divider"]]],F1=["[matListItemAvatar],[matListItemIcon]","[matListItemTitle]","[matListItemLine]","*","[matListItemMeta]","mat-divider"];var N1=new M("ListOption"),yu=(()=>{class n{_elementRef=m(Y);constructor(){}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","matListItemTitle",""]],hostAttrs:[1,"mat-mdc-list-item-title","mdc-list-item__primary-text"]})}return n})(),xu=(()=>{class n{_elementRef=m(Y);constructor(){}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","matListItemLine",""]],hostAttrs:[1,"mat-mdc-list-item-line","mdc-list-item__secondary-text"]})}return n})(),L1=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,selectors:[["","matListItemMeta",""]],hostAttrs:[1,"mat-mdc-list-item-meta","mdc-list-item__end"]})}return n})(),zb=(()=>{class n{_listOption=m(N1,{optional:!0});constructor(){}_isAlignedAtStart(){return!this._listOption||this._listOption?._getTogglePosition()==="after"}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,hostVars:4,hostBindings:function(i,r){i&2&&Q("mdc-list-item__start",r._isAlignedAtStart())("mdc-list-item__end",!r._isAlignedAtStart())}})}return n})(),B1=(()=>{class n extends zb{static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(n)))(r||n)}})();static \u0275dir=G({type:n,selectors:[["","matListItemAvatar",""]],hostAttrs:[1,"mat-mdc-list-item-avatar"],features:[Te]})}return n})(),wu=(()=>{class n extends zb{static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(n)))(r||n)}})();static \u0275dir=G({type:n,selectors:[["","matListItemIcon",""]],hostAttrs:[1,"mat-mdc-list-item-icon"],features:[Te]})}return n})(),$1=new M("MAT_LIST_CONFIG"),vu=(()=>{class n{_isNonInteractive=!0;get disableRipple(){return this._disableRipple}set disableRipple(e){this._disableRipple=kt(e)}_disableRipple=!1;get disabled(){return this._disabled()}set disabled(e){this._disabled.set(kt(e))}_disabled=X(!1);_defaultOptions=m($1,{optional:!0});static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,hostVars:1,hostBindings:function(i,r){i&2&&le("aria-disabled",r.disabled)},inputs:{disableRipple:"disableRipple",disabled:"disabled"}})}return n})(),j1=(()=>{class n{_elementRef=m(Y);_ngZone=m(K);_listBase=m(vu,{optional:!0});_platform=m(Ce);_hostElement;_isButtonElement;_noopAnimations=He();_avatars;_icons;set lines(e){this._explicitLines=Fn(e,null),this._updateItemLines(!1)}_explicitLines=null;get disableRipple(){return this.disabled||this._disableRipple||this._noopAnimations||!!this._listBase?.disableRipple}set disableRipple(e){this._disableRipple=kt(e)}_disableRipple=!1;get disabled(){return this._disabled()||!!this._listBase?.disabled}set disabled(e){this._disabled.set(kt(e))}_disabled=X(!1);_subscriptions=new lt;_rippleRenderer=null;_hasUnscopedTextContent=!1;rippleConfig;get rippleDisabled(){return this.disableRipple||!!this.rippleConfig.disabled}constructor(){m(Je).load(on);let e=m(Pi,{optional:!0});this.rippleConfig=e||{},this._hostElement=this._elementRef.nativeElement,this._isButtonElement=this._hostElement.nodeName.toLowerCase()==="button",this._listBase&&!this._listBase._isNonInteractive&&this._initInteractiveListItem(),this._isButtonElement&&!this._hostElement.hasAttribute("type")&&this._hostElement.setAttribute("type","button")}ngAfterViewInit(){this._monitorProjectedLinesAndTitle(),this._updateItemLines(!0)}ngOnDestroy(){this._subscriptions.unsubscribe(),this._rippleRenderer!==null&&this._rippleRenderer._removeTriggerEvents()}_hasIconOrAvatar(){return!!(this._avatars.length||this._icons.length)}_initInteractiveListItem(){this._hostElement.classList.add("mat-mdc-list-item-interactive"),this._rippleRenderer=new Ri(this,this._ngZone,this._hostElement,this._platform,m(te)),this._rippleRenderer.setupTriggerEvents(this._hostElement)}_monitorProjectedLinesAndTitle(){this._ngZone.runOutsideAngular(()=>{this._subscriptions.add(_t(this._lines.changes,this._titles.changes).subscribe(()=>this._updateItemLines(!1)))})}_updateItemLines(e){if(!this._lines||!this._titles||!this._unscopedContent)return;e&&this._checkDomForUnscopedTextContent();let i=this._explicitLines??this._inferLinesFromContent(),r=this._unscopedContent.nativeElement;if(this._hostElement.classList.toggle("mat-mdc-list-item-single-line",i<=1),this._hostElement.classList.toggle("mdc-list-item--with-one-line",i<=1),this._hostElement.classList.toggle("mdc-list-item--with-two-lines",i===2),this._hostElement.classList.toggle("mdc-list-item--with-three-lines",i===3),this._hasUnscopedTextContent){let o=this._titles.length===0&&i===1;r.classList.toggle("mdc-list-item__primary-text",o),r.classList.toggle("mdc-list-item__secondary-text",!o)}else r.classList.remove("mdc-list-item__primary-text"),r.classList.remove("mdc-list-item__secondary-text")}_inferLinesFromContent(){let e=this._titles.length+this._lines.length;return this._hasUnscopedTextContent&&(e+=1),e}_checkDomForUnscopedTextContent(){this._hasUnscopedTextContent=Array.from(this._unscopedContent.nativeElement.childNodes).filter(e=>e.nodeType!==e.COMMENT_NODE).some(e=>!!(e.textContent&&e.textContent.trim()))}static \u0275fac=function(i){return new(i||n)};static \u0275dir=G({type:n,contentQueries:function(i,r,o){if(i&1&&Xe(o,B1,4)(o,wu,4),i&2){let a;H(a=q())&&(r._avatars=a),H(a=q())&&(r._icons=a)}},hostVars:4,hostBindings:function(i,r){i&2&&(le("aria-disabled",r.disabled)("disabled",r._isButtonElement&&r.disabled||null),Q("mdc-list-item--disabled",r.disabled))},inputs:{lines:"lines",disableRipple:"disableRipple",disabled:"disabled"}})}return n})();var Hb=(()=>{class n extends vu{static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(n)))(r||n)}})();static \u0275cmp=I({type:n,selectors:[["mat-list"]],hostAttrs:[1,"mat-mdc-list","mat-mdc-list-base","mdc-list"],exportAs:["matList"],features:[ze([{provide:vu,useExisting:n}]),Te],ngContentSelectors:I1,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},styles:[T1],encapsulation:2,changeDetection:0})}return n})(),qb=(()=>{class n extends j1{_lines;_titles;_meta;_unscopedContent;_itemText;get activated(){return this._activated}set activated(e){this._activated=kt(e)}_activated=!1;_getAriaCurrent(){return this._hostElement.nodeName==="A"&&this._activated?"page":null}_hasBothLeadingAndTrailing(){return this._meta.length!==0&&(this._avatars.length!==0||this._icons.length!==0)}static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(n)))(r||n)}})();static \u0275cmp=I({type:n,selectors:[["mat-list-item"],["a","mat-list-item",""],["button","mat-list-item",""]],contentQueries:function(i,r,o){if(i&1&&Xe(o,xu,5)(o,yu,5)(o,L1,5),i&2){let a;H(a=q())&&(r._lines=a),H(a=q())&&(r._titles=a),H(a=q())&&(r._meta=a)}},viewQuery:function(i,r){if(i&1&&Ne(O1,5)(R1,5),i&2){let o;H(o=q())&&(r._unscopedContent=o.first),H(o=q())&&(r._itemText=o.first)}},hostAttrs:[1,"mat-mdc-list-item","mdc-list-item"],hostVars:13,hostBindings:function(i,r){i&2&&(le("aria-current",r._getAriaCurrent()),Q("mdc-list-item--activated",r.activated)("mdc-list-item--with-leading-avatar",r._avatars.length!==0)("mdc-list-item--with-leading-icon",r._icons.length!==0)("mdc-list-item--with-trailing-meta",r._meta.length!==0)("mat-mdc-list-item-both-leading-and-trailing",r._hasBothLeadingAndTrailing())("_mat-animation-noopable",r._noopAnimations))},inputs:{activated:"activated"},exportAs:["matListItem"],features:[Te],ngContentSelectors:F1,decls:10,vars:0,consts:[["unscopedContent",""],[1,"mdc-list-item__content"],[1,"mat-mdc-list-item-unscoped-content",3,"cdkObserveContent"],[1,"mat-focus-indicator"]],template:function(i,r){i&1&&(ne(P1),B(0),g(1,"span",1),B(2,1),B(3,2),g(4,"span",2,0),pe("cdkObserveContent",function(){return r._updateItemLines(!0)}),B(6,3),f()(),B(7,4),B(8,5),P(9,"div",3))},dependencies:[Qh],encapsulation:2,changeDetection:0})}return n})();var ya=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[Ds,xn,Ol,ie,ga]})}return n})();var Nl=["*"],z1=["content"],H1=[[["mat-drawer"]],[["mat-drawer-content"]],"*"],q1=["mat-drawer","mat-drawer-content","*"];function U1(n,t){if(n&1){let e=ot();g(0,"div",1),pe("click",function(){Ke(e);let r=C();return Ye(r._onBackdropClicked())}),f()}if(n&2){let e=C();Q("mat-drawer-shown",e._isShowingBackdrop())}}function G1(n,t){n&1&&(g(0,"mat-drawer-content"),B(1,2),f())}var W1=[[["mat-sidenav"]],[["mat-sidenav-content"]],"*"],K1=["mat-sidenav","mat-sidenav-content","*"];function Y1(n,t){if(n&1){let e=ot();g(0,"div",1),pe("click",function(){Ke(e);let r=C();return Ye(r._onBackdropClicked())}),f()}if(n&2){let e=C();Q("mat-drawer-shown",e._isShowingBackdrop())}}function Q1(n,t){n&1&&(g(0,"mat-sidenav-content"),B(1,2),f())}var X1=`.mat-drawer-container {
  position: relative;
  z-index: 1;
  color: var(--mat-sidenav-content-text-color, var(--mat-sys-on-background));
  background-color: var(--mat-sidenav-content-background-color, var(--mat-sys-background));
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
  background-color: var(--mat-sidenav-scrim-color, color-mix(in srgb, var(--mat-sys-neutral-variant20) 40%, transparent));
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
  color: var(--mat-sidenav-container-text-color, var(--mat-sys-on-surface-variant));
  box-shadow: var(--mat-sidenav-container-elevation-shadow, none);
  background-color: var(--mat-sidenav-container-background-color, var(--mat-sys-surface));
  border-top-right-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-bottom-right-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  width: var(--mat-sidenav-container-width, 360px);
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
  border-top-left-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-bottom-left-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}
[dir=rtl] .mat-drawer {
  border-top-left-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-bottom-left-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  transform: translate3d(100%, 0, 0);
}
[dir=rtl] .mat-drawer.mat-drawer-end {
  border-top-right-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-bottom-right-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
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
  border-right-color: var(--mat-sidenav-container-divider-color, transparent);
  border-right-width: 1px;
  border-right-style: solid;
}
.mat-drawer-side.mat-drawer-end {
  border-left-color: var(--mat-sidenav-container-divider-color, transparent);
  border-left-width: 1px;
  border-left-style: solid;
  border-right: none;
}
[dir=rtl] .mat-drawer-side {
  border-left-color: var(--mat-sidenav-container-divider-color, transparent);
  border-left-width: 1px;
  border-left-style: solid;
  border-right: none;
}
[dir=rtl] .mat-drawer-side.mat-drawer-end {
  border-right-color: var(--mat-sidenav-container-divider-color, transparent);
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
`;var Z1=new M("MAT_DRAWER_DEFAULT_AUTOSIZE",{providedIn:"root",factory:()=>!1}),Du=new M("MAT_DRAWER_CONTAINER"),Pl=(()=>{class n extends fr{_platform=m(Ce);_changeDetectorRef=m(Ue);_container=m(ku);constructor(){let e=m(Y),i=m(Ii),r=m(K);super(e,i,r)}ngAfterContentInit(){this._container._contentMarginChanges.subscribe(()=>{this._changeDetectorRef.markForCheck()})}_shouldBeHidden(){if(this._platform.isBrowser)return!1;let{start:e,end:i}=this._container;return e!=null&&e.mode!=="over"&&e.opened||i!=null&&i.mode!=="over"&&i.opened}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-drawer-content"]],hostAttrs:[1,"mat-drawer-content"],hostVars:6,hostBindings:function(i,r){i&2&&(bi("margin-left",r._container._contentMargins.left,"px")("margin-right",r._container._contentMargins.right,"px"),Q("mat-drawer-content-hidden",r._shouldBeHidden()))},features:[ze([{provide:fr,useExisting:n}]),Te],ngContentSelectors:Nl,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},encapsulation:2,changeDetection:0})}return n})(),Cu=(()=>{class n{_elementRef=m(Y);_focusTrapFactory=m(td);_focusMonitor=m(yn);_platform=m(Ce);_ngZone=m(K);_renderer=m(rt);_interactivityChecker=m(Ss);_doc=m(W);_container=m(Du,{optional:!0});_focusTrap=null;_elementFocusedBeforeDrawerWasOpened=null;_eventCleanups;_isAttached=!1;_anchor=null;get position(){return this._position}set position(e){e=e==="end"?"end":"start",e!==this._position&&(this._isAttached&&this._updatePositionInParent(e),this._position=e,this.onPositionChanged.emit())}_position="start";get mode(){return this._mode}set mode(e){this._mode=e,this._updateFocusTrapState(),this._modeChanged.next()}_mode="over";get disableClose(){return this._disableClose}set disableClose(e){this._disableClose=kt(e)}_disableClose=!1;get autoFocus(){let e=this._autoFocus;return e??(this.mode==="side"?"dialog":"first-tabbable")}set autoFocus(e){(e==="true"||e==="false"||e==null)&&(e=kt(e)),this._autoFocus=e}_autoFocus;get opened(){return this._opened()}set opened(e){this.toggle(kt(e))}_opened=X(!1);_openedVia=null;_animationStarted=new z;_animationEnd=new z;openedChange=new Ee(!0);_openedStream=this.openedChange.pipe(Be(e=>e),xe(()=>{}));openedStart=this._animationStarted.pipe(Be(()=>this.opened),to(void 0));_closedStream=this.openedChange.pipe(Be(e=>!e),xe(()=>{}));closedStart=this._animationStarted.pipe(Be(()=>!this.opened),to(void 0));_destroyed=new z;onPositionChanged=new Ee;_content;_modeChanged=new z;_injector=m(te);_changeDetectorRef=m(Ue);constructor(){this.openedChange.pipe(De(this._destroyed)).subscribe(e=>{e?(this._elementFocusedBeforeDrawerWasOpened=this._doc.activeElement,this._takeFocus()):this._isFocusWithinDrawer()&&this._restoreFocus(this._openedVia||"program")}),this._eventCleanups=this._ngZone.runOutsideAngular(()=>{let e=this._renderer,i=this._elementRef.nativeElement;return[e.listen(i,"keydown",r=>{r.keyCode===27&&!this.disableClose&&!oi(r)&&this._ngZone.run(()=>{this.close(),r.stopPropagation(),r.preventDefault()})}),e.listen(i,"transitionend",this._handleTransitionEvent),e.listen(i,"transitioncancel",this._handleTransitionEvent)]}),this._animationEnd.subscribe(()=>{this.openedChange.emit(this.opened)})}_forceFocus(e,i){this._interactivityChecker.isFocusable(e)||(e.tabIndex=-1,this._ngZone.runOutsideAngular(()=>{let r=()=>{o(),a(),e.removeAttribute("tabindex")},o=this._renderer.listen(e,"blur",r),a=this._renderer.listen(e,"mousedown",r)})),e.focus(i)}_focusByCssSelector(e,i){let r=this._elementRef.nativeElement.querySelector(e);r&&this._forceFocus(r,i)}_takeFocus(){if(!this._focusTrap)return;let e=this._elementRef.nativeElement;switch(this.autoFocus){case!1:case"dialog":return;case!0:case"first-tabbable":mt(()=>{!this._focusTrap.focusInitialElement()&&typeof e.focus=="function"&&e.focus()},{injector:this._injector});break;case"first-heading":this._focusByCssSelector('h1, h2, h3, h4, h5, h6, [role="heading"]');break;default:this._focusByCssSelector(this.autoFocus);break}}_restoreFocus(e){this.autoFocus!=="dialog"&&(this._elementFocusedBeforeDrawerWasOpened?this._focusMonitor.focusVia(this._elementFocusedBeforeDrawerWasOpened,e):this._elementRef.nativeElement.blur(),this._elementFocusedBeforeDrawerWasOpened=null)}_isFocusWithinDrawer(){let e=this._doc.activeElement;return!!e&&this._elementRef.nativeElement.contains(e)}ngAfterViewInit(){this._isAttached=!0,this._position==="end"&&this._updatePositionInParent("end"),this._platform.isBrowser&&(this._focusTrap=this._focusTrapFactory.create(this._elementRef.nativeElement),this._updateFocusTrapState())}ngOnDestroy(){this._eventCleanups.forEach(e=>e()),this._focusTrap?.destroy(),this._anchor?.remove(),this._anchor=null,this._animationStarted.complete(),this._animationEnd.complete(),this._modeChanged.complete(),this._destroyed.next(),this._destroyed.complete()}open(e){return this.toggle(!0,e)}close(){return this.toggle(!1)}_closeViaBackdropClick(){return this._setOpen(!1,!0,"mouse")}toggle(e=!this.opened,i){e&&i&&(this._openedVia=i);let r=this._setOpen(e,!e&&this._isFocusWithinDrawer(),this._openedVia||"program");return e||(this._openedVia=null),r}_setOpen(e,i,r){return e===this.opened?Promise.resolve(e?"open":"close"):(this._opened.set(e),this._container?._transitionsEnabled?(this._setIsAnimating(!0),setTimeout(()=>this._animationStarted.next())):setTimeout(()=>{this._animationStarted.next(),this._animationEnd.next()}),this._elementRef.nativeElement.classList.toggle("mat-drawer-opened",e),!e&&i&&this._restoreFocus(r),this._changeDetectorRef.markForCheck(),this._updateFocusTrapState(),new Promise(o=>{this.openedChange.pipe(Qn(1)).subscribe(a=>o(a?"open":"close"))}))}_setIsAnimating(e){this._elementRef.nativeElement.classList.toggle("mat-drawer-animating",e)}_getWidth(){return this._elementRef.nativeElement.offsetWidth||0}_updateFocusTrapState(){this._focusTrap&&(this._focusTrap.enabled=this.opened&&!!this._container?._isShowingBackdrop())}_updatePositionInParent(e){if(!this._platform.isBrowser)return;let i=this._elementRef.nativeElement,r=i.parentNode;e==="end"?(this._anchor||(this._anchor=this._doc.createComment("mat-drawer-anchor"),r.insertBefore(this._anchor,i)),r.appendChild(i)):this._anchor&&this._anchor.parentNode.insertBefore(i,this._anchor)}_handleTransitionEvent=e=>{let i=this._elementRef.nativeElement;e.target===i&&this._ngZone.run(()=>{e.type==="transitionend"&&this._setIsAnimating(!1),this._animationEnd.next(e)})};static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-drawer"]],viewQuery:function(i,r){if(i&1&&Ne(z1,5),i&2){let o;H(o=q())&&(r._content=o.first)}},hostAttrs:[1,"mat-drawer"],hostVars:12,hostBindings:function(i,r){i&2&&(le("align",null)("tabIndex",r.mode!=="side"?"-1":null),bi("visibility",!r._container&&!r.opened?"hidden":null),Q("mat-drawer-end",r.position==="end")("mat-drawer-over",r.mode==="over")("mat-drawer-push",r.mode==="push")("mat-drawer-side",r.mode==="side"))},inputs:{position:"position",mode:"mode",disableClose:"disableClose",autoFocus:"autoFocus",opened:"opened"},outputs:{openedChange:"openedChange",_openedStream:"opened",openedStart:"openedStart",_closedStream:"closed",closedStart:"closedStart",onPositionChanged:"positionChanged"},exportAs:["matDrawer"],ngContentSelectors:Nl,decls:3,vars:0,consts:[["content",""],["cdkScrollable","",1,"mat-drawer-inner-container"]],template:function(i,r){i&1&&(ne(),g(0,"div",1,0),B(2),f())},dependencies:[fr],encapsulation:2,changeDetection:0})}return n})(),ku=(()=>{class n{_dir=m(wt,{optional:!0});_element=m(Y);_ngZone=m(K);_changeDetectorRef=m(Ue);_animationDisabled=He();_transitionsEnabled=!1;_allDrawers;_drawers=new In;_content;_userContent;get start(){return this._start}get end(){return this._end}get autosize(){return this._autosize}set autosize(e){this._autosize=kt(e)}_autosize=m(Z1);get hasBackdrop(){return this._drawerHasBackdrop(this._start)||this._drawerHasBackdrop(this._end)}set hasBackdrop(e){this._backdropOverride=e==null?null:kt(e)}_backdropOverride=null;backdropClick=new Ee;_start=null;_end=null;_left=null;_right=null;_destroyed=new z;_doCheckSubject=new z;_contentMargins={left:null,right:null};_contentMarginChanges=new z;get scrollable(){return this._userContent||this._content}_injector=m(te);constructor(){let e=m(Ce),i=m(Ln);this._dir?.change.pipe(De(this._destroyed)).subscribe(()=>{this._validateDrawers(),this.updateContentMargins()}),i.change().pipe(De(this._destroyed)).subscribe(()=>this.updateContentMargins()),!this._animationDisabled&&e.isBrowser&&this._ngZone.runOutsideAngular(()=>{setTimeout(()=>{this._element.nativeElement.classList.add("mat-drawer-transition"),this._transitionsEnabled=!0},200)})}ngAfterContentInit(){this._allDrawers.changes.pipe(ct(this._allDrawers),De(this._destroyed)).subscribe(e=>{this._drawers.reset(e.filter(i=>!i._container||i._container===this)),this._drawers.notifyOnChanges()}),this._drawers.changes.pipe(ct(null)).subscribe(()=>{this._validateDrawers(),this._drawers.forEach(e=>{this._watchDrawerToggle(e),this._watchDrawerPosition(e),this._watchDrawerMode(e)}),(!this._drawers.length||this._isDrawerOpen(this._start)||this._isDrawerOpen(this._end))&&this.updateContentMargins(),this._changeDetectorRef.markForCheck()}),this._ngZone.runOutsideAngular(()=>{this._doCheckSubject.pipe(Yn(10),De(this._destroyed)).subscribe(()=>this.updateContentMargins())})}ngOnDestroy(){this._contentMarginChanges.complete(),this._doCheckSubject.complete(),this._drawers.destroy(),this._destroyed.next(),this._destroyed.complete()}open(){this._drawers.forEach(e=>e.open())}close(){this._drawers.forEach(e=>e.close())}updateContentMargins(){let e=0,i=0;if(this._left&&this._left.opened){if(this._left.mode=="side")e+=this._left._getWidth();else if(this._left.mode=="push"){let r=this._left._getWidth();e+=r,i-=r}}if(this._right&&this._right.opened){if(this._right.mode=="side")i+=this._right._getWidth();else if(this._right.mode=="push"){let r=this._right._getWidth();i+=r,e-=r}}e=e||null,i=i||null,(e!==this._contentMargins.left||i!==this._contentMargins.right)&&(this._contentMargins={left:e,right:i},this._ngZone.run(()=>this._contentMarginChanges.next(this._contentMargins)))}ngDoCheck(){this._autosize&&this._isPushed()&&this._ngZone.runOutsideAngular(()=>this._doCheckSubject.next())}_watchDrawerToggle(e){e._animationStarted.pipe(De(this._drawers.changes)).subscribe(()=>{this.updateContentMargins(),this._changeDetectorRef.markForCheck()}),e.mode!=="side"&&e.openedChange.pipe(De(this._drawers.changes)).subscribe(()=>this._setContainerClass(e.opened))}_watchDrawerPosition(e){e.onPositionChanged.pipe(De(this._drawers.changes)).subscribe(()=>{mt({read:()=>this._validateDrawers()},{injector:this._injector})})}_watchDrawerMode(e){e._modeChanged.pipe(De(_t(this._drawers.changes,this._destroyed))).subscribe(()=>{this.updateContentMargins(),this._changeDetectorRef.markForCheck()})}_setContainerClass(e){let i=this._element.nativeElement.classList,r="mat-drawer-container-has-open";e?i.add(r):i.remove(r)}_validateDrawers(){this._start=this._end=null,this._drawers.forEach(e=>{e.position=="end"?(this._end!=null,this._end=e):(this._start!=null,this._start=e)}),this._right=this._left=null,this._dir&&this._dir.value==="rtl"?(this._left=this._end,this._right=this._start):(this._left=this._start,this._right=this._end)}_isPushed(){return this._isDrawerOpen(this._start)&&this._start.mode!="over"||this._isDrawerOpen(this._end)&&this._end.mode!="over"}_onBackdropClicked(){this.backdropClick.emit(),this._closeModalDrawersViaBackdrop()}_closeModalDrawersViaBackdrop(){[this._start,this._end].filter(e=>e&&!e.disableClose&&this._drawerHasBackdrop(e)).forEach(e=>e._closeViaBackdropClick())}_isShowingBackdrop(){return this._isDrawerOpen(this._start)&&this._drawerHasBackdrop(this._start)||this._isDrawerOpen(this._end)&&this._drawerHasBackdrop(this._end)}_isDrawerOpen(e){return e!=null&&e.opened}_drawerHasBackdrop(e){return this._backdropOverride==null?!!e&&e.mode!=="side":this._backdropOverride}static \u0275fac=function(i){return new(i||n)};static \u0275cmp=I({type:n,selectors:[["mat-drawer-container"]],contentQueries:function(i,r,o){if(i&1&&Xe(o,Pl,5)(o,Cu,5),i&2){let a;H(a=q())&&(r._content=a.first),H(a=q())&&(r._allDrawers=a)}},viewQuery:function(i,r){if(i&1&&Ne(Pl,5),i&2){let o;H(o=q())&&(r._userContent=o.first)}},hostAttrs:[1,"mat-drawer-container"],hostVars:2,hostBindings:function(i,r){i&2&&Q("mat-drawer-container-explicit-backdrop",r._backdropOverride)},inputs:{autosize:"autosize",hasBackdrop:"hasBackdrop"},outputs:{backdropClick:"backdropClick"},exportAs:["matDrawerContainer"],features:[ze([{provide:Du,useExisting:n}])],ngContentSelectors:q1,decls:4,vars:2,consts:[[1,"mat-drawer-backdrop",3,"mat-drawer-shown"],[1,"mat-drawer-backdrop",3,"click"]],template:function(i,r){i&1&&(ne(H1),k(0,U1,1,2,"div",0),B(1),B(2,1),k(3,G1,2,0,"mat-drawer-content")),i&2&&(D(r.hasBackdrop?0:-1),p(3),D(r._content?-1:3))},dependencies:[Pl],styles:[`.mat-drawer-container {
  position: relative;
  z-index: 1;
  color: var(--mat-sidenav-content-text-color, var(--mat-sys-on-background));
  background-color: var(--mat-sidenav-content-background-color, var(--mat-sys-background));
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
  background-color: var(--mat-sidenav-scrim-color, color-mix(in srgb, var(--mat-sys-neutral-variant20) 40%, transparent));
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
  color: var(--mat-sidenav-container-text-color, var(--mat-sys-on-surface-variant));
  box-shadow: var(--mat-sidenav-container-elevation-shadow, none);
  background-color: var(--mat-sidenav-container-background-color, var(--mat-sys-surface));
  border-top-right-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-bottom-right-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  width: var(--mat-sidenav-container-width, 360px);
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
  border-top-left-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-bottom-left-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
}
[dir=rtl] .mat-drawer {
  border-top-left-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-bottom-left-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-top-right-radius: 0;
  border-bottom-right-radius: 0;
  transform: translate3d(100%, 0, 0);
}
[dir=rtl] .mat-drawer.mat-drawer-end {
  border-top-right-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
  border-bottom-right-radius: var(--mat-sidenav-container-shape, var(--mat-sys-corner-large));
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
  border-right-color: var(--mat-sidenav-container-divider-color, transparent);
  border-right-width: 1px;
  border-right-style: solid;
}
.mat-drawer-side.mat-drawer-end {
  border-left-color: var(--mat-sidenav-container-divider-color, transparent);
  border-left-width: 1px;
  border-left-style: solid;
  border-right: none;
}
[dir=rtl] .mat-drawer-side {
  border-left-color: var(--mat-sidenav-container-divider-color, transparent);
  border-left-width: 1px;
  border-left-style: solid;
  border-right: none;
}
[dir=rtl] .mat-drawer-side.mat-drawer-end {
  border-right-color: var(--mat-sidenav-container-divider-color, transparent);
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
`],encapsulation:2,changeDetection:0})}return n})(),Fl=(()=>{class n extends Pl{static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(n)))(r||n)}})();static \u0275cmp=I({type:n,selectors:[["mat-sidenav-content"]],hostAttrs:[1,"mat-drawer-content","mat-sidenav-content"],features:[ze([{provide:fr,useExisting:n}]),Te],ngContentSelectors:Nl,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},encapsulation:2,changeDetection:0})}return n})(),Eu=(()=>{class n extends Cu{get fixedInViewport(){return this._fixedInViewport}set fixedInViewport(e){this._fixedInViewport=kt(e)}_fixedInViewport=!1;get fixedTopGap(){return this._fixedTopGap}set fixedTopGap(e){this._fixedTopGap=Fn(e)}_fixedTopGap=0;get fixedBottomGap(){return this._fixedBottomGap}set fixedBottomGap(e){this._fixedBottomGap=Fn(e)}_fixedBottomGap=0;static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(n)))(r||n)}})();static \u0275cmp=I({type:n,selectors:[["mat-sidenav"]],hostAttrs:[1,"mat-drawer","mat-sidenav"],hostVars:16,hostBindings:function(i,r){i&2&&(le("tabIndex",r.mode!=="side"?"-1":null)("align",null),bi("top",r.fixedInViewport?r.fixedTopGap:null,"px")("bottom",r.fixedInViewport?r.fixedBottomGap:null,"px"),Q("mat-drawer-end",r.position==="end")("mat-drawer-over",r.mode==="over")("mat-drawer-push",r.mode==="push")("mat-drawer-side",r.mode==="side")("mat-sidenav-fixed",r.fixedInViewport))},inputs:{fixedInViewport:"fixedInViewport",fixedTopGap:"fixedTopGap",fixedBottomGap:"fixedBottomGap"},exportAs:["matSidenav"],features:[ze([{provide:Cu,useExisting:n}]),Te],ngContentSelectors:Nl,decls:3,vars:0,consts:[["content",""],["cdkScrollable","",1,"mat-drawer-inner-container"]],template:function(i,r){i&1&&(ne(),g(0,"div",1,0),B(2),f())},dependencies:[fr],encapsulation:2,changeDetection:0})}return n})(),Ub=(()=>{class n extends ku{_allDrawers=void 0;_content=void 0;static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(n)))(r||n)}})();static \u0275cmp=I({type:n,selectors:[["mat-sidenav-container"]],contentQueries:function(i,r,o){if(i&1&&Xe(o,Fl,5)(o,Eu,5),i&2){let a;H(a=q())&&(r._content=a.first),H(a=q())&&(r._allDrawers=a)}},hostAttrs:[1,"mat-drawer-container","mat-sidenav-container"],hostVars:2,hostBindings:function(i,r){i&2&&Q("mat-drawer-container-explicit-backdrop",r._backdropOverride)},exportAs:["matSidenavContainer"],features:[ze([{provide:Du,useExisting:n},{provide:ku,useExisting:n}]),Te],ngContentSelectors:K1,decls:4,vars:2,consts:[[1,"mat-drawer-backdrop",3,"mat-drawer-shown"],[1,"mat-drawer-backdrop",3,"click"]],template:function(i,r){i&1&&(ne(W1),k(0,Y1,1,2,"div",0),B(1),B(2,1),k(3,Q1,2,0,"mat-sidenav-content")),i&2&&(D(r.hasBackdrop?0:-1),p(3),D(r._content?-1:3))},dependencies:[Fl],styles:[X1],encapsulation:2,changeDetection:0})}return n})(),xa=(()=>{class n{static \u0275fac=function(i){return new(i||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[Nn,ie,Nn]})}return n})();var eM=[ha,$n,Ct,fa,Un,du,uu,ga,ya,_a,yd,ba,va,dn,xa],Ll=class n{static \u0275fac=function(e){return new(e||n)};static \u0275mod=V({type:n});static \u0275inj=$({imports:[eM,ha,$n,Ct,fa,Un,du,uu,ga,ya,_a,yd,ba,va,dn,xa]})};var Bl=class n{data=yt.required({transform:t=>"```json\n"+JSON.stringify(t,null,2)+"\n```"});static \u0275fac=function(e){return new(e||n)};static \u0275cmp=I({type:n,selectors:[["app-json"]],inputs:{data:[1,"data"]},decls:1,vars:1,consts:[[3,"data"]],template:function(e,i){e&1&&P(0,"markdown",0),e&2&&R("data",i.data())},dependencies:[bn,_n],encapsulation:2})};var $l={},Gr=Symbol(),qi=Symbol(),Wb=n=>typeof n=="string"?ue[n]:n,ue={plain:$l,plaintext:$l,text:$l,txt:$l},ka=(n,t)=>(t[qi]||Su)(n,t),Su=(n,t)=>{for(var e=[n],i,r=[],o=0;i=Wb(t[Gr]);)delete t[Gr],Object.assign(t,i);for(Yb(n,t,e,0);r[o++]=e[0],e=e[1];);return r},tM=(n,t,e)=>n.replace(/&/g,"&amp;").replace(t,e),Gb="</span>",jl="",wa="",Mu=n=>{for(var t="",e,i=0;e=n[i++];)t+=Kb(e);return t},Kb=n=>{if(n instanceof Ca){var{type:t,alias:e,content:i}=n,r=jl,o=wa,a=`<span class="token ${t+(e?" "+e:"")+(t=="keyword"&&typeof i=="string"?" keyword-"+i:"")}">`;wa+=Gb,jl+=a;var s=Kb(i);return jl=r,wa=o,a+s+Gb}return typeof n!="string"?Mu(n):(n=tM(n,/</g,"&lt;"),wa&&n.includes(`
`)?n.replace(/\n/g,wa+`
`+jl):n)};var Yb=(n,t,e,i,r)=>{for(var o in t)if(t[o])for(var a=0,s=t[o],l,d=Array.isArray(s)?s:[s];l=d[a];a++){if(r&&r[0]==o&&r[1]==a)return;for(var c=l.pattern||l,u=Wb(l.inside),h=l.lookbehind,y=c.global,_=l.alias,b=e,v=i;b&&(!r||v<r[2]);v+=b[0].length,b=b[1]){var x=b[0],A=0,T;if(!(x instanceof Ca)){if(c.lastIndex=y?v:0,T=c.exec(y?n:x),!T&&y)break;if(T&&T[0]){var L=h&&T[1]?T[1].length:0,N=T.index+L,ve=T[0].slice(L),ke=N+ve.length,Pt,s;if(y){for(;s=v+b[0].length,N>=s;b=b[1],v=s);if(b[0]instanceof Ca)continue;for(Pt=b,s=v;(s+=Pt[0].length)<ke;Pt=Pt[1],A++);x=n.slice(v,s),N-=v,ke-=v}for(var Ft=x.slice(ke),Nt=v+x.length,Sn=new Ca(o,u?ka(ve,u):ve,ve,_),it=b,Wn=0,un;it=it[1],Wn++<A;);Ft&&(!it||it[0]instanceof Ca?it=[Ft,it]:it[0]=Ft+it[0]),v+=N,b[0]=N?x.slice(0,N):Sn,N?b=b[1]=[Sn,it]:b[1]=it,A&&(Yb(n,t,b,v,un=[o,a,Nt]),Nt=un[2]),r&&Nt>r[2]&&(r[2]=Nt)}}}}};function Ca(n,t,e,i){this.type=n,this.content=t,this.alias=i,this.length=e.length}var Ea=(n,t,...e)=>{let i,r=[],o,a="",s,l=!1,d=!0,c=[],u,h=0,y=iM(),_=y.firstChild,b=_.children,v=b[0],x=v.firstChild,A={language:"text",value:a},T=new Set(e),L={},N=se=>{Object.assign(A,se);let de=a!=(a=se.value??a),Lt=i!=(i=A.language);u=!!A.readOnly,y.style.tabSize=A.tabSize||2,x.inputMode=u?"none":"",x.setAttribute("aria-readonly",u),Pt(),ke(),de&&(l||x.remove(),x.value=a,x.selectionEnd=0,l||v.prepend(x)),(de||Lt)&&ve()},ve=()=>{c=ka(a=x.value,ue[i]||{}),it("tokenize",c,i,a);let se=Mu(c).split(`
`),de=0,Lt=h,gi=h=se.length;for(;se[de]==r[de]&&de<gi;)++de;for(;gi&&se[--gi]==r[--Lt];);if(de==gi&&de==Lt)b[de+1].innerHTML=se[de]+`
`;else{let Wu=Lt<de?Lt:de-1,Jr=Wu,ic="";for(;Jr<gi;)ic+=`<div class=pce-line aria-hidden=true>${se[++Jr]}
</div>`;for(Jr=gi<de?gi:de-1;Jr<Lt;Jr++)b[de+1].remove();ic&&b[Wu+1].insertAdjacentHTML("afterend",ic),y.style.setProperty("--number-width",(0|Math.log10(h))+1+".001ch")}it("update",a),Wn(!0),d&&setTimeout(setTimeout,0,()=>d=!0),r=se,d=!1},ke=se=>{(se||T).forEach(de=>{typeof de=="object"?(de.update(un,A),se&&T.add(de)):(de(un,A),se||T.delete(de))})},Pt=([se,de]=Ft())=>{y.className=`prism-code-editor language-${i}${A.lineNumbers==!1?"":" show-line-numbers"} pce-${A.wordWrap?"":"no"}wrap${A.rtl?" pce-rtl":""} pce-${se<de?"has":"no"}-selection${l?" pce-focus":""}${u?" pce-readonly":""}${A.class?" "+A.class:""}`},Ft=()=>[x.selectionStart,x.selectionEnd,x.selectionDirection],Nt={Escape(){x.blur()}},Sn={},it=(se,...de)=>{L[se]?.forEach(Lt=>Lt.apply(un,de)),A["on"+se[0].toUpperCase()+se.slice(1)]?.(...de,un)},Wn=se=>{if(se||d){let de=Ft(),Lt=b[s=Vl(a,0,de[de[2]<"f"?0:1])];Lt!=o&&(o?.classList.remove("active-line"),Lt.classList.add("active-line"),o=Lt),Pt(de),it("selectionChange",de,a)}},un={container:y,wrapper:_,lines:b,textarea:x,get activeLine(){return s},get value(){return a},options:A,get focused(){return l},get tokens(){return c},inputCommandMap:Sn,keyCommandMap:Nt,extensions:{},setOptions:N,update:ve,getSelection:Ft,addExtensions(...se){ke(se)},on:(se,de)=>((L[se]||=new Set).add(de),()=>L[se].delete(de)),remove(){y.remove()}};return $t(x,"keydown",se=>{Nt[se.key]?.(se,Ft(),a)&&Ui(se)}),$t(x,"beforeinput",se=>{(u||se.inputType=="insertText"&&Sn[se.data]?.(se,Ft(),a))&&Ui(se)}),$t(x,"input",ve),$t(x,"blur",()=>{Da=null,l=!1,Pt()}),$t(x,"focus",()=>{Da=Wn,l=!0,Pt()}),$t(x,"selectionchange",se=>{Wn(),Ui(se)}),nM(n)?.append(y),t&&N(t),un};var En="u">typeof window?document:null,Au=En?.createElement("div"),Iu=(n,t)=>(Au&&(Au.innerHTML=n,t=Au.firstChild),()=>t.cloneNode(!0)),$t=(n,t,e,i)=>n.addEventListener(t,e,i),nM=n=>typeof n=="string"?En.querySelector(n):n,Vl=(n,t=0,e=1/0)=>{let i=1;for(;(t=n.indexOf(`
`,t)+1)&&t<=e;i++);return i};var iM=Iu("<div><div class=pce-wrapper><div class=pce-overlays><textarea class=pce-textarea spellcheck=false autocapitalize=off autocomplete=off>"),Ui=n=>{n.preventDefault(),n.stopImmediatePropagation()};var Da;En&&$t(En,"selectionchange",()=>Da?.());globalThis.Prism={highlightAllUnder:n=>{n.querySelectorAll("pre code").forEach(t=>{let e=Array.from(t.classList).find(r=>r.startsWith("language"))||"",i=t.textContent||"";t.textContent="",Ea(t.parentElement,{value:i.trimEnd(),language:e.replace("language-",""),lineNumbers:!1,wordWrap:!0,readOnly:!0})})}};function oM(n,t){if(n&1&&(qe(0,"span"),w(1),Qe()),n&2){let e=C();p(),ao(" ",e.lowerBoundInclusive()?">=":">"," ",e.lowerBound()," ")}}function aM(n,t){if(n&1&&(qe(0,"span"),w(1),Qe()),n&2){let e=C();p(),ao(" ",e.upperBoundInclusive()?"<=":"<"," ",e.upperBound()," ")}}function sM(n,t){if(n&1&&(qe(0,"span"),w(1),Qe()),n&2){let e=C();p(),_p("",e.lowerBoundInclusive()?"[":"("," ",e.lowerBound()," .. ",e.upperBound()," ",e.upperBoundInclusive()?"]":")"," ")}}var zl=class n{lowerBound=yt();upperBound=yt();lowerBoundInclusive=yt(!0,{transform:t=>t===!0||t=="true"});upperBoundInclusive=yt(!0,{transform:t=>t===!0||t=="true"});static \u0275fac=function(e){return new(e||n)};static \u0275cmp=I({type:n,selectors:[["app-schema-range"]],inputs:{lowerBound:[1,"lowerBound"],upperBound:[1,"upperBound"],lowerBoundInclusive:[1,"lowerBoundInclusive"],upperBoundInclusive:[1,"upperBoundInclusive"]},decls:4,vars:3,template:function(e,i){e&1&&(qe(0,"span"),k(1,oM,2,2,"span"),k(2,aM,2,2,"span"),k(3,sM,2,4,"span"),Qe()),e&2&&(p(),D(i.lowerBound()!=null&&i.upperBound()==null?1:-1),p(),D(i.lowerBound()==null&&i.upperBound()!=null?2:-1),p(),D(i.lowerBound()!=null&&i.upperBound()!=null?3:-1))},encapsulation:2})};var Qb=()=>({}),Xb=n=>({value:n});function lM(n,t){if(n&1&&lc(0,2),n&2){let e=C(),i=zt(4);R("ngTemplateOutlet",i)("ngTemplateOutletContext",uc(2,Xb,e.schema()))}}function cM(n,t){n&1&&(g(0,"span",7),w(1,"*"),f())}function dM(n,t){n&1&&(g(0,"span",8),w(1,"\xA0(deprecated)"),f())}function mM(n,t){if(n&1&&(g(0,"mat-list-item",3)(1,"mat-icon",4),w(2,"arrow_right"),f(),g(3,"span",5)(4,"b",6),w(5),f(),k(6,cM,2,0,"span",7),k(7,dM,2,0,"span",8),f(),lc(8,9),f()),n&2){let e,i=t.$implicit,r=C(2),o=zt(4);p(5),Le(i.key),p(),D((e=r.schema().required)!=null&&e.includes(i.key)?6:-1),p(),D(i.value.deprecated?7:-1),p(),R("ngTemplateOutlet",o)("ngTemplateOutletContext",uc(5,Xb,i.value))}}function uM(n,t){if(n&1&&(g(0,"mat-list"),je(1,mM,9,7,"mat-list-item",3,$e),qa(3,"keyvalue"),f()),n&2){let e=C();p(),Ve(Ua(3,0,e.schema().properties||mc(2,Qb)))}}function pM(n,t){if(n&1&&(g(0,"span",10),w(1),f()),n&2){let e=C(2).value;p(),J(" ",e.type.join(", ")," ")}}function hM(n,t){if(n&1&&w(0),n&2){let e=C(2).value;J(" ",e.type," ")}}function fM(n,t){if(n&1&&(g(0,"span",16),w(1),f()),n&2){let e=C(2).value;p(),J("(",e.format,")")}}function gM(n,t){if(n&1&&(g(0,"span",10),k(1,pM,2,1,"span",10)(2,hM,1,1),k(3,fM,2,1,"span",16),f()),n&2){let e=C().value,i=C();p(),D(i.Array.isArray(e.type)?1:2),p(2),D(e.format?3:-1)}}function _M(n,t){if(n&1&&(g(0,"span",11)(1,"mat-chip-set")(2,"a",17)(3,"mat-chip"),P(4,"mat-icon",18),w(5),f()()()()),n&2){let e=C().value;p(2),R("href",e.refAnchorUrl,Ze),p(3),J(" ",e.refTitle," ")}}function bM(n,t){if(n&1&&(g(0,"span",10),w(1," array "),g(2,"mat-chip-set")(3,"a",17)(4,"mat-chip"),P(5,"mat-icon",18),w(6),f()()()()),n&2){let e=C().value;p(3),R("href",e.items.refAnchorUrl,Ze),p(3),J(" ",e.items.refTitle," ")}}function vM(n,t){if(n&1&&(g(0,"span",10),w(1),f()),n&2){let e=C().value;p(),J(" ",e.items.type,"[] ")}}function yM(n,t){if(n&1&&(g(0,"div",12),P(1,"markdown",19),f()),n&2){let e=C().value;p(),R("data",e.description)}}function xM(n,t){if(n&1&&(g(0,"div",6),w(1),f()),n&2){let e=t.$implicit;p(),J(" ",e," ")}}function wM(n,t){if(n&1&&(g(0,"div",13)(1,"span",20)(2,"i"),w(3,"Example:"),f()(),w(4," \xA0 "),g(5,"span",21),je(6,xM,2,1,"div",6,$e),f()()),n&2){let e=C().value;p(6),Ve(e.example.value.split(`
`))}}function CM(n,t){if(n&1&&(g(0,"span",22),w(1),f()),n&2){let e=t.$implicit;p(),Le(e||"null")}}function kM(n,t){if(n&1&&(g(0,"div",13)(1,"span",20)(2,"i"),w(3,"Allowed values:"),f()(),w(4," \xA0 "),je(5,CM,2,1,"span",22,$e),f()),n&2){let e=C().value;p(5),Ve(e.enum)}}function DM(n,t){if(n&1&&(g(0,"span",15),P(1,"app-schema-range",23),w(2," items "),f()),n&2){let e=C().value;p(),R("lowerBound",e.minItems)("upperBound",e.maxItems)}}function EM(n,t){if(n&1&&(g(0,"span",15),w(1),f()),n&2){let e=C().value;p(),J(" Unique items: ",e.uniqueItems?"yes":"no"," ")}}function SM(n,t){if(n&1&&(g(0,"span",15),w(1," pattern: "),g(2,"span",6),w(3),f()()),n&2){let e=C().value;p(3),J(" ",e.pattern," ")}}function MM(n,t){if(n&1&&(g(0,"span",15),P(1,"app-schema-range",23),w(2," length "),f()),n&2){let e=C().value;p(),R("lowerBound",e.minLength)("upperBound",e.maxLength)}}function AM(n,t){if(n&1&&(g(0,"span",15),P(1,"app-schema-range",24),w(2," value range "),f()),n&2){let e=C().value;p(),R("lowerBound",e.minimum)("upperBound",e.maximum)("lowerBoundInclusive",!e.exclusiveMinimum)("upperBoundInclusive",!e.exclusiveMaximum)}}function IM(n,t){if(n&1&&(g(0,"span",15),w(1),f()),n&2){let e=C().value;p(),J(" Multiple of ",e.multipleOf," ")}}function TM(n,t){if(n&1&&(k(0,gM,4,2,"span",10),k(1,_M,6,2,"span",11),k(2,bM,7,2,"span",10),k(3,vM,2,1,"span",10),k(4,yM,2,1,"div",12),k(5,wM,8,0,"div",13),k(6,kM,7,0,"div",13),g(7,"span",14),k(8,DM,3,2,"span",15),k(9,EM,2,1,"span",15),k(10,SM,4,1,"span",15),k(11,MM,3,2,"span",15),k(12,AM,3,4,"span",15),k(13,IM,2,1,"span",15),f()),n&2){let e=t.value;D((e==null?null:e.type)!="array"?0:-1),p(),D(e.refTitle?1:-1),p(),D((e==null?null:e.type)=="array"&&(!(e==null||e.items==null)&&e.items.refTitle)?2:-1),p(),D((e==null?null:e.type)=="array"&&!(!(e==null||e.items==null)&&e.items.refTitle)?3:-1),p(),D((e.description==null?null:e.description.length)>0?4:-1),p(),D(e.example?5:-1),p(),D(e.enum&&e.enum.length>0?6:-1),p(2),D(e.minItems!=null||e.maxItems!=null?8:-1),p(),D(e.uniqueItems!=null?9:-1),p(),D(e.pattern?10:-1),p(),D(e.minLength!=null||e.maxLength!=null?11:-1),p(),D(e.minimum!=null||e.maximum!=null?12:-1),p(),D(e.multipleOf!=null?13:-1)}}var Wr=class n{schema=yt.required();Array=Array;Object=Object;static \u0275fac=function(e){return new(e||n)};static \u0275cmp=I({type:n,selectors:[["app-schema"]],inputs:{schema:[1,"schema"]},decls:5,vars:3,consts:[["valueContent",""],[1,"schema"],[3,"ngTemplateOutlet","ngTemplateOutletContext"],["lines","99"],["matListItemIcon",""],["matListItemTitle","",1,"key"],[1,"text-console"],[1,"required"],[1,"deprecated"],["matListItemLine","",3,"ngTemplateOutlet","ngTemplateOutletContext"],[1,"type","text-console"],[1,"type"],[1,"description"],[1,"flex"],[1,"flex","flex-wrap"],[1,"attribute"],[1,"format"],[3,"href"],["matChipAvatar","","fontIcon","schema"],[3,"data"],[1,"property-title"],[1,"value-box"],[1,"text-console","value-box"],["lowerBoundInclusive","true","upperBoundInclusive","true",3,"lowerBound","upperBound"],[3,"lowerBound","upperBound","lowerBoundInclusive","upperBoundInclusive"]],template:function(e,i){e&1&&(g(0,"div",1),k(1,lM,1,4,"ng-container",2),k(2,uM,4,3,"mat-list"),f(),Vt(3,TM,14,13,"ng-template",null,0,mo)),e&2&&(p(),D(i.schema().type!=="object"?1:-1),p(),D(i.Object.keys(i.schema().properties||mc(2,Qb)).length>0?2:-1))},dependencies:[va,ya,Hb,qb,wu,xu,yu,dn,Gn,fi,Ur,Ct,Mt,hn,go,bn,_n,zl,_o],styles:[".required[_ngcontent-%COMP%], .deprecated[_ngcontent-%COMP%]{color:red}.description[_ngcontent-%COMP%]{overflow:auto}.property-title[_ngcontent-%COMP%]{min-width:fit-content}.attribute[_ngcontent-%COMP%]{background-color:#805ad5;color:#fff;margin:0 .2em .2em 0;padding:.1em .2em;border-radius:4px}.value-box[_ngcontent-%COMP%]{margin:1px 6px 1px 0;background-color:#eee;border:1px dashed grey;color:#696969;border-radius:4px;padding:0 2px;overflow-wrap:anywhere;white-space:normal;width:fit-content}"]})};var Xt=class n{constructor(t){this.el=t}static \u0275fac=function(e){return new(e||n)(Se(Y))};static \u0275dir=G({type:n,selectors:[["","appNavigationTarget",""]]})};function OM(n,t){if(n&1&&(g(0,"div",6)(1,"span"),w(2,"Description"),f(),P(3,"markdown",7),f()),n&2){let e=C().$implicit;p(3),R("data",e.description)}}function RM(n,t){if(n&1&&(g(0,"a",9)(1,"mat-chip")(2,"mat-icon",10),w(3),f(),w(4),f()()),n&2){let e=t.$implicit;R("href",e.anchorUrl,Ze),p(3),Le(e.type=="schema"?"schema":"swap_vert"),p(),J(" ",e.name," ")}}function PM(n,t){if(n&1&&(g(0,"div",3)(1,"span"),w(2,"Used by"),f(),g(3,"mat-chip-set"),je(4,RM,5,3,"a",9,$e),f()()),n&2){let e=C().$implicit;p(4),Ve(e.usedBy)}}function FM(n,t){n&1&&P(0,"br")}function NM(n,t){if(n&1&&(g(0,"article",0)(1,"mat-card")(2,"mat-card-header",1)(3,"mat-card-title"),w(4),f()(),g(5,"mat-card-content")(6,"div",2)(7,"div",3)(8,"span"),w(9,"Name"),f(),g(10,"span",4),w(11),f()(),g(12,"div",3)(13,"span"),w(14,"Type"),f(),g(15,"span")(16,"div",5),w(17),f()()(),k(18,OM,4,1,"div",6),k(19,PM,6,0,"div",3),f(),g(20,"h6"),w(21,"Example"),f(),g(22,"div"),P(23,"app-json",7),f(),g(24,"h6"),w(25,"Properties"),f(),P(26,"app-schema",8),f()(),k(27,FM,1,0,"br"),f()),n&2){let e=t.$implicit,i=t.$index,r=t.$count;R("id",e.anchorIdentifier),p(4),J(" ",e.title," "),p(7),Le(e.name),p(6),Le(e.type),p(),D(e.description?18:-1),p(),D(e.usedBy.length>0?19:-1),p(4),R("data",e.example==null?null:e.example.rawValue),p(3),R("schema",e),p(),D(i!==r-1?27:-1)}}var Hl=class n{constructor(t){this.asyncApiService=t}schemas=X([]);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(t=>this.schemas.set([...t.components.schemas.values()]))}static \u0275fac=function(e){return new(e||n)(Se(nt))};static \u0275cmp=I({type:n,selectors:[["app-schemas"]],decls:4,vars:0,consts:[["appNavigationTarget","",3,"id"],[1,"flex","space-between","align-items-baseline"],[1,"table","margin-vertical-1em"],[1,"table-row"],[1,"text-console"],[1,"type-badge"],[1,"table-row","description"],[3,"data"],[3,"schema"],[3,"href"],["matChipAvatar",""]],template:function(e,i){e&1&&(g(0,"h2"),w(1,"Schemas"),f(),je(2,NM,28,9,"article",0,$e)),e&2&&(p(2),Ve(i.schemas()))},dependencies:[_n,Ct,Mt,Un,Vr,Hr,qr,zr,dn,Gn,fi,Ur,Bl,Wr,Xt],styles:[".table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:first-child{vertical-align:middle}.table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:last-child{word-break:break-word}.type-badge[_ngcontent-%COMP%]{display:inline;background-color:#e0e0e0;border-radius:4px;padding:4px;font-weight:400;font-size:small}"]})};var Sa=new qt("init"),Gi=new qt("");var Zb={asyncApiJson:{},contact:{},license:{},title:"",version:""};var Ma={ts_type:"object",title:"",name:"",anchorUrl:"",anchorIdentifier:"",usedBy:[]};function LM(n,t){if(n&1){let e=ot();g(0,"a",3),pe("click",function(){Ke(e);let r=C();return Ye(r.download())}),w(1,"AsyncAPI JSON"),f()}}function BM(n,t){if(n&1&&(g(0,"a",5),w(1),f()),n&2){C(2);let e=ir(6);R("href",e.url,Ze),p(),J(" ",e.name," ")}}function $M(n,t){if(n&1&&w(0),n&2){C(2);let e=ir(6);J(" ",e.name," ")}}function jM(n,t){if(n&1&&(g(0,"mat-chip"),P(1,"mat-icon",4),k(2,BM,2,2,"a",5)(3,$M,1,1),f()),n&2){C();let e=ir(6);p(2),D(e!=null&&e.url?2:3)}}function VM(n,t){if(n&1&&(g(0,"mat-chip"),P(1,"mat-icon",6),g(2,"a",5),w(3),f()()),n&2){C();let e=ir(8);p(2),R("href",e.url,Ze),p(),J(" ",e.url," ")}}function zM(n,t){if(n&1&&(g(0,"mat-chip"),P(1,"mat-icon",7),g(2,"a",5),w(3),f()()),n&2){C();let e=ir(10);p(2),R("href",e.href,Ze),p(),J(" ",e.name," ")}}function HM(n,t){if(n&1&&(g(0,"p"),P(1,"markdown",8),f()),n&2){let e=C();p(),R("data",e.info().description)}}var ql=class n{constructor(t){this.asyncApiService=t}asyncApiData=void 0;info=X(Zb);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(t=>{this.asyncApiData=t,this.info.set(t.info)})}download(){if(this.asyncApiData===void 0)return!1;let t=JSON.stringify(this.asyncApiData.info.asyncApiJson,null,2),e=new TextEncoder().encode(t),i=new Blob([e],{type:"application/json"}),r=window.URL.createObjectURL(i);return window.open(r),!1}static \u0275fac=function(e){return new(e||n)(Se(nt))};static \u0275cmp=I({type:n,selectors:[["app-info"]],decls:13,vars:10,consts:[[1,"info-chips"],["matChipAvatar","","fontIcon","download"],["href","javascript:void(0);"],["href","javascript:void(0);",3,"click"],["matChipAvatar","","fontIcon","attribution"],["target","_blank",3,"href"],["matChipAvatar","","fontIcon","link"],["matChipAvatar","","fontIcon","email"],[3,"data"]],template:function(e,i){if(e&1&&(g(0,"h1"),w(1),f(),g(2,"p",0)(3,"mat-chip"),P(4,"mat-icon",1),k(5,LM,2,0,"a",2),f(),za(6),k(7,jM,4,1,"mat-chip"),za(8),k(9,VM,4,2,"mat-chip"),za(10),k(11,zM,4,2,"mat-chip"),f(),k(12,HM,2,1,"p")),e&2){p(),ao(" ",i.info().title," ",i.info().version?"v"+i.info().version:"",`
`),p(4),D(i.info().asyncApiJson?5:-1),p();let r=Ha(i.info().license);p(),D(r!=null&&r.name?7:-1),p();let o=Ha(i.info().contact);p(),D(o.url?9:-1),p();let a=Ha(i.info().contact.email);p(),D(a?11:-1),p(),D(i.info().description?12:-1)}},dependencies:[dn,Gn,fi,Ct,Mt,bn,_n],styles:[".info-chips[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]{margin-inline-end:8px}"]})};function qM(n,t){if(n&1&&(g(0,"article",1)(1,"mat-card",2)(2,"mat-card-header")(3,"mat-card-title"),w(4),f(),g(5,"span",3)(6,"span",4),w(7),f()()(),g(8,"mat-card-content")(9,"table")(10,"tbody")(11,"tr")(12,"td"),w(13,"Host"),f(),g(14,"td",5),w(15),f()()()()()()()),n&2){let e=t.$implicit;R("id",e.value.anchorIdentifier),p(4),J(" ",e.key," "),p(3),J(" ",e.value.protocol," "),p(8),J(" ",e.value.host," ")}}var Ul=class n{constructor(t){this.asyncApiService=t}servers=X(new Map);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(t=>this.servers.set(t.servers))}static \u0275fac=function(e){return new(e||n)(Se(nt))};static \u0275cmp=I({type:n,selectors:[["app-servers"]],decls:6,vars:2,consts:[[1,"row"],["appNavigationTarget","",1,"width-6-of-12","width-12-of-12-s",3,"id"],["appearance","outlined"],[1,"flex","gap-16","padding-horizontal-1em","height-fix-content"],[1,"badge","protocol-badge"],[1,"text-console"]],template:function(e,i){e&1&&(g(0,"h2"),w(1,"Servers"),f(),g(2,"div",0),je(3,qM,16,4,"article",1,$e),qa(5,"keyvalue"),f()),e&2&&(p(3),Ve(Ua(5,0,i.servers())))},dependencies:[hn,Un,Vr,Hr,qr,zr,Xt,_o],styles:[".badge[_ngcontent-%COMP%]{border-radius:4px;padding:.3em;font-size:smaller;text-transform:uppercase}.protocol-badge[_ngcontent-%COMP%]{background-color:#347aeb;color:#fff}"]})};var Jb=(n,t,e)=>t.indexOf(n[0])+1||e&&t.indexOf(n[e])+1;var ev=(n=!0,t="()[]{}")=>{let e,i,r=[],o=c=>{c.extensions.matchBrackets=o,c.on("tokenize",l),n&&c.tokens[0]?c.update():l(c.tokens)},a=o.brackets=[],s=o.pairs=[],l=c=>{if(s.length=a.length=i=e=0,d(c,0),n)for(let u=0,h;h=a[u];){let y=h[0].alias;h[0].alias=(y?y+" ":"")+`bracket-${u++in s?"level-"+h[3]%12:"error"}`}},d=(c,u)=>{let h,y=0;for(;h=c[y++];){let _=h.length;if(typeof h!="string"){let b=h.content;if(Array.isArray(b))d(b,u);else if((h.alias||h.type)=="punctuation"){let v=Jb(b,t,_-1),x=v%2;if(v){if(a[e]=[h,u,u+_,i,b,!!x],x)r[i++]=[e,v+1];else for(let A=i,T;T=r[--A];)if(v==T[1]){s[s[e]=T[0]]=e,a[e][3]=i=A;break}e++}}}u+=_}};return o};var tv=(n,t)=>(t=n.indexOf(`
`,t))+1?t:n.length,Wi=(n,t,e,i)=>$t(n.textarea,t,e,i);var Dq=new Set("xml,rss,atom,jsx,tsx,xquery,xeora,xeoracube,actionscript".split(","));var Tu;var nv=(n,t,e=0,i=e,r=n.getSelection()[0])=>{let o=n.value,a=n.lines[Vl(o,0,r)],s=En.createTreeWalker(a,5),l=s.lastChild(),d=tv(o,r)+1-r-l.length;for(;-d<=i&&(l=s.previousNode());)if(!l.lastChild&&(d-=l.length||0,d<=e)){for(;l!=a;l=l.parentNode)if(l.matches?.(t))return l}};var Ou=(n,t,e=t,i)=>{let r=n.focused,o=n.textarea,a;r||($t(o,"focus",s=>{a=s.relatedTarget},{once:!0}),o.focus()),o.setSelectionRange(t,e,i),Da(!(!r&&(a?a.focus():o.blur())))},iv=En?navigator.userAgent:"",Ru=En?/Mac|iPhone|iP[ao]d/.test(navigator.platform):!1,UM=/Chrome\//.test(iv),Eq=!UM&&/AppleWebKit\//.test(iv),rv=n=>n.altKey+n.ctrlKey*2+n.metaKey*4+n.shiftKey*8,ov=(n,t)=>n.lines[0].append(t);var av=()=>n=>{let t,e=[],i=()=>{let o=n.extensions.matchBrackets,[a,s]=n.getSelection();if(o){let l=o.brackets,d=o.pairs,c,u;if(n.focused&&a==s){for(let h=0,y;y=l[++h];)if(!y[5]&&y[2]>=s&&l[d[h]]?.[1]<=s){c=l[d[h]],u=y;break}}u!=t&&(r(),u?(e=[c,u].map(h=>nv(n,".punctuation",0,-1,h[1])),e[0]!=e[1]&&c[2]==u[1]&&(e[0].textContent+=e[1].textContent,e[1].textContent="",e[1]=e[0]),r(!0)):e=[]),t=u}},r=o=>e.forEach(a=>a.classList.toggle("active-bracket",!!o));Wi(n,"focus",i),Wi(n,"blur",i),n.on("selectionChange",i)};var Pu=Ru?4:2;var sv=(n=999)=>{let t=0,e,i,r=!1,o,a,s,l,d,c,u=[],h=v=>{v>=n&&(v--,u.shift()),u.splice(t=v,n,[e.value,c(),c()])},y=v=>{u[v]&&(d.value=u[v][0],d.setSelectionRange(...u[v][v<t?2:1]),e.update(),e.extensions.cursor?.scrollIntoView(),t=v,i=!1)},_=(v,x)=>{v.extensions.history=_,e=v,c=v.getSelection,d||h(0),d=v.textarea,v.on("selectionChange",()=>{i=r,r=!1}),Wi(v,"beforeinput",A=>{let T=A.data,L=A.inputType,N=A.timeStamp;/history/.test(L)?(y(t+(L[7]=="U"?-1:1)),Ui(A)):(l=i&&(o==L||N-s<99&&L.slice(-4)=="Drop")&&!Tu&&(T!=" "||a==T))||(u[t][2]=Tu||c()),r=!0,a=T,s=N,o=L}),Wi(v,"input",()=>h(t+!l)),Wi(v,"keydown",A=>{if(!x.readOnly){let T=rv(A),L=A.keyCode,N=T==Pu&&L==90,ve=T==Pu+8&&L==90||!Ru&&T==Pu&&L==89;N?(y(t-1),Ui(A)):ve&&(y(t+1),Ui(A))}}),v.addExtensions({update(){v.value!=d.value&&b()}})},b=_.clear=()=>{h(0),i=!1};return _.has=v=>t+v in u,_.go=v=>y(t+v),_};var GM=Iu('<div style=display:flex;align-items:flex-start;justify-content:flex-end><button type=button dir=ltr style=display:none class=pce-copy aria-label=Copy><svg width=1.2em aria-hidden=true viewBox="0 0 16 16" overflow=visible stroke-linecap=round fill=none stroke=currentColor><rect x=4 y=4 width=11 height=11 rx=1 /><path d="m12 2a1 1 0 00-1-1H2A1 1 0 001 2v9a1 1 0 001 1">'),lv=()=>n=>{let t=GM(),e=t.firstChild;$t(e,"click",()=>{e.setAttribute("aria-label","Copied!"),navigator.clipboard?.writeText(n.extensions.codeFold?.fullCode??n.value)||(n.textarea.select(),En.execCommand("copy"),Ou(n,0))}),$t(e,"pointerenter",()=>e.setAttribute("aria-label","Copy")),ov(n,t)};var Kr=/\/\/.*|\/\*[^]*?(?:\*\/|$)/g;var Gl=/[()[\]{}.,:;]/,Yr=/\b(?:false|true)\b/,Fu={punctuation:/\./};ue.webmanifest=ue.json={property:/"(?:\\.|[^\\\n"])*"(?=\s*:)/g,string:/"(?:\\.|[^\\\n"])*"/g,comment:Kr,number:/-?\b\d+(?:\.\d+)?(?:e[+-]?\d+)?\b/i,operator:/:/,punctuation:/[[\]{},]/,boolean:Yr,null:{pattern:/\bnull\b/,alias:"keyword"}};var Aa=(n,t)=>n.replace(/<(\d+)>/g,(e,i)=>`(?:${t[+i]})`),Qr=(n,t,e)=>RegExp(Aa(n,t),e);var Nu=/[*&][^\s[\]{},]+/,Lu=/!(?:<[\w%#;/?:@&=$,.!~*'()[\]+-]+>|(?:[a-zA-Z\d-]*!)?[\w%#;/?:@&=$.~*'()+-]+)?/,Bu=`(?:${Lu.source}(?:[ 	]+${Nu.source})?|${Nu.source}(?:[ 	]+${Lu.source})?)`,WM=Aa("(?:[^\\s\0-\\x08\\x0e-\\x1f!\"#%&'*,:>?@[\\]{}`|\\x7f-\\x84\\x86-\\x9f\\ud800-\\udfff\\ufffe\\uffff-]|[?:-]<0>)(?:[ 	]*(?:(?![#:])<0>|:<0>))*",["[^\\s\0-\\x08\\x0e-\\x1f,[\\]{}\\x7f-\\x84\\x86-\\x9f\\ud800-\\udfff\\ufffe\\uffff]"]),cv=`"(?:\\\\.|[^\\\\
"])*"|'(?:\\\\.|[^\\\\
'])*'`,Ia=(n,t)=>Qr(`([:,[{-]\\s*(?:\\s<0>[ 	]+)?)<1>(?=[ 	]*(?:$|,|\\]|\\}|(?:
\\s*)?#))`,[Bu,n],t);ue.yml=ue.yaml={scalar:{pattern:Qr(`([:-]\\s*(?:\\s<0>[ 	]+)?[|>])[ 	]*(?:(
[ 	]+)\\S.*(?:\\2.+)*)`,[Bu]),lookbehind:!0,alias:"string"},comment:/#.*/,key:{pattern:Qr(`((?:^|[:,[{
?-])[ 	]*(?:<0>[ 	]+)?)<1>(?=\\s*:\\s)`,[Bu,"(?:"+WM+"|"+cv+")"],"g"),lookbehind:!0,alias:"atrule"},directive:{pattern:/(^[ 	]*)%.+/m,lookbehind:!0,alias:"important"},datetime:{pattern:Ia("\\d{4}-\\d\\d?-\\d\\d?(?:[tT]|[ 	]+)\\d\\d?:\\d\\d:\\d\\d(?:\\.\\d*)?(?:[ 	]*(?:Z|[+-]\\d\\d?(?::\\d\\d)?))?|\\d{4}-\\d\\d-\\d\\d|\\d\\d?:\\d\\d(?::\\d\\d(?:\\.\\d*)?)?","m"),lookbehind:!0,alias:"number"},boolean:{pattern:Ia("false|true","im"),lookbehind:!0,alias:"important"},null:{pattern:Ia("null|~","im"),lookbehind:!0,alias:"important"},string:{pattern:Ia(cv,"mg"),lookbehind:!0},number:{pattern:Ia("[+-]?(?:0x[a-f\\d]+|0o[0-7]+|(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:e[+-]?\\d+)?|\\.inf|\\.nan)","im"),lookbehind:!0},tag:Lu,important:Nu,punctuation:/---|[:[\]{},|>?-]|\.{3}/};var Wl=(n,t)=>({pattern:RegExp("(^(?:"+n+"):[ 	]*)\\S[^]*","i"),lookbehind:!0,alias:t&&"language-"+t,inside:t}),dv=ue.http={"request-line":{pattern:/^(?:CONNECT|DELETE|GET|HEAD|OPTIONS|PATCH|POST|PRI|PUT|SEARCH|TRACE)\s(?:https?:\/)?\/\S*\sHTTP\/[\d.]+/m,inside:{method:{pattern:/^\w+/,alias:"property"},"request-target":{pattern:/^(\s)[h/]\S*/,lookbehind:!0,alias:"url",inside:"uri"},"http-version":{pattern:/(?!^)\S+/,alias:"property"}}},"response-status":{pattern:/^HTTP\/[\d.]+ \d+ .+/m,inside:{"http-version":{pattern:/^\S+/,alias:"property"},"status-code":{pattern:/^( )\d+(?= )/,lookbehind:!0,alias:"number"},"reason-phrase":{pattern:/(?!^).+/,alias:"string"}}}};["application/javascript","application/json","application/xml","text/xml","text/html","text/css","text/plain"].forEach(n=>{var t=n.split("/")[1],e=n[10]&&!t[4]?"(?:"+n+"|\\w+/(?:[\\w.-]+\\+)+"+t+"(?![\\w.+-]))":n;dv[n.replace("/","-")]={pattern:RegExp("(content-type:\\s*"+e+`(?:;.*)?(?:
[\\w-].*)*
)[^\\w 	-][^]*`,"i"),lookbehind:!0,alias:"language-"+t,inside:t=="json"?ue.json||"js":t}});dv.header={pattern:/^[\w-]+:.+(?:\n[ 	].+)*/m,inside:{"header-value":[Wl("Content-Security-Policy","csp"),Wl("Public-Key-Pins(?:-Report-Only)?","hpkp"),Wl("Strict-Transport-Security","hsts"),Wl("[^:]+")],"header-name":{pattern:/^[^:]+/,alias:"keyword"},punctuation:/^:/}};var $u=/\b(?:abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|exports|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|interface|long|module|native|new|non-sealed|null|opens?|package|permits|private|protected|provides|public|record(?!\s*[()[\]{}%~.,:;?%&|^=<>/*+-])|requires|return|sealed|short|static|strictfp|super|switch|synchronized|this|throws?|to|transient|transitive|try|uses|var|void|volatile|while|with|yield)\b/,Ta="(?:[a-z]\\w*\\s*\\.\\s*)*(?:[A-Z]\\w*\\s*\\.\\s*)*",ju={pattern:/^[a-z]\w*(?:\s*\.\s*[a-z]\w*)*(?:\s*\.)?/,inside:Fu},Vu={namespace:ju,punctuation:/\./},mv={pattern:RegExp(`(^|[^\\w.])${Ta}[A-Z](?:[\\d_A-Z]*[a-z]\\w*)?\\b`),lookbehind:!0,inside:Vu};ue.java={"doc-comment":{pattern:/\/\*\*(?!\/)[^]*?(?:\*\/|$)/g,alias:"comment",inside:"javadoc"},comment:Kr,"triple-quoted-string":{pattern:/"""[ 	]*\n(?:\\.|[^\\])*?"""/g,alias:"string"},char:/'(?:\\.|[^\\\n']){1,6}'/g,string:{pattern:/(^|[^\\])"(?:\\.|[^\\\n"])*"/g,lookbehind:!0},annotation:{pattern:/(^|[^.])@\w+(?:\s*\.\s*\w+)*/,lookbehind:!0,alias:"punctuation"},generics:{pattern:/<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&))*>)*>)*>)*>/,inside:{"class-name":mv,keyword:$u,punctuation:/[().,:<>]/,operator:/[?&|]/}},import:[{pattern:RegExp(`(\\bimport\\s+)${Ta}(?:[A-Z]\\w*|\\*)(?=\\s*;)`),lookbehind:!0,inside:{namespace:ju,punctuation:/\./,operator:/\*/,"class-name":/\w+/}},{pattern:RegExp(`(\\bimport\\s+static\\s+)${Ta}(?:\\w+|\\*)(?=\\s*;)`),lookbehind:!0,alias:"static",inside:{namespace:ju,static:/\b\w+$/,punctuation:/\./,operator:/\*/,"class-name":/\w+/}}],namespace:{pattern:RegExp(`(\\b(?:exports|import(?:\\s+static)?|module|opens?|package|provides|requires|to|transitive|uses|with)\\s+)(?!${$u.source})[a-z]\\w*(?:\\.[a-z]\\w*)*\\.?`),lookbehind:!0,inside:Fu},"class-name":[mv,{pattern:RegExp(`(^|[^\\w.])${Ta}[A-Z]\\w*(?=\\s+\\w+\\s*[;,=()]|\\s*(?:\\[[\\s,]*\\]\\s*)?::\\s*new\\b)`),lookbehind:!0,inside:Vu},{pattern:RegExp(`(\\b(?:class|enum|extends|implements|instanceof|interface|new|record|throws)\\s+)${Ta}[A-Z]\\w*\\b`),lookbehind:!0,inside:Vu}],keyword:$u,boolean:Yr,function:{pattern:/\b\w+(?=\()|(::\s*)[a-z_]\w*/,lookbehind:!0},number:/\b0b[01][01_]*l?\b|\b0x(?:\.[a-f\d_p+-]+|[a-f\d_]+(?:\.[a-f\d_p+-]+)?)\b|(?:\b\d[\d_]*(?:\.[\d_]*)?|\B\.\d[\d_]*)(?:e[+-]?\d[\d_]*)?[dfl]?/i,constant:/\b[A-Z][A-Z_\d]+\b/,operator:{pattern:/(^|[^.])(?:<<=?|>>>?=?|->|--|\+\+|&&|\|\||::|[?:~]|[%&|^!=<>/*+-]=?)/m,lookbehind:!0},punctuation:Gl};var zu={"interpolation-punctuation":{pattern:/^.\{?|\}$/g,alias:"punctuation"},expression:{pattern:/[^]+/}};zu.expression.inside=ue.kts=ue.kt=ue.kotlin={"string-literal":[{pattern:/"""(?:[^$]|\$(?:(?!\{)|\{[^{}]*\}))*?"""/,alias:"multiline",inside:{interpolation:{pattern:/\$(?:[a-z_]\w*|\{[^{}]*\})/i,inside:zu},string:/[^]+/}},{pattern:/"(?:\\.|[^\\\n"$]|\$(?:(?!\{)|\{[^{}]*\}))*"/,alias:"singleline",inside:{interpolation:{pattern:/((?:^|[^\\])(?:\\\\)*)\$(?:[a-z_]\w*|\{[^{}]*\})/i,lookbehind:!0,inside:zu},string:/[^]+/}}],char:/'(?:[^\\\n']|\\(?:.|u[a-fA-F\d]{0,4}))'/g,comment:Kr,annotation:{pattern:/\B@(?:\w+:)?(?:[A-Z]\w*|\[[^\]]+\])/,alias:"builtin"},keyword:{pattern:/(^|[^.])\b(?:abstract|actual|annotation|as|break|by|catch|class|companion|const|constructor|continue|crossinline|data|do|dynamic|else|enum|expect|external|final|finally|for|fun|get|if|import|in|infix|init|inline|inner|interface|internal|is|lateinit|noinline|null|object|open|operator|out|override|package|private|protected|public|reified|return|sealed|set|super|suspend|tailrec|this|throw|to|try|typealias|val|var|vararg|when|where|while)\b/,lookbehind:!0},boolean:Yr,label:{pattern:/\b\w+@|@\w+/,alias:"symbol"},function:{pattern:/(?:`[^\n`]+`|\b\w+)(?=\s*\()|(\.)(?:`[^\n`]+`|\w+)(?=\s*\{)/g,lookbehind:!0},number:/\b(?:0[xX][a-fA-F\d]+(?:_[a-fA-F\d]+)*|0[bB][01]+(?:_[01]+)*|\d+(?:_\d+)*(?:\.\d+(?:_\d+)*)?(?:[eE][+-]?\d+(?:_\d+)*)?[fFL]?)\b/,operator:/--|\+\+|&&|\|\||->|[!=]==|!!|[%!=<>/*+-]=?|[?:]:?|\.\.|\b(?:and|inv|shl|u?shr|x?or)\b/,punctuation:Gl};var Kl=(n,t)=>{if(t.has(n))return t.get(n);var e=n,i=KM.call(n).slice(8,-1);if(i=="Object"){t.set(n,e={});for(var r in n)e[r]=Kl(n[r],t);n[Gr]&&(e[Gr]=Kl(n[Gr],t)),n[qi]&&(e[qi]=n[qi])}else if(i=="Array"){t.set(n,e=[]);for(var o=0,a=n.length;o<a;o++)e[o]=Kl(n[o],t)}return e},Yl=n=>Kl(n,new Map);var Ql=(n,t,e)=>{var i={};for(var r in n)i[r]=n[r],delete n[r];for(var r in i)r==t&&Object.assign(n,e),e.hasOwnProperty(r)||(n[r]=i[r])},KM={}.toString;var Hu=[{pattern:/&[a-z\d]{1,8};/i,alias:"named-entity"},/&#x?[a-f\d]{1,8};/i],uv=/<!--(?:(?!<!--)[^])*?-->/g,pv={pattern:/<\/?(?!\d)[^\s/=>$<%]+(?:\s(?:\s*[^\s/=>]+(?:\s*=\s*(?!\s)(?:"[^"]*"|'[^']*'|[^\s"'=>]+(?=[\s>]))?|(?=[\s/>])))+)?\s*\/?>/g,inside:{punctuation:/^<\/?|\/?>$/,tag:{pattern:/^\S+/,inside:{namespace:/^[^:]+:/}},"attr-value":[{pattern:/(=\s*)(?:"[^"]*"|'[^']*'|[^\s"'>]+)/g,lookbehind:!0,inside:{punctuation:/^["']|["']$/g,entity:Hu}}],"attr-equals":/=/,"attr-name":{pattern:/\S+/,inside:{namespace:/^[^:]+:/}}}};ue.rss=ue.atom=ue.ssml=ue.xml={comment:uv,prolog:/<\?[^]+?\?>/g,doctype:{pattern:/<!DOCTYPE(?:[^>"'[\]]|"[^"]*"|'[^']*')+(?:\[(?:[^<"'\]]|"[^"]*"|'[^']*'|<(?!!--)|<!--(?:[^-]|-(?!->))*-->)*\]\s*)?>/gi,inside:{"internal-subset":{pattern:/(\[)[^]+(?=\]\s*>$)/,lookbehind:!0,inside:"xml"},string:/"[^"]*"|'[^']*'/,punctuation:/^<!|[>[\]]/,"doctype-tag":/^DOCTYPE/i,name:/\S+/}},cdata:/<!\[CDATA\[[^]*?\]\]>/gi,tag:pv,entity:Hu,"markup-bracket":{pattern:/[()[\]{}]/,alias:"punctuation"}};var qu=(n,t)=>(n["language-"+t]={pattern:/[^]+/,inside:t},n),hv=(n,t)=>({pattern:RegExp(`(<${n}[^>]*>)(?!</${n}>)(?:<!\\[CDATA\\[(?:[^\\]]|\\](?!\\]>))*\\]\\]>|(?!<!\\[CDATA\\[)[^])+?(?=</${n}>)`,"gi"),lookbehind:!0,inside:qu({"included-cdata":{pattern:/<!\[CDATA\[[^]*?\]\]>/i,inside:qu({cdata:/^<!\[CDATA\[|\]\]>$/i},t)}},t)}),fv=(n,t,e=n)=>({pattern:RegExp(`([\\s"']${n}\\s*=\\s*)(?:"[^"]*"|'[^']*'|[^\\s>]+)`,"gi"),lookbehind:!0,alias:e,inside:qu({punctuation:/^["']|["']$/g},t)}),gv=ue.svg=ue.mathml=ue.html=ue.markup=Yl(ue.xml);gv.tag.inside["attr-value"].unshift(fv("style","css"),fv("on[a-z]+","javascript","script"));Ql(gv,"cdata",{style:hv("style","css"),script:hv("script","javascript")});var YM=[`(?:\\\\.|[^\\\\
]|
(?!
))`],Xl=n=>Qr(`((?:^|[^\\\\])(?:\\\\\\\\)*)(?:${n})`,YM,"g"),Uu=/(?:\\.|``(?:[^\n`]|`(?!`))+``|`[^\n`]+`|[^\\\n|`])+/,_v=Aa(`\\|?<0>(?:\\|<0>)+\\|?(?:
|(?![\\s\\S]))`,[Uu.source]),QM=`\\|?[ 	]*:?-{3,}:?[ 	]*(?:\\|[ 	]*:?-{3,}:?[ 	]*)+\\|?
`,Xr=ue.md=ue.markdown=Yl(ue.html);Ql(Xr,"prolog",{"front-matter-block":{pattern:/(^(?:\s*\n)?)---(?!.)[^]*?\n---(?!.)/g,lookbehind:!0,inside:{punctuation:/^---|---$/,"front-matter":{pattern:/\S(?:[^]*\S)?/,alias:"language-yaml",inside:"yaml"}}},blockquote:{pattern:/^>(?:[ 	]*>)*/m,alias:"punctuation"},table:{pattern:RegExp("^"+_v+QM+"(?:"+_v+")*","m"),inside:{"table-header-row":{pattern:/^.+/,inside:{"table-header":{pattern:Uu,alias:"important",inside:Xr},punctuation:/\|/}},"table-data-rows":{pattern:/(.+\n)[^]+/,lookbehind:!0,inside:{"table-data":{pattern:Uu,inside:Xr},punctuation:/\|/}},"table-line":{pattern:/.+/,inside:{punctuation:/\S+/}}}},code:[{pattern:/(^[ 	]*\n)(?:    |	).+(?:\n(?:    |	).+)*/m,lookbehind:!0,alias:"keyword"},{pattern:/^(```+)[^`][^]*?^\1`*$/gm,inside:{punctuation:/^`+|`+$/,"code-language":/^.+/,"code-block":/(?!^)[^]+(?=\n)/,[qi](n,t){var e=Su(n,t),i;return e[5]&&(i=(/[a-z][\w-]*/i.exec(e[1].content.replace(/\b#/g,"sharp").replace(/\b\+\+/g,"pp"))||[""])[0].toLowerCase(),e[3].alias="language-"+i,(t=ue[i])&&(e[3].content=ka(e[3].content,t))),e}}}],title:[{pattern:/\S.*\n(?:==+|--+)(?=[ 	]*$)/m,alias:"important",inside:{punctuation:/=+$|-+$/}},{pattern:/(^\s*)#.+/m,lookbehind:!0,alias:"important",inside:{punctuation:/^#+|#+$/}}],hr:{pattern:/(^\s*)([*-])(?:[ 	]*\2){2,}(?=\s*$)/m,lookbehind:!0,alias:"punctuation"},list:{pattern:/(^\s*)(?:[*+-]|\d+\.)(?=[ 	].)/m,lookbehind:!0,alias:"punctuation"},"url-reference":{pattern:/!?\[[^\]]+\]:[ 	]+(?:\S+|<(?:\\.|[^\\>])+>)(?:[ 	]+(?:"(?:\\.|[^\\"])*"|'(?:\\.|[^\\'])*'|\((?:\\.|[^\\)])*\)))?/,inside:{variable:{pattern:/^(!?\[)[^\]]+/,lookbehind:!0},string:/(?:"(?:\\.|[^\\"])*"|'(?:\\.|[^\\'])*'|\((?:\\.|[^\\)])*\))$/,punctuation:/^[[\]!:]|<|>/},alias:"url"},bold:{pattern:Xl("\\b__(?:(?!_)<0>|_(?:(?!_)<0>)+_)+__\\b|\\*\\*(?:(?!\\*)<0>|\\*(?:(?!\\*)<0>)+\\*)+\\*\\*"),lookbehind:!0,inside:{content:{pattern:/(^..)[^]+(?=..)/,lookbehind:!0,inside:{}},punctuation:/../}},italic:{pattern:Xl("\\b_(?:(?!_)<0>|__(?:(?!_)<0>)+__)+_\\b|\\*(?:(?!\\*)<0>|\\*\\*(?:(?!\\*)<0>)+\\*\\*)+\\*"),lookbehind:!0,inside:{content:{pattern:/(?!^)[^]+(?=.)/,inside:{}},punctuation:/./}},strike:{pattern:Xl("(~~?)(?:(?!~)<0>)+\\2"),lookbehind:!0,inside:{punctuation:/^~~?|~~?$/,content:{pattern:/[^]+/,inside:{}}}},"code-snippet":{pattern:/(^|[^\\`])(`+)[^\n`](?:|.*?[^\n`])\2(?!`)/g,lookbehind:!0,alias:"code keyword"},url:{pattern:Xl('!?\\[(?:(?!\\])<0>)+\\](?:\\([^\\s)]+(?:[ 	]+"(?:\\\\.|[^\\\\"])*")?\\)|[ 	]?\\[(?:(?!\\])<0>)+\\])'),lookbehind:!0,inside:{operator:/^!/,content:{pattern:/(^\[)[^\]]+(?=\])/,lookbehind:!0,inside:{}},variable:{pattern:/(^\][ 	]?\[)[^\]]+(?=\]$)/,lookbehind:!0},url:{pattern:/(^\]\()[^\s)]+/,lookbehind:!0},string:{pattern:/(^[ 	]+)"(?:\\.|[^\\"])*"(?=\)$)/,lookbehind:!0},"markup-bracket":Xr["markup-bracket"]}}});["url","bold","italic","strike"].forEach(n=>{["url","bold","italic","strike","code-snippet","markup-bracket"].forEach(t=>{n!=t&&(Xr[n].inside.content.inside[t]=Xr[t])})});var XM=["editorContainer"],Zr=class n{code=wp("");language=yt("markdown");readonly=yt(!1,{transform:t=>t=="true"});editor=void 0;editorContainer;ngAfterViewInit(){this.editorContainer.nativeElement&&(this.editor=this.initEditor())}ngOnChanges(t){t.code.previousValue!==t.code.currentValue&&this.editor?.setOptions({value:t.code.currentValue})}initEditor(){let t=Ea(this.editorContainer.nativeElement,{value:this.code(),language:this.language(),lineNumbers:!1,wordWrap:!0,readOnly:this.readonly(),onUpdate:e=>{this.code.set(e)}});return t.addExtensions(lv(),ev(!0),av(),sv()),this.code.subscribe(()=>{this.editor?.update()}),t}static \u0275fac=function(e){return new(e||n)};static \u0275cmp=I({type:n,selectors:[["app-prism-editor"]],viewQuery:function(e,i){if(e&1&&Ne(XM,5),e&2){let r;H(r=q())&&(i.editorContainer=r.first)}},inputs:{code:[1,"code"],language:[1,"language"],readonly:[1,"readonly"]},outputs:{code:"codeChange"},features:[dt],decls:2,vars:0,consts:[["editorContainer",""]],template:function(e,i){e&1&&Zt(0,"div",null,0)},styles:["[_nghost-%COMP%] > div[_ngcontent-%COMP%]{margin-bottom:.5em}"]})};function ZM(n,t){if(n&1&&(g(0,"div",3)(1,"span"),w(2,"Operation description"),f(),P(3,"markdown",17),f()),n&2){let e=C();p(3),R("data",e.operation().description)}}function JM(n,t){if(n&1&&(g(0,"div",3)(1,"span"),w(2,"Message description"),f(),P(3,"markdown",17),f()),n&2){let e=C();p(3),R("data",e.operation().message.description)}}function eA(n,t){if(n&1&&(g(0,"div",3)(1,"span"),w(2,"Content-Type"),f(),g(3,"span",18),w(4),f()()),n&2){let e=C();p(4),Le(e.operation().message.contentType)}}function tA(n,t){if(n&1&&(g(0,"div",3)(1,"span"),w(2,"Reply to"),f(),g(3,"span")(4,"mat-chip-set")(5,"a",4)(6,"mat-chip"),P(7,"mat-icon",19),w(8),f()()(),w(9," with "),g(10,"mat-chip-set")(11,"a",4)(12,"mat-chip"),P(13,"mat-icon",6),w(14),f()()()()()),n&2){let e=C();p(5),R("href",ni(e.operation().reply.channelAnchorUrl),Ze),p(3),J(" ",e.operation().reply.channelName," "),p(3),R("href",ni(e.operation().reply.messageAnchorUrl),Ze),p(3),J(" ",e.operation().reply.messageName," ")}}function nA(n,t){if(n&1&&(g(0,"a",4)(1,"mat-chip"),P(2,"mat-icon",20),w(3),f()()),n&2){let e=t.$implicit;R("href",ni(e.anchorUrl),Ze),p(3),J(" ",e.name," ")}}function iA(n,t){if(n&1){let e=ot();g(0,"app-prism-editor",22),co("codeChange",function(r){Ke(e);let o=C(3);return lo(o.operationBindingExampleString,r)||(o.operationBindingExampleString=r),Ye(r)}),f()}if(n&2){let e=C(3);so("code",e.operationBindingExampleString)}}function rA(n,t){if(n&1&&(g(0,"div"),k(1,iA,1,1,"app-prism-editor",21),f()),n&2){let e=C(2);p(),D(e.operationBindingExampleString?1:-1)}}function oA(n,t){if(n&1&&(g(0,"div",0)(1,"div",5)(2,"h6"),w(3,"Operation Binding"),f()(),g(4,"div",5),k(5,rA,2,1,"div"),f()()),n&2){let e=C();p(5),D(e.operation().protocol?5:-1)}}function aA(n,t){if(n&1){let e=ot();g(0,"app-prism-editor",22),co("codeChange",function(r){Ke(e);let o=C(2);return lo(o.messageBindingExampleString,r)||(o.messageBindingExampleString=r),Ye(r)}),f()}if(n&2){let e=C(2);so("code",e.messageBindingExampleString)}}function sA(n,t){n&1&&(g(0,"span")(1,"i"),w(2,"none"),f()())}function lA(n,t){if(n&1&&(g(0,"div",0)(1,"div",5)(2,"h6"),w(3,"Message Binding"),f()(),g(4,"div",5)(5,"div"),k(6,aA,1,1,"app-prism-editor",21),k(7,sA,3,0,"span"),f()()()),n&2){let e=C();p(6),D(e.messageBindingExampleString?6:-1),p(),D(e.messageBindingExampleString?-1:7)}}function cA(n,t){if(n&1&&(g(0,"a",4)(1,"mat-chip"),P(2,"mat-icon",6),w(3),f()()),n&2){let e=C(2);R("href",e.headers().anchorUrl,Ze),p(3),J(" ",e.headers().title," ")}}function dA(n,t){if(n&1&&P(0,"app-schema",7),n&2){let e=C(2);R("schema",e.headers())}}function mA(n,t){if(n&1){let e=ot();g(0,"div",0)(1,"div",5)(2,"h6"),w(3,"Headers"),f(),g(4,"mat-chip-set"),k(5,cA,4,2,"a",4),f(),k(6,dA,1,1,"app-schema",7),f(),g(7,"div",5)(8,"app-prism-editor",23),co("codeChange",function(r){Ke(e);let o=C();return lo(o.headersExample.value,r)||(o.headersExample.value=r),Ye(r)}),f()()()}if(n&2){let e=C();p(5),D(e.headers().anchorUrl!==e.initSchema.anchorUrl?5:-1),p(),D(e.headers()?6:-1),p(2),so("code",e.headersExample.value)}}function uA(n,t){if(n&1&&P(0,"app-schema",7),n&2){let e=C();R("schema",e.defaultSchema())}}var Zl=class n{constructor(t,e,i,r){this.asyncApiService=t;this.publisherService=e;this.uiService=i;this.snackBar=r}channelName=yt.required();operation=yt.required();initSchema=Ma;defaultSchema=X(Ma);defaultExample=X(Sa);originalDefaultExample=X(Sa);exampleLanguage=Jt(()=>{let t=this.operation().message.contentType;return t.includes("avro")?"json":t.split("/").pop()||"json"});headers=X(Ma);headersExample=Sa;originalHeadersExample=X(Sa);operationBindingExampleString;messageBindingExampleString;isShowBindings=X(we.DEFAULT_SHOW_BINDINGS);isShowHeaders=X(we.DEFAULT_SHOW_HEADERS);canPublish=X(!1);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(t=>{let e=t.components.schemas,i=this.operation().message.payload;if(i.ts_type==="ref"){let o=i.name.slice(i.name.lastIndexOf("/")+1),a=e.get(o);this.defaultSchema.set(a),this.defaultExample.set(a.example||Gi),this.originalDefaultExample.set(a.example||Gi)}else this.defaultSchema.set(i),this.defaultExample.set(i.example||Gi),this.originalDefaultExample.set(i.example||Gi);let r=this.operation().message.headers;r?this.headers.set(e.get(r?.name)):this.headers.set(Ma),this.headersExample=this.headers().example||Gi,this.originalHeadersExample.set(this.headers().example||Gi),this.operationBindingExampleString=new qt(this.operation().bindings[this.operation().protocol])?.value,this.messageBindingExampleString=this.createBindingExample(this.operation().message.bindings.get(this.operation().protocol))?.value,this.reset(),this.publisherService.canPublish(this.operation().protocol).subscribe(o=>{this.canPublish.set(o)})}),this.uiService.isShowBindings$.subscribe(t=>this.isShowBindings.set(t)),this.uiService.isShowHeaders$.subscribe(t=>this.isShowHeaders.set(t))}createBindingExample(t){if(t==null)return;let e={};return Object.keys(t).forEach(i=>{i!=="bindingVersion"&&(e[i]=this.getExampleValue(t[i]))}),new qt(e)}getExampleValue(t){if(typeof t=="string")return t;if("examples"in t&&typeof t.examples=="object")return t.examples[0]}reset(){this.defaultExample.set(new qt(this.originalDefaultExample().rawValue)),this.headersExample=new qt(this.originalHeadersExample().rawValue)}publish(){let t=this.defaultExample().value,e=this.operation().message.payload.name,i=this.headersExample.value,r=this.messageBindingExampleString;try{let o=i===""?{}:Yc("Unable to convert headers to JSON object (or is empty)",()=>JSON.parse(i||"")),a=r===""?{}:Yc("Unable to convert bindings to JSON object (or is empty)",()=>JSON.parse(r||""));this.publisherService.publish(this.operation().protocol||"not-supported-protocol",this.channelName(),t,e,o,a).subscribe(s=>this.handlePublishSuccess(),s=>this.handlePublishError(s))}catch(o){this.snackBar.open("Unable to create publishing payload: "+o?.message,"ERROR",{duration:3e3})}}handlePublishSuccess(){return this.snackBar.open("Example payload sent to: "+this.channelName(),"PUBLISHED",{duration:3e3})}handlePublishError(t){let e="Publish failed";return t?.status===ce.NOT_FOUND&&(e+=": no publisher was provided for "+this.operation().protocol),this.snackBar.open(e,"ERROR",{duration:4e3})}static \u0275fac=function(e){return new(e||n)(Se(nt),Se(Lr),Se(we),Se(zo))};static \u0275cmp=I({type:n,selectors:[["app-channel-operation"]],inputs:{channelName:[1,"channelName"],operation:[1,"operation"]},decls:45,vars:15,consts:[[1,"row"],[1,"width-12-of-12","width-12-of-12-s"],[1,"table","margin-vertical-1em"],[1,"table-row"],[3,"href"],[1,"width-6-of-12","width-12-of-12-s"],["matChipAvatar","","fontIcon","schema"],[3,"schema"],[3,"codeChange","code","language"],[1,"flex","space-between"],[1,"flex","gap-8"],["mat-raised-button","",3,"cdkCopyToClipboard"],["fontIcon","content_copy"],["mat-raised-button","",3,"click"],["fontIcon","restart_alt"],["mat-raised-button","",3,"click","disabled"],["fontIcon","send"],[3,"data"],[1,"text-console"],["matChipAvatar","","fontIcon","swap_vert"],["matChipAvatar","","fontIcon","dns"],["language","json","readonly","true",3,"code"],["language","json","readonly","true",3,"codeChange","code"],["language","json",3,"codeChange","code"]],template:function(e,i){e&1&&(g(0,"div",0)(1,"div",1)(2,"div",2)(3,"div",3)(4,"span"),w(5,"Channel"),f(),g(6,"span"),w(7),f()(),k(8,ZM,4,1,"div",3),k(9,JM,4,1,"div",3),k(10,eA,5,1,"div",3),k(11,tA,15,6,"div",3),g(12,"div",3)(13,"span"),w(14,"Servers"),f(),g(15,"span")(16,"mat-chip-set"),je(17,nA,4,3,"a",4,$e),f()()()()()(),k(19,oA,6,1,"div",0),k(20,lA,8,2,"div",0),k(21,mA,9,3,"div",0),g(22,"div",0)(23,"div",5)(24,"h6"),w(25,"Payload"),f(),g(26,"mat-chip-set")(27,"a",4)(28,"mat-chip"),P(29,"mat-icon",6),w(30),f()()(),k(31,uA,1,1,"app-schema",7),f(),g(32,"div",5)(33,"app-prism-editor",8),co("codeChange",function(o){return lo(i.defaultExample().value,o)||(i.defaultExample().value=o),o}),f(),g(34,"div",9)(35,"div",10)(36,"button",11),P(37,"mat-icon",12),w(38," Copy "),f(),g(39,"button",13),pe("click",function(){return i.reset()}),P(40,"mat-icon",14),w(41," Reset "),f()(),g(42,"button",15),pe("click",function(){return i.publish()}),P(43,"mat-icon",16),w(44," Publish "),f()()()()),e&2&&(p(7),Le(i.operation().channelName),p(),D(i.operation().description?8:-1),p(),D(i.operation().message.description?9:-1),p(),D(i.operation().message.contentType?10:-1),p(),D(i.operation().reply?11:-1),p(6),Ve(i.operation().servers),p(2),D(i.isShowBindings()?19:-1),p(),D(i.isShowBindings()?20:-1),p(),D(i.isShowHeaders()?21:-1),p(6),R("href",i.operation().message.payload.anchorUrl,Ze),p(3),J(" ",i.operation().message.payload.title," "),p(),D(i.defaultSchema()?31:-1),p(2),so("code",i.defaultExample().value),R("language",i.exampleLanguage()),p(3),R("cdkCopyToClipboard",i.defaultExample().value),p(6),R("disabled",!i.canPublish))},dependencies:[bn,_n,dn,Gn,fi,Ur,Ct,Mt,Zr,Wr,_a,vb,$n,yr],styles:[".table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:first-child{vertical-align:middle}[_nghost-%COMP%]     .mdc-evolution-chip-set__chips{max-width:100%}[_nghost-%COMP%]     .mat-mdc-standard-chip .mdc-evolution-chip__cell--primary, [_nghost-%COMP%]     .mat-mdc-standard-chip .mdc-evolution-chip__action--primary, [_nghost-%COMP%]     .mat-mdc-standard-chip .mat-mdc-chip-action-label{overflow:hidden}"]})};var pA=(n,t)=>({"send-badge":n,"receive-badge":t});function hA(n,t){if(n&1&&(g(0,"div"),P(1,"app-prism-editor",4),f()),n&2){let e=C(2).$implicit,i=C();p(),R("code",ni(i.JSON.stringify(e.bindings,null,2)))}}function fA(n,t){if(n&1&&(g(0,"div",2)(1,"div",3)(2,"h6"),w(3,"Channel Binding"),f()(),g(4,"div",3),k(5,hA,2,2,"div"),f()()),n&2){let e=C().$implicit;p(5),D(e.bindings?5:-1)}}function gA(n,t){if(n&1&&(g(0,"span",7),w(1),f()),n&2){let e=C().$implicit;p(),J(" ",e.operation.protocol," ")}}function _A(n,t){n&1&&P(0,"br")}function bA(n,t){if(n&1&&(g(0,"mat-card",5)(1,"mat-card-header")(2,"mat-card-title"),w(3),f(),g(4,"span",6),k(5,gA,2,1,"span",7),g(6,"span",8),w(7),f()()(),g(8,"mat-card-content"),P(9,"app-channel-operation",9),f()(),k(10,_A,1,0,"br")),n&2){let e=t.$implicit,i=t.$index,r=t.$count,o=C().$implicit;R("id",e.anchorIdentifier),p(2),le("data-testid",o.anchorIdentifier),p(),J(" ",e.operation.message.title," "),p(2),D(e.operation.protocol?5:-1),p(),R("ngClass",rr(9,pA,e.operation.operationType==="send",e.operation.operationType==="receive")),p(),J(" ",e.operation.operationType," "),p(2),R("channelName",o.name)("operation",e.operation),p(),D(i!==r-1?10:-1)}}function vA(n,t){n&1&&P(0,"br")}function yA(n,t){if(n&1&&(g(0,"article",0)(1,"h3"),P(2,"mat-icon",1),w(3),f(),k(4,fA,6,1,"div",2),je(5,bA,11,12,null,null,$e),k(7,vA,1,0,"br"),f()),n&2){let e=t.$implicit,i=t.$index,r=t.$count,o=C();R("id",e.anchorIdentifier),p(3),J(" ",e.name),p(),D(o.isShowBindings()?4:-1),p(),Ve(e.operations),p(2),D(i!==r-1?7:-1)}}var Jl=class n{constructor(t,e){this.asyncApiService=t;this.uiService=e}channels=X([]);isShowBindings=X(we.DEFAULT_SHOW_BINDINGS);JSON=JSON;ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(t=>{this.channels.set(t.channels)}),this.uiService.isShowBindings$.subscribe(t=>this.isShowBindings.set(t))}static \u0275fac=function(e){return new(e||n)(Se(nt),Se(we))};static \u0275cmp=I({type:n,selectors:[["app-channels"]],decls:4,vars:0,consts:[["appNavigationTarget","",3,"id"],["fontIcon","swap_vert"],[1,"row"],[1,"width-6-of-12","width-12-of-12-s"],["language","json","readonly","true",3,"code"],["appearance","outlined","appNavigationTarget","",3,"id"],[1,"flex","gap-16","padding-horizontal-1em","height-fix-content"],[1,"badge","protocol-badge"],[1,"badge",3,"ngClass"],[3,"channelName","operation"]],template:function(e,i){e&1&&(g(0,"h2"),w(1,"Channels"),f(),je(2,yA,8,4,"article",0,$e)),e&2&&(p(2),Ve(i.channels()))},dependencies:[Zr,hn,fo,Un,Vr,Hr,qr,zr,Zl,Xt,Mt],styles:[".badge[_ngcontent-%COMP%]{border-radius:4px;padding:.3em;font-size:smaller;text-transform:uppercase}.protocol-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-protocol);background-color:var(--springwolf-badge-color-background-protocol)}.send-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-send);background-color:var(--springwolf-badge-color-background-send)}.receive-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-receive);background-color:var(--springwolf-badge-color-background-receive)}"]})};var xA=["scrollableElement"],wA=["*"],Gu=(n,t)=>({selected:n,expanded:t});function CA(n,t){if(n&1&&P(0,"mat-icon",5),n&2){let e=C().$implicit;R("fontIcon",ni(e.icon))}}function kA(n,t){if(n&1&&(g(0,"span"),w(1),f()),n&2){let e=t.$implicit;vt(dc("badge ",e.type,"-badge")),p(),Le(e.value)}}function DA(n,t){if(n&1&&(g(0,"span"),w(1),f()),n&2){let e=t.$implicit;vt(dc("badge ",e.type,"-badge")),p(),Le(e.value)}}function EA(n,t){if(n&1&&(g(0,"li",2)(1,"span")(2,"a",6),w(3),f(),je(4,DA,2,4,"span",7,$e),f()()),n&2){let e=t.$implicit;R("ngClass",rr(3,Gu,e.selected,e.expanded)),p(2),R("href",e.href,Ze),p(),J(" ",e.name.join("\u200B")," "),p(),Ve(e.tags)}}function SA(n,t){if(n&1&&(g(0,"li",2)(1,"span")(2,"a",6),w(3),f(),je(4,kA,2,4,"span",7,$e),f(),g(6,"ul"),je(7,EA,6,6,"li",2,$e),f()()),n&2){let e=t.$implicit;R("ngClass",rr(3,Gu,e.selected,e.expanded)),p(2),R("href",e.href,Ze),p(),J(" ",e.name.join("\u200B")," "),p(),Ve(e.tags),p(3),Ve(e.children)}}function MA(n,t){if(n&1&&(g(0,"ul",2)(1,"li")(2,"span"),k(3,CA,1,2,"mat-icon",5),g(4,"b")(5,"a",6),w(6),f()()(),g(7,"ul"),je(8,SA,9,6,"li",2,$e),f()()()),n&2){let e=t.$implicit;R("ngClass",rr(4,Gu,e.selected,e.expanded)),p(3),D(e.icon?3:-1),p(2),R("href",e.href,Ze),p(),J(" ",e.name.join("\u200B")," "),p(2),Ve(e.children)}}var ec=class n{constructor(t,e){this.asyncApiService=t;this.location=e}scrollableElement;navigationTargets;navigation=X([]);ngOnInit(){this.location.subscribe(this.scrollToUrlLocation),this.asyncApiService.getAsyncApi().subscribe(t=>{let e=[];e.push({name:["Info"],icon:"info",href:pt.BASE_URL+"info"});let i=Array.from(t.servers.keys()).map(a=>({name:this.splitForWordBreaking(a),href:pt.BASE_URL+t.servers.get(a).anchorIdentifier,tags:[{type:"protocol",value:t.servers.get(a).protocol}]}));e.push({name:["Servers"],icon:"dns",href:pt.BASE_URL+"servers",children:i});let r={name:["Channels & Operations"],icon:"swap_vert",href:pt.BASE_URL+"channels",children:[]};t.channels.forEach(a=>{let s=a.operations.map(c=>({name:this.splitForWordBreaking(c.operation.message.title),href:pt.BASE_URL+c.anchorIdentifier,tags:[{type:"operation-"+c.operation.operationType,value:c.operation.operationType}]})),l=s.flatMap(c=>c.tags).flatMap(c=>c),d={name:this.splitForWordBreaking(a.name),href:pt.BASE_URL+a.anchorIdentifier,tags:this.filterAndSort(l,"value"),children:s};r.children.push(d)}),e.push(r);let o={name:["Schemas"],icon:"schema",href:pt.BASE_URL+"schemas",children:[]};t.components.schemas.forEach(a=>{o.children.push({name:this.splitForWordBreaking(a.title),href:pt.BASE_URL+""+a.anchorIdentifier})}),e.push(o),this.navigation.set(e),this.scrollToUrlLocation()})}splitForWordBreaking=t=>t.split(/(?<=[.,_/\-])/);filterAndSort(t,e){let i=new Set;return t.filter(o=>{let a=JSON.stringify(o);return i.has(a)?!1:i.add(a)}).sort((o,a)=>o[e]<a[e]?-1:o[e]>a[e]?1:0)}ngAfterViewInit(){this.scrollableElement.nativeElement.addEventListener("scroll",this.updateNavigationSelection)}updateNavigationSelection=()=>{let t="",e=this.scrollableElement.nativeElement.scrollTop;document.querySelectorAll("[appNavigationTarget]").forEach(i=>{let r=i,o=r.offsetTop,a=r.offsetHeight;e>=o&&e<o+a&&(t=pt.BASE_URL+""+r.getAttribute("id"))}),this.navigation().forEach(i=>{let r=!1;i.children?.forEach(o=>{let a=!1;o.children?.forEach(s=>{s.selected=t==s.href,s.expanded=s.selected,a=a||s.selected}),o.selected=t==o.href||a,r=r||o.selected,o.children?.forEach(s=>{s.expanded=o.selected})}),i.selected=t==i.href||r,i.children?.forEach(o=>{o.expanded=i.selected}),i.expanded=!0}),this.navigation.set([...this.navigation()])};scrollToUrlLocation=()=>{setTimeout(()=>{document.getElementById(window.location.hash.substring(1))?.scrollIntoView(),this.updateNavigationSelection()},10)};static \u0275fac=function(e){return new(e||n)(Se(nt),Se(ar))};static \u0275cmp=I({type:n,selectors:[["app-sidenav"]],contentQueries:function(e,i,r){if(e&1&&Xe(r,Xt,5),e&2){let o;H(o=q())&&(i.navigationTargets=o)}},viewQuery:function(e,i){if(e&1&&Ne(xA,5),e&2){let r;H(r=q())&&(i.scrollableElement=r.first)}},ngContentSelectors:wA,decls:8,vars:0,consts:[["scrollableElement",""],["mode","side","opened","",1,"sidenav","width-s-hide"],[1,"entry",3,"ngClass"],[1,"width-s-margin-reset"],[2,"overflow-y","auto","height","100%"],[3,"fontIcon"],[3,"href"],[3,"class"]],template:function(e,i){e&1&&(ne(),g(0,"mat-sidenav-container")(1,"mat-sidenav",1),je(2,MA,10,7,"ul",2,$e),f(),g(4,"mat-sidenav-content",3)(5,"div",4,0),B(7),f()()()),e&2&&(p(2),Ve(i.navigation()))},dependencies:[xa,Eu,Ub,Fl,Ct,Mt,hn,fo],styles:["a[_ngcontent-%COMP%]{color:#373737;text-decoration:none}.sidenav[_ngcontent-%COMP%]   ul[_ngcontent-%COMP%]{list-style-type:none;padding-inline-start:.5em}.sidenav[_ngcontent-%COMP%]   .entry.selected[_ngcontent-%COMP%] > span[_ngcontent-%COMP%] > a[_ngcontent-%COMP%]{font-weight:700;color:var(--mat-app-text-color)}.sidenav[_ngcontent-%COMP%]   .entry[_ngcontent-%COMP%]:not(.expanded){display:none}.sidenav[_ngcontent-%COMP%]   .entry[_ngcontent-%COMP%] > span[_ngcontent-%COMP%]{display:flex;justify-content:space-between}.sidenav[_ngcontent-%COMP%]   .badge[_ngcontent-%COMP%]{text-transform:uppercase;font-size:smaller;border-radius:.3em;padding:0 .2em;margin:.2em .3em auto}.sidenav[_ngcontent-%COMP%]   .badge.operation-send-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-send);background-color:var(--springwolf-badge-color-background-send)}.sidenav[_ngcontent-%COMP%]   .badge.operation-receive-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-receive);background-color:var(--springwolf-badge-color-background-receive)}.sidenav[_ngcontent-%COMP%]   .badge.protocol-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-protocol);background-color:var(--springwolf-badge-color-background-protocol)}"]})};function AA(n,t){if(n&1&&(g(0,"button",9),w(1," Group "),f()),n&2){C();let e=zt(16);R("matMenuTriggerFor",e)}}function IA(n,t){if(n&1){let e=ot();g(0,"button",15),pe("click",function(){let r=Ke(e).$implicit,o=C();return Ye(o.changeGroup(r.value))}),g(1,"mat-icon"),w(2),f(),w(3),f()}if(n&2){let e=t.$implicit,i=C();p(2),Le(i.isGroup()==e.value?"radio_button_checked":"radio_button_unchecked"),p(),J(" ",e.viewValue," ")}}var tc=class n{constructor(t,e,i){this.uiService=t;this.asyncApiService=e;this.assetService=i}groups=X([]);isGroup=X(we.DEFAULT_GROUP);isShowBindings=X(we.DEFAULT_SHOW_BINDINGS);isShowHeaders=X(we.DEFAULT_SHOW_HEADERS);title=X("");ngOnInit(){this.assetService.load(),this.uiService.isShowBindings$.subscribe(t=>this.isShowBindings.set(t)),this.uiService.isShowHeaders$.subscribe(t=>this.isShowHeaders.set(t)),this.uiService.isGroup$.subscribe(t=>this.isGroup.set(t)),this.uiService.uiConfig.subscribe(t=>{if(t.groups.length>0){let e=t.groups.map(i=>({value:i.name,viewValue:i.name}));this.groups.set([{value:we.DEFAULT_GROUP,viewValue:we.DEFAULT_GROUP},...e])}}),this.asyncApiService.getAsyncApi().subscribe(t=>{this.title.set(t.info.title)})}toggleIsShowBindings(){this.uiService.toggleIsShowBindings(!this.isShowBindings())}toggleIsShowHeaders(){this.uiService.toggleIsShowHeaders(!this.isShowHeaders())}changeGroup(t){this.uiService.changeGroup(t)}static \u0275fac=function(e){return new(e||n)(Se(we),Se(nt),Se(mr))};static \u0275cmp=I({type:n,selectors:[["app-header"]],decls:30,vars:5,consts:[["settings","matMenu"],["group","matMenu"],[1,"row","space-between"],["href","https://www.springwolf.dev","target","_blank",1,"flex","flex-column","items-center"],["src","assets/springwolf-logo.png","alt","Logo","height","1024","width","1024",1,"logo"],[1,"width-s-hide"],[1,"flex","flex-column","items-center"],["mat-button","","data-testid","settings",3,"matMenuTriggerFor"],[2,"color","black"],["mat-menu-item","","data-testid","settings-group-menu",3,"matMenuTriggerFor"],["mat-menu-item",""],["mat-menu-item","","data-testid","settings-bindings",3,"click"],["mat-menu-item","","data-testid","settings-headers",3,"click"],["href","https://github.com/springwolf/springwolf-core","target","_blank"],["src","assets/github.png","alt","Github","height","1024","width","1024",1,"github"],["mat-menu-item","",3,"click"]],template:function(e,i){if(e&1&&(g(0,"mat-toolbar",2)(1,"span")(2,"a",3),P(3,"img",4),w(4," Springwolf "),f()(),g(5,"h1",5),w(6),f(),g(7,"div",6)(8,"button",7)(9,"mat-icon",8),w(10,"settings"),f(),w(11," Settings "),f(),g(12,"mat-menu",null,0),k(14,AA,2,1,"button",9),g(15,"mat-menu",null,1),je(17,IA,4,2,"button",10,$e),f(),g(19,"button",11),pe("click",function(){return i.toggleIsShowBindings()}),g(20,"mat-icon"),w(21),f(),w(22," Show bindings "),f(),g(23,"button",12),pe("click",function(){return i.toggleIsShowHeaders()}),g(24,"mat-icon"),w(25),f(),w(26," Show headers "),f()(),w(27," \xA0 "),g(28,"a",13),P(29,"img",14),f()()()),e&2){let r=zt(13);p(6),Le(i.title()),p(2),R("matMenuTriggerFor",r),p(6),D(0<i.groups().length?14:-1),p(3),Ve(i.groups()),p(4),Le(i.isShowBindings()?"check_box":"check_box_outline_blank"),p(4),Le(i.isShowHeaders()?"check_box":"check_box_outline_blank")}},dependencies:[fa,gb,$n,yr,ha,$r,pa,fb,Ct,Mt],styles:[".logo[_ngcontent-%COMP%]{height:3em;width:3em;display:block}.github[_ngcontent-%COMP%]{height:2em;width:2em;display:block;filter:invert(100%)}[_nghost-%COMP%]     label, a[_ngcontent-%COMP%]{color:var(--mat-toolbar-container-text-color)}a[_ngcontent-%COMP%]{text-decoration:none}a[_ngcontent-%COMP%]:hover{color:#d3d3d3}[_nghost-%COMP%]     mat-icon{height:3em;width:3em;transform:scale(.75);filter:invert(100%)}"]})};var nc=class n{static \u0275fac=function(e){return new(e||n)};static \u0275cmp=I({type:n,selectors:[["app-root"]],decls:11,vars:0,consts:[[1,"mat-typography"],["appNavigationTarget","","id","info"],["appNavigationTarget","","id","servers"],["appNavigationTarget","","id","channels"],["appNavigationTarget","","id","schemas"]],template:function(e,i){e&1&&(P(0,"app-header"),g(1,"main",0)(2,"app-sidenav")(3,"article",1),P(4,"app-info"),f(),g(5,"article",2),P(6,"app-servers"),f(),g(7,"article",3),P(8,"app-channels"),f(),g(9,"article",4),P(10,"app-schemas"),f()()())},dependencies:[Ll,Rb,bn,tc,ec,ql,Ul,Jl,Hl,Xt],styles:[".app-header[_ngcontent-%COMP%]{position:fixed;z-index:100;width:100%}main[_ngcontent-%COMP%]{margin:0;height:calc(100% - 64px);padding:0}article[_ngcontent-%COMP%]{margin:2em 1em}"]})};os.production&&void 0;Cc(nc,hb).catch(n=>console.error(n));
