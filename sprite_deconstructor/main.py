import os
import shutil
nb_widht = 4
nb_height = 2

for i in range(nb_widht*nb_height):
    os.mkdir("folder"+str(i))
    shutil.copyfile("loaderPath.txt", "folder"+str(i)+"/spriteLoader.txt")

for j in range(nb_height*4):
    for i in range(nb_widht*3):
        folder = "folder"+str(j//4+nb_height*(i//3))
        direction = j % 4
        direction = ["Down", "Left", "Right", "Up"][direction]
        id_tile = str(j*nb_widht*3+i)
        id_tile = "tile" + id_tile.zfill(3) + ".png"
        number = (i+2) % 3
        id_return = direction + str(number) + ".png"
        os.rename(id_tile, os.path.join(folder, id_return))
