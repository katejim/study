r <- rnorm(10)
q()
r
rm(r)
r
r <- 1
1 -> r
r = 1
1 + 2
3 + 4
2 + 2+ 2 -> r
.Last.value
a <- 1
a
a <- c(1, 3, 5)
a[1]
a[2]
a[2] < 8
a[2] <- 8
a
1:1000
1:100
a[-1]
a[-2]
a[-c(1,2)]
a[c(1, -1)]
a[c(1, 0)]
a[0]
a[-1] <- 0
a
a <- 1:10
a[1:5] <- 1:3
a
a <- 1:10
a < 5
a[a < 5] <- -1000500
a
if (a == 5) print ("1212")
if (all(a == 5)) print ("1212")
if (aany(a == 5)) print ("1212")
if (any(a == 5)) print ("1212")
identical(a, a)
identical(a, 5)
all.equal(1, 1)
all.equal(1, 2)
if (all.equal(1, 2)) print ("Hello")
if (isTRUE(all.equal(1, 2))) print ("Hello")
all.equal(1, 2)
?all.equal
?if
?"if"
mode(1)
mode("ssфы")
mode(1 == 1)
is.integer(1)
is.integer(1L)
is.integer(as.integer(101010))
storage.mode(1)
base::ls()
ls()
?ls
v <- 1:2
names(v) <- c("a", "b")
v
v["a"]
names(v)
v <- 1:4
dim(v) <- c(2, 2)
v
dim(v) <- c(2, 3)
v <- 1:8
dim(v) <- c(2, 2, 2)
v
dim(v) <- c(4, 2)
v
dim(v) <- c(3, 2)
colnames(c)
colnames(c) <- letters[1:2]
colnames(v) <- letters[1:2]
rownames(v) <- letters[1:4]
v
v[1,2]
v[1,2] <- 4444
v
v[1,] <- 4444
v
v[] <- 4444
v
v[c(TRUE, FALSE), "b"] <- 4444
v
v[c(TRUE, FALSE), "b"] <- 424423
v
v[c(1,2), ] <- 4424224423
v[11]
v[5]
v[c(1,2), 1:2]
v[c(1,2), 1]
v[c(1,2), 1, drop = FALSE]
t(v)
diag(10)
solve(diag(3))
v <- 1:2
mx <- matrix(1:4, 2, 2)
mx
v
mx * v
mx * mx
mx %*% mx
1 %in% c(2, 4, 5)
1 %in% c(1, 4, 5)
for (i in 1:10) print (i)
list()
list("1", 1)
list("1", 1) -> l
l[[1]]
l[[]]
l[[2]]
l[1]
l[1]
mode(l[1])
mode(l[[1]])
l[1:2]
l[-1]
l <- list(a = 1, b = "dasdasda", c = 2i)
5i
mode(5i)
is.numeric(5i)
l
l
l$a
l$b
l$c
l[["a"]]
l[["a"]]
l$c
l$aeqeqw <- 1
l
name <- "a"
l[[name]]
l$name
l$ae
?"[["
l[["ae", exact = FALSE]]
l$ae
l$ae <- 4234241242
l
names(l)
TRUE <- 31`231
TRUE <- 31231
T <- FALSE; F <- TRUE
T <- NA

