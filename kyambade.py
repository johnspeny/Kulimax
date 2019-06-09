from keras.models import Sequential
from  keras.layers import Conv2D
from keras.layers import MaxPooling2D
from keras.layers import Flatten
from keras.layers import Dense
from  keras.models import  model_from_json
from keras.preprocessing.image import ImageDataGenerator
import os,json
import matplotlib.pyplot as plt
#initialize the CNN classifier
#(3by3 dimension of pixels from 32 byte image)
classifier=Sequential()
classifier.add(Conv2D(32,(3,3),input_shape=(64, 64, 3), activation='relu'))

# step 3: pooling reducing 3*3 matrix to a 2*2 dim matrix
classifier.add(MaxPooling2D(pool_size=(2, 2)))
# step 4: flattening converting 2*2 matrix to a Linear matrix 1D matrix of pixels
classifier.add(Flatten())

# step 5:Full connection is connecting our convolutional network to a neural network
# Here we have made 2 layer neural network with a sigmoid function as an activation function for the last layer as we need to find the probability of the object being a cat or a dog.
classifier.add(Dense(output_dim=128,activation='relu'))
classifier.add(Dense(output_dim=4,activation='softmax'))# change frm sigmoid to softmax
# lastly compiling classifier
classifier.compile(optimizer='adam',loss='sparse_categorical_crossentropy',metrics=['accuracy'])#change binary crossentropy to categorical cross entropy

# part 2 data segmentation
#fitting CNN  with images
from keras.preprocessing.image import ImageDataGenerator
train_datagen=ImageDataGenerator(rescale=1./255,shear_range=0.2,zoom_range=0.2,horizontal_flip=True)
#test_datagen
test_datagen=ImageDataGenerator(rescale=1./255)
#getting images from directories
training_set=train_datagen.flow_from_directory('C:/Users/seruyange charles/Desktop/trialclassifier/Tensorflow-image-recognition-master/train',target_size=(64,64),batch_size=10,class_mode='binary')
#repeat the same for test data
test_set=train_datagen.flow_from_directory('C:/Users/seruyange charles/Desktop/trialclassifier/Tensorflow-image-recognition-master/test',target_size=(64,64),batch_size=10,class_mode='binary')

#fitting data into classifier
history = classifier.fit_generator(training_set,steps_per_epoch=100,epochs=500,validation_data=test_set,validation_steps=10)
print('Done...................')
with open('history.json','w') as f:
    json.dump(history.history,f)
#making new prediction
import numpy as np
from keras.preprocessing import image
test_image=image.load_img('test/diseaseX/X1.jpg',target_size=(64,64))
test_image=image.img_to_array(test_image)
test_image=np.expand_dims(test_image,axis=0)
result=classifier.predict(test_image)
'''training_set.class_indices
if result[0][0]==1:
    prediction='horse'
else:
    prediction='car'
print(prediction)'''


classifier.save_weights('model_weights.h5')
classifier.save('model.h5')
model_json=classifier.to_json()
with open('model.json','w') as json_file:
     json_file.write(model_json)

plt.plot(history.history['loss'])
plt.plot(history.history['val_loss'])
plt.title('model loss')
plt.ylabel('loss')
plt.xlabel('epoch')
plt.legend(['train', 'test'], loc='upper left')
plt.show()

