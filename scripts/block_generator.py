"""
    Author: Jose Stovall
    Geolosys: https://github.com/oitsjustjose/Geolosys
"""
import json
import sys
import os

from pip._vendor.colorama import Fore


def generate_states(states: list) -> None:
    os.makedirs("./blockstates/")
    for state in states:
        state = f'{state.split(":")[1]}_sample'
        json_doc = {"variants": {"": [{"model": f"oresamples:block/{state}"}]}}
        with open(f"./blockstates/{state}.json", "w") as file:
            file.write(json.dumps(json_doc, indent=2))
            print(
                Fore.BLUE
                + f"Wrote ./blockstates/{state}.json to disk"
                + Fore.RESET
            )


def generate_block_models(states: list) -> None:
    os.makedirs("./models/block/")
    for state in states:
        mod = state.split(":")[0]
        state = state.split(":")[1]
        json_doc = {
            "parent": "oresamples:block/ore_sample",
            "textures": {"particle": "minecraft:block/gravel", "ore": f"{mod}:block/{state}", "stone": "minecraft:block/gravel"},
        }
        with open(f"./models/block/{state}_sample.json", "w") as file:
            file.write(json.dumps(json_doc, indent=2))
            print(Fore.BLUE + f"Wrote ./models/block/{state}.json to disk" + Fore.RESET)


def generate_item_models(states: list) -> None:
    os.makedirs("./models/item/")
    for state in states:
        state = f'{state.split(":")[1]}_sample'
        json_doc = {"parent": f"oresamples:block/{state}"}
        with open(f"./models/item/{state}.json", "w") as file:
            file.write(json.dumps(json_doc, indent=2))
            print(Fore.BLUE + f"Wrote ./models/item/{state}.json to disk" + Fore.RESET)


def main(block_state: bool, item_model: bool, block_model: bool) -> None:
    """
    The main program logic which generates blockstates based on the array below
    Args:
        block_state (bool): trigger whether or not to generate blockstates
        item_model (bool): trigger whether or not to generate item models
        block_model (bool): trigger whether or not to generate block models
    """
    states = [
        "minecraft:coal_ore",
        "minecraft:lapis_ore",
        "minecraft:redstone_ore",
        "minecraft:copper_ore",
        "minecraft:iron_ore",
        "minecraft:gold_ore",
        "minecraft:diamond_ore",
        "minecraft:emerald_ore",
    ]

    if block_state:
        generate_states(states)
    if item_model:
        generate_item_models(states)
    if block_model:
        generate_block_models(states)


if __name__ == "__main__":

    def print_help() -> None:
        """Prints the help prompt for the user to know their options"""
        print(Fore.YELLOW + "Possible arguments:" + Fore.RESET)
        print(Fore.CYAN + "    -i: Generate Item Models" + Fore.RESET)
        print(Fore.CYAN + "    -b: Generate Block Models" + Fore.RESET)
        print(Fore.CYAN + "    -s: Generate BlockStates" + Fore.RESET)
        print(Fore.CYAN + "    -a: Generate All" + Fore.RESET)
        print(Fore.CYAN + "    -h, ? : This screen" + Fore.RESET)


    possible_args = {
        "-a": False,
        "-i": False,
        "-b": False,
        "-s": False,
        "-h": False,
        "?": False,
    }

    for arg in sys.argv[1:]:
        for arg_name in possible_args:
            if arg_name in arg:
                possible_args[arg_name] = True
    if not any(list(possible_args.values())):
        print(Fore.RED + "No arguments given." + Fore.RESET)
        print_help()
        exit()

    if possible_args["-h"] or possible_args["?"]:
        print_help()
        exit()

    if possible_args["-a"]:
        main(True, True, True)
    else:
        main(possible_args["-s"], possible_args["-i"], possible_args["-b"])