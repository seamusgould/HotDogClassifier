### SEEFOOD

The shazam for food.

## Implementation

This app is loosely based on Silicon Valley's [![SeeFood]](https://www.youtube.com/watch?v=vIci3C4JkL0)

- I trained a neural network using Keras and Tensorflow to export it as a tensorflow lite model.
- Conducted hyperparameter tuning using Weights and Biases.
- Implemented the back end in Kotlin.
- The app supports classifier for hot dogs, but one could increase the scope of the classifier with
a hierarchical softmax loss (only if the number of categories exceeds thousands.)
  
- The result of the classifier is uploaded to the screen along with the image that is classified.

Here is a video implementation:

