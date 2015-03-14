// Compiled by ClojureScript 0.0-3058 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
cljs.repl.print_doc = (function cljs$repl$print_doc(m){
cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq(["-------------------------"], 0));

cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([[cljs.core.str((function (){var temp__4126__auto__ = cljs.core.constant$keyword$_COLON_ns.cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__4126__auto__)){
var ns = temp__4126__auto__;
return [cljs.core.str(ns),cljs.core.str("/")].join('');
} else {
return null;
}
})()),cljs.core.str(cljs.core.constant$keyword$_COLON_name.cljs$core$IFn$_invoke$arity$1(m))].join('')], 0));

if(cljs.core.truth_(cljs.core.constant$keyword$_COLON_forms.cljs$core$IFn$_invoke$arity$1(m))){
var seq__5984_5988 = cljs.core.seq(cljs.core.constant$keyword$_COLON_forms.cljs$core$IFn$_invoke$arity$1(m));
var chunk__5985_5989 = null;
var count__5986_5990 = (0);
var i__5987_5991 = (0);
while(true){
if((i__5987_5991 < count__5986_5990)){
var f_5992 = chunk__5985_5989.cljs$core$IIndexed$_nth$arity$2(null,i__5987_5991);
cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq(["  ",f_5992], 0));

var G__5993 = seq__5984_5988;
var G__5994 = chunk__5985_5989;
var G__5995 = count__5986_5990;
var G__5996 = (i__5987_5991 + (1));
seq__5984_5988 = G__5993;
chunk__5985_5989 = G__5994;
count__5986_5990 = G__5995;
i__5987_5991 = G__5996;
continue;
} else {
var temp__4126__auto___5997 = cljs.core.seq(seq__5984_5988);
if(temp__4126__auto___5997){
var seq__5984_5998__$1 = temp__4126__auto___5997;
if(cljs.core.chunked_seq_QMARK_(seq__5984_5998__$1)){
var c__4807__auto___5999 = cljs.core.chunk_first(seq__5984_5998__$1);
var G__6000 = cljs.core.chunk_rest(seq__5984_5998__$1);
var G__6001 = c__4807__auto___5999;
var G__6002 = cljs.core.count(c__4807__auto___5999);
var G__6003 = (0);
seq__5984_5988 = G__6000;
chunk__5985_5989 = G__6001;
count__5986_5990 = G__6002;
i__5987_5991 = G__6003;
continue;
} else {
var f_6004 = cljs.core.first(seq__5984_5998__$1);
cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq(["  ",f_6004], 0));

var G__6005 = cljs.core.next(seq__5984_5998__$1);
var G__6006 = null;
var G__6007 = (0);
var G__6008 = (0);
seq__5984_5988 = G__6005;
chunk__5985_5989 = G__6006;
count__5986_5990 = G__6007;
i__5987_5991 = G__6008;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(cljs.core.constant$keyword$_COLON_arglists.cljs$core$IFn$_invoke$arity$1(m))){
if(cljs.core.truth_((function (){var or__4020__auto__ = cljs.core.constant$keyword$_COLON_macro.cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__4020__auto__)){
return or__4020__auto__;
} else {
return cljs.core.constant$keyword$_COLON_repl_special_function.cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([cljs.core.constant$keyword$_COLON_arglists.cljs$core$IFn$_invoke$arity$1(m)], 0));
} else {
cljs.core.prn.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([cljs.core.second(cljs.core.constant$keyword$_COLON_arglists.cljs$core$IFn$_invoke$arity$1(m))], 0));
}
} else {
}
}

if(cljs.core.truth_(cljs.core.constant$keyword$_COLON_special_form.cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq(["Special Form"], 0));

cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([" ",cljs.core.constant$keyword$_COLON_doc.cljs$core$IFn$_invoke$arity$1(m)], 0));

if(cljs.core.contains_QMARK_(m,cljs.core.constant$keyword$_COLON_url)){
if(cljs.core.truth_(cljs.core.constant$keyword$_COLON_url.cljs$core$IFn$_invoke$arity$1(m))){
return cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([[cljs.core.str("\n  Please see http://clojure.org/"),cljs.core.str(cljs.core.constant$keyword$_COLON_url.cljs$core$IFn$_invoke$arity$1(m))].join('')], 0));
} else {
return null;
}
} else {
return cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([[cljs.core.str("\n  Please see http://clojure.org/special_forms#"),cljs.core.str(cljs.core.constant$keyword$_COLON_name.cljs$core$IFn$_invoke$arity$1(m))].join('')], 0));
}
} else {
if(cljs.core.truth_(cljs.core.constant$keyword$_COLON_macro.cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq(["Macro"], 0));
} else {
}

if(cljs.core.truth_(cljs.core.constant$keyword$_COLON_repl_special_function.cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq(["REPL Special Function"], 0));
} else {
}

return cljs.core.println.cljs$core$IFn$_invoke$arity$variadic(cljs.core.array_seq([" ",cljs.core.constant$keyword$_COLON_doc.cljs$core$IFn$_invoke$arity$1(m)], 0));
}
});
