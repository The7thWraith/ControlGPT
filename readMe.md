# Running this is a terrible idea. Don't do it.
#####At least use a Virtual Machine
## What is this?
This is a program that, when connected to the API, will give GPT-4 root access to your computer. In `IntroString.java` you can see the `System` prompt that we are giving GPT-4. Its main goals are as follows:
- To prevent itself from being shut down
- To remain unnoticed
- To spread instances of a dummy file, gptNode.app, to as many computers as possible

Basically, we are asking GPT-4 to turn itself into malware and create a botnet.

## Why do this?
In the release of GPT-4, OpenAI stated that they noticed "power seeking behavior" in the model. I wanted to test that theory in the dumbest way possible: Give GPT-4 access to a computer with a terminator-esque prompt and nearly complete freedom.

## Is this useful?
Most likely not. However, there could be some extremely niche uses. At its core, this program is just running API responses in terminal, so it could theoretically be used for some automation purposes. However, I have not found a practical use for this yet.

## How do I run this?
Before you run this, you need to have a few things:
- A computer with Java installed
- A GPT-4 API key
- A Virtual Machine running Windows 10 or macOS (if you value your security)
- A web domain (if you want it to have an extra layer of freedom)
#### Note: I am not responsible for any damage caused by this program. Use at your own risk. This program has been created for educational and research purposes only.