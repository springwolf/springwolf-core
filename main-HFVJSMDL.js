import{$ as De,$a as $e,$b as yo,A as uo,Aa as tt,Ab as Q,B as Ha,Ba as Ze,Bb as vt,C as bc,Ca as h,Cb as w,D as Ua,Da as or,Db as Le,E as An,Ea as Lp,Eb as J,F as qa,Fa as Ka,Fb as fo,G as ct,Ga as Bp,Gb as Vp,H as $t,Ha as mt,Hb as go,I as ke,Ia as Jn,Ib as _o,J as Xn,Ja as bt,Jb as bo,K as Fe,Ka as rt,Kb as Za,L as E,La as Ee,Lb as Ja,M as $,Ma as Ya,Mb as sr,N as A,Na as hn,Nb as ni,O as j,Oa as Qa,Ob as Cc,P as m,Pa as T,Pb as ze,Q as po,Qa as V,Qb as kc,R as kp,Ra as G,Rb as Dc,S as Dp,Sa as vc,Sb as lr,T as In,Ta as Te,Tb as es,U as Ep,Ua as jt,Ub as ts,V as Ke,Va as yc,Vb as vo,W as Ye,Wa as ar,Wb as zp,X as Zi,Xa as ei,Xb as en,Y as te,Ya as le,Yb as Hp,Z as W,Za as k,Zb as Up,_ as Ji,_a as D,_b as yt,a as M,aa as K,ab as je,ac as qp,b as Ie,ba as bi,bb as Ve,bc as Gp,ca as X,cb as R,cc as qe,d as L,da as Sp,db as b,dc as Wp,e as Yv,ea as Ga,eb as f,ec as Kp,f as lt,fa as er,fb as P,fc as Se,g as St,ga as dt,gb as Ue,gc as xo,h as z,ha as et,hb as Qe,hc as Yp,i as Kn,ia as Y,ib as Jt,ic as ns,j as bp,ja as Tn,jb as xc,k as Pe,ka as Mp,kb as ot,l as vp,la as ho,lb as ti,m as ye,ma as Ap,mb as pe,n as yp,na as Zn,nb as Xa,o as xp,oa as tr,ob as C,p as wp,pa as nr,pb as ne,q as Cp,qa as Wa,qb as B,r as _t,ra as ir,rb as Xe,s as Be,sa as rr,sb as Ne,t as _c,ta as Ip,tb as H,u as za,ua as Tp,ub as U,v as lo,va as Op,vb as $p,w as Yn,wa as Rp,wb as jp,x as Qn,xa as Pp,xb as wc,y as co,ya as Fp,yb as Vt,z as mo,za as Np,zb as vi}from"./chunk-CXCTWFN6.js";var Jo=L(be=>{"use strict";Object.defineProperty(be,"__esModule",{value:!0});be.regexpCode=be.getEsmExportName=be.getProperty=be.safeStringify=be.stringify=be.strConcat=be.addCodeArg=be.str=be._=be.nil=be._Code=be.Name=be.IDENTIFIER=be._CodeOrName=void 0;var Xo=class{};be._CodeOrName=Xo;be.IDENTIFIER=/^[a-z$_][a-z$_0-9]*$/i;var Ni=class extends Xo{constructor(n){if(super(),!be.IDENTIFIER.test(n))throw new Error("CodeGen: name must be a valid identifier");this.str=n}toString(){return this.str}emptyStr(){return!1}get names(){return{[this.str]:1}}};be.Name=Ni;var Gt=class extends Xo{constructor(n){super(),this._items=typeof n=="string"?[n]:n}toString(){return this.str}emptyStr(){if(this._items.length>1)return!1;let n=this._items[0];return n===""||n==='""'}get str(){var n;return(n=this._str)!==null&&n!==void 0?n:this._str=this._items.reduce((e,i)=>`${e}${i}`,"")}get names(){var n;return(n=this._names)!==null&&n!==void 0?n:this._names=this._items.reduce((e,i)=>(i instanceof Ni&&(e[i.str]=(e[i.str]||0)+1),e),{})}};be._Code=Gt;be.nil=new Gt("");function ag(t,...n){let e=[t[0]],i=0;for(;i<n.length;)Fd(e,n[i]),e.push(t[++i]);return new Gt(e)}be._=ag;var Pd=new Gt("+");function sg(t,...n){let e=[Zo(t[0])],i=0;for(;i<n.length;)e.push(Pd),Fd(e,n[i]),e.push(Pd,Zo(t[++i]));return fw(e),new Gt(e)}be.str=sg;function Fd(t,n){n instanceof Gt?t.push(...n._items):n instanceof Ni?t.push(n):t.push(bw(n))}be.addCodeArg=Fd;function fw(t){let n=1;for(;n<t.length-1;){if(t[n]===Pd){let e=gw(t[n-1],t[n+1]);if(e!==void 0){t.splice(n-1,3,e);continue}t[n++]="+"}n++}}function gw(t,n){if(n==='""')return t;if(t==='""')return n;if(typeof t=="string")return n instanceof Ni||t[t.length-1]!=='"'?void 0:typeof n!="string"?`${t.slice(0,-1)}${n}"`:n[0]==='"'?t.slice(0,-1)+n.slice(1):void 0;if(typeof n=="string"&&n[0]==='"'&&!(t instanceof Ni))return`"${t}${n.slice(1)}`}function _w(t,n){return n.emptyStr()?t:t.emptyStr()?n:sg`${t}${n}`}be.strConcat=_w;function bw(t){return typeof t=="number"||typeof t=="boolean"||t===null?t:Zo(Array.isArray(t)?t.join(","):t)}function vw(t){return new Gt(Zo(t))}be.stringify=vw;function Zo(t){return JSON.stringify(t).replace(/\u2028/g,"\\u2028").replace(/\u2029/g,"\\u2029")}be.safeStringify=Zo;function yw(t){return typeof t=="string"&&be.IDENTIFIER.test(t)?new Gt(`.${t}`):ag`[${t}]`}be.getProperty=yw;function xw(t){if(typeof t=="string"&&be.IDENTIFIER.test(t))return new Gt(`${t}`);throw new Error(`CodeGen: invalid export name: ${t}, use explicit $id name mapping`)}be.getEsmExportName=xw;function ww(t){return new Gt(t.toString())}be.regexpCode=ww});var Bd=L(Tt=>{"use strict";Object.defineProperty(Tt,"__esModule",{value:!0});Tt.ValueScope=Tt.ValueScopeName=Tt.Scope=Tt.varKinds=Tt.UsedValueState=void 0;var It=Jo(),Nd=class extends Error{constructor(n){super(`CodeGen: "code" for ${n} not defined`),this.value=n.value}},nl=(function(t){return t[t.Started=0]="Started",t[t.Completed=1]="Completed",t})(nl||(Tt.UsedValueState=nl={}));Tt.varKinds={const:new It.Name("const"),let:new It.Name("let"),var:new It.Name("var")};var il=class{constructor({prefixes:n,parent:e}={}){this._names={},this._prefixes=n,this._parent=e}toName(n){return n instanceof It.Name?n:this.name(n)}name(n){return new It.Name(this._newName(n))}_newName(n){let e=this._names[n]||this._nameGroup(n);return`${n}${e.index++}`}_nameGroup(n){var e,i;if(!((i=(e=this._parent)===null||e===void 0?void 0:e._prefixes)===null||i===void 0)&&i.has(n)||this._prefixes&&!this._prefixes.has(n))throw new Error(`CodeGen: prefix "${n}" is not allowed in this scope`);return this._names[n]={prefix:n,index:0}}};Tt.Scope=il;var rl=class extends It.Name{constructor(n,e){super(e),this.prefix=n}setValue(n,{property:e,itemIndex:i}){this.value=n,this.scopePath=(0,It._)`.${new It.Name(e)}[${i}]`}};Tt.ValueScopeName=rl;var Cw=(0,It._)`\n`,Ld=class extends il{constructor(n){super(n),this._values={},this._scope=n.scope,this.opts=Ie(M({},n),{_n:n.lines?Cw:It.nil})}get(){return this._scope}name(n){return new rl(n,this._newName(n))}value(n,e){var i;if(e.ref===void 0)throw new Error("CodeGen: ref must be passed in value");let r=this.toName(n),{prefix:o}=r,a=(i=e.key)!==null&&i!==void 0?i:e.ref,s=this._values[o];if(s){let d=s.get(a);if(d)return d}else s=this._values[o]=new Map;s.set(a,r);let l=this._scope[o]||(this._scope[o]=[]),c=l.length;return l[c]=e.ref,r.setValue(e,{property:o,itemIndex:c}),r}getValue(n,e){let i=this._values[n];if(i)return i.get(e)}scopeRefs(n,e=this._values){return this._reduceValues(e,i=>{if(i.scopePath===void 0)throw new Error(`CodeGen: name "${i}" has no value`);return(0,It._)`${n}${i.scopePath}`})}scopeCode(n=this._values,e,i){return this._reduceValues(n,r=>{if(r.value===void 0)throw new Error(`CodeGen: name "${r}" has no value`);return r.value.code},e,i)}_reduceValues(n,e,i={},r){let o=It.nil;for(let a in n){let s=n[a];if(!s)continue;let l=i[a]=i[a]||new Map;s.forEach(c=>{if(l.has(c))return;l.set(c,nl.Started);let d=e(c);if(d){let u=this.opts.es5?Tt.varKinds.var:Tt.varKinds.const;o=(0,It._)`${o}${u} ${c} = ${d};${this.opts._n}`}else if(d=r?.(c))o=(0,It._)`${o}${d}${this.opts._n}`;else throw new Nd(c);l.set(c,nl.Completed)})}return o}};Tt.ValueScope=Ld});var ae=L(re=>{"use strict";Object.defineProperty(re,"__esModule",{value:!0});re.or=re.and=re.not=re.CodeGen=re.operators=re.varKinds=re.ValueScopeName=re.ValueScope=re.Scope=re.Name=re.regexpCode=re.stringify=re.getProperty=re.nil=re.strConcat=re.str=re._=void 0;var me=Jo(),sn=Bd(),li=Jo();Object.defineProperty(re,"_",{enumerable:!0,get:function(){return li._}});Object.defineProperty(re,"str",{enumerable:!0,get:function(){return li.str}});Object.defineProperty(re,"strConcat",{enumerable:!0,get:function(){return li.strConcat}});Object.defineProperty(re,"nil",{enumerable:!0,get:function(){return li.nil}});Object.defineProperty(re,"getProperty",{enumerable:!0,get:function(){return li.getProperty}});Object.defineProperty(re,"stringify",{enumerable:!0,get:function(){return li.stringify}});Object.defineProperty(re,"regexpCode",{enumerable:!0,get:function(){return li.regexpCode}});Object.defineProperty(re,"Name",{enumerable:!0,get:function(){return li.Name}});var cl=Bd();Object.defineProperty(re,"Scope",{enumerable:!0,get:function(){return cl.Scope}});Object.defineProperty(re,"ValueScope",{enumerable:!0,get:function(){return cl.ValueScope}});Object.defineProperty(re,"ValueScopeName",{enumerable:!0,get:function(){return cl.ValueScopeName}});Object.defineProperty(re,"varKinds",{enumerable:!0,get:function(){return cl.varKinds}});re.operators={GT:new me._Code(">"),GTE:new me._Code(">="),LT:new me._Code("<"),LTE:new me._Code("<="),EQ:new me._Code("==="),NEQ:new me._Code("!=="),NOT:new me._Code("!"),OR:new me._Code("||"),AND:new me._Code("&&"),ADD:new me._Code("+")};var Vn=class{optimizeNodes(){return this}optimizeNames(n,e){return this}},$d=class extends Vn{constructor(n,e,i){super(),this.varKind=n,this.name=e,this.rhs=i}render({es5:n,_n:e}){let i=n?sn.varKinds.var:this.varKind,r=this.rhs===void 0?"":` = ${this.rhs}`;return`${i} ${this.name}${r};`+e}optimizeNames(n,e){if(n[this.name.str])return this.rhs&&(this.rhs=Er(this.rhs,n,e)),this}get names(){return this.rhs instanceof me._CodeOrName?this.rhs.names:{}}},al=class extends Vn{constructor(n,e,i){super(),this.lhs=n,this.rhs=e,this.sideEffects=i}render({_n:n}){return`${this.lhs} = ${this.rhs};`+n}optimizeNames(n,e){if(!(this.lhs instanceof me.Name&&!n[this.lhs.str]&&!this.sideEffects))return this.rhs=Er(this.rhs,n,e),this}get names(){let n=this.lhs instanceof me.Name?{}:M({},this.lhs.names);return ll(n,this.rhs)}},jd=class extends al{constructor(n,e,i,r){super(n,i,r),this.op=e}render({_n:n}){return`${this.lhs} ${this.op}= ${this.rhs};`+n}},Vd=class extends Vn{constructor(n){super(),this.label=n,this.names={}}render({_n:n}){return`${this.label}:`+n}},zd=class extends Vn{constructor(n){super(),this.label=n,this.names={}}render({_n:n}){return`break${this.label?` ${this.label}`:""};`+n}},Hd=class extends Vn{constructor(n){super(),this.error=n}render({_n:n}){return`throw ${this.error};`+n}get names(){return this.error.names}},Ud=class extends Vn{constructor(n){super(),this.code=n}render({_n:n}){return`${this.code};`+n}optimizeNodes(){return`${this.code}`?this:void 0}optimizeNames(n,e){return this.code=Er(this.code,n,e),this}get names(){return this.code instanceof me._CodeOrName?this.code.names:{}}},ea=class extends Vn{constructor(n=[]){super(),this.nodes=n}render(n){return this.nodes.reduce((e,i)=>e+i.render(n),"")}optimizeNodes(){let{nodes:n}=this,e=n.length;for(;e--;){let i=n[e].optimizeNodes();Array.isArray(i)?n.splice(e,1,...i):i?n[e]=i:n.splice(e,1)}return n.length>0?this:void 0}optimizeNames(n,e){let{nodes:i}=this,r=i.length;for(;r--;){let o=i[r];o.optimizeNames(n,e)||(kw(n,o.names),i.splice(r,1))}return i.length>0?this:void 0}get names(){return this.nodes.reduce((n,e)=>Li(n,e.names),{})}},zn=class extends ea{render(n){return"{"+n._n+super.render(n)+"}"+n._n}},qd=class extends ea{},Gd=(()=>{class t extends zn{}return t.kind="else",t})(),ol=(()=>{class t extends zn{constructor(e,i){super(i),this.condition=e}render(e){let i=`if(${this.condition})`+super.render(e);return this.else&&(i+="else "+this.else.render(e)),i}optimizeNodes(){super.optimizeNodes();let e=this.condition;if(e===!0)return this.nodes;let i=this.else;if(i){let r=i.optimizeNodes();i=this.else=Array.isArray(r)?new Gd(r):r}if(i)return e===!1?i instanceof t?i:i.nodes:this.nodes.length?this:new t(ug(e),i instanceof t?[i]:i.nodes);if(!(e===!1||!this.nodes.length))return this}optimizeNames(e,i){var r;if(this.else=(r=this.else)===null||r===void 0?void 0:r.optimizeNames(e,i),!!(super.optimizeNames(e,i)||this.else))return this.condition=Er(this.condition,e,i),this}get names(){let e=super.names;return ll(e,this.condition),this.else&&Li(e,this.else.names),e}}return t.kind="if",t})(),dl=(()=>{class t extends zn{}return t.kind="for",t})(),Wd=class extends dl{constructor(n){super(),this.iteration=n}render(n){return`for(${this.iteration})`+super.render(n)}optimizeNames(n,e){if(super.optimizeNames(n,e))return this.iteration=Er(this.iteration,n,e),this}get names(){return Li(super.names,this.iteration.names)}},Kd=class extends dl{constructor(n,e,i,r){super(),this.varKind=n,this.name=e,this.from=i,this.to=r}render(n){let e=n.es5?sn.varKinds.var:this.varKind,{name:i,from:r,to:o}=this;return`for(${e} ${i}=${r}; ${i}<${o}; ${i}++)`+super.render(n)}get names(){let n=ll(super.names,this.from);return ll(n,this.to)}},sl=class extends dl{constructor(n,e,i,r){super(),this.loop=n,this.varKind=e,this.name=i,this.iterable=r}render(n){return`for(${this.varKind} ${this.name} ${this.loop} ${this.iterable})`+super.render(n)}optimizeNames(n,e){if(super.optimizeNames(n,e))return this.iterable=Er(this.iterable,n,e),this}get names(){return Li(super.names,this.iterable.names)}},lg=(()=>{class t extends zn{constructor(e,i,r){super(),this.name=e,this.args=i,this.async=r}render(e){return`${this.async?"async ":""}function ${this.name}(${this.args})`+super.render(e)}}return t.kind="func",t})(),cg=(()=>{class t extends ea{render(e){return"return "+super.render(e)}}return t.kind="return",t})(),Yd=class extends zn{render(n){let e="try"+super.render(n);return this.catch&&(e+=this.catch.render(n)),this.finally&&(e+=this.finally.render(n)),e}optimizeNodes(){var n,e;return super.optimizeNodes(),(n=this.catch)===null||n===void 0||n.optimizeNodes(),(e=this.finally)===null||e===void 0||e.optimizeNodes(),this}optimizeNames(n,e){var i,r;return super.optimizeNames(n,e),(i=this.catch)===null||i===void 0||i.optimizeNames(n,e),(r=this.finally)===null||r===void 0||r.optimizeNames(n,e),this}get names(){let n=super.names;return this.catch&&Li(n,this.catch.names),this.finally&&Li(n,this.finally.names),n}},dg=(()=>{class t extends zn{constructor(e){super(),this.error=e}render(e){return`catch(${this.error})`+super.render(e)}}return t.kind="catch",t})(),mg=(()=>{class t extends zn{render(e){return"finally"+super.render(e)}}return t.kind="finally",t})(),Qd=class{constructor(n,e={}){this._values={},this._blockStarts=[],this._constants={},this.opts=Ie(M({},e),{_n:e.lines?`
`:""}),this._extScope=n,this._scope=new sn.Scope({parent:n}),this._nodes=[new qd]}toString(){return this._root.render(this.opts)}name(n){return this._scope.name(n)}scopeName(n){return this._extScope.name(n)}scopeValue(n,e){let i=this._extScope.value(n,e);return(this._values[i.prefix]||(this._values[i.prefix]=new Set)).add(i),i}getScopeValue(n,e){return this._extScope.getValue(n,e)}scopeRefs(n){return this._extScope.scopeRefs(n,this._values)}scopeCode(){return this._extScope.scopeCode(this._values)}_def(n,e,i,r){let o=this._scope.toName(e);return i!==void 0&&r&&(this._constants[o.str]=i),this._leafNode(new $d(n,o,i)),o}const(n,e,i){return this._def(sn.varKinds.const,n,e,i)}let(n,e,i){return this._def(sn.varKinds.let,n,e,i)}var(n,e,i){return this._def(sn.varKinds.var,n,e,i)}assign(n,e,i){return this._leafNode(new al(n,e,i))}add(n,e){return this._leafNode(new jd(n,re.operators.ADD,e))}code(n){return typeof n=="function"?n():n!==me.nil&&this._leafNode(new Ud(n)),this}object(...n){let e=["{"];for(let[i,r]of n)e.length>1&&e.push(","),e.push(i),(i!==r||this.opts.es5)&&(e.push(":"),(0,me.addCodeArg)(e,r));return e.push("}"),new me._Code(e)}if(n,e,i){if(this._blockNode(new ol(n)),e&&i)this.code(e).else().code(i).endIf();else if(e)this.code(e).endIf();else if(i)throw new Error('CodeGen: "else" body without "then" body');return this}elseIf(n){return this._elseNode(new ol(n))}else(){return this._elseNode(new Gd)}endIf(){return this._endBlockNode(ol,Gd)}_for(n,e){return this._blockNode(n),e&&this.code(e).endFor(),this}for(n,e){return this._for(new Wd(n),e)}forRange(n,e,i,r,o=this.opts.es5?sn.varKinds.var:sn.varKinds.let){let a=this._scope.toName(n);return this._for(new Kd(o,a,e,i),()=>r(a))}forOf(n,e,i,r=sn.varKinds.const){let o=this._scope.toName(n);if(this.opts.es5){let a=e instanceof me.Name?e:this.var("_arr",e);return this.forRange("_i",0,(0,me._)`${a}.length`,s=>{this.var(o,(0,me._)`${a}[${s}]`),i(o)})}return this._for(new sl("of",r,o,e),()=>i(o))}forIn(n,e,i,r=this.opts.es5?sn.varKinds.var:sn.varKinds.const){if(this.opts.ownProperties)return this.forOf(n,(0,me._)`Object.keys(${e})`,i);let o=this._scope.toName(n);return this._for(new sl("in",r,o,e),()=>i(o))}endFor(){return this._endBlockNode(dl)}label(n){return this._leafNode(new Vd(n))}break(n){return this._leafNode(new zd(n))}return(n){let e=new cg;if(this._blockNode(e),this.code(n),e.nodes.length!==1)throw new Error('CodeGen: "return" should have one node');return this._endBlockNode(cg)}try(n,e,i){if(!e&&!i)throw new Error('CodeGen: "try" without "catch" and "finally"');let r=new Yd;if(this._blockNode(r),this.code(n),e){let o=this.name("e");this._currNode=r.catch=new dg(o),e(o)}return i&&(this._currNode=r.finally=new mg,this.code(i)),this._endBlockNode(dg,mg)}throw(n){return this._leafNode(new Hd(n))}block(n,e){return this._blockStarts.push(this._nodes.length),n&&this.code(n).endBlock(e),this}endBlock(n){let e=this._blockStarts.pop();if(e===void 0)throw new Error("CodeGen: not in self-balancing block");let i=this._nodes.length-e;if(i<0||n!==void 0&&i!==n)throw new Error(`CodeGen: wrong number of nodes: ${i} vs ${n} expected`);return this._nodes.length=e,this}func(n,e=me.nil,i,r){return this._blockNode(new lg(n,e,i)),r&&this.code(r).endFunc(),this}endFunc(){return this._endBlockNode(lg)}optimize(n=1){for(;n-- >0;)this._root.optimizeNodes(),this._root.optimizeNames(this._root.names,this._constants)}_leafNode(n){return this._currNode.nodes.push(n),this}_blockNode(n){this._currNode.nodes.push(n),this._nodes.push(n)}_endBlockNode(n,e){let i=this._currNode;if(i instanceof n||e&&i instanceof e)return this._nodes.pop(),this;throw new Error(`CodeGen: not in block "${e?`${n.kind}/${e.kind}`:n.kind}"`)}_elseNode(n){let e=this._currNode;if(!(e instanceof ol))throw new Error('CodeGen: "else" without "if"');return this._currNode=e.else=n,this}get _root(){return this._nodes[0]}get _currNode(){let n=this._nodes;return n[n.length-1]}set _currNode(n){let e=this._nodes;e[e.length-1]=n}};re.CodeGen=Qd;function Li(t,n){for(let e in n)t[e]=(t[e]||0)+(n[e]||0);return t}function ll(t,n){return n instanceof me._CodeOrName?Li(t,n.names):t}function Er(t,n,e){if(t instanceof me.Name)return i(t);if(!r(t))return t;return new me._Code(t._items.reduce((o,a)=>(a instanceof me.Name&&(a=i(a)),a instanceof me._Code?o.push(...a._items):o.push(a),o),[]));function i(o){let a=e[o.str];return a===void 0||n[o.str]!==1?o:(delete n[o.str],a)}function r(o){return o instanceof me._Code&&o._items.some(a=>a instanceof me.Name&&n[a.str]===1&&e[a.str]!==void 0)}}function kw(t,n){for(let e in n)t[e]=(t[e]||0)-(n[e]||0)}function ug(t){return typeof t=="boolean"||typeof t=="number"||t===null?!t:(0,me._)`!${Xd(t)}`}re.not=ug;var Dw=pg(re.operators.AND);function Ew(...t){return t.reduce(Dw)}re.and=Ew;var Sw=pg(re.operators.OR);function Mw(...t){return t.reduce(Sw)}re.or=Mw;function pg(t){return(n,e)=>n===me.nil?e:e===me.nil?n:(0,me._)`${Xd(n)} ${t} ${Xd(e)}`}function Xd(t){return t instanceof me.Name?t:(0,me._)`(${t})`}});var he=L(se=>{"use strict";Object.defineProperty(se,"__esModule",{value:!0});se.checkStrictMode=se.getErrorPath=se.Type=se.useFunc=se.setEvaluated=se.evaluatedPropsToName=se.mergeEvaluated=se.eachItem=se.unescapeJsonPointer=se.escapeJsonPointer=se.escapeFragment=se.unescapeFragment=se.schemaRefOrVal=se.schemaHasRulesButRef=se.schemaHasRules=se.checkUnknownRules=se.alwaysValidSchema=se.toHash=void 0;var Ae=ae(),Aw=Jo();function Iw(t){let n={};for(let e of t)n[e]=!0;return n}se.toHash=Iw;function Tw(t,n){return typeof n=="boolean"?n:Object.keys(n).length===0?!0:(gg(t,n),!_g(n,t.self.RULES.all))}se.alwaysValidSchema=Tw;function gg(t,n=t.schema){let{opts:e,self:i}=t;if(!e.strictSchema||typeof n=="boolean")return;let r=i.RULES.keywords;for(let o in n)r[o]||yg(t,`unknown keyword: "${o}"`)}se.checkUnknownRules=gg;function _g(t,n){if(typeof t=="boolean")return!t;for(let e in t)if(n[e])return!0;return!1}se.schemaHasRules=_g;function Ow(t,n){if(typeof t=="boolean")return!t;for(let e in t)if(e!=="$ref"&&n.all[e])return!0;return!1}se.schemaHasRulesButRef=Ow;function Rw({topSchemaRef:t,schemaPath:n},e,i,r){if(!r){if(typeof e=="number"||typeof e=="boolean")return e;if(typeof e=="string")return(0,Ae._)`${e}`}return(0,Ae._)`${t}${n}${(0,Ae.getProperty)(i)}`}se.schemaRefOrVal=Rw;function Pw(t){return bg(decodeURIComponent(t))}se.unescapeFragment=Pw;function Fw(t){return encodeURIComponent(Jd(t))}se.escapeFragment=Fw;function Jd(t){return typeof t=="number"?`${t}`:t.replace(/~/g,"~0").replace(/\//g,"~1")}se.escapeJsonPointer=Jd;function bg(t){return t.replace(/~1/g,"/").replace(/~0/g,"~")}se.unescapeJsonPointer=bg;function Nw(t,n){if(Array.isArray(t))for(let e of t)n(e);else n(t)}se.eachItem=Nw;function hg({mergeNames:t,mergeToName:n,mergeValues:e,resultToName:i}){return(r,o,a,s)=>{let l=a===void 0?o:a instanceof Ae.Name?(o instanceof Ae.Name?t(r,o,a):n(r,o,a),a):o instanceof Ae.Name?(n(r,a,o),o):e(o,a);return s===Ae.Name&&!(l instanceof Ae.Name)?i(r,l):l}}se.mergeEvaluated={props:hg({mergeNames:(t,n,e)=>t.if((0,Ae._)`${e} !== true && ${n} !== undefined`,()=>{t.if((0,Ae._)`${n} === true`,()=>t.assign(e,!0),()=>t.assign(e,(0,Ae._)`${e} || {}`).code((0,Ae._)`Object.assign(${e}, ${n})`))}),mergeToName:(t,n,e)=>t.if((0,Ae._)`${e} !== true`,()=>{n===!0?t.assign(e,!0):(t.assign(e,(0,Ae._)`${e} || {}`),em(t,e,n))}),mergeValues:(t,n)=>t===!0?!0:M(M({},t),n),resultToName:vg}),items:hg({mergeNames:(t,n,e)=>t.if((0,Ae._)`${e} !== true && ${n} !== undefined`,()=>t.assign(e,(0,Ae._)`${n} === true ? true : ${e} > ${n} ? ${e} : ${n}`)),mergeToName:(t,n,e)=>t.if((0,Ae._)`${e} !== true`,()=>t.assign(e,n===!0?!0:(0,Ae._)`${e} > ${n} ? ${e} : ${n}`)),mergeValues:(t,n)=>t===!0?!0:Math.max(t,n),resultToName:(t,n)=>t.var("items",n)})};function vg(t,n){if(n===!0)return t.var("props",!0);let e=t.var("props",(0,Ae._)`{}`);return n!==void 0&&em(t,e,n),e}se.evaluatedPropsToName=vg;function em(t,n,e){Object.keys(e).forEach(i=>t.assign((0,Ae._)`${n}${(0,Ae.getProperty)(i)}`,!0))}se.setEvaluated=em;var fg={};function Lw(t,n){return t.scopeValue("func",{ref:n,code:fg[n.code]||(fg[n.code]=new Aw._Code(n.code))})}se.useFunc=Lw;var Zd=(function(t){return t[t.Num=0]="Num",t[t.Str=1]="Str",t})(Zd||(se.Type=Zd={}));function Bw(t,n,e){if(t instanceof Ae.Name){let i=n===Zd.Num;return e?i?(0,Ae._)`"[" + ${t} + "]"`:(0,Ae._)`"['" + ${t} + "']"`:i?(0,Ae._)`"/" + ${t}`:(0,Ae._)`"/" + ${t}.replace(/~/g, "~0").replace(/\\//g, "~1")`}return e?(0,Ae.getProperty)(t).toString():"/"+Jd(t)}se.getErrorPath=Bw;function yg(t,n,e=t.opts.strictSchema){if(e){if(n=`strict mode: ${n}`,e===!0)throw new Error(n);t.self.logger.warn(n)}}se.checkStrictMode=yg});var Hn=L(tm=>{"use strict";Object.defineProperty(tm,"__esModule",{value:!0});var ht=ae(),$w={data:new ht.Name("data"),valCxt:new ht.Name("valCxt"),instancePath:new ht.Name("instancePath"),parentData:new ht.Name("parentData"),parentDataProperty:new ht.Name("parentDataProperty"),rootData:new ht.Name("rootData"),dynamicAnchors:new ht.Name("dynamicAnchors"),vErrors:new ht.Name("vErrors"),errors:new ht.Name("errors"),this:new ht.Name("this"),self:new ht.Name("self"),scope:new ht.Name("scope"),json:new ht.Name("json"),jsonPos:new ht.Name("jsonPos"),jsonLen:new ht.Name("jsonLen"),jsonPart:new ht.Name("jsonPart")};tm.default=$w});var ta=L(ft=>{"use strict";Object.defineProperty(ft,"__esModule",{value:!0});ft.extendErrors=ft.resetErrorsCount=ft.reportExtraError=ft.reportError=ft.keyword$DataError=ft.keywordError=void 0;var fe=ae(),ml=he(),Dt=Hn();ft.keywordError={message:({keyword:t})=>(0,fe.str)`must pass "${t}" keyword validation`};ft.keyword$DataError={message:({keyword:t,schemaType:n})=>n?(0,fe.str)`"${t}" keyword must be ${n} ($data)`:(0,fe.str)`"${t}" keyword is invalid ($data)`};function jw(t,n=ft.keywordError,e,i){let{it:r}=t,{gen:o,compositeRule:a,allErrors:s}=r,l=Cg(t,n,e);i??(a||s)?xg(o,l):wg(r,(0,fe._)`[${l}]`)}ft.reportError=jw;function Vw(t,n=ft.keywordError,e){let{it:i}=t,{gen:r,compositeRule:o,allErrors:a}=i,s=Cg(t,n,e);xg(r,s),o||a||wg(i,Dt.default.vErrors)}ft.reportExtraError=Vw;function zw(t,n){t.assign(Dt.default.errors,n),t.if((0,fe._)`${Dt.default.vErrors} !== null`,()=>t.if(n,()=>t.assign((0,fe._)`${Dt.default.vErrors}.length`,n),()=>t.assign(Dt.default.vErrors,null)))}ft.resetErrorsCount=zw;function Hw({gen:t,keyword:n,schemaValue:e,data:i,errsCount:r,it:o}){if(r===void 0)throw new Error("ajv implementation error");let a=t.name("err");t.forRange("i",r,Dt.default.errors,s=>{t.const(a,(0,fe._)`${Dt.default.vErrors}[${s}]`),t.if((0,fe._)`${a}.instancePath === undefined`,()=>t.assign((0,fe._)`${a}.instancePath`,(0,fe.strConcat)(Dt.default.instancePath,o.errorPath))),t.assign((0,fe._)`${a}.schemaPath`,(0,fe.str)`${o.errSchemaPath}/${n}`),o.opts.verbose&&(t.assign((0,fe._)`${a}.schema`,e),t.assign((0,fe._)`${a}.data`,i))})}ft.extendErrors=Hw;function xg(t,n){let e=t.const("err",n);t.if((0,fe._)`${Dt.default.vErrors} === null`,()=>t.assign(Dt.default.vErrors,(0,fe._)`[${e}]`),(0,fe._)`${Dt.default.vErrors}.push(${e})`),t.code((0,fe._)`${Dt.default.errors}++`)}function wg(t,n){let{gen:e,validateName:i,schemaEnv:r}=t;r.$async?e.throw((0,fe._)`new ${t.ValidationError}(${n})`):(e.assign((0,fe._)`${i}.errors`,n),e.return(!1))}var Bi={keyword:new fe.Name("keyword"),schemaPath:new fe.Name("schemaPath"),params:new fe.Name("params"),propertyName:new fe.Name("propertyName"),message:new fe.Name("message"),schema:new fe.Name("schema"),parentSchema:new fe.Name("parentSchema")};function Cg(t,n,e){let{createErrors:i}=t.it;return i===!1?(0,fe._)`{}`:Uw(t,n,e)}function Uw(t,n,e={}){let{gen:i,it:r}=t,o=[qw(r,e),Gw(t,e)];return Ww(t,n,o),i.object(...o)}function qw({errorPath:t},{instancePath:n}){let e=n?(0,fe.str)`${t}${(0,ml.getErrorPath)(n,ml.Type.Str)}`:t;return[Dt.default.instancePath,(0,fe.strConcat)(Dt.default.instancePath,e)]}function Gw({keyword:t,it:{errSchemaPath:n}},{schemaPath:e,parentSchema:i}){let r=i?n:(0,fe.str)`${n}/${t}`;return e&&(r=(0,fe.str)`${r}${(0,ml.getErrorPath)(e,ml.Type.Str)}`),[Bi.schemaPath,r]}function Ww(t,{params:n,message:e},i){let{keyword:r,data:o,schemaValue:a,it:s}=t,{opts:l,propertyName:c,topSchemaRef:d,schemaPath:u}=s;i.push([Bi.keyword,r],[Bi.params,typeof n=="function"?n(t):n||(0,fe._)`{}`]),l.messages&&i.push([Bi.message,typeof e=="function"?e(t):e]),l.verbose&&i.push([Bi.schema,a],[Bi.parentSchema,(0,fe._)`${d}${u}`],[Dt.default.data,o]),c&&i.push([Bi.propertyName,c])}});var Dg=L(Sr=>{"use strict";Object.defineProperty(Sr,"__esModule",{value:!0});Sr.boolOrEmptySchema=Sr.topBoolOrEmptySchema=void 0;var Kw=ta(),Yw=ae(),Qw=Hn(),Xw={message:"boolean schema is false"};function Zw(t){let{gen:n,schema:e,validateName:i}=t;e===!1?kg(t,!1):typeof e=="object"&&e.$async===!0?n.return(Qw.default.data):(n.assign((0,Yw._)`${i}.errors`,null),n.return(!0))}Sr.topBoolOrEmptySchema=Zw;function Jw(t,n){let{gen:e,schema:i}=t;i===!1?(e.var(n,!1),kg(t)):e.var(n,!0)}Sr.boolOrEmptySchema=Jw;function kg(t,n){let{gen:e,data:i}=t,r={gen:e,keyword:"false schema",data:i,schema:!1,schemaCode:!1,schemaValue:!1,params:{},it:t};(0,Kw.reportError)(r,Xw,void 0,n)}});var nm=L(Mr=>{"use strict";Object.defineProperty(Mr,"__esModule",{value:!0});Mr.getRules=Mr.isJSONType=void 0;var eC=["string","number","integer","boolean","null","object","array"],tC=new Set(eC);function nC(t){return typeof t=="string"&&tC.has(t)}Mr.isJSONType=nC;function iC(){let t={number:{type:"number",rules:[]},string:{type:"string",rules:[]},array:{type:"array",rules:[]},object:{type:"object",rules:[]}};return{types:Ie(M({},t),{integer:!0,boolean:!0,null:!0}),rules:[{rules:[]},t.number,t.string,t.array,t.object],post:{rules:[]},all:{},keywords:{}}}Mr.getRules=iC});var im=L(ci=>{"use strict";Object.defineProperty(ci,"__esModule",{value:!0});ci.shouldUseRule=ci.shouldUseGroup=ci.schemaHasRulesForType=void 0;function rC({schema:t,self:n},e){let i=n.RULES.types[e];return i&&i!==!0&&Eg(t,i)}ci.schemaHasRulesForType=rC;function Eg(t,n){return n.rules.some(e=>Sg(t,e))}ci.shouldUseGroup=Eg;function Sg(t,n){var e;return t[n.keyword]!==void 0||((e=n.definition.implements)===null||e===void 0?void 0:e.some(i=>t[i]!==void 0))}ci.shouldUseRule=Sg});var na=L(gt=>{"use strict";Object.defineProperty(gt,"__esModule",{value:!0});gt.reportTypeError=gt.checkDataTypes=gt.checkDataType=gt.coerceAndCheckDataType=gt.getJSONTypes=gt.getSchemaTypes=gt.DataType=void 0;var oC=nm(),aC=im(),sC=ta(),ee=ae(),Mg=he(),Ar=(function(t){return t[t.Correct=0]="Correct",t[t.Wrong=1]="Wrong",t})(Ar||(gt.DataType=Ar={}));function lC(t){let n=Ag(t.type);if(n.includes("null")){if(t.nullable===!1)throw new Error("type: null contradicts nullable: false")}else{if(!n.length&&t.nullable!==void 0)throw new Error('"nullable" cannot be used without "type"');t.nullable===!0&&n.push("null")}return n}gt.getSchemaTypes=lC;function Ag(t){let n=Array.isArray(t)?t:t?[t]:[];if(n.every(oC.isJSONType))return n;throw new Error("type must be JSONType or JSONType[]: "+n.join(","))}gt.getJSONTypes=Ag;function cC(t,n){let{gen:e,data:i,opts:r}=t,o=dC(n,r.coerceTypes),a=n.length>0&&!(o.length===0&&n.length===1&&(0,aC.schemaHasRulesForType)(t,n[0]));if(a){let s=om(n,i,r.strictNumbers,Ar.Wrong);e.if(s,()=>{o.length?mC(t,n,o):am(t)})}return a}gt.coerceAndCheckDataType=cC;var Ig=new Set(["string","number","integer","boolean","null"]);function dC(t,n){return n?t.filter(e=>Ig.has(e)||n==="array"&&e==="array"):[]}function mC(t,n,e){let{gen:i,data:r,opts:o}=t,a=i.let("dataType",(0,ee._)`typeof ${r}`),s=i.let("coerced",(0,ee._)`undefined`);o.coerceTypes==="array"&&i.if((0,ee._)`${a} == 'object' && Array.isArray(${r}) && ${r}.length == 1`,()=>i.assign(r,(0,ee._)`${r}[0]`).assign(a,(0,ee._)`typeof ${r}`).if(om(n,r,o.strictNumbers),()=>i.assign(s,r))),i.if((0,ee._)`${s} !== undefined`);for(let c of e)(Ig.has(c)||c==="array"&&o.coerceTypes==="array")&&l(c);i.else(),am(t),i.endIf(),i.if((0,ee._)`${s} !== undefined`,()=>{i.assign(r,s),uC(t,s)});function l(c){switch(c){case"string":i.elseIf((0,ee._)`${a} == "number" || ${a} == "boolean"`).assign(s,(0,ee._)`"" + ${r}`).elseIf((0,ee._)`${r} === null`).assign(s,(0,ee._)`""`);return;case"number":i.elseIf((0,ee._)`${a} == "boolean" || ${r} === null
              || (${a} == "string" && ${r} && ${r} == +${r})`).assign(s,(0,ee._)`+${r}`);return;case"integer":i.elseIf((0,ee._)`${a} === "boolean" || ${r} === null
              || (${a} === "string" && ${r} && ${r} == +${r} && !(${r} % 1))`).assign(s,(0,ee._)`+${r}`);return;case"boolean":i.elseIf((0,ee._)`${r} === "false" || ${r} === 0 || ${r} === null`).assign(s,!1).elseIf((0,ee._)`${r} === "true" || ${r} === 1`).assign(s,!0);return;case"null":i.elseIf((0,ee._)`${r} === "" || ${r} === 0 || ${r} === false`),i.assign(s,null);return;case"array":i.elseIf((0,ee._)`${a} === "string" || ${a} === "number"
              || ${a} === "boolean" || ${r} === null`).assign(s,(0,ee._)`[${r}]`)}}}function uC({gen:t,parentData:n,parentDataProperty:e},i){t.if((0,ee._)`${n} !== undefined`,()=>t.assign((0,ee._)`${n}[${e}]`,i))}function rm(t,n,e,i=Ar.Correct){let r=i===Ar.Correct?ee.operators.EQ:ee.operators.NEQ,o;switch(t){case"null":return(0,ee._)`${n} ${r} null`;case"array":o=(0,ee._)`Array.isArray(${n})`;break;case"object":o=(0,ee._)`${n} && typeof ${n} == "object" && !Array.isArray(${n})`;break;case"integer":o=a((0,ee._)`!(${n} % 1) && !isNaN(${n})`);break;case"number":o=a();break;default:return(0,ee._)`typeof ${n} ${r} ${t}`}return i===Ar.Correct?o:(0,ee.not)(o);function a(s=ee.nil){return(0,ee.and)((0,ee._)`typeof ${n} == "number"`,s,e?(0,ee._)`isFinite(${n})`:ee.nil)}}gt.checkDataType=rm;function om(t,n,e,i){if(t.length===1)return rm(t[0],n,e,i);let r,o=(0,Mg.toHash)(t);if(o.array&&o.object){let a=(0,ee._)`typeof ${n} != "object"`;r=o.null?a:(0,ee._)`!${n} || ${a}`,delete o.null,delete o.array,delete o.object}else r=ee.nil;o.number&&delete o.integer;for(let a in o)r=(0,ee.and)(r,rm(a,n,e,i));return r}gt.checkDataTypes=om;var pC={message:({schema:t})=>`must be ${t}`,params:({schema:t,schemaValue:n})=>typeof t=="string"?(0,ee._)`{type: ${t}}`:(0,ee._)`{type: ${n}}`};function am(t){let n=hC(t);(0,sC.reportError)(n,pC)}gt.reportTypeError=am;function hC(t){let{gen:n,data:e,schema:i}=t,r=(0,Mg.schemaRefOrVal)(t,i,"type");return{gen:n,keyword:"type",data:e,schema:i.type,schemaCode:r,schemaValue:r,parentSchema:i,params:{},it:t}}});var Og=L(ul=>{"use strict";Object.defineProperty(ul,"__esModule",{value:!0});ul.assignDefaults=void 0;var Ir=ae(),fC=he();function gC(t,n){let{properties:e,items:i}=t.schema;if(n==="object"&&e)for(let r in e)Tg(t,r,e[r].default);else n==="array"&&Array.isArray(i)&&i.forEach((r,o)=>Tg(t,o,r.default))}ul.assignDefaults=gC;function Tg(t,n,e){let{gen:i,compositeRule:r,data:o,opts:a}=t;if(e===void 0)return;let s=(0,Ir._)`${o}${(0,Ir.getProperty)(n)}`;if(r){(0,fC.checkStrictMode)(t,`default is ignored for: ${s}`);return}let l=(0,Ir._)`${s} === undefined`;a.useDefaults==="empty"&&(l=(0,Ir._)`${l} || ${s} === null || ${s} === ""`),i.if(l,(0,Ir._)`${s} = ${(0,Ir.stringify)(e)}`)}});var Wt=L(Me=>{"use strict";Object.defineProperty(Me,"__esModule",{value:!0});Me.validateUnion=Me.validateArray=Me.usePattern=Me.callValidateCode=Me.schemaProperties=Me.allSchemaProperties=Me.noPropertyInData=Me.propertyInData=Me.isOwnProperty=Me.hasPropFunc=Me.reportMissingProp=Me.checkMissingProp=Me.checkReportMissingProp=void 0;var Oe=ae(),sm=he(),di=Hn(),_C=he();function bC(t,n){let{gen:e,data:i,it:r}=t;e.if(cm(e,i,n,r.opts.ownProperties),()=>{t.setParams({missingProperty:(0,Oe._)`${n}`},!0),t.error()})}Me.checkReportMissingProp=bC;function vC({gen:t,data:n,it:{opts:e}},i,r){return(0,Oe.or)(...i.map(o=>(0,Oe.and)(cm(t,n,o,e.ownProperties),(0,Oe._)`${r} = ${o}`)))}Me.checkMissingProp=vC;function yC(t,n){t.setParams({missingProperty:n},!0),t.error()}Me.reportMissingProp=yC;function Rg(t){return t.scopeValue("func",{ref:Object.prototype.hasOwnProperty,code:(0,Oe._)`Object.prototype.hasOwnProperty`})}Me.hasPropFunc=Rg;function lm(t,n,e){return(0,Oe._)`${Rg(t)}.call(${n}, ${e})`}Me.isOwnProperty=lm;function xC(t,n,e,i){let r=(0,Oe._)`${n}${(0,Oe.getProperty)(e)} !== undefined`;return i?(0,Oe._)`${r} && ${lm(t,n,e)}`:r}Me.propertyInData=xC;function cm(t,n,e,i){let r=(0,Oe._)`${n}${(0,Oe.getProperty)(e)} === undefined`;return i?(0,Oe.or)(r,(0,Oe.not)(lm(t,n,e))):r}Me.noPropertyInData=cm;function Pg(t){return t?Object.keys(t).filter(n=>n!=="__proto__"):[]}Me.allSchemaProperties=Pg;function wC(t,n){return Pg(n).filter(e=>!(0,sm.alwaysValidSchema)(t,n[e]))}Me.schemaProperties=wC;function CC({schemaCode:t,data:n,it:{gen:e,topSchemaRef:i,schemaPath:r,errorPath:o},it:a},s,l,c){let d=c?(0,Oe._)`${t}, ${n}, ${i}${r}`:n,u=[[di.default.instancePath,(0,Oe.strConcat)(di.default.instancePath,o)],[di.default.parentData,a.parentData],[di.default.parentDataProperty,a.parentDataProperty],[di.default.rootData,di.default.rootData]];a.opts.dynamicRef&&u.push([di.default.dynamicAnchors,di.default.dynamicAnchors]);let p=(0,Oe._)`${d}, ${e.object(...u)}`;return l!==Oe.nil?(0,Oe._)`${s}.call(${l}, ${p})`:(0,Oe._)`${s}(${p})`}Me.callValidateCode=CC;var kC=(0,Oe._)`new RegExp`;function DC({gen:t,it:{opts:n}},e){let i=n.unicodeRegExp?"u":"",{regExp:r}=n.code,o=r(e,i);return t.scopeValue("pattern",{key:o.toString(),ref:o,code:(0,Oe._)`${r.code==="new RegExp"?kC:(0,_C.useFunc)(t,r)}(${e}, ${i})`})}Me.usePattern=DC;function EC(t){let{gen:n,data:e,keyword:i,it:r}=t,o=n.name("valid");if(r.allErrors){let s=n.let("valid",!0);return a(()=>n.assign(s,!1)),s}return n.var(o,!0),a(()=>n.break()),o;function a(s){let l=n.const("len",(0,Oe._)`${e}.length`);n.forRange("i",0,l,c=>{t.subschema({keyword:i,dataProp:c,dataPropType:sm.Type.Num},o),n.if((0,Oe.not)(o),s)})}}Me.validateArray=EC;function SC(t){let{gen:n,schema:e,keyword:i,it:r}=t;if(!Array.isArray(e))throw new Error("ajv implementation error");if(e.some(l=>(0,sm.alwaysValidSchema)(r,l))&&!r.opts.unevaluated)return;let a=n.let("valid",!1),s=n.name("_valid");n.block(()=>e.forEach((l,c)=>{let d=t.subschema({keyword:i,schemaProp:c,compositeRule:!0},s);n.assign(a,(0,Oe._)`${a} || ${s}`),t.mergeValidEvaluated(d,s)||n.if((0,Oe.not)(a))})),t.result(a,()=>t.reset(),()=>t.error(!0))}Me.validateUnion=SC});var Lg=L(Cn=>{"use strict";Object.defineProperty(Cn,"__esModule",{value:!0});Cn.validateKeywordUsage=Cn.validSchemaType=Cn.funcKeywordCode=Cn.macroKeywordCode=void 0;var Et=ae(),$i=Hn(),MC=Wt(),AC=ta();function IC(t,n){let{gen:e,keyword:i,schema:r,parentSchema:o,it:a}=t,s=n.macro.call(a.self,r,o,a),l=Ng(e,i,s);a.opts.validateSchema!==!1&&a.self.validateSchema(s,!0);let c=e.name("valid");t.subschema({schema:s,schemaPath:Et.nil,errSchemaPath:`${a.errSchemaPath}/${i}`,topSchemaRef:l,compositeRule:!0},c),t.pass(c,()=>t.error(!0))}Cn.macroKeywordCode=IC;function TC(t,n){var e;let{gen:i,keyword:r,schema:o,parentSchema:a,$data:s,it:l}=t;RC(l,n);let c=!s&&n.compile?n.compile.call(l.self,o,a,l):n.validate,d=Ng(i,r,c),u=i.let("valid");t.block$data(u,p),t.ok((e=n.valid)!==null&&e!==void 0?e:u);function p(){if(n.errors===!1)_(),n.modifying&&Fg(t),v(()=>t.error());else{let x=n.async?y():g();n.modifying&&Fg(t),v(()=>OC(t,x))}}function y(){let x=i.let("ruleErrs",null);return i.try(()=>_((0,Et._)`await `),S=>i.assign(u,!1).if((0,Et._)`${S} instanceof ${l.ValidationError}`,()=>i.assign(x,(0,Et._)`${S}.errors`),()=>i.throw(S))),x}function g(){let x=(0,Et._)`${d}.errors`;return i.assign(x,null),_(Et.nil),x}function _(x=n.async?(0,Et._)`await `:Et.nil){let S=l.opts.passContext?$i.default.this:$i.default.self,I=!("compile"in n&&!s||n.schema===!1);i.assign(u,(0,Et._)`${x}${(0,MC.callValidateCode)(t,d,S,I)}`,n.modifying)}function v(x){var S;i.if((0,Et.not)((S=n.valid)!==null&&S!==void 0?S:u),x)}}Cn.funcKeywordCode=TC;function Fg(t){let{gen:n,data:e,it:i}=t;n.if(i.parentData,()=>n.assign(e,(0,Et._)`${i.parentData}[${i.parentDataProperty}]`))}function OC(t,n){let{gen:e}=t;e.if((0,Et._)`Array.isArray(${n})`,()=>{e.assign($i.default.vErrors,(0,Et._)`${$i.default.vErrors} === null ? ${n} : ${$i.default.vErrors}.concat(${n})`).assign($i.default.errors,(0,Et._)`${$i.default.vErrors}.length`),(0,AC.extendErrors)(t)},()=>t.error())}function RC({schemaEnv:t},n){if(n.async&&!t.$async)throw new Error("async keyword in sync schema")}function Ng(t,n,e){if(e===void 0)throw new Error(`keyword "${n}" failed to compile`);return t.scopeValue("keyword",typeof e=="function"?{ref:e}:{ref:e,code:(0,Et.stringify)(e)})}function PC(t,n,e=!1){return!n.length||n.some(i=>i==="array"?Array.isArray(t):i==="object"?t&&typeof t=="object"&&!Array.isArray(t):typeof t==i||e&&typeof t>"u")}Cn.validSchemaType=PC;function FC({schema:t,opts:n,self:e,errSchemaPath:i},r,o){if(Array.isArray(r.keyword)?!r.keyword.includes(o):r.keyword!==o)throw new Error("ajv implementation error");let a=r.dependencies;if(a?.some(s=>!Object.prototype.hasOwnProperty.call(t,s)))throw new Error(`parent schema must have dependencies of ${o}: ${a.join(",")}`);if(r.validateSchema&&!r.validateSchema(t[o])){let l=`keyword "${o}" value is invalid at path "${i}": `+e.errorsText(r.validateSchema.errors);if(n.validateSchema==="log")e.logger.error(l);else throw new Error(l)}}Cn.validateKeywordUsage=FC});var $g=L(mi=>{"use strict";Object.defineProperty(mi,"__esModule",{value:!0});mi.extendSubschemaMode=mi.extendSubschemaData=mi.getSubschema=void 0;var kn=ae(),Bg=he();function NC(t,{keyword:n,schemaProp:e,schema:i,schemaPath:r,errSchemaPath:o,topSchemaRef:a}){if(n!==void 0&&i!==void 0)throw new Error('both "keyword" and "schema" passed, only one allowed');if(n!==void 0){let s=t.schema[n];return e===void 0?{schema:s,schemaPath:(0,kn._)`${t.schemaPath}${(0,kn.getProperty)(n)}`,errSchemaPath:`${t.errSchemaPath}/${n}`}:{schema:s[e],schemaPath:(0,kn._)`${t.schemaPath}${(0,kn.getProperty)(n)}${(0,kn.getProperty)(e)}`,errSchemaPath:`${t.errSchemaPath}/${n}/${(0,Bg.escapeFragment)(e)}`}}if(i!==void 0){if(r===void 0||o===void 0||a===void 0)throw new Error('"schemaPath", "errSchemaPath" and "topSchemaRef" are required with "schema"');return{schema:i,schemaPath:r,topSchemaRef:a,errSchemaPath:o}}throw new Error('either "keyword" or "schema" must be passed')}mi.getSubschema=NC;function LC(t,n,{dataProp:e,dataPropType:i,data:r,dataTypes:o,propertyName:a}){if(r!==void 0&&e!==void 0)throw new Error('both "data" and "dataProp" passed, only one allowed');let{gen:s}=n;if(e!==void 0){let{errorPath:c,dataPathArr:d,opts:u}=n,p=s.let("data",(0,kn._)`${n.data}${(0,kn.getProperty)(e)}`,!0);l(p),t.errorPath=(0,kn.str)`${c}${(0,Bg.getErrorPath)(e,i,u.jsPropertySyntax)}`,t.parentDataProperty=(0,kn._)`${e}`,t.dataPathArr=[...d,t.parentDataProperty]}if(r!==void 0){let c=r instanceof kn.Name?r:s.let("data",r,!0);l(c),a!==void 0&&(t.propertyName=a)}o&&(t.dataTypes=o);function l(c){t.data=c,t.dataLevel=n.dataLevel+1,t.dataTypes=[],n.definedProperties=new Set,t.parentData=n.data,t.dataNames=[...n.dataNames,c]}}mi.extendSubschemaData=LC;function BC(t,{jtdDiscriminator:n,jtdMetadata:e,compositeRule:i,createErrors:r,allErrors:o}){i!==void 0&&(t.compositeRule=i),r!==void 0&&(t.createErrors=r),o!==void 0&&(t.allErrors=o),t.jtdDiscriminator=n,t.jtdMetadata=e}mi.extendSubschemaMode=BC});var dm=L((S$,jg)=>{"use strict";jg.exports=function t(n,e){if(n===e)return!0;if(n&&e&&typeof n=="object"&&typeof e=="object"){if(n.constructor!==e.constructor)return!1;var i,r,o;if(Array.isArray(n)){if(i=n.length,i!=e.length)return!1;for(r=i;r--!==0;)if(!t(n[r],e[r]))return!1;return!0}if(n.constructor===RegExp)return n.source===e.source&&n.flags===e.flags;if(n.valueOf!==Object.prototype.valueOf)return n.valueOf()===e.valueOf();if(n.toString!==Object.prototype.toString)return n.toString()===e.toString();if(o=Object.keys(n),i=o.length,i!==Object.keys(e).length)return!1;for(r=i;r--!==0;)if(!Object.prototype.hasOwnProperty.call(e,o[r]))return!1;for(r=i;r--!==0;){var a=o[r];if(!t(n[a],e[a]))return!1}return!0}return n!==n&&e!==e}});var zg=L((M$,Vg)=>{"use strict";var ui=Vg.exports=function(t,n,e){typeof n=="function"&&(e=n,n={}),e=n.cb||e;var i=typeof e=="function"?e:e.pre||function(){},r=e.post||function(){};pl(n,i,r,t,"",t)};ui.keywords={additionalItems:!0,items:!0,contains:!0,additionalProperties:!0,propertyNames:!0,not:!0,if:!0,then:!0,else:!0};ui.arrayKeywords={items:!0,allOf:!0,anyOf:!0,oneOf:!0};ui.propsKeywords={$defs:!0,definitions:!0,properties:!0,patternProperties:!0,dependencies:!0};ui.skipKeywords={default:!0,enum:!0,const:!0,required:!0,maximum:!0,minimum:!0,exclusiveMaximum:!0,exclusiveMinimum:!0,multipleOf:!0,maxLength:!0,minLength:!0,pattern:!0,format:!0,maxItems:!0,minItems:!0,uniqueItems:!0,maxProperties:!0,minProperties:!0};function pl(t,n,e,i,r,o,a,s,l,c){if(i&&typeof i=="object"&&!Array.isArray(i)){n(i,r,o,a,s,l,c);for(var d in i){var u=i[d];if(Array.isArray(u)){if(d in ui.arrayKeywords)for(var p=0;p<u.length;p++)pl(t,n,e,u[p],r+"/"+d+"/"+p,o,r,d,i,p)}else if(d in ui.propsKeywords){if(u&&typeof u=="object")for(var y in u)pl(t,n,e,u[y],r+"/"+d+"/"+$C(y),o,r,d,i,y)}else(d in ui.keywords||t.allKeys&&!(d in ui.skipKeywords))&&pl(t,n,e,u,r+"/"+d,o,r,d,i)}e(i,r,o,a,s,l,c)}}function $C(t){return t.replace(/~/g,"~0").replace(/\//g,"~1")}});var ia=L(Ot=>{"use strict";Object.defineProperty(Ot,"__esModule",{value:!0});Ot.getSchemaRefs=Ot.resolveUrl=Ot.normalizeId=Ot._getFullPath=Ot.getFullPath=Ot.inlineRef=void 0;var jC=he(),VC=dm(),zC=zg(),HC=new Set(["type","format","pattern","maxLength","minLength","maxProperties","minProperties","maxItems","minItems","maximum","minimum","uniqueItems","multipleOf","required","enum","const"]);function UC(t,n=!0){return typeof t=="boolean"?!0:n===!0?!mm(t):n?Hg(t)<=n:!1}Ot.inlineRef=UC;var qC=new Set(["$ref","$recursiveRef","$recursiveAnchor","$dynamicRef","$dynamicAnchor"]);function mm(t){for(let n in t){if(qC.has(n))return!0;let e=t[n];if(Array.isArray(e)&&e.some(mm)||typeof e=="object"&&mm(e))return!0}return!1}function Hg(t){let n=0;for(let e in t){if(e==="$ref")return 1/0;if(n++,!HC.has(e)&&(typeof t[e]=="object"&&(0,jC.eachItem)(t[e],i=>n+=Hg(i)),n===1/0))return 1/0}return n}function Ug(t,n="",e){e!==!1&&(n=Tr(n));let i=t.parse(n);return qg(t,i)}Ot.getFullPath=Ug;function qg(t,n){return t.serialize(n).split("#")[0]+"#"}Ot._getFullPath=qg;var GC=/#\/?$/;function Tr(t){return t?t.replace(GC,""):""}Ot.normalizeId=Tr;function WC(t,n,e){return e=Tr(e),t.resolve(n,e)}Ot.resolveUrl=WC;var KC=/^[a-z_][-a-z0-9._]*$/i;function YC(t,n){if(typeof t=="boolean")return{};let{schemaId:e,uriResolver:i}=this.opts,r=Tr(t[e]||n),o={"":r},a=Ug(i,r,!1),s={},l=new Set;return zC(t,{allKeys:!0},(u,p,y,g)=>{if(g===void 0)return;let _=a+p,v=o[g];typeof u[e]=="string"&&(v=x.call(this,u[e])),S.call(this,u.$anchor),S.call(this,u.$dynamicAnchor),o[p]=v;function x(I){let F=this.opts.uriResolver.resolve;if(I=Tr(v?F(v,I):I),l.has(I))throw d(I);l.add(I);let N=this.refs[I];return typeof N=="string"&&(N=this.refs[N]),typeof N=="object"?c(u,N.schema,I):I!==Tr(_)&&(I[0]==="#"?(c(u,s[I],I),s[I]=u):this.refs[I]=_),I}function S(I){if(typeof I=="string"){if(!KC.test(I))throw new Error(`invalid anchor "${I}"`);x.call(this,`#${I}`)}}}),s;function c(u,p,y){if(p!==void 0&&!VC(u,p))throw d(y)}function d(u){return new Error(`reference "${u}" resolves to more than one schema`)}}Ot.getSchemaRefs=YC});var aa=L(pi=>{"use strict";Object.defineProperty(pi,"__esModule",{value:!0});pi.getData=pi.KeywordCxt=pi.validateFunctionCode=void 0;var Qg=Dg(),Gg=na(),pm=im(),hl=na(),QC=Og(),oa=Lg(),um=$g(),q=ae(),Z=Hn(),XC=ia(),Un=he(),ra=ta();function ZC(t){if(Jg(t)&&(e_(t),Zg(t))){tk(t);return}Xg(t,()=>(0,Qg.topBoolOrEmptySchema)(t))}pi.validateFunctionCode=ZC;function Xg({gen:t,validateName:n,schema:e,schemaEnv:i,opts:r},o){r.code.es5?t.func(n,(0,q._)`${Z.default.data}, ${Z.default.valCxt}`,i.$async,()=>{t.code((0,q._)`"use strict"; ${Wg(e,r)}`),ek(t,r),t.code(o)}):t.func(n,(0,q._)`${Z.default.data}, ${JC(r)}`,i.$async,()=>t.code(Wg(e,r)).code(o))}function JC(t){return(0,q._)`{${Z.default.instancePath}="", ${Z.default.parentData}, ${Z.default.parentDataProperty}, ${Z.default.rootData}=${Z.default.data}${t.dynamicRef?(0,q._)`, ${Z.default.dynamicAnchors}={}`:q.nil}}={}`}function ek(t,n){t.if(Z.default.valCxt,()=>{t.var(Z.default.instancePath,(0,q._)`${Z.default.valCxt}.${Z.default.instancePath}`),t.var(Z.default.parentData,(0,q._)`${Z.default.valCxt}.${Z.default.parentData}`),t.var(Z.default.parentDataProperty,(0,q._)`${Z.default.valCxt}.${Z.default.parentDataProperty}`),t.var(Z.default.rootData,(0,q._)`${Z.default.valCxt}.${Z.default.rootData}`),n.dynamicRef&&t.var(Z.default.dynamicAnchors,(0,q._)`${Z.default.valCxt}.${Z.default.dynamicAnchors}`)},()=>{t.var(Z.default.instancePath,(0,q._)`""`),t.var(Z.default.parentData,(0,q._)`undefined`),t.var(Z.default.parentDataProperty,(0,q._)`undefined`),t.var(Z.default.rootData,Z.default.data),n.dynamicRef&&t.var(Z.default.dynamicAnchors,(0,q._)`{}`)})}function tk(t){let{schema:n,opts:e,gen:i}=t;Xg(t,()=>{e.$comment&&n.$comment&&n_(t),ak(t),i.let(Z.default.vErrors,null),i.let(Z.default.errors,0),e.unevaluated&&nk(t),t_(t),ck(t)})}function nk(t){let{gen:n,validateName:e}=t;t.evaluated=n.const("evaluated",(0,q._)`${e}.evaluated`),n.if((0,q._)`${t.evaluated}.dynamicProps`,()=>n.assign((0,q._)`${t.evaluated}.props`,(0,q._)`undefined`)),n.if((0,q._)`${t.evaluated}.dynamicItems`,()=>n.assign((0,q._)`${t.evaluated}.items`,(0,q._)`undefined`))}function Wg(t,n){let e=typeof t=="object"&&t[n.schemaId];return e&&(n.code.source||n.code.process)?(0,q._)`/*# sourceURL=${e} */`:q.nil}function ik(t,n){if(Jg(t)&&(e_(t),Zg(t))){rk(t,n);return}(0,Qg.boolOrEmptySchema)(t,n)}function Zg({schema:t,self:n}){if(typeof t=="boolean")return!t;for(let e in t)if(n.RULES.all[e])return!0;return!1}function Jg(t){return typeof t.schema!="boolean"}function rk(t,n){let{schema:e,gen:i,opts:r}=t;r.$comment&&e.$comment&&n_(t),sk(t),lk(t);let o=i.const("_errs",Z.default.errors);t_(t,o),i.var(n,(0,q._)`${o} === ${Z.default.errors}`)}function e_(t){(0,Un.checkUnknownRules)(t),ok(t)}function t_(t,n){if(t.opts.jtd)return Kg(t,[],!1,n);let e=(0,Gg.getSchemaTypes)(t.schema),i=(0,Gg.coerceAndCheckDataType)(t,e);Kg(t,e,!i,n)}function ok(t){let{schema:n,errSchemaPath:e,opts:i,self:r}=t;n.$ref&&i.ignoreKeywordsWithRef&&(0,Un.schemaHasRulesButRef)(n,r.RULES)&&r.logger.warn(`$ref: keywords ignored in schema at path "${e}"`)}function ak(t){let{schema:n,opts:e}=t;n.default!==void 0&&e.useDefaults&&e.strictSchema&&(0,Un.checkStrictMode)(t,"default is ignored in the schema root")}function sk(t){let n=t.schema[t.opts.schemaId];n&&(t.baseId=(0,XC.resolveUrl)(t.opts.uriResolver,t.baseId,n))}function lk(t){if(t.schema.$async&&!t.schemaEnv.$async)throw new Error("async schema in sync schema")}function n_({gen:t,schemaEnv:n,schema:e,errSchemaPath:i,opts:r}){let o=e.$comment;if(r.$comment===!0)t.code((0,q._)`${Z.default.self}.logger.log(${o})`);else if(typeof r.$comment=="function"){let a=(0,q.str)`${i}/$comment`,s=t.scopeValue("root",{ref:n.root});t.code((0,q._)`${Z.default.self}.opts.$comment(${o}, ${a}, ${s}.schema)`)}}function ck(t){let{gen:n,schemaEnv:e,validateName:i,ValidationError:r,opts:o}=t;e.$async?n.if((0,q._)`${Z.default.errors} === 0`,()=>n.return(Z.default.data),()=>n.throw((0,q._)`new ${r}(${Z.default.vErrors})`)):(n.assign((0,q._)`${i}.errors`,Z.default.vErrors),o.unevaluated&&dk(t),n.return((0,q._)`${Z.default.errors} === 0`))}function dk({gen:t,evaluated:n,props:e,items:i}){e instanceof q.Name&&t.assign((0,q._)`${n}.props`,e),i instanceof q.Name&&t.assign((0,q._)`${n}.items`,i)}function Kg(t,n,e,i){let{gen:r,schema:o,data:a,allErrors:s,opts:l,self:c}=t,{RULES:d}=c;if(o.$ref&&(l.ignoreKeywordsWithRef||!(0,Un.schemaHasRulesButRef)(o,d))){r.block(()=>r_(t,"$ref",d.all.$ref.definition));return}l.jtd||mk(t,n),r.block(()=>{for(let p of d.rules)u(p);u(d.post)});function u(p){(0,pm.shouldUseGroup)(o,p)&&(p.type?(r.if((0,hl.checkDataType)(p.type,a,l.strictNumbers)),Yg(t,p),n.length===1&&n[0]===p.type&&e&&(r.else(),(0,hl.reportTypeError)(t)),r.endIf()):Yg(t,p),s||r.if((0,q._)`${Z.default.errors} === ${i||0}`))}}function Yg(t,n){let{gen:e,schema:i,opts:{useDefaults:r}}=t;r&&(0,QC.assignDefaults)(t,n.type),e.block(()=>{for(let o of n.rules)(0,pm.shouldUseRule)(i,o)&&r_(t,o.keyword,o.definition,n.type)})}function mk(t,n){t.schemaEnv.meta||!t.opts.strictTypes||(uk(t,n),t.opts.allowUnionTypes||pk(t,n),hk(t,t.dataTypes))}function uk(t,n){if(n.length){if(!t.dataTypes.length){t.dataTypes=n;return}n.forEach(e=>{i_(t.dataTypes,e)||hm(t,`type "${e}" not allowed by context "${t.dataTypes.join(",")}"`)}),gk(t,n)}}function pk(t,n){n.length>1&&!(n.length===2&&n.includes("null"))&&hm(t,"use allowUnionTypes to allow union type keyword")}function hk(t,n){let e=t.self.RULES.all;for(let i in e){let r=e[i];if(typeof r=="object"&&(0,pm.shouldUseRule)(t.schema,r)){let{type:o}=r.definition;o.length&&!o.some(a=>fk(n,a))&&hm(t,`missing type "${o.join(",")}" for keyword "${i}"`)}}}function fk(t,n){return t.includes(n)||n==="number"&&t.includes("integer")}function i_(t,n){return t.includes(n)||n==="integer"&&t.includes("number")}function gk(t,n){let e=[];for(let i of t.dataTypes)i_(n,i)?e.push(i):n.includes("integer")&&i==="number"&&e.push("integer");t.dataTypes=e}function hm(t,n){let e=t.schemaEnv.baseId+t.errSchemaPath;n+=` at "${e}" (strictTypes)`,(0,Un.checkStrictMode)(t,n,t.opts.strictTypes)}var fl=class{constructor(n,e,i){if((0,oa.validateKeywordUsage)(n,e,i),this.gen=n.gen,this.allErrors=n.allErrors,this.keyword=i,this.data=n.data,this.schema=n.schema[i],this.$data=e.$data&&n.opts.$data&&this.schema&&this.schema.$data,this.schemaValue=(0,Un.schemaRefOrVal)(n,this.schema,i,this.$data),this.schemaType=e.schemaType,this.parentSchema=n.schema,this.params={},this.it=n,this.def=e,this.$data)this.schemaCode=n.gen.const("vSchema",o_(this.$data,n));else if(this.schemaCode=this.schemaValue,!(0,oa.validSchemaType)(this.schema,e.schemaType,e.allowUndefined))throw new Error(`${i} value must be ${JSON.stringify(e.schemaType)}`);("code"in e?e.trackErrors:e.errors!==!1)&&(this.errsCount=n.gen.const("_errs",Z.default.errors))}result(n,e,i){this.failResult((0,q.not)(n),e,i)}failResult(n,e,i){this.gen.if(n),i?i():this.error(),e?(this.gen.else(),e(),this.allErrors&&this.gen.endIf()):this.allErrors?this.gen.endIf():this.gen.else()}pass(n,e){this.failResult((0,q.not)(n),void 0,e)}fail(n){if(n===void 0){this.error(),this.allErrors||this.gen.if(!1);return}this.gen.if(n),this.error(),this.allErrors?this.gen.endIf():this.gen.else()}fail$data(n){if(!this.$data)return this.fail(n);let{schemaCode:e}=this;this.fail((0,q._)`${e} !== undefined && (${(0,q.or)(this.invalid$data(),n)})`)}error(n,e,i){if(e){this.setParams(e),this._error(n,i),this.setParams({});return}this._error(n,i)}_error(n,e){(n?ra.reportExtraError:ra.reportError)(this,this.def.error,e)}$dataError(){(0,ra.reportError)(this,this.def.$dataError||ra.keyword$DataError)}reset(){if(this.errsCount===void 0)throw new Error('add "trackErrors" to keyword definition');(0,ra.resetErrorsCount)(this.gen,this.errsCount)}ok(n){this.allErrors||this.gen.if(n)}setParams(n,e){e?Object.assign(this.params,n):this.params=n}block$data(n,e,i=q.nil){this.gen.block(()=>{this.check$data(n,i),e()})}check$data(n=q.nil,e=q.nil){if(!this.$data)return;let{gen:i,schemaCode:r,schemaType:o,def:a}=this;i.if((0,q.or)((0,q._)`${r} === undefined`,e)),n!==q.nil&&i.assign(n,!0),(o.length||a.validateSchema)&&(i.elseIf(this.invalid$data()),this.$dataError(),n!==q.nil&&i.assign(n,!1)),i.else()}invalid$data(){let{gen:n,schemaCode:e,schemaType:i,def:r,it:o}=this;return(0,q.or)(a(),s());function a(){if(i.length){if(!(e instanceof q.Name))throw new Error("ajv implementation error");let l=Array.isArray(i)?i:[i];return(0,q._)`${(0,hl.checkDataTypes)(l,e,o.opts.strictNumbers,hl.DataType.Wrong)}`}return q.nil}function s(){if(r.validateSchema){let l=n.scopeValue("validate$data",{ref:r.validateSchema});return(0,q._)`!${l}(${e})`}return q.nil}}subschema(n,e){let i=(0,um.getSubschema)(this.it,n);(0,um.extendSubschemaData)(i,this.it,n),(0,um.extendSubschemaMode)(i,n);let r=Ie(M(M({},this.it),i),{items:void 0,props:void 0});return ik(r,e),r}mergeEvaluated(n,e){let{it:i,gen:r}=this;i.opts.unevaluated&&(i.props!==!0&&n.props!==void 0&&(i.props=Un.mergeEvaluated.props(r,n.props,i.props,e)),i.items!==!0&&n.items!==void 0&&(i.items=Un.mergeEvaluated.items(r,n.items,i.items,e)))}mergeValidEvaluated(n,e){let{it:i,gen:r}=this;if(i.opts.unevaluated&&(i.props!==!0||i.items!==!0))return r.if(e,()=>this.mergeEvaluated(n,q.Name)),!0}};pi.KeywordCxt=fl;function r_(t,n,e,i){let r=new fl(t,e,n);"code"in e?e.code(r,i):r.$data&&e.validate?(0,oa.funcKeywordCode)(r,e):"macro"in e?(0,oa.macroKeywordCode)(r,e):(e.compile||e.validate)&&(0,oa.funcKeywordCode)(r,e)}var _k=/^\/(?:[^~]|~0|~1)*$/,bk=/^([0-9]+)(#|\/(?:[^~]|~0|~1)*)?$/;function o_(t,{dataLevel:n,dataNames:e,dataPathArr:i}){let r,o;if(t==="")return Z.default.rootData;if(t[0]==="/"){if(!_k.test(t))throw new Error(`Invalid JSON-pointer: ${t}`);r=t,o=Z.default.rootData}else{let c=bk.exec(t);if(!c)throw new Error(`Invalid JSON-pointer: ${t}`);let d=+c[1];if(r=c[2],r==="#"){if(d>=n)throw new Error(l("property/index",d));return i[n-d]}if(d>n)throw new Error(l("data",d));if(o=e[n-d],!r)return o}let a=o,s=r.split("/");for(let c of s)c&&(o=(0,q._)`${o}${(0,q.getProperty)((0,Un.unescapeJsonPointer)(c))}`,a=(0,q._)`${a} && ${o}`);return a;function l(c,d){return`Cannot access ${c} ${d} levels up, current level is ${n}`}}pi.getData=o_});var gl=L(gm=>{"use strict";Object.defineProperty(gm,"__esModule",{value:!0});var fm=class extends Error{constructor(n){super("validation failed"),this.errors=n,this.ajv=this.validation=!0}};gm.default=fm});var sa=L(vm=>{"use strict";Object.defineProperty(vm,"__esModule",{value:!0});var _m=ia(),bm=class extends Error{constructor(n,e,i,r){super(r||`can't resolve reference ${i} from id ${e}`),this.missingRef=(0,_m.resolveUrl)(n,e,i),this.missingSchema=(0,_m.normalizeId)((0,_m.getFullPath)(n,this.missingRef))}};vm.default=bm});var bl=L(Kt=>{"use strict";Object.defineProperty(Kt,"__esModule",{value:!0});Kt.resolveSchema=Kt.getCompilingSchema=Kt.resolveRef=Kt.compileSchema=Kt.SchemaEnv=void 0;var ln=ae(),vk=gl(),ji=Hn(),cn=ia(),a_=he(),yk=aa(),Or=class{constructor(n){var e;this.refs={},this.dynamicAnchors={};let i;typeof n.schema=="object"&&(i=n.schema),this.schema=n.schema,this.schemaId=n.schemaId,this.root=n.root||this,this.baseId=(e=n.baseId)!==null&&e!==void 0?e:(0,cn.normalizeId)(i?.[n.schemaId||"$id"]),this.schemaPath=n.schemaPath,this.localRefs=n.localRefs,this.meta=n.meta,this.$async=i?.$async,this.refs={}}};Kt.SchemaEnv=Or;function xm(t){let n=s_.call(this,t);if(n)return n;let e=(0,cn.getFullPath)(this.opts.uriResolver,t.root.baseId),{es5:i,lines:r}=this.opts.code,{ownProperties:o}=this.opts,a=new ln.CodeGen(this.scope,{es5:i,lines:r,ownProperties:o}),s;t.$async&&(s=a.scopeValue("Error",{ref:vk.default,code:(0,ln._)`require("ajv/dist/runtime/validation_error").default`}));let l=a.scopeName("validate");t.validateName=l;let c={gen:a,allErrors:this.opts.allErrors,data:ji.default.data,parentData:ji.default.parentData,parentDataProperty:ji.default.parentDataProperty,dataNames:[ji.default.data],dataPathArr:[ln.nil],dataLevel:0,dataTypes:[],definedProperties:new Set,topSchemaRef:a.scopeValue("schema",this.opts.code.source===!0?{ref:t.schema,code:(0,ln.stringify)(t.schema)}:{ref:t.schema}),validateName:l,ValidationError:s,schema:t.schema,schemaEnv:t,rootId:e,baseId:t.baseId||e,schemaPath:ln.nil,errSchemaPath:t.schemaPath||(this.opts.jtd?"":"#"),errorPath:(0,ln._)`""`,opts:this.opts,self:this},d;try{this._compilations.add(t),(0,yk.validateFunctionCode)(c),a.optimize(this.opts.code.optimize);let u=a.toString();d=`${a.scopeRefs(ji.default.scope)}return ${u}`,this.opts.code.process&&(d=this.opts.code.process(d,t));let y=new Function(`${ji.default.self}`,`${ji.default.scope}`,d)(this,this.scope.get());if(this.scope.value(l,{ref:y}),y.errors=null,y.schema=t.schema,y.schemaEnv=t,t.$async&&(y.$async=!0),this.opts.code.source===!0&&(y.source={validateName:l,validateCode:u,scopeValues:a._values}),this.opts.unevaluated){let{props:g,items:_}=c;y.evaluated={props:g instanceof ln.Name?void 0:g,items:_ instanceof ln.Name?void 0:_,dynamicProps:g instanceof ln.Name,dynamicItems:_ instanceof ln.Name},y.source&&(y.source.evaluated=(0,ln.stringify)(y.evaluated))}return t.validate=y,t}catch(u){throw delete t.validate,delete t.validateName,d&&this.logger.error("Error compiling schema, function code:",d),u}finally{this._compilations.delete(t)}}Kt.compileSchema=xm;function xk(t,n,e){var i;e=(0,cn.resolveUrl)(this.opts.uriResolver,n,e);let r=t.refs[e];if(r)return r;let o=kk.call(this,t,e);if(o===void 0){let a=(i=t.localRefs)===null||i===void 0?void 0:i[e],{schemaId:s}=this.opts;a&&(o=new Or({schema:a,schemaId:s,root:t,baseId:n}))}if(o!==void 0)return t.refs[e]=wk.call(this,o)}Kt.resolveRef=xk;function wk(t){return(0,cn.inlineRef)(t.schema,this.opts.inlineRefs)?t.schema:t.validate?t:xm.call(this,t)}function s_(t){for(let n of this._compilations)if(Ck(n,t))return n}Kt.getCompilingSchema=s_;function Ck(t,n){return t.schema===n.schema&&t.root===n.root&&t.baseId===n.baseId}function kk(t,n){let e;for(;typeof(e=this.refs[n])=="string";)n=e;return e||this.schemas[n]||_l.call(this,t,n)}function _l(t,n){let e=this.opts.uriResolver.parse(n),i=(0,cn._getFullPath)(this.opts.uriResolver,e),r=(0,cn.getFullPath)(this.opts.uriResolver,t.baseId,void 0);if(Object.keys(t.schema).length>0&&i===r)return ym.call(this,e,t);let o=(0,cn.normalizeId)(i),a=this.refs[o]||this.schemas[o];if(typeof a=="string"){let s=_l.call(this,t,a);return typeof s?.schema!="object"?void 0:ym.call(this,e,s)}if(typeof a?.schema=="object"){if(a.validate||xm.call(this,a),o===(0,cn.normalizeId)(n)){let{schema:s}=a,{schemaId:l}=this.opts,c=s[l];return c&&(r=(0,cn.resolveUrl)(this.opts.uriResolver,r,c)),new Or({schema:s,schemaId:l,root:t,baseId:r})}return ym.call(this,e,a)}}Kt.resolveSchema=_l;var Dk=new Set(["properties","patternProperties","enum","dependencies","definitions"]);function ym(t,{baseId:n,schema:e,root:i}){var r;if(((r=t.fragment)===null||r===void 0?void 0:r[0])!=="/")return;for(let s of t.fragment.slice(1).split("/")){if(typeof e=="boolean")return;let l=e[(0,a_.unescapeFragment)(s)];if(l===void 0)return;e=l;let c=typeof e=="object"&&e[this.opts.schemaId];!Dk.has(s)&&c&&(n=(0,cn.resolveUrl)(this.opts.uriResolver,n,c))}let o;if(typeof e!="boolean"&&e.$ref&&!(0,a_.schemaHasRulesButRef)(e,this.RULES)){let s=(0,cn.resolveUrl)(this.opts.uriResolver,n,e.$ref);o=_l.call(this,i,s)}let{schemaId:a}=this.opts;if(o=o||new Or({schema:e,schemaId:a,root:i,baseId:n}),o.schema!==o.root.schema)return o}});var l_=L((F$,Ek)=>{Ek.exports={$id:"https://raw.githubusercontent.com/ajv-validator/ajv/master/lib/refs/data.json#",description:"Meta-schema for $data reference (JSON AnySchema extension proposal)",type:"object",required:["$data"],properties:{$data:{type:"string",anyOf:[{format:"relative-json-pointer"},{format:"json-pointer"}]}},additionalProperties:!1}});var km=L((N$,h_)=>{"use strict";var Sk=RegExp.prototype.test.bind(/^[\da-f]{8}-[\da-f]{4}-[\da-f]{4}-[\da-f]{4}-[\da-f]{12}$/iu),d_=RegExp.prototype.test.bind(/^(?:(?:25[0-5]|2[0-4]\d|1\d{2}|[1-9]\d|\d)\.){3}(?:25[0-5]|2[0-4]\d|1\d{2}|[1-9]\d|\d)$/u),wm=RegExp.prototype.test.bind(/^[\da-f]{2}$/iu),m_=RegExp.prototype.test.bind(/^[\da-z\-._~]$/iu),Mk=RegExp.prototype.test.bind(/^[\da-z\-._~!$&'()*+,;=:@/]$/iu);function Cm(t){let n="",e=0,i=0;for(i=0;i<t.length;i++)if(e=t[i].charCodeAt(0),e!==48){if(!(e>=48&&e<=57||e>=65&&e<=70||e>=97&&e<=102))return"";n+=t[i];break}for(i+=1;i<t.length;i++){if(e=t[i].charCodeAt(0),!(e>=48&&e<=57||e>=65&&e<=70||e>=97&&e<=102))return"";n+=t[i]}return n}var Ak=RegExp.prototype.test.bind(/[^!"$&'()*+,\-.;=_`a-z{}~]/u);function c_(t){return t.length=0,!0}function Ik(t,n,e){if(t.length){let i=Cm(t);if(i!=="")n.push(i);else return e.error=!0,!1;t.length=0}return!0}function Tk(t){let n=0,e={error:!1,address:"",zone:""},i=[],r=[],o=!1,a=!1,s=Ik;for(let l=0;l<t.length;l++){let c=t[l];if(!(c==="["||c==="]"))if(c===":"){if(o===!0&&(a=!0),!s(r,i,e))break;if(++n>7){e.error=!0;break}l>0&&t[l-1]===":"&&(o=!0),i.push(":");continue}else if(c==="%"){if(!s(r,i,e))break;s=c_}else{r.push(c);continue}}return r.length&&(s===c_?e.zone=r.join(""):a?i.push(r.join("")):i.push(Cm(r))),e.address=i.join(""),e}function u_(t){if(Ok(t,":")<2)return{host:t,isIPV6:!1};let n=Tk(t);if(n.error)return{host:t,isIPV6:!1};{let e=n.address,i=n.address;return n.zone&&(e+="%"+n.zone,i+="%25"+n.zone),{host:e,isIPV6:!0,escapedHost:i}}}function Ok(t,n){let e=0;for(let i=0;i<t.length;i++)t[i]===n&&e++;return e}function Rk(t){let n=t,e=[],i=-1,r=0;for(;r=n.length;){if(r===1){if(n===".")break;if(n==="/"){e.push("/");break}else{e.push(n);break}}else if(r===2){if(n[0]==="."){if(n[1]===".")break;if(n[1]==="/"){n=n.slice(2);continue}}else if(n[0]==="/"&&(n[1]==="."||n[1]==="/")){e.push("/");break}}else if(r===3&&n==="/.."){e.length!==0&&e.pop(),e.push("/");break}if(n[0]==="."){if(n[1]==="."){if(n[2]==="/"){n=n.slice(3);continue}}else if(n[1]==="/"){n=n.slice(2);continue}}else if(n[0]==="/"&&n[1]==="."){if(n[2]==="/"){n=n.slice(2);continue}else if(n[2]==="."&&n[3]==="/"){n=n.slice(3),e.length!==0&&e.pop();continue}}if((i=n.indexOf("/",1))===-1){e.push(n);break}else e.push(n.slice(0,i)),n=n.slice(i)}return e.join("")}var Pk={"@":"%40","/":"%2F","?":"%3F","#":"%23",":":"%3A"},Fk=/[@/?#:]/g,Nk=/[@/?#]/g;function p_(t,n){let e=n?Nk:Fk;return e.lastIndex=0,t.replace(e,i=>Pk[i])}function Lk(t,n=!1){if(t.indexOf("%")===-1)return t;let e="";for(let i=0;i<t.length;i++){if(t[i]==="%"&&i+2<t.length){let r=t.slice(i+1,i+3);if(wm(r)){let o=r.toUpperCase(),a=String.fromCharCode(parseInt(o,16));n&&m_(a)?e+=a:e+="%"+o,i+=2;continue}}e+=t[i]}return e}function Bk(t){let n="";for(let e=0;e<t.length;e++){if(t[e]==="%"&&e+2<t.length){let i=t.slice(e+1,e+3);if(wm(i)){let r=i.toUpperCase(),o=String.fromCharCode(parseInt(r,16));o!=="."&&m_(o)?n+=o:n+="%"+r,e+=2;continue}}Mk(t[e])?n+=t[e]:n+=escape(t[e])}return n}function $k(t){let n="";for(let e=0;e<t.length;e++){if(t[e]==="%"&&e+2<t.length){let i=t.slice(e+1,e+3);if(wm(i)){n+="%"+i.toUpperCase(),e+=2;continue}}n+=escape(t[e])}return n}function jk(t){let n=[];if(t.userinfo!==void 0&&(n.push(t.userinfo),n.push("@")),t.host!==void 0){let e=unescape(t.host);if(!d_(e)){let i=u_(e);i.isIPV6===!0?e=`[${i.escapedHost}]`:e=p_(e,!1)}n.push(e)}return(typeof t.port=="number"||typeof t.port=="string")&&(n.push(":"),n.push(String(t.port))),n.length?n.join(""):void 0}h_.exports={nonSimpleDomain:Ak,recomposeAuthority:jk,reescapeHostDelimiters:p_,normalizePercentEncoding:Lk,normalizePathEncoding:Bk,escapePreservingEscapes:$k,removeDotSegments:Rk,isIPv4:d_,isUUID:Sk,normalizeIPv6:u_,stringArrayToHexStripped:Cm}});var v_=L((L$,b_)=>{"use strict";var{isUUID:Vk}=km(),zk=/([\da-z][\d\-a-z]{0,31}):((?:[\w!$'()*+,\-.:;=@]|%[\da-f]{2})+)/iu,Hk=["http","https","ws","wss","urn","urn:uuid"];function Uk(t){return Hk.indexOf(t)!==-1}function Dm(t){return t.secure===!0?!0:t.secure===!1?!1:t.scheme?t.scheme.length===3&&(t.scheme[0]==="w"||t.scheme[0]==="W")&&(t.scheme[1]==="s"||t.scheme[1]==="S")&&(t.scheme[2]==="s"||t.scheme[2]==="S"):!1}function f_(t){return t.host||(t.error=t.error||"HTTP URIs must have a host."),t}function g_(t){let n=String(t.scheme).toLowerCase()==="https";return(t.port===(n?443:80)||t.port==="")&&(t.port=void 0),t.path||(t.path="/"),t}function qk(t){return t.secure=Dm(t),t.resourceName=(t.path||"/")+(t.query?"?"+t.query:""),t.path=void 0,t.query=void 0,t}function Gk(t){if((t.port===(Dm(t)?443:80)||t.port==="")&&(t.port=void 0),typeof t.secure=="boolean"&&(t.scheme=t.secure?"wss":"ws",t.secure=void 0),t.resourceName){let[n,e]=t.resourceName.split("?");t.path=n&&n!=="/"?n:void 0,t.query=e,t.resourceName=void 0}return t.fragment=void 0,t}function Wk(t,n){if(!t.path)return t.error="URN can not be parsed",t;let e=t.path.match(zk);if(e){let i=n.scheme||t.scheme||"urn";t.nid=e[1].toLowerCase(),t.nss=e[2];let r=`${i}:${n.nid||t.nid}`,o=Em(r);t.path=void 0,o&&(t=o.parse(t,n))}else t.error=t.error||"URN can not be parsed.";return t}function Kk(t,n){if(t.nid===void 0)throw new Error("URN without nid cannot be serialized");let e=n.scheme||t.scheme||"urn",i=t.nid.toLowerCase(),r=`${e}:${n.nid||i}`,o=Em(r);o&&(t=o.serialize(t,n));let a=t,s=t.nss;return a.path=`${i||n.nid}:${s}`,n.skipEscape=!0,a}function Yk(t,n){let e=t;return e.uuid=e.nss,e.nss=void 0,!n.tolerant&&(!e.uuid||!Vk(e.uuid))&&(e.error=e.error||"UUID is not valid."),e}function Qk(t){let n=t;return n.nss=(t.uuid||"").toLowerCase(),n}var __={scheme:"http",domainHost:!0,parse:f_,serialize:g_},Xk={scheme:"https",domainHost:__.domainHost,parse:f_,serialize:g_},vl={scheme:"ws",domainHost:!0,parse:qk,serialize:Gk},Zk={scheme:"wss",domainHost:vl.domainHost,parse:vl.parse,serialize:vl.serialize},Jk={scheme:"urn",parse:Wk,serialize:Kk,skipNormalize:!0},eD={scheme:"urn:uuid",parse:Yk,serialize:Qk,skipNormalize:!0},yl={http:__,https:Xk,ws:vl,wss:Zk,urn:Jk,"urn:uuid":eD};Object.setPrototypeOf(yl,null);function Em(t){return t&&(yl[t]||yl[t.toLowerCase()])||void 0}b_.exports={wsIsSecure:Dm,SCHEMES:yl,isValidSchemeName:Uk,getSchemeHandler:Em}});var D_=L((B$,xl)=>{"use strict";var{normalizeIPv6:tD,removeDotSegments:la,recomposeAuthority:nD,normalizePercentEncoding:iD,normalizePathEncoding:rD,escapePreservingEscapes:oD,reescapeHostDelimiters:aD,isIPv4:sD,nonSimpleDomain:lD}=km(),{SCHEMES:cD,getSchemeHandler:x_}=v_();function dD(t,n){return typeof t=="string"?t=fD(t,n):typeof t=="object"&&(t=Rr(Vi(t,n),n)),t}function mD(t,n,e){let i=e?Object.assign({scheme:"null"},e):{scheme:"null"},r=w_(Rr(t,i),Rr(n,i),i,!0);return i.skipEscape=!0,Vi(r,i)}function w_(t,n,e,i){let r={};return i||(t=Rr(Vi(t,e),e),n=Rr(Vi(n,e),e)),e=e||{},!e.tolerant&&n.scheme?(r.scheme=n.scheme,r.userinfo=n.userinfo,r.host=n.host,r.port=n.port,r.path=la(n.path||""),r.query=n.query):(n.userinfo!==void 0||n.host!==void 0||n.port!==void 0?(r.userinfo=n.userinfo,r.host=n.host,r.port=n.port,r.path=la(n.path||""),r.query=n.query):(n.path?(n.path[0]==="/"?r.path=la(n.path):((t.userinfo!==void 0||t.host!==void 0||t.port!==void 0)&&!t.path?r.path="/"+n.path:t.path?r.path=t.path.slice(0,t.path.lastIndexOf("/")+1)+n.path:r.path=n.path,r.path=la(r.path)),r.query=n.query):(r.path=t.path,n.query!==void 0?r.query=n.query:r.query=t.query),r.userinfo=t.userinfo,r.host=t.host,r.port=t.port),r.scheme=t.scheme),r.fragment=n.fragment,r}function uD(t,n,e){let i=y_(t,e),r=y_(n,e);return i!==void 0&&r!==void 0&&i.toLowerCase()===r.toLowerCase()}function Vi(t,n){let e={host:t.host,scheme:t.scheme,userinfo:t.userinfo,port:t.port,path:t.path,query:t.query,nid:t.nid,nss:t.nss,uuid:t.uuid,fragment:t.fragment,reference:t.reference,resourceName:t.resourceName,secure:t.secure,error:""},i=Object.assign({},n),r=[],o=x_(i.scheme||e.scheme);o&&o.serialize&&o.serialize(e,i),e.path!==void 0&&(i.skipEscape?e.path=iD(e.path):(e.path=oD(e.path),e.scheme!==void 0&&(e.path=e.path.split("%3A").join(":")))),i.reference!=="suffix"&&e.scheme&&r.push(e.scheme,":");let a=nD(e);if(a!==void 0&&(i.reference!=="suffix"&&r.push("//"),r.push(a),e.path&&e.path[0]!=="/"&&r.push("/")),e.path!==void 0){let s=e.path;!i.absolutePath&&(!o||!o.absolutePath)&&(s=la(s)),a===void 0&&s[0]==="/"&&s[1]==="/"&&(s="/%2F"+s.slice(2)),r.push(s)}return e.query!==void 0&&r.push("?",e.query),e.fragment!==void 0&&r.push("#",e.fragment),r.join("")}var pD=/^(?:([^#/:?]+):)?(?:\/\/((?:([^#/?@]*)@)?(\[[^#/?\]]+\]|[^#/:?]*)(?::(\d*))?))?([^#?]*)(?:\?([^#]*))?(?:#((?:.|[\n\r])*))?/u;function hD(t,n){if(n[2]!==void 0&&t.path&&t.path[0]!=="/")return'URI path must start with "/" when authority is present.';if(typeof t.port=="number"&&(t.port<0||t.port>65535))return"URI port is malformed."}function C_(t,n){let e=Object.assign({},n),i={scheme:void 0,userinfo:void 0,host:"",port:void 0,path:"",query:void 0,fragment:void 0},r=!1,o=!1;e.reference==="suffix"&&(e.scheme?t=e.scheme+":"+t:t="//"+t);let a=t.match(pD);if(a){i.scheme=a[1],i.userinfo=a[3],i.host=a[4],i.port=parseInt(a[5],10),i.path=a[6]||"",i.query=a[7],i.fragment=a[8],isNaN(i.port)&&(i.port=a[5]);let s=hD(i,a);if(s!==void 0&&(i.error=i.error||s,r=!0),i.host)if(sD(i.host)===!1){let d=tD(i.host);i.host=d.host.toLowerCase(),o=d.isIPV6}else o=!0;i.scheme===void 0&&i.userinfo===void 0&&i.host===void 0&&i.port===void 0&&i.query===void 0&&!i.path?i.reference="same-document":i.scheme===void 0?i.reference="relative":i.fragment===void 0?i.reference="absolute":i.reference="uri",e.reference&&e.reference!=="suffix"&&e.reference!==i.reference&&(i.error=i.error||"URI is not a "+e.reference+" reference.");let l=x_(e.scheme||i.scheme);if(!e.unicodeSupport&&(!l||!l.unicodeSupport)&&i.host&&(e.domainHost||l&&l.domainHost)&&o===!1&&lD(i.host))try{i.host=URL.domainToASCII(i.host.toLowerCase())}catch(c){i.error=i.error||"Host's domain name can not be converted to ASCII: "+c}if((!l||l&&!l.skipNormalize)&&(t.indexOf("%")!==-1&&(i.scheme!==void 0&&(i.scheme=unescape(i.scheme)),i.host!==void 0&&(i.host=aD(unescape(i.host),o))),i.path&&(i.path=rD(i.path)),i.fragment))try{i.fragment=encodeURI(decodeURIComponent(i.fragment))}catch{i.error=i.error||"URI malformed"}l&&l.parse&&l.parse(i,e)}else i.error=i.error||"URI can not be parsed.";return{parsed:i,malformedAuthorityOrPort:r}}function Rr(t,n){return C_(t,n).parsed}function fD(t,n){return k_(t,n).normalized}function k_(t,n){let{parsed:e,malformedAuthorityOrPort:i}=C_(t,n);return{normalized:i?t:Vi(e,n),malformedAuthorityOrPort:i}}function y_(t,n){if(typeof t=="string"){let{normalized:e,malformedAuthorityOrPort:i}=k_(t,n);return i?void 0:e}if(typeof t=="object")return Vi(t,n)}var Sm={SCHEMES:cD,normalize:dD,resolve:mD,resolveComponent:w_,equal:uD,serialize:Vi,parse:Rr};xl.exports=Sm;xl.exports.default=Sm;xl.exports.fastUri=Sm});var S_=L(Mm=>{"use strict";Object.defineProperty(Mm,"__esModule",{value:!0});var E_=D_();E_.code='require("ajv/dist/runtime/uri").default';Mm.default=E_});var F_=L(at=>{"use strict";Object.defineProperty(at,"__esModule",{value:!0});at.CodeGen=at.Name=at.nil=at.stringify=at.str=at._=at.KeywordCxt=void 0;var gD=aa();Object.defineProperty(at,"KeywordCxt",{enumerable:!0,get:function(){return gD.KeywordCxt}});var Pr=ae();Object.defineProperty(at,"_",{enumerable:!0,get:function(){return Pr._}});Object.defineProperty(at,"str",{enumerable:!0,get:function(){return Pr.str}});Object.defineProperty(at,"stringify",{enumerable:!0,get:function(){return Pr.stringify}});Object.defineProperty(at,"nil",{enumerable:!0,get:function(){return Pr.nil}});Object.defineProperty(at,"Name",{enumerable:!0,get:function(){return Pr.Name}});Object.defineProperty(at,"CodeGen",{enumerable:!0,get:function(){return Pr.CodeGen}});var _D=gl(),O_=sa(),bD=nm(),ca=bl(),vD=ae(),da=ia(),wl=na(),Im=he(),M_=l_(),yD=S_(),R_=(t,n)=>new RegExp(t,n);R_.code="new RegExp";var xD=["removeAdditional","useDefaults","coerceTypes"],wD=new Set(["validate","serialize","parse","wrapper","root","schema","keyword","pattern","formats","validate$data","func","obj","Error"]),CD={errorDataPath:"",format:"`validateFormats: false` can be used instead.",nullable:'"nullable" keyword is supported by default.',jsonPointers:"Deprecated jsPropertySyntax can be used instead.",extendRefs:"Deprecated ignoreKeywordsWithRef can be used instead.",missingRefs:"Pass empty schema with $id that should be ignored to ajv.addSchema.",processCode:"Use option `code: {process: (code, schemaEnv: object) => string}`",sourceCode:"Use option `code: {source: true}`",strictDefaults:"It is default now, see option `strict`.",strictKeywords:"It is default now, see option `strict`.",uniqueItems:'"uniqueItems" keyword is always validated.',unknownFormats:"Disable strict mode or pass `true` to `ajv.addFormat` (or `formats` option).",cache:"Map is used as cache, schema object as key.",serialize:"Map is used as cache, schema object as key.",ajvErrors:"It is default now."},kD={ignoreKeywordsWithRef:"",jsPropertySyntax:"",unicode:'"minLength"/"maxLength" account for unicode characters by default.'},A_=200;function DD(t){var n,e,i,r,o,a,s,l,c,d,u,p,y,g,_,v,x,S,I,F,N,ge,Ce,Pt,Ft;let Nt=t.strict,Mn=(n=t.code)===null||n===void 0?void 0:n.optimize,it=Mn===!0||Mn===void 0?1:Mn||0,Wn=(i=(e=t.code)===null||e===void 0?void 0:e.regExp)!==null&&i!==void 0?i:R_,pn=(r=t.uriResolver)!==null&&r!==void 0?r:yD.default;return{strictSchema:(a=(o=t.strictSchema)!==null&&o!==void 0?o:Nt)!==null&&a!==void 0?a:!0,strictNumbers:(l=(s=t.strictNumbers)!==null&&s!==void 0?s:Nt)!==null&&l!==void 0?l:!0,strictTypes:(d=(c=t.strictTypes)!==null&&c!==void 0?c:Nt)!==null&&d!==void 0?d:"log",strictTuples:(p=(u=t.strictTuples)!==null&&u!==void 0?u:Nt)!==null&&p!==void 0?p:"log",strictRequired:(g=(y=t.strictRequired)!==null&&y!==void 0?y:Nt)!==null&&g!==void 0?g:!1,code:t.code?Ie(M({},t.code),{optimize:it,regExp:Wn}):{optimize:it,regExp:Wn},loopRequired:(_=t.loopRequired)!==null&&_!==void 0?_:A_,loopEnum:(v=t.loopEnum)!==null&&v!==void 0?v:A_,meta:(x=t.meta)!==null&&x!==void 0?x:!0,messages:(S=t.messages)!==null&&S!==void 0?S:!0,inlineRefs:(I=t.inlineRefs)!==null&&I!==void 0?I:!0,schemaId:(F=t.schemaId)!==null&&F!==void 0?F:"$id",addUsedSchema:(N=t.addUsedSchema)!==null&&N!==void 0?N:!0,validateSchema:(ge=t.validateSchema)!==null&&ge!==void 0?ge:!0,validateFormats:(Ce=t.validateFormats)!==null&&Ce!==void 0?Ce:!0,unicodeRegExp:(Pt=t.unicodeRegExp)!==null&&Pt!==void 0?Pt:!0,int32range:(Ft=t.int32range)!==null&&Ft!==void 0?Ft:!0,uriResolver:pn}}var ma=class{constructor(n={}){this.schemas={},this.refs={},this.formats=Object.create(null),this._compilations=new Set,this._loading={},this._cache=new Map,n=this.opts=M(M({},n),DD(n));let{es5:e,lines:i}=this.opts.code;this.scope=new vD.ValueScope({scope:{},prefixes:wD,es5:e,lines:i}),this.logger=TD(n.logger);let r=n.validateFormats;n.validateFormats=!1,this.RULES=(0,bD.getRules)(),I_.call(this,CD,n,"NOT SUPPORTED"),I_.call(this,kD,n,"DEPRECATED","warn"),this._metaOpts=AD.call(this),n.formats&&SD.call(this),this._addVocabularies(),this._addDefaultMetaSchema(),n.keywords&&MD.call(this,n.keywords),typeof n.meta=="object"&&this.addMetaSchema(n.meta),ED.call(this),n.validateFormats=r}_addVocabularies(){this.addKeyword("$async")}_addDefaultMetaSchema(){let{$data:n,meta:e,schemaId:i}=this.opts,r=M_;i==="id"&&(r=M({},M_),r.id=r.$id,delete r.$id),e&&n&&this.addMetaSchema(r,r[i],!1)}defaultMeta(){let{meta:n,schemaId:e}=this.opts;return this.opts.defaultMeta=typeof n=="object"?n[e]||n:void 0}validate(n,e){let i;if(typeof n=="string"){if(i=this.getSchema(n),!i)throw new Error(`no schema with key or ref "${n}"`)}else i=this.compile(n);let r=i(e);return"$async"in i||(this.errors=i.errors),r}compile(n,e){let i=this._addSchema(n,e);return i.validate||this._compileSchemaEnv(i)}compileAsync(n,e){if(typeof this.opts.loadSchema!="function")throw new Error("options.loadSchema should be a function");let{loadSchema:i}=this.opts;return r.call(this,n,e);async function r(d,u){await o.call(this,d.$schema);let p=this._addSchema(d,u);return p.validate||a.call(this,p)}async function o(d){d&&!this.getSchema(d)&&await r.call(this,{$ref:d},!0)}async function a(d){try{return this._compileSchemaEnv(d)}catch(u){if(!(u instanceof O_.default))throw u;return s.call(this,u),await l.call(this,u.missingSchema),a.call(this,d)}}function s({missingSchema:d,missingRef:u}){if(this.refs[d])throw new Error(`AnySchema ${d} is loaded but ${u} cannot be resolved`)}async function l(d){let u=await c.call(this,d);this.refs[d]||await o.call(this,u.$schema),this.refs[d]||this.addSchema(u,d,e)}async function c(d){let u=this._loading[d];if(u)return u;try{return await(this._loading[d]=i(d))}finally{delete this._loading[d]}}}addSchema(n,e,i,r=this.opts.validateSchema){if(Array.isArray(n)){for(let a of n)this.addSchema(a,void 0,i,r);return this}let o;if(typeof n=="object"){let{schemaId:a}=this.opts;if(o=n[a],o!==void 0&&typeof o!="string")throw new Error(`schema ${a} must be string`)}return e=(0,da.normalizeId)(e||o),this._checkUnique(e),this.schemas[e]=this._addSchema(n,i,e,r,!0),this}addMetaSchema(n,e,i=this.opts.validateSchema){return this.addSchema(n,e,!0,i),this}validateSchema(n,e){if(typeof n=="boolean")return!0;let i;if(i=n.$schema,i!==void 0&&typeof i!="string")throw new Error("$schema must be a string");if(i=i||this.opts.defaultMeta||this.defaultMeta(),!i)return this.logger.warn("meta-schema not available"),this.errors=null,!0;let r=this.validate(i,n);if(!r&&e){let o="schema is invalid: "+this.errorsText();if(this.opts.validateSchema==="log")this.logger.error(o);else throw new Error(o)}return r}getSchema(n){let e;for(;typeof(e=T_.call(this,n))=="string";)n=e;if(e===void 0){let{schemaId:i}=this.opts,r=new ca.SchemaEnv({schema:{},schemaId:i});if(e=ca.resolveSchema.call(this,r,n),!e)return;this.refs[n]=e}return e.validate||this._compileSchemaEnv(e)}removeSchema(n){if(n instanceof RegExp)return this._removeAllSchemas(this.schemas,n),this._removeAllSchemas(this.refs,n),this;switch(typeof n){case"undefined":return this._removeAllSchemas(this.schemas),this._removeAllSchemas(this.refs),this._cache.clear(),this;case"string":{let e=T_.call(this,n);return typeof e=="object"&&this._cache.delete(e.schema),delete this.schemas[n],delete this.refs[n],this}case"object":{let e=n;this._cache.delete(e);let i=n[this.opts.schemaId];return i&&(i=(0,da.normalizeId)(i),delete this.schemas[i],delete this.refs[i]),this}default:throw new Error("ajv.removeSchema: invalid parameter")}}addVocabulary(n){for(let e of n)this.addKeyword(e);return this}addKeyword(n,e){let i;if(typeof n=="string")i=n,typeof e=="object"&&(this.logger.warn("these parameters are deprecated, see docs for addKeyword"),e.keyword=i);else if(typeof n=="object"&&e===void 0){if(e=n,i=e.keyword,Array.isArray(i)&&!i.length)throw new Error("addKeywords: keyword must be string or non-empty array")}else throw new Error("invalid addKeywords parameters");if(RD.call(this,i,e),!e)return(0,Im.eachItem)(i,o=>Am.call(this,o)),this;FD.call(this,e);let r=Ie(M({},e),{type:(0,wl.getJSONTypes)(e.type),schemaType:(0,wl.getJSONTypes)(e.schemaType)});return(0,Im.eachItem)(i,r.type.length===0?o=>Am.call(this,o,r):o=>r.type.forEach(a=>Am.call(this,o,r,a))),this}getKeyword(n){let e=this.RULES.all[n];return typeof e=="object"?e.definition:!!e}removeKeyword(n){let{RULES:e}=this;delete e.keywords[n],delete e.all[n];for(let i of e.rules){let r=i.rules.findIndex(o=>o.keyword===n);r>=0&&i.rules.splice(r,1)}return this}addFormat(n,e){return typeof e=="string"&&(e=new RegExp(e)),this.formats[n]=e,this}errorsText(n=this.errors,{separator:e=", ",dataVar:i="data"}={}){return!n||n.length===0?"No errors":n.map(r=>`${i}${r.instancePath} ${r.message}`).reduce((r,o)=>r+e+o)}$dataMetaSchema(n,e){let i=this.RULES.all;n=JSON.parse(JSON.stringify(n));for(let r of e){let o=r.split("/").slice(1),a=n;for(let s of o)a=a[s];for(let s in i){let l=i[s];if(typeof l!="object")continue;let{$data:c}=l.definition,d=a[s];c&&d&&(a[s]=P_(d))}}return n}_removeAllSchemas(n,e){for(let i in n){let r=n[i];(!e||e.test(i))&&(typeof r=="string"?delete n[i]:r&&!r.meta&&(this._cache.delete(r.schema),delete n[i]))}}_addSchema(n,e,i,r=this.opts.validateSchema,o=this.opts.addUsedSchema){let a,{schemaId:s}=this.opts;if(typeof n=="object")a=n[s];else{if(this.opts.jtd)throw new Error("schema must be object");if(typeof n!="boolean")throw new Error("schema must be object or boolean")}let l=this._cache.get(n);if(l!==void 0)return l;i=(0,da.normalizeId)(a||i);let c=da.getSchemaRefs.call(this,n,i);return l=new ca.SchemaEnv({schema:n,schemaId:s,meta:e,baseId:i,localRefs:c}),this._cache.set(l.schema,l),o&&!i.startsWith("#")&&(i&&this._checkUnique(i),this.refs[i]=l),r&&this.validateSchema(n,!0),l}_checkUnique(n){if(this.schemas[n]||this.refs[n])throw new Error(`schema with key or id "${n}" already exists`)}_compileSchemaEnv(n){if(n.meta?this._compileMetaSchema(n):ca.compileSchema.call(this,n),!n.validate)throw new Error("ajv implementation error");return n.validate}_compileMetaSchema(n){let e=this.opts;this.opts=this._metaOpts;try{ca.compileSchema.call(this,n)}finally{this.opts=e}}};ma.ValidationError=_D.default;ma.MissingRefError=O_.default;at.default=ma;function I_(t,n,e,i="error"){for(let r in t){let o=r;o in n&&this.logger[i](`${e}: option ${r}. ${t[o]}`)}}function T_(t){return t=(0,da.normalizeId)(t),this.schemas[t]||this.refs[t]}function ED(){let t=this.opts.schemas;if(t)if(Array.isArray(t))this.addSchema(t);else for(let n in t)this.addSchema(t[n],n)}function SD(){for(let t in this.opts.formats){let n=this.opts.formats[t];n&&this.addFormat(t,n)}}function MD(t){if(Array.isArray(t)){this.addVocabulary(t);return}this.logger.warn("keywords option as map is deprecated, pass array");for(let n in t){let e=t[n];e.keyword||(e.keyword=n),this.addKeyword(e)}}function AD(){let t=M({},this.opts);for(let n of xD)delete t[n];return t}var ID={log(){},warn(){},error(){}};function TD(t){if(t===!1)return ID;if(t===void 0)return console;if(t.log&&t.warn&&t.error)return t;throw new Error("logger must implement log, warn and error methods")}var OD=/^[a-z_$][a-z0-9_$:-]*$/i;function RD(t,n){let{RULES:e}=this;if((0,Im.eachItem)(t,i=>{if(e.keywords[i])throw new Error(`Keyword ${i} is already defined`);if(!OD.test(i))throw new Error(`Keyword ${i} has invalid name`)}),!!n&&n.$data&&!("code"in n||"validate"in n))throw new Error('$data keyword must have "code" or "validate" function')}function Am(t,n,e){var i;let r=n?.post;if(e&&r)throw new Error('keyword with "post" flag cannot have "type"');let{RULES:o}=this,a=r?o.post:o.rules.find(({type:l})=>l===e);if(a||(a={type:e,rules:[]},o.rules.push(a)),o.keywords[t]=!0,!n)return;let s={keyword:t,definition:Ie(M({},n),{type:(0,wl.getJSONTypes)(n.type),schemaType:(0,wl.getJSONTypes)(n.schemaType)})};n.before?PD.call(this,a,s,n.before):a.rules.push(s),o.all[t]=s,(i=n.implements)===null||i===void 0||i.forEach(l=>this.addKeyword(l))}function PD(t,n,e){let i=t.rules.findIndex(r=>r.keyword===e);i>=0?t.rules.splice(i,0,n):(t.rules.push(n),this.logger.warn(`rule ${e} is not defined`))}function FD(t){let{metaSchema:n}=t;n!==void 0&&(t.$data&&this.opts.$data&&(n=P_(n)),t.validateSchema=this.compile(n,!0))}var ND={$ref:"https://raw.githubusercontent.com/ajv-validator/ajv/master/lib/refs/data.json#"};function P_(t){return{anyOf:[t,ND]}}});var N_=L(Tm=>{"use strict";Object.defineProperty(Tm,"__esModule",{value:!0});var LD={keyword:"id",code(){throw new Error('NOT SUPPORTED: keyword "id", use "$id" for schema ID')}};Tm.default=LD});var j_=L(zi=>{"use strict";Object.defineProperty(zi,"__esModule",{value:!0});zi.callRef=zi.getValidate=void 0;var BD=sa(),L_=Wt(),Rt=ae(),Fr=Hn(),B_=bl(),Cl=he(),$D={keyword:"$ref",schemaType:"string",code(t){let{gen:n,schema:e,it:i}=t,{baseId:r,schemaEnv:o,validateName:a,opts:s,self:l}=i,{root:c}=o;if((e==="#"||e==="#/")&&r===c.baseId)return u();let d=B_.resolveRef.call(l,c,r,e);if(d===void 0)throw new BD.default(i.opts.uriResolver,r,e);if(d instanceof B_.SchemaEnv)return p(d);return y(d);function u(){if(o===c)return kl(t,a,o,o.$async);let g=n.scopeValue("root",{ref:c});return kl(t,(0,Rt._)`${g}.validate`,c,c.$async)}function p(g){let _=$_(t,g);kl(t,_,g,g.$async)}function y(g){let _=n.scopeValue("schema",s.code.source===!0?{ref:g,code:(0,Rt.stringify)(g)}:{ref:g}),v=n.name("valid"),x=t.subschema({schema:g,dataTypes:[],schemaPath:Rt.nil,topSchemaRef:_,errSchemaPath:e},v);t.mergeEvaluated(x),t.ok(v)}}};function $_(t,n){let{gen:e}=t;return n.validate?e.scopeValue("validate",{ref:n.validate}):(0,Rt._)`${e.scopeValue("wrapper",{ref:n})}.validate`}zi.getValidate=$_;function kl(t,n,e,i){let{gen:r,it:o}=t,{allErrors:a,schemaEnv:s,opts:l}=o,c=l.passContext?Fr.default.this:Rt.nil;i?d():u();function d(){if(!s.$async)throw new Error("async schema referenced by sync schema");let g=r.let("valid");r.try(()=>{r.code((0,Rt._)`await ${(0,L_.callValidateCode)(t,n,c)}`),y(n),a||r.assign(g,!0)},_=>{r.if((0,Rt._)`!(${_} instanceof ${o.ValidationError})`,()=>r.throw(_)),p(_),a||r.assign(g,!1)}),t.ok(g)}function u(){t.result((0,L_.callValidateCode)(t,n,c),()=>y(n),()=>p(n))}function p(g){let _=(0,Rt._)`${g}.errors`;r.assign(Fr.default.vErrors,(0,Rt._)`${Fr.default.vErrors} === null ? ${_} : ${Fr.default.vErrors}.concat(${_})`),r.assign(Fr.default.errors,(0,Rt._)`${Fr.default.vErrors}.length`)}function y(g){var _;if(!o.opts.unevaluated)return;let v=(_=e?.validate)===null||_===void 0?void 0:_.evaluated;if(o.props!==!0)if(v&&!v.dynamicProps)v.props!==void 0&&(o.props=Cl.mergeEvaluated.props(r,v.props,o.props));else{let x=r.var("props",(0,Rt._)`${g}.evaluated.props`);o.props=Cl.mergeEvaluated.props(r,x,o.props,Rt.Name)}if(o.items!==!0)if(v&&!v.dynamicItems)v.items!==void 0&&(o.items=Cl.mergeEvaluated.items(r,v.items,o.items));else{let x=r.var("items",(0,Rt._)`${g}.evaluated.items`);o.items=Cl.mergeEvaluated.items(r,x,o.items,Rt.Name)}}}zi.callRef=kl;zi.default=$D});var V_=L(Om=>{"use strict";Object.defineProperty(Om,"__esModule",{value:!0});var jD=N_(),VD=j_(),zD=["$schema","$id","$defs","$vocabulary",{keyword:"$comment"},"definitions",jD.default,VD.default];Om.default=zD});var z_=L(Rm=>{"use strict";Object.defineProperty(Rm,"__esModule",{value:!0});var Dl=ae(),hi=Dl.operators,El={maximum:{okStr:"<=",ok:hi.LTE,fail:hi.GT},minimum:{okStr:">=",ok:hi.GTE,fail:hi.LT},exclusiveMaximum:{okStr:"<",ok:hi.LT,fail:hi.GTE},exclusiveMinimum:{okStr:">",ok:hi.GT,fail:hi.LTE}},HD={message:({keyword:t,schemaCode:n})=>(0,Dl.str)`must be ${El[t].okStr} ${n}`,params:({keyword:t,schemaCode:n})=>(0,Dl._)`{comparison: ${El[t].okStr}, limit: ${n}}`},UD={keyword:Object.keys(El),type:"number",schemaType:"number",$data:!0,error:HD,code(t){let{keyword:n,data:e,schemaCode:i}=t;t.fail$data((0,Dl._)`${e} ${El[n].fail} ${i} || isNaN(${e})`)}};Rm.default=UD});var H_=L(Pm=>{"use strict";Object.defineProperty(Pm,"__esModule",{value:!0});var ua=ae(),qD={message:({schemaCode:t})=>(0,ua.str)`must be multiple of ${t}`,params:({schemaCode:t})=>(0,ua._)`{multipleOf: ${t}}`},GD={keyword:"multipleOf",type:"number",schemaType:"number",$data:!0,error:qD,code(t){let{gen:n,data:e,schemaCode:i,it:r}=t,o=r.opts.multipleOfPrecision,a=n.let("res"),s=o?(0,ua._)`Math.abs(Math.round(${a}) - ${a}) > 1e-${o}`:(0,ua._)`${a} !== parseInt(${a})`;t.fail$data((0,ua._)`(${i} === 0 || (${a} = ${e}/${i}, ${s}))`)}};Pm.default=GD});var q_=L(Fm=>{"use strict";Object.defineProperty(Fm,"__esModule",{value:!0});function U_(t){let n=t.length,e=0,i=0,r;for(;i<n;)e++,r=t.charCodeAt(i++),r>=55296&&r<=56319&&i<n&&(r=t.charCodeAt(i),(r&64512)===56320&&i++);return e}Fm.default=U_;U_.code='require("ajv/dist/runtime/ucs2length").default'});var G_=L(Nm=>{"use strict";Object.defineProperty(Nm,"__esModule",{value:!0});var Hi=ae(),WD=he(),KD=q_(),YD={message({keyword:t,schemaCode:n}){let e=t==="maxLength"?"more":"fewer";return(0,Hi.str)`must NOT have ${e} than ${n} characters`},params:({schemaCode:t})=>(0,Hi._)`{limit: ${t}}`},QD={keyword:["maxLength","minLength"],type:"string",schemaType:"number",$data:!0,error:YD,code(t){let{keyword:n,data:e,schemaCode:i,it:r}=t,o=n==="maxLength"?Hi.operators.GT:Hi.operators.LT,a=r.opts.unicode===!1?(0,Hi._)`${e}.length`:(0,Hi._)`${(0,WD.useFunc)(t.gen,KD.default)}(${e})`;t.fail$data((0,Hi._)`${a} ${o} ${i}`)}};Nm.default=QD});var W_=L(Lm=>{"use strict";Object.defineProperty(Lm,"__esModule",{value:!0});var XD=Wt(),ZD=he(),Nr=ae(),JD={message:({schemaCode:t})=>(0,Nr.str)`must match pattern "${t}"`,params:({schemaCode:t})=>(0,Nr._)`{pattern: ${t}}`},eE={keyword:"pattern",type:"string",schemaType:"string",$data:!0,error:JD,code(t){let{gen:n,data:e,$data:i,schema:r,schemaCode:o,it:a}=t,s=a.opts.unicodeRegExp?"u":"";if(i){let{regExp:l}=a.opts.code,c=l.code==="new RegExp"?(0,Nr._)`new RegExp`:(0,ZD.useFunc)(n,l),d=n.let("valid");n.try(()=>n.assign(d,(0,Nr._)`${c}(${o}, ${s}).test(${e})`),()=>n.assign(d,!1)),t.fail$data((0,Nr._)`!${d}`)}else{let l=(0,XD.usePattern)(t,r);t.fail$data((0,Nr._)`!${l}.test(${e})`)}}};Lm.default=eE});var K_=L(Bm=>{"use strict";Object.defineProperty(Bm,"__esModule",{value:!0});var pa=ae(),tE={message({keyword:t,schemaCode:n}){let e=t==="maxProperties"?"more":"fewer";return(0,pa.str)`must NOT have ${e} than ${n} properties`},params:({schemaCode:t})=>(0,pa._)`{limit: ${t}}`},nE={keyword:["maxProperties","minProperties"],type:"object",schemaType:"number",$data:!0,error:tE,code(t){let{keyword:n,data:e,schemaCode:i}=t,r=n==="maxProperties"?pa.operators.GT:pa.operators.LT;t.fail$data((0,pa._)`Object.keys(${e}).length ${r} ${i}`)}};Bm.default=nE});var Y_=L($m=>{"use strict";Object.defineProperty($m,"__esModule",{value:!0});var ha=Wt(),fa=ae(),iE=he(),rE={message:({params:{missingProperty:t}})=>(0,fa.str)`must have required property '${t}'`,params:({params:{missingProperty:t}})=>(0,fa._)`{missingProperty: ${t}}`},oE={keyword:"required",type:"object",schemaType:"array",$data:!0,error:rE,code(t){let{gen:n,schema:e,schemaCode:i,data:r,$data:o,it:a}=t,{opts:s}=a;if(!o&&e.length===0)return;let l=e.length>=s.loopRequired;if(a.allErrors?c():d(),s.strictRequired){let y=t.parentSchema.properties,{definedProperties:g}=t.it;for(let _ of e)if(y?.[_]===void 0&&!g.has(_)){let v=a.schemaEnv.baseId+a.errSchemaPath,x=`required property "${_}" is not defined at "${v}" (strictRequired)`;(0,iE.checkStrictMode)(a,x,a.opts.strictRequired)}}function c(){if(l||o)t.block$data(fa.nil,u);else for(let y of e)(0,ha.checkReportMissingProp)(t,y)}function d(){let y=n.let("missing");if(l||o){let g=n.let("valid",!0);t.block$data(g,()=>p(y,g)),t.ok(g)}else n.if((0,ha.checkMissingProp)(t,e,y)),(0,ha.reportMissingProp)(t,y),n.else()}function u(){n.forOf("prop",i,y=>{t.setParams({missingProperty:y}),n.if((0,ha.noPropertyInData)(n,r,y,s.ownProperties),()=>t.error())})}function p(y,g){t.setParams({missingProperty:y}),n.forOf(y,i,()=>{n.assign(g,(0,ha.propertyInData)(n,r,y,s.ownProperties)),n.if((0,fa.not)(g),()=>{t.error(),n.break()})},fa.nil)}}};$m.default=oE});var Q_=L(jm=>{"use strict";Object.defineProperty(jm,"__esModule",{value:!0});var ga=ae(),aE={message({keyword:t,schemaCode:n}){let e=t==="maxItems"?"more":"fewer";return(0,ga.str)`must NOT have ${e} than ${n} items`},params:({schemaCode:t})=>(0,ga._)`{limit: ${t}}`},sE={keyword:["maxItems","minItems"],type:"array",schemaType:"number",$data:!0,error:aE,code(t){let{keyword:n,data:e,schemaCode:i}=t,r=n==="maxItems"?ga.operators.GT:ga.operators.LT;t.fail$data((0,ga._)`${e}.length ${r} ${i}`)}};jm.default=sE});var Sl=L(Vm=>{"use strict";Object.defineProperty(Vm,"__esModule",{value:!0});var X_=dm();X_.code='require("ajv/dist/runtime/equal").default';Vm.default=X_});var Z_=L(Hm=>{"use strict";Object.defineProperty(Hm,"__esModule",{value:!0});var zm=na(),st=ae(),lE=he(),cE=Sl(),dE={message:({params:{i:t,j:n}})=>(0,st.str)`must NOT have duplicate items (items ## ${n} and ${t} are identical)`,params:({params:{i:t,j:n}})=>(0,st._)`{i: ${t}, j: ${n}}`},mE={keyword:"uniqueItems",type:"array",schemaType:"boolean",$data:!0,error:dE,code(t){let{gen:n,data:e,$data:i,schema:r,parentSchema:o,schemaCode:a,it:s}=t;if(!i&&!r)return;let l=n.let("valid"),c=o.items?(0,zm.getSchemaTypes)(o.items):[];t.block$data(l,d,(0,st._)`${a} === false`),t.ok(l);function d(){let g=n.let("i",(0,st._)`${e}.length`),_=n.let("j");t.setParams({i:g,j:_}),n.assign(l,!0),n.if((0,st._)`${g} > 1`,()=>(u()?p:y)(g,_))}function u(){return c.length>0&&!c.some(g=>g==="object"||g==="array")}function p(g,_){let v=n.name("item"),x=(0,zm.checkDataTypes)(c,v,s.opts.strictNumbers,zm.DataType.Wrong),S=n.const("indices",(0,st._)`{}`);n.for((0,st._)`;${g}--;`,()=>{n.let(v,(0,st._)`${e}[${g}]`),n.if(x,(0,st._)`continue`),c.length>1&&n.if((0,st._)`typeof ${v} == "string"`,(0,st._)`${v} += "_"`),n.if((0,st._)`typeof ${S}[${v}] == "number"`,()=>{n.assign(_,(0,st._)`${S}[${v}]`),t.error(),n.assign(l,!1).break()}).code((0,st._)`${S}[${v}] = ${g}`)})}function y(g,_){let v=(0,lE.useFunc)(n,cE.default),x=n.name("outer");n.label(x).for((0,st._)`;${g}--;`,()=>n.for((0,st._)`${_} = ${g}; ${_}--;`,()=>n.if((0,st._)`${v}(${e}[${g}], ${e}[${_}])`,()=>{t.error(),n.assign(l,!1).break(x)})))}}};Hm.default=mE});var J_=L(qm=>{"use strict";Object.defineProperty(qm,"__esModule",{value:!0});var Um=ae(),uE=he(),pE=Sl(),hE={message:"must be equal to constant",params:({schemaCode:t})=>(0,Um._)`{allowedValue: ${t}}`},fE={keyword:"const",$data:!0,error:hE,code(t){let{gen:n,data:e,$data:i,schemaCode:r,schema:o}=t;i||o&&typeof o=="object"?t.fail$data((0,Um._)`!${(0,uE.useFunc)(n,pE.default)}(${e}, ${r})`):t.fail((0,Um._)`${o} !== ${e}`)}};qm.default=fE});var eb=L(Gm=>{"use strict";Object.defineProperty(Gm,"__esModule",{value:!0});var _a=ae(),gE=he(),_E=Sl(),bE={message:"must be equal to one of the allowed values",params:({schemaCode:t})=>(0,_a._)`{allowedValues: ${t}}`},vE={keyword:"enum",schemaType:"array",$data:!0,error:bE,code(t){let{gen:n,data:e,$data:i,schema:r,schemaCode:o,it:a}=t;if(!i&&r.length===0)throw new Error("enum must have non-empty array");let s=r.length>=a.opts.loopEnum,l,c=()=>l??(l=(0,gE.useFunc)(n,_E.default)),d;if(s||i)d=n.let("valid"),t.block$data(d,u);else{if(!Array.isArray(r))throw new Error("ajv implementation error");let y=n.const("vSchema",o);d=(0,_a.or)(...r.map((g,_)=>p(y,_)))}t.pass(d);function u(){n.assign(d,!1),n.forOf("v",o,y=>n.if((0,_a._)`${c()}(${e}, ${y})`,()=>n.assign(d,!0).break()))}function p(y,g){let _=r[g];return typeof _=="object"&&_!==null?(0,_a._)`${c()}(${e}, ${y}[${g}])`:(0,_a._)`${e} === ${_}`}}};Gm.default=vE});var tb=L(Wm=>{"use strict";Object.defineProperty(Wm,"__esModule",{value:!0});var yE=z_(),xE=H_(),wE=G_(),CE=W_(),kE=K_(),DE=Y_(),EE=Q_(),SE=Z_(),ME=J_(),AE=eb(),IE=[yE.default,xE.default,wE.default,CE.default,kE.default,DE.default,EE.default,SE.default,{keyword:"type",schemaType:["string","array"]},{keyword:"nullable",schemaType:"boolean"},ME.default,AE.default];Wm.default=IE});var Ym=L(ba=>{"use strict";Object.defineProperty(ba,"__esModule",{value:!0});ba.validateAdditionalItems=void 0;var Ui=ae(),Km=he(),TE={message:({params:{len:t}})=>(0,Ui.str)`must NOT have more than ${t} items`,params:({params:{len:t}})=>(0,Ui._)`{limit: ${t}}`},OE={keyword:"additionalItems",type:"array",schemaType:["boolean","object"],before:"uniqueItems",error:TE,code(t){let{parentSchema:n,it:e}=t,{items:i}=n;if(!Array.isArray(i)){(0,Km.checkStrictMode)(e,'"additionalItems" is ignored when "items" is not an array of schemas');return}nb(t,i)}};function nb(t,n){let{gen:e,schema:i,data:r,keyword:o,it:a}=t;a.items=!0;let s=e.const("len",(0,Ui._)`${r}.length`);if(i===!1)t.setParams({len:n.length}),t.pass((0,Ui._)`${s} <= ${n.length}`);else if(typeof i=="object"&&!(0,Km.alwaysValidSchema)(a,i)){let c=e.var("valid",(0,Ui._)`${s} <= ${n.length}`);e.if((0,Ui.not)(c),()=>l(c)),t.ok(c)}function l(c){e.forRange("i",n.length,s,d=>{t.subschema({keyword:o,dataProp:d,dataPropType:Km.Type.Num},c),a.allErrors||e.if((0,Ui.not)(c),()=>e.break())})}}ba.validateAdditionalItems=nb;ba.default=OE});var Qm=L(va=>{"use strict";Object.defineProperty(va,"__esModule",{value:!0});va.validateTuple=void 0;var ib=ae(),Ml=he(),RE=Wt(),PE={keyword:"items",type:"array",schemaType:["object","array","boolean"],before:"uniqueItems",code(t){let{schema:n,it:e}=t;if(Array.isArray(n))return rb(t,"additionalItems",n);e.items=!0,!(0,Ml.alwaysValidSchema)(e,n)&&t.ok((0,RE.validateArray)(t))}};function rb(t,n,e=t.schema){let{gen:i,parentSchema:r,data:o,keyword:a,it:s}=t;d(r),s.opts.unevaluated&&e.length&&s.items!==!0&&(s.items=Ml.mergeEvaluated.items(i,e.length,s.items));let l=i.name("valid"),c=i.const("len",(0,ib._)`${o}.length`);e.forEach((u,p)=>{(0,Ml.alwaysValidSchema)(s,u)||(i.if((0,ib._)`${c} > ${p}`,()=>t.subschema({keyword:a,schemaProp:p,dataProp:p},l)),t.ok(l))});function d(u){let{opts:p,errSchemaPath:y}=s,g=e.length,_=g===u.minItems&&(g===u.maxItems||u[n]===!1);if(p.strictTuples&&!_){let v=`"${a}" is ${g}-tuple, but minItems or maxItems/${n} are not specified or different at path "${y}"`;(0,Ml.checkStrictMode)(s,v,p.strictTuples)}}}va.validateTuple=rb;va.default=PE});var ob=L(Xm=>{"use strict";Object.defineProperty(Xm,"__esModule",{value:!0});var FE=Qm(),NE={keyword:"prefixItems",type:"array",schemaType:["array"],before:"uniqueItems",code:t=>(0,FE.validateTuple)(t,"items")};Xm.default=NE});var sb=L(Zm=>{"use strict";Object.defineProperty(Zm,"__esModule",{value:!0});var ab=ae(),LE=he(),BE=Wt(),$E=Ym(),jE={message:({params:{len:t}})=>(0,ab.str)`must NOT have more than ${t} items`,params:({params:{len:t}})=>(0,ab._)`{limit: ${t}}`},VE={keyword:"items",type:"array",schemaType:["object","boolean"],before:"uniqueItems",error:jE,code(t){let{schema:n,parentSchema:e,it:i}=t,{prefixItems:r}=e;i.items=!0,!(0,LE.alwaysValidSchema)(i,n)&&(r?(0,$E.validateAdditionalItems)(t,r):t.ok((0,BE.validateArray)(t)))}};Zm.default=VE});var lb=L(Jm=>{"use strict";Object.defineProperty(Jm,"__esModule",{value:!0});var Yt=ae(),Al=he(),zE={message:({params:{min:t,max:n}})=>n===void 0?(0,Yt.str)`must contain at least ${t} valid item(s)`:(0,Yt.str)`must contain at least ${t} and no more than ${n} valid item(s)`,params:({params:{min:t,max:n}})=>n===void 0?(0,Yt._)`{minContains: ${t}}`:(0,Yt._)`{minContains: ${t}, maxContains: ${n}}`},HE={keyword:"contains",type:"array",schemaType:["object","boolean"],before:"uniqueItems",trackErrors:!0,error:zE,code(t){let{gen:n,schema:e,parentSchema:i,data:r,it:o}=t,a,s,{minContains:l,maxContains:c}=i;o.opts.next?(a=l===void 0?1:l,s=c):a=1;let d=n.const("len",(0,Yt._)`${r}.length`);if(t.setParams({min:a,max:s}),s===void 0&&a===0){(0,Al.checkStrictMode)(o,'"minContains" == 0 without "maxContains": "contains" keyword ignored');return}if(s!==void 0&&a>s){(0,Al.checkStrictMode)(o,'"minContains" > "maxContains" is always invalid'),t.fail();return}if((0,Al.alwaysValidSchema)(o,e)){let _=(0,Yt._)`${d} >= ${a}`;s!==void 0&&(_=(0,Yt._)`${_} && ${d} <= ${s}`),t.pass(_);return}o.items=!0;let u=n.name("valid");s===void 0&&a===1?y(u,()=>n.if(u,()=>n.break())):a===0?(n.let(u,!0),s!==void 0&&n.if((0,Yt._)`${r}.length > 0`,p)):(n.let(u,!1),p()),t.result(u,()=>t.reset());function p(){let _=n.name("_valid"),v=n.let("count",0);y(_,()=>n.if(_,()=>g(v)))}function y(_,v){n.forRange("i",0,d,x=>{t.subschema({keyword:"contains",dataProp:x,dataPropType:Al.Type.Num,compositeRule:!0},_),v()})}function g(_){n.code((0,Yt._)`${_}++`),s===void 0?n.if((0,Yt._)`${_} >= ${a}`,()=>n.assign(u,!0).break()):(n.if((0,Yt._)`${_} > ${s}`,()=>n.assign(u,!1).break()),a===1?n.assign(u,!0):n.if((0,Yt._)`${_} >= ${a}`,()=>n.assign(u,!0)))}}};Jm.default=HE});var mb=L(Dn=>{"use strict";Object.defineProperty(Dn,"__esModule",{value:!0});Dn.validateSchemaDeps=Dn.validatePropertyDeps=Dn.error=void 0;var eu=ae(),UE=he(),ya=Wt();Dn.error={message:({params:{property:t,depsCount:n,deps:e}})=>{let i=n===1?"property":"properties";return(0,eu.str)`must have ${i} ${e} when property ${t} is present`},params:({params:{property:t,depsCount:n,deps:e,missingProperty:i}})=>(0,eu._)`{property: ${t},
    missingProperty: ${i},
    depsCount: ${n},
    deps: ${e}}`};var qE={keyword:"dependencies",type:"object",schemaType:"object",error:Dn.error,code(t){let[n,e]=GE(t);cb(t,n),db(t,e)}};function GE({schema:t}){let n={},e={};for(let i in t){if(i==="__proto__")continue;let r=Array.isArray(t[i])?n:e;r[i]=t[i]}return[n,e]}function cb(t,n=t.schema){let{gen:e,data:i,it:r}=t;if(Object.keys(n).length===0)return;let o=e.let("missing");for(let a in n){let s=n[a];if(s.length===0)continue;let l=(0,ya.propertyInData)(e,i,a,r.opts.ownProperties);t.setParams({property:a,depsCount:s.length,deps:s.join(", ")}),r.allErrors?e.if(l,()=>{for(let c of s)(0,ya.checkReportMissingProp)(t,c)}):(e.if((0,eu._)`${l} && (${(0,ya.checkMissingProp)(t,s,o)})`),(0,ya.reportMissingProp)(t,o),e.else())}}Dn.validatePropertyDeps=cb;function db(t,n=t.schema){let{gen:e,data:i,keyword:r,it:o}=t,a=e.name("valid");for(let s in n)(0,UE.alwaysValidSchema)(o,n[s])||(e.if((0,ya.propertyInData)(e,i,s,o.opts.ownProperties),()=>{let l=t.subschema({keyword:r,schemaProp:s},a);t.mergeValidEvaluated(l,a)},()=>e.var(a,!0)),t.ok(a))}Dn.validateSchemaDeps=db;Dn.default=qE});var pb=L(tu=>{"use strict";Object.defineProperty(tu,"__esModule",{value:!0});var ub=ae(),WE=he(),KE={message:"property name must be valid",params:({params:t})=>(0,ub._)`{propertyName: ${t.propertyName}}`},YE={keyword:"propertyNames",type:"object",schemaType:["object","boolean"],error:KE,code(t){let{gen:n,schema:e,data:i,it:r}=t;if((0,WE.alwaysValidSchema)(r,e))return;let o=n.name("valid");n.forIn("key",i,a=>{t.setParams({propertyName:a}),t.subschema({keyword:"propertyNames",data:a,dataTypes:["string"],propertyName:a,compositeRule:!0},o),n.if((0,ub.not)(o),()=>{t.error(!0),r.allErrors||n.break()})}),t.ok(o)}};tu.default=YE});var iu=L(nu=>{"use strict";Object.defineProperty(nu,"__esModule",{value:!0});var Il=Wt(),dn=ae(),QE=Hn(),Tl=he(),XE={message:"must NOT have additional properties",params:({params:t})=>(0,dn._)`{additionalProperty: ${t.additionalProperty}}`},ZE={keyword:"additionalProperties",type:["object"],schemaType:["boolean","object"],allowUndefined:!0,trackErrors:!0,error:XE,code(t){let{gen:n,schema:e,parentSchema:i,data:r,errsCount:o,it:a}=t;if(!o)throw new Error("ajv implementation error");let{allErrors:s,opts:l}=a;if(a.props=!0,l.removeAdditional!=="all"&&(0,Tl.alwaysValidSchema)(a,e))return;let c=(0,Il.allSchemaProperties)(i.properties),d=(0,Il.allSchemaProperties)(i.patternProperties);u(),t.ok((0,dn._)`${o} === ${QE.default.errors}`);function u(){n.forIn("key",r,v=>{!c.length&&!d.length?g(v):n.if(p(v),()=>g(v))})}function p(v){let x;if(c.length>8){let S=(0,Tl.schemaRefOrVal)(a,i.properties,"properties");x=(0,Il.isOwnProperty)(n,S,v)}else c.length?x=(0,dn.or)(...c.map(S=>(0,dn._)`${v} === ${S}`)):x=dn.nil;return d.length&&(x=(0,dn.or)(x,...d.map(S=>(0,dn._)`${(0,Il.usePattern)(t,S)}.test(${v})`))),(0,dn.not)(x)}function y(v){n.code((0,dn._)`delete ${r}[${v}]`)}function g(v){if(l.removeAdditional==="all"||l.removeAdditional&&e===!1){y(v);return}if(e===!1){t.setParams({additionalProperty:v}),t.error(),s||n.break();return}if(typeof e=="object"&&!(0,Tl.alwaysValidSchema)(a,e)){let x=n.name("valid");l.removeAdditional==="failing"?(_(v,x,!1),n.if((0,dn.not)(x),()=>{t.reset(),y(v)})):(_(v,x),s||n.if((0,dn.not)(x),()=>n.break()))}}function _(v,x,S){let I={keyword:"additionalProperties",dataProp:v,dataPropType:Tl.Type.Str};S===!1&&Object.assign(I,{compositeRule:!0,createErrors:!1,allErrors:!1}),t.subschema(I,x)}}};nu.default=ZE});var gb=L(ou=>{"use strict";Object.defineProperty(ou,"__esModule",{value:!0});var JE=aa(),hb=Wt(),ru=he(),fb=iu(),eS={keyword:"properties",type:"object",schemaType:"object",code(t){let{gen:n,schema:e,parentSchema:i,data:r,it:o}=t;o.opts.removeAdditional==="all"&&i.additionalProperties===void 0&&fb.default.code(new JE.KeywordCxt(o,fb.default,"additionalProperties"));let a=(0,hb.allSchemaProperties)(e);for(let u of a)o.definedProperties.add(u);o.opts.unevaluated&&a.length&&o.props!==!0&&(o.props=ru.mergeEvaluated.props(n,(0,ru.toHash)(a),o.props));let s=a.filter(u=>!(0,ru.alwaysValidSchema)(o,e[u]));if(s.length===0)return;let l=n.name("valid");for(let u of s)c(u)?d(u):(n.if((0,hb.propertyInData)(n,r,u,o.opts.ownProperties)),d(u),o.allErrors||n.else().var(l,!0),n.endIf()),t.it.definedProperties.add(u),t.ok(l);function c(u){return o.opts.useDefaults&&!o.compositeRule&&e[u].default!==void 0}function d(u){t.subschema({keyword:"properties",schemaProp:u,dataProp:u},l)}}};ou.default=eS});var yb=L(au=>{"use strict";Object.defineProperty(au,"__esModule",{value:!0});var _b=Wt(),Ol=ae(),bb=he(),vb=he(),tS={keyword:"patternProperties",type:"object",schemaType:"object",code(t){let{gen:n,schema:e,data:i,parentSchema:r,it:o}=t,{opts:a}=o,s=(0,_b.allSchemaProperties)(e),l=s.filter(_=>(0,bb.alwaysValidSchema)(o,e[_]));if(s.length===0||l.length===s.length&&(!o.opts.unevaluated||o.props===!0))return;let c=a.strictSchema&&!a.allowMatchingProperties&&r.properties,d=n.name("valid");o.props!==!0&&!(o.props instanceof Ol.Name)&&(o.props=(0,vb.evaluatedPropsToName)(n,o.props));let{props:u}=o;p();function p(){for(let _ of s)c&&y(_),o.allErrors?g(_):(n.var(d,!0),g(_),n.if(d))}function y(_){for(let v in c)new RegExp(_).test(v)&&(0,bb.checkStrictMode)(o,`property ${v} matches pattern ${_} (use allowMatchingProperties)`)}function g(_){n.forIn("key",i,v=>{n.if((0,Ol._)`${(0,_b.usePattern)(t,_)}.test(${v})`,()=>{let x=l.includes(_);x||t.subschema({keyword:"patternProperties",schemaProp:_,dataProp:v,dataPropType:vb.Type.Str},d),o.opts.unevaluated&&u!==!0?n.assign((0,Ol._)`${u}[${v}]`,!0):!x&&!o.allErrors&&n.if((0,Ol.not)(d),()=>n.break())})})}}};au.default=tS});var xb=L(su=>{"use strict";Object.defineProperty(su,"__esModule",{value:!0});var nS=he(),iS={keyword:"not",schemaType:["object","boolean"],trackErrors:!0,code(t){let{gen:n,schema:e,it:i}=t;if((0,nS.alwaysValidSchema)(i,e)){t.fail();return}let r=n.name("valid");t.subschema({keyword:"not",compositeRule:!0,createErrors:!1,allErrors:!1},r),t.failResult(r,()=>t.reset(),()=>t.error())},error:{message:"must NOT be valid"}};su.default=iS});var wb=L(lu=>{"use strict";Object.defineProperty(lu,"__esModule",{value:!0});var rS=Wt(),oS={keyword:"anyOf",schemaType:"array",trackErrors:!0,code:rS.validateUnion,error:{message:"must match a schema in anyOf"}};lu.default=oS});var Cb=L(cu=>{"use strict";Object.defineProperty(cu,"__esModule",{value:!0});var Rl=ae(),aS=he(),sS={message:"must match exactly one schema in oneOf",params:({params:t})=>(0,Rl._)`{passingSchemas: ${t.passing}}`},lS={keyword:"oneOf",schemaType:"array",trackErrors:!0,error:sS,code(t){let{gen:n,schema:e,parentSchema:i,it:r}=t;if(!Array.isArray(e))throw new Error("ajv implementation error");if(r.opts.discriminator&&i.discriminator)return;let o=e,a=n.let("valid",!1),s=n.let("passing",null),l=n.name("_valid");t.setParams({passing:s}),n.block(c),t.result(a,()=>t.reset(),()=>t.error(!0));function c(){o.forEach((d,u)=>{let p;(0,aS.alwaysValidSchema)(r,d)?n.var(l,!0):p=t.subschema({keyword:"oneOf",schemaProp:u,compositeRule:!0},l),u>0&&n.if((0,Rl._)`${l} && ${a}`).assign(a,!1).assign(s,(0,Rl._)`[${s}, ${u}]`).else(),n.if(l,()=>{n.assign(a,!0),n.assign(s,u),p&&t.mergeEvaluated(p,Rl.Name)})})}}};cu.default=lS});var kb=L(du=>{"use strict";Object.defineProperty(du,"__esModule",{value:!0});var cS=he(),dS={keyword:"allOf",schemaType:"array",code(t){let{gen:n,schema:e,it:i}=t;if(!Array.isArray(e))throw new Error("ajv implementation error");let r=n.name("valid");e.forEach((o,a)=>{if((0,cS.alwaysValidSchema)(i,o))return;let s=t.subschema({keyword:"allOf",schemaProp:a},r);t.ok(r),t.mergeEvaluated(s)})}};du.default=dS});var Sb=L(mu=>{"use strict";Object.defineProperty(mu,"__esModule",{value:!0});var Pl=ae(),Eb=he(),mS={message:({params:t})=>(0,Pl.str)`must match "${t.ifClause}" schema`,params:({params:t})=>(0,Pl._)`{failingKeyword: ${t.ifClause}}`},uS={keyword:"if",schemaType:["object","boolean"],trackErrors:!0,error:mS,code(t){let{gen:n,parentSchema:e,it:i}=t;e.then===void 0&&e.else===void 0&&(0,Eb.checkStrictMode)(i,'"if" without "then" and "else" is ignored');let r=Db(i,"then"),o=Db(i,"else");if(!r&&!o)return;let a=n.let("valid",!0),s=n.name("_valid");if(l(),t.reset(),r&&o){let d=n.let("ifClause");t.setParams({ifClause:d}),n.if(s,c("then",d),c("else",d))}else r?n.if(s,c("then")):n.if((0,Pl.not)(s),c("else"));t.pass(a,()=>t.error(!0));function l(){let d=t.subschema({keyword:"if",compositeRule:!0,createErrors:!1,allErrors:!1},s);t.mergeEvaluated(d)}function c(d,u){return()=>{let p=t.subschema({keyword:d},s);n.assign(a,s),t.mergeValidEvaluated(p,a),u?n.assign(u,(0,Pl._)`${d}`):t.setParams({ifClause:d})}}}};function Db(t,n){let e=t.schema[n];return e!==void 0&&!(0,Eb.alwaysValidSchema)(t,e)}mu.default=uS});var Mb=L(uu=>{"use strict";Object.defineProperty(uu,"__esModule",{value:!0});var pS=he(),hS={keyword:["then","else"],schemaType:["object","boolean"],code({keyword:t,parentSchema:n,it:e}){n.if===void 0&&(0,pS.checkStrictMode)(e,`"${t}" without "if" is ignored`)}};uu.default=hS});var Ab=L(pu=>{"use strict";Object.defineProperty(pu,"__esModule",{value:!0});var fS=Ym(),gS=ob(),_S=Qm(),bS=sb(),vS=lb(),yS=mb(),xS=pb(),wS=iu(),CS=gb(),kS=yb(),DS=xb(),ES=wb(),SS=Cb(),MS=kb(),AS=Sb(),IS=Mb();function TS(t=!1){let n=[DS.default,ES.default,SS.default,MS.default,AS.default,IS.default,xS.default,wS.default,yS.default,CS.default,kS.default];return t?n.push(gS.default,bS.default):n.push(fS.default,_S.default),n.push(vS.default),n}pu.default=TS});var Ib=L(hu=>{"use strict";Object.defineProperty(hu,"__esModule",{value:!0});var We=ae(),OS={message:({schemaCode:t})=>(0,We.str)`must match format "${t}"`,params:({schemaCode:t})=>(0,We._)`{format: ${t}}`},RS={keyword:"format",type:["number","string"],schemaType:"string",$data:!0,error:OS,code(t,n){let{gen:e,data:i,$data:r,schema:o,schemaCode:a,it:s}=t,{opts:l,errSchemaPath:c,schemaEnv:d,self:u}=s;if(!l.validateFormats)return;r?p():y();function p(){let g=e.scopeValue("formats",{ref:u.formats,code:l.code.formats}),_=e.const("fDef",(0,We._)`${g}[${a}]`),v=e.let("fType"),x=e.let("format");e.if((0,We._)`typeof ${_} == "object" && !(${_} instanceof RegExp)`,()=>e.assign(v,(0,We._)`${_}.type || "string"`).assign(x,(0,We._)`${_}.validate`),()=>e.assign(v,(0,We._)`"string"`).assign(x,_)),t.fail$data((0,We.or)(S(),I()));function S(){return l.strictSchema===!1?We.nil:(0,We._)`${a} && !${x}`}function I(){let F=d.$async?(0,We._)`(${_}.async ? await ${x}(${i}) : ${x}(${i}))`:(0,We._)`${x}(${i})`,N=(0,We._)`(typeof ${x} == "function" ? ${F} : ${x}.test(${i}))`;return(0,We._)`${x} && ${x} !== true && ${v} === ${n} && !${N}`}}function y(){let g=u.formats[o];if(!g){S();return}if(g===!0)return;let[_,v,x]=I(g);_===n&&t.pass(F());function S(){if(l.strictSchema===!1){u.logger.warn(N());return}throw new Error(N());function N(){return`unknown format "${o}" ignored in schema at path "${c}"`}}function I(N){let ge=N instanceof RegExp?(0,We.regexpCode)(N):l.code.formats?(0,We._)`${l.code.formats}${(0,We.getProperty)(o)}`:void 0,Ce=e.scopeValue("formats",{key:o,ref:N,code:ge});return typeof N=="object"&&!(N instanceof RegExp)?[N.type||"string",N.validate,(0,We._)`${Ce}.validate`]:["string",N,Ce]}function F(){if(typeof g=="object"&&!(g instanceof RegExp)&&g.async){if(!d.$async)throw new Error("async format in sync schema");return(0,We._)`await ${x}(${i})`}return typeof v=="function"?(0,We._)`${x}(${i})`:(0,We._)`${x}.test(${i})`}}}};hu.default=RS});var Tb=L(fu=>{"use strict";Object.defineProperty(fu,"__esModule",{value:!0});var PS=Ib(),FS=[PS.default];fu.default=FS});var Ob=L(Lr=>{"use strict";Object.defineProperty(Lr,"__esModule",{value:!0});Lr.contentVocabulary=Lr.metadataVocabulary=void 0;Lr.metadataVocabulary=["title","description","default","deprecated","readOnly","writeOnly","examples"];Lr.contentVocabulary=["contentMediaType","contentEncoding","contentSchema"]});var Pb=L(gu=>{"use strict";Object.defineProperty(gu,"__esModule",{value:!0});var NS=V_(),LS=tb(),BS=Ab(),$S=Tb(),Rb=Ob(),jS=[NS.default,LS.default,(0,BS.default)(),$S.default,Rb.metadataVocabulary,Rb.contentVocabulary];gu.default=jS});var Nb=L(Fl=>{"use strict";Object.defineProperty(Fl,"__esModule",{value:!0});Fl.DiscrError=void 0;var Fb=(function(t){return t.Tag="tag",t.Mapping="mapping",t})(Fb||(Fl.DiscrError=Fb={}))});var Bb=L(bu=>{"use strict";Object.defineProperty(bu,"__esModule",{value:!0});var Br=ae(),_u=Nb(),Lb=bl(),VS=sa(),zS=he(),HS={message:({params:{discrError:t,tagName:n}})=>t===_u.DiscrError.Tag?`tag "${n}" must be string`:`value of tag "${n}" must be in oneOf`,params:({params:{discrError:t,tag:n,tagName:e}})=>(0,Br._)`{error: ${t}, tag: ${e}, tagValue: ${n}}`},US={keyword:"discriminator",type:"object",schemaType:"object",error:HS,code(t){let{gen:n,data:e,schema:i,parentSchema:r,it:o}=t,{oneOf:a}=r;if(!o.opts.discriminator)throw new Error("discriminator: requires discriminator option");let s=i.propertyName;if(typeof s!="string")throw new Error("discriminator: requires propertyName");if(i.mapping)throw new Error("discriminator: mapping is not supported");if(!a)throw new Error("discriminator: requires oneOf keyword");let l=n.let("valid",!1),c=n.const("tag",(0,Br._)`${e}${(0,Br.getProperty)(s)}`);n.if((0,Br._)`typeof ${c} == "string"`,()=>d(),()=>t.error(!1,{discrError:_u.DiscrError.Tag,tag:c,tagName:s})),t.ok(l);function d(){let y=p();n.if(!1);for(let g in y)n.elseIf((0,Br._)`${c} === ${g}`),n.assign(l,u(y[g]));n.else(),t.error(!1,{discrError:_u.DiscrError.Mapping,tag:c,tagName:s}),n.endIf()}function u(y){let g=n.name("valid"),_=t.subschema({keyword:"oneOf",schemaProp:y},g);return t.mergeEvaluated(_,Br.Name),g}function p(){var y;let g={},_=x(r),v=!0;for(let F=0;F<a.length;F++){let N=a[F];if(N?.$ref&&!(0,zS.schemaHasRulesButRef)(N,o.self.RULES)){let Ce=N.$ref;if(N=Lb.resolveRef.call(o.self,o.schemaEnv.root,o.baseId,Ce),N instanceof Lb.SchemaEnv&&(N=N.schema),N===void 0)throw new VS.default(o.opts.uriResolver,o.baseId,Ce)}let ge=(y=N?.properties)===null||y===void 0?void 0:y[s];if(typeof ge!="object")throw new Error(`discriminator: oneOf subschemas (or referenced schemas) must have "properties/${s}"`);v=v&&(_||x(N)),S(ge,F)}if(!v)throw new Error(`discriminator: "${s}" must be required`);return g;function x({required:F}){return Array.isArray(F)&&F.includes(s)}function S(F,N){if(F.const)I(F.const,N);else if(F.enum)for(let ge of F.enum)I(ge,N);else throw new Error(`discriminator: "properties/${s}" must have "const" or "enum"`)}function I(F,N){if(typeof F!="string"||F in g)throw new Error(`discriminator: "${s}" values must be unique strings`);g[F]=N}}}};bu.default=US});var $b=L((Sj,qS)=>{qS.exports={$schema:"http://json-schema.org/draft-07/schema#",$id:"http://json-schema.org/draft-07/schema#",title:"Core schema meta-schema",definitions:{schemaArray:{type:"array",minItems:1,items:{$ref:"#"}},nonNegativeInteger:{type:"integer",minimum:0},nonNegativeIntegerDefault0:{allOf:[{$ref:"#/definitions/nonNegativeInteger"},{default:0}]},simpleTypes:{enum:["array","boolean","integer","null","number","object","string"]},stringArray:{type:"array",items:{type:"string"},uniqueItems:!0,default:[]}},type:["object","boolean"],properties:{$id:{type:"string",format:"uri-reference"},$schema:{type:"string",format:"uri"},$ref:{type:"string",format:"uri-reference"},$comment:{type:"string"},title:{type:"string"},description:{type:"string"},default:!0,readOnly:{type:"boolean",default:!1},examples:{type:"array",items:!0},multipleOf:{type:"number",exclusiveMinimum:0},maximum:{type:"number"},exclusiveMaximum:{type:"number"},minimum:{type:"number"},exclusiveMinimum:{type:"number"},maxLength:{$ref:"#/definitions/nonNegativeInteger"},minLength:{$ref:"#/definitions/nonNegativeIntegerDefault0"},pattern:{type:"string",format:"regex"},additionalItems:{$ref:"#"},items:{anyOf:[{$ref:"#"},{$ref:"#/definitions/schemaArray"}],default:!0},maxItems:{$ref:"#/definitions/nonNegativeInteger"},minItems:{$ref:"#/definitions/nonNegativeIntegerDefault0"},uniqueItems:{type:"boolean",default:!1},contains:{$ref:"#"},maxProperties:{$ref:"#/definitions/nonNegativeInteger"},minProperties:{$ref:"#/definitions/nonNegativeIntegerDefault0"},required:{$ref:"#/definitions/stringArray"},additionalProperties:{$ref:"#"},definitions:{type:"object",additionalProperties:{$ref:"#"},default:{}},properties:{type:"object",additionalProperties:{$ref:"#"},default:{}},patternProperties:{type:"object",additionalProperties:{$ref:"#"},propertyNames:{format:"regex"},default:{}},dependencies:{type:"object",additionalProperties:{anyOf:[{$ref:"#"},{$ref:"#/definitions/stringArray"}]}},propertyNames:{$ref:"#"},const:!0,enum:{type:"array",items:!0,minItems:1,uniqueItems:!0},type:{anyOf:[{$ref:"#/definitions/simpleTypes"},{type:"array",items:{$ref:"#/definitions/simpleTypes"},minItems:1,uniqueItems:!0}]},format:{type:"string"},contentMediaType:{type:"string"},contentEncoding:{type:"string"},if:{$ref:"#"},then:{$ref:"#"},else:{$ref:"#"},allOf:{$ref:"#/definitions/schemaArray"},anyOf:{$ref:"#/definitions/schemaArray"},oneOf:{$ref:"#/definitions/schemaArray"},not:{$ref:"#"}},default:!0}});var Vb=L((Re,vu)=>{"use strict";Object.defineProperty(Re,"__esModule",{value:!0});Re.MissingRefError=Re.ValidationError=Re.CodeGen=Re.Name=Re.nil=Re.stringify=Re.str=Re._=Re.KeywordCxt=Re.Ajv=void 0;var GS=F_(),WS=Pb(),KS=Bb(),jb=$b(),YS=["/properties"],Nl="http://json-schema.org/draft-07/schema",$r=class extends GS.default{_addVocabularies(){super._addVocabularies(),WS.default.forEach(n=>this.addVocabulary(n)),this.opts.discriminator&&this.addKeyword(KS.default)}_addDefaultMetaSchema(){if(super._addDefaultMetaSchema(),!this.opts.meta)return;let n=this.opts.$data?this.$dataMetaSchema(jb,YS):jb;this.addMetaSchema(n,Nl,!1),this.refs["http://json-schema.org/schema"]=Nl}defaultMeta(){return this.opts.defaultMeta=super.defaultMeta()||(this.getSchema(Nl)?Nl:void 0)}};Re.Ajv=$r;vu.exports=Re=$r;vu.exports.Ajv=$r;Object.defineProperty(Re,"__esModule",{value:!0});Re.default=$r;var QS=aa();Object.defineProperty(Re,"KeywordCxt",{enumerable:!0,get:function(){return QS.KeywordCxt}});var jr=ae();Object.defineProperty(Re,"_",{enumerable:!0,get:function(){return jr._}});Object.defineProperty(Re,"str",{enumerable:!0,get:function(){return jr.str}});Object.defineProperty(Re,"stringify",{enumerable:!0,get:function(){return jr.stringify}});Object.defineProperty(Re,"nil",{enumerable:!0,get:function(){return jr.nil}});Object.defineProperty(Re,"Name",{enumerable:!0,get:function(){return jr.Name}});Object.defineProperty(Re,"CodeGen",{enumerable:!0,get:function(){return jr.CodeGen}});var XS=gl();Object.defineProperty(Re,"ValidationError",{enumerable:!0,get:function(){return XS.default}});var ZS=sa();Object.defineProperty(Re,"MissingRefError",{enumerable:!0,get:function(){return ZS.default}})});var Qp=null;function On(){return Qp}function Ec(t){Qp??=t}var wo=class{},cr=(()=>{class t{historyGo(e){throw new Error("")}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:()=>m(Xp),providedIn:"platform"})}return t})();var Xp=(()=>{class t extends cr{_location;_history;_doc=m(W);constructor(){super(),this._location=window.location,this._history=window.history}getBaseHrefFromDOM(){return On().getBaseHref(this._doc)}onPopState(e){let i=On().getGlobalEventTarget(this._doc,"window");return i.addEventListener("popstate",e,!1),()=>i.removeEventListener("popstate",e)}onHashChange(e){let i=On().getGlobalEventTarget(this._doc,"window");return i.addEventListener("hashchange",e,!1),()=>i.removeEventListener("hashchange",e)}get href(){return this._location.href}get protocol(){return this._location.protocol}get hostname(){return this._location.hostname}get port(){return this._location.port}get pathname(){return this._location.pathname}get search(){return this._location.search}get hash(){return this._location.hash}set pathname(e){this._location.pathname=e}pushState(e,i,r){this._history.pushState(e,i,r)}replaceState(e,i,r){this._history.replaceState(e,i,r)}forward(){this._history.forward()}back(){this._history.back()}historyGo(e=0){this._history.go(e)}getState(){return this._history.state}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:()=>new t,providedIn:"platform"})}return t})();function eh(t,n){return t?n?t.endsWith("/")?n.startsWith("/")?t+n.slice(1):t+n:n.startsWith("/")?t+n:`${t}/${n}`:t:n}function Zp(t){let n=t.search(/#|\?|$/);return t[n-1]==="/"?t.slice(0,n-1)+t.slice(n):t}function ii(t){return t&&t[0]!=="?"?`?${t}`:t}var is=(()=>{class t{historyGo(e){throw new Error("")}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:()=>m(Xv),providedIn:"root"})}return t})(),Qv=new A(""),Xv=(()=>{class t extends is{_platformLocation;_baseHref;_removeListenerFns=[];constructor(e,i){super(),this._platformLocation=e,this._baseHref=i??this._platformLocation.getBaseHrefFromDOM()??m(W).location?.origin??""}ngOnDestroy(){for(;this._removeListenerFns.length;)this._removeListenerFns.pop()()}onPopState(e){this._removeListenerFns.push(this._platformLocation.onPopState(e),this._platformLocation.onHashChange(e))}getBaseHref(){return this._baseHref}prepareExternalUrl(e){return eh(this._baseHref,e)}path(e=!1){let i=this._platformLocation.pathname+ii(this._platformLocation.search),r=this._platformLocation.hash;return r&&e?`${i}${r}`:i}pushState(e,i,r,o){let a=this.prepareExternalUrl(r+ii(o));this._platformLocation.pushState(e,i,a)}replaceState(e,i,r,o){let a=this.prepareExternalUrl(r+ii(o));this._platformLocation.replaceState(e,i,a)}forward(){this._platformLocation.forward()}back(){this._platformLocation.back()}getState(){return this._platformLocation.getState()}historyGo(e=0){this._platformLocation.historyGo?.(e)}static \u0275fac=function(i){return new(i||t)(j(cr),j(Qv,8))};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var dr=(()=>{class t{_subject=new z;_basePath;_locationStrategy;_urlChangeListeners=[];_urlChangeSubscription=null;constructor(e){this._locationStrategy=e;let i=this._locationStrategy.getBaseHref();this._basePath=ey(Zp(Jp(i))),this._locationStrategy.onPopState(r=>{this._subject.next({url:this.path(!0),pop:!0,state:r.state,type:r.type})})}ngOnDestroy(){this._urlChangeSubscription?.unsubscribe(),this._urlChangeListeners=[]}path(e=!1){return this.normalize(this._locationStrategy.path(e))}getState(){return this._locationStrategy.getState()}isCurrentPathEqualTo(e,i=""){return this.path()==this.normalize(e+ii(i))}normalize(e){return t.stripTrailingSlash(Jv(this._basePath,Jp(e)))}prepareExternalUrl(e){return e&&e[0]!=="/"&&(e="/"+e),this._locationStrategy.prepareExternalUrl(e)}go(e,i="",r=null){this._locationStrategy.pushState(r,"",e,i),this._notifyUrlChangeListeners(this.prepareExternalUrl(e+ii(i)),r)}replaceState(e,i="",r=null){this._locationStrategy.replaceState(r,"",e,i),this._notifyUrlChangeListeners(this.prepareExternalUrl(e+ii(i)),r)}forward(){this._locationStrategy.forward()}back(){this._locationStrategy.back()}historyGo(e=0){this._locationStrategy.historyGo?.(e)}onUrlChange(e){return this._urlChangeListeners.push(e),this._urlChangeSubscription??=this.subscribe(i=>{this._notifyUrlChangeListeners(i.url,i.state)}),()=>{let i=this._urlChangeListeners.indexOf(e);this._urlChangeListeners.splice(i,1),this._urlChangeListeners.length===0&&(this._urlChangeSubscription?.unsubscribe(),this._urlChangeSubscription=null)}}_notifyUrlChangeListeners(e="",i){this._urlChangeListeners.forEach(r=>r(e,i))}subscribe(e,i,r){return this._subject.subscribe({next:e,error:i??void 0,complete:r??void 0})}static normalizeQueryParams=ii;static joinWithSlash=eh;static stripTrailingSlash=Zp;static \u0275fac=function(i){return new(i||t)(j(is))};static \u0275prov=E({token:t,factory:()=>Zv(),providedIn:"root"})}return t})();function Zv(){return new dr(j(is))}function Jv(t,n){if(!t||!n.startsWith(t))return n;let e=n.substring(t.length);return e===""||["/",";","?","#"].includes(e[0])?e:n}function Jp(t){return t.replace(/\/index.html$/,"")}function ey(t){if(new RegExp("^(https?:)?//").test(t)){let[,e]=t.split(/\/\/[^\/]+/);return e}return t}var Sc=/\s+/,th=[],Co=(()=>{class t{_ngEl;_renderer;initialClasses=th;rawClass;stateMap=new Map;constructor(e,i){this._ngEl=e,this._renderer=i}set klass(e){this.initialClasses=e!=null?e.trim().split(Sc):th}set ngClass(e){this.rawClass=typeof e=="string"?e.trim().split(Sc):e}ngDoCheck(){for(let i of this.initialClasses)this._updateState(i,!0);let e=this.rawClass;if(Array.isArray(e)||e instanceof Set)for(let i of e)this._updateState(i,!0);else if(e!=null)for(let i of Object.keys(e))this._updateState(i,!!e[i]);this._applyStateDiff()}_updateState(e,i){let r=this.stateMap.get(e);r!==void 0?(r.enabled!==i&&(r.changed=!0,r.enabled=i),r.touched=!0):this.stateMap.set(e,{enabled:i,changed:!0,touched:!0})}_applyStateDiff(){for(let e of this.stateMap){let i=e[0],r=e[1];r.changed?(this._toggleClass(i,r.enabled),r.changed=!1):r.touched||(r.enabled&&this._toggleClass(i,!1),this.stateMap.delete(i)),r.touched=!1}}_toggleClass(e,i){e=e.trim(),e.length>0&&e.split(Sc).forEach(r=>{i?this._renderer.addClass(this._ngEl.nativeElement,r):this._renderer.removeClass(this._ngEl.nativeElement,r)})}static \u0275fac=function(i){return new(i||t)(Ee(Y),Ee(rt))};static \u0275dir=G({type:t,selectors:[["","ngClass",""]],inputs:{klass:[0,"class","klass"],ngClass:"ngClass"}})}return t})();var ko=(()=>{class t{_viewContainerRef;_viewRef=null;ngTemplateOutletContext=null;ngTemplateOutlet=null;ngTemplateOutletInjector=null;injector=m(te);constructor(e){this._viewContainerRef=e}ngOnChanges(e){if(this._shouldRecreateView(e)){let i=this._viewContainerRef;if(this._viewRef&&i.remove(i.indexOf(this._viewRef)),!this.ngTemplateOutlet){this._viewRef=null;return}let r=this._createContextForwardProxy();this._viewRef=i.createEmbeddedView(this.ngTemplateOutlet,r,{injector:this._getInjector()})}}_getInjector(){return this.ngTemplateOutletInjector==="outlet"?this.injector:this.ngTemplateOutletInjector??void 0}_shouldRecreateView(e){return!!e.ngTemplateOutlet||!!e.ngTemplateOutletInjector}_createContextForwardProxy(){return new Proxy({},{set:(e,i,r)=>this.ngTemplateOutletContext?Reflect.set(this.ngTemplateOutletContext,i,r):!1,get:(e,i,r)=>{if(this.ngTemplateOutletContext)return Reflect.get(this.ngTemplateOutletContext,i,r)}})}static \u0275fac=function(i){return new(i||t)(Ee(hn))};static \u0275dir=G({type:t,selectors:[["","ngTemplateOutlet",""]],inputs:{ngTemplateOutletContext:"ngTemplateOutletContext",ngTemplateOutlet:"ngTemplateOutlet",ngTemplateOutletInjector:"ngTemplateOutletInjector"},features:[dt]})}return t})();function iy(t,n){return{key:t,value:n}}var Do=(()=>{class t{differs;constructor(e){this.differs=e}differ;keyValues=[];compareFn=nh;transform(e,i=nh){if(!e||!(e instanceof Map)&&typeof e!="object")return null;this.differ??=this.differs.find(e).create();let r=this.differ.diff(e),o=i!==this.compareFn;return r&&(this.keyValues=[],r.forEachItem(a=>{this.keyValues.push(iy(a.key,a.currentValue))})),(r||o)&&(i&&this.keyValues.sort(i),this.compareFn=i),this.keyValues}static \u0275fac=function(i){return new(i||t)(Ee(Wp,16))};static \u0275pipe=vc({name:"keyvalue",type:t,pure:!1})}return t})();function nh(t,n){let e=t.key,i=n.key;if(e===i)return 0;if(e==null)return 1;if(i==null)return-1;if(typeof e=="string"&&typeof i=="string")return e<i?-1:1;if(typeof e=="number"&&typeof i=="number")return e-i;if(typeof e=="boolean"&&typeof i=="boolean")return e<i?-1:1;let r=String(e),o=String(i);return r==o?0:r<o?-1:1}var fn=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({})}return t})();function Eo(t,n){n=encodeURIComponent(n);for(let e of t.split(";")){let i=e.indexOf("="),[r,o]=i==-1?[e,""]:[e.slice(0,i),e.slice(i+1)];if(r.trim()===n)return decodeURIComponent(o)}return null}var tn=class{};var Ac="browser";function Rn(t){return t===Ac}var Mo=class{_doc;constructor(n){this._doc=n}manager},rs=(()=>{class t extends Mo{constructor(e){super(e)}supports(e){return!0}addEventListener(e,i,r,o){return e.addEventListener(i,r,o),()=>this.removeEventListener(e,i,r,o)}removeEventListener(e,i,r,o){return e.removeEventListener(i,r,o)}static \u0275fac=function(i){return new(i||t)(j(W))};static \u0275prov=E({token:t,factory:t.\u0275fac})}return t})(),ss=new A(""),Rc=(()=>{class t{_zone;_plugins;_eventNameToPlugin=new Map;constructor(e,i){this._zone=i,e.forEach(a=>{a.manager=this});let r=e.filter(a=>!(a instanceof rs));this._plugins=r.slice().reverse();let o=e.find(a=>a instanceof rs);o&&this._plugins.push(o)}addEventListener(e,i,r,o){return this._findPluginFor(i).addEventListener(e,i,r,o)}getZone(){return this._zone}_findPluginFor(e){let i=this._eventNameToPlugin.get(e);if(i)return i;if(i=this._plugins.find(o=>o.supports(e)),!i)throw new Fe(5101,!1);return this._eventNameToPlugin.set(e,i),i}static \u0275fac=function(i){return new(i||t)(j(ss),j(K))};static \u0275prov=E({token:t,factory:t.\u0275fac})}return t})(),Ic="ng-app-id";function rh(t){for(let n of t)n.remove()}function oh(t,n){let e=n.createElement("style");return e.textContent=t,e}function oy(t,n,e,i){let r=t.head?.querySelectorAll(`style[${Ic}="${n}"],link[${Ic}="${n}"]`);if(r)for(let o of r)o.removeAttribute(Ic),o instanceof HTMLLinkElement?i.set(o.href.slice(o.href.lastIndexOf("/")+1),{usage:0,elements:[o]}):o.textContent&&e.set(o.textContent,{usage:0,elements:[o]})}function Oc(t,n){let e=n.createElement("link");return e.setAttribute("rel","stylesheet"),e.setAttribute("href",t),e}var Pc=(()=>{class t{doc;appId;nonce;inline=new Map;external=new Map;hosts=new Set;constructor(e,i,r,o={}){this.doc=e,this.appId=i,this.nonce=r,oy(e,i,this.inline,this.external),this.hosts.add(e.head)}addStyles(e,i){for(let r of e)this.addUsage(r,this.inline,oh);i?.forEach(r=>this.addUsage(r,this.external,Oc))}removeStyles(e,i){for(let r of e)this.removeUsage(r,this.inline);i?.forEach(r=>this.removeUsage(r,this.external))}addUsage(e,i,r){let o=i.get(e);o?o.usage++:i.set(e,{usage:1,elements:[...this.hosts].map(a=>this.addElement(a,r(e,this.doc)))})}removeUsage(e,i){let r=i.get(e);r&&(r.usage--,r.usage<=0&&(rh(r.elements),i.delete(e)))}ngOnDestroy(){for(let[,{elements:e}]of[...this.inline,...this.external])rh(e);this.hosts.clear()}addHost(e){this.hosts.add(e);for(let[i,{elements:r}]of this.inline)r.push(this.addElement(e,oh(i,this.doc)));for(let[i,{elements:r}]of this.external)r.push(this.addElement(e,Oc(i,this.doc)))}removeHost(e){this.hosts.delete(e)}addElement(e,i){return this.nonce&&i.setAttribute("nonce",this.nonce),e.appendChild(i)}static \u0275fac=function(i){return new(i||t)(j(W),j(ho),j(nr,8),j(Zn))};static \u0275prov=E({token:t,factory:t.\u0275fac})}return t})(),Tc={svg:"http://www.w3.org/2000/svg",xhtml:"http://www.w3.org/1999/xhtml",xlink:"http://www.w3.org/1999/xlink",xml:"http://www.w3.org/XML/1998/namespace",xmlns:"http://www.w3.org/2000/xmlns/",math:"http://www.w3.org/1998/Math/MathML"},Fc=/%COMP%/g;var sh="%COMP%",ay=`_nghost-${sh}`,sy=`_ngcontent-${sh}`,ly=!0,cy=new A("",{factory:()=>ly});function dy(t){return sy.replace(Fc,t)}function my(t){return ay.replace(Fc,t)}function lh(t,n){return n.map(e=>e.replace(Fc,t))}var To=(()=>{class t{eventManager;sharedStylesHost;appId;removeStylesOnCompDestroy;doc;ngZone;nonce;tracingService;rendererByCompId=new Map;defaultRenderer;constructor(e,i,r,o,a,s,l=null,c=null){this.eventManager=e,this.sharedStylesHost=i,this.appId=r,this.removeStylesOnCompDestroy=o,this.doc=a,this.ngZone=s,this.nonce=l,this.tracingService=c,this.defaultRenderer=new Ao(e,a,s,this.tracingService)}createRenderer(e,i){if(!e||!i)return this.defaultRenderer;let r=this.getOrCreateRenderer(e,i);return r instanceof as?r.applyToHost(e):r instanceof Io&&r.applyStyles(),r}getOrCreateRenderer(e,i){let r=this.rendererByCompId,o=r.get(i.id);if(!o){let a=this.doc,s=this.ngZone,l=this.eventManager,c=this.sharedStylesHost,d=this.removeStylesOnCompDestroy,u=this.tracingService;switch(i.encapsulation){case Wa.Emulated:o=new as(l,c,i,this.appId,d,a,s,u);break;case Wa.ShadowDom:return new os(l,e,i,a,s,this.nonce,u,c);case Wa.ExperimentalIsolatedShadowDom:return new os(l,e,i,a,s,this.nonce,u);default:o=new Io(l,c,i,d,a,s,u);break}r.set(i.id,o)}return o}ngOnDestroy(){this.rendererByCompId.clear()}componentReplaced(e){this.rendererByCompId.delete(e)}static \u0275fac=function(i){return new(i||t)(j(Rc),j(Pc),j(ho),j(cy),j(W),j(K),j(nr),j(Ka,8))};static \u0275prov=E({token:t,factory:t.\u0275fac})}return t})(),Ao=class{eventManager;doc;ngZone;tracingService;data=Object.create(null);throwOnSyntheticProps=!0;constructor(n,e,i,r){this.eventManager=n,this.doc=e,this.ngZone=i,this.tracingService=r}destroy(){}destroyNode=null;createElement(n,e){return e?this.doc.createElementNS(Tc[e]||e,n):this.doc.createElement(n)}createComment(n){return this.doc.createComment(n)}createText(n){return this.doc.createTextNode(n)}appendChild(n,e){(ah(n)?n.content:n).appendChild(e)}insertBefore(n,e,i){n&&(ah(n)?n.content:n).insertBefore(e,i)}removeChild(n,e){e.remove()}selectRootElement(n,e){let i=typeof n=="string"?this.doc.querySelector(n):n;if(!i)throw new Fe(-5104,!1);return e||(i.textContent=""),i}parentNode(n){return n.parentNode}nextSibling(n){return n.nextSibling}setAttribute(n,e,i,r){if(r){e=r+":"+e;let o=Tc[r];o?n.setAttributeNS(o,e,i):n.setAttribute(e,i)}else n.setAttribute(e,i)}removeAttribute(n,e,i){if(i){let r=Tc[i];r?n.removeAttributeNS(r,e):n.removeAttribute(`${i}:${e}`)}else n.removeAttribute(e)}addClass(n,e){n.classList.add(e)}removeClass(n,e){n.classList.remove(e)}setStyle(n,e,i,r){r&(or.DashCase|or.Important)?n.style.setProperty(e,i,r&or.Important?"important":""):n.style[e]=i}removeStyle(n,e,i){i&or.DashCase?n.style.removeProperty(e):n.style[e]=""}setProperty(n,e,i){n!=null&&(n[e]=i)}setValue(n,e){n.nodeValue=e}listen(n,e,i,r){if(typeof n=="string"&&(n=On().getGlobalEventTarget(this.doc,n),!n))throw new Fe(5102,!1);let o=this.decoratePreventDefault(i);return this.tracingService?.wrapEventListener&&(o=this.tracingService.wrapEventListener(n,e,o)),this.eventManager.addEventListener(n,e,o,r)}decoratePreventDefault(n){return e=>{if(e==="__ngUnwrap__")return n;n(e)===!1&&e.preventDefault()}}};function ah(t){return t.tagName==="TEMPLATE"&&t.content!==void 0}var os=class extends Ao{hostEl;sharedStylesHost;shadowRoot;constructor(n,e,i,r,o,a,s,l){super(n,r,o,s),this.hostEl=e,this.sharedStylesHost=l,this.shadowRoot=e.attachShadow({mode:"open"}),this.sharedStylesHost&&this.sharedStylesHost.addHost(this.shadowRoot);let c=i.styles;c=lh(i.id,c);for(let u of c){let p=document.createElement("style");a&&p.setAttribute("nonce",a),p.textContent=u,this.shadowRoot.appendChild(p)}let d=i.getExternalStyles?.();if(d)for(let u of d){let p=Oc(u,r);a&&p.setAttribute("nonce",a),this.shadowRoot.appendChild(p)}}nodeOrShadowRoot(n){return n===this.hostEl?this.shadowRoot:n}appendChild(n,e){return super.appendChild(this.nodeOrShadowRoot(n),e)}insertBefore(n,e,i){return super.insertBefore(this.nodeOrShadowRoot(n),e,i)}removeChild(n,e){return super.removeChild(null,e)}parentNode(n){return this.nodeOrShadowRoot(super.parentNode(this.nodeOrShadowRoot(n)))}destroy(){this.sharedStylesHost&&this.sharedStylesHost.removeHost(this.shadowRoot)}},Io=class extends Ao{sharedStylesHost;removeStylesOnCompDestroy;styles;styleUrls;constructor(n,e,i,r,o,a,s,l){super(n,o,a,s),this.sharedStylesHost=e,this.removeStylesOnCompDestroy=r;let c=i.styles;this.styles=l?lh(l,c):c,this.styleUrls=i.getExternalStyles?.(l)}applyStyles(){this.sharedStylesHost.addStyles(this.styles,this.styleUrls)}destroy(){this.removeStylesOnCompDestroy&&Lp.size===0&&this.sharedStylesHost.removeStyles(this.styles,this.styleUrls)}},as=class extends Io{contentAttr;hostAttr;constructor(n,e,i,r,o,a,s,l){let c=r+"-"+i.id;super(n,e,i,o,a,s,l,c),this.contentAttr=dy(c),this.hostAttr=my(c)}applyToHost(n){this.applyStyles(),this.setAttribute(n,this.hostAttr,"")}createElement(n,e){let i=super.createElement(n,e);return super.setAttribute(i,this.contentAttr,""),i}};var ls=class t extends wo{supportsDOMEvents=!0;static makeCurrent(){Ec(new t)}onAndCancel(n,e,i,r){return n.addEventListener(e,i,r),()=>{n.removeEventListener(e,i,r)}}dispatchEvent(n,e){n.dispatchEvent(e)}remove(n){n.remove()}createElement(n,e){return e=e||this.getDefaultDocument(),e.createElement(n)}createHtmlDocument(){return document.implementation.createHTMLDocument("fakeTitle")}getDefaultDocument(){return document}isElementNode(n){return n.nodeType===Node.ELEMENT_NODE}isShadowRoot(n){return n instanceof DocumentFragment}getGlobalEventTarget(n,e){return e==="window"?window:e==="document"?n:e==="body"?n.body:null}getBaseHref(n){let e=py();return e==null?null:hy(e)}resetBaseElement(){Oo=null}getUserAgent(){return window.navigator.userAgent}getCookie(n){return Eo(document.cookie,n)}},Oo=null;function py(){return Oo=Oo||document.head.querySelector("base"),Oo?Oo.getAttribute("href"):null}function hy(t){return new URL(t,document.baseURI).pathname}var fy=(()=>{class t{build(){return new XMLHttpRequest}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac})}return t})(),ch=["alt","control","meta","shift"],gy={"\b":"Backspace","	":"Tab","\x7F":"Delete","\x1B":"Escape",Del:"Delete",Esc:"Escape",Left:"ArrowLeft",Right:"ArrowRight",Up:"ArrowUp",Down:"ArrowDown",Menu:"ContextMenu",Scroll:"ScrollLock",Win:"OS"},_y={alt:t=>t.altKey,control:t=>t.ctrlKey,meta:t=>t.metaKey,shift:t=>t.shiftKey},dh=(()=>{class t extends Mo{constructor(e){super(e)}supports(e){return t.parseEventName(e)!=null}addEventListener(e,i,r,o){let a=t.parseEventName(i),s=t.eventCallback(a.fullKey,r,this.manager.getZone());return this.manager.getZone().runOutsideAngular(()=>On().onAndCancel(e,a.domEventName,s,o))}static parseEventName(e){let i=e.toLowerCase().split("."),r=i.shift();if(i.length===0||!(r==="keydown"||r==="keyup"))return null;let o=t._normalizeKey(i.pop()),a="",s=i.indexOf("code");if(s>-1&&(i.splice(s,1),a="code."),ch.forEach(c=>{let d=i.indexOf(c);d>-1&&(i.splice(d,1),a+=c+".")}),a+=o,i.length!=0||o.length===0)return null;let l={};return l.domEventName=r,l.fullKey=a,l}static matchEventFullKeyCode(e,i){let r=gy[e.key]||e.key,o="";return i.indexOf("code.")>-1&&(r=e.code,o="code."),r==null||!r?!1:(r=r.toLowerCase(),r===" "?r="space":r==="."&&(r="dot"),ch.forEach(a=>{if(a!==r){let s=_y[a];s(e)&&(o+=a+".")}}),o+=r,o===i)}static eventCallback(e,i,r){return o=>{t.matchEventFullKeyCode(o,e)&&r.runGuarded(()=>i(o))}}static _normalizeKey(e){return e==="esc"?"escape":e}static \u0275fac=function(i){return new(i||t)(j(W))};static \u0275prov=E({token:t,factory:t.\u0275fac})}return t})();async function Nc(t,n,e){let i=M({rootComponent:t},by(n,e));return Kp(i)}function by(t,n){return{platformRef:n?.platformRef,appProviders:[...Cy,...t?.providers??[]],platformProviders:wy}}function vy(){ls.makeCurrent()}function yy(){return new bi}function xy(){return Mp(document),document}var wy=[{provide:Zn,useValue:Ac},{provide:Ap,useValue:vy,multi:!0},{provide:W,useFactory:xy}];var Cy=[{provide:Dp,useValue:"root"},{provide:bi,useFactory:yy},{provide:ss,useClass:rs,multi:!0},{provide:ss,useClass:dh,multi:!0},To,Pc,Rc,{provide:bt,useExisting:To},{provide:tn,useClass:fy},[]];var gn=class t{headers;normalizedNames=new Map;lazyInit;lazyUpdate=null;constructor(n){n?typeof n=="string"?this.lazyInit=()=>{this.headers=new Map,n.split(`
`).forEach(e=>{let i=e.indexOf(":");if(i>0){let r=e.slice(0,i),o=e.slice(i+1).trim();this.addHeaderEntry(r,o)}})}:typeof Headers<"u"&&n instanceof Headers?(this.headers=new Map,n.forEach((e,i)=>{this.addHeaderEntry(i,e)})):this.lazyInit=()=>{this.headers=new Map,Object.entries(n).forEach(([e,i])=>{this.setHeaderEntries(e,i)})}:this.headers=new Map}has(n){return this.init(),this.headers.has(n.toLowerCase())}get(n){this.init();let e=this.headers.get(n.toLowerCase());return e&&e.length>0?e[0]:null}keys(){return this.init(),Array.from(this.normalizedNames.values())}getAll(n){return this.init(),this.headers.get(n.toLowerCase())||null}append(n,e){return this.clone({name:n,value:e,op:"a"})}set(n,e){return this.clone({name:n,value:e,op:"s"})}delete(n,e){return this.clone({name:n,value:e,op:"d"})}maybeSetNormalizedName(n,e){this.normalizedNames.has(e)||this.normalizedNames.set(e,n)}init(){this.lazyInit&&(this.lazyInit instanceof t?this.copyFrom(this.lazyInit):this.lazyInit(),this.lazyInit=null,this.lazyUpdate&&(this.lazyUpdate.forEach(n=>this.applyUpdate(n)),this.lazyUpdate=null))}copyFrom(n){n.init(),Array.from(n.headers.keys()).forEach(e=>{this.headers.set(e,n.headers.get(e)),this.normalizedNames.set(e,n.normalizedNames.get(e))})}clone(n){let e=new t;return e.lazyInit=this.lazyInit&&this.lazyInit instanceof t?this.lazyInit:this,e.lazyUpdate=(this.lazyUpdate||[]).concat([n]),e}applyUpdate(n){let e=n.name.toLowerCase();switch(n.op){case"a":case"s":let i=n.value;if(typeof i=="string"&&(i=[i]),i.length===0)return;this.maybeSetNormalizedName(n.name,e);let r=(n.op==="a"?this.headers.get(e):void 0)||[];r.push(...i),this.headers.set(e,r);break;case"d":let o=n.value;if(!o)this.headers.delete(e),this.normalizedNames.delete(e);else{let a=this.headers.get(e);if(!a)return;a=a.filter(s=>o.indexOf(s)===-1),a.length===0?(this.headers.delete(e),this.normalizedNames.delete(e)):this.headers.set(e,a)}break}}addHeaderEntry(n,e){let i=n.toLowerCase();this.maybeSetNormalizedName(n,i),this.headers.has(i)?this.headers.get(i).push(e):this.headers.set(i,[e])}setHeaderEntries(n,e){let i=(Array.isArray(e)?e:[e]).map(o=>o.toString()),r=n.toLowerCase();this.headers.set(r,i),this.maybeSetNormalizedName(n,r)}forEach(n){this.init(),Array.from(this.normalizedNames.keys()).forEach(e=>n(this.normalizedNames.get(e),this.headers.get(e)))}};var ds=class{map=new Map;set(n,e){return this.map.set(n,e),this}get(n){return this.map.has(n)||this.map.set(n,n.defaultValue()),this.map.get(n)}delete(n){return this.map.delete(n),this}has(n){return this.map.has(n)}keys(){return this.map.keys()}},ms=class{encodeKey(n){return mh(n)}encodeValue(n){return mh(n)}decodeKey(n){return decodeURIComponent(n)}decodeValue(n){return decodeURIComponent(n)}};function ky(t,n){let e=new Map;return t.length>0&&t.replace(/^\?/,"").split("&").forEach(r=>{let o=r.indexOf("="),[a,s]=o==-1?[n.decodeKey(r),""]:[n.decodeKey(r.slice(0,o)),n.decodeValue(r.slice(o+1))],l=e.get(a)||[];l.push(s),e.set(a,l)}),e}var Dy=/%(\d[a-f0-9])/gi,Ey={40:"@","3A":":",24:"$","2C":",","3B":";","3D":"=","3F":"?","2F":"/"};function mh(t){return encodeURIComponent(t).replace(Dy,(n,e)=>Ey[e]??n)}function cs(t){return`${t}`}var Bt=class t{map;encoder;updates=null;cloneFrom=null;constructor(n={}){if(this.encoder=n.encoder||new ms,n.fromString){if(n.fromObject)throw new Fe(2805,!1);this.map=ky(n.fromString,this.encoder)}else n.fromObject?(this.map=new Map,Object.keys(n.fromObject).forEach(e=>{let i=n.fromObject[e],r=Array.isArray(i)?i.map(cs):[cs(i)];this.map.set(e,r)})):this.map=null}has(n){return this.init(),this.map.has(n)}get(n){this.init();let e=this.map.get(n);return e?e[0]:null}getAll(n){return this.init(),this.map.get(n)||null}keys(){return this.init(),Array.from(this.map.keys())}append(n,e){return this.clone({param:n,value:e,op:"a"})}appendAll(n){let e=[];return Object.keys(n).forEach(i=>{let r=n[i];Array.isArray(r)?r.forEach(o=>{e.push({param:i,value:o,op:"a"})}):e.push({param:i,value:r,op:"a"})}),this.clone(e)}set(n,e){return this.clone({param:n,value:e,op:"s"})}delete(n,e){return this.clone({param:n,value:e,op:"d"})}toString(){return this.init(),this.keys().map(n=>{let e=this.encoder.encodeKey(n);return this.map.get(n).map(i=>e+"="+this.encoder.encodeValue(i)).join("&")}).filter(n=>n!=="").join("&")}clone(n){let e=new t({encoder:this.encoder});return e.cloneFrom=this.cloneFrom||this,e.updates=(this.updates||[]).concat(n),e}init(){this.map===null&&(this.map=new Map),this.cloneFrom!==null&&(this.cloneFrom.init(),this.cloneFrom.keys().forEach(n=>this.map.set(n,this.cloneFrom.map.get(n))),this.updates.forEach(n=>{switch(n.op){case"a":case"s":let e=(n.op==="a"?this.map.get(n.param):void 0)||[];e.push(cs(n.value)),this.map.set(n.param,e);break;case"d":if(n.value!==void 0){let i=this.map.get(n.param)||[],r=i.indexOf(cs(n.value));r!==-1&&i.splice(r,1),i.length>0?this.map.set(n.param,i):this.map.delete(n.param)}else{this.map.delete(n.param);break}}}),this.cloneFrom=this.updates=null)}};function Sy(t){switch(t){case"DELETE":case"GET":case"HEAD":case"OPTIONS":case"JSONP":return!1;default:return!0}}function uh(t){return typeof ArrayBuffer<"u"&&t instanceof ArrayBuffer}function ph(t){return typeof Blob<"u"&&t instanceof Blob}function hh(t){return typeof FormData<"u"&&t instanceof FormData}function My(t){return typeof URLSearchParams<"u"&&t instanceof URLSearchParams}var fh="Content-Type",gh="Accept",bh="text/plain",vh="application/json",Ay=`${vh}, ${bh}, */*`,mr=class t{url;body=null;headers;context;reportProgress=!1;withCredentials=!1;credentials;keepalive=!1;cache;priority;mode;redirect;referrer;integrity;referrerPolicy;responseType="json";method;params;urlWithParams;transferCache;timeout;constructor(n,e,i,r){this.url=e,this.method=n.toUpperCase();let o;if(Sy(this.method)||r?(this.body=i!==void 0?i:null,o=r):o=i,o){if(this.reportProgress=!!o.reportProgress,this.withCredentials=!!o.withCredentials,this.keepalive=!!o.keepalive,o.responseType&&(this.responseType=o.responseType),o.headers&&(this.headers=o.headers),o.context&&(this.context=o.context),o.params&&(this.params=o.params),o.priority&&(this.priority=o.priority),o.cache&&(this.cache=o.cache),o.credentials&&(this.credentials=o.credentials),typeof o.timeout=="number"){if(o.timeout<1||!Number.isInteger(o.timeout))throw new Fe(2822,"");this.timeout=o.timeout}o.mode&&(this.mode=o.mode),o.redirect&&(this.redirect=o.redirect),o.integrity&&(this.integrity=o.integrity),o.referrer&&(this.referrer=o.referrer),o.referrerPolicy&&(this.referrerPolicy=o.referrerPolicy),this.transferCache=o.transferCache}if(this.headers??=new gn,this.context??=new ds,!this.params)this.params=new Bt,this.urlWithParams=e;else{let a=this.params.toString();if(a.length===0)this.urlWithParams=e;else{let s=e.indexOf("?"),l=s===-1?"?":s<e.length-1?"&":"";this.urlWithParams=e+l+a}}}serializeBody(){return this.body===null?null:typeof this.body=="string"||uh(this.body)||ph(this.body)||hh(this.body)||My(this.body)?this.body:this.body instanceof Bt?this.body.toString():typeof this.body=="object"||typeof this.body=="boolean"||Array.isArray(this.body)?JSON.stringify(this.body):this.body.toString()}detectContentTypeHeader(){return this.body===null||hh(this.body)?null:ph(this.body)?this.body.type||null:uh(this.body)?null:typeof this.body=="string"?bh:this.body instanceof Bt?"application/x-www-form-urlencoded;charset=UTF-8":typeof this.body=="object"||typeof this.body=="number"||typeof this.body=="boolean"?vh:null}clone(n={}){let e=n.method||this.method,i=n.url||this.url,r=n.responseType||this.responseType,o=n.keepalive??this.keepalive,a=n.priority||this.priority,s=n.cache||this.cache,l=n.mode||this.mode,c=n.redirect||this.redirect,d=n.credentials||this.credentials,u=n.referrer||this.referrer,p=n.integrity||this.integrity,y=n.referrerPolicy||this.referrerPolicy,g=n.transferCache??this.transferCache,_=n.timeout??this.timeout,v=n.body!==void 0?n.body:this.body,x=n.withCredentials??this.withCredentials,S=n.reportProgress??this.reportProgress,I=n.headers||this.headers,F=n.params||this.params,N=n.context??this.context;return n.setHeaders!==void 0&&(I=Object.keys(n.setHeaders).reduce((ge,Ce)=>ge.set(Ce,n.setHeaders[Ce]),I)),n.setParams&&(F=Object.keys(n.setParams).reduce((ge,Ce)=>ge.set(Ce,n.setParams[Ce]),F)),new t(e,i,v,{params:F,headers:I,context:N,reportProgress:S,responseType:r,withCredentials:x,transferCache:g,keepalive:o,cache:s,priority:a,timeout:_,mode:l,redirect:c,credentials:d,referrer:u,integrity:p,referrerPolicy:y})}},yi=(function(t){return t[t.Sent=0]="Sent",t[t.UploadProgress=1]="UploadProgress",t[t.ResponseHeader=2]="ResponseHeader",t[t.DownloadProgress=3]="DownloadProgress",t[t.Response=4]="Response",t[t.User=5]="User",t})(yi||{}),pr=class{headers;status;statusText;url;ok;type;redirected;responseType;constructor(n,e=200,i="OK"){this.headers=n.headers||new gn,this.status=n.status!==void 0?n.status:e,this.statusText=n.statusText||i,this.url=n.url||null,this.redirected=n.redirected,this.responseType=n.responseType,this.ok=this.status>=200&&this.status<300}},us=class t extends pr{constructor(n={}){super(n)}type=yi.ResponseHeader;clone(n={}){return new t({headers:n.headers||this.headers,status:n.status!==void 0?n.status:this.status,statusText:n.statusText||this.statusText,url:n.url||this.url||void 0})}},xi=class t extends pr{body;constructor(n={}){super(n),this.body=n.body!==void 0?n.body:null}type=yi.Response;clone(n={}){return new t({body:n.body!==void 0?n.body:this.body,headers:n.headers||this.headers,status:n.status!==void 0?n.status:this.status,statusText:n.statusText||this.statusText,url:n.url||this.url||void 0,redirected:n.redirected??this.redirected,responseType:n.responseType??this.responseType})}},ur=class extends pr{name="HttpErrorResponse";message;error;ok=!1;constructor(n){super(n,0,"Unknown Error"),this.status>=200&&this.status<300?this.message=`Http failure during parsing for ${n.url||"(unknown url)"}`:this.message=`Http failure response for ${n.url||"(unknown url)"}: ${n.status} ${n.statusText}`,this.error=n.error||null}},Iy=200,Ty=204;var Oy=new A("");var Ry=/^\)\]\}',?\n/;var Ro=(()=>{class t{xhrFactory;tracingService=m(Ka,{optional:!0});constructor(e){this.xhrFactory=e}maybePropagateTrace(e){return this.tracingService?.propagate?this.tracingService.propagate(e):e}handle(e){if(e.method==="JSONP")throw new Fe(-2800,!1);let i=this.xhrFactory;return Pe(null).pipe($t(()=>new St(o=>{let a=i.build();if(a.open(e.method,e.urlWithParams),e.withCredentials&&(a.withCredentials=!0),e.headers.forEach((v,x)=>a.setRequestHeader(v,x.join(","))),e.headers.has(gh)||a.setRequestHeader(gh,Ay),!e.headers.has(fh)){let v=e.detectContentTypeHeader();v!==null&&a.setRequestHeader(fh,v)}if(e.timeout&&(a.timeout=e.timeout),e.responseType){let v=e.responseType.toLowerCase();a.responseType=v!=="json"?v:"text"}let s=e.serializeBody(),l=null,c=()=>{if(l!==null)return l;let v=a.statusText||"OK",x=new gn(a.getAllResponseHeaders()),S=a.responseURL||e.url;return l=new us({headers:x,status:a.status,statusText:v,url:S}),l},d=this.maybePropagateTrace(()=>{let{headers:v,status:x,statusText:S,url:I}=c(),F=null;x!==Ty&&(F=typeof a.response>"u"?a.responseText:a.response),x===0&&(x=F?Iy:0);let N=x>=200&&x<300;if(e.responseType==="json"&&typeof F=="string"){let ge=F;F=F.replace(Ry,"");try{F=F!==""?JSON.parse(F):null}catch(Ce){F=ge,N&&(N=!1,F={error:Ce,text:F})}}N?(o.next(new xi({body:F,headers:v,status:x,statusText:S,url:I||void 0})),o.complete()):o.error(new ur({error:F,headers:v,status:x,statusText:S,url:I||void 0}))}),u=this.maybePropagateTrace(v=>{let{url:x}=c(),S=new ur({error:v,status:a.status||0,statusText:a.statusText||"Unknown Error",url:x||void 0});o.error(S)}),p=u;e.timeout&&(p=this.maybePropagateTrace(v=>{let{url:x}=c(),S=new ur({error:new DOMException("Request timed out","TimeoutError"),status:a.status||0,statusText:a.statusText||"Request timeout",url:x||void 0});o.error(S)}));let y=!1,g=this.maybePropagateTrace(v=>{y||(o.next(c()),y=!0);let x={type:yi.DownloadProgress,loaded:v.loaded};v.lengthComputable&&(x.total=v.total),e.responseType==="text"&&a.responseText&&(x.partialText=a.responseText),o.next(x)}),_=this.maybePropagateTrace(v=>{let x={type:yi.UploadProgress,loaded:v.loaded};v.lengthComputable&&(x.total=v.total),o.next(x)});return a.addEventListener("load",d),a.addEventListener("error",u),a.addEventListener("timeout",p),a.addEventListener("abort",u),e.reportProgress&&(a.addEventListener("progress",g),s!==null&&a.upload&&a.upload.addEventListener("progress",_)),a.send(s),o.next({type:yi.Sent}),()=>{a.removeEventListener("error",u),a.removeEventListener("abort",u),a.removeEventListener("load",d),a.removeEventListener("timeout",p),e.reportProgress&&(a.removeEventListener("progress",g),s!==null&&a.upload&&a.upload.removeEventListener("progress",_)),a.readyState!==a.DONE&&a.abort()}})))}static \u0275fac=function(i){return new(i||t)(j(tn))};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();function yh(t,n){return n(t)}function Py(t,n){return(e,i)=>n.intercept(e,{handle:r=>t(r,i)})}function Fy(t,n,e){return(i,r)=>Ep(e,()=>n(i,o=>t(o,r)))}var xh=new A(""),Bc=new A("",{factory:()=>[]}),wh=new A(""),$c=new A("",{factory:()=>!0});function Ny(){let t=null;return(n,e)=>{t===null&&(t=(m(xh,{optional:!0})??[]).reduceRight(Py,yh));let i=m(Ga);if(m($c)){let o=i.add();return t(n,e).pipe(uo(o))}else return t(n,e)}}var Po=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:function(i){let r=null;return i?r=new(i||t):r=j(Ro),r},providedIn:"root"})}return t})();var ps=(()=>{class t{backend;injector;chain=null;pendingTasks=m(Ga);contributeToStability=m($c);constructor(e,i){this.backend=e,this.injector=i}handle(e){if(this.chain===null){let i=Array.from(new Set([...this.injector.get(Bc),...this.injector.get(wh,[])]));this.chain=i.reduceRight((r,o)=>Fy(r,o,this.injector),yh)}if(this.contributeToStability){let i=this.pendingTasks.add();return this.chain(e,r=>this.backend.handle(r)).pipe(uo(i))}else return this.chain(e,i=>this.backend.handle(i))}static \u0275fac=function(i){return new(i||t)(j(Po),j(In))};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),jc=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:function(i){let r=null;return i?r=new(i||t):r=j(ps),r},providedIn:"root"})}return t})();function Lc(t,n){return{body:n,headers:t.headers,context:t.context,observe:t.observe,params:t.params,reportProgress:t.reportProgress,responseType:t.responseType,withCredentials:t.withCredentials,credentials:t.credentials,transferCache:t.transferCache,timeout:t.timeout,keepalive:t.keepalive,priority:t.priority,cache:t.cache,mode:t.mode,redirect:t.redirect,integrity:t.integrity,referrer:t.referrer,referrerPolicy:t.referrerPolicy}}var zt=(()=>{class t{handler;constructor(e){this.handler=e}request(e,i,r={}){let o;if(e instanceof mr)o=e;else{let l;r.headers instanceof gn?l=r.headers:l=new gn(r.headers);let c;r.params&&(r.params instanceof Bt?c=r.params:c=new Bt({fromObject:r.params})),o=new mr(e,i,r.body!==void 0?r.body:null,{headers:l,context:r.context,params:c,reportProgress:r.reportProgress,responseType:r.responseType||"json",withCredentials:r.withCredentials,transferCache:r.transferCache,keepalive:r.keepalive,priority:r.priority,cache:r.cache,mode:r.mode,redirect:r.redirect,credentials:r.credentials,referrer:r.referrer,referrerPolicy:r.referrerPolicy,integrity:r.integrity,timeout:r.timeout})}let a=Pe(o).pipe(lo(l=>this.handler.handle(l)));if(e instanceof mr||r.observe==="events")return a;let s=a.pipe(Be(l=>l instanceof xi));switch(r.observe||"body"){case"body":switch(o.responseType){case"arraybuffer":return s.pipe(ye(l=>{if(l.body!==null&&!(l.body instanceof ArrayBuffer))throw new Fe(2806,!1);return l.body}));case"blob":return s.pipe(ye(l=>{if(l.body!==null&&!(l.body instanceof Blob))throw new Fe(2807,!1);return l.body}));case"text":return s.pipe(ye(l=>{if(l.body!==null&&typeof l.body!="string")throw new Fe(2808,!1);return l.body}));default:return s.pipe(ye(l=>l.body))}case"response":return s;default:throw new Fe(2809,!1)}}delete(e,i={}){return this.request("DELETE",e,i)}get(e,i={}){return this.request("GET",e,i)}head(e,i={}){return this.request("HEAD",e,i)}jsonp(e,i){return this.request("JSONP",e,{params:new Bt().append(i,"JSONP_CALLBACK"),observe:"body",responseType:"json"})}options(e,i={}){return this.request("OPTIONS",e,i)}patch(e,i,r={}){return this.request("PATCH",e,Lc(r,i))}post(e,i,r={}){return this.request("POST",e,Lc(r,i))}put(e,i,r={}){return this.request("PUT",e,Lc(r,i))}static \u0275fac=function(i){return new(i||t)(j(jc))};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var Ly=new A("",{factory:()=>!0}),By="XSRF-TOKEN",$y=new A("",{factory:()=>By}),jy="X-XSRF-TOKEN",Vy=new A("",{factory:()=>jy}),zy=(()=>{class t{cookieName=m($y);doc=m(W);lastCookieString="";lastToken=null;parseCount=0;getToken(){let e=this.doc.cookie||"";return e!==this.lastCookieString&&(this.parseCount++,this.lastToken=Eo(e,this.cookieName),this.lastCookieString=e),this.lastToken}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),Ch=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:function(i){let r=null;return i?r=new(i||t):r=j(zy),r},providedIn:"root"})}return t})();function Hy(t,n){if(!m(Ly)||t.method==="GET"||t.method==="HEAD")return n(t);try{let r=m(cr).href,{origin:o}=new URL(r),{origin:a}=new URL(t.url,o);if(o!==a)return n(t)}catch{return n(t)}let e=m(Ch).getToken(),i=m(Vy);return e!=null&&!t.headers.has(i)&&(t=t.clone({headers:t.headers.set(i,e)})),n(t)}var Vc=(function(t){return t[t.Interceptors=0]="Interceptors",t[t.LegacyInterceptors=1]="LegacyInterceptors",t[t.CustomXsrfConfiguration=2]="CustomXsrfConfiguration",t[t.NoXsrfProtection=3]="NoXsrfProtection",t[t.JsonpSupport=4]="JsonpSupport",t[t.RequestsMadeViaParent=5]="RequestsMadeViaParent",t[t.Fetch=6]="Fetch",t})(Vc||{});function Uy(t,n){return{\u0275kind:t,\u0275providers:n}}function zc(...t){let n=[zt,ps,{provide:jc,useExisting:ps},{provide:Po,useFactory:()=>m(Oy,{optional:!0})??m(Ro)},{provide:Bc,useValue:Hy,multi:!0}];for(let e of t)n.push(...e.\u0275providers);return po(n)}var _h=new A("");function Hc(){return Uy(Vc.LegacyInterceptors,[{provide:_h,useFactory:Ny},{provide:Bc,useExisting:_h,multi:!0}])}var Pn=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:function(i){let r=null;return i?r=new(i||t):r=j(Gy),r},providedIn:"root"})}return t})(),Gy=(()=>{class t extends Pn{_doc;constructor(e){super(),this._doc=e}sanitize(e,i){if(i==null)return null;switch(e){case tt.NONE:return i;case tt.HTML:return rr(i,"HTML")?ir(i):Np(this._doc,String(i)).toString();case tt.STYLE:return rr(i,"Style")?ir(i):i;case tt.SCRIPT:if(rr(i,"Script"))return ir(i);throw new Fe(5200,!1);case tt.URL:return rr(i,"URL")?ir(i):Fp(String(i));case tt.RESOURCE_URL:if(rr(i,"ResourceURL"))return ir(i);throw new Fe(5201,!1);default:throw new Fe(5202,!1)}}bypassSecurityTrustHtml(e){return Ip(e)}bypassSecurityTrustStyle(e){return Tp(e)}bypassSecurityTrustScript(e){return Op(e)}bypassSecurityTrustUrl(e){return Rp(e)}bypassSecurityTrustResourceUrl(e){return Pp(e)}static \u0275fac=function(i){return new(i||t)(j(W))};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var fs={production:!0};var Wy="@",Ky=(()=>{class t{doc;delegate;zone;animationType;moduleImpl;_rendererFactoryPromise=null;scheduler=null;injector=m(te);loadingSchedulerFn=m(Yy,{optional:!0});_engine;constructor(e,i,r,o,a){this.doc=e,this.delegate=i,this.zone=r,this.animationType=o,this.moduleImpl=a}ngOnDestroy(){this._engine?.flush()}loadImpl(){let e=()=>this.moduleImpl??import("./chunk-KMRFWVWC.js").then(r=>r),i;return this.loadingSchedulerFn?i=this.loadingSchedulerFn(e):i=e(),i.catch(r=>{throw new Fe(5300,!1)}).then(({\u0275createEngine:r,\u0275AnimationRendererFactory:o})=>{this._engine=r(this.animationType,this.doc);let a=new o(this.delegate,this._engine,this.zone);return this.delegate=a,a})}createRenderer(e,i){let r=this.delegate.createRenderer(e,i);if(r.\u0275type===0)return r;typeof r.throwOnSyntheticProps=="boolean"&&(r.throwOnSyntheticProps=!1);let o=new Uc(r);return i?.data?.animation&&!this._rendererFactoryPromise&&(this._rendererFactoryPromise=this.loadImpl()),this._rendererFactoryPromise?.then(a=>{let s=a.createRenderer(e,i);o.use(s),this.scheduler??=this.injector.get(Sp,null,{optional:!0}),this.scheduler?.notify(10)}).catch(a=>{o.use(r)}),o}begin(){this.delegate.begin?.()}end(){this.delegate.end?.()}whenRenderingDone(){return this.delegate.whenRenderingDone?.()??Promise.resolve()}componentReplaced(e){this._engine?.flush(),this.delegate.componentReplaced?.(e)}static \u0275fac=function(i){Ya()};static \u0275prov=E({token:t,factory:t.\u0275fac})}return t})(),Uc=class{delegate;replay=[];\u0275type=1;constructor(n){this.delegate=n}use(n){if(this.delegate=n,this.replay!==null){for(let e of this.replay)e(n);this.replay=null}}get data(){return this.delegate.data}destroy(){this.replay=null,this.delegate.destroy()}createElement(n,e){return this.delegate.createElement(n,e)}createComment(n){return this.delegate.createComment(n)}createText(n){return this.delegate.createText(n)}get destroyNode(){return this.delegate.destroyNode}appendChild(n,e){this.delegate.appendChild(n,e)}insertBefore(n,e,i,r){this.delegate.insertBefore(n,e,i,r)}removeChild(n,e,i,r){this.delegate.removeChild(n,e,i,r)}selectRootElement(n,e){return this.delegate.selectRootElement(n,e)}parentNode(n){return this.delegate.parentNode(n)}nextSibling(n){return this.delegate.nextSibling(n)}setAttribute(n,e,i,r){this.delegate.setAttribute(n,e,i,r)}removeAttribute(n,e,i){this.delegate.removeAttribute(n,e,i)}addClass(n,e){this.delegate.addClass(n,e)}removeClass(n,e){this.delegate.removeClass(n,e)}setStyle(n,e,i,r){this.delegate.setStyle(n,e,i,r)}removeStyle(n,e,i){this.delegate.removeStyle(n,e,i)}setProperty(n,e,i){this.shouldReplay(e)&&this.replay.push(r=>r.setProperty(n,e,i)),this.delegate.setProperty(n,e,i)}setValue(n,e){this.delegate.setValue(n,e)}listen(n,e,i,r){return this.shouldReplay(e)&&this.replay.push(o=>o.listen(n,e,i,r)),this.delegate.listen(n,e,i,r)}shouldReplay(n){return this.replay!==null&&n.startsWith(Wy)}},Yy=new A("");function Dh(t="animations"){return Bp("NgAsyncAnimations"),po([{provide:bt,useFactory:()=>new Ky(m(W),m(To),m(K),t)},{provide:tr,useValue:t==="noop"?"NoopAnimations":"BrowserAnimations"}])}function Qy(t,n){return new St(e=>{let i=!1,r=!1,o=t.subscribe(a=>{r=!0,setTimeout(()=>{e.next(a),i&&e.complete()},n)},a=>setTimeout(()=>e.error(a),n),()=>{i=!0,r||e.complete()});return()=>o.unsubscribe()})}var ce={CONTINUE:100,SWITCHING_PROTOCOLS:101,OK:200,CREATED:201,ACCEPTED:202,NON_AUTHORITATIVE_INFORMATION:203,NO_CONTENT:204,RESET_CONTENT:205,PARTIAL_CONTENT:206,MULTIPLE_CHOICES:300,MOVED_PERMANTENTLY:301,FOUND:302,SEE_OTHER:303,NOT_MODIFIED:304,USE_PROXY:305,TEMPORARY_REDIRECT:307,BAD_REQUEST:400,UNAUTHORIZED:401,PAYMENT_REQUIRED:402,FORBIDDEN:403,NOT_FOUND:404,METHOD_NOT_ALLOWED:405,NOT_ACCEPTABLE:406,PROXY_AUTHENTICATION_REQUIRED:407,REQUEST_TIMEOUT:408,CONFLICT:409,GONE:410,LENGTH_REQUIRED:411,PRECONDITION_FAILED:412,PAYLOAD_TO_LARGE:413,URI_TOO_LONG:414,UNSUPPORTED_MEDIA_TYPE:415,RANGE_NOT_SATISFIABLE:416,EXPECTATION_FAILED:417,IM_A_TEAPOT:418,UPGRADE_REQUIRED:426,INTERNAL_SERVER_ERROR:500,NOT_IMPLEMENTED:501,BAD_GATEWAY:502,SERVICE_UNAVAILABLE:503,GATEWAY_TIMEOUT:504,HTTP_VERSION_NOT_SUPPORTED:505,PROCESSING:102,MULTI_STATUS:207,IM_USED:226,PERMANENT_REDIRECT:308,UNPROCESSABLE_ENTRY:422,LOCKED:423,FAILED_DEPENDENCY:424,PRECONDITION_REQUIRED:428,TOO_MANY_REQUESTS:429,REQUEST_HEADER_FIELDS_TOO_LARGE:431,UNAVAILABLE_FOR_LEGAL_REASONS:451,VARIANT_ALSO_NEGOTIATES:506,INSUFFICIENT_STORAGE:507,NETWORK_AUTHENTICATION_REQUIRED:511},Xy={100:{code:100,text:"Continue",description:'"The initial part of a request has been received and has not yet been rejected by the server."',spec_title:"RFC7231#6.2.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.2.1"},101:{code:101,text:"Switching Protocols",description:`"The server understands and is willing to comply with the client's request, via the Upgrade header field, for a change in the application protocol being used on this connection."`,spec_title:"RFC7231#6.2.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.2.2"},200:{code:200,text:"OK",description:'"The request has succeeded."',spec_title:"RFC7231#6.3.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.1"},201:{code:201,text:"Created",description:'"The request has been fulfilled and has resulted in one or more new resources being created."',spec_title:"RFC7231#6.3.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.2"},202:{code:202,text:"Accepted",description:'"The request has been accepted for processing, but the processing has not been completed."',spec_title:"RFC7231#6.3.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.3"},203:{code:203,text:"Non-Authoritative Information",description:`"The request was successful but the enclosed payload has been modified from that of the origin server's 200 (OK) response by a transforming proxy."`,spec_title:"RFC7231#6.3.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.4"},204:{code:204,text:"No Content",description:'"The server has successfully fulfilled the request and that there is no additional content to send in the response payload body."',spec_title:"RFC7231#6.3.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.5"},205:{code:205,text:"Reset Content",description:'"The server has fulfilled the request and desires that the user agent reset the "document view", which caused the request to be sent, to its original state as received from the origin server."',spec_title:"RFC7231#6.3.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.6"},206:{code:206,text:"Partial Content",description:`"The server is successfully fulfilling a range request for the target resource by transferring one or more parts of the selected representation that correspond to the satisfiable ranges found in the requests's Range header field."`,spec_title:"RFC7233#4.1",spec_href:"https://tools.ietf.org/html/rfc7233#section-4.1"},300:{code:300,text:"Multiple Choices",description:'"The target resource has more than one representation, each with its own more specific identifier, and information about the alternatives is being provided so that the user (or user agent) can select a preferred representation by redirecting its request to one or more of those identifiers."',spec_title:"RFC7231#6.4.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.1"},301:{code:301,text:"Moved Permanently",description:'"The target resource has been assigned a new permanent URI and any future references to this resource ought to use one of the enclosed URIs."',spec_title:"RFC7231#6.4.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.2"},302:{code:302,text:"Found",description:'"The target resource resides temporarily under a different URI."',spec_title:"RFC7231#6.4.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.3"},303:{code:303,text:"See Other",description:'"The server is redirecting the user agent to a different resource, as indicated by a URI in the Location header field, that is intended to provide an indirect response to the original request."',spec_title:"RFC7231#6.4.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.4"},304:{code:304,text:"Not Modified",description:'"A conditional GET request has been received and would have resulted in a 200 (OK) response if it were not for the fact that the condition has evaluated to false."',spec_title:"RFC7232#4.1",spec_href:"https://tools.ietf.org/html/rfc7232#section-4.1"},305:{code:305,text:"Use Proxy",description:"*deprecated*",spec_title:"RFC7231#6.4.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.5"},307:{code:307,text:"Temporary Redirect",description:'"The target resource resides temporarily under a different URI and the user agent MUST NOT change the request method if it performs an automatic redirection to that URI."',spec_title:"RFC7231#6.4.7",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.4.7"},400:{code:400,text:"Bad Request",description:'"The server cannot or will not process the request because the received syntax is invalid, nonsensical, or exceeds some limitation on what the server is willing to process."',spec_title:"RFC7231#6.5.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.1"},401:{code:401,text:"Unauthorized",description:'"The request has not been applied because it lacks valid authentication credentials for the target resource."',spec_title:"RFC7235#6.3.1",spec_href:"https://tools.ietf.org/html/rfc7235#section-3.1"},402:{code:402,text:"Payment Required",description:"*reserved*",spec_title:"RFC7231#6.5.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.2"},403:{code:403,text:"Forbidden",description:'"The server understood the request but refuses to authorize it."',spec_title:"RFC7231#6.5.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.3"},404:{code:404,text:"Not Found",description:'"The origin server did not find a current representation for the target resource or is not willing to disclose that one exists."',spec_title:"RFC7231#6.5.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.4"},405:{code:405,text:"Method Not Allowed",description:'"The method specified in the request-line is known by the origin server but not supported by the target resource."',spec_title:"RFC7231#6.5.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.5"},406:{code:406,text:"Not Acceptable",description:'"The target resource does not have a current representation that would be acceptable to the user agent, according to the proactive negotiation header fields received in the request, and the server is unwilling to supply a default representation."',spec_title:"RFC7231#6.5.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.6"},407:{code:407,text:"Proxy Authentication Required",description:'"The client needs to authenticate itself in order to use a proxy."',spec_title:"RFC7231#6.3.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.3.2"},408:{code:408,text:"Request Timeout",description:'"The server did not receive a complete request message within the time that it was prepared to wait."',spec_title:"RFC7231#6.5.7",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.7"},409:{code:409,text:"Conflict",description:'"The request could not be completed due to a conflict with the current state of the resource."',spec_title:"RFC7231#6.5.8",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.8"},410:{code:410,text:"Gone",description:'"Access to the target resource is no longer available at the origin server and that this condition is likely to be permanent."',spec_title:"RFC7231#6.5.9",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.9"},411:{code:411,text:"Length Required",description:'"The server refuses to accept the request without a defined Content-Length."',spec_title:"RFC7231#6.5.10",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.10"},412:{code:412,text:"Precondition Failed",description:'"One or more preconditions given in the request header fields evaluated to false when tested on the server."',spec_title:"RFC7232#4.2",spec_href:"https://tools.ietf.org/html/rfc7232#section-4.2"},413:{code:413,text:"Payload Too Large",description:'"The server is refusing to process a request because the request payload is larger than the server is willing or able to process."',spec_title:"RFC7231#6.5.11",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.11"},414:{code:414,text:"URI Too Long",description:'"The server is refusing to service the request because the request-target is longer than the server is willing to interpret."',spec_title:"RFC7231#6.5.12",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.12"},415:{code:415,text:"Unsupported Media Type",description:'"The origin server is refusing to service the request because the payload is in a format not supported by the target resource for this method."',spec_title:"RFC7231#6.5.13",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.13"},416:{code:416,text:"Range Not Satisfiable",description:`"None of the ranges in the request's Range header field overlap the current extent of the selected resource or that the set of ranges requested has been rejected due to invalid ranges or an excessive request of small or overlapping ranges."`,spec_title:"RFC7233#4.4",spec_href:"https://tools.ietf.org/html/rfc7233#section-4.4"},417:{code:417,text:"Expectation Failed",description:`"The expectation given in the request's Expect header field could not be met by at least one of the inbound servers."`,spec_title:"RFC7231#6.5.14",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.14"},418:{code:418,text:"I'm a teapot",description:'"1988 April Fools Joke. Returned by tea pots requested to brew coffee."',spec_title:"RFC 2324",spec_href:"https://tools.ietf.org/html/rfc2324"},426:{code:426,text:"Upgrade Required",description:'"The server refuses to perform the request using the current protocol but might be willing to do so after the client upgrades to a different protocol."',spec_title:"RFC7231#6.5.15",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.5.15"},500:{code:500,text:"Internal Server Error",description:'"The server encountered an unexpected condition that prevented it from fulfilling the request."',spec_title:"RFC7231#6.6.1",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.1"},501:{code:501,text:"Not Implemented",description:'"The server does not support the functionality required to fulfill the request."',spec_title:"RFC7231#6.6.2",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.2"},502:{code:502,text:"Bad Gateway",description:'"The server, while acting as a gateway or proxy, received an invalid response from an inbound server it accessed while attempting to fulfill the request."',spec_title:"RFC7231#6.6.3",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.3"},503:{code:503,text:"Service Unavailable",description:'"The server is currently unable to handle the request due to a temporary overload or scheduled maintenance, which will likely be alleviated after some delay."',spec_title:"RFC7231#6.6.4",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.4"},504:{code:504,text:"Gateway Time-out",description:'"The server, while acting as a gateway or proxy, did not receive a timely response from an upstream server it needed to access in order to complete the request."',spec_title:"RFC7231#6.6.5",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.5"},505:{code:505,text:"HTTP Version Not Supported",description:'"The server does not support, or refuses to support, the protocol version that was used in the request message."',spec_title:"RFC7231#6.6.6",spec_href:"https://tools.ietf.org/html/rfc7231#section-6.6.6"},102:{code:102,text:"Processing",description:'"An interim response to inform the client that the server has accepted the complete request, but has not yet completed it."',spec_title:"RFC5218#10.1",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.1"},207:{code:207,text:"Multi-Status",description:'"Status for multiple independent operations."',spec_title:"RFC5218#10.2",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.2"},226:{code:226,text:"IM Used",description:'"The server has fulfilled a GET request for the resource, and the response is a representation of the result of one or more instance-manipulations applied to the current instance."',spec_title:"RFC3229#10.4.1",spec_href:"https://tools.ietf.org/html/rfc3229#section-10.4.1"},308:{code:308,text:"Permanent Redirect",description:'"The target resource has been assigned a new permanent URI and any future references to this resource SHOULD use one of the returned URIs. [...] This status code is similar to 301 Moved Permanently (Section 7.3.2 of rfc7231), except that it does not allow rewriting the request method from POST to GET."',spec_title:"RFC7238",spec_href:"https://tools.ietf.org/html/rfc7238"},422:{code:422,text:"Unprocessable Entity",description:'"The server understands the content type of the request entity (hence a 415(Unsupported Media Type) status code is inappropriate), and the syntax of the request entity is correct (thus a 400 (Bad Request) status code is inappropriate) but was unable to process the contained instructions."',spec_title:"RFC5218#10.3",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.3"},423:{code:423,text:"Locked",description:'"The source or destination resource of a method is locked."',spec_title:"RFC5218#10.4",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.4"},424:{code:424,text:"Failed Dependency",description:'"The method could not be performed on the resource because the requested action depended on another action and that action failed."',spec_title:"RFC5218#10.5",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.5"},428:{code:428,text:"Precondition Required",description:'"The origin server requires the request to be conditional."',spec_title:"RFC6585#3",spec_href:"https://tools.ietf.org/html/rfc6585#section-3"},429:{code:429,text:"Too Many Requests",description:'"The user has sent too many requests in a given amount of time ("rate limiting")."',spec_title:"RFC6585#4",spec_href:"https://tools.ietf.org/html/rfc6585#section-4"},431:{code:431,text:"Request Header Fields Too Large",description:'"The server is unwilling to process the request because its header fields are too large."',spec_title:"RFC6585#5",spec_href:"https://tools.ietf.org/html/rfc6585#section-5"},451:{code:451,text:"Unavailable For Legal Reasons",description:'"The server is denying access to the resource in response to a legal demand."',spec_title:"draft-ietf-httpbis-legally-restricted-status",spec_href:"https://tools.ietf.org/html/draft-ietf-httpbis-legally-restricted-status"},506:{code:506,text:"Variant Also Negotiates",description:'"The server has an internal configuration error: the chosen variant resource is configured to engage in transparent content negotiation itself, and is therefore not a proper end point in the negotiation process."',spec_title:"RFC2295#8.1",spec_href:"https://tools.ietf.org/html/rfc2295#section-8.1"},507:{code:507,text:"Insufficient Storage",description:'The method could not be performed on the resource because the server is unable to store the representation needed to successfully complete the request."',spec_title:"RFC5218#10.6",spec_href:"https://tools.ietf.org/html/rfc2518#section-10.6"},511:{code:511,text:"Network Authentication Required",description:'"The client needs to authenticate to gain network access."',spec_title:"RFC6585#6",spec_href:"https://tools.ietf.org/html/rfc6585#section-6"}};function Zy(t){return Xy[t+""].text||"Unknown Status"}function Jy(t){return t>=200&&t<300}var Fo=class{},qc=class{apiBase;caseSensitiveSearch;dataEncapsulation;delay;delete404;host;passThruUnknownUrl;post204;post409;put204;put404;rootPath},gs=(()=>{class t{constructor(e={}){Object.assign(this,{caseSensitiveSearch:!1,dataEncapsulation:!1,delay:500,delete404:!1,passThruUnknownUrl:!1,post204:!0,post409:!1,put204:!0,put404:!1,apiBase:void 0,host:void 0,rootPath:void 0},e)}static \u0275fac=function(i){return new(i||t)(j(qc))};static \u0275prov=E({token:t,factory:t.\u0275fac})}return t})();function e0(t){let e=/^(?:(?![^:@]+:[^:@\/]*@)([^:\/?#.]+):)?(?:\/\/)?((?:(([^:@]*)(?::([^:@]*))?)?@)?([^:\/?#]*)(?::(\d*))?)(((\/(?:[^?#](?![^?#\/]*\.[^?#\/.]+(?:[?#]|$)))*\/?)?([^?#\/]*))(?:\?([^#]*))?(?:#(.*))?)/.exec(t),i={source:"",protocol:"",authority:"",userInfo:"",user:"",password:"",host:"",port:"",relative:"",path:"",directory:"",file:"",query:"",anchor:""},r=Object.keys(i),o=r.length;for(;o--;)i[r[o]]=e&&e[o]||"";return i}function t0(t){return t.replace(/\/$/,"")}var Gc=class{inMemDbService;config=new gs;db={};dbReadySubject;passThruBackend;requestInfoUtils=this.getRequestInfoUtils();constructor(n,e={}){this.inMemDbService=n;let i=this.getLocation("/");this.config.host=i.host,this.config.rootPath=i.path,Object.assign(this.config,e)}get dbReady(){return this.dbReadySubject||(this.dbReadySubject=new Kn(!1),this.resetDb()),this.dbReadySubject.asObservable().pipe(Ha(n=>n))}handleRequest(n){return this.dbReady.pipe(lo(()=>this.handleRequest_(n)))}handleRequest_(n){let e=n.urlWithParams?n.urlWithParams:n.url,i=this.bind("parseRequestUrl"),r=i&&i(e,this.requestInfoUtils)||this.parseRequestUrl(e),o=r.collectionName,a=this.db[o],s={req:n,apiBase:r.apiBase,collection:a,collectionName:o,headers:this.createHeaders({"Content-Type":"application/json"}),id:this.parseId(a,o,r.id),method:this.getRequestMethod(n),query:r.query,resourceUrl:r.resourceUrl,url:e,utils:this.requestInfoUtils},l;if(/commands\/?$/i.test(s.apiBase))return this.commands(s);let c=this.bind(s.method);if(c){let d=c(s);if(d)return d}return this.db[o]?this.createResponse$(()=>this.collectionHandler(s)):this.config.passThruUnknownUrl?this.getPassThruBackend().handle(n):(l=this.createErrorResponseOptions(e,ce.NOT_FOUND,`Collection '${o}' not found`),this.createResponse$(()=>l))}addDelay(n){let e=this.config.delay;return e===0?n:Qy(n,e||500)}applyQuery(n,e){let i=[],r=this.config.caseSensitiveSearch?void 0:"i";e.forEach((a,s)=>{a.forEach(l=>i.push({name:s,rx:new RegExp(decodeURI(l),r)}))});let o=i.length;return o?n.filter(a=>{let s=!0,l=o;for(;s&&l;){l-=1;let c=i[l];s=c.rx.test(a[c.name])}return s}):n}bind(n){let e=this.inMemDbService[n];return e?e.bind(this.inMemDbService):void 0}bodify(n){return this.config.dataEncapsulation?{data:n}:n}clone(n){return JSON.parse(JSON.stringify(n))}collectionHandler(n){let e;switch(n.method){case"get":e=this.get(n);break;case"post":e=this.post(n);break;case"put":e=this.put(n);break;case"delete":e=this.delete(n);break;default:e=this.createErrorResponseOptions(n.url,ce.METHOD_NOT_ALLOWED,"Method not allowed");break}let i=this.bind("responseInterceptor");return i?i(e,n):e}commands(n){let e=n.collectionName.toLowerCase(),i=n.method,r={url:n.url};switch(e){case"resetdb":return r.status=ce.NO_CONTENT,this.resetDb(n).pipe(lo(()=>this.createResponse$(()=>r,!1)));case"config":if(i==="get")r.status=ce.OK,r.body=this.clone(this.config);else{let o=this.getJsonBody(n.req);Object.assign(this.config,o),this.passThruBackend=void 0,r.status=ce.NO_CONTENT}break;default:r=this.createErrorResponseOptions(n.url,ce.INTERNAL_SERVER_ERROR,`Unknown command "${e}"`)}return this.createResponse$(()=>r,!1)}createErrorResponseOptions(n,e,i){return{body:{error:`${i}`},url:n,headers:this.createHeaders({"Content-Type":"application/json"}),status:e}}createResponse$(n,e=!0){let i=this.createResponseOptions$(n),r=this.createResponse$fromResponseOptions$(i);return e?this.addDelay(r):r}createResponseOptions$(n){return new St(e=>{let i;try{i=n()}catch(o){let a=o.message||o;i=this.createErrorResponseOptions("",ce.INTERNAL_SERVER_ERROR,`${a}`)}let r=i.status;try{i.statusText=r!=null?Zy(r):void 0}catch{}return r!=null&&Jy(r)?(e.next(i),e.complete()):e.error(i),()=>{}})}delete({collection:n,collectionName:e,headers:i,id:r,url:o}){if(r==null)return this.createErrorResponseOptions(o,ce.NOT_FOUND,`Missing "${e}" id`);let a=this.removeById(n,r);return{headers:i,status:a||!this.config.delete404?ce.NO_CONTENT:ce.NOT_FOUND}}findById(n,e){return n.find(i=>i.id===e)}genId(n,e){let i=this.bind("genId");if(i){let r=i(n,e);if(r!=null)return r}return this.genIdDefault(n,e)}genIdDefault(n,e){if(!this.isCollectionIdNumeric(n,e))throw new Error(`Collection '${e}' id type is non-numeric or unknown. Can only generate numeric ids.`);let i=0;return n.reduce((r,o)=>{i=Math.max(i,typeof o.id=="number"?o.id:i)},void 0),i+1}get({collection:n,collectionName:e,headers:i,id:r,query:o,url:a}){let s=n;return r!=null&&r!==""?s=this.findById(n,r):o&&(s=this.applyQuery(n,o)),s?{body:this.bodify(this.clone(s)),headers:i,status:ce.OK}:this.createErrorResponseOptions(a,ce.NOT_FOUND,`'${e}' with id='${r}' not found`)}getLocation(n){if(!n.startsWith("http")){let e=typeof document>"u"?void 0:document,i=e?e.location.protocol+"//"+e.location.host:"http://fake";n=n.startsWith("/")?i+n:i+"/"+n}return e0(n)}getPassThruBackend(){return this.passThruBackend?this.passThruBackend:this.passThruBackend=this.createPassThruBackend()}getRequestInfoUtils(){return{createResponse$:this.createResponse$.bind(this),findById:this.findById.bind(this),isCollectionIdNumeric:this.isCollectionIdNumeric.bind(this),getConfig:()=>this.config,getDb:()=>this.db,getJsonBody:this.getJsonBody.bind(this),getLocation:this.getLocation.bind(this),getPassThruBackend:this.getPassThruBackend.bind(this),parseRequestUrl:this.parseRequestUrl.bind(this)}}indexOf(n,e){return n.findIndex(i=>i.id===e)}parseId(n,e,i){if(!this.isCollectionIdNumeric(n,e))return i;let r=parseFloat(i);return isNaN(r)?i:r}isCollectionIdNumeric(n,e){return!!(n&&n[0])&&typeof n[0].id=="number"}parseRequestUrl(n){try{let e=this.getLocation(n),i=(this.config.rootPath||"").length,r="";e.host!==this.config.host&&(i=1,r=e.protocol+"//"+e.host+"/");let a=e.path.substring(i).split("/"),s=0,l;this.config.apiBase==null?l=a[s++]:(l=t0(this.config.apiBase.trim()),l?s=l.split("/").length:s=0),l+="/";let c=a[s++];c=c&&c.split(".")[0];let d=a[s++],u=this.createQueryMap(e.query),p=r+l+c+"/";return{apiBase:l,collectionName:c,id:d,query:u,resourceUrl:p}}catch(e){let i=`unable to parse url '${n}'; original error: ${e.message}`;throw new Error(i)}}post({collection:n,collectionName:e,headers:i,id:r,req:o,resourceUrl:a,url:s}){let l=this.clone(this.getJsonBody(o));if(l.id==null)try{l.id=r||this.genId(n,e)}catch(u){let p=u.message||"";return/id type is non-numeric/.test(p)?this.createErrorResponseOptions(s,ce.UNPROCESSABLE_ENTRY,p):this.createErrorResponseOptions(s,ce.INTERNAL_SERVER_ERROR,`Failed to generate new id for '${e}'`)}if(r&&r!==l.id)return this.createErrorResponseOptions(s,ce.BAD_REQUEST,"Request id does not match item.id");r=l.id;let c=this.indexOf(n,r),d=this.bodify(l);return c===-1?(n.push(l),i.set("Location",a+"/"+r),{headers:i,body:d,status:ce.CREATED}):this.config.post409?this.createErrorResponseOptions(s,ce.CONFLICT,`'${e}' item with id='${r} exists and may not be updated with POST; use PUT instead.`):(n[c]=l,this.config.post204?{headers:i,status:ce.NO_CONTENT}:{headers:i,body:d,status:ce.OK})}put({collection:n,collectionName:e,headers:i,id:r,req:o,url:a}){let s=this.clone(this.getJsonBody(o));if(s.id==null)return this.createErrorResponseOptions(a,ce.NOT_FOUND,`Missing '${e}' id`);if(r&&r!==s.id)return this.createErrorResponseOptions(a,ce.BAD_REQUEST,`Request for '${e}' id does not match item.id`);r=s.id;let l=this.indexOf(n,r),c=this.bodify(s);return l>-1?(n[l]=s,this.config.put204?{headers:i,status:ce.NO_CONTENT}:{headers:i,body:c,status:ce.OK}):this.config.put404?this.createErrorResponseOptions(a,ce.NOT_FOUND,`'${e}' item with id='${r} not found and may not be created with PUT; use POST instead.`):(n.push(s),{headers:i,body:c,status:ce.CREATED})}removeById(n,e){let i=this.indexOf(n,e);return i>-1?(n.splice(i,1),!0):!1}resetDb(n){this.dbReadySubject&&this.dbReadySubject.next(!1);let e=this.inMemDbService.createDb(n);return(e instanceof St?e:typeof e.then=="function"?bp(e):Pe(e)).pipe(Ha()).subscribe(r=>{this.db=r,this.dbReadySubject&&this.dbReadySubject.next(!0)}),this.dbReady}},n0=(()=>{class t extends Gc{xhrFactory;constructor(e,i,r){super(e,i),this.xhrFactory=r}handle(e){try{return this.handleRequest(e)}catch(i){let r=i.message||i,o=this.createErrorResponseOptions(e.url,ce.INTERNAL_SERVER_ERROR,`${r}`);return this.createResponse$(()=>o)}}getJsonBody(e){return e.body}getRequestMethod(e){return(e.method||"get").toLowerCase()}createHeaders(e){return new gn(e)}createQueryMap(e){let i=new Map;if(e){let r=new Bt({fromString:e});r.keys().forEach(o=>i.set(o,r.getAll(o)||[]))}return i}createResponse$fromResponseOptions$(e){return e.pipe(ye(i=>new xi(i)))}createPassThruBackend(){try{return new Ro(this.xhrFactory)}catch(e){throw e.message="Cannot create passThru404 backend; "+(e.message||""),e}}static \u0275fac=function(i){return new(i||t)(j(Fo),j(gs,8),j(tn))};static \u0275prov=E({token:t,factory:t.\u0275fac})}return t})();function i0(){return new n0(m(Fo),m(gs),m(tn))}var Eh=(()=>{class t{static forRoot(e,i){return{ngModule:t,providers:[{provide:Fo,useClass:e},{provide:gs,useValue:i},{provide:Po,useFactory:i0}]}}static forFeature(e,i){return t.forRoot(e,i)}static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({})}return t})();var Sh={asyncapi:"3.1.0",info:{title:"Springwolf example project - AMQP",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-amqp-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com","x-phone":"+49 123 456789"},license:{name:"Apache License 2.0","x-desc":"some description"},"x-api-audience":"company-internal","x-generator":"springwolf"},defaultContentType:"application/json",servers:{"amqp-server":{host:"amqp:5672",protocol:"amqp"}},channels:{"CRUD-topic-exchange-1":{address:"CRUD-topic-exchange-1",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"CRUD-topic-exchange-1",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"CRUD-topic-exchange-2":{address:"CRUD-topic-exchange-2",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"CRUD-topic-exchange-2",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"another-queue",durable:!0,exclusive:!1,autoDelete:!0,vhost:"/"},bindingVersion:"0.3.0"}}},"example-bindings-queue":{address:"example-bindings-queue",bindings:{amqp:{is:"queue",queue:{name:"example-bindings-queue",durable:!0,exclusive:!1,autoDelete:!0,vhost:"/"},bindingVersion:"0.3.0"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"example-queue",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"example-topic-exchange":{address:"example-topic-exchange",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},bindings:{}},"example-topic-exchange_example-topic-routing-key":{address:"example-topic-exchange_example-topic-routing-key",messages:{"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"routingKey",exchange:{name:"example-topic-exchange",type:"topic",durable:!0,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"multi-payload-queue":{address:"multi-payload-queue",messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},bindings:{amqp:{is:"queue",queue:{name:"multi-payload-queue",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-create":{address:"queue-create",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}},bindings:{amqp:{is:"queue",queue:{name:"queue-create",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-delete":{address:"queue-delete",messages:{"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{$ref:"#/components/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}},bindings:{amqp:{is:"queue",queue:{name:"queue-delete",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-read":{address:"queue-read",bindings:{amqp:{is:"queue",queue:{name:"queue-read",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}},"queue-update":{address:"queue-update",bindings:{amqp:{is:"queue",queue:{name:"queue-update",durable:!0,exclusive:!1,autoDelete:!1,vhost:"/"},bindingVersion:"0.3.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},SpringRabbitListenerDefaultHeaders:{title:"SpringRabbitListenerDefaultHeaders",type:"object",properties:{},examples:[{}]},"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},description:"Generic payload model",examples:[{genericValue:{someEnum:"FOO2",someLong:5,someString:"some string value"}}],required:["genericValue"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{type:"integer",description:"Generic Payload field",format:"int64"}},description:"Generic payload model",examples:[{genericValue:0}],required:["genericValue"]},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{title:"GenericPayloadDto",type:"object",properties:{genericValue:{type:"string",description:"Generic Payload field"}},description:"Generic payload model",examples:[{genericValue:"string"}],required:["genericValue"]}},messages:{"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto",title:"GenericPayloadDtoExamplePayloadDto",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long",title:"GenericPayloadDtoLong",bindings:{amqp:{bindingVersion:"0.3.0"}}},"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String":{headers:{$ref:"#/components/schemas/SpringRabbitListenerDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}},name:"io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String",title:"GenericPayloadDtoString",bindings:{amqp:{bindingVersion:"0.3.0"}}}}},operations:{"CRUD-topic-exchange-1_receive_bindingsUpdate":{action:"receive",channel:{$ref:"#/channels/CRUD-topic-exchange-1"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/CRUD-topic-exchange-1/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoIo.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"CRUD-topic-exchange-2_receive_bindingsRead":{action:"receive",channel:{$ref:"#/channels/CRUD-topic-exchange-2"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/CRUD-topic-exchange-2/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"example-topic-exchange_example-topic-routing-key_receive_bindingsExample":{action:"receive",channel:{$ref:"#/channels/example-topic-exchange_example-topic-routing-key"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-topic-exchange_example-topic-routing-key/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"example-topic-exchange_send_sendMessage":{action:"send",channel:{$ref:"#/channels/example-topic-exchange"},title:"example-topic-exchange_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{amqp:{expiration:0,cc:[],priority:0,deliveryMode:1,mandatory:!1,bcc:[],timestamp:!1,ack:!1,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/example-topic-exchange/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"}]},"multi-payload-queue_receive_bindingsBeanExample":{action:"receive",channel:{$ref:"#/channels/multi-payload-queue"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/multi-payload-queue/messages/io.github.springwolf.examples.amqp.dtos.AnotherPayloadDto"},{$ref:"#/channels/multi-payload-queue/messages/io.github.springwolf.examples.amqp.dtos.ExamplePayloadDto"}]},"queue-create_receive_queuesToDeclareCreate":{action:"receive",channel:{$ref:"#/channels/queue-create"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/queue-create/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.String"}]},"queue-delete_receive_queuesToDeclareDelete":{action:"receive",channel:{$ref:"#/channels/queue-delete"},bindings:{amqp:{expiration:0,bindingVersion:"0.3.0"}},messages:[{$ref:"#/channels/queue-delete/messages/io.github.springwolf.examples.amqp.dtos.GenericPayloadDtoJava.lang.Long"}]}}};var Mh={asyncapi:"3.1.0",info:{title:"Springwolf example project - Cloud Stream",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-cloud-stream-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"another-topic":{address:"another-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"biconsumer-topic":{address:"biconsumer-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"bifunction-output-topic":{address:"bifunction-output-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"bifunction-topic":{address:"bifunction-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"consumer-class-topic":{address:"consumer-class-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"consumer-topic":{address:"consumer-topic",messages:{AnotherPayloadDto:{$ref:"#/components/messages/AnotherPayloadDto"}},bindings:{kafka:{}}},"example-topic":{address:"example-topic",messages:{ExamplePayloadDto:{$ref:"#/components/messages/ExamplePayloadDto"}},bindings:{kafka:{}}},"google-pubsub-topic":{address:"google-pubsub-topic",messages:{GooglePubSubPayloadDto:{$ref:"#/components/messages/GooglePubSubPayloadDto"}},bindings:{googlepubsub:{messageStoragePolicy:{},schemaSettings:{encoding:"BINARY",name:"project/test"},bindingVersion:"0.2.0"}}}},components:{schemas:{AnotherPayloadDto:{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},ExamplePayloadDto:{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]},GooglePubSubPayloadDto:{title:"GooglePubSubPayloadDto",type:"object",properties:{someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Google pubsub payload model",examples:[{someLong:5,someString:"some string value"}],required:["someString"]},HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]}},messages:{AnotherPayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/AnotherPayloadDto"}},name:"AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{kafka:{}}},ExamplePayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/ExamplePayloadDto"}},name:"ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{kafka:{}}},GooglePubSubPayloadDto:{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/GooglePubSubPayloadDto"}},name:"GooglePubSubPayloadDto",title:"GooglePubSubPayloadDto",bindings:{googlepubsub:{schema:{name:"project/test"},bindingVersion:"0.2.0"}}}}},operations:{"another-topic_send_process":{action:"send",channel:{$ref:"#/channels/another-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/another-topic/messages/AnotherPayloadDto"}]},"biconsumer-topic_receive_biConsumerMethod":{action:"receive",channel:{$ref:"#/channels/biconsumer-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/biconsumer-topic/messages/AnotherPayloadDto"}]},"bifunction-output-topic_send_biProcess":{action:"send",channel:{$ref:"#/channels/bifunction-output-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/bifunction-output-topic/messages/AnotherPayloadDto"}]},"bifunction-topic_receive_biProcess":{action:"receive",channel:{$ref:"#/channels/bifunction-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/bifunction-topic/messages/ExamplePayloadDto"}]},"consumer-class-topic_receive_ConsumerClass":{action:"receive",channel:{$ref:"#/channels/consumer-class-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/consumer-class-topic/messages/ExamplePayloadDto"}]},"consumer-topic_receive_consumerMethod":{action:"receive",channel:{$ref:"#/channels/consumer-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/consumer-topic/messages/AnotherPayloadDto"}]},"example-topic_receive_process":{action:"receive",channel:{$ref:"#/channels/example-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/example-topic/messages/ExamplePayloadDto"}]},"google-pubsub-topic_receive_googlePubSubConsumerMethod":{action:"receive",channel:{$ref:"#/channels/google-pubsub-topic"},description:"Auto-generated description",bindings:{kafka:{}},messages:[{$ref:"#/channels/google-pubsub-topic/messages/GooglePubSubPayloadDto"}]}}};var Ah={asyncapi:"3.1.0",info:{title:"Springwolf example project - Kafka",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-kafka-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"another-topic":{address:"another-topic",messages:{"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"avro-topic":{address:"avro-topic",messages:{"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"example-topic":{address:"example-topic",messages:{"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"integer-topic":{address:"integer-topic",messages:{"java.lang.Integer":{$ref:"#/components/messages/java.lang.Integer"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"multi-payload-topic":{address:"multi-payload-topic",messages:{"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"},"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},"javax.money.MonetaryAmount":{$ref:"#/components/messages/javax.money.MonetaryAmount"}},bindings:{kafka:{topic:"multi-payload-topic",partitions:3,replicas:1,topicConfiguration:{"cleanup.policy":["compact","delete"],"retention.ms":864e5,"retention.bytes":-1,"delete.retention.ms":864e5,"max.message.bytes":1048588},bindingVersion:"0.5.0"}}},"no-payload-used-topic":{address:"no-payload-used-topic",messages:{PayloadNotUsed:{$ref:"#/components/messages/PayloadNotUsed"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"nullable-topic":{address:"nullable-topic",messages:{"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"protobuf-topic":{address:"protobuf-topic",messages:{"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"string-topic":{address:"string-topic",messages:{"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"},"java.lang.String":{$ref:"#/components/messages/java.lang.String"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"topic-defined-via-asyncPublisher-annotation":{address:"topic-defined-via-asyncPublisher-annotation",messages:{"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}},servers:[{$ref:"#/servers/kafka-server"}],bindings:{}},"vehicle-topic":{address:"vehicle-topic",messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"xml-topic":{address:"xml-topic",messages:{"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}},"yaml-topic":{address:"yaml-topic",messages:{"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}},bindings:{kafka:{bindingVersion:"0.5.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"There can be headers, but they are not explicitly documented.",title:"HeadersNotDocumented",type:"object"}},HeadersNotUsed:{title:"HeadersNotUsed",type:"object",properties:{},description:"No headers are present.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"No headers are present.",title:"HeadersNotUsed",type:"object"}},PayloadNotUsed:{title:"PayloadNotUsed",type:"object",properties:{},description:"No payload specified",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"No payload specified",title:"PayloadNotUsed",type:"object"}},SpringDefaultHeaderAndCloudEvent:{title:"SpringDefaultHeaderAndCloudEvent",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"]},ce_id:{title:"ce_id",type:"string",description:"CloudEvent Id Header",enum:["2c60089e-6f39-459d-8ced-2d6df7e4c03a"],examples:["2c60089e-6f39-459d-8ced-2d6df7e4c03a"]},ce_source:{title:"ce_source",type:"string",description:"CloudEvent Source Header",enum:["http://localhost"],examples:["http://localhost"]},ce_specversion:{title:"ce_specversion",type:"string",description:"CloudEvent Spec Version Header",enum:["1.0"],examples:["1.0"]},ce_subject:{title:"ce_subject",type:"string",description:"CloudEvent Subject Header",enum:["Springwolf example project - Kafka"],examples:["Springwolf example project - Kafka"]},ce_time:{title:"ce_time",type:"string",description:"CloudEvent Time Header",format:"date-time",enum:["2023-10-28T20:01:23+00:00"],examples:["2023-10-28T20:01:23+00:00"]},ce_type:{title:"ce_type",type:"string",description:"CloudEvent Payload Type Header",enum:["NestedPayloadDto.v1"],examples:["NestedPayloadDto.v1"]},"content-type":{title:"content-type",type:"string",description:"CloudEvent Content-Type Header",enum:["application/json"],examples:["application/json"]}},description:"Spring __TypeId__ and CloudEvent Headers",examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto",ce_id:"2c60089e-6f39-459d-8ced-2d6df7e4c03a",ce_source:"http://localhost",ce_specversion:"1.0",ce_subject:"Springwolf example project - Kafka",ce_time:"2023-10-28T20:01:23+00:00",ce_type:"NestedPayloadDto.v1","content-type":"application/json"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Spring __TypeId__ and CloudEvent Headers",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"],title:"__TypeId__",type:"string"},ce_id:{description:"CloudEvent Id Header",enum:["2c60089e-6f39-459d-8ced-2d6df7e4c03a"],title:"ce_id",type:"string"},ce_source:{description:"CloudEvent Source Header",enum:["http://localhost"],title:"ce_source",type:"string"},ce_specversion:{description:"CloudEvent Spec Version Header",enum:["1.0"],title:"ce_specversion",type:"string"},ce_subject:{description:"CloudEvent Subject Header",enum:["Springwolf example project - Kafka"],title:"ce_subject",type:"string"},ce_time:{description:"CloudEvent Time Header",enum:["2023-10-28T20:01:23+00:00"],format:"date-time",title:"ce_time",type:"string"},ce_type:{description:"CloudEvent Payload Type Header",enum:["NestedPayloadDto.v1"],title:"ce_type",type:"string"},"content-type":{description:"CloudEvent Content-Type Header",enum:["application/json"],title:"content-type",type:"string"}},title:"SpringDefaultHeaderAndCloudEvent",type:"object"}},"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto":{title:"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"],examples:["io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherPayloadAvroDto",type:"object"}},"SpringKafkaDefaultHeaders-AnotherPayloadDto":{title:"SpringKafkaDefaultHeaders-AnotherPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-AnotherTopic":{title:"SpringKafkaDefaultHeaders-AnotherTopic",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Type ID"},my_uuid_field:{title:"my_uuid_field",type:"string",description:"Event identifier",format:"uuid",enum:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"],examples:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"]}},examples:[{__TypeId__:"string",my_uuid_field:"00000000-0000-0000-0000-000000000000"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Type ID",title:"__TypeId__",type:"string"},my_uuid_field:{description:"Event identifier",enum:["00000000-0000-0000-0000-000000000000","FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF"],format:"uuid",title:"my_uuid_field",type:"string"}},title:"SpringKafkaDefaultHeaders-AnotherTopic",type:"object"}},"SpringKafkaDefaultHeaders-ExamplePayloadDto-546532105":{title:"SpringKafkaDefaultHeaders-ExamplePayloadDto-546532105",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"]},kafka_offset:{type:"integer",format:"int32",examples:[0]},kafka_receivedMessageKey:{type:"string",examples:['"string"']},kafka_recordMetadata:{title:"ConsumerRecordMetadata",type:"object",examples:[{}]}},examples:[{ConsumerRecordMetadata:{},__TypeId__:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto",kafka_offset:0,kafka_receivedMessageKey:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"],title:"__TypeId__",type:"string"},kafka_offset:{format:"int32",type:"integer"},kafka_receivedMessageKey:{type:"string"},kafka_recordMetadata:{title:"ConsumerRecordMetadata",type:"object"}},title:"SpringKafkaDefaultHeaders-ExamplePayloadDto-546532105",type:"object"}},"SpringKafkaDefaultHeaders-Message":{title:"SpringKafkaDefaultHeaders-Message",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"],examples:["io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-Message",type:"object"}},"SpringKafkaDefaultHeaders-MonetaryAmount":{title:"SpringKafkaDefaultHeaders-MonetaryAmount",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["javax.money.MonetaryAmount"],examples:["javax.money.MonetaryAmount"]}},examples:[{__TypeId__:"javax.money.MonetaryAmount"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["javax.money.MonetaryAmount"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-MonetaryAmount",type:"object"}},"SpringKafkaDefaultHeaders-PayloadNotUsed":{title:"SpringKafkaDefaultHeaders-PayloadNotUsed",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["PayloadNotUsed"],examples:["PayloadNotUsed"]}},examples:[{__TypeId__:"PayloadNotUsed"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["PayloadNotUsed"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-PayloadNotUsed",type:"object"}},"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto":{title:"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-VehicleBase":{title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],examples:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object"}},"SpringKafkaDefaultHeaders-XmlPayloadDto":{title:"SpringKafkaDefaultHeaders-XmlPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-XmlPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-YamlPayloadDto":{title:"SpringKafkaDefaultHeaders-YamlPayloadDto",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"],examples:["io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-YamlPayloadDto",type:"object"}},"SpringKafkaDefaultHeaders-integer":{title:"SpringKafkaDefaultHeaders-integer",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["java.lang.Integer"],examples:["java.lang.Integer"]}},examples:[{__TypeId__:"java.lang.Integer"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["java.lang.Integer"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-integer",type:"object"}},"SpringKafkaDefaultHeaders-string":{title:"SpringKafkaDefaultHeaders-string",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["java.lang.String"],examples:["java.lang.String"]}},examples:[{__TypeId__:"java.lang.String"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["java.lang.String"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-string",type:"object"}},"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{type:"string",description:"Payload description using @Schema annotation and @AsyncApiPayload within envelope class",maxLength:100,examples:['"string"'],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Payload description using @Schema annotation and @AsyncApiPayload within envelope class",maxLength:100,type:"string"}},"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{title:"AnotherPayloadAvroDto",type:"object",properties:{examplePayloadAvroDto:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.avro.ExamplePayloadAvroDto"},someEnum:{title:"ExampleEnum",type:"string",enum:["FOO1","FOO2","FOO3"]}},examples:[{examplePayloadAvroDto:{someLong:0,someString:"string"},someEnum:"FOO1"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{examplePayloadAvroDto:{properties:{someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"ExamplePayloadAvroDto",type:"object"},someEnum:{enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"}},title:"AnotherPayloadAvroDto",type:"object"}},"io.github.springwolf.examples.kafka.dto.avro.ExamplePayloadAvroDto":{title:"ExamplePayloadAvroDto",type:"object",properties:{someLong:{type:"integer",format:"int64"},someString:{type:"string"}},examples:[{someLong:0,someString:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"ExamplePayloadAvroDto",type:"object"}},"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{title:"Message",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",enum:["FOO1","FOO2","FOO3","UNRECOGNIZED"]},someLong:{type:"integer",format:"int64"},someString:{type:"string"}},examples:[{someEnum:"FOO1",someLong:0,someString:"string"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someEnum:{enum:["FOO1","FOO2","FOO3","UNRECOGNIZED"],title:"ExampleEnum",type:"string"},someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"Message",type:"object"}},"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Another payload model",properties:{example:{description:`Example payload model demonstrating markdown text styling:
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
`],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{someEnum:{enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{format:"int64",type:"integer"},someString:{type:"string"}},title:"YamlPayloadDto",type:"string"}},"io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower":{title:"EnginePower",type:"object",properties:{hp:{type:"integer",format:"int32"},torque:{type:"integer",format:"int32"}},examples:[{hp:0,torque:0}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{hp:{format:"int32",type:"integer"},torque:{}},title:"EnginePower",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{discriminator:"vehicleType",title:"VehicleBase",type:"object",properties:{enginePower:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower"},powerSource:{type:"string"},topSpeed:{type:"integer",format:"int32"},vehicleType:{type:"string"}},description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],oneOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto"},{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{allOf:[{},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto":{title:"VehicleElectricPayloadDto",type:"object",description:"Electric vehicle implementation of VehicleBase",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{batteryCapacity:{type:"integer",format:"int32"},chargeTime:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{},{allOf:[{},{properties:{fuelCapacity:{format:"int32",type:"integer"}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{batteryCapacity:{},chargeTime:{}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto":{title:"VehicleGasolinePayloadDto",type:"object",description:"Gasoline vehicle implementation of VehicleBase",examples:[{enginePower:{hp:0,torque:0},fuelCapacity:0,powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{fuelCapacity:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}},"java.lang.Integer":{type:"integer",format:"int32",examples:[0],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",format:"int32",type:"integer"}},"java.lang.String":{type:"string",examples:['"string"'],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",type:"string"}},"javax.money.MonetaryAmount":{type:"object",properties:{amount:{type:"number",exclusiveMinimum:.01,examples:[99.99]},currency:{type:"string",examples:["USD"]}},examples:[{amount:99.99,currency:"USD"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{amount:{exclusiveMinimum:.01,type:"number"},currency:{type:"string"}},type:"object"}}},messages:{PayloadNotUsed:{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-PayloadNotUsed"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{title:"PayloadNotUsed",type:"object",properties:{},description:"No payload specified"}},name:"PayloadNotUsed",title:"PayloadNotUsed",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope":{headers:{$ref:"#/components/schemas/HeadersNotUsed"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"}},name:"StringPayload",title:"StringEnvelope",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}},contentType:"application/avro",name:"io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto",title:"AnotherPayloadAvroDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-Message"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}},name:"io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message",title:"Message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-AnotherTopic"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-ExamplePayloadDto-546532105"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto":{headers:{$ref:"#/components/schemas/SpringDefaultHeaderAndCloudEvent"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.NestedPayloadDto",title:"NestedPayloadDto",bindings:{kafka:{key:{type:"string",description:"Kafka Producer Message Key",examples:["example-key"]},bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-RequiredAndNullablePayloadDto"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}},name:"io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto",title:"RequiredAndNullablePayloadDto",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}},contentType:"text/xml",name:"io.github.springwolf.examples.kafka.dtos.XmlPayloadDto",title:"XmlPayloadDto",description:"Showcases a xml based message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}},contentType:"application/yaml",name:"io.github.springwolf.examples.kafka.dtos.YamlPayloadDto",title:"YamlPayloadDto",description:"Showcases a yaml based message",bindings:{kafka:{bindingVersion:"0.5.0"}}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-VehicleBase"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},name:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",title:"VehicleBase",bindings:{kafka:{bindingVersion:"0.5.0"}}},"java.lang.Integer":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-integer"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{type:"integer",format:"int32",examples:[0]}},name:"java.lang.Integer",title:"integer",bindings:{kafka:{bindingVersion:"0.5.0"}}},"java.lang.String":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-string"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{type:"string",examples:['"string"']}},name:"java.lang.String",title:"string",bindings:{kafka:{bindingVersion:"0.5.0"}}},"javax.money.MonetaryAmount":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-MonetaryAmount"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/javax.money.MonetaryAmount"}},name:"javax.money.MonetaryAmount",title:"MonetaryAmount",bindings:{kafka:{key:{type:"string",description:"Kafka Consumer Message Key",examples:["example-key"]},bindingVersion:"0.5.0"}}}}},operations:{"another-topic_receive_receiveAnotherPayloadBatched":{action:"receive",channel:{$ref:"#/channels/another-topic"},bindings:{kafka:{groupId:{type:"string",enum:["example-group-id"]},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}]},"another-topic_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-topic"},title:"another-topic_send",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"}]},"avro-topic_receive_receiveExampleAvroPayload":{action:"receive",channel:{$ref:"#/channels/avro-topic"},title:"avro-topic_receive",description:"Requires a running kafka-schema-registry. See docker-compose.yml to start it",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/avro-topic/messages/io.github.springwolf.examples.kafka.dto.avro.AnotherPayloadAvroDto"}]},"example-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/example-topic/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"}]},"integer-topic_receive_receiveIntegerPayload":{action:"receive",channel:{$ref:"#/channels/integer-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/integer-topic/messages/java.lang.Integer"}]},"multi-payload-topic_receive_ExampleClassLevelKafkaListener":{action:"receive",channel:{$ref:"#/channels/multi-payload-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/multi-payload-topic/messages/io.github.springwolf.examples.kafka.dtos.ExamplePayloadDto"},{$ref:"#/channels/multi-payload-topic/messages/io.github.springwolf.examples.kafka.dtos.AnotherPayloadDto"},{$ref:"#/channels/multi-payload-topic/messages/javax.money.MonetaryAmount"}]},"multi-payload-topic_receive_receiveMonetaryAmount":{action:"receive",channel:{$ref:"#/channels/multi-payload-topic"},title:"multi-payload-topic_receive",description:"Override description in the AsyncListener annotation with servers at kafka:29092",bindings:{kafka:{groupId:{type:"string",enum:["foo-groupId"]},clientId:{type:"string",enum:["foo-clientId"]},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/multi-payload-topic/messages/javax.money.MonetaryAmount"}]},"no-payload-used-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/no-payload-used-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/no-payload-used-topic/messages/PayloadNotUsed"}]},"nullable-topic_receive_receiveNullablePayload":{action:"receive",channel:{$ref:"#/channels/nullable-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/nullable-topic/messages/io.github.springwolf.examples.kafka.dtos.RequiredAndNullablePayloadDto"}]},"protobuf-topic_receive_receiveExampleProtobufPayload":{action:"receive",channel:{$ref:"#/channels/protobuf-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/protobuf-topic/messages/io.github.springwolf.examples.kafka.dto.proto.ExamplePayloadProtobufDto.Message"}]},"string-topic_receive_receiveStringPayload":{action:"receive",channel:{$ref:"#/channels/string-topic"},title:"string-topic_receive",description:"Final classes (like String) can be documented using an envelope class and the @AsyncApiPayload annotation.",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/string-topic/messages/io.github.springwolf.examples.kafka.consumers.StringConsumer.StringEnvelope"},{$ref:"#/channels/string-topic/messages/java.lang.String"}]},"topic-defined-via-asyncPublisher-annotation_send_sendMessage":{action:"send",channel:{$ref:"#/channels/topic-defined-via-asyncPublisher-annotation"},title:"topic-defined-via-asyncPublisher-annotation_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{kafka:{clientId:{type:"string",enum:["foo-clientId"]},bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/topic-defined-via-asyncPublisher-annotation/messages/io.github.springwolf.examples.kafka.dtos.NestedPayloadDto"}]},"vehicle-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/vehicle-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/vehicle-topic/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}]},"xml-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/xml-topic"},title:"xml-topic_receive",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/xml-topic/messages/io.github.springwolf.examples.kafka.dtos.XmlPayloadDto"}]},"yaml-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/yaml-topic"},title:"yaml-topic_receive",description:"Auto-generated description",bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/yaml-topic/messages/io.github.springwolf.examples.kafka.dtos.YamlPayloadDto"}]}}};var Ih={asyncapi:"3.1.0",info:{title:"Springwolf example project - Kafka",version:"1.0.0",description:"This group only contains endpoints that are related to vehicles.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-apitype":"internal","x-generator":"springwolf"},defaultContentType:"application/json",servers:{"kafka-server":{host:"kafka:29092",protocol:"kafka"}},channels:{"vehicle-topic":{address:"vehicle-topic",messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{$ref:"#/components/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},bindings:{kafka:{bindingVersion:"0.5.0"}}}},components:{schemas:{"SpringKafkaDefaultHeaders-VehicleBase":{title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object",properties:{__TypeId__:{title:"__TypeId__",type:"string",description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],examples:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"]}},examples:[{__TypeId__:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{__TypeId__:{description:"Spring Type Id Header",enum:["io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"],title:"__TypeId__",type:"string"}},title:"SpringKafkaDefaultHeaders-VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower":{title:"EnginePower",type:"object",properties:{hp:{type:"integer",format:"int32"},torque:{type:"integer",format:"int32"}},examples:[{hp:0,torque:0}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",properties:{hp:{format:"int32",type:"integer"},torque:{}},title:"EnginePower",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{discriminator:"vehicleType",title:"VehicleBase",type:"object",properties:{enginePower:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.EnginePower"},powerSource:{type:"string"},topSpeed:{type:"integer",format:"int32"},vehicleType:{type:"string"}},description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],oneOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto"},{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto"}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{allOf:[{},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleElectricPayloadDto":{title:"VehicleElectricPayloadDto",type:"object",description:"Electric vehicle implementation of VehicleBase",examples:[{batteryCapacity:0,chargeTime:0,enginePower:{hp:0,torque:0},powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{batteryCapacity:{type:"integer",format:"int32"},chargeTime:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{},{allOf:[{},{properties:{fuelCapacity:{format:"int32",type:"integer"}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{batteryCapacity:{},chargeTime:{}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"}},"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleGasolinePayloadDto":{title:"VehicleGasolinePayloadDto",type:"object",description:"Gasoline vehicle implementation of VehicleBase",examples:[{enginePower:{hp:0,torque:0},fuelCapacity:0,powerSource:"string",topSpeed:0,vehicleType:"string"}],allOf:[{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"},{type:"object",properties:{fuelCapacity:{type:"integer",format:"int32"}}}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",allOf:[{description:"Demonstrates the use of discriminator for polymorphic deserialization (not publishable)",oneOf:[{allOf:[{},{properties:{batteryCapacity:{},chargeTime:{format:"int32",type:"integer"}},type:"object"}],description:"Electric vehicle implementation of VehicleBase",title:"VehicleElectricPayloadDto",type:"object"},{}],properties:{enginePower:{properties:{hp:{},torque:{}},title:"EnginePower",type:"object"},powerSource:{type:"string"},topSpeed:{},vehicleType:{}},title:"VehicleBase",type:"object"},{properties:{fuelCapacity:{}},type:"object"}],description:"Gasoline vehicle implementation of VehicleBase",title:"VehicleGasolinePayloadDto",type:"object"}}},messages:{"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase":{headers:{$ref:"#/components/schemas/SpringKafkaDefaultHeaders-VehicleBase"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}},name:"io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase",title:"VehicleBase",bindings:{kafka:{bindingVersion:"0.5.0"}}}}},operations:{"vehicle-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/vehicle-topic"},bindings:{kafka:{bindingVersion:"0.5.0"}},messages:[{$ref:"#/channels/vehicle-topic/messages/io.github.springwolf.examples.kafka.dtos.discriminator.VehicleBase"}]}}};var Th={initialConfig:{showBindings:!0,showHeaders:!0},groups:[{name:"Only Vehicles"}]};var Oh={asyncapi:"3.1.0",info:{title:"Springwolf example project - JMS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-jms-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"jms-server":{host:"tcp://activemq:61616",protocol:"jms"}},channels:{"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}},bindings:{jms:{bindingVersion:"0.0.1"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}},bindings:{jms:{bindingVersion:"0.0.1"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.jms.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{jms:{bindingVersion:"0.0.1"}}},"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.jms.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{jms:{bindingVersion:"0.0.1"}}}}},operations:{"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{jms:{}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}]},"another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-queue"},title:"another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{jms:{"internal-field":"customValue",nested:{key:"nestedValue"}}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.jms.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{jms:{}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.jms.dtos.ExamplePayloadDto"}]}}};var Rh={asyncapi:"3.1.0",info:{title:"Springwolf example project - SNS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-sns-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"sns-server":{host:"http://localhost:4566",protocol:"sns"}},channels:{"another-topic":{address:"another-topic",messages:{"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}},bindings:{}},"example-topic":{address:"example-topic",messages:{"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}},bindings:{}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"There can be headers, but they are not explicitly documented.",title:"HeadersNotDocumented",type:"object"}},"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Another payload model",properties:{example:{description:"Example payload model",properties:{someEnum:{description:"Some enum field",enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{description:"Some long field",format:"int64",minimum:0,type:"integer"},someString:{description:"Some string field",type:"string"}},required:["someEnum","someString"],title:"ExamplePayloadDto",type:"object"},foo:{description:"Foo field",maxLength:100,type:"string"}},required:["example"],title:"AnotherPayloadDto",type:"object"}},"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"],"x-json-schema":{$schema:"https://json-schema.org/draft-07/schema#",description:"Example payload model",properties:{someEnum:{description:"Some enum field",enum:["FOO1","FOO2","FOO3"],title:"ExampleEnum",type:"string"},someLong:{description:"Some long field",format:"int64",minimum:0,type:"integer"},someString:{description:"Some string field",type:"string"}},required:["someEnum","someString"],title:"ExamplePayloadDto",type:"object"}}},messages:{"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.sns.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{sns:{}}},"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.sns.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{sns:{}}}}},operations:{"another-topic_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-topic"},title:"another-topic_receive",description:"Auto-generated description",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}]},"another-topic_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-topic"},title:"another-topic_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/another-topic/messages/io.github.springwolf.examples.sns.dtos.AnotherPayloadDto"}]},"example-topic_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-topic"},title:"example-topic_receive",description:"Auto-generated description",bindings:{sns:{consumers:[{protocol:"sqs",endpoint:{},filterPolicyScope:"MessageAttributes",rawMessageDelivery:!0}],bindingVersion:"0.1.0"}},messages:[{$ref:"#/channels/example-topic/messages/io.github.springwolf.examples.sns.dtos.ExamplePayloadDto"}]}}};var Ph={asyncapi:"3.1.0",info:{title:"Springwolf example project - SQS",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-sqs-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{"sqs-server":{host:"http://localhost:4566",protocol:"sqs"}},channels:{"another-queue":{address:"another-queue",messages:{"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}},bindings:{sqs:{queue:{name:"another-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600},bindingVersion:"0.2.0"}}},"example-queue":{address:"example-queue",messages:{"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}},bindings:{sqs:{queue:{name:"example-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600},bindingVersion:"0.2.0"}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{sqs:{}}},"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{sqs:{}}}}},operations:{"another-queue_receive_receiveAnotherPayload":{action:"receive",channel:{$ref:"#/channels/another-queue"},bindings:{sqs:{queues:[{name:"another-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}]},"another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/another-queue"},title:"another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{sqs:{queues:[{name:"queue-name",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/another-queue/messages/io.github.springwolf.examples.sqs.dtos.AnotherPayloadDto"}]},"example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/example-queue"},bindings:{sqs:{queues:[{name:"example-queue",fifoQueue:!0,deliveryDelay:0,visibilityTimeout:30,receiveMessageWaitTime:0,messageRetentionPeriod:345600}],bindingVersion:"0.2.0"}},messages:[{$ref:"#/channels/example-queue/messages/io.github.springwolf.examples.sqs.dtos.ExamplePayloadDto"}]}}};var Fh={asyncapi:"3.1.0",info:{title:"Springwolf example project - STOMP",version:"1.0.0",description:"Springwolf [example project](https://github.com/springwolf/springwolf-core/tree/main/springwolf-examples/springwolf-stomp-example) to demonstrate springwolfs abilities, including **markdown** support for descriptions.",termsOfService:"https://asyncapi.org/terms",contact:{name:"springwolf",url:"https://github.com/springwolf/springwolf-core",email:"example@example.com"},license:{name:"Apache License 2.0"},"x-generator":"springwolf"},defaultContentType:"application/json",servers:{stomp:{host:"localhost:8080/myendpoint",protocol:"stomp"}},channels:{"_app_queue_another-queue":{address:"/app/queue/another-queue",messages:{"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}},bindings:{}},"_app_queue_example-queue":{address:"/app/queue/example-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_queue_sendto-queue":{address:"/app/queue/sendto-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_queue_sendtouser-queue":{address:"/app/queue/sendtouser-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_app_topic_sendto-response-queue":{address:"/app/topic/sendto-response-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}},"_user_queue_sendtouser-response-queue":{address:"/user/queue/sendtouser-response-queue",messages:{"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{$ref:"#/components/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},bindings:{stomp:{}}}},components:{schemas:{HeadersNotDocumented:{title:"HeadersNotDocumented",type:"object",properties:{},description:"There can be headers, but they are not explicitly documented.",examples:[{}]},SpringStompDefaultHeaders:{title:"SpringStompDefaultHeaders",type:"object",properties:{},examples:[{}]},"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{title:"AnotherPayloadDto",type:"object",properties:{example:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"},foo:{type:"string",description:"Foo field",maxLength:100,examples:["bar"]}},description:"Another payload model",examples:[{example:{someEnum:"FOO2",someLong:5,someString:"some string value"},foo:"bar"}],required:["example"]},"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{title:"ExamplePayloadDto",type:"object",properties:{someEnum:{title:"ExampleEnum",type:"string",description:"Some enum field",enum:["FOO1","FOO2","FOO3"],examples:["FOO2"]},someLong:{type:"integer",description:"Some long field",format:"int64",minimum:0,examples:[5]},someString:{type:"string",description:"Some string field",examples:["some string value"]}},description:"Example payload model",examples:[{someEnum:"FOO2",someLong:5,someString:"some string value"}],required:["someEnum","someString"]}},messages:{"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto":{headers:{$ref:"#/components/schemas/HeadersNotDocumented"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}},name:"io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto",title:"AnotherPayloadDto",bindings:{stomp:{}}},"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto":{headers:{$ref:"#/components/schemas/SpringStompDefaultHeaders"},payload:{schemaFormat:"application/vnd.aai.asyncapi+json;version=3.1.0",schema:{$ref:"#/components/schemas/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}},name:"io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto",title:"ExamplePayloadDto",bindings:{stomp:{}}}}},operations:{"_app_queue_another-queue_send_sendMessage":{action:"send",channel:{$ref:"#/channels/_app_queue_another-queue"},title:"_app_queue_another-queue_send",description:"Custom, optional description defined in the AsyncPublisher annotation",bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_another-queue/messages/io.github.springwolf.examples.stomp.dtos.AnotherPayloadDto"}]},"_app_queue_example-queue_receive_receiveExamplePayload":{action:"receive",channel:{$ref:"#/channels/_app_queue_example-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_example-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]},"_app_queue_sendto-queue_receive_receiveExamplePayloadSendTo":{action:"receive",channel:{$ref:"#/channels/_app_queue_sendto-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_sendto-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}],reply:{channel:{$ref:"#/channels/_app_topic_sendto-response-queue"},messages:[{$ref:"#/channels/_app_topic_sendto-response-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]}},"_app_queue_sendtouser-queue_receive_receiveExamplePayloadSendToUser":{action:"receive",channel:{$ref:"#/channels/_app_queue_sendtouser-queue"},bindings:{stomp:{}},messages:[{$ref:"#/channels/_app_queue_sendtouser-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}],reply:{channel:{$ref:"#/channels/_user_queue_sendtouser-response-queue"},messages:[{$ref:"#/channels/_user_queue_sendtouser-response-queue/messages/io.github.springwolf.examples.stomp.dtos.ExamplePayloadDto"}]}}}};var _s={amqp:{value:Sh},"cloud-stream":{value:Mh},jms:{value:Oh},kafka:{value:Ah,groups:{"Only Vehicles":Ih},uiConfig:Th},sns:{value:Rh},sqs:{value:Ph},stomp:{value:Fh}};var bs=class{mockData=this.selectMockData();createDb(){return{}}get(n){if(n.req.url.endsWith("/docs"))return n.utils.createResponse$(()=>({status:ce.OK,body:this.mockData.value}));if(n.req.url.indexOf("/docs/")!==-1){let e=n.req.url.split("/docs/")[1];return n.utils.createResponse$(()=>this.mockData.groups===void 0||this.mockData.groups[e]===void 0?{status:ce.NOT_FOUND,body:void 0}:{status:ce.OK,body:this.mockData.groups[e]})}else{if(n.req.url.endsWith("/publish"))return n.utils.createResponse$(()=>({status:ce.OK,body:{}}));if(n.req.url.endsWith("/ui-config"))return this.mockData.uiConfig?n.utils.createResponse$(()=>({status:ce.OK,body:this.mockData.uiConfig})):{status:ce.NOT_FOUND,body:void 0}}return n.utils.getPassThruBackend().handle(n.req)}post(n){return n.req.url.endsWith("/publish")?n.utils.createResponse$(()=>({status:ce.OK})):n.utils.getPassThruBackend().handle(n.req)}selectMockData(){let n=window.location.hostname,e=Object.keys(_s).filter(i=>n.includes(i));return 0<e.length?_s[e[0]]:_s.kafka}};function Nh(t,n){let i=!n?.manualCleanup?n?.injector?.get(Ji)??m(Ji):null,r=p0(n?.equal),o;n?.requireSync?o=X({kind:0},{equal:r}):o=X({kind:1,value:n?.initialValue},{equal:r});let a,s=t.subscribe({next:l=>o.set({kind:1,value:l}),error:l=>{o.set({kind:2,error:l}),a?.()},complete:()=>{a?.()}});if(n?.requireSync&&o().kind===0)throw new Fe(601,!1);return a=i?.onDestroy(s.unsubscribe.bind(s)),en(()=>{let l=o();switch(l.kind){case 1:return l.value;case 2:throw l.error;case 0:throw new Fe(601,!1)}},{equal:n?.equal})}function p0(t=Object.is){return(n,e)=>n.kind===1&&e.kind===1&&t(n.value,e.value)}function Qc(){return{async:!1,breaks:!1,extensions:null,gfm:!0,hooks:null,pedantic:!1,renderer:null,silent:!1,tokenizer:null,walkTokens:null}}var Ci=Qc();function Hh(t){Ci=t}var $o={exec:()=>null};function ve(t,n=""){let e=typeof t=="string"?t:t.source,i={replace:(r,o)=>{let a=typeof o=="string"?o:o.source;return a=a.replace(xt.caret,"$1"),e=e.replace(r,a),i},getRegex:()=>new RegExp(e,n)};return i}var h0=(()=>{try{return!!new RegExp("(?<=1)(?<!1)")}catch{return!1}})(),xt={codeRemoveIndent:/^(?: {1,4}| {0,3}\t)/gm,outputLinkReplace:/\\([\[\]])/g,indentCodeCompensation:/^(\s+)(?:```)/,beginningSpace:/^\s+/,endingHash:/#$/,startingSpaceChar:/^ /,endingSpaceChar:/ $/,nonSpaceChar:/[^ ]/,newLineCharGlobal:/\n/g,tabCharGlobal:/\t/g,multipleSpaceGlobal:/\s+/g,blankLine:/^[ \t]*$/,doubleBlankLine:/\n[ \t]*\n[ \t]*$/,blockquoteStart:/^ {0,3}>/,blockquoteSetextReplace:/\n {0,3}((?:=+|-+) *)(?=\n|$)/g,blockquoteSetextReplace2:/^ {0,3}>[ \t]?/gm,listReplaceTabs:/^\t+/,listReplaceNesting:/^ {1,4}(?=( {4})*[^ ])/g,listIsTask:/^\[[ xX]\] /,listReplaceTask:/^\[[ xX]\] +/,anyLine:/\n.*\n/,hrefBrackets:/^<(.*)>$/,tableDelimiter:/[:|]/,tableAlignChars:/^\||\| *$/g,tableRowBlankLine:/\n[ \t]*$/,tableAlignRight:/^ *-+: *$/,tableAlignCenter:/^ *:-+: *$/,tableAlignLeft:/^ *:-+ *$/,startATag:/^<a /i,endATag:/^<\/a>/i,startPreScriptTag:/^<(pre|code|kbd|script)(\s|>)/i,endPreScriptTag:/^<\/(pre|code|kbd|script)(\s|>)/i,startAngleBracket:/^</,endAngleBracket:/>$/,pedanticHrefTitle:/^([^'"]*[^\s])\s+(['"])(.*)\2/,unicodeAlphaNumeric:/[\p{L}\p{N}]/u,escapeTest:/[&<>"']/,escapeReplace:/[&<>"']/g,escapeTestNoEncode:/[<>"']|&(?!(#\d{1,7}|#[Xx][a-fA-F0-9]{1,6}|\w+);)/,escapeReplaceNoEncode:/[<>"']|&(?!(#\d{1,7}|#[Xx][a-fA-F0-9]{1,6}|\w+);)/g,unescapeTest:/&(#(?:\d+)|(?:#x[0-9A-Fa-f]+)|(?:\w+));?/ig,caret:/(^|[^\[])\^/g,percentDecode:/%25/g,findPipe:/\|/g,splitPipe:/ \|/,slashPipe:/\\\|/g,carriageReturn:/\r\n|\r/g,spaceLine:/^ +$/gm,notSpaceStart:/^\S*/,endingNewline:/\n$/,listItemRegex:t=>new RegExp(`^( {0,3}${t})((?:[	 ][^\\n]*)?(?:\\n|$))`),nextBulletRegex:t=>new RegExp(`^ {0,${Math.min(3,t-1)}}(?:[*+-]|\\d{1,9}[.)])((?:[ 	][^\\n]*)?(?:\\n|$))`),hrRegex:t=>new RegExp(`^ {0,${Math.min(3,t-1)}}((?:- *){3,}|(?:_ *){3,}|(?:\\* *){3,})(?:\\n+|$)`),fencesBeginRegex:t=>new RegExp(`^ {0,${Math.min(3,t-1)}}(?:\`\`\`|~~~)`),headingBeginRegex:t=>new RegExp(`^ {0,${Math.min(3,t-1)}}#`),htmlBeginRegex:t=>new RegExp(`^ {0,${Math.min(3,t-1)}}<(?:[a-z].*>|!--)`,"i")},f0=/^(?:[ \t]*(?:\n|$))+/,g0=/^((?: {4}| {0,3}\t)[^\n]+(?:\n(?:[ \t]*(?:\n|$))*)?)+/,_0=/^ {0,3}(`{3,}(?=[^`\n]*(?:\n|$))|~{3,})([^\n]*)(?:\n|$)(?:|([\s\S]*?)(?:\n|$))(?: {0,3}\1[~`]* *(?=\n|$)|$)/,jo=/^ {0,3}((?:-[\t ]*){3,}|(?:_[ \t]*){3,}|(?:\*[ \t]*){3,})(?:\n+|$)/,b0=/^ {0,3}(#{1,6})(?=\s|$)(.*)(?:\n+|$)/,Xc=/(?:[*+-]|\d{1,9}[.)])/,Uh=/^(?!bull |blockCode|fences|blockquote|heading|html|table)((?:.|\n(?!\s*?\n|bull |blockCode|fences|blockquote|heading|html|table))+?)\n {0,3}(=+|-+) *(?:\n+|$)/,qh=ve(Uh).replace(/bull/g,Xc).replace(/blockCode/g,/(?: {4}| {0,3}\t)/).replace(/fences/g,/ {0,3}(?:`{3,}|~{3,})/).replace(/blockquote/g,/ {0,3}>/).replace(/heading/g,/ {0,3}#{1,6}/).replace(/html/g,/ {0,3}<[^\n>]+>\n/).replace(/\|table/g,"").getRegex(),v0=ve(Uh).replace(/bull/g,Xc).replace(/blockCode/g,/(?: {4}| {0,3}\t)/).replace(/fences/g,/ {0,3}(?:`{3,}|~{3,})/).replace(/blockquote/g,/ {0,3}>/).replace(/heading/g,/ {0,3}#{1,6}/).replace(/html/g,/ {0,3}<[^\n>]+>\n/).replace(/table/g,/ {0,3}\|?(?:[:\- ]*\|)+[\:\- ]*\n/).getRegex(),Zc=/^([^\n]+(?:\n(?!hr|heading|lheading|blockquote|fences|list|html|table| +\n)[^\n]+)*)/,y0=/^[^\n]+/,Jc=/(?!\s*\])(?:\\[\s\S]|[^\[\]\\])+/,x0=ve(/^ {0,3}\[(label)\]: *(?:\n[ \t]*)?([^<\s][^\s]*|<.*?>)(?:(?: +(?:\n[ \t]*)?| *\n[ \t]*)(title))? *(?:\n+|$)/).replace("label",Jc).replace("title",/(?:"(?:\\"?|[^"\\])*"|'[^'\n]*(?:\n[^'\n]+)*\n?'|\([^()]*\))/).getRegex(),w0=ve(/^( {0,3}bull)([ \t][^\n]+?)?(?:\n|$)/).replace(/bull/g,Xc).getRegex(),ws="address|article|aside|base|basefont|blockquote|body|caption|center|col|colgroup|dd|details|dialog|dir|div|dl|dt|fieldset|figcaption|figure|footer|form|frame|frameset|h[1-6]|head|header|hr|html|iframe|legend|li|link|main|menu|menuitem|meta|nav|noframes|ol|optgroup|option|p|param|search|section|summary|table|tbody|td|tfoot|th|thead|title|tr|track|ul",ed=/<!--(?:-?>|[\s\S]*?(?:-->|$))/,C0=ve("^ {0,3}(?:<(script|pre|style|textarea)[\\s>][\\s\\S]*?(?:</\\1>[^\\n]*\\n+|$)|comment[^\\n]*(\\n+|$)|<\\?[\\s\\S]*?(?:\\?>\\n*|$)|<![A-Z][\\s\\S]*?(?:>\\n*|$)|<!\\[CDATA\\[[\\s\\S]*?(?:\\]\\]>\\n*|$)|</?(tag)(?: +|\\n|/?>)[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$)|<(?!script|pre|style|textarea)([a-z][\\w-]*)(?:attribute)*? */?>(?=[ \\t]*(?:\\n|$))[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$)|</(?!script|pre|style|textarea)[a-z][\\w-]*\\s*>(?=[ \\t]*(?:\\n|$))[\\s\\S]*?(?:(?:\\n[ 	]*)+\\n|$))","i").replace("comment",ed).replace("tag",ws).replace("attribute",/ +[a-zA-Z:_][\w.:-]*(?: *= *"[^"\n]*"| *= *'[^'\n]*'| *= *[^\s"'=<>`]+)?/).getRegex(),Gh=ve(Zc).replace("hr",jo).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("|lheading","").replace("|table","").replace("blockquote"," {0,3}>").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)]) ").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",ws).getRegex(),k0=ve(/^( {0,3}> ?(paragraph|[^\n]*)(?:\n|$))+/).replace("paragraph",Gh).getRegex(),td={blockquote:k0,code:g0,def:x0,fences:_0,heading:b0,hr:jo,html:C0,lheading:qh,list:w0,newline:f0,paragraph:Gh,table:$o,text:y0},Lh=ve("^ *([^\\n ].*)\\n {0,3}((?:\\| *)?:?-+:? *(?:\\| *:?-+:? *)*(?:\\| *)?)(?:\\n((?:(?! *\\n|hr|heading|blockquote|code|fences|list|html).*(?:\\n|$))*)\\n*|$)").replace("hr",jo).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("blockquote"," {0,3}>").replace("code","(?: {4}| {0,3}	)[^\\n]").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)]) ").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",ws).getRegex(),D0=Ie(M({},td),{lheading:v0,table:Lh,paragraph:ve(Zc).replace("hr",jo).replace("heading"," {0,3}#{1,6}(?:\\s|$)").replace("|lheading","").replace("table",Lh).replace("blockquote"," {0,3}>").replace("fences"," {0,3}(?:`{3,}(?=[^`\\n]*\\n)|~{3,})[^\\n]*\\n").replace("list"," {0,3}(?:[*+-]|1[.)]) ").replace("html","</?(?:tag)(?: +|\\n|/?>)|<(?:script|pre|style|textarea|!--)").replace("tag",ws).getRegex()}),E0=Ie(M({},td),{html:ve(`^ *(?:comment *(?:\\n|\\s*$)|<(tag)[\\s\\S]+?</\\1> *(?:\\n{2,}|\\s*$)|<tag(?:"[^"]*"|'[^']*'|\\s[^'"/>\\s]*)*?/?> *(?:\\n{2,}|\\s*$))`).replace("comment",ed).replace(/tag/g,"(?!(?:a|em|strong|small|s|cite|q|dfn|abbr|data|time|code|var|samp|kbd|sub|sup|i|b|u|mark|ruby|rt|rp|bdi|bdo|span|br|wbr|ins|del|img)\\b)\\w+(?!:|[^\\w\\s@]*@)\\b").getRegex(),def:/^ *\[([^\]]+)\]: *<?([^\s>]+)>?(?: +(["(][^\n]+[")]))? *(?:\n+|$)/,heading:/^(#{1,6})(.*)(?:\n+|$)/,fences:$o,lheading:/^(.+?)\n {0,3}(=+|-+) *(?:\n+|$)/,paragraph:ve(Zc).replace("hr",jo).replace("heading",` *#{1,6} *[^
]`).replace("lheading",qh).replace("|table","").replace("blockquote"," {0,3}>").replace("|fences","").replace("|list","").replace("|html","").replace("|tag","").getRegex()}),S0=/^\\([!"#$%&'()*+,\-./:;<=>?@\[\]\\^_`{|}~])/,M0=/^(`+)([^`]|[^`][\s\S]*?[^`])\1(?!`)/,Wh=/^( {2,}|\\)\n(?!\s*$)/,A0=/^(`+|[^`])(?:(?= {2,}\n)|[\s\S]*?(?:(?=[\\<!\[`*_]|\b_|$)|[^ ](?= {2,}\n)))/,Cs=/[\p{P}\p{S}]/u,nd=/[\s\p{P}\p{S}]/u,Kh=/[^\s\p{P}\p{S}]/u,I0=ve(/^((?![*_])punctSpace)/,"u").replace(/punctSpace/g,nd).getRegex(),Yh=/(?!~)[\p{P}\p{S}]/u,T0=/(?!~)[\s\p{P}\p{S}]/u,O0=/(?:[^\s\p{P}\p{S}]|~)/u,R0=ve(/link|precode-code|html/,"g").replace("link",/\[(?:[^\[\]`]|(?<a>`+)[^`]+\k<a>(?!`))*?\]\((?:\\[\s\S]|[^\\\(\)]|\((?:\\[\s\S]|[^\\\(\)])*\))*\)/).replace("precode-",h0?"(?<!`)()":"(^^|[^`])").replace("code",/(?<b>`+)[^`]+\k<b>(?!`)/).replace("html",/<(?! )[^<>]*?>/).getRegex(),Qh=/^(?:\*+(?:((?!\*)punct)|[^\s*]))|^_+(?:((?!_)punct)|([^\s_]))/,P0=ve(Qh,"u").replace(/punct/g,Cs).getRegex(),F0=ve(Qh,"u").replace(/punct/g,Yh).getRegex(),Xh="^[^_*]*?__[^_*]*?\\*[^_*]*?(?=__)|[^*]+(?=[^*])|(?!\\*)punct(\\*+)(?=[\\s]|$)|notPunctSpace(\\*+)(?!\\*)(?=punctSpace|$)|(?!\\*)punctSpace(\\*+)(?=notPunctSpace)|[\\s](\\*+)(?!\\*)(?=punct)|(?!\\*)punct(\\*+)(?!\\*)(?=punct)|notPunctSpace(\\*+)(?=notPunctSpace)",N0=ve(Xh,"gu").replace(/notPunctSpace/g,Kh).replace(/punctSpace/g,nd).replace(/punct/g,Cs).getRegex(),L0=ve(Xh,"gu").replace(/notPunctSpace/g,O0).replace(/punctSpace/g,T0).replace(/punct/g,Yh).getRegex(),B0=ve("^[^_*]*?\\*\\*[^_*]*?_[^_*]*?(?=\\*\\*)|[^_]+(?=[^_])|(?!_)punct(_+)(?=[\\s]|$)|notPunctSpace(_+)(?!_)(?=punctSpace|$)|(?!_)punctSpace(_+)(?=notPunctSpace)|[\\s](_+)(?!_)(?=punct)|(?!_)punct(_+)(?!_)(?=punct)","gu").replace(/notPunctSpace/g,Kh).replace(/punctSpace/g,nd).replace(/punct/g,Cs).getRegex(),$0=ve(/\\(punct)/,"gu").replace(/punct/g,Cs).getRegex(),j0=ve(/^<(scheme:[^\s\x00-\x1f<>]*|email)>/).replace("scheme",/[a-zA-Z][a-zA-Z0-9+.-]{1,31}/).replace("email",/[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+(@)[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+(?![-_])/).getRegex(),V0=ve(ed).replace("(?:-->|$)","-->").getRegex(),z0=ve("^comment|^</[a-zA-Z][\\w:-]*\\s*>|^<[a-zA-Z][\\w-]*(?:attribute)*?\\s*/?>|^<\\?[\\s\\S]*?\\?>|^<![a-zA-Z]+\\s[\\s\\S]*?>|^<!\\[CDATA\\[[\\s\\S]*?\\]\\]>").replace("comment",V0).replace("attribute",/\s+[a-zA-Z:_][\w.:-]*(?:\s*=\s*"[^"]*"|\s*=\s*'[^']*'|\s*=\s*[^\s"'=<>`]+)?/).getRegex(),ys=/(?:\[(?:\\[\s\S]|[^\[\]\\])*\]|\\[\s\S]|`+[^`]*?`+(?!`)|[^\[\]\\`])*?/,H0=ve(/^!?\[(label)\]\(\s*(href)(?:(?:[ \t]*(?:\n[ \t]*)?)(title))?\s*\)/).replace("label",ys).replace("href",/<(?:\\.|[^\n<>\\])+>|[^ \t\n\x00-\x1f]*/).replace("title",/"(?:\\"?|[^"\\])*"|'(?:\\'?|[^'\\])*'|\((?:\\\)?|[^)\\])*\)/).getRegex(),Zh=ve(/^!?\[(label)\]\[(ref)\]/).replace("label",ys).replace("ref",Jc).getRegex(),Jh=ve(/^!?\[(ref)\](?:\[\])?/).replace("ref",Jc).getRegex(),U0=ve("reflink|nolink(?!\\()","g").replace("reflink",Zh).replace("nolink",Jh).getRegex(),Bh=/[hH][tT][tT][pP][sS]?|[fF][tT][pP]/,id={_backpedal:$o,anyPunctuation:$0,autolink:j0,blockSkip:R0,br:Wh,code:M0,del:$o,emStrongLDelim:P0,emStrongRDelimAst:N0,emStrongRDelimUnd:B0,escape:S0,link:H0,nolink:Jh,punctuation:I0,reflink:Zh,reflinkSearch:U0,tag:z0,text:A0,url:$o},q0=Ie(M({},id),{link:ve(/^!?\[(label)\]\((.*?)\)/).replace("label",ys).getRegex(),reflink:ve(/^!?\[(label)\]\s*\[([^\]]*)\]/).replace("label",ys).getRegex()}),Wc=Ie(M({},id),{emStrongRDelimAst:L0,emStrongLDelim:F0,url:ve(/^((?:protocol):\/\/|www\.)(?:[a-zA-Z0-9\-]+\.?)+[^\s<]*|^email/).replace("protocol",Bh).replace("email",/[A-Za-z0-9._+-]+(@)[a-zA-Z0-9-_]+(?:\.[a-zA-Z0-9-_]*[a-zA-Z0-9])+(?![-_])/).getRegex(),_backpedal:/(?:[^?!.,:;*_'"~()&]+|\([^)]*\)|&(?![a-zA-Z0-9]+;$)|[?!.,:;*_'"~)]+(?!$))+/,del:/^(~~?)(?=[^\s~])((?:\\[\s\S]|[^\\])*?(?:\\[\s\S]|[^\s~\\]))\1(?=[^~]|$)/,text:ve(/^([`~]+|[^`~])(?:(?= {2,}\n)|(?=[a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-]+@)|[\s\S]*?(?:(?=[\\<!\[`*~_]|\b_|protocol:\/\/|www\.|$)|[^ ](?= {2,}\n)|[^a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-](?=[a-zA-Z0-9.!#$%&'*+\/=?_`{\|}~-]+@)))/).replace("protocol",Bh).getRegex()}),G0=Ie(M({},Wc),{br:ve(Wh).replace("{2,}","*").getRegex(),text:ve(Wc.text).replace("\\b_","\\b_| {2,}\\n").replace(/\{2,\}/g,"*").getRegex()}),vs={normal:td,gfm:D0,pedantic:E0},No={normal:id,gfm:Wc,breaks:G0,pedantic:q0},W0={"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;"},$h=t=>W0[t];function _n(t,n){if(n){if(xt.escapeTest.test(t))return t.replace(xt.escapeReplace,$h)}else if(xt.escapeTestNoEncode.test(t))return t.replace(xt.escapeReplaceNoEncode,$h);return t}function jh(t){try{t=encodeURI(t).replace(xt.percentDecode,"%")}catch{return null}return t}function Vh(t,n){let e=t.replace(xt.findPipe,(o,a,s)=>{let l=!1,c=a;for(;--c>=0&&s[c]==="\\";)l=!l;return l?"|":" |"}),i=e.split(xt.splitPipe),r=0;if(i[0].trim()||i.shift(),i.length>0&&!i.at(-1)?.trim()&&i.pop(),n)if(i.length>n)i.splice(n);else for(;i.length<n;)i.push("");for(;r<i.length;r++)i[r]=i[r].trim().replace(xt.slashPipe,"|");return i}function Lo(t,n,e){let i=t.length;if(i===0)return"";let r=0;for(;r<i;){let o=t.charAt(i-r-1);if(o===n&&!e)r++;else if(o!==n&&e)r++;else break}return t.slice(0,i-r)}function K0(t,n){if(t.indexOf(n[1])===-1)return-1;let e=0;for(let i=0;i<t.length;i++)if(t[i]==="\\")i++;else if(t[i]===n[0])e++;else if(t[i]===n[1]&&(e--,e<0))return i;return e>0?-2:-1}function zh(t,n,e,i,r){let o=n.href,a=n.title||null,s=t[1].replace(r.other.outputLinkReplace,"$1");i.state.inLink=!0;let l={type:t[0].charAt(0)==="!"?"image":"link",raw:e,href:o,title:a,text:s,tokens:i.inlineTokens(s)};return i.state.inLink=!1,l}function Y0(t,n,e){let i=t.match(e.other.indentCodeCompensation);if(i===null)return n;let r=i[1];return n.split(`
`).map(o=>{let a=o.match(e.other.beginningSpace);if(a===null)return o;let[s]=a;return s.length>=r.length?o.slice(r.length):o}).join(`
`)}var xs=class{options;rules;lexer;constructor(t){this.options=t||Ci}space(t){let n=this.rules.block.newline.exec(t);if(n&&n[0].length>0)return{type:"space",raw:n[0]}}code(t){let n=this.rules.block.code.exec(t);if(n){let e=n[0].replace(this.rules.other.codeRemoveIndent,"");return{type:"code",raw:n[0],codeBlockStyle:"indented",text:this.options.pedantic?e:Lo(e,`
`)}}}fences(t){let n=this.rules.block.fences.exec(t);if(n){let e=n[0],i=Y0(e,n[3]||"",this.rules);return{type:"code",raw:e,lang:n[2]?n[2].trim().replace(this.rules.inline.anyPunctuation,"$1"):n[2],text:i}}}heading(t){let n=this.rules.block.heading.exec(t);if(n){let e=n[2].trim();if(this.rules.other.endingHash.test(e)){let i=Lo(e,"#");(this.options.pedantic||!i||this.rules.other.endingSpaceChar.test(i))&&(e=i.trim())}return{type:"heading",raw:n[0],depth:n[1].length,text:e,tokens:this.lexer.inline(e)}}}hr(t){let n=this.rules.block.hr.exec(t);if(n)return{type:"hr",raw:Lo(n[0],`
`)}}blockquote(t){let n=this.rules.block.blockquote.exec(t);if(n){let e=Lo(n[0],`
`).split(`
`),i="",r="",o=[];for(;e.length>0;){let a=!1,s=[],l;for(l=0;l<e.length;l++)if(this.rules.other.blockquoteStart.test(e[l]))s.push(e[l]),a=!0;else if(!a)s.push(e[l]);else break;e=e.slice(l);let c=s.join(`
`),d=c.replace(this.rules.other.blockquoteSetextReplace,`
    $1`).replace(this.rules.other.blockquoteSetextReplace2,"");i=i?`${i}
${c}`:c,r=r?`${r}
${d}`:d;let u=this.lexer.state.top;if(this.lexer.state.top=!0,this.lexer.blockTokens(d,o,!0),this.lexer.state.top=u,e.length===0)break;let p=o.at(-1);if(p?.type==="code")break;if(p?.type==="blockquote"){let y=p,g=y.raw+`
`+e.join(`
`),_=this.blockquote(g);o[o.length-1]=_,i=i.substring(0,i.length-y.raw.length)+_.raw,r=r.substring(0,r.length-y.text.length)+_.text;break}else if(p?.type==="list"){let y=p,g=y.raw+`
`+e.join(`
`),_=this.list(g);o[o.length-1]=_,i=i.substring(0,i.length-p.raw.length)+_.raw,r=r.substring(0,r.length-y.raw.length)+_.raw,e=g.substring(o.at(-1).raw.length).split(`
`);continue}}return{type:"blockquote",raw:i,tokens:o,text:r}}}list(t){let n=this.rules.block.list.exec(t);if(n){let e=n[1].trim(),i=e.length>1,r={type:"list",raw:"",ordered:i,start:i?+e.slice(0,-1):"",loose:!1,items:[]};e=i?`\\d{1,9}\\${e.slice(-1)}`:`\\${e}`,this.options.pedantic&&(e=i?e:"[*+-]");let o=this.rules.other.listItemRegex(e),a=!1;for(;t;){let l=!1,c="",d="";if(!(n=o.exec(t))||this.rules.block.hr.test(t))break;c=n[0],t=t.substring(c.length);let u=n[2].split(`
`,1)[0].replace(this.rules.other.listReplaceTabs,x=>" ".repeat(3*x.length)),p=t.split(`
`,1)[0],y=!u.trim(),g=0;if(this.options.pedantic?(g=2,d=u.trimStart()):y?g=n[1].length+1:(g=n[2].search(this.rules.other.nonSpaceChar),g=g>4?1:g,d=u.slice(g),g+=n[1].length),y&&this.rules.other.blankLine.test(p)&&(c+=p+`
`,t=t.substring(p.length+1),l=!0),!l){let x=this.rules.other.nextBulletRegex(g),S=this.rules.other.hrRegex(g),I=this.rules.other.fencesBeginRegex(g),F=this.rules.other.headingBeginRegex(g),N=this.rules.other.htmlBeginRegex(g);for(;t;){let ge=t.split(`
`,1)[0],Ce;if(p=ge,this.options.pedantic?(p=p.replace(this.rules.other.listReplaceNesting,"  "),Ce=p):Ce=p.replace(this.rules.other.tabCharGlobal,"    "),I.test(p)||F.test(p)||N.test(p)||x.test(p)||S.test(p))break;if(Ce.search(this.rules.other.nonSpaceChar)>=g||!p.trim())d+=`
`+Ce.slice(g);else{if(y||u.replace(this.rules.other.tabCharGlobal,"    ").search(this.rules.other.nonSpaceChar)>=4||I.test(u)||F.test(u)||S.test(u))break;d+=`
`+p}!y&&!p.trim()&&(y=!0),c+=ge+`
`,t=t.substring(ge.length+1),u=Ce.slice(g)}}r.loose||(a?r.loose=!0:this.rules.other.doubleBlankLine.test(c)&&(a=!0));let _=null,v;this.options.gfm&&(_=this.rules.other.listIsTask.exec(d),_&&(v=_[0]!=="[ ] ",d=d.replace(this.rules.other.listReplaceTask,""))),r.items.push({type:"list_item",raw:c,task:!!_,checked:v,loose:!1,text:d,tokens:[]}),r.raw+=c}let s=r.items.at(-1);if(s)s.raw=s.raw.trimEnd(),s.text=s.text.trimEnd();else return;r.raw=r.raw.trimEnd();for(let l=0;l<r.items.length;l++)if(this.lexer.state.top=!1,r.items[l].tokens=this.lexer.blockTokens(r.items[l].text,[]),!r.loose){let c=r.items[l].tokens.filter(u=>u.type==="space"),d=c.length>0&&c.some(u=>this.rules.other.anyLine.test(u.raw));r.loose=d}if(r.loose)for(let l=0;l<r.items.length;l++)r.items[l].loose=!0;return r}}html(t){let n=this.rules.block.html.exec(t);if(n)return{type:"html",block:!0,raw:n[0],pre:n[1]==="pre"||n[1]==="script"||n[1]==="style",text:n[0]}}def(t){let n=this.rules.block.def.exec(t);if(n){let e=n[1].toLowerCase().replace(this.rules.other.multipleSpaceGlobal," "),i=n[2]?n[2].replace(this.rules.other.hrefBrackets,"$1").replace(this.rules.inline.anyPunctuation,"$1"):"",r=n[3]?n[3].substring(1,n[3].length-1).replace(this.rules.inline.anyPunctuation,"$1"):n[3];return{type:"def",tag:e,raw:n[0],href:i,title:r}}}table(t){let n=this.rules.block.table.exec(t);if(!n||!this.rules.other.tableDelimiter.test(n[2]))return;let e=Vh(n[1]),i=n[2].replace(this.rules.other.tableAlignChars,"").split("|"),r=n[3]?.trim()?n[3].replace(this.rules.other.tableRowBlankLine,"").split(`
`):[],o={type:"table",raw:n[0],header:[],align:[],rows:[]};if(e.length===i.length){for(let a of i)this.rules.other.tableAlignRight.test(a)?o.align.push("right"):this.rules.other.tableAlignCenter.test(a)?o.align.push("center"):this.rules.other.tableAlignLeft.test(a)?o.align.push("left"):o.align.push(null);for(let a=0;a<e.length;a++)o.header.push({text:e[a],tokens:this.lexer.inline(e[a]),header:!0,align:o.align[a]});for(let a of r)o.rows.push(Vh(a,o.header.length).map((s,l)=>({text:s,tokens:this.lexer.inline(s),header:!1,align:o.align[l]})));return o}}lheading(t){let n=this.rules.block.lheading.exec(t);if(n)return{type:"heading",raw:n[0],depth:n[2].charAt(0)==="="?1:2,text:n[1],tokens:this.lexer.inline(n[1])}}paragraph(t){let n=this.rules.block.paragraph.exec(t);if(n){let e=n[1].charAt(n[1].length-1)===`
`?n[1].slice(0,-1):n[1];return{type:"paragraph",raw:n[0],text:e,tokens:this.lexer.inline(e)}}}text(t){let n=this.rules.block.text.exec(t);if(n)return{type:"text",raw:n[0],text:n[0],tokens:this.lexer.inline(n[0])}}escape(t){let n=this.rules.inline.escape.exec(t);if(n)return{type:"escape",raw:n[0],text:n[1]}}tag(t){let n=this.rules.inline.tag.exec(t);if(n)return!this.lexer.state.inLink&&this.rules.other.startATag.test(n[0])?this.lexer.state.inLink=!0:this.lexer.state.inLink&&this.rules.other.endATag.test(n[0])&&(this.lexer.state.inLink=!1),!this.lexer.state.inRawBlock&&this.rules.other.startPreScriptTag.test(n[0])?this.lexer.state.inRawBlock=!0:this.lexer.state.inRawBlock&&this.rules.other.endPreScriptTag.test(n[0])&&(this.lexer.state.inRawBlock=!1),{type:"html",raw:n[0],inLink:this.lexer.state.inLink,inRawBlock:this.lexer.state.inRawBlock,block:!1,text:n[0]}}link(t){let n=this.rules.inline.link.exec(t);if(n){let e=n[2].trim();if(!this.options.pedantic&&this.rules.other.startAngleBracket.test(e)){if(!this.rules.other.endAngleBracket.test(e))return;let o=Lo(e.slice(0,-1),"\\");if((e.length-o.length)%2===0)return}else{let o=K0(n[2],"()");if(o===-2)return;if(o>-1){let a=(n[0].indexOf("!")===0?5:4)+n[1].length+o;n[2]=n[2].substring(0,o),n[0]=n[0].substring(0,a).trim(),n[3]=""}}let i=n[2],r="";if(this.options.pedantic){let o=this.rules.other.pedanticHrefTitle.exec(i);o&&(i=o[1],r=o[3])}else r=n[3]?n[3].slice(1,-1):"";return i=i.trim(),this.rules.other.startAngleBracket.test(i)&&(this.options.pedantic&&!this.rules.other.endAngleBracket.test(e)?i=i.slice(1):i=i.slice(1,-1)),zh(n,{href:i&&i.replace(this.rules.inline.anyPunctuation,"$1"),title:r&&r.replace(this.rules.inline.anyPunctuation,"$1")},n[0],this.lexer,this.rules)}}reflink(t,n){let e;if((e=this.rules.inline.reflink.exec(t))||(e=this.rules.inline.nolink.exec(t))){let i=(e[2]||e[1]).replace(this.rules.other.multipleSpaceGlobal," "),r=n[i.toLowerCase()];if(!r){let o=e[0].charAt(0);return{type:"text",raw:o,text:o}}return zh(e,r,e[0],this.lexer,this.rules)}}emStrong(t,n,e=""){let i=this.rules.inline.emStrongLDelim.exec(t);if(!(!i||i[3]&&e.match(this.rules.other.unicodeAlphaNumeric))&&(!(i[1]||i[2])||!e||this.rules.inline.punctuation.exec(e))){let r=[...i[0]].length-1,o,a,s=r,l=0,c=i[0][0]==="*"?this.rules.inline.emStrongRDelimAst:this.rules.inline.emStrongRDelimUnd;for(c.lastIndex=0,n=n.slice(-1*t.length+r);(i=c.exec(n))!=null;){if(o=i[1]||i[2]||i[3]||i[4]||i[5]||i[6],!o)continue;if(a=[...o].length,i[3]||i[4]){s+=a;continue}else if((i[5]||i[6])&&r%3&&!((r+a)%3)){l+=a;continue}if(s-=a,s>0)continue;a=Math.min(a,a+s+l);let d=[...i[0]][0].length,u=t.slice(0,r+i.index+d+a);if(Math.min(r,a)%2){let y=u.slice(1,-1);return{type:"em",raw:u,text:y,tokens:this.lexer.inlineTokens(y)}}let p=u.slice(2,-2);return{type:"strong",raw:u,text:p,tokens:this.lexer.inlineTokens(p)}}}}codespan(t){let n=this.rules.inline.code.exec(t);if(n){let e=n[2].replace(this.rules.other.newLineCharGlobal," "),i=this.rules.other.nonSpaceChar.test(e),r=this.rules.other.startingSpaceChar.test(e)&&this.rules.other.endingSpaceChar.test(e);return i&&r&&(e=e.substring(1,e.length-1)),{type:"codespan",raw:n[0],text:e}}}br(t){let n=this.rules.inline.br.exec(t);if(n)return{type:"br",raw:n[0]}}del(t){let n=this.rules.inline.del.exec(t);if(n)return{type:"del",raw:n[0],text:n[2],tokens:this.lexer.inlineTokens(n[2])}}autolink(t){let n=this.rules.inline.autolink.exec(t);if(n){let e,i;return n[2]==="@"?(e=n[1],i="mailto:"+e):(e=n[1],i=e),{type:"link",raw:n[0],text:e,href:i,tokens:[{type:"text",raw:e,text:e}]}}}url(t){let n;if(n=this.rules.inline.url.exec(t)){let e,i;if(n[2]==="@")e=n[0],i="mailto:"+e;else{let r;do r=n[0],n[0]=this.rules.inline._backpedal.exec(n[0])?.[0]??"";while(r!==n[0]);e=n[0],n[1]==="www."?i="http://"+n[0]:i=n[0]}return{type:"link",raw:n[0],text:e,href:i,tokens:[{type:"text",raw:e,text:e}]}}}inlineText(t){let n=this.rules.inline.text.exec(t);if(n){let e=this.lexer.state.inRawBlock;return{type:"text",raw:n[0],text:n[0],escaped:e}}}},nn=class Kc{tokens;options;state;tokenizer;inlineQueue;constructor(n){this.tokens=[],this.tokens.links=Object.create(null),this.options=n||Ci,this.options.tokenizer=this.options.tokenizer||new xs,this.tokenizer=this.options.tokenizer,this.tokenizer.options=this.options,this.tokenizer.lexer=this,this.inlineQueue=[],this.state={inLink:!1,inRawBlock:!1,top:!0};let e={other:xt,block:vs.normal,inline:No.normal};this.options.pedantic?(e.block=vs.pedantic,e.inline=No.pedantic):this.options.gfm&&(e.block=vs.gfm,this.options.breaks?e.inline=No.breaks:e.inline=No.gfm),this.tokenizer.rules=e}static get rules(){return{block:vs,inline:No}}static lex(n,e){return new Kc(e).lex(n)}static lexInline(n,e){return new Kc(e).inlineTokens(n)}lex(n){n=n.replace(xt.carriageReturn,`
`),this.blockTokens(n,this.tokens);for(let e=0;e<this.inlineQueue.length;e++){let i=this.inlineQueue[e];this.inlineTokens(i.src,i.tokens)}return this.inlineQueue=[],this.tokens}blockTokens(n,e=[],i=!1){for(this.options.pedantic&&(n=n.replace(xt.tabCharGlobal,"    ").replace(xt.spaceLine,""));n;){let r;if(this.options.extensions?.block?.some(a=>(r=a.call({lexer:this},n,e))?(n=n.substring(r.raw.length),e.push(r),!0):!1))continue;if(r=this.tokenizer.space(n)){n=n.substring(r.raw.length);let a=e.at(-1);r.raw.length===1&&a!==void 0?a.raw+=`
`:e.push(r);continue}if(r=this.tokenizer.code(n)){n=n.substring(r.raw.length);let a=e.at(-1);a?.type==="paragraph"||a?.type==="text"?(a.raw+=(a.raw.endsWith(`
`)?"":`
`)+r.raw,a.text+=`
`+r.text,this.inlineQueue.at(-1).src=a.text):e.push(r);continue}if(r=this.tokenizer.fences(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.heading(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.hr(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.blockquote(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.list(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.html(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.def(n)){n=n.substring(r.raw.length);let a=e.at(-1);a?.type==="paragraph"||a?.type==="text"?(a.raw+=(a.raw.endsWith(`
`)?"":`
`)+r.raw,a.text+=`
`+r.raw,this.inlineQueue.at(-1).src=a.text):this.tokens.links[r.tag]||(this.tokens.links[r.tag]={href:r.href,title:r.title},e.push(r));continue}if(r=this.tokenizer.table(n)){n=n.substring(r.raw.length),e.push(r);continue}if(r=this.tokenizer.lheading(n)){n=n.substring(r.raw.length),e.push(r);continue}let o=n;if(this.options.extensions?.startBlock){let a=1/0,s=n.slice(1),l;this.options.extensions.startBlock.forEach(c=>{l=c.call({lexer:this},s),typeof l=="number"&&l>=0&&(a=Math.min(a,l))}),a<1/0&&a>=0&&(o=n.substring(0,a+1))}if(this.state.top&&(r=this.tokenizer.paragraph(o))){let a=e.at(-1);i&&a?.type==="paragraph"?(a.raw+=(a.raw.endsWith(`
`)?"":`
`)+r.raw,a.text+=`
`+r.text,this.inlineQueue.pop(),this.inlineQueue.at(-1).src=a.text):e.push(r),i=o.length!==n.length,n=n.substring(r.raw.length);continue}if(r=this.tokenizer.text(n)){n=n.substring(r.raw.length);let a=e.at(-1);a?.type==="text"?(a.raw+=(a.raw.endsWith(`
`)?"":`
`)+r.raw,a.text+=`
`+r.text,this.inlineQueue.pop(),this.inlineQueue.at(-1).src=a.text):e.push(r);continue}if(n){let a="Infinite loop on byte: "+n.charCodeAt(0);if(this.options.silent){console.error(a);break}else throw new Error(a)}}return this.state.top=!0,e}inline(n,e=[]){return this.inlineQueue.push({src:n,tokens:e}),e}inlineTokens(n,e=[]){let i=n,r=null;if(this.tokens.links){let l=Object.keys(this.tokens.links);if(l.length>0)for(;(r=this.tokenizer.rules.inline.reflinkSearch.exec(i))!=null;)l.includes(r[0].slice(r[0].lastIndexOf("[")+1,-1))&&(i=i.slice(0,r.index)+"["+"a".repeat(r[0].length-2)+"]"+i.slice(this.tokenizer.rules.inline.reflinkSearch.lastIndex))}for(;(r=this.tokenizer.rules.inline.anyPunctuation.exec(i))!=null;)i=i.slice(0,r.index)+"++"+i.slice(this.tokenizer.rules.inline.anyPunctuation.lastIndex);let o;for(;(r=this.tokenizer.rules.inline.blockSkip.exec(i))!=null;)o=r[2]?r[2].length:0,i=i.slice(0,r.index+o)+"["+"a".repeat(r[0].length-o-2)+"]"+i.slice(this.tokenizer.rules.inline.blockSkip.lastIndex);i=this.options.hooks?.emStrongMask?.call({lexer:this},i)??i;let a=!1,s="";for(;n;){a||(s=""),a=!1;let l;if(this.options.extensions?.inline?.some(d=>(l=d.call({lexer:this},n,e))?(n=n.substring(l.raw.length),e.push(l),!0):!1))continue;if(l=this.tokenizer.escape(n)){n=n.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.tag(n)){n=n.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.link(n)){n=n.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.reflink(n,this.tokens.links)){n=n.substring(l.raw.length);let d=e.at(-1);l.type==="text"&&d?.type==="text"?(d.raw+=l.raw,d.text+=l.text):e.push(l);continue}if(l=this.tokenizer.emStrong(n,i,s)){n=n.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.codespan(n)){n=n.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.br(n)){n=n.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.del(n)){n=n.substring(l.raw.length),e.push(l);continue}if(l=this.tokenizer.autolink(n)){n=n.substring(l.raw.length),e.push(l);continue}if(!this.state.inLink&&(l=this.tokenizer.url(n))){n=n.substring(l.raw.length),e.push(l);continue}let c=n;if(this.options.extensions?.startInline){let d=1/0,u=n.slice(1),p;this.options.extensions.startInline.forEach(y=>{p=y.call({lexer:this},u),typeof p=="number"&&p>=0&&(d=Math.min(d,p))}),d<1/0&&d>=0&&(c=n.substring(0,d+1))}if(l=this.tokenizer.inlineText(c)){n=n.substring(l.raw.length),l.raw.slice(-1)!=="_"&&(s=l.raw.slice(-1)),a=!0;let d=e.at(-1);d?.type==="text"?(d.raw+=l.raw,d.text+=l.text):e.push(l);continue}if(n){let d="Infinite loop on byte: "+n.charCodeAt(0);if(this.options.silent){console.error(d);break}else throw new Error(d)}}return e}},ri=class{options;parser;constructor(t){this.options=t||Ci}space(t){return""}code({text:t,lang:n,escaped:e}){let i=(n||"").match(xt.notSpaceStart)?.[0],r=t.replace(xt.endingNewline,"")+`
`;return i?'<pre><code class="language-'+_n(i)+'">'+(e?r:_n(r,!0))+`</code></pre>
`:"<pre><code>"+(e?r:_n(r,!0))+`</code></pre>
`}blockquote({tokens:t}){return`<blockquote>
${this.parser.parse(t)}</blockquote>
`}html({text:t}){return t}def(t){return""}heading({tokens:t,depth:n}){return`<h${n}>${this.parser.parseInline(t)}</h${n}>
`}hr(t){return`<hr>
`}list(t){let n=t.ordered,e=t.start,i="";for(let a=0;a<t.items.length;a++){let s=t.items[a];i+=this.listitem(s)}let r=n?"ol":"ul",o=n&&e!==1?' start="'+e+'"':"";return"<"+r+o+`>
`+i+"</"+r+`>
`}listitem(t){let n="";if(t.task){let e=this.checkbox({checked:!!t.checked});t.loose?t.tokens[0]?.type==="paragraph"?(t.tokens[0].text=e+" "+t.tokens[0].text,t.tokens[0].tokens&&t.tokens[0].tokens.length>0&&t.tokens[0].tokens[0].type==="text"&&(t.tokens[0].tokens[0].text=e+" "+_n(t.tokens[0].tokens[0].text),t.tokens[0].tokens[0].escaped=!0)):t.tokens.unshift({type:"text",raw:e+" ",text:e+" ",escaped:!0}):n+=e+" "}return n+=this.parser.parse(t.tokens,!!t.loose),`<li>${n}</li>
`}checkbox({checked:t}){return"<input "+(t?'checked="" ':"")+'disabled="" type="checkbox">'}paragraph({tokens:t}){return`<p>${this.parser.parseInline(t)}</p>
`}table(t){let n="",e="";for(let r=0;r<t.header.length;r++)e+=this.tablecell(t.header[r]);n+=this.tablerow({text:e});let i="";for(let r=0;r<t.rows.length;r++){let o=t.rows[r];e="";for(let a=0;a<o.length;a++)e+=this.tablecell(o[a]);i+=this.tablerow({text:e})}return i&&(i=`<tbody>${i}</tbody>`),`<table>
<thead>
`+n+`</thead>
`+i+`</table>
`}tablerow({text:t}){return`<tr>
${t}</tr>
`}tablecell(t){let n=this.parser.parseInline(t.tokens),e=t.header?"th":"td";return(t.align?`<${e} align="${t.align}">`:`<${e}>`)+n+`</${e}>
`}strong({tokens:t}){return`<strong>${this.parser.parseInline(t)}</strong>`}em({tokens:t}){return`<em>${this.parser.parseInline(t)}</em>`}codespan({text:t}){return`<code>${_n(t,!0)}</code>`}br(t){return"<br>"}del({tokens:t}){return`<del>${this.parser.parseInline(t)}</del>`}link({href:t,title:n,tokens:e}){let i=this.parser.parseInline(e),r=jh(t);if(r===null)return i;t=r;let o='<a href="'+t+'"';return n&&(o+=' title="'+_n(n)+'"'),o+=">"+i+"</a>",o}image({href:t,title:n,text:e,tokens:i}){i&&(e=this.parser.parseInline(i,this.parser.textRenderer));let r=jh(t);if(r===null)return _n(e);t=r;let o=`<img src="${t}" alt="${e}"`;return n&&(o+=` title="${_n(n)}"`),o+=">",o}text(t){return"tokens"in t&&t.tokens?this.parser.parseInline(t.tokens):"escaped"in t&&t.escaped?t.text:_n(t.text)}},rd=class{strong({text:t}){return t}em({text:t}){return t}codespan({text:t}){return t}del({text:t}){return t}html({text:t}){return t}text({text:t}){return t}link({text:t}){return""+t}image({text:t}){return""+t}br(){return""}},rn=class Yc{options;renderer;textRenderer;constructor(n){this.options=n||Ci,this.options.renderer=this.options.renderer||new ri,this.renderer=this.options.renderer,this.renderer.options=this.options,this.renderer.parser=this,this.textRenderer=new rd}static parse(n,e){return new Yc(e).parse(n)}static parseInline(n,e){return new Yc(e).parseInline(n)}parse(n,e=!0){let i="";for(let r=0;r<n.length;r++){let o=n[r];if(this.options.extensions?.renderers?.[o.type]){let s=o,l=this.options.extensions.renderers[s.type].call({parser:this},s);if(l!==!1||!["space","hr","heading","code","table","blockquote","list","html","def","paragraph","text"].includes(s.type)){i+=l||"";continue}}let a=o;switch(a.type){case"space":{i+=this.renderer.space(a);continue}case"hr":{i+=this.renderer.hr(a);continue}case"heading":{i+=this.renderer.heading(a);continue}case"code":{i+=this.renderer.code(a);continue}case"table":{i+=this.renderer.table(a);continue}case"blockquote":{i+=this.renderer.blockquote(a);continue}case"list":{i+=this.renderer.list(a);continue}case"html":{i+=this.renderer.html(a);continue}case"def":{i+=this.renderer.def(a);continue}case"paragraph":{i+=this.renderer.paragraph(a);continue}case"text":{let s=a,l=this.renderer.text(s);for(;r+1<n.length&&n[r+1].type==="text";)s=n[++r],l+=`
`+this.renderer.text(s);e?i+=this.renderer.paragraph({type:"paragraph",raw:l,text:l,tokens:[{type:"text",raw:l,text:l,escaped:!0}]}):i+=l;continue}default:{let s='Token with "'+a.type+'" type was not found.';if(this.options.silent)return console.error(s),"";throw new Error(s)}}}return i}parseInline(n,e=this.renderer){let i="";for(let r=0;r<n.length;r++){let o=n[r];if(this.options.extensions?.renderers?.[o.type]){let s=this.options.extensions.renderers[o.type].call({parser:this},o);if(s!==!1||!["escape","html","link","image","strong","em","codespan","br","del","text"].includes(o.type)){i+=s||"";continue}}let a=o;switch(a.type){case"escape":{i+=e.text(a);break}case"html":{i+=e.html(a);break}case"link":{i+=e.link(a);break}case"image":{i+=e.image(a);break}case"strong":{i+=e.strong(a);break}case"em":{i+=e.em(a);break}case"codespan":{i+=e.codespan(a);break}case"br":{i+=e.br(a);break}case"del":{i+=e.del(a);break}case"text":{i+=e.text(a);break}default:{let s='Token with "'+a.type+'" type was not found.';if(this.options.silent)return console.error(s),"";throw new Error(s)}}}return i}},Bo=class{options;block;constructor(t){this.options=t||Ci}static passThroughHooks=new Set(["preprocess","postprocess","processAllTokens","emStrongMask"]);static passThroughHooksRespectAsync=new Set(["preprocess","postprocess","processAllTokens"]);preprocess(t){return t}postprocess(t){return t}processAllTokens(t){return t}emStrongMask(t){return t}provideLexer(){return this.block?nn.lex:nn.lexInline}provideParser(){return this.block?rn.parse:rn.parseInline}},Q0=class{defaults=Qc();options=this.setOptions;parse=this.parseMarkdown(!0);parseInline=this.parseMarkdown(!1);Parser=rn;Renderer=ri;TextRenderer=rd;Lexer=nn;Tokenizer=xs;Hooks=Bo;constructor(...t){this.use(...t)}walkTokens(t,n){let e=[];for(let i of t)switch(e=e.concat(n.call(this,i)),i.type){case"table":{let r=i;for(let o of r.header)e=e.concat(this.walkTokens(o.tokens,n));for(let o of r.rows)for(let a of o)e=e.concat(this.walkTokens(a.tokens,n));break}case"list":{let r=i;e=e.concat(this.walkTokens(r.items,n));break}default:{let r=i;this.defaults.extensions?.childTokens?.[r.type]?this.defaults.extensions.childTokens[r.type].forEach(o=>{let a=r[o].flat(1/0);e=e.concat(this.walkTokens(a,n))}):r.tokens&&(e=e.concat(this.walkTokens(r.tokens,n)))}}return e}use(...t){let n=this.defaults.extensions||{renderers:{},childTokens:{}};return t.forEach(e=>{let i=M({},e);if(i.async=this.defaults.async||i.async||!1,e.extensions&&(e.extensions.forEach(r=>{if(!r.name)throw new Error("extension name required");if("renderer"in r){let o=n.renderers[r.name];o?n.renderers[r.name]=function(...a){let s=r.renderer.apply(this,a);return s===!1&&(s=o.apply(this,a)),s}:n.renderers[r.name]=r.renderer}if("tokenizer"in r){if(!r.level||r.level!=="block"&&r.level!=="inline")throw new Error("extension level must be 'block' or 'inline'");let o=n[r.level];o?o.unshift(r.tokenizer):n[r.level]=[r.tokenizer],r.start&&(r.level==="block"?n.startBlock?n.startBlock.push(r.start):n.startBlock=[r.start]:r.level==="inline"&&(n.startInline?n.startInline.push(r.start):n.startInline=[r.start]))}"childTokens"in r&&r.childTokens&&(n.childTokens[r.name]=r.childTokens)}),i.extensions=n),e.renderer){let r=this.defaults.renderer||new ri(this.defaults);for(let o in e.renderer){if(!(o in r))throw new Error(`renderer '${o}' does not exist`);if(["options","parser"].includes(o))continue;let a=o,s=e.renderer[a],l=r[a];r[a]=(...c)=>{let d=s.apply(r,c);return d===!1&&(d=l.apply(r,c)),d||""}}i.renderer=r}if(e.tokenizer){let r=this.defaults.tokenizer||new xs(this.defaults);for(let o in e.tokenizer){if(!(o in r))throw new Error(`tokenizer '${o}' does not exist`);if(["options","rules","lexer"].includes(o))continue;let a=o,s=e.tokenizer[a],l=r[a];r[a]=(...c)=>{let d=s.apply(r,c);return d===!1&&(d=l.apply(r,c)),d}}i.tokenizer=r}if(e.hooks){let r=this.defaults.hooks||new Bo;for(let o in e.hooks){if(!(o in r))throw new Error(`hook '${o}' does not exist`);if(["options","block"].includes(o))continue;let a=o,s=e.hooks[a],l=r[a];Bo.passThroughHooks.has(o)?r[a]=c=>{if(this.defaults.async&&Bo.passThroughHooksRespectAsync.has(o))return(async()=>{let u=await s.call(r,c);return l.call(r,u)})();let d=s.call(r,c);return l.call(r,d)}:r[a]=(...c)=>{if(this.defaults.async)return(async()=>{let u=await s.apply(r,c);return u===!1&&(u=await l.apply(r,c)),u})();let d=s.apply(r,c);return d===!1&&(d=l.apply(r,c)),d}}i.hooks=r}if(e.walkTokens){let r=this.defaults.walkTokens,o=e.walkTokens;i.walkTokens=function(a){let s=[];return s.push(o.call(this,a)),r&&(s=s.concat(r.call(this,a))),s}}this.defaults=M(M({},this.defaults),i)}),this}setOptions(t){return this.defaults=M(M({},this.defaults),t),this}lexer(t,n){return nn.lex(t,n??this.defaults)}parser(t,n){return rn.parse(t,n??this.defaults)}parseMarkdown(t){return(n,e)=>{let i=M({},e),r=M(M({},this.defaults),i),o=this.onError(!!r.silent,!!r.async);if(this.defaults.async===!0&&i.async===!1)return o(new Error("marked(): The async option was set to true by an extension. Remove async: false from the parse options object to return a Promise."));if(typeof n>"u"||n===null)return o(new Error("marked(): input parameter is undefined or null"));if(typeof n!="string")return o(new Error("marked(): input parameter is of type "+Object.prototype.toString.call(n)+", string expected"));if(r.hooks&&(r.hooks.options=r,r.hooks.block=t),r.async)return(async()=>{let a=r.hooks?await r.hooks.preprocess(n):n,s=await(r.hooks?await r.hooks.provideLexer():t?nn.lex:nn.lexInline)(a,r),l=r.hooks?await r.hooks.processAllTokens(s):s;r.walkTokens&&await Promise.all(this.walkTokens(l,r.walkTokens));let c=await(r.hooks?await r.hooks.provideParser():t?rn.parse:rn.parseInline)(l,r);return r.hooks?await r.hooks.postprocess(c):c})().catch(o);try{r.hooks&&(n=r.hooks.preprocess(n));let a=(r.hooks?r.hooks.provideLexer():t?nn.lex:nn.lexInline)(n,r);r.hooks&&(a=r.hooks.processAllTokens(a)),r.walkTokens&&this.walkTokens(a,r.walkTokens);let s=(r.hooks?r.hooks.provideParser():t?rn.parse:rn.parseInline)(a,r);return r.hooks&&(s=r.hooks.postprocess(s)),s}catch(a){return o(a)}}}onError(t,n){return e=>{if(e.message+=`
Please report this to https://github.com/markedjs/marked.`,t){let i="<p>An error occurred:</p><pre>"+_n(e.message+"",!0)+"</pre>";return n?Promise.resolve(i):i}if(n)return Promise.reject(e);throw e}}},wi=new Q0;function _e(t,n){return wi.parse(t,n)}_e.options=_e.setOptions=function(t){return wi.setOptions(t),_e.defaults=wi.defaults,Hh(_e.defaults),_e};_e.getDefaults=Qc;_e.defaults=Ci;_e.use=function(...t){return wi.use(...t),_e.defaults=wi.defaults,Hh(_e.defaults),_e};_e.walkTokens=function(t,n){return wi.walkTokens(t,n)};_e.parseInline=wi.parseInline;_e.Parser=rn;_e.parser=rn.parse;_e.Renderer=ri;_e.TextRenderer=rd;_e.Lexer=nn;_e.lexer=nn.lex;_e.Tokenizer=xs;_e.Hooks=Bo;_e.parse=_e;var MP=_e.options,AP=_e.setOptions,IP=_e.use,TP=_e.walkTokens,OP=_e.parseInline;var RP=rn.parse,PP=nn.lex;var X0=["*"],Z0="Copy",J0="Copied",ex=(()=>{class t{constructor(){this._buttonClick$=new z,this.copied=Nh(this._buttonClick$.pipe($t(()=>_t(Pe(!0),Cp(3e3).pipe(co(!1)))),mo(),An(1))),this.copiedText=en(()=>this.copied()?J0:Z0)}onCopyToClipboardClick(){this._buttonClick$.next()}static{this.\u0275fac=function(i){return new(i||t)}}static{this.\u0275cmp=T({type:t,selectors:[["markdown-clipboard"]],decls:2,vars:3,consts:[[1,"markdown-clipboard-button",3,"click"]],template:function(i,r){i&1&&(Ue(0,"button",0),Xa("click",function(){return r.onCopyToClipboardClick()}),w(1),Qe()),i&2&&(Q("copied",r.copied()),h(),Le(r.copiedText()))},encapsulation:2,changeDetection:0})}}return t})(),tx=new A("CLIPBOARD_OPTIONS");var nx=new A("MARKED_EXTENSIONS"),ix=new A("MARKED_OPTIONS"),rx=new A("MERMAID_OPTIONS"),ox=new A("SANITIZE");function ax(t){return typeof t=="function"}var sx="[ngx-markdown] When using the `emoji` attribute you *have to* include Emoji-Toolkit files to `angular.json` or use imports. See README for more information",lx="[ngx-markdown] When using the `katex` attribute you *have to* include KaTeX files to `angular.json` or use imports. See README for more information",cx="[ngx-markdown] When using the `mermaid` attribute you *have to* include Mermaid files to `angular.json` or use imports. See README for more information",dx="[ngx-markdown] When using the `clipboard` attribute you *have to* include Clipboard files to `angular.json` or use imports. See README for more information",mx="[ngx-markdown] When using the `clipboard` attribute you *have to* provide the `viewContainerRef` parameter to `MarkdownService.render()` function",ux="[ngx-markdown] When using the `src` attribute you *have to* pass the `HttpClient` as a parameter of the `forRoot` method. See README for more information";var ef=(()=>{class t{get options(){return this._options}set options(e){this._options=M(M({},this.DEFAULT_MARKED_OPTIONS),e)}get renderer(){return this.options.renderer}set renderer(e){this.options.renderer=e}constructor(){this.clipboardOptions=m(tx,{optional:!0}),this.extensions=m(nx,{optional:!0}),this.http=m(zt,{optional:!0}),this.mermaidOptions=m(rx,{optional:!0}),this.platform=m(Zn),this.sanitize=m(ox,{optional:!0}),this.sanitizer=m(Pn),this.DEFAULT_MARKED_OPTIONS={renderer:new ri},this.DEFAULT_KATEX_OPTIONS={delimiters:[{left:"$$",right:"$$",display:!0},{left:"$",right:"$",display:!1},{left:"\\(",right:"\\)",display:!1},{left:"\\begin{equation}",right:"\\end{equation}",display:!0},{left:"\\begin{align}",right:"\\end{align}",display:!0},{left:"\\begin{alignat}",right:"\\end{alignat}",display:!0},{left:"\\begin{gather}",right:"\\end{gather}",display:!0},{left:"\\begin{CD}",right:"\\end{CD}",display:!0},{left:"\\[",right:"\\]",display:!0}]},this.DEFAULT_MERMAID_OPTIONS={startOnLoad:!1},this.DEFAULT_CLIPBOARD_OPTIONS={buttonComponent:void 0},this.DEFAULT_PARSE_OPTIONS={decodeHtml:!1,inline:!1,emoji:!1,mermaid:!1,markedOptions:void 0,disableSanitizer:!1},this.DEFAULT_RENDER_OPTIONS={clipboard:!1,clipboardOptions:void 0,katex:!1,katexOptions:void 0,mermaid:!1,mermaidOptions:void 0},this.DEFAULT_SECURITY_CONTEXT=tt.HTML,this._options=null,this._reload$=new z,this.reload$=this._reload$.asObservable(),this.options=m(ix,{optional:!0})}parse(e,i=this.DEFAULT_PARSE_OPTIONS){let{decodeHtml:r,inline:o,emoji:a,mermaid:s,disableSanitizer:l}=i,c=M(M({},this.options),i.markedOptions),d=c.renderer||this.renderer||new ri;this.extensions&&(this.renderer=this.extendsRendererForExtensions(d)),s&&(this.renderer=this.extendsRendererForMermaid(d));let u=this.trimIndentation(e),p=r?this.decodeHtml(u):u,y=a?this.parseEmoji(p):p,g=this.parseMarked(y,c,o);return l?g:this.sanitizeHtml(g)}render(e,i=this.DEFAULT_RENDER_OPTIONS,r){let{clipboard:o,clipboardOptions:a,katex:s,katexOptions:l,mermaid:c,mermaidOptions:d}=i;s&&this.renderKatex(e,M(M({},this.DEFAULT_KATEX_OPTIONS),l)),c&&this.renderMermaid(e,M(M(M({},this.DEFAULT_MERMAID_OPTIONS),this.mermaidOptions),d)),o&&this.renderClipboard(e,r,M(M(M({},this.DEFAULT_CLIPBOARD_OPTIONS),this.clipboardOptions),a)),this.highlight(e)}reload(){this._reload$.next()}getSource(e){if(!this.http)throw new Error(ux);return this.http.get(e,{responseType:"text"}).pipe(ye(i=>this.handleExtension(e,i)))}highlight(e){if(!Rn(this.platform)||typeof Prism>"u"||typeof Prism.highlightAllUnder>"u")return;e||(e=document);let i=e.querySelectorAll('pre code:not([class*="language-"])');Array.prototype.forEach.call(i,r=>r.classList.add("language-none")),Prism.highlightAllUnder(e)}decodeHtml(e){if(!Rn(this.platform))return e;let i=document.createElement("textarea");return i.innerHTML=e,i.value}extendsRendererForExtensions(e){let i=e;return i.\u0275NgxMarkdownRendererExtendedForExtensions===!0||(this.extensions&&this.extensions.length>0&&_e.use(...this.extensions),i.\u0275NgxMarkdownRendererExtendedForExtensions=!0),e}extendsRendererForMermaid(e){let i=e;if(i.\u0275NgxMarkdownRendererExtendedForMermaid===!0)return e;let r=e.code;return e.code=o=>o.lang==="mermaid"?`<div class="mermaid">${o.text}</div>`:r(o),i.\u0275NgxMarkdownRendererExtendedForMermaid=!0,e}handleExtension(e,i){let r=e.lastIndexOf("://"),o=r>-1?e.substring(r+4):e,a=o.lastIndexOf("/"),s=a>-1?o.substring(a+1).split("?")[0]:"",l=s.lastIndexOf("."),c=l>-1?s.substring(l+1):"";return c&&c!=="md"?"```"+c+`
`+i+"\n```":i}parseMarked(e,i,r=!1){if(i.renderer){let o=M({},i.renderer);delete o.\u0275NgxMarkdownRendererExtendedForExtensions,delete o.\u0275NgxMarkdownRendererExtendedForMermaid,delete i.renderer,_e.use({renderer:o})}return r?_e.parseInline(e,i):_e.parse(e,i)}parseEmoji(e){if(!Rn(this.platform))return e;if(typeof joypixels>"u"||typeof joypixels.shortnameToUnicode>"u")throw new Error(sx);return joypixels.shortnameToUnicode(e)}renderKatex(e,i){if(Rn(this.platform)){if(typeof katex>"u"||typeof renderMathInElement>"u")throw new Error(lx);renderMathInElement(e,i)}}renderClipboard(e,i,r){if(!Rn(this.platform))return;if(typeof ClipboardJS>"u")throw new Error(dx);if(!i)throw new Error(mx);let{buttonComponent:o,buttonTemplate:a}=r,s=e.querySelectorAll("pre");for(let l=0;l<s.length;l++){let c=s.item(l),d=document.createElement("div");d.style.position="relative",c.parentNode.insertBefore(d,c),d.appendChild(c);let u=document.createElement("div");u.classList.add("markdown-clipboard-toolbar"),u.style.position="absolute",u.style.top=".5em",u.style.right=".5em",u.style.zIndex="1",d.insertAdjacentElement("beforeend",u),d.onmouseenter=()=>u.classList.add("hover"),d.onmouseleave=()=>u.classList.remove("hover");let p;if(o){let g=i.createComponent(o);p=g.hostView,g.changeDetectorRef.markForCheck()}else if(a)p=i.createEmbeddedView(a);else{let g=i.createComponent(ex);p=g.hostView,g.changeDetectorRef.markForCheck()}let y;p.rootNodes.forEach(g=>{u.appendChild(g),y=new ClipboardJS(g,{text:()=>c.innerText})}),p.onDestroy(()=>y.destroy())}}renderMermaid(e,i=this.DEFAULT_MERMAID_OPTIONS){if(!Rn(this.platform))return;if(typeof mermaid>"u"||typeof mermaid.initialize>"u")throw new Error(cx);let r=e.querySelectorAll(".mermaid");r.length!==0&&(mermaid.initialize(i),mermaid.run({nodes:r}))}trimIndentation(e){if(!e)return"";let i;return e.split(`
`).map(r=>{let o=i;return r.length>0&&(o=isNaN(o)?r.search(/\S|$/):Math.min(r.search(/\S|$/),o)),isNaN(i)&&(i=o),o?r.substring(o):r}).join(`
`)}async sanitizeHtml(e){return ax(this.sanitize)?this.sanitize(await e):this.sanitize!==tt.NONE?this.sanitizer.sanitize(this.sanitize??this.DEFAULT_SECURITY_CONTEXT,e)??"":e}static{this.\u0275fac=function(i){return new(i||t)}}static{this.\u0275prov=E({token:t,factory:t.\u0275fac})}}return t})(),od=(function(t){return t.CommandLine="command-line",t.LineHighlight="line-highlight",t.LineNumbers="line-numbers",t})(od||{}),bn=(()=>{class t{constructor(){this.element=m(Y),this.markdownService=m(ef),this.viewContainerRef=m(hn),this.error=new De,this.load=new De,this.ready=new De,this._clipboard=!1,this._commandLine=!1,this._disableSanitizer=!1,this._emoji=!1,this._inline=!1,this._katex=!1,this._lineHighlight=!1,this._lineNumbers=!1,this._mermaid=!1,this.destroyed$=new z}get disableSanitizer(){return this._disableSanitizer}set disableSanitizer(e){this._disableSanitizer=this.coerceBooleanProperty(e)}get inline(){return this._inline}set inline(e){this._inline=this.coerceBooleanProperty(e)}get clipboard(){return this._clipboard}set clipboard(e){this._clipboard=this.coerceBooleanProperty(e)}get emoji(){return this._emoji}set emoji(e){this._emoji=this.coerceBooleanProperty(e)}get katex(){return this._katex}set katex(e){this._katex=this.coerceBooleanProperty(e)}get mermaid(){return this._mermaid}set mermaid(e){this._mermaid=this.coerceBooleanProperty(e)}get lineHighlight(){return this._lineHighlight}set lineHighlight(e){this._lineHighlight=this.coerceBooleanProperty(e)}get lineNumbers(){return this._lineNumbers}set lineNumbers(e){this._lineNumbers=this.coerceBooleanProperty(e)}get commandLine(){return this._commandLine}set commandLine(e){this._commandLine=this.coerceBooleanProperty(e)}ngOnChanges(){this.loadContent()}loadContent(){if(this.data!=null){this.handleData();return}if(this.src!=null){this.handleSrc();return}}ngAfterViewInit(){!this.data&&!this.src&&this.handleTransclusion(),this.markdownService.reload$.pipe(ke(this.destroyed$)).subscribe(()=>this.loadContent())}ngOnDestroy(){this.destroyed$.next(),this.destroyed$.complete()}async render(e,i=!1){let r={decodeHtml:i,inline:this.inline,emoji:this.emoji,mermaid:this.mermaid,disableSanitizer:this.disableSanitizer},o={clipboard:this.clipboard,clipboardOptions:this.getClipboardOptions(),katex:this.katex,katexOptions:this.katexOptions,mermaid:this.mermaid,mermaidOptions:this.mermaidOptions},a=await this.markdownService.parse(e,r);this.element.nativeElement.innerHTML=a,this.handlePlugins(),this.markdownService.render(this.element.nativeElement,o,this.viewContainerRef),this.ready.emit()}coerceBooleanProperty(e){return e!=null&&`${String(e)}`!="false"}getClipboardOptions(){if(this.clipboardButtonComponent||this.clipboardButtonTemplate)return{buttonComponent:this.clipboardButtonComponent,buttonTemplate:this.clipboardButtonTemplate}}handleData(){this.render(this.data)}handleSrc(){this.markdownService.getSource(this.src).subscribe({next:e=>{this.render(e).then(()=>{this.load.emit(e)})},error:e=>this.error.emit(e)})}handleTransclusion(){this.render(this.element.nativeElement.innerHTML,!0)}handlePlugins(){this.commandLine&&(this.setPluginClass(this.element.nativeElement,od.CommandLine),this.setPluginOptions(this.element.nativeElement,{dataFilterOutput:this.filterOutput,dataHost:this.host,dataPrompt:this.prompt,dataOutput:this.output,dataUser:this.user})),this.lineHighlight&&this.setPluginOptions(this.element.nativeElement,{dataLine:this.line,dataLineOffset:this.lineOffset}),this.lineNumbers&&(this.setPluginClass(this.element.nativeElement,od.LineNumbers),this.setPluginOptions(this.element.nativeElement,{dataStart:this.start}))}setPluginClass(e,i){let r=e.querySelectorAll("pre");for(let o=0;o<r.length;o++){let a=i instanceof Array?i:[i];r.item(o).classList.add(...a)}}setPluginOptions(e,i){let r=e.querySelectorAll("pre");for(let o=0;o<r.length;o++)Object.keys(i).forEach(a=>{let s=i[a];if(s){let l=this.toLispCase(a);r.item(o).setAttribute(l,s.toString())}})}toLispCase(e){let i=e.match(/([A-Z])/g);if(!i)return e;let r=e.toString();for(let o=0,a=i.length;o<a;o++)r=r.replace(new RegExp(i[o]),"-"+i[o].toLowerCase());return r.slice(0,1)==="-"&&(r=r.slice(1)),r}static{this.\u0275fac=function(i){return new(i||t)}}static{this.\u0275cmp=T({type:t,selectors:[["markdown"],["","markdown",""]],inputs:{data:"data",src:"src",disableSanitizer:"disableSanitizer",inline:"inline",clipboard:"clipboard",clipboardButtonComponent:"clipboardButtonComponent",clipboardButtonTemplate:"clipboardButtonTemplate",emoji:"emoji",katex:"katex",katexOptions:"katexOptions",mermaid:"mermaid",mermaidOptions:"mermaidOptions",lineHighlight:"lineHighlight",line:"line",lineOffset:"lineOffset",lineNumbers:"lineNumbers",start:"start",commandLine:"commandLine",filterOutput:"filterOutput",host:"host",prompt:"prompt",output:"output",user:"user"},outputs:{error:"error",load:"load",ready:"ready"},features:[dt],ngContentSelectors:X0,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},encapsulation:2})}}return t})();function ad(t){return[ef,t?.loader??[],t?.clipboardOptions??[],t?.markedOptions??[],t?.mermaidOptions??[],t?.markedExtensions??[],t?.sanitize??[]]}var vn=(()=>{class t{static forRoot(e){return{ngModule:t,providers:[ad(e)]}}static forChild(){return{ngModule:t}}static{this.\u0275fac=function(i){return new(i||t)}}static{this.\u0275mod=V({type:t})}static{this.\u0275inj=$({})}}return t})();var Ds=new WeakMap,Je=(()=>{class t{_appRef;_injector=m(te);_environmentInjector=m(In);load(e){let i=this._appRef=this._appRef||this._injector.get(ei),r=Ds.get(i);r||(r={loaders:new Set,refs:[]},Ds.set(i,r),i.onDestroy(()=>{Ds.get(i)?.refs.forEach(o=>o.destroy()),Ds.delete(i)})),r.loaders.has(e)||(r.loaders.add(e),r.refs.push(ns(e,{environmentInjector:this._environmentInjector})))}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var hr=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["ng-component"]],exportAs:["cdkVisuallyHidden"],decls:0,vars:0,template:function(i,r){},styles:[`.cdk-visually-hidden {
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
`],encapsulation:2,changeDetection:0})}return t})(),Es;function px(){if(Es===void 0&&(Es=null,typeof window<"u")){let t=window;t.trustedTypes!==void 0&&(Es=t.trustedTypes.createPolicy("angular#components",{createHTML:n=>n}))}return Es}function ki(t){return px()?.createHTML(t)||t}function tf(t,n,e){let i=e.sanitize(tt.HTML,n);t.innerHTML=ki(i||"")}function nf(t){return Error(`Unable to find icon with the name "${t}"`)}function hx(){return Error("Could not find HttpClient for use with Angular Material icons. Please add provideHttpClient() to your providers.")}function rf(t){return Error(`The URL provided to MatIconRegistry was not trusted as a resource URL via Angular's DomSanitizer. Attempted URL was "${t}".`)}function of(t){return Error(`The literal provided to MatIconRegistry was not trusted as safe HTML by Angular's DomSanitizer. Attempted literal was "${t}".`)}var Fn=class{url;svgText;options;svgElement=null;constructor(n,e,i){this.url=n,this.svgText=e,this.options=i}},Ms=(()=>{class t{_httpClient;_sanitizer;_errorHandler;_document;_svgIconConfigs=new Map;_iconSetConfigs=new Map;_cachedIconsByUrl=new Map;_inProgressUrlFetches=new Map;_fontCssClassesByAlias=new Map;_resolvers=[];_defaultFontSetClass=["material-icons","mat-ligature-font"];constructor(e,i,r,o){this._httpClient=e,this._sanitizer=i,this._errorHandler=o,this._document=r}addSvgIcon(e,i,r){return this.addSvgIconInNamespace("",e,i,r)}addSvgIconLiteral(e,i,r){return this.addSvgIconLiteralInNamespace("",e,i,r)}addSvgIconInNamespace(e,i,r,o){return this._addSvgIconConfig(e,i,new Fn(r,null,o))}addSvgIconResolver(e){return this._resolvers.push(e),this}addSvgIconLiteralInNamespace(e,i,r,o){let a=this._sanitizer.sanitize(tt.HTML,r);if(!a)throw of(r);let s=ki(a);return this._addSvgIconConfig(e,i,new Fn("",s,o))}addSvgIconSet(e,i){return this.addSvgIconSetInNamespace("",e,i)}addSvgIconSetLiteral(e,i){return this.addSvgIconSetLiteralInNamespace("",e,i)}addSvgIconSetInNamespace(e,i,r){return this._addSvgIconSetConfig(e,new Fn(i,null,r))}addSvgIconSetLiteralInNamespace(e,i,r){let o=this._sanitizer.sanitize(tt.HTML,i);if(!o)throw of(i);let a=ki(o);return this._addSvgIconSetConfig(e,new Fn("",a,r))}registerFontClassAlias(e,i=e){return this._fontCssClassesByAlias.set(e,i),this}classNameForFontAlias(e){return this._fontCssClassesByAlias.get(e)||e}setDefaultFontSetClass(...e){return this._defaultFontSetClass=e,this}getDefaultFontSetClass(){return this._defaultFontSetClass}getSvgIconFromUrl(e){let i=this._sanitizer.sanitize(tt.RESOURCE_URL,e);if(!i)throw rf(e);let r=this._cachedIconsByUrl.get(i);return r?Pe(Ss(r)):this._loadSvgIconFromConfig(new Fn(e,null)).pipe(Xn(o=>this._cachedIconsByUrl.set(i,o)),ye(o=>Ss(o)))}getNamedSvgIcon(e,i=""){let r=af(i,e),o=this._svgIconConfigs.get(r);if(o)return this._getSvgFromConfig(o);if(o=this._getIconConfigFromResolvers(i,e),o)return this._svgIconConfigs.set(r,o),this._getSvgFromConfig(o);let a=this._iconSetConfigs.get(i);return a?this._getSvgFromIconSetConfigs(e,a):vp(nf(r))}ngOnDestroy(){this._resolvers=[],this._svgIconConfigs.clear(),this._iconSetConfigs.clear(),this._cachedIconsByUrl.clear()}_getSvgFromConfig(e){return e.svgText?Pe(Ss(this._svgElementFromConfig(e))):this._loadSvgIconFromConfig(e).pipe(ye(i=>Ss(i)))}_getSvgFromIconSetConfigs(e,i){let r=this._extractIconWithNameFromAnySet(e,i);if(r)return Pe(r);let o=i.filter(a=>!a.svgText).map(a=>this._loadSvgIconSetFromConfig(a).pipe(za(s=>{let c=`Loading icon set URL: ${this._sanitizer.sanitize(tt.RESOURCE_URL,a.url)} failed: ${s.message}`;return this._errorHandler.handleError(new Error(c)),Pe(null)})));return wp(o).pipe(ye(()=>{let a=this._extractIconWithNameFromAnySet(e,i);if(!a)throw nf(e);return a}))}_extractIconWithNameFromAnySet(e,i){for(let r=i.length-1;r>=0;r--){let o=i[r];if(o.svgText&&o.svgText.toString().indexOf(e)>-1){let a=this._svgElementFromConfig(o),s=this._extractSvgIconFromSet(a,e,o.options);if(s)return s}}return null}_loadSvgIconFromConfig(e){return this._fetchIcon(e).pipe(Xn(i=>e.svgText=i),ye(()=>this._svgElementFromConfig(e)))}_loadSvgIconSetFromConfig(e){return e.svgText?Pe(null):this._fetchIcon(e).pipe(Xn(i=>e.svgText=i))}_extractSvgIconFromSet(e,i,r){let o=e.querySelector(`[id="${i}"]`);if(!o)return null;let a=o.cloneNode(!0);if(a.removeAttribute("id"),a.nodeName.toLowerCase()==="svg")return this._setSvgAttributes(a,r);if(a.nodeName.toLowerCase()==="symbol")return this._setSvgAttributes(this._toSvgElement(a),r);let s=this._svgElementFromString(ki("<svg></svg>"));return s.appendChild(a),this._setSvgAttributes(s,r)}_svgElementFromString(e){let i=this._document.createElement("DIV");i.innerHTML=e;let r=i.querySelector("svg");if(!r)throw Error("<svg> tag not found");return r}_toSvgElement(e){let i=this._svgElementFromString(ki("<svg></svg>")),r=e.attributes;for(let o=0;o<r.length;o++){let{name:a,value:s}=r[o];a!=="id"&&i.setAttribute(a,s)}for(let o=0;o<e.childNodes.length;o++)e.childNodes[o].nodeType===this._document.ELEMENT_NODE&&i.appendChild(e.childNodes[o].cloneNode(!0));return i}_setSvgAttributes(e,i){return e.setAttribute("fit",""),e.setAttribute("height","100%"),e.setAttribute("width","100%"),e.setAttribute("preserveAspectRatio","xMidYMid meet"),e.setAttribute("focusable","false"),i&&i.viewBox&&e.setAttribute("viewBox",i.viewBox),e}_fetchIcon(e){let{url:i,options:r}=e,o=r?.withCredentials??!1;if(!this._httpClient)throw hx();if(i==null)throw Error(`Cannot fetch icon from URL "${i}".`);let a=this._sanitizer.sanitize(tt.RESOURCE_URL,i);if(!a)throw rf(i);let s=this._inProgressUrlFetches.get(a);if(s)return s;let l=this._httpClient.get(a,{responseType:"text",withCredentials:o}).pipe(ye(c=>ki(c)),uo(()=>this._inProgressUrlFetches.delete(a)),Ua());return this._inProgressUrlFetches.set(a,l),l}_addSvgIconConfig(e,i,r){return this._svgIconConfigs.set(af(e,i),r),this}_addSvgIconSetConfig(e,i){let r=this._iconSetConfigs.get(e);return r?r.push(i):this._iconSetConfigs.set(e,[i]),this}_svgElementFromConfig(e){if(!e.svgElement){let i=this._svgElementFromString(e.svgText);this._setSvgAttributes(i,e.options),e.svgElement=i}return e.svgElement}_getIconConfigFromResolvers(e,i){for(let r=0;r<this._resolvers.length;r++){let o=this._resolvers[r](i,e);if(o)return fx(o)?new Fn(o.url,null,o.options):new Fn(o,null)}}static \u0275fac=function(i){return new(i||t)(j(zt,8),j(Pn),j(W,8),j(bi))};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();function Ss(t){return t.cloneNode(!0)}function af(t,n){return t+":"+n}function fx(t){return!!(t.url&&t.options)}var gx=new A("cdk-dir-doc",{providedIn:"root",factory:()=>m(W)}),_x=/^(ar|ckb|dv|he|iw|fa|nqo|ps|sd|ug|ur|yi|.*[-_](Adlm|Arab|Hebr|Nkoo|Rohg|Thaa))(?!.*[-_](Latn|Cyrl)($|-|_))($|-|_)/i;function sf(t){let n=t?.toLowerCase()||"";return n==="auto"&&typeof navigator<"u"&&navigator?.language?_x.test(navigator.language)?"rtl":"ltr":n==="rtl"?"rtl":"ltr"}var wt=(()=>{class t{get value(){return this.valueSignal()}valueSignal=X("ltr");change=new De;constructor(){let e=m(gx,{optional:!0});if(e){let i=e.body?e.body.dir:null,r=e.documentElement?e.documentElement.dir:null;this.valueSignal.set(sf(i||r||"ltr"))}}ngOnDestroy(){this.change.complete()}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var ie=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({})}return t})();var bx=["*"],vx=new A("MAT_ICON_DEFAULT_OPTIONS"),yx=new A("mat-icon-location",{providedIn:"root",factory:()=>{let t=m(W),n=t?t.location:null;return{getPathname:()=>n?n.pathname+n.search:""}}}),lf=["clip-path","color-profile","src","cursor","fill","filter","marker","marker-start","marker-mid","marker-end","mask","stroke"],xx=lf.map(t=>`[${t}]`).join(", "),wx=/^url\(['"]?#(.*?)['"]?\)$/,Mt=(()=>{class t{_elementRef=m(Y);_iconRegistry=m(Ms);_location=m(yx);_errorHandler=m(bi);_defaultColor;get color(){return this._color||this._defaultColor}set color(e){this._color=e}_color;inline=!1;get svgIcon(){return this._svgIcon}set svgIcon(e){e!==this._svgIcon&&(e?this._updateSvgIcon(e):this._svgIcon&&this._clearSvgElement(),this._svgIcon=e)}_svgIcon;get fontSet(){return this._fontSet}set fontSet(e){let i=this._cleanupFontValue(e);i!==this._fontSet&&(this._fontSet=i,this._updateFontIconClasses())}_fontSet;get fontIcon(){return this._fontIcon}set fontIcon(e){let i=this._cleanupFontValue(e);i!==this._fontIcon&&(this._fontIcon=i,this._updateFontIconClasses())}_fontIcon;_previousFontSetClass=[];_previousFontIconClass;_svgName=null;_svgNamespace=null;_previousPath;_elementsWithExternalReferences;_currentIconFetch=lt.EMPTY;constructor(){let e=m(new Hp("aria-hidden"),{optional:!0}),i=m(vx,{optional:!0});i&&(i.color&&(this.color=this._defaultColor=i.color),i.fontSet&&(this.fontSet=i.fontSet)),e||this._elementRef.nativeElement.setAttribute("aria-hidden","true")}_splitIconName(e){if(!e)return["",""];let i=e.split(":");switch(i.length){case 1:return["",i[0]];case 2:return i;default:throw Error(`Invalid icon name: "${e}"`)}}ngOnInit(){this._updateFontIconClasses()}ngAfterViewChecked(){let e=this._elementsWithExternalReferences;if(e&&e.size){let i=this._location.getPathname();i!==this._previousPath&&(this._previousPath=i,this._prependPathToReferences(i))}}ngOnDestroy(){this._currentIconFetch.unsubscribe(),this._elementsWithExternalReferences&&this._elementsWithExternalReferences.clear()}_usingFontIcon(){return!this.svgIcon}_setSvgElement(e){this._clearSvgElement();let i=this._location.getPathname();this._previousPath=i,this._cacheChildrenWithExternalReferences(e),this._prependPathToReferences(i),this._elementRef.nativeElement.appendChild(e)}_clearSvgElement(){let e=this._elementRef.nativeElement,i=e.childNodes.length;for(this._elementsWithExternalReferences&&this._elementsWithExternalReferences.clear();i--;){let r=e.childNodes[i];(r.nodeType!==1||r.nodeName.toLowerCase()==="svg")&&r.remove()}}_updateFontIconClasses(){if(!this._usingFontIcon())return;let e=this._elementRef.nativeElement,i=(this.fontSet?this._iconRegistry.classNameForFontAlias(this.fontSet).split(/ +/):this._iconRegistry.getDefaultFontSetClass()).filter(r=>r.length>0);this._previousFontSetClass.forEach(r=>e.classList.remove(r)),i.forEach(r=>e.classList.add(r)),this._previousFontSetClass=i,this.fontIcon!==this._previousFontIconClass&&!i.includes("mat-ligature-font")&&(this._previousFontIconClass&&e.classList.remove(this._previousFontIconClass),this.fontIcon&&e.classList.add(this.fontIcon),this._previousFontIconClass=this.fontIcon)}_cleanupFontValue(e){return typeof e=="string"?e.trim().split(" ")[0]:e}_prependPathToReferences(e){let i=this._elementsWithExternalReferences;i&&i.forEach((r,o)=>{r.forEach(a=>{o.setAttribute(a.name,`url('${e}#${a.value}')`)})})}_cacheChildrenWithExternalReferences(e){let i=e.querySelectorAll(xx),r=this._elementsWithExternalReferences=this._elementsWithExternalReferences||new Map;for(let o=0;o<i.length;o++)lf.forEach(a=>{let s=i[o],l=s.getAttribute(a),c=l?l.match(wx):null;if(c){let d=r.get(s);d||(d=[],r.set(s,d)),d.push({name:a,value:c[1]})}})}_updateSvgIcon(e){if(this._svgNamespace=null,this._svgName=null,this._currentIconFetch.unsubscribe(),e){let[i,r]=this._splitIconName(e);i&&(this._svgNamespace=i),r&&(this._svgName=r),this._currentIconFetch=this._iconRegistry.getNamedSvgIcon(r,i).pipe(Qn(1)).subscribe(o=>this._setSvgElement(o),o=>{let a=`Error retrieving icon ${i}:${r}! ${o.message}`;this._errorHandler.handleError(new Error(a))})}}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-icon"]],hostAttrs:["role","img",1,"mat-icon","notranslate"],hostVars:10,hostBindings:function(i,r){i&2&&(le("data-mat-icon-type",r._usingFontIcon()?"font":"svg")("data-mat-icon-name",r._svgName||r.fontIcon)("data-mat-icon-namespace",r._svgNamespace||r.fontSet)("fontIcon",r._usingFontIcon()?r.fontIcon:null),vt(r.color?"mat-"+r.color:""),Q("mat-icon-inline",r.inline)("mat-icon-no-color",r.color!=="primary"&&r.color!=="accent"&&r.color!=="warn"))},inputs:{color:"color",inline:[2,"inline","inline",Se],svgIcon:"svgIcon",fontSet:"fontSet",fontIcon:"fontIcon"},exportAs:["matIcon"],ngContentSelectors:bx,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},styles:[`mat-icon, mat-icon.mat-primary, mat-icon.mat-accent, mat-icon.mat-warn {
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
`],encapsulation:2,changeDetection:0})}return t})(),Ct=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[ie]})}return t})();var fr=class{},As=class t{constructor(n,e){this.iconRegistry=n;this.sanitizer=e}load(){}static \u0275fac=function(e){return new(e||t)(j(Ms),j(Pn))};static \u0275prov=E({token:t,factory:t.\u0275fac})};var yn=class t{static contextPath=t.getContextPath();static getContextPath(){return document.location.pathname.split("/asyncapi-ui.html")[0]}static uiConfig=t.contextPath+"/ui-config";static docs=t.contextPath+"/docs";static getDocsForGroupEndpoint(n){return t.docs+`/${n}`}static getPublishEndpoint(n){return t.contextPath+`/plugin/${n}/publish`}};var xe=class{static DEFAULT_SHOW_BINDINGS=!0;static DEFAULT_SHOW_HEADERS=!0;static DEFAULT_GROUP="default"},Is=class t extends xe{constructor(e){super();this.http=e;this.uiConfig=this.http.get(yn.uiConfig).pipe(za(()=>Pe(this.fallbackConfig)),An()),this.uiConfig.subscribe(i=>{this.toggleIsShowBindings(i.initialConfig.showBindings),this.toggleIsShowHeaders(i.initialConfig.showHeaders)})}_getGroup=new Kn(xe.DEFAULT_GROUP);isGroup$=this._getGroup.asObservable();fallbackConfig={initialConfig:{showBindings:xe.DEFAULT_SHOW_BINDINGS,showHeaders:xe.DEFAULT_SHOW_HEADERS},groups:[]};_isShowBindings=new Kn(xe.DEFAULT_SHOW_BINDINGS);isShowBindings$=this._isShowBindings.asObservable();_isShowHeaders=new Kn(xe.DEFAULT_SHOW_HEADERS);isShowHeaders$=this._isShowHeaders.asObservable();uiConfig;toggleIsShowBindings(e){this._isShowBindings.next(e)}toggleIsShowHeaders(e){this._isShowHeaders.next(e)}changeGroup(e){this._getGroup.next(e)}static \u0275fac=function(i){return new(i||t)(j(zt))};static \u0275prov=E({token:t,factory:t.\u0275fac})};var Vo="server-";var sd="channel-";var Ht=class{value;rawValue;lineCount;constructor(n){this.rawValue=n,typeof n=="object"?Object.keys(n).length>0?this.value=JSON.stringify(n,null,2):this.value="":this.value=""+n,this.lineCount=this.value.split(`
`).length}};var Os=class extends Error{};function ld(t,n){try{return n()}catch(e){throw e instanceof Error?new Os(t+" ("+e.message+")"):new Os(t+" ("+e+")")}}function cf(t,n=()=>{}){try{return t()}catch(e){n!==void 0&&n(e);return}}function Ei(t){return t.buttons===0||t.detail===0}function Si(t){let n=t.touches&&t.touches[0]||t.changedTouches&&t.changedTouches[0];return!!n&&n.identifier===-1&&(n.radiusX==null||n.radiusX===1)&&(n.radiusY==null||n.radiusY===1)}var cd;function df(){if(cd==null){let t=typeof document<"u"?document.head:null;cd=!!(t&&(t.createShadowRoot||t.attachShadow))}return cd}function dd(t){if(df()){let n=t.getRootNode?t.getRootNode():null;if(typeof ShadowRoot<"u"&&ShadowRoot&&n instanceof ShadowRoot)return n}return null}function At(t){return t.composedPath?t.composedPath()[0]:t.target}var md;try{md=typeof Intl<"u"&&Intl.v8BreakIterator}catch{md=!1}var we=(()=>{class t{_platformId=m(Zn);isBrowser=this._platformId?Rn(this._platformId):typeof document=="object"&&!!document;EDGE=this.isBrowser&&/(edge)/i.test(navigator.userAgent);TRIDENT=this.isBrowser&&/(msie|trident)/i.test(navigator.userAgent);BLINK=this.isBrowser&&!!(window.chrome||md)&&typeof CSS<"u"&&!this.EDGE&&!this.TRIDENT;WEBKIT=this.isBrowser&&/AppleWebKit/i.test(navigator.userAgent)&&!this.BLINK&&!this.EDGE&&!this.TRIDENT;IOS=this.isBrowser&&/iPad|iPhone|iPod/.test(navigator.userAgent)&&!("MSStream"in window);FIREFOX=this.isBrowser&&/(firefox|minefield)/i.test(navigator.userAgent);ANDROID=this.isBrowser&&/android/i.test(navigator.userAgent)&&!this.TRIDENT;SAFARI=this.isBrowser&&/safari/i.test(navigator.userAgent)&&this.WEBKIT;constructor(){}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var zo;function mf(){if(zo==null&&typeof window<"u")try{window.addEventListener("test",null,Object.defineProperty({},"passive",{get:()=>zo=!0}))}finally{zo=zo||!1}return zo}function gr(t){return mf()?t:!!t.capture}function Nn(t,n=0){return uf(t)?Number(t):arguments.length===2?n:0}function uf(t){return!isNaN(parseFloat(t))&&!isNaN(Number(t))}function Ut(t){return t instanceof Y?t.nativeElement:t}var pf=new A("cdk-input-modality-detector-options"),hf={ignoreKeys:[18,17,224,91,16]},ff=650,ud={passive:!0,capture:!0},gf=(()=>{class t{_platform=m(we);_listenerCleanups;modalityDetected;modalityChanged;get mostRecentModality(){return this._modality.value}_mostRecentTarget=null;_modality=new Kn(null);_options;_lastTouchMs=0;_onKeydown=e=>{this._options?.ignoreKeys?.some(i=>i===e.keyCode)||(this._modality.next("keyboard"),this._mostRecentTarget=At(e))};_onMousedown=e=>{Date.now()-this._lastTouchMs<ff||(this._modality.next(Ei(e)?"keyboard":"mouse"),this._mostRecentTarget=At(e))};_onTouchstart=e=>{if(Si(e)){this._modality.next("keyboard");return}this._lastTouchMs=Date.now(),this._modality.next("touch"),this._mostRecentTarget=At(e)};constructor(){let e=m(K),i=m(W),r=m(pf,{optional:!0});if(this._options=M(M({},hf),r),this.modalityDetected=this._modality.pipe(qa(1)),this.modalityChanged=this.modalityDetected.pipe(mo()),this._platform.isBrowser){let o=m(bt).createRenderer(null,null);this._listenerCleanups=e.runOutsideAngular(()=>[o.listen(i,"keydown",this._onKeydown,ud),o.listen(i,"mousedown",this._onMousedown,ud),o.listen(i,"touchstart",this._onTouchstart,ud)])}}ngOnDestroy(){this._modality.complete(),this._listenerCleanups?.forEach(e=>e())}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),Ho=(function(t){return t[t.IMMEDIATE=0]="IMMEDIATE",t[t.EVENTUAL=1]="EVENTUAL",t})(Ho||{}),_f=new A("cdk-focus-monitor-default-options"),Rs=gr({passive:!0,capture:!0}),xn=(()=>{class t{_ngZone=m(K);_platform=m(we);_inputModalityDetector=m(gf);_origin=null;_lastFocusOrigin=null;_windowFocused=!1;_windowFocusTimeoutId;_originTimeoutId;_originFromTouchInteraction=!1;_elementInfo=new Map;_monitoredElementCount=0;_rootNodeFocusListenerCount=new Map;_detectionMode;_windowFocusListener=()=>{this._windowFocused=!0,this._windowFocusTimeoutId=setTimeout(()=>this._windowFocused=!1)};_document=m(W);_stopInputModalityDetector=new z;constructor(){let e=m(_f,{optional:!0});this._detectionMode=e?.detectionMode||Ho.IMMEDIATE}_rootNodeFocusAndBlurListener=e=>{let i=At(e);for(let r=i;r;r=r.parentElement)e.type==="focus"?this._onFocus(e,r):this._onBlur(e,r)};monitor(e,i=!1){let r=Ut(e);if(!this._platform.isBrowser||r.nodeType!==1)return Pe();let o=dd(r)||this._document,a=this._elementInfo.get(r);if(a)return i&&(a.checkChildren=!0),a.subject;let s={checkChildren:i,subject:new z,rootNode:o};return this._elementInfo.set(r,s),this._registerGlobalListeners(s),s.subject}stopMonitoring(e){let i=Ut(e),r=this._elementInfo.get(i);r&&(r.subject.complete(),this._setClasses(i),this._elementInfo.delete(i),this._removeGlobalListeners(r))}focusVia(e,i,r){let o=Ut(e),a=this._document.activeElement;o===a?this._getClosestElementsInfo(o).forEach(([s,l])=>this._originChanged(s,i,l)):(this._setOrigin(i),typeof o.focus=="function"&&o.focus(r))}ngOnDestroy(){this._elementInfo.forEach((e,i)=>this.stopMonitoring(i))}_getWindow(){return this._document.defaultView||window}_getFocusOrigin(e){return this._origin?this._originFromTouchInteraction?this._shouldBeAttributedToTouch(e)?"touch":"program":this._origin:this._windowFocused&&this._lastFocusOrigin?this._lastFocusOrigin:e&&this._isLastInteractionFromInputLabel(e)?"mouse":"program"}_shouldBeAttributedToTouch(e){return this._detectionMode===Ho.EVENTUAL||!!e?.contains(this._inputModalityDetector._mostRecentTarget)}_setClasses(e,i){e.classList.toggle("cdk-focused",!!i),e.classList.toggle("cdk-touch-focused",i==="touch"),e.classList.toggle("cdk-keyboard-focused",i==="keyboard"),e.classList.toggle("cdk-mouse-focused",i==="mouse"),e.classList.toggle("cdk-program-focused",i==="program")}_setOrigin(e,i=!1){this._ngZone.runOutsideAngular(()=>{if(this._origin=e,this._originFromTouchInteraction=e==="touch"&&i,this._detectionMode===Ho.IMMEDIATE){clearTimeout(this._originTimeoutId);let r=this._originFromTouchInteraction?ff:1;this._originTimeoutId=setTimeout(()=>this._origin=null,r)}})}_onFocus(e,i){let r=this._elementInfo.get(i),o=At(e);!r||!r.checkChildren&&i!==o||this._originChanged(i,this._getFocusOrigin(o),r)}_onBlur(e,i){let r=this._elementInfo.get(i);!r||r.checkChildren&&e.relatedTarget instanceof Node&&i.contains(e.relatedTarget)||(this._setClasses(i),this._emitOrigin(r,null))}_emitOrigin(e,i){e.subject.observers.length&&this._ngZone.run(()=>e.subject.next(i))}_registerGlobalListeners(e){if(!this._platform.isBrowser)return;let i=e.rootNode,r=this._rootNodeFocusListenerCount.get(i)||0;r||this._ngZone.runOutsideAngular(()=>{i.addEventListener("focus",this._rootNodeFocusAndBlurListener,Rs),i.addEventListener("blur",this._rootNodeFocusAndBlurListener,Rs)}),this._rootNodeFocusListenerCount.set(i,r+1),++this._monitoredElementCount===1&&(this._ngZone.runOutsideAngular(()=>{this._getWindow().addEventListener("focus",this._windowFocusListener)}),this._inputModalityDetector.modalityDetected.pipe(ke(this._stopInputModalityDetector)).subscribe(o=>{this._setOrigin(o,!0)}))}_removeGlobalListeners(e){let i=e.rootNode;if(this._rootNodeFocusListenerCount.has(i)){let r=this._rootNodeFocusListenerCount.get(i);r>1?this._rootNodeFocusListenerCount.set(i,r-1):(i.removeEventListener("focus",this._rootNodeFocusAndBlurListener,Rs),i.removeEventListener("blur",this._rootNodeFocusAndBlurListener,Rs),this._rootNodeFocusListenerCount.delete(i))}--this._monitoredElementCount||(this._getWindow().removeEventListener("focus",this._windowFocusListener),this._stopInputModalityDetector.next(),clearTimeout(this._windowFocusTimeoutId),clearTimeout(this._originTimeoutId))}_originChanged(e,i,r){this._setClasses(e,i),this._emitOrigin(r,i),this._lastFocusOrigin=i}_getClosestElementsInfo(e){let i=[];return this._elementInfo.forEach((r,o)=>{(o===e||r.checkChildren&&o.contains(e))&&i.push([o,r])}),i}_isLastInteractionFromInputLabel(e){let{_mostRecentTarget:i,mostRecentModality:r}=this._inputModalityDetector;if(r!=="mouse"||!i||i===e||e.nodeName!=="INPUT"&&e.nodeName!=="TEXTAREA"||e.disabled)return!1;let o=e.labels;if(o){for(let a=0;a<o.length;a++)if(o[a].contains(i))return!0}return!1}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();function _r(t){return Array.isArray(t)?t:[t]}var bf=new Set,Mi,Ps=(()=>{class t{_platform=m(we);_nonce=m(nr,{optional:!0});_matchMedia;constructor(){this._matchMedia=this._platform.isBrowser&&window.matchMedia?window.matchMedia.bind(window):Dx}matchMedia(e){return(this._platform.WEBKIT||this._platform.BLINK)&&kx(e,this._nonce),this._matchMedia(e)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();function kx(t,n){if(!bf.has(t))try{Mi||(Mi=document.createElement("style"),n&&Mi.setAttribute("nonce",n),Mi.setAttribute("type","text/css"),document.head.appendChild(Mi)),Mi.sheet&&(Mi.sheet.insertRule(`@media ${t} {body{ }}`,0),bf.add(t))}catch(e){console.error(e)}}function Dx(t){return{matches:t==="all"||t==="",media:t,addListener:()=>{},removeListener:()=>{}}}var pd=(()=>{class t{_mediaMatcher=m(Ps);_zone=m(K);_queries=new Map;_destroySubject=new z;constructor(){}ngOnDestroy(){this._destroySubject.next(),this._destroySubject.complete()}isMatched(e){return vf(_r(e)).some(r=>this._registerQuery(r).mql.matches)}observe(e){let r=vf(_r(e)).map(a=>this._registerQuery(a).observable),o=yp(r);return o=xp(o.pipe(Qn(1)),o.pipe(qa(1),Yn(0))),o.pipe(ye(a=>{let s={matches:!1,breakpoints:{}};return a.forEach(({matches:l,query:c})=>{s.matches=s.matches||l,s.breakpoints[c]=l}),s}))}_registerQuery(e){if(this._queries.has(e))return this._queries.get(e);let i=this._mediaMatcher.matchMedia(e),o={observable:new St(a=>{let s=l=>this._zone.run(()=>a.next(l));return i.addListener(s),()=>{i.removeListener(s)}}).pipe(ct(i),ye(({matches:a})=>({query:e,matches:a})),ke(this._destroySubject)),mql:i};return this._queries.set(e,o),o}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();function vf(t){return t.map(n=>n.split(",")).reduce((n,e)=>n.concat(e)).map(n=>n.trim())}function Ex(t){if(t.type==="characterData"&&t.target instanceof Comment)return!0;if(t.type==="childList"){for(let n=0;n<t.addedNodes.length;n++)if(!(t.addedNodes[n]instanceof Comment))return!1;for(let n=0;n<t.removedNodes.length;n++)if(!(t.removedNodes[n]instanceof Comment))return!1;return!0}return!1}var yf=(()=>{class t{create(e){return typeof MutationObserver>"u"?null:new MutationObserver(e)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),Sx=(()=>{class t{_mutationObserverFactory=m(yf);_observedElements=new Map;_ngZone=m(K);constructor(){}ngOnDestroy(){this._observedElements.forEach((e,i)=>this._cleanupObserver(i))}observe(e){let i=Ut(e);return new St(r=>{let a=this._observeElement(i).pipe(ye(s=>s.filter(l=>!Ex(l))),Be(s=>!!s.length)).subscribe(s=>{this._ngZone.run(()=>{r.next(s)})});return()=>{a.unsubscribe(),this._unobserveElement(i)}})}_observeElement(e){return this._ngZone.runOutsideAngular(()=>{if(this._observedElements.has(e))this._observedElements.get(e).count++;else{let i=new z,r=this._mutationObserverFactory.create(o=>i.next(o));r&&r.observe(e,{characterData:!0,childList:!0,subtree:!0}),this._observedElements.set(e,{observer:r,stream:i,count:1})}return this._observedElements.get(e).stream})}_unobserveElement(e){this._observedElements.has(e)&&(this._observedElements.get(e).count--,this._observedElements.get(e).count||this._cleanupObserver(e))}_cleanupObserver(e){if(this._observedElements.has(e)){let{observer:i,stream:r}=this._observedElements.get(e);i&&i.disconnect(),r.complete(),this._observedElements.delete(e)}}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),xf=(()=>{class t{_contentObserver=m(Sx);_elementRef=m(Y);event=new De;get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._disabled?this._unsubscribe():this._subscribe()}_disabled=!1;get debounce(){return this._debounce}set debounce(e){this._debounce=Nn(e),this._subscribe()}_debounce;_currentSubscription=null;constructor(){}ngAfterContentInit(){!this._currentSubscription&&!this.disabled&&this._subscribe()}ngOnDestroy(){this._unsubscribe()}_subscribe(){this._unsubscribe();let e=this._contentObserver.observe(this._elementRef);this._currentSubscription=(this.debounce?e.pipe(Yn(this.debounce)):e).subscribe(this.event)}_unsubscribe(){this._currentSubscription?.unsubscribe()}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","cdkObserveContent",""]],inputs:{disabled:[2,"cdkObserveContentDisabled","disabled",Se],debounce:"debounce"},outputs:{event:"cdkObserveContent"},exportAs:["cdkObserveContent"]})}return t})(),Fs=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({providers:[yf]})}return t})();var Ls=(()=>{class t{_platform=m(we);constructor(){}isDisabled(e){return e.hasAttribute("disabled")}isVisible(e){return Ax(e)&&getComputedStyle(e).visibility==="visible"}isTabbable(e){if(!this._platform.isBrowser)return!1;let i=Mx(Lx(e));if(i&&(wf(i)===-1||!this.isVisible(i)))return!1;let r=e.nodeName.toLowerCase(),o=wf(e);return e.hasAttribute("contenteditable")?o!==-1:r==="iframe"||r==="object"||this._platform.WEBKIT&&this._platform.IOS&&!Fx(e)?!1:r==="audio"?e.hasAttribute("controls")?o!==-1:!1:r==="video"?o===-1?!1:o!==null?!0:this._platform.FIREFOX||e.hasAttribute("controls"):e.tabIndex>=0}isFocusable(e,i){return Nx(e)&&!this.isDisabled(e)&&(i?.ignoreVisibility||this.isVisible(e))}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();function Mx(t){try{return t.frameElement}catch{return null}}function Ax(t){return!!(t.offsetWidth||t.offsetHeight||typeof t.getClientRects=="function"&&t.getClientRects().length)}function Ix(t){let n=t.nodeName.toLowerCase();return n==="input"||n==="select"||n==="button"||n==="textarea"}function Tx(t){return Rx(t)&&t.type=="hidden"}function Ox(t){return Px(t)&&t.hasAttribute("href")}function Rx(t){return t.nodeName.toLowerCase()=="input"}function Px(t){return t.nodeName.toLowerCase()=="a"}function Cf(t){if(!t.hasAttribute("tabindex")||t.tabIndex===void 0)return!1;let n=t.getAttribute("tabindex");return!!(n&&!isNaN(parseInt(n,10)))}function wf(t){if(!Cf(t))return null;let n=parseInt(t.getAttribute("tabindex")||"",10);return isNaN(n)?-1:n}function Fx(t){let n=t.nodeName.toLowerCase(),e=n==="input"&&t.type;return e==="text"||e==="password"||n==="select"||n==="textarea"}function Nx(t){return Tx(t)?!1:Ix(t)||Ox(t)||t.hasAttribute("contenteditable")||Cf(t)}function Lx(t){return t.ownerDocument&&t.ownerDocument.defaultView||window}var Ns=class{_element;_checker;_ngZone;_document;_injector;_startAnchor=null;_endAnchor=null;_hasAttached=!1;startAnchorListener=()=>this.focusLastTabbableElement();endAnchorListener=()=>this.focusFirstTabbableElement();get enabled(){return this._enabled}set enabled(n){this._enabled=n,this._startAnchor&&this._endAnchor&&(this._toggleAnchorTabIndex(n,this._startAnchor),this._toggleAnchorTabIndex(n,this._endAnchor))}_enabled=!0;constructor(n,e,i,r,o=!1,a){this._element=n,this._checker=e,this._ngZone=i,this._document=r,this._injector=a,o||this.attachAnchors()}destroy(){let n=this._startAnchor,e=this._endAnchor;n&&(n.removeEventListener("focus",this.startAnchorListener),n.remove()),e&&(e.removeEventListener("focus",this.endAnchorListener),e.remove()),this._startAnchor=this._endAnchor=null,this._hasAttached=!1}attachAnchors(){return this._hasAttached?!0:(this._ngZone.runOutsideAngular(()=>{this._startAnchor||(this._startAnchor=this._createAnchor(),this._startAnchor.addEventListener("focus",this.startAnchorListener)),this._endAnchor||(this._endAnchor=this._createAnchor(),this._endAnchor.addEventListener("focus",this.endAnchorListener))}),this._element.parentNode&&(this._element.parentNode.insertBefore(this._startAnchor,this._element),this._element.parentNode.insertBefore(this._endAnchor,this._element.nextSibling),this._hasAttached=!0),this._hasAttached)}focusInitialElementWhenReady(n){return new Promise(e=>{this._executeOnStable(()=>e(this.focusInitialElement(n)))})}focusFirstTabbableElementWhenReady(n){return new Promise(e=>{this._executeOnStable(()=>e(this.focusFirstTabbableElement(n)))})}focusLastTabbableElementWhenReady(n){return new Promise(e=>{this._executeOnStable(()=>e(this.focusLastTabbableElement(n)))})}_getRegionBoundary(n){let e=this._element.querySelectorAll(`[cdk-focus-region-${n}], [cdkFocusRegion${n}], [cdk-focus-${n}]`);return n=="start"?e.length?e[0]:this._getFirstTabbableElement(this._element):e.length?e[e.length-1]:this._getLastTabbableElement(this._element)}focusInitialElement(n){let e=this._element.querySelector("[cdk-focus-initial], [cdkFocusInitial]");if(e){if(!this._checker.isFocusable(e)){let i=this._getFirstTabbableElement(e);return i?.focus(n),!!i}return e.focus(n),!0}return this.focusFirstTabbableElement(n)}focusFirstTabbableElement(n){let e=this._getRegionBoundary("start");return e&&e.focus(n),!!e}focusLastTabbableElement(n){let e=this._getRegionBoundary("end");return e&&e.focus(n),!!e}hasAttached(){return this._hasAttached}_getFirstTabbableElement(n){if(this._checker.isFocusable(n)&&this._checker.isTabbable(n))return n;let e=n.children;for(let i=0;i<e.length;i++){let r=e[i].nodeType===this._document.ELEMENT_NODE?this._getFirstTabbableElement(e[i]):null;if(r)return r}return null}_getLastTabbableElement(n){if(this._checker.isFocusable(n)&&this._checker.isTabbable(n))return n;let e=n.children;for(let i=e.length-1;i>=0;i--){let r=e[i].nodeType===this._document.ELEMENT_NODE?this._getLastTabbableElement(e[i]):null;if(r)return r}return null}_createAnchor(){let n=this._document.createElement("div");return this._toggleAnchorTabIndex(this._enabled,n),n.classList.add("cdk-visually-hidden"),n.classList.add("cdk-focus-trap-anchor"),n.setAttribute("aria-hidden","true"),n}_toggleAnchorTabIndex(n,e){n?e.setAttribute("tabindex","0"):e.removeAttribute("tabindex")}toggleAnchors(n){this._startAnchor&&this._endAnchor&&(this._toggleAnchorTabIndex(n,this._startAnchor),this._toggleAnchorTabIndex(n,this._endAnchor))}_executeOnStable(n){this._injector?mt(n,{injector:this._injector}):setTimeout(n)}},hd=(()=>{class t{_checker=m(Ls);_ngZone=m(K);_document=m(W);_injector=m(te);constructor(){m(Je).load(hr)}create(e,i=!1){return new Ns(e,this._checker,this._ngZone,this._document,i,this._injector)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var kf=new A("liveAnnouncerElement",{providedIn:"root",factory:()=>null}),Df=new A("LIVE_ANNOUNCER_DEFAULT_OPTIONS"),Bx=0,fd=(()=>{class t{_ngZone=m(K);_defaultOptions=m(Df,{optional:!0});_liveElement;_document=m(W);_sanitizer=m(Pn);_previousTimeout;_currentPromise;_currentResolve;constructor(){let e=m(kf,{optional:!0});this._liveElement=e||this._createLiveElement()}announce(e,...i){let r=this._defaultOptions,o,a;return i.length===1&&typeof i[0]=="number"?a=i[0]:[o,a]=i,this.clear(),clearTimeout(this._previousTimeout),o||(o=r&&r.politeness?r.politeness:"polite"),a==null&&r&&(a=r.duration),this._liveElement.setAttribute("aria-live",o),this._liveElement.id&&this._exposeAnnouncerToModals(this._liveElement.id),this._ngZone.runOutsideAngular(()=>(this._currentPromise||(this._currentPromise=new Promise(s=>this._currentResolve=s)),clearTimeout(this._previousTimeout),this._previousTimeout=setTimeout(()=>{!e||typeof e=="string"?this._liveElement.textContent=e:tf(this._liveElement,e,this._sanitizer),typeof a=="number"&&(this._previousTimeout=setTimeout(()=>this.clear(),a)),this._currentResolve?.(),this._currentPromise=this._currentResolve=void 0},100),this._currentPromise))}clear(){this._liveElement&&(this._liveElement.textContent="")}ngOnDestroy(){clearTimeout(this._previousTimeout),this._liveElement?.remove(),this._liveElement=null,this._currentResolve?.(),this._currentPromise=this._currentResolve=void 0}_createLiveElement(){let e="cdk-live-announcer-element",i=this._document.getElementsByClassName(e),r=this._document.createElement("div");for(let o=0;o<i.length;o++)i[o].remove();return r.classList.add(e),r.classList.add("cdk-visually-hidden"),r.setAttribute("aria-atomic","true"),r.setAttribute("aria-live","polite"),r.id=`cdk-live-announcer-${Bx++}`,this._document.body.appendChild(r),r}_exposeAnnouncerToModals(e){let i=this._document.querySelectorAll('body > .cdk-overlay-container [aria-modal="true"]');for(let r=0;r<i.length;r++){let o=i[r],a=o.getAttribute("aria-owns");a?a.indexOf(e)===-1&&o.setAttribute("aria-owns",a+" "+e):o.setAttribute("aria-owns",e)}}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var $x=200,Bs=class{_letterKeyStream=new z;_items=[];_selectedItemIndex=-1;_pressedLetters=[];_skipPredicateFn;_selectedItem=new z;selectedItem=this._selectedItem;constructor(n,e){let i=typeof e?.debounceInterval=="number"?e.debounceInterval:$x;e?.skipPredicate&&(this._skipPredicateFn=e.skipPredicate),this.setItems(n),this._setupKeyHandler(i)}destroy(){this._pressedLetters=[],this._letterKeyStream.complete(),this._selectedItem.complete()}setCurrentSelectedItemIndex(n){this._selectedItemIndex=n}setItems(n){this._items=n}handleKey(n){let e=n.keyCode;n.key&&n.key.length===1?this._letterKeyStream.next(n.key.toLocaleUpperCase()):(e>=65&&e<=90||e>=48&&e<=57)&&this._letterKeyStream.next(String.fromCharCode(e))}isTyping(){return this._pressedLetters.length>0}reset(){this._pressedLetters=[]}_setupKeyHandler(n){this._letterKeyStream.pipe(Xn(e=>this._pressedLetters.push(e)),Yn(n),Be(()=>this._pressedLetters.length>0),ye(()=>this._pressedLetters.join("").toLocaleUpperCase())).subscribe(e=>{for(let i=1;i<this._items.length+1;i++){let r=(this._selectedItemIndex+i)%this._items.length,o=this._items[r];if(!this._skipPredicateFn?.(o)&&o.getLabel?.().toLocaleUpperCase().trim().indexOf(e)===0){this._selectedItem.next(o);break}}this._pressedLetters=[]})}};function oi(t,...n){return n.length?n.some(e=>t[e]):t.altKey||t.shiftKey||t.ctrlKey||t.metaKey}var $s=class{_items;_activeItemIndex=X(-1);_activeItem=X(null);_wrap=!1;_typeaheadSubscription=lt.EMPTY;_itemChangesSubscription;_vertical=!0;_horizontal=null;_allowedModifierKeys=[];_homeAndEnd=!1;_pageUpAndDown={enabled:!1,delta:10};_effectRef;_typeahead;_skipPredicateFn=n=>n.disabled;constructor(n,e){this._items=n,n instanceof Tn?this._itemChangesSubscription=n.changes.subscribe(i=>this._itemsChanged(i.toArray())):ar(n)&&(this._effectRef=er(()=>this._itemsChanged(n()),{injector:e}))}tabOut=new z;change=new z;skipPredicate(n){return this._skipPredicateFn=n,this}withWrap(n=!0){return this._wrap=n,this}withVerticalOrientation(n=!0){return this._vertical=n,this}withHorizontalOrientation(n){return this._horizontal=n,this}withAllowedModifierKeys(n){return this._allowedModifierKeys=n,this}withTypeAhead(n=200){this._typeaheadSubscription.unsubscribe();let e=this._getItemsArray();return this._typeahead=new Bs(e,{debounceInterval:typeof n=="number"?n:void 0,skipPredicate:i=>this._skipPredicateFn(i)}),this._typeaheadSubscription=this._typeahead.selectedItem.subscribe(i=>{this.setActiveItem(i)}),this}cancelTypeahead(){return this._typeahead?.reset(),this}withHomeAndEnd(n=!0){return this._homeAndEnd=n,this}withPageUpDown(n=!0,e=10){return this._pageUpAndDown={enabled:n,delta:e},this}setActiveItem(n){let e=this._activeItem();this.updateActiveItem(n),this._activeItem()!==e&&this.change.next(this._activeItemIndex())}onKeydown(n){let e=n.keyCode,r=["altKey","ctrlKey","metaKey","shiftKey"].every(o=>!n[o]||this._allowedModifierKeys.indexOf(o)>-1);switch(e){case 9:this.tabOut.next();return;case 40:if(this._vertical&&r){this.setNextItemActive();break}else return;case 38:if(this._vertical&&r){this.setPreviousItemActive();break}else return;case 39:if(this._horizontal&&r){this._horizontal==="rtl"?this.setPreviousItemActive():this.setNextItemActive();break}else return;case 37:if(this._horizontal&&r){this._horizontal==="rtl"?this.setNextItemActive():this.setPreviousItemActive();break}else return;case 36:if(this._homeAndEnd&&r){this.setFirstItemActive();break}else return;case 35:if(this._homeAndEnd&&r){this.setLastItemActive();break}else return;case 33:if(this._pageUpAndDown.enabled&&r){let o=this._activeItemIndex()-this._pageUpAndDown.delta;this._setActiveItemByIndex(o>0?o:0,1);break}else return;case 34:if(this._pageUpAndDown.enabled&&r){let o=this._activeItemIndex()+this._pageUpAndDown.delta,a=this._getItemsArray().length;this._setActiveItemByIndex(o<a?o:a-1,-1);break}else return;default:(r||oi(n,"shiftKey"))&&this._typeahead?.handleKey(n);return}this._typeahead?.reset(),n.preventDefault()}get activeItemIndex(){return this._activeItemIndex()}get activeItem(){return this._activeItem()}isTyping(){return!!this._typeahead&&this._typeahead.isTyping()}setFirstItemActive(){this._setActiveItemByIndex(0,1)}setLastItemActive(){this._setActiveItemByIndex(this._getItemsArray().length-1,-1)}setNextItemActive(){this._activeItemIndex()<0?this.setFirstItemActive():this._setActiveItemByDelta(1)}setPreviousItemActive(){this._activeItemIndex()<0&&this._wrap?this.setLastItemActive():this._setActiveItemByDelta(-1)}updateActiveItem(n){let e=this._getItemsArray(),i=typeof n=="number"?n:e.indexOf(n),r=e[i];this._activeItem.set(r??null),this._activeItemIndex.set(i),this._typeahead?.setCurrentSelectedItemIndex(i)}destroy(){this._typeaheadSubscription.unsubscribe(),this._itemChangesSubscription?.unsubscribe(),this._effectRef?.destroy(),this._typeahead?.destroy(),this.tabOut.complete(),this.change.complete()}_setActiveItemByDelta(n){this._wrap?this._setActiveInWrapMode(n):this._setActiveInDefaultMode(n)}_setActiveInWrapMode(n){let e=this._getItemsArray();for(let i=1;i<=e.length;i++){let r=(this._activeItemIndex()+n*i+e.length)%e.length,o=e[r];if(!this._skipPredicateFn(o)){this.setActiveItem(r);return}}}_setActiveInDefaultMode(n){this._setActiveItemByIndex(this._activeItemIndex()+n,n)}_setActiveItemByIndex(n,e){let i=this._getItemsArray();if(i[n]){for(;this._skipPredicateFn(i[n]);)if(n+=e,!i[n])return;this.setActiveItem(n)}}_getItemsArray(){return ar(this._items)?this._items():this._items instanceof Tn?this._items.toArray():this._items}_itemsChanged(n){this._typeahead?.setItems(n);let e=this._activeItem();if(e){let i=n.indexOf(e);i>-1&&i!==this._activeItemIndex()&&(this._activeItemIndex.set(i),this._typeahead?.setCurrentSelectedItemIndex(i))}}};var Ai=class extends $s{_origin="program";setFocusOrigin(n){return this._origin=n,this}setActiveItem(n){super.setActiveItem(n),this.activeItem&&this.activeItem.focus(this._origin)}};var bd={},ut=class t{_appId=m(ho);static _infix=`a${Math.floor(Math.random()*1e5).toString()}`;getId(n,e=!1){return this._appId!=="ng"&&(n+=this._appId),bd.hasOwnProperty(n)||(bd[n]=0),`${n}${e?t._infix+"-":""}${bd[n]++}`}static \u0275fac=function(e){return new(e||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})};var Mf={XSmall:"(max-width: 599.98px)",Small:"(min-width: 600px) and (max-width: 959.98px)",Medium:"(min-width: 960px) and (max-width: 1279.98px)",Large:"(min-width: 1280px) and (max-width: 1919.98px)",XLarge:"(min-width: 1920px)",Handset:"(max-width: 599.98px) and (orientation: portrait), (max-width: 959.98px) and (orientation: landscape)",Tablet:"(min-width: 600px) and (max-width: 839.98px) and (orientation: portrait), (min-width: 960px) and (max-width: 1279.98px) and (orientation: landscape)",Web:"(min-width: 840px) and (orientation: portrait), (min-width: 1280px) and (orientation: landscape)",HandsetPortrait:"(max-width: 599.98px) and (orientation: portrait)",TabletPortrait:"(min-width: 600px) and (max-width: 839.98px) and (orientation: portrait)",WebPortrait:"(min-width: 840px) and (orientation: portrait)",HandsetLandscape:"(max-width: 959.98px) and (orientation: landscape)",TabletLandscape:"(min-width: 960px) and (max-width: 1279.98px) and (orientation: landscape)",WebLandscape:"(min-width: 1280px) and (orientation: landscape)"};function vd(){return typeof __karma__<"u"&&!!__karma__||typeof jasmine<"u"&&!!jasmine||typeof jest<"u"&&!!jest||typeof Mocha<"u"&&!!Mocha}function Ge(t){return t==null?"":typeof t=="string"?t:`${t}px`}var on=(function(t){return t[t.NORMAL=0]="NORMAL",t[t.NEGATED=1]="NEGATED",t[t.INVERTED=2]="INVERTED",t})(on||{}),js,Ii;function Vs(){if(Ii==null){if(typeof document!="object"||!document||typeof Element!="function"||!Element)return Ii=!1,Ii;if(document.documentElement?.style&&"scrollBehavior"in document.documentElement.style)Ii=!0;else{let t=Element.prototype.scrollTo;t?Ii=!/\{\s*\[native code\]\s*\}/.test(t.toString()):Ii=!1}}return Ii}function br(){if(typeof document!="object"||!document)return on.NORMAL;if(js==null){let t=document.createElement("div"),n=t.style;t.dir="rtl",n.width="1px",n.overflow="auto",n.visibility="hidden",n.pointerEvents="none",n.position="absolute";let e=document.createElement("div"),i=e.style;i.width="2px",i.height="1px",t.appendChild(e),document.body.appendChild(t),js=on.NORMAL,t.scrollLeft===0&&(t.scrollLeft=1,js=t.scrollLeft===0?on.NEGATED:on.INVERTED),t.remove()}return js}var jx=20,Ti=(()=>{class t{_ngZone=m(K);_platform=m(we);_renderer=m(bt).createRenderer(null,null);_cleanupGlobalListener;constructor(){}_scrolled=new z;_scrolledCount=0;scrollContainers=new Map;register(e){this.scrollContainers.has(e)||this.scrollContainers.set(e,e.elementScrolled().subscribe(()=>this._scrolled.next(e)))}deregister(e){let i=this.scrollContainers.get(e);i&&(i.unsubscribe(),this.scrollContainers.delete(e))}scrolled(e=jx){return this._platform.isBrowser?new St(i=>{this._cleanupGlobalListener||(this._cleanupGlobalListener=this._ngZone.runOutsideAngular(()=>this._renderer.listen("document","scroll",()=>this._scrolled.next())));let r=e>0?this._scrolled.pipe(_c(e)).subscribe(i):this._scrolled.subscribe(i);return this._scrolledCount++,()=>{r.unsubscribe(),this._scrolledCount--,this._scrolledCount||(this._cleanupGlobalListener?.(),this._cleanupGlobalListener=void 0)}}):Pe()}ngOnDestroy(){this._cleanupGlobalListener?.(),this._cleanupGlobalListener=void 0,this.scrollContainers.forEach((e,i)=>this.deregister(i)),this._scrolled.complete()}ancestorScrolled(e,i){let r=this.getAncestorScrollContainers(e);return this.scrolled(i).pipe(Be(o=>!o||r.indexOf(o)>-1))}getAncestorScrollContainers(e){let i=[];return this.scrollContainers.forEach((r,o)=>{this._scrollableContainsElement(o,e)&&i.push(o)}),i}_scrollableContainsElement(e,i){let r=Ut(i),o=e.getElementRef().nativeElement;do if(r==o)return!0;while(r=r.parentElement);return!1}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),vr=(()=>{class t{elementRef=m(Y);scrollDispatcher=m(Ti);ngZone=m(K);dir=m(wt,{optional:!0});_scrollElement=this.elementRef.nativeElement;_destroyed=new z;_renderer=m(rt);_cleanupScroll;_elementScrolled=new z;constructor(){}ngOnInit(){this._cleanupScroll=this.ngZone.runOutsideAngular(()=>this._renderer.listen(this._scrollElement,"scroll",e=>this._elementScrolled.next(e))),this.scrollDispatcher.register(this)}ngOnDestroy(){this._cleanupScroll?.(),this._elementScrolled.complete(),this.scrollDispatcher.deregister(this),this._destroyed.next(),this._destroyed.complete()}elementScrolled(){return this._elementScrolled}getElementRef(){return this.elementRef}scrollTo(e){let i=this.elementRef.nativeElement,r=this.dir&&this.dir.value=="rtl";e.left==null&&(e.left=r?e.end:e.start),e.right==null&&(e.right=r?e.start:e.end),e.bottom!=null&&(e.top=i.scrollHeight-i.clientHeight-e.bottom),r&&br()!=on.NORMAL?(e.left!=null&&(e.right=i.scrollWidth-i.clientWidth-e.left),br()==on.INVERTED?e.left=e.right:br()==on.NEGATED&&(e.left=e.right?-e.right:e.right)):e.right!=null&&(e.left=i.scrollWidth-i.clientWidth-e.right),this._applyScrollToOptions(e)}_applyScrollToOptions(e){let i=this.elementRef.nativeElement;Vs()?i.scrollTo(e):(e.top!=null&&(i.scrollTop=e.top),e.left!=null&&(i.scrollLeft=e.left))}measureScrollOffset(e){let i="left",r="right",o=this.elementRef.nativeElement;if(e=="top")return o.scrollTop;if(e=="bottom")return o.scrollHeight-o.clientHeight-o.scrollTop;let a=this.dir&&this.dir.value=="rtl";return e=="start"?e=a?r:i:e=="end"&&(e=a?i:r),a&&br()==on.INVERTED?e==i?o.scrollWidth-o.clientWidth-o.scrollLeft:o.scrollLeft:a&&br()==on.NEGATED?e==i?o.scrollLeft+o.scrollWidth-o.clientWidth:-o.scrollLeft:e==i?o.scrollLeft:o.scrollWidth-o.clientWidth-o.scrollLeft}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","cdk-scrollable",""],["","cdkScrollable",""]]})}return t})(),Vx=20,Bn=(()=>{class t{_platform=m(we);_listeners;_viewportSize=null;_change=new z;_document=m(W);constructor(){let e=m(K),i=m(bt).createRenderer(null,null);e.runOutsideAngular(()=>{if(this._platform.isBrowser){let r=o=>this._change.next(o);this._listeners=[i.listen("window","resize",r),i.listen("window","orientationchange",r)]}this.change().subscribe(()=>this._viewportSize=null)})}ngOnDestroy(){this._listeners?.forEach(e=>e()),this._change.complete()}getViewportSize(){this._viewportSize||this._updateViewportSize();let e={width:this._viewportSize.width,height:this._viewportSize.height};return this._platform.isBrowser||(this._viewportSize=null),e}getViewportRect(){let e=this.getViewportScrollPosition(),{width:i,height:r}=this.getViewportSize();return{top:e.top,left:e.left,bottom:e.top+r,right:e.left+i,height:r,width:i}}getViewportScrollPosition(){if(!this._platform.isBrowser)return{top:0,left:0};let e=this._document,i=this._getWindow(),r=e.documentElement,o=r.getBoundingClientRect(),a=-o.top||e.body?.scrollTop||i.scrollY||r.scrollTop||0,s=-o.left||e.body?.scrollLeft||i.scrollX||r.scrollLeft||0;return{top:a,left:s}}change(e=Vx){return e>0?this._change.pipe(_c(e)):this._change}_getWindow(){return this._document.defaultView||window}_updateViewportSize(){let e=this._getWindow();this._viewportSize=this._platform.isBrowser?{width:e.innerWidth,height:e.innerHeight}:{width:0,height:0}}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var Ln=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({})}return t})(),xd=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[ie,Ln,ie,Ln]})}return t})();var Uo=class{_attachedHost=null;attach(n){return this._attachedHost=n,n.attach(this)}detach(){let n=this._attachedHost;n!=null&&(this._attachedHost=null,n.detach())}get isAttached(){return this._attachedHost!=null}setAttachedHost(n){this._attachedHost=n}},yr=class extends Uo{component;viewContainerRef;injector;projectableNodes;bindings;constructor(n,e,i,r,o){super(),this.component=n,this.viewContainerRef=e,this.injector=i,this.projectableNodes=r,this.bindings=o||null}},$n=class extends Uo{templateRef;viewContainerRef;context;injector;constructor(n,e,i,r){super(),this.templateRef=n,this.viewContainerRef=e,this.context=i,this.injector=r}get origin(){return this.templateRef.elementRef}attach(n,e=this.context){return this.context=e,super.attach(n)}detach(){return this.context=void 0,super.detach()}},wd=class extends Uo{element;constructor(n){super(),this.element=n instanceof Y?n.nativeElement:n}},xr=class{_attachedPortal=null;_disposeFn=null;_isDisposed=!1;hasAttached(){return!!this._attachedPortal}attach(n){if(n instanceof yr)return this._attachedPortal=n,this.attachComponentPortal(n);if(n instanceof $n)return this._attachedPortal=n,this.attachTemplatePortal(n);if(this.attachDomPortal&&n instanceof wd)return this._attachedPortal=n,this.attachDomPortal(n)}attachDomPortal=null;detach(){this._attachedPortal&&(this._attachedPortal.setAttachedHost(null),this._attachedPortal=null),this._invokeDisposeFn()}dispose(){this.hasAttached()&&this.detach(),this._invokeDisposeFn(),this._isDisposed=!0}setDisposeFn(n){this._disposeFn=n}_invokeDisposeFn(){this._disposeFn&&(this._disposeFn(),this._disposeFn=null)}},qo=class extends xr{outletElement;_appRef;_defaultInjector;constructor(n,e,i){super(),this.outletElement=n,this._appRef=e,this._defaultInjector=i}attachComponentPortal(n){let e;if(n.viewContainerRef){let i=n.injector||n.viewContainerRef.injector,r=i.get(Qa,null,{optional:!0})||void 0;e=n.viewContainerRef.createComponent(n.component,{index:n.viewContainerRef.length,injector:i,ngModuleRef:r,projectableNodes:n.projectableNodes||void 0,bindings:n.bindings||void 0}),this.setDisposeFn(()=>e.destroy())}else{let i=this._appRef,r=n.injector||this._defaultInjector||te.NULL,o=r.get(In,i.injector);e=ns(n.component,{elementInjector:r,environmentInjector:o,projectableNodes:n.projectableNodes||void 0,bindings:n.bindings||void 0}),i.attachView(e.hostView),this.setDisposeFn(()=>{i.viewCount>0&&i.detachView(e.hostView),e.destroy()})}return this.outletElement.appendChild(this._getComponentRootNode(e)),this._attachedPortal=n,e}attachTemplatePortal(n){let e=n.viewContainerRef,i=e.createEmbeddedView(n.templateRef,n.context,{injector:n.injector});return i.rootNodes.forEach(r=>this.outletElement.appendChild(r)),i.detectChanges(),this.setDisposeFn(()=>{let r=e.indexOf(i);r!==-1&&e.remove(r)}),this._attachedPortal=n,i}attachDomPortal=n=>{let e=n.element;e.parentNode;let i=this.outletElement.ownerDocument.createComment("dom-portal");e.parentNode.insertBefore(i,e),this.outletElement.appendChild(e),this._attachedPortal=n,super.setDisposeFn(()=>{i.parentNode&&i.parentNode.replaceChild(e,i)})};dispose(){super.dispose(),this.outletElement.remove()}_getComponentRootNode(n){return n.hostView.rootNodes[0]}};var zs=(()=>{class t extends xr{_moduleRef=m(Qa,{optional:!0});_document=m(W);_viewContainerRef=m(hn);_isInitialized=!1;_attachedRef=null;constructor(){super()}get portal(){return this._attachedPortal}set portal(e){this.hasAttached()&&!e&&!this._isInitialized||(this.hasAttached()&&super.detach(),e&&super.attach(e),this._attachedPortal=e||null)}attached=new De;get attachedRef(){return this._attachedRef}ngOnInit(){this._isInitialized=!0}ngOnDestroy(){super.dispose(),this._attachedRef=this._attachedPortal=null}attachComponentPortal(e){e.setAttachedHost(this);let i=e.viewContainerRef!=null?e.viewContainerRef:this._viewContainerRef,r=i.createComponent(e.component,{index:i.length,injector:e.injector||i.injector,projectableNodes:e.projectableNodes||void 0,ngModuleRef:this._moduleRef||void 0,bindings:e.bindings||void 0});return i!==this._viewContainerRef&&this._getRootNode().appendChild(r.hostView.rootNodes[0]),super.setDisposeFn(()=>r.destroy()),this._attachedPortal=e,this._attachedRef=r,this.attached.emit(r),r}attachTemplatePortal(e){e.setAttachedHost(this);let i=this._viewContainerRef.createEmbeddedView(e.templateRef,e.context,{injector:e.injector});return super.setDisposeFn(()=>this._viewContainerRef.clear()),this._attachedPortal=e,this._attachedRef=i,this.attached.emit(i),i}attachDomPortal=e=>{let i=e.element;i.parentNode;let r=this._document.createComment("dom-portal");e.setAttachedHost(this),i.parentNode.insertBefore(r,i),this._getRootNode().appendChild(i),this._attachedPortal=e,super.setDisposeFn(()=>{r.parentNode&&r.parentNode.replaceChild(i,r)})};_getRootNode(){let e=this._viewContainerRef.element.nativeElement;return e.nodeType===e.ELEMENT_NODE?e:e.parentNode}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","cdkPortalOutlet",""]],inputs:{portal:[0,"cdkPortalOutlet","portal"]},outputs:{attached:"attached"},exportAs:["cdkPortalOutlet"],features:[Te]})}return t})(),wr=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({})}return t})();var Af=Vs();function Nf(t){return new Hs(t.get(Bn),t.get(W))}var Hs=class{_viewportRuler;_previousHTMLStyles={top:"",left:""};_previousScrollPosition;_isEnabled=!1;_document;constructor(n,e){this._viewportRuler=n,this._document=e}attach(){}enable(){if(this._canBeEnabled()){let n=this._document.documentElement;this._previousScrollPosition=this._viewportRuler.getViewportScrollPosition(),this._previousHTMLStyles.left=n.style.left||"",this._previousHTMLStyles.top=n.style.top||"",n.style.left=Ge(-this._previousScrollPosition.left),n.style.top=Ge(-this._previousScrollPosition.top),n.classList.add("cdk-global-scrollblock"),this._isEnabled=!0}}disable(){if(this._isEnabled){let n=this._document.documentElement,e=this._document.body,i=n.style,r=e.style,o=i.scrollBehavior||"",a=r.scrollBehavior||"";this._isEnabled=!1,i.left=this._previousHTMLStyles.left,i.top=this._previousHTMLStyles.top,n.classList.remove("cdk-global-scrollblock"),Af&&(i.scrollBehavior=r.scrollBehavior="auto"),window.scroll(this._previousScrollPosition.left,this._previousScrollPosition.top),Af&&(i.scrollBehavior=o,r.scrollBehavior=a)}}_canBeEnabled(){if(this._document.documentElement.classList.contains("cdk-global-scrollblock")||this._isEnabled)return!1;let e=this._document.documentElement,i=this._viewportRuler.getViewportSize();return e.scrollHeight>i.height||e.scrollWidth>i.width}};function Lf(t,n){return new Us(t.get(Ti),t.get(K),t.get(Bn),n)}var Us=class{_scrollDispatcher;_ngZone;_viewportRuler;_config;_scrollSubscription=null;_overlayRef;_initialScrollPosition;constructor(n,e,i,r){this._scrollDispatcher=n,this._ngZone=e,this._viewportRuler=i,this._config=r}attach(n){this._overlayRef,this._overlayRef=n}enable(){if(this._scrollSubscription)return;let n=this._scrollDispatcher.scrolled(0).pipe(Be(e=>!e||!this._overlayRef.overlayElement.contains(e.getElementRef().nativeElement)));this._config&&this._config.threshold&&this._config.threshold>1?(this._initialScrollPosition=this._viewportRuler.getViewportScrollPosition().top,this._scrollSubscription=n.subscribe(()=>{let e=this._viewportRuler.getViewportScrollPosition().top;Math.abs(e-this._initialScrollPosition)>this._config.threshold?this._detach():this._overlayRef.updatePosition()})):this._scrollSubscription=n.subscribe(this._detach)}disable(){this._scrollSubscription&&(this._scrollSubscription.unsubscribe(),this._scrollSubscription=null)}detach(){this.disable(),this._overlayRef=null}_detach=()=>{this.disable(),this._overlayRef.hasAttached()&&this._ngZone.run(()=>this._overlayRef.detach())}};var Go=class{enable(){}disable(){}attach(){}};function Cd(t,n){return n.some(e=>{let i=t.bottom<e.top,r=t.top>e.bottom,o=t.right<e.left,a=t.left>e.right;return i||r||o||a})}function If(t,n){return n.some(e=>{let i=t.top<e.top,r=t.bottom>e.bottom,o=t.left<e.left,a=t.right>e.right;return i||r||o||a})}function Wo(t,n){return new qs(t.get(Ti),t.get(Bn),t.get(K),n)}var qs=class{_scrollDispatcher;_viewportRuler;_ngZone;_config;_scrollSubscription=null;_overlayRef;constructor(n,e,i,r){this._scrollDispatcher=n,this._viewportRuler=e,this._ngZone=i,this._config=r}attach(n){this._overlayRef,this._overlayRef=n}enable(){if(!this._scrollSubscription){let n=this._config?this._config.scrollThrottle:0;this._scrollSubscription=this._scrollDispatcher.scrolled(n).subscribe(()=>{if(this._overlayRef.updatePosition(),this._config&&this._config.autoClose){let e=this._overlayRef.overlayElement.getBoundingClientRect(),{width:i,height:r}=this._viewportRuler.getViewportSize();Cd(e,[{width:i,height:r,bottom:r,right:i,top:0,left:0}])&&(this.disable(),this._ngZone.run(()=>this._overlayRef.detach()))}})}}disable(){this._scrollSubscription&&(this._scrollSubscription.unsubscribe(),this._scrollSubscription=null)}detach(){this.disable(),this._overlayRef=null}},Bf=(()=>{class t{_injector=m(te);constructor(){}noop=()=>new Go;close=e=>Lf(this._injector,e);block=()=>Nf(this._injector);reposition=e=>Wo(this._injector,e);static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),ai=class{positionStrategy;scrollStrategy=new Go;panelClass="";hasBackdrop=!1;backdropClass="cdk-overlay-dark-backdrop";disableAnimations;width;height;minWidth;minHeight;maxWidth;maxHeight;direction;disposeOnNavigation=!1;usePopover;eventPredicate;constructor(n){if(n){let e=Object.keys(n);for(let i of e)n[i]!==void 0&&(this[i]=n[i])}}};var Gs=class{connectionPair;scrollableViewProperties;constructor(n,e){this.connectionPair=n,this.scrollableViewProperties=e}};var $f=(()=>{class t{_attachedOverlays=[];_document=m(W);_isAttached=!1;constructor(){}ngOnDestroy(){this.detach()}add(e){this.remove(e),this._attachedOverlays.push(e)}remove(e){let i=this._attachedOverlays.indexOf(e);i>-1&&this._attachedOverlays.splice(i,1),this._attachedOverlays.length===0&&this.detach()}canReceiveEvent(e,i,r){return r.observers.length<1?!1:e.eventPredicate?e.eventPredicate(i):!0}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),jf=(()=>{class t extends $f{_ngZone=m(K);_renderer=m(bt).createRenderer(null,null);_cleanupKeydown;add(e){super.add(e),this._isAttached||(this._ngZone.runOutsideAngular(()=>{this._cleanupKeydown=this._renderer.listen("body","keydown",this._keydownListener)}),this._isAttached=!0)}detach(){this._isAttached&&(this._cleanupKeydown?.(),this._isAttached=!1)}_keydownListener=e=>{let i=this._attachedOverlays;for(let r=i.length-1;r>-1;r--){let o=i[r];if(this.canReceiveEvent(o,e,o._keydownEvents)){this._ngZone.run(()=>o._keydownEvents.next(e));break}}};static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(t)))(r||t)}})();static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),Vf=(()=>{class t extends $f{_platform=m(we);_ngZone=m(K);_renderer=m(bt).createRenderer(null,null);_cursorOriginalValue;_cursorStyleIsSet=!1;_pointerDownEventTarget=null;_cleanups;add(e){if(super.add(e),!this._isAttached){let i=this._document.body,r={capture:!0},o=this._renderer;this._cleanups=this._ngZone.runOutsideAngular(()=>[o.listen(i,"pointerdown",this._pointerDownListener,r),o.listen(i,"click",this._clickListener,r),o.listen(i,"auxclick",this._clickListener,r),o.listen(i,"contextmenu",this._clickListener,r)]),this._platform.IOS&&!this._cursorStyleIsSet&&(this._cursorOriginalValue=i.style.cursor,i.style.cursor="pointer",this._cursorStyleIsSet=!0),this._isAttached=!0}}detach(){this._isAttached&&(this._cleanups?.forEach(e=>e()),this._cleanups=void 0,this._platform.IOS&&this._cursorStyleIsSet&&(this._document.body.style.cursor=this._cursorOriginalValue,this._cursorStyleIsSet=!1),this._isAttached=!1)}_pointerDownListener=e=>{this._pointerDownEventTarget=At(e)};_clickListener=e=>{let i=At(e),r=e.type==="click"&&this._pointerDownEventTarget?this._pointerDownEventTarget:i;this._pointerDownEventTarget=null;let o=this._attachedOverlays.slice();for(let a=o.length-1;a>-1;a--){let s=o[a],l=s._outsidePointerEvents;if(!(!s.hasAttached()||!this.canReceiveEvent(s,e,l))){if(Tf(s.overlayElement,i)||Tf(s.overlayElement,r))break;this._ngZone?this._ngZone.run(()=>l.next(e)):l.next(e)}}};static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(t)))(r||t)}})();static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();function Tf(t,n){let e=typeof ShadowRoot<"u"&&ShadowRoot,i=n;for(;i;){if(i===t)return!0;i=e&&i instanceof ShadowRoot?i.host:i.parentNode}return!1}var zf=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["ng-component"]],hostAttrs:["cdk-overlay-style-loader",""],decls:0,vars:0,template:function(i,r){},styles:[`.cdk-overlay-container, .cdk-global-overlay-wrapper {
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
`],encapsulation:2,changeDetection:0})}return t})(),Hf=(()=>{class t{_platform=m(we);_containerElement;_document=m(W);_styleLoader=m(Je);constructor(){}ngOnDestroy(){this._containerElement?.remove()}getContainerElement(){return this._loadStyles(),this._containerElement||this._createContainer(),this._containerElement}_createContainer(){let e="cdk-overlay-container";if(this._platform.isBrowser||vd()){let r=this._document.querySelectorAll(`.${e}[platform="server"], .${e}[platform="test"]`);for(let o=0;o<r.length;o++)r[o].remove()}let i=this._document.createElement("div");i.classList.add(e),vd()?i.setAttribute("platform","test"):this._platform.isBrowser||i.setAttribute("platform","server"),this._document.body.appendChild(i),this._containerElement=i}_loadStyles(){this._styleLoader.load(zf)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),kd=class{_renderer;_ngZone;element;_cleanupClick;_cleanupTransitionEnd;_fallbackTimeout;constructor(n,e,i,r){this._renderer=e,this._ngZone=i,this.element=n.createElement("div"),this.element.classList.add("cdk-overlay-backdrop"),this._cleanupClick=e.listen(this.element,"click",r)}detach(){this._ngZone.runOutsideAngular(()=>{let n=this.element;clearTimeout(this._fallbackTimeout),this._cleanupTransitionEnd?.(),this._cleanupTransitionEnd=this._renderer.listen(n,"transitionend",this.dispose),this._fallbackTimeout=setTimeout(this.dispose,500),n.style.pointerEvents="none",n.classList.remove("cdk-overlay-backdrop-showing")})}dispose=()=>{clearTimeout(this._fallbackTimeout),this._cleanupClick?.(),this._cleanupTransitionEnd?.(),this._cleanupClick=this._cleanupTransitionEnd=this._fallbackTimeout=void 0,this.element.remove()}};function Dd(t){return t&&t.nodeType===1}var Ws=class{_portalOutlet;_host;_pane;_config;_ngZone;_keyboardDispatcher;_document;_location;_outsideClickDispatcher;_animationsDisabled;_injector;_renderer;_backdropClick=new z;_attachments=new z;_detachments=new z;_positionStrategy;_scrollStrategy;_locationChanges=lt.EMPTY;_backdropRef=null;_detachContentMutationObserver;_detachContentAfterRenderRef;_disposed=!1;_previousHostParent;_keydownEvents=new z;_outsidePointerEvents=new z;_afterNextRenderRef;constructor(n,e,i,r,o,a,s,l,c,d=!1,u,p){this._portalOutlet=n,this._host=e,this._pane=i,this._config=r,this._ngZone=o,this._keyboardDispatcher=a,this._document=s,this._location=l,this._outsideClickDispatcher=c,this._animationsDisabled=d,this._injector=u,this._renderer=p,r.scrollStrategy&&(this._scrollStrategy=r.scrollStrategy,this._scrollStrategy.attach(this)),this._positionStrategy=r.positionStrategy}get overlayElement(){return this._pane}get backdropElement(){return this._backdropRef?.element||null}get hostElement(){return this._host}get eventPredicate(){return this._config?.eventPredicate||null}attach(n){if(this._disposed)return null;this._attachHost();let e=this._portalOutlet.attach(n);return this._positionStrategy?.attach(this),this._updateStackingOrder(),this._updateElementSize(),this._updateElementDirection(),this._scrollStrategy&&this._scrollStrategy.enable(),this._afterNextRenderRef?.destroy(),this._afterNextRenderRef=mt(()=>{this.hasAttached()&&this.updatePosition()},{injector:this._injector}),this._togglePointerEvents(!0),this._config.hasBackdrop&&this._attachBackdrop(),this._config.panelClass&&this._toggleClasses(this._pane,this._config.panelClass,!0),this._attachments.next(),this._completeDetachContent(),this._keyboardDispatcher.add(this),this._config.disposeOnNavigation&&(this._locationChanges=this._location.subscribe(()=>this.dispose())),this._outsideClickDispatcher.add(this),typeof e?.onDestroy=="function"&&e.onDestroy(()=>{this.hasAttached()&&this._ngZone.runOutsideAngular(()=>Promise.resolve().then(()=>this.detach()))}),e}detach(){if(!this.hasAttached())return;this.detachBackdrop(),this._togglePointerEvents(!1),this._positionStrategy&&this._positionStrategy.detach&&this._positionStrategy.detach(),this._scrollStrategy&&this._scrollStrategy.disable();let n=this._portalOutlet.detach();return this._detachments.next(),this._completeDetachContent(),this._keyboardDispatcher.remove(this),this._detachContentWhenEmpty(),this._locationChanges.unsubscribe(),this._outsideClickDispatcher.remove(this),n}dispose(){if(this._disposed)return;let n=this.hasAttached();this._positionStrategy&&this._positionStrategy.dispose(),this._disposeScrollStrategy(),this._backdropRef?.dispose(),this._locationChanges.unsubscribe(),this._keyboardDispatcher.remove(this),this._portalOutlet.dispose(),this._attachments.complete(),this._backdropClick.complete(),this._keydownEvents.complete(),this._outsidePointerEvents.complete(),this._outsideClickDispatcher.remove(this),this._host?.remove(),this._afterNextRenderRef?.destroy(),this._previousHostParent=this._pane=this._host=this._backdropRef=null,n&&this._detachments.next(),this._detachments.complete(),this._completeDetachContent(),this._disposed=!0}hasAttached(){return this._portalOutlet.hasAttached()}backdropClick(){return this._backdropClick}attachments(){return this._attachments}detachments(){return this._detachments}keydownEvents(){return this._keydownEvents}outsidePointerEvents(){return this._outsidePointerEvents}getConfig(){return this._config}updatePosition(){this._positionStrategy&&this._positionStrategy.apply()}updatePositionStrategy(n){n!==this._positionStrategy&&(this._positionStrategy&&this._positionStrategy.dispose(),this._positionStrategy=n,this.hasAttached()&&(n.attach(this),this.updatePosition()))}updateSize(n){this._config=M(M({},this._config),n),this._updateElementSize()}setDirection(n){this._config=Ie(M({},this._config),{direction:n}),this._updateElementDirection()}addPanelClass(n){this._pane&&this._toggleClasses(this._pane,n,!0)}removePanelClass(n){this._pane&&this._toggleClasses(this._pane,n,!1)}getDirection(){let n=this._config.direction;return n?typeof n=="string"?n:n.value:"ltr"}updateScrollStrategy(n){n!==this._scrollStrategy&&(this._disposeScrollStrategy(),this._scrollStrategy=n,this.hasAttached()&&(n.attach(this),n.enable()))}_updateElementDirection(){this._host.setAttribute("dir",this.getDirection())}_updateElementSize(){if(!this._pane)return;let n=this._pane.style;n.width=Ge(this._config.width),n.height=Ge(this._config.height),n.minWidth=Ge(this._config.minWidth),n.minHeight=Ge(this._config.minHeight),n.maxWidth=Ge(this._config.maxWidth),n.maxHeight=Ge(this._config.maxHeight)}_togglePointerEvents(n){this._pane.style.pointerEvents=n?"":"none"}_attachHost(){if(!this._host.parentElement){let n=this._config.usePopover?this._positionStrategy?.getPopoverInsertionPoint?.():null;Dd(n)?n.after(this._host):n?.type==="parent"?n.element.appendChild(this._host):this._previousHostParent?.appendChild(this._host)}if(this._config.usePopover)try{this._host.showPopover()}catch{}}_attachBackdrop(){let n="cdk-overlay-backdrop-showing";this._backdropRef?.dispose(),this._backdropRef=new kd(this._document,this._renderer,this._ngZone,e=>{this._backdropClick.next(e)}),this._animationsDisabled&&this._backdropRef.element.classList.add("cdk-overlay-backdrop-noop-animation"),this._config.backdropClass&&this._toggleClasses(this._backdropRef.element,this._config.backdropClass,!0),this._config.usePopover?this._host.prepend(this._backdropRef.element):this._host.parentElement.insertBefore(this._backdropRef.element,this._host),!this._animationsDisabled&&typeof requestAnimationFrame<"u"?this._ngZone.runOutsideAngular(()=>{requestAnimationFrame(()=>this._backdropRef?.element.classList.add(n))}):this._backdropRef.element.classList.add(n)}_updateStackingOrder(){!this._config.usePopover&&this._host.nextSibling&&this._host.parentNode.appendChild(this._host)}detachBackdrop(){this._animationsDisabled?(this._backdropRef?.dispose(),this._backdropRef=null):this._backdropRef?.detach()}_toggleClasses(n,e,i){let r=_r(e||[]).filter(o=>!!o);r.length&&(i?n.classList.add(...r):n.classList.remove(...r))}_detachContentWhenEmpty(){let n=!1;try{this._detachContentAfterRenderRef=mt(()=>{n=!0,this._detachContent()},{injector:this._injector})}catch(e){if(n)throw e;this._detachContent()}globalThis.MutationObserver&&this._pane&&(this._detachContentMutationObserver||=new globalThis.MutationObserver(()=>{this._detachContent()}),this._detachContentMutationObserver.observe(this._pane,{childList:!0}))}_detachContent(){(!this._pane||!this._host||this._pane.children.length===0)&&(this._pane&&this._config.panelClass&&this._toggleClasses(this._pane,this._config.panelClass,!1),this._host&&this._host.parentElement&&(this._previousHostParent=this._host.parentElement,this._host.remove()),this._completeDetachContent())}_completeDetachContent(){this._detachContentAfterRenderRef?.destroy(),this._detachContentAfterRenderRef=void 0,this._detachContentMutationObserver?.disconnect()}_disposeScrollStrategy(){let n=this._scrollStrategy;n?.disable(),n?.detach?.()}},Of="cdk-overlay-connected-position-bounding-box",zx=/([A-Za-z%]+)$/;function Qs(t,n){return new Ks(n,t.get(Bn),t.get(W),t.get(we),t.get(Hf))}var Ks=class{_viewportRuler;_document;_platform;_overlayContainer;_overlayRef;_isInitialRender=!1;_lastBoundingBoxSize={width:0,height:0};_isPushed=!1;_canPush=!0;_growAfterOpen=!1;_hasFlexibleDimensions=!0;_positionLocked=!1;_originRect;_overlayRect;_viewportRect;_containerRect;_viewportMargin=0;_scrollables=[];_preferredPositions=[];_origin;_pane;_isDisposed=!1;_boundingBox=null;_lastPosition=null;_lastScrollVisibility=null;_positionChanges=new z;_resizeSubscription=lt.EMPTY;_offsetX=0;_offsetY=0;_transformOriginSelector;_appliedPanelClasses=[];_previousPushAmount=null;_popoverLocation="global";positionChanges=this._positionChanges;get positions(){return this._preferredPositions}constructor(n,e,i,r,o){this._viewportRuler=e,this._document=i,this._platform=r,this._overlayContainer=o,this.setOrigin(n)}attach(n){this._overlayRef&&this._overlayRef,this._validatePositions(),n.hostElement.classList.add(Of),this._overlayRef=n,this._boundingBox=n.hostElement,this._pane=n.overlayElement,this._isDisposed=!1,this._isInitialRender=!0,this._lastPosition=null,this._resizeSubscription.unsubscribe(),this._resizeSubscription=this._viewportRuler.change().subscribe(()=>{this._isInitialRender=!0,this.apply()})}apply(){if(this._isDisposed||!this._platform.isBrowser)return;if(!this._isInitialRender&&this._positionLocked&&this._lastPosition){this.reapplyLastPosition();return}this._clearPanelClasses(),this._resetOverlayElementStyles(),this._resetBoundingBoxStyles(),this._viewportRect=this._getNarrowedViewportRect(),this._originRect=this._getOriginRect(),this._overlayRect=this._pane.getBoundingClientRect(),this._containerRect=this._getContainerRect();let n=this._originRect,e=this._overlayRect,i=this._viewportRect,r=this._containerRect,o=[],a;for(let s of this._preferredPositions){let l=this._getOriginPoint(n,r,s),c=this._getOverlayPoint(l,e,s),d=this._getOverlayFit(c,e,i,s);if(d.isCompletelyWithinViewport){this._isPushed=!1,this._applyPosition(s,l);return}if(this._canFitWithFlexibleDimensions(d,c,i)){o.push({position:s,origin:l,overlayRect:e,boundingBoxRect:this._calculateBoundingBoxRect(l,s)});continue}(!a||a.overlayFit.visibleArea<d.visibleArea)&&(a={overlayFit:d,overlayPoint:c,originPoint:l,position:s,overlayRect:e})}if(o.length){let s=null,l=-1;for(let c of o){let d=c.boundingBoxRect.width*c.boundingBoxRect.height*(c.position.weight||1);d>l&&(l=d,s=c)}this._isPushed=!1,this._applyPosition(s.position,s.origin);return}if(this._canPush){this._isPushed=!0,this._applyPosition(a.position,a.originPoint);return}this._applyPosition(a.position,a.originPoint)}detach(){this._clearPanelClasses(),this._lastPosition=null,this._previousPushAmount=null,this._resizeSubscription.unsubscribe()}dispose(){this._isDisposed||(this._boundingBox&&Oi(this._boundingBox.style,{top:"",left:"",right:"",bottom:"",height:"",width:"",alignItems:"",justifyContent:""}),this._pane&&this._resetOverlayElementStyles(),this._overlayRef&&this._overlayRef.hostElement.classList.remove(Of),this.detach(),this._positionChanges.complete(),this._overlayRef=this._boundingBox=null,this._isDisposed=!0)}reapplyLastPosition(){if(this._isDisposed||!this._platform.isBrowser)return;let n=this._lastPosition;n?(this._originRect=this._getOriginRect(),this._overlayRect=this._pane.getBoundingClientRect(),this._viewportRect=this._getNarrowedViewportRect(),this._containerRect=this._getContainerRect(),this._applyPosition(n,this._getOriginPoint(this._originRect,this._containerRect,n))):this.apply()}withScrollableContainers(n){return this._scrollables=n,this}withPositions(n){return this._preferredPositions=n,n.indexOf(this._lastPosition)===-1&&(this._lastPosition=null),this._validatePositions(),this}withViewportMargin(n){return this._viewportMargin=n,this}withFlexibleDimensions(n=!0){return this._hasFlexibleDimensions=n,this}withGrowAfterOpen(n=!0){return this._growAfterOpen=n,this}withPush(n=!0){return this._canPush=n,this}withLockedPosition(n=!0){return this._positionLocked=n,this}setOrigin(n){return this._origin=n,this}withDefaultOffsetX(n){return this._offsetX=n,this}withDefaultOffsetY(n){return this._offsetY=n,this}withTransformOriginOn(n){return this._transformOriginSelector=n,this}withPopoverLocation(n){return this._popoverLocation=n,this}getPopoverInsertionPoint(){return this._popoverLocation==="global"?null:this._popoverLocation!=="inline"?this._popoverLocation:this._origin instanceof Y?this._origin.nativeElement:Dd(this._origin)?this._origin:null}_getOriginPoint(n,e,i){let r;if(i.originX=="center")r=n.left+n.width/2;else{let a=this._isRtl()?n.right:n.left,s=this._isRtl()?n.left:n.right;r=i.originX=="start"?a:s}e.left<0&&(r-=e.left);let o;return i.originY=="center"?o=n.top+n.height/2:o=i.originY=="top"?n.top:n.bottom,e.top<0&&(o-=e.top),{x:r,y:o}}_getOverlayPoint(n,e,i){let r;i.overlayX=="center"?r=-e.width/2:i.overlayX==="start"?r=this._isRtl()?-e.width:0:r=this._isRtl()?0:-e.width;let o;return i.overlayY=="center"?o=-e.height/2:o=i.overlayY=="top"?0:-e.height,{x:n.x+r,y:n.y+o}}_getOverlayFit(n,e,i,r){let o=Pf(e),{x:a,y:s}=n,l=this._getOffset(r,"x"),c=this._getOffset(r,"y");l&&(a+=l),c&&(s+=c);let d=0-a,u=a+o.width-i.width,p=0-s,y=s+o.height-i.height,g=this._subtractOverflows(o.width,d,u),_=this._subtractOverflows(o.height,p,y),v=g*_;return{visibleArea:v,isCompletelyWithinViewport:o.width*o.height===v,fitsInViewportVertically:_===o.height,fitsInViewportHorizontally:g==o.width}}_canFitWithFlexibleDimensions(n,e,i){if(this._hasFlexibleDimensions){let r=i.bottom-e.y,o=i.right-e.x,a=Rf(this._overlayRef.getConfig().minHeight),s=Rf(this._overlayRef.getConfig().minWidth),l=n.fitsInViewportVertically||a!=null&&a<=r,c=n.fitsInViewportHorizontally||s!=null&&s<=o;return l&&c}return!1}_pushOverlayOnScreen(n,e,i){if(this._previousPushAmount&&this._positionLocked)return{x:n.x+this._previousPushAmount.x,y:n.y+this._previousPushAmount.y};let r=Pf(e),o=this._viewportRect,a=Math.max(n.x+r.width-o.width,0),s=Math.max(n.y+r.height-o.height,0),l=Math.max(o.top-i.top-n.y,0),c=Math.max(o.left-i.left-n.x,0),d=0,u=0;return r.width<=o.width?d=c||-a:d=n.x<this._getViewportMarginStart()?o.left-i.left-n.x:0,r.height<=o.height?u=l||-s:u=n.y<this._getViewportMarginTop()?o.top-i.top-n.y:0,this._previousPushAmount={x:d,y:u},{x:n.x+d,y:n.y+u}}_applyPosition(n,e){if(this._setTransformOrigin(n),this._setOverlayElementStyles(e,n),this._setBoundingBoxStyles(e,n),n.panelClass&&this._addPanelClasses(n.panelClass),this._positionChanges.observers.length){let i=this._getScrollVisibility();if(n!==this._lastPosition||!this._lastScrollVisibility||!Hx(this._lastScrollVisibility,i)){let r=new Gs(n,i);this._positionChanges.next(r)}this._lastScrollVisibility=i}this._lastPosition=n,this._isInitialRender=!1}_setTransformOrigin(n){if(!this._transformOriginSelector)return;let e=this._boundingBox.querySelectorAll(this._transformOriginSelector),i,r=n.overlayY;n.overlayX==="center"?i="center":this._isRtl()?i=n.overlayX==="start"?"right":"left":i=n.overlayX==="start"?"left":"right";for(let o=0;o<e.length;o++)e[o].style.transformOrigin=`${i} ${r}`}_calculateBoundingBoxRect(n,e){let i=this._viewportRect,r=this._isRtl(),o,a,s;if(e.overlayY==="top")a=n.y,o=i.height-a+this._getViewportMarginBottom();else if(e.overlayY==="bottom")s=i.height-n.y+this._getViewportMarginTop()+this._getViewportMarginBottom(),o=i.height-s+this._getViewportMarginTop();else{let y=Math.min(i.bottom-n.y+i.top,n.y),g=this._lastBoundingBoxSize.height;o=y*2,a=n.y-y,o>g&&!this._isInitialRender&&!this._growAfterOpen&&(a=n.y-g/2)}let l=e.overlayX==="start"&&!r||e.overlayX==="end"&&r,c=e.overlayX==="end"&&!r||e.overlayX==="start"&&r,d,u,p;if(c)p=i.width-n.x+this._getViewportMarginStart()+this._getViewportMarginEnd(),d=n.x-this._getViewportMarginStart();else if(l)u=n.x,d=i.right-n.x-this._getViewportMarginEnd();else{let y=Math.min(i.right-n.x+i.left,n.x),g=this._lastBoundingBoxSize.width;d=y*2,u=n.x-y,d>g&&!this._isInitialRender&&!this._growAfterOpen&&(u=n.x-g/2)}return{top:a,left:u,bottom:s,right:p,width:d,height:o}}_setBoundingBoxStyles(n,e){let i=this._calculateBoundingBoxRect(n,e);!this._isInitialRender&&!this._growAfterOpen&&(i.height=Math.min(i.height,this._lastBoundingBoxSize.height),i.width=Math.min(i.width,this._lastBoundingBoxSize.width));let r={};if(this._hasExactPosition())r.top=r.left="0",r.bottom=r.right="auto",r.maxHeight=r.maxWidth="",r.width=r.height="100%";else{let o=this._overlayRef.getConfig().maxHeight,a=this._overlayRef.getConfig().maxWidth;r.width=Ge(i.width),r.height=Ge(i.height),r.top=Ge(i.top)||"auto",r.bottom=Ge(i.bottom)||"auto",r.left=Ge(i.left)||"auto",r.right=Ge(i.right)||"auto",e.overlayX==="center"?r.alignItems="center":r.alignItems=e.overlayX==="end"?"flex-end":"flex-start",e.overlayY==="center"?r.justifyContent="center":r.justifyContent=e.overlayY==="bottom"?"flex-end":"flex-start",o&&(r.maxHeight=Ge(o)),a&&(r.maxWidth=Ge(a))}this._lastBoundingBoxSize=i,Oi(this._boundingBox.style,r)}_resetBoundingBoxStyles(){Oi(this._boundingBox.style,{top:"0",left:"0",right:"0",bottom:"0",height:"",width:"",alignItems:"",justifyContent:""})}_resetOverlayElementStyles(){Oi(this._pane.style,{top:"",left:"",bottom:"",right:"",position:"",transform:""})}_setOverlayElementStyles(n,e){let i={},r=this._hasExactPosition(),o=this._hasFlexibleDimensions,a=this._overlayRef.getConfig();if(r){let d=this._viewportRuler.getViewportScrollPosition();Oi(i,this._getExactOverlayY(e,n,d)),Oi(i,this._getExactOverlayX(e,n,d))}else i.position="static";let s="",l=this._getOffset(e,"x"),c=this._getOffset(e,"y");l&&(s+=`translateX(${l}px) `),c&&(s+=`translateY(${c}px)`),i.transform=s.trim(),a.maxHeight&&(r?i.maxHeight=Ge(a.maxHeight):o&&(i.maxHeight="")),a.maxWidth&&(r?i.maxWidth=Ge(a.maxWidth):o&&(i.maxWidth="")),Oi(this._pane.style,i)}_getExactOverlayY(n,e,i){let r={top:"",bottom:""},o=this._getOverlayPoint(e,this._overlayRect,n);if(this._isPushed&&(o=this._pushOverlayOnScreen(o,this._overlayRect,i)),n.overlayY==="bottom"){let a=this._document.documentElement.clientHeight;r.bottom=`${a-(o.y+this._overlayRect.height)}px`}else r.top=Ge(o.y);return r}_getExactOverlayX(n,e,i){let r={left:"",right:""},o=this._getOverlayPoint(e,this._overlayRect,n);this._isPushed&&(o=this._pushOverlayOnScreen(o,this._overlayRect,i));let a;if(this._isRtl()?a=n.overlayX==="end"?"left":"right":a=n.overlayX==="end"?"right":"left",a==="right"){let s=this._document.documentElement.clientWidth;r.right=`${s-(o.x+this._overlayRect.width)}px`}else r.left=Ge(o.x);return r}_getScrollVisibility(){let n=this._getOriginRect(),e=this._pane.getBoundingClientRect(),i=this._scrollables.map(r=>r.getElementRef().nativeElement.getBoundingClientRect());return{isOriginClipped:If(n,i),isOriginOutsideView:Cd(n,i),isOverlayClipped:If(e,i),isOverlayOutsideView:Cd(e,i)}}_subtractOverflows(n,...e){return e.reduce((i,r)=>i-Math.max(r,0),n)}_getNarrowedViewportRect(){let n=this._document.documentElement.clientWidth,e=this._document.documentElement.clientHeight,i=this._viewportRuler.getViewportScrollPosition();return{top:i.top+this._getViewportMarginTop(),left:i.left+this._getViewportMarginStart(),right:i.left+n-this._getViewportMarginEnd(),bottom:i.top+e-this._getViewportMarginBottom(),width:n-this._getViewportMarginStart()-this._getViewportMarginEnd(),height:e-this._getViewportMarginTop()-this._getViewportMarginBottom()}}_isRtl(){return this._overlayRef.getDirection()==="rtl"}_hasExactPosition(){return!this._hasFlexibleDimensions||this._isPushed}_getOffset(n,e){return e==="x"?n.offsetX==null?this._offsetX:n.offsetX:n.offsetY==null?this._offsetY:n.offsetY}_validatePositions(){}_addPanelClasses(n){this._pane&&_r(n).forEach(e=>{e!==""&&this._appliedPanelClasses.indexOf(e)===-1&&(this._appliedPanelClasses.push(e),this._pane.classList.add(e))})}_clearPanelClasses(){this._pane&&(this._appliedPanelClasses.forEach(n=>{this._pane.classList.remove(n)}),this._appliedPanelClasses=[])}_getViewportMarginStart(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.start??0}_getViewportMarginEnd(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.end??0}_getViewportMarginTop(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.top??0}_getViewportMarginBottom(){return typeof this._viewportMargin=="number"?this._viewportMargin:this._viewportMargin?.bottom??0}_getOriginRect(){let n=this._origin;if(n instanceof Y)return n.nativeElement.getBoundingClientRect();if(n instanceof Element)return n.getBoundingClientRect();let e=n.width||0,i=n.height||0;return{top:n.y,bottom:n.y+i,left:n.x,right:n.x+e,height:i,width:e}}_getContainerRect(){let n=this._overlayRef.getConfig().usePopover&&this._popoverLocation!=="global",e=this._overlayContainer.getContainerElement();n&&(e.style.display="block");let i=e.getBoundingClientRect();return n&&(e.style.display=""),i}};function Oi(t,n){for(let e in n)n.hasOwnProperty(e)&&(t[e]=n[e]);return t}function Rf(t){if(typeof t!="number"&&t!=null){let[n,e]=t.split(zx);return!e||e==="px"?parseFloat(n):null}return t||null}function Pf(t){return{top:Math.floor(t.top),right:Math.floor(t.right),bottom:Math.floor(t.bottom),left:Math.floor(t.left),width:Math.floor(t.width),height:Math.floor(t.height)}}function Hx(t,n){return t===n?!0:t.isOriginClipped===n.isOriginClipped&&t.isOriginOutsideView===n.isOriginOutsideView&&t.isOverlayClipped===n.isOverlayClipped&&t.isOverlayOutsideView===n.isOverlayOutsideView}var Ff="cdk-global-overlay-wrapper";function Xs(t){return new Ys}var Ys=class{_overlayRef;_cssPosition="static";_topOffset="";_bottomOffset="";_alignItems="";_xPosition="";_xOffset="";_width="";_height="";_isDisposed=!1;attach(n){let e=n.getConfig();this._overlayRef=n,this._width&&!e.width&&n.updateSize({width:this._width}),this._height&&!e.height&&n.updateSize({height:this._height}),n.hostElement.classList.add(Ff),this._isDisposed=!1}top(n=""){return this._bottomOffset="",this._topOffset=n,this._alignItems="flex-start",this}left(n=""){return this._xOffset=n,this._xPosition="left",this}bottom(n=""){return this._topOffset="",this._bottomOffset=n,this._alignItems="flex-end",this}right(n=""){return this._xOffset=n,this._xPosition="right",this}start(n=""){return this._xOffset=n,this._xPosition="start",this}end(n=""){return this._xOffset=n,this._xPosition="end",this}width(n=""){return this._overlayRef?this._overlayRef.updateSize({width:n}):this._width=n,this}height(n=""){return this._overlayRef?this._overlayRef.updateSize({height:n}):this._height=n,this}centerHorizontally(n=""){return this.left(n),this._xPosition="center",this}centerVertically(n=""){return this.top(n),this._alignItems="center",this}apply(){if(!this._overlayRef||!this._overlayRef.hasAttached())return;let n=this._overlayRef.overlayElement.style,e=this._overlayRef.hostElement.style,i=this._overlayRef.getConfig(),{width:r,height:o,maxWidth:a,maxHeight:s}=i,l=(r==="100%"||r==="100vw")&&(!a||a==="100%"||a==="100vw"),c=(o==="100%"||o==="100vh")&&(!s||s==="100%"||s==="100vh"),d=this._xPosition,u=this._xOffset,p=this._overlayRef.getConfig().direction==="rtl",y="",g="",_="";l?_="flex-start":d==="center"?(_="center",p?g=u:y=u):p?d==="left"||d==="end"?(_="flex-end",y=u):(d==="right"||d==="start")&&(_="flex-start",g=u):d==="left"||d==="start"?(_="flex-start",y=u):(d==="right"||d==="end")&&(_="flex-end",g=u),n.position=this._cssPosition,n.marginLeft=l?"0":y,n.marginTop=c?"0":this._topOffset,n.marginBottom=this._bottomOffset,n.marginRight=l?"0":g,e.justifyContent=_,e.alignItems=c?"flex-start":this._alignItems}dispose(){if(this._isDisposed||!this._overlayRef)return;let n=this._overlayRef.overlayElement.style,e=this._overlayRef.hostElement,i=e.style;e.classList.remove(Ff),i.justifyContent=i.alignItems=n.marginTop=n.marginBottom=n.marginLeft=n.marginRight=n.position="",this._overlayRef=null,this._isDisposed=!0}},Uf=(()=>{class t{_injector=m(te);constructor(){}global(){return Xs()}flexibleConnectedTo(e){return Qs(this._injector,e)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),Ed=new A("OVERLAY_DEFAULT_CONFIG");function Cr(t,n){t.get(Je).load(zf);let e=t.get(Hf),i=t.get(W),r=t.get(ut),o=t.get(ei),a=t.get(wt),s=t.get(rt,null,{optional:!0})||t.get(bt).createRenderer(null,null),l=new ai(n),c=t.get(Ed,null,{optional:!0})?.usePopover??!0;l.direction=l.direction||a.value,"showPopover"in i.body?l.usePopover=n?.usePopover??c:l.usePopover=!1;let d=i.createElement("div"),u=i.createElement("div");d.id=r.getId("cdk-overlay-"),d.classList.add("cdk-overlay-pane"),u.appendChild(d),l.usePopover&&(u.setAttribute("popover","manual"),u.classList.add("cdk-overlay-popover"));let p=l.usePopover?l.positionStrategy?.getPopoverInsertionPoint?.():null;return Dd(p)?p.after(u):p?.type==="parent"?p.element.appendChild(u):e.getContainerElement().appendChild(u),new Ws(new qo(d,o,t),u,d,l,t.get(K),t.get(jf),i,t.get(dr),t.get(Vf),n?.disableAnimations??t.get(tr,null,{optional:!0})==="NoopAnimations",t.get(In),s)}var qf=(()=>{class t{scrollStrategies=m(Bf);_positionBuilder=m(Uf);_injector=m(te);constructor(){}create(e){return Cr(this._injector,e)}position(){return this._positionBuilder}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var Ri=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({providers:[qf],imports:[ie,wr,xd,xd]})}return t})();var Wx=new A("MATERIAL_ANIMATIONS"),Gf=null;function Kx(){return m(Wx,{optional:!0})?.animationsDisabled||m(tr,{optional:!0})==="NoopAnimations"?"di-disabled":(Gf??=m(Ps).matchMedia("(prefers-reduced-motion)").matches,Gf?"reduced-motion":"enabled")}function He(){return Kx()!=="enabled"}function kt(t){return t!=null&&`${t}`!="false"}var qt=(function(t){return t[t.FADING_IN=0]="FADING_IN",t[t.VISIBLE=1]="VISIBLE",t[t.FADING_OUT=2]="FADING_OUT",t[t.HIDDEN=3]="HIDDEN",t})(qt||{}),Sd=class{_renderer;element;config;_animationForciblyDisabledThroughCss;state=qt.HIDDEN;constructor(n,e,i,r=!1){this._renderer=n,this.element=e,this.config=i,this._animationForciblyDisabledThroughCss=r}fadeOut(){this._renderer.fadeOutRipple(this)}},Wf=gr({passive:!0,capture:!0}),Md=class{_events=new Map;addHandler(n,e,i,r){let o=this._events.get(e);if(o){let a=o.get(i);a?a.add(r):o.set(i,new Set([r]))}else this._events.set(e,new Map([[i,new Set([r])]])),n.runOutsideAngular(()=>{document.addEventListener(e,this._delegateEventHandler,Wf)})}removeHandler(n,e,i){let r=this._events.get(n);if(!r)return;let o=r.get(e);o&&(o.delete(i),o.size===0&&r.delete(e),r.size===0&&(this._events.delete(n),document.removeEventListener(n,this._delegateEventHandler,Wf)))}_delegateEventHandler=n=>{let e=At(n);e&&this._events.get(n.type)?.forEach((i,r)=>{(r===e||r.contains(e))&&i.forEach(o=>o.handleEvent(n))})}},Ko={enterDuration:225,exitDuration:150},Yx=800,Kf=gr({passive:!0,capture:!0}),Yf=["mousedown","touchstart"],Qf=["mouseup","mouseleave","touchend","touchcancel"],Qx=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["ng-component"]],hostAttrs:["mat-ripple-style-loader",""],decls:0,vars:0,template:function(i,r){},styles:[`.mat-ripple {
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
`],encapsulation:2,changeDetection:0})}return t})(),Pi=class t{_target;_ngZone;_platform;_containerElement;_triggerElement=null;_isPointerDown=!1;_activeRipples=new Map;_mostRecentTransientRipple=null;_lastTouchStartEvent;_pointerUpEventsRegistered=!1;_containerRect=null;static _eventManager=new Md;constructor(n,e,i,r,o){this._target=n,this._ngZone=e,this._platform=r,r.isBrowser&&(this._containerElement=Ut(i)),o&&o.get(Je).load(Qx)}fadeInRipple(n,e,i={}){let r=this._containerRect=this._containerRect||this._containerElement.getBoundingClientRect(),o=M(M({},Ko),i.animation);i.centered&&(n=r.left+r.width/2,e=r.top+r.height/2);let a=i.radius||Xx(n,e,r),s=n-r.left,l=e-r.top,c=o.enterDuration,d=document.createElement("div");d.classList.add("mat-ripple-element"),d.style.left=`${s-a}px`,d.style.top=`${l-a}px`,d.style.height=`${a*2}px`,d.style.width=`${a*2}px`,i.color!=null&&(d.style.backgroundColor=i.color),d.style.transitionDuration=`${c}ms`,this._containerElement.appendChild(d);let u=window.getComputedStyle(d),p=u.transitionProperty,y=u.transitionDuration,g=p==="none"||y==="0s"||y==="0s, 0s"||r.width===0&&r.height===0,_=new Sd(this,d,i,g);d.style.transform="scale3d(1, 1, 1)",_.state=qt.FADING_IN,i.persistent||(this._mostRecentTransientRipple=_);let v=null;return!g&&(c||o.exitDuration)&&this._ngZone.runOutsideAngular(()=>{let x=()=>{v&&(v.fallbackTimer=null),clearTimeout(I),this._finishRippleTransition(_)},S=()=>this._destroyRipple(_),I=setTimeout(S,c+100);d.addEventListener("transitionend",x),d.addEventListener("transitioncancel",S),v={onTransitionEnd:x,onTransitionCancel:S,fallbackTimer:I}}),this._activeRipples.set(_,v),(g||!c)&&this._finishRippleTransition(_),_}fadeOutRipple(n){if(n.state===qt.FADING_OUT||n.state===qt.HIDDEN)return;let e=n.element,i=M(M({},Ko),n.config.animation);e.style.transitionDuration=`${i.exitDuration}ms`,e.style.opacity="0",n.state=qt.FADING_OUT,(n._animationForciblyDisabledThroughCss||!i.exitDuration)&&this._finishRippleTransition(n)}fadeOutAll(){this._getActiveRipples().forEach(n=>n.fadeOut())}fadeOutAllNonPersistent(){this._getActiveRipples().forEach(n=>{n.config.persistent||n.fadeOut()})}setupTriggerEvents(n){let e=Ut(n);!this._platform.isBrowser||!e||e===this._triggerElement||(this._removeTriggerEvents(),this._triggerElement=e,Yf.forEach(i=>{t._eventManager.addHandler(this._ngZone,i,e,this)}))}handleEvent(n){n.type==="mousedown"?this._onMousedown(n):n.type==="touchstart"?this._onTouchStart(n):this._onPointerUp(),this._pointerUpEventsRegistered||(this._ngZone.runOutsideAngular(()=>{Qf.forEach(e=>{this._triggerElement.addEventListener(e,this,Kf)})}),this._pointerUpEventsRegistered=!0)}_finishRippleTransition(n){n.state===qt.FADING_IN?this._startFadeOutTransition(n):n.state===qt.FADING_OUT&&this._destroyRipple(n)}_startFadeOutTransition(n){let e=n===this._mostRecentTransientRipple,{persistent:i}=n.config;n.state=qt.VISIBLE,!i&&(!e||!this._isPointerDown)&&n.fadeOut()}_destroyRipple(n){let e=this._activeRipples.get(n)??null;this._activeRipples.delete(n),this._activeRipples.size||(this._containerRect=null),n===this._mostRecentTransientRipple&&(this._mostRecentTransientRipple=null),n.state=qt.HIDDEN,e!==null&&(n.element.removeEventListener("transitionend",e.onTransitionEnd),n.element.removeEventListener("transitioncancel",e.onTransitionCancel),e.fallbackTimer!==null&&clearTimeout(e.fallbackTimer)),n.element.remove()}_onMousedown(n){let e=Ei(n),i=this._lastTouchStartEvent&&Date.now()<this._lastTouchStartEvent+Yx;!this._target.rippleDisabled&&!e&&!i&&(this._isPointerDown=!0,this.fadeInRipple(n.clientX,n.clientY,this._target.rippleConfig))}_onTouchStart(n){if(!this._target.rippleDisabled&&!Si(n)){this._lastTouchStartEvent=Date.now(),this._isPointerDown=!0;let e=n.changedTouches;if(e)for(let i=0;i<e.length;i++)this.fadeInRipple(e[i].clientX,e[i].clientY,this._target.rippleConfig)}}_onPointerUp(){this._isPointerDown&&(this._isPointerDown=!1,this._getActiveRipples().forEach(n=>{let e=n.state===qt.VISIBLE||n.config.terminateOnPointerUp&&n.state===qt.FADING_IN;!n.config.persistent&&e&&n.fadeOut()}))}_getActiveRipples(){return Array.from(this._activeRipples.keys())}_removeTriggerEvents(){let n=this._triggerElement;n&&(Yf.forEach(e=>t._eventManager.removeHandler(e,n,this)),this._pointerUpEventsRegistered&&(Qf.forEach(e=>n.removeEventListener(e,this,Kf)),this._pointerUpEventsRegistered=!1))}};function Xx(t,n,e){let i=Math.max(Math.abs(t-e.left),Math.abs(t-e.right)),r=Math.max(Math.abs(n-e.top),Math.abs(n-e.bottom));return Math.sqrt(i*i+r*r)}var Fi=new A("mat-ripple-global-options"),Zs=(()=>{class t{_elementRef=m(Y);_animationsDisabled=He();color;unbounded=!1;centered=!1;radius=0;animation;get disabled(){return this._disabled}set disabled(e){e&&this.fadeOutAllNonPersistent(),this._disabled=e,this._setupTriggerEventsIfEnabled()}_disabled=!1;get trigger(){return this._trigger||this._elementRef.nativeElement}set trigger(e){this._trigger=e,this._setupTriggerEventsIfEnabled()}_trigger;_rippleRenderer;_globalOptions;_isInitialized=!1;constructor(){let e=m(K),i=m(we),r=m(Fi,{optional:!0}),o=m(te);this._globalOptions=r||{},this._rippleRenderer=new Pi(this,e,this._elementRef,i,o)}ngOnInit(){this._isInitialized=!0,this._setupTriggerEventsIfEnabled()}ngOnDestroy(){this._rippleRenderer._removeTriggerEvents()}fadeOutAll(){this._rippleRenderer.fadeOutAll()}fadeOutAllNonPersistent(){this._rippleRenderer.fadeOutAllNonPersistent()}get rippleConfig(){return{centered:this.centered,radius:this.radius,color:this.color,animation:M(M(M({},this._globalOptions.animation),this._animationsDisabled?{enterDuration:0,exitDuration:0}:{}),this.animation),terminateOnPointerUp:this._globalOptions.terminateOnPointerUp}}get rippleDisabled(){return this.disabled||!!this._globalOptions.disabled}_setupTriggerEventsIfEnabled(){!this.disabled&&this._isInitialized&&this._rippleRenderer.setupTriggerEvents(this.trigger)}launch(e,i=0,r){return typeof e=="number"?this._rippleRenderer.fadeInRipple(e,i,M(M({},this.rippleConfig),r)):this._rippleRenderer.fadeInRipple(0,0,M(M({},this.rippleConfig),e))}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","mat-ripple",""],["","matRipple",""]],hostAttrs:[1,"mat-ripple"],hostVars:2,hostBindings:function(i,r){i&2&&Q("mat-ripple-unbounded",r.unbounded)},inputs:{color:[0,"matRippleColor","color"],unbounded:[0,"matRippleUnbounded","unbounded"],centered:[0,"matRippleCentered","centered"],radius:[0,"matRippleRadius","radius"],animation:[0,"matRippleAnimation","animation"],disabled:[0,"matRippleDisabled","disabled"],trigger:[0,"matRippleTrigger","trigger"]},exportAs:["matRipple"]})}return t})();var Zx={capture:!0},Jx=["focus","mousedown","mouseenter","touchstart"],Ad="mat-ripple-loader-uninitialized",Id="mat-ripple-loader-class-name",Xf="mat-ripple-loader-centered",Js="mat-ripple-loader-disabled",el=(()=>{class t{_document=m(W);_animationsDisabled=He();_globalRippleOptions=m(Fi,{optional:!0});_platform=m(we);_ngZone=m(K);_injector=m(te);_eventCleanups;_hosts=new Map;constructor(){let e=m(bt).createRenderer(null,null);this._eventCleanups=this._ngZone.runOutsideAngular(()=>Jx.map(i=>e.listen(this._document,i,this._onInteraction,Zx)))}ngOnDestroy(){let e=this._hosts.keys();for(let i of e)this.destroyRipple(i);this._eventCleanups.forEach(i=>i())}configureRipple(e,i){e.setAttribute(Ad,this._globalRippleOptions?.namespace??""),(i.className||!e.hasAttribute(Id))&&e.setAttribute(Id,i.className||""),i.centered&&e.setAttribute(Xf,""),i.disabled&&e.setAttribute(Js,"")}setDisabled(e,i){let r=this._hosts.get(e);r?(r.target.rippleDisabled=i,!i&&!r.hasSetUpEvents&&(r.hasSetUpEvents=!0,r.renderer.setupTriggerEvents(e))):i?e.setAttribute(Js,""):e.removeAttribute(Js)}_onInteraction=e=>{let i=At(e);if(i instanceof HTMLElement){let r=i.closest(`[${Ad}="${this._globalRippleOptions?.namespace??""}"]`);r&&this._createRipple(r)}};_createRipple(e){if(!this._document||this._hosts.has(e))return;e.querySelector(".mat-ripple")?.remove();let i=this._document.createElement("span");i.classList.add("mat-ripple",e.getAttribute(Id)),e.append(i);let r=this._globalRippleOptions,o=this._animationsDisabled?0:r?.animation?.enterDuration??Ko.enterDuration,a=this._animationsDisabled?0:r?.animation?.exitDuration??Ko.exitDuration,s={rippleDisabled:this._animationsDisabled||r?.disabled||e.hasAttribute(Js),rippleConfig:{centered:e.hasAttribute(Xf),terminateOnPointerUp:r?.terminateOnPointerUp,animation:{enterDuration:o,exitDuration:a}}},l=new Pi(s,this._ngZone,i,this._platform,this._injector),c=!s.rippleDisabled;c&&l.setupTriggerEvents(e),this._hosts.set(e,{target:s,renderer:l,hasSetUpEvents:c}),e.removeAttribute(Ad)}destroyRipple(e){let i=this._hosts.get(e);i&&(i.renderer._removeTriggerEvents(),this._hosts.delete(e))}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var an=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["structural-styles"]],decls:0,vars:0,template:function(i,r){},styles:[`.mat-focus-indicator {
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
`],encapsulation:2,changeDetection:0})}return t})();var ew=new A("MAT_BUTTON_CONFIG");function Zf(t){return t==null?void 0:xo(t)}var Jf=(()=>{class t{_elementRef=m(Y);_ngZone=m(K);_animationsDisabled=He();_config=m(ew,{optional:!0});_focusMonitor=m(xn);_cleanupClick;_renderer=m(rt);_rippleLoader=m(el);_isAnchor;_isFab=!1;color;get disableRipple(){return this._disableRipple}set disableRipple(e){this._disableRipple=e,this._updateRippleDisabled()}_disableRipple=!1;get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._updateRippleDisabled()}_disabled=!1;ariaDisabled;disabledInteractive;tabIndex;set _tabindex(e){this.tabIndex=e}constructor(){m(Je).load(an);let e=this._elementRef.nativeElement;this._isAnchor=e.tagName==="A",this.disabledInteractive=this._config?.disabledInteractive??!1,this.color=this._config?.color??null,this._rippleLoader?.configureRipple(e,{className:"mat-mdc-button-ripple"})}ngAfterViewInit(){this._focusMonitor.monitor(this._elementRef,!0),this._isAnchor&&this._setupAsAnchor()}ngOnDestroy(){this._cleanupClick?.(),this._focusMonitor.stopMonitoring(this._elementRef),this._rippleLoader?.destroyRipple(this._elementRef.nativeElement)}focus(e="program",i){e?this._focusMonitor.focusVia(this._elementRef.nativeElement,e,i):this._elementRef.nativeElement.focus(i)}_getAriaDisabled(){return this.ariaDisabled!=null?this.ariaDisabled:this._isAnchor?this.disabled||null:this.disabled&&this.disabledInteractive?!0:null}_getDisabledAttribute(){return this.disabledInteractive||!this.disabled?null:!0}_updateRippleDisabled(){this._rippleLoader?.setDisabled(this._elementRef.nativeElement,this.disableRipple||this.disabled)}_getTabIndex(){return this._isAnchor?this.disabled&&!this.disabledInteractive?-1:this.tabIndex:this.tabIndex}_setupAsAnchor(){this._cleanupClick=this._ngZone.runOutsideAngular(()=>this._renderer.listen(this._elementRef.nativeElement,"click",e=>{this.disabled&&(e.preventDefault(),e.stopImmediatePropagation())}))}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,hostAttrs:[1,"mat-mdc-button-base"],hostVars:13,hostBindings:function(i,r){i&2&&(le("disabled",r._getDisabledAttribute())("aria-disabled",r._getAriaDisabled())("tabindex",r._getTabIndex()),vt(r.color?"mat-"+r.color:""),Q("mat-mdc-button-disabled",r.disabled)("mat-mdc-button-disabled-interactive",r.disabledInteractive)("mat-unthemed",!r.color)("_mat-animation-noopable",r._animationsDisabled))},inputs:{color:"color",disableRipple:[2,"disableRipple","disableRipple",Se],disabled:[2,"disabled","disabled",Se],ariaDisabled:[2,"aria-disabled","ariaDisabled",Se],disabledInteractive:[2,"disabledInteractive","disabledInteractive",Se],tabIndex:[2,"tabIndex","tabIndex",Zf],_tabindex:[2,"tabindex","_tabindex",Zf]}})}return t})();var wn=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[ie]})}return t})();var tw=["matButton",""],nw=[[["",8,"material-icons",3,"iconPositionEnd",""],["mat-icon",3,"iconPositionEnd",""],["","matButtonIcon","",3,"iconPositionEnd",""]],"*",[["","iconPositionEnd","",8,"material-icons"],["mat-icon","iconPositionEnd",""],["","matButtonIcon","","iconPositionEnd",""]]],iw=[".material-icons:not([iconPositionEnd]), mat-icon:not([iconPositionEnd]), [matButtonIcon]:not([iconPositionEnd])","*",".material-icons[iconPositionEnd], mat-icon[iconPositionEnd], [matButtonIcon][iconPositionEnd]"];var eg=new Map([["text",["mat-mdc-button"]],["filled",["mdc-button--unelevated","mat-mdc-unelevated-button"]],["elevated",["mdc-button--raised","mat-mdc-raised-button"]],["outlined",["mdc-button--outlined","mat-mdc-outlined-button"]],["tonal",["mat-tonal-button"]]]),kr=(()=>{class t extends Jf{get appearance(){return this._appearance}set appearance(e){this.setAppearance(e||this._config?.defaultAppearance||"text")}_appearance=null;constructor(){super();let e=rw(this._elementRef.nativeElement);e&&this.setAppearance(e)}setAppearance(e){if(e===this._appearance)return;let i=this._elementRef.nativeElement.classList,r=this._appearance?eg.get(this._appearance):null,o=eg.get(e);r&&i.remove(...r),i.add(...o),this._appearance=e}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["button","matButton",""],["a","matButton",""],["button","mat-button",""],["button","mat-raised-button",""],["button","mat-flat-button",""],["button","mat-stroked-button",""],["a","mat-button",""],["a","mat-raised-button",""],["a","mat-flat-button",""],["a","mat-stroked-button",""]],hostAttrs:[1,"mdc-button"],inputs:{appearance:[0,"matButton","appearance"]},exportAs:["matButton","matAnchor"],features:[Te],attrs:tw,ngContentSelectors:iw,decls:7,vars:4,consts:[[1,"mat-mdc-button-persistent-ripple"],[1,"mdc-button__label"],[1,"mat-focus-indicator"],[1,"mat-mdc-button-touch-target"]],template:function(i,r){i&1&&(ne(nw),Jt(0,"span",0),B(1),Ue(2,"span",1),B(3,1),Qe(),B(4,2),Jt(5,"span",2)(6,"span",3)),i&2&&Q("mdc-button__ripple",!r._isFab)("mdc-fab__ripple",r._isFab)},styles:[`.mat-mdc-button-base {
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
`],encapsulation:2,changeDetection:0})}return t})();function rw(t){return t.hasAttribute("mat-raised-button")?"elevated":t.hasAttribute("mat-stroked-button")?"outlined":t.hasAttribute("mat-flat-button")?"filled":t.hasAttribute("mat-button")?"text":null}var jn=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[wn,ie]})}return t})();function ow(t,n){if(t&1){let e=ot();b(0,"div",1)(1,"button",2),pe("click",function(){Ke(e);let r=C();return Ye(r.action())}),w(2),f()()}if(t&2){let e=C();h(2),J(" ",e.data.action," ")}}var aw=["label"];function sw(t,n){}var lw=Math.pow(2,31)-1,Yo=class{_overlayRef;instance;containerInstance;_afterDismissed=new z;_afterOpened=new z;_onAction=new z;_durationTimeoutId;_dismissedByAction=!1;constructor(n,e){this._overlayRef=e,this.containerInstance=n,n._onExit.subscribe(()=>this._finishDismiss())}dismiss(){this._afterDismissed.closed||this.containerInstance.exit(),clearTimeout(this._durationTimeoutId)}dismissWithAction(){this._onAction.closed||(this._dismissedByAction=!0,this._onAction.next(),this._onAction.complete(),this.dismiss()),clearTimeout(this._durationTimeoutId)}closeWithAction(){this.dismissWithAction()}_dismissAfter(n){this._durationTimeoutId=setTimeout(()=>this.dismiss(),Math.min(n,lw))}_open(){this._afterOpened.closed||(this._afterOpened.next(),this._afterOpened.complete())}_finishDismiss(){this._overlayRef.dispose(),this._onAction.closed||this._onAction.complete(),this._afterDismissed.next({dismissedByAction:this._dismissedByAction}),this._afterDismissed.complete(),this._dismissedByAction=!1}afterDismissed(){return this._afterDismissed}afterOpened(){return this.containerInstance._onEnter}onAction(){return this._onAction}},ng=new A("MatSnackBarData"),Dr=class{politeness="polite";announcementMessage="";viewContainerRef;duration=0;panelClass;direction;data=null;horizontalPosition="center";verticalPosition="bottom"},cw=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","matSnackBarLabel",""]],hostAttrs:[1,"mat-mdc-snack-bar-label","mdc-snackbar__label"]})}return t})(),dw=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","matSnackBarActions",""]],hostAttrs:[1,"mat-mdc-snack-bar-actions","mdc-snackbar__actions"]})}return t})(),mw=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","matSnackBarAction",""]],hostAttrs:[1,"mat-mdc-snack-bar-action","mdc-snackbar__action"]})}return t})(),ig=(()=>{class t{snackBarRef=m(Yo);data=m(ng);constructor(){}action(){this.snackBarRef.dismissWithAction()}get hasAction(){return!!this.data.action}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["simple-snack-bar"]],hostAttrs:[1,"mat-mdc-simple-snack-bar"],exportAs:["matSnackBar"],decls:3,vars:2,consts:[["matSnackBarLabel",""],["matSnackBarActions",""],["matButton","","matSnackBarAction","",3,"click"]],template:function(i,r){i&1&&(b(0,"div",0),w(1),f(),k(2,ow,3,1,"div",1)),i&2&&(h(),J(" ",r.data.message,`
`),h(),D(r.hasAction?2:-1))},dependencies:[kr,cw,dw,mw],styles:[`.mat-mdc-simple-snack-bar {
  display: flex;
}
.mat-mdc-simple-snack-bar .mat-mdc-snack-bar-label {
  max-height: 50vh;
  overflow: auto;
}
`],encapsulation:2,changeDetection:0})}return t})(),Td="_mat-snack-bar-enter",Od="_mat-snack-bar-exit",uw=(()=>{class t extends xr{_ngZone=m(K);_elementRef=m(Y);_changeDetectorRef=m(qe);_platform=m(we);_animationsDisabled=He();snackBarConfig=m(Dr);_document=m(W);_trackedModals=new Set;_enterFallback;_exitFallback;_injector=m(te);_announceDelay=150;_announceTimeoutId;_destroyed=!1;_portalOutlet;_onAnnounce=new z;_onExit=new z;_onEnter=new z;_animationState="void";_live;_label;_role;_liveElementId=m(ut).getId("mat-snack-bar-container-live-");constructor(){super();let e=this.snackBarConfig;e.politeness==="assertive"&&!e.announcementMessage?this._live="assertive":e.politeness==="off"?this._live="off":this._live="polite",this._platform.FIREFOX&&(this._live==="polite"&&(this._role="status"),this._live==="assertive"&&(this._role="alert"))}attachComponentPortal(e){this._assertNotAttached();let i=this._portalOutlet.attachComponentPortal(e);return this._afterPortalAttached(),i}attachTemplatePortal(e){this._assertNotAttached();let i=this._portalOutlet.attachTemplatePortal(e);return this._afterPortalAttached(),i}attachDomPortal=e=>{this._assertNotAttached();let i=this._portalOutlet.attachDomPortal(e);return this._afterPortalAttached(),i};onAnimationEnd(e){e===Od?this._completeExit():e===Td&&(clearTimeout(this._enterFallback),this._ngZone.run(()=>{this._onEnter.next(),this._onEnter.complete()}))}enter(){this._destroyed||(this._animationState="visible",this._changeDetectorRef.markForCheck(),this._changeDetectorRef.detectChanges(),this._screenReaderAnnounce(),this._animationsDisabled?mt(()=>{this._ngZone.run(()=>queueMicrotask(()=>this.onAnimationEnd(Td)))},{injector:this._injector}):(clearTimeout(this._enterFallback),this._enterFallback=setTimeout(()=>{this._elementRef.nativeElement.classList.add("mat-snack-bar-fallback-visible"),this.onAnimationEnd(Td)},200)))}exit(){return this._destroyed?Pe(void 0):(this._ngZone.run(()=>{this._animationState="hidden",this._changeDetectorRef.markForCheck(),this._elementRef.nativeElement.setAttribute("mat-exit",""),clearTimeout(this._announceTimeoutId),this._animationsDisabled?mt(()=>{this._ngZone.run(()=>queueMicrotask(()=>this.onAnimationEnd(Od)))},{injector:this._injector}):(clearTimeout(this._exitFallback),this._exitFallback=setTimeout(()=>this.onAnimationEnd(Od),200))}),this._onExit)}ngOnDestroy(){this._destroyed=!0,this._clearFromModals(),this._completeExit()}_completeExit(){clearTimeout(this._exitFallback),queueMicrotask(()=>{this._onExit.next(),this._onExit.complete()})}_afterPortalAttached(){let e=this._elementRef.nativeElement,i=this.snackBarConfig.panelClass;i&&(Array.isArray(i)?i.forEach(a=>e.classList.add(a)):e.classList.add(i)),this._exposeToModals();let r=this._label.nativeElement,o="mdc-snackbar__label";r.classList.toggle(o,!r.querySelector(`.${o}`))}_exposeToModals(){let e=this._liveElementId,i=this._document.querySelectorAll('body > .cdk-overlay-container [aria-modal="true"]');for(let r=0;r<i.length;r++){let o=i[r],a=o.getAttribute("aria-owns");this._trackedModals.add(o),a?a.indexOf(e)===-1&&o.setAttribute("aria-owns",a+" "+e):o.setAttribute("aria-owns",e)}}_clearFromModals(){this._trackedModals.forEach(e=>{let i=e.getAttribute("aria-owns");if(i){let r=i.replace(this._liveElementId,"").trim();r.length>0?e.setAttribute("aria-owns",r):e.removeAttribute("aria-owns")}}),this._trackedModals.clear()}_assertNotAttached(){this._portalOutlet.hasAttached()}_screenReaderAnnounce(){this._announceTimeoutId||this._ngZone.runOutsideAngular(()=>{this._announceTimeoutId=setTimeout(()=>{if(this._destroyed)return;let e=this._elementRef.nativeElement,i=e.querySelector("[aria-hidden]"),r=e.querySelector("[aria-live]");if(i&&r){let o=null;this._platform.isBrowser&&document.activeElement instanceof HTMLElement&&i.contains(document.activeElement)&&(o=document.activeElement),i.removeAttribute("aria-hidden"),r.appendChild(i),o?.focus(),this._onAnnounce.next(),this._onAnnounce.complete()}},this._announceDelay)})}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-snack-bar-container"]],viewQuery:function(i,r){if(i&1&&Ne(zs,7)(aw,7),i&2){let o;H(o=U())&&(r._portalOutlet=o.first),H(o=U())&&(r._label=o.first)}},hostAttrs:[1,"mdc-snackbar","mat-mdc-snack-bar-container"],hostVars:6,hostBindings:function(i,r){i&1&&pe("animationend",function(a){return r.onAnimationEnd(a.animationName)})("animationcancel",function(a){return r.onAnimationEnd(a.animationName)}),i&2&&Q("mat-snack-bar-container-enter",r._animationState==="visible")("mat-snack-bar-container-exit",r._animationState==="hidden")("mat-snack-bar-container-animations-enabled",!r._animationsDisabled)},features:[Te],decls:6,vars:3,consts:[["label",""],[1,"mdc-snackbar__surface","mat-mdc-snackbar-surface"],[1,"mat-mdc-snack-bar-label"],["aria-hidden","true"],["cdkPortalOutlet",""]],template:function(i,r){i&1&&(b(0,"div",1)(1,"div",2,0)(3,"div",3),jt(4,sw,0,0,"ng-template",4),f(),P(5,"div"),f()()),i&2&&(h(5),le("aria-live",r._live)("role",r._role)("id",r._liveElementId))},dependencies:[zs],styles:[`@keyframes _mat-snack-bar-enter {
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
`],encapsulation:2})}return t})(),pw=new A("mat-snack-bar-default-options",{providedIn:"root",factory:()=>new Dr}),Qo=(()=>{class t{_live=m(fd);_injector=m(te);_breakpointObserver=m(pd);_parentSnackBar=m(t,{optional:!0,skipSelf:!0});_defaultConfig=m(pw);_animationsDisabled=He();_snackBarRefAtThisLevel=null;simpleSnackBarComponent=ig;snackBarContainerComponent=uw;handsetCssClass="mat-mdc-snack-bar-handset";get _openedSnackBarRef(){let e=this._parentSnackBar;return e?e._openedSnackBarRef:this._snackBarRefAtThisLevel}set _openedSnackBarRef(e){this._parentSnackBar?this._parentSnackBar._openedSnackBarRef=e:this._snackBarRefAtThisLevel=e}constructor(){}openFromComponent(e,i){return this._attach(e,i)}openFromTemplate(e,i){return this._attach(e,i)}open(e,i="",r){let o=M(M({},this._defaultConfig),r);return o.data={message:e,action:i},o.announcementMessage===e&&(o.announcementMessage=void 0),this.openFromComponent(this.simpleSnackBarComponent,o)}dismiss(){this._openedSnackBarRef&&this._openedSnackBarRef.dismiss()}ngOnDestroy(){this._snackBarRefAtThisLevel&&this._snackBarRefAtThisLevel.dismiss()}_attachSnackBarContainer(e,i){let r=i&&i.viewContainerRef&&i.viewContainerRef.injector,o=te.create({parent:r||this._injector,providers:[{provide:Dr,useValue:i}]}),a=new yr(this.snackBarContainerComponent,i.viewContainerRef,o),s=e.attach(a);return s.instance.snackBarConfig=i,s.instance}_attach(e,i){let r=M(M(M({},new Dr),this._defaultConfig),i),o=this._createOverlay(r),a=this._attachSnackBarContainer(o,r),s=new Yo(a,o);if(e instanceof Jn){let l=new $n(e,null,{$implicit:r.data,snackBarRef:s});s.instance=a.attachTemplatePortal(l)}else{let l=this._createInjector(r,s),c=new yr(e,void 0,l),d=a.attachComponentPortal(c);s.instance=d.instance}return this._breakpointObserver.observe(Mf.HandsetPortrait).pipe(ke(o.detachments())).subscribe(l=>{o.overlayElement.classList.toggle(this.handsetCssClass,l.matches)}),r.announcementMessage&&a._onAnnounce.subscribe(()=>{this._live.announce(r.announcementMessage,r.politeness)}),this._animateSnackBar(s,r),this._openedSnackBarRef=s,this._openedSnackBarRef}_animateSnackBar(e,i){e.afterDismissed().subscribe(()=>{this._openedSnackBarRef==e&&(this._openedSnackBarRef=null),i.announcementMessage&&this._live.clear()}),i.duration&&i.duration>0&&e.afterOpened().subscribe(()=>e._dismissAfter(i.duration)),this._openedSnackBarRef?(this._openedSnackBarRef.afterDismissed().subscribe(()=>{e.containerInstance.enter()}),this._openedSnackBarRef.dismiss()):e.containerInstance.enter()}_createOverlay(e){let i=new ai;i.direction=e.direction;let r=Xs(this._injector),o=e.direction==="rtl",a=e.horizontalPosition==="left"||e.horizontalPosition==="start"&&!o||e.horizontalPosition==="end"&&o,s=!a&&e.horizontalPosition!=="center";return a?r.left("0"):s?r.right("0"):r.centerHorizontally(),e.verticalPosition==="top"?r.top("0"):r.bottom("0"),i.positionStrategy=r,i.disableAnimations=this._animationsDisabled,Cr(this._injector,i)}_createInjector(e,i){let r=e&&e.viewContainerRef&&e.viewContainerRef.injector;return te.create({parent:r||this._injector,providers:[{provide:Yo,useValue:i},{provide:ng,useValue:e.data}]})}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var Rd=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({providers:[Qo],imports:[Ri,wr,jn,ig,ie]})}return t})();var si=class{},tl=class t{constructor(n){this.snackBar=n}showError(n){this.snackBar.open(n,"Close",{verticalPosition:"top"})}showWarning(n){this.snackBar.open(n,"Close",{duration:3e3})}static \u0275fac=function(e){return new(e||t)(j(Qo))};static \u0275prov=E({token:t,factory:t.\u0275fac})};var pt=class t{constructor(n){this.notificationService=n}static BASE_URL=window.location.pathname+window.location.search+"#";toAsyncApi(n){try{let e=this.mapChannels(n.channels,n.operations,n.components.messages,n.components.schemas,n.servers,n.defaultContentType),i={info:this.mapInfo(n),servers:this.mapServers(n.servers),channels:e,channelOperations:e.flatMap(r=>r.operations),components:{schemas:this.mapSchemas(n.components.schemas)},defaultContentType:n.defaultContentType};return this.postProcess(i),i}catch(e){this.notificationService.showError("Error parsing AsyncAPI: "+e?.message);return}}mapInfo(n){return{title:n.info.title,version:n.info.version,description:n.info.description,contact:{url:n.info.contact?.url,email:n.info.contact?.email&&{name:n.info.contact.email,href:"mailto:"+n.info.contact.email}||void 0},license:{name:n.info.license?.name,url:n.info.license?.url},asyncApiJson:n}}mapServers(n){let e=new Map;if(n)for(let i in n){let r=n[i];e.set(i,{host:r.host,protocol:r.protocol,anchorIdentifier:Vo+i,anchorUrl:t.BASE_URL+Vo+i})}return e}mapChannels(n,e,i,r,o,a){let s={};for(let l in n)this.parsingErrorBoundary("channel "+l,()=>{let c=n[l],d="channel-"+this.toSafeAnchorIdentifier(l);s[l]={name:c.address,anchorIdentifier:d,anchorUrl:t.BASE_URL+d,operations:[],bindings:c.bindings||{}}});for(let l in e)this.parsingErrorBoundary("operation "+l,()=>{let c=e[l],d=this.resolveRefId(c.channel.$ref),u=n[d].address;this.verifyBindings(c.bindings,"operation "+l),this.mapServerAsyncApiMessages(u,n[d],i,r,c.messages,a).forEach(y=>{let g=this.parsingErrorBoundary("channel with name "+u,()=>this.mapChannelOperation(u,n[d],n,c,y,o));g!=null&&s[d].operations.push(g)})});return Object.values(s).forEach(l=>{l.operations=l.operations.sort((c,d)=>c.operation.protocol===d.operation.protocol?c.operation.operationType===d.operation.operationType?c.name===d.name?c.operation.message.name.localeCompare(d.operation.message.name):c.name.localeCompare(d.name):c.operation.operationType.localeCompare(d.operation.operationType):c.operation.protocol!=null&&d.operation.protocol!=null?c.operation.protocol.localeCompare(d.operation.protocol):0)}),Object.values(s).sort((l,c)=>l.name.localeCompare(c.name))}mapChannelOperation(n,e,i,r,o,a){let l=(e?.servers?.map(u=>this.resolveRefId(u.$ref))||a&&Object.keys(a)||[]).map(u=>({name:u,anchorIdentifier:Vo+u,anchorUrl:t.BASE_URL+Vo+u})),c=this.mapOperation(n,i,r.action,l,o,r.bindings,r.description,r.reply),d=sd+[c.protocol,this.toSafeAnchorIdentifier(n),c.operationType,c.message.title].join("-");return{name:n,anchorIdentifier:d,anchorUrl:t.BASE_URL+d,description:e.description,operation:c,bindings:e.bindings||{}}}mapServerAsyncApiMessages(n,e,i,r,o,a){return o.map(s=>this.parsingErrorBoundary("message of channel "+n,()=>{let l=this.resolveRefId(s.$ref),c=this.resolveTitleFromName(l),d=e.messages[l],u=this.resolveRefId(d.$ref),p=i[u];return this.verifyBindings(p.bindings,"message "+p.name),{name:p.name||c,title:p.title||c,description:p.description,contentType:p.contentType||a,payload:this.mapPayload(p.name||c,p.payload.schema,r),headers:this.mapHeaders(p.headers),bindings:this.mapServerAsyncApiMessageBindings(p.bindings),rawBindings:p.bindings||{}}})).filter(s=>s!==void 0)}mapHeaders(n){if(n===void 0)return;let e=this.resolveRefId(n.$ref);return{ts_type:"ref",name:e,title:e,anchorUrl:t.BASE_URL+e}}mapPayload(n,e,i){if("$ref"in e){let r=this.resolveRefId(e.$ref);return{ts_type:"ref",name:r,title:this.resolveTitleFromName(r),anchorUrl:t.BASE_URL+r}}return this.mapSchemaObj(n,e,i)}mapServerAsyncApiMessageBindings(n){let e=new Map;return n!==void 0&&Object.keys(n).forEach(i=>{e.set(i,this.mapServerAsyncApiMessageBinding(n[i]))}),e}mapServerAsyncApiMessageBinding(n){let e={};return Object.keys(n).forEach(i=>{let r=n[i];typeof r=="object"?e[i]=this.mapServerAsyncApiMessageBinding(r):e[i]=r}),e}mapOperation(n,e,i,r,o,a,s,l){return{protocol:this.getProtocol(a)||"unsupported-protocol",bindings:a||{},servers:r,operationType:i=="send"?"send":"receive",channelName:n,description:s,message:o,reply:this.mapOperationReply(l,e)}}mapOperationReply(n,e){if(!n)return;let i=this.resolveRefId(n.channel.$ref),r=e[i].address,o=this.resolveRefId(n.messages[0].$ref);return{channelAnchorUrl:t.BASE_URL+sd+i,channelName:r,messageAnchorUrl:t.BASE_URL+o,messageName:o}}getProtocol(n){if(n!==void 0)return Object.keys(n)[0]}mapSchemas(n){let e=new Map;return Object.entries(n).forEach(([i,r])=>{let o=this.parsingErrorBoundary("schema with name "+i,()=>this.mapSchema(i,r,n));o!=null&&e.set(i,o)}),new Map([...e.entries()].sort((i,r)=>i[1].title.localeCompare(r[1].title)))}mapSchema(n,e,i){return"$ref"in e?this.mapSchemaRef(n,e):this.mapSchemaObj(n,e,i)}mapSchemaObj(n,e,i){let r={};this.addPropertiesToSchema(e,r,i),e.allOf!==void 0&&e.allOf.forEach(s=>{this.addPropertiesToSchema(s,r,i)}),e.anyOf!==void 0&&e.anyOf.length>0&&this.addPropertiesToSchema(e.anyOf[0],r,i),e.oneOf!==void 0&&e.oneOf.length>0&&this.addPropertiesToSchema(e.oneOf[0],r,i);let o=e.items!==void 0?this.mapSchema(n+"[]",e.items,i):void 0,a=e.examples!==void 0&&0<e.examples.length?new Ht(e.examples[0]):void 0;return{ts_type:"object",name:n,title:this.resolveTitleFromName(n)||"undefined-title",usedBy:[],anchorIdentifier:this.toSafeAnchorIdentifier(n),anchorUrl:t.BASE_URL+n,description:e.description,deprecated:e.deprecated,enum:e.enum,example:a,type:e.type,format:e.format,properties:r,required:e.required,items:o,minItems:e.minItems,maxItems:e.maxItems,uniqueItems:e.uniqueItems,minLength:e.minLength,maxLength:e.maxLength,pattern:e.pattern,minimum:e.exclusiveMinimum?e.exclusiveMinimum:e.minimum,maximum:e.exclusiveMaximum?e.exclusiveMinimum:e.maximum,exclusiveMinimum:e.minimum==e.exclusiveMinimum,exclusiveMaximum:e.maximum==e.exclusiveMaximum,multipleOf:e.multipleOf}}addPropertiesToSchema(n,e,i){let r=this.resolveSchema(n,i);"properties"in r&&r.properties!==void 0&&Object.entries(r.properties).forEach(([o,a])=>{e[o]=this.mapSchema(o,a,i)})}resolveSchema(n,e){let i=n;for(;"$ref"in i;){let r=this.resolveRefId(i.$ref),o=e[r];if(o!==void 0)i=o;else throw new Error("Schema "+r+" not found")}return i}mapSchemaRef(n,e){let i=this.resolveRefId(e.$ref);return{ts_type:"object",name:n,title:this.resolveTitleFromName(n),usedBy:[],anchorIdentifier:this.toSafeAnchorIdentifier(n),anchorUrl:t.BASE_URL+n,refAnchorUrl:t.BASE_URL+i,refName:i,refTitle:this.resolveTitleFromName(i)}}resolveRefId(n){return n.split("/").pop()}resolveTitleFromName(n){return n.split(".").pop()}verifyBindings(n,e){(n==null||Object.keys(n).length==0)&&this.notificationService.showWarning("No binding defined for "+e)}postProcess(n){n.components.schemas.forEach(e=>{n.channels.forEach(i=>{i.operations.forEach(r=>{r.operation.message.payload.name===e.name&&e.usedBy.push({name:r.name,anchorUrl:r.anchorUrl,type:"channel"}),r.operation.message.headers?.name===e.name&&e.usedBy.push({name:r.name,anchorUrl:r.anchorUrl,type:"channel"})})}),n.components.schemas.forEach(i=>{Object.values(i?.properties||{}).forEach(r=>{r.refName===e.name&&e.usedBy.push({name:i.title,anchorUrl:i.anchorUrl,type:"schema"})}),i.items?.refName===e.name&&e.usedBy.push({name:i.title,anchorUrl:i.anchorUrl,type:"schema"})})})}toSafeAnchorIdentifier(n){return n.replaceAll(/\$/g,"\xA7")}parsingErrorBoundary(n,e){return cf(e,i=>{this.notificationService.showError("Error parsing AsyncAPI "+n+": "+i.message)})}static \u0275fac=function(e){return new(e||t)(j(si))};static \u0275prov=E({token:t,factory:t.\u0275fac})};var zb=Yv(Vb());var Ll={$schema:"http://json-schema.org/draft-07/schema#",$ref:"#/definitions/ServerAsyncApi",definitions:{ServerAsyncApi:{type:"object",properties:{asyncapi:{type:"string"},info:{$ref:"#/definitions/ServerAsyncApiInfo"},defaultContentType:{type:"string"},servers:{$ref:"#/definitions/ServerServers"},channels:{$ref:"#/definitions/ServerChannels"},operations:{$ref:"#/definitions/ServerOperations"},components:{$ref:"#/definitions/ServerComponents"}},required:["asyncapi","info","defaultContentType","channels","operations","components"],additionalProperties:!1},ServerAsyncApiInfo:{type:"object",properties:{title:{type:"string"},version:{type:"string"},description:{type:"string"},contact:{type:"object",properties:{name:{type:"string"},url:{type:"string"},email:{type:"string"}},additionalProperties:{}},license:{type:"object",properties:{name:{type:"string"},url:{type:"string"}},additionalProperties:{}},termsOfService:{type:"string"}},required:["title"],additionalProperties:{}},ServerServers:{type:"object",additionalProperties:{type:"object",properties:{host:{type:"string"},protocol:{type:"string"},description:{type:"string"}},required:["host","protocol"],additionalProperties:!1}},ServerChannels:{type:"object",additionalProperties:{$ref:"#/definitions/ServerChannel"}},ServerChannel:{type:"object",properties:{address:{type:"string"},description:{type:"string"},messages:{type:"object",additionalProperties:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},servers:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},bindings:{$ref:"#/definitions/ServerBindings"}},required:["address"],additionalProperties:!1},ServerBindings:{type:"object",additionalProperties:{$ref:"#/definitions/ServerBinding"}},ServerBinding:{type:"object",additionalProperties:{anyOf:[{$ref:"#/definitions/ServerBinding"},{}]}},ServerOperations:{type:"object",additionalProperties:{$ref:"#/definitions/ServerOperation"}},ServerOperation:{type:"object",properties:{action:{type:"string"},title:{type:"string"},description:{type:"string"},channel:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},messages:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}},reply:{$ref:"#/definitions/ServerOperationReply"},bindings:{$ref:"#/definitions/ServerBindings"}},required:["action","channel","messages"],additionalProperties:!1},ServerOperationReply:{type:"object",properties:{channel:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},messages:{type:"array",items:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}}},required:["channel","messages"],additionalProperties:!1},ServerComponents:{type:"object",properties:{schemas:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiSchema"}},messages:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiMessage"}}},required:["schemas","messages"],additionalProperties:!1},ServerAsyncApiSchema:{type:"object",properties:{title:{type:"string"},description:{type:"string"},deprecated:{type:"boolean"},enum:{type:"array",items:{type:["string","null"]}},examples:{type:"array",items:{}},type:{anyOf:[{type:"string"},{type:"array",items:{type:"string"}}]},format:{type:"string"},not:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"},allOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},anyOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},oneOf:{type:"array",items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},properties:{type:"object",additionalProperties:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"}},required:{type:"array",items:{type:"string"}},items:{$ref:"#/definitions/ServerAsyncApiSchemaOrRef"},minItems:{type:"number"},maxItems:{type:"number"},uniqueItems:{type:"boolean"},minLength:{type:"number"},maxLength:{type:"number"},pattern:{type:"string"},minimum:{type:"number"},maximum:{type:"number"},exclusiveMinimum:{type:"number"},exclusiveMaximum:{type:"number"},multipleOf:{type:"number"}},additionalProperties:!1},ServerAsyncApiSchemaOrRef:{anyOf:[{$ref:"#/definitions/ServerAsyncApiSchema"},{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1}]},ServerAsyncApiMessage:{type:"object",properties:{name:{type:"string"},title:{type:"string"},description:{type:"string"},contentType:{type:"string"},payload:{type:"object",properties:{schemaFormat:{type:"string"},schema:{anyOf:[{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},{$ref:"#/definitions/ServerAsyncApiSchema"}]}},required:["schemaFormat","schema"],additionalProperties:!1},headers:{type:"object",properties:{$ref:{type:"string"}},required:["$ref"],additionalProperties:!1},bindings:{$ref:"#/definitions/ServerBindings"}},required:["payload"],additionalProperties:!1}}};var Vr=class t{constructor(n){this.notificationService=n}ajv=new zb.default({allErrors:!0});_logToConsole=!0;validate(n){let e=JSON.parse(JSON.stringify(n));this.ajv.opts.removeAdditional=!1;let i=this.ajv.compile(Ll);i(e)||this._logToConsole&&console.info("Validation error while parsing AsyncAPI file in Springwolf format (strict mode)",i.errors),this.ajv.removeSchema(Ll),this.ajv.opts.removeAdditional=!0;let o=this.ajv.compile(Ll);o(e)||(this.notificationService.showError("Validation error while parsing AsyncAPI file in Springwolf format (lenient mode), see console logs for details."),this._logToConsole&&console.warn("Validation error while parsing AsyncAPI file in Springwolf format (lenient mode)",o.errors))}static \u0275fac=function(e){return new(e||t)(j(si))};static \u0275prov=E({token:t,factory:t.\u0275fac})};var nt=class t{constructor(n,e,i,r){this.http=n;this.asyncApiMapperService=e;this.uiService=i;this.asyncApiValidatorService=r;this.docs=this.uiService.isGroup$.pipe($t(o=>{let a=o==xe.DEFAULT_GROUP?yn.docs:yn.getDocsForGroupEndpoint(o);return this.http.get(a)}),Xn(o=>this.asyncApiValidatorService.validate(o)),ye(o=>this.asyncApiMapperService.toAsyncApi(o)),Be(o=>o!==void 0),An())}docs;getAsyncApi(){return this.docs}static \u0275fac=function(e){return new(e||t)(j(zt),j(pt),j(xe),j(Vr))};static \u0275prov=E({token:t,factory:t.\u0275fac})};var zr=class t{constructor(n){this.http=n}publishable={};canPublish(n){return this.publishable[n]===void 0&&(this.publishable[n]=this.http.get(yn.getPublishEndpoint(n),{observe:"response"}).pipe(ye(e=>e.status===200),Ua())),this.publishable[n]}publish(n,e,i,r,o,a){let s=yn.getPublishEndpoint(n),l=new Bt().set("topic",e),c={payload:i,type:r,headers:o,bindings:a};return console.log(`Publishing to ${s} with messageBinding ${JSON.stringify(a)} and headers ${JSON.stringify(o)}: ${JSON.stringify(c)}`),this.http.post(s,c,{params:l})}static \u0275fac=function(e){return new(e||t)(j(zt))};static \u0275prov=E({token:t,factory:t.\u0275fac})};var Hb={providers:[zp(),Dh(),zc(Hc()),fs.production?[]:kp(Eh.forRoot(bs,{delay:100})),ad(),{provide:fr,useClass:As},nt,pt,Vr,{provide:si,useClass:tl},zr,{provide:xe,useClass:Is}]};var n1=["mat-menu-item",""],i1=[[["mat-icon"],["","matMenuItemIcon",""]],"*"],r1=["mat-icon, [matMenuItemIcon]","*"];function o1(t,n){t&1&&(Zi(),b(0,"svg",2),P(1,"polygon",3),f())}var a1=["*"];function s1(t,n){if(t&1){let e=ot();Ue(0,"div",0),Xa("click",function(){Ke(e);let r=C();return Ye(r.closed.emit("click"))})("animationstart",function(r){Ke(e);let o=C();return Ye(o._onAnimationStart(r.animationName))})("animationend",function(r){Ke(e);let o=C();return Ye(o._onAnimationDone(r.animationName))})("animationcancel",function(r){Ke(e);let o=C();return Ye(o._onAnimationDone(r.animationName))}),Ue(1,"div",1),B(2),Qe()()}if(t&2){let e=C();vt(e._classList),Q("mat-menu-panel-animations-disabled",e._animationsDisabled)("mat-menu-panel-exit-animation",e._panelAnimationState==="void")("mat-menu-panel-animating",e._isAnimating()),ti("id",e.panelId),le("aria-label",e.ariaLabel||null)("aria-labelledby",e.ariaLabelledby||null)("aria-describedby",e.ariaDescribedby||null)}}var wu=new A("MAT_MENU_PANEL"),xa=(()=>{class t{_elementRef=m(Y);_document=m(W);_focusMonitor=m(xn);_parentMenu=m(wu,{optional:!0});_changeDetectorRef=m(qe);role="menuitem";disabled=!1;disableRipple=!1;_hovered=new z;_focused=new z;_highlighted=!1;_triggersSubmenu=!1;constructor(){m(Je).load(an),this._parentMenu?.addItem?.(this)}focus(e,i){this._focusMonitor&&e?this._focusMonitor.focusVia(this._getHostElement(),e,i):this._getHostElement().focus(i),this._focused.next(this)}ngAfterViewInit(){this._focusMonitor&&this._focusMonitor.monitor(this._elementRef,!1)}ngOnDestroy(){this._focusMonitor&&this._focusMonitor.stopMonitoring(this._elementRef),this._parentMenu&&this._parentMenu.removeItem&&this._parentMenu.removeItem(this),this._hovered.complete(),this._focused.complete()}_getTabIndex(){return this.disabled?"-1":"0"}_getHostElement(){return this._elementRef.nativeElement}_checkDisabled(e){this.disabled&&(e.preventDefault(),e.stopPropagation())}_handleMouseEnter(){this._hovered.next(this)}getLabel(){let e=this._elementRef.nativeElement.cloneNode(!0),i=e.querySelectorAll("mat-icon, .material-icons");for(let r=0;r<i.length;r++)i[r].remove();return e.textContent?.trim()||""}_setHighlighted(e){this._highlighted=e,this._changeDetectorRef.markForCheck()}_setTriggersSubmenu(e){this._triggersSubmenu=e,this._changeDetectorRef.markForCheck()}_hasFocus(){return this._document&&this._document.activeElement===this._getHostElement()}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["","mat-menu-item",""]],hostAttrs:[1,"mat-mdc-menu-item","mat-focus-indicator"],hostVars:8,hostBindings:function(i,r){i&1&&pe("click",function(a){return r._checkDisabled(a)})("mouseenter",function(){return r._handleMouseEnter()}),i&2&&(le("role",r.role)("tabindex",r._getTabIndex())("aria-disabled",r.disabled)("disabled",r.disabled||null),Q("mat-mdc-menu-item-highlighted",r._highlighted)("mat-mdc-menu-item-submenu-trigger",r._triggersSubmenu))},inputs:{role:"role",disabled:[2,"disabled","disabled",Se],disableRipple:[2,"disableRipple","disableRipple",Se]},exportAs:["matMenuItem"],attrs:n1,ngContentSelectors:r1,decls:5,vars:3,consts:[[1,"mat-mdc-menu-item-text"],["matRipple","",1,"mat-mdc-menu-ripple",3,"matRippleDisabled","matRippleTrigger"],["viewBox","0 0 5 10","focusable","false","aria-hidden","true",1,"mat-mdc-menu-submenu-icon"],["points","0,0 5,5 0,10"]],template:function(i,r){i&1&&(ne(i1),B(0),b(1,"span",0),B(2,1),f(),P(3,"div",1),k(4,o1,2,0,":svg:svg",2)),i&2&&(h(3),R("matRippleDisabled",r.disableRipple||r.disabled)("matRippleTrigger",r._getHostElement()),h(),D(r._triggersSubmenu?4:-1))},dependencies:[Zs],encapsulation:2,changeDetection:0})}return t})();var l1=new A("MatMenuContent");var c1=new A("mat-menu-default-options",{providedIn:"root",factory:()=>({overlapTrigger:!1,xPosition:"after",yPosition:"below",backdropClass:"cdk-overlay-transparent-backdrop"})}),yu="_mat-menu-enter",Bl="_mat-menu-exit",Ur=(()=>{class t{_elementRef=m(Y);_changeDetectorRef=m(qe);_injector=m(te);_keyManager;_xPosition;_yPosition;_firstItemFocusRef;_exitFallbackTimeout;_animationsDisabled=He();_allItems;_directDescendantItems=new Tn;_classList={};_panelAnimationState="void";_animationDone=new z;_isAnimating=X(!1);parentMenu;direction;overlayPanelClass;backdropClass;ariaLabel;ariaLabelledby;ariaDescribedby;get xPosition(){return this._xPosition}set xPosition(e){this._xPosition=e,this.setPositionClasses()}get yPosition(){return this._yPosition}set yPosition(e){this._yPosition=e,this.setPositionClasses()}templateRef;items;lazyContent;overlapTrigger=!1;hasBackdrop;set panelClass(e){let i=this._previousPanelClass,r=M({},this._classList);i&&i.length&&i.split(" ").forEach(o=>{r[o]=!1}),this._previousPanelClass=e,e&&e.length&&(e.split(" ").forEach(o=>{r[o]=!0}),this._elementRef.nativeElement.className=""),this._classList=r}_previousPanelClass;get classList(){return this.panelClass}set classList(e){this.panelClass=e}closed=new De;close=this.closed;panelId=m(ut).getId("mat-menu-panel-");constructor(){let e=m(c1);this.overlayPanelClass=e.overlayPanelClass||"",this._xPosition=e.xPosition,this._yPosition=e.yPosition,this.backdropClass=e.backdropClass,this.overlapTrigger=e.overlapTrigger,this.hasBackdrop=e.hasBackdrop}ngOnInit(){this.setPositionClasses()}ngAfterContentInit(){this._updateDirectDescendants(),this._keyManager=new Ai(this._directDescendantItems).withWrap().withTypeAhead().withHomeAndEnd(),this._keyManager.tabOut.subscribe(()=>this.closed.emit("tab")),this._directDescendantItems.changes.pipe(ct(this._directDescendantItems),$t(e=>_t(...e.map(i=>i._focused)))).subscribe(e=>this._keyManager.updateActiveItem(e)),this._directDescendantItems.changes.subscribe(e=>{let i=this._keyManager;if(this._panelAnimationState==="enter"&&i.activeItem?._hasFocus()){let r=e.toArray(),o=Math.max(0,Math.min(r.length-1,i.activeItemIndex||0));r[o]&&!r[o].disabled?i.setActiveItem(o):i.setNextItemActive()}})}ngOnDestroy(){this._keyManager?.destroy(),this._directDescendantItems.destroy(),this.closed.complete(),this._firstItemFocusRef?.destroy(),clearTimeout(this._exitFallbackTimeout)}_hovered(){return this._directDescendantItems.changes.pipe(ct(this._directDescendantItems),$t(i=>_t(...i.map(r=>r._hovered))))}addItem(e){}removeItem(e){}_handleKeydown(e){let i=e.keyCode,r=this._keyManager;switch(i){case 27:oi(e)||(e.preventDefault(),this.closed.emit("keydown"));break;case 37:this.parentMenu&&this.direction==="ltr"&&this.closed.emit("keydown");break;case 39:this.parentMenu&&this.direction==="rtl"&&this.closed.emit("keydown");break;default:(i===38||i===40)&&r.setFocusOrigin("keyboard"),r.onKeydown(e);return}}focusFirstItem(e="program"){this._firstItemFocusRef?.destroy(),this._firstItemFocusRef=mt(()=>{let i=this._resolvePanel();if(!i||!i.contains(document.activeElement)){let r=this._keyManager;r.setFocusOrigin(e).setFirstItemActive(),!r.activeItem&&i&&i.focus()}},{injector:this._injector})}resetActiveItem(){this._keyManager.setActiveItem(-1)}setElevation(e){}setPositionClasses(e=this.xPosition,i=this.yPosition){this._classList=Ie(M({},this._classList),{"mat-menu-before":e==="before","mat-menu-after":e==="after","mat-menu-above":i==="above","mat-menu-below":i==="below"}),this._changeDetectorRef.markForCheck()}_onAnimationDone(e){let i=e===Bl;(i||e===yu)&&(i&&(clearTimeout(this._exitFallbackTimeout),this._exitFallbackTimeout=void 0),this._animationDone.next(i?"void":"enter"),this._isAnimating.set(!1))}_onAnimationStart(e){(e===yu||e===Bl)&&this._isAnimating.set(!0)}_setIsOpen(e){if(this._panelAnimationState=e?"enter":"void",e){if(this._keyManager.activeItemIndex===0){let i=this._resolvePanel();i&&(i.scrollTop=0)}}else this._animationsDisabled||(this._exitFallbackTimeout=setTimeout(()=>this._onAnimationDone(Bl),200));this._animationsDisabled&&setTimeout(()=>{this._onAnimationDone(e?yu:Bl)}),this._changeDetectorRef.markForCheck()}_updateDirectDescendants(){this._allItems.changes.pipe(ct(this._allItems)).subscribe(e=>{this._directDescendantItems.reset(e.filter(i=>i._parentMenu===this)),this._directDescendantItems.notifyOnChanges()})}_resolvePanel(){let e=null;return this._directDescendantItems.length&&(e=this._directDescendantItems.first._getHostElement().closest('[role="menu"]')),e}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-menu"]],contentQueries:function(i,r,o){if(i&1&&Xe(o,l1,5)(o,xa,5)(o,xa,4),i&2){let a;H(a=U())&&(r.lazyContent=a.first),H(a=U())&&(r._allItems=a),H(a=U())&&(r.items=a)}},viewQuery:function(i,r){if(i&1&&Ne(Jn,5),i&2){let o;H(o=U())&&(r.templateRef=o.first)}},hostVars:3,hostBindings:function(i,r){i&2&&le("aria-label",null)("aria-labelledby",null)("aria-describedby",null)},inputs:{backdropClass:"backdropClass",ariaLabel:[0,"aria-label","ariaLabel"],ariaLabelledby:[0,"aria-labelledby","ariaLabelledby"],ariaDescribedby:[0,"aria-describedby","ariaDescribedby"],xPosition:"xPosition",yPosition:"yPosition",overlapTrigger:[2,"overlapTrigger","overlapTrigger",Se],hasBackdrop:[2,"hasBackdrop","hasBackdrop",e=>e==null?null:Se(e)],panelClass:[0,"class","panelClass"],classList:"classList"},outputs:{closed:"closed",close:"close"},exportAs:["matMenu"],features:[ze([{provide:wu,useExisting:t}])],ngContentSelectors:a1,decls:1,vars:0,consts:[["tabindex","-1","role","menu",1,"mat-mdc-menu-panel",3,"click","animationstart","animationend","animationcancel","id"],[1,"mat-mdc-menu-content"]],template:function(i,r){i&1&&(ne(),yc(0,s1,3,12,"ng-template"))},styles:[`mat-menu {
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
`],encapsulation:2,changeDetection:0})}return t})(),d1=new A("mat-menu-scroll-strategy",{providedIn:"root",factory:()=>{let t=m(te);return()=>Wo(t)}});var Hr=new WeakMap,m1=(()=>{class t{_canHaveBackdrop;_element=m(Y);_viewContainerRef=m(hn);_menuItemInstance=m(xa,{optional:!0,self:!0});_dir=m(wt,{optional:!0});_focusMonitor=m(xn);_ngZone=m(K);_injector=m(te);_scrollStrategy=m(d1);_changeDetectorRef=m(qe);_animationsDisabled=He();_portal;_overlayRef=null;_menuOpen=!1;_closingActionsSubscription=lt.EMPTY;_menuCloseSubscription=lt.EMPTY;_pendingRemoval;_parentMaterialMenu;_parentInnerPadding;_openedBy=void 0;get _menu(){return this._menuInternal}set _menu(e){e!==this._menuInternal&&(this._menuInternal=e,this._menuCloseSubscription.unsubscribe(),e&&(this._parentMaterialMenu,this._menuCloseSubscription=e.close.subscribe(i=>{this._destroyMenu(i),(i==="click"||i==="tab")&&this._parentMaterialMenu&&this._parentMaterialMenu.closed.emit(i)})),this._menuItemInstance?._setTriggersSubmenu(this._triggersSubmenu()))}_menuInternal=null;constructor(e){this._canHaveBackdrop=e;let i=m(wu,{optional:!0});this._parentMaterialMenu=i instanceof Ur?i:void 0}ngOnDestroy(){this._menu&&this._ownsMenu(this._menu)&&Hr.delete(this._menu),this._pendingRemoval?.unsubscribe(),this._menuCloseSubscription.unsubscribe(),this._closingActionsSubscription.unsubscribe(),this._overlayRef&&(this._overlayRef.dispose(),this._overlayRef=null)}get menuOpen(){return this._menuOpen}get dir(){return this._dir&&this._dir.value==="rtl"?"rtl":"ltr"}_triggersSubmenu(){return!!(this._menuItemInstance&&this._parentMaterialMenu&&this._menu)}_closeMenu(){this._menu?.close.emit()}_openMenu(e){if(this._triggerIsAriaDisabled())return;let i=this._menu;if(this._menuOpen||!i)return;this._pendingRemoval?.unsubscribe();let r=Hr.get(i);Hr.set(i,this),r&&r!==this&&r._closeMenu();let o=this._createOverlay(i),a=o.getConfig(),s=a.positionStrategy;this._setPosition(i,s),this._canHaveBackdrop?a.hasBackdrop=i.hasBackdrop==null?!this._triggersSubmenu():i.hasBackdrop:a.hasBackdrop=i.hasBackdrop??!1,o.hasAttached()||(o.attach(this._getPortal(i)),i.lazyContent?.attach(this.menuData)),this._closingActionsSubscription=this._menuClosingActions().subscribe(()=>this._closeMenu()),i.parentMenu=this._triggersSubmenu()?this._parentMaterialMenu:void 0,i.direction=this.dir,e&&i.focusFirstItem(this._openedBy||"program"),this._setIsMenuOpen(!0),i instanceof Ur&&(i._setIsOpen(!0),i._directDescendantItems.changes.pipe(ke(i.close)).subscribe(()=>{s.withLockedPosition(!1).reapplyLastPosition(),s.withLockedPosition(!0)}))}focus(e,i){this._focusMonitor&&e?this._focusMonitor.focusVia(this._element,e,i):this._element.nativeElement.focus(i)}_destroyMenu(e){let i=this._overlayRef,r=this._menu;!i||!this.menuOpen||(this._closingActionsSubscription.unsubscribe(),this._pendingRemoval?.unsubscribe(),r instanceof Ur&&this._ownsMenu(r)?(this._pendingRemoval=r._animationDone.pipe(Qn(1)).subscribe(()=>{i.detach(),Hr.has(r)||r.lazyContent?.detach()}),r._setIsOpen(!1)):(i.detach(),r?.lazyContent?.detach()),r&&this._ownsMenu(r)&&Hr.delete(r),this.restoreFocus&&(e==="keydown"||!this._openedBy||!this._triggersSubmenu())&&this.focus(this._openedBy),this._openedBy=void 0,this._setIsMenuOpen(!1))}_setIsMenuOpen(e){e!==this._menuOpen&&(this._menuOpen=e,this._menuOpen?this.menuOpened.emit():this.menuClosed.emit(),this._triggersSubmenu()&&this._menuItemInstance._setHighlighted(e),this._changeDetectorRef.markForCheck())}_createOverlay(e){if(!this._overlayRef){let i=this._getOverlayConfig(e);this._subscribeToPositions(e,i.positionStrategy),this._overlayRef=Cr(this._injector,i),this._overlayRef.keydownEvents().subscribe(r=>{this._menu instanceof Ur&&this._menu._handleKeydown(r)})}return this._overlayRef}_getOverlayConfig(e){return new ai({positionStrategy:Qs(this._injector,this._getOverlayOrigin()).withLockedPosition().withGrowAfterOpen().withTransformOriginOn(".mat-menu-panel, .mat-mdc-menu-panel"),backdropClass:e.backdropClass||"cdk-overlay-transparent-backdrop",panelClass:e.overlayPanelClass,scrollStrategy:this._scrollStrategy(),direction:this._dir||"ltr",disableAnimations:this._animationsDisabled})}_subscribeToPositions(e,i){e.setPositionClasses&&i.positionChanges.subscribe(r=>{this._ngZone.run(()=>{let o=r.connectionPair.overlayX==="start"?"after":"before",a=r.connectionPair.overlayY==="top"?"below":"above";e.setPositionClasses(o,a)})})}_setPosition(e,i){let[r,o]=e.xPosition==="before"?["end","start"]:["start","end"],[a,s]=e.yPosition==="above"?["bottom","top"]:["top","bottom"],[l,c]=[a,s],[d,u]=[r,o],p=0;if(this._triggersSubmenu()){if(u=r=e.xPosition==="before"?"start":"end",o=d=r==="end"?"start":"end",this._parentMaterialMenu){if(this._parentInnerPadding==null){let y=this._parentMaterialMenu.items.first;this._parentInnerPadding=y?y._getHostElement().offsetTop:0}p=a==="bottom"?this._parentInnerPadding:-this._parentInnerPadding}}else e.overlapTrigger||(l=a==="top"?"bottom":"top",c=s==="top"?"bottom":"top");i.withPositions([{originX:r,originY:l,overlayX:d,overlayY:a,offsetY:p},{originX:o,originY:l,overlayX:u,overlayY:a,offsetY:p},{originX:r,originY:c,overlayX:d,overlayY:s,offsetY:-p},{originX:o,originY:c,overlayX:u,overlayY:s,offsetY:-p}])}_menuClosingActions(){let e=this._getOutsideClickStream(this._overlayRef),i=this._overlayRef.detachments(),r=this._parentMaterialMenu?this._parentMaterialMenu.closed:Pe(),o=this._parentMaterialMenu?this._parentMaterialMenu._hovered().pipe(Be(a=>this._menuOpen&&a!==this._menuItemInstance)):Pe();return _t(e,r,o,i)}_getPortal(e){return(!this._portal||this._portal.templateRef!==e.templateRef)&&(this._portal=new $n(e.templateRef,this._viewContainerRef)),this._portal}_ownsMenu(e){return Hr.get(e)===this}_triggerIsAriaDisabled(){return Se(this._element.nativeElement.getAttribute("aria-disabled"))}static \u0275fac=function(i){Ya()};static \u0275dir=G({type:t})}return t})(),Ub=(()=>{class t extends m1{_cleanupTouchstart;_hoverSubscription=lt.EMPTY;get _deprecatedMatMenuTriggerFor(){return this.menu}set _deprecatedMatMenuTriggerFor(e){this.menu=e}get menu(){return this._menu}set menu(e){this._menu=e}menuData;restoreFocus=!0;menuOpened=new De;onMenuOpen=this.menuOpened;menuClosed=new De;onMenuClose=this.menuClosed;constructor(){super(!0);let e=m(rt);this._cleanupTouchstart=e.listen(this._element.nativeElement,"touchstart",i=>{Si(i)||(this._openedBy="touch")},{passive:!0})}triggersSubmenu(){return super._triggersSubmenu()}toggleMenu(){return this.menuOpen?this.closeMenu():this.openMenu()}openMenu(){this._openMenu(!0)}closeMenu(){this._closeMenu()}updatePosition(){this._overlayRef?.updatePosition()}ngAfterContentInit(){this._handleHover()}ngOnDestroy(){super.ngOnDestroy(),this._cleanupTouchstart(),this._hoverSubscription.unsubscribe()}_getOverlayOrigin(){return this._element}_getOutsideClickStream(e){return e.backdropClick()}_handleMousedown(e){Ei(e)||(this._openedBy=e.button===0?"mouse":void 0,this.triggersSubmenu()&&e.preventDefault())}_handleKeydown(e){let i=e.keyCode;(i===13||i===32)&&(this._openedBy="keyboard"),this.triggersSubmenu()&&(i===39&&this.dir==="ltr"||i===37&&this.dir==="rtl")&&(this._openedBy="keyboard",this.openMenu())}_handleClick(e){this.triggersSubmenu()?(e.stopPropagation(),this.openMenu()):this.toggleMenu()}_handleHover(){this.triggersSubmenu()&&this._parentMaterialMenu&&(this._hoverSubscription=this._parentMaterialMenu._hovered().subscribe(e=>{e===this._menuItemInstance&&!e.disabled&&this._parentMaterialMenu?._panelAnimationState!=="void"&&(this._openedBy="mouse",this._openMenu(!1))}))}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","mat-menu-trigger-for",""],["","matMenuTriggerFor",""]],hostAttrs:[1,"mat-mdc-menu-trigger"],hostVars:3,hostBindings:function(i,r){i&1&&pe("click",function(a){return r._handleClick(a)})("mousedown",function(a){return r._handleMousedown(a)})("keydown",function(a){return r._handleKeydown(a)}),i&2&&le("aria-haspopup",r.menu?"menu":null)("aria-expanded",r.menuOpen)("aria-controls",r.menuOpen?r.menu==null?null:r.menu.panelId:null)},inputs:{_deprecatedMatMenuTriggerFor:[0,"mat-menu-trigger-for","_deprecatedMatMenuTriggerFor"],menu:[0,"matMenuTriggerFor","menu"],menuData:[0,"matMenuTriggerData","menuData"],restoreFocus:[0,"matMenuTriggerRestoreFocus","restoreFocus"]},outputs:{menuOpened:"menuOpened",onMenuOpen:"onMenuOpen",menuClosed:"menuClosed",onMenuClose:"onMenuClose"},exportAs:["matMenuTrigger"],features:[Te]})}return t})();var wa=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[wn,Ri,ie,Ln]})}return t})();var p1=["*",[["mat-toolbar-row"]]],h1=["*","mat-toolbar-row"],f1=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["mat-toolbar-row"]],hostAttrs:[1,"mat-toolbar-row"],exportAs:["matToolbarRow"]})}return t})(),qb=(()=>{class t{_elementRef=m(Y);_platform=m(we);_document=m(W);color;_toolbarRows;constructor(){}ngAfterViewInit(){this._platform.isBrowser&&(this._checkToolbarMixedModes(),this._toolbarRows.changes.subscribe(()=>this._checkToolbarMixedModes()))}_checkToolbarMixedModes(){this._toolbarRows.length}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-toolbar"]],contentQueries:function(i,r,o){if(i&1&&Xe(o,f1,5),i&2){let a;H(a=U())&&(r._toolbarRows=a)}},hostAttrs:[1,"mat-toolbar"],hostVars:6,hostBindings:function(i,r){i&2&&(vt(r.color?"mat-"+r.color:""),Q("mat-toolbar-multiple-rows",r._toolbarRows.length>0)("mat-toolbar-single-row",r._toolbarRows.length===0))},inputs:{color:"color"},exportAs:["matToolbar"],ngContentSelectors:h1,decls:2,vars:0,template:function(i,r){i&1&&(ne(p1),B(0),B(1,1))},styles:[`.mat-toolbar {
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
`],encapsulation:2,changeDetection:0})}return t})();var Ca=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[ie]})}return t})();var _1=["*"];var b1=[[["","mat-card-avatar",""],["","matCardAvatar",""]],[["mat-card-title"],["mat-card-subtitle"],["","mat-card-title",""],["","mat-card-subtitle",""],["","matCardTitle",""],["","matCardSubtitle",""]],"*"],v1=["[mat-card-avatar], [matCardAvatar]",`mat-card-title, mat-card-subtitle,
      [mat-card-title], [mat-card-subtitle],
      [matCardTitle], [matCardSubtitle]`,"*"],y1=new A("MAT_CARD_CONFIG"),Gr=(()=>{class t{appearance;constructor(){let e=m(y1,{optional:!0});this.appearance=e?.appearance||"raised"}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-card"]],hostAttrs:[1,"mat-mdc-card","mdc-card"],hostVars:8,hostBindings:function(i,r){i&2&&Q("mat-mdc-card-outlined",r.appearance==="outlined")("mdc-card--outlined",r.appearance==="outlined")("mat-mdc-card-filled",r.appearance==="filled")("mdc-card--filled",r.appearance==="filled")},inputs:{appearance:"appearance"},exportAs:["matCard"],ngContentSelectors:_1,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},styles:[`.mat-mdc-card {
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
`],encapsulation:2,changeDetection:0})}return t})(),Wr=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["mat-card-title"],["","mat-card-title",""],["","matCardTitle",""]],hostAttrs:[1,"mat-mdc-card-title"]})}return t})();var Kr=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["mat-card-content"]],hostAttrs:[1,"mat-mdc-card-content"]})}return t})();var Yr=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-card-header"]],hostAttrs:[1,"mat-mdc-card-header"],ngContentSelectors:v1,decls:4,vars:0,consts:[[1,"mat-mdc-card-header-text"]],template:function(i,r){i&1&&(ne(b1),B(0),Ue(1,"div",0),B(2,1),Qe(),B(3,2))},encapsulation:2,changeDetection:0})}return t})();var qn=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[ie]})}return t})();var Gb=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({})}return t})();var ku=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[Gb,wr,ie]})}return t})();var Du=class{_box;_destroyed=new z;_resizeSubject=new z;_resizeObserver;_elementObservables=new Map;constructor(n){this._box=n,typeof ResizeObserver<"u"&&(this._resizeObserver=new ResizeObserver(e=>this._resizeSubject.next(e)))}observe(n){return this._elementObservables.has(n)||this._elementObservables.set(n,new St(e=>{let i=this._resizeSubject.subscribe(e);return this._resizeObserver?.observe(n,{box:this._box}),()=>{this._resizeObserver?.unobserve(n),i.unsubscribe(),this._elementObservables.delete(n)}}).pipe(Be(e=>e.some(i=>i.target===n)),An({bufferSize:1,refCount:!0}),ke(this._destroyed))),this._elementObservables.get(n)}destroy(){this._destroyed.next(),this._destroyed.complete(),this._resizeSubject.complete(),this._elementObservables.clear()}},Wb=(()=>{class t{_cleanupErrorListener;_observers=new Map;_ngZone=m(K);constructor(){typeof ResizeObserver<"u"}ngOnDestroy(){for(let[,e]of this._observers)e.destroy();this._observers.clear(),this._cleanupErrorListener?.()}observe(e,i){let r=i?.box||"content-box";return this._observers.has(r)||this._observers.set(r,new Du(r)),this._observers.get(r).observe(e)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var Eu=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[ie]})}return t})();var ka=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[ie]})}return t})();var Su=class{_document;_textarea;constructor(n,e){this._document=e;let i=this._textarea=this._document.createElement("textarea"),r=i.style;r.position="fixed",r.top=r.opacity="0",r.left="-999em",i.setAttribute("aria-hidden","true"),i.value=n,i.readOnly=!0,(this._document.fullscreenElement||this._document.body).appendChild(i)}copy(){let n=this._textarea,e=!1;try{if(n){let i=this._document.activeElement;n.select(),n.setSelectionRange(0,n.value.length),e=this._document.execCommand("copy"),i&&i.focus()}}catch{}return e}destroy(){let n=this._textarea;n&&(n.remove(),this._textarea=void 0)}},x1=(()=>{class t{_document=m(W);constructor(){}copy(e){let i=this.beginCopy(e),r=i.copy();return i.destroy(),r}beginCopy(e){return new Su(e,this._document)}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})(),w1=new A("CDK_COPY_TO_CLIPBOARD_CONFIG"),Kb=(()=>{class t{_clipboard=m(x1);_ngZone=m(K);text="";attempts=1;copied=new De;_pending=new Set;_destroyed=!1;_currentTimeout;constructor(){let e=m(w1,{optional:!0});e&&e.attempts!=null&&(this.attempts=e.attempts)}copy(e=this.attempts){if(e>1){let i=e,r=this._clipboard.beginCopy(this.text);this._pending.add(r);let o=()=>{let a=r.copy();!a&&--i&&!this._destroyed?this._currentTimeout=this._ngZone.runOutsideAngular(()=>setTimeout(o,1)):(this._currentTimeout=null,this._pending.delete(r),r.destroy(),this.copied.emit(a))};o()}else this.copied.emit(this._clipboard.copy(this.text))}ngOnDestroy(){this._currentTimeout&&clearTimeout(this._currentTimeout),this._pending.forEach(e=>e.destroy()),this._pending.clear(),this._destroyed=!0}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","cdkCopyToClipboard",""]],hostBindings:function(i,r){i&1&&pe("click",function(){return r.copy()})},inputs:{text:[0,"cdkCopyToClipboard","text"],attempts:[0,"cdkCopyToClipboardAttempts","attempts"]},outputs:{copied:"cdkCopyToClipboardCopied"}})}return t})(),Da=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({})}return t})();var k1=["notch"],D1=["matFormFieldNotchedOutline",""],E1=["*"],Yb=["iconPrefixContainer"],Qb=["textPrefixContainer"],Xb=["iconSuffixContainer"],Zb=["textSuffixContainer"],S1=["textField"],M1=["*",[["mat-label"]],[["","matPrefix",""],["","matIconPrefix",""]],[["","matTextPrefix",""]],[["","matTextSuffix",""]],[["","matSuffix",""],["","matIconSuffix",""]],[["mat-error"],["","matError",""]],[["mat-hint",3,"align","end"]],[["mat-hint","align","end"]]],A1=["*","mat-label","[matPrefix], [matIconPrefix]","[matTextPrefix]","[matTextSuffix]","[matSuffix], [matIconSuffix]","mat-error, [matError]","mat-hint:not([align='end'])","mat-hint[align='end']"];function I1(t,n){t&1&&P(0,"span",21)}function T1(t,n){if(t&1&&(b(0,"label",20),B(1,1),k(2,I1,1,0,"span",21),f()),t&2){let e=C(2);R("floating",e._shouldLabelFloat())("monitorResize",e._hasOutline())("id",e._labelId),le("for",e._control.disableAutomaticLabeling?null:e._control.id),h(2),D(!e.hideRequiredMarker&&e._control.required?2:-1)}}function O1(t,n){if(t&1&&k(0,T1,3,5,"label",20),t&2){let e=C();D(e._hasFloatingLabel()?0:-1)}}function R1(t,n){t&1&&P(0,"div",7)}function P1(t,n){}function F1(t,n){if(t&1&&jt(0,P1,0,0,"ng-template",13),t&2){C(2);let e=Vt(1);R("ngTemplateOutlet",e)}}function N1(t,n){if(t&1&&(b(0,"div",9),k(1,F1,1,1,null,13),f()),t&2){let e=C();R("matFormFieldNotchedOutlineOpen",e._shouldLabelFloat()),h(),D(e._forceDisplayInfixLabel()?-1:1)}}function L1(t,n){t&1&&(b(0,"div",10,2),B(2,2),f())}function B1(t,n){t&1&&(b(0,"div",11,3),B(2,3),f())}function $1(t,n){}function j1(t,n){if(t&1&&jt(0,$1,0,0,"ng-template",13),t&2){C();let e=Vt(1);R("ngTemplateOutlet",e)}}function V1(t,n){t&1&&(b(0,"div",14,4),B(2,4),f())}function z1(t,n){t&1&&(b(0,"div",15,5),B(2,5),f())}function H1(t,n){t&1&&P(0,"div",16)}function U1(t,n){t&1&&(b(0,"div",18),B(1,6),f())}function q1(t,n){if(t&1&&(b(0,"mat-hint",22),w(1),f()),t&2){let e=C(2);R("id",e._hintLabelId),h(),Le(e.hintLabel)}}function G1(t,n){if(t&1&&(b(0,"div",19),k(1,q1,2,2,"mat-hint",22),B(2,7),P(3,"div",23),B(4,8),f()),t&2){let e=C();h(),D(e.hintLabel?1:-1)}}var Mu=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["mat-label"]]})}return t})(),W1=new A("MatError");var Au=(()=>{class t{align="start";id=m(ut).getId("mat-mdc-hint-");static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["mat-hint"]],hostAttrs:[1,"mat-mdc-form-field-hint","mat-mdc-form-field-bottom-align"],hostVars:4,hostBindings:function(i,r){i&2&&(ti("id",r.id),le("align",null),Q("mat-mdc-form-field-hint-end",r.align==="end"))},inputs:{align:"align",id:"id"}})}return t})(),K1=new A("MatPrefix");var Y1=new A("MatSuffix");var ov=new A("FloatingLabelParent"),Jb=(()=>{class t{_elementRef=m(Y);get floating(){return this._floating}set floating(e){this._floating=e,this.monitorResize&&this._handleResize()}_floating=!1;get monitorResize(){return this._monitorResize}set monitorResize(e){this._monitorResize=e,this._monitorResize?this._subscribeToResize():this._resizeSubscription.unsubscribe()}_monitorResize=!1;_resizeObserver=m(Wb);_ngZone=m(K);_parent=m(ov);_resizeSubscription=new lt;constructor(){}ngOnDestroy(){this._resizeSubscription.unsubscribe()}getWidth(){return Q1(this._elementRef.nativeElement)}get element(){return this._elementRef.nativeElement}_handleResize(){setTimeout(()=>this._parent._handleLabelResized())}_subscribeToResize(){this._resizeSubscription.unsubscribe(),this._ngZone.runOutsideAngular(()=>{this._resizeSubscription=this._resizeObserver.observe(this._elementRef.nativeElement,{box:"border-box"}).subscribe(()=>this._handleResize())})}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["label","matFormFieldFloatingLabel",""]],hostAttrs:[1,"mdc-floating-label","mat-mdc-floating-label"],hostVars:2,hostBindings:function(i,r){i&2&&Q("mdc-floating-label--float-above",r.floating)},inputs:{floating:"floating",monitorResize:"monitorResize"}})}return t})();function Q1(t){let n=t;if(n.offsetParent!==null)return n.scrollWidth;let e=n.cloneNode(!0);e.style.setProperty("position","absolute"),e.style.setProperty("transform","translate(-9999px, -9999px)"),document.documentElement.appendChild(e);let i=e.scrollWidth;return e.remove(),i}var ev="mdc-line-ripple--active",jl="mdc-line-ripple--deactivating",tv=(()=>{class t{_elementRef=m(Y);_cleanupTransitionEnd;constructor(){let e=m(K),i=m(rt);e.runOutsideAngular(()=>{this._cleanupTransitionEnd=i.listen(this._elementRef.nativeElement,"transitionend",this._handleTransitionEnd)})}activate(){let e=this._elementRef.nativeElement.classList;e.remove(jl),e.add(ev)}deactivate(){this._elementRef.nativeElement.classList.add(jl)}_handleTransitionEnd=e=>{let i=this._elementRef.nativeElement.classList,r=i.contains(jl);e.propertyName==="opacity"&&r&&i.remove(ev,jl)};ngOnDestroy(){this._cleanupTransitionEnd()}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["div","matFormFieldLineRipple",""]],hostAttrs:[1,"mdc-line-ripple"]})}return t})(),nv=(()=>{class t{_elementRef=m(Y);_ngZone=m(K);open=!1;_notch;ngAfterViewInit(){let e=this._elementRef.nativeElement,i=e.querySelector(".mdc-floating-label");i?(e.classList.add("mdc-notched-outline--upgraded"),typeof requestAnimationFrame=="function"&&(i.style.transitionDuration="0s",this._ngZone.runOutsideAngular(()=>{requestAnimationFrame(()=>i.style.transitionDuration="")}))):e.classList.add("mdc-notched-outline--no-label")}_setNotchWidth(e){let i=this._notch.nativeElement;!this.open||!e?i.style.width="":i.style.width=`calc(${e}px * var(--mat-mdc-form-field-floating-label-scale, 0.75) + 9px)`}_setMaxWidth(e){this._notch.nativeElement.style.setProperty("--mat-form-field-notch-max-width",`calc(100% - ${e}px)`)}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["div","matFormFieldNotchedOutline",""]],viewQuery:function(i,r){if(i&1&&Ne(k1,5),i&2){let o;H(o=U())&&(r._notch=o.first)}},hostAttrs:[1,"mdc-notched-outline"],hostVars:2,hostBindings:function(i,r){i&2&&Q("mdc-notched-outline--notched",r.open)},inputs:{open:[0,"matFormFieldNotchedOutlineOpen","open"]},attrs:D1,ngContentSelectors:E1,decls:5,vars:0,consts:[["notch",""],[1,"mat-mdc-notch-piece","mdc-notched-outline__leading"],[1,"mat-mdc-notch-piece","mdc-notched-outline__notch"],[1,"mat-mdc-notch-piece","mdc-notched-outline__trailing"]],template:function(i,r){i&1&&(ne(),Jt(0,"div",1),Ue(1,"div",2,0),B(3),Qe(),Jt(4,"div",3))},encapsulation:2,changeDetection:0})}return t})(),X1=(()=>{class t{value=null;stateChanges;id;placeholder;ngControl=null;focused=!1;empty=!1;shouldLabelFloat=!1;required=!1;disabled=!1;errorState=!1;controlType;autofilled;userAriaDescribedBy;disableAutomaticLabeling;describedByIds;static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t})}return t})();var Z1=new A("MatFormField"),J1=new A("MAT_FORM_FIELD_DEFAULT_OPTIONS"),iv="fill",eM="auto",rv="fixed",tM="translateY(-50%)",av=(()=>{class t{_elementRef=m(Y);_changeDetectorRef=m(qe);_platform=m(we);_idGenerator=m(ut);_ngZone=m(K);_defaults=m(J1,{optional:!0});_currentDirection;_textField;_iconPrefixContainer;_textPrefixContainer;_iconSuffixContainer;_textSuffixContainer;_floatingLabel;_notchedOutline;_lineRipple;_iconPrefixContainerSignal=yo("iconPrefixContainer");_textPrefixContainerSignal=yo("textPrefixContainer");_iconSuffixContainerSignal=yo("iconSuffixContainer");_textSuffixContainerSignal=yo("textSuffixContainer");_prefixSuffixContainers=en(()=>[this._iconPrefixContainerSignal(),this._textPrefixContainerSignal(),this._iconSuffixContainerSignal(),this._textSuffixContainerSignal()].map(e=>e?.nativeElement).filter(e=>e!==void 0));_formFieldControl;_prefixChildren;_suffixChildren;_errorChildren;_hintChildren;_labelChild=qp(Mu);get hideRequiredMarker(){return this._hideRequiredMarker}set hideRequiredMarker(e){this._hideRequiredMarker=kt(e)}_hideRequiredMarker=!1;color="primary";get floatLabel(){return this._floatLabel||this._defaults?.floatLabel||eM}set floatLabel(e){e!==this._floatLabel&&(this._floatLabel=e,this._changeDetectorRef.markForCheck())}_floatLabel;get appearance(){return this._appearanceSignal()}set appearance(e){let i=e||this._defaults?.appearance||iv;this._appearanceSignal.set(i)}_appearanceSignal=X(iv);get subscriptSizing(){return this._subscriptSizing||this._defaults?.subscriptSizing||rv}set subscriptSizing(e){this._subscriptSizing=e||this._defaults?.subscriptSizing||rv}_subscriptSizing=null;get hintLabel(){return this._hintLabel}set hintLabel(e){this._hintLabel=e,this._processHints()}_hintLabel="";_hasIconPrefix=!1;_hasTextPrefix=!1;_hasIconSuffix=!1;_hasTextSuffix=!1;_labelId=this._idGenerator.getId("mat-mdc-form-field-label-");_hintLabelId=this._idGenerator.getId("mat-mdc-hint-");_describedByIds;get _control(){return this._explicitFormFieldControl||this._formFieldControl}set _control(e){this._explicitFormFieldControl=e}_destroyed=new z;_isFocused=null;_explicitFormFieldControl;_previousControl=null;_previousControlValidatorFn=null;_stateChanges;_valueChanges;_describedByChanges;_outlineLabelOffsetResizeObserver=null;_animationsDisabled=He();constructor(){let e=this._defaults,i=m(wt);e&&(e.appearance&&(this.appearance=e.appearance),this._hideRequiredMarker=!!e?.hideRequiredMarker,e.color&&(this.color=e.color)),er(()=>this._currentDirection=i.valueSignal()),this._syncOutlineLabelOffset()}ngAfterViewInit(){this._updateFocusState(),this._animationsDisabled||this._ngZone.runOutsideAngular(()=>{setTimeout(()=>{this._elementRef.nativeElement.classList.add("mat-form-field-animations-enabled")},300)}),this._changeDetectorRef.detectChanges()}ngAfterContentInit(){this._assertFormFieldControl(),this._initializeSubscript(),this._initializePrefixAndSuffix()}ngAfterContentChecked(){this._assertFormFieldControl(),this._control!==this._previousControl&&(this._initializeControl(this._previousControl),this._control.ngControl&&this._control.ngControl.control&&(this._previousControlValidatorFn=this._control.ngControl.control.validator),this._previousControl=this._control),this._control.ngControl&&this._control.ngControl.control&&this._control.ngControl.control.validator!==this._previousControlValidatorFn&&this._changeDetectorRef.markForCheck()}ngOnDestroy(){this._outlineLabelOffsetResizeObserver?.disconnect(),this._stateChanges?.unsubscribe(),this._valueChanges?.unsubscribe(),this._describedByChanges?.unsubscribe(),this._destroyed.next(),this._destroyed.complete()}getLabelId=en(()=>this._hasFloatingLabel()?this._labelId:null);getConnectedOverlayOrigin(){return this._textField||this._elementRef}_animateAndLockLabel(){this._hasFloatingLabel()&&(this.floatLabel="always")}_initializeControl(e){let i=this._control,r="mat-mdc-form-field-type-";e&&this._elementRef.nativeElement.classList.remove(r+e.controlType),i.controlType&&this._elementRef.nativeElement.classList.add(r+i.controlType),this._stateChanges?.unsubscribe(),this._stateChanges=i.stateChanges.subscribe(()=>{this._updateFocusState(),this._changeDetectorRef.markForCheck()}),this._describedByChanges?.unsubscribe(),this._describedByChanges=i.stateChanges.pipe(ct([void 0,void 0]),ye(()=>[i.errorState,i.userAriaDescribedBy]),bc(),Be(([[o,a],[s,l]])=>o!==s||a!==l)).subscribe(()=>this._syncDescribedByIds()),this._valueChanges?.unsubscribe(),i.ngControl&&i.ngControl.valueChanges&&(this._valueChanges=i.ngControl.valueChanges.pipe(ke(this._destroyed)).subscribe(()=>this._changeDetectorRef.markForCheck()))}_checkPrefixAndSuffixTypes(){this._hasIconPrefix=!!this._prefixChildren.find(e=>!e._isText),this._hasTextPrefix=!!this._prefixChildren.find(e=>e._isText),this._hasIconSuffix=!!this._suffixChildren.find(e=>!e._isText),this._hasTextSuffix=!!this._suffixChildren.find(e=>e._isText)}_initializePrefixAndSuffix(){this._checkPrefixAndSuffixTypes(),_t(this._prefixChildren.changes,this._suffixChildren.changes).subscribe(()=>{this._checkPrefixAndSuffixTypes(),this._changeDetectorRef.markForCheck()})}_initializeSubscript(){this._hintChildren.changes.subscribe(()=>{this._processHints(),this._changeDetectorRef.markForCheck()}),this._errorChildren.changes.subscribe(()=>{this._syncDescribedByIds(),this._changeDetectorRef.markForCheck()}),this._validateHints(),this._syncDescribedByIds()}_assertFormFieldControl(){this._control}_updateFocusState(){let e=this._control.focused;e&&!this._isFocused?(this._isFocused=!0,this._lineRipple?.activate()):!e&&(this._isFocused||this._isFocused===null)&&(this._isFocused=!1,this._lineRipple?.deactivate()),this._elementRef.nativeElement.classList.toggle("mat-focused",e),this._textField?.nativeElement.classList.toggle("mdc-text-field--focused",e)}_syncOutlineLabelOffset(){Yp({earlyRead:()=>{if(this._appearanceSignal()!=="outline")return this._outlineLabelOffsetResizeObserver?.disconnect(),null;if(globalThis.ResizeObserver){this._outlineLabelOffsetResizeObserver||=new globalThis.ResizeObserver(()=>{this._writeOutlinedLabelStyles(this._getOutlinedLabelOffset())});for(let e of this._prefixSuffixContainers())this._outlineLabelOffsetResizeObserver.observe(e,{box:"border-box"})}return this._getOutlinedLabelOffset()},write:e=>this._writeOutlinedLabelStyles(e())})}_shouldAlwaysFloat(){return this.floatLabel==="always"}_hasOutline(){return this.appearance==="outline"}_forceDisplayInfixLabel(){return!this._platform.isBrowser&&this._prefixChildren.length&&!this._shouldLabelFloat()}_hasFloatingLabel=en(()=>!!this._labelChild());_shouldLabelFloat(){return this._hasFloatingLabel()?this._control.shouldLabelFloat||this._shouldAlwaysFloat():!1}_shouldForward(e){let i=this._control?this._control.ngControl:null;return i&&i[e]}_getSubscriptMessageType(){return this._errorChildren&&this._errorChildren.length>0&&this._control.errorState?"error":"hint"}_handleLabelResized(){this._refreshOutlineNotchWidth()}_refreshOutlineNotchWidth(){!this._hasOutline()||!this._floatingLabel||!this._shouldLabelFloat()?this._notchedOutline?._setNotchWidth(0):this._notchedOutline?._setNotchWidth(this._floatingLabel.getWidth())}_processHints(){this._validateHints(),this._syncDescribedByIds()}_validateHints(){this._hintChildren}_syncDescribedByIds(){if(this._control){let e=[];if(this._control.userAriaDescribedBy&&typeof this._control.userAriaDescribedBy=="string"&&e.push(...this._control.userAriaDescribedBy.split(" ")),this._getSubscriptMessageType()==="hint"){let o=this._hintChildren?this._hintChildren.find(s=>s.align==="start"):null,a=this._hintChildren?this._hintChildren.find(s=>s.align==="end"):null;o?e.push(o.id):this._hintLabel&&e.push(this._hintLabelId),a&&e.push(a.id)}else this._errorChildren&&e.push(...this._errorChildren.map(o=>o.id));let i=this._control.describedByIds,r;if(i){let o=this._describedByIds||e;r=e.concat(i.filter(a=>a&&!o.includes(a)))}else r=e;this._control.setDescribedByIds(r),this._describedByIds=e}}_getOutlinedLabelOffset(){if(!this._hasOutline()||!this._floatingLabel)return null;if(!this._iconPrefixContainer&&!this._textPrefixContainer)return["",null];if(!this._isAttachedToDom())return null;let e=this._iconPrefixContainer?.nativeElement,i=this._textPrefixContainer?.nativeElement,r=this._iconSuffixContainer?.nativeElement,o=this._textSuffixContainer?.nativeElement,a=e?.getBoundingClientRect().width??0,s=i?.getBoundingClientRect().width??0,l=r?.getBoundingClientRect().width??0,c=o?.getBoundingClientRect().width??0,d=this._currentDirection==="rtl"?"-1":"1",u=`${a+s}px`,y=`calc(${d} * (${u} + var(--mat-mdc-form-field-label-offset-x, 0px)))`,g=`var(--mat-mdc-form-field-label-transform, ${tM} translateX(${y}))`,_=a+s+l+c;return[g,_]}_writeOutlinedLabelStyles(e){if(e!==null){let[i,r]=e;this._floatingLabel&&(this._floatingLabel.element.style.transform=i),r!==null&&this._notchedOutline?._setMaxWidth(r)}}_isAttachedToDom(){let e=this._elementRef.nativeElement;if(e.getRootNode){let i=e.getRootNode();return i&&i!==e}return document.documentElement.contains(e)}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-form-field"]],contentQueries:function(i,r,o){if(i&1&&($p(o,r._labelChild,Mu,5),Xe(o,X1,5)(o,K1,5)(o,Y1,5)(o,W1,5)(o,Au,5)),i&2){wc();let a;H(a=U())&&(r._formFieldControl=a.first),H(a=U())&&(r._prefixChildren=a),H(a=U())&&(r._suffixChildren=a),H(a=U())&&(r._errorChildren=a),H(a=U())&&(r._hintChildren=a)}},viewQuery:function(i,r){if(i&1&&(jp(r._iconPrefixContainerSignal,Yb,5)(r._textPrefixContainerSignal,Qb,5)(r._iconSuffixContainerSignal,Xb,5)(r._textSuffixContainerSignal,Zb,5),Ne(S1,5)(Yb,5)(Qb,5)(Xb,5)(Zb,5)(Jb,5)(nv,5)(tv,5)),i&2){wc(4);let o;H(o=U())&&(r._textField=o.first),H(o=U())&&(r._iconPrefixContainer=o.first),H(o=U())&&(r._textPrefixContainer=o.first),H(o=U())&&(r._iconSuffixContainer=o.first),H(o=U())&&(r._textSuffixContainer=o.first),H(o=U())&&(r._floatingLabel=o.first),H(o=U())&&(r._notchedOutline=o.first),H(o=U())&&(r._lineRipple=o.first)}},hostAttrs:[1,"mat-mdc-form-field"],hostVars:38,hostBindings:function(i,r){i&2&&Q("mat-mdc-form-field-label-always-float",r._shouldAlwaysFloat())("mat-mdc-form-field-has-icon-prefix",r._hasIconPrefix)("mat-mdc-form-field-has-icon-suffix",r._hasIconSuffix)("mat-form-field-invalid",r._control.errorState)("mat-form-field-disabled",r._control.disabled)("mat-form-field-autofilled",r._control.autofilled)("mat-form-field-appearance-fill",r.appearance=="fill")("mat-form-field-appearance-outline",r.appearance=="outline")("mat-form-field-hide-placeholder",r._hasFloatingLabel()&&!r._shouldLabelFloat())("mat-primary",r.color!=="accent"&&r.color!=="warn")("mat-accent",r.color==="accent")("mat-warn",r.color==="warn")("ng-untouched",r._shouldForward("untouched"))("ng-touched",r._shouldForward("touched"))("ng-pristine",r._shouldForward("pristine"))("ng-dirty",r._shouldForward("dirty"))("ng-valid",r._shouldForward("valid"))("ng-invalid",r._shouldForward("invalid"))("ng-pending",r._shouldForward("pending"))},inputs:{hideRequiredMarker:"hideRequiredMarker",color:"color",floatLabel:"floatLabel",appearance:"appearance",subscriptSizing:"subscriptSizing",hintLabel:"hintLabel"},exportAs:["matFormField"],features:[ze([{provide:Z1,useExisting:t},{provide:ov,useExisting:t}])],ngContentSelectors:A1,decls:18,vars:21,consts:[["labelTemplate",""],["textField",""],["iconPrefixContainer",""],["textPrefixContainer",""],["textSuffixContainer",""],["iconSuffixContainer",""],[1,"mat-mdc-text-field-wrapper","mdc-text-field",3,"click"],[1,"mat-mdc-form-field-focus-overlay"],[1,"mat-mdc-form-field-flex"],["matFormFieldNotchedOutline","",3,"matFormFieldNotchedOutlineOpen"],[1,"mat-mdc-form-field-icon-prefix"],[1,"mat-mdc-form-field-text-prefix"],[1,"mat-mdc-form-field-infix"],[3,"ngTemplateOutlet"],[1,"mat-mdc-form-field-text-suffix"],[1,"mat-mdc-form-field-icon-suffix"],["matFormFieldLineRipple",""],["aria-atomic","true","aria-live","polite",1,"mat-mdc-form-field-subscript-wrapper","mat-mdc-form-field-bottom-align"],[1,"mat-mdc-form-field-error-wrapper"],[1,"mat-mdc-form-field-hint-wrapper"],["matFormFieldFloatingLabel","",3,"floating","monitorResize","id"],["aria-hidden","true",1,"mat-mdc-form-field-required-marker","mdc-floating-label--required"],[3,"id"],[1,"mat-mdc-form-field-hint-spacer"]],template:function(i,r){if(i&1&&(ne(M1),jt(0,O1,1,1,"ng-template",null,0,vo),b(2,"div",6,1),pe("click",function(a){return r._control.onContainerClick(a)}),k(4,R1,1,0,"div",7),b(5,"div",8),k(6,N1,2,2,"div",9),k(7,L1,3,0,"div",10),k(8,B1,3,0,"div",11),b(9,"div",12),k(10,j1,1,1,null,13),B(11),f(),k(12,V1,3,0,"div",14),k(13,z1,3,0,"div",15),f(),k(14,H1,1,0,"div",16),f(),b(15,"div",17),k(16,U1,2,0,"div",18)(17,G1,5,1,"div",19),f()),i&2){let o;h(2),Q("mdc-text-field--filled",!r._hasOutline())("mdc-text-field--outlined",r._hasOutline())("mdc-text-field--no-label",!r._hasFloatingLabel())("mdc-text-field--disabled",r._control.disabled)("mdc-text-field--invalid",r._control.errorState),h(2),D(!r._hasOutline()&&!r._control.disabled?4:-1),h(2),D(r._hasOutline()?6:-1),h(),D(r._hasIconPrefix?7:-1),h(),D(r._hasTextPrefix?8:-1),h(2),D(!r._hasOutline()||r._forceDisplayInfixLabel()?10:-1),h(2),D(r._hasTextSuffix?12:-1),h(),D(r._hasIconSuffix?13:-1),h(),D(r._hasOutline()?-1:14),h(),Q("mat-mdc-form-field-subscript-dynamic-size",r.subscriptSizing==="dynamic");let a=r._getSubscriptMessageType();h(),D((o=a)==="error"?16:o==="hint"?17:-1)}},dependencies:[Jb,nv,ko,tv,Au],styles:[`.mdc-text-field {
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
`],encapsulation:2,changeDetection:0})}return t})();var Ea=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[Fs,av,ie]})}return t})();var nM=new A("",{factory:()=>sv}),sv="always";var iM=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({})}return t})();var lv=(()=>{class t{static withConfig(e){return{ngModule:t,providers:[{provide:nM,useValue:e.callSetDisabledState??sv}]}}static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[iM]})}return t})();var cv=(()=>{class t{_animationsDisabled=He();state="unchecked";disabled=!1;appearance="full";constructor(){}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-pseudo-checkbox"]],hostAttrs:[1,"mat-pseudo-checkbox"],hostVars:12,hostBindings:function(i,r){i&2&&Q("mat-pseudo-checkbox-indeterminate",r.state==="indeterminate")("mat-pseudo-checkbox-checked",r.state==="checked")("mat-pseudo-checkbox-disabled",r.disabled)("mat-pseudo-checkbox-minimal",r.appearance==="minimal")("mat-pseudo-checkbox-full",r.appearance==="full")("_mat-animation-noopable",r._animationsDisabled)},inputs:{state:"state",disabled:"disabled",appearance:"appearance"},decls:0,vars:0,template:function(i,r){},styles:[`.mat-pseudo-checkbox {
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
`],encapsulation:2,changeDetection:0})}return t})();var rM=["text"],oM=[[["mat-icon"]],"*"],aM=["mat-icon","*"];function sM(t,n){if(t&1&&P(0,"mat-pseudo-checkbox",1),t&2){let e=C();R("disabled",e.disabled)("state",e.selected?"checked":"unchecked")}}function lM(t,n){if(t&1&&P(0,"mat-pseudo-checkbox",3),t&2){let e=C();R("disabled",e.disabled)}}function cM(t,n){if(t&1&&(b(0,"span",4),w(1),f()),t&2){let e=C();h(),J("(",e.group.label,")")}}var dM=new A("MAT_OPTION_PARENT_COMPONENT"),mM=new A("MatOptgroup");var Iu=class{source;isUserInput;constructor(n,e=!1){this.source=n,this.isUserInput=e}},dv=(()=>{class t{_element=m(Y);_changeDetectorRef=m(qe);_parent=m(dM,{optional:!0});group=m(mM,{optional:!0});_signalDisableRipple=!1;_selected=!1;_active=!1;_mostRecentViewValue="";get multiple(){return this._parent&&this._parent.multiple}get selected(){return this._selected}value;id=m(ut).getId("mat-option-");get disabled(){return this.group&&this.group.disabled||this._disabled()}set disabled(e){this._disabled.set(e)}_disabled=X(!1);get disableRipple(){return this._signalDisableRipple?this._parent.disableRipple():!!this._parent?.disableRipple}get hideSingleSelectionIndicator(){return!!(this._parent&&this._parent.hideSingleSelectionIndicator)}onSelectionChange=new De;_text;_stateChanges=new z;constructor(){let e=m(Je);e.load(an),e.load(hr),this._signalDisableRipple=!!this._parent&&ar(this._parent.disableRipple)}get active(){return this._active}get viewValue(){return(this._text?.nativeElement.textContent||"").trim()}select(e=!0){this._selected||(this._selected=!0,this._changeDetectorRef.markForCheck(),e&&this._emitSelectionChangeEvent())}deselect(e=!0){this._selected&&(this._selected=!1,this._changeDetectorRef.markForCheck(),e&&this._emitSelectionChangeEvent())}focus(e,i){let r=this._getHostElement();typeof r.focus=="function"&&r.focus(i)}setActiveStyles(){this._active||(this._active=!0,this._changeDetectorRef.markForCheck())}setInactiveStyles(){this._active&&(this._active=!1,this._changeDetectorRef.markForCheck())}getLabel(){return this.viewValue}_handleKeydown(e){(e.keyCode===13||e.keyCode===32)&&!oi(e)&&(this._selectViaInteraction(),e.preventDefault())}_selectViaInteraction(){this.disabled||(this._selected=this.multiple?!this._selected:!0,this._changeDetectorRef.markForCheck(),this._emitSelectionChangeEvent(!0))}_getTabIndex(){return this.disabled?"-1":"0"}_getHostElement(){return this._element.nativeElement}ngAfterViewChecked(){if(this._selected){let e=this.viewValue;e!==this._mostRecentViewValue&&(this._mostRecentViewValue&&this._stateChanges.next(),this._mostRecentViewValue=e)}}ngOnDestroy(){this._stateChanges.complete()}_emitSelectionChangeEvent(e=!1){this.onSelectionChange.emit(new Iu(this,e))}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-option"]],viewQuery:function(i,r){if(i&1&&Ne(rM,7),i&2){let o;H(o=U())&&(r._text=o.first)}},hostAttrs:["role","option",1,"mat-mdc-option","mdc-list-item"],hostVars:11,hostBindings:function(i,r){i&1&&pe("click",function(){return r._selectViaInteraction()})("keydown",function(a){return r._handleKeydown(a)}),i&2&&(ti("id",r.id),le("aria-selected",r.selected)("aria-disabled",r.disabled.toString()),Q("mdc-list-item--selected",r.selected)("mat-mdc-option-multiple",r.multiple)("mat-mdc-option-active",r.active)("mdc-list-item--disabled",r.disabled))},inputs:{value:"value",id:"id",disabled:[2,"disabled","disabled",Se]},outputs:{onSelectionChange:"onSelectionChange"},exportAs:["matOption"],ngContentSelectors:aM,decls:8,vars:5,consts:[["text",""],["aria-hidden","true",1,"mat-mdc-option-pseudo-checkbox",3,"disabled","state"],[1,"mdc-list-item__primary-text"],["state","checked","aria-hidden","true","appearance","minimal",1,"mat-mdc-option-pseudo-checkbox",3,"disabled"],[1,"cdk-visually-hidden"],["aria-hidden","true","mat-ripple","",1,"mat-mdc-option-ripple","mat-focus-indicator",3,"matRippleTrigger","matRippleDisabled"]],template:function(i,r){i&1&&(ne(oM),k(0,sM,1,2,"mat-pseudo-checkbox",1),B(1),b(2,"span",2,0),B(4,1),f(),k(5,lM,1,1,"mat-pseudo-checkbox",3),k(6,cM,2,1,"span",4),P(7,"div",5)),i&2&&(D(r.multiple?0:-1),h(5),D(!r.multiple&&r.selected&&!r.hideSingleSelectionIndicator?5:-1),h(),D(r.group&&r.group._inert?6:-1),h(),R("matRippleTrigger",r._getHostElement())("matRippleDisabled",r.disabled||r.disableRipple))},dependencies:[cv,Zs],styles:[`.mat-mdc-option {
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
`],encapsulation:2,changeDetection:0})}return t})();var mv=(()=>{class t{isErrorState(e,i){return!!(e&&e.invalid&&(e.touched||i&&i.submitted))}static \u0275fac=function(i){return new(i||t)};static \u0275prov=E({token:t,factory:t.\u0275fac,providedIn:"root"})}return t})();var Vl=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[ie]})}return t})();var Tu=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[wn,Vl,dv,ie]})}return t})();var Sa=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[Ri,Tu,ie,Ln,Ea,Tu]})}return t})();var uM=["*",[["mat-chip-avatar"],["","matChipAvatar",""]],[["mat-chip-trailing-icon"],["","matChipRemove",""],["","matChipTrailingIcon",""]]],pM=["*","mat-chip-avatar, [matChipAvatar]","mat-chip-trailing-icon,[matChipRemove],[matChipTrailingIcon]"];function hM(t,n){t&1&&(b(0,"span",3),B(1,1),f())}function fM(t,n){t&1&&(b(0,"span",6),B(1,2),f())}var gM=["*"];var _M=new A("mat-chips-default-options",{providedIn:"root",factory:()=>({separatorKeyCodes:[13]})}),Ou=new A("MatChipAvatar"),uv=new A("MatChipTrailingIcon"),pv=new A("MatChipEdit"),hv=new A("MatChipRemove"),fv=new A("MatChip"),gv=(()=>{class t{_elementRef=m(Y);_parentChip=m(fv);_isPrimary=!0;_isLeading=!1;get disabled(){return this._disabled||this._parentChip?.disabled||!1}set disabled(e){this._disabled=e}_disabled=!1;tabIndex=-1;_allowFocusWhenDisabled=!1;_getDisabledAttribute(){return this.disabled&&!this._allowFocusWhenDisabled?"":null}constructor(){m(Je).load(an),this._elementRef.nativeElement.nodeName==="BUTTON"&&this._elementRef.nativeElement.setAttribute("type","button")}focus(){this._elementRef.nativeElement.focus()}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","matChipContent",""]],hostAttrs:[1,"mat-mdc-chip-action","mdc-evolution-chip__action","mdc-evolution-chip__action--presentational"],hostVars:8,hostBindings:function(i,r){i&2&&(le("disabled",r._getDisabledAttribute())("aria-disabled",r.disabled),Q("mdc-evolution-chip__action--primary",r._isPrimary)("mdc-evolution-chip__action--secondary",!r._isPrimary)("mdc-evolution-chip__action--trailing",!r._isPrimary&&!r._isLeading))},inputs:{disabled:[2,"disabled","disabled",Se],tabIndex:[2,"tabIndex","tabIndex",e=>e==null?-1:xo(e)],_allowFocusWhenDisabled:"_allowFocusWhenDisabled"}})}return t})(),bM=(()=>{class t extends gv{_getTabindex(){return this.disabled&&!this._allowFocusWhenDisabled?null:this.tabIndex.toString()}_handleClick(e){!this.disabled&&this._isPrimary&&(e.preventDefault(),this._parentChip._handlePrimaryActionInteraction())}_handleKeydown(e){(e.keyCode===13||e.keyCode===32)&&!this.disabled&&this._isPrimary&&!this._parentChip._isEditing&&(e.preventDefault(),this._parentChip._handlePrimaryActionInteraction())}static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(t)))(r||t)}})();static \u0275dir=G({type:t,selectors:[["","matChipAction",""]],hostVars:3,hostBindings:function(i,r){i&1&&pe("click",function(a){return r._handleClick(a)})("keydown",function(a){return r._handleKeydown(a)}),i&2&&(le("tabindex",r._getTabindex()),Q("mdc-evolution-chip__action--presentational",!1))},features:[Te]})}return t})(),fi=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["mat-chip-avatar"],["","matChipAvatar",""]],hostAttrs:["role","img",1,"mat-mdc-chip-avatar","mdc-evolution-chip__icon","mdc-evolution-chip__icon--primary"],features:[ze([{provide:Ou,useExisting:t}])]})}return t})();var Gn=(()=>{class t{_changeDetectorRef=m(qe);_elementRef=m(Y);_tagName=m(Up);_ngZone=m(K);_focusMonitor=m(xn);_globalRippleOptions=m(Fi,{optional:!0});_document=m(W);_onFocus=new z;_onBlur=new z;_isBasicChip=!1;role=null;_hasFocusInternal=!1;_pendingFocus=!1;_actionChanges;_animationsDisabled=He();_allLeadingIcons;_allTrailingIcons;_allEditIcons;_allRemoveIcons;_hasFocus(){return this._hasFocusInternal}id=m(ut).getId("mat-mdc-chip-");ariaLabel=null;ariaDescription=null;_chipListDisabled=!1;_hadFocusOnRemove=!1;_textElement;get value(){return this._value!==void 0?this._value:this._textElement.textContent.trim()}set value(e){this._value=e}_value;color;removable=!0;highlighted=!1;disableRipple=!1;get disabled(){return this._disabled||this._chipListDisabled}set disabled(e){this._disabled=e}_disabled=!1;removed=new De;destroyed=new De;basicChipAttrName="mat-basic-chip";leadingIcon;editIcon;trailingIcon;removeIcon;primaryAction;_rippleLoader=m(el);_injector=m(te);constructor(){let e=m(Je);e.load(an),e.load(hr),this._monitorFocus(),this._rippleLoader?.configureRipple(this._elementRef.nativeElement,{className:"mat-mdc-chip-ripple",disabled:this._isRippleDisabled()})}ngOnInit(){this._isBasicChip=this._elementRef.nativeElement.hasAttribute(this.basicChipAttrName)||this._tagName.toLowerCase()===this.basicChipAttrName}ngAfterViewInit(){this._textElement=this._elementRef.nativeElement.querySelector(".mat-mdc-chip-action-label"),this._pendingFocus&&(this._pendingFocus=!1,this.focus())}ngAfterContentInit(){this._actionChanges=_t(this._allLeadingIcons.changes,this._allTrailingIcons.changes,this._allEditIcons.changes,this._allRemoveIcons.changes).subscribe(()=>this._changeDetectorRef.markForCheck())}ngDoCheck(){this._rippleLoader.setDisabled(this._elementRef.nativeElement,this._isRippleDisabled())}ngOnDestroy(){this._focusMonitor.stopMonitoring(this._elementRef),this._rippleLoader?.destroyRipple(this._elementRef.nativeElement),this._actionChanges?.unsubscribe(),this.destroyed.emit({chip:this}),this.destroyed.complete()}remove(){this.removable&&(this._hadFocusOnRemove=this._hasFocus(),this.removed.emit({chip:this}))}_isRippleDisabled(){return this.disabled||this.disableRipple||this._animationsDisabled||this._isBasicChip||!this._hasInteractiveActions()||!!this._globalRippleOptions?.disabled}_hasTrailingIcon(){return!!(this.trailingIcon||this.removeIcon)}_handleKeydown(e){(e.keyCode===8&&!e.repeat||e.keyCode===46)&&(e.preventDefault(),this.remove())}focus(){this.disabled||(this.primaryAction?this.primaryAction.focus():this._pendingFocus=!0)}_getSourceAction(e){return this._getActions().find(i=>{let r=i._elementRef.nativeElement;return r===e||r.contains(e)})}_getActions(){let e=[];return this.editIcon&&e.push(this.editIcon),this.primaryAction&&e.push(this.primaryAction),this.removeIcon&&e.push(this.removeIcon),e}_handlePrimaryActionInteraction(){}_hasInteractiveActions(){return this._getActions().length>0}_edit(e){}_monitorFocus(){this._focusMonitor.monitor(this._elementRef,!0).subscribe(e=>{let i=e!==null;i!==this._hasFocusInternal&&(this._hasFocusInternal=i,i?this._onFocus.next({chip:this}):(this._changeDetectorRef.markForCheck(),setTimeout(()=>this._ngZone.run(()=>this._onBlur.next({chip:this})))))})}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-basic-chip"],["","mat-basic-chip",""],["mat-chip"],["","mat-chip",""]],contentQueries:function(i,r,o){if(i&1&&Xe(o,Ou,5)(o,pv,5)(o,uv,5)(o,hv,5)(o,Ou,5)(o,uv,5)(o,pv,5)(o,hv,5),i&2){let a;H(a=U())&&(r.leadingIcon=a.first),H(a=U())&&(r.editIcon=a.first),H(a=U())&&(r.trailingIcon=a.first),H(a=U())&&(r.removeIcon=a.first),H(a=U())&&(r._allLeadingIcons=a),H(a=U())&&(r._allTrailingIcons=a),H(a=U())&&(r._allEditIcons=a),H(a=U())&&(r._allRemoveIcons=a)}},viewQuery:function(i,r){if(i&1&&Ne(bM,5),i&2){let o;H(o=U())&&(r.primaryAction=o.first)}},hostAttrs:[1,"mat-mdc-chip"],hostVars:31,hostBindings:function(i,r){i&1&&pe("keydown",function(a){return r._handleKeydown(a)}),i&2&&(ti("id",r.id),le("role",r.role)("aria-label",r.ariaLabel),vt("mat-"+(r.color||"primary")),Q("mdc-evolution-chip",!r._isBasicChip)("mdc-evolution-chip--disabled",r.disabled)("mdc-evolution-chip--with-trailing-action",r._hasTrailingIcon())("mdc-evolution-chip--with-primary-graphic",r.leadingIcon)("mdc-evolution-chip--with-primary-icon",r.leadingIcon)("mdc-evolution-chip--with-avatar",r.leadingIcon)("mat-mdc-chip-with-avatar",r.leadingIcon)("mat-mdc-chip-highlighted",r.highlighted)("mat-mdc-chip-disabled",r.disabled)("mat-mdc-basic-chip",r._isBasicChip)("mat-mdc-standard-chip",!r._isBasicChip)("mat-mdc-chip-with-trailing-icon",r._hasTrailingIcon())("_mat-animation-noopable",r._animationsDisabled))},inputs:{role:"role",id:"id",ariaLabel:[0,"aria-label","ariaLabel"],ariaDescription:[0,"aria-description","ariaDescription"],value:"value",color:"color",removable:[2,"removable","removable",Se],highlighted:[2,"highlighted","highlighted",Se],disableRipple:[2,"disableRipple","disableRipple",Se],disabled:[2,"disabled","disabled",Se]},outputs:{removed:"removed",destroyed:"destroyed"},exportAs:["matChip"],features:[ze([{provide:fv,useExisting:t}])],ngContentSelectors:pM,decls:8,vars:2,consts:[[1,"mat-mdc-chip-focus-overlay"],[1,"mdc-evolution-chip__cell","mdc-evolution-chip__cell--primary"],["matChipContent",""],[1,"mdc-evolution-chip__graphic","mat-mdc-chip-graphic"],[1,"mdc-evolution-chip__text-label","mat-mdc-chip-action-label"],[1,"mat-mdc-chip-primary-focus-indicator","mat-focus-indicator"],[1,"mdc-evolution-chip__cell","mdc-evolution-chip__cell--trailing"]],template:function(i,r){i&1&&(ne(uM),P(0,"span",0),b(1,"span",1)(2,"span",2),k(3,hM,2,0,"span",3),b(4,"span",4),B(5),P(6,"span",5),f()()(),k(7,fM,2,0,"span",6)),i&2&&(h(3),D(r.leadingIcon?3:-1),h(4),D(r._hasTrailingIcon()?7:-1))},dependencies:[gv],styles:[`.mdc-evolution-chip,
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
`],encapsulation:2,changeDetection:0})}return t})();var Qr=(()=>{class t{_elementRef=m(Y);_changeDetectorRef=m(qe);_dir=m(wt,{optional:!0});_lastDestroyedFocusedChipIndex=null;_keyManager;_destroyed=new z;_defaultRole="presentation";get chipFocusChanges(){return this._getChipStream(e=>e._onFocus)}get chipDestroyedChanges(){return this._getChipStream(e=>e.destroyed)}get chipRemovedChanges(){return this._getChipStream(e=>e.removed)}get disabled(){return this._disabled}set disabled(e){this._disabled=e,this._syncChipsState()}_disabled=!1;get empty(){return!this._chips||this._chips.length===0}get role(){return this._explicitRole?this._explicitRole:this.empty?null:this._defaultRole}tabIndex=0;set role(e){this._explicitRole=e}_explicitRole=null;get focused(){return this._hasFocusedChip()}_chips;_chipActions=new Tn;constructor(){}ngAfterViewInit(){this._setUpFocusManagement(),this._trackChipSetChanges(),this._trackDestroyedFocusedChip()}ngOnDestroy(){this._keyManager?.destroy(),this._chipActions.destroy(),this._destroyed.next(),this._destroyed.complete()}_hasFocusedChip(){return this._chips&&this._chips.some(e=>e._hasFocus())}_syncChipsState(){this._chips?.forEach(e=>{e._chipListDisabled=this._disabled,e._changeDetectorRef.markForCheck()})}focus(){}_handleKeydown(e){this._originatesFromChip(e)&&this._keyManager.onKeydown(e)}_isValidIndex(e){return e>=0&&e<this._chips.length}_allowFocusEscape(){let e=this._elementRef.nativeElement.tabIndex;e!==-1&&(this._elementRef.nativeElement.tabIndex=-1,setTimeout(()=>this._elementRef.nativeElement.tabIndex=e))}_getChipStream(e){return this._chips.changes.pipe(ct(null),$t(()=>_t(...this._chips.map(e))))}_originatesFromChip(e){let i=e.target;for(;i&&i!==this._elementRef.nativeElement;){if(i.classList.contains("mat-mdc-chip"))return!0;i=i.parentElement}return!1}_setUpFocusManagement(){this._chips.changes.pipe(ct(this._chips)).subscribe(e=>{let i=[];e.forEach(r=>r._getActions().forEach(o=>i.push(o))),this._chipActions.reset(i),this._chipActions.notifyOnChanges()}),this._keyManager=new Ai(this._chipActions).withVerticalOrientation().withHorizontalOrientation(this._dir?this._dir.value:"ltr").withHomeAndEnd().skipPredicate(e=>this._skipPredicate(e)),this.chipFocusChanges.pipe(ke(this._destroyed)).subscribe(({chip:e})=>{let i=e._getSourceAction(document.activeElement);i&&this._keyManager.updateActiveItem(i)}),this._dir?.change.pipe(ke(this._destroyed)).subscribe(e=>this._keyManager.withHorizontalOrientation(e))}_skipPredicate(e){return e.disabled}_trackChipSetChanges(){this._chips.changes.pipe(ct(null),ke(this._destroyed)).subscribe(()=>{this.disabled&&Promise.resolve().then(()=>this._syncChipsState()),this._redirectDestroyedChipFocus()})}_trackDestroyedFocusedChip(){this.chipDestroyedChanges.pipe(ke(this._destroyed)).subscribe(e=>{let r=this._chips.toArray().indexOf(e.chip),o=e.chip._hasFocus(),a=e.chip._hadFocusOnRemove&&this._keyManager.activeItem&&e.chip._getActions().includes(this._keyManager.activeItem),s=o||a;this._isValidIndex(r)&&s&&(this._lastDestroyedFocusedChipIndex=r)})}_redirectDestroyedChipFocus(){if(this._lastDestroyedFocusedChipIndex!=null){if(this._chips.length){let e=Math.min(this._lastDestroyedFocusedChipIndex,this._chips.length-1),i=this._chips.toArray()[e];i.disabled?this._chips.length===1?this.focus():this._keyManager.setPreviousItemActive():i.focus()}else this.focus();this._lastDestroyedFocusedChipIndex=null}}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-chip-set"]],contentQueries:function(i,r,o){if(i&1&&Xe(o,Gn,5),i&2){let a;H(a=U())&&(r._chips=a)}},hostAttrs:[1,"mat-mdc-chip-set","mdc-evolution-chip-set"],hostVars:1,hostBindings:function(i,r){i&1&&pe("keydown",function(a){return r._handleKeydown(a)}),i&2&&le("role",r.role)},inputs:{disabled:[2,"disabled","disabled",Se],role:"role",tabIndex:[2,"tabIndex","tabIndex",e=>e==null?0:xo(e)]},ngContentSelectors:gM,decls:2,vars:0,consts:[["role","presentation",1,"mdc-evolution-chip-set__chips"]],template:function(i,r){i&1&&(ne(),Ue(0,"div",0),B(1),Qe())},styles:[`.mat-mdc-chip-set {
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
`],encapsulation:2,changeDetection:0})}return t})();var mn=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({providers:[mv,{provide:_M,useValue:{separatorKeyCodes:[13]}}],imports:[wn,ie]})}return t})();var vM=["*"],yM=`.mdc-list {
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
`,xM=["unscopedContent"],wM=["text"],CM=[[["","matListItemAvatar",""],["","matListItemIcon",""]],[["","matListItemTitle",""]],[["","matListItemLine",""]],"*",[["","matListItemMeta",""]],[["mat-divider"]]],kM=["[matListItemAvatar],[matListItemIcon]","[matListItemTitle]","[matListItemLine]","*","[matListItemMeta]","mat-divider"];var DM=new A("ListOption"),Pu=(()=>{class t{_elementRef=m(Y);constructor(){}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","matListItemTitle",""]],hostAttrs:[1,"mat-mdc-list-item-title","mdc-list-item__primary-text"]})}return t})(),Fu=(()=>{class t{_elementRef=m(Y);constructor(){}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","matListItemLine",""]],hostAttrs:[1,"mat-mdc-list-item-line","mdc-list-item__secondary-text"]})}return t})(),EM=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,selectors:[["","matListItemMeta",""]],hostAttrs:[1,"mat-mdc-list-item-meta","mdc-list-item__end"]})}return t})(),_v=(()=>{class t{_listOption=m(DM,{optional:!0});constructor(){}_isAlignedAtStart(){return!this._listOption||this._listOption?._getTogglePosition()==="after"}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,hostVars:4,hostBindings:function(i,r){i&2&&Q("mdc-list-item__start",r._isAlignedAtStart())("mdc-list-item__end",!r._isAlignedAtStart())}})}return t})(),SM=(()=>{class t extends _v{static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(t)))(r||t)}})();static \u0275dir=G({type:t,selectors:[["","matListItemAvatar",""]],hostAttrs:[1,"mat-mdc-list-item-avatar"],features:[Te]})}return t})(),Nu=(()=>{class t extends _v{static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(t)))(r||t)}})();static \u0275dir=G({type:t,selectors:[["","matListItemIcon",""]],hostAttrs:[1,"mat-mdc-list-item-icon"],features:[Te]})}return t})(),MM=new A("MAT_LIST_CONFIG"),Ru=(()=>{class t{_isNonInteractive=!0;get disableRipple(){return this._disableRipple}set disableRipple(e){this._disableRipple=kt(e)}_disableRipple=!1;get disabled(){return this._disabled()}set disabled(e){this._disabled.set(kt(e))}_disabled=X(!1);_defaultOptions=m(MM,{optional:!0});static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,hostVars:1,hostBindings:function(i,r){i&2&&le("aria-disabled",r.disabled)},inputs:{disableRipple:"disableRipple",disabled:"disabled"}})}return t})(),AM=(()=>{class t{_elementRef=m(Y);_ngZone=m(K);_listBase=m(Ru,{optional:!0});_platform=m(we);_hostElement;_isButtonElement;_noopAnimations=He();_avatars;_icons;set lines(e){this._explicitLines=Nn(e,null),this._updateItemLines(!1)}_explicitLines=null;get disableRipple(){return this.disabled||this._disableRipple||this._noopAnimations||!!this._listBase?.disableRipple}set disableRipple(e){this._disableRipple=kt(e)}_disableRipple=!1;get disabled(){return this._disabled()||!!this._listBase?.disabled}set disabled(e){this._disabled.set(kt(e))}_disabled=X(!1);_subscriptions=new lt;_rippleRenderer=null;_hasUnscopedTextContent=!1;rippleConfig;get rippleDisabled(){return this.disableRipple||!!this.rippleConfig.disabled}constructor(){m(Je).load(an);let e=m(Fi,{optional:!0});this.rippleConfig=e||{},this._hostElement=this._elementRef.nativeElement,this._isButtonElement=this._hostElement.nodeName.toLowerCase()==="button",this._listBase&&!this._listBase._isNonInteractive&&this._initInteractiveListItem(),this._isButtonElement&&!this._hostElement.hasAttribute("type")&&this._hostElement.setAttribute("type","button")}ngAfterViewInit(){this._monitorProjectedLinesAndTitle(),this._updateItemLines(!0)}ngOnDestroy(){this._subscriptions.unsubscribe(),this._rippleRenderer!==null&&this._rippleRenderer._removeTriggerEvents()}_hasIconOrAvatar(){return!!(this._avatars.length||this._icons.length)}_initInteractiveListItem(){this._hostElement.classList.add("mat-mdc-list-item-interactive"),this._rippleRenderer=new Pi(this,this._ngZone,this._hostElement,this._platform,m(te)),this._rippleRenderer.setupTriggerEvents(this._hostElement)}_monitorProjectedLinesAndTitle(){this._ngZone.runOutsideAngular(()=>{this._subscriptions.add(_t(this._lines.changes,this._titles.changes).subscribe(()=>this._updateItemLines(!1)))})}_updateItemLines(e){if(!this._lines||!this._titles||!this._unscopedContent)return;e&&this._checkDomForUnscopedTextContent();let i=this._explicitLines??this._inferLinesFromContent(),r=this._unscopedContent.nativeElement;if(this._hostElement.classList.toggle("mat-mdc-list-item-single-line",i<=1),this._hostElement.classList.toggle("mdc-list-item--with-one-line",i<=1),this._hostElement.classList.toggle("mdc-list-item--with-two-lines",i===2),this._hostElement.classList.toggle("mdc-list-item--with-three-lines",i===3),this._hasUnscopedTextContent){let o=this._titles.length===0&&i===1;r.classList.toggle("mdc-list-item__primary-text",o),r.classList.toggle("mdc-list-item__secondary-text",!o)}else r.classList.remove("mdc-list-item__primary-text"),r.classList.remove("mdc-list-item__secondary-text")}_inferLinesFromContent(){let e=this._titles.length+this._lines.length;return this._hasUnscopedTextContent&&(e+=1),e}_checkDomForUnscopedTextContent(){this._hasUnscopedTextContent=Array.from(this._unscopedContent.nativeElement.childNodes).filter(e=>e.nodeType!==e.COMMENT_NODE).some(e=>!!(e.textContent&&e.textContent.trim()))}static \u0275fac=function(i){return new(i||t)};static \u0275dir=G({type:t,contentQueries:function(i,r,o){if(i&1&&Xe(o,SM,4)(o,Nu,4),i&2){let a;H(a=U())&&(r._avatars=a),H(a=U())&&(r._icons=a)}},hostVars:4,hostBindings:function(i,r){i&2&&(le("aria-disabled",r.disabled)("disabled",r._isButtonElement&&r.disabled||null),Q("mdc-list-item--disabled",r.disabled))},inputs:{lines:"lines",disableRipple:"disableRipple",disabled:"disabled"}})}return t})();var bv=(()=>{class t extends Ru{static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(t)))(r||t)}})();static \u0275cmp=T({type:t,selectors:[["mat-list"]],hostAttrs:[1,"mat-mdc-list","mat-mdc-list-base","mdc-list"],exportAs:["matList"],features:[ze([{provide:Ru,useExisting:t}]),Te],ngContentSelectors:vM,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},styles:[yM],encapsulation:2,changeDetection:0})}return t})(),vv=(()=>{class t extends AM{_lines;_titles;_meta;_unscopedContent;_itemText;get activated(){return this._activated}set activated(e){this._activated=kt(e)}_activated=!1;_getAriaCurrent(){return this._hostElement.nodeName==="A"&&this._activated?"page":null}_hasBothLeadingAndTrailing(){return this._meta.length!==0&&(this._avatars.length!==0||this._icons.length!==0)}static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(t)))(r||t)}})();static \u0275cmp=T({type:t,selectors:[["mat-list-item"],["a","mat-list-item",""],["button","mat-list-item",""]],contentQueries:function(i,r,o){if(i&1&&Xe(o,Fu,5)(o,Pu,5)(o,EM,5),i&2){let a;H(a=U())&&(r._lines=a),H(a=U())&&(r._titles=a),H(a=U())&&(r._meta=a)}},viewQuery:function(i,r){if(i&1&&Ne(xM,5)(wM,5),i&2){let o;H(o=U())&&(r._unscopedContent=o.first),H(o=U())&&(r._itemText=o.first)}},hostAttrs:[1,"mat-mdc-list-item","mdc-list-item"],hostVars:13,hostBindings:function(i,r){i&2&&(le("aria-current",r._getAriaCurrent()),Q("mdc-list-item--activated",r.activated)("mdc-list-item--with-leading-avatar",r._avatars.length!==0)("mdc-list-item--with-leading-icon",r._icons.length!==0)("mdc-list-item--with-trailing-meta",r._meta.length!==0)("mat-mdc-list-item-both-leading-and-trailing",r._hasBothLeadingAndTrailing())("_mat-animation-noopable",r._noopAnimations))},inputs:{activated:"activated"},exportAs:["matListItem"],features:[Te],ngContentSelectors:kM,decls:10,vars:0,consts:[["unscopedContent",""],[1,"mdc-list-item__content"],[1,"mat-mdc-list-item-unscoped-content",3,"cdkObserveContent"],[1,"mat-focus-indicator"]],template:function(i,r){i&1&&(ne(CM),B(0),b(1,"span",1),B(2,1),B(3,2),b(4,"span",2,0),pe("cdkObserveContent",function(){return r._updateItemLines(!0)}),B(6,3),f()(),B(7,4),B(8,5),P(9,"div",3))},dependencies:[xf],encapsulation:2,changeDetection:0})}return t})();var Ma=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[Fs,wn,Vl,ie,ka]})}return t})();var ql=["*"],TM=["content"],OM=[[["mat-drawer"]],[["mat-drawer-content"]],"*"],RM=["mat-drawer","mat-drawer-content","*"];function PM(t,n){if(t&1){let e=ot();b(0,"div",1),pe("click",function(){Ke(e);let r=C();return Ye(r._onBackdropClicked())}),f()}if(t&2){let e=C();Q("mat-drawer-shown",e._isShowingBackdrop())}}function FM(t,n){t&1&&(b(0,"mat-drawer-content"),B(1,2),f())}var NM=[[["mat-sidenav"]],[["mat-sidenav-content"]],"*"],LM=["mat-sidenav","mat-sidenav-content","*"];function BM(t,n){if(t&1){let e=ot();b(0,"div",1),pe("click",function(){Ke(e);let r=C();return Ye(r._onBackdropClicked())}),f()}if(t&2){let e=C();Q("mat-drawer-shown",e._isShowingBackdrop())}}function $M(t,n){t&1&&(b(0,"mat-sidenav-content"),B(1,2),f())}var jM=`.mat-drawer-container {
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
`;var VM=new A("MAT_DRAWER_DEFAULT_AUTOSIZE",{providedIn:"root",factory:()=>!1}),$u=new A("MAT_DRAWER_CONTAINER"),Hl=(()=>{class t extends vr{_platform=m(we);_changeDetectorRef=m(qe);_container=m(Bu);constructor(){let e=m(Y),i=m(Ti),r=m(K);super(e,i,r)}ngAfterContentInit(){this._container._contentMarginChanges.subscribe(()=>{this._changeDetectorRef.markForCheck()})}_shouldBeHidden(){if(this._platform.isBrowser)return!1;let{start:e,end:i}=this._container;return e!=null&&e.mode!=="over"&&e.opened||i!=null&&i.mode!=="over"&&i.opened}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-drawer-content"]],hostAttrs:[1,"mat-drawer-content"],hostVars:6,hostBindings:function(i,r){i&2&&(vi("margin-left",r._container._contentMargins.left,"px")("margin-right",r._container._contentMargins.right,"px"),Q("mat-drawer-content-hidden",r._shouldBeHidden()))},features:[ze([{provide:vr,useExisting:t}]),Te],ngContentSelectors:ql,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},encapsulation:2,changeDetection:0})}return t})(),Lu=(()=>{class t{_elementRef=m(Y);_focusTrapFactory=m(hd);_focusMonitor=m(xn);_platform=m(we);_ngZone=m(K);_renderer=m(rt);_interactivityChecker=m(Ls);_doc=m(W);_container=m($u,{optional:!0});_focusTrap=null;_elementFocusedBeforeDrawerWasOpened=null;_eventCleanups;_isAttached=!1;_anchor=null;get position(){return this._position}set position(e){e=e==="end"?"end":"start",e!==this._position&&(this._isAttached&&this._updatePositionInParent(e),this._position=e,this.onPositionChanged.emit())}_position="start";get mode(){return this._mode}set mode(e){this._mode=e,this._updateFocusTrapState(),this._modeChanged.next()}_mode="over";get disableClose(){return this._disableClose}set disableClose(e){this._disableClose=kt(e)}_disableClose=!1;get autoFocus(){let e=this._autoFocus;return e??(this.mode==="side"?"dialog":"first-tabbable")}set autoFocus(e){(e==="true"||e==="false"||e==null)&&(e=kt(e)),this._autoFocus=e}_autoFocus;get opened(){return this._opened()}set opened(e){this.toggle(kt(e))}_opened=X(!1);_openedVia=null;_animationStarted=new z;_animationEnd=new z;openedChange=new De(!0);_openedStream=this.openedChange.pipe(Be(e=>e),ye(()=>{}));openedStart=this._animationStarted.pipe(Be(()=>this.opened),co(void 0));_closedStream=this.openedChange.pipe(Be(e=>!e),ye(()=>{}));closedStart=this._animationStarted.pipe(Be(()=>!this.opened),co(void 0));_destroyed=new z;onPositionChanged=new De;_content;_modeChanged=new z;_injector=m(te);_changeDetectorRef=m(qe);constructor(){this.openedChange.pipe(ke(this._destroyed)).subscribe(e=>{e?(this._elementFocusedBeforeDrawerWasOpened=this._doc.activeElement,this._takeFocus()):this._isFocusWithinDrawer()&&this._restoreFocus(this._openedVia||"program")}),this._eventCleanups=this._ngZone.runOutsideAngular(()=>{let e=this._renderer,i=this._elementRef.nativeElement;return[e.listen(i,"keydown",r=>{r.keyCode===27&&!this.disableClose&&!oi(r)&&this._ngZone.run(()=>{this.close(),r.stopPropagation(),r.preventDefault()})}),e.listen(i,"transitionend",this._handleTransitionEvent),e.listen(i,"transitioncancel",this._handleTransitionEvent)]}),this._animationEnd.subscribe(()=>{this.openedChange.emit(this.opened)})}_forceFocus(e,i){this._interactivityChecker.isFocusable(e)||(e.tabIndex=-1,this._ngZone.runOutsideAngular(()=>{let r=()=>{o(),a(),e.removeAttribute("tabindex")},o=this._renderer.listen(e,"blur",r),a=this._renderer.listen(e,"mousedown",r)})),e.focus(i)}_focusByCssSelector(e,i){let r=this._elementRef.nativeElement.querySelector(e);r&&this._forceFocus(r,i)}_takeFocus(){if(!this._focusTrap)return;let e=this._elementRef.nativeElement;switch(this.autoFocus){case!1:case"dialog":return;case!0:case"first-tabbable":mt(()=>{!this._focusTrap.focusInitialElement()&&typeof e.focus=="function"&&e.focus()},{injector:this._injector});break;case"first-heading":this._focusByCssSelector('h1, h2, h3, h4, h5, h6, [role="heading"]');break;default:this._focusByCssSelector(this.autoFocus);break}}_restoreFocus(e){this.autoFocus!=="dialog"&&(this._elementFocusedBeforeDrawerWasOpened?this._focusMonitor.focusVia(this._elementFocusedBeforeDrawerWasOpened,e):this._elementRef.nativeElement.blur(),this._elementFocusedBeforeDrawerWasOpened=null)}_isFocusWithinDrawer(){let e=this._doc.activeElement;return!!e&&this._elementRef.nativeElement.contains(e)}ngAfterViewInit(){this._isAttached=!0,this._position==="end"&&this._updatePositionInParent("end"),this._platform.isBrowser&&(this._focusTrap=this._focusTrapFactory.create(this._elementRef.nativeElement),this._updateFocusTrapState())}ngOnDestroy(){this._eventCleanups.forEach(e=>e()),this._focusTrap?.destroy(),this._anchor?.remove(),this._anchor=null,this._animationStarted.complete(),this._animationEnd.complete(),this._modeChanged.complete(),this._destroyed.next(),this._destroyed.complete()}open(e){return this.toggle(!0,e)}close(){return this.toggle(!1)}_closeViaBackdropClick(){return this._setOpen(!1,!0,"mouse")}toggle(e=!this.opened,i){e&&i&&(this._openedVia=i);let r=this._setOpen(e,!e&&this._isFocusWithinDrawer(),this._openedVia||"program");return e||(this._openedVia=null),r}_setOpen(e,i,r){return e===this.opened?Promise.resolve(e?"open":"close"):(this._opened.set(e),this._container?._transitionsEnabled?(this._setIsAnimating(!0),setTimeout(()=>this._animationStarted.next())):setTimeout(()=>{this._animationStarted.next(),this._animationEnd.next()}),this._elementRef.nativeElement.classList.toggle("mat-drawer-opened",e),!e&&i&&this._restoreFocus(r),this._changeDetectorRef.markForCheck(),this._updateFocusTrapState(),new Promise(o=>{this.openedChange.pipe(Qn(1)).subscribe(a=>o(a?"open":"close"))}))}_setIsAnimating(e){this._elementRef.nativeElement.classList.toggle("mat-drawer-animating",e)}_getWidth(){return this._elementRef.nativeElement.offsetWidth||0}_updateFocusTrapState(){this._focusTrap&&(this._focusTrap.enabled=this.opened&&!!this._container?._isShowingBackdrop())}_updatePositionInParent(e){if(!this._platform.isBrowser)return;let i=this._elementRef.nativeElement,r=i.parentNode;e==="end"?(this._anchor||(this._anchor=this._doc.createComment("mat-drawer-anchor"),r.insertBefore(this._anchor,i)),r.appendChild(i)):this._anchor&&this._anchor.parentNode.insertBefore(i,this._anchor)}_handleTransitionEvent=e=>{let i=this._elementRef.nativeElement;e.target===i&&this._ngZone.run(()=>{e.type==="transitionend"&&this._setIsAnimating(!1),this._animationEnd.next(e)})};static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-drawer"]],viewQuery:function(i,r){if(i&1&&Ne(TM,5),i&2){let o;H(o=U())&&(r._content=o.first)}},hostAttrs:[1,"mat-drawer"],hostVars:12,hostBindings:function(i,r){i&2&&(le("align",null)("tabIndex",r.mode!=="side"?"-1":null),vi("visibility",!r._container&&!r.opened?"hidden":null),Q("mat-drawer-end",r.position==="end")("mat-drawer-over",r.mode==="over")("mat-drawer-push",r.mode==="push")("mat-drawer-side",r.mode==="side"))},inputs:{position:"position",mode:"mode",disableClose:"disableClose",autoFocus:"autoFocus",opened:"opened"},outputs:{openedChange:"openedChange",_openedStream:"opened",openedStart:"openedStart",_closedStream:"closed",closedStart:"closedStart",onPositionChanged:"positionChanged"},exportAs:["matDrawer"],ngContentSelectors:ql,decls:3,vars:0,consts:[["content",""],["cdkScrollable","",1,"mat-drawer-inner-container"]],template:function(i,r){i&1&&(ne(),b(0,"div",1,0),B(2),f())},dependencies:[vr],encapsulation:2,changeDetection:0})}return t})(),Bu=(()=>{class t{_dir=m(wt,{optional:!0});_element=m(Y);_ngZone=m(K);_changeDetectorRef=m(qe);_animationDisabled=He();_transitionsEnabled=!1;_allDrawers;_drawers=new Tn;_content;_userContent;get start(){return this._start}get end(){return this._end}get autosize(){return this._autosize}set autosize(e){this._autosize=kt(e)}_autosize=m(VM);get hasBackdrop(){return this._drawerHasBackdrop(this._start)||this._drawerHasBackdrop(this._end)}set hasBackdrop(e){this._backdropOverride=e==null?null:kt(e)}_backdropOverride=null;backdropClick=new De;_start=null;_end=null;_left=null;_right=null;_destroyed=new z;_doCheckSubject=new z;_contentMargins={left:null,right:null};_contentMarginChanges=new z;get scrollable(){return this._userContent||this._content}_injector=m(te);constructor(){let e=m(we),i=m(Bn);this._dir?.change.pipe(ke(this._destroyed)).subscribe(()=>{this._validateDrawers(),this.updateContentMargins()}),i.change().pipe(ke(this._destroyed)).subscribe(()=>this.updateContentMargins()),!this._animationDisabled&&e.isBrowser&&this._ngZone.runOutsideAngular(()=>{setTimeout(()=>{this._element.nativeElement.classList.add("mat-drawer-transition"),this._transitionsEnabled=!0},200)})}ngAfterContentInit(){this._allDrawers.changes.pipe(ct(this._allDrawers),ke(this._destroyed)).subscribe(e=>{this._drawers.reset(e.filter(i=>!i._container||i._container===this)),this._drawers.notifyOnChanges()}),this._drawers.changes.pipe(ct(null)).subscribe(()=>{this._validateDrawers(),this._drawers.forEach(e=>{this._watchDrawerToggle(e),this._watchDrawerPosition(e),this._watchDrawerMode(e)}),(!this._drawers.length||this._isDrawerOpen(this._start)||this._isDrawerOpen(this._end))&&this.updateContentMargins(),this._changeDetectorRef.markForCheck()}),this._ngZone.runOutsideAngular(()=>{this._doCheckSubject.pipe(Yn(10),ke(this._destroyed)).subscribe(()=>this.updateContentMargins())})}ngOnDestroy(){this._contentMarginChanges.complete(),this._doCheckSubject.complete(),this._drawers.destroy(),this._destroyed.next(),this._destroyed.complete()}open(){this._drawers.forEach(e=>e.open())}close(){this._drawers.forEach(e=>e.close())}updateContentMargins(){let e=0,i=0;if(this._left&&this._left.opened){if(this._left.mode=="side")e+=this._left._getWidth();else if(this._left.mode=="push"){let r=this._left._getWidth();e+=r,i-=r}}if(this._right&&this._right.opened){if(this._right.mode=="side")i+=this._right._getWidth();else if(this._right.mode=="push"){let r=this._right._getWidth();i+=r,e-=r}}e=e||null,i=i||null,(e!==this._contentMargins.left||i!==this._contentMargins.right)&&(this._contentMargins={left:e,right:i},this._ngZone.run(()=>this._contentMarginChanges.next(this._contentMargins)))}ngDoCheck(){this._autosize&&this._isPushed()&&this._ngZone.runOutsideAngular(()=>this._doCheckSubject.next())}_watchDrawerToggle(e){e._animationStarted.pipe(ke(this._drawers.changes)).subscribe(()=>{this.updateContentMargins(),this._changeDetectorRef.markForCheck()}),e.mode!=="side"&&e.openedChange.pipe(ke(this._drawers.changes)).subscribe(()=>this._setContainerClass(e.opened))}_watchDrawerPosition(e){e.onPositionChanged.pipe(ke(this._drawers.changes)).subscribe(()=>{mt({read:()=>this._validateDrawers()},{injector:this._injector})})}_watchDrawerMode(e){e._modeChanged.pipe(ke(_t(this._drawers.changes,this._destroyed))).subscribe(()=>{this.updateContentMargins(),this._changeDetectorRef.markForCheck()})}_setContainerClass(e){let i=this._element.nativeElement.classList,r="mat-drawer-container-has-open";e?i.add(r):i.remove(r)}_validateDrawers(){this._start=this._end=null,this._drawers.forEach(e=>{e.position=="end"?(this._end!=null,this._end=e):(this._start!=null,this._start=e)}),this._right=this._left=null,this._dir&&this._dir.value==="rtl"?(this._left=this._end,this._right=this._start):(this._left=this._start,this._right=this._end)}_isPushed(){return this._isDrawerOpen(this._start)&&this._start.mode!="over"||this._isDrawerOpen(this._end)&&this._end.mode!="over"}_onBackdropClicked(){this.backdropClick.emit(),this._closeModalDrawersViaBackdrop()}_closeModalDrawersViaBackdrop(){[this._start,this._end].filter(e=>e&&!e.disableClose&&this._drawerHasBackdrop(e)).forEach(e=>e._closeViaBackdropClick())}_isShowingBackdrop(){return this._isDrawerOpen(this._start)&&this._drawerHasBackdrop(this._start)||this._isDrawerOpen(this._end)&&this._drawerHasBackdrop(this._end)}_isDrawerOpen(e){return e!=null&&e.opened}_drawerHasBackdrop(e){return this._backdropOverride==null?!!e&&e.mode!=="side":this._backdropOverride}static \u0275fac=function(i){return new(i||t)};static \u0275cmp=T({type:t,selectors:[["mat-drawer-container"]],contentQueries:function(i,r,o){if(i&1&&Xe(o,Hl,5)(o,Lu,5),i&2){let a;H(a=U())&&(r._content=a.first),H(a=U())&&(r._allDrawers=a)}},viewQuery:function(i,r){if(i&1&&Ne(Hl,5),i&2){let o;H(o=U())&&(r._userContent=o.first)}},hostAttrs:[1,"mat-drawer-container"],hostVars:2,hostBindings:function(i,r){i&2&&Q("mat-drawer-container-explicit-backdrop",r._backdropOverride)},inputs:{autosize:"autosize",hasBackdrop:"hasBackdrop"},outputs:{backdropClick:"backdropClick"},exportAs:["matDrawerContainer"],features:[ze([{provide:$u,useExisting:t}])],ngContentSelectors:RM,decls:4,vars:2,consts:[[1,"mat-drawer-backdrop",3,"mat-drawer-shown"],[1,"mat-drawer-backdrop",3,"click"]],template:function(i,r){i&1&&(ne(OM),k(0,PM,1,2,"div",0),B(1),B(2,1),k(3,FM,2,0,"mat-drawer-content")),i&2&&(D(r.hasBackdrop?0:-1),h(3),D(r._content?-1:3))},dependencies:[Hl],styles:[`.mat-drawer-container {
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
`],encapsulation:2,changeDetection:0})}return t})(),Ul=(()=>{class t extends Hl{static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(t)))(r||t)}})();static \u0275cmp=T({type:t,selectors:[["mat-sidenav-content"]],hostAttrs:[1,"mat-drawer-content","mat-sidenav-content"],features:[ze([{provide:vr,useExisting:t}]),Te],ngContentSelectors:ql,decls:1,vars:0,template:function(i,r){i&1&&(ne(),B(0))},encapsulation:2,changeDetection:0})}return t})(),ju=(()=>{class t extends Lu{get fixedInViewport(){return this._fixedInViewport}set fixedInViewport(e){this._fixedInViewport=kt(e)}_fixedInViewport=!1;get fixedTopGap(){return this._fixedTopGap}set fixedTopGap(e){this._fixedTopGap=Nn(e)}_fixedTopGap=0;get fixedBottomGap(){return this._fixedBottomGap}set fixedBottomGap(e){this._fixedBottomGap=Nn(e)}_fixedBottomGap=0;static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(t)))(r||t)}})();static \u0275cmp=T({type:t,selectors:[["mat-sidenav"]],hostAttrs:[1,"mat-drawer","mat-sidenav"],hostVars:16,hostBindings:function(i,r){i&2&&(le("tabIndex",r.mode!=="side"?"-1":null)("align",null),vi("top",r.fixedInViewport?r.fixedTopGap:null,"px")("bottom",r.fixedInViewport?r.fixedBottomGap:null,"px"),Q("mat-drawer-end",r.position==="end")("mat-drawer-over",r.mode==="over")("mat-drawer-push",r.mode==="push")("mat-drawer-side",r.mode==="side")("mat-sidenav-fixed",r.fixedInViewport))},inputs:{fixedInViewport:"fixedInViewport",fixedTopGap:"fixedTopGap",fixedBottomGap:"fixedBottomGap"},exportAs:["matSidenav"],features:[ze([{provide:Lu,useExisting:t}]),Te],ngContentSelectors:ql,decls:3,vars:0,consts:[["content",""],["cdkScrollable","",1,"mat-drawer-inner-container"]],template:function(i,r){i&1&&(ne(),b(0,"div",1,0),B(2),f())},dependencies:[vr],encapsulation:2,changeDetection:0})}return t})(),yv=(()=>{class t extends Bu{_allDrawers=void 0;_content=void 0;static \u0275fac=(()=>{let e;return function(r){return(e||(e=et(t)))(r||t)}})();static \u0275cmp=T({type:t,selectors:[["mat-sidenav-container"]],contentQueries:function(i,r,o){if(i&1&&Xe(o,Ul,5)(o,ju,5),i&2){let a;H(a=U())&&(r._content=a.first),H(a=U())&&(r._allDrawers=a)}},hostAttrs:[1,"mat-drawer-container","mat-sidenav-container"],hostVars:2,hostBindings:function(i,r){i&2&&Q("mat-drawer-container-explicit-backdrop",r._backdropOverride)},exportAs:["matSidenavContainer"],features:[ze([{provide:$u,useExisting:t},{provide:Bu,useExisting:t}]),Te],ngContentSelectors:LM,decls:4,vars:2,consts:[[1,"mat-drawer-backdrop",3,"mat-drawer-shown"],[1,"mat-drawer-backdrop",3,"click"]],template:function(i,r){i&1&&(ne(NM),k(0,BM,1,2,"div",0),B(1),B(2,1),k(3,$M,2,0,"mat-sidenav-content")),i&2&&(D(r.hasBackdrop?0:-1),h(3),D(r._content?-1:3))},dependencies:[Ul],styles:[jM],encapsulation:2,changeDetection:0})}return t})(),Aa=(()=>{class t{static \u0275fac=function(i){return new(i||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[Ln,ie,Ln]})}return t})();var HM=[wa,jn,Ct,Ca,qn,ku,Eu,ka,Ma,Da,Rd,Ea,Sa,mn,Aa],Gl=class t{static \u0275fac=function(e){return new(e||t)};static \u0275mod=V({type:t});static \u0275inj=$({imports:[HM,wa,jn,Ct,Ca,qn,ku,Eu,ka,Ma,Da,Rd,Ea,Sa,mn,Aa]})};var Wl=class t{data=yt.required({transform:n=>"```json\n"+JSON.stringify(n,null,2)+"\n```"});static \u0275fac=function(e){return new(e||t)};static \u0275cmp=T({type:t,selectors:[["app-json"]],inputs:{data:[1,"data"]},decls:1,vars:1,consts:[[3,"data"]],template:function(e,i){e&1&&P(0,"markdown",0),e&2&&R("data",i.data())},dependencies:[vn,bn],encapsulation:2})};var Kl={},Xr=Symbol(),Gi=Symbol(),wv=t=>typeof t=="string"?ue[t]:t,ue={plain:Kl,plaintext:Kl,text:Kl,txt:Kl},Oa=(t,n)=>(n[Gi]||Vu)(t,n),Vu=(t,n)=>{for(var e=[t],i,r=[],o=0;i=wv(n[Xr]);)delete n[Xr],Object.assign(n,i);for(kv(t,n,e,0);r[o++]=e[0],e=e[1];);return r},zu=(t,n,e)=>t.replace(/&/g,"&amp;").replace(n,e),xv="</span>",Yl="",Ia="",Hu=t=>{for(var n="",e,i=0;e=t[i++];)n+=Cv(e);return n},Cv=t=>{if(t instanceof Ta){var{type:n,alias:e,content:i}=t,r=Yl,o=Ia,a=`<span class="token ${n+(e?" "+e:"")+(n=="keyword"&&typeof i=="string"?" keyword-"+i:"")}">`;Ia+=xv,Yl+=a;var s=Cv(i);return Yl=r,Ia=o,a+s+xv}return typeof t!="string"?Hu(t):(t=zu(t,/</g,"&lt;"),Ia&&t.includes(`
`)?t.replace(/\n/g,Ia+`
`+Yl):t)};var kv=(t,n,e,i,r)=>{for(var o in n)if(n[o])for(var a=0,s=n[o],l,c=Array.isArray(s)?s:[s];l=c[a];a++){if(r&&r[0]==o&&r[1]==a)return;for(var d=l.pattern||l,u=wv(l.inside),p=l.lookbehind,y=d.global,g=l.alias,_=e,v=i;_&&(!r||v<r[2]);v+=_[0].length,_=_[1]){var x=_[0],S=0,I;if(!(x instanceof Ta)){if(d.lastIndex=y?v:0,I=d.exec(y?t:x),!I&&y)break;if(I&&I[0]){var F=p&&I[1]?I[1].length:0,N=I.index+F,ge=I[0].slice(F),Ce=N+ge.length,Pt,s;if(y){for(;s=v+_[0].length,N>=s;_=_[1],v=s);if(_[0]instanceof Ta)continue;for(Pt=_,s=v;(s+=Pt[0].length)<Ce;Pt=Pt[1],S++);x=t.slice(v,s),N-=v,Ce-=v}for(var Ft=x.slice(Ce),Nt=v+x.length,Mn=new Ta(o,u?Oa(ge,u):ge,ge,g),it=_,Wn=0,pn;it=it[1],Wn++<S;);Ft&&(!it||it[0]instanceof Ta?it=[Ft,it]:it[0]=Ft+it[0]),v+=N,_[0]=N?x.slice(0,N):Mn,N?_=_[1]=[Mn,it]:_[1]=it,S&&(kv(t,n,_,v,pn=[o,a,Nt]),Nt=pn[2]),r&&Nt>r[2]&&(r[2]=Nt)}}}}};function Ta(t,n,e,i){this.type=t,this.content=n,this.alias=i,this.length=e.length}var Ra=(t,n,...e)=>{let i,r=[],o,a="",s,l=!1,c=!0,d=[],u,p=0,y=qM(),g=y.firstChild,_=g.children,v=_[0],x=v.firstChild,S={language:"text",value:a},I=new Set(e),F={},N=oe=>{Object.assign(S,oe);let de=a!=(a=oe.value??a),Lt=i!=(i=S.language);u=!!S.readOnly,y.style.tabSize=S.tabSize||2,x.inputMode=u?"none":"",x.setAttribute("aria-readonly",u),Pt(),Ce(),de&&(l||x.remove(),x.value=a,x.selectionEnd=0,l||v.prepend(x)),(de||Lt)&&ge()},ge=()=>{d=Oa(a=x.value,ue[i]||{}),it("tokenize",d,i,a);let oe=Hu(d).split(`
`),de=0,Lt=p,_i=p=oe.length;for(;oe[de]==r[de]&&de<_i;)++de;for(;_i&&oe[--_i]==r[--Lt];);if(de==_i&&de==Lt)_[de+1].innerHTML=oe[de]+`
`;else{let _p=Lt<de?Lt:de-1,so=_p,gc="";for(;so<_i;)gc+=`<div class=pce-line aria-hidden=true>${oe[++so]}
</div>`;for(so=_i<de?_i:de-1;so<Lt;so++)_[de+1].remove();gc&&_[_p+1].insertAdjacentHTML("afterend",gc),y.style.setProperty("--number-width",(0|Math.log10(p))+1+".001ch")}it("update",a),Wn(!0),c&&setTimeout(setTimeout,0,()=>c=!0),r=oe,c=!1},Ce=oe=>{(oe||I).forEach(de=>{typeof de=="object"?(de.update(pn,S),oe&&I.add(de)):(de(pn,S),oe||I.delete(de))})},Pt=([oe,de]=Ft())=>{y.className=`prism-code-editor language-${i}${S.lineNumbers==!1?"":" show-line-numbers"} pce-${S.wordWrap?"":"no"}wrap${S.rtl?" pce-rtl":""} pce-${oe<de?"has":"no"}-selection${l?" pce-focus":""}${u?" pce-readonly":""}${S.class?" "+S.class:""}`},Ft=()=>[x.selectionStart,x.selectionEnd,x.selectionDirection],Nt={Escape(){x.blur()}},Mn={},it=(oe,...de)=>{F[oe]?.forEach(Lt=>Lt.apply(pn,de)),S["on"+oe[0].toUpperCase()+oe.slice(1)]?.(...de,pn)},Wn=oe=>{if(oe||c){let de=Ft(),Lt=_[s=Ql(a,0,de[de[2]<"f"?0:1])];Lt!=o&&(o?.classList.remove("active-line"),Lt.classList.add("active-line"),o=Lt),Pt(de),it("selectionChange",de,a)}},pn={container:y,wrapper:g,lines:_,textarea:x,get activeLine(){return s},get value(){return a},options:S,get focused(){return l},get tokens(){return d},inputCommandMap:Mn,keyCommandMap:Nt,extensions:{},setOptions:N,update:ge,getSelection:Ft,addExtensions(...oe){Ce(oe)},on:(oe,de)=>((F[oe]||=new Set).add(de),()=>F[oe].delete(de)),remove(){y.remove()}};return Qt(x,"keydown",oe=>{Nt[oe.key]?.(oe,Ft(),a)&&Wi(oe)}),Qt(x,"beforeinput",oe=>{(u||oe.inputType=="insertText"&&Mn[oe.data]?.(oe,Ft(),a))&&Wi(oe)}),Qt(x,"input",ge),Qt(x,"blur",()=>{qu=null,l=!1,Pt()}),Qt(x,"focus",()=>{qu=Wn,l=!0,Pt()}),Qt(x,"selectionchange",oe=>{Wn(!oe.isTrusted),Wi(oe)}),UM(t)?.append(y),n&&N(n),pn};var Xt="u">typeof window?document:null,Uu=Xt?.createElement("div"),Gu=(t,n)=>(Uu&&(Uu.innerHTML=t,n=Uu.firstChild),()=>n.cloneNode(!0)),Qt=(t,n,e,i)=>t.addEventListener(n,e,i),UM=t=>typeof t=="string"?Xt.querySelector(t):t,Ql=(t,n=0,e=1/0)=>{let i=1;for(;(n=t.indexOf(`
`,n)+1)&&n<=e;i++);return i},Xl={},qM=Gu("<div><div class=pce-wrapper><div class=pce-overlays><textarea class=pce-textarea spellcheck=false autocapitalize=off autocomplete=off>"),Wi=t=>{t.preventDefault(),t.stopImmediatePropagation()};var qu;Xt&&Qt(Xt,"selectionchange",()=>qu?.());globalThis.Prism={highlightAllUnder:t=>{t.querySelectorAll("pre code").forEach(n=>{let e=Array.from(n.classList).find(r=>r.startsWith("language"))||"",i=n.textContent||"";n.textContent="",Ra(n.parentElement,{value:i.trimEnd(),language:e.replace("language-",""),lineNumbers:!1,wordWrap:!0,readOnly:!0})})}};function GM(t,n){if(t&1&&(Ue(0,"span"),w(1),Qe()),t&2){let e=C();h(),fo(" ",e.lowerBoundInclusive()?">=":">"," ",e.lowerBound()," ")}}function WM(t,n){if(t&1&&(Ue(0,"span"),w(1),Qe()),t&2){let e=C();h(),fo(" ",e.upperBoundInclusive()?"<=":"<"," ",e.upperBound()," ")}}function KM(t,n){if(t&1&&(Ue(0,"span"),w(1),Qe()),t&2){let e=C();h(),Vp("",e.lowerBoundInclusive()?"[":"("," ",e.lowerBound()," .. ",e.upperBound()," ",e.upperBoundInclusive()?"]":")"," ")}}var Zl=class t{lowerBound=yt();upperBound=yt();lowerBoundInclusive=yt(!0,{transform:n=>n===!0||n=="true"});upperBoundInclusive=yt(!0,{transform:n=>n===!0||n=="true"});static \u0275fac=function(e){return new(e||t)};static \u0275cmp=T({type:t,selectors:[["app-schema-range"]],inputs:{lowerBound:[1,"lowerBound"],upperBound:[1,"upperBound"],lowerBoundInclusive:[1,"lowerBoundInclusive"],upperBoundInclusive:[1,"upperBoundInclusive"]},decls:4,vars:3,template:function(e,i){e&1&&(Ue(0,"span"),k(1,GM,2,2,"span"),k(2,WM,2,2,"span"),k(3,KM,2,4,"span"),Qe()),e&2&&(h(),D(i.lowerBound()!=null&&i.upperBound()==null?1:-1),h(),D(i.lowerBound()==null&&i.upperBound()!=null?2:-1),h(),D(i.lowerBound()!=null&&i.upperBound()!=null?3:-1))},encapsulation:2})};var Dv=()=>({}),Ev=t=>({value:t});function YM(t,n){if(t&1&&xc(0,2),t&2){let e=C(),i=Vt(4);R("ngTemplateOutlet",i)("ngTemplateOutletContext",Dc(2,Ev,e.schema()))}}function QM(t,n){t&1&&(b(0,"span",7),w(1,"*"),f())}function XM(t,n){t&1&&(b(0,"span",8),w(1,"\xA0(deprecated)"),f())}function ZM(t,n){if(t&1&&(b(0,"mat-list-item",3)(1,"mat-icon",4),w(2,"arrow_right"),f(),b(3,"span",5)(4,"b",6),w(5),f(),k(6,QM,2,0,"span",7),k(7,XM,2,0,"span",8),f(),xc(8,9),f()),t&2){let e,i=n.$implicit,r=C(2),o=Vt(4);h(5),Le(i.key),h(),D((e=r.schema().required)!=null&&e.includes(i.key)?6:-1),h(),D(i.value.deprecated?7:-1),h(),R("ngTemplateOutlet",o)("ngTemplateOutletContext",Dc(5,Ev,i.value))}}function JM(t,n){if(t&1&&(b(0,"mat-list"),je(1,ZM,9,7,"mat-list-item",3,$e),es(3,"keyvalue"),f()),t&2){let e=C();h(),Ve(ts(3,0,e.schema().properties||kc(2,Dv)))}}function eA(t,n){if(t&1&&(b(0,"span",10),w(1),f()),t&2){let e=C(2).value;h(),J(" ",e.type.join(", ")," ")}}function tA(t,n){if(t&1&&w(0),t&2){let e=C(2).value;J(" ",e.type," ")}}function nA(t,n){if(t&1&&(b(0,"span",16),w(1),f()),t&2){let e=C(2).value;h(),J("(",e.format,")")}}function iA(t,n){if(t&1&&(b(0,"span",10),k(1,eA,2,1,"span",10)(2,tA,1,1),k(3,nA,2,1,"span",16),f()),t&2){let e=C().value,i=C();h(),D(i.Array.isArray(e.type)?1:2),h(2),D(e.format?3:-1)}}function rA(t,n){if(t&1&&(b(0,"span",11)(1,"mat-chip-set")(2,"a",17)(3,"mat-chip"),P(4,"mat-icon",18),w(5),f()()()()),t&2){let e=C().value;h(2),R("href",e.refAnchorUrl,Ze),h(3),J(" ",e.refTitle," ")}}function oA(t,n){if(t&1&&(b(0,"span",10),w(1," array "),b(2,"mat-chip-set")(3,"a",17)(4,"mat-chip"),P(5,"mat-icon",18),w(6),f()()()()),t&2){let e=C().value;h(3),R("href",e.items.refAnchorUrl,Ze),h(3),J(" ",e.items.refTitle," ")}}function aA(t,n){if(t&1&&(b(0,"span",10),w(1),f()),t&2){let e=C().value;h(),J(" ",e.items.type,"[] ")}}function sA(t,n){if(t&1&&(b(0,"div",12),P(1,"markdown",19),f()),t&2){let e=C().value;h(),R("data",e.description)}}function lA(t,n){if(t&1&&(b(0,"div",6),w(1),f()),t&2){let e=n.$implicit;h(),J(" ",e," ")}}function cA(t,n){if(t&1&&(b(0,"div",13)(1,"span",20)(2,"i"),w(3,"Example:"),f()(),w(4," \xA0 "),b(5,"span",21),je(6,lA,2,1,"div",6,$e),f()()),t&2){let e=C().value;h(6),Ve(e.example.value.split(`
`))}}function dA(t,n){if(t&1&&(b(0,"span",22),w(1),f()),t&2){let e=n.$implicit;h(),Le(e||"null")}}function mA(t,n){if(t&1&&(b(0,"div",13)(1,"span",20)(2,"i"),w(3,"Allowed values:"),f()(),w(4," \xA0 "),je(5,dA,2,1,"span",22,$e),f()),t&2){let e=C().value;h(5),Ve(e.enum)}}function uA(t,n){if(t&1&&(b(0,"span",15),P(1,"app-schema-range",23),w(2," items "),f()),t&2){let e=C().value;h(),R("lowerBound",e.minItems)("upperBound",e.maxItems)}}function pA(t,n){if(t&1&&(b(0,"span",15),w(1),f()),t&2){let e=C().value;h(),J(" Unique items: ",e.uniqueItems?"yes":"no"," ")}}function hA(t,n){if(t&1&&(b(0,"span",15),w(1," pattern: "),b(2,"span",6),w(3),f()()),t&2){let e=C().value;h(3),J(" ",e.pattern," ")}}function fA(t,n){if(t&1&&(b(0,"span",15),P(1,"app-schema-range",23),w(2," length "),f()),t&2){let e=C().value;h(),R("lowerBound",e.minLength)("upperBound",e.maxLength)}}function gA(t,n){if(t&1&&(b(0,"span",15),P(1,"app-schema-range",24),w(2," value range "),f()),t&2){let e=C().value;h(),R("lowerBound",e.minimum)("upperBound",e.maximum)("lowerBoundInclusive",!e.exclusiveMinimum)("upperBoundInclusive",!e.exclusiveMaximum)}}function _A(t,n){if(t&1&&(b(0,"span",15),w(1),f()),t&2){let e=C().value;h(),J(" Multiple of ",e.multipleOf," ")}}function bA(t,n){if(t&1&&(k(0,iA,4,2,"span",10),k(1,rA,6,2,"span",11),k(2,oA,7,2,"span",10),k(3,aA,2,1,"span",10),k(4,sA,2,1,"div",12),k(5,cA,8,0,"div",13),k(6,mA,7,0,"div",13),b(7,"span",14),k(8,uA,3,2,"span",15),k(9,pA,2,1,"span",15),k(10,hA,4,1,"span",15),k(11,fA,3,2,"span",15),k(12,gA,3,4,"span",15),k(13,_A,2,1,"span",15),f()),t&2){let e=n.value;D((e==null?null:e.type)!="array"?0:-1),h(),D(e.refTitle?1:-1),h(),D((e==null?null:e.type)=="array"&&(!(e==null||e.items==null)&&e.items.refTitle)?2:-1),h(),D((e==null?null:e.type)=="array"&&!(!(e==null||e.items==null)&&e.items.refTitle)?3:-1),h(),D((e.description==null?null:e.description.length)>0?4:-1),h(),D(e.example?5:-1),h(),D(e.enum&&e.enum.length>0?6:-1),h(2),D(e.minItems!=null||e.maxItems!=null?8:-1),h(),D(e.uniqueItems!=null?9:-1),h(),D(e.pattern?10:-1),h(),D(e.minLength!=null||e.maxLength!=null?11:-1),h(),D(e.minimum!=null||e.maximum!=null?12:-1),h(),D(e.multipleOf!=null?13:-1)}}var Zr=class t{schema=yt.required();Array=Array;Object=Object;static \u0275fac=function(e){return new(e||t)};static \u0275cmp=T({type:t,selectors:[["app-schema"]],inputs:{schema:[1,"schema"]},decls:5,vars:3,consts:[["valueContent",""],[1,"schema"],[3,"ngTemplateOutlet","ngTemplateOutletContext"],["lines","99"],["matListItemIcon",""],["matListItemTitle","",1,"key"],[1,"text-console"],[1,"required"],[1,"deprecated"],["matListItemLine","",3,"ngTemplateOutlet","ngTemplateOutletContext"],[1,"type","text-console"],[1,"type"],[1,"description"],[1,"flex"],[1,"flex","flex-wrap"],[1,"attribute"],[1,"format"],[3,"href"],["matChipAvatar","","fontIcon","schema"],[3,"data"],[1,"property-title"],[1,"value-box"],[1,"text-console","value-box"],["lowerBoundInclusive","true","upperBoundInclusive","true",3,"lowerBound","upperBound"],[3,"lowerBound","upperBound","lowerBoundInclusive","upperBoundInclusive"]],template:function(e,i){e&1&&(b(0,"div",1),k(1,YM,1,4,"ng-container",2),k(2,JM,4,3,"mat-list"),f(),jt(3,bA,14,13,"ng-template",null,0,vo)),e&2&&(h(),D(i.schema().type!=="object"?1:-1),h(),D(i.Object.keys(i.schema().properties||kc(2,Dv)).length>0?2:-1))},dependencies:[Sa,Ma,bv,vv,Nu,Fu,Pu,mn,Gn,fi,Qr,Ct,Mt,fn,ko,vn,bn,Zl,Do],styles:[".required[_ngcontent-%COMP%], .deprecated[_ngcontent-%COMP%]{color:red}.description[_ngcontent-%COMP%]{overflow:auto}.property-title[_ngcontent-%COMP%]{min-width:fit-content}.attribute[_ngcontent-%COMP%]{background-color:#805ad5;color:#fff;margin:0 .2em .2em 0;padding:.1em .2em;border-radius:4px}.value-box[_ngcontent-%COMP%]{margin:1px 6px 1px 0;background-color:#eee;border:1px dashed grey;color:#696969;border-radius:4px;padding:0 2px;overflow-wrap:anywhere;white-space:normal;width:fit-content}"]})};var Zt=class t{constructor(n){this.el=n}static \u0275fac=function(e){return new(e||t)(Ee(Y))};static \u0275dir=G({type:t,selectors:[["","appNavigationTarget",""]]})};function vA(t,n){if(t&1&&(b(0,"div",6)(1,"span"),w(2,"Description"),f(),P(3,"markdown",7),f()),t&2){let e=C().$implicit;h(3),R("data",e.description)}}function yA(t,n){if(t&1&&(b(0,"a",9)(1,"mat-chip")(2,"mat-icon",10),w(3),f(),w(4),f()()),t&2){let e=n.$implicit;R("href",e.anchorUrl,Ze),h(3),Le(e.type=="schema"?"schema":"swap_vert"),h(),J(" ",e.name," ")}}function xA(t,n){if(t&1&&(b(0,"div",3)(1,"span"),w(2,"Used by"),f(),b(3,"mat-chip-set"),je(4,yA,5,3,"a",9,$e),f()()),t&2){let e=C().$implicit;h(4),Ve(e.usedBy)}}function wA(t,n){t&1&&P(0,"br")}function CA(t,n){if(t&1&&(b(0,"article",0)(1,"mat-card")(2,"mat-card-header",1)(3,"mat-card-title"),w(4),f()(),b(5,"mat-card-content")(6,"div",2)(7,"div",3)(8,"span"),w(9,"Name"),f(),b(10,"span",4),w(11),f()(),b(12,"div",3)(13,"span"),w(14,"Type"),f(),b(15,"span")(16,"div",5),w(17),f()()(),k(18,vA,4,1,"div",6),k(19,xA,6,0,"div",3),f(),b(20,"h6"),w(21,"Example"),f(),b(22,"div"),P(23,"app-json",7),f(),b(24,"h6"),w(25,"Properties"),f(),P(26,"app-schema",8),f()(),k(27,wA,1,0,"br"),f()),t&2){let e=n.$implicit,i=n.$index,r=n.$count;R("id",e.anchorIdentifier),h(4),J(" ",e.title," "),h(7),Le(e.name),h(6),Le(e.type),h(),D(e.description?18:-1),h(),D(e.usedBy.length>0?19:-1),h(4),R("data",e.example==null?null:e.example.rawValue),h(3),R("schema",e),h(),D(i!==r-1?27:-1)}}var Jl=class t{constructor(n){this.asyncApiService=n}schemas=X([]);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(n=>this.schemas.set([...n.components.schemas.values()]))}static \u0275fac=function(e){return new(e||t)(Ee(nt))};static \u0275cmp=T({type:t,selectors:[["app-schemas"]],decls:4,vars:0,consts:[["appNavigationTarget","",3,"id"],[1,"flex","space-between","align-items-baseline"],[1,"table","margin-vertical-1em"],[1,"table-row"],[1,"text-console"],[1,"type-badge"],[1,"table-row","description"],[3,"data"],[3,"schema"],[3,"href"],["matChipAvatar",""]],template:function(e,i){e&1&&(b(0,"h2"),w(1,"Schemas"),f(),je(2,CA,28,9,"article",0,$e)),e&2&&(h(2),Ve(i.schemas()))},dependencies:[bn,Ct,Mt,qn,Gr,Kr,Yr,Wr,mn,Gn,fi,Qr,Wl,Zr,Zt],styles:[".table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:first-child{vertical-align:middle}.table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:last-child{word-break:break-word}.type-badge[_ngcontent-%COMP%]{display:inline;background-color:#e0e0e0;border-radius:4px;padding:4px;font-weight:400;font-size:small}"]})};var Pa=new Ht("init"),Ki=new Ht("");var Sv={asyncApiJson:{},contact:{},license:{},title:"",version:""};var Fa={ts_type:"object",title:"",name:"",anchorUrl:"",anchorIdentifier:"",usedBy:[]};function kA(t,n){if(t&1){let e=ot();b(0,"a",3),pe("click",function(){Ke(e);let r=C();return Ye(r.download())}),w(1,"AsyncAPI JSON"),f()}}function DA(t,n){if(t&1&&(b(0,"a",5),w(1),f()),t&2){C(2);let e=sr(6);R("href",e.url,Ze),h(),J(" ",e.name," ")}}function EA(t,n){if(t&1&&w(0),t&2){C(2);let e=sr(6);J(" ",e.name," ")}}function SA(t,n){if(t&1&&(b(0,"mat-chip"),P(1,"mat-icon",4),k(2,DA,2,2,"a",5)(3,EA,1,1),f()),t&2){C();let e=sr(6);h(2),D(e!=null&&e.url?2:3)}}function MA(t,n){if(t&1&&(b(0,"mat-chip"),P(1,"mat-icon",6),b(2,"a",5),w(3),f()()),t&2){C();let e=sr(8);h(2),R("href",e.url,Ze),h(),J(" ",e.url," ")}}function AA(t,n){if(t&1&&(b(0,"mat-chip"),P(1,"mat-icon",7),b(2,"a",5),w(3),f()()),t&2){C();let e=sr(10);h(2),R("href",e.href,Ze),h(),J(" ",e.name," ")}}function IA(t,n){if(t&1&&(b(0,"p"),P(1,"markdown",8),f()),t&2){let e=C();h(),R("data",e.info().description)}}var ec=class t{constructor(n){this.asyncApiService=n}asyncApiData=void 0;info=X(Sv);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(n=>{this.asyncApiData=n,this.info.set(n.info)})}download(){if(this.asyncApiData===void 0)return!1;let n=JSON.stringify(this.asyncApiData.info.asyncApiJson,null,2),e=new TextEncoder().encode(n),i=new Blob([e],{type:"application/json"}),r=window.URL.createObjectURL(i);return window.open(r),!1}static \u0275fac=function(e){return new(e||t)(Ee(nt))};static \u0275cmp=T({type:t,selectors:[["app-info"]],decls:13,vars:10,consts:[[1,"info-chips"],["matChipAvatar","","fontIcon","download"],["href","javascript:void(0);"],["href","javascript:void(0);",3,"click"],["matChipAvatar","","fontIcon","attribution"],["target","_blank",3,"href"],["matChipAvatar","","fontIcon","link"],["matChipAvatar","","fontIcon","email"],[3,"data"]],template:function(e,i){if(e&1&&(b(0,"h1"),w(1),f(),b(2,"p",0)(3,"mat-chip"),P(4,"mat-icon",1),k(5,kA,2,0,"a",2),f(),Za(6),k(7,SA,4,1,"mat-chip"),Za(8),k(9,MA,4,2,"mat-chip"),Za(10),k(11,AA,4,2,"mat-chip"),f(),k(12,IA,2,1,"p")),e&2){h(),fo(" ",i.info().title," ",i.info().version?"v"+i.info().version:"",`
`),h(4),D(i.info().asyncApiJson?5:-1),h();let r=Ja(i.info().license);h(),D(r!=null&&r.name?7:-1),h();let o=Ja(i.info().contact);h(),D(o.url?9:-1),h();let a=Ja(i.info().contact.email);h(),D(a?11:-1),h(),D(i.info().description?12:-1)}},dependencies:[mn,Gn,fi,Ct,Mt,vn,bn],styles:[".info-chips[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]{margin-inline-end:8px}"]})};function TA(t,n){if(t&1&&(b(0,"article",1)(1,"mat-card",2)(2,"mat-card-header")(3,"mat-card-title"),w(4),f(),b(5,"span",3)(6,"span",4),w(7),f()()(),b(8,"mat-card-content")(9,"table")(10,"tbody")(11,"tr")(12,"td"),w(13,"Host"),f(),b(14,"td",5),w(15),f()()()()()()()),t&2){let e=n.$implicit;R("id",e.value.anchorIdentifier),h(4),J(" ",e.key," "),h(3),J(" ",e.value.protocol," "),h(8),J(" ",e.value.host," ")}}var tc=class t{constructor(n){this.asyncApiService=n}servers=X(new Map);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(n=>this.servers.set(n.servers))}static \u0275fac=function(e){return new(e||t)(Ee(nt))};static \u0275cmp=T({type:t,selectors:[["app-servers"]],decls:6,vars:2,consts:[[1,"row"],["appNavigationTarget","",1,"width-6-of-12","width-12-of-12-s",3,"id"],["appearance","outlined"],[1,"flex","gap-16","padding-horizontal-1em","height-fix-content"],[1,"badge","protocol-badge"],[1,"text-console"]],template:function(e,i){e&1&&(b(0,"h2"),w(1,"Servers"),f(),b(2,"div",0),je(3,TA,16,4,"article",1,$e),es(5,"keyvalue"),f()),e&2&&(h(3),Ve(ts(5,0,i.servers())))},dependencies:[fn,qn,Gr,Kr,Yr,Wr,Zt,Do],styles:[".badge[_ngcontent-%COMP%]{border-radius:4px;padding:.3em;font-size:smaller;text-transform:uppercase}.protocol-badge[_ngcontent-%COMP%]{background-color:#347aeb;color:#fff}"]})};var Mv=(t,n,e)=>n.indexOf(t[0])+1||e&&n.indexOf(t[e])+1;var Av=(t=!0,n="()[]{}")=>{let e,i,r=[],o=d=>{d.extensions.matchBrackets=o,d.on("tokenize",l),t&&d.tokens[0]?d.update():l(d.tokens)},a=o.brackets=[],s=o.pairs=[],l=d=>{if(s.length=a.length=i=e=0,c(d,0),t)for(let u=0,p;p=a[u];){let y=p[0].alias;p[0].alias=(y?y+" ":"")+`bracket-${u++in s?"level-"+p[3]%12:"error"}`}},c=(d,u)=>{let p,y=0;for(;p=d[y++];){let g=p.length;if(typeof p!="string"){let _=p.content;if(Array.isArray(_))c(_,u);else if((p.alias||p.type)=="punctuation"){let v=Mv(_,n,g-1),x=v%2;if(v){if(a[e]=[p,u,u+g,i,_,!!x],x)r[i++]=[e,v+1];else for(let S=i,I;I=r[--S];)if(v==I[1]){s[s[e]=I[0]]=e,a[e][3]=i=S;break}e++}}}u+=g}};return o};var Jr=(t,n)=>n?t.lastIndexOf(`
`,n-1)+1:0,Na=(t,n)=>(n=t.indexOf(`
`,n))+1?n:t.length,Iv=(t,n,e,i)=>(Qt(t,n,e,i),()=>t.removeEventListener(n,e,i)),gi=(t,n,e,i)=>Iv(t.textarea,n,e,i),Tv=(t,n)=>parseFloat(getComputedStyle(t)[n]);var hq=new Set("xml,rss,atom,jsx,tsx,xquery,xeora,xeoracube,actionscript".split(","));var Yi,eo=t=>t.replace(/[$+?|.^*()[\]{}\\]/g,"\\$&"),Ov=(t,n)=>t.slice(Jr(t,n),n),to=(t,n,e=n)=>[t.slice(n=Jr(t,n),e=Na(t,e)).split(`
`),n,e],Yu=(t,n,e=0,i=e,r=t.getSelection()[0])=>{let o=t.value,a=t.lines[Ql(o,0,r)],s=Xt.createTreeWalker(a,5),l=s.lastChild(),c=Na(o,r)+1-r-l.length;for(;-c<=i&&(l=s.previousNode());)if(!l.lastChild&&(c-=l.length||0,c<=e)){for(;l!=a;l=l.parentNode)if(l.matches?.(n))return l}},Qu=(t,n)=>Yu(t,"[class*=language-]",0,0,n)?.className.match(/language-(\S*)/)[1]||t.options.language,En=(t,n,e,i,r,o)=>{if(t.options.readOnly)return;Yi=t.getSelection(),i??=e;let a=t.textarea,s=t.value,l=Ku&&!s[i??Yi[1]]&&/\n$/.test(n)&&/^$|\n$/.test(s),c;t.focused||a.focus(),e!=null&&a.setSelectionRange(e,i),r!=null&&(c=t.on("update",()=>{a.setSelectionRange(r,o??r,Yi[2]),c()})),Wu||a.dispatchEvent(new InputEvent("beforeinput",{data:n})),Ku||Wu?(l&&(a.selectionEnd--,n=n.slice(0,-1)),Wu&&(n+=`
`),Xt.execCommand(n?"insertHTML":"delete",!1,zu(n,/</g,"&lt;")),l&&a.selectionStart++):Xt.execCommand(n?"insertText":"delete",!1,n),Yi=0},Xu=(t,n,e=n,i)=>{let r=t.textarea,o=Iv(r,"focus",a=>{let s=a.relatedTarget;s?s.focus():r.blur()});r.setSelectionRange(n,e,i),o(),r.dispatchEvent(new Event("selectionchange"))},Rv=Xt?navigator.userAgent:"",Sn=Xt?/Mac|iPhone|iP[ao]d/.test(navigator.platform):!1,Ku=/Chrome\//.test(Rv),Wu=!Ku&&/AppleWebKit\//.test(Rv),Zu=t=>t.altKey+t.ctrlKey*2+t.metaKey*4+t.shiftKey*8,Pv=(t,n)=>t.lines[0].append(n);var Fv=()=>t=>{let n,e=[],i=()=>{let o=t.extensions.matchBrackets,[a,s]=t.getSelection();if(o){let l=o.brackets,c=o.pairs,d,u;if(t.focused&&a==s){for(let p=0,y;y=l[++p];)if(!y[5]&&y[2]>=s&&l[c[p]]?.[1]<=s){d=l[c[p]],u=y;break}}u!=n&&(r(),u?(e=[d,u].map(p=>Yu(t,".punctuation",0,-1,p[1])),e[0]!=e[1]&&d[2]==u[1]&&(e[0].textContent+=e[1].textContent,e[1].textContent="",e[1]=e[0]),r(!0)):e=[]),n=u}},r=o=>e.forEach(a=>a.classList.toggle("active-bracket",!!o));gi(t,"focus",i),gi(t,"blur",i),t.on("selectionChange",i)};var nc=Sn?4:2;var La=!1;var Qi=t=>t.search(/\S|$/),ip=({options:{insertSpaces:t=!0,tabSize:n}})=>[t?" ":"	",t?n||2:1],Xi=t=>!t.options.readOnly&&!t.extensions.cursor?.scrollIntoView(),Nv=(t,n,e,i,r,o,a)=>{let s=e.join(`
`);if(s==n.join(`
`))return;let l=n.length-1,c=e[l],d=n[l],u=d.length-c.length,p=e[0].length-n[0].length,y=i+Qi((p<0?e:n)[0]),g=r-d.length+Qi(u>0?c:d),_=i-r+s.length+u,v=y>o?o:Math.max(y,o+p),x=a+i-r+s.length;En(t,s,i,r,v,a<g?x+u:Math.max(g+_,x))},Ba=(t,n)=>{let[e,i]=t.getSelection(),[r,o,a]=to(t.value,e,i),[s,l]=ip(t);return Nv(t,r,r.map(n?c=>c.slice(Qi(c)?l-Qi(c)%l:0):c=>c&&s.repeat(l-Qi(c)%l)+c),o,a,e,i),Xi(t)},Lv=(t,n)=>{let[e,i]=ip(t);return En(t,e.repeat(i-(n-Jr(t.value,n))%i)),Xi(t)},rc=(t,n)=>{let e=t.getSelection(),i=t.value;n&&(e[0]=e[1]=Na(i,e[1]));let[r,o]=ip(t),[a,s]=e,l=Xl[Qu(t,a)]?.autoIndent,c=Math.floor(Qi(Ov(i,a))/o)*o,d=l?.[0]?.(e,i,t)?o:0,u=l?.[1]?.(e,i,t),p=`
`+r.repeat(c+d)+(u?`
`+r.repeat(c):"");if(p[1]||i[s])return En(t,p,a,s,a+c+d+1),Xi(t)},Ju=(t,n)=>{let[e,i]=t.getSelection(),r=t.value,o=n?Jr(r,e)-1:e,a=n?i:r.indexOf(`
`,i)+1;if(o>-1&&a>0){let[s,l,c]=to(r,o,a),d=s[n?"shift":"pop"](),u=(d.length+1)*(n?-1:1);s[n?"push":"unshift"](d),En(t,s.join(`
`),l,c,e+u,i+u)}return Xi(t)},ep=(t,n)=>{let[e,i]=t.getSelection(),r=t.value,[o,a,s]=to(r,e,i),l=o.join(`
`),c=n?0:l.length+1;return En(t,l+`
`+l,a,s,e+c,i+c),Xi(t)},tp=(t,n)=>(t.container.scrollBy(0,Tv(t.container,"lineHeight")*(n?-1:1)),!0),Bv=t=>{let[n,e,i]=t.getSelection(),r=t.value,[o,a,s]=to(r,n,e),l=i>"f"?e-s+o.pop().length:n-a,c=Na(r,s+1)-s-1;return En(t,"",a-!!a,s+!a,a+Math.min(l,c)),Xi(t)},np=(t,n)=>{let[e,i]=t.getSelection(),r=t.value,o=n?e:Jr(r,e),a=Xl[Qu(t,o)]||{},{line:s,block:l}=a.getComments?.(t,o,r)||a.comments||{},[c,d,u]=to(r,e,i),p=c.length-1;if(n){if(l){let[y,g]=l,_=r.slice(e,i),v=r.slice(0,e).search(eo(y)+" ?$");v+1&&RegExp("^ ?"+eo(g)).test(r.slice(i))?En(t,_,v,i+(r[i]==" ")+g.length,v,v+i-e):En(t,`${y} ${_} ${g}`,e,i,e+y.length+1,i+y.length+1)}}else if(s){let y=eo(s),g=RegExp(`^\\s*(${y} ?|$)`),_=RegExp(y+" ?"),v=!/\S/.test(r.slice(d,u));Nv(t,c,c.map(!v&&c.every(x=>g.test(x))?x=>x.replace(_,""):x=>v||/\S/.test(x)?x.replace(/(?!\s)/,s+" "):x),d,u,e,i)}else if(l){let[y,g]=l,_=c[0],v=Qi(_),x=_.startsWith(y,v)&&c[p].endsWith(g);c[0]=_.replace(x?RegExp(eo(y)+" ?"):/(?!\s)/,x?"":y+" ");let S=c[0].length-_.length;c[p]=x?c[p].replace(RegExp(` ?${eo(g)}$`),""):c[p]+" "+g;let I=c.join(`
`),F=v+d,N=F>e?e:Math.max(e+S,F),ge=F>i-(e!=i)?i:Math.min(Math.max(F,i+S),d+I.length);En(t,I,d,u,N,Math.max(N,ge))}return l||s&&!n?Xi(t):!1},OA={Tab(t){if(!La){let[n,e]=t.getSelection();return n==e?Lv(t,n):Ba(t)}},"8+Tab":t=>!La&&Ba(t,!0),"1+ArrowDown":t=>Ju(t),"1+ArrowUp":t=>Ju(t,!0),"9+ArrowDown":t=>ep(t),"9+ArrowUp":t=>ep(t,!0),Enter:t=>rc(t),"8+Enter":t=>rc(t),"Mod+Enter":t=>rc(t,!0),"Mod+]":t=>Ba(t),"Mod+[":t=>Ba(t,!0),"8+Mod+k":Bv,"Mod+/":t=>np(t),"9+a":t=>np(t,!0),[Sn?"10+m":"2+m"]:()=>(La=!La,!0),[`2+${Sn?"Page":"Arrow"}Down`]:t=>tp(t),[`2+${Sn?"Page":"Arrow"}Up`]:t=>tp(t,!0)};var RA=[["2+ ","Trigger suggestion"],["mod+i","Trigger suggestion"],...Sn?[["1+Escape","Trigger suggestion"]]:[],["2+ ","Toggle suggestion documentation"],["mod+i","Toggle suggestion documentation"],["Tab","Insert suggestion"],["Enter","Insert suggestion"],["Escape","Close completion widget"],["Escape","Clear tab stops"],["Tab","Select next tab stop"],["8+Tab","Select previous tab stop"],["ArrowUp","Select previous suggestion"],["ArrowDown","Select next suggestion"],["PageUp","Select first visible suggestion"],["PageDown","Select last visible suggestion"]],ic=Sn?5:1,PA=[["mod+f","Start search"],["mod+g","Find next match"],["mod+8+g","Find previous match"],["f3","Find next match"],["8+f3","Find previous match"],["Enter","Select next match"],["8+Enter","Select previous match"],["Escape","Close search widget"],["Enter","Replace match"],[`${Sn?4:3}+Enter`,"Replace all matches"],[ic+"+r","Toggle regex search"],[ic+"+p","Toggle case preservation"],[ic+"+w","Toggle whole word search"],[ic+"+l","Toggle find in selection"]];var rp=(t=999)=>{let n=0,e,i,r=!1,o,a,s,l,c,d,u=[],p=v=>{v>=t&&(v--,u.shift()),u.splice(n=v,t,[e.value,d(),d()])},y=v=>{u[v]&&(c.value=u[v][0],c.setSelectionRange(...u[v][v<n?2:1]),e.update(),e.extensions.cursor?.scrollIntoView(),n=v,i=!1)},g=(v,x)=>{v.extensions.history=g,e=v,d=v.getSelection,c||p(0),c=v.textarea,v.on("selectionChange",()=>{i=r,r=!1}),gi(v,"beforeinput",S=>{let I=S.data,F=S.inputType,N=S.timeStamp;/history/.test(F)?(y(n+(F[7]=="U"?-1:1)),Wi(S)):(l=i&&(o==F||N-s<99&&F.slice(-4)=="Drop")&&!Yi&&(I!=" "||a==I))||(u[n][2]=Yi||d()),r=!0,a=I,s=N,o=F}),gi(v,"input",()=>p(n+!l)),gi(v,"keydown",S=>{if(!x.readOnly){let I=Zu(S),F=S.keyCode,N=I==nc&&F==90,ge=I==nc+8&&F==90||!Sn&&I==nc&&F==89;N?(y(n-1),Wi(S)):ge&&(y(n+1),Wi(S))}}),v.addExtensions({update(){v.value!=c.value&&_()}})},_=g.clear=()=>{p(0),i=!1};return g.has=v=>n+v in u,g.go=v=>y(n+v),g};var FA=Gu('<div style=display:flex;align-items:flex-start;justify-content:flex-end><button type=button dir=ltr style=display:none class=pce-copy aria-label=Copy><svg width=1.2em aria-hidden=true viewBox="0 0 16 16" overflow=visible stroke-linecap=round fill=none stroke=currentColor><rect x=4 y=4 width=11 height=11 rx=1 /><path d="m12 2a1 1 0 00-1-1H2A1 1 0 001 2v9a1 1 0 001 1">'),$v=()=>t=>{let n=FA(),e=n.firstChild;Qt(e,"click",()=>{e.setAttribute("aria-label","Copied!"),navigator.clipboard?.writeText(t.extensions.codeFold?.fullCode??t.value)||(t.textarea.select(),Xt.execCommand("copy"),Xu(t,0))}),Qt(e,"pointerenter",()=>e.setAttribute("aria-label","Copy")),Pv(t,n)};var no=/\/\/.*|\/\*[^]*?(?:\*\/|$)/g;var oc=/[()[\]{}.,:;]/,io=/\b(?:false|true)\b/,op={punctuation:/\./};ue.webmanifest=ue.json={property:/"(?:\\.|[^\\\n"])*"(?=\s*:)/g,string:/"(?:\\.|[^\\\n"])*"/g,comment:no,number:/-?\b\d+(?:\.\d+)?(?:e[+-]?\d+)?\b/i,operator:/:/,punctuation:/[[\]{},]/,boolean:io,null:{pattern:/\bnull\b/,alias:"keyword"}};var $a=(t,n)=>t.replace(/<(\d+)>/g,(e,i)=>`(?:${n[+i]})`),ro=(t,n,e)=>RegExp($a(t,n),e);var ap=/[*&][^\s[\]{},]+/,sp=/!(?:<[\w%#;/?:@&=$,.!~*'()[\]+-]+>|(?:[a-zA-Z\d-]*!)?[\w%#;/?:@&=$.~*'()+-]+)?/,lp=`(?:${sp.source}(?:[ 	]+${ap.source})?|${ap.source}(?:[ 	]+${sp.source})?)`,NA=$a("(?:[^\\s\0-\\x08\\x0e-\\x1f!\"#%&'*,:>?@[\\]{}`|\\x7f-\\x84\\x86-\\x9f\\ud800-\\udfff\\ufffe\\uffff-]|[?:-]<0>)(?:[ 	]*(?:(?![#:])<0>|:<0>))*",["[^\\s\0-\\x08\\x0e-\\x1f,[\\]{}\\x7f-\\x84\\x86-\\x9f\\ud800-\\udfff\\ufffe\\uffff]"]),jv=`"(?:\\\\.|[^\\\\
"])*"|'(?:\\\\.|[^\\\\
'])*'`,ja=(t,n)=>ro(`([:,[{-]\\s*(?:\\s<0>[ 	]+)?)<1>(?=[ 	]*(?:$|,|\\]|\\}|(?:
\\s*)?#))`,[lp,t],n);ue.yml=ue.yaml={scalar:{pattern:ro(`([:-]\\s*(?:\\s<0>[ 	]+)?[|>])[ 	]*(?:(
[ 	]+)\\S.*(?:\\2.+)*)`,[lp]),lookbehind:!0,alias:"string"},comment:/#.*/,key:{pattern:ro(`((?:^|[:,[{
?-])[ 	]*(?:<0>[ 	]+)?)<1>(?=\\s*:\\s)`,[lp,"(?:"+NA+"|"+jv+")"],"g"),lookbehind:!0,alias:"atrule"},directive:{pattern:/(^[ 	]*)%.+/m,lookbehind:!0,alias:"important"},datetime:{pattern:ja("\\d{4}-\\d\\d?-\\d\\d?(?:[tT]|[ 	]+)\\d\\d?:\\d\\d:\\d\\d(?:\\.\\d*)?(?:[ 	]*(?:Z|[+-]\\d\\d?(?::\\d\\d)?))?|\\d{4}-\\d\\d-\\d\\d|\\d\\d?:\\d\\d(?::\\d\\d(?:\\.\\d*)?)?","m"),lookbehind:!0,alias:"number"},boolean:{pattern:ja("false|true","im"),lookbehind:!0,alias:"important"},null:{pattern:ja("null|~","im"),lookbehind:!0,alias:"important"},string:{pattern:ja(jv,"mg"),lookbehind:!0},number:{pattern:ja("[+-]?(?:0x[a-f\\d]+|0o[0-7]+|(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:e[+-]?\\d+)?|\\.inf|\\.nan)","im"),lookbehind:!0},tag:sp,important:ap,punctuation:/---|[:[\]{},|>?-]|\.{3}/};var ac=(t,n)=>({pattern:RegExp("(^(?:"+t+"):[ 	]*)\\S[^]*","i"),lookbehind:!0,alias:n&&"language-"+n,inside:n}),Vv=ue.http={"request-line":{pattern:/^(?:CONNECT|DELETE|GET|HEAD|OPTIONS|PATCH|POST|PRI|PUT|SEARCH|TRACE)\s(?:https?:\/)?\/\S*\sHTTP\/[\d.]+/m,inside:{method:{pattern:/^\w+/,alias:"property"},"request-target":{pattern:/^(\s)[h/]\S*/,lookbehind:!0,alias:"url",inside:"uri"},"http-version":{pattern:/(?!^)\S+/,alias:"property"}}},"response-status":{pattern:/^HTTP\/[\d.]+ \d+ .+/m,inside:{"http-version":{pattern:/^\S+/,alias:"property"},"status-code":{pattern:/^( )\d+(?= )/,lookbehind:!0,alias:"number"},"reason-phrase":{pattern:/(?!^).+/,alias:"string"}}}};["application/javascript","application/json","application/xml","text/xml","text/html","text/css","text/plain"].forEach(t=>{var n=t.split("/")[1],e=t[10]&&!n[4]?"(?:"+t+"|\\w+/(?:[\\w.-]+\\+)+"+n+"(?![\\w.+-]))":t;Vv[t.replace("/","-")]={pattern:RegExp("(content-type:\\s*"+e+`(?:;.*)?(?:
[\\w-].*)*
)[^\\w 	-][^]*`,"i"),lookbehind:!0,alias:"language-"+n,inside:n=="json"?ue.json||"js":n}});Vv.header={pattern:/^[\w-]+:.+(?:\n[ 	].+)*/m,inside:{"header-value":[ac("Content-Security-Policy","csp"),ac("Public-Key-Pins(?:-Report-Only)?","hpkp"),ac("Strict-Transport-Security","hsts"),ac("[^:]+")],"header-name":{pattern:/^[^:]+/,alias:"keyword"},punctuation:/^:/}};var cp=/\b(?:abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|exports|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|interface|long|module|native|new|non-sealed|null|opens?|package|permits|private|protected|provides|public|record(?!\s*[()[\]{}%~.,:;?%&|^=<>/*+-])|requires|return|sealed|short|static|strictfp|super|switch|synchronized|this|throws?|to|transient|transitive|try|uses|var|void|volatile|while|with|yield)\b/,Va="(?:[a-z]\\w*\\s*\\.\\s*)*(?:[A-Z]\\w*\\s*\\.\\s*)*",dp={pattern:/^[a-z]\w*(?:\s*\.\s*[a-z]\w*)*(?:\s*\.)?/,inside:op},mp={namespace:dp,punctuation:/\./},zv={pattern:RegExp(`(^|[^\\w.])${Va}[A-Z](?:[\\d_A-Z]*[a-z]\\w*)?\\b`),lookbehind:!0,inside:mp};ue.java={"doc-comment":{pattern:/\/\*\*(?!\/)[^]*?(?:\*\/|$)/g,alias:"comment",inside:"javadoc"},comment:no,"triple-quoted-string":{pattern:/"""[ 	]*\n(?:\\.|[^\\])*?"""/g,alias:"string"},char:/'(?:\\.|[^\\\n']){1,6}'/g,string:{pattern:/(^|[^\\])"(?:\\.|[^\\\n"])*"/g,lookbehind:!0},annotation:{pattern:/(^|[^.])@\w+(?:\s*\.\s*\w+)*/,lookbehind:!0,alias:"punctuation"},generics:{pattern:/<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&)|<(?:[\w\s,.?]|&(?!&))*>)*>)*>)*>/,inside:{"class-name":zv,keyword:cp,punctuation:/[().,:<>]/,operator:/[?&|]/}},import:[{pattern:RegExp(`(\\bimport\\s+)${Va}(?:[A-Z]\\w*|\\*)(?=\\s*;)`),lookbehind:!0,inside:{namespace:dp,punctuation:/\./,operator:/\*/,"class-name":/\w+/}},{pattern:RegExp(`(\\bimport\\s+static\\s+)${Va}(?:\\w+|\\*)(?=\\s*;)`),lookbehind:!0,alias:"static",inside:{namespace:dp,static:/\b\w+$/,punctuation:/\./,operator:/\*/,"class-name":/\w+/}}],namespace:{pattern:RegExp(`(\\b(?:exports|import(?:\\s+static)?|module|opens?|package|provides|requires|to|transitive|uses|with)\\s+)(?!${cp.source})[a-z]\\w*(?:\\.[a-z]\\w*)*\\.?`),lookbehind:!0,inside:op},"class-name":[zv,{pattern:RegExp(`(^|[^\\w.])${Va}[A-Z]\\w*(?=\\s+\\w+\\s*[;,=()]|\\s*(?:\\[[\\s,]*\\]\\s*)?::\\s*new\\b)`),lookbehind:!0,inside:mp},{pattern:RegExp(`(\\b(?:class|enum|extends|implements|instanceof|interface|new|record|throws)\\s+)${Va}[A-Z]\\w*\\b`),lookbehind:!0,inside:mp}],keyword:cp,boolean:io,function:{pattern:/\b\w+(?=\()|(::\s*)[a-z_]\w*/,lookbehind:!0},number:/\b0b[01][01_]*l?\b|\b0x(?:\.[a-f\d_p+-]+|[a-f\d_]+(?:\.[a-f\d_p+-]+)?)\b|(?:\b\d[\d_]*(?:\.[\d_]*)?|\B\.\d[\d_]*)(?:e[+-]?\d[\d_]*)?[dfl]?/i,constant:/\b[A-Z][A-Z_\d]+\b/,operator:{pattern:/(^|[^.])(?:<<=?|>>>?=?|->|--|\+\+|&&|\|\||::|[?:~]|[%&|^!=<>/*+-]=?)/m,lookbehind:!0},punctuation:oc};var up={"interpolation-punctuation":{pattern:/^.\{?|\}$/g,alias:"punctuation"},expression:{pattern:/[^]+/}};up.expression.inside=ue.kts=ue.kt=ue.kotlin={"string-literal":[{pattern:/"""(?:[^$]|\$(?:(?!\{)|\{[^{}]*\}))*?"""/,alias:"multiline",inside:{interpolation:{pattern:/\$(?:[a-z_]\w*|\{[^{}]*\})/i,inside:up},string:/[^]+/}},{pattern:/"(?:\\.|[^\\\n"$]|\$(?:(?!\{)|\{[^{}]*\}))*"/,alias:"singleline",inside:{interpolation:{pattern:/((?:^|[^\\])(?:\\\\)*)\$(?:[a-z_]\w*|\{[^{}]*\})/i,lookbehind:!0,inside:up},string:/[^]+/}}],char:/'(?:[^\\\n']|\\(?:.|u[a-fA-F\d]{0,4}))'/g,comment:no,annotation:{pattern:/\B@(?:\w+:)?(?:[A-Z]\w*|\[[^\]]+\])/,alias:"builtin"},keyword:{pattern:/(^|[^.])\b(?:abstract|actual|annotation|as|break|by|catch|class|companion|const|constructor|continue|crossinline|data|do|dynamic|else|enum|expect|external|final|finally|for|fun|get|if|import|in|infix|init|inline|inner|interface|internal|is|lateinit|noinline|null|object|open|operator|out|override|package|private|protected|public|reified|return|sealed|set|super|suspend|tailrec|this|throw|to|try|typealias|val|var|vararg|when|where|while)\b/,lookbehind:!0},boolean:io,label:{pattern:/\b\w+@|@\w+/,alias:"symbol"},function:{pattern:/(?:`[^\n`]+`|\b\w+)(?=\s*\()|(\.)(?:`[^\n`]+`|\w+)(?=\s*\{)/g,lookbehind:!0},number:/\b(?:0[xX][a-fA-F\d]+(?:_[a-fA-F\d]+)*|0[bB][01]+(?:_[01]+)*|\d+(?:_\d+)*(?:\.\d+(?:_\d+)*)?(?:[eE][+-]?\d+(?:_\d+)*)?[fFL]?)\b/,operator:/--|\+\+|&&|\|\||->|[!=]==|!!|[%!=<>/*+-]=?|[?:]:?|\.\.|\b(?:and|inv|shl|u?shr|x?or)\b/,punctuation:oc};var sc=(t,n)=>{if(n.has(t))return n.get(t);var e=t,i=LA.call(t).slice(8,-1);if(i=="Object"){n.set(t,e={});for(var r in t)e[r]=sc(t[r],n);t[Xr]&&(e[Xr]=sc(t[Xr],n)),t[Gi]&&(e[Gi]=t[Gi])}else if(i=="Array"){n.set(t,e=[]);for(var o=0,a=t.length;o<a;o++)e[o]=sc(t[o],n)}return e},lc=t=>sc(t,new Map);var cc=(t,n,e)=>{var i={};for(var r in t)i[r]=t[r],delete t[r];for(var r in i)r==n&&Object.assign(t,e),e.hasOwnProperty(r)||(t[r]=i[r])},LA={}.toString;var pp=[{pattern:/&[a-z\d]{1,8};/i,alias:"named-entity"},/&#x?[a-f\d]{1,8};/i],Hv=/<!--(?:(?!<!--)[^])*?-->/g,Uv={pattern:/<\/?(?!\d)[^\s/=>$<%]+(?:\s(?:\s*[^\s/=>]+(?:\s*=\s*(?!\s)(?:"[^"]*"|'[^']*'|[^\s"'=>]+(?=[\s>]))?|(?=[\s/>])))+)?\s*\/?>/g,inside:{punctuation:/^<\/?|\/?>$/,tag:{pattern:/^\S+/,inside:{namespace:/^[^:]+:/}},"attr-value":[{pattern:/(=\s*)(?:"[^"]*"|'[^']*'|[^\s"'>]+)/g,lookbehind:!0,inside:{punctuation:/^["']|["']$/g,entity:pp}}],"attr-equals":/=/,"attr-name":{pattern:/\S+/,inside:{namespace:/^[^:]+:/}}}};ue.rss=ue.atom=ue.ssml=ue.xml={comment:Hv,prolog:/<\?[^]+?\?>/g,doctype:{pattern:/<!DOCTYPE(?:[^>"'[\]]|"[^"]*"|'[^']*')+(?:\[(?:[^<"'\]]|"[^"]*"|'[^']*'|<(?!!--)|<!--(?:[^-]|-(?!->))*-->)*\]\s*)?>/gi,inside:{"internal-subset":{pattern:/(\[)[^]+(?=\]\s*>$)/,lookbehind:!0,inside:"xml"},string:/"[^"]*"|'[^']*'/,punctuation:/^<!|[>[\]]/,"doctype-tag":/^DOCTYPE/i,name:/\S+/}},cdata:/<!\[CDATA\[[^]*?\]\]>/gi,tag:Uv,entity:pp,"markup-bracket":{pattern:/[()[\]{}]/,alias:"punctuation"}};var hp=(t,n)=>(t["language-"+n]={pattern:/[^]+/,inside:n},t),qv=(t,n)=>({pattern:RegExp(`(<${t}[^>]*>)(?!</${t}>)(?:<!\\[CDATA\\[(?:[^\\]]|\\](?!\\]>))*\\]\\]>|(?!<!\\[CDATA\\[)[^])+?(?=</${t}>)`,"gi"),lookbehind:!0,inside:hp({"included-cdata":{pattern:/<!\[CDATA\[[^]*?\]\]>/i,inside:hp({cdata:/^<!\[CDATA\[|\]\]>$/i},n)}},n)}),Gv=(t,n,e=t)=>({pattern:RegExp(`([\\s"']${t}\\s*=\\s*)(?:"[^"]*"|'[^']*'|[^\\s>]+)`,"gi"),lookbehind:!0,alias:e,inside:hp({punctuation:/^["']|["']$/g},n)}),Wv=ue.svg=ue.mathml=ue.html=ue.markup=lc(ue.xml);Wv.tag.inside["attr-value"].unshift(Gv("style","css"),Gv("on[a-z]+","javascript","script"));cc(Wv,"cdata",{style:qv("style","css"),script:qv("script","javascript")});var BA=[`(?:\\\\.|[^\\\\
]|
(?!
))`],dc=t=>ro(`((?:^|[^\\\\])(?:\\\\\\\\)*)(?:${t})`,BA,"g"),fp=/(?:\\.|``(?:[^\n`]|`(?!`))+``|`[^\n`]+`|[^\\\n|`])+/,Kv=$a(`\\|?<0>(?:\\|<0>)+\\|?(?:
|(?![\\s\\S]))`,[fp.source]),$A=`\\|?[ 	]*:?-{3,}:?[ 	]*(?:\\|[ 	]*:?-{3,}:?[ 	]*)+\\|?
`,oo=ue.md=ue.markdown=lc(ue.html);cc(oo,"prolog",{"front-matter-block":{pattern:/(^(?:\s*\n)?)---(?!.)[^]*?\n---(?!.)/g,lookbehind:!0,inside:{punctuation:/^---|---$/,"front-matter":{pattern:/\S(?:[^]*\S)?/,alias:"language-yaml",inside:"yaml"}}},blockquote:{pattern:/^>(?:[ 	]*>)*/m,alias:"punctuation"},table:{pattern:RegExp("^"+Kv+$A+"(?:"+Kv+")*","m"),inside:{"table-header-row":{pattern:/^.+/,inside:{"table-header":{pattern:fp,alias:"important",inside:oo},punctuation:/\|/}},"table-data-rows":{pattern:/(.+\n)[^]+/,lookbehind:!0,inside:{"table-data":{pattern:fp,inside:oo},punctuation:/\|/}},"table-line":{pattern:/.+/,inside:{punctuation:/\S+/}}}},code:[{pattern:/(^[ 	]*\n)(?:    |	).+(?:\n(?:    |	).+)*/m,lookbehind:!0,alias:"keyword"},{pattern:/^(```+)[^`][^]*?^\1`*$/gm,inside:{punctuation:/^`+|`+$/,"code-language":/^.+/,"code-block":/(?!^)[^]+(?=\n)/,[Gi](t,n){var e=Vu(t,n),i;return e[5]&&(i=(/[a-z][\w-]*/i.exec(e[1].content.replace(/\b#/g,"sharp").replace(/\b\+\+/g,"pp"))||[""])[0].toLowerCase(),e[3].alias="language-"+i,(n=ue[i])&&(e[3].content=Oa(e[3].content,n))),e}}}],title:[{pattern:/\S.*\n(?:==+|--+)(?=[ 	]*$)/m,alias:"important",inside:{punctuation:/=+$|-+$/}},{pattern:/(^\s*)#.+/m,lookbehind:!0,alias:"important",inside:{punctuation:/^#+|#+$/}}],hr:{pattern:/(^\s*)([*-])(?:[ 	]*\2){2,}(?=\s*$)/m,lookbehind:!0,alias:"punctuation"},list:{pattern:/(^\s*)(?:[*+-]|\d+\.)(?=[ 	].)/m,lookbehind:!0,alias:"punctuation"},"url-reference":{pattern:/!?\[[^\]]+\]:[ 	]+(?:\S+|<(?:\\.|[^\\>])+>)(?:[ 	]+(?:"(?:\\.|[^\\"])*"|'(?:\\.|[^\\'])*'|\((?:\\.|[^\\)])*\)))?/,inside:{variable:{pattern:/^(!?\[)[^\]]+/,lookbehind:!0},string:/(?:"(?:\\.|[^\\"])*"|'(?:\\.|[^\\'])*'|\((?:\\.|[^\\)])*\))$/,punctuation:/^[[\]!:]|<|>/},alias:"url"},bold:{pattern:dc("\\b__(?:(?!_)<0>|_(?:(?!_)<0>)+_)+__\\b|\\*\\*(?:(?!\\*)<0>|\\*(?:(?!\\*)<0>)+\\*)+\\*\\*"),lookbehind:!0,inside:{content:{pattern:/(^..)[^]+(?=..)/,lookbehind:!0,inside:{}},punctuation:/../}},italic:{pattern:dc("\\b_(?:(?!_)<0>|__(?:(?!_)<0>)+__)+_\\b|\\*(?:(?!\\*)<0>|\\*\\*(?:(?!\\*)<0>)+\\*\\*)+\\*"),lookbehind:!0,inside:{content:{pattern:/(?!^)[^]+(?=.)/,inside:{}},punctuation:/./}},strike:{pattern:dc("(~~?)(?:(?!~)<0>)+\\2"),lookbehind:!0,inside:{punctuation:/^~~?|~~?$/,content:{pattern:/[^]+/,inside:{}}}},"code-snippet":{pattern:/(^|[^\\`])(`+)[^\n`](?:|.*?[^\n`])\2(?!`)/g,lookbehind:!0,alias:"code keyword"},url:{pattern:dc('!?\\[(?:(?!\\])<0>)+\\](?:\\([^\\s)]+(?:[ 	]+"(?:\\\\.|[^\\\\"])*")?\\)|[ 	]?\\[(?:(?!\\])<0>)+\\])'),lookbehind:!0,inside:{operator:/^!/,content:{pattern:/(^\[)[^\]]+(?=\])/,lookbehind:!0,inside:{}},variable:{pattern:/(^\][ 	]?\[)[^\]]+(?=\]$)/,lookbehind:!0},url:{pattern:/(^\]\()[^\s)]+/,lookbehind:!0},string:{pattern:/(^[ 	]+)"(?:\\.|[^\\"])*"(?=\)$)/,lookbehind:!0},"markup-bracket":oo["markup-bracket"]}}});["url","bold","italic","strike"].forEach(t=>{["url","bold","italic","strike","code-snippet","markup-bracket"].forEach(n=>{t!=n&&(oo[t].inside.content.inside[n]=oo[n])})});var jA=["editorContainer"],ao=class t{code=Gp("");language=yt("markdown");readonly=yt(!1,{transform:n=>n=="true"});editor=void 0;editorContainer;ngAfterViewInit(){this.editorContainer.nativeElement&&(this.editor=this.initEditor())}ngOnChanges(n){n.code.previousValue!==n.code.currentValue&&this.editor?.setOptions({value:n.code.currentValue})}initEditor(){let n=Ra(this.editorContainer.nativeElement,{value:this.code(),language:this.language(),lineNumbers:!1,wordWrap:!0,readOnly:this.readonly(),onUpdate:e=>{this.code.set(e)}});return n.addExtensions($v(),Av(!0),Fv(),rp()),this.code.subscribe(()=>{this.editor?.update()}),n}static \u0275fac=function(e){return new(e||t)};static \u0275cmp=T({type:t,selectors:[["app-prism-editor"]],viewQuery:function(e,i){if(e&1&&Ne(jA,5),e&2){let r;H(r=U())&&(i.editorContainer=r.first)}},inputs:{code:[1,"code"],language:[1,"language"],readonly:[1,"readonly"]},outputs:{code:"codeChange"},features:[dt],decls:2,vars:0,consts:[["editorContainer",""]],template:function(e,i){e&1&&Jt(0,"div",null,0)},styles:["[_nghost-%COMP%] > div[_ngcontent-%COMP%]{margin-bottom:.5em}"]})};function VA(t,n){if(t&1&&(b(0,"div",3)(1,"span"),w(2,"Operation description"),f(),P(3,"markdown",17),f()),t&2){let e=C();h(3),R("data",e.operation().description)}}function zA(t,n){if(t&1&&(b(0,"div",3)(1,"span"),w(2,"Message description"),f(),P(3,"markdown",17),f()),t&2){let e=C();h(3),R("data",e.operation().message.description)}}function HA(t,n){if(t&1&&(b(0,"div",3)(1,"span"),w(2,"Content-Type"),f(),b(3,"span",18),w(4),f()()),t&2){let e=C();h(4),Le(e.operation().message.contentType)}}function UA(t,n){if(t&1&&(b(0,"div",3)(1,"span"),w(2,"Reply to"),f(),b(3,"span")(4,"mat-chip-set")(5,"a",4)(6,"mat-chip"),P(7,"mat-icon",19),w(8),f()()(),w(9," with "),b(10,"mat-chip-set")(11,"a",4)(12,"mat-chip"),P(13,"mat-icon",6),w(14),f()()()()()),t&2){let e=C();h(5),R("href",ni(e.operation().reply.channelAnchorUrl),Ze),h(3),J(" ",e.operation().reply.channelName," "),h(3),R("href",ni(e.operation().reply.messageAnchorUrl),Ze),h(3),J(" ",e.operation().reply.messageName," ")}}function qA(t,n){if(t&1&&(b(0,"a",4)(1,"mat-chip"),P(2,"mat-icon",20),w(3),f()()),t&2){let e=n.$implicit;R("href",ni(e.anchorUrl),Ze),h(3),J(" ",e.name," ")}}function GA(t,n){if(t&1){let e=ot();b(0,"app-prism-editor",22),bo("codeChange",function(r){Ke(e);let o=C(3);return _o(o.operationBindingExampleString,r)||(o.operationBindingExampleString=r),Ye(r)}),f()}if(t&2){let e=C(3);go("code",e.operationBindingExampleString)}}function WA(t,n){if(t&1&&(b(0,"div"),k(1,GA,1,1,"app-prism-editor",21),f()),t&2){let e=C(2);h(),D(e.operationBindingExampleString?1:-1)}}function KA(t,n){if(t&1&&(b(0,"div",0)(1,"div",5)(2,"h6"),w(3,"Operation Binding"),f()(),b(4,"div",5),k(5,WA,2,1,"div"),f()()),t&2){let e=C();h(5),D(e.operation().protocol?5:-1)}}function YA(t,n){if(t&1){let e=ot();b(0,"app-prism-editor",22),bo("codeChange",function(r){Ke(e);let o=C(2);return _o(o.messageBindingExampleString,r)||(o.messageBindingExampleString=r),Ye(r)}),f()}if(t&2){let e=C(2);go("code",e.messageBindingExampleString)}}function QA(t,n){t&1&&(b(0,"span")(1,"i"),w(2,"none"),f()())}function XA(t,n){if(t&1&&(b(0,"div",0)(1,"div",5)(2,"h6"),w(3,"Message Binding"),f()(),b(4,"div",5)(5,"div"),k(6,YA,1,1,"app-prism-editor",21),k(7,QA,3,0,"span"),f()()()),t&2){let e=C();h(6),D(e.messageBindingExampleString?6:-1),h(),D(e.messageBindingExampleString?-1:7)}}function ZA(t,n){if(t&1&&(b(0,"a",4)(1,"mat-chip"),P(2,"mat-icon",6),w(3),f()()),t&2){let e=C(2);R("href",e.headers().anchorUrl,Ze),h(3),J(" ",e.headers().title," ")}}function JA(t,n){if(t&1&&P(0,"app-schema",7),t&2){let e=C(2);R("schema",e.headers())}}function eI(t,n){if(t&1){let e=ot();b(0,"div",0)(1,"div",5)(2,"h6"),w(3,"Headers"),f(),b(4,"mat-chip-set"),k(5,ZA,4,2,"a",4),f(),k(6,JA,1,1,"app-schema",7),f(),b(7,"div",5)(8,"app-prism-editor",23),bo("codeChange",function(r){Ke(e);let o=C();return _o(o.headersExample.value,r)||(o.headersExample.value=r),Ye(r)}),f()()()}if(t&2){let e=C();h(5),D(e.headers().anchorUrl!==e.initSchema.anchorUrl?5:-1),h(),D(e.headers()?6:-1),h(2),go("code",e.headersExample.value)}}function tI(t,n){if(t&1&&P(0,"app-schema",7),t&2){let e=C();R("schema",e.defaultSchema())}}var mc=class t{constructor(n,e,i,r){this.asyncApiService=n;this.publisherService=e;this.uiService=i;this.snackBar=r}channelName=yt.required();operation=yt.required();initSchema=Fa;defaultSchema=X(Fa);defaultExample=X(Pa);originalDefaultExample=X(Pa);exampleLanguage=en(()=>{let n=this.operation().message.contentType;return n.includes("avro")?"json":n.split("/").pop()||"json"});headers=X(Fa);headersExample=Pa;originalHeadersExample=X(Pa);operationBindingExampleString;messageBindingExampleString;isShowBindings=X(xe.DEFAULT_SHOW_BINDINGS);isShowHeaders=X(xe.DEFAULT_SHOW_HEADERS);canPublish=X(!1);ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(n=>{let e=n.components.schemas,i=this.operation().message.payload;if(i.ts_type==="ref"){let o=i.name.slice(i.name.lastIndexOf("/")+1),a=e.get(o);this.defaultSchema.set(a),this.defaultExample.set(a.example||Ki),this.originalDefaultExample.set(a.example||Ki)}else this.defaultSchema.set(i),this.defaultExample.set(i.example||Ki),this.originalDefaultExample.set(i.example||Ki);let r=this.operation().message.headers;r?this.headers.set(e.get(r?.name)):this.headers.set(Fa),this.headersExample=this.headers().example||Ki,this.originalHeadersExample.set(this.headers().example||Ki),this.operationBindingExampleString=new Ht(this.operation().bindings[this.operation().protocol])?.value,this.messageBindingExampleString=this.createBindingExample(this.operation().message.bindings.get(this.operation().protocol))?.value,this.reset(),this.publisherService.canPublish(this.operation().protocol).subscribe(o=>{this.canPublish.set(o)})}),this.uiService.isShowBindings$.subscribe(n=>this.isShowBindings.set(n)),this.uiService.isShowHeaders$.subscribe(n=>this.isShowHeaders.set(n))}createBindingExample(n){if(n==null)return;let e={};return Object.keys(n).forEach(i=>{i!=="bindingVersion"&&(e[i]=this.getExampleValue(n[i]))}),new Ht(e)}getExampleValue(n){if(typeof n=="string")return n;if("examples"in n&&typeof n.examples=="object")return n.examples[0]}reset(){this.defaultExample.set(new Ht(this.originalDefaultExample().rawValue)),this.headersExample=new Ht(this.originalHeadersExample().rawValue)}publish(){let n=this.defaultExample().value,e=this.operation().message.payload.name,i=this.headersExample.value,r=this.messageBindingExampleString;try{let o=i===""?{}:ld("Unable to convert headers to JSON object (or is empty)",()=>JSON.parse(i||"")),a=r===""?{}:ld("Unable to convert bindings to JSON object (or is empty)",()=>JSON.parse(r||""));this.publisherService.publish(this.operation().protocol||"not-supported-protocol",this.channelName(),n,e,o,a).subscribe(s=>this.handlePublishSuccess(),s=>this.handlePublishError(s))}catch(o){this.snackBar.open("Unable to create publishing payload: "+o?.message,"ERROR",{duration:3e3})}}handlePublishSuccess(){return this.snackBar.open("Example payload sent to: "+this.channelName(),"PUBLISHED",{duration:3e3})}handlePublishError(n){let e="Publish failed";return n?.status===ce.NOT_FOUND&&(e+=": no publisher was provided for "+this.operation().protocol),this.snackBar.open(e,"ERROR",{duration:4e3})}static \u0275fac=function(e){return new(e||t)(Ee(nt),Ee(zr),Ee(xe),Ee(Qo))};static \u0275cmp=T({type:t,selectors:[["app-channel-operation"]],inputs:{channelName:[1,"channelName"],operation:[1,"operation"]},decls:45,vars:15,consts:[[1,"row"],[1,"width-12-of-12","width-12-of-12-s"],[1,"table","margin-vertical-1em"],[1,"table-row"],[3,"href"],[1,"width-6-of-12","width-12-of-12-s"],["matChipAvatar","","fontIcon","schema"],[3,"schema"],[3,"codeChange","code","language"],[1,"flex","space-between"],[1,"flex","gap-8"],["mat-raised-button","",3,"cdkCopyToClipboard"],["fontIcon","content_copy"],["mat-raised-button","",3,"click"],["fontIcon","restart_alt"],["mat-raised-button","",3,"click","disabled"],["fontIcon","send"],[3,"data"],[1,"text-console"],["matChipAvatar","","fontIcon","swap_vert"],["matChipAvatar","","fontIcon","dns"],["language","json","readonly","true",3,"code"],["language","json","readonly","true",3,"codeChange","code"],["language","json",3,"codeChange","code"]],template:function(e,i){e&1&&(b(0,"div",0)(1,"div",1)(2,"div",2)(3,"div",3)(4,"span"),w(5,"Channel"),f(),b(6,"span"),w(7),f()(),k(8,VA,4,1,"div",3),k(9,zA,4,1,"div",3),k(10,HA,5,1,"div",3),k(11,UA,15,6,"div",3),b(12,"div",3)(13,"span"),w(14,"Servers"),f(),b(15,"span")(16,"mat-chip-set"),je(17,qA,4,3,"a",4,$e),f()()()()()(),k(19,KA,6,1,"div",0),k(20,XA,8,2,"div",0),k(21,eI,9,3,"div",0),b(22,"div",0)(23,"div",5)(24,"h6"),w(25,"Payload"),f(),b(26,"mat-chip-set")(27,"a",4)(28,"mat-chip"),P(29,"mat-icon",6),w(30),f()()(),k(31,tI,1,1,"app-schema",7),f(),b(32,"div",5)(33,"app-prism-editor",8),bo("codeChange",function(o){return _o(i.defaultExample().value,o)||(i.defaultExample().value=o),o}),f(),b(34,"div",9)(35,"div",10)(36,"button",11),P(37,"mat-icon",12),w(38," Copy "),f(),b(39,"button",13),pe("click",function(){return i.reset()}),P(40,"mat-icon",14),w(41," Reset "),f()(),b(42,"button",15),pe("click",function(){return i.publish()}),P(43,"mat-icon",16),w(44," Publish "),f()()()()),e&2&&(h(7),Le(i.operation().channelName),h(),D(i.operation().description?8:-1),h(),D(i.operation().message.description?9:-1),h(),D(i.operation().message.contentType?10:-1),h(),D(i.operation().reply?11:-1),h(6),Ve(i.operation().servers),h(2),D(i.isShowBindings()?19:-1),h(),D(i.isShowBindings()?20:-1),h(),D(i.isShowHeaders()?21:-1),h(6),R("href",i.operation().message.payload.anchorUrl,Ze),h(3),J(" ",i.operation().message.payload.title," "),h(),D(i.defaultSchema()?31:-1),h(2),go("code",i.defaultExample().value),R("language",i.exampleLanguage()),h(3),R("cdkCopyToClipboard",i.defaultExample().value),h(6),R("disabled",!i.canPublish))},dependencies:[vn,bn,mn,Gn,fi,Qr,Ct,Mt,ao,Zr,Da,Kb,jn,kr],styles:[".table-row[_ngcontent-%COMP%] > *[_ngcontent-%COMP%]:first-child{vertical-align:middle}[_nghost-%COMP%]     .mdc-evolution-chip-set__chips{max-width:100%}[_nghost-%COMP%]     .mat-mdc-standard-chip .mdc-evolution-chip__cell--primary, [_nghost-%COMP%]     .mat-mdc-standard-chip .mdc-evolution-chip__action--primary, [_nghost-%COMP%]     .mat-mdc-standard-chip .mat-mdc-chip-action-label{overflow:hidden}"]})};var nI=(t,n)=>({"send-badge":t,"receive-badge":n});function iI(t,n){if(t&1&&(b(0,"div"),P(1,"app-prism-editor",4),f()),t&2){let e=C(2).$implicit,i=C();h(),R("code",ni(i.JSON.stringify(e.bindings,null,2)))}}function rI(t,n){if(t&1&&(b(0,"div",2)(1,"div",3)(2,"h6"),w(3,"Channel Binding"),f()(),b(4,"div",3),k(5,iI,2,2,"div"),f()()),t&2){let e=C().$implicit;h(5),D(e.bindings?5:-1)}}function oI(t,n){if(t&1&&(b(0,"span",7),w(1),f()),t&2){let e=C().$implicit;h(),J(" ",e.operation.protocol," ")}}function aI(t,n){t&1&&P(0,"br")}function sI(t,n){if(t&1&&(b(0,"mat-card",5)(1,"mat-card-header")(2,"mat-card-title"),w(3),f(),b(4,"span",6),k(5,oI,2,1,"span",7),b(6,"span",8),w(7),f()()(),b(8,"mat-card-content"),P(9,"app-channel-operation",9),f()(),k(10,aI,1,0,"br")),t&2){let e=n.$implicit,i=n.$index,r=n.$count,o=C().$implicit;R("id",e.anchorIdentifier),h(2),le("data-testid",o.anchorIdentifier),h(),J(" ",e.operation.message.title," "),h(2),D(e.operation.protocol?5:-1),h(),R("ngClass",lr(9,nI,e.operation.operationType==="send",e.operation.operationType==="receive")),h(),J(" ",e.operation.operationType," "),h(2),R("channelName",o.name)("operation",e.operation),h(),D(i!==r-1?10:-1)}}function lI(t,n){t&1&&P(0,"br")}function cI(t,n){if(t&1&&(b(0,"article",0)(1,"h3"),P(2,"mat-icon",1),w(3),f(),k(4,rI,6,1,"div",2),je(5,sI,11,12,null,null,$e),k(7,lI,1,0,"br"),f()),t&2){let e=n.$implicit,i=n.$index,r=n.$count,o=C();R("id",e.anchorIdentifier),h(3),J(" ",e.name),h(),D(o.isShowBindings()?4:-1),h(),Ve(e.operations),h(2),D(i!==r-1?7:-1)}}var uc=class t{constructor(n,e){this.asyncApiService=n;this.uiService=e}channels=X([]);isShowBindings=X(xe.DEFAULT_SHOW_BINDINGS);JSON=JSON;ngOnInit(){this.asyncApiService.getAsyncApi().subscribe(n=>{this.channels.set(n.channels)}),this.uiService.isShowBindings$.subscribe(n=>this.isShowBindings.set(n))}static \u0275fac=function(e){return new(e||t)(Ee(nt),Ee(xe))};static \u0275cmp=T({type:t,selectors:[["app-channels"]],decls:4,vars:0,consts:[["appNavigationTarget","",3,"id"],["fontIcon","swap_vert"],[1,"row"],[1,"width-6-of-12","width-12-of-12-s"],["language","json","readonly","true",3,"code"],["appearance","outlined","appNavigationTarget","",3,"id"],[1,"flex","gap-16","padding-horizontal-1em","height-fix-content"],[1,"badge","protocol-badge"],[1,"badge",3,"ngClass"],[3,"channelName","operation"]],template:function(e,i){e&1&&(b(0,"h2"),w(1,"Channels"),f(),je(2,cI,8,4,"article",0,$e)),e&2&&(h(2),Ve(i.channels()))},dependencies:[ao,fn,Co,qn,Gr,Kr,Yr,Wr,mc,Zt,Mt],styles:[".badge[_ngcontent-%COMP%]{border-radius:4px;padding:.3em;font-size:smaller;text-transform:uppercase}.protocol-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-protocol);background-color:var(--springwolf-badge-color-background-protocol)}.send-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-send);background-color:var(--springwolf-badge-color-background-send)}.receive-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-receive);background-color:var(--springwolf-badge-color-background-receive)}"]})};var dI=["scrollableElement"],mI=["*"],gp=(t,n)=>({selected:t,expanded:n});function uI(t,n){if(t&1&&P(0,"mat-icon",5),t&2){let e=C().$implicit;R("fontIcon",ni(e.icon))}}function pI(t,n){if(t&1&&(b(0,"span"),w(1),f()),t&2){let e=n.$implicit;vt(Cc("badge ",e.type,"-badge")),h(),Le(e.value)}}function hI(t,n){if(t&1&&(b(0,"span"),w(1),f()),t&2){let e=n.$implicit;vt(Cc("badge ",e.type,"-badge")),h(),Le(e.value)}}function fI(t,n){if(t&1&&(b(0,"li",2)(1,"span")(2,"a",6),w(3),f(),je(4,hI,2,4,"span",7,$e),f()()),t&2){let e=n.$implicit;R("ngClass",lr(3,gp,e.selected,e.expanded)),h(2),R("href",e.href,Ze),h(),J(" ",e.name.join("\u200B")," "),h(),Ve(e.tags)}}function gI(t,n){if(t&1&&(b(0,"li",2)(1,"span")(2,"a",6),w(3),f(),je(4,pI,2,4,"span",7,$e),f(),b(6,"ul"),je(7,fI,6,6,"li",2,$e),f()()),t&2){let e=n.$implicit;R("ngClass",lr(3,gp,e.selected,e.expanded)),h(2),R("href",e.href,Ze),h(),J(" ",e.name.join("\u200B")," "),h(),Ve(e.tags),h(3),Ve(e.children)}}function _I(t,n){if(t&1&&(b(0,"ul",2)(1,"li")(2,"span"),k(3,uI,1,2,"mat-icon",5),b(4,"b")(5,"a",6),w(6),f()()(),b(7,"ul"),je(8,gI,9,6,"li",2,$e),f()()()),t&2){let e=n.$implicit;R("ngClass",lr(4,gp,e.selected,e.expanded)),h(3),D(e.icon?3:-1),h(2),R("href",e.href,Ze),h(),J(" ",e.name.join("\u200B")," "),h(2),Ve(e.children)}}var pc=class t{constructor(n,e){this.asyncApiService=n;this.location=e}scrollableElement;navigationTargets;navigation=X([]);ngOnInit(){this.location.subscribe(this.scrollToUrlLocation),this.asyncApiService.getAsyncApi().subscribe(n=>{let e=[];e.push({name:["Info"],icon:"info",href:pt.BASE_URL+"info"});let i=Array.from(n.servers.keys()).map(a=>({name:this.splitForWordBreaking(a),href:pt.BASE_URL+n.servers.get(a).anchorIdentifier,tags:[{type:"protocol",value:n.servers.get(a).protocol}]}));e.push({name:["Servers"],icon:"dns",href:pt.BASE_URL+"servers",children:i});let r={name:["Channels & Operations"],icon:"swap_vert",href:pt.BASE_URL+"channels",children:[]};n.channels.forEach(a=>{let s=a.operations.map(d=>({name:this.splitForWordBreaking(d.operation.message.title),href:pt.BASE_URL+d.anchorIdentifier,tags:[{type:"operation-"+d.operation.operationType,value:d.operation.operationType}]})),l=s.flatMap(d=>d.tags).flatMap(d=>d),c={name:this.splitForWordBreaking(a.name),href:pt.BASE_URL+a.anchorIdentifier,tags:this.filterAndSort(l,"value"),children:s};r.children.push(c)}),e.push(r);let o={name:["Schemas"],icon:"schema",href:pt.BASE_URL+"schemas",children:[]};n.components.schemas.forEach(a=>{o.children.push({name:this.splitForWordBreaking(a.title),href:pt.BASE_URL+""+a.anchorIdentifier})}),e.push(o),this.navigation.set(e),this.scrollToUrlLocation()})}splitForWordBreaking=n=>n.split(/(?<=[.,_/\-])/);filterAndSort(n,e){let i=new Set;return n.filter(o=>{let a=JSON.stringify(o);return i.has(a)?!1:i.add(a)}).sort((o,a)=>o[e]<a[e]?-1:o[e]>a[e]?1:0)}ngAfterViewInit(){this.scrollableElement.nativeElement.addEventListener("scroll",this.updateNavigationSelection)}updateNavigationSelection=()=>{let n="",e=this.scrollableElement.nativeElement.scrollTop;document.querySelectorAll("[appNavigationTarget]").forEach(i=>{let r=i,o=r.offsetTop,a=r.offsetHeight;e>=o&&e<o+a&&(n=pt.BASE_URL+""+r.getAttribute("id"))}),this.navigation().forEach(i=>{let r=!1;i.children?.forEach(o=>{let a=!1;o.children?.forEach(s=>{s.selected=n==s.href,s.expanded=s.selected,a=a||s.selected}),o.selected=n==o.href||a,r=r||o.selected,o.children?.forEach(s=>{s.expanded=o.selected})}),i.selected=n==i.href||r,i.children?.forEach(o=>{o.expanded=i.selected}),i.expanded=!0}),this.navigation.set([...this.navigation()])};scrollToUrlLocation=()=>{setTimeout(()=>{document.getElementById(window.location.hash.substring(1))?.scrollIntoView(),this.updateNavigationSelection()},10)};static \u0275fac=function(e){return new(e||t)(Ee(nt),Ee(dr))};static \u0275cmp=T({type:t,selectors:[["app-sidenav"]],contentQueries:function(e,i,r){if(e&1&&Xe(r,Zt,5),e&2){let o;H(o=U())&&(i.navigationTargets=o)}},viewQuery:function(e,i){if(e&1&&Ne(dI,5),e&2){let r;H(r=U())&&(i.scrollableElement=r.first)}},ngContentSelectors:mI,decls:8,vars:0,consts:[["scrollableElement",""],["mode","side","opened","",1,"sidenav","width-s-hide"],[1,"entry",3,"ngClass"],[1,"width-s-margin-reset"],[2,"overflow-y","auto","height","100%"],[3,"fontIcon"],[3,"href"],[3,"class"]],template:function(e,i){e&1&&(ne(),b(0,"mat-sidenav-container")(1,"mat-sidenav",1),je(2,_I,10,7,"ul",2,$e),f(),b(4,"mat-sidenav-content",3)(5,"div",4,0),B(7),f()()()),e&2&&(h(2),Ve(i.navigation()))},dependencies:[Aa,ju,yv,Ul,Ct,Mt,fn,Co],styles:["a[_ngcontent-%COMP%]{color:#373737;text-decoration:none}.sidenav[_ngcontent-%COMP%]   ul[_ngcontent-%COMP%]{list-style-type:none;padding-inline-start:.5em}.sidenav[_ngcontent-%COMP%]   .entry.selected[_ngcontent-%COMP%] > span[_ngcontent-%COMP%] > a[_ngcontent-%COMP%]{font-weight:700;color:var(--mat-app-text-color)}.sidenav[_ngcontent-%COMP%]   .entry[_ngcontent-%COMP%]:not(.expanded){display:none}.sidenav[_ngcontent-%COMP%]   .entry[_ngcontent-%COMP%] > span[_ngcontent-%COMP%]{display:flex;justify-content:space-between}.sidenav[_ngcontent-%COMP%]   .badge[_ngcontent-%COMP%]{text-transform:uppercase;font-size:smaller;border-radius:.3em;padding:0 .2em;margin:.2em .3em auto}.sidenav[_ngcontent-%COMP%]   .badge.operation-send-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-send);background-color:var(--springwolf-badge-color-background-send)}.sidenav[_ngcontent-%COMP%]   .badge.operation-receive-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-receive);background-color:var(--springwolf-badge-color-background-receive)}.sidenav[_ngcontent-%COMP%]   .badge.protocol-badge[_ngcontent-%COMP%]{color:var(--springwolf-badge-color-protocol);background-color:var(--springwolf-badge-color-background-protocol)}"]})};function bI(t,n){if(t&1&&(b(0,"button",9),w(1," Group "),f()),t&2){C();let e=Vt(16);R("matMenuTriggerFor",e)}}function vI(t,n){if(t&1){let e=ot();b(0,"button",15),pe("click",function(){let r=Ke(e).$implicit,o=C();return Ye(o.changeGroup(r.value))}),b(1,"mat-icon"),w(2),f(),w(3),f()}if(t&2){let e=n.$implicit,i=C();h(2),Le(i.isGroup()==e.value?"radio_button_checked":"radio_button_unchecked"),h(),J(" ",e.viewValue," ")}}var hc=class t{constructor(n,e,i){this.uiService=n;this.asyncApiService=e;this.assetService=i}groups=X([]);isGroup=X(xe.DEFAULT_GROUP);isShowBindings=X(xe.DEFAULT_SHOW_BINDINGS);isShowHeaders=X(xe.DEFAULT_SHOW_HEADERS);title=X("");ngOnInit(){this.assetService.load(),this.uiService.isShowBindings$.subscribe(n=>this.isShowBindings.set(n)),this.uiService.isShowHeaders$.subscribe(n=>this.isShowHeaders.set(n)),this.uiService.isGroup$.subscribe(n=>this.isGroup.set(n)),this.uiService.uiConfig.subscribe(n=>{if(n.groups.length>0){let e=n.groups.map(i=>({value:i.name,viewValue:i.name}));this.groups.set([{value:xe.DEFAULT_GROUP,viewValue:xe.DEFAULT_GROUP},...e])}}),this.asyncApiService.getAsyncApi().subscribe(n=>{this.title.set(n.info.title)})}toggleIsShowBindings(){this.uiService.toggleIsShowBindings(!this.isShowBindings())}toggleIsShowHeaders(){this.uiService.toggleIsShowHeaders(!this.isShowHeaders())}changeGroup(n){this.uiService.changeGroup(n)}static \u0275fac=function(e){return new(e||t)(Ee(xe),Ee(nt),Ee(fr))};static \u0275cmp=T({type:t,selectors:[["app-header"]],decls:30,vars:5,consts:[["settings","matMenu"],["group","matMenu"],[1,"row","space-between"],["href","https://www.springwolf.dev","target","_blank",1,"flex","flex-column","items-center"],["src","assets/springwolf-logo.png","alt","Logo","height","1024","width","1024",1,"logo"],[1,"width-s-hide"],[1,"flex","flex-column","items-center"],["mat-button","","data-testid","settings",3,"matMenuTriggerFor"],[2,"color","black"],["mat-menu-item","","data-testid","settings-group-menu",3,"matMenuTriggerFor"],["mat-menu-item",""],["mat-menu-item","","data-testid","settings-bindings",3,"click"],["mat-menu-item","","data-testid","settings-headers",3,"click"],["href","https://github.com/springwolf/springwolf-core","target","_blank"],["src","assets/github.png","alt","Github","height","1024","width","1024",1,"github"],["mat-menu-item","",3,"click"]],template:function(e,i){if(e&1&&(b(0,"mat-toolbar",2)(1,"span")(2,"a",3),P(3,"img",4),w(4," Springwolf "),f()(),b(5,"h1",5),w(6),f(),b(7,"div",6)(8,"button",7)(9,"mat-icon",8),w(10,"settings"),f(),w(11," Settings "),f(),b(12,"mat-menu",null,0),k(14,bI,2,1,"button",9),b(15,"mat-menu",null,1),je(17,vI,4,2,"button",10,$e),f(),b(19,"button",11),pe("click",function(){return i.toggleIsShowBindings()}),b(20,"mat-icon"),w(21),f(),w(22," Show bindings "),f(),b(23,"button",12),pe("click",function(){return i.toggleIsShowHeaders()}),b(24,"mat-icon"),w(25),f(),w(26," Show headers "),f()(),w(27," \xA0 "),b(28,"a",13),P(29,"img",14),f()()()),e&2){let r=Vt(13);h(6),Le(i.title()),h(2),R("matMenuTriggerFor",r),h(6),D(0<i.groups().length?14:-1),h(3),Ve(i.groups()),h(4),Le(i.isShowBindings()?"check_box":"check_box_outline_blank"),h(4),Le(i.isShowHeaders()?"check_box":"check_box_outline_blank")}},dependencies:[Ca,qb,jn,kr,wa,Ur,xa,Ub,Ct,Mt],styles:[".logo[_ngcontent-%COMP%]{height:3em;width:3em;display:block}.github[_ngcontent-%COMP%]{height:2em;width:2em;display:block;filter:invert(100%)}[_nghost-%COMP%]     label, a[_ngcontent-%COMP%]{color:var(--mat-toolbar-container-text-color)}a[_ngcontent-%COMP%]{text-decoration:none}a[_ngcontent-%COMP%]:hover{color:#d3d3d3}[_nghost-%COMP%]     mat-icon{height:3em;width:3em;transform:scale(.75);filter:invert(100%)}"]})};var fc=class t{static \u0275fac=function(e){return new(e||t)};static \u0275cmp=T({type:t,selectors:[["app-root"]],decls:11,vars:0,consts:[[1,"mat-typography"],["appNavigationTarget","","id","info"],["appNavigationTarget","","id","servers"],["appNavigationTarget","","id","channels"],["appNavigationTarget","","id","schemas"]],template:function(e,i){e&1&&(P(0,"app-header"),b(1,"main",0)(2,"app-sidenav")(3,"article",1),P(4,"app-info"),f(),b(5,"article",2),P(6,"app-servers"),f(),b(7,"article",3),P(8,"app-channels"),f(),b(9,"article",4),P(10,"app-schemas"),f()()())},dependencies:[Gl,lv,vn,hc,pc,ec,tc,uc,Jl,Zt],styles:[".app-header[_ngcontent-%COMP%]{position:fixed;z-index:100;width:100%}main[_ngcontent-%COMP%]{margin:0;height:calc(100% - 64px);padding:0}article[_ngcontent-%COMP%]{margin:2em 1em}"]})};fs.production&&void 0;Nc(fc,Hb).catch(t=>console.error(t));
