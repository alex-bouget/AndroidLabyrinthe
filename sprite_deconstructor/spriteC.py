import os

string = "!\n"

this_dir = os.getcwd().split("/")[-1]

for i in os.listdir():
    string += "sprites/" + this_dir + "/" + i + "/!sprites/" + this_dir + "/" + i + "/spriteLoader.txt\n"

with open("spriteLoader.txt", "w") as f:
    f.write(string[0:-1])
