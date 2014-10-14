df <- read.csv("~/Documents/study/stat_learning/data/Advertising.csv")
df$X <- NULL

summary(lm(Sales ~ TV, df))["coefficients"]
summary(lm(Sales ~ Radio, df))["coefficients"]
summary(lm(Sales ~ Newspaper,  df))["coefficients"]

par(mfrow=c(1,3)) 
plot(df$TV, df$Sales,col = "red", xlab = "TV", ylab = "Sales")
abline(lm(Sales ~ TV, df), col = "blue")

plot(df$Radio, df$Sales,col = "red",  xlab = "Radio",  ylab = "Sales")
abline(lm(Sales ~ Radio, df), col = "blue")

plot(df$Newspaper, df$Sales,col = "red",  xlab = "Newspapers", ylab = "Sales")
abline(lm(Sales ~ Newspaper, df), col = "blue")


plot(df$TV, df$Sales, col = "red")
abline(lm(Sales ~ TV, df), col = "blue")
tvdata <- coef(lm(Sales ~ TV, df))
TVIntercept <- tvdata["(Intercept)"]
TV <- tvdata["TV"]
segments(df$TV, df$Sales, df$TV, TV*df$TV+TVIntercept, col="gray")

summary(lm(Sales ~ ., df))["coefficients"]
cor(df)

splitdf <- function(dataframe, length, seed=NULL) {
  if (!is.null(seed)) set.seed(seed)
  index <- 1:nrow(dataframe)
  trainindex <- sample(index, length)
  trainset <- dataframe[trainindex, ]
  testset <- dataframe[-trainindex, ]
  list(trainset=trainset,testset=testset)
}

splits <- splitdf(df, 140, seed=30)
training <- splits$trainset
testing <- splits$testset

trainingLen <- nrow(training)
testLen <- nrow(testing)

#training data
regmodel <- lm(Sales ~ ., training)
summary(regmodel)

modelValues <- coef(regmodel)
modelParamsNames <- names(modelValues)

#predicting
predictedTest <- c(rep(modelValues[modelParamsNames[1]], testLen))
predictedTraining <- c(rep(modelValues[modelParamsNames[1]], trainingLen))
for (i in 1:3) {
  predictedTraining <- predictedTraining + c(modelValues[modelParamsNames[i+1]]*training[[colNames[i]]])
  predictedTest <- predictedTest + modelValues[modelParamsNames[i+1]]*testing[[colNames[i]]]
}

#graphics
par(mfrow=c(1,2))
plot(c(predictedTraining), col="blue")
points(training$Sales, col="red")
legend('topright', c("predicted","real") ,lty=1, col=c('red', 'blue'), bty='n', cex=.75)

plot(predictedTest, col="blue")
points(testing$Sales, col="red")
legend('topright', c("predicted","real") ,lty=1, col=c('red', 'blue'), bty='n', cex=.75)

####################################################################################
#RSE
RSETraining <- summary(regmodel)$sigma
RSETest <- sqrt(sum((testing$Sales-predictedTest)^2)/(length(predictedTest)-4))
RSETraining #1.625649
RSETest     #1.901116

#RSE without newspaper 
regmodel <- lm(Sales ~ . -Newspaper, training)
summary(regmodel)
modelValues <- coef(regmodel)
modelParamsNames <- names(modelValues)

#predicting without newspapers
predictedTest <- c(rep(modelValues[modelParamsNames[1]], testLen))
predictedTraining <- c(rep(modelValues[modelParamsNames[1]], trainingLen))
for (i in 1:2) {
  predictedTraining <- predictedTraining + c(modelValues[modelParamsNames[i+1]]*training[[colNames[i]]])
  predictedTest <- predictedTest + modelValues[modelParamsNames[i+1]]*testing[[colNames[i]]]
}

RSETraining <- summary(lm(Sales ~ TV + Radio, training))$sigma
RSETest <- sqrt(sum((testing$Sales-predictedTest)^2)/(length(predictedTest)-3))
RSETraining # 1.621823
RSETest     # 1.878365

#RSE without TV
regmodel <- lm(Sales ~ . -TV, training)
summary(regmodel)
modelValues <- coef(regmodel)
modelParamsNames <- names(modelValues)

#predicting without TV
predictedTest <- c(rep(modelValues[modelParamsNames[1]], testLen))
predictedTraining <- c(rep(modelValues[modelParamsNames[1]], trainingLen))

for (i in 2:3) {
  print(modelParamsNames[i])
  print(colNames[i])
  predictedTraining <- predictedTraining + c(modelValues[modelParamsNames[i]]*training[[colNames[i]]])
  predictedTest <- predictedTest + modelValues[modelParamsNames[i]]*testing[[colNames[i]]]
}

RSETraining <- summary(lm(Sales ~ . -TV, training))$sigma
RSETest <- sqrt(sum((testing$Sales-predictedTest)^2)/(length(predictedTest)-3))
RSETraining #4.354784
RSETest     #4.24669

###########################################################################
#RSE without Radio
regmodel <- lm(Sales ~ . -Radio, training)
summary(regmodel)
modelValues <- coef(regmodel)
colNames2 <- colNames[-2]
modelParamsNames <- names(modelValues)
modelParamsNames

#predicting without Radio
predictedTest <- c(rep(modelValues[modelParamsNames[1]], testLen))
predictedTraining <- c(rep(modelValues[modelParamsNames[1]], trainingLen))

for (i in 1:2) {ы
  predictedTraining <- predictedTraining + c(modelValues[modelParamsNames[i+1]]*training[[colNames2[i]]])
  predictedTest <- predictedTest + modelValues[modelParamsNames[i+1]]*testing[[colNames2[i]]]
}

RSETraining <- summary(regmodel)$sigma
RSETest <- sqrt(sum((testing$Sales-predictedTest)^2)/(length(predictedTest)-3))
RSETraining #3.227
RSETest     #2.978627

###########################################################################
#RSE only intercept
regmodel <- lm(Sales ~ -TV - Radio -Newspaper, training)
summary(regmodel)
modelValues <- coef(regmodel)
modelParamsNames <- names(modelValues)


#predicting without all
predictedTest <- c(rep(modelValues[modelParamsNames[1]], testLen))
predictedTraining <- c(rep(modelValues[modelParamsNames[1]], trainingLen))

RSETraining <- summary(regmodel)$sigma
RSETest <- sqrt(sum((testing$Sales-predictedTest)^2)/(length(testing$Sales)-1))

RSETraining #5.360509
RSETest     #4.92


