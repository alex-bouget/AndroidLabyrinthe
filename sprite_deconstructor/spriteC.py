import os

string = "!\n"

for i in os.listdir():
    string += "sprites/" + i + "/!sprites/" + i + "/spriteLoader.txt\n"

with open("spriteLoader.txt", "w") as f:
    f.write(string)