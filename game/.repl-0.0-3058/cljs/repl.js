// Compiled by ClojureScript 0.0-3058 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
cljs.repl.print_doc = (function cljs$repl$print_doc(m){
cljs.core.println.call(null,"-------------------------");

cljs.core.println.call(null,[cljs.core.str((function (){var temp__4126__auto__ = new cljs.core.Keyword(null,"ns","ns",441598760).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__4126__auto__)){
var ns = temp__4126__auto__;
return [cljs.core.str(ns),cljs.core.str("/")].join('');
} else {
return null;
}
})()),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));

if(cljs.core.truth_(new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m))){
var seq__12604_12608 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__12605_12609 = null;
var count__12606_12610 = (0);
var i__12607_12611 = (0);
while(true){
if((i__12607_12611 < count__12606_12610)){
var f_12612 = cljs.core._nth.call(null,chunk__12605_12609,i__12607_12611);
cljs.core.println.call(null,"  ",f_12612);

var G__12613 = seq__12604_12608;
var G__12614 = chunk__12605_12609;
var G__12615 = count__12606_12610;
var G__12616 = (i__12607_12611 + (1));
seq__12604_12608 = G__12613;
chunk__12605_12609 = G__12614;
count__12606_12610 = G__12615;
i__12607_12611 = G__12616;
continue;
} else {
var temp__4126__auto___12617 = cljs.core.seq.call(null,seq__12604_12608);
if(temp__4126__auto___12617){
var seq__12604_12618__$1 = temp__4126__auto___12617;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12604_12618__$1)){
var c__4807__auto___12619 = cljs.core.chunk_first.call(null,seq__12604_12618__$1);
var G__12620 = cljs.core.chunk_rest.call(null,seq__12604_12618__$1);
var G__12621 = c__4807__auto___12619;
var G__12622 = cljs.core.count.call(null,c__4807__auto___12619);
var G__12623 = (0);
seq__12604_12608 = G__12620;
chunk__12605_12609 = G__12621;
count__12606_12610 = G__12622;
i__12607_12611 = G__12623;
continue;
} else {
var f_12624 = cljs.core.first.call(null,seq__12604_12618__$1);
cljs.core.println.call(null,"  ",f_12624);

var G__12625 = cljs.core.next.call(null,seq__12604_12618__$1);
var G__12626 = null;
var G__12627 = (0);
var G__12628 = (0);
seq__12604_12608 = G__12625;
chunk__12605_12609 = G__12626;
count__12606_12610 = G__12627;
i__12607_12611 = G__12628;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
if(cljs.core.truth_((function (){var or__4020__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__4020__auto__)){
return or__4020__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m));
} else {
cljs.core.prn.call(null,cljs.core.second.call(null,new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m)));
}
} else {
}
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"special-form","special-form",-1326536374).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Special Form");

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.contains_QMARK_.call(null,m,new cljs.core.Keyword(null,"url","url",276297046))){
if(cljs.core.truth_(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))){
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/"),cljs.core.str(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))].join(''));
} else {
return null;
}
} else {
return cljs.core.println.call(null,[cljs.core.str("\n  Please see http://clojure.org/special_forms#"),cljs.core.str(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Macro");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"REPL Special Function");
} else {
}

return cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));
}
});
