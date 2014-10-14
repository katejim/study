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

#training data
regmodel <- lm(Sales ~ ., training)
summary(regmodel)

Intercept <- coef(regmodel)["(Intercept)"]
TV <- coef(regmodel)["TV"]
Radio <- coef(regmodel)["Radio"]
Newspaper <- coef(regmodel)["Newspaper"]

#predicting
predictedTraining <- training$TV*TV + training$Radio *Radio + training$Newspaper*Newspaper + Intercept
predictedTest <- testing$TV*TV + testing$Radio *Radio + testing$Newspaper*Newspaper + Intercept

par(mfrow=c(1,2))
plot(predictedTraining, col="blue")
points(training$Sales, col="red")
legend('topright', c("predicted","real") ,lty=1, col=c('red', 'blue'), bty='n', cex=.75)

plot(predictedTest, col="blue")
points(testing$Sales, col="red")
legend('topright', c("predicted","real") ,lty=1, col=c('red', 'blue'), bty='n', cex=.75)

#RSE
RSETraining <- summary(regmodel)$sigma
RSETest <- sqrt(sum((testing$Sales-predictedTest)^2)/(length(predictedTest)-4))
RSETraining #1.625649
RSETest     #1.901116

#RSE without newspaper 
predictedTraining <- training$TV*TV + training$Radio *Radio +  Intercept
predictedTest <- testing$TV*TV + testing$Radio *Radio + Intercept
RSETraining <- summary(lm(Sales ~ TV + Radio, training))$sigma
RSETest <- sqrt(sum((testing$Sales-predictedTest)^2)/(length(predictedTest)-3))
RSETraining # 1.621823
RSETest     # 1.888162

#RSE without TV
predictedTraining <- training$Newspaper*Newspaper + training$Radio *Radio +  Intercept
predictedTest <- testing$Newspaper*Newspaper + testing$Radio *Radio + Intercept
RSETraining <- summary(lm(Sales ~ Newspaper + Radio, training))$sigma
RSETest <- sqrt(sum((testing$Sales-predictedTest)^2)/(length(predictedTest)-3))
RSETraining #4.354784
RSETest     #8.048166

#RSE without Radio
predictedTraining <- training$Newspaper*Newspaper + training$TV *TV +  Intercept
predictedTest <- testing$Newspaper*Newspaper + testing$TV *TV + Intercept
RSETraining <- summary(lm(Sales ~ Newspaper + TV, training))$sigma
RSETest <- sqrt(sum((testing$Sales-predictedTest)^2)/(length(predictedTest)-3))
RSETraining #3.227028
RSETest     #5.534934

#RSE only intercept
predictedTraining <- training$Newspaper*Newspaper + training$TV *TV +  Intercept
predictedTest <- Intercept
RSETraining <- summary(lm(Sales ~ -Newspaper - TV - Radio, training))$sigma
RSETest <- sqrt(sum((testing$Sales-predictedTest)^2)/(length(testing$Sales)-1))
RSETraining #5.361
RSETest     #12.04064


