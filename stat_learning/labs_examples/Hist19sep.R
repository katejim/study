iris.setosa <- subset(iris, Species == "setosa")
dim(iris.setosa)
idx <- sample(1:50, size = 35)
idx <- sample(1:50, size = 35)
iris.setosa.train <- iris.setosa[idx, ]
iris.setosa.test <- iris.setosa[-idx, ]
l <- lm(Sepal.Length ~ . - Species, data = iris.setosa.train)
iris.setosa.train$Species <- NULL
l <- lm(Sepal.Length ~ ., data = iris.setosa.train)
pred <- predict(l, iris.setosa.test)
library(lattice(
library(lattice)
xyplot(pred ~ iris.setosa.test$Sepal.Length)
xyplot(pred ~ iris.setosa.test$Sepal.Length)
xyplot(pred ~ iris.setosa.test$Sepal.Length, cex = 4)

xyplot(pred ~ iris.setosa.test$Sepal.Length, cex = 4, pch = 19)
xyplot(pred ~ iris.setosa.test$Sepal.Length, cex = 4, pch = 19)
?read.table
df <- read.csv("~/Downloads/Advertising.csv")
head(df)
df$X <- NULL
df <- read.csv("~/Documents/study/stat_learning/data/Advertising.csv")
df
head(df)
df$X <- NULL
l <- lm(Sales ~ ., data = df)
summary(l)
vcov(l)
cor(df)
library(latticist)
latticist(df)
df$logNew <- log(df$Newspaper)
library(latticist)
library(latticist)
latticist(df)
df$logNew <- NULL
xyplot(residuals(l) ~ fitted(l))
xyplot(residuals(l) ~ fitted(l))
xyplot(residuals(l) ~ fitted(l), panel = panel.loess)
ll <- update(l, . ~ . - Newspaper)
xyplot(residuals(ll) ~ fitted(ll), panel = panel.loess)
xyplot(residuals(ll) ~ fitted(ll), panel = panel.loess)
xyplot(residuals(ll) ~ fitted(ll), panel = panel.loess)
ll <- update(l, . ~ TV)
xyplot(residuals(ll) ~ fitted(ll), panel = panel.xyplot)
xyplot(I(residuals(ll) + fitted(ll)) ~ fitted(ll), panel = panel.xyplot)
xyplot(df$Sales ~ fitted(ll), panel = panel.xyplot)
xyplot(df$Sales ~ fitted(ll), panel = function(...) {panel.xyplot(...); panel.loess(...)})
xyplot(df$Sales ~ fitted(ll), panel = function(...) {panel.xyplot(...); panel.lmline(...)})
library(latticeExtra)
xyplot(~ fitted(ll))
xyplot(fitted(ll))
plot(fitted(ll))
xyplot(fitted(ll) ~ TV, data = df)
xyplot(fitted(l) ~ TV, data = df)
xyplot(I(fitted(l) + 2*sd(l$res)) ~ TV, data = df)
panel.custom <- function(x, y, ...) {panel.lmline(x, y, ...); panel.lmline(x, y - sd(y))}
xyplot(Sales ~ TV, data = df)
xyplot(Sales ~ TV, data = df, panel = panel.custom)
xyplot(Sales ~ TV, data = df, panel = panel.custom)
savehistory(file="~/Dropbox/AU/R/hist19sep.R")
