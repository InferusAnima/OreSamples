"""
    Author: Jose Stovall
    Geolosys: https://github.com/oitsjustjose/Geolosys
"""
import json
import sys
import os
import shutil
import pathlib

from pip._vendor.colorama import Fore


def remove_content(folder: str):
    for filename in os.listdir(folder):
        file_path = os.path.join(folder, filename)
        try:
            if os.path.isfile(file_path) or os.path.islink(file_path):
                os.unlink(file_path)
            elif os.path.isdir(file_path):
                shutil.rmtree(file_path)
        except Exception as e:
            print('Failed to delete %s. Reason: %s' % (file_path, e))


def generate_states(states: list) -> None:
    path = "../src/main/resources/assets/oresamples/blockstates"
    path = os.path.join(pathlib.Path(__file__).parent.resolve(), path)
    if os.path.exists(path):
        remove_content(path)
        os.rmdir(path)
    os.makedirs(path)
    for state in states:
        state = f'{state[0].split(":")[1]}_sample'
        json_doc = {"variants": {"": [{"model": f"oresamples:block/{state}"}]}}
        with open(f"{path}/{state}.json", "w") as file:
            file.write(json.dumps(json_doc, indent=2))
            print(
                Fore.BLUE
                + f"Wrote {path}/{state}.json to disk"
                + Fore.RESET
            )


def generate_block_models(states: list) -> None:
    path = "../src/main/resources/assets/oresamples/models/block"
    path = os.path.join(pathlib.Path(__file__).parent.resolve(), path)
    if os.path.exists(path):
        remove_content(path)
        os.rmdir(path)
    os.makedirs(path)
    for state in states:
        state = state[0]
        mod = state.split(":")[0]
        state = state.split(":")[1]
        json_doc = {
            "parent": "oresamples:block/ore_sample",
            "textures": {"particle": "minecraft:block/gravel", "ore": f"{mod}:block/{state}", "stone": "minecraft:block/gravel"},
        }
        with open(f"{path}/{state}_sample.json", "w") as file:
            file.write(json.dumps(json_doc, indent=2))
            print(Fore.BLUE + f"Wrote {path}/{state}_sample.json to disk" + Fore.RESET)
    with open(os.path.join(pathlib.Path(__file__).parent.resolve(), "ore_sample.json"), "r") as file:
        ore_sample = json.load(file)
    with open(f"{path}/ore_sample.json", "w") as file:
            file.write(json.dumps(ore_sample, indent=2))
            print(Fore.BLUE + f"Wrote {path}/ore_sample.json to disk" + Fore.RESET)


def generate_item_models(states: list) -> None:
    path = "../src/main/resources/assets/oresamples/models/item"
    path = os.path.join(pathlib.Path(__file__).parent.resolve(), path)
    if os.path.exists(path):
        remove_content(path)
        os.rmdir(path)
    os.makedirs(path)
    for state in states:
        state = f'{state[0].split(":")[1]}_sample'
        json_doc = {"parent": f"oresamples:block/{state}"}
        with open(f"{path}/{state}.json", "w") as file:
            file.write(json.dumps(json_doc, indent=2))
            print(Fore.BLUE + f"Wrote {path}/{state}.json to disk" + Fore.RESET)


def generate_loot_tables(states: list) -> None:
    path = "../src/main/resources/data/oresamples/loot_tables/blocks"
    path = os.path.join(pathlib.Path(__file__).parent.resolve(), path)
    if os.path.exists(path):
        remove_content(path)
        os.rmdir(path)
    os.makedirs(path)
    for state in states:
        item = state[1]
        state = f'{state[0].split(":")[1]}_sample'
        json_doc = {
            "type": "minecraft:block",
            "pools": [
                {
                    "rolls": 1,
                    "entries": [
                        {
                        "type": "minecraft:item",
                        "name": item
                        }
                    ],
                    "conditions": [
                        {
                        "condition": "minecraft:survives_explosion"
                        }
                    ]
                }
            ]
        }

        with open(f"{path}/{state}.json", "w") as file:
            file.write(json.dumps(json_doc, indent=2))
            print(Fore.BLUE + f"Wrote {path}/{state}.json to disk" + Fore.RESET)


def main(block_state: bool, item_model: bool, block_model: bool, loot_table: bool) -> None:
    """
    The main program logic which generates blockstates based on the array below
    Args:
        block_state (bool): trigger whether or not to generate blockstates
        item_model (bool): trigger whether or not to generate item models
        block_model (bool): trigger whether or not to generate block models
        loot_table (bool): trigger whether or not to generate loot tables
    """
    states = [
        ("minecraft:coal_ore", "minecraft:coal"),
        ("minecraft:lapis_ore", "minecraft:lapis_lazuli"),
        ("minecraft:redstone_ore", "minecraft:redstone"),
        ("minecraft:copper_ore", "minecraft:raw_copper"),
        ("minecraft:iron_ore", "minecraft:raw_iron"),
        ("minecraft:gold_ore", "minecraft:raw_gold"),
        ("minecraft:diamond_ore", "minecraft:diamond"),
        ("minecraft:emerald_ore", "minecraft:emerald"),
        ("create:zinc_ore", "create:raw_zinc"),
    ]

    if block_state:
        generate_states(states)
    if item_model:
        generate_item_models(states)
    if block_model:
        generate_block_models(states)
    if loot_table:
        generate_loot_tables(states)


if __name__ == "__main__":

    def print_help() -> None:
        """Prints the help prompt for the user to know their options"""
        print(Fore.YELLOW + "Possible arguments:" + Fore.RESET)
        print(Fore.CYAN + "    -i: Generate Item Models" + Fore.RESET)
        print(Fore.CYAN + "    -b: Generate Block Models" + Fore.RESET)
        print(Fore.CYAN + "    -s: Generate BlockStates" + Fore.RESET)
        print(Fore.CYAN + "    -f: Generate LootTables" + Fore.RESET)
        print(Fore.CYAN + "    -a: Generate All" + Fore.RESET)
        print(Fore.CYAN + "    -h, ? : This screen" + Fore.RESET)


    possible_args = {
        "-a": False,
        "-i": False,
        "-b": False,
        "-s": False,
        "-f": False,
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
        main(True, True, True, True)
    else:
        main(possible_args["-s"], possible_args["-i"], possible_args["-b"], possible_args["-f"])