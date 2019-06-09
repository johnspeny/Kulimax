import numpy as np
import os
from keras.models import model_from_json

json_file=open('model.json','r')
model_load=json_file.read()
classifier=model_from_json(model_load)
json_file.close()
classifier.load_weights('model_weights.h5')
print('done')
#prediction

from keras.preprocessing import image
m =['diseaseX','diseaseY','diseaseZ','Healthy']
test_image = image.load_img('test/diseaseY/Y1.jpg', target_size=(64,64))
test_image = image.img_to_array(test_image)
test_image = np.expand_dims(test_image, axis=0)
result = classifier.predict_classes(test_image)
print(m[int(result)])
#training_set.class_indices

#converting model to tflite
from tensorflow.contrib import lite
converter = lite.TFLiteConverter.from_keras_model_file( 'model.h5' ) # Your model's name
model = converter.convert()
file = open( 'model.tflite' , 'wb' )
file.write( model )


